-- ============================================
-- 2.0.4 增量迁移：补齐制作模块记录表
-- 2026-09-06：store/db/sqlite/make.go 已真实实现 ItemCombine/ItemDisjoint
-- (写 t_item_combine/t_item_disjoint 记录表)，但 MySQL 从未建这两张表，
-- mysql make.go 全部为 stub；本次补齐表结构并对齐实现。
-- ============================================

-- 物品合成记录表
CREATE TABLE IF NOT EXISTS t_item_combine (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    target_index INT UNSIGNED NOT NULL COMMENT '目标格子',
    material_list TEXT NOT NULL COMMENT '材料列表(JSON)',
    count INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '合成次数',
    result_guid BIGINT UNSIGNED NOT NULL COMMENT '产出物品GUID',
    cost_money INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '消耗金币',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    INDEX idx_item_combine_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物品合成记录表';

-- 物品分解记录表
CREATE TABLE IF NOT EXISTS t_item_disjoint (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    equip_guids TEXT NOT NULL COMMENT '分解装备GUID列表',
    material_list TEXT NOT NULL COMMENT '产出材料列表(JSON)',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    INDEX idx_item_disjoint_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物品分解记录表';
