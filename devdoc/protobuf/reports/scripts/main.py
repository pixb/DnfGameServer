#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移追踪系统主入口

提供统一接口访问所有功能模块
"""

import sys
import os
from pathlib import Path

# 添加项目根目录到Python路径
project_root = Path(__file__).parent.parent.parent.parent
sys.path.insert(0, str(project_root / 'devdoc' / 'protobuf'))

from core.migration_tracker import MigrationTracker
from batch_management.update_all_batches import update_all_batches
from batch_management.fix_batch_status import fix_batch_status
from batch_management.add_batches_22_33_simple import add_batches_22_33
from reports.generate_report import generate_markdown_report, generate_html_report
from reports.enhanced_report_generator import EnhancedReportGenerator
from ai_assistant import AIMigrationAssistant
from query.query_mappings import MessageMappingQuery
from analyze.analyze_jprotobuf_files import count_jprotobuf_files


def status():
    """显示当前迁移状态"""
    with MigrationTracker() as tracker:
        progress = tracker.get_overall_progress()
        batches = tracker.list_batches()

        print("\n" + "="*80)
        print("📊 Protobuf迁移进度追踪系统")
        print("="*80)

        print(f"\n✅ 总体进度: {progress['files']['progress_percent']}%")
        print(f"   文件: {progress['files']['migrated']}/{progress['files']['total']}")
        print(f"   批次: {progress['batches']['completed']}/{progress['batches']['total']}")

        print("\n📋 批次列表:")
        for batch in batches:
            status_emoji = {
                'pending': '⏳',
                'in_progress': '🔄',
                'completed': '✅',
                'failed': '❌',
                'blocked': '🚫',
                'skipped': '⏭️'
            }.get(batch.status, '❓')

            print(f"  {status_emoji} {batch.batch_name} (#{batch.batch_number}): {batch.description}")
            print(f"     状态: {batch.status.upper()}, 进度: {batch.migrated_files}/{batch.total_files}")

        print("\n" + "="*80)


def generate_report(format='markdown'):
    """生成迁移报告"""
    print(f"\n📄 正在生成 {format.upper()} 报告...")
    if format == 'markdown':
        generate_markdown_report()
    elif format == 'html':
        generate_html_report()
    elif format == 'comprehensive':
        generator = EnhancedReportGenerator()
        generator.generate_comprehensive_report()
        generator.generate_json_report()
    print("✅ 报告生成完成")


def query(old_name=None, new_name=None, module_id=None, batch_name=None):
    """查询消息映射"""
    query = MessageMappingQuery('/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_progress.db')

    if old_name:
        query.query_by_old_name(old_name)
    elif new_name:
        query.query_by_new_name(new_name)
    elif module_id:
        query.query_by_module_id(module_id)
    elif batch_name:
        query.query_by_batch(batch_name)
    else:
        query.list_all()


def fix_all_batches():
    """修正所有批次状态"""
    print("\n🔧 正在修正所有批次状态...")
    fix_batch_status()
    print("\n✅ 批次状态修正完成")


def analyze():
    """分析JProtobuf使用情况"""
    print("\n🔬 正在分析JProtobuf使用情况...")
    result = count_jprotobuf_files()
    print("\n📊 分析结果:")
    for key, value in result.items():
        print(f"  {key}: {value}")
    print(f"  总计: {result['total']}")


def add_batches():
    """添加批次22-33到数据库"""
    print("\n📦 正在添加批次22-33到数据库...")
    add_batches_22_33()
    print("\n✅ 批次添加完成")


def ai_summary():
    """AI 助手摘要"""
    print("\n🤖 AI 迁移助手摘要")
    print("=" * 80)
    
    assistant = AIMigrationAssistant()
    summary = assistant.get_migration_summary()
    
    print(f"\n📊 总体进度: {summary['overall_progress']['files']['progress_percent']}%")
    print(f"   文件: {summary['overall_progress']['files']['migrated']}/{summary['overall_progress']['files']['total']}")
    print(f"   批次: {summary['overall_progress']['batches']['completed']}/{summary['overall_progress']['batches']['total']}")
    
    if summary['next_batch']:
        print(f"\n📋 下一个批次: {summary['next_batch']['batch_name']} ({summary['next_batch']['description']})")
        print(f"   优先级: {summary['next_batch']['priority']}")
        print(f"   文件数: {summary['next_batch']['total_files']}")
    else:
        print(f"\n📋 所有批次已完成")
    
    print(f"\n🔴 待解决问题: {summary['open_issues']}")
    
    print("\n" + "=" * 80)


def ai_scan():
    """AI 助手文件扫描"""
    print("\n🔍 AI 迁移助手文件扫描")
    print("=" * 80)
    
    assistant = AIMigrationAssistant()
    
    proto_files = assistant.scan_proto_files()
    print(f"\n📁 Proto 文件: {len(proto_files)} 个")
    for pf in proto_files[:10]:
        print(f"   {pf['path']}")
    if len(proto_files) > 10:
        print(f"   ... 还有 {len(proto_files) - 10} 个文件")
    
    java_files = assistant.scan_java_files()
    print(f"\n📁 Java 文件: {len(java_files)} 个")
    
    test_files = assistant.scan_test_files()
    print(f"\n📁 测试文件: {len(test_files)} 个")
    for tf in test_files[:10]:
        print(f"   {tf['path']}")
    if len(test_files) > 10:
        print(f"   ... 还有 {len(test_files) - 10} 个文件")
    
    print("\n" + "=" * 80)


def ai_export():
    """AI 助手数据导出"""
    print("\n💾 AI 迁移助手数据导出")
    print("=" * 80)
    
    assistant = AIMigrationAssistant()
    output_file = assistant.export_to_json()
    
    print(f"\n✅ 数据已导出到: {output_file}")
    print("\n" + "=" * 80)


def main():
    """主函数"""
    if len(sys.argv) < 2:
        # 默认显示状态
        status()
        return

    command = sys.argv[1]

    commands = {
        'status': status,
        'report': lambda: generate_report(sys.argv[2] if len(sys.argv) > 2 else 'markdown'),
        'query': lambda: query(*sys.argv[2:]),
        'fix': fix_all_batches,
        'analyze': analyze,
        'add-batches': add_batches,
        'ai-summary': ai_summary,
        'ai-scan': ai_scan,
        'ai-export': ai_export,
    }

    if command in commands:
        commands[command]()
    else:
        print(f"❌ 未知命令: {command}")
        print("\n可用命令:")
        for cmd in commands.keys():
            print(f"  {cmd}")


if __name__ == '__main__':
    main()
