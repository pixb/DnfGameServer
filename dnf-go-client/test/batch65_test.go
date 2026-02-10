package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch65Migration 测试第65批迁移的消息类型
func TestBatch65Migration(t *testing.T) {
	t.Run("Gm", testBatch65GmMessages)
	t.Run("Fame", testBatch65FameMessages)
	t.Run("GuildAuction", testBatch65GuildAuctionMessages)
	t.Run("ItemProduction", testBatch65ItemProductionMessages)
	t.Run("Historicsite", testBatch65HistoricsiteMessages)
	t.Run("Achievement", testBatch65AchievementMessages)
	t.Run("ArcadePvp", testBatch65ArcadePvpMessages)
	t.Run("AdventureUnion", testBatch65AdventureUnionMessages)
	t.Run("Party", testBatch65PartyMessages)
	t.Run("Ping", testBatch65PingMessages)
	t.Run("Wedding", testBatch65WeddingMessages)
	t.Run("Currency", testBatch65CurrencyMessages)
	t.Run("GuildCreate", testBatch65GuildCreateMessages)
	t.Run("Creature", testBatch65CreatureMessages)
	t.Run("Voice", testBatch65VoiceMessages)
	t.Run("Phase", testBatch65PhaseMessages)
	t.Run("GuildStructure", testBatch65GuildStructureMessages)
	t.Run("Friend", testBatch65FriendMessages)
	t.Run("Ranking", testBatch65RankingMessages)
	t.Run("Auction", testBatch65AuctionMessages)
	t.Run("Blacklist", testBatch65BlacklistMessages)
	t.Run("AllClear", testBatch65AllClearMessages)
	t.Run("GuildRedpacket", testBatch65GuildRedpacketMessages)
	t.Run("InviteRoom", testBatch65InviteRoomMessages)
	t.Run("Skill", testBatch65SkillMessages)
	t.Run("Intrude", testBatch65IntrudeMessages)
	t.Run("BattleServer", testBatch65BattleServerMessages)
	t.Run("Adventurebook", testBatch65AdventurebookMessages)
	t.Run("BoardGame", testBatch65BoardGameMessages)
	t.Run("NpcShop", testBatch65NpcShopMessages)
	t.Run("Divorce", testBatch65DivorceMessages)
	t.Run("GuildBingo", testBatch65GuildBingoMessages)
	t.Run("ServerResponse", testBatch65ServerResponseMessages)
	t.Run("SecedeGuild", testBatch65SecedeGuildMessages)
	t.Run("SaveWeakServer", testBatch65SaveWeakServerMessages)
	t.Run("SendStorage", testBatch65SendStorageMessages)
	t.Run("SelectDungeon", testBatch65SelectDungeonMessages)
	t.Run("SendingInvite", testBatch65SendingInviteMessages)
	t.Run("ReplyProposal", testBatch65ReplyProposalMessages)
	t.Run("PvpControl", testBatch65PvpControlMessages)
	t.Run("ReturnToTown", testBatch65ReturnToTownMessages)
	t.Run("LockstepRoom", testBatch65LockstepRoomMessages)
	t.Run("StartDungeon", testBatch65StartDungeonMessages)
	t.Run("SyncDungeon", testBatch65SyncDungeonMessages)
	t.Run("UpdateDungeon", testBatch65UpdateDungeonMessages)
	t.Run("RequestFriend", testBatch65RequestFriendMessages)
	t.Run("RewardQuest", testBatch65RewardQuestMessages)
	t.Run("Mannequin", testBatch65MannequinMessages)
	t.Run("TssData", testBatch65TssDataMessages)
}

// 测试GM相关消息
func testBatch65GmMessages(t *testing.T) {
	reqGmCommand := &dnfv1.ReqGmCommand{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGmCommand)
	assert.Equal(t, int32(0), reqGmCommand.Error)
	assert.Equal(t, int32(1), reqGmCommand.Index)
}

// 测试名声相关消息
func testBatch65FameMessages(t *testing.T) {
	resFameAndCharmPoint := &dnfv1.ResFameAndCharmPoint{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resFameAndCharmPoint)
	assert.Equal(t, int32(0), resFameAndCharmPoint.Error)
	assert.Equal(t, int32(1), resFameAndCharmPoint.Index)
}

// 测试公会拍卖相关消息
func testBatch65GuildAuctionMessages(t *testing.T) {
	resGuildAuctionRewardInfo := &dnfv1.ResGuildAuctionRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildAuctionRewardInfo)
	assert.Equal(t, int32(0), resGuildAuctionRewardInfo.Error)
	assert.Equal(t, int32(1), resGuildAuctionRewardInfo.Index)
}

// 测试物品生产相关消息
func testBatch65ItemProductionMessages(t *testing.T) {
	reqItemProductionCancel := &dnfv1.ReqItemProductionCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemProductionCancel)
	assert.Equal(t, int32(0), reqItemProductionCancel.Error)
	assert.Equal(t, int32(1), reqItemProductionCancel.Index)
}

// 测试历史站点相关消息
func testBatch65HistoricsiteMessages(t *testing.T) {
	reqHistoricsiteResultRecord := &dnfv1.ReqHistoricsiteResultRecord{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteResultRecord)
	assert.Equal(t, int32(0), reqHistoricsiteResultRecord.Error)
	assert.Equal(t, int32(1), reqHistoricsiteResultRecord.Index)

	resHistoricsiteStaff := &dnfv1.ResHistoricsiteStaff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteStaff)
	assert.Equal(t, int32(0), resHistoricsiteStaff.Error)
	assert.Equal(t, int32(1), resHistoricsiteStaff.Index)
}

// 测试成就相关消息
func testBatch65AchievementMessages(t *testing.T) {
	resAchievementBonusReward := &dnfv1.ResAchievementBonusReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAchievementBonusReward)
	assert.Equal(t, int32(0), resAchievementBonusReward.Error)
	assert.Equal(t, int32(1), resAchievementBonusReward.Index)
}

// 测试街机PVP相关消息
func testBatch65ArcadePvpMessages(t *testing.T) {
	resArcadePvpRewardInfo := &dnfv1.ResArcadePvpRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resArcadePvpRewardInfo)
	assert.Equal(t, int32(0), resArcadePvpRewardInfo.Error)
	assert.Equal(t, int32(1), resArcadePvpRewardInfo.Index)
}

// 测试冒险联盟相关消息
func testBatch65AdventureUnionMessages(t *testing.T) {
	reqAdventureUnionExpeditionCancel := &dnfv1.ReqAdventureUnionExpeditionCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionExpeditionCancel)
	assert.Equal(t, int32(0), reqAdventureUnionExpeditionCancel.Error)
	assert.Equal(t, int32(1), reqAdventureUnionExpeditionCancel.Index)

	reqAdventureUnionSubdueStart := &dnfv1.ReqAdventureUnionSubdueStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionSubdueStart)
	assert.Equal(t, int32(0), reqAdventureUnionSubdueStart.Error)
	assert.Equal(t, int32(1), reqAdventureUnionSubdueStart.Index)
}

// 测试队伍相关消息
func testBatch65PartyMessages(t *testing.T) {
	resPartyKickMember := &dnfv1.ResPartyKickMember{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPartyKickMember)
	assert.Equal(t, int32(0), resPartyKickMember.Error)
	assert.Equal(t, int32(1), resPartyKickMember.Index)

	resSuggestMoveParty := &dnfv1.ResSuggestMoveParty{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSuggestMoveParty)
	assert.Equal(t, int32(0), resSuggestMoveParty.Error)
	assert.Equal(t, int32(1), resSuggestMoveParty.Index)

	resPartyLeave := &dnfv1.ResPartyLeave{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPartyLeave)
	assert.Equal(t, int32(0), resPartyLeave.Error)
	assert.Equal(t, int32(1), resPartyLeave.Index)
}

// 测试Ping相关消息
func testBatch65PingMessages(t *testing.T) {
	pingRes := &dnfv1.PingRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, pingRes)
	assert.Equal(t, int32(0), pingRes.Error)
	assert.Equal(t, int32(1), pingRes.Index)

	heartbeat := &dnfv1.Heartbeat{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, heartbeat)
	assert.Equal(t, int32(0), heartbeat.Error)
	assert.Equal(t, int32(1), heartbeat.Index)
}

// 测试婚礼相关消息
func testBatch65WeddingMessages(t *testing.T) {
	resLoadWeddingAttendanceList := &dnfv1.ResLoadWeddingAttendanceList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLoadWeddingAttendanceList)
	assert.Equal(t, int32(0), resLoadWeddingAttendanceList.Error)
	assert.Equal(t, int32(1), resLoadWeddingAttendanceList.Index)

	ptWeddingGuestbook := &dnfv1.PtWeddingGuestbook{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptWeddingGuestbook)
	assert.Equal(t, int32(0), ptWeddingGuestbook.Error)
	assert.Equal(t, int32(1), ptWeddingGuestbook.Index)
}

// 测试货币相关消息
func testBatch65CurrencyMessages(t *testing.T) {
	ptCurrencyRewardInfo := &dnfv1.PtCurrencyRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCurrencyRewardInfo)
	assert.Equal(t, int32(0), ptCurrencyRewardInfo.Error)
	assert.Equal(t, int32(1), ptCurrencyRewardInfo.Index)
}

// 测试公会创建相关消息
func testBatch65GuildCreateMessages(t *testing.T) {
	reqCreateGuild := &dnfv1.ReqCreateGuild{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCreateGuild)
	assert.Equal(t, int32(0), reqCreateGuild.Error)
	assert.Equal(t, int32(1), reqCreateGuild.Index)
}

// 测试生物相关消息
func testBatch65CreatureMessages(t *testing.T) {
	resCreatureErrandReward := &dnfv1.ResCreatureErrandReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureErrandReward)
	assert.Equal(t, int32(0), resCreatureErrandReward.Error)
	assert.Equal(t, int32(1), resCreatureErrandReward.Index)

	reqCreatureCommunionActive := &dnfv1.ReqCreatureCommunionActive{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCreatureCommunionActive)
	assert.Equal(t, int32(0), reqCreatureCommunionActive.Error)
	assert.Equal(t, int32(1), reqCreatureCommunionActive.Index)
}

// 测试语音相关消息
func testBatch65VoiceMessages(t *testing.T) {
	ptGvoiceMemberInfo := &dnfv1.PtGvoiceMemberInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGvoiceMemberInfo)
	assert.Equal(t, int32(0), ptGvoiceMemberInfo.Error)
	assert.Equal(t, int32(1), ptGvoiceMemberInfo.Index)
}

// 测试阶段相关消息
func testBatch65PhaseMessages(t *testing.T) {
	ptPhaseInfo := &dnfv1.PtPhaseInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPhaseInfo)
	assert.Equal(t, int32(0), ptPhaseInfo.Error)
	assert.Equal(t, int32(1), ptPhaseInfo.Index)
}

// 测试公会结构相关消息
func testBatch65GuildStructureMessages(t *testing.T) {
	notifyGuildStructureList := &dnfv1.NotifyGuildStructureList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyGuildStructureList)
	assert.Equal(t, int32(0), notifyGuildStructureList.Error)
	assert.Equal(t, int32(1), notifyGuildStructureList.Index)
}

// 测试好友相关消息
func testBatch65FriendMessages(t *testing.T) {
	reqRequestFriendInvite := &dnfv1.ReqRequestFriendInvite{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRequestFriendInvite)
	assert.Equal(t, int32(0), reqRequestFriendInvite.Error)
	assert.Equal(t, int32(1), reqRequestFriendInvite.Index)
}

// 测试排名相关消息
func testBatch65RankingMessages(t *testing.T) {
	ptRankingGroup := &dnfv1.PtRankingGroup{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRankingGroup)
	assert.Equal(t, int32(0), ptRankingGroup.Error)
	assert.Equal(t, int32(1), ptRankingGroup.Index)
}

// 测试拍卖相关消息
func testBatch65AuctionMessages(t *testing.T) {
	reqAuctionRegisterCancel := &dnfv1.ReqAuctionRegisterCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAuctionRegisterCancel)
	assert.Equal(t, int32(0), reqAuctionRegisterCancel.Error)
	assert.Equal(t, int32(1), reqAuctionRegisterCancel.Index)
}

// 测试黑名单相关消息
func testBatch65BlacklistMessages(t *testing.T) {
	resLoadBlacklist := &dnfv1.ResLoadBlacklist{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLoadBlacklist)
	assert.Equal(t, int32(0), resLoadBlacklist.Error)
	assert.Equal(t, int32(1), resLoadBlacklist.Index)
}

// 测试全部清除相关消息
func testBatch65AllClearMessages(t *testing.T) {
	resAllClear := &dnfv1.ResAllClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAllClear)
	assert.Equal(t, int32(0), resAllClear.Error)
	assert.Equal(t, int32(1), resAllClear.Index)
}

// 测试公会红包相关消息
func testBatch65GuildRedpacketMessages(t *testing.T) {
	resGuildRedpacketInfo := &dnfv1.ResGuildRedpacketInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildRedpacketInfo)
	assert.Equal(t, int32(0), resGuildRedpacketInfo.Error)
	assert.Equal(t, int32(1), resGuildRedpacketInfo.Index)
}

// 测试邀请房间相关消息
func testBatch65InviteRoomMessages(t *testing.T) {
	ptInviteRoomUserlist := &dnfv1.PtInviteRoomUserlist{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptInviteRoomUserlist)
	assert.Equal(t, int32(0), ptInviteRoomUserlist.Error)
	assert.Equal(t, int32(1), ptInviteRoomUserlist.Index)
}

// 测试技能相关消息
func testBatch65SkillMessages(t *testing.T) {
	reqSkillSlot := &dnfv1.ReqSkillSlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqSkillSlot)
	assert.Equal(t, int32(0), reqSkillSlot.Error)
	assert.Equal(t, int32(1), reqSkillSlot.Index)
}

// 测试入侵相关消息
func testBatch65IntrudeMessages(t *testing.T) {
	ptIntrudeMemberInfo := &dnfv1.PtIntrudeMemberInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptIntrudeMemberInfo)
	assert.Equal(t, int32(0), ptIntrudeMemberInfo.Error)
	assert.Equal(t, int32(1), ptIntrudeMemberInfo.Index)
}

// 测试战斗服务器相关消息
func testBatch65BattleServerMessages(t *testing.T) {
	reqConnectBattleServer := &dnfv1.ReqConnectBattleServer{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqConnectBattleServer)
	assert.Equal(t, int32(0), reqConnectBattleServer.Error)
	assert.Equal(t, int32(1), reqConnectBattleServer.Index)
}

// 测试冒险手册相关消息
func testBatch65AdventurebookMessages(t *testing.T) {
	notifyAdventurebookReward := &dnfv1.NotifyAdventurebookReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyAdventurebookReward)
	assert.Equal(t, int32(0), notifyAdventurebookReward.Error)
	assert.Equal(t, int32(1), notifyAdventurebookReward.Index)
}

// 测试棋盘游戏相关消息
func testBatch65BoardGameMessages(t *testing.T) {
	resBoardGameInfo := &dnfv1.ResBoardGameInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBoardGameInfo)
	assert.Equal(t, int32(0), resBoardGameInfo.Error)
	assert.Equal(t, int32(1), resBoardGameInfo.Index)
}

// 测试NPC商店相关消息
func testBatch65NpcShopMessages(t *testing.T) {
	reqNpcShopItemSell := &dnfv1.ReqNpcShopItemSell{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqNpcShopItemSell)
	assert.Equal(t, int32(0), reqNpcShopItemSell.Error)
	assert.Equal(t, int32(1), reqNpcShopItemSell.Index)
}

// 测试离婚相关消息
func testBatch65DivorceMessages(t *testing.T) {
	enumDivorceReplyType := &dnfv1.EnumDivorceReplyType{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumDivorceReplyType)
	assert.Equal(t, int32(0), enumDivorceReplyType.Error)
	assert.Equal(t, int32(1), enumDivorceReplyType.Index)
}

// 测试公会宾果相关消息
func testBatch65GuildBingoMessages(t *testing.T) {
	resGuildBingoLoadDetail := &dnfv1.ResGuildBingoLoadDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildBingoLoadDetail)
	assert.Equal(t, int32(0), resGuildBingoLoadDetail.Error)
	assert.Equal(t, int32(1), resGuildBingoLoadDetail.Index)
}

// 测试服务器响应相关消息
func testBatch65ServerResponseMessages(t *testing.T) {
	resServerResponsePacketSupplement := &dnfv1.ResServerResponsePacketSupplement{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resServerResponsePacketSupplement)
	assert.Equal(t, int32(0), resServerResponsePacketSupplement.Error)
	assert.Equal(t, int32(1), resServerResponsePacketSupplement.Index)
}

// 测试退出公会相关消息
func testBatch65SecedeGuildMessages(t *testing.T) {
	resSecedeGuildDetail := &dnfv1.ResSecedeGuildDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSecedeGuildDetail)
	assert.Equal(t, int32(0), resSecedeGuildDetail.Error)
	assert.Equal(t, int32(1), resSecedeGuildDetail.Index)
}

// 测试保存弱服务器相关消息
func testBatch65SaveWeakServerMessages(t *testing.T) {
	resSaveWeakServerFullData := &dnfv1.ResSaveWeakServerFullData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSaveWeakServerFullData)
	assert.Equal(t, int32(0), resSaveWeakServerFullData.Error)
	assert.Equal(t, int32(1), resSaveWeakServerFullData.Index)
}

// 测试发送仓库相关消息
func testBatch65SendStorageMessages(t *testing.T) {
	resSendStorageComplete := &dnfv1.ResSendStorageComplete{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSendStorageComplete)
	assert.Equal(t, int32(0), resSendStorageComplete.Error)
	assert.Equal(t, int32(1), resSendStorageComplete.Index)
}

// 测试选择副本相关消息
func testBatch65SelectDungeonMessages(t *testing.T) {
	resSelectOtherDungeonAtMultiDetail := &dnfv1.ResSelectOtherDungeonAtMultiDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSelectOtherDungeonAtMultiDetail)
	assert.Equal(t, int32(0), resSelectOtherDungeonAtMultiDetail.Error)
	assert.Equal(t, int32(1), resSelectOtherDungeonAtMultiDetail.Index)
}

// 测试发送邀请相关消息
func testBatch65SendingInviteMessages(t *testing.T) {
	resSendingInviteFriendListFull := &dnfv1.ResSendingInviteFriendListFull{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSendingInviteFriendListFull)
	assert.Equal(t, int32(0), resSendingInviteFriendListFull.Error)
	assert.Equal(t, int32(1), resSendingInviteFriendListFull.Index)
}

// 测试回复提案相关消息
func testBatch65ReplyProposalMessages(t *testing.T) {
	resReplyProposalDetail := &dnfv1.ResReplyProposalDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resReplyProposalDetail)
	assert.Equal(t, int32(0), resReplyProposalDetail.Error)
	assert.Equal(t, int32(1), resReplyProposalDetail.Index)
}

// 测试PVP控制相关消息
func testBatch65PvpControlMessages(t *testing.T) {
	resSetPvpControlModeFull := &dnfv1.ResSetPvpControlModeFull{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSetPvpControlModeFull)
	assert.Equal(t, int32(0), resSetPvpControlModeFull.Error)
	assert.Equal(t, int32(1), resSetPvpControlModeFull.Index)
}

// 测试返回城镇相关消息
func testBatch65ReturnToTownMessages(t *testing.T) {
	resReturnToTownAtMultiPlayDetail := &dnfv1.ResReturnToTownAtMultiPlayDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resReturnToTownAtMultiPlayDetail)
	assert.Equal(t, int32(0), resReturnToTownAtMultiPlayDetail.Error)
	assert.Equal(t, int32(1), resReturnToTownAtMultiPlayDetail.Index)
}

// 测试锁步房间相关消息
func testBatch65LockstepRoomMessages(t *testing.T) {
	resStartLockstepRoomDetail := &dnfv1.ResStartLockstepRoomDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartLockstepRoomDetail)
	assert.Equal(t, int32(0), resStartLockstepRoomDetail.Error)
	assert.Equal(t, int32(1), resStartLockstepRoomDetail.Index)
}

// 测试开始副本相关消息
func testBatch65StartDungeonMessages(t *testing.T) {
	resStartDungeonPrepare := &dnfv1.ResStartDungeonPrepare{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartDungeonPrepare)
	assert.Equal(t, int32(0), resStartDungeonPrepare.Error)
	assert.Equal(t, int32(1), resStartDungeonPrepare.Index)

	resStartDungeonCompleteDetail := &dnfv1.ResStartDungeonCompleteDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartDungeonCompleteDetail)
	assert.Equal(t, int32(0), resStartDungeonCompleteDetail.Error)
	assert.Equal(t, int32(1), resStartDungeonCompleteDetail.Index)
}

// 测试同步副本相关消息
func testBatch65SyncDungeonMessages(t *testing.T) {
	resSyncDungeonStartTimeFull := &dnfv1.ResSyncDungeonStartTimeFull{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSyncDungeonStartTimeFull)
	assert.Equal(t, int32(0), resSyncDungeonStartTimeFull.Error)
	assert.Equal(t, int32(1), resSyncDungeonStartTimeFull.Index)
}

// 测试更新副本相关消息
func testBatch65UpdateDungeonMessages(t *testing.T) {
	resUpdateDungeonClearConditionGetItemDetail := &dnfv1.ResUpdateDungeonClearConditionGetItemDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resUpdateDungeonClearConditionGetItemDetail)
	assert.Equal(t, int32(0), resUpdateDungeonClearConditionGetItemDetail.Error)
	assert.Equal(t, int32(1), resUpdateDungeonClearConditionGetItemDetail.Index)
}

// 测试请求好友相关消息
func testBatch65RequestFriendMessages(t *testing.T) {
	resRequestFriendInviteDetail := &dnfv1.ResRequestFriendInviteDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRequestFriendInviteDetail)
	assert.Equal(t, int32(0), resRequestFriendInviteDetail.Error)
	assert.Equal(t, int32(1), resRequestFriendInviteDetail.Index)
}

// 测试奖励任务相关消息
func testBatch65RewardQuestMessages(t *testing.T) {
	resRewardQuestFull := &dnfv1.ResRewardQuestFull{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRewardQuestFull)
	assert.Equal(t, int32(0), resRewardQuestFull.Error)
	assert.Equal(t, int32(1), resRewardQuestFull.Index)
}

// 测试人体模型相关消息
func testBatch65MannequinMessages(t *testing.T) {
	resSetAppendageMannequinDetail := &dnfv1.ResSetAppendageMannequinDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSetAppendageMannequinDetail)
	assert.Equal(t, int32(0), resSetAppendageMannequinDetail.Error)
	assert.Equal(t, int32(1), resSetAppendageMannequinDetail.Index)

	resSetNameMannequinFull := &dnfv1.ResSetNameMannequinFull{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSetNameMannequinFull)
	assert.Equal(t, int32(0), resSetNameMannequinFull.Error)
	assert.Equal(t, int32(1), resSetNameMannequinFull.Index)
}

// 测试TSS数据相关消息
func testBatch65TssDataMessages(t *testing.T) {
	resTssDataComplete := &dnfv1.ResTssDataComplete{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTssDataComplete)
	assert.Equal(t, int32(0), resTssDataComplete.Error)
	assert.Equal(t, int32(1), resTssDataComplete.Index)
}
