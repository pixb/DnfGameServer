#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次38到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_38():
    """添加批次38到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_38")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_38 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_38",
            batch_number=38,
            description="用户系统相关消息",
            status="completed",
            priority=5,
            total_files=44,
            migrated_files=44,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="迁移用户界面、系统命令、商店、票务、赛季通行证等多个系统的消息类型",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_38 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("PT_WATING_LIST_USER_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_WATING_LIST_USER_INFO.java"),
        ("PT_USER_NAME", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_USER_NAME.java"),
        ("PT_USER_MINIMUM_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_USER_MINIMUM_INFO.java"),
        ("PT_USER_LOADING_STATUS", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_USER_LOADING_STATUS.java"),
        ("PT_SIMPLE_CHARACTER", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SIMPLE_CHARACTER.java"),
        ("PT_USER_BOARD_GAME_RESULT", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_USER_BOARD_GAME_RESULT.java"),
        ("PT_ROOM_USER_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ROOM_USER_INFO.java"),
        ("PT_TEAM_RESULT", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TEAM_RESULT.java"),
        ("PT_SHOP_TAB_RESET", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SHOP_TAB_RESET.java"),
        ("PT_SHOP_BUY_COUNT", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SHOP_BUY_COUNT.java"),
        ("PT_TICKET", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TICKET.java"),
        ("PT_TICKET_REWARD_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TICKET_REWARD_INFO.java"),
        ("PT_TOWER_REWARD_ITEMS", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TOWER_REWARD_ITEMS.java"),
        ("PT_TIME_ATTACK_REWARD", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TIME_ATTACK_REWARD.java"),
        ("PT_SHARE_REWARD_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SHARE_REWARD_INFO.java"),
        ("PT_SKIN_ITEM", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SKIN_ITEM.java"),
        ("PT_SKIN_CHAT_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SKIN_CHAT_INFO.java"),
        ("PT_SKIN_EQUIP_PUT_ON_OFF", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SKIN_EQUIP_PUT_ON_OFF.java"),
        ("PT_TUTORIAL", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TUTORIAL.java"),
        ("PT_SYSTEM_COMMAND", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSTEM_COMMAND.java"),
        ("PT_SYSCMD_COMMAND", "SYSTEM_MISC_TYPES", "proto/dnf/v1/system_misc_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_COMMAND.java"),
        ("PT_SYSCMD_COMMAND_TAKE_ITEM", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_COMMAND_TAKE_ITEM.java"),
        ("PT_SYSCMD_ITEMJSON", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_ITEMJSON.java"),
        ("PT_SYSCMD_CERASHOP", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_CERASHOP.java"),
        ("PT_SYSCMD_PACKAGES", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_PACKAGES.java"),
        ("PT_SYSCMD_PARTY", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_PARTY.java"),
        ("PT_SYSCMD_MASTERBUFF", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_MASTERBUFF.java"),
        ("PT_WHISPER_CHAT", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_WHISPER_CHAT.java"),
        ("PT_USE_INHERIT_TYPE", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_USE_INHERIT_TYPE.java"),
        ("PT_WAREHOUSE_NPC", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_WAREHOUSE_NPC.java"),
        ("PT_WAREHOUSE_STRUCTURE", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_WAREHOUSE_STRUCTURE.java"),
        ("PT_WORLDRADE_REWARD", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_WORLDRADE_REWARD.java"),
        ("PT_WELFARE_FIND_REWARD_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_WELFARE_FIND_REWARD_INFO.java"),
        ("PT_USABLE_FACILITY_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_USABLE_FACILITY_INFO.java"),
        ("PT_SERIA_BUFF_MATERIAL", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SERIA_BUFF_MATERIAL.java"),
        ("PT_SERIA_BUFF_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SERIA_BUFF_INFO.java"),
        ("PT_SD_DEATH_MATCH_REWARD", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SD_DEATH_MATCH_REWARD.java"),
        ("PT_SD_BATTLEROYAL_RECORD", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SD_BATTLEROYAL_RECORD.java"),
        ("PT_TOWN_INTERVAL", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TOWN_INTERVAL.java"),
        ("PT_SUBDUE_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SUBDUE_INFO.java"),
        ("PT_STATE_OBJECT_DROP", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_STATE_OBJECT_DROP.java"),
        ("PT_SPECIAL_TILE_INFO", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SPECIAL_TILE_INFO.java"),
        ("PT_SEASONPASS", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SEASONPASS.java"),
        ("PT_TOY_BILLING_VERIFY", "USER_SYSTEMS", "proto/dnf/v1/user_systems.proto", "src/main/java/com/dnfm/mina/protobuf/PT_TOY_BILLING_VERIFY.java"),
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
    print("\n✅ 批次38添加完成")


if __name__ == "__main__":
    add_batch_38()
