package cmd

import (
	"context"
	"fmt"
	"strings"
	"time"

	"github.com/spf13/cobra"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"github.com/pixb/DnfGameServer/dnf-go-server/store/db"
)

// migrateCmd represents the migrate command
var migrateCmd = &cobra.Command{
	Use:   "migrate",
	Short: "Run database migrations",
	Long: `Run database migrations to set up or update the database schema.

This command will:
1. Connect to the database
2. Check if migrations are needed
3. Apply any pending migrations

Examples:
  # Run all pending migrations
  dnf-server migrate

  # Migrate from Java DnfGameServer (requires existing Java tables)
  dnf-server migrate from-java

  # Check migration status
  dnf-server migrate status`,
}

// migrateFromJavaCmd 从 Java 版本迁移数据
var migrateFromJavaCmd = &cobra.Command{
	Use:   "from-java",
	Short: "Migrate data from Java DnfGameServer",
	Long: `Migrate data from existing Java DnfGameServer tables.

This command will:
1. Create new Go schema tables if not exists
2. Migrate data from Java tables (t_account, t_role, etc.)
3. Preserve all original data

WARNING: Make sure to backup your database before running this!`,
	RunE: runMigrateFromJava,
}

// migrateStatusCmd 查看迁移状态
var migrateStatusCmd = &cobra.Command{
	Use:   "status",
	Short: "Check migration status",
	Long:  `Show the status of all applied migrations.`,
	RunE:  runMigrateStatus,
}

func init() {
	rootCmd.AddCommand(migrateCmd)
	migrateCmd.AddCommand(migrateFromJavaCmd)
	migrateCmd.AddCommand(migrateStatusCmd)

	// 添加从 Java 迁移的 flag
	migrateFromJavaCmd.Flags().Bool("dry-run", false, "Preview migration without executing")
	migrateFromJavaCmd.Flags().Bool("force", false, "Force migration even if tables exist")
}

func runMigrate(cmd *cobra.Command, args []string) error {
	ctx := context.Background()

	// 获取配置
	prof := GetProfile()
	if prof == nil {
		return fmt.Errorf("profile not initialized")
	}

	fmt.Printf("Running database migrations...\n")
	fmt.Printf("Driver: %s\n", prof.Driver)
	fmt.Printf("DSN: %s\n", maskDSN(prof.DSN))

	// 检查DSN
	if prof.DSN == "" {
		return fmt.Errorf("database DSN not configured. Use --dsn flag or DATABASE_DSN env var")
	}

	// 创建数据库驱动
	driver, err := db.NewDBDriver(prof)
	if err != nil {
		return fmt.Errorf("failed to create database driver: %w", err)
	}
	defer driver.Close()

	// 创建Store
	s := store.New(driver, prof)
	defer s.Close()

	// 执行迁移
	fmt.Println("Applying migrations...")
	if err := s.Migrate(ctx); err != nil {
		return fmt.Errorf("failed to migrate database: %w", err)
	}

	// 显示迁移状态
	records, err := s.GetMigrationStatus(ctx)
	if err != nil {
		fmt.Printf("Warning: failed to get migration status: %v\n", err)
	} else {
		fmt.Printf("\nApplied migrations: %d\n", len(records))
		for _, r := range records {
			fmt.Printf("  - %s: %s\n", r.Version, r.Description)
		}
	}

	fmt.Println("\n✅ Database migrations completed successfully!")
	return nil
}

func runMigrateFromJava(cmd *cobra.Command, args []string) error {
	ctx := context.Background()

	// 获取配置
	prof := GetProfile()
	if prof == nil {
		return fmt.Errorf("profile not initialized")
	}

	fmt.Println("========================================")
	fmt.Println("Java DnfGameServer Data Migration")
	fmt.Println("========================================")
	fmt.Printf("Driver: %s\n", prof.Driver)
	fmt.Printf("DSN: %s\n", maskDSN(prof.DSN))

	// 检查DSN
	if prof.DSN == "" {
		return fmt.Errorf("database DSN not configured")
	}

	// 检查是否为 dry-run
	dryRun, _ := cmd.Flags().GetBool("dry-run")
	if dryRun {
		fmt.Println("\n[DRY RUN MODE] No changes will be made")
	}

	// 确认
	force, _ := cmd.Flags().GetBool("force")
	if !force && !dryRun {
		fmt.Println("\n⚠️  WARNING: This will migrate data from Java tables.")
		fmt.Println("Make sure you have backed up your database!")
		fmt.Print("\nContinue? [y/N]: ")
		var response string
		fmt.Scanln(&response)
		if response != "y" && response != "Y" {
			fmt.Println("Migration cancelled.")
			return nil
		}
	}

	// 创建数据库驱动
	driver, err := db.NewDBDriver(prof)
	if err != nil {
		return fmt.Errorf("failed to create database driver: %w", err)
	}
	defer driver.Close()

	// 创建Store
	s := store.New(driver, prof)
	defer s.Close()

	// 首先运行基础迁移
	fmt.Println("\n1. Creating base schema...")
	if !dryRun {
		if err := s.Migrate(ctx); err != nil {
			return fmt.Errorf("failed to create base schema: %w", err)
		}
	}
	fmt.Println("   ✅ Base schema created")

	// 从 Java 迁移数据
	fmt.Println("\n2. Migrating data from Java tables...")
	if !dryRun {
		if err := s.MigrateFromJava(ctx); err != nil {
			return fmt.Errorf("failed to migrate from Java: %w", err)
		}
	}
	fmt.Println("   ✅ Data migration completed")

	fmt.Println("\n========================================")
	fmt.Println("✅ Migration completed successfully!")
	fmt.Println("========================================")
	fmt.Println("\nData migrated:")
	fmt.Println("  - Accounts (t_account -> account)")
	fmt.Println("  - Roles (t_role -> role)")
	fmt.Println("  - Notices (t_notice -> notice)")
	fmt.Println("  - Pay data (t_paydata -> pay_data)")
	fmt.Println("  - Charges (t_charge -> charge)")
	fmt.Println("  - Offline data (t_offline -> offline_data)")
	fmt.Println("  - Auctions (t_auction -> auction)")
	fmt.Println("  - Quests (p_taskset/p_taskinfo -> quest)")

	return nil
}

func runMigrateStatus(cmd *cobra.Command, args []string) error {
	ctx := context.Background()

	// 获取配置
	prof := GetProfile()
	if prof == nil {
		return fmt.Errorf("profile not initialized")
	}

	fmt.Println("Migration Status")
	fmt.Println("================")
	fmt.Printf("Driver: %s\n\n", prof.Driver)

	// 检查DSN
	if prof.DSN == "" {
		return fmt.Errorf("database DSN not configured")
	}

	// 创建数据库驱动
	driver, err := db.NewDBDriver(prof)
	if err != nil {
		return fmt.Errorf("failed to create database driver: %w", err)
	}
	defer driver.Close()

	// 创建Store
	s := store.New(driver, prof)
	defer s.Close()

	// 获取迁移状态
	records, err := s.GetMigrationStatus(ctx)
	if err != nil {
		return fmt.Errorf("failed to get migration status: %w", err)
	}

	if len(records) == 0 {
		fmt.Println("No migrations applied yet.")
		fmt.Println("Run 'dnf-server migrate' to apply migrations.")
		return nil
	}

	fmt.Printf("Applied migrations: %d\n\n", len(records))
	fmt.Printf("%-15s %-30s %s\n", "VERSION", "APPLIED AT", "DESCRIPTION")
	fmt.Println(strings.Repeat("-", 80))
	for _, r := range records {
		appliedAt := time.Unix(r.AppliedAt, 0).Format("2006-01-02 15:04:05")
		fmt.Printf("%-15s %-30s %s\n", r.Version, appliedAt, r.Description)
	}

	return nil
}

// maskDSN 隐藏 DSN 中的密码
func maskDSN(dsn string) string {
	// 简单的密码隐藏，实际应用中可能需要更完善的处理
	if dsn == "" {
		return ""
	}
	// 如果包含密码，替换为 ***
	if strings.Contains(dsn, ":") && strings.Contains(dsn, "@") {
		parts := strings.Split(dsn, "@")
		if len(parts) == 2 {
			auth := parts[0]
			if strings.Contains(auth, ":") {
				authParts := strings.SplitN(auth, ":", 2)
				if len(authParts) == 2 {
					return authParts[0] + ":***@" + parts[1]
				}
			}
		}
	}
	return dsn
}
