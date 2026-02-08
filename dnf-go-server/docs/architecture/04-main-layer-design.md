# Main层架构设计文档 (Cobra CLI)

## 1. 概述

### 1.1 设计目标
- 使用Cobra CLI框架规范化命令行接口
- 支持子命令(serve, migrate, version)
- 使用Viper进行配置管理
- 实现优雅关闭和信号处理

### 1.2 与当前main.go的对比

| 特性 | 当前实现 (196行) | 新架构 (Cobra) |
|-----|-----------------|----------------|
| 命令解析 | flag包 | Cobra子命令 |
| 配置管理 | 自定义加载 | Viper + 配置文件 |
| 代码组织 | 单一文件 | 模块化 |
| 扩展性 | 差 | 支持多子命令 |
| 帮助文档 | 手动实现 | 自动生成 |

## 2. 目录结构

```
cmd/server/
├── main.go              # 入口文件 (30行以内)
└── cmd/
    ├── root.go          # 根命令定义
    ├── serve.go         # serve子命令
    ├── migrate.go       # migrate子命令
    └── version.go       # version子命令

internal/profile/
└── profile.go           # 配置结构定义
```

## 3. 核心文件设计

### 3.1 入口文件 (cmd/server/main.go)

```go
package main

import (
    "github.com/dnf-go-server/cmd/server/cmd"
)

func main() {
    cmd.Execute()
}
```

### 3.2 根命令 (cmd/server/cmd/root.go)

```go
package cmd

import (
    "fmt"
    "os"

    "github.com/spf13/cobra"
    "github.com/spf13/viper"
    
    "github.com/dnf-go-server/internal/profile"
)

var (
    cfgFile string
    prof    *profile.Profile
)

// rootCmd 代表基础命令
cmd := &cobra.Command{
    Use:   "dnf-server",
    Short: "DNF Go Game Server",
    Long: `DNF Go Server - A high-performance game server written in Go.

This server provides both TCP game connections and HTTP API endpoints
for the DNF (Dungeon & Fighter) game.`,
    PersistentPreRunE: func(cmd *cobra.Command, args []string) error {
        // 加载配置
        if err := initConfig(); err != nil {
            return err
        }
        return nil
    },
}

func Execute() {
    if err := rootCmd.Execute(); err != nil {
        fmt.Fprintln(os.Stderr, err)
        os.Exit(1)
    }
}

func init() {
    // 全局flags
    rootCmd.PersistentFlags().StringVar(&cfgFile, "config", "", "config file (default is ./configs/config.yaml)")
    rootCmd.PersistentFlags().StringP("driver", "d", "mysql", "database driver (mysql, sqlite, postgres)")
    rootCmd.PersistentFlags().StringP("dsn", "", "", "database DSN")
    
    // 绑定到viper
    viper.BindPFlag("driver", rootCmd.PersistentFlags().Lookup("driver"))
    viper.BindPFlag("dsn", rootCmd.PersistentFlags().Lookup("dsn"))
}

func initConfig() error {
    if cfgFile != "" {
        // 使用指定的配置文件
        viper.SetConfigFile(cfgFile)
    } else {
        // 搜索配置文件
        viper.AddConfigPath(".")
        viper.AddConfigPath("./configs")
        viper.AddConfigPath("/etc/dnf-server")
        viper.SetConfigName("config")
        viper.SetConfigType("yaml")
    }

    // 读取环境变量
    viper.SetEnvPrefix("DNF")
    viper.AutomaticEnv()

    // 读取配置文件
    if err := viper.ReadInConfig(); err != nil {
        if _, ok := err.(viper.ConfigFileNotFoundError); !ok {
            return fmt.Errorf("failed to read config: %w", err)
        }
        // 配置文件不存在，使用默认值
        fmt.Fprintln(os.Stderr, "Warning: config file not found, using defaults")
    }

    // 解析配置到结构体
    prof = &profile.Profile{}
    if err := viper.Unmarshal(prof); err != nil {
        return fmt.Errorf("failed to unmarshal config: %w", err)
    }

    // 设置默认值
    prof.SetDefaults()

    return nil
}
```

### 3.3 serve子命令 (cmd/server/cmd/serve.go)

```go
package cmd

import (
    "context"
    "fmt"
    "os"
    "os/signal"
    "syscall"
    "time"

    "github.com/spf13/cobra"

    "github.com/dnf-go-server/internal/utils/logger"
    "github.com/dnf-go-server/server"
    "github.com/dnf-go-server/store"
    "github.com/dnf-go-server/store/db"
)

// serveCmd 启动服务器
cmd := &cobra.Command{
    Use:   "serve",
    Short: "Start the DNF game server",
    Long:  `Start the DNF game server with TCP and HTTP endpoints.`,
    RunE: func(cmd *cobra.Command, args []string) error {
        return runServe()
    },
}

func init() {
    rootCmd.AddCommand(serveCmd)
    
    // serve命令特有flags
    serveCmd.Flags().IntP("port", "p", 8081, "server port")
    serveCmd.Flags().StringP("host", "", "0.0.0.0", "server host")
    serveCmd.Flags().Bool("demo", false, "demo mode with extra logging")
    
    viper.BindPFlag("port", serveCmd.Flags().Lookup("port"))
    viper.BindPFlag("host", serveCmd.Flags().Lookup("host"))
    viper.BindPFlag("demo", serveCmd.Flags().Lookup("demo"))
}

func runServe() error {
    ctx := context.Background()

    // 1. 初始化日志
    if err := logger.Init(prof.Log.Level, prof.Log.IsDevelopment); err != nil {
        return fmt.Errorf("failed to init logger: %w", err)
    }
    defer logger.Sync()

    logger.Info("starting DNF Go Server",
        logger.String("version", prof.Version),
        logger.String("driver", prof.Driver),
    )

    // 2. 创建数据库驱动
    driver, err := db.NewDBDriver(prof)
    if err != nil {
        return fmt.Errorf("failed to create database driver: %w", err)
    }

    // 3. 创建Store
    s := store.New(driver, prof)
    defer s.Close()

    // 4. 测试数据库连接
    if err := driver.GetDB().Ping(); err != nil {
        return fmt.Errorf("failed to ping database: %w", err)
    }
    logger.Info("database connected")

    // 5. 创建并启动服务器
    srv, err := server.NewServer(ctx, prof, s)
    if err != nil {
        return fmt.Errorf("failed to create server: %w", err)
    }

    if err := srv.Start(ctx); err != nil {
        return fmt.Errorf("failed to start server: %w", err)
    }

    // 6. 等待退出信号
    quit := make(chan os.Signal, 1)
    signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)

    logger.Info("server started successfully, waiting for connections...",
        logger.String("address", fmt.Sprintf("%s:%d", prof.Addr, prof.Port)),
    )

    <-quit
    logger.Info("shutting down server...")

    // 7. 优雅关闭
    shutdownCtx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
    defer cancel()

    if err := srv.Shutdown(shutdownCtx); err != nil {
        logger.Error("server shutdown error", logger.ErrorField(err))
        return err
    }

    logger.Info("server stopped")
    return nil
}
```

### 3.4 migrate子命令 (cmd/server/cmd/migrate.go)

```go
package cmd

import (
    "context"
    "fmt"

    "github.com/spf13/cobra"

    "github.com/dnf-go-server/internal/utils/logger"
    "github.com/dnf-go-server/store"
    "github.com/dnf-go-server/store/db"
)

// migrateCmd 数据库迁移
cmd := &cobra.Command{
    Use:   "migrate",
    Short: "Run database migrations",
    Long:  `Run database migrations to create or update the database schema.`,
    RunE: func(cmd *cobra.Command, args []string) error {
        return runMigrate()
    },
}

var (
    migrateDryRun bool
    migrateSeed   bool
)

func init() {
    rootCmd.AddCommand(migrateCmd)
    
    migrateCmd.Flags().BoolVar(&migrateDryRun, "dry-run", false, "print migrations without executing")
    migrateCmd.Flags().BoolVar(&migrateSeed, "seed", false, "seed demo data after migration")
}

func runMigrate() error {
    ctx := context.Background()

    // 初始化日志
    if err := logger.Init("info", false); err != nil {
        return err
    }
    defer logger.Sync()

    // 创建数据库驱动
    driver, err := db.NewDBDriver(prof)
    if err != nil {
        return fmt.Errorf("failed to create driver: %w", err)
    }

    // 创建Store
    s := store.New(driver, prof)
    defer s.Close()

    // 执行迁移
    logger.Info("running database migrations...")
    
    if migrateDryRun {
        logger.Info("dry-run mode: showing migrations without executing")
        // 列出待执行的迁移
        if err := s.ListPendingMigrations(ctx); err != nil {
            return err
        }
        return nil
    }

    if err := s.Migrate(ctx); err != nil {
        return fmt.Errorf("migration failed: %w", err)
    }

    logger.Info("migrations completed successfully")

    // 可选：种子数据
    if migrateSeed {
        logger.Info("seeding demo data...")
        if err := s.Seed(ctx); err != nil {
            return fmt.Errorf("seeding failed: %w", err)
        }
        logger.Info("seeding completed")
    }

    return nil
}
```

### 3.5 version子命令 (cmd/server/cmd/version.go)

```go
package cmd

import (
    "fmt"
    "runtime"

    "github.com/spf13/cobra"
)

var (
    // 这些变量在构建时通过ldflags注入
    Version   = "dev"
    Commit    = "unknown"
    BuildTime = "unknown"
)

// versionCmd 显示版本信息
cmd := &cobra.Command{
    Use:   "version",
    Short: "Print version information",
    Long:  `Print detailed version information about the DNF server.`,
    Run: func(cmd *cobra.Command, args []string) {
        fmt.Printf("DNF Go Server\n")
        fmt.Printf("  Version:    %s\n", Version)
        fmt.Printf("  Commit:     %s\n", Commit)
        fmt.Printf("  Build Time: %s\n", BuildTime)
        fmt.Printf("  Go Version: %s\n", runtime.Version())
        fmt.Printf("  OS/Arch:    %s/%s\n", runtime.GOOS, runtime.GOARCH)
    },
}

func init() {
    rootCmd.AddCommand(versionCmd)
}
```

## 4. 配置结构 (internal/profile/profile.go)

```go
package profile

import (
    "fmt"
    "os"
    "path/filepath"
)

// Profile 服务器配置
type Profile struct {
    // 基础配置
    Version string `mapstructure:"version"`
    Mode    string `mapstructure:"mode"` // dev, prod, demo
    
    // 服务器配置
    Addr string `mapstructure:"addr"`
    Port int    `mapstructure:"port"`
    
    // 数据库配置
    Driver string `mapstructure:"driver"` // mysql, sqlite, postgres
    DSN    string `mapstructure:"dsn"`
    
    // 日志配置
    Log LogConfig `mapstructure:"log"`
    
    // 游戏配置
    Game GameConfig `mapstructure:"game"`
    
    // 数据目录
    Data string `mapstructure:"data"`
}

// LogConfig 日志配置
type LogConfig struct {
    Level         string `mapstructure:"level"`          // debug, info, warn, error
    IsDevelopment bool   `mapstructure:"is_development"`
    Output        string `mapstructure:"output"`         // stdout, file
    FilePath      string `mapstructure:"file_path"`
}

// GameConfig 游戏配置
type GameConfig struct {
    MaxConnections int   `mapstructure:"max_connections"`
    IdleTimeout    int   `mapstructure:"idle_timeout"`    // 秒
    
    // 疲劳值配置
    DailyMaxFatigue int  `mapstructure:"daily_max_fatigue"`
    FatigueRecovery int  `mapstructure:"fatigue_recovery"` // 每小时恢复
    
    // 经验配置
    ExpMultiplier   float64 `mapstructure:"exp_multiplier"`
    GoldMultiplier  float64 `mapstructure:"gold_multiplier"`
}

// SetDefaults 设置默认值
func (p *Profile) SetDefaults() {
    if p.Version == "" {
        p.Version = "dev"
    }
    if p.Mode == "" {
        p.Mode = "dev"
    }
    if p.Addr == "" {
        p.Addr = "0.0.0.0"
    }
    if p.Port == 0 {
        p.Port = 8081
    }
    if p.Driver == "" {
        p.Driver = "mysql"
    }
    if p.Data == "" {
        p.Data = "./data"
    }
    
    // 日志默认值
    if p.Log.Level == "" {
        p.Log.Level = "info"
    }
    if p.Log.Output == "" {
        p.Log.Output = "stdout"
    }
    
    // 游戏默认值
    if p.Game.MaxConnections == 0 {
        p.Game.MaxConnections = 10000
    }
    if p.Game.IdleTimeout == 0 {
        p.Game.IdleTimeout = 300 // 5分钟
    }
    if p.Game.DailyMaxFatigue == 0 {
        p.Game.DailyMaxFatigue = 100
    }
}

// IsDev 检查是否为开发模式
func (p *Profile) IsDev() bool {
    return p.Mode == "dev" || p.Mode == "development"
}

// IsProd 检查是否为生产模式
func (p *Profile) IsProd() bool {
    return p.Mode == "prod" || p.Mode == "production"
}

// IsDemo 检查是否为演示模式
func (p *Profile) IsDemo() bool {
    return p.Mode == "demo"
}

// Validate 验证配置
func (p *Profile) Validate() error {
    // 验证驱动
    validDrivers := []string{"mysql", "sqlite", "postgres"}
    found := false
    for _, d := range validDrivers {
        if p.Driver == d {
            found = true
            break
        }
    }
    if !found {
        return fmt.Errorf("invalid driver: %s (must be one of: %v)", p.Driver, validDrivers)
    }
    
    // 验证端口
    if p.Port <= 0 || p.Port > 65535 {
        return fmt.Errorf("invalid port: %d", p.Port)
    }
    
    // 验证数据目录
    if p.Data != "" {
        if err := os.MkdirAll(p.Data, 0755); err != nil {
            return fmt.Errorf("failed to create data directory: %w", err)
        }
    }
    
    return nil
}

// GetDSN 获取数据库DSN
func (p *Profile) GetDSN() string {
    if p.DSN != "" {
        return p.DSN
    }
    
    // 根据驱动生成默认DSN
    switch p.Driver {
    case "sqlite":
        return filepath.Join(p.Data, "dnf.db")
    case "mysql":
        return "user:password@tcp(localhost:3306)/dnf?charset=utf8mb4&parseTime=True&loc=Local"
    case "postgres":
        return "postgres://user:password@localhost:5432/dnf?sslmode=disable"
    default:
        return ""
    }
}
```

## 5. 配置文件示例

### 5.1 开发环境 (configs/config.yaml)

```yaml
# DNF Go Server 配置

version: "1.0.0"
mode: dev

# 服务器配置
addr: "0.0.0.0"
port: 8081

# 数据库配置
driver: sqlite
dsn: "./data/dnf.db"

# 日志配置
log:
  level: debug
  is_development: true
  output: stdout

# 游戏配置
game:
  max_connections: 1000
  idle_timeout: 300
  daily_max_fatigue: 100
  fatigue_recovery: 10
  exp_multiplier: 2.0
  gold_multiplier: 2.0

# 数据目录
data: "./data"
```

### 5.2 生产环境 (configs/config.prod.yaml)

```yaml
# DNF Go Server 生产环境配置

version: "1.0.0"
mode: prod

# 服务器配置
addr: "0.0.0.0"
port: 8081

# 数据库配置
driver: mysql
dsn: "${DNF_DSN}"

# 日志配置
log:
  level: info
  is_development: false
  output: file
  file_path: "/var/log/dnf-server/server.log"

# 游戏配置
game:
  max_connections: 10000
  idle_timeout: 300
  daily_max_fatigue: 100
  fatigue_recovery: 10
  exp_multiplier: 1.0
  gold_multiplier: 1.0

# 数据目录
data: "/var/lib/dnf-server"
```

### 5.3 Docker环境 (configs/config.docker.yaml)

```yaml
version: "1.0.0"
mode: prod

addr: "0.0.0.0"
port: 8081

driver: mysql
dsn: "root:${MYSQL_ROOT_PASSWORD}@tcp(mysql:3306)/dnf?charset=utf8mb4&parseTime=True&loc=Local"

log:
  level: info
  is_development: false
  output: stdout

game:
  max_connections: 5000
  idle_timeout: 300
  daily_max_fatigue: 100
  fatigue_recovery: 10

 data: "/data"
```

## 6. 使用示例

### 6.1 启动服务器

```bash
# 使用默认配置
dnf-server serve

# 指定配置文件
dnf-server serve --config ./configs/config.prod.yaml

# 指定参数
dnf-server serve --port 9090 --driver sqlite --dsn ./data/game.db

# 演示模式
dnf-server serve --demo
```

### 6.2 数据库迁移

```bash
# 执行迁移
dnf-server migrate

# 查看待执行的迁移（不执行）
dnf-server migrate --dry-run

# 迁移并添加演示数据
dnf-server migrate --seed

# 使用指定配置
dnf-server migrate --config ./configs/config.prod.yaml
```

### 6.3 查看版本

```bash
# 显示版本信息
dnf-server version
```

### 6.4 帮助信息

```bash
# 显示帮助
dnf-server --help

# 显示子命令帮助
dnf-server serve --help
dnf-server migrate --help
```

## 7. 环境变量支持

```bash
# 使用环境变量覆盖配置
export DNF_DRIVER=mysql
export DNF_DSN="user:pass@tcp(localhost:3306)/dnf"
export DNF_PORT=9090
export DNF_MODE=prod
export DNF_LOG_LEVEL=debug

# 然后启动服务器
dnf-server serve
```

## 8. 构建脚本

### 8.1 Makefile

```makefile
.PHONY: build build-linux build-windows build-mac clean test run

# 变量
BINARY_NAME=dnf-server
VERSION=$(shell git describe --tags --always --dirty)
COMMIT=$(shell git rev-parse --short HEAD)
BUILD_TIME=$(shell date -u '+%Y-%m-%d_%H:%M:%S')
LDFLAGS=-ldflags "-X github.com/dnf-go-server/cmd/server/cmd.Version=${VERSION} \
                  -X github.com/dnf-go-server/cmd/server/cmd.Commit=${COMMIT} \
                  -X github.com/dnf-go-server/cmd/server/cmd.BuildTime=${BUILD_TIME}"

# 构建
build:
	go build ${LDFLAGS} -o bin/${BINARY_NAME} ./cmd/server

# 交叉编译
build-linux:
	GOOS=linux GOARCH=amd64 go build ${LDFLAGS} -o bin/${BINARY_NAME}-linux-amd64 ./cmd/server

build-windows:
	GOOS=windows GOARCH=amd64 go build ${LDFLAGS} -o bin/${BINARY_NAME}-windows-amd64.exe ./cmd/server

build-mac:
	GOOS=darwin GOARCH=amd64 go build ${LDFLAGS} -o bin/${BINARY_NAME}-darwin-amd64 ./cmd/server

# 测试
test:
	go test -v ./...

# 运行
dev:
	go run ./cmd/server serve --config ./configs/config.yaml

# 迁移
migrate:
	go run ./cmd/server migrate --config ./configs/config.yaml

# 清理
clean:
	rm -rf bin/
	go clean
```

### 8.2 构建脚本 (scripts/build.sh)

```bash
#!/bin/bash

set -e

VERSION=$(git describe --tags --always --dirty 2>/dev/null || echo "dev")
COMMIT=$(git rev-parse --short HEAD 2>/dev/null || echo "unknown")
BUILD_TIME=$(date -u '+%Y-%m-%d_%H:%M:%S')

echo "Building DNF Server..."
echo "Version: ${VERSION}"
echo "Commit: ${COMMIT}"
echo "Build Time: ${BUILD_TIME}"

LDFLAGS="-X github.com/dnf-go-server/cmd/server/cmd.Version=${VERSION} \
         -X github.com/dnf-go-server/cmd/server/cmd.Commit=${COMMIT} \
         -X github.com/dnf-go-server/cmd/server/cmd.BuildTime=${BUILD_TIME}"

# 创建输出目录
mkdir -p bin

# 构建
go build -ldflags "${LDFLAGS}" -o bin/dnf-server ./cmd/server

echo "Build complete: bin/dnf-server"

# 显示版本
./bin/dnf-server version
```

## 9. 优雅关闭流程

```
1. 接收信号 (SIGINT/SIGTERM)
   ↓
2. 停止接受新连接
   ↓
3. 等待现有请求完成 (10秒超时)
   ↓
4. 关闭gRPC服务器
   ↓
5. 关闭Echo服务器
   ↓
6. 关闭Store (关闭缓存和数据库连接)
   ↓
7. 同步日志
   ↓
8. 退出程序
```

## 10. 扩展性考虑

### 10.1 添加新子命令

```go
// cmd/server/cmd/backup.go
package cmd

import "github.com/spf13/cobra"

var backupCmd = &cobra.Command{
    Use:   "backup",
    Short: "Backup database",
    RunE: func(cmd *cobra.Command, args []string) error {
        // 实现备份逻辑
        return nil
    },
}

func init() {
    rootCmd.AddCommand(backupCmd)
    backupCmd.Flags().StringP("output", "o", "./backups", "backup output directory")
}
```

### 10.2 配置文件热加载

```go
// 在root.go中添加
viper.WatchConfig()
viper.OnConfigChange(func(e fsnotify.Event) {
    logger.Info("config file changed", logger.String("file", e.Name))
    // 重新加载配置
    if err := viper.Unmarshal(prof); err != nil {
        logger.Error("failed to reload config", logger.ErrorField(err))
    }
})
```

---

**创建日期**: 2026-02-09
**版本**: v1.0
**状态**: 设计完成，待实现
