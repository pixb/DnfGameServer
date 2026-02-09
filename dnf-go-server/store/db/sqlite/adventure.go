package sqlite

import (
	"context"
	"fmt"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 冒险数据 CRUD (存根) ====================

func (d *DB) GetAdventureData(ctx context.Context, find *store.FindAdventureData) (*store.AdventureData, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) CreateAdventureData(ctx context.Context, create *store.CreateAdventureData) (*store.AdventureData, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureData(ctx context.Context, update *store.UpdateAdventureData) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险存储物品 CRUD (存根) ====================

func (d *DB) CreateAdventureStorageItem(ctx context.Context, create *store.CreateAdventureStorageItem) (*store.AdventureStorageItem, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureStorageItem(ctx context.Context, update *store.UpdateAdventureStorageItem) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureStorageItems(ctx context.Context, find *store.FindAdventureStorageItem) ([]*store.AdventureStorageItem, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) DeleteAdventureStorageItem(ctx context.Context, id uint64) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险收获 CRUD (存根) ====================

func (d *DB) CreateAdventureReap(ctx context.Context, create *store.CreateAdventureReap) (*store.AdventureReap, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureReap(ctx context.Context, update *store.UpdateAdventureReap) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureReaps(ctx context.Context, roleID uint64) ([]*store.AdventureReap, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) DeleteAdventureReap(ctx context.Context, id uint64) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险书 CRUD (存根) ====================

func (d *DB) CreateAdventureBook(ctx context.Context, create *store.CreateAdventureBook) (*store.AdventureBook, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureBook(ctx context.Context, update *store.UpdateAdventureBook) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) GetAdventureBook(ctx context.Context, find *store.FindAdventureBook) (*store.AdventureBook, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureBooks(ctx context.Context, roleID uint64) ([]*store.AdventureBook, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险书条件 CRUD (存根) ====================

func (d *DB) CreateAdventureBookCondition(ctx context.Context, create *store.CreateAdventureBookCondition) (*store.AdventureBookCondition, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureBookCondition(ctx context.Context, update *store.UpdateAdventureBookCondition) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险书奖励 CRUD (存根) ====================

func (d *DB) CreateAdventureBookReward(ctx context.Context, create *store.CreateAdventureBookReward) (*store.AdventureBookReward, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ClaimAdventureBookReward(ctx context.Context, claim *store.ClaimAdventureBookReward) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureBookRewards(ctx context.Context, bookID int32) ([]*store.AdventureBookReward, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}
