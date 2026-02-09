package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试钻石商店货币信息
func TestBatch27CeraShopMoney(t *testing.T) {
	ceraShopMoney := &dnfv1.CeraShopMoney{
		Index: 1,
		Count: 100,
		Value: 1000,
	}

	data, err := proto.Marshal(ceraShopMoney)
	if err != nil {
		t.Fatalf("Failed to marshal CeraShopMoney: %v", err)
	}

	unmarshaled := &dnfv1.CeraShopMoney{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal CeraShopMoney: %v", err)
	}

	if unmarshaled.Index != ceraShopMoney.Index {
		t.Errorf("Index mismatch: got %d, want %d", unmarshaled.Index, ceraShopMoney.Index)
	}

	if unmarshaled.Count != ceraShopMoney.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, ceraShopMoney.Count)
	}

	if unmarshaled.Value != ceraShopMoney.Value {
		t.Errorf("Value mismatch: got %d, want %d", unmarshaled.Value, ceraShopMoney.Value)
	}
}

// 测试餐厅支付信息
func TestBatch27DiningPay(t *testing.T) {
	diningPay := &dnfv1.DiningPay{
		Index: 1,
		Type:  2,
		Count: 3,
	}

	data, err := proto.Marshal(diningPay)
	if err != nil {
		t.Fatalf("Failed to marshal DiningPay: %v", err)
	}

	unmarshaled := &dnfv1.DiningPay{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal DiningPay: %v", err)
	}

	if unmarshaled.Index != diningPay.Index {
		t.Errorf("Index mismatch: got %d, want %d", unmarshaled.Index, diningPay.Index)
	}

	if unmarshaled.Type != diningPay.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, diningPay.Type)
	}

	if unmarshaled.Count != diningPay.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, diningPay.Count)
	}
}

// 测试守护者订单信息
func TestBatch27GuardianOrder(t *testing.T) {
	guardianOrder := &dnfv1.GuardianOrder{
		Guardiandeal:   123456789,
		Growtype:       1,
		Secondgrowtype: 2,
		Job:            3,
		Name:            "TestGuardian",
		Level:           50,
	}

	data, err := proto.Marshal(guardianOrder)
	if err != nil {
		t.Fatalf("Failed to marshal GuardianOrder: %v", err)
	}

	unmarshaled := &dnfv1.GuardianOrder{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuardianOrder: %v", err)
	}

	if unmarshaled.Guardiandeal != guardianOrder.Guardiandeal {
		t.Errorf("Guardiandeal mismatch: got %d, want %d", unmarshaled.Guardiandeal, guardianOrder.Guardiandeal)
	}

	if unmarshaled.Growtype != guardianOrder.Growtype {
		t.Errorf("Growtype mismatch: got %d, want %d", unmarshaled.Growtype, guardianOrder.Growtype)
	}

	if unmarshaled.Secondgrowtype != guardianOrder.Secondgrowtype {
		t.Errorf("Secondgrowtype mismatch: got %d, want %d", unmarshaled.Secondgrowtype, guardianOrder.Secondgrowtype)
	}

	if unmarshaled.Job != guardianOrder.Job {
		t.Errorf("Job mismatch: got %d, want %d", unmarshaled.Job, guardianOrder.Job)
	}

	if unmarshaled.Name != guardianOrder.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, guardianOrder.Name)
	}

	if unmarshaled.Level != guardianOrder.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, guardianOrder.Level)
	}
}
