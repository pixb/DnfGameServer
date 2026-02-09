package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试任务列表请求消息
func TestBatch16TaskListRequest(t *testing.T) {
	req := &dnfv1.TaskListRequest{
		CharacterId: 1,
		TaskType:    1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskListRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskListRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskType != req.TaskType {
		t.Errorf("TaskType mismatch: got %d, want %d", unmarshaled.TaskType, req.TaskType)
	}
}

// 测试任务列表响应消息
func TestBatch16TaskListResponse(t *testing.T) {
	resp := &dnfv1.TaskListResponse{
		Error:       0,
		CharacterId: 1,
		Tasks: []*dnfv1.TaskBriefInfo{
			{
				TaskId:     1,
				TaskName:   "Test Task",
				TaskType:   1,
				TaskStatus: 1,
				TaskLevel:  10,
				TaskIcon:   "icon.png",
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskListResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if len(unmarshaled.Tasks) != len(resp.Tasks) {
		t.Errorf("Tasks length mismatch: got %d, want %d", len(unmarshaled.Tasks), len(resp.Tasks))
	}
}

// 测试任务详情请求消息
func TestBatch16TaskInfoRequest(t *testing.T) {
	req := &dnfv1.TaskInfoRequest{
		CharacterId: 1,
		TaskId:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskInfoRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskId != req.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, req.TaskId)
	}
}

// 测试任务详情响应消息
func TestBatch16TaskInfoResponse(t *testing.T) {
	resp := &dnfv1.TaskInfoResponse{
		Error:       0,
		CharacterId: 1,
		TaskInfo: &dnfv1.TaskDetailInfo{
			TaskId:      1,
			TaskName:    "Test Task",
			TaskDesc:    "Test task description",
			TaskType:    1,
			TaskStatus:  1,
			TaskLevel:   10,
			AcceptLevel: 5,
			Objectives: []*dnfv1.TaskObjective{
				{
					ObjectiveId:     1,
					ObjectiveDesc:   "Kill 10 monsters",
					CurrentProgress: 5,
					TargetProgress:  10,
					IsCompleted:     false,
				},
			},
			Rewards: []*dnfv1.TaskReward{
				{
					RewardId:    1,
					RewardType:  1,
					RewardCount: 100,
					RewardName:  "Gold",
				},
			},
			TaskNpc:      "Test NPC",
			AcceptTime:   1234567890,
			DeadlineTime: 1234567890 + 86400,
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskInfoResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskInfoResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.TaskInfo == nil {
		t.Errorf("TaskInfo should not be nil")
	}
}

// 测试任务接受请求消息
func TestBatch16TaskAcceptRequest(t *testing.T) {
	req := &dnfv1.TaskAcceptRequest{
		CharacterId: 1,
		TaskId:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskAcceptRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskAcceptRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskAcceptRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskId != req.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, req.TaskId)
	}
}

// 测试任务接受响应消息
func TestBatch16TaskAcceptResponse(t *testing.T) {
	resp := &dnfv1.TaskAcceptResponse{
		Error:       0,
		CharacterId: 1,
		TaskId:      1,
		Success:     true,
		Message:     "Task accepted successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskAcceptResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskAcceptResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskAcceptResponse: %v", err)
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

// 测试任务完成请求消息
func TestBatch16TaskFinishRequest(t *testing.T) {
	req := &dnfv1.TaskFinishRequest{
		CharacterId: 1,
		TaskId:      1,
		NpcId:       1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TaskFinishRequest: %v", err)
	}

	unmarshaled := &dnfv1.TaskFinishRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskFinishRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.TaskId != req.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, req.TaskId)
	}

	if unmarshaled.NpcId != req.NpcId {
		t.Errorf("NpcId mismatch: got %d, want %d", unmarshaled.NpcId, req.NpcId)
	}
}

// 测试任务完成响应消息
func TestBatch16TaskFinishResponse(t *testing.T) {
	resp := &dnfv1.TaskFinishResponse{
		Error:       0,
		CharacterId: 1,
		TaskId:      1,
		Success:     true,
		Message:     "Task completed successfully",
		Rewards: []*dnfv1.TaskReward{
			{
				RewardId:    1,
				RewardType:  1,
				RewardCount: 100,
				RewardName:  "Gold",
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TaskFinishResponse: %v", err)
	}

	unmarshaled := &dnfv1.TaskFinishResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskFinishResponse: %v", err)
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

// 测试任务简要信息
func TestBatch16TaskBriefInfo(t *testing.T) {
	taskInfo := &dnfv1.TaskBriefInfo{
		TaskId:     1,
		TaskName:   "Test Task",
		TaskType:   1,
		TaskStatus: 1,
		TaskLevel:  10,
		TaskIcon:   "icon.png",
	}

	data, err := proto.Marshal(taskInfo)
	if err != nil {
		t.Fatalf("Failed to marshal TaskBriefInfo: %v", err)
	}

	unmarshaled := &dnfv1.TaskBriefInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskBriefInfo: %v", err)
	}

	if unmarshaled.TaskId != taskInfo.TaskId {
		t.Errorf("TaskId mismatch: got %d, want %d", unmarshaled.TaskId, taskInfo.TaskId)
	}

	if unmarshaled.TaskName != taskInfo.TaskName {
		t.Errorf("TaskName mismatch: got %s, want %s", unmarshaled.TaskName, taskInfo.TaskName)
	}

	if unmarshaled.TaskType != taskInfo.TaskType {
		t.Errorf("TaskType mismatch: got %d, want %d", unmarshaled.TaskType, taskInfo.TaskType)
	}

	if unmarshaled.TaskStatus != taskInfo.TaskStatus {
		t.Errorf("TaskStatus mismatch: got %d, want %d", unmarshaled.TaskStatus, taskInfo.TaskStatus)
	}

	if unmarshaled.TaskLevel != taskInfo.TaskLevel {
		t.Errorf("TaskLevel mismatch: got %d, want %d", unmarshaled.TaskLevel, taskInfo.TaskLevel)
	}

	if unmarshaled.TaskIcon != taskInfo.TaskIcon {
		t.Errorf("TaskIcon mismatch: got %s, want %s", unmarshaled.TaskIcon, taskInfo.TaskIcon)
	}
}

// 测试任务目标
func TestBatch16TaskObjective(t *testing.T) {
	objective := &dnfv1.TaskObjective{
		ObjectiveId:     1,
		ObjectiveDesc:   "Kill 10 monsters",
		CurrentProgress: 5,
		TargetProgress:  10,
		IsCompleted:     false,
	}

	data, err := proto.Marshal(objective)
	if err != nil {
		t.Fatalf("Failed to marshal TaskObjective: %v", err)
	}

	unmarshaled := &dnfv1.TaskObjective{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskObjective: %v", err)
	}

	if unmarshaled.ObjectiveId != objective.ObjectiveId {
		t.Errorf("ObjectiveId mismatch: got %d, want %d", unmarshaled.ObjectiveId, objective.ObjectiveId)
	}

	if unmarshaled.ObjectiveDesc != objective.ObjectiveDesc {
		t.Errorf("ObjectiveDesc mismatch: got %s, want %s", unmarshaled.ObjectiveDesc, objective.ObjectiveDesc)
	}

	if unmarshaled.CurrentProgress != objective.CurrentProgress {
		t.Errorf("CurrentProgress mismatch: got %d, want %d", unmarshaled.CurrentProgress, objective.CurrentProgress)
	}

	if unmarshaled.TargetProgress != objective.TargetProgress {
		t.Errorf("TargetProgress mismatch: got %d, want %d", unmarshaled.TargetProgress, objective.TargetProgress)
	}

	if unmarshaled.IsCompleted != objective.IsCompleted {
		t.Errorf("IsCompleted mismatch: got %v, want %v", unmarshaled.IsCompleted, objective.IsCompleted)
	}
}

// 测试任务奖励
func TestBatch16TaskReward(t *testing.T) {
	reward := &dnfv1.TaskReward{
		RewardId:    1,
		RewardType:  1,
		RewardCount: 100,
		RewardName:  "Gold",
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal TaskReward: %v", err)
	}

	unmarshaled := &dnfv1.TaskReward{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TaskReward: %v", err)
	}

	if unmarshaled.RewardId != reward.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", unmarshaled.RewardId, reward.RewardId)
	}

	if unmarshaled.RewardType != reward.RewardType {
		t.Errorf("RewardType mismatch: got %d, want %d", unmarshaled.RewardType, reward.RewardType)
	}

	if unmarshaled.RewardCount != reward.RewardCount {
		t.Errorf("RewardCount mismatch: got %d, want %d", unmarshaled.RewardCount, reward.RewardCount)
	}

	if unmarshaled.RewardName != reward.RewardName {
		t.Errorf("RewardName mismatch: got %s, want %s", unmarshaled.RewardName, reward.RewardName)
	}
}
