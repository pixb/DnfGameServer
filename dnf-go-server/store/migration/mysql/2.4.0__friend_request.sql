-- ============================================
-- 2.4.0 好友申请表
-- 2026-09-06 第三十九轮: 好友申请/同意流程(申请制取代直接加好友)
-- 客户端经 HTTP 发好友申请, 接收方同意后双向建立好友关系
-- ============================================

CREATE TABLE IF NOT EXISTS friend_request (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',

    from_role_id BIGINT UNSIGNED NOT NULL COMMENT '申请人角色ID',
    from_role_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '申请人角色名(冗余)',
    to_role_id BIGINT UNSIGNED NOT NULL COMMENT '接收人角色ID',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '0=待处理 1=已同意 2=已拒绝',
    INDEX idx_to_status (to_role_id, status),
    INDEX idx_from_role (from_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='好友申请表';
