package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func TestAuthkeyRefreshRequest_Serialization(t *testing.T) {
	req := &dnfv1.AuthkeyRefreshRequest{
		Authkey: "test_authkey_123",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.AuthkeyRefreshRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedReq.Authkey, req.Authkey)
	}
}

func TestAuthkeyRefreshResponse_Serialization(t *testing.T) {
	resp := &dnfv1.AuthkeyRefreshResponse{
		Error:   0,
		Authkey: "new_authkey_456",
		Version: "1.0.0",
	}

	resp.Channel = append(resp.Channel, &dnfv1.ChannelInfo{
		World:    1,
		Channel:  1,
		Ip:       "127.0.0.1",
		Port:     10001,
		Priority: 1,
	})

	resp.Channel = append(resp.Channel, &dnfv1.ChannelInfo{
		World:    1,
		Channel:  2,
		Ip:       "127.0.0.1",
		Port:     10002,
		Priority: 2,
	})

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.AuthkeyRefreshResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if parsedResp.Authkey != resp.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedResp.Authkey, resp.Authkey)
	}
	if parsedResp.Version != resp.Version {
		t.Errorf("Version mismatch: got %s, want %s", parsedResp.Version, resp.Version)
	}
	if len(parsedResp.Channel) != len(resp.Channel) {
		t.Errorf("Channel length mismatch: got %d, want %d", len(parsedResp.Channel), len(resp.Channel))
	}
}

func TestPlatformProfileUpdateRequest_Serialization(t *testing.T) {
	req := &dnfv1.PlatformProfileUpdateRequest{
		Profileurl:      "https://example.com/profile.jpg",
		Profilename:     "Test User",
		Firstlocation:   1,
		Secondlocation:  2,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.PlatformProfileUpdateRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Profileurl != req.Profileurl {
		t.Errorf("Profileurl mismatch: got %s, want %s", parsedReq.Profileurl, req.Profileurl)
	}
	if parsedReq.Profilename != req.Profilename {
		t.Errorf("Profilename mismatch: got %s, want %s", parsedReq.Profilename, req.Profilename)
	}
	if parsedReq.Firstlocation != req.Firstlocation {
		t.Errorf("Firstlocation mismatch: got %d, want %d", parsedReq.Firstlocation, req.Firstlocation)
	}
	if parsedReq.Secondlocation != req.Secondlocation {
		t.Errorf("Secondlocation mismatch: got %d, want %d", parsedReq.Secondlocation, req.Secondlocation)
	}
}

func TestPlatformProfileUpdateResponse_Serialization(t *testing.T) {
	resp := &dnfv1.PlatformProfileUpdateResponse{
		Error: 0,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.PlatformProfileUpdateResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
}

func TestAuthkeyRefreshRequest_EmptyString(t *testing.T) {
	req := &dnfv1.AuthkeyRefreshRequest{
		Authkey: "",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.AuthkeyRefreshRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedReq.Authkey, req.Authkey)
	}
}

func TestAuthkeyRefreshResponse_MultipleChannels(t *testing.T) {
	resp := &dnfv1.AuthkeyRefreshResponse{
		Error:   0,
		Authkey: "test_authkey",
		Version: "1.0.0",
	}

	for i := 0; i < 5; i++ {
		resp.Channel = append(resp.Channel, &dnfv1.ChannelInfo{
			World:    1,
			Channel:  int32(i + 1),
			Ip:       "127.0.0.1",
			Port:     int32(10000 + i),
			Priority: int32(i + 1),
		})
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.AuthkeyRefreshResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if len(parsedResp.Channel) != 5 {
		t.Errorf("Channel length mismatch: got %d, want %d", len(parsedResp.Channel), 5)
	}

	for i, channel := range parsedResp.Channel {
		expectedChannel := int32(i + 1)
		expectedPort := int32(10000 + i)
		expectedPriority := int32(i + 1)
		if channel.Channel != expectedChannel {
			t.Errorf("Channel[%d] Channel mismatch: got %d, want %d", i, channel.Channel, expectedChannel)
		}
		if channel.Port != expectedPort {
			t.Errorf("Channel[%d] Port mismatch: got %d, want %d", i, channel.Port, expectedPort)
		}
		if channel.Priority != expectedPriority {
			t.Errorf("Channel[%d] Priority mismatch: got %d, want %d", i, channel.Priority, expectedPriority)
		}
	}
}

func TestPlatformProfileUpdateRequest_BoundaryValues(t *testing.T) {
	tests := []struct {
		name string
		req  *dnfv1.PlatformProfileUpdateRequest
	}{
		{
			name: "zero_values",
			req: &dnfv1.PlatformProfileUpdateRequest{
				Profileurl:      "",
				Profilename:     "",
				Firstlocation:   0,
				Secondlocation:  0,
			},
		},
		{
			name: "max_values",
			req: &dnfv1.PlatformProfileUpdateRequest{
				Profileurl:      "https://example.com/very_long_profile_url_that_exceeds_normal_length",
				Profilename:     "Very Long Profile Name That Exceeds Normal Length",
				Firstlocation:   2147483647,
				Secondlocation:  2147483647,
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			data, err := proto.Marshal(tt.req)
			if err != nil {
				t.Fatalf("Marshal failed: %v", err)
			}

			var parsedReq dnfv1.PlatformProfileUpdateRequest
			if err := proto.Unmarshal(data, &parsedReq); err != nil {
				t.Fatalf("Unmarshal failed: %v", err)
			}

			if parsedReq.Profileurl != tt.req.Profileurl {
				t.Errorf("Profileurl mismatch: got %s, want %s", parsedReq.Profileurl, tt.req.Profileurl)
			}
			if parsedReq.Profilename != tt.req.Profilename {
				t.Errorf("Profilename mismatch: got %s, want %s", parsedReq.Profilename, tt.req.Profilename)
			}
			if parsedReq.Firstlocation != tt.req.Firstlocation {
				t.Errorf("Firstlocation mismatch: got %d, want %d", parsedReq.Firstlocation, tt.req.Firstlocation)
			}
			if parsedReq.Secondlocation != tt.req.Secondlocation {
				t.Errorf("Secondlocation mismatch: got %d, want %d", parsedReq.Secondlocation, tt.req.Secondlocation)
			}
		})
	}
}
