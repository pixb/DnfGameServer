package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 活动配置CRUD ====================

// CreateEventConfig 创建活动配置
func (d *DB) CreateEventConfig(ctx context.Context, create *store.EventConfig) (*store.EventConfig, error) {
	query := `
      INSERT INTO t_event_config (created_at, updated_at, row_status, event_id, title, description, event_type, status, start_time, end_time, reward_config)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.EventID, create.Title, create.Description, create.EventType,
		create.Status, create.StartTime, create.EndTime, create.RewardConfig,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create event config: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// UpdateEventConfig 更新活动配置
func (d *DB) UpdateEventConfig(ctx context.Context, update *store.UpdateEventConfig) error {
	var sets []string
	var args []interface{}

	if update.Title != nil {
		sets = append(sets, "title = ?")
		args = append(args, *update.Title)
	}
	if update.Description != nil {
		sets = append(sets, "description = ?")
		args = append(args, *update.Description)
	}
	if update.EventType != nil {
		sets = append(sets, "event_type = ?")
		args = append(args, *update.EventType)
	}
	if update.Status != nil {
		sets = append(sets, "status = ?")
		args = append(args, *update.Status)
	}
	if update.StartTime != nil {
		sets = append(sets, "start_time = ?")
		args = append(args, *update.StartTime)
	}
	if update.EndTime != nil {
		sets = append(sets, "end_time = ?")
		args = append(args, *update.EndTime)
	}
	if update.RewardConfig != nil {
		sets = append(sets, "reward_config = ?")
		args = append(args, *update.RewardConfig)
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

	_, err := d.db.ExecContext(ctx, "UPDATE t_event_config SET "+strings.Join(sets, ", ")+" WHERE id = ?", args...)
	if err != nil {
		return fmt.Errorf("failed to update event config: %w", err)
	}
	return nil
}

// GetEventConfig 获取活动配置
func (d *DB) GetEventConfig(ctx context.Context, find *store.FindEventConfig) (*store.EventConfig, error) {
	query := `
      SELECT id, created_at, updated_at, row_status, event_id, title, description, event_type, status, start_time, end_time, reward_config
      FROM t_event_config
      WHERE row_status = 'NORMAL'
   `
	var args []interface{}
	cond, args := buildEventConfigCond(find, args)

	if len(cond) > 0 {
		query += " AND " + strings.Join(cond, " AND ")
	}
	query += " LIMIT 1"

	row := d.db.QueryRowContext(ctx, query, args...)
	e, err := scanEventConfig(row)
	if err != nil {
		return nil, err
	}
	return e, nil
}

// ListEventConfigs 获取活动配置列表
func (d *DB) ListEventConfigs(ctx context.Context, find *store.FindEventConfig) ([]*store.EventConfig, error) {
	query := `
      SELECT id, created_at, updated_at, row_status, event_id, title, description, event_type, status, start_time, end_time, reward_config
      FROM t_event_config
      WHERE row_status = 'NORMAL'
   `
	var args []interface{}
	cond, args := buildEventConfigCond(find, args)

	if len(cond) > 0 {
		query += " AND " + strings.Join(cond, " AND ")
	}
	query += " ORDER BY id ASC"

	if find != nil && find.Limit != nil {
		query += " LIMIT ?"
		args = append(args, *find.Limit)
		if find.Offset != nil {
			query += " OFFSET ?"
			args = append(args, *find.Offset)
		}
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to list event configs: %w", err)
	}
	defer rows.Close()

	var list []*store.EventConfig
	for rows.Next() {
		e := &store.EventConfig{}
		if err := rows.Scan(&e.ID, &e.CreatedAt, &e.UpdatedAt, &e.RowStatus,
			&e.EventID, &e.Title, &e.Description, &e.EventType, &e.Status,
			&e.StartTime, &e.EndTime, &e.RewardConfig); err != nil {
			return nil, fmt.Errorf("failed to scan event config: %w", err)
		}
		list = append(list, e)
	}
	return list, rows.Err()
}

// DeleteEventConfig 删除活动配置
func (d *DB) DeleteEventConfig(ctx context.Context, id uint64) error {
	now := time.Now().Unix()
	_, err := d.db.ExecContext(ctx, `
      UPDATE t_event_config SET row_status = ?, updated_at = ? WHERE id = ?
   `, store.RowStatusDeleted, now, id)
	if err != nil {
		return fmt.Errorf("failed to delete event config: %w", err)
	}
	return nil
}

// ==================== 活动进度 ====================

// GetEventProgress 获取活动进度
func (d *DB) GetEventProgress(ctx context.Context, find *store.FindEventProgress) (*store.EventProgress, error) {
	query := `
      SELECT id, created_at, updated_at, row_status, role_id, event_id, progress_type, progress_value, status
      FROM t_event_progress
      WHERE row_status = 'NORMAL' AND role_id = ?
   `
	var args []interface{}
	args = append(args, find.RoleID)

	if find.EventID != nil {
		query += " AND event_id = ?"
		args = append(args, *find.EventID)
	}
	if find.ProgressType != nil {
		query += " AND progress_type = ?"
		args = append(args, *find.ProgressType)
	}
	query += " LIMIT 1"

	row := d.db.QueryRowContext(ctx, query, args...)
	p := &store.EventProgress{}
	if err := row.Scan(&p.ID, &p.CreatedAt, &p.UpdatedAt, &p.RowStatus,
		&p.RoleID, &p.EventID, &p.ProgressType, &p.ProgressValue, &p.Status); err != nil {
		return nil, fmt.Errorf("failed to get event progress: %w", err)
	}
	return p, nil
}

// ListEventProgress 获取角色活动进度列表
func (d *DB) ListEventProgress(ctx context.Context, roleID uint64) ([]*store.EventProgress, error) {
	rows, err := d.db.QueryContext(ctx, `
      SELECT id, created_at, updated_at, row_status, role_id, event_id, progress_type, progress_value, status
      FROM t_event_progress
      WHERE row_status = 'NORMAL' AND role_id = ?
      ORDER BY event_id ASC, progress_type ASC
   `, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to list event progress: %w", err)
	}
	defer rows.Close()

	var list []*store.EventProgress
	for rows.Next() {
		p := &store.EventProgress{}
		if err := rows.Scan(&p.ID, &p.CreatedAt, &p.UpdatedAt, &p.RowStatus,
			&p.RoleID, &p.EventID, &p.ProgressType, &p.ProgressValue, &p.Status); err != nil {
			return nil, fmt.Errorf("failed to scan event progress: %w", err)
		}
		list = append(list, p)
	}
	return list, rows.Err()
}

// UpsertEventProgress 新增/更新活动进度(按 role_id+event_id+progress_type 唯一)
func (d *DB) UpsertEventProgress(ctx context.Context, p *store.EventProgress) (*store.EventProgress, error) {
	now := time.Now().Unix()
	// sqlite 无 ON DUPLICATE KEY,先查后插/更
	existing, err := d.GetEventProgress(ctx, &store.FindEventProgress{
		RoleID:       p.RoleID,
		EventID:      &p.EventID,
		ProgressType: &p.ProgressType,
	})
	if err != nil && !isNoRows(err) {
		return nil, fmt.Errorf("failed to query event progress: %w", err)
	}
	if existing != nil {
		_, err = d.db.ExecContext(ctx, `
         UPDATE t_event_progress SET progress_value = ?, status = ?, updated_at = ? WHERE id = ?
      `, p.ProgressValue, p.Status, now, existing.ID)
		if err != nil {
			return nil, fmt.Errorf("failed to update event progress: %w", err)
		}
		p.ID = existing.ID
	} else {
		result, err := d.db.ExecContext(ctx, `
         INSERT INTO t_event_progress (created_at, updated_at, row_status, role_id, event_id, progress_type, progress_value, status)
         VALUES (?, ?, ?, ?, ?, ?, ?, ?)
      `, now, now, store.RowStatusNormal, p.RoleID, p.EventID, p.ProgressType, p.ProgressValue, p.Status)
		if err != nil {
			return nil, fmt.Errorf("failed to insert event progress: %w", err)
		}
		id, _ := result.LastInsertId()
		p.ID = uint64(id)
	}
	p.CreatedAt = now
	p.UpdatedAt = now
	p.RowStatus = store.RowStatusNormal
	return p, nil
}

// UpdateEventProgress 更新活动进度
func (d *DB) UpdateEventProgress(ctx context.Context, update *store.UpdateEventProgress) error {
	var sets []string
	var args []interface{}

	if update.ProgressValue != nil {
		sets = append(sets, "progress_value = ?")
		args = append(args, *update.ProgressValue)
	}
	if update.Status != nil {
		sets = append(sets, "status = ?")
		args = append(args, *update.Status)
	}
	if len(sets) == 0 {
		return nil
	}
	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.RoleID, update.ID)

	_, err := d.db.ExecContext(ctx, "UPDATE t_event_progress SET "+strings.Join(sets, ", ")+" WHERE role_id = ? AND id = ?", args...)
	if err != nil {
		return fmt.Errorf("failed to update event progress: %w", err)
	}
	return nil
}

// buildEventConfigCond 构建活动配置查询条件
func buildEventConfigCond(find *store.FindEventConfig, args []interface{}) ([]string, []interface{}) {
	var cond []string
	if find == nil {
		return cond, args
	}
	if find.EventID != nil {
		cond = append(cond, "event_id = ?")
		args = append(args, *find.EventID)
	}
	if find.EventType != nil {
		cond = append(cond, "event_type = ?")
		args = append(args, *find.EventType)
	}
	if find.Status != nil {
		cond = append(cond, "status = ?")
		args = append(args, *find.Status)
	}
	if find.Running != nil && *find.Running {
		now := time.Now().Unix()
		cond = append(cond, "start_time <= ? AND end_time >= ? AND status = ?")
		args = append(args, now, now, store.EventStatusRunning)
	}
	return cond, args
}

func scanEventConfig(row rowScanner) (*store.EventConfig, error) {
	e := &store.EventConfig{}
	if err := row.Scan(&e.ID, &e.CreatedAt, &e.UpdatedAt, &e.RowStatus,
		&e.EventID, &e.Title, &e.Description, &e.EventType, &e.Status,
		&e.StartTime, &e.EndTime, &e.RewardConfig); err != nil {
		return nil, fmt.Errorf("failed to scan event config: %w", err)
	}
	return e, nil
}

type rowScanner interface {
	Scan(dest ...interface{}) error
}

func isNoRows(err error) bool {
	return err == sql.ErrNoRows
}
