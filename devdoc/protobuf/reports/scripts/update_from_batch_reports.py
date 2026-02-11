#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
根据批次迁移记录更新jprotobuf_proto_mappings表
"""

import sqlite3
import re
import os
from pathlib import Path
from typing import List, Dict, Optional

class BatchMappingUpdater:
    """根据批次迁移记录更新jprotobuf_proto_mappings表"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_reports_dir = self.project_root / 'devdoc' / 'protobuf' / 'reports' / 'archive' / 'batch_mapping_reports'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def scan_batch_reports(self) -> List[Path]:
        """扫描批次迁移报告文件"""
        reports = []
        
        for report_file in self.batch_reports_dir.glob('batch_*_mapping_report.md'):
            reports.append(report_file)
        
        # 按批次号排序
        def get_batch_number(report_path):
            match = re.search(r'batch_(\d+)_', report_path.name)
            if match:
                return int(match.group(1))
            return 0
        
        reports.sort(key=get_batch_number)
        return reports
    
    def parse_batch_report(self, report_path: Path) -> List[Dict]:
        """解析批次迁移报告文件"""
        mappings = []
        
        with open(report_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # 提取批次号
        batch_match = re.search(r'batch_(\d+)_', report_path.name)
        batch_number = int(batch_match.group(1)) if batch_match else 0
        
        # 提取消息映射清单
        lines = content.split('\n')
        
        # 找到表格开始位置
        table_start = -1
        for i, line in enumerate(lines):
            if '| ModuleID | CMD | 旧消息 | 类型 | 新消息 | Proto文件 | 实现状态 | 生成文件 |' in line:
                table_start = i + 2  # 跳过表头和分隔线
                break
        
        if table_start == -1:
            return mappings
        
        # 解析表格行
        for i in range(table_start, len(lines)):
            line = lines[i]
            if line.strip() == '---' or not line.strip():
                break
            
            # 清理行并分割
            line = line.strip().strip('|')
            parts = [part.strip() for part in line.split('|')]
            
            if len(parts) < 8:
                continue
            
            module_id_str = parts[0]
            cmd_str = parts[1]
            old_message_str = parts[2]
            message_type_str = parts[3]
            new_message_str = parts[4]
            proto_file_str = parts[5]
            impl_status_str = parts[6]
            generated_files_str = parts[7]
            
            # 提取实际值
            module_id = int(module_id_str) if module_id_str.isdigit() else 0
            cmd = int(cmd_str) if cmd_str.isdigit() else 0
            
            # 提取 ` 中的值
            old_message_match = re.search(r'`([^`]+)`', old_message_str)
            old_message = old_message_match.group(1) if old_message_match else old_message_str
            
            new_message_match = re.search(r'`([^`]+)`', new_message_str)
            new_message = new_message_match.group(1) if new_message_match else new_message_str
            
            message_type = message_type_str
            proto_file = proto_file_str
            impl_status = impl_status_str
            generated_files = generated_files_str
            
            # 构建完整的proto文件路径
            proto_file_path = f"proto/dnf/v1/{proto_file}" if proto_file else None
            
            mapping = {
                'batch_number': batch_number,
                'module_id': module_id,
                'cmd': cmd,
                'jprotobuf_message_name': old_message,
                'message_type': message_type,
                'proto_message_name': new_message,
                'proto_file_path': proto_file_path,
                'implementation_status': impl_status,
                'generated_files': generated_files
            }
            
            mappings.append(mapping)
        
        return mappings
    
    def update_mappings(self, all_mappings: List[Dict]):
        """更新jprotobuf_proto_mappings表"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取批次信息
        cursor.execute('SELECT id, batch_number FROM batches')
        batches = {row[1]: row[0] for row in cursor.fetchall()}
        
        updated_count = 0
        
        for mapping in all_mappings:
            jprotobuf_name = mapping['jprotobuf_message_name']
            proto_name = mapping['proto_message_name']
            proto_file = mapping['proto_file_path']
            module_id = mapping['module_id']
            message_type = mapping['message_type']
            batch_number = mapping['batch_number']
            
            # 获取批次ID
            batch_id = batches.get(batch_number)
            
            # 构建JProtobuf文件路径
            jprotobuf_file = f"src/main/java/com/dnfm/mina/protobuf/{jprotobuf_name}.java"
            
            # 更新映射
            cursor.execute('''
                UPDATE jprotobuf_proto_mappings 
                SET proto_message_name = ?, proto_file_path = ?, 
                    is_migrated = 1, module_id = ?, message_type = ?, batch_id = ?
                WHERE jprotobuf_message_name = ?
            ''', (proto_name, proto_file, module_id, message_type, batch_id, jprotobuf_name))
            
            if cursor.rowcount > 0:
                updated_count += 1
        
        conn.commit()
        conn.close()
        
        print(f"✅ 成功更新了 {updated_count} 条映射记录")
    
    def run(self):
        """执行更新操作"""
        print("🔍 正在扫描批次迁移报告文件...")
        reports = self.scan_batch_reports()
        print(f"  找到 {len(reports)} 个批次迁移报告文件")
        
        all_mappings = []
        
        for report in reports:
            print(f"🔍 正在解析 {report.name}...")
            mappings = self.parse_batch_report(report)
            all_mappings.extend(mappings)
            print(f"  提取了 {len(mappings)} 条映射记录")
        
        print(f"\n📊 总计提取了 {len(all_mappings)} 条映射记录")
        
        if all_mappings:
            print("\n💾 正在更新jprotobuf_proto_mappings表...")
            self.update_mappings(all_mappings)
        else:
            print("\n❌ 未提取到任何映射记录")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db'
    
    updater = BatchMappingUpdater(db_path)
    updater.run()

if __name__ == '__main__':
    main()
