-- ============================================
-- 2.1.0 增量迁移：合成配方成功率/随机产出/失败保底扩展
-- 2026-09-06 第十五轮：t_make_recipe 增加 success_rate(成功率)、result_pool(随机产出池)、
-- fail_result_index/fail_result_count(失败保底产出); t_item_combine 增加 success(合成结果标记)。
-- 语义：成功时 result_pool 非空则按权重随机产出(否则用固定 result_index/result_count)；
--       失败时 fail_result_index 非空则产出保底物品，否则无产出。
-- ============================================

ALTER TABLE t_make_recipe
    ADD COLUMN IF NOT EXISTS success_rate INT UNSIGNED NOT NULL DEFAULT 100 COMMENT '成功率百分比(0-100)' AFTER cost_money,
    ADD COLUMN IF NOT EXISTS result_pool TEXT NULL COMMENT '随机产出池(JSON:[{"result_index":x,"result_count":y,"weight":z}],空=固定产物)' AFTER success_rate,
    ADD COLUMN IF NOT EXISTS fail_result_index INT UNSIGNED NULL COMMENT '失败保底产物模板(NULL=失败无产出)' AFTER result_pool,
    ADD COLUMN IF NOT EXISTS fail_result_count INT UNSIGNED NULL COMMENT '失败保底产物数量' AFTER fail_result_index;

ALTER TABLE t_item_combine
    ADD COLUMN IF NOT EXISTS success TINYINT NOT NULL DEFAULT 1 COMMENT '合成是否成功(0=失败)' AFTER cost_money;

-- 种子配方(幂等, 仅新增不覆盖): 1004 随机产出池(2001x1 -> 60%:1001x1 / 40%:1002x1, 免费);
-- 1005 成功率 50% + 失败保底(2001x1 -> 成功 1001x1, 失败 2013000000x1, 免费)
INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1004, 1001, 1, '[{"index":2001,"count":1}]', 0, 1, 100, '[{"result_index":1001,"result_count":1,"weight":60},{"result_index":1002,"result_count":1,"weight":40}]', NULL, NULL),
    (1005, 1001, 1, '[{"index":2001,"count":1}]', 0, 1, 50, NULL, 2013000000, 1)
ON DUPLICATE KEY UPDATE recipe_index = recipe_index;
