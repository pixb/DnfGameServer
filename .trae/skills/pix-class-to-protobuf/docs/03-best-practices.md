# 最佳实践

## 1. 调试技巧

### 查看字段提取信息

在脚本中添加调试输出：
```python
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

## 2. 错误处理

### 处理 javap 调用失败

```python
try:
    result = subprocess.run(
        ['javap', '-p', '-v', str(class_file_path)],
        capture_output=True,
        text=True,
        check=True
    )
    return result.stdout
except subprocess.CalledProcessError as e:
    print(f'Error running javap on {class_file_path}: {e}')
    return None
```

### 处理无效字段

```python
# 确保字段类型是有效的
if not field_type:
    print(f'  警告: 字段 {field_name} 没有类型信息，已跳过')
    continue

# 确保字段名是有效的
if not field_name:
    print(f'  警告: 字段没有名称，已跳过')
    continue
```

## 3. 性能优化

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

## 4. 代码质量

### 模块化设计

- 将功能拆分为独立的函数
- 每个函数只做一件事
- 使用清晰的函数命名

### 错误处理

- 全面的错误捕获和处理
- 提供有用的错误信息
- 记录错误日志

### 文档注释

```python
def parse_javap_output(javap_output):
    """解析 javap 输出，提取字段信息
    
    Args:
        javap_output: javap 命令的输出文本
        
    Returns:
        list: 字段信息列表，每个字段包含 name, java_type, proto_type, order, required
    """
    # ... 实现逻辑
```

## 5. 分批调试

当需要处理大量 .class 文件时，建议使用分批调试策略：

### 创建批次文件

```bash
# 创建批次文件，每行一个 .class 文件名
echo "RES_VERIFICATION_AUTH.class" > batch_1.txt
echo "REQ_VERIFICATION_AUTH.class" >> batch_1.txt
```

### 使用批次文件生成

```bash
# 使用批次文件生成 proto 文件
python generate_proto_from_class_batch.py --batch batch_1.txt
```

### 分批测试策略

1. **第 1 批**：选择 10 个简单类型文件，测试基本功能
2. **第 2 批**：选择 15 个复杂类型文件，测试 ENUM、repeated、map 类型
3. **第 3 批**：选择 20 个有依赖关系的文件，测试 import 语句
4. **第 4 批**：选择 30 个文件，测试批量处理性能
5. **第 5 批**：生成所有文件，验证完整性

### 验证生成的文件

```bash
# 使用 buf lint 验证 proto 文件
cd proto/generated/batch_1_test
buf lint

# 使用 buf generate 生成 Java 代码
buf generate
```

## 6. 文件管理

### 目录结构

```
protobuf-class-to-proto/
├── classes/
│   └── com/dnfm/mina/protobuf/
│       ├── RES_VERIFICATION_AUTH.class
│       └── ...
├── proto/
│   └── generated/
│       ├── batch_1_test/
│       ├── batch_2_test/
│       └── common/
└── scripts/
    ├── generate_proto_from_class.py
    └── generate_proto_from_class_batch.py
```

### 批次文件管理

```bash
# 创建批次文件
echo "RES_VERIFICATION_AUTH.class" > batch_1.txt

# 清理批次文件，移除注释行
Get-Content test_batch.txt | Where-Object { $_ -notlike '#*' -and $_ -notlike '' } | Set-Content test_batch_clean.txt

# 合并批次文件
Get-Content batch_1.txt, batch_2.txt, batch_3.txt | Set-Content all_batches.txt
```

## 7. 验证和测试

### 使用 buf lint 验证

```bash
# 创建 buf.yaml 文件
cat > buf.yaml << EOF
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
buf lint
```

### 使用 buf generate 生成代码

```bash
# 生成 Java 代码
buf generate

# 生成其他语言的代码
buf generate --template=go
buf generate --template=cpp
```