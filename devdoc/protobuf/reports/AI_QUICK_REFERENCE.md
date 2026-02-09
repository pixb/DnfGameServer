# AI 迁移助手 - 快速参考卡片

## 🚀 快速开始

### 导入助手
```python
from ai_assistant import AIMigrationAssistant

assistant = AIMigrationAssistant()
```

### 命令行快速访问
```bash
cd /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts

# 查看状态
python3 main.py status

# AI 助手摘要
python3 main.py ai-summary

# AI 助手文件扫描
python3 main.py ai-scan

# AI 助手数据导出
python3 main.py ai-export
```

## 📋 查询接口

### 获取下一个待迁移批次
```python
next_batch = assistant.get_next_batch_to_migrate()
# 返回: {'batch_name': 'batch_34', 'batch_number': 34, 'description': '...', 'priority': 5, 'total_files': 10}
```

### 获取批次的所有文件
```python
files = assistant.get_batch_files('batch_22')
# 返回: [{'file_name': 'STREAM_DATA', 'module_name': 'STREAM_DATA', 'status': 'completed', ...}, ...]
```

### 获取文件详细信息
```python
file_info = assistant.get_file_info('PT_SKILL')
# 返回: {'file_name': 'PT_SKILL', 'module_name': 'SKILL', 'batch_name': 'batch_29', 'status': 'completed', ...}
```

### 按模块搜索文件
```python
files = assistant.search_files_by_module('SKILL')
# 返回: [{'file_name': 'PT_SKILL', 'status': 'completed', ...}, ...]
```

### 获取待解决问题
```python
issues = assistant.get_open_issues()
# 返回: [{'id': 1, 'title': '命名冲突', 'description': '...', 'severity': 'high', ...}, ...]

issues = assistant.get_open_issues('batch_32')
# 返回: 指定批次的待解决问题
```

### 获取迁移摘要
```python
summary = assistant.get_migration_summary()
# 返回: {'overall_progress': {...}, 'modules': [...], 'next_batch': {...}, 'open_issues': 1}
```

### 获取批次摘要
```python
batch_summary = assistant.get_batch_summary('batch_22')
# 返回: {'batch': {...}, 'files': [...], 'issues': [...]}
```

## 📝 更新接口

### 开始批次迁移
```python
assistant.start_batch_migration('batch_34')
# 返回: True/False
```

### 完成批次迁移
```python
assistant.complete_batch_migration('batch_34')
# 返回: True/False
```

### 更新文件状态
```python
assistant.update_file_status(
    file_name='PT_SKILL',
    status='completed',
    proto_file='proto/dnf/v1/skill.proto',
    java_file='src/main/java/com/dnfm/mina/protobuf/PT_SKILL.java',
    has_test=True,
    test_passed=True
)
# 返回: True/False
```

### 添加问题
```python
issue_id = assistant.add_issue(
    title='命名冲突',
    description='PT_HIDDEN_CHATTING 与 chat.proto 中的 HiddenChatting 冲突',
    batch_name='batch_32',
    file_name='PT_HIDDEN_CHATTING',
    severity='high'
)
# 返回: 问题 ID
```

### 解决问题
```python
assistant.resolve_issue(
    issue_id=1,
    solution='重命名为 HiddenChattingInfo'
)
# 返回: True/False
```

## 🔍 扫描接口

### 扫描 proto 文件
```python
proto_files = assistant.scan_proto_files()
# 返回: [{'path': 'proto/dnf/v1/skill.proto', 'name': 'skill', 'size': 1024}, ...]
```

### 扫描 Java 文件
```python
java_files = assistant.scan_java_files()
# 返回: [{'path': 'src/main/java/...', 'name': '...', 'size': 2048}, ...]
```

### 扫描测试文件
```python
test_files = assistant.scan_test_files()
# 返回: [{'path': 'dnf-go-client/test/...', 'name': '...', 'size': 512}, ...]
```

## 📦 创建接口

### 创建新批次
```python
batch_id = assistant.create_batch(
    batch_name='batch_34',
    batch_number=34,
    description='新的迁移批次',
    priority=5,
    file_names=['PT_SKILL', 'PT_DROP_OBJECT_GOLD']
)
# 返回: 批次 ID
```

## 💾 导出接口

### 导出数据到 JSON
```python
output_file = assistant.export_to_json('my_migration_data.json')
# 返回: '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/my_migration_data.json'
```

## 🎯 典型工作流

### 工作流 1: 开始新的迁移批次
```python
# 1. 获取下一个待迁移批次
next_batch = assistant.get_next_batch_to_migrate()
print(f"下一个批次: {next_batch['batch_name']}")

# 2. 开始迁移
assistant.start_batch_migration(next_batch['batch_name'])

# 3. 获取文件列表
files = assistant.get_batch_files(next_batch['batch_name'])
print(f"需要迁移 {len(files)} 个文件")

# 4. 迁移每个文件
for file in files:
    # ... 执行迁移逻辑 ...
    
    # 更新文件状态
    assistant.update_file_status(
        file['file_name'], 
        'completed',
        proto_file=f'proto/dnf/v1/{file["file_name"].lower()}.proto',
        java_file=f'src/main/java/com/dnfm/mina/protobuf/{file["file_name"]}.java',
        has_test=True,
        test_passed=True
    )

# 5. 完成批次
assistant.complete_batch_migration(next_batch['batch_name'])
```

### 工作流 2: 查询迁移进度
```python
# 1. 获取整体进度
summary = assistant.get_migration_summary()
print(f"总体进度: {summary['overall_progress']['files']['progress_percent']}%")

# 2. 获取模块进度
for module in summary['modules']:
    print(f"{module['module_name']}: {module['progress_percent']}%")

# 3. 获取待解决问题
issues = assistant.get_open_issues()
for issue in issues:
    print(f"问题 {issue['id']}: {issue['title']}")
```

### 工作流 3: 记录和解决问题
```python
# 1. 添加问题
issue_id = assistant.add_issue(
    title='命名冲突',
    description='PT_HIDDEN_CHATTING 与 chat.proto 中的 HiddenChatting 冲突',
    batch_name='batch_32',
    file_name='PT_HIDDEN_CHATTING',
    severity='high'
)

# 2. 查询问题
issues = assistant.get_open_issues('batch_32')
for issue in issues:
    print(f"问题 {issue['id']}: {issue['title']}")

# 3. 解决问题
assistant.resolve_issue(
    issue_id,
    solution='重命名为 HiddenChattingInfo'
)
```

### 工作流 4: 检查文件状态
```python
# 1. 检查特定文件
file_info = assistant.get_file_info('PT_SKILL')
if file_info:
    print(f"文件状态: {file_info['status']}")
    print(f"是否有测试: {file_info['has_test']}")
    print(f"测试是否通过: {file_info['test_passed']}")
else:
    print("文件不存在")

# 2. 按模块搜索
files = assistant.search_files_by_module('SKILL')
for file in files:
    print(f"{file['file_name']}: {file['status']}")

# 3. 扫描文件系统
proto_files = assistant.scan_proto_files()
print(f"找到 {len(proto_files)} 个 proto 文件")
```

## 📊 状态常量

### 批次状态
```python
from core.migration_tracker import MigrationStatus

MigrationStatus.PENDING.value      # 'pending' - 待迁移
MigrationStatus.IN_PROGRESS.value  # 'in_progress' - 进行中
MigrationStatus.COMPLETED.value    # 'completed' - 已完成
MigrationStatus.FAILED.value      # 'failed' - 失败
MigrationStatus.BLOCKED.value     # 'blocked' - 阻塞
MigrationStatus.SKIPPED.value     # 'skipped' - 跳过
```

### 问题严重程度
```python
'critical'  # 严重
'high'      # 高
'medium'    # 中等
'low'       # 低
```

### 问题状态
```python
from core.migration_tracker import IssueStatus

IssueStatus.OPEN.value        # 'open' - 待解决
IssueStatus.IN_PROGRESS.value # 'in_progress' - 处理中
IssueStatus.RESOLVED.value   # 'resolved' - 已解决
IssueStatus.CLOSED.value     # 'closed' - 已关闭
```

## 🔧 故障排查

### 问题: 找不到批次
```python
batch = assistant.tracker.get_batch_by_name('batch_34')
if not batch:
    print("批次不存在")
```

### 问题: 找不到文件
```python
file_info = assistant.get_file_info('PT_SKILL')
if not file_info:
    print("文件不存在")
```

### 问题: 更新失败
```python
result = assistant.update_file_status('PT_SKILL', 'completed')
if not result:
    print("更新失败")
```

## 📚 相关文档

- [使用指南](USAGE_GUIDE.md) - 完整的使用说明
- [AI 能力分析](AI_CAPABILITY_ANALYSIS.md) - AI 使用能力详细分析
- [最终评估报告](FINAL_EVALUATION_REPORT.md) - 系统能力评估
- [优化总结](OPTIMIZATION_SUMMARY.md) - 优化内容总结

## 💡 提示

1. **使用 AI 助手**: AI 助手提供了简化的接口，推荐使用
2. **批量操作**: 尽量使用批量操作，减少数据库访问次数
3. **错误处理**: 始终检查返回值，处理可能的错误
4. **导出数据**: 定期导出数据，备份重要信息
5. **使用命令行**: 快速查看状态时，使用命令行接口更方便

## 🎯 快速命令参考

| 命令 | 说明 |
|------|------|
| `python3 main.py status` | 查看当前状态 |
| `python3 main.py ai-summary` | AI 助手摘要 |
| `python3 main.py ai-scan` | AI 助手文件扫描 |
| `python3 main.py ai-export` | AI 助手数据导出 |
| `python3 main.py report markdown` | 生成 Markdown 报告 |
| `python3 main.py report html` | 生成 HTML 报告 |
| `python3 main.py report comprehensive` | 生成综合报告 |
| `python3 main.py query --old-name PT_SKILL` | 查询消息映射 |
| `python3 main.py analyze` | 分析 JProtobuf 使用情况 |
