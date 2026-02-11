package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch74Migration 测试第74批迁移的消息类型
func TestBatch74Migration(t *testing.T) {
	t.Run("Raid", testBatch74RaidMessages)
	t.Run("Guild", testBatch74GuildMessages)
	t.Run("Contents", testBatch74ContentsMessages)
	t.Run("Storage", testBatch74StorageMessages)
	t.Run("BoardGame", testBatch74BoardGameMessages)
	t.Run("Minigame", testBatch74MinigameMessages)
	t.Run("Historicsite", testBatch74HistoricsiteMessages)
	t.Run("Interaction", testBatch74InteractionMessages)
	t.Run("Friend", testBatch74FriendMessages)
	t.Run("Purchase", testBatch74PurchaseMessages)
	t.Run("Item", testBatch74ItemMessages)
	t.Run("Rank", testBatch74RankMessages)
	t.Run("Messenger", testBatch74MessengerMessages)
	t.Run("Damage", testBatch74DamageMessages)
	t.Run("Wedding", testBatch74WeddingMessages)
	t.Run("World", testBatch74WorldMessages)
	t.Run("Verification", testBatch74VerificationMessages)
	t.Run("Relay", testBatch74RelayMessages)
	t.Run("HiddenChatting", testBatch74HiddenChattingMessages)
	t.Run("Quest", testBatch74QuestMessages)
	t.Run("Achievement", testBatch74AchievementMessages)
	t.Run("Randomoption", testBatch74RandomoptionMessages)
	t.Run("Repurchase", testBatch74RepurchaseMessages)
	t.Run("Reap", testBatch74ReapMessages)
	t.Run("Battleleague", testBatch74BattleleagueMessages)
	t.Run("Note", testBatch74NoteMessages)
	t.Run("Object", testBatch74ObjectMessages)
	t.Run("Cera", testBatch74CeraMessages)
}

// 测试突袭相关消息
func testBatch74RaidMessages(t *testing.T) {
	resRaidUseBuff := &dnfv1.ResRaidUseBuff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRaidUseBuff)
	assert.Equal(t, int32(0), resRaidUseBuff.Error)
	assert.Equal(t, int32(1), resRaidUseBuff.Index)

	ptRaidEntranceCount := &dnfv1.PtRaidEntranceCount{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRaidEntranceCount)
	assert.Equal(t, int32(0), ptRaidEntranceCount.Error)
	assert.Equal(t, int32(1), ptRaidEntranceCount.Index)
}

// 测试公会相关消息
func testBatch74GuildMessages(t *testing.T) {
	resGuildRedpacketInfo := &dnfv1.ResGuildRedpacketInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildRedpacketInfo)
	assert.Equal(t, int32(0), resGuildRedpacketInfo.Error)
	assert.Equal(t, int32(1), resGuildRedpacketInfo.Index)

	resGuildBuyContentBuff := &dnfv1.ResGuildBuyContentBuff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildBuyContentBuff)
	assert.Equal(t, int32(0), resGuildBuyContentBuff.Error)
	assert.Equal(t, int32(1), resGuildBuyContentBuff.Index)

	ptGuildBingoSquareInfo := &dnfv1.PtGuildBingoSquareInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildBingoSquareInfo)
	assert.Equal(t, int32(0), ptGuildBingoSquareInfo.Error)
	assert.Equal(t, int32(1), ptGuildBingoSquareInfo.Index)

	notifyGuildRefreshHistory := &dnfv1.NotifyGuildRefreshHistory{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyGuildRefreshHistory)
	assert.Equal(t, int32(0), notifyGuildRefreshHistory.Error)
	assert.Equal(t, int32(1), notifyGuildRefreshHistory.Index)
}

// 测试内容相关消息
func testBatch74ContentsMessages(t *testing.T) {
	reqContentsPreviewReward := &dnfv1.ReqContentsPreviewReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqContentsPreviewReward)
	assert.Equal(t, int32(0), reqContentsPreviewReward.Error)
	assert.Equal(t, int32(1), reqContentsPreviewReward.Index)
}

// 测试存储相关消息
func testBatch74StorageMessages(t *testing.T) {
	resSendStorage := &dnfv1.ResSendStorage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSendStorage)
	assert.Equal(t, int32(0), resSendStorage.Error)
	assert.Equal(t, int32(1), resSendStorage.Index)
}

// 测试棋盘游戏相关消息
func testBatch74BoardGameMessages(t *testing.T) {
	resBoardGameMessage := &dnfv1.ResBoardGameMessage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBoardGameMessage)
	assert.Equal(t, int32(0), resBoardGameMessage.Error)
	assert.Equal(t, int32(1), resBoardGameMessage.Index)
}

// 测试小游戏相关消息
func testBatch74MinigameMessages(t *testing.T) {
	resMinigameLotteryOpen := &dnfv1.ResMinigameLotteryOpen{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameLotteryOpen)
	assert.Equal(t, int32(0), resMinigameLotteryOpen.Error)
	assert.Equal(t, int32(1), resMinigameLotteryOpen.Index)

	resMinigameLotteryList := &dnfv1.ResMinigameLotteryList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameLotteryList)
	assert.Equal(t, int32(0), resMinigameLotteryList.Error)
	assert.Equal(t, int32(1), resMinigameLotteryList.Index)

	resMinigameStartLotusQuiz := &dnfv1.ResMinigameStartLotusQuiz{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameStartLotusQuiz)
	assert.Equal(t, int32(0), resMinigameStartLotusQuiz.Error)
	assert.Equal(t, int32(1), resMinigameStartLotusQuiz.Index)

	resMinigameClearLotusQuiz := &dnfv1.ResMinigameClearLotusQuiz{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameClearLotusQuiz)
	assert.Equal(t, int32(0), resMinigameClearLotusQuiz.Error)
	assert.Equal(t, int32(1), resMinigameClearLotusQuiz.Index)
}

// 测试历史站点相关消息
func testBatch74HistoricsiteMessages(t *testing.T) {
	resHistoricsiteKeepingRelicList := &dnfv1.ResHistoricsiteKeepingRelicList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteKeepingRelicList)
	assert.Equal(t, int32(0), resHistoricsiteKeepingRelicList.Error)
	assert.Equal(t, int32(1), resHistoricsiteKeepingRelicList.Index)

	resHistoricsiteSetStaff := &dnfv1.ResHistoricsiteSetStaff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteSetStaff)
	assert.Equal(t, int32(0), resHistoricsiteSetStaff.Error)
	assert.Equal(t, int32(1), resHistoricsiteSetStaff.Index)

	reqHistoricsiteCapture := &dnfv1.ReqHistoricsiteCapture{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteCapture)
	assert.Equal(t, int32(0), reqHistoricsiteCapture.Error)
	assert.Equal(t, int32(1), reqHistoricsiteCapture.Index)
}

// 测试交互相关消息
func testBatch74InteractionMessages(t *testing.T) {
	ptInteractionMenu := &dnfv1.PtInteractionMenu{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptInteractionMenu)
	assert.Equal(t, int32(0), ptInteractionMenu.Error)
	assert.Equal(t, int32(1), ptInteractionMenu.Index)
}

// 测试好友相关消息
func testBatch74FriendMessages(t *testing.T) {
	resFriendRecvFatigue := &dnfv1.ResFriendRecvFatigue{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resFriendRecvFatigue)
	assert.Equal(t, int32(0), resFriendRecvFatigue.Error)
	assert.Equal(t, int32(1), resFriendRecvFatigue.Index)
}

// 测试购买相关消息
func testBatch74PurchaseMessages(t *testing.T) {
	reqPurchaseGuildNpc := &dnfv1.ReqPurchaseGuildNpc{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPurchaseGuildNpc)
	assert.Equal(t, int32(0), reqPurchaseGuildNpc.Error)
	assert.Equal(t, int32(1), reqPurchaseGuildNpc.Index)
}

// 测试物品相关消息
func testBatch74ItemMessages(t *testing.T) {
	resItemProductionRegister := &dnfv1.ResItemProductionRegister{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemProductionRegister)
	assert.Equal(t, int32(0), resItemProductionRegister.Error)
	assert.Equal(t, int32(1), resItemProductionRegister.Index)

	resItemAvrPrice := &dnfv1.ResItemAvrPrice{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemAvrPrice)
	assert.Equal(t, int32(0), resItemAvrPrice.Error)
	assert.Equal(t, int32(1), resItemAvrPrice.Index)
}

// 测试排名相关消息
func testBatch74RankMessages(t *testing.T) {
	resRankTrainingStart := &dnfv1.ResRankTrainingStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRankTrainingStart)
	assert.Equal(t, int32(0), resRankTrainingStart.Error)
	assert.Equal(t, int32(1), resRankTrainingStart.Index)
}

// 测试信使相关消息
func testBatch74MessengerMessages(t *testing.T) {
	ptMessengerFriendInfo := &dnfv1.PtMessengerFriendInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMessengerFriendInfo)
	assert.Equal(t, int32(0), ptMessengerFriendInfo.Error)
	assert.Equal(t, int32(1), ptMessengerFriendInfo.Index)
}

// 测试伤害相关消息
func testBatch74DamageMessages(t *testing.T) {
	ptDamageAnalysis := &dnfv1.PtDamageAnalysis{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptDamageAnalysis)
	assert.Equal(t, int32(0), ptDamageAnalysis.Error)
	assert.Equal(t, int32(1), ptDamageAnalysis.Index)
}

// 测试婚礼相关消息
func testBatch74WeddingMessages(t *testing.T) {
	resSendWeddingInvitation := &dnfv1.ResSendWeddingInvitation{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSendWeddingInvitation)
	assert.Equal(t, int32(0), resSendWeddingInvitation.Error)
	assert.Equal(t, int32(1), resSendWeddingInvitation.Index)
}

// 测试世界相关消息
func testBatch74WorldMessages(t *testing.T) {
	ptWorldRaidInfo := &dnfv1.PtWorldRaidInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptWorldRaidInfo)
	assert.Equal(t, int32(0), ptWorldRaidInfo.Error)
	assert.Equal(t, int32(1), ptWorldRaidInfo.Index)
}

// 测试验证相关消息
func testBatch74VerificationMessages(t *testing.T) {
	reqVerificationData := &dnfv1.ReqVerificationData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqVerificationData)
	assert.Equal(t, int32(0), reqVerificationData.Error)
	assert.Equal(t, int32(1), reqVerificationData.Index)
}

// 测试中继相关消息
func testBatch74RelayMessages(t *testing.T) {
	ptRelayPvpRoundUserInfo := &dnfv1.PtRelayPvpRoundUserInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRelayPvpRoundUserInfo)
	assert.Equal(t, int32(0), ptRelayPvpRoundUserInfo.Error)
	assert.Equal(t, int32(1), ptRelayPvpRoundUserInfo.Index)
}

// 测试隐藏聊天相关消息
func testBatch74HiddenChattingMessages(t *testing.T) {
	resHiddenChattingLoad := &dnfv1.ResHiddenChattingLoad{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHiddenChattingLoad)
	assert.Equal(t, int32(0), resHiddenChattingLoad.Error)
	assert.Equal(t, int32(1), resHiddenChattingLoad.Index)
}

// 测试任务相关消息
func testBatch74QuestMessages(t *testing.T) {
	ptQuest := &dnfv1.PtQuest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptQuest)
	assert.Equal(t, int32(0), ptQuest.Error)
	assert.Equal(t, int32(1), ptQuest.Index)
}

// 测试成就相关消息
func testBatch74AchievementMessages(t *testing.T) {
	achievementBonusRewardRequest := &dnfv1.AchievementBonusRewardRequest{
		CharacterId: 100,
		BonusId:     200,
	}
	assert.NotNil(t, achievementBonusRewardRequest)
	assert.Equal(t, int32(100), achievementBonusRewardRequest.CharacterId)
	assert.Equal(t, int32(200), achievementBonusRewardRequest.BonusId)
}

// 测试随机选项相关消息
func testBatch74RandomoptionMessages(t *testing.T) {
	resRandomoptionSlotLock := &dnfv1.ResRandomoptionSlotLock{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRandomoptionSlotLock)
	assert.Equal(t, int32(0), resRandomoptionSlotLock.Error)
	assert.Equal(t, int32(1), resRandomoptionSlotLock.Index)
}

// 测试重新购买相关消息
func testBatch74RepurchaseMessages(t *testing.T) {
	ptRepurchaseCreature := &dnfv1.PtRepurchaseCreature{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRepurchaseCreature)
	assert.Equal(t, int32(0), ptRepurchaseCreature.Error)
	assert.Equal(t, int32(1), ptRepurchaseCreature.Index)
}

// 测试收获相关消息
func testBatch74ReapMessages(t *testing.T) {
	ptReapReward := &dnfv1.PtReapReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptReapReward)
	assert.Equal(t, int32(0), ptReapReward.Error)
	assert.Equal(t, int32(1), ptReapReward.Index)
}

// 测试战斗联赛相关消息
func testBatch74BattleleagueMessages(t *testing.T) {
	reqBattleleaguePveDestroySummonstone := &dnfv1.ReqBattleleaguePveDestroySummonstone{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqBattleleaguePveDestroySummonstone)
	assert.Equal(t, int32(0), reqBattleleaguePveDestroySummonstone.Error)
	assert.Equal(t, int32(1), reqBattleleaguePveDestroySummonstone.Index)
}

// 测试笔记相关消息
func testBatch74NoteMessages(t *testing.T) {
	ptNoteMessage := &dnfv1.PtNoteMessage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptNoteMessage)
	assert.Equal(t, int32(0), ptNoteMessage.Error)
	assert.Equal(t, int32(1), ptNoteMessage.Index)
}

// 测试物体相关消息
func testBatch74ObjectMessages(t *testing.T) {
	ptObject := &dnfv1.PtObject{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptObject)
	assert.Equal(t, int32(0), ptObject.Error)
	assert.Equal(t, int32(1), ptObject.Index)
}

// 测试塞拉相关消息
func testBatch74CeraMessages(t *testing.T) {
	ptCeraShopInfo := &dnfv1.PtCeraShopInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCeraShopInfo)
	assert.Equal(t, int32(0), ptCeraShopInfo.Error)
	assert.Equal(t, int32(1), ptCeraShopInfo.Index)
}
