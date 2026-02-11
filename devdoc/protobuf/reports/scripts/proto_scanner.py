#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
标准Protobuf文件扫描器
扫描所有标准Protobuf文件，提取消息信息并存储到数据库
"""

import sqlite3
import re
from pathlib import Path
from typing import Dict, List, Optional

class ProtoScanner:
    """标准Protobuf文件扫描器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def scan_proto_file(self, proto_file: Path) -> List[Dict]:
        """扫描单个Proto文件"""
        try:
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
        except:
            return []
        
        messages = []
        
        # 提取package
        package_match = re.search(r'package\s+([\w.]+);', content)
        package_name = package_match.group(1) if package_match else None
        
        # 提取所有消息定义
        message_pattern = r'message\s+(\w+)\s*\{([^}]+)\}'
        for match in re.finditer(message_pattern, content, re.DOTALL):
            message_name = match.group(1)
            message_body = match.group(2)
            
            message_info = {
                'message_name': message_name,
                'file_path': str(proto_file),
                'package_name': package_name,
                'fields': [],
                'is_nested': False,
                'parent_message': None,
                'field_count': 0
            }
            
            # 提取字段
            field_pattern = r'(\w+)\s+(\w+)\s*=\s*(\d+);\s*(//.*)?'
            for field_match in re.finditer(field_pattern, message_body):
                field_type = field_match.group(1)
                field_name = field_match.group(2)
                field_number = field_match.group(3)
                comment = field_match.group(4) or ''
                
                # 检查是否是repeated
                is_repeated = 'repeated' in message_body[:message_body.find(field_name)]
                
                # 检查是否是optional
                is_optional = 'optional' in message_body[:message_body.find(field_name)]
                
                message_info['fields'].append({
                    'field_name': field_name,
                    'field_type': field_type,
                    'field_number': int(field_number),
                    'is_repeated': is_repeated,
                    'is_optional': is_optional,
                    'comment': comment.strip()
                })
            
            message_info['field_count'] = len(message_info['fields'])
            messages.append(message_info)
        
        return messages
    
    def scan_all_proto_files(self) -> List[Dict]:
        """扫描所有Proto文件"""
        print("🔍 扫描标准Protobuf文件...")
        print()
        
        all_messages = []
        
        proto_files = list(self.proto_dir.glob('*.proto'))
        total_files = len(proto_files)
        
        for i, proto_file in enumerate(proto_files, 1):
            print(f"  扫描: {proto_file.name} ({i}/{total_files})")
            
            messages = self.scan_proto_file(proto_file)
            all_messages.extend(messages)
        
        print(f"  完成: {len(all_messages)} 个消息")
        print()
        
        return all_messages
    
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
                    SELECT id FROM proto_messages
                    WHERE message_name = ?
                ''', (message_info['message_name'],))
                
                existing = cursor.fetchone()
                
                if existing:
                    # 更新
                    cursor.execute('''
                        UPDATE proto_messages
                        SET file_path = ?, package_name = ?, field_count = ?,
                            updated_at = CURRENT_TIMESTAMP
                        WHERE message_name = ?
                    ''', (
                        message_info['file_path'],
                        message_info['package_name'],
                        message_info['field_count'],
                        message_info['message_name']
                    ))
                    updated += 1
                else:
                    # 插入
                    cursor.execute('''
                        INSERT INTO proto_messages
                        (message_name, file_path, package_name, field_count, 
                         is_nested, parent_message)
                        VALUES (?, ?, ?, ?, ?, ?)
                    ''', (
                        message_info['message_name'],
                        message_info['file_path'],
                        message_info['package_name'],
                        message_info['field_count'],
                        1 if message_info['is_nested'] else 0,
                        message_info['parent_message']
                    ))
                    inserted += 1
                
                # 获取消息ID
                cursor.execute('''
                    SELECT id FROM proto_messages
                    WHERE message_name = ?
                ''', (message_info['message_name'],))
                
                message_id = cursor.fetchone()[0]
                
                # 保存字段
                for field_info in message_info['fields']:
                    try:
                        cursor.execute('''
                            INSERT OR REPLACE INTO message_fields
                            (message_type, message_id, field_name, field_type,
                             field_number, is_repeated, is_optional, comment)
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                        ''', (
                            'proto',
                            message_id,
                            field_info['field_name'],
                            field_info['field_type'],
                            field_info['field_number'],
                            1 if field_info['is_repeated'] else 0,
                            1 if field_info['is_optional'] else 0,
                            field_info['comment']
                        ))
                    except Exception as e:
                        pass  # 忽略字段插入错误
                
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
        messages = self.scan_all_proto_files()
        self.save_to_database(messages)
        
        print("✅ 标准Protobuf扫描完成！")
        print()
        
        # 打印统计
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('SELECT COUNT(*) FROM proto_messages')
        total = cursor.fetchone()[0]
        
        cursor.execute('''
            SELECT file_path, COUNT(*) 
            FROM proto_messages 
            GROUP BY file_path 
            ORDER BY COUNT(*) DESC
        ''')
        
        file_stats = cursor.fetchall()
        
        conn.close()
        
        print("📊 统计信息:")
        print(f"  总消息数: {total}")
        print("  按文件分布:")
        for file_path, count in file_stats[:10]:
            file_name = Path(file_path).name
            print(f"    {file_name}: {count} 个消息")
        print()

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='标准Protobuf文件扫描器')
    parser.add_argument('--scan', action='store_true', help='扫描并保存')
    
    args = parser.parse_args()
    
    if args.scan:
        db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
        scanner = ProtoScanner(db_path)
        scanner.scan_and_save()
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
