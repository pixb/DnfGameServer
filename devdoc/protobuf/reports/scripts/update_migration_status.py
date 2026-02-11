#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
根据标准Protobuf定义更新迁移状态
定义：生成了对JProtobuf生成标准的消息定义即为迁移
"""

import sqlite3
import re
from pathlib import Path
from typing import Dict, List, Tuple

class MigrationStatusUpdater:
    """迁移状态更新器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        self.java_dir = self.project_root / 'src/main/java/com/dnfm/mina/protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def get_standard_proto_messages(self) -> Dict[str, Dict]:
        """获取所有标准Protobuf消息及其定义文件"""
        proto_messages = {}
        
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                message_name = match.group(1)
                proto_messages[message_name] = {
                    'file': proto_file.name,
                    'path': str(proto_file)
                }
        
        return proto_messages
    
    def find_jprotobuf_mapping(self, proto_message: str) -> Tuple[str, str]:
        """查找标准Protobuf消息对应的JProtobuf消息"""
        # 尝试多种映射方式
        
        # 1. 直接映射：AchievementReward -> PT_ACHIEVEMENT_REWARD
        possible_jprotobuf_names = []
        
        # 转换为全大写
        possible_jprotobuf_names.append(f"PT_{proto_message.upper()}")
        possible_jprotobuf_names.append(f"REQ_{proto_message.replace('Request', '').upper()}")
        possible_jprotobuf_names.append(f"RES_{proto_message.replace('Response', '').upper()}")
        
        # 检查JProtobuf文件是否存在
        for jprotobuf_name in possible_jprotobuf_names:
            java_file = self.java_dir / f"{jprotobuf_name}.java"
            if java_file.exists():
                return jprotobuf_name, 'direct'
        
        # 2. 检查批次文档
        batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
        for batch_dir in sorted(batch_docs_dir.glob('batch_*')):
            batch_match = re.search(r'batch_(\d+)', batch_dir.name)
            if not batch_match:
                continue
            
            batch_id = int(batch_match.group(1))
            
            # 检查迁移计划
            plan_file = batch_dir / '01_迁移计划.md'
            if plan_file.exists():
                with open(plan_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                if proto_message in content:
                    return f"BATCH_{batch_id}", 'batch_document'
        
        return None, None
    
    def update_migration_status(self, dry_run: bool = True) -> Dict:
        """更新迁移状态"""
        print("🔍 开始更新迁移状态...")
        print()
        
        # 获取所有标准Protobuf消息
        proto_messages = self.get_standard_proto_messages()
        print(f"📊 标准Protobuf消息总数: {len(proto_messages)}")
        print()
        
        conn = self._connect()
        cursor = conn.cursor()
        
        # 统计
        stats = {
            'total_proto_messages': len(proto_messages),
            'already_in_db': 0,
            'new_records': 0,
            'update_records': 0,
            'batch_ids': set()
        }
        
        # 处理每个标准Protobuf消息
        for i, (proto_message, proto_info) in enumerate(proto_messages.items()):
            if (i + 1) % 100 == 0:
                print(f"  进度: {i + 1}/{len(proto_messages)}")
            
            # 检查数据库中是否已有记录
            cursor.execute('''
                SELECT id, jprotobuf_message_name, is_migrated, batch_id
                FROM jprotobuf_proto_mappings
                WHERE proto_message_name = ?
            ''', (proto_message,))
            
            existing = cursor.fetchone()
            
            if existing:
                # 已有记录，检查是否需要更新
                record_id, jprotobuf_name, is_migrated, batch_id = existing
                
                if not is_migrated or is_migrated == 0:
                    # 更新为已迁移
                    if not dry_run:
                        cursor.execute('''
                            UPDATE jprotobuf_proto_mappings
                            SET is_migrated = 1, updated_at = CURRENT_TIMESTAMP
                            WHERE id = ?
                        ''', (record_id,))
                    
                    stats['update_records'] += 1
                    if batch_id:
                        stats['batch_ids'].add(batch_id)
                else:
                    stats['already_in_db'] += 1
            else:
                # 没有记录，创建新记录
                # 查找对应的JProtobuf消息
                jprotobuf_name, mapping_type = self.find_jprotobuf_mapping(proto_message)
                
                # 确定批次ID
                batch_id = None
                if mapping_type == 'batch_document' and jprotobuf_name:
                    batch_match = re.search(r'BATCH_(\d+)', jprotobuf_name)
                    if batch_match:
                        batch_id = int(batch_match.group(1))
                
                # 确定消息类型
                message_type = 'NEW'
                if proto_message.endswith('Request'):
                    message_type = 'REQ'
                elif proto_message.endswith('Response'):
                    message_type = 'RES'
                elif jprotobuf_name and jprotobuf_name.startswith('PT_'):
                    message_type = 'PT'
                
                if not dry_run:
                    cursor.execute('''
                        INSERT INTO jprotobuf_proto_mappings
                        (jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                         proto_file_path, is_migrated, module_id, message_type, batch_id)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    ''', (
                        jprotobuf_name or f"NEW_{proto_message}",  # 使用占位符
                        "NEW_MESSAGE" if not jprotobuf_name else f"{jprotobuf_name}.java",  # 使用占位符路径
                        proto_message,
                        proto_info['path'],
                        1,  # 已迁移
                        None,
                        message_type,
                        batch_id
                    ))
                
                stats['new_records'] += 1
                if batch_id:
                    stats['batch_ids'].add(batch_id)
        
        if not dry_run:
            conn.commit()
        
        conn.close()
        
        return stats
    
    def print_update_report(self, stats: Dict):
        """打印更新报告"""
        print()
        print("=" * 80)
        print("迁移状态更新报告")
        print("=" * 80)
        print()
        print(f"📊 标准Protobuf消息总数: {stats['total_proto_messages']}")
        print(f"✅ 数据库中已有记录: {stats['already_in_db']}")
        print(f"🆕 新增记录: {stats['new_records']}")
        print(f"🔄 更新记录: {stats['update_records']}")
        print(f"📦 涉及批次: {len(stats['batch_ids'])}")
        if stats['batch_ids']:
            print(f"   批次列表: {', '.join(sorted(map(str, stats['batch_ids'])))}")
        print()
        print("=" * 80)

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='根据标准Protobuf定义更新迁移状态')
    parser.add_argument('--dry-run', action='store_true', help='试运行，不实际更新数据库')
    parser.add_argument('--execute', action='store_true', help='执行更新')
    
    args = parser.parse_args()
    
    if not args.dry_run and not args.execute:
        parser.print_help()
        return
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    updater = MigrationStatusUpdater(db_path)
    
    stats = updater.update_migration_status(dry_run=args.dry_run)
    updater.print_update_report(stats)
    
    if args.dry_run:
        print()
        print("⚠️  这是试运行，数据库未实际更新")
        print("   使用 --execute 参数执行实际更新")
    elif args.execute:
        print()
        print("✅ 数据库已更新")

if __name__ == '__main__':
    main()
