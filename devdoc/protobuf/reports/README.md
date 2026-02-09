# Protobuf迁移追踪系统

## 📖 系统概述

用于追踪和管理Protobuf迁移进度的系统，支持AI自动化操作和进度统计。

## 🚀 快速使用

### 查看状态
```bash
cd devdoc/protobuf/reports/scripts
python3 main.py status
```

### 生成报告
```bash
# Markdown 报告
python3 main.py report markdown

# HTML 报告
python3 main.py report html

# 综合报告（Markdown + JSON）
python3 main.py report comprehensive
```

### 查询消息映射
```bash
python3 main.py query --old-name PT_SKILL
```

### 分析JProtobuf
```bash
python3 main.py analyze
```

### 添加批次到数据库
```bash
python3 main.py add-batches
```

### 修正批次状态
```bash
python3 main.py fix
```

## 📁 目录说明

- **scripts/** - 工具脚本和主程序
  - `main.py` - 主入口程序
  - `migration_progress.db` - SQLite 数据库
  - `core/` - 核心功能模块
  - `batch_management/` - 批次管理工具
  - `reports/` - 报告生成工具
  - `query/` - 查询工具
  - `analyze/` - 分析工具

## 📖 详细文档

- **USAGE_GUIDE.md** - 完整使用指南
- **scripts/migration_report.md** - Markdown 格式迁移报告
- **scripts/migration_report.html** - HTML 格式迁移报告
- **scripts/comprehensive_migration_report.md** - 综合迁移报告
- **scripts/migration_report.json** - JSON 格式结构化数据

## 📊 当前进度

- 批次: 25/25 完成
- 文件: 124/124 完成
- 进度: 100%
- 测试覆盖率: 100%

## 🎯 系统功能

### 1. 迁移进度追踪
- 批次管理（创建、更新、查询）
- 文件追踪（状态、测试、问题）
- 问题记录和跟踪
- 迁移日志记录

### 2. 报告生成
- **Markdown 报告**: 简洁的文本格式报告
- **HTML 报告**: 可视化的网页报告
- **综合报告**: 包含详细统计、时间线、测试覆盖率等
- **JSON 报告**: 结构化数据，便于程序处理

### 3. 消息映射查询
- 按旧名称查询
- 按新名称查询
- 按模块ID查询
- 按批次查询

### 4. JProtobuf 分析
- 统计使用 JProtobuf 的文件数量
- 按模块分布分析
- 总体使用情况统计

## 🛠️ 维护建议

1. **定期查看进度**: `python3 main.py status`
2. **迁移完成后更新**: 及时更新批次和文件状态
3. **定期生成报告**: 使用 `python3 main.py report comprehensive` 生成综合报告
4. **记录问题**: 遇到问题及时记录到数据库
5. **备份数据库**: 定期备份 `migration_progress.db` 文件
6. **使用版本控制**: 将报告文件纳入版本控制

## 📊 数据库结构

系统使用 SQLite 数据库存储迁移数据，包含以下表：
- `batches` - 批次信息
- `migration_files` - 迁移文件信息
- `issues` - 问题记录
- `migration_logs` - 迁移日志

详细结构说明请参考 [USAGE_GUIDE.md](USAGE_GUIDE.md)

## 🔧 扩展功能

### 添加新批次
使用 `add_batches_22_33_simple.py` 作为模板，创建新的批次添加脚本。

### 自定义报告
参考 `enhanced_report_generator.py` 创建自定义报告生成器。

### 添加查询功能
在 `query/query_mappings.py` 中添加新的查询方法。
