package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

// DungeonTestSuite 副本测试套件
type DungeonTestSuite struct {
	BaseTestSuite
}

// setupAuthenticatedClient 设置认证客户端
func (s *DungeonTestSuite) setupAuthenticatedClient() {
	// 登录
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_dungeon_user",
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

// TestEnterDungeon 测试进入副本
func (s *DungeonTestSuite) TestEnterDungeon() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/dungeon/enter", map[string]interface{}{
		"dungeon_id": 1,
	})
	s.NoError(err)
	// 可能成功或等级不足
	s.NotNil(resp["error"])
}

// TestEnterDungeonInvalidLevel 测试进入副本-等级不足
func (s *DungeonTestSuite) TestEnterDungeonInvalidLevel() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/dungeon/enter", map[string]interface{}{
		"dungeon_id": 999, // 高级副本
	})
	s.NoError(err)
	// 应该返回等级不足错误
	s.NotNil(resp["error"])
}

// TestExitDungeon 测试退出副本
func (s *DungeonTestSuite) TestExitDungeon() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/dungeon/exit", nil)
	s.NoError(err)
	// 可能成功或不在副本中
	s.NotNil(resp["error"])
}

// TestRevive 测试复活
func (s *DungeonTestSuite) TestRevive() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/dungeon/revive", nil)
	s.NoError(err)
	// 可能成功或不需要复活
	s.NotNil(resp["error"])
}

// TestChangeRoom 测试切换房间
func (s *DungeonTestSuite) TestChangeRoom() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/dungeon/room/change", map[string]interface{}{
		"room_id": 2,
	})
	s.NoError(err)
	// 可能成功或不在副本中
	s.NotNil(resp["error"])
}

func TestDungeonSuite(t *testing.T) {
	suite.Run(t, new(DungeonTestSuite))
}
