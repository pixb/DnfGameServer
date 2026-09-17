-- ============================================
-- 2.0.9 增量迁移：分解产出配置表 t_make_disjoint
-- 2026-09-06 第十三轮：ItemDisjoint 从"硬编码 2013000000 x N*10"深化为
-- 配置驱动——按物品模板(item_index)查分解材料(material_index)与单件产出数量
-- (material_count)，多件物品产出按材料模板聚合入包。
-- ============================================

-- 分解产出配置表
CREATE TABLE IF NOT EXISTS t_make_disjoint (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item_index INT UNSIGNED NOT NULL COMMENT '物品模板ID(对应 bag_item.item_id)',
    material_index INT UNSIGNED NOT NULL COMMENT '分解材料模板ID',
    material_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '单件分解产出数量',
    enabled TINYINT NOT NULL DEFAULT 1 COMMENT '是否启用(0=停用)',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    UNIQUE KEY uk_make_disjoint_item (item_index)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分解产出配置表';

-- 种子配置(幂等): 1/2/3 对齐既有测试(单件 10 个材料); 合成产物 1001/1002/1003 按模板差异化
INSERT INTO t_make_disjoint (item_index, material_index, material_count, enabled) VALUES
    (1, 2013000000, 10, 1),
    (2, 2013000000, 10, 1),
    (3, 2013000000, 10, 1),
    (1001, 2013000000, 15, 1),
    (1002, 2013000000, 20, 1),
    (1003, 2013000000, 25, 1)
ON DUPLICATE KEY UPDATE item_index = item_index;
