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

// PartyTCPTestSuite 组队系统TCP测试套件
type PartyTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *PartyTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *PartyTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *PartyTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *PartyTCPTestSuite) sendMessage(message []byte) error {
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
func (s *PartyTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPCreateParty 测试创建队伍（TCP）
func (s *PartyTCPTestSuite) TestTCPCreateParty() {
	fmt.Println("========== TC001: 创建队伍 ==========")
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

	// 步骤2: 构造创建队伍请求
	fmt.Println("\n步骤2: 构造创建队伍请求")
	createPartyRequest := &v1.ControlGroupRequest{
		Type:         1, // 创建队伍
		Partyname:    "测试队伍",
		Dungeonindex: 1,
		Minlevel:     1,
		Maxlevel:     100,
		Area:         1,
		Subtype:      1,
		Stageindex:   1,
		Publictype:   1,
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码创建队伍请求
	fmt.Println("\n步骤3: 编码创建队伍请求")
	createPartyRequestJSON, err := protojson.Marshal(createPartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(createPartyRequestJSON))

	// 步骤4: 发送创建队伍请求
	fmt.Println("\n步骤4: 发送创建队伍请求")
	createPartyMessage := append([]byte("CREATE_PARTY:"), createPartyRequestJSON...)
	err = s.sendMessage(createPartyMessage)
	s.NoError(err)
	fmt.Println("创建队伍请求发送成功")

	// 步骤5: 接收创建队伍响应
	fmt.Println("\n步骤5: 接收创建队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证创建队伍响应
	fmt.Println("\n步骤6: 验证创建队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Create party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPCreatePartyInvalidParams 测试创建队伍_无效参数（TCP）
func (s *PartyTCPTestSuite) TestTCPCreatePartyInvalidParams() {
	fmt.Println("========== TC002: 创建队伍_无效参数 ==========")
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

	// 步骤2: 构造创建队伍请求（无效参数）
	fmt.Println("\n步骤2: 构造创建队伍请求（无效参数）")
	createPartyRequest := &v1.ControlGroupRequest{
		Type:      1,  // 创建队伍
		Partyname: "", // 空队伍名称
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码创建队伍请求
	fmt.Println("\n步骤3: 编码创建队伍请求")
	createPartyRequestJSON, err := protojson.Marshal(createPartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(createPartyRequestJSON))

	// 步骤4: 发送创建队伍请求
	fmt.Println("\n步骤4: 发送创建队伍请求")
	createPartyMessage := append([]byte("CREATE_PARTY:"), createPartyRequestJSON...)
	err = s.sendMessage(createPartyMessage)
	s.NoError(err)
	fmt.Println("创建队伍请求发送成功")

	// 步骤5: 接收创建队伍响应
	fmt.Println("\n步骤5: 接收创建队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证创建队伍响应
	fmt.Println("\n步骤6: 验证创建队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Create party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPJoinParty 测试加入队伍（TCP）
func (s *PartyTCPTestSuite) TestTCPJoinParty() {
	fmt.Println("========== TC003: 加入队伍 ==========")
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

	// 步骤2: 构造加入队伍请求
	fmt.Println("\n步骤2: 构造加入队伍请求")
	joinPartyRequest := &v1.ControlGroupRequest{
		Type:      2, // 加入队伍
		Partyguid: 1, // 队伍GUID
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码加入队伍请求
	fmt.Println("\n步骤3: 编码加入队伍请求")
	joinPartyRequestJSON, err := protojson.Marshal(joinPartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(joinPartyRequestJSON))

	// 步骤4: 发送加入队伍请求
	fmt.Println("\n步骤4: 发送加入队伍请求")
	joinPartyMessage := append([]byte("JOIN_PARTY:"), joinPartyRequestJSON...)
	err = s.sendMessage(joinPartyMessage)
	s.NoError(err)
	fmt.Println("加入队伍请求发送成功")

	// 步骤5: 接收加入队伍响应
	fmt.Println("\n步骤5: 接收加入队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证加入队伍响应
	fmt.Println("\n步骤6: 验证加入队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Join party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPJoinPartyInvalidGUID 测试加入队伍_无效队伍GUID（TCP）
func (s *PartyTCPTestSuite) TestTCPJoinPartyInvalidGUID() {
	fmt.Println("========== TC004: 加入队伍_无效队伍GUID ==========")
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

	// 步骤2: 构造加入队伍请求（无效队伍GUID）
	fmt.Println("\n步骤2: 构造加入队伍请求（无效队伍GUID）")
	joinPartyRequest := &v1.ControlGroupRequest{
		Type:      2,      // 加入队伍
		Partyguid: 999999, // 无效队伍GUID
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码加入队伍请求
	fmt.Println("\n步骤3: 编码加入队伍请求")
	joinPartyRequestJSON, err := protojson.Marshal(joinPartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(joinPartyRequestJSON))

	// 步骤4: 发送加入队伍请求
	fmt.Println("\n步骤4: 发送加入队伍请求")
	joinPartyMessage := append([]byte("JOIN_PARTY:"), joinPartyRequestJSON...)
	err = s.sendMessage(joinPartyMessage)
	s.NoError(err)
	fmt.Println("加入队伍请求发送成功")

	// 步骤5: 接收加入队伍响应
	fmt.Println("\n步骤5: 接收加入队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证加入队伍响应
	fmt.Println("\n步骤6: 验证加入队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Join party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPLeaveParty 测试离开队伍（TCP）
func (s *PartyTCPTestSuite) TestTCPLeaveParty() {
	fmt.Println("========== TC005: 离开队伍 ==========")
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

	// 步骤2: 构造离开队伍请求
	fmt.Println("\n步骤2: 构造离开队伍请求")
	leavePartyRequest := &v1.ControlGroupRequest{
		Type:      3, // 离开队伍
		Partyguid: 1, // 队伍GUID
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码离开队伍请求
	fmt.Println("\n步骤3: 编码离开队伍请求")
	leavePartyRequestJSON, err := protojson.Marshal(leavePartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(leavePartyRequestJSON))

	// 步骤4: 发送离开队伍请求
	fmt.Println("\n步骤4: 发送离开队伍请求")
	leavePartyMessage := append([]byte("LEAVE_PARTY:"), leavePartyRequestJSON...)
	err = s.sendMessage(leavePartyMessage)
	s.NoError(err)
	fmt.Println("离开队伍请求发送成功")

	// 步骤5: 接收离开队伍响应
	fmt.Println("\n步骤5: 接收离开队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证离开队伍响应
	fmt.Println("\n步骤6: 验证离开队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Leave party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPLeavePartyLeaderLeave 测试离开队伍_队长离开（TCP）
func (s *PartyTCPTestSuite) TestTCPLeavePartyLeaderLeave() {
	fmt.Println("========== TC006: 离开队伍_队长离开 ==========")
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

	// 步骤2: 构造队长离开队伍请求
	fmt.Println("\n步骤2: 构造队长离开队伍请求")
	leavePartyRequest := &v1.ControlGroupRequest{
		Type:      3, // 离开队伍
		Partyguid: 1, // 队伍GUID
		Charguid:  1, // 队长角色GUID
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码队长离开队伍请求
	fmt.Println("\n步骤3: 编码队长离开队伍请求")
	leavePartyRequestJSON, err := protojson.Marshal(leavePartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(leavePartyRequestJSON))

	// 步骤4: 发送队长离开队伍请求
	fmt.Println("\n步骤4: 发送队长离开队伍请求")
	leavePartyMessage := append([]byte("LEAVE_PARTY:"), leavePartyRequestJSON...)
	err = s.sendMessage(leavePartyMessage)
	s.NoError(err)
	fmt.Println("队长离开队伍请求发送成功")

	// 步骤5: 接收队长离开队伍响应
	fmt.Println("\n步骤5: 接收队长离开队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证队长离开队伍响应
	fmt.Println("\n步骤6: 验证队长离开队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Leave party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPSearchParty 测试搜索队伍（TCP）
func (s *PartyTCPTestSuite) TestTCPSearchParty() {
	fmt.Println("========== TC007: 搜索队伍 ==========")
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

	// 步骤2: 构造搜索队伍请求
	fmt.Println("\n步骤2: 构造搜索队伍请求")
	searchPartyRequest := &v1.SearchPartyListRequest{
		Dungeonindex: 1,
		Minlevel:     1,
		Maxlevel:     100,
	}
	fmt.Println("SearchPartyListRequest对象创建成功")

	// 步骤3: 编码搜索队伍请求
	fmt.Println("\n步骤3: 编码搜索队伍请求")
	searchPartyRequestJSON, err := protojson.Marshal(searchPartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(searchPartyRequestJSON))

	// 步骤4: 发送搜索队伍请求
	fmt.Println("\n步骤4: 发送搜索队伍请求")
	searchPartyMessage := append([]byte("SEARCH_PARTY:"), searchPartyRequestJSON...)
	err = s.sendMessage(searchPartyMessage)
	s.NoError(err)
	fmt.Println("搜索队伍请求发送成功")

	// 步骤5: 接收搜索队伍响应
	fmt.Println("\n步骤5: 接收搜索队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证搜索队伍响应
	fmt.Println("\n步骤6: 验证搜索队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Search party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPSearchPartyInvalidCondition 测试搜索队伍_无效条件（TCP）
func (s *PartyTCPTestSuite) TestTCPSearchPartyInvalidCondition() {
	fmt.Println("========== TC008: 搜索队伍_无效条件 ==========")
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

	// 步骤2: 构造搜索队伍请求（无效条件）
	fmt.Println("\n步骤2: 构造搜索队伍请求（无效条件）")
	searchPartyRequest := &v1.SearchPartyListRequest{
		Dungeonindex: 999, // 无效副本索引
		Minlevel:     101, // 最小等级大于最大等级
		Maxlevel:     100,
	}
	fmt.Println("SearchPartyListRequest对象创建成功")

	// 步骤3: 编码搜索队伍请求
	fmt.Println("\n步骤3: 编码搜索队伍请求")
	searchPartyRequestJSON, err := protojson.Marshal(searchPartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(searchPartyRequestJSON))

	// 步骤4: 发送搜索队伍请求
	fmt.Println("\n步骤4: 发送搜索队伍请求")
	searchPartyMessage := append([]byte("SEARCH_PARTY:"), searchPartyRequestJSON...)
	err = s.sendMessage(searchPartyMessage)
	s.NoError(err)
	fmt.Println("搜索队伍请求发送成功")

	// 步骤5: 接收搜索队伍响应
	fmt.Println("\n步骤5: 接收搜索队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证搜索队伍响应
	fmt.Println("\n步骤6: 验证搜索队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Search party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestTCPModifyPartySetting 测试队伍设置_修改设置（TCP）
func (s *PartyTCPTestSuite) TestTCPModifyPartySetting() {
	fmt.Println("========== TC009: 队伍设置_修改设置 ==========")
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

	// 步骤2: 构造修改队伍设置请求
	fmt.Println("\n步骤2: 构造修改队伍设置请求")
	modifyPartySettingRequest := &v1.ControlGroupRequest{
		Type:       4,  // 修改队伍设置
		Partyguid:  1,  // 队伍GUID
		Minlevel:   10, // 修改最小等级
		Maxlevel:   90, // 修改最大等级
		Publictype: 2,  // 修改队伍类型
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码修改队伍设置请求
	fmt.Println("\n步骤3: 编码修改队伍设置请求")
	modifyPartySettingRequestJSON, err := protojson.Marshal(modifyPartySettingRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(modifyPartySettingRequestJSON))

	// 步骤4: 发送修改队伍设置请求
	fmt.Println("\n步骤4: 发送修改队伍设置请求")
	modifyPartySettingMessage := append([]byte("MODIFY_PARTY_SETTING:"), modifyPartySettingRequestJSON...)
	err = s.sendMessage(modifyPartySettingMessage)
	s.NoError(err)
	fmt.Println("修改队伍设置请求发送成功")

	// 步骤5: 接收修改队伍设置响应
	fmt.Println("\n步骤5: 接收修改队伍设置响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证修改队伍设置响应
	fmt.Println("\n步骤6: 验证修改队伍设置响应")
	s.NotEmpty(response)
	fmt.Printf("Modify party setting response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC009 测试结束 ==========")
}

// TestTCPKickOutMember 测试队伍成员管理_踢出队员（TCP）
func (s *PartyTCPTestSuite) TestTCPKickOutMember() {
	fmt.Println("========== TC010: 队伍成员管理_踢出队员 ==========")
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

	// 步骤2: 构造踢出队员请求
	fmt.Println("\n步骤2: 构造踢出队员请求")
	kickOutMemberRequest := &v1.VoteKickOutUserRequest{
		Targetguid: 2, // 目标队员GUID
	}
	fmt.Println("VoteKickOutUserRequest对象创建成功")

	// 步骤3: 编码踢出队员请求
	fmt.Println("\n步骤3: 编码踢出队员请求")
	kickOutMemberRequestJSON, err := protojson.Marshal(kickOutMemberRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(kickOutMemberRequestJSON))

	// 步骤4: 发送踢出队员请求
	fmt.Println("\n步骤4: 发送踢出队员请求")
	kickOutMemberMessage := append([]byte("KICK_OUT_MEMBER:"), kickOutMemberRequestJSON...)
	err = s.sendMessage(kickOutMemberMessage)
	s.NoError(err)
	fmt.Println("踢出队员请求发送成功")

	// 步骤5: 接收踢出队员响应
	fmt.Println("\n步骤5: 接收踢出队员响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证踢出队员响应
	fmt.Println("\n步骤6: 验证踢出队员响应")
	s.NotEmpty(response)
	fmt.Printf("Kick out member response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC010 测试结束 ==========")
}

// TestTCPStartMultiPlay 测试队伍副本_开始多人游戏（TCP）
func (s *PartyTCPTestSuite) TestTCPStartMultiPlay() {
	fmt.Println("========== TC011: 队伍副本_开始多人游戏 ==========")
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

	// 步骤2: 构造开始多人游戏请求
	fmt.Println("\n步骤2: 构造开始多人游戏请求")
	startMultiPlayRequest := &v1.StartMultiPlayRequest{
		Input:     1,
		Type:      1,
		Index:     1,
		Partyguid: 1, // 队伍GUID
	}
	fmt.Println("StartMultiPlayRequest对象创建成功")

	// 步骤3: 编码开始多人游戏请求
	fmt.Println("\n步骤3: 编码开始多人游戏请求")
	startMultiPlayRequestJSON, err := protojson.Marshal(startMultiPlayRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(startMultiPlayRequestJSON))

	// 步骤4: 发送开始多人游戏请求
	fmt.Println("\n步骤4: 发送开始多人游戏请求")
	startMultiPlayMessage := append([]byte("START_MULTI_PLAY:"), startMultiPlayRequestJSON...)
	err = s.sendMessage(startMultiPlayMessage)
	s.NoError(err)
	fmt.Println("开始多人游戏请求发送成功")

	// 步骤5: 接收开始多人游戏响应
	fmt.Println("\n步骤5: 接收开始多人游戏响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证开始多人游戏响应
	fmt.Println("\n步骤6: 验证开始多人游戏响应")
	s.NotEmpty(response)
	fmt.Printf("Start multi play response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC011 测试结束 ==========")
}

// TestTCPConnectBattleServer 测试队伍副本_连接战斗服务器（TCP）
func (s *PartyTCPTestSuite) TestTCPConnectBattleServer() {
	fmt.Println("========== TC012: 队伍副本_连接战斗服务器 ==========")
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

	// 步骤2: 构造连接战斗服务器请求
	fmt.Println("\n步骤2: 构造连接战斗服务器请求")
	connectBattleServerRequest := &v1.ConnectBattleServerRequest{
		Matchingguid: 1,               // 匹配GUID
		Charguid:     1,               // 角色GUID
		Authkey:      "test_auth_key", // 认证密钥
	}
	fmt.Println("ConnectBattleServerRequest对象创建成功")

	// 步骤3: 编码连接战斗服务器请求
	fmt.Println("\n步骤3: 编码连接战斗服务器请求")
	connectBattleServerRequestJSON, err := protojson.Marshal(connectBattleServerRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(connectBattleServerRequestJSON))

	// 步骤4: 发送连接战斗服务器请求
	fmt.Println("\n步骤4: 发送连接战斗服务器请求")
	connectBattleServerMessage := append([]byte("CONNECT_BATTLE_SERVER:"), connectBattleServerRequestJSON...)
	err = s.sendMessage(connectBattleServerMessage)
	s.NoError(err)
	fmt.Println("连接战斗服务器请求发送成功")

	// 步骤5: 接收连接战斗服务器响应
	fmt.Println("\n步骤5: 接收连接战斗服务器响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证连接战斗服务器响应
	fmt.Println("\n步骤6: 验证连接战斗服务器响应")
	s.NotEmpty(response)
	fmt.Printf("Connect battle server response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC012 测试结束 ==========")
}

// TestTCPLoadPartyStatus 测试队伍状态_加载状态（TCP）
func (s *PartyTCPTestSuite) TestTCPLoadPartyStatus() {
	fmt.Println("========== TC013: 队伍状态_加载状态 ==========")
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

	// 步骤2: 构造加载队伍状态请求
	fmt.Println("\n步骤2: 构造加载队伍状态请求")
	loadPartyStatusRequest := &v1.PartyLoadingStatusRequest{}
	fmt.Println("PartyLoadingStatusRequest对象创建成功")

	// 步骤3: 编码加载队伍状态请求
	fmt.Println("\n步骤3: 编码加载队伍状态请求")
	loadPartyStatusRequestJSON, err := protojson.Marshal(loadPartyStatusRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(loadPartyStatusRequestJSON))

	// 步骤4: 发送加载队伍状态请求
	fmt.Println("\n步骤4: 发送加载队伍状态请求")
	loadPartyStatusMessage := append([]byte("LOAD_PARTY_STATUS:"), loadPartyStatusRequestJSON...)
	err = s.sendMessage(loadPartyStatusMessage)
	s.NoError(err)
	fmt.Println("加载队伍状态请求发送成功")

	// 步骤5: 接收加载队伍状态响应
	fmt.Println("\n步骤5: 接收加载队伍状态响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证加载队伍状态响应
	fmt.Println("\n步骤6: 验证加载队伍状态响应")
	s.NotEmpty(response)
	fmt.Printf("Load party status response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC013 测试结束 ==========")
}

// TestTCPHalfOpenParty 测试队伍邀请_半开队伍（TCP）
func (s *PartyTCPTestSuite) TestTCPHalfOpenParty() {
	fmt.Println("========== TC014: 队伍邀请_半开队伍 ==========")
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

	// 步骤2: 构造半开队伍请求
	fmt.Println("\n步骤2: 构造半开队伍请求")
	halfOpenPartyRequest := &v1.HalfOpenPartyAcceptRequest{
		Partyguid: 1, // 队伍GUID
	}
	fmt.Println("HalfOpenPartyAcceptRequest对象创建成功")

	// 步骤3: 编码半开队伍请求
	fmt.Println("\n步骤3: 编码半开队伍请求")
	halfOpenPartyRequestJSON, err := protojson.Marshal(halfOpenPartyRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(halfOpenPartyRequestJSON))

	// 步骤4: 发送半开队伍请求
	fmt.Println("\n步骤4: 发送半开队伍请求")
	halfOpenPartyMessage := append([]byte("HALF_OPEN_PARTY:"), halfOpenPartyRequestJSON...)
	err = s.sendMessage(halfOpenPartyMessage)
	s.NoError(err)
	fmt.Println("半开队伍请求发送成功")

	// 步骤5: 接收半开队伍响应
	fmt.Println("\n步骤5: 接收半开队伍响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证半开队伍响应
	fmt.Println("\n步骤6: 验证半开队伍响应")
	s.NotEmpty(response)
	fmt.Printf("Half open party response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC014 测试结束 ==========")
}

// TestTCPConcurrentOperation 测试队伍异常_并发操作（TCP）
func (s *PartyTCPTestSuite) TestTCPConcurrentOperation() {
	fmt.Println("========== TC015: 队伍异常_并发操作 ==========")
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

	// 步骤2: 构造并发操作请求
	fmt.Println("\n步骤2: 构造并发操作请求")
	concurrentOperationRequest := &v1.ControlGroupRequest{
		Type:      5, // 并发操作
		Partyguid: 1, // 队伍GUID
	}
	fmt.Println("ControlGroupRequest对象创建成功")

	// 步骤3: 编码并发操作请求
	fmt.Println("\n步骤3: 编码并发操作请求")
	concurrentOperationRequestJSON, err := protojson.Marshal(concurrentOperationRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(concurrentOperationRequestJSON))

	// 步骤4: 发送并发操作请求
	fmt.Println("\n步骤4: 发送并发操作请求")
	concurrentOperationMessage := append([]byte("CONCURRENT_OPERATION:"), concurrentOperationRequestJSON...)
	err = s.sendMessage(concurrentOperationMessage)
	s.NoError(err)
	fmt.Println("并发操作请求发送成功")

	// 步骤5: 接收并发操作响应
	fmt.Println("\n步骤5: 接收并发操作响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证并发操作响应
	fmt.Println("\n步骤6: 验证并发操作响应")
	s.NotEmpty(response)
	fmt.Printf("Concurrent operation response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC015 测试结束 ==========")
}

// TestPartyTCPSuite 测试组队系统TCP测试套件
func TestPartyTCPSuite(t *testing.T) {
	suite.Run(t, new(PartyTCPTestSuite))
}
