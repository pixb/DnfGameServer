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

// getGold 查询角色当前金币(合成费用扣减断言用)
func getGold(roleID uint64) int64 {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return -1
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var gold int64
	if err := db.QueryRow("SELECT gold FROM role_currency WHERE role_id = ?", roleID).Scan(&gold); err != nil {
		return -1
	}
	return gold
}

// countCombineRecords 统计角色合成记录数(配方生效断言用)
func countCombineRecords(roleID uint64) int {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return -1
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var n int
	if err := db.QueryRow("SELECT COUNT(*) FROM t_item_combine WHERE role_id = ?", roleID).Scan(&n); err != nil {
		return -1
	}
	return n
}

// countCombineBySuccess 统计角色指定成败结果的合成记录数(success: 1=成功 0=失败)
func countCombineBySuccess(roleID uint64, success int) int {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return -1
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var n int
	if err := db.QueryRow("SELECT COUNT(*) FROM t_item_combine WHERE role_id = ? AND success = ?", roleID, success).Scan(&n); err != nil {
		return -1
	}
	return n
}

// countDisjointRecords 统计角色分解记录数(分解配置生效断言用)
func countDisjointRecords(roleID uint64) int {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return -1
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var n int
	if err := db.QueryRow("SELECT COUNT(*) FROM t_item_disjoint WHERE role_id = ?", roleID).Scan(&n); err != nil {
		return -1
	}
	return n
}

// bagItemCount 查询角色背包指定模板物品的总数量(分解产出断言用)
func bagItemCount(roleID uint64, itemID int32) int {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return -1
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var n int
	if err := db.QueryRow(
		"SELECT COALESCE(SUM(count), 0) FROM bag_item WHERE role_id = ? AND item_id = ? AND row_status = 'NORMAL'",
		roleID, itemID).Scan(&n); err != nil {
		return -1
	}
	return n
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
	if err := clearRolesForOpenids("mk_comb_01", "mk_disj_01", "mk_comb_02", "mk_comb_03", "mk_disj_02", "mk_disj_03", "mk_comb_04", "mk_comb_05"); err != nil {
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

// TestItemCombineRandomPool 随机产出池(recipe 1004: 2001x1 -> 60%:1001x1 / 40%:1002x1)
// 60 次合成: 材料 2001 全部消耗, 产物两种模板均出现, 记录全部成功
func (s *MakeTestSuite) TestItemCombineRandomPool() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_comb_04", 18)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 60}))

	for i := 0; i < 60; i++ {
		resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
			"index": 1004,
			"material_items": []map[string]interface{}{
				{"index": 2001, "count": 1},
			},
			"count": 1,
		})
		s.NoError(err)
		s.NotNil(resp)
		if resp != nil {
			if errVal, ok := resp["error"]; ok {
				s.Equal(float64(0), errVal)
			}
			// 池配方无失败路径, 响应应恒为 success 且产物为 1001/1002 之一
			s.Equal("success", resp["result"])
			itemID, _ := resp["itemId"].(float64)
			s.True(itemID == 1001 || itemID == 1002, fmt.Sprintf("unexpected item: %v", resp["itemId"]))
		}
	}

	// 材料 2001 全部消耗
	s.Equal(0, bagItemCount(roleID, 2001))
	// 产物 1001/1002 合计 60, 且两种模板均出现过(权重 60/40, 60 次几乎必然)
	got1001 := bagItemCount(roleID, 1001)
	got1002 := bagItemCount(roleID, 1002)
	s.Equal(60, got1001+got1002)
	s.True(got1001 > 0, "1001 从未产出")
	s.True(got1002 > 0, "1002 从未产出")
	// 记录 60 条且全部成功
	s.Equal(60, countCombineRecords(roleID))
	s.Equal(60, countCombineBySuccess(roleID, 1))
	s.Equal(0, countCombineBySuccess(roleID, 0))
}

// TestItemCombineSuccessRate 成功率 + 失败保底(recipe 1005: 2001x1 -> 50%:1001x1 / 失败:2013000000x1)
// 30 次合成: 成功/失败均出现, 产物总数 = 30, 响应 result 与记录 success 标记一致
func (s *MakeTestSuite) TestItemCombineSuccessRate() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_comb_05", 19)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 30}))

	respSuccess := 0
	respFail := 0
	for i := 0; i < 30; i++ {
		resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
			"index": 1005,
			"material_items": []map[string]interface{}{
				{"index": 2001, "count": 1},
			},
			"count": 1,
		})
		s.NoError(err)
		s.NotNil(resp)
		if resp != nil {
			if errVal, ok := resp["error"]; ok {
				s.Equal(float64(0), errVal)
			}
			if resp["result"] == "success" {
				respSuccess++
				itemID, _ := resp["itemId"].(float64)
				s.Equal(float64(1001), itemID, "成功应产出 1001")
			} else {
				respFail++
				itemID, _ := resp["itemId"].(float64)
				s.Equal(float64(2013000000), itemID, "失败应产出保底 2013000000")
			}
		}
	}

	// 材料 2001 全部消耗
	s.Equal(0, bagItemCount(roleID, 2001))
	// 30 次合成: 成功与失败均出现(50% 概率, 30 次几乎必然双态)
	s.Equal(30, respSuccess+respFail)
	s.True(respSuccess > 0, "无成功合成")
	s.True(respFail > 0, "无失败合成")
	// 响应与记录一致: 记录 success 标记数与响应一致
	s.Equal(30, countCombineRecords(roleID))
	s.Equal(respSuccess, countCombineBySuccess(roleID, 1))
	s.Equal(respFail, countCombineBySuccess(roleID, 0))
	// 产物: 成功给 1001, 失败给保底 2013000000, 合计 30
	prod1001 := bagItemCount(roleID, 1001)
	prodFail := bagItemCount(roleID, 2013000000)
	s.Equal(respSuccess, prod1001)
	s.Equal(respFail, prodFail)
	s.Equal(30, prod1001+prodFail)
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

// TestItemCombineRecipeFee 配方驱动: recipe 1002(材料 2001x3+2002x3, 费用 500)
// 验证按配方扣金币(1000 -> 500)且写入合成记录
func (s *MakeTestSuite) TestItemCombineRecipeFee() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_comb_02", 14)
	s.Require().NoError(setGold(roleID, 1000))
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 3, 2002: 3}))

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1002,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 3},
			{"index": 2002, "count": 3},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 配方费用 500 已扣: 1000 -> 500
	s.Equal(int64(500), getGold(roleID))
	// 合成记录已写
	s.Equal(1, countCombineRecords(roleID))
}

// TestItemCombineWrongMaterials 配方驱动: 材料与配方不符(recipe 1001 需 2001x1+2002x1,
// 客户端多给 2001x2)应报错且不产生记录
func (s *MakeTestSuite) TestItemCombineWrongMaterials() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_comb_03", 15)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 5, 2002: 5}))

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1001,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 2},
			{"index": 2002, "count": 1},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	s.Equal(0, countCombineRecords(roleID))
}

// TestItemCombineUnknownRecipe 配方不存在(9999)应报错
func (s *MakeTestSuite) TestItemCombineUnknownRecipe() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("mk_comb_03", 15)

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index":          9999,
		"material_items": []map[string]interface{}{},
		"count":          1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
}

// TestItemCombineNotEnoughMoney 配方驱动: 金币不足(recipe 1002 费用 500, 只有 100)应报错
func (s *MakeTestSuite) TestItemCombineNotEnoughMoney() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_comb_02", 14)
	s.Require().NoError(setGold(roleID, 100))
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 3, 2002: 3}))

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1002,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 3},
			{"index": 2002, "count": 3},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	s.Equal(0, countCombineRecords(roleID))
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
	// 配置驱动: 物品 1/2/3 各产出 2013000000x10, 聚合 30 个
	s.Equal(30, bagItemCount(roleID, 2013000000))
	s.Equal(1, countDisjointRecords(roleID))
}

// TestItemDisjointByConfig 配置驱动: 混合模板(物品 1001 -> 15, 1002 -> 20)聚合产出 35
func (s *MakeTestSuite) TestItemDisjointByConfig() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_disj_02", 16)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{1001: 1, 1002: 1}))
	ids := bagItemIDs(roleID, 2)
	s.Require().Len(ids, 2)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 1001x15 + 1002x20 = 35 个材料, 原物品已移除
	s.Equal(35, bagItemCount(roleID, 2013000000))
	s.Equal(0, bagItemCount(roleID, 1001))
	s.Equal(0, bagItemCount(roleID, 1002))
	s.Equal(1, countDisjointRecords(roleID))
}

// TestItemDisjointNoConfig 配置驱动: 无分解配置的模板(9999)应报错且不留任何副作用
func (s *MakeTestSuite) TestItemDisjointNoConfig() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_disj_03", 17)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{9999: 1}))
	ids := bagItemIDs(roleID, 1)
	s.Require().Len(ids, 1)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	// 事务回滚: 原物品仍在、无记录、无材料产出
	s.Equal(1, bagItemCount(roleID, 9999))
	s.Equal(0, countDisjointRecords(roleID))
	s.Equal(0, bagItemCount(roleID, 2013000000))
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
