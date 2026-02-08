package mysql

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 好友CRUD ====================

// CreateFriend 创建好友
func (d *DB) CreateFriend(ctx context.Context, create *store.Friend) (*store.Friend, error) {
	query := `
      INSERT INTO friend (created_at, updated_at, row_status, role_id, friend_id, friend_name, intimacy, friend_group)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.RoleID, create.FriendID, create.FriendName, create.Intimacy, create.Group,
	)
	if err != nil {
		if strings.Contains(err.Error(), "Duplicate entry") {
			return nil, store.ErrDuplicate
		}
		return nil, fmt.Errorf("failed to create friend: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetFriend 获取好友
func (d *DB) GetFriend(ctx context.Context, find *store.FindFriend) (*store.Friend, error) {
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
	if find.FriendID != nil {
		where = append(where, "friend_id = ?")
		args = append(args, *find.FriendID)
	}

	query := `SELECT id, created_at, updated_at, row_status, role_id, friend_id, friend_name, intimacy, friend_group FROM friend`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}

	var f store.Friend
	err := d.db.QueryRowContext(ctx, query, args...).Scan(
		&f.ID, &f.CreatedAt, &f.UpdatedAt, &f.RowStatus,
		&f.RoleID, &f.FriendID, &f.FriendName, &f.Intimacy, &f.Group,
	)
	if err == sql.ErrNoRows {
		return nil, store.ErrNotFound
	}
	if err != nil {
		return nil, fmt.Errorf("failed to get friend: %w", err)
	}
	return &f, nil
}

// UpdateFriend 更新好友
func (d *DB) UpdateFriend(ctx context.Context, update *store.UpdateFriend) error {
	// 简化实现
	return nil
}

// ListFriends 获取好友列表
func (d *DB) ListFriends(ctx context.Context, roleID uint64) ([]*store.Friend, error) {
	query := `SELECT id, created_at, updated_at, row_status, role_id, friend_id, friend_name, intimacy, friend_group FROM friend WHERE role_id = ?`

	rows, err := d.db.QueryContext(ctx, query, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to query friends: %w", err)
	}
	defer rows.Close()

	var friends []*store.Friend
	for rows.Next() {
		var f store.Friend
		err := rows.Scan(&f.ID, &f.CreatedAt, &f.UpdatedAt, &f.RowStatus,
			&f.RoleID, &f.FriendID, &f.FriendName, &f.Intimacy, &f.Group)
		if err != nil {
			return nil, fmt.Errorf("failed to scan friend: %w", err)
		}
		friends = append(friends, &f)
	}

	return friends, nil
}

// DeleteFriend 删除好友
func (d *DB) DeleteFriend(ctx context.Context, delete *store.DeleteFriend) error {
	query := "DELETE FROM friend WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete friend: %w", err)
	}
	return nil
}

// ==================== 邮件CRUD ====================

// CreateMail 创建邮件
func (d *DB) CreateMail(ctx context.Context, create *store.Mail) (*store.Mail, error) {
	query := `
      INSERT INTO mail (created_at, updated_at, row_status, sender_id, sender_name, receiver_id, title, content, attachments, gold, is_read, is_claimed, expire_at)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.SenderID, create.SenderName, create.ReceiverID, create.Title, create.Content,
		create.Attachments, create.Gold, create.IsRead, create.IsClaimed, create.ExpireAt,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create mail: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetMail 获取邮件
func (d *DB) GetMail(ctx context.Context, find *store.FindMail) (*store.Mail, error) {
	mails, err := d.ListMails(ctx, find)
	if err != nil {
		return nil, err
	}
	if len(mails) == 0 {
		return nil, store.ErrNotFound
	}
	return mails[0], nil
}

// ListMails 查询邮件列表
func (d *DB) ListMails(ctx context.Context, find *store.FindMail) ([]*store.Mail, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.ReceiverID != nil {
		where = append(where, "receiver_id = ?")
		args = append(args, *find.ReceiverID)
	}

	query := `SELECT id, created_at, updated_at, row_status, sender_id, sender_name, receiver_id, title, content, attachments, gold, is_read, is_claimed, expire_at FROM mail`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " ORDER BY created_at DESC"

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query mails: %w", err)
	}
	defer rows.Close()

	var mails []*store.Mail
	for rows.Next() {
		var m store.Mail
		err := rows.Scan(&m.ID, &m.CreatedAt, &m.UpdatedAt, &m.RowStatus,
			&m.SenderID, &m.SenderName, &m.ReceiverID, &m.Title, &m.Content,
			&m.Attachments, &m.Gold, &m.IsRead, &m.IsClaimed, &m.ExpireAt)
		if err != nil {
			return nil, fmt.Errorf("failed to scan mail: %w", err)
		}
		mails = append(mails, &m)
	}

	return mails, nil
}

// UpdateMail 更新邮件
func (d *DB) UpdateMail(ctx context.Context, update *store.UpdateMail) error {
	// 简化实现
	return nil
}

// DeleteMail 删除邮件
func (d *DB) DeleteMail(ctx context.Context, delete *store.DeleteMail) error {
	query := "DELETE FROM mail WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete mail: %w", err)
	}
	return nil
}
