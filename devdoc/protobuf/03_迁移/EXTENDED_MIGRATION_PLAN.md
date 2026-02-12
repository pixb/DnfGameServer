# 批次40-44+ - 扩展迁移计划

## 📋 迁移概述

**迁移目标**: 迁移所有未迁移的JProtobuf消息（RES、PT、OTHER、ENUM）

**迁移范围**:
- RES消息: 733个（响应消息）
- PT消息: 555个（数据传输消息）
- OTHER消息: 170个（其他消息）
- ENUM消息: 51个（枚举）
- **总计**: 1509个

## 📊 消息类型分布

| 消息类型 | 总数 | 已迁移 | 未迁移 | 迁移进度 |
|---------|------|--------|--------|----------|
| REQ | 693 | 693 | 0 | 100% ✅ |
| RES | 733 | 0 | 733 | 0% ❌ |
| PT | 555 | 0 | 555 | 0% ❌ |
| OTHER | 170 | 0 | 170 | 0% ❌ |
| ENUM | 51 | 0 | 51 | 0% ❌ |
| **总计** | **2202** | **693** | **1509** | **31.5%** |

## 🎯 迁移策略

### 按模块分组

根据JProtobuf消息的ModuleID，将未迁移的消息按模块分组：

#### RES消息模块分布

| ModuleID | 模块名称 | 消息数 |
|----------|----------|--------|
| 14000-14999 | BASIC | ~400 |
| 11000-11999 | CHARACTER | ~100 |
| 12000-12999 | DUNGEON | ~50 |
| 13000-13999 | EQUIP | ~50 |
| 15000-15999 | GUILD | ~30 |
| 16000-16999 | OTHER | ~50 |
| 17000-17999 | PAYMENT | ~10 |
| 18000-18999 | SKILL | ~10 |
| 19000-19999 | SOCIAL | ~10 |
| 20000-20999 | TASK | ~20 |
| 21000-21999 | TOWN | ~3 |

#### PT消息模块分布

| ModuleID | 模块名称 | 消息数 |
|----------|----------|--------|
| 14000-14999 | BASIC | ~300 |
| 11000-11999 | CHARACTER | ~100 |
| 12000-12999 | DUNGEON | ~50 |
| 13000-13999 | EQUIP | ~50 |
| 15000-15999 | GUILD | ~30 |
| 16000-16999 | OTHER | ~25 |

### 批次规划

由于消息数量较大（1509个），建议分批迁移：

| 批次 | 消息类型 | 模块 | 消息数 | 预计批次 |
|------|----------|------|--------|----------|
| 40 | RES | BASIC | 40 | batch_40_basic_res |
| 41 | RES | BASIC | 40 | batch_41_basic_res |
| 42 | RES | BASIC | 40 | batch_42_basic_res |
| 43 | RES | BASIC | 40 | batch_43_basic_res |
| 44 | RES | BASIC | 40 | batch_44_basic_res |
| 45 | RES | BASIC | 40 | batch_45_basic_res |
| 46 | RES | BASIC | 40 | batch_46_basic_res |
| 47 | RES | BASIC | 40 | batch_47_basic_res |
| 48 | RES | BASIC | 40 | batch_48_basic_res |
| 49 | RES | BASIC | 40 | batch_49_basic_res |
| 50 | RES | BASIC | 40 | batch_50_basic_res |
| 51 | RES | CHARACTER | 40 | batch_51_character_res |
| 52 | RES | CHARACTER | 40 | batch_52_character_res |
| 53 | RES | CHARACTER | 20 | batch_53_character_res |
| 54 | RES | DUNGEON | 40 | batch_54_dungeon_res |
| 55 | RES | EQUIP | 40 | batch_55_equip_res |
| 56 | RES | EQUIP | 10 | batch_56_equip_res |
| 57 | RES | GUILD | 30 | batch_57_guild_res |
| 58 | RES | OTHER | 40 | batch_58_other_res |
| 59 | RES | OTHER | 10 | batch_59_other_res |
| 60 | RES | PAYMENT | 10 | batch_60_payment_res |
| 61 | RES | SKILL | 10 | batch_61_skill_res |
| 62 | RES | SOCIAL | 10 | batch_62_social_res |
| 63 | RES | TASK | 20 | batch_63_task_res |
| 64 | RES | TOWN | 3 | batch_64_town_res |
| 65 | PT | BASIC | 40 | batch_65_basic_pt |
| 66 | PT | BASIC | 40 | batch_66_basic_pt |
| 67 | PT | BASIC | 40 | batch_67_basic_pt |
| 68 | PT | BASIC | 40 | batch_68_basic_pt |
| 69 | PT | BASIC | 40 | batch_69_basic_pt |
| 70 | PT | BASIC | 40 | batch_70_basic_pt |
| 71 | PT | BASIC | 40 | batch_71_basic_pt |
| 72 | PT | BASIC | 40 | batch_72_basic_pt |
| 73 | PT | BASIC | 40 | batch_73_basic_pt |
| 74 | PT | BASIC | 40 | batch_74_basic_pt |
| 75 | PT | CHARACTER | 40 | batch_75_character_pt |
| 76 | PT | CHARACTER | 40 | batch_76_character_pt |
| 77 | PT | CHARACTER | 20 | batch_77_character_pt |
| 78 | PT | DUNGEON | 40 | batch_78_dungeon_pt |
| 79 | PT | EQUIP | 40 | batch_79_equip_pt |
| 80 | PT | EQUIP | 10 | batch_80_equip_pt |
| 81 | PT | GUILD | 30 | batch_81_guild_pt |
| 82 | PT | OTHER | 25 | batch_82_other_pt |
| 83 | OTHER | BASIC | 40 | batch_83_basic_other |
| 84 | OTHER | BASIC | 40 | batch_84_basic_other |
| 85 | OTHER | BASIC | 40 | batch_85_basic_other |
| 86 | OTHER | BASIC | 50 | batch_86_basic_other |
| 87 | ENUM | BASIC | 51 | batch_87_basic_enum |

## 🔧 迁移步骤

### 1. 分析未迁移消息

查询数据库，获取所有未迁移的消息列表，按模块和消息类型分组。

### 2. 创建批次目录

为每个批次创建目录和迁移计划文档。

### 3. 生成Proto文件

为每个批次生成对应的Proto文件，遵循以下规则：
- 消息名称使用PascalCase
- 字段名称使用lower_snake_case
- 字段编号从1开始
- 添加必要的注释

### 4. 更新数据库

在数据库中创建迁移记录，关联JProtobuf消息和Proto消息。

### 5. 生成测试用例

为每个批次生成Go测试用例。

### 6. 验证迁移结果

运行测试，验证迁移的正确性。

## 📝 命名规范

### RES消息

- JProtobuf: `RES_XXX`
- Proto: `XxxResponse`

### PT消息

- JProtobuf: `PT_XXX`
- Proto: `XxxData`

### OTHER消息

- JProtobuf: `REQ_XXX` / `RES_XXX` / `NOTIFY_XXX`
- Proto: `XxxRequest` / `XxxResponse` / `XxxNotify`

### ENUM消息

- JProtobuf: 枚举类型
- Proto: `enum Xxx`

## ⚠️ 注意事项

1. **RES消息配对**: RES消息通常与REQ消息配对，需要保持一致性
2. **字段映射**: 确保Proto字段与JProtobuf字段正确映射
3. **类型转换**: 注意Java类型到Proto类型的正确转换
4. **默认值**: 处理字段的默认值
5. **可选字段**: 正确标识optional/repeated字段

## 📊 预期结果

- **迁移批次数**: 48个（批次40-87）
- **迁移消息数**: 1509个
- **Proto文件数**: 48个
- **测试用例数**: 1509个
- **总迁移进度**: 100%（2202/2202）

## 🚀 开始迁移

准备开始迁移工作...

---

**创建时间**: 2026-02-12 08:00:00
