package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch57Migration 测试第57批迁移的消息类型
func TestBatch57Migration(t *testing.T) {
	t.Run("BattleLeague", testBattleLeagueMessages)
	t.Run("BoardGame", testBoardGameMessages)
	t.Run("BookmarkAndBuff", testBookmarkAndBuffMessages)
	t.Run("CardAndShop", testCardAndShopMessages)
	t.Run("Champion", testChampionMessages)
	t.Run("TeamAndChannel", testTeamAndChannelMessages)
	t.Run("Character", testCharacterMessages)
	t.Run("Charguid", testCharguidMessages)
	t.Run("ChatAndChivalry", testChatAndChivalryMessages)
}

// 测试战斗联盟相关消息
func testBattleLeagueMessages(t *testing.T) {
	// 测试BattleLeagueRewardInfo
	battleLeagueRewardInfo := &dnfv1.BattleLeagueRewardInfo{
		Type:  1,
		Id:    1001,
		Count: 10,
	}
	assert.NotNil(t, battleLeagueRewardInfo)
	assert.Equal(t, int32(1), battleLeagueRewardInfo.Type)
	assert.Equal(t, int32(1001), battleLeagueRewardInfo.Id)
	assert.Equal(t, int32(10), battleLeagueRewardInfo.Count)

	// 测试BattleleagueTransitionCard
	battleleagueTransitionCard := &dnfv1.BattleleagueTransitionCard{
		Charguid: 1234567890,
		Items:    []int32{1, 2, 3},
		Buffs:    []int32{4, 5, 6},
		Pvepoint: 1000,
		Pverewards: []*dnfv1.BattleLeagueRewardInfo{
			{
				Type:  2,
				Id:    2001,
				Count: 5,
			},
		},
	}
	assert.NotNil(t, battleleagueTransitionCard)
	assert.Equal(t, uint64(1234567890), battleleagueTransitionCard.Charguid)
	assert.Len(t, battleleagueTransitionCard.Items, 3)
	assert.Len(t, battleleagueTransitionCard.Buffs, 3)
	assert.Equal(t, int32(1000), battleleagueTransitionCard.Pvepoint)
	assert.Len(t, battleleagueTransitionCard.Pverewards, 1)

	// 测试BattlePassInfo
	battlePassInfo := &dnfv1.BattlePassInfo{
		SeasonpassFlag: 1,
	}
	assert.NotNil(t, battlePassInfo)
	assert.Equal(t, int32(1), battlePassInfo.SeasonpassFlag)

	// 测试Blacklist
	blacklist := &dnfv1.Blacklist{
		Charguid:       1234567890,
		Name:           "TestUser",
		Time:           1234567890,
		Level:          95,
		Job:            1,
		Growtype:       2,
		Secgrowtype:    3,
		Characterframe: 1,
	}
	assert.NotNil(t, blacklist)
	assert.Equal(t, uint64(1234567890), blacklist.Charguid)
	assert.Equal(t, "TestUser", blacklist.Name)
	assert.Equal(t, int64(1234567890), blacklist.Time)
	assert.Equal(t, int32(95), blacklist.Level)
}

// 测试棋盘游戏相关消息
func testBoardGameMessages(t *testing.T) {
	// 测试BoardGameInfo
	boardGameInfo := &dnfv1.BoardGameInfo{
		Tiles:    []*dnfv1.BoardTileInfo{},
		Users:    []*dnfv1.BoardUserContentsInfo{},
		Sizex:    10,
		Sizey:    10,
		Playtime: 600,
	}
	assert.NotNil(t, boardGameInfo)
	assert.Len(t, boardGameInfo.Tiles, 0)
	assert.Len(t, boardGameInfo.Users, 0)
	assert.Equal(t, int32(10), boardGameInfo.Sizex)
	assert.Equal(t, int32(10), boardGameInfo.Sizey)
	assert.Equal(t, int32(600), boardGameInfo.Playtime)

	// 测试BoardMoveResult
	boardMoveResult := &dnfv1.BoardMoveResult{
		Result: 1,
		Posx:   5,
		Posy:   5,
	}
	assert.NotNil(t, boardMoveResult)
	assert.Equal(t, int32(1), boardMoveResult.Result)
	assert.Equal(t, int32(5), boardMoveResult.Posx)
	assert.Equal(t, int32(5), boardMoveResult.Posy)

	// 测试BoardInfo
	boardInfo := &dnfv1.BoardInfo{
		Score:      1000,
		Msg:        "Test message",
		Noteguid:   1234567890,
		Likecount:  5,
		Hatecount:  1,
		Registtime: 1234567890,
		Myreaction: 1,
	}
	assert.NotNil(t, boardInfo)
	assert.Equal(t, int32(1000), boardInfo.Score)
	assert.Equal(t, "Test message", boardInfo.Msg)
	assert.Equal(t, uint64(1234567890), boardInfo.Noteguid)
	assert.Equal(t, int32(5), boardInfo.Likecount)
	assert.Equal(t, int32(1), boardInfo.Hatecount)
	assert.Equal(t, int64(1234567890), boardInfo.Registtime)
	assert.Equal(t, int32(1), boardInfo.Myreaction)
}

// 测试书签和Buff相关消息
func testBookmarkAndBuffMessages(t *testing.T) {
	// 测试BookmarkItem
	bookmarkItem := &dnfv1.BookmarkItem{
		Index: 1,
		Count: 1,
		Bind:  true,
	}
	assert.NotNil(t, bookmarkItem)
	assert.Equal(t, int32(1), bookmarkItem.Index)
	assert.Equal(t, int32(1), bookmarkItem.Count)
	assert.Equal(t, true, bookmarkItem.Bind)

	// 测试Buff
	buff := &dnfv1.Buff{
		Buff: 1001,
	}
	assert.NotNil(t, buff)
	assert.Equal(t, int32(1001), buff.Buff)

	// 测试BuffList
	buffList := &dnfv1.BuffList{
		Time: 1234567890,
	}
	assert.NotNil(t, buffList)
	assert.Equal(t, int64(1234567890), buffList.Time)
}

// 测试卡片和商店相关消息
func testCardAndShopMessages(t *testing.T) {
	// 测试CardAttach
	cardAttach := &dnfv1.CardAttach{
		Index: 1,
	}
	assert.NotNil(t, cardAttach)
	assert.Equal(t, int32(1), cardAttach.Index)

	// 测试CardCompose
	cardCompose := &dnfv1.CardCompose{
		Index: 1,
		Count: 5,
		Bind:  true,
	}
	assert.NotNil(t, cardCompose)
	assert.Equal(t, int32(1), cardCompose.Index)
	assert.Equal(t, int32(5), cardCompose.Count)
	assert.Equal(t, true, cardCompose.Bind)

	// 测试CeraShopBuy
	ceraShopBuy := &dnfv1.CeraShopBuy{
		Goodsid: 1001,
		Count:   5,
		Type:    1,
	}
	assert.NotNil(t, ceraShopBuy)
	assert.Equal(t, int32(1001), ceraShopBuy.Goodsid)
	assert.Equal(t, int32(5), ceraShopBuy.Count)
	assert.Equal(t, int32(1), ceraShopBuy.Type)
}

// 测试冠军相关消息
func testChampionMessages(t *testing.T) {
	// 测试ChampionStageInfo
	championStageInfo := &dnfv1.ChampionStageInfo{
		Guid:       1234567890,
		Posx:       100,
		Posy:       200,
		Die:        false,
		Objectguid: 1234567891,
		Skill:      1001,
		Value:      100,
	}
	assert.NotNil(t, championStageInfo)
	assert.Equal(t, uint64(1234567890), championStageInfo.Guid)
	assert.Equal(t, int32(100), championStageInfo.Posx)
	assert.Equal(t, int32(200), championStageInfo.Posy)
	assert.Equal(t, false, championStageInfo.Die)
	assert.Equal(t, uint64(1234567891), championStageInfo.Objectguid)
	assert.Equal(t, int32(1001), championStageInfo.Skill)
	assert.Equal(t, int32(100), championStageInfo.Value)

	// 测试ChampionInfo
	championInfo := &dnfv1.ChampionInfo{
		Appearance: true,
		Detail:     []*dnfv1.ChampionStageInfo{championStageInfo},
	}
	assert.NotNil(t, championInfo)
	assert.Equal(t, true, championInfo.Appearance)
	assert.Len(t, championInfo.Detail, 1)
}

// 测试队伍和频道相关消息
func testTeamAndChannelMessages(t *testing.T) {
	// 测试ChangeBuff
	changeBuff := &dnfv1.ChangeBuff{
		Time:    1234567890,
		Endtime: 1234567890 + 3600,
		Type:    1,
		Index:   1,
		Values:  []int32{100, 200, 300},
	}
	assert.NotNil(t, changeBuff)
	assert.Equal(t, int64(1234567890), changeBuff.Time)
	assert.Equal(t, int64(1234567890+3600), changeBuff.Endtime)
	assert.Equal(t, int32(1), changeBuff.Type)
	assert.Equal(t, int32(1), changeBuff.Index)
	assert.Len(t, changeBuff.Values, 3)

	// 测试ChangeTeamUserInfo
	changeTeamUserInfo := &dnfv1.ChangeTeamUserInfo{
		Charguid: 1234567890,
		Teamtype: 1,
	}
	assert.NotNil(t, changeTeamUserInfo)
	assert.Equal(t, uint64(1234567890), changeTeamUserInfo.Charguid)
	assert.Equal(t, int32(1), changeTeamUserInfo.Teamtype)

	// 测试CharacterEquipOnlyIndex
	characterEquipOnlyIndex := &dnfv1.CharacterEquipOnlyIndex{}
	assert.NotNil(t, characterEquipOnlyIndex)
}

// 测试角色相关消息
func testCharacterMessages(t *testing.T) {
	// 测试Character
	character := &dnfv1.Character{
		CharGuid:       1234567890,
		LastLogout:     1234567890,
		GrowType:       1,
		SecGrowType:    2,
		Job:            3,
		Level:          95,
		Name:           "Test Character",
		Fatigue:        156,
		EquipScore:     10000,
		CharacterFrame: 1,
		ChangeName:     false,
	}
	assert.NotNil(t, character)
	assert.Equal(t, int64(1234567890), character.CharGuid)
	assert.Equal(t, "Test Character", character.Name)
	assert.Equal(t, int32(95), character.Level)
	assert.Equal(t, int32(3), character.Job)

	// 测试CharacterGuid
	characterGuid := &dnfv1.CharacterGuid{
		Charguid: 1234567890,
		Type:     1,
		Posx:     100,
		Posy:     200,
	}
	assert.NotNil(t, characterGuid)
	assert.Equal(t, uint64(1234567890), characterGuid.Charguid)
	assert.Equal(t, int32(1), characterGuid.Type)
	assert.Equal(t, int32(100), characterGuid.Posx)
	assert.Equal(t, int32(200), characterGuid.Posy)
}

// 测试Charguid相关消息
func testCharguidMessages(t *testing.T) {
	// 测试CharguidEntranceItem
	charguidEntranceItem := &dnfv1.CharguidEntranceItem{
		Charguid: 1234567890,
	}
	assert.NotNil(t, charguidEntranceItem)
	assert.Equal(t, uint64(1234567890), charguidEntranceItem.Charguid)

	// 测试CharguidFatigue
	charguidFatigue := &dnfv1.CharguidFatigue{
		Charguid: 1234567890,
		Fatigue:  100,
	}
	assert.NotNil(t, charguidFatigue)
	assert.Equal(t, uint64(1234567890), charguidFatigue.Charguid)
	assert.Equal(t, int32(100), charguidFatigue.Fatigue)

	// 测试CharguidTicket
	charguidTicket := &dnfv1.CharguidTicket{
		Charguid: 1234567890,
	}
	assert.NotNil(t, charguidTicket)
	assert.Equal(t, uint64(1234567890), charguidTicket.Charguid)
}

// 测试聊天和骑士精神相关消息
func testChatAndChivalryMessages(t *testing.T) {
	// 测试Chat
	chat := &dnfv1.Chat{
		Error:    0,
		Type:     1,
		Subtype:  2,
		Charguid: 1234567890,
	}
	assert.NotNil(t, chat)
	assert.Equal(t, int32(0), chat.Error)
	assert.Equal(t, int32(1), chat.Type)
	assert.Equal(t, int32(2), chat.Subtype)
	assert.Equal(t, uint64(1234567890), chat.Charguid)

	// 测试ChatChannel
	chatChannel := &dnfv1.ChatChannel{
		ChannelId:    1,
		Name:         "General",
		Type:         1,
		MaxUsers:     100,
		CurrentUsers: 50,
	}
	assert.NotNil(t, chatChannel)
	assert.Equal(t, int32(1), chatChannel.ChannelId)
	assert.Equal(t, "General", chatChannel.Name)
	assert.Equal(t, int32(1), chatChannel.Type)
	assert.Equal(t, int32(100), chatChannel.MaxUsers)
	assert.Equal(t, int32(50), chatChannel.CurrentUsers)

	// 测试ChatSync
	chatSync := &dnfv1.ChatSync{
		Index: 1234567890,
		Type:  1,
	}
	assert.NotNil(t, chatSync)
	assert.Equal(t, uint64(1234567890), chatSync.Index)
	assert.Equal(t, int32(1), chatSync.Type)

	// 测试ChatSyncInfo
	chatSyncInfo := &dnfv1.ChatSyncInfo{
		Lastindex:  1234567890,
		Totalcount: 10,
		Type:       1,
		Chatmsg:    []*dnfv1.Chat{},
	}
	assert.NotNil(t, chatSyncInfo)
	assert.Equal(t, uint64(1234567890), chatSyncInfo.Lastindex)
	assert.Equal(t, uint64(10), chatSyncInfo.Totalcount)
	assert.Equal(t, int32(1), chatSyncInfo.Type)
	assert.Len(t, chatSyncInfo.Chatmsg, 0)

	// 测试Chivalry
	chivalry := &dnfv1.Chivalry{
		Index: 1,
		Value: 100,
	}
	assert.NotNil(t, chivalry)
	assert.Equal(t, int32(1), chivalry.Index)
	assert.Equal(t, int32(100), chivalry.Value)
}
