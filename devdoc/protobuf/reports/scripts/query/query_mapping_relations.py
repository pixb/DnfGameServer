#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
JProtobuf与标准Protobuf映射关系详细查询工具
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Optional

class MappingRelationQuery:
    """查询JProtobuf与标准Protobuf之间的映射关系"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        self.java_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def query_by_jprotobuf_name(self, jprotobuf_name: str) -> Optional[Dict]:
        """根据JProtobuf消息名查询映射关系"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                   proto_file_path, is_migrated, module_id, message_type, batch_id
            FROM jprotobuf_proto_mappings
            WHERE jprotobuf_message_name LIKE ?
        ''', (f'%{jprotobuf_name}%',))
        
        results = cursor.fetchall()
        conn.close()
        
        if not results:
            return None
        
        return {
            'type': 'jprotobuf',
            'results': [{
                'jprotobuf_message_name': row[0],
                'jprotobuf_file_path': row[1],
                'proto_message_name': row[2],
                'proto_file_path': row[3],
                'is_migrated': row[4],
                'module_id': row[5],
                'message_type': row[6],
                'batch_id': row[7]
            } for row in results]
        }
    
    def query_by_proto_name(self, proto_name: str) -> Optional[Dict]:
        """根据标准Protobuf消息名查询映射关系"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                   proto_file_path, is_migrated, module_id, message_type, batch_id
            FROM jprotobuf_proto_mappings
            WHERE proto_message_name LIKE ?
        ''', (f'%{proto_name}%',))
        
        results = cursor.fetchall()
        conn.close()
        
        if not results:
            return None
        
        return {
            'type': 'proto',
            'results': [{
                'jprotobuf_message_name': row[0],
                'jprotobuf_file_path': row[1],
                'proto_message_name': row[2],
                'proto_file_path': row[3],
                'is_migrated': row[4],
                'module_id': row[5],
                'message_type': row[6],
                'batch_id': row[7]
            } for row in results]
        }
    
    def query_by_module_id(self, module_id: int) -> Optional[Dict]:
        """根据ModuleID查询映射关系"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                   proto_file_path, is_migrated, module_id, message_type, batch_id
            FROM jprotobuf_proto_mappings
            WHERE module_id = ?
        ''', (module_id,))
        
        results = cursor.fetchall()
        conn.close()
        
        if not results:
            return None
        
        return {
            'type': 'module',
            'results': [{
                'jprotobuf_message_name': row[0],
                'jprotobuf_file_path': row[1],
                'proto_message_name': row[2],
                'proto_file_path': row[3],
                'is_migrated': row[4],
                'module_id': row[5],
                'message_type': row[6],
                'batch_id': row[7]
            } for row in results]
        }
    
    def query_by_batch_id(self, batch_id: int) -> Optional[Dict]:
        """根据批次ID查询映射关系"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                   proto_file_path, is_migrated, module_id, message_type, batch_id
            FROM jprotobuf_proto_mappings
            WHERE batch_id = ?
        ''', (batch_id,))
        
        results = cursor.fetchall()
        conn.close()
        
        if not results:
            return None
        
        return {
            'type': 'batch',
            'results': [{
                'jprotobuf_message_name': row[0],
                'jprotobuf_file_path': row[1],
                'proto_message_name': row[2],
                'proto_file_path': row[3],
                'is_migrated': row[4],
                'module_id': row[5],
                'message_type': row[6],
                'batch_id': row[7]
            } for row in results]
        }
    
    def query_by_message_type(self, message_type: str) -> Optional[Dict]:
        """根据消息类型查询映射关系"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                   proto_file_path, is_migrated, module_id, message_type, batch_id
            FROM jprotobuf_proto_mappings
            WHERE message_type = ?
        ''', (message_type,))
        
        results = cursor.fetchall()
        conn.close()
        
        if not results:
            return None
        
        return {
            'type': 'message_type',
            'results': [{
                'jprotobuf_message_name': row[0],
                'jprotobuf_file_path': row[1],
                'proto_message_name': row[2],
                'proto_file_path': row[3],
                'is_migrated': row[4],
                'module_id': row[5],
                'message_type': row[6],
                'batch_id': row[7]
            } for row in results]
        }
    
    def query_new_proto_messages(self) -> Optional[Dict]:
        """查询标准Protobuf新增的消息（JProtobuf中不存在）"""
        # 扫描所有proto消息
        proto_messages = set()
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                proto_messages.add(match.group(1))
        
        # 从数据库获取所有映射的proto消息
        conn = self._connect()
        cursor = conn.cursor()
        cursor.execute('SELECT DISTINCT proto_message_name FROM jprotobuf_proto_mappings WHERE proto_message_name IS NOT NULL')
        mapped_proto_messages = {row[0] for row in cursor.fetchall()}
        conn.close()
        
        # 新增的消息
        new_messages = proto_messages - mapped_proto_messages
        
        if not new_messages:
            return None
        
        return {
            'type': 'new_proto',
            'results': sorted(new_messages)
        }
    
    def query_not_migrated(self) -> Optional[Dict]:
        """查询未迁移的消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                   proto_file_path, is_migrated, module_id, message_type, batch_id
            FROM jprotobuf_proto_mappings
            WHERE is_migrated = 0 OR proto_message_name IS NULL
        ''')
        
        results = cursor.fetchall()
        conn.close()
        
        if not results:
            return None
        
        return {
            'type': 'not_migrated',
            'results': [{
                'jprotobuf_message_name': row[0],
                'jprotobuf_file_path': row[1],
                'proto_message_name': row[2],
                'proto_file_path': row[3],
                'is_migrated': row[4],
                'module_id': row[5],
                'message_type': row[6],
                'batch_id': row[7]
            } for row in results]
        }
    
    def print_results(self, result: Dict, limit: int = 50):
        """打印查询结果"""
        if not result:
            print("❌ 未找到匹配的结果")
            return
        
        result_type = result['type']
        results = result['results']
        
        print(f"\n🔍 查询类型: {result_type}")
        print(f"📊 找到 {len(results)} 条结果")
        print("=" * 80)
        
        if result_type == 'new_proto':
            for i, msg in enumerate(results[:limit]):
                print(f"  [{i+1}] {msg}")
            if len(results) > limit:
                print(f"  ... 还有 {len(results) - limit} 个")
        else:
            for i, item in enumerate(results[:limit]):
                status = "✅" if item['is_migrated'] else "❌"
                print(f"\n【{i+1}】{status} {item['jprotobuf_message_name']}")
                print(f"    JProtobuf文件: {item['jprotobuf_file_path']}")
                print(f"    ModuleID: {item['module_id']}, 消息类型: {item['message_type']}")
                if item['proto_message_name']:
                    print(f"    → 标准Protobuf消息: {item['proto_message_name']}")
                    print(f"    → Proto文件: {item['proto_file_path']}")
                else:
                    print(f"    → 标准Protobuf消息: 未映射")
                if item['batch_id']:
                    print(f"    → 批次ID: {item['batch_id']}")
            
            if len(results) > limit:
                print(f"\n... 还有 {len(results) - limit} 条结果")

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='查询JProtobuf与标准Protobuf之间的映射关系')
    parser.add_argument('--jprotobuf', type=str, help='根据JProtobuf消息名查询')
    parser.add_argument('--proto', type=str, help='根据标准Protobuf消息名查询')
    parser.add_argument('--module', type=int, help='根据ModuleID查询')
    parser.add_argument('--batch', type=int, help='根据批次ID查询')
    parser.add_argument('--type', type=str, help='根据消息类型查询 (REQ/RES/PT/NOTIFY)')
    parser.add_argument('--new-proto', action='store_true', help='查询标准Protobuf新增的消息')
    parser.add_argument('--not-migrated', action='store_true', help='查询未迁移的消息')
    parser.add_argument('--limit', type=int, default=50, help='限制显示结果数量')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    query = MappingRelationQuery(db_path)
    
    result = None
    
    if args.jprotobuf:
        result = query.query_by_jprotobuf_name(args.jprotobuf)
    elif args.proto:
        result = query.query_by_proto_name(args.proto)
    elif args.module:
        result = query.query_by_module_id(args.module)
    elif args.batch:
        result = query.query_by_batch_id(args.batch)
    elif args.type:
        result = query.query_by_message_type(args.type.upper())
    elif args.new_proto:
        result = query.query_new_proto_messages()
    elif args.not_migrated:
        result = query.query_not_migrated()
    else:
        parser.print_help()
        return
    
    query.print_results(result, args.limit)

if __name__ == '__main__':
    main()
