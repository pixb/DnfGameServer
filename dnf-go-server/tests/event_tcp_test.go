package tests

import (
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

// EventTCPTestSuite 事件系统TCP测试套件
type EventTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *EventTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *EventTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *EventTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *EventTCPTestSuite) sendMessage(message []byte) error {
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
func (s *EventTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPTriggerEvent 测试事件触发（TCP）
func (s *EventTCPTestSuite) TestTCPTriggerEvent() {
	fmt.Println("========== TC001: 事件触发 ==========")
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

	// 步骤2: 构造事件触发请求
	fmt.Println("\n步骤2: 构造事件触发请求")
	triggerEventRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
		"params":   map[string]interface{}{"key": "value"}, // 事件参数
	}
	fmt.Println("事件触发请求对象创建成功")

	// 步骤3: 编码事件触发请求
	fmt.Println("\n步骤3: 编码事件触发请求")
	triggerEventRequestJSON, err := json.Marshal(triggerEventRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(triggerEventRequestJSON))

	// 步骤4: 发送事件触发请求
	fmt.Println("\n步骤4: 发送事件触发请求")
	triggerEventMessage := append([]byte("TRIGGER_EVENT:"), triggerEventRequestJSON...)
	err = s.sendMessage(triggerEventMessage)
	s.NoError(err)
	fmt.Println("事件触发请求发送成功")

	// 步骤5: 接收事件触发响应
	fmt.Println("\n步骤5: 接收事件触发响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件触发响应
	fmt.Println("\n步骤6: 验证事件触发响应")
	s.NotEmpty(response)
	fmt.Printf("Trigger event response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPHandleEvent 测试事件处理（TCP）
func (s *EventTCPTestSuite) TestTCPHandleEvent() {
	fmt.Println("========== TC002: 事件处理 ==========")
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

	// 步骤2: 构造事件处理请求
	fmt.Println("\n步骤2: 构造事件处理请求")
	handleEventRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
		"action":   "process", // 处理动作
	}
	fmt.Println("事件处理请求对象创建成功")

	// 步骤3: 编码事件处理请求
	fmt.Println("\n步骤3: 编码事件处理请求")
	handleEventRequestJSON, err := json.Marshal(handleEventRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(handleEventRequestJSON))

	// 步骤4: 发送事件处理请求
	fmt.Println("\n步骤4: 发送事件处理请求")
	handleEventMessage := append([]byte("HANDLE_EVENT:"), handleEventRequestJSON...)
	err = s.sendMessage(handleEventMessage)
	s.NoError(err)
	fmt.Println("事件处理请求发送成功")

	// 步骤5: 接收事件处理响应
	fmt.Println("\n步骤5: 接收事件处理响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件处理响应
	fmt.Println("\n步骤6: 验证事件处理响应")
	s.NotEmpty(response)
	fmt.Printf("Handle event response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPDistributeEventReward 测试事件奖励发放（TCP）
func (s *EventTCPTestSuite) TestTCPDistributeEventReward() {
	fmt.Println("========== TC003: 事件奖励发放 ==========")
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

	// 步骤2: 构造事件奖励发放请求
	fmt.Println("\n步骤2: 构造事件奖励发放请求")
	distributeEventRewardRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
		"user_id":  1, // 用户ID
		"reward_id": 1, // 奖励ID
	}
	fmt.Println("事件奖励发放请求对象创建成功")

	// 步骤3: 编码事件奖励发放请求
	fmt.Println("\n步骤3: 编码事件奖励发放请求")
	distributeEventRewardRequestJSON, err := json.Marshal(distributeEventRewardRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(distributeEventRewardRequestJSON))

	// 步骤4: 发送事件奖励发放请求
	fmt.Println("\n步骤4: 发送事件奖励发放请求")
	distributeEventRewardMessage := append([]byte("DISTRIBUTE_EVENT_REWARD:"), distributeEventRewardRequestJSON...)
	err = s.sendMessage(distributeEventRewardMessage)
	s.NoError(err)
	fmt.Println("事件奖励发放请求发送成功")

	// 步骤5: 接收事件奖励发放响应
	fmt.Println("\n步骤5: 接收事件奖励发放响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件奖励发放响应
	fmt.Println("\n步骤6: 验证事件奖励发放响应")
	s.NotEmpty(response)
	fmt.Printf("Distribute event reward response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPQueryEventStatus 测试事件状态查询（TCP）
func (s *EventTCPTestSuite) TestTCPQueryEventStatus() {
	fmt.Println("========== TC004: 事件状态查询 ==========")
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

	// 步骤2: 构造事件状态查询请求
	fmt.Println("\n步骤2: 构造事件状态查询请求")
	queryEventStatusRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
	}
	fmt.Println("事件状态查询请求对象创建成功")

	// 步骤3: 编码事件状态查询请求
	fmt.Println("\n步骤3: 编码事件状态查询请求")
	queryEventStatusRequestJSON, err := json.Marshal(queryEventStatusRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryEventStatusRequestJSON))

	// 步骤4: 发送事件状态查询请求
	fmt.Println("\n步骤4: 发送事件状态查询请求")
	queryEventStatusMessage := append([]byte("QUERY_EVENT_STATUS:"), queryEventStatusRequestJSON...)
	err = s.sendMessage(queryEventStatusMessage)
	s.NoError(err)
	fmt.Println("事件状态查询请求发送成功")

	// 步骤5: 接收事件状态查询响应
	fmt.Println("\n步骤5: 接收事件状态查询响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件状态查询响应
	fmt.Println("\n步骤6: 验证事件状态查询响应")
	s.NotEmpty(response)
	fmt.Printf("Query event status response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPValidateEventCompletion 测试事件完成验证（TCP）
func (s *EventTCPTestSuite) TestTCPValidateEventCompletion() {
	fmt.Println("========== TC005: 事件完成验证 ==========")
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

	// 步骤2: 构造事件完成验证请求
	fmt.Println("\n步骤2: 构造事件完成验证请求")
	validateEventCompletionRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
		"user_id":  1, // 用户ID
	}
	fmt.Println("事件完成验证请求对象创建成功")

	// 步骤3: 编码事件完成验证请求
	fmt.Println("\n步骤3: 编码事件完成验证请求")
	validateEventCompletionRequestJSON, err := json.Marshal(validateEventCompletionRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(validateEventCompletionRequestJSON))

	// 步骤4: 发送事件完成验证请求
	fmt.Println("\n步骤4: 发送事件完成验证请求")
	validateEventCompletionMessage := append([]byte("VALIDATE_EVENT_COMPLETION:"), validateEventCompletionRequestJSON...)
	err = s.sendMessage(validateEventCompletionMessage)
	s.NoError(err)
	fmt.Println("事件完成验证请求发送成功")

	// 步骤5: 接收事件完成验证响应
	fmt.Println("\n步骤5: 接收事件完成验证响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件完成验证响应
	fmt.Println("\n步骤6: 验证事件完成验证响应")
	s.NotEmpty(response)
	fmt.Printf("Validate event completion response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPQueryEventList 测试事件列表查询（TCP）
func (s *EventTCPTestSuite) TestTCPQueryEventList() {
	fmt.Println("========== TC006: 事件列表查询 ==========")
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

	// 步骤2: 构造事件列表查询请求
	fmt.Println("\n步骤2: 构造事件列表查询请求")
	queryEventListRequest := map[string]interface{}{
		"page":      1, // 页码
		"page_size": 10, // 每页大小
	}
	fmt.Println("事件列表查询请求对象创建成功")

	// 步骤3: 编码事件列表查询请求
	fmt.Println("\n步骤3: 编码事件列表查询请求")
	queryEventListRequestJSON, err := json.Marshal(queryEventListRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryEventListRequestJSON))

	// 步骤4: 发送事件列表查询请求
	fmt.Println("\n步骤4: 发送事件列表查询请求")
	queryEventListMessage := append([]byte("QUERY_EVENT_LIST:"), queryEventListRequestJSON...)
	err = s.sendMessage(queryEventListMessage)
	s.NoError(err)
	fmt.Println("事件列表查询请求发送成功")

	// 步骤5: 接收事件列表查询响应
	fmt.Println("\n步骤5: 接收事件列表查询响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件列表查询响应
	fmt.Println("\n步骤6: 验证事件列表查询响应")
	s.NotEmpty(response)
	fmt.Printf("Query event list response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPQueryEventDetail 测试事件详情查询（TCP）
func (s *EventTCPTestSuite) TestTCPQueryEventDetail() {
	fmt.Println("========== TC007: 事件详情查询 ==========")
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

	// 步骤2: 构造事件详情查询请求
	fmt.Println("\n步骤2: 构造事件详情查询请求")
	queryEventDetailRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
	}
	fmt.Println("事件详情查询请求对象创建成功")

	// 步骤3: 编码事件详情查询请求
	fmt.Println("\n步骤3: 编码事件详情查询请求")
	queryEventDetailRequestJSON, err := json.Marshal(queryEventDetailRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryEventDetailRequestJSON))

	// 步骤4: 发送事件详情查询请求
	fmt.Println("\n步骤4: 发送事件详情查询请求")
	queryEventDetailMessage := append([]byte("QUERY_EVENT_DETAIL:"), queryEventDetailRequestJSON...)
	err = s.sendMessage(queryEventDetailMessage)
	s.NoError(err)
	fmt.Println("事件详情查询请求发送成功")

	// 步骤5: 接收事件详情查询响应
	fmt.Println("\n步骤5: 接收事件详情查询响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件详情查询响应
	fmt.Println("\n步骤6: 验证事件详情查询响应")
	s.NotEmpty(response)
	fmt.Printf("Query event detail response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPQueryEventProgress 测试事件进度查询（TCP）
func (s *EventTCPTestSuite) TestTCPQueryEventProgress() {
	fmt.Println("========== TC008: 事件进度查询 ==========")
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

	// 步骤2: 构造事件进度查询请求
	fmt.Println("\n步骤2: 构造事件进度查询请求")
	queryEventProgressRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
		"user_id":  1, // 用户ID
	}
	fmt.Println("事件进度查询请求对象创建成功")

	// 步骤3: 编码事件进度查询请求
	fmt.Println("\n步骤3: 编码事件进度查询请求")
	queryEventProgressRequestJSON, err := json.Marshal(queryEventProgressRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryEventProgressRequestJSON))

	// 步骤4: 发送事件进度查询请求
	fmt.Println("\n步骤4: 发送事件进度查询请求")
	queryEventProgressMessage := append([]byte("QUERY_EVENT_PROGRESS:"), queryEventProgressRequestJSON...)
	err = s.sendMessage(queryEventProgressMessage)
	s.NoError(err)
	fmt.Println("事件进度查询请求发送成功")

	// 步骤5: 接收事件进度查询响应
	fmt.Println("\n步骤5: 接收事件进度查询响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件进度查询响应
	fmt.Println("\n步骤6: 验证事件进度查询响应")
	s.NotEmpty(response)
	fmt.Printf("Query event progress response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestTCPReceiveEventReward 测试事件奖励领取（TCP）
func (s *EventTCPTestSuite) TestTCPReceiveEventReward() {
	fmt.Println("========== TC009: 事件奖励领取 ==========")
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

	// 步骤2: 构造事件奖励领取请求
	fmt.Println("\n步骤2: 构造事件奖励领取请求")
	receiveEventRewardRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
		"user_id":  1, // 用户ID
		"reward_id": 1, // 奖励ID
	}
	fmt.Println("事件奖励领取请求对象创建成功")

	// 步骤3: 编码事件奖励领取请求
	fmt.Println("\n步骤3: 编码事件奖励领取请求")
	receiveEventRewardRequestJSON, err := json.Marshal(receiveEventRewardRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(receiveEventRewardRequestJSON))

	// 步骤4: 发送事件奖励领取请求
	fmt.Println("\n步骤4: 发送事件奖励领取请求")
	receiveEventRewardMessage := append([]byte("RECEIVE_EVENT_REWARD:"), receiveEventRewardRequestJSON...)
	err = s.sendMessage(receiveEventRewardMessage)
	s.NoError(err)
	fmt.Println("事件奖励领取请求发送成功")

	// 步骤5: 接收事件奖励领取响应
	fmt.Println("\n步骤5: 接收事件奖励领取响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件奖励领取响应
	fmt.Println("\n步骤6: 验证事件奖励领取响应")
	s.NotEmpty(response)
	fmt.Printf("Receive event reward response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC009 测试结束 ==========")
}

// TestTCPResetEvent 测试事件重置（TCP）
func (s *EventTCPTestSuite) TestTCPResetEvent() {
	fmt.Println("========== TC010: 事件重置 ==========")
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

	// 步骤2: 构造事件重置请求
	fmt.Println("\n步骤2: 构造事件重置请求")
	resetEventRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
	}
	fmt.Println("事件重置请求对象创建成功")

	// 步骤3: 编码事件重置请求
	fmt.Println("\n步骤3: 编码事件重置请求")
	resetEventRequestJSON, err := json.Marshal(resetEventRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(resetEventRequestJSON))

	// 步骤4: 发送事件重置请求
	fmt.Println("\n步骤4: 发送事件重置请求")
	resetEventMessage := append([]byte("RESET_EVENT:"), resetEventRequestJSON...)
	err = s.sendMessage(resetEventMessage)
	s.NoError(err)
	fmt.Println("事件重置请求发送成功")

	// 步骤5: 接收事件重置响应
	fmt.Println("\n步骤5: 接收事件重置响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件重置响应
	fmt.Println("\n步骤6: 验证事件重置响应")
	s.NotEmpty(response)
	fmt.Printf("Reset event response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC010 测试结束 ==========")
}

// TestTCPDeleteEvent 测试事件删除（TCP）
func (s *EventTCPTestSuite) TestTCPDeleteEvent() {
	fmt.Println("========== TC011: 事件删除 ==========")
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

	// 步骤2: 构造事件删除请求
	fmt.Println("\n步骤2: 构造事件删除请求")
	deleteEventRequest := map[string]interface{}{
		"event_id": 1, // 事件ID
	}
	fmt.Println("事件删除请求对象创建成功")

	// 步骤3: 编码事件删除请求
	fmt.Println("\n步骤3: 编码事件删除请求")
	deleteEventRequestJSON, err := json.Marshal(deleteEventRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(deleteEventRequestJSON))

	// 步骤4: 发送事件删除请求
	fmt.Println("\n步骤4: 发送事件删除请求")
	deleteEventMessage := append([]byte("DELETE_EVENT:"), deleteEventRequestJSON...)
	err = s.sendMessage(deleteEventMessage)
	s.NoError(err)
	fmt.Println("事件删除请求发送成功")

	// 步骤5: 接收事件删除响应
	fmt.Println("\n步骤5: 接收事件删除响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件删除响应
	fmt.Println("\n步骤6: 验证事件删除响应")
	s.NotEmpty(response)
	fmt.Printf("Delete event response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC011 测试结束 ==========")
}

// TestTCPCreateEvent 测试事件创建（TCP）
func (s *EventTCPTestSuite) TestTCPCreateEvent() {
	fmt.Println("========== TC012: 事件创建 ==========")
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

	// 步骤2: 构造事件创建请求
	fmt.Println("\n步骤2: 构造事件创建请求")
	createEventRequest := map[string]interface{}{
		"event_name": "测试事件", // 事件名称
		"event_type": "daily", // 事件类型
		"start_time": time.Now().Unix(), // 开始时间
		"end_time":   time.Now().Add(24 * time.Hour).Unix(), // 结束时间
	}
	fmt.Println("事件创建请求对象创建成功")

	// 步骤3: 编码事件创建请求
	fmt.Println("\n步骤3: 编码事件创建请求")
	createEventRequestJSON, err := json.Marshal(createEventRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(createEventRequestJSON))

	// 步骤4: 发送事件创建请求
	fmt.Println("\n步骤4: 发送事件创建请求")
	createEventMessage := append([]byte("CREATE_EVENT:"), createEventRequestJSON...)
	err = s.sendMessage(createEventMessage)
	s.NoError(err)
	fmt.Println("事件创建请求发送成功")

	// 步骤5: 接收事件创建响应
	fmt.Println("\n步骤5: 接收事件创建响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证事件创建响应
	fmt.Println("\n步骤6: 验证事件创建响应")
	s.NotEmpty(response)
	fmt.Printf("Create event response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC012 测试结束 ==========")
}

// TestEventTCPSuite 测试事件系统TCP测试套件
func TestEventTCPSuite(t *testing.T) {
	suite.Run(t, new(EventTCPTestSuite))
}