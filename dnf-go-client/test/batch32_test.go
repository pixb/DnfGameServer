package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试自定义数据
func TestBatch32CustomData(t *testing.T) {
	customData := &dnfv1.CustomData{
		Type:       1,
		Senderguid: 123456789,
		IValue:     100,
		FValue:     3.14,
	}

	data, err := proto.Marshal(customData)
	if err != nil {
		t.Fatalf("Failed to marshal CustomData: %v", err)
	}

	unmarshaled := &dnfv1.CustomData{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal CustomData: %v", err)
	}

	if unmarshaled.Type != customData.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, customData.Type)
	}

	if unmarshaled.Senderguid != customData.Senderguid {
		t.Errorf("Senderguid mismatch: got %d, want %d", unmarshaled.Senderguid, customData.Senderguid)
	}

	if unmarshaled.IValue != customData.IValue {
		t.Errorf("IValue mismatch: got %d, want %d", unmarshaled.IValue, customData.IValue)
	}

	if unmarshaled.FValue != customData.FValue {
		t.Errorf("FValue mismatch: got %f, want %f", unmarshaled.FValue, customData.FValue)
	}
}

// 测试怪物经验信息
func TestBatch32MonsterExp(t *testing.T) {
	monsterExp := &dnfv1.MonsterExp{
		Playercount: 4,
		Charguid:    123456789,
		Exp:         1000,
	}

	data, err := proto.Marshal(monsterExp)
	if err != nil {
		t.Fatalf("Failed to marshal MonsterExp: %v", err)
	}

	unmarshaled := &dnfv1.MonsterExp{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MonsterExp: %v", err)
	}

	if unmarshaled.Playercount != monsterExp.Playercount {
		t.Errorf("Playercount mismatch: got %d, want %d", unmarshaled.Playercount, monsterExp.Playercount)
	}

	if unmarshaled.Charguid != monsterExp.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", unmarshaled.Charguid, monsterExp.Charguid)
	}

	if unmarshaled.Exp != monsterExp.Exp {
		t.Errorf("Exp mismatch: got %d, want %d", unmarshaled.Exp, monsterExp.Exp)
	}
}

// 测试隐藏聊天信息
func TestBatch32HiddenChattingInfo(t *testing.T) {
	hiddenChattingInfo := &dnfv1.HiddenChattingInfo{
		Type:     1,
		Charguid: 123456789,
		Indexes:  []int64{100, 200, 300},
	}

	data, err := proto.Marshal(hiddenChattingInfo)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChattingInfo: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChattingInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChattingInfo: %v", err)
	}

	if unmarshaled.Type != hiddenChattingInfo.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, hiddenChattingInfo.Type)
	}

	if unmarshaled.Charguid != hiddenChattingInfo.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", unmarshaled.Charguid, hiddenChattingInfo.Charguid)
	}

	if len(unmarshaled.Indexes) != len(hiddenChattingInfo.Indexes) {
		t.Errorf("Indexes length mismatch: got %d, want %d", len(unmarshaled.Indexes), len(hiddenChattingInfo.Indexes))
	}

	for i, index := range unmarshaled.Indexes {
		if index != hiddenChattingInfo.Indexes[i] {
			t.Errorf("Indexes[%d] mismatch: got %d, want %d", i, index, hiddenChattingInfo.Indexes[i])
		}
	}
}
