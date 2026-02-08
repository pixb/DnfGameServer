package mysql

import (
	"context"
	"fmt"
	"strings"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

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
      ON DUPLICATE KEY UPDATE
      gold = VALUES(gold), coin = VALUES(coin), fatigue = VALUES(fatigue), max_fatigue = VALUES(max_fatigue)
   `

	_, err := d.db.ExecContext(ctx, query,
		update.RoleID, update.Gold, update.Coin, update.Fatigue, update.MaxFatigue,
	)
	if err != nil {
		return fmt.Errorf("failed to update role currency: %w", err)
	}
	return nil
}
