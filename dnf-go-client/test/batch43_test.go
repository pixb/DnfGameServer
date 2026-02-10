package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch43DiningFoodBuffInfo(t *testing.T) {
	info := &dnfv1.DiningFoodBuffInfo{
		Index:    1,
		Endtime:  1234567890,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal DiningFoodBuffInfo: %v", err)
	}

	parsed := &dnfv1.DiningFoodBuffInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DiningFoodBuffInfo: %v", err)
	}

	if parsed.Index != info.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, info.Index)
	}

	if parsed.Endtime != info.Endtime {
		t.Errorf("Endtime mismatch: got %d, want %d", parsed.Endtime, info.Endtime)
	}
}

func TestBatch43DragonBoardAppendage(t *testing.T) {
	appendage := &dnfv1.DragonBoardAppendage{
		Charguid: 123456789,
		Index:    1,
	}

	data, err := proto.Marshal(appendage)
	if err != nil {
		t.Fatalf("Failed to marshal DragonBoardAppendage: %v", err)
	}

	parsed := &dnfv1.DragonBoardAppendage{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DragonBoardAppendage: %v", err)
	}

	if parsed.Charguid != appendage.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, appendage.Charguid)
	}

	if parsed.Index != appendage.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, appendage.Index)
	}
}

func TestBatch43DragonBoardChanceCard(t *testing.T) {
	changedice := &dnfv1.DragonBoardChangeDice{
		Charguid: 123456789,
		Type:     1,
	}

	changeslots := []*dnfv1.DragonBoardChangeSlot{
		{Charguid: 123456789, Index: 1},
		{Charguid: 123456789, Index: 2},
	}

	hold := &dnfv1.DragonBoardHold{
		Charguid: 123456789,
		Count:    3,
	}

	moveslot := &dnfv1.DragonBoardChangeSlot{
		Charguid: 123456789,
		Index:    4,
	}

	debuff := &dnfv1.DragonBoardAppendage{
		Charguid: 123456789,
		Index:    5,
	}

	dotdamage := &dnfv1.DragonBoardAppendage{
		Charguid: 123456789,
		Index:    6,
	}

	boss := &dnfv1.DragonBoardAppendage{
		Charguid: 123456789,
		Index:    7,
	}

	chancecard := &dnfv1.DragonBoardChanceCard{
		Index:       1,
		Changedice:  changedice,
		Changeslots: changeslots,
		Hold:        hold,
		Moveslot:    moveslot,
		Debuff:      debuff,
		Dotdamage:   dotdamage,
		Boss:        boss,
	}

	data, err := proto.Marshal(chancecard)
	if err != nil {
		t.Fatalf("Failed to marshal DragonBoardChanceCard: %v", err)
	}

	parsed := &dnfv1.DragonBoardChanceCard{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DragonBoardChanceCard: %v", err)
	}

	if parsed.Index != chancecard.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, chancecard.Index)
	}

	if parsed.Changedice == nil {
		t.Errorf("Changedice is nil")
	}

	if len(parsed.Changeslots) != len(chancecard.Changeslots) {
		t.Errorf("Changeslots length mismatch: got %d, want %d", len(parsed.Changeslots), len(chancecard.Changeslots))
	}
}

func TestBatch43DragonBoardMove(t *testing.T) {
	rewards := []*dnfv1.DragonBoardReward{
		{Index: 1, Count: 10},
		{Index: 2, Count: 5},
	}

	chancecard := &dnfv1.DragonBoardChanceCard{
		Index: 1,
	}

	move := &dnfv1.DragonBoardMove{
		Error:      0,
		Charguid:   123456789,
		Slot:       1,
		Appendage:  2,
		Rewards:    rewards,
		Chancecard: chancecard,
	}

	data, err := proto.Marshal(move)
	if err != nil {
		t.Fatalf("Failed to marshal DragonBoardMove: %v", err)
	}

	parsed := &dnfv1.DragonBoardMove{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DragonBoardMove: %v", err)
	}

	if parsed.Error != move.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsed.Error, move.Error)
	}

	if parsed.Charguid != move.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, move.Charguid)
	}

	if len(parsed.Rewards) != len(move.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(parsed.Rewards), len(move.Rewards))
	}
}

func TestBatch43DragonBoardResult(t *testing.T) {
	rewards := []*dnfv1.DragonBoardReward{
		{Index: 1, Count: 10},
		{Index: 2, Count: 5},
	}

	slotrewards := []*dnfv1.DragonBoardReward{
		{Index: 3, Count: 3},
	}

	result := &dnfv1.DragonBoardResult{
		Charguid:   123456789,
		Job:        1,
		Rank:       1,
		Turncount:  10,
		Playtime:   300,
		Rewards:    rewards,
		Slotrewards: slotrewards,
		World:      1,
		Creditscore: 100,
	}

	data, err := proto.Marshal(result)
	if err != nil {
		t.Fatalf("Failed to marshal DragonBoardResult: %v", err)
	}

	parsed := &dnfv1.DragonBoardResult{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DragonBoardResult: %v", err)
	}

	if parsed.Charguid != result.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, result.Charguid)
	}

	if parsed.Job != result.Job {
		t.Errorf("Job mismatch: got %d, want %d", parsed.Job, result.Job)
	}

	if len(parsed.Rewards) != len(result.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(parsed.Rewards), len(result.Rewards))
	}
}

func TestBatch43DragonBoardMember(t *testing.T) {
	rewards := []*dnfv1.DragonBoardReward{
		{Index: 1, Count: 10},
	}

	appendages := []*dnfv1.DragonBoardGainAppendage{
		{Index: 1, Count: 2},
		{Index: 2, Count: 1},
	}

	member := &dnfv1.DragonBoardMember{
		Charguid:    123456789,
		Dicetype:    1,
		Slot:        1,
		Connected:   true,
		Clearboss:   true,
		Rewards:     rewards,
		Appendages:  appendages,
	}

	data, err := proto.Marshal(member)
	if err != nil {
		t.Fatalf("Failed to marshal DragonBoardMember: %v", err)
	}

	parsed := &dnfv1.DragonBoardMember{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DragonBoardMember: %v", err)
	}

	if parsed.Charguid != member.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, member.Charguid)
	}

	if parsed.Dicetype != member.Dicetype {
		t.Errorf("Dicetype mismatch: got %d, want %d", parsed.Dicetype, member.Dicetype)
	}

	if len(parsed.Appendages) != len(member.Appendages) {
		t.Errorf("Appendages length mismatch: got %d, want %d", len(parsed.Appendages), len(member.Appendages))
	}
}

func TestBatch43DreamMazeDungeon(t *testing.T) {
	dungeon := &dnfv1.DreamMazeDungeon{
		Index:     1,
		Cleartime: 1234567890,
		Grade:     3,
		Bosshp:    100,
	}

	data, err := proto.Marshal(dungeon)
	if err != nil {
		t.Fatalf("Failed to marshal DreamMazeDungeon: %v", err)
	}

	parsed := &dnfv1.DreamMazeDungeon{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DreamMazeDungeon: %v", err)
	}

	if parsed.Index != dungeon.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, dungeon.Index)
	}

	if parsed.Cleartime != dungeon.Cleartime {
		t.Errorf("Cleartime mismatch: got %d, want %d", parsed.Cleartime, dungeon.Cleartime)
	}
}

func TestBatch43DropObjectItem(t *testing.T) {
	item := &dnfv1.DropObjectItem{
		Charguid:    123456789,
		Itemindex:   1001,
		Upgrade:     10,
		Quality:     3,
		Count:       1,
		Isgrowthcare: true,
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal DropObjectItem: %v", err)
	}

	parsed := &dnfv1.DropObjectItem{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DropObjectItem: %v", err)
	}

	if parsed.Charguid != item.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, item.Charguid)
	}

	if parsed.Itemindex != item.Itemindex {
		t.Errorf("Itemindex mismatch: got %d, want %d", parsed.Itemindex, item.Itemindex)
	}

	if parsed.Isgrowthcare != item.Isgrowthcare {
		t.Errorf("Isgrowthcare mismatch: got %v, want %v", parsed.Isgrowthcare, item.Isgrowthcare)
	}
}

func TestBatch43Dungeonrank(t *testing.T) {
	rank := &dnfv1.Dungeonrank{
		Counter:      100,
		Multihit:     50,
		Smash:        20,
		Givedamage:   10000,
		Receivedamage: 5000,
	}

	data, err := proto.Marshal(rank)
	if err != nil {
		t.Fatalf("Failed to marshal Dungeonrank: %v", err)
	}

	parsed := &dnfv1.Dungeonrank{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal Dungeonrank: %v", err)
	}

	if parsed.Counter != rank.Counter {
		t.Errorf("Counter mismatch: got %d, want %d", parsed.Counter, rank.Counter)
	}

	if parsed.Givedamage != rank.Givedamage {
		t.Errorf("Givedamage mismatch: got %d, want %d", parsed.Givedamage, rank.Givedamage)
	}
}

func TestBatch43DungeonStartInfo(t *testing.T) {
	users := []uint64{123456789, 987654321}

	detail := []*dnfv1.UserMinimumInfo{
		{Charguid: 123456789, Name: "Player1"},
		{Charguid: 987654321, Name: "Player2"},
	}

	info := &dnfv1.DungeonStartInfo{
		Matchingguid: 111111111,
		Dungeonguid:  222222222,
		Battleworld:  1,
		Bchannel:     1,
		Bip:          "192.168.1.1",
		Bport:        8000,
		Matchtype:    1,
		World:        1,
		Channel:      1,
		Targetguid:   123456789,
		Users:        users,
		Detail:       detail,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal DungeonStartInfo: %v", err)
	}

	parsed := &dnfv1.DungeonStartInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal DungeonStartInfo: %v", err)
	}

	if parsed.Matchingguid != info.Matchingguid {
		t.Errorf("Matchingguid mismatch: got %d, want %d", parsed.Matchingguid, info.Matchingguid)
	}

	if parsed.Bip != info.Bip {
		t.Errorf("Bip mismatch: got %s, want %s", parsed.Bip, info.Bip)
	}

	if len(parsed.Users) != len(info.Users) {
		t.Errorf("Users length mismatch: got %d, want %d", len(parsed.Users), len(info.Users))
	}
}

func TestBatch43EmblemRequest(t *testing.T) {
	request := &dnfv1.EmblemRequest{
		Index: 1,
		Slot:  2,
	}

	data, err := proto.Marshal(request)
	if err != nil {
		t.Fatalf("Failed to marshal EmblemRequest: %v", err)
	}

	parsed := &dnfv1.EmblemRequest{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal EmblemRequest: %v", err)
	}

	if parsed.Index != request.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, request.Index)
	}

	if parsed.Slot != request.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", parsed.Slot, request.Slot)
	}
}

func TestBatch43EnterStageMap(t *testing.T) {
	drops := []*dnfv1.StateObjectDrop{
		{Golds: []int32{100, 200}, Items: []int32{1001, 1002}, Charguid: 123456789},
		{Golds: []int32{150, 250}, Items: []int32{1003, 1004}, Charguid: 987654321},
	}

	objects := []*dnfv1.EnterStageObject{
		{Drops: drops, Guid: 1, Type: 1, Index: 1},
		{Drops: drops, Guid: 2, Type: 2, Index: 2},
	}

	stageMap := &dnfv1.EnterStageMap{
		Guid:    1,
		Objects: objects,
	}

	data, err := proto.Marshal(stageMap)
	if err != nil {
		t.Fatalf("Failed to marshal EnterStageMap: %v", err)
	}

	parsed := &dnfv1.EnterStageMap{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal EnterStageMap: %v", err)
	}

	if parsed.Guid != stageMap.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, stageMap.Guid)
	}

	if len(parsed.Objects) != len(stageMap.Objects) {
		t.Errorf("Objects length mismatch: got %d, want %d", len(parsed.Objects), len(stageMap.Objects))
	}
}

func TestBatch43EntryInfo(t *testing.T) {
	info := &dnfv1.EntryInfo{
		Charguid: 123456789,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal EntryInfo: %v", err)
	}

	parsed := &dnfv1.EntryInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal EntryInfo: %v", err)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}
}

func TestBatch43EquipPutOnOff(t *testing.T) {
	putOnOff := &dnfv1.EquipPutOnOff{
		Guid: 123456789,
		Slot: 1,
	}

	data, err := proto.Marshal(putOnOff)
	if err != nil {
		t.Fatalf("Failed to marshal EquipPutOnOff: %v", err)
	}

	parsed := &dnfv1.EquipPutOnOff{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal EquipPutOnOff: %v", err)
	}

	if parsed.Guid != putOnOff.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, putOnOff.Guid)
	}

	if parsed.Slot != putOnOff.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", parsed.Slot, putOnOff.Slot)
	}
}

func TestBatch43EscortClearReward(t *testing.T) {
	reward := &dnfv1.EscortClearReward{
		Error:     0,
		Type:      1,
		Index:     1001,
		Count:     10,
		Level:     10,
		Totalexp:  1000,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal EscortClearReward: %v", err)
	}

	parsed := &dnfv1.EscortClearReward{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal EscortClearReward: %v", err)
	}

	if parsed.Error != reward.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsed.Error, reward.Error)
	}

	if parsed.Index != reward.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, reward.Index)
	}
}

func TestBatch43EscortReward(t *testing.T) {
	reward := &dnfv1.EscortReward{
		Addexp:   100,
		Totalexp: 1000,
		Wagonhp:  100,
		Index:    1001,
		Count:    10,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal EscortReward: %v", err)
	}

	parsed := &dnfv1.EscortReward{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal EscortReward: %v", err)
	}

	if parsed.Addexp != reward.Addexp {
		t.Errorf("Addexp mismatch: got %d, want %d", parsed.Addexp, reward.Addexp)
	}

	if parsed.Wagonhp != reward.Wagonhp {
		t.Errorf("Wagonhp mismatch: got %d, want %d", parsed.Wagonhp, reward.Wagonhp)
	}
}
