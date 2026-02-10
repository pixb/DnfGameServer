#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次44到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_44():
    """添加批次44到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_44")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_44 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_44",
            batch_number=44,
            description="用户系统和技能系统迁移",
            status="in_progress",
            priority=5,
            total_files=40,
            migrated_files=0,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date=None,
            blocker=None,
            notes="迁移用户系统和技能系统相关的消息类型，使用reports工具优化迁移过程",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_44 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("USER_INFO", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/USER_INFO.java"),
        ("RES_USER_STATUS", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_USER_STATUS.java"),
        ("RES_USER_REPORT", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_USER_REPORT.java"),
        ("UPDATE_ANTIEVIL_SCORE", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/UPDATE_ANTIEVIL_SCORE.java"),
        ("UPDATE_ANTIEVIL_SCORE1", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/UPDATE_ANTIEVIL_SCORE1.java"),
        ("RES_TENCENT_CREDITSCORE_INFO", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TENCENT_CREDITSCORE_INFO.java"),
        ("RES_TITLE_LIST", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TITLE_LIST.java"),
        ("RES_SEASON_PASS_INFO", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SEASON_PASS_INFO.java"),
        ("RES_VERIFICATION_AUTH", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_VERIFICATION_AUTH.java"),
        ("RES_VERIFICATION_FINISH", "USER_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_VERIFICATION_FINISH.java"),
        ("RES_SKILL_INFO", "SKILL_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SKILL_INFO.java"),
        ("RES_SKILL_INIT", "SKILL_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SKILL_INIT.java"),
        ("RES_SKILL_SLOT", "SKILL_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SKILL_SLOT.java"),
        ("RES_SKILL_PAGE_SET", "SKILL_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SKILL_PAGE_SET.java"),
        ("PT_SKILL", "SKILL_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SKILL.java"),
        ("PT_ALL_SKILL_SLOT", "SKILL_SYSTEM", "proto/dnf/v1/user_skills_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ALL_SKILL_SLOT.java"),
        ("RES_STAGE_INFO", "STAGE_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_STAGE_INFO.java"),
        ("RES_STAGE_CLEAR", "STAGE_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_STAGE_CLEAR.java"),
        ("RES_DUNGEON_INFO", "STAGE_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_DUNGEON_INFO.java"),
        ("RES_DUNGEON_CLEAR", "STAGE_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_DUNGEON_CLEAR.java"),
        ("RES_TRANSMISSION_ITEM", "STAGE_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TRANSMISSION_ITEM.java"),
        ("RES_REQUEST_TO_RE_ENTER_DUNGEON", "STAGE_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_REQUEST_TO_RE_ENTER_DUNGEON.java"),
        ("SEND_CHAT", "CHAT_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/SEND_CHAT.java"),
        ("RES_TOWN_CHAT", "CHAT_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TOWN_CHAT.java"),
        ("RES_TOWN_CHAT_LIST", "CHAT_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TOWN_CHAT_LIST.java"),
        ("SESSION_AUTH_CHAT", "CHAT_SYSTEM", "proto/dnf/v1/stage_chat_systems.proto", "src/main/java/com/dnfm/mina/protobuf/SESSION_AUTH_CHAT.java"),
        ("STREAM_DATA", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/STREAM_DATA.java"),
        ("WATCH_ROOM_DATA", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/WATCH_ROOM_DATA.java"),
        ("SendItem_GuidInfo", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/SendItem_GuidInfo.java"),
        ("SendItem_Info", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/SendItem_Info.java"),
        ("SUBSYSTEM", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/SUBSYSTEM.java"),
        ("TYPE_TUTORIAL_LIST", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/TYPE_TUTORIAL_LIST.java"),
        ("RES_TUTORIAL_LIST", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TUTORIAL_LIST.java"),
        ("RES_TUTORIAL_SAVE", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_TUTORIAL_SAVE.java"),
        ("SECTOR_LIST", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/SECTOR_LIST.java"),
        ("SECTOR_INFO", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/SECTOR_INFO.java"),
        ("RES_SERVER_CHECKUP", "OTHER_SYSTEM", "proto/dnf/v1/other_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SERVER_CHECKUP.java")
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
    print("\n✅ 批次44添加完成")


if __name__ == "__main__":
    add_batch_44()
