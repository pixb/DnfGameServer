#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
删除批次93及以后的记录
"""

import sqlite3
from pathlib import Path

# 数据库路径
db_path = Path(__file__).parent.parent / 'data' / 'migration_progress.db'

def delete_batches_from_93():
    """删除批次93及以后的记录"""
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    try:
        # 查询要删除的批次
        cursor.execute('SELECT id, batch_name, batch_number FROM batches WHERE batch_number >= 93')
        batches_to_delete = cursor.fetchall()

        if not batches_to_delete:
            print("没有找到批次93及以后的记录")
            return

        print(f"找到 {len(batches_to_delete)} 个批次需要删除:")
        for batch in batches_to_delete:
            print(f"  - 批次 {batch[2]}: {batch[1]} (ID: {batch[0]})")

        # 获取批次ID列表
        batch_ids = [batch[0] for batch in batches_to_delete]

        # 删除相关记录（按依赖顺序）
        # 1. 删除迁移日志
        cursor.execute(f'DELETE FROM migration_logs WHERE batch_id IN ({",".join(["?"] * len(batch_ids))})', batch_ids)
        log_count = cursor.rowcount
        print(f"删除了 {log_count} 条迁移日志记录")

        # 2. 删除问题（先获取文件ID）
        cursor.execute(f'SELECT id FROM migration_files WHERE batch_id IN ({",".join(["?"] * len(batch_ids))})', batch_ids)
        file_ids = [row[0] for row in cursor.fetchall()]
        if file_ids:
            cursor.execute(f'DELETE FROM issues WHERE file_id IN ({",".join(["?"] * len(file_ids))})', file_ids)
            issue_count = cursor.rowcount
            print(f"删除了 {issue_count} 条问题记录")

        # 3. 删除迁移文件
        cursor.execute(f'DELETE FROM migration_files WHERE batch_id IN ({",".join(["?"] * len(batch_ids))})', batch_ids)
        file_count = cursor.rowcount
        print(f"删除了 {file_count} 条迁移文件记录")

        # 4. 删除批次
        cursor.execute(f'DELETE FROM batches WHERE id IN ({",".join(["?"] * len(batch_ids))})', batch_ids)
        batch_count = cursor.rowcount
        print(f"删除了 {batch_count} 个批次记录")

        # 提交更改
        conn.commit()

        print("\n✅ 批次93及以后的记录已成功删除")

        # 验证删除结果
        cursor.execute('SELECT COUNT(*) FROM batches WHERE batch_number >= 93')
        remaining = cursor.fetchone()[0]
        if remaining == 0:
            print("✅ 验证通过：没有剩余的批次93及以后的记录")
        else:
            print(f"⚠️ 警告：仍有 {remaining} 个批次93及以后的记录")

    except Exception as e:
        conn.rollback()
        print(f"❌ 删除失败: {e}")
    finally:
        conn.close()

if __name__ == '__main__':
    delete_batches_from_93()
