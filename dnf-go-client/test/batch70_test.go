package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch70Migration 测试第70批迁移的消息类型
func TestBatch70Migration(t *testing.T) {
	t.Run("Reward", testBatch70RewardMessages)
	t.Run("Skill", testBatch70SkillMessages)
	t.Run("Emblem", testBatch70EmblemMessages)
	t.Run("Relay", testBatch70RelayMessages)
	t.Run("Quest", testBatch70QuestMessages)
	t.Run("Guild", testBatch70GuildMessages)
	t.Run("AttackSquad", testBatch70AttackSquadMessages)
	t.Run("Creature", testBatch70CreatureMessages)
	t.Run("Tutorial", testBatch70TutorialMessages)
	t.Run("Wedding", testBatch70WeddingMessages)
	t.Run("Achievement", testBatch70AchievementMessages)
	t.Run("Auction", testBatch70AuctionMessages)
	t.Run("GuildWar", testBatch70GuildWarMessages)
	t.Run("Frame", testBatch70FrameMessages)
	t.Run("Battleleague", testBatch70BattleleagueMessages)
	t.Run("Blacklist", testBatch70BlacklistMessages)
	t.Run("Chat", testBatch70ChatMessages)
	t.Run("Player", testBatch70PlayerMessages)
	t.Run("Timebox", testBatch70TimeboxMessages)
	t.Run("AdventureUnion", testBatch70AdventureUnionMessages)
	t.Run("Minigame", testBatch70MinigameMessages)
	t.Run("Pvp", testBatch70PvpMessages)
	t.Run("Town", testBatch70TownMessages)
	t.Run("Adventurebook", testBatch70AdventurebookMessages)
	t.Run("Character", testBatch70CharacterMessages)
	t.Run("Historicsite", testBatch70HistoricsiteMessages)
	t.Run("Items", testBatch70ItemsMessages)
	t.Run("Party", testBatch70PartyMessages)
	t.Run("Artifact", testBatch70ArtifactMessages)
	t.Run("OpenTimeBox", testBatch70OpenTimeBoxMessages)
	t.Run("Rank", testBatch70RankMessages)
	t.Run("Map", testBatch70MapMessages)
	t.Run("Equipments", testBatch70EquipmentsMessages)
	t.Run("Collection", testBatch70CollectionMessages)
	t.Run("Marriage", testBatch70MarriageMessages)
}

// 测试奖励相关消息
func testBatch70RewardMessages(t *testing.T) {
	ptRewardCurrencyInfo := &dnfv1.PtRewardCurrencyInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRewardCurrencyInfo)
	assert.Equal(t, int32(0), ptRewardCurrencyInfo.Error)
	assert.Equal(t, int32(1), ptRewardCurrencyInfo.Index)
}

// 测试技能相关消息
func testBatch70SkillMessages(t *testing.T) {
	ptInitSkill := &dnfv1.PtInitSkill{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptInitSkill)
	assert.Equal(t, int32(0), ptInitSkill.Error)
	assert.Equal(t, int32(1), ptInitSkill.Index)
}

// 测试徽章相关消息
func testBatch70EmblemMessages(t *testing.T) {
	ptEmblemRequest := &dnfv1.PtEmblemRequest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptEmblemRequest)
	assert.Equal(t, int32(0), ptEmblemRequest.Error)
	assert.Equal(t, int32(1), ptEmblemRequest.Index)
}

// 测试中继相关消息
func testBatch70RelayMessages(t *testing.T) {
	relayFrameDataTypesReq := &dnfv1.RelayFrameDataTypesReq{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, relayFrameDataTypesReq)
	assert.Equal(t, int32(0), relayFrameDataTypesReq.Error)
	assert.Equal(t, int32(1), relayFrameDataTypesReq.Index)
}

// 测试任务相关消息
func testBatch70QuestMessages(t *testing.T) {
	notifyCompleteQuest := &dnfv1.NotifyCompleteQuest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyCompleteQuest)
	assert.Equal(t, int32(0), notifyCompleteQuest.Error)
	assert.Equal(t, int32(1), notifyCompleteQuest.Index)
}

// 测试公会相关消息
func testBatch70GuildMessages(t *testing.T) {
	notifyGuildFishingDisaster := &dnfv1.NotifyGuildFishingDisaster{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyGuildFishingDisaster)
	assert.Equal(t, int32(0), notifyGuildFishingDisaster.Error)
	assert.Equal(t, int32(1), notifyGuildFishingDisaster.Index)
}

// 测试攻击小队相关消息
func testBatch70AttackSquadMessages(t *testing.T) {
	reqAttackSquadAddRecruit := &dnfv1.ReqAttackSquadAddRecruit{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadAddRecruit)
	assert.Equal(t, int32(0), reqAttackSquadAddRecruit.Error)
	assert.Equal(t, int32(1), reqAttackSquadAddRecruit.Index)
}

// 测试生物相关消息
func testBatch70CreatureMessages(t *testing.T) {
	resCreatureErrandCancel := &dnfv1.ResCreatureErrandCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureErrandCancel)
	assert.Equal(t, int32(0), resCreatureErrandCancel.Error)
	assert.Equal(t, int32(1), resCreatureErrandCancel.Index)
}

// 测试教程相关消息
func testBatch70TutorialMessages(t *testing.T) {
	typeTutoriaList := &dnfv1.TypeTutoriaList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, typeTutoriaList)
	assert.Equal(t, int32(0), typeTutoriaList.Error)
	assert.Equal(t, int32(1), typeTutoriaList.Index)
}

// 测试婚礼相关消息
func testBatch70WeddingMessages(t *testing.T) {
	ptWeddingInvitation := &dnfv1.PtWeddingInvitation{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptWeddingInvitation)
	assert.Equal(t, int32(0), ptWeddingInvitation.Error)
	assert.Equal(t, int32(1), ptWeddingInvitation.Index)
}

// 测试成就相关消息
func testBatch70AchievementMessages(t *testing.T) {
	reqAchievementList := &dnfv1.ReqAchievementList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAchievementList)
	assert.Equal(t, int32(0), reqAchievementList.Error)
	assert.Equal(t, int32(1), reqAchievementList.Index)
}

// 测试拍卖相关消息
func testBatch70AuctionMessages(t *testing.T) {
	ptAuctionBase := &dnfv1.PtAuctionBase{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAuctionBase)
	assert.Equal(t, int32(0), ptAuctionBase.Error)
	assert.Equal(t, int32(1), ptAuctionBase.Index)
}

// 测试公会战争相关消息
func testBatch70GuildWarMessages(t *testing.T) {
	ptGuildWarEndResult := &dnfv1.PtGuildWarEndResult{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildWarEndResult)
	assert.Equal(t, int32(0), ptGuildWarEndResult.Error)
	assert.Equal(t, int32(1), ptGuildWarEndResult.Index)
}

// 测试帧相关消息
func testBatch70FrameMessages(t *testing.T) {
	frameDataRes := &dnfv1.FrameDataRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, frameDataRes)
	assert.Equal(t, int32(0), frameDataRes.Error)
	assert.Equal(t, int32(1), frameDataRes.Index)
}

// 测试战斗联赛相关消息
func testBatch70BattleleagueMessages(t *testing.T) {
	notifyBattleleagueNextDungeon := &dnfv1.NotifyBattleleagueNextDungeon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBattleleagueNextDungeon)
	assert.Equal(t, int32(0), notifyBattleleagueNextDungeon.Error)
	assert.Equal(t, int32(1), notifyBattleleagueNextDungeon.Index)
}

// 测试黑名单相关消息
func testBatch70BlacklistMessages(t *testing.T) {
	resDeleteBlacklist := &dnfv1.ResDeleteBlacklist{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resDeleteBlacklist)
	assert.Equal(t, int32(0), resDeleteBlacklist.Error)
	assert.Equal(t, int32(1), resDeleteBlacklist.Index)
}

// 测试聊天相关消息
func testBatch70ChatMessages(t *testing.T) {
	sendChatTypesReq := &dnfv1.SendChatTypesReq{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, sendChatTypesReq)
	assert.Equal(t, int32(0), sendChatTypesReq.Error)
	assert.Equal(t, int32(1), sendChatTypesReq.Index)
}

// 测试玩家相关消息
func testBatch70PlayerMessages(t *testing.T) {
	playerInput := &dnfv1.PlayerInput{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, playerInput)
	assert.Equal(t, int32(0), playerInput.Error)
	assert.Equal(t, int32(1), playerInput.Index)
}

// 测试时间盒相关消息
func testBatch70TimeboxMessages(t *testing.T) {
	notifyFailedToAcquireTimebox := &dnfv1.NotifyFailedToAcquireTimebox{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyFailedToAcquireTimebox)
	assert.Equal(t, int32(0), notifyFailedToAcquireTimebox.Error)
	assert.Equal(t, int32(1), notifyFailedToAcquireTimebox.Index)
}

// 测试冒险联盟相关消息
func testBatch70AdventureUnionMessages(t *testing.T) {
	reqAdventureUnionSetShareboard := &dnfv1.ReqAdventureUnionSetShareboard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionSetShareboard)
	assert.Equal(t, int32(0), reqAdventureUnionSetShareboard.Error)
	assert.Equal(t, int32(1), reqAdventureUnionSetShareboard.Index)

	ptAdventureUnionShareboardFrame := &dnfv1.PtAdventureUnionShareboardFrame{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAdventureUnionShareboardFrame)
	assert.Equal(t, int32(0), ptAdventureUnionShareboardFrame.Error)
	assert.Equal(t, int32(1), ptAdventureUnionShareboardFrame.Index)

	reqAdventureUnionGrowCollectionOther := &dnfv1.ReqAdventureUnionGrowCollectionOther{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionGrowCollectionOther)
	assert.Equal(t, int32(0), reqAdventureUnionGrowCollectionOther.Error)
	assert.Equal(t, int32(1), reqAdventureUnionGrowCollectionOther.Index)
}

// 测试小游戏相关消息
func testBatch70MinigameMessages(t *testing.T) {
	resMinigameRestaurentStart := &dnfv1.ResMinigameRestaurentStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameRestaurentStart)
	assert.Equal(t, int32(0), resMinigameRestaurentStart.Error)
	assert.Equal(t, int32(1), resMinigameRestaurentStart.Index)
}

// 测试PVP相关消息
func testBatch70PvpMessages(t *testing.T) {
	reqSetPvpControlMode := &dnfv1.ReqSetPvpControlMode{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqSetPvpControlMode)
	assert.Equal(t, int32(0), reqSetPvpControlMode.Error)
	assert.Equal(t, int32(1), reqSetPvpControlMode.Index)
}

// 测试城镇相关消息
func testBatch70TownMessages(t *testing.T) {
	resTownUserGuidList := &dnfv1.ResTownUserGuidList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTownUserGuidList)
	assert.Equal(t, int32(0), resTownUserGuidList.Error)
	assert.Equal(t, int32(1), resTownUserGuidList.Index)
}

// 测试冒险手册相关消息
func testBatch70AdventurebookMessages(t *testing.T) {
	ptAdventurebookAchieveClearCondition := &dnfv1.PtAdventurebookAchieveClearCondition{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAdventurebookAchieveClearCondition)
	assert.Equal(t, int32(0), ptAdventurebookAchieveClearCondition.Error)
	assert.Equal(t, int32(1), ptAdventurebookAchieveClearCondition.Index)
}

// 测试角色相关消息
func testBatch70CharacterMessages(t *testing.T) {
	ptCharacterGuid := &dnfv1.PtCharacterGuid{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCharacterGuid)
	assert.Equal(t, int32(0), ptCharacterGuid.Error)
	assert.Equal(t, int32(1), ptCharacterGuid.Index)
}

// 测试历史站点相关消息
func testBatch70HistoricsiteMessages(t *testing.T) {
	ptHistoricsiteStrategySlot := &dnfv1.PtHistoricsiteStrategySlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptHistoricsiteStrategySlot)
	assert.Equal(t, int32(0), ptHistoricsiteStrategySlot.Error)
	assert.Equal(t, int32(1), ptHistoricsiteStrategySlot.Index)
}

// 测试物品相关消息
func testBatch70ItemsMessages(t *testing.T) {
	ptItems := &dnfv1.PtItems{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptItems)
	assert.Equal(t, int32(0), ptItems.Error)
	assert.Equal(t, int32(1), ptItems.Index)
}

// 测试队伍相关消息
func testBatch70PartyMessages(t *testing.T) {
	reqHalfOpenPartyAccept := &dnfv1.ReqHalfOpenPartyAccept{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHalfOpenPartyAccept)
	assert.Equal(t, int32(0), reqHalfOpenPartyAccept.Error)
	assert.Equal(t, int32(1), reqHalfOpenPartyAccept.Index)
}

// 测试神器相关消息
func testBatch70ArtifactMessages(t *testing.T) {
	resArtifactEquippedOptionChange := &dnfv1.ResArtifactEquippedOptionChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resArtifactEquippedOptionChange)
	assert.Equal(t, int32(0), resArtifactEquippedOptionChange.Error)
	assert.Equal(t, int32(1), resArtifactEquippedOptionChange.Index)
}

// 测试打开时间盒相关消息
func testBatch70OpenTimeBoxMessages(t *testing.T) {
	resOpenTimeBox := &dnfv1.ResOpenTimeBox{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resOpenTimeBox)
	assert.Equal(t, int32(0), resOpenTimeBox.Error)
	assert.Equal(t, int32(1), resOpenTimeBox.Index)
}

// 测试排名相关消息
func testBatch70RankMessages(t *testing.T) {
	ptRankFriendInfo := &dnfv1.PtRankFriendInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRankFriendInfo)
	assert.Equal(t, int32(0), ptRankFriendInfo.Error)
	assert.Equal(t, int32(1), ptRankFriendInfo.Index)
}

// 测试地图相关消息
func testBatch70MapMessages(t *testing.T) {
	ptMapObjectDrop := &dnfv1.PtMapObjectDrop{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMapObjectDrop)
	assert.Equal(t, int32(0), ptMapObjectDrop.Error)
	assert.Equal(t, int32(1), ptMapObjectDrop.Index)
}

// 测试装备相关消息
func testBatch70EquipmentsMessages(t *testing.T) {
	relayEquipmentsDataTypesNoti := &dnfv1.RelayEquipmentsDataTypesNoti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, relayEquipmentsDataTypesNoti)
	assert.Equal(t, int32(0), relayEquipmentsDataTypesNoti.Error)
	assert.Equal(t, int32(1), relayEquipmentsDataTypesNoti.Index)
}

// 测试收集相关消息
func testBatch70CollectionMessages(t *testing.T) {
	ptCollectionItem := &dnfv1.PtCollectionItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCollectionItem)
	assert.Equal(t, int32(0), ptCollectionItem.Error)
	assert.Equal(t, int32(1), ptCollectionItem.Index)
}

// 测试婚姻相关消息
func testBatch70MarriageMessages(t *testing.T) {
	resLoadMarriageInfo := &dnfv1.ResLoadMarriageInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLoadMarriageInfo)
	assert.Equal(t, int32(0), resLoadMarriageInfo.Error)
	assert.Equal(t, int32(1), resLoadMarriageInfo.Index)
}
