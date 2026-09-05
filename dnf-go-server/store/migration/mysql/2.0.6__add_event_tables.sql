-- ============================================
-- 2.0.6 增量迁移：活动模块建表
-- 2026-09-06：event HTTP/TCP handler 此前为空数据源
-- (活动配置暂存于运营后台),本次补齐配置表与角色进度表,
-- 使活动模块具备真实存储。
-- ============================================

-- 活动配置表
CREATE TABLE IF NOT EXISTS t_event_config (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    event_id INT NOT NULL COMMENT '活动业务ID',
    title VARCHAR(128) NOT NULL DEFAULT '' COMMENT '活动标题',
    description TEXT COMMENT '活动描述',
    event_type INT NOT NULL DEFAULT 0 COMMENT '活动类型: 0=通用 1=限时 2=签到 3=累计',
    status INT NOT NULL DEFAULT 0 COMMENT '状态: 0=关闭 1=进行中 2=已结束 3=未开始',
    start_time BIGINT NOT NULL DEFAULT 0 COMMENT '开始时间(Unix秒)',
    end_time BIGINT NOT NULL DEFAULT 0 COMMENT '结束时间(Unix秒)',
    reward_config TEXT COMMENT '奖励配置(JSON)',
    UNIQUE KEY uk_event_id (event_id),
    INDEX idx_event_status (status),
    INDEX idx_event_type (event_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动配置表';

-- 活动进度表
CREATE TABLE IF NOT EXISTS t_event_progress (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    event_id INT NOT NULL COMMENT '活动业务ID',
    progress_type INT NOT NULL DEFAULT 0 COMMENT '进度类型',
    progress_value BIGINT NOT NULL DEFAULT 0 COMMENT '进度值',
    status INT NOT NULL DEFAULT 0 COMMENT '状态: 0=进行中 1=已领奖',
    UNIQUE KEY uk_role_event_progress (role_id, event_id, progress_type),
    INDEX idx_event_id (event_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动进度表';
