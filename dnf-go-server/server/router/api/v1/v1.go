package v1

import (
	"context"
	"net/http"

	"github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"google.golang.org/grpc"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/profile"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/server/auth"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// APIV1Service API v1服务集合
type APIV1Service struct {
	// 嵌入所有服务的UnimplementedServer
	dnfv1.UnimplementedGameServiceServer
	dnfv1.UnimplementedAuthServiceServer

	Secret  string
	Profile *profile.Profile
	Store   *store.Store
}

// NewAPIV1Service 创建服务实例
func NewAPIV1Service(secret string, profile *profile.Profile, s *store.Store) *APIV1Service {
	return &APIV1Service{
		Secret:  secret,
		Profile: profile,
		Store:   s,
	}
}

// RegisterGRPCServices 注册gRPC服务
func (s *APIV1Service) RegisterGRPCServices(grpcServer *grpc.Server) {
	dnfv1.RegisterGameServiceServer(grpcServer, s)
	dnfv1.RegisterAuthServiceServer(grpcServer, s)
}

// authMiddleware Echo认证中间件
func authMiddleware(authenticator *auth.Authenticator) echo.MiddlewareFunc {
	return func(next echo.HandlerFunc) echo.HandlerFunc {
		return func(c echo.Context) error {
			authHeader := c.Request().Header.Get("Authorization")
			result := authenticator.Authenticate(c.Request().Context(), authHeader)

			if result == nil {
				return echo.NewHTTPError(http.StatusUnauthorized, `{"code": 16, "message": "authentication required"}`)
			}

			if result.Claims != nil {
				c.Set("user_id", result.Claims.UserID)
				c.Set("claims", result.Claims)
			}

			return next(c)
		}
	}
}

// RegisterGateway 注册网关处理器
func (s *APIV1Service) RegisterGateway(ctx context.Context, echoServer *echo.Echo) error {
	// 1. 网关认证中间件
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

			// 设置用户上下文
			if result != nil {
				if result.Claims != nil {
					ctx = auth.SetUserClaimsInContext(ctx, result.Claims)
					ctx = context.WithValue(ctx, auth.UserIDContextKey, result.Claims.UserID)
				} else if result.User != nil {
					ctx = auth.SetUserClaimsInContext(ctx, &auth.UserClaims{
						UserID: result.User.ID,
					})
				}
				r = r.WithContext(ctx)
			}

			next(w, r, pathParams)
		}
	}

	// 2. 创建gRPC-Gateway mux
	gwMux := runtime.NewServeMux(
		runtime.WithMiddlewares(gatewayAuthMiddleware),
	)

	// 3. 注册AuthService (有HTTP注解)
	if err := dnfv1.RegisterAuthServiceHandlerServer(ctx, gwMux, s); err != nil {
		return err
	}

	// 4. 创建网关路由组
	gwGroup := echoServer.Group("")
	gwGroup.Use(middleware.CORS())
	handler := echo.WrapHandler(gwMux)

	// 5. 注册网关路由
	gwGroup.Any("/api/v1/*", handler)

	// 6. 添加直接的REST路由（用于GameService）
	apiGroup := echoServer.Group("/api/v1")
	apiGroup.Use(authMiddleware(authenticator))
	apiGroup.Use(middleware.CORS())

	// 背包路由
	apiGroup.GET("/bag", s.handleGetBag)
	apiGroup.GET("/bag/items", s.handleGetBagItems)

	// 商店路由
	apiGroup.GET("/shop/list", s.handleGetShopList)
	apiGroup.POST("/shop/buy", s.handleBuyItem)
	apiGroup.POST("/shop/sell", s.handleSellItem)

	// 好友路由
	apiGroup.GET("/friend/list", s.handleGetFriendList)
	apiGroup.POST("/friend/add", s.handleAddFriend)
	apiGroup.POST("/friend/remove", s.handleRemoveFriend)

	// 公会路由
	apiGroup.GET("/guild/info", s.handleGetGuildInfo)
	apiGroup.POST("/guild/create", s.handleCreateGuild)
	apiGroup.POST("/guild/join", s.handleJoinGuild)
	apiGroup.POST("/guild/leave", s.handleLeaveGuild)

	// 任务路由
	apiGroup.GET("/quest/list", s.handleGetQuestList)
	apiGroup.POST("/quest/accept", s.handleAcceptQuest)
	apiGroup.POST("/quest/complete", s.handleCompleteQuest)

	// 邮件路由
	apiGroup.GET("/mail/list", s.handleGetMailList)
	apiGroup.POST("/mail/send", s.handleSendMail)
	apiGroup.POST("/mail/claim", s.handleClaimMail)

	// 拍卖行路由
	apiGroup.GET("/auction/search", s.handleSearchAuction)
	apiGroup.POST("/auction/register", s.handleRegisterAuction)
	apiGroup.POST("/auction/bid", s.handleBidAuction)
	apiGroup.POST("/auction/buyout", s.handleBuyoutAuction)

	// 成就路由
	apiGroup.POST("/achievement/info", s.handleAchievementInfo)
	apiGroup.POST("/achievement/reward", s.handleAchievementReward)
	apiGroup.POST("/achievement/list", s.handleAchievementList)
	apiGroup.POST("/achievement/bonus_reward", s.handleAchievementBonusReward)

	return nil
}

// IsPublicMethod 检查方法是否为公开端点
func IsPublicMethod(procedure string) bool {
	_, ok := PublicMethods[procedure]
	return ok
}
