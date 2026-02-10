package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch49Migration 测试批次49的迁移结果
func TestBatch49Migration(t *testing.T) {
	// 测试系统消息补充
	t.Run("SystemMessagesSupplement", testSystemMessagesSupplement)
	
	// 测试团队系统补充
	t.Run("TeamSystemsSupplement", testTeamSystemsSupplement)
	
	// 测试副本系统补充
	t.Run("DungeonSystemsSupplement", testDungeonSystemsSupplement)
	
	// 测试社交系统补充
	t.Run("SocialSystemsSupplement", testSocialSystemsSupplement)
}

// testSystemMessagesSupplement 测试系统消息补充
func testSystemMessagesSupplement(t *testing.T) {
	// 测试RES_SERVER_RESPONSE_PACKET_SUPPLEMENT
	serverResponseSupplement := &dnfv1.RES_SERVER_RESPONSE_PACKET_SUPPLEMENT{
		Error:   0,
		Message: "Success",
		Code:    200,
	}
	assert.NotNil(t, serverResponseSupplement)
	assert.Equal(t, int32(0), serverResponseSupplement.Error)
	assert.Equal(t, "Success", serverResponseSupplement.Message)
	assert.Equal(t, int32(200), serverResponseSupplement.Code)
	
	// 测试RES_SECEDE_GUILD_DETAIL
	secedeGuildDetail := &dnfv1.RES_SECEDE_GUILD_DETAIL{
		Error:     0,
		Message:   "Successfully seceded from guild",
		GuildId:   12345,
		GuildName: "Test Guild",
	}
	assert.NotNil(t, secedeGuildDetail)
	assert.Equal(t, int32(0), secedeGuildDetail.Error)
	assert.Equal(t, "Successfully seceded from guild", secedeGuildDetail.Message)
	assert.Equal(t, int64(12345), secedeGuildDetail.GuildId)
	assert.Equal(t, "Test Guild", secedeGuildDetail.GuildName)
	
	// 测试RES_SAVE_WEAK_SERVER_FULL_DATA
	saveWeakServerFull := &dnfv1.RES_SAVE_WEAK_SERVER_FULL_DATA{
		Error:     0,
		Data:      []byte{1, 2, 3, 4, 5},
		Timestamp: 1234567890,
		Signature: "test-signature",
	}
	assert.NotNil(t, saveWeakServerFull)
	assert.Equal(t, int32(0), saveWeakServerFull.Error)
	assert.Len(t, saveWeakServerFull.Data, 5)
	assert.Equal(t, int64(1234567890), saveWeakServerFull.Timestamp)
	assert.Equal(t, "test-signature", saveWeakServerFull.Signature)
	
	// 测试RES_SEND_GUILD_REDPACKET_DETAIL
	sendRedpacketDetail := &dnfv1.RES_SEND_GUILD_REDPACKET_DETAIL{
		Error:               0,
		GuildId:             12345,
		RedpacketId:         "rp_12345",
		Amount:              100,
		SenderName:          "Sender1",
		ParticipantCount:    5,
	}
	assert.NotNil(t, sendRedpacketDetail)
	assert.Equal(t, int32(0), sendRedpacketDetail.Error)
	assert.Equal(t, int64(12345), sendRedpacketDetail.GuildId)
	assert.Equal(t, "rp_12345", sendRedpacketDetail.RedpacketId)
	assert.Equal(t, int32(100), sendRedpacketDetail.Amount)
	assert.Equal(t, "Sender1", sendRedpacketDetail.SenderName)
	assert.Equal(t, int32(5), sendRedpacketDetail.ParticipantCount)
	
	// 测试RES_SEND_STORAGE_COMPLETE
	sendStorageComplete := &dnfv1.RES_SEND_STORAGE_COMPLETE{
		Error:     0,
		StorageId: 1,
		ItemCount: 5,
		Success:   true,
		Message:   "Storage sent successfully",
	}
	assert.NotNil(t, sendStorageComplete)
	assert.Equal(t, int32(0), sendStorageComplete.Error)
	assert.Equal(t, int32(1), sendStorageComplete.StorageId)
	assert.Equal(t, int32(5), sendStorageComplete.ItemCount)
	assert.True(t, sendStorageComplete.Success)
	assert.Equal(t, "Storage sent successfully", sendStorageComplete.Message)
}

// testTeamSystemsSupplement 测试团队系统补充
func testTeamSystemsSupplement(t *testing.T) {
	// 测试RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL
	selectDungeonDetail := &dnfv1.RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL{
		Error:               0,
		DungeonId:           1001,
		DungeonName:         "Test Dungeon",
		Difficulty:          1,
		RecommendedLevel:    50,
		PlayerCount:         4,
	}
	assert.NotNil(t, selectDungeonDetail)
	assert.Equal(t, int32(0), selectDungeonDetail.Error)
	assert.Equal(t, int32(1001), selectDungeonDetail.DungeonId)
	assert.Equal(t, "Test Dungeon", selectDungeonDetail.DungeonName)
	assert.Equal(t, int32(1), selectDungeonDetail.Difficulty)
	assert.Equal(t, int32(50), selectDungeonDetail.RecommendedLevel)
	assert.Equal(t, int32(4), selectDungeonDetail.PlayerCount)
	
	// 测试RES_SENDING_INVITE_FRIEND_LIST_FULL
	sendInviteFull := &dnfv1.RES_SENDING_INVITE_FRIEND_LIST_FULL{
		Error:      0,
		Invites: []*dnfv1.FriendInviteInfo{
			{
				FriendId:   1001,
				FriendName: "Player1",
				Level:      50,
				Job:        1,
			},
		},
		TotalCount: 1,
		Page:       1,
		PageSize:   10,
	}
	assert.NotNil(t, sendInviteFull)
	assert.Equal(t, int32(0), sendInviteFull.Error)
	assert.Len(t, sendInviteFull.Invites, 1)
	assert.Equal(t, int32(1), sendInviteFull.TotalCount)
	assert.Equal(t, int32(1), sendInviteFull.Page)
	assert.Equal(t, int32(10), sendInviteFull.PageSize)
	
	// 测试RES_REPLY_PROPOSAL_DETAIL
	replyProposalDetail := &dnfv1.RES_REPLY_PROPOSAL_DETAIL{
		Error:        0,
		Accepted:     true,
		ProposerId:   2001,
		ProposerName: "Proposer1",
		ProposalType: "team",
		Message:      "Proposal accepted",
	}
	assert.NotNil(t, replyProposalDetail)
	assert.Equal(t, int32(0), replyProposalDetail.Error)
	assert.True(t, replyProposalDetail.Accepted)
	assert.Equal(t, int64(2001), replyProposalDetail.ProposerId)
	assert.Equal(t, "Proposer1", replyProposalDetail.ProposerName)
	assert.Equal(t, "team", replyProposalDetail.ProposalType)
	assert.Equal(t, "Proposal accepted", replyProposalDetail.Message)
	
	// 测试RES_SET_PVP_CONTROL_MODE_FULL
	setPVPCFull := &dnfv1.RES_SET_PVP_CONTROL_MODE_FULL{
		Error:    0,
		Mode:     1,
		ModeName: "PVP Mode",
		Success:  true,
		Message:  "PVP mode set successfully",
	}
	assert.NotNil(t, setPVPCFull)
	assert.Equal(t, int32(0), setPVPCFull.Error)
	assert.Equal(t, int32(1), setPVPCFull.Mode)
	assert.Equal(t, "PVP Mode", setPVPCFull.ModeName)
	assert.True(t, setPVPCFull.Success)
	assert.Equal(t, "PVP mode set successfully", setPVPCFull.Message)
	
	// 测试RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL
	returnTownDetail := &dnfv1.RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL{
		Error:    0,
		TownName: "Test Town",
		TownId:   1,
		Success:  true,
		Message:  "Returned to town successfully",
	}
	assert.NotNil(t, returnTownDetail)
	assert.Equal(t, int32(0), returnTownDetail.Error)
	assert.Equal(t, "Test Town", returnTownDetail.TownName)
	assert.Equal(t, int32(1), returnTownDetail.TownId)
	assert.True(t, returnTownDetail.Success)
	assert.Equal(t, "Returned to town successfully", returnTownDetail.Message)
}

// testDungeonSystemsSupplement 测试副本系统补充
func testDungeonSystemsSupplement(t *testing.T) {
	// 测试RES_START_LOCKSTEP_ROOM_DETAIL
	startLockstepDetail := &dnfv1.RES_START_LOCKSTEP_ROOM_DETAIL{
		Error:          0,
		RoomId:         101,
		MaxPlayers:     4,
		CurrentPlayers: 2,
		Ready:          false,
		Countdown:      30,
	}
	assert.NotNil(t, startLockstepDetail)
	assert.Equal(t, int32(0), startLockstepDetail.Error)
	assert.Equal(t, int32(101), startLockstepDetail.RoomId)
	assert.Equal(t, int32(4), startLockstepDetail.MaxPlayers)
	assert.Equal(t, int32(2), startLockstepDetail.CurrentPlayers)
	assert.False(t, startLockstepDetail.Ready)
	assert.Equal(t, int32(30), startLockstepDetail.Countdown)
	
	// 测试RES_START_DUNGEON_PREPARE
	startDungeonPrepare := &dnfv1.RES_START_DUNGEON_PREPARE{
		Error:             0,
		DungeonId:         1001,
		DungeonName:       "Test Dungeon",
		RecommendedLevel:  50,
		PlayerCount:       4,
		Ready:             true,
		Countdown:         10,
	}
	assert.NotNil(t, startDungeonPrepare)
	assert.Equal(t, int32(0), startDungeonPrepare.Error)
	assert.Equal(t, int32(1001), startDungeonPrepare.DungeonId)
	assert.Equal(t, "Test Dungeon", startDungeonPrepare.DungeonName)
	assert.Equal(t, int32(50), startDungeonPrepare.RecommendedLevel)
	assert.Equal(t, int32(4), startDungeonPrepare.PlayerCount)
	assert.True(t, startDungeonPrepare.Ready)
	assert.Equal(t, int32(10), startDungeonPrepare.Countdown)
	
	// 测试RES_START_DUNGEON_COMPLETE_DETAIL
	startDungeonCompleteDetail := &dnfv1.RES_START_DUNGEON_COMPLETE_DETAIL{
		Error:         0,
		DungeonId:     1001,
		Success:       true,
		Rewards: []*dnfv1.RewardInfo{
			{
				ItemId:   101,
				ItemName: "Test Item",
				Count:    1,
				Rarity:   1,
			},
		},
		ClearTime:    300,
		PerfectClear: true,
		Rank:         1,
		RankName:     "S",
	}
	assert.NotNil(t, startDungeonCompleteDetail)
	assert.Equal(t, int32(0), startDungeonCompleteDetail.Error)
	assert.Equal(t, int32(1001), startDungeonCompleteDetail.DungeonId)
	assert.True(t, startDungeonCompleteDetail.Success)
	assert.Len(t, startDungeonCompleteDetail.Rewards, 1)
	assert.Equal(t, int32(300), startDungeonCompleteDetail.ClearTime)
	assert.True(t, startDungeonCompleteDetail.PerfectClear)
	assert.Equal(t, int32(1), startDungeonCompleteDetail.Rank)
	assert.Equal(t, "S", startDungeonCompleteDetail.RankName)
	
	// 测试RES_SYNC_DUNGEON_START_TIME_FULL
	syncTimeFull := &dnfv1.RES_SYNC_DUNGEON_START_TIME_FULL{
		Error:         0,
		StartTime:     1234567890,
		Duration:      3600,
		RemainingTime: 3000,
		TimeSync:      true,
		DungeonName:   "Test Dungeon",
	}
	assert.NotNil(t, syncTimeFull)
	assert.Equal(t, int32(0), syncTimeFull.Error)
	assert.Equal(t, int64(1234567890), syncTimeFull.StartTime)
	assert.Equal(t, int32(3600), syncTimeFull.Duration)
	assert.Equal(t, int32(3000), syncTimeFull.RemainingTime)
	assert.True(t, syncTimeFull.TimeSync)
	assert.Equal(t, "Test Dungeon", syncTimeFull.DungeonName)
	
	// 测试RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL
	updateConditionDetail := &dnfv1.RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL{
		Error:          0,
		ItemId:         101,
		ItemName:       "Test Item",
		Count:          1,
		RequiredCount:  1,
		Completed:      true,
		ConditionName:  "Collect Test Item",
	}
	assert.NotNil(t, updateConditionDetail)
	assert.Equal(t, int32(0), updateConditionDetail.Error)
	assert.Equal(t, int32(101), updateConditionDetail.ItemId)
	assert.Equal(t, "Test Item", updateConditionDetail.ItemName)
	assert.Equal(t, int32(1), updateConditionDetail.Count)
	assert.Equal(t, int32(1), updateConditionDetail.RequiredCount)
	assert.True(t, updateConditionDetail.Completed)
	assert.Equal(t, "Collect Test Item", updateConditionDetail.ConditionName)
}

// testSocialSystemsSupplement 测试社交系统补充
func testSocialSystemsSupplement(t *testing.T) {
	// 测试RES_REQUEST_FRIEND_INVITE_DETAIL
	requestFriendDetail := &dnfv1.RES_REQUEST_FRIEND_INVITE_DETAIL{
		Error:        0,
		FriendId:     1001,
		FriendName:   "Player1",
		FriendLevel:  50,
		FriendJob:    1,
		Online:       true,
		Status:       "online",
	}
	assert.NotNil(t, requestFriendDetail)
	assert.Equal(t, int32(0), requestFriendDetail.Error)
	assert.Equal(t, int64(1001), requestFriendDetail.FriendId)
	assert.Equal(t, "Player1", requestFriendDetail.FriendName)
	assert.Equal(t, int32(50), requestFriendDetail.FriendLevel)
	assert.Equal(t, int32(1), requestFriendDetail.FriendJob)
	assert.True(t, requestFriendDetail.Online)
	assert.Equal(t, "online", requestFriendDetail.Status)
	
	// 测试RES_REWARD_QUEST_FULL
	rewardQuestFull := &dnfv1.RES_REWARD_QUEST_FULL{
		Error:       0,
		QuestId:     101,
		QuestName:   "Test Quest",
		RewardId:    201,
		RewardName:  "Test Reward",
		RewardCount: 1,
		Success:     true,
		Message:     "Quest completed successfully",
	}
	assert.NotNil(t, rewardQuestFull)
	assert.Equal(t, int32(0), rewardQuestFull.Error)
	assert.Equal(t, int32(101), rewardQuestFull.QuestId)
	assert.Equal(t, "Test Quest", rewardQuestFull.QuestName)
	assert.Equal(t, int32(201), rewardQuestFull.RewardId)
	assert.Equal(t, "Test Reward", rewardQuestFull.RewardName)
	assert.Equal(t, int32(1), rewardQuestFull.RewardCount)
	assert.True(t, rewardQuestFull.Success)
	assert.Equal(t, "Quest completed successfully", rewardQuestFull.Message)
	
	// 测试RES_SET_APPENDAGE_MANNEQUIN_DETAIL
	setAppendageDetail := &dnfv1.RES_SET_APPENDAGE_MANNEQUIN_DETAIL{
		Error:         0,
		AppendageId:   1,
		AppendageName: "Test Appendage",
		MannequinId:   101,
		MannequinName: "Test Mannequin",
		Success:       true,
		Message:       "Appendage set successfully",
	}
	assert.NotNil(t, setAppendageDetail)
	assert.Equal(t, int32(0), setAppendageDetail.Error)
	assert.Equal(t, int32(1), setAppendageDetail.AppendageId)
	assert.Equal(t, "Test Appendage", setAppendageDetail.AppendageName)
	assert.Equal(t, int32(101), setAppendageDetail.MannequinId)
	assert.Equal(t, "Test Mannequin", setAppendageDetail.MannequinName)
	assert.True(t, setAppendageDetail.Success)
	assert.Equal(t, "Appendage set successfully", setAppendageDetail.Message)
	
	// 测试RES_SET_NAME_MANNEQUIN_FULL
	setNameFull := &dnfv1.RES_SET_NAME_MANNEQUIN_FULL{
		Error:        0,
		Name:         "New Mannequin Name",
		MannequinId:  101,
		MannequinName: "Test Mannequin",
		Success:      true,
		Message:      "Name set successfully",
		NameChanged:  true,
	}
	assert.NotNil(t, setNameFull)
	assert.Equal(t, int32(0), setNameFull.Error)
	assert.Equal(t, "New Mannequin Name", setNameFull.Name)
	assert.Equal(t, int32(101), setNameFull.MannequinId)
	assert.Equal(t, "Test Mannequin", setNameFull.MannequinName)
	assert.True(t, setNameFull.Success)
	assert.Equal(t, "Name set successfully", setNameFull.Message)
	assert.True(t, setNameFull.NameChanged)
	
	// 测试RES_TSS_DATA_COMPLETE
	tssDataComplete := &dnfv1.RES_TSS_DATA_COMPLETE{
		Error:      0,
		Data:       []byte{1, 2, 3},
		Timestamp:  1234567890,
		DataType:   "test",
		Encrypted:  false,
		Signature:  "test-signature",
		Version:    1,
	}
	assert.NotNil(t, tssDataComplete)
	assert.Equal(t, int32(0), tssDataComplete.Error)
	assert.Len(t, tssDataComplete.Data, 3)
	assert.Equal(t, int32(1234567890), tssDataComplete.Timestamp)
	assert.Equal(t, "test", tssDataComplete.DataType)
	assert.False(t, tssDataComplete.Encrypted)
	assert.Equal(t, "test-signature", tssDataComplete.Signature)
	assert.Equal(t, int32(1), tssDataComplete.Version)
}
