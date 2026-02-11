#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
更新第三批迁移状态
"""

import sqlite3
from pathlib import Path

class MigrationStatusUpdater:
    """迁移状态更新器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
    
    def _connect(self):
        """连接到数据库"""
        conn = sqlite3.connect(self.db_path)
        conn.row_factory = sqlite3.Row
        return conn
    
    def add_batch(self, batch_number: int, batch_name: str, description: str):
        """添加批次"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 检查批次是否已存在
        cursor.execute('SELECT id FROM migration_batches WHERE batch_number = ?', (batch_number,))
        existing = cursor.fetchone()
        
        if existing:
            print(f"⚠️  批次 {batch_number} 已存在，跳过添加")
            conn.close()
            return
        
        cursor.execute('''
            INSERT INTO migration_batches (batch_number, batch_name, description, status, start_time)
            VALUES (?, ?, ?, 'in_progress', CURRENT_TIMESTAMP)
        ''', (batch_number, batch_name, description))
        
        conn.commit()
        conn.close()
        print(f"✅ 添加批次 {batch_number}: {batch_name}")
    
    def add_proto_message(self, message_name: str, file_path: str, package_name: str, field_count: int):
        """添加标准 Protobuf 消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 检查消息是否已存在
        cursor.execute('SELECT id FROM proto_messages WHERE message_name = ?', (message_name,))
        existing = cursor.fetchone()
        
        if existing:
            conn.close()
            return existing['id']
        
        cursor.execute('''
            INSERT INTO proto_messages (message_name, file_path, package_name, field_count)
            VALUES (?, ?, ?, ?)
        ''', (message_name, file_path, package_name, field_count))
        
        conn.commit()
        proto_id = cursor.lastrowid
        conn.close()
        return proto_id
    
    def add_migration_record(self, batch_id: int, jprotobuf_name: str, proto_name: str, proto_file: str):
        """添加迁移记录"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取 JProtobuf 消息 ID
        cursor.execute('SELECT id FROM jprotobuf_messages WHERE message_name = ?', (jprotobuf_name,))
        jprotobuf_row = cursor.fetchone()
        
        if not jprotobuf_row:
            print(f"⚠️  未找到 JProtobuf 消息: {jprotobuf_name}")
            conn.close()
            return
        
        jprotobuf_id = jprotobuf_row['id']
        
        # 获取标准 Protobuf 消息 ID
        cursor.execute('SELECT id FROM proto_messages WHERE message_name = ?', (proto_name,))
        proto_row = cursor.fetchone()
        
        if not proto_row:
            print(f"⚠️  未找到标准 Protobuf 消息: {proto_name}")
            conn.close()
            return
        
        proto_id = proto_row['id']
        
        # 检查迁移记录是否已存在
        cursor.execute('''
            SELECT id FROM migration_records 
            WHERE jprotobuf_message_id = ? AND proto_message_id = ?
        ''', (jprotobuf_id, proto_id))
        existing = cursor.fetchone()
        
        if existing:
            conn.close()
            return
        
        # 添加映射关系
        cursor.execute('''
            INSERT INTO message_mappings (jprotobuf_message_id, proto_message_id, mapping_type, mapping_confidence, is_verified)
            VALUES (?, ?, 'direct_mapping', 1.0, 1)
        ''', (jprotobuf_id, proto_id))
        
        # 添加迁移记录
        cursor.execute('''
            INSERT INTO migration_records (batch_id, jprotobuf_message_id, proto_message_id, migration_status)
            VALUES (?, ?, ?, 'completed')
        ''', (batch_id, jprotobuf_id, proto_id))
        
        conn.commit()
        conn.close()
        print(f"✅ 添加迁移记录: {jprotobuf_name} → {proto_name}")
    
    def update_batch_status(self, batch_number: int, status: str):
        """更新批次状态"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            UPDATE migration_batches 
            SET status = ?, end_time = CURRENT_TIMESTAMP
            WHERE batch_number = ?
        ''', (status, batch_number))
        
        conn.commit()
        conn.close()
        print(f"✅ 更新批次 {batch_number} 状态为: {status}")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    updater = MigrationStatusUpdater(db_path)
    
    print("📝 更新第三批迁移状态...")
    print()
    
    # 添加批次
    print("1. 添加批次...")
    updater.add_batch(3, 'ITEM', 'ITEM 模块迁移 - P1 优先级')
    print()
    
    # 添加标准 Protobuf 消息
    print("2. 添加标准 Protobuf 消息...")
    updater.add_proto_message('AddWatchingBookmarkRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 1)
    updater.add_proto_message('AdventurebookSpecialRewardRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 5)
    updater.add_proto_message('AdventurebookTeraRewardRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 2)
    updater.add_proto_message('AdventureAutoSearchMiniGameRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 0)
    updater.add_proto_message('AdventureAutoSearchRewardRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 2)
    updater.add_proto_message('AdventureDataRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 0)
    updater.add_proto_message('AdventureUnionRepresentCharacterChangeRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 1)
    updater.add_proto_message('AdventureUnionSetCollectionSlotRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 2)
    updater.add_proto_message('ApplyGuildNpcSkinRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 1)
    updater.add_proto_message('ArrangeGuildStructureRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 4)
    updater.add_proto_message('ArtifactEquippedOptionChangeRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 3)
    updater.add_proto_message('ArtifactInvenOptionChangeRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 1)
    updater.add_proto_message('AuctionDetailItemListRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 3)
    updater.add_proto_message('BattlePassRankingRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 0)
    updater.add_proto_message('BilingInfoRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 4)
    updater.add_proto_message('BilingKrVerifyRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 6)
    updater.add_proto_message('BlackDiamonGetBucketRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 0)
    updater.add_proto_message('BlackDiamonGetRewardRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 1)
    updater.add_proto_message('BuyTicketRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 2)
    updater.add_proto_message('CancelAttackSquadJoinRequest', 'proto/dnf/v1/item.proto', 'dnf.v1', 2)
    print()
    
    # 添加迁移记录
    print("3. 添加迁移记录...")
    updater.add_migration_record(3, 'REQ_ADD_WATCHING_BOOKMARK', 'AddWatchingBookmarkRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ADVENTUREBOOK_SPECIAL_REWARD', 'AdventurebookSpecialRewardRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ADVENTUREBOOK_TERA_REWARD', 'AdventurebookTeraRewardRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ADVENTURE_AUTO_SEARCH_MINI_GAME', 'AdventureAutoSearchMiniGameRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ADVENTURE_AUTO_SEARCH_REWARD', 'AdventureAutoSearchRewardRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ADVENTURE_DATA', 'AdventureDataRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE', 'AdventureUnionRepresentCharacterChangeRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ADVENTURE_UNION_SET_COLLECTION_SLOT', 'AdventureUnionSetCollectionSlotRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_APPLY_GUILD_NPC_SKIN', 'ApplyGuildNpcSkinRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ARRANGE_GUILD_STRUCTURE', 'ArrangeGuildStructureRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ARTIFACT_EQUIPPED_OPTION_CHANGE', 'ArtifactEquippedOptionChangeRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_ARTIFACT_INVEN_OPTION_CHANGE', 'ArtifactInvenOptionChangeRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_AUCTION_DETAIL_ITEM_LIST', 'AuctionDetailItemListRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_BATTLE_PASS_RANKING', 'BattlePassRankingRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_BILING_INFO', 'BilingInfoRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_BILING_KR_VERIFY', 'BilingKrVerifyRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_BLACK_DIAMON_GET_BUCKET', 'BlackDiamonGetBucketRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_BLACK_DIAMON_GET_REWARD', 'BlackDiamonGetRewardRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_BUY_TICKET', 'BuyTicketRequest', 'proto/dnf/v1/item.proto')
    updater.add_migration_record(3, 'REQ_CANCEL_ATTACK_SQUAD_JOIN', 'CancelAttackSquadJoinRequest', 'proto/dnf/v1/item.proto')
    print()
    
    # 更新批次状态
    print("4. 更新批次状态...")
    updater.update_batch_status(3, 'completed')
    print()
    
    print("✅ 第三批迁移状态更新完成！")

if __name__ == '__main__':
    main()
