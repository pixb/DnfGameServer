-- 2026-09-08 第八十轮: 分解随机池/权重产物 (sqlite 同构)
ALTER TABLE t_make_disjoint ADD COLUMN result_pool TEXT NULL;

INSERT OR IGNORE INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (3004, '精铁盾牌', 2, 5, 0, 120, '防具, 分解出随机材料');

INSERT OR REPLACE INTO t_make_disjoint (item_index, material_index, material_count, material_list, result_pool, enabled) VALUES
    (3004, 0, 0, NULL, '[{"material_index":2013000000,"material_count":2,"bind_type":0,"weight":60},{"material_index":2013000001,"material_count":1,"bind_type":0,"weight":40}]', 1);
