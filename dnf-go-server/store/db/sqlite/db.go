package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"os"
	"path/filepath"
	"time"

	_ "github.com/mattn/go-sqlite3"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ProfileProvider 配置接口
type ProfileProvider interface {
	GetDriver() string
	GetDSN() string
	GetMode() string
}

// DB SQLite数据库实现
type DB struct {
	db     *sql.DB
	config ProfileProvider
}

// NewDB 创建SQLite数据库实例
func NewDB(config ProfileProvider) (store.Driver, error) {
	dsn := config.GetDSN()

	// 确保目录存在
	dir := filepath.Dir(dsn)
	if dir != "" && dir != "." {
		if err := os.MkdirAll(dir, 0755); err != nil {
			return nil, fmt.Errorf("failed to create database directory: %w", err)
		}
	}

	db, err := sql.Open("sqlite3", dsn+"?_journal_mode=WAL&_synchronous=NORMAL&_busy_timeout=5000")
	if err != nil {
		return nil, fmt.Errorf("failed to open sqlite connection: %w", err)
	}

	// 配置连接池
	db.SetMaxOpenConns(1) // SQLite单写
	db.SetMaxIdleConns(1)
	db.SetConnMaxLifetime(1 * time.Hour)

	// 测试连接
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	if err := db.PingContext(ctx); err != nil {
		return nil, fmt.Errorf("failed to ping sqlite: %w", err)
	}

	return &DB{db: db, config: config}, nil
}

// GetDB 获取底层sql.DB
func (d *DB) GetDB() *sql.DB {
	return d.db
}

// Close 关闭数据库连接
func (d *DB) Close() error {
	return d.db.Close()
}

// IsInitialized 检查数据库是否已初始化
func (d *DB) IsInitialized(ctx context.Context) (bool, error) {
	query := `SELECT 1 FROM account LIMIT 1`
	var dummy int
	err := d.db.QueryRowContext(ctx, query).Scan(&dummy)
	if err == sql.ErrNoRows {
		return true, nil // 表存在但无数据
	}
	if err != nil {
		return false, nil // 表不存在
	}
	return true, nil
}

// GetCurrentSchemaVersion 获取当前Schema版本
func (d *DB) GetCurrentSchemaVersion() string {
	// TODO: 从数据库读取版本
	return "0.1"
}
