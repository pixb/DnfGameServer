# 脚本文档

## 概述

本目录包含从 Java .class 文件生成 Protobuf 协议文件的脚本。

## 脚本列表

### 1. generate_proto_from_class.py

**功能**：从单个或多个 .class 文件生成 .proto 文件

**用法**：
```bash
# 基本用法
python generate_proto_from_class.py

# 指定输入输出目录
python generate_proto_from_class.py --class_dir "path/to/classes" --proto_dir "path/to/proto"

# 查看帮助
python generate_proto_from_class.py --help
```

**参数**：
- `--class_dir`：包含 .class 文件的目录（默认：`protobuf-class-to-proto/classes/com/dnfm/mina/protobuf`）
- `--proto_dir`：生成 .proto 文件的目录（默认：`proto/generated`）

**输出**：
- 在指定的 proto_dir 目录下生成 .proto 文件
- 每个有字段的类生成一个对应的 .proto 文件
- 自动生成 import 语句

### 2. generate_proto_from_class_batch.py

**功能**：使用批次文件批量生成 .proto 文件

**用法**：
```bash
# 基本用法
python generate_proto_from_class_batch.py --batch batch_1.txt

# 指定输出目录
python generate_proto_from_class_batch.py --batch batch_1.txt --proto_dir proto/generated/batch_1_test

# 查看帮助
python generate_proto_from_class_batch.py --help
```

**参数**：
- `--batch`：批次文件路径，每行一个 .class 文件名
- `--proto_dir`：生成 .proto 文件的目录（默认：`proto/generated`）
- `--class_dir`：包含 .class 文件的目录（默认：`protobuf-class-to-proto/classes/com/dnfm/mina/protobuf`）

**批次文件格式**：
```
# 注释行以 # 开头
RES_VERIFICATION_AUTH.class
REQ_VERIFICATION_AUTH.class
```

**输出**：
- 在指定的 proto_dir 目录下生成 .proto 文件
- 只处理批次文件中指定的 .class 文件
- 自动生成 import 语句

## 使用示例

### 示例 1：生成所有 .class 文件

```bash
# 进入项目目录
cd /path/to/project

# 运行生成脚本
python generate_proto_from_class.py
```

### 示例 2：使用批次文件生成

```bash
# 创建批次文件
cat > batch_1.txt << EOF
RES_VERIFICATION_AUTH.class
REQ_VERIFICATION_AUTH.class
EOF

# 运行生成脚本
python generate_proto_from_class_batch.py --batch batch_1.txt
```

### 示例 3：分批调试

```bash
# 第 1 批：测试基本功能
python generate_proto_from_class_batch.py --batch batch_1.txt --proto_dir proto/generated/batch_1_test

# 第 2 批：测试复杂类型
python generate_proto_from_class_batch.py --batch batch_2.txt --proto_dir proto/generated/batch_2_test

# 第 3 批：测试 import 语句
python generate_proto_from_class_batch.py --batch batch_3.txt --proto_dir proto/generated/batch_3_test
```

## 输出文件结构

生成的 .proto 文件结构如下：

```
proto/generated/
├── batch_1_test/
│   ├── res_verification_auth.proto
│   └── req_verification_auth.proto
├── batch_2_test/
│   ├── res_money_item_list.proto
│   └── ...
└── common/
    ├── pt_money_item.proto
    └── ...
```

## 验证生成的文件

### 使用 buf lint 验证

```bash
# 创建 buf.yaml 文件
cat > proto/generated/batch_1_test/buf.yaml << EOF
version: v2
deps:
  - buf.build/googleapis/googleapis

lint:
  use:
    - BASIC
    - ENUM_ZERO_VALUE_SUFFIX
    - FIELD_LOWER_SNAKE_CASE
    - MESSAGE_NAMES
    - SERVICE_NAMES
    - PACKAGE_SAME_DIRECTORY
  except:
    - ENUM_VALUE_PREFIX
    - FIELD_NOT_REQUIRED
    - PACKAGE_DIRECTORY_MATCH
    - PACKAGE_NO_IMPORT_CYCLE
    - PACKAGE_VERSION_SUFFIX
    - ENUM_ZERO_VALUE_SUFFIX
  disallow_comment_ignores: true

breaking:
  use:
    - FILE
  except:
    - EXTENSION_NO_DELETE
    - FIELD_SAME_DEFAULT
EOF

# 运行 buf lint
cd proto/generated/batch_1_test
buf lint
```

### 使用 buf generate 生成代码

```bash
# 生成 Java 代码
cd proto/generated/batch_1_test
buf generate

# 生成其他语言的代码
buf generate --template=go
buf generate --template=cpp
```

## 常见问题

### 问题 1：找不到 .class 文件

**原因**：文件路径不正确

**解决方案**：
```bash
# 检查文件是否存在
ls -la protobuf-class-to-proto/classes/com/dnfm/mina/protobuf/

# 使用正确的路径
python generate_proto_from_class.py --class_dir "correct/path/to/classes"
```

### 问题 2：生成的 proto 文件有语法错误

**原因**：类型名包含特殊字符

**解决方案**：
```bash
# 使用 buf lint 查看错误
cd proto/generated/batch_1_test
buf lint

# 根据错误信息修复问题
```

### 问题 3：import 文件缺失

**原因**：import 的文件不存在

**解决方案**：
```bash
# 创建 common 目录
mkdir -p proto/generated/common

# 复制 import 的文件到 common 目录
cp proto/generated/*.proto proto/generated/common/
```

## 性能优化

### 使用缓存

```python
import functools

@functools.lru_cache(maxsize=128)
def get_javap_output(class_file_path):
    """使用 javap 获取 .class 文件的详细信息（带缓存）"""
    # ... 实现逻辑
```

### 并行处理

```python
from concurrent.futures import ThreadPoolExecutor

def process_class_file(class_file):
    # 处理单个 .class 文件
    pass

with ThreadPoolExecutor(max_workers=4) as executor:
    results = list(executor.map(process_class_file, class_files))
```

## 调试技巧

### 查看字段提取信息

```python
# 在脚本中添加调试输出
if fields:
    print(f'  Found {len(fields)} fields in {class_name}')
else:
    print(f'  No fields found in {class_name}')

for field in fields:
    print(f'    Field: {field}')
```

### 手动解析单个 .class 文件

```bash
# 进入 classes 目录
cd protobuf-class-to-proto/classes/com/dnfm/mina/protobuf

# 使用 javap 查看详细信息
javap -p -v RES_VERIFICATION_AUTH.class
```

## 参考文档

- [实现细节](../docs/01-implementation.md)
- [关键技术点](../docs/02-key-techniques.md)
- [最佳实践](../docs/03-best-practices.md)
- [常见问题](../docs/04-faq.md)