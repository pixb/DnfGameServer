package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch71Migration 测试第71批迁移的消息类型
func TestBatch71Migration(t *testing.T) {
	t.Run("Syscmd", testBatch71SyscmdMessages)
	t.Run("Mail", testBatch71MailMessages)
	t.Run("Quest", testBatch71QuestMessages)
	t.Run("Crack", testBatch71CrackMessages)
	t.Run("Collection", testBatch71CollectionMessages)
	t.Run("Mentor", testBatch71MentorMessages)
	t.Run("Dungeon", testBatch71DungeonMessages)
	t.Run("Chat", testBatch71ChatMessages)
	t.Run("Adventure", testBatch71AdventureMessages)
	t.Run("Lottery", testBatch71LotteryMessages)
	t.Run("Auth", testBatch71AuthMessages)
	t.Run("Ranking", testBatch71RankingMessages)
	t.Run("Frame", testBatch71FrameMessages)
	t.Run("Pve", testBatch71PveMessages)
	t.Run("Character", testBatch71CharacterMessages)
	t.Run("Auction", testBatch71AuctionMessages)
	t.Run("Party", testBatch71PartyMessages)
	t.Run("Chivalry", testBatch71ChivalryMessages)
	t.Run("Team", testBatch71TeamMessages)
	t.Run("Notify", testBatch71NotifyMessages)
	t.Run("Toy", testBatch71ToyMessages)
	t.Run("Historicsite", testBatch71HistoricsiteMessages)
	t.Run("Exit", testBatch71ExitMessages)
	t.Run("Pvp", testBatch71PvpMessages)
	t.Run("Guild", testBatch71GuildMessages)
	t.Run("Content", testBatch71ContentMessages)
	t.Run("Pray", testBatch71PrayMessages)
	t.Run("AttackSquad", testBatch71AttackSquadMessages)
	t.Run("Minigame", testBatch71MinigameMessages)
	t.Run("AdventureUnion", testBatch71AdventureUnionMessages)
	t.Run("ChivalryUpdate", testBatch71ChivalryUpdateMessages)
	t.Run("AuctionPrice", testBatch71AuctionPriceMessages)
	t.Run("HistoricsiteBulletin", testBatch71HistoricsiteBulletinMessages)
	t.Run("GuildEmblem", testBatch71GuildEmblemMessages)
}

// 测试系统命令相关消息
func testBatch71SyscmdMessages(t *testing.T) {
	ptSyscmdCerashop := &dnfv1.PtSyscmdCerashop{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptSyscmdCerashop)
	assert.Equal(t, int32(0), ptSyscmdCerashop.Error)
	assert.Equal(t, int32(1), ptSyscmdCerashop.Index)
}

// 测试邮件相关消息
func testBatch71MailMessages(t *testing.T) {
	reqMailGet := &dnfv1.ReqMailGet{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMailGet)
	assert.Equal(t, int32(0), reqMailGet.Error)
	assert.Equal(t, int32(1), reqMailGet.Index)
}

// 测试任务相关消息
func testBatch71QuestMessages(t *testing.T) {
	resAcceptQuest := &dnfv1.ResAcceptQuest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAcceptQuest)
	assert.Equal(t, int32(0), resAcceptQuest.Error)
	assert.Equal(t, int32(1), resAcceptQuest.Index)
}

// 测试裂缝相关消息
func testBatch71CrackMessages(t *testing.T) {
	reqDetachCrack := &dnfv1.ReqDetachCrack{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqDetachCrack)
	assert.Equal(t, int32(0), reqDetachCrack.Error)
	assert.Equal(t, int32(1), reqDetachCrack.Index)
}

// 测试收集相关消息
func testBatch71CollectionMessages(t *testing.T) {
	resCollectionLoad := &dnfv1.ResCollectionLoad{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCollectionLoad)
	assert.Equal(t, int32(0), resCollectionLoad.Error)
	assert.Equal(t, int32(1), resCollectionLoad.Index)
}

// 测试导师相关消息
func testBatch71MentorMessages(t *testing.T) {
	ptMentorMessage := &dnfv1.PtMentorMessage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMentorMessage)
	assert.Equal(t, int32(0), ptMentorMessage.Error)
	assert.Equal(t, int32(1), ptMentorMessage.Index)
}

// 测试副本相关消息
func testBatch71DungeonMessages(t *testing.T) {
	reqStartDungeon := &dnfv1.ReqStartDungeon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqStartDungeon)
	assert.Equal(t, int32(0), reqStartDungeon.Error)
	assert.Equal(t, int32(1), reqStartDungeon.Index)
}

// 测试聊天相关消息
func testBatch71ChatMessages(t *testing.T) {
	sendChatTypesNoti := &dnfv1.SendChatTypesNoti{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, sendChatTypesNoti)
	assert.Equal(t, int32(0), sendChatTypesNoti.Error)
	assert.Equal(t, int32(1), sendChatTypesNoti.Index)
}

// 测试冒险相关消息
func testBatch71AdventureMessages(t *testing.T) {
	ptAdventureListItem := &dnfv1.PtAdventureListItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAdventureListItem)
	assert.Equal(t, int32(0), ptAdventureListItem.Error)
	assert.Equal(t, int32(1), ptAdventureListItem.Index)

	ptAdventureExpInfo := &dnfv1.PtAdventureExpInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptAdventureExpInfo)
	assert.Equal(t, int32(0), ptAdventureExpInfo.Error)
	assert.Equal(t, int32(1), ptAdventureExpInfo.Index)
}

// 测试抽奖相关消息
func testBatch71LotteryMessages(t *testing.T) {
	packLottery := &dnfv1.PackLottery{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, packLottery)
	assert.Equal(t, int32(0), packLottery.Error)
	assert.Equal(t, int32(1), packLottery.Index)
}

// 测试认证相关消息
func testBatch71AuthMessages(t *testing.T) {
	checkAuthTypesRes := &dnfv1.CheckAuthTypesRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, checkAuthTypesRes)
	assert.Equal(t, int32(0), checkAuthTypesRes.Error)
	assert.Equal(t, int32(1), checkAuthTypesRes.Index)
}

// 测试排名相关消息
func testBatch71RankingMessages(t *testing.T) {
	reqRankingDungeonDamageInfo := &dnfv1.ReqRankingDungeonDamageInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRankingDungeonDamageInfo)
	assert.Equal(t, int32(0), reqRankingDungeonDamageInfo.Error)
	assert.Equal(t, int32(1), reqRankingDungeonDamageInfo.Index)
}

// 测试帧相关消息
func testBatch71FrameMessages(t *testing.T) {
	frameDataTypesRes := &dnfv1.FrameDataTypesRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, frameDataTypesRes)
	assert.Equal(t, int32(0), frameDataTypesRes.Error)
	assert.Equal(t, int32(1), frameDataTypesRes.Index)
}

// 测试PVE相关消息
func testBatch71PveMessages(t *testing.T) {
	ptPveRoundInfo := &dnfv1.PtPveRoundInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPveRoundInfo)
	assert.Equal(t, int32(0), ptPveRoundInfo.Error)
	assert.Equal(t, int32(1), ptPveRoundInfo.Index)
}

// 测试角色相关消息
func testBatch71CharacterMessages(t *testing.T) {
	resCharacterSlotChange := &dnfv1.ResCharacterSlotChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCharacterSlotChange)
	assert.Equal(t, int32(0), resCharacterSlotChange.Error)
	assert.Equal(t, int32(1), resCharacterSlotChange.Index)
}

// 测试拍卖相关消息
func testBatch71AuctionMessages(t *testing.T) {
	resAuctionCategoryList := &dnfv1.ResAuctionCategoryList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAuctionCategoryList)
	assert.Equal(t, int32(0), resAuctionCategoryList.Error)
	assert.Equal(t, int32(1), resAuctionCategoryList.Index)

	reqAuctionItemPriceList := &dnfv1.ReqAuctionItemPriceList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAuctionItemPriceList)
	assert.Equal(t, int32(0), reqAuctionItemPriceList.Error)
	assert.Equal(t, int32(1), reqAuctionItemPriceList.Index)
}

// 测试队伍相关消息
func testBatch71PartyMessages(t *testing.T) {
	reqPartyUpdate := &dnfv1.ReqPartyUpdate{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPartyUpdate)
	assert.Equal(t, int32(0), reqPartyUpdate.Error)
	assert.Equal(t, int32(1), reqPartyUpdate.Index)

	resPartyCancelInvitation := &dnfv1.ResPartyCancelInvitation{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resPartyCancelInvitation)
	assert.Equal(t, int32(0), resPartyCancelInvitation.Error)
	assert.Equal(t, int32(1), resPartyCancelInvitation.Index)
}

// 测试骑士相关消息
func testBatch71ChivalryMessages(t *testing.T) {
	enumChivalryMission := &dnfv1.EnumChivalryMission{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, enumChivalryMission)
	assert.Equal(t, int32(0), enumChivalryMission.Error)
	assert.Equal(t, int32(1), enumChivalryMission.Index)
}

// 测试队伍变更相关消息
func testBatch71TeamMessages(t *testing.T) {
	resTeamChange := &dnfv1.ResTeamChange{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resTeamChange)
	assert.Equal(t, int32(0), resTeamChange.Error)
	assert.Equal(t, int32(1), resTeamChange.Index)
}

// 测试通知相关消息
func testBatch71NotifyMessages(t *testing.T) {
	resNotifyInvalidUser := &dnfv1.ResNotifyInvalidUser{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resNotifyInvalidUser)
	assert.Equal(t, int32(0), resNotifyInvalidUser.Error)
	assert.Equal(t, int32(1), resNotifyInvalidUser.Index)
}

// 测试玩具相关消息
func testBatch71ToyMessages(t *testing.T) {
	ptToyBillingVerify := &dnfv1.PtToyBillingVerify{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptToyBillingVerify)
	assert.Equal(t, int32(0), ptToyBillingVerify.Error)
	assert.Equal(t, int32(1), ptToyBillingVerify.Index)
}

// 测试历史站点相关消息
func testBatch71HistoricsiteMessages(t *testing.T) {
	reqHistoricsiteApply := &dnfv1.ReqHistoricsiteApply{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqHistoricsiteApply)
	assert.Equal(t, int32(0), reqHistoricsiteApply.Error)
	assert.Equal(t, int32(1), reqHistoricsiteApply.Index)

	resHistoricsiteBulletin := &dnfv1.ResHistoricsiteBulletin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteBulletin)
	assert.Equal(t, int32(0), resHistoricsiteBulletin.Error)
	assert.Equal(t, int32(1), resHistoricsiteBulletin.Index)
}

// 测试退出相关消息
func testBatch71ExitMessages(t *testing.T) {
	exitRes := &dnfv1.ExitRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, exitRes)
	assert.Equal(t, int32(0), exitRes.Error)
	assert.Equal(t, int32(1), exitRes.Index)
}

// 测试PVP相关消息
func testBatch71PvpMessages(t *testing.T) {
	reqCustomPvpRoomSetting := &dnfv1.ReqCustomPvpRoomSetting{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCustomPvpRoomSetting)
	assert.Equal(t, int32(0), reqCustomPvpRoomSetting.Error)
	assert.Equal(t, int32(1), reqCustomPvpRoomSetting.Index)
}

// 测试公会相关消息
func testBatch71GuildMessages(t *testing.T) {
	resGuildEmblemPaletteBuy := &dnfv1.ResGuildEmblemPaletteBuy{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildEmblemPaletteBuy)
	assert.Equal(t, int32(0), resGuildEmblemPaletteBuy.Error)
	assert.Equal(t, int32(1), resGuildEmblemPaletteBuy.Index)

	resGuildEmblemBuy := &dnfv1.ResGuildEmblemBuy{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildEmblemBuy)
	assert.Equal(t, int32(0), resGuildEmblemBuy.Error)
	assert.Equal(t, int32(1), resGuildEmblemBuy.Index)
}

// 测试内容相关消息
func testBatch71ContentMessages(t *testing.T) {
	contentStream := &dnfv1.ContentStream{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, contentStream)
	assert.Equal(t, int32(0), contentStream.Error)
	assert.Equal(t, int32(1), contentStream.Index)
}

// 测试祈祷相关消息
func testBatch71PrayMessages(t *testing.T) {
	ptPrayMaterialInfo := &dnfv1.PtPrayMaterialInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPrayMaterialInfo)
	assert.Equal(t, int32(0), ptPrayMaterialInfo.Error)
	assert.Equal(t, int32(1), ptPrayMaterialInfo.Index)
}

// 测试攻击小队相关消息
func testBatch71AttackSquadMessages(t *testing.T) {
	reqAttackSquadList := &dnfv1.ReqAttackSquadList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadList)
	assert.Equal(t, int32(0), reqAttackSquadList.Error)
	assert.Equal(t, int32(1), reqAttackSquadList.Index)
}

// 测试小游戏相关消息
func testBatch71MinigameMessages(t *testing.T) {
	reqMinigame77valentineReward := &dnfv1.ReqMinigame77valentineReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqMinigame77valentineReward)
	assert.Equal(t, int32(0), reqMinigame77valentineReward.Error)
	assert.Equal(t, int32(1), reqMinigame77valentineReward.Index)
}

// 测试冒险联盟相关消息
func testBatch71AdventureUnionMessages(t *testing.T) {
	reqAdventureUnionShowCheckOther := &dnfv1.ReqAdventureUnionShowCheckOther{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionShowCheckOther)
	assert.Equal(t, int32(0), reqAdventureUnionShowCheckOther.Error)
	assert.Equal(t, int32(1), reqAdventureUnionShowCheckOther.Index)
}

// 测试骑士更新相关消息
func testBatch71ChivalryUpdateMessages(t *testing.T) {
	reqChivalryUpdateMission := &dnfv1.ReqChivalryUpdateMission{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqChivalryUpdateMission)
	assert.Equal(t, int32(0), reqChivalryUpdateMission.Error)
	assert.Equal(t, int32(1), reqChivalryUpdateMission.Index)
}

// 测试拍卖价格相关消息
func testBatch71AuctionPriceMessages(t *testing.T) {
	reqAuctionItemPriceList := &dnfv1.ReqAuctionItemPriceList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAuctionItemPriceList)
	assert.Equal(t, int32(0), reqAuctionItemPriceList.Error)
	assert.Equal(t, int32(1), reqAuctionItemPriceList.Index)
}

// 测试历史站点公告相关消息
func testBatch71HistoricsiteBulletinMessages(t *testing.T) {
	resHistoricsiteBulletin := &dnfv1.ResHistoricsiteBulletin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsiteBulletin)
	assert.Equal(t, int32(0), resHistoricsiteBulletin.Error)
	assert.Equal(t, int32(1), resHistoricsiteBulletin.Index)
}

// 测试公会徽章相关消息
func testBatch71GuildEmblemMessages(t *testing.T) {
	resGuildEmblemBuy := &dnfv1.ResGuildEmblemBuy{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildEmblemBuy)
	assert.Equal(t, int32(0), resGuildEmblemBuy.Error)
	assert.Equal(t, int32(1), resGuildEmblemBuy.Index)
}
