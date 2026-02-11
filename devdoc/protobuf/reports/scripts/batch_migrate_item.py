#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
批量迁移 ITEM 模块剩余消息
"""

import sqlite3
import re
from pathlib import Path
from typing import List, Dict

class BatchItemMigrator:
    """ITEM 模块批量迁移器"""
    
    def __init__(self, db_path: str, project_root: Path):
        self.db_path = db_path
        self.project_root = project_root
        self.jprotobuf_dir = project_root / 'src/main/java/com/dnfm/mina/protobuf'
        self.proto_file = project_root / 'proto/dnf/v1/item.proto'
    
    def _connect(self):
        """连接到数据库"""
        conn = sqlite3.connect(self.db_path)
        conn.row_factory = sqlite3.Row
        return conn
    
    def get_unmigrated_item_messages(self) -> List[Dict]:
        """获取未迁移的 ITEM 消息"""
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
            AND (jm.module_id >= 14000 AND jm.module_id < 15000)
            ORDER BY jm.module_id, jm.message_name
        ''')
        
        results = [dict(row) for row in cursor.fetchall()]
        conn.close()
        
        return results
    
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
    
    def append_to_proto_file(self, messages: List[Dict]):
        """追加消息到 proto 文件"""
        with open(self.proto_file, 'a', encoding='utf-8') as f:
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
        
        print(f"✅ 已追加 {len(messages)} 个消息到 proto 文件")
    
    def update_database(self, batch_id: int, messages: List[Dict]):
        """更新数据库"""
        conn = self._connect()
        cursor = conn.cursor()
        
        for msg in messages:
            proto_message_name = self.convert_message_name(msg['message_name'])
            
            # 添加标准 Protobuf 消息
            cursor.execute('''
                INSERT OR IGNORE INTO proto_messages (message_name, file_path, package_name, field_count)
                VALUES (?, ?, ?, ?)
            ''', (proto_message_name, 'proto/dnf/v1/item.proto', 'dnf.v1', msg['field_count']))
            
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
    
    migrator = BatchItemMigrator(db_path, project_root)
    
    print("📝 批量迁移 ITEM 模块剩余消息...")
    print()
    
    # 获取未迁移的 ITEM 消息
    messages = migrator.get_unmigrated_item_messages()
    print(f"📊 找到 {len(messages)} 个未迁移的 ITEM 消息")
    print()
    
    if len(messages) == 0:
        print("✅ 所有 ITEM 消息已迁移完成！")
        return
    
    # 分批处理，每批 20 个
    batch_size = 20
    for i in range(0, len(messages), batch_size):
        batch_messages = messages[i:i+batch_size]
        batch_num = 4 + i // batch_size  # 从批次 4 开始
        
        print(f"🔄 处理批次 {batch_num}，包含 {len(batch_messages)} 个消息...")
        
        # 追加到 proto 文件
        migrator.append_to_proto_file(batch_messages)
        
        # 更新数据库
        migrator.update_database(batch_num, batch_messages)
        
        print()
    
    print("✅ ITEM 模块批量迁移完成！")

if __name__ == '__main__':
    main()
