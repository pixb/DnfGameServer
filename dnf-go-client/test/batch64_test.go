package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch64Migration 测试第64批迁移的消息类型
func TestBatch64Migration(t *testing.T) {
	t.Run("Pray", testBatch64PrayMessages)
	t.Run("Chat", testBatch64ChatMessages)
	t.Run("Hidden", testBatch64HiddenMessages)
	t.Run("Creature", testBatch64CreatureMessages)
	t.Run("Item", testBatch64ItemMessages)
	t.Run("Monitor", testBatch64MonitorMessages)
	t.Run("Minigame", testBatch64MinigameMessages)
	t.Run("Board", testBatch64BoardMessages)
	t.Run("Event", testBatch64EventMessages)
	t.Run("Chivalry", testBatch64ChivalryMessages)
	t.Run("Card", testBatch64CardMessages)
	t.Run("Quest", testBatch64QuestMessages)
	t.Run("Guild", testBatch64GuildMessages)
	t.Run("Adventure", testBatch64AdventureMessages)
	t.Run("Server", testBatch64ServerMessages)
	t.Run("Storage", testBatch64StorageMessages)
	t.Run("Friend", testBatch64FriendMessages)
	t.Run("Proposal", testBatch64ProposalMessages)
	t.Run("Pvp", testBatch64PvpMessages)
	t.Run("Dungeon", testBatch64DungeonMessages)
	t.Run("Tutorial", testBatch64TutorialMessages)
	t.Run("Coin", testBatch64CoinMessages)
	t.Run("Tower", testBatch64TowerMessages)
	t.Run("Game", testBatch64GameMessages)
	t.Run("Standby", testBatch64StandbyMessages)
	t.Run("Verification", testBatch64VerificationMessages)
	t.Run("Welfare", testBatch64WelfareMessages)
	t.Run("World", testBatch64WorldMessages)
	t.Run("Wardrobe", testBatch64WardrobeMessages)
}

// 测试祈祷相关消息
func testBatch64PrayMessages(t *testing.T) {
	resPray := &dnfv1.ResPray{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPray)
	assert.Equal(t, int32(0), resPray.Error)
	assert.Equal(t, int32(1), resPray.Index)
}

// 测试聊天相关消息
func testBatch64ChatMessages(t *testing.T) {
	ptChatChannel := &dnfv1.PtChatChannel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptChatChannel)
	assert.Equal(t, int32(0), ptChatChannel.Error)
	assert.Equal(t, int32(1), ptChatChannel.Index)

	resHiddenChattngDelete := &dnfv1.ResHiddenChattngDelete{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHiddenChattngDelete)
	assert.Equal(t, int32(0), resHiddenChattngDelete.Error)
	assert.Equal(t, int32(1), resHiddenChattngDelete.Index)
}

// 测试隐藏相关消息
func testBatch64HiddenMessages(t *testing.T) {
	// 已在ChatMessages中测试
}

// 测试生物相关消息
func testBatch64CreatureMessages(t *testing.T) {
	resCreatureErrandSend := &dnfv1.ResCreatureErrandSend{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureErrandSend)
	assert.Equal(t, int32(0), resCreatureErrandSend.Error)
	assert.Equal(t, int32(1), resCreatureErrandSend.Index)
}

// 测试物品相关消息
func testBatch64ItemMessages(t *testing.T) {
	reqItemProductionRegister := &dnfv1.ReqItemProductionRegister{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemProductionRegister)
	assert.Equal(t, int32(0), reqItemProductionRegister.Error)
	assert.Equal(t, int32(1), reqItemProductionRegister.Index)

	reqItemEmblemUpgradeInven := &dnfv1.ReqItemEmblemUpgradeInven{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemEmblemUpgradeInven)
	assert.Equal(t, int32(0), reqItemEmblemUpgradeInven.Error)
	assert.Equal(t, int32(1), reqItemEmblemUpgradeInven.Index)
}

// 测试监控相关消息
func testBatch64MonitorMessages(t *testing.T) {
	resMonitor := &dnfv1.ResMonitor{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMonitor)
	assert.Equal(t, int32(0), resMonitor.Error)
	assert.Equal(t, int32(1), resMonitor.Index)
}

// 测试小游戏相关消息
func testBatch64MinigameMessages(t *testing.T) {
	resMinigameMooncakeStart := &dnfv1.ResMinigameMooncakeStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameMooncakeStart)
	assert.Equal(t, int32(0), resMinigameMooncakeStart.Error)
	assert.Equal(t, int32(1), resMinigameMooncakeStart.Index)

	resMinigameRestauranReward := &dnfv1.ResMinigameRestauranReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameRestauranReward)
	assert.Equal(t, int32(0), resMinigameRestauranReward.Error)
	assert.Equal(t, int32(1), resMinigameRestauranReward.Index)

	reqMinigameLotteryOpen := &dnfv1.ReqMinigameLotteryOpen{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigameLotteryOpen)
	assert.Equal(t, int32(0), reqMinigameLotteryOpen.Error)
	assert.Equal(t, int32(1), reqMinigameLotteryOpen.Index)
}

// 测试棋盘相关消息
func testBatch64BoardMessages(t *testing.T) {
	notifyBoardGameEmoticon := &dnfv1.NotifyBoardGameEmoticon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBoardGameEmoticon)
	assert.Equal(t, int32(0), notifyBoardGameEmoticon.Error)
	assert.Equal(t, int32(1), notifyBoardGameEmoticon.Index)

	ptDropObjectGold := &dnfv1.PtDropObjectGold{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptDropObjectGold)
	assert.Equal(t, int32(0), ptDropObjectGold.Error)
	assert.Equal(t, int32(1), ptDropObjectGold.Index)

	resStartBoardGame := &dnfv1.ResStartBoardGame{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartBoardGame)
	assert.Equal(t, int32(0), resStartBoardGame.Error)
	assert.Equal(t, int32(1), resStartBoardGame.Index)
}

// 测试事件相关消息
func testBatch64EventMessages(t *testing.T) {
	reqEventGetReward := &dnfv1.ReqEventGetReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqEventGetReward)
	assert.Equal(t, int32(0), reqEventGetReward.Error)
	assert.Equal(t, int32(1), reqEventGetReward.Index)
}

// 测试骑士相关消息
func testBatch64ChivalryMessages(t *testing.T) {
	notifyChivalryChangeGrade := &dnfv1.NotifyChivalryChangeGrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyChivalryChangeGrade)
	assert.Equal(t, int32(0), notifyChivalryChangeGrade.Error)
	assert.Equal(t, int32(1), notifyChivalryChangeGrade.Index)
}

// 测试卡片相关消息
func testBatch64CardMessages(t *testing.T) {
	enumHistoricsiteEnter := &dnfv1.EnumHistoricsiteEnter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumHistoricsiteEnter)
	assert.Equal(t, int32(0), enumHistoricsiteEnter.Error)
	assert.Equal(t, int32(1), enumHistoricsiteEnter.Index)

	resCardCompose := &dnfv1.ResCardCompose{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCardCompose)
	assert.Equal(t, int32(0), resCardCompose.Error)
	assert.Equal(t, int32(1), resCardCompose.Index)
}

// 测试任务相关消息
func testBatch64QuestMessages(t *testing.T) {
	reqQuestGiveUp := &dnfv1.ReqQuestGiveUp{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqQuestGiveUp)
	assert.Equal(t, int32(0), reqQuestGiveUp.Error)
	assert.Equal(t, int32(1), reqQuestGiveUp.Index)

	resRewardQuest := &dnfv1.ResRewardQuest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRewardQuest)
	assert.Equal(t, int32(0), resRewardQuest.Error)
	assert.Equal(t, int32(1), resRewardQuest.Index)
}

// 测试公会相关消息
func testBatch64GuildMessages(t *testing.T) {
	ptGuildAttendReward := &dnfv1.PtGuildAttendReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildAttendReward)
	assert.Equal(t, int32(0), ptGuildAttendReward.Error)
	assert.Equal(t, int32(1), ptGuildAttendReward.Index)

	resSecedeGuild := &dnfv1.ResSecedeGuild{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSecedeGuild)
	assert.Equal(t, int32(0), resSecedeGuild.Error)
	assert.Equal(t, int32(1), resSecedeGuild.Index)

	resSendGuildRedpacket := &dnfv1.ResSendGuildRedpacket{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSendGuildRedpacket)
	assert.Equal(t, int32(0), resSendGuildRedpacket.Error)
	assert.Equal(t, int32(1), resSendGuildRedpacket.Index)
}

// 测试冒险相关消息
func testBatch64AdventureMessages(t *testing.T) {
	resAdventureReapReward := &dnfv1.ResAdventureReapReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAdventureReapReward)
	assert.Equal(t, int32(0), resAdventureReapReward.Error)
	assert.Equal(t, int32(1), resAdventureReapReward.Index)
}

// 测试服务器相关消息
func testBatch64ServerMessages(t *testing.T) {
	resServerResponsePacket := &dnfv1.ResServerResponsePacket{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resServerResponsePacket)
	assert.Equal(t, int32(0), resServerResponsePacket.Error)
	assert.Equal(t, int32(1), resServerResponsePacket.Index)

	resSaveWeakServerSimpleData := &dnfv1.ResSaveWeakServerSimpleData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSaveWeakServerSimpleData)
	assert.Equal(t, int32(0), resSaveWeakServerSimpleData.Error)
	assert.Equal(t, int32(1), resSaveWeakServerSimpleData.Index)

	resStartGame := &dnfv1.ResStartGame{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartGame)
	assert.Equal(t, int32(0), resStartGame.Error)
	assert.Equal(t, int32(1), resStartGame.Index)

	resStandby := &dnfv1.ResStandby{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStandby)
	assert.Equal(t, int32(0), resStandby.Error)
	assert.Equal(t, int32(1), resStandby.Index)

	resTssData := &dnfv1.ResTssData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTssData)
	assert.Equal(t, int32(0), resTssData.Error)
	assert.Equal(t, int32(1), resTssData.Index)
}

// 测试仓库相关消息
func testBatch64StorageMessages(t *testing.T) {
	resSendStorage := &dnfv1.ResSendStorage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSendStorage)
	assert.Equal(t, int32(0), resSendStorage.Error)
	assert.Equal(t, int32(1), resSendStorage.Index)

	resStorageStep := &dnfv1.ResStorageStep{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStorageStep)
	assert.Equal(t, int32(0), resStorageStep.Error)
	assert.Equal(t, int32(1), resStorageStep.Index)

	resStorageExtend := &dnfv1.ResStorageExtend{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStorageExtend)
	assert.Equal(t, int32(0), resStorageExtend.Error)
	assert.Equal(t, int32(1), resStorageExtend.Index)
}

// 测试好友相关消息
func testBatch64FriendMessages(t *testing.T) {
	resSelectOtherDungeonAtMulti := &dnfv1.ResSelectOtherDungeonAtMulti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSelectOtherDungeonAtMulti)
	assert.Equal(t, int32(0), resSelectOtherDungeonAtMulti.Error)
	assert.Equal(t, int32(1), resSelectOtherDungeonAtMulti.Index)

	resSendingInviteFriendList := &dnfv1.ResSendingInviteFriendList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSendingInviteFriendList)
	assert.Equal(t, int32(0), resSendingInviteFriendList.Error)
	assert.Equal(t, int32(1), resSendingInviteFriendList.Index)

	resRequestFriendInvite := &dnfv1.ResRequestFriendInvite{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRequestFriendInvite)
	assert.Equal(t, int32(0), resRequestFriendInvite.Error)
	assert.Equal(t, int32(1), resRequestFriendInvite.Index)
}

// 测试提案相关消息
func testBatch64ProposalMessages(t *testing.T) {
	resReplyProposal := &dnfv1.ResReplyProposal{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resReplyProposal)
	assert.Equal(t, int32(0), resReplyProposal.Error)
	assert.Equal(t, int32(1), resReplyProposal.Index)
}

// 测试PVP相关消息
func testBatch64PvpMessages(t *testing.T) {
	resSetPvpControlMode := &dnfv1.ResSetPvpControlMode{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSetPvpControlMode)
	assert.Equal(t, int32(0), resSetPvpControlMode.Error)
	assert.Equal(t, int32(1), resSetPvpControlMode.Index)
}

// 测试副本相关消息
func testBatch64DungeonMessages(t *testing.T) {
	resReturnToTownAtMultiPlay := &dnfv1.ResReturnToTownAtMultiPlay{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resReturnToTownAtMultiPlay)
	assert.Equal(t, int32(0), resReturnToTownAtMultiPlay.Error)
	assert.Equal(t, int32(1), resReturnToTownAtMultiPlay.Index)

	resStartLockstepRoom := &dnfv1.ResStartLockstepRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartLockstepRoom)
	assert.Equal(t, int32(0), resStartLockstepRoom.Error)
	assert.Equal(t, int32(1), resStartLockstepRoom.Index)

	resStartDungeon := &dnfv1.ResStartDungeon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartDungeon)
	assert.Equal(t, int32(0), resStartDungeon.Error)
	assert.Equal(t, int32(1), resStartDungeon.Index)

	resStartDungeonComplete := &dnfv1.ResStartDungeonComplete{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resStartDungeonComplete)
	assert.Equal(t, int32(0), resStartDungeonComplete.Error)
	assert.Equal(t, int32(1), resStartDungeonComplete.Index)

	resSyncDungeonStartTime := &dnfv1.ResSyncDungeonStartTime{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSyncDungeonStartTime)
	assert.Equal(t, int32(0), resSyncDungeonStartTime.Error)
	assert.Equal(t, int32(1), resSyncDungeonStartTime.Index)

	resUpdateDungeonClearConditionGetItem := &dnfv1.ResUpdateDungeonClearConditionGetItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resUpdateDungeonClearConditionGetItem)
	assert.Equal(t, int32(0), resUpdateDungeonClearConditionGetItem.Error)
	assert.Equal(t, int32(1), resUpdateDungeonClearConditionGetItem.Index)
}

// 测试教程相关消息
func testBatch64TutorialMessages(t *testing.T) {
	resTutorialSave := &dnfv1.ResTutorialSave{
		Error:      0,
		TutorialId: 1,
	}
	assert.NotNil(t, resTutorialSave)
	assert.Equal(t, int32(0), resTutorialSave.Error)
	assert.Equal(t, int32(1), resTutorialSave.TutorialId)
}

// 测试金币相关消息
func testBatch64CoinMessages(t *testing.T) {
	resUseCoin := &dnfv1.ResUseCoin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resUseCoin)
	assert.Equal(t, int32(0), resUseCoin.Error)
	assert.Equal(t, int32(1), resUseCoin.Index)
}

// 测试塔相关消息
func testBatch64TowerMessages(t *testing.T) {
	resTowerInfo := &dnfv1.ResTowerInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTowerInfo)
	assert.Equal(t, int32(0), resTowerInfo.Error)
	assert.Equal(t, int32(1), resTowerInfo.Index)
}

// 测试游戏相关消息
func testBatch64GameMessages(t *testing.T) {
	resTargetUserDetailInfo := &dnfv1.ResTargetUserDetailInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTargetUserDetailInfo)
	assert.Equal(t, int32(0), resTargetUserDetailInfo.Error)
	assert.Equal(t, int32(1), resTargetUserDetailInfo.Index)
}

// 测试待机相关消息
func testBatch64StandbyMessages(t *testing.T) {
	// 已在ServerMessages中测试
}

// 测试验证相关消息
func testBatch64VerificationMessages(t *testing.T) {
	resVerificationSimple := &dnfv1.ResVerificationSimple{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resVerificationSimple)
	assert.Equal(t, int32(0), resVerificationSimple.Error)
	assert.Equal(t, int32(1), resVerificationSimple.Index)

	resVerificationEvent := &dnfv1.ResVerificationEvent{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resVerificationEvent)
	assert.Equal(t, int32(0), resVerificationEvent.Error)
	assert.Equal(t, int32(1), resVerificationEvent.Index)

	resVerificationStart := &dnfv1.ResVerificationStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resVerificationStart)
	assert.Equal(t, int32(0), resVerificationStart.Error)
	assert.Equal(t, int32(1), resVerificationStart.Index)
}

// 测试福利相关消息
func testBatch64WelfareMessages(t *testing.T) {
	resWelfareFindRewardInfo := &dnfv1.ResWelfareFindRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWelfareFindRewardInfo)
	assert.Equal(t, int32(0), resWelfareFindRewardInfo.Error)
	assert.Equal(t, int32(1), resWelfareFindRewardInfo.Index)

	resWelfareFindRewardGet := &dnfv1.ResWelfareFindRewardGet{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWelfareFindRewardGet)
	assert.Equal(t, int32(0), resWelfareFindRewardGet.Error)
	assert.Equal(t, int32(1), resWelfareFindRewardGet.Index)
}

// 测试世界相关消息
func testBatch64WorldMessages(t *testing.T) {
	resWorldRaidRanking := &dnfv1.ResWorldRaidRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWorldRaidRanking)
	assert.Equal(t, int32(0), resWorldRaidRanking.Error)
	assert.Equal(t, int32(1), resWorldRaidRanking.Index)

	resWorldBossDamage := &dnfv1.ResWorldBossDamage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWorldBossDamage)
	assert.Equal(t, int32(0), resWorldBossDamage.Error)
	assert.Equal(t, int32(1), resWorldBossDamage.Index)

	resWorldbossRanking := &dnfv1.ResWorldbossRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWorldbossRanking)
	assert.Equal(t, int32(0), resWorldbossRanking.Error)
	assert.Equal(t, int32(1), resWorldbossRanking.Index)

	resWorldBossInfo := &dnfv1.ResWorldBossInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWorldBossInfo)
	assert.Equal(t, int32(0), resWorldBossInfo.Error)
	assert.Equal(t, int32(1), resWorldBossInfo.Index)
}

// 测试衣橱相关消息
func testBatch64WardrobeMessages(t *testing.T) {
	resSetAppendageMannequin := &dnfv1.ResSetAppendageMannequin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSetAppendageMannequin)
	assert.Equal(t, int32(0), resSetAppendageMannequin.Error)
	assert.Equal(t, int32(1), resSetAppendageMannequin.Index)

	resSetNameMannequin := &dnfv1.ResSetNameMannequin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSetNameMannequin)
	assert.Equal(t, int32(0), resSetNameMannequin.Error)
	assert.Equal(t, int32(1), resSetNameMannequin.Index)

	resWardrobeSetMannequin := &dnfv1.ResWardrobeSetMannequin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWardrobeSetMannequin)
	assert.Equal(t, int32(0), resWardrobeSetMannequin.Error)
	assert.Equal(t, int32(1), resWardrobeSetMannequin.Index)

	resWardrobeSetSlot := &dnfv1.ResWardrobeSetSlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWardrobeSetSlot)
	assert.Equal(t, int32(0), resWardrobeSetSlot.Error)
	assert.Equal(t, int32(1), resWardrobeSetSlot.Index)
}
