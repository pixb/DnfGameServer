package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试好友列表请求消息
func TestBatch18FriendListRequest(t *testing.T) {
	req := &dnfv1.FriendListRequest{
		CharacterId: 1,
		Page:        1,
		PageSize:    20,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendListRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendListRequest: %v", err)
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

// 测试好友列表响应消息
func TestBatch18FriendListResponse(t *testing.T) {
	resp := &dnfv1.FriendListResponse{
		Error:       0,
		CharacterId: 1,
		Friends: []*dnfv1.FriendInfo{
			{
				CharacterId:    2,
				CharacterName:  "Test Friend",
				Level:          10,
				Job:            1,
				OnlineStatus:   1,
				LastLoginTime:  1234567890,
				ServerName:     "Test Server",
			},
		},
		TotalCount: 5,
		Page:       1,
		PageSize:   20,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendListResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if len(unmarshaled.Friends) != len(resp.Friends) {
		t.Errorf("Friends length mismatch: got %d, want %d", len(unmarshaled.Friends), len(resp.Friends))
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

// 测试好友添加请求消息
func TestBatch18FriendAddRequest(t *testing.T) {
	req := &dnfv1.FriendAddRequest{
		CharacterId:      1,
		FriendCharacterId: 2,
		FriendName:       "Test Friend",
		VerifyMessage:    "Hello, let's be friends!",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendAddRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendAddRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendAddRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.FriendCharacterId != req.FriendCharacterId {
		t.Errorf("FriendCharacterId mismatch: got %d, want %d", unmarshaled.FriendCharacterId, req.FriendCharacterId)
	}

	if unmarshaled.FriendName != req.FriendName {
		t.Errorf("FriendName mismatch: got %s, want %s", unmarshaled.FriendName, req.FriendName)
	}

	if unmarshaled.VerifyMessage != req.VerifyMessage {
		t.Errorf("VerifyMessage mismatch: got %s, want %s", unmarshaled.VerifyMessage, req.VerifyMessage)
	}
}

// 测试好友添加响应消息
func TestBatch18FriendAddResponse(t *testing.T) {
	resp := &dnfv1.FriendAddResponse{
		Error:            0,
		CharacterId:      1,
		FriendCharacterId: 2,
		Success:          true,
		Message:          "Friend added successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendAddResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendAddResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendAddResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
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

// 测试好友删除请求消息
func TestBatch18FriendDeleteRequest(t *testing.T) {
	req := &dnfv1.FriendDeleteRequest{
		CharacterId:      1,
		FriendCharacterId: 2,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendDeleteRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendDeleteRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendDeleteRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.FriendCharacterId != req.FriendCharacterId {
		t.Errorf("FriendCharacterId mismatch: got %d, want %d", unmarshaled.FriendCharacterId, req.FriendCharacterId)
	}
}

// 测试好友删除响应消息
func TestBatch18FriendDeleteResponse(t *testing.T) {
	resp := &dnfv1.FriendDeleteResponse{
		Error:            0,
		CharacterId:      1,
		FriendCharacterId: 2,
		Success:          true,
		Message:          "Friend deleted successfully",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendDeleteResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendDeleteResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendDeleteResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
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

// 测试好友消息请求消息
func TestBatch18FriendMessageRequest(t *testing.T) {
	req := &dnfv1.FriendMessageRequest{
		CharacterId:      1,
		FriendCharacterId: 2,
		Message:          "Hello, how are you?",
		SendTime:         1234567890,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal FriendMessageRequest: %v", err)
	}

	unmarshaled := &dnfv1.FriendMessageRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendMessageRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.FriendCharacterId != req.FriendCharacterId {
		t.Errorf("FriendCharacterId mismatch: got %d, want %d", unmarshaled.FriendCharacterId, req.FriendCharacterId)
	}

	if unmarshaled.Message != req.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, req.Message)
	}

	if unmarshaled.SendTime != req.SendTime {
		t.Errorf("SendTime mismatch: got %d, want %d", unmarshaled.SendTime, req.SendTime)
	}
}

// 测试好友消息响应消息
func TestBatch18FriendMessageResponse(t *testing.T) {
	resp := &dnfv1.FriendMessageResponse{
		Error:            0,
		CharacterId:      1,
		FriendCharacterId: 2,
		Success:          true,
		Message:          "Message sent successfully",
		SendTime:         1234567890,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal FriendMessageResponse: %v", err)
	}

	unmarshaled := &dnfv1.FriendMessageResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendMessageResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
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

	if unmarshaled.SendTime != resp.SendTime {
		t.Errorf("SendTime mismatch: got %d, want %d", unmarshaled.SendTime, resp.SendTime)
	}
}

// 测试好友消息通知消息
func TestBatch18FriendMessageNotify(t *testing.T) {
	notify := &dnfv1.FriendMessageNotify{
		FromCharacterId:   1,
		FromCharacterName: "Test Sender",
		ToCharacterId:     2,
		Message:           "Hello, how are you?",
		SendTime:          1234567890,
	}

	data, err := proto.Marshal(notify)
	if err != nil {
		t.Fatalf("Failed to marshal FriendMessageNotify: %v", err)
	}

	unmarshaled := &dnfv1.FriendMessageNotify{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendMessageNotify: %v", err)
	}

	if unmarshaled.FromCharacterId != notify.FromCharacterId {
		t.Errorf("FromCharacterId mismatch: got %d, want %d", unmarshaled.FromCharacterId, notify.FromCharacterId)
	}

	if unmarshaled.FromCharacterName != notify.FromCharacterName {
		t.Errorf("FromCharacterName mismatch: got %s, want %s", unmarshaled.FromCharacterName, notify.FromCharacterName)
	}

	if unmarshaled.ToCharacterId != notify.ToCharacterId {
		t.Errorf("ToCharacterId mismatch: got %d, want %d", unmarshaled.ToCharacterId, notify.ToCharacterId)
	}

	if unmarshaled.Message != notify.Message {
		t.Errorf("Message mismatch: got %s, want %s", unmarshaled.Message, notify.Message)
	}

	if unmarshaled.SendTime != notify.SendTime {
		t.Errorf("SendTime mismatch: got %d, want %d", unmarshaled.SendTime, notify.SendTime)
	}
}

// 测试好友信息
func TestBatch18FriendInfo(t *testing.T) {
	friendInfo := &dnfv1.FriendInfo{
		CharacterId:    2,
		CharacterName:  "Test Friend",
		Level:          10,
		Job:            1,
		OnlineStatus:   1,
		LastLoginTime:  1234567890,
		ServerName:     "Test Server",
	}

	data, err := proto.Marshal(friendInfo)
	if err != nil {
		t.Fatalf("Failed to marshal FriendInfo: %v", err)
	}

	unmarshaled := &dnfv1.FriendInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal FriendInfo: %v", err)
	}

	if unmarshaled.CharacterId != friendInfo.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, friendInfo.CharacterId)
	}

	if unmarshaled.CharacterName != friendInfo.CharacterName {
		t.Errorf("CharacterName mismatch: got %s, want %s", unmarshaled.CharacterName, friendInfo.CharacterName)
	}

	if unmarshaled.Level != friendInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, friendInfo.Level)
	}

	if unmarshaled.Job != friendInfo.Job {
		t.Errorf("Job mismatch: got %d, want %d", unmarshaled.Job, friendInfo.Job)
	}

	if unmarshaled.OnlineStatus != friendInfo.OnlineStatus {
		t.Errorf("OnlineStatus mismatch: got %d, want %d", unmarshaled.OnlineStatus, friendInfo.OnlineStatus)
	}

	if unmarshaled.LastLoginTime != friendInfo.LastLoginTime {
		t.Errorf("LastLoginTime mismatch: got %d, want %d", unmarshaled.LastLoginTime, friendInfo.LastLoginTime)
	}

	if unmarshaled.ServerName != friendInfo.ServerName {
		t.Errorf("ServerName mismatch: got %s, want %s", unmarshaled.ServerName, friendInfo.ServerName)
	}
}
