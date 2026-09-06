-- ============================================
-- 2.0.9 增量迁移：分解产出配置表 t_make_disjoint
-- 2026-09-06 第十三轮：与 mysql 2.0.9 对齐；ItemDisjoint 深化为配置驱动。
-- ============================================

-- 分解产出配置表
CREATE TABLE IF NOT EXISTS t_make_disjoint (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    item_index INTEGER NOT NULL,
    material_index INTEGER NOT NULL,
    material_count INTEGER NOT NULL DEFAULT 0,
    enabled INTEGER NOT NULL DEFAULT 1,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE UNIQUE INDEX IF NOT EXISTS uk_make_disjoint_item ON t_make_disjoint(item_index);

-- 种子配置(幂等): 与 mysql 2.0.9 完全一致
INSERT OR IGNORE INTO t_make_disjoint (item_index, material_index, material_count, enabled) VALUES
    (1, 2013000000, 10, 1),
    (2, 2013000000, 10, 1),
    (3, 2013000000, 10, 1),
    (1001, 2013000000, 15, 1),
    (1002, 2013000000, 20, 1),
    (1003, 2013000000, 25, 1);
