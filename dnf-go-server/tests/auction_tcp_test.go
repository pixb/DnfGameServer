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

// AuctionTCPTestSuite 拍卖系统TCP测试套件
type AuctionTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *AuctionTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *AuctionTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *AuctionTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *AuctionTCPTestSuite) sendMessage(message []byte) error {
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
func (s *AuctionTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPRegisterAuctionItem 测试注册拍卖物品（TCP）
func (s *AuctionTCPTestSuite) TestTCPRegisterAuctionItem() {
	fmt.Println("========== TC001: 注册拍卖物品 ==========")
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

	// 步骤2: 构造注册拍卖物品请求
	fmt.Println("\n步骤2: 构造注册拍卖物品请求")
	registerAuctionItemRequest := map[string]interface{}{
		"item_id": 1, // 物品ID
		"item_name": "测试物品", // 物品名称
		"item_level": 10, // 物品等级
		"start_price": 100, // 起拍价
		"buyout_price": 1000, // 一口价
		"auction_duration": 3600, // 拍卖时长（秒）
		"seller_id": 1, // 卖家ID
	}
	fmt.Println("注册拍卖物品请求对象创建成功")

	// 步骤3: 编码注册拍卖物品请求
	fmt.Println("\n步骤3: 编码注册拍卖物品请求")
	registerAuctionItemRequestJSON, err := json.Marshal(registerAuctionItemRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(registerAuctionItemRequestJSON))

	// 步骤4: 发送注册拍卖物品请求
	fmt.Println("\n步骤4: 发送注册拍卖物品请求")
	registerAuctionItemMessage := append([]byte("REGISTER_AUCTION_ITEM:"), registerAuctionItemRequestJSON...)
	err = s.sendMessage(registerAuctionItemMessage)
	s.NoError(err)
	fmt.Println("注册拍卖物品请求发送成功")

	// 步骤5: 接收注册拍卖物品响应
	fmt.Println("\n步骤5: 接收注册拍卖物品响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证注册拍卖物品响应
	fmt.Println("\n步骤6: 验证注册拍卖物品响应")
	s.NotEmpty(response)
	fmt.Printf("Register auction item response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPBuyoutAuction 测试一口价购买（TCP）
func (s *AuctionTCPTestSuite) TestTCPBuyoutAuction() {
	fmt.Println("========== TC002: 一口价购买 ==========")
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

	// 步骤2: 构造一口价购买请求
	fmt.Println("\n步骤2: 构造一口价购买请求")
	buyoutAuctionRequest := map[string]interface{}{
		"auction_id": 1, // 拍卖ID
		"buyer_id": 2, // 买家ID
		"buyout_price": 1000, // 一口价
	}
	fmt.Println("一口价购买请求对象创建成功")

	// 步骤3: 编码一口价购买请求
	fmt.Println("\n步骤3: 编码一口价购买请求")
	buyoutAuctionRequestJSON, err := json.Marshal(buyoutAuctionRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(buyoutAuctionRequestJSON))

	// 步骤4: 发送一口价购买请求
	fmt.Println("\n步骤4: 发送一口价购买请求")
	buyoutAuctionMessage := append([]byte("BUYOUT_AUCTION:"), buyoutAuctionRequestJSON...)
	err = s.sendMessage(buyoutAuctionMessage)
	s.NoError(err)
	fmt.Println("一口价购买请求发送成功")

	// 步骤5: 接收一口价购买响应
	fmt.Println("\n步骤5: 接收一口价购买响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证一口价购买响应
	fmt.Println("\n步骤6: 验证一口价购买响应")
	s.NotEmpty(response)
	fmt.Printf("Buyout auction response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPBidAuction 测试竞价（TCP）
func (s *AuctionTCPTestSuite) TestTCPBidAuction() {
	fmt.Println("========== TC003: 竞价 ==========")
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

	// 步骤2: 构造竞价请求
	fmt.Println("\n步骤2: 构造竞价请求")
	bidAuctionRequest := map[string]interface{}{
		"auction_id": 1, // 拍卖ID
		"bidder_id": 2, // 竞价者ID
		"bid_price": 200, // 竞价价格
	}
	fmt.Println("竞价请求对象创建成功")

	// 步骤3: 编码竞价请求
	fmt.Println("\n步骤3: 编码竞价请求")
	bidAuctionRequestJSON, err := json.Marshal(bidAuctionRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(bidAuctionRequestJSON))

	// 步骤4: 发送竞价请求
	fmt.Println("\n步骤4: 发送竞价请求")
	bidAuctionMessage := append([]byte("BID_AUCTION:"), bidAuctionRequestJSON...)
	err = s.sendMessage(bidAuctionMessage)
	s.NoError(err)
	fmt.Println("竞价请求发送成功")

	// 步骤5: 接收竞价响应
	fmt.Println("\n步骤5: 接收竞价响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证竞价响应
	fmt.Println("\n步骤6: 验证竞价响应")
	s.NotEmpty(response)
	fmt.Printf("Bid auction response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPCancelAuction 测试取消拍卖（TCP）
func (s *AuctionTCPTestSuite) TestTCPCancelAuction() {
	fmt.Println("========== TC004: 取消拍卖 ==========")
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

	// 步骤2: 构造取消拍卖请求
	fmt.Println("\n步骤2: 构造取消拍卖请求")
	cancelAuctionRequest := map[string]interface{}{
		"auction_id": 1, // 拍卖ID
		"seller_id": 1, // 卖家ID
	}
	fmt.Println("取消拍卖请求对象创建成功")

	// 步骤3: 编码取消拍卖请求
	fmt.Println("\n步骤3: 编码取消拍卖请求")
	cancelAuctionRequestJSON, err := json.Marshal(cancelAuctionRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(cancelAuctionRequestJSON))

	// 步骤4: 发送取消拍卖请求
	fmt.Println("\n步骤4: 发送取消拍卖请求")
	cancelAuctionMessage := append([]byte("CANCEL_AUCTION:"), cancelAuctionRequestJSON...)
	err = s.sendMessage(cancelAuctionMessage)
	s.NoError(err)
	fmt.Println("取消拍卖请求发送成功")

	// 步骤5: 接收取消拍卖响应
	fmt.Println("\n步骤5: 接收取消拍卖响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证取消拍卖响应
	fmt.Println("\n步骤6: 验证取消拍卖响应")
	s.NotEmpty(response)
	fmt.Printf("Cancel auction response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPAuctionEnd 测试拍卖结束（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionEnd() {
	fmt.Println("========== TC005: 拍卖结束 ==========")
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

	// 步骤2: 构造拍卖结束请求
	fmt.Println("\n步骤2: 构造拍卖结束请求")
	auctionEndRequest := map[string]interface{}{
		"auction_id": 1, // 拍卖ID
	}
	fmt.Println("拍卖结束请求对象创建成功")

	// 步骤3: 编码拍卖结束请求
	fmt.Println("\n步骤3: 编码拍卖结束请求")
	auctionEndRequestJSON, err := json.Marshal(auctionEndRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionEndRequestJSON))

	// 步骤4: 发送拍卖结束请求
	fmt.Println("\n步骤4: 发送拍卖结束请求")
	auctionEndMessage := append([]byte("AUCTION_END:"), auctionEndRequestJSON...)
	err = s.sendMessage(auctionEndMessage)
	s.NoError(err)
	fmt.Println("拍卖结束请求发送成功")

	// 步骤5: 接收拍卖结束响应
	fmt.Println("\n步骤5: 接收拍卖结束响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖结束响应
	fmt.Println("\n步骤6: 验证拍卖结束响应")
	s.NotEmpty(response)
	fmt.Printf("Auction end response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPAuctionList 测试拍卖列表（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionList() {
	fmt.Println("========== TC006: 拍卖列表 ==========")
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

	// 步骤2: 构造拍卖列表请求
	fmt.Println("\n步骤2: 构造拍卖列表请求")
	auctionListRequest := map[string]interface{}{
		"page": 1, // 页码
		"page_size": 10, // 每页大小
		"sort_by": "price", // 排序方式
		"sort_order": "asc", // 排序顺序
	}
	fmt.Println("拍卖列表请求对象创建成功")

	// 步骤3: 编码拍卖列表请求
	fmt.Println("\n步骤3: 编码拍卖列表请求")
	auctionListRequestJSON, err := json.Marshal(auctionListRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionListRequestJSON))

	// 步骤4: 发送拍卖列表请求
	fmt.Println("\n步骤4: 发送拍卖列表请求")
	auctionListMessage := append([]byte("AUCTION_LIST:"), auctionListRequestJSON...)
	err = s.sendMessage(auctionListMessage)
	s.NoError(err)
	fmt.Println("拍卖列表请求发送成功")

	// 步骤5: 接收拍卖列表响应
	fmt.Println("\n步骤5: 接收拍卖列表响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖列表响应
	fmt.Println("\n步骤6: 验证拍卖列表响应")
	s.NotEmpty(response)
	fmt.Printf("Auction list response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPAuctionSearch 测试拍卖搜索（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionSearch() {
	fmt.Println("========== TC007: 拍卖搜索 ==========")
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

	// 步骤2: 构造拍卖搜索请求
	fmt.Println("\n步骤2: 构造拍卖搜索请求")
	auctionSearchRequest := map[string]interface{}{
		"keyword": "测试", // 搜索关键词
		"page": 1, // 页码
		"page_size": 10, // 每页大小
	}
	fmt.Println("拍卖搜索请求对象创建成功")

	// 步骤3: 编码拍卖搜索请求
	fmt.Println("\n步骤3: 编码拍卖搜索请求")
	auctionSearchRequestJSON, err := json.Marshal(auctionSearchRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionSearchRequestJSON))

	// 步骤4: 发送拍卖搜索请求
	fmt.Println("\n步骤4: 发送拍卖搜索请求")
	auctionSearchMessage := append([]byte("AUCTION_SEARCH:"), auctionSearchRequestJSON...)
	err = s.sendMessage(auctionSearchMessage)
	s.NoError(err)
	fmt.Println("拍卖搜索请求发送成功")

	// 步骤5: 接收拍卖搜索响应
	fmt.Println("\n步骤5: 接收拍卖搜索响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖搜索响应
	fmt.Println("\n步骤6: 验证拍卖搜索响应")
	s.NotEmpty(response)
	fmt.Printf("Auction search response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPAuctionCategory 测试拍卖分类（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionCategory() {
	fmt.Println("========== TC008: 拍卖分类 ==========")
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

	// 步骤2: 构造拍卖分类请求
	fmt.Println("\n步骤2: 构造拍卖分类请求")
	auctionCategoryRequest := map[string]interface{}{
		"category_id": 1, // 分类ID
		"page": 1, // 页码
		"page_size": 10, // 每页大小
	}
	fmt.Println("拍卖分类请求对象创建成功")

	// 步骤3: 编码拍卖分类请求
	fmt.Println("\n步骤3: 编码拍卖分类请求")
	auctionCategoryRequestJSON, err := json.Marshal(auctionCategoryRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionCategoryRequestJSON))

	// 步骤4: 发送拍卖分类请求
	fmt.Println("\n步骤4: 发送拍卖分类请求")
	auctionCategoryMessage := append([]byte("AUCTION_CATEGORY:"), auctionCategoryRequestJSON...)
	err = s.sendMessage(auctionCategoryMessage)
	s.NoError(err)
	fmt.Println("拍卖分类请求发送成功")

	// 步骤5: 接收拍卖分类响应
	fmt.Println("\n步骤5: 接收拍卖分类响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖分类响应
	fmt.Println("\n步骤6: 验证拍卖分类响应")
	s.NotEmpty(response)
	fmt.Printf("Auction category response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestTCPAuctionDetail 测试拍卖详情（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionDetail() {
	fmt.Println("========== TC009: 拍卖详情 ==========")
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

	// 步骤2: 构造拍卖详情请求
	fmt.Println("\n步骤2: 构造拍卖详情请求")
	auctionDetailRequest := map[string]interface{}{
		"auction_id": 1, // 拍卖ID
	}
	fmt.Println("拍卖详情请求对象创建成功")

	// 步骤3: 编码拍卖详情请求
	fmt.Println("\n步骤3: 编码拍卖详情请求")
	auctionDetailRequestJSON, err := json.Marshal(auctionDetailRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionDetailRequestJSON))

	// 步骤4: 发送拍卖详情请求
	fmt.Println("\n步骤4: 发送拍卖详情请求")
	auctionDetailMessage := append([]byte("AUCTION_DETAIL:"), auctionDetailRequestJSON...)
	err = s.sendMessage(auctionDetailMessage)
	s.NoError(err)
	fmt.Println("拍卖详情请求发送成功")

	// 步骤5: 接收拍卖详情响应
	fmt.Println("\n步骤5: 接收拍卖详情响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖详情响应
	fmt.Println("\n步骤6: 验证拍卖详情响应")
	s.NotEmpty(response)
	fmt.Printf("Auction detail response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC009 测试结束 ==========")
}

// TestTCPAuctionRecord 测试拍卖记录（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionRecord() {
	fmt.Println("========== TC010: 拍卖记录 ==========")
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

	// 步骤2: 构造拍卖记录请求
	fmt.Println("\n步骤2: 构造拍卖记录请求")
	auctionRecordRequest := map[string]interface{}{
		"user_id": 1, // 用户ID
		"record_type": "seller", // 记录类型（seller/buyer）
		"page": 1, // 页码
		"page_size": 10, // 每页大小
	}
	fmt.Println("拍卖记录请求对象创建成功")

	// 步骤3: 编码拍卖记录请求
	fmt.Println("\n步骤3: 编码拍卖记录请求")
	auctionRecordRequestJSON, err := json.Marshal(auctionRecordRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionRecordRequestJSON))

	// 步骤4: 发送拍卖记录请求
	fmt.Println("\n步骤4: 发送拍卖记录请求")
	auctionRecordMessage := append([]byte("AUCTION_RECORD:"), auctionRecordRequestJSON...)
	err = s.sendMessage(auctionRecordMessage)
	s.NoError(err)
	fmt.Println("拍卖记录请求发送成功")

	// 步骤5: 接收拍卖记录响应
	fmt.Println("\n步骤5: 接收拍卖记录响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖记录响应
	fmt.Println("\n步骤6: 验证拍卖记录响应")
	s.NotEmpty(response)
	fmt.Printf("Auction record response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC010 测试结束 ==========")
}

// TestTCPAuctionFee 测试拍卖手续费（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionFee() {
	fmt.Println("========== TC011: 拍卖手续费 ==========")
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

	// 步骤2: 构造拍卖手续费请求
	fmt.Println("\n步骤2: 构造拍卖手续费请求")
	auctionFeeRequest := map[string]interface{}{
		"auction_id": 1, // 拍卖ID
		"seller_id": 1, // 卖家ID
	}
	fmt.Println("拍卖手续费请求对象创建成功")

	// 步骤3: 编码拍卖手续费请求
	fmt.Println("\n步骤3: 编码拍卖手续费请求")
	auctionFeeRequestJSON, err := json.Marshal(auctionFeeRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionFeeRequestJSON))

	// 步骤4: 发送拍卖手续费请求
	fmt.Println("\n步骤4: 发送拍卖手续费请求")
	auctionFeeMessage := append([]byte("AUCTION_FEE:"), auctionFeeRequestJSON...)
	err = s.sendMessage(auctionFeeMessage)
	s.NoError(err)
	fmt.Println("拍卖手续费请求发送成功")

	// 步骤5: 接收拍卖手续费响应
	fmt.Println("\n步骤5: 接收拍卖手续费响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖手续费响应
	fmt.Println("\n步骤6: 验证拍卖手续费响应")
	s.NotEmpty(response)
	fmt.Printf("Auction fee response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC011 测试结束 ==========")
}

// TestTCPAuctionStatistic 测试拍卖统计（TCP）
func (s *AuctionTCPTestSuite) TestTCPAuctionStatistic() {
	fmt.Println("========== TC012: 拍卖统计 ==========")
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

	// 步骤2: 构造拍卖统计请求
	fmt.Println("\n步骤2: 构造拍卖统计请求")
	auctionStatisticRequest := map[string]interface{}{
		"start_time": time.Now().Add(-24 * time.Hour).Unix(), // 开始时间
		"end_time": time.Now().Unix(), // 结束时间
		"statistic_type": "sales", // 统计类型
	}
	fmt.Println("拍卖统计请求对象创建成功")

	// 步骤3: 编码拍卖统计请求
	fmt.Println("\n步骤3: 编码拍卖统计请求")
	auctionStatisticRequestJSON, err := json.Marshal(auctionStatisticRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(auctionStatisticRequestJSON))

	// 步骤4: 发送拍卖统计请求
	fmt.Println("\n步骤4: 发送拍卖统计请求")
	actionStatisticMessage := append([]byte("AUCTION_STATISTIC:"), auctionStatisticRequestJSON...)
	err = s.sendMessage(actionStatisticMessage)
	s.NoError(err)
	fmt.Println("拍卖统计请求发送成功")

	// 步骤5: 接收拍卖统计响应
	fmt.Println("\n步骤5: 接收拍卖统计响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证拍卖统计响应
	fmt.Println("\n步骤6: 验证拍卖统计响应")
	s.NotEmpty(response)
	fmt.Printf("Auction statistic response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC012 测试结束 ==========")
}

// TestAuctionTCPSuite 测试拍卖系统TCP测试套件
func TestAuctionTCPSuite(t *testing.T) {
	suite.Run(t, new(AuctionTCPTestSuite))
}