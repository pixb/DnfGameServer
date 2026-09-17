package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type MarketTestSuite struct {
	BaseTestSuite
}

func (s *MarketTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestSearchMarketItems 测试搜索市场物品
func (s *MarketTestSuite) TestSearchMarketItems() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_search_01", 1)

	searchResp, err := s.Client.Post("/api/v1/market/search", map[string]interface{}{
		"item_index": 0,
		"min_level":  1,
		"max_level":  99,
		"min_price":  0,
		"max_price":  999999999,
		"page":       1,
		"page_size":  20,
	})
	s.NoError(err)
	s.NotNil(searchResp)

	if searchResp != nil {
		s.Equal(float64(0), searchResp["error"])
	}
}

// TestPublishMarketItem 测试发布市场物品
func (s *MarketTestSuite) TestPublishMarketItem() {
	charGuid := s.loginAndSelectCharacterWithUserAndSlot("mk_publish_01", 2)

	publishResp, err := s.Client.Post("/api/v1/market/publish", map[string]interface{}{
		"item_guid":     charGuid,
		"price":         10000,
		"currency_type": 1,
		"duration":      72,
	})
	s.NoError(err)
	s.NotNil(publishResp)

	if publishResp != nil {
		s.Equal(float64(0), publishResp["error"], "Failed to publish market item: %+v", publishResp)
	}
}

// TestCancelMarketItem 测试取消市场物品
func (s *MarketTestSuite) TestCancelMarketItem() {
	charGuid := s.loginAndSelectCharacterWithUserAndSlot("mk_cancel_01", 3)

	cancelResp, err := s.Client.Post("/api/v1/market/cancel", map[string]interface{}{
		"item_guid": charGuid,
	})
	s.NoError(err)
	s.NotNil(cancelResp)

	if cancelResp != nil {
		s.Equal(float64(0), cancelResp["error"])
	}
}

// TestBuyMarketItem 测试购买市场物品
func (s *MarketTestSuite) TestBuyMarketItem() {
	charGuid := s.loginAndSelectCharacterWithUserAndSlot("mk_buy_01", 4)

	buyResp, err := s.Client.Post("/api/v1/market/buy", map[string]interface{}{
		"item_guid": charGuid,
	})
	s.NoError(err)
	s.NotNil(buyResp)

	if buyResp != nil {
		s.Equal(float64(0), buyResp["error"])
	}
}

// TestGetMarketItemDetail 测试获取市场物品详情
func (s *MarketTestSuite) TestGetMarketItemDetail() {
	charGuid := s.loginAndSelectCharacterWithUserAndSlot("mk_detail_01", 5)

	detailResp, err := s.Client.Post("/api/v1/market/detail", map[string]interface{}{
		"item_guid": charGuid,
	})
	s.NoError(err)
	s.NotNil(detailResp)

	if detailResp != nil {
		s.Equal(float64(0), detailResp["error"])
	}
}

// TestGetMyMarketItems 测试获取我的市场物品
func (s *MarketTestSuite) TestGetMyMarketItems() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_my_01", 6)

	myItemsResp, err := s.Client.Post("/api/v1/market/my_items", map[string]interface{}{
		"page":      1,
		"page_size": 20,
	})
	s.NoError(err)
	s.NotNil(myItemsResp)

	if myItemsResp != nil {
		s.Equal(float64(0), myItemsResp["error"])
	}
}

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户登录并确保有角色可选
func (s *MarketTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Fatal("Login failed")
	}

	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		// 无角色时创建(与其余套件一致)
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": fmt.Sprintf("MarketHero%02d", slot),
			"job":  1,
		})
		s.NoError(err)
		s.NotNil(createResp)
		if createResp == nil || createResp["error"] != float64(0) {
			s.T().Fatal("Failed to create character")
		}
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)
		characters, ok = listResp["characters"].([]interface{})
		if !ok || len(characters) == 0 {
			s.T().Fatal("No characters available after creation")
		}
	}

	// 取账号首个角色(单角色账号 list[0] 即目标角色)
	firstChar, ok := characters[0].(map[string]interface{})
	if !ok {
		s.T().Fatal("Failed to parse character")
	}
	if guid, ok := firstChar["charGuid"].(float64); ok {
		return uint64(guid)
	}
	if uid, ok := firstChar["uid"].(float64); ok {
		return uint64(uid)
	}

	s.T().Fatal("Failed to select character")
	return 0
}

func TestMarketTestSuite(t *testing.T) {
	suite.Run(t, new(MarketTestSuite))
}
