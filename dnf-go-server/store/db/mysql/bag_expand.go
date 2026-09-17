package mysql

import (
	"context"
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
		return nil, fmt.Errorf("failed to get bag expand: %w", err)
	}
	return b, nil
}

// UpsertBagExpand 新增/更新背包扩容记录(按 role_id+bag_type 唯一)
func (d *DB) UpsertBagExpand(ctx context.Context, b *store.BagExpand) (*store.BagExpand, error) {
	now := time.Now().Unix()
	_, err := d.db.ExecContext(ctx, `
      INSERT INTO t_bag_expand (created_at, updated_at, row_status, role_id, bag_type, capacity)
      VALUES (?, ?, ?, ?, ?, ?)
      ON DUPLICATE KEY UPDATE capacity = VALUES(capacity), updated_at = VALUES(updated_at)
   `, now, now, store.RowStatusNormal, b.RoleID, b.BagType, b.Capacity)
	if err != nil {
		return nil, fmt.Errorf("failed to upsert bag expand: %w", err)
	}
	return b, nil
}
