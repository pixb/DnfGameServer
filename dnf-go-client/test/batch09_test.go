package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestBatch09MailList(t *testing.T) {
	req := &dnfv1.MailListRequest{
		Page: 1,
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailListRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailListRequest: %v", err)
	}

	if unmarshaled.Page != 1 {
		t.Errorf("Expected Page=1, got %d", unmarshaled.Page)
	}
	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
	}

	resp := &dnfv1.MailListResponse{
		Error: 1,
		Count: 2,
		Type:  0,
		Postallist: []*dnfv1.PostAllList{
			{
				Index:      1,
				Count:      1,
				Guid:       1001,
				Title:      "Test Mail 1",
				Msg:        "Test message 1",
				Expiretime: 1234567890,
				Read:       false,
				Receive:    false,
				Importance: 1,
			},
			{
				Index:      2,
				Count:      1,
				Guid:       1002,
				Title:      "Test Mail 2",
				Msg:        "Test message 2",
				Expiretime: 1234567891,
				Read:       true,
				Receive:    true,
				Importance: 2,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailListResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.MailListResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal MailListResponse: %v", err)
	}

	if unmarshaledResp.Error != 1 {
		t.Errorf("Expected Error=1, got %d", unmarshaledResp.Error)
	}
	if unmarshaledResp.Count != 2 {
		t.Errorf("Expected Count=2, got %d", unmarshaledResp.Count)
	}
	if len(unmarshaledResp.Postallist) != 2 {
		t.Errorf("Expected 2 mails, got %d", len(unmarshaledResp.Postallist))
	}
}

func TestBatch09MailGet(t *testing.T) {
	req := &dnfv1.MailGetRequest{
		Type: 0,
		Guid: 1001,
		Selecteditems: []*dnfv1.SelectedItem{
			{
				Index:     1,
				Count:     1,
				Guid:      2001,
				Slotindex: 1,
			},
		},
		Selectedpackages: []*dnfv1.PostPackage{
			{
				Value:     100,
				Index:     1,
				Slotindex: 1,
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailGetRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailGetRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailGetRequest: %v", err)
	}

	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
	}
	if unmarshaled.Guid != 1001 {
		t.Errorf("Expected Guid=1001, got %d", unmarshaled.Guid)
	}
	if len(unmarshaled.Selecteditems) != 1 {
		t.Errorf("Expected 1 selected item, got %d", len(unmarshaled.Selecteditems))
	}

	resp := &dnfv1.MailGetResponse{
		Error: 1,
		Guid:  1001,
		Limit: false,
		Type:  0,
		Remaineditems: []*dnfv1.SelectedItem{
			{
				Index:     2,
				Count:     1,
				Guid:      2002,
				Slotindex: 2,
			},
		},
		Remainedpackages: []*dnfv1.PostPackage{
			{
				Value:     200,
				Index:     2,
				Slotindex: 2,
			},
		},
		Rewards: &dnfv1.ContentsRewardInfo{
			Items: &dnfv1.ItemRewardInfo{
				Inventory: &dnfv1.Items{
					Equipitems: []*dnfv1.Equip{
						{
							EquipId: 3001,
							Guid:    3001,
						},
					},
					Materialitems: []*dnfv1.Stackable{
						{
							Index: 4001,
							Count: 10,
							Bind:  false,
						},
					},
				},
			},
			Currency: &dnfv1.CurrencyRewardInfo{
				Gold:    1000,
				Diamond: 100,
				Cera:    50,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailGetResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.MailGetResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal MailGetResponse: %v", err)
	}

	if unmarshaledResp.Error != 1 {
		t.Errorf("Expected Error=1, got %d", unmarshaledResp.Error)
	}
	if unmarshaledResp.Guid != 1001 {
		t.Errorf("Expected Guid=1001, got %d", unmarshaledResp.Guid)
	}
	if len(unmarshaledResp.Remaineditems) != 1 {
		t.Errorf("Expected 1 remained item, got %d", len(unmarshaledResp.Remaineditems))
	}
}

func TestBatch09MailRead(t *testing.T) {
	req := &dnfv1.MailReadRequest{
		Guid: 1001,
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailReadRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailReadRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailReadRequest: %v", err)
	}

	if unmarshaled.Guid != 1001 {
		t.Errorf("Expected Guid=1001, got %d", unmarshaled.Guid)
	}
	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
	}

	resp := &dnfv1.MailReadResponse{
		Error: 1,
		Guid:  1001,
		Type:  0,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailReadResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.MailReadResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal MailReadResponse: %v", err)
	}

	if unmarshaledResp.Error != 1 {
		t.Errorf("Expected Error=1, got %d", unmarshaledResp.Error)
	}
}

func TestBatch09MailDelete(t *testing.T) {
	req := &dnfv1.MailDeleteRequest{
		Guid: 1001,
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailDeleteRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailDeleteRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailDeleteRequest: %v", err)
	}

	if unmarshaled.Guid != 1001 {
		t.Errorf("Expected Guid=1001, got %d", unmarshaled.Guid)
	}

	resp := &dnfv1.MailDeleteResponse{
		Error: 1,
		Guid:  1001,
		Type:  0,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailDeleteResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.MailDeleteResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal MailDeleteResponse: %v", err)
	}

	if unmarshaledResp.Error != 1 {
		t.Errorf("Expected Error=1, got %d", unmarshaledResp.Error)
	}
}

func TestBatch09MailItemAllGet(t *testing.T) {
	req := &dnfv1.MailItemAllGetRequest{
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailItemAllGetRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailItemAllGetRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailItemAllGetRequest: %v", err)
	}

	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
	}

	resp := &dnfv1.MailItemAllGetResponse{
		Error:   1,
		Page:    1,
		Maxpage: 5,
		Limit:   false,
		Type:    0,
		Bind:    false,
		Rewards: &dnfv1.ContentsRewardInfo{
			Items: &dnfv1.ItemRewardInfo{
				Inventory: &dnfv1.Items{
					Materialitems: []*dnfv1.Stackable{
						{
							Index: 5001,
							Count: 20,
							Bind:  false,
						},
					},
				},
			},
			Currency: &dnfv1.CurrencyRewardInfo{
				Gold:    5000,
				Diamond: 500,
				Cera:    250,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailItemAllGetResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.MailItemAllGetResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal MailItemAllGetResponse: %v", err)
	}

	if unmarshaledResp.Error != 1 {
		t.Errorf("Expected Error=1, got %d", unmarshaledResp.Error)
	}
	if unmarshaledResp.Page != 1 {
		t.Errorf("Expected Page=1, got %d", unmarshaledResp.Page)
	}
}

func TestBatch09MailAllDelete(t *testing.T) {
	req := &dnfv1.MailAllDeleteRequest{
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailAllDeleteRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailAllDeleteRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailAllDeleteRequest: %v", err)
	}

	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
	}

	resp := &dnfv1.MailAllDeleteResponse{
		Error:      1,
		Type:       0,
		Removecount: 10,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailAllDeleteResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.MailAllDeleteResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal MailAllDeleteResponse: %v", err)
	}

	if unmarshaledResp.Error != 1 {
		t.Errorf("Expected Error=1, got %d", unmarshaledResp.Error)
	}
	if unmarshaledResp.Removecount != 10 {
		t.Errorf("Expected Removecount=10, got %d", unmarshaledResp.Removecount)
	}
}

func TestBatch09MailDistributeAccountToCharacter(t *testing.T) {
	req := &dnfv1.MailDistributeAccountToCharacterRequest{
		Charguid: 10001,
		Distributedmails: []*dnfv1.MailDistributionItems{
			{
				Postalguid: 20001,
				Selecteditems: []*dnfv1.SelectedItem{
					{
						Index:     1,
						Count:     1,
						Guid:      30001,
						Slotindex: 1,
					},
				},
				Selectedpackages: []*dnfv1.PostPackage{
					{
						Value:     100,
						Index:     1,
						Slotindex: 1,
					},
				},
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailDistributeAccountToCharacterRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailDistributeAccountToCharacterRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailDistributeAccountToCharacterRequest: %v", err)
	}

	if unmarshaled.Charguid != 10001 {
		t.Errorf("Expected Charguid=10001, got %d", unmarshaled.Charguid)
	}
	if len(unmarshaled.Distributedmails) != 1 {
		t.Errorf("Expected 1 distributed mail, got %d", len(unmarshaled.Distributedmails))
	}

	resp := &dnfv1.MailDistributeAccountToCharacterResponse{
		Error: 1,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailDistributeAccountToCharacterResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.MailDistributeAccountToCharacterResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal MailDistributeAccountToCharacterResponse: %v", err)
	}

	if unmarshaledResp.Error != 1 {
		t.Errorf("Expected Error=1, got %d", unmarshaledResp.Error)
	}
}
