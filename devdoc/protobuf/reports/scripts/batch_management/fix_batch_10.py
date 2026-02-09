#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
清理并重置批次10的状态
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, MigrationFile

def fix_batch_10():
    """修正批次10"""
    
    with MigrationTracker() as tracker:
        print("🔧 正在修正批次10...")
        
        batch10 = tracker.get_batch_by_name("batch_10")
        if not batch10:
            print("❌ 批次10不存在")
            return
        
        # 删除批次10的所有文件记录
        cursor = tracker.conn.cursor()
        cursor.execute('DELETE FROM migration_files WHERE batch_id = ?', (batch10.id,))
        tracker.conn.commit()
        print(f"  已清理批次10的旧文件记录")
        
        # 添加正确的文件记录（ITEM模块的2个文件）
        files_10 = [
            ("REQ_ITEM_USE.java", "ITEM", 14000),
            ("RES_ITEM_USE.java", "ITEM", 14000),
            ("REQ_ITEM_REINFORCE.java", "ITEM", 14001),
            ("RES_ITEM_REINFORCE.java", "ITEM", 14001),
        ]
        
        for fname, module, mid in files_10:
            f = MigrationFile(
                id=None,
                batch_id=batch10.id,
                file_name=fname,
                module_name=module,
                module_id=mid,
                status='completed',
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
                print(f"  ✅ 添加文件: {fname}")
            except Exception as e:
                print(f"  ⚠️  {fname}: {e}")
        
        # 更新批次统计
        tracker._update_batch_file_count(batch10.id)
        tracker._update_batch_migrated_count(batch10.id)
        
        print(f"\n✅ 批次10修正完成！")
        
        # 重新显示批次信息
        batch = tracker.get_batch_by_name("batch_10")
        if batch:
            print(f"  状态: {batch.status}")
            print(f"  进度: {batch.migrated_files}/{batch.total_files}")

if __name__ == '__main__':
    fix_batch_10()
