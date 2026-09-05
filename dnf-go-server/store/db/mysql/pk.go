package mysql

import (
	"context"
	"database/sql"
	"errors"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== PK 相关 ====================

const pvpRecordCols = "id, created_at, updated_at, row_status, role_id, match_type, win, score, opponent_id, battle_time"
const pvpStatsCols = "id, created_at, updated_at, row_status, role_id, total_matches, win_count, lose_count, total_score, max_win_streak, current_streak"

// ListPvpRecords 查询角色 PK 记录(按战斗时间倒序)
func (d *DB) ListPvpRecords(ctx context.Context, roleID uint64, limit int) ([]*store.PvpRecord, error) {
	query := "SELECT " + pvpRecordCols + " FROM t_pvp_record WHERE role_id = ? ORDER BY battle_time DESC LIMIT ?"
	rows, err := d.db.QueryContext(ctx, query, roleID, limit)
	if err != nil {
		return nil, fmt.Errorf("failed to query pvp records: %w", err)
	}
	defer rows.Close()

	var records []*store.PvpRecord
	for rows.Next() {
		var r store.PvpRecord
		var rowStatus string
		var win bool
		if err := rows.Scan(&r.ID, &r.CreatedAt, &r.UpdatedAt, &rowStatus, &r.RoleID,
			&r.MatchType, &win, &r.Score, &r.OpponentID, &r.BattleTime); err != nil {
			return nil, fmt.Errorf("failed to scan pvp record: %w", err)
		}
		r.RowStatus = store.RowStatus(rowStatus)
		r.Win = win
		records = append(records, &r)
	}
	return records, rows.Err()
}

// GetPvpStats 获取角色 PK 统计
func (d *DB) GetPvpStats(ctx context.Context, roleID uint64) (*store.PvpStats, error) {
	query := "SELECT " + pvpStatsCols + " FROM t_pvp_stats WHERE role_id = ?"
	row := d.db.QueryRowContext(ctx, query, roleID)

	var s store.PvpStats
	var rowStatus string
	if err := row.Scan(&s.ID, &s.CreatedAt, &s.UpdatedAt, &rowStatus, &s.RoleID,
		&s.TotalMatches, &s.WinCount, &s.LoseCount, &s.TotalScore,
		&s.MaxWinStreak, &s.CurrentStreak); err != nil {
		if errors.Is(err, sql.ErrNoRows) {
			return nil, store.ErrNotFound
		}
		return nil, fmt.Errorf("failed to scan pvp stats: %w", err)
	}
	s.RowStatus = store.RowStatus(rowStatus)
	return &s, nil
}

// GetActivePvpSeason 获取进行中的 PK 赛季
func (d *DB) GetActivePvpSeason(ctx context.Context) (*store.PvpSeason, error) {
	query := "SELECT id, created_at, updated_at, row_status, season_id, season_name, start_time, end_time, status FROM t_pvp_season WHERE status = 1 ORDER BY season_id DESC LIMIT 1"
	row := d.db.QueryRowContext(ctx, query)

	var s store.PvpSeason
	var rowStatus string
	if err := row.Scan(&s.ID, &s.CreatedAt, &s.UpdatedAt, &rowStatus,
		&s.SeasonID, &s.SeasonName, &s.StartTime, &s.EndTime, &s.Status); err != nil {
		if errors.Is(err, sql.ErrNoRows) {
			return nil, store.ErrNotFound
		}
		return nil, fmt.Errorf("failed to scan pvp season: %w", err)
	}
	s.RowStatus = store.RowStatus(rowStatus)
	return &s, nil
}

// ListPvpRewards 查询角色 PK 奖励
func (d *DB) ListPvpRewards(ctx context.Context, roleID uint64) ([]*store.PvpReward, error) {
	query := "SELECT id, created_at, updated_at, row_status, role_id, reward_id, reward_name, count, claimed FROM t_pvp_reward WHERE role_id = ?"
	rows, err := d.db.QueryContext(ctx, query, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to query pvp rewards: %w", err)
	}
	defer rows.Close()

	var rewards []*store.PvpReward
	for rows.Next() {
		var r store.PvpReward
		var rowStatus string
		var claimed bool
		if err := rows.Scan(&r.ID, &r.CreatedAt, &r.UpdatedAt, &rowStatus,
			&r.RoleID, &r.RewardID, &r.RewardName, &r.Count, &claimed); err != nil {
			return nil, fmt.Errorf("failed to scan pvp reward: %w", err)
		}
		r.RowStatus = store.RowStatus(rowStatus)
		r.Claimed = claimed
		rewards = append(rewards, &r)
	}
	return rewards, rows.Err()
}

// ListPvpMatchTypes 查询启用的 PK 匹配类型
func (d *DB) ListPvpMatchTypes(ctx context.Context) ([]*store.PvpMatchType, error) {
	query := "SELECT id, created_at, updated_at, row_status, match_type, type_name, min_level, max_level, min_players, max_players, status FROM t_pvp_match_type WHERE status = 1"
	rows, err := d.db.QueryContext(ctx, query)
	if err != nil {
		return nil, fmt.Errorf("failed to query pvp match types: %w", err)
	}
	defer rows.Close()

	var types []*store.PvpMatchType
	for rows.Next() {
		var t store.PvpMatchType
		var rowStatus string
		if err := rows.Scan(&t.ID, &t.CreatedAt, &t.UpdatedAt, &rowStatus,
			&t.MatchType, &t.TypeName, &t.MinLevel, &t.MaxLevel,
			&t.MinPlayers, &t.MaxPlayers, &t.Status); err != nil {
			return nil, fmt.Errorf("failed to scan pvp match type: %w", err)
		}
		t.RowStatus = store.RowStatus(rowStatus)
		types = append(types, &t)
	}
	return types, rows.Err()
}

// SubmitPvpBattleResult 提交 PK 战斗结果(记录 + 统计,事务内)
func (d *DB) SubmitPvpBattleResult(ctx context.Context, record *store.PvpRecord, stats *store.PvpStats) error {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return fmt.Errorf("failed to begin tx: %w", err)
	}
	defer tx.Rollback()

	now := time.Now().Unix()

	// 1. 插入 PK 记录
	if _, err := tx.ExecContext(ctx,
		"INSERT INTO t_pvp_record (created_at, updated_at, role_id, match_type, win, score, opponent_id, battle_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
		now, now, record.RoleID, record.MatchType, record.Win, record.Score, record.OpponentID, record.BattleTime); err != nil {
		return fmt.Errorf("failed to create pvp record: %w", err)
	}

	// 2. 更新统计(存在则累加,不存在则创建)
	var existingID uint64
	err = tx.QueryRowContext(ctx, "SELECT id FROM t_pvp_stats WHERE role_id = ?", stats.RoleID).Scan(&existingID)
	switch {
	case err == nil:
		if _, err := tx.ExecContext(ctx,
			"UPDATE t_pvp_stats SET total_matches = ?, win_count = ?, lose_count = ?, total_score = ?, max_win_streak = ?, current_streak = ?, updated_at = ? WHERE role_id = ?",
			stats.TotalMatches, stats.WinCount, stats.LoseCount, stats.TotalScore,
			stats.MaxWinStreak, stats.CurrentStreak, now, stats.RoleID); err != nil {
			return fmt.Errorf("failed to update pvp stats: %w", err)
		}
	case errors.Is(err, sql.ErrNoRows):
		if _, err := tx.ExecContext(ctx,
			"INSERT INTO t_pvp_stats (created_at, updated_at, role_id, total_matches, win_count, lose_count, total_score, max_win_streak, current_streak) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
			now, now, stats.RoleID, stats.TotalMatches, stats.WinCount, stats.LoseCount,
			stats.TotalScore, stats.MaxWinStreak, stats.CurrentStreak); err != nil {
			return fmt.Errorf("failed to create pvp stats: %w", err)
		}
	default:
		return fmt.Errorf("failed to query pvp stats: %w", err)
	}

	return tx.Commit()
}
