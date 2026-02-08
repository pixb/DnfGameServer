package mysql

import (
	"context"
	"database/sql"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 任务CRUD ====================

// CreateQuest 创建任务
func (d *DB) CreateQuest(ctx context.Context, create *store.Quest) (*store.Quest, error) {
	query := `
      INSERT INTO quest (created_at, updated_at, row_status, quest_id, name, description, type, level_required, job_required, pre_quest_id, target_type, target_id, target_count, reward_exp, reward_gold, reward_items)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.QuestID, create.Name, create.Description, create.Type, create.LevelRequired,
		create.JobRequired, create.PreQuestID, create.TargetType, create.TargetID, create.TargetCount,
		create.RewardExp, create.RewardGold, create.RewardItems,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create quest: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetQuest 获取任务
func (d *DB) GetQuest(ctx context.Context, find *store.FindQuest) (*store.Quest, error) {
	quests, err := d.ListQuests(ctx, find)
	if err != nil {
		return nil, err
	}
	if len(quests) == 0 {
		return nil, store.ErrNotFound
	}
	return quests[0], nil
}

// ListQuests 查询任务列表
func (d *DB) ListQuests(ctx context.Context, find *store.FindQuest) ([]*store.Quest, error) {
	query := `SELECT id, created_at, updated_at, row_status, quest_id, name, description, type, level_required, job_required, pre_quest_id, target_type, target_id, target_count, reward_exp, reward_gold, reward_items FROM quest`
	args := []interface{}{}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query quests: %w", err)
	}
	defer rows.Close()

	var quests []*store.Quest
	for rows.Next() {
		var q store.Quest
		err := rows.Scan(&q.ID, &q.CreatedAt, &q.UpdatedAt, &q.RowStatus,
			&q.QuestID, &q.Name, &q.Description, &q.Type, &q.LevelRequired, &q.JobRequired,
			&q.PreQuestID, &q.TargetType, &q.TargetID, &q.TargetCount,
			&q.RewardExp, &q.RewardGold, &q.RewardItems)
		if err != nil {
			return nil, fmt.Errorf("failed to scan quest: %w", err)
		}
		quests = append(quests, &q)
	}

	return quests, nil
}

// UpdateQuest 更新任务
func (d *DB) UpdateQuest(ctx context.Context, update *store.UpdateQuest) (*store.Quest, error) {
	// 简化实现，实际应该动态构建更新
	return d.GetQuest(ctx, &store.FindQuest{})
}

// ==================== 角色任务CRUD ====================

// CreateRoleQuest 创建角色任务
func (d *DB) CreateRoleQuest(ctx context.Context, create *store.RoleQuest) (*store.RoleQuest, error) {
	query := `
      INSERT INTO role_quest (created_at, updated_at, row_status, role_id, quest_id, status, progress, accepted_at, completed_at)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.RoleID, create.QuestID, create.Status, create.Progress, create.AcceptedAt, create.CompletedAt,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create role quest: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	return create, nil
}

// GetRoleQuest 获取角色任务
func (d *DB) GetRoleQuest(ctx context.Context, find *store.FindRoleQuest) (*store.RoleQuest, error) {
	var query string
	var args []interface{}

	if find.ID != nil {
		query = `SELECT id, created_at, updated_at, row_status, role_id, quest_id, status, progress, accepted_at, completed_at FROM role_quest WHERE id = ?`
		args = append(args, *find.ID)
	} else if find.RoleID != nil && find.QuestID != nil {
		query = `SELECT id, created_at, updated_at, row_status, role_id, quest_id, status, progress, accepted_at, completed_at FROM role_quest WHERE role_id = ? AND quest_id = ?`
		args = append(args, *find.RoleID, *find.QuestID)
	} else {
		return nil, store.ErrNotFound
	}

	var rq store.RoleQuest
	err := d.db.QueryRowContext(ctx, query, args...).Scan(
		&rq.ID, &rq.CreatedAt, &rq.UpdatedAt, &rq.RowStatus,
		&rq.RoleID, &rq.QuestID, &rq.Status, &rq.Progress, &rq.AcceptedAt, &rq.CompletedAt,
	)
	if err == sql.ErrNoRows {
		return nil, store.ErrNotFound
	}
	if err != nil {
		return nil, fmt.Errorf("failed to get role quest: %w", err)
	}
	return &rq, nil
}

// UpdateRoleQuest 更新角色任务
func (d *DB) UpdateRoleQuest(ctx context.Context, update *store.UpdateRoleQuest) error {
	// 简化实现
	return nil
}

// ListRoleQuests 获取角色任务列表
func (d *DB) ListRoleQuests(ctx context.Context, roleID uint64) ([]*store.RoleQuest, error) {
	query := `SELECT id, created_at, updated_at, row_status, role_id, quest_id, status, progress, accepted_at, completed_at FROM role_quest WHERE role_id = ?`

	rows, err := d.db.QueryContext(ctx, query, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to query role quests: %w", err)
	}
	defer rows.Close()

	var quests []*store.RoleQuest
	for rows.Next() {
		var q store.RoleQuest
		err := rows.Scan(&q.ID, &q.CreatedAt, &q.UpdatedAt, &q.RowStatus,
			&q.RoleID, &q.QuestID, &q.Status, &q.Progress, &q.AcceptedAt, &q.CompletedAt)
		if err != nil {
			return nil, fmt.Errorf("failed to scan role quest: %w", err)
		}
		quests = append(quests, &q)
	}

	return quests, nil
}

// DeleteRoleQuest 删除角色任务
func (d *DB) DeleteRoleQuest(ctx context.Context, delete *store.DeleteRoleQuest) error {
	query := "DELETE FROM role_quest WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete role quest: %w", err)
	}
	return nil
}
