#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移状态追踪工具
追踪和管理JProtobuf到标准Protobuf的迁移状态
"""

import sqlite3
from pathlib import Path
from typing import Dict, List, Optional
from datetime import datetime

class MigrationStatusTracker:
    """迁移状态追踪器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def get_overall_status(self) -> Dict:
        """获取整体迁移状态"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # JProtobuf消息总数
        cursor.execute('SELECT COUNT(*) FROM jprotobuf_messages')
        total_jprotobuf = cursor.fetchone()[0]
        
        # 标准Protobuf消息总数
        cursor.execute('SELECT COUNT(*) FROM proto_messages')
        total_proto = cursor.fetchone()[0]
        
        # 已映射的消息数
        cursor.execute('SELECT COUNT(DISTINCT jprotobuf_message_id) FROM message_mappings')
        mapped_count = cursor.fetchone()[0]
        
        # 迁移记录数
        cursor.execute('SELECT COUNT(*) FROM migration_records')
        total_records = cursor.fetchone()[0]
        
        # 已迁移的消息数
        cursor.execute('''
            SELECT COUNT(DISTINCT jprotobuf_message_id) 
            FROM migration_records 
            WHERE migration_status = 'migrated'
        ''')
        migrated_count = cursor.fetchone()[0]
        
        # 批次统计
        cursor.execute('SELECT COUNT(*) FROM migration_batches')
        total_batches = cursor.fetchone()[0]
        
        cursor.execute('''
            SELECT COUNT(*) FROM migration_batches 
            WHERE status = 'completed'
        ''')
        completed_batches = cursor.fetchone()[0]
        
        conn.close()
        
        return {
            'total_jprotobuf': total_jprotobuf,
            'total_proto': total_proto,
            'mapped_count': mapped_count,
            'total_records': total_records,
            'migrated_count': migrated_count,
            'total_batches': total_batches,
            'completed_batches': completed_batches,
            'migration_rate': round(migrated_count / total_jprotobuf * 100, 2) if total_jprotobuf > 0 else 0,
            'mapping_rate': round(mapped_count / total_jprotobuf * 100, 2) if total_jprotobuf > 0 else 0
        }
    
    def get_message_status(self, message_name: str) -> Optional[Dict]:
        """获取消息的迁移状态"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 查找JProtobuf消息
        cursor.execute('''
            SELECT id, message_name, message_type, file_path, module_id, cmd
            FROM jprotobuf_messages
            WHERE message_name = ?
        ''', (message_name,))
        
        jprotobuf_result = cursor.fetchone()
        if not jprotobuf_result:
            conn.close()
            return None
        
        jprotobuf_id = jprotobuf_result[0]
        
        # 查找映射
        cursor.execute('''
            SELECT pm.message_name, pm.file_path, mm.mapping_type, mm.is_verified
            FROM message_mappings mm
            LEFT JOIN proto_messages pm ON mm.proto_message_id = pm.id
            WHERE mm.jprotobuf_message_id = ?
        ''', (jprotobuf_id,))
        
        mapping_result = cursor.fetchone()
        
        # 查找迁移记录
        cursor.execute('''
            SELECT mb.batch_number, mb.batch_name, mr.migration_status, mr.created_at
            FROM migration_records mr
            LEFT JOIN migration_batches mb ON mr.batch_id = mb.id
            WHERE mr.jprotobuf_message_id = ?
            ORDER BY mr.created_at DESC
            LIMIT 1
        ''', (jprotobuf_id,))
        
        record_result = cursor.fetchone()
        
        conn.close()
        
        return {
            'jprotobuf_name': jprotobuf_result[1],
            'jprotobuf_type': jprotobuf_result[2],
            'jprotobuf_path': jprotobuf_result[3],
            'module_id': jprotobuf_result[4],
            'cmd': jprotobuf_result[5],
            'proto_name': mapping_result[0] if mapping_result else None,
            'proto_path': mapping_result[1] if mapping_result else None,
            'mapping_type': mapping_result[2] if mapping_result else None,
            'is_verified': bool(mapping_result[3]) if mapping_result else False,
            'batch_number': record_result[0] if record_result else None,
            'batch_name': record_result[1] if record_result else None,
            'migration_status': record_result[2] if record_result else None,
            'migration_date': record_result[3] if record_result else None
        }
    
    def get_batch_status(self, batch_number: int) -> Optional[Dict]:
        """获取批次的迁移状态"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT id, batch_number, batch_name, description, status, 
                   start_time, end_time, jprotobuf_count, proto_count
            FROM migration_batches
            WHERE batch_number = ?
        ''', (batch_number,))
        
        result = cursor.fetchone()
        if not result:
            conn.close()
            return None
        
        batch_id = result[0]
        
        # 获取批次中的消息
        cursor.execute('''
            SELECT jm.message_name, pm.message_name, mr.migration_status
            FROM migration_records mr
            LEFT JOIN jprotobuf_messages jm ON mr.jprotobuf_message_id = jm.id
            LEFT JOIN proto_messages pm ON mr.proto_message_id = pm.id
            WHERE mr.batch_id = ?
            ORDER BY jm.message_name
        ''', (batch_id,))
        
        messages = []
        for row in cursor.fetchall():
            messages.append({
                'jprotobuf_name': row[0],
                'proto_name': row[1],
                'status': row[2]
            })
        
        conn.close()
        
        return {
            'batch_number': result[1],
            'batch_name': result[2],
            'description': result[3],
            'status': result[4],
            'start_time': result[5],
            'end_time': result[6],
            'jprotobuf_count': result[7],
            'proto_count': result[8],
            'messages': messages
        }
    
    def get_unmapped_messages(self, limit: int = 100) -> List[Dict]:
        """获取未映射的消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jm.id, jm.message_name, jm.message_type, jm.file_path
            FROM jprotobuf_messages jm
            LEFT JOIN message_mappings mm ON jm.id = mm.jprotobuf_message_id
            WHERE mm.id IS NULL
            ORDER BY jm.message_name
            LIMIT ?
        ''', (limit,))
        
        messages = []
        for row in cursor.fetchall():
            messages.append({
                'id': row[0],
                'message_name': row[1],
                'message_type': row[2],
                'file_path': row[3]
            })
        
        conn.close()
        
        return messages
    
    def get_unmigrated_messages(self, limit: int = 100) -> List[Dict]:
        """获取未迁移的消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT DISTINCT jm.id, jm.message_name, jm.message_type, jm.file_path
            FROM jprotobuf_messages jm
            LEFT JOIN migration_records mr ON jm.id = mr.jprotobuf_message_id
            WHERE mr.id IS NULL OR mr.migration_status != 'migrated'
            ORDER BY jm.message_name
            LIMIT ?
        ''', (limit,))
        
        messages = []
        for row in cursor.fetchall():
            messages.append({
                'id': row[0],
                'message_name': row[1],
                'message_type': row[2],
                'file_path': row[3]
            })
        
        conn.close()
        
        return messages
    
    def get_migration_summary_by_type(self) -> Dict[str, Dict]:
        """按类型获取迁移摘要"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT message_type, COUNT(*) 
            FROM jprotobuf_messages 
            GROUP BY message_type
        ''')
        
        type_stats = {}
        for row in cursor.fetchall():
            message_type = row[0]
            total = row[1]
            
            # 获取已映射数量
            cursor.execute('''
                SELECT COUNT(DISTINCT jm.id)
                FROM jprotobuf_messages jm
                LEFT JOIN message_mappings mm ON jm.id = mm.jprotobuf_message_id
                WHERE jm.message_type = ? AND mm.id IS NOT NULL
            ''', (message_type,))
            
            mapped = cursor.fetchone()[0]
            
            # 获取已迁移数量
            cursor.execute('''
                SELECT COUNT(DISTINCT jm.id)
                FROM jprotobuf_messages jm
                LEFT JOIN migration_records mr ON jm.id = mr.jprotobuf_message_id
                WHERE jm.message_type = ? AND mr.migration_status = 'migrated'
            ''', (message_type,))
            
            migrated = cursor.fetchone()[0]
            
            type_stats[message_type] = {
                'total': total,
                'mapped': mapped,
                'migrated': migrated,
                'mapping_rate': round(mapped / total * 100, 2) if total > 0 else 0,
                'migration_rate': round(migrated / total * 100, 2) if total > 0 else 0
            }
        
        conn.close()
        
        return type_stats
    
    def print_overall_status(self):
        """打印整体迁移状态"""
        status = self.get_overall_status()
        
        print("📊 整体迁移状态")
        print("=" * 80)
        print()
        print(f"JProtobuf消息总数: {status['total_jprotobuf']}")
        print(f"标准Protobuf消息总数: {status['total_proto']}")
        print(f"已映射消息数: {status['mapped_count']} ({status['mapping_rate']}%)")
        print(f"已迁移消息数: {status['migrated_count']} ({status['migration_rate']}%)")
        print(f"迁移记录总数: {status['total_records']}")
        print(f"批次总数: {status['total_batches']}")
        print(f"已完成批次: {status['completed_batches']}")
        print()
    
    def print_message_status(self, message_name: str):
        """打印消息的迁移状态"""
        status = self.get_message_status(message_name)
        
        if not status:
            print(f"❌ 未找到消息: {message_name}")
            return
        
        print(f"📊 消息 {message_name} 的迁移状态")
        print("=" * 80)
        print()
        print(f"JProtobuf:")
        print(f"  名称: {status['jprotobuf_name']}")
        print(f"  类型: {status['jprotobuf_type']}")
        print(f"  文件: {status['jprotobuf_path']}")
        if status['module_id']:
            print(f"  ModuleID: {status['module_id']}")
        if status['cmd']:
            print(f"  CMD: {status['cmd']}")
        print()
        
        if status['proto_name']:
            print(f"标准Protobuf:")
            print(f"  名称: {status['proto_name']}")
            print(f"  文件: {status['proto_path']}")
            print(f"  映射类型: {status['mapping_type']}")
            print(f"  已验证: {'✅' if status['is_verified'] else '❌'}")
            print()
        
        if status['batch_number']:
            print(f"迁移信息:")
            print(f"  批次: {status['batch_number']} - {status['batch_name']}")
            print(f"  状态: {status['migration_status']}")
            print(f"  日期: {status['migration_date']}")
            print()
        else:
            print("迁移信息: 未迁移")
            print()

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='迁移状态追踪工具')
    parser.add_argument('--overall', action='store_true', help='查看整体迁移状态')
    parser.add_argument('--message', type=str, help='查看消息的迁移状态')
    parser.add_argument('--batch', type=int, help='查看批次的迁移状态')
    parser.add_argument('--unmapped', action='store_true', help='查看未映射的消息')
    parser.add_argument('--unmigrated', action='store_true', help='查看未迁移的消息')
    parser.add_argument('--by-type', action='store_true', help='按类型查看迁移摘要')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    tracker = MigrationStatusTracker(db_path)
    
    if args.overall:
        tracker.print_overall_status()
    elif args.message:
        tracker.print_message_status(args.message)
    elif args.batch:
        status = tracker.get_batch_status(args.batch)
        if status:
            print(f"📊 批次 {args.batch} 的迁移状态")
            print("=" * 80)
            print()
            print(f"批次名称: {status['batch_name']}")
            print(f"状态: {status['status']}")
            print(f"开始时间: {status['start_time']}")
            print(f"结束时间: {status['end_time']}")
            print(f"JProtobuf消息数: {status['jprotobuf_count']}")
            print(f"标准Protobuf消息数: {status['proto_count']}")
            print()
            print("消息列表:")
            for msg in status['messages']:
                print(f"  {msg['jprotobuf_name']} -> {msg['proto_name']} ({msg['status']})")
            print()
        else:
            print(f"❌ 未找到批次: {args.batch}")
    elif args.unmapped:
        messages = tracker.get_unmapped_messages()
        print(f"📊 未映射的消息 ({len(messages)} 个)")
        print("=" * 80)
        for msg in messages:
            print(f"  {msg['message_name']} ({msg['message_type']})")
        print()
    elif args.unmigrated:
        messages = tracker.get_unmigrated_messages()
        print(f"📊 未迁移的消息 ({len(messages)} 个)")
        print("=" * 80)
        for msg in messages:
            print(f"  {msg['message_name']} ({msg['message_type']})")
        print()
    elif args.by_type:
        type_stats = tracker.get_migration_summary_by_type()
        print("📊 按类型的迁移摘要")
        print("=" * 80)
        print()
        for msg_type, stats in type_stats.items():
            print(f"{msg_type}:")
            print(f"  总数: {stats['total']}")
            print(f"  已映射: {stats['mapped']} ({stats['mapping_rate']}%)")
            print(f"  已迁移: {stats['migrated']} ({stats['migration_rate']}%)")
            print()
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
