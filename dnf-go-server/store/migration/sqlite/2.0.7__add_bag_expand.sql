-- ============================================
-- 2.0.7 增量迁移：背包扩容容量存储
-- 2026-09-06：与 mysql 2.0.7 对齐。
-- ============================================

-- 背包扩容表
CREATE TABLE IF NOT EXISTS t_bag_expand (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    role_id INTEGER NOT NULL,
    bag_type INTEGER NOT NULL DEFAULT 1,
    capacity INTEGER NOT NULL DEFAULT 0,
    UNIQUE(role_id, bag_type)
);
CREATE INDEX IF NOT EXISTS idx_bag_expand_role ON t_bag_expand(role_id);
