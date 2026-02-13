package mysql

import (
	"context"

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
func (d *DB) ItemCombine(ctx context.Context, roleID uint64, index int32, materialItems []*dnfv1.MaterialItem, count int32) (*store.ItemCombineResult, error) {
	return nil, nil
}

// ItemDisjoint 物品分解
func (d *DB) ItemDisjoint(ctx context.Context, roleID uint64, guids []uint64) (*store.ItemDisjointResult, error) {
	return nil, nil
}

// CardCompose 卡片合成
func (d *DB) CardCompose(ctx context.Context, roleID uint64, userCardList []*dnfv1.CardCompose) (*store.CardComposeResult, error) {
	return nil, nil
}

// WardrobeSetSlot 衣柜槽位设置
func (d *DB) WardrobeSetSlot(ctx context.Context, roleID uint64) error {
	return nil
}
