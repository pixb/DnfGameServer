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

// EnterGameTCPTestSuite 进入游戏TCP测试套件
type EnterGameTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *EnterGameTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *EnterGameTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *EnterGameTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *EnterGameTCPTestSuite) sendMessage(message []byte) error {
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
func (s *EnterGameTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPPlayerLogin 测试玩家登录验证（TCP）
func (s *EnterGameTCPTestSuite) TestTCPPlayerLogin() {
	fmt.Println("========== TC001: 玩家登录验证 ==========")
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

	// 步骤2: 构造登录请求
	fmt.Println("\n步骤2: 构造登录请求")
	loginRequest := &v1.LoginRequest{
		Openid: "test_openid_001",
		Type:   1,
		Token:  "test_token_001",
		PlatId: 1,
		ClientIp: "127.0.0.1",
		Version: "1.0.0",
	}
	fmt.Println("LoginRequest对象创建成功")

	// 步骤3: 编码登录请求
	fmt.Println("\n步骤3: 编码登录请求")
	loginRequestJSON, err := protojson.Marshal(loginRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(loginRequestJSON))

	// 步骤4: 发送登录请求
	fmt.Println("\n步骤4: 发送登录请求")
	loginMessage := append([]byte("LOGIN:"), loginRequestJSON...)
	err = s.sendMessage(loginMessage)
	s.NoError(err)
	fmt.Println("登录请求发送成功")

	// 步骤5: 接收登录响应
	fmt.Println("\n步骤5: 接收登录响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证登录响应
	fmt.Println("\n步骤6: 验证登录响应")
	s.NotEmpty(response)
	fmt.Printf("Login response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPPlayerLoginInvalidOpenID 测试玩家登录失败_无效openid（TCP）
func (s *EnterGameTCPTestSuite) TestTCPPlayerLoginInvalidOpenID() {
	fmt.Println("========== TC002: 玩家登录失败_无效openid ==========")
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

	// 步骤2: 构造登录请求（无效openid）
	fmt.Println("\n步骤2: 构造登录请求（无效openid）")
	loginRequest := &v1.LoginRequest{
		Openid: "invalid_openid",
		Type:   1,
		Token:  "test_token_001",
		PlatId: 1,
		ClientIp: "127.0.0.1",
		Version: "1.0.0",
	}
	fmt.Println("LoginRequest对象创建成功")

	// 步骤3: 编码登录请求
	fmt.Println("\n步骤3: 编码登录请求")
	loginRequestJSON, err := protojson.Marshal(loginRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(loginRequestJSON))

	// 步骤4: 发送登录请求
	fmt.Println("\n步骤4: 发送登录请求")
	loginMessage := append([]byte("LOGIN:"), loginRequestJSON...)
	err = s.sendMessage(loginMessage)
	s.NoError(err)
	fmt.Println("登录请求发送成功")

	// 步骤5: 接收登录响应
	fmt.Println("\n步骤5: 接收登录响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证登录响应
	fmt.Println("\n步骤6: 验证登录响应")
	s.NotEmpty(response)
	fmt.Printf("Login response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPGetCharacterList 测试获取角色列表（TCP）
func (s *EnterGameTCPTestSuite) TestTCPGetCharacterList() {
	fmt.Println("========== TC003: 获取角色列表 ==========")
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

	// 步骤2: 发送获取角色列表请求
	fmt.Println("\n步骤2: 发送获取角色列表请求")
	getCharacterListRequest := &v1.CharacterListRequest{
		Openid: "test_openid_001",
	}
	getCharacterListRequestJSON, err := protojson.Marshal(getCharacterListRequest)
	s.NoError(err)
	getCharacterListMessage := append([]byte("GET_CHARACTER_LIST:"), getCharacterListRequestJSON...)
	err = s.sendMessage(getCharacterListMessage)
	s.NoError(err)
	fmt.Println("获取角色列表请求发送成功")

	// 步骤3: 接收角色列表响应
	fmt.Println("\n步骤3: 接收角色列表响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 验证角色列表响应
	fmt.Println("\n步骤4: 验证角色列表响应")
	s.NotEmpty(response)
	fmt.Printf("Get character list response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPGetCharacterListNoCharacter 测试获取角色列表_无角色（TCP）
func (s *EnterGameTCPTestSuite) TestTCPGetCharacterListNoCharacter() {
	fmt.Println("========== TC004: 获取角色列表_无角色 ==========")
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

	// 步骤2: 发送获取角色列表请求（无角色）
	fmt.Println("\n步骤2: 发送获取角色列表请求（无角色）")
	getCharacterListRequest := &v1.CharacterListRequest{
		Openid: "test_openid_no_character",
	}
	getCharacterListRequestJSON, err := protojson.Marshal(getCharacterListRequest)
	s.NoError(err)
	getCharacterListMessage := append([]byte("GET_CHARACTER_LIST:"), getCharacterListRequestJSON...)
	err = s.sendMessage(getCharacterListMessage)
	s.NoError(err)
	fmt.Println("获取角色列表请求发送成功")

	// 步骤3: 接收角色列表响应
	fmt.Println("\n步骤3: 接收角色列表响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 验证角色列表响应
	fmt.Println("\n步骤4: 验证角色列表响应")
	s.NotEmpty(response)
	fmt.Printf("Get character list response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPSelectCharacter 测试选择角色（TCP）
func (s *EnterGameTCPTestSuite) TestTCPSelectCharacter() {
	fmt.Println("========== TC005: 选择角色 ==========")
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

	// 步骤2: 发送选择角色请求
	fmt.Println("\n步骤2: 发送选择角色请求")
	selectCharacterRequest := &v1.SelectCharacterRequest{
		Uid: 1,
	}
	selectCharacterRequestJSON, err := protojson.Marshal(selectCharacterRequest)
	s.NoError(err)
	selectCharacterMessage := append([]byte("SELECT_CHARACTER:"), selectCharacterRequestJSON...)
	err = s.sendMessage(selectCharacterMessage)
	s.NoError(err)
	fmt.Println("选择角色请求发送成功")

	// 步骤3: 接收选择角色响应
	fmt.Println("\n步骤3: 接收选择角色响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 验证选择角色响应
	fmt.Println("\n步骤4: 验证选择角色响应")
	s.NotEmpty(response)
	fmt.Printf("Select character response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPSelectCharacterInvalidGUID 测试选择角色_无效角色GUID（TCP）
func (s *EnterGameTCPTestSuite) TestTCPSelectCharacterInvalidGUID() {
	fmt.Println("========== TC006: 选择角色_无效角色GUID ==========")
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

	// 步骤2: 发送选择角色请求（无效角色GUID）
	fmt.Println("\n步骤2: 发送选择角色请求（无效角色GUID）")
	selectCharacterRequest := &v1.SelectCharacterRequest{
		Uid: 999999,
	}
	selectCharacterRequestJSON, err := protojson.Marshal(selectCharacterRequest)
	s.NoError(err)
	selectCharacterMessage := append([]byte("SELECT_CHARACTER:"), selectCharacterRequestJSON...)
	err = s.sendMessage(selectCharacterMessage)
	s.NoError(err)
	fmt.Println("选择角色请求发送成功")

	// 步骤3: 接收选择角色响应
	fmt.Println("\n步骤3: 接收选择角色响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 验证选择角色响应
	fmt.Println("\n步骤4: 验证选择角色响应")
	s.NotEmpty(response)
	fmt.Printf("Select character response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPEnterGame 测试进入游戏（TCP）
func (s *EnterGameTCPTestSuite) TestTCPEnterGame() {
	fmt.Println("========== TC007: 进入游戏 ==========")
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

	// 步骤2: 发送进入游戏请求
	fmt.Println("\n步骤2: 发送进入游戏请求")
	enterGameRequest := []byte("ENTER_GAME")
	err = s.sendMessage(enterGameRequest)
	s.NoError(err)
	fmt.Println("进入游戏请求发送成功")

	// 步骤3: 接收进入游戏响应
	fmt.Println("\n步骤3: 接收进入游戏响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 验证进入游戏响应
	fmt.Println("\n步骤4: 验证进入游戏响应")
	s.NotEmpty(response)
	fmt.Printf("Enter game response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPEnterGameNoCharacterSelected 测试进入游戏_未选择角色（TCP）
func (s *EnterGameTCPTestSuite) TestTCPEnterGameNoCharacterSelected() {
	fmt.Println("========== TC008: 进入游戏_未选择角色 ==========")
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

	// 步骤2: 发送进入游戏请求（未选择角色）
	fmt.Println("\n步骤2: 发送进入游戏请求（未选择角色）")
	enterGameRequest := []byte("ENTER_GAME:NO_CHARACTER")
	err = s.sendMessage(enterGameRequest)
	s.NoError(err)
	fmt.Println("进入游戏请求发送成功")

	// 步骤3: 接收进入游戏响应
	fmt.Println("\n步骤3: 接收进入游戏响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 验证进入游戏响应
	fmt.Println("\n步骤4: 验证进入游戏响应")
	s.NotEmpty(response)
	fmt.Printf("Enter game response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestTCPPlayerDataLoadIntegrity 测试玩家数据加载完整性（TCP）
func (s *EnterGameTCPTestSuite) TestTCPPlayerDataLoadIntegrity() {
	fmt.Println("========== TC009: 玩家数据加载完整性 ==========")
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

	// 步骤2: 发送玩家数据加载请求
	fmt.Println("\n步骤2: 发送玩家数据加载请求")
	playerDataLoadRequest := []byte("LOAD_PLAYER_DATA:1")
	err = s.sendMessage(playerDataLoadRequest)
	s.NoError(err)
	fmt.Println("玩家数据加载请求发送成功")

	// 步骤3: 接收玩家数据加载响应
	fmt.Println("\n步骤3: 接收玩家数据加载响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 验证玩家数据加载响应
	fmt.Println("\n步骤4: 验证玩家数据加载响应")
	s.NotEmpty(response)
	fmt.Printf("Player data load response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC009 测试结束 ==========")
}

// TestTCPRepeatLogin 测试重复登录处理（TCP）
func (s *EnterGameTCPTestSuite) TestTCPRepeatLogin() {
	fmt.Println("========== TC010: 重复登录处理 ==========")
	// 步骤1: 建立第一个TCP连接
	fmt.Println("\n步骤1: 建立第一个TCP连接")
	err := s.connectTCP()
	s.NoError(err)
	s.NotNil(s.socket)
	if s.socket == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	fmt.Println("第一个TCP连接建立成功")

	// 步骤2: 发送登录请求
	fmt.Println("\n步骤2: 发送登录请求")
	loginRequest := &v1.LoginRequest{
		Openid: "test_openid_001",
		Type:   1,
		Token:  "test_token_001",
		PlatId: 1,
		ClientIp: "127.0.0.1",
		Version: "1.0.0",
	}
	loginRequestJSON, err := protojson.Marshal(loginRequest)
	s.NoError(err)
	loginMessage := append([]byte("LOGIN:"), loginRequestJSON...)
	err = s.sendMessage(loginMessage)
	s.NoError(err)
	fmt.Println("登录请求发送成功")

	// 步骤3: 接收登录响应
	fmt.Println("\n步骤3: 接收登录响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤4: 建立第二个TCP连接（重复登录）
	fmt.Println("\n步骤4: 建立第二个TCP连接（重复登录）")
	s.socket.Close() // 关闭第一个连接
	err = s.connectTCP()
	s.NoError(err)
	s.NotNil(s.socket)
	if s.socket == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	fmt.Println("第二个TCP连接建立成功")

	// 步骤5: 发送重复登录请求
	fmt.Println("\n步骤5: 发送重复登录请求")
	err = s.sendMessage(loginMessage)
	s.NoError(err)
	fmt.Println("重复登录请求发送成功")

	// 步骤6: 接收重复登录响应
	fmt.Println("\n步骤6: 接收重复登录响应")
	response, err = s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤7: 验证重复登录响应
	fmt.Println("\n步骤7: 验证重复登录响应")
	s.NotEmpty(response)
	fmt.Printf("Repeat login response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC010 测试结束 ==========")
}

// TestEnterGameTCPSuite 运行所有进入游戏TCP测试
func TestEnterGameTCPSuite(t *testing.T) {
	suite.Run(t, new(EnterGameTCPTestSuite))
}