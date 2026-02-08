# pix-java-to-protobuf 技能

## 📂 基础信息

| 属性 | 值 |
| :--- | :--- |
| **名称** | pix-java-to-protobuf |
| **版本** | 1.0.0 |
| **类型** | 转换技能 (Conversion Skill) |
| **核心功能** | 从 Java 文件反推 protobuf 协议 |
| **适用环境** | Trae |

## 🎯 项目概述

pix-java-to-protobuf 是一个转换技能，旨在帮助用户从现有的 Java 文件（使用 JProtobuf 或类似框架）反推生成 protobuf 协议文件（.proto）。它提供结构化的指导，从 Java 文件解析到 proto 文件生成的完整流程。

### 核心特性

| 特性 | 描述 |
| :--- | :--- |
| **自动解析** | 自动解析 Java 文件中的 @Protobuf 注解 |
| **类型映射** | 完整的 Java 到 proto 类型映射表 |
| **泛型支持** | 支持 List、Map 等泛型类型的转换 |
| **Import 管理** | 自动收集并添加必要的 import 语句 |
| **Buf 集成** | 提供完整的 buf 配置示例 |

## 🛠️ 工作原理

当用户请求从 Java 文件生成 proto 文件时，pix-java-to-protobuf 会引导用户完成以下 5 个阶段：

### 阶段 1: 需求分析
- 分析 Java 文件的包结构和类定义
- 识别使用的注解框架
- 确定需要转换的文件范围
- 定义 proto 文件的输出目录和命名规范

### 阶段 2: 文件扫描
- 递归扫描 Java 源代码目录
- 过滤出包含 @Protobuf 注解的文件
- 识别消息类、枚举类和服务类
- 建立文件依赖关系图

### 阶段 3: 解析转换
- 解析 @Protobuf 注解，提取字段信息
- 处理字段类型映射（Java 类型 → proto 类型）
- 处理泛型类型（List、Map 等）
- 生成 proto 文件内容

### 阶段 4: 文件生成
- 按模块或包组织 proto 文件
- 添加必要的 import 语句
- 生成 buf 配置文件（可选）
- 使用 buf 工具生成目标语言代码

### 阶段 5: 验证优化
- 使用 buf lint 验证 proto 文件
- 使用 buf generate 生成目标语言代码
- 检查生成的代码是否正确
- 根据需要进行优化调整

## 📋 使用示例

### 示例 1: 简单消息类转换

**用户请求**："如何将这个 Java 类转换为 proto 文件？"

**Java 文件**：
```java
@ProtobufClass
public class PT_USER_INFO {
    @Protobuf(fieldType = FieldType.UINT64, order = 1)
    public Long charguid;

    @Protobuf(fieldType = FieldType.STRING, order = 2)
    public String name;

    @Protobuf(fieldType = FieldType.INT32, order = 3)
    public Integer level;
}
```

**响应**：

```markdown
# Proto 文件生成指南

## 阶段 1: 需求分析

- **类名**: PT_USER_INFO
- **字段数量**: 3
- **输出目录**: proto/generated/common

## 阶段 2: 文件扫描

已识别的消息类：PT_USER_INFO

## 阶段 3: 解析转换

**字段解析结果**：
1. `charguid`: Long → uint64 (order=1)
2. `name`: String → string (order=2)
3. `level`: Integer → int32 (order=3)

## 阶段 4: 文件生成

**生成的 proto 文件**：
```protobuf
// Generated from Java file: PT_USER_INFO.java
syntax = "proto3";
package dnfm.protobuf;

message PT_USER_INFO {
  uint64 charguid = 1;
  string name = 2;
  int32 level = 3;
}
```

## 阶段 5: 验证优化

1. 使用 `buf lint` 验证 proto 文件
2. 使用 `buf generate` 生成 Java 代码
3. 对比生成的代码和原始 Java 代码
```

### 示例 2: 复杂消息类转换

**用户请求**："转换包含 List 和 Map 的 Java 类"

**Java 文件**：
```java
@MessageMeta(module = 11011, cmd = 1)
@ProtobufClass
public class RES_START_DUNGEON {
    @Protobuf(fieldType = FieldType.INT32, order = 1)
    public Integer error;

    @Protobuf(order = 2)
    public List<PT_USER_INFO> users;

    @Protobuf(order = 3)
    public Map<Integer, PT_ITEM> items;
}
```

**响应**：

```markdown
# 复杂消息类转换指南

## 阶段 1: 需求分析

- **类名**: RES_START_DUNGEON
- **模块**: 11011
- **命令**: 1
- **字段数量**: 3（包含泛型类型）

## 阶段 2: 文件扫描

已识别的消息类：RES_START_DUNGEON
依赖类型：PT_USER_INFO, PT_ITEM

## 阶段 3: 解析转换

**字段解析结果**：
1. `error`: Integer → int32 (order=1)
2. `users`: List<PT_USER_INFO> → repeated PT_USER_INFO (order=2)
3. `items`: Map<Integer, PT_ITEM> → map<int32, PT_ITEM> (order=3)

## 阶段 4: 文件生成

**生成的 proto 文件**：
```protobuf
// Generated from Java file: RES_START_DUNGEON.java
syntax = "proto3";
package dnfm.protobuf;

import "common/pt_user_info.proto";
import "common/pt_item.proto";

message RES_START_DUNGEON {
  int32 error = 1;
  repeated PT_USER_INFO users = 2;
  map<int32, PT_ITEM> items = 3;
}

// Protocol Info:
// Module: 11011
// Cmd: 1
```

## 阶段 5: 验证优化

1. 使用 `buf lint` 验证 proto 文件
2. 使用 `buf generate` 生成 Java 代码
3. 验证生成的代码包含正确的字段和类型
```

## 🚀 使用指南

### 激活方式

当你询问以下类型的问题时，pix-java-to-protobuf 会自动激活：

- "如何从 Java 文件生成 proto 文件"
- "Java 到 protobuf 的转换"
- "反推 protobuf 协议"
- "解析 @Protobuf 注解"
- "生成 proto 文件"
- "convert java to proto"
- "generate protobuf from java"

### 预期输出

pix-java-to-protobuf 会提供：
1. 完整的转换流程指导
2. 详细的类型映射表
3. 注解解析指南
4. 实用的 Python 转换脚本
5. Buf 配置示例
6. 最佳实践和注意事项

## 📚 支持的概念范围

pix-java-to-protobuf 可以处理各种类型的 Java 类和注解：

| 支持的类型 | 示例 |
| :--- | :--- |
| **基本类型** | Integer, Long, String, Boolean |
| **集合类型** | List<T>, Map<K, V> |
| **消息类型** | 嵌套的消息类 |
| **枚举类型** | Java 枚举类 |
| **注解类型** | @Protobuf, @MessageMeta, @ProtobufClass |

## 🎓 设计理念

pix-java-to-protobuf 的设计基于以下理念：

1. **自动化优先**：提供自动化的转换脚本，减少手动工作
2. **类型安全**：确保类型映射的正确性和一致性
3. **结构规范**：生成符合 protobuf 规范的文件
4. **易于维护**：保持 proto 文件和 Java 文件的同步
5. **灵活扩展**：支持自定义类型映射和转换规则

通过遵循这些理念，pix-java-to-protobuf 不仅帮助用户生成 proto 文件，还培养用户的协议转换能力，使他们能够在未来自主处理更复杂的转换场景。

## 🤝 贡献指南

要为 pix-java-to-protobuf 技能做出贡献：

1. Fork 仓库
2. 为你的更改创建一个新分支
3. 添加新的功能或改进现有功能
4. 提交拉取请求与你的更改

## 💡 反馈

我们欢迎反馈以改进 pix-java-to-protobuf 技能。请提供以下方面的建议：

- 新的类型映射需求
- 更好的注解解析方法
- 改进的转换流程
- 错误修复或性能增强

## 📄 许可证

本技能以 MIT 许可证发布。
