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

-- 成就配置表
CREATE TABLE IF NOT EXISTS t_achievement_config (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    achievement_id INTEGER NOT NULL UNIQUE,
    name TEXT NOT NULL,
    description TEXT DEFAULT '',
    type INTEGER NOT NULL DEFAULT 0,
    target_value INTEGER NOT NULL DEFAULT 0,
    reward_type INTEGER NOT NULL DEFAULT 0,
    reward_index INTEGER NOT NULL DEFAULT 0,
    reward_count INTEGER NOT NULL DEFAULT 0,
    bonus_reward INTEGER NOT NULL DEFAULT 0,
    status INTEGER NOT NULL DEFAULT 1
);

CREATE INDEX IF NOT EXISTS idx_achievement_config_id ON t_achievement_config(achievement_id);

-- 成就记录表
CREATE TABLE IF NOT EXISTS t_achievement_record (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    role_id INTEGER NOT NULL,
    achievement_id INTEGER NOT NULL,
    progress INTEGER NOT NULL DEFAULT 0,
    completed INTEGER NOT NULL DEFAULT 0,
    rewarded INTEGER NOT NULL DEFAULT 0,
    complete_time INTEGER DEFAULT 0,
    reward_time INTEGER DEFAULT 0,
    UNIQUE(role_id, achievement_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_achievement_record_role ON t_achievement_record(role_id);
CREATE INDEX IF NOT EXISTS idx_achievement_record_achievement ON t_achievement_record(achievement_id);

-- 成就奖励表
CREATE TABLE IF NOT EXISTS t_achievement_reward (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    role_id INTEGER NOT NULL,
    achievement_id INTEGER NOT NULL,
    reward_type INTEGER NOT NULL,
    reward_index INTEGER NOT NULL,
    reward_count INTEGER NOT NULL,
    claimed INTEGER NOT NULL DEFAULT 0,
    claim_time INTEGER DEFAULT 0,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_achievement_reward_role ON t_achievement_reward(role_id);
CREATE INDEX IF NOT EXISTS idx_achievement_reward_achievement ON t_achievement_reward(achievement_id);

-- 冒险联盟表
CREATE TABLE IF NOT EXISTS t_adventure_union (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL UNIQUE,
    name TEXT NOT NULL,
    exp INTEGER DEFAULT 0,
    level INTEGER DEFAULT 1,
    day INTEGER DEFAULT 1,
    typical_character_guid INTEGER DEFAULT 0,
    last_change_name_time INTEGER DEFAULT 0,
    shareboard_background INTEGER DEFAULT 0,
    shareboard_frame INTEGER DEFAULT 0,
    shareboard_show_antievil_score INTEGER DEFAULT 0,
    auto_search_count INTEGER DEFAULT 0,
    shareboard_total_antievil_score INTEGER DEFAULT 0,
    shareboard_antievil_score_refresh INTEGER DEFAULT 0,
    is_adventure_condition INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_adventure_union_role ON t_adventure_union(role_id);

-- 冒险联盟远征表
CREATE TABLE IF NOT EXISTS t_adventure_union_expedition (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    expedition_id INTEGER NOT NULL,
    expedition_type INTEGER NOT NULL,
    status INTEGER DEFAULT 0,
    start_time INTEGER DEFAULT 0,
    end_time INTEGER DEFAULT 0,
    reward_claimed INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_adventure_union_expedition_role ON t_adventure_union_expedition(role_id);

-- 冒险联盟讨伐表
CREATE TABLE IF NOT EXISTS t_adventure_union_subdue (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    subdue_id INTEGER NOT NULL,
    subdue_type INTEGER NOT NULL,
    character_guid INTEGER DEFAULT 0,
    status INTEGER DEFAULT 0,
    start_time INTEGER DEFAULT 0,
    end_time INTEGER DEFAULT 0,
    reward_claimed INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_adventure_union_subdue_role ON t_adventure_union_subdue(role_id);

-- 冒险联盟收藏表
CREATE TABLE IF NOT EXISTS t_adventure_union_collection (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    collection_id INTEGER NOT NULL,
    progress INTEGER DEFAULT 0,
    completed INTEGER DEFAULT 0,
    reward_claimed INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_adventure_union_collection_role ON t_adventure_union_collection(role_id);

-- 冒险联盟展示板槽位表
CREATE TABLE IF NOT EXISTS t_adventure_union_shareboard_slot (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    slot_id INTEGER NOT NULL,
    slot_type INTEGER NOT NULL,
    item_id INTEGER DEFAULT 0,
    item_count INTEGER DEFAULT 0,
    show INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_adventure_union_shareboard_slot_role ON t_adventure_union_shareboard_slot(role_id);

-- 冒险奖励表
CREATE TABLE IF NOT EXISTS t_adventure_reap (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    reap_id INTEGER NOT NULL,
    reward_type INTEGER NOT NULL,
    reward_index INTEGER NOT NULL,
    reward_count INTEGER NOT NULL,
    claimed INTEGER DEFAULT 0,
    claim_time INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_adventure_reap_role ON t_adventure_reap(role_id);

-- 冒险联盟等级奖励表
CREATE TABLE IF NOT EXISTS t_adventure_union_level_reward (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    level INTEGER NOT NULL,
    reward_type INTEGER NOT NULL,
    reward_index INTEGER NOT NULL,
    reward_count INTEGER NOT NULL,
    claimed INTEGER DEFAULT 0,
    claim_time INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_adventure_union_level_reward_role ON t_adventure_union_level_reward(role_id);

-- 组队表
CREATE TABLE IF NOT EXISTS t_party (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    party_id INTEGER NOT NULL UNIQUE,
    leader_id INTEGER NOT NULL,
    name TEXT NOT NULL,
    max_members INTEGER DEFAULT 4,
    status INTEGER DEFAULT 0,
    dungeon_index INTEGER DEFAULT 0,
    room_id INTEGER DEFAULT 0,
    min_level INTEGER DEFAULT 1,
    max_level INTEGER DEFAULT 99,
    area INTEGER DEFAULT 0,
    subtype INTEGER DEFAULT 0,
    stage_index INTEGER DEFAULT 0,
    public_type INTEGER DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (leader_id) REFERENCES role(role_id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_party_leader ON t_party(leader_id);
CREATE INDEX IF NOT EXISTS idx_party_status ON t_party(status);
CREATE INDEX IF NOT EXISTS idx_party_dungeon ON t_party(dungeon_index);

-- 组队成员表
CREATE TABLE IF NOT EXISTS t_party_member (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    row_status TEXT NOT NULL DEFAULT 'NORMAL',
    party_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    player_id INTEGER NOT NULL,
    team_type INTEGER DEFAULT 0,
    status INTEGER DEFAULT 0,
    ping INTEGER DEFAULT 0,
    join_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (party_id) REFERENCES t_party(party_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_party_member_party ON t_party_member(party_id);
CREATE INDEX IF NOT EXISTS idx_party_member_role ON t_party_member(role_id);

-- 徽章升级记录表
CREATE TABLE IF NOT EXISTS t_emblem_upgrade (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    emblem_index INTEGER NOT NULL,
    level INTEGER NOT NULL,
    try_count INTEGER NOT NULL,
    success_count INTEGER NOT NULL,
    cost_money INTEGER NOT NULL,
    cost_talisman INTEGER NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_emblem_upgrade_role ON t_emblem_upgrade(role_id);

-- 时装合成记录表
CREATE TABLE IF NOT EXISTS t_avatar_compose (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    avatar_guids TEXT NOT NULL,
    result_index INTEGER NOT NULL,
    result_guid INTEGER NOT NULL,
    cost_money INTEGER NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_avatar_compose_role ON t_avatar_compose(role_id);

-- 物品制作记录表
CREATE TABLE IF NOT EXISTS t_item_production (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    slot_index INTEGER NOT NULL,
    recipe_index INTEGER NOT NULL,
    count INTEGER NOT NULL,
    result_index INTEGER NOT NULL,
    result_count INTEGER NOT NULL,
    cost_money INTEGER NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_item_production_role ON t_item_production(role_id);

-- 物品合成记录表
CREATE TABLE IF NOT EXISTS t_item_combine (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    target_index INTEGER NOT NULL,
    material_list TEXT NOT NULL,
    count INTEGER NOT NULL,
    result_guid INTEGER NOT NULL,
    cost_money INTEGER NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_item_combine_role ON t_item_combine(role_id);

-- 物品分解记录表
CREATE TABLE IF NOT EXISTS t_item_disjoint (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    equip_guids TEXT NOT NULL,
    material_list TEXT NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_item_disjoint_role ON t_item_disjoint(role_id);

-- 卡片合成记录表
CREATE TABLE IF NOT EXISTS t_card_compose (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    card_list TEXT NOT NULL,
    result_index INTEGER NOT NULL,
    result_count INTEGER NOT NULL,
    cost_money INTEGER NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_card_compose_role ON t_card_compose(role_id);

-- 衣柜槽位表
CREATE TABLE IF NOT EXISTS t_wardrobe_slot (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    slot_index INTEGER NOT NULL,
    avatar_guid INTEGER NOT NULL,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_wardrobe_slot_role ON t_wardrobe_slot(role_id);

-- 徽章配置表
CREATE TABLE IF NOT EXISTS t_consume_config (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id INTEGER NOT NULL UNIQUE,
    level INTEGER NOT NULL,
    name TEXT NOT NULL,
    description TEXT DEFAULT ''
);

CREATE INDEX IF NOT EXISTS idx_consume_config_item ON t_consume_config(item_id);

-- 角色货币表
CREATE TABLE IF NOT EXISTS t_role_currency (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL UNIQUE,
    money INTEGER NOT NULL DEFAULT 0,
    create_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    update_time INTEGER NOT NULL DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_role_currency_role ON t_role_currency(role_id);
