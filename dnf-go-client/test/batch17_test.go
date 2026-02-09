package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试任务进度更新请求消息
func TestBatch17TaskProgressUpdateRequest(t *testing.T) {
	req := &dnfv1.TaskProgressUpdateRequest{
		CharacterId: 1,
		TaskId:      1,
		ObjectiveId: 1,
		Progress:    5,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskProgressUpdateRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskProgressUpdateRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskProgressUpdateRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskId != req.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, req.TaskId)
	}

	if unmarshaled.ObjectiveId != req.ObjectiveId {
		t.Errorf("ObjectiveId mismatch: got %d, want %d", unmarshaled.ObjectiveId, req.ObjectiveId)
	}

	if unmarshaled.Progress != req.Progress {
		t.Errorf("Progress mismatch: got %d, want %d", unmarshaled.Progress, req.Progress)
	}
}

// 测试任务进度更新响应消息
func TestBatch17TaskProgressUpdateResponse(t *testing.T) {
	resp := &dnfv1.TaskProgressUpdateResponse{
		Error:           0,
		CharacterId:     1,
		TaskId:          1,
		ObjectiveId:      1,
		CurrentProgress: 5,
		IsCompleted:     false,
		Message:         "Progress updated successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskProgressUpdateResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskProgressUpdateResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskProgressUpdateResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.TaskId != resp.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, resp.TaskId)
	}

	if unmarshaled.ObjectiveId != resp.ObjectiveId {
		t.Errorf("ObjectiveId mismatch: got %d, want %d", unmarshaled.ObjectiveId, resp.ObjectiveId)
	}

	if unmarshaled.CurrentProgress != resp.CurrentProgress {
		t.Errorf("CurrentProgress mismatch: got %d, want %d", unmarshaled.CurrentProgress, resp.CurrentProgress)
	}

	if unmarshaled.IsCompleted != resp.IsCompleted {
		t.Errorf("IsCompleted mismatch: got %v, want %v", unmarshaled.IsCompleted, resp.IsCompleted)
	}

	if unmarshaled.Message != resp.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, resp.Message)
	}
}

// 测试任务放弃请求消息
func TestBatch17TaskAbandonRequest(t *testing.T) {
	req := &dnfv1.TaskAbandonRequest{
		CharacterId: 1,
		TaskId:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskAbandonRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskAbandonRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskAbandonRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskId != req.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, req.TaskId)
	}
}

// 测试任务放弃响应消息
func TestBatch17TaskAbandonResponse(t *testing.T) {
	resp := &dnfv1.TaskAbandonResponse{
		Error:       0,
		CharacterId: 1,
		TaskId:      1,
		Success:     true,
		Message:     "Task abandoned successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskAbandonResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskAbandonResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskAbandonResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.TaskId != resp.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, resp.TaskId)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}

	if unmarshaled.Message != resp.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, resp.Message)
	}
}

// 测试任务奖励领取请求消息
func TestBatch17TaskRewardClaimRequest(t *testing.T) {
	req := &dnfv1.TaskRewardClaimRequest{
		CharacterId: 1,
		TaskId:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskRewardClaimRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskRewardClaimRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskRewardClaimRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskId != req.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, req.TaskId)
	}
}

// 测试任务奖励领取响应消息
func TestBatch17TaskRewardClaimResponse(t *testing.T) {
	resp := &dnfv1.TaskRewardClaimResponse{
		Error:       0,
		CharacterId: 1,
		TaskId:      1,
		Success:     true,
		Message:     "Reward claimed successfully",
		Rewards: []*dnfv1.TaskReward{
			{
				RewardId:      1,
				RewardType:     1,
				RewardCount:    100,
				RewardName:     "Gold",
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskRewardClaimResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskRewardClaimResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskRewardClaimResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.TaskId != resp.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, resp.TaskId)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}

	if unmarshaled.Message != resp.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, resp.Message)
	}

	if len(unmarshaled.Rewards) != len(resp.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(unmarshaled.Rewards), len(resp.Rewards))
	}
}

// 测试任务追踪请求消息
func TestBatch17TaskTrackRequest(t *testing.T) {
	req := &dnfv1.TaskTrackRequest{
		CharacterId: 1,
		TaskId:      1,
		IsTracking:  true,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskTrackRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskTrackRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskTrackRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskId != req.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, req.TaskId)
	}

	if unmarshaled.IsTracking != req.IsTracking {
		t.Errorf("IsTracking mismatch: got %v, want %v", unmarshaled.IsTracking, req.IsTracking)
	}
}

// 测试任务追踪响应消息
func TestBatch17TaskTrackResponse(t *testing.T) {
	resp := &dnfv1.TaskTrackResponse{
		Error:       0,
		CharacterId: 1,
		TaskId:      1,
		IsTracking:  true,
		Message:     "Task tracking updated successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskTrackResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskTrackResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskTrackResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.TaskId != resp.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, resp.TaskId)
	}

	if unmarshaled.IsTracking != resp.IsTracking {
		t.Errorf("IsTracking mismatch: got %v, want %v", unmarshaled.IsTracking, resp.IsTracking)
	}

	if unmarshaled.Message != resp.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, resp.Message)
	}
}
