-- ============================================
-- 2.0.3 增量迁移：补齐 PK 匹配表与副本入场表
-- 2026-09-06：models/pk.go 定义 t_pvp_matching/t_raid_entrance，
-- 此前从未建表；PK 业务深化(匹配/取消/副本入场/每日重置)依赖这两张表。
-- ============================================

-- PK 匹配表
CREATE TABLE IF NOT EXISTS t_pvp_matching (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    matching_id BIGINT UNSIGNED NOT NULL COMMENT '匹配ID',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    match_type INT UNSIGNED NOT NULL COMMENT '匹配类型',
    status INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0=匹配中 1=匹配成功 2=已取消 3=已超时',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_matching_id (matching_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 匹配表';

-- 副本入场记录表
CREATE TABLE IF NOT EXISTS t_raid_entrance (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    raid_index INT UNSIGNED NOT NULL COMMENT '副本索引',
    daily_character_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '每日角色入场次数',
    character_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色入场次数',
    account_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号入场次数',
    daily_reward_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '每日奖励次数',
    reward_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '奖励次数',
    last_enter_time BIGINT NOT NULL DEFAULT 0 COMMENT '最后入场时间',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_role_raid (role_id, raid_index)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='副本入场记录表';
