#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移追溯系统 - 扩展数据库以支持完整的迁移历史追溯
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Optional
from datetime import datetime
from collections import defaultdict

class MigrationTraceabilitySystem:
    """迁移追溯系统"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def create_traceability_tables(self):
        """创建追溯表"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 1. 消息迁移历史表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_migration_history (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                proto_message_name TEXT NOT NULL,
                migration_reason TEXT,
                created_from TEXT,
                created_from_type TEXT,
                batch_id INTEGER,
                migration_date TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                UNIQUE(proto_message_name)
            )
        ''')
        
        # 2. 消息依赖关系表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_dependencies (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                parent_message TEXT NOT NULL,
                child_message TEXT NOT NULL,
                dependency_type TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                UNIQUE(parent_message, child_message)
            )
        ''')
        
        # 3. 消息重构记录表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_refactoring_records (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                proto_message_name TEXT NOT NULL,
                original_structure TEXT,
                refactored_structure TEXT,
                refactoring_reason TEXT,
                batch_id INTEGER,
                refactoring_date TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        # 4. 消息使用场景表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_usage_scenarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                proto_message_name TEXT NOT NULL,
                usage_scenario TEXT,
                used_in_messages TEXT,
                usage_description TEXT,
                batch_id INTEGER,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        conn.commit()
        conn.close()
        
        print("✅ 成功创建追溯表")
    
    def extract_migration_reason(self, proto_message: str, batch_id: int) -> Optional[str]:
        """从批次文档中提取迁移原因"""
        if not batch_id:
            return None
        
        batch_plan_file = self.batch_docs_dir / f'batch_{batch_id}' / '01_迁移计划.md'
        batch_result_file = self.batch_docs_dir / f'batch_{batch_id}' / '02_迁移结果.md'
        
        reasons = []
        
        # 从迁移计划中提取
        if batch_plan_file.exists():
            with open(batch_plan_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 查找消息相关的描述
            if proto_message in content:
                # 提取上下文
                lines = content.split('\n')
                for i, line in enumerate(lines):
                    if proto_message in line:
                        # 获取前后几行
                        context_start = max(0, i - 3)
                        context_end = min(len(lines), i + 4)
                        context = '\n'.join(lines[context_start:context_end])
                        reasons.append(f"迁移计划: {context}")
                        break
        
        # 从迁移结果中提取
        if batch_result_file.exists():
            with open(batch_result_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            if proto_message in content:
                # 查找问题描述和解决方案
                problem_match = re.search(r'问题描述[：:]\s*([^\n]+)', content)
                if problem_match:
                    reasons.append(f"问题描述: {problem_match.group(1)}")
                
                solution_match = re.search(r'解决方案[：:]\s*([^\n]+)', content)
                if solution_match:
                    reasons.append(f"解决方案: {solution_match.group(1)}")
        
        return '\n'.join(reasons) if reasons else None
    
    def analyze_message_creation(self, proto_message: str) -> Dict:
        """分析消息的创建原因"""
        creation_info = {
            'proto_message_name': proto_message,
            'creation_type': None,
            'created_from': None,
            'migration_reason': None,
            'batch_id': None,
            'migration_date': None
        }
        
        # 查找批次信息
        conn = self._connect()
        cursor = conn.cursor()
        
        # 检查是否在映射表中
        cursor.execute('''
            SELECT jprotobuf_message_name, batch_id 
            FROM jprotobuf_proto_mappings 
            WHERE proto_message_name = ?
        ''', (proto_message,))
        
        result = cursor.fetchone()
        if result:
            jprotobuf_name = result[0]
            batch_id = result[1]
            
            if jprotobuf_name:
                # 直接映射
                creation_info['creation_type'] = 'direct_mapping'
                creation_info['created_from'] = jprotobuf_name
                creation_info['batch_id'] = batch_id
            else:
                # 可能是新增消息
                creation_info['creation_type'] = 'new_message'
                creation_info['batch_id'] = batch_id
        else:
            # 未找到映射，可能是新增消息
            creation_info['creation_type'] = 'new_message'
        
        conn.close()
        
        # 提取迁移原因
        if creation_info['batch_id']:
            creation_info['migration_reason'] = self.extract_migration_reason(
                proto_message, 
                creation_info['batch_id']
            )
        
        return creation_info
    
    def analyze_message_dependencies(self, proto_message: str) -> List[Dict]:
        """分析消息的依赖关系"""
        dependencies = []
        
        proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        
        for proto_file in proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 查找消息定义
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
                        dependencies.append({
                            'parent_message': proto_message,
                            'child_message': field_type,
                            'dependency_type': 'field'
                        })
        
        return dependencies
    
    def populate_traceability_data(self):
        """填充追溯数据"""
        print("🔍 正在分析所有标准Protobuf消息...")
        
        # 获取所有proto消息
        proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        proto_messages = set()
        
        for proto_file in proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                proto_messages.add(match.group(1))
        
        print(f"  找到 {len(proto_messages)} 个消息")
        
        conn = self._connect()
        cursor = conn.cursor()
        
        # 分析每个消息
        for i, proto_message in enumerate(proto_messages):
            if (i + 1) % 100 == 0:
                print(f"  进度: {i + 1}/{len(proto_messages)}")
            
            # 1. 分析创建原因
            creation_info = self.analyze_message_creation(proto_message)
            
            # 插入迁移历史
            cursor.execute('''
                INSERT OR REPLACE INTO message_migration_history 
                (proto_message_name, migration_reason, created_from, 
                 created_from_type, batch_id, migration_date)
                VALUES (?, ?, ?, ?, ?, ?)
            ''', (
                proto_message,
                creation_info['migration_reason'],
                creation_info['created_from'],
                creation_info['creation_type'],
                creation_info['batch_id'],
                datetime.now().isoformat()
            ))
            
            # 2. 分析依赖关系
            dependencies = self.analyze_message_dependencies(proto_message)
            for dep in dependencies:
                cursor.execute('''
                    INSERT OR IGNORE INTO message_dependencies 
                    (parent_message, child_message, dependency_type)
                    VALUES (?, ?, ?)
                ''', (dep['parent_message'], dep['child_message'], dep['dependency_type']))
        
        conn.commit()
        conn.close()
        
        print(f"✅ 成功填充 {len(proto_messages)} 个消息的追溯数据")
    
    def query_message_traceability(self, proto_message: str) -> Dict:
        """查询消息的追溯信息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 1. 获取迁移历史
        cursor.execute('''
            SELECT * FROM message_migration_history 
            WHERE proto_message_name = ?
        ''', (proto_message,))
        
        history = cursor.fetchone()
        
        # 2. 获取依赖关系
        cursor.execute('''
            SELECT child_message, dependency_type 
            FROM message_dependencies 
            WHERE parent_message = ?
        ''', (proto_message,))
        
        dependencies = cursor.fetchall()
        
        # 3. 获取使用场景
        cursor.execute('''
            SELECT usage_scenario, used_in_messages, usage_description 
            FROM message_usage_scenarios 
            WHERE proto_message_name = ?
        ''', (proto_message,))
        
        usage_scenarios = cursor.fetchall()
        
        conn.close()
        
        return {
            'message_name': proto_message,
            'migration_history': history,
            'dependencies': dependencies,
            'usage_scenarios': usage_scenarios
        }
    
    def generate_traceability_report(self, proto_message: str):
        """生成追溯报告"""
        traceability = self.query_message_traceability(proto_message)
        
        print("=" * 80)
        print(f"消息追溯报告: {proto_message}")
        print("=" * 80)
        print()
        
        # 迁移历史
        if traceability['migration_history']:
            history = traceability['migration_history']
            print("📜 迁移历史")
            print("-" * 80)
            print(f"  创建类型: {history[4]}")
            if history[3]:
                print(f"  创建来源: {history[3]}")
            if history[5]:
                print(f"  批次ID: {history[5]}")
            if history[6]:
                print(f"  迁移日期: {history[6]}")
            if history[2]:
                print(f"  迁移原因:")
                for line in history[2].split('\n'):
                    print(f"    {line}")
            print()
        
        # 依赖关系
        if traceability['dependencies']:
            print("🔗 依赖关系")
            print("-" * 80)
            for dep in traceability['dependencies']:
                print(f"  {dep[0]} (类型: {dep[1]})")
            print()
        
        # 使用场景
        if traceability['usage_scenarios']:
            print("📋 使用场景")
            print("-" * 80)
            for scenario in traceability['usage_scenarios']:
                if scenario[0]:
                    print(f"  场景: {scenario[0]}")
                if scenario[1]:
                    print(f"  使用于: {scenario[1]}")
                if scenario[2]:
                    print(f"  描述: {scenario[2]}")
            print()

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='迁移追溯系统')
    parser.add_argument('--init', action='store_true', help='初始化追溯表')
    parser.add_argument('--populate', action='store_true', help='填充追溯数据')
    parser.add_argument('--query', type=str, help='查询消息追溯信息')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    system = MigrationTraceabilitySystem(db_path)
    
    if args.init:
        system.create_traceability_tables()
    elif args.populate:
        system.populate_traceability_data()
    elif args.query:
        system.generate_traceability_report(args.query)
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
