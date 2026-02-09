#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
生成消息文件映射报告
"""

import sqlite3
import sys
from pathlib import Path
from datetime import datetime
from typing import List, Dict

class MappingReportGenerator:
    """映射报告生成器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.reports_dir = self.project_root / 'devdoc' / 'protobuf' / 'reports'
        self.reports_dir.mkdir(parents=True, exist_ok=True)
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def generate_batch_report(self, batch_id: int) -> str:
        """生成特定批次的映射报告"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取批次信息
        cursor.execute('''
            SELECT batch_name, batch_number, description, status
            FROM batches WHERE id = ?
        ''', (batch_id,))
        batch_info = cursor.fetchone()
        
        if not batch_info:
            conn.close()
            return f"❌ 找不到批次ID: {batch_id}"
        
        batch_name, batch_number, description, status = batch_info
        
        # 获取该批次的所有消息映射
        cursor.execute('''
            SELECT module_id, cmd_id, old_message_name, old_message_type,
                   old_java_file, new_message_name, new_proto_file, 
                   new_java_file, new_go_file, implementation_status
            FROM message_file_mappings
            WHERE batch_id = ?
            ORDER BY module_id, cmd_id, old_message_type
        ''', (batch_id,))
        
        mappings = cursor.fetchall()
        conn.close()
        
        if not mappings:
            return f"⚠️  批次 {batch_name} 没有消息映射数据"
        
        # 生成报告
        report = f"""# 批次{batch_number:02d}消息文件映射报告

**批次**: {batch_name}  
**模块**: {description}  
**状态**: {status}  
**生成时间**: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}

---

## 消息映射清单

| ModuleID | CMD | 旧消息 | 类型 | 新消息 | Proto文件 | 实现状态 | 生成文件 |
|:--------:|:---:|:-------|:----:|:-------|:----------|:--------:|:---------|
"""
        
        # 统计
        complete_count = 0
        simplified_count = 0
        missing_count = 0
        
        for mapping in mappings:
            (module_id, cmd_id, old_name, old_type, old_file,
             new_name, proto_file, new_java, new_go, impl_status) = mapping
            
            # 状态图标
            if impl_status == 'complete':
                status_icon = '✅'
                complete_count += 1
            elif impl_status == 'simplified':
                status_icon = '⚠️'
                simplified_count += 1
            else:
                status_icon = '❌'
                missing_count += 1
            
            # 生成文件列表
            gen_files = []
            if new_java:
                gen_files.append(new_java.split('/')[-1])
            if new_go:
                gen_files.append(new_go.split('/')[-1])
            gen_files_str = ', '.join(gen_files) if gen_files else '-'
            
            report += f"| {module_id} | {cmd_id} | `{old_name}` | {old_type} | `{new_name}` | `{proto_file.split('/')[-1]}` | {status_icon} {impl_status} | {gen_files_str} |\n"
        
        # 添加统计信息
        report += f"""

---

## 文件路径汇总

### 原Java文件位置
- **路径**: `src/main/java/com/dnfm/mina/protobuf/`
- **文件数**: {len([m for m in mappings if m[2]])} 个

### Proto文件位置
- **路径**: `proto/dnf/v1/`
- **文件列表**:
"""
        
        # 提取唯一的proto文件
        proto_files = set()
        for mapping in mappings:
            proto_file = mapping[6]
            if proto_file:
                proto_files.add(proto_file.split('/')[-1])
        
        for proto in sorted(proto_files):
            report += f"  - `{proto}`\n"
        
        report += f"""
### 生成的Java文件位置
- **路径**: `proto/gen/java/com/dnfm/mina/protobuf/generated/`
- **文件数**: {len([m for m in mappings if m[7]])} 个

### 生成的Go文件位置
- **路径**: `dnf-go-client/gen/dnf/v1/`
- **文件数**: {len(set([m[8].split('/')[-1] for m in mappings if m[8]]))} 个

---

## 实现状态统计

| 状态 | 消息数 | 百分比 |
|:-----|:------:|:------:|
| ✅ 完整实现 | {complete_count} | {complete_count/len(mappings)*100:.1f}% |
| ⚠️ 简化实现 | {simplified_count} | {simplified_count/len(mappings)*100:.1f}% |
| ❌ 缺失实现 | {missing_count} | {missing_count/len(mappings)*100:.1f}% |
| **总计** | **{len(mappings)}** | **100%** |

---

*报告由消息映射追踪系统自动生成*
"""
        
        return report
    
    def generate_all_batches_report(self) -> str:
        """生成所有批次的汇总报告"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取所有批次
        cursor.execute('''
            SELECT id, batch_name, batch_number, description, status
            FROM batches
            ORDER BY batch_number
        ''')
        batches = cursor.fetchall()
        
        report = f"""# 全部批次消息文件映射汇总报告

**生成时间**: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}  
**批次总数**: {len(batches)}

---

## 批次概览

| 批次 | 模块 | 描述 | 消息数 | 完整 | 简化 | 缺失 | 状态 |
|:-----|:-----|:-----|:------:|:----:|:----:|:----:|:----:|
"""
        
        total_mappings = 0
        total_complete = 0
        total_simplified = 0
        total_missing = 0
        
        for batch in batches:
            batch_id, batch_name, batch_number, description, status = batch
            
            # 统计该批次的映射
            cursor.execute('''
                SELECT implementation_status, COUNT(*)
                FROM message_file_mappings
                WHERE batch_id = ?
                GROUP BY implementation_status
            ''', (batch_id,))
            
            stats = {row[0]: row[1] for row in cursor.fetchall()}
            complete = stats.get('complete', 0)
            simplified = stats.get('simplified', 0)
            missing = stats.get('missing', 0)
            total = complete + simplified + missing
            
            total_mappings += total
            total_complete += complete
            total_simplified += simplified
            total_missing += missing
            
            status_icon = '✅' if status == 'completed' else '🔄' if status == 'in_progress' else '⏳'
            
            report += f"| {batch_name} | {batch_number} | {description[:30]}... | {total} | {complete} | {simplified} | {missing} | {status_icon} |\n"
        
        report += f"""

---

## 总体统计

| 指标 | 数值 | 百分比 |
|:-----|:----:|:------:|
| **总消息数** | {total_mappings} | 100% |
| **完整实现** | {total_complete} | {total_complete/total_mappings*100:.1f}% |
| **简化实现** | {total_simplified} | {total_simplified/total_mappings*100:.1f}% |
| **缺失实现** | {total_missing} | {total_missing/total_mappings*100:.1f}% |

---

## 各批次详细报告

"""
        
        # 为每个批次添加链接
        for batch in batches:
            batch_id, batch_name, batch_number, description, status = batch
            report += f"- [{batch_name} - {description}](batch_{batch_number:02d}_mapping_report.md)\n"
        
        report += f"""

---

*报告由消息映射追踪系统自动生成*
"""
        
        conn.close()
        return report
    
    def save_reports(self):
        """保存所有报告"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取所有批次
        cursor.execute('SELECT id, batch_number FROM batches ORDER BY batch_number')
        batches = cursor.fetchall()
        conn.close()
        
        saved_count = 0
        
        # 为每个批次生成报告
        for batch_id, batch_number in batches:
            report = self.generate_batch_report(batch_id)
            filename = f"batch_{batch_number:02d}_mapping_report.md"
            filepath = self.reports_dir / filename
            
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(report)
            
            print(f"  ✅ 已生成: {filename}")
            saved_count += 1
        
        # 生成汇总报告
        summary_report = self.generate_all_batches_report()
        summary_path = self.reports_dir / "all_batches_mapping_summary.md"
        
        with open(summary_path, 'w', encoding='utf-8') as f:
            f.write(summary_report)
        
        print(f"  ✅ 已生成: all_batches_mapping_summary.md")
        saved_count += 1
        
        print(f"\n📊 共生成 {saved_count} 份报告")
        print(f"📁 报告位置: {self.reports_dir}")

def main():
    """主函数"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/migration_progress.db'
    
    print("📊 正在生成消息映射报告...")
    print("=" * 60)
    
    generator = MappingReportGenerator(db_path)
    generator.save_reports()
    
    print("\n✅ 所有报告生成完成！")
    print("\n查看报告:")
    print("  cd devdoc/protobuf/reports")
    print("  ls -la *.md")

if __name__ == '__main__':
    main()
