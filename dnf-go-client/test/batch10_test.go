package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// TestBatch10ItemUse 测试物品使用消息
func TestBatch10ItemUse(t *testing.T) {
	req := &dnfv1.ItemUseRequest{
		Index:           1,
		Count:           1,
		Bind:            true,
		Guid:            123456789,
		Type:            0,
		Input:           "test",
		Selectitemindex: 0,
		Quest: []*dnfv1.PT_QUEST{
			{
				Id:       1,
				Progress: 50,
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ItemUseRequest: %v", err)
	}

	unmarshaled := &dnfv1.ItemUseRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ItemUseRequest: %v", err)
	}

	if unmarshaled.Index != 1 {
		t.Errorf("Expected Index=1, got %d", unmarshaled.Index)
	}
	if unmarshaled.Count != 1 {
		t.Errorf("Expected Count=1, got %d", unmarshaled.Count)
	}
	if !unmarshaled.Bind {
		t.Errorf("Expected Bind=true, got %v", unmarshaled.Bind)
	}
	if unmarshaled.Guid != 123456789 {
		t.Errorf("Expected Guid=123456789, got %d", unmarshaled.Guid)
	}
	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
	}
	if unmarshaled.Input != "test" {
		t.Errorf("Expected Input=\"test\", got %q", unmarshaled.Input)
	}
	if unmarshaled.Selectitemindex != 0 {
		t.Errorf("Expected Selectitemindex=0, got %d", unmarshaled.Selectitemindex)
	}
	if len(unmarshaled.Quest) != 1 {
		t.Errorf("Expected 1 quest, got %d", len(unmarshaled.Quest))
	} else if unmarshaled.Quest[0].Id != 1 {
		t.Errorf("Expected Quest[0].Id=1, got %d", unmarshaled.Quest[0].Id)
	} else if unmarshaled.Quest[0].Progress != 50 {
		t.Errorf("Expected Quest[0].Progress=50, got %d", unmarshaled.Quest[0].Progress)
	}

	// 测试响应
	resp := &dnfv1.ItemUseResponse{
		Error:  0,
		Result: 1,
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ItemUseResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.ItemUseResponse{}
	if err := proto.Unmarshal(respData, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal ItemUseResponse: %v", err)
	}

	if unmarshaledResp.Error != 0 {
		t.Errorf("Expected Error=0, got %d", unmarshaledResp.Error)
	}
	if unmarshaledResp.Result != 1 {
		t.Errorf("Expected Result=1, got %d", unmarshaledResp.Result)
	}
}

// TestBatch10ItemReinforce 测试物品强化消息
func TestBatch10ItemReinforce(t *testing.T) {
	req := &dnfv1.ItemReinforceRequest{
		Type:        1,
		Guid:        123456789,
		Talisman:    0,
		Customtype:  0,
		Noticharlist: []uint64{1, 2, 3},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ItemReinforceRequest: %v", err)
	}

	unmarshaled := &dnfv1.ItemReinforceRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ItemReinforceRequest: %v", err)
	}

	if unmarshaled.Type != 1 {
		t.Errorf("Expected Type=1, got %d", unmarshaled.Type)
	}
	if unmarshaled.Guid != 123456789 {
		t.Errorf("Expected Guid=123456789, got %d", unmarshaled.Guid)
	}
	if unmarshaled.Talisman != 0 {
		t.Errorf("Expected Talisman=0, got %d", unmarshaled.Talisman)
	}
	if unmarshaled.Customtype != 0 {
		t.Errorf("Expected Customtype=0, got %d", unmarshaled.Customtype)
	}
	if len(unmarshaled.Noticharlist) != 3 {
		t.Errorf("Expected 3 noticharlist items, got %d", len(unmarshaled.Noticharlist))
	}

	// 测试响应
	resp := &dnfv1.ItemReinforceResponse{
		Error:  0,
		Result: 1,
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ItemReinforceResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.ItemReinforceResponse{}
	if err := proto.Unmarshal(respData, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal ItemReinforceResponse: %v", err)
	}

	if unmarshaledResp.Error != 0 {
		t.Errorf("Expected Error=0, got %d", unmarshaledResp.Error)
	}
	if unmarshaledResp.Result != 1 {
		t.Errorf("Expected Result=1, got %d", unmarshaledResp.Result)
	}
}

// TestBatch10ItemList 测试物品列表消息
func TestBatch10ItemList(t *testing.T) {
	req := &dnfv1.ItemListRequest{
		Type: 0,
		Page: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ItemListRequest: %v", err)
	}

	unmarshaled := &dnfv1.ItemListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ItemListRequest: %v", err)
	}

	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
	}
	if unmarshaled.Page != 1 {
		t.Errorf("Expected Page=1, got %d", unmarshaled.Page)
	}

	// 测试响应
	resp := &dnfv1.ItemListResponse{
		Error: 0,
		Items: []*dnfv1.ItemInfo{
			{
				Index:          1,
				Guid:           123456789,
				Id:             1001,
				Count:          1,
				Bind:           true,
				ReinforceLevel: 10,
			},
		},
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ItemListResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.ItemListResponse{}
	if err := proto.Unmarshal(respData, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal ItemListResponse: %v", err)
	}

	if unmarshaledResp.Error != 0 {
		t.Errorf("Expected Error=0, got %d", unmarshaledResp.Error)
	}
	if len(unmarshaledResp.Items) != 1 {
		t.Errorf("Expected 1 item, got %d", len(unmarshaledResp.Items))
	}
}

// TestBatch10ItemMove 测试物品移动消息
func TestBatch10ItemMove(t *testing.T) {
	req := &dnfv1.ItemMoveRequest{
		FromIndex: 1,
		ToIndex:   2,
		Count:     1,
		Guid:      123456789,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ItemMoveRequest: %v", err)
	}

	unmarshaled := &dnfv1.ItemMoveRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ItemMoveRequest: %v", err)
	}

	if unmarshaled.FromIndex != 1 {
		t.Errorf("Expected FromIndex=1, got %d", unmarshaled.FromIndex)
	}
	if unmarshaled.ToIndex != 2 {
		t.Errorf("Expected ToIndex=2, got %d", unmarshaled.ToIndex)
	}
	if unmarshaled.Count != 1 {
		t.Errorf("Expected Count=1, got %d", unmarshaled.Count)
	}
	if unmarshaled.Guid != 123456789 {
		t.Errorf("Expected Guid=123456789, got %d", unmarshaled.Guid)
	}

	// 测试响应
	resp := &dnfv1.ItemMoveResponse{
		Error:   0,
		Success: true,
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ItemMoveResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.ItemMoveResponse{}
	if err := proto.Unmarshal(respData, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal ItemMoveResponse: %v", err)
	}

	if unmarshaledResp.Error != 0 {
		t.Errorf("Expected Error=0, got %d", unmarshaledResp.Error)
	}
	if !unmarshaledResp.Success {
		t.Errorf("Expected Success=true, got %v", unmarshaledResp.Success)
	}
}

// TestBatch10ItemDrop 测试物品丢弃消息
func TestBatch10ItemDrop(t *testing.T) {
	req := &dnfv1.ItemDropRequest{
		Index: 1,
		Count: 1,
		Guid:  123456789,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ItemDropRequest: %v", err)
	}

	unmarshaled := &dnfv1.ItemDropRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ItemDropRequest: %v", err)
	}

	if unmarshaled.Index != 1 {
		t.Errorf("Expected Index=1, got %d", unmarshaled.Index)
	}
	if unmarshaled.Count != 1 {
		t.Errorf("Expected Count=1, got %d", unmarshaled.Count)
	}
	if unmarshaled.Guid != 123456789 {
		t.Errorf("Expected Guid=123456789, got %d", unmarshaled.Guid)
	}

	// 测试响应
	resp := &dnfv1.ItemDropResponse{
		Error:   0,
		Success: true,
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ItemDropResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.ItemDropResponse{}
	if err := proto.Unmarshal(respData, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal ItemDropResponse: %v", err)
	}

	if unmarshaledResp.Error != 0 {
		t.Errorf("Expected Error=0, got %d", unmarshaledResp.Error)
	}
	if !unmarshaledResp.Success {
		t.Errorf("Expected Success=true, got %v", unmarshaledResp.Success)
	}
}

// TestBatch10ItemSplit 测试物品拆分消息
func TestBatch10ItemSplit(t *testing.T) {
	req := &dnfv1.ItemSplitRequest{
		Index: 1,
		Count: 5,
		Guid:  123456789,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ItemSplitRequest: %v", err)
	}

	unmarshaled := &dnfv1.ItemSplitRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ItemSplitRequest: %v", err)
	}

	if unmarshaled.Index != 1 {
		t.Errorf("Expected Index=1, got %d", unmarshaled.Index)
	}
	if unmarshaled.Count != 5 {
		t.Errorf("Expected Count=5, got %d", unmarshaled.Count)
	}
	if unmarshaled.Guid != 123456789 {
		t.Errorf("Expected Guid=123456789, got %d", unmarshaled.Guid)
	}

	// 测试响应
	resp := &dnfv1.ItemSplitResponse{
		Error:   0,
		Success: true,
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ItemSplitResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.ItemSplitResponse{}
	if err := proto.Unmarshal(respData, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal ItemSplitResponse: %v", err)
	}

	if unmarshaledResp.Error != 0 {
		t.Errorf("Expected Error=0, got %d", unmarshaledResp.Error)
	}
	if !unmarshaledResp.Success {
		t.Errorf("Expected Success=true, got %v", unmarshaledResp.Success)
	}
}
