-- 从 Java DnfGameServer 迁移数据到 Go DNF Server
-- 执行方式: 在已存在 Java 版本表的数据库中执行
-- 注意: 此脚本假设 Java 表结构和数据已经存在

-- ============================================
-- 开始事务
-- ============================================
START TRANSACTION;

-- ============================================
-- 1. 迁移账户数据 (t_account -> account)
-- ============================================
INSERT INTO account (
    openid, account_key, auth_key, last_login_at, last_login_ip,
    authority, status, accumulate_cera, user_id, passwd,
    role_max_count, score, channel_no, zhanling_exp,
    money_box, epic_piece_box, mail_box, acc_shop_info_box,
    adventure_reap_info, adventure_union_info, ad_storage_box,
    adv_book_box, adv_union_sub_info_box, activity_box,
    created_at, updated_at
)
SELECT 
    id, COALESCE(accountkey, ''), '', COALESCE(lastLoginTime, 0), '',
    COALESCE(privilege, 0), COALESCE(isStop, 0), COALESCE(accumulatecera, 0), 
    userID, passwd, COALESCE(roleMaxCount, 3), COALESCE(score, 0), 
    channelNo, COALESCE(zhanlingexp, 0),
    moneyBox, epicPieceBox, mailBox, accShopInfoBox,
    adventureReapInfo, adventureUnionInfo, adStorageBox,
    advBookBox, advUnionSubInfoBox, activityBox,
    UNIX_TIMESTAMP(createTime), UNIX_TIMESTAMP()
FROM t_account
WHERE id NOT IN (SELECT openid FROM account);

-- ============================================
-- 2. 迁移角色数据 (t_role -> role)
-- ============================================
INSERT INTO role (
    account_id, role_id, name, job, level, exp, fatigue,
    grow_type, sec_grow_type, equip_score, character_frame,
    money, res_coin, contribution_coin, magic_crystal, high_magic_crystal,
    cera_score, pk_coin, friend_point, small_coin,
    avatar_visible_flags, deletion_status, deletion_time,
    change_name, sp, tp, add_sp, add_tp, day, score, q_index,
    dist_name, server_name, lock_time, word_time, weapon_index,
    exp_ratio, fatigue_ratio, adventure_name,
    -- JSON 字段
    pos, server_simple_data_box, friend_box, title_box,
    avatar_box, emblem_box, card_box, creature_box,
    artifact_box, equip_box, equipped_box, material_box,
    consumable_box, role_shop_info_box, crack_equip_box,
    crack_box, damage_box, chat_frame_box, char_frame_box,
    sd_avatar_box, bookmark_box, scroll_box, money_box,
    cera_shop_buy_info, tuto_box, skill_box, skill_slot_box,
    dungeon_tickets_box, tonic_box, mail_box, sys_mail_box,
    char_storage_box, re_pur_sto_item, tower_info_box,
    creature_errand_box, local_reward_box, quest_info_box,
    sys_buff_box, clear_dungeon_box, achievement_box,
    collection_box, note_msg_box, essence_box, auction_box,
    created_at, updated_at
)
SELECT 
    (SELECT id FROM account WHERE openid = t_role.openid LIMIT 1),
    roleId, name, COALESCE(job, 0), COALESCE(level, 1), COALESCE(exp, 200),
    COALESCE(fatigue, 0),
    COALESCE(growtype, 0), COALESCE(secgrowtype, 0),
    COALESCE(equipscore, 229), COALESCE(characterframe, 0),
    COALESCE(money, 0), COALESCE(rescoin, 0), COALESCE(contributioncoin, 0),
    COALESCE(magiccrystal, 0), COALESCE(highmagiccrystal, 0),
    COALESCE(cerascore, 0), COALESCE(pkcoin, 0), COALESCE(friendpoint, 0),
    COALESCE(smallcoin, 0), COALESCE(avatarVisibleFlags, 0),
    COALESCE(deletionstatus, 0), COALESCE(deletiontime, 0),
    COALESCE(changename, 0), COALESCE(sp, 40), COALESCE(tp, 0),
    COALESCE(addsp, 0), COALESCE(addtp, 0), COALESCE(day, 0),
    COALESCE(score, 0), COALESCE(qindex, 100110),
    distName, servername, COALESCE(lockTime, 0), COALESCE(wordTime, 0),
    COALESCE(weaponIndex, 0), COALESCE(expratio, 100), COALESCE(fatigueratio, 100),
    COALESCE(adventurename, ''),
    -- JSON 字段
    pos, serverSimpleDataBox, friendBox, titleBox,
    avatarBox, emblemBox, cardBox, creatureBox,
    artifactBox, equipBox, equippedBox, materialBox,
    consumableBox, roleShopInfoBox, crackEquipBox,
    crackBox, damageBox, chatFrameBox, charFrameBox,
    sdAvatarBox, bookmarkBox, scrollBox, moneyBox,
    ceraShopBuyInfo, tutoBox, skillBox, skillslotBox,
    dungeonTicketsBox, tonicBox, mailBox, sysMailBox,
    charStorageBox, rePurStoItem, towerInfoBox,
    creatureErrandBox, localRewardBox, questInfoBox,
    sysBuffBox, clearDungeonBox, achievementBox,
    collectionBox, noteMsgBox, essenceBox, auctionBox,
    COALESCE(createtime, UNIX_TIMESTAMP()), UNIX_TIMESTAMP()
FROM t_role
WHERE uid NOT IN (SELECT id FROM role)
  AND EXISTS (SELECT 1 FROM account WHERE openid = t_role.openid);

-- ============================================
-- 3. 初始化角色属性 (默认值)
-- ============================================
INSERT INTO role_attributes (role_id)
SELECT id FROM role
WHERE id NOT IN (SELECT role_id FROM role_attributes);

-- ============================================
-- 4. 初始化角色货币 (从 role 的 money 字段)
-- ============================================
INSERT INTO role_currency (role_id, gold)
SELECT id, money FROM role
WHERE id NOT IN (SELECT role_id FROM role_currency);

-- ============================================
-- 5. 迁移公告数据 (t_notice -> notice)
-- ============================================
INSERT INTO notice (content, type, created_at)
SELECT content, 0, UNIX_TIMESTAMP()
FROM t_notice
WHERE id NOT IN (SELECT id FROM notice);

-- ============================================
-- 6. 迁移支付数据 (t_paydata -> pay_data)
-- ============================================
INSERT INTO pay_data (
    user_id, order_id, pkg, money, game_name,
    app_channel, user_channel, status, created_at
)
SELECT 
    userid, orderid, pkg, money, gamename,
    app_channel, userchannel, status,
    UNIX_TIMESTAMP(createtime)
FROM t_paydata
WHERE id NOT IN (SELECT id FROM pay_data);

-- ============================================
-- 7. 迁移充值数据 (t_charge -> charge)
-- ============================================
INSERT INTO charge (
    trade_no, gold, rmb, time_at, user_name, status
)
SELECT 
    tradeNo, gold, rmb, UNIX_TIMESTAMP(time), userName, status
FROM t_charge
WHERE id NOT IN (SELECT id FROM charge);

-- ============================================
-- 8. 迁移离线数据 (t_offline -> offline_data)
-- ============================================
INSERT INTO offline_data (role_id, data, last_logout_time)
SELECT 
    uid, data, lastLogoutTime
FROM t_offline
WHERE uid NOT IN (SELECT role_id FROM offline_data);

-- ============================================
-- 9. 迁移拍卖数据 (t_auction -> auction)
-- ============================================
INSERT INTO auction (
    item_id, seller_id, seller_name, price,
    start_time, end_time, status
)
SELECT 
    itemId, 
    (SELECT id FROM account WHERE openid = sellerId LIMIT 1),
    sellerId,
    price,
    UNIX_TIMESTAMP(startTime), UNIX_TIMESTAMP(endTime), 0
FROM t_auction
WHERE id NOT IN (SELECT id FROM auction);

-- ============================================
-- 10. 从配置表迁移任务定义 (p_taskset -> quest)
-- ============================================
INSERT INTO quest (quest_id, name, description, type, reward_exp, created_at)
SELECT id, name, '', type, reward, UNIX_TIMESTAMP()
FROM p_taskset
WHERE id NOT IN (SELECT quest_id FROM quest);

-- ============================================
-- 11. 从任务信息表迁移任务定义 (p_taskinfo -> quest，补充)
-- ============================================
INSERT INTO quest (quest_id, name, description, created_at)
SELECT id, name, description, UNIX_TIMESTAMP()
FROM p_taskinfo
WHERE id NOT IN (SELECT quest_id FROM quest);

-- ============================================
-- 提交事务
-- ============================================
COMMIT;

-- 记录迁移
INSERT INTO schema_migrations (version, description) VALUES
('1.0.0-migrate', '从 Java DnfGameServer 迁移数据')
ON DUPLICATE KEY UPDATE applied_at = CURRENT_TIMESTAMP;

SELECT '数据迁移完成!' AS result;
