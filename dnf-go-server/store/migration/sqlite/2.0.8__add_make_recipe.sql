-- ============================================
-- 2.0.8 增量迁移：合成配方配置表 t_make_recipe
-- 2026-09-06 第十二轮：与 mysql 2.0.8 对齐；ItemCombine 深化为配方驱动。
-- ============================================

-- 合成配方配置表
CREATE TABLE IF NOT EXISTS t_make_recipe (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    recipe_index INTEGER NOT NULL,
    result_index INTEGER NOT NULL,
    result_count INTEGER NOT NULL DEFAULT 1,
    material_list TEXT NOT NULL,
    cost_money INTEGER NOT NULL DEFAULT 0,
    enabled INTEGER NOT NULL DEFAULT 1,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
);
CREATE UNIQUE INDEX IF NOT EXISTS uk_make_recipe_index ON t_make_recipe(recipe_index);

-- 种子配方(幂等): 与 mysql 2.0.8 完全一致
INSERT OR IGNORE INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled) VALUES
    (1001, 1001, 1, '[{"index":2001,"count":1},{"index":2002,"count":1}]', 0, 1),
    (1002, 1002, 1, '[{"index":2001,"count":3},{"index":2002,"count":3}]', 500, 1),
    (1003, 1003, 2, '[{"index":2001,"count":2},{"index":2002,"count":2},{"index":2003,"count":2}]', 1000, 1);
