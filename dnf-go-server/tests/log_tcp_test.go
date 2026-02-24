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

// LogTCPTestSuite 日志系统TCP测试套件
type LogTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *LogTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *LogTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *LogTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *LogTCPTestSuite) sendMessage(message []byte) error {
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
func (s *LogTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPRecordLog 测试日志记录（TCP）
func (s *LogTCPTestSuite) TestTCPRecordLog() {
	fmt.Println("========== TC001: 日志记录 ==========")
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

	// 步骤2: 构造日志记录请求
	fmt.Println("\n步骤2: 构造日志记录请求")
	recordLogRequest := map[string]interface{}{
		"log_level": "info", // 日志级别
		"log_message": "测试日志记录", // 日志消息
		"log_category": "test", // 日志类别
		"log_time": time.Now().Unix(), // 日志时间
	}
	fmt.Println("日志记录请求对象创建成功")

	// 步骤3: 编码日志记录请求
	fmt.Println("\n步骤3: 编码日志记录请求")
	recordLogRequestJSON, err := json.Marshal(recordLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(recordLogRequestJSON))

	// 步骤4: 发送日志记录请求
	fmt.Println("\n步骤4: 发送日志记录请求")
	recordLogMessage := append([]byte("RECORD_LOG:"), recordLogRequestJSON...)
	err = s.sendMessage(recordLogMessage)
	s.NoError(err)
	fmt.Println("日志记录请求发送成功")

	// 步骤5: 接收日志记录响应
	fmt.Println("\n步骤5: 接收日志记录响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志记录响应
	fmt.Println("\n步骤6: 验证日志记录响应")
	s.NotEmpty(response)
	fmt.Printf("Record log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPQueryLog 测试日志查询（TCP）
func (s *LogTCPTestSuite) TestTCPQueryLog() {
	fmt.Println("========== TC002: 日志查询 ==========")
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

	// 步骤2: 构造日志查询请求
	fmt.Println("\n步骤2: 构造日志查询请求")
	queryLogRequest := map[string]interface{}{
		"log_level": "info", // 日志级别
		"log_category": "test", // 日志类别
		"start_time": time.Now().Add(-24 * time.Hour).Unix(), // 开始时间
		"end_time": time.Now().Unix(), // 结束时间
		"page": 1, // 页码
		"page_size": 10, // 每页大小
	}
	fmt.Println("日志查询请求对象创建成功")

	// 步骤3: 编码日志查询请求
	fmt.Println("\n步骤3: 编码日志查询请求")
	queryLogRequestJSON, err := json.Marshal(queryLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryLogRequestJSON))

	// 步骤4: 发送日志查询请求
	fmt.Println("\n步骤4: 发送日志查询请求")
	queryLogMessage := append([]byte("QUERY_LOG:"), queryLogRequestJSON...)
	err = s.sendMessage(queryLogMessage)
	s.NoError(err)
	fmt.Println("日志查询请求发送成功")

	// 步骤5: 接收日志查询响应
	fmt.Println("\n步骤5: 接收日志查询响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志查询响应
	fmt.Println("\n步骤6: 验证日志查询响应")
	s.NotEmpty(response)
	fmt.Printf("Query log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPStatisticLog 测试日志统计（TCP）
func (s *LogTCPTestSuite) TestTCPStatisticLog() {
	fmt.Println("========== TC003: 日志统计 ==========")
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

	// 步骤2: 构造日志统计请求
	fmt.Println("\n步骤2: 构造日志统计请求")
	statisticLogRequest := map[string]interface{}{
		"start_time": time.Now().Add(-24 * time.Hour).Unix(), // 开始时间
		"end_time": time.Now().Unix(), // 结束时间
		"group_by": "hour", // 分组方式
	}
	fmt.Println("日志统计请求对象创建成功")

	// 步骤3: 编码日志统计请求
	fmt.Println("\n步骤3: 编码日志统计请求")
	statisticLogRequestJSON, err := json.Marshal(statisticLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(statisticLogRequestJSON))

	// 步骤4: 发送日志统计请求
	fmt.Println("\n步骤4: 发送日志统计请求")
	statisticLogMessage := append([]byte("STATISTIC_LOG:"), statisticLogRequestJSON...)
	err = s.sendMessage(statisticLogMessage)
	s.NoError(err)
	fmt.Println("日志统计请求发送成功")

	// 步骤5: 接收日志统计响应
	fmt.Println("\n步骤5: 接收日志统计响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志统计响应
	fmt.Println("\n步骤6: 验证日志统计响应")
	s.NotEmpty(response)
	fmt.Printf("Statistic log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPDeleteLog 测试日志删除（TCP）
func (s *LogTCPTestSuite) TestTCPDeleteLog() {
	fmt.Println("========== TC004: 日志删除 ==========")
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

	// 步骤2: 构造日志删除请求
	fmt.Println("\n步骤2: 构造日志删除请求")
	deleteLogRequest := map[string]interface{}{
		"log_ids": []int{1, 2, 3}, // 日志ID列表
	}
	fmt.Println("日志删除请求对象创建成功")

	// 步骤3: 编码日志删除请求
	fmt.Println("\n步骤3: 编码日志删除请求")
	deleteLogRequestJSON, err := json.Marshal(deleteLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(deleteLogRequestJSON))

	// 步骤4: 发送日志删除请求
	fmt.Println("\n步骤4: 发送日志删除请求")
	deleteLogMessage := append([]byte("DELETE_LOG:"), deleteLogRequestJSON...)
	err = s.sendMessage(deleteLogMessage)
	s.NoError(err)
	fmt.Println("日志删除请求发送成功")

	// 步骤5: 接收日志删除响应
	fmt.Println("\n步骤5: 接收日志删除响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志删除响应
	fmt.Println("\n步骤6: 验证日志删除响应")
	s.NotEmpty(response)
	fmt.Printf("Delete log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPExportLog 测试日志导出（TCP）
func (s *LogTCPTestSuite) TestTCPExportLog() {
	fmt.Println("========== TC005: 日志导出 ==========")
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

	// 步骤2: 构造日志导出请求
	fmt.Println("\n步骤2: 构造日志导出请求")
	exportLogRequest := map[string]interface{}{
		"start_time": time.Now().Add(-24 * time.Hour).Unix(), // 开始时间
		"end_time": time.Now().Unix(), // 结束时间
		"log_level": "info", // 日志级别
		"export_format": "csv", // 导出格式
	}
	fmt.Println("日志导出请求对象创建成功")

	// 步骤3: 编码日志导出请求
	fmt.Println("\n步骤3: 编码日志导出请求")
	exportLogRequestJSON, err := json.Marshal(exportLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(exportLogRequestJSON))

	// 步骤4: 发送日志导出请求
	fmt.Println("\n步骤4: 发送日志导出请求")
	exportLogMessage := append([]byte("EXPORT_LOG:"), exportLogRequestJSON...)
	err = s.sendMessage(exportLogMessage)
	s.NoError(err)
	fmt.Println("日志导出请求发送成功")

	// 步骤5: 接收日志导出响应
	fmt.Println("\n步骤5: 接收日志导出响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志导出响应
	fmt.Println("\n步骤6: 验证日志导出响应")
	s.NotEmpty(response)
	fmt.Printf("Export log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPCleanLog 测试日志清理（TCP）
func (s *LogTCPTestSuite) TestTCPCleanLog() {
	fmt.Println("========== TC006: 日志清理 ==========")
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

	// 步骤2: 构造日志清理请求
	fmt.Println("\n步骤2: 构造日志清理请求")
	cleanLogRequest := map[string]interface{}{
		"before_time": time.Now().Add(-30 * 24 * time.Hour).Unix(), // 清理时间
		"log_level": "info", // 日志级别
	}
	fmt.Println("日志清理请求对象创建成功")

	// 步骤3: 编码日志清理请求
	fmt.Println("\n步骤3: 编码日志清理请求")
	cleanLogRequestJSON, err := json.Marshal(cleanLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(cleanLogRequestJSON))

	// 步骤4: 发送日志清理请求
	fmt.Println("\n步骤4: 发送日志清理请求")
	cleanLogMessage := append([]byte("CLEAN_LOG:"), cleanLogRequestJSON...)
	err = s.sendMessage(cleanLogMessage)
	s.NoError(err)
	fmt.Println("日志清理请求发送成功")

	// 步骤5: 接收日志清理响应
	fmt.Println("\n步骤5: 接收日志清理响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志清理响应
	fmt.Println("\n步骤6: 验证日志清理响应")
	s.NotEmpty(response)
	fmt.Printf("Clean log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPMonitorLog 测试日志监控（TCP）
func (s *LogTCPTestSuite) TestTCPMonitorLog() {
	fmt.Println("========== TC007: 日志监控 ==========")
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

	// 步骤2: 构造日志监控请求
	fmt.Println("\n步骤2: 构造日志监控请求")
	monitorLogRequest := map[string]interface{}{
		"monitor_level": "error", // 监控级别
		"monitor_duration": 3600, // 监控时长（秒）
	}
	fmt.Println("日志监控请求对象创建成功")

	// 步骤3: 编码日志监控请求
	fmt.Println("\n步骤3: 编码日志监控请求")
	monitorLogRequestJSON, err := json.Marshal(monitorLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(monitorLogRequestJSON))

	// 步骤4: 发送日志监控请求
	fmt.Println("\n步骤4: 发送日志监控请求")
	monitorLogMessage := append([]byte("MONITOR_LOG:"), monitorLogRequestJSON...)
	err = s.sendMessage(monitorLogMessage)
	s.NoError(err)
	fmt.Println("日志监控请求发送成功")

	// 步骤5: 接收日志监控响应
	fmt.Println("\n步骤5: 接收日志监控响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志监控响应
	fmt.Println("\n步骤6: 验证日志监控响应")
	s.NotEmpty(response)
	fmt.Printf("Monitor log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPAnalyzeLog 测试日志分析（TCP）
func (s *LogTCPTestSuite) TestTCPAnalyzeLog() {
	fmt.Println("========== TC008: 日志分析 ==========")
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

	// 步骤2: 构造日志分析请求
	fmt.Println("\n步骤2: 构造日志分析请求")
	analyzeLogRequest := map[string]interface{}{
		"start_time": time.Now().Add(-24 * time.Hour).Unix(), // 开始时间
		"end_time": time.Now().Unix(), // 结束时间
		"analysis_type": "trend", // 分析类型
		"group_by": "hour", // 分组方式
	}
	fmt.Println("日志分析请求对象创建成功")

	// 步骤3: 编码日志分析请求
	fmt.Println("\n步骤3: 编码日志分析请求")
	analyzeLogRequestJSON, err := json.Marshal(analyzeLogRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(analyzeLogRequestJSON))

	// 步骤4: 发送日志分析请求
	fmt.Println("\n步骤4: 发送日志分析请求")
	analyzeLogMessage := append([]byte("ANALYZE_LOG:"), analyzeLogRequestJSON...)
	err = s.sendMessage(analyzeLogMessage)
	s.NoError(err)
	fmt.Println("日志分析请求发送成功")

	// 步骤5: 接收日志分析响应
	fmt.Println("\n步骤5: 接收日志分析响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证日志分析响应
	fmt.Println("\n步骤6: 验证日志分析响应")
	s.NotEmpty(response)
	fmt.Printf("Analyze log response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestLogTCPSuite 测试日志系统TCP测试套件
func TestLogTCPSuite(t *testing.T) {
	suite.Run(t, new(LogTCPTestSuite))
}