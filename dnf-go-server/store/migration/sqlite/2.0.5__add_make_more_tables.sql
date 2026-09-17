-- ============================================
-- 2.0.5 增量迁移：补齐制作模块其余记录表
-- 2026-09-06：与 mysql 2.0.5 对齐；sqlite LATEST.sql 已有这 4 张表，
-- 此文件供增量迁移链路保持 mysql/sqlite 对称。
-- ============================================

-- 徽章升级记录表
CREATE TABLE IF NOT EXISTS t_emblem_upgrade (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    emblem_index INTEGER NOT NULL,
    level INTEGER NOT NULL,
    try_count INTEGER NOT NULL,
    success_count INTEGER NOT NULL,
    cost_money INTEGER NOT NULL DEFAULT 0,
    cost_talisman INTEGER NOT NULL DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_emblem_upgrade_role ON t_emblem_upgrade(role_id);

-- 时装合成记录表
CREATE TABLE IF NOT EXISTS t_avatar_compose (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    avatar_guids TEXT NOT NULL,
    result_index INTEGER NOT NULL,
    result_guid INTEGER NOT NULL,
    cost_money INTEGER NOT NULL DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_avatar_compose_role ON t_avatar_compose(role_id);

-- 物品制作记录表
CREATE TABLE IF NOT EXISTS t_item_production (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    slot_index INTEGER NOT NULL,
    recipe_index INTEGER NOT NULL,
    count INTEGER NOT NULL DEFAULT 1,
    result_index INTEGER NOT NULL,
    result_count INTEGER NOT NULL DEFAULT 1,
    cost_money INTEGER NOT NULL DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_item_production_role ON t_item_production(role_id);

-- 卡片合成记录表
CREATE TABLE IF NOT EXISTS t_card_compose (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    card_list TEXT NOT NULL,
    result_index INTEGER NOT NULL,
    result_count INTEGER NOT NULL DEFAULT 1,
    cost_money INTEGER NOT NULL DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_card_compose_role ON t_card_compose(role_id);
