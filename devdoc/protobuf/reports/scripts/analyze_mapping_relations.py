#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
JProtobuf与标准Protobuf对应关系统计工具
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Set, Tuple
from collections import defaultdict
from datetime import datetime

class MappingRelationAnalyzer:
    """分析JProtobuf与标准Protobuf之间的对应关系"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        self.java_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def scan_all_jprotobuf_messages(self) -> Set[str]:
        """扫描所有JProtobuf消息"""
        messages = set()
        
        for java_file in self.java_dir.glob('*.java'):
            filename = java_file.name
            
            # 跳过JProtobuf生成的类文件
            if '$$JProtoBufClass' in filename:
                continue
            
            # 跳过Message基类
            if filename == 'Message.java':
                continue
            
            # 提取消息名（去掉.java扩展名）
            message_name = filename[:-5]
            messages.add(message_name)
        
        return messages
    
    def scan_all_proto_messages(self) -> Set[str]:
        """扫描所有标准Protobuf消息"""
        messages = set()
        
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取所有message定义
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                msg_name = match.group(1)
                messages.add(msg_name)
        
        return messages
    
    def get_database_mappings(self) -> Dict[str, Dict]:
        """从数据库获取映射关系"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, proto_message_name, is_migrated, 
                   module_id, message_type, batch_id, proto_file_path
            FROM jprotobuf_proto_mappings
        ''')
        
        mappings = {}
        for row in cursor.fetchall():
            jprotobuf_name = row[0]
            mappings[jprotobuf_name] = {
                'proto_message_name': row[1],
                'is_migrated': row[2],
                'module_id': row[3],
                'message_type': row[4],
                'batch_id': row[5],
                'proto_file_path': row[6]
            }
        
        conn.close()
        return mappings
    
    def classify_mappings(self, jprotobuf_messages: Set[str], 
                        proto_messages: Set[str],
                        db_mappings: Dict[str, Dict]) -> Dict:
        """分类映射关系"""
        
        # 1. 已迁移的消息（数据库中有映射）
        migrated = {}
        for jprotobuf_name, mapping in db_mappings.items():
            if mapping['is_migrated']:
                proto_name = mapping['proto_message_name']
                if proto_name:
                    migrated[jprotobuf_name] = {
                        'proto_name': proto_name,
                        'module_id': mapping['module_id'],
                        'message_type': mapping['message_type'],
                        'batch_id': mapping['batch_id'],
                        'proto_file_path': mapping['proto_file_path']
                    }
        
        # 2. JProtobuf中存在但未迁移的消息
        not_migrated = set()
        for jprotobuf_name in jprotobuf_messages:
            if jprotobuf_name not in db_mappings or not db_mappings[jprotobuf_name]['is_migrated']:
                not_migrated.add(jprotobuf_name)
        
        # 3. 标准Protobuf中新增的消息（JProtobuf中不存在）
        new_proto_messages = set()
        for proto_name in proto_messages:
            # 检查是否在任何映射中
            found = False
            for mapping in db_mappings.values():
                if mapping['proto_message_name'] == proto_name:
                    found = True
                    break
            if not found:
                new_proto_messages.add(proto_name)
        
        # 4. 直接对应（名称相同或相似）
        direct_mappings = []
        for jprotobuf_name, mapping in migrated.items():
            proto_name = mapping['proto_name']
            
            # 完全匹配
            if jprotobuf_name == proto_name:
                direct_mappings.append((jprotobuf_name, proto_name, 'exact'))
            # 去掉REQ/RES/PT前缀后匹配
            elif self._is_similar_name(jprotobuf_name, proto_name):
                direct_mappings.append((jprotobuf_name, proto_name, 'similar'))
        
        # 5. 重命名（名称不同但功能相同）
        renamed_mappings = []
        for jprotobuf_name, mapping in migrated.items():
            proto_name = mapping['proto_name']
            if not self._is_similar_name(jprotobuf_name, proto_name):
                renamed_mappings.append((jprotobuf_name, proto_name))
        
        return {
            'migrated': migrated,
            'not_migrated': not_migrated,
            'new_proto_messages': new_proto_messages,
            'direct_mappings': direct_mappings,
            'renamed_mappings': renamed_mappings,
            'total_jprotobuf': len(jprotobuf_messages),
            'total_proto': len(proto_messages),
            'total_migrated': len(migrated),
            'total_not_migrated': len(not_migrated),
            'total_new_proto': len(new_proto_messages)
        }
    
    def _is_similar_name(self, jprotobuf_name: str, proto_name: str) -> bool:
        """判断两个名称是否相似"""
        # 去掉前缀
        jprotobuf_base = jprotobuf_name.replace('REQ_', '').replace('RES_', '').replace('PT_', '')
        proto_base = proto_name.replace('Request', '').replace('Response', '')
        
        # 转换为大写比较
        return jprotobuf_base.upper() == proto_base.upper()
    
    def analyze_by_message_type(self, migrated: Dict[str, Dict]) -> Dict:
        """按消息类型分析"""
        type_stats = defaultdict(lambda: {'total': 0, 'migrated': 0, 'details': []})
        
        for jprotobuf_name, mapping in migrated.items():
            msg_type = mapping['message_type'] or 'UNKNOWN'
            type_stats[msg_type]['total'] += 1
            type_stats[msg_type]['migrated'] += 1
            type_stats[msg_type]['details'].append({
                'jprotobuf': jprotobuf_name,
                'proto': mapping['proto_name'],
                'module_id': mapping['module_id'],
                'batch_id': mapping['batch_id']
            })
        
        return dict(type_stats)
    
    def analyze_by_batch(self, migrated: Dict[str, Dict]) -> Dict:
        """按批次分析"""
        batch_stats = defaultdict(lambda: {'total': 0, 'details': []})
        
        for jprotobuf_name, mapping in migrated.items():
            batch_id = mapping['batch_id']
            batch_stats[batch_id]['total'] += 1
            batch_stats[batch_id]['details'].append({
                'jprotobuf': jprotobuf_name,
                'proto': mapping['proto_name'],
                'module_id': mapping['module_id'],
                'message_type': mapping['message_type']
            })
        
        return dict(batch_stats)
    
    def analyze_by_proto_file(self, migrated: Dict[str, Dict]) -> Dict:
        """按Proto文件分析"""
        file_stats = defaultdict(lambda: {'total': 0, 'details': []})
        
        for jprotobuf_name, mapping in migrated.items():
            proto_file = mapping['proto_file_path'] or 'unknown'
            file_stats[proto_file]['total'] += 1
            file_stats[proto_file]['details'].append({
                'jprotobuf': jprotobuf_name,
                'proto': mapping['proto_name'],
                'message_type': mapping['message_type']
            })
        
        return dict(file_stats)
    
    def generate_report(self, classification: Dict, type_stats: Dict, 
                      batch_stats: Dict, file_stats: Dict):
        """生成统计报告"""
        
        print("=" * 80)
        print("JProtobuf与标准Protobuf对应关系统计报告")
        print("=" * 80)
        print(f"生成时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
        print()
        
        # 总体统计
        print("📊 总体统计")
        print("-" * 80)
        print(f"JProtobuf消息总数: {classification['total_jprotobuf']}")
        print(f"标准Protobuf消息总数: {classification['total_proto']}")
        print(f"已迁移消息数: {classification['total_migrated']} ({classification['total_migrated']/classification['total_jprotobuf']*100:.2f}%)")
        print(f"未迁移消息数: {classification['total_not_migrated']} ({classification['total_not_migrated']/classification['total_jprotobuf']*100:.2f}%)")
        print(f"标准Protobuf新增消息: {classification['total_new_proto']}")
        print()
        
        # 映射类型统计
        print("🔗 映射类型统计")
        print("-" * 80)
        exact_count = len([m for m in classification['direct_mappings'] if m[2] == 'exact'])
        similar_count = len([m for m in classification['direct_mappings'] if m[2] == 'similar'])
        renamed_count = len(classification['renamed_mappings'])
        
        print(f"完全匹配: {exact_count}")
        print(f"相似匹配: {similar_count}")
        print(f"重命名: {renamed_count}")
        print()
        
        # 按消息类型统计
        print("📋 按消息类型统计")
        print("-" * 80)
        for msg_type, stats in sorted(type_stats.items()):
            print(f"{msg_type}: {stats['total']} 个消息")
        print()
        
        # 按批次统计
        print("📦 按批次统计")
        print("-" * 80)
        for batch_id in sorted([b for b in batch_stats.keys() if b is not None]):
            stats = batch_stats[batch_id]
            print(f"批次 {batch_id}: {stats['total']} 个消息")
        print()
        
        # 按Proto文件统计（Top 10）
        print("📁 按Proto文件统计 (Top 10)")
        print("-" * 80)
        sorted_files = sorted(file_stats.items(), key=lambda x: x[1]['total'], reverse=True)[:10]
        for proto_file, stats in sorted_files:
            print(f"{proto_file}: {stats['total']} 个消息")
        print()
        
        # 新增消息示例
        if classification['total_new_proto'] > 0:
            print("➕ 标准Protobuf新增消息示例 (前20个)")
            print("-" * 80)
            for msg in sorted(list(classification['new_proto_messages']))[:20]:
                print(f"  - {msg}")
            if classification['total_new_proto'] > 20:
                print(f"  ... 还有 {classification['total_new_proto'] - 20} 个")
            print()
        
        # 重命名消息示例
        if renamed_count > 0:
            print("🔄 重命名消息示例 (前20个)")
            print("-" * 80)
            for jprotobuf_name, proto_name in classification['renamed_mappings'][:20]:
                print(f"  {jprotobuf_name} → {proto_name}")
            if renamed_count > 20:
                print(f"  ... 还有 {renamed_count - 20} 个")
            print()
    
    def run(self):
        """执行分析"""
        print("🔍 正在扫描JProtobuf消息...")
        jprotobuf_messages = self.scan_all_jprotobuf_messages()
        print(f"  找到 {len(jprotobuf_messages)} 个JProtobuf消息")
        
        print("🔍 正在扫描标准Protobuf消息...")
        proto_messages = self.scan_all_proto_messages()
        print(f"  找到 {len(proto_messages)} 个标准Protobuf消息")
        
        print("🔍 正在从数据库获取映射关系...")
        db_mappings = self.get_database_mappings()
        print(f"  找到 {len(db_mappings)} 条映射记录")
        
        print("📊 正在分析映射关系...")
        classification = self.classify_mappings(jprotobuf_messages, proto_messages, db_mappings)
        
        print("📊 正在按消息类型分析...")
        type_stats = self.analyze_by_message_type(classification['migrated'])
        
        print("📊 正在按批次分析...")
        batch_stats = self.analyze_by_batch(classification['migrated'])
        
        print("📊 正在按Proto文件分析...")
        file_stats = self.analyze_by_proto_file(classification['migrated'])
        
        print()
        self.generate_report(classification, type_stats, batch_stats, file_stats)

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    
    analyzer = MappingRelationAnalyzer(db_path)
    analyzer.run()

if __name__ == '__main__':
    main()
