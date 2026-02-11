#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
分析标准Protobuf新增消息与JProtobuf消息的对应关系
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Set, Tuple
from collections import defaultdict

class NewProtoMessageAnalyzer:
    """分析标准Protobuf新增消息的来源和用途"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        self.java_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def get_new_proto_messages(self) -> Set[str]:
        """获取标准Protobuf新增的消息"""
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
        return new_messages
    
    def analyze_message_usage(self, proto_message: str) -> Dict:
        """分析消息在proto文件中的使用情况"""
        usage_info = {
            'message_name': proto_message,
            'defined_in': [],
            'used_by': [],
            'is_nested': False,
            'is_repeated': False,
            'is_optional': False
        }
        
        # 查找定义位置
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 查找定义
            message_pattern = rf'message\s+{re.escape(proto_message)}\s*\{{'
            if re.search(message_pattern, content):
                usage_info['defined_in'].append(proto_file.name)
            
            # 查找使用
            usage_pattern = rf'\b{re.escape(proto_message)}\b'
            if re.search(usage_pattern, content) and proto_file.name not in usage_info['defined_in']:
                usage_info['used_by'].append(proto_file.name)
        
        # 分析字段类型
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 检查是否作为repeated字段使用
            repeated_pattern = rf'repeated\s+{re.escape(proto_message)}\s+\w+'
            if re.search(repeated_pattern, content):
                usage_info['is_repeated'] = True
            
            # 检查是否作为optional字段使用
            optional_pattern = rf'optional\s+{re.escape(proto_message)}\s+\w+'
            if re.search(optional_pattern, content):
                usage_info['is_optional'] = True
        
        return usage_info
    
    def find_related_jprotobuf_messages(self, proto_message: str) -> List[str]:
        """查找相关的JProtobuf消息"""
        related_messages = []
        
        # 转换为可能的JProtobuf名称
        base_name = proto_message.replace('Request', '').replace('Response', '')
        base_name_upper = base_name.upper()
        
        # 可能的JProtobuf名称
        possible_names = [
            f"REQ_{base_name_upper}",
            f"RES_{base_name_upper}",
            f"PT_{base_name_upper}",
            base_name_upper,
            proto_message.upper()
        ]
        
        # 检查是否存在
        for name in possible_names:
            java_file = self.java_dir / f"{name}.java"
            if java_file.exists():
                related_messages.append(name)
        
        return related_messages
    
    def analyze_batch_migration(self, proto_message: str) -> Dict:
        """分析批次迁移记录"""
        migration_info = {
            'message_name': proto_message,
            'found_in_batch': None,
            'batch_description': '',
            'migration_date': '',
            'related_jprotobuf_messages': []
        }
        
        # 搜索批次文档
        for batch_file in self.batch_docs_dir.glob('batch_*/01_迁移计划.md'):
            with open(batch_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 搜索消息名
            if proto_message in content:
                # 提取批次号
                batch_match = re.search(r'batch_(\d+)', batch_file.name)
                if batch_match:
                    migration_info['found_in_batch'] = f"batch_{batch_match.group(1)}"
                
                # 提取描述
                desc_match = re.search(r'模块名称\|.*?\|.*?\|([^|]+)', content)
                if desc_match:
                    migration_info['batch_description'] = desc_match.group(1).strip()
                
                # 提取日期
                date_match = re.search(r'\d{4}-\d{2}-\d{2}', content)
                if date_match:
                    migration_info['migration_date'] = date_match.group(0)
                
                break
        
        # 查找相关的JProtobuf消息
        migration_info['related_jprotobuf_messages'] = self.find_related_jprotobuf_messages(proto_message)
        
        return migration_info
    
    def classify_new_message(self, proto_message: str, usage_info: Dict, migration_info: Dict) -> str:
        """分类新增消息的类型"""
        if usage_info['defined_in']:
            if 'Request' in proto_message or 'Response' in proto_message:
                return "请求/响应消息"
            elif proto_message.startswith('PT_'):
                return "数据类型消息"
            elif migration_info['found_in_batch']:
                return "迁移过程中新增的辅助消息"
            else:
                return "数据结构消息"
        else:
            return "未知类型"
    
    def generate_analysis_report(self, limit: int = 50):
        """生成分析报告"""
        print("=" * 80)
        print("标准Protobuf新增消息分析报告")
        print("=" * 80)
        print()
        
        # 获取新增消息
        new_messages = self.get_new_proto_messages()
        print(f"📊 找到 {len(new_messages)} 个标准Protobuf新增消息")
        print()
        
        # 分类统计
        classification = defaultdict(list)
        
        for proto_message in sorted(list(new_messages))[:limit]:
            # 分析使用情况
            usage_info = self.analyze_message_usage(proto_message)
            
            # 分析批次迁移
            migration_info = self.analyze_batch_migration(proto_message)
            
            # 分类
            msg_type = self.classify_new_message(proto_message, usage_info, migration_info)
            classification[msg_type].append(proto_message)
            
            # 打印详细信息
            print(f"【{proto_message}】")
            print(f"  类型: {msg_type}")
            
            if usage_info['defined_in']:
                print(f"  定义文件: {', '.join(usage_info['defined_in'])}")
            else:
                print(f"  定义文件: 未找到")
            
            if usage_info['used_by']:
                print(f"  被使用于: {', '.join(usage_info['used_by'][:5])}")
                if len(usage_info['used_by']) > 5:
                    print(f"              ... 还有 {len(usage_info['used_by']) - 5} 个文件")
            
            if usage_info['is_repeated']:
                print(f"  使用方式: 作为repeated字段")
            elif usage_info['is_optional']:
                print(f"  使用方式: 作为optional字段")
            
            if migration_info['found_in_batch']:
                print(f"  迁移批次: {migration_info['found_in_batch']}")
                if migration_info['batch_description']:
                    print(f"  批次描述: {migration_info['batch_description']}")
            
            if migration_info['related_jprotobuf_messages']:
                print(f"  相关JProtobuf消息: {', '.join(migration_info['related_jprotobuf_messages'])}")
            else:
                print(f"  相关JProtobuf消息: 无")
            
            print()
        
        # 打印分类统计
        print("=" * 80)
        print("分类统计")
        print("=" * 80)
        for msg_type, messages in sorted(classification.items()):
            print(f"{msg_type}: {len(messages)} 个")
        print()
        
        if len(new_messages) > limit:
            print(f"... 还有 {len(new_messages) - limit} 个新增消息未显示")

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='分析标准Protobuf新增消息')
    parser.add_argument('--message', type=str, help='分析特定消息')
    parser.add_argument('--limit', type=int, default=50, help='限制显示结果数量')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    analyzer = NewProtoMessageAnalyzer(db_path)
    
    if args.message:
        # 分析特定消息
        usage_info = analyzer.analyze_message_usage(args.message)
        migration_info = analyzer.analyze_batch_migration(args.message)
        msg_type = analyzer.classify_new_message(args.message, usage_info, migration_info)
        
        print(f"【{args.message}】")
        print(f"  类型: {msg_type}")
        print(f"  定义文件: {', '.join(usage_info['defined_in'])}")
        print(f"  被使用于: {', '.join(usage_info['used_by'][:5])}")
        if migration_info['found_in_batch']:
            print(f"  迁移批次: {migration_info['found_in_batch']}")
        if migration_info['related_jprotobuf_messages']:
            print(f"  相关JProtobuf消息: {', '.join(migration_info['related_jprotobuf_messages'])}")
    else:
        # 生成完整报告
        analyzer.generate_analysis_report(args.limit)

if __name__ == '__main__':
    main()
