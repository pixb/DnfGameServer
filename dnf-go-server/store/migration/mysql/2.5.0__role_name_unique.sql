-- ============================================
-- 2.5.0 角色名全局唯一约束
-- 2026-09-06 第四十轮: DB 层硬约束兜底 handler 软约束(第二十五轮)
-- 前置治理(一次性数据操作, 已执行): 重名组保留最新(MAX(id)), 其余改名 name_old_<id>(710 行)
-- name 列收窄为 VARCHAR(64)(64*4=256 字节 < 767, 兼容 MySQL 5.7 COMPACT 行格式索引限制)
-- ============================================

ALTER TABLE role MODIFY COLUMN name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '角色名(全局唯一)';

ALTER TABLE role ADD UNIQUE INDEX uk_name (name);
