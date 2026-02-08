# DnfGameServer MySQL 数据库

## 文件说明

- `docker-compose.yaml` - Docker Compose 配置文件
- `init.sql` - 数据库初始化脚本
- `start-mysql.sh` - 启动 MySQL 容器
- `stop-mysql.sh` - 停止 MySQL 容器

## 使用方法

### 启动 MySQL

```bash
cd mysql-db
./start-mysql.sh
```

### 停止 MySQL

```bash
cd mysql-db
./stop-mysql.sh
```

### 重启 MySQL

```bash
cd mysql-db
./stop-mysql.sh
./start-mysql.sh
```

## 数据库配置

| 配置项 | 值 |
|---------|-----|
| 主机地址 | 127.0.0.1 |
| 端口 | 3306 |
| 用户名 | root |
| 密码 | 123456 |
| 数据库 | game, login, game_kuafu |

## 数据卷

MySQL 数据会持久化到 Docker 卷 `mysql-data`，即使删除容器，数据也不会丢失。

## 连接测试

```bash
# 测试连接
mysql -h 127.0.0.1 -P 3306 -u root -p123456 -e "SHOW DATABASES;"
```

## 注意事项

1. 确保已安装 Docker 和 Docker Compose
2. 首次启动会自动创建三个数据库
3. 数据卷会保留所有数据，谨慎使用 `docker-compose down -v`
4. 修改密码需要同时修改 `docker-compose.yaml` 和项目的 `application.properties`
