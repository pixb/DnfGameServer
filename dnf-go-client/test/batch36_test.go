package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestBatch36TowerInfo(t *testing.T) {
	req := &dnfv1.TowerInfoRequest{
		ModuleId: 11080,
		Cmd:      0,
		Type:     1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.TowerInfoRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	if parsedReq.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsedReq.Type, req.Type)
	}

	resp := &dnfv1.TowerInfoResponse{
		Error:        0,
		ClearFloor:   50,
		Type:         1,
		SweepCount:   10,
		Agp:          100,
		Point:        500,
		List: []*dnfv1.FloorInfo{
			{
				Index:   1,
				Count:   5,
				Floor:   1,
				BestClearTime: 1000,
				ClearTime:      2000,
			},
		},
		ClearRate: []*dnfv1.TowerOfIllusionClearRate{
			{
				Floor:   1,
				Percent: 100.0,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.TowerInfoResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if parsedResp.ClearFloor != resp.ClearFloor {
		t.Errorf("ClearFloor mismatch: got %d, want %d", parsedResp.ClearFloor, resp.ClearFloor)
	}

	if len(parsedResp.List) != len(resp.List) {
		t.Errorf("List length mismatch: got %d, want %d", len(parsedResp.List), len(resp.List))
	}
}

func TestBatch36TonicInfo(t *testing.T) {
	req := &dnfv1.TonicInfoRequest{
		ModuleId: 11039,
		Cmd:      0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.TonicInfoRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	resp := &dnfv1.TonicInfoResponse{
		Error: 0,
		CrystalTonic: []*dnfv1.TonicInfo{
			{
				Index: 1,
				Level: 10,
			},
			{
				Index: 2,
				Level: 20,
			},
		},
		CrystalTonicPoint: []*dnfv1.TonicMaterialUsage{
			{
				Index: 1,
				Total: 100,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.TonicInfoResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if len(parsedResp.CrystalTonic) != len(resp.CrystalTonic) {
		t.Errorf("CrystalTonic length mismatch: got %d, want %d", len(parsedResp.CrystalTonic), len(resp.CrystalTonic))
	}
}

func TestBatch36TonicUpgrade(t *testing.T) {
	req := &dnfv1.TonicUpgradeRequest{
		ModuleId: 11041,
		Cmd:      0,
		Index:    1,
		AutoMode: true,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.TonicUpgradeRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	if parsedReq.AutoMode != req.AutoMode {
		t.Errorf("AutoMode mismatch: got %v, want %v", parsedReq.AutoMode, req.AutoMode)
	}

	resp := &dnfv1.TonicUpgradeResponse{
		Error: 0,
		Index: 1,
		CrystalTonic: []*dnfv1.TonicInfo{
			{
				Index: 1,
				Level: 11,
			},
		},
		CrystalTonicPoint: []*dnfv1.TonicMaterialUsage{
			{
				Index: 1,
				Total: 90,
			},
		},
		Currency: []*dnfv1.MoneyItem{
			{
				Index:  1,
				Count:  100,
				Season: 1,
			},
		},
		RemoveItems: &dnfv1.RemoveItems{
			MaterialItems: []*dnfv1.Stackable{
				{
					Index: 2001,
					Count: 10,
				},
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.TonicUpgradeResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if len(parsedResp.Currency) != len(resp.Currency) {
		t.Errorf("Currency length mismatch: got %d, want %d", len(parsedResp.Currency), len(resp.Currency))
	}
}

func TestBatch36WardrobeAndTitle(t *testing.T) {
	wardrobeReq := &dnfv1.WardrobeInfoRequest{
		ModuleId: 14081,
		Cmd:      0,
	}

	data, err := proto.Marshal(wardrobeReq)
	if err != nil {
		t.Fatalf("Failed to marshal wardrobe request: %v", err)
	}

	parsedWardrobeReq := &dnfv1.WardrobeInfoRequest{}
	if err := proto.Unmarshal(data, parsedWardrobeReq); err != nil {
		t.Fatalf("Failed to unmarshal wardrobe request: %v", err)
	}

	if parsedWardrobeReq.ModuleId != wardrobeReq.ModuleId {
		t.Errorf("Wardrobe ModuleId mismatch: got %d, want %d", parsedWardrobeReq.ModuleId, wardrobeReq.ModuleId)
	}

	wardrobeResp := &dnfv1.WardrobeInfoResponse{
		Error: 0,
		Wardrobe: &dnfv1.WardrobeInfo{
			ExtensionSlot: 10,
			Slot:          20,
			List: []*dnfv1.AvatarMannequinInfo{
				{
					Slot: 1,
					Name: "Mannequin1",
					List: []*dnfv1.AvatarMannequinPartInfo{
						{
							Guid:  1234567890123456789,
							Index: 1,
						},
					},
				},
			},
		},
	}

	data, err = proto.Marshal(wardrobeResp)
	if err != nil {
		t.Fatalf("Failed to marshal wardrobe response: %v", err)
	}

	parsedWardrobeResp := &dnfv1.WardrobeInfoResponse{}
	if err := proto.Unmarshal(data, parsedWardrobeResp); err != nil {
		t.Fatalf("Failed to unmarshal wardrobe response: %v", err)
	}

	if parsedWardrobeResp.Error != wardrobeResp.Error {
		t.Errorf("Wardrobe Error mismatch: got %d, want %d", parsedWardrobeResp.Error, wardrobeResp.Error)
	}

	titleReq := &dnfv1.TitleListRequest{
		ModuleId: 14049,
		Cmd:      0,
	}

	data, err = proto.Marshal(titleReq)
	if err != nil {
		t.Fatalf("Failed to marshal title request: %v", err)
	}

	parsedTitleReq := &dnfv1.TitleListRequest{}
	if err := proto.Unmarshal(data, parsedTitleReq); err != nil {
		t.Fatalf("Failed to unmarshal title request: %v", err)
	}

	if parsedTitleReq.ModuleId != titleReq.ModuleId {
		t.Errorf("Title ModuleId mismatch: got %d, want %d", parsedTitleReq.ModuleId, titleReq.ModuleId)
	}

	titleResp := &dnfv1.TitleListResponse{
		Error:     0,
		Page:      1,
		MaxCount:  100,
		Title: []*dnfv1.Equip{
			{
				EquipId: 1001,
				Level:   1,
			},
		},
	}

	data, err = proto.Marshal(titleResp)
	if err != nil {
		t.Fatalf("Failed to marshal title response: %v", err)
	}

	parsedTitleResp := &dnfv1.TitleListResponse{}
	if err := proto.Unmarshal(data, parsedTitleResp); err != nil {
		t.Fatalf("Failed to unmarshal title response: %v", err)
	}

	if parsedTitleResp.Error != titleResp.Error {
		t.Errorf("Title Error mismatch: got %d, want %d", parsedTitleResp.Error, titleResp.Error)
	}

	if len(parsedTitleResp.Title) != len(titleResp.Title) {
		t.Errorf("Title length mismatch: got %d, want %d", len(parsedTitleResp.Title), len(titleResp.Title))
	}
}
