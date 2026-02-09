#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
创建消息文件映射表
"""

import sqlite3
import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

def create_message_mappings_table():
    """创建消息文件映射表"""
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/migration_progress.db'
    
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()
    
    # 创建消息文件映射表
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS message_file_mappings (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            batch_id INTEGER,
            module_id INTEGER,
            cmd_id INTEGER,
            old_message_name TEXT,
            old_message_type TEXT,
            old_java_file TEXT,
            new_message_name TEXT,
            new_proto_file TEXT,
            new_java_file TEXT,
            new_go_file TEXT,
            implementation_status TEXT DEFAULT 'pending',
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (batch_id) REFERENCES batches(id)
        )
    ''')
    
    # 创建索引
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_mappings_batch ON message_file_mappings(batch_id)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_mappings_module ON message_file_mappings(module_id)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_mappings_old_name ON message_file_mappings(old_message_name)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_mappings_new_name ON message_file_mappings(new_message_name)')
    
    conn.commit()
    conn.close()
    
    print("✅ 消息文件映射表创建完成")

if __name__ == '__main__':
    create_message_mappings_table()
