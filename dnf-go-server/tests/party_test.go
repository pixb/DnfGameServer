package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type PartyTestSuite struct {
	BaseTestSuite
}

func (s *PartyTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestSearchPartyList 测试搜索队伍列表
func (s *PartyTestSuite) TestSearchPartyList() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("pt_search_01", 1)

	searchResp, err := s.Client.Post("/api/v1/party/search", map[string]interface{}{
		"dungeonindex": 0,
		"minlevel":     1,
		"maxlevel":     99,
	})
	s.NoError(err)
	s.NotNil(searchResp)

	if searchResp != nil {
		s.Equal(float64(0), searchResp["error"])
	}
}

// TestCreateParty 测试创建队伍
func (s *PartyTestSuite) TestCreateParty() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("pt_create_01", 2)

	createResp, err := s.Client.Post("/api/v1/party/create", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(createResp)

	if createResp != nil {
		s.Equal(float64(0), createResp["error"], "Failed to create party: %+v", createResp)
	}
}

// TestCheckProhibitedWord 测试检查禁用词
func (s *PartyTestSuite) TestCheckProhibitedWord() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("pt_check_01", 3)

	checkResp, err := s.Client.Post("/api/v1/party/check_prohibited_word", map[string]interface{}{
		"word": "test",
	})
	s.NoError(err)
	s.NotNil(checkResp)

	if checkResp != nil {
		s.Equal(float64(0), checkResp["error"])
	}
}

// TestTargetUserPartyInfo 测试获取目标用户队伍信息
func (s *PartyTestSuite) TestTargetUserPartyInfo() {
	charGuid := s.loginAndSelectCharacterWithUserAndSlot("pt_target_01", 4)

	infoResp, err := s.Client.Post("/api/v1/party/target_user_info", map[string]interface{}{
		"charguid": charGuid,
	})
	s.NoError(err)
	s.NotNil(infoResp)

	if infoResp != nil {
		if errVal, ok := infoResp["error"].(float64); ok {
			s.Equal(float64(1), errVal)
		}
	}
}

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户和槽位登录并选择角色
func (s *PartyTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
	// 1. 登录
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
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

	s.T().Logf("Character list response: %+v", listResp)

	characters, ok := listResp["characters"].([]interface{})
	var charguid uint64
	var found bool

	if ok && len(characters) > 0 {
		for _, char := range characters {
			charMap := char.(map[string]interface{})
			s.T().Logf("Character: %+v", charMap)
			if charSlot, ok := charMap["roleId"].(float64); ok && int(charSlot) == slot {
				switch v := charMap["uid"].(type) {
				case float64:
					charguid = uint64(v)
				case string:
					charguid = 0
					if len(v) > 0 {
						fmt.Sscanf(v, "%d", &charguid)
					}
				}
				found = true
				break
			}
		}
	}

	if !found {
		charName := openid
		if len(charName) > 12 {
			charName = charName[:12]
		}
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": charName,
			"job":  1,
			"slot": slot,
		})
		s.NoError(err)
		s.NotNil(createResp)
		if createResp != nil {
			if errorVal, ok := createResp["error"].(float64); ok && errorVal != 0 {
				if errorVal == 3 {
					listResp, err = s.Client.Get("/api/v1/character/list")
					s.NoError(err)
					s.NotNil(listResp)

					characters, ok = listResp["characters"].([]interface{})
					if ok && len(characters) > 0 {
						for _, char := range characters {
							charMap := char.(map[string]interface{})
							if charSlot, ok := charMap["roleId"].(float64); ok && int(charSlot) == slot {
								switch v := charMap["uid"].(type) {
								case float64:
									charguid = uint64(v)
								case string:
									charguid = 0
									if len(v) > 0 {
										fmt.Sscanf(v, "%d", &charguid)
									}
								}
								found = true
								break
							}
						}
					}
					if !found {
						s.T().Fatalf("Failed to create character: error=%d (role name exists), response=%+v", int(errorVal), createResp)
					}
				} else {
					s.T().Fatalf("Failed to create character: error=%d, response=%+v", int(errorVal), createResp)
				}
			}
		}

		if !found {
			listResp, err = s.Client.Get("/api/v1/character/list")
			s.NoError(err)
			s.NotNil(listResp)

			characters, ok = listResp["characters"].([]interface{})
			if !ok || len(characters) == 0 {
				s.T().Fatal("Failed to create character")
			}

			for _, char := range characters {
				charMap := char.(map[string]interface{})
				if charSlot, ok := charMap["roleId"].(float64); ok && int(charSlot) == slot {
					switch v := charMap["uid"].(type) {
					case float64:
						charguid = uint64(v)
					case string:
						charguid = 0
						if len(v) > 0 {
							fmt.Sscanf(v, "%d", &charguid)
						}
					}
					break
				}
			}
		}
	}

	// 4. 选择角色
	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"uid": charguid,
	})
	s.NoError(err)
	s.NotNil(selectResp)

	s.T().Logf("Select character response: %+v, keys: %v", selectResp, getKeys(selectResp))

	if authToken, ok := selectResp["auth_token"].(string); ok {
		s.Client.SetToken(authToken)
	} else if authToken, ok := selectResp["authToken"].(string); ok {
		s.Client.SetToken(authToken)
	} else {
		s.T().Fatalf("No auth token found in response: %+v, keys: %v", selectResp, getKeys(selectResp))
	}

	return charguid
}

func getKeys(m map[string]interface{}) []string {
	keys := make([]string, 0, len(m))
	for k := range m {
		keys = append(keys, k)
	}
	return keys
}

func TestPartyTestSuite(t *testing.T) {
	suite.Run(t, new(PartyTestSuite))
}
