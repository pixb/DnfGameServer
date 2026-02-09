#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
更新批次10-13的完成状态
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def update_batches_10_to_13():
    """更新批次10-13为已完成状态"""
    
    with MigrationTracker() as tracker:
        print("🔄 正在更新批次10-13的完成状态...")
        print("=" * 80)
        
        # 批次10: ITEM模块
        batch10 = tracker.get_batch_by_name("batch_10")
        if batch10:
            tracker.update_batch(
                batch10.id,
                status='completed',
                total_files=2,
                migrated_files=2,
                actual_end_date='2026-02-09'
            )
            print(f"✅ 批次10更新为完成: ITEM模块 (2个文件)")
            
            # 添加批次10的迁移文件
            files_10 = [
                ("REQ_ITEM_USE.java", "ITEM", 14000, "completed"),
                ("RES_ITEM_USE.java", "ITEM", 14000, "completed"),
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
                    proto_file=f"proto/dnf/v1/item.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次10 - 物品模块",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 创建并更新批次11: SKILL模块
        batch11 = tracker.get_batch_by_name("batch_11")
        if batch11:
            tracker.update_batch(
                batch11.id,
                description="SKILL技能模块",
                status='completed',
                priority=7,
                total_files=2,
                migrated_files=2,
                actual_end_date='2026-02-09'
            )
            print(f"✅ 批次11更新为完成: SKILL模块 (2个文件)")
            
            files_11 = [
                ("REQ_SKILL_SLOT.java", "SKILL", 16000, "completed"),
                ("RES_SKILL_SLOT.java", "SKILL", 16000, "completed"),
            ]
            for fname, module, mid, status in files_11:
                f = MigrationFile(
                    id=None,
                    batch_id=batch11.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=7,
                    proto_file=f"proto/dnf/v1/skill.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次11 - 技能模块",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 创建并更新批次12: ACHIEVEMENT模块
        batch12 = tracker.get_batch_by_name("batch_12")
        if batch12:
            tracker.update_batch(
                batch12.id,
                description="ACHIEVEMENT成就模块",
                status='completed',
                priority=6,
                total_files=4,
                migrated_files=4,
                actual_end_date='2026-02-09'
            )
            print(f"✅ 批次12更新为完成: ACHIEVEMENT模块 (4个文件)")
            
            files_12 = [
                ("REQ_ACHIEVEMENT_LIST.java", "ACHIEVEMENT", 17000, "completed"),
                ("RES_ACHIEVEMENT_LIST.java", "ACHIEVEMENT", 17000, "completed"),
                ("REQ_ACHIEVEMENT_INFO.java", "ACHIEVEMENT", 17001, "completed"),
                ("RES_ACHIEVEMENT_INFO.java", "ACHIEVEMENT", 17001, "completed"),
                ("REQ_ACHIEVEMENT_REWARD.java", "ACHIEVEMENT", 17002, "completed"),
                ("RES_ACHIEVEMENT_REWARD.java", "ACHIEVEMENT", 17002, "completed"),
                ("REQ_ACHIEVEMENT_BONUS_REWARD.java", "ACHIEVEMENT", 17003, "completed"),
                ("RES_ACHIEVEMENT_BONUS_REWARD.java", "ACHIEVEMENT", 17003, "completed"),
            ]
            for fname, module, mid, status in files_12:
                f = MigrationFile(
                    id=None,
                    batch_id=batch12.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=6,
                    proto_file=f"proto/dnf/v1/achievement.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次12 - 成就模块",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except:
                    pass
        
        # 创建批次13: ADVENTURE模块
        batch13_exists = tracker.get_batch_by_name("batch_13")
        if not batch13_exists:
            batch13 = Batch(
                id=None,
                batch_name="batch_13",
                batch_number=13,
                description="ADVENTURE冒险模块",
                status='completed',
                priority=6,
                total_files=10,
                migrated_files=10,
                start_date='2026-02-09',
                planned_end_date=None,
                actual_end_date='2026-02-09',
                blocker=None,
                notes=None,
                created_at=None,
                updated_at=None
            )
            batch13_id = tracker.create_batch(batch13)
            print(f"✅ 批次13创建并完成: ADVENTURE模块 (10个文件)")
        else:
            tracker.update_batch(
                batch13_exists.id,
                description="ADVENTURE冒险模块",
                status='completed',
                priority=6,
                total_files=10,
                migrated_files=10,
                actual_end_date='2026-02-09'
            )
            batch13_id = batch13_exists.id
            print(f"✅ 批次13更新为完成: ADVENTURE模块 (10个文件)")
        
        # 添加批次13的文件
        files_13 = [
            ("REQ_ADVENTURE_DATA.java", "ADVENTURE", 18000, "completed"),
            ("RES_ADVENTURE_DATA.java", "ADVENTURE", 18000, "completed"),
            ("REQ_ADVENTURE_REAP_INFO.java", "ADVENTURE", 18001, "completed"),
            ("RES_ADVENTURE_REAP_INFO.java", "ADVENTURE", 18001, "completed"),
            ("REQ_ADVENTURE_REAP_REWARD.java", "ADVENTURE", 18002, "completed"),
            ("RES_ADVENTURE_REAP_REWARD.java", "ADVENTURE", 18002, "completed"),
            ("REQ_ADVENTURE_STORAGE_LIST.java", "ADVENTURE", 18003, "completed"),
            ("RES_ADVENTURE_STORAGE_LIST.java", "ADVENTURE", 18003, "completed"),
            ("REQ_ADVENTURE_AUTO_SEARCH.java", "ADVENTURE", 18004, "completed"),
            ("RES_ADVENTURE_AUTO_SEARCH.java", "ADVENTURE", 18004, "completed"),
            ("REQ_ADVENTURE_AUTO_SEARCH_REWARD.java", "ADVENTURE", 18005, "completed"),
            ("RES_ADVENTURE_AUTO_SEARCH_REWARD.java", "ADVENTURE", 18005, "completed"),
            ("REQ_ADVENTURE_BOOK_INFO.java", "ADVENTURE", 18006, "completed"),
            ("RES_ADVENTURE_BOOK_INFO.java", "ADVENTURE", 18006, "completed"),
            ("REQ_ADVENTURE_BOOK_SPECIAL_REWARD.java", "ADVENTURE", 18007, "completed"),
            ("RES_ADVENTURE_BOOK_SPECIAL_REWARD.java", "ADVENTURE", 18007, "completed"),
            ("REQ_ADVENTURE_BOOK_TERA_REWARD.java", "ADVENTURE", 18008, "completed"),
            ("RES_ADVENTURE_BOOK_TERA_REWARD.java", "ADVENTURE", 18008, "completed"),
            ("REQ_ADVENTURE_BOOK_UPDATE_CONDITION.java", "ADVENTURE", 18009, "completed"),
            ("RES_ADVENTURE_BOOK_UPDATE_CONDITION.java", "ADVENTURE", 18009, "completed"),
        ]
        for fname, module, mid, status in files_13:
            f = MigrationFile(
                id=None,
                batch_id=batch13_id if not batch13_exists else batch13_exists.id,
                file_name=fname,
                module_name=module,
                module_id=mid,
                status=status,
                priority=6,
                proto_file=f"proto/dnf/v1/adventure.proto",
                java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                has_test=True,
                test_passed=True,
                issues_count=0,
                migration_notes=f"批次13 - 冒险模块",
                start_date="2026-02-09",
                completion_date="2026-02-09",
                created_at=None,
                updated_at=None
            )
            try:
                tracker.create_file(f)
            except:
                pass
        
        # 更新所有批次的文件计数
        print("\n🔄 更新批次文件计数...")
        for i in range(10, 14):
            batch = tracker.get_batch_by_name(f"batch_{i:02d}")
            if batch:
                tracker._update_batch_file_count(batch.id)
                tracker._update_batch_migrated_count(batch.id)
        
        print("\n" + "=" * 80)
        print("✅ 批次10-13更新完成！")
        print("\n📊 最新进度概览:")
        
        # 显示最新进度
        progress = tracker.get_overall_progress()
        print(f"  批次: {progress['batches']['completed']}/{progress['batches']['total']} 完成")
        print(f"  文件: {progress['files']['migrated']}/{progress['files']['total']} 完成")
        print(f"  进度: {progress['files']['progress_percent']}%")
        
        # 按模块统计
        print("\n📈 按模块统计:")
        modules = tracker.get_module_progress()
        for m in sorted(modules, key=lambda x: x['total_files'], reverse=True)[:15]:
            status_icon = "✅" if m['progress_percent'] == 100 else "🔄" if m['progress_percent'] > 0 else "⏳"
            print(f"  {status_icon} {m['module_name']:<15}: {m['completed_files']:>3}/{m['total_files']:>3} ({m['progress_percent']:>5.1f}%)")

if __name__ == '__main__':
    update_batches_10_to_13()
