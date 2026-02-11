#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
JProtobuf到标准Protobuf消息映射查询工具
提供命令行接口查询消息映射关系
"""

import sqlite3
import sys
import argparse
from pathlib import Path

class JProtobufProtoMappingQuery:
    """JProtobuf到标准Protobuf消息映射查询器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.conn = sqlite3.connect(db_path)
        self.conn.row_factory = sqlite3.Row
    
    def __del__(self):
        if hasattr(self, 'conn') and self.conn:
            self.conn.close()
    
    def query_by_jprotobuf_name(self, name: str):
        """根据JProtobuf消息名查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT * FROM jprotobuf_proto_mappings
            WHERE jprotobuf_message_name LIKE ?
            ORDER BY jprotobuf_message_name
        ''', (f'%{name}%',))
        
        results = cursor.fetchall()
        self._print_results(results, f"JProtobuf消息名包含 '{name}'")
    
    def query_by_proto_name(self, name: str):
        """根据标准Protobuf消息名查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT * FROM jprotobuf_proto_mappings
            WHERE proto_message_name LIKE ?
            ORDER BY proto_message_name
        ''', (f'%{name}%',))
        
        results = cursor.fetchall()
        self._print_results(results, f"标准Protobuf消息名包含 '{name}'")
    
    def query_by_module_id(self, module_id: int):
        """根据ModuleID查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT * FROM jprotobuf_proto_mappings
            WHERE module_id = ?
            ORDER BY jprotobuf_message_name
        ''', (module_id,))
        
        results = cursor.fetchall()
        self._print_results(results, f"ModuleID = {module_id}")
    
    def query_by_batch(self, batch_id: int):
        """根据批次ID查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT * FROM jprotobuf_proto_mappings
            WHERE batch_id = ?
            ORDER BY jprotobuf_message_name
        ''', (batch_id,))
        
        results = cursor.fetchall()
        self._print_results(results, f"批次ID = {batch_id}")
    
    def query_by_message_type(self, message_type: str):
        """根据消息类型查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT * FROM jprotobuf_proto_mappings
            WHERE message_type = ?
            ORDER BY jprotobuf_message_name
        ''', (message_type,))
        
        results = cursor.fetchall()
        self._print_results(results, f"消息类型 = {message_type}")
    
    def query_migrated(self, is_migrated: bool):
        """根据迁移状态查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT * FROM jprotobuf_proto_mappings
            WHERE is_migrated = ?
            ORDER BY jprotobuf_message_name
        ''', (1 if is_migrated else 0,))
        
        status = "已迁移" if is_migrated else "未迁移"
        results = cursor.fetchall()
        self._print_results(results, f"迁移状态 = {status}")
    
    def list_all(self, limit: int = 100):
        """列出所有映射"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT * FROM jprotobuf_proto_mappings
            ORDER BY jprotobuf_message_name
            LIMIT ?
        ''', (limit,))
        
        results = cursor.fetchall()
        self._print_results(results, f"所有消息 (前{limit}条)")
    
    def show_statistics(self):
        """显示统计信息"""
        cursor = self.conn.cursor()
        
        print("\n📊 JProtobuf到标准Protobuf消息映射统计")
        print("=" * 80)
        
        # 总体统计
        cursor.execute('SELECT COUNT(*) FROM jprotobuf_proto_mappings')
        total = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(*) FROM jprotobuf_proto_mappings WHERE is_migrated = 1')
        migrated = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(*) FROM jprotobuf_proto_mappings WHERE is_migrated = 0')
        not_migrated = cursor.fetchone()[0]
        
        print(f"\n总体统计:")
        print(f"  📊 总计: {total}")
        print(f"  ✅ 已迁移: {migrated} ({migrated/total*100:.2f}%)")
        print(f"  ❌ 未迁移: {not_migrated} ({not_migrated/total*100:.2f}%)")
        
        # 按消息类型统计
        cursor.execute('''
            SELECT message_type, COUNT(*) as count,
                   SUM(CASE WHEN is_migrated = 1 THEN 1 ELSE 0 END) as migrated_count
            FROM jprotobuf_proto_mappings
            GROUP BY message_type
            ORDER BY count DESC
        ''')
        
        print("\n按消息类型统计:")
        for row in cursor.fetchall():
            msg_type = row[0]
            count = row[1]
            migrated_count = row[2]
            percentage = (migrated_count / count * 100) if count > 0 else 0
            print(f"  {msg_type}: {count} (已迁移: {migrated_count}, {percentage:.2f}%)")
        
        # 按批次统计
        cursor.execute('''
            SELECT batch_id, COUNT(*) as count,
                   SUM(CASE WHEN is_migrated = 1 THEN 1 ELSE 0 END) as migrated_count
            FROM jprotobuf_proto_mappings
            WHERE batch_id IS NOT NULL
            GROUP BY batch_id
            ORDER BY batch_id
        ''')
        
        print("\n按批次统计:")
        for row in cursor.fetchall():
            batch_id = row[0]
            count = row[1]
            migrated_count = row[2]
            percentage = (migrated_count / count * 100) if count > 0 else 0
            print(f"  批次{batch_id}: {count} (已迁移: {migrated_count}, {percentage:.2f}%)")
    
    def _print_results(self, results, title: str):
        """打印查询结果"""
        print(f"\n🔍 查询结果: {title}")
        print("=" * 100)
        
        if not results:
            print("❌ 未找到匹配的消息")
            return
        
        print(f"找到 {len(results)} 条记录\n")
        
        for i, row in enumerate(results, 1):
            status_icon = "✅" if row['is_migrated'] else "❌"
            
            print(f"【{i}】{status_icon} {row['jprotobuf_message_name']}")
            print(f"    JProtobuf文件: {row['jprotobuf_file_path']}")
            print(f"    ModuleID: {row['module_id']}, 消息类型: {row['message_type']}")
            
            if row['is_migrated']:
                print(f"    → 标准Protobuf消息: {row['proto_message_name']}")
                print(f"    → Proto文件: {row['proto_file_path']}")
            else:
                print(f"    → 未找到对应的标准Protobuf消息")
            
            print()

def main():
    """主函数"""
    parser = argparse.ArgumentParser(
        description='JProtobuf到标准Protobuf消息映射查询工具',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
示例:
  # 查询JProtobuf消息名
  python query_jprotobuf_proto_mappings.py --jprotobuf REQ_LOGIN
  
  # 查询标准Protobuf消息名
  python query_jprotobuf_proto_mappings.py --proto LoginRequest
  
  # 查询特定ModuleID
  python query_jprotobuf_proto_mappings.py --module 10000
  
  # 查询特定批次
  python query_jprotobuf_proto_mappings.py --batch 1
  
  # 查询消息类型
  python query_jprotobuf_proto_mappings.py --type REQ
  
  # 查询已迁移的消息
  python query_jprotobuf_proto_mappings.py --migrated
  
  # 查询未迁移的消息
  python query_jprotobuf_proto_mappings.py --not-migrated
  
  # 列出所有
  python query_jprotobuf_proto_mappings.py --all
  
  # 显示统计
  python query_jprotobuf_proto_mappings.py --stats
        """
    )
    
    parser.add_argument('--jprotobuf', type=str, help='根据JProtobuf消息名查询')
    parser.add_argument('--proto', type=str, help='根据标准Protobuf消息名查询')
    parser.add_argument('--module', type=int, help='根据ModuleID查询')
    parser.add_argument('--batch', type=int, help='根据批次ID查询')
    parser.add_argument('--type', type=str, choices=['REQ', 'RES', 'PT', 'NOTIFY'],
                       help='根据消息类型查询')
    parser.add_argument('--migrated', action='store_true', help='查询已迁移的消息')
    parser.add_argument('--not-migrated', action='store_true', help='查询未迁移的消息')
    parser.add_argument('--all', action='store_true', help='列出所有映射')
    parser.add_argument('--limit', type=int, default=100, help='列出记录的数量限制')
    parser.add_argument('--stats', action='store_true', help='显示统计信息')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    query = JProtobufProtoMappingQuery(db_path)
    
    if args.jprotobuf:
        query.query_by_jprotobuf_name(args.jprotobuf)
    elif args.proto:
        query.query_by_proto_name(args.proto)
    elif args.module:
        query.query_by_module_id(args.module)
    elif args.batch:
        query.query_by_batch(args.batch)
    elif args.type:
        query.query_by_message_type(args.type)
    elif args.migrated:
        query.query_migrated(True)
    elif args.not_migrated:
        query.query_migrated(False)
    elif args.all:
        query.list_all(args.limit)
    elif args.stats:
        query.show_statistics()
    else:
        parser.print_help()
        print("\n💡 提示: 使用 --stats 查看总体统计")

if __name__ == '__main__':
    main()
