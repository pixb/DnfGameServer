package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch52Migration 测试批次52的迁移结果
func TestBatch52Migration(t *testing.T) {
	// 测试塔系统
	t.Run("TowerSystem", testBatch52TowerSystem)
	
	// 测试药水系统
	t.Run("TonicSystem", testBatch52TonicSystem)
	
	// 测试衣柜系统
	t.Run("WardrobeSystem", testBatch52WardrobeSystem)
	
	// 测试物品移除
	t.Run("RemoveItems", testBatch52RemoveItems)
}

// testBatch52TowerSystem 测试塔系统
func testBatch52TowerSystem(t *testing.T) {
	// 测试TowerOfIllusionClearRate
	towerOfIllusionClearRate := &dnfv1.TowerOfIllusionClearRate{
		Floor:   10,
		Percent: 95.5,
	}
	assert.NotNil(t, towerOfIllusionClearRate)
	assert.Equal(t, int32(10), towerOfIllusionClearRate.Floor)
	assert.Equal(t, float32(95.5), towerOfIllusionClearRate.Percent)
}

// testBatch52TonicSystem 测试药水系统
func testBatch52TonicSystem(t *testing.T) {
	// 测试TonicInfo
	tonicInfo := &dnfv1.TonicInfo{
		Index: 1,
		Level: 5,
	}
	assert.NotNil(t, tonicInfo)
	assert.Equal(t, int32(1), tonicInfo.Index)
	assert.Equal(t, int32(5), tonicInfo.Level)
	
	// 测试TonicMaterialUsage
	tonicMaterialUsage := &dnfv1.TonicMaterialUsage{
		Index: 2,
		Total: 100,
	}
	assert.NotNil(t, tonicMaterialUsage)
	assert.Equal(t, int32(2), tonicMaterialUsage.Index)
	assert.Equal(t, int32(100), tonicMaterialUsage.Total)
}

// testBatch52WardrobeSystem 测试衣柜系统
func testBatch52WardrobeSystem(t *testing.T) {
	// 测试AvatarMannequinPartInfo
	avatarMannequinPartInfo := &dnfv1.AvatarMannequinPartInfo{
		Guid:  1234567890,
		Index: 1,
	}
	assert.NotNil(t, avatarMannequinPartInfo)
	assert.Equal(t, uint64(1234567890), avatarMannequinPartInfo.Guid)
	assert.Equal(t, int32(1), avatarMannequinPartInfo.Index)
	
	// 测试AvatarMannequinInfo
	avatarMannequinInfo := &dnfv1.AvatarMannequinInfo{
		Slot: 1,
		Name:  "Test Mannequin",
		List: []*dnfv1.AvatarMannequinPartInfo{
			avatarMannequinPartInfo,
		},
	}
	assert.NotNil(t, avatarMannequinInfo)
	assert.Equal(t, int32(1), avatarMannequinInfo.Slot)
	assert.Equal(t, "Test Mannequin", avatarMannequinInfo.Name)
	assert.Len(t, avatarMannequinInfo.List, 1)
	
	// 测试WardrobeInfo
	wardrobeInfo := &dnfv1.WardrobeInfo{
		ExtensionSlot: 5,
		List: []*dnfv1.AvatarMannequinInfo{
			avatarMannequinInfo,
		},
		AppendageSlot: 3,
		Slot:          10,
	}
	assert.NotNil(t, wardrobeInfo)
	assert.Equal(t, int32(5), wardrobeInfo.ExtensionSlot)
	assert.Len(t, wardrobeInfo.List, 1)
	assert.Equal(t, int32(3), wardrobeInfo.AppendageSlot)
	assert.Equal(t, int32(10), wardrobeInfo.Slot)
}

// testBatch52RemoveItems 测试物品移除
func testBatch52RemoveItems(t *testing.T) {
	// 测试RemoveItems
	removeItems := &dnfv1.RemoveItems{
		MaterialItems: []*dnfv1.Stackable{
			{Index: 1, Count: 10},
		},
		EmblemItems: []*dnfv1.Stackable{
			{Index: 2, Count: 5},
		},
		CardItems: []*dnfv1.Stackable{
			{Index: 3, Count: 3},
		},
		ConsumeItems: []*dnfv1.Stackable{
			{Index: 4, Count: 20},
		},
		EpicPieceItems: []*dnfv1.Stackable{
			{Index: 5, Count: 2},
		},
		CrackItems: []*dnfv1.Stackable{
			{Index: 6, Count: 1},
		},
		BookmarkItems: []*dnfv1.Stackable{
			{Index: 7, Count: 4},
		},
	}
	assert.NotNil(t, removeItems)
	assert.Len(t, removeItems.MaterialItems, 1)
	assert.Len(t, removeItems.EmblemItems, 1)
	assert.Len(t, removeItems.CardItems, 1)
	assert.Len(t, removeItems.ConsumeItems, 1)
	assert.Len(t, removeItems.EpicPieceItems, 1)
	assert.Len(t, removeItems.CrackItems, 1)
	assert.Len(t, removeItems.BookmarkItems, 1)
}
