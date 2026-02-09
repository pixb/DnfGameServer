#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次37到数据库
"""

import sys
from pathlib import Path

# 添加项目根目录到Python路径
project_root = Path(__file__).parent.parent.parent.parent
sys.path.insert(0, str(project_root / 'devdoc' / 'protobuf' / 'reports' / 'scripts'))

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_37():
    """添加批次37到数据库"""
    with MigrationTracker() as tracker:
        # 检查批次是否已存在
        existing = tracker.get_batch_by_name("batch_37")
        if existing:
            print(f"⚠️  批次 batch_37 已存在，将更新其状态")
            tracker.update_batch(existing.id, status="completed")
            print(f"✅ 批次 batch_37 状态已更新为 completed")
            return

        # 创建批次37
        batch = Batch(
            id=None,
            batch_name="batch_37",
            batch_number=37,
            description="婚礼系统、世界突袭系统、观看书签系统、验证系统",
            status="completed",
            priority=5,
            total_files=12,
            migrated_files=12,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="包含婚礼、世界突袭、观看书签和验证系统的消息定义",
            created_at=None,
            updated_at=None
        )

        batch_id = tracker.create_batch(batch)
        print(f"✅ 批次 batch_37 已创建，ID: {batch_id}")

        # 添加文件
        files = [
            # 婚礼系统
            ("PT_WEDDING_GUESTBOOK", "WeddingGuestbook", None, "social_events.proto", "batch37_test.go"),
            ("PT_WEDDING_INVITATION", "WeddingInvitation", None, "social_events.proto", "batch37_test.go"),
            ("PT_WEDDING_ATTENDANCE", "WeddingAttendance", None, "social_events.proto", "batch37_test.go"),
            ("PT_WEDDING_THEME", "WeddingTheme", None, "social_events.proto", "batch37_test.go"),
            ("PT_WEDDING_THEME_CEREMONY", "WeddingThemeCeremony", None, "social_events.proto", "batch37_test.go"),
            ("PT_WEDDING_PREPARATION", "WeddingPreparation", None, "social_events.proto", "batch37_test.go"),
            ("PT_WEDDING_MONEYGIFT_RANKING", "WeddingMoneygiftRanking", None, "social_events.proto", "batch37_test.go"),
            # 世界突袭系统
            ("PT_WORLD_RAID_INFO", "WorldRaidInfo", None, "social_events.proto", "batch37_test.go"),
            ("PT_WORLD_RAID_RANKING", "WorldRaidRanking", None, "social_events.proto", "batch37_test.go"),
            # 观看书签系统
            ("PT_WATCHING_BOOKMARK", "WatchingBookmark", None, "social_events.proto", "batch37_test.go"),
            # 验证系统
            ("PT_VERIFICATION", "Verification", None, "social_events.proto", "batch37_test.go"),
            ("PT_VERIFICATION_ADD_DAMAGE_DATA", "VerificationAddDamageData", None, "social_events.proto", "batch37_test.go"),
        ]

        for file_name, module_name, module_id, proto_file, test_file in files:
            file = MigrationFile(
                id=None,
                batch_id=batch_id,
                file_name=file_name,
                module_name=module_name,
                module_id=module_id,
                status="completed",
                priority=5,
                proto_file=proto_file,
                java_file=None,
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
            print(f"✅ 文件 {file_name} 已添加")

        print(f"\n📦 批次37添加完成，共 {len(files)} 个文件")

if __name__ == "__main__":
    add_batch_37()
