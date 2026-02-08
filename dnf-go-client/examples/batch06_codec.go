package main

import (
	"encoding/binary"
	"fmt"
	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func main() {
	fmt.Println("===== 批次06编解码测试 =====")

	testAuthkeyRefresh()
	testPlatformProfileUpdate()

	fmt.Println("===== 所有测试通过 =====")
}

func testAuthkeyRefresh() {
	fmt.Println("\n--- 测试认证密钥刷新消息 ---")

	req := &dnfv1.AuthkeyRefreshRequest{
		Authkey: "test_authkey_123",
	}

	moduleID := uint16(10009)
	body, _ := proto.Marshal(req)
	packet := buildPacket(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.AuthkeyRefreshRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: Authkey=%s\n", parsedReq.Authkey)

	resp := &dnfv1.AuthkeyRefreshResponse{
		Error:   0,
		Authkey: "new_authkey_456",
		Version: "1.0.0",
	}

	resp.Channel = append(resp.Channel, &dnfv1.ChannelInfo{
		World:    1,
		Channel:  1,
		Ip:       "127.0.0.1",
		Port:     10001,
		Priority: 1,
	})

	resp.Channel = append(resp.Channel, &dnfv1.ChannelInfo{
		World:    1,
		Channel:  2,
		Ip:       "127.0.0.1",
		Port:     10002,
		Priority: 2,
	})

	respBody, _ := proto.Marshal(resp)
	respPacket := buildPacket(moduleID, 1, respBody)

	fmt.Printf("响应数据包长度: %d\n", len(respPacket))
	fmt.Printf("响应数据包: %v\n", respPacket)

	parsedResp := &dnfv1.AuthkeyRefreshResponse{}
	if err := proto.Unmarshal(respBody, parsedResp); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的响应: Error=%d, Authkey=%s, Version=%s\n",
		parsedResp.Error, parsedResp.Authkey, parsedResp.Version)
	fmt.Printf("频道数量: %d\n", len(parsedResp.Channel))
	for i, channel := range parsedResp.Channel {
		fmt.Printf("  频道[%d]: World=%d, Channel=%d, Ip=%s, Port=%d, Priority=%d\n",
			i, channel.World, channel.Channel, channel.Ip, channel.Port, channel.Priority)
	}
}

func testPlatformProfileUpdate() {
	fmt.Println("\n--- 测试平台资料更新消息 ---")

	req := &dnfv1.PlatformProfileUpdateRequest{
		Profileurl:      "https://example.com/profile.jpg",
		Profilename:     "Test User",
		Firstlocation:   1,
		Secondlocation:  2,
	}

	moduleID := uint16(10012)
	body, _ := proto.Marshal(req)
	packet := buildPacket(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.PlatformProfileUpdateRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: Profileurl=%s, Profilename=%s, Firstlocation=%d, Secondlocation=%d\n",
		parsedReq.Profileurl, parsedReq.Profilename, parsedReq.Firstlocation, parsedReq.Secondlocation)

	resp := &dnfv1.PlatformProfileUpdateResponse{
		Error: 0,
	}

	respBody, _ := proto.Marshal(resp)
	respPacket := buildPacket(moduleID, 1, respBody)

	fmt.Printf("响应数据包长度: %d\n", len(respPacket))
	fmt.Printf("响应数据包: %v\n", respPacket)

	parsedResp := &dnfv1.PlatformProfileUpdateResponse{}
	if err := proto.Unmarshal(respBody, parsedResp); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的响应: Error=%d\n", parsedResp.Error)
}

func buildPacket(moduleID uint16, cmd byte, body []byte) []byte {
	totalLen := len(body) + 8
	packet := make([]byte, totalLen)

	binary.LittleEndian.PutUint16(packet[0:2], uint16(totalLen))
	binary.LittleEndian.PutUint16(packet[2:4], moduleID)
	packet[4] = 1
	packet[5] = 0
	binary.LittleEndian.PutUint16(packet[6:8], 0)
	copy(packet[8:], body)

	return packet
}
