package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch77Migration 测试第77批迁移的消息类型
func TestBatch77Migration(t *testing.T) {
	t.Run("AttackSquad", testBatch77AttackSquadMessages)
	t.Run("Creature", testBatch77CreatureMessages)
	t.Run("Guild", testBatch77GuildMessages)
	t.Run("Wedding", testBatch77WeddingMessages)
	t.Run("Adventurebook", testBatch77AdventurebookMessages)
	t.Run("HiddenChatting", testBatch77HiddenChattingMessages)
	t.Run("Wardrobe", testBatch77WardrobeMessages)
	t.Run("Minigame", testBatch77MinigameMessages)
	t.Run("Welfare", testBatch77WelfareMessages)
	t.Run("Dungeon", testBatch77DungeonMessages)
	t.Run("Verification", testBatch77VerificationMessages)
	t.Run("BoardGame", testBatch77BoardGameMessages)
	t.Run("Friendly", testBatch77FriendlyMessages)
	t.Run("Battleleague", testBatch77BattleleagueMessages)
	t.Run("Achievement", testBatch77AchievementMessages)
	t.Run("Session", testBatch77SessionMessages)
	t.Run("Room", testBatch77RoomMessages)
	t.Run("Contents", testBatch77ContentsMessages)
	t.Run("Pvp", testBatch77PvpMessages)
	t.Run("Friend", testBatch77FriendMessages)
	t.Run("MultiPlay", testBatch77MultiPlayMessages)
	t.Run("Raid", testBatch77RaidMessages)
	t.Run("Town", testBatch77TownMessages)
	t.Run("Platform", testBatch77PlatformMessages)
	t.Run("Stream", testBatch77StreamMessages)
}

// 测试攻击小队相关消息
func testBatch77AttackSquadMessages(t *testing.T) {
	resCancelAttackSquadJoin := &dnfv1.ResCancelAttackSquadJoin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCancelAttackSquadJoin)
	assert.Equal(t, int32(0), resCancelAttackSquadJoin.Error)
	assert.Equal(t, int32(1), resCancelAttackSquadJoin.Index)

	resAttackSquadMemberSlotChange := &dnfv1.ResAttackSquadMemberSlotChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAttackSquadMemberSlotChange)
	assert.Equal(t, int32(0), resAttackSquadMemberSlotChange.Error)
	assert.Equal(t, int32(1), resAttackSquadMemberSlotChange.Index)

	reqAttackSquadSendInvite := &dnfv1.ReqAttackSquadSendInvite{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadSendInvite)
	assert.Equal(t, int32(0), reqAttackSquadSendInvite.Error)
	assert.Equal(t, int32(1), reqAttackSquadSendInvite.Index)
}

// 测试生物相关消息
func testBatch77CreatureMessages(t *testing.T) {
	resCreatureEvolve := &dnfv1.ResCreatureEvolve{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureEvolve)
	assert.Equal(t, int32(0), resCreatureEvolve.Error)
	assert.Equal(t, int32(1), resCreatureEvolve.Index)

	reqCreatureFeeding := &dnfv1.ReqCreatureFeeding{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCreatureFeeding)
	assert.Equal(t, int32(0), reqCreatureFeeding.Error)
	assert.Equal(t, int32(1), reqCreatureFeeding.Index)
}

// 测试公会相关消息
func testBatch77GuildMessages(t *testing.T) {
	enumGuildBingoItemType := &dnfv1.EnumGuildBingoItemType{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumGuildBingoItemType)
	assert.Equal(t, int32(0), enumGuildBingoItemType.Error)
	assert.Equal(t, int32(1), enumGuildBingoItemType.Index)

	resGuildGuardianDungeonInfo := &dnfv1.ResGuildGuardianDungeonInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildGuardianDungeonInfo)
	assert.Equal(t, int32(0), resGuildGuardianDungeonInfo.Error)
	assert.Equal(t, int32(1), resGuildGuardianDungeonInfo.Index)

	resGuildNewDiningBuyInfo := &dnfv1.ResGuildNewDiningBuyInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildNewDiningBuyInfo)
	assert.Equal(t, int32(0), resGuildNewDiningBuyInfo.Error)
	assert.Equal(t, int32(1), resGuildNewDiningBuyInfo.Index)

	ptGuildWarDetail := &dnfv1.PtGuildWarDetail{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildWarDetail)
	assert.Equal(t, int32(0), ptGuildWarDetail.Error)
	assert.Equal(t, int32(1), ptGuildWarDetail.Index)

	ptGuildAuctionRewardInfo := &dnfv1.PtGuildAuctionRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildAuctionRewardInfo)
	assert.Equal(t, int32(0), ptGuildAuctionRewardInfo.Error)
	assert.Equal(t, int32(1), ptGuildAuctionRewardInfo.Index)
}

// 测试婚礼相关消息
func testBatch77WeddingMessages(t *testing.T) {
	notifyWeddingEffect := &dnfv1.NotifyWeddingEffect{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyWeddingEffect)
	assert.Equal(t, int32(0), notifyWeddingEffect.Error)
	assert.Equal(t, int32(1), notifyWeddingEffect.Index)
}

// 测试冒险书相关消息
func testBatch77AdventurebookMessages(t *testing.T) {
	reqAdventurebookSpecialReward := &dnfv1.ReqAdventurebookSpecialReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventurebookSpecialReward)
	assert.Equal(t, int32(0), reqAdventurebookSpecialReward.Error)
	assert.Equal(t, int32(1), reqAdventurebookSpecialReward.Index)

	ptAdventurebookInfo := &dnfv1.PtAdventurebookInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAdventurebookInfo)
	assert.Equal(t, int32(0), ptAdventurebookInfo.Error)
	assert.Equal(t, int32(1), ptAdventurebookInfo.Index)
}

// 测试隐藏聊天相关消息
func testBatch77HiddenChattingMessages(t *testing.T) {
	resHiddenChattingDelete := &dnfv1.ResHiddenChattingDelete{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHiddenChattingDelete)
	assert.Equal(t, int32(0), resHiddenChattingDelete.Error)
	assert.Equal(t, int32(1), resHiddenChattingDelete.Index)
}

// 测试衣柜相关消息
func testBatch77WardrobeMessages(t *testing.T) {
	reqWardrobeInfo := &dnfv1.ReqWardrobeInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqWardrobeInfo)
	assert.Equal(t, int32(0), reqWardrobeInfo.Error)
	assert.Equal(t, int32(1), reqWardrobeInfo.Index)
}

// 测试小游戏相关消息
func testBatch77MinigameMessages(t *testing.T) {
	reqMinigameClearLotusQuiz := &dnfv1.ReqMinigameClearLotusQuiz{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigameClearLotusQuiz)
	assert.Equal(t, int32(0), reqMinigameClearLotusQuiz.Error)
	assert.Equal(t, int32(1), reqMinigameClearLotusQuiz.Index)

	resMinigameMiniPongClear := &dnfv1.ResMinigameMiniPongClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameMiniPongClear)
	assert.Equal(t, int32(0), resMinigameMiniPongClear.Error)
	assert.Equal(t, int32(1), resMinigameMiniPongClear.Index)

	resMinigameSpecialClassLoad := &dnfv1.ResMinigameSpecialClassLoad{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameSpecialClassLoad)
	assert.Equal(t, int32(0), resMinigameSpecialClassLoad.Error)
	assert.Equal(t, int32(1), resMinigameSpecialClassLoad.Index)
}

// 测试福利相关消息
func testBatch77WelfareMessages(t *testing.T) {
	ptWelfareFindRewardInfo := &dnfv1.PtWelfareFindRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptWelfareFindRewardInfo)
	assert.Equal(t, int32(0), ptWelfareFindRewardInfo.Error)
	assert.Equal(t, int32(1), ptWelfareFindRewardInfo.Index)
}

// 测试副本相关消息
func testBatch77DungeonMessages(t *testing.T) {
	reqDungeonResult := &dnfv1.ReqDungeonResult{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDungeonResult)
	assert.Equal(t, int32(0), reqDungeonResult.Error)
	assert.Equal(t, int32(1), reqDungeonResult.Index)
}

// 测试验证相关消息
func testBatch77VerificationMessages(t *testing.T) {
	reqVerificationBySyncDifference := &dnfv1.ReqVerificationBySyncDifference{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqVerificationBySyncDifference)
	assert.Equal(t, int32(0), reqVerificationBySyncDifference.Error)
	assert.Equal(t, int32(1), reqVerificationBySyncDifference.Index)
}

// 测试棋盘游戏相关消息
func testBatch77BoardGameMessages(t *testing.T) {
	resBoardGameEnterCompletion := &dnfv1.ResBoardGameEnterCompletion{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBoardGameEnterCompletion)
	assert.Equal(t, int32(0), resBoardGameEnterCompletion.Error)
	assert.Equal(t, int32(1), resBoardGameEnterCompletion.Index)

	resRemoveBoard := &dnfv1.ResRemoveBoard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRemoveBoard)
	assert.Equal(t, int32(0), resRemoveBoard.Error)
	assert.Equal(t, int32(1), resRemoveBoard.Index)
}

// 测试友好相关消息
func testBatch77FriendlyMessages(t *testing.T) {
	resRefreshFriendly := &dnfv1.ResRefreshFriendly{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRefreshFriendly)
	assert.Equal(t, int32(0), resRefreshFriendly.Error)
	assert.Equal(t, int32(1), resRefreshFriendly.Index)
}

// 测试战斗联赛相关消息
func testBatch77BattleleagueMessages(t *testing.T) {
	notifyBattleleagueTeamRandomCard := &dnfv1.NotifyBattleleagueTeamRandomCard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBattleleagueTeamRandomCard)
	assert.Equal(t, int32(0), notifyBattleleagueTeamRandomCard.Error)
	assert.Equal(t, int32(1), notifyBattleleagueTeamRandomCard.Index)
}

// 测试成就相关消息
func testBatch77AchievementMessages(t *testing.T) {
	resAchievementReward := &dnfv1.ResAchievementReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAchievementReward)
	assert.Equal(t, int32(0), resAchievementReward.Error)
	assert.Equal(t, int32(1), resAchievementReward.Index)
}

// 测试会话相关消息
func testBatch77SessionMessages(t *testing.T) {
	sessionAuthRes := &dnfv1.SessionAuthRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, sessionAuthRes)
	assert.Equal(t, int32(0), sessionAuthRes.Error)
	assert.Equal(t, int32(1), sessionAuthRes.Index)
}

// 测试房间相关消息
func testBatch77RoomMessages(t *testing.T) {
	ptRoomUserInfo := &dnfv1.PtRoomUserInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRoomUserInfo)
	assert.Equal(t, int32(0), ptRoomUserInfo.Error)
	assert.Equal(t, int32(1), ptRoomUserInfo.Index)

	notifyJoinRoom := &dnfv1.NotifyJoinRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyJoinRoom)
	assert.Equal(t, int32(0), notifyJoinRoom.Error)
	assert.Equal(t, int32(1), notifyJoinRoom.Index)
}

// 测试内容相关消息
func testBatch77ContentsMessages(t *testing.T) {
	ptNewContents := &dnfv1.PtNewContents{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptNewContents)
	assert.Equal(t, int32(0), ptNewContents.Error)
	assert.Equal(t, int32(1), ptNewContents.Index)

	resContentsNotify := &dnfv1.ResContentsNotify{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resContentsNotify)
	assert.Equal(t, int32(0), resContentsNotify.Error)
	assert.Equal(t, int32(1), resContentsNotify.Index)

	resNotifyCheckupStatus := &dnfv1.ResNotifyCheckupStatus{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resNotifyCheckupStatus)
	assert.Equal(t, int32(0), resNotifyCheckupStatus.Error)
	assert.Equal(t, int32(1), resNotifyCheckupStatus.Index)
}

// 测试PVP相关消息
func testBatch77PvpMessages(t *testing.T) {
	reqPvpRecordInfo := &dnfv1.ReqPvpRecordInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPvpRecordInfo)
	assert.Equal(t, int32(0), reqPvpRecordInfo.Error)
	assert.Equal(t, int32(1), reqPvpRecordInfo.Index)
}

// 测试好友相关消息
func testBatch77FriendMessages(t *testing.T) {
	reqFriendFind := &dnfv1.ReqFriendFind{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqFriendFind)
	assert.Equal(t, int32(0), reqFriendFind.Error)
	assert.Equal(t, int32(1), reqFriendFind.Index)
}

// 测试多人游戏相关消息
func testBatch77MultiPlayMessages(t *testing.T) {
	notifyMultiPlayDungeonSelectRewardCard := &dnfv1.NotifyMultiPlayDungeonSelectRewardCard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyMultiPlayDungeonSelectRewardCard)
	assert.Equal(t, int32(0), notifyMultiPlayDungeonSelectRewardCard.Error)
	assert.Equal(t, int32(1), notifyMultiPlayDungeonSelectRewardCard.Index)
}

// 测试突袭相关消息
func testBatch77RaidMessages(t *testing.T) {
	resRaidPhaseStart := &dnfv1.ResRaidPhaseStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRaidPhaseStart)
	assert.Equal(t, int32(0), resRaidPhaseStart.Error)
	assert.Equal(t, int32(1), resRaidPhaseStart.Index)
}

// 测试城镇相关消息
func testBatch77TownMessages(t *testing.T) {
	reqLeaveFromTown := &dnfv1.ReqLeaveFromTown{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqLeaveFromTown)
	assert.Equal(t, int32(0), reqLeaveFromTown.Error)
	assert.Equal(t, int32(1), reqLeaveFromTown.Index)
}

// 测试平台相关消息
func testBatch77PlatformMessages(t *testing.T) {
	reqPlatformProfileUpdate := &dnfv1.ReqPlatformProfileUpdate{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPlatformProfileUpdate)
	assert.Equal(t, int32(0), reqPlatformProfileUpdate.Error)
	assert.Equal(t, int32(1), reqPlatformProfileUpdate.Index)
}

// 测试流数据相关消息
func testBatch77StreamMessages(t *testing.T) {
	streamDataReq := &dnfv1.StreamDataReq{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, streamDataReq)
	assert.Equal(t, int32(0), streamDataReq.Error)
	assert.Equal(t, int32(1), streamDataReq.Index)
}
