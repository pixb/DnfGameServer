package sqlite

import (
	"context"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 账户CRUD ====================

// CreateAccount 创建账户
func (d *DB) CreateAccount(ctx context.Context, create *store.Account) (*store.Account, error) {
	query := `
      INSERT INTO account (created_at, updated_at, row_status, openid, account_key, auth_key, last_login_at, last_login_ip, authority, status)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.OpenID, create.AccountKey, create.AuthKey,
		create.LastLoginAt, create.LastLoginIP, create.Authority, create.Status,
	)
	if err != nil {
		if strings.Contains(err.Error(), "UNIQUE constraint failed") {
			return nil, store.ErrDuplicate
		}
		return nil, fmt.Errorf("failed to create account: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetAccount 获取单个账户
func (d *DB) GetAccount(ctx context.Context, find *store.FindAccount) (*store.Account, error) {
	accounts, err := d.ListAccounts(ctx, find)
	if err != nil {
		return nil, err
	}
	if len(accounts) == 0 {
		return nil, store.ErrNotFound
	}
	return accounts[0], nil
}

// ListAccounts 查询账户列表
func (d *DB) ListAccounts(ctx context.Context, find *store.FindAccount) ([]*store.Account, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.OpenID != nil {
		where = append(where, "openid = ?")
		args = append(args, *find.OpenID)
	}
	if find.Status != nil {
		where = append(where, "status = ?")
		args = append(args, *find.Status)
	}
	if find.RowStatus != nil {
		where = append(where, "row_status = ?")
		args = append(args, *find.RowStatus)
	}

	query := "SELECT id, created_at, updated_at, row_status, openid, account_key, auth_key, last_login_at, last_login_ip, authority, status FROM account"
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	if find.Limit != nil {
		query += fmt.Sprintf(" LIMIT %d", *find.Limit)
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query accounts: %w", err)
	}
	defer rows.Close()

	var accounts []*store.Account
	for rows.Next() {
		var a store.Account
		err := rows.Scan(&a.ID, &a.CreatedAt, &a.UpdatedAt, &a.RowStatus,
			&a.OpenID, &a.AccountKey, &a.AuthKey, &a.LastLoginAt, &a.LastLoginIP,
			&a.Authority, &a.Status)
		if err != nil {
			return nil, fmt.Errorf("failed to scan account: %w", err)
		}
		accounts = append(accounts, &a)
	}

	return accounts, nil
}

// UpdateAccount 更新账户
func (d *DB) UpdateAccount(ctx context.Context, update *store.UpdateAccount) (*store.Account, error) {
	var sets []string
	var args []interface{}

	if update.RowStatus != nil {
		sets = append(sets, "row_status = ?")
		args = append(args, *update.RowStatus)
	}
	if update.AccountKey != nil {
		sets = append(sets, "account_key = ?")
		args = append(args, *update.AccountKey)
	}
	if update.AuthKey != nil {
		sets = append(sets, "auth_key = ?")
		args = append(args, *update.AuthKey)
	}
	if update.LastLoginAt != nil {
		sets = append(sets, "last_login_at = ?")
		args = append(args, *update.LastLoginAt)
	}
	if update.LastLoginIP != nil {
		sets = append(sets, "last_login_ip = ?")
		args = append(args, *update.LastLoginIP)
	}
	if update.Authority != nil {
		sets = append(sets, "authority = ?")
		args = append(args, *update.Authority)
	}
	if update.Status != nil {
		sets = append(sets, "status = ?")
		args = append(args, *update.Status)
	}

	if len(sets) == 0 {
		return d.GetAccount(ctx, &store.FindAccount{FindBase: store.FindBase{ID: &update.ID}})
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE account SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to update account: %w", err)
	}

	return d.GetAccount(ctx, &store.FindAccount{FindBase: store.FindBase{ID: &update.ID}})
}

// DeleteAccount 删除账户
func (d *DB) DeleteAccount(ctx context.Context, delete *store.DeleteAccount) error {
	query := "DELETE FROM account WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete account: %w", err)
	}
	return nil
}
