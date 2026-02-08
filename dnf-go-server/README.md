# DNF Go Server

DNF 游戏服务器的 Go 语言实现版本，从 Java + Spring Boot + Apache MINA + jprotobuf 迁移而来。

## 迁移状态

**当前状态**: 基础设施已完成，游戏逻辑待实现

- ✅ ProtoBuf 协议定义 (100+ 消息类型)
- ✅ TCP 服务器框架 (替代 Apache MINA)
- ✅ 消息编解码器 (替代 jprotobuf)
- ✅ 消息分发器
- ✅ 配置系统 (替代 Spring Properties)
- ✅ 日志系统 (替代 SLF4J)
- ✅ 数据库层 (GORM 替代 Nutz ORM)
- ✅ 登录认证处理器 (示例)
- ⏳ 其他游戏逻辑处理器

详细进度见: [MIGRATION_PROGRESS.md](docs/MIGRATION_PROGRESS.md)

## 项目结构

```
dnf-go-server/
├── cmd/
│   └── server/              # 主程序入口
│       └── main.go
├── internal/
│   ├── server/
│   │   └── http/            # HTTP服务器 (Echo框架)
│   ├── network/             # TCP网络层 (替代MINA)
│   │   ├── server.go        # TCP服务器
│   │   ├── session.go       # 会话管理
│   │   ├── codec.go         # ProtoBuf编解码器
│   │   ├── dispatcher.go    # 消息分发器
│   │   └── message_registry.go  # 消息注册表
│   ├── game/                # 游戏逻辑
│   │   ├── player_service.go
│   │   └── handlers/        # 消息处理器
│   │       ├── auth.go      # 认证处理器
│   │       └── register.go  # 处理器注册
│   ├── db/                  # 数据库层
│   │   ├── db.go
│   │   └── models/
│   │       └── base.go
│   └── utils/               # 工具包
│       ├── config/          # 配置管理 (Viper)
│       └── logger/          # 日志 (Zap)
├── proto/                   # ProtoBuf协议定义
│   ├── dnf/v1/              # 协议文件
│   │   ├── auth.proto       # 认证协议
│   │   ├── role.proto       # 角色协议
│   │   ├── item.proto       # 物品协议
│   │   ├── dungeon.proto    # 副本协议
│   │   ├── chat.proto       # 聊天协议
│   │   ├── shop.proto       # 商店协议
│   │   ├── quest.proto      # 任务协议
│   │   ├── guild.proto      # 公会协议
│   │   └── service.proto    # gRPC服务定义
│   └── gen/                 # 生成的Go代码
│       └── dnf/v1/
├── configs/
│   └── config.yaml          # 配置文件
├── scripts/
│   ├── build.sh             # 构建脚本
│   └── start.sh             # 启动脚本
└── docs/                    # 文档
    ├── README.md            # 架构设计文档
    ├── MIGRATION_PROGRESS.md  # 迁移进度
    └── problems/            # 迁移问题记录
```

## 技术栈

| 功能 | Java (原) | Go (新) |
|------|-----------|---------|
| 网络层 | Apache MINA 2.0.19 | 原生 net + 自定义 |
| HTTP框架 | Spring Boot | Echo v4 |
| 序列化 | jprotobuf | 官方 protobuf |
| ORM | Nutz | GORM |
| 缓存 | Guava Cache | go-cache |
| 配置 | Spring Properties | Viper |
| 日志 | SLF4J | Zap |
| 数据库 | MySQL 5.7 | MySQL 5.7 |

## 快速开始

### 1. 环境要求

- Go 1.21+
- MySQL 5.7+
- buf (用于生成 protobuf)

### 2. 安装依赖

```bash
cd dnf-go-server
go mod tidy
```

### 3. 生成 ProtoBuf 代码

```bash
cd proto
buf generate
```

### 4. 配置数据库

编辑 `configs/config.yaml`:

```yaml
database:
  host: "127.0.0.1"
  port: 3306
  username: "root"
  password: "your_password"
  database: "dnf_game"
```

### 5. 构建

```bash
./scripts/build.sh
```

### 6. 运行

```bash
./scripts/start.sh
# 或指定配置文件
./scripts/start.sh configs/config.yaml
```

## 协议格式

### 消息结构

```
[2字节长度][2字节模块][2字节命令][Protobuf消息体]
```

- 长度: 大端序 uint16，包含模块+命令+消息体的总长度
- 模块: 大端序 uint16，表示功能模块 (10000-10007)
- 命令: 大端序 uint16，表示具体操作 (0-255)
- 消息体: Protobuf 序列化后的数据

### 模块划分

| 模块ID | 功能 |
|--------|------|
| 10000 | 认证 (登录、创建角色、角色列表、选择角色) |
| 10001 | 角色系统 (属性、技能、疲劳值) |
| 10002 | 背包系统 (物品、装备、货币) |
| 10003 | 副本系统 (进入、退出、复活) |
| 10004 | 聊天系统 (频道、好友) |
| 10005 | 商店系统 (NPC商店、拍卖行、个人商店) |
| 10006 | 任务系统 (接受、完成、奖励) |
| 10007 | 公会系统 (创建、加入、技能) |

## 开发指南

### 添加新的消息处理器

1. 在 `internal/game/handlers/` 下创建处理器文件

```go
// handlers/role.go
package handlers

import (
    dnfv1 "dnf-go-server/proto/gen/dnf/v1"
    "dnf-go-server/internal/network"
    "google.golang.org/protobuf/proto"
)

func GetRoleInfoHandler(session *network.Session, msg proto.Message) {
    req := msg.(*dnfv1.GetRoleInfoRequest)
    // 处理逻辑...
    
    resp := &dnfv1.GetRoleInfoResponse{
        Error: 0,
        // ...
    }
    session.WriteResponse(10001, 1, resp)
}
```

2. 在 `handlers/register.go` 中注册

```go
func RegisterAllHandlers(dispatcher *network.MessageDispatcher) {
    // ...
    dispatcher.RegisterHandler(10001, 0, GetRoleInfoHandler)
}
```

### 添加 ProtoBuf 消息

1. 在 `proto/dnf/v1/` 下编辑对应的 .proto 文件
2. 运行 `buf generate` 生成 Go 代码
3. 在 `message_registry.go` 中注册消息
4. 在处理器中使用新消息

### HTTP API

在 `internal/server/http/server.go` 中添加:

```go
func (s *Server) registerRoutes() {
    s.echo.GET("/api/your-endpoint", s.yourHandler)
}

func (s *Server) yourHandler(c echo.Context) error {
    return c.JSON(http.StatusOK, Success(data))
}
```

## Java vs Go 关键差异

| Java (Spring/MINA) | Go |
|-------------------|-----|
| @MessageMeta(module, cmd) | ProtoCodec.RegisterMessage |
| IoHandler | ConnectionHandler 接口 |
| IoSession | Session 结构体 |
| ProtocolCodecFilter | ProtoCodec |
| ConcurrentHashMap | map + sync.RWMutex |
| ThreadPool | Goroutine |
| @Autowired | 构造函数注入 |
| Spring Properties | Viper |
| SLF4J | Zap |
| Nutz ORM | GORM |

## 测试

```bash
# 运行所有测试
go test ./...

# 运行特定包测试
go test ./internal/network/... -v

# 运行基准测试
go test ./internal/network/... -bench=.
```

## 性能优化

1. 使用 `sync.Pool` 复用对象
2. 避免内存分配 (零拷贝)
3. 使用 atomic 替代锁
4. 合理设置 GOMAXPROCS
5. 启用连接池复用

## 文档

- [架构设计文档](docs/README.md)
- [迁移进度](docs/MIGRATION_PROGRESS.md)
- [迁移问题记录](docs/problems/)

## 许可证

MIT
