package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch46UserInfo(t *testing.T) {
	original := &dnfv1.USER_INFO{
		Charguid:      123456789,
		Job:           1,
		Growtype:      2,
		Secgrowtype:   3,
		TeamType:      4,
		World:         5,
		Level:         60,
		Name:          "TestUser",
		ProfileURL:    "http://example.com/profile",
		ProfileName:   "TestProfile",
		CharacterFrame: 1,
		Rank:          10,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.USER_INFO{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Charguid != original.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", decoded.Charguid, original.Charguid)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
	if decoded.Level != original.Level {
		t.Errorf("Level mismatch: got %d, want %d", decoded.Level, original.Level)
	}
}

func TestBatch46SkillInfo(t *testing.T) {
	original := &dnfv1.RES_SKILL_INFO{
		Error:   0,
		Type:    1,
		Sp:      100,
		Addsp:   10,
		Tp:      50,
		Addtp:   5,
		Skilllist: []*dnfv1.PT_SKILL{
			{
				Id:      1,
				Level:   5,
				Learned: true,
			},
			{
				Id:      2,
				Level:   3,
				Learned: false,
			},
		},
		Skillslot: &dnfv1.PT_ALL_SKILL_SLOT{
			Skills: []int32{1, 2, 3, 4, 5},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RES_SKILL_INFO{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if decoded.Sp != original.Sp {
		t.Errorf("SP mismatch: got %d, want %d", decoded.Sp, original.Sp)
	}
	if len(decoded.Skilllist) != len(original.Skilllist) {
		t.Errorf("Skill list length mismatch: got %d, want %d", len(decoded.Skilllist), len(original.Skilllist))
	}
}

func TestBatch46StageInfo(t *testing.T) {
	original := &dnfv1.RES_STAGE_INFO{
		Error: 0,
		Map: &dnfv1.PT_MAP_INFO{
			Id:   1,
			Name: "TestMap",
			Type: 2,
		},
		Mapsize: 100,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RES_STAGE_INFO{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if decoded.Map.Name != original.Map.Name {
		t.Errorf("Map name mismatch: got %s, want %s", decoded.Map.Name, original.Map.Name)
	}
}

func TestBatch46ChatMessage(t *testing.T) {
	original := &dnfv1.SEND_CHAT{
		Content: "Hello World",
		Type:    1,
		Target:  "All",
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.SEND_CHAT{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Content != original.Content {
		t.Errorf("Content mismatch: got %s, want %s", decoded.Content, original.Content)
	}
	if decoded.Type != original.Type {
		t.Errorf("Type mismatch: got %d, want %d", decoded.Type, original.Type)
	}
}

func TestBatch46StreamData(t *testing.T) {
	original := &dnfv1.STREAM_DATA{
		Type: 1,
		Data: []byte("test data"),
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.STREAM_DATA{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Type != original.Type {
		t.Errorf("Type mismatch: got %d, want %d", decoded.Type, original.Type)
	}
	if string(decoded.Data) != string(original.Data) {
		t.Errorf("Data mismatch: got %s, want %s", string(decoded.Data), string(original.Data))
	}
}
