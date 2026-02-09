package test

import (
	"bytes"
	"encoding/binary"
	"fmt"
	"net"
	"testing"
	"time"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"

	"google.golang.org/protobuf/proto"
)

const (
	ServerHost = "127.0.0.1"
	ServerPort = 10001
)

// MessageHeader 消息头结构（Java服务端格式）
type MessageHeader struct {
	TotalLen uint16 // 总长度
	Module   uint16 // 模块ID
	Seq      byte   // 序列号
	TransID  byte   // 事务ID
	Reserved uint16 // 保留字段
}

// TestLoginRequest_NetworkCommunication 测试Go客户端与Java服务端的网络通信
func TestLoginRequest_NetworkCommunication(t *testing.T) {
	conn, err := net.Dial("tcp", fmt.Sprintf("%s:%d", ServerHost, ServerPort))
	if err != nil {
		t.Fatalf("连接Java服务端失败: %v", err)
	}
	defer conn.Close()

	conn.SetDeadline(time.Now().Add(5 * time.Second))

	loginRequest := &dnfv1.LoginRequest{
		Openid:         "hutue",
		Type:           1,
		Token:          "123456",
		PlatID:         1001,
		ClientIP:       "127.0.0.1",
		Version:        "1.0.0",
		Friendopenid:   "",
		Cancelunregist: 0,
		Countrycode:    "CN",
		Toyplatid:      0,
		Agetype:        1,
	}

	requestData, err := proto.Marshal(loginRequest)
	if err != nil {
		t.Fatalf("序列化登录请求失败: %v", err)
	}

	header := MessageHeader{
		TotalLen: uint16(len(requestData) + 8),
		Module:   10000,
		Seq:      1,
		TransID:  1,
		Reserved: 0,
	}

	headerBuffer := new(bytes.Buffer)
	binary.Write(headerBuffer, binary.LittleEndian, header.TotalLen)
	binary.Write(headerBuffer, binary.LittleEndian, header.Module)
	binary.Write(headerBuffer, binary.LittleEndian, header.Seq)
	binary.Write(headerBuffer, binary.LittleEndian, header.TransID)
	binary.Write(headerBuffer, binary.LittleEndian, header.Reserved)

	message := append(headerBuffer.Bytes(), requestData...)

	_, err = conn.Write(message)
	if err != nil {
		t.Fatalf("发送登录请求失败: %v", err)
	}

	t.Logf("发送登录请求成功，数据长度: %d", len(message))

	responseHeader := make([]byte, 8)
	_, err = conn.Read(responseHeader)
	if err != nil {
		t.Fatalf("读取响应头失败: %v", err)
	}

	responseHeaderBuffer := bytes.NewReader(responseHeader)
	var respHeader MessageHeader
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.TotalLen)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Module)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Seq)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.TransID)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Reserved)

	t.Logf("收到响应头: TotalLen=%d, Module=%d, Seq=%d, TransID=%d, Reserved=%d",
		respHeader.TotalLen, respHeader.Module, respHeader.Seq, respHeader.TransID, respHeader.Reserved)

	if respHeader.Module != 10000 {
		t.Errorf("响应模块ID错误，期望10000，实际%d", respHeader.Module)
	}

	responseBodyLen := int(respHeader.TotalLen) - 8
	if responseBodyLen < 0 {
		responseBodyLen = 0
	}

	responseBody := make([]byte, responseBodyLen)
	if responseBodyLen > 0 {
		_, err = conn.Read(responseBody)
		if err != nil {
			t.Fatalf("读取响应体失败: %v", err)
		}
	}

	t.Logf("收到响应体，数据长度: %d", len(responseBody))

	loginResponse := &dnfv1.LoginResponse{}
	err = proto.Unmarshal(responseBody, loginResponse)
	if err != nil {
		t.Fatalf("反序列化登录响应失败: %v", err)
	}

	t.Logf("登录响应解析成功:")
	t.Logf("  Error: %d", loginResponse.Error)
	t.Logf("  AuthKey: %s", loginResponse.Authkey)
	t.Logf("  AccountKey: %s", loginResponse.Accountkey)
	t.Logf("  Encrypt: %v", loginResponse.Encrypt)
	t.Logf("  ServerTime: %d", loginResponse.Servertime)
	t.Logf("  LocalTime: %s", loginResponse.Localtime)
	t.Logf("  Authority: %d", loginResponse.Authority)
	t.Logf("  WorldID: %d", loginResponse.Worldid)

	if loginResponse.Error != 0 {
		t.Logf("登录失败，错误码: %d", loginResponse.Error)
	} else {
		t.Logf("登录成功！")
	}
}

// TestLoginRequest_InvalidCredentials 测试无效凭证的登录请求
func TestLoginRequest_InvalidCredentials(t *testing.T) {
	conn, err := net.Dial("tcp", fmt.Sprintf("%s:%d", ServerHost, ServerPort))
	if err != nil {
		t.Fatalf("连接Java服务端失败: %v", err)
	}
	defer conn.Close()

	conn.SetDeadline(time.Now().Add(5 * time.Second))

	loginRequest := &dnfv1.LoginRequest{
		Openid:         "invalid_openid",
		Type:           1,
		Token:          "invalid_token",
		PlatID:         1001,
		ClientIP:       "127.0.0.1",
		Version:        "1.0.0",
		Friendopenid:   "",
		Cancelunregist: 0,
		Countrycode:    "CN",
		Toyplatid:      0,
		Agetype:        1,
	}

	requestData, err := proto.Marshal(loginRequest)
	if err != nil {
		t.Fatalf("序列化登录请求失败: %v", err)
	}

	header := MessageHeader{
		TotalLen: uint16(len(requestData) + 8),
		Module:   10000,
		Seq:      1,
		TransID:  2,
		Reserved: 0,
	}

	headerBuffer := new(bytes.Buffer)
	binary.Write(headerBuffer, binary.LittleEndian, header.TotalLen)
	binary.Write(headerBuffer, binary.LittleEndian, header.Module)
	binary.Write(headerBuffer, binary.LittleEndian, header.Seq)
	binary.Write(headerBuffer, binary.LittleEndian, header.TransID)
	binary.Write(headerBuffer, binary.LittleEndian, header.Reserved)

	message := append(headerBuffer.Bytes(), requestData...)

	_, err = conn.Write(message)
	if err != nil {
		t.Fatalf("发送登录请求失败: %v", err)
	}

	t.Logf("发送无效凭证登录请求成功")

	responseHeader := make([]byte, 8)
	_, err = conn.Read(responseHeader)
	if err != nil {
		t.Fatalf("读取响应头失败: %v", err)
	}

	responseHeaderBuffer := bytes.NewReader(responseHeader)
	var respHeader MessageHeader
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.TotalLen)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Module)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Seq)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.TransID)
	binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Reserved)

	t.Logf("收到响应头: TotalLen=%d, Module=%d, Seq=%d, TransID=%d, Reserved=%d",
		respHeader.TotalLen, respHeader.Module, respHeader.Seq, respHeader.TransID, respHeader.Reserved)

	responseBodyLen := int(respHeader.TotalLen) - 8
	if responseBodyLen < 0 {
		responseBodyLen = 0
	}

	responseBody := make([]byte, responseBodyLen)
	if responseBodyLen > 0 {
		_, err = conn.Read(responseBody)
		if err != nil {
			t.Fatalf("读取响应体失败: %v", err)
		}
	}

	loginResponse := &dnfv1.LoginResponse{}
	err = proto.Unmarshal(responseBody, loginResponse)
	if err != nil {
		t.Fatalf("反序列化登录响应失败: %v", err)
	}

	t.Logf("无效凭证登录响应:")
	t.Logf("  Error: %d", loginResponse.Error)

	if loginResponse.Error == 0 {
		t.Error("期望登录失败，但实际登录成功")
	}
}

// TestLoginRequest_MultipleRequests 测试多个并发登录请求
func TestLoginRequest_MultipleRequests(t *testing.T) {
	numRequests := 3
	for i := 0; i < numRequests; i++ {
		t.Run(fmt.Sprintf("Request_%d", i), func(t *testing.T) {
			conn, err := net.Dial("tcp", fmt.Sprintf("%s:%d", ServerHost, ServerPort))
			if err != nil {
				t.Fatalf("连接Java服务端失败: %v", err)
			}
			defer conn.Close()

			conn.SetDeadline(time.Now().Add(5 * time.Second))

			loginRequest := &dnfv1.LoginRequest{
				Openid:         fmt.Sprintf("test_openid_%d_%d", i, time.Now().Unix()),
				Type:           1,
				Token:          fmt.Sprintf("test_token_%d", i),
				PlatID:         1001,
				ClientIP:       "127.0.0.1",
				Version:        "1.0.0",
				Friendopenid:   "",
				Cancelunregist: 0,
				Countrycode:    "CN",
				Toyplatid:      0,
				Agetype:        1,
			}

			requestData, err := proto.Marshal(loginRequest)
			if err != nil {
				t.Fatalf("序列化登录请求失败: %v", err)
			}

			header := MessageHeader{
				TotalLen: uint16(len(requestData) + 8),
				Module:   10000,
				Seq:      1,
				TransID:  byte(i + 10),
				Reserved: 0,
			}

			headerBuffer := new(bytes.Buffer)
			binary.Write(headerBuffer, binary.LittleEndian, header.TotalLen)
			binary.Write(headerBuffer, binary.LittleEndian, header.Module)
			binary.Write(headerBuffer, binary.LittleEndian, header.Seq)
			binary.Write(headerBuffer, binary.LittleEndian, header.TransID)
			binary.Write(headerBuffer, binary.LittleEndian, header.Reserved)

			message := append(headerBuffer.Bytes(), requestData...)

			_, err = conn.Write(message)
			if err != nil {
				t.Fatalf("发送登录请求失败: %v", err)
			}

			t.Logf("发送登录请求 #%d 成功", i)

			responseHeader := make([]byte, 8)
			_, err = conn.Read(responseHeader)
			if err != nil {
				t.Fatalf("读取响应头失败: %v", err)
			}

			responseHeaderBuffer := bytes.NewReader(responseHeader)
			var respHeader MessageHeader
			binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.TotalLen)
			binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Module)
			binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Seq)
			binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.TransID)
			binary.Read(responseHeaderBuffer, binary.LittleEndian, &respHeader.Reserved)

			t.Logf("请求 #%d 收到响应: TotalLen=%d, Module=%d, Seq=%d, TransID=%d, Reserved=%d",
				i, respHeader.TotalLen, respHeader.Module, respHeader.Seq, respHeader.TransID, respHeader.Reserved)

			responseBodyLen := int(respHeader.TotalLen) - 8
			if responseBodyLen < 0 {
				responseBodyLen = 0
			}

			responseBody := make([]byte, responseBodyLen)
			if responseBodyLen > 0 {
				_, err = conn.Read(responseBody)
				if err != nil {
					t.Fatalf("读取响应体失败: %v", err)
				}
			}

			loginResponse := &dnfv1.LoginResponse{}
			err = proto.Unmarshal(responseBody, loginResponse)
			if err != nil {
				t.Fatalf("反序列化登录响应失败: %v", err)
			}

			t.Logf("请求 #%d 登录响应: Error=%d, AuthKey=%s", i, loginResponse.Error, loginResponse.Authkey)
		})
	}
}
