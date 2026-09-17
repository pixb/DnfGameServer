-- 2026-09-08 第七十八轮: 分解配置引用更多模板物品 (sqlite 同构)
UPDATE t_make_disjoint SET material_list = '[{"material_index":2013000000,"material_count":1,"bind_type":0},{"material_index":2013000001,"material_count":1,"bind_type":0}]', enabled = 1 WHERE item_index = 3002;
