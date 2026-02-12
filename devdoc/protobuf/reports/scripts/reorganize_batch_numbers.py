#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
重新组织批次编号，使其连续
"""

import sqlite3
from pathlib import Path
from typing import List, Dict

class BatchReorganizer:
    """批次重新组织器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.conn = sqlite3.connect(db_path)
        self.conn.row_factory = sqlite3.Row
    
    def __del__(self):
        if hasattr(self, 'conn') and self.conn:
            self.conn.close()
    
    def get_all_batches(self) -> List[Dict]:
        """获取所有批次"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT batch_number, batch_name, description, status, created_at
            FROM migration_batches
            ORDER BY batch_number
        ''')
        
        return [dict(row) for row in cursor.fetchall()]
    
    def get_batch_messages(self, batch_number: int) -> List[Dict]:
        """获取批次的消息"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT 
                mr.id as record_id,
                mr.jprotobuf_message_id,
                mr.proto_message_id,
                jm.message_name,
                pm.message_name as proto_name
            FROM migration_records mr
            JOIN jprotobuf_messages jm ON mr.jprotobuf_message_id = jm.id
            JOIN proto_messages pm ON mr.proto_message_id = pm.id
            WHERE mr.batch_id = ?
            ORDER BY jm.message_name
        ''', (batch_number,))
        
        return [dict(row) for row in cursor.fetchall()]
    
    def reorganize_batch_numbers(self):
        """重新组织批次编号"""
        batches = self.get_all_batches()
        
        print(f"📋 找到 {len(batches)} 个批次")
        print()
        
        # 创建新的批次编号映射
        new_batch_mapping = {}
        batch_info_mapping = {}
        current_batch_num = 1
        
        for batch in batches:
            old_batch_num = batch['batch_number']
            new_batch_num = current_batch_num
            
            new_batch_mapping[old_batch_num] = new_batch_num
            batch_info_mapping[old_batch_num] = batch
            
            print(f"批次 {old_batch_num} → {new_batch_num}: {batch['batch_name']}")
            
            current_batch_num += 1
        
        print()
        print("🔄 开始更新数据库...")
        
        # 更新批次编号
        cursor = self.conn.cursor()
        for old_batch_num, new_batch_num in new_batch_mapping.items():
            cursor.execute('''
                UPDATE migration_batches
                SET batch_number = ?
                WHERE batch_number = ?
            ''', (new_batch_num, old_batch_num))
        
        # 更新迁移记录中的批次ID
        for old_batch_num, new_batch_num in new_batch_mapping.items():
            cursor.execute('''
                UPDATE migration_records
                SET batch_id = ?
                WHERE batch_id = ?
            ''', (new_batch_num, old_batch_num))
        
        self.conn.commit()
        
        print(f"✅ 数据库更新完成")
        print()
        
        # 生成批次编号映射文档
        self.generate_batch_mapping_document(new_batch_mapping, batch_info_mapping)
        
        return new_batch_mapping, batch_info_mapping
    
    def generate_batch_mapping_document(self, new_batch_mapping: Dict, batch_info_mapping: Dict):
        """生成批次编号映射文档"""
        content = '''# 批次编号重新组织映射表

## 📋 映射说明

由于批量迁移脚本从批次9开始编号，导致批次编号不连续。本表显示了批次编号的重新组织映射。

## 🔢 旧批次号 → 新批次号

| 旧批次号 | 新批次号 | 批次名称 |
|-----------|-----------|----------|
'''
        
        for old_batch_num in sorted(new_batch_mapping.keys()):
            new_batch_num = new_batch_mapping[old_batch_num]
            batch_name = batch_info_mapping[old_batch_num]['batch_name']
            content += f"| {old_batch_num} | {new_batch_num} | {batch_name} |\n"
        
        content += '''
## 🔢 新批次号 → 旧批次号

| 新批次号 | 旧批次号 | 批次名称 |
|-----------|-----------|----------|
'''
        
        # 创建反向映射
        reverse_mapping = {v: k for k, v in new_batch_mapping.items()}
        for new_batch_num in sorted(reverse_mapping.keys()):
            old_batch_num = reverse_mapping[new_batch_num]
            batch_name = batch_info_mapping[old_batch_num]['batch_name']
            content += f"| {new_batch_num} | {old_batch_num} | {batch_name} |\n"
        
        content += '''
## 📊 统计信息

- **总批次数**: 39
- **批次编号范围**: 1-39 (连续)
- **重新组织时间**: 2026-02-12 07:40:00

## ✅ 完成

批次编号已重新组织为连续的1-39。

---

**文档生成时间**: 2026-02-12 07:40:00
'''
        
        # 写入文档
        output_dir = Path('/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/03_迁移')
        output_file = output_dir / 'BATCH_NUMBER_MAPPING.md'
        output_file.write_text(content, encoding='utf-8')
        
        print(f"✅ 生成批次编号映射文档: {output_file}")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    
    reorganizer = BatchReorganizer(db_path)
    reorganizer.reorganize_batch_numbers()

if __name__ == '__main__':
    main()
