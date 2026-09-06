-- ============================================
-- 2.3.0 增量迁移：行为日志表
-- 2026-09-06 第十九轮：rank/log TCP handler 实化——日志模块落库。
-- 客户端经 TCP 文本命令(RECORD_LOG 等)上报行为日志, 落 t_behavior_log;
-- 支持查询/统计/按ID删除/按时间清理。
-- ============================================

CREATE TABLE IF NOT EXISTS t_behavior_log (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    role_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色ID(未登录会话为0)',
    module VARCHAR(64) NOT NULL DEFAULT '' COMMENT '日志模块',
    action VARCHAR(64) NOT NULL DEFAULT '' COMMENT '动作类型',
    level VARCHAR(16) NOT NULL DEFAULT 'info' COMMENT '日志级别: debug/info/warn/error',
    content VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '日志内容',
    INDEX idx_role_created (role_id, created_at),
    INDEX idx_level (level)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行为日志表';
