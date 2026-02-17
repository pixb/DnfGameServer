package tests

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/suite"
)

type MakeTestSuite struct {
	BaseTestSuite
}

func TestMakeTestSuite(t *testing.T) {
	suite.Run(t, new(MakeTestSuite))
}

func (s *MakeTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

func (s *MakeTestSuite) loginAndSelectCharacter() uint64 {
	return s.loginAndSelectCharacterWithUser("test_user_001")
}

func (s *MakeTestSuite) loginAndSelectCharacterWithUser(openid string) uint64 {
	return s.loginAndSelectCharacterWithUserAndSlot(openid, 1)
}

func (s *MakeTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Fatal("Login failed")
		return 0
	}

	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	var charguid uint64
	var found bool

	if ok && len(characters) > 0 {
		for _, char := range characters {
			charMap := char.(map[string]interface{})
			if charSlot, ok := charMap["slot"].(float64); ok && int(charSlot) == slot {
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
				if errorVal == 5 {
					listResp, err = s.Client.Get("/api/v1/character/list")
					s.NoError(err)
					s.NotNil(listResp)

					characters, ok = listResp["characters"].([]interface{})
					if ok && len(characters) > 0 {
						for _, char := range characters {
							charMap := char.(map[string]interface{})
							if charSlot, ok := charMap["slot"].(float64); ok && int(charSlot) == slot {
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
						s.T().Fatalf("Failed to create character: error=%d, response=%+v", int(errorVal), createResp)
					}
				} else if errorVal == 3 {
					listResp, err = s.Client.Get("/api/v1/character/list")
					s.NoError(err)
					s.NotNil(listResp)

					characters, ok = listResp["characters"].([]interface{})
					if ok && len(characters) > 0 {
						for _, char := range characters {
							charMap := char.(map[string]interface{})
							if charSlot, ok := charMap["slot"].(float64); ok && int(charSlot) == slot {
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
				if charSlot, ok := charMap["slot"].(float64); ok && int(charSlot) == slot {
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

func (s *MakeTestSuite) TestEmblemUpgrade() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_emblem_01", 1)

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade", map[string]interface{}{
		"index":    1001,
		"trycount": 1,
		"talisman": 0,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestEmblemUpgradeWithTalisman() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_emblem_02", 2)

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade", map[string]interface{}{
		"index":    1001,
		"trycount": 1,
		"talisman": 1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	if successCount, ok := resp["successcount"]; ok {
		s.Equal(float64(1), successCount)
	}
}

func (s *MakeTestSuite) TestEmblemUpgradeQuick() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_emblem_03", 3)

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade_quick", map[string]interface{}{
		"source": []map[string]interface{}{
			{"index": 1001, "count": 2},
		},
		"target": 1002,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestAvatarCompose() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_avatar_01", 4)

	resp, err := s.Client.Post("/api/v1/make/avatar/compose", map[string]interface{}{
		"guids": []uint64{1001, 1002, 1003},
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestProductionInfo() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_prod_01", 5)

	resp, err := s.Client.Get("/api/v1/make/production/info?slottype=1")
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestProductionRegister() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_prod_02", 6)

	resp, err := s.Client.Post("/api/v1/make/production/register", map[string]interface{}{
		"slot_index":   1,
		"recipe_index": 2001,
		"count":        1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestItemCombine() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_comb_01", 7)

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1001,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 1},
			{"index": 2002, "count": 1},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestItemDisjoint() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_disj_01", 8)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": []uint64{1001, 1002, 1003},
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestCardCompose() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_card_01", 9)

	resp, err := s.Client.Post("/api/v1/make/card/compose", map[string]interface{}{
		"user_card_list": []map[string]interface{}{
			{"index": 3001, "count": 2},
		},
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestWardrobeSetSlot() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_ward_01", 10)

	resp, err := s.Client.Post("/api/v1/make/wardrobe/set_slot", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
}

func (s *MakeTestSuite) TestEmblemUpgradeNotEnoughEmblem() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_emblem_04", 11)

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade", map[string]interface{}{
		"index":    9999,
		"trycount": 1,
		"talisman": 0,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
}

func (s *MakeTestSuite) TestProductionRegisterNotEnoughMoney() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_prod_03", 12)

	resp, err := s.Client.Post("/api/v1/make/production/register", map[string]interface{}{
		"slot_index":   1,
		"recipe_index": 9999,
		"count":        100,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
}

func (s *MakeTestSuite) TestCardComposeNotEnoughCards() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_card_02", 13)

	resp, err := s.Client.Post("/api/v1/make/card/compose", map[string]interface{}{
		"user_card_list": []map[string]interface{}{
			{"index": 9999, "count": 2},
		},
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
}
