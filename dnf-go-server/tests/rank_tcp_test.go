package tests

import (
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
	"testing"
	"time"

	v1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/stretchr/testify/suite"
	"google.golang.org/protobuf/encoding/protojson"
)

// RankTCPTestSuite 排名系统TCP测试套件
type RankTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *RankTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *RankTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *RankTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *RankTCPTestSuite) sendMessage(message []byte) error {
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
func (s *RankTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPQueryPersonalRank 测试查询个人排名（TCP）
func (s *RankTCPTestSuite) TestTCPQueryPersonalRank() {
	fmt.Println("========== TC001: 查询个人排名 ==========")
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

	// 步骤2: 构造查询个人排名请求
	fmt.Println("\n步骤2: 构造查询个人排名请求")
	pvpRankingRequest := &v1.PvpRankingRequest{
		MatchType: 1, // 匹配类型
		Page:      1, // 页码
		PageSize:  10, // 每页大小
	}
	fmt.Println("PvpRankingRequest对象创建成功")

	// 步骤3: 编码查询个人排名请求
	fmt.Println("\n步骤3: 编码查询个人排名请求")
	pvpRankingRequestJSON, err := protojson.Marshal(pvpRankingRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(pvpRankingRequestJSON))

	// 步骤4: 发送查询个人排名请求
	fmt.Println("\n步骤4: 发送查询个人排名请求")
	pvpRankingMessage := append([]byte("QUERY_PERSONAL_RANK:"), pvpRankingRequestJSON...)
	err = s.sendMessage(pvpRankingMessage)
	s.NoError(err)
	fmt.Println("查询个人排名请求发送成功")

	// 步骤5: 接收查询个人排名响应
	fmt.Println("\n步骤5: 接收查询个人排名响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证查询个人排名响应
	fmt.Println("\n步骤6: 验证查询个人排名响应")
	s.NotEmpty(response)
	fmt.Printf("Query personal rank response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPQueryMyRank 测试查询我的排名（TCP）
func (s *RankTCPTestSuite) TestTCPQueryMyRank() {
	fmt.Println("========== TC002: 查询我的排名 ==========")
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

	// 步骤2: 构造查询我的排名请求
	fmt.Println("\n步骤2: 构造查询我的排名请求")
	// 注意：查询我的排名请求在pk.proto中没有明确定义，这里使用通用请求格式
	queryMyRankRequest := map[string]interface{}{
		"match_type": 1, // 匹配类型
	}
	fmt.Println("查询我的排名请求对象创建成功")

	// 步骤3: 编码查询我的排名请求
	fmt.Println("\n步骤3: 编码查询我的排名请求")
	queryMyRankRequestJSON, err := json.Marshal(queryMyRankRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryMyRankRequestJSON))

	// 步骤4: 发送查询我的排名请求
	fmt.Println("\n步骤4: 发送查询我的排名请求")
	queryMyRankMessage := append([]byte("QUERY_MY_RANK:"), queryMyRankRequestJSON...)
	err = s.sendMessage(queryMyRankMessage)
	s.NoError(err)
	fmt.Println("查询我的排名请求发送成功")

	// 步骤5: 接收查询我的排名响应
	fmt.Println("\n步骤5: 接收查询我的排名响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证查询我的排名响应
	fmt.Println("\n步骤6: 验证查询我的排名响应")
	s.NotEmpty(response)
	fmt.Printf("Query my rank response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPQueryFriendRank 测试查询好友排名（TCP）
func (s *RankTCPTestSuite) TestTCPQueryFriendRank() {
	fmt.Println("========== TC003: 查询好友排名 ==========")
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

	// 步骤2: 构造查询好友排名请求
	fmt.Println("\n步骤2: 构造查询好友排名请求")
	// 注意：查询好友排名请求在pk.proto中没有明确定义，这里使用通用请求格式
	queryFriendRankRequest := map[string]interface{}{
		"match_type": 1, // 匹配类型
	}
	fmt.Println("查询好友排名请求对象创建成功")

	// 步骤3: 编码查询好友排名请求
	fmt.Println("\n步骤3: 编码查询好友排名请求")
	queryFriendRankRequestJSON, err := json.Marshal(queryFriendRankRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryFriendRankRequestJSON))

	// 步骤4: 发送查询好友排名请求
	fmt.Println("\n步骤4: 发送查询好友排名请求")
	queryFriendRankMessage := append([]byte("QUERY_FRIEND_RANK:"), queryFriendRankRequestJSON...)
	err = s.sendMessage(queryFriendRankMessage)
	s.NoError(err)
	fmt.Println("查询好友排名请求发送成功")

	// 步骤5: 接收查询好友排名响应
	fmt.Println("\n步骤5: 接收查询好友排名响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证查询好友排名响应
	fmt.Println("\n步骤6: 验证查询好友排名响应")
	s.NotEmpty(response)
	fmt.Printf("Query friend rank response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPQueryMyTeamRank 测试查询我的队伍排名（TCP）
func (s *RankTCPTestSuite) TestTCPQueryMyTeamRank() {
	fmt.Println("========== TC004: 查询我的队伍排名 ==========")
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

	// 步骤2: 构造查询我的队伍排名请求
	fmt.Println("\n步骤2: 构造查询我的队伍排名请求")
	// 注意：查询我的队伍排名请求在pk.proto中没有明确定义，这里使用通用请求格式
	queryMyTeamRankRequest := map[string]interface{}{
		"match_type": 1, // 匹配类型
	}
	fmt.Println("查询我的队伍排名请求对象创建成功")

	// 步骤3: 编码查询我的队伍排名请求
	fmt.Println("\n步骤3: 编码查询我的队伍排名请求")
	queryMyTeamRankRequestJSON, err := json.Marshal(queryMyTeamRankRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryMyTeamRankRequestJSON))

	// 步骤4: 发送查询我的队伍排名请求
	fmt.Println("\n步骤4: 发送查询我的队伍排名请求")
	queryMyTeamRankMessage := append([]byte("QUERY_MY_TEAM_RANK:"), queryMyTeamRankRequestJSON...)
	err = s.sendMessage(queryMyTeamRankMessage)
	s.NoError(err)
	fmt.Println("查询我的队伍排名请求发送成功")

	// 步骤5: 接收查询我的队伍排名响应
	fmt.Println("\n步骤5: 接收查询我的队伍排名响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证查询我的队伍排名响应
	fmt.Println("\n步骤6: 验证查询我的队伍排名响应")
	s.NotEmpty(response)
	fmt.Printf("Query my team rank response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestRankTCPSuite 测试排名系统TCP测试套件
func TestRankTCPSuite(t *testing.T) {
	suite.Run(t, new(RankTCPTestSuite))
}