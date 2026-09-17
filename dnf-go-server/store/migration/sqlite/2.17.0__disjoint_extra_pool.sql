-- 2026-09-08 第八十一轮: 分解固定+随机混产 (sqlite 同构)
ALTER TABLE t_make_disjoint ADD COLUMN extra_pool TEXT NULL;

INSERT OR IGNORE INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (3005, '精铁头盔', 2, 8, 0, 180, '防具, 分解固定材料+额外随机');

INSERT OR REPLACE INTO t_make_disjoint (item_index, material_index, material_count, material_list, extra_pool, enabled) VALUES
    (3005, 2013000000, 1, '[{"material_index":2013000000,"material_count":1,"bind_type":0}]', '[{"material_index":2013000000,"material_count":1,"bind_type":0,"weight":60},{"material_index":2013000001,"material_count":1,"bind_type":0,"weight":40}]', 1);
