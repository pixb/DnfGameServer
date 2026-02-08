---
name: pix-jptotobuf-to-proto
description: This skill should be used when user asks to migrate from JProtobuf to standard Protobuf. Activates with phrases like migrate JProtobuf to Protobuf, convert JProtobuf to standard Protobuf, JProtobuf migration, JProtobuf to proto conversion, batch migration of JProtobuf. Provides structured guidance for incremental migration process including analysis, mapping, code generation, testing, and experience documentation.
---

# pix-jptotobuf-to-proto 技能

## 📂 基础信息

| 属性 | 值 |
| :--- | :--- |
| **名称** | pix-jptotobuf-to-proto |
| **版本** | 1.0.0 |
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

## 🌟 功能示例

### 示例 1: 单文件迁移

**用户请求**："迁移 LOGIN.java 文件到标准 Protobuf"

**响应**：

```markdown
# LOGIN.java 迁移计划

## 一、分析阶段
- 分析 LOGIN.java 的字段定义
- 确认 @Protobuf 注解的字段类型和顺序
- 映射到标准 Protobuf 类型

## 二、生成 Proto 文件
- 在 proto/dnf/v1/auth.proto 中定义对应消息
- 保持字段序号和类型一致性
- 验证 Proto 文件语法正确

## 三、生成 Go 代码
- 执行 `buf generate` 生成 Go 代码
- 生成路径：`dnf-go-client/gen/go/dnf/v1/auth.pb.go`
- 验证生成的 Go 代码编译通过

## 四、编写 Go 测试用例
- 创建测试文件：`dnf-go-client/test/auth_test.go`
- 测试序列化/反序列化
- 测试通过

## 五、生成 Java 代码
- 执行 `buf generate` 生成 Java 代码
- 替换到 Java 工程
- 验证 Java 代码编译通过

## 六、调试 Java 服务端
- 修改 Java 代码使用新协议
- 编译并启动服务
- 验证服务启动成功

## 七、运行 Go 测试用例
- 再次运行 Go 测试用例
- 验证跨语言通信正常
- 本次重构完毕

## 八、经验总结
- 记录遇到的问题和解决方案
- 更新迁移最佳实践
- 为下一批迁移做准备
```

### 示例 2: 批次迁移计划

**用户请求**："为第一批迁移创建计划"

**响应**：

```markdown
# 批次 01 迁移计划

## 一、迁移概览
**批次编号**：batch_01
**迁移时间**：2026-02-08
**迁移文件数**：1个
**迁移状态**：计划中

## 二、迁移文件
### Java文件
- `src/main/java/com/dnfm/mina/protobuf/LOGIN.java`

### 对应Proto文件
- `proto/dnf/v1/auth.proto`

## 三、迁移步骤
1. 分析Java文件
2. 生成Proto文件
3. 生成Go代码
4. 编写Go测试用例
5. 生成Java代码
6. 调试Java服务端
7. 运行Go测试用例

## 四、测试规划
- Go测试用例：序列化/反序列化测试
- Java测试用例：消息转换测试
- 集成测试：跨语言通信测试

## 五、经验总结模板
- 问题记录
- 解决方案
- 最佳实践
- 改进建议
```

## 📋 迁移流程

### 阶段 1: 分析 Java 文件
1. **字段分析**：分析 `@Protobuf` 注解的字段定义
2. **类型映射**：映射到标准 Protobuf 类型
3. **顺序确认**：确认字段序号和必填性
4. **依赖分析**：分析消息间的依赖关系

### 阶段 2: 生成 Proto 文件
1. **文件创建**：在 `proto` 目录创建对应文件
2. **消息定义**：定义与 Java 对应的消息结构
3. **字段映射**：保持字段序号和类型一致
4. **语法验证**：验证 Proto 文件语法正确

### 阶段 3: 生成 Go 代码
1. **执行生成**：运行 `buf generate` 生成 Go 代码
2. **路径验证**：确认代码生成到正确路径
3. **编译检查**：验证生成的 Go 代码编译通过
4. **代码审查**：审查生成的代码结构

### 阶段 4: 编写 Go 测试用例
1. **测试文件创建**：创建对应的测试文件
2. **序列化测试**：测试消息序列化功能
3. **反序列化测试**：测试消息反序列化功能
4. **边界值测试**：测试边界值和异常情况

### 阶段 5: 生成 Java 代码
1. **执行生成**：运行 `buf generate` 生成 Java 代码
2. **代码集成**：将生成的代码集成到 Java 工程
3. **编译检查**：验证 Java 代码编译通过
4. **冲突解决**：解决可能的代码冲突

### 阶段 6: 调试 Java 服务端
1. **代码修改**：修改 Java 代码使用新协议
2. **编译构建**：编译整个项目
3. **服务启动**：启动 Java 服务端
4. **错误调试**：解决启动过程中的错误

### 阶段 7: 运行 Go 测试用例
1. **测试执行**：再次运行 Go 测试用例
2. **跨语言验证**：验证 Java 与 Go 通信正常
3. **问题解决**：解决跨语言通信问题
4. **经验总结**：记录本次迁移的经验

## 📁 批次文档结构

每次迁移都应该创建以下文档结构：

```
devdoc/protobuf/batch_XX/
├── 01_迁移计划.md      # 迁移概览和步骤
├── 02_文件映射.md      # Java到Proto的映射
├── 03_测试规划.md      # 测试策略和用例
└── 04_经验总结.md      # 问题和解决方案
```

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

## 📚 类型映射指南

### 基本类型映射

| JProtobuf 类型 | 标准 Protobuf 类型 | Go 类型 | Java 类型 |
| :--- | :--- | :--- | :--- |
| `FieldType.UINT64` | `uint64` | `uint64` | `long` |
| `FieldType.UINT32` | `uint32` | `uint32` | `int` |
| `FieldType.INT64` | `int64` | `int64` | `long` |
| `FieldType.INT32` | `int32` | `int32` | `int` |
| `FieldType.STRING` | `string` | `string` | `String` |
| `FieldType.BOOL` | `bool` | `bool` | `boolean` |
| `FieldType.FLOAT` | `float` | `float32` | `float` |
| `FieldType.DOUBLE` | `double` | `float64` | `double` |

### 复杂类型映射

| JProtobuf 类型 | 标准 Protobuf 类型 | Go 类型 | Java 类型 |
| :--- | :--- | :--- | :--- |
| `List<T>` | `repeated T` | `[]T` | `List<T>` |
| `Map<K,V>` | `map<K,V>` | `map[K]V` | `Map<K,V>` |
| 嵌套消息 | 嵌套消息 | 结构体 | 内部类 |
| 枚举类型 | `enum` | 枚举 | 枚举 |

## 🔍 常见问题与解决方案

### 1. Proto 文件错误

**问题**：`buf generate` 失败，提示字段 tag 重复

**解决方案**：
- 检查 Proto 文件中的字段序号
- 确保每个字段都有唯一的序号
- 从 1 开始连续编号

### 2. Java 插件配置错误

**问题**：`Unknown generator option: paths`

**解决方案**：
- Java 插件不支持 `paths` 选项
- 从 Java 插件配置中移除 `opt: paths=source_relative`

### 3. 类型映射错误

**问题**：跨语言类型不兼容

**解决方案**：
- 严格按照类型映射表进行映射
- 测试边界值和最大值
- 确保无符号类型正确处理

### 4. 序列化不兼容

**问题**：新旧协议序列化结果不兼容

**解决方案**：
- 保持字段序号一致
- 保持字段类型兼容
- 测试序列化/反序列化兼容性

### 5. 服务启动失败

**问题**：Java 服务启动失败

**解决方案**：
- 检查依赖是否正确
- 检查代码是否正确集成
- 查看详细的错误日志

## 📝 经验总结模板

每次迁移完成后，应该记录以下内容：

### 1. 问题记录
- **问题描述**：详细描述遇到的问题
- **发生阶段**：在哪个迁移步骤发生
- **影响范围**：影响了哪些功能

### 2. 解决方案
- **解决方法**：详细的解决方案
- **实施步骤**：具体的操作步骤
- **验证结果**：解决后的验证结果

### 3. 最佳实践
- **推荐做法**：经过验证的最佳实践
- **避免事项**：应该避免的错误做法
- **优化建议**：进一步优化的建议

### 4. 改进建议
- **流程改进**：迁移流程的改进建议
- **工具改进**：工具使用的改进建议
- **文档改进**：文档编写的改进建议

## 🎯 迁移成功标准

### Go 侧验证
- ✅ 所有 Go 测试用例通过
- ✅ Go 代码编译无错误
- ✅ 序列化/反序列化正确
- ✅ 边界值测试通过

### Java 侧验证
- ✅ 所有 Java 测试用例通过
- ✅ Java 代码编译无错误
- ✅ 服务启动成功
- ✅ 消息处理正确

### 集成验证
- ✅ Go 客户端能发送请求
- ✅ Java 服务端能接收请求
- ✅ Java 服务端能发送响应
- ✅ Go 客户端能接收响应
- ✅ 跨语言通信正常

## 📚 参考资源

### 工具文档
- [Buf 官方文档](https://buf.build/docs)
- [Protobuf 官方文档](https://protobuf.dev)
- [Go Protobuf 文档](https://pkg.go.dev/google.golang.org/protobuf)

### 最佳实践
- **渐进式迁移**：每次只迁移 1-2 个文件
- **充分测试**：每个步骤都要测试通过
- **文档化**：详细记录迁移过程和经验
- **跨语言验证**：确保 Java 和 Go 通信正常

### 风险控制
- **回退方案**：保留旧的 JProtobuf 实现
- **灰度发布**：逐步切换到新协议
- **监控**：添加日志监控协议使用情况
- **测试覆盖**：确保测试覆盖所有场景

## 🌟 总结

pix-jptotobuf-to-proto 技能提供了一个结构化、可重复的 JProtobuf 到标准 Protobuf 迁移流程。通过遵循 7 步迁移流程，使用批次化文档管理，结合跨语言测试验证，可以确保迁移过程安全、高效，同时积累宝贵的迁移经验。

记住，成功的迁移应该：
1. **计划先行**：详细的迁移计划和文档
2. **测试驱动**：每个步骤都有充分的测试
3. **渐进式**：小规模、可控的迁移步伐
4. **经验积累**：详细记录问题和解决方案
5. **跨语言验证**：确保不同语言间通信正常

现在，你已经准备好开始 JProtobuf 到标准 Protobuf 的迁移了！