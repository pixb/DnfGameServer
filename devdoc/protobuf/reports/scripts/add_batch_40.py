#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次40到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_40():
    """添加批次40到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_40")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_40 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_40",
            batch_number=40,
            description="多系统迁移",
            status="completed",
            priority=5,
            total_files=40,
            migrated_files=40,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="迁移多个游戏系统的消息类型，包括账户、成就、战斗、AI、炼金术、技能、祭坛、APC、附魔、街机PVP、神器、裂隙请求、攻击小队、拍卖、头像、战场和战斗联赛系统",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_40 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("PT_ACCOUNT_TICKET", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ACCOUNT_TICKET.java"),
        ("PT_ACHIEVEMENT_REWARD", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ACHIEVEMENT_REWARD.java"),
        ("PT_ACTION_COUNT_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ACTION_COUNT_INFO.java"),
        ("PT_ACTIVE_STATUS_DAMAGE", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ACTIVE_STATUS_DAMAGE.java"),
        ("PT_AI_CHARACTER_DETAIL_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AI_CHARACTER_DETAIL_INFO.java"),
        ("PT_AI_CHARACTER_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AI_CHARACTER_INFO.java"),
        ("PT_ALCHEMY_RECIPE_LIMIT", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ALCHEMY_RECIPE_LIMIT.java"),
        ("PT_ALL_CLEAR_REWARD", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ALL_CLEAR_REWARD.java"),
        ("PT_ALL_SKILL_SLOT", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ALL_SKILL_SLOT.java"),
        ("PT_ALTAR_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ALTAR_INFO.java"),
        ("PT_APC_CHARACTER", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_APC_CHARACTER.java"),
        ("PT_APC_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_APC_INFO.java"),
        ("PT_APPENDAGE", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_APPENDAGE.java"),
        ("PT_APPENDAGE_EXP", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_APPENDAGE_EXP.java"),
        ("PT_ARCADE_PVP_INFO_CURRENCY", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ARCADE_PVP_INFO_CURRENCY.java"),
        ("PT_ARTIFACT", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ARTIFACT.java"),
        ("PT_ARTIFACT_BASE_OPTION", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ARTIFACT_BASE_OPTION.java"),
        ("PT_ATTACH_CRACK_REQUEST", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ATTACH_CRACK_REQUEST.java"),
        ("PT_ATTACK_SQUAD_ADVERTISEMENT", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ATTACK_SQUAD_ADVERTISEMENT.java"),
        ("PT_ATTACK_SQUAD_BOARD_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ATTACK_SQUAD_BOARD_INFO.java"),
        ("PT_ATTACK_SQUAD_BOARD_USER_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ATTACK_SQUAD_BOARD_USER_INFO.java"),
        ("PT_ATTACK_SQUAD_DETAIL_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ATTACK_SQUAD_DETAIL_INFO.java"),
        ("PT_ATTACK_SQUAD_MEMBER_INFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ATTACK_SQUAD_MEMBER_INFO.java"),
        ("PT_ATTACK_SQUAD_TIMER", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ATTACK_SQUAD_TIMER.java"),
        ("PT_AUCTION_BASE", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AUCTION_BASE.java"),
        ("PT_AUCTION_EQUIP", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AUCTION_EQUIP.java"),
        ("PT_AUCTION_ITEM_INDEX", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AUCTION_ITEM_INDEX.java"),
        ("PT_AUCTION_STACKABLE", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AUCTION_STACKABLE.java"),
        ("PT_AVATAR_COMPOSE_MATERIAL", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AVATAR_COMPOSE_MATERIAL.java"),
        ("PT_AVATAR_DISASAMBBLED_MATERIAL", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AVATAR_DISASAMBBLED_MATERIAL.java"),
        ("PT_AVATAR_GUID", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AVATAR_GUID.java"),
        ("PT_AVATAR_INDEX_SLOT", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AVATAR_INDEX_SLOT.java"),
        ("PT_AVATAR_ITEM", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AVATAR_ITEM.java"),
        ("PT_BATTLEFIELD_STATEINFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLEFIELD_STATEINFO.java"),
        ("PT_BATTLEFIELD_TEAMINFO", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLEFIELD_TEAMINFO.java"),
        ("PT_BATTLELEAGUE_BUFF", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLELEAGUE_BUFF.java"),
        ("PT_BATTLELEAGUE_CONTRIBUTE", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLELEAGUE_CONTRIBUTE.java"),
        ("PT_BATTLELEAGUE_PVE_RECORD", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLELEAGUE_PVE_RECORD.java"),
        ("PT_BATTLELEAGUE_PVP_RECORD", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLELEAGUE_PVP_RECORD.java"),
        ("PT_BATTLELEAGUE_REWARD", "MULTI_SYSTEMS", "proto/dnf/v1/multi_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLELEAGUE_REWARD.java"),
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
    print("\n✅ 批次40添加完成")


if __name__ == "__main__":
    add_batch_40()
