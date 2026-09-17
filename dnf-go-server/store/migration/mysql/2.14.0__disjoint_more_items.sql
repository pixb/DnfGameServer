-- 2026-09-08 第七十八轮: 分解配置引用更多模板物品
-- 3002 旧式护手: 旧列单材料(2013000000x1) -> material_list 多产物(2013000000x1 + 2013000001x1)
-- 走 material_list 多产物路径(与 3001 同构, bind 下限 0)
UPDATE t_make_disjoint SET material_list = '[{"material_index":2013000000,"material_count":1,"bind_type":0},{"material_index":2013000001,"material_count":1,"bind_type":0}]', enabled = 1 WHERE item_index = 3002;
