-- 2026-09-08 第七十三轮: 物品模板体系实化——t_item_template 表 + 种子 (sqlite 同构)
CREATE TABLE IF NOT EXISTS t_item_template (
    item_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL DEFAULT '',
    item_type INTEGER NOT NULL DEFAULT 0,
    level INTEGER NOT NULL DEFAULT 1,
    bind_type INTEGER NOT NULL DEFAULT 0,
    sell_price INTEGER NOT NULL DEFAULT 0,
    description TEXT NOT NULL DEFAULT ''
);

INSERT OR IGNORE INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
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
    (2013000001, '破损护甲', 0, 1, 0, 3, '分解产物(保底)');
