package test

import (
	"testing"
	"time"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch44Equipped(t *testing.T) {
	original := &dnfv1.Equipped{
		Index:        1,
		Guid:         123456789,
		Upgrade:      10,
		Reforge:      5,
		Reforgeexp:   100,
		Amplify:      3,
		Aoption:      2,
		Quality:      4,
		Endurance:    100,
		Enchant:      12,
		Slot:         5,
		Scount:       1,
		Tcount:       2,
		Expiretime:   int64(time.Now().Unix()),
		Rappearance:  true,
		Skin:         1,
		Locked:       false,
		Enchantindex: 3,
		Sealing:      0,
		Upgradepoint: 50,
		Season:       1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.Equipped{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Guid != original.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", decoded.Guid, original.Guid)
	}
}

func TestBatch44EscortGetInfo(t *testing.T) {
	original := &dnfv1.EscortGetInfo{
		Error: 0,
		Index: 1,
		Points: []*dnfv1.PointInfo{
			{
				Way:     1,
				Point:   100,
				Dungeon: 1,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EscortGetInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Points) != len(original.Points) {
		t.Errorf("Points length mismatch: got %d, want %d", len(decoded.Points), len(original.Points))
	}
}

func TestBatch44EscortPartyInfo(t *testing.T) {
	original := &dnfv1.EscortPartyInfo{
		Error: 0,
		Party: []*dnfv1.EscortGetInfo{
			{
				Error: 0,
				Index: 1,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EscortPartyInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Party) != len(original.Party) {
		t.Errorf("Party length mismatch: got %d, want %d", len(decoded.Party), len(original.Party))
	}
}

func TestBatch44EscortPointReward(t *testing.T) {
	original := &dnfv1.EscortPointReward{
		Error:    0,
		Type:     1,
		Index:    1,
		Count:    10,
		Level:    50,
		Totalexp: 1000,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EscortPointReward{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch44EscortRequestReady(t *testing.T) {
	original := &dnfv1.EscortRequestReady{}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EscortRequestReady{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}
}

func TestBatch44EscortStart(t *testing.T) {
	original := &dnfv1.EscortStart{
		Error: 0,
		Area:  1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EscortStart{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if decoded.Area != original.Area {
		t.Errorf("Area mismatch: got %d, want %d", decoded.Area, original.Area)
	}
}

func TestBatch44EventCondition(t *testing.T) {
	original := &dnfv1.EventCondition{
		Charguid: 123456789,
		Count:    5,
		Index:    1,
		Type:     2,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EventCondition{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Charguid != original.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", decoded.Charguid, original.Charguid)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch44EventData(t *testing.T) {
	original := &dnfv1.EventData{
		Index:     1,
		Status:    1,
		Count:     5,
		Value:     100,
		DateStart: int64(time.Now().Unix()),
		DateEnd:   int64(time.Now().Unix() + 86400),
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EventData{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Status != original.Status {
		t.Errorf("Status mismatch: got %d, want %d", decoded.Status, original.Status)
	}
}

func TestBatch44EventDataForCharacter(t *testing.T) {
	original := &dnfv1.EventDataForCharacter{
		Guid: 123456789,
		Eventlist: []*dnfv1.MainEventData{
			{
				Index:  1,
				Status: 1,
				Count:  5,
				Value:  100,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EventDataForCharacter{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Guid != original.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", decoded.Guid, original.Guid)
	}
	if len(decoded.Eventlist) != len(original.Eventlist) {
		t.Errorf("Eventlist length mismatch: got %d, want %d", len(decoded.Eventlist), len(original.Eventlist))
	}
}

func TestBatch44EventOnTime(t *testing.T) {
	original := &dnfv1.EventOnTime{
		Index:           1,
		Active:          1,
		Days:            []int32{1, 2, 3, 4, 5, 6, 7},
		StartHour:       10,
		StartMinute:     0,
		EndHour:         22,
		EndMinute:       0,
		RewardItemId:     1001,
		RewardItemCount:  10,
		RewardBuffId:    2001,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EventOnTime{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if len(decoded.Days) != len(original.Days) {
		t.Errorf("Days length mismatch: got %d, want %d", len(decoded.Days), len(original.Days))
	}
}

func TestBatch44EventRewardInfo(t *testing.T) {
	original := &dnfv1.EventRewardInfo{
		Index: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EventRewardInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
}

func TestBatch44EventScheduler(t *testing.T) {
	original := &dnfv1.EventScheduler{
		Index:     1,
		Startdate: uint64(time.Now().Unix()),
		Enddate:   uint64(time.Now().Unix() + 86400),
		Active:    true,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EventScheduler{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Active != original.Active {
		t.Errorf("Active mismatch: got %v, want %v", decoded.Active, original.Active)
	}
}

func TestBatch44EventSelectInfo(t *testing.T) {
	original := &dnfv1.EventSelectInfo{
		SelectedCharguid: 123456789,
		EventDateEnd:     int64(time.Now().Unix() + 86400),
		EventIndex:       1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.EventSelectInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.SelectedCharguid != original.SelectedCharguid {
		t.Errorf("SelectedCharguid mismatch: got %d, want %d", decoded.SelectedCharguid, original.SelectedCharguid)
	}
	if decoded.EventIndex != original.EventIndex {
		t.Errorf("EventIndex mismatch: got %d, want %d", decoded.EventIndex, original.EventIndex)
	}
}

func TestBatch44ExpDetailinfo(t *testing.T) {
	original := &dnfv1.ExpDetailinfo{
		Baseexp:    1000,
		Rankexp:    500,
		Premiumexp: 200,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ExpDetailinfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Baseexp != original.Baseexp {
		t.Errorf("Baseexp mismatch: got %d, want %d", decoded.Baseexp, original.Baseexp)
	}
	if decoded.Rankexp != original.Rankexp {
		t.Errorf("Rankexp mismatch: got %d, want %d", decoded.Rankexp, original.Rankexp)
	}
}

func TestBatch44FacilityInfo(t *testing.T) {
	original := &dnfv1.FacilityInfo{
		Type:  1,
		Level: 10,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.FacilityInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Type != original.Type {
		t.Errorf("Type mismatch: got %d, want %d", decoded.Type, original.Type)
	}
	if decoded.Level != original.Level {
		t.Errorf("Level mismatch: got %d, want %d", decoded.Level, original.Level)
	}
}

func TestBatch44FloorList(t *testing.T) {
	original := &dnfv1.FloorList{
		Index: 1,
		Tick:  uint64(time.Now().Unix()),
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.FloorList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Tick != original.Tick {
		t.Errorf("Tick mismatch: got %d, want %d", decoded.Tick, original.Tick)
	}
}

func TestBatch44FloorRecord(t *testing.T) {
	original := &dnfv1.FloorRecord{
		Tick: uint64(time.Now().Unix()),
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.FloorRecord{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Tick != original.Tick {
		t.Errorf("Tick mismatch: got %d, want %d", decoded.Tick, original.Tick)
	}
}

func TestBatch44FriendDailyClosenessInfo(t *testing.T) {
	original := &dnfv1.FriendDailyClosenessInfo{
		Index: 1,
		Count: 5,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.FriendDailyClosenessInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch44FriendRank(t *testing.T) {
	original := &dnfv1.FriendRank{
		Guid:           123456789,
		Score:          10000,
		Job:            1,
		Growtype:       2,
		Name:           "TestName",
		Level:          50,
		Launchinfo:     1,
		Vip:            1,
		Profileurl:     "http://example.com/profile",
		Profilename:    "TestProfile",
		Creditscore:    100,
		Characterframe: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.FriendRank{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Guid != original.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", decoded.Guid, original.Guid)
	}
	if decoded.Score != original.Score {
		t.Errorf("Score mismatch: got %d, want %d", decoded.Score, original.Score)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
}

func TestBatch44GachaResult(t *testing.T) {
	original := &dnfv1.GachaResult{
		Grade:      1,
		Recipe:     2,
		Gachaindex: 3,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GachaResult{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Grade != original.Grade {
		t.Errorf("Grade mismatch: got %d, want %d", decoded.Grade, original.Grade)
	}
	if decoded.Recipe != original.Recipe {
		t.Errorf("Recipe mismatch: got %d, want %d", decoded.Recipe, original.Recipe)
	}
}

func TestBatch44GameChannel(t *testing.T) {
	original := &dnfv1.GameChannel{
		Status: []*dnfv1.GameChannelStatus{
			{
				World:    1,
				Channel:  1,
				Active:   1,
				Cu:       100,
				Session:  50,
				Capacity: 200,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GameChannel{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if len(decoded.Status) != len(original.Status) {
		t.Errorf("Status length mismatch: got %d, want %d", len(decoded.Status), len(original.Status))
	}
}

func TestBatch44Gauge(t *testing.T) {
	original := &dnfv1.Gauge{
		Type:  dnfv1.DungeonGaugeType_DUNGEON_GAUGE_HP,
		Gauge: 100,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.Gauge{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Type != original.Type {
		t.Errorf("Type mismatch: got %v, want %v", decoded.Type, original.Type)
	}
	if decoded.Gauge != original.Gauge {
		t.Errorf("Gauge mismatch: got %d, want %d", decoded.Gauge, original.Gauge)
	}
}

func TestBatch44GuardianDeal(t *testing.T) {
	original := &dnfv1.GuardianDeal{
		Charguid:     123456789,
		Guardiandeal: 1000,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuardianDeal{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Charguid != original.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", decoded.Charguid, original.Charguid)
	}
	if decoded.Guardiandeal != original.Guardiandeal {
		t.Errorf("Guardiandeal mismatch: got %d, want %d", decoded.Guardiandeal, original.Guardiandeal)
	}
}

func TestBatch44MainEventData(t *testing.T) {
	original := &dnfv1.MainEventData{
		Index:     1,
		Status:    1,
		Count:     5,
		Value:     100,
		DateStart: int64(time.Now().Unix()),
		DateEnd:   int64(time.Now().Unix() + 86400),
		Sub: []*dnfv1.EventData{
			{
				Index:  1,
				Status: 1,
				Count:  5,
				Value:  100,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.MainEventData{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if len(decoded.Sub) != len(original.Sub) {
		t.Errorf("Sub length mismatch: got %d, want %d", len(decoded.Sub), len(original.Sub))
	}
}
