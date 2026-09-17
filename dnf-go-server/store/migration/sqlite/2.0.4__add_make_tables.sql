-- ============================================
-- 2.0.4 增量迁移：补齐制作模块记录表
-- 2026-09-06：与 mysql 2.0.4 对齐；sqlite LATEST.sql 已有这两张表，
-- 此文件供增量迁移链路保持 mysql/sqlite 对称。
-- ============================================

-- 物品合成记录表
CREATE TABLE IF NOT EXISTS t_item_combine (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    target_index INTEGER NOT NULL,
    material_list TEXT NOT NULL,
    count INTEGER NOT NULL DEFAULT 1,
    result_guid INTEGER NOT NULL,
    cost_money INTEGER NOT NULL DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_item_combine_role ON t_item_combine(role_id);

-- 物品分解记录表
CREATE TABLE IF NOT EXISTS t_item_disjoint (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    equip_guids TEXT NOT NULL,
    material_list TEXT NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE INDEX IF NOT EXISTS idx_item_disjoint_role ON t_item_disjoint(role_id);
