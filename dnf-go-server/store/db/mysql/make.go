package mysql

import (
	"context"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

// ==================== 制作相关 ====================

// EmblemUpgrade 徽章升级
func (d *DB) EmblemUpgrade(ctx context.Context, roleID uint64, index int32, tryCount int32, talisman int32) (*store.EmblemUpgradeResult, error) {
	return nil, nil
}

// EmblemUpgradeQuick 徽章快速升级
func (d *DB) EmblemUpgradeQuick(ctx context.Context, roleID uint64, source []*dnfv1.IndexCount, target int32) (*store.EmblemUpgradeQuickResult, error) {
	return nil, nil
}

// AvatarCompose 时装合成
func (d *DB) AvatarCompose(ctx context.Context, roleID uint64, guids []uint64) (*store.AvatarComposeResult, error) {
	return nil, nil
}

// GetProductionInfo 获取物品制作信息
func (d *DB) GetProductionInfo(ctx context.Context, roleID uint64, slotType int32) (*store.ProductionInfoResult, error) {
	return nil, nil
}

// ProductionRegister 物品制作注册
func (d *DB) ProductionRegister(ctx context.Context, roleID uint64, slotIndex int32, recipeIndex int32, count int32) (*store.ProductionRegisterResult, error) {
	return nil, nil
}

// ItemCombine 物品合成
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐:写 t_item_combine 记录表,
// 产出装备 GUID 返回(合成配方/材料扣减待接配置表)
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
// 2026-09-06 由 stub 实装,与 sqlite 驱动对齐:写 t_item_disjoint 记录表,
// 产出分解材料(材料规则待接配置表)
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
	return nil, nil
}

// WardrobeSetSlot 衣柜槽位设置
func (d *DB) WardrobeSetSlot(ctx context.Context, roleID uint64) error {
	return nil
}
