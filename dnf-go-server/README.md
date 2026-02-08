# DNF Go Server

DNF 游戏服务器的 Go 语言实现版本，采用现代化的 Go 架构设计，从 Java + Spring Boot + Apache MINA + jprotobuf 迁移而来。

## 架构概览

本项目采用分层的 Go 架构设计：

```
┌─────────────────────────────────────────────────────────────────┐
│                        DNF Go Server                            │
├─────────────────────────────────────────────────────────────────┤
│  Main Layer                                                     │
│  ├── Cobra CLI (serve, migrate, version)                       │
│  └── Viper Configuration                                       │
├─────────────────────────────────────────────────────────────────┤
│  Server Layer                                                   │
│  ├── Echo + cmux (单端口多协议)                                 │
│  ├── gRPC-Gateway (REST API)                                   │
│  ├── Connect RPC (浏览器客户端)                                │
│  └── 原生 gRPC (HTTP/2)                                        │
├─────────────────────────────────────────────────────────────────┤
│  Store Layer                                                    │
│  ├── Driver Interface (MySQL/SQLite/PostgreSQL)               │
│  ├── Cache Layer (go-cache)                                    │
│  └── Migration System                                          │
├─────────────────────────────────────────────────────────────────┤
│  Legacy Layer (保持运行)                                        │
│  ├── TCP Server (替代 Apache MINA)                             │
│  ├── Message Dispatcher                                        │
│  └── Game Handlers                                             │
└─────────────────────────────────────────────────────────────────┘
```

## 迁移状态

**当前状态**: 新架构基础设施已完成 (约75%)

### 已完成 ✅
- ProtoBuf 协议定义 (100+ 消息类型)
- Store层架构 (Driver接口、缓存、迁移)
- Server层架构 (Echo + cmux + 三协议支持)
- Main层架构 (Cobra CLI + Viper)
- 认证模块 (JWT、PAT支持)
- 数据库模型 (18张表)
- TCP网络层框架 (替代 MINA)
- 消息编解码器 (替代 jprotobuf)

### 进行中 ⏳
- Connect RPC拦截器链完善
- 剩余Store方法实现
- 服务层完整实现

详细进度见: [docs/architecture/05-migration-plan.md](docs/architecture/05-migration-plan.md)

## 项目结构

```
dnf-go-server/
├── cmd/
│   └── server/              # 主程序入口
│       ├── main.go          # 入口 (5行)
│       └── cmd/             # Cobra子命令
│           ├── root.go      # 根命令
│           ├── serve.go     # serve子命令
│           ├── migrate.go   # migrate子命令
│           └── version.go   # version子命令
├── server/                  # 服务器核心 (新架构)
│   ├── server.go            # Echo + cmux + gRPC
│   ├── auth/                # 认证模块
│   │   ├── authenticator.go
│   │   ├── token.go
│   │   └── extract.go
│   └── router/api/v1/       # API v1
│       ├── v1.go            # 服务注册
│       ├── acl_config.go    # 公开端点
│       ├── connect_interceptors.go
│       └── services.go
├── store/                   # 数据层 (新架构)
│   ├── driver.go            # Driver接口
│   ├── store.go             # Store包装器 (带缓存)
│   ├── cache.go             # 缓存实现
│   ├── *.go                 # 模型定义
│   ├── db/
│   │   ├── db.go            # Driver工厂
│   │   └── mysql/           # MySQL实现
│   └── migration/           # 数据库迁移
│       └── mysql/
│           └── LATEST.sql
├── internal/                # 内部模块
│   ├── profile/             # 配置结构
│   │   └── profile.go
│   ├── server/http/         # HTTP服务器 (旧)
│   ├── network/             # TCP网络层 (旧)
│   ├── game/                # 游戏逻辑 (旧)
│   ├── db/                  # GORM模型 (旧)
│   └── utils/               # 工具包
├── proto/                   # ProtoBuf协议
│   ├── dnf/v1/              # 协议文件
│   └── gen/                 # 生成代码
├── configs/                 # 配置文件
│   ├── config.yaml          # 开发配置
│   ├── config.prod.yaml     # 生产配置
│   └── config.docker.yaml   # Docker配置
├── scripts/                 # 脚本
│   └── build.sh             # 构建脚本
├── docs/                    # 文档
│   ├── README.md
│   ├── MIGRATION_PROGRESS.md
│   └── architecture/        # 架构文档
│       ├── 01-overview.md
│       ├── 02-store-layer-design.md
│       ├── 03-server-layer-design.md
│       ├── 04-main-layer-design.md
│       └── 05-migration-plan.md
├── Makefile                 # 构建命令
├── Dockerfile               # Docker镜像
├── docker-compose.yml       # Docker编排
└── .env.example             # 环境变量示例
```

## 技术栈对比

| 功能 | Java (原) | Go (新) |
|------|-----------|---------|
| CLI框架 | - | Cobra |
| 配置管理 | Spring Properties | Viper |
| HTTP框架 | Spring Boot | Echo v4 |
| RPC框架 | - | Connect RPC + gRPC-Gateway |
| 端口复用 | - | cmux |
| 网络层 | Apache MINA 2.0.19 | 原生 net + 自定义 |
| 序列化 | jprotobuf | 官方 protobuf |
| ORM | Nutz | GORM + Driver模式 |
| 缓存 | Guava Cache | go-cache |
| 日志 | SLF4J | Zap |
| 数据库 | MySQL 5.7 | MySQL 5.7 / SQLite (开发) |

## 快速开始

### 1. 环境要求

- Go 1.24+
- **SQLite** - 用于开发/测试 (无需安装，内置)
- **MySQL 5.7+** - 用于生产 (可选)
- buf (用于生成 protobuf)
- Docker & Docker Compose (可选)

### 2. 安装依赖

```bash
cd dnf-go-server
go mod tidy
```

### 3. 开发环境运行 (SQLite) - 推荐

开发环境默认使用 SQLite，无需安装 MySQL，数据库文件保存在 `data/dnf_game.db`：

```bash
# 查看所有可用命令
make help

# 启动开发服务器 (SQLite)
make dev

# 重置 SQLite 数据库
make dev-reset

# 运行测试
make dev-test
```

### 4. 生产环境运行 (MySQL)

生产环境使用 MySQL 数据库：

```bash
# 复制环境变量示例
cp .env.example .env
# 编辑 .env 配置数据库连接

# 构建
make build

# 运行数据库迁移
make migrate

# 启动服务器
make run
```

### 5. 常用命令

```bash
# 显示版本
make version

# 查看项目信息
make info

# 清理构建文件和数据库
make clean
```

### 5. 使用 Docker

```bash
# 启动所有服务 (MySQL + Server)
docker-compose up -d

# 查看日志
docker-compose logs -f server

# 停止服务
docker-compose down
```

## 架构设计文档

详细设计文档位于 `docs/architecture/`：

1. [01-overview.md](docs/architecture/01-overview.md) - 重构概述
2. [02-store-layer-design.md](docs/architecture/02-store-layer-design.md) - Store层设计
3. [03-server-layer-design.md](docs/architecture/03-server-layer-design.md) - Server层设计
4. [04-main-layer-design.md](docs/architecture/04-main-layer-design.md) - Main层设计
5. [05-migration-plan.md](docs/architecture/05-migration-plan.md) - 迁移执行计划

## 多协议支持

服务器同时支持三种协议，共享同一个端口 (8081)：

| 协议 | 端点 | 用途 |
|------|------|------|
| REST API | `/api/v1/*` | 第三方集成、管理后台 |
| Connect RPC | `/dnf.api.v1.*` | Web客户端、浏览器应用 |
| gRPC | HTTP/2 | 服务间通信、高性能场景 |

## 开发指南

### 添加新的 API 端点

1. **定义 Proto 服务** (proto/dnf/v1/*.proto)
2. **生成代码** (`cd proto && buf generate`)
3. **实现服务** (server/router/api/v1/*_service.go)
4. **注册服务** (server/router/api/v1/v1.go)

### 添加新的数据模型

1. **定义模型** (store/*.go)
2. **更新 Driver 接口** (store/driver.go)
3. **实现 CRUD** (store/db/mysql/*.go)
4. **添加缓存** (store/store.go)
5. **创建迁移** (store/migration/mysql/)

## 性能优化

1. 使用 `sync.Pool` 复用对象
2. 避免内存分配 (零拷贝)
3. 使用 atomic 替代锁 (计数器场景)
4. 合理设置 GOMAXPROCS
5. 启用数据库连接池
6. 使用缓存层减少数据库查询

## 监控指标

- Goroutine 数量
- 内存使用
- 数据库连接池状态
- 缓存命中率
- API 响应时间

## 许可证

MIT
