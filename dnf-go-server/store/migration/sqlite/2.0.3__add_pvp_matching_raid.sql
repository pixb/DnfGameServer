-- ============================================
-- 2.0.3 增量迁移：补齐 PK 匹配表与副本入场表
-- 与 mysql 2.0.3 对齐
-- ============================================

-- PK 匹配表
CREATE TABLE IF NOT EXISTS t_pvp_matching (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    matching_id INTEGER NOT NULL UNIQUE,
    role_id INTEGER NOT NULL,
    match_type INTEGER NOT NULL,
    status INTEGER NOT NULL DEFAULT 0,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL'
);
CREATE INDEX IF NOT EXISTS idx_pvp_matching_role ON t_pvp_matching(role_id);

-- 副本入场记录表
CREATE TABLE IF NOT EXISTS t_raid_entrance (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    raid_index INTEGER NOT NULL,
    daily_character_count INTEGER NOT NULL DEFAULT 0,
    character_count INTEGER NOT NULL DEFAULT 0,
    account_count INTEGER NOT NULL DEFAULT 0,
    daily_reward_count INTEGER NOT NULL DEFAULT 0,
    reward_count INTEGER NOT NULL DEFAULT 0,
    last_enter_time INTEGER NOT NULL DEFAULT 0,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    UNIQUE(role_id, raid_index)
);
CREATE INDEX IF NOT EXISTS idx_raid_entrance_role ON t_raid_entrance(role_id);
