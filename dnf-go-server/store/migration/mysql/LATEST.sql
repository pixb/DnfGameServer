-- DNF Go Server 瀹屾暣鏁版嵁搴?Schema
-- 鐗堟湰: 1.0.0
-- 鍏煎: MySQL 5.7+

-- ============================================
-- 1. 杩佺Щ鐗堟湰鎺у埗琛?
-- ============================================
CREATE TABLE IF NOT EXISTS schema_migrations (
    version VARCHAR(255) PRIMARY KEY,
    applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- 2. 璐︽埛琛?(浠?Java 鐨?t_account 杩佺Щ)
-- ============================================
CREATE TABLE IF NOT EXISTS account (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    -- 鍘?t_account 瀛楁鏄犲皠
    openid VARCHAR(255) NOT NULL UNIQUE COMMENT '鍘?id 瀛楁',
    account_key VARCHAR(255) NOT NULL DEFAULT '' COMMENT '鍘?accountkey',
    auth_key VARCHAR(255) NOT NULL DEFAULT '',
    last_login_at BIGINT NOT NULL DEFAULT 0 COMMENT '鍘?lastLoginTime',
    last_login_ip VARCHAR(45) NOT NULL DEFAULT '',
    authority INT NOT NULL DEFAULT 0 COMMENT '鍘?privilege',
    status INT NOT NULL DEFAULT 0 COMMENT '0=姝ｅ父, 1=鍋滅敤(鍘?isStop)',
    
    -- 鍘?t_account 鍏朵粬瀛楁淇濈暀
    accumulate_cera BIGINT NOT NULL DEFAULT 0 COMMENT '鍘?accumulatecera',
    user_id VARCHAR(255) DEFAULT NULL COMMENT '鍘?userID',
    passwd VARCHAR(255) DEFAULT NULL COMMENT '鍘?passwd',
    role_max_count INT NOT NULL DEFAULT 3 COMMENT '鍘?roleMaxCount',
    score INT NOT NULL DEFAULT 0 COMMENT '鍘?score',
    channel_no VARCHAR(255) DEFAULT NULL COMMENT '鍘?channelNo',
    zhanling_exp INT NOT NULL DEFAULT 0 COMMENT '鍘?zhanlingexp',
    
    -- JSON 瀛楁
    money_box JSON COMMENT '鍘?moneyBox',
    epic_piece_box JSON COMMENT '鍘?epicPieceBox',
    mail_box JSON COMMENT '鍘?mailBox',
    acc_shop_info_box JSON COMMENT '鍘?accShopInfoBox',
    adventure_reap_info JSON COMMENT '鍘?adventureReapInfo',
    adventure_union_info JSON COMMENT '鍘?adventureUnionInfo',
    ad_storage_box JSON COMMENT '鍘?adStorageBox',
    adv_book_box JSON COMMENT '鍘?advBookBox',
    adv_union_sub_info_box JSON COMMENT '鍘?advUnionSubInfoBox',
    activity_box JSON COMMENT '鍘?activityBox',
    
    INDEX idx_openid (openid),
    INDEX idx_status (status),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='璐︽埛琛?- 鍏煎鍘?t_account';

-- ============================================
-- 3. 瑙掕壊琛?(浠?Java 鐨?t_role 杩佺Щ)
-- ============================================
CREATE TABLE IF NOT EXISTS role (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    -- 涓庡師琛ㄥ搴?
    account_id BIGINT UNSIGNED NOT NULL COMMENT '鍏宠仈 account.id',
    role_id INT NOT NULL COMMENT '鍘?roleId - 瑙掕壊妲戒綅1-4',
    name VARCHAR(255) NOT NULL COMMENT '鍘?name',
    job INT NOT NULL COMMENT '鍘?job',
    level INT NOT NULL DEFAULT 1 COMMENT '鍘?level',
    exp BIGINT NOT NULL DEFAULT 200 COMMENT '鍘?exp',
    fatigue INT NOT NULL DEFAULT 0 COMMENT '鍘?fatigue',
    max_fatigue INT NOT NULL DEFAULT 100,
    map_id INT NOT NULL DEFAULT 1 COMMENT '鍘?pos 涓殑鍦板浘ID',
    dungeon_id INT NOT NULL DEFAULT 0 COMMENT '副本地图ID(2.10.0)',
    x INT NOT NULL DEFAULT 0,
    y INT NOT NULL DEFAULT 0,
    pos_z FLOAT NOT NULL DEFAULT 0 COMMENT 'Z坐标(2.10.0)',
    channel INT NOT NULL DEFAULT 1,
    
    -- 鍘熻〃鍏朵粬瀛楁
    grow_type INT NOT NULL DEFAULT 0 COMMENT '鍘?growtype',
    sec_grow_type INT NOT NULL DEFAULT 0 COMMENT '鍘?secgrowtype',
    equip_score INT NOT NULL DEFAULT 229 COMMENT '鍘?equipscore',
    character_frame INT NOT NULL DEFAULT 0 COMMENT '鍘?characterframe',
    money INT NOT NULL DEFAULT 0 COMMENT '鍘?money - 閲戝竵',
    res_coin INT NOT NULL DEFAULT 0 COMMENT '鍘?rescoin - 澶嶆椿甯?,
    contribution_coin INT NOT NULL DEFAULT 0 COMMENT '鍘?contributioncoin',
    magic_crystal INT NOT NULL DEFAULT 0 COMMENT '鍘?magiccrystal',
    high_magic_crystal INT NOT NULL DEFAULT 0 COMMENT '鍘?highmagiccrystal',
    cera_score INT NOT NULL DEFAULT 0 COMMENT '鍘?cerascore - 鐐瑰埜',
    pk_coin INT NOT NULL DEFAULT 0 COMMENT '鍘?pkcoin',
    friend_point INT NOT NULL DEFAULT 0 COMMENT '鍘?friendpoint',
    small_coin INT NOT NULL DEFAULT 0 COMMENT '鍘?smallcoin',
    avatar_visible_flags INT NOT NULL DEFAULT 0 COMMENT '鍘?avatarVisibleFlags',
    deletion_status INT NOT NULL DEFAULT 0 COMMENT '鍘?deletionstatus',
    deletion_time BIGINT NOT NULL DEFAULT 0 COMMENT '鍘?deletiontime',
    change_name BOOLEAN NOT NULL DEFAULT FALSE COMMENT '鍘?changename',
    sp INT NOT NULL DEFAULT 40 COMMENT '鍘?sp',
    tp INT NOT NULL DEFAULT 0 COMMENT '鍘?tp',
    add_sp INT NOT NULL DEFAULT 0 COMMENT '鍘?addsp',
    add_tp INT NOT NULL DEFAULT 0 COMMENT '鍘?addtp',
    day INT NOT NULL DEFAULT 0 COMMENT '鍘?day',
    score INT NOT NULL DEFAULT 0 COMMENT '鍘?score',
    q_index INT NOT NULL DEFAULT 100110 COMMENT '鍘?qindex',
    dist_name VARCHAR(255) DEFAULT NULL COMMENT '鍘?distName',
    server_name VARCHAR(255) DEFAULT NULL COMMENT '鍘?servername',
    lock_time BIGINT NOT NULL DEFAULT 0 COMMENT '鍘?lockTime - 灏佸彿鏃堕棿',
    word_time BIGINT NOT NULL DEFAULT 0 COMMENT '鍘?wordTime - 绂佽█鏃堕棿',
    weapon_index INT NOT NULL DEFAULT 0 COMMENT '鍘?weaponIndex',
    exp_ratio INT NOT NULL DEFAULT 100 COMMENT '鍘?expratio',
    fatigue_ratio INT NOT NULL DEFAULT 100 COMMENT '鍘?fatigueratio',
    adventure_name VARCHAR(255) DEFAULT '' COMMENT '鍘?adventurename',
    
    -- JSON 瀛楁
    pos JSON COMMENT '鍘?pos - 鍧愭爣鏁版嵁',
    server_simple_data_box JSON COMMENT '鍘?serverSimpleDataBox',
    friend_box JSON COMMENT '鍘?friendBox',
    title_box JSON COMMENT '鍘?titleBox',
    avatar_box JSON COMMENT '鍘?avatarBox',
    emblem_box JSON COMMENT '鍘?emblemBox',
    card_box JSON COMMENT '鍘?cardBox',
    creature_box JSON COMMENT '鍘?creatureBox',
    artifact_box JSON COMMENT '鍘?artifactBox',
    equip_box JSON COMMENT '鍘?equipBox',
    equipped_box JSON COMMENT '鍘?equippedBox',
    material_box JSON COMMENT '鍘?materialBox',
    consumable_box JSON COMMENT '鍘?consumableBox',
    role_shop_info_box JSON COMMENT '鍘?roleShopInfoBox',
    crack_equip_box JSON COMMENT '鍘?crackEquipBox',
    crack_box JSON COMMENT '鍘?crackBox',
    damage_box JSON COMMENT '鍘?damageBox',
    chat_frame_box JSON COMMENT '鍘?chatFrameBox',
    char_frame_box JSON COMMENT '鍘?charFrameBox',
    sd_avatar_box JSON COMMENT '鍘?sdAvatarBox',
    bookmark_box JSON COMMENT '鍘?bookmarkBox',
    scroll_box JSON COMMENT '鍘?scrollBox',
    money_box JSON COMMENT '鍘?moneyBox',
    cera_shop_buy_info JSON COMMENT '鍘?ceraShopBuyInfo',
    tuto_box JSON COMMENT '鍘?tutoBox',
    skill_box JSON COMMENT '鍘?skillBox',
    skill_slot_box JSON COMMENT '鍘?skillslotBox',
    dungeon_tickets_box JSON COMMENT '鍘?dungeonTicketsBox',
    tonic_box JSON COMMENT '鍘?tonicBox',
    mail_box JSON COMMENT '鍘?mailBox',
    sys_mail_box JSON COMMENT '鍘?sysMailBox',
    char_storage_box JSON COMMENT '鍘?charStorageBox',
    re_pur_sto_item JSON COMMENT '鍘?rePurStoItem',
    tower_info_box JSON COMMENT '鍘?towerInfoBox',
    creature_errand_box JSON COMMENT '鍘?creatureErrandBox',
    local_reward_box JSON COMMENT '鍘?localRewardBox',
    quest_info_box JSON COMMENT '鍘?questInfoBox',
    sys_buff_box JSON COMMENT '鍘?sysBuffBox',
    clear_dungeon_box JSON COMMENT '鍘?clearDungeonBox',
    achievement_box JSON COMMENT '鍘?achievementBox',
    collection_box JSON COMMENT '鍘?collectionBox',
    note_msg_box JSON COMMENT '鍘?noteMsgBox',
    essence_box JSON COMMENT '鍘?essenceBox',
    auction_box JSON COMMENT '鍘?auctionBox',
    
    UNIQUE KEY uk_account_role (account_id, role_id),
    INDEX idx_account_id (account_id),
    INDEX idx_name (name),
    INDEX idx_openid_role (account_id, role_id),
    FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='瑙掕壊琛?- 鍏煎鍘?t_role';

-- ============================================
-- 4. 瑙掕壊灞炴€ц〃 (浠?t_role 鐨勫睘鎬ф媶鍒?
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='瑙掕壊灞炴€ц〃';

-- ============================================
-- 5. 瑙掕壊璐у竵琛?(浠?t_role 鐨勮揣甯佸瓧娈垫媶鍒?
-- ============================================
CREATE TABLE IF NOT EXISTS role_currency (
    role_id BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    gold BIGINT NOT NULL DEFAULT 0 COMMENT '閲戝竵',
    coin BIGINT NOT NULL DEFAULT 0 COMMENT '浠ｅ竵',
    fatigue INT NOT NULL DEFAULT 100,
    max_fatigue INT NOT NULL DEFAULT 100,
    
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='瑙掕壊璐у竵琛?;

-- ============================================
-- 6. 鑳屽寘鐗╁搧琛?
-- ============================================
CREATE TABLE IF NOT EXISTS bag_item (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL,
    item_id INT NOT NULL,
    grid_index INT NOT NULL COMMENT '鑳屽寘鏍煎瓙绱㈠紩',
    count INT NOT NULL DEFAULT 1,
    is_equipped BOOLEAN NOT NULL DEFAULT FALSE,
    bind_type INT NOT NULL DEFAULT 0 COMMENT '0=鏃犵粦瀹? 1=瑁呭缁戝畾, 2=鎷惧彇缁戝畾',
    durability INT NOT NULL DEFAULT 100,
    enhance_level INT NOT NULL DEFAULT 0,
    attributes JSON COMMENT '棰濆灞炴€?,
    
    INDEX idx_role_id (role_id),
    INDEX idx_item_id (item_id),
    UNIQUE KEY uk_role_grid (role_id, grid_index),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鑳屽寘鐗╁搧琛?;

-- ============================================
-- 7. 鍏細琛?
-- ============================================
CREATE TABLE IF NOT EXISTS guild (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    name VARCHAR(32) NOT NULL UNIQUE,
    level INT NOT NULL DEFAULT 1,
    exp BIGINT NOT NULL DEFAULT 0,
    notice TEXT COMMENT '鍏細鍏憡',
    leader_id BIGINT UNSIGNED NOT NULL,
    member_count INT NOT NULL DEFAULT 1,
    max_members INT NOT NULL DEFAULT 50,
    fund BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_name (name),
    INDEX idx_leader (leader_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍏細琛?;

-- ============================================
-- 8. 鍏細鎴愬憳琛?
-- ============================================
CREATE TABLE IF NOT EXISTS guild_member (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    guild_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL UNIQUE,
    position INT NOT NULL DEFAULT 0 COMMENT '0=鎴愬憳, 1=绮捐嫳, 2=鍓細闀? 3=浼氶暱',
    contribution BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_guild_id (guild_id),
    FOREIGN KEY (guild_id) REFERENCES guild(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍏細鎴愬憳琛?;

-- ============================================
-- 9. 浠诲姟瀹氫箟琛?(浠?p_taskinfo 杩佺Щ)
-- ============================================
CREATE TABLE IF NOT EXISTS quest (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    quest_id INT NOT NULL UNIQUE COMMENT '鍘?id',
    name VARCHAR(255) NOT NULL COMMENT '鍘?name',
    description TEXT COMMENT '鍘?description',
    type INT NOT NULL DEFAULT 0 COMMENT '鍘?type',
    level_required INT NOT NULL DEFAULT 1 COMMENT '鎺ュ彇绛夌骇',
    job_required INT NOT NULL DEFAULT 0 COMMENT '鑱屼笟闄愬埗',
    pre_quest_id INT NOT NULL DEFAULT 0 COMMENT '鍓嶇疆浠诲姟',
    target_type INT NOT NULL DEFAULT 0 COMMENT '鐩爣绫诲瀷: 0=鍑绘潃, 1=鏀堕泦, 2=瀵硅瘽',
    target_id INT NOT NULL DEFAULT 0,
    target_count INT NOT NULL DEFAULT 1,
    reward_exp BIGINT NOT NULL DEFAULT 0,
    reward_gold BIGINT NOT NULL DEFAULT 0,
    reward_items JSON,
    
    INDEX idx_quest_id (quest_id),
    INDEX idx_type (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='浠诲姟瀹氫箟琛?- 鍏煎鍘?p_taskinfo/p_taskset';

-- ============================================
-- 10. 瑙掕壊浠诲姟琛?
-- ============================================
CREATE TABLE IF NOT EXISTS role_quest (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL,
    quest_id INT NOT NULL,
    status INT NOT NULL DEFAULT 0 COMMENT '0=鏈帴, 1=杩涜涓? 2=鍙畬鎴? 3=宸插畬鎴?,
    progress INT NOT NULL DEFAULT 0,
    accepted_at BIGINT NOT NULL DEFAULT 0,
    completed_at BIGINT NOT NULL DEFAULT 0,
    
    UNIQUE KEY uk_role_quest (role_id, quest_id),
    INDEX idx_role_id (role_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='瑙掕壊浠诲姟杩涘害琛?;

-- ============================================
-- 11. 濂藉弸琛?
-- ============================================
CREATE TABLE IF NOT EXISTS friend (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL,
    friend_id BIGINT UNSIGNED NOT NULL COMMENT '濂藉弸瑙掕壊ID',
    friend_name VARCHAR(32) NOT NULL,
    intimacy INT NOT NULL DEFAULT 0 COMMENT '浜插瘑搴?,
    friend_group VARCHAR(32) NOT NULL DEFAULT '榛樿鍒嗙粍',
    
    UNIQUE KEY uk_role_friend (role_id, friend_id),
    INDEX idx_role_id (role_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='濂藉弸琛?;

-- ============================================
-- 12. 閭欢琛?
-- ============================================
CREATE TABLE IF NOT EXISTS mail (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    sender_id BIGINT UNSIGNED NOT NULL DEFAULT 0,
    sender_name VARCHAR(32) NOT NULL DEFAULT '绯荤粺',
    receiver_id BIGINT UNSIGNED NOT NULL,
    title VARCHAR(128) NOT NULL,
    content TEXT,
    attachments JSON COMMENT '闄勪欢鐗╁搧',
    gold BIGINT NOT NULL DEFAULT 0,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    is_claimed BOOLEAN NOT NULL DEFAULT FALSE,
    expire_at BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_receiver (receiver_id),
    INDEX idx_expire (expire_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='閭欢琛?;

-- ============================================
-- 13. 鎷嶅崠琛?(浠?t_auction 杩佺Щ)
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
    status INT NOT NULL DEFAULT 0 COMMENT '0=鎷嶅崠涓? 1=宸插敭鍑? 2=宸插彇娑?,
    
    INDEX idx_seller (seller_id),
    INDEX idx_status (status),
    INDEX idx_end_time (end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鎷嶅崠琛?- 鍏煎鍘?t_auction';

-- ============================================
-- 14. 绂荤嚎鏁版嵁琛?(浠?t_offline 杩佺Щ)
-- ============================================
CREATE TABLE IF NOT EXISTS offline_data (
    role_id BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    data JSON COMMENT '绂荤嚎鏁版嵁',
    last_logout_time BIGINT NOT NULL DEFAULT 0,
    
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='绂荤嚎鏁版嵁琛?- 鍏煎鍘?t_offline';

-- ============================================
-- 15. 绯荤粺璁剧疆琛?
-- ============================================
CREATE TABLE IF NOT EXISTS system_setting (
    name VARCHAR(256) PRIMARY KEY,
    value TEXT NOT NULL,
    description TEXT NOT NULL DEFAULT '',
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP())
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='绯荤粺璁剧疆琛?;

-- ============================================
-- 16. 鍏憡琛?(浠?t_notice 杩佺Щ)
-- ============================================
CREATE TABLE IF NOT EXISTS notice (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    content TEXT NOT NULL COMMENT '鍘?content',
    type INT NOT NULL DEFAULT 0 COMMENT '0=鏅€氬叕鍛? 1=婊氬姩鍏憡',
    start_time BIGINT NOT NULL DEFAULT 0,
    end_time BIGINT NOT NULL DEFAULT 0,
    
    INDEX idx_time (start_time, end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍏憡琛?- 鍏煎鍘?t_notice';

-- ============================================
-- 17. 鏀粯鏁版嵁琛?(浠?t_paydata 杩佺Щ)
-- ============================================
CREATE TABLE IF NOT EXISTS pay_data (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    
    user_id VARCHAR(255) NOT NULL COMMENT '鍘?userid',
    order_id VARCHAR(255) NOT NULL UNIQUE COMMENT '鍘?orderid',
    pkg VARCHAR(255) COMMENT '鍘?pkg',
    money VARCHAR(255) COMMENT '鍘?money',
    game_name VARCHAR(255) COMMENT '鍘?gamename',
    app_channel VARCHAR(255) COMMENT '鍘?app_channel',
    user_channel VARCHAR(255) COMMENT '鍘?userchannel',
    status VARCHAR(255) COMMENT '鍘?status',
    
    INDEX idx_user_id (user_id),
    INDEX idx_order_id (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鏀粯鏁版嵁琛?- 鍏煎鍘?t_paydata';

-- ============================================
-- 18. 鍏呭€艰〃 (浠?t_charge 杩佺Щ)
-- ============================================
CREATE TABLE IF NOT EXISTS charge (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    
    trade_no VARCHAR(255) NOT NULL UNIQUE COMMENT '鍘?tradeNo',
    gold INT NOT NULL DEFAULT 0 COMMENT '鍘?gold',
    rmb INT NOT NULL DEFAULT 0 COMMENT '鍘?rmb',
    time_at BIGINT NOT NULL DEFAULT 0 COMMENT '鍘?time',
    user_name VARCHAR(255) NOT NULL COMMENT '鍘?userName',
    status INT NOT NULL DEFAULT 0 COMMENT '鍘?status',
    
    INDEX idx_user_name (user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍏呭€艰〃 - 鍏煎鍘?t_charge';

-- ============================================
-- 19. 鍒濆鍖栨暟鎹?
-- ============================================

-- 鎻掑叆榛樿绯荤粺璁剧疆
INSERT INTO system_setting (name, value, description) VALUES
('server_version', '1.0.0', '鏈嶅姟鍣ㄧ増鏈?),
('secret_key', '', 'JWT瀵嗛挜'),
('max_online', '1000', '鏈€澶у湪绾夸汉鏁?),
('maintenance_mode', 'false', '缁存姢妯″紡')
ON DUPLICATE KEY UPDATE value = VALUES(value);

-- 鎻掑叆娴嬭瘯鍏憡
INSERT INTO notice (content, type) VALUES
('娆㈣繋鏉ュ埌 DNF Go Server锛?, 0),
('鏈嶅姟鍣ㄨ繍琛屾甯?, 0)
ON DUPLICATE KEY UPDATE updated_at = UNIX_TIMESTAMP();

-- 璁板綍杩佺Щ鐗堟湰
INSERT INTO schema_migrations (version, description) VALUES
('1.0.0', 'Initial schema - 鍏煎 Java DnfGameServer')
ON DUPLICATE KEY UPDATE applied_at = CURRENT_TIMESTAMP;

-- ============================================
-- 20. 鍐掗櫓鑱旂洘琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_union (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL UNIQUE COMMENT '瑙掕壊ID',
    name VARCHAR(64) NOT NULL COMMENT '鍐掗櫓鑱旂洘鍚嶇О',
    exp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍐掗櫓鑱旂洘缁忛獙',
    level INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鍐掗櫓鑱旂洘绛夌骇',
    day INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '澶╂暟',
    typical_character_guid BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '浠ｈ〃瑙掕壊GUID',
    last_change_name_time BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鏈€鍚庢敼鍚嶆椂闂?,
    shareboard_background INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '灞曠ず鏉胯儗鏅?,
    shareboard_frame INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '灞曠ず鏉胯竟妗?,
    shareboard_show_antievil_score TINYINT(1) NOT NULL DEFAULT 0 COMMENT '灞曠ず鏉挎樉绀鸿浼愬垎鏁?,
    auto_search_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鑷姩鎼滅储娆℃暟',
    shareboard_total_antievil_score INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '灞曠ず鏉挎€昏浼愬垎鏁?,
    shareboard_antievil_score_refresh TINYINT(1) NOT NULL DEFAULT 0 COMMENT '灞曠ず鏉胯浼愬垎鏁板埛鏂?,
    is_adventure_condition TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鍐掗櫓鏉′欢',
    
    INDEX idx_role_id (role_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓鑱旂洘琛?;

-- ============================================
-- 21. 鍐掗櫓鑱旂洘杩滃緛琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_union_expedition (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    expedition_id INT UNSIGNED NOT NULL COMMENT '杩滃緛ID',
    expedition_type INT UNSIGNED NOT NULL COMMENT '杩滃緛绫诲瀷',
    status INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鐘舵€?,
    start_time BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '寮€濮嬫椂闂?,
    end_time BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '缁撴潫鏃堕棿',
    reward_claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '濂栧姳鏄惁宸查鍙?,
    
    INDEX idx_role_id (role_id),
    INDEX idx_expedition_id (expedition_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓鑱旂洘杩滃緛琛?;

-- ============================================
-- 22. 鍐掗櫓鑱旂洘璁ㄤ紣琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_union_subdue (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    subdue_id INT UNSIGNED NOT NULL COMMENT '璁ㄤ紣ID',
    subdue_type INT UNSIGNED NOT NULL COMMENT '璁ㄤ紣绫诲瀷',
    character_guid BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '瑙掕壊GUID',
    status INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鐘舵€?,
    start_time BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '寮€濮嬫椂闂?,
    end_time BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '缁撴潫鏃堕棿',
    reward_claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '濂栧姳鏄惁宸查鍙?,
    
    INDEX idx_role_id (role_id),
    INDEX idx_subdue_id (subdue_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓鑱旂洘璁ㄤ紣琛?;

-- ============================================
-- 23. 鍐掗櫓鑱旂洘鏀惰棌琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_union_collection (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    collection_id INT UNSIGNED NOT NULL COMMENT '鏀惰棌ID',
    progress INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '杩涘害',
    completed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁瀹屾垚',
    reward_claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '濂栧姳鏄惁宸查鍙?,
    
    INDEX idx_role_id (role_id),
    INDEX idx_collection_id (collection_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓鑱旂洘鏀惰棌琛?;

-- ============================================
-- 24. 鍐掗櫓鑱旂洘灞曠ず鏉挎Ы浣嶈〃
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_union_shareboard_slot (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    slot_id INT UNSIGNED NOT NULL COMMENT '妲戒綅ID',
    slot_type INT UNSIGNED NOT NULL COMMENT '妲戒綅绫诲瀷',
    item_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鐗╁搧ID',
    item_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鐗╁搧鏁伴噺',
    `show` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁鏄剧ず',
    
    INDEX idx_role_id (role_id),
    INDEX idx_slot_id (slot_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓鑱旂洘灞曠ず鏉挎Ы浣嶈〃';

-- ============================================
-- 25. 鍐掗櫓濂栧姳琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_reap (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    reap_id INT UNSIGNED NOT NULL COMMENT '濂栧姳ID',
    reward_type INT UNSIGNED NOT NULL COMMENT '濂栧姳绫诲瀷',
    reward_index INT UNSIGNED NOT NULL COMMENT '濂栧姳绱㈠紩',
    reward_count INT UNSIGNED NOT NULL COMMENT '濂栧姳鏁伴噺',
    claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁宸查鍙?,
    claim_time BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '棰嗗彇鏃堕棿',
    
    INDEX idx_role_id (role_id),
    INDEX idx_reap_id (reap_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓濂栧姳琛?;

-- ============================================
-- 26. 鍐掗櫓鑱旂洘绛夌骇濂栧姳琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_union_level_reward (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    level INT UNSIGNED NOT NULL COMMENT '绛夌骇',
    reward_type INT UNSIGNED NOT NULL COMMENT '濂栧姳绫诲瀷',
    reward_index INT UNSIGNED NOT NULL COMMENT '濂栧姳绱㈠紩',
    reward_count INT UNSIGNED NOT NULL COMMENT '濂栧姳鏁伴噺',
    claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁宸查鍙?,
    claim_time BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '棰嗗彇鏃堕棿',
    
    INDEX idx_role_id (role_id),
    INDEX idx_level (level),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓鑱旂洘绛夌骇濂栧姳琛?;

-- ============================================
-- 鎴愬氨閰嶇疆琛紙鎴愬氨瀹氫箟锛屼緵鎴愬氨妯″潡鏌ヨ锛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_achievement_config (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    achievement_id INT UNSIGNED NOT NULL COMMENT '鎴愬氨ID',
    name VARCHAR(255) NOT NULL DEFAULT '' COMMENT '鎴愬氨鍚嶇О',
    description TEXT COMMENT '鎴愬氨鎻忚堪',
    target_value INT NOT NULL DEFAULT 0 COMMENT '鐩爣鍊?,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_achievement_id (achievement_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鎴愬氨閰嶇疆琛?;

-- ============================================
-- 鎴愬氨璁板綍琛紙鐜╁鎴愬氨杩涘害涓庨鍙栫姸鎬侊級
-- ============================================
CREATE TABLE IF NOT EXISTS t_achievement_record (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    achievement_id INT UNSIGNED NOT NULL COMMENT '鎴愬氨ID',
    progress INT NOT NULL DEFAULT 0 COMMENT '褰撳墠杩涘害',
    completed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁瀹屾垚',
    rewarded TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁宸查鍩虹濂栧姳',
    reward_time BIGINT NOT NULL DEFAULT 0 COMMENT '棰嗗彇鏃堕棿',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_role_achievement (role_id, achievement_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鎴愬氨璁板綍琛?;

-- ============================================
-- 鎴愬氨濂栧姳棰嗗彇琛紙棰濆濂栧姳棰嗗彇璁板綍锛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_achievement_reward (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    achievement_id INT UNSIGNED NOT NULL COMMENT '鎴愬氨ID',
    reward_type INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '濂栧姳绫诲瀷',
    reward_index INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '濂栧姳绱㈠紩',
    reward_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '濂栧姳鏁伴噺',
    claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁宸查鍙?,
    claim_time BIGINT NOT NULL DEFAULT 0 COMMENT '棰嗗彇鏃堕棿',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    INDEX idx_role_id (role_id),
    INDEX idx_role_achievement (role_id, achievement_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鎴愬氨濂栧姳棰嗗彇琛?;

-- ============================================
-- 鍐掗櫓鏁版嵁琛紙鎴愬氨绯荤粺鑱斿姩鍐掗櫓鑱旂洘缁忛獙锛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_adventure_data (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    adventureunionlevel INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鍐掗櫓鑱旂洘绛夌骇',
    adventureunionexp BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍐掗櫓鑱旂洘缁忛獙',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍐掗櫓鏁版嵁琛?;

-- ============================================
-- 缁勯槦琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_party (
    party_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    leader_id BIGINT UNSIGNED NOT NULL COMMENT '闃熼暱瑙掕壊ID',
    name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '闃熶紞鍚嶇О',
    max_members INT NOT NULL DEFAULT 4 COMMENT '鏈€澶ф垚鍛樻暟',
    status INT NOT NULL DEFAULT 0 COMMENT '0=鎷涘嫙涓? 1=鎴樻枟涓? 2=宸茶В鏁?,
    dungeon_index INT NOT NULL DEFAULT 0 COMMENT '鍓湰绱㈠紩',
    room_id INT NOT NULL DEFAULT 0 COMMENT '鎴块棿ID',
    min_level INT NOT NULL DEFAULT 1 COMMENT '鏈€浣庣瓑绾?,
    max_level INT NOT NULL DEFAULT 156 COMMENT '鏈€楂樼瓑绾?,
    area INT NOT NULL DEFAULT 0 COMMENT '鍖哄煙',
    subtype INT NOT NULL DEFAULT 0 COMMENT '瀛愮被鍨?,
    stage_index INT NOT NULL DEFAULT 0 COMMENT '闃舵绱㈠紩',
    public_type INT NOT NULL DEFAULT 0 COMMENT '鍏紑绫诲瀷',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    update_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    INDEX idx_status (status),
    INDEX idx_leader (leader_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缁勯槦琛?;

-- ============================================
-- 缁勯槦鎴愬憳琛?
-- ============================================
CREATE TABLE IF NOT EXISTS t_party_member (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    party_id BIGINT UNSIGNED NOT NULL COMMENT '闃熶紞ID',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    player_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鐜╁ID',
    team_type INT NOT NULL DEFAULT 0 COMMENT '闃熶紞绫诲瀷',
    status INT NOT NULL DEFAULT 0 COMMENT '0=鍦ㄩ槦, 1=宸查€€鍑?,
    join_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_party_role (party_id, role_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缁勯槦鎴愬憳琛?;

-- ============================================
-- 2.7.0 澧為噺杩佺Щ锛氶槦浼嶅姞鍏ョ敵璇疯褰?-- 2026-09-07 绗簲鍗佷簩杞細鍗婂紑鏀鹃槦浼?public_type=1)鐢宠/闃熼暱鎺ュ彈娴佺▼瀹炲寲
-- ============================================
CREATE TABLE IF NOT EXISTS t_party_request (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    party_id BIGINT UNSIGNED NOT NULL COMMENT '闃熶紞ID',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '鐢宠鑰呰鑹睮D',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_party_role (party_id, role_id),
    KEY idx_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='闃熶紞鍔犲叆鐢宠琛?;

-- ============================================

-- ============================================
-- 2.0.2 澧為噺杩佺Щ锛氳ˉ榻?PK 鏈嶅姟琛?t_pvp_*)
-- 2026-09-06锛歱k_service 鍘熷熀浜庨仐鐣?MySQL 鐩磋繛鏌ヨ t_pvp_* 琛紝
-- 浣嗚縼绉?SQL 浠庢湭寤鸿繃杩欎簺琛?姝ゅ墠鏌ヨ鍏ㄩ儴鎶ラ敊銆丠TTP 灞傚悶閿欒繑鍥炵┖鏁版嵁)銆?
-- 鏈 store 灞傝ˉ榻?PK 瀹炵幇骞剁粺涓€寤鸿〃銆?
-- ============================================

-- PK 璁板綍琛?
CREATE TABLE IF NOT EXISTS t_pvp_record (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    match_type INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍖归厤绫诲瀷',
    win TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁鑳滃埄',
    score INT NOT NULL DEFAULT 0 COMMENT '绉垎鍙樺寲',
    opponent_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '瀵规墜瑙掕壊ID',
    battle_time BIGINT NOT NULL DEFAULT 0 COMMENT '鎴樻枟鏃堕棿',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    INDEX idx_role_id (role_id),
    INDEX idx_battle_time (battle_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 璁板綍琛?;

-- PK 缁熻琛?
CREATE TABLE IF NOT EXISTS t_pvp_stats (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    total_matches INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鎬诲満娆?,
    win_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鑳滃埄鍦烘',
    lose_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '澶辫触鍦烘',
    total_score INT NOT NULL DEFAULT 0 COMMENT '鎬荤Н鍒?,
    max_win_streak INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鏈€澶ц繛鑳?,
    current_streak INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '褰撳墠杩炶儨',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 缁熻琛?;

-- PK 璧涘琛?
CREATE TABLE IF NOT EXISTS t_pvp_season (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    season_id INT UNSIGNED NOT NULL COMMENT '璧涘ID',
    season_name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '璧涘鍚嶇О',
    start_time BIGINT NOT NULL DEFAULT 0 COMMENT '寮€濮嬫椂闂?,
    end_time BIGINT NOT NULL DEFAULT 0 COMMENT '缁撴潫鏃堕棿',
    status INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鐘舵€? 0=鏈紑濮?1=杩涜涓?2=宸茬粨鏉?,
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_season_id (season_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 璧涘琛?;

-- PK 濂栧姳琛?
CREATE TABLE IF NOT EXISTS t_pvp_reward (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    reward_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '濂栧姳ID',
    reward_name VARCHAR(100) NOT NULL DEFAULT '' COMMENT '濂栧姳鍚嶇О',
    count INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鏁伴噺',
    claimed TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁宸查鍙?,
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 濂栧姳琛?;

-- PK 鍖归厤绫诲瀷琛?
CREATE TABLE IF NOT EXISTS t_pvp_match_type (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    match_type INT UNSIGNED NOT NULL COMMENT '鍖归厤绫诲瀷',
    type_name VARCHAR(50) NOT NULL DEFAULT '' COMMENT '绫诲瀷鍚嶇О',
    min_level INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鏈€浣庣瓑绾?,
    max_level INT UNSIGNED NOT NULL DEFAULT 100 COMMENT '鏈€楂樼瓑绾?,
    min_players INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鏈€灏戜汉鏁?,
    max_players INT UNSIGNED NOT NULL DEFAULT 4 COMMENT '鏈€澶氫汉鏁?,
    status INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鐘舵€? 0=绂佺敤 1=鍚敤',
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    UNIQUE KEY uk_match_type (match_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 鍖归厤绫诲瀷琛?;

-- ============================================
-- 2.0.3 澧為噺杩佺Щ锛氳ˉ榻?PK 鍖归厤琛ㄤ笌鍓湰鍏ュ満琛?
-- 2026-09-06锛歮odels/pk.go 瀹氫箟 t_pvp_matching/t_raid_entrance锛?
-- 姝ゅ墠浠庢湭寤鸿〃锛汸K 涓氬姟娣卞寲(鍖归厤/鍙栨秷/鍓湰鍏ュ満/姣忔棩閲嶇疆)渚濊禆杩欎袱寮犺〃銆?
-- ============================================

-- PK 鍖归厤琛?
CREATE TABLE IF NOT EXISTS t_pvp_matching (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    matching_id BIGINT UNSIGNED NOT NULL COMMENT '鍖归厤ID',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    match_type INT UNSIGNED NOT NULL COMMENT '鍖归厤绫诲瀷',
    status INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鐘舵€? 0=鍖归厤涓?1=鍖归厤鎴愬姛 2=宸插彇娑?3=宸茶秴鏃?,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_matching_id (matching_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PK 鍖归厤琛?;

-- 鍓湰鍏ュ満璁板綍琛?
CREATE TABLE IF NOT EXISTS t_raid_entrance (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    raid_index INT UNSIGNED NOT NULL COMMENT '鍓湰绱㈠紩',
    daily_character_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '姣忔棩瑙掕壊鍏ュ満娆℃暟',
    character_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '瑙掕壊鍏ュ満娆℃暟',
    account_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '璐﹀彿鍏ュ満娆℃暟',
    daily_reward_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '姣忔棩濂栧姳娆℃暟',
    reward_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '濂栧姳娆℃暟',
    last_enter_time BIGINT NOT NULL DEFAULT 0 COMMENT '鏈€鍚庡叆鍦烘椂闂?,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    UNIQUE KEY uk_role_raid (role_id, raid_index)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍓湰鍏ュ満璁板綍琛?;

-- ============================================
-- 2.0.4 澧為噺杩佺Щ锛氳ˉ榻愬埗浣滄ā鍧楄褰曡〃
-- 2026-09-06锛歴tore/db/sqlite/make.go 宸茬湡瀹炲疄鐜?ItemCombine/ItemDisjoint
-- (鍐?t_item_combine/t_item_disjoint 璁板綍琛?锛屼絾 MySQL 浠庢湭寤鸿繖涓ゅ紶琛紝
-- mysql make.go 鍏ㄩ儴涓?stub锛涙湰娆¤ˉ榻愯〃缁撴瀯骞跺榻愬疄鐜般€?
-- ============================================

-- 鐗╁搧鍚堟垚璁板綍琛?
CREATE TABLE IF NOT EXISTS t_item_combine (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    target_index INT UNSIGNED NOT NULL COMMENT '鐩爣鏍煎瓙',
    material_list TEXT NOT NULL COMMENT '鏉愭枡鍒楄〃(JSON)',
    count INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鍚堟垚娆℃暟',
    result_guid BIGINT UNSIGNED NOT NULL COMMENT '浜у嚭鐗╁搧GUID',
    cost_money INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '娑堣€楅噾甯?,
    success TINYINT NOT NULL DEFAULT 1 COMMENT '鍚堟垚鏄惁鎴愬姛(0=澶辫触)',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    INDEX idx_item_combine_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鐗╁搧鍚堟垚璁板綍琛?;

-- 鐗╁搧鍒嗚В璁板綍琛?
CREATE TABLE IF NOT EXISTS t_item_disjoint (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    equip_guids TEXT NOT NULL COMMENT '鍒嗚В瑁呭GUID鍒楄〃',
    material_list TEXT NOT NULL COMMENT '浜у嚭鏉愭枡鍒楄〃(JSON)',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    INDEX idx_item_disjoint_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鐗╁搧鍒嗚В璁板綍琛?;

-- ============================================
-- 2.0.8 澧為噺杩佺Щ锛氬悎鎴愰厤鏂归厤缃〃 t_make_recipe
-- 2026-09-06 绗崄浜岃疆锛欼temCombine 娣卞寲涓洪厤鏂归┍鍔?鏉愭枡/浜х墿/璐圭敤)銆?
-- ============================================

-- 鍚堟垚閰嶆柟閰嶇疆琛?
CREATE TABLE IF NOT EXISTS t_make_recipe (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    recipe_index INT UNSIGNED NOT NULL COMMENT '閰嶆柟绱㈠紩(鍚堟垚鎺ュ彛 index 鍏ュ弬)',
    result_index INT UNSIGNED NOT NULL COMMENT '浜х墿鐗╁搧妯℃澘ID',
    result_count INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鍗曟鍚堟垚浜х墿鏁伴噺',
    material_list TEXT NOT NULL COMMENT '鏉愭枡鍒楄〃(JSON:[{"index":2001,"count":1}])',
    cost_money INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍗曟鍚堟垚璐圭敤(閲戝竵)',
    success_rate INT UNSIGNED NOT NULL DEFAULT 100 COMMENT '鎴愬姛鐜囩櫨鍒嗘瘮(0-100)',
    result_pool TEXT NULL COMMENT '闅忔満浜у嚭姹?JSON:[{result_index,result_count,weight}],绌?鍥哄畾浜х墿)',
    fail_result_index INT UNSIGNED NULL COMMENT '澶辫触淇濆簳浜х墿妯℃澘(NULL=澶辫触鏃犱骇鍑?',
    fail_result_count INT UNSIGNED NULL COMMENT '澶辫触淇濆簳浜х墿鏁伴噺',
    enabled TINYINT NOT NULL DEFAULT 1 COMMENT '鏄惁鍚敤(0=鍋滅敤)',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    UNIQUE KEY uk_make_recipe_index (recipe_index)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍚堟垚閰嶆柟閰嶇疆琛?;

-- ============================================
-- 2.0.9 澧為噺杩佺Щ锛氬垎瑙ｄ骇鍑洪厤缃〃 t_make_disjoint
-- 2026-09-06 绗崄涓夎疆锛欼temDisjoint 娣卞寲涓洪厤缃┍鍔?妯℃澘->鍒嗚В鏉愭枡/鏁伴噺)銆?
-- ============================================

-- 鍒嗚В浜у嚭閰嶇疆琛?
CREATE TABLE IF NOT EXISTS t_make_disjoint (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item_index INT UNSIGNED NOT NULL COMMENT '鐗╁搧妯℃澘ID(瀵瑰簲 bag_item.item_id)',
    material_index INT UNSIGNED NOT NULL COMMENT '鍒嗚В鏉愭枡妯℃澘ID',
    material_count INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍗曚欢鍒嗚В浜у嚭鏁伴噺',
    material_list TEXT NULL COMMENT '澶氭潗鏂欎骇鍑?JSON:[{material_index,material_count,bind_type}],绌?鐢ㄦ棫鍒?',
    enabled TINYINT NOT NULL DEFAULT 1 COMMENT '鏄惁鍚敤(0=鍋滅敤)',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    UNIQUE KEY uk_make_disjoint_item (item_index)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍒嗚В浜у嚭閰嶇疆琛?;
-- ============================================
-- 2.0.5 澧為噺杩佺Щ锛氳ˉ榻愬埗浣滄ā鍧楀叾浣欒褰曡〃
-- ============================================
-- 2.0.5 澧為噺杩佺Щ锛氳ˉ榻愬埗浣滄ā鍧楀叾浣欒褰曡〃
-- 2026-09-06锛歴qlite make.go 宸插疄鐜?EmblemUpgrade/AvatarCompose/
-- ProductionRegister/CardCompose(鍐欒褰曡〃)锛屼絾 MySQL 浠庢湭寤鸿繖 4 寮犺〃锛?
-- 鏈琛ヨ〃骞跺榻?mysql make.go 瀹炵幇銆?
-- 娉ㄦ剰锛歁ySQL 璐у竵琛ㄤ负 role_currency(gold 瀛楁)锛宻qlite 涓?t_role_currency(money 瀛楁)锛?
-- 涓ら┍鍔?SQL 澶╃劧涓嶅悓锛屾墸璐归€昏緫鍒嗗埆閫傞厤銆?
-- ============================================

-- 寰界珷鍗囩骇璁板綍琛?
CREATE TABLE IF NOT EXISTS t_emblem_upgrade (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    emblem_index INT NOT NULL COMMENT '寰界珷绱㈠紩',
    level INT NOT NULL COMMENT '褰撳墠绛夌骇',
    try_count INT NOT NULL COMMENT '灏濊瘯娆℃暟',
    success_count INT NOT NULL COMMENT '鎴愬姛娆℃暟',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '娑堣€楅噾甯?,
    cost_talisman INT NOT NULL DEFAULT 0 COMMENT '娑堣€楁姢绗︽暟',
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    INDEX idx_emblem_upgrade_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='寰界珷鍗囩骇璁板綍琛?;

-- 鏃惰鍚堟垚璁板綍琛?
CREATE TABLE IF NOT EXISTS t_avatar_compose (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    avatar_guids TEXT NOT NULL COMMENT '鏃惰GUID鍒楄〃',
    result_index INT NOT NULL COMMENT '浜у嚭鏃惰绱㈠紩',
    result_guid BIGINT UNSIGNED NOT NULL COMMENT '浜у嚭鏃惰GUID',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '娑堣€楅噾甯?,
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    INDEX idx_avatar_compose_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鏃惰鍚堟垚璁板綍琛?;

-- 鐗╁搧鍒朵綔璁板綍琛?
CREATE TABLE IF NOT EXISTS t_item_production (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    slot_index INT NOT NULL COMMENT '鍒朵綔妲戒綅',
    recipe_index INT NOT NULL COMMENT '閰嶆柟绱㈠紩',
    count INT NOT NULL DEFAULT 1 COMMENT '鍒朵綔娆℃暟',
    result_index INT NOT NULL COMMENT '浜у嚭鐗╁搧绱㈠紩',
    result_count INT NOT NULL DEFAULT 1 COMMENT '浜у嚭鏁伴噺',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '娑堣€楅噾甯?,
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    INDEX idx_item_production_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鐗╁搧鍒朵綔璁板綍琛?;

-- 鍗＄墖鍚堟垚璁板綍琛?
CREATE TABLE IF NOT EXISTS t_card_compose (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    card_list TEXT NOT NULL COMMENT '鏉愭枡鍗″垪琛?,
    result_index INT NOT NULL COMMENT '浜у嚭鍗＄储寮?,
    result_count INT NOT NULL DEFAULT 1 COMMENT '浜у嚭鏁伴噺',
    cost_money INT NOT NULL DEFAULT 0 COMMENT '娑堣€楅噾甯?,
    create_time BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()) COMMENT '鍒涘缓鏃堕棿',
    INDEX idx_card_compose_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鍗＄墖鍚堟垚璁板綍琛?;

-- ============================================
-- 2.0.6 澧為噺杩佺Щ锛氭椿鍔ㄦā鍧楀缓琛?
-- 2026-09-06锛歟vent HTTP/TCP handler 姝ゅ墠涓虹┖鏁版嵁婧?
-- (娲诲姩閰嶇疆鏆傚瓨浜庤繍钀ュ悗鍙?,鏈琛ラ綈閰嶇疆琛ㄤ笌瑙掕壊杩涘害琛?
-- 浣挎椿鍔ㄦā鍧楀叿澶囩湡瀹炲瓨鍌ㄣ€?
-- ============================================

-- 娲诲姩閰嶇疆琛?
CREATE TABLE IF NOT EXISTS t_event_config (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    event_id INT NOT NULL COMMENT '娲诲姩涓氬姟ID',
    title VARCHAR(128) NOT NULL DEFAULT '' COMMENT '娲诲姩鏍囬',
    description TEXT COMMENT '娲诲姩鎻忚堪',
    event_type INT NOT NULL DEFAULT 0 COMMENT '娲诲姩绫诲瀷: 0=閫氱敤 1=闄愭椂 2=绛惧埌 3=绱',
    status INT NOT NULL DEFAULT 0 COMMENT '鐘舵€? 0=鍏抽棴 1=杩涜涓?2=宸茬粨鏉?3=鏈紑濮?,
    start_time BIGINT NOT NULL DEFAULT 0 COMMENT '寮€濮嬫椂闂?Unix绉?',
    end_time BIGINT NOT NULL DEFAULT 0 COMMENT '缁撴潫鏃堕棿(Unix绉?',
    reward_config TEXT COMMENT '濂栧姳閰嶇疆(JSON)',
    UNIQUE KEY uk_event_id (event_id),
    INDEX idx_event_status (status),
    INDEX idx_event_type (event_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='娲诲姩閰嶇疆琛?;

-- 娲诲姩杩涘害琛?
CREATE TABLE IF NOT EXISTS t_event_progress (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    event_id INT NOT NULL COMMENT '娲诲姩涓氬姟ID',
    progress_type INT NOT NULL DEFAULT 0 COMMENT '杩涘害绫诲瀷',
    progress_value BIGINT NOT NULL DEFAULT 0 COMMENT '杩涘害鍊?,
    status INT NOT NULL DEFAULT 0 COMMENT '鐘舵€? 0=杩涜涓?1=宸查濂?,
    UNIQUE KEY uk_role_event_progress (role_id, event_id, progress_type),
    INDEX idx_event_id (event_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='娲诲姩杩涘害琛?;

-- ============================================
-- 2.0.7 澧為噺杩佺Щ锛氳儗鍖呮墿瀹瑰閲忓瓨鍌?
-- 2026-09-06锛欱AG_EXPAND 鍛戒护姝ゅ墠涓?mock(鍝嶅簲鍥哄畾 60 鏍?锛?
-- 鏈鏂板瀹归噺琛ㄤ娇鎵╁缁撴灉鍙寔涔呭寲銆?
-- ============================================

-- 鑳屽寘鎵╁琛?
CREATE TABLE IF NOT EXISTS t_bag_expand (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    bag_type INT NOT NULL DEFAULT 1 COMMENT '鑳屽寘绫诲瀷: 1=鏅€?2=浠撳簱',
    capacity INT NOT NULL DEFAULT 0 COMMENT '鎵╁妲戒綅鏁?鍩虹 50 涔嬩笂)',
    UNIQUE KEY uk_role_bag (role_id, bag_type),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鑳屽寘鎵╁琛?;


CREATE TABLE IF NOT EXISTS t_behavior_log (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    role_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '瑙掕壊ID(鏈櫥褰曚細璇濅负0)',
    module VARCHAR(64) NOT NULL DEFAULT '' COMMENT '鏃ュ織妯″潡',
    action VARCHAR(64) NOT NULL DEFAULT '' COMMENT '鍔ㄤ綔绫诲瀷',
    level VARCHAR(16) NOT NULL DEFAULT 'info' COMMENT '鏃ュ織绾у埆: debug/info/warn/error',
    content VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '鏃ュ織鍐呭',
    INDEX idx_role_created (role_id, created_at),
    INDEX idx_level (level)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='琛屼负鏃ュ織琛?;
-- ============================================
-- 2.4.0 濂藉弸鐢宠琛?-- 2026-09-06 绗笁鍗佷節杞? 濂藉弸鐢宠/鍚屾剰娴佺▼(鐢宠鍒跺彇浠ｇ洿鎺ュ姞濂藉弸)
-- 瀹㈡埛绔粡 HTTP 鍙戝ソ鍙嬬敵璇? 鎺ユ敹鏂瑰悓鎰忓悗鍙屽悜寤虹珛濂藉弸鍏崇郴
-- ============================================

CREATE TABLE IF NOT EXISTS friend_request (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',

    from_role_id BIGINT UNSIGNED NOT NULL COMMENT '鐢宠浜鸿鑹睮D',
    from_role_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '鐢宠浜鸿鑹插悕(鍐椾綑)',
    to_role_id BIGINT UNSIGNED NOT NULL COMMENT '鎺ユ敹浜鸿鑹睮D',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '0=寰呭鐞?1=宸插悓鎰?2=宸叉嫆缁?,
    INDEX idx_to_status (to_role_id, status),
    INDEX idx_from_role (from_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='濂藉弸鐢宠琛?;


-- ============================================
-- 2.5.0 瑙掕壊鍚嶅叏灞€鍞竴绾︽潫
-- 2026-09-06 绗洓鍗佽疆: DB 灞傜‖绾︽潫鍏滃簳 handler 杞害鏉?绗簩鍗佷簲杞?
-- 鍓嶇疆娌荤悊(涓€娆℃€ф暟鎹搷浣? 宸叉墽琛?: 閲嶅悕缁勪繚鐣欐渶鏂?MAX(id)), 鍏朵綑鏀瑰悕 name_old_<id>(710 琛?
-- name 鍒楁敹绐勪负 VARCHAR(64)(64*4=256 瀛楄妭 < 767, 鍏煎 MySQL 5.7 COMPACT 琛屾牸寮忕储寮曢檺鍒?
-- ============================================

ALTER TABLE role MODIFY COLUMN name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '瑙掕壊鍚?鍏ㄥ眬鍞竴)';

ALTER TABLE role ADD UNIQUE INDEX uk_name (name);

-- ============================================
-- 21. 鎶€鑳介厤缃〃 + 瑙掕壊鎶€鑳借〃 (2.0.1 琛ヨ〃, 绗洓鍗佸洓杞苟鍏ュ揩鐓?
-- ============================================
CREATE TABLE IF NOT EXISTS skills (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    skill_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鎶€鑳絀D',
    name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '鎶€鑳藉悕绉?,
    description TEXT COMMENT '鎶€鑳芥弿杩?,
    level INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '绛夌骇',
    max_level INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鏈€澶х瓑绾?,
    sp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'SP娑堣€?,
    tp INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'TP娑堣€?,
    type INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '绫诲瀷',
    attack INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '姣忕骇鏀诲嚮鍔涘姞鎴?2.9.0)',
    cooldown INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍐峰嵈绉掓暟(2.9.0)',
    job_required INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鎵€闇€鑱屼笟',
    level_required INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鎵€闇€绛夌骇',
    pre_skill_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍓嶇疆鎶€鑳?,
    pre_skill_level INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鍓嶇疆鎶€鑳界瓑绾?,
    UNIQUE KEY uk_skill_id (skill_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='鎶€鑳介厤缃〃';

CREATE TABLE IF NOT EXISTS role_skills (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT UNSIGNED NOT NULL COMMENT '瑙掕壊ID',
    skill_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '鎶€鑳絀D',
    level INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '鎶€鑳界瓑绾?,
    is_learned TINYINT(1) NOT NULL DEFAULT 0 COMMENT '鏄惁宸插涔?,
    last_cast_at BIGINT NOT NULL DEFAULT 0 COMMENT '鎶€鑳芥渶鍚庢柦鏀炬椂闂存埑(2.9.0)',
    UNIQUE KEY uk_role_skill (role_id, skill_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='瑙掕壊鎶€鑳借〃';

-- 鎶€鑳介厤缃瀛?閫氱敤 + 涓夎亴涓? 涓?2.6.0 澧為噺杩佺Щ涓€鑷?
INSERT IGNORE INTO skills
  (skill_id, name, description, level, max_level, sp, tp, type, job_required, level_required, pre_skill_id, pre_skill_level)
VALUES
  (1001, '鍐插埡', '鍚戝墠蹇€熷啿鍒轰竴娈佃窛绂?, 1, 5, 5, 0, 0, 0, 1, 0, 0),
  (1002, '鏍兼尅', '杩涘叆鏍兼尅濮挎€? 鍑忓皯鎵€鍙椾激瀹?, 1, 5, 5, 0, 2, 0, 1, 0, 0),
  (1101, '涓婃寫', '灏嗘晫浜烘寫椋炲埌绌轰腑', 1, 10, 10, 0, 0, 1, 5, 0, 0),
  (1102, '宕╁北鍑?, '璺冭捣鍚庣寷鍑诲湴闈? 闇囦激鍛ㄥ洿鏁屼汉', 1, 10, 15, 0, 0, 1, 10, 1101, 1),
  (1201, '閾跺厜钀藉垉', '浠庣┖涓惤涓嬫敾鍑绘晫浜?, 1, 10, 10, 0, 0, 2, 5, 0, 0),
  (1202, '涓夋鏂?, '杩炵画鏂╁嚮涓夋, 鍚戝墠绐佽繘', 1, 10, 15, 0, 0, 2, 10, 1201, 1),
  (1301, '蹇垫皵娉?, '鍑濊仛蹇垫皵鍚戝墠鍙戝嚭鍐插嚮娉?, 1, 10, 10, 0, 0, 3, 5, 0, 0),
  (1302, '闆烽渾鑳屾憯', '鎶撲綇鏁屼汉鑳屾憯, 闄勫甫闆峰睘鎬т激瀹?, 1, 10, 15, 0, 0, 3, 10, 1301, 1);

-- 鎶€鑳芥垬鏂楁暟鍊煎洖濉?2.9.0): 姣忕骇鏀诲嚮鍔涘姞鎴?鍐峰嵈绉掓暟
UPDATE skills SET attack=5,  cooldown=5  WHERE skill_id=1001; -- 鍐插埡
UPDATE skills SET attack=0,  cooldown=8  WHERE skill_id=1002; -- 鏍兼尅(闃插尽鎶€)
UPDATE skills SET attack=10, cooldown=10 WHERE skill_id=1101; -- 涓婃寫
UPDATE skills SET attack=15, cooldown=15 WHERE skill_id=1102; -- 宕╁北鍑?UPDATE skills SET attack=12, cooldown=12 WHERE skill_id=1201; -- 閾跺厜钀藉垉
UPDATE skills SET attack=8,  cooldown=6  WHERE skill_id=1202; -- 涓夋鏂?UPDATE skills SET attack=10, cooldown=8  WHERE skill_id=1301; -- 蹇垫皵娉?UPDATE skills SET attack=20, cooldown=20 WHERE skill_id=1302; -- 闆锋祽鑳屾憯

-- 合成配方深化种子(2.11.0): 批量/升级/随机池/费用配方(与 2.11.0__make_recipe_seed.sql 一致)
INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1006, 1002, 1, '[{"index":2001,"count":3}]', 100, 1, 100, NULL, NULL, NULL),
    (1007, 1002, 1, '[{"index":1001,"count":2},{"index":2001,"count":1}]', 50, 1, 100, NULL, NULL, NULL),
    (1008, 1002, 1, '[{"index":1001,"count":1},{"index":2001,"count":2}]', 0, 1, 100, '[{"result_index":1002,"result_count":1,"weight":60},{"result_index":2001,"result_count":2,"weight":40}]', NULL, NULL),
    (1009, 1001, 5, '[{"index":2001,"count":10}]', 500, 1, 100, NULL, NULL, NULL)
ON DUPLICATE KEY UPDATE recipe_index = recipe_index;

-- 物品模板配置表(2.12.0, 与 2.12.0__item_template.sql 一致)
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

-- 模板物品扩展+配方引用(2.13.0, 与 2.13.0__recipe_more_items.sql 一致)
INSERT INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (2004, '秘银矿石', 0, 10, 0, 50, '高级锻造材料'),
    (1004, '屠龙巨剑', 1, 20, 0, 2000, '传说武器, 合成配方产物')
ON DUPLICATE KEY UPDATE item_id = item_id;

INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1010, 1004, 1, '[{"index":2004,"count":3}]', 500, 1, 100, NULL, NULL, NULL),
    (1011, 2004, 1, '[{"index":3001,"count":1},{"index":2003,"count":2}]', 0, 1, 100, NULL, NULL, NULL),
    (1012, 1003, 1, '[{"index":1002,"count":2},{"index":2002,"count":3}]', 300, 1, 100, NULL, NULL, NULL)
ON DUPLICATE KEY UPDATE recipe_index = recipe_index;

-- 分解配置引用更多模板物品(2.14.0, 与 2.14.0__disjoint_more_items.sql 一致)
UPDATE t_make_disjoint SET material_list = '[{"material_index":2013000000,"material_count":1,"bind_type":0},{"material_index":2013000001,"material_count":1,"bind_type":0}]', enabled = 1 WHERE item_index = 3002;

-- 武器/防具完整升级链(2.15.0, 与 2.15.0__armor_chain.sql 一致)
INSERT INTO t_item_template (item_id, name, item_type, level, bind_type, sell_price, description) VALUES
    (1101, '布甲上衣', 2, 1, 0, 80, '基础防具'),
    (1102, '皮甲上衣', 2, 5, 0, 150, '中级防具'),
    (1103, '铁甲上衣', 2, 10, 0, 400, '高级防具')
ON DUPLICATE KEY UPDATE item_id = item_id;

INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate, result_pool, fail_result_index, fail_result_count) VALUES
    (1014, 1004, 1, '[{"index":1003,"count":1},{"index":2004,"count":2}]', 600, 1, 100, NULL, NULL, NULL),
    (1015, 1101, 1, '[{"index":2001,"count":1},{"index":2002,"count":1}]', 0, 1, 100, NULL, NULL, NULL),
    (1016, 1102, 1, '[{"index":1101,"count":2},{"index":2003,"count":1}]', 100, 1, 100, NULL, NULL, NULL),
    (1017, 1103, 1, '[{"index":1102,"count":2},{"index":2004,"count":1}]', 300, 1, 100, NULL, NULL, NULL)
ON DUPLICATE KEY UPDATE recipe_index = recipe_index;
