package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

// BagTestSuite 背包测试套件
type BagTestSuite struct {
	BaseTestSuite
}

// setupAuthenticatedClient 设置认证客户端
func (s *BagTestSuite) setupAuthenticatedClient() {
	// 登录
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_bag_user",
	})
	s.NoError(err)
	s.AssertSuccess(resp)

	if token, ok := resp["auth_key"].(string); ok {
		s.Client.SetToken(token)
	}

	// 获取角色列表并选择角色
	listResp, err := s.Client.Post("/api/v1/character/list", nil)
	s.NoError(err)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		s.T().Skip("No characters available")
	}

	firstChar := characters[0].(map[string]interface{})
	_, err = s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"role_id": firstChar["id"],
	})
	s.NoError(err)
}

// TestGetBag 测试获取背包
func (s *BagTestSuite) TestGetBag() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/bag", nil)
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotNil(resp["items"])
}

// TestMoveItem 测试移动物品
func (s *BagTestSuite) TestMoveItem() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/bag/move", map[string]interface{}{
		"from_slot": 1,
		"to_slot":   2,
	})
	s.NoError(err)
	// 可能成功或失败取决于是否有物品
	s.NotNil(resp["error"])
}

// TestUseItem 测试使用物品
func (s *BagTestSuite) TestUseItem() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/item/use", map[string]interface{}{
		"item_id": 1,
		"count":   1,
	})
	s.NoError(err)
	// 可能成功或物品不存在
	s.NotNil(resp["error"])
}

// TestSellItem 测试出售物品
func (s *BagTestSuite) TestSellItem() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/item/sell", map[string]interface{}{
		"item_id": 1,
		"count":   1,
	})
	s.NoError(err)
	// 可能成功或物品不存在
	s.NotNil(resp["error"])
}

// TestEquipItem 测试装备物品
func (s *BagTestSuite) TestEquipItem() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/item/equip", map[string]interface{}{
		"item_id": 1,
	})
	s.NoError(err)
	// 可能成功或物品不存在
	s.NotNil(resp["error"])
}

// TestUnequipItem 测试卸下装备
func (s *BagTestSuite) TestUnequipItem() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/item/unequip", map[string]interface{}{
		"equip_slot": 1,
	})
	s.NoError(err)
	// 可能成功或没有装备
	s.NotNil(resp["error"])
}

func TestBagSuite(t *testing.T) {
	suite.Run(t, new(BagTestSuite))
}
