#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次41到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_41():
    """添加批次41到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_41")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_41 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_41",
            batch_number=41,
            description="多系统迁移",
            status="completed",
            priority=5,
            total_files=40,
            migrated_files=40,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="迁移多个游戏系统的消息类型，包括战斗联赛、战斗通行证、黑名单、棋盘游戏、书签、Buff、卡片、CERA商店、冠军、Buff变更、队伍变更、频道、角色、角色物品、聊天和骑士系统",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_41 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("PT_BATTLELEAGUE_REWARD_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLELEAGUE_REWARD_INFO.java"),
        ("PT_BATTLELEAGUE_TRANSITION_CARD", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLELEAGUE_TRANSITION_CARD.java"),
        ("PT_BATTLE_PASS_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLE_PASS_INFO.java"),
        ("PT_BLACKLIST", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BLACKLIST.java"),
        ("PT_BOARD_GAME_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOARD_GAME_INFO.java"),
        ("PT_BOARD_MOVE_RESULT", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOARD_MOVE_RESULT.java"),
        ("PT_BOARD_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOARD_INFO.java"),
        ("PT_BOARD_TILE_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOARD_TILE_INFO.java"),
        ("PT_BOARD_USER_CONTENTS_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOARD_USER_CONTENTS_INFO.java"),
        ("PT_BOARD_USER_MINIMUM_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOARD_USER_MINIMUM_INFO.java"),
        ("PT_BOOKMARK_ITEM", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOOKMARK_ITEM.java"),
        ("PT_BUFF", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BUFF.java"),
        ("PT_BUFF_LIST", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BUFF_LIST.java"),
        ("PT_CARD_ATTACH", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CARD_ATTACH.java"),
        ("PT_CARD_COMPOSE", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CARD_COMPOSE.java"),
        ("PT_CERA_SHOP_BUY", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CERA_SHOP_BUY.java"),
        ("PT_CERA_SHOP_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CERA_SHOP_INFO.java"),
        ("PT_CERA_SHOP_Mileage", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CERA_SHOP_Mileage.java"),
        ("PT_CHAMPION_CHANGE", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHAMPION_CHANGE.java"),
        ("PT_CHAMPION_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHAMPION_INFO.java"),
        ("PT_CHAMPION_STAGE_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHAMPION_STAGE_INFO.java"),
        ("PT_CHANGE_BUFF", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHANGE_BUFF.java"),
        ("PT_CHANGE_TEAM_USER_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHANGE_TEAM_USER_INFO.java"),
        ("PT_CHANNEL", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHANNEL.java"),
        ("PT_CHANNEL_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHANNEL_INFO.java"),
        ("PT_CHARACTER", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER.java"),
        ("PT_CHARACTER_EQUIP_ONLY_INDEX", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_EQUIP_ONLY_INDEX.java"),
        ("PT_CHARACTER_GUID", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_GUID.java"),
        ("PT_CHARACTER_GUILD_REDPACKET_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_GUILD_REDPACKET_INFO.java"),
        ("PT_CHARACTER_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_INFO.java"),
        ("PT_CHARACTER_RAID_DAMAGE_METER_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_RAID_DAMAGE_METER_INFO.java"),
        ("PT_CHARACTER_RAID_ROUND_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_RAID_ROUND_INFO.java"),
        ("PT_CHARGUID_ENTRANCE_ITEM", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARGUID_ENTRANCE_ITEM.java"),
        ("PT_CHARGUID_FATIGUE", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARGUID_FATIGUE.java"),
        ("PT_CHARGUID_TICKET", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARGUID_TICKET.java"),
        ("PT_CHAT", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHAT.java"),
        ("PT_CHAT_CHANNEL", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHAT_CHANNEL.java"),
        ("PT_CHAT_SYNC", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHAT_SYNC.java"),
        ("PT_CHAT_SYNC_INFO", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHAT_SYNC_INFO.java"),
        ("PT_CHIVALRY", "GAME_SYSTEMS", "proto/dnf/v1/game_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHIVALRY.java"),
    ]
    
    # 添加文件数据
    for file_data in files_data:
        try:
            file = MigrationFile(
                id=None,
                batch_id=batch_id,
                file_name=file_data[0],
                module_name=file_data[1],
                module_id=None,
                status="completed",
                priority=5,
                proto_file=file_data[2],
                java_file=file_data[3],
                has_test=True,
                test_passed=True,
                issues_count=0,
                migration_notes=None,
                start_date="2026-02-10",
                completion_date="2026-02-10",
                created_at=None,
                updated_at=None
            )
            tracker.create_file(file)
            print(f"✅ 添加文件: {file_data[0]}")
        except Exception as e:
            print(f"❌ 添加文件 {file_data[0]} 失败: {e}")
    
    tracker.close()
    print("\n✅ 批次41添加完成")


if __name__ == "__main__":
    add_batch_41()
