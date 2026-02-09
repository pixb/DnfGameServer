package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试技能信息
func TestBatch29Skill(t *testing.T) {
	skill := &dnfv1.Skill{
		Index: 1,
		Level: 10,
	}

	data, err := proto.Marshal(skill)
	if err != nil {
		t.Fatalf("Failed to marshal Skill: %v", err)
	}

	unmarshaled := &dnfv1.Skill{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal Skill: %v", err)
	}

	if unmarshaled.Index != skill.Index {
		t.Errorf("Index mismatch: got %d, want %d", unmarshaled.Index, skill.Index)
	}

	if unmarshaled.Level != skill.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, skill.Level)
	}
}

// 测试掉落对象金币信息
func TestBatch29DropObjectGold(t *testing.T) {
	dropObjectGold := &dnfv1.DropObjectGold{
		Charguid: 123456789,
		Gold:     10000,
	}

	data, err := proto.Marshal(dropObjectGold)
	if err != nil {
		t.Fatalf("Failed to marshal DropObjectGold: %v", err)
	}

	unmarshaled := &dnfv1.DropObjectGold{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal DropObjectGold: %v", err)
	}

	if unmarshaled.Charguid != dropObjectGold.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", unmarshaled.Charguid, dropObjectGold.Charguid)
	}

	if unmarshaled.Gold != dropObjectGold.Gold {
		t.Errorf("Gold mismatch: got %d, want %d", unmarshaled.Gold, dropObjectGold.Gold)
	}
}
