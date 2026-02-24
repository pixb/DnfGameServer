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

// BagTCPTestSuite 背包系统TCP测试套件
type BagTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	seq        byte
	authKey    string
	serverHost string
	serverPort int
}

// SetupSuite 设置测试套件
func (s *BagTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = 9000
	s.seq = 0
}

// TearDownSuite 清理测试套件
func (s *BagTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// connectTCP 连接到TCP服务器
func (s *BagTCPTestSuite) connectTCP() error {
	addr := fmt.Sprintf("%s:%d", s.serverHost, s.serverPort)
	conn, err := net.DialTimeout("tcp", addr, 10*time.Second)
	if err != nil {
		return err
	}
	s.socket = conn
	return nil
}

// sendMessage 发送消息到TCP服务器
func (s *BagTCPTestSuite) sendMessage(message []byte) error {
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
func (s *BagTCPTestSuite) receiveMessage() ([]byte, error) {
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

// TestTCPGetBagInfo 测试获取背包信息（TCP）
func (s *BagTCPTestSuite) TestTCPGetBagInfo() {
	fmt.Println("========== TC001: 获取背包信息 ==========")
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

	// 步骤2: 构造获取背包信息请求
	fmt.Println("\n步骤2: 构造获取背包信息请求")
	getBagRequest := &v1.GetBagRequest{
		BagType: 1, // 1:装备 2:消耗品 3:材料 4:任务
	}
	fmt.Println("GetBagRequest对象创建成功")

	// 步骤3: 编码获取背包信息请求
	fmt.Println("\n步骤3: 编码获取背包信息请求")
	getBagRequestJSON, err := protojson.Marshal(getBagRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(getBagRequestJSON))

	// 步骤4: 发送获取背包信息请求
	fmt.Println("\n步骤4: 发送获取背包信息请求")
	getBagMessage := append([]byte("GET_BAG_INFO:"), getBagRequestJSON...)
	err = s.sendMessage(getBagMessage)
	s.NoError(err)
	fmt.Println("获取背包信息请求发送成功")

	// 步骤5: 接收获取背包信息响应
	fmt.Println("\n步骤5: 接收获取背包信息响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证获取背包信息响应
	fmt.Println("\n步骤6: 验证获取背包信息响应")
	s.NotEmpty(response)
	fmt.Printf("Get bag info response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC001 测试结束 ==========")
}

// TestTCPUseItem 测试使用物品（TCP）
func (s *BagTCPTestSuite) TestTCPUseItem() {
	fmt.Println("========== TC002: 使用物品 ==========")
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

	// 步骤2: 构造使用物品请求
	fmt.Println("\n步骤2: 构造使用物品请求")
	useItemRequest := &v1.UseItemRequest{
		Guid:  1, // 物品GUID
		Count: 1, // 使用数量
	}
	fmt.Println("UseItemRequest对象创建成功")

	// 步骤3: 编码使用物品请求
	fmt.Println("\n步骤3: 编码使用物品请求")
	useItemRequestJSON, err := protojson.Marshal(useItemRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(useItemRequestJSON))

	// 步骤4: 发送使用物品请求
	fmt.Println("\n步骤4: 发送使用物品请求")
	useItemMessage := append([]byte("USE_ITEM:"), useItemRequestJSON...)
	err = s.sendMessage(useItemMessage)
	s.NoError(err)
	fmt.Println("使用物品请求发送成功")

	// 步骤5: 接收使用物品响应
	fmt.Println("\n步骤5: 接收使用物品响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证使用物品响应
	fmt.Println("\n步骤6: 验证使用物品响应")
	s.NotEmpty(response)
	fmt.Printf("Use item response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC002 测试结束 ==========")
}

// TestTCPDropItem 测试丢弃物品（TCP）
func (s *BagTCPTestSuite) TestTCPDropItem() {
	fmt.Println("========== TC003: 丢弃物品 ==========")
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

	// 步骤2: 构造丢弃物品请求
	fmt.Println("\n步骤2: 构造丢弃物品请求")
	// 注意：丢弃物品请求在item.proto中没有明确定义，这里使用通用请求格式
	dropItemRequest := map[string]interface{}{
		"guid":  1, // 物品GUID
		"count": 1, // 丢弃数量
	}
	fmt.Println("丢弃物品请求对象创建成功")

	// 步骤3: 编码丢弃物品请求
	fmt.Println("\n步骤3: 编码丢弃物品请求")
	dropItemRequestJSON, err := json.Marshal(dropItemRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(dropItemRequestJSON))

	// 步骤4: 发送丢弃物品请求
	fmt.Println("\n步骤4: 发送丢弃物品请求")
	dropItemMessage := append([]byte("DROP_ITEM:"), dropItemRequestJSON...)
	err = s.sendMessage(dropItemMessage)
	s.NoError(err)
	fmt.Println("丢弃物品请求发送成功")

	// 步骤5: 接收丢弃物品响应
	fmt.Println("\n步骤5: 接收丢弃物品响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证丢弃物品响应
	fmt.Println("\n步骤6: 验证丢弃物品响应")
	s.NotEmpty(response)
	fmt.Printf("Drop item response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC003 测试结束 ==========")
}

// TestTCPItemCompose 测试物品合成（TCP）
func (s *BagTCPTestSuite) TestTCPItemCompose() {
	fmt.Println("========== TC004: 物品合成 ==========")
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

	// 步骤2: 构造物品合成请求
	fmt.Println("\n步骤2: 构造物品合成请求")
	// 注意：物品合成请求在item.proto中没有明确定义，这里使用通用请求格式
	itemComposeRequest := map[string]interface{}{
		"compose_id": 1, // 合成ID
		"materials": []map[string]interface{}{ // 材料列表
			{"item_id": 1, "count": 1},
			{"item_id": 2, "count": 1},
		},
	}
	fmt.Println("物品合成请求对象创建成功")

	// 步骤3: 编码物品合成请求
	fmt.Println("\n步骤3: 编码物品合成请求")
	itemComposeRequestJSON, err := json.Marshal(itemComposeRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(itemComposeRequestJSON))

	// 步骤4: 发送物品合成请求
	fmt.Println("\n步骤4: 发送物品合成请求")
	itemComposeMessage := append([]byte("ITEM_COMPOSE:"), itemComposeRequestJSON...)
	err = s.sendMessage(itemComposeMessage)
	s.NoError(err)
	fmt.Println("物品合成请求发送成功")

	// 步骤5: 接收物品合成响应
	fmt.Println("\n步骤5: 接收物品合成响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证物品合成响应
	fmt.Println("\n步骤6: 验证物品合成响应")
	s.NotEmpty(response)
	fmt.Printf("Item compose response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC004 测试结束 ==========")
}

// TestTCPItemReinforce 测试物品强化（TCP）
func (s *BagTCPTestSuite) TestTCPItemReinforce() {
	fmt.Println("========== TC005: 物品强化 ==========")
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

	// 步骤2: 构造物品强化请求
	fmt.Println("\n步骤2: 构造物品强化请求")
	// 注意：物品强化请求在item.proto中没有明确定义，这里使用通用请求格式
	itemReinforceRequest := map[string]interface{}{
		"guid": 1, // 物品GUID
		"level": 1, // 强化等级
	}
	fmt.Println("物品强化请求对象创建成功")

	// 步骤3: 编码物品强化请求
	fmt.Println("\n步骤3: 编码物品强化请求")
	itemReinforceRequestJSON, err := json.Marshal(itemReinforceRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(itemReinforceRequestJSON))

	// 步骤4: 发送物品强化请求
	fmt.Println("\n步骤4: 发送物品强化请求")
	itemReinforceMessage := append([]byte("ITEM_REINFORCE:"), itemReinforceRequestJSON...)
	err = s.sendMessage(itemReinforceMessage)
	s.NoError(err)
	fmt.Println("物品强化请求发送成功")

	// 步骤5: 接收物品强化响应
	fmt.Println("\n步骤5: 接收物品强化响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证物品强化响应
	fmt.Println("\n步骤6: 验证物品强化响应")
	s.NotEmpty(response)
	fmt.Printf("Item reinforce response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC005 测试结束 ==========")
}

// TestTCPItemTransfer 测试物品转移（TCP）
func (s *BagTCPTestSuite) TestTCPItemTransfer() {
	fmt.Println("========== TC006: 物品转移 ==========")
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

	// 步骤2: 构造物品转移请求
	fmt.Println("\n步骤2: 构造物品转移请求")
	moveItemRequest := &v1.MoveItemRequest{
		Guid:    1, // 物品GUID
		FromBag: 1, // 源背包类型
		ToBag:   2, // 目标背包类型
		ToSlot:  1, // 目标槽位
	}
	fmt.Println("MoveItemRequest对象创建成功")

	// 步骤3: 编码物品转移请求
	fmt.Println("\n步骤3: 编码物品转移请求")
	moveItemRequestJSON, err := protojson.Marshal(moveItemRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(moveItemRequestJSON))

	// 步骤4: 发送物品转移请求
	fmt.Println("\n步骤4: 发送物品转移请求")
	moveItemMessage := append([]byte("ITEM_TRANSFER:"), moveItemRequestJSON...)
	err = s.sendMessage(moveItemMessage)
	s.NoError(err)
	fmt.Println("物品转移请求发送成功")

	// 步骤5: 接收物品转移响应
	fmt.Println("\n步骤5: 接收物品转移响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证物品转移响应
	fmt.Println("\n步骤6: 验证物品转移响应")
	s.NotEmpty(response)
	fmt.Printf("Item transfer response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC006 测试结束 ==========")
}

// TestTCPItemSort 测试物品整理（TCP）
func (s *BagTCPTestSuite) TestTCPItemSort() {
	fmt.Println("========== TC007: 物品整理 ==========")
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

	// 步骤2: 构造物品整理请求
	fmt.Println("\n步骤2: 构造物品整理请求")
	// 注意：物品整理请求在item.proto中没有明确定义，这里使用通用请求格式
	itemSortRequest := map[string]interface{}{
		"bag_type": 1, // 背包类型
	}
	fmt.Println("物品整理请求对象创建成功")

	// 步骤3: 编码物品整理请求
	fmt.Println("\n步骤3: 编码物品整理请求")
	itemSortRequestJSON, err := json.Marshal(itemSortRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(itemSortRequestJSON))

	// 步骤4: 发送物品整理请求
	fmt.Println("\n步骤4: 发送物品整理请求")
	itemSortMessage := append([]byte("ITEM_SORT:"), itemSortRequestJSON...)
	err = s.sendMessage(itemSortMessage)
	s.NoError(err)
	fmt.Println("物品整理请求发送成功")

	// 步骤5: 接收物品整理响应
	fmt.Println("\n步骤5: 接收物品整理响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证物品整理响应
	fmt.Println("\n步骤6: 验证物品整理响应")
	s.NotEmpty(response)
	fmt.Printf("Item sort response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC007 测试结束 ==========")
}

// TestTCPItemDecompose 测试物品分解（TCP）
func (s *BagTCPTestSuite) TestTCPItemDecompose() {
	fmt.Println("========== TC008: 物品分解 ==========")
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

	// 步骤2: 构造物品分解请求
	fmt.Println("\n步骤2: 构造物品分解请求")
	// 注意：物品分解请求在item.proto中没有明确定义，这里使用通用请求格式
	itemDecomposeRequest := map[string]interface{}{
		"guid": 1, // 物品GUID
	}
	fmt.Println("物品分解请求对象创建成功")

	// 步骤3: 编码物品分解请求
	fmt.Println("\n步骤3: 编码物品分解请求")
	itemDecomposeRequestJSON, err := json.Marshal(itemDecomposeRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(itemDecomposeRequestJSON))

	// 步骤4: 发送物品分解请求
	fmt.Println("\n步骤4: 发送物品分解请求")
	itemDecomposeMessage := append([]byte("ITEM_DECOMPOSE:"), itemDecomposeRequestJSON...)
	err = s.sendMessage(itemDecomposeMessage)
	s.NoError(err)
	fmt.Println("物品分解请求发送成功")

	// 步骤5: 接收物品分解响应
	fmt.Println("\n步骤5: 接收物品分解响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证物品分解响应
	fmt.Println("\n步骤6: 验证物品分解响应")
	s.NotEmpty(response)
	fmt.Printf("Item decompose response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC008 测试结束 ==========")
}

// TestTCPItemRename 测试物品重命名（TCP）
func (s *BagTCPTestSuite) TestTCPItemRename() {
	fmt.Println("========== TC009: 物品重命名 ==========")
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

	// 步骤2: 构造物品重命名请求
	fmt.Println("\n步骤2: 构造物品重命名请求")
	// 注意：物品重命名请求在item.proto中没有明确定义，这里使用通用请求格式
	itemRenameRequest := map[string]interface{}{
		"guid":  1, // 物品GUID
		"name": "新名称", // 新名称
	}
	fmt.Println("物品重命名请求对象创建成功")

	// 步骤3: 编码物品重命名请求
	fmt.Println("\n步骤3: 编码物品重命名请求")
	itemRenameRequestJSON, err := json.Marshal(itemRenameRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(itemRenameRequestJSON))

	// 步骤4: 发送物品重命名请求
	fmt.Println("\n步骤4: 发送物品重命名请求")
	itemRenameMessage := append([]byte("ITEM_RENAME:"), itemRenameRequestJSON...)
	err = s.sendMessage(itemRenameMessage)
	s.NoError(err)
	fmt.Println("物品重命名请求发送成功")

	// 步骤5: 接收物品重命名响应
	fmt.Println("\n步骤5: 接收物品重命名响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证物品重命名响应
	fmt.Println("\n步骤6: 验证物品重命名响应")
	s.NotEmpty(response)
	fmt.Printf("Item rename response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC009 测试结束 ==========")
}

// TestTCPBagExpand 测试背包扩容（TCP）
func (s *BagTCPTestSuite) TestTCPBagExpand() {
	fmt.Println("========== TC010: 背包扩容 ==========")
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

	// 步骤2: 构造背包扩容请求
	fmt.Println("\n步骤2: 构造背包扩容请求")
	// 注意：背包扩容请求在item.proto中没有明确定义，这里使用通用请求格式
	bagExpandRequest := map[string]interface{}{
		"bag_type": 1, // 背包类型
		"slots":    10, // 扩容槽位数
	}
	fmt.Println("背包扩容请求对象创建成功")

	// 步骤3: 编码背包扩容请求
	fmt.Println("\n步骤3: 编码背包扩容请求")
	bagExpandRequestJSON, err := json.Marshal(bagExpandRequest)
	s.NoError(err)
	fmt.Printf("编码成功，数据长度: %d\n", len(bagExpandRequestJSON))

	// 步骤4: 发送背包扩容请求
	fmt.Println("\n步骤4: 发送背包扩容请求")
	bagExpandMessage := append([]byte("BAG_EXPAND:"), bagExpandRequestJSON...)
	err = s.sendMessage(bagExpandMessage)
	s.NoError(err)
	fmt.Println("背包扩容请求发送成功")

	// 步骤5: 接收背包扩容响应
	fmt.Println("\n步骤5: 接收背包扩容响应")
	response, err := s.receiveMessage()
	s.NoError(err)
	s.NotNil(response)
	fmt.Printf("接收响应成功，数据长度: %d\n", len(response))

	// 步骤6: 验证背包扩容响应
	fmt.Println("\n步骤6: 验证背包扩容响应")
	s.NotEmpty(response)
	fmt.Printf("Bag expand response: %s\n", string(response))

	// 关闭连接
	s.socket.Close()
	fmt.Println("========== TC010 测试结束 ==========")
}

// TestBagTCPSuite 测试背包系统TCP测试套件
func TestBagTCPSuite(t *testing.T) {
	suite.Run(t, new(BagTCPTestSuite))
}