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

// CreatePvpMatchType 创建 PK 匹配类型
func (d *DB) CreatePvpMatchType(ctx context.Context, t *store.PvpMatchType) error {
	now := time.Now().Unix()
	query := "INSERT INTO t_pvp_match_type (created_at, updated_at, row_status, match_type, type_name, min_level, max_level, min_players, max_players, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	if _, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal, t.MatchType, t.TypeName, t.MinLevel,
		t.MaxLevel, t.MinPlayers, t.MaxPlayers, t.Status); err != nil {
		return fmt.Errorf("failed to create pvp match type: %w", err)
	}
	return nil
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

// ==================== PK 匹配 / 入场 / 排名 / 历史 ====================

// CreatePvpMatching 创建 PK 匹配记录
func (d *DB) CreatePvpMatching(ctx context.Context, m *store.PvpMatching) (*store.PvpMatching, error) {
	now := time.Now().Unix()
	query := "INSERT INTO t_pvp_matching (created_at, updated_at, row_status, matching_id, role_id, match_type, status) VALUES (?, ?, ?, ?, ?, ?, ?)"
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal, m.MatchingID, m.RoleID, m.MatchType, m.Status)
	if err != nil {
		return nil, fmt.Errorf("failed to create pvp matching: %w", err)
	}
	id, _ := result.LastInsertId()
	m.ID = uint64(id)
	m.CreatedAt = now
	m.UpdatedAt = now
	m.RowStatus = store.RowStatusNormal
	return m, nil
}

// UpdatePvpMatchingStatus 更新匹配状态
func (d *DB) UpdatePvpMatchingStatus(ctx context.Context, matchingID uint64, status uint32) error {
	query := "UPDATE t_pvp_matching SET status = ?, updated_at = ? WHERE matching_id = ?"
	if _, err := d.db.ExecContext(ctx, query, status, time.Now().Unix(), matchingID); err != nil {
		return fmt.Errorf("failed to update pvp matching status: %w", err)
	}
	return nil
}

// ListPvpMatchingsByRole 查询角色匹配记录(按状态)
func (d *DB) ListPvpMatchingsByRole(ctx context.Context, roleID uint64, status uint32) ([]*store.PvpMatching, error) {
	query := "SELECT id, created_at, updated_at, row_status, matching_id, role_id, match_type, status FROM t_pvp_matching WHERE role_id = ? AND status = ?"
	rows, err := d.db.QueryContext(ctx, query, roleID, status)
	if err != nil {
		return nil, fmt.Errorf("failed to query pvp matchings: %w", err)
	}
	defer rows.Close()

	var matchings []*store.PvpMatching
	for rows.Next() {
		var m store.PvpMatching
		var rowStatus string
		if err := rows.Scan(&m.ID, &m.CreatedAt, &m.UpdatedAt, &rowStatus,
			&m.MatchingID, &m.RoleID, &m.MatchType, &m.Status); err != nil {
			return nil, fmt.Errorf("failed to scan pvp matching: %w", err)
		}
		m.RowStatus = store.RowStatus(rowStatus)
		matchings = append(matchings, &m)
	}
	return matchings, rows.Err()
}

// ListRaidEntrances 查询角色副本入场记录
func (d *DB) ListRaidEntrances(ctx context.Context, roleID uint64) ([]*store.RaidEntrance, error) {
	query := "SELECT id, created_at, updated_at, row_status, role_id, raid_index, daily_character_count, character_count, account_count, daily_reward_count, reward_count, last_enter_time FROM t_raid_entrance WHERE role_id = ?"
	rows, err := d.db.QueryContext(ctx, query, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to query raid entrances: %w", err)
	}
	defer rows.Close()

	var entrances []*store.RaidEntrance
	for rows.Next() {
		var e store.RaidEntrance
		var rowStatus string
		if err := rows.Scan(&e.ID, &e.CreatedAt, &e.UpdatedAt, &rowStatus,
			&e.RoleID, &e.RaidIndex, &e.DailyCharacterCount, &e.CharacterCount,
			&e.AccountCount, &e.DailyRewardCount, &e.RewardCount, &e.LastEnterTime); err != nil {
			return nil, fmt.Errorf("failed to scan raid entrance: %w", err)
		}
		e.RowStatus = store.RowStatus(rowStatus)
		entrances = append(entrances, &e)
	}
	return entrances, rows.Err()
}

// UpsertRaidEntrance 创建/更新副本入场记录
func (d *DB) UpsertRaidEntrance(ctx context.Context, e *store.RaidEntrance) error {
	now := time.Now().Unix()
	query := "INSERT INTO t_raid_entrance (created_at, updated_at, row_status, role_id, raid_index, daily_character_count, character_count, account_count, daily_reward_count, reward_count, last_enter_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE updated_at = VALUES(updated_at), daily_character_count = VALUES(daily_character_count), character_count = VALUES(character_count), account_count = VALUES(account_count), daily_reward_count = VALUES(daily_reward_count), reward_count = VALUES(reward_count), last_enter_time = VALUES(last_enter_time)"
	if _, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal, e.RoleID, e.RaidIndex, e.DailyCharacterCount,
		e.CharacterCount, e.AccountCount, e.DailyRewardCount, e.RewardCount, e.LastEnterTime); err != nil {
		return fmt.Errorf("failed to upsert raid entrance: %w", err)
	}
	return nil
}

// ResetDailyRaidEntrances 重置角色副本每日入场/奖励计数
func (d *DB) ResetDailyRaidEntrances(ctx context.Context, roleID uint64) error {
	query := "UPDATE t_raid_entrance SET daily_character_count = 0, daily_reward_count = 0, updated_at = ? WHERE role_id = ?"
	if _, err := d.db.ExecContext(ctx, query, time.Now().Unix(), roleID); err != nil {
		return fmt.Errorf("failed to reset daily raid entrances: %w", err)
	}
	return nil
}

// ListPvpRanking 查询 PK 排名(按总积分倒序)
func (d *DB) ListPvpRanking(ctx context.Context, limit int) ([]*store.PvpRankingEntry, error) {
	query := "SELECT s.role_id, r.name, r.level, r.job, s.total_score, s.win_count, s.lose_count FROM t_pvp_stats s JOIN role r ON r.id = s.role_id AND r.row_status = 'NORMAL' ORDER BY s.total_score DESC LIMIT ?"
	rows, err := d.db.QueryContext(ctx, query, limit)
	if err != nil {
		return nil, fmt.Errorf("failed to query pvp ranking: %w", err)
	}
	defer rows.Close()

	var entries []*store.PvpRankingEntry
	for rows.Next() {
		var e store.PvpRankingEntry
		var score int32
		if err := rows.Scan(&e.RoleID, &e.Name, &e.Level, &e.Job, &score, &e.WinCount, &e.LoseCount); err != nil {
			return nil, fmt.Errorf("failed to scan pvp ranking: %w", err)
		}
		e.Score = score
		entries = append(entries, &e)
	}
	if err := rows.Err(); err != nil {
		return nil, err
	}
	for i, e := range entries {
		e.Rank = uint32(i + 1)
	}
	return entries, nil
}

// ListPvpMatchHistory 查询角色 PK 匹配历史(含对手名)
func (d *DB) ListPvpMatchHistory(ctx context.Context, roleID uint64, limit int) ([]*store.PvpMatchHistoryEntry, error) {
	query := "SELECT r.id, r.role_id, r.match_type, r.win, r.score, r.opponent_id, o.name, r.battle_time FROM t_pvp_record r LEFT JOIN role o ON o.id = r.opponent_id AND o.row_status = 'NORMAL' WHERE r.role_id = ? ORDER BY r.battle_time DESC LIMIT ?"
	rows, err := d.db.QueryContext(ctx, query, roleID, limit)
	if err != nil {
		return nil, fmt.Errorf("failed to query pvp match history: %w", err)
	}
	defer rows.Close()

	var entries []*store.PvpMatchHistoryEntry
	for rows.Next() {
		var e store.PvpMatchHistoryEntry
		var opponentName sql.NullString
		var opponentID uint64
		if err := rows.Scan(&e.ID, &e.RoleID, &e.MatchType, &e.Win, &e.Score,
			&opponentID, &opponentName, &e.BattleTime); err != nil {
			return nil, fmt.Errorf("failed to scan pvp match history: %w", err)
		}
		if opponentName.Valid {
			e.OpponentName = opponentName.String
		}
		entries = append(entries, &e)
	}
	return entries, rows.Err()
}
