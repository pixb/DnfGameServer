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

	// 0.5 模板存在性校验(2026-09-08 第七十七轮): 配方材料/产物/随机池/保底产物均需在 t_item_template, 防脏数据扣材料
	tpls, tplErr := d.ListItemTemplates(ctx)
	if tplErr != nil {
		return nil, fmt.Errorf("failed to load item templates: %w", tplErr)
	}
	tplOK := map[int32]bool{}
	for _, t := range tpls {
		tplOK[t.ItemID] = true
	}
	for _, m := range recipe.materials {
		if !tplOK[m.Index] {
			return nil, fmt.Errorf("配方引用未知物品模板: 材料 %d", m.Index)
		}
	}
	if !tplOK[recipe.resultIndex] {
		return nil, fmt.Errorf("配方引用未知物品模板: 产物 %d", recipe.resultIndex)
	}
	for _, o := range recipe.pool {
		if !tplOK[o.ResultIndex] {
			return nil, fmt.Errorf("配方引用未知物品模板: 随机产物 %d", o.ResultIndex)
		}
	}
	if recipe.failResultIndex > 0 && !tplOK[recipe.failResultIndex] {
		return nil, fmt.Errorf("配方引用未知物品模板: 保底产物 %d", recipe.failResultIndex)
	}

	// 1. 加载背包(grid_index -> 物品)
	rows, err := tx.QueryContext(ctx, `
		SELECT id, grid_index, item_id, count, bind_type FROM bag_item
		WHERE role_id = ? AND row_status = 'NORMAL'`, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to load bag: %w", err)
	}
	type bagRow struct {
		id       uint64
		grid     int32
		itemID   int32
		count    int32
		bindType int32
	}
	bag := map[int32]*bagRow{}
	byItem := map[int32][]*bagRow{}
	for rows.Next() {
		var b bagRow
		if err := rows.Scan(&b.id, &b.grid, &b.itemID, &b.count, &b.bindType); err != nil {
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

	// 2026-09-06 第二十三轮: 产物绑定类型继承材料——取被扣减材料中的最大 bind_type
	// (0=无绑定 < 1=装备绑定 < 2=拾取绑定; 含绑定材料的合成产物保持绑定, 防"洗绑定")
	productBindType := int32(0)
	for grid := range deduct {
		if bag[grid].bindType > productBindType {
			productBindType = bag[grid].bindType
		}
	}

	// 5. 逐次判定合成结果(2026-09-06 第十八轮: 批量 count>1 逐次掷点, 而非一次掷点×数量):
	//    每次独立掷点——成功率(缺省 100 恒成功) -> 成功且池非空按权重随机产出,
	//    否则固定 result_index/result_count; 失败时 fail_result_index 非空产出保底, 否则无产出。
	//    产出按 (itemID) 聚合入包(绑定继承材料最大值, 见第二十三轮); 记录逐次写入。
	type rollOut struct {
		index   int32
		count   int32
		success bool
	}
	rolls := make([]rollOut, 0, count)
	agg := map[int32]int32{} // itemID -> 聚合数量
	anySuccess := false
	for i := int32(0); i < count; i++ {
		ok := true
		if recipe.successRate < 100 {
			ok = rand.Intn(100) < int(recipe.successRate)
		}
		outIndex := recipe.resultIndex
		outCount := recipe.resultCount
		if ok && len(recipe.pool) > 0 {
			pick := pickRecipeOutput(recipe.pool)
			outIndex = pick.ResultIndex
			outCount = pick.ResultCount
		}
		if !ok && recipe.failResultIndex > 0 {
			outIndex = recipe.failResultIndex
			outCount = recipe.failResultCount
		}
		if !ok && recipe.failResultIndex <= 0 {
			outIndex, outCount = 0, 0
		}
		rolls = append(rolls, rollOut{index: outIndex, count: outCount, success: ok})
		if outIndex > 0 && outCount > 0 {
			agg[outIndex] += outCount
		}
		if ok {
			anySuccess = true
		}
	}

	// 6. 产物入包(按 itemID 聚合, 每物品一格): 新格子 = MAX(grid_index)+1
	var maxGrid int32
	_ = tx.QueryRowContext(ctx, `
		SELECT COALESCE(MAX(grid_index), 0) FROM bag_item WHERE role_id = ?`, roleID).Scan(&maxGrid)
	gridOf := map[int32]int32{}  // itemID -> 已分配的 grid
	guidOf := map[int32]uint64{} // itemID -> bag_item 行ID
	productIDs := make([]uint64, len(rolls))
	for i, r := range rolls {
		if r.index <= 0 || r.count <= 0 {
			productIDs[i] = 0
			continue
		}
		grid, ok := gridOf[r.index]
		if !ok {
			maxGrid++
			grid = maxGrid
			gridOf[r.index] = grid
			result, err := tx.ExecContext(ctx, `
				INSERT INTO bag_item (created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes)
				VALUES (?, ?, 'NORMAL', ?, ?, ?, ?, 0, ?, 0, 0, NULL)`,
				now, now, roleID, r.index, grid, agg[r.index], productBindType)
			if err != nil {
				return nil, fmt.Errorf("failed to create product: %w", err)
			}
			id, _ := result.LastInsertId()
			guidOf[r.index] = uint64(id)
		}
		productIDs[i] = guidOf[r.index]
	}

	// 7. 合成记录(逐次写入, 每掷点一行: result_guid 指向聚合产物行, 费用按单次)
	for i, r := range rolls {
		successFlag := 0
		if r.success {
			successFlag = 1
		}
		_, err = tx.ExecContext(ctx, `
			INSERT INTO t_item_combine (role_id, target_index, material_list, count, result_guid, cost_money, success, create_time)
			VALUES (?, ?, ?, 1, ?, ?, ?, ?)
		`, roleID, recipe.resultIndex, recipe.materialJSON, productIDs[i], recipe.costMoney, successFlag, now)
		if err != nil {
			return nil, fmt.Errorf("failed to insert item combine record: %w", err)
		}
	}

	// 响应: count=1 时 Equip 指向唯一产物(兼容既有语义); Items 逐次列出
	var equip *dnfv1.EquipmentInfo
	var rewards *dnfv1.PT_CONTENTS_REWARD_INFO
	entries := make([]*store.ItemCombineEntry, 0, len(rolls))
	for i, r := range rolls {
		entries = append(entries, &store.ItemCombineEntry{
			ItemID:   r.index,
			Count:    r.count,
			Success:  r.success,
			GUID:     productIDs[i],
			BindType: productBindType,
		})
	}
	if len(rolls) == 1 && rolls[0].index > 0 && rolls[0].count > 0 {
		equip = &dnfv1.EquipmentInfo{
			Guid:   uint64(productIDs[0]),
			ItemId: uint32(rolls[0].index),
		}
		rewards = &dnfv1.PT_CONTENTS_REWARD_INFO{
			Items: &dnfv1.PT_ITEMS{
				EquipItems: []*dnfv1.EquipmentInfo{equip},
			},
			Currency: &dnfv1.PT_CURRENCY_REWARD_INFO{},
		}
	}

	err = tx.Commit()
	if err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.ItemCombineResult{
		Equip:       equip,
		Rewards:     rewards,
		RemoveItems: removeItems,
		Success:     anySuccess,
		Items:       entries,
	}, nil
}

// ItemDisjoint 物品分解
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐:写 t_item_disjoint 记录表,
// 产出分解材料(材料规则待接配置表)
// 2026-09-06 实化: guids 为背包物品ID, 移除对应物品并将分解材料(2013000000 x N*10)入背包
// 2026-09-06 第十三轮 配置驱动: 分解产出按 t_make_disjoint(item_index -> material_index/count)
// 查表, 无配置/停用报错; 多件物品产出按材料模板聚合入包; material_list 写聚合 JSON。
func (d *DB) ItemDisjoint(ctx context.Context, roleID uint64, guids []uint64) (*store.ItemDisjointResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	now := time.Now().Unix()

	// 1. 逐件校验归属 -> 查分解配置 -> 移除, 产出按 (材料模板, 绑定类型) 聚合
	materials := map[[2]int32]int32{} // [材料模板ID, 绑定类型] -> 数量
	removedGuids := make([]uint64, 0, len(guids))
	for _, g := range guids {
		var itemID, itemBind int32
		err := tx.QueryRowContext(ctx, `
			SELECT item_id, bind_type FROM bag_item WHERE id = ? AND role_id = ? AND row_status = 'NORMAL'`, g, roleID).Scan(&itemID, &itemBind)
		if err == sql.ErrNoRows {
			return nil, fmt.Errorf("背包物品不存在: %d", g)
		}
		if err != nil {
			return nil, fmt.Errorf("failed to check bag item: %w", err)
		}
		var matIndex, matCount int32
		var materialList sql.NullString
		var enabled int
		err = tx.QueryRowContext(ctx, `
			SELECT material_index, material_count, material_list, enabled FROM t_make_disjoint WHERE item_index = ?`, itemID).
			Scan(&matIndex, &matCount, &materialList, &enabled)
		if err == sql.ErrNoRows {
			return nil, fmt.Errorf("物品无分解配置: %d", itemID)
		}
		if err != nil {
			return nil, fmt.Errorf("failed to load disjoint config: %w", err)
		}
		if enabled == 0 {
			return nil, fmt.Errorf("分解配置已停用: %d", itemID)
		}
		// 2026-09-06 第十六轮: material_list(JSON 多材料+bind_type)非空优先, 空/NULL 回退旧列
		var outs []disjointOutput
		if materialList.Valid && materialList.String != "" && materialList.String != "null" {
			if err := json.Unmarshal([]byte(materialList.String), &outs); err != nil {
				return nil, fmt.Errorf("failed to parse disjoint outputs: %w", err)
			}
		} else {
			outs = []disjointOutput{{MaterialIndex: matIndex, MaterialCount: matCount}}
		}
		if len(outs) == 0 {
			return nil, fmt.Errorf("分解配置产出为空: %d", itemID)
		}
		for _, o := range outs {
			if o.MaterialIndex <= 0 || o.MaterialCount <= 0 {
				continue
			}
			// 2026-09-06 第二十四轮: 产物绑定继承材料——max(材料绑定, 配置绑定)
			// (配置指定绑定为下限; 材料绑定更高时保持, 防"分解洗绑定")
			bind := o.BindType
			if itemBind > bind {
				bind = itemBind
			}
			materials[[2]int32{o.MaterialIndex, bind}] += o.MaterialCount
		}
		if _, err := tx.ExecContext(ctx, `
			DELETE FROM bag_item WHERE id = ? AND role_id = ?`, g, roleID); err != nil {
			return nil, fmt.Errorf("failed to remove item: %w", err)
		}
		removedGuids = append(removedGuids, g)
	}

	// 2. 分解材料入包: 按 (材料模板, 绑定类型) 组合各占一格
	type aggOutput struct {
		index    int32
		count    int32
		bindType int32
	}
	var aggs []aggOutput
	for key, cnt := range materials {
		aggs = append(aggs, aggOutput{index: key[0], count: cnt, bindType: key[1]})
	}
	sort.Slice(aggs, func(i, j int) bool {
		if aggs[i].index != aggs[j].index {
			return aggs[i].index < aggs[j].index
		}
		return aggs[i].bindType < aggs[j].bindType
	})

	var maxGrid int32
	_ = tx.QueryRowContext(ctx, `
		SELECT COALESCE(MAX(grid_index), 0) FROM bag_item WHERE role_id = ?`, roleID).Scan(&maxGrid)
	rewardsCount := map[int32]int32{} // 模板ID -> 数量(响应聚合, proto 无 bind_type)
	for _, a := range aggs {
		maxGrid++
		if _, err := tx.ExecContext(ctx, `
			INSERT INTO bag_item (created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes)
			VALUES (?, ?, 'NORMAL', ?, ?, ?, ?, 0, ?, 0, 0, NULL)`,
			now, now, roleID, a.index, maxGrid, a.count, a.bindType); err != nil {
			return nil, fmt.Errorf("failed to create disjoint material: %w", err)
		}
		rewardsCount[a.index] += a.count
	}

	// 3. 分解记录(material_list 写聚合 JSON, 含 bind_type)
	type disjointRecordItem struct {
		MaterialIndex int32 `json:"material_index"`
		MaterialCount int32 `json:"material_count"`
		BindType      int32 `json:"bind_type"`
	}
	recItems := make([]disjointRecordItem, 0, len(aggs))
	for _, a := range aggs {
		recItems = append(recItems, disjointRecordItem{
			MaterialIndex: a.index,
			MaterialCount: a.count,
			BindType:      a.bindType,
		})
	}
	materialJSON, _ := json.Marshal(recItems)
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_item_disjoint (role_id, equip_guids, material_list, create_time)
		VALUES (?, ?, ?, ?)
	`, roleID, fmt.Sprintf("%v", removedGuids), string(materialJSON), now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert item disjoint record: %w", err)
	}

	// 4. 响应奖励(按模板聚合数量)
	var templates []int32
	for t := range rewardsCount {
		templates = append(templates, t)
	}
	sort.Slice(templates, func(i, j int) bool { return templates[i] < templates[j] })
	materialItems := make([]*dnfv1.StackableItem, 0, len(templates))
	for _, t := range templates {
		materialItems = append(materialItems, &dnfv1.StackableItem{
			Index: uint32(t),
			Count: uint32(rewardsCount[t]),
		})
	}

	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items: &dnfv1.PT_ITEMS{
			MaterialItems: materialItems,
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
// 2026-09-06 第十五轮: 增加成功率/随机产出池/失败保底字段
type makeRecipe struct {
	resultIndex     int32
	resultCount     int32
	costMoney       int32
	materials       []recipeMaterial
	materialJSON    string
	successRate     int32
	pool            []recipeOutput
	failResultIndex int32
	failResultCount int32
}

// recipeOutput 随机产出池条目(result_pool JSON)
type recipeOutput struct {
	ResultIndex int32 `json:"result_index"`
	ResultCount int32 `json:"result_count"`
	Weight      int32 `json:"weight"`
}

// disjointOutput 分解产出条目(t_make_disjoint.material_list JSON)
// 2026-09-06 第十六轮: 多材料产出 + 绑定类型(bind_type 缺省 0)
type disjointOutput struct {
	MaterialIndex int32 `json:"material_index"`
	MaterialCount int32 `json:"material_count"`
	BindType      int32 `json:"bind_type"`
}

// pickRecipeOutput 按权重随机选一个产出
func pickRecipeOutput(pool []recipeOutput) recipeOutput {
	total := 0
	for _, p := range pool {
		total += int(p.Weight)
	}
	if total <= 0 {
		return pool[0]
	}
	r := rand.Intn(total)
	for _, p := range pool {
		r -= int(p.Weight)
		if r < 0 {
			return p
		}
	}
	return pool[len(pool)-1]
}

// loadMakeRecipe 在事务内按 recipe_index 加载配方
func loadMakeRecipe(ctx context.Context, tx *sql.Tx, recipeIndex int32) (*makeRecipe, error) {
	var (
		resultIndex     int32
		resultCount     int32
		costMoney       int32
		materialList    string
		enabled         int
		successRate     int32
		resultPool      sql.NullString
		failResultIndex sql.NullInt64
		failResultCount sql.NullInt64
	)
	err := tx.QueryRowContext(ctx, `
		SELECT result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count
		FROM t_make_recipe WHERE recipe_index = ?`, recipeIndex).
		Scan(&resultIndex, &resultCount, &materialList, &costMoney, &enabled, &successRate, &resultPool, &failResultIndex, &failResultCount)
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
	rc := &makeRecipe{
		resultIndex:     resultIndex,
		resultCount:     resultCount,
		costMoney:       costMoney,
		materials:       mats,
		materialJSON:    materialList,
		successRate:     successRate,
		failResultIndex: int32(failResultIndex.Int64),
		failResultCount: int32(failResultCount.Int64),
	}
	if successRate <= 0 {
		rc.successRate = 0
	}
	if resultPool.Valid && resultPool.String != "" && resultPool.String != "null" {
		if err := json.Unmarshal([]byte(resultPool.String), &rc.pool); err != nil {
			return nil, fmt.Errorf("failed to parse recipe result pool: %w", err)
		}
	}
	return rc, nil
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
