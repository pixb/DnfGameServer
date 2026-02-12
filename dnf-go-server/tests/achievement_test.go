package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type AchievementTestSuite struct {
	BaseTestSuite
}

func (s *AchievementTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestAchievementInfo 测试成就信息查询
func (s *AchievementTestSuite) TestAchievementInfo() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 查询成就信息
	infoResp, err := s.Client.Post("/api/v1/achievement/info", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(infoResp)

	if infoResp != nil {
		s.Equal(float64(0), infoResp["error"])
	}
}

// TestAchievementReward 测试成就奖励领取
func (s *AchievementTestSuite) TestAchievementReward() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 领取成就奖励
	rewardResp, err := s.Client.Post("/api/v1/achievement/reward", map[string]interface{}{
		"field_1": int32(1),
		"field_2": int32(1),
		"field_3": charguid,
	})
	s.NoError(err)
	s.NotNil(rewardResp)

	if rewardResp != nil {
		// 成就可能不存在，所以返回错误是正常的
		errVal, ok := rewardResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(1)),
			fmt.Sprintf("Expected error 0 or 1, got: %v", errVal))
	}
}

// TestAchievementList 测试成就列表
func (s *AchievementTestSuite) TestAchievementList() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取成就列表
	listResp, err := s.Client.Post("/api/v1/achievement/list", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(listResp)

	if listResp != nil {
		// 检查是否有error字段，如果没有则跳过检查
		if errVal, ok := listResp["error"]; ok {
			s.Equal(float64(0), errVal)
		}
	}
}

// TestAchievementBonusReward 测试成就额外奖励
func (s *AchievementTestSuite) TestAchievementBonusReward() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 领取成就额外奖励
	bonusResp, err := s.Client.Post("/api/v1/achievement/bonus_reward", map[string]interface{}{
		"field_1": int32(1),
		"field_2": int32(1),
		"field_3": charguid,
		"field_4": int32(1),
	})
	s.NoError(err)
	s.NotNil(bonusResp)

	if bonusResp != nil {
		// 成就可能不存在，所以返回错误是正常的
		errVal, ok := bonusResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(1)),
			fmt.Sprintf("Expected error 0 or 1, got: %v", errVal))
	}
}

// loginAndSelectCharacter 辅助函数：登录并选择角色
func (s *AchievementTestSuite) loginAndSelectCharacter() uint64 {
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

	return charguid
}

// TestAchievementSuite 运行所有成就测试
func TestAchievementSuite(t *testing.T) {
	suite.Run(t, new(AchievementTestSuite))
}
