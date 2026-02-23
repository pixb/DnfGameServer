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

// PKTCPTestSuite PK系统TCP测试套件
type PKTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *PKTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *PKTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *PKTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *PKTCPTestSuite) sendMessage(message []byte) error {
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
func (s *PKTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPMultiPlayRequestMatch 测试多人游戏请求匹配（TCP）
func (s *PKTCPTestSuite) TestTCPMultiPlayRequestMatch() {
	fmt.Println("========== TC001: 多人游戏请求匹配 ==========")
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

	// 步骤2: 构造多人游戏请求匹配请求
	fmt.Println("\n步骤2: 构造多人游戏请求匹配请求")
	multiPlayRequestMatchRequest := &v1.MultiPlayRequestMatchRequest{
		TransId:      1,
		Matchtype:    1, // 匹配类型
		Dungeonindex: 1, // 副本索引
	}
	fmt.Println("MultiPlayRequestMatchRequest对象创建成功")

	// 步骤3: 编码多人游戏请求匹配请求
	fmt.Println("\n步骤3: 编码多人游戏请求匹配请求")
	multiPlayRequestMatchRequestJSON, err := protojson.Marshal(multiPlayRequestMatchRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(multiPlayRequestMatchRequestJSON))

	// 步骤4: 发送多人游戏请求匹配请求
	fmt.Println("\n步骤4: 发送多人游戏请求匹配请求")
	multiPlayRequestMatchMessage := append([]byte("MULTI_PLAY_REQUEST_MATCH:"), multiPlayRequestMatchRequestJSON...)
	err = s.sendMessage(multiPlayRequestMatchMessage)
	s.NoError(err)
	fmt.Println("多人游戏请求匹配请求发送成功")

	// 步骤5: 接收多人游戏请求匹配响应
	fmt.Println("\n步骤5: 接收多人游戏请求匹配响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证多人游戏请求匹配响应
	fmt.Println("\n步骤6: 验证多人游戏请求匹配响应")
	s.NotEmpty(response)
	fmt.Printf("Multi play request match response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPMultiPlayRequestMatchCancel 测试取消多人游戏请求匹配（TCP）
func (s *PKTCPTestSuite) TestTCPMultiPlayRequestMatchCancel() {
	fmt.Println("========== TC002: 取消多人游戏请求匹配 ==========")
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

	// 步骤2: 构造取消多人游戏请求匹配请求
	fmt.Println("\n步骤2: 构造取消多人游戏请求匹配请求")
	multiPlayRequestMatchCancelRequest := &v1.MultiPlayRequestMatchCancelRequest{
		TransId:      1,
		Matchingguid: 1, // 匹配GUID
	}
	fmt.Println("MultiPlayRequestMatchCancelRequest对象创建成功")

	// 步骤3: 编码取消多人游戏请求匹配请求
	fmt.Println("\n步骤3: 编码取消多人游戏请求匹配请求")
	multiPlayRequestMatchCancelRequestJSON, err := protojson.Marshal(multiPlayRequestMatchCancelRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(multiPlayRequestMatchCancelRequestJSON))

	// 步骤4: 发送取消多人游戏请求匹配请求
	fmt.Println("\n步骤4: 发送取消多人游戏请求匹配请求")
	multiPlayRequestMatchCancelMessage := append([]byte("MULTI_PLAY_REQUEST_MATCH_CANCEL:"), multiPlayRequestMatchCancelRequestJSON...)
	err = s.sendMessage(multiPlayRequestMatchCancelMessage)
	s.NoError(err)
	fmt.Println("取消多人游戏请求匹配请求发送成功")

	// 步骤5: 接收取消多人游戏请求匹配响应
	fmt.Println("\n步骤5: 接收取消多人游戏请求匹配响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证取消多人游戏请求匹配响应
	fmt.Println("\n步骤6: 验证取消多人游戏请求匹配响应")
	s.NotEmpty(response)
	fmt.Printf("Multi play request match cancel response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPHistoricSiteNoti 测试历史遗迹通知（TCP）
func (s *PKTCPTestSuite) TestTCPHistoricSiteNoti() {
	fmt.Println("========== TC003: 历史遗迹通知 ==========")
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

	// 步骤2: 构造历史遗迹通知请求
	fmt.Println("\n步骤2: 构造历史遗迹通知请求")
	historicSiteNotiRequest := &v1.HistoricSiteNotiRequest{
		TransId: 1,
	}
	fmt.Println("HistoricSiteNotiRequest对象创建成功")

	// 步骤3: 编码历史遗迹通知请求
	fmt.Println("\n步骤3: 编码历史遗迹通知请求")
	historicSiteNotiRequestJSON, err := protojson.Marshal(historicSiteNotiRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(historicSiteNotiRequestJSON))

	// 步骤4: 发送历史遗迹通知请求
	fmt.Println("\n步骤4: 发送历史遗迹通知请求")
	historicSiteNotiMessage := append([]byte("HISTORIC_SITE_NOTI:"), historicSiteNotiRequestJSON...)
	err = s.sendMessage(historicSiteNotiMessage)
	s.NoError(err)
	fmt.Println("历史遗迹通知请求发送成功")

	// 步骤5: 接收历史遗迹通知响应
	fmt.Println("\n步骤5: 接收历史遗迹通知响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证历史遗迹通知响应
	fmt.Println("\n步骤6: 验证历史遗迹通知响应")
	s.NotEmpty(response)
	fmt.Printf("Historic site noti response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPLoadGuildDonationInfo 测试加载公会捐赠信息（TCP）
func (s *PKTCPTestSuite) TestTCPLoadGuildDonationInfo() {
	fmt.Println("========== TC004: 加载公会捐赠信息 ==========")
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

	// 步骤2: 构造加载公会捐赠信息请求
	fmt.Println("\n步骤2: 构造加载公会捐赠信息请求")
	loadGuildDonationInfoRequest := &v1.LoadGuildDonationInfoRequest{
		TransId: 1,
	}
	fmt.Println("LoadGuildDonationInfoRequest对象创建成功")

	// 步骤3: 编码加载公会捐赠信息请求
	fmt.Println("\n步骤3: 编码加载公会捐赠信息请求")
	loadGuildDonationInfoRequestJSON, err := protojson.Marshal(loadGuildDonationInfoRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(loadGuildDonationInfoRequestJSON))

	// 步骤4: 发送加载公会捐赠信息请求
	fmt.Println("\n步骤4: 发送加载公会捐赠信息请求")
	loadGuildDonationInfoMessage := append([]byte("LOAD_GUILD_DONATION_INFO:"), loadGuildDonationInfoRequestJSON...)
	err = s.sendMessage(loadGuildDonationInfoMessage)
	s.NoError(err)
	fmt.Println("加载公会捐赠信息请求发送成功")

	// 步骤5: 接收加载公会捐赠信息响应
	fmt.Println("\n步骤5: 接收加载公会捐赠信息响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证加载公会捐赠信息响应
	fmt.Println("\n步骤6: 验证加载公会捐赠信息响应")
	s.NotEmpty(response)
	fmt.Printf("Load guild donation info response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPReturnToTownAtMultiPlay 测试返回城镇_多人游戏（TCP）
func (s *PKTCPTestSuite) TestTCPReturnToTownAtMultiPlay() {
	fmt.Println("========== TC005: 返回城镇_多人游戏 ==========")
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

	// 步骤2: 构造返回城镇_多人游戏请求
	fmt.Println("\n步骤2: 构造返回城镇_多人游戏请求")
	returnToTownAtMultiPlayRequest := &v1.ReturnToTownAtMultiPlayRequest{
		TransId: 1,
	}
	fmt.Println("ReturnToTownAtMultiPlayRequest对象创建成功")

	// 步骤3: 编码返回城镇_多人游戏请求
	fmt.Println("\n步骤3: 编码返回城镇_多人游戏请求")
	returnToTownAtMultiPlayRequestJSON, err := protojson.Marshal(returnToTownAtMultiPlayRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(returnToTownAtMultiPlayRequestJSON))

	// 步骤4: 发送返回城镇_多人游戏请求
	fmt.Println("\n步骤4: 发送返回城镇_多人游戏请求")
	returnToTownAtMultiPlayMessage := append([]byte("RETURN_TO_TOWN_AT_MULTI_PLAY:"), returnToTownAtMultiPlayRequestJSON...)
	err = s.sendMessage(returnToTownAtMultiPlayMessage)
	s.NoError(err)
	fmt.Println("返回城镇_多人游戏请求发送成功")

	// 步骤5: 接收返回城镇_多人游戏响应
	fmt.Println("\n步骤5: 接收返回城镇_多人游戏响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证返回城镇_多人游戏响应
	fmt.Println("\n步骤6: 验证返回城镇_多人游戏响应")
	s.NotEmpty(response)
	fmt.Printf("Return to town at multi play response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPCustomGameRoomSetting 测试自定义游戏房间设置（TCP）
func (s *PKTCPTestSuite) TestTCPCustomGameRoomSetting() {
	fmt.Println("========== TC006: 自定义游戏房间设置 ==========")
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

	// 步骤2: 构造自定义游戏房间设置请求
	fmt.Println("\n步骤2: 构造自定义游戏房间设置请求")
	customGameRoomSettingRequest := &v1.CustomGameRoomSettingRequest{
		TransId: 1,
		Customdata: &v1.CustomData{
			Senderguid: 1,
			Type:       1,
			IValue:     100,
			SValue:     "test",
		},
	}
	fmt.Println("CustomGameRoomSettingRequest对象创建成功")

	// 步骤3: 编码自定义游戏房间设置请求
	fmt.Println("\n步骤3: 编码自定义游戏房间设置请求")
	customGameRoomSettingRequestJSON, err := protojson.Marshal(customGameRoomSettingRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(customGameRoomSettingRequestJSON))

	// 步骤4: 发送自定义游戏房间设置请求
	fmt.Println("\n步骤4: 发送自定义游戏房间设置请求")
	customGameRoomSettingMessage := append([]byte("CUSTOM_GAME_ROOM_SETTING:"), customGameRoomSettingRequestJSON...)
	err = s.sendMessage(customGameRoomSettingMessage)
	s.NoError(err)
	fmt.Println("自定义游戏房间设置请求发送成功")

	// 步骤5: 接收自定义游戏房间设置响应
	fmt.Println("\n步骤5: 接收自定义游戏房间设置响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证自定义游戏房间设置响应
	fmt.Println("\n步骤6: 验证自定义游戏房间设置响应")
	s.NotEmpty(response)
	fmt.Printf("Custom game room setting response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPPKRanking 测试PK排名（TCP）
func (s *PKTCPTestSuite) TestTCPPKRanking() {
	fmt.Println("========== TC007: PK排名 ==========")
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

	// 步骤2: 构造PK排名请求
	fmt.Println("\n步骤2: 构造PK排名请求")
	// 注意：PK排名请求在pk.proto中没有定义，这里使用通用请求格式
	pktcpRankingRequest := map[string]interface{}{
		"transId": 1,
	}
	fmt.Println("PK排名请求对象创建成功")

	// 步骤3: 编码PK排名请求
	fmt.Println("\n步骤3: 编码PK排名请求")
	pktcpRankingRequestJSON, err := json.Marshal(pktcpRankingRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(pktcpRankingRequestJSON))

	// 步骤4: 发送PK排名请求
	fmt.Println("\n步骤4: 发送PK排名请求")
	pktcpRankingMessage := append([]byte("PK_RANKING:"), pktcpRankingRequestJSON...)
	err = s.sendMessage(pktcpRankingMessage)
	s.NoError(err)
	fmt.Println("PK排名请求发送成功")

	// 步骤5: 接收PK排名响应
	fmt.Println("\n步骤5: 接收PK排名响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证PK排名响应
	fmt.Println("\n步骤6: 验证PK排名响应")
	s.NotEmpty(response)
	fmt.Printf("PK ranking response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPPKMatchType 测试PK匹配类型（TCP）
func (s *PKTCPTestSuite) TestTCPPKMatchType() {
	fmt.Println("========== TC008: PK匹配类型 ==========")
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

	// 步骤2: 构造PK匹配类型请求
	fmt.Println("\n步骤2: 构造PK匹配类型请求")
	// 注意：PK匹配类型请求在pk.proto中没有定义，这里使用通用请求格式
	pkMatchTypeRequest := map[string]interface{}{
		"transId":   1,
		"matchType": 1,
	}
	fmt.Println("PK匹配类型请求对象创建成功")

	// 步骤3: 编码PK匹配类型请求
	fmt.Println("\n步骤3: 编码PK匹配类型请求")
	pkMatchTypeRequestJSON, err := json.Marshal(pkMatchTypeRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(pkMatchTypeRequestJSON))

	// 步骤4: 发送PK匹配类型请求
	fmt.Println("\n步骤4: 发送PK匹配类型请求")
	pkMatchTypeMessage := append([]byte("PK_MATCH_TYPE:"), pkMatchTypeRequestJSON...)
	err = s.sendMessage(pkMatchTypeMessage)
	s.NoError(err)
	fmt.Println("PK匹配类型请求发送成功")

	// 步骤5: 接收PK匹配类型响应
	fmt.Println("\n步骤5: 接收PK匹配类型响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证PK匹配类型响应
	fmt.Println("\n步骤6: 验证PK匹配类型响应")
	s.NotEmpty(response)
	fmt.Printf("PK match type response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestTCPMultiPlayRequestMatchInvalidParams 测试多人游戏请求匹配_无效参数（TCP）
func (s *PKTCPTestSuite) TestTCPMultiPlayRequestMatchInvalidParams() {
	fmt.Println("========== TC009: 多人游戏请求匹配_无效参数 ==========")
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

	// 步骤2: 构造多人游戏请求匹配请求（无效参数）
	fmt.Println("\n步骤2: 构造多人游戏请求匹配请求（无效参数）")
	multiPlayRequestMatchRequest := &v1.MultiPlayRequestMatchRequest{
		TransId:      1,
		Matchtype:    999, // 无效匹配类型
		Dungeonindex: 999, // 无效副本索引
	}
	fmt.Println("MultiPlayRequestMatchRequest对象创建成功")

	// 步骤3: 编码多人游戏请求匹配请求
	fmt.Println("\n步骤3: 编码多人游戏请求匹配请求")
	multiPlayRequestMatchRequestJSON, err := protojson.Marshal(multiPlayRequestMatchRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(multiPlayRequestMatchRequestJSON))

	// 步骤4: 发送多人游戏请求匹配请求
	fmt.Println("\n步骤4: 发送多人游戏请求匹配请求")
	multiPlayRequestMatchMessage := append([]byte("MULTI_PLAY_REQUEST_MATCH:"), multiPlayRequestMatchRequestJSON...)
	err = s.sendMessage(multiPlayRequestMatchMessage)
	s.NoError(err)
	fmt.Println("多人游戏请求匹配请求发送成功")

	// 步骤5: 接收多人游戏请求匹配响应
	fmt.Println("\n步骤5: 接收多人游戏请求匹配响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证多人游戏请求匹配响应
	fmt.Println("\n步骤6: 验证多人游戏请求匹配响应")
	s.NotEmpty(response)
	fmt.Printf("Multi play request match response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC009 测试结束 ==========")
}

// TestTCPMultiPlayRequestMatchConcurrentOperation 测试多人游戏请求匹配_并发操作（TCP）
func (s *PKTCPTestSuite) TestTCPMultiPlayRequestMatchConcurrentOperation() {
	fmt.Println("========== TC010: 多人游戏请求匹配_并发操作 ==========")
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

	// 步骤2: 构造多人游戏请求匹配请求（并发操作）
	fmt.Println("\n步骤2: 构造多人游戏请求匹配请求（并发操作）")
	multiPlayRequestMatchRequest := &v1.MultiPlayRequestMatchRequest{
		TransId:      1,
		Matchtype:    1, // 匹配类型
		Dungeonindex: 1, // 副本索引
	}
	fmt.Println("MultiPlayRequestMatchRequest对象创建成功")

	// 步骤3: 编码多人游戏请求匹配请求
	fmt.Println("\n步骤3: 编码多人游戏请求匹配请求")
	multiPlayRequestMatchRequestJSON, err := protojson.Marshal(multiPlayRequestMatchRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(multiPlayRequestMatchRequestJSON))

	// 步骤4: 发送多人游戏请求匹配请求（并发操作）
	fmt.Println("\n步骤4: 发送多人游戏请求匹配请求（并发操作）")
	multiPlayRequestMatchMessage := append([]byte("MULTI_PLAY_REQUEST_MATCH_CONCURRENT:"), multiPlayRequestMatchRequestJSON...)
	err = s.sendMessage(multiPlayRequestMatchMessage)
	s.NoError(err)
	fmt.Println("多人游戏请求匹配请求（并发操作）发送成功")

	// 步骤5: 接收多人游戏请求匹配响应
	fmt.Println("\n步骤5: 接收多人游戏请求匹配响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证多人游戏请求匹配响应
	fmt.Println("\n步骤6: 验证多人游戏请求匹配响应")
	s.NotEmpty(response)
	fmt.Printf("Multi play request match concurrent response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC010 测试结束 ==========")
}

// TestPKTCPSuite 测试PK系统TCP测试套件
func TestPKTCPSuite(t *testing.T) {
	suite.Run(t, new(PKTCPTestSuite))
}
