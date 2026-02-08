# DNF Go Server 迁移开发文档

## 1. 项目概述

### 1.1 迁移目标
将现有的 Java + Spring Boot + Apache MINA 游戏服务器迁移到 Go 语言版本，保持原有功能不变，提升性能和可维护性。

### 1.2 技术栈对比

| 功能模块 | Java 版本 | Go 版本 |
|---------|----------|---------|
| 框架 | Spring Boot 1.5.14 | 纯 Go + 轻量级框架 |
| 网络层 | Apache MINA 2.0.19 | 自定义 TCP Server (net包) |
| 数据库 | MySQL + Druid + Nutz ORM | MySQL + GORM |
| 缓存 | Guava Cache | go-cache / bigcache |
| 消息队列 | 内部实现 | 内部实现 |
| 配置管理 | Spring Properties | Viper |
| 日志 | SLF4J + Logback | Zap |
| 序列化 | Protobuf (jprotobuf) | Protobuf (官方库) |

## 2. 架构设计

### 2.1 整体架构
```
┌─────────────────────────────────────────────────────────────┐
│                     DNF Go Server                           │
├──────────────┬──────────────┬──────────────┬────────────────┤
│   HTTP API   │  TCP Server  │   Database   │    Cache       │
│   (Echo)     │  (自定义)     │   (GORM)     │  (go-cache)    │
└──────────────┴──────────────┴──────────────┴────────────────┘
```

### 2.2 目录结构
```
dnf-go-server/
├── cmd/
│   └── server/           # 主程序入口
├── internal/
│   ├── server/          # 服务器核心
│   │   ├── tcp/         # TCP服务器
│   │   └── http/        # HTTP服务器
│   ├── network/         # 网络层
│   │   ├── codec/       # 编解码器
│   │   ├── session/     # 会话管理
│   │   ├── message/     # 消息处理
│   │   └── dispatcher/  # 消息分发
│   ├── game/            # 游戏逻辑
│   │   ├── player/      # 玩家模块
│   │   ├── role/        # 角色模块
│   │   ├── bag/         # 背包模块
│   │   ├── login/       # 登录模块
│   │   └── ...          # 其他模块
│   ├── db/              # 数据库层
│   │   ├── models/      # 数据模型
│   │   └── dao/         # 数据访问
│   └── utils/           # 工具函数
├── pkg/                 # 公共包
├── configs/             # 配置文件
├── scripts/             # 脚本
└── docs/                # 文档
    ├── migration/       # 迁移文档
    └── problems/        # 问题记录
```

## 3. 模块设计

### 3.1 网络层 (替代 MINA)

#### 3.1.1 核心组件
- **TCPServer**: TCP服务器主类
- **SessionManager**: 会话管理 (替代 MINA Session)
- **MessageCodec**: 消息编解码 (替代 ProtocolCodecFilter)
- **MessageDispatcher**: 消息分发器
- **ConnectionHandler**: 连接处理器

#### 3.1.2 消息处理流程
```
客户端连接 → Session创建 → 消息解码 → 消息分发 → 业务处理 → 消息编码 → 发送响应
```

### 3.2 数据库层 (替代 Nutz ORM)

#### 3.2.1 技术选型
- **GORM**: ORM框架
- **连接池**: 使用GORM自带连接池

#### 3.2.2 主要模型
- Account (账户)
- Role (角色)
- PlayerProfile (玩家资料)
- 各种背包物品表

### 3.3 缓存层 (替代 Guava Cache)

#### 3.3.1 技术选型
- **go-cache**: 内存缓存
- 可选 **bigcache**: 高性能缓存

#### 3.3.2 缓存策略
- 玩家数据缓存 (带TTL)
- 会话缓存
- 配置缓存

## 4. 迁移计划

### 4.1 第一阶段: 基础设施
- [x] 项目结构搭建
- [ ] 配置文件管理 (Viper)
- [ ] 日志系统 (Zap)
- [ ] 数据库连接 (GORM)
- [ ] Protobuf定义迁移

### 4.2 第二阶段: 网络层
- [ ] TCP Server实现
- [ ] 会话管理
- [ ] 消息编解码
- [ ] 消息分发器
- [ ] 心跳检测

### 4.3 第三阶段: 游戏逻辑
- [ ] 登录模块
- [ ] 角色模块
- [ ] 背包模块
- [ ] 玩家管理
- [ ] 场景管理

### 4.4 第四阶段: HTTP API
- [ ] Echo框架集成
- [ ] GM接口
- [ ] 支付接口
- [ ] 服务器管理接口

## 5. 关键问题与解决方案

### 5.1 Java → Go 概念映射

| Java概念 | Go实现方式 |
|---------|-----------|
| Spring @Autowired | 依赖注入手动管理/使用wire |
| ConcurrentHashMap | sync.Map 或 map+RWLock |
| AtomicInteger | sync/atomic |
| ThreadPool | Goroutine + Channel |
| Enum | 常量 + iota |
| Lombok @Data | struct tags |

### 5.2 并发模型差异
- **Java**: Thread-based + 线程池
- **Go**: Goroutine + Channel (CSP模型)

### 5.3 热更新支持
- Java支持类热替换
- Go需要 graceful restart 或 插件系统

## 6. 性能考虑

### 6.1 优化点
1. 使用 sync.Pool 复用对象
2. 避免内存分配 (零拷贝)
3. 使用 atomic 替代锁 (计数器场景)
4. 合理设置 GOMAXPROCS

### 6.2 监控指标
- Goroutine数量
- 内存使用
- 连接数
- 消息处理延迟

## 7. 开发规范

### 7.1 命名规范
- 包名: 小写，简短
- 结构体: PascalCase
- 方法: PascalCase (导出) / camelCase (私有)
- 常量: UPPER_SNAKE_CASE

### 7.2 错误处理
- 显式错误检查
- 使用 errors.Wrap 添加上下文
- 定义业务错误码

### 7.3 日志规范
- 使用结构化日志
- 关键路径必打日志
- 避免在循环中高频打日志

## 8. 测试策略

### 8.1 单元测试
- 使用 testify 框架
- Mock 外部依赖

### 8.2 集成测试
- Docker Compose 测试环境
- 数据库隔离

### 8.3 压力测试
- 使用 vegeta 或 k6
- 对比 Java 版本性能

## 9. 部署方案

### 9.1 构建
```bash
# 本地构建
go build -o bin/dnf-server ./cmd/server

# Docker构建
docker build -t dnf-go-server .
```

### 9.2 运行
```bash
# 直接运行
./bin/dnf-server -config=configs/config.yaml

# Docker运行
docker run -d -p 8080:8080 -p 9000:9000 dnf-go-server
```

## 10. 文档维护

所有迁移过程中遇到的问题和解决方案都记录在 `docs/problems/` 目录下，按日期和模块分类。

---

**创建日期**: 2026-02-06  
**版本**: v1.0  
**状态**: 规划中
