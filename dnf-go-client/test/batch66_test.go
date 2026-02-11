package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch66Migration 测试第66批迁移的消息类型
func TestBatch66Migration(t *testing.T) {
	t.Run("DragonBoard", testBatch66DragonBoardMessages)
	t.Run("MinigameAdventurer", testBatch66MinigameAdventurerMessages)
	t.Run("MainEvent", testBatch66MainEventMessages)
	t.Run("Recovery", testBatch66RecoveryMessages)
	t.Run("Collection", testBatch66CollectionMessages)
	t.Run("Avatar", testBatch66AvatarMessages)
	t.Run("Historicsite", testBatch66HistoricsiteMessages)
	t.Run("Auction", testBatch66AuctionMessages)
	t.Run("Lottery", testBatch66LotteryMessages)
	t.Run("Friend", testBatch66FriendMessages)
	t.Run("Party", testBatch66PartyMessages)
	t.Run("Contents", testBatch66ContentsMessages)
	t.Run("Guild", testBatch66GuildMessages)
	t.Run("Maas", testBatch66MaasMessages)
	t.Run("Group", testBatch66GroupMessages)
	t.Run("Dungeon", testBatch66DungeonMessages)
	t.Run("Weddinghall", testBatch66WeddinghallMessages)
	t.Run("Ranking", testBatch66RankingMessages)
	t.Run("BoardGame", testBatch66BoardGameMessages)
	t.Run("Character", testBatch66CharacterMessages)
	t.Run("Shop", testBatch66ShopMessages)
	t.Run("Item", testBatch66ItemMessages)
	t.Run("Achievement", testBatch66AchievementMessages)
	t.Run("Pvp", testBatch66PvpMessages)
	t.Run("Attack", testBatch66AttackMessages)
	t.Run("Platform", testBatch66PlatformMessages)
	t.Run("Quest", testBatch66QuestMessages)
	t.Run("Emblem", testBatch66EmblemMessages)
}

// 测试龙板相关消息
func testBatch66DragonBoardMessages(t *testing.T) {
	reqDragonBoardReconnect := &dnfv1.ReqDragonBoardReconnect{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDragonBoardReconnect)
	assert.Equal(t, int32(0), reqDragonBoardReconnect.Error)
	assert.Equal(t, int32(1), reqDragonBoardReconnect.Index)
}

// 测试小游戏冒险家相关消息
func testBatch66MinigameAdventurerMessages(t *testing.T) {
	resMinigameAdventurerMakerReward := &dnfv1.ResMinigameAdventurerMakerReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMinigameAdventurerMakerReward)
	assert.Equal(t, int32(0), resMinigameAdventurerMakerReward.Error)
	assert.Equal(t, int32(1), resMinigameAdventurerMakerReward.Index)

	ptMinigameAdventurerMakerBabyInfo := &dnfv1.PtMinigameAdventurerMakerBabyInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMinigameAdventurerMakerBabyInfo)
	assert.Equal(t, int32(0), ptMinigameAdventurerMakerBabyInfo.Error)
	assert.Equal(t, int32(1), ptMinigameAdventurerMakerBabyInfo.Index)
}

// 测试主事件相关消息
func testBatch66MainEventMessages(t *testing.T) {
	ptMainEventData := &dnfv1.PtMainEventData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMainEventData)
	assert.Equal(t, int32(0), ptMainEventData.Error)
	assert.Equal(t, int32(1), ptMainEventData.Index)
}

// 测试恢复相关消息
func testBatch66RecoveryMessages(t *testing.T) {
	reqRecoveryCharac := &dnfv1.ReqRecoveryCharac{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRecoveryCharac)
	assert.Equal(t, int32(0), reqRecoveryCharac.Error)
	assert.Equal(t, int32(1), reqRecoveryCharac.Index)
}

// 测试收集相关消息
func testBatch66CollectionMessages(t *testing.T) {
	resCollectionLevelReward := &dnfv1.ResCollectionLevelReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCollectionLevelReward)
	assert.Equal(t, int32(0), resCollectionLevelReward.Error)
	assert.Equal(t, int32(1), resCollectionLevelReward.Index)
}

// 测试头像相关消息
func testBatch66AvatarMessages(t *testing.T) {
	ptAvatarDisasambledMaterial := &dnfv1.PtAvatarDisasambledMaterial{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAvatarDisasambledMaterial)
	assert.Equal(t, int32(0), ptAvatarDisasambledMaterial.Error)
	assert.Equal(t, int32(1), ptAvatarDisasambledMaterial.Index)
}

// 测试历史站点相关消息
func testBatch66HistoricsiteMessages(t *testing.T) {
	resHistoricsiteMemberRecord := &dnfv1.ResHistoricsiteMemberRecord{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteMemberRecord)
	assert.Equal(t, int32(0), resHistoricsiteMemberRecord.Error)
	assert.Equal(t, int32(1), resHistoricsiteMemberRecord.Index)

	ptHistoricsiteRelicInfo := &dnfv1.PtHistoricsiteRelicInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptHistoricsiteRelicInfo)
	assert.Equal(t, int32(0), ptHistoricsiteRelicInfo.Error)
	assert.Equal(t, int32(1), ptHistoricsiteRelicInfo.Index)
}

// 测试拍卖相关消息
func testBatch66AuctionMessages(t *testing.T) {
	resAuctionMyRegisteredItemList := &dnfv1.ResAuctionMyRegisteredItemList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAuctionMyRegisteredItemList)
	assert.Equal(t, int32(0), resAuctionMyRegisteredItemList.Error)
	assert.Equal(t, int32(1), resAuctionMyRegisteredItemList.Index)
}

// 测试彩票相关消息
func testBatch66LotteryMessages(t *testing.T) {
	reqMinigameLotteryList := &dnfv1.ReqMinigameLotteryList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigameLotteryList)
	assert.Equal(t, int32(0), reqMinigameLotteryList.Error)
	assert.Equal(t, int32(1), reqMinigameLotteryList.Index)
}

// 测试好友相关消息
func testBatch66FriendMessages(t *testing.T) {
	reqFriendSendFatigue := &dnfv1.ReqFriendSendFatigue{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqFriendSendFatigue)
	assert.Equal(t, int32(0), reqFriendSendFatigue.Error)
	assert.Equal(t, int32(1), reqFriendSendFatigue.Index)

	notifyFriendsInfo := &dnfv1.NotifyFriendsInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyFriendsInfo)
	assert.Equal(t, int32(0), notifyFriendsInfo.Error)
	assert.Equal(t, int32(1), notifyFriendsInfo.Index)
}

// 测试队伍相关消息
func testBatch66PartyMessages(t *testing.T) {
	ptParty := &dnfv1.PtParty{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptParty)
	assert.Equal(t, int32(0), ptParty.Error)
	assert.Equal(t, int32(1), ptParty.Index)
}

// 测试内容相关消息
func testBatch66ContentsMessages(t *testing.T) {
	reqContentsReward := &dnfv1.ReqContentsReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqContentsReward)
	assert.Equal(t, int32(0), reqContentsReward.Error)
	assert.Equal(t, int32(1), reqContentsReward.Index)
}

// 测试公会相关消息
func testBatch66GuildMessages(t *testing.T) {
	resGuildStructureRetrive := &dnfv1.ResGuildStructureRetrive{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildStructureRetrive)
	assert.Equal(t, int32(0), resGuildStructureRetrive.Error)
	assert.Equal(t, int32(1), resGuildStructureRetrive.Index)

	reqGuildMemberRankingInfo := &dnfv1.ReqGuildMemberRankingInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildMemberRankingInfo)
	assert.Equal(t, int32(0), reqGuildMemberRankingInfo.Error)
	assert.Equal(t, int32(1), reqGuildMemberRankingInfo.Index)

	reqGuildHistoricsiteRanking := &dnfv1.ReqGuildHistoricsiteRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildHistoricsiteRanking)
	assert.Equal(t, int32(0), reqGuildHistoricsiteRanking.Error)
	assert.Equal(t, int32(1), reqGuildHistoricsiteRanking.Index)
}

// 测试MAAS相关消息
func testBatch66MaasMessages(t *testing.T) {
	resMaasMustNoti := &dnfv1.ResMaasMustNoti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMaasMustNoti)
	assert.Equal(t, int32(0), resMaasMustNoti.Error)
	assert.Equal(t, int32(1), resMaasMustNoti.Index)
}

// 测试组相关消息
func testBatch66GroupMessages(t *testing.T) {
	ptGroupMember := &dnfv1.PtGroupMember{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGroupMember)
	assert.Equal(t, int32(0), ptGroupMember.Error)
	assert.Equal(t, int32(1), ptGroupMember.Index)
}

// 测试副本相关消息
func testBatch66DungeonMessages(t *testing.T) {
	notifySyncDungeonStartTime := &dnfv1.NotifySyncDungeonStartTime{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifySyncDungeonStartTime)
	assert.Equal(t, int32(0), notifySyncDungeonStartTime.Error)
	assert.Equal(t, int32(1), notifySyncDungeonStartTime.Index)
}

// 测试婚礼大厅相关消息
func testBatch66WeddinghallMessages(t *testing.T) {
	ptIntrudeWeddinghallInfo := &dnfv1.PtIntrudeWeddinghallInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptIntrudeWeddinghallInfo)
	assert.Equal(t, int32(0), ptIntrudeWeddinghallInfo.Error)
	assert.Equal(t, int32(1), ptIntrudeWeddinghallInfo.Index)
}

// 测试排名相关消息
func testBatch66RankingMessages(t *testing.T) {
	reqMyRanking := &dnfv1.ReqMyRanking{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMyRanking)
	assert.Equal(t, int32(0), reqMyRanking.Error)
	assert.Equal(t, int32(1), reqMyRanking.Index)
}

// 测试棋盘游戏相关消息
func testBatch66BoardGameMessages(t *testing.T) {
	reqBoardGameEnterCompletion := &dnfv1.ReqBoardGameEnterCompletion{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqBoardGameEnterCompletion)
	assert.Equal(t, int32(0), reqBoardGameEnterCompletion.Error)
	assert.Equal(t, int32(1), reqBoardGameEnterCompletion.Index)
}

// 测试角色相关消息
func testBatch66CharacterMessages(t *testing.T) {
	reqCharacterStatInfo := &dnfv1.ReqCharacterStatInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCharacterStatInfo)
	assert.Equal(t, int32(0), reqCharacterStatInfo.Error)
	assert.Equal(t, int32(1), reqCharacterStatInfo.Index)
}

// 测试商店相关消息
func testBatch66ShopMessages(t *testing.T) {
	resCeraShopBuyInfo := &dnfv1.ResCeraShopBuyInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCeraShopBuyInfo)
	assert.Equal(t, int32(0), resCeraShopBuyInfo.Error)
	assert.Equal(t, int32(1), resCeraShopBuyInfo.Index)
}

// 测试物品相关消息
func testBatch66ItemMessages(t *testing.T) {
	ptItemDisjointGuid := &dnfv1.PtItemDisjointGuid{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptItemDisjointGuid)
	assert.Equal(t, int32(0), ptItemDisjointGuid.Error)
	assert.Equal(t, int32(1), ptItemDisjointGuid.Index)
}

// 测试成就相关消息
func testBatch66AchievementMessages(t *testing.T) {
	achievementBonusPacketDataList := &dnfv1.AchievementBonusPacketDataList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, achievementBonusPacketDataList)
	assert.Equal(t, int32(0), achievementBonusPacketDataList.Error)
	assert.Equal(t, int32(1), achievementBonusPacketDataList.Index)
}

// 测试PVP相关消息
func testBatch66PvpMessages(t *testing.T) {
	resPvp3Vs3JoinCharguid := &dnfv1.ResPvp3Vs3JoinCharguid{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPvp3Vs3JoinCharguid)
	assert.Equal(t, int32(0), resPvp3Vs3JoinCharguid.Error)
	assert.Equal(t, int32(1), resPvp3Vs3JoinCharguid.Index)
}

// 测试攻击相关消息
func testBatch66AttackMessages(t *testing.T) {
	reqAttackSquadInfoChange := &dnfv1.ReqAttackSquadInfoChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadInfoChange)
	assert.Equal(t, int32(0), reqAttackSquadInfoChange.Error)
	assert.Equal(t, int32(1), reqAttackSquadInfoChange.Index)
}

// 测试平台相关消息
func testBatch66PlatformMessages(t *testing.T) {
	resJoinPlatformParty := &dnfv1.ResJoinPlatformParty{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resJoinPlatformParty)
	assert.Equal(t, int32(0), resJoinPlatformParty.Error)
	assert.Equal(t, int32(1), resJoinPlatformParty.Index)

	ptRankPlatformFriendInfo := &dnfv1.PtRankPlatformFriendInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRankPlatformFriendInfo)
	assert.Equal(t, int32(0), ptRankPlatformFriendInfo.Error)
	assert.Equal(t, int32(1), ptRankPlatformFriendInfo.Index)
}

// 测试任务相关消息
func testBatch66QuestMessages(t *testing.T) {
	ptCompleteQuestInfo := &dnfv1.PtCompleteQuestInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCompleteQuestInfo)
	assert.Equal(t, int32(0), ptCompleteQuestInfo.Error)
	assert.Equal(t, int32(1), ptCompleteQuestInfo.Index)
}

// 测试徽章相关消息
func testBatch66EmblemMessages(t *testing.T) {
	resEmblemExtract := &dnfv1.ResEmblemExtract{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEmblemExtract)
	assert.Equal(t, int32(0), resEmblemExtract.Error)
	assert.Equal(t, int32(1), resEmblemExtract.Index)
}
