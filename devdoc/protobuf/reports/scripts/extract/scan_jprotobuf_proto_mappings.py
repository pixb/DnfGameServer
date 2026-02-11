#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
扫描并建立JProtobuf到标准Protobuf消息映射关系
"""

import sqlite3
import re
import os
from pathlib import Path
from dataclasses import dataclass
from typing import List, Optional, Dict
from collections import defaultdict

@dataclass
class JProtobufMessage:
    """JProtobuf消息类"""
    name: str
    file_path: str
    module_id: Optional[int]
    message_type: str

@dataclass
class ProtoMessage:
    """标准Protobuf消息"""
    name: str
    file_path: str
    module_id: Optional[int]
    message_type: str

class JProtobufProtoMapper:
    """JProtobuf到标准Protobuf消息映射器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.java_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
        self.proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def scan_jprotobuf_messages(self) -> List[JProtobufMessage]:
        """扫描JProtobuf消息文件"""
        messages = []
        
        for java_file in self.java_dir.glob('*.java'):
            filename = java_file.name
            
            # 跳过JProtobuf生成的类文件
            if '$$JProtoBufClass' in filename:
                continue
            
            # 判断消息类型
            message_type = 'UNKNOWN'
            if filename.startswith('REQ_'):
                message_type = 'REQ'
            elif filename.startswith('RES_'):
                message_type = 'RES'
            elif filename.startswith('PT_'):
                message_type = 'PT'
            elif filename.startswith('NOTIFY_'):
                message_type = 'NOTIFY'
            else:
                continue
            
            # 读取文件内容提取ModuleID
            module_id = None
            try:
                with open(java_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                # 查找@MessageMeta注解
                meta_match = re.search(r'@MessageMeta\s*\(\s*module\s*=\s*(\d+)', content)
                if meta_match:
                    module_id = int(meta_match.group(1))
            except:
                pass
            
            messages.append(JProtobufMessage(
                name=filename.replace('.java', ''),
                file_path=f"src/main/java/com/dnfm/mina/protobuf/{filename}",
                module_id=module_id,
                message_type=message_type
            ))
        
        return messages
    
    def scan_proto_messages(self) -> List[ProtoMessage]:
        """扫描标准Protobuf消息文件"""
        messages = []
        
        for proto_file in self.proto_dir.glob('*.proto'):
            with open(proto_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取当前module_id（从注释中）
            module_id = None
            module_match = re.search(r'module[=:]\s*(\d+)', content)
            if module_match:
                module_id = int(module_match.group(1))
            
            # 提取所有message定义
            message_pattern = r'message\s+(\w+)\s*\{'
            for match in re.finditer(message_pattern, content):
                msg_name = match.group(1)
                
                # 查找该消息附近的module/cmd注释
                before_text = content[:match.start()]
                lines = before_text.split('\n')
                
                msg_module = module_id
                
                # 向上查找最近5行
                for i in range(1, min(6, len(lines) + 1)):
                    line = lines[-i]
                    if not msg_module:
                        mod_match = re.search(r'module[=:]\s*(\d+)', line)
                        if mod_match:
                            msg_module = int(mod_match.group(1))
                
                # 判断消息类型
                message_type = 'UNKNOWN'
                if 'Request' in msg_name:
                    message_type = 'REQ'
                elif 'Response' in msg_name:
                    message_type = 'RES'
                elif msg_name.startswith('PT_') or msg_name.startswith('P'):
                    message_type = 'PT'
                
                messages.append(ProtoMessage(
                    name=msg_name,
                    file_path=f"proto/dnf/v1/{proto_file.name}",
                    module_id=msg_module,
                    message_type=message_type
                ))
        
        return messages
    
    def match_messages(self, jprotobuf_msgs: List[JProtobufMessage], 
                      proto_msgs: List[ProtoMessage]) -> List[Dict]:
        """匹配JProtobuf和标准Protobuf消息"""
        mappings = []
        
        # 建立标准Protobuf消息的查找字典
        proto_dict = defaultdict(list)
        for proto_msg in proto_msgs:
            if proto_msg.module_id:
                proto_dict[proto_msg.module_id].append(proto_msg)
        
        # 获取批次信息
        conn = self._connect()
        cursor = conn.cursor()
        cursor.execute('SELECT id, batch_number FROM batches')
        batches = {row[1]: row[0] for row in cursor.fetchall()}
        conn.close()
        
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
        
        for jprotobuf_msg in jprotobuf_msgs:
            # 查找匹配的标准Protobuf消息
            matched_proto = None
            
            # 优先通过ModuleID匹配
            if jprotobuf_msg.module_id and jprotobuf_msg.module_id in proto_dict:
                for proto_msg in proto_dict[jprotobuf_msg.module_id]:
                    if proto_msg.message_type == jprotobuf_msg.message_type:
                        # 名称匹配
                        jpb_name = jprotobuf_msg.name.replace('REQ_', '').replace('RES_', '').replace('PT_', '').replace('NOTIFY_', '')
                        proto_name = proto_msg.name.replace('Request', '').replace('Response', '')
                        
                        if jpb_name.lower() in proto_name.lower() or proto_name.lower() in jpb_name.lower():
                            matched_proto = proto_msg
                            break
            
            # 如果没有匹配到，尝试其他匹配策略
            if not matched_proto:
                for proto_msg in proto_msgs:
                    if proto_msg.message_type == jprotobuf_msg.message_type:
                        jpb_name = jprotobuf_msg.name.replace('REQ_', '').replace('RES_', '').replace('PT_', '').replace('NOTIFY_', '')
                        proto_name = proto_msg.name.replace('Request', '').replace('Response', '')
                        
                        if jpb_name.lower() == proto_name.lower():
                            matched_proto = proto_msg
                            break
            
            # 确定批次ID
            batch_id = None
            if jprotobuf_msg.module_id:
                batch_id = module_to_batch.get(jprotobuf_msg.module_id)
                if batch_id:
                    batch_id = batches.get(batch_id)
            
            is_migrated = matched_proto is not None
            
            mapping = {
                'jprotobuf_message_name': jprotobuf_msg.name,
                'jprotobuf_file_path': jprotobuf_msg.file_path,
                'proto_message_name': matched_proto.name if matched_proto else None,
                'proto_file_path': matched_proto.file_path if matched_proto else None,
                'is_migrated': is_migrated,
                'module_id': jprotobuf_msg.module_id,
                'message_type': jprotobuf_msg.message_type,
                'batch_id': batch_id
            }
            
            mappings.append(mapping)
        
        return mappings
    
    def save_mappings(self, mappings: List[Dict]):
        """保存映射关系到数据库"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 清空现有数据
        cursor.execute('DELETE FROM jprotobuf_proto_mappings')
        
        # 插入新数据
        for mapping in mappings:
            cursor.execute('''
                INSERT INTO jprotobuf_proto_mappings 
                (jprotobuf_message_name, jprotobuf_file_path, proto_message_name,
                 proto_file_path, is_migrated, module_id, message_type, batch_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            ''', (
                mapping['jprotobuf_message_name'],
                mapping['jprotobuf_file_path'],
                mapping['proto_message_name'],
                mapping['proto_file_path'],
                mapping['is_migrated'],
                mapping['module_id'],
                mapping['message_type'],
                mapping['batch_id']
            ))
        
        conn.commit()
        conn.close()
        
        print(f"✅ 已保存 {len(mappings)} 条JProtobuf到标准Protobuf消息映射关系")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    
    mapper = JProtobufProtoMapper(db_path)
    
    print("🔍 正在扫描JProtobuf消息文件...")
    jprotobuf_msgs = mapper.scan_jprotobuf_messages()
    print(f"  找到 {len(jprotobuf_msgs)} 个JProtobuf消息")
    
    print("🔍 正在扫描标准Protobuf消息文件...")
    proto_msgs = mapper.scan_proto_messages()
    print(f"  找到 {len(proto_msgs)} 个标准Protobuf消息")
    
    print("🔗 正在建立消息映射关系...")
    mappings = mapper.match_messages(jprotobuf_msgs, proto_msgs)
    
    print("💾 正在保存到数据库...")
    mapper.save_mappings(mappings)
    
    # 统计
    migrated = sum(1 for m in mappings if m['is_migrated'])
    not_migrated = sum(1 for m in mappings if not m['is_migrated'])
    
    print("\n📊 映射统计:")
    print(f"  ✅ 已迁移: {migrated}")
    print(f"  ❌ 未迁移: {not_migrated}")
    print(f"  📊 总计: {len(mappings)}")
    
    # 按消息类型统计
    type_stats = defaultdict(int)
    for m in mappings:
        type_stats[m['message_type']] += 1
    
    print("\n📊 按消息类型统计:")
    for msg_type, count in sorted(type_stats.items()):
        print(f"  {msg_type}: {count}")

if __name__ == '__main__':
    main()
