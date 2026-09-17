-- ============================================
-- 2.1.0 增量迁移：合成配方成功率/随机产出/失败保底扩展(sqlite)
-- 2026-09-06 第十五轮：与 mysql 驱动对称, t_make_recipe 增加 success_rate/result_pool/
-- fail_result_index/fail_result_count; t_item_combine 增加 success 标记。
-- ============================================

ALTER TABLE t_make_recipe ADD COLUMN success_rate INTEGER NOT NULL DEFAULT 100;
ALTER TABLE t_make_recipe ADD COLUMN result_pool TEXT;
ALTER TABLE t_make_recipe ADD COLUMN fail_result_index INTEGER;
ALTER TABLE t_make_recipe ADD COLUMN fail_result_count INTEGER;
ALTER TABLE t_item_combine ADD COLUMN success INTEGER NOT NULL DEFAULT 1;

-- 种子配方(幂等): 1004 随机产出池; 1005 成功率 50% + 失败保底
INSERT OR IGNORE INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1004, 1001, 1, '[{"index":2001,"count":1}]', 0, 1, 100, '[{"result_index":1001,"result_count":1,"weight":60},{"result_index":1002,"result_count":1,"weight":40}]', NULL, NULL),
    (1005, 1001, 1, '[{"index":2001,"count":1}]', 0, 1, 50, NULL, 2013000000, 1);
