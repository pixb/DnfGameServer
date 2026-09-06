package mysql

import (
	"context"
	"database/sql"
	"encoding/json"
	"fmt"
	"math/rand"
	"sort"
	"time"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 制作相关 ====================

// EmblemUpgrade 徽章升级
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐(货币表为 role_currency.gold)
func (d *DB) EmblemUpgrade(ctx context.Context, roleID uint64, index int32, tryCount int32, talisman int32) (*store.EmblemUpgradeResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	level := int(index % 1000)
	if level == 0 {
		level = 10
	}

	moneyCost := getEmblemCost(level)
	successCount := int32(0)

	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items:    &dnfv1.PT_ITEMS{},
		Currency: &dnfv1.PT_CURRENCY_REWARD_INFO{},
	}

	removeItems := &dnfv1.PT_REMOVEITEMS{
		ConsumeItems:  []*dnfv1.ConsumeItems{},
		MaterialItems: []*dnfv1.StackableItem{},
		EmblemItems:   []*dnfv1.StackableItem{},
	}

	if talisman > 0 {
		successCount = tryCount
	} else {
		for i := int32(0); i < tryCount; i++ {
			if isEmblemSuccess(level) {
				successCount++
			}
		}
	}

	now := time.Now().Unix()
	emblemNew := &dnfv1.StackableItem{
		Index: uint32(index + 1),
		Count: uint32(successCount),
	}
	emblemOld := &dnfv1.StackableItem{
		Index: uint32(index),
		Count: uint32(tryCount - successCount),
	}

	rewards.Items.EmblemItems = []*dnfv1.StackableItem{emblemNew, emblemOld}
	removeItems.EmblemItems = []*dnfv1.StackableItem{
		{Index: uint32(index), Count: uint32(tryCount)},
	}

	_, err = tx.ExecContext(ctx, "UPDATE role_currency SET gold = gold - ? WHERE role_id = ?", int64(moneyCost)*int64(tryCount), roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT gold FROM role_currency WHERE role_id = ?", roleID).Scan(&money)
	if err != nil {
		return nil, fmt.Errorf("failed to query money: %w", err)
	}

	rewards.Currency.Currency = []*dnfv1.MoneyItem{
		{Count: money},
	}

	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_emblem_upgrade (role_id, emblem_index, level, try_count, success_count, cost_money, cost_talisman, create_time)
		VALUES (?, ?, ?, ?, ?, ?, ?, ?)
	`, roleID, index, level, tryCount, successCount, int64(moneyCost)*int64(tryCount), talisman, now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert emblem upgrade record: %w", err)
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.EmblemUpgradeResult{
		SuccessCount: successCount,
		Rewards:      rewards,
		RemoveItems:  removeItems,
	}, nil
}

// EmblemUpgradeQuick 徽章快速升级
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐
func (d *DB) EmblemUpgradeQuick(ctx context.Context, roleID uint64, source []*dnfv1.IndexCount, target int32) (*store.EmblemUpgradeQuickResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items:    &dnfv1.PT_ITEMS{},
		Currency: &dnfv1.PT_CURRENCY_REWARD_INFO{},
	}

	removeItems := &dnfv1.PT_REMOVEITEMS{
		MaterialItems: []*dnfv1.StackableItem{},
		EmblemItems:   []*dnfv1.StackableItem{},
	}

	emblemNew := &dnfv1.StackableItem{
		Index: uint32(target),
		Count: 1,
	}
	rewards.Items.EmblemItems = []*dnfv1.StackableItem{emblemNew}

	for _, src := range source {
		removeItems.EmblemItems = append(removeItems.EmblemItems, &dnfv1.StackableItem{
			Index: uint32(src.Index),
			Count: uint32(src.Count),
		})
	}

	_, err = tx.ExecContext(ctx, "UPDATE role_currency SET gold = gold - 1000 WHERE role_id = ?", roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT gold FROM role_currency WHERE role_id = ?", roleID).Scan(&money)
	if err != nil {
		return nil, fmt.Errorf("failed to query money: %w", err)
	}

	rewards.Currency.Currency = []*dnfv1.MoneyItem{
		{Count: money},
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.EmblemUpgradeQuickResult{
		Rewards:     rewards,
		RemoveItems: removeItems,
	}, nil
}

// AvatarCompose 时装合成
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐
func (d *DB) AvatarCompose(ctx context.Context, roleID uint64, guids []uint64) (*store.AvatarComposeResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items:    &dnfv1.PT_ITEMS{},
		Currency: &dnfv1.PT_CURRENCY_REWARD_INFO{},
	}

	removeItems := &dnfv1.PT_REMOVEITEMS{
		ConsumeItems: []*dnfv1.ConsumeItems{},
	}

	avatarNew := &dnfv1.AvatarItem{
		Index: 1001,
		Guid:  guids[0],
	}
	rewards.Items.AvatarItems = []*dnfv1.AvatarItem{avatarNew}

	_, err = tx.ExecContext(ctx, "UPDATE role_currency SET gold = gold - 4000 WHERE role_id = ?", roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT gold FROM role_currency WHERE role_id = ?", roleID).Scan(&money)
	if err != nil {
		return nil, fmt.Errorf("failed to query money: %w", err)
	}

	rewards.Currency.Currency = []*dnfv1.MoneyItem{
		{Count: money},
	}

	now := time.Now().Unix()
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_avatar_compose (role_id, avatar_guids, result_index, result_guid, cost_money, create_time)
		VALUES (?, ?, ?, ?, ?, ?)
	`, roleID, fmt.Sprintf("%v", guids), avatarNew.Index, avatarNew.Guid, 4000, now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert avatar compose record: %w", err)
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.AvatarComposeResult{
		Rewards:     rewards,
		RemoveItems: removeItems,
	}, nil
}

// GetProductionInfo 获取物品制作信息
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐
func (d *DB) GetProductionInfo(ctx context.Context, roleID uint64, slotType int32) (*store.ProductionInfoResult, error) {
	if slotType != 1 {
		return nil, fmt.Errorf("invalid slot type: %d", slotType)
	}

	infos := []*dnfv1.PT_ITEM_PRODUCTION_SLOT{
		{SlotIndex: 1, UsableCount: 100},
		{SlotIndex: 2, UsableCount: 100},
		{SlotIndex: 3, UsableCount: 100},
		{SlotIndex: -1, UsableCount: 100},
	}

	return &store.ProductionInfoResult{
		Infos: infos,
	}, nil
}

// ProductionRegister 物品制作注册
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐
func (d *DB) ProductionRegister(ctx context.Context, roleID uint64, slotIndex int32, recipeIndex int32, count int32) (*store.ProductionRegisterResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items:    &dnfv1.PT_ITEMS{},
		Currency: &dnfv1.PT_CURRENCY_REWARD_INFO{},
	}

	removeItems := &dnfv1.PT_REMOVEITEMS{
		MaterialItems: []*dnfv1.StackableItem{},
	}

	materialItems := []*dnfv1.ConsumeItems{
		{ItemIndex: 2001, Count: count},
		{ItemIndex: 2002, Count: count},
	}

	price := int32(1000)
	_, err = tx.ExecContext(ctx, "UPDATE role_currency SET gold = gold - ? WHERE role_id = ?", price*count, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT gold FROM role_currency WHERE role_id = ?", roleID).Scan(&money)
	if err != nil {
		return nil, fmt.Errorf("failed to query money: %w", err)
	}

	rewards.Currency.Currency = []*dnfv1.MoneyItem{
		{Count: money},
	}

	now := time.Now().Unix()
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_item_production (role_id, slot_index, recipe_index, count, result_index, result_count, cost_money, create_time)
		VALUES (?, ?, ?, ?, ?, ?, ?, ?)
	`, roleID, slotIndex, recipeIndex, count, 1001, count, price*count, now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert item production record: %w", err)
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.ProductionRegisterResult{
		Rewards:       rewards,
		RemoveItems:   removeItems,
		MaterialItems: materialItems,
	}, nil
}

// ItemCombine 物品合成
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐:写 t_item_combine 记录表,
// 产出装备 GUID 返回(合成配方/材料扣减待接配置表)
// 2026-09-06 实化: 材料按背包格子校验并扣减, 产物(target_index 作物品模板ID)入背包
// 2026-09-06 第十二轮 配方驱动: index 为配方索引(recipe_index), 按 t_make_recipe 校验材料
// (材料模板与数量严格一致)、扣金币(cost_money)、产物按 result_index/result_count 入包;
// materialItems 为客户端指定的背包格子(可为空, 空则按配方模板自动从背包解析)。
func (d *DB) ItemCombine(ctx context.Context, roleID uint64, index int32, materialItems []*dnfv1.MaterialItem, count int32) (*store.ItemCombineResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	now := time.Now().Unix()

	// 0. 加载配方(不存在/停用直接报错)
	recipe, err := loadMakeRecipe(ctx, tx, index)
	if err != nil {
		return nil, err
	}

	// 1. 加载背包(grid_index -> 物品)
	rows, err := tx.QueryContext(ctx, `
		SELECT id, grid_index, item_id, count FROM bag_item
		WHERE role_id = ? AND row_status = 'NORMAL'`, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to load bag: %w", err)
	}
	type bagRow struct {
		id     uint64
		grid   int32
		itemID int32
		count  int32
	}
	bag := map[int32]*bagRow{}
	byItem := map[int32][]*bagRow{}
	for rows.Next() {
		var b bagRow
		if err := rows.Scan(&b.id, &b.grid, &b.itemID, &b.count); err != nil {
			rows.Close()
			return nil, fmt.Errorf("failed to scan bag item: %w", err)
		}
		bag[b.grid] = &b
		byItem[b.itemID] = append(byItem[b.itemID], &b)
	}
	rows.Close()
	if err := rows.Err(); err != nil {
		return nil, fmt.Errorf("failed to iterate bag: %w", err)
	}
	for _, list := range byItem {
		sort.Slice(list, func(i, j int) bool { return list[i].grid < list[j].grid })
	}

	// 2. 确定扣减计划(grid -> count): 客户端指定格子 或 按配方模板自动解析
	deduct := map[int32]int32{}
	if len(materialItems) > 0 {
		consumed := map[int32]int32{} // 材料模板ID -> 消耗数
		for _, mat := range materialItems {
			b, ok := bag[mat.Index]
			if !ok {
				return nil, fmt.Errorf("材料不足: 格子 %d 不存在", mat.Index)
			}
			if b.count < mat.Count {
				return nil, fmt.Errorf("材料不足: 格子 %d 需要 %d 个", mat.Index, mat.Count)
			}
			consumed[b.itemID] += mat.Count
			deduct[b.grid] += mat.Count
		}
		need := map[int32]int32{}
		for _, m := range recipe.materials {
			need[m.Index] = m.Count * count
		}
		if len(consumed) != len(need) {
			return nil, fmt.Errorf("材料与配方不符: 材料种类不一致")
		}
		for tpl, n := range need {
			if consumed[tpl] != n {
				return nil, fmt.Errorf("材料与配方不符: 模板 %d 需要 %d 个, 实际 %d 个", tpl, n, consumed[tpl])
			}
		}
	} else {
		for _, m := range recipe.materials {
			need := m.Count * count
			for _, b := range byItem[m.Index] {
				if need <= 0 {
					break
				}
				take := b.count
				if take > need {
					take = need
				}
				deduct[b.grid] += take
				need -= take
			}
			if need > 0 {
				return nil, fmt.Errorf("材料不足: 模板 %d 缺 %d 个", m.Index, need)
			}
		}
	}

	// 3. 扣减材料(减至 0 删除格子, 否则更新数量)
	removeItems := &dnfv1.PT_REMOVEITEMS{
		MaterialItems: []*dnfv1.StackableItem{},
	}
	for grid, c := range deduct {
		b := bag[grid]
		if b.count == c {
			if _, err := tx.ExecContext(ctx, `
				DELETE FROM bag_item WHERE id = ? AND role_id = ?`, b.id, roleID); err != nil {
				return nil, fmt.Errorf("failed to remove material: %w", err)
			}
		} else {
			if _, err := tx.ExecContext(ctx, `
				UPDATE bag_item SET count = ?, updated_at = ? WHERE id = ? AND role_id = ?`,
				b.count-c, now, b.id, roleID); err != nil {
				return nil, fmt.Errorf("failed to deduct material: %w", err)
			}
		}
		removeItems.MaterialItems = append(removeItems.MaterialItems, &dnfv1.StackableItem{
			Index: uint32(b.itemID),
			Count: uint32(c),
		})
	}

	// 4. 合成费用(金币): 余额不足报错, 否则扣减
	fee := recipe.costMoney * count
	if fee > 0 {
		var gold int64
		err := tx.QueryRowContext(ctx, "SELECT gold FROM role_currency WHERE role_id = ?", roleID).Scan(&gold)
		if err != nil || gold < int64(fee) {
			return nil, fmt.Errorf("金币不足: 需要 %d", fee)
		}
		if _, err := tx.ExecContext(ctx, "UPDATE role_currency SET gold = gold - ? WHERE role_id = ?", int64(fee), roleID); err != nil {
			return nil, fmt.Errorf("failed to deduct money: %w", err)
		}
	}

	// 5. 产物入包: 按配方 result_index/result_count, 新格子 = MAX(grid_index)+1
	var maxGrid int32
	_ = tx.QueryRowContext(ctx, `
		SELECT COALESCE(MAX(grid_index), 0) FROM bag_item WHERE role_id = ?`, roleID).Scan(&maxGrid)
	newGrid := maxGrid + 1
	resultCount := recipe.resultCount * count
	result, err := tx.ExecContext(ctx, `
		INSERT INTO bag_item (created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes)
		VALUES (?, ?, 'NORMAL', ?, ?, ?, ?, 0, 0, 0, 0, NULL)`,
		now, now, roleID, recipe.resultIndex, newGrid, resultCount)
	if err != nil {
		return nil, fmt.Errorf("failed to create product: %w", err)
	}
	productID, _ := result.LastInsertId()

	// 6. 合成记录
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_item_combine (role_id, target_index, material_list, count, result_guid, cost_money, create_time)
		VALUES (?, ?, ?, ?, ?, ?, ?)
	`, roleID, recipe.resultIndex, recipe.materialJSON, count, productID, fee, now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert item combine record: %w", err)
	}

	equip := &dnfv1.EquipmentInfo{
		Guid:   uint64(productID),
		ItemId: uint32(recipe.resultIndex),
	}
	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items: &dnfv1.PT_ITEMS{
			EquipItems: []*dnfv1.EquipmentInfo{equip},
		},
		Currency: &dnfv1.PT_CURRENCY_REWARD_INFO{},
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.ItemCombineResult{
		Equip:       equip,
		Rewards:     rewards,
		RemoveItems: removeItems,
	}, nil
}

// ItemDisjoint 物品分解
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐:写 t_item_disjoint 记录表,
// 产出分解材料(材料规则待接配置表)
// 2026-09-06 实化: guids 为背包物品ID, 移除对应物品并将分解材料(2013000000 x N*10)入背包
func (d *DB) ItemDisjoint(ctx context.Context, roleID uint64, guids []uint64) (*store.ItemDisjointResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	now := time.Now().Unix()

	// 1. 校验并移除背包物品
	removed := 0
	for _, g := range guids {
		var exist int
		err := tx.QueryRowContext(ctx, `
			SELECT COUNT(*) FROM bag_item WHERE id = ? AND role_id = ? AND row_status = 'NORMAL'`, g, roleID).Scan(&exist)
		if err != nil {
			return nil, fmt.Errorf("failed to check bag item: %w", err)
		}
		if exist == 0 {
			return nil, fmt.Errorf("背包物品不存在: %d", g)
		}
		if _, err := tx.ExecContext(ctx, `
			DELETE FROM bag_item WHERE id = ? AND role_id = ?`, g, roleID); err != nil {
			return nil, fmt.Errorf("failed to remove item: %w", err)
		}
		removed++
	}

	// 2. 分解材料入包
	materialCount := int32(removed) * 10
	var maxGrid int32
	_ = tx.QueryRowContext(ctx, `
		SELECT COALESCE(MAX(grid_index), 0) FROM bag_item WHERE role_id = ?`, roleID).Scan(&maxGrid)
	if materialCount > 0 {
		_, err = tx.ExecContext(ctx, `
			INSERT INTO bag_item (created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes)
			VALUES (?, ?, 'NORMAL', ?, 2013000000, ?, ?, 0, 0, 0, 0, NULL)`,
			now, now, roleID, maxGrid+1, materialCount)
		if err != nil {
			return nil, fmt.Errorf("failed to create disjoint material: %w", err)
		}
	}

	// 3. 分解记录
	material := &dnfv1.StackableItem{
		Index: 2013000000,
		Count: uint32(materialCount),
	}
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_item_disjoint (role_id, equip_guids, material_list, create_time)
		VALUES (?, ?, ?, ?)
	`, roleID, fmt.Sprintf("%v", guids), fmt.Sprintf("%v", material), now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert item disjoint record: %w", err)
	}

	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items: &dnfv1.PT_ITEMS{
			MaterialItems: []*dnfv1.StackableItem{material},
		},
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.ItemDisjointResult{
		Rewards: rewards,
	}, nil
}

// CardCompose 卡片合成
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐(成功概率按稀有度/数量,随机产出)
func (d *DB) CardCompose(ctx context.Context, roleID uint64, userCardList []*dnfv1.CardCompose) (*store.CardComposeResult, error) {
	if len(userCardList) == 0 {
		return nil, fmt.Errorf("empty card list")
	}

	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	totalCount := int32(0)
	for _, card := range userCardList {
		totalCount += card.Count
	}

	rarity := int32(0)
	if len(userCardList) > 0 {
		rarity = userCardList[0].Index / 1000
	}

	successRate := int32(0)
	switch rarity {
	case 0:
		switch totalCount {
		case 2:
			successRate = 20
		case 3:
			successRate = 40
		case 4:
			successRate = 80
		}
	case 1:
		switch totalCount {
		case 2:
			successRate = 4
		case 3:
			successRate = 8
		case 4:
			successRate = 16
		}
	default:
		switch totalCount {
		case 2:
			successRate = 2
		case 3:
			successRate = 3
		case 4:
			successRate = 4
		}
	}

	r := rand.Intn(100)
	resultIndex := userCardList[0].Index
	if r < int(successRate) {
		resultIndex = (rarity+1)*1000 + 1
	}

	costMoney := int32(10000 * totalCount)
	_, err = tx.ExecContext(ctx, "UPDATE role_currency SET gold = gold - ? WHERE role_id = ?", costMoney, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT gold FROM role_currency WHERE role_id = ?", roleID).Scan(&money)
	if err != nil {
		return nil, fmt.Errorf("failed to query money: %w", err)
	}

	now := time.Now().Unix()
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_card_compose (role_id, card_list, result_index, result_count, cost_money, create_time)
		VALUES (?, ?, ?, ?, ?, ?)
	`, roleID, fmt.Sprintf("%v", userCardList), resultIndex, 1, costMoney, now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert card compose record: %w", err)
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.CardComposeResult{
		Card: []*dnfv1.CardCompose{
			{Index: resultIndex, Count: 1},
		},
		Currency: []*dnfv1.MoneyItem{
			{Count: money},
		},
	}, nil
}

// WardrobeSetSlot 衣柜槽位设置
func (d *DB) WardrobeSetSlot(ctx context.Context, roleID uint64) error {
	return nil
}

// ==================== 合成配方 ====================

// recipeMaterial 配方材料项(JSON: [{"index":2001,"count":1}])
type recipeMaterial struct {
	Index int32 `json:"index"`
	Count int32 `json:"count"`
}

// makeRecipe 合成配方(取自 t_make_recipe)
type makeRecipe struct {
	resultIndex  int32
	resultCount  int32
	costMoney    int32
	materials    []recipeMaterial
	materialJSON string
}

// loadMakeRecipe 在事务内按 recipe_index 加载配方
func loadMakeRecipe(ctx context.Context, tx *sql.Tx, recipeIndex int32) (*makeRecipe, error) {
	var (
		resultIndex  int32
		resultCount  int32
		costMoney    int32
		materialList string
		enabled      int
	)
	err := tx.QueryRowContext(ctx, `
		SELECT result_index, result_count, material_list, cost_money, enabled
		FROM t_make_recipe WHERE recipe_index = ?`, recipeIndex).
		Scan(&resultIndex, &resultCount, &materialList, &costMoney, &enabled)
	if err == sql.ErrNoRows {
		return nil, fmt.Errorf("配方不存在: %d", recipeIndex)
	}
	if err != nil {
		return nil, fmt.Errorf("failed to load recipe: %w", err)
	}
	if enabled == 0 {
		return nil, fmt.Errorf("配方已停用: %d", recipeIndex)
	}
	var mats []recipeMaterial
	if err := json.Unmarshal([]byte(materialList), &mats); err != nil {
		return nil, fmt.Errorf("failed to parse recipe materials: %w", err)
	}
	return &makeRecipe{
		resultIndex:  resultIndex,
		resultCount:  resultCount,
		costMoney:    costMoney,
		materials:    mats,
		materialJSON: materialList,
	}, nil
}

// getEmblemCost 徽章升级金币消耗(按等级)
func getEmblemCost(level int) int {
	switch level {
	case 1:
		return 1000
	case 2:
		return 1500
	case 3:
		return 2000
	case 4:
		return 3000
	case 5:
		return 4000
	case 6:
		return 5000
	case 7:
		return 10000
	case 8:
		return 15000
	case 9:
		return 30000
	default:
		return 0
	}
}

// isEmblemSuccess 徽章升级成功率(按等级)
func isEmblemSuccess(level int) bool {
	r := rand.Intn(100)
	switch level {
	case 1, 2, 3, 4:
		return true
	case 5:
		return r <= 70
	case 6:
		return r <= 60
	case 7:
		return r <= 50
	case 8:
		return r <= 40
	case 9:
		return r <= 30
	default:
		return false
	}
}
