package handlers

import (
	"dnf-go-server/internal/network"
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
}
