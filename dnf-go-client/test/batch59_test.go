package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch59Migration 测试第59批迁移的消息类型
func TestBatch59Migration(t *testing.T) {
	t.Run("DiningAndDragonBoard", testDiningAndDragonBoardMessages)
	t.Run("DreamMaze", testDreamMazeMessages)
	t.Run("DropObject", testDropObjectMessages)
	t.Run("Dungeon", testDungeonMessages)
	t.Run("Emblem", testEmblemMessages)
	t.Run("EnterStage", testEnterStageMessages)
	t.Run("EntryAndEquip", testEntryAndEquipMessages)
	t.Run("Escort", testEscortMessages)
}

// 测试餐饮和龙棋盘相关消息
func testDiningAndDragonBoardMessages(t *testing.T) {
	// 测试DiningFoodBuffInfo
	diningFoodBuffInfo := &dnfv1.DiningFoodBuffInfo{
		Index:     1,
		Endtime:   1234567890,
		FoodId:    1001,
		BuffId:    2001,
		Duration:  3600,
		StartTime: 1234567890,
	}
	assert.NotNil(t, diningFoodBuffInfo)
	assert.Equal(t, int32(1), diningFoodBuffInfo.Index)
	assert.Equal(t, int64(1234567890), diningFoodBuffInfo.Endtime)
	assert.Equal(t, int32(1001), diningFoodBuffInfo.FoodId)
	assert.Equal(t, int32(2001), diningFoodBuffInfo.BuffId)
	assert.Equal(t, int32(3600), diningFoodBuffInfo.Duration)
	assert.Equal(t, int64(1234567890), diningFoodBuffInfo.StartTime)

	// 测试DragonBoardAppendage
	dragonBoardAppendage := &dnfv1.DragonBoardAppendage{
		Charguid:    1234567890,
		Index:       1,
		AppendageId: 1001,
		Level:       5,
		Exp:         1000,
	}
	assert.NotNil(t, dragonBoardAppendage)
	assert.Equal(t, uint64(1234567890), dragonBoardAppendage.Charguid)
	assert.Equal(t, int32(1), dragonBoardAppendage.Index)
	assert.Equal(t, int32(1001), dragonBoardAppendage.AppendageId)
	assert.Equal(t, int32(5), dragonBoardAppendage.Level)
	assert.Equal(t, int32(1000), dragonBoardAppendage.Exp)

	// 测试DragonBoardChanceCard
	dragonBoardChanceCard := &dnfv1.DragonBoardChanceCard{
		Index: 1,
	}
	assert.NotNil(t, dragonBoardChanceCard)
	assert.Equal(t, int32(1), dragonBoardChanceCard.Index)

	// 测试DragonBoardChangeDice
	dragonBoardChangeDice := &dnfv1.DragonBoardChangeDice{
		Charguid: 1234567890,
		Type:     1,
	}
	assert.NotNil(t, dragonBoardChangeDice)
	assert.Equal(t, uint64(1234567890), dragonBoardChangeDice.Charguid)
	assert.Equal(t, int32(1), dragonBoardChangeDice.Type)

	// 测试DragonBoardChangeSlot
	dragonBoardChangeSlot := &dnfv1.DragonBoardChangeSlot{
		Charguid: 1234567890,
		Index:    1,
	}
	assert.NotNil(t, dragonBoardChangeSlot)
	assert.Equal(t, uint64(1234567890), dragonBoardChangeSlot.Charguid)
	assert.Equal(t, int32(1), dragonBoardChangeSlot.Index)

	// 测试DragonBoardGainAppendage
	dragonBoardGainAppendage := &dnfv1.DragonBoardGainAppendage{
		Charguid:    1234567890,
		Index:       1,
		AppendageId: 1001,
		Count:       5,
	}
	assert.NotNil(t, dragonBoardGainAppendage)
	assert.Equal(t, uint64(1234567890), dragonBoardGainAppendage.Charguid)
	assert.Equal(t, int32(1), dragonBoardGainAppendage.Index)
	assert.Equal(t, int32(1001), dragonBoardGainAppendage.AppendageId)
	assert.Equal(t, int32(5), dragonBoardGainAppendage.Count)

	// 测试DragonBoardHold
	dragonBoardHold := &dnfv1.DragonBoardHold{
		Charguid: 1234567890,
		Count:    5,
	}
	assert.NotNil(t, dragonBoardHold)
	assert.Equal(t, uint64(1234567890), dragonBoardHold.Charguid)
	assert.Equal(t, int32(5), dragonBoardHold.Count)

	// 测试DragonBoardMember
	dragonBoardMember := &dnfv1.DragonBoardMember{
		Charguid:    1234567890,
		Name:        "Test Member",
		Job:         1,
		Level:       95,
		Growtype:    2,
		Secgrowtype: 3,
		Rank:        1,
		Teamtype:    1,
		Creditscore: 1000,
		Dicetype:    1,
		Slot:        1,
		Connected:   true,
		Clearboss:   true,
		Rewards:     []*dnfv1.DragonBoardReward{{Index: 1, Count: 5}},
		Appendages:  []*dnfv1.DragonBoardGainAppendage{{Charguid: 1234567890, Index: 1, AppendageId: 1001, Count: 5}},
	}
	assert.NotNil(t, dragonBoardMember)
	assert.Equal(t, uint64(1234567890), dragonBoardMember.Charguid)
	assert.Equal(t, "Test Member", dragonBoardMember.Name)
	assert.Equal(t, int32(1), dragonBoardMember.Job)
	assert.Equal(t, int32(95), dragonBoardMember.Level)
	assert.Equal(t, int32(2), dragonBoardMember.Growtype)
	assert.Equal(t, int32(3), dragonBoardMember.Secgrowtype)
	assert.Equal(t, int32(1), dragonBoardMember.Rank)
	assert.Equal(t, int32(1), dragonBoardMember.Teamtype)
	assert.Equal(t, int32(1000), dragonBoardMember.Creditscore)
	assert.Equal(t, int32(1), dragonBoardMember.Dicetype)
	assert.Equal(t, int32(1), dragonBoardMember.Slot)
	assert.Equal(t, true, dragonBoardMember.Connected)
	assert.Equal(t, true, dragonBoardMember.Clearboss)
	assert.Len(t, dragonBoardMember.Rewards, 1)
	assert.Len(t, dragonBoardMember.Appendages, 1)

	// 测试DragonBoardMove
	dragonBoardMove := &dnfv1.DragonBoardMove{
		Error:      0,
		Charguid:   1234567890,
		Slot:       1,
		Appendage:  1,
		Rewards:    []*dnfv1.DragonBoardReward{{Index: 1, Count: 5}},
		Chancecard: &dnfv1.DragonBoardChanceCard{Index: 1},
	}
	assert.NotNil(t, dragonBoardMove)
	assert.Equal(t, int32(0), dragonBoardMove.Error)
	assert.Equal(t, uint64(1234567890), dragonBoardMove.Charguid)
	assert.Equal(t, int32(1), dragonBoardMove.Slot)
	assert.Equal(t, int32(1), dragonBoardMove.Appendage)
	assert.Len(t, dragonBoardMove.Rewards, 1)
	assert.NotNil(t, dragonBoardMove.Chancecard)

	// 测试DragonBoardReward
	dragonBoardReward := &dnfv1.DragonBoardReward{
		Index: 1,
		Count: 5,
	}
	assert.NotNil(t, dragonBoardReward)
	assert.Equal(t, int32(1), dragonBoardReward.Index)
	assert.Equal(t, int32(5), dragonBoardReward.Count)

	// 测试DragonBoardResult
	dragonBoardResult := &dnfv1.DragonBoardResult{
		Charguid:    1234567890,
		Job:         1,
		Rank:        1,
		Turncount:   10,
		Playtime:    3600,
		Rewards:     []*dnfv1.DragonBoardReward{{Index: 1, Count: 5}},
		Slotrewards: []*dnfv1.DragonBoardReward{{Index: 2, Count: 10}},
		World:       1,
		Creditscore: 1000,
	}
	assert.NotNil(t, dragonBoardResult)
	assert.Equal(t, uint64(1234567890), dragonBoardResult.Charguid)
	assert.Equal(t, int32(1), dragonBoardResult.Job)
	assert.Equal(t, int32(1), dragonBoardResult.Rank)
	assert.Equal(t, int32(10), dragonBoardResult.Turncount)
	assert.Equal(t, int32(3600), dragonBoardResult.Playtime)
	assert.Len(t, dragonBoardResult.Rewards, 1)
	assert.Len(t, dragonBoardResult.Slotrewards, 1)
	assert.Equal(t, int32(1), dragonBoardResult.World)
	assert.Equal(t, int32(1000), dragonBoardResult.Creditscore)
}

// 测试梦境迷宫相关消息
func testDreamMazeMessages(t *testing.T) {
	// 测试DreamMazeDungeon
	dreamMazeDungeon := &dnfv1.DreamMazeDungeon{
		Index:            1,
		Name:             "Test Dungeon",
		Level:            95,
		Difficulty:       1,
		RecommendedLevel: 90,
		PlayerCount:      4,
		Cleartime:        3600,
		Grade:            1,
		Bosshp:           1000000,
	}
	assert.NotNil(t, dreamMazeDungeon)
	assert.Equal(t, int32(1), dreamMazeDungeon.Index)
	assert.Equal(t, "Test Dungeon", dreamMazeDungeon.Name)
	assert.Equal(t, int32(95), dreamMazeDungeon.Level)
	assert.Equal(t, int32(1), dreamMazeDungeon.Difficulty)
	assert.Equal(t, int32(90), dreamMazeDungeon.RecommendedLevel)
	assert.Equal(t, int32(4), dreamMazeDungeon.PlayerCount)
	assert.Equal(t, int64(3600), dreamMazeDungeon.Cleartime)
	assert.Equal(t, int32(1), dreamMazeDungeon.Grade)
	assert.Equal(t, int64(1000000), dreamMazeDungeon.Bosshp)

	// 测试DreamMazePlayMember
	dreamMazePlayMember := &dnfv1.DreamMazePlayMember{
		Charguid:       1234567890,
		Name:           "Test Player",
		Level:          95,
		Job:            1,
		Growtype:       2,
		Secondgrowtype: 3,
	}
	assert.NotNil(t, dreamMazePlayMember)
	assert.Equal(t, uint64(1234567890), dreamMazePlayMember.Charguid)
	assert.Equal(t, "Test Player", dreamMazePlayMember.Name)
	assert.Equal(t, int32(95), dreamMazePlayMember.Level)
	assert.Equal(t, int32(1), dreamMazePlayMember.Job)
	assert.Equal(t, int32(2), dreamMazePlayMember.Growtype)
	assert.Equal(t, int32(3), dreamMazePlayMember.Secondgrowtype)
}

// 测试掉落物品相关消息
func testDropObjectMessages(t *testing.T) {
	// 测试DropObjectItem
	dropObjectItem := &dnfv1.DropObjectItem{
		Charguid:     1234567890,
		Itemindex:    1001,
		Index:        1,
		Count:        5,
		Rarity:       1,
		Bind:         true,
		Upgrade:      0,
		Quality:      1,
		Isgrowthcare: false,
	}
	assert.NotNil(t, dropObjectItem)
	assert.Equal(t, uint64(1234567890), dropObjectItem.Charguid)
	assert.Equal(t, int32(1001), dropObjectItem.Itemindex)
	assert.Equal(t, int32(1), dropObjectItem.Index)
	assert.Equal(t, int32(5), dropObjectItem.Count)
	assert.Equal(t, int32(1), dropObjectItem.Rarity)
	assert.Equal(t, true, dropObjectItem.Bind)
	assert.Equal(t, int32(0), dropObjectItem.Upgrade)
	assert.Equal(t, int32(1), dropObjectItem.Quality)
	assert.Equal(t, false, dropObjectItem.Isgrowthcare)
}

// 测试副本相关消息
func testDungeonMessages(t *testing.T) {
	// 测试Dungeonrank
	dungeonrank := &dnfv1.Dungeonrank{
		Charguid:      1234567890,
		Name:          "Test Player",
		Job:           1,
		Level:         95,
		Growtype:      2,
		Secgrowtype:   3,
		Rank:          1,
		Score:         1000,
		Time:          3600,
		DungeonId:     1001,
		DungeonName:   "Test Dungeon",
		Counter:       100,
		Multihit:      50,
		Smash:         20,
		Givedamage:    1000000,
		Receivedamage: 100000,
	}
	assert.NotNil(t, dungeonrank)
	assert.Equal(t, uint64(1234567890), dungeonrank.Charguid)
	assert.Equal(t, "Test Player", dungeonrank.Name)
	assert.Equal(t, int32(1), dungeonrank.Job)
	assert.Equal(t, int32(95), dungeonrank.Level)
	assert.Equal(t, int32(2), dungeonrank.Growtype)
	assert.Equal(t, int32(3), dungeonrank.Secgrowtype)
	assert.Equal(t, int32(1), dungeonrank.Rank)
	assert.Equal(t, int32(1000), dungeonrank.Score)
	assert.Equal(t, int32(3600), dungeonrank.Time)
	assert.Equal(t, int32(1001), dungeonrank.DungeonId)
	assert.Equal(t, "Test Dungeon", dungeonrank.DungeonName)
	assert.Equal(t, int32(100), dungeonrank.Counter)
	assert.Equal(t, int32(50), dungeonrank.Multihit)
	assert.Equal(t, int32(20), dungeonrank.Smash)
	assert.Equal(t, int64(1000000), dungeonrank.Givedamage)
	assert.Equal(t, int64(100000), dungeonrank.Receivedamage)

	// 测试DungeonGetInfo
	dungeonGetInfo := &dnfv1.DungeonGetInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, dungeonGetInfo)
	assert.Equal(t, int32(0), dungeonGetInfo.Error)
	assert.Equal(t, int32(1), dungeonGetInfo.Index)

	// 测试DungeonParticipate
	dungeonParticipate := &dnfv1.DungeonParticipate{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, dungeonParticipate)
	assert.Equal(t, int32(0), dungeonParticipate.Error)
	assert.Equal(t, int32(1), dungeonParticipate.Index)

	// 测试DungeonPartyCount
	dungeonPartyCount := &dnfv1.DungeonPartyCount{
		Count: 4,
	}
	assert.NotNil(t, dungeonPartyCount)
	assert.Equal(t, int32(4), dungeonPartyCount.Count)

	// 测试DungeonPartyCount2
	dungeonPartyCount2 := &dnfv1.DungeonPartyCount2{
		Count: 4,
	}
	assert.NotNil(t, dungeonPartyCount2)
	assert.Equal(t, int32(4), dungeonPartyCount2.Count)

	// 测试DungeonRankInfo
	dungeonRankInfo := &dnfv1.DungeonRankInfo{
		Rank:  1,
		Score: 1000,
	}
	assert.NotNil(t, dungeonRankInfo)
	assert.Equal(t, int32(1), dungeonRankInfo.Rank)
	assert.Equal(t, int32(1000), dungeonRankInfo.Score)

	// 测试DungeonResultQuestInfo
	dungeonResultQuestInfo := &dnfv1.DungeonResultQuestInfo{
		Questid: 1001,
		Count:   5,
	}
	assert.NotNil(t, dungeonResultQuestInfo)
	assert.Equal(t, int32(1001), dungeonResultQuestInfo.Questid)
	assert.Equal(t, int32(5), dungeonResultQuestInfo.Count)

	// 测试DungeonStartInfo
	dungeonStartInfo := &dnfv1.DungeonStartInfo{
		Matchingguid: 1234567890,
		Dungeonguid:  9876543210,
		Battleworld:  1,
		Bchannel:     1,
		Bip:          "127.0.0.1",
		Bport:        8080,
	}
	assert.NotNil(t, dungeonStartInfo)
	assert.Equal(t, uint64(1234567890), dungeonStartInfo.Matchingguid)
	assert.Equal(t, uint64(9876543210), dungeonStartInfo.Dungeonguid)
	assert.Equal(t, int32(1), dungeonStartInfo.Battleworld)
	assert.Equal(t, int32(1), dungeonStartInfo.Bchannel)
	assert.Equal(t, "127.0.0.1", dungeonStartInfo.Bip)
	assert.Equal(t, int32(8080), dungeonStartInfo.Bport)
}

// 测试徽章相关消息
func testEmblemMessages(t *testing.T) {
	// 测试EmblemRequest
	emblemRequest := &dnfv1.EmblemRequest{
		Index: 1,
	}
	assert.NotNil(t, emblemRequest)
	assert.Equal(t, int32(1), emblemRequest.Index)

	// 测试EmblemResult
	emblemResult := &dnfv1.EmblemResult{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, emblemResult)
	assert.Equal(t, int32(0), emblemResult.Error)
	assert.Equal(t, int32(1), emblemResult.Index)
}

// 测试进入舞台相关消息
func testEnterStageMessages(t *testing.T) {
	// 测试EnterStageMap
	enterStageMap := &dnfv1.EnterStageMap{
		Guid:    1234567890,
		Objects: []*dnfv1.EnterStageObject{{Guid: 1234567890}},
	}
	assert.NotNil(t, enterStageMap)
	assert.Equal(t, uint64(1234567890), enterStageMap.Guid)
	assert.Len(t, enterStageMap.Objects, 1)

	// 测试EnterStageObject
	enterStageObject := &dnfv1.EnterStageObject{
		Guid: 1234567890,
	}
	assert.NotNil(t, enterStageObject)
	assert.Equal(t, uint64(1234567890), enterStageObject.Guid)
}

// 测试入口和装备相关消息
func testEntryAndEquipMessages(t *testing.T) {
	// 测试EntryInfo
	entryInfo := &dnfv1.EntryInfo{
		Charguid: 1234567890,
		Name:     "Test Player",
		Level:    95,
		Job:      1,
	}
	assert.NotNil(t, entryInfo)
	assert.Equal(t, uint64(1234567890), entryInfo.Charguid)
	assert.Equal(t, "Test Player", entryInfo.Name)
	assert.Equal(t, int32(95), entryInfo.Level)
	assert.Equal(t, int32(1), entryInfo.Job)

	// 测试EquipPutOnOff
	equipPutOnOff := &dnfv1.EquipPutOnOff{
		Guid: 1234567890,
		Slot: 1,
	}
	assert.NotNil(t, equipPutOnOff)
	assert.Equal(t, uint64(1234567890), equipPutOnOff.Guid)
	assert.Equal(t, int32(1), equipPutOnOff.Slot)
}

// 测试护送相关消息
func testEscortMessages(t *testing.T) {
	// 测试EscortClearReward
	escortClearReward := &dnfv1.EscortClearReward{
		Error:    0,
		Type:     1,
		Index:    1,
		Count:    5,
		Level:    95,
		Totalexp: 1000000,
	}
	assert.NotNil(t, escortClearReward)
	assert.Equal(t, int32(0), escortClearReward.Error)
	assert.Equal(t, int32(1), escortClearReward.Type)
	assert.Equal(t, int32(1), escortClearReward.Index)
	assert.Equal(t, int32(5), escortClearReward.Count)
	assert.Equal(t, int32(95), escortClearReward.Level)
	assert.Equal(t, int64(1000000), escortClearReward.Totalexp)

	// 测试EscortCurrentReward
	escortCurrentReward := &dnfv1.EscortCurrentReward{
		Index: 1,
	}
	assert.NotNil(t, escortCurrentReward)
	assert.Equal(t, int32(1), escortCurrentReward.Index)

	// 测试EscortDoneReady
	escortDoneReady := &dnfv1.EscortDoneReady{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, escortDoneReady)
	assert.Equal(t, int32(0), escortDoneReady.Error)
	assert.Equal(t, int32(1), escortDoneReady.Index)

	// 测试EscortReward
	escortReward := &dnfv1.EscortReward{
		Type:     1,
		Index:    1,
		Count:    5,
		Addexp:   1000,
		Totalexp: 10000,
		Wagonhp:  100,
	}
	assert.NotNil(t, escortReward)
	assert.Equal(t, int32(1), escortReward.Type)
	assert.Equal(t, int32(1), escortReward.Index)
	assert.Equal(t, int32(5), escortReward.Count)
	assert.Equal(t, int64(1000), escortReward.Addexp)
	assert.Equal(t, int64(10000), escortReward.Totalexp)
	assert.Equal(t, int32(100), escortReward.Wagonhp)
}
