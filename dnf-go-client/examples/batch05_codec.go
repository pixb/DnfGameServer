package main

import (
	"encoding/binary"
	"fmt"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func main05() {
	fmt.Println("===== 批次05编解码测试 =====")

	testStandby()
	testRemoveCharacter()
	testStartGame()
	testExitCharacter()

	fmt.Println("===== 所有测试通过 =====")
}

func testStandby() {
	fmt.Println("\n--- 测试待机消息 ---")

	req := &dnfv1.StandbyRequest{}

	moduleID := uint16(10001)
	body, _ := proto.Marshal(req)
	packet := buildPacket05(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.StandbyRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: (空消息)\n")

	resp := &dnfv1.StandbyResponse{
		Error:     0,
		Standby:   1,
		Vip:       1,
		Reconnect: 0,
	}

	respBody, _ := proto.Marshal(resp)
	respPacket := buildPacket05(moduleID, 1, respBody)

	fmt.Printf("响应数据包长度: %d\n", len(respPacket))
	fmt.Printf("响应数据包: %v\n", respPacket)

	parsedResp := &dnfv1.StandbyResponse{}
	if err := proto.Unmarshal(respBody, parsedResp); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的响应: Error=%d, Standby=%d, Vip=%d, Reconnect=%d\n",
		parsedResp.Error, parsedResp.Standby, parsedResp.Vip, parsedResp.Reconnect)
}

func testRemoveCharacter() {
	fmt.Println("\n--- 测试删除角色消息 ---")

	req := &dnfv1.DeleteCharacterRequest{
		CharGuid: 1234567890,
	}

	moduleID := uint16(10004)
	body, _ := proto.Marshal(req)
	packet := buildPacket05(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.DeleteCharacterRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: CharGuid=%d\n", parsedReq.CharGuid)

	resp := &dnfv1.DeleteCharacterResponse{
		Error:       0,
		Charguid:    1234567890,
		Pendingtime: 1707433600,
	}

	respBody, _ := proto.Marshal(resp)
	respPacket := buildPacket05(moduleID, 1, respBody)

	fmt.Printf("响应数据包长度: %d\n", len(respPacket))
	fmt.Printf("响应数据包: %v\n", respPacket)

	parsedResp := &dnfv1.DeleteCharacterResponse{}
	if err := proto.Unmarshal(respBody, parsedResp); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的响应: Error=%d, Charguid=%d, Pendingtime=%d\n",
		parsedResp.Error, parsedResp.Charguid, parsedResp.Pendingtime)
}

func testStartGame() {
	fmt.Println("\n--- 测试开始游戏消息 ---")

	req := &dnfv1.StartGameRequest{
		Charguid:    1234567890,
		Dungeonguid: 0,
		Authkey:     "test_authkey_123",
		Accesstoken: "test_token_123",
		Paytoken:    "pay_token_123",
		Town:        1,
		Area:        1,
		Posx:        100,
		Posy:        200,
		Partyguid:   0,
	}

	req.Request = append(req.Request, &dnfv1.ProtocolTransaction{
		Protocol:      10000,
		Transactionid: 1,
	})

	req.Request = append(req.Request, &dnfv1.ProtocolTransaction{
		Protocol:      10002,
		Transactionid: 2,
	})

	moduleID := uint16(10005)
	body, _ := proto.Marshal(req)
	packet := buildPacket05(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.StartGameRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: Charguid=%d, Dungeonguid=%d, Authkey=%s\n",
		parsedReq.Charguid, parsedReq.Dungeonguid, parsedReq.Authkey)
	fmt.Printf("请求数量: %d\n", len(parsedReq.Request))
}

func testExitCharacter() {
	fmt.Println("\n--- 测试退出角色消息 ---")

	req := &dnfv1.ExitCharacterRequest{
		World:           1,
		Channel:         1,
		Reservationtype: 0,
		Exittype:        0,
	}

	moduleID := uint16(10007)
	body, _ := proto.Marshal(req)
	packet := buildPacket05(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.ExitCharacterRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: World=%d, Channel=%d, Reservationtype=%d, Exittype=%d\n",
		parsedReq.World, parsedReq.Channel, parsedReq.Reservationtype, parsedReq.Exittype)
}

func buildPacket05(moduleID uint16, cmd byte, body []byte) []byte {
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
