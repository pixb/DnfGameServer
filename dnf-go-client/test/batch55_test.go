package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch55Migration 测试批次55的迁移结果
func TestBatch55Migration(t *testing.T) {
	// 测试冒险系统
	t.Run("AdventureSystem", testBatch55AdventureSystem)
	
	// 测试冒险联盟系统
	t.Run("AdventureUnionSystem", testBatch55AdventureUnionSystem)
	
	// 测试冒险奖励系统
	t.Run("AdventureRewardSystem", testBatch55AdventureRewardSystem)
}

// testBatch55AdventureSystem 测试冒险系统
func testBatch55AdventureSystem(t *testing.T) {
	// 测试AdventurebookInfo
	adventurebookInfo := &dnfv1.AdventurebookInfo{
		Bindex:           1,
		Bstate:           2,
		Charguid:         1234567890,
		Charname:         "Test Character",
		Growtype:         1,
		Growsecondtype:   2,
		Job:              5,
		Rewardedcharguid: []uint64{1234567891, 1234567892},
		Terareward:       true,
	}
	assert.NotNil(t, adventurebookInfo)
	assert.Equal(t, int32(1), adventurebookInfo.Bindex)
	assert.Equal(t, int32(2), adventurebookInfo.Bstate)
	assert.Equal(t, uint64(1234567890), adventurebookInfo.Charguid)
	assert.Equal(t, "Test Character", adventurebookInfo.Charname)
	assert.Equal(t, int32(1), adventurebookInfo.Growtype)
	assert.Equal(t, int32(2), adventurebookInfo.Growsecondtype)
	assert.Equal(t, int32(5), adventurebookInfo.Job)
	assert.Len(t, adventurebookInfo.Rewardedcharguid, 2)
	assert.True(t, adventurebookInfo.Terareward)
	
	// 测试AdventurebookCondition
	adventurebookCondition := &dnfv1.AdventurebookCondition{
		Bookindex:       1,
		Booktype:        2,
		Conditionstate:  3,
	}
	assert.NotNil(t, adventurebookCondition)
	assert.Equal(t, int32(1), adventurebookCondition.Bookindex)
	assert.Equal(t, int32(2), adventurebookCondition.Booktype)
	assert.Equal(t, int32(3), adventurebookCondition.Conditionstate)
	
	// 测试AdventurebookAchieveClearCondition
	adventurebookAchieveClearCondition := &dnfv1.AdventurebookAchieveClearCondition{
		Groupindex:      1,
		State:           2,
		Conditioncount:  3,
	}
	assert.NotNil(t, adventurebookAchieveClearCondition)
	assert.Equal(t, int32(1), adventurebookAchieveClearCondition.Groupindex)
	assert.Equal(t, int32(2), adventurebookAchieveClearCondition.State)
	assert.Equal(t, int32(3), adventurebookAchieveClearCondition.Conditioncount)
	
	// 测试AdventurebookOpenCondition
	adventurebookOpenCondition := &dnfv1.AdventurebookOpenCondition{
		Cindex:          1,
		Cstate:          2,
	}
	assert.NotNil(t, adventurebookOpenCondition)
	assert.Equal(t, int32(1), adventurebookOpenCondition.Cindex)
	assert.Equal(t, int32(2), adventurebookOpenCondition.Cstate)
	
	// 测试AdventurebookQuest
	adventurebookQuest := &dnfv1.AdventurebookQuest{
		Qindex:               1,
		Qstate:               2,
		Qcount:               3,
		Achieveindex:         4,
		Achieveclearconditions: []*dnfv1.AdventurebookAchieveClearCondition{
			{
				Groupindex:      1,
				State:           2,
				Conditioncount:  3,
			},
		},
	}
	assert.NotNil(t, adventurebookQuest)
	assert.Equal(t, int32(1), adventurebookQuest.Qindex)
	assert.Equal(t, int32(2), adventurebookQuest.Qstate)
	assert.Equal(t, int32(3), adventurebookQuest.Qcount)
	assert.Equal(t, int32(4), adventurebookQuest.Achieveindex)
	assert.Len(t, adventurebookQuest.Achieveclearconditions, 1)
}

// testBatch55AdventureUnionSystem 测试冒险联盟系统
func testBatch55AdventureUnionSystem(t *testing.T) {
	// 测试AdventureUnionCollection
	adventureUnionCollection := &dnfv1.AdventureUnionCollection{
		Job:          5,
		Growtype:     1,
		Secgrowtype:  2,
		Equipscore:   10000,
	}
	assert.NotNil(t, adventureUnionCollection)
	assert.Equal(t, int32(5), adventureUnionCollection.Job)
	assert.Equal(t, int32(1), adventureUnionCollection.Growtype)
	assert.Equal(t, int32(2), adventureUnionCollection.Secgrowtype)
	assert.Equal(t, int32(10000), adventureUnionCollection.Equipscore)
	
	// 测试AdventureUnionCollectionSlot
	adventureUnionCollectionSlot := &dnfv1.AdventureUnionCollectionSlot{
		Slot:         1,
		Rewardindex:  2,
	}
	assert.NotNil(t, adventureUnionCollectionSlot)
	assert.Equal(t, int32(1), adventureUnionCollectionSlot.Slot)
	assert.Equal(t, int32(2), adventureUnionCollectionSlot.Rewardindex)
	
	// 测试AdventureUnionExpedition
	adventureUnionExpedition := &dnfv1.AdventureUnionExpedition{
		Area:             1,
		Time:             3600,
		Consumefatigue:   8,
		Regdate:          1234567890,
		Characters:       []*dnfv1.CharguidFatigue{
			{
				Charguid:  1234567890,
				Fatigue:   8,
			},
		},
		Rewards:          []*dnfv1.AdventureUnionExpeditionRewards{
			{
				Index:  1,
				Count:  5,
			},
		},
	}
	assert.NotNil(t, adventureUnionExpedition)
	assert.Equal(t, int32(1), adventureUnionExpedition.Area)
	assert.Equal(t, int32(3600), adventureUnionExpedition.Time)
	assert.Equal(t, int32(8), adventureUnionExpedition.Consumefatigue)
	assert.Equal(t, int64(1234567890), adventureUnionExpedition.Regdate)
	assert.Len(t, adventureUnionExpedition.Characters, 1)
	assert.Len(t, adventureUnionExpedition.Rewards, 1)
	
	// 测试AdventureUnionExpeditionRewards
	adventureUnionExpeditionRewards := &dnfv1.AdventureUnionExpeditionRewards{
		Index:  1,
		Count:  5,
	}
	assert.NotNil(t, adventureUnionExpeditionRewards)
	assert.Equal(t, int32(1), adventureUnionExpeditionRewards.Index)
	assert.Equal(t, int32(5), adventureUnionExpeditionRewards.Count)
	
	// 测试AdventureUnionLevelReward
	adventureUnionLevelReward := &dnfv1.AdventureUnionLevelReward{
		Level:  10,
	}
	assert.NotNil(t, adventureUnionLevelReward)
	assert.Equal(t, int32(10), adventureUnionLevelReward.Level)
	
	// 测试AdventureUnionLevelRewardInfo
	adventureUnionLevelRewardInfo := &dnfv1.AdventureUnionLevelRewardInfo{
		Level:       10,
		Itemindex:   1,
		Itemcount:   5,
	}
	assert.NotNil(t, adventureUnionLevelRewardInfo)
	assert.Equal(t, int32(10), adventureUnionLevelRewardInfo.Level)
	assert.Equal(t, int32(1), adventureUnionLevelRewardInfo.Itemindex)
	assert.Equal(t, int32(5), adventureUnionLevelRewardInfo.Itemcount)
	
	// 测试AdventureUnionShareboardBackground
	adventureUnionShareboardBackground := &dnfv1.AdventureUnionShareboardBackground{
		Index:  1,
	}
	assert.NotNil(t, adventureUnionShareboardBackground)
	assert.Equal(t, int32(1), adventureUnionShareboardBackground.Index)
	
	// 测试AdventureUnionShareboardFrame
	adventureUnionShareboardFrame := &dnfv1.AdventureUnionShareboardFrame{
		Index:  1,
	}
	assert.NotNil(t, adventureUnionShareboardFrame)
	assert.Equal(t, int32(1), adventureUnionShareboardFrame.Index)
	
	// 测试AdventureUnionShareboardSlot
	adventureUnionShareboardSlot := &dnfv1.AdventureUnionShareboardSlot{
		Slot:          1,
		Charguid:      1234567890,
		Direction:     true,
		Antievilscore: 1000,
	}
	assert.NotNil(t, adventureUnionShareboardSlot)
	assert.Equal(t, int32(1), adventureUnionShareboardSlot.Slot)
	assert.Equal(t, uint64(1234567890), adventureUnionShareboardSlot.Charguid)
	assert.True(t, adventureUnionShareboardSlot.Direction)
	assert.Equal(t, int32(1000), adventureUnionShareboardSlot.Antievilscore)
	
	// 测试AdventureUnionShareboardSlotDetailInfo
	adventureUnionShareboardSlotDetailInfo := &dnfv1.AdventureUnionShareboardSlotDetailInfo{
		Slot:        1,
		Direction:   true,
		Characterinfo: &dnfv1.Character{},
	}
	assert.NotNil(t, adventureUnionShareboardSlotDetailInfo)
	assert.Equal(t, int32(1), adventureUnionShareboardSlotDetailInfo.Slot)
	assert.True(t, adventureUnionShareboardSlotDetailInfo.Direction)
	assert.NotNil(t, adventureUnionShareboardSlotDetailInfo.Characterinfo)
}

// testBatch55AdventureRewardSystem 测试冒险奖励系统
func testBatch55AdventureRewardSystem(t *testing.T) {
	// 测试AdventureClearReward
	adventureClearReward := &dnfv1.AdventureClearReward{
		Exp:     1000,
		Index:   1,
		Count:   5,
		Items:   &dnfv1.Items{},
		List:    []*dnfv1.MoneyItem{},
	}
	assert.NotNil(t, adventureClearReward)
	assert.Equal(t, int32(1000), adventureClearReward.Exp)
	assert.Equal(t, int32(1), adventureClearReward.Index)
	assert.Equal(t, int32(5), adventureClearReward.Count)
	assert.NotNil(t, adventureClearReward.Items)
	assert.NotNil(t, adventureClearReward.List)
	
	// 测试AdventureExpInfo
	adventureExpInfo := &dnfv1.AdventureExpInfo{
		Rewardexp: 1000,
		Level:     85,
		Exp:       1000000,
	}
	assert.NotNil(t, adventureExpInfo)
	assert.Equal(t, int32(1000), adventureExpInfo.Rewardexp)
	assert.Equal(t, int32(85), adventureExpInfo.Level)
	assert.Equal(t, int64(1000000), adventureExpInfo.Exp)
	
	// 测试AdventureListItem
	adventureListItem := &dnfv1.AdventureListItem{
		Type:   1,
		Count:  5,
	}
	assert.NotNil(t, adventureListItem)
	assert.Equal(t, int32(1), adventureListItem.Type)
	assert.Equal(t, int32(5), adventureListItem.Count)
	
	// 测试AdventureRewardInfo
	adventureRewardInfo := &dnfv1.AdventureRewardInfo{
		Rewards:       &dnfv1.ContentsRewardInfo{},
		Adventureexp:  &dnfv1.AdventureExpInfo{},
		Charexps:      []*dnfv1.ExpInfo{},
	}
	assert.NotNil(t, adventureRewardInfo)
	assert.NotNil(t, adventureRewardInfo.Rewards)
	assert.NotNil(t, adventureRewardInfo.Adventureexp)
	assert.NotNil(t, adventureRewardInfo.Charexps)
	
	// 测试AdventureRewardStep
	adventureRewardStep := &dnfv1.AdventureRewardStep{
		Step:  1,
	}
	assert.NotNil(t, adventureRewardStep)
	assert.Equal(t, int32(1), adventureRewardStep.Step)
	
	// 测试ExpInfo
	expInfo := &dnfv1.ExpInfo{
		Exp:      1000,
		Charguid: 1234567890,
	}
	assert.NotNil(t, expInfo)
	assert.Equal(t, int32(1000), expInfo.Exp)
	assert.Equal(t, uint64(1234567890), expInfo.Charguid)
	
	// 测试CharguidFatigue
	charguidFatigue := &dnfv1.CharguidFatigue{
		Charguid:  1234567890,
		Fatigue:   8,
	}
	assert.NotNil(t, charguidFatigue)
	assert.Equal(t, uint64(1234567890), charguidFatigue.Charguid)
	assert.Equal(t, int32(8), charguidFatigue.Fatigue)
}
