package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch47SystemBuff(t *testing.T) {
	original := &dnfv1.RES_SYSTEM_BUFF_APPENDAGE_LIST{
		Error: 0,
		Appendages: []*dnfv1.SystemBuffAppendage{
			{},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RES_SYSTEM_BUFF_APPENDAGE_LIST{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Appendages) != len(original.Appendages) {
		t.Errorf("Appendages length mismatch: got %d, want %d", len(decoded.Appendages), len(original.Appendages))
	}
}

func TestBatch47PartyInfo(t *testing.T) {
	original := &dnfv1.RES_SEARCH_PARTY_LIST{
		Error: 0,
		Parties: []*dnfv1.PartyInfo{
			{
				Id:          1,
				Name:        "Test Party",
				MemberCount: 3,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RES_SEARCH_PARTY_LIST{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Parties) != len(original.Parties) {
		t.Errorf("Parties length mismatch: got %d, want %d", len(decoded.Parties), len(original.Parties))
	}
}

func TestBatch47Wardrobe(t *testing.T) {
	original := &dnfv1.RES_WARDROBE_INFO{
		Error: 0,
		Slots: []*dnfv1.WardrobeSlot{
			{
				Id:       1,
				ItemId:   1001,
				Occupied: true,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RES_WARDROBE_INFO{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Slots) != len(original.Slots) {
		t.Errorf("Slots length mismatch: got %d, want %d", len(decoded.Slots), len(original.Slots))
	}
}

func TestBatch47Tonic(t *testing.T) {
	original := &dnfv1.RES_TONIC_INFO{
		Error: 0,
		Tonics: []*dnfv1.TonicInfo{
			{
				Level: 5,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RES_TONIC_INFO{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Tonics) != len(original.Tonics) {
		t.Errorf("Tonics length mismatch: got %d, want %d", len(decoded.Tonics), len(original.Tonics))
	}
}

func TestBatch47WorldBoss(t *testing.T) {
	original := &dnfv1.RES_WORLD_BOSS_INFO{
		Error:         0,
		BossId:        1,
		BossName:      "Test Boss",
		Hp:            1000000,
		MaxHp:         2000000,
		RemainingTime: 3600,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RES_WORLD_BOSS_INFO{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if decoded.BossName != original.BossName {
		t.Errorf("BossName mismatch: got %s, want %s", decoded.BossName, original.BossName)
	}
	if decoded.Hp != original.Hp {
		t.Errorf("Hp mismatch: got %d, want %d", decoded.Hp, original.Hp)
	}
}
