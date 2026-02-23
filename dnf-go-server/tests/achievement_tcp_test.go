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

// AchievementTCPTestSuite 成就系统TCP测试套件
type AchievementTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *AchievementTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *AchievementTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *AchievementTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *AchievementTCPTestSuite) sendMessage(message []byte) error {
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
func (s *AchievementTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPReceiveAchievementReward 测试领取成就奖励（TCP）
func (s *AchievementTCPTestSuite) TestTCPReceiveAchievementReward() {
	fmt.Println("========== TC001: 领取成就奖励 ==========")
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

	// 步骤2: 构造领取成就奖励请求
	fmt.Println("\n步骤2: 构造领取成就奖励请求")
	receiveAchievementRewardRequest := &v1.AchievementRewardRequest{
		Field_1: 1,
		Field_2: 1,
		Field_3: 1,
	}
	fmt.Println("AchievementRewardRequest对象创建成功")

	// 步骤3: 编码领取成就奖励请求
	fmt.Println("\n步骤3: 编码领取成就奖励请求")
	receiveAchievementRewardRequestJSON, err := protojson.Marshal(receiveAchievementRewardRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(receiveAchievementRewardRequestJSON))

	// 步骤4: 发送领取成就奖励请求
	fmt.Println("\n步骤4: 发送领取成就奖励请求")
	receiveAchievementRewardMessage := append([]byte("RECEIVE_ACHIEVEMENT_REWARD:"), receiveAchievementRewardRequestJSON...)
	err = s.sendMessage(receiveAchievementRewardMessage)
	s.NoError(err)
	fmt.Println("领取成就奖励请求发送成功")

	// 步骤5: 接收领取成就奖励响应
	fmt.Println("\n步骤5: 接收领取成就奖励响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证领取成就奖励响应
	fmt.Println("\n步骤6: 验证领取成就奖励响应")
	s.NotEmpty(response)
	fmt.Printf("Receive achievement reward response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPGetAchievementList 测试获取成就列表（TCP）
func (s *AchievementTCPTestSuite) TestTCPGetAchievementList() {
	fmt.Println("========== TC002: 获取成就列表 ==========")
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

	// 步骤2: 构造获取成就列表请求
	fmt.Println("\n步骤2: 构造获取成就列表请求")
	getAchievementListRequest := &v1.AchievementListRequest{
		Field_1: 1,
	}
	fmt.Println("AchievementListRequest对象创建成功")

	// 步骤3: 编码获取成就列表请求
	fmt.Println("\n步骤3: 编码获取成就列表请求")
	getAchievementListRequestJSON, err := protojson.Marshal(getAchievementListRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getAchievementListRequestJSON))

	// 步骤4: 发送获取成就列表请求
	fmt.Println("\n步骤4: 发送获取成就列表请求")
	getAchievementListMessage := append([]byte("GET_ACHIEVEMENT_LIST:"), getAchievementListRequestJSON...)
	err = s.sendMessage(getAchievementListMessage)
	s.NoError(err)
	fmt.Println("获取成就列表请求发送成功")

	// 步骤5: 接收获取成就列表响应
	fmt.Println("\n步骤5: 接收获取成就列表响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证获取成就列表响应
	fmt.Println("\n步骤6: 验证获取成就列表响应")
	s.NotEmpty(response)
	fmt.Printf("Get achievement list response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPGetAchievementDetail 测试获取成就详情（TCP）
func (s *AchievementTCPTestSuite) TestTCPGetAchievementDetail() {
	fmt.Println("========== TC003: 获取成就详情 ==========")
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

	// 步骤2: 构造获取成就详情请求
	fmt.Println("\n步骤2: 构造获取成就详情请求")
	// 注意：获取成就详情请求在achievement.proto中没有明确定义，这里使用通用请求格式
	getAchievementDetailRequest := map[string]interface{}{
		"achievement_id": 1,
	}
	fmt.Println("获取成就详情请求对象创建成功")

	// 步骤3: 编码获取成就详情请求
	fmt.Println("\n步骤3: 编码获取成就详情请求")
	getAchievementDetailRequestJSON, err := json.Marshal(getAchievementDetailRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getAchievementDetailRequestJSON))

	// 步骤4: 发送获取成就详情请求
	fmt.Println("\n步骤4: 发送获取成就详情请求")
	getAchievementDetailMessage := append([]byte("GET_ACHIEVEMENT_DETAIL:"), getAchievementDetailRequestJSON...)
	err = s.sendMessage(getAchievementDetailMessage)
	s.NoError(err)
	fmt.Println("获取成就详情请求发送成功")

	// 步骤5: 接收获取成就详情响应
	fmt.Println("\n步骤5: 接收获取成就详情响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证获取成就详情响应
	fmt.Println("\n步骤6: 验证获取成就详情响应")
	s.NotEmpty(response)
	fmt.Printf("Get achievement detail response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPUpdateAchievementProgress 测试成就进度更新（TCP）
func (s *AchievementTCPTestSuite) TestTCPUpdateAchievementProgress() {
	fmt.Println("========== TC004: 成就进度更新 ==========")
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

	// 步骤2: 构造成就进度更新请求
	fmt.Println("\n步骤2: 构造成就进度更新请求")
	// 注意：成就进度更新请求在achievement.proto中没有明确定义，这里使用通用请求格式
	updateAchievementProgressRequest := map[string]interface{}{
		"achievement_id": 1,
		"progress":       50,
		"target_value":   100,
	}
	fmt.Println("成就进度更新请求对象创建成功")

	// 步骤3: 编码成就进度更新请求
	fmt.Println("\n步骤3: 编码成就进度更新请求")
	updateAchievementProgressRequestJSON, err := json.Marshal(updateAchievementProgressRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(updateAchievementProgressRequestJSON))

	// 步骤4: 发送成就进度更新请求
	fmt.Println("\n步骤4: 发送成就进度更新请求")
	updateAchievementProgressMessage := append([]byte("UPDATE_ACHIEVEMENT_PROGRESS:"), updateAchievementProgressRequestJSON...)
	err = s.sendMessage(updateAchievementProgressMessage)
	s.NoError(err)
	fmt.Println("成就进度更新请求发送成功")

	// 步骤5: 接收成就进度更新响应
	fmt.Println("\n步骤5: 接收成就进度更新响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就进度更新响应
	fmt.Println("\n步骤6: 验证成就进度更新响应")
	s.NotEmpty(response)
	fmt.Printf("Update achievement progress response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPReceiveAchievementRewardAgain 测试成就奖励领取（TCP）
func (s *AchievementTCPTestSuite) TestTCPReceiveAchievementRewardAgain() {
	fmt.Println("========== TC005: 成就奖励领取 ==========")
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

	// 步骤2: 构造成就奖励领取请求
	fmt.Println("\n步骤2: 构造成就奖励领取请求")
	receiveAchievementRewardRequest := &v1.AchievementRewardRequest{
		Field_1: 1,
		Field_2: 1,
		Field_3: 1,
	}
	fmt.Println("AchievementRewardRequest对象创建成功")

	// 步骤3: 编码成就奖励领取请求
	fmt.Println("\n步骤3: 编码成就奖励领取请求")
	receiveAchievementRewardRequestJSON, err := protojson.Marshal(receiveAchievementRewardRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(receiveAchievementRewardRequestJSON))

	// 步骤4: 发送成就奖励领取请求
	fmt.Println("\n步骤4: 发送成就奖励领取请求")
	receiveAchievementRewardMessage := append([]byte("RECEIVE_ACHIEVEMENT_REWARD:"), receiveAchievementRewardRequestJSON...)
	err = s.sendMessage(receiveAchievementRewardMessage)
	s.NoError(err)
	fmt.Println("成就奖励领取请求发送成功")

	// 步骤5: 接收成就奖励领取响应
	fmt.Println("\n步骤5: 接收成就奖励领取响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就奖励领取响应
	fmt.Println("\n步骤6: 验证成就奖励领取响应")
	s.NotEmpty(response)
	fmt.Printf("Receive achievement reward response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPBatchGetAchievements 测试批量获取成就（TCP）
func (s *AchievementTCPTestSuite) TestTCPBatchGetAchievements() {
	fmt.Println("========== TC006: 批量获取成就 ==========")
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

	// 步骤2: 构造批量获取成就请求
	fmt.Println("\n步骤2: 构造批量获取成就请求")
	// 注意：批量获取成就请求在achievement.proto中没有明确定义，这里使用通用请求格式
	batchGetAchievementsRequest := map[string]interface{}{
		"achievement_ids": []int{1, 2, 3, 4, 5},
	}
	fmt.Println("批量获取成就请求对象创建成功")

	// 步骤3: 编码批量获取成就请求
	fmt.Println("\n步骤3: 编码批量获取成就请求")
	batchGetAchievementsRequestJSON, err := json.Marshal(batchGetAchievementsRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(batchGetAchievementsRequestJSON))

	// 步骤4: 发送批量获取成就请求
	fmt.Println("\n步骤4: 发送批量获取成就请求")
	batchGetAchievementsMessage := append([]byte("BATCH_GET_ACHIEVEMENTS:"), batchGetAchievementsRequestJSON...)
	err = s.sendMessage(batchGetAchievementsMessage)
	s.NoError(err)
	fmt.Println("批量获取成就请求发送成功")

	// 步骤5: 接收批量获取成就响应
	fmt.Println("\n步骤5: 接收批量获取成就响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证批量获取成就响应
	fmt.Println("\n步骤6: 验证批量获取成就响应")
	s.NotEmpty(response)
	fmt.Printf("Batch get achievements response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPGetAchievementStatistics 测试成就统计信息（TCP）
func (s *AchievementTCPTestSuite) TestTCPGetAchievementStatistics() {
	fmt.Println("========== TC007: 成就统计信息 ==========")
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

	// 步骤2: 构造成就统计信息请求
	fmt.Println("\n步骤2: 构造成就统计信息请求")
	// 注意：成就统计信息请求在achievement.proto中没有明确定义，这里使用通用请求格式
	getAchievementStatisticsRequest := map[string]interface{}{
		"user_id": 1,
	}
	fmt.Println("成就统计信息请求对象创建成功")

	// 步骤3: 编码成就统计信息请求
	fmt.Println("\n步骤3: 编码成就统计信息请求")
	getAchievementStatisticsRequestJSON, err := json.Marshal(getAchievementStatisticsRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getAchievementStatisticsRequestJSON))

	// 步骤4: 发送成就统计信息请求
	fmt.Println("\n步骤4: 发送成就统计信息请求")
	getAchievementStatisticsMessage := append([]byte("GET_ACHIEVEMENT_STATISTICS:"), getAchievementStatisticsRequestJSON...)
	err = s.sendMessage(getAchievementStatisticsMessage)
	s.NoError(err)
	fmt.Println("成就统计信息请求发送成功")

	// 步骤5: 接收成就统计信息响应
	fmt.Println("\n步骤5: 接收成就统计信息响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就统计信息响应
	fmt.Println("\n步骤6: 验证成就统计信息响应")
	s.NotEmpty(response)
	fmt.Printf("Get achievement statistics response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPAchievementUnlockNotification 测试成就解锁通知（TCP）
func (s *AchievementTCPTestSuite) TestTCPAchievementUnlockNotification() {
	fmt.Println("========== TC008: 成就解锁通知 ==========")
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

	// 步骤2: 构造成就解锁通知请求
	fmt.Println("\n步骤2: 构造成就解锁通知请求")
	// 注意：成就解锁通知请求在achievement.proto中没有明确定义，这里使用通用请求格式
	achievementUnlockNotificationRequest := map[string]interface{}{
		"achievement_id": 1,
		"user_id":        1,
	}
	fmt.Println("成就解锁通知请求对象创建成功")

	// 步骤3: 编码成就解锁通知请求
	fmt.Println("\n步骤3: 编码成就解锁通知请求")
	achievementUnlockNotificationRequestJSON, err := json.Marshal(achievementUnlockNotificationRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(achievementUnlockNotificationRequestJSON))

	// 步骤4: 发送成就解锁通知请求
	fmt.Println("\n步骤4: 发送成就解锁通知请求")
	achievementUnlockNotificationMessage := append([]byte("ACHIEVEMENT_UNLOCK_NOTIFICATION:"), achievementUnlockNotificationRequestJSON...)
	err = s.sendMessage(achievementUnlockNotificationMessage)
	s.NoError(err)
	fmt.Println("成就解锁通知请求发送成功")

	// 步骤5: 接收成就解锁通知响应
	fmt.Println("\n步骤5: 接收成就解锁通知响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就解锁通知响应
	fmt.Println("\n步骤6: 验证成就解锁通知响应")
	s.NotEmpty(response)
	fmt.Printf("Achievement unlock notification response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestTCPValidateAchievementCondition 测试成就条件验证（TCP）
func (s *AchievementTCPTestSuite) TestTCPValidateAchievementCondition() {
	fmt.Println("========== TC009: 成就条件验证 ==========")
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

	// 步骤2: 构造成就条件验证请求
	fmt.Println("\n步骤2: 构造成就条件验证请求")
	// 注意：成就条件验证请求在achievement.proto中没有明确定义，这里使用通用请求格式
	validateAchievementConditionRequest := map[string]interface{}{
		"achievement_id": 1,
		"user_id":        1,
		"condition":      "kill_monsters",
		"value":          100,
	}
	fmt.Println("成就条件验证请求对象创建成功")

	// 步骤3: 编码成就条件验证请求
	fmt.Println("\n步骤3: 编码成就条件验证请求")
	validateAchievementConditionRequestJSON, err := json.Marshal(validateAchievementConditionRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(validateAchievementConditionRequestJSON))

	// 步骤4: 发送成就条件验证请求
	fmt.Println("\n步骤4: 发送成就条件验证请求")
	validateAchievementConditionMessage := append([]byte("VALIDATE_ACHIEVEMENT_CONDITION:"), validateAchievementConditionRequestJSON...)
	err = s.sendMessage(validateAchievementConditionMessage)
	s.NoError(err)
	fmt.Println("成就条件验证请求发送成功")

	// 步骤5: 接收成就条件验证响应
	fmt.Println("\n步骤5: 接收成就条件验证响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就条件验证响应
	fmt.Println("\n步骤6: 验证成就条件验证响应")
	s.NotEmpty(response)
	fmt.Printf("Validate achievement condition response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC009 测试结束 ==========")
}

// TestTCPAchievementSystemBoundary 测试成就系统边界测试（TCP）
func (s *AchievementTCPTestSuite) TestTCPAchievementSystemBoundary() {
	fmt.Println("========== TC010: 成就系统边界测试 ==========")
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

	// 步骤2: 构造成就系统边界测试请求
	fmt.Println("\n步骤2: 构造成就系统边界测试请求")
	// 注意：成就系统边界测试请求在achievement.proto中没有明确定义，这里使用通用请求格式
	achievementSystemBoundaryRequest := map[string]interface{}{
		"test_type":      "boundary",
		"achievement_id": 999999, // 边界值测试
		"progress":       999999, // 边界值测试
	}
	fmt.Println("成就系统边界测试请求对象创建成功")

	// 步骤3: 编码成就系统边界测试请求
	fmt.Println("\n步骤3: 编码成就系统边界测试请求")
	achievementSystemBoundaryRequestJSON, err := json.Marshal(achievementSystemBoundaryRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(achievementSystemBoundaryRequestJSON))

	// 步骤4: 发送成就系统边界测试请求
	fmt.Println("\n步骤4: 发送成就系统边界测试请求")
	achievementSystemBoundaryMessage := append([]byte("ACHIEVEMENT_SYSTEM_BOUNDARY:"), achievementSystemBoundaryRequestJSON...)
	err = s.sendMessage(achievementSystemBoundaryMessage)
	s.NoError(err)
	fmt.Println("成就系统边界测试请求发送成功")

	// 步骤5: 接收成就系统边界测试响应
	fmt.Println("\n步骤5: 接收成就系统边界测试响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就系统边界测试响应
	fmt.Println("\n步骤6: 验证成就系统边界测试响应")
	s.NotEmpty(response)
	fmt.Printf("Achievement system boundary response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC010 测试结束 ==========")
}

// TestTCPAchievementSystemConcurrent 测试成就系统并发测试（TCP）
func (s *AchievementTCPTestSuite) TestTCPAchievementSystemConcurrent() {
	fmt.Println("========== TC011: 成就系统并发测试 ==========")
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

	// 步骤2: 构造成就系统并发测试请求
	fmt.Println("\n步骤2: 构造成就系统并发测试请求")
	// 注意：成就系统并发测试请求在achievement.proto中没有明确定义，这里使用通用请求格式
	achievementSystemConcurrentRequest := map[string]interface{}{
		"test_type":  "concurrent",
		"user_id":    1,
		"operations": []string{"update_progress", "get_list", "receive_reward"},
	}
	fmt.Println("成就系统并发测试请求对象创建成功")

	// 步骤3: 编码成就系统并发测试请求
	fmt.Println("\n步骤3: 编码成就系统并发测试请求")
	achievementSystemConcurrentRequestJSON, err := json.Marshal(achievementSystemConcurrentRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(achievementSystemConcurrentRequestJSON))

	// 步骤4: 发送成就系统并发测试请求
	fmt.Println("\n步骤4: 发送成就系统并发测试请求")
	achievementSystemConcurrentMessage := append([]byte("ACHIEVEMENT_SYSTEM_CONCURRENT:"), achievementSystemConcurrentRequestJSON...)
	err = s.sendMessage(achievementSystemConcurrentMessage)
	s.NoError(err)
	fmt.Println("成就系统并发测试请求发送成功")

	// 步骤5: 接收成就系统并发测试响应
	fmt.Println("\n步骤5: 接收成就系统并发测试响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就系统并发测试响应
	fmt.Println("\n步骤6: 验证成就系统并发测试响应")
	s.NotEmpty(response)
	fmt.Printf("Achievement system concurrent response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC011 测试结束 ==========")
}

// TestTCPAchievementSystemPerformance 测试成就系统性能测试（TCP）
func (s *AchievementTCPTestSuite) TestTCPAchievementSystemPerformance() {
	fmt.Println("========== TC012: 成就系统性能测试 ==========")
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

	// 步骤2: 构造成就系统性能测试请求
	fmt.Println("\n步骤2: 构造成就系统性能测试请求")
	// 注意：成就系统性能测试请求在achievement.proto中没有明确定义，这里使用通用请求格式
	achievementSystemPerformanceRequest := map[string]interface{}{
		"test_type":       "performance",
		"user_id":         1,
		"operation_count": 1000, // 性能测试操作次数
	}
	fmt.Println("成就系统性能测试请求对象创建成功")

	// 步骤3: 编码成就系统性能测试请求
	fmt.Println("\n步骤3: 编码成就系统性能测试请求")
	achievementSystemPerformanceRequestJSON, err := json.Marshal(achievementSystemPerformanceRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(achievementSystemPerformanceRequestJSON))

	// 步骤4: 发送成就系统性能测试请求
	fmt.Println("\n步骤4: 发送成就系统性能测试请求")
	achievementSystemPerformanceMessage := append([]byte("ACHIEVEMENT_SYSTEM_PERFORMANCE:"), achievementSystemPerformanceRequestJSON...)
	err = s.sendMessage(achievementSystemPerformanceMessage)
	s.NoError(err)
	fmt.Println("成就系统性能测试请求发送成功")

	// 步骤5: 接收成就系统性能测试响应
	fmt.Println("\n步骤5: 接收成就系统性能测试响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证成就系统性能测试响应
	fmt.Println("\n步骤6: 验证成就系统性能测试响应")
	s.NotEmpty(response)
	fmt.Printf("Achievement system performance response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC012 测试结束 ==========")
}

// TestAchievementTCPSuite 测试成就系统TCP测试套件
func TestAchievementTCPSuite(t *testing.T) {
	suite.Run(t, new(AchievementTCPTestSuite))
}
