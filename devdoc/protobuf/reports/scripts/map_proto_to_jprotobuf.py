#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
通过proto消息定义反查对应的JProtobuf Java文件
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict, Optional, Tuple
from collections import defaultdict
from datetime import datetime

class ProtoToJprotobufMapper:
    """通过proto消息定义反查对应的JProtobuf Java文件"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        self.java_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def scan_proto_messages(self) -> List[Dict]:
        """扫描proto文件中的所有消息定义"""
        messages = []
        
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取module_id
            module_id = None
            module_match = re.search(r'module[=:]\s*(\d+)', content)
            if module_match:
                module_id = int(module_match.group(1))
            
            # 提取所有message定义
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                msg_name = match.group(1)
                
                # 判断消息类型
                message_type = 'UNKNOWN'
                if 'Request' in msg_name:
                    message_type = 'REQ'
                elif 'Response' in msg_name:
                    message_type = 'RES'
                elif msg_name.startswith('PT_') or msg_name.startswith('P'):
                    message_type = 'PT'
                
                messages.append({
                    'proto_message_name': msg_name,
                    'proto_file': proto_file.name,
                    'proto_file_path': f"proto/dnf/v1/{proto_file.name}",
                    'module_id': module_id,
                    'message_type': message_type
                })
        
        return messages
    
    def infer_jprotobuf_name(self, proto_msg_name: str, message_type: str) -> List[str]:
        """根据proto消息名推断可能的JProtobuf消息名"""
        possible_names = []
        
        # 去掉Request/Response后缀
        base_name = proto_msg_name.replace('Request', '').replace('Response', '')
        
        # 转换为大写
        base_name_upper = base_name.upper()
        
        # 根据消息类型添加前缀
        if message_type == 'REQ':
            possible_names.append(f"REQ_{base_name_upper}")
        elif message_type == 'RES':
            possible_names.append(f"RES_{base_name_upper}")
        elif message_type == 'PT':
            possible_names.append(f"PT_{base_name_upper}")
        
        # 添加一些常见的变体
        possible_names.append(base_name_upper)
        
        return possible_names
    
    def find_jprotobuf_file(self, possible_names: List[str]) -> Optional[Tuple[str, int]]:
        """查找对应的JProtobuf Java文件"""
        for name in possible_names:
            java_file = self.java_dir / f"{name}.java"
            if java_file.exists():
                # 读取文件获取ModuleID
                module_id = None
                try:
                    with open(java_file, 'r', encoding='utf-8') as f:
                        content = f.read()
                    meta_match = re.search(r'@MessageMeta\s*\(\s*module\s*=\s*(\d+)', content)
                    if meta_match:
                        module_id = int(meta_match.group(1))
                except:
                    pass
                
                return (name, module_id)
        
        return None
    
    def build_mappings(self, proto_messages: List[Dict]) -> List[Dict]:
        """构建映射关系"""
        mappings = []
        
        # 获取现有的映射关系
        conn = self._connect()
        cursor = conn.cursor()
        cursor.execute('SELECT jprotobuf_message_name, proto_message_name FROM jprotobuf_proto_mappings')
        existing_mappings = {row[0]: row[1] for row in cursor.fetchall()}
        conn.close()
        
        for proto_msg in proto_messages:
            proto_msg_name = proto_msg['proto_message_name']
            
            # 检查是否已经有映射
            if proto_msg_name in existing_mappings.values():
                continue
            
            # 推断可能的JProtobuf消息名
            possible_names = self.infer_jprotobuf_name(proto_msg_name, proto_msg['message_type'])
            
            # 查找对应的JProtobuf文件
            jprotobuf_info = self.find_jprotobuf_file(possible_names)
            
            if jprotobuf_info:
                jprotobuf_name, jprotobuf_module_id = jprotobuf_info
                
                mapping = {
                    'jprotobuf_message_name': jprotobuf_name,
                    'jprotobuf_file_path': f"src/main/java/com/dnfm/mina/protobuf/{jprotobuf_name}.java",
                    'proto_message_name': proto_msg_name,
                    'proto_file_path': proto_msg['proto_file_path'],
                    'is_migrated': 1,
                    'module_id': jprotobuf_module_id or proto_msg['module_id'],
                    'message_type': proto_msg['message_type'],
                    'batch_id': None  # 需要根据module_id确定
                }
                
                mappings.append(mapping)
        
        return mappings
    
    def update_database(self, mappings: List[Dict]):
        """更新数据库"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取批次信息
        cursor.execute('SELECT id, batch_number FROM batches')
        batches = {row[1]: row[0] for row in cursor.fetchall()}
        
        # ModuleID到批次的映射
        module_to_batch = {
            10000: 1, 10006: 2, 10002: 3, 10008: 4, 10001: 5, 10009: 6, 10012: 6,
            10014: 7, 10017: 7, 10031: 7, 10032: 7, 10100: 8, 10103: 8, 10106: 8,
            10107: 8, 10108: 8, 10109: 8, 15001: 9, 15002: 9, 15003: 9, 15004: 9,
            15005: 9, 15006: 9, 14000: 10, 14001: 10, 14002: 10, 14003: 10, 14006: 10,
            14017: 10, 16000: 11, 16001: 11, 17000: 12, 17001: 12, 17002: 12, 17003: 12,
            18000: 13, 18001: 13, 18002: 13, 18003: 13, 18004: 13, 18005: 13, 18006: 13,
            18007: 13, 18008: 13, 18009: 13
        }
        
        updated_count = 0
        inserted_count = 0
        
        for mapping in mappings:
            jprotobuf_name = mapping['jprotobuf_message_name']
            
            # 确定批次ID
            module_id = mapping['module_id']
            batch_number = module_to_batch.get(module_id) if module_id else None
            batch_id = batches.get(batch_number) if batch_number else None
            
            # 检查是否已存在
            cursor.execute('SELECT id FROM jprotobuf_proto_mappings WHERE jprotobuf_message_name = ?', (jprotobuf_name,))
            existing = cursor.fetchone()
            
            if existing:
                # 更新现有记录
                cursor.execute('''
                    UPDATE jprotobuf_proto_mappings 
                    SET proto_message_name = ?, proto_file_path = ?, 
                        is_migrated = 1, module_id = ?, message_type = ?, batch_id = ?
                    WHERE jprotobuf_message_name = ?
                ''', (
                    mapping['proto_message_name'],
                    mapping['proto_file_path'],
                    module_id,
                    mapping['message_type'],
                    batch_id,
                    jprotobuf_name
                ))
                if cursor.rowcount > 0:
                    updated_count += 1
            else:
                # 插入新记录
                now = datetime.now().isoformat()
                cursor.execute('''
                    INSERT INTO jprotobuf_proto_mappings 
                    (jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                     proto_file_path, is_migrated, module_id, message_type, batch_id, created_at, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                ''', (
                    jprotobuf_name,
                    mapping['jprotobuf_file_path'],
                    mapping['proto_message_name'],
                    mapping['proto_file_path'],
                    1,
                    module_id,
                    mapping['message_type'],
                    batch_id,
                    now,
                    now
                ))
                inserted_count += 1
        
        conn.commit()
        conn.close()
        
        print(f"✅ 更新了 {updated_count} 条记录")
        print(f"✅ 插入了 {inserted_count} 条新记录")
    
    def run(self):
        """执行映射操作"""
        print("🔍 正在扫描proto文件中的消息定义...")
        proto_messages = self.scan_proto_messages()
        print(f"  找到 {len(proto_messages)} 个proto消息")
        
        print("\n🔗 正在构建映射关系...")
        mappings = self.build_mappings(proto_messages)
        print(f"  构建了 {len(mappings)} 条新映射")
        
        if mappings:
            print("\n💾 正在更新数据库...")
            self.update_database(mappings)
        else:
            print("\n❌ 未找到任何新映射")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    
    mapper = ProtoToJprotobufMapper(db_path)
    mapper.run()

if __name__ == '__main__':
    main()
