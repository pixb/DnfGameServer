package tests

import (
	"encoding/binary"
	"fmt"
	"net"
	"testing"
	"time"

	v1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/stretchr/testify/suite"
	"google.golang.org/protobuf/encoding/protojson"
)

// GuildTCPTestSuite 公会系统TCP测试套件
type GuildTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *GuildTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *GuildTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *GuildTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *GuildTCPTestSuite) sendMessage(message []byte) error {
	if s.socket == nil {
		return fmt.Errorf("socket not connected")
	}

	// 编码消息长度（使用大端序，与BinaryCodec一致）
	length := len(message)
	lengthBytes := make([]byte, 2)
	binary.BigEndian.PutUint16(lengthBytes, uint16(length))

	// 发送长度和消息
	buffer := append(lengthBytes, message...)
	_, err := s.socket.Write(buffer)
	return err
}

// receiveMessage 从TCP服务器接收消息
func (s *GuildTCPTestSuite) receiveMessage() ([]byte, error) {
	if s.socket == nil {
		return nil, fmt.Errorf("socket not connected")
	}

	// 读取消息长度（使用大端序，与BinaryCodec一致）
	lengthBytes := make([]byte, 2)
	_, err := s.socket.Read(lengthBytes)
	if err != nil {
		return nil, err
	}

	length := binary.BigEndian.Uint16(lengthBytes)
	if length == 0 {
		return nil, nil
	}

	// 读取消息体
	message := make([]byte, length)
	_, err = s.socket.Read(message)
	if err != nil {
		return nil, err
	}

	return message, nil
}

// TestTCPCreateGuild 测试创建公会（TCP）
func (s *GuildTCPTestSuite) TestTCPCreateGuild() {
	fmt.Println("========== TC001: 创建公会 ==========")
	// 步骤1: 建立TCP连接
	fmt.Println("\n步骤1: 建立TCP连接")
	err := s.connectTCP()
	s.NoError(err)
	s.NotNil(s.socket)
	if s.socket == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	fmt.Println("TCP连接建立成功")

	// 步骤2: 构造创建公会请求
	fmt.Println("\n步骤2: 构造创建公会请求")
	createGuildRequest := &v1.CreateGuildRequest{
		Name: "测试公会", // 公会名称
	}
	fmt.Println("CreateGuildRequest对象创建成功")

	// 步骤3: 编码创建公会请求
	fmt.Println("\n步骤3: 编码创建公会请求")
	createGuildRequestJSON, err := protojson.Marshal(createGuildRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(createGuildRequestJSON))

	// 步骤4: 发送创建公会请求
	fmt.Println("\n步骤4: 发送创建公会请求")
	createGuildMessage := append([]byte("CREATE_GUILD:"), createGuildRequestJSON...)
	err = s.sendMessage(createGuildMessage)
	s.NoError(err)
	fmt.Println("创建公会请求发送成功")

	// 步骤5: 接收创建公会响应
	fmt.Println("\n步骤5: 接收创建公会响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证创建公会响应
	fmt.Println("\n步骤6: 验证创建公会响应")
	s.NotEmpty(response)
	fmt.Printf("Create guild response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPJoinGuild 测试加入公会（TCP）
func (s *GuildTCPTestSuite) TestTCPJoinGuild() {
	fmt.Println("========== TC002: 加入公会 ==========")
	// 步骤1: 建立TCP连接
	fmt.Println("\n步骤1: 建立TCP连接")
	err := s.connectTCP()
	s.NoError(err)
	s.NotNil(s.socket)
	if s.socket == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	fmt.Println("TCP连接建立成功")

	// 步骤2: 构造加入公会请求
	fmt.Println("\n步骤2: 构造加入公会请求")
	joinGuildRequest := &v1.JoinGuildRequest{
		GuildId: 1, // 公会ID
	}
	fmt.Println("JoinGuildRequest对象创建成功")

	// 步骤3: 编码加入公会请求
	fmt.Println("\n步骤3: 编码加入公会请求")
	joinGuildRequestJSON, err := protojson.Marshal(joinGuildRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(joinGuildRequestJSON))

	// 步骤4: 发送加入公会请求
	fmt.Println("\n步骤4: 发送加入公会请求")
	joinGuildMessage := append([]byte("JOIN_GUILD:"), joinGuildRequestJSON...)
	err = s.sendMessage(joinGuildMessage)
	s.NoError(err)
	fmt.Println("加入公会请求发送成功")

	// 步骤5: 接收加入公会响应
	fmt.Println("\n步骤5: 接收加入公会响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证加入公会响应
	fmt.Println("\n步骤6: 验证加入公会响应")
	s.NotEmpty(response)
	fmt.Printf("Join guild response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPLeaveGuild 测试退出公会（TCP）
func (s *GuildTCPTestSuite) TestTCPLeaveGuild() {
	fmt.Println("========== TC003: 退出公会 ==========")
	// 步骤1: 建立TCP连接
	fmt.Println("\n步骤1: 建立TCP连接")
	err := s.connectTCP()
	s.NoError(err)
	s.NotNil(s.socket)
	if s.socket == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	fmt.Println("TCP连接建立成功")

	// 步骤2: 构造退出公会请求
	fmt.Println("\n步骤2: 构造退出公会请求")
	leaveGuildRequest := &v1.LeaveGuildRequest{}
	fmt.Println("LeaveGuildRequest对象创建成功")

	// 步骤3: 编码退出公会请求
	fmt.Println("\n步骤3: 编码退出公会请求")
	leaveGuildRequestJSON, err := protojson.Marshal(leaveGuildRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(leaveGuildRequestJSON))

	// 步骤4: 发送退出公会请求
	fmt.Println("\n步骤4: 发送退出公会请求")
	leaveGuildMessage := append([]byte("LEAVE_GUILD:"), leaveGuildRequestJSON...)
	err = s.sendMessage(leaveGuildMessage)
	s.NoError(err)
	fmt.Println("退出公会请求发送成功")

	// 步骤5: 接收退出公会响应
	fmt.Println("\n步骤5: 接收退出公会响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证退出公会响应
	fmt.Println("\n步骤6: 验证退出公会响应")
	s.NotEmpty(response)
	fmt.Printf("Leave guild response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPGetGuildInfo 测试获取公会信息（TCP）
func (s *GuildTCPTestSuite) TestTCPGetGuildInfo() {
	fmt.Println("========== TC004: 获取公会信息 ==========")
	// 步骤1: 建立TCP连接
	fmt.Println("\n步骤1: 建立TCP连接")
	err := s.connectTCP()
	s.NoError(err)
	s.NotNil(s.socket)
	if s.socket == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	fmt.Println("TCP连接建立成功")

	// 步骤2: 构造获取公会信息请求
	fmt.Println("\n步骤2: 构造获取公会信息请求")
	getGuildInfoRequest := &v1.GetGuildInfoRequest{}
	fmt.Println("GetGuildInfoRequest对象创建成功")

	// 步骤3: 编码获取公会信息请求
	fmt.Println("\n步骤3: 编码获取公会信息请求")
	getGuildInfoRequestJSON, err := protojson.Marshal(getGuildInfoRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getGuildInfoRequestJSON))

	// 步骤4: 发送获取公会信息请求
	fmt.Println("\n步骤4: 发送获取公会信息请求")
	getGuildInfoMessage := append([]byte("GET_GUILD_INFO:"), getGuildInfoRequestJSON...)
	err = s.sendMessage(getGuildInfoMessage)
	s.NoError(err)
	fmt.Println("获取公会信息请求发送成功")

	// 步骤5: 接收获取公会信息响应
	fmt.Println("\n步骤5: 接收获取公会信息响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证获取公会信息响应
	fmt.Println("\n步骤6: 验证获取公会信息响应")
	s.NotEmpty(response)
	fmt.Printf("Get guild info response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPGuildDonate 测试公会捐赠（TCP）
func (s *GuildTCPTestSuite) TestTCPGuildDonate() {
	fmt.Println("========== TC005: 公会捐赠 ==========")
	// 步骤1: 建立TCP连接
	fmt.Println("\n步骤1: 建立TCP连接")
	err := s.connectTCP()
	s.NoError(err)
	s.NotNil(s.socket)
	if s.socket == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	fmt.Println("TCP连接建立成功")

	// 步骤2: 构造公会捐赠请求
	fmt.Println("\n步骤2: 构造公会捐赠请求")
	guildDonateRequest := &v1.GuildDonateRequest{
		DonateType: 1, // 1:金币 2:点券 3:物品
		Amount:     100, // 捐赠数量
	}
	fmt.Println("GuildDonateRequest对象创建成功")

	// 步骤3: 编码公会捐赠请求
	fmt.Println("\n步骤3: 编码公会捐赠请求")
	guildDonateRequestJSON, err := protojson.Marshal(guildDonateRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(guildDonateRequestJSON))

	// 步骤4: 发送公会捐赠请求
	fmt.Println("\n步骤4: 发送公会捐赠请求")
	guildDonateMessage := append([]byte("GUILD_DONATE:"), guildDonateRequestJSON...)
	err = s.sendMessage(guildDonateMessage)
	s.NoError(err)
	fmt.Println("公会捐赠请求发送成功")

	// 步骤5: 接收公会捐赠响应
	fmt.Println("\n步骤5: 接收公会捐赠响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证公会捐赠响应
	fmt.Println("\n步骤6: 验证公会捐赠响应")
	s.NotEmpty(response)
	fmt.Printf("Guild donate response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestGuildTCPSuite 测试公会系统TCP测试套件
func TestGuildTCPSuite(t *testing.T) {
	suite.Run(t, new(GuildTCPTestSuite))
}