package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

type EventTestSuite struct {
	BaseTestSuite
}

func (s *EventTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestEventList 测试获取活动列表
func (s *EventTestSuite) TestEventList() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_list_01", 1)

	listResp, err := s.Client.Post("/api/v1/event/list", map[string]interface{}{
		"type":   0,
		"status": 1,
	})
	s.NoError(err)
	s.NotNil(listResp)

	if listResp != nil {
		s.Equal(float64(0), listResp["error"])
	}
}

// TestEventDetail 测试获取活动详情
func (s *EventTestSuite) TestEventDetail() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_detail_01", 2)

	detailResp, err := s.Client.Post("/api/v1/event/detail", map[string]interface{}{
		"event_id": 1,
	})
	s.NoError(err)
	s.NotNil(detailResp)

	if detailResp != nil {
		s.Equal(float64(0), detailResp["error"])
	}
}

// TestQueryAccessTimeByEvent 测试查询活动访问时间
func (s *EventTestSuite) TestQueryAccessTimeByEvent() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_time_01", 3)

	timeResp, err := s.Client.Post("/api/v1/event/access_time", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(timeResp)

	if timeResp != nil {
		s.Equal(float64(0), timeResp["error"])
	}
}

// TestEventGetReward 测试获取活动奖励
func (s *EventTestSuite) TestEventGetReward() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_reward_01", 4)

	rewardResp, err := s.Client.Post("/api/v1/event/get_reward", map[string]interface{}{
		"event_id":  1,
		"reward_id": 1,
	})
	s.NoError(err)
	s.NotNil(rewardResp)

	if rewardResp != nil {
		s.Equal(float64(0), rewardResp["error"])
	}
}

// TestEventProgressUpdate 测试更新活动进度
func (s *EventTestSuite) TestEventProgressUpdate() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_progress_01", 5)

	progressResp, err := s.Client.Post("/api/v1/event/update_progress", map[string]interface{}{
		"event_id":      1,
		"progress_type": 1,
		"progress_value": 10,
	})
	s.NoError(err)
	s.NotNil(progressResp)

	if progressResp != nil {
		s.Equal(float64(0), progressResp["error"])
	}
}

// TestEventParticipate 测试参与活动
func (s *EventTestSuite) TestEventParticipate() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_participate_01", 6)

	participateResp, err := s.Client.Post("/api/v1/event/participate", map[string]interface{}{
		"event_id":         1,
		"participate_type": 1,
	})
	s.NoError(err)
	s.NotNil(participateResp)

	if participateResp != nil {
		s.Equal(float64(0), participateResp["error"])
	}
}

// TestEventListByType 测试按类型获取活动列表
func (s *EventTestSuite) TestEventListByType() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_type_01", 7)

	typeResp, err := s.Client.Post("/api/v1/event/list", map[string]interface{}{
		"type":   1,
		"status": 1,
	})
	s.NoError(err)
	s.NotNil(typeResp)

	if typeResp != nil {
		s.Equal(float64(0), typeResp["error"])
	}
}

// TestEventListActive 测试获取活动中的活动
func (s *EventTestSuite) TestEventListActive() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("ev_active_01", 8)

	activeResp, err := s.Client.Post("/api/v1/event/list", map[string]interface{}{
		"type":   0,
		"status": 1,
	})
	s.NoError(err)
	s.NotNil(activeResp)

	if activeResp != nil {
		s.Equal(float64(0), activeResp["error"])
	}
}

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户和槽位登录并选择角色
func (s *EventTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Fatal("Login failed")
	}

	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	if list, ok := listResp["characters"].([]interface{}); ok && len(list) > slot {
		if char, ok := list[slot].(map[string]interface{}); ok {
			if guid, ok := char["charguid"].(float64); ok {
				selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
					"charguid": uint64(guid),
				})
				s.NoError(err)
				s.NotNil(selectResp)
				return uint64(guid)
			}
		}
	}

	s.T().Fatal("Failed to select character")
	return 0
}

func TestEventTestSuite(t *testing.T) {
	suite.Run(t, new(EventTestSuite))
}
