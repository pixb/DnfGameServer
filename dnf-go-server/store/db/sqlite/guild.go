package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 公会CRUD ====================

// CreateGuild 创建公会
func (d *DB) CreateGuild(ctx context.Context, create *store.Guild) (*store.Guild, error) {
	query := `
      INSERT INTO guild (created_at, updated_at, row_status, name, level, exp, notice, leader_id, member_count, max_members, fund)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.Name, create.Level, create.Exp, create.Notice, create.LeaderID,
		create.MemberCount, create.MaxMembers, create.Fund,
	)
	if err != nil {
		if strings.Contains(err.Error(), "UNIQUE constraint failed") {
			return nil, store.ErrDuplicate
		}
		return nil, fmt.Errorf("failed to create guild: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetGuild 获取公会
func (d *DB) GetGuild(ctx context.Context, find *store.FindGuild) (*store.Guild, error) {
	guilds, err := d.ListGuilds(ctx, find)
	if err != nil {
		return nil, err
	}
	if len(guilds) == 0 {
		return nil, store.ErrNotFound
	}
	return guilds[0], nil
}

// ListGuilds 查询公会列表
func (d *DB) ListGuilds(ctx context.Context, find *store.FindGuild) ([]*store.Guild, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.Name != nil {
		where = append(where, "name = ?")
		args = append(args, *find.Name)
	}
	if find.LeaderID != nil {
		where = append(where, "leader_id = ?")
		args = append(args, *find.LeaderID)
	}

	query := `SELECT id, created_at, updated_at, row_status, name, level, exp, notice, leader_id, member_count, max_members, fund FROM guild`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query guilds: %w", err)
	}
	defer rows.Close()

	var guilds []*store.Guild
	for rows.Next() {
		var g store.Guild
		err := rows.Scan(&g.ID, &g.CreatedAt, &g.UpdatedAt, &g.RowStatus,
			&g.Name, &g.Level, &g.Exp, &g.Notice, &g.LeaderID, &g.MemberCount, &g.MaxMembers, &g.Fund)
		if err != nil {
			return nil, fmt.Errorf("failed to scan guild: %w", err)
		}
		guilds = append(guilds, &g)
	}

	return guilds, nil
}

// UpdateGuild 更新公会
func (d *DB) UpdateGuild(ctx context.Context, update *store.UpdateGuild) (*store.Guild, error) {
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
	if update.Notice != nil {
		sets = append(sets, "notice = ?")
		args = append(args, *update.Notice)
	}
	if update.LeaderID != nil {
		sets = append(sets, "leader_id = ?")
		args = append(args, *update.LeaderID)
	}
	if update.RowStatus != nil {
		sets = append(sets, "row_status = ?")
		args = append(args, *update.RowStatus)
	}

	if len(sets) == 0 {
		return d.GetGuild(ctx, &store.FindGuild{FindBase: store.FindBase{ID: &update.ID}})
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE guild SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to update guild: %w", err)
	}

	return d.GetGuild(ctx, &store.FindGuild{FindBase: store.FindBase{ID: &update.ID}})
}

// DeleteGuild 删除公会
func (d *DB) DeleteGuild(ctx context.Context, delete *store.DeleteGuild) error {
	query := "DELETE FROM guild WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete guild: %w", err)
	}
	return nil
}

// ==================== 公会成员CRUD ====================

// AddGuildMember 添加公会成员
func (d *DB) AddGuildMember(ctx context.Context, create *store.GuildMember) (*store.GuildMember, error) {
	query := `
      INSERT INTO guild_member (created_at, updated_at, row_status, guild_id, role_id, position, contribution)
      VALUES (?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.GuildID, create.RoleID, create.Position, create.Contribution,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to add guild member: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetGuildMember 获取公会成员
func (d *DB) GetGuildMember(ctx context.Context, find *store.FindGuildMember) (*store.GuildMember, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.GuildID != nil {
		where = append(where, "guild_id = ?")
		args = append(args, *find.GuildID)
	}
	if find.RoleID != nil {
		where = append(where, "role_id = ?")
		args = append(args, *find.RoleID)
	}

	query := `SELECT id, created_at, updated_at, row_status, guild_id, role_id, position, contribution FROM guild_member`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}

	var member store.GuildMember
	err := d.db.QueryRowContext(ctx, query, args...).Scan(
		&member.ID, &member.CreatedAt, &member.UpdatedAt, &member.RowStatus,
		&member.GuildID, &member.RoleID, &member.Position, &member.Contribution,
	)
	if err == sql.ErrNoRows {
		return nil, store.ErrNotFound
	}
	if err != nil {
		return nil, fmt.Errorf("failed to get guild member: %w", err)
	}
	return &member, nil
}

// UpdateGuildMember 更新公会成员
func (d *DB) UpdateGuildMember(ctx context.Context, update *store.UpdateGuildMember) error {
	var sets []string
	var args []interface{}

	if update.Position != nil {
		sets = append(sets, "position = ?")
		args = append(args, *update.Position)
	}
	if update.Contribution != nil {
		sets = append(sets, "contribution = ?")
		args = append(args, *update.Contribution)
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

	query := fmt.Sprintf("UPDATE guild_member SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update guild member: %w", err)
	}
	return nil
}

// ListGuildMembers 获取公会成员列表
func (d *DB) ListGuildMembers(ctx context.Context, guildID uint64) ([]*store.GuildMember, error) {
	query := `
      SELECT id, created_at, updated_at, row_status, guild_id, role_id, position, contribution
      FROM guild_member WHERE guild_id = ?
   `

	rows, err := d.db.QueryContext(ctx, query, guildID)
	if err != nil {
		return nil, fmt.Errorf("failed to query guild members: %w", err)
	}
	defer rows.Close()

	var members []*store.GuildMember
	for rows.Next() {
		var m store.GuildMember
		err := rows.Scan(&m.ID, &m.CreatedAt, &m.UpdatedAt, &m.RowStatus,
			&m.GuildID, &m.RoleID, &m.Position, &m.Contribution)
		if err != nil {
			return nil, fmt.Errorf("failed to scan guild member: %w", err)
		}
		members = append(members, &m)
	}

	return members, nil
}

// RemoveGuildMember 移除公会成员
func (d *DB) RemoveGuildMember(ctx context.Context, delete *store.DeleteGuildMember) error {
	query := "DELETE FROM guild_member WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to remove guild member: %w", err)
	}
	return nil
}
