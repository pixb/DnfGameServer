package server

import (
	"context"
	"fmt"
	"net"
	"net/http"
	"sync"
	"time"

	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"github.com/soheilhy/cmux"
	"google.golang.org/grpc"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/profile"
	v1 "github.com/pixb/DnfGameServer/dnf-go-server/server/router/api/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// Server 游戏服务器
type Server struct {
	Profile *profile.Profile
	Store   *store.Store
	Secret  string

	// 服务器组件
	echoServer   *echo.Echo
	grpcServer   *grpc.Server
	tcpServer    *network.TCPServer
	apiV1Service *v1.APIV1Service

	// 生命周期管理
	wg       sync.WaitGroup
	listener net.Listener
}

// NewServer 创建服务器实例
func NewServer(ctx context.Context, prof *profile.Profile, s *store.Store) (*Server, error) {
	server := &Server{
		Profile: prof,
		Store:   s,
	}

	// 1. 初始化Echo服务器
	echoServer := echo.New()
	echoServer.Debug = prof.Mode == "dev"
	echoServer.HideBanner = true
	echoServer.Use(middleware.Recover())
	echoServer.Use(middleware.Logger())
	echoServer.Use(middleware.CORS())
	server.echoServer = echoServer

	// 2. 健康检查端点
	echoServer.GET("/healthz", func(c echo.Context) error {
		return c.String(http.StatusOK, "OK\n")
	})

	// 3. 获取或创建Secret
	secret, err := server.getOrCreateSecret(ctx)
	if err != nil {
		return nil, fmt.Errorf("failed to get secret: %w", err)
	}
	server.Secret = secret

	// 4. 创建API v1服务
	server.apiV1Service = v1.NewAPIV1Service(server.Secret, prof, s)

	// 5. 创建gRPC服务器
	server.grpcServer = grpc.NewServer()

	// 注册所有gRPC服务
	server.apiV1Service.RegisterGRPCServices(server.grpcServer)

	// 6. 创建TCP服务器
	tcpConfig := network.DefaultServerConfig()
	// 使用与HTTP/gRPC不同的端口，避免冲突
	tcpConfig.Port = 9000

	// 创建一个基本的连接处理器
	tcpHandler := &BasicTCPHandler{}

	server.tcpServer = network.NewTCPServer(tcpConfig, tcpHandler)

	// 设置默认的二进制编解码器
	server.tcpServer.SetCodec(&network.BinaryCodec{LengthFieldSize: 2})

	return server, nil
}

// BasicTCPHandler 基本的TCP连接处理器
type BasicTCPHandler struct{}

// OnSessionCreated 会话创建时调用
func (h *BasicTCPHandler) OnSessionCreated(session *network.Session) {
	// 会话创建时的处理逻辑
}

// OnSessionOpened 会话打开时调用
func (h *BasicTCPHandler) OnSessionOpened(session *network.Session) {
	// 会话打开时的处理逻辑
}

// OnMessageReceived 收到消息时调用
func (h *BasicTCPHandler) OnMessageReceived(session *network.Session, message interface{}) {
	// 收到消息时的处理逻辑
}

// OnSessionClosed 会话关闭时调用
func (h *BasicTCPHandler) OnSessionClosed(session *network.Session) {
	// 会话关闭时的处理逻辑
}

// OnExceptionCaught 捕获到异常时调用
func (h *BasicTCPHandler) OnExceptionCaught(session *network.Session, err error) {
	// 捕获到异常时的处理逻辑
}

// Start 启动服务器
func (s *Server) Start(ctx context.Context) error {
	address := fmt.Sprintf("0.0.0.0:%d", s.Profile.Port)

	// 1. 创建TCP监听器
	listener, err := net.Listen("tcp", address)
	if err != nil {
		return fmt.Errorf("failed to listen: %w", err)
	}
	s.listener = listener

	// 2. 创建cmux多路复用器
	m := cmux.New(listener)
	httpListener := m.Match(cmux.HTTP1Fast()) // HTTP/1.1
	grpcListener := m.Match(cmux.HTTP2())     // HTTP/2

	// 3. 启动gRPC服务器 (HTTP/2)
	s.wg.Add(1)
	go func() {
		defer s.wg.Done()
		s.echoServer.Logger.Info("gRPC server starting on ", address)
		if err := s.grpcServer.Serve(grpcListener); err != nil {
			s.echoServer.Logger.Error("gRPC server error: ", err)
		}
	}()

	// 4. 注册网关处理器 (HTTP/1.1)
	if err := s.apiV1Service.RegisterGateway(ctx, s.echoServer); err != nil {
		return fmt.Errorf("failed to register gateway: %w", err)
	}

	// 5. 启动Echo服务器 (HTTP/1.1)
	s.echoServer.Listener = httpListener
	s.wg.Add(1)
	go func() {
		defer s.wg.Done()
		s.echoServer.Logger.Info("HTTP server starting on ", address)
		if err := s.echoServer.Start(address); err != nil && err != http.ErrServerClosed {
			s.echoServer.Logger.Error("HTTP server error: ", err)
		}
	}()

	// 6. 启动cmux
	s.wg.Add(1)
	go func() {
		defer s.wg.Done()
		s.echoServer.Logger.Info("cmux started on ", address)
		if err := m.Serve(); err != nil {
			s.echoServer.Logger.Error("cmux error: ", err)
		}
	}()

	// 7. 启动TCP服务器
	s.wg.Add(1)
	go func() {
		defer s.wg.Done()
		s.echoServer.Logger.Info("TCP server starting on port 9000")
		if err := s.tcpServer.Start(); err != nil {
			s.echoServer.Logger.Error("TCP server error: ", err)
		}
	}()

	s.echoServer.Logger.Info("Server started successfully")
	return nil
}

// Shutdown 优雅关闭
func (s *Server) Shutdown(ctx context.Context) error {
	s.echoServer.Logger.Info("Shutting down server...")

	// 1. 关闭gRPC服务器
	if s.grpcServer != nil {
		s.grpcServer.GracefulStop()
	}

	// 2. 关闭Echo服务器
	shutdownCtx, cancel := context.WithTimeout(ctx, 10*time.Second)
	defer cancel()
	if err := s.echoServer.Shutdown(shutdownCtx); err != nil {
		return err
	}

	// 3. 关闭TCP服务器
	if s.tcpServer != nil {
		if err := s.tcpServer.Stop(); err != nil {
			s.echoServer.Logger.Error("Error stopping TCP server: ", err)
		}
	}

	// 4. 关闭Store
	if err := s.Store.Close(); err != nil {
		return err
	}

	// 5. 等待所有goroutine
	s.wg.Wait()
	return nil
}

func (s *Server) getOrCreateSecret(ctx context.Context) (string, error) {
	// 尝试从数据库获取
	setting, err := s.Store.GetInstanceBasicSetting(ctx)
	if err == nil && setting.SecretKey != "" {
		return setting.SecretKey, nil
	}

	// 生成新Secret
	secret := generateRandomSecret()

	// 保存到数据库
	// 注意: 这里简化处理，实际需要UpsertInstanceSetting

	return secret, nil
}

func generateRandomSecret() string {
	// 简化实现，实际应该生成随机字符串
	return "dnf-go-server-secret-key-change-in-production"
}
