package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch73Migration 测试第73批迁移的消息类型
func TestBatch73Migration(t *testing.T) {
	t.Run("AdventureUnion", testBatch73AdventureUnionMessages)
	t.Run("Wedding", testBatch73WeddingMessages)
	t.Run("Divorce", testBatch73DivorceMessages)
	t.Run("Event", testBatch73EventMessages)
	t.Run("Room", testBatch73RoomMessages)
	t.Run("Equip", testBatch73EquipMessages)
	t.Run("Party", testBatch73PartyMessages)
	t.Run("Guild", testBatch73GuildMessages)
	t.Run("Friend", testBatch73FriendMessages)
	t.Run("BoardGame", testBatch73BoardGameMessages)
	t.Run("Community", testBatch73CommunityMessages)
	t.Run("Invite", testBatch73InviteMessages)
	t.Run("Dungeon", testBatch73DungeonMessages)
	t.Run("Minigame", testBatch73MinigameMessages)
	t.Run("Acquaintance", testBatch73AcquaintanceMessages)
	t.Run("Control", testBatch73ControlMessages)
	t.Run("Auction", testBatch73AuctionMessages)
	t.Run("Ngs", testBatch73NgsMessages)
	t.Run("Quest", testBatch73QuestMessages)
	t.Run("Wardrobe", testBatch73WardrobeMessages)
	t.Run("Ai", testBatch73AiMessages)
	t.Run("Tlog", testBatch73TlogMessages)
	t.Run("Gvoice", testBatch73GvoiceMessages)
	t.Run("Share", testBatch73ShareMessages)
}

// 测试冒险联盟相关消息
func testBatch73AdventureUnionMessages(t *testing.T) {
	resAdventureUnionExpeditionCancel := &dnfv1.ResAdventureUnionExpeditionCancel{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAdventureUnionExpeditionCancel)
	assert.Equal(t, int32(0), resAdventureUnionExpeditionCancel.Error)
	assert.Equal(t, int32(1), resAdventureUnionExpeditionCancel.Index)
}

// 测试婚礼相关消息
func testBatch73WeddingMessages(t *testing.T) {
	resLoadWeddingPreparation := &dnfv1.ResLoadWeddingPreparation{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLoadWeddingPreparation)
	assert.Equal(t, int32(0), resLoadWeddingPreparation.Error)
	assert.Equal(t, int32(1), resLoadWeddingPreparation.Index)
}

// 测试离婚相关消息
func testBatch73DivorceMessages(t *testing.T) {
	reqDivorce := &dnfv1.ReqDivorce{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDivorce)
	assert.Equal(t, int32(0), reqDivorce.Error)
	assert.Equal(t, int32(1), reqDivorce.Index)
}

// 测试事件相关消息
func testBatch73EventMessages(t *testing.T) {
	ptEventCondition := &dnfv1.PtEventCondition{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptEventCondition)
	assert.Equal(t, int32(0), ptEventCondition.Error)
	assert.Equal(t, int32(1), ptEventCondition.Index)

	resEventGetReward := &dnfv1.ResEventGetReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEventGetReward)
	assert.Equal(t, int32(0), resEventGetReward.Error)
	assert.Equal(t, int32(1), resEventGetReward.Index)
}

// 测试房间相关消息
func testBatch73RoomMessages(t *testing.T) {
	resLeaveRoom := &dnfv1.ResLeaveRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLeaveRoom)
	assert.Equal(t, int32(0), resLeaveRoom.Error)
	assert.Equal(t, int32(1), resLeaveRoom.Index)

	reqInviteRoom := &dnfv1.ReqInviteRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqInviteRoom)
	assert.Equal(t, int32(0), reqInviteRoom.Error)
	assert.Equal(t, int32(1), reqInviteRoom.Index)
}

// 测试装备相关消息
func testBatch73EquipMessages(t *testing.T) {
	ptEquip := &dnfv1.PtEquip{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptEquip)
	assert.Equal(t, int32(0), ptEquip.Error)
	assert.Equal(t, int32(1), ptEquip.Index)

	ptEquipped := &dnfv1.PtEquipped{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptEquipped)
	assert.Equal(t, int32(0), ptEquipped.Error)
	assert.Equal(t, int32(1), ptEquipped.Index)
}

// 测试队伍相关消息
func testBatch73PartyMessages(t *testing.T) {
	ptPartyDeathtowerObject := &dnfv1.PtPartyDeathtowerObject{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPartyDeathtowerObject)
	assert.Equal(t, int32(0), ptPartyDeathtowerObject.Error)
	assert.Equal(t, int32(1), ptPartyDeathtowerObject.Index)

	notifyPartyInvite := &dnfv1.NotifyPartyInvite{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyPartyInvite)
	assert.Equal(t, int32(0), notifyPartyInvite.Error)
	assert.Equal(t, int32(1), notifyPartyInvite.Index)

	ptPartyDungeonMap := &dnfv1.PtPartyDungeonMap{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPartyDungeonMap)
	assert.Equal(t, int32(0), ptPartyDungeonMap.Error)
	assert.Equal(t, int32(1), ptPartyDungeonMap.Index)

	reqPartyCancelInvitation := &dnfv1.ReqPartyCancelInvitation{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPartyCancelInvitation)
	assert.Equal(t, int32(0), reqPartyCancelInvitation.Error)
	assert.Equal(t, int32(1), reqPartyCancelInvitation.Index)

	reqPartiesLoad := &dnfv1.ReqPartiesLoad{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPartiesLoad)
	assert.Equal(t, int32(0), reqPartiesLoad.Error)
	assert.Equal(t, int32(1), reqPartiesLoad.Index)
}

// 测试公会相关消息
func testBatch73GuildMessages(t *testing.T) {
	resAlarmGuildBanish := &dnfv1.ResAlarmGuildBanish{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAlarmGuildBanish)
	assert.Equal(t, int32(0), resAlarmGuildBanish.Error)
	assert.Equal(t, int32(1), resAlarmGuildBanish.Index)

	ptGuildHistoricsiteRanking := &dnfv1.PtGuildHistoricsiteRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildHistoricsiteRanking)
	assert.Equal(t, int32(0), ptGuildHistoricsiteRanking.Error)
	assert.Equal(t, int32(1), ptGuildHistoricsiteRanking.Index)

	resGuildBingoSelectMap := &dnfv1.ResGuildBingoSelectMap{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildBingoSelectMap)
	assert.Equal(t, int32(0), resGuildBingoSelectMap.Error)
	assert.Equal(t, int32(1), resGuildBingoSelectMap.Index)

	resLoadGuildInfo := &dnfv1.ResLoadGuildInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLoadGuildInfo)
	assert.Equal(t, int32(0), resLoadGuildInfo.Error)
	assert.Equal(t, int32(1), resLoadGuildInfo.Index)

	ptGuildSymbol := &dnfv1.PtGuildSymbol{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildSymbol)
	assert.Equal(t, int32(0), ptGuildSymbol.Error)
	assert.Equal(t, int32(1), ptGuildSymbol.Index)

	resGuildPublicBuffUpgrade := &dnfv1.ResGuildPublicBuffUpgrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildPublicBuffUpgrade)
	assert.Equal(t, int32(0), resGuildPublicBuffUpgrade.Error)
	assert.Equal(t, int32(1), resGuildPublicBuffUpgrade.Index)

	reqGuildEmblemBuylistInfo := &dnfv1.ReqGuildEmblemBuylistInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildEmblemBuylistInfo)
	assert.Equal(t, int32(0), reqGuildEmblemBuylistInfo.Error)
	assert.Equal(t, int32(1), reqGuildEmblemBuylistInfo.Index)
}

// 测试好友相关消息
func testBatch73FriendMessages(t *testing.T) {
	reqFollowFriend := &dnfv1.ReqFollowFriend{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqFollowFriend)
	assert.Equal(t, int32(0), reqFollowFriend.Error)
	assert.Equal(t, int32(1), reqFollowFriend.Index)
}

// 测试棋盘游戏相关消息
func testBatch73BoardGameMessages(t *testing.T) {
	notifyBoardGameMessage := &dnfv1.NotifyBoardGameMessage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBoardGameMessage)
	assert.Equal(t, int32(0), notifyBoardGameMessage.Error)
	assert.Equal(t, int32(1), notifyBoardGameMessage.Index)
}

// 测试社区相关消息
func testBatch73CommunityMessages(t *testing.T) {
	reqCommunityGiftSendLogList := &dnfv1.ReqCommunityGiftSendLogList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCommunityGiftSendLogList)
	assert.Equal(t, int32(0), reqCommunityGiftSendLogList.Error)
	assert.Equal(t, int32(1), reqCommunityGiftSendLogList.Index)
}

// 测试邀请相关消息
func testBatch73InviteMessages(t *testing.T) {
	notifyPartyInvite := &dnfv1.NotifyPartyInvite{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyPartyInvite)
	assert.Equal(t, int32(0), notifyPartyInvite.Error)
	assert.Equal(t, int32(1), notifyPartyInvite.Index)

	reqInviteRoom := &dnfv1.ReqInviteRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqInviteRoom)
	assert.Equal(t, int32(0), reqInviteRoom.Error)
	assert.Equal(t, int32(1), reqInviteRoom.Index)
}

// 测试副本相关消息
func testBatch73DungeonMessages(t *testing.T) {
	ptPartyDungeonMap := &dnfv1.PtPartyDungeonMap{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPartyDungeonMap)
	assert.Equal(t, int32(0), ptPartyDungeonMap.Error)
	assert.Equal(t, int32(1), ptPartyDungeonMap.Index)

	resSelectOtherDungeonAtMulti := &dnfv1.ResSelectOtherDungeonAtMulti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSelectOtherDungeonAtMulti)
	assert.Equal(t, int32(0), resSelectOtherDungeonAtMulti.Error)
	assert.Equal(t, int32(1), resSelectOtherDungeonAtMulti.Index)
}

// 测试小游戏相关消息
func testBatch73MinigameMessages(t *testing.T) {
	ptMinigameAdventurerMakerInfo := &dnfv1.PtMinigameAdventurerMakerInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMinigameAdventurerMakerInfo)
	assert.Equal(t, int32(0), ptMinigameAdventurerMakerInfo.Error)
	assert.Equal(t, int32(1), ptMinigameAdventurerMakerInfo.Index)
}

// 测试熟人相关消息
func testBatch73AcquaintanceMessages(t *testing.T) {
	resAcquaintancePartyList := &dnfv1.ResAcquaintancePartyList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAcquaintancePartyList)
	assert.Equal(t, int32(0), resAcquaintancePartyList.Error)
	assert.Equal(t, int32(1), resAcquaintancePartyList.Index)
}

// 测试控制相关消息
func testBatch73ControlMessages(t *testing.T) {
	reqControlGroupQueryarea := &dnfv1.ReqControlGroupQueryarea{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqControlGroupQueryarea)
	assert.Equal(t, int32(0), reqControlGroupQueryarea.Error)
	assert.Equal(t, int32(1), reqControlGroupQueryarea.Index)

	reqSyncStageInfo := &dnfv1.ReqSyncStageInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqSyncStageInfo)
	assert.Equal(t, int32(0), reqSyncStageInfo.Error)
	assert.Equal(t, int32(1), reqSyncStageInfo.Index)
}

// 测试拍卖相关消息
func testBatch73AuctionMessages(t *testing.T) {
	resAuctionHistory := &dnfv1.ResAuctionHistory{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAuctionHistory)
	assert.Equal(t, int32(0), resAuctionHistory.Error)
	assert.Equal(t, int32(1), resAuctionHistory.Index)
}

// 测试NGS相关消息
func testBatch73NgsMessages(t *testing.T) {
	reqNgsVerify := &dnfv1.ReqNgsVerify{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqNgsVerify)
	assert.Equal(t, int32(0), reqNgsVerify.Error)
	assert.Equal(t, int32(1), reqNgsVerify.Index)
}

// 测试任务相关消息
func testBatch73QuestMessages(t *testing.T) {
	ptQuestInfo := &dnfv1.PtQuestInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptQuestInfo)
	assert.Equal(t, int32(0), ptQuestInfo.Error)
	assert.Equal(t, int32(1), ptQuestInfo.Index)

	resQuestInfo := &dnfv1.ResQuestInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resQuestInfo)
	assert.Equal(t, int32(0), resQuestInfo.Error)
	assert.Equal(t, int32(1), resQuestInfo.Index)
}

// 测试衣柜相关消息
func testBatch73WardrobeMessages(t *testing.T) {
	resWardrobeSetSlotMannequin := &dnfv1.ResWardrobeSetSlotMannequin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWardrobeSetSlotMannequin)
	assert.Equal(t, int32(0), resWardrobeSetSlotMannequin.Error)
	assert.Equal(t, int32(1), resWardrobeSetSlotMannequin.Index)
}

// 测试AI相关消息
func testBatch73AiMessages(t *testing.T) {
	reqAiCharacterInfo := &dnfv1.ReqAiCharacterInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAiCharacterInfo)
	assert.Equal(t, int32(0), reqAiCharacterInfo.Error)
	assert.Equal(t, int32(1), reqAiCharacterInfo.Index)
}

// 测试TLOG相关消息
func testBatch73TlogMessages(t *testing.T) {
	reqTlogWorldbossAttack := &dnfv1.ReqTlogWorldbossAttack{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqTlogWorldbossAttack)
	assert.Equal(t, int32(0), reqTlogWorldbossAttack.Error)
	assert.Equal(t, int32(1), reqTlogWorldbossAttack.Index)
}

// 测试GVoice相关消息
func testBatch73GvoiceMessages(t *testing.T) {
	resAddGvoiceMember := &dnfv1.ResAddGvoiceMember{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAddGvoiceMember)
	assert.Equal(t, int32(0), resAddGvoiceMember.Error)
	assert.Equal(t, int32(1), resAddGvoiceMember.Index)
}

// 测试分享相关消息
func testBatch73ShareMessages(t *testing.T) {
	reqShareBigimageAct := &dnfv1.ReqShareBigimageAct{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqShareBigimageAct)
	assert.Equal(t, int32(0), reqShareBigimageAct.Error)
	assert.Equal(t, int32(1), reqShareBigimageAct.Index)

	reqReleaseBookmark := &dnfv1.ReqReleaseBookmark{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqReleaseBookmark)
	assert.Equal(t, int32(0), reqReleaseBookmark.Error)
	assert.Equal(t, int32(1), reqReleaseBookmark.Index)
}
