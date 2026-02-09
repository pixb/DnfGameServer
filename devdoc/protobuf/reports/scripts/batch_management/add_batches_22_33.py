#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次22-33到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, MigrationStatus, Batch, MigrationFile


def add_batch_22_33():
    """添加批次22-33到数据库"""
    with MigrationTracker() as tracker:
        # 批次22: STREAM_DATA 和 USER_INFO 模块
        batch_22 = Batch(
            id=None,
            batch_name="batch_22",
            batch_number=22,
            description="STREAM_DATA 和 USER_INFO 模块",
            status=MigrationStatus.COMPLETED.value,
            priority=5,
            total_files=2,
            migrated_files=2,
            start_date="2026-02-09",
            actual_end_date="2026-02-09",
            notes="迁移了STREAM_DATA和USER_INFO两个模块",
            created_at=None,
            updated_at=None
        )
        tracker.create_batch(batch_22)
        
        # 添加批次22的文件
        batch_22_id = tracker.get_batch_by_name("batch_22").id
        tracker.add_file(
            batch_id=batch_22_id,
            file_name="STREAM_DATA",
            module_name="STREAM_DATA",
            status=MigrationStatus.COMPLETED.value,
            priority=5,
            proto_file="proto/dnf/v1/stream_data.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/STREAM_DATA.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_22_id,
            file_name="USER_INFO",
            module_name="USER_INFO",
            status=MigrationStatus.COMPLETED.value,
            priority=5,
            proto_file="proto/dnf/v1/user_info.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/USER_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        
        # 批次23: 通用数据结构
        tracker.add_batch(
            batch_name="batch_23",
            batch_number=23,
            description="通用数据结构（PT_GUILD_SYMBOL、PT_SD_AVATAR_ITEM、PT_RANKING）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=3,
            migrated_files=3,
            start_date="2026-02-09",
            actual_end_date="2026-02-09",
            notes="迁移了PT_GUILD_SYMBOL、PT_SD_AVATAR_ITEM和PT_RANKING三个通用数据结构"
        )
        
        # 添加批次23的文件
        batch_23_id = tracker.get_batch_by_name("batch_23").id
        tracker.add_file(
            batch_id=batch_23_id,
            file_name="PT_GUILD_SYMBOL",
            module_name="COMMON_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/common_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_GUILD_SYMBOL.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_23_id,
            file_name="PT_SD_AVATAR_ITEM",
            module_name="COMMON_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/common_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_SD_AVATAR_ITEM.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_23_id,
            file_name="PT_RANKING",
            module_name="COMMON_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/common_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_RANKING.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        
        # 批次24: 基础数据结构
        tracker.add_batch(
            batch_name="batch_24",
            batch_number=24,
            description="基础数据结构（AUTH_INFO、CHARACTER_INFO、BASE_CHAT）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=3,
            migrated_files=3,
            start_date="2026-02-09",
            actual_end_date="2026-02-09",
            notes="迁移了AUTH_INFO、CHARACTER_INFO和BASE_CHAT三个基础数据结构"
        )
        
        # 添加批次24的文件
        batch_24_id = tracker.get_batch_by_name("batch_24").id
        tracker.add_file(
            batch_id=batch_24_id,
            file_name="AUTH_INFO",
            module_name="BASIC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/basic_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/AUTH_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_24_id,
            file_name="CHARACTER_INFO",
            module_name="BASIC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/basic_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/CHARACTER_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_24_id,
            file_name="BASE_CHAT",
            module_name="BASIC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/basic_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/BASE_CHAT.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        
        # 批次25: 系统数据结构
        tracker.add_batch(
            batch_name="batch_25",
            batch_number=25,
            description="系统数据结构（Actor、CHAT_BASE、SUBSYSTEM）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=3,
            migrated_files=3,
            start_date="2026-02-09",
            actual_end_date="2026-02-09",
            notes="迁移了Actor、CHAT_BASE和SUBSYSTEM三个系统数据结构"
        )
        
        # 添加批次25的文件
        batch_25_id = tracker.get_batch_by_name("batch_25").id
        tracker.add_file(
            batch_id=batch_25_id,
            file_name="Actor",
            module_name="SYSTEM_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/system_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/Actor.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_25_id,
            file_name="CHAT_BASE",
            module_name="SYSTEM_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/system_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/CHAT_BASE.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_25_id,
            file_name="SUBSYSTEM",
            module_name="SYSTEM_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/system_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/SUBSYSTEM.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        
        # 批次26: 系统选项数据结构
        tracker.add_batch(
            batch_name="batch_26",
            batch_number=26,
            description="系统选项数据结构（RES_SYSTEM_BUFF_APPENDAGE_LIST、PT_SYSTEM_BUFF_APPENDAGE、PT_BATTLE_OPTION、ENUM_BATTLE_OPTION、RES_RANDOMOPTION_SELECT）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=5,
            migrated_files=5,
            start_date="2026-02-09",
            actual_end_date="2026-02-09",
            notes="迁移了5个系统选项数据结构"
        )
        
        # 添加批次26的文件
        batch_26_id = tracker.get_batch_by_name("batch_26").id
        tracker.add_file(
            batch_id=batch_26_id,
            file_name="RES_SYSTEM_BUFF_APPENDAGE_LIST",
            module_name="OPTION_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/option_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/RES_SYSTEM_BUFF_APPENDAGE_LIST.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_26_id,
            file_name="PT_SYSTEM_BUFF_APPENDAGE",
            module_name="OPTION_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/option_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_SYSTEM_BUFF_APPENDAGE.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_26_id,
            file_name="PT_BATTLE_OPTION",
            module_name="OPTION_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/option_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_BATTLE_OPTION.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_26_id,
            file_name="ENUM_BATTLE_OPTION",
            module_name="OPTION_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/option_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/ENUM_BATTLE_OPTION.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_26_id,
            file_name="RES_RANDOMOPTION_SELECT",
            module_name="OPTION_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/option_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/RES_RANDOMOPTION_SELECT.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        
        # 批次27: 杂项数据结构
        tracker.add_batch(
            batch_name="batch_27",
            batch_number=27,
            description="杂项数据结构（PT_CERA_SHOP_MONEY、PT_DINING_PAY、PT_GUARDIAN_ORDER）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=3,
            migrated_files=3,
            start_date="2026-02-09",
            actual_end_date="2026-02-09",
            notes="迁移了PT_CERA_SHOP_MONEY、PT_DINING_PAY和PT_GUARDIAN_ORDER三个杂项数据结构"
        )
        
        # 添加批次27的文件
        batch_27_id = tracker.get_batch_by_name("batch_27").id
        tracker.add_file(
            batch_id=batch_27_id,
            file_name="PT_CERA_SHOP_MONEY",
            module_name="MISC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/misc_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_CERA_SHOP_MONEY.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_27_id,
            file_name="PT_DINING_PAY",
            module_name="MISC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/misc_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_DINING_PAY.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_27_id,
            file_name="PT_GUARDIAN_ORDER",
            module_name="MISC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/misc_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_GUARDIAN_ORDER.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        
        # 批次28: 社交数据结构
        tracker.add_batch(
            batch_name="batch_28",
            batch_number=28,
            description="社交数据结构（PT_MENTEE_INFO、PT_MENTOR_QUEST_INFO、PT_AUCTION_ITEM_PRICE_INFO、PT_CHARACTER_SLOT_INFO）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=4,
            migrated_files=4,
            start_date="2026-02-09",
            actual_end_date="2026-02-09",
            notes="迁移了PT_MENTEE_INFO、PT_MENTOR_QUEST_INFO、PT_AUCTION_ITEM_PRICE_INFO和PT_CHARACTER_SLOT_INFO四个社交数据结构"
        )
        
        # 添加批次28的文件
        batch_28_id = tracker.get_batch_by_name("batch_28").id
        tracker.add_file(
            batch_id=batch_28_id,
            file_name="PT_MENTEE_INFO",
            module_name="SOCIAL_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/social_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_MENTEE_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_28_id,
            file_name="PT_MENTOR_QUEST_INFO",
            module_name="SOCIAL_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/social_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_MENTOR_QUEST_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_28_id,
            file_name="PT_AUCTION_ITEM_PRICE_INFO",
            module_name="SOCIAL_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/social_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_AUCTION_ITEM_PRICE_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        tracker.add_file(
            batch_id=batch_28_id,
            file_name="PT_CHARACTER_SLOT_INFO",
            module_name="SOCIAL_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/social_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_SLOT_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-09"
        )
        
        # 批次29: 游戏玩法数据结构
        tracker.add_batch(
            batch_name="batch_29",
            batch_number=29,
            description="游戏玩法数据结构（PT_SKILL、PT_DROP_OBJECT_GOLD）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=2,
            migrated_files=2,
            start_date="2026-02-10",
            actual_end_date="2026-02-10",
            notes="迁移了PT_SKILL和PT_DROP_OBJECT_GOLD两个游戏玩法数据结构"
        )
        
        # 添加批次29的文件
        batch_29_id = tracker.get_batch_by_name("batch_29").id
        tracker.add_file(
            batch_id=batch_29_id,
            file_name="PT_SKILL",
            module_name="GAMEPLAY_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/gameplay_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_SKILL.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_29_id,
            file_name="PT_DROP_OBJECT_GOLD",
            module_name="GAMEPLAY_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/gameplay_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_DROP_OBJECT_GOLD.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        
        # 批次30: 库存数据结构
        tracker.add_batch(
            batch_name="batch_30",
            batch_number=30,
            description="库存数据结构（PT_STACKABLE、PT_REPURCHASE_STACKABLE）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=2,
            migrated_files=2,
            start_date="2026-02-10",
            actual_end_date="2026-02-10",
            notes="迁移了PT_STACKABLE和PT_REPURCHASE_STACKABLE两个库存数据结构"
        )
        
        # 添加批次30的文件
        batch_30_id = tracker.get_batch_by_name("batch_30").id
        tracker.add_file(
            batch_id=batch_30_id,
            file_name="PT_STACKABLE",
            module_name="INVENTORY_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/inventory_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_STACKABLE.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_30_id,
            file_name="PT_REPURCHASE_STACKABLE",
            module_name="INVENTORY_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/inventory_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_REPURCHASE_STACKABLE.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        
        # 批次31: 物品数据结构
        tracker.add_batch(
            batch_name="batch_31",
            batch_number=31,
            description="物品数据结构（PT_ITEM_GUID、PT_SLOT_ENCHANT_EXP、PT_SLOT_ENCHANT_INFO）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=3,
            migrated_files=3,
            start_date="2026-02-10",
            actual_end_date="2026-02-10",
            notes="迁移了PT_ITEM_GUID、PT_SLOT_ENCHANT_EXP和PT_SLOT_ENCHANT_INFO三个物品数据结构"
        )
        
        # 添加批次31的文件
        batch_31_id = tracker.get_batch_by_name("batch_31").id
        tracker.add_file(
            batch_id=batch_31_id,
            file_name="PT_ITEM_GUID",
            module_name="ITEM_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/item_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_ITEM_GUID.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_31_id,
            file_name="PT_SLOT_ENCHANT_EXP",
            module_name="ITEM_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/item_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_SLOT_ENCHANT_EXP.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_31_id,
            file_name="PT_SLOT_ENCHANT_INFO",
            module_name="ITEM_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/item_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_SLOT_ENCHANT_INFO.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        
        # 批次32: 杂项数据结构
        tracker.add_batch(
            batch_name="batch_32",
            batch_number=32,
            description="杂项数据结构（PT_CUSTOM_DATA、PT_MONSTER_EXP、PT_HIDDEN_CHATTING）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=3,
            migrated_files=3,
            start_date="2026-02-10",
            actual_end_date="2026-02-10",
            notes="迁移了PT_CUSTOM_DATA、PT_MONSTER_EXP和PT_HIDDEN_CHATTING三个杂项数据结构"
        )
        
        # 添加批次32的文件
        batch_32_id = tracker.get_batch_by_name("batch_32").id
        tracker.add_file(
            batch_id=batch_32_id,
            file_name="PT_CUSTOM_DATA",
            module_name="MISC_DATA_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/misc_data_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_CUSTOM_DATA.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_32_id,
            file_name="PT_MONSTER_EXP",
            module_name="MISC_DATA_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/misc_data_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_MONSTER_EXP.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_32_id,
            file_name="PT_HIDDEN_CHATTING",
            module_name="MISC_DATA_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/misc_data_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_HIDDEN_CHATTING.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        
        # 批次33: 系统杂项数据结构
        tracker.add_batch(
            batch_name="batch_33",
            batch_number=33,
            description="系统杂项数据结构（PT_SYSCMD_COMMAND、PT_GUILD_BUFF_UPGRADE_COST、PT_GUILD_DONATION_ACCUMULATE_REWARD）",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            total_files=3,
            migrated_files=3,
            start_date="2026-02-10",
            actual_end_date="2026-02-10",
            notes="迁移了PT_SYSCMD_COMMAND、PT_GUILD_BUFF_UPGRADE_COST和PT_GUILD_DONATION_ACCUMULATE_REWARD三个系统杂项数据结构"
        )
        
        # 添加批次33的文件
        batch_33_id = tracker.get_batch_by_name("batch_33").id
        tracker.add_file(
            batch_id=batch_33_id,
            file_name="PT_SYSCMD_COMMAND",
            module_name="SYSTEM_MISC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/system_misc_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_COMMAND.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_33_id,
            file_name="PT_GUILD_BUFF_UPGRADE_COST",
            module_name="SYSTEM_MISC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/system_misc_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_GUILD_BUFF_UPGRADE_COST.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        tracker.add_file(
            batch_id=batch_33_id,
            file_name="PT_GUILD_DONATION_ACCUMULATE_REWARD",
            module_name="SYSTEM_MISC_TYPES",
            status=MigrationStatus.COMPLETED.value,
            priority=4,
            proto_file="proto/dnf/v1/system_misc_types.proto",
            java_file="src/main/java/com/dnfm/mina/protobuf/PT_GUILD_DONATION_ACCUMULATE_REWARD.java",
            has_test=True,
            test_passed=True,
            completion_date="2026-02-10"
        )
        
        print("✅ 成功添加批次22-33到数据库")


if __name__ == '__main__':
    add_batch_22_33()
