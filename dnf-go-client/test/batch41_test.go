package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch41BattleleagueRewardInfo(t *testing.T) {
	reward := &dnfv1.BattleleagueRewardInfo{
		Index: 1,
		Count: 10,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal BattleleagueRewardInfo: %v", err)
	}

	parsed := &dnfv1.BattleleagueRewardInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattleleagueRewardInfo: %v", err)
	}

	if parsed.Index != reward.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, reward.Index)
	}

	if parsed.Count != reward.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, reward.Count)
	}
}

func TestBatch41BattleleagueTransitionCard(t *testing.T) {
	card := &dnfv1.BattleleagueTransitionCard{
		Charguid: 123456789,
		Items:    []int32{1, 2, 3},
		Buffs:    []int32{4, 5, 6},
		Buff: []*dnfv1.BattleleagueBuff{
			{Index: 1, Customvalue: 100},
		},
		Pvepoint: 500,
		Pverewards: []*dnfv1.BattleLeagueRewardInfo{
			{Type: 1, Id: 100, Count: 10},
		},
	}

	data, err := proto.Marshal(card)
	if err != nil {
		t.Fatalf("Failed to marshal BattleleagueTransitionCard: %v", err)
	}

	parsed := &dnfv1.BattleleagueTransitionCard{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattleleagueTransitionCard: %v", err)
	}

	if parsed.Charguid != card.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, card.Charguid)
	}

	if len(parsed.Items) != len(card.Items) {
		t.Errorf("Items length mismatch: got %d, want %d", len(parsed.Items), len(card.Items))
	}
}

func TestBatch41BattlePassInfo(t *testing.T) {
	info := &dnfv1.BattlePassInfo{
		SeasonpassFlag: 1,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal BattlePassInfo: %v", err)
	}

	parsed := &dnfv1.BattlePassInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattlePassInfo: %v", err)
	}

	if parsed.SeasonpassFlag != info.SeasonpassFlag {
		t.Errorf("SeasonpassFlag mismatch: got %d, want %d", parsed.SeasonpassFlag, info.SeasonpassFlag)
	}
}

func TestBatch41Blacklist(t *testing.T) {
	blacklist := &dnfv1.Blacklist{
		Charguid:       123456789,
		Name:           "test_user",
		Time:           1234567890,
		Level:          50,
		Job:            1,
		Growtype:       1,
		Secgrowtype:    1,
		Characterframe: 1,
	}

	data, err := proto.Marshal(blacklist)
	if err != nil {
		t.Fatalf("Failed to marshal Blacklist: %v", err)
	}

	parsed := &dnfv1.Blacklist{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal Blacklist: %v", err)
	}

	if parsed.Charguid != blacklist.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, blacklist.Charguid)
	}

	if parsed.Name != blacklist.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, blacklist.Name)
	}
}

func TestBatch41BoardGameInfo(t *testing.T) {
	info := &dnfv1.BoardGameInfo{
		Tiles: []*dnfv1.BoardTileInfo{
			{Posx: 1, Posy: 1, Type: 1, Status: 1},
		},
		Users: []*dnfv1.BoardUserContentsInfo{
			{Charguid: 123456789, Posx: 1, Posy: 1},
		},
		Sizex:    10,
		Sizey:    10,
		Playtime: 300,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal BoardGameInfo: %v", err)
	}

	parsed := &dnfv1.BoardGameInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BoardGameInfo: %v", err)
	}

	if parsed.Sizex != info.Sizex {
		t.Errorf("Sizex mismatch: got %d, want %d", parsed.Sizex, info.Sizex)
	}

	if parsed.Sizey != info.Sizey {
		t.Errorf("Sizey mismatch: got %d, want %d", parsed.Sizey, info.Sizey)
	}
}

func TestBatch41BoardMoveResult(t *testing.T) {
	result := &dnfv1.BoardMoveResult{
		Result: 1,
		Posx:   5,
		Posy:   5,
	}

	data, err := proto.Marshal(result)
	if err != nil {
		t.Fatalf("Failed to marshal BoardMoveResult: %v", err)
	}

	parsed := &dnfv1.BoardMoveResult{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BoardMoveResult: %v", err)
	}

	if parsed.Result != result.Result {
		t.Errorf("Result mismatch: got %d, want %d", parsed.Result, result.Result)
	}

	if parsed.Posx != result.Posx {
		t.Errorf("Posx mismatch: got %d, want %d", parsed.Posx, result.Posx)
	}
}

func TestBatch41BoardInfo(t *testing.T) {
	info := &dnfv1.BoardInfo{
		Score:      100,
		Msg:        "test message",
		Noteguid:   123456789,
		Charinfo:   &dnfv1.Character{CharGuid: 123456789, Name: "test"},
		Likecount:  10,
		Hatecount:  5,
		Registtime: 1234567890,
		Myreaction: 1,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal BoardInfo: %v", err)
	}

	parsed := &dnfv1.BoardInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BoardInfo: %v", err)
	}

	if parsed.Score != info.Score {
		t.Errorf("Score mismatch: got %d, want %d", parsed.Score, info.Score)
	}

	if parsed.Msg != info.Msg {
		t.Errorf("Msg mismatch: got %s, want %s", parsed.Msg, info.Msg)
	}
}

func TestBatch41BoardTileInfo(t *testing.T) {
	tile := &dnfv1.BoardTileInfo{
		Posx:   1,
		Posy:   1,
		Type:   1,
		Status: 1,
		Excavation: &dnfv1.ExcavationBoardTileInfo{
			Index: 1,
			Value: 100,
		},
	}

	data, err := proto.Marshal(tile)
	if err != nil {
		t.Fatalf("Failed to marshal BoardTileInfo: %v", err)
	}

	parsed := &dnfv1.BoardTileInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BoardTileInfo: %v", err)
	}

	if parsed.Posx != tile.Posx {
		t.Errorf("Posx mismatch: got %d, want %d", parsed.Posx, tile.Posx)
	}

	if parsed.Posy != tile.Posy {
		t.Errorf("Posy mismatch: got %d, want %d", parsed.Posy, tile.Posy)
	}
}

func TestBatch41BoardUserContentsInfo(t *testing.T) {
	user := &dnfv1.BoardUserContentsInfo{
		Charguid: 123456789,
		Posx:     1,
		Posy:     1,
		Hp:       100,
		Gold:     1000,
		Key:      5,
		Relic:    3,
	}

	data, err := proto.Marshal(user)
	if err != nil {
		t.Fatalf("Failed to marshal BoardUserContentsInfo: %v", err)
	}

	parsed := &dnfv1.BoardUserContentsInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BoardUserContentsInfo: %v", err)
	}

	if parsed.Charguid != user.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, user.Charguid)
	}

	if parsed.Hp != user.Hp {
		t.Errorf("Hp mismatch: got %d, want %d", parsed.Hp, user.Hp)
	}
}

func TestBatch41BoardUserMinimumInfo(t *testing.T) {
	user := &dnfv1.BoardUserMinimumInfo{
		Charguid:    123456789,
		Playerid:    1,
		Growtype:    1,
		Secgrowtype: 1,
		Job:         1,
		Level:       50,
		Name:        "test_user",
		Teamtype:    dnfv1.TeamType(1),
	}

	data, err := proto.Marshal(user)
	if err != nil {
		t.Fatalf("Failed to marshal BoardUserMinimumInfo: %v", err)
	}

	parsed := &dnfv1.BoardUserMinimumInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BoardUserMinimumInfo: %v", err)
	}

	if parsed.Charguid != user.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, user.Charguid)
	}

	if parsed.Name != user.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, user.Name)
	}
}

func TestBatch41BookmarkItem(t *testing.T) {
	item := &dnfv1.BookmarkItem{
		Index: 1,
		Count: 10,
		Bind:  true,
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal BookmarkItem: %v", err)
	}

	parsed := &dnfv1.BookmarkItem{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BookmarkItem: %v", err)
	}

	if parsed.Index != item.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, item.Index)
	}

	if parsed.Count != item.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, item.Count)
	}

	if parsed.Bind != item.Bind {
		t.Errorf("Bind mismatch: got %v, want %v", parsed.Bind, item.Bind)
	}
}

func TestBatch41Buff(t *testing.T) {
	buff := &dnfv1.Buff{
		Buff: 100,
	}

	data, err := proto.Marshal(buff)
	if err != nil {
		t.Fatalf("Failed to marshal Buff: %v", err)
	}

	parsed := &dnfv1.Buff{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal Buff: %v", err)
	}

	if parsed.Buff != buff.Buff {
		t.Errorf("Buff mismatch: got %d, want %d", parsed.Buff, buff.Buff)
	}
}

func TestBatch41BuffList(t *testing.T) {
	list := &dnfv1.BuffList{
		Time: 1234567890,
		Appendages: []*dnfv1.SystemBuffAppendage{
			{Index: 1, Endtime: 1234567900, Values: []int32{100}},
		},
	}

	data, err := proto.Marshal(list)
	if err != nil {
		t.Fatalf("Failed to marshal BuffList: %v", err)
	}

	parsed := &dnfv1.BuffList{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BuffList: %v", err)
	}

	if parsed.Time != list.Time {
		t.Errorf("Time mismatch: got %d, want %d", parsed.Time, list.Time)
	}

	if len(parsed.Appendages) != len(list.Appendages) {
		t.Errorf("Appendages length mismatch: got %d, want %d", len(parsed.Appendages), len(list.Appendages))
	}
}

func TestBatch41CardAttach(t *testing.T) {
	card := &dnfv1.CardAttach{
		Index: 1,
	}

	data, err := proto.Marshal(card)
	if err != nil {
		t.Fatalf("Failed to marshal CardAttach: %v", err)
	}

	parsed := &dnfv1.CardAttach{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CardAttach: %v", err)
	}

	if parsed.Index != card.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, card.Index)
	}
}

func TestBatch41CardCompose(t *testing.T) {
	card := &dnfv1.CardCompose{
		Index: 1,
		Count: 10,
		Bind:  true,
	}

	data, err := proto.Marshal(card)
	if err != nil {
		t.Fatalf("Failed to marshal CardCompose: %v", err)
	}

	parsed := &dnfv1.CardCompose{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CardCompose: %v", err)
	}

	if parsed.Index != card.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, card.Index)
	}

	if parsed.Count != card.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, card.Count)
	}

	if parsed.Bind != card.Bind {
		t.Errorf("Bind mismatch: got %v, want %v", parsed.Bind, card.Bind)
	}
}

func TestBatch41CeraShopBuy(t *testing.T) {
	buy := &dnfv1.CeraShopBuy{
		Goodsid: 1,
		Count:   10,
		Type:    1,
	}

	data, err := proto.Marshal(buy)
	if err != nil {
		t.Fatalf("Failed to marshal CeraShopBuy: %v", err)
	}

	parsed := &dnfv1.CeraShopBuy{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CeraShopBuy: %v", err)
	}

	if parsed.Goodsid != buy.Goodsid {
		t.Errorf("Goodsid mismatch: got %d, want %d", parsed.Goodsid, buy.Goodsid)
	}

	if parsed.Count != buy.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, buy.Count)
	}
}

func TestBatch41CeraShopInfo(t *testing.T) {
	info := &dnfv1.CeraShopInfo{
		Buy: []*dnfv1.ShopBuyCount{
			{Goodsid: 1, Count: 10},
		},
		Reset_: []*dnfv1.ShopTabReset{
			{Shopid: 1, Tab: 1, Count: 5},
		},
		Group: []*dnfv1.ShopGroupReset{
			{Index: 1, Count: 3},
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal CeraShopInfo: %v", err)
	}

	parsed := &dnfv1.CeraShopInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CeraShopInfo: %v", err)
	}

	if len(parsed.Buy) != len(info.Buy) {
		t.Errorf("Buy length mismatch: got %d, want %d", len(parsed.Buy), len(info.Buy))
	}

	if len(parsed.Reset_) != len(info.Reset_) {
		t.Errorf("Reset length mismatch: got %d, want %d", len(parsed.Reset_), len(info.Reset_))
	}

	if len(parsed.Group) != len(info.Group) {
		t.Errorf("Group length mismatch: got %d, want %d", len(parsed.Group), len(info.Group))
	}
}

func TestBatch41CeraShopMileage(t *testing.T) {
	mileage := &dnfv1.CeraShopMileage{
		Index: 1,
		Value: 1000,
	}

	data, err := proto.Marshal(mileage)
	if err != nil {
		t.Fatalf("Failed to marshal CeraShopMileage: %v", err)
	}

	parsed := &dnfv1.CeraShopMileage{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CeraShopMileage: %v", err)
	}

	if parsed.Index != mileage.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, mileage.Index)
	}

	if parsed.Value != mileage.Value {
		t.Errorf("Value mismatch: got %d, want %d", parsed.Value, mileage.Value)
	}
}

func TestBatch41ChampionChange(t *testing.T) {
	change := &dnfv1.ChampionChange{
		Type:           1,
		Guid:           123456789,
		Objectguid:     987654321,
		CurrentMapGuid: 111,
	}

	data, err := proto.Marshal(change)
	if err != nil {
		t.Fatalf("Failed to marshal ChampionChange: %v", err)
	}

	parsed := &dnfv1.ChampionChange{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChampionChange: %v", err)
	}

	if parsed.Type != change.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsed.Type, change.Type)
	}

	if parsed.Guid != change.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, change.Guid)
	}
}

func TestBatch41ChampionInfo(t *testing.T) {
	info := &dnfv1.ChampionInfo{
		Appearance: true,
		Detail: []*dnfv1.ChampionStageInfo{
			{Guid: 123456789, Posx: 1, Posy: 1},
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal ChampionInfo: %v", err)
	}

	parsed := &dnfv1.ChampionInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChampionInfo: %v", err)
	}

	if parsed.Appearance != info.Appearance {
		t.Errorf("Appearance mismatch: got %v, want %v", parsed.Appearance, info.Appearance)
	}

	if len(parsed.Detail) != len(info.Detail) {
		t.Errorf("Detail length mismatch: got %d, want %d", len(parsed.Detail), len(info.Detail))
	}
}

func TestBatch41ChampionStageInfo(t *testing.T) {
	stage := &dnfv1.ChampionStageInfo{
		Guid:       123456789,
		Posx:       1,
		Posy:       1,
		Die:        false,
		Objectguid: 987654321,
		Skill:      1,
		Value:      100,
	}

	data, err := proto.Marshal(stage)
	if err != nil {
		t.Fatalf("Failed to marshal ChampionStageInfo: %v", err)
	}

	parsed := &dnfv1.ChampionStageInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChampionStageInfo: %v", err)
	}

	if parsed.Guid != stage.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, stage.Guid)
	}

	if parsed.Posx != stage.Posx {
		t.Errorf("Posx mismatch: got %d, want %d", parsed.Posx, stage.Posx)
	}
}

func TestBatch41ChangeBuff(t *testing.T) {
	buff := &dnfv1.ChangeBuff{
		Time:    1234567890,
		Endtime: 1234567900,
		Type:    1,
		Index:   1,
		Values:  []int32{100, 200, 300},
	}

	data, err := proto.Marshal(buff)
	if err != nil {
		t.Fatalf("Failed to marshal ChangeBuff: %v", err)
	}

	parsed := &dnfv1.ChangeBuff{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChangeBuff: %v", err)
	}

	if parsed.Time != buff.Time {
		t.Errorf("Time mismatch: got %d, want %d", parsed.Time, buff.Time)
	}

	if parsed.Endtime != buff.Endtime {
		t.Errorf("Endtime mismatch: got %d, want %d", parsed.Endtime, buff.Endtime)
	}

	if len(parsed.Values) != len(buff.Values) {
		t.Errorf("Values length mismatch: got %d, want %d", len(parsed.Values), len(buff.Values))
	}
}

func TestBatch41ChangeTeamUserInfo(t *testing.T) {
	user := &dnfv1.ChangeTeamUserInfo{
		Charguid: 123456789,
		Teamtype: 1,
	}

	data, err := proto.Marshal(user)
	if err != nil {
		t.Fatalf("Failed to marshal ChangeTeamUserInfo: %v", err)
	}

	parsed := &dnfv1.ChangeTeamUserInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChangeTeamUserInfo: %v", err)
	}

	if parsed.Charguid != user.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, user.Charguid)
	}

	if parsed.Teamtype != user.Teamtype {
		t.Errorf("Teamtype mismatch: got %d, want %d", parsed.Teamtype, user.Teamtype)
	}
}

func TestBatch41CharacterEquipOnlyIndex(t *testing.T) {
	equip := &dnfv1.CharacterEquipOnlyIndex{
		Equiplist: []*dnfv1.EquipIndexSlot{
			{Index: 1, Slot: 1},
		},
		Avatarlist: []*dnfv1.AvatarIndexSlot{
			{Index: 1, Slot: 1},
		},
	}

	data, err := proto.Marshal(equip)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterEquipOnlyIndex: %v", err)
	}

	parsed := &dnfv1.CharacterEquipOnlyIndex{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CharacterEquipOnlyIndex: %v", err)
	}

	if len(parsed.Equiplist) != len(equip.Equiplist) {
		t.Errorf("Equiplist length mismatch: got %d, want %d", len(parsed.Equiplist), len(equip.Equiplist))
	}

	if len(parsed.Avatarlist) != len(equip.Avatarlist) {
		t.Errorf("Avatarlist length mismatch: got %d, want %d", len(parsed.Avatarlist), len(equip.Avatarlist))
	}
}

func TestBatch41CharacterGuildRedpacketInfo(t *testing.T) {
	info := &dnfv1.CharacterGuildRedpacketInfo{
		Achvindex:  1,
		Expiretime: 1234567890,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterGuildRedpacketInfo: %v", err)
	}

	parsed := &dnfv1.CharacterGuildRedpacketInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CharacterGuildRedpacketInfo: %v", err)
	}

	if parsed.Achvindex != info.Achvindex {
		t.Errorf("Achvindex mismatch: got %d, want %d", parsed.Achvindex, info.Achvindex)
	}

	if parsed.Expiretime != info.Expiretime {
		t.Errorf("Expiretime mismatch: got %d, want %d", parsed.Expiretime, info.Expiretime)
	}
}

func TestBatch41CharacterRaidDamageMeterInfo(t *testing.T) {
	info := &dnfv1.CharacterRaidDamageMeterInfo{
		Charguid:    123456789,
		Name:        "test_user",
		Job:         1,
		Level:       50,
		Growtype:    1,
		Secgrowtype: 1,
		Damage:      1000000,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterRaidDamageMeterInfo: %v", err)
	}

	parsed := &dnfv1.CharacterRaidDamageMeterInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CharacterRaidDamageMeterInfo: %v", err)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}

	if parsed.Name != info.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, info.Name)
	}

	if parsed.Damage != info.Damage {
		t.Errorf("Damage mismatch: got %d, want %d", parsed.Damage, info.Damage)
	}
}

func TestBatch41CharacterRaidRoundInfo(t *testing.T) {
	info := &dnfv1.CharacterRaidRoundInfo{
		Charguid:       123456789,
		Name:           "test_user",
		Job:            1,
		Level:          50,
		Growtype:       1,
		Secgrowtype:    1,
		Creditscore:    100,
		Characterframe: 1,
		Roundinfo:      &dnfv1.PveRoundInfo{Round: 1, Score: 1000},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterRaidRoundInfo: %v", err)
	}

	parsed := &dnfv1.CharacterRaidRoundInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CharacterRaidRoundInfo: %v", err)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}

	if parsed.Creditscore != info.Creditscore {
		t.Errorf("Creditscore mismatch: got %d, want %d", parsed.Creditscore, info.Creditscore)
	}
}

func TestBatch41CharguidEntranceItem(t *testing.T) {
	item := &dnfv1.CharguidEntranceItem{
		Charguid: 123456789,
		Items: []*dnfv1.EntranceItem{
			{Index: 1, Count: 10},
		},
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal CharguidEntranceItem: %v", err)
	}

	parsed := &dnfv1.CharguidEntranceItem{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CharguidEntranceItem: %v", err)
	}

	if parsed.Charguid != item.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, item.Charguid)
	}

	if len(parsed.Items) != len(item.Items) {
		t.Errorf("Items length mismatch: got %d, want %d", len(parsed.Items), len(item.Items))
	}
}

func TestBatch41CharguidTicket(t *testing.T) {
	ticket := &dnfv1.CharguidTicket{
		Charguid: 123456789,
		Ticket: []*dnfv1.Ticket{
			{DungeonType: "dungeon_test", DailyTicket: 10},
		},
	}

	data, err := proto.Marshal(ticket)
	if err != nil {
		t.Fatalf("Failed to marshal CharguidTicket: %v", err)
	}

	parsed := &dnfv1.CharguidTicket{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CharguidTicket: %v", err)
	}

	if parsed.Charguid != ticket.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, ticket.Charguid)
	}

	if len(parsed.Ticket) != len(ticket.Ticket) {
		t.Errorf("Ticket length mismatch: got %d, want %d", len(parsed.Ticket), len(ticket.Ticket))
	}
}

func TestBatch41Chat(t *testing.T) {
	chat := &dnfv1.Chat{
		Error:            0,
		Type:             1,
		Subtype:          1,
		Charguid:         123456789,
		Targetguid:       987654321,
		Job:              1,
		Growtype:         1,
		Level:            50,
		Sender:           "test_sender",
		Chat:             "test message",
		Voice:            "voice_data",
		Voicetime:        "00:01:00",
		Receiver:         "test_receiver",
		Hyperlinktype:    1,
		Hyperlinksubtype: 1,
		Secgrowtype:      1,
		Online:           1,
		Hyperlinkdatas:   []*dnfv1.HyperlinkData{{Index: 1, Bind: true}},
		Sub:              []*dnfv1.HyperlinkSystemmessageSub{{Index: 1, Message: "sub message"}},
		Partyguid:        111,
		Equip:            &dnfv1.Equip{EquipId: 1},
		Title:            &dnfv1.Equip{EquipId: 2},
		Flag:             &dnfv1.Equip{EquipId: 3},
		Emblem:           &dnfv1.Stackable{Index: 1},
		Material:         &dnfv1.Stackable{Index: 2},
		Avatar:           &dnfv1.AvatarItem{Index: 1},
		Card:             &dnfv1.Stackable{Index: 3},
		Creature:         &dnfv1.Creature{Id: 1},
		Cartifact:        &dnfv1.Artifact{Id: 1},
		Skinchatinfo:     &dnfv1.SkinChatInfo{Chatframe: 1},
		Info:             &dnfv1.RecruitPartyInfo{Type: 1, Msg: "recruit"},
		Date:             1234567890,
		Rpguid:           222,
		Creditscore:      100,
		Crack:            &dnfv1.Stackable{Index: 4},
		Crackequip:       &dnfv1.Equip{EquipId: 4},
		Sdavatar:         &dnfv1.AvatarItem{Index: 2},
	}

	data, err := proto.Marshal(chat)
	if err != nil {
		t.Fatalf("Failed to marshal Chat: %v", err)
	}

	parsed := &dnfv1.Chat{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal Chat: %v", err)
	}

	if parsed.Charguid != chat.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, chat.Charguid)
	}

	if parsed.Sender != chat.Sender {
		t.Errorf("Sender mismatch: got %s, want %s", parsed.Sender, chat.Sender)
	}

	if parsed.Chat != chat.Chat {
		t.Errorf("Chat mismatch: got %s, want %s", parsed.Chat, chat.Chat)
	}
}

func TestBatch41ChatSync(t *testing.T) {
	sync := &dnfv1.ChatSync{
		Index: 123456789,
		Type:  1,
	}

	data, err := proto.Marshal(sync)
	if err != nil {
		t.Fatalf("Failed to marshal ChatSync: %v", err)
	}

	parsed := &dnfv1.ChatSync{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChatSync: %v", err)
	}

	if parsed.Index != sync.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, sync.Index)
	}

	if parsed.Type != sync.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsed.Type, sync.Type)
	}
}

func TestBatch41ChatSyncInfo(t *testing.T) {
	info := &dnfv1.ChatSyncInfo{
		Lastindex:  123456789,
		Totalcount: 1000,
		Type:       1,
		Chatmsg: []*dnfv1.Chat{
			{Charguid: 123456789, Sender: "test", Chat: "message"},
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal ChatSyncInfo: %v", err)
	}

	parsed := &dnfv1.ChatSyncInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChatSyncInfo: %v", err)
	}

	if parsed.Lastindex != info.Lastindex {
		t.Errorf("Lastindex mismatch: got %d, want %d", parsed.Lastindex, info.Lastindex)
	}

	if parsed.Totalcount != info.Totalcount {
		t.Errorf("Totalcount mismatch: got %d, want %d", parsed.Totalcount, info.Totalcount)
	}

	if len(parsed.Chatmsg) != len(info.Chatmsg) {
		t.Errorf("Chatmsg length mismatch: got %d, want %d", len(parsed.Chatmsg), len(info.Chatmsg))
	}
}
