package v1

import (
	"context"
	"net/http"

	"github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"google.golang.org/grpc"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/pk_service"
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
	PK      *pk_service.PkService // 仅 MySQL 驱动下非 nil
}

// NewAPIV1Service 创建服务实例
func NewAPIV1Service(secret string, profile *profile.Profile, s *store.Store, pkSvc *pk_service.PkService) *APIV1Service {
	return &APIV1Service{
		Secret:  secret,
		Profile: profile,
		Store:   s,
		PK:      pkSvc,
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
	apiGroup.GET("/task/list", s.handleGetQuestList)
	apiGroup.POST("/task/accept", s.handleAcceptQuest)
	apiGroup.POST("/task/complete", s.handleCompleteQuest)
	apiGroup.POST("/task/reward", s.handleGetQuestReward)
	apiGroup.POST("/task/abandon", s.handleAbandonQuest)

	// 任务路由别名(兼容 /quest/* 客户端协议)
	apiGroup.GET("/quest/list", s.handleGetQuestList)
	apiGroup.POST("/quest/accept", s.handleAcceptQuest)
	apiGroup.POST("/quest/complete", s.handleCompleteQuest)
	apiGroup.POST("/quest/reward", s.handleGetQuestReward)
	apiGroup.POST("/quest/abandon", s.handleAbandonQuest)

	// 邮件路由
	apiGroup.GET("/mail/list", s.handleGetMailList)
	apiGroup.POST("/mail/send", s.handleSendMail)
	apiGroup.POST("/mail/claim", s.handleClaimMail)

	// 拍卖行路由
	apiGroup.GET("/auctions/search", s.handleSearchAuction)
	apiGroup.POST("/auctions", s.handleCreateAuction)
	apiGroup.POST("/auctions/:auction_id/bid", s.handleBidAuction)
	apiGroup.POST("/auctions/:auction_id/buyout", s.handleBuyoutAuction)
	apiGroup.POST("/auctions/:auction_id/cancel", s.handleCancelAuction)
	apiGroup.GET("/auctions/:auction_id", s.handleGetAuction)
	apiGroup.GET("/auctions", s.handleListAuctions)
	apiGroup.GET("/auctions/my", s.handleListMyAuctions)
	apiGroup.GET("/auctions/my-bids", s.handleListMyBids)
	apiGroup.GET("/auctions/history", s.handleListAuctionHistory)

	// 兼容测试用例的API路径
	apiGroup.POST("/auction/register", s.handleCreateAuction)
	apiGroup.POST("/auction/buyout", s.handleBuyoutAuction)
	apiGroup.POST("/auction/bid", s.handleBidAuction)
	apiGroup.POST("/auction/cancel", s.handleCancelAuction)
	apiGroup.GET("/auction/list", s.handleListAuctions)
	apiGroup.GET("/auction/my", s.handleListMyAuctions)
	apiGroup.GET("/auction/statistics", s.handleAuctionStatistics)
	apiGroup.GET("/auction/detail", s.handleAuctionDetail)
	apiGroup.GET("/auction/record", s.handleAuctionRecord)
	apiGroup.GET("/auction/fee", s.handleAuctionFee)
	apiGroup.GET("/auction/category", s.handleAuctionCategory)
	apiGroup.GET("/auction/search", s.handleSearchAuction)

	// 成就路由
	apiGroup.POST("/achievement/info", s.handleAchievementInfo)
	apiGroup.POST("/achievement/reward", s.handleAchievementReward)
	apiGroup.POST("/achievement/list", s.handleAchievementList)
	apiGroup.POST("/achievement/bonus_reward", s.handleAchievementBonusReward)

	// 冒险路由
	apiGroup.POST("/adventure/union/info", s.handleAdventureUnionInfo)
	apiGroup.POST("/adventure/union/name_change", s.handleAdventureUnionNameChange)
	apiGroup.POST("/adventure/union/expedition_start", s.handleAdventureUnionExpeditionStart)
	apiGroup.POST("/adventure/union/expedition_cancel", s.handleAdventureUnionExpeditionCancel)
	apiGroup.POST("/adventure/union/expedition_reward", s.handleAdventureUnionExpeditionReward)
	apiGroup.POST("/adventure/union/subdue_info", s.handleAdventureUnionSubdueInfo)
	apiGroup.POST("/adventure/union/subdue_start", s.handleAdventureUnionSubdueStart)
	apiGroup.POST("/adventure/union/subdue_reward", s.handleAdventureUnionSubdueReward)
	apiGroup.POST("/adventure/union/open_shareboard_slot", s.handleAdventureUnionOpenShareboardSlot)
	apiGroup.POST("/adventure/union/set_shareboard", s.handleAdventureUnionSetShareboard)
	apiGroup.POST("/adventure/reap/info", s.handleAdventureReapInfo)
	apiGroup.POST("/adventure/reap/reward", s.handleAdventureReapReward)
	apiGroup.POST("/adventure/union/search_start", s.handleAdventureUnionSearchStart)
	apiGroup.POST("/adventure/union/collection_reward", s.handleAdventureUnionCollectionReward)
	apiGroup.POST("/adventure/union/level_reward", s.handleAdventureUnionLevelReward)

	// 进入游戏/城镇路由
	apiGroup.POST("/game/ping", s.handleGamePing)
	apiGroup.POST("/game/enter_town", s.handleGameEnterTown)
	apiGroup.POST("/game/leave_town", s.handleGameLeaveTown)
	apiGroup.POST("/game/daily_reset", s.handleGameDailyReset)
	apiGroup.GET("/game/character_info", s.handleGameCharacterInfo)
	apiGroup.POST("/game/interaction_menu", s.handleGameInteractionMenu)
	apiGroup.POST("/game/not_transaction_state", s.handleGameNotTransactionState)
	apiGroup.POST("/game/pvp_record", s.handleGamePvpRecord)
	apiGroup.POST("/game/adventure_union_subdue", s.handleGameAdventureUnionSubdue)
	apiGroup.POST("/game/sending_invite_friend_list", s.handleGameSendingInviteFriendList)
	apiGroup.POST("/game/load_server_simple_data", s.handleGameLoadServerSimpleData)
	apiGroup.POST("/game/save_server_simple_data", s.handleGameSaveServerSimpleData)
	apiGroup.POST("/game/enter_channel", s.handleGameEnterChannel)
	apiGroup.POST("/game/standby", s.handleGameStandby)
	apiGroup.POST("/game/idip_notices", s.handleGameIdipNotices)
	apiGroup.POST("/game/black_diamon_info", s.handleGameBlackDiamonInfo)
	apiGroup.POST("/game/private_store_goods_list", s.handleGamePrivateStoreGoodsList)
	apiGroup.POST("/game/recommend_guild_list", s.handleGameRecommendGuildList)
	apiGroup.POST("/game/adventure_union_info_other", s.handleGameAdventureUnionInfoOther)
	apiGroup.POST("/game/start", s.handleGameStart)

	// PK 路由
	apiGroup.POST("/pk/multi_play_request_match", s.handlePkMultiPlayRequestMatch)
	apiGroup.POST("/pk/multi_play_request_match_cancel", s.handlePkMultiPlayRequestMatchCancel)
	apiGroup.POST("/pk/historic_site_noti", s.handlePkHistoricSiteNoti)
	apiGroup.POST("/pk/load_guild_donation_info", s.handlePkLoadGuildDonationInfo)
	apiGroup.POST("/pk/dream_maze_basic_info", s.handlePkDreamMazeBasicInfo)
	apiGroup.POST("/pk/raid_entrance_count", s.handlePkRaidEntranceCount)
	apiGroup.POST("/pk/loading_progress", s.handlePkLoadingProgress)
	apiGroup.POST("/pk/return_to_town", s.handlePkReturnToTown)
	apiGroup.POST("/pk/custom_game_room_setting", s.handlePkCustomGameRoomSetting)
	apiGroup.GET("/pk/record", s.handlePkRecord)
	apiGroup.GET("/pk/ranking", s.handlePkRanking)
	apiGroup.GET("/pk/stats", s.handlePkStats)
	apiGroup.GET("/pk/match_history", s.handlePkMatchHistory)
	apiGroup.GET("/pk/season_info", s.handlePkSeasonInfo)
	apiGroup.GET("/pk/reward", s.handlePkReward)
	apiGroup.POST("/pk/daily_reset", s.handlePkDailyReset)
	apiGroup.GET("/pk/match_types", s.handlePkMatchTypes)
	apiGroup.POST("/pk/battle_result", s.handlePkBattleResult)

	// 排名路由
	apiGroup.POST("/rank/personal", s.handleRankPersonal)
	apiGroup.POST("/rank/my", s.handleRankMy)
	apiGroup.POST("/rank/friend", s.handleRankFriend)
	apiGroup.POST("/rank/party", s.handleRankParty)
	apiGroup.POST("/rank/list", s.handleRankList)

	// 事件路由
	apiGroup.POST("/event/list", s.handleEventList)
	apiGroup.POST("/event/detail", s.handleEventDetail)
	apiGroup.POST("/event/access_time", s.handleEventAccessTime)
	apiGroup.POST("/event/get_reward", s.handleEventGetReward)
	apiGroup.POST("/event/update_progress", s.handleEventUpdateProgress)
	apiGroup.POST("/event/participate", s.handleEventParticipate)

	// 制作路由
	apiGroup.POST("/make/emblem/upgrade", s.handleMakeEmblemUpgrade)
	apiGroup.POST("/make/emblem/upgrade_quick", s.handleMakeEmblemUpgradeQuick)
	apiGroup.POST("/make/avatar/compose", s.handleMakeAvatarCompose)
	apiGroup.GET("/make/production/info", s.handleMakeProductionInfo)
	apiGroup.POST("/make/production/register", s.handleMakeProductionRegister)
	apiGroup.POST("/make/item/combine", s.handleMakeItemCombine)
	apiGroup.POST("/make/item/disjoint", s.handleMakeItemDisjoint)
	apiGroup.POST("/make/card/compose", s.handleMakeCardCompose)
	apiGroup.POST("/make/wardrobe/set_slot", s.handleMakeWardrobeSetSlot)

	// 组队路由
	apiGroup.POST("/party/search", s.handleSearchPartyList)
	apiGroup.POST("/party/create", s.handleCreateParty)
	apiGroup.POST("/party/check_prohibited_word", s.handleCheckProhibitedWord)
	apiGroup.POST("/party/target_user_info", s.handleTargetUserPartyInfo)

	// 角色路由
	apiGroup.GET("/character/list", s.handleGetCharacterList)
	apiGroup.POST("/character/create", s.handleCreateCharacter)
	apiGroup.POST("/character/select", s.handleSelectCharacter)
	apiGroup.POST("/character/enter", s.handleEnterGame)

	return nil
}

// IsPublicMethod 检查方法是否为公开端点
func IsPublicMethod(procedure string) bool {
	_, ok := PublicMethods[procedure]
	return ok
}
