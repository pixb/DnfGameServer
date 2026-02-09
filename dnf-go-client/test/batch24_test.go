package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试认证信息请求消息
func TestBatch24AuthInfoRequest(t *testing.T) {
	authInfoRequest := &dnfv1.AuthInfoRequest{
		AuthIndex: 123456789,
	}

	data, err := proto.Marshal(authInfoRequest)
	if err != nil {
		t.Fatalf("Failed to marshal AuthInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.AuthInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AuthInfoRequest: %v", err)
	}

	if unmarshaled.AuthIndex != authInfoRequest.AuthIndex {
		t.Errorf("AuthIndex mismatch: got %d, want %d", unmarshaled.AuthIndex, authInfoRequest.AuthIndex)
	}
}

// 测试认证信息响应消息
func TestBatch24AuthInfoResponse(t *testing.T) {
	authInfoResponse := &dnfv1.AuthInfoResponse{
		Error: 0,
	}

	data, err := proto.Marshal(authInfoResponse)
	if err != nil {
		t.Fatalf("Failed to marshal AuthInfoResponse: %v", err)
	}

	unmarshaled := &dnfv1.AuthInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AuthInfoResponse: %v", err)
	}

	if unmarshaled.Error != authInfoResponse.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, authInfoResponse.Error)
	}
}

// 测试基础角色信息消息
func TestBatch24BasicCharacterInfo(t *testing.T) {
	basicCharacterInfo := &dnfv1.BasicCharacterInfo{
		MGuid: "123456789",
		MName: "TestCharacter",
	}

	data, err := proto.Marshal(basicCharacterInfo)
	if err != nil {
		t.Fatalf("Failed to marshal BasicCharacterInfo: %v", err)
	}

	unmarshaled := &dnfv1.BasicCharacterInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal BasicCharacterInfo: %v", err)
	}

	if unmarshaled.MGuid != basicCharacterInfo.MGuid {
		t.Errorf("MGuid mismatch: got %s, want %s", unmarshaled.MGuid, basicCharacterInfo.MGuid)
	}

	if unmarshaled.MName != basicCharacterInfo.MName {
		t.Errorf("MName mismatch: got %s, want %s", unmarshaled.MName, basicCharacterInfo.MName)
	}
}

// 测试基础聊天信息消息
func TestBatch24BaseChat(t *testing.T) {
	baseChat := &dnfv1.BaseChat{
		Type:           1,
		Charguid:       123456789,
		Level:          50,
		Name:           "TestUser",
		Job:            1,
		Growtype:       1,
		Secgrowtype:    0,
		Chat:           "Hello world",
		Creditscore:    1000,
		Characterframe: 1,
		Chatframe:      2,
	}

	data, err := proto.Marshal(baseChat)
	if err != nil {
		t.Fatalf("Failed to marshal BaseChat: %v", err)
	}

	unmarshaled := &dnfv1.BaseChat{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal BaseChat: %v", err)
	}

	if unmarshaled.Type != baseChat.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, baseChat.Type)
	}

	if unmarshaled.Charguid != baseChat.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", unmarshaled.Charguid, baseChat.Charguid)
	}

	if unmarshaled.Name != baseChat.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, baseChat.Name)
	}

	if unmarshaled.Chat != baseChat.Chat {
		t.Errorf("Chat mismatch: got %s, want %s", unmarshaled.Chat, baseChat.Chat)
	}
}
