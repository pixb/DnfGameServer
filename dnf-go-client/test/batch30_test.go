package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试可堆叠物品信息
func TestBatch30Stackable(t *testing.T) {
	stackable := &dnfv1.Stackable{
		Index:           1,
		Count:           100,
		Bind:            true,
		Scount:           5,
		Tcount:           10,
		Expiretime:       1770648000,
		Acquisitiontime:  1770648100,
		Sindex:           2,
		Slotindex:        3,
	}

	data, err := proto.Marshal(stackable)
	if err != nil {
		t.Fatalf("Failed to marshal Stackable: %v", err)
	}

	unmarshaled := &dnfv1.Stackable{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal Stackable: %v", err)
	}

	if unmarshaled.Index != stackable.Index {
		t.Errorf("Index mismatch: got %d, want %d", unmarshaled.Index, stackable.Index)
	}

	if unmarshaled.Count != stackable.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, stackable.Count)
	}

	if unmarshaled.Bind != stackable.Bind {
		t.Errorf("Bind mismatch: got %v, want %v", unmarshaled.Bind, stackable.Bind)
	}

	if unmarshaled.Scount != stackable.Scount {
		t.Errorf("Scount mismatch: got %d, want %d", unmarshaled.Scount, stackable.Scount)
	}

	if unmarshaled.Tcount != stackable.Tcount {
		t.Errorf("Tcount mismatch: got %d, want %d", unmarshaled.Tcount, stackable.Tcount)
	}

	if unmarshaled.Expiretime != stackable.Expiretime {
		t.Errorf("Expiretime mismatch: got %d, want %d", unmarshaled.Expiretime, stackable.Expiretime)
	}

	if unmarshaled.Acquisitiontime != stackable.Acquisitiontime {
		t.Errorf("Acquisitiontime mismatch: got %d, want %d", unmarshaled.Acquisitiontime, stackable.Acquisitiontime)
	}

	if unmarshaled.Sindex != stackable.Sindex {
		t.Errorf("Sindex mismatch: got %d, want %d", unmarshaled.Sindex, stackable.Sindex)
	}

	if unmarshaled.Slotindex != stackable.Slotindex {
		t.Errorf("Slotindex mismatch: got %d, want %d", unmarshaled.Slotindex, stackable.Slotindex)
	}
}

// 测试可重新购买的可堆叠物品信息
func TestBatch30RepurchaseStackable(t *testing.T) {
	stackable := &dnfv1.Stackable{
		Index:  1,
		Count:  10,
		Bind:    false,
		Scount:  0,
		Tcount:  0,
	}

	repurchaseStackable := &dnfv1.RepurchaseStackable{
		Guid: 123456789,
		Item: stackable,
	}

	data, err := proto.Marshal(repurchaseStackable)
	if err != nil {
		t.Fatalf("Failed to marshal RepurchaseStackable: %v", err)
	}

	unmarshaled := &dnfv1.RepurchaseStackable{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal RepurchaseStackable: %v", err)
	}

	if unmarshaled.Guid != repurchaseStackable.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, repurchaseStackable.Guid)
	}

	if unmarshaled.Item.Index != repurchaseStackable.Item.Index {
		t.Errorf("Item.Index mismatch: got %d, want %d", unmarshaled.Item.Index, repurchaseStackable.Item.Index)
	}

	if unmarshaled.Item.Count != repurchaseStackable.Item.Count {
		t.Errorf("Item.Count mismatch: got %d, want %d", unmarshaled.Item.Count, repurchaseStackable.Item.Count)
	}
}
