package tests

import (
	"bufio"
	"bytes"
	"net"
	"testing"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/handlers"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

// TestTCPMessageLink 端到端验证 TCP 消息链路：
// ProtoCodec 解码 → MessageDispatcher 分发 → Handler 处理 → 响应编码回传
// 使用 LoginHandler（不依赖外部服务），验证完整链路可用。
func TestTCPMessageLink(t *testing.T) {
	// 1. 创建编解码器并注册所有消息类型
	codec := network.NewProtoCodec()
	codec.RegisterAllMessages()
	network.SetEncoderInstance(codec)

	// 2. 创建分发器并注册所有处理器
	dispatcher := network.NewMessageDispatcher()
	handlers.RegisterAllHandlers(dispatcher)

	// 3. 启动 TCP 服务器（随机端口）
	cfg := network.DefaultServerConfig()
	cfg.Host = "127.0.0.1"
	cfg.Port = 0
	tcpServer := network.NewTCPServer(cfg, nil)
	tcpServer.SetCodec(codec)
	tcpServer.SetDispatcher(dispatcher)

	if err := tcpServer.Start(); err != nil {
		t.Fatalf("failed to start tcp server: %v", err)
	}
	defer tcpServer.Stop()

	addr := tcpServer.Addr()
	if addr == nil {
		t.Fatal("tcp server addr is nil")
	}

	// 4. 客户端连接
	conn, err := net.DialTimeout("tcp", addr.String(), 5*time.Second)
	if err != nil {
		t.Fatalf("failed to dial tcp server: %v", err)
	}
	defer conn.Close()

	// 5. 发送登录请求（module=10000, cmd=0）
	req := &dnfv1.LoginRequest{
		Openid:  "test_openid_001",
		Version: "1.0",
	}
	packet, err := codec.EncodeWithMeta(10000, 0, req)
	if err != nil {
		t.Fatalf("failed to encode login request: %v", err)
	}

	if _, err := conn.Write(packet); err != nil {
		t.Fatalf("failed to write login request: %v", err)
	}

	// 6. 读取并解码响应（Decode 自行消费 [2字节长度][4字节header][body]）
	conn.SetReadDeadline(time.Now().Add(5 * time.Second))
	msg, err := codec.Decode(conn)
	if err != nil {
		t.Fatalf("failed to decode response: %v", err)
	}

	p, ok := msg.(*network.ProtocolPacket)
	if !ok {
		t.Fatalf("unexpected message type: %T", msg)
	}

	resp, ok := p.Message.(*dnfv1.LoginResponse)
	if !ok {
		t.Fatalf("unexpected response type: %T, meta=%v", p.Message, p.Meta)
	}

	if resp.Error != 0 {
		t.Fatalf("login response error: %d", resp.Error)
	}
	if resp.AuthKey == "" {
		t.Fatal("login response authKey is empty")
	}

	// 8. 验证 meta 正确（module=10000, cmd=1）
	if p.Meta.Module != 10000 || p.Meta.Cmd != 1 {
		t.Fatalf("unexpected response meta: module=%d cmd=%d", p.Meta.Module, p.Meta.Cmd)
	}

	t.Logf("TCP message link OK: login resp error=%d authKey=%s", resp.Error, resp.AuthKey)
}

// TestTCPModuleRegistration 验证关键模块消息类型与处理器均已注册
func TestTCPModuleRegistration(t *testing.T) {
	codec := network.NewProtoCodec()
	codec.RegisterAllMessages()

	dispatcher := network.NewMessageDispatcher()
	handlers.RegisterAllHandlers(dispatcher)

	cases := []struct {
		name   string
		module uint16
		cmd    uint16
	}{
		{"auth-login", 10000, 0},
		{"role-info", 10001, 0},
		{"bag", 10002, 0},
		{"dungeon", 10003, 0},
		{"chat", 10004, 0},
		{"shop", 10005, 0},
		{"quest", 10006, 0},
		{"guild", 10007, 0},
		{"pk", 10008, 0},
		{"party", 10009, 0},
		{"achievement", 10700, 0},
		{"adventure", 17201, 0},
	}

	for _, tc := range cases {
		if !dispatcher.HasHandler(tc.module, tc.cmd) {
			t.Errorf("handler not registered: %s (module=%d cmd=%d)", tc.name, tc.module, tc.cmd)
		}
	}

	// 验证消息类型可编解码往返（使用已注册的登录请求）
	req := &dnfv1.LoginRequest{Openid: "roundtrip_test"}
	data, err := codec.EncodeWithMeta(10000, 0, req)
	if err != nil {
		t.Fatalf("encode failed: %v", err)
	}
	decoded, err := codec.Decode(bufio.NewReader(bytes.NewReader(data)))
	if err != nil {
		t.Fatalf("decode failed: %v", err)
	}
	pkt := decoded.(*network.ProtocolPacket)
	if pkt.Meta.Module != 10000 || pkt.Meta.Cmd != 0 {
		t.Fatalf("roundtrip meta mismatch: %v", pkt.Meta)
	}
	if _, ok := pkt.Message.(*dnfv1.LoginRequest); !ok {
		t.Fatalf("roundtrip type mismatch: %T", pkt.Message)
	}
}

// readFull 读取完整数据
func readFull(reader *bufio.Reader, buf []byte) (int, error) {
	total := 0
	for total < len(buf) {
		n, err := reader.Read(buf[total:])
		if err != nil {
			return total, err
		}
		total += n
	}
	return total, nil
}
