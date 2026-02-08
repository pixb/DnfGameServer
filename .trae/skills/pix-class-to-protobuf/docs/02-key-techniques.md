# 关键技术点

## 1. 常量池解析

**问题**：javap 输出的常量池有两种格式
```
#10 = Utf8               INT32  // 有注释
#12 = Integer            1       // 无注释
```

**解决方案**：使用两个正则表达式匹配
```python
# 匹配有注释的常量池条目
const_pattern_with_comment = r'#(\d+)\s+=\s+(.+?)\s+//\s*(.+?)$'

# 匹配没有注释的常量池条目
const_pattern_without_comment = r'#(\d+)\s+=\s+(\S+)\s+(.+?)(?=\n|$)'
```

## 2. 注解文本处理

**问题**：javap 输出的注解格式为 `0: #7(#8=e#9.#10,#11=I#12,#13=Z#14)`

**解决方案**：跳过 "0: " 前缀
```python
if annotation_text.startswith('0:'):
    annotation_text = annotation_text[2:].strip()
```

## 3. 字段类型提取

**问题**：字段类型通过常量池引用，需要正确解析

**解决方案**：优先从 `const_value` 获取
```python
field_type = constant_pool[field_type_idx].get('const_value', 
    constant_pool[field_type_idx].get('comment', 
        constant_pool[field_type_idx].get('value', '')))
```

## 4. 特殊字符处理

**问题**：Java 内部类使用 `$` 符号，在 protobuf 中无效

**解决方案**：替换 `$` 为 `_`
```python
field_type = field_type.replace('$', '_')
```

## 5. ENUM 类型处理

**问题**：`fieldType` 为 `ENUM` 时，需要从 Java 类型中提取枚举类型名

**解决方案**：从完整类名中提取枚举类型名
```python
if field_type == 'ENUM':
    if 'ENUM_' in java_type:
        enum_type = java_type.split('.')[-1]
        enum_type = enum_type.replace('$', '_')
        proto_type = enum_type
    else:
        proto_type = 'int32'
```

## 6. import 语句生成

**问题**：自定义类型需要 import 语句

**解决方案**：收集需要导入的类型并生成 import 语句
```python
imported_types = set()
for field in fields:
    field_type = field.get('proto_type', '')
    if field_type and field_type[0].isupper() and field_type != class_name:
        imported_types.add(field_type)

import_lines = []
for imported_type in imported_types:
    imported_proto_filename = imported_type.lower() + '.proto'
    import_lines.append(f'import "{imported_subdir}/{imported_proto_filename}";')
```

## 7. 重复字段标签处理

**问题**：多个字段可能使用相同的标签

**解决方案**：检查并跳过重复标签
```python
used_orders = set()
unique_fields = []
for field in sorted_fields:
    order = field.get('order', 0)
    if order not in used_orders:
        used_orders.add(order)
        unique_fields.append(field)
    else:
        print(f'  警告: 字段 {field.get("name")} 与其他字段重复标签 {order}，已跳过')
```

## 8. 泛型类型提取

**问题**：Java 类型中包含泛型类型，如 `java.util.List<com.dnfm.mina.protobuf.ENUM_IDIP_PROHIBIT_TYPE$T>`

**解决方案**：正确提取泛型类型并移除特殊字符
```python
def extract_generic_type(java_type):
    if '<' in java_type and '>' in java_type:
        # 提取尖括号内的类型
        generic_type = java_type.split('<')[1].split('>')[0]
        # 移除包名，只保留类名
        generic_type = generic_type.split('.')[-1]
        # 替换特殊字符
        generic_type = generic_type.replace('$', '_')
        return generic_type
    return None
```