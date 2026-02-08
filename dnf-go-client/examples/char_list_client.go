package main

import (
	"encoding/binary"
	"fmt"
	"net"
	"time"

	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func main() {
	conn, err := net.Dial("tcp", "127.0.0.1:10001")
	if err != nil {
		fmt.Printf("连接服务器失败: %v\n", err)
		return
	}
	defer conn.Close()

	fmt.Println("已连接到服务器")

	loginReq := &dnfv1.LoginRequest{
		Openid: "test_openid_123",
		Type:   1,
		Token:  "test_token_123",
	}

	loginData, err := proto.Marshal(loginReq)
	if err != nil {
		fmt.Printf("序列化登录请求失败: %v\n", err)
		return
	}

	moduleId := uint16(10000)
	cmd := uint8(0)
	transId := uint8(1)

	totalLen := uint16(len(loginData) + 8)

	buf := make([]byte, totalLen)
	binary.LittleEndian.PutUint16(buf[0:2], totalLen)
	binary.LittleEndian.PutUint16(buf[2:4], moduleId)
	buf[4] = cmd
	buf[5] = transId
	binary.LittleEndian.PutUint16(buf[6:8], 0)
	copy(buf[8:], loginData)

	fmt.Printf("发送登录请求: ModuleID=%d, CMD=%d, TransID=%d, DataLen=%d\n", moduleId, cmd, transId, len(loginData))
	_, err = conn.Write(buf)
	if err != nil {
		fmt.Printf("发送登录请求失败: %v\n", err)
		return
	}

	fmt.Println("等待登录响应...")

	conn.SetReadDeadline(time.Now().Add(10 * time.Second))
	header := make([]byte, 8)
	_, err = conn.Read(header)
	if err != nil {
		fmt.Printf("读取登录响应头失败: %v\n", err)
		return
	}

	respTotalLen := binary.LittleEndian.Uint16(header[0:2])
	respModuleId := binary.LittleEndian.Uint16(header[2:4])
	respCmd := header[4]
	respTransId := header[5]

	fmt.Printf("收到登录响应头: TotalLen=%d, ModuleID=%d, CMD=%d, TransID=%d\n", respTotalLen, respModuleId, respCmd, respTransId)

	bodyLen := respTotalLen - 8
	body := make([]byte, bodyLen)
	_, err = conn.Read(body)
	if err != nil {
		fmt.Printf("读取登录响应体失败: %v\n", err)
		return
	}

	var loginResp dnfv1.LoginResponse
	if err := proto.Unmarshal(body, &loginResp); err != nil {
		fmt.Printf("解析登录响应失败: %v\n", err)
		return
	}

	fmt.Println("=== 登录响应 ===")
	fmt.Printf("Error: %d\n", loginResp.Error)
	fmt.Printf("Authkey: %s\n", loginResp.Authkey)
	fmt.Printf("Accountkey: %s\n", loginResp.Accountkey)

	fmt.Println("\n发送角色列表请求...")

	charListReq := &dnfv1.CharacterListRequest{}
	charListData, err := proto.Marshal(charListReq)
	if err != nil {
		fmt.Printf("序列化角色列表请求失败: %v\n", err)
		return
	}

	moduleId = uint16(10002)
	cmd = uint8(0)
	transId = uint8(2)

	totalLen = uint16(len(charListData) + 8)

	buf = make([]byte, totalLen)
	binary.LittleEndian.PutUint16(buf[0:2], totalLen)
	binary.LittleEndian.PutUint16(buf[2:4], moduleId)
	buf[4] = cmd
	buf[5] = transId
	binary.LittleEndian.PutUint16(buf[6:8], 0)
	copy(buf[8:], charListData)

	fmt.Printf("发送角色列表请求: ModuleID=%d, CMD=%d, TransID=%d, DataLen=%d\n", moduleId, cmd, transId, len(charListData))
	_, err = conn.Write(buf)
	if err != nil {
		fmt.Printf("发送角色列表请求失败: %v\n", err)
		return
	}

	fmt.Println("等待角色列表响应...")

	conn.SetReadDeadline(time.Now().Add(10 * time.Second))
	header = make([]byte, 8)
	_, err = conn.Read(header)
	if err != nil {
		fmt.Printf("读取响应头失败: %v\n", err)
		return
	}

	respTotalLen = binary.LittleEndian.Uint16(header[0:2])
	respModuleId = binary.LittleEndian.Uint16(header[2:4])
	respCmd = header[4]
	respTransId = header[5]

	fmt.Printf("收到角色列表响应头: TotalLen=%d, ModuleID=%d, CMD=%d, TransID=%d\n", respTotalLen, respModuleId, respCmd, respTransId)

	bodyLen = respTotalLen - 8
	body = make([]byte, bodyLen)
	_, err = conn.Read(body)
	if err != nil {
		fmt.Printf("读取响应体失败: %v\n", err)
		return
	}

	var charListResp dnfv1.CharacterListResponse
	if err := proto.Unmarshal(body, &charListResp); err != nil {
		fmt.Printf("解析角色列表响应失败: %v\n", err)
		return
	}

	fmt.Println("=== 角色列表响应 ===")
	fmt.Printf("Error: %d\n", charListResp.Error)
	fmt.Printf("Version: %d\n", charListResp.Version)
	fmt.Printf("Count: %d\n", charListResp.Count)
	fmt.Printf("AdventureUnionLevel: %d\n", charListResp.AdventureUnionLevel)
	fmt.Printf("AdventureUnionExp: %d\n", charListResp.AdventureUnionExp)
	fmt.Printf("DailyCreateCharCount: %d\n", charListResp.DailyCreateCharCount)
	fmt.Printf("DailyCreateCharMaxCount: %d\n", charListResp.DailyCreateCharMaxCount)
	fmt.Printf("AdventureUnionName: %s\n", charListResp.AdventureUnionName)
	fmt.Printf("MaxCount: %d\n", charListResp.MaxCount)
	fmt.Printf("IgnoreContentsBlacklist: %s\n", charListResp.IgnoreContentsBlacklist)

	fmt.Println("\n=== 职业限制列表 ===")
	for i, limit := range charListResp.Limits {
		fmt.Printf("[%d] Job=%d, GrowType=%d, Level=%d\n", i, limit.Job, limit.GrowType, limit.Level)
	}

	fmt.Println("\n=== 角色列表 ===")
	for i, char := range charListResp.Characters {
		fmt.Printf("[%d] CharGuid=%d, Name=%s, Level=%d, Job=%d\n", i, char.CharGuid, char.Name, char.Level, char.Job)
		fmt.Printf("    GrowType=%d, SecGrowType=%d, Fatigue=%d, EquipScore=%d\n", char.GrowType, char.SecGrowType, char.Fatigue, char.EquipScore)
		fmt.Printf("    AvatarVisibleFlags=%d, DeletionStatus=%d\n", char.AvatarVisibleFlags, char.DeletionStatus)
		if char.Equips != nil {
			fmt.Printf("    EquipList: %d items\n", len(char.Equips.Equiplist))
			fmt.Printf("    AvatarList: %d items\n", len(char.Equips.Avatarlist))
		}
	}

	fmt.Println("\n角色列表通信测试完成！")
}
