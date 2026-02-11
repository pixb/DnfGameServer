#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
修复jprotobuf_proto_mappings表中的Proto文件路径格式
"""

import sqlite3

def fix_proto_file_paths():
    """修复jprotobuf_proto_mappings表中的Proto文件路径格式"""
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()
    
    # 修复包含反引号的Proto文件路径
    cursor.execute('''
        UPDATE jprotobuf_proto_mappings 
        SET proto_file_path = REPLACE(REPLACE(proto_file_path, '`', ''), "'", '')
        WHERE proto_file_path LIKE '%`%' OR proto_file_path LIKE "%'%"
    ''')
    
    updated_count = cursor.rowcount
    conn.commit()
    conn.close()
    
    print(f"✅ 成功修复了 {updated_count} 条Proto文件路径")

def main():
    """主函数"""
    fix_proto_file_paths()

if __name__ == '__main__':
    main()
