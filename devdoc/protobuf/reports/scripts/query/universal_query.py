#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
通用查询工具
提供灵活的命令行接口查询消息映射关系
"""

import sqlite3
import sys
import argparse
import json
from pathlib import Path
from typing import List, Dict, Optional

class UniversalQuery:
    """通用查询器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.conn = sqlite3.connect(db_path)
        self.conn.row_factory = sqlite3.Row
    
    def __del__(self):
        if hasattr(self, 'conn') and self.conn:
            self.conn.close()
    
    def query_by_jprotobuf_name(self, name: str, exact: bool = False) -> List[Dict]:
        """根据JProtobuf消息名查询"""
        cursor = self.conn.cursor()
        if exact:
            cursor.execute('''
                SELECT 
                    jm.message_name as jprotobuf_message_name,
                    jm.file_path as jprotobuf_file_path,
                    jm.module_id,
                    jm.message_type,
                    jm.field_count as jprotobuf_field_count,
                    pm.message_name as proto_message_name,
                    pm.file_path as proto_file_path,
                    pm.package_name,
                    pm.field_count as proto_field_count,
                    mm.mapping_type,
                    mm.mapping_confidence,
                    mm.is_verified,
                    mb.batch_number,
                    mb.batch_name,
                    mr.migration_status
                FROM message_mappings mm
                JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
                LEFT JOIN proto_messages pm ON mm.proto_message_id = pm.id
                LEFT JOIN migration_batches mb ON mb.batch_number = 1
                LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
                WHERE jm.message_name = ?
                ORDER BY jm.message_name
            ''', (name,))
        else:
            cursor.execute('''
                SELECT 
                    jm.message_name as jprotobuf_message_name,
                    jm.file_path as jprotobuf_file_path,
                    jm.module_id,
                    jm.message_type,
                    jm.field_count as jprotobuf_field_count,
                    pm.message_name as proto_message_name,
                    pm.file_path as proto_file_path,
                    pm.package_name,
                    pm.field_count as proto_field_count,
                    mm.mapping_type,
                    mm.mapping_confidence,
                    mm.is_verified,
                    mb.batch_number,
                    mb.batch_name,
                    mr.migration_status
                FROM message_mappings mm
                JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
                LEFT JOIN proto_messages pm ON mm.proto_message_id = pm.id
                LEFT JOIN migration_batches mb ON mb.batch_number = 1
                LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
                WHERE jm.message_name LIKE ?
                ORDER BY jm.message_name
            ''', (f'%{name}%',))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def query_by_proto_name(self, name: str, exact: bool = False) -> List[Dict]:
        """根据标准Protobuf消息名查询"""
        cursor = self.conn.cursor()
        if exact:
            cursor.execute('''
                SELECT 
                    jm.message_name as jprotobuf_message_name,
                    jm.file_path as jprotobuf_file_path,
                    jm.module_id,
                    jm.message_type,
                    jm.field_count as jprotobuf_field_count,
                    pm.message_name as proto_message_name,
                    pm.file_path as proto_file_path,
                    pm.package_name,
                    pm.field_count as proto_field_count,
                    mm.mapping_type,
                    mm.mapping_confidence,
                    mm.is_verified,
                    mb.batch_number,
                    mb.batch_name,
                    mr.migration_status
                FROM message_mappings mm
                JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
                JOIN proto_messages pm ON mm.proto_message_id = pm.id
                LEFT JOIN migration_batches mb ON mb.batch_number = 1
                LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
                WHERE pm.message_name = ?
                ORDER BY pm.message_name
            ''', (name,))
        else:
            cursor.execute('''
                SELECT 
                    jm.message_name as jprotobuf_message_name,
                    jm.file_path as jprotobuf_file_path,
                    jm.module_id,
                    jm.message_type,
                    jm.field_count as jprotobuf_field_count,
                    pm.message_name as proto_message_name,
                    pm.file_path as proto_file_path,
                    pm.package_name,
                    pm.field_count as proto_field_count,
                    mm.mapping_type,
                    mm.mapping_confidence,
                    mm.is_verified,
                    mb.batch_number,
                    mb.batch_name,
                    mr.migration_status
                FROM message_mappings mm
                JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
                JOIN proto_messages pm ON mm.proto_message_id = pm.id
                LEFT JOIN migration_batches mb ON mb.batch_number = 1
                LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
                WHERE pm.message_name LIKE ?
                ORDER BY pm.message_name
            ''', (f'%{name}%',))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def query_by_module_id(self, module_id: int) -> List[Dict]:
        """根据ModuleID查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT 
                jm.message_name as jprotobuf_message_name,
                jm.file_path as jprotobuf_file_path,
                jm.module_id,
                jm.message_type,
                jm.field_count as jprotobuf_field_count,
                pm.message_name as proto_message_name,
                pm.file_path as proto_file_path,
                pm.package_name,
                pm.field_count as proto_field_count,
                mm.mapping_type,
                mm.mapping_confidence,
                mm.is_verified,
                mb.batch_number,
                mb.batch_name,
                mr.migration_status
            FROM message_mappings mm
            JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
            LEFT JOIN proto_messages pm ON mm.proto_message_id = pm.id
            LEFT JOIN migration_batches mb ON mb.batch_number = 1
            LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
            WHERE jm.module_id = ?
            ORDER BY jm.message_name
        ''', (module_id,))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def query_by_batch(self, batch_id: int) -> List[Dict]:
        """根据批次ID查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT 
                jm.message_name as jprotobuf_message_name,
                jm.file_path as jprotobuf_file_path,
                jm.module_id,
                jm.message_type,
                jm.field_count as jprotobuf_field_count,
                pm.message_name as proto_message_name,
                pm.file_path as proto_file_path,
                pm.package_name,
                pm.field_count as proto_field_count,
                mm.mapping_type,
                mm.mapping_confidence,
                mm.is_verified,
                mb.batch_number,
                mb.batch_name,
                mr.migration_status
            FROM message_mappings mm
            JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
            JOIN proto_messages pm ON mm.proto_message_id = pm.id
            JOIN migration_batches mb ON mb.batch_number = ?
            LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
            ORDER BY jm.message_name
        ''', (batch_id,))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def query_by_message_type(self, message_type: str) -> List[Dict]:
        """根据消息类型查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT 
                jm.message_name as jprotobuf_message_name,
                jm.file_path as jprotobuf_file_path,
                jm.module_id,
                jm.message_type,
                jm.field_count as jprotobuf_field_count,
                pm.message_name as proto_message_name,
                pm.file_path as proto_file_path,
                pm.package_name,
                pm.field_count as proto_field_count,
                mm.mapping_type,
                mm.mapping_confidence,
                mm.is_verified,
                mb.batch_number,
                mb.batch_name,
                mr.migration_status
            FROM message_mappings mm
            JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
            JOIN proto_messages pm ON mm.proto_message_id = pm.id
            LEFT JOIN migration_batches mb ON mb.batch_number = 1
            LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
            WHERE jm.message_type = ?
            ORDER BY jm.message_name
        ''', (message_type,))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def query_by_status(self, status: str) -> List[Dict]:
        """根据迁移状态查询"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT 
                jm.message_name as jprotobuf_message_name,
                jm.file_path as jprotobuf_file_path,
                jm.module_id,
                jm.message_type,
                jm.field_count as jprotobuf_field_count,
                pm.message_name as proto_message_name,
                pm.file_path as proto_file_path,
                pm.package_name,
                pm.field_count as proto_field_count,
                mm.mapping_type,
                mm.mapping_confidence,
                mm.is_verified,
                mb.batch_number,
                mb.batch_name,
                mr.migration_status
            FROM message_mappings mm
            JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
            JOIN proto_messages pm ON mm.proto_message_id = pm.id
            LEFT JOIN migration_batches mb ON mb.batch_number = 1
            LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
            WHERE mr.migration_status = ?
            ORDER BY jm.message_name
        ''', (status,))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def list_all(self, limit: int = 100) -> List[Dict]:
        """列出所有映射"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT 
                jm.message_name as jprotobuf_message_name,
                jm.file_path as jprotobuf_file_path,
                jm.module_id,
                jm.message_type,
                jm.field_count as jprotobuf_field_count,
                pm.message_name as proto_message_name,
                pm.file_path as proto_file_path,
                pm.package_name,
                pm.field_count as proto_field_count,
                mm.mapping_type,
                mm.mapping_confidence,
                mm.is_verified,
                mb.batch_number,
                mb.batch_name,
                mr.migration_status
            FROM message_mappings mm
            JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
            LEFT JOIN proto_messages pm ON mm.proto_message_id = pm.id
            LEFT JOIN migration_batches mb ON mb.batch_number = 1
            LEFT JOIN migration_records mr ON mr.jprotobuf_message_id = jm.id AND mr.proto_message_id = pm.id
            ORDER BY jm.message_name
            LIMIT ?
        ''', (limit,))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def show_statistics(self) -> Dict:
        """显示统计信息"""
        cursor = self.conn.cursor()
        
        stats = {}
        
        # 总体统计
        cursor.execute('SELECT COUNT(*) FROM jprotobuf_messages')
        stats['jprotobuf_total'] = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(*) FROM proto_messages')
        stats['proto_total'] = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(*) FROM message_mappings')
        stats['mappings_total'] = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(*) FROM message_mappings WHERE is_verified = 1')
        stats['mappings_verified'] = cursor.fetchone()[0]
        
        # 按消息类型统计
        cursor.execute('''
            SELECT jm.message_type, COUNT(*) as count,
                   SUM(CASE WHEN mm.is_verified = 1 THEN 1 ELSE 0 END) as verified_count
            FROM jprotobuf_messages jm
            LEFT JOIN message_mappings mm ON mm.jprotobuf_message_id = jm.id
            GROUP BY jm.message_type
            ORDER BY count DESC
        ''')
        
        stats['by_type'] = []
        for row in cursor.fetchall():
            stats['by_type'].append({
                'type': row[0],
                'count': row[1],
                'verified': row[2],
                'percentage': (row[2] / row[1] * 100) if row[1] > 0 else 0
            })
        
        # 按批次统计
        cursor.execute('''
            SELECT mb.batch_number, mb.batch_name, COUNT(*) as count,
                   SUM(CASE WHEN mm.is_verified = 1 THEN 1 ELSE 0 END) as verified_count
            FROM message_mappings mm
            LEFT JOIN migration_batches mb ON mb.batch_number = 1
            GROUP BY mb.batch_number
            ORDER BY mb.batch_number
        ''')
        
        stats['by_batch'] = []
        for row in cursor.fetchall():
            stats['by_batch'].append({
                'batch_number': row[0],
                'batch_name': row[1],
                'count': row[2],
                'verified': row[3],
                'percentage': (row[3] / row[2] * 100) if row[2] > 0 else 0
            })
        
        return stats
    
    def print_results(self, results: List[Dict], title: str, output_format: str = 'table'):
        """打印查询结果"""
        if output_format == 'json':
            print(json.dumps(results, indent=2, ensure_ascii=False))
        elif output_format == 'csv':
            if results:
                headers = results[0].keys()
                print(','.join(headers))
                for row in results:
                    print(','.join(str(row[h]) for h in headers))
        else:
            print(f"\n🔍 查询结果: {title}")
            print("=" * 100)
            
            if not results:
                print("❌ 未找到匹配的消息")
                return
            
            print(f"找到 {len(results)} 条记录\n")
            
            for i, row in enumerate(results, 1):
                status_icon = "✅" if row.get('is_verified') else "❌"
                
                print(f"【{i}】{status_icon} {row['jprotobuf_message_name']}")
                print(f"    JProtobuf文件: {row['jprotobuf_file_path']}")
                print(f"    ModuleID: {row['module_id']}, 消息类型: {row['message_type']}")
                print(f"    字段数: {row['jprotobuf_field_count']}")
                
                if row['proto_message_name']:
                    print(f"    → 标准Protobuf消息: {row['proto_message_name']}")
                    print(f"    → Proto文件: {row['proto_file_path']}")
                    print(f"    → 包名: {row['package_name']}")
                    print(f"    → 字段数: {row['proto_field_count']}")
                    print(f"    → 映射类型: {row['mapping_type']}")
                    print(f"    → 置信度: {row['mapping_confidence']}")
                    print(f"    → 已验证: {'是' if row['is_verified'] else '否'}")
                    
                    if row['jprotobuf_field_count'] != row['proto_field_count']:
                        print(f"    ⚠️  字段数不匹配: JProtobuf({row['jprotobuf_field_count']}) != 标准 Protobuf({row['proto_field_count']})")
                else:
                    print(f"    → 未找到对应的标准Protobuf消息")
                
                if row['batch_number']:
                    print(f"    → 批次: {row['batch_number']} ({row['batch_name']})")
                
                if row['migration_status']:
                    print(f"    → 迁移状态: {row['migration_status']}")
                
                print()
    
    def print_statistics(self, stats: Dict):
        """打印统计信息"""
        print("\n📊 消息映射统计")
        print("=" * 80)
        
        print(f"\n总体统计:")
        print(f"  📊 JProtobuf消息总数: {stats['jprotobuf_total']}")
        print(f"  📊 标准Protobuf消息总数: {stats['proto_total']}")
        print(f"  🔗 映射关系总数: {stats['mappings_total']}")
        if stats['mappings_total'] > 0:
            print(f"  ✅ 已验证映射: {stats['mappings_verified']} ({stats['mappings_verified']/stats['mappings_total']*100:.2f}%)")
        else:
            print(f"  ✅ 已验证映射: 0")
        
        print("\n按消息类型统计:")
        for item in stats['by_type']:
            print(f"  {item['type']}: {item['count']} (已验证: {item['verified']}, {item['percentage']:.2f}%)")
        
        print("\n按批次统计:")
        for item in stats['by_batch']:
            print(f"  批次{item['batch_number']} ({item['batch_name']}): {item['count']} (已验证: {item['verified']}, {item['percentage']:.2f}%)")

def main():
    """主函数"""
    parser = argparse.ArgumentParser(
        description='通用查询工具',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
示例:
  # 查询JProtobuf消息名（模糊匹配）
  python universal_query.py --jprotobuf REQ_LOGIN
  
  # 查询JProtobuf消息名（精确匹配）
  python universal_query.py --jprotobuf REQ_LOGIN --exact
  
  # 查询标准Protobuf消息名
  python universal_query.py --proto RobotUserAttribute2Request
  
  # 查询特定ModuleID
  python universal_query.py --module 10000
  
  # 查询特定批次
  python universal_query.py --batch 1
  
  # 查询消息类型
  python universal_query.py --type REQ
  
  # 查询迁移状态
  python universal_query.py --status completed
  
  # 列出所有
  python universal_query.py --all
  
  # 显示统计
  python universal_query.py --stats
  
  # 输出为JSON格式
  python universal_query.py --proto RobotUserAttribute2Request --format json
  
  # 输出为CSV格式
  python universal_query.py --batch 1 --format csv
        """
    )
    
    parser.add_argument('--jprotobuf', type=str, help='根据JProtobuf消息名查询')
    parser.add_argument('--proto', type=str, help='根据标准Protobuf消息名查询')
    parser.add_argument('--module', type=int, help='根据ModuleID查询')
    parser.add_argument('--batch', type=int, help='根据批次ID查询')
    parser.add_argument('--type', type=str, choices=['REQ', 'RES', 'PT', 'NOTIFY', 'OTHER', 'ENUM'],
                       help='根据消息类型查询')
    parser.add_argument('--status', type=str, choices=['completed', 'pending', 'in_progress', 'failed'],
                       help='根据迁移状态查询')
    parser.add_argument('--all', action='store_true', help='列出所有映射')
    parser.add_argument('--limit', type=int, default=100, help='列出记录的数量限制')
    parser.add_argument('--stats', action='store_true', help='显示统计信息')
    parser.add_argument('--exact', action='store_true', help='精确匹配消息名')
    parser.add_argument('--format', type=str, choices=['table', 'json', 'csv'], default='table',
                       help='输出格式')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    query = UniversalQuery(db_path)
    
    if args.jprotobuf:
        results = query.query_by_jprotobuf_name(args.jprotobuf, args.exact)
        query.print_results(results, f"JProtobuf消息名包含 '{args.jprotobuf}'", args.format)
    elif args.proto:
        results = query.query_by_proto_name(args.proto, args.exact)
        query.print_results(results, f"标准Protobuf消息名包含 '{args.proto}'", args.format)
    elif args.module:
        results = query.query_by_module_id(args.module)
        query.print_results(results, f"ModuleID = {args.module}", args.format)
    elif args.batch:
        results = query.query_by_batch(args.batch)
        query.print_results(results, f"批次ID = {args.batch}", args.format)
    elif args.type:
        results = query.query_by_message_type(args.type)
        query.print_results(results, f"消息类型 = {args.type}", args.format)
    elif args.status:
        results = query.query_by_status(args.status)
        query.print_results(results, f"迁移状态 = {args.status}", args.format)
    elif args.all:
        results = query.list_all(args.limit)
        query.print_results(results, f"所有消息 (前{args.limit}条)", args.format)
    elif args.stats:
        stats = query.show_statistics()
        if args.format == 'json':
            print(json.dumps(stats, indent=2, ensure_ascii=False))
        else:
            query.print_statistics(stats)
    else:
        parser.print_help()
        print("\n💡 提示: 使用 --stats 查看总体统计")

if __name__ == '__main__':
    main()
