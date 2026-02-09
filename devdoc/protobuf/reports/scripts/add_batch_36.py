#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次36到数据库
"""

import sys
from pathlib import Path

# 添加项目根目录到Python路径
project_root = Path(__file__).parent.parent.parent.parent
sys.path.insert(0, str(project_root / 'devdoc' / 'protobuf' / 'reports' / 'scripts'))

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_36():
    """添加批次36到数据库"""
    with MigrationTracker() as tracker:
        # 检查批次是否已存在
        existing = tracker.get_batch_by_name("batch_36")
        if existing:
            print(f"⚠️  批次 batch_36 已存在，将更新其状态")
            tracker.update_batch(existing.id, status="completed")
            print(f"✅ 批次 batch_36 状态已更新为 completed")
            return

        # 创建批次36
        batch = Batch(
            id=None,
            batch_name="batch_36",
            batch_number=36,
            description="游戏系统（塔、炼金、衣柜、称号）",
            status="completed",
            priority=5,
            total_files=8,
            migrated_files=8,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date="2026-02-10",
            blocker=None,
            notes="包含塔系统、炼金系统、衣柜系统和称号系统的消息定义",
            created_at=None,
            updated_at=None
        )

        batch_id = tracker.create_batch(batch)
        print(f"✅ 批次 batch_36 已创建，ID: {batch_id}")

        # 添加文件
        files = [
            ("PT_TOWER_INFO", "TowerInfo", 11080, "gamesystems.proto", "batch36_test.go"),
            ("PT_TOWER_OF_ILLUSION_CLEAR_RATE", "TowerOfIllusionClearRate", None, "gamesystems.proto", "batch36_test.go"),
            ("PT_TONIC_INFO", "TonicInfo", 11039, "gamesystems.proto", "batch36_test.go"),
            ("PT_TONIC_MATERIAL_USAGE", "TonicMaterialUsage", None, "gamesystems.proto", "batch36_test.go"),
            ("PT_WARDROBE_INFO", "WardrobeInfo", 14081, "gamesystems.proto", "batch36_test.go"),
            ("PT_AVATAR_MANNEQUIN_INFO", "AvatarMannequinInfo", None, "gamesystems.proto", "batch36_test.go"),
            ("PT_AVATAR_MANNEQUIN_PART_INFO", "AvatarMannequinPartInfo", None, "gamesystems.proto", "batch36_test.go"),
            ("PT_REMOVEITEMS", "RemoveItems", None, "gamesystems.proto", "batch36_test.go"),
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

        print(f"\n📦 批次36添加完成，共 {len(files)} 个文件")

if __name__ == "__main__":
    add_batch_36()
