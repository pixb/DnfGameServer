# AI 操作指南 - Protobuf 迁移进度追踪

本文档帮助 AI 快速理解和操作迁移进度追踪系统。

## 系统概述

这是一个基于 SQLite 的迁移进度管理系统，用于追踪从 JProtobuf 到标准 Protobuf 的迁移过程。

**核心文件**:

- `migration_tracker.py` - 主要操作接口
- `migration_progress.db` - SQLite 数据库
- `init_migration_db.py` - 初始化脚本

## 常用操作速查

### 1. 查看整体进度

```python
import subprocess
result = subprocess.run(['python', 'migration_tracker.py', 'progress'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 2. 查看批次详情

```python
# 查看 batch_01
result = subprocess.run(['python', 'migration_tracker.py', 'batch', 'batch_01'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 3. 列出所有批次

```python
result = subprocess.run(['python', 'migration_tracker.py', 'list_batches'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 4. 列出文件

```python
# 列出所有文件
result = subprocess.run(['python', 'migration_tracker.py', 'list_files'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)

# 列出批次03的文件
result = subprocess.run(['python', 'migration_tracker.py', 'list_files', 'batch_03'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 5. 列出问题

```python
# 列出所有待解决问题
result = subprocess.run(['python', 'migration_tracker.py', 'list_issues'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 6. 添加新批次

```python
# 添加 batch_11
result = subprocess.run(['python', 'migration_tracker.py', 'add_batch', 
                        'batch_11', '11', '商城系统模块', '7'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 7. 添加文件到批次

```python
# 添加文件到 batch_03
result = subprocess.run(['python', 'migration_tracker.py', 'add_file', 
                        'batch_03', 'CharacterInfo.java', 'CHARACTER', '8', '1001'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 8. 更新文件状态

```python
# 开始迁移文件 ID 为 6 的文件
result = subprocess.run(['python', 'migration_tracker.py', 'start_file', '6'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)

# 标记文件 ID 为 6 的文件为已完成
result = subprocess.run(['python', 'migration_tracker.py', 'complete_file', '6'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 9. 添加问题

```python
# 添加问题到 batch_03
result = subprocess.run(['python', 'migration_tracker.py', 'add_issue', 
                        'batch_03', '编译错误', '找不到类定义', 'high', 'CharacterInfo.java'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

### 10. 解决问题

```python
# 解决问题 ID 为 2 的问题
result = subprocess.run(['python', 'migration_tracker.py', 'resolve_issue', 
                        '2', '添加缺失的import语句'], 
                       capture_output=True, text=True, cwd='devdoc/protobuf')
print(result.stdout)
```

## 直接 API 调用

如果需要更复杂的操作，可以直接使用 Python API：

```python
import sys
sys.path.insert(0, 'devdoc/protobuf')

from migration_tracker import MigrationTracker

with MigrationTracker('devdoc/protobuf/migration_progress.db') as tracker:
    # 获取统计
    progress = tracker.get_overall_progress()
    print(f"总进度: {progress['files']['progress_percent']}%")
    
    # 按模块统计
    module_stats = tracker.get_module_progress()
    for m in module_stats:
        print(f"{m['module_name']}: {m['completed_files']}/{m['total_files']}")
```

## 数据结构说明

### Batch (批次)

```python
{
    'id': 1,
    'batch_name': 'batch_01',
    'batch_number': 1,
    'description': '登录认证模块',
    'status': 'completed',
    'priority': 10,
    'total_files': 5,
    'migrated_files': 5
}
```

### MigrationFile (文件)

```python
{
    'id': 1,
    'batch_id': 1,
    'file_name': 'LoginRequest.java',
    'module_name': 'LOGIN',
    'module_id': 10000,
    'status': 'completed',
    'has_test': True,
    'test_passed': True,
    'issues_count': 0
}
```

### Issue (问题)

```python
{
    'id': 1,
    'batch_id': 2,
    'title': 'SESSION_LOGOUT使用场景待确认',
    'description': '找不到Module ID...',
    'status': 'open',
    'severity': 'medium'
}
```

## 状态常量

```python
# 批次/文件状态
status = ['pending', 'in_progress', 'completed', 'failed', 'blocked', 'skipped']

# 问题状态
issue_status = ['open', 'in_progress', 'resolved', 'closed']

# 严重程度
severity = ['critical', 'high', 'medium', 'low']
```

## 最佳实践

1. **定期查看进度**: 在每次对话开始时执行 `progress` 命令
2. **及时更新状态**: 文件开始迁移时调用 `start_file`，完成时调用 `complete_file`
3. **记录问题**: 遇到问题时立即用 `add_issue` 记录
4. **解决问题**: 问题解决后立即用 `resolve_issue` 标记
5. **导出报告**: 定期执行 `export` 生成备份

## 故障排除

**问题**: 命令报错找不到数据库
**解决**: 先运行 `python init_migration_db.py` 初始化

**问题**: 文件 ID 不存在
**解决**: 先用 `list_files` 查看有效的文件 ID

**问题**: 批次不存在
**解决**: 先用 `list_batches` 查看有效的批次名称
