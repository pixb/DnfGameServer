-- ============================================
-- 2.3.0 增量迁移：行为日志表(sqlite 镜像)
-- 2026-09-06 第十九轮：rank/log TCP handler 实化——日志模块落库。
-- 与 mysql 驱动对称。
-- ============================================

CREATE TABLE IF NOT EXISTS t_behavior_log (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT 0,
    role_id INTEGER NOT NULL DEFAULT 0,
    module TEXT NOT NULL DEFAULT '',
    action TEXT NOT NULL DEFAULT '',
    level TEXT NOT NULL DEFAULT 'info',
    content TEXT NOT NULL DEFAULT ''
);

CREATE INDEX IF NOT EXISTS idx_behavior_role_created ON t_behavior_log (role_id, created_at);
CREATE INDEX IF NOT EXISTS idx_behavior_level ON t_behavior_log (level);
