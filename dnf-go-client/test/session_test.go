package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func TestPingRequest_Serialization(t *testing.T) {
	req := &dnfv1.PingRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.PingRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}
}

func TestPingResponse_Serialization(t *testing.T) {
	res := &dnfv1.PingResponse{
		Error:        0,
		Responsetime: 100,
	}

	data, err := proto.Marshal(res)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedRes dnfv1.PingResponse
	if err := proto.Unmarshal(data, &parsedRes); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedRes.Error != res.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, res.Error)
	}
	if parsedRes.Responsetime != res.Responsetime {
		t.Errorf("Responsetime mismatch: got %d, want %d", parsedRes.Responsetime, res.Responsetime)
	}
}

func TestPingTypesRequest_Serialization(t *testing.T) {
	req := &dnfv1.PingTypesRequest{
		Timestamp: 1234567890123456789,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.PingTypesRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Timestamp != req.Timestamp {
		t.Errorf("Timestamp mismatch: got %d, want %d", parsedReq.Timestamp, req.Timestamp)
	}
}

func TestPingTypesResponse_Serialization(t *testing.T) {
	res := &dnfv1.PingTypesResponse{
		Error:     0,
		Timestamp: 1234567890123456789,
	}

	data, err := proto.Marshal(res)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedRes dnfv1.PingTypesResponse
	if err := proto.Unmarshal(data, &parsedRes); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedRes.Error != res.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, res.Error)
	}
	if parsedRes.Timestamp != res.Timestamp {
		t.Errorf("Timestamp mismatch: got %d, want %d", parsedRes.Timestamp, res.Timestamp)
	}
}

func TestSessionLogoutRequest_Serialization(t *testing.T) {
	req := &dnfv1.SessionLogoutRequest{
		AuthIndex: 1234567890123456789,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.SessionLogoutRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.AuthIndex != req.AuthIndex {
		t.Errorf("AuthIndex mismatch: got %d, want %d", parsedReq.AuthIndex, req.AuthIndex)
	}
}

func TestSessionLogoutResponse_Serialization(t *testing.T) {
	res := &dnfv1.SessionLogoutResponse{
		Error:      0,
		ServerType: 1,
		ServerKey:  1234567890123456789,
	}

	data, err := proto.Marshal(res)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedRes dnfv1.SessionLogoutResponse
	if err := proto.Unmarshal(data, &parsedRes); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedRes.Error != res.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, res.Error)
	}
	if parsedRes.ServerType != res.ServerType {
		t.Errorf("ServerType mismatch: got %d, want %d", parsedRes.ServerType, res.ServerType)
	}
	if parsedRes.ServerKey != res.ServerKey {
		t.Errorf("ServerKey mismatch: got %d, want %d", parsedRes.ServerKey, res.ServerKey)
	}
}

func TestPingResponse_BoundaryValues(t *testing.T) {
	testCases := []struct {
		name string
		res  *dnfv1.PingResponse
	}{
		{
			name: "zero values",
			res: &dnfv1.PingResponse{
				Error:        0,
				Responsetime: 0,
			},
		},
		{
			name: "max values",
			res: &dnfv1.PingResponse{
				Error:        2147483647,
				Responsetime: 2147483647,
			},
		},
		{
			name: "negative values",
			res: &dnfv1.PingResponse{
				Error:        -1,
				Responsetime: -1,
			},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			data, err := proto.Marshal(tc.res)
			if err != nil {
				t.Fatalf("Marshal failed: %v", err)
			}

			var parsedRes dnfv1.PingResponse
			if err := proto.Unmarshal(data, &parsedRes); err != nil {
				t.Fatalf("Unmarshal failed: %v", err)
			}

			if parsedRes.Error != tc.res.Error {
				t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, tc.res.Error)
			}
			if parsedRes.Responsetime != tc.res.Responsetime {
				t.Errorf("Responsetime mismatch: got %d, want %d", parsedRes.Responsetime, tc.res.Responsetime)
			}
		})
	}
}

func TestSessionLogoutResponse_BoundaryValues(t *testing.T) {
	testCases := []struct {
		name string
		res  *dnfv1.SessionLogoutResponse
	}{
		{
			name: "zero values",
			res: &dnfv1.SessionLogoutResponse{
				Error:      0,
				ServerType: 0,
				ServerKey:  0,
			},
		},
		{
			name: "max values",
			res: &dnfv1.SessionLogoutResponse{
				Error:      2147483647,
				ServerType: 2147483647,
				ServerKey:  9223372036854775807,
			},
		},
		{
			name: "negative values",
			res: &dnfv1.SessionLogoutResponse{
				Error:      -1,
				ServerType: -1,
				ServerKey:  -1,
			},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			data, err := proto.Marshal(tc.res)
			if err != nil {
				t.Fatalf("Marshal failed: %v", err)
			}

			var parsedRes dnfv1.SessionLogoutResponse
			if err := proto.Unmarshal(data, &parsedRes); err != nil {
				t.Fatalf("Unmarshal failed: %v", err)
			}

			if parsedRes.Error != tc.res.Error {
				t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, tc.res.Error)
			}
			if parsedRes.ServerType != tc.res.ServerType {
				t.Errorf("ServerType mismatch: got %d, want %d", parsedRes.ServerType, tc.res.ServerType)
			}
			if parsedRes.ServerKey != tc.res.ServerKey {
				t.Errorf("ServerKey mismatch: got %d, want %d", parsedRes.ServerKey, tc.res.ServerKey)
			}
		})
	}
}
