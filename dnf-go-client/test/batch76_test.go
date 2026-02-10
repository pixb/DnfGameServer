package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch76Migration 测试第76批迁移的消息类型
func TestBatch76Migration(t *testing.T) {
	t.Run("Adventurebook", testBatch76AdventurebookMessages)
	t.Run("AttackSquad", testBatch76AttackSquadMessages)
	t.Run("Raid", testBatch76RaidMessages)
	t.Run("Guild", testBatch76GuildMessages)
	t.Run("Creature", testBatch76CreatureMessages)
	t.Run("Seria", testBatch76SeriaMessages)
	t.Run("Gvoice", testBatch76GvoiceMessages)
	t.Run("Equipment", testBatch76EquipmentMessages)
	t.Run("Alarm", testBatch76AlarmMessages)
	t.Run("Battleleague", testBatch76BattleleagueMessages)
	t.Run("Historicsite", testBatch76HistoricsiteMessages)
	t.Run("Chat", testBatch76ChatMessages)
	t.Run("Bookmark", testBatch76BookmarkMessages)
	t.Run("Frame", testBatch76FrameMessages)
	t.Run("Wating", testBatch76WatingMessages)
	t.Run("Minigame", testBatch76MinigameMessages)
	t.Run("Consume", testBatch76ConsumeMessages)
	t.Run("Auction", testBatch76AuctionMessages)
	t.Run("Enum", testBatch76EnumMessages)
	t.Run("Equip", testBatch76EquipMessages)
	t.Run("Exp", testBatch76ExpMessages)
	t.Run("Party", testBatch76PartyMessages)
	t.Run("Reward", testBatch76RewardMessages)
	t.Run("Board", testBatch76BoardMessages)
	t.Run("Collection", testBatch76CollectionMessages)
	t.Run("Town", testBatch76TownMessages)
	t.Run("Character", testBatch76CharacterMessages)
}

// 测试冒险书相关消息
func testBatch76AdventurebookMessages(t *testing.T) {
	reqAdventurebookSpecialReward := &dnfv1.ReqAdventurebookSpecialReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventurebookSpecialReward)
	assert.Equal(t, int32(0), reqAdventurebookSpecialReward.Error)
	assert.Equal(t, int32(1), reqAdventurebookSpecialReward.Index)
}

// 测试攻击小队相关消息
func testBatch76AttackSquadMessages(t *testing.T) {
	resAttackSquadList := &dnfv1.ResAttackSquadList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAttackSquadList)
	assert.Equal(t, int32(0), resAttackSquadList.Error)
	assert.Equal(t, int32(1), resAttackSquadList.Index)

	reqCancelAttackSquadJoin := &dnfv1.ReqCancelAttackSquadJoin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCancelAttackSquadJoin)
	assert.Equal(t, int32(0), reqCancelAttackSquadJoin.Error)
	assert.Equal(t, int32(1), reqCancelAttackSquadJoin.Index)
}

// 测试突袭相关消息
func testBatch76RaidMessages(t *testing.T) {
	reqRaidSyncNodeVariable := &dnfv1.ReqRaidSyncNodeVariable{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRaidSyncNodeVariable)
	assert.Equal(t, int32(0), reqRaidSyncNodeVariable.Error)
	assert.Equal(t, int32(1), reqRaidSyncNodeVariable.Index)
}

// 测试公会相关消息
func testBatch76GuildMessages(t *testing.T) {
	resGuildMaterialProduction := &dnfv1.ResGuildMaterialProduction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildMaterialProduction)
	assert.Equal(t, int32(0), resGuildMaterialProduction.Error)
	assert.Equal(t, int32(1), resGuildMaterialProduction.Index)

	ptGuildHistoryValue := &dnfv1.PtGuildHistoryValue{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildHistoryValue)
	assert.Equal(t, int32(0), ptGuildHistoryValue.Error)
	assert.Equal(t, int32(1), ptGuildHistoryValue.Index)

	enumGuildStructureStatus := &dnfv1.EnumGuildStructureStatus{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumGuildStructureStatus)
	assert.Equal(t, int32(0), enumGuildStructureStatus.Error)
	assert.Equal(t, int32(1), enumGuildStructureStatus.Index)

	resGuildDonationRequestHelpCancel := &dnfv1.ResGuildDonationRequestHelpCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildDonationRequestHelpCancel)
	assert.Equal(t, int32(0), resGuildDonationRequestHelpCancel.Error)
	assert.Equal(t, int32(1), resGuildDonationRequestHelpCancel.Index)
}

// 测试生物相关消息
func testBatch76CreatureMessages(t *testing.T) {
	reqCreatureErrandCancel := &dnfv1.ReqCreatureErrandCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCreatureErrandCancel)
	assert.Equal(t, int32(0), reqCreatureErrandCancel.Error)
	assert.Equal(t, int32(1), reqCreatureErrandCancel.Index)

	resCreatureErrandList := &dnfv1.ResCreatureErrandList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureErrandList)
	assert.Equal(t, int32(0), resCreatureErrandList.Error)
	assert.Equal(t, int32(1), resCreatureErrandList.Index)
}

// 测试Seria相关消息
func testBatch76SeriaMessages(t *testing.T) {
	resGetSeriaBuff := &dnfv1.ResGetSeriaBuff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGetSeriaBuff)
	assert.Equal(t, int32(0), resGetSeriaBuff.Error)
	assert.Equal(t, int32(1), resGetSeriaBuff.Index)
}

// 测试GVoice相关消息
func testBatch76GvoiceMessages(t *testing.T) {
	reqChangeRoleGvoiceMember := &dnfv1.ReqChangeRoleGvoiceMember{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqChangeRoleGvoiceMember)
	assert.Equal(t, int32(0), reqChangeRoleGvoiceMember.Error)
	assert.Equal(t, int32(1), reqChangeRoleGvoiceMember.Index)
}

// 测试装备相关消息
func testBatch76EquipmentMessages(t *testing.T) {
	resEquipmentRarify := &dnfv1.ResEquipmentRarify{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEquipmentRarify)
	assert.Equal(t, int32(0), resEquipmentRarify.Error)
	assert.Equal(t, int32(1), resEquipmentRarify.Index)

	ptEquipPutOnOff := &dnfv1.PtEquipPutOnOff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptEquipPutOnOff)
	assert.Equal(t, int32(0), ptEquipPutOnOff.Error)
	assert.Equal(t, int32(1), ptEquipPutOnOff.Index)
}

// 测试警报相关消息
func testBatch76AlarmMessages(t *testing.T) {
	resAlarmGuildJoin := &dnfv1.ResAlarmGuildJoin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAlarmGuildJoin)
	assert.Equal(t, int32(0), resAlarmGuildJoin.Error)
	assert.Equal(t, int32(1), resAlarmGuildJoin.Index)
}

// 测试战斗联赛相关消息
func testBatch76BattleleagueMessages(t *testing.T) {
	reqBattleleaguePvpRecord := &dnfv1.ReqBattleleaguePvpRecord{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqBattleleaguePvpRecord)
	assert.Equal(t, int32(0), reqBattleleaguePvpRecord.Error)
	assert.Equal(t, int32(1), reqBattleleaguePvpRecord.Index)
}

// 测试历史站点相关消息
func testBatch76HistoricsiteMessages(t *testing.T) {
	ptHistoricsiteAltarRoomInfo := &dnfv1.PtHistoricsiteAltarRoomInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptHistoricsiteAltarRoomInfo)
	assert.Equal(t, int32(0), ptHistoricsiteAltarRoomInfo.Error)
	assert.Equal(t, int32(1), ptHistoricsiteAltarRoomInfo.Index)
}

// 测试聊天相关消息
func testBatch76ChatMessages(t *testing.T) {
	resChatChannelList := &dnfv1.ResChatChannelList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resChatChannelList)
	assert.Equal(t, int32(0), resChatChannelList.Error)
	assert.Equal(t, int32(1), resChatChannelList.Index)

	ptChat := &dnfv1.PtChat{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptChat)
	assert.Equal(t, int32(0), ptChat.Error)
	assert.Equal(t, int32(1), ptChat.Index)
}

// 测试书签相关消息
func testBatch76BookmarkMessages(t *testing.T) {
	reqReleaseBookmark := &dnfv1.ReqReleaseBookmark{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqReleaseBookmark)
	assert.Equal(t, int32(0), reqReleaseBookmark.Error)
	assert.Equal(t, int32(1), reqReleaseBookmark.Index)

	resReleaseBookmark := &dnfv1.ResReleaseBookmark{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resReleaseBookmark)
	assert.Equal(t, int32(0), resReleaseBookmark.Error)
	assert.Equal(t, int32(1), resReleaseBookmark.Index)
}

// 测试帧数据相关消息
func testBatch76FrameMessages(t *testing.T) {
	frameDataNoti := &dnfv1.FrameDataNoti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, frameDataNoti)
	assert.Equal(t, int32(0), frameDataNoti.Error)
	assert.Equal(t, int32(1), frameDataNoti.Index)

	resultReasonRes := &dnfv1.ResultReasonRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resultReasonRes)
	assert.Equal(t, int32(0), resultReasonRes.Error)
	assert.Equal(t, int32(1), resultReasonRes.Index)

	inputReq := &dnfv1.InputReq{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, inputReq)
	assert.Equal(t, int32(0), inputReq.Error)
	assert.Equal(t, int32(1), inputReq.Index)
}

// 测试等待列表相关消息
func testBatch76WatingMessages(t *testing.T) {
	resWatingListInfoSync := &dnfv1.ResWatingListInfoSync{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWatingListInfoSync)
	assert.Equal(t, int32(0), resWatingListInfoSync.Error)
	assert.Equal(t, int32(1), resWatingListInfoSync.Index)
}

// 测试小游戏相关消息
func testBatch76MinigameMessages(t *testing.T) {
	resMinigame77ValentineReward := &dnfv1.ResMinigame77ValentineReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigame77ValentineReward)
	assert.Equal(t, int32(0), resMinigame77ValentineReward.Error)
	assert.Equal(t, int32(1), resMinigame77ValentineReward.Index)

	resMinigameBreakingStart := &dnfv1.ResMinigameBreakingStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameBreakingStart)
	assert.Equal(t, int32(0), resMinigameBreakingStart.Error)
	assert.Equal(t, int32(1), resMinigameBreakingStart.Index)

	ptMinigameSpecialClassInfo := &dnfv1.PtMinigameSpecialClassInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMinigameSpecialClassInfo)
	assert.Equal(t, int32(0), ptMinigameSpecialClassInfo.Error)
	assert.Equal(t, int32(1), ptMinigameSpecialClassInfo.Index)
}

// 测试消耗相关消息
func testBatch76ConsumeMessages(t *testing.T) {
	ptConsumeItems := &dnfv1.PtConsumeItems{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptConsumeItems)
	assert.Equal(t, int32(0), ptConsumeItems.Error)
	assert.Equal(t, int32(1), ptConsumeItems.Index)
}

// 测试拍卖相关消息
func testBatch76AuctionMessages(t *testing.T) {
	reqAuctionRegisterComplete := &dnfv1.ReqAuctionRegisterComplete{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAuctionRegisterComplete)
	assert.Equal(t, int32(0), reqAuctionRegisterComplete.Error)
	assert.Equal(t, int32(1), reqAuctionRegisterComplete.Index)

	reqAuctionCategoryItemList := &dnfv1.ReqAuctionCategoryItemList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAuctionCategoryItemList)
	assert.Equal(t, int32(0), reqAuctionCategoryItemList.Error)
	assert.Equal(t, int32(1), reqAuctionCategoryItemList.Index)
}

// 测试枚举相关消息
func testBatch76EnumMessages(t *testing.T) {
	enumGuildStructureStatus := &dnfv1.EnumGuildStructureStatus{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumGuildStructureStatus)
	assert.Equal(t, int32(0), enumGuildStructureStatus.Error)
	assert.Equal(t, int32(1), enumGuildStructureStatus.Index)
}

// 测试装备相关消息
func testBatch76EquipMessages(t *testing.T) {
	ptEquipPutOnOff := &dnfv1.PtEquipPutOnOff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptEquipPutOnOff)
	assert.Equal(t, int32(0), ptEquipPutOnOff.Error)
	assert.Equal(t, int32(1), ptEquipPutOnOff.Index)
}

// 测试经验相关消息
func testBatch76ExpMessages(t *testing.T) {
	ptExpDetailinfo := &dnfv1.PtExpDetailinfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptExpDetailinfo)
	assert.Equal(t, int32(0), ptExpDetailinfo.Error)
	assert.Equal(t, int32(1), ptExpDetailinfo.Index)
}

// 测试队伍相关消息
func testBatch76PartyMessages(t *testing.T) {
	notifyPartyUpdateMember := &dnfv1.NotifyPartyUpdateMember{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyPartyUpdateMember)
	assert.Equal(t, int32(0), notifyPartyUpdateMember.Error)
	assert.Equal(t, int32(1), notifyPartyUpdateMember.Index)
}

// 测试奖励相关消息
func testBatch76RewardMessages(t *testing.T) {
	ptRewardItem := &dnfv1.PtRewardItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRewardItem)
	assert.Equal(t, int32(0), ptRewardItem.Error)
	assert.Equal(t, int32(1), ptRewardItem.Index)
}

// 测试棋盘相关消息
func testBatch76BoardMessages(t *testing.T) {
	resBoardGameSync := &dnfv1.ResBoardGameSync{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBoardGameSync)
	assert.Equal(t, int32(0), resBoardGameSync.Error)
	assert.Equal(t, int32(1), resBoardGameSync.Index)
}

// 测试收集相关消息
func testBatch76CollectionMessages(t *testing.T) {
	reqCollectionReward := &dnfv1.ReqCollectionReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCollectionReward)
	assert.Equal(t, int32(0), reqCollectionReward.Error)
	assert.Equal(t, int32(1), reqCollectionReward.Index)
}

// 测试城镇相关消息
func testBatch76TownMessages(t *testing.T) {
	reqEnterToTown := &dnfv1.ReqEnterToTown{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqEnterToTown)
	assert.Equal(t, int32(0), reqEnterToTown.Error)
	assert.Equal(t, int32(1), reqEnterToTown.Index)
}

// 测试角色相关消息
func testBatch76CharacterMessages(t *testing.T) {
	ptCharacterRaidRoundInfo := &dnfv1.PtCharacterRaidRoundInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCharacterRaidRoundInfo)
	assert.Equal(t, int32(0), ptCharacterRaidRoundInfo.Error)
	assert.Equal(t, int32(1), ptCharacterRaidRoundInfo.Index)
}
