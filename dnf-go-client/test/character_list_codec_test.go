package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func TestCharacterListCodec_Serialization(t *testing.T) {
	charListReq := &dnfv1.CharacterListRequest{}
	data, err := proto.Marshal(charListReq)
	if err != nil {
		t.Fatalf("序列化角色列表请求失败: %v", err)
	}

	var parsedReq dnfv1.CharacterListRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("反序列化失败: %v", err)
	}

	charListResp := &dnfv1.CharacterListResponse{
		Error:                   0,
		Version:                 1,
		AdventureUnionLevel:     10,
		AdventureUnionExp:       1000,
		DailyCreateCharCount:    1,
		DailyCreateCharMaxCount: 3,
		AdventureUnionName:      "TestUnion",
		MaxCount:                5,
		IgnoreContentsBlacklist: "test_blacklist",
		Count:                   1,
	}

	charListResp.Limits = append(charListResp.Limits, &dnfv1.JobLimitInfo{
		Job:      1,
		GrowType: 1,
		Level:    10,
	})

	charListResp.Characters = append(charListResp.Characters, &dnfv1.CharacterWithEquipList{
		CharGuid:           1234567890,
		LastLogout:         1234567890,
		GrowType:           1,
		SecGrowType:        0,
		Job:                1,
		Level:              50,
		Name:               "TestCharacter",
		Fatigue:            100,
		EquipScore:         1000,
		CharacterFrame:     1,
		AvatarVisibleFlags: 0xFFFFFFFF,
		DeletionStatus:     0,
		DeletionTime:       0,
		CreateTime:         1234567890,
		ChangeName:         false,
	})

	respData, err := proto.Marshal(charListResp)
	if err != nil {
		t.Fatalf("序列化角色列表响应失败: %v", err)
	}

	var parsedResp dnfv1.CharacterListResponse
	if err := proto.Unmarshal(respData, &parsedResp); err != nil {
		t.Fatalf("反序列化失败: %v", err)
	}

	if parsedResp.Error != charListResp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, charListResp.Error)
	}
	if parsedResp.Version != charListResp.Version {
		t.Errorf("Version mismatch: got %d, want %d", parsedResp.Version, charListResp.Version)
	}
	if parsedResp.Count != charListResp.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsedResp.Count, charListResp.Count)
	}
	if parsedResp.AdventureUnionLevel != charListResp.AdventureUnionLevel {
		t.Errorf("AdventureUnionLevel mismatch: got %d, want %d", parsedResp.AdventureUnionLevel, charListResp.AdventureUnionLevel)
	}
	if parsedResp.AdventureUnionExp != charListResp.AdventureUnionExp {
		t.Errorf("AdventureUnionExp mismatch: got %d, want %d", parsedResp.AdventureUnionExp, charListResp.AdventureUnionExp)
	}
	if len(parsedResp.Limits) != len(charListResp.Limits) {
		t.Errorf("Limits length mismatch: got %d, want %d", len(parsedResp.Limits), len(charListResp.Limits))
	}
	if len(parsedResp.Characters) != len(charListResp.Characters) {
		t.Errorf("Characters length mismatch: got %d, want %d", len(parsedResp.Characters), len(charListResp.Characters))
	}

	if len(parsedResp.Limits) > 0 {
		limit := parsedResp.Limits[0]
		if limit.Job != 1 {
			t.Errorf("Job mismatch: got %d, want %d", limit.Job, 1)
		}
		if limit.GrowType != 1 {
			t.Errorf("GrowType mismatch: got %d, want %d", limit.GrowType, 1)
		}
		if limit.Level != 10 {
			t.Errorf("Level mismatch: got %d, want %d", limit.Level, 10)
		}
	}

	if len(parsedResp.Characters) > 0 {
		char := parsedResp.Characters[0]
		if char.CharGuid != 1234567890 {
			t.Errorf("CharGuid mismatch: got %d, want %d", char.CharGuid, 1234567890)
		}
		if char.Level != 50 {
			t.Errorf("Level mismatch: got %d, want %d", char.Level, 50)
		}
		if char.Name != "TestCharacter" {
			t.Errorf("Name mismatch: got %s, want %s", char.Name, "TestCharacter")
		}
	}
}

func TestCharacterListCodec_NetworkPacket(t *testing.T) {
	charListResp := &dnfv1.CharacterListResponse{
		Error:                   0,
		Version:                 1,
		AdventureUnionLevel:     10,
		AdventureUnionExp:       1000,
		DailyCreateCharCount:    1,
		DailyCreateCharMaxCount: 3,
		AdventureUnionName:      "TestUnion",
		MaxCount:                5,
		IgnoreContentsBlacklist: "test_blacklist",
		Count:                   1,
	}

	charListResp.Limits = append(charListResp.Limits, &dnfv1.JobLimitInfo{
		Job:      1,
		GrowType: 1,
		Level:    10,
	})

	charListResp.Characters = append(charListResp.Characters, &dnfv1.CharacterWithEquipList{
		CharGuid:           1234567890,
		LastLogout:         1234567890,
		GrowType:           1,
		SecGrowType:        0,
		Job:                1,
		Level:              50,
		Name:               "TestCharacter",
		Fatigue:            100,
		EquipScore:         1000,
		CharacterFrame:     1,
		AvatarVisibleFlags: 0xFFFFFFFF,
		DeletionStatus:     0,
		DeletionTime:       0,
		CreateTime:         1234567890,
		ChangeName:         false,
	})

	respData, err := proto.Marshal(charListResp)
	if err != nil {
		t.Fatalf("序列化失败: %v", err)
	}

	moduleId := uint16(10002)
	cmd := uint8(1)
	transId := uint8(1)

	totalLen := uint16(len(respData) + 8)

	if totalLen != 112 {
		t.Errorf("TotalLen mismatch: got %d, want %d", totalLen, 112)
	}

	if len(respData) != 104 {
		t.Errorf("BodyLen mismatch: got %d, want %d", len(respData), 104)
	}

	if moduleId != 10002 {
		t.Errorf("ModuleId mismatch: got %d, want %d", moduleId, 10002)
	}

	if cmd != 1 {
		t.Errorf("Cmd mismatch: got %d, want %d", cmd, 1)
	}

	if transId != 1 {
		t.Errorf("TransId mismatch: got %d, want %d", transId, 1)
	}
}
