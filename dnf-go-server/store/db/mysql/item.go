package mysql

import (
	"context"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 背包物品CRUD ====================

// CreateBagItem 创建背包物品
func (d *DB) CreateBagItem(ctx context.Context, create *store.BagItem) (*store.BagItem, error) {
	query := `
      INSERT INTO bag_item (created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.RoleID, create.ItemID, create.GridIndex, create.Count,
		create.IsEquiped, create.BindType, create.Durability, create.EnhanceLevel, create.Attributes,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create bag item: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// UpdateBagItem 更新背包物品
func (d *DB) UpdateBagItem(ctx context.Context, update *store.UpdateBagItem) error {
	var sets []string
	var args []interface{}

	if update.GridIndex != nil {
		sets = append(sets, "grid_index = ?")
		args = append(args, *update.GridIndex)
	}
	if update.Count != nil {
		sets = append(sets, "count = ?")
		args = append(args, *update.Count)
	}
	if update.IsEquiped != nil {
		sets = append(sets, "is_equipped = ?")
		args = append(args, *update.IsEquiped)
	}
	if update.Durability != nil {
		sets = append(sets, "durability = ?")
		args = append(args, *update.Durability)
	}
	if update.EnhanceLevel != nil {
		sets = append(sets, "enhance_level = ?")
		args = append(args, *update.EnhanceLevel)
	}
	if update.Attributes != nil {
		sets = append(sets, "attributes = ?")
		args = append(args, *update.Attributes)
	}
	if update.RowStatus != nil {
		sets = append(sets, "row_status = ?")
		args = append(args, *update.RowStatus)
	}

	if len(sets) == 0 {
		return nil
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE bag_item SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update bag item: %w", err)
	}
	return nil
}

// GetBagItem 获取背包物品
func (d *DB) GetBagItem(ctx context.Context, find *store.FindBagItem) (*store.BagItem, error) {
	items, err := d.ListBagItems(ctx, find)
	if err != nil {
		return nil, err
	}
	if len(items) == 0 {
		return nil, store.ErrNotFound
	}
	return items[0], nil
}

// ListBagItems 查询背包物品列表
func (d *DB) ListBagItems(ctx context.Context, find *store.FindBagItem) ([]*store.BagItem, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.RoleID != nil {
		where = append(where, "role_id = ?")
		args = append(args, *find.RoleID)
	}
	if find.ItemID != nil {
		where = append(where, "item_id = ?")
		args = append(args, *find.ItemID)
	}
	if find.GridIndex != nil {
		where = append(where, "grid_index = ?")
		args = append(args, *find.GridIndex)
	}

	query := `SELECT id, created_at, updated_at, row_status, role_id, item_id, grid_index, count, is_equipped, bind_type, durability, enhance_level, attributes FROM bag_item`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " ORDER BY grid_index"

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query bag items: %w", err)
	}
	defer rows.Close()

	var items []*store.BagItem
	for rows.Next() {
		var item store.BagItem
		err := rows.Scan(&item.ID, &item.CreatedAt, &item.UpdatedAt, &item.RowStatus,
			&item.RoleID, &item.ItemID, &item.GridIndex, &item.Count,
			&item.IsEquiped, &item.BindType, &item.Durability, &item.EnhanceLevel, &item.Attributes)
		if err != nil {
			return nil, fmt.Errorf("failed to scan bag item: %w", err)
		}
		items = append(items, &item)
	}

	return items, nil
}

// ListBagItemsByRole 获取角色的所有背包物品
func (d *DB) ListBagItemsByRole(ctx context.Context, roleID uint64) ([]*store.BagItem, error) {
	return d.ListBagItems(ctx, &store.FindBagItem{RoleID: &roleID})
}

// DeleteBagItem 删除背包物品
func (d *DB) DeleteBagItem(ctx context.Context, delete *store.DeleteBagItem) error {
	query := "DELETE FROM bag_item WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete bag item: %w", err)
	}
	return nil
}
