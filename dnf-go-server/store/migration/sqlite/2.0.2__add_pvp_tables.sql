-- ============================================
-- 2.0.2 增量迁移：补齐 PK 服务表(t_pvp_*)
-- 与 mysql 2.0.2 对齐
-- ============================================

-- PK 记录表
CREATE TABLE IF NOT EXISTS t_pvp_record (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    match_type INTEGER NOT NULL DEFAULT 0,
    win INTEGER NOT NULL DEFAULT 0,
    score INTEGER NOT NULL DEFAULT 0,
    opponent_id INTEGER NOT NULL DEFAULT 0,
    battle_time INTEGER NOT NULL DEFAULT 0,
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_pvp_record_role ON t_pvp_record(role_id);

-- PK 统计表
CREATE TABLE IF NOT EXISTS t_pvp_stats (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL UNIQUE,
    total_matches INTEGER NOT NULL DEFAULT 0,
    win_count INTEGER NOT NULL DEFAULT 0,
    lose_count INTEGER NOT NULL DEFAULT 0,
    total_score INTEGER NOT NULL DEFAULT 0,
    max_win_streak INTEGER NOT NULL DEFAULT 0,
    current_streak INTEGER NOT NULL DEFAULT 0,
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);

-- PK 赛季表
CREATE TABLE IF NOT EXISTS t_pvp_season (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    season_id INTEGER NOT NULL UNIQUE,
    season_name TEXT NOT NULL DEFAULT '',
    start_time INTEGER NOT NULL DEFAULT 0,
    end_time INTEGER NOT NULL DEFAULT 0,
    status INTEGER NOT NULL DEFAULT 0,
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);

-- PK 奖励表
CREATE TABLE IF NOT EXISTS t_pvp_reward (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    reward_id INTEGER NOT NULL DEFAULT 0,
    reward_name TEXT NOT NULL DEFAULT '',
    count INTEGER NOT NULL DEFAULT 1,
    claimed INTEGER NOT NULL DEFAULT 0,
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_pvp_reward_role ON t_pvp_reward(role_id);

-- PK 匹配类型表
CREATE TABLE IF NOT EXISTS t_pvp_match_type (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    match_type INTEGER NOT NULL UNIQUE,
    type_name TEXT NOT NULL DEFAULT '',
    min_level INTEGER NOT NULL DEFAULT 1,
    max_level INTEGER NOT NULL DEFAULT 100,
    min_players INTEGER NOT NULL DEFAULT 1,
    max_players INTEGER NOT NULL DEFAULT 4,
    status INTEGER NOT NULL DEFAULT 1,
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
