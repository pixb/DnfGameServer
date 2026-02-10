#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次49到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_49():
    """添加批次49到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_49")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_49 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_49",
            batch_number=49,
            description="系统消息、团队系统、副本系统、社交系统等补充文件迁移",
            status="pending",
            priority=5,
            total_files=20,
            migrated_files=0,
            start_date="2026-02-10",
            planned_end_date="2026-02-10",
            actual_end_date=None,
            blocker=None,
            notes="补充第48批迁移的文件，完成整个JProtobuf到标准Protobuf的迁移过程",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_49 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        # 系统消息补充
        ("RES_SERVER_RESPONSE_PACKET_SUPPLEMENT", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SERVER_RESPONSE_PACKET_SUPPLEMENT.java"),
        ("RES_SECEDE_GUILD_DETAIL", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SECEDE_GUILD_DETAIL.java"),
        ("RES_SAVE_WEAK_SERVER_FULL_DATA", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SAVE_WEAK_SERVER_FULL_DATA.java"),
        ("RES_SEND_GUILD_REDPACKET_DETAIL", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SEND_GUILD_REDPACKET_DETAIL.java"),
        ("RES_SEND_STORAGE_COMPLETE", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SEND_STORAGE_COMPLETE.java"),
        # 团队系统补充
        ("RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SELECT_OTHER_DUNGEON_AT_MULTI_DETAIL.java"),
        ("RES_SENDING_INVITE_FRIEND_LIST_FULL", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SENDING_INVITE_FRIEND_LIST_FULL.java"),
        ("RES_REPLY_PROPOSAL_DETAIL", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_REPLY_PROPOSAL_DETAIL.java"),
        ("RES_SET_PVP_CONTROL_MODE_FULL", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SET_PVP_CONTROL_MODE_FULL.java"),
        ("RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_RETURN_TO_TOWN_AT_MULTI_PLAY_DETAIL.java"),
        # 副本系统补充
        ("RES_START_LOCKSTEP_ROOM_DETAIL", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_LOCKSTEP_ROOM_DETAIL.java"),
        ("RES_START_DUNGEON_PREPARE", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_DUNGEON_PREPARE.java"),
        ("RES_START_DUNGEON_COMPLETE_DETAIL", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_DUNGEON_COMPLETE_DETAIL.java"),
        ("RES_SYNC_DUNGEON_START_TIME_FULL", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SYNC_DUNGEON_START_TIME_FULL.java"),
        ("RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM_DETAIL.java"),
        # 社交系统补充
        ("RES_REQUEST_FRIEND_INVITE_DETAIL", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_REQUEST_FRIEND_INVITE_DETAIL.java"),
        ("RES_REWARD_QUEST_FULL", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_REWARD_QUEST_FULL.java"),
        ("RES_SET_APPENDAGE_MANNEQUIN_DETAIL", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SET_APPENDAGE_MANNEQUIN_DETAIL.java"),
        ("RES_SET_NAME_MANNEQUIN_FULL", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SET_NAME_MANNEQUIN_FULL.java"),
        ("RES_TSS_DATA_COMPLETE", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TSS_DATA_COMPLETE.java")
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
                status="pending",
                priority=5,
                proto_file=file_data[2],
                java_file=file_data[3],
                has_test=False,
                test_passed=False,
                issues_count=0,
                migration_notes="补充文件迁移",
                start_date="2026-02-10",
                completion_date=None,
                created_at=None,
                updated_at=None
            )
            tracker.create_file(file)
            print(f"✅ 添加文件: {file_data[0]}")
        except Exception as e:
            print(f"❌ 添加文件 {file_data[0]} 失败: {e}")
    
    tracker.close()
    print("\n✅ 批次49添加完成")


if __name__ == "__main__":
    add_batch_49()
