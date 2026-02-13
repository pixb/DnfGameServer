package store

import (
	"context"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

// EmblemUpgradeResult 徽章升级结果
type EmblemUpgradeResult struct {
	SuccessCount int32                          `json:"successCount"`
	Rewards      *dnfv1.PT_CONTENTS_REWARD_INFO `json:"rewards"`
	RemoveItems  *dnfv1.PT_REMOVEITEMS          `json:"removeItems"`
}

// EmblemUpgradeQuickResult 徽章快速升级结果
type EmblemUpgradeQuickResult struct {
	Rewards     *dnfv1.PT_CONTENTS_REWARD_INFO `json:"rewards"`
	RemoveItems *dnfv1.PT_REMOVEITEMS          `json:"removeItems"`
}

// AvatarComposeResult 时装合成结果
type AvatarComposeResult struct {
	Rewards     *dnfv1.PT_CONTENTS_REWARD_INFO `json:"rewards"`
	RemoveItems *dnfv1.PT_REMOVEITEMS          `json:"removeItems"`
}

// ProductionInfoResult 物品制作信息结果
type ProductionInfoResult struct {
	Infos []*dnfv1.PT_ITEM_PRODUCTION_SLOT `json:"infos"`
}

// ProductionRegisterResult 物品制作注册结果
type ProductionRegisterResult struct {
	Rewards       *dnfv1.PT_CONTENTS_REWARD_INFO `json:"rewards"`
	RemoveItems   *dnfv1.PT_REMOVEITEMS          `json:"removeItems"`
	MaterialItems []*dnfv1.ConsumeItems          `json:"materialItems"`
}

// ItemCombineResult 物品合成结果
type ItemCombineResult struct {
	Equip       *dnfv1.EquipmentInfo           `json:"equip"`
	Avatar      *dnfv1.AvatarItem              `json:"avatar"`
	Rewards     *dnfv1.PT_CONTENTS_REWARD_INFO `json:"rewards"`
	RemoveItems *dnfv1.PT_REMOVEITEMS          `json:"removeItems"`
}

// ItemDisjointResult 物品分解结果
type ItemDisjointResult struct {
	Rewards *dnfv1.PT_CONTENTS_REWARD_INFO `json:"rewards"`
}

// CardComposeResult 卡片合成结果
type CardComposeResult struct {
	Card     []*dnfv1.CardCompose `json:"card"`
	Currency []*dnfv1.MoneyItem   `json:"currency"`
}

// ==================== 制作相关Store方法 ====================

// EmblemUpgrade 徽章升级
func (s *Store) EmblemUpgrade(ctx context.Context, roleID uint64, index int32, tryCount int32, talisman int32) (*EmblemUpgradeResult, error) {
	return s.driver.EmblemUpgrade(ctx, roleID, index, tryCount, talisman)
}

// EmblemUpgradeQuick 徽章快速升级
func (s *Store) EmblemUpgradeQuick(ctx context.Context, roleID uint64, source []*dnfv1.IndexCount, target int32) (*EmblemUpgradeQuickResult, error) {
	return s.driver.EmblemUpgradeQuick(ctx, roleID, source, target)
}

// AvatarCompose 时装合成
func (s *Store) AvatarCompose(ctx context.Context, roleID uint64, guids []uint64) (*AvatarComposeResult, error) {
	return s.driver.AvatarCompose(ctx, roleID, guids)
}

// GetProductionInfo 获取物品制作信息
func (s *Store) GetProductionInfo(ctx context.Context, roleID uint64, slotType int32) (*ProductionInfoResult, error) {
	return s.driver.GetProductionInfo(ctx, roleID, slotType)
}

// ProductionRegister 物品制作注册
func (s *Store) ProductionRegister(ctx context.Context, roleID uint64, slotIndex int32, recipeIndex int32, count int32) (*ProductionRegisterResult, error) {
	return s.driver.ProductionRegister(ctx, roleID, slotIndex, recipeIndex, count)
}

// ItemCombine 物品合成
func (s *Store) ItemCombine(ctx context.Context, roleID uint64, index int32, materialItems []*dnfv1.MaterialItem, count int32) (*ItemCombineResult, error) {
	return s.driver.ItemCombine(ctx, roleID, index, materialItems, count)
}

// ItemDisjoint 物品分解
func (s *Store) ItemDisjoint(ctx context.Context, roleID uint64, guids []uint64) (*ItemDisjointResult, error) {
	return s.driver.ItemDisjoint(ctx, roleID, guids)
}

// CardCompose 卡片合成
func (s *Store) CardCompose(ctx context.Context, roleID uint64, userCardList []*dnfv1.CardCompose) (*CardComposeResult, error) {
	return s.driver.CardCompose(ctx, roleID, userCardList)
}

// WardrobeSetSlot 衣柜槽位设置
func (s *Store) WardrobeSetSlot(ctx context.Context, roleID uint64) error {
	return s.driver.WardrobeSetSlot(ctx, roleID)
}
