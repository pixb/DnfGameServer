#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移报告和统计工具
生成JProtobuf到标准Protobuf迁移的综合报告
"""

import sqlite3
from pathlib import Path
from typing import Dict, List
from datetime import datetime

class MigrationReportGenerator:
    """迁移报告生成器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.reports_dir = self.project_root / 'devdoc' / 'protobuf' / 'reports' / 'docs'
        self.reports_dir.mkdir(parents=True, exist_ok=True)
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def generate_overall_report(self) -> str:
        """生成整体迁移报告"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 基本统计
        cursor.execute('SELECT COUNT(*) FROM jprotobuf_messages')
        total_jprotobuf = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(*) FROM proto_messages')
        total_proto = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(DISTINCT jprotobuf_message_id) FROM message_mappings')
        mapped_count = cursor.fetchone()[0]
        
        cursor.execute('''
            SELECT COUNT(DISTINCT jprotobuf_message_id) 
            FROM migration_records 
            WHERE migration_status = 'migrated'
        ''')
        migrated_count = cursor.fetchone()[0]
        
        cursor.execute('SELECT COUNT(*) FROM migration_batches')
        total_batches = cursor.fetchone()[0]
        
        cursor.execute('''
            SELECT COUNT(*) FROM migration_batches 
            WHERE status = 'completed'
        ''')
        completed_batches = cursor.fetchone()[0]
        
        # 按类型统计
        cursor.execute('''
            SELECT message_type, COUNT(*) 
            FROM jprotobuf_messages 
            GROUP BY message_type
            ORDER BY COUNT(*) DESC
        ''')
        
        type_stats = []
        for row in cursor.fetchall():
            msg_type = row[0]
            total = row[1]
            
            cursor.execute('''
                SELECT COUNT(DISTINCT jm.id)
                FROM jprotobuf_messages jm
                LEFT JOIN message_mappings mm ON jm.id = mm.jprotobuf_message_id
                WHERE jm.message_type = ? AND mm.id IS NOT NULL
            ''', (msg_type,))
            
            mapped = cursor.fetchone()[0]
            
            cursor.execute('''
                SELECT COUNT(DISTINCT jm.id)
                FROM jprotobuf_messages jm
                LEFT JOIN migration_records mr ON jm.id = mr.jprotobuf_message_id
                WHERE jm.message_type = ? AND mr.migration_status = 'migrated'
            ''', (msg_type,))
            
            migrated = cursor.fetchone()[0]
            
            type_stats.append({
                'type': msg_type,
                'total': total,
                'mapped': mapped,
                'migrated': migrated,
                'mapping_rate': round(mapped / total * 100, 2) if total > 0 else 0,
                'migration_rate': round(migrated / total * 100, 2) if total > 0 else 0
            })
        
        # 批次统计
        cursor.execute('''
            SELECT batch_number, batch_name, status, jprotobuf_count, proto_count
            FROM migration_batches
            ORDER BY batch_number
        ''')
        
        batch_stats = []
        for row in cursor.fetchall():
            batch_stats.append({
                'batch_number': row[0],
                'batch_name': row[1],
                'status': row[2],
                'jprotobuf_count': row[3],
                'proto_count': row[4]
            })
        
        conn.close()
        
        # 生成报告
        report = f"""# JProtobuf到标准Protobuf迁移报告

## 📊 整体概况

| 指标 | 数值 |
| :--- | :--- |
| **JProtobuf消息总数** | {total_jprotobuf} |
| **标准Protobuf消息总数** | {total_proto} |
| **已映射消息数** | {mapped_count} ({round(mapped_count / total_jprotobuf * 100, 2) if total_jprotobuf > 0 else 0}%) |
| **已迁移消息数** | {migrated_count} ({round(migrated_count / total_jprotobuf * 100, 2) if total_jprotobuf > 0 else 0}%) |
| **批次总数** | {total_batches} |
| **已完成批次** | {completed_batches} ({round(completed_batches / total_batches * 100, 2) if total_batches > 0 else 0}%) |

## 📈 按类型统计

| 类型 | 总数 | 已映射 | 已迁移 | 映射率 | 迁移率 |
| :--- | :--- | :--- | :--- | :--- | :--- |
"""
        
        for stat in type_stats:
            report += f"| {stat['type']} | {stat['total']} | {stat['mapped']} | {stat['migrated']} | {stat['mapping_rate']}% | {stat['migration_rate']}% |\n"
        
        report += "\n## 📦 批次统计\n\n"
        
        for batch in batch_stats:
            status_icon = "✅" if batch['status'] == 'completed' else "⏳" if batch['status'] == 'in_progress' else "❌"
            report += f"### 批次 {batch['batch_number']}: {batch['batch_name']} {status_icon}\n\n"
            report += f"- 状态: {batch['status']}\n"
            report += f"- JProtobuf消息数: {batch['jprotobuf_count']}\n"
            report += f"- 标准Protobuf消息数: {batch['proto_count']}\n\n"
        
        report += f"\n---\n\n*报告生成时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}*\n"
        
        return report
    
    def generate_mapping_report(self) -> str:
        """生成映射关系报告"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT 
                jm.message_name as jprotobuf_name,
                jm.message_type as jprotobuf_type,
                pm.message_name as proto_name,
                pm.file_path as proto_path,
                mm.mapping_type,
                mm.mapping_confidence,
                mm.is_verified
            FROM message_mappings mm
            LEFT JOIN jprotobuf_messages jm ON mm.jprotobuf_message_id = jm.id
            LEFT JOIN proto_messages pm ON mm.proto_message_id = pm.id
            ORDER BY jm.message_name
        ''')
        
        mappings = []
        for row in cursor.fetchall():
            mappings.append({
                'jprotobuf_name': row[0],
                'jprotobuf_type': row[1],
                'proto_name': row[2],
                'proto_path': row[3],
                'mapping_type': row[4],
                'mapping_confidence': row[5],
                'is_verified': row[6]
            })
        
        conn.close()
        
        report = """# 消息映射关系报告

## 📋 映射列表

| JProtobuf消息 | 类型 | 标准Protobuf消息 | 文件 | 映射类型 | 置信度 | 已验证 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
"""
        
        for mapping in mappings:
            proto_file = Path(mapping['proto_path']).name if mapping['proto_path'] else 'N/A'
            verified = "✅" if mapping['is_verified'] else "❌"
            report += f"| {mapping['jprotobuf_name']} | {mapping['jprotobuf_type']} | {mapping['proto_name']} | {proto_file} | {mapping['mapping_type']} | {mapping['mapping_confidence']} | {verified} |\n"
        
        report += f"\n---\n\n*报告生成时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}*\n"
        
        return report
    
    def generate_batch_report(self, batch_number: int) -> str:
        """生成批次报告"""
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('''
            SELECT batch_number, batch_name, description, status,
                   start_time, end_time, jprotobuf_count, proto_count
            FROM migration_batches
            WHERE batch_number = ?
        ''', (batch_number,))
        
        result = cursor.fetchone()
        if not result:
            return f"# 批次 {batch_number} 报告\n\n❌ 未找到该批次\n"
        
        batch_id = result[0]
        
        cursor.execute('''
            SELECT 
                jm.message_name as jprotobuf_name,
                jm.message_type as jprotobuf_type,
                pm.message_name as proto_name,
                mr.migration_status
            FROM migration_records mr
            LEFT JOIN jprotobuf_messages jm ON mr.jprotobuf_message_id = jm.id
            LEFT JOIN proto_messages pm ON mr.proto_message_id = pm.id
            WHERE mr.batch_id = ?
            ORDER BY jm.message_name
        ''', (batch_id,))
        
        messages = []
        for row in cursor.fetchall():
            messages.append({
                'jprotobuf_name': row[0],
                'jprotobuf_type': row[1],
                'proto_name': row[2],
                'migration_status': row[3]
            })
        
        conn.close()
        
        status_icon = "✅" if result[3] == 'completed' else "⏳" if result[3] == 'in_progress' else "❌"
        
        report = f"""# 批次 {batch_number} 迁移报告

## 📋 批次信息

| 属性 | 值 |
| :--- | :--- |
| **批次编号** | {result[0]} |
| **批次名称** | {result[1]} |
| **描述** | {result[2] or 'N/A'} |
| **状态** | {result[3]} {status_icon} |
| **开始时间** | {result[4]} |
| **结束时间** | {result[5]} |
| **JProtobuf消息数** | {result[6]} |
| **标准Protobuf消息数** | {result[7]} |

## 📦 消息列表

| JProtobuf消息 | 类型 | 标准Protobuf消息 | 状态 |
| :--- | :--- | :--- | :--- |
"""
        
        for msg in messages:
            status_icon = "✅" if msg['migration_status'] == 'migrated' else "⏳"
            report += f"| {msg['jprotobuf_name']} | {msg['jprotobuf_type']} | {msg['proto_name'] or 'N/A'} | {msg['migration_status']} {status_icon} |\n"
        
        report += f"\n---\n\n*报告生成时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}*\n"
        
        return report
    
    def save_report(self, report: str, filename: str):
        """保存报告到文件"""
        report_path = self.reports_dir / filename
        with open(report_path, 'w', encoding='utf-8') as f:
            f.write(report)
        print(f"✅ 报告已保存: {report_path}")
    
    def generate_all_reports(self):
        """生成所有报告"""
        print("📊 生成迁移报告...")
        print()
        
        # 整体报告
        overall_report = self.generate_overall_report()
        self.save_report(overall_report, 'MIGRATION_OVERALL_REPORT.md')
        
        # 映射关系报告
        mapping_report = self.generate_mapping_report()
        self.save_report(mapping_report, 'MIGRATION_MAPPING_REPORT.md')
        
        # 批次报告
        conn = self._connect()
        cursor = conn.cursor()
        cursor.execute('SELECT batch_number FROM migration_batches ORDER BY batch_number')
        batch_numbers = [row[0] for row in cursor.fetchall()]
        conn.close()
        
        for batch_number in batch_numbers:
            batch_report = self.generate_batch_report(batch_number)
            self.save_report(batch_report, f'BATCH_{batch_number}_REPORT.md')
        
        print()
        print("✅ 所有报告生成完成！")
        print()

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='迁移报告和统计工具')
    parser.add_argument('--generate-all', action='store_true', help='生成所有报告')
    parser.add_argument('--overall', action='store_true', help='生成整体报告')
    parser.add_argument('--mapping', action='store_true', help='生成映射关系报告')
    parser.add_argument('--batch', type=int, help='生成批次报告')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    generator = MigrationReportGenerator(db_path)
    
    if args.generate_all:
        generator.generate_all_reports()
    elif args.overall:
        report = generator.generate_overall_report()
        print(report)
    elif args.mapping:
        report = generator.generate_mapping_report()
        print(report)
    elif args.batch:
        report = generator.generate_batch_report(args.batch)
        print(report)
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
