#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次42到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_42():
    """添加批次42到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_42")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_42 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_42",
            batch_number=42,
            description="多系统迁移",
            status="completed",
            priority=5,
            total_files=40,
            migrated_files=40,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="迁移多个游戏系统的消息类型，包括棋盘游戏、骑士、副本、客户端、亲密度、收集、回归、评论、社区礼物、任务、消耗、内容奖励、控制、裂隙、生物、背包、货币、自定义PVP、伤害分析和时间线系统",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_42 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("PT_BOARD_GAME_MOVE_RESULT", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BOARD_GAME_MOVE_RESULT.java"),
        ("PT_CHIVALRY_CURRENCY_DAILY_GAIN", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHIVALRY_CURRENCY_DAILY_GAIN.java"),
        ("PT_CHIVALRY_MISSION", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHIVALRY_MISSION.java"),
        ("PT_CLEAR_DUNGEON_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CLEAR_DUNGEON_INFO.java"),
        ("PT_CLEAR_DUNGEON_INFO_LIST", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CLEAR_DUNGEON_INFO_LIST.java"),
        ("PT_CLEAR_DUNGEON_STARREWARD", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CLEAR_DUNGEON_STARREWARD.java"),
        ("PT_CLIENTINFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CLIENTINFO.java"),
        ("PT_CLOSENESS_RESULT_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CLOSENESS_RESULT_INFO.java"),
        ("PT_COLLECTION_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COLLECTION_INFO.java"),
        ("PT_COLLECTION_ITEM", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COLLECTION_ITEM.java"),
        ("PT_COMBACK_CHANNEL_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMBACK_CHANNEL_INFO.java"),
        ("PT_COMEBACK_MATCHING_USERS", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMEBACK_MATCHING_USERS.java"),
        ("PT_COMMENT", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMMENT.java"),
        ("PT_COMMUNITY_GIFT_DELETE_LOG", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMMUNITY_GIFT_DELETE_LOG.java"),
        ("PT_COMMUNITY_GIFT_GUID_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMMUNITY_GIFT_GUID_INFO.java"),
        ("PT_COMMUNITY_GIFT_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMMUNITY_GIFT_INFO.java"),
        ("PT_COMMUNITY_GIFT_RECEIVER_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMMUNITY_GIFT_RECEIVER_INFO.java"),
        ("PT_COMMUNITY_GIFT_SENDER_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMMUNITY_GIFT_SENDER_INFO.java"),
        ("PT_COMMUNITY_GIFT_UNTIE_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMMUNITY_GIFT_UNTIE_INFO.java"),
        ("PT_COMPLETE_QUEST_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_COMPLETE_QUEST_INFO.java"),
        ("PT_CONSUME_ITEMS", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CONSUME_ITEMS.java"),
        ("PT_CONSUME_LIST", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CONSUME_LIST.java"),
        ("PT_CONTENTS_REWARD_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CONTENTS_REWARD_INFO.java"),
        ("PT_CONTROL_GROUP_INVITE_ITEM", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CONTROL_GROUP_INVITE_ITEM.java"),
        ("PT_CONTROL_TRAININGROOM_CLEARINFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CONTROL_TRAININGROOM_CLEARINFO.java"),
        ("PT_CRACK", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CRACK.java"),
        ("PT_CREATURE", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CREATURE.java"),
        ("PT_CREATURE_COMMUNION", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CREATURE_COMMUNION.java"),
        ("PT_CREATURE_ERRAND", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CREATURE_ERRAND.java"),
        ("PT_CREATURE_LEARN_SKILL_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CREATURE_LEARN_SKILL_INFO.java"),
        ("PT_CREATURE_LEARN_SKILL_INFOS", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CREATURE_LEARN_SKILL_INFOS.java"),
        ("PT_CREATURE_RANDOM_OPTION", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CREATURE_RANDOM_OPTION.java"),
        ("PT_CREATURE_SKILL", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CREATURE_SKILL.java"),
        ("PT_CUBE_ITEMS", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CUBE_ITEMS.java"),
        ("PT_CURRENCY_DAILY_GAIN", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CURRENCY_DAILY_GAIN.java"),
        ("PT_CURRENCY_REWARD_INFO", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CURRENCY_REWARD_INFO.java"),
        ("PT_CUSTOM_PVP_DATA", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CUSTOM_PVP_DATA.java"),
        ("PT_CUSTOM_PVP_ROOM_SETTING", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CUSTOM_PVP_ROOM_SETTING.java"),
        ("PT_DAMAGE_ANALYSIS", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DAMAGE_ANALYSIS.java"),
        ("PT_DETAIL_TIME_LINE", "GAMEPLAY_SYSTEMS", "proto/dnf/v1/gameplay_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DETAIL_TIME_LINE.java"),
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
    print("\n✅ 批次42添加完成")


if __name__ == "__main__":
    add_batch_42()
