---
name: pix-jptotobuf-to-proto
description: This skill should be used when user asks to migrate from JProtobuf to standard Protobuf. Activates with phrases like migrate JProtobuf to Protobuf, convert JProtobuf to standard Protobuf, JProtobuf migration, JProtobuf to proto conversion, batch migration of JProtobuf. Provides structured guidance for incremental migration process including analysis, mapping, code generation, testing, and experience documentation.
---

# pix-jptotobuf-to-proto 技能

## 📂 基础信息

| 属性 | 值 |
| :--- | :--- |
| **名称** | pix-jptotobuf-to-proto |
| **版本** | 1.7.0 |
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

详细的迁移批次信息已拆分到独立文件：

**迁移批次详情**：[migrations.md](./migrations.md)

## 🌟 功能实现

核心功能和最佳实践已拆分到独立文件：

**功能实现和最佳实践**：[implementation.md](./implementation.md)

## 📁 文档结构

| 文档 | 描述 | 用途 |
| :--- | :--- | :--- |
| [SKILL.md](./SKILL.md) | 主文档 - 索引和概览 | 快速了解技能功能和结构 |
| [migrations.md](./migrations.md) | 迁移批次详情 | 查看所有迁移批次的详细信息 |
| [implementation.md](./implementation.md) | 功能实现和最佳实践 | 查看核心功能实现和最佳实践 |

## 🔧 技术栈

- **核心工具**：buf, protoc, protoc-gen-go, protoc-gen-go-grpc, protoc-gen-javalite
- **支持语言**：Java, Go
- **项目类型**：游戏服务器协议迁移
- **通信框架**：Mina (Java), 自定义Go客户端

## 📈 迁移统计

| 统计项 | 数值 |
| :--- | :--- |
| 总迁移批次 | 21 |
| 总迁移文件数 | 约150+ |
| 总测试用例数 | 约200+ |
| 支持的消息类型 | 约100+ |

## � 核心价值

1. **降低迁移风险**：结构化流程，减少错误
2. **提高迁移效率**：批量化处理，快速迭代
3. **保证通信兼容**：双模式编解码器，渐进式迁移
4. **跨语言支持**：同时支持 Java 和 Go
5. **经验沉淀**：详细文档和最佳实践

## 🔍 使用方法

1. **了解迁移流程**：阅读 [migrations.md](./migrations.md) 了解已完成的迁移批次
2. **查看功能实现**：阅读 [implementation.md](./implementation.md) 了解核心功能
3. **参考最佳实践**：根据需要查阅具体的功能文档
4. **执行迁移**：按照文档指引执行新的迁移批次

## � 版本历史

| 版本 | 日期 | 主要变更 |
| :--- | :--- | :--- |
| 1.7.0 | 2026-02-09 | 完成批次20 (MAIL)和批次21 (CHAT)迁移，优化文档结构 |
| 1.6.0 | 2026-02-09 | 完成批次18 (FRIEND)和批次19 (FRIEND续)迁移 |
| 1.5.0 | 2026-02-09 | 完成批次16 (TASK)和批次17 (TASK续)迁移 |
| 1.4.0 | 2026-02-09 | 完成批次14 (GUILD)和批次15 (GUILD续)迁移 |
| 1.3.0 | 2026-02-09 | 完成批次13 (ADVENTURE)迁移，新增冒险模块支持 |
| 1.2.0 | 2026-02-09 | 完成批次10-12迁移，新增物品、技能、成就模块支持 |
| 1.1.0 | 2026-02-09 | 完成批次01-09迁移，建立完整迁移流程 |
| 1.0.0 | 2026-02-09 | 初始化技能，建立基本结构和文档 |