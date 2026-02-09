package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试物品GUID
func TestBatch31ItemGuid(t *testing.T) {
	itemGuid := &dnfv1.ItemGuid{
		Guid: 123456789,
	}

	data, err := proto.Marshal(itemGuid)
	if err != nil {
		t.Fatalf("Failed to marshal ItemGuid: %v", err)
	}

	unmarshaled := &dnfv1.ItemGuid{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ItemGuid: %v", err)
	}

	if unmarshaled.Guid != itemGuid.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, itemGuid.Guid)
	}
}

// 测试槽位附魔经验信息
func TestBatch31SlotEnchantExp(t *testing.T) {
	slotEnchantExp := &dnfv1.SlotEnchantExp{
		Etype: 1,
		Exp:   100,
	}

	data, err := proto.Marshal(slotEnchantExp)
	if err != nil {
		t.Fatalf("Failed to marshal SlotEnchantExp: %v", err)
	}

	unmarshaled := &dnfv1.SlotEnchantExp{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SlotEnchantExp: %v", err)
	}

	if unmarshaled.Etype != slotEnchantExp.Etype {
		t.Errorf("Etype mismatch: got %d, want %d", unmarshaled.Etype, slotEnchantExp.Etype)
	}

	if unmarshaled.Exp != slotEnchantExp.Exp {
		t.Errorf("Exp mismatch: got %d, want %d", unmarshaled.Exp, slotEnchantExp.Exp)
	}
}

// 测试槽位附魔信息
func TestBatch31SlotEnchantInfo(t *testing.T) {
	slotEnchantExp1 := &dnfv1.SlotEnchantExp{
		Etype: 1,
		Exp:   100,
	}

	slotEnchantExp2 := &dnfv1.SlotEnchantExp{
		Etype: 2,
		Exp:   200,
	}

	slotEnchantInfo := &dnfv1.SlotEnchantInfo{
		Slot:   1,
		Evalue: []*dnfv1.SlotEnchantExp{slotEnchantExp1, slotEnchantExp2},
	}

	data, err := proto.Marshal(slotEnchantInfo)
	if err != nil {
		t.Fatalf("Failed to marshal SlotEnchantInfo: %v", err)
	}

	unmarshaled := &dnfv1.SlotEnchantInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SlotEnchantInfo: %v", err)
	}

	if unmarshaled.Slot != slotEnchantInfo.Slot {
		t.Errorf("Slot mismatch: got %d, want %d", unmarshaled.Slot, slotEnchantInfo.Slot)
	}

	if len(unmarshaled.Evalue) != len(slotEnchantInfo.Evalue) {
		t.Errorf("Evalue length mismatch: got %d, want %d", len(unmarshaled.Evalue), len(slotEnchantInfo.Evalue))
	}

	for i, exp := range unmarshaled.Evalue {
		if exp.Etype != slotEnchantInfo.Evalue[i].Etype {
			t.Errorf("Evalue[%d].Etype mismatch: got %d, want %d", i, exp.Etype, slotEnchantInfo.Evalue[i].Etype)
		}
		if exp.Exp != slotEnchantInfo.Evalue[i].Exp {
			t.Errorf("Evalue[%d].Exp mismatch: got %d, want %d", i, exp.Exp, slotEnchantInfo.Evalue[i].Exp)
		}
	}
}
