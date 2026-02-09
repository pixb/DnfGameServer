package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试冒险数据请求消息
func TestBatch13AdventureDataRequest(t *testing.T) {
	req := &dnfv1.AdventureDataRequest{
		CharacterId: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureDataRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureDataRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureDataRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}
}

// 测试冒险数据响应消息
func TestBatch13AdventureDataResponse(t *testing.T) {
	resp := &dnfv1.AdventureDataResponse{
		Error:       0,
		CharacterId: 1,
		Data: &dnfv1.AdventureData{
			AdventureLevel:        10,
			AdventureExp:          1000,
			Energy:                100,
			MaxEnergy:             100,
			LastEnergyRecovery:    1234567890,
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureDataResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureDataResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureDataResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.Data == nil {
		t.Errorf("Data should not be nil")
	}
}

// 测试冒险收获信息请求消息
func TestBatch13AdventureReapInfoRequest(t *testing.T) {
	req := &dnfv1.AdventureReapInfoRequest{
		CharacterId: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureReapInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureReapInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureReapInfoRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}
}

// 测试冒险收获信息响应消息
func TestBatch13AdventureReapInfoResponse(t *testing.T) {
	resp := &dnfv1.AdventureReapInfoResponse{
		Error:       0,
		CharacterId: 1,
		Reaps: []*dnfv1.AdventureReapInfo{
			{
				ReapId:       1,
				Progress:     100,
				Total:        100,
				IsCompleted:  true,
				StartTime:    1234567890,
				EndTime:      1234567890,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureReapInfoResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureReapInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureReapInfoResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if len(unmarshaled.Reaps) != len(resp.Reaps) {
		t.Errorf("Reaps length mismatch: got %d, want %d", len(unmarshaled.Reaps), len(resp.Reaps))
	}
}

// 测试冒险收获奖励请求消息
func TestBatch13AdventureReapRewardRequest(t *testing.T) {
	req := &dnfv1.AdventureReapRewardRequest{
		CharacterId: 1,
		ReapId:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureReapRewardRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureReapRewardRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureReapRewardRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.ReapId != req.ReapId {
		t.Errorf("ReapId mismatch: got %d, want %d", unmarshaled.ReapId, req.ReapId)
	}
}

// 测试冒险收获奖励响应消息
func TestBatch13AdventureReapRewardResponse(t *testing.T) {
	resp := &dnfv1.AdventureReapRewardResponse{
		Error:       0,
		CharacterId: 1,
		ReapId:      1,
		IsSuccess:   true,
		Rewards: []*dnfv1.AdventureReapReward{
			{
				RewardId:    1,
				Type:        1,
				Amount:      100,
				IsClaimed:   false,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureReapRewardResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureReapRewardResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureReapRewardResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.ReapId != resp.ReapId {
		t.Errorf("ReapId mismatch: got %d, want %d", unmarshaled.ReapId, resp.ReapId)
	}

	if unmarshaled.IsSuccess != resp.IsSuccess {
		t.Errorf("IsSuccess mismatch: got %v, want %v", unmarshaled.IsSuccess, resp.IsSuccess)
	}

	if len(unmarshaled.Rewards) != len(resp.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(unmarshaled.Rewards), len(resp.Rewards))
	}
}

// 测试冒险存储列表请求消息
func TestBatch13AdventureStorageListRequest(t *testing.T) {
	req := &dnfv1.AdventureStorageListRequest{
		CharacterId: 1,
		Page:        1,
		PageSize:    20,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureStorageListRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureStorageListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureStorageListRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.Page != req.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, req.Page)
	}

	if unmarshaled.PageSize != req.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, req.PageSize)
	}
}

// 测试冒险存储列表响应消息
func TestBatch13AdventureStorageListResponse(t *testing.T) {
	resp := &dnfv1.AdventureStorageListResponse{
		Error:       0,
		CharacterId: 1,
		Total:       0,
		Page:        1,
		PageSize:    20,
		Items: []*dnfv1.AdventureStorageItem{
			{
				ItemId:       1,
				Count:        10,
				IsBound:      false,
				StorageTime:  1234567890,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureStorageListResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureStorageListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureStorageListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.Total != resp.Total {
		t.Errorf("Total mismatch: got %d, want %d", unmarshaled.Total, resp.Total)
	}

	if unmarshaled.Page != resp.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, resp.Page)
	}

	if unmarshaled.PageSize != resp.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, resp.PageSize)
	}

	if len(unmarshaled.Items) != len(resp.Items) {
		t.Errorf("Items length mismatch: got %d, want %d", len(unmarshaled.Items), len(resp.Items))
	}
}

// 测试冒险自动搜索请求消息
func TestBatch13AdventureAutoSearchRequest(t *testing.T) {
	req := &dnfv1.AdventureAutoSearchRequest{
		CharacterId: 1,
		Duration:    60,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureAutoSearchRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureAutoSearchRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureAutoSearchRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.Duration != req.Duration {
		t.Errorf("Duration mismatch: got %d, want %d", unmarshaled.Duration, req.Duration)
	}
}

// 测试冒险自动搜索响应消息
func TestBatch13AdventureAutoSearchResponse(t *testing.T) {
	resp := &dnfv1.AdventureAutoSearchResponse{
		Error:       0,
		CharacterId: 1,
		IsSuccess:   true,
		Search: &dnfv1.AdventureAutoSearch{
			SearchId:     1,
			Duration:     60,
			Progress:     0,
			IsCompleted:  false,
			StartTime:    1234567890,
			EndTime:      1234567890,
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureAutoSearchResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureAutoSearchResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureAutoSearchResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.IsSuccess != resp.IsSuccess {
		t.Errorf("IsSuccess mismatch: got %v, want %v", unmarshaled.IsSuccess, resp.IsSuccess)
	}

	if unmarshaled.Search == nil {
		t.Errorf("Search should not be nil")
	}
}

// 测试冒险自动搜索奖励请求消息
func TestBatch13AdventureAutoSearchRewardRequest(t *testing.T) {
	req := &dnfv1.AdventureAutoSearchRewardRequest{
		CharacterId: 1,
		SearchId:    1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureAutoSearchRewardRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureAutoSearchRewardRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureAutoSearchRewardRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.SearchId != req.SearchId {
		t.Errorf("SearchId mismatch: got %d, want %d", unmarshaled.SearchId, req.SearchId)
	}
}

// 测试冒险自动搜索奖励响应消息
func TestBatch13AdventureAutoSearchRewardResponse(t *testing.T) {
	resp := &dnfv1.AdventureAutoSearchRewardResponse{
		Error:       0,
		CharacterId: 1,
		SearchId:    1,
		IsSuccess:   true,
		Rewards: []*dnfv1.AdventureReapReward{
			{
				RewardId:    1,
				Type:        1,
				Amount:      100,
				IsClaimed:   false,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureAutoSearchRewardResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureAutoSearchRewardResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureAutoSearchRewardResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.SearchId != resp.SearchId {
		t.Errorf("SearchId mismatch: got %d, want %d", unmarshaled.SearchId, resp.SearchId)
	}

	if unmarshaled.IsSuccess != resp.IsSuccess {
		t.Errorf("IsSuccess mismatch: got %v, want %v", unmarshaled.IsSuccess, resp.IsSuccess)
	}

	if len(unmarshaled.Rewards) != len(resp.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(unmarshaled.Rewards), len(resp.Rewards))
	}
}

// 测试冒险书信息请求消息
func TestBatch13AdventureBookInfoRequest(t *testing.T) {
	req := &dnfv1.AdventureBookInfoRequest{
		CharacterId: 1,
		BookId:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookInfoRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.BookId != req.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, req.BookId)
	}
}

// 测试冒险书信息响应消息
func TestBatch13AdventureBookInfoResponse(t *testing.T) {
	resp := &dnfv1.AdventureBookInfoResponse{
		Error:       0,
		CharacterId: 1,
		Books: []*dnfv1.AdventureBookInfo{
			{
				BookId:      1,
				Name:        "Test Book",
				Level:       5,
				Experience:  500,
				Conditions: []*dnfv1.AdventureBookCondition{
					{
						ConditionId: 1,
						Current:     100,
						Target:      100,
						IsCompleted: true,
					},
				},
				Rewards: []*dnfv1.AdventureBookReward{
					{
						RewardId:    1,
						Type:        1,
						Amount:      100,
						IsClaimed:   false,
					},
				},
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookInfoResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookInfoResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if len(unmarshaled.Books) != len(resp.Books) {
		t.Errorf("Books length mismatch: got %d, want %d", len(unmarshaled.Books), len(resp.Books))
	}
}

// 测试冒险书特殊奖励请求消息
func TestBatch13AdventureBookSpecialRewardRequest(t *testing.T) {
	req := &dnfv1.AdventureBookSpecialRewardRequest{
		CharacterId: 1,
		BookId:      1,
		RewardId:    1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookSpecialRewardRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookSpecialRewardRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookSpecialRewardRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.BookId != req.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, req.BookId)
	}

	if unmarshaled.RewardId != req.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", unmarshaled.RewardId, req.RewardId)
	}
}

// 测试冒险书特殊奖励响应消息
func TestBatch13AdventureBookSpecialRewardResponse(t *testing.T) {
	resp := &dnfv1.AdventureBookSpecialRewardResponse{
		Error:       0,
		CharacterId: 1,
		BookId:      1,
		RewardId:    1,
		IsSuccess:   true,
		Reward: &dnfv1.AdventureBookReward{
			RewardId:  1,
			Type:      1,
			Amount:    100,
			IsClaimed: true,
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookSpecialRewardResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookSpecialRewardResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookSpecialRewardResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.BookId != resp.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, resp.BookId)
	}

	if unmarshaled.RewardId != resp.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", unmarshaled.RewardId, resp.RewardId)
	}

	if unmarshaled.IsSuccess != resp.IsSuccess {
		t.Errorf("IsSuccess mismatch: got %v, want %v", unmarshaled.IsSuccess, resp.IsSuccess)
	}

	if unmarshaled.Reward == nil {
		t.Errorf("Reward should not be nil")
	}
}

// 测试冒险书泰拉奖励请求消息
func TestBatch13AdventureBookTeraRewardRequest(t *testing.T) {
	req := &dnfv1.AdventureBookTeraRewardRequest{
		CharacterId: 1,
		BookId:      1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookTeraRewardRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookTeraRewardRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookTeraRewardRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.BookId != req.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, req.BookId)
	}
}

// 测试冒险书泰拉奖励响应消息
func TestBatch13AdventureBookTeraRewardResponse(t *testing.T) {
	resp := &dnfv1.AdventureBookTeraRewardResponse{
		Error:       0,
		CharacterId: 1,
		BookId:      1,
		IsSuccess:   true,
		Rewards: []*dnfv1.AdventureBookReward{
			{
				RewardId:  1,
				Type:      1,
				Amount:    100,
				IsClaimed: true,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookTeraRewardResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookTeraRewardResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookTeraRewardResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.BookId != resp.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, resp.BookId)
	}

	if unmarshaled.IsSuccess != resp.IsSuccess {
		t.Errorf("IsSuccess mismatch: got %v, want %v", unmarshaled.IsSuccess, resp.IsSuccess)
	}

	if len(unmarshaled.Rewards) != len(resp.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(unmarshaled.Rewards), len(resp.Rewards))
	}
}

// 测试冒险书条件更新请求消息
func TestBatch13AdventureBookUpdateConditionRequest(t *testing.T) {
	req := &dnfv1.AdventureBookUpdateConditionRequest{
		CharacterId: 1,
		BookId:      1,
		ConditionId: 1,
		Value:       100,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookUpdateConditionRequest: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookUpdateConditionRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookUpdateConditionRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.BookId != req.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, req.BookId)
	}

	if unmarshaled.ConditionId != req.ConditionId {
		t.Errorf("ConditionId mismatch: got %d, want %d", unmarshaled.ConditionId, req.ConditionId)
	}

	if unmarshaled.Value != req.Value {
		t.Errorf("Value mismatch: got %d, want %d", unmarshaled.Value, req.Value)
	}
}

// 测试冒险书条件更新响应消息
func TestBatch13AdventureBookUpdateConditionResponse(t *testing.T) {
	resp := &dnfv1.AdventureBookUpdateConditionResponse{
		Error:       0,
		CharacterId: 1,
		BookId:      1,
		ConditionId: 1,
		IsSuccess:   true,
		Condition: &dnfv1.AdventureBookCondition{
			ConditionId: 1,
			Current:     100,
			Target:      100,
			IsCompleted: true,
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookUpdateConditionResponse: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookUpdateConditionResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookUpdateConditionResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.BookId != resp.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, resp.BookId)
	}

	if unmarshaled.ConditionId != resp.ConditionId {
		t.Errorf("ConditionId mismatch: got %d, want %d", unmarshaled.ConditionId, resp.ConditionId)
	}

	if unmarshaled.IsSuccess != resp.IsSuccess {
		t.Errorf("IsSuccess mismatch: got %v, want %v", unmarshaled.IsSuccess, resp.IsSuccess)
	}

	if unmarshaled.Condition == nil {
		t.Errorf("Condition should not be nil")
	}
}

// 测试冒险存储物品
func TestBatch13AdventureStorageItem(t *testing.T) {
	item := &dnfv1.AdventureStorageItem{
		ItemId:      1,
		Count:       10,
		IsBound:     false,
		StorageTime: 1234567890,
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureStorageItem: %v", err)
	}

	unmarshaled := &dnfv1.AdventureStorageItem{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureStorageItem: %v", err)
	}

	if unmarshaled.ItemId != item.ItemId {
		t.Errorf("ItemId mismatch: got %d, want %d", unmarshaled.ItemId, item.ItemId)
	}

	if unmarshaled.Count != item.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, item.Count)
	}

	if unmarshaled.IsBound != item.IsBound {
		t.Errorf("IsBound mismatch: got %v, want %v", unmarshaled.IsBound, item.IsBound)
	}

	if unmarshaled.StorageTime != item.StorageTime {
		t.Errorf("StorageTime mismatch: got %d, want %d", unmarshaled.StorageTime, item.StorageTime)
	}
}

// 测试冒险收获信息
func TestBatch13AdventureReapInfo(t *testing.T) {
	reapInfo := &dnfv1.AdventureReapInfo{
		ReapId:      1,
		Progress:    100,
		Total:       100,
		IsCompleted: true,
		StartTime:   1234567890,
		EndTime:     1234567890,
	}

	data, err := proto.Marshal(reapInfo)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureReapInfo: %v", err)
	}

	unmarshaled := &dnfv1.AdventureReapInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureReapInfo: %v", err)
	}

	if unmarshaled.ReapId != reapInfo.ReapId {
		t.Errorf("ReapId mismatch: got %d, want %d", unmarshaled.ReapId, reapInfo.ReapId)
	}

	if unmarshaled.Progress != reapInfo.Progress {
		t.Errorf("Progress mismatch: got %d, want %d", unmarshaled.Progress, reapInfo.Progress)
	}

	if unmarshaled.Total != reapInfo.Total {
		t.Errorf("Total mismatch: got %d, want %d", unmarshaled.Total, reapInfo.Total)
	}

	if unmarshaled.IsCompleted != reapInfo.IsCompleted {
		t.Errorf("IsCompleted mismatch: got %v, want %v", unmarshaled.IsCompleted, reapInfo.IsCompleted)
	}

	if unmarshaled.StartTime != reapInfo.StartTime {
		t.Errorf("StartTime mismatch: got %d, want %d", unmarshaled.StartTime, reapInfo.StartTime)
	}

	if unmarshaled.EndTime != reapInfo.EndTime {
		t.Errorf("EndTime mismatch: got %d, want %d", unmarshaled.EndTime, reapInfo.EndTime)
	}
}

// 测试冒险收获奖励
func TestBatch13AdventureReapReward(t *testing.T) {
	reward := &dnfv1.AdventureReapReward{
		RewardId:  1,
		Type:      1,
		Amount:    100,
		IsClaimed: false,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureReapReward: %v", err)
	}

	unmarshaled := &dnfv1.AdventureReapReward{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureReapReward: %v", err)
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

	if unmarshaled.IsClaimed != reward.IsClaimed {
		t.Errorf("IsClaimed mismatch: got %v, want %v", unmarshaled.IsClaimed, reward.IsClaimed)
	}
}

// 测试冒险自动搜索
func TestBatch13AdventureAutoSearch(t *testing.T) {
	search := &dnfv1.AdventureAutoSearch{
		SearchId:     1,
		Duration:     60,
		Progress:     0,
		IsCompleted:  false,
		StartTime:    1234567890,
		EndTime:      1234567890,
		Rewards: []*dnfv1.AdventureReapReward{
			{
				RewardId:  1,
				Type:      1,
				Amount:    100,
				IsClaimed: false,
			},
		},
	}

	data, err := proto.Marshal(search)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureAutoSearch: %v", err)
	}

	unmarshaled := &dnfv1.AdventureAutoSearch{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureAutoSearch: %v", err)
	}

	if unmarshaled.SearchId != search.SearchId {
		t.Errorf("SearchId mismatch: got %d, want %d", unmarshaled.SearchId, search.SearchId)
	}

	if unmarshaled.Duration != search.Duration {
		t.Errorf("Duration mismatch: got %d, want %d", unmarshaled.Duration, search.Duration)
	}

	if unmarshaled.Progress != search.Progress {
		t.Errorf("Progress mismatch: got %d, want %d", unmarshaled.Progress, search.Progress)
	}

	if unmarshaled.IsCompleted != search.IsCompleted {
		t.Errorf("IsCompleted mismatch: got %v, want %v", unmarshaled.IsCompleted, search.IsCompleted)
	}

	if unmarshaled.StartTime != search.StartTime {
		t.Errorf("StartTime mismatch: got %d, want %d", unmarshaled.StartTime, search.StartTime)
	}

	if unmarshaled.EndTime != search.EndTime {
		t.Errorf("EndTime mismatch: got %d, want %d", unmarshaled.EndTime, search.EndTime)
	}

	if len(unmarshaled.Rewards) != len(search.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(unmarshaled.Rewards), len(search.Rewards))
	}
}

// 测试冒险书条件
func TestBatch13AdventureBookCondition(t *testing.T) {
	condition := &dnfv1.AdventureBookCondition{
		ConditionId: 1,
		Current:     100,
		Target:      100,
		IsCompleted: true,
	}

	data, err := proto.Marshal(condition)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookCondition: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookCondition{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookCondition: %v", err)
	}

	if unmarshaled.ConditionId != condition.ConditionId {
		t.Errorf("ConditionId mismatch: got %d, want %d", unmarshaled.ConditionId, condition.ConditionId)
	}

	if unmarshaled.Current != condition.Current {
		t.Errorf("Current mismatch: got %d, want %d", unmarshaled.Current, condition.Current)
	}

	if unmarshaled.Target != condition.Target {
		t.Errorf("Target mismatch: got %d, want %d", unmarshaled.Target, condition.Target)
	}

	if unmarshaled.IsCompleted != condition.IsCompleted {
		t.Errorf("IsCompleted mismatch: got %v, want %v", unmarshaled.IsCompleted, condition.IsCompleted)
	}
}

// 测试冒险书奖励
func TestBatch13AdventureBookReward(t *testing.T) {
	reward := &dnfv1.AdventureBookReward{
		RewardId:  1,
		Type:      1,
		Amount:    100,
		IsClaimed: false,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookReward: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookReward{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookReward: %v", err)
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

	if unmarshaled.IsClaimed != reward.IsClaimed {
		t.Errorf("IsClaimed mismatch: got %v, want %v", unmarshaled.IsClaimed, reward.IsClaimed)
	}
}

// 测试冒险书信息
func TestBatch13AdventureBookInfo(t *testing.T) {
	bookInfo := &dnfv1.AdventureBookInfo{
		BookId:     1,
		Name:       "Test Book",
		Level:      5,
		Experience: 500,
		Conditions: []*dnfv1.AdventureBookCondition{
			{
				ConditionId: 1,
				Current:     100,
				Target:      100,
				IsCompleted: true,
			},
		},
		Rewards: []*dnfv1.AdventureBookReward{
			{
				RewardId:  1,
				Type:      1,
				Amount:    100,
				IsClaimed: false,
			},
		},
	}

	data, err := proto.Marshal(bookInfo)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureBookInfo: %v", err)
	}

	unmarshaled := &dnfv1.AdventureBookInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureBookInfo: %v", err)
	}

	if unmarshaled.BookId != bookInfo.BookId {
		t.Errorf("BookId mismatch: got %d, want %d", unmarshaled.BookId, bookInfo.BookId)
	}

	if unmarshaled.Name != bookInfo.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, bookInfo.Name)
	}

	if unmarshaled.Level != bookInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, bookInfo.Level)
	}

	if unmarshaled.Experience != bookInfo.Experience {
		t.Errorf("Experience mismatch: got %d, want %d", unmarshaled.Experience, bookInfo.Experience)
	}

	if len(unmarshaled.Conditions) != len(bookInfo.Conditions) {
		t.Errorf("Conditions length mismatch: got %d, want %d", len(unmarshaled.Conditions), len(bookInfo.Conditions))
	}

	if len(unmarshaled.Rewards) != len(bookInfo.Rewards) {
		t.Errorf("Rewards length mismatch: got %d, want %d", len(unmarshaled.Rewards), len(bookInfo.Rewards))
	}
}

// 测试冒险数据
func TestBatch13AdventureData(t *testing.T) {
	data := &dnfv1.AdventureData{
		AdventureLevel:     10,
		AdventureExp:       1000,
		Energy:             100,
		MaxEnergy:          100,
		LastEnergyRecovery: 1234567890,
	}

	bytes, err := proto.Marshal(data)
	if err != nil {
		t.Fatalf("Failed to marshal AdventureData: %v", err)
	}

	unmarshaled := &dnfv1.AdventureData{}
	if err := proto.Unmarshal(bytes, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AdventureData: %v", err)
	}

	if unmarshaled.AdventureLevel != data.AdventureLevel {
		t.Errorf("AdventureLevel mismatch: got %d, want %d", unmarshaled.AdventureLevel, data.AdventureLevel)
	}

	if unmarshaled.AdventureExp != data.AdventureExp {
		t.Errorf("AdventureExp mismatch: got %d, want %d", unmarshaled.AdventureExp, data.AdventureExp)
	}

	if unmarshaled.Energy != data.Energy {
		t.Errorf("Energy mismatch: got %d, want %d", unmarshaled.Energy, data.Energy)
	}

	if unmarshaled.MaxEnergy != data.MaxEnergy {
		t.Errorf("MaxEnergy mismatch: got %d, want %d", unmarshaled.MaxEnergy, data.MaxEnergy)
	}

	if unmarshaled.LastEnergyRecovery != data.LastEnergyRecovery {
		t.Errorf("LastEnergyRecovery mismatch: got %d, want %d", unmarshaled.LastEnergyRecovery, data.LastEnergyRecovery)
	}
}