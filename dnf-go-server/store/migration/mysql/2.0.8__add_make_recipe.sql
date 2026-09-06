-- ============================================
-- 2.0.8 增量迁移：合成配方配置表 t_make_recipe
-- 2026-09-06 第十二轮：ItemCombine 从"产物=target_index 直用、费用硬编码 0"
-- 深化为配方驱动——按 recipe_index 查配方(材料列表/产物模板/产物数量/合成费用)，
-- 材料按配方校验并扣减、按费用扣金币、产物按配方入包。
-- ============================================

-- 合成配方配置表
CREATE TABLE IF NOT EXISTS t_make_recipe (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    recipe_index INT UNSIGNED NOT NULL COMMENT '配方索引(合成接口 index 入参)',
    result_index INT UNSIGNED NOT NULL COMMENT '产物物品模板ID',
    result_count INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '单次合成产物数量',
    material_list TEXT NOT NULL COMMENT '材料列表(JSON:[{"index":2001,"count":1}])',
    cost_money INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '单次合成费用(金币)',
    enabled TINYINT NOT NULL DEFAULT 1 COMMENT '是否启用(0=停用)',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    UNIQUE KEY uk_make_recipe_index (recipe_index)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='合成配方配置表';

-- 种子配方(幂等): 1001 对齐既有测试(材料 2001x1+2002x1 -> 1001x1, 免费);
-- 1002 验证费用扣减(2001x3+2002x3 -> 1002x1, 500金币);
-- 1003 验证多材料+多产物(2001x2+2002x2+2003x2 -> 1003x2, 1000金币)
INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled) VALUES
    (1001, 1001, 1, '[{"index":2001,"count":1},{"index":2002,"count":1}]', 0, 1),
    (1002, 1002, 1, '[{"index":2001,"count":3},{"index":2002,"count":3}]', 500, 1),
    (1003, 1003, 2, '[{"index":2001,"count":2},{"index":2002,"count":2},{"index":2003,"count":2}]', 1000, 1)
ON DUPLICATE KEY UPDATE recipe_index = recipe_index;
