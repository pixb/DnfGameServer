package tests

import (
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
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
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
	if !ok || len(characters) == 0 {
		s.T().Fatal("No characters available")
		return 0
	}

	firstChar := characters[0].(map[string]interface{})
	charguid := uint64(firstChar["uid"].(float64))

	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"uid": charguid,
	})
	s.NoError(err)
	s.NotNil(selectResp)

	return charguid
}

func (s *MakeTestSuite) TestEmblemUpgrade() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade", map[string]interface{}{
		"index":    1001,
		"trycount": 1,
		"talisman": 0,
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
}

func (s *MakeTestSuite) TestEmblemUpgradeWithTalisman() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade", map[string]interface{}{
		"index":    1001,
		"trycount": 1,
		"talisman": 1,
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
	s.Equal(float64(1), resp["successcount"])
}

func (s *MakeTestSuite) TestEmblemUpgradeQuick() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade_quick", map[string]interface{}{
		"source": []map[string]interface{}{
			{"index": 1001, "count": 2},
		},
		"target": 1002,
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
	s.NotNil(resp["rewards"])
}

func (s *MakeTestSuite) TestAvatarCompose() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/avatar/compose", map[string]interface{}{
		"guids": []uint64{1001, 1002, 1003},
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
	s.NotNil(resp["rewards"])
}

func (s *MakeTestSuite) TestProductionInfo() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Get("/api/v1/make/production/info?slottype=1")
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
	s.NotNil(resp["infos"])
}

func (s *MakeTestSuite) TestProductionRegister() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/production/register", map[string]interface{}{
		"slot_index":   1,
		"recipe_index": 2001,
		"count":        1,
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
	s.NotNil(resp["rewards"])
	s.NotNil(resp["materialitems"])
}

func (s *MakeTestSuite) TestItemCombine() {
	_ = s.loginAndSelectCharacter()

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

	s.Equal(float64(0), resp["error"])
	s.NotNil(resp["rewards"])
}

func (s *MakeTestSuite) TestItemDisjoint() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": []uint64{1001, 1002, 1003},
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
	s.NotNil(resp["rewards"])
}

func (s *MakeTestSuite) TestCardCompose() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/card/compose", map[string]interface{}{
		"user_card_list": []map[string]interface{}{
			{"index": 3001, "count": 2},
		},
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
	s.NotNil(resp["card"])
	s.NotNil(resp["currency"])
}

func (s *MakeTestSuite) TestWardrobeSetSlot() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/wardrobe/set_slot", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(0), resp["error"])
}

func (s *MakeTestSuite) TestEmblemUpgradeNotEnoughEmblem() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/emblem/upgrade", map[string]interface{}{
		"index":    9999,
		"trycount": 1,
		"talisman": 0,
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(1), resp["error"])
}

func (s *MakeTestSuite) TestProductionRegisterNotEnoughMoney() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/production/register", map[string]interface{}{
		"slot_index":   1,
		"recipe_index": 9999,
		"count":        100,
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(1), resp["error"])
}

func (s *MakeTestSuite) TestCardComposeNotEnoughCards() {
	_ = s.loginAndSelectCharacter()

	resp, err := s.Client.Post("/api/v1/make/card/compose", map[string]interface{}{
		"user_card_list": []map[string]interface{}{
			{"index": 9999, "count": 2},
		},
	})
	s.NoError(err)
	s.NotNil(resp)

	s.Equal(float64(1), resp["error"])
}
