#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
统计proto/dnf/v1目录中已迁移的消息数量
"""

import sqlite3
import re
from pathlib import Path
from typing import Set, Dict

class ProtoMigrationCounter:
    """统计proto文件中已迁移的消息数量"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.proto_dir = Path('/home/pix/dev/code/java/DnfGameServer/proto/dnf/v1')
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def scan_proto_messages(self) -> Dict[str, Set[str]]:
        """扫描proto文件中的所有消息定义"""
        proto_messages = {}
        
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取所有message定义
            message_pattern = r'message\s+(\w+)\s*\{'
            messages = set()
            
            for match in re.finditer(message_pattern, content):
                msg_name = match.group(1)
                messages.add(msg_name)
            
            if messages:
                proto_messages[proto_file.name] = messages
        
        return proto_messages
    
    def get_migrated_messages(self) -> Set[str]:
        """从数据库获取已迁移的消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('SELECT proto_message_name FROM jprotobuf_proto_mappings WHERE is_migrated = 1')
        migrated_messages = {row[0] for row in cursor.fetchall()}
        
        conn.close()
        return migrated_messages
    
    def count_migrated(self, proto_messages: Dict[str, Set[str]], 
                    migrated_messages: Set[str]) -> Dict:
        """统计已迁移的消息数量"""
        total_messages = 0
        migrated_count = 0
        not_migrated_count = 0
        
        proto_file_stats = {}
        
        for proto_file, messages in proto_messages.items():
            file_total = len(messages)
            file_migrated = len(messages & migrated_messages)
            file_not_migrated = file_total - file_migrated
            
            total_messages += file_total
            migrated_count += file_migrated
            not_migrated_count += file_not_migrated
            
            proto_file_stats[proto_file] = {
                'total': file_total,
                'migrated': file_migrated,
                'not_migrated': file_not_migrated,
                'migrated_messages': messages & migrated_messages,
                'not_migrated_messages': messages - migrated_messages
            }
        
        return {
            'total_messages': total_messages,
            'migrated_count': migrated_count,
            'not_migrated_count': not_migrated_count,
            'migration_rate': (migrated_count / total_messages * 100) if total_messages > 0 else 0,
            'proto_file_stats': proto_file_stats
        }
    
    def run(self):
        """执行统计操作"""
        print("🔍 正在扫描proto/dnf/v1目录中的消息定义...")
        proto_messages = self.scan_proto_messages()
        
        total_proto_messages = sum(len(msgs) for msgs in proto_messages.values())
        print(f"  找到 {len(proto_messages)} 个proto文件")
        print(f"  总共 {total_proto_messages} 个消息定义")
        
        print("\n🔍 正在从数据库获取已迁移的消息...")
        migrated_messages = self.get_migrated_messages()
        print(f"  数据库中已迁移的消息: {len(migrated_messages)} 个")
        
        print("\n📊 正在统计已迁移的消息数量...")
        stats = self.count_migrated(proto_messages, migrated_messages)
        
        print(f"\n📊 统计结果:")
        print(f"  总消息数: {stats['total_messages']}")
        print(f"  ✅ 已迁移: {stats['migrated_count']} ({stats['migration_rate']:.2f}%)")
        print(f"  ❌ 未迁移: {stats['not_migrated_count']} ({100 - stats['migration_rate']:.2f}%)")
        
        # 按文件统计
        print(f"\n📁 按文件统计:")
        sorted_files = sorted(stats['proto_file_stats'].items(), 
                          key=lambda x: x[1]['total'], 
                          reverse=True)
        
        for proto_file, file_stats in sorted_files:
            file_rate = (file_stats['migrated'] / file_stats['total'] * 100) if file_stats['total'] > 0 else 0
            print(f"  {proto_file}:")
            print(f"    总数: {file_stats['total']}, 已迁移: {file_stats['migrated']} ({file_rate:.2f}%), 未迁移: {file_stats['not_migrated']}")
        
        # 显示已迁移的消息列表
        if stats['migrated_count'] > 0:
            print(f"\n✅ 已迁移的消息列表:")
            for proto_file, file_stats in sorted_files:
                if file_stats['migrated_messages']:
                    print(f"  {proto_file}:")
                    for msg in sorted(file_stats['migrated_messages']):
                        print(f"    - {msg}")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    
    counter = ProtoMigrationCounter(db_path)
    counter.run()

if __name__ == '__main__':
    main()
