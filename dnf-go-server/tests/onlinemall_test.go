package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

type OnlineMallTestSuite struct {
	BaseTestSuite
}

func (s *OnlineMallTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestGetOnlineMallItems 测试获取商城商品列表
func (s *OnlineMallTestSuite) TestGetOnlineMallItems() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_list_01", 1)

	listResp, err := s.Client.Post("/api/v1/onlinemall/list", map[string]interface{}{
		"page":      1,
		"page_size": 20,
	})
	s.NoError(err)
	s.NotNil(listResp)

	if listResp != nil {
		s.Equal(float64(0), listResp["error"])
	}
}

// TestGetOnlineMallItemDetail 测试获取商城商品详情
func (s *OnlineMallTestSuite) TestGetOnlineMallItemDetail() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_detail_01", 2)

	detailResp, err := s.Client.Post("/api/v1/onlinemall/detail", map[string]interface{}{
		"item_id": 1,
	})
	s.NoError(err)
	s.NotNil(detailResp)

	if detailResp != nil {
		s.Equal(float64(0), detailResp["error"])
	}
}

// TestBuyOnlineMallItem 测试购买商城商品
func (s *OnlineMallTestSuite) TestBuyOnlineMallItem() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_buy_01", 3)

	buyResp, err := s.Client.Post("/api/v1/onlinemall/buy", map[string]interface{}{
		"item_id":       1,
		"buy_count":     1,
		"currency_type": 1,
	})
	s.NoError(err)
	s.NotNil(buyResp)

	if buyResp != nil {
		s.Equal(float64(0), buyResp["error"], "Failed to buy mall item: %+v", buyResp)
	}
}

// TestGetOnlineMallCategories 测试获取商城分类
func (s *OnlineMallTestSuite) TestGetOnlineMallCategories() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_category_01", 4)

	categoryResp, err := s.Client.Get("/api/v1/onlinemall/categories")
	s.NoError(err)
	s.NotNil(categoryResp)

	if categoryResp != nil {
		s.Equal(float64(0), categoryResp["error"])
	}
}

// TestGetOnlineMallItemsByCategory 测试按分类获取商城商品
func (s *OnlineMallTestSuite) TestGetOnlineMallItemsByCategory() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_category_items_01", 5)

	categoryItemsResp, err := s.Client.Post("/api/v1/onlinemall/category_items", map[string]interface{}{
		"category_id": 1,
		"page":       1,
		"page_size":  20,
	})
	s.NoError(err)
	s.NotNil(categoryItemsResp)

	if categoryItemsResp != nil {
		s.Equal(float64(0), categoryItemsResp["error"])
	}
}

// TestGetOnlineMallRecommendItems 测试获取推荐商品
func (s *OnlineMallTestSuite) TestGetOnlineMallRecommendItems() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_recommend_01", 6)

	recommendResp, err := s.Client.Get("/api/v1/onlinemall/recommend")
	s.NoError(err)
	s.NotNil(recommendResp)

	if recommendResp != nil {
		s.Equal(float64(0), recommendResp["error"])
	}
}

// TestGetOnlineMallHotItems 测试获取热门商品
func (s *OnlineMallTestSuite) TestGetOnlineMallHotItems() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_hot_01", 7)

	hotResp, err := s.Client.Get("/api/v1/onlinemall/hot")
	s.NoError(err)
	s.NotNil(hotResp)

	if hotResp != nil {
		s.Equal(float64(0), hotResp["error"])
	}
}

// TestGetOnlineMallBuyLimit 测试获取购买限制
func (s *OnlineMallTestSuite) TestGetOnlineMallBuyLimit() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("om_limit_01", 8)

	limitResp, err := s.Client.Post("/api/v1/onlinemall/buy_limit", map[string]interface{}{
		"item_id": 1,
	})
	s.NoError(err)
	s.NotNil(limitResp)

	if limitResp != nil {
		s.Equal(float64(0), limitResp["error"])
	}
}

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户和槽位登录并选择角色
func (s *OnlineMallTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
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

	if list, ok := listResp["characters"].([]interface{}); ok && len(list) > slot {
		if char, ok := list[slot].(map[string]interface{}); ok {
			if guid, ok := char["charguid"].(float64); ok {
				selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
					"charguid": uint64(guid),
				})
				s.NoError(err)
				s.NotNil(selectResp)
				return uint64(guid)
			}
		}
	}

	s.T().Fatal("Failed to select character")
	return 0
}

func TestOnlineMallTestSuite(t *testing.T) {
	suite.Run(t, new(OnlineMallTestSuite))
}
