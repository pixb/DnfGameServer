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

// ShopTCPTestSuite 在线商城系统TCP测试套件
type ShopTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *ShopTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *ShopTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *ShopTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *ShopTCPTestSuite) sendMessage(message []byte) error {
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
func (s *ShopTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPGetShopItemList 测试获取商城商品列表（TCP）
func (s *ShopTCPTestSuite) TestTCPGetShopItemList() {
	fmt.Println("========== TC001: 获取商城商品列表 ==========")
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

	// 步骤2: 构造获取商城商品列表请求
	fmt.Println("\n步骤2: 构造获取商城商品列表请求")
	getShopListRequest := &v1.GetShopListRequest{
		ShopType: v1.ShopType_CERA_SHOP, // 1:NPC商店 2:点券商店 3:拍卖行 4:个人商店
		NpcId:    0, // NPC商店使用
	}
	fmt.Println("GetShopListRequest对象创建成功")

	// 步骤3: 编码获取商城商品列表请求
	fmt.Println("\n步骤3: 编码获取商城商品列表请求")
	getShopListRequestJSON, err := protojson.Marshal(getShopListRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getShopListRequestJSON))

	// 步骤4: 发送获取商城商品列表请求
	fmt.Println("\n步骤4: 发送获取商城商品列表请求")
	getShopListMessage := append([]byte("GET_SHOP_ITEM_LIST:"), getShopListRequestJSON...)
	err = s.sendMessage(getShopListMessage)
	s.NoError(err)
	fmt.Println("获取商城商品列表请求发送成功")

	// 步骤5: 接收获取商城商品列表响应
	fmt.Println("\n步骤5: 接收获取商城商品列表响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证获取商城商品列表响应
	fmt.Println("\n步骤6: 验证获取商城商品列表响应")
	s.NotEmpty(response)
	fmt.Printf("Get shop item list response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPBuyShopItem 测试购买商城商品（TCP）
func (s *ShopTCPTestSuite) TestTCPBuyShopItem() {
	fmt.Println("========== TC002: 购买商城商品 ==========")
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

	// 步骤2: 构造购买商城商品请求
	fmt.Println("\n步骤2: 构造购买商城商品请求")
	buyItemRequest := &v1.BuyItemRequest{
		ShopType: v1.ShopType_CERA_SHOP, // 1:NPC商店 2:点券商店 3:拍卖行 4:个人商店
		Slot:     1, // 商品槽位
		Count:    1, // 购买数量
	}
	fmt.Println("BuyItemRequest对象创建成功")

	// 步骤3: 编码购买商城商品请求
	fmt.Println("\n步骤3: 编码购买商城商品请求")
	buyItemRequestJSON, err := protojson.Marshal(buyItemRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(buyItemRequestJSON))

	// 步骤4: 发送购买商城商品请求
	fmt.Println("\n步骤4: 发送购买商城商品请求")
	buyItemMessage := append([]byte("BUY_SHOP_ITEM:"), buyItemRequestJSON...)
	err = s.sendMessage(buyItemMessage)
	s.NoError(err)
	fmt.Println("购买商城商品请求发送成功")

	// 步骤5: 接收购买商城商品响应
	fmt.Println("\n步骤5: 接收购买商城商品响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证购买商城商品响应
	fmt.Println("\n步骤6: 验证购买商城商品响应")
	s.NotEmpty(response)
	fmt.Printf("Buy shop item response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPGetShopItemDetail 测试获取商城商品详情（TCP）
func (s *ShopTCPTestSuite) TestTCPGetShopItemDetail() {
	fmt.Println("========== TC003: 获取商城商品详情 ==========")
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

	// 步骤2: 构造获取商城商品详情请求
	fmt.Println("\n步骤2: 构造获取商城商品详情请求")
	// 注意：获取商城商品详情请求在shop.proto中没有明确定义，这里使用通用请求格式
	getShopItemDetailRequest := map[string]interface{}{
		"shop_type": v1.ShopType_CERA_SHOP, // 1:NPC商店 2:点券商店 3:拍卖行 4:个人商店
		"slot":      1, // 商品槽位
	}
	fmt.Println("获取商城商品详情请求对象创建成功")

	// 步骤3: 编码获取商城商品详情请求
	fmt.Println("\n步骤3: 编码获取商城商品详情请求")
	getShopItemDetailRequestJSON, err := json.Marshal(getShopItemDetailRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getShopItemDetailRequestJSON))

	// 步骤4: 发送获取商城商品详情请求
	fmt.Println("\n步骤4: 发送获取商城商品详情请求")
	getShopItemDetailMessage := append([]byte("GET_SHOP_ITEM_DETAIL:"), getShopItemDetailRequestJSON...)
	err = s.sendMessage(getShopItemDetailMessage)
	s.NoError(err)
	fmt.Println("获取商城商品详情请求发送成功")

	// 步骤5: 接收获取商城商品详情响应
	fmt.Println("\n步骤5: 接收获取商城商品详情响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证获取商城商品详情响应
	fmt.Println("\n步骤6: 验证获取商城商品详情响应")
	s.NotEmpty(response)
	fmt.Printf("Get shop item detail response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPQueryShopOrder 测试查询商城订单（TCP）
func (s *ShopTCPTestSuite) TestTCPQueryShopOrder() {
	fmt.Println("========== TC004: 查询商城订单 ==========")
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

	// 步骤2: 构造查询商城订单请求
	fmt.Println("\n步骤2: 构造查询商城订单请求")
	// 注意：查询商城订单请求在shop.proto中没有明确定义，这里使用通用请求格式
	queryShopOrderRequest := map[string]interface{}{
		"order_id": 1, // 订单ID
	}
	fmt.Println("查询商城订单请求对象创建成功")

	// 步骤3: 编码查询商城订单请求
	fmt.Println("\n步骤3: 编码查询商城订单请求")
	queryShopOrderRequestJSON, err := json.Marshal(queryShopOrderRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(queryShopOrderRequestJSON))

	// 步骤4: 发送查询商城订单请求
	fmt.Println("\n步骤4: 发送查询商城订单请求")
	queryShopOrderMessage := append([]byte("QUERY_SHOP_ORDER:"), queryShopOrderRequestJSON...)
	err = s.sendMessage(queryShopOrderMessage)
	s.NoError(err)
	fmt.Println("查询商城订单请求发送成功")

	// 步骤5: 接收查询商城订单响应
	fmt.Println("\n步骤5: 接收查询商城订单响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证查询商城订单响应
	fmt.Println("\n步骤6: 验证查询商城订单响应")
	s.NotEmpty(response)
	fmt.Printf("Query shop order response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPCancelShopOrder 测试取消商城订单（TCP）
func (s *ShopTCPTestSuite) TestTCPCancelShopOrder() {
	fmt.Println("========== TC005: 取消商城订单 ==========")
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

	// 步骤2: 构造取消商城订单请求
	fmt.Println("\n步骤2: 构造取消商城订单请求")
	// 注意：取消商城订单请求在shop.proto中没有明确定义，这里使用通用请求格式
	cancelShopOrderRequest := map[string]interface{}{
		"order_id": 1, // 订单ID
	}
	fmt.Println("取消商城订单请求对象创建成功")

	// 步骤3: 编码取消商城订单请求
	fmt.Println("\n步骤3: 编码取消商城订单请求")
	cancelShopOrderRequestJSON, err := json.Marshal(cancelShopOrderRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(cancelShopOrderRequestJSON))

	// 步骤4: 发送取消商城订单请求
	fmt.Println("\n步骤4: 发送取消商城订单请求")
	cancelShopOrderMessage := append([]byte("CANCEL_SHOP_ORDER:"), cancelShopOrderRequestJSON...)
	err = s.sendMessage(cancelShopOrderMessage)
	s.NoError(err)
	fmt.Println("取消商城订单请求发送成功")

	// 步骤5: 接收取消商城订单响应
	fmt.Println("\n步骤5: 接收取消商城订单响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证取消商城订单响应
	fmt.Println("\n步骤6: 验证取消商城订单响应")
	s.NotEmpty(response)
	fmt.Printf("Cancel shop order response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestShopTCPSuite 测试在线商城系统TCP测试套件
func TestShopTCPSuite(t *testing.T) {
	suite.Run(t, new(ShopTCPTestSuite))
}