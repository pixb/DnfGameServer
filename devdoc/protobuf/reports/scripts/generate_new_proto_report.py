#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
生成标准Protobuf新增消息的详细分析报告
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Set
from collections import defaultdict

class NewProtoMessageReportGenerator:
    """生成标准Protobuf新增消息的详细分析报告"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        self.java_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def get_new_proto_messages(self) -> Set[str]:
        """获取标准Protobuf新增的消息"""
        proto_messages = set()
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                proto_messages.add(match.group(1))
        
        conn = self._connect()
        cursor = conn.cursor()
        cursor.execute('SELECT DISTINCT proto_message_name FROM jprotobuf_proto_mappings WHERE proto_message_name IS NOT NULL')
        mapped_proto_messages = {row[0] for row in cursor.fetchall()}
        conn.close()
        
        return proto_messages - mapped_proto_messages
    
    def analyze_message_structure(self, proto_message: str) -> Dict:
        """分析消息的结构"""
        structure_info = {
            'message_name': proto_message,
            'defined_in': None,
            'fields': [],
            'nested_messages': [],
            'used_as_repeated': False,
            'used_as_field': False
        }
        
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 查找定义
            message_pattern = rf'message\s+{re.escape(proto_message)}\s*\{{([^}}]+)\}}'
            match = re.search(message_pattern, content, re.DOTALL)
            if match:
                structure_info['defined_in'] = proto_file.name
                message_body = match.group(1)
                
                # 提取字段
                field_pattern = r'(\w+)\s+(\w+)\s*=\s*(\d+);'
                for field_match in re.finditer(field_pattern, message_body):
                    field_type = field_match.group(1)
                    field_name = field_match.group(2)
                    field_number = field_match.group(3)
                    structure_info['fields'].append({
                        'type': field_type,
                        'name': field_name,
                        'number': field_number
                    })
                
                # 提取嵌套消息
                nested_pattern = r'message\s+(\w+)\s*\{'
                for nested_match in re.finditer(nested_pattern, message_body):
                    nested_name = nested_match.group(1)
                    structure_info['nested_messages'].append(nested_name)
            
            # 检查是否被使用
            if structure_info['defined_in'] and proto_file.name != structure_info['defined_in']:
                # 检查是否作为repeated字段使用
                repeated_pattern = rf'repeated\s+{re.escape(proto_message)}\s+\w+'
                if re.search(repeated_pattern, content):
                    structure_info['used_as_repeated'] = True
                
                # 检查是否作为普通字段使用
                field_pattern = rf'\b{re.escape(proto_message)}\b\s+\w+\s*='
                if re.search(field_pattern, content):
                    structure_info['used_as_field'] = True
        
        return structure_info
    
    def find_jprotobuf_context(self, proto_message: str) -> Dict:
        """查找JProtobuf中的上下文信息"""
        context_info = {
            'message_name': proto_message,
            'related_jprotobuf_files': [],
            'usage_context': []
        }
        
        # 转换为可能的JProtobuf名称
        base_name = proto_message.replace('Request', '').replace('Response', '')
        base_name_upper = base_name.upper()
        
        possible_names = [
            f"REQ_{base_name_upper}",
            f"RES_{base_name_upper}",
            f"PT_{base_name_upper}",
            base_name_upper,
            proto_message.upper()
        ]
        
        # 检查是否存在
        for name in possible_names:
            java_file = self.java_dir / f"{name}.java"
            if java_file.exists():
                context_info['related_jprotobuf_files'].append(name)
                
                # 读取文件内容
                try:
                    with open(java_file, 'r', encoding='utf-8') as f:
                        content = f.read()
                    
                    # 提取ModuleID和CMD
                    meta_match = re.search(r'@MessageMeta\s*\([^)]*module\s*=\s*(\d+)[^)]*cmd\s*=\s*(\d+)', content)
                    if meta_match:
                        module_id = meta_match.group(1)
                        cmd = meta_match.group(2)
                        context_info['usage_context'].append({
                            'jprotobuf_name': name,
                            'module_id': module_id,
                            'cmd': cmd
                        })
                except:
                    pass
        
        return context_info
    
    def generate_detailed_report(self, limit: int = 20):
        """生成详细分析报告"""
        print("=" * 80)
        print("标准Protobuf新增消息详细分析报告")
        print("=" * 80)
        print()
        
        new_messages = self.get_new_proto_messages()
        print(f"📊 找到 {len(new_messages)} 个标准Protobuf新增消息")
        print()
        
        # 按类型分类
        request_messages = []
        response_messages = []
        data_messages = []
        
        for proto_message in sorted(list(new_messages))[:limit]:
            structure_info = self.analyze_message_structure(proto_message)
            context_info = self.find_jprotobuf_context(proto_message)
            
            # 分类
            if 'Request' in proto_message:
                request_messages.append(proto_message)
            elif 'Response' in proto_message:
                response_messages.append(proto_message)
            else:
                data_messages.append(proto_message)
            
            # 打印详细信息
            print(f"【{proto_message}】")
            print(f"  定义文件: {structure_info['defined_in'] or '未找到'}")
            
            if structure_info['fields']:
                print(f"  字段数: {len(structure_info['fields'])}")
                for field in structure_info['fields'][:5]:
                    print(f"    - {field['type']} {field['name']} = {field['number']}")
                if len(structure_info['fields']) > 5:
                    print(f"    ... 还有 {len(structure_info['fields']) - 5} 个字段")
            
            if structure_info['used_as_repeated']:
                print(f"  使用方式: 作为repeated字段")
            elif structure_info['used_as_field']:
                print(f"  使用方式: 作为普通字段")
            
            if context_info['related_jprotobuf_files']:
                print(f"  相关JProtobuf文件: {', '.join(context_info['related_jprotobuf_files'])}")
                for ctx in context_info['usage_context']:
                    print(f"    - {ctx['jprotobuf_name']}: ModuleID={ctx['module_id']}, CMD={ctx['cmd']}")
            else:
                print(f"  相关JProtobuf文件: 无（这是迁移过程中新增的消息）")
            
            print()
        
        # 打印统计
        print("=" * 80)
        print("分类统计")
        print("=" * 80)
        print(f"请求消息 (Request): {len(request_messages)}")
        print(f"响应消息 (Response): {len(response_messages)}")
        print(f"数据消息 (Data): {len(data_messages)}")
        print()
        
        if len(new_messages) > limit:
            print(f"... 还有 {len(new_messages) - limit} 个新增消息未显示")

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='生成标准Protobuf新增消息的详细分析报告')
    parser.add_argument('--limit', type=int, default=20, help='限制显示结果数量')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    generator = NewProtoMessageReportGenerator(db_path)
    generator.generate_detailed_report(args.limit)

if __name__ == '__main__':
    main()
