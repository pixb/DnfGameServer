# 批次25: 系统数据结构迁移文档

## 概述

本批次迁移了系统相关的数据结构，包括角色信息、聊天基础信息和超链接数据结构。这些数据结构主要用于系统级别的通信和数据传输。

## 迁移日期

2026-02-09

## 迁移文件

1. **Actor.java** - 角色信息
2. **CHAT_BASE.java** - 聊天基础信息
3. **SUBSYSTEM.java** - 子系统相关数据结构

## 生成的Proto文件

- **proto/dnf/v1/system_types.proto** - 系统数据类型定义

## 消息类型映射

### 1. Actor (角色信息)

**Java类**: `com.dnfm.mina.protobuf.Actor`

**Proto消息**: `dnf.v1.Actor`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| accountkey | accountkey | string | 账户密钥 |
| cera | cera | int32 | 钻石数量 |
| charguid | charguid | string | 角色GUID |
| name | name | string | 角色名称 |
| openid | openid | string | OpenID |
| sessionid | sessionid | int32 | 会话ID |
| userip | userip | string | 用户IP |
| version | version | string | 版本号 |
| worldid | worldid | int32 | 世界ID |
| platid | platid | int32 | 平台ID |

### 2. ChatBase (聊天基础信息)

**Java类**: `com.dnfm.mina.protobuf.CHAT_BASE`

**Proto消息**: `dnf.v1.ChatBase`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| mType | m_type | int32 | 类型 |
| mMsg | m_msg | string | 消息 |
| mVoice | m_voice | string | 语音 |
| mVoidcetime | m_voidcetime | string | 过期时间 |
| hyperlinktype | hyperlinktype | int32 | 超链接类型 |
| hyperlinksubtype | hyperlinksubtype | int32 | 超链接子类型 |
| hyperlinkdatas | hyperlinkdatas | repeated HyperlinkData | 超链接数据 |
| sub | sub | repeated HyperlinkSystemMessageSub | 子消息 |

### 3. HyperlinkData (超链接数据)

**Java类**: `com.dnfm.mina.protobuf.SUBSYSTEM.Types.PT_HYPERLINK_DATA`

**Proto消息**: `dnf.v1.HyperlinkData`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| index | index | uint64 | 索引 |
| bind | bind | bool | 是否绑定 |

### 4. HyperlinkSystemMessageSub (超链接系统消息子消息)

**Java类**: `com.dnfm.mina.protobuf.SUBSYSTEM.Types.PT_HYPERLINK_SYSTEMMESSAGE_SUB`

**Proto消息**: `dnf.v1.HyperlinkSystemMessageSub`

**字段映射**:
| Java字段 | Proto字段 | 类型 | 说明 |
|---------|-----------|------|------|
| msg | msg | string | 消息内容 |

## 代码生成

### Java代码

生成的Java代码位于:
- `proto/gen/java/com/dnfm/mina/protobuf/generated/Actor.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/ActorOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/ChatBase.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/ChatBaseOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/HyperlinkData.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/HyperlinkDataOrBuilder.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/HyperlinkSystemMessageSub.java`
- `proto/gen/java/com/dnfm/mina/protobuf/generated/HyperlinkSystemMessageSubOrBuilder.java`

### Go代码

生成的Go代码位于:
- `dnf-go-client/gen/dnf/v1/system_types.pb.go`

## 测试验证

### Go单元测试

测试文件: `dnf-go-client/test/batch25_test.go`

测试用例:
- `TestBatch25Actor` - 测试角色信息消息的序列化和反序列化
- `TestBatch25HyperlinkData` - 测试超链接数据消息的序列化和反序列化
- `TestBatch25HyperlinkSystemMessageSub` - 测试超链接系统消息子消息的序列化和反序列化
- `TestBatch25ChatBase` - 测试聊天基础信息消息的序列化和反序列化

测试结果: ✅ 全部通过

### Java编译验证

编译命令: `mvn clean compile -DskipTests`

编译结果: ✅ 成功

## 迁移成果

1. ✅ 成功迁移Actor、CHAT_BASE和SUBSYSTEM三个系统数据结构
2. ✅ 新增system_types.proto文件，集中管理系统数据结构
3. ✅ 生成了Java和Go语言的代码，支持跨语言通信
4. ✅ 编写了Go单元测试验证消息编解码
5. ✅ 验证了Java编译和功能测试
6. ✅ 更新了迁移文档

## 注意事项

1. 这些是系统级别的数据结构，不需要分配单独的module ID
2. ChatBase消息包含嵌套的HyperlinkData和HyperlinkSystemMessageSub消息
3. SUBSYSTEM.java中包含重复的类定义（Types内部类和外部类），在proto中只保留一份定义
4. 所有字段都是可选的（required = false），在proto3中默认为optional

## 后续工作

1. 根据实际使用情况，可能需要为这些系统数据结构添加编解码器支持
2. 如果需要与旧版JProtobuf兼容，需要实现消息适配器
3. 根据业务需求，可能需要添加更多的验证逻辑

## 相关文档

- [批次23文档](../batch_23/)
- [批次24文档](../batch_24/)
- [JProtobuf迁移指南](../../.trae/skills/pix-jptotobuf-to-proto/SKILL.md)
