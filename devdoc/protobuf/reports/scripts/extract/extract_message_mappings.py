#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
提取并填充消息文件映射数据
"""

import sqlite3
import re
import os
from pathlib import Path
from dataclasses import dataclass
from typing import List, Optional, Tuple

@dataclass
class ProtoMessage:
    """Proto消息定义"""
    name: str
    module_id: Optional[int]
    cmd_id: Optional[int]
    proto_file: str

@dataclass
class OldJavaMessage:
    """旧Java消息类"""
    name: str
    message_type: str  # REQ/RES/PT/NOTIFY
    module_id: Optional[int]
    java_file: str

@dataclass
class MessageMapping:
    """消息映射关系"""
    batch_id: int
    module_id: int
    cmd_id: int
    old_message_name: str
    old_message_type: str
    old_java_file: str
    new_message_name: str
    new_proto_file: str
    new_java_file: str
    new_go_file: str
    implementation_status: str

class MessageMappingExtractor:
    """消息映射提取器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def extract_proto_messages(self) -> List[ProtoMessage]:
        """从proto文件提取消息定义"""
        proto_dir = self.project_root / 'proto' / 'dnf' / 'v1'
        messages = []
        
        for proto_file in proto_dir.glob('*.proto'):
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
                # 在message定义前查找注释
                before_text = content[:match.start()]
                lines = before_text.split('\n')
                
                msg_module = module_id
                msg_cmd = None
                
                # 向上查找最近5行
                for i in range(1, min(6, len(lines) + 1)):
                    line = lines[-i]
                    if not msg_module:
                        mod_match = re.search(r'module[=:]\s*(\d+)', line)
                        if mod_match:
                            msg_module = int(mod_match.group(1))
                    
                    cmd_match = re.search(r'cmd[=:]\s*(\d+)', line)
                    if cmd_match:
                        msg_cmd = int(cmd_match.group(1))
                        break
                
                messages.append(ProtoMessage(
                    name=msg_name,
                    module_id=msg_module,
                    cmd_id=msg_cmd,
                    proto_file=f"proto/dnf/v1/{proto_file.name}"
                ))
        
        return messages
    
    def extract_old_java_messages(self) -> List[OldJavaMessage]:
        """从Java源码提取旧消息类"""
        java_dir = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'protobuf'
        messages = []
        
        for java_file in java_dir.glob('*.java'):
            filename = java_file.name
            
            # 判断消息类型
            message_type = None
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
            
            messages.append(OldJavaMessage(
                name=filename.replace('.java', ''),
                message_type=message_type,
                module_id=module_id,
                java_file=f"src/main/java/com/dnfm/mina/protobuf/{filename}"
            ))
        
        return messages
    
    def check_implementation_status(self, message_name: str) -> str:
        """检查消息的编解码器实现状态"""
        decoder_file = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'codec' / 'StandardProtobufDecoder.java'
        encoder_file = self.project_root / 'src' / 'main' / 'java' / 'com' / 'dnfm' / 'mina' / 'codec' / 'StandardProtobufEncoder.java'
        
        # 构建适配方法名
        # 例如: LoginRequest -> adaptLoginRequest
        # REQ_LOGIN -> adaptLoginRequest
        if message_name.startswith('REQ_'):
            base_name = message_name[4:]
        elif message_name.startswith('RES_'):
            base_name = message_name[4:]
        elif message_name.startswith('PT_'):
            base_name = message_name[3:]
        else:
            base_name = message_name
        
        # 尝试不同的命名转换
        method_name_variants = [
            f"adapt{base_name}Request",
            f"adapt{base_name}Response",
            f"adapt{base_name}",
        ]
        
        try:
            with open(decoder_file, 'r', encoding='utf-8') as f:
                decoder_content = f.read()
            with open(encoder_file, 'r', encoding='utf-8') as f:
                encoder_content = f.read()
            
            for method_name in method_name_variants:
                # 检查Decoder中是否存在该方法
                decoder_pattern = rf'private\s+Message\s+{method_name}\s*\('
                encoder_pattern = rf'private\s+byte\[\]\s+{method_name}\s*\('
                
                decoder_match = re.search(decoder_pattern, decoder_content)
                encoder_match = re.search(encoder_pattern, encoder_content)
                
                if decoder_match or encoder_match:
                    # 检查是否是简化实现（返回null）
                    if decoder_match:
                        # 查找方法体
                        method_start = decoder_match.end()
                        brace_count = 0
                        method_body = ""
                        for i, char in enumerate(decoder_content[method_start:]):
                            method_body += char
                            if char == '{':
                                brace_count += 1
                            elif char == '}':
                                brace_count -= 1
                                if brace_count == 0:
                                    break
                        
                        if 'return null' in method_body or 'return null;' in method_body:
                            return 'simplified'
                    
                    return 'complete'
            
            return 'missing'
        except:
            return 'unknown'
    
    def build_mappings(self, proto_messages: List[ProtoMessage], 
                      old_messages: List[OldJavaMessage]) -> List[MessageMapping]:
        """构建消息映射关系"""
        mappings = []
        
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取批次信息
        cursor.execute('SELECT id, batch_name, batch_number FROM batches')
        batches = {row[2]: {'id': row[0], 'name': row[1]} for row in cursor.fetchall()}
        
        # ModuleID到批次的映射（基于已知的批次划分）
        module_to_batch = {
            10000: 1,   # batch_01 - LOGIN
            10006: 2,   # batch_02 - SESSION
            10002: 3,   # batch_03 - CHARACTER
            10003: 4,   # batch_04 - CREATE_CHARACTER
            10008: 4,   # batch_04 - CHANNEL
            10011: 4,   # batch_04 - ENTER_CHANNEL
            10001: 5,   # batch_05 - STANDBY
            10004: 5,   # batch_05 - REMOVE_CHARACTER
            10005: 5,   # batch_05 - START_GAME
            10007: 5,   # batch_05 - EXIT_CHARACTER
            10009: 6,   # batch_06 - AUTHKEY_REFRESH
            10012: 6,   # batch_06 - PLATFORM_PROFILE
            10014: 7,   # batch_07 - BATTLE_SERVER
            10017: 7,   # batch_07 - IDIP
            10031: 7,   # batch_07 - SERVER_DATA
            10032: 7,   # batch_07 - SERVER_DATA
            10100: 8,   # batch_08 - TOWN
            10103: 8,   # batch_08 - CHARACTER_INFO
            10106: 8,   # batch_08 - TOWN_USER_GUID
            10107: 8,   # batch_08 - TARGET_USER_DETAIL
            10108: 8,   # batch_08 - INTERACTION_MENU
            10109: 8,   # batch_08 - LEAVE_FROM_TOWN
            15001: 9,   # batch_09 - MAIL
            15002: 9,
            15003: 9,
            15004: 9,
            15005: 9,
            15006: 9,
            14000: 10,  # batch_10 - ITEM
            14001: 10,
            14002: 10,
            14003: 10,
            14006: 10,
            14017: 10,
            16000: 11,  # batch_11 - SKILL
            16001: 11,
            17000: 12,  # batch_12 - ACHIEVEMENT
            17001: 12,
            17002: 12,
            17003: 12,
            18000: 13,  # batch_13 - ADVENTURE
            18001: 13,
            18002: 13,
            18003: 13,
            18004: 13,
            18005: 13,
            18006: 13,
            18007: 13,
            18008: 13,
            18009: 13,
        }
        
        conn.close()
        
        # 先建立旧消息的查找字典
        old_msg_dict = {}
        for old_msg in old_messages:
            key = (old_msg.module_id, old_msg.message_type)
            if key not in old_msg_dict:
                old_msg_dict[key] = []
            old_msg_dict[key].append(old_msg)
        
        # 遍历proto消息，建立映射
        for proto_msg in proto_messages:
            if proto_msg.module_id is None:
                continue
            
            # 确定批次
            batch_number = module_to_batch.get(proto_msg.module_id, 1)
            batch_info = batches.get(batch_number, {'id': None, 'name': f'batch_{batch_number:02d}'})
            
            # 判断消息类型
            message_type = 'UNKNOWN'
            if 'Request' in proto_msg.name:
                message_type = 'REQ'
            elif 'Response' in proto_msg.name:
                message_type = 'RES'
            elif proto_msg.name.startswith('PT_') or proto_msg.name.startswith('P'):
                message_type = 'PT'
            
            # 查找对应的旧消息
            old_msg_name = ''
            old_msg_file = ''
            
            # 尝试匹配
            key = (proto_msg.module_id, message_type)
            if key in old_msg_dict:
                for old_msg in old_msg_dict[key]:
                    # 简单的名称匹配
                    old_base = old_msg.name.replace('REQ_', '').replace('RES_', '').replace('PT_', '')
                    new_base = proto_msg.name.replace('Request', '').replace('Response', '')
                    
                    if old_base.lower() in new_base.lower() or new_base.lower() in old_base.lower():
                        old_msg_name = old_msg.name
                        old_msg_file = old_msg.java_file
                        break
            
            # 如果没有匹配到，构造默认名称
            if not old_msg_name:
                prefix = 'REQ_' if message_type == 'REQ' else 'RES_' if message_type == 'RES' else 'PT_'
                old_msg_name = f"{prefix}{proto_msg.name.upper().replace('REQUEST', '').replace('RESPONSE', '')}"
            
            # 检查实现状态
            impl_status = self.check_implementation_status(proto_msg.name)
            
            # 构建生成文件路径
            new_java_file = f"proto/gen/java/com/dnfm/mina/protobuf/generated/{proto_msg.name}.java"
            proto_filename = proto_msg.proto_file.split('/')[-1].replace('.proto', '')
            new_go_file = f"dnf-go-client/gen/dnf/v1/{proto_filename}.pb.go"
            
            mapping = MessageMapping(
                batch_id=batch_info['id'] or batch_number,
                module_id=proto_msg.module_id or 0,
                cmd_id=proto_msg.cmd_id or 0,
                old_message_name=old_msg_name,
                old_message_type=message_type,
                old_java_file=old_msg_file or f"src/main/java/com/dnfm/mina/protobuf/{old_msg_name}.java",
                new_message_name=proto_msg.name,
                new_proto_file=proto_msg.proto_file,
                new_java_file=new_java_file,
                new_go_file=new_go_file,
                implementation_status=impl_status
            )
            
            mappings.append(mapping)
        
        return mappings
    
    def save_mappings(self, mappings: List[MessageMapping]):
        """保存映射关系到数据库"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 清空现有数据
        cursor.execute('DELETE FROM message_file_mappings')
        
        # 插入新数据
        for mapping in mappings:
            cursor.execute('''
                INSERT INTO message_file_mappings 
                (batch_id, module_id, cmd_id, old_message_name, old_message_type,
                 old_java_file, new_message_name, new_proto_file, new_java_file,
                 new_go_file, implementation_status)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            ''', (
                mapping.batch_id, mapping.module_id, mapping.cmd_id,
                mapping.old_message_name, mapping.old_message_type,
                mapping.old_java_file, mapping.new_message_name,
                mapping.new_proto_file, mapping.new_java_file,
                mapping.new_go_file, mapping.implementation_status
            ))
        
        conn.commit()
        conn.close()
        
        print(f"✅ 已保存 {len(mappings)} 条消息映射关系")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/migration_progress.db'
    
    extractor = MessageMappingExtractor(db_path)
    
    print("🔍 正在提取proto消息定义...")
    proto_messages = extractor.extract_proto_messages()
    print(f"  找到 {len(proto_messages)} 个proto消息")
    
    print("🔍 正在提取旧Java消息类...")
    old_messages = extractor.extract_old_java_messages()
    print(f"  找到 {len(old_messages)} 个旧Java消息")
    
    print("🔗 正在构建消息映射关系...")
    mappings = extractor.build_mappings(proto_messages, old_messages)
    
    print("💾 正在保存到数据库...")
    extractor.save_mappings(mappings)
    
    # 统计
    complete = sum(1 for m in mappings if m.implementation_status == 'complete')
    simplified = sum(1 for m in mappings if m.implementation_status == 'simplified')
    missing = sum(1 for m in mappings if m.implementation_status == 'missing')
    
    print("\n📊 实现状态统计:")
    print(f"  ✅ 完整实现: {complete}")
    print(f"  ⚠️  简化实现: {simplified}")
    print(f"  ❌ 缺失实现: {missing}")
    print(f"  📊 总计: {len(mappings)}")

if __name__ == '__main__':
    main()
