package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 背包扩容 ====================

// GetBagExpand 获取背包扩容记录
func (d *DB) GetBagExpand(ctx context.Context, find *store.FindBagExpand) (*store.BagExpand, error) {
	query := `
      SELECT id, created_at, updated_at, row_status, role_id, bag_type, capacity
      FROM t_bag_expand
      WHERE row_status = 'NORMAL' AND role_id = ?
   `
	var args []interface{}
	args = append(args, find.RoleID)

	if find.BagType != nil {
		query += " AND bag_type = ?"
		args = append(args, *find.BagType)
	}
	query += " LIMIT 1"

	row := d.db.QueryRowContext(ctx, query, args...)
	b := &store.BagExpand{}
	if err := row.Scan(&b.ID, &b.CreatedAt, &b.UpdatedAt, &b.RowStatus,
		&b.RoleID, &b.BagType, &b.Capacity); err != nil {
		if err == sql.ErrNoRows {
			return nil, err
		}
		return nil, fmt.Errorf("failed to get bag expand: %w", err)
	}
	return b, nil
}

// UpsertBagExpand 新增/更新背包扩容记录(按 role_id+bag_type 唯一)
func (d *DB) UpsertBagExpand(ctx context.Context, b *store.BagExpand) (*store.BagExpand, error) {
	now := time.Now().Unix()
	existing, err := d.GetBagExpand(ctx, &store.FindBagExpand{
		RoleID:  b.RoleID,
		BagType: &b.BagType,
	})
	if err != nil && err != sql.ErrNoRows {
		return nil, fmt.Errorf("failed to query bag expand: %w", err)
	}
	if existing != nil {
		_, err = d.db.ExecContext(ctx, `
         UPDATE t_bag_expand SET capacity = ?, updated_at = ? WHERE id = ?
      `, b.Capacity, now, existing.ID)
		if err != nil {
			return nil, fmt.Errorf("failed to update bag expand: %w", err)
		}
		b.ID = existing.ID
	} else {
		result, err := d.db.ExecContext(ctx, `
         INSERT INTO t_bag_expand (created_at, updated_at, row_status, role_id, bag_type, capacity)
         VALUES (?, ?, ?, ?, ?, ?)
      `, now, now, store.RowStatusNormal, b.RoleID, b.BagType, b.Capacity)
		if err != nil {
			return nil, fmt.Errorf("failed to insert bag expand: %w", err)
		}
		id, _ := result.LastInsertId()
		b.ID = uint64(id)
	}
	b.CreatedAt = now
	b.UpdatedAt = now
	b.RowStatus = store.RowStatusNormal
	return b, nil
}
