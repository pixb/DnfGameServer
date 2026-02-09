#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次22-33到迁移追踪系统（简化版）
"""

import sys
import sqlite3

def add_batches_22_33():
    """添加批次22-33到数据库"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/migration_progress.db'
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()
    
    # 批次数据
    batches_data = [
        ("batch_22", 22, "STREAM_DATA 和 USER_INFO 模块", "completed", 5, 2, 2, "2026-02-09", None, "2026-02-09", None, "迁移了STREAM_DATA和USER_INFO两个模块"),
        ("batch_23", 23, "通用数据结构（PT_GUILD_SYMBOL、PT_SD_AVATAR_ITEM、PT_RANKING）", "completed", 4, 3, 3, "2026-02-09", None, "2026-02-09", None, "迁移了PT_GUILD_SYMBOL、PT_SD_AVATAR_ITEM和PT_RANKING三个通用数据结构"),
        ("batch_24", 24, "基础数据结构（AUTH_INFO、CHARACTER_INFO、BASE_CHAT）", "completed", 4, 3, 3, "2026-02-09", None, "2026-02-09", None, "迁移了AUTH_INFO、CHARACTER_INFO和BASE_CHAT三个基础数据结构"),
        ("batch_25", 25, "系统数据结构（Actor、CHAT_BASE、SUBSYSTEM）", "completed", 4, 3, 3, "2026-02-09", None, "2026-02-09", None, "迁移了Actor、CHAT_BASE和SUBSYSTEM三个系统数据结构"),
        ("batch_26", 26, "系统选项数据结构（5个数据结构）", "completed", 4, 5, 5, "2026-02-09", None, "2026-02-09", None, "迁移了5个系统选项数据结构"),
        ("batch_27", 27, "杂项数据结构（PT_CERA_SHOP_MONEY、PT_DINING_PAY、PT_GUARDIAN_ORDER）", "completed", 4, 3, 3, "2026-02-09", None, "2026-02-09", None, "迁移了PT_CERA_SHOP_MONEY、PT_DINING_PAY和PT_GUARDIAN_ORDER三个杂项数据结构"),
        ("batch_28", 28, "社交数据结构（4个数据结构）", "completed", 4, 4, 4, "2026-02-09", None, "2026-02-09", None, "迁移了PT_MENTEE_INFO、PT_MENTOR_QUEST_INFO、PT_AUCTION_ITEM_PRICE_INFO和PT_CHARACTER_SLOT_INFO四个社交数据结构"),
        ("batch_29", 29, "游戏玩法数据结构（PT_SKILL、PT_DROP_OBJECT_GOLD）", "completed", 4, 2, 2, "2026-02-10", None, "2026-02-10", None, "迁移了PT_SKILL和PT_DROP_OBJECT_GOLD两个游戏玩法数据结构"),
        ("batch_30", 30, "库存数据结构（PT_STACKABLE、PT_REPURCHASE_STACKABLE）", "completed", 4, 2, 2, "2026-02-10", None, "2026-02-10", None, "迁移了PT_STACKABLE和PT_REPURCHASE_STACKABLE两个库存数据结构"),
        ("batch_31", 31, "物品数据结构（PT_ITEM_GUID、PT_SLOT_ENCHANT_EXP、PT_SLOT_ENCHANT_INFO）", "completed", 4, 3, 3, "2026-02-10", None, "2026-02-10", None, "迁移了PT_ITEM_GUID、PT_SLOT_ENCHANT_EXP和PT_SLOT_ENCHANT_INFO三个物品数据结构"),
        ("batch_32", 32, "杂项数据结构（PT_CUSTOM_DATA、PT_MONSTER_EXP、PT_HIDDEN_CHATTING）", "completed", 4, 3, 3, "2026-02-10", None, "2026-02-10", None, "迁移了PT_CUSTOM_DATA、PT_MONSTER_EXP和PT_HIDDEN_CHATTING三个杂项数据结构"),
        ("batch_33", 33, "系统杂项数据结构（3个数据结构）", "completed", 4, 3, 3, "2026-02-10", None, "2026-02-10", None, "迁移了PT_SYSCMD_COMMAND、PT_GUILD_BUFF_UPGRADE_COST和PT_GUILD_DONATION_ACCUMULATE_REWARD三个系统杂项数据结构"),
    ]
    
    # 插入批次数据
    for batch_data in batches_data:
        try:
            cursor.execute('''
                INSERT OR REPLACE INTO batches 
                (batch_name, batch_number, description, status, priority, total_files, migrated_files, start_date, planned_end_date, actual_end_date, blocker, notes)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            ''', batch_data)
            print(f"✅ 添加批次: {batch_data[0]}")
        except Exception as e:
            print(f"❌ 添加批次 {batch_data[0]} 失败: {e}")
    
    conn.commit()
    
    # 获取批次ID并添加文件数据
    batch_ids = {}
    for batch_name in [b[0] for b in batches_data]:
        cursor.execute('SELECT id FROM batches WHERE batch_name = ?', (batch_name,))
        row = cursor.fetchone()
        if row:
            batch_ids[batch_name] = row[0]
    
    # 文件数据 (batch_name, file_name, module_name, status, priority, proto_file, java_file, has_test, test_passed, completion_date)
    files_data = [
        # 批次22
        ("batch_22", "STREAM_DATA", "STREAM_DATA", "completed", 5, "proto/dnf/v1/stream_data.proto", "src/main/java/com/dnfm/mina/protobuf/STREAM_DATA.java", 1, 1, "2026-02-09"),
        ("batch_22", "USER_INFO", "USER_INFO", "completed", 5, "proto/dnf/v1/user_info.proto", "src/main/java/com/dnfm/mina/protobuf/USER_INFO.java", 1, 1, "2026-02-09"),
        # 批次23
        ("batch_23", "PT_GUILD_SYMBOL", "COMMON_TYPES", "completed", 4, "proto/dnf/v1/common_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_GUILD_SYMBOL.java", 1, 1, "2026-02-09"),
        ("batch_23", "PT_SD_AVATAR_ITEM", "COMMON_TYPES", "completed", 4, "proto/dnf/v1/common_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SD_AVATAR_ITEM.java", 1, 1, "2026-02-09"),
        ("batch_23", "PT_RANKING", "COMMON_TYPES", "completed", 4, "proto/dnf/v1/common_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_RANKING.java", 1, 1, "2026-02-09"),
        # 批次24
        ("batch_24", "AUTH_INFO", "BASIC_TYPES", "completed", 4, "proto/dnf/v1/basic_types.proto", "src/main/java/com/dnfm/mina/protobuf/AUTH_INFO.java", 1, 1, "2026-02-09"),
        ("batch_24", "CHARACTER_INFO", "BASIC_TYPES", "completed", 4, "proto/dnf/v1/basic_types.proto", "src/main/java/com/dnfm/mina/protobuf/CHARACTER_INFO.java", 1, 1, "2026-02-09"),
        ("batch_24", "BASE_CHAT", "BASIC_TYPES", "completed", 4, "proto/dnf/v1/basic_types.proto", "src/main/java/com/dnfm/mina/protobuf/BASE_CHAT.java", 1, 1, "2026-02-09"),
        # 批次25
        ("batch_25", "Actor", "SYSTEM_TYPES", "completed", 4, "proto/dnf/v1/system_types.proto", "src/main/java/com/dnfm/mina/protobuf/Actor.java", 1, 1, "2026-02-09"),
        ("batch_25", "CHAT_BASE", "SYSTEM_TYPES", "completed", 4, "proto/dnf/v1/system_types.proto", "src/main/java/com/dnfm/mina/protobuf/CHAT_BASE.java", 1, 1, "2026-02-09"),
        ("batch_25", "SUBSYSTEM", "SYSTEM_TYPES", "completed", 4, "proto/dnf/v1/system_types.proto", "src/main/java/com/dnfm/mina/protobuf/SUBSYSTEM.java", 1, 1, "2026-02-09"),
        # 批次26
        ("batch_26", "RES_SYSTEM_BUFF_APPENDAGE_LIST", "OPTION_TYPES", "completed", 4, "proto/dnf/v1/option_types.proto", "src/main/java/com/dnfm/mina/protobuf/RES_SYSTEM_BUFF_APPENDAGE_LIST.java", 1, 1, "2026-02-09"),
        ("batch_26", "PT_SYSTEM_BUFF_APPENDAGE", "OPTION_TYPES", "completed", 4, "proto/dnf/v1/option_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSTEM_BUFF_APPENDAGE.java", 1, 1, "2026-02-09"),
        ("batch_26", "PT_BATTLE_OPTION", "OPTION_TYPES", "completed", 4, "proto/dnf/v1/option_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_BATTLE_OPTION.java", 1, 1, "2026-02-09"),
        ("batch_26", "ENUM_BATTLE_OPTION", "OPTION_TYPES", "completed", 4, "proto/dnf/v1/option_types.proto", "src/main/java/com/dnfm/mina/protobuf/ENUM_BATTLE_OPTION.java", 1, 1, "2026-02-09"),
        ("batch_26", "RES_RANDOMOPTION_SELECT", "OPTION_TYPES", "completed", 4, "proto/dnf/v1/option_types.proto", "src/main/java/com/dnfm/mina/protobuf/RES_RANDOMOPTION_SELECT.java", 1, 1, "2026-02-09"),
        # 批次27
        ("batch_27", "PT_CERA_SHOP_MONEY", "MISC_TYPES", "completed", 4, "proto/dnf/v1/misc_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CERA_SHOP_MONEY.java", 1, 1, "2026-02-09"),
        ("batch_27", "PT_DINING_PAY", "MISC_TYPES", "completed", 4, "proto/dnf/v1/misc_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DINING_PAY.java", 1, 1, "2026-02-09"),
        ("batch_27", "PT_GUARDIAN_ORDER", "MISC_TYPES", "completed", 4, "proto/dnf/v1/misc_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_GUARDIAN_ORDER.java", 1, 1, "2026-02-09"),
        # 批次28
        ("batch_28", "PT_MENTEE_INFO", "SOCIAL_TYPES", "completed", 4, "proto/dnf/v1/social_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_MENTEE_INFO.java", 1, 1, "2026-02-09"),
        ("batch_28", "PT_MENTOR_QUEST_INFO", "SOCIAL_TYPES", "completed", 4, "proto/dnf/v1/social_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_MENTOR_QUEST_INFO.java", 1, 1, "2026-02-09"),
        ("batch_28", "PT_AUCTION_ITEM_PRICE_INFO", "SOCIAL_TYPES", "completed", 4, "proto/dnf/v1/social_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_AUCTION_ITEM_PRICE_INFO.java", 1, 1, "2026-02-09"),
        ("batch_28", "PT_CHARACTER_SLOT_INFO", "SOCIAL_TYPES", "completed", 4, "proto/dnf/v1/social_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CHARACTER_SLOT_INFO.java", 1, 1, "2026-02-09"),
        # 批次29
        ("batch_29", "PT_SKILL", "GAMEPLAY_TYPES", "completed", 4, "proto/dnf/v1/gameplay_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SKILL.java", 1, 1, "2026-02-10"),
        ("batch_29", "PT_DROP_OBJECT_GOLD", "GAMEPLAY_TYPES", "completed", 4, "proto/dnf/v1/gameplay_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_DROP_OBJECT_GOLD.java", 1, 1, "2026-02-10"),
        # 批次30
        ("batch_30", "PT_STACKABLE", "INVENTORY_TYPES", "completed", 4, "proto/dnf/v1/inventory_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_STACKABLE.java", 1, 1, "2026-02-10"),
        ("batch_30", "PT_REPURCHASE_STACKABLE", "INVENTORY_TYPES", "completed", 4, "proto/dnf/v1/inventory_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_REPURCHASE_STACKABLE.java", 1, 1, "2026-02-10"),
        # 批次31
        ("batch_31", "PT_ITEM_GUID", "ITEM_TYPES", "completed", 4, "proto/dnf/v1/item_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_ITEM_GUID.java", 1, 1, "2026-02-10"),
        ("batch_31", "PT_SLOT_ENCHANT_EXP", "ITEM_TYPES", "completed", 4, "proto/dnf/v1/item_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SLOT_ENCHANT_EXP.java", 1, 1, "2026-02-10"),
        ("batch_31", "PT_SLOT_ENCHANT_INFO", "ITEM_TYPES", "completed", 4, "proto/dnf/v1/item_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SLOT_ENCHANT_INFO.java", 1, 1, "2026-02-10"),
        # 批次32
        ("batch_32", "PT_CUSTOM_DATA", "MISC_DATA_TYPES", "completed", 4, "proto/dnf/v1/misc_data_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_CUSTOM_DATA.java", 1, 1, "2026-02-10"),
        ("batch_32", "PT_MONSTER_EXP", "MISC_DATA_TYPES", "completed", 4, "proto/dnf/v1/misc_data_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_MONSTER_EXP.java", 1, 1, "2026-02-10"),
        ("batch_32", "PT_HIDDEN_CHATTING", "MISC_DATA_TYPES", "completed", 4, "proto/dnf/v1/misc_data_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_HIDDEN_CHATTING.java", 1, 1, "2026-02-10"),
        # 批次33
        ("batch_33", "PT_SYSCMD_COMMAND", "SYSTEM_MISC_TYPES", "completed", 4, "proto/dnf/v1/system_misc_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_SYSCMD_COMMAND.java", 1, 1, "2026-02-10"),
        ("batch_33", "PT_GUILD_BUFF_UPGRADE_COST", "SYSTEM_MISC_TYPES", "completed", 4, "proto/dnf/v1/system_misc_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_GUILD_BUFF_UPGRADE_COST.java", 1, 1, "2026-02-10"),
        ("batch_33", "PT_GUILD_DONATION_ACCUMULATE_REWARD", "SYSTEM_MISC_TYPES", "completed", 4, "proto/dnf/v1/system_misc_types.proto", "src/main/java/com/dnfm/mina/protobuf/PT_GUILD_DONATION_ACCUMULATE_REWARD.java", 1, 1, "2026-02-10"),
    ]
    
    # 插入文件数据
    for file_data in files_data:
        batch_name = file_data[0]
        if batch_name in batch_ids:
            batch_id = batch_ids[batch_name]
            try:
                cursor.execute('''
                    INSERT OR REPLACE INTO migration_files 
                    (batch_id, file_name, module_name, status, priority, proto_file, java_file, has_test, test_passed, completion_date)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                ''', (batch_id,) + file_data[1:])
                print(f"✅ 添加文件: {batch_name} - {file_data[1]}")
            except Exception as e:
                print(f"❌ 添加文件 {batch_name} - {file_data[1]} 失败: {e}")
    
    conn.commit()
    conn.close()
    print("\n✅ 成功添加批次22-33到数据库")


if __name__ == '__main__':
    add_batches_22_33()
