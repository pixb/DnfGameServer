package tests

import (
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

// AuthTestSuite 认证测试套件
type AuthTestSuite struct {
	BaseTestSuite
}

// TestLogin 测试登录功能
func (s *AuthTestSuite) TestLogin() {
	// 测试正常登录
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotEmpty(resp["auth_key"])
	s.NotEmpty(resp["account_key"])

	// 保存token用于后续测试
	if token, ok := resp["auth_key"].(string); ok {
		s.Client.SetToken(token)
	}
}

// TestLoginNewUser 测试新用户登录（自动创建账户）
func (s *AuthTestSuite) TestLoginNewUser() {
	uniqueID := time.Now().UnixNano()
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_new_user_" + string(rune(uniqueID)),
	})
	s.NoError(err)
	s.AssertSuccess(resp)
}

// TestGetCharacterList 测试获取角色列表
func (s *AuthTestSuite) TestGetCharacterList() {
	// 先登录
	s.TestLogin()

	resp, err := s.Client.Post("/api/v1/character/list", nil)
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotNil(resp["characters"])
}

// TestCreateCharacter 测试创建角色
func (s *AuthTestSuite) TestCreateCharacter() {
	s.TestLogin()

	uniqueID := time.Now().UnixNano()
	resp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": "TestRole_" + string(rune(uniqueID)),
		"job":  1,
	})
	s.NoError(err)

	// 可能成功或角色名已存在
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 3, "Should succeed or get duplicate name error")
}

// TestCreateCharacterInvalidName 测试创建角色-无效名称
func (s *AuthTestSuite) TestCreateCharacterInvalidName() {
	s.TestLogin()

	resp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": "",
		"job":  1,
	})
	s.NoError(err)
	s.AssertError(resp, 1) // Invalid parameter
}

// TestSelectCharacter 测试选择角色
func (s *AuthTestSuite) TestSelectCharacter() {
	s.TestLogin()

	// 先获取角色列表
	listResp, err := s.Client.Post("/api/v1/character/list", nil)
	s.NoError(err)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		s.T().Skip("No characters available")
	}

	// 选择第一个角色
	firstChar := characters[0].(map[string]interface{})
	resp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"role_id": firstChar["id"],
	})
	s.NoError(err)
	s.AssertSuccess(resp)
}

// TestAuthWithInvalidToken 测试无效Token
func (s *AuthTestSuite) TestAuthWithInvalidToken() {
	s.Client.SetToken("invalid_token")
	resp, err := s.Client.Post("/api/v1/character/list", nil)
	s.NoError(err)
	// 应该返回认证错误
	s.NotEqual(float64(0), resp["error"])
}

func TestAuthSuite(t *testing.T) {
	suite.Run(t, new(AuthTestSuite))
}
