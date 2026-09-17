-- 2026-09-08 第七十三轮: 物品模板体系实化——t_item_template 表 + 种子
-- 物品 id 体系梳理: 1001-1003 武器产物 / 2001-2003 基础材料 / 3001-3002 分解源 /
-- 20001 任务奖励 / 2013000000-0001 分解产物(保底)
CREATE TABLE IF NOT EXISTS t_item_template (
    item_id INT UNSIGNED PRIMARY KEY COMMENT '物品模板ID',
    name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '物品名称',
    item_type TINYINT NOT NULL DEFAULT 0 COMMENT '物品类型(0=材料 1=武器 2=防具 3=消耗品)',
    level INT NOT NULL DEFAULT 1 COMMENT '物品等级',
    bind_type TINYINT NOT NULL DEFAULT 0 COMMENT '默认绑定类型(0=无 1=装备绑定 2=拾取绑定)',
    sell_price INT NOT NULL DEFAULT 0 COMMENT '出售价格(金币)',
    description VARCHAR(255) NOT NULL DEFAULT '' COMMENT '物品描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物品模板配置表';

INSERT INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (1001, '钢铁短剑', 1, 1, 0, 100, '初级武器, 合成配方产物'),
    (1002, '精钢长剑', 1, 5, 0, 300, '进阶武器, 合成配方产物'),
    (1003, '秘银巨剑', 1, 10, 0, 800, '高级武器, 合成配方产物'),
    (2001, '铁矿石', 0, 1, 0, 5, '基础锻造材料'),
    (2002, '铜矿石', 0, 1, 0, 8, '基础锻造材料'),
    (2003, '银矿石', 0, 5, 0, 20, '中级锻造材料'),
    (3001, '精铁碎甲', 0, 3, 0, 15, '可分解的旧护甲'),
    (3002, '旧式护手', 0, 3, 0, 12, '可分解的旧护手'),
    (20001, '任务嘉奖箱', 3, 1, 0, 0, '完成任务获得的嘉奖宝箱'),
    (2013000000, '破损剑刃', 0, 1, 0, 2, '分解产物(保底)'),
    (2013000001, '破损护甲', 0, 1, 0, 3, '分解产物(保底)')
ON DUPLICATE KEY UPDATE item_id = item_id;
