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

	// 创建角色，使用唯一名称(16 字符内: 前缀+12 位纳秒后缀)
	uniqueName := fmt.Sprintf("TC_%012d", time.Now().UnixNano()%1000000000000)
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

// TestCharacterCreateDuplicateName 角色名全局唯一(2026-09-06 第二十五轮):
// 同名再次创建应拒绝, 且不产生新角色
func (s *CharacterTestSuite) TestCharacterCreateDuplicateName() {
	token := s.LoginAs(s.openid)
	s.NotEmpty(token, "Login should return a token")

	name := fmt.Sprintf("TD_%012d", time.Now().UnixNano()%1000000000000)
	first, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": name,
		"job":  1,
	})
	s.NoError(err)
	s.AssertSuccess(first)

	// 同名二次创建被拒
	second, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": name,
		"job":  1,
	})
	s.NoError(err)
	s.NotNil(second)
	if errVal, ok := second["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	if msg, ok := second["message"].(string); ok {
		s.Contains(msg, "已存在")
	}
}

// TestCharacterCreateInvalidName 角色名长度/字符集校验(2026-09-06 第二十六轮):
// 超长(>16 字符)与非法字符(空格/标点等)建角应拒绝 error 4, 且不产生新角色
func (s *CharacterTestSuite) TestCharacterCreateInvalidName() {
	token := s.LoginAs(s.openid)
	s.NotEmpty(token, "Login should return a token")

	// 超长名: 17 个字符
	longResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": "AAAAAAAAAAAAAAAAA",
		"job":  1,
	})
	s.NoError(err)
	s.NotNil(longResp)
	if errVal, ok := longResp["error"]; ok {
		s.Equal(float64(4), errVal)
	}
	if msg, ok := longResp["message"].(string); ok {
		s.Contains(msg, "长度")
	}

	// 非法字符: 空格与感叹号
	badResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": "Bad Name!",
		"job":  1,
	})
	s.NoError(err)
	s.NotNil(badResp)
	if errVal, ok := badResp["error"]; ok {
		s.Equal(float64(4), errVal)
	}
	if msg, ok := badResp["message"].(string); ok {
		s.Contains(msg, "仅允许")
	}
}

// TestCharacterCreateChineseName 中文角色名合法(2026-09-06 第二十六轮):
// 中文/下划线/数字组合在 16 字符内应创建成功
func (s *CharacterTestSuite) TestCharacterCreateChineseName() {
	token := s.LoginAs(s.openid)
	s.NotEmpty(token, "Login should return a token")

	chineseName := fmt.Sprintf("测试勇士_%06d", time.Now().UnixNano()%1000000)
	resp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": chineseName,
		"job":  1,
	})
	s.NoError(err)
	s.AssertSuccess(resp)

	data, ok := resp["data"].(map[string]interface{})
	s.True(ok, "Response should contain data")
	s.Equal(chineseName, data["name"], "Chinese character name should match")
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
