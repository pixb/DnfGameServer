# Reports 目录整理方案

## 📋 当前问题分析

### 1. 文件冗余
- **数据库重复**: `databases/migration_progress.db` 和 `scripts/migration_progress.db`
- **报告重复**: `docs/migration_report.html` 和 `scripts/migration_report.html`
- **文档分散**: 根目录和 `docs/` 目录都有文档

### 2. 文档重复
- `AI_OPERATION_GUIDE.md` (docs/) 和 `USAGE_GUIDE.md` (根目录) 内容重复
- `README_MIGRATION_TRACKER.md` (docs/) 和 `README.md` (根目录) 内容重复
- 多个批次映射报告可以合并

### 3. 目录结构不合理
- `docs/` 目录包含历史报告，应该归档
- 根目录文档过多，应该分类
- `scripts/` 目录包含生成的报告文件，应该分离

## 🎯 整理方案

### 新的目录结构
```
reports/
├── README.md                           # 系统概述（保留）
├── USAGE_GUIDE.md                      # 使用指南（保留）
├── AI_QUICK_REFERENCE.md              # AI 快速参考（保留）
├── docs/                              # 详细文档
│   ├── AI_CAPABILITY_ANALYSIS.md       # AI 能力分析（从根目录移入）
│   ├── FINAL_EVALUATION_REPORT.md     # 最终评估报告（从根目录移入）
│   └── OPTIMIZATION_SUMMARY.md       # 优化总结（从根目录移入）
├── archive/                           # 历史报告归档
│   ├── batch_mapping_reports/          # 批次映射报告
│   ├── migration_progress_reports/     # 迁移进度报告
│   └── other_reports/                # 其他历史报告
├── outputs/                           # 生成的报告输出
│   ├── migration_report.md            # Markdown 报告
│   ├── migration_report.html          # HTML 报告
│   ├── comprehensive_migration_report.md  # 综合报告
│   └── migration_report.json         # JSON 报告
├── scripts/                           # 工具脚本
│   ├── main.py                       # 主入口
│   ├── ai_assistant.py               # AI 助手
│   ├── core/                        # 核心功能
│   ├── batch_management/             # 批次管理
│   ├── reports/                     # 报告生成
│   ├── query/                      # 查询工具
│   ├── analyze/                    # 分析工具
│   ├── extract/                    # 提取工具
│   └── database/                   # 数据库工具
└── data/                             # 数据文件
    └── migration_progress.db        # 数据库（统一位置）
```

## 🗑️ 需要删除的文件

### 重复文件
- `scripts/migration_progress.db` (保留 `databases/migration_progress.db`)
- `scripts/migration_report.html` (保留 `outputs/migration_report.html`)
- `scripts/migration_report.md` (保留 `outputs/migration_report.md`)
- `scripts/migration_report.json` (保留 `outputs/migration_report.json`)
- `scripts/comprehensive_migration_report.md` (保留 `outputs/comprehensive_migration_report.md`)

### 过时文档
- `docs/AI_OPERATION_GUIDE.md` (被 `USAGE_GUIDE.md` 替代)
- `docs/README_MIGRATION_TRACKER.md` (被 `README.md` 替代)
- `docs/MIGRATION_PROGRESS_12_BATCHES_REPORT.md` (过时的进度报告)
- `docs/MIGRATION_FINAL_13_BATCHES.md` (过时的批次报告)

### 冗余脚本
- `scripts/batch_management/add_batches_22_33.py` (被 `add_batches_22_33_simple.py` 替代)
- `scripts/batch_management/fix_batch_10.py` (特定批次修复脚本，可以归档)
- `scripts/batch_management/update_batches_10_13.py` (特定批次更新脚本，可以归档)

## 📁 需要移动的文件

### 从根目录移到 docs/
- `AI_CAPABILITY_ANALYSIS.md`
- `FINAL_EVALUATION_REPORT.md`
- `OPTIMIZATION_SUMMARY.md`

### 从 scripts/ 移到 outputs/
- `migration_report.md`
- `migration_report.html`
- `migration_report.json`
- `comprehensive_migration_report.md`

### 从 scripts/ 移到 data/
- `migration_progress.db`

### 从 docs/ 移到 archive/
- `batch_01_mapping_report.md`
- `batch_03_mapping_report.md`
- `batch_04_mapping_report.md`
- `batch_06_mapping_report.md`
- `batch_07_mapping_report.md`
- `batch_08_mapping_report.md`
- `all_batches_mapping_summary.md`
- `JPROTOBUF_MIGRATION_REPORT.md`
- `MESSAGE_MAPPINGS_GUIDE.md`
- `MIGRATION_FINAL_13_BATCHES.md`
- `MIGRATION_PROGRESS_12_BATCHES_REPORT.md`

## ✅ 整理后的优势

1. **清晰的目录结构**: 每个目录职责明确
2. **避免文件重复**: 每个文件只有一个位置
3. **便于维护**: 相关文件集中管理
4. **历史归档**: 历史报告单独存放
5. **输出分离**: 生成的报告与脚本分离
6. **数据集中**: 数据库文件统一管理
