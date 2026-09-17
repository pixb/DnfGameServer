package mysql

import (
	"context"
	"database/sql"
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
	// attributes 为空时写 NULL(表有 JSON 校验约束,空字符串不合法)
	var attrs interface{}
	if create.Attributes != "" {
		attrs = create.Attributes
	}
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.RoleID, create.ItemID, create.GridIndex, create.Count,
		create.IsEquiped, create.BindType, create.Durability, create.EnhanceLevel, attrs,
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
		// attributes 可为 NULL(空属性存 NULL),用 NullString 兼容
		var attrs sql.NullString
		err := rows.Scan(&item.ID, &item.CreatedAt, &item.UpdatedAt, &item.RowStatus,
			&item.RoleID, &item.ItemID, &item.GridIndex, &item.Count,
			&item.IsEquiped, &item.BindType, &item.Durability, &item.EnhanceLevel, &attrs)
		if err != nil {
			return nil, fmt.Errorf("failed to scan bag item: %w", err)
		}
		item.Attributes = attrs.String
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

// ==================== 物品模板(2026-09-08 第七十三轮) ====================

// GetItemTemplate 获取物品模板
func (d *DB) GetItemTemplate(ctx context.Context, itemID int32) (*store.ItemTemplate, error) {
	row := d.db.QueryRowContext(ctx,
		`SELECT item_id, name, item_type, level, bind_type, sell_price, description FROM t_item_template WHERE item_id = ?`,
		itemID)
	var t store.ItemTemplate
	if err := row.Scan(&t.ItemID, &t.Name, &t.ItemType, &t.Level, &t.BindType, &t.SellPrice, &t.Description); err != nil {
		if err == sql.ErrNoRows {
			return nil, store.ErrNotFound
		}
		return nil, fmt.Errorf("failed to scan item template: %w", err)
	}
	return &t, nil
}

// ListItemTemplates 获取全部物品模板
func (d *DB) ListItemTemplates(ctx context.Context) ([]*store.ItemTemplate, error) {
	rows, err := d.db.QueryContext(ctx,
		`SELECT item_id, name, item_type, level, bind_type, sell_price, description FROM t_item_template ORDER BY item_id`)
	if err != nil {
		return nil, fmt.Errorf("failed to query item templates: %w", err)
	}
	defer rows.Close()

	var list []*store.ItemTemplate
	for rows.Next() {
		var t store.ItemTemplate
		if err := rows.Scan(&t.ItemID, &t.Name, &t.ItemType, &t.Level, &t.BindType, &t.SellPrice, &t.Description); err != nil {
			return nil, fmt.Errorf("failed to scan item template: %w", err)
		}
		list = append(list, &t)
	}
	return list, rows.Err()
}
