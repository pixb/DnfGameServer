#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
AI 迁移操作助手
为AI提供友好的接口，用于管理迁移工作、查询工作、记录工作
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile, Issue, MigrationStatus, IssueStatus
from pathlib import Path
from typing import List, Dict, Optional, Tuple
import json


class AIMigrationAssistant:
    """AI 迁移操作助手"""
    
    def __init__(self):
        self.tracker = MigrationTracker()
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
    
    # ========== 快速查询接口 ==========
    
    def get_next_batch_to_migrate(self) -> Optional[Dict]:
        """获取下一个待迁移的批次"""
        batches = self.tracker.list_batches(status='pending', order_by='priority DESC, batch_number')
        if batches:
            batch = batches[0]
            return {
                'batch_name': batch.batch_name,
                'batch_number': batch.batch_number,
                'description': batch.description,
                'priority': batch.priority,
                'total_files': batch.total_files
            }
        return None
    
    def get_batch_files(self, batch_name: str) -> List[Dict]:
        """获取批次的所有文件"""
        batch = self.tracker.get_batch_by_name(batch_name)
        if not batch:
            return []
        
        files = self.tracker.list_files(batch_id=batch.id)
        return [
            {
                'file_name': f.file_name,
                'module_name': f.module_name,
                'status': f.status,
                'proto_file': f.proto_file,
                'java_file': f.java_file,
                'has_test': f.has_test,
                'test_passed': f.test_passed
            }
            for f in files
        ]
    
    def get_file_info(self, file_name: str) -> Optional[Dict]:
        """获取文件详细信息"""
        files = self.tracker.list_files()
        for f in files:
            if f.file_name == file_name:
                batch = self.tracker.get_batch(f.batch_id)
                return {
                    'file_name': f.file_name,
                    'module_name': f.module_name,
                    'batch_name': batch.batch_name if batch else None,
                    'status': f.status,
                    'proto_file': f.proto_file,
                    'java_file': f.java_file,
                    'has_test': f.has_test,
                    'test_passed': f.test_passed,
                    'issues_count': f.issues_count
                }
        return None
    
    def search_files_by_module(self, module_name: str) -> List[Dict]:
        """按模块搜索文件"""
        files = self.tracker.list_files(module_name=module_name)
        return [
            {
                'file_name': f.file_name,
                'status': f.status,
                'has_test': f.has_test,
                'test_passed': f.test_passed
            }
            for f in files
        ]
    
    def get_open_issues(self, batch_name: Optional[str] = None) -> List[Dict]:
        """获取所有待解决的问题"""
        if batch_name:
            batch = self.tracker.get_batch_by_name(batch_name)
            if not batch:
                return []
            issues = self.tracker.list_issues(batch_id=batch.id, status='open')
        else:
            issues = self.tracker.list_issues(status='open')
        
        return [
            {
                'id': i.id,
                'title': i.title,
                'description': i.description,
                'severity': i.severity,
                'created_at': i.created_at
            }
            for i in issues
        ]
    
    # ========== 快速更新接口 ==========
    
    def start_batch_migration(self, batch_name: str) -> bool:
        """开始迁移批次"""
        batch = self.tracker.get_batch_by_name(batch_name)
        if not batch:
            return False
        
        from datetime import datetime
        return self.tracker.update_batch(
            batch.id,
            status=MigrationStatus.IN_PROGRESS.value,
            start_date=datetime.now().strftime('%Y-%m-%d')
        )
    
    def complete_batch_migration(self, batch_name: str) -> bool:
        """完成批次迁移"""
        batch = self.tracker.get_batch_by_name(batch_name)
        if not batch:
            return False
        
        from datetime import datetime
        return self.tracker.update_batch(
            batch.id,
            status=MigrationStatus.COMPLETED.value,
            actual_end_date=datetime.now().strftime('%Y-%m-%d')
        )
    
    def update_file_status(self, file_name: str, status: str, 
                          proto_file: Optional[str] = None,
                          java_file: Optional[str] = None,
                          has_test: bool = False,
                          test_passed: bool = False) -> bool:
        """更新文件状态"""
        files = self.tracker.list_files()
        for f in files:
            if f.file_name == file_name:
                update_data = {'status': status}
                if proto_file:
                    update_data['proto_file'] = proto_file
                if java_file:
                    update_data['java_file'] = java_file
                update_data['has_test'] = has_test
                update_data['test_passed'] = test_passed
                
                if status == MigrationStatus.COMPLETED.value:
                    from datetime import datetime
                    update_data['completion_date'] = datetime.now().strftime('%Y-%m-%d')
                
                return self.tracker.update_file(f.id, **update_data)
        return False
    
    def add_issue(self, title: str, description: str, 
                  batch_name: Optional[str] = None,
                  file_name: Optional[str] = None,
                  severity: str = 'medium') -> int:
        """添加问题"""
        batch_id = None
        file_id = None
        
        if batch_name:
            batch = self.tracker.get_batch_by_name(batch_name)
            if batch:
                batch_id = batch.id
        
        if file_name:
            files = self.tracker.list_files()
            for f in files:
                if f.file_name == file_name:
                    file_id = f.id
                    break
        
        issue = Issue(
            id=None,
            batch_id=batch_id,
            file_id=file_id,
            title=title,
            description=description,
            solution=None,
            status=IssueStatus.OPEN.value,
            severity=severity,
            tags=None,
            created_at=None,
            resolved_at=None
        )
        
        return self.tracker.create_issue(issue)
    
    def resolve_issue(self, issue_id: int, solution: Optional[str] = None) -> bool:
        """解决问题"""
        update_data = {'status': IssueStatus.RESOLVED.value}
        if solution:
            update_data['solution'] = solution
        return self.tracker.update_issue(issue_id, **update_data)
    
    # ========== 文件扫描接口 ==========
    
    def scan_proto_files(self) -> List[Dict]:
        """扫描所有 proto 文件"""
        proto_dir = self.project_root / 'proto'
        proto_files = list(proto_dir.rglob('*.proto'))
        
        results = []
        for proto_file in proto_files:
            results.append({
                'path': str(proto_file.relative_to(self.project_root)),
                'name': proto_file.stem,
                'size': proto_file.stat().st_size
            })
        
        return sorted(results, key=lambda x: x['path'])
    
    def scan_java_files(self) -> List[Dict]:
        """扫描所有 Java 文件"""
        java_dir = self.project_root / 'src' / 'main' / 'java'
        java_files = list(java_dir.rglob('*.java'))
        
        results = []
        for java_file in java_files:
            results.append({
                'path': str(java_file.relative_to(self.project_root)),
                'name': java_file.stem,
                'size': java_file.stat().st_size
            })
        
        return sorted(results, key=lambda x: x['path'])
    
    def scan_test_files(self) -> List[Dict]:
        """扫描所有测试文件"""
        test_dir = self.project_root / 'dnf-go-client' / 'test'
        test_files = list(test_dir.rglob('*_test.go'))
        
        results = []
        for test_file in test_files:
            results.append({
                'path': str(test_file.relative_to(self.project_root)),
                'name': test_file.stem,
                'size': test_file.stat().st_size
            })
        
        return sorted(results, key=lambda x: x['path'])
    
    # ========== 批次创建接口 ==========
    
    def create_batch(self, batch_name: str, batch_number: int, 
                   description: str, priority: int = 5,
                   file_names: Optional[List[str]] = None) -> int:
        """创建新批次"""
        batch = Batch(
            id=None,
            batch_name=batch_name,
            batch_number=batch_number,
            description=description,
            status=MigrationStatus.PENDING.value,
            priority=priority,
            total_files=len(file_names) if file_names else 0,
            migrated_files=0,
            start_date=None,
            planned_end_date=None,
            actual_end_date=None,
            blocker=None,
            notes=None,
            created_at=None,
            updated_at=None
        )
        
        batch_id = self.tracker.create_batch(batch)
        
        if file_names:
            for file_name in file_names:
                file = MigrationFile(
                    id=None,
                    batch_id=batch_id,
                    file_name=file_name,
                    module_name='UNKNOWN',
                    module_id=None,
                    status=MigrationStatus.PENDING.value,
                    priority=priority,
                    proto_file=None,
                    java_file=None,
                    has_test=False,
                    test_passed=False,
                    issues_count=0,
                    migration_notes=None,
                    start_date=None,
                    completion_date=None,
                    created_at=None,
                    updated_at=None
                )
                self.tracker.create_file(file)
        
        return batch_id
    
    # ========== 统计和报告接口 ==========
    
    def get_migration_summary(self) -> Dict:
        """获取迁移摘要"""
        progress = self.tracker.get_overall_progress()
        modules = self.tracker.get_module_progress()
        
        return {
            'overall_progress': progress,
            'modules': modules,
            'next_batch': self.get_next_batch_to_migrate(),
            'open_issues': len(self.get_open_issues())
        }
    
    def get_batch_summary(self, batch_name: str) -> Dict:
        """获取批次摘要"""
        batch = self.tracker.get_batch_by_name(batch_name)
        if not batch:
            return {}
        
        files = self.tracker.list_files(batch_id=batch.id)
        issues = self.tracker.list_issues(batch_id=batch.id)
        
        return {
            'batch': {
                'name': batch.batch_name,
                'number': batch.batch_number,
                'description': batch.description,
                'status': batch.status,
                'priority': batch.priority,
                'total_files': batch.total_files,
                'migrated_files': batch.migrated_files,
                'progress_percent': round(batch.migrated_files / batch.total_files * 100, 2) 
                                  if batch.total_files > 0 else 0
            },
            'files': [
                {
                    'name': f.file_name,
                    'status': f.status,
                    'has_test': f.has_test,
                    'test_passed': f.test_passed
                }
                for f in files
            ],
            'issues': [
                {
                    'id': i.id,
                    'title': i.title,
                    'severity': i.severity,
                    'status': i.status
                }
                for i in issues
            ]
        }
    
    def export_to_json(self, output_file: str = 'ai_migration_data.json'):
        """导出所有数据到 JSON"""
        batches = self.tracker.list_batches()
        files = self.tracker.list_files()
        issues = self.tracker.list_issues()
        
        data = {
            'batches': [
                {
                    'name': b.batch_name,
                    'number': b.batch_number,
                    'description': b.description,
                    'status': b.status,
                    'priority': b.priority,
                    'total_files': b.total_files,
                    'migrated_files': b.migrated_files
                }
                for b in batches
            ],
            'files': [
                {
                    'name': f.file_name,
                    'module': f.module_name,
                    'batch_id': f.batch_id,
                    'status': f.status,
                    'has_test': f.has_test,
                    'test_passed': f.test_passed
                }
                for f in files
            ],
            'issues': [
                {
                    'id': i.id,
                    'title': i.title,
                    'description': i.description,
                    'severity': i.severity,
                    'status': i.status
                }
                for i in issues
            ]
        }
        
        output_path = self.project_root / 'devdoc' / 'protobuf' / 'reports' / output_file
        with open(output_path, 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)
        
        return str(output_path)


def main():
    """测试 AI 助手"""
    assistant = AIMigrationAssistant()
    
    print("=" * 80)
    print("🤖 AI 迁移操作助手测试")
    print("=" * 80)
    
    # 测试快速查询
    print("\n📊 迁移摘要:")
    summary = assistant.get_migration_summary()
    print(f"  总体进度: {summary['overall_progress']['files']['progress_percent']}%")
    print(f"  下一个批次: {summary['next_batch']}")
    print(f"  待解决问题: {summary['open_issues']}")
    
    # 测试文件扫描
    print("\n📁 文件扫描:")
    proto_files = assistant.scan_proto_files()
    print(f"  Proto 文件: {len(proto_files)} 个")
    
    java_files = assistant.scan_java_files()
    print(f"  Java 文件: {len(java_files)} 个")
    
    test_files = assistant.scan_test_files()
    print(f"  测试文件: {len(test_files)} 个")
    
    # 测试导出
    print("\n💾 导出数据:")
    output_file = assistant.export_to_json()
    print(f"  已导出到: {output_file}")
    
    print("\n✅ 测试完成")


if __name__ == '__main__':
    main()
