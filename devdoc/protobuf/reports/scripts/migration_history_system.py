#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移历史记录系统
记录和管理JProtobuf到标准Protobuf的迁移历史
"""

import sqlite3
import re
from pathlib import Path
from typing import Dict, List, Optional
from datetime import datetime

class MigrationHistorySystem:
    """迁移历史记录系统"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def import_batch_from_docs(self, batch_dir: Path) -> Optional[int]:
        """从批次文档导入迁移记录"""
        batch_match = re.search(r'batch_(\d+)', batch_dir.name)
        if not batch_match:
            return None
        
        batch_number = int(batch_match.group(1))
        
        # 检查批次计划文件
        plan_file = batch_dir / '01_迁移计划.md'
        if not plan_file.exists():
            return None
        
        # 读取批次计划
        with open(plan_file, 'r', encoding='utf-8') as f:
            plan_content = f.read()
        
        # 提取批次信息
        batch_name = None
        description = None
        
        # 尝试提取模块名称
        module_match = re.search(r'模块名称[:：]\s*([^\n]+)', plan_content)
        if module_match:
            batch_name = module_match.group(1).strip()
        
        # 检查批次结果文件
        result_file = batch_dir / '02_迁移结果.md'
        if result_file.exists():
            with open(result_file, 'r', encoding='utf-8') as f:
                result_content = f.read()
            
            # 提取迁移状态
            status_match = re.search(r'迁移状态[:：]\s*([^\n]+)', result_content)
            if status_match:
                status_text = status_match.group(1).strip()
                if '完成' in status_text or '✅' in status_text:
                    status = 'completed'
                elif '进行中' in status_text or '⏳' in status_text:
                    status = 'in_progress'
                elif '失败' in status_text or '❌' in status_text:
                    status = 'failed'
                else:
                    status = 'pending'
            else:
                status = 'completed'
            
            # 提取迁移日期
            date_match = re.search(r'迁移日期[:：]\s*([^\n]+)', result_content)
            if date_match:
                try:
                    migration_date = datetime.strptime(date_match.group(1).strip(), '%Y-%m-%d').isoformat()
                except:
                    migration_date = datetime.now().isoformat()
            else:
                migration_date = datetime.now().isoformat()
        else:
            status = 'pending'
            migration_date = datetime.now().isoformat()
        
        # 保存批次信息
        conn = self._connect()
        cursor = conn.cursor()
        
        # 检查批次是否已存在
        cursor.execute('''
            SELECT id FROM migration_batches
            WHERE batch_number = ?
        ''', (batch_number,))
        
        existing = cursor.fetchone()
        
        if existing:
            batch_id = existing[0]
            # 更新
            cursor.execute('''
                UPDATE migration_batches
                SET batch_name = ?, description = ?, status = ?,
                    end_time = ?, updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
            ''', (batch_name, description, status, migration_date, batch_id))
        else:
            # 插入
            cursor.execute('''
                INSERT INTO migration_batches
                (batch_number, batch_name, description, status, start_time, end_time)
                VALUES (?, ?, ?, ?, ?, ?)
            ''', (batch_number, batch_name, description, status, migration_date, migration_date))
            batch_id = cursor.lastrowid
        
        # 提取迁移的消息
        migrated_messages = self._extract_migrated_messages(result_file if result_file.exists() else plan_file)
        
        # 保存迁移记录
        for jprotobuf_name in migrated_messages:
            # 获取JProtobuf消息ID
            cursor.execute('''
                SELECT id FROM jprotobuf_messages
                WHERE message_name = ?
            ''', (jprotobuf_name,))
            
            jprotobuf_result = cursor.fetchone()
            if not jprotobuf_result:
                continue
            
            jprotobuf_id = jprotobuf_result[0]
            
            # 查找对应的Proto消息ID
            cursor.execute('''
                SELECT proto_message_id FROM message_mappings
                WHERE jprotobuf_message_id = ?
            ''', (jprotobuf_id,))
            
            mapping_result = cursor.fetchone()
            proto_id = mapping_result[0] if mapping_result else None
            
            # 检查迁移记录是否已存在
            cursor.execute('''
                SELECT id FROM migration_records
                WHERE batch_id = ? AND jprotobuf_message_id = ?
            ''', (batch_id, jprotobuf_id))
            
            record_existing = cursor.fetchone()
            
            if not record_existing:
                # 插入迁移记录
                cursor.execute('''
                    INSERT INTO migration_records
                    (batch_id, jprotobuf_message_id, proto_message_id, migration_status)
                    VALUES (?, ?, ?, ?)
                ''', (batch_id, jprotobuf_id, proto_id, 'migrated' if proto_id else 'pending'))
        
        # 添加历史记录
        cursor.execute('''
            INSERT INTO migration_history
            (batch_id, action_type, action_description, performed_by)
            VALUES (?, ?, ?, ?)
        ''', (batch_id, 'import', f'Imported batch {batch_number} from documents', 'system'))
        
        # 更新批次统计
        cursor.execute('''
            UPDATE migration_batches
            SET jprotobuf_count = (
                SELECT COUNT(*) FROM migration_records
                WHERE batch_id = ?
            ),
            proto_count = (
                SELECT COUNT(*) FROM migration_records
                WHERE batch_id = ? AND proto_message_id IS NOT NULL
            )
            WHERE id = ?
        ''', (batch_id, batch_id, batch_id))
        
        conn.commit()
        conn.close()
        
        return batch_id
    
    def _extract_migrated_messages(self, doc_file: Path) -> List[str]:
        """从文档中提取迁移的消息"""
        with open(doc_file, 'r', encoding='utf-8') as f:
            content = f.read()
        
        messages = []
        
        # 从表格中提取消息名
        table_pattern = r'\|\s*([A-Z_]+\.java)\s*\|'
        for match in re.finditer(table_pattern, content):
            message_name = match.group(1).replace('.java', '')
            if message_name.startswith(('REQ_', 'RES_', 'PT_')):
                messages.append(message_name)
        
        return messages
    
    def import_all_batches(self):
        """导入所有批次"""
        print("🔍 导入所有批次...")
        print()
        
        batch_dirs = sorted(self.batch_docs_dir.glob('batch_*'))
        total_batches = len(batch_dirs)
        
        imported = 0
        updated = 0
        skipped = 0
        
        for i, batch_dir in enumerate(batch_dirs, 1):
            print(f"  处理批次: {batch_dir.name} ({i}/{total_batches})")
            
            batch_id = self.import_batch_from_docs(batch_dir)
            
            if batch_id:
                imported += 1
            else:
                skipped += 1
        
        print()
        print(f"  导入: {imported} 个批次")
        print(f"  跳过: {skipped} 个批次")
        print()
        
        print("✅ 批次导入完成！")
        print()
    
    def get_migration_history(self, limit: int = 50) -> List[Dict]:
        """获取迁移历史"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT 
                mh.id,
                mb.batch_number,
                mb.batch_name,
                mh.action_type,
                mh.action_description,
                mh.performed_by,
                mh.created_at
            FROM migration_history mh
            LEFT JOIN migration_batches mb ON mh.batch_id = mb.id
            ORDER BY mh.created_at DESC
            LIMIT ?
        ''', (limit,))
        
        history = []
        for row in cursor.fetchall():
            history.append({
                'id': row[0],
                'batch_number': row[1],
                'batch_name': row[2],
                'action_type': row[3],
                'action_description': row[4],
                'performed_by': row[5],
                'created_at': row[6]
            })
        
        conn.close()
        
        return history
    
    def get_message_migration_history(self, message_name: str) -> List[Dict]:
        """获取消息的迁移历史"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT 
                mr.id,
                mb.batch_number,
                mb.batch_name,
                jm.message_name as jprotobuf_name,
                pm.message_name as proto_name,
                mr.migration_status,
                mr.migration_reason,
                mr.created_at
            FROM migration_records mr
            LEFT JOIN migration_batches mb ON mr.batch_id = mb.id
            LEFT JOIN jprotobuf_messages jm ON mr.jprotobuf_message_id = jm.id
            LEFT JOIN proto_messages pm ON mr.proto_message_id = pm.id
            WHERE jm.message_name = ?
            ORDER BY mr.created_at DESC
        ''', (message_name,))
        
        history = []
        for row in cursor.fetchall():
            history.append({
                'id': row[0],
                'batch_number': row[1],
                'batch_name': row[2],
                'jprotobuf_name': row[3],
                'proto_name': row[4],
                'migration_status': row[5],
                'migration_reason': row[6],
                'created_at': row[7]
            })
        
        conn.close()
        
        return history

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='迁移历史记录系统')
    parser.add_argument('--import-all', action='store_true', help='导入所有批次')
    parser.add_argument('--history', action='store_true', help='查看迁移历史')
    parser.add_argument('--message', type=str, help='查看消息的迁移历史')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    system = MigrationHistorySystem(db_path)
    
    if args.import_all:
        system.import_all_batches()
    elif args.history:
        history = system.get_migration_history()
        print("📜 迁移历史:")
        print("=" * 80)
        for record in history:
            print(f"  批次: {record['batch_number']} - {record['batch_name']}")
            print(f"  操作: {record['action_type']}")
            print(f"  描述: {record['action_description']}")
            print(f"  时间: {record['created_at']}")
            print()
    elif args.message:
        history = system.get_message_migration_history(args.message)
        print(f"📜 消息 {args.message} 的迁移历史:")
        print("=" * 80)
        if history:
            for record in history:
                print(f"  批次: {record['batch_number']} - {record['batch_name']}")
                print(f"  状态: {record['migration_status']}")
                print(f"  Proto: {record['proto_name']}")
                print(f"  时间: {record['created_at']}")
                print()
        else:
            print("  未找到迁移记录")
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
