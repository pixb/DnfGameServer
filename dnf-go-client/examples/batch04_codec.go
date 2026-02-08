package main

import (
	"encoding/binary"
	"fmt"
	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func main() {
	fmt.Println("===== 批次04编解码测试 =====")

	testCreateCharacter()
	testChannelList()
	testEnterChannel()

	fmt.Println("===== 所有测试通过 =====")
}

func testCreateCharacter() {
	fmt.Println("\n--- 测试创建角色消息 ---")

	req := &dnfv1.CreateCharacterRequest{
		Job:  1,
		Name: "TestCharacter",
	}

	moduleID := uint16(10003)
	body, _ := proto.Marshal(req)
	packet := buildPacket(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.CreateCharacterRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: Job=%d, Name=%s\n", parsedReq.Job, parsedReq.Name)

	resp := &dnfv1.CreateCharacterResponse{
		Error:    0,
		Charguid: 1234567890,
		Job:      1,
		Name:     "TestCharacter",
	}

	respBody, _ := proto.Marshal(resp)
	respPacket := buildPacket(moduleID, 1, respBody)

	fmt.Printf("响应数据包长度: %d\n", len(respPacket))
	fmt.Printf("响应数据包: %v\n", respPacket)

	parsedResp := &dnfv1.CreateCharacterResponse{}
	if err := proto.Unmarshal(respBody, parsedResp); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的响应: Error=%d, Charguid=%d, Job=%d, Name=%s\n",
		parsedResp.Error, parsedResp.Charguid, parsedResp.Job, parsedResp.Name)
}

func testChannelList() {
	fmt.Println("\n--- 测试频道列表消息 ---")

	req := &dnfv1.ChannelListRequest{
		Type:    1,
		Worldid: 1,
	}

	moduleID := uint16(10008)
	body, _ := proto.Marshal(req)
	packet := buildPacket(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.ChannelListRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: Type=%d, Worldid=%d\n", parsedReq.Type, parsedReq.Worldid)

	resp := &dnfv1.ChannelListResponse{
		Error: 0,
		Count: 2,
		Type:  1,
	}

	resp.List = append(resp.List, &dnfv1.Channel{
		World:       1,
		Channel:      1,
		Ip:          "127.0.0.1",
		Port:        8080,
		Saturation:  50,
		Integration: false,
	})

	resp.List = append(resp.List, &dnfv1.Channel{
		World:       1,
		Channel:      2,
		Ip:          "127.0.0.1",
		Port:        8081,
		Saturation:  30,
		Integration: false,
	})

	resp.Integrations = append(resp.Integrations, &dnfv1.IntegrationWorld{
		World: 1,
	})

	resp.Integrationrecommands = append(resp.Integrationrecommands, &dnfv1.Channel{
		World:       1,
		Channel:      3,
		Ip:          "127.0.0.1",
		Port:        8082,
		Saturation:  20,
		Integration: true,
	})

	resp.Worldrecommands = append(resp.Worldrecommands, &dnfv1.Channel{
		World:       2,
		Channel:      1,
		Ip:          "127.0.0.1",
		Port:        8083,
		Saturation:  10,
		Integration: false,
	})

	respBody, _ := proto.Marshal(resp)
	respPacket := buildPacket(moduleID, 1, respBody)

	fmt.Printf("响应数据包长度: %d\n", len(respPacket))
	fmt.Printf("响应数据包: %v\n", respPacket)

	parsedResp := &dnfv1.ChannelListResponse{}
	if err := proto.Unmarshal(respBody, parsedResp); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的响应: Error=%d, Count=%d, Type=%d\n",
		parsedResp.Error, parsedResp.Count, parsedResp.Type)
	fmt.Printf("频道数量: %d\n", len(parsedResp.List))
	fmt.Printf("集成世界数量: %d\n", len(parsedResp.Integrations))
	fmt.Printf("集成推荐频道数量: %d\n", len(parsedResp.Integrationrecommands))
	fmt.Printf("世界推荐频道数量: %d\n", len(parsedResp.Worldrecommands))
}

func testEnterChannel() {
	fmt.Println("\n--- 测试进入频道消息 ---")

	req := &dnfv1.EnterChannelRequest{
		Openid:          "test_openid_123",
		Charguid:        1234567890,
		Authkey:          "test_authkey_123",
		Version:          "1.0.0",
		Accesstoken:     "test_token_123",
		Launchinfo:       1,
		Dungeonguid:     0,
		Registeredchannelid: "channel123",
		Installchannelid:  "install123",
		Isexternpackage:  false,
		Validusercheckcode: "check123",
		ToyPlatID:       1,
		Countrycode:      "CN",
		Language:         "zh-CN",
		Adid:            "ad123",
		Idfv:             "idfv123",
		Isadult:          false,
	}

	req.Deviceinfo = &dnfv1.ClientInfo{
		PlatID:    1,
		DeviceSoft: "Android",
		DeviceHard: "SM-G960F",
	}

	moduleID := uint16(10011)
	body, _ := proto.Marshal(req)
	packet := buildPacket(moduleID, 0, body)

	fmt.Printf("请求数据包长度: %d\n", len(packet))
	fmt.Printf("请求数据包: %v\n", packet)

	parsedReq := &dnfv1.EnterChannelRequest{}
	if err := proto.Unmarshal(body, parsedReq); err != nil {
		panic(err)
	}

	fmt.Printf("解析后的请求: Openid=%s, Charguid=%d, Authkey=%s\n",
		parsedReq.Openid, parsedReq.Charguid, parsedReq.Authkey)
	fmt.Printf("设备信息: PlatID=%d, DeviceSoft=%s, DeviceHard=%s\n",
		parsedReq.Deviceinfo.PlatID, parsedReq.Deviceinfo.DeviceSoft, parsedReq.Deviceinfo.DeviceHard)
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
