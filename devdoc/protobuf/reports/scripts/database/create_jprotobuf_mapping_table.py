#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
创建JProtobuf到标准Protobuf消息映射表
"""

import sqlite3
import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

def create_jprotobuf_mapping_table():
    """创建JProtobuf到标准Protobuf消息映射表"""
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()
    
    # 删除旧表（如果存在）
    cursor.execute('DROP TABLE IF EXISTS jprotobuf_proto_mappings')
    
    # 创建JProtobuf到标准Protobuf消息映射表
    cursor.execute('''
        CREATE TABLE jprotobuf_proto_mappings (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            jprotobuf_message_name TEXT NOT NULL,
            jprotobuf_file_path TEXT NOT NULL,
            proto_message_name TEXT,
            proto_file_path TEXT,
            is_migrated BOOLEAN DEFAULT 0,
            module_id INTEGER,
            message_type TEXT,
            batch_id INTEGER,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
    ''')
    
    # 创建索引
    cursor.execute('CREATE INDEX idx_jprotobuf_name ON jprotobuf_proto_mappings(jprotobuf_message_name)')
    cursor.execute('CREATE INDEX idx_proto_name ON jprotobuf_proto_mappings(proto_message_name)')
    cursor.execute('CREATE INDEX idx_is_migrated ON jprotobuf_proto_mappings(is_migrated)')
    cursor.execute('CREATE INDEX idx_module_id ON jprotobuf_proto_mappings(module_id)')
    cursor.execute('CREATE INDEX idx_batch_id ON jprotobuf_proto_mappings(batch_id)')
    
    conn.commit()
    conn.close()
    
    print("✅ JProtobuf到标准Protobuf消息映射表创建完成")

if __name__ == '__main__':
    create_jprotobuf_mapping_table()
