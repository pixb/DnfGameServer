package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type SocialTestSuite struct {
	BaseTestSuite
}

func (s *SocialTestSuite) setupAuthenticatedClient(openid string) {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)

	if resp == nil {
		s.T().Skip("Login failed")
		return
	}

	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": fmt.Sprintf("SocialHero%d", time.Now().UnixNano()%10000),
			"job":  1,
		})
		s.NoError(err)
		s.NotNil(createResp)
		if createResp != nil && createResp["error"] == float64(0) {
			listResp, _ = s.Client.Get("/api/v1/character/list")
			if listResp != nil {
				characters, _ = listResp["characters"].([]interface{})
			}
		}
	}

	if characters != nil && len(characters) > 0 {
		firstChar := characters[0].(map[string]interface{})
		_, err = s.Client.Post("/api/v1/character/select", map[string]interface{}{
			"uid": firstChar["uid"],
		})
		s.NoError(err)
	}
}

func (s *SocialTestSuite) TestGetFriendList() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/friend/list")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

func (s *SocialTestSuite) TestAddFriend() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social2_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/friend/add", map[string]interface{}{
		"target_name": "NonExistentPlayer",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 6,
			fmt.Sprintf("Expected success or target not found, got: %v", errVal))
	}
}

func (s *SocialTestSuite) TestRemoveFriend() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social3_%d", uniqueID))

	resp, err := s.Client.Post("/api/v1/friend/remove", map[string]interface{}{
		"friend_uid": 99999,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		errVal, _ := resp["error"].(float64)
		s.True(errVal == 0 || errVal == 6,
			fmt.Sprintf("Expected success or friend not found, got: %v", errVal))
	}
}

func (s *SocialTestSuite) TestGetMailList() {
	uniqueID := time.Now().UnixNano()
	s.setupAuthenticatedClient(fmt.Sprintf("test_social4_%d", uniqueID))

	resp, err := s.Client.Get("/api/v1/mail/list")
	s.NoError(err)
	s.NotNil(resp)
	if resp != nil {
		s.Equal(float64(0), resp["error"])
	}
}

// createKnownCharacter 登录并建已知名字角色(返回 charGuid)
// 2026-09-06 第三十五轮: 好友链路测试需要可预知角色名
func (s *SocialTestSuite) createKnownCharacter(openid, name string) float64 {
	token := s.LoginAs(openid)
	s.NotEmpty(token, "Login should return a token")
	resp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": name,
		"job":  1,
	})
	s.NoError(err)
	s.NotNil(resp)
	s.Equal(float64(0), resp["error"], "character create should succeed")
	guid, ok := resp["data"].(map[string]interface{})["charGuid"].(float64)
	s.True(ok, "created character should have charGuid")
	s.Greater(guid, float64(0), "charGuid should be positive")
	return guid
}

// TestFriendLifecycle 好友加/查/幂等/删 全链路(2026-09-06 第三十五轮):
// JSON body 传参(target_name/friend_uid), add → list 可见 → 重复 add 幂等 → remove → list 空
func (s *SocialTestSuite) TestFriendLifecycle() {
	uid := time.Now().UnixNano()
	nameA := fmt.Sprintf("FA_%06d", uid%1000000)
	nameB := fmt.Sprintf("FB_%06d", uid%1000000)
	openidA := fmt.Sprintf("test_friend_a_%d", uid)
	openidB := fmt.Sprintf("test_friend_b_%d", uid)

	guidA := s.createKnownCharacter(openidA, nameA)
	guidB := s.createKnownCharacter(openidB, nameB)
	s.Greater(guidA, float64(0))
	s.Greater(guidB, float64(0))

	// 切回 A 登录态(建 B 时 token 已切换)
	s.setupAuthenticatedClient(openidA)

	// 加 B 为好友(JSON target_name)
	resp, err := s.Client.Post("/api/v1/friend/add", map[string]interface{}{
		"target_name": nameB,
	})
	s.NoError(err)
	s.Equal(float64(0), resp["error"], "add friend should succeed")

	// 列表可见 B
	listResp, err := s.Client.Get("/api/v1/friend/list")
	s.NoError(err)
	friends, ok := listResp["friends"].([]interface{})
	s.True(ok, "friend list should be present")
	s.Len(friends, 1, "should have exactly 1 friend")
	first := friends[0].(map[string]interface{})
	s.Equal(nameB, first["name"], "friend name should match")

	// 重复加幂等(列表仍 1 条)
	resp2, err := s.Client.Post("/api/v1/friend/add", map[string]interface{}{
		"target_name": nameB,
	})
	s.NoError(err)
	s.Equal(float64(0), resp2["error"], "duplicate add should be idempotent")
	listResp2, _ := s.Client.Get("/api/v1/friend/list")
	s.Len(listResp2["friends"].([]interface{}), 1, "duplicate add should not duplicate entry")

	// 删除好友(JSON friend_uid)
	resp3, err := s.Client.Post("/api/v1/friend/remove", map[string]interface{}{
		"friend_uid": guidB,
	})
	s.NoError(err)
	s.Equal(float64(0), resp3["error"], "remove friend should succeed")
	listResp3, _ := s.Client.Get("/api/v1/friend/list")
	s.Len(listResp3["friends"].([]interface{}), 0, "friend list should be empty after remove")
}

// TestAddFriendSelf 不能添加自己为好友(error=8)
func (s *SocialTestSuite) TestAddFriendSelf() {
	uid := time.Now().UnixNano()
	name := fmt.Sprintf("FS_%06d", uid%1000000)
	s.createKnownCharacter(fmt.Sprintf("test_friend_self_%d", uid), name)

	resp, err := s.Client.Post("/api/v1/friend/add", map[string]interface{}{
		"target_name": name,
	})
	s.NoError(err)
	s.Equal(float64(8), resp["error"], "adding self should be rejected with error 8")
}

func TestSocialSuite(t *testing.T) {
	suite.Run(t, new(SocialTestSuite))
}
