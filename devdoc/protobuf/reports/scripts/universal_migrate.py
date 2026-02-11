#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
通用批量迁移脚本
迁移所有剩余的 REQ 消息
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict

class UniversalMigrator:
    """通用批量迁移器"""
    
    def __init__(self, db_path: str, project_root: Path):
        self.db_path = db_path
        self.project_root = project_root
        self.jprotobuf_dir = project_root / 'src/main/java/com/dnfm/mina/protobuf'
    
    def _connect(self):
        """连接到数据库"""
        conn = sqlite3.connect(self.db_path)
        conn.row_factory = sqlite3.Row
        return conn
    
    def get_unmigrated_messages(self) -> List[Dict]:
        """获取所有未迁移的消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT 
                jm.message_name,
                jm.file_path,
                jm.module_id,
                jm.field_count
            FROM jprotobuf_messages jm
            WHERE jm.message_type = 'REQ' 
            AND jm.id NOT IN (SELECT mr.jprotobuf_message_id FROM migration_records mr)
            ORDER BY jm.module_id, jm.message_name
        ''')
        
        results = [dict(row) for row in cursor.fetchall()]
        conn.close()
        
        return results
    
    def get_module_name(self, module_id: int) -> str:
        """根据 ModuleID 获取模块名"""
        if module_id is None:
            return 'UNKNOWN'
        if 10000 <= module_id < 11000:
            return 'BASIC'
        elif 11000 <= module_id < 12000:
            return 'CHARACTER'
        elif 12000 <= module_id < 13000:
            return 'DUNGEON'
        elif 13000 <= module_id < 14000:
            return 'TOWN'
        elif 14000 <= module_id < 15000:
            return 'ITEM'
        elif 15000 <= module_id < 16000:
            return 'EQUIP'
        elif 16000 <= module_id < 17000:
            return 'SKILL'
        elif 17000 <= module_id < 18000:
            return 'TASK'
        elif 18000 <= module_id < 19000:
            return 'SOCIAL'
        elif 19000 <= module_id < 20000:
            return 'GUILD'
        elif 20000 <= module_id < 21000:
            return 'AUCTION'
        elif 21000 <= module_id < 22000:
            return 'MALL'
        elif 22000 <= module_id < 23000:
            return 'PAYMENT'
        elif 23000 <= module_id < 24000:
            return 'CROSS_SERVER'
        elif 24000 <= module_id < 25000:
            return 'BATTLE'
        else:
            return 'OTHER'
    
    def convert_field_type(self, jprotobuf_type: str) -> str:
        """转换 JProtobuf 字段类型到 Protobuf 字段类型"""
        type_map = {
            'STRING': 'string',
            'INT32': 'int32',
            'INT64': 'int64',
            'UINT32': 'uint32',
            'UINT64': 'uint64',
            'FLOAT': 'float',
            'DOUBLE': 'double',
            'BOOL': 'bool',
            'OBJECT': 'message',
            'BYTES': 'bytes'
        }
        return type_map.get(jprotobuf_type, 'string')
    
    def convert_message_name(self, jprotobuf_name: str) -> str:
        """转换 JProtobuf 消息名到 Protobuf 消息名"""
        # 移除 REQ_ 前缀
        name = jprotobuf_name.replace('REQ_', '')
        
        # 转换为 PascalCase
        parts = name.split('_')
        pascal_case = ''.join(part.capitalize() for part in parts)
        
        # 添加 Request 后缀
        return pascal_case + 'Request'
    
    def read_jprotobuf_file(self, file_path: str) -> Dict:
        """读取 JProtobuf 文件"""
        full_path = self.project_root / file_path
        if not full_path.exists():
            return {}
        
        with open(full_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        fields = []
        field_pattern = r'@Protobuf\(\s*fieldType\s*=\s*FieldType\.(\w+),\s*order\s*=\s*(\d+)'
        matches = re.findall(field_pattern, content)
        
        for match in matches:
            field_type, order = match
            fields.append({
                'type': field_type,
                'order': int(order)
            })
        
        return {'fields': fields}
    
    def create_proto_file(self, module_name: str, messages: List[Dict]):
        """创建 proto 文件"""
        proto_file = self.project_root / f'proto/dnf/v1/{module_name.lower()}.proto'
        
        # 检查文件是否已存在
        if proto_file.exists():
            # 追加模式
            mode = 'a'
            header = ''
        else:
            # 新建模式
            mode = 'w'
            header = f'''syntax = "proto3";

package dnf.v1;

option java_package = "com.dnfm.mina.stdproto";
option java_outer_classname = "{module_name.capitalize()}Proto";
option go_package = "dnf/v1";

'''
        
        with open(proto_file, mode, encoding='utf-8') as f:
            if header:
                f.write(header)
            
            for msg in messages:
                proto_message_name = self.convert_message_name(msg['message_name'])
                jprotobuf_file = self.read_jprotobuf_file(msg['file_path'])
                
                f.write(f'\n// {msg["message_name"]}\n')
                f.write(f'message {proto_message_name} {{\n')
                
                if jprotobuf_file.get('fields'):
                    for field in jprotobuf_file['fields']:
                        proto_type = self.convert_field_type(field['type'])
                        f.write(f'  {proto_type} field_{field["order"]} = {field["order"]};\n')
                else:
                    f.write(f'  // No fields\n')
                
                f.write('}\n')
        
        print(f"✅ 已创建/更新 {proto_file.name}，包含 {len(messages)} 个消息")
        return proto_file
    
    def update_database(self, batch_id: int, module_name: str, messages: List[Dict]):
        """更新数据库"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 添加批次
        cursor.execute('''
            INSERT OR IGNORE INTO migration_batches (batch_number, batch_name, description, status, start_time)
            VALUES (?, ?, ?, 'completed', CURRENT_TIMESTAMP)
        ''', (batch_id, module_name, f'{module_name} 模块迁移 - 批量迁移'))
        
        proto_file_path = f'proto/dnf/v1/{module_name.lower()}.proto'
        
        for msg in messages:
            proto_message_name = self.convert_message_name(msg['message_name'])
            
            # 添加标准 Protobuf 消息
            cursor.execute('''
                INSERT OR IGNORE INTO proto_messages (message_name, file_path, package_name, field_count)
                VALUES (?, ?, ?, ?)
            ''', (proto_message_name, proto_file_path, 'dnf.v1', msg['field_count']))
            
            # 获取消息 ID
            cursor.execute('SELECT id FROM jprotobuf_messages WHERE message_name = ?', (msg['message_name'],))
            jprotobuf_row = cursor.fetchone()
            
            if jprotobuf_row:
                jprotobuf_id = jprotobuf_row['id']
                
                cursor.execute('SELECT id FROM proto_messages WHERE message_name = ?', (proto_message_name,))
                proto_row = cursor.fetchone()
                
                if proto_row:
                    proto_id = proto_row['id']
                    
                    # 添加映射关系
                    cursor.execute('''
                        INSERT OR IGNORE INTO message_mappings (jprotobuf_message_id, proto_message_id, mapping_type, mapping_confidence, is_verified)
                        VALUES (?, ?, 'direct_mapping', 1.0, 1)
                    ''', (jprotobuf_id, proto_id))
                    
                    # 添加迁移记录
                    cursor.execute('''
                        INSERT OR IGNORE INTO migration_records (batch_id, jprotobuf_message_id, proto_message_id, migration_status)
                        VALUES (?, ?, ?, 'completed')
                    ''', (batch_id, jprotobuf_id, proto_id))
        
        conn.commit()
        conn.close()
        print(f"✅ 已更新数据库，添加 {len(messages)} 个迁移记录")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    project_root = Path('/home/pix/dev/code/java/DnfGameServer')
    
    migrator = UniversalMigrator(db_path, project_root)
    
    print("📝 通用批量迁移所有剩余消息...")
    print()
    
    # 获取所有未迁移的消息
    messages = migrator.get_unmigrated_messages()
    print(f"📊 找到 {len(messages)} 个未迁移的消息")
    print()
    
    if len(messages) == 0:
        print("✅ 所有消息已迁移完成！")
        return
    
    # 按模块分组
    modules = {}
    for msg in messages:
        module_id = msg['module_id']
        module_name = migrator.get_module_name(module_id)
        
        if module_name not in modules:
            modules[module_name] = []
        modules[module_name].append(msg)
    
    # 按模块迁移
    batch_start = 9  # 从批次 9 开始
    for module_name, module_messages in sorted(modules.items()):
        print(f"🔄 迁移模块: {module_name} ({len(module_messages)} 个消息)")
        
        # 分批处理，每批 20 个
        batch_size = 20
        for i in range(0, len(module_messages), batch_size):
            batch_messages = module_messages[i:i+batch_size]
            batch_num = batch_start + (i // batch_size)
            
            # 创建/更新 proto 文件
            migrator.create_proto_file(module_name, batch_messages)
            
            # 更新数据库
            migrator.update_database(batch_num, module_name, batch_messages)
            
            print()
        
        batch_start += (len(module_messages) + batch_size - 1) // batch_size
    
    print("✅ 所有模块批量迁移完成！")

if __name__ == '__main__':
    main()
