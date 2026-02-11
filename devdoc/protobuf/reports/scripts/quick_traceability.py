#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移追溯系统 - 快速查询工具
"""

import sqlite3
import re
from pathlib import Path
from typing import Dict, List

class QuickTraceabilityQuery:
    """快速追溯查询工具"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def quick_query(self, proto_message: str) -> Dict:
        """快速查询消息的追溯信息"""
        result = {
            'message_name': proto_message,
            'summary': '',
            'details': []
        }
        
        # 1. 检查是否有直接映射
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT jprotobuf_message_name, batch_id 
            FROM jprotobuf_proto_mappings 
            WHERE proto_message_name = ?
        ''', (proto_message,))
        
        mapping = cursor.fetchone()
        conn.close()
        
        if mapping:
            # 有直接映射
            result['summary'] = f"✅ 直接映射自 JProtobuf: {mapping[0]}"
            result['details'].append(f"批次ID: {mapping[1]}")
            return result
        
        # 2. 查找批次文档
        batch_info = self._find_in_batch_docs(proto_message)
        
        if batch_info:
            # 在批次文档中找到
            batch_id = batch_info['batch_id']
            doc_type = batch_info['doc_type']
            
            result['summary'] = f"✅ 在批次 {batch_id} 中定义的{doc_type}"
            result['details'].append(f"批次: batch_{batch_id}")
            result['details'].append(f"文档: {doc_type}")
            
            if batch_info['context']:
                result['details'].append(f"上下文: {batch_info['context'][:100]}...")
            
            return result
        
        # 3. 分析使用关系
        usage_info = self._analyze_usage(proto_message)
        
        if usage_info['used_by']:
            # 被其他消息使用
            main_users = [u for u in usage_info['used_by'] if 'Request' in u or 'Response' in u]
            
            result['summary'] = f"✅ 数据结构消息，被 {len(usage_info['used_by'])} 个消息使用"
            result['details'].append(f"主要使用者: {', '.join(main_users[:3])}")
            
            if len(usage_info['used_by']) > 3:
                result['details'].append(f"... 还有 {len(usage_info['used_by']) - 3} 个")
            
            return result
        
        # 4. 未找到明确信息
        result['summary'] = "❓ 未找到明确的迁移原因"
        result['details'].append("可能是未完成迁移或未记录的消息")
        
        return result
    
    def _find_in_batch_docs(self, proto_message: str) -> Dict:
        """在批次文档中查找消息"""
        for batch_dir in sorted(self.batch_docs_dir.glob('batch_*')):
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
                    return {
                        'batch_id': batch_id,
                        'doc_type': '迁移计划',
                        'context': self._extract_context(content, proto_message)
                    }
            
            # 检查迁移结果
            result_file = batch_dir / '02_迁移结果.md'
            if result_file.exists():
                with open(result_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                if proto_message in content:
                    return {
                        'batch_id': batch_id,
                        'doc_type': '迁移结果',
                        'context': self._extract_context(content, proto_message)
                    }
        
        return None
    
    def _extract_context(self, content: str, proto_message: str) -> str:
        """提取上下文"""
        lines = content.split('\n')
        for i, line in enumerate(lines):
            if proto_message in line:
                start = max(0, i - 2)
                end = min(len(lines), i + 3)
                return '\n'.join(lines[start:end])
        return ""
    
    def _analyze_usage(self, proto_message: str) -> Dict:
        """分析消息的使用情况"""
        proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        
        usage_info = {
            'used_by': [],
            'uses': []
        }
        
        for proto_file in proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 检查是否被使用
            if re.search(rf'\b{re.escape(proto_message)}\b', content):
                # 查找使用它的消息
                message_pattern = r'message\s+(\w+)\s*\{'
                for match in re.finditer(message_pattern, content):
                    using_message = match.group(1)
                    if using_message != proto_message:
                        usage_info['used_by'].append(using_message)
        
        return usage_info
    
    def print_quick_result(self, proto_message: str):
        """打印快速查询结果"""
        result = self.quick_query(proto_message)
        
        print(f"\n【{proto_message}】")
        print(f"  {result['summary']}")
        
        if result['details']:
            print("  详细信息:")
            for detail in result['details']:
                print(f"    - {detail}")

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='快速追溯查询工具')
    parser.add_argument('message', type=str, help='要查询的消息名称')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    query = QuickTraceabilityQuery(db_path)
    query.print_quick_result(args.message)

if __name__ == '__main__':
    main()
