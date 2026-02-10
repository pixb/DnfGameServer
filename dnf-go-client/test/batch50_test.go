package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch50Migration 测试批次50的迁移结果
func TestBatch50Migration(t *testing.T) {
	// 测试系统消息补充
	t.Run("SystemMessagesSupplement", testBatch50SystemMessagesSupplement)
	
	// 测试团队系统补充
	t.Run("TeamSystemsSupplement", testBatch50TeamSystemsSupplement)
	
	// 测试副本系统补充
	t.Run("DungeonSystemsSupplement", testBatch50DungeonSystemsSupplement)
	
	// 测试社交系统补充
	t.Run("SocialSystemsSupplement", testBatch50SocialSystemsSupplement)
}

// testBatch50SystemMessagesSupplement 测试系统消息补充
func testBatch50SystemMessagesSupplement(t *testing.T) {
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
		GuildId:   12345,
		GuildName: "Test Guild",
	}
	assert.NotNil(t, secedeGuildDetail)
	assert.Equal(t, int32(0), secedeGuildDetail.Error)
	assert.Equal(t, int64(12345), secedeGuildDetail.GuildId)
	assert.Equal(t, "Test Guild", secedeGuildDetail.GuildName)
	
	// 测试RES_SAVE_WEAK_SERVER_FULL_DATA
	saveWeakServerFullData := &dnfv1.RES_SAVE_WEAK_SERVER_FULL_DATA{
		Error:     0,
		Data:      []byte("test data"),
		Timestamp: 1234567890,
		Signature: "test signature",
	}
	assert.NotNil(t, saveWeakServerFullData)
	assert.Equal(t, int32(0), saveWeakServerFullData.Error)
	assert.Equal(t, []byte("test data"), saveWeakServerFullData.Data)
	assert.Equal(t, int64(1234567890), saveWeakServerFullData.Timestamp)
	assert.Equal(t, "test signature", saveWeakServerFullData.Signature)
	
	// 测试RES_SEND_GUILD_REDPACKET_DETAIL
	sendGuildRedpacketDetail := &dnfv1.RES_SEND_GUILD_REDPACKET_DETAIL{
		Error:      0,
		GuildId:    12345,
		RedpacketId: "123456",
		Amount:     1000,
		SenderName: "Test Sender",
	}
	assert.NotNil(t, sendGuildRedpacketDetail)
	assert.Equal(t, int32(0), sendGuildRedpacketDetail.Error)
	assert.Equal(t, int64(12345), sendGuildRedpacketDetail.GuildId)
	assert.Equal(t, "123456", sendGuildRedpacketDetail.RedpacketId)
	assert.Equal(t, int32(1000), sendGuildRedpacketDetail.Amount)
	assert.Equal(t, "Test Sender", sendGuildRedpacketDetail.SenderName)
	
	// 测试RES_SEND_STORAGE_COMPLETE
	sendStorageComplete := &dnfv1.RES_SEND_STORAGE_COMPLETE{
		Error:      0,
		StorageId:  100,
		ItemCount:  20,
		Success:    true,
		Message:    "Storage operation completed",
	}
	assert.NotNil(t, sendStorageComplete)
	assert.Equal(t, int32(0), sendStorageComplete.Error)
	assert.Equal(t, int32(100), sendStorageComplete.StorageId)
	assert.Equal(t, int32(20), sendStorageComplete.ItemCount)
	assert.True(t, sendStorageComplete.Success)
	assert.Equal(t, "Storage operation completed", sendStorageComplete.Message)
}

// testBatch50TeamSystemsSupplement 测试团队系统补充
func testBatch50TeamSystemsSupplement(t *testing.T) {
	// 测试RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL
	selectOtherDungeonAtMultiDetail := &dnfv1.RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL{
		Error:     0,
		DungeonId: 100,
	}
	assert.NotNil(t, selectOtherDungeonAtMultiDetail)
	assert.Equal(t, int32(0), selectOtherDungeonAtMultiDetail.Error)
	assert.Equal(t, int32(100), selectOtherDungeonAtMultiDetail.DungeonId)
	
	// 测试RES_SENDING_INVITE_FRIEND_LIST_FULL
	sendingInviteFriendListFull := &dnfv1.RES_SENDING_INVITE_FRIEND_LIST_FULL{
		Error: 0,
	}
	assert.NotNil(t, sendingInviteFriendListFull)
	assert.Equal(t, int32(0), sendingInviteFriendListFull.Error)
	
	// 测试RES_REPLY_PROPOSAL_DETAIL
	replyProposalDetail := &dnfv1.RES_REPLY_PROPOSAL_DETAIL{
		Error:    0,
		Accepted: true,
	}
	assert.NotNil(t, replyProposalDetail)
	assert.Equal(t, int32(0), replyProposalDetail.Error)
	assert.True(t, replyProposalDetail.Accepted)
	
	// 测试RES_SET_PVP_CONTROL_MODE_FULL
	setPvpControlModeFull := &dnfv1.RES_SET_PVP_CONTROL_MODE_FULL{
		Error: 0,
		Mode:  1,
	}
	assert.NotNil(t, setPvpControlModeFull)
	assert.Equal(t, int32(0), setPvpControlModeFull.Error)
	assert.Equal(t, int32(1), setPvpControlModeFull.Mode)
	
	// 测试RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL
	returnToTownAtMultiPlayDetail := &dnfv1.RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL{
		Error:  0,
		TownId: 10,
	}
	assert.NotNil(t, returnToTownAtMultiPlayDetail)
	assert.Equal(t, int32(0), returnToTownAtMultiPlayDetail.Error)
	assert.Equal(t, int32(10), returnToTownAtMultiPlayDetail.TownId)
}

// testBatch50DungeonSystemsSupplement 测试副本系统补充
func testBatch50DungeonSystemsSupplement(t *testing.T) {
	// 测试RES_START_LOCKSTEP_ROOM_DETAIL
	startLockstepRoomDetail := &dnfv1.RES_START_LOCKSTEP_ROOM_DETAIL{
		Error:  0,
		RoomId: 12345,
	}
	assert.NotNil(t, startLockstepRoomDetail)
	assert.Equal(t, int32(0), startLockstepRoomDetail.Error)
	assert.Equal(t, int32(12345), startLockstepRoomDetail.RoomId)
	
	// 测试RES_START_DUNGEON_PREPARE
	startDungeonPrepare := &dnfv1.RES_START_DUNGEON_PREPARE{
		Error:     0,
		DungeonId: 100,
	}
	assert.NotNil(t, startDungeonPrepare)
	assert.Equal(t, int32(0), startDungeonPrepare.Error)
	assert.Equal(t, int32(100), startDungeonPrepare.DungeonId)
	
	// 测试RES_START_DUNGEON_COMPLETE_DETAIL
	startDungeonCompleteDetail := &dnfv1.RES_START_DUNGEON_COMPLETE_DETAIL{
		Error:     0,
		DungeonId: 100,
		Success:   true,
	}
	assert.NotNil(t, startDungeonCompleteDetail)
	assert.Equal(t, int32(0), startDungeonCompleteDetail.Error)
	assert.Equal(t, int32(100), startDungeonCompleteDetail.DungeonId)
	assert.True(t, startDungeonCompleteDetail.Success)
	
	// 测试RES_SYNC_DUNGEON_START_TIME_FULL
	syncDungeonStartTimeFull := &dnfv1.RES_SYNC_DUNGEON_START_TIME_FULL{
		Error:     0,
		StartTime: 1234567890,
	}
	assert.NotNil(t, syncDungeonStartTimeFull)
	assert.Equal(t, int32(0), syncDungeonStartTimeFull.Error)
	assert.Equal(t, int64(1234567890), syncDungeonStartTimeFull.StartTime)
	
	// 测试RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL
	updateDungeonclearConditionGetItemDetail := &dnfv1.RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL{
		Error:   0,
		ItemId:  1000,
		Count:   5,
	}
	assert.NotNil(t, updateDungeonclearConditionGetItemDetail)
	assert.Equal(t, int32(0), updateDungeonclearConditionGetItemDetail.Error)
	assert.Equal(t, int32(1000), updateDungeonclearConditionGetItemDetail.ItemId)
	assert.Equal(t, int32(5), updateDungeonclearConditionGetItemDetail.Count)
}

// testBatch50SocialSystemsSupplement 测试社交系统补充
func testBatch50SocialSystemsSupplement(t *testing.T) {
	// 测试RES_REQUEST_FRIEND_INVITE_DETAIL
	requestFriendInviteDetail := &dnfv1.RES_REQUEST_FRIEND_INVITE_DETAIL{
		Error:   0,
		FriendId: 12345,
	}
	assert.NotNil(t, requestFriendInviteDetail)
	assert.Equal(t, int32(0), requestFriendInviteDetail.Error)
	assert.Equal(t, int64(12345), requestFriendInviteDetail.FriendId)
	
	// 测试RES_REWARD_QUEST_FULL
	rewardQuestFull := &dnfv1.RES_REWARD_QUEST_FULL{
		Error:   0,
		QuestId: 1000,
		RewardId: 2000,
	}
	assert.NotNil(t, rewardQuestFull)
	assert.Equal(t, int32(0), rewardQuestFull.Error)
	assert.Equal(t, int32(1000), rewardQuestFull.QuestId)
	assert.Equal(t, int32(2000), rewardQuestFull.RewardId)
	
	// 测试RES_SET_APPENDAGE_MANNEQUIN_DETAIL
	setAppendageMannequinDetail := &dnfv1.RES_SET_APPENDAGE_MANNEQUIN_DETAIL{
		Error: 0,
	}
	assert.NotNil(t, setAppendageMannequinDetail)
	assert.Equal(t, int32(0), setAppendageMannequinDetail.Error)
	
	// 测试RES_SET_NAME_MANNEQUIN_FULL
	setNameMannequinFull := &dnfv1.RES_SET_NAME_MANNEQUIN_FULL{
		Error: 0,
		Name:  "Test Mannequin",
	}
	assert.NotNil(t, setNameMannequinFull)
	assert.Equal(t, int32(0), setNameMannequinFull.Error)
	assert.Equal(t, "Test Mannequin", setNameMannequinFull.Name)
	
	// 测试RES_TSS_DATA_COMPLETE
	tssDataComplete := &dnfv1.RES_TSS_DATA_COMPLETE{
		Error: 0,
	}
	assert.NotNil(t, tssDataComplete)
	assert.Equal(t, int32(0), tssDataComplete.Error)
}
