#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次48到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_48():
    """添加批次48到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_48")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_48 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_48",
            batch_number=48,
            description="系统消息、团队系统、副本系统、社交系统等多系统迁移",
            status="completed",
            priority=5,
            total_files=40,
            migrated_files=40,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="使用reports工具优化迁移过程，完成JProtobuf到标准Protobuf的最终迁移",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_48 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        # 系统消息
        ("RES_SERVER_RESPONSE_PACKET", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SERVER_RESPONSE_PACKET.java"),
        ("RES_SECEDE_GUILD", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SECEDE_GUILD.java"),
        ("RES_SAVE_WEAK_SERVER_SIMPLE_DATA", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SAVE_WEAK_SERVER_SIMPLE_DATA.java"),
        ("RES_SEND_GUILD_REDPACKET", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SEND_GUILD_REDPACKET.java"),
        ("RES_SEND_STORAGE", "SYSTEM_MESSAGE", "proto/dnf/v1/system_messages.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SEND_STORAGE.java"),
        # 团队系统
        ("RES_SELECT_OTHER_DUNGEON_AT_MULTI", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SELECT_OTHER_DUNGEON_AT_MULTI.java"),
        ("RES_SENDING_INVITE_FRIEND_LIST", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SENDING_INVITE_FRIEND_LIST.java"),
        ("RES_REPLY_PROPOSAL", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_REPLY_PROPOSAL.java"),
        ("RES_SET_PVP_CONTROL_MODE", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SET_PVP_CONTROL_MODE.java"),
        ("RES_RETURN_TO_TOWN_AT_MULTI_PLAY", "TEAM_SYSTEM", "proto/dnf/v1/team_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_RETURN_TO_TOWN_AT_MULTI_PLAY.java"),
        # 副本系统
        ("RES_START_LOCKSTEP_ROOM", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_LOCKSTEP_ROOM.java"),
        ("RES_START_DUNGEON", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_DUNGEON.java"),
        ("RES_START_DUNGEON_COMPLETE", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_DUNGEON_COMPLETE.java"),
        ("RES_SYNC_DUNGEON_START_TIME", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SYNC_DUNGEON_START_TIME.java"),
        ("RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM", "DUNGEON_SYSTEM", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM.java"),
        # 社交系统
        ("RES_REQUEST_FRIEND_INVITE", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_REQUEST_FRIEND_INVITE.java"),
        ("RES_REWARD_QUEST", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_REWARD_QUEST.java"),
        ("RES_SET_APPENDAGE_MANNEQUIN", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SET_APPENDAGE_MANNEQUIN.java"),
        ("RES_SET_NAME_MANNEQUIN", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SET_NAME_MANNEQUIN.java"),
        ("RES_TSS_DATA", "SOCIAL_SYSTEM", "proto/dnf/v1/social_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TSS_DATA.java"),
        # 用户系统
        ("RES_TUTORIAL_SAVE", "USER_SYSTEM", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TUTORIAL_SAVE.java"),
        ("RES_USE_COIN", "USER_SYSTEM", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_USE_COIN.java"),
        ("RES_TOWER_INFO", "USER_SYSTEM", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TOWER_INFO.java"),
        ("RES_START_BOARD_GAME", "USER_SYSTEM", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_BOARD_GAME.java"),
        ("RES_TARGET_USER_DETAIL_INFO", "USER_SYSTEM", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TARGET_USER_DETAIL_INFO.java"),
        # 系统功能
        ("RES_START_GAME", "SYSTEM_FUNCTION", "proto/dnf/v1/system_functions.proto", "src/main/java/com/dnfm/mina/protobuf/RES_START_GAME.java"),
        ("RES_STANDBY", "SYSTEM_FUNCTION", "proto/dnf/v1/system_functions.proto", "src/main/java/com/dnfm/mina/protobuf/RES_STANDBY.java"),
        ("RES_STORAGE_STEP", "SYSTEM_FUNCTION", "proto/dnf/v1/system_functions.proto", "src/main/java/com/dnfm/mina/protobuf/RES_STORAGE_STEP.java"),
        ("RES_VERIFICATION_SIMPLE", "SYSTEM_FUNCTION", "proto/dnf/v1/system_functions.proto", "src/main/java/com/dnfm/mina/protobuf/RES_VERIFICATION_SIMPLE.java"),
        ("RES_WELFARE_FIND_REWARD_INFO", "SYSTEM_FUNCTION", "proto/dnf/v1/system_functions.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WELFARE_FIND_REWARD_INFO.java"),
        # 战斗系统
        ("RES_WORLD_RAID_RANKING", "COMBAT_SYSTEM", "proto/dnf/v1/combat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WORLD_RAID_RANKING.java"),
        ("RES_WORLD_BOSS_DAMAGE", "COMBAT_SYSTEM", "proto/dnf/v1/combat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WORLD_BOSS_DAMAGE.java"),
        ("RES_WORLDBOSS_RANKING", "COMBAT_SYSTEM", "proto/dnf/v1/combat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WORLDBOSS_RANKING.java"),
        ("RES_WORLD_BOSS_INFO", "COMBAT_SYSTEM", "proto/dnf/v1/combat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WORLD_BOSS_INFO.java"),
        ("RES_WARDROBE_SET_MANNEQUIN", "COMBAT_SYSTEM", "proto/dnf/v1/combat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WARDROBE_SET_MANNEQUIN.java"),
        # 其他系统
        ("RES_WARDROBE_SET_SLOT", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WARDROBE_SET_SLOT.java"),
        ("RES_VERIFICATION_EVENT", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_VERIFICATION_EVENT.java"),
        ("RES_WELFARE_FIND_REWARD_GET", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_WELFARE_FIND_REWARD_GET.java"),
        ("RES_VERIFICATION_START", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_VERIFICATION_START.java"),
        ("RES_STORAGE_EXTEND", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_STORAGE_EXTEND.java")
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
                migration_notes="使用reports工具优化迁移过程",
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
    print("\n✅ 批次48添加完成")


if __name__ == "__main__":
    add_batch_48()
