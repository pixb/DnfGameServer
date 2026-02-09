package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试技能槽位请求消息
func TestBatch11SkillSlotRequest(t *testing.T) {
	req := &dnfv1.SkillSlotRequest{
		CharacterId: 1,
		Page:        1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal SkillSlotRequest: %v", err)
	}

	unmarshaled := &dnfv1.SkillSlotRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SkillSlotRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.Page != req.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, req.Page)
	}
}

// 测试技能槽位响应消息
func TestBatch11SkillSlotResponse(t *testing.T) {
	resp := &dnfv1.SkillSlotResponse{
		Error:       0,
		CharacterId: 1,
		Page:        1,
		MaxPage:     5,
		Slots: []*dnfv1.SkillSlotInfo{
			{
				SlotId:  1,
				SkillId: 101,
				Level:   5,
				Hotkey:  1,
			},
			{
				SlotId:  2,
				SkillId: 102,
				Level:   3,
				Hotkey:  2,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal SkillSlotResponse: %v", err)
	}

	unmarshaled := &dnfv1.SkillSlotResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SkillSlotResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if len(unmarshaled.Slots) != len(resp.Slots) {
		t.Errorf("Slots length mismatch: got %d, want %d", len(unmarshaled.Slots), len(resp.Slots))
	}
}

// 测试技能设置请求消息
func TestBatch11SkillSetRequest(t *testing.T) {
	req := &dnfv1.SkillSetRequest{
		CharacterId: 1,
		Skills: []*dnfv1.SkillSlotSkillInfo{
			{
				SkillId:    101,
				Level:      5,
				IsEquipped: true,
			},
			{
				SkillId:    102,
				Level:      3,
				IsEquipped: false,
			},
		},
		Slots: []*dnfv1.SkillSlotInfo{
			{
				SlotId:  1,
				SkillId: 101,
				Level:   5,
				Hotkey:  1,
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal SkillSetRequest: %v", err)
	}

	unmarshaled := &dnfv1.SkillSetRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SkillSetRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if len(unmarshaled.Skills) != len(req.Skills) {
		t.Errorf("Skills length mismatch: got %d, want %d", len(unmarshaled.Skills), len(req.Skills))
	}

	if len(unmarshaled.Slots) != len(req.Slots) {
		t.Errorf("Slots length mismatch: got %d, want %d", len(unmarshaled.Slots), len(req.Slots))
	}
}

// 测试技能设置响应消息
func TestBatch11SkillSetResponse(t *testing.T) {
	resp := &dnfv1.SkillSetResponse{
		Error:       0,
		Success:     true,
		CharacterId: 1,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal SkillSetResponse: %v", err)
	}

	unmarshaled := &dnfv1.SkillSetResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SkillSetResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}
}

// 测试技能槽位信息
func TestBatch11SkillSlotInfo(t *testing.T) {
	slotInfo := &dnfv1.SkillSlotInfo{
		SlotId:  1,
		SkillId: 101,
		Level:   5,
		Hotkey:  1,
	}

	data, err := proto.Marshal(slotInfo)
	if err != nil {
		t.Fatalf("Failed to marshal SkillSlotInfo: %v", err)
	}

	unmarshaled := &dnfv1.SkillSlotInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SkillSlotInfo: %v", err)
	}

	if unmarshaled.SlotId != slotInfo.SlotId {
		t.Errorf("SlotId mismatch: got %d, want %d", unmarshaled.SlotId, slotInfo.SlotId)
	}

	if unmarshaled.SkillId != slotInfo.SkillId {
		t.Errorf("SkillId mismatch: got %d, want %d", unmarshaled.SkillId, slotInfo.SkillId)
	}

	if unmarshaled.Level != slotInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, slotInfo.Level)
	}

	if unmarshaled.Hotkey != slotInfo.Hotkey {
		t.Errorf("Hotkey mismatch: got %d, want %d", unmarshaled.Hotkey, slotInfo.Hotkey)
	}
}

// 测试技能槽位技能信息
func TestBatch11SkillSlotSkillInfo(t *testing.T) {
	skillInfo := &dnfv1.SkillSlotSkillInfo{
		SkillId:    101,
		Level:      5,
		IsEquipped: true,
	}

	data, err := proto.Marshal(skillInfo)
	if err != nil {
		t.Fatalf("Failed to marshal SkillSlotSkillInfo: %v", err)
	}

	unmarshaled := &dnfv1.SkillSlotSkillInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SkillSlotSkillInfo: %v", err)
	}

	if unmarshaled.SkillId != skillInfo.SkillId {
		t.Errorf("SkillId mismatch: got %d, want %d", unmarshaled.SkillId, skillInfo.SkillId)
	}

	if unmarshaled.Level != skillInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, skillInfo.Level)
	}

	if unmarshaled.IsEquipped != skillInfo.IsEquipped {
		t.Errorf("IsEquipped mismatch: got %v, want %v", unmarshaled.IsEquipped, skillInfo.IsEquipped)
	}
}
