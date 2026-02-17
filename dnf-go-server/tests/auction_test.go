package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

type AuctionTestSuite struct {
	BaseTestSuite
}

func (s *AuctionTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestAuctionMyRegisteredItemList 测试查询我的注册物品列表
func (s *AuctionTestSuite) TestAuctionMyRegisteredItemList() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_my_list_01", 1)

	myListResp, err := s.Client.Post("/api/v1/auction/my_registered_items", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(myListResp)

	if myListResp != nil {
		s.Equal(float64(0), myListResp["error"])
	}
}

// TestAuctionCategoryItemList 测试查询分类物品列表
func (s *AuctionTestSuite) TestAuctionCategoryItemList() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_category_01", 2)

	categoryResp, err := s.Client.Post("/api/v1/auction/category_items", map[string]interface{}{
		"category": 1,
	})
	s.NoError(err)
	s.NotNil(categoryResp)

	if categoryResp != nil {
		s.Equal(float64(0), categoryResp["error"])
	}
}

// TestAuctionItemPriceList 测试查询物品价格列表
func (s *AuctionTestSuite) TestAuctionItemPriceList() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_price_01", 3)

	priceResp, err := s.Client.Post("/api/v1/auction/item_price_list", map[string]interface{}{
		"category": 1,
		"index":    1001,
	})
	s.NoError(err)
	s.NotNil(priceResp)

	if priceResp != nil {
		s.Equal(float64(0), priceResp["error"])
	}
}

// TestAuctionBuyAtOnce 测试一口价购买
func (s *AuctionTestSuite) TestAuctionBuyAtOnce() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_buy_01", 4)

	buyResp, err := s.Client.Post("/api/v1/auction/buy_at_once", map[string]interface{}{
		"category": 1,
		"index":    1001,
		"price":    1000,
		"qty":      1,
	})
	s.NoError(err)
	s.NotNil(buyResp)

	if buyResp != nil {
		s.Equal(float64(0), buyResp["error"], "Failed to buy at once: %+v", buyResp)
	}
}

// TestItemAvrPrice 测试查询物品平均价格
func (s *AuctionTestSuite) TestItemAvrPrice() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_avr_01", 5)

	avrResp, err := s.Client.Post("/api/v1/auction/item_avr_price", map[string]interface{}{
		"index": 1001,
	})
	s.NoError(err)
	s.NotNil(avrResp)

	if avrResp != nil {
		s.Equal(float64(0), avrResp["error"])
	}
}

// TestAuctionRegisterItem 测试注册拍卖物品
func (s *AuctionTestSuite) TestAuctionRegisterItem() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_register_01", 6)

	registerResp, err := s.Client.Post("/api/v1/auction/register_item", map[string]interface{}{
		"tab":   1,
		"index": 1001,
		"qty":   1,
		"price": 1000,
	})
	s.NoError(err)
	s.NotNil(registerResp)

	if registerResp != nil {
		s.Equal(float64(0), registerResp["error"], "Failed to register item: %+v", registerResp)
	}
}

// TestAuctionRegisterEquip 测试注册拍卖装备
func (s *AuctionTestSuite) TestAuctionRegisterEquip() {
	charGuid := s.loginAndSelectCharacterWithUserAndSlot("ac_register_equip_01", 7)

	registerResp, err := s.Client.Post("/api/v1/auction/register_item", map[string]interface{}{
		"tab":   2,
		"index": 2001,
		"qty":   1,
		"price": 5000,
		"guid":  charGuid,
	})
	s.NoError(err)
	s.NotNil(registerResp)

	if registerResp != nil {
		s.Equal(float64(0), registerResp["error"], "Failed to register equip: %+v", registerResp)
	}
}

// TestAuctionRegisterCancel 测试取消注册
func (s *AuctionTestSuite) TestAuctionRegisterCancel() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_cancel_01", 8)

	cancelResp, err := s.Client.Post("/api/v1/auction/register_cancel", map[string]interface{}{
		"category": 1,
		"auid":     uint64(1),
	})
	s.NoError(err)
	s.NotNil(cancelResp)

	if cancelResp != nil {
		s.Equal(float64(0), cancelResp["error"])
	}
}

// TestAuctionRegisterComplete 测试完成注册
func (s *AuctionTestSuite) TestAuctionRegisterComplete() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_complete_01", 9)

	completeResp, err := s.Client.Post("/api/v1/auction/register_complete", map[string]interface{}{
		"tab":   1,
		"index": 1001,
		"qty":   1,
		"price": 1000,
	})
	s.NoError(err)
	s.NotNil(completeResp)

	if completeResp != nil {
		s.Equal(float64(0), completeResp["error"])
	}
}

// TestAuctionHistory 测试查询拍卖历史
func (s *AuctionTestSuite) TestAuctionHistory() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_history_01", 10)

	historyResp, err := s.Client.Post("/api/v1/auction/history", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(historyResp)

	if historyResp != nil {
		s.Equal(float64(0), historyResp["error"])
	}
}

// TestAuctionBuyAtOnceEquip 测试一口价购买装备
func (s *AuctionTestSuite) TestAuctionBuyAtOnceEquip() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_buy_equip_01", 11)

	buyResp, err := s.Client.Post("/api/v1/auction/buy_at_once", map[string]interface{}{
		"category": 2,
		"index":    2001,
		"price":    5000,
		"qty":      1,
	})
	s.NoError(err)
	s.NotNil(buyResp)

	if buyResp != nil {
		s.Equal(float64(0), buyResp["error"], "Failed to buy equip at once: %+v", buyResp)
	}
}

// TestAuctionRegisterStackable 测试注册堆叠物品
func (s *AuctionTestSuite) TestAuctionRegisterStackable() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ac_register_stack_01", 12)

	registerResp, err := s.Client.Post("/api/v1/auction/register_item", map[string]interface{}{
		"tab":   1,
		"index": 1002,
		"qty":   10,
		"price": 500,
	})
	s.NoError(err)
	s.NotNil(registerResp)

	if registerResp != nil {
		s.Equal(float64(0), registerResp["error"], "Failed to register stackable: %+v", registerResp)
	}
}

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户和槽位登录并选择角色
func (s *AuctionTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
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

func TestAuctionTestSuite(t *testing.T) {
	suite.Run(t, new(AuctionTestSuite))
}
