#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
从批次文档中提取已迁移的消息，更新数据库记录
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Set

class BatchMigrationUpdater:
    """批次迁移记录更新器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def extract_migrated_messages_from_batch(self, batch_num: int) -> Dict:
        """从批次文档中提取已迁移的消息"""
        batch_dir = self.batch_docs_dir / f'batch_{batch_num}'
        
        if not batch_dir.exists():
            return {}
        
        migrated_messages = {
            'batch_id': batch_num,
            'jprotobuf_messages': [],
            'proto_messages': [],
            'mappings': []
        }
        
        # 检查迁移计划
        plan_file = batch_dir / '01_迁移计划.md'
        if plan_file.exists():
            with open(plan_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取JProtobuf消息
            jprotobuf_pattern = r'`(\w+\.java)`'
            for match in re.finditer(jprotobuf_pattern, content):
                file_name = match.group(1)
                message_name = file_name.replace('.java', '')
                if message_name not in migrated_messages['jprotobuf_messages']:
                    migrated_messages['jprotobuf_messages'].append(message_name)
        
        # 检查迁移结果
        result_file = batch_dir / '02_迁移结果.md'
        if result_file.exists():
            with open(result_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取生成的Proto消息
            proto_pattern = r'`(\w+\.java)`'
            for match in re.finditer(proto_pattern, content):
                file_name = match.group(1)
                message_name = file_name.replace('.java', '')
                if message_name not in migrated_messages['proto_messages']:
                    migrated_messages['proto_messages'].append(message_name)
        
        return migrated_messages
    
    def get_all_batch_migrations(self) -> List[Dict]:
        """获取所有批次的迁移信息"""
        all_migrations = []
        
        for batch_dir in sorted(self.batch_docs_dir.glob('batch_*')):
            batch_match = re.search(r'batch_(\d+)', batch_dir.name)
            if not batch_match:
                continue
            
            batch_num = int(batch_match.group(1))
            migration_info = self.extract_migrated_messages_from_batch(batch_num)
            
            if migration_info and (migration_info.get('jprotobuf_messages') or migration_info.get('proto_messages')):
                all_migrations.append(migration_info)
        
        return all_migrations
    
    def get_standard_proto_messages(self) -> Set[str]:
        """获取所有标准Protobuf消息"""
        proto_messages = set()
        
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                proto_messages.add(match.group(1))
        
        return proto_messages
    
    def analyze_migration_gaps(self) -> Dict:
        """分析迁移记录的缺口"""
        print("🔍 分析迁移记录缺口...")
        print()
        
        # 获取所有标准Protobuf消息
        proto_messages = self.get_standard_proto_messages()
        print(f"📊 标准Protobuf消息总数: {len(proto_messages)}")
        
        # 获取数据库中的映射
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, proto_message_name, is_migrated, batch_id, id
            FROM jprotobuf_proto_mappings
        ''')
        
        db_mappings = cursor.fetchall()
        print(f"📋 数据库映射记录数: {len(db_mappings)}")
        
        # 获取所有批次的迁移信息
        all_migrations = self.get_all_batch_migrations()
        print(f"📁 批次文档数: {len(all_migrations)}")
        print()
        
        # 分析缺口
        gaps = {
            'proto_messages_not_in_db': [],
            'batch_messages_not_in_db': [],
            'unmapped_jprotobuf': []
        }
        
        # 1. 检查标准Protobuf消息是否在数据库中
        for proto_msg in sorted(proto_messages):
            cursor.execute('''
                SELECT COUNT(*) FROM jprotobuf_proto_mappings
                WHERE proto_message_name = ?
            ''', (proto_msg,))
            
            count = cursor.fetchone()[0]
            if count == 0:
                gaps['proto_messages_not_in_db'].append(proto_msg)
        
        # 2. 检查批次文档中的消息是否在数据库中
        for migration in all_migrations:
            for jprotobuf_msg in migration['jprotobuf_messages']:
                cursor.execute('''
                    SELECT COUNT(*) FROM jprotobuf_proto_mappings
                    WHERE jprotobuf_message_name = ?
                ''', (jprotobuf_msg,))
                
                count = cursor.fetchone()[0]
                if count == 0:
                    gaps['batch_messages_not_in_db'].append({
                        'batch_id': migration['batch_id'],
                        'message': jprotobuf_msg
                    })
        
        # 3. 检查未迁移的JProtobuf消息
        cursor.execute('''
            SELECT jprotobuf_message_name, id
            FROM jprotobuf_proto_mappings
            WHERE is_migrated = 0 OR is_migrated IS NULL
        ''')
        
        unmigrated = cursor.fetchall()
        for jprotobuf_msg, record_id in unmigrated:
            gaps['unmapped_jprotobuf'].append({
                'message': jprotobuf_msg,
                'id': record_id
            })
        
        conn.close()
        
        return gaps
    
    def print_migration_gaps_report(self, gaps: Dict):
        """打印迁移缺口报告"""
        print("=" * 80)
        print("迁移记录缺口报告")
        print("=" * 80)
        print()
        
        # 1. 标准Protobuf消息但不在数据库中
        if gaps['proto_messages_not_in_db']:
            print(f"📊 标准Protobuf消息但不在数据库中: {len(gaps['proto_messages_not_in_db'])}")
            print("-" * 80)
            for i, msg in enumerate(gaps['proto_messages_not_in_db'][:20], 1):
                print(f"  {i}. {msg}")
            if len(gaps['proto_messages_not_in_db']) > 20:
                print(f"  ... 还有 {len(gaps['proto_messages_not_in_db']) - 20} 个")
            print()
        
        # 2. 批次文档中的消息但不在数据库中
        if gaps['batch_messages_not_in_db']:
            print(f"📁 批次文档中的消息但不在数据库中: {len(gaps['batch_messages_not_in_db'])}")
            print("-" * 80)
            for i, item in enumerate(gaps['batch_messages_not_in_db'][:20], 1):
                print(f"  {i}. 批次{item['batch_id']}: {item['message']}")
            if len(gaps['batch_messages_not_in_db']) > 20:
                print(f"  ... 还有 {len(gaps['batch_messages_not_in_db']) - 20} 个")
            print()
        
        # 3. 未迁移的JProtobuf消息
        if gaps['unmapped_jprotobuf']:
            print(f"❌ 未迁移的JProtobuf消息: {len(gaps['unmapped_jprotobuf'])}")
            print("-" * 80)
            for i, item in enumerate(gaps['unmapped_jprotobuf'][:20], 1):
                print(f"  {i}. {item['message']} (ID: {item['id']})")
            if len(gaps['unmapped_jprotobuf']) > 20:
                print(f"  ... 还有 {len(gaps['unmapped_jprotobuf']) - 20} 个")
            print()
        
        print("=" * 80)

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='批次迁移记录更新器')
    parser.add_argument('--analyze', action='store_true', help='分析迁移记录缺口')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    updater = BatchMigrationUpdater(db_path)
    
    if args.analyze:
        gaps = updater.analyze_migration_gaps()
        updater.print_migration_gaps_report(gaps)
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
