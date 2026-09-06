-- ============================================
-- 2.2.0 增量迁移：分解多材料产出 + 绑定类型(sqlite)
-- 2026-09-06 第十六轮：与 mysql 驱动对称, t_make_disjoint 增加 material_list(JSON),
-- 非空优先, 空/NULL 回退旧列; 既有种子回填为 JSON, 新增 3001/3002。
-- ============================================

ALTER TABLE t_make_disjoint ADD COLUMN material_list TEXT;

-- 回填既有配置为 JSON
UPDATE t_make_disjoint SET material_list = CASE item_index
    WHEN 1 THEN '[{"material_index":2013000000,"material_count":10,"bind_type":0}]'
    WHEN 2 THEN '[{"material_index":2013000000,"material_count":10,"bind_type":0}]'
    WHEN 3 THEN '[{"material_index":2013000000,"material_count":10,"bind_type":0}]'
    WHEN 1001 THEN '[{"material_index":2013000000,"material_count":15,"bind_type":0}]'
    WHEN 1002 THEN '[{"material_index":2013000000,"material_count":20,"bind_type":0}]'
    WHEN 1003 THEN '[{"material_index":2013000000,"material_count":25,"bind_type":0}]'
END
WHERE item_index IN (1, 2, 3, 1001, 1002, 1003) AND material_list IS NULL;

-- 新种子(幂等): 3001 多材料+bind_type; 3002 旧列回退
INSERT OR IGNORE INTO t_make_disjoint (item_index, material_index, material_count, enabled, material_list) VALUES
    (3001, 2013000000, 3, 1, '[{"material_index":2013000000,"material_count":3,"bind_type":0},{"material_index":2013000001,"material_count":2,"bind_type":1}]'),
    (3002, 2013000000, 5, 1, NULL);
