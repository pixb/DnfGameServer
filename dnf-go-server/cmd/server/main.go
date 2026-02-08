package main

import (
	"context"
	"flag"
	"os"
	"os/signal"
	"syscall"
	"time"

	"dnf-go-server/internal/db"
	"dnf-go-server/internal/game"
	"dnf-go-server/internal/game/handlers"
	"dnf-go-server/internal/network"
	httpserver "dnf-go-server/internal/server/http"
	"dnf-go-server/internal/utils/config"
	"dnf-go-server/internal/utils/logger"
)

var (
	configPath string
)

func init() {
	flag.StringVar(&configPath, "config", "", "配置文件路径")
}

func main() {
	flag.Parse()

	// 1. 加载配置
	cfg, err := config.Load(configPath)
	if err != nil {
		logger.Fatalf("failed to load config: %v", err)
	}

	// 2. 初始化日志
	if err := logger.Init(cfg.Log.Level, cfg.Log.IsDevelopment); err != nil {
		logger.Fatalf("failed to init logger: %v", err)
	}
	defer logger.Sync()

	logger.Info("starting DNF Go Server...",
		logger.String("version", "1.0.0"),
		logger.String("log_level", cfg.Log.Level),
	)

	// 3. 初始化数据库
	database, err := db.NewDB(&cfg.Database)
	if err != nil {
		logger.Fatalf("failed to connect database: %v", err)
	}
	defer database.Close()

	// 4. 测试数据库连接
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	if err := database.Ping(ctx); err != nil {
		logger.Fatalf("failed to ping database: %v", err)
	}
	logger.Info("database connection established")

	// 5. 初始化玩家服务
	playerService := game.NewPlayerService(database)
	if err := playerService.LoadAllPlayerProfiles(); err != nil {
		logger.Fatalf("failed to load player profiles: %v", err)
	}

	// 6. 启动HTTP服务器
	httpServer := httpserver.NewServer(cfg)
	httpServer.SetPlayerService(playerService)

	go func() {
		if err := httpServer.Start(); err != nil {
			logger.Fatalf("HTTP server error: %v", err)
		}
	}()

	logger.Info("HTTP server started",
		logger.String("host", cfg.Server.HTTP.Host),
		logger.Int("port", cfg.Server.HTTP.Port),
	)

	// 7. 初始化TCP服务器
	// 7.1 创建Proto编解码器
	codec := network.NewProtoCodec()
	codec.RegisterAllMessages() // 注册所有protobuf消息类型

	// 7.2 设置全局编码器(用于WriteResponse)
	network.SetEncoderInstance(codec)

	// 7.3 创建消息分发器
	dispatcher := network.DefaultDispatcher()

	// 7.4 注册所有消息处理器
	handlers.RegisterAllHandlers(dispatcher)

	logger.Info("message handlers registered",
		logger.Int("handler_count", dispatcher.GetHandlerCount()),
	)

	// 7.5 创建TCP服务器
	tcpConfig := &network.ServerConfig{
		Host:            cfg.Server.TCP.Host,
		Port:            cfg.Server.TCP.Port,
		Backlog:         3000,
		ReadBufferSize:  2048,
		WriteBufferSize: 2048,
		MaxConnections:  10000,
		IdleTimeout:     30 * time.Second,
	}

	// 创建连接处理器
	tcpHandler := &DefaultConnectionHandler{}
	tcpServer := network.NewTCPServer(tcpConfig, tcpHandler)

	// 设置编解码器和分发器
	tcpServer.SetCodec(codec)
	tcpServer.SetDispatcher(dispatcher)

	// 7.6 启动TCP服务器
	go func() {
		if err := tcpServer.Start(); err != nil {
			logger.Fatalf("TCP server error: %v", err)
		}
	}()

	logger.Info("TCP server started",
		logger.String("host", cfg.Server.TCP.Host),
		logger.Int("port", cfg.Server.TCP.Port),
	)

	// 8. 等待退出信号
	quit := make(chan os.Signal, 1)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)

	logger.Info("server started successfully, waiting for connections...")
	<-quit

	logger.Info("shutting down server...")

	// 优雅关闭
	shutdownCtx, shutdownCancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer shutdownCancel()

	// 关闭TCP服务器
	if err := tcpServer.Stop(); err != nil {
		logger.Error("TCP server shutdown error", logger.ErrorField(err))
	}

	// 关闭HTTP服务器
	if err := httpServer.Stop(shutdownCtx); err != nil {
		logger.Error("HTTP server shutdown error", logger.ErrorField(err))
	}

	logger.Info("server stopped")
}

// DefaultConnectionHandler 默认连接处理器
type DefaultConnectionHandler struct{}

// OnSessionCreated 会话创建时触发
func (h *DefaultConnectionHandler) OnSessionCreated(session *network.Session) {
	logger.Info("session created",
		logger.Int64("session_id", session.ID()),
		logger.String("remote_addr", session.RemoteAddr()),
	)
}

// OnSessionOpened 会话打开时触发
func (h *DefaultConnectionHandler) OnSessionOpened(session *network.Session) {
	logger.Info("session opened",
		logger.Int64("session_id", session.ID()),
	)
}

// OnMessageReceived 收到消息时触发
func (h *DefaultConnectionHandler) OnMessageReceived(session *network.Session, message interface{}) {
	// 消息已由分发器处理，这里不需要额外操作
}

// OnSessionClosed 会话关闭时触发
func (h *DefaultConnectionHandler) OnSessionClosed(session *network.Session) {
	logger.Info("session closed",
		logger.Int64("session_id", session.ID()),
	)
}

// OnExceptionCaught 发生异常时触发
func (h *DefaultConnectionHandler) OnExceptionCaught(session *network.Session, err error) {
	logger.Error("session exception",
		logger.Int64("session_id", session.ID()),
		logger.ErrorField(err),
	)
}
