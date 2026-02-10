#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次39到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_39():
    """添加批次39到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_39")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_39 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_39",
            batch_number=39,
            description="冒险书和冒险联盟系统",
            status="completed",
            priority=5,
            total_files=20,
            migrated_files=20,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="迁移冒险书系统和冒险联盟系统的消息类型",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_39 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("PT_ADVENTUREBOOK_INFO", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTUREBOOK_INFO.java"),
        ("PT_ADVENTUREBOOK_CONDITION", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTUREBOOK_CONDITION.java"),
        ("PT_ADVENTUREBOOK_ACHIEVE_CLEAR_CONDITION", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTUREBOOK_ACHIEVE_CLEAR_CONDITION.java"),
        ("PT_ADVENTUREBOOK_OPEN_CONDITION", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTUREBOOK_OPEN_CONDITION.java"),
        ("PT_ADVENTUREBOOK_QUEST", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTUREBOOK_QUEST.java"),
        ("PT_ADVENTURE_CLEAR_REWARD", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_CLEAR_REWARD.java"),
        ("PT_ADVENTURE_EXP_INFO", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_EXP_INFO.java"),
        ("PT_ADVENTURE_LIST_ITEM", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_LIST_ITEM.java"),
        ("PT_ADVENTURE_REWARD_INFO", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_REWARD_INFO.java"),
        ("PT_ADVENTURE_REWARD_STEP", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_REWARD_STEP.java"),
        ("PT_ADVENTURE_UNION_COLLECTION", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_COLLECTION.java"),
        ("PT_ADVENTURE_UNION_COLLECTION_SLOT", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_COLLECTION_SLOT.java"),
        ("PT_ADVENTURE_UNION_EXPEDITION", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_EXPEDITION.java"),
        ("PT_ADVENTURE_UNION_EXPEDITION_REWARDS", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_EXPEDITION_REWARDS.java"),
        ("PT_ADVENTURE_UNION_LEVEL_REWARD", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_LEVEL_REWARD.java"),
        ("PT_ADVENTURE_UNION_LEVEL_REWARD_INFO", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_LEVEL_REWARD_INFO.java"),
        ("PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND.java"),
        ("PT_ADVENTURE_UNION_SHAREBOARD_FRAME", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_SHAREBOARD_FRAME.java"),
        ("PT_ADVENTURE_UNION_SHAREBOARD_SLOT", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_SHAREBOARD_SLOT.java"),
        ("PT_ADVENTURE_UNION_SHAREBOARD_SLOT_DETAIL_INFO", "ADVENTURE_SYSTEMS", "proto/dnf/v1/adventure_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ADVENTURE_UNION_SHAREBOARD_SLOT_DETAIL_INFO.java"),
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
    print("\n✅ 批次39添加完成")


if __name__ == "__main__":
    add_batch_39()
