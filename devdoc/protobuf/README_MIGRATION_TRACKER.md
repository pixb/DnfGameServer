# Protobuf 迁移进度追踪系统

基于 SQLite 的轻量级迁移进度管理系统，提供完整的增删查改接口，便于 AI 操作和查询。

## 系统特点

- **结构化存储**: 使用 SQLite 数据库存储，不会因为数据量大而影响 AI 上下文
- **完整 CRUD**: 提供批次、文件、问题的增删查改接口
- **AI 友好**: 所有操作都有清晰的命令封装，便于 AI 理解和执行
- **实时统计**: 自动生成进度报告和统计信息
- **可视化支持**: 可导出 JSON 报告用于生成图表

## 文件结构

```
devdoc/protobuf/
├── migration_tracker.py      # 核心追踪器（含完整 CRUD 接口）
├── init_migration_db.py      # 数据库初始化脚本
├── migration_progress.db     # SQLite 数据库（自动生成）
└── migration_report.json     # 导出报告（可选）
```

## 快速开始

### 1. 初始化数据库

```bash
cd devdoc/protobuf
python init_migration_db.py
```

这将创建数据库并导入你现有的 10 个批次的基础信息。

### 2. 查看整体进度

```bash
python migration_tracker.py progress
```

### 3. 列出所有批次

```bash
python migration_tracker.py list_batches
```

## 命令参考

### 批次管理

```bash
# 添加批次
python migration_tracker.py add_batch <名称> <序号> <描述> [优先级]

# 示例
python migration_tracker.py add_batch batch_11 11 "商城系统" 7

# 查看批次详情
python migration_tracker.py batch <名称>

# 示例
python migration_tracker.py batch batch_01
```

### 文件管理

```bash
# 添加文件到批次
python migration_tracker.py add_file <批次名> <文件名> <模块名> [优先级] [模块ID]

# 示例
python migration_tracker.py add_file batch_03 CharacterInfo.java CHARACTER 8 1001

# 开始迁移文件
python migration_tracker.py start_file <文件ID>

# 完成文件迁移
python migration_tracker.py complete_file <文件ID> [是否有测试] [测试是否通过]

# 示例
python migration_tracker.py start_file 1
python migration_tracker.py complete_file 1 true true
```

### 问题管理

```bash
# 添加问题
python migration_tracker.py add_issue <批次名> <标题> <描述> [严重程度] [文件名]

# 示例
python migration_tracker.py add_issue batch_03 "编译错误" "找不到符号" high CharacterInfo.java

# 解决问题
python migration_tracker.py resolve_issue <问题ID> <解决方案>

# 示例
python migration_tracker.py resolve_issue 1 "添加了缺失的依赖"
```

### 查询命令

```bash
# 查看整体进度
python migration_tracker.py progress

# 列出批次
python migration_tracker.py list_batches [状态]
python migration_tracker.py list_batches completed

# 列出文件
python migration_tracker.py list_files [批次名] [状态]
python migration_tracker.py list_files batch_03 in_progress

# 列出问题
python migration_tracker.py list_issues [状态] [严重程度]
python migration_tracker.py list_issues open critical
```

### 导出报告

```bash
# 导出完整报告
python migration_tracker.py export [文件名]

# 示例
python migration_tracker.py export report.json
```

## Python API 使用

你也可以直接在 Python 中调用 API：

```python
from migration_tracker import MigrationTracker, Batch, MigrationFile

# 使用上下文管理器
with MigrationTracker() as tracker:
    # 获取整体进度
    progress = tracker.get_overall_progress()
    print(f"总进度: {progress['files']['progress_percent']}%")
    
    # 获取批次详情
    batch_progress = tracker.get_batch_progress(1)
    print(f"批次01进度: {batch_progress['progress_percent']}%")
    
    # 按模块统计
    module_stats = tracker.get_module_progress()
    for module in module_stats:
        print(f"{module['module_name']}: {module['progress_percent']}%")
```

## 数据库结构

### batches (批次表)
- id: 主键
- batch_name: 批次名称（唯一）
- batch_number: 批次序号（唯一）
- description: 描述
- status: 状态 (pending/in_progress/completed/failed/blocked)
- priority: 优先级 (1-10)
- total_files: 总文件数
- migrated_files: 已迁移文件数
- start_date: 开始日期
- planned_end_date: 计划完成日期
- actual_end_date: 实际完成日期
- blocker: 阻塞原因
- notes: 备注
- created_at/updated_at: 创建/更新时间

### migration_files (文件表)
- id: 主键
- batch_id: 所属批次
- file_name: 文件名
- module_name: 模块名
- module_id: 模块ID
- status: 状态
- priority: 优先级
- proto_file: proto文件路径
- java_file: Java文件路径
- has_test: 是否有测试
- test_passed: 测试是否通过
- issues_count: 问题数量
- migration_notes: 迁移备注
- start_date/completion_date: 开始/完成日期

### issues (问题表)
- id: 主键
- batch_id: 所属批次（可选）
- file_id: 相关文件（可选）
- title: 标题
- description: 描述
- solution: 解决方案
- status: 状态
- severity: 严重程度 (critical/high/medium/low)
- tags: 标签 (JSON格式)
- created_at/resolved_at: 创建/解决时间

## AI 使用建议

当你需要查询或更新迁移进度时，可以直接使用命令行工具：

```
# AI 可以这样使用：
"帮我查看当前迁移进度"
→ 执行: python migration_tracker.py progress

"批次01有哪些待解决问题？"
→ 执行: python migration_tracker.py batch batch_01

"所有进行中的文件有哪些？"
→ 执行: python migration_tracker.py list_files None in_progress

"标记文件5为已完成"
→ 执行: python migration_tracker.py complete_file 5
```

## 数据迁移

如果你已经从 Markdown 文档中记录了迁移信息，可以通过 `init_migration_db.py` 导入，或者手动调用 API 批量导入。

## 扩展功能

你可以基于这个系统扩展更多功能：

1. **Web 看板**: 使用 Flask/FastAPI 提供 REST API，前端用 React/Vue 展示
2. **图表生成**: 使用 matplotlib/plotly 生成进度图表
3. **自动报告**: 定时生成并发送邮件报告
4. **集成 CI/CD**: 在迁移测试通过时自动更新状态

## 注意事项

1. 数据库文件 `migration_progress.db` 应该提交到 git，因为它是项目的进度记录
2. 定期使用 `export` 命令备份报告
3. 可以在 `migration_logs` 表中查看操作历史
