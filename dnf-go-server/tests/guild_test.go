package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type GuildTestSuite struct {
	BaseTestSuite
}

func (s *GuildTestSuite) setupAuthenticatedClient(openid string) {
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
			"name": fmt.Sprintf("GuildHero%d", time.Now().UnixNano()%10000),
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

func (s *GuildTestSuite) TestGetGuildInfo() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_guild_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/guild/info")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func (s *GuildTestSuite) TestCreateGuild() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_guild2_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/guild/create", map[string]interface{}{
		"name": fmt.Sprintf("TestGuild%d", uniqueID%10000),
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 3 || errVal == 7,
			fmt.Sprintf("Expected success, got: %v", errVal))
	}
}

func (s *GuildTestSuite) TestJoinGuild() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_guild3_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/guild/join", map[string]interface{}{
		"guild_id": 99999,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 6 || errVal == 7 || errVal == 8,
			fmt.Sprintf("Expected success or error, got: %v", errVal))
	}
}

func (s *GuildTestSuite) TestLeaveGuild() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_guild4_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/guild/leave", nil)
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 9 || errVal == 10,
			fmt.Sprintf("Expected success or error, got: %v", errVal))
	}
}

func TestGuildSuite(t *testing.T) {
	suite.Run(t, new(GuildTestSuite))
}
