package tests

import (
	"encoding/binary"
	"fmt"
	"net"
	"time"
)

// main 主函数
func main() {
	// 连接到TCP服务器
	addr := "127.0.0.1:9000"
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		fmt.Printf("连接失败: %v\n", err)
		return
	}
	defer conn.Close()

	fmt.Println("连接成功")

	// 准备消息
	message := []byte("LOGIN:test123")
	length := len(message)

	// 构建完整的消息（长度字段 + 消息体）
	lengthBytes := make([]byte, 2)
	binary.BigEndian.PutUint16(lengthBytes, uint16(length))
	buffer := append(lengthBytes, message...)

	fmt.Printf("发送消息长度: %d\n", length)
	fmt.Printf("发送消息内容: %s\n", string(message))
	fmt.Printf("发送消息总长度: %d\n", len(buffer))

	// 发送完整的消息
	_, err = conn.Write(buffer)
	if err != nil {
		fmt.Printf("发送消息失败: %v\n", err)
		return
	}

	fmt.Println("消息发送成功")

	// 读取响应
	conn.SetReadDeadline(time.Now().Add(5 * time.Second))
	response := make([]byte, 1024)
	n, err := conn.Read(response)
	if err != nil {
		fmt.Printf("读取响应失败: %v\n", err)
		return
	}

	fmt.Printf("收到响应长度: %d\n", n)
	fmt.Printf("收到响应内容: %s\n", string(response[:n]))
}
