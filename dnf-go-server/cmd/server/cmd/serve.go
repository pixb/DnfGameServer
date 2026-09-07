package cmd

import (
	"context"
	"fmt"
	"os"
	"os/signal"
	"syscall"
	"time"

	"github.com/spf13/cobra"
	"github.com/spf13/viper"

	"errors"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/achievement_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/adventure_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/handlers"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/party_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/pk_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/server"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"github.com/pixb/DnfGameServer/dnf-go-server/store/db"
)

// ensureAdminAccounts 确保初始管理员账号存在且 authority=1(2026-09-06 第四十一轮)
// 不存在则自动创建; 存在则提升为管理员并确保启用(status=1)
func ensureAdminAccounts(ctx context.Context, s *store.Store, adminOpenIDs []string) error {
	if len(adminOpenIDs) == 0 {
		return nil
	}
	adminAuthority := int32(1)
	statusNormal := int32(1)
	for _, openid := range adminOpenIDs {
		acct, err := s.GetAccount(ctx, &store.FindAccount{OpenID: &openid})
		if errors.Is(err, store.ErrNotFound) {
			if _, cerr := s.CreateAccount(ctx, &store.Account{
				OpenID:     openid,
				AccountKey: "",
				AuthKey:    "",
				Authority:  adminAuthority,
				Status:     statusNormal,
			}); cerr != nil {
				return fmt.Errorf("failed to create admin account %s: %w", openid, cerr)
			}
			fmt.Printf("Admin account created: %s (authority=1)\n", openid)
			continue
		}
		if err != nil {
			return fmt.Errorf("failed to get admin account %s: %w", openid, err)
		}
		if acct == nil || acct.Authority != 1 || acct.Status != 1 {
			if _, uerr := s.UpdateAccount(ctx, &store.UpdateAccount{
				ID:        acct.ID,
				Authority: &adminAuthority,
				Status:    &statusNormal,
			}); uerr != nil {
				return fmt.Errorf("failed to promote admin account %s: %w", openid, uerr)
			}
			fmt.Printf("Admin account ensured: %s (authority=1)\n", openid)
		}
	}
	return nil
}

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

	// 2026-09-06 第二十九轮: 邮件过期清理周期可配置
	serveCmd.Flags().String("mail-cleanup-interval", "5m", "mail expired cleanup interval (Go duration, e.g. 5m/30s)")
	viper.BindPFlag("mail_cleanup_interval", serveCmd.Flags().Lookup("mail-cleanup-interval"))

	// 2026-09-06 第四十一轮: 初始管理员账号 openid(逗号分隔, 启动时确保存在且 authority=1)
	serveCmd.Flags().String("admin-openids", "", "initial admin account openids (comma separated)")
	viper.BindPFlag("admin_openids", serveCmd.Flags().Lookup("admin-openids"))

	// 2026-09-07 第六十一轮: 拍卖到期结算扫描间隔(秒, 默认 30)
	serveCmd.Flags().Int("auction-settle-interval", 30, "auction settle loop interval (seconds)")
	viper.BindPFlag("auction_settle_interval", serveCmd.Flags().Lookup("auction-settle-interval"))
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

	// 3.55 确保初始管理员账号存在且为管理员(2026-09-06 第四十一轮)
	if err := ensureAdminAccounts(ctx, s, prof.AdminOpenIDList()); err != nil {
		fmt.Printf("Warning: failed to ensure admin accounts: %v\n", err)
	}

	// 3.6 初始化服务
	fmt.Println("Initializing services...")
	handlers.InitShopStore(s) // 2026-09-07 第五十四轮: 商店/拍卖行 store 注入
	partySvc := party_service.NewPartyService(s)
	handlers.InitPartyService(partySvc)

	achievementSvc := achievement_service.NewAchievementService(s)
	handlers.InitAchievementService(achievementSvc)

	adventureSvc := adventure_service.NewAdventureService(s)
	handlers.InitAdventureService(adventureSvc)

	// PK服务基于 store 层,MySQL/SQLite 驱动下均可用
	pkSvc := pk_service.NewPkService(s)
	handlers.InitPkService(pkSvc)

	// 背包物品 store 注入(丢弃/合成/强化/整理/分解走真实存储)
	handlers.InitItemStore(s)

	// 商店/拍卖行 store 注入(订单查询/取消/拍卖结算/手续费)
	handlers.InitShopStore(s)

	// 活动 store 注入(活动配置/进度)
	handlers.InitEventStore(s)

	// 行为日志 store 注入(rank/log TCP handler 实化, 2026-09-06 第十九轮)
	handlers.InitLogStore(s)

	// 排名 store 注入(rank TCP handler 实化, 2026-09-06 第十九轮)
	handlers.InitRankStore(s)

	// 认证 store 注入(LoginHandler 实化, 2026-09-06 第三十二轮)
	handlers.InitAuthStore(s)

	// 技能 store 注入(学习/升级实化, 2026-09-07 第四十四轮)
	handlers.InitSkillStore(s)

	fmt.Println("Services initialized successfully")

	// 3.7 后台定时结算过期拍卖(2026-09-07 第五十九轮; 第六十一轮间隔可配置): 默认 30s, --auction-settle-interval 覆盖
	settleInterval := time.Duration(viper.GetInt("auction_settle_interval")) * time.Second
	if settleInterval <= 0 {
		settleInterval = 30 * time.Second
	}
	go func() {
		ticker := time.NewTicker(settleInterval)
		defer ticker.Stop()
		for {
			select {
			case <-ctx.Done():
				return
			case <-ticker.C:
				if n, err := s.SettleExpiredAuctions(ctx); err != nil {
					fmt.Printf("Warning: auction settle loop error: %v\n", err)
				} else if n > 0 {
					fmt.Printf("Auction settle loop: %d auction(s) settled\n", n)
				}
			}
		}
	}()

	// 4. 创建服务器
	fmt.Println("Creating server...")
	srv, err := server.NewServer(ctx, prof, s, pkSvc)
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
