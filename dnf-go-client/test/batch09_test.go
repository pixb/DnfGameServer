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
		Error:      1,
		TotalCount: 2,
		Page:       1,
		Mails: []*dnfv1.MailInfo{
			{
				Guid:          1001,
				Title:          "Test Mail 1",
				Content:        "Test message 1",
				Type:           0,
				Status:         1,
				SendTime:       1234567890,
				ExpireTime:    12345678900,
				HasAttachment: false,
				IsRead:         false,
			},
			{
				Guid:          1002,
				Title:          "Test Mail 2",
				Content:        "Test message 2",
				Type:           0,
				Status:         1,
				SendTime:       1234567891,
				ExpireTime:    12345678901,
				HasAttachment: true,
				IsRead:         true,
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
	if unmarshaledResp.TotalCount != 2 {
		t.Errorf("Expected TotalCount=2, got %d", unmarshaledResp.TotalCount)
	}
	if len(unmarshaledResp.Mails) != 2 {
		t.Errorf("Expected 2 mails, got %d", len(unmarshaledResp.Mails))
	}
}

func TestBatch09MailGet(t *testing.T) {
	req := &dnfv1.MailGetRequest{
		Type: 0,
		Guid: 1001,
		SelectedItems: []*dnfv1.SelectedItem{
			{
				Guid:  2001,
				Count: 1,
			},
		},
		SelectedPackages: []*dnfv1.PostPackage{
			{
				Guid:  3001,
				Count: 100,
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
	if len(unmarshaled.SelectedItems) != 1 {
		t.Errorf("Expected 1 selected item, got %d", len(unmarshaled.SelectedItems))
	}
	if len(unmarshaled.SelectedPackages) != 1 {
		t.Errorf("Expected 1 selected package, got %d", len(unmarshaled.SelectedPackages))
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
	if unmarshaled.Type != 0 {
		t.Errorf("Expected Type=0, got %d", unmarshaled.Type)
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
}
