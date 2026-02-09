package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestBatch34WelfareRewardGet(t *testing.T) {
	req := &dnfv1.WelfareRewardGetRequest{
		ModuleId: -1,
		Cmd:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.WelfareRewardGetRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	resp := &dnfv1.WelfareRewardGetResponse{
		Error:               0,
		Level:               100,
		AdventureUnionLevel: 50,
		AdventureUnionExp:   1000000,
		ClearExp: &dnfv1.ExpDetailInfo{
			BaseExp:    1000,
			RankExp:    500,
			PremiumExp: 300,
			AppendageExp: []*dnfv1.AppendageExp{
				{Index: 1, Exp: 100},
				{Index: 2, Exp: 200},
			},
		},
		Ticket: &dnfv1.Ticket{
			DungeonType:        "dungeon_001",
			DailyTicket:        5,
			WeeklyTicket:       20,
			PremiumTicket:      10,
			SweepTicket:        3,
			DailyRewardTicket:  1,
			WeeklyRewardTicket: 5,
			BuyCount:           2,
			ChargeTime:         1234567890,
			FreeTicket:         1,
		},
		Rewards: &dnfv1.ContentsRewardInfo{
			Items: &dnfv1.ItemRewardInfo{
				Inventory: &dnfv1.Items{
					Index: 1,
					Count: 10,
				},
			},
			Currency: &dnfv1.CurrencyRewardInfo{
				Currency: []*dnfv1.MoneyItem{
					{Index: 1, Count: 100, Season: 1},
					{Index: 2, Count: 200, Season: 1},
				},
			},
			Ticket: &dnfv1.TicketRewardInfo{
				Character: []*dnfv1.Ticket{
					{DungeonType: "dungeon_001", DailyTicket: 5},
				},
				Account: []*dnfv1.Ticket{
					{DungeonType: "dungeon_002", WeeklyTicket: 10},
				},
			},
			Postal: &dnfv1.PostalRewardInfo{
				PostalIds: []int32{1001, 1002, 1003},
			},
			AccountPostal: &dnfv1.PostalRewardInfo{
				PostalIds: []int32{2001, 2002},
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.WelfareRewardGetResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if parsedResp.Level != resp.Level {
		t.Errorf("Level mismatch: got %d, want %d", parsedResp.Level, resp.Level)
	}

	if len(parsedResp.ClearExp.AppendageExp) != len(resp.ClearExp.AppendageExp) {
		t.Errorf("AppendageExp length mismatch: got %d, want %d", len(parsedResp.ClearExp.AppendageExp), len(resp.ClearExp.AppendageExp))
	}
}

func TestBatch34WelfareRewardInfo(t *testing.T) {
	req := &dnfv1.WelfareRewardInfoRequest{
		ModuleId: -1,
		Cmd:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.WelfareRewardInfoRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	resp := &dnfv1.WelfareRewardInfoResponse{
		Error: 0,
		DungeonInfos: []*dnfv1.WelfareRewardInfo{
			{
				CurrentState: 1,
				DungeonType:  100,
				OverdueState: 0,
			},
			{
				CurrentState: 2,
				DungeonType:  101,
				OverdueState: 1,
			},
			{
				CurrentState: 3,
				DungeonType:  102,
				OverdueState: 0,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.WelfareRewardInfoResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if len(parsedResp.DungeonInfos) != len(resp.DungeonInfos) {
		t.Errorf("DungeonInfos length mismatch: got %d, want %d", len(parsedResp.DungeonInfos), len(resp.DungeonInfos))
	}

	for i, info := range resp.DungeonInfos {
		if parsedResp.DungeonInfos[i].CurrentState != info.CurrentState {
			t.Errorf("DungeonInfo[%d] CurrentState mismatch: got %d, want %d", i, parsedResp.DungeonInfos[i].CurrentState, info.CurrentState)
		}
	}
}

func TestBatch34WelfareComplexData(t *testing.T) {
	items := &dnfv1.Items{
		EquipItems: []*dnfv1.Equip{
			{EquipId: 1001, Level: 50, Quality: 5, Guid: 1234567890},
			{EquipId: 1002, Level: 60, Quality: 6, Guid: 1234567891},
		},
		MaterialItems: []*dnfv1.Stackable{
			{Index: 2001, Count: 100},
			{Index: 2002, Count: 50},
		},
		AvatarItems: []*dnfv1.SdAvatarItem{
			{Index: 3001, Count: 1, Guid: 1234567892, Option: 3},
		},
		Index: 1,
		Count: 3,
	}

	data, err := proto.Marshal(items)
	if err != nil {
		t.Fatalf("Failed to marshal items: %v", err)
	}

	parsedItems := &dnfv1.Items{}
	if err := proto.Unmarshal(data, parsedItems); err != nil {
		t.Fatalf("Failed to unmarshal items: %v", err)
	}

	if len(parsedItems.EquipItems) != len(items.EquipItems) {
		t.Errorf("EquipItems length mismatch: got %d, want %d", len(parsedItems.EquipItems), len(items.EquipItems))
	}

	if len(parsedItems.MaterialItems) != len(items.MaterialItems) {
		t.Errorf("MaterialItems length mismatch: got %d, want %d", len(parsedItems.MaterialItems), len(items.MaterialItems))
	}

	if parsedItems.Index != items.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsedItems.Index, items.Index)
	}
}
