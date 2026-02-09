# 批次27: 杂项数据结构迁移文档

## 概述

本批次迁移了一些杂项的数据结构，包括钻石商店货币信息、餐厅支付信息和守护者订单信息。这些数据结构主要用于各种游戏系统的辅助功能。

## 迁移日期

2026-02-09

## 迁移文件

1. **PT_CERA_SHOP_MONEY.java** - 钻石商店货币信息
2. **PT_DINING_PAY.java** - 餐厅支付信息
3. **PT_GUARDIAN_ORDER.java** - 守护者订单信息

## 生成的Proto文件

- **proto/dnf/v1/misc_types.proto** - 杂项数据类型定义

## 消息类型映射

### 1. CeraShopMoney (钻石商店货币信息)

**Java类**: `com.dnfm.mina.protobuf.PT_CERA_SHOP_MONEY`

**Proto消息**: `dnf.v1.CeraShopMoney`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| index | index | int32 | 索引 |
| count | count | int32 | 数量 |
| value | value | int32 | 值 |

### 2. DiningPay (餐厅支付信息)

**Java类**: `com.dnfm.mina.protobuf.PT_DINING_PAY`

**Proto消息**: `dnf.v1.DiningPay`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| index | index | int32 | 索引 |
| type | type | int32 | 类型 |
| count | count | int32 | 数量 |

### 3. GuardianOrder (守护者订单信息)

**Java类**: `com.dnfm.mina.protobuf.PT_GUARDIAN_ORDER`

**Proto消息**: `dnf.v1.GuardianOrder`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| guardiandeal | guardiandeal | int64 | 守护者交易 |
| growtype | growtype | int32 | 成长类型 |
| secondgrowtype | secondgrowtype | int32 | 第二成长类型 |
| job | job | int32 | 职业 |
| name | name | string | 名称 |
| level | level | int32 | 等级 |

## 代码生成

### Java代码

生成的Java代码位于:
- `proto/gen/java/com/dnfm/mina/protobuf/generated/CeraShopMoney.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/CeraShopMoneyOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/DiningPay.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/DiningPayOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/GuardianOrder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/GuardianOrderOrBuilder.java`

### Go代码

生成的Go代码位于:
- `dnf-go-client/gen/dnf/v1/misc_types.pb.go`

## 测试验证

### Go单元测试

测试文件: `dnf-go-client/test/batch27_test.go`

测试用例:
- `TestBatch27CeraShopMoney` - 测试钻石商店货币信息的序列化和反序列化
- `TestBatch27DiningPay` - 测试餐厅支付信息的序列化和反序列化
- `TestBatch27GuardianOrder` - 测试守护者订单信息的序列化和反序列化

测试结果: ✅ 全部通过

### Java编译验证

编译命令: `mvn clean compile -DskipTests`

编译结果: ✅ 成功

## 迁移成果

1. ✅ 成功迁移PT_CERA_SHOP_MONEY、PT_DINING_PAY和PT_GUARDIAN_ORDER三个杂项数据结构
2. ✅ 新增misc_types.proto文件，集中管理杂项数据结构
3. ✅ 生成了Java和Go语言的代码，支持跨语言通信
4. ✅ 编写了Go单元测试验证消息编解码
5. ✅ 验证了Java编译和功能测试
6. ✅ 更新了迁移文档和技能文档

## 注意事项

1. 这些是简单的数据结构，不需要分配单独的module ID
2. 所有字段都是可选的（required = false），在proto3中默认为optional
3. GuardianOrder消息包含了更多的字段，包括成长类型、职业、名称和等级等信息

## 后续工作

1. 根据实际使用情况，可能需要为这些杂项数据结构添加编解码器支持
2. 如果需要与旧版JProtobuf兼容，需要实现消息适配器
3. 根据业务需求，可能需要添加更多的验证逻辑

## 相关文档

- [批次23文档](../batch_23/)
- [批次24文档](../batch_24/)
- [批次25文档](../batch_25/)
- [批次26文档](../batch_26/)
- [JProtobuf迁移指南](../../.trae/skills/pix-jptotobuf-to-proto/SKILL.md)
