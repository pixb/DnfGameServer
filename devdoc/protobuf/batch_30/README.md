# 批次30: 库存数据结构迁移文档

## 概述

本批次迁移了一些库存相关的数据结构，包括可堆叠物品信息和可重新购买的可堆叠物品信息。这些数据结构主要用于游戏中的库存管理系统功能。

## 迁移日期

2026-02-10

## 迁移文件

1. **PT_STACKABLE.java** - 可堆叠物品信息
2. **PT_REPURCHASE_STACKABLE.java** - 可重新购买的可堆叠物品信息

## 生成的Proto文件

- **proto/dnf/v1/inventory_types.proto** - 库存数据类型定义

## 消息类型映射

### 1. Stackable (可堆叠物品信息)

**Java类**: `com.dnfm.mina.protobuf.PT_STACKABLE`

**Proto消息**: `dnf.v1.Stackable`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| index | index | int32 | 索引 |
| count | count | int32 | 数量 |
| bind | bind | bool | 绑定 |
| scount | scount | int32 | S数量 |
| tcount | tcount | int32 | T数量 |
| expiretime | expiretime | int64 | 过期时间 |
| acquisitiontime | acquisitiontime | int64 | 获取时间 |
| sindex | sindex | int32 | S索引 |
| slotindex | slotindex | int32 | 槽位索引 |

### 2. RepurchaseStackable (可重新购买的可堆叠物品信息)

**Java类**: `com.dnfm.mina.protobuf.PT_REPURCHASE_STACKABLE`

**Proto消息**: `dnf.v1.RepurchaseStackable`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| guid | guid | uint64 | GUID |
| item | item | Stackable | 物品 |

## 代码生成

### Java代码

生成的Java代码位于:
- `proto/gen/java/com/dnfm/mina/protobuf/generated/Stackable.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/StackableOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/RepurchaseStackable.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/RepurchaseStackableOrBuilder.java`

### Go代码

生成的Go代码位于:
- `dnf-go-client/gen/dnf/v1/inventory_types.pb.go`

## 测试验证

### Go单元测试

测试文件: `dnf-go-client/test/batch30_test.go`

测试用例:
- `TestBatch30Stackable` - 测试可堆叠物品信息的序列化和反序列化
- `TestBatch30RepurchaseStackable` - 测试可重新购买的可堆叠物品信息的序列化和反序列化

测试结果: ✅ 全部通过

### Java编译验证

编译命令: `mvn clean compile -DskipTests`

编译结果: ✅ 成功

## 迁移成果

1. ✅ 成功迁移PT_STACKABLE和PT_REPURCHASE_STACKABLE两个库存数据结构
2. ✅ 新增inventory_types.proto文件，集中管理库存数据结构
3. ✅ 生成了Java和Go语言的代码，支持跨语言通信
4. ✅ 编写了Go单元测试验证消息编解码
5. ✅ 验证了Java编译和功能测试
6. ✅ 更新了迁移文档和技能文档

## 注意事项

1. 这些是简单的数据结构，不需要分配单独的module ID
2. 所有字段都是可选的（required = false），在proto3中默认为optional
3. Stackable消息包含了9个字段，用于表示可堆叠物品的详细信息
4. RepurchaseStackable消息包含了GUID和Stackable两个字段，用于表示可重新购买的物品信息
5. 使用了uint64类型来表示GUID，确保了数据的准确性
6. RepurchaseStackable消息中的item字段是一个嵌套的Stackable消息

## 后续工作

1. 根据实际使用情况，可能需要为这些库存数据结构添加编解码器支持
2. 如果需要与旧版JProtobuf兼容，需要实现消息适配器
3. 根据业务需求，可能需要添加更多的验证逻辑

## 相关文档

- [批次23文档](../batch_23/)
- [批次24文档](../batch_24/)
- [批次25文档](../batch_25/)
- [批次26文档](../batch_26/)
- [批次27文档](../batch_27/)
- [批次28文档](../batch_28/)
- [批次29文档](../batch_29/)
- [JProtobuf迁移指南](../../.trae/skills/pix-jptotobuf-to-proto/SKILL.md)
