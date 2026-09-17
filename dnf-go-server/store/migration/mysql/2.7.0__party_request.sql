-- ============================================
-- 2.7.0 增量迁移：队伍加入申请记录
-- 2026-09-07 第五十二轮：半开放队伍(public_type=1)申请/队长接受流程实化
--   * t_party_request: 申请者(role_id) → 队伍(party_id)
--   * JOIN 半开放队伍产生申请(不直接入队); 队长 Accept 加入/Refuse 删除
-- ============================================

CREATE TABLE IF NOT EXISTS t_party_request (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    party_id BIGINT UNSIGNED NOT NULL COMMENT '队伍ID',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '申请者角色ID',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_party_role (party_id, role_id),
    KEY idx_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='队伍加入申请表';
