-- DnfGameServer 数据库表结构脚本
-- 创建日期: 2026-02-06

-- ============================================
-- 数据库: game (主游戏数据库)
-- ============================================

USE `game`;

-- ============================================
-- 1. 账户表 (t_account)
-- ============================================
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` VARCHAR(255) NOT NULL COMMENT 'openid',
  `accountkey` VARCHAR(255) DEFAULT NULL COMMENT 'accountkey',
  `accumulatecera` BIGINT DEFAULT 0 COMMENT '累计充值',
  `userID` VARCHAR(255) DEFAULT NULL COMMENT '用户ID',
  `passwd` VARCHAR(255) DEFAULT NULL COMMENT '密码',
  `roleMaxCount` INT DEFAULT 3 COMMENT '最大角色数量',
  `isStop` TINYINT(1) DEFAULT 0 COMMENT '是否停用',
  `privilege` SMALLINT DEFAULT 0 COMMENT '权限等级',
  `score` INT DEFAULT 0 COMMENT '积分',
  `channelNo` VARCHAR(255) DEFAULT NULL COMMENT '渠道号',
  `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanlingexp` INT DEFAULT 0 COMMENT '战令经验',
  `moneyBox` JSON DEFAULT NULL COMMENT '账户名下的各种货币',
  `epicPieceBox` JSON DEFAULT NULL COMMENT '装备碎片',
  `mailBox` JSON DEFAULT NULL COMMENT '账户邮箱',
  `storageline` INT DEFAULT 1 COMMENT '仓库行数',
  `accShopInfoBox` JSON DEFAULT NULL COMMENT '角色购买记录',
  `adventureReapInfo` JSON DEFAULT NULL COMMENT '冒险收获信息',
  `adventureUnionInfo` JSON DEFAULT NULL COMMENT '冒险联盟信息',
  `adStorageBox` JSON DEFAULT NULL COMMENT '账号金库',
  `advBookBox` JSON DEFAULT NULL COMMENT '冒险图鉴',
  `advUnionSubInfoBox` JSON DEFAULT NULL COMMENT '冒险联盟子信息',
  `activityBox` JSON DEFAULT NULL COMMENT '账号活动',
  `lastLoginTime` BIGINT DEFAULT 0 COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  KEY `idx_userID` (`userID`),
  KEY `idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='账户表';

-- ============================================
-- 2. 角色表 (t_role)
-- ============================================
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` INT NOT NULL COMMENT '角色ID',
  `uid` BIGINT NOT NULL COMMENT '角色唯一ID',
  `lastlogout` BIGINT DEFAULT 0 COMMENT '最后登出时间',
  `growtype` INT DEFAULT 0 COMMENT '成长类型',
  `secgrowtype` INT DEFAULT 0 COMMENT '第二成长类型',
  `job` INT DEFAULT 0 COMMENT '职业',
  `level` INT DEFAULT 1 COMMENT '等级',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '角色名',
  `fatigue` INT DEFAULT 0 COMMENT '疲劳值',
  `equipscore` INT DEFAULT 229 COMMENT '装备评分',
  `characterframe` INT DEFAULT 0 COMMENT '角色边框',
  `money` INT DEFAULT 0 COMMENT '金币',
  `rescoin` INT DEFAULT 0 COMMENT '复活币',
  `contributioncoin` INT DEFAULT 0 COMMENT '贡献币',
  `magiccrystal` INT DEFAULT 0 COMMENT '魔晶',
  `highmagiccrystal` INT DEFAULT 0 COMMENT '高级魔晶',
  `cerascore` INT DEFAULT 0 COMMENT '点券',
  `pkcoin` INT DEFAULT 0 COMMENT 'PK币',
  `friendpoint` INT DEFAULT 0 COMMENT '好友点',
  `smallcoin` INT DEFAULT 0 COMMENT '小币',
  `avatarVisibleFlags` INT DEFAULT 0 COMMENT '头像可见标志',
  `deletionstatus` INT DEFAULT 0 COMMENT '删除状态',
  `deletiontime` BIGINT DEFAULT 0 COMMENT '删除时间',
  `createtime` BIGINT DEFAULT 0 COMMENT '创建时间',
  `changename` TINYINT(1) DEFAULT 0 COMMENT '是否改名',
  `openid` VARCHAR(255) DEFAULT NULL COMMENT 'openid',
  `exp` INT DEFAULT 200 COMMENT '经验值',
  `sp` INT DEFAULT 40 COMMENT 'SP点',
  `tp` INT DEFAULT 0 COMMENT 'TP点',
  `addsp` INT DEFAULT 0 COMMENT '附加SP',
  `addtp` INT DEFAULT 0 COMMENT '附加TP',
  `day` INT DEFAULT 0 COMMENT '天数',
  `score` INT DEFAULT 0 COMMENT '分数',
  `qindex` INT DEFAULT 100110 COMMENT '任务索引',
  `distName` VARCHAR(255) DEFAULT NULL COMMENT '区服名',
  `servername` VARCHAR(255) DEFAULT NULL COMMENT '服务器名',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `lockTime` BIGINT DEFAULT 0 COMMENT '封号时间',
  `pos` JSON DEFAULT NULL COMMENT '坐标数据',
  `storageline` INT DEFAULT 1 COMMENT '仓库行数',
  `wordTime` BIGINT DEFAULT 0 COMMENT '禁言时间',
  `weaponIndex` INT DEFAULT 0 COMMENT '武器index',
  `expratio` INT DEFAULT 100 COMMENT '经验比例',
  `fatigueratio` INT DEFAULT 100 COMMENT '疲劳比例',
  `adventurename` VARCHAR(255) DEFAULT '' COMMENT '冒险名称',
  `serverSimpleDataBox` JSON DEFAULT NULL COMMENT '服务器简单数据',
  `friendBox` JSON DEFAULT NULL COMMENT '好友箱',
  `titleBox` JSON DEFAULT NULL COMMENT '称号箱',
  `avatarBox` JSON DEFAULT NULL COMMENT '装扮背包',
  `emblemBox` JSON DEFAULT NULL COMMENT '徽章背包',
  `cardBox` JSON DEFAULT NULL COMMENT '卡片背包',
  `creatureBox` JSON DEFAULT NULL COMMENT '宠物背包',
  `artifactBox` JSON DEFAULT NULL COMMENT '宠物装备',
  `equipBox` JSON DEFAULT NULL COMMENT '装备背包',
  `equippedBox` JSON DEFAULT NULL COMMENT '已装备',
  `materialBox` JSON DEFAULT NULL COMMENT '材料背包',
  `consumableBox` JSON DEFAULT NULL COMMENT '消耗品背包',
  `roleShopInfoBox` JSON DEFAULT NULL COMMENT '角色购买记录',
  `crackEquipBox` JSON DEFAULT NULL COMMENT '强化装备箱',
  `crackBox` JSON DEFAULT NULL COMMENT '强化箱',
  `damageBox` JSON DEFAULT NULL COMMENT '伤害字体',
  `chatFrameBox` JSON DEFAULT NULL COMMENT '聊天边框',
  `charFrameBox` JSON DEFAULT NULL COMMENT '人物边框',
  `sdAvatarBox` JSON DEFAULT NULL COMMENT 'SD装扮箱',
  `bookmarkBox` JSON DEFAULT NULL COMMENT '书签箱',
  `scrollBox` JSON DEFAULT NULL COMMENT '卷轴箱',
  `moneyBox` JSON DEFAULT NULL COMMENT '金钱箱',
  `ceraShopBuyInfo` JSON DEFAULT NULL COMMENT '点券商店购买信息',
  `tutoBox` JSON DEFAULT NULL COMMENT '教程箱',
  `skillBox` JSON DEFAULT NULL COMMENT '技能箱',
  `skillslotBox` JSON DEFAULT NULL COMMENT '技能槽箱',
  `dungeonTicketsBox` JSON DEFAULT NULL COMMENT '副本门票箱',
  `tonicBox` JSON DEFAULT NULL COMMENT '魔力强化信息',
  `mailBox` JSON DEFAULT NULL COMMENT '邮箱',
  `sysMailBox` JSON DEFAULT NULL COMMENT '系统邮箱',
  `charStorageBox` JSON DEFAULT NULL COMMENT '角色金库',
  `rePurStoItem` JSON DEFAULT NULL COMMENT '回收仓库物品',
  `towerInfoBox` JSON DEFAULT NULL COMMENT '塔信息箱',
  `creatureErrandBox` JSON DEFAULT NULL COMMENT '宠物差事箱',
  `localRewardBox` JSON DEFAULT NULL COMMENT '本地奖励箱',
  `questInfoBox` JSON DEFAULT NULL COMMENT '任务信息箱',
  `sysBuffBox` JSON DEFAULT NULL COMMENT '系统Buff箱',
  `clearDungeonBox` JSON DEFAULT NULL COMMENT '通关副本箱',
  `achievementBox` JSON DEFAULT NULL COMMENT '成就箱',
  `collectionBox` JSON DEFAULT NULL COMMENT '收集箱',
  `noteMsgBox` JSON DEFAULT NULL COMMENT '便签消息箱',
  `essenceBox` JSON DEFAULT NULL COMMENT '精华箱',
  `auctionBox` JSON DEFAULT NULL COMMENT '拍卖箱',
  PRIMARY KEY (`uid`),
  KEY `idx_roleId` (`roleId`),
  KEY `idx_uid` (`uid`),
  KEY `idx_openid` (`openid`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- ============================================
-- 3. 支付数据表 (t_paydata)
-- ============================================
DROP TABLE IF EXISTS `t_paydata`;
CREATE TABLE `t_paydata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(255) DEFAULT NULL COMMENT '用户ID',
  `orderid` VARCHAR(255) DEFAULT NULL COMMENT '订单号',
  `pkg` VARCHAR(255) DEFAULT NULL COMMENT '包名',
  `money` VARCHAR(255) DEFAULT NULL COMMENT '金额',
  `gamename` VARCHAR(255) DEFAULT NULL COMMENT '游戏名称',
  `createtime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `app_channel` VARCHAR(255) DEFAULT NULL COMMENT '游戏渠道码',
  `userchannel` VARCHAR(255) DEFAULT NULL COMMENT '用户渠道',
  `status` VARCHAR(255) DEFAULT NULL COMMENT '支付状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='支付数据表';

-- ============================================
-- 4. 充值表 (t_charge)
-- ============================================
DROP TABLE IF EXISTS `t_charge`;
CREATE TABLE `t_charge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tradeNo` VARCHAR(255) NOT NULL COMMENT '交易号',
  `gold` INT DEFAULT 0 COMMENT '金币',
  `rmb` INT DEFAULT 0 COMMENT '人民币',
  `time` DATETIME DEFAULT NULL COMMENT '时间',
  `userName` VARCHAR(255) DEFAULT NULL COMMENT '用户名',
  `status` INT DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tradeNo` (`tradeNo`),
  KEY `idx_userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='充值表';

-- ============================================
-- 5. 公告表 (t_notice)
-- ============================================
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(1024) DEFAULT NULL COMMENT '公告内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告表';

-- ============================================
-- 数据库: login (登录数据库)
-- ============================================

USE `login`;

-- 登录数据库暂时不需要额外的表，使用 game 数据库的 t_account 表

-- ============================================
-- 数据库: game_kuafu (跨服数据库)
-- ============================================

USE `game_kuafu`;

-- 跨服数据库暂时不需要额外的表，根据实际需求添加

-- ============================================
-- 配置表 (以 p_ 开头的表)
-- ============================================

USE `game`;

-- 角色经验配置表 (p_exp)
DROP TABLE IF EXISTS `p_exp`;
CREATE TABLE `p_exp` (
  `level` INT NOT NULL COMMENT '等级',
  `exp` INT DEFAULT 0 COMMENT '经验值',
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色经验配置表';

-- 游戏地图配置表 (p_gamemap)
DROP TABLE IF EXISTS `p_gamemap`;
CREATE TABLE `p_gamemap` (
  `name` VARCHAR(255) NOT NULL COMMENT '地图名称',
  `id` INT DEFAULT 0 COMMENT '地图ID',
  `safe_zone` TINYINT(1) DEFAULT 0 COMMENT '安全区',
  `telePortX` INT DEFAULT 0 COMMENT '传送点X',
  `telePortY` INT DEFAULT 0 COMMENT '传送点Y',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='游戏地图配置表';

-- NPC配置表 (p_npc)
DROP TABLE IF EXISTS `p_npc`;
CREATE TABLE `p_npc` (
  `id` INT NOT NULL COMMENT 'NPC ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT 'NPC名称',
  `type` TINYINT DEFAULT 1 COMMENT 'NPC类型',
  `x` SMALLINT DEFAULT 0 COMMENT 'X坐标',
  `y` SMALLINT DEFAULT 0 COMMENT 'Y坐标',
  `fangxiang` SMALLINT DEFAULT 0 COMMENT '方向',
  `mapId` INT DEFAULT 0 COMMENT '地图ID',
  `content` TEXT DEFAULT '' COMMENT '对话',
  `content1` TEXT DEFAULT NULL COMMENT '对话2',
  `icon` INT DEFAULT 0 COMMENT '外观',
  `list` JSON DEFAULT '[]' COMMENT '物品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='NPC配置表';

-- 宠物经验配置表 (p_petexp)
DROP TABLE IF EXISTS `p_petexp`;
CREATE TABLE `p_petexp` (
  `level` INT NOT NULL COMMENT '等级',
  `nextLevelExp` INT DEFAULT 0 COMMENT '下一级经验',
  `expDan` INT DEFAULT 0 COMMENT '经验丹',
  `highExpDan` INT DEFAULT 0 COMMENT '高级经验丹',
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='宠物经验配置表';

-- 消耗品配置表 (p_consume)
DROP TABLE IF EXISTS `p_consume`;
CREATE TABLE `p_consume` (
  `index` INT NOT NULL COMMENT '物品索引',
  `itemname` VARCHAR(255) DEFAULT NULL COMMENT '物品名称',
  `stackabletype` INT DEFAULT 0 COMMENT '客户端物品类型',
  `grade` INT DEFAULT 0 COMMENT '物品品质',
  `subtype` INT DEFAULT 0 COMMENT '类别',
  `weight` INT DEFAULT 0 COMMENT '物品重量',
  `score` INT DEFAULT 0 COMMENT '抗魔值',
  `minlevel` INT DEFAULT 0 COMMENT '最低使用等级',
  `rarity` INT DEFAULT 0 COMMENT '稀有度',
  `level` INT DEFAULT 0 COMMENT '物品等级',
  `sellprice` INT DEFAULT 0 COMMENT '出售价格',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消耗品配置表';

-- 装备配置表 (p_equip)
DROP TABLE IF EXISTS `p_equip`;
CREATE TABLE `p_equip` (
  `index` INT NOT NULL COMMENT '装备索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '装备名称',
  `score` INT DEFAULT 0 COMMENT '抗魔值',
  `minlevel` INT DEFAULT 0 COMMENT '最低使用等级',
  `equiptype` INT DEFAULT 0 COMMENT '装备类型',
  `grade` INT DEFAULT 0 COMMENT '品质',
  `weight` INT DEFAULT 0 COMMENT '重量',
  `rarity` INT DEFAULT 0 COMMENT '稀有度',
  `groupname` VARCHAR(255) DEFAULT NULL COMMENT '组名',
  `subtype` INT DEFAULT 0 COMMENT '子类型',
  `disjointList` JSON DEFAULT NULL COMMENT '分解列表',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='装备配置表';

-- 物品商店配置表 (p_itemshop)
DROP TABLE IF EXISTS `p_itemshop`;
CREATE TABLE `p_itemshop` (
  `index` INT NOT NULL COMMENT '索引',
  `goodsid` INT DEFAULT 0 COMMENT '商品ID',
  `moneytype` INT DEFAULT 0 COMMENT '货币类型',
  `moneycount` INT DEFAULT 0 COMMENT '货币数量',
  `limittype` VARCHAR(50) DEFAULT NULL COMMENT '限制类型',
  `limitday` VARCHAR(50) DEFAULT NULL COMMENT '限制天数',
  `limitcount` INT DEFAULT 0 COMMENT '限制数量',
  `shopname` VARCHAR(255) DEFAULT NULL COMMENT '商店名称',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='物品商店配置表';

-- 技能配置表 (p_skill)
DROP TABLE IF EXISTS `p_skill`;
CREATE TABLE `p_skill` (
  `id` INT NOT NULL COMMENT '技能ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  `tp` INT DEFAULT 0 COMMENT 'TP消耗',
  `maxLevel` INT DEFAULT 0 COMMENT '最大等级',
  `type` INT DEFAULT 0 COMMENT '技能类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='技能配置表';

-- 服务器配置表 (p_server)
DROP TABLE IF EXISTS `p_server`;
CREATE TABLE `p_server` (
  `id` INT NOT NULL COMMENT '服务器ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '服务器名称',
  `ip` VARCHAR(255) DEFAULT NULL COMMENT '服务器IP',
  `port` INT DEFAULT 0 COMMENT '服务器端口',
  `maxPlayers` INT DEFAULT 0 COMMENT '最大玩家数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='服务器配置表';

-- 在线商城配置表 (p_onlinemall)
DROP TABLE IF EXISTS `p_onlinemall`;
CREATE TABLE `p_onlinemall` (
  `id` INT NOT NULL COMMENT '商品ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '商品名称',
  `price` INT DEFAULT 0 COMMENT '价格',
  `discount` INT DEFAULT 0 COMMENT '折扣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='在线商城配置表';

-- 转职配置表 (p_transfer)
DROP TABLE IF EXISTS `p_transfer`;
CREATE TABLE `p_transfer` (
  `id` INT NOT NULL COMMENT '转职ID',
  `fromJob` INT DEFAULT 0 COMMENT '原职业',
  `toJob` INT DEFAULT 0 COMMENT '目标职业',
  `level` INT DEFAULT 0 COMMENT '所需等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='转职配置表';

-- 任务配置表 (p_taskset)
DROP TABLE IF EXISTS `p_taskset`;
CREATE TABLE `p_taskset` (
  `id` INT NOT NULL COMMENT '任务ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '任务名称',
  `type` INT DEFAULT 0 COMMENT '任务类型',
  `reward` INT DEFAULT 0 COMMENT '奖励',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务配置表';

-- 对话配置表 (p_talkset)
DROP TABLE IF EXISTS `p_talkset`;
CREATE TABLE `p_talkset` (
  `id` INT NOT NULL COMMENT '对话ID',
  `npcId` INT DEFAULT 0 COMMENT 'NPC ID',
  `content` TEXT DEFAULT NULL COMMENT '对话内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='对话配置表';

-- 皮肤配置表 (p_skin)
DROP TABLE IF EXISTS `p_skin`;
CREATE TABLE `p_skin` (
  `id` INT NOT NULL COMMENT '皮肤ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '皮肤名称',
  `type` INT DEFAULT 0 COMMENT '皮肤类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='皮肤配置表';

-- 技能播放时间配置表 (p_skill_play_time)
DROP TABLE IF EXISTS `p_skill_play_time`;
CREATE TABLE `p_skill_play_time` (
  `id` INT NOT NULL COMMENT '技能ID',
  `playTime` INT DEFAULT 0 COMMENT '播放时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='技能播放时间配置表';

-- 地图BOSS位置配置表 (p_mapbosspos)
DROP TABLE IF EXISTS `p_mapbosspos`;
CREATE TABLE `p_mapbosspos` (
  `id` INT NOT NULL COMMENT 'ID',
  `mapId` INT DEFAULT 0 COMMENT '地图ID',
  `bossId` INT DEFAULT 0 COMMENT 'BOSS ID',
  `x` INT DEFAULT 0 COMMENT 'X坐标',
  `y` INT DEFAULT 0 COMMENT 'Y坐标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地图BOSS位置配置表';

-- 副本配置表 (p_dungeon)
DROP TABLE IF EXISTS `p_dungeon`;
CREATE TABLE `p_dungeon` (
  `id` INT NOT NULL COMMENT '副本ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '副本名称',
  `level` INT DEFAULT 0 COMMENT '副本等级',
  `maxPlayers` INT DEFAULT 0 COMMENT '最大玩家数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='副本配置表';

-- 副本地图配置表 (p_dungeonmap)
DROP TABLE IF EXISTS `p_dungeonmap`;
CREATE TABLE `p_dungeonmap` (
  `id` INT NOT NULL COMMENT '地图ID',
  `dungeonId` INT DEFAULT 0 COMMENT '副本ID',
  `mapName` VARCHAR(255) DEFAULT NULL COMMENT '地图名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='副本地图配置表';

-- 任务信息表 (p_taskinfo)
DROP TABLE IF EXISTS `p_taskinfo`;
CREATE TABLE `p_taskinfo` (
  `id` INT NOT NULL COMMENT '任务ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '任务名称',
  `description` TEXT DEFAULT NULL COMMENT '任务描述',
  `reward` INT DEFAULT 0 COMMENT '奖励',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务信息表';

-- 敏感词表 (p_word)
DROP TABLE IF EXISTS `p_word`;
CREATE TABLE `p_word` (
  `id` INT NOT NULL COMMENT 'ID',
  `word` VARCHAR(255) DEFAULT NULL COMMENT '敏感词',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='敏感词表';

-- 物品掉落配置表 (p_itemdropset)
DROP TABLE IF EXISTS `p_itemdropset`;
CREATE TABLE `p_itemdropset` (
  `id` INT NOT NULL COMMENT '掉落组ID',
  `itemId` INT DEFAULT 0 COMMENT '物品ID',
  `rate` INT DEFAULT 0 COMMENT '掉落率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='物品掉落配置表';

-- 角色评分配置表 (cnf_characterscoreconfig)
DROP TABLE IF EXISTS `cnf_characterscoreconfig`;
CREATE TABLE `cnf_characterscoreconfig` (
  `id` INT NOT NULL COMMENT 'ID',
  `level` INT DEFAULT 0 COMMENT '等级',
  `score` INT DEFAULT 0 COMMENT '评分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色评分配置表';

-- 强化评分配置表 (cnf_upgradescoreconfig)
DROP TABLE IF EXISTS `cnf_upgradescoreconfig`;
CREATE TABLE `cnf_upgradescoreconfig` (
  `id` INT NOT NULL COMMENT 'ID',
  `level` INT DEFAULT 0 COMMENT '强化等级',
  `score` INT DEFAULT 0 COMMENT '评分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='强化评分配置表';

-- 登录服务器表 (t_server)
DROP TABLE IF EXISTS `t_server`;
CREATE TABLE `t_server` (
  `id` INT NOT NULL COMMENT '服务器ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '服务器名称',
  `ip` VARCHAR(255) DEFAULT NULL COMMENT '服务器IP',
  `port` INT DEFAULT 0 COMMENT '服务器端口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录服务器表';

-- 邀请表 (t_invite)
DROP TABLE IF EXISTS `t_invite`;
CREATE TABLE `t_invite` (
  `id` VARCHAR(255) NOT NULL COMMENT '邀请ID',
  `inviterId` VARCHAR(255) DEFAULT NULL COMMENT '邀请者ID',
  `inviteeId` VARCHAR(255) DEFAULT NULL COMMENT '被邀请者ID',
  `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='邀请表';

-- 身份表 (t_identity)
DROP TABLE IF EXISTS `t_identity`;
CREATE TABLE `t_identity` (
  `type` INT NOT NULL COMMENT '类型',
  `currId` INT DEFAULT 0 COMMENT '当前ID',
  PRIMARY KEY (`type`),
  UNIQUE KEY `uk_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='身份表';

-- 离线数据表 (t_offline)
DROP TABLE IF EXISTS `t_offline`;
CREATE TABLE `t_offline` (
  `uid` BIGINT NOT NULL COMMENT '角色UID',
  `data` JSON DEFAULT NULL COMMENT '离线数据',
  `lastLogoutTime` BIGINT DEFAULT 0 COMMENT '最后登出时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='离线数据表';

-- 拍卖表 (t_auction)
DROP TABLE IF EXISTS `t_auction`;
CREATE TABLE `t_auction` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '拍卖ID',
  `itemId` INT DEFAULT 0 COMMENT '物品ID',
  `sellerId` VARCHAR(255) DEFAULT NULL COMMENT '卖家ID',
  `price` INT DEFAULT 0 COMMENT '价格',
  `startTime` DATETIME DEFAULT NULL COMMENT '开始时间',
  `endTime` DATETIME DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='拍卖表';

-- 支付网关表 (pay_gateway)
DROP TABLE IF EXISTS `pay_gateway`;
CREATE TABLE `pay_gateway` (
  `id` INT NOT NULL COMMENT '网关ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '网关名称',
  `type` VARCHAR(50) DEFAULT NULL COMMENT '网关类型',
  `config` JSON DEFAULT NULL COMMENT '配置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='支付网关表';

-- 剑魂技能配置表 (p_skillswordman)
DROP TABLE IF EXISTS `p_skillswordman`;
CREATE TABLE `p_skillswordman` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='剑魂技能配置表';

-- 鬼泣技能配置表 (p_skillmage)
DROP TABLE IF EXISTS `p_skillmage`;
CREATE TABLE `p_skillmage` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='鬼泣技能配置表';

-- 狂战技能配置表 (p_skillfighter)
DROP TABLE IF EXISTS `p_skillfighter`;
CREATE TABLE `p_skillfighter` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='狂战技能配置表';

-- 漫游技能配置表 (p_skillgunner)
DROP TABLE IF EXISTS `p_skillgunner`;
CREATE TABLE `p_skillgunner` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='漫游技能配置表';

-- 散打技能配置表 (p_skillpriest)
DROP TABLE IF EXISTS `p_skillpriest`;
CREATE TABLE `p_skillpriest` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='散打技能配置表';

-- 召唤技能配置表 (p_skillcreature)
DROP TABLE IF EXISTS `p_skillcreature`;
CREATE TABLE `p_skillcreature` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='召唤技能配置表';

-- 剑宗技能配置表 (p_skillatswordman)
DROP TABLE IF EXISTS `p_skillatswordman`;
CREATE TABLE `p_skillatswordman` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='剑宗技能配置表';

-- 暗帝技能配置表 (p_skillatpriest)
DROP TABLE IF EXISTS `p_skillatpriest`;
CREATE TABLE `p_skillatpriest` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='暗帝技能配置表';

-- 狂帝技能配置表 (p_skillatfighter)
DROP TABLE IF EXISTS `p_skillatfighter`;
CREATE TABLE `p_skillatfighter` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='狂帝技能配置表';

-- 魔枪技能配置表 (p_skillatdemoniclancer)
DROP TABLE IF EXISTS `p_skillatdemoniclancer`;
CREATE TABLE `p_skillatdemoniclancer` (
  `index` INT NOT NULL COMMENT '技能索引',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '技能名称',
  `level` INT DEFAULT 0 COMMENT '技能等级',
  `sp` INT DEFAULT 0 COMMENT 'SP消耗',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='魔枪技能配置表';

-- ============================================
-- 初始化数据
-- ============================================

-- 插入测试公告
INSERT INTO `t_notice` (`id`, `content`) VALUES (1, '欢迎来到 DNF 游戏服务器！');

-- 插入角色经验配置数据 (1-100级)
INSERT INTO `p_exp` (`level`, `exp`) VALUES
(1, 0), (2, 100), (3, 300), (4, 600), (5, 1000),
(6, 1500), (7, 2100), (8, 2800), (9, 3600), (10, 4500),
(11, 5500), (12, 6600), (13, 7800), (14, 9100), (15, 10500),
(16, 12000), (17, 13600), (18, 15300), (19, 17100), (20, 19000),
(21, 21000), (22, 23100), (23, 25300), (24, 27600), (25, 30000),
(26, 32500), (27, 35100), (28, 37800), (29, 40600), (30, 43500),
(31, 46500), (32, 49600), (33, 52800), (34, 56100), (35, 59500),
(36, 63000), (37, 66600), (38, 70300), (39, 74100), (40, 78000),
(41, 82000), (42, 86100), (43, 90300), (44, 94600), (45, 99000),
(46, 103500), (47, 108100), (48, 112800), (49, 117600), (50, 122500),
(51, 127500), (52, 132600), (53, 137800), (54, 143100), (55, 148500),
(56, 154000), (57, 159600), (58, 165300), (59, 171100), (60, 177000),
(61, 183000), (62, 189100), (63, 195300), (64, 201600), (65, 208000),
(66, 214500), (67, 221100), (68, 227800), (69, 234600), (70, 241500),
(71, 248500), (72, 255600), (73, 262800), (74, 270100), (75, 277500),
(76, 285000), (77, 292600), (78, 300300), (79, 308100), (80, 316000),
(81, 324000), (82, 332100), (83, 340300), (84, 348600), (85, 357000),
(86, 365500), (87, 374100), (88, 382800), (89, 391600), (90, 400500),
(91, 409500), (92, 418600), (93, 427800), (94, 437100), (95, 446500),
(96, 456000), (97, 465600), (98, 475300), (99, 485100), (100, 495000);

-- 插入宠物经验配置数据 (1-50级)
INSERT INTO `p_petexp` (`level`, `nextLevelExp`, `expDan`, `highExpDan`) VALUES
(1, 100, 10, 5), (2, 200, 20, 10), (3, 300, 30, 15), (4, 400, 40, 20), (5, 500, 50, 25),
(6, 600, 60, 30), (7, 700, 70, 35), (8, 800, 80, 40), (9, 900, 90, 45), (10, 1000, 100, 50),
(11, 1100, 110, 55), (12, 1200, 120, 60), (13, 1300, 130, 65), (14, 1400, 140, 70), (15, 1500, 150, 75),
(16, 1600, 160, 80), (17, 1700, 170, 85), (18, 1800, 180, 90), (19, 1900, 190, 95), (20, 2000, 200, 100),
(21, 2100, 210, 105), (22, 2200, 220, 110), (23, 2300, 230, 115), (24, 2400, 240, 120), (25, 2500, 250, 125),
(26, 2600, 260, 130), (27, 2700, 270, 135), (28, 2800, 280, 140), (29, 2900, 290, 145), (30, 3000, 300, 150),
(31, 3100, 310, 155), (32, 3200, 320, 160), (33, 3300, 330, 165), (34, 3400, 340, 170), (35, 3500, 350, 175),
(36, 3600, 360, 180), (37, 3700, 370, 185), (38, 3800, 380, 190), (39, 3900, 390, 195), (40, 4000, 400, 200),
(41, 4100, 410, 205), (42, 4200, 420, 210), (43, 4300, 430, 215), (44, 4400, 440, 220), (45, 4500, 450, 225),
(46, 4600, 460, 230), (47, 4700, 470, 235), (48, 4800, 480, 240), (49, 4900, 490, 245), (50, 5000, 500, 250);

-- 显示所有表
SHOW TABLES;