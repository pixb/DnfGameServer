-- 2026-09-08 第八十一轮: 分解固定+随机混产(extra_pool 额外奖励池, 与 result_pool 替代语义并存)
-- extra_pool 语义: 固定产物(material_list/旧列)必然产出, extra_pool 必掷一次额外入包(权重选一)
ALTER TABLE t_make_disjoint ADD COLUMN extra_pool TEXT NULL;

-- 新模板 3005 精铁头盔(防具, 分解固定+随机混产测试源)
INSERT INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (3005, '精铁头盔', 2, 8, 0, 180, '防具, 分解固定材料+额外随机')
ON DUPLICATE KEY UPDATE item_id = item_id;

-- 3005 分解: 固定 2013000000x1(破损剑刃) + 额外池 60% 剑刃x1 / 40% 护甲x1
INSERT INTO t_make_disjoint (item_index, material_index, material_count, material_list, extra_pool, enabled) VALUES
    (3005, 2013000000, 1, '[{"material_index":2013000000,"material_count":1,"bind_type":0}]', '[{"material_index":2013000000,"material_count":1,"bind_type":0,"weight":60},{"material_index":2013000001,"material_count":1,"bind_type":0,"weight":40}]', 1)
ON DUPLICATE KEY UPDATE extra_pool = VALUES(extra_pool), enabled = 1;
