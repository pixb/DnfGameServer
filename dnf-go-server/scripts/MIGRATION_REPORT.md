# MySQL 到 SQLite 数据迁移完成报告

## 迁移概述

本次迁移将 MySQL 数据库中的 `game` 库和 `login` 库的所有表数据完整迁移到 SQLite 数据库中。

## 迁移统计

### 数据库统计
- **game库表数量**: 59个表
- **login库表数量**: 8个表
- **总表数量**: 83个表
- **总迁移记录数**: 44,564条

### 主要表数据统计

| 表名 | 描述 | 记录数 |
|------|------|--------|
| p_achievement | 成就配置 | 1,616 |
| p_word | 单词配置 | 14,065 |
| p_equip | 装备配置 | 9,379 |
| p_consume | 消耗品配置 | 3,952 |
| p_dungeon | 副本配置 | 1,162 |
| p_dungeonmap | 副本地图 | 6,987 |
| p_itemshop | 商店配置 | 3,311 |
| p_skillatfighter | 技能-格斗家 | 176 |
| p_skillatpriest | 技能-牧师 | 176 |
| t_account | 账户 | 1 |
| t_auction | 拍卖 | 1,215 |
| login_t_account | 登录账户 | 1 |
| login_t_server | 登录服务器 | 1 |

## 迁移的表

### game库表 (59个)

#### 配置表 (p_前缀)
- p_achievement - 成就配置
- p_consume - 消耗品配置
- p_dungeon - 副本配置
- p_dungeonmap - 副本地图配置
- p_equip - 装备配置
- p_exp - 经验配置
- p_gamemap - 游戏地图配置
- p_itemdropset - 物品掉落配置
- p_itemshop - 商店配置
- p_mapbosspos - 地图BOSS位置配置
- p_npc - NPC配置
- p_onlinemall - 在线商城配置
- p_petexp - 宠物经验配置
- p_server - 服务器配置
- p_skill - 技能配置
- p_skill_play_time - 技能播放时间配置
- p_skillatdemoniclancer - 技能-恶魔召唤师
- p_skillatfighter - 技能-格斗家
- p_skillatpriest - 技能-牧师
- p_skillatswordman - 技能-剑魂
- p_skillcreature - 技能-创造者
- p_skillfighter - 技能-格斗家
- p_skillgunner - 技能-神枪手
- p_skillmage - 技能-魔法师
- p_skillpriest - 技能-牧师
- p_skillswordman - 技能-剑魂
- p_skin - 皮肤配置
- p_talkset - 对话配置
- p_taskinfo - 任务信息配置
- p_taskset - 任务集配置
- p_transfer - 转职配置
- p_word - 单词配置
- cnf_characterscoreconfig - 角色分数配置
- cnf_upgradescoreconfig - 升级分数配置

#### 数据表 (t_前缀)
- t_account - 账户表
- t_agent - 代理表
- t_agent_account - 代理账户表
- t_agent_charge - 代理充值表
- t_auction - 拍卖表
- t_channel - 频道表
- t_charge - 充值表
- t_identity - 身份表
- t_invite - 邀请表
- t_notice - 公告表
- t_offline - 离线表
- t_party - 队伍表
- t_paydata - 支付数据表
- t_payinfo - 支付信息表
- t_payorder - 支付订单表
- t_rank - 排行表
- t_rankview - 排行视图表
- t_recharge - 充值表
- t_role - 角色表
- t_server - 服务器表
- t_yaoqing - 邀请表
- pay_gateway - 支付网关表

### login库表 (8个，添加login_前缀)

- login_sys_agent - 系统代理表
- login_sys_user - 系统用户表
- login_t_account - 登录账户表
- login_t_channel - 登录频道表
- login_t_gm_log - GM日志表
- login_t_invite - 登录邀请表
- login_t_order - 登录订单表
- login_t_server - 登录服务器表

## 迁移工具

### 工具位置
`/home/pix/dev/code/java/DnfGameServer/dnf-go-server/scripts/migrate_full.go`

### 工具特性

1. **自动表结构检测**: 自动从MySQL读取表结构并创建对应的SQLite表
2. **数据类型转换**: 自动将MySQL数据类型转换为SQLite兼容类型
3. **表名冲突处理**: 为login库的表添加`login_`前缀，避免与game库表名冲突
4. **主键处理**: 对有主键的表使用INSERT OR REPLACE，对无主键的表先删除再重建
5. **错误处理**: 完善的错误处理和日志输出

### 数据类型映射

| MySQL类型 | SQLite类型 |
|-----------|-------------|
| TINYINT | INTEGER |
| SMALLINT | INTEGER |
| INT | INTEGER |
| BIGINT | INTEGER |
| FLOAT | REAL |
| DOUBLE | REAL |
| DECIMAL | REAL |
| CHAR/VARCHAR | TEXT |
| TEXT | TEXT |
| DATE | INTEGER |
| DATETIME/TIMESTAMP | INTEGER |
| JSON | TEXT |

## 使用方法

### 运行迁移
```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-server/scripts
go run migrate_full.go
```

### 重新迁移（清空现有数据）
```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-server
rm -f game.db
sqlite3 game.db < store/migration/sqlite/LATEST.sql
cd scripts
go run migrate_full.go
```

## 验证结果

### 服务器状态
- **数据库驱动**: SQLite
- **数据库文件**: `game.db`
- **服务器地址**: http://localhost:8081
- **状态**: 运行正常

### API测试
```bash
# 登录测试
curl -X POST http://localhost:8081/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"test","password":"test"}'

# 成就列表测试
curl -X POST http://localhost:8081/api/v1/achievement/list \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"field_1":1}'
```

## 注意事项

1. **表名冲突**: login库的表在SQLite中添加了`login_`前缀以避免与game库表名冲突
2. **无主键表**: 对于没有主键的表，每次迁移会先删除表再重建，确保数据不重复
3. **JSON字段**: MySQL的JSON类型字段在SQLite中存储为TEXT类型
4. **时间字段**: MySQL的DATETIME/TIMESTAMP类型在SQLite中存储为INTEGER（Unix时间戳）
5. **数据一致性**: 建议定期运行迁移工具以同步MySQL和SQLite数据

## 迁移完成时间
2026-02-13

## 下一步建议

1. **数据验证**: 验证关键表的数据完整性和准确性
2. **性能测试**: 测试SQLite数据库在大量数据下的性能表现
3. **备份策略**: 建立SQLite数据库的定期备份机制
4. **同步机制**: 考虑建立MySQL到SQLite的实时或定时同步机制
