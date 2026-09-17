-- ============================================
-- 2.0.5 增量迁移：补齐制作模块其余记录表
-- 2026-09-06：sqlite make.go 已实现 EmblemUpgrade/AvatarCompose/
-- ProductionRegister/CardCompose(写记录表)，但 MySQL 从未建这 4 张表；
-- 本次补表并对齐 mysql make.go 实现。
-- 注意：MySQL 货币表为 role_currency(gold 字段)，sqlite 为 t_role_currency(money 字段)，
-- 两驱动 SQL 天然不同，扣费逻辑分别适配。
-- ============================================

-- 徽章升级记录表
CREATE TABLE IF NOT EXISTS t_emblem_upgrade (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    emblem_index INT NOT NULL COMMENT '徽章索引',
    level INT NOT NULL COMMENT '当前等级',
    try_count INT NOT NULL COMMENT '尝试次数',
    success_count INT NOT NULL COMMENT '成功次数',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '消耗金币',
    cost_talisman INT NOT NULL DEFAULT 0 COMMENT '消耗护符数',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    INDEX idx_emblem_upgrade_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='徽章升级记录表';

-- 时装合成记录表
CREATE TABLE IF NOT EXISTS t_avatar_compose (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    avatar_guids TEXT NOT NULL COMMENT '时装GUID列表',
    result_index INT NOT NULL COMMENT '产出时装索引',
    result_guid BIGINT UNSIGNED NOT NULL COMMENT '产出时装GUID',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '消耗金币',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    INDEX idx_avatar_compose_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='时装合成记录表';

-- 物品制作记录表
CREATE TABLE IF NOT EXISTS t_item_production (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    slot_index INT NOT NULL COMMENT '制作槽位',
    recipe_index INT NOT NULL COMMENT '配方索引',
    count INT NOT NULL DEFAULT 1 COMMENT '制作次数',
    result_index INT NOT NULL COMMENT '产出物品索引',
    result_count INT NOT NULL DEFAULT 1 COMMENT '产出数量',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '消耗金币',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    INDEX idx_item_production_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物品制作记录表';

-- 卡片合成记录表
CREATE TABLE IF NOT EXISTS t_card_compose (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    card_list TEXT NOT NULL COMMENT '材料卡列表',
    result_index INT NOT NULL COMMENT '产出卡索引',
    result_count INT NOT NULL DEFAULT 1 COMMENT '产出数量',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '消耗金币',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '创建时间',
    INDEX idx_card_compose_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卡片合成记录表';
