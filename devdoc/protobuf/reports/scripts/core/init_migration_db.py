#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
初始化迁移追踪数据库并导入现有批次数据
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile, Issue, MigrationStatus

def init_with_existing_data():
    """初始化数据库并导入现有批次数据"""
    
    with MigrationTracker() as tracker:
        print("🚀 初始化迁移进度数据库...")
        print("=" * 50)
        
        # 创建批次01 - LOGIN
        batch1 = Batch(
            id=None,
            batch_name="batch_01",
            batch_number=1,
            description="登录认证模块迁移",
            status="completed",
            priority=10,
            total_files=5,
            migrated_files=5,
            start_date="2026-02-08",
            planned_end_date="2026-02-09",
            actual_end_date="2026-02-09",
            blocker=None,
            notes="跨语言通信验证通过，双模式编解码器实现完成",
            created_at=None,
            updated_at=None
        )
        b1_id = tracker.create_batch(batch1)
        print(f"✅ 批次01创建成功 (ID: {b1_id})")
        
        # 添加批次01的文件
        files_01 = [
            ("ChannelInfo.java", "LOGIN", 10000),
            ("IntrudeMemberInfo.java", "LOGIN", 10000),
            ("IntrudeInfo.java", "LOGIN", 10000),
            ("LoginRequest.java", "LOGIN", 10000),
            ("LoginResponse.java", "LOGIN", 10000),
        ]
        
        for fname, module, mid in files_01:
            f = MigrationFile(
                id=None,
                batch_id=b1_id,
                file_name=fname,
                module_name=module,
                module_id=mid,
                status="completed",
                priority=10,
                proto_file=f"proto/dnf/v1/auth_login.proto",
                java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                has_test=True,
                test_passed=True,
                issues_count=0,
                migration_notes="迁移成功",
                start_date="2026-02-08",
                completion_date="2026-02-09",
                created_at=None,
                updated_at=None
            )
            tracker.create_file(f)
        print(f"✅ 批次01文件添加完成 ({len(files_01)}个文件)")
        
        # 创建批次02 - SESSION
        batch2 = Batch(
            id=None,
            batch_name="batch_02",
            batch_number=2,
            description="会话管理模块迁移",
            status="completed",
            priority=9,
            total_files=2,
            migrated_files=1,
            start_date="2026-02-09",
            planned_end_date="2026-02-09",
            actual_end_date="2026-02-09",
            blocker="SESSION_LOGOUT使用场景待确认",
            notes="PING消息迁移完成，SESSION_LOGOUT待确认",
            created_at=None,
            updated_at=None
        )
        b2_id = tracker.create_batch(batch2)
        print(f"✅ 批次02创建成功 (ID: {b2_id})")
        
        # 添加批次02的文件
        files_02 = [
            ("REQ_PING.java", "SESSION", 10006, "completed"),
            ("RES_PING.java", "SESSION", 10006, "completed"),
            ("REQ_SESSION_LOGOUT.java", "SESSION", None, "pending"),
            ("RES_SESSION_LOGOUT.java", "SESSION", None, "pending"),
        ]
        
        for fname, module, mid, status in files_02:
            f = MigrationFile(
                id=None,
                batch_id=b2_id,
                file_name=fname,
                module_name=module,
                module_id=mid,
                status=status,
                priority=8,
                proto_file=f"proto/dnf/v1/session.proto" if status == "completed" else None,
                java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                has_test=status == "completed",
                test_passed=status == "completed",
                issues_count=0 if status == "completed" else 1,
                migration_notes="迁移成功" if status == "completed" else "使用场景待确认",
                start_date="2026-02-09" if status == "completed" else None,
                completion_date="2026-02-09" if status == "completed" else None,
                created_at=None,
                updated_at=None
            )
            tracker.create_file(f)
        print(f"✅ 批次02文件添加完成 ({len(files_02)}个文件)")
        
        # 添加示例问题
        issue1 = Issue(
            id=None,
            batch_id=b2_id,
            file_id=None,
            title="SESSION_LOGOUT使用场景待确认",
            description="没有找到REQ/RES类，只有Types.REQ和Types.RES。没有找到Module ID定义，也没有找到实际使用场景。",
            solution=None,
            status="open",
            severity="medium",
            tags='["usage", "module_id"]',
            created_at=None,
            resolved_at=None
        )
        tracker.create_issue(issue1)
        print(f"✅ 示例问题添加完成")
        
        # 创建批次03-10的占位符
        future_batches = [
            ("batch_03", 3, "角色管理模块", "in_progress", 8),
            ("batch_04", 4, "物品系统模块", "pending", 8),
            ("batch_05", 5, "技能系统模块", "pending", 7),
            ("batch_06", 6, "副本系统模块", "pending", 7),
            ("batch_07", 7, "聊天系统模块", "pending", 6),
            ("batch_08", 8, "邮件系统模块", "pending", 6),
            ("batch_09", 9, "好友系统模块", "pending", 5),
            ("batch_10", 10, "宠物系统模块", "pending", 5),
        ]
        
        for name, num, desc, status, priority in future_batches:
            batch = Batch(
                id=None,
                batch_name=name,
                batch_number=num,
                description=desc,
                status=status,
                priority=priority,
                total_files=0,
                migrated_files=0,
                start_date=None,
                planned_end_date=None,
                actual_end_date=None,
                blocker=None,
                notes=None,
                created_at=None,
                updated_at=None
            )
            tracker.create_batch(batch)
        print(f"✅ 未来批次创建完成 ({len(future_batches)}个批次)")
        
        print("\n" + "=" * 50)
        print("✨ 数据库初始化完成！")
        print("\n可用命令:")
        print("  python migration_tracker.py progress        - 查看整体进度")
        print("  python migration_tracker.py list_batches    - 列出所有批次")
        print("  python migration_tracker.py batch batch_01  - 查看批次详情")

if __name__ == '__main__':
    init_with_existing_data()
