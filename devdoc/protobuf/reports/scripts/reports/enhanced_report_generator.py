#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
增强版迁移进度报告生成器
提供更丰富的报告功能和更好的可视化效果
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker
from datetime import datetime, timedelta
from typing import List, Dict
import json
from pathlib import Path

class EnhancedReportGenerator:
    """增强版报告生成器"""
    
    def __init__(self, db_path: str = None):
        self.tracker = MigrationTracker(db_path) if db_path else MigrationTracker()
        self.outputs_dir = Path(__file__).parent.parent.parent / 'outputs'
    
    def generate_comprehensive_report(self, output_file: str = "comprehensive_migration_report.md"):
        """生成综合迁移报告"""
        
        # 默认输出到 outputs 目录
        if not Path(output_file).is_absolute():
            output_file = str(self.outputs_dir / output_file)
        
        progress = self.tracker.get_overall_progress()
        batches = self.tracker.list_batches(order_by="batch_number")
        modules = self.tracker.get_module_progress()
        issues = self.tracker.list_issues()
        
        report = f"""# Protobuf 迁移综合报告

**生成时间**: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}

---

## 📊 执行摘要

### 总体进度

| 指标 | 数值 | 状态 |
|------|------|------|
| **批次完成** | {progress['batches']['completed']}/{progress['batches']['total']} | {'✅ 全部完成' if progress['batches']['completed'] == progress['batches']['total'] else '🔄 进行中'} |
| **文件完成** | {progress['files']['migrated']}/{progress['files']['total']} | {'✅ 全部完成' if progress['files']['migrated'] == progress['files']['total'] else '🔄 进行中'} |
| **总体进度** | **{progress['files']['progress_percent']}%** | {'🎉 迁移完成' if progress['files']['progress_percent'] == 100 else '🔄 迁移中'} |
| **待解决问题** | {progress['issues']['open']}/{progress['issues']['total']} | {'✅ 无问题' if progress['issues']['open'] == 0 else '⚠️ 需要关注'} |

### 文件状态分布

| 状态 | 数量 | 占比 |
|------|------|------|
"""
        
        total_files = sum(progress['files']['by_status'].values())
        for status, count in progress['files']['by_status'].items():
            emoji = {"completed": "✅", "in_progress": "🔄", "pending": "⏳", 
                    "failed": "❌", "blocked": "🚫"}.get(status, "⚪")
            percentage = round(count / total_files * 100, 1) if total_files > 0 else 0
            report += f"| {emoji} {status} | {count} | {percentage}% |\n"
        
        # 测试覆盖率
        test_coverage = self._calculate_test_coverage()
        report += f"""

### 测试覆盖率

| 指标 | 数值 |
|------|------|
| **有测试的文件** | {test_coverage['with_test']} |
| **测试通过** | {test_coverage['test_passed']} |
| **测试覆盖率** | {test_coverage['coverage']}% |

---

## 📦 批次详情

### 已完成批次

| 批次 | 描述 | 文件数 | 完成日期 | 耗时 |
|------|------|--------|----------|------|
"""
        
        completed_batches = [b for b in batches if b.status == 'completed']
        for batch in completed_batches:
            duration = self._calculate_duration(batch.start_date, batch.actual_end_date)
            report += f"| {batch.batch_name} | {batch.description} | {batch.total_files} | {batch.actual_end_date or '-'} | {duration} |\n"
        
        in_progress_batches = [b for b in batches if b.status == 'in_progress']
        if in_progress_batches:
            report += """

### 进行中批次

| 批次 | 描述 | 进度 | 开始日期 |
|------|------|------|----------|
"""
            for batch in in_progress_batches:
                progress_pct = round(batch.migrated_files / batch.total_files * 100, 1) if batch.total_files > 0 else 0
                report += f"| {batch.batch_name} | {batch.description} | {batch.migrated_files}/{batch.total_files} ({progress_pct}%) | {batch.start_date or '-'} |\n"
        
        pending_batches = [b for b in batches if b.status == 'pending']
        if pending_batches:
            report += """

### 待开始批次

| 批次 | 描述 | 优先级 |
|------|------|--------|
"""
            for batch in pending_batches:
                report += f"| {batch.batch_name} | {batch.description} | {'⭐' * (batch.priority // 2)} |\n"
        
        # 模块进度
        if modules:
            report += """

---

## 🔧 模块进度

| 模块 | 总文件 | 已完成 | 进行中 | 进度 |
|------|--------|--------|--------|------|
"""
            for module in modules:
                bar_length = 20
                filled = int(module['progress_percent'] / 100 * bar_length)
                bar = "█" * filled + "░" * (bar_length - filled)
                report += f"| {module['module_name']} | {module['total_files']} | {module['completed_files']} | {module['in_progress_files']} | {bar} {module['progress_percent']}% |\n"
        
        # 问题汇总
        if issues:
            report += """

---

## 🔴 问题汇总

### 待解决问题

| ID | 批次 | 严重程度 | 标题 | 创建时间 |
|----|------|----------|------|----------|
"""
            open_issues = [i for i in issues if i.status == 'open']
            for issue in open_issues:
                batch = self.tracker.get_batch(issue.batch_id)
                batch_name = batch.batch_name if batch else "-"
                severity_emoji = {"critical": "🔴", "high": "🟠", "medium": "🟡", "low": "🟢"}.get(issue.severity, "⚪")
                created_time = issue.created_at[:10] if issue.created_at else "-"
                report += f"| {issue.id} | {batch_name} | {severity_emoji} {issue.severity} | {issue.title} | {created_time} |\n"
            
            resolved_issues = [i for i in issues if i.status == 'resolved']
            if resolved_issues:
                report += """

### 已解决问题

| ID | 批次 | 严重程度 | 标题 | 解决时间 |
|----|------|----------|------|----------|
"""
                for issue in resolved_issues:
                    batch = self.tracker.get_batch(issue.batch_id)
                    batch_name = batch.batch_name if batch else "-"
                    severity_emoji = {"critical": "🔴", "high": "🟠", "medium": "🟡", "low": "🟢"}.get(issue.severity, "⚪")
                    resolved_time = issue.resolved_at[:10] if issue.resolved_at else "-"
                    report += f"| {issue.id} | {batch_name} | {severity_emoji} {issue.severity} | {issue.title} | {resolved_time} |\n"
        
        # 迁移时间线
        report += """

---

## 📅 迁移时间线

| 日期 | 批次 | 状态 | 备注 |
|------|------|------|------|
"""
        
        timeline = self._generate_timeline(batches)
        for item in timeline:
            report += f"| {item['date']} | {item['batch']} | {item['status']} | {item['notes']} |\n"
        
        # 统计信息
        stats = self._calculate_statistics(batches)
        report += f"""

---

## 📈 统计信息

### 迁移速度

| 指标 | 数值 |
|------|------|
| **平均每批文件数** | {stats['avg_files_per_batch']:.1f} |
| **最快批次** | {stats['fastest_batch']['name']} ({stats['fastest_batch']['duration']}) |
| **最慢批次** | {stats['slowest_batch']['name']} ({stats['slowest_batch']['duration']}) |
| **总迁移时间** | {stats['total_duration']} |

### 模块分布

| 模块类型 | 批次数 | 文件数 |
|----------|--------|--------|
"""
        
        for module_type, data in stats['module_distribution'].items():
            report += f"| {module_type} | {data['batch_count']} | {data['file_count']} |\n"
        
        report += """

---

*报告由迁移进度追踪系统自动生成*
"""
        
        with open(output_file, 'w', encoding='utf-8') as f:
            f.write(report)
        
        print(f"✅ 综合报告已生成: {output_file}")
        return report
    
    def _calculate_test_coverage(self) -> Dict:
        """计算测试覆盖率"""
        files = self.tracker.list_files()
        with_test = sum(1 for f in files if f.has_test)
        test_passed = sum(1 for f in files if f.test_passed)
        coverage = round(with_test / len(files) * 100, 1) if files else 0
        return {
            'with_test': with_test,
            'test_passed': test_passed,
            'coverage': coverage
        }
    
    def _calculate_duration(self, start_date: str, end_date: str) -> str:
        """计算持续时间"""
        if not start_date or not end_date:
            return "-"
        try:
            start = datetime.strptime(start_date, '%Y-%m-%d')
            end = datetime.strptime(end_date, '%Y-%m-%d')
            duration = (end - start).days
            if duration == 0:
                return "1天"
            elif duration == 1:
                return "1天"
            else:
                return f"{duration}天"
        except:
            return "-"
    
    def _generate_timeline(self, batches: List) -> List[Dict]:
        """生成时间线"""
        timeline = []
        for batch in batches:
            if batch.start_date:
                timeline.append({
                    'date': batch.start_date,
                    'batch': batch.batch_name,
                    'status': batch.status,
                    'notes': batch.description[:50] + "..." if len(batch.description) > 50 else batch.description
                })
        return sorted(timeline, key=lambda x: x['date'])
    
    def _calculate_statistics(self, batches: List) -> Dict:
        """计算统计信息"""
        completed_batches = [b for b in batches if b.status == 'completed']
        
        avg_files = round(sum(b.total_files for b in completed_batches) / len(completed_batches), 1) if completed_batches else 0
        
        durations = []
        for batch in completed_batches:
            if batch.start_date and batch.actual_end_date:
                try:
                    start = datetime.strptime(batch.start_date, '%Y-%m-%d')
                    end = datetime.strptime(batch.actual_end_date, '%Y-%m-%d')
                    duration = (end - start).days + 1
                    durations.append((batch.batch_name, duration))
                except:
                    pass
        
        fastest = min(durations, key=lambda x: x[1]) if durations else (None, 0)
        slowest = max(durations, key=lambda x: x[1]) if durations else (None, 0)
        
        # 计算总迁移时间
        if completed_batches:
            first_start = min(datetime.strptime(b.start_date, '%Y-%m-%d') for b in completed_batches if b.start_date)
            last_end = max(datetime.strptime(b.actual_end_date, '%Y-%m-%d') for b in completed_batches if b.actual_end_date)
            total_duration = (last_end - first_start).days + 1
        else:
            total_duration = 0
        
        # 模块分布
        module_dist = {}
        for batch in batches:
            files = self.tracker.list_files(batch_id=batch.id)
            module_type = batch.description.split('（')[0] if '（' in batch.description else batch.description
            if module_type not in module_dist:
                module_dist[module_type] = {'batch_count': 0, 'file_count': 0}
            module_dist[module_type]['batch_count'] += 1
            module_dist[module_type]['file_count'] += len(files)
        
        return {
            'avg_files_per_batch': avg_files,
            'fastest_batch': {'name': fastest[0], 'duration': f"{fastest[1]}天"},
            'slowest_batch': {'name': slowest[0], 'duration': f"{slowest[1]}天"},
            'total_duration': f"{total_duration}天",
            'module_distribution': module_dist
        }
    
    def generate_json_report(self, output_file: str = "migration_report.json"):
        """生成JSON格式的报告"""
        
        # 默认输出到 outputs 目录
        if not Path(output_file).is_absolute():
            output_file = str(self.outputs_dir / output_file)
        
        progress = self.tracker.get_overall_progress()
        batches = self.tracker.list_batches(order_by="batch_number")
        modules = self.tracker.get_module_progress()
        issues = self.tracker.list_issues()
        
        report = {
            'generated_at': datetime.now().isoformat(),
            'summary': {
                'batches': progress['batches'],
                'files': progress['files'],
                'issues': progress['issues']
            },
            'batches': [
                {
                    'name': b.batch_name,
                    'number': b.batch_number,
                    'description': b.description,
                    'status': b.status,
                    'priority': b.priority,
                    'total_files': b.total_files,
                    'migrated_files': b.migrated_files,
                    'start_date': b.start_date,
                    'actual_end_date': b.actual_end_date,
                    'notes': b.notes
                }
                for b in batches
            ],
            'modules': modules,
            'issues': [
                {
                    'id': i.id,
                    'batch_id': i.batch_id,
                    'title': i.title,
                    'description': i.description,
                    'status': i.status,
                    'severity': i.severity,
                    'created_at': i.created_at,
                    'resolved_at': i.resolved_at
                }
                for i in issues
            ]
        }
        
        with open(output_file, 'w', encoding='utf-8') as f:
            json.dump(report, f, ensure_ascii=False, indent=2)
        
        print(f"✅ JSON报告已生成: {output_file}")
        return report


def main():
    """主函数"""
    generator = EnhancedReportGenerator()
    
    print("\n📊 正在生成综合迁移报告...")
    generator.generate_comprehensive_report("comprehensive_migration_report.md")
    
    print("\n📊 正在生成JSON报告...")
    generator.generate_json_report("migration_report.json")
    
    print("\n✅ 所有报告生成完成")


if __name__ == '__main__':
    main()
