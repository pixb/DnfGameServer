# 数据库迁移工具

## 概述

本工具用于将Java服务器的MySQL数据库迁移到Go服务端的SQLite数据库。

## 功能特性

- ✅ 自动迁移账户数据
- ✅ 自动迁移角色数据
- ✅ 自动迁移成就配置和记录
- ✅ 自动迁移游戏配置表
- ✅ 数据类型自动转换
- ✅ JSON字段智能解析
- ✅ 时间戳自动转换
- ✅ 外键关系自动处理
- ✅ 数据完整性验证
- ✅ 详细的迁移日志

## 环境要求

- Python 3.7+
- MySQL 5.7+
- SQLite 3.x

## 安装依赖

```bash
pip install mysql-connector-python
```

## 配置说明

### 1. MySQL配置

编辑 `scripts/migrate_data.py` 中的MySQL连接配置：

```python
MYSQL_CONFIG = {
    'host': '127.0.0.1',      # MySQL服务器地址
    'port': 3306,               # MySQL端口
    'user': 'root',             # MySQL用户名
    'password': '123456',       # MySQL密码
    'database': 'game',         # MySQL数据库名
    'charset': 'utf8mb4'
}
```

### 2. SQLite配置

编辑 `scripts/migrate_data.py` 中的SQLite数据库路径：

```python
SQLITE_DB = 'game.db'  # SQLite数据库文件路径
```

## 使用方法

### 步骤1: 准备SQLite数据库

```bash
cd dnf-go-server

# 创建SQLite数据库
sqlite3 game.db < store/migration/sqlite/schema.sql
```

### 步骤2: 执行迁移

```bash
# 进入脚本目录
cd scripts

# 执行迁移脚本
python migrate_data.py
```

### 步骤3: 验证数据

```bash
# 连接SQLite数据库
sqlite3 ../game.db

# 查看数据统计
SELECT COUNT(*) FROM account;
SELECT COUNT(*) FROM role;
SELECT COUNT(*) FROM t_achievement_config;
SELECT COUNT(*) FROM t_achievement_record;

# 退出
.quit
```

## 迁移内容

### 核心业务表

| 表名 | 说明 | 状态 |
|------|------|------|
| account | 账户表 | ✅ |
| role | 角色表 | ✅ |
| t_achievement_config | 成就配置表 | ✅ |
| t_achievement_record | 成就记录表 | ✅ |

### 配置表

| 表名 | 说明 | 状态 |
|------|------|------|
| p_exp | 经验配置表 | ✅ |
| p_equip | 装备配置表 | ✅ |
| p_consume | 消耗品配置表 | ✅ |
| p_skill | 技能配置表 | ✅ |
| p_npc | NPC配置表 | ✅ |
| p_gamemap | 地图配置表 | ✅ |
| p_dungeon | 副本配置表 | ✅ |
| p_petexp | 宠物经验配置表 | ✅ |
| p_itemshop | 物品商店配置表 | ✅ |
| p_server | 服务器配置表 | ✅ |
| p_onlinemall | 在线商城配置表 | ✅ |
| p_transfer | 转职配置表 | ✅ |
| p_taskset | 任务配置表 | ✅ |
| p_talkset | 对话配置表 | ✅ |
| p_skin | 皮肤配置表 | ✅ |
| p_skill_play_time | 技能播放时间配置表 | ✅ |
| p_mapbosspos | 地图BOSS位置配置表 | ✅ |

## 数据类型转换

### 时间类型

| MySQL类型 | SQLite类型 | 转换方式 |
|----------|-----------|---------|
| DATETIME | INTEGER | Unix时间戳 |
| TIMESTAMP | INTEGER | Unix时间戳 |
| BIGINT (时间戳) | INTEGER | 直接转换 |

### JSON类型

| MySQL类型 | SQLite类型 | 转换方式 |
|----------|-----------|---------|
| JSON | TEXT | 保留为JSON字符串 |
| JSON对象 | 拆分到独立表 | 反序列化后存储 |

### 数值类型

| MySQL类型 | SQLite类型 | 转换方式 |
|----------|-----------|---------|
| TINYINT | INTEGER | 直接转换 |
| SMALLINT | INTEGER | 直接转换 |
| INT | INTEGER | 直接转换 |
| BIGINT | INTEGER | 直接转换 |
| FLOAT | REAL | 直接转换 |
| DOUBLE | REAL | 直接转换 |
| DECIMAL | REAL | 直接转换 |

### 字符串类型

| MySQL类型 | SQLite类型 | 转换方式 |
|----------|-----------|---------|
| VARCHAR | TEXT | 直接转换 |
| TEXT | TEXT | 直接转换 |
| CHAR | TEXT | 直接转换 |

## 迁移示例

### 示例输出

```
============================================================
MySQL 到 SQLite 数据迁移工具
============================================================

连接MySQL数据库...
✓ MySQL连接成功
连接SQLite数据库...
✓ SQLite连接成功

开始数据迁移...
------------------------------------------------------------
迁移账户数据...
✓ 迁移了 100 条账户数据
迁移角色数据...
✓ 迁移了 250 条角色数据
迁移成就配置数据...
✓ 迁移了 50 条成就配置数据
迁移成就记录数据...
✓ 迁移了 1250 条成就记录数据
迁移配置表数据...
  ✓ 迁移了 100 条 p_exp 数据
  ✓ 迁移了 500 条 p_equip 数据
  ✓ 迁移了 300 条 p_consume 数据
  ✓ 迁移了 200 条 p_skill 数据
  ✓ 迁移了 150 条 p_npc 数据
  ✓ 迁移了 50 条 p_gamemap 数据
  ✓ 迁移了 30 条 p_dungeon 数据
  ✓ 迁移了 100 条 p_petexp 数据
  ✓ 迁移了 80 条 p_itemshop 数据
  ✓ 迁移了 10 条 p_server 数据
  ✓ 迁移了 100 条 p_onlinemall 数据
  ✓ 迁移了 20 条 p_transfer 数据
  ✓ 迁移了 150 条 p_taskset 数据
  ✓ 迁移了 200 条 p_talkset 数据
  ✓ 迁移了 50 条 p_skin 数据
  ✓ 迁移了 200 条 p_skill_play_time 数据
  ✓ 迁移了 100 条 p_mapbosspos 数据
------------------------------------------------------------

数据迁移完成!

验证迁移数据...
  账户数据: 100 条
  角色数据: 250 条
  成就配置: 50 条
  成就记录: 1250 条
  配置表: 17 个

============================================================
迁移统计:
  账户数据: 100 条
  角色数据: 250 条
  成就配置: 50 条
  成就记录: 1250 条
  配置数据: 2440 条
============================================================

数据库连接已关闭
```

## 常见问题

### Q1: MySQL连接失败

**错误信息**: `✗ MySQL连接失败: Access denied for user 'root'@'localhost'`

**解决方法**: 检查MySQL用户名和密码是否正确

```bash
# 测试MySQL连接
mysql -h 127.0.0.1 -P 3306 -u root -p
```

### Q2: SQLite数据库不存在

**错误信息**: `✗ SQLite连接失败: unable to open database file`

**解决方法**: 先创建SQLite数据库

```bash
sqlite3 game.db < store/migration/sqlite/schema.sql
```

### Q3: 数据迁移失败

**错误信息**: `✗ 迁移账户数据失败: ...`

**解决方法**: 
1. 检查MySQL表结构是否与预期一致
2. 查看详细的错误信息
3. 检查MySQL和SQLite的表结构是否匹配

### Q4: 时间戳转换错误

**错误信息**: 时间戳转换失败

**解决方法**: 检查MySQL中的时间字段格式是否正确

```sql
-- 查看时间字段格式
SELECT createTime FROM t_account LIMIT 1;
```

### Q5: JSON字段解析失败

**错误信息**: JSON解析失败

**解决方法**: 检查MySQL中的JSON字段格式是否正确

```sql
-- 查看JSON字段格式
SELECT pos FROM t_role LIMIT 1;
```

## 性能优化

### 批量插入

脚本已经实现了批量插入，默认批处理大小为1000条。

### 事务处理

脚本使用事务确保数据一致性，如果迁移失败会自动回滚。

### 索引优化

迁移完成后，建议创建索引以提高查询性能：

```sql
-- 创建索引
CREATE INDEX idx_account_openid ON account(openid);
CREATE INDEX idx_role_account ON role(account_id);
CREATE INDEX idx_achievement_record_role ON t_achievement_record(role_id);
CREATE INDEX idx_achievement_record_achievement ON t_achievement_record(achievement_id);
```

## 回滚方案

如果迁移失败，可以回滚：

```bash
# 删除SQLite数据库
rm game.db

# 重新创建
sqlite3 game.db < store/migration/sqlite/schema.sql

# 重新执行迁移
python scripts/migrate_data.py
```

## 注意事项

1. **数据备份**: 迁移前务必备份MySQL数据
   ```bash
   mysqldump -h 127.0.0.1 -P 3306 -u root -p game > game_backup.sql
   ```

2. **停服迁移**: 建议在停服期间进行迁移，避免数据不一致

3. **数据验证**: 迁移后务必验证数据完整性

4. **性能测试**: 大数据量迁移时进行性能测试

5. **日志记录**: 记录迁移过程中的错误和警告

6. **权限检查**: 确保MySQL用户有足够的权限读取数据

## 扩展功能

### 添加新的表迁移

如果需要迁移其他表，可以在 `migrate_data.py` 中添加相应的迁移函数：

```python
def migrate_custom_table(mysql_conn, sqlite_conn):
    """迁移自定义表"""
    print("迁移自定义表数据...")
    
    mysql_cursor = mysql_conn.cursor(dictionary=True)
    sqlite_cursor = sqlite_conn.cursor()
    
    try:
        mysql_cursor.execute("SELECT * FROM t_custom_table")
        rows = mysql_cursor.fetchall()
        
        for row in rows:
            sql = """
            INSERT OR REPLACE INTO t_custom_table 
            (field1, field2, field3)
            VALUES (?, ?, ?)
            """
            sqlite_cursor.execute(sql, (
                row.get('field1', ''),
                row.get('field2', 0),
                row.get('field3', 0)
            ))
        
        sqlite_conn.commit()
        print(f"✓ 迁移了 {len(rows)} 条自定义表数据")
        return len(rows)
        
    except Exception as e:
        print(f"✗ 迁移自定义表数据失败: {e}")
        sqlite_conn.rollback()
        return 0
    finally:
        mysql_cursor.close()
```

然后在 `main()` 函数中调用：

```python
def main():
    # ... 其他迁移代码 ...
    
    custom_count = migrate_custom_table(mysql_conn, sqlite_conn)
    
    # ... 其他代码 ...
```

## 技术支持

如有问题，请参考：

- 数据库迁移文档: `devdoc/06_go服务端开发/数据库迁移文档.md`
- SQLite Schema: `store/migration/sqlite/schema.sql`
- MySQL Schema: `mysql-db/schema.sql`

## 更新日志

### v1.0.0 (2024-02-13)

- ✅ 初始版本
- ✅ 支持账户、角色、成就数据迁移
- ✅ 支持配置表迁移
- ✅ 支持数据类型自动转换
- ✅ 支持数据完整性验证
