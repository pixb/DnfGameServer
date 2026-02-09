package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试流数据请求消息
func TestBatch22StreamDataRequest(t *testing.T) {
	req := &dnfv1.StreamDataRequest{
		StartFrame: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal StreamDataRequest: %v", err)
	}

	unmarshaled := &dnfv1.StreamDataRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal StreamDataRequest: %v", err)
	}

	if unmarshaled.StartFrame != req.StartFrame {
		t.Errorf("StartFrame mismatch: got %d, want %d", unmarshaled.StartFrame, req.StartFrame)
	}
}

// 测试流数据响应消息
func TestBatch22StreamDataResponse(t *testing.T) {
	resp := &dnfv1.StreamDataResponse{
		Error:    0,
		ReqFrame: 1,
		EndFrame: 10,
		Frames: []*dnfv1.FrameData{
			{
				FrameSize:  100,
				SplitCount: 1,
				SplitSeq:   0,
				FrameData:  []byte{1, 2, 3, 4, 5},
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal StreamDataResponse: %v", err)
	}

	unmarshaled := &dnfv1.StreamDataResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal StreamDataResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.ReqFrame != resp.ReqFrame {
		t.Errorf("ReqFrame mismatch: got %d, want %d", unmarshaled.ReqFrame, resp.ReqFrame)
	}

	if unmarshaled.EndFrame != resp.EndFrame {
		t.Errorf("EndFrame mismatch: got %d, want %d", unmarshaled.EndFrame, resp.EndFrame)
	}

	if len(unmarshaled.Frames) != len(resp.Frames) {
		t.Errorf("Frames length mismatch: got %d, want %d", len(unmarshaled.Frames), len(resp.Frames))
	}
}

// 测试用户信息消息
func TestBatch22UserInfo(t *testing.T) {
	userInfo := &dnfv1.UserInfo{
		Charguid:       123456789,
		Job:            1,
		Growtype:       1,
		Secgrowtype:    0,
		TeamType:       0,
		World:          1,
		Level:          50,
		Name:           "TestUser",
		ProfileUrl:     "",
		ProfileName:    "",
		CharacterFrame: 0,
		Rank:           100,
	}

	data, err := proto.Marshal(userInfo)
	if err != nil {
		t.Fatalf("Failed to marshal UserInfo: %v", err)
	}

	unmarshaled := &dnfv1.UserInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal UserInfo: %v", err)
	}

	if unmarshaled.Charguid != userInfo.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", unmarshaled.Charguid, userInfo.Charguid)
	}

	if unmarshaled.Job != userInfo.Job {
		t.Errorf("Job mismatch: got %d, want %d", unmarshaled.Job, userInfo.Job)
	}

	if unmarshaled.Name != userInfo.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, userInfo.Name)
	}

	if unmarshaled.Level != userInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, userInfo.Level)
	}
}
