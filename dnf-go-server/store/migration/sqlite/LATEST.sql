-- SQLite Schema for DNF Go Server
-- 注意: SQLite不直接支持某些MySQL特性，需要做相应调整

-- 迁移版本控制表
CREATE TABLE IF NOT EXISTS schema_migrations (
    version TEXT PRIMARY KEY,
    applied_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    description TEXT DEFAULT ''
);

-- 账户表
CREATE TABLE IF NOT EXISTS account (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    openid TEXT NOT NULL UNIQUE,
    account_key TEXT NOT NULL,
    auth_key TEXT NOT NULL,
    last_login_at INTEGER DEFAULT 0,
    last_login_ip TEXT DEFAULT '',
    authority INTEGER DEFAULT 0,
    status INTEGER DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_account_openid ON account(openid);
CREATE INDEX IF NOT EXISTS idx_account_status ON account(status);

-- 角色表
CREATE TABLE IF NOT EXISTS role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    account_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    name TEXT NOT NULL,
    job INTEGER NOT NULL,
    level INTEGER DEFAULT 1,
    exp INTEGER DEFAULT 0,
    fatigue INTEGER DEFAULT 100,
    max_fatigue INTEGER DEFAULT 100,
    map_id INTEGER DEFAULT 0,
    x INTEGER DEFAULT 0,
    y INTEGER DEFAULT 0,
    channel INTEGER DEFAULT 0,
    UNIQUE(account_id, role_id),
    FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_role_account ON role(account_id);
CREATE INDEX IF NOT EXISTS idx_role_name ON role(name);

-- 角色属性表
CREATE TABLE IF NOT EXISTS role_attributes (
    role_id INTEGER PRIMARY KEY,
    hp INTEGER DEFAULT 100,
    max_hp INTEGER DEFAULT 100,
    mp INTEGER DEFAULT 100,
    max_mp INTEGER DEFAULT 100,
    strength INTEGER DEFAULT 10,
    intelligence INTEGER DEFAULT 10,
    vitality INTEGER DEFAULT 10,
    spirit INTEGER DEFAULT 10,
    physical_attack INTEGER DEFAULT 10,
    physical_defense INTEGER DEFAULT 5,
    magic_attack INTEGER DEFAULT 10,
    magic_defense INTEGER DEFAULT 5,
    move_speed INTEGER DEFAULT 100,
    attack_speed INTEGER DEFAULT 100,
    cast_speed INTEGER DEFAULT 100,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- 角色货币表
CREATE TABLE IF NOT EXISTS role_currency (
    role_id INTEGER PRIMARY KEY,
    gold INTEGER DEFAULT 0,
    coin INTEGER DEFAULT 0,
    fatigue INTEGER DEFAULT 100,
    max_fatigue INTEGER DEFAULT 100,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- 背包物品表
CREATE TABLE IF NOT EXISTS bag_item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    role_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    grid_index INTEGER NOT NULL,
    count INTEGER DEFAULT 1,
    is_equipped INTEGER DEFAULT 0,
    bind_type INTEGER DEFAULT 0,
    durability INTEGER DEFAULT 100,
    enhance_level INTEGER DEFAULT 0,
    attributes TEXT DEFAULT '',
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_bag_item_role ON bag_item(role_id);
CREATE INDEX IF NOT EXISTS idx_bag_item_item ON bag_item(item_id);

-- 任务表
CREATE TABLE IF NOT EXISTS quest (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    quest_id INTEGER NOT NULL UNIQUE,
    name TEXT NOT NULL,
    description TEXT DEFAULT '',
    type INTEGER DEFAULT 0,
    level_required INTEGER DEFAULT 1,
    job_required INTEGER DEFAULT 0,
    pre_quest_id INTEGER DEFAULT 0,
    target_type INTEGER DEFAULT 0,
    target_id INTEGER DEFAULT 0,
    target_count INTEGER DEFAULT 0,
    reward_exp INTEGER DEFAULT 0,
    reward_gold INTEGER DEFAULT 0,
    reward_items TEXT DEFAULT ''
);

CREATE INDEX IF NOT EXISTS idx_quest_quest_id ON quest(quest_id);

-- 角色任务表
CREATE TABLE IF NOT EXISTS role_quest (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    role_id INTEGER NOT NULL,
    quest_id INTEGER NOT NULL,
    status INTEGER DEFAULT 0,
    progress INTEGER DEFAULT 0,
    accepted_at INTEGER DEFAULT 0,
    completed_at INTEGER DEFAULT 0,
    UNIQUE(role_id, quest_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_role_quest_role ON role_quest(role_id);

-- 公会表
CREATE TABLE IF NOT EXISTS guild (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    name TEXT NOT NULL UNIQUE,
    level INTEGER DEFAULT 1,
    exp INTEGER DEFAULT 0,
    notice TEXT DEFAULT '',
    leader_id INTEGER NOT NULL,
    member_count INTEGER DEFAULT 1,
    max_members INTEGER DEFAULT 50,
    fund INTEGER DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_guild_leader ON guild(leader_id);

-- 公会成员表
CREATE TABLE IF NOT EXISTS guild_member (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    guild_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    position INTEGER DEFAULT 0,
    contribution INTEGER DEFAULT 0,
    UNIQUE(guild_id, role_id),
    FOREIGN KEY (guild_id) REFERENCES guild(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_guild_member_guild ON guild_member(guild_id);
CREATE INDEX IF NOT EXISTS idx_guild_member_role ON guild_member(role_id);

-- 好友表
CREATE TABLE IF NOT EXISTS friend (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    role_id INTEGER NOT NULL,
    friend_id INTEGER NOT NULL,
    friend_name TEXT DEFAULT '',
    intimacy INTEGER DEFAULT 0,
    friend_group TEXT DEFAULT '默认分组',
    UNIQUE(role_id, friend_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_friend_role ON friend(role_id);

-- 邮件表
CREATE TABLE IF NOT EXISTS mail (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    sender_id INTEGER DEFAULT 0,
    sender_name TEXT DEFAULT '',
    receiver_id INTEGER NOT NULL,
    title TEXT NOT NULL,
    content TEXT DEFAULT '',
    attachments TEXT DEFAULT '',
    gold INTEGER DEFAULT 0,
    is_read INTEGER DEFAULT 0,
    is_claimed INTEGER DEFAULT 0,
    expire_at INTEGER DEFAULT 0,
    FOREIGN KEY (receiver_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_mail_receiver ON mail(receiver_id);
CREATE INDEX IF NOT EXISTS idx_mail_expire ON mail(expire_at);

-- 系统设置表
CREATE TABLE IF NOT EXISTS system_setting (
    name TEXT PRIMARY KEY,
    value TEXT NOT NULL,
    description TEXT DEFAULT ''
);

-- 拍卖行物品表
CREATE TABLE IF NOT EXISTS auction_item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    seller_id INTEGER NOT NULL,
    seller_name TEXT DEFAULT '',
    item_id INTEGER NOT NULL,
    count INTEGER DEFAULT 1,
    price INTEGER DEFAULT 0,
    total_price INTEGER DEFAULT 0,
    duration INTEGER DEFAULT 24,
    status INTEGER DEFAULT 0,
    bidder_id INTEGER DEFAULT 0,
    bidder_name TEXT DEFAULT '',
    bid_price INTEGER DEFAULT 0,
    bid_count INTEGER DEFAULT 0,
    attributes TEXT DEFAULT '',
    end_time INTEGER DEFAULT 0,
    FOREIGN KEY (seller_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_auction_item_seller ON auction_item(seller_id);
CREATE INDEX IF NOT EXISTS idx_auction_item_item ON auction_item(item_id);
CREATE INDEX IF NOT EXISTS idx_auction_item_status ON auction_item(status);
CREATE INDEX IF NOT EXISTS idx_auction_item_end_time ON auction_item(end_time);

-- 拍卖历史表
CREATE TABLE IF NOT EXISTS auction_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    auction_id INTEGER NOT NULL,
    seller_id INTEGER NOT NULL,
    buyer_id INTEGER DEFAULT 0,
    item_id INTEGER NOT NULL,
    count INTEGER DEFAULT 1,
    final_price INTEGER DEFAULT 0,
    seller_income INTEGER DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_auction_history_seller ON auction_history(seller_id);
CREATE INDEX IF NOT EXISTS idx_auction_history_buyer ON auction_history(buyer_id);

-- 插入默认系统设置
INSERT OR IGNORE INTO system_setting (name, value, description) VALUES 
('basic', '{"version":"1.0.0","name":"DNF Go Server"}', '实例基本设置');
