# JProtobuf到标准Protobuf迁移系统使用指南

## 📋 系统概述

本系统提供完整的JProtobuf到标准Protobuf迁移记录和管理功能，包括：

- **数据库管理**：完整的数据库表结构，支持迁移记录、消息映射、批次管理等
- **文件扫描**：自动扫描JProtobuf和标准Protobuf文件
- **映射分析**：分析消息之间的映射关系
- **历史记录**：记录完整的迁移历史
- **状态追踪**：实时追踪迁移状态
- **报告生成**：生成详细的迁移报告

## 🚀 快速开始

### 1. 完整设置

```bash
cd /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts
python migration_system.py --full-setup
```

这将执行以下操作：
1. 初始化数据库
2. 扫描所有JProtobuf和标准Protobuf文件
3. 分析映射关系
4. 导入迁移历史
5. 生成所有报告
6. 显示当前状态

### 2. 分步操作

#### 初始化数据库

```bash
python init_migration_database.py --init
```

#### 扫描JProtobuf文件

```bash
python jprotobuf_scanner.py --scan
```

#### 扫描标准Protobuf文件

```bash
python proto_scanner.py --scan
```

#### 分析映射关系

```bash
python mapping_analyzer.py --analyze
```

#### 导入迁移历史

```bash
python migration_history_system.py --import-all
```

#### 生成报告

```bash
python migration_report_generator.py --generate-all
```

## 📊 数据库表结构

### 1. jprotobuf_messages
存储JProtobuf消息信息

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| message_name | TEXT | 消息名称 |
| file_path | TEXT | 文件路径 |
| module_id | INTEGER | 模块ID |
| cmd | INTEGER | 命令ID |
| message_type | TEXT | 消息类型 (REQ/RES/PT/OTHER/ENUM) |
| field_count | INTEGER | 字段数量 |
| has_dependencies | BOOLEAN | 是否有依赖 |

### 2. proto_messages
存储标准Protobuf消息信息

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| message_name | TEXT | 消息名称 |
| file_path | TEXT | 文件路径 |
| package_name | TEXT | 包名 |
| field_count | INTEGER | 字段数量 |
| is_nested | BOOLEAN | 是否嵌套 |
| parent_message | TEXT | 父消息 |

### 3. message_mappings
存储消息映射关系

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| jprotobuf_message_id | INTEGER | JProtobuf消息ID |
| proto_message_id | INTEGER | 标准Protobuf消息ID |
| mapping_type | TEXT | 映射类型 |
| mapping_confidence | REAL | 映射置信度 |
| is_verified | BOOLEAN | 是否已验证 |
| verified_by | TEXT | 验证人 |
| verified_at | TIMESTAMP | 验证时间 |
| notes | TEXT | 备注 |

### 4. migration_batches
存储迁移批次信息

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| batch_number | INTEGER | 批次编号 |
| batch_name | TEXT | 批次名称 |
| description | TEXT | 描述 |
| status | TEXT | 状态 (pending/in_progress/completed/failed) |
| start_time | TIMESTAMP | 开始时间 |
| end_time | TIMESTAMP | 结束时间 |
| jprotobuf_count | INTEGER | JProtobuf消息数 |
| proto_count | INTEGER | 标准Protobuf消息数 |

### 5. migration_records
存储迁移记录

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| batch_id | INTEGER | 批次ID |
| jprotobuf_message_id | INTEGER | JProtobuf消息ID |
| proto_message_id | INTEGER | 标准Protobuf消息ID |
| migration_status | TEXT | 迁移状态 |
| migration_reason | TEXT | 迁移原因 |
| migration_notes | TEXT | 迁移备注 |

### 6. message_fields
存储消息字段信息

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| message_type | TEXT | 消息类型 (jprotobuf/proto) |
| message_id | INTEGER | 消息ID |
| field_name | TEXT | 字段名称 |
| field_type | TEXT | 字段类型 |
| field_number | INTEGER | 字段编号 |
| is_repeated | BOOLEAN | 是否重复 |
| is_optional | BOOLEAN | 是否可选 |
| default_value | TEXT | 默认值 |
| comment | TEXT | 注释 |

### 7. message_dependencies
存储消息依赖关系

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| parent_message_id | INTEGER | 父消息ID |
| child_message_id | INTEGER | 子消息ID |
| dependency_type | TEXT | 依赖类型 |
| dependency_description | TEXT | 依赖描述 |

### 8. migration_history
存储迁移历史

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| batch_id | INTEGER | 批次ID |
| action_type | TEXT | 操作类型 |
| action_description | TEXT | 操作描述 |
| action_data | TEXT | 操作数据 |
| performed_by | TEXT | 执行人 |
| created_at | TIMESTAMP | 创建时间 |

### 9. message_usage_scenarios
存储消息使用场景

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键 |
| message_type | TEXT | 消息类型 |
| message_id | INTEGER | 消息ID |
| scenario_name | TEXT | 场景名称 |
| scenario_description | TEXT | 场景描述 |
| used_by_messages | TEXT | 被哪些消息使用 |

## 📈 状态查询

### 查看整体状态

```bash
python migration_status_tracker.py --overall
```

### 查看消息状态

```bash
python migration_status_tracker.py --message PT_ACHIEVEMENT_REWARD
```

### 查看批次状态

```bash
python migration_status_tracker.py --batch 12
```

### 查看未映射的消息

```bash
python migration_status_tracker.py --unmapped
```

### 查看未迁移的消息

```bash
python migration_status_tracker.py --unmigrated
```

### 按类型查看迁移摘要

```bash
python migration_status_tracker.py --by-type
```

## 📄 报告生成

### 生成所有报告

```bash
python migration_report_generator.py --generate-all
```

### 生成整体报告

```bash
python migration_report_generator.py --overall
```

### 生成映射关系报告

```bash
python migration_report_generator.py --mapping
```

### 生成批次报告

```bash
python migration_report_generator.py --batch 12
```

## 📂 报告文件位置

所有报告保存在：`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/docs/`

- `MIGRATION_OVERALL_REPORT.md` - 整体迁移报告
- `MIGRATION_MAPPING_REPORT.md` - 映射关系报告
- `BATCH_XX_REPORT.md` - 各批次报告

## 🔧 数据库位置

数据库文件：`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db`

## 📊 当前系统状态

| 指标 | 数值 |
|------|------|
| JProtobuf消息总数 | 2202 |
| 标准Protobuf消息总数 | 2148 |
| 已映射消息数 | 14 (0.64%) |
| 已迁移消息数 | 14 (0.64%) |
| 迁移记录总数 | 19 |
| 批次总数 | 90 |
| 已完成批次 | 84 (93.33%) |

## 🎯 下一步工作

1. **完善映射关系**：目前只有14个消息建立了映射关系，需要继续分析其他消息的映射
2. **验证映射准确性**：对已建立的映射关系进行验证
3. **补充迁移记录**：为更多消息补充迁移记录
4. **分析未迁移消息**：分析未迁移消息的原因和优先级
5. **优化映射算法**：改进映射关系分析算法，提高准确率

## 📝 注意事项

1. **数据备份**：在进行重要操作前，建议备份数据库文件
2. **定期更新**：定期运行扫描和映射分析，保持数据最新
3. **验证结果**：对生成的映射关系进行人工验证
4. **版本控制**：将报告文件纳入版本控制
5. **团队协作**：建立团队协作流程，确保迁移记录的准确性

## 🆘 常见问题

### Q: 如何重新初始化数据库？

```bash
python init_migration_database.py --drop
python init_migration_database.py --init
```

### Q: 如何查看迁移历史？

```bash
python migration_history_system.py --history
```

### Q: 如何查看特定消息的迁移历史？

```bash
python migration_history_system.py --message PT_ACHIEVEMENT_REWARD
```

### Q: 数据库文件在哪里？

`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db`

### Q: 报告文件在哪里？

`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/docs/`

## 📞 支持

如有问题，请查看相关脚本的帮助信息：

```bash
python migration_system.py --help
```
