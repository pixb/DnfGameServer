package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch54Migration 测试批次54的迁移结果
func TestBatch54Migration(t *testing.T) {
	// 测试用户系统
	t.Run("UserSystem", testBatch54UserSystem)

	// 测试游戏系统
	t.Run("GameSystem", testBatch54GameSystem)

	// 测试奖励系统
	t.Run("RewardSystem", testBatch54RewardSystem)

	// 测试皮肤系统
	t.Run("SkinSystem", testBatch54SkinSystem)

	// 测试系统命令
	t.Run("SystemCommand", testBatch54SystemCommand)
}

// testBatch54UserSystem 测试用户系统
func testBatch54UserSystem(t *testing.T) {
	// 测试UserName
	userName := &dnfv1.UserName{
		Name: "Test User",
	}
	assert.NotNil(t, userName)
	assert.Equal(t, "Test User", userName.Name)

	// 测试UserMinimumInfo
	userMinimumInfo := &dnfv1.UserMinimumInfo{
		Charguid:     1234567890,
		Growtype:     1,
		Secgrowtype:  2,
		Job:          5,
		Level:        85,
		Name:         "Test Player",
		Teamtype:     dnfv1.TeamType_TEAM_TYPE_NORMAL,
		Rank:         10,
		Pvpstargrade: 5,
		Profileurl:   "http://example.com/profile.jpg",
		World:        1,
		Skinchatinfo: &dnfv1.SkinChatInfo{
			SkinId:         1,
			ChatContent:    "Hello",
			Chatframe:      1,
			Characterframe: 2,
		},
		Iskeyboard: true,
	}
	assert.NotNil(t, userMinimumInfo)
	assert.Equal(t, uint64(1234567890), userMinimumInfo.Charguid)
	assert.Equal(t, int32(1), userMinimumInfo.Growtype)
	assert.Equal(t, int32(2), userMinimumInfo.Secgrowtype)
	assert.Equal(t, int32(5), userMinimumInfo.Job)
	assert.Equal(t, int32(85), userMinimumInfo.Level)
	assert.Equal(t, "Test Player", userMinimumInfo.Name)
	assert.Equal(t, dnfv1.TeamType_TEAM_TYPE_NORMAL, userMinimumInfo.Teamtype)
	assert.Equal(t, int32(10), userMinimumInfo.Rank)
	assert.Equal(t, int32(5), userMinimumInfo.Pvpstargrade)
	assert.Equal(t, "http://example.com/profile.jpg", userMinimumInfo.Profileurl)
	assert.Equal(t, int32(1), userMinimumInfo.World)
	assert.NotNil(t, userMinimumInfo.Skinchatinfo)
	assert.Equal(t, int32(1), userMinimumInfo.Skinchatinfo.Chatframe)
	assert.Equal(t, int32(2), userMinimumInfo.Skinchatinfo.Characterframe)
	assert.True(t, userMinimumInfo.Iskeyboard)

	// 测试UserLoadingStatus
	userLoadingStatus := &dnfv1.UserLoadingStatus{
		Charguid:     1234567890,
		Ready:        true,
		Waiting:      false,
		Disconnect:   false,
		Leave:        false,
		Kickoutvotes: []uint64{1234567891, 1234567892},
	}
	assert.NotNil(t, userLoadingStatus)
	assert.Equal(t, uint64(1234567890), userLoadingStatus.Charguid)
	assert.True(t, userLoadingStatus.Ready)
	assert.False(t, userLoadingStatus.Waiting)
	assert.False(t, userLoadingStatus.Disconnect)
	assert.False(t, userLoadingStatus.Leave)
	assert.Len(t, userLoadingStatus.Kickoutvotes, 2)

	// 测试SimpleCharacter
	simpleCharacter := &dnfv1.SimpleCharacter{
		Charguid:       1234567890,
		Name:           "Test Player",
		Level:          85,
		Job:            5,
		Growtype:       1,
		Secgrowtype:    2,
		Equipscore:     10000,
		Characterframe: 10,
	}
	assert.NotNil(t, simpleCharacter)
	assert.Equal(t, uint64(1234567890), simpleCharacter.Charguid)
	assert.Equal(t, "Test Player", simpleCharacter.Name)
	assert.Equal(t, int32(85), simpleCharacter.Level)
	assert.Equal(t, int32(5), simpleCharacter.Job)
	assert.Equal(t, int32(1), simpleCharacter.Growtype)
	assert.Equal(t, int32(2), simpleCharacter.Secgrowtype)
	assert.Equal(t, int32(10000), simpleCharacter.Equipscore)
	assert.Equal(t, int32(10), simpleCharacter.Characterframe)

	// 测试RoomUserInfo
	roomUserInfo := &dnfv1.RoomUserInfo{
		Charguid:       1234567890,
		Level:          85,
		Job:            5,
		Growtype:       1,
		Secondgrowtype: 2,
		Name:           "Test Player",
		Teamtype:       1,
		Creditscore:    1000,
		Ready:          true,
		Skinchatinfo: &dnfv1.SkinChatInfo{
			Chatframe:      1,
			Characterframe: 2,
		},
	}
	assert.NotNil(t, roomUserInfo)
	assert.Equal(t, uint64(1234567890), roomUserInfo.Charguid)
	assert.Equal(t, int32(85), roomUserInfo.Level)
	assert.Equal(t, int32(5), roomUserInfo.Job)
	assert.Equal(t, int32(1), roomUserInfo.Growtype)
	assert.Equal(t, int32(2), roomUserInfo.Secondgrowtype)
	assert.Equal(t, "Test Player", roomUserInfo.Name)
	assert.Equal(t, int32(1), roomUserInfo.Teamtype)
	assert.Equal(t, int32(1000), roomUserInfo.Creditscore)
	assert.True(t, roomUserInfo.Ready)
	assert.NotNil(t, roomUserInfo.Skinchatinfo)

	// 测试WatingListUserInfo
	watingListUserInfo := &dnfv1.WatingListUserInfo{
		Charguid:    1234567890,
		Name:        "Test Player",
		Gname:       "Test Guild",
		Level:       85,
		Job:         5,
		Growtype:    1,
		Secgrowtype: 2,
		Leader:      true,
		Ready:       true,
		Customdata:  1234567891,
		Partyguid:   1234567892,
	}
	assert.NotNil(t, watingListUserInfo)
	assert.Equal(t, uint64(1234567890), watingListUserInfo.Charguid)
	assert.Equal(t, "Test Player", watingListUserInfo.Name)
	assert.Equal(t, "Test Guild", watingListUserInfo.Gname)
	assert.Equal(t, int32(85), watingListUserInfo.Level)
	assert.Equal(t, int32(5), watingListUserInfo.Job)
	assert.Equal(t, int32(1), watingListUserInfo.Growtype)
	assert.Equal(t, int32(2), watingListUserInfo.Secgrowtype)
	assert.True(t, watingListUserInfo.Leader)
	assert.True(t, watingListUserInfo.Ready)
	assert.Equal(t, int64(1234567891), watingListUserInfo.Customdata)
	assert.Equal(t, uint64(1234567892), watingListUserInfo.Partyguid)
}

// testBatch54GameSystem 测试游戏系统
func testBatch54GameSystem(t *testing.T) {
	// 测试UserBoardGameResult
	userBoardGameResult := &dnfv1.UserBoardGameResult{
		Charguid: 1234567890,
		Playtime: 3600,
		Mine:     100,
		Gold:     500,
		Hp:       1000,
		Relic:    50,
	}
	assert.NotNil(t, userBoardGameResult)
	assert.Equal(t, uint64(1234567890), userBoardGameResult.Charguid)
	assert.Equal(t, int32(3600), userBoardGameResult.Playtime)
	assert.Equal(t, int32(100), userBoardGameResult.Mine)
	assert.Equal(t, int32(500), userBoardGameResult.Gold)
	assert.Equal(t, int32(1000), userBoardGameResult.Hp)
	assert.Equal(t, int32(50), userBoardGameResult.Relic)

	// 测试TeamResult
	teamResult := &dnfv1.TeamResult{
		Win:        1,
		Addpoint:   100,
		Clearcount: 5,
	}
	assert.NotNil(t, teamResult)
	assert.Equal(t, int32(1), teamResult.Win)
	assert.Equal(t, int32(100), teamResult.Addpoint)
	assert.Equal(t, int32(5), teamResult.Clearcount)
}

// testBatch54RewardSystem 测试奖励系统
func testBatch54RewardSystem(t *testing.T) {
	// 测试TowerRewardItems
	towerRewardItems := &dnfv1.TowerRewardItems{
		Equipitems:     []*dnfv1.Equip{},
		Titleitems:     []*dnfv1.Equip{},
		Flagitems:      []*dnfv1.Equip{},
		Materialitems:  []*dnfv1.Stackable{},
		Consumeitems:   []*dnfv1.Stackable{},
		Emblemitems:    []*dnfv1.Stackable{},
		Carditems:      []*dnfv1.Stackable{},
		Epicpieceitems: []*dnfv1.Stackable{},
		Cartifactitems: []*dnfv1.Artifact{},
		Creatureitems:  []*dnfv1.Creature{},
		Avataritems:    []*dnfv1.AvatarItem{},
		Index:          1,
		Count:          5,
	}
	assert.NotNil(t, towerRewardItems)
	assert.Equal(t, int32(1), towerRewardItems.Index)
	assert.Equal(t, int32(5), towerRewardItems.Count)

	// 测试TimeAttackReward
	timeAttackReward := &dnfv1.TimeAttackReward{
		Index: 1,
		Count: 5,
	}
	assert.NotNil(t, timeAttackReward)
	assert.Equal(t, int32(1), timeAttackReward.Index)
	assert.Equal(t, int32(5), timeAttackReward.Count)

	// 测试ShareRewardInfo
	shareRewardInfo := &dnfv1.ShareRewardInfo{
		Index: 1,
		State: 2,
	}
	assert.NotNil(t, shareRewardInfo)
	assert.Equal(t, int32(1), shareRewardInfo.Index)
	assert.Equal(t, int32(2), shareRewardInfo.State)
}

// testBatch54SkinSystem 测试皮肤系统
func testBatch54SkinSystem(t *testing.T) {
	// 测试SkinChatInfo
	skinChatInfo := &dnfv1.SkinChatInfo{
		SkinId:         1,
		ChatContent:    "Hello",
		Chatframe:      1,
		Characterframe: 2,
	}
	assert.NotNil(t, skinChatInfo)
	assert.Equal(t, int32(1), skinChatInfo.SkinId)
	assert.Equal(t, "Hello", skinChatInfo.ChatContent)
	assert.Equal(t, int32(1), skinChatInfo.Chatframe)
	assert.Equal(t, int32(2), skinChatInfo.Characterframe)

	// 测试SkinItemInfo
	skinItemInfo := &dnfv1.SkinItemInfo{
		Index:      1,
		Guid:       1234567890,
		Expiretime: 1234567890000,
	}
	assert.NotNil(t, skinItemInfo)
	assert.Equal(t, int32(1), skinItemInfo.Index)
	assert.Equal(t, uint64(1234567890), skinItemInfo.Guid)
	assert.Equal(t, int64(1234567890000), skinItemInfo.Expiretime)

	// 测试SkinEquipPutOnOff
	skinEquipPutOnOff := &dnfv1.SkinEquipPutOnOff{
		Index:   1,
		Isequip: 1,
	}
	assert.NotNil(t, skinEquipPutOnOff)
	assert.Equal(t, int32(1), skinEquipPutOnOff.Index)
	assert.Equal(t, int32(1), skinEquipPutOnOff.Isequip)
}

// testBatch54SystemCommand 测试系统命令
func testBatch54SystemCommand(t *testing.T) {
	// 测试Tutorial
	tutorial := &dnfv1.Tutorial{
		Index:   1,
		State:   2,
		Type:    3,
		Enforce: true,
	}
	assert.NotNil(t, tutorial)
	assert.Equal(t, int32(1), tutorial.Index)
	assert.Equal(t, int32(2), tutorial.State)
	assert.Equal(t, int32(3), tutorial.Type)
	assert.True(t, tutorial.Enforce)

	// 测试TutorialInfo
	tutorialInfo := &dnfv1.TutorialInfo{
		Step: 1,
		Coin: 100,
	}
	assert.NotNil(t, tutorialInfo)
	assert.Equal(t, int32(1), tutorialInfo.Step)
	assert.Equal(t, int32(100), tutorialInfo.Coin)
}
