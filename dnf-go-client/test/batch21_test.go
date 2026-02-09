package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试城镇聊天请求消息
func TestBatch21TownChatRequest(t *testing.T) {
	req := &dnfv1.TownChatRequest{
		Type:     1,
		Chat:     "Hello world",
		Voice:    "",
		Voicetime: "",
		Name:     "TestUser",
		Guid:     1234567890,
		Job:      1,
		Level:    50,
		Channel:  1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TownChatRequest: %v", err)
	}

	unmarshaled := &dnfv1.TownChatRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TownChatRequest: %v", err)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}

	if unmarshaled.Chat != req.Chat {
		t.Errorf("Chat mismatch: got %s, want %s", unmarshaled.Chat, req.Chat)
	}

	if unmarshaled.Name != req.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, req.Name)
	}

	if unmarshaled.Guid != req.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, req.Guid)
	}
}

// 测试城镇聊天响应消息
func TestBatch21TownChatResponse(t *testing.T) {
	resp := &dnfv1.TownChatResponse{
		Error:   0,
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TownChatResponse: %v", err)
	}

	unmarshaled := &dnfv1.TownChatResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TownChatResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试快速聊天请求消息
func TestBatch21QuickChattingRequest(t *testing.T) {
	req := &dnfv1.QuickChattingRequest{
		Type: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal QuickChattingRequest: %v", err)
	}

	unmarshaled := &dnfv1.QuickChattingRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal QuickChattingRequest: %v", err)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}
}

// 测试快速聊天响应消息
func TestBatch21QuickChattingResponse(t *testing.T) {
	resp := &dnfv1.QuickChattingResponse{
		Error:   0,
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal QuickChattingResponse: %v", err)
	}

	unmarshaled := &dnfv1.QuickChattingResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal QuickChattingResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试加载公会聊天请求消息
func TestBatch21LoadGuildChatRequest(t *testing.T) {
	req := &dnfv1.LoadGuildChatRequest{
		Sequence: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal LoadGuildChatRequest: %v", err)
	}

	unmarshaled := &dnfv1.LoadGuildChatRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal LoadGuildChatRequest: %v", err)
	}

	if unmarshaled.Sequence != req.Sequence {
		t.Errorf("Sequence mismatch: got %d, want %d", unmarshaled.Sequence, req.Sequence)
	}
}

// 测试加载公会聊天响应消息
func TestBatch21LoadGuildChatResponse(t *testing.T) {
	resp := &dnfv1.LoadGuildChatResponse{
		Error:      0,
		TotalCount: 5,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal LoadGuildChatResponse: %v", err)
	}

	unmarshaled := &dnfv1.LoadGuildChatResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal LoadGuildChatResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.TotalCount != resp.TotalCount {
		t.Errorf("TotalCount mismatch: got %d, want %d", unmarshaled.TotalCount, resp.TotalCount)
	}
}

// 测试加载隐藏聊天请求消息
func TestBatch21HiddenChattingLoadRequest(t *testing.T) {
	req := &dnfv1.HiddenChattingLoadRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChattingLoadRequest: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChattingLoadRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChattingLoadRequest: %v", err)
	}
}

// 测试加载隐藏聊天响应消息
func TestBatch21HiddenChattingLoadResponse(t *testing.T) {
	resp := &dnfv1.HiddenChattingLoadResponse{
		Error:      0,
		TotalCount: 3,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChattingLoadResponse: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChattingLoadResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChattingLoadResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.TotalCount != resp.TotalCount {
		t.Errorf("TotalCount mismatch: got %d, want %d", unmarshaled.TotalCount, resp.TotalCount)
	}
}

// 测试添加隐藏聊天请求消息
func TestBatch21HiddenChattingAddRequest(t *testing.T) {
	req := &dnfv1.HiddenChattingAddRequest{
		Chattings: []*dnfv1.HiddenChatting{
			{
				Guid: 1234567890,
				Name: "TestUser1",
				Type: 1,
			},
			{
				Guid: 1234567891,
				Name: "TestUser2",
				Type: 2,
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChattingAddRequest: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChattingAddRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChattingAddRequest: %v", err)
	}

	if len(unmarshaled.Chattings) != len(req.Chattings) {
		t.Errorf("Chattings length mismatch: got %d, want %d", len(unmarshaled.Chattings), len(req.Chattings))
	}
}

// 测试添加隐藏聊天响应消息
func TestBatch21HiddenChattingAddResponse(t *testing.T) {
	resp := &dnfv1.HiddenChattingAddResponse{
		Error:   0,
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChattingAddResponse: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChattingAddResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChattingAddResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试删除隐藏聊天请求消息
func TestBatch21HiddenChattingDeleteRequest(t *testing.T) {
	req := &dnfv1.HiddenChattingDeleteRequest{
		Chattings: []*dnfv1.HiddenChatting{
			{
				Guid: 1234567890,
				Name: "TestUser1",
				Type: 1,
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChattingDeleteRequest: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChattingDeleteRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChattingDeleteRequest: %v", err)
	}

	if len(unmarshaled.Chattings) != len(req.Chattings) {
		t.Errorf("Chattings length mismatch: got %d, want %d", len(unmarshaled.Chattings), len(req.Chattings))
	}
}

// 测试删除隐藏聊天响应消息
func TestBatch21HiddenChattingDeleteResponse(t *testing.T) {
	resp := &dnfv1.HiddenChattingDeleteResponse{
		Error:   0,
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChattingDeleteResponse: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChattingDeleteResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChattingDeleteResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试聊天频道列表请求消息
func TestBatch21ChatChannelListRequest(t *testing.T) {
	req := &dnfv1.ChatChannelListRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ChatChannelListRequest: %v", err)
	}

	unmarshaled := &dnfv1.ChatChannelListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ChatChannelListRequest: %v", err)
	}
}

// 测试聊天频道列表响应消息
func TestBatch21ChatChannelListResponse(t *testing.T) {
	resp := &dnfv1.ChatChannelListResponse{
		Error:      0,
		TotalCount: 10,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ChatChannelListResponse: %v", err)
	}

	unmarshaled := &dnfv1.ChatChannelListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ChatChannelListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.TotalCount != resp.TotalCount {
		t.Errorf("TotalCount mismatch: got %d, want %d", unmarshaled.TotalCount, resp.TotalCount)
	}
}

// 测试聊天框架标签列表请求消息
func TestBatch21ChatFrameTabListRequest(t *testing.T) {
	req := &dnfv1.ChatFrameTabListRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal ChatFrameTabListRequest: %v", err)
	}

	unmarshaled := &dnfv1.ChatFrameTabListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ChatFrameTabListRequest: %v", err)
	}
}

// 测试聊天框架标签列表响应消息
func TestBatch21ChatFrameTabListResponse(t *testing.T) {
	resp := &dnfv1.ChatFrameTabListResponse{
		Error: 0,
		Tabs:  []string{"All", "Guild", "Private", "System"},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal ChatFrameTabListResponse: %v", err)
	}

	unmarshaled := &dnfv1.ChatFrameTabListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ChatFrameTabListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if len(unmarshaled.Tabs) != len(resp.Tabs) {
		t.Errorf("Tabs length mismatch: got %d, want %d", len(unmarshaled.Tabs), len(resp.Tabs))
	}
}

// 测试隐藏聊天
func TestBatch21HiddenChatting(t *testing.T) {
	chatting := &dnfv1.HiddenChatting{
		Guid: 1234567890,
		Name: "TestUser",
		Type: 1,
	}

	data, err := proto.Marshal(chatting)
	if err != nil {
		t.Fatalf("Failed to marshal HiddenChatting: %v", err)
	}

	unmarshaled := &dnfv1.HiddenChatting{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HiddenChatting: %v", err)
	}

	if unmarshaled.Guid != chatting.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, chatting.Guid)
	}

	if unmarshaled.Name != chatting.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, chatting.Name)
	}

	if unmarshaled.Type != chatting.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, chatting.Type)
	}
}

// 测试聊天频道
func TestBatch21ChatChannel(t *testing.T) {
	channel := &dnfv1.ChatChannel{
		ChannelId:    1,
		Name:          "General",
		Type:          1,
		MaxUsers:      100,
		CurrentUsers:  50,
	}

	data, err := proto.Marshal(channel)
	if err != nil {
		t.Fatalf("Failed to marshal ChatChannel: %v", err)
	}

	unmarshaled := &dnfv1.ChatChannel{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ChatChannel: %v", err)
	}

	if unmarshaled.ChannelId != channel.ChannelId {
		t.Errorf("ChannelId mismatch: got %d, want %d", unmarshaled.ChannelId, channel.ChannelId)
	}

	if unmarshaled.Name != channel.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, channel.Name)
	}

	if unmarshaled.Type != channel.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, channel.Type)
	}

	if unmarshaled.MaxUsers != channel.MaxUsers {
		t.Errorf("MaxUsers mismatch: got %d, want %d", unmarshaled.MaxUsers, channel.MaxUsers)
	}

	if unmarshaled.CurrentUsers != channel.CurrentUsers {
		t.Errorf("CurrentUsers mismatch: got %d, want %d", unmarshaled.CurrentUsers, channel.CurrentUsers)
	}
}

// 测试公会聊天
func TestBatch21GuildChat(t *testing.T) {
	chat := &dnfv1.GuildChat{
		Guid:      1234567890,
		Name:       "TestUser",
		Content:    "Hello guild!",
		Type:       1,
		Timestamp:  1234567890,
		Job:        1,
		Level:      50,
	}

	data, err := proto.Marshal(chat)
	if err != nil {
		t.Fatalf("Failed to marshal GuildChat: %v", err)
	}

	unmarshaled := &dnfv1.GuildChat{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildChat: %v", err)
	}

	if unmarshaled.Guid != chat.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, chat.Guid)
	}

	if unmarshaled.Name != chat.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, chat.Name)
	}

	if unmarshaled.Content != chat.Content {
		t.Errorf("Content mismatch: got %s, want %s", unmarshaled.Content, chat.Content)
	}

	if unmarshaled.Type != chat.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, chat.Type)
	}

	if unmarshaled.Timestamp != chat.Timestamp {
		t.Errorf("Timestamp mismatch: got %d, want %d", unmarshaled.Timestamp, chat.Timestamp)
	}

	if unmarshaled.Job != chat.Job {
		t.Errorf("Job mismatch: got %d, want %d", unmarshaled.Job, chat.Job)
	}

	if unmarshaled.Level != chat.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, chat.Level)
	}
}
