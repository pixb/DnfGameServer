#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Protobuf迁移进度追踪系统
提供完整的增删查改接口，便于AI操作
"""

import sqlite3
import json
from datetime import datetime
from pathlib import Path
from typing import List, Dict, Optional, Tuple
from dataclasses import dataclass, asdict
from enum import Enum

class MigrationStatus(Enum):
    """迁移状态"""
    PENDING = "pending"          # 待迁移
    IN_PROGRESS = "in_progress"  # 进行中
    COMPLETED = "completed"      # 已完成
    FAILED = "failed"            # 失败
    BLOCKED = "blocked"          # 阻塞
    SKIPPED = "skipped"          # 跳过

class IssueStatus(Enum):
    """问题状态"""
    OPEN = "open"               # 待解决
    IN_PROGRESS = "in_progress" # 处理中
    RESOLVED = "resolved"       # 已解决
    CLOSED = "closed"           # 已关闭

@dataclass
class Batch:
    """批次信息"""
    id: Optional[int]
    batch_name: str             # 批次名称，如 "batch_01"
    batch_number: int           # 批次序号
    description: str            # 批次描述
    status: str                 # 状态
    priority: int               # 优先级(1-10)
    total_files: int            # 总文件数
    migrated_files: int         # 已迁移文件数
    start_date: Optional[str]   # 开始日期
    planned_end_date: Optional[str]  # 计划完成日期
    actual_end_date: Optional[str]   # 实际完成日期
    blocker: Optional[str]      # 阻塞原因
    notes: Optional[str]        # 备注
    created_at: Optional[str]   # 创建时间
    updated_at: Optional[str]   # 更新时间

@dataclass
class MigrationFile:
    """迁移文件信息"""
    id: Optional[int]
    batch_id: int               # 所属批次
    file_name: str              # 文件名
    module_name: str            # 模块名
    module_id: Optional[int]    # 模块ID
    status: str                 # 状态
    priority: int               # 优先级
    proto_file: Optional[str]   # proto文件路径
    java_file: Optional[str]    # Java文件路径
    has_test: bool              # 是否有测试
    test_passed: bool           # 测试是否通过
    issues_count: int           # 问题数量
    migration_notes: Optional[str]  # 迁移备注
    start_date: Optional[str]   # 开始日期
    completion_date: Optional[str]  # 完成日期
    created_at: Optional[str]
    updated_at: Optional[str]

@dataclass
class Issue:
    """问题信息"""
    id: Optional[int]
    batch_id: Optional[int]     # 所属批次
    file_id: Optional[int]      # 相关文件
    title: str                  # 问题标题
    description: str            # 问题描述
    solution: Optional[str]     # 解决方案
    status: str                 # 状态
    severity: str               # 严重程度 (critical/high/medium/low)
    tags: Optional[str]         # 标签，JSON格式
    created_at: Optional[str]
    resolved_at: Optional[str]

class MigrationTracker:
    """迁移进度追踪器"""
    
    def __init__(self, db_path: str = None):
        if db_path is None:
            # 默认使用 data 目录下的数据库
            db_path = str(Path(__file__).parent.parent.parent / 'data' / 'migration_progress.db')
        self.db_path = db_path
        self.conn = None
        self._connect()
        self._init_tables()
    
    def _connect(self):
        """连接数据库"""
        self.conn = sqlite3.connect(self.db_path)
        self.conn.row_factory = sqlite3.Row
    
    def _init_tables(self):
        """初始化表结构"""
        cursor = self.conn.cursor()
        
        # 批次表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS batches (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                batch_name TEXT UNIQUE NOT NULL,
                batch_number INTEGER UNIQUE NOT NULL,
                description TEXT,
                status TEXT DEFAULT 'pending',
                priority INTEGER DEFAULT 5,
                total_files INTEGER DEFAULT 0,
                migrated_files INTEGER DEFAULT 0,
                start_date TEXT,
                planned_end_date TEXT,
                actual_end_date TEXT,
                blocker TEXT,
                notes TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        # 迁移文件表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS migration_files (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                batch_id INTEGER NOT NULL,
                file_name TEXT NOT NULL,
                module_name TEXT NOT NULL,
                module_id INTEGER,
                status TEXT DEFAULT 'pending',
                priority INTEGER DEFAULT 5,
                proto_file TEXT,
                java_file TEXT,
                has_test BOOLEAN DEFAULT 0,
                test_passed BOOLEAN DEFAULT 0,
                issues_count INTEGER DEFAULT 0,
                migration_notes TEXT,
                start_date TEXT,
                completion_date TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (batch_id) REFERENCES batches(id),
                UNIQUE(batch_id, file_name)
            )
        ''')
        
        # 问题表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS issues (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                batch_id INTEGER,
                file_id INTEGER,
                title TEXT NOT NULL,
                description TEXT,
                solution TEXT,
                status TEXT DEFAULT 'open',
                severity TEXT DEFAULT 'medium',
                tags TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                resolved_at TIMESTAMP,
                FOREIGN KEY (batch_id) REFERENCES batches(id),
                FOREIGN KEY (file_id) REFERENCES migration_files(id)
            )
        ''')
        
        # 迁移日志表
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS migration_logs (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                batch_id INTEGER,
                file_id INTEGER,
                action TEXT NOT NULL,
                details TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (batch_id) REFERENCES batches(id),
                FOREIGN KEY (file_id) REFERENCES migration_files(id)
            )
        ''')
        
        # 创建索引
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_batches_status ON batches(status)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_files_batch ON migration_files(batch_id)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_files_status ON migration_files(status)')
        cursor.execute('CREATE INDEX IF NOT EXISTS idx_issues_status ON issues(status)')
        
        self.conn.commit()
    
    def close(self):
        """关闭数据库连接"""
        if self.conn:
            self.conn.close()
    
    def __enter__(self):
        return self
    
    def __exit__(self, exc_type, exc_val, exc_tb):
        self.close()

    # ========== 批次管理 ==========
    
    def create_batch(self, batch: Batch) -> int:
        """创建批次"""
        cursor = self.conn.cursor()
        cursor.execute('''
            INSERT INTO batches (batch_name, batch_number, description, status, priority,
                               total_files, migrated_files, start_date, planned_end_date,
                               actual_end_date, blocker, notes)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ''', (batch.batch_name, batch.batch_number, batch.description, batch.status,
              batch.priority, batch.total_files, batch.migrated_files, batch.start_date,
              batch.planned_end_date, batch.actual_end_date, batch.blocker, batch.notes))
        self.conn.commit()
        return cursor.lastrowid
    
    def get_batch(self, batch_id: int) -> Optional[Batch]:
        """获取批次详情"""
        cursor = self.conn.cursor()
        cursor.execute('SELECT * FROM batches WHERE id = ?', (batch_id,))
        row = cursor.fetchone()
        if row:
            return Batch(**dict(row))
        return None
    
    def get_batch_by_name(self, batch_name: str) -> Optional[Batch]:
        """通过名称获取批次"""
        cursor = self.conn.cursor()
        cursor.execute('SELECT * FROM batches WHERE batch_name = ?', (batch_name,))
        row = cursor.fetchone()
        if row:
            return Batch(**dict(row))
        return None
    
    def update_batch(self, batch_id: int, **kwargs) -> bool:
        """更新批次信息"""
        if not kwargs:
            return False
        
        # 自动更新 updated_at
        kwargs['updated_at'] = datetime.now().isoformat()
        
        cursor = self.conn.cursor()
        set_clause = ', '.join([f"{k} = ?" for k in kwargs.keys()])
        values = list(kwargs.values()) + [batch_id]
        
        cursor.execute(f'UPDATE batches SET {set_clause} WHERE id = ?', values)
        self.conn.commit()
        return cursor.rowcount > 0
    
    def list_batches(self, status: Optional[str] = None, 
                     order_by: str = "batch_number") -> List[Batch]:
        """列出批次"""
        cursor = self.conn.cursor()
        query = 'SELECT * FROM batches'
        params = []
        
        if status:
            query += ' WHERE status = ?'
            params.append(status)
        
        query += f' ORDER BY {order_by}'
        
        cursor.execute(query, params)
        return [Batch(**dict(row)) for row in cursor.fetchall()]
    
    def delete_batch(self, batch_id: int) -> bool:
        """删除批次（级联删除相关文件和问题）"""
        cursor = self.conn.cursor()
        cursor.execute('DELETE FROM batches WHERE id = ?', (batch_id,))
        self.conn.commit()
        return cursor.rowcount > 0

    # ========== 文件管理 ==========
    
    def create_file(self, file: MigrationFile) -> int:
        """创建迁移文件记录"""
        cursor = self.conn.cursor()
        cursor.execute('''
            INSERT INTO migration_files (batch_id, file_name, module_name, module_id,
                                       status, priority, proto_file, java_file, has_test,
                                       test_passed, issues_count, migration_notes,
                                       start_date, completion_date)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ''', (file.batch_id, file.file_name, file.module_name, file.module_id,
              file.status, file.priority, file.proto_file, file.java_file,
              file.has_test, file.test_passed, file.issues_count, file.migration_notes,
              file.start_date, file.completion_date))
        self.conn.commit()
        
        # 更新批次的文件计数
        self._update_batch_file_count(file.batch_id)
        
        return cursor.lastrowid
    
    def get_file(self, file_id: int) -> Optional[MigrationFile]:
        """获取文件详情"""
        cursor = self.conn.cursor()
        cursor.execute('SELECT * FROM migration_files WHERE id = ?', (file_id,))
        row = cursor.fetchone()
        if row:
            return MigrationFile(**dict(row))
        return None
    
    def update_file(self, file_id: int, **kwargs) -> bool:
        """更新文件信息"""
        if not kwargs:
            return False
        
        kwargs['updated_at'] = datetime.now().isoformat()
        
        # 获取当前文件信息以更新批次计数
        cursor = self.conn.cursor()
        cursor.execute('SELECT batch_id, status FROM migration_files WHERE id = ?', (file_id,))
        old_file = cursor.fetchone()
        
        set_clause = ', '.join([f"{k} = ?" for k in kwargs.keys()])
        values = list(kwargs.values()) + [file_id]
        
        cursor.execute(f'UPDATE migration_files SET {set_clause} WHERE id = ?', values)
        self.conn.commit()
        
        # 如果状态改变，更新批次的迁移计数
        if old_file and 'status' in kwargs:
            self._update_batch_migrated_count(old_file['batch_id'])
        
        return cursor.rowcount > 0
    
    def list_files(self, batch_id: Optional[int] = None,
                   status: Optional[str] = None,
                   module_name: Optional[str] = None) -> List[MigrationFile]:
        """列出文件"""
        cursor = self.conn.cursor()
        query = 'SELECT * FROM migration_files WHERE 1=1'
        params = []
        
        if batch_id:
            query += ' AND batch_id = ?'
            params.append(batch_id)
        if status:
            query += ' AND status = ?'
            params.append(status)
        if module_name:
            query += ' AND module_name = ?'
            params.append(module_name)
        
        query += ' ORDER BY priority DESC, file_name'
        
        cursor.execute(query, params)
        return [MigrationFile(**dict(row)) for row in cursor.fetchall()]
    
    def delete_file(self, file_id: int) -> bool:
        """删除文件"""
        cursor = self.conn.cursor()
        
        # 获取批次ID
        cursor.execute('SELECT batch_id FROM migration_files WHERE id = ?', (file_id,))
        row = cursor.fetchone()
        batch_id = row['batch_id'] if row else None
        
        cursor.execute('DELETE FROM migration_files WHERE id = ?', (file_id,))
        self.conn.commit()
        
        if batch_id:
            self._update_batch_file_count(batch_id)
            self._update_batch_migrated_count(batch_id)
        
        return cursor.rowcount > 0

    # ========== 问题管理 ==========
    
    def create_issue(self, issue: Issue) -> int:
        """创建问题"""
        cursor = self.conn.cursor()
        cursor.execute('''
            INSERT INTO issues (batch_id, file_id, title, description, solution,
                              status, severity, tags)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        ''', (issue.batch_id, issue.file_id, issue.title, issue.description,
              issue.solution, issue.status, issue.severity, issue.tags))
        self.conn.commit()
        
        # 更新文件的issues_count
        if issue.file_id:
            self._update_file_issues_count(issue.file_id)
        
        return cursor.lastrowid
    
    def get_issue(self, issue_id: int) -> Optional[Issue]:
        """获取问题详情"""
        cursor = self.conn.cursor()
        cursor.execute('SELECT * FROM issues WHERE id = ?', (issue_id,))
        row = cursor.fetchone()
        if row:
            return Issue(**dict(row))
        return None
    
    def update_issue(self, issue_id: int, **kwargs) -> bool:
        """更新问题"""
        if not kwargs:
            return False
        
        if 'status' in kwargs and kwargs['status'] == 'resolved':
            kwargs['resolved_at'] = datetime.now().isoformat()
        
        cursor = self.conn.cursor()
        set_clause = ', '.join([f"{k} = ?" for k in kwargs.keys()])
        values = list(kwargs.values()) + [issue_id]
        
        cursor.execute(f'UPDATE issues SET {set_clause} WHERE id = ?', values)
        self.conn.commit()
        
        # 如果更新了file_id，需要更新相关文件的issues_count
        if 'file_id' in kwargs or 'status' in kwargs:
            cursor.execute('SELECT file_id FROM issues WHERE id = ?', (issue_id,))
            row = cursor.fetchone()
            if row and row['file_id']:
                self._update_file_issues_count(row['file_id'])
        
        return cursor.rowcount > 0
    
    def list_issues(self, batch_id: Optional[int] = None,
                    file_id: Optional[int] = None,
                    status: Optional[str] = None,
                    severity: Optional[str] = None) -> List[Issue]:
        """列出问题"""
        cursor = self.conn.cursor()
        query = 'SELECT * FROM issues WHERE 1=1'
        params = []
        
        if batch_id:
            query += ' AND batch_id = ?'
            params.append(batch_id)
        if file_id:
            query += ' AND file_id = ?'
            params.append(file_id)
        if status:
            query += ' AND status = ?'
            params.append(status)
        if severity:
            query += ' AND severity = ?'
            params.append(severity)
        
        query += ' ORDER BY created_at DESC'
        
        cursor.execute(query, params)
        return [Issue(**dict(row)) for row in cursor.fetchall()]
    
    def delete_issue(self, issue_id: int) -> bool:
        """删除问题"""
        cursor = self.conn.cursor()
        
        # 获取文件ID
        cursor.execute('SELECT file_id FROM issues WHERE id = ?', (issue_id,))
        row = cursor.fetchone()
        file_id = row['file_id'] if row else None
        
        cursor.execute('DELETE FROM issues WHERE id = ?', (issue_id,))
        self.conn.commit()
        
        if file_id:
            self._update_file_issues_count(file_id)
        
        return cursor.rowcount > 0

    # ========== 统计和报告 ==========
    
    def get_overall_progress(self) -> Dict:
        """获取整体进度统计"""
        cursor = self.conn.cursor()
        
        # 总体统计
        cursor.execute('''
            SELECT 
                COUNT(*) as total_batches,
                SUM(CASE WHEN status = 'completed' THEN 1 ELSE 0 END) as completed_batches,
                SUM(total_files) as total_files,
                SUM(migrated_files) as migrated_files
            FROM batches
        ''')
        batch_stats = dict(cursor.fetchone())
        
        # 各状态文件统计
        cursor.execute('''
            SELECT status, COUNT(*) as count
            FROM migration_files
            GROUP BY status
        ''')
        file_status = {row['status']: row['count'] for row in cursor.fetchall()}
        
        # 问题统计
        cursor.execute('''
            SELECT 
                COUNT(*) as total_issues,
                SUM(CASE WHEN status = 'open' THEN 1 ELSE 0 END) as open_issues,
                SUM(CASE WHEN severity = 'critical' THEN 1 ELSE 0 END) as critical_issues
            FROM issues
        ''')
        issue_stats = dict(cursor.fetchone())
        
        # 计算进度百分比
        total_files = batch_stats.get('total_files', 0) or 0
        migrated_files = batch_stats.get('migrated_files', 0) or 0
        progress_percent = (migrated_files / total_files * 100) if total_files > 0 else 0
        
        return {
            'batches': {
                'total': batch_stats.get('total_batches', 0),
                'completed': batch_stats.get('completed_batches', 0)
            },
            'files': {
                'total': total_files,
                'migrated': migrated_files,
                'progress_percent': round(progress_percent, 2),
                'by_status': file_status
            },
            'issues': {
                'total': issue_stats.get('total_issues', 0),
                'open': issue_stats.get('open_issues', 0),
                'critical': issue_stats.get('critical_issues', 0)
            }
        }
    
    def get_batch_progress(self, batch_id: int) -> Dict:
        """获取批次进度详情"""
        cursor = self.conn.cursor()
        
        # 批次基本信息
        batch = self.get_batch(batch_id)
        if not batch:
            return {}
        
        # 文件状态分布
        cursor.execute('''
            SELECT status, COUNT(*) as count
            FROM migration_files
            WHERE batch_id = ?
            GROUP BY status
        ''', (batch_id,))
        file_status = {row['status']: row['count'] for row in cursor.fetchall()}
        
        # 问题列表
        open_issues = self.list_issues(batch_id=batch_id, status='open')
        
        return {
            'batch': asdict(batch),
            'files_by_status': file_status,
            'open_issues': [asdict(issue) for issue in open_issues],
            'progress_percent': round(batch.migrated_files / batch.total_files * 100, 2) 
                               if batch.total_files > 0 else 0
        }
    
    def get_module_progress(self) -> List[Dict]:
        """按模块统计进度"""
        cursor = self.conn.cursor()
        cursor.execute('''
            SELECT 
                module_name,
                COUNT(*) as total_files,
                SUM(CASE WHEN status = 'completed' THEN 1 ELSE 0 END) as completed_files,
                SUM(CASE WHEN status = 'in_progress' THEN 1 ELSE 0 END) as in_progress_files
            FROM migration_files
            GROUP BY module_name
            ORDER BY total_files DESC
        ''')
        
        results = []
        for row in cursor.fetchall():
            total = row['total_files']
            completed = row['completed_files']
            results.append({
                'module_name': row['module_name'],
                'total_files': total,
                'completed_files': completed,
                'in_progress_files': row['in_progress_files'],
                'progress_percent': round(completed / total * 100, 2) if total > 0 else 0
            })
        return results

    # ========== 内部方法 ==========
    
    def _update_batch_file_count(self, batch_id: int):
        """更新批次文件总数"""
        cursor = self.conn.cursor()
        cursor.execute('''
            UPDATE batches 
            SET total_files = (SELECT COUNT(*) FROM migration_files WHERE batch_id = ?)
            WHERE id = ?
        ''', (batch_id, batch_id))
        self.conn.commit()
    
    def _update_batch_migrated_count(self, batch_id: int):
        """更新批次已迁移文件数"""
        cursor = self.conn.cursor()
        cursor.execute('''
            UPDATE batches 
            SET migrated_files = (SELECT COUNT(*) FROM migration_files 
                                 WHERE batch_id = ? AND status = 'completed')
            WHERE id = ?
        ''', (batch_id, batch_id))
        self.conn.commit()
    
    def _update_file_issues_count(self, file_id: int):
        """更新文件的问题计数"""
        cursor = self.conn.cursor()
        cursor.execute('''
            UPDATE migration_files 
            SET issues_count = (SELECT COUNT(*) FROM issues 
                               WHERE file_id = ? AND status = 'open')
            WHERE id = ?
        ''', (file_id, file_id))
        self.conn.commit()
    
    def add_log(self, action: str, batch_id: Optional[int] = None, 
                file_id: Optional[int] = None, details: Optional[str] = None):
        """添加日志"""
        cursor = self.conn.cursor()
        cursor.execute('''
            INSERT INTO migration_logs (batch_id, file_id, action, details)
            VALUES (?, ?, ?, ?)
        ''', (batch_id, file_id, action, details))
        self.conn.commit()


# ========== AI友好的命令接口 ==========

def cmd_init_db():
    """初始化数据库"""
    with MigrationTracker() as tracker:
        print("✅ 数据库初始化完成")
        return True

def cmd_add_batch(name: str, number: int, description: str, 
                  priority: int = 5, planned_end: Optional[str] = None):
    """添加批次"""
    with MigrationTracker() as tracker:
        batch = Batch(
            id=None,
            batch_name=name,
            batch_number=number,
            description=description,
            status='pending',
            priority=priority,
            total_files=0,
            migrated_files=0,
            start_date=None,
            planned_end_date=planned_end,
            actual_end_date=None,
            blocker=None,
            notes=None,
            created_at=None,
            updated_at=None
        )
        batch_id = tracker.create_batch(batch)
        print(f"✅ 批次创建成功，ID: {batch_id}")
        return batch_id

def cmd_add_file(batch_name: str, file_name: str, module_name: str,
                 priority: int = 5, module_id: Optional[int] = None):
    """添加文件到批次"""
    with MigrationTracker() as tracker:
        batch = tracker.get_batch_by_name(batch_name)
        if not batch:
            print(f"❌ 批次 {batch_name} 不存在")
            return None
        
        file = MigrationFile(
            id=None,
            batch_id=batch.id,
            file_name=file_name,
            module_name=module_name,
            module_id=module_id,
            status='pending',
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
        file_id = tracker.create_file(file)
        print(f"✅ 文件添加成功，ID: {file_id}")
        return file_id

def cmd_start_file(file_id: int):
    """开始迁移文件"""
    with MigrationTracker() as tracker:
        tracker.update_file(file_id, status='in_progress', start_date=datetime.now().isoformat())
        tracker.add_log('start_migration', file_id=file_id)
        print(f"✅ 文件 {file_id} 迁移开始")

def cmd_complete_file(file_id: int, has_test: bool = True, test_passed: bool = True):
    """完成文件迁移"""
    with MigrationTracker() as tracker:
        tracker.update_file(file_id, 
                          status='completed', 
                          completion_date=datetime.now().isoformat(),
                          has_test=has_test,
                          test_passed=test_passed)
        tracker.add_log('complete_migration', file_id=file_id)
        print(f"✅ 文件 {file_id} 迁移完成")

def cmd_add_issue(batch_name: str, title: str, description: str,
                  severity: str = 'medium', file_name: Optional[str] = None):
    """添加问题"""
    with MigrationTracker() as tracker:
        batch = tracker.get_batch_by_name(batch_name)
        if not batch:
            print(f"❌ 批次 {batch_name} 不存在")
            return None
        
        file_id = None
        if file_name:
            files = tracker.list_files(batch_id=batch.id)
            for f in files:
                if f.file_name == file_name:
                    file_id = f.id
                    break
        
        issue = Issue(
            id=None,
            batch_id=batch.id,
            file_id=file_id,
            title=title,
            description=description,
            solution=None,
            status='open',
            severity=severity,
            tags=None,
            created_at=None,
            resolved_at=None
        )
        issue_id = tracker.create_issue(issue)
        print(f"✅ 问题添加成功，ID: {issue_id}")
        return issue_id

def cmd_resolve_issue(issue_id: int, solution: str):
    """解决问题"""
    with MigrationTracker() as tracker:
        tracker.update_issue(issue_id, status='resolved', solution=solution)
        tracker.add_log('resolve_issue', details=f'Issue {issue_id} resolved')
        print(f"✅ 问题 {issue_id} 已解决")

def cmd_show_progress():
    """显示整体进度"""
    with MigrationTracker() as tracker:
        progress = tracker.get_overall_progress()
        
        print("\n📊 迁移进度总览")
        print("=" * 50)
        print(f"批次: {progress['batches']['completed']}/{progress['batches']['total']} 完成")
        print(f"文件: {progress['files']['migrated']}/{progress['files']['total']} 完成")
        print(f"进度: {progress['files']['progress_percent']}%")
        print(f"问题: {progress['issues']['open']}/{progress['issues']['total']} 待解决")
        
        if progress['issues']['critical'] and progress['issues']['critical'] > 0:
            print(f"⚠️ 严重问题: {progress['issues']['critical']} 个")
        
        print("\n文件状态分布:")
        for status, count in progress['files']['by_status'].items():
            print(f"  - {status}: {count}")

def cmd_show_batch(batch_name: str):
    """显示批次详情"""
    with MigrationTracker() as tracker:
        batch = tracker.get_batch_by_name(batch_name)
        if not batch:
            print(f"❌ 批次 {batch_name} 不存在")
            return
        
        progress = tracker.get_batch_progress(batch.id)
        
        print(f"\n📦 批次: {batch_name}")
        print("=" * 50)
        print(f"描述: {batch.description}")
        print(f"状态: {batch.status}")
        print(f"进度: {progress['progress_percent']}% ({batch.migrated_files}/{batch.total_files})")
        print(f"优先级: {batch.priority}")
        
        if batch.blocker:
            print(f"⚠️ 阻塞: {batch.blocker}")
        
        print("\n文件状态:")
        for status, count in progress['files_by_status'].items():
            print(f"  - {status}: {count}")
        
        if progress['open_issues']:
            print("\n待解决问题:")
            for issue in progress['open_issues']:
                print(f"  - [{issue['severity']}] {issue['title']}")

def cmd_list_batches(status: Optional[str] = None):
    """列出批次"""
    with MigrationTracker() as tracker:
        batches = tracker.list_batches(status=status)
        
        print(f"\n📋 批次列表{' (' + status + ')' if status else ''}")
        print("=" * 80)
        print(f"{'ID':<5}{'名称':<15}{'状态':<12}{'进度':<10}{'优先级':<8}描述")
        print("-" * 80)
        
        for b in batches:
            progress = f"{b.migrated_files}/{b.total_files}"
            print(f"{b.id:<5}{b.batch_name:<15}{b.status:<12}{progress:<10}{b.priority:<8}{b.description[:30]}")

def cmd_list_files(batch_name: Optional[str] = None, status: Optional[str] = None):
    """列出文件"""
    with MigrationTracker() as tracker:
        batch_id = None
        if batch_name:
            batch = tracker.get_batch_by_name(batch_name)
            if batch:
                batch_id = batch.id
        
        files = tracker.list_files(batch_id=batch_id, status=status)
        
        print(f"\n📄 文件列表")
        print("=" * 80)
        print(f"{'ID':<5}{'批次':<12}{'文件名':<25}{'模块':<15}{'状态':<12}{'问题'}")
        print("-" * 80)
        
        for f in files:
            batch = tracker.get_batch(f.batch_id)
            batch_name_short = batch.batch_name if batch else "Unknown"
            print(f"{f.id:<5}{batch_name_short:<12}{f.file_name[:25]:<25}{f.module_name:<15}{f.status:<12}{f.issues_count}")

def cmd_list_issues(status: Optional[str] = 'open', severity: Optional[str] = None):
    """列出问题"""
    with MigrationTracker() as tracker:
        issues = tracker.list_issues(status=status, severity=severity)
        
        print(f"\n🔴 问题列表")
        print("=" * 80)
        print(f"{'ID':<5}{'严重':<8}{'状态':<10}{'标题':<40}批次")
        print("-" * 80)
        
        for issue in issues:
            batch = tracker.get_batch(issue.batch_id) if issue.batch_id else None
            batch_name = batch.batch_name if batch else "-"
            print(f"{issue.id:<5}{issue.severity:<8}{issue.status:<10}{issue.title[:40]:<40}{batch_name}")

def cmd_export_report(output_file: str = "migration_report.json"):
    """导出完整报告"""
    with MigrationTracker() as tracker:
        report = {
            'generated_at': datetime.now().isoformat(),
            'overall_progress': tracker.get_overall_progress(),
            'batches': [],
            'module_progress': tracker.get_module_progress()
        }
        
        batches = tracker.list_batches()
        for batch in batches:
            batch_progress = tracker.get_batch_progress(batch.id)
            report['batches'].append({
                'summary': asdict(batch),
                'progress': batch_progress
            })
        
        with open(output_file, 'w', encoding='utf-8') as f:
            json.dump(report, f, ensure_ascii=False, indent=2)
        
        print(f"✅ 报告已导出到: {output_file}")


# ========== 主程序 ==========

if __name__ == '__main__':
    import sys
    
    if len(sys.argv) < 2:
        print("""
Protobuf迁移进度追踪系统

用法: python migration_tracker.py <命令> [参数]

命令列表:
  init                    - 初始化数据库
  add_batch               - 添加批次
  add_file                - 添加文件
  start_file <id>         - 开始迁移文件
  complete_file <id>      - 完成文件迁移
  add_issue               - 添加问题
  resolve_issue <id>      - 解决问题
  progress                - 显示整体进度
  batch <name>            - 显示批次详情
  list_batches            - 列出批次
  list_files              - 列出文件
  list_issues             - 列出问题
  export [file]           - 导出报告

示例:
  python migration_tracker.py init
  python migration_tracker.py add_batch batch_11 11 "角色管理模块" 8
  python migration_tracker.py add_file batch_11 CharacterInfo.java CHARACTER 1001
  python migration_tracker.py start_file 1
  python migration_tracker.py complete_file 1
  python migration_tracker.py add_issue batch_11 "编译错误" "找不到符号" high CharacterInfo.java
  python migration_tracker.py progress
        """)
        sys.exit(0)
    
    command = sys.argv[1]
    
    if command == 'init':
        cmd_init_db()
    elif command == 'add_batch' and len(sys.argv) >= 5:
        cmd_add_batch(sys.argv[2], int(sys.argv[3]), sys.argv[4], 
                     int(sys.argv[5]) if len(sys.argv) > 5 else 5)
    elif command == 'add_file' and len(sys.argv) >= 5:
        cmd_add_file(sys.argv[2], sys.argv[3], sys.argv[4],
                    int(sys.argv[5]) if len(sys.argv) > 5 else 5,
                    int(sys.argv[6]) if len(sys.argv) > 6 else None)
    elif command == 'start_file' and len(sys.argv) >= 3:
        cmd_start_file(int(sys.argv[2]))
    elif command == 'complete_file' and len(sys.argv) >= 3:
        has_test = sys.argv[3].lower() == 'true' if len(sys.argv) > 3 else True
        test_passed = sys.argv[4].lower() == 'true' if len(sys.argv) > 4 else True
        cmd_complete_file(int(sys.argv[2]), has_test, test_passed)
    elif command == 'add_issue' and len(sys.argv) >= 5:
        cmd_add_issue(sys.argv[2], sys.argv[3], sys.argv[4],
                     sys.argv[5] if len(sys.argv) > 5 else 'medium',
                     sys.argv[6] if len(sys.argv) > 6 else None)
    elif command == 'resolve_issue' and len(sys.argv) >= 4:
        cmd_resolve_issue(int(sys.argv[2]), sys.argv[3])
    elif command == 'progress':
        cmd_show_progress()
    elif command == 'batch' and len(sys.argv) >= 3:
        cmd_show_batch(sys.argv[2])
    elif command == 'list_batches':
        cmd_list_batches(sys.argv[2] if len(sys.argv) > 2 else None)
    elif command == 'list_files':
        cmd_list_files(sys.argv[2] if len(sys.argv) > 2 else None,
                      sys.argv[3] if len(sys.argv) > 3 else None)
    elif command == 'list_issues':
        cmd_list_issues(sys.argv[2] if len(sys.argv) > 2 else 'open',
                       sys.argv[3] if len(sys.argv) > 3 else None)
    elif command == 'export':
        cmd_export_report(sys.argv[2] if len(sys.argv) > 2 else "migration_report.json")
    else:
        print(f"❌ 未知命令或参数不足: {command}")
        print("使用 'python migration_tracker.py' 查看帮助")
