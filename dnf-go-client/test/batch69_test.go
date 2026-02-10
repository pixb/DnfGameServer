package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch69Migration 测试第69批迁移的消息类型
func TestBatch69Migration(t *testing.T) {
	t.Run("Daily", testBatch69DailyMessages)
	t.Run("Minigame", testBatch69MinigameMessages)
	t.Run("Guardian", testBatch69GuardianMessages)
	t.Run("AttackSquad", testBatch69AttackSquadMessages)
	t.Run("ArcadePvp", testBatch69ArcadePvpMessages)
	t.Run("HiddenChatting", testBatch69HiddenChattingMessages)
	t.Run("Battleleague", testBatch69BattleleagueMessages)
	t.Run("Facility", testBatch69FacilityMessages)
	t.Run("Achievement", testBatch69AchievementMessages)
	t.Run("Exp", testBatch69ExpMessages)
	t.Run("FreeChange", testBatch69FreeChangeMessages)
	t.Run("Welfare", testBatch69WelfareMessages)
	t.Run("EnterToTown", testBatch69EnterToTownMessages)
	t.Run("Board", testBatch69BoardMessages)
	t.Run("Waiting", testBatch69WaitingMessages)
	t.Run("Raid", testBatch69RaidMessages)
	t.Run("Seria", testBatch69SeriaMessages)
	t.Run("Tonic", testBatch69TonicMessages)
	t.Run("Avatar", testBatch69AvatarMessages)
	t.Run("GuildDining", testBatch69GuildDiningMessages)
	t.Run("Maze", testBatch69MazeMessages)
	t.Run("Errand", testBatch69ErrandMessages)
	t.Run("Auction", testBatch69AuctionMessages)
	t.Run("Chivalry", testBatch69ChivalryMessages)
	t.Run("Verification", testBatch69VerificationMessages)
	t.Run("Friend", testBatch69FriendMessages)
	t.Run("Character", testBatch69CharacterMessages)
	t.Run("Equip", testBatch69EquipMessages)
	t.Run("Item", testBatch69ItemMessages)
	t.Run("DragonRoad", testBatch69DragonRoadMessages)
	t.Run("GuildDonation", testBatch69GuildDonationMessages)
	t.Run("Protocol", testBatch69ProtocolMessages)
	t.Run("Historicsite", testBatch69HistoricsiteMessages)
	t.Run("ReEnter", testBatch69ReEnterMessages)
}

// 测试每日相关消息
func testBatch69DailyMessages(t *testing.T) {
	resDailyReset := &dnfv1.ResDailyReset{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resDailyReset)
	assert.Equal(t, int32(0), resDailyReset.Error)
	assert.Equal(t, int32(1), resDailyReset.Index)
}

// 测试小游戏相关消息
func testBatch69MinigameMessages(t *testing.T) {
	resMinigameChemicalClear := &dnfv1.ResMinigameChemicalClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameChemicalClear)
	assert.Equal(t, int32(0), resMinigameChemicalClear.Error)
	assert.Equal(t, int32(1), resMinigameChemicalClear.Index)

	resMinigameSpecialClassMazeClear := &dnfv1.ResMinigameSpecialClassMazeClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameSpecialClassMazeClear)
	assert.Equal(t, int32(0), resMinigameSpecialClassMazeClear.Error)
	assert.Equal(t, int32(1), resMinigameSpecialClassMazeClear.Index)
}

// 测试守护者相关消息
func testBatch69GuardianMessages(t *testing.T) {
	ptGuardianAuction := &dnfv1.PtGuardianAuction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuardianAuction)
	assert.Equal(t, int32(0), ptGuardianAuction.Error)
	assert.Equal(t, int32(1), ptGuardianAuction.Index)
}

// 测试攻击小队相关消息
func testBatch69AttackSquadMessages(t *testing.T) {
	ptAttackSquadTimer := &dnfv1.PtAttackSquadTimer{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAttackSquadTimer)
	assert.Equal(t, int32(0), ptAttackSquadTimer.Error)
	assert.Equal(t, int32(1), ptAttackSquadTimer.Index)
}

// 测试街机PVP相关消息
func testBatch69ArcadePvpMessages(t *testing.T) {
	ptArcadePvpInfoCurrency := &dnfv1.PtArcadePvpInfoCurrency{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptArcadePvpInfoCurrency)
	assert.Equal(t, int32(0), ptArcadePvpInfoCurrency.Error)
	assert.Equal(t, int32(1), ptArcadePvpInfoCurrency.Index)
}

// 测试隐藏聊天相关消息
func testBatch69HiddenChattingMessages(t *testing.T) {
	resHiddenChattingAdd := &dnfv1.ResHiddenChattingAdd{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHiddenChattingAdd)
	assert.Equal(t, int32(0), resHiddenChattingAdd.Error)
	assert.Equal(t, int32(1), resHiddenChattingAdd.Index)
}

// 测试战斗联赛相关消息
func testBatch69BattleleagueMessages(t *testing.T) {
	notifyBattleleaguePveDestroySummonstone := &dnfv1.NotifyBattleleaguePveDestroySummonstone{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBattleleaguePveDestroySummonstone)
	assert.Equal(t, int32(0), notifyBattleleaguePveDestroySummonstone.Error)
	assert.Equal(t, int32(1), notifyBattleleaguePveDestroySummonstone.Index)

	ptBattleleaguePveRecord := &dnfv1.PtBattleleaguePveRecord{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptBattleleaguePveRecord)
	assert.Equal(t, int32(0), ptBattleleaguePveRecord.Error)
	assert.Equal(t, int32(1), ptBattleleaguePveRecord.Index)
}

// 测试设施相关消息
func testBatch69FacilityMessages(t *testing.T) {
	ptFacilityInfo := &dnfv1.PtFacilityInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptFacilityInfo)
	assert.Equal(t, int32(0), ptFacilityInfo.Error)
	assert.Equal(t, int32(1), ptFacilityInfo.Index)
}

// 测试成就相关消息
func testBatch69AchievementMessages(t *testing.T) {
	ptAchievementReward := &dnfv1.PtAchievementReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAchievementReward)
	assert.Equal(t, int32(0), ptAchievementReward.Error)
	assert.Equal(t, int32(1), ptAchievementReward.Index)
}

// 测试经验相关消息
func testBatch69ExpMessages(t *testing.T) {
	ptExpInfo := &dnfv1.PtExpInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptExpInfo)
	assert.Equal(t, int32(0), ptExpInfo.Error)
	assert.Equal(t, int32(1), ptExpInfo.Index)
}

// 测试免费转职相关消息
func testBatch69FreeChangeMessages(t *testing.T) {
	resFreeChangeJob := &dnfv1.ResFreeChangeJob{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resFreeChangeJob)
	assert.Equal(t, int32(0), resFreeChangeJob.Error)
	assert.Equal(t, int32(1), resFreeChangeJob.Index)

	reqFreeChangeJob := &dnfv1.ReqFreeChangeJob{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqFreeChangeJob)
	assert.Equal(t, int32(0), reqFreeChangeJob.Error)
	assert.Equal(t, int32(1), reqFreeChangeJob.Index)
}

// 测试福利相关消息
func testBatch69WelfareMessages(t *testing.T) {
	reqWelfareFindRewardGet := &dnfv1.ReqWelfareFindRewardGet{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqWelfareFindRewardGet)
	assert.Equal(t, int32(0), reqWelfareFindRewardGet.Error)
	assert.Equal(t, int32(1), reqWelfareFindRewardGet.Index)
}

// 测试进入城镇相关消息
func testBatch69EnterToTownMessages(t *testing.T) {
	resEnterToTown := &dnfv1.ResEnterToTown{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEnterToTown)
	assert.Equal(t, int32(0), resEnterToTown.Error)
	assert.Equal(t, int32(1), resEnterToTown.Index)
}

// 测试公告板相关消息
func testBatch69BoardMessages(t *testing.T) {
	enumBoardResultType := &dnfv1.EnumBoardResultType{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumBoardResultType)
	assert.Equal(t, int32(0), enumBoardResultType.Error)
	assert.Equal(t, int32(1), enumBoardResultType.Index)
}

// 测试等待相关消息
func testBatch69WaitingMessages(t *testing.T) {
	resWaitingToUsersLoading := &dnfv1.ResWaitingToUsersLoading{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWaitingToUsersLoading)
	assert.Equal(t, int32(0), resWaitingToUsersLoading.Error)
	assert.Equal(t, int32(1), resWaitingToUsersLoading.Index)
}

// 测试突袭相关消息
func testBatch69RaidMessages(t *testing.T) {
	notifyRaidUseBuff := &dnfv1.NotifyRaidUseBuff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyRaidUseBuff)
	assert.Equal(t, int32(0), notifyRaidUseBuff.Error)
	assert.Equal(t, int32(1), notifyRaidUseBuff.Index)

	reqRaidBuyBuff := &dnfv1.ReqRaidBuyBuff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRaidBuyBuff)
	assert.Equal(t, int32(0), reqRaidBuyBuff.Error)
	assert.Equal(t, int32(1), reqRaidBuyBuff.Index)

	reqRaidSyncNodeVariable := &dnfv1.ReqRaidSyncNodeVariable{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRaidSyncNodeVariable)
	assert.Equal(t, int32(0), reqRaidSyncNodeVariable.Error)
	assert.Equal(t, int32(1), reqRaidSyncNodeVariable.Index)
}

// 测试赛丽亚相关消息
func testBatch69SeriaMessages(t *testing.T) {
	resGetSeriaBuff := &dnfv1.ResGetSeriaBuff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGetSeriaBuff)
	assert.Equal(t, int32(0), resGetSeriaBuff.Error)
	assert.Equal(t, int32(1), resGetSeriaBuff.Index)
}

// 测试补剂相关消息
func testBatch69TonicMessages(t *testing.T) {
	resTonicUpgrade := &dnfv1.ResTonicUpgrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTonicUpgrade)
	assert.Equal(t, int32(0), resTonicUpgrade.Error)
	assert.Equal(t, int32(1), resTonicUpgrade.Index)
}

// 测试头像相关消息
func testBatch69AvatarMessages(t *testing.T) {
	ptAvatarGuid := &dnfv1.PtAvatarGuid{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAvatarGuid)
	assert.Equal(t, int32(0), ptAvatarGuid.Error)
	assert.Equal(t, int32(1), ptAvatarGuid.Index)
}

// 测试公会餐厅相关消息
func testBatch69GuildDiningMessages(t *testing.T) {
	resAlarmGuildDiningBuy := &dnfv1.ResAlarmGuildDiningBuy{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAlarmGuildDiningBuy)
	assert.Equal(t, int32(0), resAlarmGuildDiningBuy.Error)
	assert.Equal(t, int32(1), resAlarmGuildDiningBuy.Index)
}

// 测试迷宫相关消息
func testBatch69MazeMessages(t *testing.T) {
	resMinigameSpecialClassMazeClear := &dnfv1.ResMinigameSpecialClassMazeClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameSpecialClassMazeClear)
	assert.Equal(t, int32(0), resMinigameSpecialClassMazeClear.Error)
	assert.Equal(t, int32(1), resMinigameSpecialClassMazeClear.Index)
}

// 测试任务相关消息
func testBatch69ErrandMessages(t *testing.T) {
	ptErrandClear := &dnfv1.PtErrandClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptErrandClear)
	assert.Equal(t, int32(0), ptErrandClear.Error)
	assert.Equal(t, int32(1), ptErrandClear.Index)
}

// 测试拍卖相关消息
func testBatch69AuctionMessages(t *testing.T) {
	resAuctionDetailItemList := &dnfv1.ResAuctionDetailItemList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAuctionDetailItemList)
	assert.Equal(t, int32(0), resAuctionDetailItemList.Error)
	assert.Equal(t, int32(1), resAuctionDetailItemList.Index)
}

// 测试骑士相关消息
func testBatch69ChivalryMessages(t *testing.T) {
	notifyChivalryLike := &dnfv1.NotifyChivalryLike{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyChivalryLike)
	assert.Equal(t, int32(0), notifyChivalryLike.Error)
	assert.Equal(t, int32(1), notifyChivalryLike.Index)
}

// 测试验证相关消息
func testBatch69VerificationMessages(t *testing.T) {
	reqVerificationStart := &dnfv1.ReqVerificationStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqVerificationStart)
	assert.Equal(t, int32(0), reqVerificationStart.Error)
	assert.Equal(t, int32(1), reqVerificationStart.Index)
}

// 测试好友相关消息
func testBatch69FriendMessages(t *testing.T) {
	reqDeleteFriend := &dnfv1.ReqDeleteFriend{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDeleteFriend)
	assert.Equal(t, int32(0), reqDeleteFriend.Error)
	assert.Equal(t, int32(1), reqDeleteFriend.Index)
}

// 测试角色相关消息
func testBatch69CharacterMessages(t *testing.T) {
	resChangeCharacterName := &dnfv1.ResChangeCharacterName{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resChangeCharacterName)
	assert.Equal(t, int32(0), resChangeCharacterName.Error)
	assert.Equal(t, int32(1), resChangeCharacterName.Index)
}

// 测试装备相关消息
func testBatch69EquipMessages(t *testing.T) {
	ptEquipIndexSlot := &dnfv1.PtEquipIndexSlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptEquipIndexSlot)
	assert.Equal(t, int32(0), ptEquipIndexSlot.Error)
	assert.Equal(t, int32(1), ptEquipIndexSlot.Index)
}

// 测试物品相关消息
func testBatch69ItemMessages(t *testing.T) {
	defItemConsumable := &dnfv1.DefItemConsumable{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, defItemConsumable)
	assert.Equal(t, int32(0), defItemConsumable.Error)
	assert.Equal(t, int32(1), defItemConsumable.Index)

	reqItemEmblemExtract := &dnfv1.ReqItemEmblemExtract{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemEmblemExtract)
	assert.Equal(t, int32(0), reqItemEmblemExtract.Error)
	assert.Equal(t, int32(1), reqItemEmblemExtract.Index)
}

// 测试龙路相关消息
func testBatch69DragonRoadMessages(t *testing.T) {
	reqDragonRoadTimeBoxList := &dnfv1.ReqDragonRoadTimeBoxList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDragonRoadTimeBoxList)
	assert.Equal(t, int32(0), reqDragonRoadTimeBoxList.Error)
	assert.Equal(t, int32(1), reqDragonRoadTimeBoxList.Index)
}

// 测试公会捐赠相关消息
func testBatch69GuildDonationMessages(t *testing.T) {
	reqGuildDonationResponseHelp := &dnfv1.ReqGuildDonationResponseHelp{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildDonationResponseHelp)
	assert.Equal(t, int32(0), reqGuildDonationResponseHelp.Error)
	assert.Equal(t, int32(1), reqGuildDonationResponseHelp.Index)
}

// 测试协议相关消息
func testBatch69ProtocolMessages(t *testing.T) {
	ptProtocolTransaction := &dnfv1.PtProtocolTransaction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptProtocolTransaction)
	assert.Equal(t, int32(0), ptProtocolTransaction.Error)
	assert.Equal(t, int32(1), ptProtocolTransaction.Index)
}

// 测试历史站点相关消息
func testBatch69HistoricsiteMessages(t *testing.T) {
	notifyHistoricsiteRegisteredEnterCount := &dnfv1.NotifyHistoricsiteRegisteredEnterCount{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyHistoricsiteRegisteredEnterCount)
	assert.Equal(t, int32(0), notifyHistoricsiteRegisteredEnterCount.Error)
	assert.Equal(t, int32(1), notifyHistoricsiteRegisteredEnterCount.Index)

	reqHistoricsiteMemberRecord := &dnfv1.ReqHistoricsiteMemberRecord{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteMemberRecord)
	assert.Equal(t, int32(0), reqHistoricsiteMemberRecord.Error)
	assert.Equal(t, int32(1), reqHistoricsiteMemberRecord.Index)
}

// 测试重新进入相关消息
func testBatch69ReEnterMessages(t *testing.T) {
	notifyRequestToReEnterAcceptDungeon := &dnfv1.NotifyRequestToReEnterAcceptDungeon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyRequestToReEnterAcceptDungeon)
	assert.Equal(t, int32(0), notifyRequestToReEnterAcceptDungeon.Error)
	assert.Equal(t, int32(1), notifyRequestToReEnterAcceptDungeon.Index)
}
