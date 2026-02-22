package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type PkTestSuite struct {
	BaseTestSuite
}

func (s *PkTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestMultiPlayRequestMatch 测试多人游戏请求匹配
func (s *PkTestSuite) TestMultiPlayRequestMatch() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 请求多人游戏匹配
	matchResp, err := s.Client.Post("/api/v1/pk/multi_play_request_match", map[string]interface{}{
		"transId":      uint32(1),
		"matchtype":    uint32(1),
		"dungeonindex": uint32(1),
	})
	s.NoError(err)
	s.NotNil(matchResp)

	if matchResp != nil {
		s.NotNil(matchResp["transId"])
	}
}

// TestMultiPlayRequestMatchCancel 测试取消多人游戏请求匹配
func (s *PkTestSuite) TestMultiPlayRequestMatchCancel() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 取消多人游戏匹配
	cancelResp, err := s.Client.Post("/api/v1/pk/multi_play_request_match_cancel", map[string]interface{}{
		"transId":      uint32(1),
		"matchingguid": uint64(123456),
	})
	s.NoError(err)
	s.NotNil(cancelResp)

	if cancelResp != nil {
		s.NotNil(cancelResp["transId"])
	}
}

// TestHistoricSiteNoti 测试历史遗迹通知
func (s *PkTestSuite) TestHistoricSiteNoti() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取历史遗迹通知
	notiResp, err := s.Client.Post("/api/v1/pk/historic_site_noti", map[string]interface{}{
		"transId": uint32(1),
	})
	s.NoError(err)
	s.NotNil(notiResp)

	if notiResp != nil {
		s.NotNil(notiResp["transId"])
	}
}

// TestLoadGuildDonationInfo 测试加载公会捐赠信息
func (s *PkTestSuite) TestLoadGuildDonationInfo() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 加载公会捐赠信息
	donationResp, err := s.Client.Post("/api/v1/pk/load_guild_donation_info", map[string]interface{}{
		"transId": uint32(1),
	})
	s.NoError(err)
	s.NotNil(donationResp)

	if donationResp != nil {
		s.NotNil(donationResp["transId"])
		s.NotNil(donationResp["recipe"])
	}
}

// TestDreamMazeBasicInfo 测试梦境迷宫基本信息
func (s *PkTestSuite) TestDreamMazeBasicInfo() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取梦境迷宫基本信息
	mazeResp, err := s.Client.Post("/api/v1/pk/dream_maze_basic_info", map[string]interface{}{
		"transId": uint32(1),
	})
	s.NoError(err)
	s.NotNil(mazeResp)

	if mazeResp != nil {
		s.NotNil(mazeResp["transId"])
	}
}

// TestRaidEntranceCount 测试副本入场次数
func (s *PkTestSuite) TestRaidEntranceCount() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取副本入场次数
	raidResp, err := s.Client.Post("/api/v1/pk/raid_entrance_count", map[string]interface{}{
		"transId": uint32(1),
	})
	s.NoError(err)
	s.NotNil(raidResp)

	if raidResp != nil {
		s.NotNil(raidResp["transId"])
		s.NotNil(raidResp["entrance"])
	}
}

// TestLoadingProgress 测试加载进度
func (s *PkTestSuite) TestLoadingProgress() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 报告加载进度
	progressResp, err := s.Client.Post("/api/v1/pk/loading_progress", map[string]interface{}{
		"transId": uint32(1),
		"value":   uint32(50),
	})
	s.NoError(err)
	s.NotNil(progressResp)

	if progressResp != nil {
		s.NotNil(progressResp["transId"])
		s.NotNil(progressResp["matchingguid"])
		s.NotNil(progressResp["charguid"])
		s.Equal(float64(50), progressResp["value"])
	}
}

// TestReturnToTownAtMultiPlay 测试返回城镇
func (s *PkTestSuite) TestReturnToTownAtMultiPlay() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 返回城镇
	returnResp, err := s.Client.Post("/api/v1/pk/return_to_town", map[string]interface{}{
		"transId": uint32(1),
	})
	s.NoError(err)
	s.NotNil(returnResp)

	if returnResp != nil {
		s.NotNil(returnResp["transId"])
	}
}

// TestCustomGameRoomSetting 测试自定义游戏房间设置
func (s *PkTestSuite) TestCustomGameRoomSetting() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 设置自定义游戏房间
	settingResp, err := s.Client.Post("/api/v1/pk/custom_game_room_setting", map[string]interface{}{
		"transId": uint32(1),
		"customdata": map[string]interface{}{
			"senderguid": uint64(charguid),
			"type":       uint32(3),
			"iValue":     int32(100),
		},
	})
	s.NoError(err)
	s.NotNil(settingResp)

	if settingResp != nil {
		s.NotNil(settingResp["transId"])
	}
}

// TestPvpRecord 测试 PK 记录
func (s *PkTestSuite) TestPvpRecord() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取 PK 记录
	recordResp, err := s.Client.Get(fmt.Sprintf("/api/v1/pk/record?charguid=%d", uint64(charguid)))
	s.NoError(err)
	s.NotNil(recordResp)

	if recordResp != nil {
		s.NotNil(recordResp["records"])
	}
}

// TestPvpRanking 测试 PK 排名
func (s *PkTestSuite) TestPvpRanking() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取 PK 排名
	rankingResp, err := s.Client.Get("/api/v1/pk/ranking")
	s.NoError(err)
	s.NotNil(rankingResp)

	if rankingResp != nil {
		s.NotNil(rankingResp["rankings"])
	}
}

// TestPvpStats 测试 PK 统计
func (s *PkTestSuite) TestPvpStats() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取 PK 统计
	statsResp, err := s.Client.Get(fmt.Sprintf("/api/v1/pk/stats?charguid=%d", uint64(charguid)))
	s.NoError(err)
	s.NotNil(statsResp)

	if statsResp != nil {
		s.NotNil(statsResp["stats"])
	}
}

// TestPvpMatchHistory 测试 PK 匹配历史
func (s *PkTestSuite) TestPvpMatchHistory() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取 PK 匹配历史
	historyResp, err := s.Client.Get(fmt.Sprintf("/api/v1/pk/match_history?charguid=%d", uint64(charguid)))
	s.NoError(err)
	s.NotNil(historyResp)

	if historyResp != nil {
		s.NotNil(historyResp["history"])
	}
}

// TestPvpSeasonInfo 测试 PK 赛季信息
func (s *PkTestSuite) TestPvpSeasonInfo() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取 PK 赛季信息
	seasonResp, err := s.Client.Get("/api/v1/pk/season_info")
	s.NoError(err)
	s.NotNil(seasonResp)

	if seasonResp != nil {
		s.NotNil(seasonResp["season"])
	}
}

// TestPvpReward 测试 PK 奖励
func (s *PkTestSuite) TestPvpReward() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取 PK 奖励
	rewardResp, err := s.Client.Get(fmt.Sprintf("/api/v1/pk/reward?charguid=%d", uint64(charguid)))
	s.NoError(err)
	s.NotNil(rewardResp)

	if rewardResp != nil {
		s.NotNil(rewardResp["rewards"])
	}
}

// TestPvpDailyReset 测试 PK 每日重置
func (s *PkTestSuite) TestPvpDailyReset() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 执行 PK 每日重置
	resetResp, err := s.Client.Post("/api/v1/pk/daily_reset", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resetResp)

	if resetResp != nil {
		s.NotNil(resetResp["error"])
	}
}

// TestPvpMatchTypes 测试 PK 匹配类型
func (s *PkTestSuite) TestPvpMatchTypes() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取 PK 匹配类型
	typesResp, err := s.Client.Get("/api/v1/pk/match_types")
	s.NoError(err)
	s.NotNil(typesResp)

	if typesResp != nil {
		s.NotNil(typesResp["types"])
	}
}

// TestPvpBattleResult 测试 PK 战斗结果
func (s *PkTestSuite) TestPvpBattleResult() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 提交 PK 战斗结果
	resultResp, err := s.Client.Post("/api/v1/pk/battle_result", map[string]interface{}{
		"matchingguid": uint64(123456),
		"win":          true,
		"score":        int32(100),
		"opponentId":   uint64(789012),
	})
	s.NoError(err)
	s.NotNil(resultResp)

	if resultResp != nil {
		s.NotNil(resultResp["error"])
	}
}

// loginAndSelectCharacter 辅助函数：登录并选择角色
func (s *PkTestSuite) loginAndSelectCharacter() int64 {
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

	// 调试信息：打印listResp的结构
	fmt.Printf("listResp: %+v\n", listResp)

	// 检查characters字段
	if charactersData, ok := listResp["characters"]; ok {
		fmt.Printf("charactersData type: %T\n", charactersData)

		// 尝试转换为[]interface{}
		if charactersArray, ok := charactersData.([]interface{}); ok {
			fmt.Printf("charactersArray length: %d\n", len(charactersArray))

			if len(charactersArray) > 0 {
				firstChar := charactersArray[0]
				fmt.Printf("firstChar type: %T\n", firstChar)

				if charMap, ok := firstChar.(map[string]interface{}); ok {
					fmt.Printf("firstChar map: %+v\n", charMap)

					// 检查是否有charGuid字段
					if charGuidValue, ok := charMap["charGuid"]; ok {
						fmt.Printf("charGuidValue: %v, type: %T\n", charGuidValue, charGuidValue)
						if floatValue, ok := charGuidValue.(float64); ok {
							charguid := int64(floatValue)
							return charguid
						} else {
							s.T().Fatalf("charGuid is not a float64: %T", charGuidValue)
						}
					} else {
						// 尝试其他可能的字段名
						fmt.Println("No charGuid field found, checking other fields:")
						for key := range charMap {
							fmt.Printf("Field: %s\n", key)
						}
						s.T().Fatal("No charGuid field found in character")
					}
				} else {
					s.T().Fatalf("firstChar is not a map[string]interface{}: %T", firstChar)
				}
			} else {
				s.T().Fatal("No characters available in array")
			}
		} else {
			s.T().Fatalf("characters is not a []interface{}: %T", charactersData)
		}
	} else {
		s.T().Fatal("No characters field in response")
	}

	return 0
}

// TestPkSuite 运行所有 PK 测试
func TestPkSuite(t *testing.T) {
	suite.Run(t, new(PkTestSuite))
}
