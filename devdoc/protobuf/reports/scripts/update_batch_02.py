#!/usr/bin/env python3
"""
更新第二批迁移状态
"""

import sqlite3
from pathlib import Path
from datetime import datetime

class MigrationStatusUpdater:
    """迁移状态更新器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
    
    def _connect(self):
        """连接到数据库"""
        return sqlite3.connect(self.db_path)
    
    def add_batch(self, batch_number: int, batch_name: str, description: str):
        """添加批次"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            INSERT OR REPLACE INTO migration_batches (batch_number, batch_name, description, status, start_time, end_time)
            VALUES (?, ?, ?, 'in_progress', ?, NULL)
        ''', (batch_number, batch_name, description, datetime.now()))
        
        conn.commit()
        conn.close()
        
        print(f"✅ 添加批次: {batch_number} - {batch_name}")
    
    def add_migration_record(self, batch_number: int, jprotobuf_message: str, proto_message: str, 
                            proto_file: str, status: str = 'completed'):
        """添加迁移记录"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取 JProtobuf 消息 ID
        cursor.execute('SELECT id FROM jprotobuf_messages WHERE message_name = ?', (jprotobuf_message,))
        jprotobuf_row = cursor.fetchone()
        
        if not jprotobuf_row:
            print(f"⚠️  未找到 JProtobuf 消息: {jprotobuf_message}")
            conn.close()
            return
        
        jprotobuf_id = jprotobuf_row[0]
        
        # 获取标准 Protobuf 消息 ID
        cursor.execute('SELECT id FROM proto_messages WHERE message_name = ?', (proto_message,))
        proto_row = cursor.fetchone()
        
        if not proto_row:
            print(f"⚠️  未找到标准 Protobuf 消息: {proto_message}")
            conn.close()
            return
        
        proto_id = proto_row[0]
        
        # 添加迁移记录
        cursor.execute('''
            INSERT INTO migration_records (batch_id, jprotobuf_message_id, proto_message_id, migration_status)
            VALUES (?, ?, ?, ?)
        ''', (batch_number, jprotobuf_id, proto_id, status))
        
        # 更新映射关系
        cursor.execute('''
            INSERT OR REPLACE INTO message_mappings 
            (jprotobuf_message_id, proto_message_id, mapping_type, mapping_confidence, is_verified)
            VALUES (?, ?, 'direct_mapping', 1.0, 1)
        ''', (jprotobuf_id, proto_id))
        
        conn.commit()
        conn.close()
        
        print(f"✅ 添加迁移记录: {jprotobuf_message} -> {proto_message}")
    
    def add_proto_message(self, message_name: str, file_path: str, package_name: str, field_count: int):
        """添加标准 Protobuf 消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            INSERT OR REPLACE INTO proto_messages 
            (message_name, file_path, package_name, field_count, is_nested, parent_message)
            VALUES (?, ?, ?, ?, 0, NULL)
        ''', (message_name, file_path, package_name, field_count))
        
        conn.commit()
        conn.close()
        
        print(f"✅ 添加标准 Protobuf 消息: {message_name}")
    
    def update_batch_status(self, batch_number: int, status: str):
        """更新批次状态"""
        conn = self._connect()
        cursor = conn.cursor()
        
        if status == 'completed':
            cursor.execute('''
                UPDATE migration_batches 
                SET status = ?, end_time = ?
                WHERE batch_number = ?
            ''', (status, datetime.now(), batch_number))
        else:
            cursor.execute('''
                UPDATE migration_batches 
                SET status = ?
                WHERE batch_number = ?
            ''', (status, batch_number))
        
        conn.commit()
        conn.close()
        
        print(f"✅ 更新批次状态: {batch_number} -> {status}")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    updater = MigrationStatusUpdater(db_path)
    
    print("📝 更新第二批迁移状态...")
    print()
    
    # 添加批次
    print("1. 添加批次...")
    updater.add_batch(2, 'CHARACTER', 'CHARACTER 模块迁移 - P1 优先级')
    print()
    
    # 添加标准 Protobuf 消息
    print("2. 添加标准 Protobuf 消息...")
    updater.add_proto_message('CharacterGuid', 'proto/dnf/v1/character.proto', 'dnf.v1', 4)
    updater.add_proto_message('CharacterInfoRequest', 'proto/dnf/v1/character.proto', 'dnf.v1', 3)
    updater.add_proto_message('CharacterStatInfoRequest', 'proto/dnf/v1/character.proto', 'dnf.v1', 30)
    updater.add_proto_message('CharacterFrameTabListRequest', 'proto/dnf/v1/character.proto', 'dnf.v1', 0)
    updater.add_proto_message('CharacterGuildRedpacketInfoRequest', 'proto/dnf/v1/character.proto', 'dnf.v1', 0)
    updater.add_proto_message('CharacterSlotChangeRequest', 'proto/dnf/v1/character.proto', 'dnf.v1', 0)
    print()
    
    # 添加迁移记录
    print("3. 添加迁移记录...")
    updater.add_migration_record(2, 'REQ_CHARACTER_INFO', 'CharacterInfoRequest', 'proto/dnf/v1/character.proto')
    updater.add_migration_record(2, 'REQ_CHARACTER_STAT_INFO', 'CharacterStatInfoRequest', 'proto/dnf/v1/character.proto')
    updater.add_migration_record(2, 'REQ_CHARACTER_FRAME_TAB_LIST', 'CharacterFrameTabListRequest', 'proto/dnf/v1/character.proto')
    updater.add_migration_record(2, 'REQ_CHARACTER_GUILD_REDPACKET_INFO', 'CharacterGuildRedpacketInfoRequest', 'proto/dnf/v1/character.proto')
    updater.add_migration_record(2, 'REQ_CHARACTER_SLOT_CHANGE', 'CharacterSlotChangeRequest', 'proto/dnf/v1/character.proto')
    print()
    
    # 更新批次状态
    print("4. 更新批次状态...")
    updater.update_batch_status(2, 'completed')
    print()
    
    print("✅ 第二批迁移状态更新完成！")

if __name__ == '__main__':
    main()
