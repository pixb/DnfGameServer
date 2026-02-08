# pix-class-to-protobuf

从 Java .class 文件生成 Protobuf 协议文件的 Trae 技能。

## 📖 简介

`pix-class-to-protobuf` 是一个强大的技能，能够从 Java .class 文件自动生成 Protobuf 协议文件。通过使用 javap 解析字节码，提取字段信息和注解，我们可以准确地重建 protobuf 协议定义。

## 🎯 核心功能

- **自动解析字节码**：使用 javap 命令解析 .class 文件
- **提取字段信息**：从 @Protobuf 注解中提取字段类型、序号等信息
- **类型映射**：自动将 Java 类型映射到 protobuf 类型
- **生成 import 语句**：自动收集和生成 import 语句
- **处理复杂类型**：支持 repeated、map 等复杂类型
- **错误处理**：全面的错误捕获和处理机制

## 🚀 快速开始

### 前置条件

- Python 3.6+
- JDK（javap 命令）

### 安装

1. 将 `pix-class-to-protobuf` 技能复制到 Trae 的 skills 目录：
```bash
cp -r pix-class-to-protobuf ~/.trae/skills/
```

2. 验证技能是否可用：
```bash
# 在 Trae 中询问
"从 class 文件生成 protobuf"
```

### 基本使用

#### 1. 准备 .class 文件

```bash
# 创建目录结构
mkdir -p protobuf-class-to-proto/classes/com/dnfm/mina/protobuf

# 复制 .class 文件
cp -r path/to/classes/*.class protobuf-class-to-proto/classes/com/dnfm/mina/protobuf/
```

#### 2. 运行生成脚本

```bash
# 使用默认配置
python generate_proto_from_class.py

# 指定输入输出目录
python generate_proto_from_class.py --class_dir "path/to/classes" --proto_dir "path/to/proto"
```

#### 3. 验证生成的文件

```bash
# 检查 proto 文件语法
cd proto/generated
buf lint
```

## 📁 目录结构

```
pix-class-to-protobuf/
├── SKILL.md              # 技能定义文件
├── README.md             # 技能说明文档（本文件）
├── scripts/              # 支持脚本
│   └── generate_proto_from_class.py  # 主要脚本文件
├── references/           # 参考文档
│   └── examples/         # 示例文件
└── assets/               # 资源文件
```

## 🛠️ 使用示例

### 示例 1：生成简单的 proto 文件

输入：`RES_VERIFICATION_AUTH.class`
```java
public class RES_VERIFICATION_AUTH extends Message {
    @Protobuf(fieldType = FieldType.INT32, order = 1)
    public Integer error;
    
    @Protobuf(fieldType = FieldType.INT32, order = 2)
    public Integer authkey;
}
```

输出：`res_verification_auth.proto`
```protobuf
// Generated from class file: RES_VERIFICATION_AUTH.class
syntax = "proto3";
package dnfm.protobuf;

message RES_VERIFICATION_AUTH {
  int32 error = 1;
  int32 authkey = 2;
}
```

### 示例 2：生成包含 import 的 proto 文件

输入：`RES_MONEY_ITEM_LIST.class`
```java
public class RES_MONEY_ITEM_LIST extends Message {
    @Protobuf(fieldType = FieldType.INT32, order = 1)
    public Integer error;
    
    @Protobuf(order = 2)
    public List<PT_MONEY_ITEM> currency;
    
    @Protobuf(order = 4)
    public List<PT_CURRENCY_DAILY_GAIN> characterdailygain;
}
```

输出：`res_money_item_list.proto`
```protobuf
// Generated from class file: RES_MONEY_ITEM_LIST.class
syntax = "proto3";
package dnfm.protobuf;

import "common/pt_currency_daily_gain.proto";
import "common/pt_money_item.proto";

message RES_MONEY_ITEM_LIST {
  int32 error = 1;
  repeated PT_MONEY_ITEM currency = 2;
  repeated PT_MONEY_ITEM accountcurrency = 3;
  repeated PT_CURRENCY_DAILY_GAIN characterdailygain = 4;
  repeated PT_CURRENCY_DAILY_GAIN accountdailygain = 5;
}
```

### 示例 3：处理 map 类型

输入：包含 Map 字段的类
```java
public class CREATURE_SKILL_INFO extends Message {
    @Protobuf(order = 1)
    public Map<Integer, PT_CREATURE_LEARN_SKILL_INFO> skillInfos;
}
```

输出：生成的 proto 文件
```protobuf
message CREATURE_SKILL_INFO {
  map<int32, PT_CREATURE_LEARN_SKILL_INFO> skillInfos = 1;
}
```

## 🔧 配置选项

### 命令行参数

| 参数 | 默认值 | 说明 |
|------|--------|------|
| `--class_dir` | `protobuf-class-to-proto/classes/com/dnfm/mina/protobuf` | 包含 .class 文件的目录 |
| `--proto_dir` | `proto/generated` | 生成 .proto 文件的目录 |

### 类型映射

| Java 类型 | Protobuf 类型 |
|-----------|---------------|
| `Integer` | `int32` |
| `Long` | `int64` |
| `String` | `string` |
| `Boolean` | `bool` |
| `Float` | `float` |
| `Double` | `double` |
| `byte[]` | `bytes` |
| `List<T>` | `repeated T` |
| `Map<K, V>` | `map<K, V>` |

## 🚨 常见问题

### Q: 为什么生成的 proto 文件有语法错误？

A: 可能的原因：
1. 类型名包含特殊字符（如 `$`）
2. 字段名包含特殊字符
3. 重复的字段标签

解决方案：
- 脚本会自动替换特殊字符为 `_`
- 检查并跳过重复标签
- 使用 `buf lint` 验证生成的 proto 文件

### Q: 为什么某些字段没有被提取？

A: 可能的原因：
1. 字段没有 `@Protobuf` 注解
2. 字段是 private 或 protected
3. 正则表达式没有匹配到字段

解决方案：
- 检查字段是否有 `@Protobuf` 注解
- 使用 `javap -p -v` 查看字段信息
- 调整正则表达式

### Q: 为什么 import 语句没有生成？

A: 可能的原因：
1. 自定义类型没有被正确识别
2. 类型名不满足 import 条件

解决方案：
- 检查类型名是否以大写字母开头
- 确保类型名不等于当前类名
- 调试 `imported_types` 集合

## 📚 参考资源

### 相关文档

- [Protobuf 官方文档](https://developers.google.com/protocol-buffers)
- [Java 字节码规范](https://docs.oracle.com/javase/specs/jvms/se8/html/)
- [javap 命令文档](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/javap.html)

### 相关技能

- `pix-java-to-protobuf` - 从 Java 源文件生成 protobuf
- `pix-skill-creator` - 创建 agent skill

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

MIT License

## 📮 联系方式

如有问题或建议，请通过以下方式联系：
- 提交 Issue
- 发送邮件

---

**注意**：本技能仅供学习和参考使用，请根据实际需求进行调整。
