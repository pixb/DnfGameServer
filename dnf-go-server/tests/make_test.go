package tests

import (
	"database/sql"
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

// seedBagItems 清空角色背包并预置材料(集成测试数据准备,绕过 HTTP 层)
func seedBagItems(roleID uint64, items map[int32]int32) error {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return fmt.Errorf("open db: %w", err)
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	if _, err := db.Exec("DELETE FROM bag_item WHERE role_id = ?", roleID); err != nil {
		return fmt.Errorf("clear bag: %w", err)
	}
	now := time.Now().Unix()
	for grid, count := range items {
		if _, err := db.Exec(`
			INSERT INTO bag_item (created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes)
			VALUES (?, ?, 'NORMAL', ?, ?, ?, ?, 0, 0, 0, 0, NULL)`,
			now, now, roleID, grid, grid, count); err != nil {
			return fmt.Errorf("seed bag item: %w", err)
		}
	}
	return nil
}

// bagItemIDs 取角色背包前 limit 个物品ID(合成/分解接口入参用)
func bagItemIDs(roleID uint64, limit int) []uint64 {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return nil
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	rows, err := db.Query(
		"SELECT id FROM bag_item WHERE role_id = ? AND row_status = 'NORMAL' ORDER BY grid_index LIMIT ?",
		roleID, limit)
	if err != nil {
		return nil
	}
	defer rows.Close()
	var ids []uint64
	for rows.Next() {
		var id uint64
		if rows.Scan(&id) == nil {
			ids = append(ids, id)
		}
	}
	return ids
}

// clearRolesForOpenids 清理测试账号旧角色(外键级联清背包/货币等), 控制集成数据污染
func clearRolesForOpenids(openids ...string) error {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return fmt.Errorf("open db: %w", err)
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	for _, o := range openids {
		if _, err := db.Exec(`
			DELETE r FROM role r JOIN account a ON r.account_id = a.id WHERE a.openid = ?`, o); err != nil {
			return fmt.Errorf("clear roles: %w", err)
		}
	}
	return nil
}

type MakeTestSuite struct {
	BaseTestSuite
}

func TestMakeTestSuite(t *testing.T) {
	suite.Run(t, new(MakeTestSuite))
}

func (s *MakeTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	// 清理本套件用到的固定 openid 旧角色, 避免角色累积/槽位漂移
	if err := clearRolesForOpenids("mk_comb_01", "mk_disj_01"); err != nil {
		s.T().Logf("clear roles warning: %v", err)
	}
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

	// 角色列表字段为 charGuid(list 响应无 slot/uid 字段)
	charguid := lastCharGuid(listResp)

	if charguid == 0 {
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
				s.T().Fatalf("Failed to create character: error=%d, response=%+v", int(errorVal), createResp)
			}
		}
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)
		charguid = lastCharGuid(listResp)
		if charguid == 0 {
			s.T().Fatal("Failed to create character")
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

// lastCharGuid 取角色列表最后一个 charGuid(最新创建)
func lastCharGuid(listResp map[string]interface{}) uint64 {
	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		return 0
	}
	charMap, ok := characters[len(characters)-1].(map[string]interface{})
	if !ok {
		return 0
	}
	switch v := charMap["charGuid"].(type) {
	case float64:
		return uint64(v)
	case string:
		var id uint64
		if len(v) > 0 {
			fmt.Sscanf(v, "%d", &id)
		}
		return id
	}
	return 0
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
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_comb_01", 7)
	// 预置材料: 格子 2001/2002 各 5 个(合成会按格子校验并扣减)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 5, 2002: 5}))

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
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_disj_01", 8)
	// 预置 3 件物品, 取真实背包 ID 分解
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{1: 1, 2: 1, 3: 1}))
	ids := bagItemIDs(roleID, 3)
	s.Require().Len(ids, 3)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
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
