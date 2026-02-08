package network

import (
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// RegisterAllMessages 注册所有protobuf消息类型
// 对应Java的@MessageMeta注解，将module+cmd映射到proto消息
func (c *ProtoCodec) RegisterAllMessages() {
	// ==================== 认证模块 (Module = 10000) ====================
	// 登录请求/响应 (cmd=0)
	c.RegisterMessage(10000, 0, func() proto.Message { return &dnfv1.LoginRequest{} })
	c.RegisterMessage(10000, 1, func() proto.Message { return &dnfv1.LoginResponse{} })

	// 创建角色 (cmd=1)
	c.RegisterMessage(10000, 2, func() proto.Message { return &dnfv1.CreateCharacterRequest{} })
	c.RegisterMessage(10000, 3, func() proto.Message { return &dnfv1.CreateCharacterResponse{} })

	// 角色列表 (cmd=2)
	c.RegisterMessage(10000, 4, func() proto.Message { return &dnfv1.CharacterListRequest{} })
	c.RegisterMessage(10000, 5, func() proto.Message { return &dnfv1.CharacterListResponse{} })

	// 选择角色 (cmd=3)
	c.RegisterMessage(10000, 6, func() proto.Message { return &dnfv1.SelectCharacterRequest{} })
	c.RegisterMessage(10000, 7, func() proto.Message { return &dnfv1.SelectCharacterResponse{} })

	// ==================== 角色模块 (Module = 10001) ====================
	// 获取角色信息 (cmd=0)
	c.RegisterMessage(10001, 0, func() proto.Message { return &dnfv1.GetRoleInfoRequest{} })
	c.RegisterMessage(10001, 1, func() proto.Message { return &dnfv1.GetRoleInfoResponse{} })

	// 更新属性 (cmd=1)
	c.RegisterMessage(10001, 2, func() proto.Message { return &dnfv1.UpdateAttributesRequest{} })
	c.RegisterMessage(10001, 3, func() proto.Message { return &dnfv1.UpdateAttributesResponse{} })

	// 学习技能 (cmd=2)
	c.RegisterMessage(10001, 4, func() proto.Message { return &dnfv1.LearnSkillRequest{} })
	c.RegisterMessage(10001, 5, func() proto.Message { return &dnfv1.LearnSkillResponse{} })

	// 升级技能 (cmd=3)
	c.RegisterMessage(10001, 6, func() proto.Message { return &dnfv1.UpgradeSkillRequest{} })
	c.RegisterMessage(10001, 7, func() proto.Message { return &dnfv1.UpgradeSkillResponse{} })

	// 恢复疲劳值 (cmd=4)
	c.RegisterMessage(10001, 8, func() proto.Message { return &dnfv1.RecoverFatigueRequest{} })
	c.RegisterMessage(10001, 9, func() proto.Message { return &dnfv1.RecoverFatigueResponse{} })

	// 角色升级通知 (cmd=100)
	c.RegisterMessage(10001, 100, func() proto.Message { return &dnfv1.RoleLevelUpNotify{} })

	// ==================== 背包模块 (Module = 10002) ====================
	// 获取背包 (cmd=0)
	c.RegisterMessage(10002, 0, func() proto.Message { return &dnfv1.GetBagRequest{} })
	c.RegisterMessage(10002, 1, func() proto.Message { return &dnfv1.GetBagResponse{} })

	// 使用物品 (cmd=1)
	c.RegisterMessage(10002, 2, func() proto.Message { return &dnfv1.UseItemRequest{} })
	c.RegisterMessage(10002, 3, func() proto.Message { return &dnfv1.UseItemResponse{} })

	// 移动物品 (cmd=2)
	c.RegisterMessage(10002, 4, func() proto.Message { return &dnfv1.MoveItemRequest{} })
	c.RegisterMessage(10002, 5, func() proto.Message { return &dnfv1.MoveItemResponse{} })

	// 出售物品 (cmd=3)
	c.RegisterMessage(10002, 6, func() proto.Message { return &dnfv1.SellItemRequest{} })
	c.RegisterMessage(10002, 7, func() proto.Message { return &dnfv1.SellItemResponse{} })

	// 装备物品 (cmd=4)
	c.RegisterMessage(10002, 8, func() proto.Message { return &dnfv1.EquipItemRequest{} })
	c.RegisterMessage(10002, 9, func() proto.Message { return &dnfv1.EquipItemResponse{} })

	// ==================== 副本模块 (Module = 10003) ====================
	// 进入副本 (cmd=0)
	c.RegisterMessage(10003, 0, func() proto.Message { return &dnfv1.EnterDungeonRequest{} })
	c.RegisterMessage(10003, 1, func() proto.Message { return &dnfv1.EnterDungeonResponse{} })

	// 退出副本 (cmd=1)
	c.RegisterMessage(10003, 2, func() proto.Message { return &dnfv1.ExitDungeonRequest{} })
	c.RegisterMessage(10003, 3, func() proto.Message { return &dnfv1.ExitDungeonResponse{} })

	// 复活 (cmd=2)
	c.RegisterMessage(10003, 4, func() proto.Message { return &dnfv1.ReviveRequest{} })
	c.RegisterMessage(10003, 5, func() proto.Message { return &dnfv1.ReviveResponse{} })

	// 切换房间 (cmd=3)
	c.RegisterMessage(10003, 6, func() proto.Message { return &dnfv1.ChangeRoomRequest{} })
	c.RegisterMessage(10003, 7, func() proto.Message { return &dnfv1.ChangeRoomResponse{} })

	// 怪物击杀通知 (cmd=100)
	c.RegisterMessage(10003, 100, func() proto.Message { return &dnfv1.MonsterKillNotify{} })

	// BOSS击杀通知 (cmd=101)
	c.RegisterMessage(10003, 101, func() proto.Message { return &dnfv1.BossKillNotify{} })

	// 副本完成通知 (cmd=102)
	c.RegisterMessage(10003, 102, func() proto.Message { return &dnfv1.DungeonCompleteNotify{} })

	// ==================== 聊天模块 (Module = 10004) ====================
	// 发送聊天 (cmd=0)
	c.RegisterMessage(10004, 0, func() proto.Message { return &dnfv1.SendChatRequest{} })
	c.RegisterMessage(10004, 1, func() proto.Message { return &dnfv1.SendChatResponse{} })

	// 获取聊天历史 (cmd=1)
	c.RegisterMessage(10004, 2, func() proto.Message { return &dnfv1.GetChatHistoryRequest{} })
	c.RegisterMessage(10004, 3, func() proto.Message { return &dnfv1.GetChatHistoryResponse{} })

	// 接收聊天通知 (cmd=100)
	c.RegisterMessage(10004, 100, func() proto.Message { return &dnfv1.ReceiveChatNotify{} })

	// 系统消息 (cmd=101)
	c.RegisterMessage(10004, 101, func() proto.Message { return &dnfv1.SystemMessage{} })

	// 广播通知 (cmd=102)
	c.RegisterMessage(10004, 102, func() proto.Message { return &dnfv1.BroadcastNotify{} })

	// 获取好友列表 (cmd=200)
	c.RegisterMessage(10004, 200, func() proto.Message { return &dnfv1.GetFriendListRequest{} })
	c.RegisterMessage(10004, 201, func() proto.Message { return &dnfv1.GetFriendListResponse{} })

	// 添加好友 (cmd=202)
	c.RegisterMessage(10004, 202, func() proto.Message { return &dnfv1.AddFriendRequest{} })
	c.RegisterMessage(10004, 203, func() proto.Message { return &dnfv1.AddFriendResponse{} })

	// 好友申请通知 (cmd=204)
	c.RegisterMessage(10004, 204, func() proto.Message { return &dnfv1.FriendRequestNotify{} })

	// 回复好友申请 (cmd=205)
	c.RegisterMessage(10004, 205, func() proto.Message { return &dnfv1.ReplyFriendRequestMessage{} })

	// 删除好友 (cmd=206)
	c.RegisterMessage(10004, 206, func() proto.Message { return &dnfv1.RemoveFriendRequest{} })
	c.RegisterMessage(10004, 207, func() proto.Message { return &dnfv1.RemoveFriendResponse{} })

	// 好友上线通知 (cmd=208)
	c.RegisterMessage(10004, 208, func() proto.Message { return &dnfv1.FriendOnlineNotify{} })

	// ==================== 商店模块 (Module = 10005) ====================
	// 获取商店列表 (cmd=0)
	c.RegisterMessage(10005, 0, func() proto.Message { return &dnfv1.GetShopListRequest{} })
	c.RegisterMessage(10005, 1, func() proto.Message { return &dnfv1.GetShopListResponse{} })

	// 购买物品 (cmd=1)
	c.RegisterMessage(10005, 2, func() proto.Message { return &dnfv1.BuyItemRequest{} })
	c.RegisterMessage(10005, 3, func() proto.Message { return &dnfv1.BuyItemResponse{} })

	// 出售给商店 (cmd=2)
	c.RegisterMessage(10005, 4, func() proto.Message { return &dnfv1.SellToShopRequest{} })
	c.RegisterMessage(10005, 5, func() proto.Message { return &dnfv1.SellToShopResponse{} })

	// 搜索拍卖行 (cmd=100)
	c.RegisterMessage(10005, 100, func() proto.Message { return &dnfv1.SearchAuctionRequest{} })
	c.RegisterMessage(10005, 101, func() proto.Message { return &dnfv1.SearchAuctionResponse{} })

	// 上架拍卖 (cmd=102)
	c.RegisterMessage(10005, 102, func() proto.Message { return &dnfv1.RegisterAuctionRequest{} })
	c.RegisterMessage(10005, 103, func() proto.Message { return &dnfv1.RegisterAuctionResponse{} })

	// 竞拍 (cmd=104)
	c.RegisterMessage(10005, 104, func() proto.Message { return &dnfv1.BidAuctionRequest{} })
	c.RegisterMessage(10005, 105, func() proto.Message { return &dnfv1.BidAuctionResponse{} })

	// 一口价购买 (cmd=106)
	c.RegisterMessage(10005, 106, func() proto.Message { return &dnfv1.BuyoutAuctionRequest{} })
	c.RegisterMessage(10005, 107, func() proto.Message { return &dnfv1.BuyoutAuctionResponse{} })

	// 开设个人商店 (cmd=200)
	c.RegisterMessage(10005, 200, func() proto.Message { return &dnfv1.OpenPrivateStoreRequest{} })
	c.RegisterMessage(10005, 201, func() proto.Message { return &dnfv1.OpenPrivateStoreResponse{} })

	// 查看个人商店 (cmd=202)
	c.RegisterMessage(10005, 202, func() proto.Message { return &dnfv1.ViewPrivateStoreRequest{} })
	c.RegisterMessage(10005, 203, func() proto.Message { return &dnfv1.ViewPrivateStoreResponse{} })

	// 从个人商店购买 (cmd=204)
	c.RegisterMessage(10005, 204, func() proto.Message { return &dnfv1.BuyFromPrivateStoreRequest{} })
	c.RegisterMessage(10005, 205, func() proto.Message { return &dnfv1.BuyFromPrivateStoreResponse{} })

	// ==================== 任务模块 (Module = 10006) ====================
	// 获取任务列表 (cmd=0)
	c.RegisterMessage(10006, 0, func() proto.Message { return &dnfv1.GetQuestListRequest{} })
	c.RegisterMessage(10006, 1, func() proto.Message { return &dnfv1.GetQuestListResponse{} })

	// 接受任务 (cmd=1)
	c.RegisterMessage(10006, 2, func() proto.Message { return &dnfv1.AcceptQuestRequest{} })
	c.RegisterMessage(10006, 3, func() proto.Message { return &dnfv1.AcceptQuestResponse{} })

	// 完成任务 (cmd=2)
	c.RegisterMessage(10006, 4, func() proto.Message { return &dnfv1.CompleteQuestRequest{} })
	c.RegisterMessage(10006, 5, func() proto.Message { return &dnfv1.CompleteQuestResponse{} })

	// 领取任务奖励 (cmd=3)
	c.RegisterMessage(10006, 6, func() proto.Message { return &dnfv1.GetQuestRewardRequest{} })
	c.RegisterMessage(10006, 7, func() proto.Message { return &dnfv1.GetQuestRewardResponse{} })

	// 放弃任务 (cmd=4)
	c.RegisterMessage(10006, 8, func() proto.Message { return &dnfv1.AbandonQuestRequest{} })
	c.RegisterMessage(10006, 9, func() proto.Message { return &dnfv1.AbandonQuestResponse{} })

	// 任务进度更新通知 (cmd=100)
	c.RegisterMessage(10006, 100, func() proto.Message { return &dnfv1.QuestProgressNotify{} })

	// 任务完成通知 (cmd=101)
	c.RegisterMessage(10006, 101, func() proto.Message { return &dnfv1.QuestCompletedNotify{} })

	// ==================== 公会模块 (Module = 10007) ====================
	// 获取公会信息 (cmd=0)
	c.RegisterMessage(10007, 0, func() proto.Message { return &dnfv1.GetGuildInfoRequest{} })
	c.RegisterMessage(10007, 1, func() proto.Message { return &dnfv1.GetGuildInfoResponse{} })

	// 创建公会 (cmd=1)
	c.RegisterMessage(10007, 2, func() proto.Message { return &dnfv1.CreateGuildRequest{} })
	c.RegisterMessage(10007, 3, func() proto.Message { return &dnfv1.CreateGuildResponse{} })

	// 加入公会 (cmd=2)
	c.RegisterMessage(10007, 4, func() proto.Message { return &dnfv1.JoinGuildRequest{} })
	c.RegisterMessage(10007, 5, func() proto.Message { return &dnfv1.JoinGuildResponse{} })

	// 离开公会 (cmd=3)
	c.RegisterMessage(10007, 6, func() proto.Message { return &dnfv1.LeaveGuildRequest{} })
	c.RegisterMessage(10007, 7, func() proto.Message { return &dnfv1.LeaveGuildResponse{} })

	// 公会捐赠 (cmd=4)
	c.RegisterMessage(10007, 8, func() proto.Message { return &dnfv1.GuildDonateRequest{} })
	c.RegisterMessage(10007, 9, func() proto.Message { return &dnfv1.GuildDonateResponse{} })

	// 获取公会技能 (cmd=5)
	c.RegisterMessage(10007, 10, func() proto.Message { return &dnfv1.GetGuildSkillRequest{} })
	c.RegisterMessage(10007, 11, func() proto.Message { return &dnfv1.GetGuildSkillResponse{} })

	// 升级公会技能 (cmd=6)
	c.RegisterMessage(10007, 12, func() proto.Message { return &dnfv1.UpgradeGuildSkillRequest{} })
	c.RegisterMessage(10007, 13, func() proto.Message { return &dnfv1.UpgradeGuildSkillResponse{} })
}
