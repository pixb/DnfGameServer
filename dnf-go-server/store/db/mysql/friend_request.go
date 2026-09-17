package mysql

import (
	"context"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 好友申请CRUD(2026-09-06 第三十九轮) ====================

// CreateFriendRequest 创建好友申请
func (d *DB) CreateFriendRequest(ctx context.Context, create *store.FriendRequest) (*store.FriendRequest, error) {
	query := `
      INSERT INTO friend_request (created_at, updated_at, row_status, from_role_id, from_role_name, to_role_id, status)
      VALUES (?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.FromRoleID, create.FromRoleName, create.ToRoleID, create.Status,
	)
	if err != nil {
		if strings.Contains(err.Error(), "Duplicate entry") {
			return nil, store.ErrDuplicate
		}
		return nil, fmt.Errorf("failed to create friend request: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetFriendRequest 获取好友申请
func (d *DB) GetFriendRequest(ctx context.Context, find *store.FindFriendRequest) (*store.FriendRequest, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.FromRoleID != nil {
		where = append(where, "from_role_id = ?")
		args = append(args, *find.FromRoleID)
	}
	if find.ToRoleID != nil {
		where = append(where, "to_role_id = ?")
		args = append(args, *find.ToRoleID)
	}
	if find.Status != nil {
		where = append(where, "status = ?")
		args = append(args, *find.Status)
	}

	query := `SELECT id, created_at, updated_at, row_status, from_role_id, from_role_name, to_role_id, status FROM friend_request`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " ORDER BY id DESC LIMIT 1"

	row := d.db.QueryRowContext(ctx, query, args...)
	fr, err := scanFriendRequest(row)
	if err != nil {
		return nil, err
	}
	return fr, nil
}

// ListFriendRequests 查询好友申请列表
func (d *DB) ListFriendRequests(ctx context.Context, find *store.FindFriendRequest) ([]*store.FriendRequest, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.FromRoleID != nil {
		where = append(where, "from_role_id = ?")
		args = append(args, *find.FromRoleID)
	}
	if find.ToRoleID != nil {
		where = append(where, "to_role_id = ?")
		args = append(args, *find.ToRoleID)
	}
	if find.Status != nil {
		where = append(where, "status = ?")
		args = append(args, *find.Status)
	}

	query := `SELECT id, created_at, updated_at, row_status, from_role_id, from_role_name, to_role_id, status FROM friend_request`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " ORDER BY id DESC"

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to list friend requests: %w", err)
	}
	defer rows.Close()

	var list []*store.FriendRequest
	for rows.Next() {
		fr := &store.FriendRequest{}
		if err := rows.Scan(&fr.ID, &fr.CreatedAt, &fr.UpdatedAt, &fr.RowStatus,
			&fr.FromRoleID, &fr.FromRoleName, &fr.ToRoleID, &fr.Status); err != nil {
			return nil, fmt.Errorf("failed to scan friend request: %w", err)
		}
		list = append(list, fr)
	}
	return list, rows.Err()
}

// UpdateFriendRequest 更新好友申请
func (d *DB) UpdateFriendRequest(ctx context.Context, update *store.UpdateFriendRequest) error {
	sets := []string{"updated_at = ?"}
	args := []interface{}{time.Now().Unix()}

	if update.Status != nil {
		sets = append(sets, "status = ?")
		args = append(args, *update.Status)
	}
	if update.RowStatus != nil {
		sets = append(sets, "row_status = ?")
		args = append(args, string(*update.RowStatus))
	}

	args = append(args, update.ID)
	query := fmt.Sprintf("UPDATE friend_request SET %s WHERE id = ?", strings.Join(sets, ", "))
	if _, err := d.db.ExecContext(ctx, query, args...); err != nil {
		return fmt.Errorf("failed to update friend request: %w", err)
	}
	return nil
}

// scanFriendRequest 扫描单行
type requestRowScanner interface {
	Scan(dest ...interface{}) error
}

func scanFriendRequest(row requestRowScanner) (*store.FriendRequest, error) {
	fr := &store.FriendRequest{}
	if err := row.Scan(&fr.ID, &fr.CreatedAt, &fr.UpdatedAt, &fr.RowStatus,
		&fr.FromRoleID, &fr.FromRoleName, &fr.ToRoleID, &fr.Status); err != nil {
		return nil, fmt.Errorf("failed to scan friend request: %w", err)
	}
	return fr, nil
}
