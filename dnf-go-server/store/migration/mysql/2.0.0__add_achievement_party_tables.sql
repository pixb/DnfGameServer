-- ============================================
-- 2.0.0 增量迁移：成就系统与组队系统表
-- 2026-09-05 新增（成就/组队此前仅文档声称实现，缺表导致 store 查询失败）
-- ============================================

-- 成就配置表（成就定义，供成就模块查询）
CREATE TABLE IF NOT EXISTS t_achievement_config (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    achievement_id INT UNSIGNED NOT NULL COMMENT '成就ID',
    name VARCHAR(255) NOT NULL DEFAULT '' COMMENT '成就名称',
    description TEXT COMMENT '成就描述',
    target_value INT NOT NULL DEFAULT 0 COMMENT '目标值',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_achievement_id (achievement_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成就配置表';

-- 成就记录表（玩家成就进度与领取状态）
CREATE TABLE IF NOT EXISTS t_achievement_record (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    achievement_id INT UNSIGNED NOT NULL COMMENT '成就ID',
    progress INT NOT NULL DEFAULT 0 COMMENT '当前进度',
    completed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否完成',
    rewarded TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已领基础奖励',
    reward_time BIGINT NOT NULL DEFAULT 0 COMMENT '领取时间',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_role_achievement (role_id, achievement_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成就记录表';

-- 成就奖励领取表（额外奖励领取记录）
CREATE TABLE IF NOT EXISTS t_achievement_reward (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    achievement_id INT UNSIGNED NOT NULL COMMENT '成就ID',
    reward_type INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '奖励类型',
    reward_index INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '奖励索引',
    reward_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '奖励数量',
    claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已领取',
    claim_time BIGINT NOT NULL DEFAULT 0 COMMENT '领取时间',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    INDEX idx_role_id (role_id),
    INDEX idx_role_achievement (role_id, achievement_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成就奖励领取表';

-- 冒险数据表（成就系统联动冒险联盟经验）
CREATE TABLE IF NOT EXISTS t_adventure_data (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    adventureunionlevel INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '冒险联盟等级',
    adventureunionexp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '冒险联盟经验',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='冒险数据表';

-- 组队表
CREATE TABLE IF NOT EXISTS t_party (
    party_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    leader_id BIGINT UNSIGNED NOT NULL COMMENT '队长角色ID',
    name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '队伍名称',
    max_members INT NOT NULL DEFAULT 4 COMMENT '最大成员数',
    status INT NOT NULL DEFAULT 0 COMMENT '0=招募中, 1=战斗中, 2=已解散',
    dungeon_index INT NOT NULL DEFAULT 0 COMMENT '副本索引',
    room_id INT NOT NULL DEFAULT 0 COMMENT '房间ID',
    min_level INT NOT NULL DEFAULT 1 COMMENT '最低等级',
    max_level INT NOT NULL DEFAULT 156 COMMENT '最高等级',
    area INT NOT NULL DEFAULT 0 COMMENT '区域',
    subtype INT NOT NULL DEFAULT 0 COMMENT '子类型',
    stage_index INT NOT NULL DEFAULT 0 COMMENT '阶段索引',
    public_type INT NOT NULL DEFAULT 0 COMMENT '公开类型',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    update_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    INDEX idx_status (status),
    INDEX idx_leader (leader_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组队表';

-- 组队成员表
CREATE TABLE IF NOT EXISTS t_party_member (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    party_id BIGINT UNSIGNED NOT NULL COMMENT '队伍ID',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    player_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '玩家ID',
    team_type INT NOT NULL DEFAULT 0 COMMENT '队伍类型',
    status INT NOT NULL DEFAULT 0 COMMENT '0=在队, 1=已退出',
    join_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_party_role (party_id, role_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组队成员表';
