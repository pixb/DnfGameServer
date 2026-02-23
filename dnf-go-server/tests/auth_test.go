package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type AuthTestSuite struct {
	BaseTestSuite
}

func (s *AuthTestSuite) TestLogin() {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
	s.NotNil(resp["authKey"])
	s.NotNil(resp["accountKey"])

	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}
}

func (s *AuthTestSuite) TestLoginNewUser() {
	uniqueID := time.Now().UnixNano()
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": fmt.Sprintf("test_new_user_%d", uniqueID),
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func (s *AuthTestSuite) TestGetCharacterList() {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
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
	if listResp != nil {
		s.Equal(float64(0), listResp["error"])
		s.NotNil(listResp["characters"])
	}
}

func (s *AuthTestSuite) TestCreateCharacter() {
	uniqueID := time.Now().UnixNano()
	openid := fmt.Sprintf("test_create_%d", uniqueID)
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

	charName := fmt.Sprintf("Hero%d", uniqueID%10000)
	createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": charName,
		"job":  1,
	})
	s.NoError(err)
	s.NotNil(createResp)
	if createResp != nil {
		errVal := createResp["error"]
		s.True(errVal == nil || errVal == float64(0) || errVal == float64(3),
			fmt.Sprintf("Expected success or name exists, got error: %v", errVal))
	}
}

func (s *AuthTestSuite) TestCreateCharacterInvalidName() {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
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

	createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": "",
		"job":  1,
	})
	s.NoError(err)
	s.NotNil(createResp)
	if createResp != nil {
		s.Equal(float64(2), createResp["error"])
	}
}

func (s *AuthTestSuite) TestSelectCharacter() {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
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
		s.T().Skip("No characters available")
		return
	}

	firstChar := characters[0].(map[string]interface{})
	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"charGuid": firstChar["charGuid"],
	})
	s.NoError(err)
	s.NotNil(selectResp)
	if selectResp != nil {
		s.Equal(float64(0), selectResp["error"])
	}
}

func (s *AuthTestSuite) TestAuthWithInvalidToken() {
	s.Client.SetToken("invalid_token")
	resp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.NotEqual(float64(0), resp["error"])
	}
}

func TestAuthSuite(t *testing.T) {
	suite.Run(t, new(AuthTestSuite))
}
