package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"time"

	_ "github.com/mattn/go-sqlite3"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 成就相关 ====================

// GetAchievements 获取成就信息
func (d *DB) GetAchievements(ctx context.Context, roleID uint64, queryType int32) ([]*store.AchievementInfo, error) {
	query := `
		SELECT ar.achievement_id, ac.name, ac.description, ar.progress, ac.target_value, ar.completed, ar.rewarded
		FROM t_achievement_record ar
		LEFT JOIN t_achievement_config ac ON ar.achievement_id = ac.achievement_id
		WHERE ar.role_id = ?
	`
	args := []interface{}{roleID}

	switch queryType {
	case 1:
	case 2:
		query += " AND ar.completed = ?"
		args = append(args, true)
	case 3:
		query += " AND ar.completed = ?"
		args = append(args, false)
	default:
		return nil, fmt.Errorf("invalid query type: %d", queryType)
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query achievements: %w", err)
	}
	defer rows.Close()

	var achievements []*store.AchievementInfo
	for rows.Next() {
		var a store.AchievementInfo
		err := rows.Scan(&a.AchievementID, &a.Name, &a.Description, &a.Progress, &a.TargetValue, &a.Completed, &a.Rewarded)
		if err != nil {
			return nil, fmt.Errorf("failed to scan achievement: %w", err)
		}
		achievements = append(achievements, &a)
	}

	return achievements, nil
}

// ClaimAchievementReward 领取成就奖励
func (d *DB) ClaimAchievementReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32) (*store.AchievementRewardResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	var completed, rewarded bool
	err = tx.QueryRowContext(ctx, "SELECT completed, rewarded FROM t_achievement_record WHERE role_id = ? AND achievement_id = ?", roleID, achievementID).Scan(&completed, &rewarded)
	if err == sql.ErrNoRows {
		return nil, fmt.Errorf("achievement not found")
	}
	if err != nil {
		return nil, fmt.Errorf("failed to query achievement: %w", err)
	}

	if !completed {
		return nil, fmt.Errorf("achievement not completed")
	}

	if rewarded {
		return nil, fmt.Errorf("achievement reward already claimed")
	}

	now := time.Now()
	_, err = tx.ExecContext(ctx, "UPDATE t_achievement_record SET rewarded = ?, reward_time = ? WHERE role_id = ? AND achievement_id = ?", true, now, roleID, achievementID)
	if err != nil {
		return nil, fmt.Errorf("failed to update achievement record: %w", err)
	}

	var adventureUnionLevel int32
	var adventureUnionExp uint64
	err = tx.QueryRowContext(ctx, "SELECT adventureunionlevel, adventureunionexp FROM t_adventure_data WHERE role_id = ?", roleID).Scan(&adventureUnionLevel, &adventureUnionExp)
	if err == sql.ErrNoRows {
		adventureUnionLevel = 1
		adventureUnionExp = 0
	} else if err != nil {
		return nil, fmt.Errorf("failed to query adventure data: %w", err)
	}

	newExp := adventureUnionExp + 100
	_, err = tx.ExecContext(ctx, "UPDATE t_adventure_data SET adventureunionexp = ? WHERE role_id = ?", newExp, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to update adventure exp: %w", err)
	}

	if err := tx.Commit(); err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.AchievementRewardResult{
		AdventureUnionLevel: adventureUnionLevel,
		AdventureUnionExp:   newExp,
		ConsumeItems:        []*dnfv1.StackableItem{},
		InvenItems:          &dnfv1.PT_ITEMS{},
	}, nil
}

// GetAchievementList 获取成就列表
func (d *DB) GetAchievementList(ctx context.Context, roleID uint64, queryType int32) (*store.AchievementListResult, error) {
	achievements, err := d.GetAchievements(ctx, roleID, queryType)
	if err != nil {
		return nil, err
	}

	return &store.AchievementListResult{
		Achievements: achievements,
		Total:        int32(len(achievements)),
	}, nil
}

// ClaimAchievementBonusReward 领取成就额外奖励
func (d *DB) ClaimAchievementBonusReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32, rewardIndex uint32, rewardCount uint32) (*store.AchievementBonusRewardResult, error) {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return nil, fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	var completed, rewarded bool
	err = tx.QueryRowContext(ctx, "SELECT completed, rewarded FROM t_achievement_record WHERE role_id = ? AND achievement_id = ?", roleID, achievementID).Scan(&completed, &rewarded)
	if err == sql.ErrNoRows {
		return nil, fmt.Errorf("achievement not found")
	}
	if err != nil {
		return nil, fmt.Errorf("failed to query achievement: %w", err)
	}

	if !completed {
		return nil, fmt.Errorf("achievement not completed")
	}

	if !rewarded {
		return nil, fmt.Errorf("achievement reward not claimed yet")
	}

	var claimed bool
	err = tx.QueryRowContext(ctx, "SELECT claimed FROM t_achievement_reward WHERE role_id = ? AND achievement_id = ? AND reward_type = ? AND reward_index = ?", roleID, achievementID, rewardType, rewardIndex).Scan(&claimed)
	if err == nil && claimed {
		return nil, fmt.Errorf("bonus reward already claimed")
	}

	now := time.Now()
	_, err = tx.ExecContext(ctx, "INSERT INTO t_achievement_reward (role_id, achievement_id, reward_type, reward_index, reward_count, claimed, claim_time, create_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", roleID, achievementID, rewardType, rewardIndex, rewardCount, true, now, now)
	if err != nil {
		return nil, fmt.Errorf("failed to insert achievement reward: %w", err)
	}

	if err := tx.Commit(); err != nil {
		return nil, fmt.Errorf("failed to commit transaction: %w", err)
	}

	return &store.AchievementBonusRewardResult{
		InvenItems: &dnfv1.PT_ITEMS{},
	}, nil
}
