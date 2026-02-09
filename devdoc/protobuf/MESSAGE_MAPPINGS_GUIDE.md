# 消息文件映射追踪系统使用指南

## 📋 系统概述

本系统用于追踪JProtobuf到标准Protobuf的消息映射关系，记录新旧消息的对应关系、文件路径和实现状态。

**核心功能**:
- ✅ 记录新旧消息映射关系
- ✅ 追踪proto/Java/Go文件路径
- ✅ 监控实现状态（完整/简化/缺失）
- ✅ 自动生成Markdown报告
- ✅ 提供命令行查询工具

---

## 📁 文件结构

```
devdoc/protobuf/
├── migration_progress.db              # SQLite数据库（核心）
├── message_mappings/                  # 映射相关脚本
│   ├── create_mappings_table.py      # 创建数据库表
│   ├── extract_message_mappings.py   # 提取并填充数据
│   ├── generate_mapping_reports.py   # 生成报告
│   └── query_mappings.py             # 查询工具
└── reports/                          # 生成的报告
    ├── all_batches_mapping_summary.md    # 汇总报告
    ├── batch_01_mapping_report.md        # 批次01报告
    ├── batch_02_mapping_report.md        # 批次02报告
    └── ...
```

---

## 🚀 快速开始

### 1. 查看现有报告

```bash
cd devdoc/protobuf/reports

# 查看汇总报告
cat all_batches_mapping_summary.md

# 查看特定批次报告
cat batch_01_mapping_report.md
```

### 2. 使用查询工具

```bash
cd devdoc/protobuf

# 查看统计信息
python query_mappings.py --stats

# 查询特定消息
python query_mappings.py --old REQ_LOGIN
python query_mappings.py --new LoginRequest

# 查询特定ModuleID
python query_mappings.py --module 10000

# 查询特定批次
python query_mappings.py --batch batch_01

# 查询实现状态
python query_mappings.py --status complete
python query_mappings.py --status missing
```

### 3. 更新数据（迁移新批次后）

```bash
cd devdoc/protobuf

# 步骤1: 提取新数据
python extract_message_mappings.py

# 步骤2: 重新生成报告
python generate_mapping_reports.py
```

---

## 📊 数据库结构

### 核心表: message_file_mappings

| 字段 | 说明 | 示例 |
|:-----|:-----|:-----|
| batch_id | 批次ID | 1 (batch_01) |
| module_id | Module ID | 10000 |
| cmd_id | CMD ID | 0 |
| old_message_name | 旧消息名 | REQ_LOGIN |
| old_message_type | 消息类型 | REQ/RES/PT |
| old_java_file | 原Java文件路径 | src/main/java/.../REQ_LOGIN.java |
| new_message_name | 新消息名 | LoginRequest |
| new_proto_file | Proto文件路径 | proto/dnf/v1/auth_login.proto |
| new_java_file | 生成的Java文件 | proto/gen/java/.../LoginRequest.java |
| new_go_file | 生成的Go文件 | dnf-go-client/gen/dnf/v1/auth_login.pb.go |
| implementation_status | 实现状态 | complete/simplified/missing |

---

## 📈 报告内容说明

### 批次报告包含:

1. **消息映射清单**
   - ModuleID/CMD
   - 旧消息名 → 新消息名
   - Proto文件
   - 实现状态
   - 生成文件列表

2. **文件路径汇总**
   - 原Java文件位置
   - Proto文件位置
   - 生成的Java文件位置
   - 生成的Go文件位置

3. **实现状态统计**
   - 完整实现数量
   - 简化实现数量
   - 缺失实现数量

---

## 🔧 工作流程

### 场景1: 新批次迁移完成后

```bash
# 1. 迁移完成后，提取新数据
python extract_message_mappings.py

# 2. 生成新批次的报告
python generate_mapping_reports.py

# 3. 查看报告
ls reports/batch_XX_mapping_report.md
```

### 场景2: 查找特定消息的对应关系

```bash
# 知道旧消息名，查找新消息
python query_mappings.py --old REQ_LOGIN

# 知道新消息名，查找旧消息
python query_mappings.py --new LoginRequest

# 查看所有消息
python query_mappings.py --all
```

### 场景3: 检查实现状态

```bash
# 查看统计
python query_mappings.py --stats

# 查看哪些消息缺失实现
python query_mappings.py --status missing

# 查看哪些消息已完整实现
python query_mappings.py --status complete
```

---

## 💡 使用技巧

### 1. 批量查询

```bash
# 查询所有ModuleID为10000的消息
python query_mappings.py --module 10000

# 查询特定批次的所有消息
python query_mappings.py --batch batch_08
```

### 2. 模糊查询

```bash
# 查询包含"LOGIN"的所有消息
python query_mappings.py --old LOGIN

# 查询包含"Character"的所有消息
python query_mappings.py --new Character
```

### 3. 对比检查

```bash
# 检查批次08的城镇消息是否都完整实现
python query_mappings.py --batch batch_08 | grep "实现状态"
```

---

## 📝 实现状态说明

| 状态 | 含义 | 说明 |
|:-----|:-----|:-----|
| **complete** | 完整实现 | 编解码器中已实现完整的字段映射 |
| **simplified** | 简化实现 | 编解码器中返回null或默认值 |
| **missing** | 缺失实现 | 编解码器中未找到对应的适配方法 |

---

## 🔄 系统更新流程

每次完成新批次迁移后，执行以下步骤更新系统：

```bash
cd devdoc/protobuf

# 1. 提取新的消息映射数据
python extract_message_mappings.py

# 2. 生成新的报告
python generate_mapping_reports.py

# 3. 查看更新后的统计
python query_mappings.py --stats

# 4. 查看新批次的详细报告
cat reports/batch_XX_mapping_report.md
```

---

## 📞 故障排除

### 问题1: 查询不到消息

**可能原因**:
- 数据库未初始化
- 数据未提取

**解决方案**:
```bash
# 重新提取数据
python extract_message_mappings.py
```

### 问题2: 报告生成失败

**可能原因**:
- reports目录不存在
- 数据库中没有数据

**解决方案**:
```bash
# 创建目录并重新生成
mkdir -p reports
python extract_message_mappings.py
python generate_mapping_reports.py
```

### 问题3: 实现状态显示不准确

**可能原因**:
- 编解码器代码有更新
- 正则表达式匹配失败

**解决方案**:
- 检查 `extract_message_mappings.py` 中的正则表达式
- 手动更新特定消息的implementation_status字段

---

## 🎯 最佳实践

1. **定期更新**: 每次完成新批次迁移后立即更新数据库
2. **查看报告**: 定期查看报告，了解整体进度
3. **关注缺失**: 重点关注 `missing` 状态的消息，这些需要完善
4. **备份数据**: 定期备份 `migration_progress.db` 文件

---

## 📚 相关文档

- `migration_tracker.py` - 迁移进度追踪工具
- `AI_OPERATION_GUIDE.md` - AI操作指南
- `README_MIGRATION_TRACKER.md` - 追踪系统说明

---

**创建时间**: 2026-02-09  
**版本**: v1.0  
**维护者**: AI Assistant
