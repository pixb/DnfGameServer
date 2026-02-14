package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
)

// RegisterAllHandlers 注册所有消息处理器
func RegisterAllHandlers(dispatcher *network.MessageDispatcher) {
	// ==================== 认证模块 (Module = 10000) ====================
	dispatcher.RegisterHandler(10000, 0, LoginHandler)            // 登录请求
	dispatcher.RegisterHandler(10000, 2, CreateCharacterHandler)  // 创建角色
	dispatcher.RegisterHandler(10000, 4, GetCharacterListHandler) // 获取角色列表
	dispatcher.RegisterHandler(10000, 6, SelectCharacterHandler)  // 选择角色

	// ==================== 角色模块 (Module = 10001) ====================
	dispatcher.RegisterHandler(10001, 0, GetRoleInfoHandler)      // 获取角色信息
	dispatcher.RegisterHandler(10001, 2, UpdateAttributesHandler) // 更新属性
	dispatcher.RegisterHandler(10001, 4, LearnSkillHandler)       // 学习技能
	dispatcher.RegisterHandler(10001, 6, UpgradeSkillHandler)     // 升级技能
	dispatcher.RegisterHandler(10001, 8, RecoverFatigueHandler)   // 恢复疲劳值

	// ==================== 背包模块 (Module = 10002) ====================
	dispatcher.RegisterHandler(10002, 0, GetBagHandler)    // 获取背包
	dispatcher.RegisterHandler(10002, 2, UseItemHandler)   // 使用物品
	dispatcher.RegisterHandler(10002, 4, MoveItemHandler)  // 移动物品
	dispatcher.RegisterHandler(10002, 6, SellItemHandler)  // 出售物品
	dispatcher.RegisterHandler(10002, 8, EquipItemHandler) // 装备物品

	// ==================== 副本模块 (Module = 10003) ====================
	dispatcher.RegisterHandler(10003, 0, EnterDungeonHandler) // 进入副本
	dispatcher.RegisterHandler(10003, 2, ExitDungeonHandler)  // 退出副本
	dispatcher.RegisterHandler(10003, 4, ReviveHandler)       // 复活
	dispatcher.RegisterHandler(10003, 6, ChangeRoomHandler)   // 切换房间

	// ==================== 聊天模块 (Module = 10004) ====================
	dispatcher.RegisterHandler(10004, 0, SendChatHandler)        // 发送聊天
	dispatcher.RegisterHandler(10004, 2, GetChatHistoryHandler)  // 获取聊天历史
	dispatcher.RegisterHandler(10004, 200, GetFriendListHandler) // 获取好友列表
	dispatcher.RegisterHandler(10004, 202, AddFriendHandler)     // 添加好友
	dispatcher.RegisterHandler(10004, 206, RemoveFriendHandler)  // 删除好友

	// ==================== 商店模块 (Module = 10005) ====================
	dispatcher.RegisterHandler(10005, 0, GetShopListHandler)       // 获取商店列表
	dispatcher.RegisterHandler(10005, 2, BuyItemHandler)           // 购买物品
	dispatcher.RegisterHandler(10005, 4, SellToShopHandler)        // 出售给商店
	dispatcher.RegisterHandler(10005, 100, SearchAuctionHandler)   // 搜索拍卖行
	dispatcher.RegisterHandler(10005, 102, RegisterAuctionHandler) // 上架拍卖
	dispatcher.RegisterHandler(10005, 104, BidAuctionHandler)      // 竞拍
	dispatcher.RegisterHandler(10005, 106, BuyoutAuctionHandler)   // 一口价购买

	// ==================== 任务模块 (Module = 10006) ====================
	dispatcher.RegisterHandler(10006, 0, GetQuestListHandler)   // 获取任务列表
	dispatcher.RegisterHandler(10006, 2, AcceptQuestHandler)    // 接受任务
	dispatcher.RegisterHandler(10006, 4, CompleteQuestHandler)  // 完成任务
	dispatcher.RegisterHandler(10006, 6, GetQuestRewardHandler) // 领取任务奖励
	dispatcher.RegisterHandler(10006, 8, AbandonQuestHandler)   // 放弃任务

	// ==================== 公会模块 (Module = 10007) ====================
	dispatcher.RegisterHandler(10007, 0, GetGuildInfoHandler)       // 获取公会信息
	dispatcher.RegisterHandler(10007, 2, CreateGuildHandler)        // 创建公会
	dispatcher.RegisterHandler(10007, 4, JoinGuildHandler)          // 加入公会
	dispatcher.RegisterHandler(10007, 6, LeaveGuildHandler)         // 离开公会
	dispatcher.RegisterHandler(10007, 8, GuildDonateHandler)        // 公会贡献
	dispatcher.RegisterHandler(10007, 10, GetGuildSkillHandler)     // 获取公会技能
	dispatcher.RegisterHandler(10007, 12, UpgradeGuildSkillHandler) // 升级公会技能

	// ==================== PK 模块 (Module = 10008) ====================
	dispatcher.RegisterHandler(10008, 0, MultiPlayRequestMatchHandler)       // 多人游戏请求匹配
	dispatcher.RegisterHandler(10008, 2, MultiPlayRequestMatchCancelHandler) // 取消多人游戏请求匹配
	dispatcher.RegisterHandler(10008, 4, HistoricSiteNotiHandler)            // 历史遗迹通知
	dispatcher.RegisterHandler(10008, 6, LoadGuildDonationInfoHandler)       // 加载公会捐赠信息
	dispatcher.RegisterHandler(10008, 8, DreamMazeBasicInfoHandler)          // 梦境迷宫基本信息
	dispatcher.RegisterHandler(10008, 10, RaidEntranceCountHandler)          // 副本入场次数
	dispatcher.RegisterHandler(10008, 12, LoadingProgressHandler)            // 加载进度
	dispatcher.RegisterHandler(10008, 14, ReturnToTownAtMultiPlayHandler)    // 返回城镇
	dispatcher.RegisterHandler(10008, 16, CustomGameRoomSettingHandler)      // 自定义游戏房间设置
	dispatcher.RegisterHandler(10008, 20, PvpRecordHandler)                  // PK 记录
	dispatcher.RegisterHandler(10008, 22, PvpRankingHandler)                 // PK 排名
	dispatcher.RegisterHandler(10008, 24, PvpStatsHandler)                   // PK 统计
	dispatcher.RegisterHandler(10008, 26, PvpMatchHistoryHandler)            // PK 匹配历史
	dispatcher.RegisterHandler(10008, 28, PvpSeasonInfoHandler)              // PK 赛季信息
	dispatcher.RegisterHandler(10008, 30, PvpRewardHandler)                  // PK 奖励
	dispatcher.RegisterHandler(10008, 32, PvpDailyResetHandler)              // PK 每日重置
	dispatcher.RegisterHandler(10008, 34, PvpMatchTypesHandler)              // PK 匹配类型
	dispatcher.RegisterHandler(10008, 36, PvpBattleResultHandler)            // PK 战斗结果

	// ==================== 冒险联盟模块 (Module = 17201) ====================
	dispatcher.RegisterHandler(17201, 0, AdventureUnionInfoHandler)               // 冒险联盟信息
	dispatcher.RegisterHandler(17201, 1, AdventureUnionNameChangeHandler)         // 冒险联盟改名
	dispatcher.RegisterHandler(17201, 2, AdventureUnionExpeditionStartHandler)    // 冒险联盟远征开始
	dispatcher.RegisterHandler(17201, 3, AdventureUnionExpeditionCancelHandler)   // 冒险联盟远征取消
	dispatcher.RegisterHandler(17201, 4, AdventureUnionExpeditionRewardHandler)   // 冒险联盟远征奖励
	dispatcher.RegisterHandler(17201, 5, AdventureUnionSubdueInfoHandler)         // 冒险联盟讨伐信息
	dispatcher.RegisterHandler(17201, 6, AdventureUnionSubdueStartHandler)        // 冒险联盟讨伐开始
	dispatcher.RegisterHandler(17201, 7, AdventureUnionSubdueRewardHandler)       // 冒险联盟讨伐奖励
	dispatcher.RegisterHandler(17201, 8, AdventureUnionOpenShareboardSlotHandler) // 冒险联盟展示板槽位开启
	dispatcher.RegisterHandler(17201, 9, AdventureUnionSetShareboardHandler)      // 冒险联盟展示板设置
	dispatcher.RegisterHandler(17201, 11, AdventureReapInfoHandler)               // 冒险奖励信息
	dispatcher.RegisterHandler(17201, 12, AdventureReapRewardHandler)             // 冒险奖励领取
	dispatcher.RegisterHandler(17201, 13, AdventureUnionSearchStartHandler)       // 冒险联盟搜索开始
	dispatcher.RegisterHandler(17201, 15, AdventureUnionCollectionRewardHandler)  // 冒险联盟收藏奖励
	dispatcher.RegisterHandler(17201, 16, AdventureUnionLevelRewardHandler)       // 冒险联盟等级奖励

	// ==================== 组队模块 (Module = 10009) ====================
	dispatcher.RegisterHandler(10009, 0, SearchPartyListHandler)                // 搜索队伍列表
	dispatcher.RegisterHandler(10009, 2, RecommendGroupHandler)                 // 推荐队伍
	dispatcher.RegisterHandler(10009, 4, ControlGroupHandler)                   // 控制队伍
	dispatcher.RegisterHandler(10009, 6, StartMultiPlayHandler)                 // 开始多人游戏
	dispatcher.RegisterHandler(10009, 8, MultiPlaySyncDungeonHandler)           // 同步多人副本
	dispatcher.RegisterHandler(10009, 10, MultiPlayDungeonEnterCompleteHandler) // 多人副本进入完成
	dispatcher.RegisterHandler(10009, 12, PartyLoadingStatusHandler)            // 获取队伍加载状态
	dispatcher.RegisterHandler(10009, 14, ReadyToLockstepHandler)               // 准备锁定步骤
	dispatcher.RegisterHandler(10009, 16, VoteKickOutUserHandler)               // 投票踢出用户
	dispatcher.RegisterHandler(10009, 18, ConnectBattleServerHandler)           // 连接战斗服务器
	dispatcher.RegisterHandler(10009, 20, CheckProhibitedWordHandler)           // 检查禁用词
	dispatcher.RegisterHandler(10009, 22, HalfOpenPartyAcceptHandler)           // 半公开队伍接受
	dispatcher.RegisterHandler(10009, 24, HalfOpenPartyRefuseHandler)           // 半公开队伍拒绝
	dispatcher.RegisterHandler(10009, 26, HalfOpenPartyJoinHandler)             // 半公开队伍加入
	dispatcher.RegisterHandler(10009, 28, PartyDungeonConditionHandler)         // 多人游戏副本条件
	dispatcher.RegisterHandler(10009, 30, MultiPlayStartDungeonHandler)         // 多人游戏开始副本
	dispatcher.RegisterHandler(10009, 32, TargetUserPartyInfoHandler)           // 目标用户队伍信息
}
