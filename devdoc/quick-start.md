# DnfGameServer 快速启动指南

## 前置条件

- ✅ 已安装 Java 8
- ✅ 已安装 Docker 和 Docker Compose
- ✅ 已配置好网络（如果使用 Docker 拉取镜像）

## 一键启动流程

### 步骤 1：启动 MySQL 数据库

```bash
cd mysql-db
./start-mysql.sh
```

**等待时间**：约 10-15 秒

**验证启动**：
```bash
docker ps | grep dnf-mysql
```

### 步骤 2：构建项目（首次需要）

```bash
./build.sh
```

**构建时间**：约 1-2 分钟

### 步骤 3：启动游戏服务器

```bash
./start.sh
```

**启动时间**：约 5-10 秒

**验证启动**：
```bash
# 检查 Web 服务
curl http://localhost:20001

# 检查游戏服务
telnet localhost 18888
```

## 常用命令

### 停止 MySQL

```bash
cd mysql-db
./stop-mysql.sh
```

### 停止游戏服务器

```bash
# 在启动终端按 Ctrl+C
```

### 重启所有服务

```bash
cd mysql-db
./stop-mysql.sh
./start-mysql.sh
cd ..
./start.sh
```

### 查看日志

```bash
# 游戏服务器日志
tail -f logs/application.log

# MySQL 日志
docker logs -f dnf-mysql
```

### 查看数据库

```bash
# 连接到 MySQL
mysql -h 127.0.0.1 -P 3306 -u root -p123456

# 切换数据库
USE game;
USE login;
USE game_kuafu;

# 查看表
SHOW TABLES;

# 查看数据
SELECT * FROM t_role LIMIT 10;
```

## 端口说明

| 服务 | 端口 | 协议 | 用途 |
|------|------|------|------|
| Web 服务 | 20001 | HTTP | 管理接口、API |
| 游戏服务 | 18888 | TCP | 游戏客户端连接 |
| MySQL | 3306 | TCP | 数据库连接 |

## 配置文件位置

| 文件 | 路径 | 说明 |
|------|------|------|
| 主配置 | src/main/resources/application.properties | 主配置文件 |
| 跨服配置 | src/main/resources/application-kuafu.properties | 跨服模式配置 |
| 合服配置 | src/main/resources/application-merge.properties | 合服模式配置 |
| 日志配置 | src/main/resources/logback.xml | 日志配置 |

## 故障排查

### 问题 1：MySQL 启动失败

**症状**：容器启动后立即退出

**解决方案**：
```bash
# 查看日志
docker logs dnf-mysql

# 检查端口占用
lsof -i :3306

# 删除旧容器重新创建
cd mysql-db
docker-compose down
docker-compose up -d
```

### 问题 2：游戏服务器启动失败

**症状**：启动时报错数据库连接失败

**解决方案**：
```bash
# 1. 确认 MySQL 正在运行
docker ps | grep dnf-mysql

# 2. 测试连接
mysql -h 127.0.0.1 -P 3306 -u root -p123456 -e "SELECT 1;"

# 3. 检查配置
cat src/main/resources/application.properties | grep datasource
```

### 问题 3：端口被占用

**症状**：启动时报端口已被占用

**解决方案**：
```bash
# 查找占用进程
lsof -i :20001
lsof -i :18888

# 杀死进程
kill -9 <PID>

# 或修改配置文件中的端口
vim src/main/resources/application.properties
```

### 问题 4：内存不足

**症状**：启动后频繁 GC 或 OOM

**解决方案**：
```bash
# 修改 start.sh 中的 JVM 参数
# 将 -Xmx2g 改为 -Xmx4g
```

## 开发建议

### 1. 使用 IDE

推荐使用 IntelliJ IDEA 或 Eclipse 导入项目。

### 2. 热部署

使用 Spring Boot DevTools 实现热部署。

### 3. 调试模式

在 IDE 中直接运行 `GameServerStartup` 主类进行调试。

### 4. 日志级别

修改 `application.properties` 中的日志级别：

```properties
logging.level.root=debug
logging.level.com.dnfm=debug
```

## 数据备份

### 备份数据卷

```bash
# 创建备份
docker run --rm -v mysql-data:/data -v $(pwd):/backup alpine tar czf /backup/mysql-$(date +%Y%m%d).tar.gz /data
```

### 恢复数据

```bash
# 恢复备份
docker run --rm -v mysql-data:/data -v $(pwd):/backup alpine tar xzf /backup/mysql-20260206.tar.gz -C /
```

## 性能测试

### 压力测试

```bash
# 使用 Apache Bench
ab -n 1000 -c 10 http://localhost:20001/api/test

# 使用 JMeter
# 创建测试计划进行压测
```

### 数据库性能

```bash
# 查看慢查询
docker exec dnf-mysql mysql -uroot -p123456 -e "SHOW VARIABLES LIKE 'slow_query_log';"

# 启用慢查询日志
docker exec dnf-mysql mysql -uroot -p123456 -e "SET GLOBAL slow_query_log = 'ON';"
```

## 下一步

启动成功后，可以：

1. 访问 Web 管理接口：http://localhost:20001
2. 连接游戏客户端到：localhost:18888
3. 查看数据库表结构：[database-tables.md](./database-tables.md)
4. 查看完整文档：[README.md](./README.md)

## 技术支持

- Spring Boot 文档：https://docs.spring.io/spring-boot/docs/1.5.14.RELEASE/
- Nutz 文档：http://www.nutzam.com/
- Apache Mina 文档：https://mina.apache.org/
- MySQL 文档：https://dev.mysql.com/doc/
