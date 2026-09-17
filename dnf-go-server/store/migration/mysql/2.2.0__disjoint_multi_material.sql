-- ============================================
-- 2.2.0 增量迁移：分解多材料产出 + 绑定类型
-- 2026-09-06 第十六轮：t_make_disjoint 增加 material_list(JSON 多材料产出,
-- [{"material_index":x,"material_count":y,"bind_type":z}]); 非空时优先使用,
-- 空/NULL 回退旧列 material_index/material_count(bind_type 默认 0)。
-- 既有 6 条种子回填为 JSON(旧列保留); 新增 3001(多材料+bind_type)与 3002(旧列回退用例)。
-- ============================================

ALTER TABLE t_make_disjoint
    ADD COLUMN IF NOT EXISTS material_list TEXT NULL COMMENT '多材料产出(JSON:[{material_index,material_count,bind_type}],空=用旧列)' AFTER material_count;

-- 回填既有配置为 JSON(幂等: 仅对未回填的行生效)
UPDATE t_make_disjoint SET material_list = CASE item_index
    WHEN 1 THEN '[{"material_index":2013000000,"material_count":10,"bind_type":0}]'
    WHEN 2 THEN '[{"material_index":2013000000,"material_count":10,"bind_type":0}]'
    WHEN 3 THEN '[{"material_index":2013000000,"material_count":10,"bind_type":0}]'
    WHEN 1001 THEN '[{"material_index":2013000000,"material_count":15,"bind_type":0}]'
    WHEN 1002 THEN '[{"material_index":2013000000,"material_count":20,"bind_type":0}]'
    WHEN 1003 THEN '[{"material_index":2013000000,"material_count":25,"bind_type":0}]'
END
WHERE item_index IN (1, 2, 3, 1001, 1002, 1003) AND material_list IS NULL;

-- 新种子(幂等, 仅新增不覆盖): 3001 多材料产出(材料2013000000x3无绑定 + 2013000001x2装备绑定);
-- 3002 旧列回退(单材料 2013000000x5, material_list 置 NULL 验证回退路径)
INSERT INTO t_make_disjoint (item_index, material_index, material_count, enabled, material_list) VALUES
    (3001, 2013000000, 3, 1, '[{"material_index":2013000000,"material_count":3,"bind_type":0},{"material_index":2013000001,"material_count":2,"bind_type":1}]'),
    (3002, 2013000000, 5, 1, NULL)
ON DUPLICATE KEY UPDATE item_index = item_index;
