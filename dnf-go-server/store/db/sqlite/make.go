package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"math/rand"
	"time"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 制作相关 ====================

// EmblemUpgrade 徽章升级
func (d *DB) EmblemUpgrade(ctx context.Context, roleID uint64, index int32, tryCount int32, talisman int32) (*store.EmblemUpgradeResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	var level int
	err = tx.QueryRowContext(ctx, "SELECT level FROM t_consume_config WHERE item_id = ?", index).Scan(&level)
	if err == sql.ErrNoRows {
		return nil, fmt.Errorf("emblem not found")
	}
	if err != nil {
		return nil, fmt.Errorf("failed to query emblem: %w", err)
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

	_, err = tx.ExecContext(ctx, "UPDATE t_role_currency SET money = money - ? WHERE role_id = ?", int64(moneyCost)*int64(tryCount), roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT money FROM t_role_currency WHERE role_id = ?", roleID).Scan(&money)
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

	_, err = tx.ExecContext(ctx, "UPDATE t_role_currency SET money = money - 1000 WHERE role_id = ?", roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT money FROM t_role_currency WHERE role_id = ?", roleID).Scan(&money)
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

	_, err = tx.ExecContext(ctx, "UPDATE t_role_currency SET money = money - 4000 WHERE role_id = ?", roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT money FROM t_role_currency WHERE role_id = ?", roleID).Scan(&money)
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
	_, err = tx.ExecContext(ctx, "UPDATE t_role_currency SET money = money - ? WHERE role_id = ?", price*count, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT money FROM t_role_currency WHERE role_id = ?", roleID).Scan(&money)
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
func (d *DB) ItemCombine(ctx context.Context, roleID uint64, index int32, materialItems []*dnfv1.MaterialItem, count int32) (*store.ItemCombineResult, error) {
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

	equip := &dnfv1.EquipmentInfo{
		Guid: uint64(time.Now().UnixNano()),
	}
	rewards.Items.EquipItems = []*dnfv1.EquipmentInfo{equip}

	for _, mat := range materialItems {
		removeItems.MaterialItems = append(removeItems.MaterialItems, &dnfv1.StackableItem{
			Index: uint32(mat.Index),
			Count: uint32(mat.Count),
		})
	}

	now := time.Now().Unix()
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_item_combine (role_id, target_index, material_list, count, result_guid, cost_money, create_time)
		VALUES (?, ?, ?, ?, ?, ?, ?)
	`, roleID, index, fmt.Sprintf("%v", materialItems), count, equip.Guid, 0, now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert item combine record: %w", err)
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
func (d *DB) ItemDisjoint(ctx context.Context, roleID uint64, guids []uint64) (*store.ItemDisjointResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	rewards := &dnfv1.PT_CONTENTS_REWARD_INFO{
		Items: &dnfv1.PT_ITEMS{},
	}

	material := &dnfv1.StackableItem{
		Index: 2013000000,
		Count: uint32(int32(len(guids)) * 10),
	}
	rewards.Items.MaterialItems = []*dnfv1.StackableItem{material}

	now := time.Now().Unix()
	_, err = tx.ExecContext(ctx, `
		INSERT INTO t_item_disjoint (role_id, equip_guids, material_list, create_time)
		VALUES (?, ?, ?, ?)
	`, roleID, fmt.Sprintf("%v", guids), fmt.Sprintf("%v", material), now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert item disjoint record: %w", err)
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
func (d *DB) CardCompose(ctx context.Context, roleID uint64, userCardList []*dnfv1.CardCompose) (*store.CardComposeResult, error) {
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
	case 2:
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
	_, err = tx.ExecContext(ctx, "UPDATE t_role_currency SET money = money - ? WHERE role_id = ?", costMoney, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update money: %w", err)
	}

	var money int64
	err = tx.QueryRowContext(ctx, "SELECT money FROM t_role_currency WHERE role_id = ?", roleID).Scan(&money)
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
