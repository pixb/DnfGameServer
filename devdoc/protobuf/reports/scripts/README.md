# 脚本工具目录

## 核心工具

### main.py - 统一入口（推荐使用）

```bash
python3 main.py status      # 查看状态
python3 main.py fix         # 修正批次
python3 main.py report html # 生成报告
python3 main.py analyze     # 分析JProtobuf
```

## 功能模块

- **core/** - 核心追踪系统
  - migration_tracker.py - 数据库操作
  - init_migration_db.py - 数据库初始化

- **batch_management/** - 批次管理
  - update_all_batches.py - 更新批次
  - fix_batch_status.py - 修正批次状态
  - fix_batch_10.py - 修正批次10
  - update_batches_10_13.py - 更新批次10-13

- **reports/** - 报告生成
  - generate_report.py - 生成进度报告
  - generate_mapping_reports.py - 生成映射报告

- **query/** - 查询工具
  - query_mappings.py - 消息映射查询

- **extract/** - 数据提取
  - extract_message_mappings.py - 提取映射数据

- **analyze/** - 分析工具
  - analyze_jprotobuf_files.py - JProtobuf分析

- **database/** - 数据库管理
  - create_mappings_table.py - 创建映射表
