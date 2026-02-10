package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch65Migration 测试批次65的迁移结果
func TestBatch65Migration(t *testing.T) {
	// 测试系统消息
	t.Run("SystemMessages", testBatch65SystemMessages)

	// 测试团队系统
	t.Run("TeamSystems", testBatch65TeamSystems)

	// 测试副本系统
	t.Run("DungeonSystems", testBatch65DungeonSystems)

	// 测试社交系统
	t.Run("SocialSystems", testBatch65SocialSystems)
}

// testBatch65SystemMessages 测试系统消息
func testBatch65SystemMessages(t *testing.T) {
	// 测试RES_SERVER_RESPONSE_PACKET_SUPPLEMENT
	resServerResponsePacketSupplement := &dnfv1.RES_SERVER_RESPONSE_PACKET_SUPPLEMENT{
		Error:   0,
		Message: "Success",
		Code:    200,
	}
	assert.NotNil(t, resServerResponsePacketSupplement)
	assert.Equal(t, int32(0), resServerResponsePacketSupplement.Error)
	assert.Equal(t, "Success", resServerResponsePacketSupplement.Message)
	assert.Equal(t, int32(200), resServerResponsePacketSupplement.Code)

	// 测试RES_SECEDE_GUILD_DETAIL
	resSecedeGuildDetail := &dnfv1.RES_SECEDE_GUILD_DETAIL{
		Error:     0,
		Message:   "Guild seceded successfully",
		GuildId:   1234567890,
		GuildName: "Test Guild",
	}
	assert.NotNil(t, resSecedeGuildDetail)
	assert.Equal(t, int32(0), resSecedeGuildDetail.Error)
	assert.Equal(t, "Guild seceded successfully", resSecedeGuildDetail.Message)
	assert.Equal(t, int64(1234567890), resSecedeGuildDetail.GuildId)
	assert.Equal(t, "Test Guild", resSecedeGuildDetail.GuildName)

	// 测试RES_SAVE_WEAK_SERVER_FULL_DATA
	resSaveWeakServerFullData := &dnfv1.RES_SAVE_WEAK_SERVER_FULL_DATA{
		Error:     0,
		Data:      []byte{1, 2, 3, 4, 5},
		Timestamp: 123456789,
		Signature: "test_signature",
	}
	assert.NotNil(t, resSaveWeakServerFullData)
	assert.Equal(t, int32(0), resSaveWeakServerFullData.Error)
	assert.Equal(t, []byte{1, 2, 3, 4, 5}, resSaveWeakServerFullData.Data)
	assert.Equal(t, int64(123456789), resSaveWeakServerFullData.Timestamp)
	assert.Equal(t, "test_signature", resSaveWeakServerFullData.Signature)

	// 测试RES_SEND_GUILD_REDPACKET_DETAIL
	resSendGuildRedpacketDetail := &dnfv1.RES_SEND_GUILD_REDPACKET_DETAIL{
		Error:            0,
		GuildId:          1234567890,
		RedpacketId:      "redpacket_123",
		Amount:           1000,
		SenderName:       "Test Sender",
		ParticipantCount: 10,
	}
	assert.NotNil(t, resSendGuildRedpacketDetail)
	assert.Equal(t, int32(0), resSendGuildRedpacketDetail.Error)
	assert.Equal(t, int64(1234567890), resSendGuildRedpacketDetail.GuildId)
	assert.Equal(t, "redpacket_123", resSendGuildRedpacketDetail.RedpacketId)
	assert.Equal(t, int32(1000), resSendGuildRedpacketDetail.Amount)
	assert.Equal(t, "Test Sender", resSendGuildRedpacketDetail.SenderName)
	assert.Equal(t, int32(10), resSendGuildRedpacketDetail.ParticipantCount)

	// 测试RES_SEND_STORAGE_COMPLETE
	resSendStorageComplete := &dnfv1.RES_SEND_STORAGE_COMPLETE{
		Error:     0,
		StorageId: 1,
		ItemCount: 10,
		Success:   true,
		Message:   "Storage sent successfully",
	}
	assert.NotNil(t, resSendStorageComplete)
	assert.Equal(t, int32(0), resSendStorageComplete.Error)
	assert.Equal(t, int32(1), resSendStorageComplete.StorageId)
	assert.Equal(t, int32(10), resSendStorageComplete.ItemCount)
	assert.True(t, resSendStorageComplete.Success)
	assert.Equal(t, "Storage sent successfully", resSendStorageComplete.Message)
}

// testBatch65TeamSystems 测试团队系统
func testBatch65TeamSystems(t *testing.T) {
	// 测试RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL
	resSelectOtherDungeonAtMultiDetail := &dnfv1.RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL{
		Error:            0,
		DungeonId:        100,
		DungeonName:      "Test Dungeon",
		Difficulty:       1,
		RecommendedLevel: 85,
		PlayerCount:      2,
	}
	assert.NotNil(t, resSelectOtherDungeonAtMultiDetail)
	assert.Equal(t, int32(0), resSelectOtherDungeonAtMultiDetail.Error)
	assert.Equal(t, int32(100), resSelectOtherDungeonAtMultiDetail.DungeonId)
	assert.Equal(t, "Test Dungeon", resSelectOtherDungeonAtMultiDetail.DungeonName)
	assert.Equal(t, int32(1), resSelectOtherDungeonAtMultiDetail.Difficulty)
	assert.Equal(t, int32(85), resSelectOtherDungeonAtMultiDetail.RecommendedLevel)
	assert.Equal(t, int32(2), resSelectOtherDungeonAtMultiDetail.PlayerCount)

	// 测试RES_SENDING_INVITE_FRIEND_LIST_FULL
	resSendingInviteFriendListFull := &dnfv1.RES_SENDING_INVITE_FRIEND_LIST_FULL{
		Error:      0,
		Invites:    []*dnfv1.FriendInviteInfo{},
		TotalCount: 100,
		Page:       1,
		PageSize:   20,
	}
	assert.NotNil(t, resSendingInviteFriendListFull)
	assert.Equal(t, int32(0), resSendingInviteFriendListFull.Error)
	assert.Equal(t, int32(100), resSendingInviteFriendListFull.TotalCount)
	assert.Equal(t, int32(1), resSendingInviteFriendListFull.Page)
	assert.Equal(t, int32(20), resSendingInviteFriendListFull.PageSize)

	// 测试RES_REPLY_PROPOSAL_DETAIL
	resReplyProposalDetail := &dnfv1.RES_REPLY_PROPOSAL_DETAIL{
		Error:        0,
		Accepted:     true,
		ProposerId:   1234567891,
		ProposerName: "Test User",
		ProposalType: "Party",
		Message:      "Proposal accepted",
	}
	assert.NotNil(t, resReplyProposalDetail)
	assert.Equal(t, int32(0), resReplyProposalDetail.Error)
	assert.True(t, resReplyProposalDetail.Accepted)
	assert.Equal(t, int64(1234567891), resReplyProposalDetail.ProposerId)
	assert.Equal(t, "Test User", resReplyProposalDetail.ProposerName)
	assert.Equal(t, "Party", resReplyProposalDetail.ProposalType)
	assert.Equal(t, "Proposal accepted", resReplyProposalDetail.Message)

	// 测试RES_SET_PVP_CONTROL_MODE_FULL
	resSetPvpControlModeFull := &dnfv1.RES_SET_PVP_CONTROL_MODE_FULL{
		Error:    0,
		Mode:     1,
		ModeName: "Normal",
		Success:  true,
		Message:  "PVP control mode set successfully",
	}
	assert.NotNil(t, resSetPvpControlModeFull)
	assert.Equal(t, int32(0), resSetPvpControlModeFull.Error)
	assert.Equal(t, int32(1), resSetPvpControlModeFull.Mode)
	assert.Equal(t, "Normal", resSetPvpControlModeFull.ModeName)
	assert.True(t, resSetPvpControlModeFull.Success)
	assert.Equal(t, "PVP control mode set successfully", resSetPvpControlModeFull.Message)

	// 测试RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL
	resReturnToTownAtMultiPlayDetail := &dnfv1.RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL{
		Error:    0,
		TownName: "Test Town",
		TownId:   1,
		Success:  true,
		Message:  "Returned to town successfully",
	}
	assert.NotNil(t, resReturnToTownAtMultiPlayDetail)
	assert.Equal(t, int32(0), resReturnToTownAtMultiPlayDetail.Error)
	assert.Equal(t, "Test Town", resReturnToTownAtMultiPlayDetail.TownName)
	assert.Equal(t, int32(1), resReturnToTownAtMultiPlayDetail.TownId)
	assert.True(t, resReturnToTownAtMultiPlayDetail.Success)
	assert.Equal(t, "Returned to town successfully", resReturnToTownAtMultiPlayDetail.Message)
}

// testBatch65DungeonSystems 测试副本系统
func testBatch65DungeonSystems(t *testing.T) {
	// 测试RES_START_LOCKSTEP_ROOM_DETAIL
	resStartLockstepRoomDetail := &dnfv1.RES_START_LOCKSTEP_ROOM_DETAIL{
		Error:          0,
		RoomId:         1234567890,
		MaxPlayers:     4,
		CurrentPlayers: 2,
		Ready:          true,
		Countdown:      10,
	}
	assert.NotNil(t, resStartLockstepRoomDetail)
	assert.Equal(t, int32(0), resStartLockstepRoomDetail.Error)
	assert.Equal(t, int32(1234567890), resStartLockstepRoomDetail.RoomId)
	assert.Equal(t, int32(4), resStartLockstepRoomDetail.MaxPlayers)
	assert.Equal(t, int32(2), resStartLockstepRoomDetail.CurrentPlayers)
	assert.True(t, resStartLockstepRoomDetail.Ready)
	assert.Equal(t, int32(10), resStartLockstepRoomDetail.Countdown)

	// 测试RES_START_DUNGEON_PREPARE
	resStartDungeonPrepare := &dnfv1.RES_START_DUNGEON_PREPARE{
		Error:            0,
		DungeonId:        100,
		DungeonName:      "Test Dungeon",
		RecommendedLevel: 85,
		PlayerCount:      4,
		Ready:            true,
		Countdown:        10,
	}
	assert.NotNil(t, resStartDungeonPrepare)
	assert.Equal(t, int32(0), resStartDungeonPrepare.Error)
	assert.Equal(t, int32(100), resStartDungeonPrepare.DungeonId)
	assert.Equal(t, "Test Dungeon", resStartDungeonPrepare.DungeonName)
	assert.Equal(t, int32(85), resStartDungeonPrepare.RecommendedLevel)
	assert.Equal(t, int32(4), resStartDungeonPrepare.PlayerCount)
	assert.True(t, resStartDungeonPrepare.Ready)
	assert.Equal(t, int32(10), resStartDungeonPrepare.Countdown)

	// 测试RES_START_DUNGEON_COMPLETE_DETAIL
	resStartDungeonCompleteDetail := &dnfv1.RES_START_DUNGEON_COMPLETE_DETAIL{
		Error:        0,
		DungeonId:    100,
		Success:      true,
		Rewards:      []*dnfv1.RewardInfo{},
		ClearTime:    300,
		PerfectClear: true,
		Rank:         1,
		RankName:     "S",
	}
	assert.NotNil(t, resStartDungeonCompleteDetail)
	assert.Equal(t, int32(0), resStartDungeonCompleteDetail.Error)
	assert.Equal(t, int32(100), resStartDungeonCompleteDetail.DungeonId)
	assert.True(t, resStartDungeonCompleteDetail.Success)
	assert.Equal(t, int32(300), resStartDungeonCompleteDetail.ClearTime)
	assert.True(t, resStartDungeonCompleteDetail.PerfectClear)
	assert.Equal(t, int32(1), resStartDungeonCompleteDetail.Rank)
	assert.Equal(t, "S", resStartDungeonCompleteDetail.RankName)

	// 测试RES_SYNC_DUNGEON_START_TIME_FULL
	resSyncDungeonStartTimeFull := &dnfv1.RES_SYNC_DUNGEON_START_TIME_FULL{
		Error:         0,
		StartTime:     1234567890000,
		Duration:      300,
		RemainingTime: 150,
		TimeSync:      true,
		DungeonName:   "Test Dungeon",
	}
	assert.NotNil(t, resSyncDungeonStartTimeFull)
	assert.Equal(t, int32(0), resSyncDungeonStartTimeFull.Error)
	assert.Equal(t, int64(1234567890000), resSyncDungeonStartTimeFull.StartTime)
	assert.Equal(t, int32(300), resSyncDungeonStartTimeFull.Duration)
	assert.Equal(t, int32(150), resSyncDungeonStartTimeFull.RemainingTime)
	assert.True(t, resSyncDungeonStartTimeFull.TimeSync)
	assert.Equal(t, "Test Dungeon", resSyncDungeonStartTimeFull.DungeonName)

	// 测试RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL
	resUpdateDungeonearclearConditionGetItemDetail := &dnfv1.RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL{
		Error:         0,
		ItemId:        1000,
		ItemName:      "Test Item",
		Count:         10,
		RequiredCount: 20,
		Completed:     false,
		ConditionName: "Collect Items",
	}
	assert.NotNil(t, resUpdateDungeonearclearConditionGetItemDetail)
	assert.Equal(t, int32(0), resUpdateDungeonearclearConditionGetItemDetail.Error)
	assert.Equal(t, int32(1000), resUpdateDungeonearclearConditionGetItemDetail.ItemId)
	assert.Equal(t, "Test Item", resUpdateDungeonearclearConditionGetItemDetail.ItemName)
	assert.Equal(t, int32(10), resUpdateDungeonearclearConditionGetItemDetail.Count)
	assert.Equal(t, int32(20), resUpdateDungeonearclearConditionGetItemDetail.RequiredCount)
	assert.False(t, resUpdateDungeonearclearConditionGetItemDetail.Completed)
	assert.Equal(t, "Collect Items", resUpdateDungeonearclearConditionGetItemDetail.ConditionName)
}

// testBatch65SocialSystems 测试社交系统
func testBatch65SocialSystems(t *testing.T) {
	// 测试RES_REQUEST_FRIEND_INVITE_DETAIL
	resRequestFriendInviteDetail := &dnfv1.RES_REQUEST_FRIEND_INVITE_DETAIL{
		Error:       0,
		FriendId:    1234567890,
		FriendName:  "Test Friend",
		FriendLevel: 85,
		FriendJob:   5,
		Online:      true,
		Status:      "Available",
	}
	assert.NotNil(t, resRequestFriendInviteDetail)
	assert.Equal(t, int32(0), resRequestFriendInviteDetail.Error)
	assert.Equal(t, int64(1234567890), resRequestFriendInviteDetail.FriendId)
	assert.Equal(t, "Test Friend", resRequestFriendInviteDetail.FriendName)
	assert.Equal(t, int32(85), resRequestFriendInviteDetail.FriendLevel)
	assert.Equal(t, int32(5), resRequestFriendInviteDetail.FriendJob)
	assert.True(t, resRequestFriendInviteDetail.Online)
	assert.Equal(t, "Available", resRequestFriendInviteDetail.Status)

	// 测试RES_REWARD_QUEST_FULL
	resRewardQuestFull := &dnfv1.RES_REWARD_QUEST_FULL{
		Error:       0,
		QuestId:     1,
		QuestName:   "Test Quest",
		RewardId:    100,
		RewardName:  "Test Reward",
		RewardCount: 10,
		Success:     true,
		Message:     "Quest rewarded successfully",
	}
	assert.NotNil(t, resRewardQuestFull)
	assert.Equal(t, int32(0), resRewardQuestFull.Error)
	assert.Equal(t, int32(1), resRewardQuestFull.QuestId)
	assert.Equal(t, "Test Quest", resRewardQuestFull.QuestName)
	assert.Equal(t, int32(100), resRewardQuestFull.RewardId)
	assert.Equal(t, "Test Reward", resRewardQuestFull.RewardName)
	assert.Equal(t, int32(10), resRewardQuestFull.RewardCount)
	assert.True(t, resRewardQuestFull.Success)
	assert.Equal(t, "Quest rewarded successfully", resRewardQuestFull.Message)

	// 测试RES_SET_APPENDAGE_MANNEQUIN_DETAIL
	resSetAppendageMannequinDetail := &dnfv1.RES_SET_APPENDAGE_MANNEQUIN_DETAIL{
		Error:         0,
		AppendageId:   1,
		AppendageName: "Test Appendage",
		MannequinId:   2,
		MannequinName: "Test Mannequin",
		Success:       true,
		Message:       "Appendage mannequin set successfully",
	}
	assert.NotNil(t, resSetAppendageMannequinDetail)
	assert.Equal(t, int32(0), resSetAppendageMannequinDetail.Error)
	assert.Equal(t, int32(1), resSetAppendageMannequinDetail.AppendageId)
	assert.Equal(t, "Test Appendage", resSetAppendageMannequinDetail.AppendageName)
	assert.Equal(t, int32(2), resSetAppendageMannequinDetail.MannequinId)
	assert.Equal(t, "Test Mannequin", resSetAppendageMannequinDetail.MannequinName)
	assert.True(t, resSetAppendageMannequinDetail.Success)
	assert.Equal(t, "Appendage mannequin set successfully", resSetAppendageMannequinDetail.Message)

	// 测试RES_SET_NAME_MANNEQUIN_FULL
	resSetNameMannequinFull := &dnfv1.RES_SET_NAME_MANNEQUIN_FULL{
		Error:         0,
		Name:          "Test Name",
		MannequinId:   1,
		MannequinName: "Test Mannequin",
		Success:       true,
		Message:       "Mannequin name set successfully",
		NameChanged:   true,
	}
	assert.NotNil(t, resSetNameMannequinFull)
	assert.Equal(t, int32(0), resSetNameMannequinFull.Error)
	assert.Equal(t, "Test Name", resSetNameMannequinFull.Name)
	assert.Equal(t, int32(1), resSetNameMannequinFull.MannequinId)
	assert.Equal(t, "Test Mannequin", resSetNameMannequinFull.MannequinName)
	assert.True(t, resSetNameMannequinFull.Success)
	assert.Equal(t, "Mannequin name set successfully", resSetNameMannequinFull.Message)
	assert.True(t, resSetNameMannequinFull.NameChanged)

	// 测试RES_TSS_DATA_COMPLETE
	resTssDataComplete := &dnfv1.RES_TSS_DATA_COMPLETE{
		Error:     0,
		Data:      []byte{1, 2, 3, 4, 5},
		Timestamp: 123456789,
		DataType:  "test_data",
		Encrypted: true,
		Signature: "test_signature",
		Version:   1,
	}
	assert.NotNil(t, resTssDataComplete)
	assert.Equal(t, int32(0), resTssDataComplete.Error)
	assert.Equal(t, []byte{1, 2, 3, 4, 5}, resTssDataComplete.Data)
	assert.Equal(t, int32(123456789), resTssDataComplete.Timestamp)
	assert.Equal(t, "test_data", resTssDataComplete.DataType)
	assert.True(t, resTssDataComplete.Encrypted)
	assert.Equal(t, "test_signature", resTssDataComplete.Signature)
	assert.Equal(t, int32(1), resTssDataComplete.Version)
}
