package main

import (
	"encoding/binary"
	"fmt"
	"log"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func main() {
	fmt.Println("===== 批次09 MAIL模块编解码器测试 =====")

	testMailList()
	testMailGet()
	testMailRead()
	testMailDelete()
	testMailItemAllGet()
	testMailAllDelete()

	fmt.Println("===== 批次09 MAIL模块编解码器测试完成 =====")
}

func testMailList() {
	fmt.Println("\n--- 测试邮件列表请求 (ModuleID: 15001) ---")

	req := &dnfv1.MailListRequest{
		Page: 1,
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal MailListRequest: %v", err)
	}

	packet := buildPacket(15001, 0, data)
	fmt.Printf("请求包长度: %d\n", len(packet))
	fmt.Printf("请求包内容: %v\n", packet[:min(20, len(packet))])

	resp := &dnfv1.MailListResponse{
		Error: 1,
		Count: 2,
		Type:  0,
		Postallist: []*dnfv1.PostAllList{
			{
				Index:      1,
				Count:      1,
				Guid:       1001,
				Title:      "Test Mail 1",
				Msg:        "Test message 1",
				Expiretime: 1234567890,
				Read:       false,
				Receive:    false,
				Importance: 1,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Failed to marshal MailListResponse: %v", err)
	}

	packet = buildPacket(15001, 1, data)
	fmt.Printf("响应包长度: %d\n", len(packet))
	fmt.Printf("响应包内容: %v\n", packet[:min(20, len(packet))])
}

func testMailGet() {
	fmt.Println("\n--- 测试获取邮件请求 (ModuleID: 15002) ---")

	req := &dnfv1.MailGetRequest{
		Type: 0,
		Guid: 1001,
		Selecteditems: []*dnfv1.SelectedItem{
			{
				Index:     1,
				Count:     1,
				Guid:      2001,
				Slotindex: 1,
			},
		},
		Selectedpackages: []*dnfv1.PostPackage{
			{
				Value:     100,
				Index:     1,
				Slotindex: 1,
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal MailGetRequest: %v", err)
	}

	packet := buildPacket(15002, 0, data)
	fmt.Printf("请求包长度: %d\n", len(packet))

	resp := &dnfv1.MailGetResponse{
		Error: 1,
		Guid:  1001,
		Limit: false,
		Type:  0,
		Remaineditems: []*dnfv1.SelectedItem{
			{
				Index:     2,
				Count:     1,
				Guid:      2002,
				Slotindex: 2,
			},
		},
		Remainedpackages: []*dnfv1.PostPackage{
			{
				Value:     200,
				Index:     2,
				Slotindex: 2,
			},
		},
		Rewards: &dnfv1.ContentsRewardInfo{
			Items: &dnfv1.ItemRewardInfo{
				Inventory: &dnfv1.Items{
					Equipitems: []*dnfv1.Equip{
						{
							EquipId: 3001,
							Guid:    3001,
						},
					},
					Materialitems: []*dnfv1.Stackable{
						{
							Index: 4001,
							Count: 10,
							Bind:  false,
						},
					},
				},
			},
			Currency: &dnfv1.CurrencyRewardInfo{
				Gold:    1000,
				Diamond: 100,
				Cera:    50,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Failed to marshal MailGetResponse: %v", err)
	}

	packet = buildPacket(15002, 1, data)
	fmt.Printf("响应包长度: %d\n", len(packet))
}

func testMailRead() {
	fmt.Println("\n--- 测试读取邮件请求 (ModuleID: 15003) ---")

	req := &dnfv1.MailReadRequest{
		Guid: 1001,
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal MailReadRequest: %v", err)
	}

	packet := buildPacket(15003, 0, data)
	fmt.Printf("请求包长度: %d\n", len(packet))

	resp := &dnfv1.MailReadResponse{
		Error: 1,
		Guid:  1001,
		Type:  0,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Failed to marshal MailReadResponse: %v", err)
	}

	packet = buildPacket(15003, 1, data)
	fmt.Printf("响应包长度: %d\n", len(packet))
}

func testMailDelete() {
	fmt.Println("\n--- 测试删除邮件请求 (ModuleID: 15004) ---")

	req := &dnfv1.MailDeleteRequest{
		Guid: 1001,
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal MailDeleteRequest: %v", err)
	}

	packet := buildPacket(15004, 0, data)
	fmt.Printf("请求包长度: %d\n", len(packet))

	resp := &dnfv1.MailDeleteResponse{
		Error: 1,
		Guid:  1001,
		Type:  0,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Failed to marshal MailDeleteResponse: %v", err)
	}

	packet = buildPacket(15004, 1, data)
	fmt.Printf("响应包长度: %d\n", len(packet))
}

func testMailItemAllGet() {
	fmt.Println("\n--- 测试一键领取所有邮件物品请求 (ModuleID: 15005) ---")

	req := &dnfv1.MailItemAllGetRequest{
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal MailItemAllGetRequest: %v", err)
	}

	packet := buildPacket(15005, 0, data)
	fmt.Printf("请求包长度: %d\n", len(packet))

	resp := &dnfv1.MailItemAllGetResponse{
		Error:   1,
		Page:    1,
		Maxpage: 5,
		Limit:   false,
		Type:    0,
		Bind:    false,
		Rewards: &dnfv1.ContentsRewardInfo{
			Items: &dnfv1.ItemRewardInfo{
				Inventory: &dnfv1.Items{
					Materialitems: []*dnfv1.Stackable{
						{
							Index: 5001,
							Count: 20,
							Bind:  false,
						},
					},
				},
			},
			Currency: &dnfv1.CurrencyRewardInfo{
				Gold:    5000,
				Diamond: 500,
				Cera:    250,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Failed to marshal MailItemAllGetResponse: %v", err)
	}

	packet = buildPacket(15005, 1, data)
	fmt.Printf("响应包长度: %d\n", len(packet))
}

func testMailAllDelete() {
	fmt.Println("\n--- 测试删除所有邮件请求 (ModuleID: 15006) ---")

	req := &dnfv1.MailAllDeleteRequest{
		Type: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal MailAllDeleteRequest: %v", err)
	}

	packet := buildPacket(15006, 0, data)
	fmt.Printf("请求包长度: %d\n", len(packet))

	resp := &dnfv1.MailAllDeleteResponse{
		Error:      1,
		Type:       0,
		Removecount: 10,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Failed to marshal MailAllDeleteResponse: %v", err)
	}

	packet = buildPacket(15006, 1, data)
	fmt.Printf("响应包长度: %d\n", len(packet))
}

func buildPacket(moduleID int, cmd int, body []byte) []byte {
	totalLen := len(body) + 8
	packet := make([]byte, totalLen)

	binary.LittleEndian.PutUint16(packet[0:2], uint16(totalLen))
	binary.LittleEndian.PutUint16(packet[2:4], uint16(moduleID))
	packet[4] = 1
	packet[5] = byte(cmd)
	binary.LittleEndian.PutUint16(packet[6:8], 0)
	copy(packet[8:], body)

	return packet
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
