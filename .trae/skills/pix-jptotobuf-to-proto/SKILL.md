---
name: pix-jptotobuf-to-proto
description: This skill should be used when user asks to migrate from JProtobuf to standard Protobuf. Activates with phrases like migrate JProtobuf to Protobuf, convert JProtobuf to standard Protobuf, JProtobuf migration, JProtobuf to proto conversion, batch migration of JProtobuf. Provides structured guidance for incremental migration process including analysis, mapping, code generation, testing, and experience documentation.
---

# pix-jptotobuf-to-proto 技能

## 📂 基础信息

| 属性 | 值 |
| :--- | :--- |
| **名称** | pix-jptotobuf-to-proto |
| **版本** | 1.6.0 |
| **类型** | 工具技能 (Tool Skill) |
| **核心功能** | JProtobuf 到标准 Protobuf 迁移 |
| **适用环境** | Trae |

## 🎯 核心目标

提供结构化的 JProtobuf 到标准 Protobuf 迁移流程，确保跨语言通信正常，减少迁移风险，积累迁移经验。

### 解决的问题

| 痛点 | 解决方案 |
| :--- | :--- |
| 手动迁移繁琐且容易出错 | 标准化的 7 步迁移流程 |
| 跨语言通信兼容性问题 | 详细的类型映射和测试验证 |
| 迁移经验无法积累 | 批次化文档和经验总结 |
| 大规模迁移风险高 | 渐进式迁移策略和回退方案 |

## 🚀 使用方法

### 激活方式

当你询问以下类型的问题时，pix-jptotobuf-to-proto 会自动激活：

- "迁移 JProtobuf 到标准 Protobuf"
- "将 Java 的 JProtobuf 转换为标准 Protobuf"
- "批量迁移 JProtobuf 协议"
- "JProtobuf 到 Proto 的映射"
- "为 JProtobuf 迁移创建批次计划"

### 预期输出

pix-jptotobuf-to-proto 会提供：
1. 完整的 7 步迁移流程指南
2. 详细的文件映射和类型转换规则
3. 批次化的迁移计划和文档模板
4. 跨语言测试策略和验证方法
5. 经验总结和问题解决方案

## 📚 迁移批次

### 批次01: AUTH_LOGIN (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_LOGIN.java, RES_LOGIN.java
- **状态**: ✅ 完成
- **文档**: [批次01文档](../../devdoc/protobuf/batch_01/)
- **成果**: 成功实现跨语言通信，验证了双模式编解码器

### 批次02: PING (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_PING.java, RES_PING.java
- **状态**: ✅ 完成
- **文档**: [批次02文档](../../devdoc/protobuf/batch_02/)
- **成果**: 
  - 成功迁移PING消息
  - SESSION_LOGOUT和HEARTBEAT暂未迁移（缺乏实际使用场景）
  - 验证了跨语言通信正确性

### 批次03: CHARACTER (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_CHARAC_LIST.java, RES_CHARAC_LIST.java
- **状态**: ✅ 完成
- **文档**: [批次03文档](../../devdoc/protobuf/batch_03/)
- **成果**: 
  - 成功迁移CHARAC_LIST消息
  - 新增CharacterWithEquipList消息类型解决结构不匹配问题
  - 处理复杂嵌套结构（角色、职业限制、装备列表）
  - 验证了跨语言通信正确性
  - Go单元测试7个测试用例全部通过
  - Java编译成功，无错误

### 批次04: CHANNEL (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_CREATE_CHARACTER.java, RES_CREATE_CHARACTER.java, REQ_CHANNEL_LIST.java, RES_CHANNEL_LIST.java, REQ_ENTER_CHANNEL.java
- **状态**: ✅ 完成
- **文档**: [批次04文档](../../devdoc/protobuf/batch_04/)
- **成果**: 
  - 成功迁移创建角色、频道列表、进入频道消息
  - 修复CreateCharacter消息的CMD值和字段定义
  - 新增channel.proto文件定义频道相关消息
  - 处理复杂嵌套结构（ClientInfo包含枚举类型）
  - 验证了跨语言通信正确性
  - Go单元测试9个测试用例全部通过
  - 编解码测试验证了跨语言通信

### 批次05: STANDBY (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_STANDBY.java, RES_STANDBY.java, REQ_REMOVE_CHARACTER.java, RES_REMOVE_CHARACTER.java, REQ_START_GAME.java, REQ_EXIT_CHARACTER.java
- **状态**: ✅ 完成
- **文档**: [批次05文档](../../devdoc/protobuf/batch_05/)
- **成果**: 
  - 成功迁移待机、删除角色、开始游戏、退出角色消息
  - 修复DeleteCharacter消息的CMD值和字段定义
  - 新增Standby、StartGame、ExitCharacter消息
  - 处理复杂嵌套结构（StartGameRequest包含ProtocolTransaction列表）
  - 验证了跨语言通信正确性
  - Go单元测试9个测试用例全部通过
  - 编解码测试验证了跨语言通信

### 批次06: AUTH (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_AUTHKEY_REFRESH.java, RES_AUTHKEY_REFRESH.java, REQ_PLATFORM_PROFILE_UPDATE.java, RES_PLATFORM_PROFILE_UPDATE.java
- **状态**: ✅ 完成
- **文档**: [批次06文档](../../devdoc/protobuf/batch_06/)
- **成果**: 
  - 成功迁移认证密钥刷新、平台资料更新消息
  - 新增auth.proto和platform.proto文件
  - 正确处理依赖关系（复用auth_login.proto中的ChannelInfo）
  - 处理列表类型（AuthkeyRefreshResponse包含频道信息列表）
  - 验证了跨语言通信正确性
  - Go单元测试7个测试用例全部通过
  - 编解码测试验证了跨语言通信

### 批次07: BATTLE, IDIP, SERVER_DATA (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_CONNECT_BATTLE_SERVER.java, RES_CONNECT_BATTLE_SERVER.java, REQ_IDIP_PROHIBIT_LIST.java, RES_IDIP_PROHIBIT_LIST.java, REQ_LOAD_SERVER_SIMPLE_DATA.java, RES_LOAD_SERVER_SIMPLE_DATA.java, REQ_SAVE_SERVER_SIMPLE_DATA.java, RES_SAVE_SERVER_SIMPLE_DATA.java
- **状态**: ✅ 完成
- **文档**: [批次07文档](../../devdoc/protobuf/batch_07/)
- **成果**: 
  - 成功迁移连接战斗服务器、IDIP禁止列表、服务器简单数据加载和保存消息
  - 新增battle.proto、idip.proto和server_data.proto文件
  - 解决枚举值冲突问题（将NONE重命名为IDIP_NONE）
  - 修复类型不匹配问题（将Prohibit.type从int32改为IdipProhibitType枚举）
  - 修复Java编译错误（StandardProtobufDecoder中hasBuildType()方法调用问题）
  - 验证了跨语言通信正确性
  - Go单元测试11个测试用例全部通过
  - 编解码测试验证了跨语言通信

### 批次08: TOWN (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_ENTER_TO_TOWN.java, RES_ENTER_TO_TOWN.java, REQ_LEAVE_FROM_TOWN.java, RES_LEAVE_FROM_TOWN.java, REQ_CHARACTER_INFO.java, RES_CHARACTER_INFO.java, REQ_TOWN_USER_GUID_LIST.java, RES_TOWN_USER_GUID_LIST.java, REQ_TARGET_USER_DETAIL_INFO.java, RES_TARGET_USER_DETAIL_INFO.java, REQ_INTERACTION_MENU.java, RES_INTERACTION_MENU.java
- **状态**: ✅ 完成
- **文档**: [批次08文档](../../devdoc/protobuf/batch_08/)
- **成果**: 
  - 成功迁移进入/离开城镇、角色信息、城镇用户GUID列表、目标用户详情、交互菜单消息
  - 新增town.proto文件定义城镇相关消息
  - 新增CharacterGuid和CharacterInfo数据结构
  - 暂缓城镇聊天消息迁移（依赖复杂数据结构）
  - 验证了跨语言通信正确性
  - Go单元测试8个测试用例全部通过
  - 编解码测试验证了跨语言通信

### 批次09: MAIL (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_MAIL_LIST.java, RES_MAIL_LIST.java, REQ_MAIL_GET.java, RES_MAIL_GET.java, REQ_MAIL_READ.java, RES_MAIL_READ.java, REQ_MAIL_DELETE.java, RES_MAIL_DELETE.java, REQ_MAIL_ITEM_ALL_GET.java, RES_MAIL_ITEM_ALL_GET.java, REQ_MAIL_ALL_DELETE.java, RES_MAIL_ALL_DELETE.java
- **状态**: ✅ 完成
- **文档**: [批次09文档](../../devdoc/protobuf/batch_09/)
- **成果**: 
  - 成功迁移邮件列表、获取邮件、读取邮件、删除邮件、一键领取所有邮件物品、删除所有邮件消息
  - 新增mail.proto文件定义邮件相关消息
  - 新增18个数据结构定义（SelectedItem, PostPackage, Stackable等）
  - ModuleID 15007的消息未迁移（非Message类，需要特殊处理）
  - 验证了跨语言通信正确性
  - Go单元测试7个测试用例全部通过
  - 编解码测试验证了跨语言通信
  - Java编译成功，无错误

### 批次10: ITEM (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_ITEM_USE.java, REQ_ITEM_REINFORCE.java
- **状态**: ✅ 完成
- **文档**: [批次10文档](../../devdoc/protobuf/batch_10/)
- **成果**: 
  - 成功迁移物品使用、物品强化消息
  - 新增item.proto文件定义物品相关消息
  - 定义了物品相关数据结构（ItemInfo等）
  - 扩展了StandardProtobufDecoder和StandardProtobufEncoder支持物品模块
  - 实现了物品相关消息的编解码支持
  - 验证了跨语言通信正确性
  - Go单元测试6个测试用例全部通过
  - Java编译成功，无错误

### 批次11: SKILL (已完成)
- **迁移日期**: 2026-02-09
- **迁移文件**: REQ_SKILL_SLOT.java, REQ_SKILL_SET.java
- **状态**: ✅ 完成
- **文档**: [批次11文档](../../devdoc/protobuf/batch_11/)
- **成果**: 
  - 成功迁移技能槽位、技能设置消息
  - 新增skill.proto文件定义技能相关消息
  - 定义了技能相关数据结构（SkillSlotInfo, SkillSlotSkillInfo等）
  - 扩展了StandardProtobufDecoder和StandardProtobufEncoder支持技能模块
  - 实现了技能相关消息的编解码支持
  - 验证了跨语言通信正确性
  - Go单元测试6个测试用例全部通过
  - Java编译成功，无错误

## 🌟 功能实现

### 核心功能

#### 1. 迁移流程管理

提供完整的 7 步迁移流程，从分析到测试的全过程。

**详细文档**：[01_迁移流程.md](./01_迁移流程.md)

#### 2. 类型映射

提供 JProtobuf 到标准 Protobuf 的完整类型映射表，包括基本类型、复杂类型和特殊类型。

**详细文档**：[02_类型映射.md](./02_类型映射.md)

#### 3. 问题解决

提供 16 个常见问题的解决方案，涵盖代码生成、编译错误、运行时错误和配置问题。

**详细文档**：[03_常见问题.md](./03_常见问题.md)

#### 4. 双模式编解码器

提供双模式编解码器的完整实现，支持渐进式迁移和灰度发布。

**详细文档**：[04_双模式编解码器.md](./04_双模式编解码器.md)

#### 5. 跨语言通信测试

提供跨语言通信测试的完整指南，包括测试环境准备、消息头结构、测试验证要点。

**详细文档**：[05_跨语言通信测试.md](./05_跨语言通信测试.md)

#### 6. 经验总结

提供经验总结模板，用于记录迁移过程中遇到的问题、解决方案和最佳实践。

**详细文档**：[06_经验总结模板.md](./06_经验总结模板.md)

#### 7. Proto文件选项说明

提供Proto文件选项的详细说明，特别是`java_multiple_files`选项的重要性。

**详细文档**：[07_Proto文件选项说明.md](./07_Proto文件选项说明.md)

### 代码示例

#### 示例 1: Proto 文件选项配置

```protobuf
syntax = "proto3";

package dnf.v1;

// Go代码生成选项
option go_package = "dnf/proto/v1;sessionv1";

// Java代码生成选项（重要）
option java_multiple_files = true;  // 为每个消息生成独立的Java文件
option java_package = "com.dnfm.mina.protobuf.generated";  // 指定Java包名
option java_outer_classname = "SessionProto";  // 指定外层类名

message PingRequest {
}
```

#### 示例 2: 消息适配方法

```java
private byte[] adaptLoginResponse(Message msg) throws Exception {
    RES_LOGIN oldResponse = (RES_LOGIN) msg;
    
    LoginResponse.Builder builder = LoginResponse.newBuilder();
    
    if (oldResponse.error != null) {
        builder.setError(oldResponse.error);
    }
    if (oldResponse.authkey != null) {
        builder.setAuthkey(oldResponse.authkey);
    }
    
    return builder.build().toByteArray();
}
```

#### 示例 3: Go 客户端消息头构建

```go
func buildMessageHeader(moduleID uint16, seq uint8, transactionID uint8, body []byte) []byte {
    totalLen := uint16(len(body) + 8)
    
    buf := new(bytes.Buffer)
    binary.Write(&buf, binary.LittleEndian, totalLen)
    binary.Write(&buf, binary.LittleEndian, moduleID)
    buf.WriteByte(seq)
    buf.WriteByte(transactionID)
    binary.Write(&buf, binary.LittleEndian, uint16(0))
    buf.Write(body)
    
    return buf.Bytes()
}
```

#### 示例 3: 类型转换工具

```java
public class TypeConverter {
    public static String toProtoType(FieldType fieldType) {
        switch (fieldType) {
            case INT32:
                return "int32";
            case INT64:
                return "int64";
            case STRING:
                return "string";
            default:
                return "unknown";
        }
    }
}
```

## 📋 最佳实践

### 迁移策略

- **渐进式迁移**：每次只迁移 1-2 个文件，降低风险
- **充分测试**：每个步骤都要测试通过，确保质量
- **文档化**：详细记录迁移过程和经验，便于后续参考
- **跨语言验证**：确保 Java 和 Go 通信正常，验证兼容性
- **批次化管理**：按批次组织迁移工作，便于跟踪和回顾

### 代码质量

- **Null 检查**：在消息适配方法中为所有字段添加 null 检查
- **模块化设计**：将功能拆分为独立的模块，便于维护
- **错误处理**：全面的错误捕获和处理，提供友好的错误信息
- **代码注释**：详细的代码注释和文档，便于理解和维护

### 性能优化

- **缓存策略**：合理使用缓存减少重复计算
- **异步处理**：使用异步操作提高性能
- **资源管理**：及时释放不需要的资源
- **代码优化**：减少不必要的计算和操作

### 用户体验

- **清晰的输出**：结构化、易读的输出格式
- **友好的错误信息**：详细、有用的错误提示
- **进度反馈**：长时间操作的进度提示
- **合理的默认值**：为参数提供合理的默认值

### 风险控制

- **回退方案**：保留旧的 JProtobuf 实现，便于快速回退
- **灰度发布**：逐步切换到新协议，降低风险
- **监控**：添加日志监控协议使用情况
- **测试覆盖**：确保测试覆盖所有场景

### 端口管理

- **明确区分**：HTTP端口和游戏服务器端口要明确区分
- **配置管理**：在配置文件中统一管理端口配置
- **日志记录**：启动时记录监听的端口，便于调试

## 🛠️ 工具和命令

### 核心命令

| 命令 | 描述 | 用途 |
| :--- | :--- | :--- |
| `buf generate` | 生成多语言代码 | 生成 Go 和 Java 代码 |
| `go test ./...` | 运行 Go 测试 | 验证 Go 代码功能 |
| `mvn clean compile` | 编译 Java 项目 | 验证 Java 代码编译 |
| `mvn spring-boot:run` | 启动 Java 服务 | 测试服务端功能 |

### 配置文件

| 文件 | 用途 | 位置 |
| :--- | :--- | :--- |
| `proto/buf.yaml` | buf 配置 | 主项目根目录 |
| `proto/buf.gen.yaml` | 代码生成配置 | 主项目根目录 |
| `proto/dnf/v1/*.proto` | Proto 协议文件 | 按包结构组织 |

## 📁 文档索引

本技能采用总-分结构，主文档提供索引和概览，详细内容在子文档中：

| 文档 | 描述 | 适用场景 |
| :--- | :--- | :--- |
| [01_迁移流程.md](./01_迁移流程.md) | 7步迁移流程详解 | 开始迁移前了解完整流程 |
| [02_类型映射.md](./02_类型映射.md) | JProtobuf到标准Protobuf类型映射 | 查找类型转换规则 |
| [03_常见问题.md](./03_常见问题.md) | 16个常见问题与解决方案 | 遇到问题时查找解决方案 |
| [04_双模式编解码器.md](./04_双模式编解码器.md) | 双模式编解码器实现 | 实现渐进式迁移 |
| [05_跨语言通信测试.md](./05_跨语言通信测试.md) | 跨语言通信测试指南 | 验证跨语言通信 |
| [06_经验总结模板.md](./06_经验总结模板.md) | 经验总结模板 | 记录迁移经验 |
| [07_Proto文件选项说明.md](./07_Proto文件选项说明.md) | Proto文件选项详细说明 | 配置Proto文件选项 |

## 📚 参考资源

### 工具文档
- [Buf 官方文档](https://buf.build/docs)
- [Protobuf 官方文档](https://protobuf.dev)
- [Go Protobuf 文档](https://pkg.go.dev/google.golang.org/protobuf)

### 技能文档
- [01_迁移流程.md](./01_迁移流程.md) - 7 步迁移流程详解
- [02_类型映射.md](./02_类型映射.md) - JProtobuf 到标准 Protobuf 类型映射
- [03_常见问题.md](./03_常见问题.md) - 常见问题与解决方案
- [04_双模式编解码器.md](./04_双模式编解码器.md) - 双模式编解码器实现
- [05_跨语言通信测试.md](./05_跨语言通信测试.md) - 跨语言通信测试指南
- [06_经验总结模板.md](./06_经验总结模板.md) - 经验总结模板

## 🌟 总结

pix-jptotobuf-to-proto 技能提供了一个结构化、可重复的 JProtobuf 到标准 Protobuf 迁移流程。通过遵循 7 步迁移流程，使用批次化文档管理，结合跨语言测试验证，可以确保迁移过程安全、高效，同时积累宝贵的迁移经验。

记住，成功的迁移应该：
1. **计划先行**：详细的迁移计划和文档
2. **测试驱动**：每个步骤都有充分的测试
3. **渐进式**：小规模、可控的迁移步伐
4. **经验积累**：详细记录问题和解决方案
5. **跨语言验证**：确保不同语言间通信正常

现在，你已经准备好开始 JProtobuf 到标准 Protobuf 的迁移了！
