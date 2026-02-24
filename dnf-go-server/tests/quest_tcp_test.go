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

// QuestTCPTestSuite 任务系统TCP测试套件
type QuestTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *QuestTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *QuestTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *QuestTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *QuestTCPTestSuite) sendMessage(message []byte) error {
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
func (s *QuestTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPGetQuestList 测试获取任务列表（TCP）
func (s *QuestTCPTestSuite) TestTCPGetQuestList() {
	fmt.Println("========== TC001: 获取任务列表 ==========")
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

	// 步骤2: 构造获取任务列表请求
	fmt.Println("\n步骤2: 构造获取任务列表请求")
	getQuestListRequest := &v1.GetQuestListRequest{
		QuestType: v1.QuestType_MAIN, // 1:主线任务 2:支线任务 3:日常任务 4:活动任务
	}
	fmt.Println("GetQuestListRequest对象创建成功")

	// 步骤3: 编码获取任务列表请求
	fmt.Println("\n步骤3: 编码获取任务列表请求")
	getQuestListRequestJSON, err := protojson.Marshal(getQuestListRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getQuestListRequestJSON))

	// 步骤4: 发送获取任务列表请求
	fmt.Println("\n步骤4: 发送获取任务列表请求")
	getQuestListMessage := append([]byte("GET_QUEST_LIST:"), getQuestListRequestJSON...)
	err = s.sendMessage(getQuestListMessage)
	s.NoError(err)
	fmt.Println("获取任务列表请求发送成功")

	// 步骤5: 接收获取任务列表响应
	fmt.Println("\n步骤5: 接收获取任务列表响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证获取任务列表响应
	fmt.Println("\n步骤6: 验证获取任务列表响应")
	s.NotEmpty(response)
	fmt.Printf("Get quest list response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPAcceptQuest 测试接受任务（TCP）
func (s *QuestTCPTestSuite) TestTCPAcceptQuest() {
	fmt.Println("========== TC002: 接受任务 ==========")
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

	// 步骤2: 构造接受任务请求
	fmt.Println("\n步骤2: 构造接受任务请求")
	acceptQuestRequest := &v1.AcceptQuestRequest{
		QuestId: 1, // 任务ID
	}
	fmt.Println("AcceptQuestRequest对象创建成功")

	// 步骤3: 编码接受任务请求
	fmt.Println("\n步骤3: 编码接受任务请求")
	acceptQuestRequestJSON, err := protojson.Marshal(acceptQuestRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(acceptQuestRequestJSON))

	// 步骤4: 发送接受任务请求
	fmt.Println("\n步骤4: 发送接受任务请求")
	acceptQuestMessage := append([]byte("ACCEPT_QUEST:"), acceptQuestRequestJSON...)
	err = s.sendMessage(acceptQuestMessage)
	s.NoError(err)
	fmt.Println("接受任务请求发送成功")

	// 步骤5: 接收接受任务响应
	fmt.Println("\n步骤5: 接收接受任务响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证接受任务响应
	fmt.Println("\n步骤6: 验证接受任务响应")
	s.NotEmpty(response)
	fmt.Printf("Accept quest response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPCompleteQuest 测试完成任务（TCP）
func (s *QuestTCPTestSuite) TestTCPCompleteQuest() {
	fmt.Println("========== TC003: 完成任务 ==========")
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

	// 步骤2: 构造完成任务请求
	fmt.Println("\n步骤2: 构造完成任务请求")
	completeQuestRequest := &v1.CompleteQuestRequest{
		QuestId: 1, // 任务ID
	}
	fmt.Println("CompleteQuestRequest对象创建成功")

	// 步骤3: 编码完成任务请求
	fmt.Println("\n步骤3: 编码完成任务请求")
	completeQuestRequestJSON, err := protojson.Marshal(completeQuestRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(completeQuestRequestJSON))

	// 步骤4: 发送完成任务请求
	fmt.Println("\n步骤4: 发送完成任务请求")
	completeQuestMessage := append([]byte("COMPLETE_QUEST:"), completeQuestRequestJSON...)
	err = s.sendMessage(completeQuestMessage)
	s.NoError(err)
	fmt.Println("完成任务请求发送成功")

	// 步骤5: 接收完成任务响应
	fmt.Println("\n步骤5: 接收完成任务响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证完成任务响应
	fmt.Println("\n步骤6: 验证完成任务响应")
	s.NotEmpty(response)
	fmt.Printf("Complete quest response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPGetQuestReward 测试领取任务奖励（TCP）
func (s *QuestTCPTestSuite) TestTCPGetQuestReward() {
	fmt.Println("========== TC004: 领取任务奖励 ==========")
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

	// 步骤2: 构造领取任务奖励请求
	fmt.Println("\n步骤2: 构造领取任务奖励请求")
	getQuestRewardRequest := &v1.GetQuestRewardRequest{
		QuestId: 1, // 任务ID
	}
	fmt.Println("GetQuestRewardRequest对象创建成功")

	// 步骤3: 编码领取任务奖励请求
	fmt.Println("\n步骤3: 编码领取任务奖励请求")
	getQuestRewardRequestJSON, err := protojson.Marshal(getQuestRewardRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getQuestRewardRequestJSON))

	// 步骤4: 发送领取任务奖励请求
	fmt.Println("\n步骤4: 发送领取任务奖励请求")
	getQuestRewardMessage := append([]byte("GET_QUEST_REWARD:"), getQuestRewardRequestJSON...)
	err = s.sendMessage(getQuestRewardMessage)
	s.NoError(err)
	fmt.Println("领取任务奖励请求发送成功")

	// 步骤5: 接收领取任务奖励响应
	fmt.Println("\n步骤5: 接收领取任务奖励响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证领取任务奖励响应
	fmt.Println("\n步骤6: 验证领取任务奖励响应")
	s.NotEmpty(response)
	fmt.Printf("Get quest reward response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPAbandonQuest 测试放弃任务（TCP）
func (s *QuestTCPTestSuite) TestTCPAbandonQuest() {
	fmt.Println("========== TC005: 放弃任务 ==========")
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

	// 步骤2: 构造放弃任务请求
	fmt.Println("\n步骤2: 构造放弃任务请求")
	abandonQuestRequest := &v1.AbandonQuestRequest{
		QuestId: 1, // 任务ID
	}
	fmt.Println("AbandonQuestRequest对象创建成功")

	// 步骤3: 编码放弃任务请求
	fmt.Println("\n步骤3: 编码放弃任务请求")
	abandonQuestRequestJSON, err := protojson.Marshal(abandonQuestRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(abandonQuestRequestJSON))

	// 步骤4: 发送放弃任务请求
	fmt.Println("\n步骤4: 发送放弃任务请求")
	abandonQuestMessage := append([]byte("ABANDON_QUEST:"), abandonQuestRequestJSON...)
	err = s.sendMessage(abandonQuestMessage)
	s.NoError(err)
	fmt.Println("放弃任务请求发送成功")

	// 步骤5: 接收放弃任务响应
	fmt.Println("\n步骤5: 接收放弃任务响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证放弃任务响应
	fmt.Println("\n步骤6: 验证放弃任务响应")
	s.NotEmpty(response)
	fmt.Printf("Abandon quest response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestQuestTCPSuite 测试任务系统TCP测试套件
func TestQuestTCPSuite(t *testing.T) {
	suite.Run(t, new(QuestTCPTestSuite))
}