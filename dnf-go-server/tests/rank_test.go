package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

type RankTestSuite struct {
	BaseTestSuite
}

func (s *RankTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestInquirePersonalRanking 测试查询个人排名
func (s *RankTestSuite) TestInquirePersonalRanking() {
	charGuid := s.loginAndSelectCharacterWithUserAndSlot("rk_personal_01", 1)

	personalResp, err := s.Client.Post("/api/v1/rank/personal", map[string]interface{}{
		"charguid": charGuid,
		"type":     1,
	})
	s.NoError(err)
	s.NotNil(personalResp)

	if personalResp != nil {
		s.Equal(float64(0), personalResp["error"])
	}
}

// TestMyRanking 测试查询我的排名
func (s *RankTestSuite) TestMyRanking() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("rk_my_01", 2)

	myRankResp, err := s.Client.Post("/api/v1/rank/my", map[string]interface{}{
		"type": 1,
	})
	s.NoError(err)
	s.NotNil(myRankResp)

	if myRankResp != nil {
		s.Equal(float64(0), myRankResp["error"])
	}
}

// TestRankFriend 测试查询好友排名
func (s *RankTestSuite) TestRankFriend() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("rk_friend_01", 3)

	friendResp, err := s.Client.Post("/api/v1/rank/friend", map[string]interface{}{
		"type": 1,
	})
	s.NoError(err)
	s.NotNil(friendResp)

	if friendResp != nil {
		s.Equal(float64(0), friendResp["error"])
	}
}

// TestMyPartyRanking 测试查询我的队伍排名
func (s *RankTestSuite) TestMyPartyRanking() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("rk_party_01", 4)

	partyResp, err := s.Client.Post("/api/v1/rank/party", map[string]interface{}{
		"type": 1,
	})
	s.NoError(err)
	s.NotNil(partyResp)

	if partyResp != nil {
		s.Equal(float64(0), partyResp["error"])
	}
}

// TestRankingList 测试查询排行榜列表
func (s *RankTestSuite) TestRankingList() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("rk_list_01", 5)

	listResp, err := s.Client.Post("/api/v1/rank/list", map[string]interface{}{
		"type":      1,
		"page":      1,
		"page_size": 20,
	})
	s.NoError(err)
	s.NotNil(listResp)

	if listResp != nil {
		s.Equal(float64(0), listResp["error"])
	}
}

// TestRankingListLevel 测试查询等级排行榜
func (s *RankTestSuite) TestRankingListLevel() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("rk_level_01", 6)

	levelResp, err := s.Client.Post("/api/v1/rank/list", map[string]interface{}{
		"type":      1,
		"page":      1,
		"page_size": 20,
	})
	s.NoError(err)
	s.NotNil(levelResp)

	if levelResp != nil {
		s.Equal(float64(0), levelResp["error"])
	}
}

// TestRankingListEquipScore 测试查询战力排行榜
func (s *RankTestSuite) TestRankingListEquipScore() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("rk_equip_01", 7)

	equipResp, err := s.Client.Post("/api/v1/rank/list", map[string]interface{}{
		"type":      2,
		"page":      1,
		"page_size": 20,
	})
	s.NoError(err)
	s.NotNil(equipResp)

	if equipResp != nil {
		s.Equal(float64(0), equipResp["error"])
	}
}

// TestRankingListDungeon 测试查询副本排行榜
func (s *RankTestSuite) TestRankingListDungeon() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("rk_dungeon_01", 8)

	dungeonResp, err := s.Client.Post("/api/v1/rank/list", map[string]interface{}{
		"type":      3,
		"page":      1,
		"page_size": 20,
	})
	s.NoError(err)
	s.NotNil(dungeonResp)

	if dungeonResp != nil {
		s.Equal(float64(0), dungeonResp["error"])
	}
}

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户和槽位登录并选择角色
func (s *RankTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
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

func TestRankTestSuite(t *testing.T) {
	suite.Run(t, new(RankTestSuite))
}
