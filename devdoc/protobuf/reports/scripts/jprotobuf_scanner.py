#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
JProtobuf文件扫描器
扫描所有JProtobuf Java文件，提取消息信息并存储到数据库
"""

import sqlite3
import re
from pathlib import Path
from typing import Dict, List, Optional
from datetime import datetime

class JProtobufScanner:
    """JProtobuf文件扫描器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.jprotobuf_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def scan_jprotobuf_file(self, java_file: Path) -> Optional[Dict]:
        """扫描单个JProtobuf文件"""
        # 排除生成的文件
        if '$JProtoBufClass' in java_file.name:
            return None
        
        try:
            with open(java_file, 'r', encoding='utf-8') as f:
                content = f.read()
        except:
            return None
        
        # 提取消息信息
        message_info = {
            'message_name': None,
            'file_path': str(java_file),
            'module_id': None,
            'cmd': None,
            'message_type': None,
            'fields': [],
            'has_dependencies': False
        }
        
        # 提取消息名
        class_match = re.search(r'public\s+class\s+(\w+)\s+(?:extends\s+Message)?', content)
        if not class_match:
            return None
        
        message_info['message_name'] = class_match.group(1)
        
        # 确定消息类型
        if message_info['message_name'].startswith('REQ_'):
            message_info['message_type'] = 'REQ'
        elif message_info['message_name'].startswith('RES_'):
            message_info['message_type'] = 'RES'
        elif message_info['message_name'].startswith('PT_'):
            message_info['message_type'] = 'PT'
        elif message_info['message_name'].startswith('ENUM_'):
            message_info['message_type'] = 'ENUM'
        else:
            message_info['message_type'] = 'OTHER'
        
        # 提取ModuleID和CMD
        meta_match = re.search(r'@MessageMeta\s*\([^)]*module\s*=\s*(\d+)[^)]*cmd\s*=\s*(\d+)', content)
        if meta_match:
            message_info['module_id'] = int(meta_match.group(1))
            message_info['cmd'] = int(meta_match.group(2))
        
        # 提取字段
        field_pattern = r'@Protobuf\([^)]+\)\s+public\s+(\w+)\s+(\w+);'
        for match in re.finditer(field_pattern, content):
            annotations = match.group(0)
            field_type = match.group(1)
            field_name = match.group(2)
            
            # 提取order
            order_match = re.search(r'order\s*=\s*(\d+)', annotations)
            order = int(order_match.group(1)) if order_match else 0
            
            # 提取fieldType
            type_match = re.search(r'fieldType\s*=\s*FieldType\.(\w+)', annotations)
            field_type_full = type_match.group(1) if type_match else 'UNKNOWN'
            
            # 检查是否是repeated
            is_repeated = 'List<' in annotations
            
            # 检查是否是optional
            is_optional = 'required = false' in annotations
            
            message_info['fields'].append({
                'field_name': field_name,
                'field_type': field_type,
                'field_type_full': field_type_full,
                'order': order,
                'is_repeated': is_repeated,
                'is_optional': is_optional
            })
        
        # 检查是否有依赖（自定义类型）
        custom_types = set()
        for field in message_info['fields']:
            if field['field_type'] not in [
                'Integer', 'Long', 'String', 'Boolean', 
                'Float', 'Double', 'Byte', 'Short'
            ]:
                custom_types.add(field['field_type'])
        
        message_info['has_dependencies'] = len(custom_types) > 0
        message_info['field_count'] = len(message_info['fields'])
        
        return message_info
    
    def scan_all_jprotobuf_files(self) -> List[Dict]:
        """扫描所有JProtobuf文件"""
        print("🔍 扫描JProtobuf文件...")
        print()
        
        messages = []
        skipped = 0
        errors = 0
        
        java_files = list(self.jprotobuf_dir.glob('*.java'))
        total_files = len(java_files)
        
        for i, java_file in enumerate(java_files, 1):
            if (i - 1) % 100 == 0:
                print(f"  进度: {i}/{total_files}")
            
            message_info = self.scan_jprotobuf_file(java_file)
            
            if message_info:
                messages.append(message_info)
            elif '$JProtoBufClass' in java_file.name:
                skipped += 1
            else:
                errors += 1
        
        print(f"  完成: {len(messages)} 个消息")
        print(f"  跳过: {skipped} 个生成文件")
        print(f"  错误: {errors} 个文件")
        print()
        
        return messages
    
    def save_to_database(self, messages: List[Dict]):
        """保存到数据库"""
        print("💾 保存到数据库...")
        print()
        
        conn = self._connect()
        cursor = conn.cursor()
        
        inserted = 0
        updated = 0
        errors = 0
        
        for message_info in messages:
            try:
                # 检查是否已存在
                cursor.execute('''
                    SELECT id FROM jprotobuf_messages
                    WHERE message_name = ?
                ''', (message_info['message_name'],))
                
                existing = cursor.fetchone()
                
                if existing:
                    # 更新
                    cursor.execute('''
                        UPDATE jprotobuf_messages
                        SET module_id = ?, cmd = ?, message_type = ?, 
                            field_count = ?, has_dependencies = ?,
                            updated_at = CURRENT_TIMESTAMP
                        WHERE message_name = ?
                    ''', (
                        message_info['module_id'],
                        message_info['cmd'],
                        message_info['message_type'],
                        message_info['field_count'],
                        1 if message_info['has_dependencies'] else 0,
                        message_info['message_name']
                    ))
                    updated += 1
                else:
                    # 插入
                    cursor.execute('''
                        INSERT INTO jprotobuf_messages
                        (message_name, file_path, module_id, cmd, 
                         message_type, field_count, has_dependencies)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                    ''', (
                        message_info['message_name'],
                        message_info['file_path'],
                        message_info['module_id'],
                        message_info['cmd'],
                        message_info['message_type'],
                        message_info['field_count'],
                        1 if message_info['has_dependencies'] else 0
                    ))
                    inserted += 1
                
            except Exception as e:
                errors += 1
                print(f"  错误: {message_info['message_name']} - {e}")
        
        conn.commit()
        conn.close()
        
        print(f"  插入: {inserted} 条记录")
        print(f"  更新: {updated} 条记录")
        print(f"  错误: {errors} 条记录")
        print()
    
    def scan_and_save(self):
        """扫描并保存"""
        messages = self.scan_all_jprotobuf_files()
        self.save_to_database(messages)
        
        print("✅ JProtobuf扫描完成！")
        print()
        
        # 打印统计
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('SELECT COUNT(*) FROM jprotobuf_messages')
        total = cursor.fetchone()[0]
        
        cursor.execute('SELECT message_type, COUNT(*) FROM jprotobuf_messages GROUP BY message_type')
        type_stats = cursor.fetchall()
        
        conn.close()
        
        print("📊 统计信息:")
        print(f"  总消息数: {total}")
        print("  按类型分布:")
        for msg_type, count in type_stats:
            print(f"    {msg_type}: {count}")
        print()

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='JProtobuf文件扫描器')
    parser.add_argument('--scan', action='store_true', help='扫描并保存')
    
    args = parser.parse_args()
    
    if args.scan:
        db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
        scanner = JProtobufScanner(db_path)
        scanner.scan_and_save()
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
