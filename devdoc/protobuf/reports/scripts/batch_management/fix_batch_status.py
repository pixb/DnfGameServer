#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
修正批次状态 - 根据实际文档更新
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def fix_batch_status():
    """修正批次状态"""
    
    with MigrationTracker() as tracker:
        print("🔧 正在修正批次状态...")
        print("=" * 80)
        
        # 1. 修正批次02 - PING消息已完成 (2个文件)
        batch02 = tracker.get_batch_by_name("batch_02")
        if batch02:
            # 删除旧文件记录
            cursor = tracker.conn.cursor()
            cursor.execute('DELETE FROM migration_files WHERE batch_id = ?', (batch02.id,))
            tracker.conn.commit()
            
            # 添加正确的文件记录
            files_02 = [
                ("REQ_PING.java", "SESSION", 10006, "completed"),
                ("RES_PING.java", "SESSION", 10006, "completed"),
            ]
            for fname, module, mid, status in files_02:
                f = MigrationFile(
                    id=None,
                    batch_id=batch02.id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status=status,
                    priority=9,
                    proto_file=f"proto/dnf/v1/session.proto",
                    java_file=f"src/main/java/com/dnfm/mina/protobuf/{fname}",
                    has_test=True,
                    test_passed=True,
                    issues_count=0,
                    migration_notes=f"批次02 - PING消息",
                    start_date="2026-02-09",
                    completion_date="2026-02-09",
                    created_at=None,
                    updated_at=None
                )
                try:
                    tracker.create_file(f)
                except Exception as e:
                    print(f"  警告: {e}")
            
            tracker.update_batch(
                batch02.id,
                description="会话管理模块(PING)",
                status='completed',
                total_files=2,
                migrated_files=2,
                actual_end_date='2026-02-09'
            )
            print(f"✅ 批次02已修正: PING模块 (2个文件)")
        
        # 2. 修正批次10 - 物品模块已完成 (2个文件)
        batch10 = tracker.get_batch_by_name("batch_10")
        if batch10:
            tracker.update_batch(
                batch10.id,
                description="物品相关消息(使用/强化)",
                status='completed',
                priority=8,
                total_files=2,
                migrated_files=2,
                actual_end_date='2026-02-09'
            )
            print(f"✅ 批次10已修正: ITEM模块 (2个文件)")
        
        # 3. 修正批次11 - 技能模块已完成 (2个文件)
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
            print(f"✅ 批次11已修正: SKILL模块 (2个文件)")
        
        # 4. 修正批次12 - 成就模块已完成 (8个文件)
        batch12 = tracker.get_batch_by_name("batch_12")
        if batch12:
            tracker.update_batch(
                batch12.id,
                description="ACHIEVEMENT成就模块",
                status='completed',
                priority=6,
                total_files=8,
                migrated_files=8,
                actual_end_date='2026-02-09'
            )
            print(f"✅ 批次12已修正: ACHIEVEMENT模块 (8个文件)")
        
        # 5. 添加批次13 - 冒险模块已完成 (20个文件)
        batch13_exists = tracker.get_batch_by_name("batch_13")
        if not batch13_exists:
            batch13 = Batch(
                id=None,
                batch_name="batch_13",
                batch_number=13,
                description="ADVENTURE冒险模块",
                status='completed',
                priority=6,
                total_files=20,
                migrated_files=20,
                start_date='2026-02-09',
                planned_end_date=None,
                actual_end_date='2026-02-09',
                blocker=None,
                notes=None,
                created_at=None,
                updated_at=None
            )
            batch13_id = tracker.create_batch(batch13)
            
            # 添加批次13的文件
            files_13 = [
                ("REQ_ADVENTURE_DATA.java", "ADVENTURE", 18000),
                ("RES_ADVENTURE_DATA.java", "ADVENTURE", 18000),
                ("REQ_ADVENTURE_REAP_INFO.java", "ADVENTURE", 18001),
                ("RES_ADVENTURE_REAP_INFO.java", "ADVENTURE", 18001),
                ("REQ_ADVENTURE_REAP_REWARD.java", "ADVENTURE", 18002),
                ("RES_ADVENTURE_REAP_REWARD.java", "ADVENTURE", 18002),
                ("REQ_ADVENTURE_STORAGE_LIST.java", "ADVENTURE", 18003),
                ("RES_ADVENTURE_STORAGE_LIST.java", "ADVENTURE", 18003),
                ("REQ_ADVENTURE_AUTO_SEARCH.java", "ADVENTURE", 18004),
                ("RES_ADVENTURE_AUTO_SEARCH.java", "ADVENTURE", 18004),
                ("REQ_ADVENTURE_AUTO_SEARCH_REWARD.java", "ADVENTURE", 18005),
                ("RES_ADVENTURE_AUTO_SEARCH_REWARD.java", "ADVENTURE", 18005),
                ("REQ_ADVENTURE_BOOK_INFO.java", "ADVENTURE", 18006),
                ("RES_ADVENTURE_BOOK_INFO.java", "ADVENTURE", 18006),
                ("REQ_ADVENTURE_BOOK_SPECIAL_REWARD.java", "ADVENTURE", 18007),
                ("RES_ADVENTURE_BOOK_SPECIAL_REWARD.java", "ADVENTURE", 18007),
                ("REQ_ADVENTURE_BOOK_TERA_REWARD.java", "ADVENTURE", 18008),
                ("RES_ADVENTURE_BOOK_TERA_REWARD.java", "ADVENTURE", 18008),
                ("REQ_ADVENTURE_BOOK_UPDATE_CONDITION.java", "ADVENTURE", 18009),
                ("RES_ADVENTURE_BOOK_UPDATE_CONDITION.java", "ADVENTURE", 18009),
            ]
            for fname, module, mid in files_13:
                f = MigrationFile(
                    id=None,
                    batch_id=batch13_id,
                    file_name=fname,
                    module_name=module,
                    module_id=mid,
                    status='completed',
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
            
            print(f"✅ 批次13已创建: ADVENTURE模块 (20个文件)")
        else:
            print(f"✅ 批次13已存在")
        
        # 更新所有批次的文件计数
        print("\n🔄 更新批次文件计数...")
        for i in range(1, 14):
            batch = tracker.get_batch_by_name(f"batch_{i:02d}")
            if batch:
                tracker._update_batch_file_count(batch.id)
                tracker._update_batch_migrated_count(batch.id)
        
        print("\n" + "=" * 80)
        print("✅ 批次状态修正完成！")
        print("\n📊 最新进度概览:")
        
        # 显示最新进度
        progress = tracker.get_overall_progress()
        print(f"  批次: {progress['batches']['completed']}/{progress['batches']['total']} 完成")
        print(f"  文件: {progress['files']['migrated']}/{progress['files']['total']} 完成")
        print(f"  进度: {progress['files']['progress_percent']}%")

if __name__ == '__main__':
    fix_batch_status()
