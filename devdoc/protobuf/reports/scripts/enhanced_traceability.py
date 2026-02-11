#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
增强版迁移追溯系统 - 从批次文档提取详细迁移原因
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Optional
from datetime import datetime
from collections import defaultdict

class EnhancedMigrationTraceability:
    """增强版迁移追溯系统"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def find_message_in_batch_docs(self, proto_message: str) -> Dict:
        """在批次文档中查找消息"""
        info = {
            'proto_message': proto_message,
            'found_in_batches': [],
            'migration_reasons': [],
            'refactoring_details': []
        }
        
        # 搜索所有批次文档
        for batch_dir in sorted(self.batch_docs_dir.glob('batch_*')):
            batch_num = re.search(r'batch_(\d+)', batch_dir.name)
            if not batch_num:
                continue
            
            batch_id = int(batch_num.group(1))
            
            # 检查迁移计划
            plan_file = batch_dir / '01_迁移计划.md'
            if plan_file.exists():
                with open(plan_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                if proto_message in content:
                    info['found_in_batches'].append({
                        'batch_id': batch_id,
                        'file': '01_迁移计划.md',
                        'context': self._extract_context(content, proto_message)
                    })
            
            # 检查迁移结果
            result_file = batch_dir / '02_迁移结果.md'
            if result_file.exists():
                with open(result_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                if proto_message in content:
                    info['found_in_batches'].append({
                        'batch_id': batch_id,
                        'file': '02_迁移结果.md',
                        'context': self._extract_context(content, proto_message)
                    })
                    
                    # 提取重构详情
                    refactoring = self._extract_refactoring_details(content, proto_message)
                    if refactoring:
                        info['refactoring_details'].append({
                            'batch_id': batch_id,
                            'details': refactoring
                        })
        
        return info
    
    def _extract_context(self, content: str, proto_message: str) -> str:
        """提取消息的上下文"""
        lines = content.split('\n')
        for i, line in enumerate(lines):
            if proto_message in line:
                # 获取前后5行
                start = max(0, i - 5)
                end = min(len(lines), i + 6)
                context = '\n'.join(lines[start:end])
                return context
        return ""
    
    def _extract_refactoring_details(self, content: str, proto_message: str) -> Optional[str]:
        """提取重构详情"""
        # 查找问题描述和解决方案
        problem_match = re.search(
            rf'问题描述[：:][^#]*{re.escape(proto_message)}[^#]*?问题描述[：:]\s*([^\n]+)',
            content, re.DOTALL
        )
        
        solution_match = re.search(
            rf'解决方案[：:][^#]*{re.escape(proto_message)}[^#]*?解决方案[：:]\s*([^\n]+)',
            content, re.DOTALL
        )
        
        details = []
        if problem_match:
            details.append(f"问题: {problem_match.group(1)}")
        if solution_match:
            details.append(f"解决方案: {solution_match.group(1)}")
        
        return '\n'.join(details) if details else None
    
    def analyze_message_relationships(self, proto_message: str) -> Dict:
        """分析消息的关系"""
        relationships = {
            'proto_message': proto_message,
            'direct_mappings': [],
            'indirect_mappings': [],
            'used_by_messages': [],
            'uses_messages': []
        }
        
        # 1. 查找直接映射
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, batch_id 
            FROM jprotobuf_proto_mappings 
            WHERE proto_message_name = ?
        ''', (proto_message,))
        
        direct_result = cursor.fetchone()
        if direct_result:
            relationships['direct_mappings'].append({
                'jprotobuf_name': direct_result[0],
                'batch_id': direct_result[1],
                'type': 'direct'
            })
        
        # 2. 查找间接映射（通过批次）
        if not relationships['direct_mappings']:
            # 查找同一批次中的其他消息
            cursor.execute('''
                SELECT DISTINCT batch_id 
                FROM jprotobuf_proto_mappings 
                WHERE proto_message_name LIKE ?
            ''', (f'%{proto_message.replace("Request", "").replace("Response", "").upper()}%',))
            
            batch_results = cursor.fetchall()
            for batch_row in batch_results:
                batch_id = batch_row[0]
                
                # 查找该批次中的所有JProtobuf消息
                cursor.execute('''
                    SELECT jprotobuf_message_name, proto_message_name 
                    FROM jprotobuf_proto_mappings 
                    WHERE batch_id = ?
                ''', (batch_id,))
                
                batch_mappings = cursor.fetchall()
                for jprotobuf_name, mapped_proto in batch_mappings:
                    if mapped_proto:
                        relationships['indirect_mappings'].append({
                            'jprotobuf_name': jprotobuf_name,
                            'proto_name': mapped_proto,
                            'batch_id': batch_id,
                            'type': 'indirect'
                        })
        
        conn.close()
        
        # 3. 分析proto文件中的使用关系
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 检查是否被其他消息使用
            usage_pattern = rf'\b{re.escape(proto_message)}\b'
            if re.search(usage_pattern, content):
                # 查找使用它的消息
                message_pattern = r'message\s+(\w+)\s*\{'
                for match in re.finditer(message_pattern, content):
                    using_message = match.group(1)
                    if using_message != proto_message:
                        relationships['used_by_messages'].append({
                            'message': using_message,
                            'file': proto_file.name
                        })
            
            # 检查是否使用了其他消息
            message_pattern = rf'message\s+{re.escape(proto_message)}\s*\{{([^}}]+)\}}'
            match = re.search(message_pattern, content, re.DOTALL)
            if match:
                message_body = match.group(1)
                
                # 提取字段类型
                field_pattern = r'(\w+)\s+\w+\s*=\s*\d+'
                for field_match in re.finditer(field_pattern, message_body):
                    field_type = field_match.group(1)
                    
                    # 检查是否是自定义消息类型
                    if field_type not in ['int32', 'int64', 'uint32', 'uint64', 'bool', 'string', 'float', 'double', 'bytes']:
                        relationships['uses_messages'].append({
                            'message': field_type,
                            'type': 'field'
                        })
        
        return relationships
    
    def generate_comprehensive_traceability_report(self, proto_message: str):
        """生成综合追溯报告"""
        print("=" * 80)
        print(f"消息追溯报告: {proto_message}")
        print("=" * 80)
        print()
        
        # 1. 查找批次文档
        batch_info = self.find_message_in_batch_docs(proto_message)
        
        if batch_info['found_in_batches']:
            print("📜 批次文档记录")
            print("-" * 80)
            for record in batch_info['found_in_batches']:
                print(f"  批次 {record['batch_id']} - {record['file']}")
                if record['context']:
                    print(f"  上下文:")
                    for line in record['context'].split('\n'):
                        print(f"    {line}")
            print()
        
        # 2. 分析消息关系
        relationships = self.analyze_message_relationships(proto_message)
        
        if relationships['direct_mappings']:
            print("🔗 直接映射关系")
            print("-" * 80)
            for mapping in relationships['direct_mappings']:
                print(f"  JProtobuf: {mapping['jprotobuf_name']}")
                print(f"  批次ID: {mapping['batch_id']}")
                print(f"  类型: 直接映射")
            print()
        
        if relationships['indirect_mappings']:
            print("🔗 间接映射关系（同一批次）")
            print("-" * 80)
            for mapping in relationships['indirect_mappings'][:5]:
                print(f"  JProtobuf: {mapping['jprotobuf_name']}")
                print(f"  Proto: {mapping['proto_name']}")
                print(f"  批次ID: {mapping['batch_id']}")
            if len(relationships['indirect_mappings']) > 5:
                print(f"  ... 还有 {len(relationships['indirect_mappings']) - 5} 个")
            print()
        
        if relationships['used_by_messages']:
            print("📋 被以下消息使用")
            print("-" * 80)
            for usage in relationships['used_by_messages'][:10]:
                print(f"  {usage['message']} (文件: {usage['file']})")
            if len(relationships['used_by_messages']) > 10:
                print(f"  ... 还有 {len(relationships['used_by_messages']) - 10} 个")
            print()
        
        if relationships['uses_messages']:
            print("📋 使用了以下消息")
            print("-" * 80)
            for usage in relationships['uses_messages'][:10]:
                print(f"  {usage['message']} (类型: {usage['type']})")
            if len(relationships['uses_messages']) > 10:
                print(f"  ... 还有 {len(relationships['uses_messages']) - 10} 个")
            print()
        
        # 3. 推断迁移原因
        self._infer_migration_reason(proto_message, batch_info, relationships)
    
    def _infer_migration_reason(self, proto_message: str, batch_info: Dict, relationships: Dict):
        """推断迁移原因"""
        print("💡 推断的迁移原因")
        print("-" * 80)
        
        reasons = []
        
        # 检查是否有直接映射
        if relationships['direct_mappings']:
            reasons.append("✅ 这是直接从JProtobuf映射过来的消息")
            reasons.append(f"   来源: {relationships['direct_mappings'][0]['jprotobuf_name']}")
        
        # 检查是否在批次文档中
        elif batch_info['found_in_batches']:
            reasons.append("✅ 这是迁移过程中定义的新消息")
            
            # 分析上下文
            for record in batch_info['found_in_batches']:
                if '迁移计划' in record['file']:
                    reasons.append(f"   在批次 {record['batch_id']} 的迁移计划中定义")
                elif '迁移结果' in record['file']:
                    reasons.append(f"   在批次 {record['batch_id']} 的迁移结果中生成")
        
        # 检查是否被其他消息使用
        elif relationships['used_by_messages']:
            reasons.append("✅ 这是作为数据结构定义的辅助消息")
            reasons.append(f"   被 {len(relationships['used_by_messages'])} 个消息使用")
            
            # 查找使用它的主要消息
            main_users = [u['message'] for u in relationships['used_by_messages'] if 'Request' in u['message'] or 'Response' in u['message']]
            if main_users:
                reasons.append(f"   主要用于: {', '.join(main_users[:3])}")
        
        # 检查是否使用了其他消息
        elif relationships['uses_messages']:
            reasons.append("✅ 这是组合了其他数据结构的复杂消息")
            reasons.append(f"   使用了 {len(relationships['uses_messages'])} 个子消息")
        
        else:
            reasons.append("❓ 未找到明确的迁移原因")
            reasons.append("   可能是未完成迁移或未记录的消息")
        
        for reason in reasons:
            print(f"  {reason}")
        
        print()

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='增强版迁移追溯系统')
    parser.add_argument('message', type=str, help='要查询的消息名称')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    system = EnhancedMigrationTraceability(db_path)
    system.generate_comprehensive_traceability_report(args.message)

if __name__ == '__main__':
    main()
