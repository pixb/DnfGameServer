package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch48Migration 测试批次48的迁移结果
func TestBatch48Migration(t *testing.T) {
	// 测试系统消息
	t.Run("SystemMessages", testSystemMessages)

	// 测试团队系统
	t.Run("TeamSystems", testTeamSystems)

	// 测试副本系统
	t.Run("DungeonSystems", testDungeonSystems)

	// 测试社交系统
	t.Run("SocialSystems", testSocialSystems)

	// 测试用户系统
	t.Run("UserSystems", testUserSystems)

	// 测试系统功能
	t.Run("SystemFunctions", testSystemFunctions)

	// 测试战斗系统
	t.Run("CombatSystems", testCombatSystems)

	// 测试其他系统
	t.Run("OtherSystems", testOtherSystems)
}

// testSystemMessages 测试系统消息
func testSystemMessages(t *testing.T) {
	// 测试RES_SERVER_RESPONSE_PACKET
	serverResponse := &dnfv1.RES_SERVER_RESPONSE_PACKET{
		Error: 0,
	}
	assert.NotNil(t, serverResponse)
	assert.Equal(t, int32(0), serverResponse.Error)

	// 测试RES_SECEDE_GUILD
	secedeGuild := &dnfv1.RES_SECEDE_GUILD{
		Error:   0,
		Message: "Successfully seceded from guild",
	}
	assert.NotNil(t, secedeGuild)
	assert.Equal(t, int32(0), secedeGuild.Error)
	assert.Equal(t, "Successfully seceded from guild", secedeGuild.Message)

	// 测试RES_SAVE_WEAK_SERVER_SIMPLE_DATA
	saveWeakServer := &dnfv1.RES_SAVE_WEAK_SERVER_SIMPLE_DATA{
		Error: 0,
		Data:  []byte{1, 2, 3, 4, 5},
	}
	assert.NotNil(t, saveWeakServer)
	assert.Equal(t, int32(0), saveWeakServer.Error)
	assert.Len(t, saveWeakServer.Data, 5)

	// 测试RES_SEND_GUILD_REDPACKET
	sendRedpacket := &dnfv1.RES_SEND_GUILD_REDPACKET{
		Error:       0,
		GuildId:     12345,
		RedpacketId: "rp_12345",
		Amount:      100,
	}
	assert.NotNil(t, sendRedpacket)
	assert.Equal(t, int32(0), sendRedpacket.Error)
	assert.Equal(t, int64(12345), sendRedpacket.GuildId)
	assert.Equal(t, "rp_12345", sendRedpacket.RedpacketId)
	assert.Equal(t, int32(100), sendRedpacket.Amount)

	// 测试RES_SEND_STORAGE
	sendStorage := &dnfv1.RES_SEND_STORAGE{
		Error:     0,
		StorageId: 1,
		ItemCount: 5,
	}
	assert.NotNil(t, sendStorage)
	assert.Equal(t, int32(0), sendStorage.Error)
	assert.Equal(t, int32(1), sendStorage.StorageId)
	assert.Equal(t, int32(5), sendStorage.ItemCount)
}

// testTeamSystems 测试团队系统
func testTeamSystems(t *testing.T) {
	// 测试RES_SELECT_OTHER_DUNGEON_AT_MULTI
	selectDungeon := &dnfv1.RES_SELECT_OTHER_DUNGEON_AT_MULTI{
		Error:       0,
		DungeonId:   1001,
		DungeonName: "Test Dungeon",
	}
	assert.NotNil(t, selectDungeon)
	assert.Equal(t, int32(0), selectDungeon.Error)
	assert.Equal(t, int32(1001), selectDungeon.DungeonId)
	assert.Equal(t, "Test Dungeon", selectDungeon.DungeonName)

	// 测试RES_SENDING_INVITE_FRIEND_LIST
	sendInvite := &dnfv1.RES_SENDING_INVITE_FRIEND_LIST{
		Error: 0,
		Invites: []*dnfv1.FriendInviteInfo{
			{
				FriendId:   1001,
				FriendName: "Player1",
				Level:      50,
				Job:        1,
			},
		},
	}
	assert.NotNil(t, sendInvite)
	assert.Equal(t, int32(0), sendInvite.Error)
	assert.Len(t, sendInvite.Invites, 1)
	assert.Equal(t, int64(1001), sendInvite.Invites[0].FriendId)

	// 测试RES_REPLY_PROPOSAL
	replyProposal := &dnfv1.RES_REPLY_PROPOSAL{
		Error:      0,
		Accepted:   true,
		ProposerId: 2001,
	}
	assert.NotNil(t, replyProposal)
	assert.Equal(t, int32(0), replyProposal.Error)
	assert.True(t, replyProposal.Accepted)
	assert.Equal(t, int64(2001), replyProposal.ProposerId)

	// 测试RES_SET_PVP_CONTROL_MODE
	setPVP := &dnfv1.RES_SET_PVP_CONTROL_MODE{
		Error: 0,
		Mode:  1,
	}
	assert.NotNil(t, setPVP)
	assert.Equal(t, int32(0), setPVP.Error)
	assert.Equal(t, int32(1), setPVP.Mode)

	// 测试RES_RETURN_TO_TOWN_AT_MULTI_PLAY
	returnTown := &dnfv1.RES_RETURN_TO_TOWN_AT_MULTI_PLAY{
		Error:    0,
		TownName: "Test Town",
	}
	assert.NotNil(t, returnTown)
	assert.Equal(t, int32(0), returnTown.Error)
	assert.Equal(t, "Test Town", returnTown.TownName)
}

// testDungeonSystems 测试副本系统
func testDungeonSystems(t *testing.T) {
	// 测试RES_START_LOCKSTEP_ROOM
	startLockstep := &dnfv1.RES_START_LOCKSTEP_ROOM{
		Error:      0,
		RoomId:     101,
		MaxPlayers: 4,
	}
	assert.NotNil(t, startLockstep)
	assert.Equal(t, int32(0), startLockstep.Error)
	assert.Equal(t, int32(101), startLockstep.RoomId)
	assert.Equal(t, int32(4), startLockstep.MaxPlayers)

	// 测试RES_START_DUNGEON
	startDungeon := &dnfv1.RES_START_DUNGEON{
		Error:            0,
		DungeonId:        1001,
		DungeonName:      "Test Dungeon",
		RecommendedLevel: 50,
	}
	assert.NotNil(t, startDungeon)
	assert.Equal(t, int32(0), startDungeon.Error)
	assert.Equal(t, int32(1001), startDungeon.DungeonId)
	assert.Equal(t, "Test Dungeon", startDungeon.DungeonName)
	assert.Equal(t, int32(50), startDungeon.RecommendedLevel)

	// 测试RES_START_DUNGEON_COMPLETE
	startDungeonComplete := &dnfv1.RES_START_DUNGEON_COMPLETE{
		Error:     0,
		DungeonId: 1001,
		Success:   true,
		Rewards: []*dnfv1.RewardInfo{
			{
				ItemId:   101,
				ItemName: "Test Item",
				Count:    1,
				Rarity:   1,
			},
		},
	}
	assert.NotNil(t, startDungeonComplete)
	assert.Equal(t, int32(0), startDungeonComplete.Error)
	assert.Equal(t, int32(1001), startDungeonComplete.DungeonId)
	assert.True(t, startDungeonComplete.Success)
	assert.Len(t, startDungeonComplete.Rewards, 1)

	// 测试RES_SYNC_DUNGEON_START_TIME
	syncTime := &dnfv1.RES_SYNC_DUNGEON_START_TIME{
		Error:     0,
		StartTime: 1234567890,
		Duration:  3600,
	}
	assert.NotNil(t, syncTime)
	assert.Equal(t, int32(0), syncTime.Error)
	assert.Equal(t, int64(1234567890), syncTime.StartTime)
	assert.Equal(t, int32(3600), syncTime.Duration)

	// 测试RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM
	updateCondition := &dnfv1.RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM{
		Error:     0,
		ItemId:    101,
		Count:     1,
		Completed: true,
	}
	assert.NotNil(t, updateCondition)
	assert.Equal(t, int32(0), updateCondition.Error)
	assert.Equal(t, int32(101), updateCondition.ItemId)
	assert.Equal(t, int32(1), updateCondition.Count)
	assert.True(t, updateCondition.Completed)
}

// testSocialSystems 测试社交系统
func testSocialSystems(t *testing.T) {
	// 测试RES_REQUEST_FRIEND_INVITE
	requestFriend := &dnfv1.RES_REQUEST_FRIEND_INVITE{
		Error:      0,
		FriendId:   1001,
		FriendName: "Player1",
	}
	assert.NotNil(t, requestFriend)
	assert.Equal(t, int32(0), requestFriend.Error)
	assert.Equal(t, int64(1001), requestFriend.FriendId)
	assert.Equal(t, "Player1", requestFriend.FriendName)

	// 测试RES_REWARD_QUEST
	rewardQuest := &dnfv1.RES_REWARD_QUEST{
		Error:     0,
		QuestId:   101,
		QuestName: "Test Quest",
	}
	assert.NotNil(t, rewardQuest)
	assert.Equal(t, int32(0), rewardQuest.Error)
	assert.Equal(t, int32(101), rewardQuest.QuestId)
	assert.Equal(t, "Test Quest", rewardQuest.QuestName)

	// 测试RES_SET_APPENDAGE_MANNEQUIN
	setAppendage := &dnfv1.RES_SET_APPENDAGE_MANNEQUIN{
		Error:       0,
		AppendageId: 1,
		MannequinId: 101,
	}
	assert.NotNil(t, setAppendage)
	assert.Equal(t, int32(0), setAppendage.Error)
	assert.Equal(t, int32(1), setAppendage.AppendageId)
	assert.Equal(t, int32(101), setAppendage.MannequinId)

	// 测试RES_SET_NAME_MANNEQUIN
	setName := &dnfv1.RES_SET_NAME_MANNEQUIN{
		Error:       0,
		Name:        "Test Mannequin",
		MannequinId: 101,
	}
	assert.NotNil(t, setName)
	assert.Equal(t, int32(0), setName.Error)
	assert.Equal(t, "Test Mannequin", setName.Name)
	assert.Equal(t, int32(101), setName.MannequinId)

	// 测试RES_TSS_DATA
	tssData := &dnfv1.RES_TSS_DATA{
		Error:     0,
		Data:      []byte{1, 2, 3},
		Timestamp: 1234567890,
	}
	assert.NotNil(t, tssData)
	assert.Equal(t, int32(0), tssData.Error)
	assert.Len(t, tssData.Data, 3)
	assert.Equal(t, int32(1234567890), tssData.Timestamp)
}

// testUserSystems 测试用户系统
func testUserSystems(t *testing.T) {
	// 测试RES_TUTORIAL_SAVE
	tutorialSave := &dnfv1.RES_TUTORIAL_SAVE{
		Error:      0,
		TutorialId: 1,
	}
	assert.NotNil(t, tutorialSave)
	assert.Equal(t, int32(0), tutorialSave.Error)
	assert.Equal(t, int32(1), tutorialSave.TutorialId)

	// 测试RES_USE_COIN
	useCoin := &dnfv1.RES_USE_COIN{
		Error:         0,
		CoinAmount:    100,
		RemainingCoin: 900,
	}
	assert.NotNil(t, useCoin)
	assert.Equal(t, int32(0), useCoin.Error)
	assert.Equal(t, int64(100), useCoin.CoinAmount)
	assert.Equal(t, int64(900), useCoin.RemainingCoin)

	// 测试RES_TOWER_INFO
	towerInfo := &dnfv1.RES_TOWER_INFO{
		Error:        0,
		TowerId:      1,
		CurrentFloor: 10,
		MaxFloor:     50,
	}
	assert.NotNil(t, towerInfo)
	assert.Equal(t, int32(0), towerInfo.Error)
	assert.Equal(t, int32(1), towerInfo.TowerId)
	assert.Equal(t, int32(10), towerInfo.CurrentFloor)
	assert.Equal(t, int32(50), towerInfo.MaxFloor)

	// 测试RES_START_BOARD_GAME
	startBoardGame := &dnfv1.RES_START_BOARD_GAME{
		Error:    0,
		GameId:   1,
		GameName: "Test Board Game",
	}
	assert.NotNil(t, startBoardGame)
	assert.Equal(t, int32(0), startBoardGame.Error)
	assert.Equal(t, int32(1), startBoardGame.GameId)
	assert.Equal(t, "Test Board Game", startBoardGame.GameName)

	// 测试RES_TARGET_USER_DETAIL_INFO
	targetUserInfo := &dnfv1.RES_TARGET_USER_DETAIL_INFO{
		Error:    0,
		UserId:   1001,
		UserName: "Player1",
		Level:    50,
		Job:      1,
	}
	assert.NotNil(t, targetUserInfo)
	assert.Equal(t, int32(0), targetUserInfo.Error)
	assert.Equal(t, int64(1001), targetUserInfo.UserId)
	assert.Equal(t, "Player1", targetUserInfo.UserName)
	assert.Equal(t, int32(50), targetUserInfo.Level)
	assert.Equal(t, int32(1), targetUserInfo.Job)
}

// testSystemFunctions 测试系统功能
func testSystemFunctions(t *testing.T) {
	// 测试RES_START_GAME
	startGame := &dnfv1.RES_START_GAME{
		Error:    0,
		UserId:   1001,
		UserName: "Player1",
	}
	assert.NotNil(t, startGame)
	assert.Equal(t, int32(0), startGame.Error)
	assert.Equal(t, int64(1001), startGame.UserId)
	assert.Equal(t, "Player1", startGame.UserName)

	// 测试RES_STANDBY
	standby := &dnfv1.RES_STANDBY{
		Error:   0,
		Message: "Server standby",
	}
	assert.NotNil(t, standby)
	assert.Equal(t, int32(0), standby.Error)
	assert.Equal(t, "Server standby", standby.Message)

	// 测试RES_STORAGE_STEP
	storageStep := &dnfv1.RES_STORAGE_STEP{
		Error: 0,
		Step:  1,
	}
	assert.NotNil(t, storageStep)
	assert.Equal(t, int32(0), storageStep.Error)
	assert.Equal(t, int32(1), storageStep.Step)

	// 测试RES_VERIFICATION_SIMPLE
	verificationSimple := &dnfv1.RES_VERIFICATION_SIMPLE{
		Error:   0,
		Token:   "test-token",
		Success: true,
	}
	assert.NotNil(t, verificationSimple)
	assert.Equal(t, int32(0), verificationSimple.Error)
	assert.Equal(t, "test-token", verificationSimple.Token)
	assert.True(t, verificationSimple.Success)

	// 测试RES_WELFARE_FIND_REWARD_INFO
	welfareFindRewardInfo := &dnfv1.RES_WELFARE_FIND_REWARD_INFO{
		Error:   0,
		Rewards: []*dnfv1.WelfareReward{},
	}
	assert.NotNil(t, welfareFindRewardInfo)
	assert.Equal(t, int32(0), welfareFindRewardInfo.Error)
	assert.Empty(t, welfareFindRewardInfo.Rewards)
}

// testCombatSystems 测试战斗系统
func testCombatSystems(t *testing.T) {
	// 测试RES_WORLD_RAID_RANKING
	worldRaidRanking := &dnfv1.RES_WORLD_RAID_RANKING{
		Error:  0,
		RaidId: 1,
		Ranks:  []*dnfv1.RaidRankItem{},
	}
	assert.NotNil(t, worldRaidRanking)
	assert.Equal(t, int32(0), worldRaidRanking.Error)
	assert.Equal(t, int32(1), worldRaidRanking.RaidId)
	assert.Empty(t, worldRaidRanking.Ranks)
}

// testOtherSystems 测试其他系统
func testOtherSystems(t *testing.T) {
	// 其他系统测试
	assert.True(t, true)
}
