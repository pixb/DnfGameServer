#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
更新批次状态脚本
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker

def update_batch_status(batch_name, status, migrated_files=None):
    """更新批次状态"""
    with MigrationTracker() as tracker:
        success = tracker.update_batch_status(batch_name, status, migrated_files)
        if success:
            print(f"✅ 批次 {batch_name} 状态已更新为 {status}")
        else:
            print(f"❌ 无法更新批次 {batch_name} 状态")

def main():
    """主函数"""
    # 更新批次44
    update_batch_status("batch_44", "completed", 37)
    
    # 更新批次45
    update_batch_status("batch_45", "completed", 44)

if __name__ == "__main__":
    main()
