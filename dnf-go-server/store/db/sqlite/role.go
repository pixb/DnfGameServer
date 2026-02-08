package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

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
		if strings.Contains(err.Error(), "UNIQUE constraint failed") {
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

// GetRoleByName 根据角色名获取角色
func (d *DB) GetRoleByName(ctx context.Context, name string) (*store.Role, error) {
	query := `SELECT id, created_at, updated_at, row_status, account_id, role_id, name, job, level, exp, fatigue, max_fatigue, map_id, x, y, channel FROM role WHERE name = ? AND row_status = 'NORMAL' LIMIT 1`
	row := d.db.QueryRowContext(ctx, query, name)

	var role store.Role
	var rowStatus string
	err := row.Scan(
		&role.ID, &role.CreatedAt, &role.UpdatedAt, &rowStatus,
		&role.AccountID, &role.RoleID, &role.Name, &role.Job, &role.Level,
		&role.Exp, &role.Fatigue, &role.MaxFatigue, &role.MapID, &role.X, &role.Y, &role.Channel,
	)
	if err == sql.ErrNoRows {
		return nil, store.ErrNotFound
	}
	if err != nil {
		return nil, fmt.Errorf("failed to get role by name: %w", err)
	}
	role.RowStatus = store.RowStatus(rowStatus)
	return &role, nil
}

// CountRolesByAccount 获取账户的角色数量
func (d *DB) CountRolesByAccount(ctx context.Context, accountID uint64) (int, error) {
	query := `SELECT COUNT(*) FROM role WHERE account_id = ? AND row_status = 'NORMAL'`
	var count int
	err := d.db.QueryRowContext(ctx, query, accountID).Scan(&count)
	if err != nil {
		return 0, fmt.Errorf("failed to count roles: %w", err)
	}
	return count, nil
}

// ==================== 角色属性CRUD ====================

// CreateRoleAttributes 创建角色属性
func (d *DB) CreateRoleAttributes(ctx context.Context, create *store.RoleAttributes) (*store.RoleAttributes, error) {
	query := `
      INSERT INTO role_attributes (role_id, hp, max_hp, mp, max_mp, strength, intelligence, vitality, spirit,
         physical_attack, physical_defense, magic_attack, magic_defense, move_speed, attack_speed, cast_speed)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	_, err := d.db.ExecContext(ctx, query,
		create.RoleID, create.HP, create.MaxHP, create.MP, create.MaxMP,
		create.Strength, create.Intelligence, create.Vitality, create.Spirit,
		create.PhysicalAttack, create.PhysicalDefense, create.MagicAttack, create.MagicDefense,
		create.MoveSpeed, create.AttackSpeed, create.CastSpeed,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create role attributes: %w", err)
	}

	return create, nil
}

// UpdateRoleAttributes 更新角色属性
func (d *DB) UpdateRoleAttributes(ctx context.Context, update *store.UpdateRoleAttributes) error {
	var sets []string
	var args []interface{}

	if update.HP != nil {
		sets = append(sets, "hp = ?")
		args = append(args, *update.HP)
	}
	if update.MaxHP != nil {
		sets = append(sets, "max_hp = ?")
		args = append(args, *update.MaxHP)
	}
	if update.MP != nil {
		sets = append(sets, "mp = ?")
		args = append(args, *update.MP)
	}
	if update.MaxMP != nil {
		sets = append(sets, "max_mp = ?")
		args = append(args, *update.MaxMP)
	}
	if update.Strength != nil {
		sets = append(sets, "strength = ?")
		args = append(args, *update.Strength)
	}
	if update.Intelligence != nil {
		sets = append(sets, "intelligence = ?")
		args = append(args, *update.Intelligence)
	}
	if update.Vitality != nil {
		sets = append(sets, "vitality = ?")
		args = append(args, *update.Vitality)
	}
	if update.Spirit != nil {
		sets = append(sets, "spirit = ?")
		args = append(args, *update.Spirit)
	}
	if update.PhysicalAttack != nil {
		sets = append(sets, "physical_attack = ?")
		args = append(args, *update.PhysicalAttack)
	}
	if update.PhysicalDefense != nil {
		sets = append(sets, "physical_defense = ?")
		args = append(args, *update.PhysicalDefense)
	}
	if update.MagicAttack != nil {
		sets = append(sets, "magic_attack = ?")
		args = append(args, *update.MagicAttack)
	}
	if update.MagicDefense != nil {
		sets = append(sets, "magic_defense = ?")
		args = append(args, *update.MagicDefense)
	}
	if update.MoveSpeed != nil {
		sets = append(sets, "move_speed = ?")
		args = append(args, *update.MoveSpeed)
	}
	if update.AttackSpeed != nil {
		sets = append(sets, "attack_speed = ?")
		args = append(args, *update.AttackSpeed)
	}
	if update.CastSpeed != nil {
		sets = append(sets, "cast_speed = ?")
		args = append(args, *update.CastSpeed)
	}

	if len(sets) == 0 {
		return nil
	}

	args = append(args, update.RoleID)
	query := fmt.Sprintf("UPDATE role_attributes SET %s WHERE role_id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update role attributes: %w", err)
	}
	return nil
}

// GetRoleAttributes 获取角色属性
func (d *DB) GetRoleAttributes(ctx context.Context, roleID uint64) (*store.RoleAttributes, error) {
	query := `
      SELECT role_id, hp, max_hp, mp, max_mp, strength, intelligence, vitality, spirit,
         physical_attack, physical_defense, magic_attack, magic_defense, move_speed, attack_speed, cast_speed
      FROM role_attributes WHERE role_id = ?
   `

	var attrs store.RoleAttributes
	err := d.db.QueryRowContext(ctx, query, roleID).Scan(
		&attrs.RoleID, &attrs.HP, &attrs.MaxHP, &attrs.MP, &attrs.MaxMP,
		&attrs.Strength, &attrs.Intelligence, &attrs.Vitality, &attrs.Spirit,
		&attrs.PhysicalAttack, &attrs.PhysicalDefense, &attrs.MagicAttack, &attrs.MagicDefense,
		&attrs.MoveSpeed, &attrs.AttackSpeed, &attrs.CastSpeed,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to get role attributes: %w", err)
	}
	return &attrs, nil
}

// ==================== 角色货币CRUD ====================

// GetRoleCurrency 获取角色货币
func (d *DB) GetRoleCurrency(ctx context.Context, roleID uint64) (*store.RoleCurrency, error) {
	query := `SELECT role_id, gold, coin, fatigue, max_fatigue FROM role_currency WHERE role_id = ?`

	var currency store.RoleCurrency
	err := d.db.QueryRowContext(ctx, query, roleID).Scan(
		&currency.RoleID, &currency.Gold, &currency.Coin, &currency.Fatigue, &currency.MaxFatigue,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to get role currency: %w", err)
	}
	return &currency, nil
}

// UpdateRoleCurrency 更新角色货币
func (d *DB) UpdateRoleCurrency(ctx context.Context, update *store.RoleCurrency) error {
	query := `
      INSERT INTO role_currency (role_id, gold, coin, fatigue, max_fatigue)
      VALUES (?, ?, ?, ?, ?)
      ON CONFLICT(role_id) DO UPDATE SET
      gold = excluded.gold, coin = excluded.coin, fatigue = excluded.fatigue, max_fatigue = excluded.max_fatigue
   `

	_, err := d.db.ExecContext(ctx, query,
		update.RoleID, update.Gold, update.Coin, update.Fatigue, update.MaxFatigue,
	)
	if err != nil {
		return fmt.Errorf("failed to update role currency: %w", err)
	}
	return nil
}
