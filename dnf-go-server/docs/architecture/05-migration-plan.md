# 迁移计划文档

## 1. 概述

### 1.1 迁移策略
采用**增量迁移**策略：
1. 保持现有代码继续运行
2. 并行开发新架构
3. 模块逐个替换
4. 完整测试后切换

### 1.2 迁移原则
- **向后兼容**: 保持API和协议不变
- **数据兼容**: 数据库schema保持不变或可自动迁移
- **渐进式**: 一次只迁移一个模块
- **可回滚**: 保留旧代码直到新架构稳定

## 2. 迁移阶段

### Phase 1: 基础设施准备 (Week 1-2)

#### 2.1.1 添加新依赖
```bash
# 添加到 go.mod
go get github.com/spf13/cobra
go get github.com/spf13/viper
go get github.com/soheilhy/cmux
go get github.com/golang-jwt/jwt/v5
```

#### 2.1.2 创建新目录结构
```
dnf-go-server/
├── cmd/server/cmd/          # 新增
├── internal/profile/        # 新增
├── server/                  # 新增
├── server/auth/             # 新增
├── server/router/api/v1/    # 新增
└── store/                   # 新增
```

#### 2.1.3 文件迁移检查清单
- [ ] 创建 `internal/profile/profile.go`
- [ ] 创建新配置文件 `configs/config.v2.yaml`
- [ ] 保持 `configs/config.yaml` 不变（旧配置）

### Phase 2: Store层迁移 (Week 3-4)

#### 2.2.1 创建Store层基础文件
```bash
# 创建基础结构
mkdir -p store/db/mysql
mkdir -p store/migration/mysql
```

**需要创建的文件**:
1. `store/driver.go` - 接口定义
2. `store/store.go` - Store包装器
3. `store/cache.go` - 缓存实现
4. `store/model.go` - 基础类型
5. `store/db/db.go` - Driver工厂

#### 2.2.2 模型迁移顺序

| 顺序 | 模型 | 旧文件 | 新文件 | 复杂度 |
|-----|------|-------|-------|--------|
| 1 | Account | `internal/db/models/base.go` | `store/account.go` | 低 |
| 2 | Role | `internal/db/models/base.go` | `store/role.go` | 低 |
| 3 | RoleAttributes | `internal/db/models/role.go` | `store/role_attributes.go` | 中 |
| 4 | BagItem | `internal/db/models/item.go` | `store/bag_item.go` | 中 |
| 5 | Quest/RoleQuest | `internal/db/models/social.go` | `store/quest.go` | 中 |
| 6 | Guild/GuildMember | `internal/db/models/guild.go` | `store/guild.go` | 中 |
| 7 | Friend | `internal/db/models/social.go` | `store/friend.go` | 低 |
| 8 | Mail | `internal/db/models/guild.go` | `store/mail.go` | 低 |
| 9 | Auction | (新增) | `store/auction.go` | 低 |
| 10 | SystemSetting | (新增) | `store/system_setting.go` | 低 |

#### 2.2.3 MySQL实现迁移

**需要创建的文件**:
- `store/db/mysql/db.go` - MySQL Driver
- `store/db/mysql/account.go` - Account CRUD
- `store/db/mysql/role.go` - Role CRUD
- `store/db/mysql/role_attributes.go` - RoleAttributes CRUD
- `store/db/mysql/bag_item.go` - BagItem CRUD
- `store/db/mysql/quest.go` - Quest CRUD
- `store/db/mysql/guild.go` - Guild CRUD
- `store/db/mysql/friend.go` - Friend CRUD
- `store/db/mysql/mail.go` - Mail CRUD
- `store/db/mysql/auction.go` - Auction CRUD
- `store/db/mysql/system_setting.go` - SystemSetting CRUD

#### 2.2.4 数据库迁移脚本

**需要创建的SQL文件**:
- `store/migration/mysql/LATEST.sql` - 完整Schema
- `store/migration/mysql/0.1/00__initial_schema.sql` - 初始迁移

#### 2.2.5 Store层验证清单
- [ ] 所有模型定义完成
- [ ] 所有CRUD方法实现
- [ ] 缓存功能正常
- [ ] 单元测试通过
- [ ] 与现有GORM代码数据兼容

### Phase 3: Server层迁移 (Week 5-6)

#### 2.3.1 创建Proto服务定义

**需要创建的proto文件**:
1. `proto/api/v1/auth_service.proto`
2. `proto/api/v1/role_service.proto`
3. `proto/api/v1/item_service.proto`
4. `proto/api/v1/dungeon_service.proto`
5. `proto/api/v1/chat_service.proto`
6. `proto/api/v1/shop_service.proto`
7. `proto/api/v1/quest_service.proto`
8. `proto/api/v1/guild_service.proto`

**buf配置**:
- `proto/buf.yaml`
- `proto/buf.gen.yaml`

**生成命令**:
```bash
cd proto && buf generate
```

#### 2.3.2 创建Server层基础文件

**需要创建的文件**:
1. `server/server.go` - 服务器主类
2. `server/auth/authenticator.go` - 认证器
3. `server/auth/claims.go` - JWT声明
4. `server/auth/context.go` - 上下文工具
5. `server/auth/extract.go` - Token提取
6. `server/auth/token.go` - Token管理

#### 2.3.3 创建API v1路由

**需要创建的文件**:
1. `server/router/api/v1/v1.go` - 服务注册主文件
2. `server/router/api/v1/acl_config.go` - 公开端点配置
3. `server/router/api/v1/connect_interceptors.go` - 拦截器链
4. `server/router/api/v1/connect_handler.go` - Connect包装器
5. `server/router/api/v1/header_carrier.go` - Header处理

#### 2.3.4 服务实现迁移

**将现有handlers迁移到新服务**:

| 旧文件 | 新文件 | 说明 |
|-------|-------|------|
| `internal/game/handlers/auth.go` | `server/router/api/v1/auth_service.go` | 登录/注册 |
| `internal/game/handlers/role.go` | `server/router/api/v1/role_service.go` | 角色管理 |
| `internal/game/handlers/item.go` | `server/router/api/v1/item_service.go` | 背包/物品 |
| `internal/game/handlers/dungeon.go` | `server/router/api/v1/dungeon_service.go` | 副本 |
| `internal/game/handlers/chat.go` | `server/router/api/v1/chat_service.go` | 聊天/好友 |
| `internal/game/handlers/shop.go` | `server/router/api/v1/shop_service.go` | 商店/拍卖 |
| `internal/game/handlers/quest.go` | `server/router/api/v1/quest_service.go` | 任务 |
| `internal/game/handlers/guild.go` | `server/router/api/v1/guild_service.go` | 公会 |

#### 2.3.5 Server层验证清单
- [ ] 所有proto定义完成
- [ ] 代码生成成功
- [ ] gRPC服务注册正常
- [ ] gRPC-Gateway正常工作
- [ ] Connect RPC正常工作
- [ ] cmux端口复用正常
- [ ] 认证流程完整
- [ ] 拦截器链正常工作

### Phase 4: Main层迁移 (Week 7-8)

#### 2.4.1 创建Cobra命令结构

**需要创建的文件**:
1. `cmd/server/cmd/root.go` - 根命令
2. `cmd/server/cmd/serve.go` - serve子命令
3. `cmd/server/cmd/migrate.go` - migrate子命令
4. `cmd/server/cmd/version.go` - version子命令

#### 2.4.2 重构main.go

**旧文件**: `cmd/server/main.go` (196行)

**新文件**: `cmd/server/main.go` (5行)
```go
package main
import "github.com/dnf-go-server/cmd/server/cmd"
func main() { cmd.Execute() }
```

#### 2.4.3 Main层验证清单
- [ ] 所有子命令可用
- [ ] 配置文件加载正常
- [ ] 环境变量覆盖正常
- [ ] 帮助信息完整
- [ ] 优雅关闭正常

### Phase 5: 集成测试 (Week 9-10)

#### 2.5.1 端到端测试

**测试场景**:
1. 启动服务器
2. 创建账户
3. 登录
4. 创建角色
5. 获取角色列表
6. 进入副本
7. 聊天
8. 购买物品
9. 完成任务
10. 加入公会
11. 退出登录

#### 2.5.2 性能测试

**测试指标**:
- 连接数: 10000并发
- 延迟: P99 < 100ms
- 吞吐量: 10000 QPS
- 内存使用: < 2GB

#### 2.5.3 兼容性测试

**测试项**:
- [ ] 旧客户端能连接新服务器
- [ ] 数据库数据完全兼容
- [ ] 配置文件向后兼容
- [ ] API响应格式一致

### Phase 6: 切换与清理 (Week 11-12)

#### 2.6.1 切换检查清单
- [ ] 所有测试通过
- [ ] 性能达标
- [ ] 文档更新完成
- [ ] 回滚方案准备

#### 2.6.2 切换流程
```
1. 备份现有代码和数据
2. 更新Dockerfile
3. 更新docker-compose.yml
4. 部署到新服务器
5. 流量切换
6. 监控24小时
```

#### 2.6.3 清理旧代码
```bash
# 在确认新架构稳定后删除旧代码
rm -rf internal/db/models/*.go  # 保留 migration
rm -rf internal/game/handlers/*.go
rm -rf internal/network/*.go  # 保留用于TCP的部分
```

## 3. 风险缓解

### 3.1 高风险项

| 风险 | 概率 | 影响 | 缓解措施 |
|-----|------|------|---------|
| 数据库迁移失败 | 低 | 高 | 完整备份，逐步迁移 |
| 性能下降 | 中 | 高 | 提前基准测试，逐步优化 |
| 功能丢失 | 低 | 高 | 完整功能测试清单 |
| 协议不兼容 | 低 | 中 | 保持proto定义一致 |

### 3.2 回滚方案

**触发条件**:
- 生产环境严重故障
- 性能不达标
- 功能丢失

**回滚步骤**:
1. 停止新服务器
2. 启动旧服务器
3. 回滚数据库（如有必要）
4. 恢复流量

## 4. 代码对照表

### 4.1 文件映射

```
旧架构                                    新架构
────────────────────────────────────────────────────────────────
cmd/server/main.go                    →  cmd/server/main.go (简化)
                                       +  cmd/server/cmd/*.go

configs/config.yaml                   →  configs/config.yaml (兼容)
                                       +  internal/profile/profile.go

internal/db/db.go                     →  store/db/db.go
internal/db/models/*.go               →  store/*.go
                                       +  store/migration/*.sql

internal/game/handlers/*.go           →  server/router/api/v1/*_service.go

internal/server/http/server.go        →  server/server.go
                                       +  server/router/api/v1/v1.go

internal/utils/config/config.go       →  (整合到profile)
internal/utils/logger/logger.go       →  (保持)

(新增)                                →  server/auth/*.go
(新增)                                →  proto/api/v1/*.proto
```

### 4.2 接口对照

| 旧接口 | 新接口 | 状态 |
|-------|-------|------|
| `db.NewDB()` | `store.New()` | 变更 |
| `PlayerService` | `store.Store` | 替换 |
| `network.TCPServer` | `server.Server` | 替换 |
| `handlers.RegisterAllHandlers()` | `v1.RegisterGateway()` | 替换 |

## 5. 测试计划

### 5.1 单元测试

**Store层**:
```go
func TestAccountCRUD(t *testing.T) {
    // 测试创建
    // 测试查询
    // 测试更新
    // 测试删除
}

func TestCacheBehavior(t *testing.T) {
    // 测试缓存命中
    // 测试缓存过期
    // 测试缓存清除
}
```

**Server层**:
```go
func TestAuthService(t *testing.T) {
    // 测试登录
    // 测试Token刷新
    // 测试认证失败
}
```

### 5.2 集成测试

```go
func TestEndToEndGameFlow(t *testing.T) {
    // 完整游戏流程测试
}
```

### 5.3 基准测试

```go
func BenchmarkAccountCreate(b *testing.B) {
    // 创建账户性能测试
}

func BenchmarkLogin(b *testing.B) {
    // 登录性能测试
}
```

## 6. 文档更新

### 6.1 需要更新的文档
- [ ] `docs/README.md` - 更新架构说明
- [ ] `README.md` - 更新构建和运行说明
- [ ] API文档 - 新增gRPC/Connect文档
- [ ] 部署文档 - 更新Docker说明

### 6.2 需要创建的文档
- [x] `docs/architecture/01-overview.md` - 重构概述
- [x] `docs/architecture/02-store-layer-design.md` - Store层设计
- [x] `docs/architecture/03-server-layer-design.md` - Server层设计
- [x] `docs/architecture/04-main-layer-design.md` - Main层设计
- [x] `docs/architecture/05-migration-plan.md` - 迁移计划

## 7. 时间线

```
Week 1-2:  [Phase 1] 基础设施准备
          ├─ 添加依赖
          ├─ 创建目录结构
          └─ 准备配置

Week 3-4:  [Phase 2] Store层实现
          ├─ Driver接口
          ├─ 模型迁移
          ├─ MySQL实现
          └─ 缓存层

Week 5-6:  [Phase 3] Server层实现
          ├─ Proto定义
          ├─ 代码生成
          ├─ 服务实现
          └─ 路由配置

Week 7-8:  [Phase 4] Main层重构
          ├─ Cobra CLI
          ├─ Profile配置
          └─ 优雅关闭

Week 9-10: [Phase 5] 集成测试
          ├─ 端到端测试
          ├─ 性能测试
          └─ 兼容性测试

Week 11-12: [Phase 6] 切换与清理
          ├─ 生产部署
          ├─ 监控
          └─ 旧代码清理
```

## 8. 成功标准

- [ ] 所有Phase完成
- [ ] 单元测试覆盖率 > 70%
- [ ] 集成测试100%通过
- [ ] 性能不低于旧架构
- [ ] 零数据丢失
- [ ] 生产环境稳定运行7天

---

**创建日期**: 2026-02-09
**版本**: v1.0
**状态**: 规划完成，准备执行
