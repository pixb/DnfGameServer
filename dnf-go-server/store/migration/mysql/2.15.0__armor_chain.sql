-- 2026-09-08 第七十九轮: 武器/防具完整升级链
-- 防具模板: 1101 布甲上衣(Lv1) / 1102 皮甲上衣(Lv5) / 1103 铁甲上衣(Lv10)
INSERT INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (1101, '布甲上衣', 2, 1, 0, 80, '基础防具'),
    (1102, '皮甲上衣', 2, 5, 0, 150, '中级防具'),
    (1103, '铁甲上衣', 2, 10, 0, 400, '高级防具')
ON DUPLICATE KEY UPDATE item_id = item_id;

-- 武器升级链末段: 1003 秘银巨剑 + 2004x2 + 600 金 -> 1004 屠龙巨剑
-- 防具升级链: 1015 2001x1+2002x1 -> 1101x1(布甲) / 1016 1101x2+2003x1+100金 -> 1102x1(皮甲) /
--            1017 1102x2+2004x1+300金 -> 1103x1(铁甲)
INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1014, 1004, 1, '[{"index":1003,"count":1},{"index":2004,"count":2}]', 600, 1, 100, NULL, NULL, NULL),
    (1015, 1101, 1, '[{"index":2001,"count":1},{"index":2002,"count":1}]', 0, 1, 100, NULL, NULL, NULL),
    (1016, 1102, 1, '[{"index":1101,"count":2},{"index":2003,"count":1}]', 100, 1, 100, NULL, NULL, NULL),
    (1017, 1103, 1, '[{"index":1102,"count":2},{"index":2004,"count":1}]', 300, 1, 100, NULL, NULL, NULL)
ON DUPLICATE KEY UPDATE recipe_index = recipe_index;
