# Protobuf 迁移追踪系统 - 使用指南

## 📖 系统概述

Protobuf 迁移追踪系统是一个完整的迁移进度管理和报告工具，用于追踪和管理 JProtobuf 到标准 Protobuf 的迁移进度。系统提供数据库存储、命令行接口、多种报告格式等功能。

## 🚀 快速开始

### 安装依赖

```bash
cd /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts
pip install -r requirements.txt  # 如果有 requirements.txt
```

### 基本命令

```bash
# 查看当前迁移状态
python3 main.py status

# 生成 Markdown 报告
python3 main.py report markdown

# 生成 HTML 报告
python3 main.py report html

# 生成综合报告（Markdown + JSON）
python3 main.py report comprehensive

# 查询消息映射
python3 main.py query --old-name PT_SKILL

# 分析 JProtobuf 使用情况
python3 main.py analyze

# 修正所有批次状态
python3 main.py fix

# 添加批次22-33到数据库
python3 main.py add-batches
```

## 📊 功能模块

### 1. 迁移进度追踪

系统使用 SQLite 数据库存储迁移进度，包括：
- 批次信息（批次名称、描述、状态、优先级等）
- 文件信息（文件名、模块名、状态、测试情况等）
- 问题记录（问题标题、描述、解决方案、状态等）
- 迁移日志（操作记录、时间戳等）

### 2. 报告生成

系统支持多种报告格式：

#### Markdown 报告
```bash
python3 main.py report markdown
```
生成 `migration_report.md`，包含：
- 总体进度
- 批次详情
- 模块进度
- 待解决问题

#### HTML 报告
```bash
python3 main.py report html
```
生成 `migration_report.html`，提供更好的可视化效果。

#### 综合报告
```bash
python3 main.py report comprehensive
```
生成两个文件：
- `comprehensive_migration_report.md`：详细的综合报告
- `migration_report.json`：JSON 格式的结构化数据

综合报告包含：
- 执行摘要
- 文件状态分布
- 测试覆盖率
- 批次详情（已完成/进行中/待开始）
- 模块进度
- 问题汇总（待解决/已解决）
- 迁移时间线
- 统计信息（迁移速度、模块分布等）

### 3. 消息映射查询

```bash
# 按旧名称查询
python3 main.py query --old-name PT_SKILL

# 按新名称查询
python3 main.py query --new-name Skill

# 按模块ID查询
python3 main.py query --module-id 1001

# 按批次查询
python3 main.py query --batch-name batch_22

# 列出所有映射
python3 main.py query
```

### 4. JProtobuf 分析

```bash
python3 main.py analyze
```

分析项目中 JProtobuf 的使用情况，统计：
- 使用 JProtobuf 的文件数量
- 按模块分布情况
- 总体使用情况

### 5. 批次管理

#### 添加批次
```bash
python3 main.py add-batches
```
将批次22-33添加到数据库中。

#### 修正批次状态
```bash
python3 main.py fix
```
自动修正所有批次的状态，确保数据库与实际迁移进度一致。

## 📁 目录结构

```
reports/scripts/
├── main.py                          # 主入口
├── migration_progress.db            # SQLite 数据库
├── migration_report.md              # Markdown 报告
├── migration_report.html            # HTML 报告
├── comprehensive_migration_report.md # 综合报告
├── migration_report.json            # JSON 报告
├── core/
│   └── migration_tracker.py         # 迁移追踪器核心类
├── batch_management/
│   ├── add_batches_22_33_simple.py  # 添加批次22-33
│   ├── fix_batch_status.py          # 修正批次状态
│   ├── fix_batch_10.py              # 修正批次10
│   ├── update_all_batches.py        # 更新所有批次
│   └── update_batches_10_13.py      # 更新批次10-13
├── reports/
│   ├── generate_report.py           # 生成基础报告
│   ├── enhanced_report_generator.py # 生成增强报告
│   └── generate_mapping_reports.py # 生成映射报告
├── query/
│   └── query_mappings.py            # 查询消息映射
└── analyze/
    └── analyze_jprotobuf_files.py   # 分析 JProtobuf 文件
```

## 🔧 数据库结构

### batches 表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| batch_name | TEXT | 批次名称（唯一） |
| batch_number | INTEGER | 批次序号（唯一） |
| description | TEXT | 批次描述 |
| status | TEXT | 状态 |
| priority | INTEGER | 优先级（1-10） |
| total_files | INTEGER | 总文件数 |
| migrated_files | INTEGER | 已迁移文件数 |
| start_date | TEXT | 开始日期 |
| planned_end_date | TEXT | 计划完成日期 |
| actual_end_date | TEXT | 实际完成日期 |
| blocker | TEXT | 阻塞原因 |
| notes | TEXT | 备注 |
| created_at | TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | 更新时间 |

### migration_files 表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| batch_id | INTEGER | 所属批次ID |
| file_name | TEXT | 文件名 |
| module_name | TEXT | 模块名 |
| module_id | INTEGER | 模块ID |
| status | TEXT | 状态 |
| priority | INTEGER | 优先级 |
| proto_file | TEXT | proto文件路径 |
| java_file | TEXT | Java文件路径 |
| has_test | BOOLEAN | 是否有测试 |
| test_passed | BOOLEAN | 测试是否通过 |
| issues_count | INTEGER | 问题数量 |
| migration_notes | TEXT | 迁移备注 |
| start_date | TEXT | 开始日期 |
| completion_date | TEXT | 完成日期 |
| created_at | TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | 更新时间 |

### issues 表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| batch_id | INTEGER | 所属批次ID |
| file_id | INTEGER | 相关文件ID |
| title | TEXT | 问题标题 |
| description | TEXT | 问题描述 |
| solution | TEXT | 解决方案 |
| status | TEXT | 状态 |
| severity | TEXT | 严重程度 |
| tags | TEXT | 标签（JSON格式） |
| created_at | TIMESTAMP | 创建时间 |
| resolved_at | TIMESTAMP | 解决时间 |

### migration_logs 表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| batch_id | INTEGER | 所属批次ID |
| file_id | INTEGER | 相关文件ID |
| action | TEXT | 操作类型 |
| details | TEXT | 操作详情 |
| created_at | TIMESTAMP | 创建时间 |

## 📊 状态说明

### 批次状态
- `pending`: 待迁移
- `in_progress`: 进行中
- `completed`: 已完成
- `failed`: 失败
- `blocked`: 阻塞
- `skipped`: 跳过

### 问题严重程度
- `critical`: 严重
- `high`: 高
- `medium`: 中等
- `low`: 低

### 问题状态
- `open`: 待解决
- `in_progress`: 处理中
- `resolved`: 已解决
- `closed`: 已关闭

## 🎯 使用场景

### 场景1：开始新的迁移批次

1. 在数据库中创建新批次记录
2. 添加需要迁移的文件
3. 开始迁移工作
4. 更新文件状态和测试结果
5. 完成后更新批次状态

### 场景2：生成迁移报告

1. 运行 `python3 main.py status` 查看当前进度
2. 运行 `python3 main.py report comprehensive` 生成综合报告
3. 查看生成的报告文件
4. 根据报告调整迁移计划

### 场景3：查询消息映射

1. 运行 `python3 main.py query --old-name <旧名称>` 查询映射
2. 查看查询结果
3. 根据映射信息更新代码

### 场景4：分析迁移进度

1. 运行 `python3 main.py analyze` 分析 JProtobuf 使用情况
2. 查看分析结果
3. 根据分析结果规划后续迁移批次

## 🔍 故障排查

### 问题1：数据库锁定
**症状**: 提示 "database is locked"
**解决**: 确保没有其他进程正在访问数据库，关闭所有打开的连接

### 问题2：批次状态不一致
**症状**: 显示的进度与实际不符
**解决**: 运行 `python3 main.py fix` 修正批次状态

### 问题3：报告生成失败
**症状**: 提示 "No module named 'xxx'"
**解决**: 安装缺失的依赖包

## 📝 最佳实践

1. **定期更新进度**: 每次迁移完成后及时更新数据库
2. **生成报告**: 定期生成报告以跟踪进度
3. **记录问题**: 遇到问题时及时记录到数据库
4. **备份数据库**: 定期备份 `migration_progress.db` 文件
5. **使用版本控制**: 将报告文件纳入版本控制

## 🤝 贡献

如果您发现任何问题或有改进建议，请：
1. 记录问题到数据库
2. 生成报告分析问题
3. 提出改进建议

## 📄 许可

本系统为 DnfGameServer 项目的一部分，遵循项目许可证。
