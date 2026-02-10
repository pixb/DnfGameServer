package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch39AdventurebookInfo(t *testing.T) {
	info := &dnfv1.AdventurebookInfo{
		Bindex:         1,
		Bstate:         2,
		Charguid:       123456789,
		Charname:       "TestChar",
		Growtype:       3,
		Growsecondtype: 4,
		Job:            5,
		Rewardedcharguid: []uint64{111, 222, 333},
		Terareward:     true,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AdventurebookInfo: %v", err)
	}

	parsed := &dnfv1.AdventurebookInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventurebookInfo: %v", err)
	}

	if parsed.Bindex != info.Bindex {
		t.Errorf("Bindex mismatch: got %d, want %d", parsed.Bindex, info.Bindex)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}

	if len(parsed.Rewardedcharguid) != len(info.Rewardedcharguid) {
		t.Errorf("Rewardedcharguid length mismatch: got %d, want %d", len(parsed.Rewardedcharguid), len(info.Rewardedcharguid))
	}
}

func TestBatch39AdventurebookCondition(t *testing.T) {
	condition := &dnfv1.AdventurebookCondition{
		Bookindex:      1,
		Booktype:        2,
		Conditionstate: 3,
	}

	data, err := proto.Marshal(condition)
	if err != nil {
		t.Fatalf("Failed to marshal AdventurebookCondition: %v", err)
	}

	parsed := &dnfv1.AdventurebookCondition{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventurebookCondition: %v", err)
	}

	if parsed.Bookindex != condition.Bookindex {
		t.Errorf("Bookindex mismatch: got %d, want %d", parsed.Bookindex, condition.Bookindex)
	}
}

func TestBatch39AdventurebookAchieveClearCondition(t *testing.T) {
	condition := &dnfv1.AdventurebookAchieveClearCondition{
		Groupindex:      1,
		State:           2,
		Conditioncount: 3,
	}

	data, err := proto.Marshal(condition)
	if err != nil {
		t.Fatalf("Failed to marshal AdventurebookAchieveClearCondition: %v", err)
	}

	parsed := &dnfv1.AdventurebookAchieveClearCondition{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventurebookAchieveClearCondition: %v", err)
	}

	if parsed.Groupindex != condition.Groupindex {
		t.Errorf("Groupindex mismatch: got %d, want %d", parsed.Groupindex, condition.Groupindex)
	}
}

func TestBatch39AdventurebookOpenCondition(t *testing.T) {
	condition := &dnfv1.AdventurebookOpenCondition{
		Cindex: 1,
		Cstate: 2,
	}

	data, err := proto.Marshal(condition)
	if err != nil {
		t.Fatalf("Failed to marshal AdventurebookOpenCondition: %v", err)
	}

	parsed := &dnfv1.AdventurebookOpenCondition{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventurebookOpenCondition: %v", err)
	}

	if parsed.Cindex != condition.Cindex {
		t.Errorf("Cindex mismatch: got %d, want %d", parsed.Cindex, condition.Cindex)
	}
}

func TestBatch39AdventurebookQuest(t *testing.T) {
	quest := &dnfv1.AdventurebookQuest{
		Qindex:        1,
		Qstate:        2,
		Qcount:        3,
		Achieveindex:  4,
		Achieveclearconditions: []*dnfv1.AdventurebookAchieveClearCondition{
			{Groupindex: 1, State: 2, Conditioncount: 3},
			{Groupindex: 4, State: 5, Conditioncount: 6},
		},
	}

	data, err := proto.Marshal(quest)
	if err != nil {
		t.Fatalf("Failed to marshal AdventurebookQuest: %v", err)
	}

	parsed := &dnfv1.AdventurebookQuest{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventurebookQuest: %v", err)
	}

	if parsed.Qindex != quest.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", parsed.Qindex, quest.Qindex)
	}

	if len(parsed.Achieveclearconditions) != len(quest.Achieveclearconditions) {
		t.Errorf("Achieveclearconditions length mismatch: got %d, want %d", len(parsed.Achieveclearconditions), len(quest.Achieveclearconditions))
	}
}

func TestBatch39AdventureClearReward(t *testing.T) {
	reward := &dnfv1.AdventureClearReward{
		Exp:   100,
		Index: 1,
		Count: 2,
		Items: &dnfv1.Items{},
		List: []*dnfv1.MoneyItem{
			{Index: 1, Count: 100},
			{Index: 2, Count: 200},
		},
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureClearReward: %v", err)
	}

	parsed := &dnfv1.AdventureClearReward{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureClearReward: %v", err)
	}

	if parsed.Exp != reward.Exp {
		t.Errorf("Exp mismatch: got %d, want %d", parsed.Exp, reward.Exp)
	}

	if len(parsed.List) != len(reward.List) {
		t.Errorf("List length mismatch: got %d, want %d", len(parsed.List), len(reward.List))
	}
}

func TestBatch39AdventureExpInfo(t *testing.T) {
	info := &dnfv1.AdventureExpInfo{
		Rewardexp: 1000,
		Level:    50,
		Exp:       1234567890,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureExpInfo: %v", err)
	}

	parsed := &dnfv1.AdventureExpInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureExpInfo: %v", err)
	}

	if parsed.Rewardexp != info.Rewardexp {
		t.Errorf("Rewardexp mismatch: got %d, want %d", parsed.Rewardexp, info.Rewardexp)
	}

	if parsed.Exp != info.Exp {
		t.Errorf("Exp mismatch: got %d, want %d", parsed.Exp, info.Exp)
	}
}

func TestBatch39AdventureListItem(t *testing.T) {
	item := &dnfv1.AdventureListItem{
		Type:  1,
		Count: 10,
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureListItem: %v", err)
	}

	parsed := &dnfv1.AdventureListItem{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureListItem: %v", err)
	}

	if parsed.Type != item.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsed.Type, item.Type)
	}

	if parsed.Count != item.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, item.Count)
	}
}

func TestBatch39AdventureRewardInfo(t *testing.T) {
	info := &dnfv1.AdventureRewardInfo{
		Rewards:      &dnfv1.ContentsRewardInfo{},
		Adventureexp: &dnfv1.AdventureExpInfo{Rewardexp: 100, Level: 10, Exp: 1000},
		Charexps: []*dnfv1.ExpInfo{
			{Exp: 100, Charguid: 111},
			{Exp: 200, Charguid: 222},
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureRewardInfo: %v", err)
	}

	parsed := &dnfv1.AdventureRewardInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureRewardInfo: %v", err)
	}

	if len(parsed.Charexps) != len(info.Charexps) {
		t.Errorf("Charexps length mismatch: got %d, want %d", len(parsed.Charexps), len(info.Charexps))
	}
}

func TestBatch39AdventureRewardStep(t *testing.T) {
	step := &dnfv1.AdventureRewardStep{
		Step: 5,
	}

	data, err := proto.Marshal(step)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureRewardStep: %v", err)
	}

	parsed := &dnfv1.AdventureRewardStep{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureRewardStep: %v", err)
	}

	if parsed.Step != step.Step {
		t.Errorf("Step mismatch: got %d, want %d", parsed.Step, step.Step)
	}
}

func TestBatch39AdventureUnionCollection(t *testing.T) {
	collection := &dnfv1.AdventureUnionCollection{
		Job:         1,
		Growtype:    2,
		Secgrowtype: 3,
		Equipscore:  1000,
	}

	data, err := proto.Marshal(collection)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionCollection: %v", err)
	}

	parsed := &dnfv1.AdventureUnionCollection{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionCollection: %v", err)
	}

	if parsed.Job != collection.Job {
		t.Errorf("Job mismatch: got %d, want %d", parsed.Job, collection.Job)
	}

	if parsed.Equipscore != collection.Equipscore {
		t.Errorf("Equipscore mismatch: got %d, want %d", parsed.Equipscore, collection.Equipscore)
	}
}

func TestBatch39AdventureUnionCollectionSlot(t *testing.T) {
	slot := &dnfv1.AdventureUnionCollectionSlot{
		Slot:        1,
		Rewardindex: 100,
	}

	data, err := proto.Marshal(slot)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionCollectionSlot: %v", err)
	}

	parsed := &dnfv1.AdventureUnionCollectionSlot{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionCollectionSlot: %v", err)
	}

	if parsed.Slot != slot.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", parsed.Slot, slot.Slot)
	}

	if parsed.Rewardindex != slot.Rewardindex {
		t.Errorf("Rewardindex mismatch: got %d, want %d", parsed.Rewardindex, slot.Rewardindex)
	}
}

func TestBatch39AdventureUnionExpedition(t *testing.T) {
	expedition := &dnfv1.AdventureUnionExpedition{
		Area:           1,
		Time:           3600,
		Consumefatigue: 10,
		Regdate:        1234567890,
		Characters: []*dnfv1.CharguidFatigue{
			{Charguid: 111, Fatigue: 5},
			{Charguid: 222, Fatigue: 8},
		},
		Rewards: []*dnfv1.AdventureUnionExpeditionRewards{
			{Index: 1, Count: 10},
			{Index: 2, Count: 20},
		},
	}

	data, err := proto.Marshal(expedition)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionExpedition: %v", err)
	}

	parsed := &dnfv1.AdventureUnionExpedition{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionExpedition: %v", err)
	}

	if parsed.Area != expedition.Area {
		t.Errorf("Area mismatch: got %d, want %d", parsed.Area, expedition.Area)
	}

	if len(parsed.Characters) != len(expedition.Characters) {
		t.Errorf("Characters length mismatch: got %d, want %d", len(parsed.Characters), len(expedition.Characters))
	}
}

func TestBatch39AdventureUnionExpeditionRewards(t *testing.T) {
	rewards := &dnfv1.AdventureUnionExpeditionRewards{
		Index: 1,
		Count: 10,
	}

	data, err := proto.Marshal(rewards)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionExpeditionRewards: %v", err)
	}

	parsed := &dnfv1.AdventureUnionExpeditionRewards{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionExpeditionRewards: %v", err)
	}

	if parsed.Index != rewards.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, rewards.Index)
	}

	if parsed.Count != rewards.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, rewards.Count)
	}
}

func TestBatch39AdventureUnionLevelReward(t *testing.T) {
	reward := &dnfv1.AdventureUnionLevelReward{
		Level: 50,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionLevelReward: %v", err)
	}

	parsed := &dnfv1.AdventureUnionLevelReward{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionLevelReward: %v", err)
	}

	if parsed.Level != reward.Level {
		t.Errorf("Level mismatch: got %d, want %d", parsed.Level, reward.Level)
	}
}

func TestBatch39AdventureUnionLevelRewardInfo(t *testing.T) {
	info := &dnfv1.AdventureUnionLevelRewardInfo{
		Level:     50,
		Itemindex:  100,
		Itemcount: 5,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionLevelRewardInfo: %v", err)
	}

	parsed := &dnfv1.AdventureUnionLevelRewardInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionLevelRewardInfo: %v", err)
	}

	if parsed.Level != info.Level {
		t.Errorf("Level mismatch: got %d, want %d", parsed.Level, info.Level)
	}

	if parsed.Itemindex != info.Itemindex {
		t.Errorf("Itemindex mismatch: got %d, want %d", parsed.Itemindex, info.Itemindex)
	}

	if parsed.Itemcount != info.Itemcount {
		t.Errorf("Itemcount mismatch: got %d, want %d", parsed.Itemcount, info.Itemcount)
	}
}

func TestBatch39AdventureUnionShareboardBackground(t *testing.T) {
	background := &dnfv1.AdventureUnionShareboardBackground{
		Index: 1,
	}

	data, err := proto.Marshal(background)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionShareboardBackground: %v", err)
	}

	parsed := &dnfv1.AdventureUnionShareboardBackground{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionShareboardBackground: %v", err)
	}

	if parsed.Index != background.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, background.Index)
	}
}

func TestBatch39AdventureUnionShareboardFrame(t *testing.T) {
	frame := &dnfv1.AdventureUnionShareboardFrame{
		Index: 1,
	}

	data, err := proto.Marshal(frame)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionShareboardFrame: %v", err)
	}

	parsed := &dnfv1.AdventureUnionShareboardFrame{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionShareboardFrame: %v", err)
	}

	if parsed.Index != frame.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, frame.Index)
	}
}

func TestBatch39AdventureUnionShareboardSlot(t *testing.T) {
	slot := &dnfv1.AdventureUnionShareboardSlot{
		Slot:         1,
		Charguid:     123456789,
		Direction:    true,
		Antievilscore: 100,
	}

	data, err := proto.Marshal(slot)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionShareboardSlot: %v", err)
	}

	parsed := &dnfv1.AdventureUnionShareboardSlot{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionShareboardSlot: %v", err)
	}

	if parsed.Slot != slot.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", parsed.Slot, slot.Slot)
	}

	if parsed.Charguid != slot.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, slot.Charguid)
	}

	if parsed.Direction != slot.Direction {
		t.Errorf("Direction mismatch: got %v, want %v", parsed.Direction, slot.Direction)
	}
}

func TestBatch39AdventureUnionShareboardSlotDetailInfo(t *testing.T) {
	info := &dnfv1.AdventureUnionShareboardSlotDetailInfo{
		Slot:         1,
		Direction:    true,
		Characterinfo: &dnfv1.Character{},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureUnionShareboardSlotDetailInfo: %v", err)
	}

	parsed := &dnfv1.AdventureUnionShareboardSlotDetailInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AdventureUnionShareboardSlotDetailInfo: %v", err)
	}

	if parsed.Slot != info.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", parsed.Slot, info.Slot)
	}

	if parsed.Direction != info.Direction {
		t.Errorf("Direction mismatch: got %v, want %v", parsed.Direction, info.Direction)
	}
}

func TestBatch39ExpInfo(t *testing.T) {
	info := &dnfv1.ExpInfo{
		Exp:      100,
		Charguid: 123456789,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal ExpInfo: %v", err)
	}

	parsed := &dnfv1.ExpInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ExpInfo: %v", err)
	}

	if parsed.Exp != info.Exp {
		t.Errorf("Exp mismatch: got %d, want %d", parsed.Exp, info.Exp)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}
}

func TestBatch39CharguidFatigue(t *testing.T) {
	fatigue := &dnfv1.CharguidFatigue{
		Charguid: 123456789,
		Fatigue:  50,
	}

	data, err := proto.Marshal(fatigue)
	if err != nil {
		t.Fatalf("Failed to marshal CharguidFatigue: %v", err)
	}

	parsed := &dnfv1.CharguidFatigue{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal CharguidFatigue: %v", err)
	}

	if parsed.Charguid != fatigue.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, fatigue.Charguid)
	}

	if parsed.Fatigue != fatigue.Fatigue {
		t.Errorf("Fatigue mismatch: got %d, want %d", parsed.Fatigue, fatigue.Fatigue)
	}
}
