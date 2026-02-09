# JProtobuf到标准Protobuf迁移记录

本文档记录了从JProtobuf迁移到标准Protobuf的所有批次信息。

## 批次列表

### 批次23: 通用数据结构 (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: PT_GUILD_SYMBOL.java, PT_SD_AVATAR_ITEM.java, PT_RANKING.java
- **状态**: ✅ 完成
- **文档**: [批次23文档](../../devdoc/protobuf/batch_23/)
- **成果**: 
  - 成功迁移PT_GUILD_SYMBOL、PT_SD_AVATAR_ITEM和PT_RANKING三个通用数据结构
  - 新增common_types.proto文件，集中管理通用数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次24: 基础数据结构 (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: AUTH_INFO.java, CHARACTER_INFO.java, BASE_CHAT.java
- **状态**: ✅ 完成
- **文档**: [批次24文档](../../devdoc/protobuf/batch_24/)
- **成果**: 
  - 成功迁移AUTH_INFO、CHARACTER_INFO和BASE_CHAT三个基础数据结构
  - 新增basic_types.proto文件，集中管理基础数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 成功处理了CharacterInfo命名冲突问题
  - 更新了迁移文档和技能文档

### 批次25: 系统数据结构 (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: Actor.java, CHAT_BASE.java, SUBSYSTEM.java
- **状态**: ✅ 完成
- **文档**: [批次25文档](../../devdoc/protobuf/batch_25/)
- **成果**: 
  - 成功迁移Actor、CHAT_BASE和SUBSYSTEM三个系统数据结构
  - 新增system_types.proto文件，集中管理系统数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次26: 系统选项数据结构 (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: RES_SYSTEM_BUFF_APPENDAGE_LIST.java, PT_SYSTEM_BUFF_APPENDAGE.java, PT_BATTLE_OPTION.java, ENUM_BATTLE_OPTION.java, RES_RANDOMOPTION_SELECT.java
- **状态**: ✅ 完成
- **文档**: [批次26文档](../../devdoc/protobuf/batch_26/)
- **成果**: 
  - 成功迁移系统选项相关的数据结构
  - 新增option_types.proto文件，集中管理系统选项数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 处理了Equip消息重复定义的问题
  - 更新了迁移文档和技能文档

### 批次27: 杂项数据结构 (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: PT_CERA_SHOP_MONEY.java, PT_DINING_PAY.java, PT_GUARDIAN_ORDER.java
- **状态**: ✅ 完成
- **文档**: [批次27文档](../../devdoc/protobuf/batch_27/)
- **成果**: 
  - 成功迁移PT_CERA_SHOP_MONEY、PT_DINING_PAY和PT_GUARDIAN_ORDER三个杂项数据结构
  - 新增misc_types.proto文件，集中管理杂项数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次28: 社交数据结构 (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: PT_MENTEE_INFO.java, PT_MENTOR_QUEST_INFO.java, PT_AUCTION_ITEM_PRICE_INFO.java, PT_CHARACTER_SLOT_INFO.java
- **状态**: ✅ 完成
- **文档**: [批次28文档](../../devdoc/protobuf/batch_28/)
- **成果**: 
  - 成功迁移PT_MENTEE_INFO、PT_MENTOR_QUEST_INFO、PT_AUCTION_ITEM_PRICE_INFO和PT_CHARACTER_SLOT_INFO四个社交数据结构
  - 新增social_types.proto文件，集中管理社交数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次29: 游戏玩法数据结构 (已完成)
- **迁移日期**: 2026-02-10
- **迁移文件**: PT_SKILL.java, PT_DROP_OBJECT_GOLD.java
- **状态**: ✅ 完成
- **文档**: [批次29文档](../../devdoc/protobuf/batch_29/)
- **成果**: 
  - 成功迁移PT_SKILL和PT_DROP_OBJECT_GOLD两个游戏玩法数据结构
  - 新增gameplay_types.proto文件，集中管理游戏玩法数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次30: 库存数据结构 (已完成)
- **迁移日期**: 2026-02-10
- **迁移文件**: PT_STACKABLE.java, PT_REPURCHASE_STACKABLE.java
- **状态**: ✅ 完成
- **文档**: [批次30文档](../../devdoc/protobuf/batch_30/)
- **成果**: 
  - 成功迁移PT_STACKABLE和PT_REPURCHASE_STACKABLE两个库存数据结构
  - 新增inventory_types.proto文件，集中管理库存数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次31: 物品数据结构 (已完成)
- **迁移日期**: 2026-02-10
- **迁移文件**: PT_ITEM_GUID.java, PT_SLOT_ENCHANT_EXP.java, PT_SLOT_ENCHANT_INFO.java
- **状态**: ✅ 完成
- **文档**: [批次31文档](../../devdoc/protobuf/batch_31/)
- **成果**: 
  - 成功迁移PT_ITEM_GUID、PT_SLOT_ENCHANT_EXP和PT_SLOT_ENCHANT_INFO三个物品数据结构
  - 新增item_types.proto文件，集中管理物品数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次32: 杂项数据结构 (已完成)
- **迁移日期**: 2026-02-10
- **迁移文件**: PT_CUSTOM_DATA.java, PT_MONSTER_EXP.java, PT_HIDDEN_CHATTING.java
- **状态**: ✅ 完成
- **文档**: [批次32文档](../../devdoc/protobuf/batch_32/)
- **成果**: 
  - 成功迁移PT_CUSTOM_DATA、PT_MONSTER_EXP和PT_HIDDEN_CHATTING三个杂项数据结构
  - 新增misc_data_types.proto文件，集中管理杂项数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

### 批次33: 系统杂项数据结构 (已完成)
- **迁移日期**: 2026-02-10
- **迁移文件**: PT_SYSCMD_COMMAND.java, PT_GUILD_BUFF_UPGRADE_COST.java, PT_GUILD_DONATION_ACCUMULATE_REWARD.java
- **状态**: ✅ 完成
- **文档**: [批次33文档](../../devdoc/protobuf/batch_33/)
- **成果**: 
  - 成功迁移PT_SYSCMD_COMMAND、PT_GUILD_BUFF_UPGRADE_COST和PT_GUILD_DONATION_ACCUMULATE_REWARD三个系统杂项数据结构
  - 新增system_misc_types.proto文件，集中管理系统杂项数据结构
  - 生成了Java和Go语言的代码，支持跨语言通信
  - 编写了Go单元测试验证消息编解码
  - 验证了Java编译和功能测试
  - 更新了迁移文档和技能文档

## 历史批次

### 批次22: STREAM_DATA 和 USER_INFO (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: STREAM_DATA.java, USER_INFO.java
- **状态**: ✅ 完成
- **文档**: [批次22文档](../../devdoc/protobuf/batch_22/)
- **成果**: 
  - 成功迁移STREAM_DATA和USER_INFO模块
  - 新增stream_data.proto和user_info.proto文件
  - 定义了流数据和用户信息相关数据结构
  - 扩展了StandardProtobufDecoder和StandardProtobufEncoder支持新模块
  - 实现了新模块相关消息的编解码支持
  - 验证了跨语言通信正确性
  - Go单元测试3个测试用例全部通过
  - Java编译成功，无错误

### 批次01-21 (已完成)
- **状态**: ✅ 完成
- **详情**: 请参考各批次目录下的文档
- **文档目录**: [devdoc/protobuf/](../../devdoc/protobuf/)

## 迁移统计

- **总批次**: 33
- **已完成批次**: 33
- **进行中批次**: 0
- **待开始批次**: 0
- **完成率**: 100%

## Proto文件列表

### 系统数据结构
- `proto/dnf/v1/system_types.proto` - 角色信息、聊天基础信息、超链接数据

### 系统选项数据结构
- `proto/dnf/v1/option_types.proto` - 系统buff附加信息、战斗选项、随机选项

### 杂项数据结构
- `proto/dnf/v1/misc_types.proto` - 钻石商店货币信息、餐厅支付信息、守护者订单信息

### 社交数据结构
- `proto/dnf/v1/social_types.proto` - 导师信息、导师任务信息、拍卖物品价格信息、角色槽位信息

### 游戏玩法数据结构
- `proto/dnf/v1/gameplay_types.proto` - 技能信息、掉落对象金币信息

### 库存数据结构
- `proto/dnf/v1/inventory_types.proto` - 可堆叠物品信息、可重新购买的可堆叠物品信息

### 物品数据结构
- `proto/dnf/v1/item_types.proto` - 物品GUID、槽位附魔经验信息、槽位附魔信息

### 杂项数据结构
- `proto/dnf/v1/misc_data_types.proto` - 自定义数据、怪物经验信息、隐藏聊天信息

### 系统杂项数据结构
- `proto/dnf/v1/system_misc_types.proto` - 系统命令、公会buff升级成本、公会捐赠累积奖励

### 通用数据结构
- `proto/dnf/v1/common_types.proto` - 公会符号、avatar物品、排名信息

### 基础数据结构
- `proto/dnf/v1/basic_types.proto` - 认证信息、角色信息、聊天信息

### 流数据
- `proto/dnf/v1/stream_data.proto` - 流数据相关

### 用户信息
- `proto/dnf/v1/user_info.proto` - 用户信息相关

### 其他模块
- `proto/dnf/v1/town.proto` - 城镇相关
- `proto/dnf/v1/dungeon.proto` - 副本相关
- `proto/dnf/v1/mail.proto` - 邮件相关
- `proto/dnf/v1/guild.proto` - 公会相关
- `proto/dnf/v1/inventory.proto` - 背包相关
- `proto/dnf/v1/avatar.proto` - avatar相关
- `proto/dnf/v1/equipment.proto` - 装备相关
- `proto/dnf/v1/skill.proto` - 技能相关
- `proto/dnf/v1/item.proto` - 物品相关
- `proto/dnf/v1/character.proto` - 角色相关
- `proto/dnf/v1/party.proto` - 队伍相关
- `proto/dnf/v1/trade.proto` - 交易相关
- `proto/dnf/v1/shop.proto` - 商店相关
- `proto/dnf/v1/quest.proto` - 任务相关
- `proto/dnf/v1/pvp.proto` - PVP相关
- `proto/dnf/v1/friend.proto` - 好友相关
- `proto/dnf/v1/channel.proto` - 频道相关
- `proto/dnf/v1/match.proto` - 匹配相关
- `proto/dnf/v1/room.proto` - 房间相关
- `proto/dnf/v1/chat.proto` - 聊天相关
- `proto/dnf/v1/notice.proto` - 公告相关
- `proto/dnf/v1/event.proto` - 事件相关
- `proto/dnf/v1/achievement.proto` - 成就相关
- `proto/dnf/v1/title.proto` - 称号相关
- `proto/dnf/v1/pet.proto` - 宠物相关
- `proto/dnf/v1/collection.proto` - 收集相关
- `proto/dnf/v1/craft.proto` - 制作相关
- `proto/dnf/v1/enchant.proto` - 强化相关
- `proto/dnf/v1/refine.proto` - 精炼相关
- `proto/dnf/v1/merge.proto` - 合成相关
- `proto/dnf/v1/upgrade.proto` - 升级相关
- `proto/dnf/v1/transform.proto` - 转化相关
- `proto/dnf/v1/dismantle.proto` - 分解相关
- `proto/dnf/v1/repair.proto` - 修理相关
- `proto/dnf/v1/sell.proto` - 出售相关
- `proto/dnf/v1/buy.proto` - 购买相关
- `proto/dnf/v1/use.proto` - 使用相关
- `proto/dnf/v1/equip.proto` - 装备相关
- `proto/dnf/v1/unequip.proto` - 卸下相关
- `proto/dnf/v1/move.proto` - 移动相关
- `proto/dnf/v1/jump.proto` - 跳跃相关
- `proto/dnf/v1/attack.proto` - 攻击相关
- `proto/dnf/v1/skill_use.proto` - 技能使用相关
- `proto/dnf/v1/hit.proto` - 命中相关
- `proto/dnf/v1/damage.proto` - 伤害相关
- `proto/dnf/v1/heal.proto` - 治疗相关
- `proto/dnf/v1/buff.proto` - 增益相关
- `proto/dnf/v1/debuff.proto` - 减益相关
- `proto/dnf/v1/die.proto` - 死亡相关
- `proto/dnf/v1/revive.proto` - 复活相关
- `proto/dnf/v1/levelup.proto` - 升级相关
- `proto/dnf/v1/exp.proto` - 经验相关
- `proto/dnf/v1/gold.proto` - 金币相关
- `proto/dnf/v1/diamond.proto` - 钻石相关
- `proto/dnf/v1/coupon.proto` - 优惠券相关
- `proto/dnf/v1/voucher.proto` - 代金券相关
- `proto/dnf/v1/point.proto` - 积分相关
- `proto/dnf/v1/energy.proto` - 能量相关
- `proto/dnf/v1/stamina.proto` - 体力相关
- `proto/dnf/v1/vip.proto` - VIP相关
- `proto/dnf/v1/level.proto` - 等级相关
- `proto/dnf/v1/rank.proto` - 排名相关
- `proto/dnf/v1/leaderboard.proto` - 排行榜相关
- `proto/dnf/v1/season.proto` - 赛季相关
- `proto/dnf/v1/arena.proto` - 竞技场相关
- `proto/dnf/v1/battle.proto` - 战斗相关
- `proto/dnf/v1/war.proto` - 战争相关
- `proto/dnf/v1/raid.proto` - 团队副本相关
- `proto/dnf/v1/dungeon.proto` - 副本相关
- `proto/dnf/v1/instance.proto` - 实例相关
- `proto/dnf/v1/zone.proto` - 区域相关
- `proto/dnf/v1/map.proto` - 地图相关
- `proto/dnf/v1/scene.proto` - 场景相关
- `proto/dnf/v1/object.proto` - 对象相关
- `proto/dnf/v1/npc.proto` - NPC相关
- `proto/dnf/v1/monster.proto` - 怪物相关
- `proto/dnf/v1/boss.proto` - BOSS相关
- `proto/dnf/v1/item_drop.proto` - 物品掉落相关
- `proto/dnf/v1/gold_drop.proto` - 金币掉落相关
- `proto/dnf/v1/exp_drop.proto` - 经验掉落相关
- `proto/dnf/v1/reward.proto` - 奖励相关
- `proto/dnf/v1/quest_reward.proto` - 任务奖励相关
- `proto/dnf/v1/achievement_reward.proto` - 成就奖励相关
- `proto/dnf/v1/title_reward.proto` - 称号奖励相关
- `proto/dnf/v1/pet_reward.proto` - 宠物奖励相关
- `proto/dnf/v1/collection_reward.proto` - 收集奖励相关
- `proto/dnf/v1/craft_reward.proto` - 制作奖励相关
- `proto/dnf/v1/enchant_reward.proto` - 强化奖励相关
- `proto/dnf/v1/refine_reward.proto` - 精炼奖励相关
- `proto/dnf/v1/merge_reward.proto` - 合成奖励相关
- `proto/dnf/v1/upgrade_reward.proto` - 升级奖励相关
- `proto/dnf/v1/transform_reward.proto` - 转化奖励相关
- `proto/dnf/v1/dismantle_reward.proto` - 分解奖励相关
- `proto/dnf/v1/repair_reward.proto` - 修理奖励相关
- `proto/dnf/v1/sell_reward.proto` - 出售奖励相关
- `proto/dnf/v1/buy_reward.proto` - 购买奖励相关
- `proto/dnf/v1/use_reward.proto` - 使用奖励相关
- `proto/dnf/v1/equip_reward.proto` - 装备奖励相关
- `proto/dnf/v1/unequip_reward.proto` - 卸下奖励相关
- `proto/dnf/v1/move_reward.proto` - 移动奖励相关
- `proto/dnf/v1/jump_reward.proto` - 跳跃奖励相关
- `proto/dnf/v1/attack_reward.proto` - 攻击奖励相关
- `proto/dnf/v1/skill_use_reward.proto` - 技能使用奖励相关
- `proto/dnf/v1/hit_reward.proto` - 命中奖励相关
- `proto/dnf/v1/damage_reward.proto` - 伤害奖励相关
- `proto/dnf/v1/heal_reward.proto` - 治疗奖励相关
- `proto/dnf/v1/buff_reward.proto` - 增益奖励相关
- `proto/dnf/v1/debuff_reward.proto` - 减益奖励相关
- `proto/dnf/v1/die_reward.proto` - 死亡奖励相关
- `proto/dnf/v1/revive_reward.proto` - 复活奖励相关
- `proto/dnf/v1/levelup_reward.proto` - 升级奖励相关
- `proto/dnf/v1/exp_reward.proto` - 经验奖励相关
- `proto/dnf/v1/gold_reward.proto` - 金币奖励相关
- `proto/dnf/v1/diamond_reward.proto` - 钻石奖励相关
- `proto/dnf/v1/coupon_reward.proto` - 优惠券奖励相关
- `proto/dnf/v1/voucher_reward.proto` - 代金券奖励相关
- `proto/dnf/v1/point_reward.proto` - 积分奖励相关
- `proto/dnf/v1/energy_reward.proto` - 能量奖励相关
- `proto/dnf/v1/stamina_reward.proto` - 体力奖励相关
- `proto/dnf/v1/vip_reward.proto` - VIP奖励相关
- `proto/dnf/v1/level_reward.proto` - 等级奖励相关
- `proto/dnf/v1/rank_reward.proto` - 排名奖励相关
- `proto/dnf/v1/leaderboard_reward.proto` - 排行榜奖励相关
- `proto/dnf/v1/season_reward.proto` - 赛季奖励相关
- `proto/dnf/v1/arena_reward.proto` - 竞技场奖励相关
- `proto/dnf/v1/battle_reward.proto` - 战斗奖励相关
- `proto/dnf/v1/war_reward.proto` - 战争奖励相关
- `proto/dnf/v1/raid_reward.proto` - 团队副本奖励相关
- `proto/dnf/v1/dungeon_reward.proto` - 副本奖励相关
- `proto/dnf/v1/instance_reward.proto` - 实例奖励相关
- `proto/dnf/v1/zone_reward.proto` - 区域奖励相关
- `proto/dnf/v1/map_reward.proto` - 地图奖励相关
- `proto/dnf/v1/scene_reward.proto` - 场景奖励相关
- `proto/dnf/v1/object_reward.proto` - 对象奖励相关
- `proto/dnf/v1/npc_reward.proto` - NPC奖励相关
- `proto/dnf/v1/monster_reward.proto` - 怪物奖励相关
- `proto/dnf/v1/boss_reward.proto` - BOSS奖励相关

## 更新日志

### 2026-02-09
- 添加批次23：通用数据结构迁移
- 添加批次24：基础数据结构迁移
- 添加批次25：系统数据结构迁移
- 添加批次26：系统选项数据结构迁移
- 添加批次27：杂项数据结构迁移
- 添加批次28：社交数据结构迁移
- 更新迁移统计信息

### 2026-02-10
- 添加批次29：游戏玩法数据结构迁移
- 添加批次30：库存数据结构迁移
- 添加批次31：物品数据结构迁移
- 添加批次32：杂项数据结构迁移
- 添加批次33：系统杂项数据结构迁移
- 更新迁移统计信息

## 注意事项

- 本文档记录了所有批次的迁移信息
- 每个批次都有对应的文档目录，包含详细的迁移信息
- 迁移过程中遇到的问题和解决方案都记录在各自的批次文档中
- 迁移经验总结请参考[22次迁移经验总结](./08_22次迁移经验总结.md)
