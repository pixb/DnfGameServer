# Server层架构设计文档

## 1. 概述

### 1.1 设计目标
实现统一的服务层架构，支持三种协议同时运行：
- **REST API**: gRPC-Gateway 提供标准HTTP接口
- **Connect RPC**: 浏览器友好的RPC协议
- **原生gRPC**: HTTP/2 高性能RPC

### 1.2 核心特性
- **单端口多协议**: 使用cmux在单个端口上同时服务HTTP/1.1和HTTP/2
- **统一认证**: 一套认证逻辑同时支持三种协议
- **拦截器链**: 元数据→日志→恢复→认证
- **代码生成**: 从proto定义自动生成所有服务端代码

## 2. 目录结构

```
server/
├── server.go                        # 服务器主类 (核心)
├── auth/                            # 认证模块
│   ├── authenticator.go             # 主认证器
│   ├── claims.go                    # JWT声明
│   ├── context.go                   # 上下文工具
│   ├── extract.go                   # Token提取
│   └── token.go                     # Token生成与验证
│
└── router/
    └── api/
        └── v1/                      # API v1版本
            ├── v1.go                # 服务注册主文件 (核心)
            ├── acl_config.go        # 公开端点配置
            ├── connect_interceptors.go  # Connect拦截器链
            ├── connect_handler.go   # Connect服务包装器
            ├── header_carrier.go    # 协议无关的header处理
            │
            ├── auth_service.go      # AuthService实现
            ├── role_service.go      # RoleService实现
            ├── item_service.go      # ItemService实现
            ├── dungeon_service.go   # DungeonService实现
            ├── chat_service.go      # ChatService实现
            ├── shop_service.go      # ShopService实现
            ├── quest_service.go     # QuestService实现
            └── guild_service.go     # GuildService实现
```

## 3. 服务器主类 (server/server.go)

```go
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

    v1pb "github.com/dnf-go-server/proto/gen/api/v1"
    "github.com/dnf-go-server/internal/profile"
    "github.com/dnf-go-server/server/router/api/v1"
    "github.com/dnf-go-server/store"
)

// Server 游戏服务器主类
type Server struct {
    Profile *profile.Profile
    Store   *store.Store
    Secret  string

    echoServer   *echo.Echo
    grpcServer   *grpc.Server
    apiV1Service *v1.APIV1Service
    wg           sync.WaitGroup
}

// NewServer 创建服务器实例
func NewServer(ctx context.Context, prof *profile.Profile, store *store.Store) (*Server, error) {
    s := &Server{
        Profile: prof,
        Store:   store,
    }

    // ========== Echo服务器设置 ==========
    echoServer := echo.New()
    echoServer.Debug = prof.IsDev()
    echoServer.HideBanner = true
    echoServer.Use(middleware.Recover())
    echoServer.Use(middleware.Logger())
    echoServer.Use(middleware.CORS())
    s.echoServer = echoServer

    // 健康检查
    echoServer.GET("/healthz", func(c echo.Context) error {
        return c.String(http.StatusOK, "Service ready.")
    })

    // 游戏状态检查
    echoServer.GET("/api/v1/status", func(c echo.Context) error {
        return c.JSON(http.StatusOK, map[string]interface{}{
            "status":  "running",
            "version": "1.0.0",
        })
    })

    // ========== 获取或创建密钥 ==========
    secret, err := s.getOrCreateSecret(ctx)
    if err != nil {
        return nil, fmt.Errorf("failed to get secret: %w", err)
    }
    s.Secret = secret

    // ========== 创建API v1服务 ==========
    s.apiV1Service = v1.NewAPIV1Service(s.Secret, prof, store)

    // ========== gRPC服务器设置 ==========
    s.grpcServer = grpc.NewServer()
    
    // 注册所有gRPC服务
    v1pb.RegisterAuthServiceServer(s.grpcServer, s.apiV1Service)
    v1pb.RegisterRoleServiceServer(s.grpcServer, s.apiV1Service)
    v1pb.RegisterItemServiceServer(s.grpcServer, s.apiV1Service)
    v1pb.RegisterDungeonServiceServer(s.grpcServer, s.apiV1Service)
    v1pb.RegisterChatServiceServer(s.grpcServer, s.apiV1Service)
    v1pb.RegisterShopServiceServer(s.grpcServer, s.apiV1Service)
    v1pb.RegisterQuestServiceServer(s.grpcServer, s.apiV1Service)
    v1pb.RegisterGuildServiceServer(s.grpcServer, s.apiV1Service)

    return s, nil
}

// getOrCreateSecret 获取或创建服务器密钥
func (s *Server) getOrCreateSecret(ctx context.Context) (string, error) {
    setting, err := s.Store.GetSystemSetting(ctx, "secret_key")
    if err != nil && err != store.ErrNotFound {
        return "", err
    }
    
    if setting != nil && setting.Value != "" {
        return setting.Value, nil
    }
    
    // 生成新密钥
    secret := generateRandomSecret(32)
    err = s.Store.UpsertSystemSetting(ctx, &store.SystemSetting{
        Name:        "secret_key",
        Value:       secret,
        Description: "Server secret key for JWT signing",
    })
    
    return secret, err
}

// Start 启动服务器
func (s *Server) Start(ctx context.Context) error {
    address := fmt.Sprintf("%s:%d", s.Profile.Addr, s.Profile.Port)

    listener, err := net.Listen("tcp", address)
    if err != nil {
        return fmt.Errorf("failed to listen: %w", err)
    }

    // ========== cmux多路复用 ==========
    m := cmux.New(listener)
    
    // HTTP/1.1 匹配器 (REST + Gateway + Connect)
    httpListener := m.Match(cmux.HTTP1Fast())
    
    // HTTP/2 匹配器 (原生gRPC)
    grpcListener := m.Match(cmux.HTTP2())

    // ========== 启动gRPC服务器 ==========
    s.wg.Add(1)
    go func() {
        defer s.wg.Done()
        s.echoServer.Logger.Info("gRPC server starting on ", address)
        s.grpcServer.Serve(grpcListener)
    }()

    // ========== 注册Gateway处理器 ==========
    if err := s.apiV1Service.RegisterGateway(ctx, s.echoServer); err != nil {
        return fmt.Errorf("failed to register gateway: %w", err)
    }

    // ========== 启动Echo服务器 ==========
    s.echoServer.Listener = httpListener
    s.wg.Add(1)
    go func() {
        defer s.wg.Done()
        s.echoServer.Logger.Info("Echo server starting on ", address)
        s.echoServer.Start(address)
    }()

    // ========== 启动cmux路由器 ==========
    s.wg.Add(1)
    go func() {
        defer s.wg.Done()
        s.echoServer.Logger.Info("cmux router starting")
        m.Serve()
    }()

    s.echoServer.Logger.Info("Server started successfully (HTTP/1.1 + HTTP/2)")
    return nil
}

// Shutdown 优雅关闭服务器
func (s *Server) Shutdown(ctx context.Context) error {
    s.echoServer.Logger.Info("Shutting down server...")

    // 1. 停止gRPC服务器
    if s.grpcServer != nil {
        s.grpcServer.GracefulStop()
    }

    // 2. 关闭Echo服务器
    shutdownCtx, cancel := context.WithTimeout(ctx, 10*time.Second)
    defer cancel()
    s.echoServer.Shutdown(shutdownCtx)

    // 3. 关闭Store
    s.Store.Close()
    
    // 4. 等待所有goroutine
    s.wg.Wait()
    
    return nil
}

func generateRandomSecret(length int) string {
    // 实现随机密钥生成
    return "your-secret-key-here"
}
```

## 4. 服务注册主文件 (server/router/api/v1/v1.go)

```go
package v1

import (
    "context"
    "net/http"

    "connectrpc.com/connect"
    "github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
    "github.com/labstack/echo/v4"
    echomiddleware "github.com/labstack/echo/v4/middleware"

    v1pb "github.com/dnf-go-server/proto/gen/api/v1"
    apiv1connect "github.com/dnf-go-server/proto/gen/api/v1/apiv1connect"
    "github.com/dnf-go-server/internal/profile"
    "github.com/dnf-go-server/server/auth"
    "github.com/dnf-go-server/store"
)

// APIV1Service 实现所有gRPC服务
type APIV1Service struct {
    // 嵌入所有Unimplemented*Server以实现接口
    v1pb.UnimplementedAuthServiceServer
    v1pb.UnimplementedRoleServiceServer
    v1pb.UnimplementedItemServiceServer
    v1pb.UnimplementedDungeonServiceServer
    v1pb.UnimplementedChatServiceServer
    v1pb.UnimplementedShopServiceServer
    v1pb.UnimplementedQuestServiceServer
    v1pb.UnimplementedGuildServiceServer

    Secret  string
    Profile *profile.Profile
    Store   *store.Store
}

// NewAPIV1Service 创建API v1服务
func NewAPIV1Service(secret string, profile *profile.Profile, store *store.Store) *APIV1Service {
    return &APIV1Service{
        Secret:  secret,
        Profile: profile,
        Store:   store,
    }
}

// RegisterGateway 注册Gateway和Connect处理器
func (s *APIV1Service) RegisterGateway(ctx context.Context, echoServer *echo.Echo) error {
    // ========== Gateway认证中间件 ==========
    authenticator := auth.NewAuthenticator(s.Store, s.Secret)
    
    gatewayAuthMiddleware := func(next runtime.HandlerFunc) runtime.HandlerFunc {
        return func(w http.ResponseWriter, r *http.Request, pathParams map[string]string) {
            ctx := r.Context()

            // 获取RPC方法名
            rpcMethod, ok := runtime.RPCMethod(ctx)

            // 提取认证头
            authHeader := r.Header.Get("Authorization")

            // 执行认证
            result := authenticator.Authenticate(ctx, authHeader)

            // 非公开方法需要认证
            if result == nil && ok && !IsPublicMethod(rpcMethod) {
                http.Error(w, `{"code": 16, "message": "authentication required"}`, http.StatusUnauthorized)
                return
            }

            // 设置上下文
            if result != nil {
                if result.Claims != nil {
                    ctx = auth.SetUserClaimsInContext(ctx, result.Claims)
                    ctx = context.WithValue(ctx, auth.UserIDContextKey, result.Claims.UserID)
                } else if result.User != nil {
                    ctx = auth.SetUserInContext(ctx, result.User, result.AccessToken)
                }
                r = r.WithContext(ctx)
            }

            next(w, r, pathParams)
        }
    }

    // ========== 创建gRPC-Gateway mux ==========
    gwMux := runtime.NewServeMux(
        runtime.WithMiddlewares(gatewayAuthMiddleware),
    )

    // 注册所有服务处理器
    if err := v1pb.RegisterAuthServiceHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }
    if err := v1pb.RegisterRoleServiceHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }
    if err := v1pb.RegisterItemServiceHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }
    if err := v1pb.RegisterDungeonServiceHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }
    if err := v1pb.RegisterChatServiceHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }
    if err := v1pb.RegisterShopServiceHandlerHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }
    if err := v1pb.RegisterQuestServiceHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }
    if err := v1pb.RegisterGuildServiceHandlerServer(ctx, gwMux, s); err != nil {
        return err
    }

    // Gateway路由组
    gwGroup := echoServer.Group("")
    gwGroup.Use(echomiddleware.CORS())
    handler := echo.WrapHandler(gwMux)

    // 注册路由
    gwGroup.Any("/api/v1/*", handler)

    // ========== Connect处理器注册 ==========
    connectInterceptors := connect.WithInterceptors(
        NewMetadataInterceptor(),
        NewLoggingInterceptor(s.Profile.Demo),
        NewRecoveryInterceptor(s.Profile.Demo),
        NewAuthInterceptor(s.Store, s.Secret),
    )

    connectMux := http.NewServeMux()
    connectHandler := NewConnectServiceHandler(s)
    connectHandler.RegisterConnectHandlers(connectMux, connectInterceptors)

    // Connect CORS配置
    corsHandler := echomiddleware.CORSWithConfig(echomiddleware.CORSConfig{
        AllowOriginFunc: func(_ string) (bool, error) {
            return true, nil // 开发环境允许所有
        },
        AllowMethods:     []string{http.MethodGet, http.MethodPost, http.MethodOptions},
        AllowHeaders:     []string{"*"},
        AllowCredentials: true,
    })

    // Connect路由组
    connectGroup := echoServer.Group("", corsHandler)
    connectGroup.Any("/dnf.api.v1.*", echo.WrapHandler(connectMux))

    return nil
}
```

## 5. 公开端点配置 (server/router/api/v1/acl_config.go)

```go
package v1

// PublicMethods 定义不需要认证的API端点
// 这是认证的唯一真实来源
var PublicMethods = map[string]struct{}{
    // ========== 认证服务 ==========
    "/dnf.api.v1.AuthService/Login":        {},
    "/dnf.api.v1.AuthService/CreateAccount": {},
    "/dnf.api.v1.AuthService/RefreshToken":  {},

    // ========== 公开信息 ==========
    "/dnf.api.v1.RoleService/ListProfessions": {},
    "/dnf.api.v1.ShopService/GetShopList":    {},
    "/dnf.api.v1.GuildService/ListGuilds":    {},
}

// IsPublicMethod 检查端点是否公开
func IsPublicMethod(procedure string) bool {
    _, ok := PublicMethods[procedure]
    return ok
}
```

## 6. Connect拦截器链

### 6.1 元数据拦截器 (connect_interceptors.go)

```go
package v1

import (
    "context"

    "connectrpc.com/connect"
    "google.golang.org/grpc/metadata"
)

// MetadataInterceptor 转换HTTP头到gRPC元数据
type MetadataInterceptor struct{}

func NewMetadataInterceptor() *MetadataInterceptor {
    return &MetadataInterceptor{}
}

func (*MetadataInterceptor) WrapUnary(next connect.UnaryFunc) connect.UnaryFunc {
    return func(ctx context.Context, req connect.AnyRequest) (connect.AnyResponse, error) {
        header := req.Header()
        md := metadata.MD{}

        // 复制重要头信息
        if ua := header.Get("User-Agent"); ua != "" {
            md.Set("user-agent", ua)
        }
        if xff := header.Get("X-Forwarded-For"); xff != "" {
            md.Set("x-forwarded-for", xff)
        }
        if xri := header.Get("X-Real-Ip"); xri != "" {
            md.Set("x-real-ip", xri)
        }
        if cookie := header.Get("Cookie"); cookie != "" {
            md.Set("cookie", cookie)
        }

        ctx = metadata.NewIncomingContext(ctx, md)
        resp, err := next(ctx, req)

        // 禁用缓存
        if resp != nil && resp.Header() != nil {
            resp.Header().Set("Cache-Control", "no-cache, no-store, must-revalidate")
            resp.Header().Set("Pragma", "no-cache")
            resp.Header().Set("Expires", "0")
        }

        return resp, err
    }
}

func (*MetadataInterceptor) WrapStreamingClient(next connect.StreamingClientFunc) connect.StreamingClientFunc {
    return next
}

func (*MetadataInterceptor) WrapStreamingHandler(next connect.StreamingHandlerFunc) connect.StreamingHandlerFunc {
    return next
}
```

### 6.2 日志拦截器

```go
package v1

import (
    "context"
    "fmt"
    "log/slog"

    "connectrpc.com/connect"
    pkgerrors "github.com/pkg/errors"
)

// LoggingInterceptor 记录Connect RPC请求日志
type LoggingInterceptor struct {
    logStacktrace bool
}

func NewLoggingInterceptor(logStacktrace bool) *LoggingInterceptor {
    return &LoggingInterceptor{logStacktrace: logStacktrace}
}

func (in *LoggingInterceptor) WrapUnary(next connect.UnaryFunc) connect.UnaryFunc {
    return func(ctx context.Context, req connect.AnyRequest) (connect.AnyResponse, error) {
        resp, err := next(ctx, req)
        in.log(req.Spec().Procedure, err)
        return resp, err
    }
}

func (in *LoggingInterceptor) log(procedure string, err error) {
    level, msg := in.classifyError(err)
    attrs := []slog.Attr{slog.String("method", procedure)}
    if err != nil {
        attrs = append(attrs, slog.String("error", err.Error()))
        if in.logStacktrace {
            attrs = append(attrs, slog.String("stacktrace", fmt.Sprintf("%+v", err)))
        }
    }
    slog.LogAttrs(context.Background(), level, msg, attrs...)
}

func (in *LoggingInterceptor) classifyError(err error) (slog.Level, string) {
    if err == nil {
        return slog.LevelInfo, "OK"
    }

    var connectErr *connect.Error
    if !pkgerrors.As(err, &connectErr) {
        return slog.LevelError, "unknown error"
    }

    // 客户端错误
    switch connectErr.Code() {
    case connect.CodeCanceled,
        connect.CodeInvalidArgument,
        connect.CodeNotFound,
        connect.CodeAlreadyExists,
        connect.CodePermissionDenied,
        connect.CodeUnauthenticated,
        connect.CodeResourceExhausted,
        connect.CodeFailedPrecondition,
        connect.CodeAborted,
        connect.CodeOutOfRange:
        return slog.LevelInfo, "client error"
    default:
        return slog.LevelError, "server error"
    }
}
```

### 6.3 恢复拦截器

```go
package v1

import (
    "context"
    "runtime/debug"

    "connectrpc.com/connect"
    pkgerrors "github.com/pkg/errors"
)

// RecoveryInterceptor 从panic中恢复
type RecoveryInterceptor struct {
    logStacktrace bool
}

func NewRecoveryInterceptor(logStacktrace bool) *RecoveryInterceptor {
    return &RecoveryInterceptor{logStacktrace: logStacktrace}
}

func (in *RecoveryInterceptor) WrapUnary(next connect.UnaryFunc) connect.UnaryFunc {
    return func(ctx context.Context, req connect.AnyRequest) (resp connect.AnyResponse, err error) {
        defer func() {
            if r := recover(); r != nil {
                in.logPanic(req.Spec().Procedure, r)
                err = connect.NewError(connect.CodeInternal, pkgerrors.New("internal server error"))
            }
        }()
        return next(ctx, req)
    }
}

func (in *RecoveryInterceptor) logPanic(procedure string, panicValue any) {
    attrs := []slog.Attr{
        slog.String("method", procedure),
        slog.Any("panic", panicValue),
    }
    if in.logStacktrace {
        attrs = append(attrs, slog.String("stacktrace", string(debug.Stack())))
    }
    slog.LogAttrs(context.Background(), slog.LevelError, "panic recovered in Connect handler", attrs...)
}
```

### 6.4 认证拦截器

```go
package v1

import (
    "context"
    "errors"

    "connectrpc.com/connect"

    "github.com/dnf-go-server/server/auth"
    "github.com/dnf-go-server/store"
)

// AuthInterceptor Connect认证拦截器
type AuthInterceptor struct {
    authenticator *auth.Authenticator
}

func NewAuthInterceptor(store *store.Store, secret string) *AuthInterceptor {
    return &AuthInterceptor{
        authenticator: auth.NewAuthenticator(store, secret),
    }
}

func (in *AuthInterceptor) WrapUnary(next connect.UnaryFunc) connect.UnaryFunc {
    return func(ctx context.Context, req connect.AnyRequest) (connect.AnyResponse, error) {
        header := req.Header()
        authHeader := header.Get("Authorization")

        result := in.authenticator.Authenticate(ctx, authHeader)

        // 非公开方法需要认证
        if result == nil && !IsPublicMethod(req.Spec().Procedure) {
            return nil, connect.NewError(connect.CodeUnauthenticated, errors.New("authentication required"))
        }

        // 设置上下文
        if result != nil {
            if result.Claims != nil {
                ctx = auth.SetUserClaimsInContext(ctx, result.Claims)
                ctx = context.WithValue(ctx, auth.UserIDContextKey, result.Claims.UserID)
            } else if result.User != nil {
                ctx = auth.SetUserInContext(ctx, result.User, result.AccessToken)
            }
        }

        return next(ctx, req)
    }
}
```

## 7. Connect服务包装器 (connect_handler.go)

```go
package v1

import (
    "context"
    "net/http"

    "connectrpc.com/connect"
    "google.golang.org/grpc/codes"
    "google.golang.org/grpc/status"

    apiv1connect "github.com/dnf-go-server/proto/gen/api/v1/apiv1connect"
    v1pb "github.com/dnf-go-server/proto/gen/api/v1"
)

// ConnectServiceHandler 包装APIV1Service实现Connect接口
type ConnectServiceHandler struct {
    *APIV1Service
}

func NewConnectServiceHandler(svc *APIV1Service) *ConnectServiceHandler {
    return &ConnectServiceHandler{APIV1Service: svc}
}

// RegisterConnectHandlers 注册所有Connect服务处理器
func (s *ConnectServiceHandler) RegisterConnectHandlers(mux *http.ServeMux, opts ...connect.HandlerOption) {
    handlers := []struct {
        path    string
        handler http.Handler
    }{
        wrap(apiv1connect.NewAuthServiceHandler(s, opts...)),
        wrap(apiv1connect.NewRoleServiceHandler(s, opts...)),
        wrap(apiv1connect.NewItemServiceHandler(s, opts...)),
        wrap(apiv1connect.NewDungeonServiceHandler(s, opts...)),
        wrap(apiv1connect.NewChatServiceHandler(s, opts...)),
        wrap(apiv1connect.NewShopServiceHandler(s, opts...)),
        wrap(apiv1connect.NewQuestServiceHandler(s, opts...)),
        wrap(apiv1connect.NewGuildServiceHandler(s, opts...)),
    }

    for _, h := range handlers {
        mux.Handle(h.path, h.handler)
    }
}

func wrap(path string, handler http.Handler) struct {
    path    string
    handler http.Handler
} {
    return struct {
        path    string
        handler http.Handler
    }{path, handler}
}

// ========== AuthService Connect实现 ==========

func (s *ConnectServiceHandler) Login(ctx context.Context, req *connect.Request[v1pb.LoginRequest]) (*connect.Response[v1pb.LoginResponse], error) {
    resp, err := s.APIV1Service.Login(ctx, req.Msg)
    if err != nil {
        return nil, convertGRPCError(err)
    }
    return connect.NewResponse(resp), nil
}

func (s *ConnectServiceHandler) CreateAccount(ctx context.Context, req *connect.Request[v1pb.CreateAccountRequest]) (*connect.Response[v1pb.CreateAccountResponse], error) {
    resp, err := s.APIV1Service.CreateAccount(ctx, req.Msg)
    if err != nil {
        return nil, convertGRPCError(err)
    }
    return connect.NewResponse(resp), nil
}

func (s *ConnectServiceHandler) RefreshToken(ctx context.Context, req *connect.Request[v1pb.RefreshTokenRequest]) (*connect.Response[v1pb.RefreshTokenResponse], error) {
    return connectWithHeaderCarrier(ctx, func(ctx context.Context) (*v1pb.RefreshTokenResponse, error) {
        return s.APIV1Service.RefreshToken(ctx, req.Msg)
    })
}

// ========== RoleService Connect实现 ==========

func (s *ConnectServiceHandler) CreateRole(ctx context.Context, req *connect.Request[v1pb.CreateRoleRequest]) (*connect.Response[v1pb.CreateRoleResponse], error) {
    resp, err := s.APIV1Service.CreateRole(ctx, req.Msg)
    if err != nil {
        return nil, convertGRPCError(err)
    }
    return connect.NewResponse(resp), nil
}

func (s *ConnectServiceHandler) GetRole(ctx context.Context, req *connect.Request[v1pb.GetRoleRequest]) (*connect.Response[v1pb.Role], error) {
    resp, err := s.APIV1Service.GetRole(ctx, req.Msg)
    if err != nil {
        return nil, convertGRPCError(err)
    }
    return connect.NewResponse(resp), nil
}

func (s *ConnectServiceHandler) ListRoles(ctx context.Context, req *connect.Request[v1pb.ListRolesRequest]) (*connect.Response[v1pb.ListRolesResponse], error) {
    resp, err := s.APIV1Service.ListRoles(ctx, req.Msg)
    if err != nil {
        return nil, convertGRPCError(err)
    }
    return connect.NewResponse(resp), nil
}

// ... 其他服务的Connect实现

// convertGRPCError 转换gRPC错误到Connect错误
func convertGRPCError(err error) error {
    if err == nil {
        return nil
    }
    if st, ok := status.FromError(err); ok {
        return connect.NewError(grpcCodeToConnectCode(st.Code()), err)
    }
    return connect.NewError(connect.CodeInternal, err)
}

func grpcCodeToConnectCode(code codes.Code) connect.Code {
    return connect.Code(code)
}
```

## 8. 协议无关的Header处理 (header_carrier.go)

```go
package v1

import (
    "context"

    "connectrpc.com/connect"
    "google.golang.org/grpc"
    "google.golang.org/grpc/metadata"
)

// headerCarrierKey 存储响应头的上下文key
type headerCarrierKey struct{}

// HeaderCarrier 存储需要设置的响应头
type HeaderCarrier struct {
    headers map[string]string
}

func newHeaderCarrier() *HeaderCarrier {
    return &HeaderCarrier{headers: make(map[string]string)}
}

func (h *HeaderCarrier) Set(key, value string) {
    h.headers[key] = value
}

func (h *HeaderCarrier) Get(key string) string {
    return h.headers[key]
}

func (h *HeaderCarrier) All() map[string]string {
    return h.headers
}

func WithHeaderCarrier(ctx context.Context) context.Context {
    return context.WithValue(ctx, headerCarrierKey{}, newHeaderCarrier())
}

func GetHeaderCarrier(ctx context.Context) *HeaderCarrier {
    if carrier, ok := ctx.Value(headerCarrierKey{}).(*HeaderCarrier); ok {
        return carrier
    }
    return nil
}

func SetResponseHeader(ctx context.Context, key, value string) error {
    if carrier := GetHeaderCarrier(ctx); carrier != nil {
        carrier.Set(key, value)
        return nil
    }
    return grpc.SetHeader(ctx, metadata.New(map[string]string{key: value}))
}

// connectWithHeaderCarrier 支持设置响应头的Connect包装器
func connectWithHeaderCarrier[T any](ctx context.Context, fn func(context.Context) (*T, error)) (*connect.Response[T], error) {
    ctx = WithHeaderCarrier(ctx)
    resp, err := fn(ctx)
    if err != nil {
        return nil, convertGRPCError(err)
    }
    connectResp := connect.NewResponse(resp)
    if carrier := GetHeaderCarrier(ctx); carrier != nil {
        for key, value := range carrier.All() {
            connectResp.Header().Set(key, value)
        }
    }
    return connectResp, nil
}
```

## 9. 认证模块

### 9.1 主认证器 (server/auth/authenticator.go)

```go
package auth

import (
    "context"
    "strings"
    "time"

    "github.com/dnf-go-server/store"
)

const PersonalAccessTokenPrefix = "pat_"

// AuthResult 认证结果
type AuthResult struct {
    User        *store.Account
    Claims      *UserClaims
    AccessToken string
}

// Authenticator 认证器
type Authenticator struct {
    Store  *store.Store
    Secret string
}

func NewAuthenticator(store *store.Store, secret string) *Authenticator {
    return &Authenticator{Store: store, Secret: secret}
}

// Authenticate 执行认证
func (a *Authenticator) Authenticate(ctx context.Context, authHeader string) *AuthResult {
    token := ExtractBearerToken(authHeader)
    if token == "" {
        return nil
    }

    // 尝试JWT Token (无状态)
    if !strings.HasPrefix(token, PersonalAccessTokenPrefix) {
        claims, err := a.AuthenticateByAccessToken(token)
        if err == nil && claims != nil {
            return &AuthResult{
                Claims:      claims,
                AccessToken: token,
            }
        }
    }

    // 尝试PAT (数据库验证)
    if strings.HasPrefix(token, PersonalAccessTokenPrefix) {
        // TODO: 实现PAT验证
        // user, pat, err := a.AuthenticateByPAT(ctx, token)
    }

    return nil
}

// AuthenticateByAccessToken 验证JWT Token
func (a *Authenticator) AuthenticateByAccessToken(token string) (*UserClaims, error) {
    claims, err := ValidateAccessToken(token, a.Secret)
    if err != nil {
        return nil, err
    }
    return &UserClaims{
        UserID:   claims.UserID,
        Username: claims.Username,
        Role:     claims.Role,
    }, nil
}
```

### 9.2 Token管理 (server/auth/token.go)

```go
package auth

import (
    "crypto/rand"
    "encoding/base64"
    "fmt"
    "time"

    "github.com/golang-jwt/jwt/v5"
)

const AccessTokenDuration = 15 * time.Minute
const RefreshTokenDuration = 7 * 24 * time.Hour

// JWTClaims JWT声明
type JWTClaims struct {
    jwt.RegisteredClaims
    UserID   int32  `json:"user_id"`
    Username string `json:"username"`
    Role     string `json:"role"`
}

// GenerateAccessToken 生成访问Token
func GenerateAccessToken(userID int32, username, role, secret string) (string, error) {
    now := time.Now()
    claims := JWTClaims{
        RegisteredClaims: jwt.RegisteredClaims{
            ExpiresAt: jwt.NewNumericDate(now.Add(AccessTokenDuration)),
            IssuedAt:  jwt.NewNumericDate(now),
            NotBefore: jwt.NewNumericDate(now),
            Issuer:    "dnf-go-server",
        },
        UserID:   userID,
        Username: username,
        Role:     role,
    }

    token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
    return token.SignedString([]byte(secret))
}

// ValidateAccessToken 验证访问Token
func ValidateAccessToken(tokenString, secret string) (*JWTClaims, error) {
    token, err := jwt.ParseWithClaims(tokenString, &JWTClaims{}, func(token *jwt.Token) (interface{}, error) {
        if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
            return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
        }
        return []byte(secret), nil
    })

    if claims, ok := token.Claims.(*JWTClaims); ok && token.Valid {
        return claims, nil
    }
    return nil, err
}

// GenerateRefreshToken 生成刷新Token
func GenerateRefreshToken() (string, error) {
    b := make([]byte, 32)
    _, err := rand.Read(b)
    if err != nil {
        return "", err
    }
    return base64.StdEncoding.EncodeToString(b), nil
}
```

### 9.3 上下文工具 (server/auth/context.go)

```go
package auth

import "context"

type ContextKey int

const (
    UserIDContextKey ContextKey = iota
    AccessTokenContextKey
    UserClaimsContextKey
    RefreshTokenIDContextKey
)

// GetUserID 从上下文获取用户ID
func GetUserID(ctx context.Context) int32 {
    if v, ok := ctx.Value(UserIDContextKey).(int32); ok {
        return v
    }
    return 0
}

// GetUserClaims 从上下文获取用户声明
func GetUserClaims(ctx context.Context) *UserClaims {
    if v, ok := ctx.Value(UserClaimsContextKey).(*UserClaims); ok {
        return v
    }
    return nil
}

// SetUserClaimsInContext 在上下文中设置用户声明
func SetUserClaimsInContext(ctx context.Context, claims *UserClaims) context.Context {
    return context.WithValue(ctx, UserClaimsContextKey, claims)
}

// SetUserInContext 在上下文中设置用户信息
func SetUserInContext(ctx context.Context, user *store.Account, accessToken string) context.Context {
    ctx = context.WithValue(ctx, UserIDContextKey, user.ID)
    if accessToken != "" {
        ctx = context.WithValue(ctx, AccessTokenContextKey, accessToken)
    }
    return ctx
}
```

### 9.4 Token提取 (server/auth/extract.go)

```go
package auth

import "strings"

const BearerPrefix = "Bearer "

// ExtractBearerToken 从Authorization头提取Token
func ExtractBearerToken(authHeader string) string {
    if strings.HasPrefix(authHeader, BearerPrefix) {
        return strings.TrimPrefix(authHeader, BearerPrefix)
    }
    return ""
}
```

## 10. Proto服务定义示例

### 10.1 Auth服务 (proto/api/v1/auth_service.proto)

```protobuf
syntax = "proto3";

package dnf.api.v1;

import "google/api/annotations.proto";
import "google/api/field_behavior.proto";

option go_package = "gen/api/v1";

service AuthService {
  rpc Login(LoginRequest) returns (LoginResponse) {
    option (google.api.http) = {
      post: "/api/v1/auth/login"
      body: "*"
    };
  }

  rpc CreateAccount(CreateAccountRequest) returns (CreateAccountResponse) {
    option (google.api.http) = {
      post: "/api/v1/auth/register"
      body: "*"
    };
  }

  rpc RefreshToken(RefreshTokenRequest) returns (RefreshTokenResponse) {
    option (google.api.http) = {
      post: "/api/v1/auth/refresh"
      body: "*"
    };
  }

  rpc Logout(LogoutRequest) returns (LogoutResponse) {
    option (google.api.http) = {
      post: "/api/v1/auth/logout"
      body: "*"
    };
  }
}

message LoginRequest {
  string username = 1 [(google.api.field_behavior) = REQUIRED];
  string password = 2 [(google.api.field_behavior) = REQUIRED];
}

message LoginResponse {
  string access_token = 1;
  string refresh_token = 2;
  int32 expires_in = 3; // 秒
  UserInfo user = 4;
}

message CreateAccountRequest {
  string username = 1 [(google.api.field_behavior) = REQUIRED];
  string password = 2 [(google.api.field_behavior) = REQUIRED];
  string email = 3;
  string phone = 4;
}

message CreateAccountResponse {
  int32 account_id = 1;
  string message = 2;
}

message RefreshTokenRequest {
  string refresh_token = 1 [(google.api.field_behavior) = REQUIRED];
}

message RefreshTokenResponse {
  string access_token = 1;
  string refresh_token = 2;
  int32 expires_in = 3;
}

message LogoutRequest {
  string refresh_token = 1;
}

message LogoutResponse {
  bool success = 1;
}

message UserInfo {
  int32 id = 1;
  string username = 2;
  string email = 3;
  string role = 4;
}
```

### 10.2 Role服务 (proto/api/v1/role_service.proto)

```protobuf
syntax = "proto3";

package dnf.api.v1;

import "google/api/annotations.proto";
import "google/api/field_behavior.proto";

option go_package = "gen/api/v1";

service RoleService {
  rpc CreateRole(CreateRoleRequest) returns (CreateRoleResponse) {
    option (google.api.http) = {
      post: "/api/v1/roles"
      body: "*"
    };
  }

  rpc GetRole(GetRoleRequest) returns (Role) {
    option (google.api.http) = {
      get: "/api/v1/roles/{role_id}"
    };
  }

  rpc ListRoles(ListRolesRequest) returns (ListRolesResponse) {
    option (google.api.http) = {
      get: "/api/v1/roles"
    };
  }

  rpc UpdateRole(UpdateRoleRequest) returns (Role) {
    option (google.api.http) = {
      patch: "/api/v1/roles/{role_id}"
      body: "*"
    };
  }

  rpc DeleteRole(DeleteRoleRequest) returns (DeleteRoleResponse) {
    option (google.api.http) = {
      delete: "/api/v1/roles/{role_id}"
    };
  }
}

message CreateRoleRequest {
  string name = 1 [(google.api.field_behavior) = REQUIRED];
  int32 profession = 2 [(google.api.field_behavior) = REQUIRED];
}

message CreateRoleResponse {
  int32 role_id = 1;
  Role role = 2;
}

message GetRoleRequest {
  int32 role_id = 1 [(google.api.field_behavior) = REQUIRED];
}

message ListRolesRequest {
  int32 account_id = 1;
  int32 page = 2;
  int32 page_size = 3;
}

message ListRolesResponse {
  repeated Role roles = 1;
  int32 total = 2;
}

message UpdateRoleRequest {
  int32 role_id = 1 [(google.api.field_behavior) = REQUIRED];
  optional string name = 2;
  optional int32 level = 3;
}

message DeleteRoleRequest {
  int32 role_id = 1 [(google.api.field_behavior) = REQUIRED];
}

message DeleteRoleResponse {
  bool success = 1;
}

message Role {
  int32 id = 1;
  int32 account_id = 2;
  string name = 3;
  int32 profession = 4;
  int32 level = 5;
  int64 exp = 6;
  int64 gold = 7;
  int64 diamonds = 8;
  int32 fatigue = 9;
  int32 max_fatigue = 10;
  RoleAttributes attributes = 11;
}

message RoleAttributes {
  int32 strength = 1;
  int32 intelligence = 2;
  int32 vitality = 3;
  int32 spirit = 4;
  int32 hp = 5;
  int32 max_hp = 6;
  int32 mp = 7;
  int32 max_mp = 8;
  int32 attack = 9;
  int32 defense = 10;
}
```

## 11. 多协议架构图

```
                    ┌─────────────────────────────────────────┐
                    │           客户端请求                    │
                    │  (REST/Connect/gRPC)                   │
                    └─────────────────┬───────────────────────┘
                                      │
                                      ▼
                    ┌─────────────────────────────────────────┐
                    │         单一端口 (8081)                  │
                    │    ┌─────────────────────────┐          │
                    │    │        cmux             │          │
                    │    │    连接多路复用         │          │
                    │    └───────────┬─────────────┘          │
                    └────────────────┼────────────────────────┘
                                     │
            ┌────────────────────────┼────────────────────────┐
            │                        │                        │
            ▼                        │                        ▼
┌─────────────────────┐              │         ┌─────────────────────┐
│  cmux.HTTP1Fast()   │              │         │   cmux.HTTP2()      │
│   (HTTP/1.1)        │              │         │    (HTTP/2)         │
└─────────┬───────────┘              │         └──────────┬──────────┘
          │                          │                    │
          ▼                          │                    ▼
┌─────────────────────┐              │         ┌─────────────────────┐
│    Echo Server      │              │         │   gRPC Server       │
│                     │              │         │                     │
│ /api/v1/*           │              │         │ AuthService         │
│   └> gRPC-Gateway   │              │         │ RoleService         │
│                     │              │         │ ItemService         │
│ /dnf.api.v1.*       │              │         │ ...                 │
│   └> Connect RPC    │              │         │                     │
└─────────────────────┘              │         └─────────────────────┘
                                     │
                    ┌────────────────┴────────────────┐
                    │      统一认证 & 拦截器链         │
                    └────────────────┬────────────────┘
                                     │
                                     ▼
                    ┌─────────────────────────────────┐
                    │           Store层               │
                    │     (Driver接口 + 缓存)         │
                    └─────────────────────────────────┘
```

## 12. 性能考虑

### 12.1 cmux性能
- 连接在建立时分类，零运行时开销
- 每个连接只匹配一次
- 支持数千并发连接

### 12.2 协议选择建议
- **REST API**: 第三方集成，简单请求
- **Connect RPC**: Web客户端，浏览器应用
- **gRPC**: 服务间通信，高性能场景

### 12.3 缓存策略
- 认证信息: JWT自包含，无需缓存
- 角色信息: 10分钟TTL
- 公会信息: 10分钟TTL
- 实时数据: 不缓存

---

**创建日期**: 2026-02-09
**版本**: v1.0
**状态**: 设计完成，待实现
