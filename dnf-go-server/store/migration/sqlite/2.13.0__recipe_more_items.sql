-- 2026-09-08 第七十六轮: 模板物品扩展 + 配方引用更多模板物品 (sqlite 同构)
INSERT OR IGNORE INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (2004, '秘银矿石', 0, 10, 0, 50, '高级锻造材料'),
    (1004, '屠龙巨剑', 1, 20, 0, 2000, '传说武器, 合成配方产物');

INSERT OR IGNORE INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1010, 1004, 1, '[{"index":2004,"count":3}]', 500, 1, 100, NULL, NULL, NULL),
    (1011, 2004, 1, '[{"index":3001,"count":1},{"index":2003,"count":2}]', 0, 1, 100, NULL, NULL, NULL),
    (1012, 1003, 1, '[{"index":1002,"count":2},{"index":2002,"count":3}]', 300, 1, 100, NULL, NULL, NULL);
