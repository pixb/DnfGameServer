package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type CharacterTestSuite struct {
	BaseTestSuite
	openid string
}

func TestCharacterTestSuite(t *testing.T) {
	suite.Run(t, new(CharacterTestSuite))
}

func (s *CharacterTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.openid = "test_character_openid"
}

func (s *CharacterTestSuite) TestCharacterCreate() {
	// 登录
	token := s.LoginAs(s.openid)
	s.NotEmpty(token, "Login should return a token")

	// 创建角色，使用唯一名称
	uniqueName := fmt.Sprintf("TestCharacter_%d", time.Now().UnixNano())
	resp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name":     uniqueName,
		"job":      1,
		"growType": 0,
	})
	s.NoError(err)
	s.AssertSuccess(resp)

	// 检查返回数据
	data, ok := resp["data"].(map[string]interface{})
	s.True(ok, "Response should contain data")
	s.NotEmpty(data["charGuid"], "Character should have a charGuid")
	s.Equal(uniqueName, data["name"], "Character name should match")
}

func (s *CharacterTestSuite) TestCharacterList() {
	// 登录
	token := s.LoginAs(s.openid)
	s.NotEmpty(token, "Login should return a token")

	// 获取角色列表
	resp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.AssertSuccess(resp)

	// 检查返回数据
	characters, ok := resp["characters"].([]interface{})
	s.True(ok, "Response should contain characters list")
	s.Greater(len(characters), 0, "Should have at least one character")
}

func (s *CharacterTestSuite) TestCharacterSelect() {
	// 登录
	token := s.LoginAs(s.openid)
	s.NotEmpty(token, "Login should return a token")

	// 获取角色列表
	resp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.AssertSuccess(resp)

	// 选择第一个角色
	characters, ok := resp["characters"].([]interface{})
	s.True(ok, "Response should contain characters list")
	s.Greater(len(characters), 0, "Should have at least one character")

	character := characters[0].(map[string]interface{})
	charGuid := character["charGuid"]

	// 选择角色
	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"charGuid": charGuid,
	})
	s.NoError(err)
	s.AssertSuccess(selectResp)
	s.Equal(charGuid, selectResp["charGuid"], "Selected character should match")
}

func (s *CharacterTestSuite) TestCharacterEnter() {
	// 登录
	token := s.LoginAs(s.openid)
	s.NotEmpty(token, "Login should return a token")

	// 进入游戏
	enterResp, err := s.Client.Post("/api/v1/character/enter", nil)
	s.NoError(err)
	s.AssertSuccess(enterResp)
	s.NotEmpty(enterResp["charGuid"], "Should return a character GUID")
	s.NotEmpty(enterResp["serverTime"], "Should return server time")
}
