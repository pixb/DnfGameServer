# DnfGameServer 项目文档

## 项目概述

DNF（地下城与勇士）游戏服务器，基于 Spring Boot 1.5.14 + Apache Mina + Nutz + MySQL 开发。

## 技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Java | 8 | 开发语言 |
| Spring Boot | 1.5.14 | 应用框架 |
| Apache Mina | 2.0.19 | 网络通信 |
| Nutz | 1.r.66 | ORM 框架 |
| MySQL | 5.7 | 数据库 |
| Druid | 1.1.9 | 连接池 |
| Lombok | 1.18.30 | 代码简化 |
| FastJSON | 1.2.47 | JSON 处理 |

## 项目结构

```
DnfGameServer/
├── src/
│   ├── main/
│   │   ├── java/com/dnfm/
│   │   │   ├── game/           # 游戏核心模块
│   │   │   ├── cross/          # 跨服模块
│   │   │   ├── mina/           # 网络通信模块
│   │   │   ├── common/         # 公共模块
│   │   │   └── logs/           # 日志模块
│   │   └── resources/        # 配置文件
│   └── test/                 # 测试代码
├── mysql-db/                 # 数据库配置
│   ├── docker-compose.yaml  # Docker Compose 配置
│   ├── init.sql          # 数据库初始化脚本
│   ├── start-mysql.sh    # 启动 MySQL 脚本
│   └── stop-mysql.sh     # 停止 MySQL 脚本
├── devdoc/                   # 开发文档
│   └── database-tables.md # 数据库表结构
├── build.sh                  # 构建脚本
├── start.sh                  # 启动脚本
└── pom.xml                   # Maven 配置
```

## 快速开始

### 1. 环境要求

- Java 8 (Amazon Corretto 8 推荐)
- Maven 3.x
- Docker & Docker Compose
- MySQL 5.7+

### 2. 启动数据库

```bash
cd mysql-db
./start-mysql.sh
```

### 3. 构建项目

```bash
./build.sh
```

### 4. 启动服务器

```bash
./start.sh
```

## 协议迁移

本项目正在进行从 JProtobuf 到标准 Protobuf 的协议迁移工作。

### 迁移文档

- [迁移流程规划](./03_迁移/01_迁移流程规划.md) - 完整的迁移流程说明
- [重新迁移说明](./03_迁移/02_重新迁移.md) - 迁移系统重构说明
- [迁移系统重构记录](./03_迁移/04_记录重构迁移系统记录.md) - 迁移系统详细记录

### 迁移状态

当前已完成 2 个批次的迁移：

- **Batch 01**: AUTH_LOGIN 模块 (16 个消息)
- **Batch 02**: CHARACTER 模块 (5 个消息)

### 迁移工具

- `jprotobuf_scanner.py` - 扫描 JProtobuf 消息
- `mapping_analyzer.py` - 分析映射关系
- `generate_go_tests.py` - 生成 Go 测试用例
- `universal_query.py` - 查询迁移状态

更多详细信息请参考 [迁移流程规划文档](./03_迁移/01_迁移流程规划.md)。

## 数据库配置

### 数据库列表

| 数据库名 | 用途 |
|---------|------|
| game | 主游戏数据 |
| login | 登录数据 |
| game_kuafu | 跨服数据 |

### 连接配置

```properties
# 主数据库
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/game
spring.datasource.username=root
spring.datasource.password=123456

# 登录数据库
custom.datasource.adb.url=jdbc:mysql://127.0.0.1:3306/login
custom.datasource.adb.username=root
custom.datasource.adb.password=123456

# 跨服数据库
custom.datasource.kuafu.url=jdbc:mysql://127.0.0.1:3306/game_kuafu
custom.datasource.kuafu.username=root
custom.datasource.kuafu.password=123456
```

## 端口配置

| 服务 | 端口 | 说明 |
|------|------|------|
| Web 服务 | 20001 | HTTP 接口 |
| 游戏服务 | 18888 | 游戏客户端连接 |

## 核心功能模块

### 1. 角色系统
- 角色创建/删除
- 角色属性管理
- 职业转换
- 技能系统

### 2. 背包系统
- 物品管理
- 装备系统
- 仓库系统
- 金钱系统

### 3. 副本系统
- 副本匹配
- 组队系统
- 副本奖励
- 难度系统

### 4. 社交系统
- 好友系统
- 聊天系统
- 公会系统

### 5. 交易系统
- 拍卖系统
- 商城系统
- 充值系统

### 6. 跨服系统
- 跨服匹配
- 跨服对战
- 跨服聊天

## 开发指南

### 1. 代码规范

- 使用 Lombok 简化代码
- 遵循 RESTful API 设计
- 使用 Nutz 注解定义数据模型

### 2. 数据库操作

```java
// 查询
Role role = Db4PlayerService.getInstance().fetch(roleId);

// 保存
role.save();

// 批量操作
Db4PlayerService.getInstance().insert(roles);
```

### 3. 网络通信

```java
// 发送消息
Session session = SessionManager.INSTANCE.getSession(roleId);
session.write(message);

// 处理消息
@MessageHandler
public void handle(CReqLogin msg) {
    // 处理登录逻辑
}
```

## 部署说明

### 1. 本地开发

使用 Docker Compose 启动 MySQL，直接运行 JAR 包。

### 2. 生产部署

1. 配置生产数据库
2. 修改配置文件
3. 构建生产包
4. 部署到服务器
5. 使用 systemd 或 supervisor 管理进程

## 性能优化

### 1. JVM 参数

```bash
-Xms512m              # 初始堆内存
-Xmx2g                # 最大堆内存
-XX:+UseG1GC          # G1 垃圾收集器
-XX:MaxGCPauseMillis=200  # 最大 GC 暂停
```

### 2. 数据库优化

- 使用连接池（Druid）
- 添加必要索引
- 定期清理过期数据

### 3. 网络优化

- 使用 NIO 通信
- 配置合理的缓冲区大小
- 启用 TCP No-Delay

## 监控与日志

### 1. 日志配置

使用 Logback 配置日志级别和输出。

### 2. 监控指标

- Spring Boot Actuator
- Druid 监控
- 自定义监控接口

## 常见问题

### 1. 数据库连接失败

检查 MySQL 服务是否启动，配置是否正确。

### 2. 端口被占用

检查 20001 和 18888 端口是否被占用。

### 3. 内存不足

调整 JVM 内存参数，增加堆内存大小。

### 4. 构建失败

确保使用 Java 8 环境，检查依赖是否完整。

## 技术支持

- Spring Boot 文档：https://docs.spring.io/spring-boot/docs/1.5.14.RELEASE/reference/htmlsingle/
- Nutz 文档：http://www.nutzam.com/
- Apache Mina 文档：https://mina.apache.org/

## 更新日志

- 2026-02-06: 初始化项目，添加 Docker Compose 支持
- 2026-02-06: 创建数据库表结构文档
- 2026-02-06: 优化启动脚本
