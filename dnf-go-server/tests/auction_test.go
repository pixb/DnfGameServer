package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type AuctionTestSuite struct {
	BaseTestSuite
}

func (s *AuctionTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestAuctionRegister 测试注册拍卖物品
func (s *AuctionTestSuite) TestAuctionRegister() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 注册拍卖物品
	resp, err := s.Client.Post("/api/v1/auction/register", map[string]interface{}{
		"charguid":    charguid,
		"itemId":      1,
		"itemCount":   1,
		"startPrice":  100,
		"buyoutPrice": 1000,
		"duration":    24,
		"transid":     1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["registerStatus"])
	}
}

// TestAuctionBuyout 测试一口价购买
func (s *AuctionTestSuite) TestAuctionBuyout() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 一口价购买
	resp, err := s.Client.Post("/api/v1/auction/buyout", map[string]interface{}{
		"charguid":  charguid,
		"auctionId": 1,
		"transid":   2,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["buyoutStatus"])
	}
}

// TestAuctionBid 测试竞价
func (s *AuctionTestSuite) TestAuctionBid() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 竞价
	resp, err := s.Client.Post("/api/v1/auction/bid", map[string]interface{}{
		"charguid":  charguid,
		"auctionId": 1,
		"bidPrice":  200,
		"transid":   3,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["bidStatus"])
	}
}

// TestAuctionCancel 测试取消拍卖
func (s *AuctionTestSuite) TestAuctionCancel() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 取消拍卖
	resp, err := s.Client.Post("/api/v1/auction/cancel", map[string]interface{}{
		"charguid":  charguid,
		"auctionId": 1,
		"transid":   4,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["cancelStatus"])
	}
}

// TestAuctionList 测试获取拍卖列表
func (s *AuctionTestSuite) TestAuctionList() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取拍卖列表
	resp, err := s.Client.Get("/api/v1/auction/list?page=1&size=10")
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["auctions"])
	}
}

// TestAuctionSearch 测试搜索拍卖物品
func (s *AuctionTestSuite) TestAuctionSearch() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 搜索拍卖物品
	resp, err := s.Client.Get("/api/v1/auction/search?keyword=sword&page=1&size=10")
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["auctions"])
	}
}

// TestAuctionCategory 测试拍卖分类
func (s *AuctionTestSuite) TestAuctionCategory() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取拍卖分类
	resp, err := s.Client.Get("/api/v1/auction/category")
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["categories"])
	}
}

// TestAuctionDetail 测试获取拍卖详情
func (s *AuctionTestSuite) TestAuctionDetail() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取拍卖详情
	resp, err := s.Client.Get("/api/v1/auction/detail?auctionId=1")
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["auction"])
	}
}

// TestAuctionRecord 测试获取拍卖记录
func (s *AuctionTestSuite) TestAuctionRecord() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取拍卖记录
	resp, err := s.Client.Get(fmt.Sprintf("/api/v1/auction/record?charguid=%d", charguid))
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["records"])
	}
}

// TestAuctionFee 测试拍卖手续费
func (s *AuctionTestSuite) TestAuctionFee() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取拍卖手续费
	resp, err := s.Client.Get("/api/v1/auction/fee?price=1000")
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["fee"])
	}
}

// TestAuctionStatistics 测试拍卖统计
func (s *AuctionTestSuite) TestAuctionStatistics() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取拍卖统计
	resp, err := s.Client.Get("/api/v1/auction/statistics")
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.Equal(float64(0), resp["error"])
		s.NotNil(resp["statistics"])
	}
}

// TestAuctionRegisterInvalidItem 测试注册无效物品
func (s *AuctionTestSuite) TestAuctionRegisterInvalidItem() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 注册无效物品
	resp, err := s.Client.Post("/api/v1/auction/register", map[string]interface{}{
		"charguid":    charguid,
		"itemId":      999999,
		"itemCount":   1,
		"startPrice":  100,
		"buyoutPrice": 1000,
		"duration":    24,
		"transid":     5,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.NotEqual(float64(0), resp["error"])
	}
}

// TestAuctionBidLowPrice 测试低价竞价
func (s *AuctionTestSuite) TestAuctionBidLowPrice() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 低价竞价
	resp, err := s.Client.Post("/api/v1/auction/bid", map[string]interface{}{
		"charguid":  charguid,
		"auctionId": 1,
		"bidPrice":  1,
		"transid":   6,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp != nil {
		s.NotEqual(float64(0), resp["error"])
	}
}

// loginAndSelectCharacter 辅助函数：登录并选择角色
func (s *AuctionTestSuite) loginAndSelectCharacter() uint64 {
	// 1. 登录
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_auction_001",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Skip("Login failed")
		return 0
	}

	// 2. 设置 token
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	// 3. 获取角色列表
	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		// 4. 创建角色
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": "AuctionHero",
			"job":  1,
		})
		s.NoError(err)
		s.NotNil(createResp)
		if createResp == nil || createResp["error"] != float64(0) {
			s.T().Skip("Failed to create character")
			return 0
		}

		// 5. 重新获取角色列表
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)
		characters, ok = listResp["characters"].([]interface{})
		if !ok || len(characters) == 0 {
			s.T().Skip("No characters available after creation")
			return 0
		}
	}

	// 6. 选择角色
	firstChar := characters[0].(map[string]interface{})
	charguid := uint64(firstChar["uid"].(float64))

	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"uid": charguid,
	})
	s.NoError(err)
	s.NotNil(selectResp)

	return charguid
}

// TestAuctionSuite 运行所有拍卖系统测试
func TestAuctionSuite(t *testing.T) {
	suite.Run(t, new(AuctionTestSuite))
}
