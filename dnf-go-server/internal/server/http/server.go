package http

import (
	"context"
	"fmt"
	"net/http"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/auth_service"
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
	authService   *auth_service.Service
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

// SetAuthService 设置认证服务
func (s *Server) SetAuthService(svc *auth_service.Service) {
	s.authService = svc
}

// registerRoutes 注册路由
func (s *Server) registerRoutes() {
	// 健康检查
	s.echo.GET("/health", s.healthCheck)

	// 游戏服务器相关接口
	s.echo.GET("/scl/my/id", s.getServerID)

	// 认证接口
	auth := s.echo.Group("/api/v1/auth")
	{
		auth.POST("/login", s.login)
	}

	// 角色接口
	character := s.echo.Group("/api/v1/character")
	{
		character.GET("/list", s.getCharacterList)
		character.POST("/create", s.createCharacter)
		character.POST("/select", s.selectCharacter)
		character.POST("/enter", s.enterGame)
	}

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

// login 处理登录请求
func (s *Server) login(c echo.Context) error {
	var req auth_service.LoginRequest
	if err := c.Bind(&req); err != nil {
		return c.JSON(http.StatusBadRequest, Error(400, "请求参数错误"))
	}

	if s.authService == nil {
		return c.JSON(http.StatusInternalServerError, Error(500, "认证服务未初始化"))
	}

	resp, err := s.authService.Login(&req)
	if err != nil {
		logger.Error("登录失败", logger.Error(err), logger.String("openid", req.OpenID))
		return c.JSON(http.StatusOK, resp)
	}

	logger.Info("登录成功", logger.String("openid", req.OpenID))
	return c.JSON(http.StatusOK, resp)
}

// getCharacterList 获取角色列表
func (s *Server) getCharacterList(c echo.Context) error {
	// 获取authKey
	authKey := c.QueryParam("authKey")
	if authKey == "" {
		// 尝试从Authorization header获取
		authHeader := c.Request().Header.Get("Authorization")
		if len(authHeader) > 7 && authHeader[:7] == "Bearer " {
			authKey = authHeader[7:]
		}
	}

	if authKey == "" {
		return c.JSON(http.StatusBadRequest, Error(400, "authKey不能为空"))
	}

	if s.authService == nil {
		return c.JSON(http.StatusInternalServerError, Error(500, "认证服务未初始化"))
	}

	// 验证认证密钥
	account, err := s.authService.ValidateAuth(authKey)
	if err != nil {
		return c.JSON(http.StatusUnauthorized, Error(401, "认证失败: "+err.Error()))
	}

	// 从数据库获取角色列表
	var roles []models.Role
	if err := s.authService.DB.Where("account_id = ? AND status = ?", account.ID, 1).Find(&roles).Error; err != nil {
		logger.Error("获取角色列表失败", logger.Error(err), logger.Int64("account_id", account.ID))
		return c.JSON(http.StatusInternalServerError, Error(500, "获取角色列表失败"))
	}

	// 构建角色响应
	characters := make([]models.RoleResponse, 0, len(roles))
	for _, role := range roles {
		characters = append(characters, models.RoleResponse{
			CharGUID:    role.CharGUID,
			Name:        role.Name,
			Level:       role.Level,
			Job:         role.Job,
			GrowType:    role.GrowType,
			SecGrowType: role.SecGrowType,
			Status:      role.Status,
		})
	}

	response := map[string]interface{}{
		"error":      0,
		"characters": characters,
	}

	return c.JSON(http.StatusOK, response)
}

// CreateCharacterRequest 创建角色请求
type CreateCharacterRequest struct {
	Name     string `json:"name" validate:"required"`
	Job      int    `json:"job" validate:"required"`
	GrowType int    `json:"growType"`
}

// createCharacter 创建角色
func (s *Server) createCharacter(c echo.Context) error {
	// 获取authKey
	authKey := c.QueryParam("authKey")
	if authKey == "" {
		// 尝试从Authorization header获取
		authHeader := c.Request().Header.Get("Authorization")
		if len(authHeader) > 7 && authHeader[:7] == "Bearer " {
			authKey = authHeader[7:]
		}
	}

	if authKey == "" {
		return c.JSON(http.StatusBadRequest, Error(400, "authKey不能为空"))
	}

	if s.authService == nil {
		return c.JSON(http.StatusInternalServerError, Error(500, "认证服务未初始化"))
	}

	// 验证认证密钥
	account, err := s.authService.ValidateAuth(authKey)
	if err != nil {
		return c.JSON(http.StatusUnauthorized, Error(401, "认证失败: "+err.Error()))
	}

	// 绑定请求参数
	var req CreateCharacterRequest
	if err := c.Bind(&req); err != nil {
		return c.JSON(http.StatusBadRequest, Error(400, "请求参数错误"))
	}

	if req.Name == "" {
		return c.JSON(http.StatusBadRequest, Error(400, "角色名称不能为空"))
	}

	// 检查角色名称是否已存在
	var existingRole models.Role
	if err := s.authService.DB.Where("account_id = ? AND name = ?", account.ID, req.Name).First(&existingRole).Error; err == nil {
		return c.JSON(http.StatusBadRequest, Error(400, "角色名称已存在"))
	}

	// 创建新角色
	role := models.Role{
		AccountID:    account.ID,
		CharGUID:     time.Now().UnixNano(),
		Name:         req.Name,
		Level:        1,
		Job:          req.Job,
		GrowType:     req.GrowType,
		SecGrowType:  0,
		Exp:          0,
		Gold:         1000,
		Hp:           100,
		Mp:           100,
		Strength:     10,
		Intelligence: 10,
		Vitality:     10,
		Spirit:       10,
		Status:       1,
		CreatedAt:    time.Now(),
		LastPlayAt:   time.Now(),
	}

	if err := s.authService.DB.Create(&role).Error; err != nil {
		logger.Error("创建角色失败", logger.Error(err), logger.Int64("account_id", account.ID), logger.String("name", req.Name))
		return c.JSON(http.StatusInternalServerError, Error(500, "创建角色失败"))
	}

	response := map[string]interface{}{
		"error": 0,
		"data": models.RoleResponse{
			CharGUID:    role.CharGUID,
			Name:        role.Name,
			Level:       role.Level,
			Job:         role.Job,
			GrowType:    role.GrowType,
			SecGrowType: role.SecGrowType,
			Status:      role.Status,
		},
	}

	return c.JSON(http.StatusOK, response)
}

// SelectCharacterRequest 选择角色请求
type SelectCharacterRequest struct {
	CharGUID int64 `json:"charGuid" validate:"required"`
}

// selectCharacter 选择角色
func (s *Server) selectCharacter(c echo.Context) error {
	// 获取authKey
	authKey := c.QueryParam("authKey")
	if authKey == "" {
		// 尝试从Authorization header获取
		authHeader := c.Request().Header.Get("Authorization")
		if len(authHeader) > 7 && authHeader[:7] == "Bearer " {
			authKey = authHeader[7:]
		}
	}

	if authKey == "" {
		return c.JSON(http.StatusBadRequest, Error(400, "authKey不能为空"))
	}

	if s.authService == nil {
		return c.JSON(http.StatusInternalServerError, Error(500, "认证服务未初始化"))
	}

	// 验证认证密钥
	account, err := s.authService.ValidateAuth(authKey)
	if err != nil {
		return c.JSON(http.StatusUnauthorized, Error(401, "认证失败: "+err.Error()))
	}

	// 绑定请求参数
	var req SelectCharacterRequest
	if err := c.Bind(&req); err != nil {
		return c.JSON(http.StatusBadRequest, Error(400, "请求参数错误"))
	}

	// 检查角色是否存在且属于该账号
	var role models.Role
	if err := s.authService.DB.Where("account_id = ? AND char_guid = ?", account.ID, req.CharGUID).First(&role).Error; err != nil {
		return c.JSON(http.StatusBadRequest, Error(400, "角色不存在或不属于该账号"))
	}

	// 这里可以将选择的角色信息存储到session或缓存中
	// 后续进入游戏时使用

	response := map[string]interface{}{
		"error":    0,
		"message":  "角色选择成功",
		"charGuid": req.CharGUID,
	}

	return c.JSON(http.StatusOK, response)
}

// enterGame 进入游戏
func (s *Server) enterGame(c echo.Context) error {
	// 获取authKey
	authKey := c.QueryParam("authKey")
	if authKey == "" {
		// 尝试从Authorization header获取
		authHeader := c.Request().Header.Get("Authorization")
		if len(authHeader) > 7 && authHeader[:7] == "Bearer " {
			authKey = authHeader[7:]
		}
	}

	if authKey == "" {
		return c.JSON(http.StatusBadRequest, Error(400, "authKey不能为空"))
	}

	if s.authService == nil {
		return c.JSON(http.StatusInternalServerError, Error(500, "认证服务未初始化"))
	}

	// 验证认证密钥
	account, err := s.authService.ValidateAuth(authKey)
	if err != nil {
		return c.JSON(http.StatusUnauthorized, Error(401, "认证失败: "+err.Error()))
	}

	// TODO: 检查是否已选择角色
	// 这里简化处理，实际应该从session或缓存中获取已选择的角色

	// 获取账号下的第一个角色
	var role models.Role
	if err := s.authService.DB.Where("account_id = ?", account.ID).First(&role).Error; err != nil {
		return c.JSON(http.StatusBadRequest, Error(400, "未选择角色或角色不存在"))
	}

	// 模拟进入游戏逻辑
	// 实际应该加载角色数据、初始化游戏状态等

	response := map[string]interface{}{
		"error":      0,
		"message":    "进入游戏成功",
		"charGuid":   role.CharGUID,
		"serverTime": time.Now().Unix(),
	}

	return c.JSON(http.StatusOK, response)
}
