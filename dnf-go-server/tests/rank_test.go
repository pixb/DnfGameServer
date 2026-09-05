package tests

import (
	"fmt"
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

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户登录并确保有角色可选
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

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		// 无角色时创建(与其余套件一致)
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": fmt.Sprintf("RankHero%02d", slot),
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

	// 取账号首个角色(单角色账号 list[0] 即目标角色)
	firstChar, ok := characters[0].(map[string]interface{})
	if !ok {
		s.T().Fatal("Failed to parse character")
	}
	if guid, ok := firstChar["charGuid"].(float64); ok {
		return uint64(guid)
	}
	if uid, ok := firstChar["uid"].(float64); ok {
		return uint64(uid)
	}

	s.T().Fatal("Failed to select character")
	return 0
}

func TestRankTestSuite(t *testing.T) {
	suite.Run(t, new(RankTestSuite))
}
