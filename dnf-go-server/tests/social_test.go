package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type SocialTestSuite struct {
	BaseTestSuite
}

func (s *SocialTestSuite) setupAuthenticatedClient(openid string) {
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
			"name": fmt.Sprintf("SocialHero%d", time.Now().UnixNano()%10000),
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

func (s *SocialTestSuite) TestGetFriendList() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/friend/list")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func (s *SocialTestSuite) TestAddFriend() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social2_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/friend/add", map[string]interface{}{
		"target_name": "NonExistentPlayer",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 6,
			fmt.Sprintf("Expected success or target not found, got: %v", errVal))
	}
}

func (s *SocialTestSuite) TestRemoveFriend() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social3_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/friend/remove", map[string]interface{}{
		"friend_uid": 99999,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 6,
			fmt.Sprintf("Expected success or friend not found, got: %v", errVal))
	}
}

func (s *SocialTestSuite) TestGetMailList() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social4_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/mail/list")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func TestSocialSuite(t *testing.T) {
	suite.Run(t, new(SocialTestSuite))
}
