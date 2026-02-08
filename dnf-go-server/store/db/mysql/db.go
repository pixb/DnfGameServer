package mysql

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"

	_ "github.com/go-sql-driver/mysql"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ProfileProvider 配置接口
type ProfileProvider interface {
	GetDriver() string
	GetDSN() string
	GetMode() string
}

// DB MySQL数据库实现
type DB struct {
	db     *sql.DB
	config ProfileProvider
}

// NewDB 创建MySQL数据库实例
func NewDB(config ProfileProvider) (store.Driver, error) {
	db, err := sql.Open("mysql", config.GetDSN())
	if err != nil {
		return nil, fmt.Errorf("failed to open mysql connection: %w", err)
	}

	// 配置连接池
	db.SetMaxOpenConns(25)
	db.SetMaxIdleConns(10)
	db.SetConnMaxLifetime(1 * time.Hour)

	// 测试连接
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	if err := db.PingContext(ctx); err != nil {
		return nil, fmt.Errorf("failed to ping mysql: %w", err)
	}

	return &DB{db: db, config: config}, nil
}

// GetDB 获取底层sql.DB
func (d *DB) GetDB() *sql.DB {
	return d.db
}

// Close 关闭数据库连接
func (d *DB) Close() error {
	return d.db.Close()
}

// IsInitialized 检查数据库是否已初始化
func (d *DB) IsInitialized(ctx context.Context) (bool, error) {
	query := `SELECT 1 FROM account LIMIT 1`
	var dummy int
	err := d.db.QueryRowContext(ctx, query).Scan(&dummy)
	if err == sql.ErrNoRows {
		return true, nil // 表存在但无数据
	}
	if err != nil {
		return false, nil // 表不存在
	}
	return true, nil
}

// GetCurrentSchemaVersion 获取当前Schema版本
func (d *DB) GetCurrentSchemaVersion() string {
	// TODO: 从数据库读取版本
	return "0.1"
}

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
		if strings.Contains(err.Error(), "Duplicate entry") {
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

// ==================== 角色CRUD ====================

// CreateRole 创建角色
func (d *DB) CreateRole(ctx context.Context, create *store.Role) (*store.Role, error) {
	query := `
      INSERT INTO role (created_at, updated_at, row_status, account_id, role_id, name, job, level, exp, fatigue, max_fatigue, map_id, x, y, channel)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.AccountID, create.RoleID, create.Name, create.Job, create.Level,
		create.Exp, create.Fatigue, create.MaxFatigue, create.MapID, create.X, create.Y, create.Channel,
	)
	if err != nil {
		if strings.Contains(err.Error(), "Duplicate entry") {
			return nil, store.ErrDuplicate
		}
		return nil, fmt.Errorf("failed to create role: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal

	// 创建默认属性
	d.CreateRoleAttributes(ctx, &store.RoleAttributes{
		RoleID:       create.ID,
		HP:           100,
		MaxHP:        100,
		MP:           100,
		MaxMP:        100,
		Strength:     10,
		Intelligence: 10,
		Vitality:     10,
		Spirit:       10,
	})

	// 创建默认货币
	d.UpdateRoleCurrency(ctx, &store.RoleCurrency{
		RoleID:     create.ID,
		Gold:       0,
		Coin:       0,
		Fatigue:    100,
		MaxFatigue: 100,
	})

	return create, nil
}

// GetRole 获取角色
func (d *DB) GetRole(ctx context.Context, find *store.FindRole) (*store.Role, error) {
	roles, err := d.ListRoles(ctx, find)
	if err != nil {
		return nil, err
	}
	if len(roles) == 0 {
		return nil, store.ErrNotFound
	}
	return roles[0], nil
}

// ListRoles 查询角色列表
func (d *DB) ListRoles(ctx context.Context, find *store.FindRole) ([]*store.Role, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.AccountID != nil {
		where = append(where, "account_id = ?")
		args = append(args, *find.AccountID)
	}
	if find.RoleID != nil {
		where = append(where, "role_id = ?")
		args = append(args, *find.RoleID)
	}
	if find.Name != nil {
		where = append(where, "name = ?")
		args = append(args, *find.Name)
	}
	if find.RowStatus != nil {
		where = append(where, "row_status = ?")
		args = append(args, *find.RowStatus)
	}

	query := `SELECT id, created_at, updated_at, row_status, account_id, role_id, name, job, level, exp, fatigue, max_fatigue, map_id, x, y, channel FROM role`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	if find.Limit != nil {
		query += fmt.Sprintf(" LIMIT %d", *find.Limit)
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query roles: %w", err)
	}
	defer rows.Close()

	var roles []*store.Role
	for rows.Next() {
		var r store.Role
		err := rows.Scan(&r.ID, &r.CreatedAt, &r.UpdatedAt, &r.RowStatus,
			&r.AccountID, &r.RoleID, &r.Name, &r.Job, &r.Level, &r.Exp,
			&r.Fatigue, &r.MaxFatigue, &r.MapID, &r.X, &r.Y, &r.Channel)
		if err != nil {
			return nil, fmt.Errorf("failed to scan role: %w", err)
		}
		roles = append(roles, &r)
	}

	return roles, nil
}

// ListRolesByAccount 获取账户的所有角色
func (d *DB) ListRolesByAccount(ctx context.Context, accountID uint64) ([]*store.Role, error) {
	return d.ListRoles(ctx, &store.FindRole{AccountID: &accountID})
}

// UpdateRole 更新角色
func (d *DB) UpdateRole(ctx context.Context, update *store.UpdateRole) (*store.Role, error) {
	var sets []string
	var args []interface{}

	if update.Name != nil {
		sets = append(sets, "name = ?")
		args = append(args, *update.Name)
	}
	if update.Level != nil {
		sets = append(sets, "level = ?")
		args = append(args, *update.Level)
	}
	if update.Exp != nil {
		sets = append(sets, "exp = ?")
		args = append(args, *update.Exp)
	}
	if update.Fatigue != nil {
		sets = append(sets, "fatigue = ?")
		args = append(args, *update.Fatigue)
	}
	if update.MapID != nil {
		sets = append(sets, "map_id = ?")
		args = append(args, *update.MapID)
	}
	if update.X != nil {
		sets = append(sets, "x = ?")
		args = append(args, *update.X)
	}
	if update.Y != nil {
		sets = append(sets, "y = ?")
		args = append(args, *update.Y)
	}
	if update.RowStatus != nil {
		sets = append(sets, "row_status = ?")
		args = append(args, *update.RowStatus)
	}

	if len(sets) == 0 {
		return d.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &update.ID}})
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE role SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to update role: %w", err)
	}

	return d.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &update.ID}})
}

// DeleteRole 删除角色
func (d *DB) DeleteRole(ctx context.Context, delete *store.DeleteRole) error {
	query := "DELETE FROM role WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete role: %w", err)
	}
	return nil
}
