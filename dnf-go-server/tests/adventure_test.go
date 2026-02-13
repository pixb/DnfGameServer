package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type AdventureTestSuite struct {
	BaseTestSuite
}

func (s *AdventureTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestAdventureUnionInfo 测试冒险联盟信息查询
func (s *AdventureTestSuite) TestAdventureUnionInfo() {
	_ = s.loginAndSelectCharacter()

	infoResp, err := s.Client.Post("/api/v1/adventure/union/info", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(infoResp)

	if infoResp != nil {
		s.Equal(float64(0), infoResp["error"])
	}
}

// TestAdventureUnionNameChange 测试冒险联盟改名
func (s *AdventureTestSuite) TestAdventureUnionNameChange() {
	_ = s.loginAndSelectCharacter()

	nameChangeResp, err := s.Client.Post("/api/v1/adventure/union/name_change", map[string]interface{}{
		"field_1": "测试冒险联盟",
	})
	s.NoError(err)
	s.NotNil(nameChangeResp)

	if nameChangeResp != nil {
		s.Equal(float64(0), nameChangeResp["error"])
	}
}

// TestAdventureUnionExpeditionStart 测试冒险联盟远征开始
func (s *AdventureTestSuite) TestAdventureUnionExpeditionStart() {
	_ = s.loginAndSelectCharacter()

	expeditionStartResp, err := s.Client.Post("/api/v1/adventure/union/expedition_start", map[string]interface{}{
		"field_1": int32(1),
		"field_2": int32(1),
	})
	s.NoError(err)
	s.NotNil(expeditionStartResp)

	if expeditionStartResp != nil {
		errVal, ok := expeditionStartResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionExpeditionCancel 测试冒险联盟远征取消
func (s *AdventureTestSuite) TestAdventureUnionExpeditionCancel() {
	_ = s.loginAndSelectCharacter()

	expeditionCancelResp, err := s.Client.Post("/api/v1/adventure/union/expedition_cancel", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(expeditionCancelResp)

	if expeditionCancelResp != nil {
		errVal, ok := expeditionCancelResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionExpeditionReward 测试冒险联盟远征奖励
func (s *AdventureTestSuite) TestAdventureUnionExpeditionReward() {
	_ = s.loginAndSelectCharacter()

	expeditionRewardResp, err := s.Client.Post("/api/v1/adventure/union/expedition_reward", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(expeditionRewardResp)

	if expeditionRewardResp != nil {
		errVal, ok := expeditionRewardResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionSubdueInfo 测试冒险联盟讨伐信息
func (s *AdventureTestSuite) TestAdventureUnionSubdueInfo() {
	_ = s.loginAndSelectCharacter()

	subdueInfoResp, err := s.Client.Post("/api/v1/adventure/union/subdue_info", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(subdueInfoResp)

	if subdueInfoResp != nil {
		errVal, ok := subdueInfoResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionSubdueStart 测试冒险联盟讨伐开始
func (s *AdventureTestSuite) TestAdventureUnionSubdueStart() {
	charguid := s.loginAndSelectCharacter()

	subdueStartResp, err := s.Client.Post("/api/v1/adventure/union/subdue_start", map[string]interface{}{
		"field_1": int32(1),
		"field_2": int32(1),
		"field_3": charguid,
	})
	s.NoError(err)
	s.NotNil(subdueStartResp)

	if subdueStartResp != nil {
		errVal, ok := subdueStartResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionSubdueReward 测试冒险联盟讨伐奖励
func (s *AdventureTestSuite) TestAdventureUnionSubdueReward() {
	_ = s.loginAndSelectCharacter()

	subdueRewardResp, err := s.Client.Post("/api/v1/adventure/union/subdue_reward", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(subdueRewardResp)

	if subdueRewardResp != nil {
		errVal, ok := subdueRewardResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionOpenShareboardSlot 测试冒险联盟展示板槽位开启
func (s *AdventureTestSuite) TestAdventureUnionOpenShareboardSlot() {
	_ = s.loginAndSelectCharacter()

	openSlotResp, err := s.Client.Post("/api/v1/adventure/union/open_shareboard_slot", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(openSlotResp)

	if openSlotResp != nil {
		errVal, ok := openSlotResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionSetShareboard 测试冒险联盟展示板设置
func (s *AdventureTestSuite) TestAdventureUnionSetShareboard() {
	_ = s.loginAndSelectCharacter()

	setShareboardResp, err := s.Client.Post("/api/v1/adventure/union/set_shareboard", map[string]interface{}{
		"field_1": int32(1),
		"field_2": int32(1),
		"field_4": true,
	})
	s.NoError(err)
	s.NotNil(setShareboardResp)

	if setShareboardResp != nil {
		errVal, ok := setShareboardResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureReapInfo 测试冒险奖励信息
func (s *AdventureTestSuite) TestAdventureReapInfo() {
	_ = s.loginAndSelectCharacter()

	reapInfoResp, err := s.Client.Post("/api/v1/adventure/reap/info", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(reapInfoResp)

	if reapInfoResp != nil {
		errVal, ok := reapInfoResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureReapReward 测试冒险奖励领取
func (s *AdventureTestSuite) TestAdventureReapReward() {
	_ = s.loginAndSelectCharacter()

	reapRewardResp, err := s.Client.Post("/api/v1/adventure/reap/reward", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(reapRewardResp)

	if reapRewardResp != nil {
		errVal, ok := reapRewardResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionSearchStart 测试冒险联盟搜索开始
func (s *AdventureTestSuite) TestAdventureUnionSearchStart() {
	_ = s.loginAndSelectCharacter()

	searchStartResp, err := s.Client.Post("/api/v1/adventure/union/search_start", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(searchStartResp)

	if searchStartResp != nil {
		errVal, ok := searchStartResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionCollectionReward 测试冒险联盟收藏奖励
func (s *AdventureTestSuite) TestAdventureUnionCollectionReward() {
	_ = s.loginAndSelectCharacter()

	collectionRewardResp, err := s.Client.Post("/api/v1/adventure/union/collection_reward", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(collectionRewardResp)

	if collectionRewardResp != nil {
		errVal, ok := collectionRewardResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// TestAdventureUnionLevelReward 测试冒险联盟等级奖励
func (s *AdventureTestSuite) TestAdventureUnionLevelReward() {
	_ = s.loginAndSelectCharacter()

	levelRewardResp, err := s.Client.Post("/api/v1/adventure/union/level_reward", map[string]interface{}{
		"field_1": int32(1),
	})
	s.NoError(err)
	s.NotNil(levelRewardResp)

	if levelRewardResp != nil {
		errVal, ok := levelRewardResp["error"]
		s.True(ok && (errVal == float64(0) || errVal == float64(3)),
			fmt.Sprintf("Expected error 0 or 3, got: %v", errVal))
	}
}

// loginAndSelectCharacter 辅助函数：登录并选择角色
func (s *AdventureTestSuite) loginAndSelectCharacter() uint64 {
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

func TestAdventureTestSuite(t *testing.T) {
	suite.Run(t, new(AdventureTestSuite))
}
