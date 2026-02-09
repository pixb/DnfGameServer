package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestConnectBattleServerRequest_Serialization(t *testing.T) {
	req := &dnfv1.ConnectBattleServerRequest{
		Authkey:     "test_authkey_123",
		Openid:      "test_openid_456",
		World:       1,
		Channel:     1,
		Charguid:    1234567890,
		Type:        1,
		Dungeonguid: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.ConnectBattleServerRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedReq.Authkey, req.Authkey)
	}
	if parsedReq.Openid != req.Openid {
		t.Errorf("Openid mismatch: got %s, want %s", parsedReq.Openid, req.Openid)
	}
	if parsedReq.World != req.World {
		t.Errorf("World mismatch: got %d, want %d", parsedReq.World, req.World)
	}
	if parsedReq.Channel != req.Channel {
		t.Errorf("Channel mismatch: got %d, want %d", parsedReq.Channel, req.Channel)
	}
	if parsedReq.Charguid != req.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsedReq.Charguid, req.Charguid)
	}
	if parsedReq.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsedReq.Type, req.Type)
	}
	if parsedReq.Dungeonguid != req.Dungeonguid {
		t.Errorf("Dungeonguid mismatch: got %d, want %d", parsedReq.Dungeonguid, req.Dungeonguid)
	}
}

func TestConnectBattleServerResponse_Serialization(t *testing.T) {
	resp := &dnfv1.ConnectBattleServerResponse{
		Error:      0,
		Authkey:    "new_authkey_789",
		Bchannel:   1,
		Servertime: 1707433600,
		Encrypt:    true,
		Key:        "test_key_123",
	}

	resp.Seeds = append(resp.Seeds, 100)
	resp.Seeds = append(resp.Seeds, 200)
	resp.Seeds = append(resp.Seeds, 300)

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.ConnectBattleServerResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if parsedResp.Authkey != resp.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedResp.Authkey, resp.Authkey)
	}
	if parsedResp.Bchannel != resp.Bchannel {
		t.Errorf("Bchannel mismatch: got %d, want %d", parsedResp.Bchannel, resp.Bchannel)
	}
	if parsedResp.Servertime != resp.Servertime {
		t.Errorf("Servertime mismatch: got %d, want %d", parsedResp.Servertime, resp.Servertime)
	}
	if parsedResp.Encrypt != resp.Encrypt {
		t.Errorf("Encrypt mismatch: got %v, want %v", parsedResp.Encrypt, resp.Encrypt)
	}
	if parsedResp.Key != resp.Key {
		t.Errorf("Key mismatch: got %s, want %s", parsedResp.Key, resp.Key)
	}
	if len(parsedResp.Seeds) != len(resp.Seeds) {
		t.Errorf("Seeds length mismatch: got %d, want %d", len(parsedResp.Seeds), len(resp.Seeds))
	}
}

func TestIdipProhibitListRequest_Serialization(t *testing.T) {
	req := &dnfv1.IdipProhibitListRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.IdipProhibitListRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}
}

func TestIdipProhibitListResponse_Serialization(t *testing.T) {
	resp := &dnfv1.IdipProhibitListResponse{
		Error: 0,
	}

	resp.Prohibit = append(resp.Prohibit, &dnfv1.Prohibit{
		Target:  1234567890,
		Type:    dnfv1.IdipProhibitType_BAN,
		Endtime: 1707433600,
		Reason:  "Test ban reason",
	})

	resp.Prohibit = append(resp.Prohibit, &dnfv1.Prohibit{
		Target:  9876543210,
		Type:    dnfv1.IdipProhibitType_CHAT,
		Endtime: 1707520000,
		Reason:  "Test chat ban reason",
	})

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.IdipProhibitListResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if len(parsedResp.Prohibit) != len(resp.Prohibit) {
		t.Errorf("Prohibit length mismatch: got %d, want %d", len(parsedResp.Prohibit), len(resp.Prohibit))
	}
}

func TestLoadServerSimpleDataRequest_Serialization(t *testing.T) {
	req := &dnfv1.LoadServerSimpleDataRequest{}

	req.List = append(req.List, &dnfv1.LoadServerSimpleData{
		Type:      1,
		Enumvalue: 100,
	})

	req.List = append(req.List, &dnfv1.LoadServerSimpleData{
		Type:      2,
		Enumvalue: 200,
	})

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.LoadServerSimpleDataRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if len(parsedReq.List) != len(req.List) {
		t.Errorf("List length mismatch: got %d, want %d", len(parsedReq.List), len(req.List))
	}
}

func TestLoadServerSimpleDataResponse_Serialization(t *testing.T) {
	resp := &dnfv1.LoadServerSimpleDataResponse{
		Error:     0,
		Type:      1,
		Enumvalue: 100,
		Value:     "test_value_123",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.LoadServerSimpleDataResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if parsedResp.Type != resp.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsedResp.Type, resp.Type)
	}
	if parsedResp.Enumvalue != resp.Enumvalue {
		t.Errorf("Enumvalue mismatch: got %d, want %d", parsedResp.Enumvalue, resp.Enumvalue)
	}
	if parsedResp.Value != resp.Value {
		t.Errorf("Value mismatch: got %s, want %s", parsedResp.Value, resp.Value)
	}
}

func TestSaveServerSimpleDataRequest_Serialization(t *testing.T) {
	req := &dnfv1.SaveServerSimpleDataRequest{
		Type:      1,
		Enumvalue: 100,
		Value:     "test_value_123",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.SaveServerSimpleDataRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsedReq.Type, req.Type)
	}
	if parsedReq.Enumvalue != req.Enumvalue {
		t.Errorf("Enumvalue mismatch: got %d, want %d", parsedReq.Enumvalue, req.Enumvalue)
	}
	if parsedReq.Value != req.Value {
		t.Errorf("Value mismatch: got %s, want %s", parsedReq.Value, req.Value)
	}
}

func TestSaveServerSimpleDataResponse_Serialization(t *testing.T) {
	resp := &dnfv1.SaveServerSimpleDataResponse{
		Error: 0,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.SaveServerSimpleDataResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
}

func TestProhibit_Serialization(t *testing.T) {
	prohibit := &dnfv1.Prohibit{
		Target:   1234567890,
		Type:     dnfv1.IdipProhibitType_BAN,
		Endtime:  1707433600,
		Reason:   "Test ban reason",
	}

	prohibit.Subtype = append(prohibit.Subtype, 1)
	prohibit.Subtype = append(prohibit.Subtype, 2)
	prohibit.Subtype = append(prohibit.Subtype, 3)

	prohibit.Times = append(prohibit.Times, 1707433600)
	prohibit.Times = append(prohibit.Times, 1707520000)

	data, err := proto.Marshal(prohibit)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedProhibit dnfv1.Prohibit
	if err := proto.Unmarshal(data, &parsedProhibit); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedProhibit.Target != prohibit.Target {
		t.Errorf("Target mismatch: got %d, want %d", parsedProhibit.Target, prohibit.Target)
	}
	if parsedProhibit.Type != prohibit.Type {
		t.Errorf("Type mismatch: got %v, want %v", parsedProhibit.Type, prohibit.Type)
	}
	if parsedProhibit.Endtime != prohibit.Endtime {
		t.Errorf("Endtime mismatch: got %d, want %d", parsedProhibit.Endtime, prohibit.Endtime)
	}
	if parsedProhibit.Reason != prohibit.Reason {
		t.Errorf("Reason mismatch: got %s, want %s", parsedProhibit.Reason, prohibit.Reason)
	}
	if len(parsedProhibit.Subtype) != len(prohibit.Subtype) {
		t.Errorf("Subtype length mismatch: got %d, want %d", len(parsedProhibit.Subtype), len(prohibit.Subtype))
	}
	if len(parsedProhibit.Times) != len(prohibit.Times) {
		t.Errorf("Times length mismatch: got %d, want %d", len(parsedProhibit.Times), len(prohibit.Times))
	}
}

func TestConnectBattleServerRequest_ZeroValues(t *testing.T) {
	req := &dnfv1.ConnectBattleServerRequest{
		Authkey:     "",
		Openid:      "",
		World:       0,
		Channel:     0,
		Charguid:    0,
		Type:        0,
		Dungeonguid: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.ConnectBattleServerRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedReq.Authkey, req.Authkey)
	}
	if parsedReq.Openid != req.Openid {
		t.Errorf("Openid mismatch: got %s, want %s", parsedReq.Openid, req.Openid)
	}
}

func TestIdipProhibitListResponse_MultipleProhibits(t *testing.T) {
	resp := &dnfv1.IdipProhibitListResponse{
		Error: 0,
	}

	for i := 0; i < 5; i++ {
		var prohibitType dnfv1.IdipProhibitType
		switch i {
		case 0:
			prohibitType = dnfv1.IdipProhibitType_IDIP_NONE
		case 1:
			prohibitType = dnfv1.IdipProhibitType_PATTERN
		case 2:
			prohibitType = dnfv1.IdipProhibitType_INCOME
		case 3:
			prohibitType = dnfv1.IdipProhibitType_RANKING
		case 4:
			prohibitType = dnfv1.IdipProhibitType_CHAT
		}
		resp.Prohibit = append(resp.Prohibit, &dnfv1.Prohibit{
			Target:  uint64(i + 1),
			Type:    prohibitType,
			Endtime: 1707433600 + int64(i*86400),
			Reason:  "Test reason",
		})
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.IdipProhibitListResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if len(parsedResp.Prohibit) != 5 {
		t.Errorf("Prohibit length mismatch: got %d, want %d", len(parsedResp.Prohibit), 5)
	}
}
