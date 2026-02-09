package main

import (
	"fmt"
	"log"
	"time"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func main07() {
	fmt.Println("===== 批次07编解码测试开始 =====")

	testConnectBattleServer()
	testIdipProhibitList()
	testLoadServerSimpleData()
	testSaveServerSimpleData()

	fmt.Println("===== 批次07编解码测试完成 =====")
}

func testConnectBattleServer() {
	fmt.Println("\n--- 测试连接战斗服务器消息 ---")

	req := &dnfv1.ConnectBattleServerRequest{
		Authkey:     "test_authkey_123",
		Openid:      "test_openid_456",
		World:       1,
		Channel:     1,
		Charguid:    1234567890,
		Type:        1,
		Dungeonguid: 0,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("请求序列化成功，长度: %d\n", len(data))

	resp := &dnfv1.ConnectBattleServerResponse{
		Error:      0,
		Authkey:    "new_authkey_789",
		Bchannel:   1,
		Servertime: uint64(time.Now().Unix()),
		Encrypt:    true,
		Key:        "test_key_123",
	}
	resp.Seeds = append(resp.Seeds, 100)
	resp.Seeds = append(resp.Seeds, 200)
	resp.Seeds = append(resp.Seeds, 300)

	respData, err := proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("响应序列化成功，长度: %d\n", len(respData))

	var parsedResp dnfv1.ConnectBattleServerResponse
	if err := proto.Unmarshal(respData, &parsedResp); err != nil {
		log.Fatalf("Unmarshal failed: %v", err)
	}
	fmt.Printf("响应反序列化成功: error=%d, authkey=%s, encrypt=%v\n",
		parsedResp.Error, parsedResp.Authkey, parsedResp.Encrypt)
}

func testIdipProhibitList() {
	fmt.Println("\n--- 测试IDIP禁止列表消息 ---")

	req := &dnfv1.IdipProhibitListRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("请求序列化成功，长度: %d\n", len(data))

	resp := &dnfv1.IdipProhibitListResponse{
		Error: 0,
	}

	resp.Prohibit = append(resp.Prohibit, &dnfv1.Prohibit{
		Target:  1234567890,
		Type:    dnfv1.IdipProhibitType_BAN,
		Endtime: time.Now().Unix() + 86400,
		Reason:  "Test ban reason",
	})

	resp.Prohibit = append(resp.Prohibit, &dnfv1.Prohibit{
		Target:  9876543210,
		Type:    dnfv1.IdipProhibitType_CHAT,
		Endtime: time.Now().Unix() + 86400,
		Reason:  "Test chat ban reason",
	})

	respData, err := proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("响应序列化成功，长度: %d\n", len(respData))

	var parsedResp dnfv1.IdipProhibitListResponse
	if err := proto.Unmarshal(respData, &parsedResp); err != nil {
		log.Fatalf("Unmarshal failed: %v", err)
	}
	fmt.Printf("响应反序列化成功: error=%d, prohibit count=%d\n",
		parsedResp.Error, len(parsedResp.Prohibit))
}

func testLoadServerSimpleData() {
	fmt.Println("\n--- 测试加载服务器简单数据消息 ---")

	req := &dnfv1.LoadServerSimpleDataRequest{}

	req.List = append(req.List, &dnfv1.LoadServerSimpleData{
		Type:      1,
		Enumvalue: 100,
	})

	req.List = append(req.List, &dnfv1.LoadServerSimpleData{
		Type:      2,
		Enumvalue: 200,
	})

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("请求序列化成功，长度: %d\n", len(data))

	resp := &dnfv1.LoadServerSimpleDataResponse{
		Error:     0,
		Type:      1,
		Enumvalue: 100,
		Value:     "test_value_123",
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("响应序列化成功，长度: %d\n", len(respData))

	var parsedResp dnfv1.LoadServerSimpleDataResponse
	if err := proto.Unmarshal(respData, &parsedResp); err != nil {
		log.Fatalf("Unmarshal failed: %v", err)
	}
	fmt.Printf("响应反序列化成功: error=%d, type=%d, value=%s\n",
		parsedResp.Error, parsedResp.Type, parsedResp.Value)
}

func testSaveServerSimpleData() {
	fmt.Println("\n--- 测试保存服务器简单数据消息 ---")

	req := &dnfv1.SaveServerSimpleDataRequest{
		Type:      1,
		Enumvalue: 100,
		Value:     "test_value_123",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("请求序列化成功，长度: %d\n", len(data))

	resp := &dnfv1.SaveServerSimpleDataResponse{
		Error: 0,
	}

	respData, err := proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Marshal failed: %v", err)
	}
	fmt.Printf("响应序列化成功，长度: %d\n", len(respData))

	var parsedResp dnfv1.SaveServerSimpleDataResponse
	if err := proto.Unmarshal(respData, &parsedResp); err != nil {
		log.Fatalf("Unmarshal failed: %v", err)
	}
	fmt.Printf("响应反序列化成功: error=%d\n", parsedResp.Error)
}
