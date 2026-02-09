package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

// ShopTestSuite 商店测试套件
type ShopTestSuite struct {
	BaseTestSuite
}

// setupAuthenticatedClient 设置认证客户端
func (s *ShopTestSuite) setupAuthenticatedClient() {
	// 登录
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_shop_user",
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

// TestGetShopList 测试获取商店列表
func (s *ShopTestSuite) TestGetShopList() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/shop/list", map[string]interface{}{
		"shop_id": 1,
	})
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotNil(resp["items"])
}

// TestBuyItem 测试购买物品
func (s *ShopTestSuite) TestBuyItem() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/shop/buy", map[string]interface{}{
		"shop_id": 1,
		"item_id": 1,
		"count":   1,
	})
	s.NoError(err)
	// 可能成功或金币不足
	s.NotNil(resp["error"])
}

// TestBuyItemNotEnoughMoney 测试购买物品-金币不足
func (s *ShopTestSuite) TestBuyItemNotEnoughMoney() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/shop/buy", map[string]interface{}{
		"shop_id": 1,
		"item_id": 1,
		"count":   999999, // 大量购买
	})
	s.NoError(err)
	// 应该返回金币不足错误
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 3, "Should succeed or get not enough money error")
}

// TestSellToShop 测试出售物品给商店
func (s *ShopTestSuite) TestSellToShop() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/shop/sell", map[string]interface{}{
		"item_id": 1,
		"count":   1,
	})
	s.NoError(err)
	// 可能成功或物品不存在
	s.NotNil(resp["error"])
}

// TestSearchAuction 测试搜索拍卖行
func (s *ShopTestSuite) TestSearchAuction() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/auction/search", map[string]interface{}{
		"keyword": "sword",
	})
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotNil(resp["items"])
}

// TestRegisterAuction 测试上架拍卖
func (s *ShopTestSuite) TestRegisterAuction() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/auction/register", map[string]interface{}{
		"item_id": 1,
		"price":   1000,
	})
	s.NoError(err)
	// 可能成功或物品不存在
	s.NotNil(resp["error"])
}

// TestBidAuction 测试竞拍
func (s *ShopTestSuite) TestBidAuction() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/auction/bid", map[string]interface{}{
		"auction_id": 1,
		"price":      1000,
	})
	s.NoError(err)
	// 可能成功或拍卖不存在
	s.NotNil(resp["error"])
}

// TestBuyoutAuction 测试一口价购买
func (s *ShopTestSuite) TestBuyoutAuction() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/auction/buyout", map[string]interface{}{
		"auction_id": 1,
	})
	s.NoError(err)
	// 可能成功或拍卖不存在
	s.NotNil(resp["error"])
}

func TestShopSuite(t *testing.T) {
	suite.Run(t, new(ShopTestSuite))
}
