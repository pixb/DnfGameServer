#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移系统数据库初始化脚本
创建完整的数据库表结构，支持JProtobuf到标准Protobuf的迁移记录
"""

import sqlite3
from pathlib import Path

class MigrationDatabaseInitializer:
    """迁移数据库初始化器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def initialize_database(self):
        """初始化数据库表结构"""
        print("🔧 初始化迁移数据库...")
        print()
        
        conn = self._connect()
        cursor = conn.cursor()
        
        # 1. JProtobuf消息表
        print("1. 创建 jprotobuf_messages 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS jprotobuf_messages (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                message_name TEXT NOT NULL UNIQUE,
                file_path TEXT NOT NULL,
                module_id INTEGER,
                cmd INTEGER,
                message_type TEXT NOT NULL,
                field_count INTEGER DEFAULT 0,
                has_dependencies BOOLEAN DEFAULT 0,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        # 2. 标准Protobuf消息表
        print("2. 创建 proto_messages 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS proto_messages (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                message_name TEXT NOT NULL UNIQUE,
                file_path TEXT NOT NULL,
                package_name TEXT,
                field_count INTEGER DEFAULT 0,
                is_nested BOOLEAN DEFAULT 0,
                parent_message TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        # 3. 消息映射表
        print("3. 创建 message_mappings 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_mappings (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                jprotobuf_message_id INTEGER NOT NULL,
                proto_message_id INTEGER,
                mapping_type TEXT NOT NULL,
                mapping_confidence REAL DEFAULT 0.0,
                is_verified BOOLEAN DEFAULT 0,
                verified_by TEXT,
                verified_at TIMESTAMP,
                notes TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (jprotobuf_message_id) REFERENCES jprotobuf_messages(id),
                FOREIGN KEY (proto_message_id) REFERENCES proto_messages(id),
                UNIQUE(jprotobuf_message_id, proto_message_id)
            )
        ''')
        
        # 4. 迁移批次表
        print("4. 创建 migration_batches 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS migration_batches (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                batch_number INTEGER NOT NULL UNIQUE,
                batch_name TEXT,
                description TEXT,
                status TEXT DEFAULT 'pending',
                start_time TIMESTAMP,
                end_time TIMESTAMP,
                jprotobuf_count INTEGER DEFAULT 0,
                proto_count INTEGER DEFAULT 0,
                created_by TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        # 5. 迁移记录表
        print("5. 创建 migration_records 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS migration_records (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                batch_id INTEGER NOT NULL,
                jprotobuf_message_id INTEGER NOT NULL,
                proto_message_id INTEGER,
                migration_status TEXT NOT NULL,
                migration_reason TEXT,
                migration_notes TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (batch_id) REFERENCES migration_batches(id),
                FOREIGN KEY (jprotobuf_message_id) REFERENCES jprotobuf_messages(id),
                FOREIGN KEY (proto_message_id) REFERENCES proto_messages(id)
            )
        ''')
        
        # 6. 消息字段表
        print("6. 创建 message_fields 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_fields (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                message_type TEXT NOT NULL,
                message_id INTEGER NOT NULL,
                field_name TEXT NOT NULL,
                field_type TEXT NOT NULL,
                field_number INTEGER NOT NULL,
                is_repeated BOOLEAN DEFAULT 0,
                is_optional BOOLEAN DEFAULT 0,
                default_value TEXT,
                comment TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (message_id) REFERENCES proto_messages(id) ON DELETE CASCADE,
                UNIQUE(message_type, message_id, field_name)
            )
        ''')
        
        # 7. 消息依赖表
        print("7. 创建 message_dependencies 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_dependencies (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                parent_message_id INTEGER NOT NULL,
                child_message_id INTEGER NOT NULL,
                dependency_type TEXT NOT NULL,
                dependency_description TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (parent_message_id) REFERENCES proto_messages(id) ON DELETE CASCADE,
                FOREIGN KEY (child_message_id) REFERENCES proto_messages(id) ON DELETE CASCADE,
                UNIQUE(parent_message_id, child_message_id)
            )
        ''')
        
        # 8. 迁移历史表
        print("8. 创建 migration_history 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS migration_history (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                batch_id INTEGER NOT NULL,
                action_type TEXT NOT NULL,
                action_description TEXT,
                action_data TEXT,
                performed_by TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (batch_id) REFERENCES migration_batches(id)
            )
        ''')
        
        # 9. 消息使用场景表
        print("9. 创建 message_usage_scenarios 表...")
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS message_usage_scenarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                message_type TEXT NOT NULL,
                message_id INTEGER NOT NULL,
                scenario_name TEXT NOT NULL,
                scenario_description TEXT,
                used_by_messages TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (message_id) REFERENCES proto_messages(id) ON DELETE CASCADE
            )
        ''')
        
        # 10. 索引
        print("10. 创建索引...")
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_jprotobuf_messages_module ON jprotobuf_messages(module_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_jprotobuf_messages_type ON jprotobuf_messages(message_type)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_proto_messages_file ON proto_messages(file_path)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_message_mappings_jprotobuf ON message_mappings(jprotobuf_message_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_message_mappings_proto ON message_mappings(proto_message_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_migration_records_batch ON migration_records(batch_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_migration_records_status ON migration_records(migration_status)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_message_fields_message ON message_fields(message_type, message_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_message_dependencies_parent ON message_dependencies(parent_message_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_message_dependencies_child ON message_dependencies(child_message_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_migration_history_batch ON migration_history(batch_id)')
        
        conn.commit()
        conn.close()
        
        print()
        print("✅ 数据库初始化完成！")
        print()
        print("已创建的表:")
        print("  1. jprotobuf_messages - JProtobuf消息")
        print("  2. proto_messages - 标准Protobuf消息")
        print("  3. message_mappings - 消息映射关系")
        print("  4. migration_batches - 迁移批次")
        print("  5. migration_records - 迁移记录")
        print("  6. message_fields - 消息字段")
        print("  7. message_dependencies - 消息依赖")
        print("  8. migration_history - 迁移历史")
        print("  9. message_usage_scenarios - 消息使用场景")
        print()
    
    def drop_all_tables(self):
        """删除所有表（慎用）"""
        print("⚠️  警告：即将删除所有表！")
        
        confirm = input("确认删除所有表？(yes/no): ")
        if confirm.lower() != 'yes':
            print("已取消")
            return
        
        conn = self._connect()
        cursor = conn.cursor()
        
        tables = [
            'message_usage_scenarios',
            'migration_history',
            'message_dependencies',
            'message_fields',
            'migration_records',
            'migration_batches',
            'message_mappings',
            'proto_messages',
            'jprotobuf_messages'
        ]
        
        for table in tables:
            cursor.execute(f'DROP TABLE IF EXISTS {table}')
            print(f"  已删除表: {table}")
        
        conn.commit()
        conn.close()
        
        print()
        print("✅ 所有表已删除")

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='迁移数据库初始化脚本')
    parser.add_argument('--init', action='store_true', help='初始化数据库')
    parser.add_argument('--drop', action='store_true', help='删除所有表（慎用）')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    initializer = MigrationDatabaseInitializer(db_path)
    
    if args.init:
        initializer.initialize_database()
    elif args.drop:
        initializer.drop_all_tables()
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
