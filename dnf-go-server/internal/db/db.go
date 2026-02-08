package db

import (
	"context"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/config"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"

	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"gorm.io/gorm/schema"
)

// DB 数据库连接管理
type DB struct {
	*gorm.DB
}

// NewDB 创建数据库连接
func NewDB(cfg *config.DatabaseConfig) (*DB, error) {
	dsn := cfg.GetDSN()

	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{
		NamingStrategy: schema.NamingStrategy{
			SingularTable: true, // 使用单数表名
		},
		// 禁用默认事务，提升性能（游戏服务器通常自己控制事务）
		SkipDefaultTransaction: true,
	})

	if err != nil {
		return nil, fmt.Errorf("failed to connect to database: %w", err)
	}

	// 配置连接池
	sqlDB, err := db.DB()
	if err != nil {
		return nil, fmt.Errorf("failed to get sql.DB: %w", err)
	}

	sqlDB.SetMaxOpenConns(cfg.MaxOpenConns)
	sqlDB.SetMaxIdleConns(cfg.MaxIdleConns)
	sqlDB.SetConnMaxLifetime(time.Duration(cfg.ConnMaxLifetime) * time.Second)
	sqlDB.SetConnMaxIdleTime(time.Duration(cfg.ConnMaxIdleTime) * time.Second)

	logger.Info("database connected successfully",
		logger.String("host", cfg.Host),
		logger.Int("port", cfg.Port),
		logger.String("database", cfg.Database),
	)

	database := &DB{db}

	// 自动迁移所有表
	if err := database.AutoMigrate(); err != nil {
		return nil, fmt.Errorf("failed to auto migrate: %w", err)
	}

	return database, nil
}

// Close 关闭数据库连接
func (d *DB) Close() error {
	sqlDB, err := d.DB.DB()
	if err != nil {
		return err
	}
	return sqlDB.Close()
}

// Ping 检查数据库连接
func (d *DB) Ping(ctx context.Context) error {
	sqlDB, err := d.DB.DB()
	if err != nil {
		return err
	}
	return sqlDB.PingContext(ctx)
}

// AutoMigrate 自动迁移所有表结构
func (d *DB) AutoMigrate() error {
	logger.Info("auto migrating database tables...")

	err := d.DB.AutoMigrate(
		&models.Account{},
		&models.Role{},
		&models.RoleAttributes{},
		&models.RolePosition{},
		&models.RoleSkill{},
		&models.BagItem{},
		&models.RoleCurrency{},
		&models.ItemBase{},
		&models.Quest{},
		&models.RoleQuest{},
		&models.Friend{},
		&models.FriendRequest{},
		&models.Guild{},
		&models.GuildMember{},
		&models.GuildSkill{},
		&models.GuildJoinRequest{},
		&models.Mail{},
	)

	if err != nil {
		return err
	}

	logger.Info("database tables migrated successfully")
	return nil
}

// String 辅助函数：将 string 转换为 *string
func String(s string) *string {
	return &s
}

// Int 辅助函数：将 int 转换为 *int
func Int(i int) *int {
	return &i
}

// Int64 辅助函数：将 int64 转换为 *int64
func Int64(i int64) *int64 {
	return &i
}

// Bool 辅助函数：将 bool 转换为 *bool
func Bool(b bool) *bool {
	return &b
}
