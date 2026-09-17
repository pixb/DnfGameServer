-- 2026-09-08 第七十六轮: 模板物品扩展 + 配方引用更多模板物品
-- 新模板: 2004 秘银矿石(材料 Lv10) / 1004 屠龙巨剑(武器 Lv20)
INSERT INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (2004, '秘银矿石', 0, 10, 0, 50, '高级锻造材料'),
    (1004, '屠龙巨剑', 1, 20, 0, 2000, '传说武器, 合成配方产物')
ON DUPLICATE KEY UPDATE item_id = item_id;

-- 新配方(引用新模板物品):
-- 1010 秘银锻造: 2004x3 + 500 金 -> 1004x1
-- 1011 材料进阶: 3001x1 + 2003x2 -> 2004x1
-- 1012 武器升级链: 1002x2 + 2002x3 + 300 金 -> 1003x1
INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1010, 1004, 1, '[{"index":2004,"count":3}]', 500, 1, 100, NULL, NULL, NULL),
    (1011, 2004, 1, '[{"index":3001,"count":1},{"index":2003,"count":2}]', 0, 1, 100, NULL, NULL, NULL),
    (1012, 1003, 1, '[{"index":1002,"count":2},{"index":2002,"count":3}]', 300, 1, 100, NULL, NULL, NULL)
ON DUPLICATE KEY UPDATE recipe_index = recipe_index;
