package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试角色信息消息
func TestBatch25Actor(t *testing.T) {
	actor := &dnfv1.Actor{
		Accountkey: "test_account_key",
		Cera:       1000,
		Charguid:   "123456789",
		Name:       "TestActor",
		Openid:     "test_openid",
		Sessionid:  12345,
		Userip:     "192.168.1.1",
		Version:    "1.0.0",
		Worldid:    1,
		Platid:     1,
	}

	data, err := proto.Marshal(actor)
	if err != nil {
		t.Fatalf("Failed to marshal Actor: %v", err)
	}

	unmarshaled := &dnfv1.Actor{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal Actor: %v", err)
	}

	if unmarshaled.Accountkey != actor.Accountkey {
		t.Errorf("Accountkey mismatch: got %s, want %s", unmarshaled.Accountkey, actor.Accountkey)
	}

	if unmarshaled.Name != actor.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, actor.Name)
	}

	if unmarshaled.Cera != actor.Cera {
		t.Errorf("Cera mismatch: got %d, want %d", unmarshaled.Cera, actor.Cera)
	}
}

// 测试超链接数据消息
func TestBatch25HyperlinkData(t *testing.T) {
	hyperlinkData := &dnfv1.HyperlinkData{
		Index: 123456789,
		Bind:  true,
	}

	data, err := proto.Marshal(hyperlinkData)
	if err != nil {
		t.Fatalf("Failed to marshal HyperlinkData: %v", err)
	}

	unmarshaled := &dnfv1.HyperlinkData{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HyperlinkData: %v", err)
	}

	if unmarshaled.Index != hyperlinkData.Index {
		t.Errorf("Index mismatch: got %d, want %d", unmarshaled.Index, hyperlinkData.Index)
	}

	if unmarshaled.Bind != hyperlinkData.Bind {
		t.Errorf("Bind mismatch: got %v, want %v", unmarshaled.Bind, hyperlinkData.Bind)
	}
}

// 测试超链接系统消息子消息
func TestBatch25HyperlinkSystemMessageSub(t *testing.T) {
	hyperlinkSystemMessageSub := &dnfv1.HyperlinkSystemMessageSub{
		Msg: "Test system message",
	}

	data, err := proto.Marshal(hyperlinkSystemMessageSub)
	if err != nil {
		t.Fatalf("Failed to marshal HyperlinkSystemMessageSub: %v", err)
	}

	unmarshaled := &dnfv1.HyperlinkSystemMessageSub{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal HyperlinkSystemMessageSub: %v", err)
	}

	if unmarshaled.Msg != hyperlinkSystemMessageSub.Msg {
		t.Errorf("Msg mismatch: got %s, want %s", unmarshaled.Msg, hyperlinkSystemMessageSub.Msg)
	}
}

// 测试聊天基础信息消息
func TestBatch25ChatBase(t *testing.T) {
	chatBase := &dnfv1.ChatBase{
		MType:            1,
		MMsg:             "Hello world",
		MVoice:           "voice_data",
		MVoidcetime:      "2026-02-09",
		Hyperlinktype:    1,
		Hyperlinksubtype: 2,
		Hyperlinkdatas: []*dnfv1.HyperlinkData{
			{
				Index: 123456789,
				Bind:  true,
			},
		},
		Sub: []*dnfv1.HyperlinkSystemMessageSub{
			{
				Msg: "Test sub message",
			},
		},
	}

	data, err := proto.Marshal(chatBase)
	if err != nil {
		t.Fatalf("Failed to marshal ChatBase: %v", err)
	}

	unmarshaled := &dnfv1.ChatBase{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal ChatBase: %v", err)
	}

	if unmarshaled.MType != chatBase.MType {
		t.Errorf("MType mismatch: got %d, want %d", unmarshaled.MType, chatBase.MType)
	}

	if unmarshaled.MMsg != chatBase.MMsg {
		t.Errorf("MMsg mismatch: got %s, want %s", unmarshaled.MMsg, chatBase.MMsg)
	}

	if len(unmarshaled.Hyperlinkdatas) != len(chatBase.Hyperlinkdatas) {
		t.Errorf("Hyperlinkdatas length mismatch: got %d, want %d", len(unmarshaled.Hyperlinkdatas), len(chatBase.Hyperlinkdatas))
	}

	if len(unmarshaled.Sub) != len(chatBase.Sub) {
		t.Errorf("Sub length mismatch: got %d, want %d", len(unmarshaled.Sub), len(chatBase.Sub))
	}
}
