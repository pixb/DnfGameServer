-- ============================================
-- 2.0.6 增量迁移：活动模块建表
-- 2026-09-06：与 mysql 2.0.6 对齐,活动配置/进度落库。
-- ============================================

-- 活动配置表
CREATE TABLE IF NOT EXISTS t_event_config (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    event_id INTEGER NOT NULL,
    title TEXT NOT NULL DEFAULT '',
    description TEXT,
    event_type INTEGER NOT NULL DEFAULT 0,
    status INTEGER NOT NULL DEFAULT 0,
    start_time INTEGER NOT NULL DEFAULT 0,
    end_time INTEGER NOT NULL DEFAULT 0,
    reward_config TEXT,
    UNIQUE(event_id)
);
CREATE INDEX IF NOT EXISTS idx_event_config_status ON t_event_config(status);
CREATE INDEX IF NOT EXISTS idx_event_config_type ON t_event_config(event_type);

-- 活动进度表
CREATE TABLE IF NOT EXISTS t_event_progress (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    role_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    progress_type INTEGER NOT NULL DEFAULT 0,
    progress_value INTEGER NOT NULL DEFAULT 0,
    status INTEGER NOT NULL DEFAULT 0,
    UNIQUE(role_id, event_id, progress_type)
);
CREATE INDEX IF NOT EXISTS idx_event_progress_event ON t_event_progress(event_id);
