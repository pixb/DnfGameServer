package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch72Migration 测试第72批迁移的消息类型
func TestBatch72Migration(t *testing.T) {
	t.Run("GuildDining", testBatch72GuildDiningMessages)
	t.Run("ControlGroup", testBatch72ControlGroupMessages)
	t.Run("Team", testBatch72TeamMessages)
	t.Run("Bookmark", testBatch72BookmarkMessages)
	t.Run("Raid", testBatch72RaidMessages)
	t.Run("BoardGame", testBatch72BoardGameMessages)
	t.Run("Minigame", testBatch72MinigameMessages)
	t.Run("Fatigue", testBatch72FatigueMessages)
	t.Run("Crackequip", testBatch72CrackequipMessages)
	t.Run("Server", testBatch72ServerMessages)
	t.Run("ReadyRoom", testBatch72ReadyRoomMessages)
	t.Run("Verification", testBatch72VerificationMessages)
	t.Run("Character", testBatch72CharacterMessages)
	t.Run("Compose", testBatch72ComposeMessages)
	t.Run("Maas", testBatch72MaasMessages)
	t.Run("Item", testBatch72ItemMessages)
	t.Run("Repurchase", testBatch72RepurchaseMessages)
	t.Run("Equipment", testBatch72EquipmentMessages)
	t.Run("Melting", testBatch72MeltingMessages)
	t.Run("Breaking", testBatch72BreakingMessages)
	t.Run("MultiPlay", testBatch72MultiPlayMessages)
	t.Run("Pvp", testBatch72PvpMessages)
	t.Run("Watching", testBatch72WatchingMessages)
	t.Run("GuildMember", testBatch72GuildMemberMessages)
	t.Run("Card", testBatch72CardMessages)
	t.Run("QuickChatting", testBatch72QuickChattingMessages)
	t.Run("Adventure", testBatch72AdventureMessages)
	t.Run("Passive", testBatch72PassiveMessages)
	t.Run("Blacklist", testBatch72BlacklistMessages)
	t.Run("Historicsite", testBatch72HistoricsiteMessages)
	t.Run("Charguid", testBatch72CharguidMessages)
	t.Run("Mentor", testBatch72MentorMessages)
	t.Run("GuildMaterial", testBatch72GuildMaterialMessages)
	t.Run("Inven", testBatch72InvenMessages)
	t.Run("Refresh", testBatch72RefreshMessages)
	t.Run("Dragon", testBatch72DragonMessages)
}

// 测试公会餐厅相关消息
func testBatch72GuildDiningMessages(t *testing.T) {
	reqGuildDiningBuy := &dnfv1.ReqGuildDiningBuy{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildDiningBuy)
	assert.Equal(t, int32(0), reqGuildDiningBuy.Error)
	assert.Equal(t, int32(1), reqGuildDiningBuy.Index)
}

// 测试控制组相关消息
func testBatch72ControlGroupMessages(t *testing.T) {
	resControlGroupQueryarea := &dnfv1.ResControlGroupQueryarea{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resControlGroupQueryarea)
	assert.Equal(t, int32(0), resControlGroupQueryarea.Error)
	assert.Equal(t, int32(1), resControlGroupQueryarea.Index)
}

// 测试队伍相关消息
func testBatch72TeamMessages(t *testing.T) {
	resTeamChange := &dnfv1.ResTeamChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTeamChange)
	assert.Equal(t, int32(0), resTeamChange.Error)
	assert.Equal(t, int32(1), resTeamChange.Index)
}

// 测试书签相关消息
func testBatch72BookmarkMessages(t *testing.T) {
	resBookmarkList := &dnfv1.ResBookmarkList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBookmarkList)
	assert.Equal(t, int32(0), resBookmarkList.Error)
	assert.Equal(t, int32(1), resBookmarkList.Index)

	resComposeBookmark := &dnfv1.ResComposeBookmark{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resComposeBookmark)
	assert.Equal(t, int32(0), resComposeBookmark.Error)
	assert.Equal(t, int32(1), resComposeBookmark.Index)
}

// 测试突袭相关消息
func testBatch72RaidMessages(t *testing.T) {
	notifyRaidCoinInfo := &dnfv1.NotifyRaidCoinInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyRaidCoinInfo)
	assert.Equal(t, int32(0), notifyRaidCoinInfo.Error)
	assert.Equal(t, int32(1), notifyRaidCoinInfo.Index)
}

// 测试棋盘游戏相关消息
func testBatch72BoardGameMessages(t *testing.T) {
	resBoardGameSyncHp := &dnfv1.ResBoardGameSyncHp{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBoardGameSyncHp)
	assert.Equal(t, int32(0), resBoardGameSyncHp.Error)
	assert.Equal(t, int32(1), resBoardGameSyncHp.Index)
}

// 测试小游戏相关消息
func testBatch72MinigameMessages(t *testing.T) {
	resMinigameMiniPongLoad := &dnfv1.ResMinigameMiniPongLoad{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameMiniPongLoad)
	assert.Equal(t, int32(0), resMinigameMiniPongLoad.Error)
	assert.Equal(t, int32(1), resMinigameMiniPongLoad.Index)

	resMinigameBreakingClear := &dnfv1.ResMinigameBreakingClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameBreakingClear)
	assert.Equal(t, int32(0), resMinigameBreakingClear.Error)
	assert.Equal(t, int32(1), resMinigameBreakingClear.Index)
}

// 测试疲劳相关消息
func testBatch72FatigueMessages(t *testing.T) {
	resFatigueHelpRequest := &dnfv1.ResFatigueHelpRequest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resFatigueHelpRequest)
	assert.Equal(t, int32(0), resFatigueHelpRequest.Error)
	assert.Equal(t, int32(1), resFatigueHelpRequest.Index)
}

// 测试裂缝装备相关消息
func testBatch72CrackequipMessages(t *testing.T) {
	resCrackequipList := &dnfv1.ResCrackequipList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCrackequipList)
	assert.Equal(t, int32(0), resCrackequipList.Error)
	assert.Equal(t, int32(1), resCrackequipList.Index)
}

// 测试服务器相关消息
func testBatch72ServerMessages(t *testing.T) {
	kickedByServerNoti := &dnfv1.KickedByServerNoti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, kickedByServerNoti)
	assert.Equal(t, int32(0), kickedByServerNoti.Error)
	assert.Equal(t, int32(1), kickedByServerNoti.Index)
}

// 测试准备房间相关消息
func testBatch72ReadyRoomMessages(t *testing.T) {
	resReadyRoom := &dnfv1.ResReadyRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resReadyRoom)
	assert.Equal(t, int32(0), resReadyRoom.Error)
	assert.Equal(t, int32(1), resReadyRoom.Index)
}

// 测试验证相关消息
func testBatch72VerificationMessages(t *testing.T) {
	ptVerificationAddDamageData := &dnfv1.PtVerificationAddDamageData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptVerificationAddDamageData)
	assert.Equal(t, int32(0), ptVerificationAddDamageData.Error)
	assert.Equal(t, int32(1), ptVerificationAddDamageData.Index)
}

// 测试角色相关消息
func testBatch72CharacterMessages(t *testing.T) {
	reqCreateCharacter := &dnfv1.ReqCreateCharacter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCreateCharacter)
	assert.Equal(t, int32(0), reqCreateCharacter.Error)
	assert.Equal(t, int32(1), reqCreateCharacter.Index)
}

// 测试组合相关消息
func testBatch72ComposeMessages(t *testing.T) {
	resComposeBookmark := &dnfv1.ResComposeBookmark{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resComposeBookmark)
	assert.Equal(t, int32(0), resComposeBookmark.Error)
	assert.Equal(t, int32(1), resComposeBookmark.Index)
}

// 测试MAAS相关消息
func testBatch72MaasMessages(t *testing.T) {
	resMaasSleepPlay := &dnfv1.ResMaasSleepPlay{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMaasSleepPlay)
	assert.Equal(t, int32(0), resMaasSleepPlay.Error)
	assert.Equal(t, int32(1), resMaasSleepPlay.Index)
}

// 测试物品相关消息
func testBatch72ItemMessages(t *testing.T) {
	ptItemDisjointMaterial := &dnfv1.PtItemDisjointMaterial{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptItemDisjointMaterial)
	assert.Equal(t, int32(0), ptItemDisjointMaterial.Error)
	assert.Equal(t, int32(1), ptItemDisjointMaterial.Index)
}

// 测试重新购买相关消息
func testBatch72RepurchaseMessages(t *testing.T) {
	resRepurchaseItem := &dnfv1.ResRepurchaseItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRepurchaseItem)
	assert.Equal(t, int32(0), resRepurchaseItem.Error)
	assert.Equal(t, int32(1), resRepurchaseItem.Index)
}

// 测试装备相关消息
func testBatch72EquipmentMessages(t *testing.T) {
	resEquipmentRarify := &dnfv1.ResEquipmentRarify{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEquipmentRarify)
	assert.Equal(t, int32(0), resEquipmentRarify.Error)
	assert.Equal(t, int32(1), resEquipmentRarify.Index)
}

// 测试熔炼相关消息
func testBatch72MeltingMessages(t *testing.T) {
	resMeltingCrack := &dnfv1.ResMeltingCrack{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMeltingCrack)
	assert.Equal(t, int32(0), resMeltingCrack.Error)
	assert.Equal(t, int32(1), resMeltingCrack.Index)
}

// 测试破坏相关消息
func testBatch72BreakingMessages(t *testing.T) {
	resMinigameBreakingClear := &dnfv1.ResMinigameBreakingClear{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameBreakingClear)
	assert.Equal(t, int32(0), resMinigameBreakingClear.Error)
	assert.Equal(t, int32(1), resMinigameBreakingClear.Index)
}

// 测试多人游戏相关消息
func testBatch72MultiPlayMessages(t *testing.T) {
	resMultiPlayStartDungeon := &dnfv1.ResMultiPlayStartDungeon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMultiPlayStartDungeon)
	assert.Equal(t, int32(0), resMultiPlayStartDungeon.Error)
	assert.Equal(t, int32(1), resMultiPlayStartDungeon.Index)
}

// 测试PVP相关消息
func testBatch72PvpMessages(t *testing.T) {
	resPvpRecordInfo := &dnfv1.ResPvpRecordInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPvpRecordInfo)
	assert.Equal(t, int32(0), resPvpRecordInfo.Error)
	assert.Equal(t, int32(1), resPvpRecordInfo.Index)
}

// 测试观看相关消息
func testBatch72WatchingMessages(t *testing.T) {
	resLoadWatchingBookmarks := &dnfv1.ResLoadWatchingBookmarks{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLoadWatchingBookmarks)
	assert.Equal(t, int32(0), resLoadWatchingBookmarks.Error)
	assert.Equal(t, int32(1), resLoadWatchingBookmarks.Index)
}

// 测试公会成员相关消息
func testBatch72GuildMemberMessages(t *testing.T) {
	resGuildMemberRankingInfo := &dnfv1.ResGuildMemberRankingInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildMemberRankingInfo)
	assert.Equal(t, int32(0), resGuildMemberRankingInfo.Error)
	assert.Equal(t, int32(1), resGuildMemberRankingInfo.Index)
}

// 测试卡片相关消息
func testBatch72CardMessages(t *testing.T) {
	resCardAttach := &dnfv1.ResCardAttach{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCardAttach)
	assert.Equal(t, int32(0), resCardAttach.Error)
	assert.Equal(t, int32(1), resCardAttach.Index)
}

// 测试快速聊天相关消息
func testBatch72QuickChattingMessages(t *testing.T) {
	reqQuickChatting := &dnfv1.ReqQuickChatting{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqQuickChatting)
	assert.Equal(t, int32(0), reqQuickChatting.Error)
	assert.Equal(t, int32(1), reqQuickChatting.Index)
}

// 测试冒险相关消息
func testBatch72AdventureMessages(t *testing.T) {
	reqAdventureReapReward := &dnfv1.ReqAdventureReapReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureReapReward)
	assert.Equal(t, int32(0), reqAdventureReapReward.Error)
	assert.Equal(t, int32(1), reqAdventureReapReward.Index)
}

// 测试被动相关消息
func testBatch72PassiveMessages(t *testing.T) {
	ptPassiveObject := &dnfv1.PtPassiveObject{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPassiveObject)
	assert.Equal(t, int32(0), ptPassiveObject.Error)
	assert.Equal(t, int32(1), ptPassiveObject.Index)
}

// 测试黑名单相关消息
func testBatch72BlacklistMessages(t *testing.T) {
	ptBlacklist := &dnfv1.PtBlacklist{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptBlacklist)
	assert.Equal(t, int32(0), ptBlacklist.Error)
	assert.Equal(t, int32(1), ptBlacklist.Index)
}

// 测试历史站点相关消息
func testBatch72HistoricsiteMessages(t *testing.T) {
	resHistoricsiteEmoticons := &dnfv1.ResHistoricsiteEmoticons{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteEmoticons)
	assert.Equal(t, int32(0), resHistoricsiteEmoticons.Error)
	assert.Equal(t, int32(1), resHistoricsiteEmoticons.Index)
}

// 测试充值指南相关消息
func testBatch72CharguidMessages(t *testing.T) {
	ptCharguidEntranceItem := &dnfv1.PtCharguidEntranceItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCharguidEntranceItem)
	assert.Equal(t, int32(0), ptCharguidEntranceItem.Error)
	assert.Equal(t, int32(1), ptCharguidEntranceItem.Index)
}

// 测试导师相关消息
func testBatch72MentorMessages(t *testing.T) {
	ptMentorQuestInfo := &dnfv1.PtMentorQuestInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMentorQuestInfo)
	assert.Equal(t, int32(0), ptMentorQuestInfo.Error)
	assert.Equal(t, int32(1), ptMentorQuestInfo.Index)
}

// 测试公会材料相关消息
func testBatch72GuildMaterialMessages(t *testing.T) {
	reqGuildMaterialProduction := &dnfv1.ReqGuildMaterialProduction{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildMaterialProduction)
	assert.Equal(t, int32(0), reqGuildMaterialProduction.Error)
	assert.Equal(t, int32(1), reqGuildMaterialProduction.Index)
}

// 测试背包相关消息
func testBatch72InvenMessages(t *testing.T) {
	reqInvenExtend := &dnfv1.ReqInvenExtend{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqInvenExtend)
	assert.Equal(t, int32(0), reqInvenExtend.Error)
	assert.Equal(t, int32(1), reqInvenExtend.Index)
}

// 测试刷新相关消息
func testBatch72RefreshMessages(t *testing.T) {
	resRefreshCharacterInfo := &dnfv1.ResRefreshCharacterInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRefreshCharacterInfo)
	assert.Equal(t, int32(0), resRefreshCharacterInfo.Error)
	assert.Equal(t, int32(1), resRefreshCharacterInfo.Index)
}

// 测试龙盘相关消息
func testBatch72DragonMessages(t *testing.T) {
	notifyDragonBoardResult := &dnfv1.NotifyDragonBoardResult{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyDragonBoardResult)
	assert.Equal(t, int32(0), notifyDragonBoardResult.Error)
	assert.Equal(t, int32(1), notifyDragonBoardResult.Index)
}
