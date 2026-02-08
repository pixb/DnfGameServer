# SQLite 开发指南

## 概述

DNF Go Server 现在支持两种数据库后端：
- **MySQL/MariaDB**: 生产环境使用
- **SQLite**: 开发测试环境使用

## 快速开始

### 1. 初始化开发数据库

```bash
# 运行初始化脚本
chmod +x scripts/init-sqlite.sh
./scripts/init-sqlite.sh
```

### 2. 使用开发配置启动服务器

```bash
go run ./cmd/server/main.go serve --config config.dev.yaml
```

### 3. 验证数据库

```bash
# 检查SQLite数据库
sqlite3 ./data/dnf_dev.db ".tables"

# 查看账户表
sqlite3 ./data/dnf_dev.db "SELECT * FROM account;"
```

## 配置文件说明

### 开发配置 (config.dev.yaml)

```yaml
driver: sqlite
dsn: ./data/dnf_dev.db
mode: dev
```

### 生产配置 (config.prod.yaml)

```yaml
driver: mysql
dsn: user:password@tcp(localhost:3306)/dnf_game?charset=utf8mb4&parseTime=True&loc=Local
mode: prod
```

## MySQL vs SQLite 差异

| 特性 | MySQL | SQLite |
|------|-------|--------|
| 适用场景 | 生产环境，高并发 | 开发测试，单机 |
| 数据存储 | 独立服务器 | 本地文件 |
| 并发写入 | 支持 | 单写多读 |
| 数据类型 | 丰富 | 简单 |
| 外键约束 | 完全支持 | 支持(需启用) |

## 代码实现差异

### 1. 自增ID

**MySQL:**
```sql
id INT AUTO_INCREMENT PRIMARY KEY
```

**SQLite:**
```sql
id INTEGER PRIMARY KEY AUTOINCREMENT
```

### 2. 时间戳

**MySQL:**
```sql
created_at BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP())
```

**SQLite:**
```sql
created_at INTEGER NOT NULL DEFAULT (strftime('%s', 'now'))
```

### 3. INSERT OR UPDATE

**MySQL:**
```sql
INSERT ... ON DUPLICATE KEY UPDATE ...
```

**SQLite:**
```sql
INSERT ... ON CONFLICT(column) DO UPDATE SET ...
```

### 4. 连接池配置

**MySQL:**
```go
db.SetMaxOpenConns(25)
db.SetMaxIdleConns(10)
```

**SQLite:**
```go
db.SetMaxOpenConns(1) // SQLite单写
db.SetMaxIdleConns(1)
```

## 数据迁移

### 从MySQL迁移到SQLite

由于两种数据库的SQL语法有差异，建议使用应用层代码进行数据迁移，而不是直接导出导入。

示例代码：

```go
// 从MySQL读取
mysqlDriver, _ := db.NewDBDriver(mysqlProfile)
accounts, _ := mysqlDriver.ListAccounts(ctx, &store.FindAccount{})

// 写入SQLite
sqliteDriver, _ := db.NewDBDriver(sqliteProfile)
for _, acc := range accounts {
    sqliteDriver.CreateAccount(ctx, acc)
}
```

## 调试技巧

### 1. 查看SQLite数据库内容

```bash
# 交互式Shell
sqlite3 ./data/dnf_dev.db

# 查看表结构
.schema account

# 查看数据
SELECT * FROM account LIMIT 10;

# 导出为SQL
.dump > backup.sql
```

### 2. 启用日志

在开发配置中设置日志级别：

```yaml
log:
  level: debug
```

### 3. WAL模式

SQLite默认使用WAL(Write-Ahead Logging)模式，提高并发性能：

```go
db.Exec("PRAGMA journal_mode=WAL")
```

## 常见问题

### Q: SQLite支持并发写入吗？
A: SQLite使用文件锁实现并发控制，同一时刻只能有一个写入操作。对于游戏服务器，建议使用连接池设置`SetMaxOpenConns(1)`。

### Q: 如何备份SQLite数据库？
A: SQLite是单文件数据库，直接复制文件即可：
```bash
cp ./data/dnf_dev.db ./data/dnf_dev.db.backup
```

### Q: 生产环境可以使用SQLite吗？
A: 不推荐。SQLite适用于开发和测试环境，生产环境建议使用MySQL以获得更好的并发性能和运维支持。

## 文件结构

```
store/
├── db/
│   ├── db.go              # 驱动工厂
│   ├── mysql/             # MySQL实现
│   │   ├── db.go
│   │   ├── account.go
│   │   └── ...
│   └── sqlite/            # SQLite实现
│       ├── db.go
│       ├── account.go
│       └── ...
└── migration/
    └── sqlite/
        └── schema.sql     # SQLite Schema
```

## 开发建议

1. **开发阶段**: 使用SQLite，启动速度快，无需配置数据库服务器
2. **测试阶段**: 使用SQLite内存模式进行单元测试
3. **生产阶段**: 使用MySQL，确保数据安全和性能

---

**创建日期**: 2026-02-09
**版本**: v1.0
