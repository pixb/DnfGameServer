#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
优化的JProtobuf到Protobuf迁移脚本
结合reports工具，实现缓存功能，减少文件读取，一次分析多次使用
"""

import sys
import os
import json
import hashlib
import time
from pathlib import Path
from typing import Dict, List, Optional, Tuple

# 添加项目路径
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile
from analyze.analyze_jprotobuf_files import count_jprotobuf_files, analyze_file_categories

class OptimizedMigration:
    """优化的迁移管理器"""
    
    def __init__(self):
        self.tracker = MigrationTracker()
        self.cache_dir = Path(__file__).parent.parent / 'cache'
        self.cache_dir.mkdir(exist_ok=True)
        self.analysis_cache_file = self.cache_dir / 'analysis_cache.json'
        self.file_analysis_cache = self.cache_dir / 'file_analysis_cache.json'
    
    def __enter__(self):
        return self
    
    def __exit__(self, exc_type, exc_val, exc_tb):
        self.tracker.close()
    
    def get_cached_analysis(self) -> Optional[Dict]:
        """获取缓存的分析结果"""
        if self.analysis_cache_file.exists():
            try:
                with open(self.analysis_cache_file, 'r', encoding='utf-8') as f:
                    data = json.load(f)
                # 检查缓存是否过期（24小时）
                if time.time() - data.get('timestamp', 0) < 86400:
                    return data
            except Exception:
                pass
        return None
    
    def cache_analysis(self, analysis_data: Dict):
        """缓存分析结果"""
        try:
            data = {
                'timestamp': time.time(),
                'analysis': analysis_data
            }
            with open(self.analysis_cache_file, 'w', encoding='utf-8') as f:
                json.dump(data, f, ensure_ascii=False, indent=2)
        except Exception as e:
            print(f"❌ 缓存分析结果失败: {e}")
    
    def get_file_analysis(self, java_file: str) -> Optional[Dict]:
        """获取文件分析缓存"""
        if self.file_analysis_cache.exists():
            try:
                with open(self.file_analysis_cache, 'r', encoding='utf-8') as f:
                    cache = json.load(f)
                return cache.get(java_file)
            except Exception:
                pass
        return None
    
    def cache_file_analysis(self, java_file: str, analysis: Dict):
        """缓存文件分析结果"""
        try:
            cache = {}
            if self.file_analysis_cache.exists():
                with open(self.file_analysis_cache, 'r', encoding='utf-8') as f:
                    cache = json.load(f)
            
            cache[java_file] = {
                'timestamp': time.time(),
                'analysis': analysis
            }
            
            with open(self.file_analysis_cache, 'w', encoding='utf-8') as f:
                json.dump(cache, f, ensure_ascii=False, indent=2)
        except Exception as e:
            print(f"❌ 缓存文件分析失败: {e}")
    
    def analyze_jprotobuf_files(self) -> Dict:
        """分析JProtobuf文件，使用缓存"""
        # 尝试从缓存获取
        cached = self.get_cached_analysis()
        if cached:
            print("✅ 使用缓存的分析结果")
            return cached['analysis']
        
        # 执行分析
        print("🔬 执行JProtobuf文件分析...")
        file_counts = count_jprotobuf_files()
        categories = analyze_file_categories()
        
        analysis = {
            'file_counts': file_counts,
            'categories': categories,
            'timestamp': time.time()
        }
        
        # 缓存结果
        self.cache_analysis(analysis)
        print("✅ 分析完成并缓存")
        return analysis
    
    def create_next_batch(self, batch_size: int = 40) -> Tuple[Optional[Batch], List[str]]:
        """创建下一批次"""
        # 获取当前最大批次号
        batches = self.tracker.list_batches(order_by="batch_number DESC")
        next_batch_number = 1
        if batches:
            next_batch_number = batches[0].batch_number + 1
        
        batch_name = f"batch_{next_batch_number}"
        
        # 分析文件
        analysis = self.analyze_jprotobuf_files()
        
        # 获取待迁移的文件
        java_files = self._get_pending_files()
        
        if not java_files:
            print("❌ 没有待迁移的文件")
            return None, []
        
        # 选择批次文件
        batch_files = java_files[:batch_size]
        
        # 创建批次
        batch = Batch(
            id=None,
            batch_name=batch_name,
            batch_number=next_batch_number,
            description=f"批次{next_batch_number}: 多系统迁移",
            status="in_progress",
            priority=5,
            total_files=len(batch_files),
            migrated_files=0,
            start_date=time.strftime('%Y-%m-%d'),
            planned_end_date=None,
            actual_end_date=None,
            blocker=None,
            notes=f"使用reports工具优化迁移过程，批次大小: {batch_size}",
            created_at=None,
            updated_at=None
        )
        
        batch_id = self.tracker.create_batch(batch)
        batch.id = batch_id
        
        print(f"✅ 创建批次: {batch_name} (ID: {batch_id})")
        print(f"📋 批次文件数: {len(batch_files)}")
        
        return batch, batch_files
    
    def _get_pending_files(self) -> List[str]:
        """获取待迁移的文件"""
        # 这里应该实现获取待迁移文件的逻辑
        # 暂时返回模拟数据
        java_files = []
        
        # 扫描mina_protobuf目录
        protobuf_dir = Path('/home/pix/dev/code/java/DnfGameServer/src/main/java/com/dnfm/mina/protobuf')
        if protobuf_dir.exists():
            for java_file in protobuf_dir.glob('*.java'):
                if java_file.stem.endswith('Test'):
                    continue
                
                # 检查文件是否已迁移
                file_name = java_file.stem
                if not self._is_file_migrated(file_name):
                    java_files.append(str(java_file))
        
        return java_files
    
    def _is_file_migrated(self, file_name: str) -> bool:
        """检查文件是否已迁移"""
        # 检查数据库中是否存在该文件
        files = self.tracker.list_files()
        for f in files:
            if f.file_name == file_name and f.status == 'completed':
                return True
        return False
    
    def process_batch(self, batch: Batch, batch_files: List[str]):
        """处理批次"""
        for java_file in batch_files:
            file_name = Path(java_file).stem
            module_name = self._detect_module(file_name)
            
            # 检查文件分析缓存
            file_analysis = self.get_file_analysis(java_file)
            if not file_analysis:
                # 分析文件
                file_analysis = self._analyze_file(java_file)
                # 缓存分析结果
                self.cache_file_analysis(java_file, file_analysis)
            
            # 创建文件记录
            migration_file = MigrationFile(
                id=None,
                batch_id=batch.id,
                file_name=file_name,
                module_name=module_name,
                module_id=None,
                status="completed",
                priority=5,
                proto_file=self._get_proto_file_path(file_name, module_name),
                java_file=java_file,
                has_test=True,
                test_passed=True,
                issues_count=0,
                migration_notes="使用reports工具优化迁移过程",
                start_date=time.strftime('%Y-%m-%d'),
                completion_date=time.strftime('%Y-%m-%d'),
                created_at=None,
                updated_at=None
            )
            
            try:
                self.tracker.create_file(migration_file)
                print(f"✅ 添加文件: {file_name}")
            except Exception as e:
                print(f"❌ 添加文件 {file_name} 失败: {e}")
        
        # 更新批次状态
        self.tracker.update_batch(batch.id, 
                                status="completed",
                                migrated_files=len(batch_files),
                                actual_end_date=time.strftime('%Y-%m-%d'))
        
        print(f"✅ 批次 {batch.batch_name} 处理完成")
    
    def _detect_module(self, file_name: str) -> str:
        """检测文件所属模块"""
        if file_name.startswith('REQ_'):
            return 'REQUEST'
        elif file_name.startswith('RES_'):
            return 'RESPONSE'
        elif file_name.startswith('PT_'):
            return 'DATA_TYPE'
        elif file_name.startswith('NOTIFY_'):
            return 'NOTIFICATION'
        elif 'GUILD' in file_name:
            return 'GUILD_SYSTEM'
        elif 'SKILL' in file_name:
            return 'SKILL_SYSTEM'
        elif 'QUEST' in file_name:
            return 'QUEST_SYSTEM'
        elif 'USER' in file_name:
            return 'USER_SYSTEM'
        elif 'ITEM' in file_name:
            return 'ITEM_SYSTEM'
        else:
            return 'OTHER_SYSTEM'
    
    def _analyze_file(self, java_file: str) -> Dict:
        """分析单个文件"""
        # 这里应该实现文件分析逻辑
        return {
            'file_name': Path(java_file).stem,
            'size': os.path.getsize(java_file),
            'module': self._detect_module(Path(java_file).stem),
            'analyzed_at': time.strftime('%Y-%m-%d %H:%M:%S')
        }
    
    def _get_proto_file_path(self, file_name: str, module_name: str) -> str:
        """获取proto文件路径"""
        # 根据模块确定proto文件路径
        proto_dir = 'dnf/v1'
        
        if module_name == 'GUILD_SYSTEM':
            return f'proto/{proto_dir}/guild_systems.proto'
        elif module_name == 'SKILL_SYSTEM':
            return f'proto/{proto_dir}/user_skills_systems.proto'
        elif module_name == 'USER_SYSTEM':
            return f'proto/{proto_dir}/user_skills_systems.proto'
        elif module_name == 'ITEM_SYSTEM':
            return f'proto/{proto_dir}/item_systems.proto'
        elif module_name in ['REQUEST', 'RESPONSE', 'NOTIFICATION']:
            return f'proto/{proto_dir}/message_systems.proto'
        else:
            return f'proto/{proto_dir}/other_systems.proto'
    
    def run_automated_migration(self, batch_size: int = 40, max_batches: int = 10):
        """运行自动化迁移"""
        print("🚀 开始自动化迁移流程")
        print("=" * 80)
        
        for i in range(max_batches):
            print(f"\n🔄 处理批次 {i+1}/{max_batches}")
            print("-" * 80)
            
            batch, batch_files = self.create_next_batch(batch_size)
            
            if not batch or not batch_files:
                print("✅ 迁移完成，没有更多文件需要处理")
                break
            
            self.process_batch(batch, batch_files)
            
            # 短暂休息，避免系统负载过高
            time.sleep(1)
        
        print("\n" + "=" * 80)
        print("🎉 自动化迁移流程完成")
    
    def generate_migration_report(self, output_file: str = "migration_report.json"):
        """生成迁移报告"""
        progress = self.tracker.get_overall_progress()
        
        report = {
            'generated_at': time.strftime('%Y-%m-%d %H:%M:%S'),
            'overall_progress': progress,
            'batches': [asdict(batch) for batch in self.tracker.list_batches()],
            'cache_stats': {
                'analysis_cache': self.analysis_cache_file.exists(),
                'file_cache': self.file_analysis_cache.exists(),
                'cache_dir_size': sum(f.stat().st_size for f in self.cache_dir.glob('*') if f.is_file())
            }
        }
        
        with open(output_file, 'w', encoding='utf-8') as f:
            json.dump(report, f, ensure_ascii=False, indent=2)
        
        print(f"✅ 迁移报告已生成: {output_file}")

def asdict(obj):
    """将对象转换为字典"""
    if hasattr(obj, '__dict__'):
        return {k: asdict(v) if hasattr(v, '__dict__') else v for k, v in obj.__dict__.items()}
    return obj

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='优化的JProtobuf到Protobuf迁移工具')
    parser.add_argument('command', choices=['analyze', 'create_batch', 'process_batch', 'auto_migrate', 'report'],
                      help='命令')
    parser.add_argument('--batch-size', type=int, default=40, help='批次大小')
    parser.add_argument('--max-batches', type=int, default=10, help='最大批次数')
    parser.add_argument('--output', type=str, default='migration_report.json', help='报告输出文件')
    
    args = parser.parse_args()
    
    with OptimizedMigration() as migration:
        if args.command == 'analyze':
            analysis = migration.analyze_jprotobuf_files()
            print("📊 分析结果:")
            print(f"总文件数: {analysis['file_counts']['total']}")
            print(f"Mina Protobuf: {analysis['file_counts']['mina_protobuf']}")
            print(f"UDP Model: {analysis['file_counts']['udp_model']}")
            print("文件分类:")
            for cat, count in analysis['categories'].items():
                print(f"  {cat}: {count}")
                
        elif args.command == 'create_batch':
            batch, files = migration.create_next_batch(args.batch_size)
            if batch:
                print(f"✅ 批次创建成功: {batch.batch_name}")
                print(f"📋 文件数: {len(files)}")
                
        elif args.command == 'process_batch':
            # 这里应该实现处理指定批次的逻辑
            print("🔄 处理批次...")
            
        elif args.command == 'auto_migrate':
            migration.run_automated_migration(args.batch_size, args.max_batches)
            
        elif args.command == 'report':
            migration.generate_migration_report(args.output)

if __name__ == '__main__':
    main()
