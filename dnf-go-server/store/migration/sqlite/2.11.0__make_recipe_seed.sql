-- 2026-09-07 第七十一轮: 合成配方配置表深化——补充批量/升级/随机池/费用配方种子 (sqlite 同构)
-- 物品体系沿用既有: 2001=基础材料, 1001/1002=产物模板
INSERT OR IGNORE INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1006, 1002, 1, '[{"index":2001,"count":3}]', 100, 1, 100, NULL, NULL, NULL),
    (1007, 1002, 1, '[{"index":1001,"count":2},{"index":2001,"count":1}]', 50, 1, 100, NULL, NULL, NULL),
    (1008, 1002, 1, '[{"index":1001,"count":1},{"index":2001,"count":2}]', 0, 1, 100, '[{"result_index":1002,"result_count":1,"weight":60},{"result_index":2001,"result_count":2,"weight":40}]', NULL, NULL),
    (1009, 1001, 5, '[{"index":2001,"count":10}]', 500, 1, 100, NULL, NULL, NULL);
