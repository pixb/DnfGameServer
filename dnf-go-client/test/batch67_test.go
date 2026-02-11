package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch67Migration 测试第67批迁移的消息类型
func TestBatch67Migration(t *testing.T) {
	t.Run("RandomOption", testBatch67RandomOptionMessages)
	t.Run("Avatar", testBatch67AvatarMessages)
	t.Run("PlayWithMe", testBatch67PlayWithMeMessages)
	t.Run("MultiPlay", testBatch67MultiPlayMessages)
	t.Run("Artifact", testBatch67ArtifactMessages)
	t.Run("Contents", testBatch67ContentsMessages)
	t.Run("Pvp", testBatch67PvpMessages)
	t.Run("AdventureUnion", testBatch67AdventureUnionMessages)
	t.Run("Ranking", testBatch67RankingMessages)
	t.Run("Player", testBatch67PlayerMessages)
	t.Run("Welfare", testBatch67WelfareMessages)
	t.Run("Material", testBatch67MaterialMessages)
	t.Run("Share", testBatch67ShareMessages)
	t.Run("Recruit", testBatch67RecruitMessages)
	t.Run("Party", testBatch67PartyMessages)
	t.Run("Divorce", testBatch67DivorceMessages)
	t.Run("Item", testBatch67ItemMessages)
	t.Run("NewContents", testBatch67ContentsMessages)
	t.Run("Interesting", testBatch67InterestingMessages)
	t.Run("Mannequin", testBatch67MannequinMessages)
	t.Run("CeraGacha", testBatch67CeraGachaMessages)
	t.Run("Board", testBatch67BoardMessages)
	t.Run("Auction", testBatch67AuctionMessages)
	t.Run("Dungeon", testBatch67DungeonMessages)
	t.Run("ArcadePvp", testBatch67ArcadePvpMessages)
	t.Run("AttackSquad", testBatch67AttackSquadMessages)
	t.Run("Skill", testBatch67SkillMessages)
	t.Run("Node", testBatch67NodeMessages)
	t.Run("Member", testBatch67MemberMessages)
	t.Run("Creature", testBatch67CreatureMessages)
	t.Run("Mail", testBatch67MailMessages)
	t.Run("Production", testBatch67ProductionMessages)
	t.Run("Historicsite", testBatch67HistoricsiteMessages)
	t.Run("BlackDiamond", testBatch67BlackDiamondMessages)
	t.Run("GuildChat", testBatch67GuildChatMessages)
	t.Run("Raid", testBatch67RaidMessages)
	t.Run("Card", testBatch67CardMessages)
	t.Run("Bookmark", testBatch67BookmarkMessages)
}

// 测试随机选项相关消息
func testBatch67RandomOptionMessages(t *testing.T) {
	ptRandomoptionItem := &dnfv1.PtRandomoptionItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRandomoptionItem)
	assert.Equal(t, int32(0), ptRandomoptionItem.Error)
	assert.Equal(t, int32(1), ptRandomoptionItem.Index)
}

// 测试头像相关消息
func testBatch67AvatarMessages(t *testing.T) {
	resAvatarVisibleFlagsSet := &dnfv1.ResAvatarVisibleFlagsSet{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAvatarVisibleFlagsSet)
	assert.Equal(t, int32(0), resAvatarVisibleFlagsSet.Error)
	assert.Equal(t, int32(1), resAvatarVisibleFlagsSet.Index)
}

// 测试一起玩相关消息
func testBatch67PlayWithMeMessages(t *testing.T) {
	ptPlayWithMeFriendInfo := &dnfv1.PtPlayWithMeFriendInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPlayWithMeFriendInfo)
	assert.Equal(t, int32(0), ptPlayWithMeFriendInfo.Error)
	assert.Equal(t, int32(1), ptPlayWithMeFriendInfo.Index)
}

// 测试多人游戏相关消息
func testBatch67MultiPlayMessages(t *testing.T) {
	resMultiPlayStartDungeon := &dnfv1.ResMultiPlayStartDungeon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMultiPlayStartDungeon)
	assert.Equal(t, int32(0), resMultiPlayStartDungeon.Error)
	assert.Equal(t, int32(1), resMultiPlayStartDungeon.Index)

	reqMultiPlayDungeonSelectRewardCard := &dnfv1.ReqMultiPlayDungeonSelectRewardCard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMultiPlayDungeonSelectRewardCard)
	assert.Equal(t, int32(0), reqMultiPlayDungeonSelectRewardCard.Error)
	assert.Equal(t, int32(1), reqMultiPlayDungeonSelectRewardCard.Index)
}

// 测试神器相关消息
func testBatch67ArtifactMessages(t *testing.T) {
	reqArtifactList := &dnfv1.ReqArtifactList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqArtifactList)
	assert.Equal(t, int32(0), reqArtifactList.Error)
	assert.Equal(t, int32(1), reqArtifactList.Index)
}

// 测试内容相关消息
func testBatch67ContentsMessages(t *testing.T) {
	contentsListTypesRes := &dnfv1.ContentsListTypesRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, contentsListTypesRes)
	assert.Equal(t, int32(0), contentsListTypesRes.Error)
	assert.Equal(t, int32(1), contentsListTypesRes.Index)

	resNewContentsList := &dnfv1.ResNewContentsList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resNewContentsList)
	assert.Equal(t, int32(0), resNewContentsList.Error)
	assert.Equal(t, int32(1), resNewContentsList.Index)
}

// 测试PVP相关消息
func testBatch67PvpMessages(t *testing.T) {
	resPvpRecordInfo := &dnfv1.ResPvpRecordInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPvpRecordInfo)
	assert.Equal(t, int32(0), resPvpRecordInfo.Error)
	assert.Equal(t, int32(1), resPvpRecordInfo.Index)

	reqPvpRecordInfo := &dnfv1.ReqPvpRecordInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPvpRecordInfo)
	assert.Equal(t, int32(0), reqPvpRecordInfo.Error)
	assert.Equal(t, int32(1), reqPvpRecordInfo.Index)
}

// 测试冒险联盟相关消息
func testBatch67AdventureUnionMessages(t *testing.T) {
	reqAdventureUnionRepresentCharacterChange := &dnfv1.ReqAdventureUnionRepresentCharacterChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionRepresentCharacterChange)
	assert.Equal(t, int32(0), reqAdventureUnionRepresentCharacterChange.Error)
	assert.Equal(t, int32(1), reqAdventureUnionRepresentCharacterChange.Index)

	reqAdventureUnionExpeditionStart := &dnfv1.ReqAdventureUnionExpeditionStart{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionExpeditionStart)
	assert.Equal(t, int32(0), reqAdventureUnionExpeditionStart.Error)
	assert.Equal(t, int32(1), reqAdventureUnionExpeditionStart.Index)
}

// 测试排名相关消息
func testBatch67RankingMessages(t *testing.T) {
	reqRankingDungeonSeasonInfo := &dnfv1.ReqRankingDungeonSeasonInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRankingDungeonSeasonInfo)
	assert.Equal(t, int32(0), reqRankingDungeonSeasonInfo.Error)
	assert.Equal(t, int32(1), reqRankingDungeonSeasonInfo.Index)
}

// 测试玩家相关消息
func testBatch67PlayerMessages(t *testing.T) {
	playerData := &dnfv1.PlayerData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, playerData)
	assert.Equal(t, int32(0), playerData.Error)
	assert.Equal(t, int32(1), playerData.Index)
}

// 测试福利相关消息
func testBatch67WelfareMessages(t *testing.T) {
	resWelfareFindRewardInfo := &dnfv1.ResWelfareFindRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWelfareFindRewardInfo)
	assert.Equal(t, int32(0), resWelfareFindRewardInfo.Error)
	assert.Equal(t, int32(1), resWelfareFindRewardInfo.Index)
}

// 测试材料相关消息
func testBatch67MaterialMessages(t *testing.T) {
	resMaterialList := &dnfv1.ResMaterialList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMaterialList)
	assert.Equal(t, int32(0), resMaterialList.Error)
	assert.Equal(t, int32(1), resMaterialList.Index)
}

// 测试分享相关消息
func testBatch67ShareMessages(t *testing.T) {
	resLoadShareReward := &dnfv1.ResLoadShareReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resLoadShareReward)
	assert.Equal(t, int32(0), resLoadShareReward.Error)
	assert.Equal(t, int32(1), resLoadShareReward.Index)
}

// 测试招募相关消息
func testBatch67RecruitMessages(t *testing.T) {
	ptRecruitPartyInfo := &dnfv1.PtRecruitPartyInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRecruitPartyInfo)
	assert.Equal(t, int32(0), ptRecruitPartyInfo.Error)
	assert.Equal(t, int32(1), ptRecruitPartyInfo.Index)
}

// 测试队伍相关消息
func testBatch67PartyMessages(t *testing.T) {
	notifyPartyJoinSuccess := &dnfv1.NotifyPartyJoinSuccess{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyPartyJoinSuccess)
	assert.Equal(t, int32(0), notifyPartyJoinSuccess.Error)
	assert.Equal(t, int32(1), notifyPartyJoinSuccess.Index)
}

// 测试离婚相关消息
func testBatch67DivorceMessages(t *testing.T) {
	resDivorceQuestionReply := &dnfv1.ResDivorceQuestionReply{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resDivorceQuestionReply)
	assert.Equal(t, int32(0), resDivorceQuestionReply.Error)
	assert.Equal(t, int32(1), resDivorceQuestionReply.Index)
}

// 测试物品相关消息
func testBatch67ItemMessages(t *testing.T) {
	resItemAvrPrice := &dnfv1.ResItemAvrPrice{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemAvrPrice)
	assert.Equal(t, int32(0), resItemAvrPrice.Error)
	assert.Equal(t, int32(1), resItemAvrPrice.Index)
}

// 测试有趣商品相关消息
func testBatch67InterestingMessages(t *testing.T) {
	ptInterestingGoods := &dnfv1.PtInterestingGoods{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptInterestingGoods)
	assert.Equal(t, int32(0), ptInterestingGoods.Error)
	assert.Equal(t, int32(1), ptInterestingGoods.Index)
}

// 测试人体模型相关消息
func testBatch67MannequinMessages(t *testing.T) {
	resSetNameMannequin := &dnfv1.ResSetNameMannequin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSetNameMannequin)
	assert.Equal(t, int32(0), resSetNameMannequin.Error)
	assert.Equal(t, int32(1), resSetNameMannequin.Index)
}

// 测试CERA扭蛋相关消息
func testBatch67CeraGachaMessages(t *testing.T) {
	reqCeraGachaBuy := &dnfv1.ReqCeraGachaBuy{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCeraGachaBuy)
	assert.Equal(t, int32(0), reqCeraGachaBuy.Error)
	assert.Equal(t, int32(1), reqCeraGachaBuy.Index)
}

// 测试公告板相关消息
func testBatch67BoardMessages(t *testing.T) {
	reqLoadBoard := &dnfv1.ReqLoadBoard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqLoadBoard)
	assert.Equal(t, int32(0), reqLoadBoard.Error)
	assert.Equal(t, int32(1), reqLoadBoard.Index)
}

// 测试拍卖相关消息
func testBatch67AuctionMessages(t *testing.T) {
	reqAuctionRegisterComplete := &dnfv1.ReqAuctionRegisterComplete{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAuctionRegisterComplete)
	assert.Equal(t, int32(0), reqAuctionRegisterComplete.Error)
	assert.Equal(t, int32(1), reqAuctionRegisterComplete.Index)
}

// 测试副本相关消息
func testBatch67DungeonMessages(t *testing.T) {
	reqMultiPlayDungeonSelectRewardCard := &dnfv1.ReqMultiPlayDungeonSelectRewardCard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMultiPlayDungeonSelectRewardCard)
	assert.Equal(t, int32(0), reqMultiPlayDungeonSelectRewardCard.Error)
	assert.Equal(t, int32(1), reqMultiPlayDungeonSelectRewardCard.Index)
}

// 测试街机PVP相关消息
func testBatch67ArcadePvpMessages(t *testing.T) {
	resArcadePvpBuyTiket := &dnfv1.ResArcadePvpBuyTiket{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resArcadePvpBuyTiket)
	assert.Equal(t, int32(0), resArcadePvpBuyTiket.Error)
	assert.Equal(t, int32(1), resArcadePvpBuyTiket.Index)
}

// 测试攻击小队相关消息
func testBatch67AttackSquadMessages(t *testing.T) {
	reqAttackSquadBoardRenew := &dnfv1.ReqAttackSquadBoardRenew{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadBoardRenew)
	assert.Equal(t, int32(0), reqAttackSquadBoardRenew.Error)
	assert.Equal(t, int32(1), reqAttackSquadBoardRenew.Index)
}

// 测试技能相关消息
func testBatch67SkillMessages(t *testing.T) {
	ptSkillInfo := &dnfv1.PtSkillInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptSkillInfo)
	assert.Equal(t, int32(0), ptSkillInfo.Error)
	assert.Equal(t, int32(1), ptSkillInfo.Index)
}

// 测试节点相关消息
func testBatch67NodeMessages(t *testing.T) {
	nodeKickTypesNoti := &dnfv1.NodeKickTypesNoti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, nodeKickTypesNoti)
	assert.Equal(t, int32(0), nodeKickTypesNoti.Error)
	assert.Equal(t, int32(1), nodeKickTypesNoti.Index)
}

// 测试成员相关消息
func testBatch67MemberMessages(t *testing.T) {
	ptMemberAreaInfo := &dnfv1.PtMemberAreaInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMemberAreaInfo)
	assert.Equal(t, int32(0), ptMemberAreaInfo.Error)
	assert.Equal(t, int32(1), ptMemberAreaInfo.Index)
}

// 测试生物相关消息
func testBatch67CreatureMessages(t *testing.T) {
	reqCreatureErrandSend := &dnfv1.ReqCreatureErrandSend{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCreatureErrandSend)
	assert.Equal(t, int32(0), reqCreatureErrandSend.Error)
	assert.Equal(t, int32(1), reqCreatureErrandSend.Index)
}

// 测试邮件相关消息
func testBatch67MailMessages(t *testing.T) {
	resMailGet := &dnfv1.ResMailGet{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMailGet)
	assert.Equal(t, int32(0), resMailGet.Error)
	assert.Equal(t, int32(1), resMailGet.Index)
}

// 测试生产相关消息
func testBatch67ProductionMessages(t *testing.T) {
	resItemProductionRegister := &dnfv1.ResItemProductionRegister{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resItemProductionRegister)
	assert.Equal(t, int32(0), resItemProductionRegister.Error)
	assert.Equal(t, int32(1), resItemProductionRegister.Index)
}

// 测试历史站点相关消息
func testBatch67HistoricsiteMessages(t *testing.T) {
	reqHistoricsiteStaff := &dnfv1.ReqHistoricsiteStaff{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteStaff)
	assert.Equal(t, int32(0), reqHistoricsiteStaff.Error)
	assert.Equal(t, int32(1), reqHistoricsiteStaff.Index)
}

// 测试黑钻石相关消息
func testBatch67BlackDiamondMessages(t *testing.T) {
	resBlackDiamonGetReward := &dnfv1.ResBlackDiamonGetReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBlackDiamonGetReward)
	assert.Equal(t, int32(0), resBlackDiamonGetReward.Error)
	assert.Equal(t, int32(1), resBlackDiamonGetReward.Index)
}

// 测试公会聊天相关消息
func testBatch67GuildChatMessages(t *testing.T) {
	reqLoadGuildChat := &dnfv1.ReqLoadGuildChat{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqLoadGuildChat)
	assert.Equal(t, int32(0), reqLoadGuildChat.Error)
	assert.Equal(t, int32(1), reqLoadGuildChat.Index)
}

// 测试突袭相关消息
func testBatch67RaidMessages(t *testing.T) {
	ptRaidBuffInfo := &dnfv1.PtRaidBuffInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptRaidBuffInfo)
	assert.Equal(t, int32(0), ptRaidBuffInfo.Error)
	assert.Equal(t, int32(1), ptRaidBuffInfo.Index)
}

// 测试卡牌相关消息
func testBatch67CardMessages(t *testing.T) {
	reqCardCompose := &dnfv1.ReqCardCompose{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCardCompose)
	assert.Equal(t, int32(0), reqCardCompose.Error)
	assert.Equal(t, int32(1), reqCardCompose.Index)
}

// 测试书签相关消息
func testBatch67BookmarkMessages(t *testing.T) {
	reqDisjointBookmark := &dnfv1.ReqDisjointBookmark{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDisjointBookmark)
	assert.Equal(t, int32(0), reqDisjointBookmark.Error)
	assert.Equal(t, int32(1), reqDisjointBookmark.Index)
}
