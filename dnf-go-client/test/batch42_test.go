package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch42BoardGameMoveResult(t *testing.T) {
	tile := &dnfv1.BoardTileInfo{
		Posx:   1,
		Posy:   2,
		Type:   3,
		Status: 4,
	}

	user := &dnfv1.BoardUserContentsInfo{
		Charguid: 123456789,
		Posx:     1,
		Posy:     2,
		Hp:       100,
		Gold:     1000,
		Key:      5,
		Relic:    10,
	}

	result := &dnfv1.BoardGameMoveResult{
		Tile: tile,
		User: user,
	}

	data, err := proto.Marshal(result)
	if err != nil {
		t.Fatalf("Failed to marshal BoardGameMoveResult: %v", err)
	}

	parsed := &dnfv1.BoardGameMoveResult{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BoardGameMoveResult: %v", err)
	}

	if parsed.Tile.Posx != tile.Posx {
		t.Errorf("Tile.Posx mismatch: got %d, want %d", parsed.Tile.Posx, tile.Posx)
	}

	if parsed.User.Charguid != user.Charguid {
		t.Errorf("User.Charguid mismatch: got %d, want %d", parsed.User.Charguid, user.Charguid)
	}
}

func TestBatch42ChivalryCurrencyDailyGain(t *testing.T) {
	gain := &dnfv1.ChivalryCurrencyDailyGain{
		Type:  1,
		Index: 2,
		Count: 10,
	}

	data, err := proto.Marshal(gain)
	if err != nil {
		t.Fatalf("Failed to marshal ChivalryCurrencyDailyGain: %v", err)
	}

	parsed := &dnfv1.ChivalryCurrencyDailyGain{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChivalryCurrencyDailyGain: %v", err)
	}

	if parsed.Type != gain.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsed.Type, gain.Type)
	}

	if parsed.Count != gain.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, gain.Count)
	}
}

func TestBatch42ChivalryMission(t *testing.T) {
	mission := &dnfv1.ChivalryMission{
		Type:  dnfv1.ChivalryMissionType_CHIVALRY_MISSION_DUNGEON,
		Value: 100,
		Count: 5,
	}

	data, err := proto.Marshal(mission)
	if err != nil {
		t.Fatalf("Failed to marshal ChivalryMission: %v", err)
	}

	parsed := &dnfv1.ChivalryMission{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ChivalryMission: %v", err)
	}

	if parsed.Type != mission.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsed.Type, mission.Type)
	}

	if parsed.Value != mission.Value {
		t.Errorf("Value mismatch: got %d, want %d", parsed.Value, mission.Value)
	}
}

func TestBatch42ClearDungeonInfo(t *testing.T) {
	info := &dnfv1.ClearDungeonInfo{
		Floor:             10,
		Grade:             3,
		Score:             1000,
		Step:              5,
		Shortestcleartime: 60000,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal ClearDungeonInfo: %v", err)
	}

	parsed := &dnfv1.ClearDungeonInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ClearDungeonInfo: %v", err)
	}

	if parsed.Floor != info.Floor {
		t.Errorf("Floor mismatch: got %d, want %d", parsed.Floor, info.Floor)
	}

	if parsed.Score != info.Score {
		t.Errorf("Score mismatch: got %d, want %d", parsed.Score, info.Score)
	}
}

func TestBatch42ClearDungeonInfoList(t *testing.T) {
	info1 := &dnfv1.ClearDungeonInfo{
		Floor: 10,
		Grade: 3,
		Score: 1000,
	}

	info2 := &dnfv1.ClearDungeonInfo{
		Floor: 20,
		Grade: 4,
		Score: 2000,
	}

	list := &dnfv1.ClearDungeonInfoList{
		Index: 1,
		List:  []*dnfv1.ClearDungeonInfo{info1, info2},
	}

	data, err := proto.Marshal(list)
	if err != nil {
		t.Fatalf("Failed to marshal ClearDungeonInfoList: %v", err)
	}

	parsed := &dnfv1.ClearDungeonInfoList{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ClearDungeonInfoList: %v", err)
	}

	if len(parsed.List) != 2 {
		t.Errorf("List length mismatch: got %d, want %d", len(parsed.List), 2)
	}

	if parsed.List[0].Floor != info1.Floor {
		t.Errorf("List[0].Floor mismatch: got %d, want %d", parsed.List[0].Floor, info1.Floor)
	}
}

func TestBatch42Clientinfo(t *testing.T) {
	info := &dnfv1.Clientinfo{
		PlatID:         1,
		DeviceSoft:     "Android 10",
		DeviceHard:     "Pixel 3",
		TeleOper:       "China Mobile",
		Network:        "4G",
		ScrWidth:       1080,
		ScrHeight:      2340,
		Density:        2.5,
		Cpu:            "Snapdragon 835",
		Memory:         4096,
		GlRender:       "Adreno 540",
		GlVersion:      "OpenGL ES 3.2",
		DeviceID:       "test_device_id",
		ClientIP:       "192.168.1.1",
		Type:           1,
		OAID:           "test_oaid",
		BuildType:      dnfv1.ClientBuildType_ADULT,
		DeviceLanguage: "zh-CN",
		DeviceUTC:      8,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal Clientinfo: %v", err)
	}

	parsed := &dnfv1.Clientinfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal Clientinfo: %v", err)
	}

	if parsed.PlatID != info.PlatID {
		t.Errorf("PlatID mismatch: got %d, want %d", parsed.PlatID, info.PlatID)
	}

	if parsed.DeviceSoft != info.DeviceSoft {
		t.Errorf("DeviceSoft mismatch: got %s, want %s", parsed.DeviceSoft, info.DeviceSoft)
	}

	if parsed.Density != info.Density {
		t.Errorf("Density mismatch: got %f, want %f", parsed.Density, info.Density)
	}
}

func TestBatch42ClosenessResultInfo(t *testing.T) {
	info := &dnfv1.ClosenessResultInfo{
		Fguid:          123456789,
		Increasescore:  10,
		Closenessscore: 100,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal ClosenessResultInfo: %v", err)
	}

	parsed := &dnfv1.ClosenessResultInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ClosenessResultInfo: %v", err)
	}

	if parsed.Fguid != info.Fguid {
		t.Errorf("Fguid mismatch: got %d, want %d", parsed.Fguid, info.Fguid)
	}

	if parsed.Increasescore != info.Increasescore {
		t.Errorf("Increasescore mismatch: got %d, want %d", parsed.Increasescore, info.Increasescore)
	}
}

func TestBatch42CollectionInfo(t *testing.T) {
	item1 := &dnfv1.CollectionItem{
		Index:       1,
		Regist:      true,
		Reward:      true,
		Rewardindex: 10,
	}

	item2 := &dnfv1.CollectionItem{
		Index:       2,
		Regist:      true,
		Reward:      false,
		Rewardindex: 0,
	}

	info := &dnfv1.CollectionInfo{
		Index:  1,
		Reward: true,
		Items:  []*dnfv1.CollectionItem{item1, item2},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal CollectionInfo: %v", err)
	}

	parsed := &dnfv1.CollectionInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CollectionInfo: %v", err)
	}

	if parsed.Index != info.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, info.Index)
	}

	if len(parsed.Items) != 2 {
		t.Errorf("Items length mismatch: got %d, want %d", len(parsed.Items), 2)
	}
}

func TestBatch42Comment(t *testing.T) {
	comment := &dnfv1.Comment{
		Writerguid:     123456789,
		Name:           "test_user",
		Comment:        "test comment",
		Writetime:      1234567890,
		Growtype:       1,
		Secondgrowtype: 1,
		Job:            1,
	}

	data, err := proto.Marshal(comment)
	if err != nil {
		t.Fatalf("Failed to marshal Comment: %v", err)
	}

	parsed := &dnfv1.Comment{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal Comment: %v", err)
	}

	if parsed.Writerguid != comment.Writerguid {
		t.Errorf("Writerguid mismatch: got %d, want %d", parsed.Writerguid, comment.Writerguid)
	}

	if parsed.Name != comment.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, comment.Name)
	}

	if parsed.Comment != comment.Comment {
		t.Errorf("Comment mismatch: got %s, want %s", parsed.Comment, comment.Comment)
	}
}

func TestBatch42CommunityGiftReceiverInfo(t *testing.T) {
	info := &dnfv1.CommunityGiftReceiverInfo{
		Name:           "test_receiver",
		Level:          50,
		Job:            1,
		Growtype:       1,
		Secondgrowtype: 1,
		Itemindex:      100,
		Count:          10,
		Charm:          1000,
		Date:           1234567890,
		Expiretime:     12345678900,
		Charguid:       123456789,
		Closenessscore: 50,
		Check:          1,
		Msg:            "test message",
		Index:          "gift_index",
		Packagingindex: 5,
		Characterframe: 10,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal CommunityGiftReceiverInfo: %v", err)
	}

	parsed := &dnfv1.CommunityGiftReceiverInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CommunityGiftReceiverInfo: %v", err)
	}

	if parsed.Name != info.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, info.Name)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}
}

func TestBatch42CompleteQuestInfo(t *testing.T) {
	consumeItem1 := &dnfv1.ConsumeItems{
		Itemindex: 100,
		Count:     10,
	}

	consumeItem2 := &dnfv1.ConsumeItems{
		Itemindex: 200,
		Count:     5,
	}

	removeitems := &dnfv1.Removeitems{
		Items: []*dnfv1.ConsumeItems{consumeItem1, consumeItem2},
	}

	info := &dnfv1.CompleteQuestInfo{
		Index:       1,
		Type:        2,
		State:       3,
		Count:       10,
		Removeitems: removeitems,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal CompleteQuestInfo: %v", err)
	}

	parsed := &dnfv1.CompleteQuestInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CompleteQuestInfo: %v", err)
	}

	if parsed.Index != info.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, info.Index)
	}

	if len(parsed.Removeitems.Items) != 2 {
		t.Errorf("Removeitems.Items length mismatch: got %d, want %d", len(parsed.Removeitems.Items), 2)
	}
}

func TestBatch42CreatureErrand(t *testing.T) {
	todayinfo := &dnfv1.ErrandToday{
		Indexlist:   []int32{1, 2, 3},
		Rewardindex: 10,
		Rewardcount: 5,
	}

	clear1 := &dnfv1.ErrandClear{
		Rarity: 1,
		Count:  10,
	}

	clear2 := &dnfv1.ErrandClear{
		Rarity: 2,
		Count:  5,
	}

	info1 := &dnfv1.ErrandInfo{
		Errandid:  1,
		State:     2,
		Starttime: 1234567890,
		Endtime:   12345678900,
		Guidlist:  []uint64{111, 222, 333},
		Istoday:   true,
		Todayinfo: todayinfo,
	}

	errand := &dnfv1.CreatureErrand{
		Level:         10,
		Todayinfo:     todayinfo,
		Clearlist:     []*dnfv1.ErrandClear{clear1, clear2},
		Slotlist:      []*dnfv1.ErrandInfo{info1},
		Lastresettime: 1234567890,
	}

	data, err := proto.Marshal(errand)
	if err != nil {
		t.Fatalf("Failed to marshal CreatureErrand: %v", err)
	}

	parsed := &dnfv1.CreatureErrand{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CreatureErrand: %v", err)
	}

	if parsed.Level != errand.Level {
		t.Errorf("Level mismatch: got %d, want %d", parsed.Level, errand.Level)
	}

	if len(parsed.Clearlist) != 2 {
		t.Errorf("Clearlist length mismatch: got %d, want %d", len(parsed.Clearlist), 2)
	}
}

func TestBatch42CreatureCommunion(t *testing.T) {
	skillInfo1 := &dnfv1.CreatureLearnSkillInfo{
		Category:   1,
		Skillindex: 100,
		Skilllevel: 5,
	}

	skillInfo2 := &dnfv1.CreatureLearnSkillInfo{
		Category:   2,
		Skillindex: 200,
		Skilllevel: 10,
	}

	skillInfos1 := &dnfv1.CreatureLearnSkillInfos{
		SkillInfos: []*dnfv1.CreatureLearnSkillInfo{skillInfo1, skillInfo2},
	}

	slotInfos := map[int32]*dnfv1.CreatureLearnSkillInfos{
		1: skillInfos1,
	}

	communion := &dnfv1.CreatureCommunion{
		Level:         10,
		Selectedslot:  1,
		Extentionslot: 2,
		SlotInfos:     slotInfos,
	}

	data, err := proto.Marshal(communion)
	if err != nil {
		t.Fatalf("Failed to marshal CreatureCommunion: %v", err)
	}

	parsed := &dnfv1.CreatureCommunion{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CreatureCommunion: %v", err)
	}

	if parsed.Level != communion.Level {
		t.Errorf("Level mismatch: got %d, want %d", parsed.Level, communion.Level)
	}

	if len(parsed.SlotInfos) != 1 {
		t.Errorf("SlotInfos length mismatch: got %d, want %d", len(parsed.SlotInfos), 1)
	}
}

func TestBatch42CustomPvpData(t *testing.T) {
	data := &dnfv1.CustomPvpData{
		Type:     1,
		Data:     100,
		Isuse:    1,
		Ishide:   0,
		Charguid: 123456789,
	}

	serialized, err := proto.Marshal(data)
	if err != nil {
		t.Fatalf("Failed to marshal CustomPvpData: %v", err)
	}

	parsed := &dnfv1.CustomPvpData{}
	if err := proto.Unmarshal(serialized, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CustomPvpData: %v", err)
	}

	if parsed.Type != data.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsed.Type, data.Type)
	}

	if parsed.Charguid != data.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, data.Charguid)
	}
}

func TestBatch42DamageAnalysis(t *testing.T) {
	analysis := &dnfv1.DamageAnalysis{
		Time:           1.5,
		Skillindex:     100,
		Damage:         1000,
		Criticaldamage: 500,
	}

	data, err := proto.Marshal(analysis)
	if err != nil {
		t.Fatalf("Failed to marshal DamageAnalysis: %v", err)
	}

	parsed := &dnfv1.DamageAnalysis{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DamageAnalysis: %v", err)
	}

	if parsed.Time != analysis.Time {
		t.Errorf("Time mismatch: got %f, want %f", parsed.Time, analysis.Time)
	}

	if parsed.Damage != analysis.Damage {
		t.Errorf("Damage mismatch: got %d, want %d", parsed.Damage, analysis.Damage)
	}
}

func TestBatch42DetailTimeLine(t *testing.T) {
	timeline := &dnfv1.DetailTimeLine{
		Charguid:      123456789,
		Index:         1,
		Date:          1234567890,
		Charactername: "test_character",
	}

	data, err := proto.Marshal(timeline)
	if err != nil {
		t.Fatalf("Failed to marshal DetailTimeLine: %v", err)
	}

	parsed := &dnfv1.DetailTimeLine{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DetailTimeLine: %v", err)
	}

	if parsed.Charguid != timeline.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, timeline.Charguid)
	}

	if parsed.Charactername != timeline.Charactername {
		t.Errorf("Charactername mismatch: got %s, want %s", parsed.Charactername, timeline.Charactername)
	}
}
