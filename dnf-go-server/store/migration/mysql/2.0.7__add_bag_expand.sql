-- ============================================
-- 2.0.7 增量迁移：背包扩容容量存储
-- 2026-09-06：BAG_EXPAND 命令此前为 mock(响应固定 60 格)，
-- 本次新增容量表使扩容结果可持久化。
-- ============================================

-- 背包扩容表
CREATE TABLE IF NOT EXISTS t_bag_expand (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    bag_type INT NOT NULL DEFAULT 1 COMMENT '背包类型: 1=普通 2=仓库',
    capacity INT NOT NULL DEFAULT 0 COMMENT '扩容槽位数(基础 50 之上)',
    UNIQUE KEY uk_role_bag (role_id, bag_type),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='背包扩容表';
