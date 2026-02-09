package main

import (
	"bytes"
	"encoding/binary"
	"log"
	"net"
	"time"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

const (
	ServerAddr = "127.0.0.1:10001"
	ModuleID   = 10000 // 登录模块ID
)

func mainLogin() {
	log.Println("=== 开始测试标准Protobuf跨语言通信 ===")

	// 创建登录请求
	req := &dnfv1.LoginRequest{
		Openid:         "hutue", // 使用数据库中存在的账号
		Type:           1,
		Token:          "test_token_abc",
		PlatID:         111,
		ClientIP:       "127.0.0.1",
		Version:        "1.0.0",
		Friendopenid:   "",
		Cancelunregist: 0,
		Countrycode:    "CN",
		Toyplatid:      1,
		Agetype:        1,
	}

	log.Printf("发送登录请求: openid=%s, token=%s, platID=%d", req.Openid, req.Token, req.PlatID)

	// 序列化请求
	reqData, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("序列化请求失败: %v", err)
	}
	log.Printf("请求序列化成功，长度=%d", len(reqData))

	// 构建消息头
	// 消息头结构：总长度(2) + 模块ID(2) + 序列号(1) + 事务ID(1) + 保留字段(2)
	totalLen := len(reqData) + 8
	buf := new(bytes.Buffer)

	// 使用LittleEndian字节序
	binary.Write(buf, binary.LittleEndian, uint16(totalLen))
	binary.Write(buf, binary.LittleEndian, uint16(ModuleID))
	binary.Write(buf, binary.LittleEndian, uint8(1))  // seq
	binary.Write(buf, binary.LittleEndian, uint8(0))  // transactionId
	binary.Write(buf, binary.LittleEndian, uint16(0)) // reserved
	buf.Write(reqData)

	log.Printf("消息头构建成功，总长度=%d", totalLen)

	// 连接服务器
	conn, err := net.DialTimeout("tcp", ServerAddr, 5*time.Second)
	if err != nil {
		log.Fatalf("连接服务器失败: %v", err)
	}
	defer conn.Close()

	log.Printf("连接服务器成功: %s", ServerAddr)

	// 发送请求
	_, err = conn.Write(buf.Bytes())
	if err != nil {
		log.Fatalf("发送请求失败: %v", err)
	}
	log.Println("发送请求成功")

	// 接收响应
	responseBuf := make([]byte, 4096)
	conn.SetReadDeadline(time.Now().Add(10 * time.Second))
	n, err := conn.Read(responseBuf)
	if err != nil {
		log.Fatalf("接收响应失败: %v", err)
	}
	log.Printf("接收响应成功，长度=%d", n)

	// 解析消息头
	if n < 8 {
		log.Fatalf("响应长度不足: %d", n)
	}

	reader := bytes.NewReader(responseBuf[:n])
	var totalLenResp, moduleIDResp uint16
	var seq, transactionID uint8
	var reserved uint16

	binary.Read(reader, binary.LittleEndian, &totalLenResp)
	binary.Read(reader, binary.LittleEndian, &moduleIDResp)
	binary.Read(reader, binary.LittleEndian, &seq)
	binary.Read(reader, binary.LittleEndian, &transactionID)
	binary.Read(reader, binary.LittleEndian, &reserved)

	log.Printf("响应消息头: totalLen=%d, moduleID=%d, seq=%d, transactionID=%d",
		totalLenResp, moduleIDResp, seq, transactionID)

	// 解析消息体
	bodyLen := totalLenResp - 8
	if bodyLen > 0 && n >= int(totalLenResp) {
		bodyData := responseBuf[8:totalLenResp]
		log.Printf("响应消息体长度=%d", bodyLen)

		// 反序列化响应
		var resp dnfv1.LoginResponse
		if err := proto.Unmarshal(bodyData, &resp); err != nil {
			log.Fatalf("反序列化响应失败: %v", err)
		}

		log.Println("=== 登录响应解析成功 ===")
		log.Printf("Error: %d", resp.Error)
		log.Printf("Authkey: %s", resp.Authkey)
		log.Printf("Accountkey: %s", resp.Accountkey)
		log.Printf("Encrypt: %v", resp.Encrypt)
		log.Printf("Servertime: %d", resp.Servertime)
		log.Printf("Localtime: %s", resp.Localtime)
		log.Printf("Authority: %d", resp.Authority)
		log.Printf("Key: %s", resp.Key)
		log.Printf("Worldid: %d", resp.Worldid)

		// 解析频道信息
		if len(resp.Channel) > 0 {
			log.Printf("频道列表:")
			for i, channel := range resp.Channel {
				log.Printf("  [%d] World=%d, Channel=%d, IP=%s, Port=%d, Priority=%d",
					i, channel.World, channel.Channel, channel.Ip, channel.Port, channel.Priority)
			}
		}

		// 解析入侵信息
		if resp.Intrudeinfo != nil {
			intrude := resp.Intrudeinfo
			log.Printf("入侵信息: Intrude=%v, Name=%s, Guid=%d, RpGuid=%d",
				intrude.Intrude, intrude.Name, intrude.Guid, intrude.RpGuid)
			if intrude.Channel != nil {
				log.Printf("  通道: World=%d, Channel=%d, IP=%s, Port=%d",
					intrude.Channel.World, intrude.Channel.Channel, intrude.Channel.Ip, intrude.Channel.Port)
			}
			if len(intrude.PartyMembers) > 0 {
				log.Printf("  队伍成员:")
				for i, member := range intrude.PartyMembers {
					log.Printf("    [%d] Name=%s, Level=%d, Job=%d",
						i, member.Name, member.Level, member.Job)
				}
			}
		}

		// 解析种子
		if len(resp.Seeds) > 0 {
			log.Printf("种子: %v", resp.Seeds)
		}

		log.Println("=== 测试成功 ===")
	} else {
		log.Printf("响应消息体为空或长度不足")
	}
}
