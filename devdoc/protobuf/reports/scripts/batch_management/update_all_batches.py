#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
更新迁移数据库，导入所有12个批次的实际进度
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile, Issue
from datetime import datetime

def update_all_batches():
    """更新所有12个批次的实际进度"""
    
    with MigrationTracker() as tracker:
        print("🔄 正在更新迁移进度数据库...")
        print("=" * 80)
        
        # 批次信息：(名称, 序号, 描述, 状态, 优先级, 总文件数, 已迁移数, 开始日期, 完成日期)
        batches_info = [
            # 已完成批次
            ("batch_01", 1, "登录认证模块", "completed", 10, 5, 5, "2026-02-08", "2026-02-09"),
            ("batch_02", 2, "会话管理模块(PING)", "completed", 9, 1, 1, "2026-02-09", "2026-02-09"),
            ("batch_03", 3, "角色列表管理", "completed", 9, 2, 2, "2026-02-09", "2026-02-09"),
            ("batch_04", 4, "创建角色/频道列表/进入频道", "completed", 9, 5, 5, "2026-02-09", "2026-02-09"),
            ("batch_05", 5, "待机/删除角色/开始游戏/退出角色", "completed", 8, 8, 8, "2026-02-09", "2026-02-09"),
            ("batch_06", 6, "认证密钥刷新/平台资料更新", "completed", 8, 4, 4, "2026-02-09", "2026-02-09"),
            ("batch_07", 7, "战斗服务器/IDIP禁止/服务器数据", "completed", 8, 8, 8, "2026-02-09", "2026-02-09"),
            ("batch_08", 8, "城镇相关消息(进入/离开/角色信息)", "completed", 7, 14, 14, "2026-02-09", "2026-02-09"),
            ("batch_09", 9, "MAIL邮件模块", "completed", 7, 12, 12, "2026-02-09", "2026-02-09"),
            
            # 进行中和计划中批次
            ("batch_10", 10, "物品相关消息", "in_progress", 8, 12, 0, "2026-02-09", None),
            ("batch_11", 11, "待规划", "pending", 6, 0, 0, None, None),
            ("batch_12", 12, "待规划", "pending", 6, 0, 0, None, None),
        ]
        
        updated_count = 0
        created_count = 0
        
        for batch_name, number, desc, status, priority, total, migrated, start, end in batches_info:
            # 检查批次是否已存在
            existing = tracker.get_batch_by_name(batch_name)
            
            if existing:
                # 更新现有批次
                tracker.update_batch(
                    existing.id,
                    description=desc,
                    status=status,
                    priority=priority,
                    total_files=total,
                    migrated_files=migrated,
                    start_date=start,
                    actual_end_date=end
                )
                updated_count += 1
                print(f"📝 更新批次: {batch_name} - {desc} ({status})")
            else:
                # 创建新批次
                batch = Batch(
                    id=None,
                    batch_name=batch_name,
                    batch_number=number,
                    description=desc,
                    status=status,
                    priority=priority,
                    total_files=total,
                    migrated_files=migrated,
                    start_date=start,
                    planned_end_date=None,
                    actual_end_date=end,
                    blocker=None,
                    notes=None,
                    created_at=None,
                    updated_at=None
                )
                tracker.create_batch(batch)
                created_count += 1
                print(f"✅ 创建批次: {batch_name} - {desc} ({status})")
        
        print(f"\n批次更新完成: 更新 {updated_count} 个, 创建 {created_count} 个")
        
        # 添加批次01的文件详情
        print("\n📁 添加批次01的迁移文件...")
        batch1 = tracker.get_batch_by_name("batch_01")
        if batch1:
            files_01 = [
                ("ChannelInfo.java", "LOGIN", 10000, "completed"),
                ("IntrudeMemberInfo.java", "LOGIN", 10000, "completed"),
                ("IntrudeInfo.java", "LOGIN", 10000, "completed"),
                ("REQ_LOGIN.java", "LOGIN", 10000, "completed"),
                ("RES_LOGIN.java", "LOGIN", 10000, "completed"),
            ]
            for fname, module, mid, status in files_01:
                f = MigrationFile(
                    id=None,
                    batch_id=batch1.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=10,
                    proto_file=f"proto/dnf/v1/auth_login.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次01 - {module}模块",
                    start_date="2026-02-08",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass  # 文件可能已存在
        
        # 添加批次03的文件详情(CHARACTER)
        print("📁 添加批次03(CHARACTER)的迁移文件...")
        batch3 = tracker.get_batch_by_name("batch_03")
        if batch3:
            files_03 = [
                ("REQ_CHARAC_LIST.java", "CHARACTER", 10002, "completed"),
                ("RES_CHARAC_LIST.java", "CHARACTER", 10002, "completed"),
            ]
            for fname, module, mid, status in files_03:
                f = MigrationFile(
                    id=None,
                    batch_id=batch3.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=9,
                    proto_file=f"proto/dnf/v1/character.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次03 - 角色列表管理",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 添加批次04的文件详情
        print("📁 添加批次04(创建角色/频道)的迁移文件...")
        batch4 = tracker.get_batch_by_name("batch_04")
        if batch4:
            files_04 = [
                ("REQ_CREATE_CHARACTER.java", "CHARACTER", 10003, "completed"),
                ("RES_CREATE_CHARACTER.java", "CHARACTER", 10003, "completed"),
                ("REQ_CHANNEL_LIST.java", "CHANNEL", 10008, "completed"),
                ("RES_CHANNEL_LIST.java", "CHANNEL", 10008, "completed"),
                ("REQ_ENTER_CHANNEL.java", "CHANNEL", 10011, "completed"),
            ]
            for fname, module, mid, status in files_04:
                f = MigrationFile(
                    id=None,
                    batch_id=batch4.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=9,
                    proto_file=f"proto/dnf/v1/{'character' if 'CHARACTER' in fname else 'channel'}.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次04 - 创建角色和频道管理",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 添加批次05的文件详情
        print("📁 添加批次05(待机/删除角色/游戏)的迁移文件...")
        batch5 = tracker.get_batch_by_name("batch_05")
        if batch5:
            files_05 = [
                ("REQ_STANDBY.java", "CHARACTER", 10001, "completed"),
                ("RES_STANDBY.java", "CHARACTER", 10001, "completed"),
                ("REQ_REMOVE_CHARACTER.java", "CHARACTER", 10004, "completed"),
                ("RES_REMOVE_CHARACTER.java", "CHARACTER", 10004, "completed"),
                ("REQ_START_GAME.java", "CHARACTER", 10005, "completed"),
                ("REQ_EXIT_CHARACTER.java", "CHARACTER", 10007, "completed"),
            ]
            for fname, module, mid, status in files_05:
                f = MigrationFile(
                    id=None,
                    batch_id=batch5.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=8,
                    proto_file=f"proto/dnf/v1/character.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次05 - 待机/删除角色/开始游戏",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 添加批次06的文件详情
        print("📁 添加批次06(认证/平台)的迁移文件...")
        batch6 = tracker.get_batch_by_name("batch_06")
        if batch6:
            files_06 = [
                ("REQ_AUTHKEY_REFRESH.java", "AUTH", 10009, "completed"),
                ("RES_AUTHKEY_REFRESH.java", "AUTH", 10009, "completed"),
                ("REQ_PLATFORM_PROFILE_UPDATE.java", "PLATFORM", 10012, "completed"),
                ("RES_PLATFORM_PROFILE_UPDATE.java", "PLATFORM", 10012, "completed"),
            ]
            for fname, module, mid, status in files_06:
                f = MigrationFile(
                    id=None,
                    batch_id=batch6.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=8,
                    proto_file=f"proto/dnf/v1/{module.lower()}.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次06 - 认证和平台资料",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 添加批次07的文件详情
        print("📁 添加批次07(战斗服务器/IDIP/服务器数据)的迁移文件...")
        batch7 = tracker.get_batch_by_name("batch_07")
        if batch7:
            files_07 = [
                ("REQ_CONNECT_BATTLE_SERVER.java", "BATTLE", 10014, "completed"),
                ("RES_CONNECT_BATTLE_SERVER.java", "BATTLE", 10014, "completed"),
                ("REQ_IDIP_PROHIBIT_LIST.java", "IDIP", 10017, "completed"),
                ("RES_IDIP_PROHIBIT_LIST.java", "IDIP", 10017, "completed"),
                ("REQ_LOAD_SERVER_SIMPLE_DATA.java", "SERVER_DATA", 10031, "completed"),
                ("RES_LOAD_SERVER_SIMPLE_DATA.java", "SERVER_DATA", 10031, "completed"),
                ("REQ_SAVE_SERVER_SIMPLE_DATA.java", "SERVER_DATA", 10032, "completed"),
                ("RES_SAVE_SERVER_SIMPLE_DATA.java", "SERVER_DATA", 10032, "completed"),
            ]
            for fname, module, mid, status in files_07:
                proto_file = "battle.proto" if "BATTLE" in fname else "idip.proto" if "IDIP" in fname else "server_data.proto"
                f = MigrationFile(
                    id=None,
                    batch_id=batch7.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=8,
                    proto_file=f"proto/dnf/v1/{proto_file}",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次07 - 战斗服务器和服务器数据",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 添加批次08的文件详情
        print("📁 添加批次08(城镇相关)的迁移文件...")
        batch8 = tracker.get_batch_by_name("batch_08")
        if batch8:
            files_08 = [
                ("REQ_ENTER_TO_TOWN.java", "TOWN", 10100, "completed"),
                ("RES_ENTER_TO_TOWN.java", "TOWN", 10100, "completed"),
                ("REQ_LEAVE_FROM_TOWN.java", "TOWN", 10109, "completed"),
                ("RES_LEAVE_FROM_TOWN.java", "TOWN", 10109, "completed"),
                ("REQ_CHARACTER_INFO.java", "TOWN", 10103, "completed"),
                ("RES_CHARACTER_INFO.java", "TOWN", 10103, "completed"),
                ("REQ_TOWN_USER_GUID_LIST.java", "TOWN", 10106, "completed"),
                ("RES_TOWN_USER_GUID_LIST.java", "TOWN", 10106, "completed"),
                ("REQ_TARGET_USER_DETAIL_INFO.java", "TOWN", 10107, "completed"),
                ("RES_TARGET_USER_DETAIL_INFO.java", "TOWN", 10107, "completed"),
                ("REQ_INTERACTION_MENU.java", "TOWN", 10108, "completed"),
                ("RES_INTERACTION_MENU.java", "TOWN", 10108, "completed"),
            ]
            for fname, module, mid, status in files_08:
                f = MigrationFile(
                    id=None,
                    batch_id=batch8.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=7,
                    proto_file=f"proto/dnf/v1/town.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次08 - 城镇相关消息",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 添加批次09的文件详情
        print("📁 添加批次09(MAIL邮件模块)的迁移文件...")
        batch9 = tracker.get_batch_by_name("batch_09")
        if batch9:
            files_09 = [
                ("REQ_MAIL_LIST.java", "MAIL", 15001, "completed"),
                ("RES_MAIL_LIST.java", "MAIL", 15001, "completed"),
                ("REQ_MAIL_GET.java", "MAIL", 15002, "completed"),
                ("RES_MAIL_GET.java", "MAIL", 15002, "completed"),
                ("REQ_MAIL_READ.java", "MAIL", 15003, "completed"),
                ("RES_MAIL_READ.java", "MAIL", 15003, "completed"),
                ("REQ_MAIL_DELETE.java", "MAIL", 15004, "completed"),
                ("RES_MAIL_DELETE.java", "MAIL", 15004, "completed"),
                ("REQ_MAIL_ITEM_ALL_GET.java", "MAIL", 15005, "completed"),
                ("RES_MAIL_ITEM_ALL_GET.java", "MAIL", 15005, "completed"),
                ("REQ_MAIL_ALL_DELETE.java", "MAIL", 15006, "completed"),
                ("RES_MAIL_ALL_DELETE.java", "MAIL", 15006, "completed"),
            ]
            for fname, module, mid, status in files_09:
                f = MigrationFile(
                    id=None,
                    batch_id=batch9.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=7,
                    proto_file=f"proto/dnf/v1/mail.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次09 - MAIL邮件模块",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 添加批次10的文件（计划中）
        print("📁 添加批次10(物品模块)的待迁移文件...")
        batch10 = tracker.get_batch_by_name("batch_10")
        if batch10:
            files_10 = [
                ("REQ_ITEM_USE.java", "ITEM", 14017, "pending"),
                ("RES_ITEM_USE.java", "ITEM", 14017, "pending"),
                ("REQ_ITEM_REINFORCE.java", "ITEM", 14006, "pending"),
                ("RES_ITEM_REINFORCE.java", "ITEM", 14006, "pending"),
                ("REQ_ITEM_LIST.java", "ITEM", 14000, "pending"),
                ("RES_ITEM_LIST.java", "ITEM", 14000, "pending"),
                ("REQ_ITEM_MOVE.java", "ITEM", 14001, "pending"),
                ("RES_ITEM_MOVE.java", "ITEM", 14001, "pending"),
                ("REQ_ITEM_DROP.java", "ITEM", 14002, "pending"),
                ("RES_ITEM_DROP.java", "ITEM", 14002, "pending"),
                ("REQ_ITEM_SPLIT.java", "ITEM", 14003, "pending"),
                ("RES_ITEM_SPLIT.java", "ITEM", 14003, "pending"),
            ]
            for fname, module, mid, status in files_10:
                f = MigrationFile(
                    id=None,
                    batch_id=batch10.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=8,
                    proto_file=None,
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=False,
                    test_passed=False,
                    issues_count=0,
                    migration_notes=f"批次10 - 物品模块(计划中)",
                    start_date=None,
                    completion_date=None,
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 更新批次的文件计数
        print("\n🔄 更新批次文件计数...")
        for batch_name, _, _, _, _, _, _, _, _ in batches_info:
            batch = tracker.get_batch_by_name(batch_name)
            if batch:
                tracker._update_batch_file_count(batch.id)
                tracker._update_batch_migrated_count(batch.id)
        
        print("\n" + "=" * 80)
        print("✅ 数据库更新完成！")
        print("\n当前进度概览:")
        
        # 显示最新进度
        progress = tracker.get_overall_progress()
        print(f"  批次: {progress['batches']['completed']}/{progress['batches']['total']} 完成")
        print(f"  文件: {progress['files']['migrated']}/{progress['files']['total']} 完成")
        print(f"  进度: {progress['files']['progress_percent']}%")
        
        # 按模块统计
        print("\n按模块统计:")
        modules = tracker.get_module_progress()
        for m in modules[:10]:  # 只显示前10个
            print(f"  {m['module_name']:<15}: {m['completed_files']:>3}/{m['total_files']:>3} ({m['progress_percent']:>5.1f}%)")

if __name__ == '__main__':
    update_all_batches()
