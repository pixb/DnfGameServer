# DNF Go Server 重构开发计划

## 1. 项目概述

### 1.1 当前状态
项目已完成基础的Java→Go迁移，包括：
- Protobuf协议定义和生成
- TCP服务器框架(替代Apache MINA)
- 基础游戏逻辑处理器(42个)
- GORM数据库模型
- HTTP服务器(Echo)

### 1.2 重构目标
采用标准Go项目架构模式，提升可维护性和扩展性：
- **Store层**: Driver接口模式，支持多数据库(SQLite/MySQL/PostgreSQL)
- **Server层**: Echo + Connect RPC + gRPC-Gateway + cmux单端口多协议
- **Main层**: Cobra CLI + Viper配置 + 优雅关闭

## 2. 架构对比

### 2.1 当前架构
```
cmd/server/main.go (196行，包含所有初始化逻辑)
├── internal/db/ (GORM直接操作)
├── internal/network/ (自定义TCP)
├── internal/server/http/ (Echo HTTP)
└── internal/game/ (游戏逻辑)
```

**问题**:
- main.go过于臃肿
- 数据库层没有抽象，无法切换数据库
- HTTP和TCP使用不同端口
- 缺少统一的服务层架构
- 配置管理简单，不支持环境变量覆盖

### 2.2 目标架构
```
cmd/server/main.go (50行，仅调用server.Start)
├── server/ (服务器核心)
│   ├── server.go (Echo + cmux + 服务注册)
│   ├── auth/ (认证模块)
│   └── router/api/v1/ (Connect RPC + gRPC-Gateway)
├── store/ (数据层)
│   ├── driver.go (数据库接口)
│   ├── store.go (带缓存的包装器)
│   ├── db/ (具体数据库实现)
│   └── migration/ (数据库迁移)
├── proto/ (协议定义)
│   └── gen/ (生成代码)
└── internal/profile/ (配置结构)
```

## 3. 重构模块

### 3.1 Phase 1: Store层重构 (Driver接口模式)

**目标**: 替换GORM直接操作，实现Driver接口模式

**核心文件**:
1. `store/driver.go` - Driver接口定义
2. `store/store.go` - Store包装器(带缓存)
3. `store/db/db.go` - Driver工厂
4. `store/db/mysql/` - MySQL实现
5. `store/migration/` - 数据库迁移

**模型迁移**:
- Account → store.Account
- Role → store.Role  
- RoleAttributes → store.RoleAttributes
- BagItem → store.BagItem
- 其他所有模型...

**详细文档**: [store-layer-design.md](./store-layer-design.md)

### 3.2 Phase 2: Server层重构 (统一服务层)

**目标**: 实现Connect RPC + gRPC-Gateway + cmux

**核心文件**:
1. `server/server.go` - 服务器主类
2. `server/router/api/v1/v1.go` - 服务注册
3. `server/auth/` - 认证模块
4. `proto/api/v1/` - 服务proto定义

**协议支持**:
- REST API (gRPC-Gateway)
- Connect RPC (浏览器客户端)
- 原生gRPC (HTTP/2)

**详细文档**: [server-layer-design.md](./server-layer-design.md)

### 3.3 Phase 3: Main层重构 (Cobra CLI)

**目标**: 使用Cobra CLI框架，规范化命令行接口

**核心文件**:
1. `cmd/server/main.go` - 使用Cobra
2. `internal/profile/profile.go` - 配置结构
3. 支持子命令: `serve`, `migrate`, `version`

**详细文档**: [main-layer-design.md](./main-layer-design.md)

### 3.4 Phase 4: 迁移执行

**目标**: 逐步迁移现有功能到新架构

**迁移策略**:
1. 保持现有代码运行
2. 并行开发新架构
3. 逐步替换模块
4. 完整测试后切换

**详细文档**: [migration-plan.md](./migration-plan.md)

## 4. 开发顺序

```
Week 1-2: Store层设计文档
Week 3-4: Store层实现
Week 5-6: Server层设计文档
Week 7-8: Server层实现
Week 9-10: Main层重构
Week 11-12: 迁移执行和测试
```

## 5. 文件清单

### 5.1 需要创建的文档
- [x] 01-overview.md (本文档)
- [ ] 02-store-layer-design.md
- [ ] 03-server-layer-design.md
- [ ] 04-main-layer-design.md
- [ ] 05-migration-plan.md

### 5.2 需要创建的代码文件

**Store层** (~15个文件):
- store/driver.go
- store/store.go
- store/cache.go
- store/model.go
- store/account.go
- store/role.go
- store/item.go
- store/db/db.go
- store/db/mysql/db.go
- store/migration/mysql/LATEST.sql
- store/migration/mysql/0.1/*.sql

**Server层** (~10个文件):
- server/server.go
- server/auth/*.go (5个文件)
- server/router/api/v1/v1.go
- server/router/api/v1/acl_config.go
- server/router/api/v1/connect_interceptors.go
- server/router/api/v1/connect_handler.go

**Main层** (~3个文件):
- cmd/server/main.go (重写)
- internal/profile/profile.go

**Proto层** (~5个文件):
- proto/api/v1/auth_service.proto
- proto/api/v1/role_service.proto
- proto/api/v1/item_service.proto
- proto/buf.yaml
- proto/buf.gen.yaml

## 6. 技术决策

### 6.1 数据库选型
- **主数据库**: MySQL (与Java版本保持一致)
- **开发/测试**: SQLite (轻量级，零配置)
- **未来扩展**: PostgreSQL支持

### 6.2 缓存策略
- 使用内存缓存 (TTL 10分钟)
- 最大1000条缓存项
- 自动清理过期项

### 6.3 认证方式
- JWT Access Token (15分钟有效期)
- Refresh Token (长期有效，数据库验证)
- PAT (Personal Access Token)

## 7. 风险与应对

| 风险 | 影响 | 应对措施 |
|-----|------|---------|
| 重构期间功能不可用 | 高 | 并行开发，保持旧代码运行 |
| 数据迁移问题 | 高 | 详细测试，备份数据 |
| 性能下降 | 中 | 基准测试对比 |
| 学习成本 | 低 | 参考技能文档 |

## 8. 成功标准

- [ ] 所有现有功能正常工作
- [ ] 代码覆盖率 > 70%
- [ ] 性能不低于Java版本
- [ ] 支持多数据库切换
- [ ] 单端口支持多协议
- [ ] 完整的API文档

---

**创建日期**: 2026-02-09
**版本**: v1.0
**状态**: 规划中
