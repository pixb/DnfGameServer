package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestCharacterListRequest_Serialization(t *testing.T) {
	req := &dnfv1.CharacterListRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.CharacterListRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}
}

func TestCharacterListResponse_Serialization(t *testing.T) {
	res := &dnfv1.CharacterListResponse{
		Error:                    0,
		Version:                  1,
		AdventureUnionLevel:       10,
		AdventureUnionExp:         1000,
		DailyCreateCharCount:       1,
		DailyCreateCharMaxCount:   3,
		AdventureUnionName:        "AdventureUnion",
		MaxCount:                 5,
		IgnoreContentsBlacklist:    "blacklist",
	}

	data, err := proto.Marshal(res)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedRes dnfv1.CharacterListResponse
	if err := proto.Unmarshal(data, &parsedRes); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedRes.Error != res.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, res.Error)
	}
	if parsedRes.Version != res.Version {
		t.Errorf("Version mismatch: got %d, want %d", parsedRes.Version, res.Version)
	}
	if parsedRes.AdventureUnionLevel != res.AdventureUnionLevel {
		t.Errorf("AdventureUnionLevel mismatch: got %d, want %d", parsedRes.AdventureUnionLevel, res.AdventureUnionLevel)
	}
	if parsedRes.AdventureUnionExp != res.AdventureUnionExp {
		t.Errorf("AdventureUnionExp mismatch: got %d, want %d", parsedRes.AdventureUnionExp, res.AdventureUnionExp)
	}
}

func TestJobLimitInfo_Serialization(t *testing.T) {
	limits := []*dnfv1.JobLimitInfo{
		{
			Job:      1,
			GrowType: 1,
			Level:     10,
		},
		{
			Job:      2,
			GrowType: 1,
			Level:     20,
		},
	}

	for _, limit := range limits {
		data, err := proto.Marshal(limit)
		if err != nil {
			t.Fatalf("Marshal failed: %v", err)
		}

		var parsedLimit dnfv1.JobLimitInfo
		if err := proto.Unmarshal(data, &parsedLimit); err != nil {
			t.Fatalf("Unmarshal failed: %v", err)
		}

		if parsedLimit.Job != limit.Job {
			t.Errorf("Job mismatch: got %d, want %d", parsedLimit.Job, limit.Job)
		}
		if parsedLimit.GrowType != limit.GrowType {
			t.Errorf("GrowType mismatch: got %d, want %d", parsedLimit.GrowType, limit.GrowType)
		}
		if parsedLimit.Level != limit.Level {
			t.Errorf("Level mismatch: got %d, want %d", parsedLimit.Level, limit.Level)
		}
	}
}

func TestCharacter_Serialization(t *testing.T) {
	char := &dnfv1.CharacterWithEquipList{
		CharGuid:           1234567890,
		LastLogout:         1234567890,
		GrowType:           1,
		SecGrowType:        0,
		Job:                1,
		Level:              50,
		Name:               "TestCharacter",
		Fatigue:            100,
		EquipScore:          1000,
		CharacterFrame:      1,
		AvatarVisibleFlags:  0xFFFFFFFF,
		DeletionStatus:     0,
		DeletionTime:       0,
		CreateTime:         1234567890,
		ChangeName:         false,
	}

	data, err := proto.Marshal(char)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedChar dnfv1.CharacterWithEquipList
	if err := proto.Unmarshal(data, &parsedChar); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedChar.CharGuid != char.CharGuid {
		t.Errorf("CharGuid mismatch: got %d, want %d", parsedChar.CharGuid, char.CharGuid)
	}
	if parsedChar.Level != char.Level {
		t.Errorf("Level mismatch: got %d, want %d", parsedChar.Level, char.Level)
	}
	if parsedChar.Name != char.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsedChar.Name, char.Name)
	}
}

func TestEquipIndexSlot_Serialization(t *testing.T) {
	slot := &dnfv1.EquipIndexSlot{
		Index:     1,
		Slot:      2,
		Reforge:    3,
		Reforgeexp: 100,
		Upgrade:    5,
	}

	data, err := proto.Marshal(slot)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedSlot dnfv1.EquipIndexSlot
	if err := proto.Unmarshal(data, &parsedSlot); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedSlot.Index != slot.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsedSlot.Index, slot.Index)
	}
	if parsedSlot.Slot != slot.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", parsedSlot.Slot, slot.Slot)
	}
	if parsedSlot.Reforge != slot.Reforge {
		t.Errorf("Reforge mismatch: got %d, want %d", parsedSlot.Reforge, slot.Reforge)
	}
}

func TestAvatarIndexSlot_Serialization(t *testing.T) {
	slot := &dnfv1.AvatarIndexSlot{
		Index: 1,
		Slot:  2,
	}

	data, err := proto.Marshal(slot)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedSlot dnfv1.AvatarIndexSlot
	if err := proto.Unmarshal(data, &parsedSlot); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedSlot.Index != slot.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsedSlot.Index, slot.Index)
	}
	if parsedSlot.Slot != slot.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", parsedSlot.Slot, slot.Slot)
	}
}

func TestCharacterListResponse_BoundaryValues(t *testing.T) {
	testCases := []struct {
		name string
		res  *dnfv1.CharacterListResponse
	}{
		{
			name: "zero values",
			res: &dnfv1.CharacterListResponse{
				Error:                    0,
				Version:                  0,
				AdventureUnionLevel:       0,
				AdventureUnionExp:         0,
				DailyCreateCharCount:       0,
				DailyCreateCharMaxCount:   0,
				MaxCount:                 0,
				AdventureUnionName:        "",
				IgnoreContentsBlacklist:    "",
			},
		},
		{
			name: "max values",
			res: &dnfv1.CharacterListResponse{
				Error:                    2147483647,
				Version:                  2147483647,
				AdventureUnionLevel:       2147483647,
				AdventureUnionExp:         9223372036854775807,
				DailyCreateCharCount:       2147483647,
				DailyCreateCharMaxCount:   2147483647,
				MaxCount:                 2147483647,
				AdventureUnionName:        "MaxAdventureUnion",
				IgnoreContentsBlacklist:    "MaxBlacklist",
			},
		},
		{
			name: "negative values",
			res: &dnfv1.CharacterListResponse{
				Error:                    -1,
				Version:                  -1,
				AdventureUnionLevel:       -1,
				AdventureUnionExp:         -1,
				DailyCreateCharCount:       -1,
				DailyCreateCharMaxCount:   -1,
				MaxCount:                 -1,
			},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			data, err := proto.Marshal(tc.res)
			if err != nil {
				t.Fatalf("Marshal failed: %v", err)
			}

			var parsedRes dnfv1.CharacterListResponse
			if err := proto.Unmarshal(data, &parsedRes); err != nil {
				t.Fatalf("Unmarshal failed: %v", err)
			}

			if parsedRes.Error != tc.res.Error {
				t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, tc.res.Error)
			}
			if parsedRes.Version != tc.res.Version {
				t.Errorf("Version mismatch: got %d, want %d", parsedRes.Version, tc.res.Version)
			}
			if parsedRes.AdventureUnionLevel != tc.res.AdventureUnionLevel {
				t.Errorf("AdventureUnionLevel mismatch: got %d, want %d", parsedRes.AdventureUnionLevel, tc.res.AdventureUnionLevel)
			}
		})
	}
}
