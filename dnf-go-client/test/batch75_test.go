package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch75Migration 测试第75批迁移的消息类型
func TestBatch75Migration(t *testing.T) {
	t.Run("Raid", testBatch75RaidMessages)
	t.Run("Guild", testBatch75GuildMessages)
	t.Run("Champion", testBatch75ChampionMessages)
	t.Run("Adventurebook", testBatch75AdventurebookMessages)
	t.Run("Recommend", testBatch75RecommendMessages)
	t.Run("Maas", testBatch75MaasMessages)
	t.Run("Room", testBatch75RoomMessages)
	t.Run("Attack", testBatch75AttackMessages)
	t.Run("Report", testBatch75ReportMessages)
	t.Run("Biling", testBatch75BilingMessages)
	t.Run("Historicsite", testBatch75HistoricsiteMessages)
	t.Run("Battleleague", testBatch75BattleleagueMessages)
	t.Run("Protocol", testBatch75ProtocolMessages)
	t.Run("Chivalry", testBatch75ChivalryMessages)
	t.Run("Redpacket", testBatch75RedpacketMessages)
	t.Run("Item", testBatch75ItemMessages)
	t.Run("Emblem", testBatch75EmblemMessages)
	t.Run("Tonic", testBatch75TonicMessages)
	t.Run("Tlog", testBatch75TlogMessages)
	t.Run("Kick", testBatch75KickMessages)
	t.Run("Map", testBatch75MapMessages)
	t.Run("AdventureUnion", testBatch75AdventureUnionMessages)
	t.Run("Repurchase", testBatch75RepurchaseMessages)
	t.Run("Dining", testBatch75DiningMessages)
	t.Run("GameChannel", testBatch75GameChannelMessages)
	t.Run("Structure", testBatch75StructureMessages)
	t.Run("Team", testBatch75TeamMessages)
	t.Run("Stage", testBatch75StageMessages)
	t.Run("Exit", testBatch75ExitMessages)
	t.Run("Randomoption", testBatch75RandomoptionMessages)
	t.Run("DreamMaze", testBatch75DreamMazeMessages)
	t.Run("Lock", testBatch75LockMessages)
	t.Run("Minigame", testBatch75MinigameMessages)
}

// 测试突袭相关消息
func testBatch75RaidMessages(t *testing.T) {
	ptRaidRewardInfo := &dnfv1.PtRaidRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRaidRewardInfo)
	assert.Equal(t, int32(0), ptRaidRewardInfo.Error)
	assert.Equal(t, int32(1), ptRaidRewardInfo.Index)

	ptRaidUserRewards := &dnfv1.PtRaidUserRewards{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRaidUserRewards)
	assert.Equal(t, int32(0), ptRaidUserRewards.Error)
	assert.Equal(t, int32(1), ptRaidUserRewards.Index)
}

// 测试公会相关消息
func testBatch75GuildMessages(t *testing.T) {
	resGuildDonationAccumulateReward2 := &dnfv1.ResGuildDonationAccumulateReward2{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildDonationAccumulateReward2)
	assert.Equal(t, int32(0), resGuildDonationAccumulateReward2.Error)
	assert.Equal(t, int32(1), resGuildDonationAccumulateReward2.Index)

	notifyGuildRedpacketAdd := &dnfv1.NotifyGuildRedpacketAdd{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyGuildRedpacketAdd)
	assert.Equal(t, int32(0), notifyGuildRedpacketAdd.Error)
	assert.Equal(t, int32(1), notifyGuildRedpacketAdd.Index)

	resGuildDonationNotify := &dnfv1.ResGuildDonationNotify{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildDonationNotify)
	assert.Equal(t, int32(0), resGuildDonationNotify.Error)
	assert.Equal(t, int32(1), resGuildDonationNotify.Index)

	reqGuildDiningAction := &dnfv1.ReqGuildDiningAction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildDiningAction)
	assert.Equal(t, int32(0), reqGuildDiningAction.Error)
	assert.Equal(t, int32(1), reqGuildDiningAction.Index)

	reqGuildStructureBuild := &dnfv1.ReqGuildStructureBuild{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildStructureBuild)
	assert.Equal(t, int32(0), reqGuildStructureBuild.Error)
	assert.Equal(t, int32(1), reqGuildStructureBuild.Index)
}

// 测试冠军相关消息
func testBatch75ChampionMessages(t *testing.T) {
	ptChampionStageInfo := &dnfv1.PtChampionStageInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptChampionStageInfo)
	assert.Equal(t, int32(0), ptChampionStageInfo.Error)
	assert.Equal(t, int32(1), ptChampionStageInfo.Index)
}

// 测试冒险书相关消息
func testBatch75AdventurebookMessages(t *testing.T) {
	resAdventurebookUpdateCondition := &dnfv1.ResAdventurebookUpdateCondition{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAdventurebookUpdateCondition)
	assert.Equal(t, int32(0), resAdventurebookUpdateCondition.Error)
	assert.Equal(t, int32(1), resAdventurebookUpdateCondition.Index)
}

// 测试推荐相关消息
func testBatch75RecommendMessages(t *testing.T) {
	ptRecommendGuild := &dnfv1.PtRecommendGuild{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRecommendGuild)
	assert.Equal(t, int32(0), ptRecommendGuild.Error)
	assert.Equal(t, int32(1), ptRecommendGuild.Index)
}

// 测试MAAS相关消息
func testBatch75MaasMessages(t *testing.T) {
	reqMaasRemind := &dnfv1.ReqMaasRemind{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMaasRemind)
	assert.Equal(t, int32(0), reqMaasRemind.Error)
	assert.Equal(t, int32(1), reqMaasRemind.Index)
}

// 测试房间相关消息
func testBatch75RoomMessages(t *testing.T) {
	reqMakeRoom := &dnfv1.ReqMakeRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMakeRoom)
	assert.Equal(t, int32(0), reqMakeRoom.Error)
	assert.Equal(t, int32(1), reqMakeRoom.Index)

	resKickRoom := &dnfv1.ResKickRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resKickRoom)
	assert.Equal(t, int32(0), resKickRoom.Error)
	assert.Equal(t, int32(1), resKickRoom.Index)
}

// 测试攻击相关消息
func testBatch75AttackMessages(t *testing.T) {
	ptAttackSquadBoardInfo := &dnfv1.PtAttackSquadBoardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAttackSquadBoardInfo)
	assert.Equal(t, int32(0), ptAttackSquadBoardInfo.Error)
	assert.Equal(t, int32(1), ptAttackSquadBoardInfo.Index)
}

// 测试报告相关消息
func testBatch75ReportMessages(t *testing.T) {
	ptReportInfo := &dnfv1.PtReportInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptReportInfo)
	assert.Equal(t, int32(0), ptReportInfo.Error)
	assert.Equal(t, int32(1), ptReportInfo.Index)
}

// 测试Biling相关消息
func testBatch75BilingMessages(t *testing.T) {
	reqBilingKrBalance := &dnfv1.ReqBilingKrBalance{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqBilingKrBalance)
	assert.Equal(t, int32(0), reqBilingKrBalance.Error)
	assert.Equal(t, int32(1), reqBilingKrBalance.Index)
}

// 测试历史站点相关消息
func testBatch75HistoricsiteMessages(t *testing.T) {
	resHistoricsiteRegisterEnter := &dnfv1.ResHistoricsiteRegisterEnter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteRegisterEnter)
	assert.Equal(t, int32(0), resHistoricsiteRegisterEnter.Error)
	assert.Equal(t, int32(1), resHistoricsiteRegisterEnter.Index)

	resHistoricsiteResultRecord := &dnfv1.ResHistoricsiteResultRecord{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteResultRecord)
	assert.Equal(t, int32(0), resHistoricsiteResultRecord.Error)
	assert.Equal(t, int32(1), resHistoricsiteResultRecord.Index)

	reqHistoricsiteRelicList := &dnfv1.ReqHistoricsiteRelicList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteRelicList)
	assert.Equal(t, int32(0), reqHistoricsiteRelicList.Error)
	assert.Equal(t, int32(1), reqHistoricsiteRelicList.Index)
}

// 测试战斗联赛相关消息
func testBatch75BattleleagueMessages(t *testing.T) {
	notifyBattleleagueTransitionCard := &dnfv1.NotifyBattleleagueTransitionCard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBattleleagueTransitionCard)
	assert.Equal(t, int32(0), notifyBattleleagueTransitionCard.Error)
	assert.Equal(t, int32(1), notifyBattleleagueTransitionCard.Index)
}

// 测试协议相关消息
func testBatch75ProtocolMessages(t *testing.T) {
	ptProtocolTransaction := &dnfv1.PtProtocolTransaction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptProtocolTransaction)
	assert.Equal(t, int32(0), ptProtocolTransaction.Error)
	assert.Equal(t, int32(1), ptProtocolTransaction.Index)
}

// 测试骑士相关消息
func testBatch75ChivalryMessages(t *testing.T) {
	reqChivalryLike := &dnfv1.ReqChivalryLike{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqChivalryLike)
	assert.Equal(t, int32(0), reqChivalryLike.Error)
	assert.Equal(t, int32(1), reqChivalryLike.Index)
}

// 测试红包相关消息
func testBatch75RedpacketMessages(t *testing.T) {
	notifyGuildRedpacketAdd := &dnfv1.NotifyGuildRedpacketAdd{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyGuildRedpacketAdd)
	assert.Equal(t, int32(0), notifyGuildRedpacketAdd.Error)
	assert.Equal(t, int32(1), notifyGuildRedpacketAdd.Index)
}

// 测试物品相关消息
func testBatch75ItemMessages(t *testing.T) {
	ptItemUseInvenSlot := &dnfv1.PtItemUseInvenSlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptItemUseInvenSlot)
	assert.Equal(t, int32(0), ptItemUseInvenSlot.Error)
	assert.Equal(t, int32(1), ptItemUseInvenSlot.Index)

	resItemLock := &dnfv1.ResItemLock{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemLock)
	assert.Equal(t, int32(0), resItemLock.Error)
	assert.Equal(t, int32(1), resItemLock.Index)
}

// 测试徽章相关消息
func testBatch75EmblemMessages(t *testing.T) {
	resEmblemList := &dnfv1.ResEmblemList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEmblemList)
	assert.Equal(t, int32(0), resEmblemList.Error)
	assert.Equal(t, int32(1), resEmblemList.Index)
}

// 测试补品相关消息
func testBatch75TonicMessages(t *testing.T) {
	resTonicInfo := &dnfv1.ResTonicInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTonicInfo)
	assert.Equal(t, int32(0), resTonicInfo.Error)
	assert.Equal(t, int32(1), resTonicInfo.Index)
}

// 测试TLOG相关消息
func testBatch75TlogMessages(t *testing.T) {
	reqTlogFrameSyncTrace := &dnfv1.ReqTlogFrameSyncTrace{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqTlogFrameSyncTrace)
	assert.Equal(t, int32(0), reqTlogFrameSyncTrace.Error)
	assert.Equal(t, int32(1), reqTlogFrameSyncTrace.Index)
}

// 测试踢出相关消息
func testBatch75KickMessages(t *testing.T) {
	resKickRoom := &dnfv1.ResKickRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resKickRoom)
	assert.Equal(t, int32(0), resKickRoom.Error)
	assert.Equal(t, int32(1), resKickRoom.Index)
}

// 测试地图相关消息
func testBatch75MapMessages(t *testing.T) {
	ptMapGuids := &dnfv1.PtMapGuids{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMapGuids)
	assert.Equal(t, int32(0), ptMapGuids.Error)
	assert.Equal(t, int32(1), ptMapGuids.Index)
}

// 测试冒险联盟相关消息
func testBatch75AdventureUnionMessages(t *testing.T) {
	resAdventureUnionSubdueInfo := &dnfv1.ResAdventureUnionSubdueInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAdventureUnionSubdueInfo)
	assert.Equal(t, int32(0), resAdventureUnionSubdueInfo.Error)
	assert.Equal(t, int32(1), resAdventureUnionSubdueInfo.Index)

	resAdventureUnionShowCheckOther := &dnfv1.ResAdventureUnionShowCheckOther{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAdventureUnionShowCheckOther)
	assert.Equal(t, int32(0), resAdventureUnionShowCheckOther.Error)
	assert.Equal(t, int32(1), resAdventureUnionShowCheckOther.Index)
}

// 测试重新购买相关消息
func testBatch75RepurchaseMessages(t *testing.T) {
	ptRepurchaseEquip := &dnfv1.PtRepurchaseEquip{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRepurchaseEquip)
	assert.Equal(t, int32(0), ptRepurchaseEquip.Error)
	assert.Equal(t, int32(1), ptRepurchaseEquip.Index)
}

// 测试餐厅相关消息
func testBatch75DiningMessages(t *testing.T) {
	reqGuildDiningAction := &dnfv1.ReqGuildDiningAction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildDiningAction)
	assert.Equal(t, int32(0), reqGuildDiningAction.Error)
	assert.Equal(t, int32(1), reqGuildDiningAction.Index)
}

// 测试游戏频道相关消息
func testBatch75GameChannelMessages(t *testing.T) {
	ptGameChannel := &dnfv1.PtGameChannel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGameChannel)
	assert.Equal(t, int32(0), ptGameChannel.Error)
	assert.Equal(t, int32(1), ptGameChannel.Index)
}

// 测试结构相关消息
func testBatch75StructureMessages(t *testing.T) {
	reqGuildStructureBuild := &dnfv1.ReqGuildStructureBuild{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildStructureBuild)
	assert.Equal(t, int32(0), reqGuildStructureBuild.Error)
	assert.Equal(t, int32(1), reqGuildStructureBuild.Index)
}

// 测试团队相关消息
func testBatch75TeamMessages(t *testing.T) {
	enumTeam := &dnfv1.EnumTeam{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumTeam)
	assert.Equal(t, int32(0), enumTeam.Error)
	assert.Equal(t, int32(1), enumTeam.Index)
}

// 测试阶段相关消息
func testBatch75StageMessages(t *testing.T) {
	resEnterStage := &dnfv1.ResEnterStage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEnterStage)
	assert.Equal(t, int32(0), resEnterStage.Error)
	assert.Equal(t, int32(1), resEnterStage.Index)
}

// 测试退出相关消息
func testBatch75ExitMessages(t *testing.T) {
	resExitCharacter := &dnfv1.ResExitCharacter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resExitCharacter)
	assert.Equal(t, int32(0), resExitCharacter.Error)
	assert.Equal(t, int32(1), resExitCharacter.Index)
}

// 测试随机选项相关消息
func testBatch75RandomoptionMessages(t *testing.T) {
	reqRandomoptionUnlock := &dnfv1.ReqRandomoptionUnlock{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRandomoptionUnlock)
	assert.Equal(t, int32(0), reqRandomoptionUnlock.Error)
	assert.Equal(t, int32(1), reqRandomoptionUnlock.Index)
}

// 测试梦迷宫相关消息
func testBatch75DreamMazeMessages(t *testing.T) {
	reqDreamMazePlaymember := &dnfv1.ReqDreamMazePlaymember{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDreamMazePlaymember)
	assert.Equal(t, int32(0), reqDreamMazePlaymember.Error)
	assert.Equal(t, int32(1), reqDreamMazePlaymember.Index)
}

// 测试锁定相关消息
func testBatch75LockMessages(t *testing.T) {
	resItemLock := &dnfv1.ResItemLock{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemLock)
	assert.Equal(t, int32(0), resItemLock.Error)
	assert.Equal(t, int32(1), resItemLock.Index)
}

// 测试小游戏相关消息
func testBatch75MinigameMessages(t *testing.T) {
	resMinigameSpecialClassStart := &dnfv1.ResMinigameSpecialClassStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameSpecialClassStart)
	assert.Equal(t, int32(0), resMinigameSpecialClassStart.Error)
	assert.Equal(t, int32(1), resMinigameSpecialClassStart.Index)
}
