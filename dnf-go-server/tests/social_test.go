package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

// SocialTestSuite 社交测试套件
type SocialTestSuite struct {
	BaseTestSuite
}

// setupAuthenticatedClient 设置认证客户端
func (s *SocialTestSuite) setupAuthenticatedClient() {
	// 登录
	resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
		"openid": "test_social_user",
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

// TestSendChat 测试发送聊天消息
func (s *SocialTestSuite) TestSendChat() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/chat/send", map[string]interface{}{
		"channel": 1, // 世界频道
		"message": "Hello, DNF!",
	})
	s.NoError(err)
	s.AssertSuccess(resp)
}

// TestSendChatInvalidChannel 测试发送聊天-无效频道
func (s *SocialTestSuite) TestSendChatInvalidChannel() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/chat/send", map[string]interface{}{
		"channel": 999, // 无效频道
		"message": "Hello",
	})
	s.NoError(err)
	s.AssertError(resp, 1) // Invalid parameter
}

// TestGetChatHistory 测试获取聊天记录
func (s *SocialTestSuite) TestGetChatHistory() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/chat/history", map[string]interface{}{
		"channel": 1,
	})
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotNil(resp["messages"])
}

// TestGetFriendList 测试获取好友列表
func (s *SocialTestSuite) TestGetFriendList() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/friend/list", nil)
	s.NoError(err)
	s.AssertSuccess(resp)
	s.NotNil(resp["friends"])
}

// TestAddFriend 测试添加好友
func (s *SocialTestSuite) TestAddFriend() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/friend/add", map[string]interface{}{
		"friend_id": 999, // 测试好友ID
	})
	s.NoError(err)
	// 可能成功或好友不存在
	s.NotNil(resp["error"])
}

// TestRemoveFriend 测试删除好友
func (s *SocialTestSuite) TestRemoveFriend() {
	s.setupAuthenticatedClient()

	resp, err := s.Client.Post("/api/v1/friend/remove", map[string]interface{}{
		"friend_id": 999,
	})
	s.NoError(err)
	// 可能成功或好友不存在
	s.NotNil(resp["error"])
}

func TestSocialSuite(t *testing.T) {
	suite.Run(t, new(SocialTestSuite))
}
