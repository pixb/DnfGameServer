-- DNF Go Server 完整数据库 Schema
-- 版本: 1.0.0
-- 兼容: MySQL 5.7+

-- ============================================
-- 1. 迁移版本控制表
-- ============================================
CREATE TABLE IF NOT EXISTS schema_migrations (
    version VARCHAR(255) PRIMARY KEY,
    applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 2. 账户表 (从 Java 的 t_account 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS account (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    -- 原 t_account 字段映射
    openid VARCHAR(255) NOT NULL UNIQUE COMMENT '原 id 字段',
    account_key VARCHAR(255) NOT NULL DEFAULT '' COMMENT '原 accountkey',
    auth_key VARCHAR(255) NOT NULL DEFAULT '',
    last_login_at BIGINT NOT NULL DEFAULT 0 COMMENT '原 lastLoginTime',
    last_login_ip VARCHAR(45) NOT NULL DEFAULT '',
    authority INT NOT NULL DEFAULT 0 COMMENT '原 privilege',
    status INT NOT NULL DEFAULT 0 COMMENT '0=正常, 1=停用(原 isStop)',
    
    -- 原 t_account 其他字段保留
    accumulate_cera BIGINT NOT NULL DEFAULT 0 COMMENT '原 accumulatecera',
    user_id VARCHAR(255) DEFAULT NULL COMMENT '原 userID',
    passwd VARCHAR(255) DEFAULT NULL COMMENT '原 passwd',
    role_max_count INT NOT NULL DEFAULT 3 COMMENT '原 roleMaxCount',
    score INT NOT NULL DEFAULT 0 COMMENT '原 score',
    channel_no VARCHAR(255) DEFAULT NULL COMMENT '原 channelNo',
    zhanling_exp INT NOT NULL DEFAULT 0 COMMENT '原 zhanlingexp',
    
    -- JSON 字段
    money_box JSON COMMENT '原 moneyBox',
    epic_piece_box JSON COMMENT '原 epicPieceBox',
    mail_box JSON COMMENT '原 mailBox',
    acc_shop_info_box JSON COMMENT '原 accShopInfoBox',
    adventure_reap_info JSON COMMENT '原 adventureReapInfo',
    adventure_union_info JSON COMMENT '原 adventureUnionInfo',
    ad_storage_box JSON COMMENT '原 adStorageBox',
    adv_book_box JSON COMMENT '原 advBookBox',
    adv_union_sub_info_box JSON COMMENT '原 advUnionSubInfoBox',
    activity_box JSON COMMENT '原 activityBox',
    
    INDEX idx_openid (openid),
    INDEX idx_status (status),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表 - 兼容原 t_account';

-- ============================================
-- 3. 角色表 (从 Java 的 t_role 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS role (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    -- 与原表对应
    account_id BIGINT UNSIGNED NOT NULL COMMENT '关联 account.id',
    role_id INT NOT NULL COMMENT '原 roleId - 角色槽位1-4',
    name VARCHAR(255) NOT NULL COMMENT '原 name',
    job INT NOT NULL COMMENT '原 job',
    level INT NOT NULL DEFAULT 1 COMMENT '原 level',
    exp BIGINT NOT NULL DEFAULT 200 COMMENT '原 exp',
    fatigue INT NOT NULL DEFAULT 0 COMMENT '原 fatigue',
    max_fatigue INT NOT NULL DEFAULT 100,
    map_id INT NOT NULL DEFAULT 1 COMMENT '原 pos 中的地图ID',
    x INT NOT NULL DEFAULT 0,
    y INT NOT NULL DEFAULT 0,
    channel INT NOT NULL DEFAULT 1,
    
    -- 原表其他字段
    grow_type INT NOT NULL DEFAULT 0 COMMENT '原 growtype',
    sec_grow_type INT NOT NULL DEFAULT 0 COMMENT '原 secgrowtype',
    equip_score INT NOT NULL DEFAULT 229 COMMENT '原 equipscore',
    character_frame INT NOT NULL DEFAULT 0 COMMENT '原 characterframe',
    money INT NOT NULL DEFAULT 0 COMMENT '原 money - 金币',
    res_coin INT NOT NULL DEFAULT 0 COMMENT '原 rescoin - 复活币',
    contribution_coin INT NOT NULL DEFAULT 0 COMMENT '原 contributioncoin',
    magic_crystal INT NOT NULL DEFAULT 0 COMMENT '原 magiccrystal',
    high_magic_crystal INT NOT NULL DEFAULT 0 COMMENT '原 highmagiccrystal',
    cera_score INT NOT NULL DEFAULT 0 COMMENT '原 cerascore - 点券',
    pk_coin INT NOT NULL DEFAULT 0 COMMENT '原 pkcoin',
    friend_point INT NOT NULL DEFAULT 0 COMMENT '原 friendpoint',
    small_coin INT NOT NULL DEFAULT 0 COMMENT '原 smallcoin',
    avatar_visible_flags INT NOT NULL DEFAULT 0 COMMENT '原 avatarVisibleFlags',
    deletion_status INT NOT NULL DEFAULT 0 COMMENT '原 deletionstatus',
    deletion_time BIGINT NOT NULL DEFAULT 0 COMMENT '原 deletiontime',
    change_name BOOLEAN NOT NULL DEFAULT FALSE COMMENT '原 changename',
    sp INT NOT NULL DEFAULT 40 COMMENT '原 sp',
    tp INT NOT NULL DEFAULT 0 COMMENT '原 tp',
    add_sp INT NOT NULL DEFAULT 0 COMMENT '原 addsp',
    add_tp INT NOT NULL DEFAULT 0 COMMENT '原 addtp',
    day INT NOT NULL DEFAULT 0 COMMENT '原 day',
    score INT NOT NULL DEFAULT 0 COMMENT '原 score',
    q_index INT NOT NULL DEFAULT 100110 COMMENT '原 qindex',
    dist_name VARCHAR(255) DEFAULT NULL COMMENT '原 distName',
    server_name VARCHAR(255) DEFAULT NULL COMMENT '原 servername',
    lock_time BIGINT NOT NULL DEFAULT 0 COMMENT '原 lockTime - 封号时间',
    word_time BIGINT NOT NULL DEFAULT 0 COMMENT '原 wordTime - 禁言时间',
    weapon_index INT NOT NULL DEFAULT 0 COMMENT '原 weaponIndex',
    exp_ratio INT NOT NULL DEFAULT 100 COMMENT '原 expratio',
    fatigue_ratio INT NOT NULL DEFAULT 100 COMMENT '原 fatigueratio',
    adventure_name VARCHAR(255) DEFAULT '' COMMENT '原 adventurename',
    
    -- JSON 字段
    pos JSON COMMENT '原 pos - 坐标数据',
    server_simple_data_box JSON COMMENT '原 serverSimpleDataBox',
    friend_box JSON COMMENT '原 friendBox',
    title_box JSON COMMENT '原 titleBox',
    avatar_box JSON COMMENT '原 avatarBox',
    emblem_box JSON COMMENT '原 emblemBox',
    card_box JSON COMMENT '原 cardBox',
    creature_box JSON COMMENT '原 creatureBox',
    artifact_box JSON COMMENT '原 artifactBox',
    equip_box JSON COMMENT '原 equipBox',
    equipped_box JSON COMMENT '原 equippedBox',
    material_box JSON COMMENT '原 materialBox',
    consumable_box JSON COMMENT '原 consumableBox',
    role_shop_info_box JSON COMMENT '原 roleShopInfoBox',
    crack_equip_box JSON COMMENT '原 crackEquipBox',
    crack_box JSON COMMENT '原 crackBox',
    damage_box JSON COMMENT '原 damageBox',
    chat_frame_box JSON COMMENT '原 chatFrameBox',
    char_frame_box JSON COMMENT '原 charFrameBox',
    sd_avatar_box JSON COMMENT '原 sdAvatarBox',
    bookmark_box JSON COMMENT '原 bookmarkBox',
    scroll_box JSON COMMENT '原 scrollBox',
    money_box JSON COMMENT '原 moneyBox',
    cera_shop_buy_info JSON COMMENT '原 ceraShopBuyInfo',
    tuto_box JSON COMMENT '原 tutoBox',
    skill_box JSON COMMENT '原 skillBox',
    skill_slot_box JSON COMMENT '原 skillslotBox',
    dungeon_tickets_box JSON COMMENT '原 dungeonTicketsBox',
    tonic_box JSON COMMENT '原 tonicBox',
    mail_box JSON COMMENT '原 mailBox',
    sys_mail_box JSON COMMENT '原 sysMailBox',
    char_storage_box JSON COMMENT '原 charStorageBox',
    re_pur_sto_item JSON COMMENT '原 rePurStoItem',
    tower_info_box JSON COMMENT '原 towerInfoBox',
    creature_errand_box JSON COMMENT '原 creatureErrandBox',
    local_reward_box JSON COMMENT '原 localRewardBox',
    quest_info_box JSON COMMENT '原 questInfoBox',
    sys_buff_box JSON COMMENT '原 sysBuffBox',
    clear_dungeon_box JSON COMMENT '原 clearDungeonBox',
    achievement_box JSON COMMENT '原 achievementBox',
    collection_box JSON COMMENT '原 collectionBox',
    note_msg_box JSON COMMENT '原 noteMsgBox',
    essence_box JSON COMMENT '原 essenceBox',
    auction_box JSON COMMENT '原 auctionBox',
    
    UNIQUE KEY uk_account_role (account_id, role_id),
    INDEX idx_account_id (account_id),
    INDEX idx_name (name),
    INDEX idx_openid_role (account_id, role_id),
    FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表 - 兼容原 t_role';

-- ============================================
-- 4. 角色属性表 (从 t_role 的属性拆分)
-- ============================================
CREATE TABLE IF NOT EXISTS role_attributes (
    role_id BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    
    hp INT NOT NULL DEFAULT 100,
    max_hp INT NOT NULL DEFAULT 100,
    mp INT NOT NULL DEFAULT 100,
    max_mp INT NOT NULL DEFAULT 100,
    strength INT NOT NULL DEFAULT 10,
    intelligence INT NOT NULL DEFAULT 10,
    vitality INT NOT NULL DEFAULT 10,
    spirit INT NOT NULL DEFAULT 10,
    physical_attack INT NOT NULL DEFAULT 10,
    physical_defense INT NOT NULL DEFAULT 10,
    magic_attack INT NOT NULL DEFAULT 10,
    magic_defense INT NOT NULL DEFAULT 10,
    move_speed INT NOT NULL DEFAULT 100,
    attack_speed INT NOT NULL DEFAULT 100,
    cast_speed INT NOT NULL DEFAULT 100,
    
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色属性表';

-- ============================================
-- 5. 角色货币表 (从 t_role 的货币字段拆分)
-- ============================================
CREATE TABLE IF NOT EXISTS role_currency (
    role_id BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    gold BIGINT NOT NULL DEFAULT 0 COMMENT '金币',
    coin BIGINT NOT NULL DEFAULT 0 COMMENT '代币',
    fatigue INT NOT NULL DEFAULT 100,
    max_fatigue INT NOT NULL DEFAULT 100,
    
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色货币表';

-- ============================================
-- 6. 背包物品表
-- ============================================
CREATE TABLE IF NOT EXISTS bag_item (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL,
    item_id INT NOT NULL,
    grid_index INT NOT NULL COMMENT '背包格子索引',
    count INT NOT NULL DEFAULT 1,
    is_equipped BOOLEAN NOT NULL DEFAULT FALSE,
    bind_type INT NOT NULL DEFAULT 0 COMMENT '0=无绑定, 1=装备绑定, 2=拾取绑定',
    durability INT NOT NULL DEFAULT 100,
    enhance_level INT NOT NULL DEFAULT 0,
    attributes JSON COMMENT '额外属性',
    
    INDEX idx_role_id (role_id),
    INDEX idx_item_id (item_id),
    UNIQUE KEY uk_role_grid (role_id, grid_index),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='背包物品表';

-- ============================================
-- 7. 公会表
-- ============================================
CREATE TABLE IF NOT EXISTS guild (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    name VARCHAR(32) NOT NULL UNIQUE,
    level INT NOT NULL DEFAULT 1,
    exp BIGINT NOT NULL DEFAULT 0,
    notice TEXT COMMENT '公会公告',
    leader_id BIGINT UNSIGNED NOT NULL,
    member_count INT NOT NULL DEFAULT 1,
    max_members INT NOT NULL DEFAULT 50,
    fund BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_name (name),
    INDEX idx_leader (leader_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公会表';

-- ============================================
-- 8. 公会成员表
-- ============================================
CREATE TABLE IF NOT EXISTS guild_member (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    guild_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL UNIQUE,
    position INT NOT NULL DEFAULT 0 COMMENT '0=成员, 1=精英, 2=副会长, 3=会长',
    contribution BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_guild_id (guild_id),
    FOREIGN KEY (guild_id) REFERENCES guild(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公会成员表';

-- ============================================
-- 9. 任务定义表 (从 p_taskinfo 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS quest (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    quest_id INT NOT NULL UNIQUE COMMENT '原 id',
    name VARCHAR(255) NOT NULL COMMENT '原 name',
    description TEXT COMMENT '原 description',
    type INT NOT NULL DEFAULT 0 COMMENT '原 type',
    level_required INT NOT NULL DEFAULT 1 COMMENT '接取等级',
    job_required INT NOT NULL DEFAULT 0 COMMENT '职业限制',
    pre_quest_id INT NOT NULL DEFAULT 0 COMMENT '前置任务',
    target_type INT NOT NULL DEFAULT 0 COMMENT '目标类型: 0=击杀, 1=收集, 2=对话',
    target_id INT NOT NULL DEFAULT 0,
    target_count INT NOT NULL DEFAULT 1,
    reward_exp BIGINT NOT NULL DEFAULT 0,
    reward_gold BIGINT NOT NULL DEFAULT 0,
    reward_items JSON,
    
    INDEX idx_quest_id (quest_id),
    INDEX idx_type (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务定义表 - 兼容原 p_taskinfo/p_taskset';

-- ============================================
-- 10. 角色任务表
-- ============================================
CREATE TABLE IF NOT EXISTS role_quest (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL,
    quest_id INT NOT NULL,
    status INT NOT NULL DEFAULT 0 COMMENT '0=未接, 1=进行中, 2=可完成, 3=已完成',
    progress INT NOT NULL DEFAULT 0,
    accepted_at BIGINT NOT NULL DEFAULT 0,
    completed_at BIGINT NOT NULL DEFAULT 0,
    
    UNIQUE KEY uk_role_quest (role_id, quest_id),
    INDEX idx_role_id (role_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色任务进度表';

-- ============================================
-- 11. 好友表
-- ============================================
CREATE TABLE IF NOT EXISTS friend (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL,
    friend_id BIGINT UNSIGNED NOT NULL COMMENT '好友角色ID',
    friend_name VARCHAR(32) NOT NULL,
    intimacy INT NOT NULL DEFAULT 0 COMMENT '亲密度',
    friend_group VARCHAR(32) NOT NULL DEFAULT '默认分组',
    
    UNIQUE KEY uk_role_friend (role_id, friend_id),
    INDEX idx_role_id (role_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='好友表';

-- ============================================
-- 12. 邮件表
-- ============================================
CREATE TABLE IF NOT EXISTS mail (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    sender_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
    sender_name VARCHAR(32) NOT NULL DEFAULT '系统',
    receiver_id BIGINT UNSIGNED NOT NULL,
    title VARCHAR(128) NOT NULL,
    content TEXT,
    attachments JSON COMMENT '附件物品',
    gold BIGINT NOT NULL DEFAULT 0,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    is_claimed BOOLEAN NOT NULL DEFAULT FALSE,
    expire_at BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_receiver (receiver_id),
    INDEX idx_expire (expire_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件表';

-- ============================================
-- 13. 拍卖表 (从 t_auction 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS auction (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    item_id INT NOT NULL,
    seller_id BIGINT UNSIGNED NOT NULL,
    seller_name VARCHAR(32) NOT NULL,
    price BIGINT NOT NULL DEFAULT 0,
    bid_price BIGINT NOT NULL DEFAULT 0,
    bidder_id BIGINT UNSIGNED DEFAULT NULL,
    start_time BIGINT NOT NULL DEFAULT 0,
    end_time BIGINT NOT NULL DEFAULT 0,
    status INT NOT NULL DEFAULT 0 COMMENT '0=拍卖中, 1=已售出, 2=已取消',
    
    INDEX idx_seller (seller_id),
    INDEX idx_status (status),
    INDEX idx_end_time (end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='拍卖表 - 兼容原 t_auction';

-- ============================================
-- 14. 离线数据表 (从 t_offline 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS offline_data (
    role_id BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    data JSON COMMENT '离线数据',
    last_logout_time BIGINT NOT NULL DEFAULT 0,
    
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='离线数据表 - 兼容原 t_offline';

-- ============================================
-- 15. 系统设置表
-- ============================================
CREATE TABLE IF NOT EXISTS system_setting (
    name VARCHAR(256) PRIMARY KEY,
    value TEXT NOT NULL,
    description TEXT NOT NULL DEFAULT '',
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP())
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统设置表';

-- ============================================
-- 16. 公告表 (从 t_notice 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS notice (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    content TEXT NOT NULL COMMENT '原 content',
    type INT NOT NULL DEFAULT 0 COMMENT '0=普通公告, 1=滚动公告',
    start_time BIGINT NOT NULL DEFAULT 0,
    end_time BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_time (start_time, end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表 - 兼容原 t_notice';

-- ============================================
-- 17. 支付数据表 (从 t_paydata 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS pay_data (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    
    user_id VARCHAR(255) NOT NULL COMMENT '原 userid',
    order_id VARCHAR(255) NOT NULL UNIQUE COMMENT '原 orderid',
    pkg VARCHAR(255) COMMENT '原 pkg',
    money VARCHAR(255) COMMENT '原 money',
    game_name VARCHAR(255) COMMENT '原 gamename',
    app_channel VARCHAR(255) COMMENT '原 app_channel',
    user_channel VARCHAR(255) COMMENT '原 userchannel',
    status VARCHAR(255) COMMENT '原 status',
    
    INDEX idx_user_id (user_id),
    INDEX idx_order_id (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付数据表 - 兼容原 t_paydata';

-- ============================================
-- 18. 充值表 (从 t_charge 迁移)
-- ============================================
CREATE TABLE IF NOT EXISTS charge (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    
    trade_no VARCHAR(255) NOT NULL UNIQUE COMMENT '原 tradeNo',
    gold INT NOT NULL DEFAULT 0 COMMENT '原 gold',
    rmb INT NOT NULL DEFAULT 0 COMMENT '原 rmb',
    time_at BIGINT NOT NULL DEFAULT 0 COMMENT '原 time',
    user_name VARCHAR(255) NOT NULL COMMENT '原 userName',
    status INT NOT NULL DEFAULT 0 COMMENT '原 status',
    
    INDEX idx_user_name (user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值表 - 兼容原 t_charge';

-- ============================================
-- 19. 初始化数据
-- ============================================

-- 插入默认系统设置
INSERT INTO system_setting (name, value, description) VALUES
('server_version', '1.0.0', '服务器版本'),
('secret_key', '', 'JWT密钥'),
('max_online', '1000', '最大在线人数'),
('maintenance_mode', 'false', '维护模式')
ON DUPLICATE KEY UPDATE value = VALUES(value);

-- 插入测试公告
INSERT INTO notice (content, type) VALUES
('欢迎来到 DNF Go Server！', 0),
('服务器运行正常', 0)
ON DUPLICATE KEY UPDATE updated_at = UNIX_TIMESTAMP();

-- 记录迁移版本
INSERT INTO schema_migrations (version, description) VALUES
('1.0.0', 'Initial schema - 兼容 Java DnfGameServer')
ON DUPLICATE KEY UPDATE applied_at = CURRENT_TIMESTAMP;
