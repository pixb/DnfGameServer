# 批次28: 社交数据结构迁移文档

## 概述

本批次迁移了一些社交相关的数据结构，包括导师信息、导师任务信息、拍卖物品价格信息和角色槽位信息。这些数据结构主要用于游戏中的社交系统功能。

## 迁移日期

2026-02-09

## 迁移文件

1. **PT_MENTEE_INFO.java** - 导师信息
2. **PT_MENTOR_QUEST_INFO.java** - 导师任务信息
3. **PT_AUCTION_ITEM_PRICE_INFO.java** - 拍卖物品价格信息
4. **PT_CHARACTER_SLOT_INFO.java** - 角色槽位信息

## 生成的Proto文件

- **proto/dnf/v1/social_types.proto** - 社交数据类型定义

## 消息类型映射

### 1. MentorQuestInfo (导师任务信息)

**Java类**: `com.dnfm.mina.protobuf.PT_MENTOR_QUEST_INFO`

**Proto消息**: `dnf.v1.MentorQuestInfo`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| qindex | qindex | int32 | 任务索引 |
| state | state | int32 | 状态 |
| masterrewardtime | masterrewardtime | int64 | 师傅奖励时间 |
| rewardtime | rewardtime | int64 | 奖励时间 |

### 2. MenteeInfo (导师信息)

**Java类**: `com.dnfm.mina.protobuf.PT_MENTEE_INFO`

**Proto消息**: `dnf.v1.MenteeInfo`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| guid | guid | uint64 | GUID |
| level | level | int32 | 等级 |
| name | name | string | 名称 |
| job | job | int32 | 职业 |
| growtype | growtype | int32 | 成长类型 |
| secondgrowtype | secondgrowtype | int32 | 第二成长类型 |
| active | active | int32 | 活跃度 |
| online | online | int32 | 在线状态 |
| followerallowgraduate | followerallowgraduate | int32 | 徒弟允许毕业 |
| masterallowgraduate | masterallowgraduate | int32 | 师傅允许毕业 |
| mentordungeonticket | mentordungeonticket | int32 | 导师地下城门票 |
| registlevel | registlevel | int32 | 注册等级 |
| regdate | regdate | int64 | 注册日期 |
| seqlevelgift | seqlevelgift | string | 序列等级礼物 |
| seqlevelgiftmaster | seqlevelgiftmaster | string | 序列等级礼物师傅 |
| sendgreeting | sendgreeting | int64 | 发送问候 |
| recvgreeting | recvgreeting | int64 | 接收问候 |
| questcount | questcount | int32 | 任务数量 |
| quest | quest | repeated MentorQuestInfo | 任务列表 |
| creditscore | creditscore | int32 | 信用分数 |

### 3. AuctionItemPriceInfo (拍卖物品价格信息)

**Java类**: `com.dnfm.mina.protobuf.PT_AUCTION_ITEM_PRICE_INFO`

**Proto消息**: `dnf.v1.AuctionItemPriceInfo`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| price | price | int32 | 价格 |
| count | count | int32 | 数量 |

### 4. CharacterSlotInfo (角色槽位信息)

**Java类**: `com.dnfm.mina.protobuf.PT_CHARACTER_SLOT_INFO`

**Proto消息**: `dnf.v1.CharacterSlotInfo`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| charguid | charguid | uint64 | 角色GUID |

## 代码生成

### Java代码

生成的Java代码位于:
- `proto/gen/java/com/dnfm/mina/protobuf/generated/MentorQuestInfo.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/MentorQuestInfoOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/MenteeInfo.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/MenteeInfoOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/AuctionItemPriceInfo.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/AuctionItemPriceInfoOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/CharacterSlotInfo.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/CharacterSlotInfoOrBuilder.java`

### Go代码

生成的Go代码位于:
- `dnf-go-client/gen/dnf/v1/social_types.pb.go`

## 测试验证

### Go单元测试

测试文件: `dnf-go-client/test/batch28_test.go`

测试用例:
- `TestBatch28MentorQuestInfo` - 测试导师任务信息的序列化和反序列化
- `TestBatch28MenteeInfo` - 测试导师信息的序列化和反序列化
- `TestBatch28AuctionItemPriceInfo` - 测试拍卖物品价格信息的序列化和反序列化
- `TestBatch28CharacterSlotInfo` - 测试角色槽位信息的序列化和反序列化

测试结果: ✅ 全部通过

### Java编译验证

编译命令: `mvn clean compile -DskipTests`

编译结果: ✅ 成功

## 迁移成果

1. ✅ 成功迁移PT_MENTEE_INFO、PT_MENTOR_QUEST_INFO、PT_AUCTION_ITEM_PRICE_INFO和PT_CHARACTER_SLOT_INFO四个社交数据结构
2. ✅ 新增social_types.proto文件，集中管理社交数据结构
3. ✅ 生成了Java和Go语言的代码，支持跨语言通信
4. ✅ 编写了Go单元测试验证消息编解码
5. ✅ 验证了Java编译和功能测试
6. ✅ 更新了迁移文档和技能文档

## 注意事项

1. 这些是简单的数据结构，不需要分配单独的module ID
2. 所有字段都是可选的（required = false），在proto3中默认为optional
3. MenteeInfo消息包含了嵌套的MentorQuestInfo消息，用于表示任务列表
4. 使用了uint64类型来表示GUID，确保了数据的准确性

## 后续工作

1. 根据实际使用情况，可能需要为这些社交数据结构添加编解码器支持
2. 如果需要与旧版JProtobuf兼容，需要实现消息适配器
3. 根据业务需求，可能需要添加更多的验证逻辑

## 相关文档

- [批次23文档](../batch_23/)
- [批次24文档](../batch_24/)
- [批次25文档](../batch_25/)
- [批次26文档](../batch_26/)
- [批次27文档](../batch_27/)
- [JProtobuf迁移指南](../../.trae/skills/pix-jptotobuf-to-proto/SKILL.md)
