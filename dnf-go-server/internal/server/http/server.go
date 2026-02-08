package http

import (
	"context"
	"fmt"
	"net/http"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/config"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"

	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
)

// Server HTTP服务器
type Server struct {
	echo   *echo.Echo
	config *config.Config

	// 服务依赖
	playerService interface{}
	// TODO: 添加其他服务
}

// NewServer 创建HTTP服务器
func NewServer(cfg *config.Config) *Server {
	e := echo.New()

	// 隐藏Echo banner
	e.HideBanner = true

	// 中间件
	e.Use(middleware.Recover())
	e.Use(middleware.CORS())
	e.Use(requestLogger())

	server := &Server{
		echo:   e,
		config: cfg,
	}

	// 注册路由
	server.registerRoutes()

	return server
}

// SetPlayerService 设置玩家服务
func (s *Server) SetPlayerService(svc interface{}) {
	s.playerService = svc
}

// registerRoutes 注册路由
func (s *Server) registerRoutes() {
	// 健康检查
	s.echo.GET("/health", s.healthCheck)

	// 游戏服务器相关接口
	s.echo.GET("/scl/my/id", s.getServerID)

	// GM接口
	gm := s.echo.Group("/gm")
	{
		gm.POST("/reload", s.gmReload)
		gm.POST("/kick/:uid", s.gmKickPlayer)
		gm.GET("/online/count", s.gmGetOnlineCount)
	}

	// 支付接口
	pay := s.echo.Group("/pay")
	{
		pay.POST("/callback", s.payCallback)
	}
}

// Start 启动服务器
func (s *Server) Start() error {
	addr := fmt.Sprintf("%s:%d", s.config.Server.HTTP.Host, s.config.Server.HTTP.Port)
	logger.Info("HTTP server starting", logger.String("addr", addr))
	return s.echo.Start(addr)
}

// Stop 停止服务器
func (s *Server) Stop(ctx context.Context) error {
	logger.Info("HTTP server stopping")
	return s.echo.Shutdown(ctx)
}

// requestLogger 请求日志中间件
func requestLogger() echo.MiddlewareFunc {
	return func(next echo.HandlerFunc) echo.HandlerFunc {
		return func(c echo.Context) error {
			start := time.Now()

			err := next(c)

			latency := time.Since(start)
			req := c.Request()
			res := c.Response()

			logger.Info("HTTP request",
				logger.String("method", req.Method),
				logger.String("path", req.URL.Path),
				logger.Int("status", res.Status),
				logger.Int64("latency_ms", latency.Milliseconds()),
				logger.String("ip", c.RealIP()),
			)

			return err
		}
	}
}

// HTTPResult 通用响应结构 (替代 Java HttpResult)
type HTTPResult struct {
	Code    int         `json:"code"`
	Message string      `json:"message"`
	Data    interface{} `json:"data,omitempty"`
}

// Success 成功响应
func Success(data interface{}) *HTTPResult {
	return &HTTPResult{
		Code:    0,
		Message: "success",
		Data:    data,
	}
}

// Error 错误响应
func Error(code int, message string) *HTTPResult {
	return &HTTPResult{
		Code:    code,
		Message: message,
	}
}

// 处理器实现

func (s *Server) healthCheck(c echo.Context) error {
	return c.JSON(http.StatusOK, Success(map[string]interface{}{
		"status":    "ok",
		"timestamp": time.Now().Unix(),
	}))
}

func (s *Server) getServerID(c echo.Context) error {
	return c.JSON(http.StatusOK, Success(map[string]interface{}{
		"server_id": 1,
		"name":      "DNF-Go-Server",
	}))
}

func (s *Server) gmReload(c echo.Context) error {
	// TODO: 实现重新加载配置
	logger.Info("GM reload requested")
	return c.JSON(http.StatusOK, Success(nil))
}

func (s *Server) gmKickPlayer(c echo.Context) error {
	uid := c.Param("uid")
	logger.Info("GM kick player", logger.String("uid", uid))
	// TODO: 实现踢人逻辑
	return c.JSON(http.StatusOK, Success(nil))
}

func (s *Server) gmGetOnlineCount(c echo.Context) error {
	// TODO: 实现在线人数统计
	return c.JSON(http.StatusOK, Success(map[string]interface{}{
		"online_count": 0,
	}))
}

func (s *Server) payCallback(c echo.Context) error {
	// TODO: 实现支付回调处理
	logger.Info("pay callback received")
	return c.JSON(http.StatusOK, Success(nil))
}
