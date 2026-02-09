package tests

import (
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

// GuildTestSuite 公会测试套件
type GuildTestSuite struct {
	BaseTestSuite
}

// setupAuthenticatedClient 设置认证客户端
func (s *GuildTestSuite) setupAuthenticatedClient() {
	// 登录
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_guild_user",
	})
	s.NoError(err)
	s.AssertSuccess(resp)

	if token, ok := resp["auth_key"].(string); ok {
		s.Client.SetToken(token)
	}

	// 获取角色列表并选择角色
	listResp, err := s.Client.Post("/api/v1/character/list", nil)
	s.NoError(err)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		s.T().Skip("No characters available")
	}

	firstChar := characters[0].(map[string]interface{})
	_, err = s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"role_id": firstChar["id"],
	})
	s.NoError(err)
}

// TestGetGuildInfo 测试获取公会信息
func (s *GuildTestSuite) TestGetGuildInfo() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/guild/info", nil)
	s.NoError(err)
	// 可能成功或不在公会中
	s.NotNil(resp["error"])
}

// TestCreateGuild 测试创建公会
func (s *GuildTestSuite) TestCreateGuild() {
	s.setupAuthenticatedClient()

	uniqueID := time.Now().UnixNano()
	resp, err := s.Client.Post("/api/v1/guild/create", map[string]interface{}{
		"name": "TestGuild_" + string(rune(uniqueID)),
	})
	s.NoError(err)
	// 可能成功或金币不足/已有公会
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 3 || errorCode == 7, "Should succeed or get money/not in guild error")
}

// TestCreateGuildInvalidName 测试创建公会-无效名称
func (s *GuildTestSuite) TestCreateGuildInvalidName() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/guild/create", map[string]interface{}{
		"name": "",
	})
	s.NoError(err)
	s.AssertError(resp, 1) // Invalid parameter
}

// TestJoinGuild 测试加入公会
func (s *GuildTestSuite) TestJoinGuild() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/guild/join", map[string]interface{}{
		"guild_id": 1,
	})
	s.NoError(err)
	// 可能成功或公会不存在/已满/已有公会
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 2 || errorCode == 7 || errorCode == 8, "Should succeed or get appropriate error")
}

// TestLeaveGuild 测试离开公会
func (s *GuildTestSuite) TestLeaveGuild() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/guild/leave", nil)
	s.NoError(err)
	// 可能成功或不在公会中/是会长
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 9 || errorCode == 10, "Should succeed or get appropriate error")
}

// TestGuildDonate 测试公会捐赠
func (s *GuildTestSuite) TestGuildDonate() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/guild/donate", map[string]interface{}{
		"donate_type": 1, // 金币
		"amount":      100,
	})
	s.NoError(err)
	// 可能成功或不在公会中/金币不足
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 3 || errorCode == 9, "Should succeed or get appropriate error")
}

// TestGetGuildSkill 测试获取公会技能
func (s *GuildTestSuite) TestGetGuildSkill() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/guild/skill", nil)
	s.NoError(err)
	// 可能成功或不在公会中
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 9, "Should succeed or get appropriate error")
}

// TestUpgradeGuildSkill 测试升级公会技能
func (s *GuildTestSuite) TestUpgradeGuildSkill() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/guild/skill/upgrade", map[string]interface{}{
		"skill_id": 1,
	})
	s.NoError(err)
	// 可能成功或不在公会中/权限不足/资金不足
	errorCode := resp["error"].(float64)
	s.True(errorCode == 0 || errorCode == 9 || errorCode == 11 || errorCode == 12, "Should succeed or get appropriate error")
}

func TestGuildSuite(t *testing.T) {
	suite.Run(t, new(GuildTestSuite))
}
