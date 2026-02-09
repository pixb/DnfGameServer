# 批次23迁移文档：通用数据结构

## 迁移概述

**批次**：23
**日期**：2026-02-09
**模块**：通用数据结构（PT_GUILD_SYMBOL、PT_SD_AVATAR_ITEM、PT_RANKING）
**状态**：完成
**负责人**：系统自动

## 迁移文件

### 源文件（JProtobuf）
- `src/main/java/com/dnfm/mina/protobuf/PT_GUILD_SYMBOL.java`
- `src/main/java/com/dnfm/mina/protobuf/PT_SD_AVATAR_ITEM.java`
- `src/main/java/com/dnfm/mina/protobuf/PT_RANKING.java`

### 目标文件（标准Protobuf）
- `proto/dnf/v1/common_types.proto`

## 消息定义

### GuildSymbol（公会符号）

| 字段 | 类型 | 说明 |
|---------|------|------|
| index | int32 | 索引 |
| colorindex | int32 | 颜色索引 |

### SdAvatarItem（avatar物品）

| 字段 | 类型 | 说明 |
|---------|------|------|
| index | int32 | 索引 |
| count | int32 | 数量 |
| guid | uint64 | 物品GUID |
| expiretime | uint64 | 过期时间 |
| option | int32 | 选项 |
| locked | bool | 是否锁定 |
| scount | int32 | 特殊数量 |
| tcount | int32 | 总数量 |

### Ranking（排名信息）

| 字段 | 类型 | 说明 |
|---------|------|------|
| guid | uint64 | 角色GUID |
| growtype | int32 | 成长类型 |
| secondgrowtype | int32 | 次要成长类型 |
| job | int32 | 职业 |
| level | int32 | 等级 |
| name | string | 名称 |
| rank | int64 | 排名 |
| score | uint64 | 分数 |
| gsymbol | repeated GuildSymbol | 公会符号 |
| gmastername | string | 公会会长名称 |
| launchinfo | int32 | 启动信息 |
| vip | int32 | VIP等级 |
| profileurl | string | 头像URL |
| profilename | string | 头像名称 |
| wincount | int32 | 获胜次数 |
| gwinpoint | uint64 | 公会获胜点数 |
| winningrate | int32 | 胜率 |
| spinfos | repeated int32 | 旋转信息 |
| creditscore | int32 | 信用分数 |
| characterframe | int32 | 角色边框 |
| platform | int32 | 平台 |
| platformserverid | uint32 | 平台服务器ID |
| gname | string | 公会名称 |

## 编解码器支持

### 注意事项
- 这些是通用数据结构，不是独立的消息模块
- 不需要为它们分配单独的module ID
- 它们会被其他消息类型引用和使用

## 测试验证

### Go单元测试
- `dnf-go-client/test/batch23_test.go`
- 测试用例：
  - `TestBatch23GuildSymbol`
  - `TestBatch23SdAvatarItem`
  - `TestBatch23Ranking`
- 测试结果：全部通过

### Java编译验证
- Maven编译：成功
- 编译时间：2026-02-09 22:33:26
- 编译状态：BUILD SUCCESS

## 代码生成

### Java代码
- 生成目录：`proto/gen/java/com/dnfm/mina/protobuf/generated/`
- 生成文件：
  - `GuildSymbol.java`
  - `GuildSymbolOrBuilder.java`
  - `SdAvatarItem.java`
  - `SdAvatarItemOrBuilder.java`
  - `Ranking.java`
  - `RankingOrBuilder.java`

### Go代码
- 生成目录：`dnf-go-client/gen/dnf/v1/`
- 生成文件：
  - `common_types.pb.go`

## 迁移成果

1. **成功迁移**：将PT_GUILD_SYMBOL、PT_SD_AVATAR_ITEM和PT_RANKING三个通用数据结构从JProtobuf迁移到标准Protobuf
2. **统一管理**：创建了common_types.proto文件，集中管理通用数据结构
3. **代码生成**：生成了Java和Go语言的代码，支持跨语言通信
4. **测试验证**：编写了Go单元测试，验证消息编解码功能正常
5. **编译通过**：Java代码编译成功，无语法错误

## 注意事项

- 这些是通用数据结构，会被其他消息类型引用和使用
- 保留了原始JProtobuf数据结构，确保向后兼容性
- 使用了标准Protobuf 3语法，遵循最佳实践
- 为每个字段分配了唯一的字段编号
- 确保了生成的代码与现有系统的集成

## 后续步骤

- 在其他需要使用这些数据结构的消息类型中引用它们
- 运行完整的功能测试，验证数据结构的使用是否正常
- 继续下一批次的迁移工作
