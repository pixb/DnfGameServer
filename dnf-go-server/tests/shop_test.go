package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type ShopTestSuite struct {
	BaseTestSuite
}

func (s *ShopTestSuite) setupAuthenticatedClient(openid string) {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp == nil {
		s.T().Skip("Login failed")
		return
	}

	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": fmt.Sprintf("ShopHero%d", time.Now().UnixNano()%10000),
			"job":  1,
		})
		s.NoError(err)
		s.NotNil(createResp)
		if createResp != nil && createResp["error"] == float64(0) {
			listResp, _ = s.Client.Get("/api/v1/character/list")
			if listResp != nil {
				characters, _ = listResp["characters"].([]interface{})
			}
		}
	}

	if characters != nil && len(characters) > 0 {
		firstChar := characters[0].(map[string]interface{})
		_, err = s.Client.Post("/api/v1/character/select", map[string]interface{}{
			"uid": firstChar["uid"],
		})
		s.NoError(err)
	}
}

func (s *ShopTestSuite) TestGetShopList() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_shop_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/shop/list")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func (s *ShopTestSuite) TestBuyItem() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_shop2_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/shop/buy", map[string]interface{}{
		"slot":  1,
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 3,
			fmt.Sprintf("Expected success (0) or insufficient gold (3), got: %v", errVal))
	}
}

func (s *ShopTestSuite) TestSellToShop() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_shop3_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/shop/sell", map[string]interface{}{
		"guid":  999999,
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 6,
			fmt.Sprintf("Expected success (0) or item not found (6), got: %v", errVal))
	}
}

func (s *ShopTestSuite) TestSearchAuction() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_shop4_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/auction/search")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func TestShopSuite(t *testing.T) {
	suite.Run(t, new(ShopTestSuite))
}
