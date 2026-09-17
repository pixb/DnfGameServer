package tests

import (
	"database/sql"
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type EventTestSuite struct {
	BaseTestSuite
}

func (s *EventTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	// 确保活动 event_id=1 存在且 NORMAL:
	// TCP 套件(event_tcp_test.go,文件名序先跑)的 DELETE_EVENT 会删掉活动 1,
	// 本套件依赖活动 1,因此在套件启动时恢复(测试数据自洽)。
	ensureEventConfig1()
}

// ensureEventConfig1 直接落库确保 event_id=1 的活动配置存在(绕过 HTTP,测试数据准备)
func ensureEventConfig1() {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		fmt.Println("ensureEventConfig1 open db:", err)
		return
	}
	defer db.Close()
	now := int64(0)
	_ = db.QueryRow(`SELECT UNIX_TIMESTAMP()`).Scan(&now)
	_, err = db.Exec(`INSERT INTO t_event_config
      (created_at, updated_at, row_status, event_id, title, description, event_type, status, start_time, end_time, reward_config)
      VALUES (?, ?, 'NORMAL', 1, '新手活动', '由测试 SetupSuite 恢复', 1, 1, ?, ?, '{}')
      ON DUPLICATE KEY UPDATE row_status = 'NORMAL'`, now, now, now, now+86400)
	if err != nil {
		fmt.Println("ensureEventConfig1 upsert:", err)
	}
	// 重置活动 1 的领奖进度(progress_type >= 100 为领奖标记),避免历史 already 状态
	if _, err := db.Exec(`DELETE FROM t_event_progress WHERE event_id = 1 AND progress_type >= 100`); err != nil {
		fmt.Println("ensureEventConfig1 clear claims:", err)
	}
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
		"event_id":       1,
		"progress_type":  1,
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

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户登录并确保有角色可选
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

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		// 无角色时创建(与其余套件一致)
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": fmt.Sprintf("EventHero%02d", slot),
			"job":  1,
		})
		s.NoError(err)
		s.NotNil(createResp)
		if createResp == nil || createResp["error"] != float64(0) {
			s.T().Fatal("Failed to create character")
		}
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)
		characters, ok = listResp["characters"].([]interface{})
		if !ok || len(characters) == 0 {
			s.T().Fatal("No characters available after creation")
		}
	}

	// 取账号首个角色
	firstChar, ok := characters[0].(map[string]interface{})
	if !ok {
		s.T().Fatal("Failed to parse character")
	}
	if guid, ok := firstChar["charGuid"].(float64); ok {
		return uint64(guid)
	}
	if guid, ok := firstChar["uid"].(float64); ok {
		return uint64(guid)
	}

	s.T().Fatal("Failed to select character")
	return 0
}

func TestEventTestSuite(t *testing.T) {
	suite.Run(t, new(EventTestSuite))
}
