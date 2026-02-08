package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func TestStandbyRequest_Serialization(t *testing.T) {
	req := &dnfv1.StandbyRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.StandbyRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}
}

func TestStandbyResponse_Serialization(t *testing.T) {
	resp := &dnfv1.StandbyResponse{
		Error:     0,
		Standby:   1,
		Vip:       1,
		Reconnect: 0,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.StandbyResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if parsedResp.Standby != resp.Standby {
		t.Errorf("Standby mismatch: got %d, want %d", parsedResp.Standby, resp.Standby)
	}
	if parsedResp.Vip != resp.Vip {
		t.Errorf("Vip mismatch: got %d, want %d", parsedResp.Vip, resp.Vip)
	}
	if parsedResp.Reconnect != resp.Reconnect {
		t.Errorf("Reconnect mismatch: got %d, want %d", parsedResp.Reconnect, resp.Reconnect)
	}
}

func TestDeleteCharacterRequest_Serialization(t *testing.T) {
	req := &dnfv1.DeleteCharacterRequest{
		CharGuid: 1234567890,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.DeleteCharacterRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.CharGuid != req.CharGuid {
		t.Errorf("CharGuid mismatch: got %d, want %d", parsedReq.CharGuid, req.CharGuid)
	}
}

func TestDeleteCharacterResponse_Serialization(t *testing.T) {
	resp := &dnfv1.DeleteCharacterResponse{
		Error:       0,
		Charguid:    1234567890,
		Pendingtime: 1707433600,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.DeleteCharacterResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if parsedResp.Charguid != resp.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsedResp.Charguid, resp.Charguid)
	}
	if parsedResp.Pendingtime != resp.Pendingtime {
		t.Errorf("Pendingtime mismatch: got %d, want %d", parsedResp.Pendingtime, resp.Pendingtime)
	}
}

func TestProtocolTransaction_Serialization(t *testing.T) {
	transaction := &dnfv1.ProtocolTransaction{
		Protocol:      10000,
		Transactionid: 1,
	}

	data, err := proto.Marshal(transaction)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedTransaction dnfv1.ProtocolTransaction
	if err := proto.Unmarshal(data, &parsedTransaction); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedTransaction.Protocol != transaction.Protocol {
		t.Errorf("Protocol mismatch: got %d, want %d", parsedTransaction.Protocol, transaction.Protocol)
	}
	if parsedTransaction.Transactionid != transaction.Transactionid {
		t.Errorf("Transactionid mismatch: got %d, want %d", parsedTransaction.Transactionid, transaction.Transactionid)
	}
}

func TestStartGameRequest_Serialization(t *testing.T) {
	req := &dnfv1.StartGameRequest{
		Charguid:    1234567890,
		Dungeonguid: 0,
		Authkey:      "test_authkey_123",
		Accesstoken: "test_token_123",
		Paytoken:     "pay_token_123",
		Town:         1,
		Area:         1,
		Posx:         100,
		Posy:         200,
		Partyguid:    0,
	}

	req.Request = append(req.Request, &dnfv1.ProtocolTransaction{
		Protocol:      10000,
		Transactionid: 1,
	})

	req.Request = append(req.Request, &dnfv1.ProtocolTransaction{
		Protocol:      10002,
		Transactionid: 2,
	})

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.StartGameRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Charguid != req.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsedReq.Charguid, req.Charguid)
	}
	if parsedReq.Dungeonguid != req.Dungeonguid {
		t.Errorf("Dungeonguid mismatch: got %d, want %d", parsedReq.Dungeonguid, req.Dungeonguid)
	}
	if parsedReq.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedReq.Authkey, req.Authkey)
	}
	if parsedReq.Accesstoken != req.Accesstoken {
		t.Errorf("Accesstoken mismatch: got %s, want %s", parsedReq.Accesstoken, req.Accesstoken)
	}
	if parsedReq.Paytoken != req.Paytoken {
		t.Errorf("Paytoken mismatch: got %s, want %s", parsedReq.Paytoken, req.Paytoken)
	}
	if parsedReq.Town != req.Town {
		t.Errorf("Town mismatch: got %d, want %d", parsedReq.Town, req.Town)
	}
	if parsedReq.Area != req.Area {
		t.Errorf("Area mismatch: got %d, want %d", parsedReq.Area, req.Area)
	}
	if parsedReq.Posx != req.Posx {
		t.Errorf("Posx mismatch: got %d, want %d", parsedReq.Posx, req.Posx)
	}
	if parsedReq.Posy != req.Posy {
		t.Errorf("Posy mismatch: got %d, want %d", parsedReq.Posy, req.Posy)
	}
	if len(parsedReq.Request) != len(req.Request) {
		t.Errorf("Request length mismatch: got %d, want %d", len(parsedReq.Request), len(req.Request))
	}
	if parsedReq.Partyguid != req.Partyguid {
		t.Errorf("Partyguid mismatch: got %d, want %d", parsedReq.Partyguid, req.Partyguid)
	}
}

func TestExitCharacterRequest_Serialization(t *testing.T) {
	req := &dnfv1.ExitCharacterRequest{
		World:            1,
		Channel:          1,
		Reservationtype:  0,
		Exittype:         0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.ExitCharacterRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.World != req.World {
		t.Errorf("World mismatch: got %d, want %d", parsedReq.World, req.World)
	}
	if parsedReq.Channel != req.Channel {
		t.Errorf("Channel mismatch: got %d, want %d", parsedReq.Channel, req.Channel)
	}
	if parsedReq.Reservationtype != req.Reservationtype {
		t.Errorf("Reservationtype mismatch: got %d, want %d", parsedReq.Reservationtype, req.Reservationtype)
	}
	if parsedReq.Exittype != req.Exittype {
		t.Errorf("Exittype mismatch: got %d, want %d", parsedReq.Exittype, req.Exittype)
	}
}

func TestStandbyResponse_BoundaryValues(t *testing.T) {
	tests := []struct {
		name string
		resp *dnfv1.StandbyResponse
	}{
		{
			name: "zero_values",
			resp: &dnfv1.StandbyResponse{
				Error:     0,
				Standby:   0,
				Vip:       0,
				Reconnect: 0,
			},
		},
		{
			name: "max_values",
			resp: &dnfv1.StandbyResponse{
				Error:     2147483647,
				Standby:   2147483647,
				Vip:       2147483647,
				Reconnect: 2147483647,
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			data, err := proto.Marshal(tt.resp)
			if err != nil {
				t.Fatalf("Marshal failed: %v", err)
			}

			var parsedResp dnfv1.StandbyResponse
			if err := proto.Unmarshal(data, &parsedResp); err != nil {
				t.Fatalf("Unmarshal failed: %v", err)
			}

			if parsedResp.Error != tt.resp.Error {
				t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, tt.resp.Error)
			}
			if parsedResp.Standby != tt.resp.Standby {
				t.Errorf("Standby mismatch: got %d, want %d", parsedResp.Standby, tt.resp.Standby)
			}
			if parsedResp.Vip != tt.resp.Vip {
				t.Errorf("Vip mismatch: got %d, want %d", parsedResp.Vip, tt.resp.Vip)
			}
			if parsedResp.Reconnect != tt.resp.Reconnect {
				t.Errorf("Reconnect mismatch: got %d, want %d", parsedResp.Reconnect, tt.resp.Reconnect)
			}
		})
	}
}

func TestStartGameRequest_ComplexStructure(t *testing.T) {
	req := &dnfv1.StartGameRequest{
		Charguid:    1234567890,
		Dungeonguid: 0,
		Authkey:      "test_authkey_123",
		Accesstoken: "test_token_123",
		Paytoken:     "pay_token_123",
		Town:         1,
		Area:         1,
		Posx:         100,
		Posy:         200,
		Partyguid:    0,
	}

	for i := 0; i < 5; i++ {
		req.Request = append(req.Request, &dnfv1.ProtocolTransaction{
			Protocol:      int32(10000 + i),
			Transactionid: int32(i + 1),
		})
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.StartGameRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if len(parsedReq.Request) != 5 {
		t.Errorf("Request length mismatch: got %d, want %d", len(parsedReq.Request), 5)
	}

	for i, transaction := range parsedReq.Request {
		expectedProtocol := int32(10000 + i)
		expectedTransactionid := int32(i + 1)
		if transaction.Protocol != expectedProtocol {
			t.Errorf("Transaction[%d] Protocol mismatch: got %d, want %d", i, transaction.Protocol, expectedProtocol)
		}
		if transaction.Transactionid != expectedTransactionid {
			t.Errorf("Transaction[%d] Transactionid mismatch: got %d, want %d", i, transaction.Transactionid, expectedTransactionid)
		}
	}
}
