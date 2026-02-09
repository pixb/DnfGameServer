package store

import (
	"context"
	"embed"
	"fmt"
	"io/fs"
	"sort"
	"strings"
)

//go:embed migration/*/*.sql
var migrationFS embed.FS

// Migrator 数据库迁移器
type Migrator struct {
	driver  Driver
	profile ProfileProvider
}

// MigrationRecord 迁移记录
type MigrationRecord struct {
	Version     string
	AppliedAt   int64
	Description string
}

// NewMigrator 创建迁移器
func NewMigrator(driver Driver, profile ProfileProvider) *Migrator {
	return &Migrator{
		driver:  driver,
		profile: profile,
	}
}

// Migrate 执行数据库迁移
func (m *Migrator) Migrate(ctx context.Context) error {
	// 1. 确保 schema_migrations 表存在
	if err := m.ensureMigrationsTable(ctx); err != nil {
		return fmt.Errorf("failed to create migrations table: %w", err)
	}

	// 2. 检查是否已初始化
	initialized, err := m.driver.IsInitialized(ctx)
	if err != nil {
		// 尝试从 schema_migrations 表判断
		initialized = m.isSchemaMigrationsExists(ctx)
	}

	// 3. 如果未初始化，执行初始迁移
	if !initialized {
		if err := m.runInitialMigration(ctx); err != nil {
			return fmt.Errorf("failed to run initial migration: %w", err)
		}
	}

	// 4. 检查并执行增量迁移
	if err := m.runPendingMigrations(ctx); err != nil {
		return fmt.Errorf("failed to run pending migrations: %w", err)
	}

	return nil
}

// ensureMigrationsTable 确保迁移表存在
func (m *Migrator) ensureMigrationsTable(ctx context.Context) error {
	driver := m.profile.GetDriver()
	var query string

	switch driver {
	case "sqlite":
		query = `
			CREATE TABLE IF NOT EXISTS schema_migrations (
				version TEXT PRIMARY KEY,
				applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
				description TEXT NOT NULL DEFAULT ''
			)
		`
	default: // mysql and others
		query = `
			CREATE TABLE IF NOT EXISTS schema_migrations (
				version VARCHAR(255) PRIMARY KEY,
				applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
				description TEXT NOT NULL DEFAULT ''
			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
		`
	}

	db := m.driver.GetDB()
	_, err := db.ExecContext(ctx, query)
	return err
}

// isSchemaMigrationsExists 检查迁移表是否存在
func (m *Migrator) isSchemaMigrationsExists(ctx context.Context) bool {
	driver := m.profile.GetDriver()
	var query string

	switch driver {
	case "sqlite":
		query = `SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='schema_migrations'`
	default: // mysql
		query = `
			SELECT COUNT(*) FROM information_schema.tables 
			WHERE table_schema = DATABASE() AND table_name = 'schema_migrations'
		`
	}

	var count int
	db := m.driver.GetDB()
	err := db.QueryRowContext(ctx, query).Scan(&count)
	return err == nil && count > 0
}

// runInitialMigration 执行初始迁移
func (m *Migrator) runInitialMigration(ctx context.Context) error {
	// 读取 LATEST.sql
	sql, err := m.GetLatestSchema()
	if err != nil {
		return fmt.Errorf("failed to read latest schema: %w", err)
	}

	// 执行 SQL
	db := m.driver.GetDB()
	if _, err := db.ExecContext(ctx, sql); err != nil {
		return fmt.Errorf("failed to execute initial schema: %w", err)
	}

	return nil
}

// runPendingMigrations 执行待处理的迁移
func (m *Migrator) runPendingMigrations(ctx context.Context) error {
	// 获取已应用的版本
	applied, err := m.getAppliedMigrations(ctx)
	if err != nil {
		return fmt.Errorf("failed to get applied migrations: %w", err)
	}

	// 获取所有可用迁移
	available, err := m.getAvailableMigrations()
	if err != nil {
		return fmt.Errorf("failed to get available migrations: %w", err)
	}

	// 执行未应用的迁移
	for _, migration := range available {
		if _, ok := applied[migration.Version]; !ok {
			if err := m.applyMigration(ctx, migration); err != nil {
				return fmt.Errorf("failed to apply migration %s: %w", migration.Version, err)
			}
		}
	}

	return nil
}

// getAppliedMigrations 获取已应用的迁移
func (m *Migrator) getAppliedMigrations(ctx context.Context) (map[string]bool, error) {
	query := `SELECT version FROM schema_migrations`
	rows, err := m.driver.GetDB().QueryContext(ctx, query)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	applied := make(map[string]bool)
	for rows.Next() {
		var version string
		if err := rows.Scan(&version); err != nil {
			return nil, err
		}
		applied[version] = true
	}

	return applied, rows.Err()
}

// Migration 迁移信息
type Migration struct {
	Version     string
	FilePath    string
	Description string
}

// getAvailableMigrations 获取所有可用迁移
func (m *Migrator) getAvailableMigrations() ([]Migration, error) {
	driver := m.profile.GetDriver()
	basePath := "migration/" + driver + "/"

	// 读取目录
	entries, err := migrationFS.ReadDir(basePath)
	if err != nil {
		// 目录不存在时返回空列表
		return []Migration{}, nil
	}

	var migrations []Migration
	for _, entry := range entries {
		if entry.IsDir() {
			continue
		}
		name := entry.Name()
		if !strings.HasSuffix(name, ".sql") {
			continue
		}

		// 跳过 LATEST.sql，它是初始迁移文件
		if name == "LATEST.sql" {
			continue
		}

		// 解析版本号 (格式: 1.0.0__description.sql)
		version := m.parseVersion(name)
		migrations = append(migrations, Migration{
			Version:     version,
			FilePath:    basePath + name,
			Description: m.parseDescription(name),
		})
	}

	// 按版本排序
	sort.Slice(migrations, func(i, j int) bool {
		return compareVersions(migrations[i].Version, migrations[j].Version) < 0
	})

	return migrations, nil
}

// applyMigration 应用单个迁移
func (m *Migrator) applyMigration(ctx context.Context, migration Migration) error {
	// 读取迁移文件
	sql, err := migrationFS.ReadFile(migration.FilePath)
	if err != nil {
		return fmt.Errorf("failed to read migration file: %w", err)
	}

	// 开始事务
	tx, err := m.driver.GetDB().BeginTx(ctx, nil)
	if err != nil {
		return fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	// 执行迁移
	if _, err := tx.ExecContext(ctx, string(sql)); err != nil {
		return fmt.Errorf("failed to execute migration: %w", err)
	}

	// 记录迁移
	if _, err := tx.ExecContext(ctx,
		"INSERT INTO schema_migrations (version, description) VALUES (?, ?)",
		migration.Version, migration.Description); err != nil {
		return fmt.Errorf("failed to record migration: %w", err)
	}

	// 提交
	return tx.Commit()
}

// parseVersion 从文件名解析版本号
func (m *Migrator) parseVersion(filename string) string {
	// 格式: 1.0.0__description.sql
	parts := strings.Split(filename, "__")
	if len(parts) > 0 {
		return strings.TrimSuffix(parts[0], ".sql")
	}
	return strings.TrimSuffix(filename, ".sql")
}

// parseDescription 从文件名解析描述
func (m *Migrator) parseDescription(filename string) string {
	// 格式: 1.0.0__description.sql
	parts := strings.Split(filename, "__")
	if len(parts) > 1 {
		desc := strings.TrimSuffix(parts[1], ".sql")
		return strings.ReplaceAll(desc, "_", " ")
	}
	return ""
}

// getMigrationBasePath 获取迁移文件基础路径
func (m *Migrator) getMigrationBasePath() string {
	driver := m.profile.GetDriver()
	return "migration/" + driver + "/"
}

// GetLatestSchema 获取最新Schema
func (m *Migrator) GetLatestSchema() (string, error) {
	filePath := m.getMigrationBasePath() + "LATEST.sql"
	bytes, err := migrationFS.ReadFile(filePath)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// GetMigrationFiles 获取所有迁移文件
func (m *Migrator) GetMigrationFiles() ([]string, error) {
	pattern := m.getMigrationBasePath() + "*/*.sql"
	files, err := fs.Glob(migrationFS, pattern)
	if err != nil {
		return nil, err
	}
	sort.Strings(files)
	return files, nil
}

// GetMigrationStatus 获取迁移状态
func (m *Migrator) GetMigrationStatus(ctx context.Context) ([]MigrationRecord, error) {
	query := `SELECT version, applied_at, description FROM schema_migrations ORDER BY applied_at`
	rows, err := m.driver.GetDB().QueryContext(ctx, query)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var records []MigrationRecord
	for rows.Next() {
		var r MigrationRecord
		if err := rows.Scan(&r.Version, &r.AppliedAt, &r.Description); err != nil {
			return nil, err
		}
		records = append(records, r)
	}

	return records, rows.Err()
}

// compareVersions 比较版本号
func compareVersions(v1, v2 string) int {
	return strings.Compare(v1, v2)
}

// MigrateStore Store的迁移方法
func (s *Store) Migrate(ctx context.Context) error {
	migrator := NewMigrator(s.driver, s.profile)
	return migrator.Migrate(ctx)
}

// GetMigrationStatus 获取迁移状态
func (s *Store) GetMigrationStatus(ctx context.Context) ([]MigrationRecord, error) {
	migrator := NewMigrator(s.driver, s.profile)
	return migrator.GetMigrationStatus(ctx)
}

// MigrateFromJava 从 Java 版本迁移数据
func (s *Store) MigrateFromJava(ctx context.Context) error {
	// 读取迁移脚本
	sql, err := migrationFS.ReadFile("migration/mysql/1.0.0__migrate_from_java.sql")
	if err != nil {
		return fmt.Errorf("failed to read migration script: %w", err)
	}

	// 执行迁移
	_, err = s.driver.GetDB().ExecContext(ctx, string(sql))
	if err != nil {
		return fmt.Errorf("failed to execute migration: %w", err)
	}

	return nil
}
