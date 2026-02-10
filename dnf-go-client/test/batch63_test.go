package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch63Migration 测试第63批迁移的消息类型
func TestBatch63Migration(t *testing.T) {
	t.Run("Guild", testBatch63GuildMessages)
	t.Run("Promotion", testBatch63PromotionMessages)
	t.Run("Raid", testBatch63RaidMessages)
	t.Run("Adventure", testBatch63AdventureMessages)
	t.Run("Buff", testBatch63BuffMessages)
	t.Run("Quest", testBatch63QuestMessages)
	t.Run("Community", testBatch63CommunityMessages)
	t.Run("Event", testBatch63EventMessages)
	t.Run("Auth", testBatch63AuthMessages)
}

// 测试公会相关消息
func testBatch63GuildMessages(t *testing.T) {
	// 测试ReqGuildDonationRequestHelpCompleteInfo
	reqGuildDonationRequestHelpCompleteInfo := &dnfv1.ReqGuildDonationRequestHelpCompleteInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildDonationRequestHelpCompleteInfo)
	assert.Equal(t, int32(0), reqGuildDonationRequestHelpCompleteInfo.Error)
	assert.Equal(t, int32(1), reqGuildDonationRequestHelpCompleteInfo.Index)

	// 测试ResGuildDonationAccumulateReward
	resGuildDonationAccumulateReward := &dnfv1.ResGuildDonationAccumulateReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildDonationAccumulateReward)
	assert.Equal(t, int32(0), resGuildDonationAccumulateReward.Error)
	assert.Equal(t, int32(1), resGuildDonationAccumulateReward.Index)
}

// 测试晋升相关消息
func testBatch63PromotionMessages(t *testing.T) {
	// 测试ResPromotionInfo
	resPromotionInfo := &dnfv1.ResPromotionInfo{
		Error: 0,
		Level: 10,
	}
	assert.NotNil(t, resPromotionInfo)
	assert.Equal(t, int32(0), resPromotionInfo.Error)
	assert.Equal(t, int32(10), resPromotionInfo.Level)
}

// 测试突袭相关消息
func testBatch63RaidMessages(t *testing.T) {
	// 测试NotifyRaidPartyLeaderSurrender
	notifyRaidPartyLeaderSurrender := &dnfv1.NotifyRaidPartyLeaderSurrender{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyRaidPartyLeaderSurrender)
	assert.Equal(t, int32(0), notifyRaidPartyLeaderSurrender.Error)
	assert.Equal(t, int32(1), notifyRaidPartyLeaderSurrender.Index)
}

// 测试冒险相关消息
func testBatch63AdventureMessages(t *testing.T) {
	// 测试NotifyAdventurebookUpdateCondition
	notifyAdventurebookUpdateCondition := &dnfv1.NotifyAdventurebookUpdateCondition{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyAdventurebookUpdateCondition)
	assert.Equal(t, int32(0), notifyAdventurebookUpdateCondition.Error)
	assert.Equal(t, int32(1), notifyAdventurebookUpdateCondition.Index)

	// 测试ReqAdventureReapInfo
	reqAdventureReapInfo := &dnfv1.ReqAdventureReapInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureReapInfo)
	assert.Equal(t, int32(0), reqAdventureReapInfo.Error)
	assert.Equal(t, int32(1), reqAdventureReapInfo.Index)

	// 测试ResAdventureUnionInfo
	resAdventureUnionInfo := &dnfv1.ResAdventureUnionInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAdventureUnionInfo)
	assert.Equal(t, int32(0), resAdventureUnionInfo.Error)
	assert.Equal(t, int32(1), resAdventureUnionInfo.Index)
}

// 测试Buff相关消息
func testBatch63BuffMessages(t *testing.T) {
	// 测试ResSeriaBuffList
	resSeriaBuffList := &dnfv1.ResSeriaBuffList{
		Error: 0,
		Buffs: []int32{1, 2, 3},
	}
	assert.NotNil(t, resSeriaBuffList)
	assert.Equal(t, int32(0), resSeriaBuffList.Error)
	assert.Len(t, resSeriaBuffList.Buffs, 3)

	// 测试ResRandomoptionChange
	resRandomoptionChange := &dnfv1.ResRandomoptionChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRandomoptionChange)
	assert.Equal(t, int32(0), resRandomoptionChange.Error)
	assert.Equal(t, int32(1), resRandomoptionChange.Index)
}

// 测试任务相关消息
func testBatch63QuestMessages(t *testing.T) {
	// 测试ResCompleteQuest
	resCompleteQuest := &dnfv1.ResCompleteQuest{
		Error:  0,
		Qindex: 1,
	}
	assert.NotNil(t, resCompleteQuest)
	assert.Equal(t, int32(0), resCompleteQuest.Error)
	assert.Equal(t, int32(1), resCompleteQuest.Qindex)
}

// 测试社区相关消息
func testBatch63CommunityMessages(t *testing.T) {
	// 测试PtCommunityGiftGuidInfo
	ptCommunityGiftGuidInfo := &dnfv1.PtCommunityGiftGuidInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCommunityGiftGuidInfo)
	assert.Equal(t, int32(0), ptCommunityGiftGuidInfo.Error)
	assert.Equal(t, int32(1), ptCommunityGiftGuidInfo.Index)

	// 测试NotifyProposal
	notifyProposal := &dnfv1.NotifyProposal{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyProposal)
	assert.Equal(t, int32(0), notifyProposal.Error)
	assert.Equal(t, int32(1), notifyProposal.Index)
}

// 测试事件相关消息
func testBatch63EventMessages(t *testing.T) {
	// 测试ReqEventSelectCharacter
	reqEventSelectCharacter := &dnfv1.ReqEventSelectCharacter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqEventSelectCharacter)
	assert.Equal(t, int32(0), reqEventSelectCharacter.Error)
	assert.Equal(t, int32(1), reqEventSelectCharacter.Index)

	// 测试ReqMinigameSpecialClassStart
	reqMinigameSpecialClassStart := &dnfv1.ReqMinigameSpecialClassStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigameSpecialClassStart)
	assert.Equal(t, int32(0), reqMinigameSpecialClassStart.Error)
	assert.Equal(t, int32(1), reqMinigameSpecialClassStart.Index)
}

// 测试认证相关消息
func testBatch63AuthMessages(t *testing.T) {
	// 测试AuthInfoRes
	authInfoRes := &dnfv1.AuthInfoRes{
		Error:   0,
		Message: "Success",
	}
	assert.NotNil(t, authInfoRes)
	assert.Equal(t, int32(0), authInfoRes.Error)
	assert.Equal(t, "Success", authInfoRes.Message)
}

// 测试历史站点相关消息
func testBatch63HistoricsiteMessages(t *testing.T) {
	// 测试ReqHistoricsiteUpdateStrategySlot
	reqHistoricsiteUpdateStrategySlot := &dnfv1.ReqHistoricsiteUpdateStrategySlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteUpdateStrategySlot)
	assert.Equal(t, int32(0), reqHistoricsiteUpdateStrategySlot.Error)
	assert.Equal(t, int32(1), reqHistoricsiteUpdateStrategySlot.Index)
}

// 测试生物联盟相关消息
func testBatch63CreatureMessages(t *testing.T) {
	// 测试ResCreatureCommunionInfo
	resCreatureCommunionInfo := &dnfv1.ResCreatureCommunionInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureCommunionInfo)
	assert.Equal(t, int32(0), resCreatureCommunionInfo.Error)
	assert.Equal(t, int32(1), resCreatureCommunionInfo.Index)
}

// 测试导师相关消息
func testBatch63MentorMessages(t *testing.T) {
	// 测试PtMentorGetAllGrowthRewardQuest
	ptMentorGetAllGrowthRewardQuest := &dnfv1.PtMentorGetAllGrowthRewardQuest{
		Error:  0,
		Quests: []int32{1, 2, 3},
	}
	assert.NotNil(t, ptMentorGetAllGrowthRewardQuest)
	assert.Equal(t, int32(0), ptMentorGetAllGrowthRewardQuest.Error)
	assert.Len(t, ptMentorGetAllGrowthRewardQuest.Quests, 3)

	// 测试PtMentorGraduateEquipInfo
	ptMentorGraduateEquipInfo := &dnfv1.PtMentorGraduateEquipInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMentorGraduateEquipInfo)
	assert.Equal(t, int32(0), ptMentorGraduateEquipInfo.Error)
	assert.Equal(t, int32(1), ptMentorGraduateEquipInfo.Index)
}

// 测试物品相关消息
func testBatch63ItemMessages(t *testing.T) {
	// 测试ResItemProductionCancel
	resItemProductionCancel := &dnfv1.ResItemProductionCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemProductionCancel)
	assert.Equal(t, int32(0), resItemProductionCancel.Error)
	assert.Equal(t, int32(1), resItemProductionCancel.Index)
}
