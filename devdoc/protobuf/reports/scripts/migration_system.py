#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
迁移系统主控制脚本
整合所有迁移系统功能，提供统一的入口
"""

import argparse
import sys
from pathlib import Path

# 添加脚本目录到Python路径
script_dir = Path(__file__).parent
sys.path.insert(0, str(script_dir))

from init_migration_database import MigrationDatabaseInitializer
from jprotobuf_scanner import JProtobufScanner
from proto_scanner import ProtoScanner
from mapping_analyzer import MessageMappingAnalyzer
from migration_history_system import MigrationHistorySystem
from migration_status_tracker import MigrationStatusTracker
from migration_report_generator import MigrationReportGenerator

class MigrationSystemController:
    """迁移系统控制器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.initializer = MigrationDatabaseInitializer(db_path)
        self.jprotobuf_scanner = JProtobufScanner(db_path)
        self.proto_scanner = ProtoScanner(db_path)
        self.mapping_analyzer = MessageMappingAnalyzer(db_path)
        self.history_system = MigrationHistorySystem(db_path)
        self.status_tracker = MigrationStatusTracker(db_path)
        self.report_generator = MigrationReportGenerator(db_path)
    
    def init_system(self):
        """初始化迁移系统"""
        print("🚀 初始化迁移系统...")
        print()
        self.initializer.initialize_database()
    
    def scan_all(self):
        """扫描所有文件"""
        print("🔍 扫描所有文件...")
        print()
        self.jprotobuf_scanner.scan_and_save()
        self.proto_scanner.scan_and_save()
    
    def analyze_mappings(self):
        """分析映射关系"""
        print("🔗 分析映射关系...")
        print()
        self.mapping_analyzer.analyze_and_save()
    
    def import_history(self):
        """导入迁移历史"""
        print("📜 导入迁移历史...")
        print()
        self.history_system.import_all_batches()
    
    def generate_reports(self):
        """生成报告"""
        print("📊 生成报告...")
        print()
        self.report_generator.generate_all_reports()
    
    def show_status(self):
        """显示状态"""
        self.status_tracker.print_overall_status()
    
    def full_setup(self):
        """完整设置"""
        print("🚀 开始完整设置...")
        print()
        
        # 1. 初始化数据库
        self.init_system()
        
        # 2. 扫描所有文件
        self.scan_all()
        
        # 3. 分析映射关系
        self.analyze_mappings()
        
        # 4. 导入迁移历史
        self.import_history()
        
        # 5. 生成报告
        self.generate_reports()
        
        # 6. 显示状态
        self.show_status()
        
        print("✅ 完整设置完成！")
        print()

def main():
    """主函数"""
    parser = argparse.ArgumentParser(
        description='JProtobuf到标准Protobuf迁移系统',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
示例:
  python migration_system.py --init              # 初始化数据库
  python migration_system.py --scan-all          # 扫描所有文件
  python migration_system.py --analyze            # 分析映射关系
  python migration_system.py --import-history     # 导入迁移历史
  python migration_system.py --generate-reports   # 生成报告
  python migration_system.py --status             # 显示状态
  python migration_system.py --full-setup         # 完整设置
        """
    )
    
    parser.add_argument('--init', action='store_true', help='初始化数据库')
    parser.add_argument('--scan-all', action='store_true', help='扫描所有文件')
    parser.add_argument('--analyze', action='store_true', help='分析映射关系')
    parser.add_argument('--import-history', action='store_true', help='导入迁移历史')
    parser.add_argument('--generate-reports', action='store_true', help='生成报告')
    parser.add_argument('--status', action='store_true', help='显示状态')
    parser.add_argument('--full-setup', action='store_true', help='完整设置')
    
    args = parser.parse_args()
    
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    controller = MigrationSystemController(db_path)
    
    if args.init:
        controller.init_system()
    elif args.scan_all:
        controller.scan_all()
    elif args.analyze:
        controller.analyze_mappings()
    elif args.import_history:
        controller.import_history()
    elif args.generate_reports:
        controller.generate_reports()
    elif args.status:
        controller.show_status()
    elif args.full_setup:
        controller.full_setup()
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
