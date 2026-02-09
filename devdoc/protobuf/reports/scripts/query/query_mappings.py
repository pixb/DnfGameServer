#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
消息映射查询工具
提供命令行接口查询消息映射关系
"""

import sqlite3
import sys
import argparse
from pathlib import Path

class MessageMappingQuery:
    """消息映射查询器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.conn = sqlite3.connect(db_path)
        self.conn.row_factory = sqlite3.Row
    
    def __del__(self):
        if hasattr(self, 'conn') and self.conn:
            self.conn.close()
    
    def query_by_old_name(self, name: str):
        """根据旧消息名查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT m.*, b.batch_name, b.description
            FROM message_file_mappings m
            JOIN batches b ON m.batch_id = b.id
            WHERE m.old_message_name LIKE ?
            ORDER BY m.module_id, m.cmd_id
        ''', (f'%{name}%',))
        
        results = cursor.fetchall()
        self._print_results(results, f"旧消息名包含 '{name}'")
    
    def query_by_new_name(self, name: str):
        """根据新消息名查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT m.*, b.batch_name, b.description
            FROM message_file_mappings m
            JOIN batches b ON m.batch_id = b.id
            WHERE m.new_message_name LIKE ?
            ORDER BY m.module_id, m.cmd_id
        ''', (f'%{name}%',))
        
        results = cursor.fetchall()
        self._print_results(results, f"新消息名包含 '{name}'")
    
    def query_by_module_id(self, module_id: int):
        """根据ModuleID查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT m.*, b.batch_name, b.description
            FROM message_file_mappings m
            JOIN batches b ON m.batch_id = b.id
            WHERE m.module_id = ?
            ORDER BY m.cmd_id
        ''', (module_id,))
        
        results = cursor.fetchall()
        self._print_results(results, f"ModuleID = {module_id}")
    
    def query_by_batch(self, batch_name: str):
        """根据批次查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT m.*, b.batch_name, b.description
            FROM message_file_mappings m
            JOIN batches b ON m.batch_id = b.id
            WHERE b.batch_name = ?
            ORDER BY m.module_id, m.cmd_id
        ''', (batch_name,))
        
        results = cursor.fetchall()
        self._print_results(results, f"批次 = {batch_name}")
    
    def query_by_status(self, status: str):
        """根据实现状态查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT m.*, b.batch_name, b.description
            FROM message_file_mappings m
            JOIN batches b ON m.batch_id = b.id
            WHERE m.implementation_status = ?
            ORDER BY m.module_id, m.cmd_id
        ''', (status,))
        
        results = cursor.fetchall()
        status_map = {
            'complete': '完整实现',
            'simplified': '简化实现',
            'missing': '缺失实现'
        }
        self._print_results(results, f"实现状态 = {status_map.get(status, status)}")
    
    def list_all(self):
        """列出所有映射"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT m.*, b.batch_name, b.description
            FROM message_file_mappings m
            JOIN batches b ON m.batch_id = b.id
            ORDER BY m.module_id, m.cmd_id
        ''')
        
        results = cursor.fetchall()
        self._print_results(results, "所有消息")
    
    def show_statistics(self):
        """显示统计信息"""
        cursor = self.conn.cursor()
        
        print("\n📊 消息映射统计")
        print("=" * 70)
        
        # 总体统计
        cursor.execute('''
            SELECT implementation_status, COUNT(*) as count
            FROM message_file_mappings
            GROUP BY implementation_status
        ''')
        
        print("\n实现状态分布:")
        status_map = {
            'complete': '✅ 完整实现',
            'simplified': '⚠️ 简化实现',
            'missing': '❌ 缺失实现'
        }
        
        for row in cursor.fetchall():
            status = status_map.get(row[0], row[0])
            print(f"  {status}: {row[1]} 个")
        
        # 按批次统计
        cursor.execute('''
            SELECT b.batch_name, COUNT(*) as count
            FROM message_file_mappings m
            JOIN batches b ON m.batch_id = b.id
            GROUP BY b.batch_name
            ORDER BY b.batch_number
        ''')
        
        print("\n按批次分布:")
        for row in cursor.fetchall():
            print(f"  {row[0]}: {row[1]} 个")
        
        # 按模块统计
        cursor.execute('''
            SELECT module_id, COUNT(*) as count
            FROM message_file_mappings
            GROUP BY module_id
            ORDER BY module_id
        ''')
        
        print("\n按ModuleID分布 (前10个):")
        for i, row in enumerate(cursor.fetchall()[:10]):
            print(f"  Module {row[0]}: {row[1]} 个")
    
    def _print_results(self, results, title: str):
        """打印查询结果"""
        print(f"\n🔍 查询结果: {title}")
        print("=" * 80)
        
        if not results:
            print("❌ 未找到匹配的消息")
            return
        
        print(f"找到 {len(results)} 条记录\n")
        
        for i, row in enumerate(results, 1):
            print(f"【{i}】 {row['old_message_name']} → {row['new_message_name']}")
            print(f"    ModuleID: {row['module_id']}, CMD: {row['cmd_id']}")
            print(f"    批次: {row['batch_name']} ({row['description']})")
            print(f"    类型: {row['old_message_type']}")
            
            # 状态图标
            status_icon = {
                'complete': '✅',
                'simplified': '⚠️',
                'missing': '❌'
            }.get(row['implementation_status'], '❓')
            
            print(f"    实现状态: {status_icon} {row['implementation_status']}")
            print(f"    原文件: {row['old_java_file']}")
            print(f"    Proto: {row['new_proto_file']}")
            print(f"    Java生成: {row['new_java_file']}")
            print(f"    Go生成: {row['new_go_file']}")
            print()

def main():
    """主函数"""
    parser = argparse.ArgumentParser(
        description='消息映射查询工具',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
示例:
  # 查询旧消息名
  python query_mappings.py --old REQ_LOGIN
  
  # 查询新消息名
  python query_mappings.py --new LoginRequest
  
  # 查询特定ModuleID
  python query_mappings.py --module 10000
  
  # 查询特定批次
  python query_mappings.py --batch batch_01
  
  # 查询实现状态
  python query_mappings.py --status complete
  
  # 列出所有
  python query_mappings.py --all
  
  # 显示统计
  python query_mappings.py --stats
        """
    )
    
    parser.add_argument('--old', type=str, help='根据旧消息名查询')
    parser.add_argument('--new', type=str, help='根据新消息名查询')
    parser.add_argument('--module', type=int, help='根据ModuleID查询')
    parser.add_argument('--batch', type=str, help='根据批次查询')
    parser.add_argument('--status', type=str, choices=['complete', 'simplified', 'missing'],
                       help='根据实现状态查询')
    parser.add_argument('--all', action='store_true', help='列出所有映射')
    parser.add_argument('--stats', action='store_true', help='显示统计信息')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/migration_progress.db'
    query = MessageMappingQuery(db_path)
    
    if args.old:
        query.query_by_old_name(args.old)
    elif args.new:
        query.query_by_new_name(args.new)
    elif args.module:
        query.query_by_module_id(args.module)
    elif args.batch:
        query.query_by_batch(args.batch)
    elif args.status:
        query.query_by_status(args.status)
    elif args.all:
        query.list_all()
    elif args.stats:
        query.show_statistics()
    else:
        parser.print_help()
        print("\n💡 提示: 使用 --stats 查看总体统计")

if __name__ == '__main__':
    main()
