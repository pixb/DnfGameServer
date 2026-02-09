package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试好友请求列表请求消息
func TestBatch19FriendRequestListRequest(t *testing.T) {
	req := &dnfv1.FriendRequestListRequest{
		CharacterId: 1,
		Page:        1,
		PageSize:    20,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendRequestListRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendRequestListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendRequestListRequest: %v", err)
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

// 测试好友请求列表响应消息
func TestBatch19FriendRequestListResponse(t *testing.T) {
	resp := &dnfv1.FriendRequestListResponse{
		Error:       0,
		CharacterId: 1,
		Requests: []*dnfv1.FriendRequestInfo{
			{
				RequestId:         1,
				FromCharacterId:   2,
				FromCharacterName: "Test Sender",
				Level:             10,
				Job:               1,
				VerifyMessage:     "Hello, let's be friends!",
				RequestTime:       1234567890,
			},
		},
		TotalCount: 3,
		Page:       1,
		PageSize:   20,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendRequestListResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendRequestListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendRequestListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if len(unmarshaled.Requests) != len(resp.Requests) {
		t.Errorf("Requests length mismatch: got %d, want %d", len(unmarshaled.Requests), len(resp.Requests))
	}

	if unmarshaled.TotalCount != resp.TotalCount {
		t.Errorf("TotalCount mismatch: got %d, want %d", unmarshaled.TotalCount, resp.TotalCount)
	}

	if unmarshaled.Page != resp.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, resp.Page)
	}

	if unmarshaled.PageSize != resp.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, resp.PageSize)
	}
}

// 测试好友请求接受请求消息
func TestBatch19FriendRequestAcceptRequest(t *testing.T) {
	req := &dnfv1.FriendRequestAcceptRequest{
		CharacterId:     1,
		RequestId:       1,
		FromCharacterId: 2,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendRequestAcceptRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendRequestAcceptRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendRequestAcceptRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.RequestId != req.RequestId {
		t.Errorf("RequestId mismatch: got %d, want %d", unmarshaled.RequestId, req.RequestId)
	}

	if unmarshaled.FromCharacterId != req.FromCharacterId {
		t.Errorf("FromCharacterId mismatch: got %d, want %d", unmarshaled.FromCharacterId, req.FromCharacterId)
	}
}

// 测试好友请求接受响应消息
func TestBatch19FriendRequestAcceptResponse(t *testing.T) {
	resp := &dnfv1.FriendRequestAcceptResponse{
		Error:             0,
		CharacterId:       1,
		RequestId:         1,
		FriendCharacterId: 2,
		Success:           true,
		Message:           "Friend request accepted successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendRequestAcceptResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendRequestAcceptResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendRequestAcceptResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.RequestId != resp.RequestId {
		t.Errorf("RequestId mismatch: got %d, want %d", unmarshaled.RequestId, resp.RequestId)
	}

	if unmarshaled.FriendCharacterId != resp.FriendCharacterId {
		t.Errorf("FriendCharacterId mismatch: got %d, want %d", unmarshaled.FriendCharacterId, resp.FriendCharacterId)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}

	if unmarshaled.Message != resp.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, resp.Message)
	}
}

// 测试好友请求拒绝请求消息
func TestBatch19FriendRequestRejectRequest(t *testing.T) {
	req := &dnfv1.FriendRequestRejectRequest{
		CharacterId:     1,
		RequestId:       1,
		FromCharacterId: 2,
		Reason:          "Not interested",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendRequestRejectRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendRequestRejectRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendRequestRejectRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.RequestId != req.RequestId {
		t.Errorf("RequestId mismatch: got %d, want %d", unmarshaled.RequestId, req.RequestId)
	}

	if unmarshaled.FromCharacterId != req.FromCharacterId {
		t.Errorf("FromCharacterId mismatch: got %d, want %d", unmarshaled.FromCharacterId, req.FromCharacterId)
	}

	if unmarshaled.Reason != req.Reason {
		t.Errorf("Reason mismatch: got %s, want %s", unmarshaled.Reason, req.Reason)
	}
}

// 测试好友请求拒绝响应消息
func TestBatch19FriendRequestRejectResponse(t *testing.T) {
	resp := &dnfv1.FriendRequestRejectResponse{
		Error:           0,
		CharacterId:     1,
		RequestId:       1,
		FromCharacterId: 2,
		Success:         true,
		Message:         "Friend request rejected successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendRequestRejectResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendRequestRejectResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendRequestRejectResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.RequestId != resp.RequestId {
		t.Errorf("RequestId mismatch: got %d, want %d", unmarshaled.RequestId, resp.RequestId)
	}

	if unmarshaled.FromCharacterId != resp.FromCharacterId {
		t.Errorf("FromCharacterId mismatch: got %d, want %d", unmarshaled.FromCharacterId, resp.FromCharacterId)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}

	if unmarshaled.Message != resp.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, resp.Message)
	}
}

// 测试好友黑名单请求消息
func TestBatch19FriendBlacklistRequest(t *testing.T) {
	req := &dnfv1.FriendBlacklistRequest{
		CharacterId:       1,
		Operation:         1,
		TargetCharacterId: 2,
		Page:              1,
		PageSize:          20,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendBlacklistRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendBlacklistRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendBlacklistRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.Operation != req.Operation {
		t.Errorf("Operation mismatch: got %d, want %d", unmarshaled.Operation, req.Operation)
	}

	if unmarshaled.TargetCharacterId != req.TargetCharacterId {
		t.Errorf("TargetCharacterId mismatch: got %d, want %d", unmarshaled.TargetCharacterId, req.TargetCharacterId)
	}

	if unmarshaled.Page != req.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, req.Page)
	}

	if unmarshaled.PageSize != req.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, req.PageSize)
	}
}

// 测试好友黑名单响应消息
func TestBatch19FriendBlacklistResponse(t *testing.T) {
	resp := &dnfv1.FriendBlacklistResponse{
		Error:       0,
		CharacterId: 1,
		Operation:   1,
		Success:     true,
		Message:     "Blacklist operation completed successfully",
		Blacklist: []*dnfv1.BlacklistInfo{
			{
				CharacterId:   2,
				CharacterName: "Test Blacklist",
				Level:         10,
				Job:           1,
				AddTime:       1234567890,
				Reason:        "Annoying",
			},
		},
		TotalCount: 5,
		Page:       1,
		PageSize:   20,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendBlacklistResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendBlacklistResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendBlacklistResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.Operation != resp.Operation {
		t.Errorf("Operation mismatch: got %d, want %d", unmarshaled.Operation, resp.Operation)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}

	if unmarshaled.Message != resp.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, resp.Message)
	}

	if len(unmarshaled.Blacklist) != len(resp.Blacklist) {
		t.Errorf("Blacklist length mismatch: got %d, want %d", len(unmarshaled.Blacklist), len(resp.Blacklist))
	}

	if unmarshaled.TotalCount != resp.TotalCount {
		t.Errorf("TotalCount mismatch: got %d, want %d", unmarshaled.TotalCount, resp.TotalCount)
	}

	if unmarshaled.Page != resp.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, resp.Page)
	}

	if unmarshaled.PageSize != resp.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, resp.PageSize)
	}
}

// 测试好友请求信息
func TestBatch19FriendRequestInfo(t *testing.T) {
	requestInfo := &dnfv1.FriendRequestInfo{
		RequestId:         1,
		FromCharacterId:   2,
		FromCharacterName: "Test Sender",
		Level:             10,
		Job:               1,
		VerifyMessage:     "Hello, let's be friends!",
		RequestTime:       1234567890,
	}

	data, err := proto.Marshal(requestInfo)
	if err != nil {
		t.Fatalf("Failed to marshal FriendRequestInfo: %v", err)
	}

	unmarshaled := &dnfv1.FriendRequestInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendRequestInfo: %v", err)
	}

	if unmarshaled.RequestId != requestInfo.RequestId {
		t.Errorf("RequestId mismatch: got %d, want %d", unmarshaled.RequestId, requestInfo.RequestId)
	}

	if unmarshaled.FromCharacterId != requestInfo.FromCharacterId {
		t.Errorf("FromCharacterId mismatch: got %d, want %d", unmarshaled.FromCharacterId, requestInfo.FromCharacterId)
	}

	if unmarshaled.FromCharacterName != requestInfo.FromCharacterName {
		t.Errorf("FromCharacterName mismatch: got %s, want %s", unmarshaled.FromCharacterName, requestInfo.FromCharacterName)
	}

	if unmarshaled.Level != requestInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, requestInfo.Level)
	}

	if unmarshaled.Job != requestInfo.Job {
		t.Errorf("Job mismatch: got %d, want %d", unmarshaled.Job, requestInfo.Job)
	}

	if unmarshaled.VerifyMessage != requestInfo.VerifyMessage {
		t.Errorf("VerifyMessage mismatch: got %s, want %s", unmarshaled.VerifyMessage, requestInfo.VerifyMessage)
	}

	if unmarshaled.RequestTime != requestInfo.RequestTime {
		t.Errorf("RequestTime mismatch: got %d, want %d", unmarshaled.RequestTime, requestInfo.RequestTime)
	}
}

// 测试黑名单信息
func TestBatch19BlacklistInfo(t *testing.T) {
	blacklistInfo := &dnfv1.BlacklistInfo{
		CharacterId:   2,
		CharacterName: "Test Blacklist",
		Level:         10,
		Job:           1,
		AddTime:       1234567890,
		Reason:        "Annoying",
	}

	data, err := proto.Marshal(blacklistInfo)
	if err != nil {
		t.Fatalf("Failed to marshal BlacklistInfo: %v", err)
	}

	unmarshaled := &dnfv1.BlacklistInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal BlacklistInfo: %v", err)
	}

	if unmarshaled.CharacterId != blacklistInfo.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, blacklistInfo.CharacterId)
	}

	if unmarshaled.CharacterName != blacklistInfo.CharacterName {
		t.Errorf("CharacterName mismatch: got %s, want %s", unmarshaled.CharacterName, blacklistInfo.CharacterName)
	}

	if unmarshaled.Level != blacklistInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, blacklistInfo.Level)
	}

	if unmarshaled.Job != blacklistInfo.Job {
		t.Errorf("Job mismatch: got %d, want %d", unmarshaled.Job, blacklistInfo.Job)
	}

	if unmarshaled.AddTime != blacklistInfo.AddTime {
		t.Errorf("AddTime mismatch: got %d, want %d", unmarshaled.AddTime, blacklistInfo.AddTime)
	}

	if unmarshaled.Reason != blacklistInfo.Reason {
		t.Errorf("Reason mismatch: got %s, want %s", unmarshaled.Reason, blacklistInfo.Reason)
	}
}
