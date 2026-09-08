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

// seedBagItemsWithBinds 清空角色背包并预置材料, 支持按物品模板指定 bind_type(缺省 0)
// (2026-09-06 第二十三轮: 合成绑定继承测试用; binds 缺省的物品按 0 处理)
func seedBagItemsWithBinds(roleID uint64, items map[int32]int32, binds map[int32]int32) error {
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
		bind := int32(0)
		if b, ok := binds[grid]; ok {
			bind = b
		}
		if _, err := db.Exec(`
			INSERT INTO bag_item (created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes)
			VALUES (?, ?, 'NORMAL', ?, ?, ?, ?, 0, ?, 0, 0, NULL)`,
			now, now, roleID, grid, grid, count, bind); err != nil {
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
	if err := clearRolesForOpenids("mk_comb_01", "mk_disj_01", "mk_comb_02", "mk_comb_03", "mk_disj_02", "mk_disj_03", "mk_comb_04", "mk_comb_05", "mk_disj_04", "mk_disj_05", "mk_batch_a", "mk_batch_b", "mk_bind_a", "mk_bind_b", "mk_disj_06", "mk_disj_07", "mk_exp_01", "mk_exp_02", "mk_exp_03", "mk_exp_04", "mk_name_01", "mk_name_02", "mk_more_01", "mk_more_02", "mk_more_03", "mk_tpl_01"); err != nil {
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

// TestItemDisjointMultiMaterial 多材料产出(3001: 2013000000x3无绑定 + 2013000001x2装备绑定)
// 分解 1 行: 两种材料各按配置入包, bind_type 正确, 原物品清除
func (s *MakeTestSuite) TestItemDisjointMultiMaterial() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_disj_04", 20)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{3001: 1}))
	ids := bagItemIDs(roleID, 1)
	s.Require().Len(ids, 1)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 2013000000x3(无绑定) + 2013000001x2(装备绑定)
	s.Equal(3, bagItemCount(roleID, 2013000000))
	s.Equal(0, bagItemBindType(roleID, 2013000000))
	s.Equal(2, bagItemCount(roleID, 2013000001))
	s.Equal(1, bagItemBindType(roleID, 2013000001))
	// 原物品已移除, 记录 1 条
	s.Equal(0, bagItemCount(roleID, 3001))
	s.Equal(1, countDisjointRecords(roleID))
}

// TestItemDisjointLegacyColumn 兼容回退: 3002 配置 material_list 为 NULL, 走旧列单材料产出
func (s *MakeTestSuite) TestItemDisjointLegacyColumn() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_disj_05", 21)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{3002: 1}))
	ids := bagItemIDs(roleID, 1)
	s.Require().Len(ids, 1)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
	})
	s.NoError(err)
	s.NotNil(resp)

	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 旧列 2013000000x5 生效, bind_type 默认 0
	s.Equal(5, bagItemCount(roleID, 2013000000))
	s.Equal(0, bagItemBindType(roleID, 2013000000))
	s.Equal(0, bagItemCount(roleID, 3002))
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

// TestItemDisjointBindInherit 分解产物绑定继承材料(配置 1: 物品1 -> 2013000000x10 bind0):
// 材料 物品1 bind2 -> 产物 2013000000 bind2(材料绑定最高, 防"分解洗绑定")
func (s *MakeTestSuite) TestItemDisjointBindInherit() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_disj_06", 26)
	s.Require().NoError(seedBagItemsWithBinds(roleID,
		map[int32]int32{1: 1},
		map[int32]int32{1: 2}))
	ids := bagItemIDs(roleID, 1)
	s.Require().Len(ids, 1)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal(10, bagItemCount(roleID, 2013000000))
	s.Equal(2, bagItemBindType(roleID, 2013000000))
	s.Equal(0, bagItemCount(roleID, 1))
	s.Equal(1, countDisjointRecords(roleID))
}

// TestItemDisjointBindInheritMixed 分解多材料配置+绑定继承(3001: 2013000000x3 bind0 + 2013000001x2 bind1):
// 材料 3001 bind1 -> 两产物绑定均抬升为 1(配置为下限)
func (s *MakeTestSuite) TestItemDisjointBindInheritMixed() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_disj_07", 27)
	s.Require().NoError(seedBagItemsWithBinds(roleID,
		map[int32]int32{3001: 1},
		map[int32]int32{3001: 1}))
	ids := bagItemIDs(roleID, 1)
	s.Require().Len(ids, 1)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 2013000000: max(材料1, 配置0)=1; 2013000001: max(材料1, 配置1)=1
	s.Equal(3, bagItemCount(roleID, 2013000000))
	s.Equal(1, bagItemBindType(roleID, 2013000000))
	s.Equal(2, bagItemCount(roleID, 2013000001))
	s.Equal(1, bagItemBindType(roleID, 2013000001))
	s.Equal(0, bagItemCount(roleID, 3001))
	s.Equal(1, countDisjointRecords(roleID))
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

// TestItemCombineBatchPerRoll 批量合成逐次掷点(配方1005: 50%成功1001x1/失败保底2013000000x1):
// count=5 时逐次判定——响应 items 逐条列出, 成功次数与记录/背包一一对应
func (s *MakeTestSuite) TestItemCombineBatchPerRoll() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_batch_a", 22)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 5}))

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1005,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 5},
		},
		"count": 5,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 响应 count/items 长度
	if v, ok := resp["count"].(float64); ok {
		s.Equal(float64(5), v)
	}
	items, ok := resp["items"].([]interface{})
	s.True(ok, "items should be a list")
	s.Len(items, 5)

	// 逐条校验: 成功→1001x1, 失败→保底2013000000x1; guid 非零
	successCount := 0
	for _, it := range items {
		m, ok := it.(map[string]interface{})
		s.True(ok)
		if !ok {
			continue
		}
		if succ, ok := m["success"].(bool); ok && succ {
			successCount++
			s.Equal(float64(1001), m["itemId"])
		} else {
			s.Equal(float64(2013000000), m["itemId"])
		}
		s.Equal(float64(1), m["count"])
		if g, ok := m["guid"].(float64); ok {
			s.True(g > 0)
		}
	}

	// 背包与记录一致: 材料清空, 产物=成功数, 保底=5-成功数
	s.Equal(0, bagItemCount(roleID, 2001))
	s.Equal(successCount, bagItemCount(roleID, 1001))
	s.Equal(5-successCount, bagItemCount(roleID, 2013000000))
	s.Equal(5, countCombineRecords(roleID))
	s.Equal(successCount, countCombineBySuccess(roleID, 1))
	s.Equal(5-successCount, countCombineBySuccess(roleID, 0))
}

// TestItemCombineBatchCap 批量上限: count 超过 99 截断为 99, 材料按 99 份扣减
func (s *MakeTestSuite) TestItemCombineBatchCap() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_batch_b", 23)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 200}))

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1004, // 100% 成功随机池 60%:1001/40%:1002
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 99}, // 按截断后的 99 份给材料
		},
		"count": 200,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	if v, ok := resp["count"].(float64); ok {
		s.Equal(float64(99), v)
	}
	items, ok := resp["items"].([]interface{})
	s.True(ok, "items should be a list")
	s.Len(items, 99)
	// 全部成功(池产出 1001/1002 之一), 材料按 99 扣
	s.Equal(101, bagItemCount(roleID, 2001))
	s.Equal(99, bagItemCount(roleID, 1001)+bagItemCount(roleID, 1002))
	s.Equal(99, countCombineBySuccess(roleID, 1))
}

// TestItemCombineBindInherit 产物绑定继承材料最大值(recipe 1001: 2001x1+2002x1 -> 1001x1):
// 材料 2001 bind1 + 2002 bind0 -> 产物 1001 bind1; 响应 bindType 同步返回
func (s *MakeTestSuite) TestItemCombineBindInherit() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_bind_a", 24)
	s.Require().NoError(seedBagItemsWithBinds(roleID,
		map[int32]int32{2001: 1, 2002: 1},
		map[int32]int32{2001: 1}))

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
	// 响应 items 逐条带 bindType
	items, ok := resp["items"].([]interface{})
	s.True(ok)
	s.Len(items, 1)
	if entry, ok := items[0].(map[string]interface{}); ok {
		if bt, ok := entry["bindType"].(float64); ok {
			s.Equal(float64(1), bt)
		}
	}
	// 背包产物 bind_type=1(继承材料最大值), 材料消耗干净
	s.Equal(1, bagItemCount(roleID, 1001))
	s.Equal(1, bagItemBindType(roleID, 1001))
	s.Equal(0, bagItemCount(roleID, 2001))
	s.Equal(0, bagItemCount(roleID, 2002))
}

// TestItemCombineBindInheritHighest 拾取绑定(2)材料优先级最高(recipe 1002: 2001x3+2002x3 -> 1002x1, 500金):
// 材料 2001 bind2 + 2002 bind1 -> 产物 1002 bind2
func (s *MakeTestSuite) TestItemCombineBindInheritHighest() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_bind_b", 25)
	s.Require().NoError(setGold(roleID, 1000))
	s.Require().NoError(seedBagItemsWithBinds(roleID,
		map[int32]int32{2001: 3, 2002: 3},
		map[int32]int32{2001: 2, 2002: 1}))

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
	s.Equal(1, bagItemCount(roleID, 1002))
	s.Equal(2, bagItemBindType(roleID, 1002))
	// 费用按次扣减 500, 余额 500
	s.Equal(int64(500), getGold(roleID))
	s.Equal(0, bagItemCount(roleID, 2001))
	s.Equal(0, bagItemCount(roleID, 2002))
}

// TestMakeRecipeExpand 合成配方配置表深化(2026-09-07 第七十一轮, 新种子 1006-1009):
// 1006 批量(2001x3+100 金币 -> 1002x1) / 1007 产物升级(1001x2+2001x1+50 -> 1002x1) /
// 1008 随机池(1001x1+2001x2 -> 60%:1002x1 / 40%:2001x2) / 1009 大额批量(2001x10+500 -> 1001x5)
func (s *MakeTestSuite) TestMakeRecipeExpand() {
	// 1006: 批量合成 + 费用扣减
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_exp_01", 20)
	s.Require().NoError(setGold(roleID, 1000))
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 3}))

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1006,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 3},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal(0, bagItemCount(roleID, 2001), "材料 2001x3 应扣净")
	s.Equal(1, bagItemCount(roleID, 1002), "产物 1002x1 应入包")
	s.Equal(int64(900), getGold(roleID), "费用 100 应扣减(1000->900)")

	// 1007: 产物升级(两材料配方, 费用 50)
	roleID7 := s.loginAndSelectCharacterWithUserAndSlot("mk_exp_02", 21)
	s.Require().NoError(setGold(roleID7, 1000))
	s.Require().NoError(seedBagItems(roleID7, map[int32]int32{1001: 2, 2001: 1}))
	resp7, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1007,
		"material_items": []map[string]interface{}{
			{"index": 1001, "count": 2},
			{"index": 2001, "count": 1},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp7)
	if errVal, ok := resp7["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal(int64(950), getGold(roleID7), "费用 50 应扣减(1000->950)")
	s.Equal(0, bagItemCount(roleID7, 1001), "材料 1001x2 应扣净")
	s.Equal(0, bagItemCount(roleID7, 2001), "材料 2001x1 应扣净")
	s.Equal(1, bagItemCount(roleID7, 1002), "升级产物 1002x1 应入包")

	// 1009: 大额批量(产物 x5)
	roleID9 := s.loginAndSelectCharacterWithUserAndSlot("mk_exp_03", 22)
	s.Require().NoError(setGold(roleID9, 1000))
	s.Require().NoError(seedBagItems(roleID9, map[int32]int32{2001: 10}))
	resp9, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1009,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 10},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp9)
	if errVal, ok := resp9["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal(0, bagItemCount(roleID9, 2001), "材料 2001x10 应扣净")
	s.Equal(5, bagItemCount(roleID9, 1001), "产物 1001x5 应入包")
	s.Equal(int64(500), getGold(roleID9), "费用 500 应扣减(1000->500)")

	// 1008: 随机产出池(产物为 1002x1 或 2001x2 之一)
	roleID8 := s.loginAndSelectCharacterWithUserAndSlot("mk_exp_04", 23)
	s.Require().NoError(seedBagItems(roleID8, map[int32]int32{1001: 1, 2001: 2}))
	resp8, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1008,
		"material_items": []map[string]interface{}{
			{"index": 1001, "count": 1},
			{"index": 2001, "count": 2},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp8)
	if errVal, ok := resp8["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	itemID, _ := resp8["itemId"].(float64)
	s.True(itemID == 1002 || itemID == 2001, fmt.Sprintf("池配方产物应为 1002 或 2001, got %v", resp8["itemId"]))
	if itemID == 1002 {
		s.Equal(1, bagItemCount(roleID8, 1002))
	} else {
		s.Equal(2, bagItemCount(roleID8, 2001))
	}
}

// TestMakeRecipeMoreItems 配方引用更多模板物品(2026-09-08 第七十六轮, 新模板 2004/1004 + 配方 1010-1012):
// 1010 秘银锻造(2004x3+500金 -> 1004屠龙巨剑) / 1011 材料进阶(3001x1+2003x2 -> 2004秘银矿石) /
// 1012 武器升级链(1002x2+2002x3+300金 -> 1003x1)
func (s *MakeTestSuite) TestMakeRecipeMoreItems() {
	// 1010: 秘银锻造(产物带名称)
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_more_01", 26)
	s.Require().NoError(setGold(roleID, 1000))
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2004: 3}))
	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1010,
		"material_items": []map[string]interface{}{
			{"index": 2004, "count": 3},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal("屠龙巨剑", resp["itemName"], "1010 产物应为屠龙巨剑")
	s.Equal(0, bagItemCount(roleID, 2004), "材料 2004x3 应扣净")
	s.Equal(1, bagItemCount(roleID, 1004), "产物 1004x1 应入包")
	s.Equal(int64(500), getGold(roleID), "费用 500 应扣减(1000->500)")

	// 1011: 材料进阶(无费用, 双材料)
	roleID1 := s.loginAndSelectCharacterWithUserAndSlot("mk_more_02", 27)
	s.Require().NoError(seedBagItems(roleID1, map[int32]int32{3001: 1, 2003: 2}))
	resp1, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1011,
		"material_items": []map[string]interface{}{
			{"index": 3001, "count": 1},
			{"index": 2003, "count": 2},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp1)
	if errVal, ok := resp1["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal("秘银矿石", resp1["itemName"], "1011 产物应为秘银矿石")
	s.Equal(0, bagItemCount(roleID1, 3001), "材料 3001x1 应扣净")
	s.Equal(0, bagItemCount(roleID1, 2003), "材料 2003x2 应扣净")
	s.Equal(1, bagItemCount(roleID1, 2004), "产物 2004x1 应入包")

	// 1012: 武器升级链(费用 300)
	roleID2 := s.loginAndSelectCharacterWithUserAndSlot("mk_more_03", 28)
	s.Require().NoError(setGold(roleID2, 1000))
	s.Require().NoError(seedBagItems(roleID2, map[int32]int32{1002: 2, 2002: 3}))
	resp2, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1012,
		"material_items": []map[string]interface{}{
			{"index": 1002, "count": 2},
			{"index": 2002, "count": 3},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp2)
	if errVal, ok := resp2["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal("秘银巨剑", resp2["itemName"], "1012 产物应为秘银巨剑")
	s.Equal(0, bagItemCount(roleID2, 1002), "材料 1002x2 应扣净")
	s.Equal(0, bagItemCount(roleID2, 2002), "材料 2002x3 应扣净")
	s.Equal(1, bagItemCount(roleID2, 1003), "产物 1003x1 应入包")
	s.Equal(int64(700), getGold(roleID2), "费用 300 应扣减(1000->700)")
}

// TestCombineRejectsUnknownTemplate 配方引用模板表做存在性校验(2026-09-08 第七十七轮):
// 自包含直插脏配方(2001x1 -> 999999 未知模板), 合成应报错且材料不扣, 结束后删除
func (s *MakeTestSuite) TestCombineRejectsUnknownTemplate() {
	s.Require().NoError(upsertDirtyRecipe(1013))
	defer func() {
		s.NoError(dropDirtyRecipe(1013))
	}()

	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_tpl_01", 29)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 1}))
	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1013,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 1},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.NotEqual(float64(0), errVal, "脏配方应报错")
	}
	msg, _ := resp["message"].(string)
	s.Contains(msg, "配方引用未知物品模板", "应提示模板不存在")
	s.Equal(1, bagItemCount(roleID, 2001), "材料 2001 不应被扣")
}

// TestItemCombineResponseName 合成响应带物品名称(2026-09-08 第七十四轮, 物品模板体系消费)
// recipe 1006: 2001x3+100金 -> 1002x1; 响应 items/顶层带 name(itemName)
func (s *MakeTestSuite) TestItemCombineResponseName() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_name_01", 24)
	s.Require().NoError(setGold(roleID, 1000))
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 3}))

	resp, err := s.Client.Post("/api/v1/make/item/combine", map[string]interface{}{
		"index": 1006,
		"material_items": []map[string]interface{}{
			{"index": 2001, "count": 3},
		},
		"count": 1,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 顶层产物名称
	s.Equal("精钢长剑", resp["itemName"])
	// items 每项带 name
	items, ok := resp["items"].([]interface{})
	s.True(ok)
	s.Len(items, 1)
	first, _ := items[0].(map[string]interface{})
	s.Equal("精钢长剑", first["name"])
	s.Equal(float64(1002), first["itemId"])
}

// TestItemDisjointResponseItems 分解响应带产物明细与名称(2026-09-08 第七十四轮)
// 3001 分解: 2013000000x3(破损剑刃) + 2013000001x2(破损护甲)
func (s *MakeTestSuite) TestItemDisjointResponseItems() {
	roleID := s.loginAndSelectCharacterWithUserAndSlot("mk_name_02", 25)
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{3001: 1}))
	ids := bagItemIDs(roleID, 1)
	s.Require().Len(ids, 1)

	resp, err := s.Client.Post("/api/v1/make/item/disjoint", map[string]interface{}{
		"guids": ids,
	})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	items, ok := resp["items"].([]interface{})
	s.True(ok, "响应应带 items 产物明细")
	s.Len(items, 2)
	byID := map[float64]map[string]interface{}{}
	for _, raw := range items {
		it, _ := raw.(map[string]interface{})
		byID[it["itemId"].(float64)] = it
	}
	s.Equal("破损剑刃", byID[2013000000]["name"])
	s.Equal(float64(3), byID[2013000000]["count"])
	s.Equal("破损护甲", byID[2013000001]["name"])
	s.Equal(float64(2), byID[2013000001]["count"])
	// 产物确实入包
	s.Equal(3, bagItemCount(roleID, 2013000000))
	s.Equal(2, bagItemCount(roleID, 2013000001))
}
