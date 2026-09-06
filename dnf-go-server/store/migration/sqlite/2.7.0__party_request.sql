-- ============================================
-- 2.7.0 增量迁移：队伍加入申请记录
-- 2026-09-07 第五十二轮：半开放队伍(public_type=1)申请/队长接受流程实化
--   * t_party_request: 申请者(role_id) → 队伍(party_id)
--   * JOIN 半开放队伍产生申请(不直接入队); 队长 Accept 加入/Refuse 删除
-- ============================================

CREATE TABLE IF NOT EXISTS t_party_request (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    party_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    UNIQUE (party_id, role_id)
);
CREATE INDEX IF NOT EXISTS idx_pr_role ON t_party_request (role_id);
