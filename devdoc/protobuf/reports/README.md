# Protobuf迁移追踪系统

## 📖 系统概述

用于追踪和管理Protobuf迁移进度的系统，支持AI自动化操作和进度统计。

## 🚀 快速使用

### 查看状态
```bash
cd devdoc/protobuf/reports/scripts
python3 main.py status
```

### 修正批次
```bash
python3 main.py fix
```

### 生成报告
```bash
python3 main.py report html
```

### 分析JProtobuf
```bash
python3 main.py analyze
```

## 📁 目录说明

- **databases/** - 数据库文件
- **docs/** - 详细文档和使用指南
- **scripts/** - 工具脚本（详见scripts/README.md）

## 📖 详细文档

所有详细使用说明请查看 `docs/` 目录：
- `README_MIGRATION_TRACKER.md` - 系统详细说明
- `AI_OPERATION_GUIDE.md` - AI操作指南
- `MESSAGE_MAPPINGS_GUIDE.md` - 消息映射指南
- `MIGRATION_PROGRESS_12_BATCHES_REPORT.md` - 迁移进度报告
- `migration_report.html` - HTML格式迁移报告

## 📊 当前进度

- 批次: 13/13 完成
- 文件: 92/92 完成
- 进度: 100%

## 🛠️ 维护建议

1. 定期查看进度 `python3 main.py status`
2. 迁移完成后修正状态 `python3 main.py fix`
3. 定期生成报告监控进度
4. 定期备份数据库
