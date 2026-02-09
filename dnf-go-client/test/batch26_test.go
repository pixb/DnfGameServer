package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试系统buff附加列表响应
func TestBatch26ResSystemBuffAppendageList(t *testing.T) {
	systemBuffAppendageList := &dnfv1.ResSystemBuffAppendageList{
		Error: 0,
		Time:  1770648000,
		Appendages: []*dnfv1.SystemBuffAppendage{
			{
				Index:   1,
				Endtime: 1770651600,
				Values:  []int32{100, 200, 300},
			},
			{
				Index:   2,
				Endtime: 1770655200,
				Values:  []int32{400, 500},
			},
		},
	}

	data, err := proto.Marshal(systemBuffAppendageList)
	if err != nil {
		t.Fatalf("Failed to marshal ResSystemBuffAppendageList: %v", err)
	}

	unmarshaled := &dnfv1.ResSystemBuffAppendageList{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ResSystemBuffAppendageList: %v", err)
	}

	if unmarshaled.Error != systemBuffAppendageList.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, systemBuffAppendageList.Error)
	}

	if unmarshaled.Time != systemBuffAppendageList.Time {
		t.Errorf("Time mismatch: got %d, want %d", unmarshaled.Time, systemBuffAppendageList.Time)
	}

	if len(unmarshaled.Appendages) != len(systemBuffAppendageList.Appendages) {
		t.Errorf("Appendages length mismatch: got %d, want %d", len(unmarshaled.Appendages), len(systemBuffAppendageList.Appendages))
	}
}

// 测试战斗选项
func TestBatch26BattleOption(t *testing.T) {
	battleOption := &dnfv1.BattleOption{
		Type:  dnfv1.BattleOptionType_BATTLE_OPTION_TYPE_EVADE,
		Value: true,
	}

	data, err := proto.Marshal(battleOption)
	if err != nil {
		t.Fatalf("Failed to marshal BattleOption: %v", err)
	}

	unmarshaled := &dnfv1.BattleOption{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal BattleOption: %v", err)
	}

	if unmarshaled.Type != battleOption.Type {
		t.Errorf("Type mismatch: got %v, want %v", unmarshaled.Type, battleOption.Type)
	}

	if unmarshaled.Value != battleOption.Value {
		t.Errorf("Value mismatch: got %v, want %v", unmarshaled.Value, battleOption.Value)
	}
}

// 测试随机选项选择响应
func TestBatch26ResRandomOptionSelect(t *testing.T) {
	randomOptionSelect := &dnfv1.ResRandomOptionSelect{
		Error:     0,
		Guid:      123456789,
		Type:      1,
		EquipGuid: 987654321,
		EquipType: 2,
	}

	data, err := proto.Marshal(randomOptionSelect)
	if err != nil {
		t.Fatalf("Failed to marshal ResRandomOptionSelect: %v", err)
	}

	unmarshaled := &dnfv1.ResRandomOptionSelect{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ResRandomOptionSelect: %v", err)
	}

	if unmarshaled.Error != randomOptionSelect.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, randomOptionSelect.Error)
	}

	if unmarshaled.Guid != randomOptionSelect.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, randomOptionSelect.Guid)
	}

	if unmarshaled.Type != randomOptionSelect.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, randomOptionSelect.Type)
	}

	if unmarshaled.EquipGuid != randomOptionSelect.EquipGuid {
		t.Errorf("EquipGuid mismatch: got %d, want %d", unmarshaled.EquipGuid, randomOptionSelect.EquipGuid)
	}

	if unmarshaled.EquipType != randomOptionSelect.EquipType {
		t.Errorf("EquipType mismatch: got %d, want %d", unmarshaled.EquipType, randomOptionSelect.EquipType)
	}
}
