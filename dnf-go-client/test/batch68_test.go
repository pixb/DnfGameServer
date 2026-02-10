package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch68Migration 测试第68批迁移的消息类型
func TestBatch68Migration(t *testing.T) {
	t.Run("Wedding", testBatch68WeddingMessages)
	t.Run("Object", testBatch68ObjectMessages)
	t.Run("Minigame", testBatch68MinigameMessages)
	t.Run("Floor", testBatch68FloorMessages)
	t.Run("Recovery", testBatch68RecoveryMessages)
	t.Run("GuildBingo", testBatch68GuildBingoMessages)
	t.Run("SpecialClass", testBatch68SpecialClassMessages)
	t.Run("Mail", testBatch68MailMessages)
	t.Run("Character", testBatch68CharacterMessages)
	t.Run("Community", testBatch68CommunityMessages)
	t.Run("Proxy", testBatch68ProxyMessages)
	t.Run("Item", testBatch68ItemMessages)
	t.Run("Dungeon", testBatch68DungeonMessages)
	t.Run("Quest", testBatch68QuestMessages)
	t.Run("Fatigue", testBatch68FatigueMessages)
	t.Run("Battleleague", testBatch68BattleleagueMessages)
	t.Run("Material", testBatch68MaterialMessages)
	t.Run("Enum", testBatch68EnumMessages)
	t.Run("MultiPlay", testBatch68MultiPlayMessages)
	t.Run("Guild", testBatch68GuildMessages)
	t.Run("Historicsite", testBatch68HistoricsiteMessages)
	t.Run("Party", testBatch68PartyMessages)
	t.Run("Raid", testBatch68RaidMessages)
	t.Run("Biling", testBatch68BilingMessages)
	t.Run("AdventureUnion", testBatch68AdventureUnionMessages)
	t.Run("Ranking", testBatch68RankingMessages)
	t.Run("Pray", testBatch68PrayMessages)
	t.Run("AdventurerMaker", testBatch68AdventurerMakerMessages)
	t.Run("Lecture", testBatch68LectureMessages)
}

// 测试婚礼相关消息
func testBatch68WeddingMessages(t *testing.T) {
	reqLoadWeddingMoneygiftRanking := &dnfv1.ReqLoadWeddingMoneygiftRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqLoadWeddingMoneygiftRanking)
	assert.Equal(t, int32(0), reqLoadWeddingMoneygiftRanking.Error)
	assert.Equal(t, int32(1), reqLoadWeddingMoneygiftRanking.Index)
}

// 测试物体相关消息
func testBatch68ObjectMessages(t *testing.T) {
	ptObjectInfo := &dnfv1.PtObjectInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptObjectInfo)
	assert.Equal(t, int32(0), ptObjectInfo.Error)
	assert.Equal(t, int32(1), ptObjectInfo.Index)
}

// 测试小游戏相关消息
func testBatch68MinigameMessages(t *testing.T) {
	resMinigameLotteryBuy := &dnfv1.ResMinigameLotteryBuy{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameLotteryBuy)
	assert.Equal(t, int32(0), resMinigameLotteryBuy.Error)
	assert.Equal(t, int32(1), resMinigameLotteryBuy.Index)

	resMinigameAdventurerMakerCompliment := &dnfv1.ResMinigameAdventurerMakerCompliment{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameAdventurerMakerCompliment)
	assert.Equal(t, int32(0), resMinigameAdventurerMakerCompliment.Error)
	assert.Equal(t, int32(1), resMinigameAdventurerMakerCompliment.Index)
}

// 测试楼层相关消息
func testBatch68FloorMessages(t *testing.T) {
	ptFloorList := &dnfv1.PtFloorList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptFloorList)
	assert.Equal(t, int32(0), ptFloorList.Error)
	assert.Equal(t, int32(1), ptFloorList.Index)
}

// 测试恢复相关消息
func testBatch68RecoveryMessages(t *testing.T) {
	resRecoveryCharac := &dnfv1.ResRecoveryCharac{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRecoveryCharac)
	assert.Equal(t, int32(0), resRecoveryCharac.Error)
	assert.Equal(t, int32(1), resRecoveryCharac.Index)
}

// 测试公会宾果相关消息
func testBatch68GuildBingoMessages(t *testing.T) {
	resGuildBingoUseItem := &dnfv1.ResGuildBingoUseItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildBingoUseItem)
	assert.Equal(t, int32(0), resGuildBingoUseItem.Error)
	assert.Equal(t, int32(1), resGuildBingoUseItem.Index)

	ptGuildBingoRewardInfo := &dnfv1.PtGuildBingoRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildBingoRewardInfo)
	assert.Equal(t, int32(0), ptGuildBingoRewardInfo.Error)
	assert.Equal(t, int32(1), ptGuildBingoRewardInfo.Index)

	resGuildBingoUpdateSquare := &dnfv1.ResGuildBingoUpdateSquare{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildBingoUpdateSquare)
	assert.Equal(t, int32(0), resGuildBingoUpdateSquare.Error)
	assert.Equal(t, int32(1), resGuildBingoUpdateSquare.Index)
}

// 测试特殊职业相关消息
func testBatch68SpecialClassMessages(t *testing.T) {
	ptMinigameSpecialClassStudentInfo := &dnfv1.PtMinigameSpecialClassStudentInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMinigameSpecialClassStudentInfo)
	assert.Equal(t, int32(0), ptMinigameSpecialClassStudentInfo.Error)
	assert.Equal(t, int32(1), ptMinigameSpecialClassStudentInfo.Index)

	ptMinigameSpecialClassTicketInfo := &dnfv1.PtMinigameSpecialClassTicketInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMinigameSpecialClassTicketInfo)
	assert.Equal(t, int32(0), ptMinigameSpecialClassTicketInfo.Error)
	assert.Equal(t, int32(1), ptMinigameSpecialClassTicketInfo.Index)

	reqMinigameSpecialClassLecture := &dnfv1.ReqMinigameSpecialClassLecture{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigameSpecialClassLecture)
	assert.Equal(t, int32(0), reqMinigameSpecialClassLecture.Error)
	assert.Equal(t, int32(1), reqMinigameSpecialClassLecture.Index)
}

// 测试邮件相关消息
func testBatch68MailMessages(t *testing.T) {
	resMailList := &dnfv1.ResMailList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMailList)
	assert.Equal(t, int32(0), resMailList.Error)
	assert.Equal(t, int32(1), resMailList.Index)
}

// 测试角色相关消息
func testBatch68CharacterMessages(t *testing.T) {
	reqCharacterStatInfo := &dnfv1.ReqCharacterStatInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCharacterStatInfo)
	assert.Equal(t, int32(0), reqCharacterStatInfo.Error)
	assert.Equal(t, int32(1), reqCharacterStatInfo.Index)
}

// 测试社区相关消息
func testBatch68CommunityMessages(t *testing.T) {
	reqCommunityGiftSendHongbaoMessage := &dnfv1.ReqCommunityGiftSendHongbaoMessage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCommunityGiftSendHongbaoMessage)
	assert.Equal(t, int32(0), reqCommunityGiftSendHongbaoMessage.Error)
	assert.Equal(t, int32(1), reqCommunityGiftSendHongbaoMessage.Index)

	reqCommunityGiftDeleteLog := &dnfv1.ReqCommunityGiftDeleteLog{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCommunityGiftDeleteLog)
	assert.Equal(t, int32(0), reqCommunityGiftDeleteLog.Error)
	assert.Equal(t, int32(1), reqCommunityGiftDeleteLog.Index)
}

// 测试代理包相关消息
func testBatch68ProxyMessages(t *testing.T) {
	proxyPacketRestoreTypesReq := &dnfv1.ProxyPacketRestoreTypesReq{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, proxyPacketRestoreTypesReq)
	assert.Equal(t, int32(0), proxyPacketRestoreTypesReq.Error)
	assert.Equal(t, int32(1), proxyPacketRestoreTypesReq.Index)
}

// 测试物品相关消息
func testBatch68ItemMessages(t *testing.T) {
	ptItemUsableInfo := &dnfv1.PtItemUsableInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptItemUsableInfo)
	assert.Equal(t, int32(0), ptItemUsableInfo.Error)
	assert.Equal(t, int32(1), ptItemUsableInfo.Index)
}

// 测试副本相关消息
func testBatch68DungeonMessages(t *testing.T) {
	resDungeonTickets := &dnfv1.ResDungeonTickets{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resDungeonTickets)
	assert.Equal(t, int32(0), resDungeonTickets.Error)
	assert.Equal(t, int32(1), resDungeonTickets.Index)
}

// 测试任务相关消息
func testBatch68QuestMessages(t *testing.T) {
	resQuestGiveUp := &dnfv1.ResQuestGiveUp{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resQuestGiveUp)
	assert.Equal(t, int32(0), resQuestGiveUp.Error)
	assert.Equal(t, int32(1), resQuestGiveUp.Index)

	ptQuestUpdate := &dnfv1.PtQuestUpdate{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptQuestUpdate)
	assert.Equal(t, int32(0), ptQuestUpdate.Error)
	assert.Equal(t, int32(1), ptQuestUpdate.Index)
}

// 测试疲劳相关消息
func testBatch68FatigueMessages(t *testing.T) {
	reqRecoveryFatigue := &dnfv1.ReqRecoveryFatigue{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRecoveryFatigue)
	assert.Equal(t, int32(0), reqRecoveryFatigue.Error)
	assert.Equal(t, int32(1), reqRecoveryFatigue.Index)
}

// 测试战斗联赛相关消息
func testBatch68BattleleagueMessages(t *testing.T) {
	resBattleleaguePvpDie := &dnfv1.ResBattleleaguePvpDie{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBattleleaguePvpDie)
	assert.Equal(t, int32(0), resBattleleaguePvpDie.Error)
	assert.Equal(t, int32(1), resBattleleaguePvpDie.Index)
}

// 测试材料相关消息
func testBatch68MaterialMessages(t *testing.T) {
	reqMaterialList := &dnfv1.ReqMaterialList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMaterialList)
	assert.Equal(t, int32(0), reqMaterialList.Error)
	assert.Equal(t, int32(1), reqMaterialList.Index)
}

// 测试枚举相关消息
func testBatch68EnumMessages(t *testing.T) {
	enumIdipNotifyType := &dnfv1.EnumIdipNotifyType{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumIdipNotifyType)
	assert.Equal(t, int32(0), enumIdipNotifyType.Error)
	assert.Equal(t, int32(1), enumIdipNotifyType.Index)

	enumPartyOpenTypes := &dnfv1.EnumPartyOpenTypes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumPartyOpenTypes)
	assert.Equal(t, int32(0), enumPartyOpenTypes.Error)
	assert.Equal(t, int32(1), enumPartyOpenTypes.Index)
}

// 测试多人游戏相关消息
func testBatch68MultiPlayMessages(t *testing.T) {
	reqMultiPlayRequestMatchCancel := &dnfv1.ReqMultiPlayRequestMatchCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMultiPlayRequestMatchCancel)
	assert.Equal(t, int32(0), reqMultiPlayRequestMatchCancel.Error)
	assert.Equal(t, int32(1), reqMultiPlayRequestMatchCancel.Index)
}

// 测试公会相关消息
func testBatch68GuildMessages(t *testing.T) {
	resGuildPrivateBuffUpgrade := &dnfv1.ResGuildPrivateBuffUpgrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildPrivateBuffUpgrade)
	assert.Equal(t, int32(0), resGuildPrivateBuffUpgrade.Error)
	assert.Equal(t, int32(1), resGuildPrivateBuffUpgrade.Index)

	resGuildUseFacility := &dnfv1.ResGuildUseFacility{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildUseFacility)
	assert.Equal(t, int32(0), resGuildUseFacility.Error)
	assert.Equal(t, int32(1), resGuildUseFacility.Index)

	notifyGuildMemberGrade := &dnfv1.NotifyGuildMemberGrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyGuildMemberGrade)
	assert.Equal(t, int32(0), notifyGuildMemberGrade.Error)
	assert.Equal(t, int32(1), notifyGuildMemberGrade.Index)
}

// 测试历史站点相关消息
func testBatch68HistoricsiteMessages(t *testing.T) {
	reqHistoricsiteKeepingRelicList := &dnfv1.ReqHistoricsiteKeepingRelicList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteKeepingRelicList)
	assert.Equal(t, int32(0), reqHistoricsiteKeepingRelicList.Error)
	assert.Equal(t, int32(1), reqHistoricsiteKeepingRelicList.Index)

	resHistoricsiteCancelRegisteredEnter := &dnfv1.ResHistoricsiteCancelRegisteredEnter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteCancelRegisteredEnter)
	assert.Equal(t, int32(0), resHistoricsiteCancelRegisteredEnter.Error)
	assert.Equal(t, int32(1), resHistoricsiteCancelRegisteredEnter.Index)
}

// 测试队伍相关消息
func testBatch68PartyMessages(t *testing.T) {
	resPartyRefuse := &dnfv1.ResPartyRefuse{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPartyRefuse)
	assert.Equal(t, int32(0), resPartyRefuse.Error)
	assert.Equal(t, int32(1), resPartyRefuse.Index)
}

// 测试突袭相关消息
func testBatch68RaidMessages(t *testing.T) {
	notifyRaidRetry := &dnfv1.NotifyRaidRetry{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyRaidRetry)
	assert.Equal(t, int32(0), notifyRaidRetry.Error)
	assert.Equal(t, int32(1), notifyRaidRetry.Index)

	resRaidEntranceCount := &dnfv1.ResRaidEntranceCount{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRaidEntranceCount)
	assert.Equal(t, int32(0), resRaidEntranceCount.Error)
	assert.Equal(t, int32(1), resRaidEntranceCount.Index)
}

// 测试账单相关消息
func testBatch68BilingMessages(t *testing.T) {
	resBilingNotify := &dnfv1.ResBilingNotify{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBilingNotify)
	assert.Equal(t, int32(0), resBilingNotify.Error)
	assert.Equal(t, int32(1), resBilingNotify.Index)
}

// 测试冒险联盟相关消息
func testBatch68AdventureUnionMessages(t *testing.T) {
	reqAdventureUnionCharacterListOther := &dnfv1.ReqAdventureUnionCharacterListOther{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionCharacterListOther)
	assert.Equal(t, int32(0), reqAdventureUnionCharacterListOther.Error)
	assert.Equal(t, int32(1), reqAdventureUnionCharacterListOther.Index)
}

// 测试排名相关消息
func testBatch68RankingMessages(t *testing.T) {
	resRankingDungeonSeasonInfo := &dnfv1.ResRankingDungeonSeasonInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRankingDungeonSeasonInfo)
	assert.Equal(t, int32(0), resRankingDungeonSeasonInfo.Error)
	assert.Equal(t, int32(1), resRankingDungeonSeasonInfo.Index)
}

// 测试祈祷相关消息
func testBatch68PrayMessages(t *testing.T) {
	resPrayInfo := &dnfv1.ResPrayInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPrayInfo)
	assert.Equal(t, int32(0), resPrayInfo.Error)
	assert.Equal(t, int32(1), resPrayInfo.Index)
}

// 测试冒险家制造相关消息
func testBatch68AdventurerMakerMessages(t *testing.T) {
	resMinigameAdventurerMakerCompliment := &dnfv1.ResMinigameAdventurerMakerCompliment{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameAdventurerMakerCompliment)
	assert.Equal(t, int32(0), resMinigameAdventurerMakerCompliment.Error)
	assert.Equal(t, int32(1), resMinigameAdventurerMakerCompliment.Index)
}

// 测试讲座相关消息
func testBatch68LectureMessages(t *testing.T) {
	reqMinigameSpecialClassLecture := &dnfv1.ReqMinigameSpecialClassLecture{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigameSpecialClassLecture)
	assert.Equal(t, int32(0), reqMinigameSpecialClassLecture.Error)
	assert.Equal(t, int32(1), reqMinigameSpecialClassLecture.Index)
}
