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

	pingReq := &dnfv1.PingRequest{}
	data, err := proto.Marshal(pingReq)
	if err != nil {
		fmt.Printf("序列化PING请求失败: %v\n", err)
		return
	}

	totalLen := len(data) + 8
	packet := make([]byte, totalLen)

	binary.LittleEndian.PutUint16(packet[0:2], uint16(totalLen))
	binary.LittleEndian.PutUint16(packet[2:4], 10006)
	packet[4] = 1
	packet[5] = 0
	binary.LittleEndian.PutUint16(packet[6:8], 0)
	copy(packet[8:], data)

	fmt.Printf("发送PING请求，数据长度: %d\n", len(packet))
	_, err = conn.Write(packet)
	if err != nil {
		fmt.Printf("发送PING请求失败: %v\n", err)
		return
	}

	fmt.Println("等待PING响应...")

	conn.SetReadDeadline(time.Now().Add(5 * time.Second))
	header := make([]byte, 8)
	_, err = conn.Read(header)
	if err != nil {
		fmt.Printf("读取响应头失败: %v\n", err)
		return
	}

	responseLen := binary.LittleEndian.Uint16(header[0:2]) - 8
	moduleId := binary.LittleEndian.Uint16(header[2:4])
	fmt.Printf("收到响应头，moduleId: %d, body长度: %d\n", moduleId, responseLen)

	if responseLen > 0 {
		body := make([]byte, responseLen)
		_, err = conn.Read(body)
		if err != nil {
			fmt.Printf("读取响应体失败: %v\n", err)
			return
		}

		pingRes := &dnfv1.PingResponse{}
		err = proto.Unmarshal(body, pingRes)
		if err != nil {
			fmt.Printf("解析PING响应失败: %v\n", err)
			return
		}

		fmt.Printf("PING响应解析成功: error=%d, responsetime=%d\n", pingRes.Error, pingRes.Responsetime)
	} else {
		fmt.Println("PING响应体为空")
	}

	fmt.Println("PING测试完成")
}
