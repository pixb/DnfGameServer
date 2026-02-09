package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type BagTestSuite struct {
	BaseTestSuite
}

func (s *BagTestSuite) setupAuthenticatedClient(openid string) string {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp == nil {
		s.T().Skip("Login failed")
		return ""
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
			"name": fmt.Sprintf("BagHero%d", time.Now().UnixNano()%10000),
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

	return openid
}

func (s *BagTestSuite) TestGetBag() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_bag_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/bag")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func (s *BagTestSuite) TestGetBagItems() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_bag2_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/bag/items")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func TestBagSuite(t *testing.T) {
	suite.Run(t, new(BagTestSuite))
}
