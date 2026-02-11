# 查询工具使用说明

## 📋 概述

本目录包含三个通用的查询工具，用于查询 JProtobuf 到标准 Protobuf 的消息映射关系。

## 🛠️ 工具列表

### 1. universal_query.py（推荐使用）

**功能**：通用查询工具，支持多种查询方式和输出格式

**位置**：`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/query/universal_query.py`

**特点**：
- 支持精确匹配和模糊匹配
- 支持多种输出格式（表格、JSON、CSV）
- 显示字段数量对比
- 显示迁移状态

**使用方法**：

```bash
cd /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/query
```

#### 查询示例

##### 1.1 根据标准 Protobuf 消息名查询（模糊匹配）

```bash
python universal_query.py --proto RobotUserAttribute2Request
```

##### 1.2 根据标准 Protobuf 消息名查询（精确匹配）

```bash
python universal_query.py --proto RobotUserAttribute2Request --exact
```

##### 1.3 根据 JProtobuf 消息名查询

```bash
python universal_query.py --jprotobuf REQ_LOGIN
```

##### 1.4 根据 ModuleID 查询

```bash
python universal_query.py --module 10000
```

##### 1.5 根据批次查询

```bash
python universal_query.py --batch 1
```

##### 1.6 根据消息类型查询

```bash
python universal_query.py --type REQ
```

##### 1.7 根据迁移状态查询

```bash
python universal_query.py --status completed
```

##### 1.8 列出所有映射

```bash
python universal_query.py --all
```

##### 1.9 列出指定数量的映射

```bash
python universal_query.py --all --limit 50
```

##### 1.10 显示统计信息

```bash
python universal_query.py --stats
```

##### 1.11 输出为 JSON 格式

```bash
python universal_query.py --proto RobotUserAttribute2Request --format json
```

##### 1.12 输出为 CSV 格式

```bash
python universal_query.py --batch 1 --format csv
```

#### 查询结果说明

查询结果包含以下信息：

- **JProtobuf 消息名称**：原始 JProtobuf 消息的名称
- **JProtobuf 文件路径**：JProtobuf Java 文件的完整路径
- **ModuleID**：消息的模块 ID
- **消息类型**：REQ、RES、PT、NOTIFY 等
- **JProtobuf 字段数**：JProtobuf 消息的字段数量
- **标准 Protobuf 消息名称**：对应的标准 Protobuf 消息名称
- **标准 Protobuf 文件路径**：标准 Protobuf 文件的完整路径
- **包名**：标准 Protobuf 的包名
- **标准 Protobuf 字段数**：标准 Protobuf 消息的字段数量
- **映射类型**：direct_mapping、name_based_mapping 等
- **置信度**：映射的置信度（0.0 - 1.0）
- **已验证**：是否已验证（是/否）
- **批次**：所属批次号和批次名称
- **迁移状态**：completed、pending、in_progress、failed

### 2. query_jprotobuf_proto_mappings.py

**功能**：查询 JProtobuf 到标准 Protobuf 的消息映射关系

**位置**：`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/query/query_jprotobuf_proto_mappings.py`

**使用方法**：

```bash
cd /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/query
```

#### 查询示例

##### 1.1 根据标准 Protobuf 消息名查询

```bash
python query_jprotobuf_proto_mappings.py --proto RobotUserAttribute2Request
```

##### 1.2 根据 JProtobuf 消息名查询

```bash
python query_jprotobuf_proto_mappings.py --jprotobuf REQ_LOGIN
```

##### 1.3 根据 ModuleID 查询

```bash
python query_jprotobuf_proto_mappings.py --module 10000
```

##### 1.4 根据批次查询

```bash
python query_jprotobuf_proto_mappings.py --batch 1
```

##### 1.5 根据消息类型查询

```bash
python query_jprotobuf_proto_mappings.py --type REQ
```

##### 1.6 查询已验证的消息

```bash
python query_jprotobuf_proto_mappings.py --migrated
```

##### 1.7 查询未验证的消息

```bash
python query_jprotobuf_proto_mappings.py --not-migrated
```

##### 1.8 列出所有映射

```bash
python query_jprotobuf_proto_mappings.py --all
```

##### 1.9 列出指定数量的映射

```bash
python query_jprotobuf_proto_mappings.py --all --limit 50
```

##### 1.10 显示统计信息

```bash
python query_jprotobuf_proto_mappings.py --stats
```

#### 查询结果说明

查询结果包含以下信息：

- **JProtobuf 消息名称**：原始 JProtobuf 消息的名称
- **JProtobuf 文件路径**：JProtobuf Java 文件的完整路径
- **ModuleID**：消息的模块 ID
- **消息类型**：REQ、RES、PT、NOTIFY 等
- **标准 Protobuf 消息名称**：对应的标准 Protobuf 消息名称
- **标准 Protobuf 文件路径**：标准 Protobuf 文件的完整路径
- **包名**：标准 Protobuf 的包名
- **映射类型**：direct_mapping、name_based_mapping 等
- **置信度**：映射的置信度（0.0 - 1.0）
- **已验证**：是否已验证（是/否）
- **批次**：所属批次号和批次名称

### 2. query_mappings.py

**功能**：查询消息映射关系（旧版，使用旧数据库）

**位置**：`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/query/query_mappings.py`

**注意**：此工具使用旧数据库结构，建议使用 `query_jprotobuf_proto_mappings.py`

## 📊 数据库信息

**数据库路径**：`/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db`

**主要表结构**：

- `jprotobuf_messages`：JProtobuf 消息表
- `proto_messages`：标准 Protobuf 消息表
- `message_mappings`：消息映射关系表
- `migration_batches`：迁移批次表
- `migration_records`：迁移记录表

## 💡 使用技巧

### 1. 快速查询特定消息

```bash
# 查询 RobotUserAttribute2Request
python query_jprotobuf_proto_mappings.py --proto RobotUserAttribute2Request

# 查询所有包含 LOGIN 的消息
python query_jprotobuf_proto_mappings.py --proto LOGIN
```

### 2. 查看批次详情

```bash
# 查看批次 1 的所有消息
python query_jprotobuf_proto_mappings.py --batch 1
```

### 3. 按消息类型统计

```bash
# 查看所有 REQ 类型的消息
python query_jprotobuf_proto_mappings.py --type REQ

# 查看所有 RES 类型的消息
python query_jprotobuf_proto_mappings.py --type RES
```

### 4. 查看整体进度

```bash
# 显示统计信息
python query_jprotobuf_proto_mappings.py --stats
```

### 5. 查找未迁移的消息

```bash
# 查找所有未验证的消息
python query_jprotobuf_proto_mappings.py --not-migrated
```

## 🔧 故障排除

### 问题 1：找不到数据库文件

**错误信息**：`sqlite3.OperationalError: unable to open database file`

**解决方案**：
```bash
# 检查数据库文件是否存在
ls -l /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db

# 如果不存在，运行数据库初始化脚本
cd /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts
python init_migration_database.py --init
```

### 问题 2：查询结果为空

**可能原因**：
- 消息名称拼写错误
- 消息尚未迁移
- 批次号不存在

**解决方案**：
- 使用模糊查询（部分匹配）
- 查看统计信息确认数据是否存在
- 检查消息名称大小写

### 问题 3：权限错误

**错误信息**：`Permission denied`

**解决方案**：
```bash
# 添加执行权限
chmod +x /home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/query/*.py
```

## 📝 常用查询命令速查

### universal_query.py（推荐）

```bash
# 查询特定消息（模糊匹配）
python universal_query.py --proto <消息名>

# 查询特定消息（精确匹配）
python universal_query.py --proto <消息名> --exact

# 查询批次
python universal_query.py --batch <批次号>

# 查询类型
python universal_query.py --type <REQ|RES|PT|NOTIFY>

# 查看统计
python universal_query.py --stats

# 查看所有
python universal_query.py --all --limit <数量>

# 输出为 JSON
python universal_query.py --proto <消息名> --format json

# 输出为 CSV
python universal_query.py --batch <批次号> --format csv
```

### query_jprotobuf_proto_mappings.py

## 🎯 实际应用场景

### 场景 1：验证消息映射

```bash
# 验证 RobotUserAttribute2Request 的映射关系
python universal_query.py --proto RobotUserAttribute2Request
```

### 场景 2：查看批次进度

```bash
# 查看批次 1 的所有消息
python universal_query.py --batch 1
```

### 场景 3：查找未迁移的消息

```bash
# 查找所有未验证的消息
python universal_query.py --status pending
```

### 场景 4：按模块查询

```bash
# 查询 ModuleID 为 10000 的所有消息
python universal_query.py --module 10000
```

### 场景 5：查看整体迁移进度

```bash
# 显示统计信息
python universal_query.py --stats
```

### 场景 6：导出查询结果

```bash
# 导出为 JSON 格式
python universal_query.py --batch 1 --format json > batch1.json

# 导出为 CSV 格式
python universal_query.py --batch 1 --format csv > batch1.csv
```

### 场景 7：精确匹配查询

```bash
# 精确匹配消息名称
python universal_query.py --jprotobuf REQ_LOGIN --exact
```

## 📚 相关文档

- [迁移流程规划](../../03_迁移/01_迁移流程规划.md)
- [迁移指南](../../03_迁移/02_迁移指南.md)
- [进度跟踪](../../03_迁移/03_进度跟踪.md)

## 🆘 获取帮助

```bash
# 查看帮助信息
python query_jprotobuf_proto_mappings.py --help
```
