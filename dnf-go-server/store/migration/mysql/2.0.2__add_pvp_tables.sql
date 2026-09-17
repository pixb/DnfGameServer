-- ============================================
-- 2.0.2 增量迁移：补齐 PK 服务表(t_pvp_*)
-- 2026-09-06：pk_service 原基于遗留 MySQL 直连查询 t_pvp_* 表，
-- 但迁移 SQL 从未建过这些表(此前查询全部报错、HTTP 层吞错返回空数据)。
-- 本次 store 层补齐 PK 实现并统一建表。
-- ============================================

-- PK 记录表
CREATE TABLE IF NOT EXISTS t_pvp_record (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    match_type INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '匹配类型',
    win TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否胜利',
    score INT NOT NULL DEFAULT 0 COMMENT '积分变化',
    opponent_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '对手角色ID',
    battle_time BIGINT NOT NULL DEFAULT 0 COMMENT '战斗时间',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    INDEX idx_role_id (role_id),
    INDEX idx_battle_time (battle_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 记录表';

-- PK 统计表
CREATE TABLE IF NOT EXISTS t_pvp_stats (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    total_matches INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '总场次',
    win_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '胜利场次',
    lose_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '失败场次',
    total_score INT NOT NULL DEFAULT 0 COMMENT '总积分',
    max_win_streak INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大连胜',
    current_streak INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前连胜',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 统计表';

-- PK 赛季表
CREATE TABLE IF NOT EXISTS t_pvp_season (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    season_id INT UNSIGNED NOT NULL COMMENT '赛季ID',
    season_name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '赛季名称',
    start_time BIGINT NOT NULL DEFAULT 0 COMMENT '开始时间',
    end_time BIGINT NOT NULL DEFAULT 0 COMMENT '结束时间',
    status INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0=未开始 1=进行中 2=已结束',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_season_id (season_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 赛季表';

-- PK 奖励表
CREATE TABLE IF NOT EXISTS t_pvp_reward (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    reward_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '奖励ID',
    reward_name VARCHAR(100) NOT NULL DEFAULT '' COMMENT '奖励名称',
    count INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数量',
    claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已领取',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 奖励表';

-- PK 匹配类型表
CREATE TABLE IF NOT EXISTS t_pvp_match_type (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    match_type INT UNSIGNED NOT NULL COMMENT '匹配类型',
    type_name VARCHAR(50) NOT NULL DEFAULT '' COMMENT '类型名称',
    min_level INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '最低等级',
    max_level INT UNSIGNED NOT NULL DEFAULT 100 COMMENT '最高等级',
    min_players INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '最少人数',
    max_players INT UNSIGNED NOT NULL DEFAULT 4 COMMENT '最多人数',
    status INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0=禁用 1=启用',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_match_type (match_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 匹配类型表';
