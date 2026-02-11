#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
分析迁移记录的混乱情况
"""

import sqlite3
import re
from pathlib import Path
from typing import Dict, List, Set
from collections import defaultdict

class MigrationConfusionAnalyzer:
    """迁移记录混乱分析器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def analyze_message_confusion(self, message_name: str) -> Dict:
        """分析单个消息的混乱情况"""
        print("=" * 80)
        print(f"分析消息: {message_name}")
        print("=" * 80)
        print()
        
        confusion_info = {
            'message_name': message_name,
            'database_records': [],
            'batch_plans': [],
            'batch_results': [],
            'conflicts': []
        }
        
        # 1. 查询数据库记录
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT id, jprotobuf_message_name, proto_message_name, is_migrated,
                   batch_id, message_type, created_at, updated_at
            FROM jprotobuf_proto_mappings
            WHERE jprotobuf_message_name = ?
            ORDER BY id
        ''', (message_name,))
        
        db_records = cursor.fetchall()
        conn.close()
        
        if db_records:
            print("📊 数据库记录:")
            print("-" * 80)
            for row in db_records:
                print(f"  记录ID: {row[0]}")
                print(f"  JProtobuf: {row[1]}")
                print(f"  Proto: {row[2]}")
                print(f"  已迁移: {'✅' if row[3] else '❌'}")
                print(f"  批次: {row[4]}")
                print(f"  类型: {row[5]}")
                print(f"  创建时间: {row[6]}")
                print(f"  更新时间: {row[7]}")
                print()
            
            confusion_info['database_records'] = db_records
        else:
            print("📊 数据库记录: 未找到")
            print()
        
        # 2. 搜索批次文档
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
                
                if message_name in content:
                    # 提取上下文
                    lines = content.split('\n')
                    for i, line in enumerate(lines):
                        if message_name in line:
                            context_start = max(0, i - 2)
                            context_end = min(len(lines), i + 3)
                            context = '\n'.join(lines[context_start:context_end])
                            
                            confusion_info['batch_plans'].append({
                                'batch_id': batch_id,
                                'file': '01_迁移计划.md',
                                'context': context
                            })
                            break
            
            # 检查迁移结果
            result_file = batch_dir / '02_迁移结果.md'
            if result_file.exists():
                with open(result_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                if message_name in content:
                    # 提取上下文
                    lines = content.split('\n')
                    for i, line in enumerate(lines):
                        if message_name in line:
                            context_start = max(0, i - 2)
                            context_end = min(len(lines), i + 3)
                            context = '\n'.join(lines[context_start:context_end])
                            
                            confusion_info['batch_results'].append({
                                'batch_id': batch_id,
                                'file': '02_迁移结果.md',
                                'context': context
                            })
                            break
        
        # 3. 打印批次文档记录
        if confusion_info['batch_plans']:
            print("📜 批次迁移计划:")
            print("-" * 80)
            for record in confusion_info['batch_plans']:
                print(f"  批次 {record['batch_id']} - {record['file']}")
                print(f"  上下文:")
                for line in record['context'].split('\n'):
                    print(f"    {line}")
                print()
        
        if confusion_info['batch_results']:
            print("📜 批次迁移结果:")
            print("-" * 80)
            for record in confusion_info['batch_results']:
                print(f"  批次 {record['batch_id']} - {record['file']}")
                print(f"  上下文:")
                for line in record['context'].split('\n'):
                    print(f"    {line}")
                print()
        
        # 4. 分析冲突
        print("🔍 冲突分析:")
        print("-" * 80)
        
        # 检查是否在多个批次中出现
        batch_ids_in_plans = {r['batch_id'] for r in confusion_info['batch_plans']}
        batch_ids_in_results = {r['batch_id'] for r in confusion_info['batch_results']}
        all_batch_ids = batch_ids_in_plans | batch_ids_in_results
        
        if len(all_batch_ids) > 1:
            print(f"  ⚠️  该消息在 {len(all_batch_ids)} 个批次中出现:")
            for batch_id in sorted(all_batch_ids):
                in_plan = batch_id in batch_ids_in_plans
                in_result = batch_id in batch_ids_in_results
                print(f"    批次 {batch_id}: {'迁移计划' if in_plan else ''}{'迁移结果' if in_result else ''}")
            confusion_info['conflicts'].append(f"在多个批次中出现")
        
        # 检查数据库中的批次与文档是否一致
        if db_records:
            db_batch_ids = {row[4] for row in db_records if row[4]}
            if db_batch_ids != all_batch_ids:
                print(f"  ⚠️  数据库中的批次与文档不一致:")
                print(f"    数据库批次: {sorted(db_batch_ids)}")
                print(f"    文档批次: {sorted(all_batch_ids)}")
                confusion_info['conflicts'].append("数据库批次与文档不一致")
        
        # 检查是否声称已迁移但实际未迁移
        if db_records:
            for row in db_records:
                if row[3]:  # is_migrated = True
                    if not row[2]:  # proto_message_name = None
                        print(f"  ⚠️  记录ID {row[0]} 声称已迁移但没有Proto消息名")
                        confusion_info['conflicts'].append(f"记录{row[0]}声称已迁移但没有Proto消息名")
        
        # 检查是否在批次结果中声称已迁移
        for record in confusion_info['batch_results']:
            context = record['context']
            if '→' in context and '已存在' in context:
                print(f"  ⚠️  批次 {record['batch_id']} 声称该消息已存在")
                confusion_info['conflicts'].append(f"批次{record['batch_id']}声称已存在")
        
        if not confusion_info['conflicts']:
            print("  ✅ 未发现冲突")
        
        print()
        
        return confusion_info
    
    def analyze_all_confusions(self) -> Dict:
        """分析所有混乱的迁移记录"""
        print("🔍 分析所有混乱的迁移记录...")
        print()
        
        # 获取所有在多个批次中出现的消息
        message_batches = defaultdict(list)
        
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
                
                # 提取消息名称
                message_pattern = r'^(?:\d+\.\s+)?([A-Z_]+)\s*(?:-|→|$)'
                for match in re.finditer(message_pattern, content, re.MULTILINE):
                    message_name = match.group(1)
                    if len(message_name) > 3:  # 过滤掉太短的
                        message_batches[message_name].append({
                            'batch_id': batch_id,
                            'file': '01_迁移计划.md'
                        })
            
            # 检查迁移结果
            result_file = batch_dir / '02_迁移结果.md'
            if result_file.exists():
                with open(result_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                # 提取消息名称
                message_pattern = r'^(?:\d+\.\s+)?(?:\*\*)?([A-Z_]+)(?:\*\*)?\s*(?:-|→|$)'
                for match in re.finditer(message_pattern, content, re.MULTILINE):
                    message_name = match.group(1)
                    if len(message_name) > 3:  # 过滤掉太短的
                        message_batches[message_name].append({
                            'batch_id': batch_id,
                            'file': '02_迁移结果.md'
                        })
        
        # 找出在多个批次中出现的消息
        confused_messages = {
            msg: batches for msg, batches in message_batches.items()
            if len(batches) > 1
        }
        
        print(f"📊 在多个批次中出现的消息数: {len(confused_messages)}")
        print()
        
        # 按出现次数排序
        sorted_messages = sorted(
            confused_messages.items(),
            key=lambda x: len(x[1]),
            reverse=True
        )
        
        print("📋 在多个批次中出现的消息:")
        print("-" * 80)
        for i, (msg, batches) in enumerate(sorted_messages[:20], 1):
            batch_ids = sorted({b['batch_id'] for b in batches})
            print(f"{i}. {msg} ({len(batches)}次)")
            print(f"   批次: {', '.join(map(str, batch_ids))}")
        
        if len(sorted_messages) > 20:
            print(f"... 还有 {len(sorted_messages) - 20} 个")
        
        print()
        
        return confused_messages

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='迁移记录混乱分析器')
    parser.add_argument('--message', type=str, help='分析特定消息')
    parser.add_argument('--all', action='store_true', help='分析所有混乱的记录')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    analyzer = MigrationConfusionAnalyzer(db_path)
    
    if args.message:
        analyzer.analyze_message_confusion(args.message)
    elif args.all:
        analyzer.analyze_all_confusions()
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
