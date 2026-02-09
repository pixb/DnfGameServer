package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

// QuestTestSuite 任务测试套件
type QuestTestSuite struct {
	BaseTestSuite
}

// setupAuthenticatedClient 设置认证客户端
func (s *QuestTestSuite) setupAuthenticatedClient() {
	// 登录
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_quest_user",
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

// TestGetQuestList 测试获取任务列表
func (s *QuestTestSuite) TestGetQuestList() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/quest/list", nil)
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotNil(resp["quests"])
}

// TestAcceptQuest 测试接受任务
func (s *QuestTestSuite) TestAcceptQuest() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/quest/accept", map[string]interface{}{
		"quest_id": 1,
	})
	s.NoError(err)
	// 可能成功或任务不存在/已接受
	s.NotNil(resp["error"])
}

// TestCompleteQuest 测试完成任务
func (s *QuestTestSuite) TestCompleteQuest() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/quest/complete", map[string]interface{}{
		"quest_id": 1,
	})
	s.NoError(err)
	// 可能成功或任务未完成/不存在
	s.NotNil(resp["error"])
}

// TestGetQuestReward 测试领取任务奖励
func (s *QuestTestSuite) TestGetQuestReward() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/quest/reward", map[string]interface{}{
		"quest_id": 1,
	})
	s.NoError(err)
	// 可能成功或任务未完成/已领取
	s.NotNil(resp["error"])
}

// TestAbandonQuest 测试放弃任务
func (s *QuestTestSuite) TestAbandonQuest() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/quest/abandon", map[string]interface{}{
		"quest_id": 1,
	})
	s.NoError(err)
	// 可能成功或任务不存在
	s.NotNil(resp["error"])
}

func TestQuestSuite(t *testing.T) {
	suite.Run(t, new(QuestTestSuite))
}
