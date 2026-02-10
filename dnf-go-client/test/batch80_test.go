package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch80Migration 测试第80批迁移的消息类型
func TestBatch80Migration(t *testing.T) {
	t.Run("Item", testBatch80ItemMessages)
	t.Run("Guild", testBatch80GuildMessages)
	t.Run("AttackSquad", testBatch80AttackSquadMessages)
	t.Run("Minigame", testBatch80MinigameMessages)
	t.Run("Quest", testBatch80QuestMessages)
	t.Run("Control", testBatch80ControlMessages)
	t.Run("Historicsite", testBatch80HistoricsiteMessages)
	t.Run("Raid", testBatch80RaidMessages)
	t.Run("User", testBatch80UserMessages)
	t.Run("Ranking", testBatch80RankingMessages)
	t.Run("Party", testBatch80PartyMessages)
	t.Run("Emblem", testBatch80EmblemMessages)
	t.Run("Friend", testBatch80FriendMessages)
	t.Run("Cera", testBatch80CeraMessages)
	t.Run("Message", testBatch80MessageMessages)
	t.Run("Voice", testBatch80VoiceMessages)
	t.Run("TimeBox", testBatch80TimeBoxMessages)
	t.Run("Board", testBatch80BoardMessages)
	t.Run("Pvp", testBatch80PvpMessages)
	t.Run("Facility", testBatch80FacilityMessages)
	t.Run("Team", testBatch80TeamMessages)
	t.Run("Money", testBatch80MoneyMessages)
	t.Run("Proposal", testBatch80ProposalMessages)
	t.Run("Dungeon", testBatch80DungeonMessages)
	t.Run("Auction", testBatch80AuctionMessages)
	t.Run("Equip", testBatch80EquipMessages)
	t.Run("Syscmd", testBatch80SyscmdMessages)
	t.Run("Reforge", testBatch80ReforgeMessages)
	t.Run("Fatigue", testBatch80FatigueMessages)
	t.Run("Lockstep", testBatch80LockstepMessages)
	t.Run("Warehouse", testBatch80WarehouseMessages)
	t.Run("Dining", testBatch80DiningMessages)
	t.Run("Mentee", testBatch80MenteeMessages)
	t.Run("Bingo", testBatch80BingoMessages)
	t.Run("Chivalry", testBatch80ChivalryMessages)
	t.Run("Seal", testBatch80SealMessages)
}

// 测试物品相关消息
func testBatch80ItemMessages(t *testing.T) {
	reqItemCombine := &dnfv1.ReqItemCombine{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemCombine)
	assert.Equal(t, int32(0), reqItemCombine.Error)
	assert.Equal(t, int32(1), reqItemCombine.Index)

	resItemDisjoint := &dnfv1.ResItemDisjoint{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemDisjoint)
	assert.Equal(t, int32(0), resItemDisjoint.Error)
	assert.Equal(t, int32(1), resItemDisjoint.Index)

	reqOpenTimeBox := &dnfv1.ReqOpenTimeBox{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqOpenTimeBox)
	assert.Equal(t, int32(0), reqOpenTimeBox.Error)
	assert.Equal(t, int32(1), reqOpenTimeBox.Index)

	resMoneyItemList := &dnfv1.ResMoneyItemList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMoneyItemList)
	assert.Equal(t, int32(0), resMoneyItemList.Error)
	assert.Equal(t, int32(1), resMoneyItemList.Index)

	reqEquipList := &dnfv1.ReqEquipList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqEquipList)
	assert.Equal(t, int32(0), reqEquipList.Error)
	assert.Equal(t, int32(1), reqEquipList.Index)

	resItemReforge := &dnfv1.ResItemReforge{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemReforge)
	assert.Equal(t, int32(0), resItemReforge.Error)
	assert.Equal(t, int32(1), resItemReforge.Index)

	resSealItem := &dnfv1.ResSealItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSealItem)
	assert.Equal(t, int32(0), resSealItem.Error)
	assert.Equal(t, int32(1), resSealItem.Index)
}

// 测试公会相关消息
func testBatch80GuildMessages(t *testing.T) {
	reqGuildPublicBuffUpgrade := &dnfv1.ReqGuildPublicBuffUpgrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildPublicBuffUpgrade)
	assert.Equal(t, int32(0), reqGuildPublicBuffUpgrade.Error)
	assert.Equal(t, int32(1), reqGuildPublicBuffUpgrade.Index)

	reqGuildBuyContentBuff := &dnfv1.ReqGuildBuyContentBuff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildBuyContentBuff)
	assert.Equal(t, int32(0), reqGuildBuyContentBuff.Error)
	assert.Equal(t, int32(1), reqGuildBuyContentBuff.Index)

	reqGuildNameChange := &dnfv1.ReqGuildNameChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildNameChange)
	assert.Equal(t, int32(0), reqGuildNameChange.Error)
	assert.Equal(t, int32(1), reqGuildNameChange.Index)

	resGuildWarehouseList := &dnfv1.ResGuildWarehouseList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildWarehouseList)
	assert.Equal(t, int32(0), resGuildWarehouseList.Error)
	assert.Equal(t, int32(1), resGuildWarehouseList.Index)

	resGuildDiningAction := &dnfv1.ResGuildDiningAction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildDiningAction)
	assert.Equal(t, int32(0), resGuildDiningAction.Error)
	assert.Equal(t, int32(1), resGuildDiningAction.Index)

	resGuildBingoUpdateSquare := &dnfv1.ResGuildBingoUpdateSquare{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildBingoUpdateSquare)
	assert.Equal(t, int32(0), resGuildBingoUpdateSquare.Error)
	assert.Equal(t, int32(1), resGuildBingoUpdateSquare.Index)
}

// 测试攻击小队相关消息
func testBatch80AttackSquadMessages(t *testing.T) {
	reqAttackSquadJoin := &dnfv1.ReqAttackSquadJoin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadJoin)
	assert.Equal(t, int32(0), reqAttackSquadJoin.Error)
	assert.Equal(t, int32(1), reqAttackSquadJoin.Index)

	resAttackSquadRecommendInviteList := &dnfv1.ResAttackSquadRecommendInviteList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAttackSquadRecommendInviteList)
	assert.Equal(t, int32(0), resAttackSquadRecommendInviteList.Error)
	assert.Equal(t, int32(1), resAttackSquadRecommendInviteList.Index)

	resAttackSquadAcceptJoinNoti := &dnfv1.ResAttackSquadAcceptJoinNoti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAttackSquadAcceptJoinNoti)
	assert.Equal(t, int32(0), resAttackSquadAcceptJoinNoti.Error)
	assert.Equal(t, int32(1), resAttackSquadAcceptJoinNoti.Index)
}

// 测试小游戏相关消息
func testBatch80MinigameMessages(t *testing.T) {
	reqMinigameSpecialClassMazeClear := &dnfv1.ReqMinigameSpecialClassMazeClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigameSpecialClassMazeClear)
	assert.Equal(t, int32(0), reqMinigameSpecialClassMazeClear.Error)
	assert.Equal(t, int32(1), reqMinigameSpecialClassMazeClear.Index)

	resMinigameRestaurantLoad := &dnfv1.ResMinigameRestaurantLoad{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameRestaurantLoad)
	assert.Equal(t, int32(0), resMinigameRestaurantLoad.Error)
	assert.Equal(t, int32(1), resMinigameRestaurantLoad.Index)
}

// 测试任务相关消息
func testBatch80QuestMessages(t *testing.T) {
	reqRewardQuest := &dnfv1.ReqRewardQuest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRewardQuest)
	assert.Equal(t, int32(0), reqRewardQuest.Error)
	assert.Equal(t, int32(1), reqRewardQuest.Index)
}

// 测试控制相关消息
func testBatch80ControlMessages(t *testing.T) {
	reqControlTrainingroomReward := &dnfv1.ReqControlTrainingroomReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqControlTrainingroomReward)
	assert.Equal(t, int32(0), reqControlTrainingroomReward.Error)
	assert.Equal(t, int32(1), reqControlTrainingroomReward.Index)

	resControlGroupCustom := &dnfv1.ResControlGroupCustom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resControlGroupCustom)
	assert.Equal(t, int32(0), resControlGroupCustom.Error)
	assert.Equal(t, int32(1), resControlGroupCustom.Index)
}

// 测试历史遗址相关消息
func testBatch80HistoricsiteMessages(t *testing.T) {
	resHistoricsiteMemberRecord := &dnfv1.ResHistoricsiteMemberRecord{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteMemberRecord)
	assert.Equal(t, int32(0), resHistoricsiteMemberRecord.Error)
	assert.Equal(t, int32(1), resHistoricsiteMemberRecord.Index)

	resHistoricsiteUpdateStrategySlot := &dnfv1.ResHistoricsiteUpdateStrategySlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteUpdateStrategySlot)
	assert.Equal(t, int32(0), resHistoricsiteUpdateStrategySlot.Error)
	assert.Equal(t, int32(1), resHistoricsiteUpdateStrategySlot.Index)
}

// 测试突袭相关消息
func testBatch80RaidMessages(t *testing.T) {
	ptRaidDamageMeter := &dnfv1.PtRaidDamageMeter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRaidDamageMeter)
	assert.Equal(t, int32(0), ptRaidDamageMeter.Error)
	assert.Equal(t, int32(1), ptRaidDamageMeter.Index)
}

// 测试用户相关消息
func testBatch80UserMessages(t *testing.T) {
	ptUserName := &dnfv1.PtUserName{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptUserName)
	assert.Equal(t, int32(0), ptUserName.Error)
	assert.Equal(t, int32(1), ptUserName.Index)
}

// 测试排名相关消息
func testBatch80RankingMessages(t *testing.T) {
	resInquireLocationRanking := &dnfv1.ResInquireLocationRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resInquireLocationRanking)
	assert.Equal(t, int32(0), resInquireLocationRanking.Error)
	assert.Equal(t, int32(1), resInquireLocationRanking.Index)

	ptPartyRanking := &dnfv1.PtPartyRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPartyRanking)
	assert.Equal(t, int32(0), ptPartyRanking.Error)
	assert.Equal(t, int32(1), ptPartyRanking.Index)
}

// 测试队伍相关消息
func testBatch80PartyMessages(t *testing.T) {
	resPartyMemberBattleServerDisconnect := &dnfv1.ResPartyMemberBattleServerDisconnect{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPartyMemberBattleServerDisconnect)
	assert.Equal(t, int32(0), resPartyMemberBattleServerDisconnect.Error)
	assert.Equal(t, int32(1), resPartyMemberBattleServerDisconnect.Index)

	reqPartyInvite := &dnfv1.ReqPartyInvite{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPartyInvite)
	assert.Equal(t, int32(0), reqPartyInvite.Error)
	assert.Equal(t, int32(1), reqPartyInvite.Index)

	reqPartyLeave := &dnfv1.ReqPartyLeave{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPartyLeave)
	assert.Equal(t, int32(0), reqPartyLeave.Error)
	assert.Equal(t, int32(1), reqPartyLeave.Index)
}

// 测试徽章相关消息
func testBatch80EmblemMessages(t *testing.T) {
	reqEmblemList := &dnfv1.ReqEmblemList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqEmblemList)
	assert.Equal(t, int32(0), reqEmblemList.Error)
	assert.Equal(t, int32(1), reqEmblemList.Index)
}

// 测试好友相关消息
func testBatch80FriendMessages(t *testing.T) {
	ptRecommendFriendInfo := &dnfv1.PtRecommendFriendInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRecommendFriendInfo)
	assert.Equal(t, int32(0), ptRecommendFriendInfo.Error)
	assert.Equal(t, int32(1), ptRecommendFriendInfo.Index)

	resFriendRecvFatigue := &dnfv1.ResFriendRecvFatigue{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resFriendRecvFatigue)
	assert.Equal(t, int32(0), resFriendRecvFatigue.Error)
	assert.Equal(t, int32(1), resFriendRecvFatigue.Index)
}

// 测试彩虹相关消息
func testBatch80CeraMessages(t *testing.T) {
	ptCeraShopMoney := &dnfv1.PtCeraShopMoney{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCeraShopMoney)
	assert.Equal(t, int32(0), ptCeraShopMoney.Error)
	assert.Equal(t, int32(1), ptCeraShopMoney.Index)

	resCeraInterestingGoodsList := &dnfv1.ResCeraInterestingGoodsList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCeraInterestingGoodsList)
	assert.Equal(t, int32(0), resCeraInterestingGoodsList.Error)
	assert.Equal(t, int32(1), resCeraInterestingGoodsList.Index)
}

// 测试消息相关消息
func testBatch80MessageMessages(t *testing.T) {
	resMessageAssistantSendStructmsg := &dnfv1.ResMessageAssistantSendStructmsg{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMessageAssistantSendStructmsg)
	assert.Equal(t, int32(0), resMessageAssistantSendStructmsg.Error)
	assert.Equal(t, int32(1), resMessageAssistantSendStructmsg.Index)
}

// 测试语音相关消息
func testBatch80VoiceMessages(t *testing.T) {
	resBroadcastGvoiceMemberList := &dnfv1.ResBroadcastGvoiceMemberList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBroadcastGvoiceMemberList)
	assert.Equal(t, int32(0), resBroadcastGvoiceMemberList.Error)
	assert.Equal(t, int32(1), resBroadcastGvoiceMemberList.Index)
}

// 测试棋盘相关消息
func testBatch80BoardMessages(t *testing.T) {
	ptBoardInfo := &dnfv1.PtBoardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptBoardInfo)
	assert.Equal(t, int32(0), ptBoardInfo.Error)
	assert.Equal(t, int32(1), ptBoardInfo.Index)
}

// 测试PVP相关消息
func testBatch80PvpMessages(t *testing.T) {
	reqPvpHistory := &dnfv1.ReqPvpHistory{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPvpHistory)
	assert.Equal(t, int32(0), reqPvpHistory.Error)
	assert.Equal(t, int32(1), reqPvpHistory.Index)
}

// 测试设施相关消息
func testBatch80FacilityMessages(t *testing.T) {
	ptUsableFacilityInfo := &dnfv1.PtUsableFacilityInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptUsableFacilityInfo)
	assert.Equal(t, int32(0), ptUsableFacilityInfo.Error)
	assert.Equal(t, int32(1), ptUsableFacilityInfo.Index)
}

// 测试团队相关消息
func testBatch80TeamMessages(t *testing.T) {
	reqTeamChange := &dnfv1.ReqTeamChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqTeamChange)
	assert.Equal(t, int32(0), reqTeamChange.Error)
	assert.Equal(t, int32(1), reqTeamChange.Index)
}

// 测试提案相关消息
func testBatch80ProposalMessages(t *testing.T) {
	ptProposalInfo := &dnfv1.PtProposalInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptProposalInfo)
	assert.Equal(t, int32(0), ptProposalInfo.Error)
	assert.Equal(t, int32(1), ptProposalInfo.Index)
}

// 测试副本相关消息
func testBatch80DungeonMessages(t *testing.T) {
	resDungeonResult := &dnfv1.ResDungeonResult{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resDungeonResult)
	assert.Equal(t, int32(0), resDungeonResult.Error)
	assert.Equal(t, int32(1), resDungeonResult.Index)
}

// 测试拍卖相关消息
func testBatch80AuctionMessages(t *testing.T) {
	ptAuctionEquip := &dnfv1.PtAuctionEquip{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAuctionEquip)
	assert.Equal(t, int32(0), ptAuctionEquip.Error)
	assert.Equal(t, int32(1), ptAuctionEquip.Index)
}

// 测试系统命令相关消息
func testBatch80SyscmdMessages(t *testing.T) {
	ptSyscmdPackages := &dnfv1.PtSyscmdPackages{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptSyscmdPackages)
	assert.Equal(t, int32(0), ptSyscmdPackages.Error)
	assert.Equal(t, int32(1), ptSyscmdPackages.Index)
}

// 测试重铸相关消息
func testBatch80ReforgeMessages(t *testing.T) {
	resItemReforge := &dnfv1.ResItemReforge{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemReforge)
	assert.Equal(t, int32(0), resItemReforge.Error)
	assert.Equal(t, int32(1), resItemReforge.Index)
}

// 测试疲劳相关消息
func testBatch80FatigueMessages(t *testing.T) {
	resRecoveryFatigue := &dnfv1.ResRecoveryFatigue{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRecoveryFatigue)
	assert.Equal(t, int32(0), resRecoveryFatigue.Error)
	assert.Equal(t, int32(1), resRecoveryFatigue.Index)
}

// 测试锁定步骤相关消息
func testBatch80LockstepMessages(t *testing.T) {
	reqStartLockstepRoom := &dnfv1.ReqStartLockstepRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqStartLockstepRoom)
	assert.Equal(t, int32(0), reqStartLockstepRoom.Error)
	assert.Equal(t, int32(1), reqStartLockstepRoom.Index)
}

// 测试仓库相关消息
func testBatch80WarehouseMessages(t *testing.T) {
	resGuildWarehouseList := &dnfv1.ResGuildWarehouseList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildWarehouseList)
	assert.Equal(t, int32(0), resGuildWarehouseList.Error)
	assert.Equal(t, int32(1), resGuildWarehouseList.Index)
}

// 测试餐厅相关消息
func testBatch80DiningMessages(t *testing.T) {
	resGuildDiningAction := &dnfv1.ResGuildDiningAction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildDiningAction)
	assert.Equal(t, int32(0), resGuildDiningAction.Error)
	assert.Equal(t, int32(1), resGuildDiningAction.Index)
}

// 测试徒弟相关消息
func testBatch80MenteeMessages(t *testing.T) {
	ptMenteeInfo := &dnfv1.PtMenteeInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMenteeInfo)
	assert.Equal(t, int32(0), ptMenteeInfo.Error)
	assert.Equal(t, int32(1), ptMenteeInfo.Index)
}

// 测试宾果相关消息
func testBatch80BingoMessages(t *testing.T) {
	resGuildBingoUpdateSquare := &dnfv1.ResGuildBingoUpdateSquare{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildBingoUpdateSquare)
	assert.Equal(t, int32(0), resGuildBingoUpdateSquare.Error)
	assert.Equal(t, int32(1), resGuildBingoUpdateSquare.Index)
}

// 测试骑士团相关消息
func testBatch80ChivalryMessages(t *testing.T) {
	resChivalryUpdateMission := &dnfv1.ResChivalryUpdateMission{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resChivalryUpdateMission)
	assert.Equal(t, int32(0), resChivalryUpdateMission.Error)
	assert.Equal(t, int32(1), resChivalryUpdateMission.Index)
}

// 测试封印相关消息
func testBatch80SealMessages(t *testing.T) {
	resSealItem := &dnfv1.ResSealItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSealItem)
	assert.Equal(t, int32(0), resSealItem.Error)
	assert.Equal(t, int32(1), resSealItem.Index)
}
