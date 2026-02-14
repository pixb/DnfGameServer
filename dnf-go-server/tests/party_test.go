package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type PartyTestSuite struct {
	BaseTestSuite
}

func (s *PartyTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestSearchPartyList 测试搜索队伍列表
func (s *PartyTestSuite) TestSearchPartyList() {
	_ = s.loginAndSelectCharacter()

	searchResp, err := s.Client.Post("/api/v1/party/search", map[string]interface{}{
		"dungeonindex": 0,
		"minlevel":     1,
		"maxlevel":     99,
	})
	s.NoError(err)
	s.NotNil(searchResp)

	if searchResp != nil {
		s.Equal(float64(0), searchResp["error"])
	}
}

// TestCreateParty 测试创建队伍
func (s *PartyTestSuite) TestCreateParty() {
	_ = s.loginAndSelectCharacter()

	createResp, err := s.Client.Post("/api/v1/party/create", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(createResp)

	if createResp != nil {
		s.Equal(float64(0), createResp["error"])
	}
}

// TestCheckProhibitedWord 测试检查禁用词
func (s *PartyTestSuite) TestCheckProhibitedWord() {
	_ = s.loginAndSelectCharacter()

	checkResp, err := s.Client.Post("/api/v1/party/check_prohibited", map[string]interface{}{
		"word": "test",
	})
	s.NoError(err)
	s.NotNil(checkResp)

	if checkResp != nil {
		s.Equal(float64(0), checkResp["error"])
	}
}

// TestTargetUserPartyInfo 测试获取目标用户队伍信息
func (s *PartyTestSuite) TestTargetUserPartyInfo() {
	_ = s.loginAndSelectCharacter()

	infoResp, err := s.Client.Post("/api/v1/party/target_info", map[string]interface{}{
		"charguid": 2,
	})
	s.NoError(err)
	s.NotNil(infoResp)

	if infoResp != nil {
		s.Equal(float64(0), infoResp["error"])
	}
}

// loginAndSelectCharacter 辅助函数：登录并选择角色
func (s *PartyTestSuite) loginAndSelectCharacter() uint64 {
	// 1. 登录
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Fatal("Login failed")
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
		// 如果没有角色，创建一个
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": "TestHero",
			"job":  1,
		})
		s.NoError(err)
		s.NotNil(createResp)

		// 重新获取角色列表
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)

		characters, ok = listResp["characters"].([]interface{})
		if !ok || len(characters) == 0 {
			s.T().Fatal("Failed to create character")
		}
	}

	// 4. 选择角色
	firstChar := characters[0].(map[string]interface{})
	var charguid uint64
	switch v := firstChar["uid"].(type) {
	case float64:
		charguid = uint64(v)
	case string:
		charguid = 0
		if len(v) > 0 {
			fmt.Sscanf(v, "%d", &charguid)
		}
	}

	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"uid": charguid,
	})
	s.NoError(err)
	s.NotNil(selectResp)

	if authToken, ok := selectResp["authToken"].(string); ok {
		s.Client.SetToken(authToken)
	}

	return charguid
}

func TestPartyTestSuite(t *testing.T) {
	suite.Run(t, new(PartyTestSuite))
}
