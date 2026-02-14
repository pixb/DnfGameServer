package cmd

import (
	"context"
	"fmt"
	"os"
	"os/signal"
	"syscall"

	"github.com/spf13/cobra"
	"github.com/spf13/viper"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/handlers"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/party_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/server"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"github.com/pixb/DnfGameServer/dnf-go-server/store/db"
)

// serveCmd represents the serve command
var serveCmd = &cobra.Command{
	Use:   "serve",
	Short: "Start the game server",
	Long: `Start the DNF game server.

This command starts both the HTTP API server and TCP game server.
The server will listen on the configured port (default: 8081).`,
	RunE: runServe,
}

func init() {
	rootCmd.AddCommand(serveCmd)

	// serve command specific flags
	serveCmd.Flags().IntP("port", "p", 8081, "server port")
	viper.BindPFlag("port", serveCmd.Flags().Lookup("port"))
}

func runServe(cmd *cobra.Command, args []string) error {
	ctx := context.Background()

	// 获取配置
	prof := GetProfile()
	if prof == nil {
		return fmt.Errorf("profile not initialized")
	}

	// 设置默认值
	if prof.Port == 0 {
		prof.Port = 8081
	}

	fmt.Printf("Starting DNF Go Server...\n")
	fmt.Printf("Driver: %s\n", prof.Driver)
	fmt.Printf("Mode: %s\n", prof.Mode)
	fmt.Printf("Port: %d\n", prof.Port)

	// 检查DSN
	if prof.DSN == "" {
		return fmt.Errorf("database DSN not configured. Use --dsn flag or DATABASE_DSN env var")
	}

	// 1. 创建数据库驱动
	fmt.Println("Initializing database...")
	driver, err := db.NewDBDriver(prof)
	if err != nil {
		return fmt.Errorf("failed to create database driver: %w", err)
	}

	// 2. 创建Store
	s := store.New(driver, prof)

	// 3. 执行数据库迁移
	fmt.Println("Running database migrations...")
	if err := s.Migrate(ctx); err != nil {
		return fmt.Errorf("failed to migrate database: %w", err)
	}
	fmt.Println("Database migrations completed")

	// 3.5 初始化测试数据
	if prof.Mode == "dev" {
		fmt.Println("Seeding test data...")
		if err := s.Seed(ctx); err != nil {
			fmt.Printf("Warning: failed to seed test data: %v\n", err)
		} else {
			fmt.Println("Test data seeded successfully")
		}
	}

	// 3.6 初始化服务
	fmt.Println("Initializing services...")
	partySvc := party_service.NewPartyService(s)
	handlers.InitPartyService(partySvc)
	fmt.Println("Services initialized successfully")

	// 4. 创建服务器
	fmt.Println("Creating server...")
	srv, err := server.NewServer(ctx, prof, s)
	if err != nil {
		return fmt.Errorf("failed to create server: %w", err)
	}

	// 5. 启动服务器
	fmt.Println("Starting server...")
	go func() {
		if err := srv.Start(ctx); err != nil {
			fmt.Fprintf(os.Stderr, "Server error: %v\n", err)
			os.Exit(1)
		}
	}()

	fmt.Printf("Server started successfully on port %d\n", prof.Port)
	fmt.Println("Press Ctrl+C to stop")

	// 6. 等待退出信号
	quit := make(chan os.Signal, 1)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)
	<-quit

	fmt.Println("\nShutting down server...")
	if err := srv.Shutdown(ctx); err != nil {
		return fmt.Errorf("failed to shutdown server: %w", err)
	}

	fmt.Println("Server stopped")
	return nil
}
