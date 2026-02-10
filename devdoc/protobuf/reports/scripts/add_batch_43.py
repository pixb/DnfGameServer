#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次43到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_43():
    """添加批次43到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_43")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_43 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_43",
            batch_number=43,
            description="多系统迁移",
            status="completed",
            priority=5,
            total_files=40,
            migrated_files=40,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="迁移多个游戏系统的消息类型，包括餐厅、龙棋盘、梦境迷宫、掉落、副本、徽章、关卡、入口、装备、差事和护送系统",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_43 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("PT_DINING_FOOD_BUFF_INFO", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DINING_FOOD_BUFF_INFO.java"),
        ("PT_DRAGON_BOARD_APPENDAGE", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_APPENDAGE.java"),
        ("PT_DRAGON_BOARD_CHANCE_CARD", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_CHANCE_CARD.java"),
        ("PT_DRAGON_BOARD_CHANGE_DICE", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_CHANGE_DICE.java"),
        ("PT_DRAGON_BOARD_CHANGE_SLOT", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_CHANGE_SLOT.java"),
        ("PT_DRAGON_BOARD_GAIN_APPENDAGE", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_GAIN_APPENDAGE.java"),
        ("PT_DRAGON_BOARD_HOLD", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_HOLD.java"),
        ("PT_DRAGON_BOARD_MEMBER", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_MEMBER.java"),
        ("PT_DRAGON_BOARD_MOVE", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_MOVE.java"),
        ("PT_DRAGON_BOARD_REWARD", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_REWARD.java"),
        ("PT_DRAGON_BOARD_RESULT", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DRAGON_BOARD_RESULT.java"),
        ("PT_DREAM_MAZE_DUNGEON", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DREAM_MAZE_DUNGEON.java"),
        ("PT_DREAM_MAZE_PLAY_MEMBER", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DREAM_MAZE_PLAY_MEMBER.java"),
        ("PT_DROP_OBJECT_ITEM", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DROP_OBJECT_ITEM.java"),
        ("PT_DUNGEONRANK", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEONRANK.java"),
        ("PT_DUNGEON_GET_INFO", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEON_GET_INFO.java"),
        ("PT_DUNGEON_PARTICIATE", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEON_PARTICIATE.java"),
        ("PT_DUNGEON_PARTY_COUNT", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEON_PARTY_COUNT.java"),
        ("PT_DUNGEON_PARTY_COUNT2", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEON_PARTY_COUNT2.java"),
        ("PT_DUNGEON_RANK_INFO", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEON_RANK_INFO.java"),
        ("PT_DUNGEON_RESULT_QUEST_INFO", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEON_RESULT_QUEST_INFO.java"),
        ("PT_DUNGEON_START_INFO", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DUNGEON_START_INFO.java"),
        ("PT_EMBLEM_REQUEST", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_EMBLEM_REQUEST.java"),
        ("PT_EMBLEM_RESULT", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_EMBLEM_RESULT.java"),
        ("PT_ENTER_STAGE_MAP", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ENTER_STAGE_MAP.java"),
        ("PT_ENTER_STAGE_OBJECT", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ENTER_STAGE_OBJECT.java"),
        ("PT_ENTRY_INFO", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ENTRY_INFO.java"),
        ("PT_EQUIP_PUT_ON_OFF", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_EQUIP_PUT_ON_OFF.java"),
        ("PT_ESCORT_CLEAR_REWARD", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ESCORT_CLEAR_REWARD.java"),
        ("PT_ESCORT_CURRENT_REWARD", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ESCORT_CURRENT_REWARD.java"),
        ("PT_ESCORT_DONE_READY", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ESCORT_DONE_READY.java"),
        ("PT_ESCORT_REWARD", "DUNGEON_SYSTEMS", "proto/dnf/v1/dungeon_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ESCORT_REWARD.java"),
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
    print("\n✅ 批次43添加完成")


if __name__ == "__main__":
    add_batch_43()
