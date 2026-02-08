# 数据库迁移指南

## 概述

DNF Go Server 提供了完整的数据库迁移系统，支持：

1. **新部署** - 创建全新的数据库表结构
2. **版本升级** - 应用增量迁移
3. **从 Java 迁移** - 从 Java DnfGameServer 平滑迁移数据

## 迁移系统架构

```
store/migration/
├── mysql/
│   ├── LATEST.sql                 # 最新完整 Schema
│   └── 1.0.0__migrate_from_java.sql  # 从 Java 迁移数据
└── sqlite/
    └── LATEST.sql                 # SQLite 版本
```

### 版本控制表

迁移系统使用 `schema_migrations` 表记录已应用的迁移：

```sql
CREATE TABLE schema_migrations (
    version VARCHAR(255) PRIMARY KEY,
    applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT NOT NULL DEFAULT ''
);
```

## 开发环境 (SQLite) - 推荐

开发环境默认使用 SQLite 数据库，避免污染 MySQL 生产数据库。

### 快速开始

```bash
# 使用 SQLite 运行开发服务器（推荐）
make dev

# 或者手动运行
go run ./cmd/server serve --config=configs/config.dev.yaml
```

开发配置 (`configs/config.dev.yaml`):
- 驱动: SQLite
- 数据库文件: `./data/dnf_game.db`
- 日志级别: debug

### 开发命令

```bash
# 启动开发服务器 (SQLite)
make dev

# 重置 SQLite 数据库
make dev-reset

# 运行测试 (SQLite)
make dev-test
```

## 生产环境 (MySQL)

生产环境使用 MySQL 数据库。

### 1. 全新部署

创建新的数据库表结构：

```bash
make migrate
# 或
./bin/dnf-server migrate
```

### 2. 查看迁移状态

```bash
./bin/dnf-server migrate status
```

输出示例：
```
Migration Status
================
Driver: mysql

Applied migrations: 1

VERSION         APPLIED AT                     DESCRIPTION
--------------------------------------------------------------------------------
1.0.0           2026-02-09 12:00:00           Initial schema - 兼容 Java DnfGameServer
```

### 3. 从 Java DnfGameServer 迁移

如果你已有 Java 版本的 DnfGameServer 数据库，可以平滑迁移数据：

```bash
# 预览迁移（不实际执行）
./bin/dnf-server migrate from-java --dry-run

# 执行迁移
./bin/dnf-server migrate from-java

# 强制迁移（跳过确认）
./bin/dnf-server migrate from-java --force
```

⚠️ **警告**: 迁移前请务必备份数据库！

## 表结构映射

### Java → Go 表名映射

| Java 表名 | Go 表名 | 说明 |
|-----------|---------|------|
| t_account | account | 账户表 |
| t_role | role | 角色表 |
| t_notice | notice | 公告表 |
| t_paydata | pay_data | 支付数据表 |
| t_charge | charge | 充值表 |
| t_auction | auction | 拍卖表 |
| t_offline | offline_data | 离线数据表 |
| p_taskset / p_taskinfo | quest | 任务定义表 |
| - | role_attributes | 角色属性表（新拆分） |
| - | role_currency | 角色货币表（新拆分） |
| - | bag_item | 背包物品表（新拆分） |
| - | guild | 公会表（新增） |
| - | guild_member | 公会成员表（新增） |
| - | friend | 好友表（新增） |
| - | mail | 邮件表（新增） |

### 字段映射示例

**t_account → account**

| Java 字段 | Go 字段 | 类型变化 |
|-----------|---------|----------|
| id | openid | VARCHAR → VARCHAR (主键改为自增 id) |
| accountkey | account_key | VARCHAR |
| lastLoginTime | last_login_at | BIGINT → BIGINT |
| privilege | authority | SMALLINT → INT |
| isStop | status | TINYINT → INT |
| moneyBox | money_box | JSON → JSON |

**t_role → role**

| Java 字段 | Go 字段 | 说明 |
|-----------|---------|------|
| uid | id | BIGINT (新自增主键) |
| openid | account_id | 外键关联 account |
| roleId | role_id | 角色槽位 1-4 |
| pos | pos | JSON 坐标数据 |
| equipscore | equip_score | 装备评分 |
| ... | ... | ... |

## 数据迁移流程

### 从 Java 迁移的完整流程

1. **备份数据库**
   ```bash
   mysqldump -u root -p game > backup_$(date +%Y%m%d).sql
   ```

2. **运行基础迁移**
   ```bash
   ./bin/dnf-server migrate
   ```

3. **从 Java 迁移数据**
   ```bash
   ./bin/dnf-server migrate from-java
   ```

4. **验证数据**
   ```sql
   -- 检查账户数量
   SELECT COUNT(*) FROM account;
   SELECT COUNT(*) FROM t_account; -- 对比

   -- 检查角色数量
   SELECT COUNT(*) FROM role;
   SELECT COUNT(*) FROM t_role; -- 对比
   ```

### 迁移脚本说明

**1.0.0__migrate_from_java.sql** 执行以下操作：

1. 迁移账户数据 (`t_account` → `account`)
2. 迁移角色数据 (`t_role` → `role`)
3. 初始化角色属性 (`role_attributes`)
4. 初始化角色货币 (`role_currency`)
5. 迁移公告数据 (`t_notice` → `notice`)
6. 迁移支付数据 (`t_paydata` → `pay_data`)
7. 迁移充值数据 (`t_charge` → `charge`)
8. 迁移离线数据 (`t_offline` → `offline_data`)
9. 迁移拍卖数据 (`t_auction` → `auction`)
10. 迁移任务数据 (`p_taskset/p_taskinfo` → `quest`)

## 增量迁移

如需添加新的迁移：

1. 在 `store/migration/mysql/` 创建新文件：
   ```
   1.1.0__add_new_feature.sql
   ```

2. 编写迁移 SQL：
   ```sql
   -- 1.1.0__add_new_feature.sql
   ALTER TABLE role ADD COLUMN new_field INT DEFAULT 0;

   INSERT INTO schema_migrations (version, description) VALUES
   ('1.1.0', 'Add new feature')
   ON DUPLICATE KEY UPDATE applied_at = CURRENT_TIMESTAMP;
   ```

3. 运行迁移：
   ```bash
   ./bin/dnf-server migrate
   ```

## 回滚

当前迁移系统不支持自动回滚。如需回滚，请：

1. 使用备份恢复数据库
2. 清除 `schema_migrations` 表中的记录
3. 重新运行迁移

## 故障排除

### 问题1: 迁移表已存在

```
Error: Table 'account' already exists
```

**解决**: 如需重新迁移，先删除表或使用 `--force` 标志。

### 问题2: 数据类型不匹配

```
Error: Data too long for column 'name'
```

**解决**: 检查源数据长度，可能需要手动调整。

### 问题3: 外键约束失败

```
Error: Cannot add or update a child row: a foreign key constraint fails
```

**解决**: 确保父表数据先迁移，或暂时禁用外键检查：
```sql
SET FOREIGN_KEY_CHECKS = 0;
-- 执行迁移
SET FOREIGN_KEY_CHECKS = 1;
```

## 最佳实践

1. **始终备份** - 迁移前务必备份数据库
2. **测试环境** - 先在测试环境验证迁移
3. **版本控制** - 使用语义化版本号（如 1.0.0, 1.1.0）
4. **原子性** - 每个迁移应该完整执行或完全回滚
5. **兼容性** - 保持向后兼容，不要删除旧字段

## 相关命令

```bash
# 构建服务器
make build

# 运行迁移
make migrate

# 查看帮助
./bin/dnf-server migrate --help
./bin/dnf-server migrate from-java --help
```
