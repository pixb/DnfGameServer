import re
import os
import subprocess
from pathlib import Path

# Java 类型到 proto 类型的映射
FIELD_TYPE_MAP = {
    'INT32': 'int32',
    'UINT32': 'uint32',
    'INT64': 'int64',
    'UINT64': 'uint64',
    'FLOAT': 'float',
    'DOUBLE': 'double',
    'BOOL': 'bool',
    'STRING': 'string',
    'BYTES': 'bytes',
    'OBJECT': 'object',
}

def parse_javap_output(javap_output):
    """解析 javap 输出，提取字段信息"""
    fields = []
    
    # 提取常量池
    constant_pool = {}
    # 匹配有注释的常量池条目：#1 = Class              #2             // java/lang/Object
    const_pattern_with_comment = r'#(\d+)\s+=\s+(.+?)\s+//\s*(.+?)$'
    for match in re.finditer(const_pattern_with_comment, javap_output):
        index = int(match.group(1))
        value = match.group(2).strip()
        comment = match.group(3).strip() if match.group(3) else ''
        constant_pool[index] = {'value': value, 'comment': comment}
    
    # 匹配没有注释的常量池条目：#12 = Integer            1
    const_pattern_without_comment = r'#(\d+)\s+=\s+(\S+)\s+(.+?)(?=\n|$)'
    for match in re.finditer(const_pattern_without_comment, javap_output):
        index = int(match.group(1))
        if index not in constant_pool:  # 避免覆盖已有条目
            value = match.group(2).strip()
            const_value = match.group(3).strip()
            constant_pool[index] = {'value': value, 'const_value': const_value}
    
    # 提取字段信息
    field_pattern = r'public\s+([^\s;]+)\s+(\w+);'
    for match in re.finditer(field_pattern, javap_output):
        java_type = match.group(1)
        field_name = match.group(2)
        
        # 移除类型名中的 > 符号
        java_type = java_type.replace('>', '')
        
        # 查找该字段的注解信息
        annotation_pattern = rf'public\s+{re.escape(java_type)}\s+{re.escape(field_name)};.*?RuntimeVisibleAnnotations:(.*?)(?=public|\}})'
        annotation_match = re.search(annotation_pattern, javap_output, re.DOTALL)
        
        field_info = {
            'name': field_name,
            'java_type': java_type,
            'proto_type': None,
            'order': None,
            'required': False,
        }
        
        if annotation_match:
            annotation_text = annotation_match.group(1).strip()
            # 跳过 "0: " 前缀
            if annotation_text.startswith('0:'):
                annotation_text = annotation_text[2:].strip()
            
            # 注解格式：#7(#8=e#9.#10,#11=I#12,#13=Z#14)
            # 提取 fieldType: #8=e#9.#10
            field_type_match = re.search(r'#(\d+)=e#(\d+)\.#(\d+)', annotation_text)
            if field_type_match:
                field_type_idx = int(field_type_match.group(3))
                if field_type_idx in constant_pool:
                    # 尝试从 const_value 获取，如果没有则从 comment 获取，最后从 value 获取
                    field_type = constant_pool[field_type_idx].get('const_value', constant_pool[field_type_idx].get('comment', constant_pool[field_type_idx].get('value', '')))
                    
                    # 修复类型名中的特殊字符
                    field_type = field_type.replace('$', '_')  # 替换 $ 为 _
                    
                    # 映射字段类型（先检查 ENUM 类型）
                    if field_type == 'ENUM':
                        # 对于 ENUM 类型，从 Java 类型中提取枚举类型名
                        if 'ENUM_' in java_type:
                            # 从完整类名中提取枚举类型名
                            enum_type = java_type.split('.')[-1]
                            enum_type = enum_type.replace('$', '_')  # 替换 $ 为 _
                            proto_type = enum_type
                        else:
                            # 如果无法提取，使用默认类型
                            proto_type = 'int32'
                    elif field_type.startswith('ENUM_'):
                        # 对于枚举类型，保持原始大小写
                        proto_type = field_type
                    elif field_type in FIELD_TYPE_MAP:
                        proto_type = FIELD_TYPE_MAP[field_type]
                    else:
                        # 对于其他类型，使用原始大小写
                        proto_type = field_type
                    
                    field_info['proto_type'] = proto_type
            
            # 提取 order: #11=I#12
            order_match = re.search(r'#(\d+)=I#(\d+)', annotation_text)
            if order_match:
                order_idx = int(order_match.group(2))
                if order_idx in constant_pool:
                    # 尝试从 const_value 获取，如果没有则从 value 获取
                    order_value = constant_pool[order_idx].get('const_value', constant_pool[order_idx].get('value', ''))
                    if order_value.isdigit():
                        field_info['order'] = int(order_value)
            
            # 提取 required: #13=Z#14
            required_match = re.search(r'#(\d+)=Z#(\d+)', annotation_text)
            if required_match:
                required_idx = int(required_match.group(2))
                if required_idx in constant_pool:
                    # 尝试从 const_value 获取，如果没有则从 value 获取
                    required_value = constant_pool[required_idx].get('const_value', constant_pool[required_idx].get('value', ''))
                    if required_value.isdigit():
                        field_info['required'] = required_value == '1'
        
        # 如果没有注解信息，尝试从 Java 类型推断
        if not field_info['proto_type']:
            if 'List' in java_type:
                # 提取泛型类型
                generic_match = re.search(r'List<([^>]*)', java_type)
                if generic_match:
                    field_type = generic_match.group(1)
                    # 修复类型名中的特殊字符
                    field_type = field_type.replace('$', '_')  # 替换 $ 为 _
                    # 修复类型名中的包路径
                    if '.' in field_type:
                        field_type = field_type.split('.')[-1]
                    # 修复类型名中可能残留的 > 符号
                    field_type = field_type.replace('>', '')
                    field_info['proto_type'] = field_type
                    field_info['is_repeated'] = True
            elif 'Map' in java_type:
                # 提取泛型类型
                generic_match = re.search(r'Map<([^,]+),\s*([^>]+)>', java_type)
                if generic_match:
                    key_type = generic_match.group(1)
                    value_type = generic_match.group(2)
                    # 映射键类型
                    key_proto_type = FIELD_TYPE_MAP.get(key_type.upper(), key_type.lower())
                    # 修复值类型中的特殊字符
                    value_type = value_type.replace('$', '_')  # 替换 $ 为 _
                    # 修复值类型中的包路径
                    if '.' in value_type:
                        value_type = value_type.split('.')[-1]
                    field_info['proto_type'] = f'map<{key_proto_type}, {value_type}>'
                    field_info['is_map'] = True
            else:
                # 基本类型映射
                if 'Long' in java_type:
                    field_info['proto_type'] = 'int64'
                elif 'Integer' in java_type:
                    field_info['proto_type'] = 'int32'
                elif 'String' in java_type:
                    field_info['proto_type'] = 'string'
                elif 'Boolean' in java_type:
                    field_info['proto_type'] = 'bool'
                elif 'Float' in java_type:
                    field_info['proto_type'] = 'float'
                elif 'Double' in java_type:
                    field_info['proto_type'] = 'double'
                elif 'byte[]' in java_type:
                    field_info['proto_type'] = 'bytes'
                else:
                    # 假设是消息类型
                    field_type = java_type
                    # 修复类型名中的特殊字符
                    field_type = field_type.replace('$', '_')  # 替换 $ 为 _
                    # 修复类型名中的包路径
                    if '.' in field_type:
                        field_type = field_type.split('.')[-1]
                    field_info['proto_type'] = field_type
        
        fields.append(field_info)
    
    return fields

def get_javap_output(class_file_path):
    """使用 javap 获取 .class 文件的详细信息"""
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

def get_inner_classes(javap_output):
    """从 javap 输出中提取内部类"""
    inner_classes = []
    inner_class_pattern = r'public\s+static\s+#\d+=\s+#\d+\s+of\s+#\d+;\s*//(\w+)=class\s+([^\s]+)'
    for match in re.finditer(inner_class_pattern, javap_output):
        inner_class_name = match.group(1)
        inner_class_full_name = match.group(2)
        inner_classes.append({
            'name': inner_class_name,
            'full_name': inner_class_full_name,
        })
    return inner_classes

def is_enum_type(javap_output):
    """检测是否为 ENUM 类型"""
    # 检查是否继承自 java.lang.Enum
    if 'java/lang/Enum' in javap_output:
        return True
    return False

def extract_enum_values(javap_output):
    """从 ENUM 类型的 javap 输出中提取枚举值"""
    enum_values = []
    # 匹配枚举值的定义：putstatic     #10                 // Field NONE:Lcom/dnfm/mina/protobuf/ENUM_ROOM_TYPE$T;
    enum_value_pattern = r'putstatic\s+#\d+\s+//\s+Field\s+(\w+):'
    for match in re.finditer(enum_value_pattern, javap_output):
        enum_value_name = match.group(1)
        enum_values.append(enum_value_name)
    return enum_values

def generate_enum_proto_file(class_name, enum_values, output_path):
    """生成 ENUM 类型的 proto 文件"""
    # 替换 $ 为 _
    class_name_clean = class_name.replace('$', '_')
    
    proto_content = f'''// Generated from class file: {class_name}.class
syntax = "proto3";
package dnfm.protobuf;

enum {class_name_clean} {{
'''
    
    for i, enum_value in enumerate(enum_values):
        # 为枚举值添加前缀，避免与其他枚举类型的值冲突
        enum_value_with_prefix = f'{class_name_clean}_{enum_value}'
        proto_content += f'  {enum_value_with_prefix} = {i};\n'
    
    proto_content += '}\n'
    
    # 写入文件
    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    with open(output_path, 'w', encoding='utf-8') as f:
        f.write(proto_content)

def generate_proto_file(class_info, output_path):
    """生成 proto 文件"""
    class_name = class_info['class_name']
    fields = class_info['fields']
    inner_classes = class_info.get('inner_classes', [])
    
    # 收集 import 类型
    imported_types = set()
    for field in fields:
        field_type = field.get('proto_type', '')
        
        # 处理 repeated 类型
        if field.get('is_repeated'):
            # 从完整类名中提取简单类型名
            if '.' in field_type:
                field_type = field_type.split('.')[-1]
        
        # 处理 map 类型
        elif field.get('is_map'):
            # map 类型已经在 parse_javap_output 中处理过了
            continue
        
        # 基本类型不需要 import
        if field_type in ['int32', 'uint32', 'int64', 'uint64', 'sint32', 'sint64', 
                         'fixed32', 'fixed64', 'sfixed32', 'sfixed64', 
                         'bool', 'float', 'double', 'string', 'bytes']:
            continue
        
        # 自定义类型需要 import
        if field_type and field_type[0].isupper() and field_type != class_name:
            imported_types.add(field_type)
    
    # 生成 import 语句
    import_lines = []
    for imported_type in imported_types:
        imported_proto_filename = imported_type.lower() + '.proto'
        import_lines.append(f'import "{imported_proto_filename}";')
    
    # 生成 proto 内容
    proto_content = f'''// Generated from class file: {class_name}.class
syntax = "proto3";
package dnfm.protobuf;

'''
    for import_line in import_lines:
        proto_content += import_line + '\n'

    proto_content += f'''
message {class_name} {{
'''
    # 按字段序号排序
    sorted_fields = sorted([f for f in fields if f.get('order') is not None and f.get('order', 0) > 0], key=lambda x: x.get('order', 0))
    
    # 检查重复字段标签
    used_orders = set()
    unique_fields = []
    for field in sorted_fields:
        order = field.get('order', 0)
        if order not in used_orders:
            used_orders.add(order)
            unique_fields.append(field)
        else:
            print(f'  警告: 字段 {field.get("name")} 与其他字段重复标签 {order}，已跳过')
    
    # 调试信息
    print(f'  Total fields: {len(fields)}')
    print(f'  Sorted fields: {len(sorted_fields)}')
    print(f'  Unique fields: {len(unique_fields)}')
    for field in fields:
        print(f'    Field: {field}')
    
    for field in unique_fields:
        field_type = field.get('proto_type', '')
        field_name = field.get('name', '')
        order = field.get('order', 0)
        is_map = field.get('is_map', False)
        
        # 确保字段类型是有效的
        if not field_type:
            print(f'  警告: 字段 {field_name} 没有类型信息，已跳过')
            continue
        
        # 确保字段名是有效的
        if not field_name:
            print(f'  警告: 字段没有名称，已跳过')
            continue
        
        # 如果是 repeated 类型，从完整类名中提取简单类型名
        if field.get('is_repeated') and '.' in field_type:
            field_type = field_type.split('.')[-1]
        
        # 修复类型名中的特殊字符
        field_type = field_type.replace('$', '_')
        
        # 修复字段名中的特殊字符
        field_name = field_name.replace('$', '_')
        
        # 处理 protobuf 关键字
        proto_keywords = {
            'enum': 'enum_type',
            'message': 'message_type',
            'service': 'service_type',
            'rpc': 'rpc_type',
            'option': 'option_type',
            'import': 'import_type',
            'package': 'package_type',
            'syntax': 'syntax_type',
            'repeated': 'repeated_type',
            'required': 'required_type',
            'optional': 'optional_type',
            'bool': 'bool_type',
            'bytes': 'bytes_type',
            'double': 'double_type',
            'float': 'float_type',
            'int32': 'int32_type',
            'int64': 'int64_type',
            'uint32': 'uint32_type',
            'uint64': 'uint64_type',
            'sint32': 'sint32_type',
            'sint64': 'sint64_type',
            'fixed32': 'fixed32_type',
            'fixed64': 'fixed64_type',
            'sfixed32': 'sfixed32_type',
            'sfixed64': 'sfixed64_type',
            'string': 'string_type',
            'map': 'map_type',
            'oneof': 'oneof_type',
            'reserved': 'reserved_type',
            'to': 'to_value',
            'from': 'from_value',
            'true': 'true_value',
            'false': 'false_value',
            'default': 'default_value',
            'extend': 'extend_type',
            'extensions': 'extensions_type',
            'max': 'max_value',
            'min': 'min_value',
            'return': 'return_value',
        }
        
        if field_name.lower() in proto_keywords:
            print(f'  警告: 字段名 {field_name} 是 protobuf 关键字，已重命名为 {proto_keywords[field_name.lower()]}')
            field_name = proto_keywords[field_name.lower()]
        
        if is_map:
            proto_content += f'  {field_type} {field_name} = {order};\n'
        elif field.get('is_repeated'):
            proto_content += f'  repeated {field_type} {field_name} = {order};\n'
        else:
            proto_content += f'  {field_type} {field_name} = {order};\n'

    proto_content += '}\n'
    
    # 写入文件
    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    with open(output_path, 'w', encoding='utf-8') as f:
        f.write(proto_content)

def main():
    class_dir = 'protobuf-class-to-proto/classes/com/dnfm/mina/protobuf'
    proto_dir = 'proto/generated'
    
    # 创建 proto 目录
    os.makedirs(proto_dir, exist_ok=True)
    
    # 遍历 .class 文件
    class_files = list(Path(class_dir).rglob('*.class'))
    print(f'Found {len(class_files)} class files')
    
    # 过滤出消息类（排除 JProtoBufClass 和内部类）
    message_classes = []
    for class_file in class_files:
        class_name = class_file.stem
        
        # 排除 JProtoBufClass 和非 ENUM 的内部类
        if '$$JProtoBufClass' in class_name:
            continue
        
        # 对于内部类，只处理 ENUM 类型的内部类（如 ENUM_ROOM_TYPE$T）
        if '$' in class_name:
            # 检查是否为 ENUM 类型的内部类
            if not class_name.startswith('ENUM_'):
                continue
        
        message_classes.append(class_file)
    
    print(f'Found {len(message_classes)} message classes')
    
    generated_count = 0
    for class_file in message_classes:
        class_name = class_file.stem
        
        # 获取 javap 输出
        javap_output = get_javap_output(class_file)
        if not javap_output:
            continue
        
        # 检查是否为 ENUM 类型
        if is_enum_type(javap_output):
            # 提取枚举值
            enum_values = extract_enum_values(javap_output)
            
            # 调试信息
            if enum_values:
                print(f'  Found {len(enum_values)} enum values in {class_name}')
            else:
                print(f'  No enum values found in {class_name}')
            
            # 如果没有枚举值，跳过
            if not enum_values:
                continue
            
            # 生成 enum proto 文件
            proto_filename = class_name.replace('$', '_').lower() + '.proto'
            proto_path = os.path.join(proto_dir, proto_filename)
            
            generate_enum_proto_file(class_name, enum_values, proto_path)
            generated_count += 1
            print(f'Generated: {proto_path}')
            continue
        
        # 提取字段信息
        fields = parse_javap_output(javap_output)
        
        # 调试信息
        if fields:
            print(f'  Found {len(fields)} fields in {class_name}')
        else:
            print(f'  No fields found in {class_name}')
        
        # 提取内部类
        inner_classes = get_inner_classes(javap_output)
        
        # 如果没有字段，跳过
        if not fields:
            continue
        
        # 生成 proto 文件
        proto_filename = class_name.lower() + '.proto'
        proto_path = os.path.join(proto_dir, proto_filename)
        
        class_info = {
            'class_name': class_name,
            'fields': fields,
            'inner_classes': inner_classes,
        }
        
        generate_proto_file(class_info, proto_path)
        generated_count += 1
        print(f'Generated: {proto_path}')
    
    print(f'\nGenerated {generated_count} proto files')

if __name__ == '__main__':
    main()
