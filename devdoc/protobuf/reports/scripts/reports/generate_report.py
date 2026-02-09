#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
生成迁移进度报告（Markdown 和 HTML 格式）
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf')

from migration_tracker import MigrationTracker
from datetime import datetime

def generate_markdown_report(output_file: str = "migration_report.md"):
    """生成 Markdown 格式的进度报告"""
    
    with MigrationTracker() as tracker:
        progress = tracker.get_overall_progress()
        batches = tracker.list_batches(order_by="batch_number")
        modules = tracker.get_module_progress()
        
        report = f"""# Protobuf 迁移进度报告

**生成时间**: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}

---

## 📊 总体进度

| 指标 | 数值 |
|------|------|
| **批次完成** | {progress['batches']['completed']}/{progress['batches']['total']} |
| **文件完成** | {progress['files']['migrated']}/{progress['files']['total']} |
| **进度百分比** | **{progress['files']['progress_percent']}%** |
| **待解决问题** | {progress['issues']['open']}/{progress['issues']['total']} |

### 文件状态分布

| 状态 | 数量 |
|------|------|
"""
        
        for status, count in progress['files']['by_status'].items():
            emoji = {"completed": "✅", "in_progress": "🔄", "pending": "⏳", 
                    "failed": "❌", "blocked": "🚫"}.get(status, "⚪")
            report += f"| {emoji} {status} | {count} |\n"
        
        report += f"""

---

## 📦 批次详情

| 批次 | 描述 | 状态 | 进度 | 优先级 |
|------|------|------|------|--------|
"""
        
        for batch in batches:
            status_emoji = {
                "completed": "✅",
                "in_progress": "🔄", 
                "pending": "⏳",
                "failed": "❌",
                "blocked": "🚫"
            }.get(batch.status, "⚪")
            
            progress_str = f"{batch.migrated_files}/{batch.total_files}"
            progress_pct = round(batch.migrated_files / batch.total_files * 100, 1) if batch.total_files > 0 else 0
            
            report += f"| {batch.batch_name} | {batch.description} | {status_emoji} {batch.status} | {progress_str} ({progress_pct}%) | {'⭐' * (batch.priority // 2)} |\n"
        
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
        
        # 添加问题汇总
        issues = tracker.list_issues(status='open')
        if issues:
            report += """

---

## 🔴 待解决问题

| ID | 批次 | 严重程度 | 标题 |
|----|------|----------|------|
"""
            for issue in issues:
                batch = tracker.get_batch(issue.batch_id)
                batch_name = batch.batch_name if batch else "-"
                severity_emoji = {"critical": "🔴", "high": "🟠", "medium": "🟡", "low": "🟢"}.get(issue.severity, "⚪")
                report += f"| {issue.id} | {batch_name} | {severity_emoji} {issue.severity} | {issue.title} |\n"
        
        report += """

---

*报告由迁移进度追踪系统自动生成*
"""
        
        with open(output_file, 'w', encoding='utf-8') as f:
            f.write(report)
        
        print(f"✅ Markdown 报告已生成: {output_file}")
        return report

def generate_html_report(output_file: str = "migration_report.html"):
    """生成 HTML 格式的进度报告"""
    
    with MigrationTracker() as tracker:
        progress = tracker.get_overall_progress()
        batches = tracker.list_batches(order_by="batch_number")
        modules = tracker.get_module_progress()
        
        html = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Protobuf 迁移进度报告</title>
    <style>
        body {{
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background: #f5f5f5;
        }}
        .header {{
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            border-radius: 10px;
            margin-bottom: 30px;
        }}
        .header h1 {{
            margin: 0 0 10px 0;
            font-size: 2.5em;
        }}
        .header .timestamp {{
            opacity: 0.9;
        }}
        .stats-grid {{
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }}
        .stat-card {{
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
        }}
        .stat-value {{
            font-size: 2.5em;
            font-weight: bold;
            color: #667eea;
        }}
        .stat-label {{
            color: #666;
            margin-top: 5px;
        }}
        .section {{
            background: white;
            padding: 25px;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }}
        .section h2 {{
            margin-top: 0;
            color: #333;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px;
        }}
        table {{
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }}
        th, td {{
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }}
        th {{
            background: #f8f9fa;
            font-weight: 600;
            color: #555;
        }}
        tr:hover {{
            background: #f8f9fa;
        }}
        .badge {{
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 0.85em;
            font-weight: 500;
        }}
        .badge-completed {{
            background: #d4edda;
            color: #155724;
        }}
        .badge-in_progress {{
            background: #fff3cd;
            color: #856404;
        }}
        .badge-pending {{
            background: #e2e3e5;
            color: #383d41;
        }}
        .badge-failed {{
            background: #f8d7da;
            color: #721c24;
        }}
        .progress-bar {{
            width: 100%;
            height: 20px;
            background: #e9ecef;
            border-radius: 10px;
            overflow: hidden;
        }}
        .progress-fill {{
            height: 100%;
            background: linear-gradient(90deg, #667eea 0%, #764ba2 100%);
            transition: width 0.3s ease;
        }}
        .progress-text {{
            text-align: center;
            margin-top: 5px;
            font-weight: 500;
            color: #667eea;
        }}
        .footer {{
            text-align: center;
            color: #999;
            margin-top: 40px;
            padding-top: 20px;
            border-top: 1px solid #eee;
        }}
    </style>
</head>
<body>
    <div class="header">
        <h1>📊 Protobuf 迁移进度报告</h1>
        <div class="timestamp">生成时间: {datetime.now().strftime('%Y年%m月%d日 %H:%M:%S')}</div>
    </div>
    
    <div class="stats-grid">
        <div class="stat-card">
            <div class="stat-value">{progress['batches']['completed']}/{progress['batches']['total']}</div>
            <div class="stat-label">批次完成</div>
        </div>
        <div class="stat-card">
            <div class="stat-value">{progress['files']['migrated']}/{progress['files']['total']}</div>
            <div class="stat-label">文件完成</div>
        </div>
        <div class="stat-card">
            <div class="stat-value">{progress['files']['progress_percent']}%</div>
            <div class="stat-label">总进度</div>
        </div>
        <div class="stat-card">
            <div class="stat-value">{progress['issues']['open']}</div>
            <div class="stat-label">待解决问题</div>
        </div>
    </div>
"""
        
        # 添加总体进度条
        html += f"""
    <div class="section">
        <h2>📈 总体进度</h2>
        <div class="progress-bar">
            <div class="progress-fill" style="width: {progress['files']['progress_percent']}%"></div>
        </div>
        <div class="progress-text">{progress['files']['migrated']} / {progress['files']['total']} 文件已迁移 ({progress['files']['progress_percent']}%)</div>
    </div>
"""
        
        # 添加批次表格
        html += """
    <div class="section">
        <h2>📦 批次详情</h2>
        <table>
            <thead>
                <tr>
                    <th>批次</th>
                    <th>描述</th>
                    <th>状态</th>
                    <th>进度</th>
                    <th>优先级</th>
                </tr>
            </thead>
            <tbody>
"""
        
        for batch in batches:
            status_class = f"badge-{batch.status}"
            progress_pct = round(batch.migrated_files / batch.total_files * 100, 1) if batch.total_files > 0 else 0
            
            html += f"""
                <tr>
                    <td><strong>{batch.batch_name}</strong></td>
                    <td>{batch.description}</td>
                    <td><span class="badge {status_class}">{batch.status}</span></td>
                    <td>{batch.migrated_files}/{batch.total_files} ({progress_pct}%)</td>
                    <td>{"⭐" * (batch.priority // 2)}</td>
                </tr>
"""
        
        html += """
            </tbody>
        </table>
    </div>
"""
        
        # 添加模块进度
        if modules:
            html += """
    <div class="section">
        <h2>🔧 模块进度</h2>
        <table>
            <thead>
                <tr>
                    <th>模块</th>
                    <th>总文件</th>
                    <th>已完成</th>
                    <th>进行中</th>
                    <th>进度</th>
                </tr>
            </thead>
            <tbody>
"""
            for module in modules:
                html += f"""
                <tr>
                    <td><strong>{module['module_name']}</strong></td>
                    <td>{module['total_files']}</td>
                    <td>{module['completed_files']}</td>
                    <td>{module['in_progress_files']}</td>
                    <td>{module['progress_percent']}%</td>
                </tr>
"""
            
            html += """
            </tbody>
        </table>
    </div>
"""
        
        # 添加问题列表
        issues = tracker.list_issues(status='open')
        if issues:
            html += """
    <div class="section">
        <h2>🔴 待解决问题</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>批次</th>
                    <th>严重程度</th>
                    <th>标题</th>
                </tr>
            </thead>
            <tbody>
"""
            for issue in issues:
                batch = tracker.get_batch(issue.batch_id)
                batch_name = batch.batch_name if batch else "-"
                severity_color = {"critical": "#dc3545", "high": "#fd7e14", "medium": "#ffc107", "low": "#28a745"}.get(issue.severity, "#6c757d")
                
                html += f"""
                <tr>
                    <td>{issue.id}</td>
                    <td>{batch_name}</td>
                    <td><span style="color: {severity_color}; font-weight: bold;">{issue.severity.upper()}</span></td>
                    <td>{issue.title}</td>
                </tr>
"""
            
            html += """
            </tbody>
        </table>
    </div>
"""
        
        html += """
    <div class="footer">
        <p>报告由迁移进度追踪系统自动生成</p>
    </div>
</body>
</html>
"""
        
        with open(output_file, 'w', encoding='utf-8') as f:
            f.write(html)
        
        print(f"✅ HTML 报告已生成: {output_file}")
        return html

if __name__ == '__main__':
    import argparse
    
    parser = argparse.ArgumentParser(description='生成迁移进度报告')
    parser.add_argument('--format', choices=['markdown', 'html', 'both'], 
                       default='both', help='报告格式')
    parser.add_argument('--output', default='migration_report', 
                       help='输出文件名（不含扩展名）')
    
    args = parser.parse_args()
    
    if args.format in ['markdown', 'both']:
        generate_markdown_report(f"{args.output}.md")
    
    if args.format in ['html', 'both']:
        generate_html_report(f"{args.output}.html")
    
    print("\n✨ 报告生成完成！")
