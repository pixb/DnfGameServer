package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试成就列表请求消息
func TestBatch12AchievementListRequest(t *testing.T) {
	req := &dnfv1.AchievementListRequest{
		CharacterId: 1,
		CategoryId:  0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementListRequest: %v", err)
	}

	unmarshaled := &dnfv1.AchievementListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementListRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.CategoryId != req.CategoryId {
		t.Errorf("CategoryId mismatch: got %d, want %d", unmarshaled.CategoryId, req.CategoryId)
	}
}

// 测试成就列表响应消息
func TestBatch12AchievementListResponse(t *testing.T) {
	resp := &dnfv1.AchievementListResponse{
		Error:           0,
		CharacterId:     1,
		TotalPoints:     100,
		CompletedCount:  10,
		TotalCount:      50,
		Categories: []*dnfv1.AchievementCategory{
			{
				CategoryId: 1,
				Name:       "Test Category",
				Order:      1,
			},
		},
		Achievements: []*dnfv1.AchievementInfo{
			{
				AchievementId: 1,
				Name:          "Test Achievement",
				Description:   "Test Description",
				CategoryId:    1,
				Points:        10,
				Completed:     true,
				CompletedTime: 1234567890,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementListResponse: %v", err)
	}

	unmarshaled := &dnfv1.AchievementListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if len(unmarshaled.Categories) != len(resp.Categories) {
		t.Errorf("Categories length mismatch: got %d, want %d", len(unmarshaled.Categories), len(resp.Categories))
	}

	if len(unmarshaled.Achievements) != len(resp.Achievements) {
		t.Errorf("Achievements length mismatch: got %d, want %d", len(unmarshaled.Achievements), len(resp.Achievements))
	}
}

// 测试成就信息请求消息
func TestBatch12AchievementInfoRequest(t *testing.T) {
	req := &dnfv1.AchievementInfoRequest{
		CharacterId:    1,
		AchievementId:  1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.AchievementInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementInfoRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.AchievementId != req.AchievementId {
		t.Errorf("AchievementId mismatch: got %d, want %d", unmarshaled.AchievementId, req.AchievementId)
	}
}

// 测试成就信息响应消息
func TestBatch12AchievementInfoResponse(t *testing.T) {
	resp := &dnfv1.AchievementInfoResponse{
		Error:       0,
		CharacterId: 1,
		Achievement: &dnfv1.AchievementInfo{
			AchievementId: 1,
			Name:          "Test Achievement",
			Description:   "Test Description",
			CategoryId:    1,
			Points:        10,
			Progress: &dnfv1.AchievementProgress{
				Current:    100,
				Target:     100,
				Completed:  true,
			},
			Rewards: []*dnfv1.AchievementReward{
				{
					RewardId: 1,
					Type:     1,
					Amount:   100,
					Claimed:  false,
				},
			},
			Completed:     true,
			CompletedTime: 1234567890,
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementInfoResponse: %v", err)
	}

	unmarshaled := &dnfv1.AchievementInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementInfoResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.Achievement == nil {
		t.Errorf("Achievement should not be nil")
	}
}

// 测试成就奖励请求消息
func TestBatch12AchievementRewardRequest(t *testing.T) {
	req := &dnfv1.AchievementRewardRequest{
		CharacterId:    1,
		AchievementId:  1,
		RewardId:       1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementRewardRequest: %v", err)
	}

	unmarshaled := &dnfv1.AchievementRewardRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementRewardRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.AchievementId != req.AchievementId {
		t.Errorf("AchievementId mismatch: got %d, want %d", unmarshaled.AchievementId, req.AchievementId)
	}

	if unmarshaled.RewardId != req.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", unmarshaled.RewardId, req.RewardId)
	}
}

// 测试成就奖励响应消息
func TestBatch12AchievementRewardResponse(t *testing.T) {
	resp := &dnfv1.AchievementRewardResponse{
		Error:         0,
		CharacterId:   1,
		AchievementId: 1,
		RewardId:      1,
		Success:       true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementRewardResponse: %v", err)
	}

	unmarshaled := &dnfv1.AchievementRewardResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementRewardResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.AchievementId != resp.AchievementId {
		t.Errorf("AchievementId mismatch: got %d, want %d", unmarshaled.AchievementId, resp.AchievementId)
	}

	if unmarshaled.RewardId != resp.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", unmarshaled.RewardId, resp.RewardId)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试成就额外奖励请求消息
func TestBatch12AchievementBonusRewardRequest(t *testing.T) {
	req := &dnfv1.AchievementBonusRewardRequest{
		CharacterId: 1,
		BonusId:     1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementBonusRewardRequest: %v", err)
	}

	unmarshaled := &dnfv1.AchievementBonusRewardRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementBonusRewardRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.BonusId != req.BonusId {
		t.Errorf("BonusId mismatch: got %d, want %d", unmarshaled.BonusId, req.BonusId)
	}
}

// 测试成就额外奖励响应消息
func TestBatch12AchievementBonusRewardResponse(t *testing.T) {
	resp := &dnfv1.AchievementBonusRewardResponse{
		Error:       0,
		CharacterId: 1,
		BonusId:     1,
		Success:     true,
		Rewards: []*dnfv1.AchievementReward{
			{
				RewardId: 1,
				Type:     1,
				Amount:   100,
				Claimed:  true,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementBonusRewardResponse: %v", err)
	}

	unmarshaled := &dnfv1.AchievementBonusRewardResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementBonusRewardResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.BonusId != resp.BonusId {
		t.Errorf("BonusId mismatch: got %d, want %d", unmarshaled.BonusId, resp.BonusId)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}

	if len(unmarshaled.Rewards) != len(resp.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(unmarshaled.Rewards), len(resp.Rewards))
	}
}

// 测试成就分类
func TestBatch12AchievementCategory(t *testing.T) {
	category := &dnfv1.AchievementCategory{
		CategoryId: 1,
		Name:       "Test Category",
		Order:      1,
	}

	data, err := proto.Marshal(category)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementCategory: %v", err)
	}

	unmarshaled := &dnfv1.AchievementCategory{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementCategory: %v", err)
	}

	if unmarshaled.CategoryId != category.CategoryId {
		t.Errorf("CategoryId mismatch: got %d, want %d", unmarshaled.CategoryId, category.CategoryId)
	}

	if unmarshaled.Name != category.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, category.Name)
	}

	if unmarshaled.Order != category.Order {
		t.Errorf("Order mismatch: got %d, want %d", unmarshaled.Order, category.Order)
	}
}

// 测试成就进度
func TestBatch12AchievementProgress(t *testing.T) {
	progress := &dnfv1.AchievementProgress{
		Current:   50,
		Target:    100,
		Completed: false,
	}

	data, err := proto.Marshal(progress)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementProgress: %v", err)
	}

	unmarshaled := &dnfv1.AchievementProgress{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementProgress: %v", err)
	}

	if unmarshaled.Current != progress.Current {
		t.Errorf("Current mismatch: got %d, want %d", unmarshaled.Current, progress.Current)
	}

	if unmarshaled.Target != progress.Target {
		t.Errorf("Target mismatch: got %d, want %d", unmarshaled.Target, progress.Target)
	}

	if unmarshaled.Completed != progress.Completed {
		t.Errorf("Completed mismatch: got %v, want %v", unmarshaled.Completed, progress.Completed)
	}
}

// 测试成就奖励
func TestBatch12AchievementReward(t *testing.T) {
	reward := &dnfv1.AchievementReward{
		RewardId: 1,
		Type:     1,
		Amount:   100,
		Claimed:  false,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementReward: %v", err)
	}

	unmarshaled := &dnfv1.AchievementReward{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementReward: %v", err)
	}

	if unmarshaled.RewardId != reward.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", unmarshaled.RewardId, reward.RewardId)
	}

	if unmarshaled.Type != reward.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, reward.Type)
	}

	if unmarshaled.Amount != reward.Amount {
		t.Errorf("Amount mismatch: got %d, want %d", unmarshaled.Amount, reward.Amount)
	}

	if unmarshaled.Claimed != reward.Claimed {
		t.Errorf("Claimed mismatch: got %v, want %v", unmarshaled.Claimed, reward.Claimed)
	}
}

// 测试成就信息
func TestBatch12AchievementInfo(t *testing.T) {
	info := &dnfv1.AchievementInfo{
		AchievementId: 1,
		Name:          "Test Achievement",
		Description:   "Test Description",
		CategoryId:    1,
		Points:        10,
		Progress: &dnfv1.AchievementProgress{
			Current:    100,
			Target:     100,
			Completed:  true,
		},
		Rewards: []*dnfv1.AchievementReward{
			{
				RewardId: 1,
				Type:     1,
				Amount:   100,
				Claimed:  false,
			},
		},
		Completed:     true,
		CompletedTime: 1234567890,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AchievementInfo: %v", err)
	}

	unmarshaled := &dnfv1.AchievementInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AchievementInfo: %v", err)
	}

	if unmarshaled.AchievementId != info.AchievementId {
		t.Errorf("AchievementId mismatch: got %d, want %d", unmarshaled.AchievementId, info.AchievementId)
	}

	if unmarshaled.Name != info.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, info.Name)
	}

	if unmarshaled.Description != info.Description {
		t.Errorf("Description mismatch: got %s, want %s", unmarshaled.Description, info.Description)
	}

	if unmarshaled.CategoryId != info.CategoryId {
		t.Errorf("CategoryId mismatch: got %d, want %d", unmarshaled.CategoryId, info.CategoryId)
	}

	if unmarshaled.Points != info.Points {
		t.Errorf("Points mismatch: got %d, want %d", unmarshaled.Points, info.Points)
	}

	if unmarshaled.Completed != info.Completed {
		t.Errorf("Completed mismatch: got %v, want %v", unmarshaled.Completed, info.Completed)
	}

	if unmarshaled.CompletedTime != info.CompletedTime {
		t.Errorf("CompletedTime mismatch: got %d, want %d", unmarshaled.CompletedTime, info.CompletedTime)
	}
}