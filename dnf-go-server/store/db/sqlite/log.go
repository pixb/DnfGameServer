package sqlite

import (
	"context"
	"fmt"
	"strings"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 行为日志 ====================
// 2026-09-06 第十九轮: rank/log TCP handler 实化——日志模块落库, 与 mysql 驱动对称。

// RecordBehaviorLog 记录行为日志
func (d *DB) RecordBehaviorLog(ctx context.Context, log *store.BehaviorLog) error {
	_, err := d.db.ExecContext(ctx, `
		INSERT INTO t_behavior_log (created_at, role_id, module, action, level, content)
		VALUES (?, ?, ?, ?, ?, ?)`,
		log.CreatedAt, log.RoleID, log.Module, log.Action, log.Level, log.Content)
	if err != nil {
		return fmt.Errorf("failed to record behavior log: %w", err)
	}
	return nil
}

// ListBehaviorLogs 查询行为日志(按创建时间倒序, limit 限制, 缺省 10)
func (d *DB) ListBehaviorLogs(ctx context.Context, roleID uint64, limit int) ([]*store.BehaviorLog, error) {
	if limit <= 0 {
		limit = 10
	}
	rows, err := d.db.QueryContext(ctx, `
		SELECT id, created_at, role_id, module, action, level, content FROM t_behavior_log
		WHERE role_id = ? ORDER BY created_at DESC, id DESC LIMIT ?`, roleID, limit)
	if err != nil {
		return nil, fmt.Errorf("failed to list behavior logs: %w", err)
	}
	defer rows.Close()

	var logs []*store.BehaviorLog
	for rows.Next() {
		var l store.BehaviorLog
		if err := rows.Scan(&l.ID, &l.CreatedAt, &l.RoleID, &l.Module, &l.Action, &l.Level, &l.Content); err != nil {
			return nil, fmt.Errorf("failed to scan behavior log: %w", err)
		}
		logs = append(logs, &l)
	}
	return logs, rows.Err()
}

// StatisticBehaviorLogs 按动作统计行为日志数
func (d *DB) StatisticBehaviorLogs(ctx context.Context, roleID uint64) (map[string]int64, error) {
	rows, err := d.db.QueryContext(ctx, `
		SELECT action, COUNT(*) FROM t_behavior_log WHERE role_id = ? GROUP BY action`, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to statistic behavior logs: %w", err)
	}
	defer rows.Close()

	stats := map[string]int64{}
	for rows.Next() {
		var action string
		var cnt int64
		if err := rows.Scan(&action, &cnt); err != nil {
			return nil, fmt.Errorf("failed to scan behavior log statistic: %w", err)
		}
		stats[action] = cnt
	}
	return stats, rows.Err()
}

// DeleteBehaviorLogs 按ID删除行为日志, 返回删除行数
func (d *DB) DeleteBehaviorLogs(ctx context.Context, ids []uint64) (int64, error) {
	if len(ids) == 0 {
		return 0, nil
	}
	placeholders := make([]string, len(ids))
	args := make([]interface{}, len(ids))
	for i, id := range ids {
		placeholders[i] = "?"
		args[i] = id
	}
	result, err := d.db.ExecContext(ctx, `DELETE FROM t_behavior_log WHERE id IN (`+strings.Join(placeholders, ",")+`)`, args...)
	if err != nil {
		return 0, fmt.Errorf("failed to delete behavior logs: %w", err)
	}
	n, _ := result.RowsAffected()
	return n, nil
}

// CleanBehaviorLogs 清理指定时间之前的行为日志, 返回删除行数
func (d *DB) CleanBehaviorLogs(ctx context.Context, before int64) (int64, error) {
	result, err := d.db.ExecContext(ctx, `DELETE FROM t_behavior_log WHERE created_at < ?`, before)
	if err != nil {
		return 0, fmt.Errorf("failed to clean behavior logs: %w", err)
	}
	n, _ := result.RowsAffected()
	return n, nil
}
