package network

import (
	"bytes"
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestProtoCodec_RegisterAndDecode(t *testing.T) {
	// 创建编解码器
	codec := NewProtoCodec()

	// 注册消息类型
	codec.RegisterMessage(10000, 0, func() proto.Message {
		return &dnfv1.LoginRequest{}
	})

	// 创建测试消息
	req := &dnfv1.LoginRequest{
		Openid:   "test_openid_123",
		Type:     1,
		Token:    "test_token",
		PlatId:   1,
		Version:  "1.0.0",
		ClientIp: "127.0.0.1",
	}

	// 编码消息
	data, err := codec.EncodeWithMeta(10000, 0, req)
	if err != nil {
		t.Fatalf("failed to encode message: %v", err)
	}

	// 解码消息
	reader := bytes.NewReader(data)
	decoded, err := codec.Decode(reader)
	if err != nil {
		t.Fatalf("failed to decode message: %v", err)
	}

	// 验证解码结果
	packet, ok := decoded.(*ProtocolPacket)
	if !ok {
		t.Fatal("decoded message is not a ProtocolPacket")
	}

	if packet.Meta.Module != 10000 {
		t.Errorf("expected module 10000, got %d", packet.Meta.Module)
	}

	if packet.Meta.Cmd != 0 {
		t.Errorf("expected cmd 0, got %d", packet.Meta.Cmd)
	}

	decodedReq, ok := packet.Message.(*dnfv1.LoginRequest)
	if !ok {
		t.Fatal("decoded message is not a LoginRequest")
	}

	if decodedReq.Openid != req.Openid {
		t.Errorf("expected openid %s, got %s", req.Openid, decodedReq.Openid)
	}

	if decodedReq.Token != req.Token {
		t.Errorf("expected token %s, got %s", req.Token, decodedReq.Token)
	}
}

func TestProtoCodec_RegisterAllMessages(t *testing.T) {
	codec := NewProtoCodec()
	codec.RegisterAllMessages()

	// 验证一些关键消息是否已注册
	tests := []struct {
		module uint16
		cmd    uint16
	}{
		{10000, 0}, // LoginRequest
		{10000, 1}, // LoginResponse
		{10001, 0}, // GetRoleInfoRequest
		{10002, 0}, // GetBagRequest
		{10003, 0}, // EnterDungeonRequest
	}

	for _, tt := range tests {
		key := MessageMeta{Module: tt.module, Cmd: tt.cmd}.MessageKey()
		factory, ok := codec.msgRegistry[key]
		if !ok {
			t.Errorf("message not registered: module=%d, cmd=%d", tt.module, tt.cmd)
			continue
		}

		// 验证工厂函数能创建消息
		msg := factory()
		if msg == nil {
			t.Errorf("factory returned nil for: module=%d, cmd=%d", tt.module, tt.cmd)
		}
	}
}

func TestMessageMeta_MessageKey(t *testing.T) {
	meta := MessageMeta{
		Module: 10000,
		Cmd:    1,
	}

	key := meta.MessageKey()
	expected := (uint32(10000) << 16) | uint32(1)

	if key != expected {
		t.Errorf("expected key %d, got %d", expected, key)
	}
}

func BenchmarkProtoCodec_Encode(b *testing.B) {
	codec := NewProtoCodec()
	codec.RegisterMessage(10000, 0, func() proto.Message {
		return &dnfv1.LoginRequest{}
	})

	req := &dnfv1.LoginRequest{
		Openid:   "benchmark_openid",
		Type:     1,
		Token:    "benchmark_token",
		PlatId:   1,
		Version:  "1.0.0",
		ClientIp: "127.0.0.1",
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		_, err := codec.EncodeWithMeta(10000, 0, req)
		if err != nil {
			b.Fatal(err)
		}
	}
}

func BenchmarkProtoCodec_Decode(b *testing.B) {
	codec := NewProtoCodec()
	codec.RegisterMessage(10000, 0, func() proto.Message {
		return &dnfv1.LoginRequest{}
	})

	req := &dnfv1.LoginRequest{
		Openid:   "benchmark_openid",
		Type:     1,
		Token:    "benchmark_token",
		PlatId:   1,
		Version:  "1.0.0",
		ClientIp: "127.0.0.1",
	}

	data, _ := codec.EncodeWithMeta(10000, 0, req)

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		reader := bytes.NewReader(data)
		_, err := codec.Decode(reader)
		if err != nil {
			b.Fatal(err)
		}
	}
}
