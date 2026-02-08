# SQLite 开发环境配置

## 概述

DNF Go Server 支持两种数据库驱动：

1. **SQLite** - 用于开发和测试（默认）
2. **MySQL** - 用于生产环境

使用 SQLite 的好处：
- ✅ 无需安装 MySQL 服务器
- ✅ 零配置，开箱即用
- ✅ 数据隔离，不会污染生产数据库
- ✅ 轻量级，适合开发和测试

## 快速开始

### 1. 使用 SQLite 运行开发服务器

```bash
cd dnf-go-server

# 方式1: 使用 Make 命令（推荐）
make dev

# 方式2: 手动运行
go run ./cmd/server serve --config=configs/config.dev.yaml
```

### 2. 配置说明

开发配置文件 `configs/config.dev.yaml`:

```yaml
# 服务器配置
port: 8081
mode: dev

# 数据库配置 - SQLite
driver: sqlite
dsn: ./data/dnf_game.db
```

### 3. 数据库文件位置

SQLite 数据库文件保存在项目目录：

```
dnf-go-server/
├── data/
│   ├── dnf_game.db          # 主数据库文件
│   ├── dnf_game.db-shm      # 共享内存文件
│   └── dnf_game.db-wal      # 预写日志文件
```

**注意**: `.db-shm` 和 `.db-wal` 是 SQLite 的辅助文件，不要手动删除。

## 常用命令

### 开发命令

```bash
# 启动开发服务器 (SQLite)
make dev

# 运行数据库迁移
make dev-migrate

# 重置数据库（删除并重新创建）
make dev-reset

# 运行测试
make dev-test
```

### 数据库管理

```bash
# 删除 SQLite 数据库
make clean-sqlite

# 查看数据库文件
ls -lh data/

# 使用 sqlite3 命令行工具
sqlite3 data/dnf_game.db
```

## 数据库 Schema

SQLite 版本的 Schema 位于：`store/migration/sqlite/LATEST.sql`

与 MySQL 版本的主要区别：

| 特性 | MySQL | SQLite |
|------|-------|--------|
| 主键 | `BIGINT UNSIGNED AUTO_INCREMENT` | `INTEGER PRIMARY KEY AUTOINCREMENT` |
| 时间戳 | `BIGINT DEFAULT UNIX_TIMESTAMP()` | `INTEGER DEFAULT (strftime('%s', 'now'))` |
| JSON | `JSON` 类型 | `TEXT` 类型 |
| 布尔值 | `BOOLEAN` | `INTEGER` (0/1) |
| 字符串 | `VARCHAR(n)` | `TEXT` |

## 从 SQLite 迁移到 MySQL

当需要将开发环境的数据迁移到生产 MySQL 时：

### 1. 导出 SQLite 数据

```bash
sqlite3 data/dnf_game.db .dump > backup.sql
```

### 2. 配置 MySQL

编辑 `configs/config.yaml`:

```yaml
# MySQL 配置
driver: mysql
dsn: root:password@tcp(127.0.0.1:3306)/dnf_game?charset=utf8mb4&parseTime=True&loc=Local
```

### 3. 运行迁移

```bash
make migrate
```

## 故障排除

### 问题1: 数据库文件被锁定

```
database is locked
```

**解决**: SQLite 只支持单写。确保没有其他进程访问数据库，或等待几秒钟重试。

### 问题2: 无法创建数据目录

```
failed to create database directory
```

**解决**: 确保有权限创建目录：

```bash
mkdir -p data
chmod 755 data
```

### 问题3: 表不存在

```
no such table: account
```

**解决**: 先运行数据库迁移：

```bash
make dev-migrate
```

## .gitignore

SQLite 数据库文件已添加到 `.gitignore`，不会被提交到 Git：

```
data/
*.db
*.db-shm
*.db-wal
*.sqlite
*.sqlite3
```

## 注意事项

1. **并发限制**: SQLite 只支持单写多读，不适合高并发场景
2. **数据备份**: 定期备份 `data/dnf_game.db` 文件
3. **生产环境**: 生产环境必须使用 MySQL，不要直接使用 SQLite

## 相关文档

- [数据库迁移指南](MIGRATION.md)
- [架构设计文档](architecture/)
