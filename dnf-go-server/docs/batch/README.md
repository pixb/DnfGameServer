# Java to Go 分批迁移计划

## 迁移策略

### 核心原则
1. **文档先行**: 每个批次开始前必须先完成设计文档
2. **测试先行**: 每个功能必须先写测试，再实现代码
3. **最小实现**: 每个批次只实现核心功能，避免过度设计
4. **状态记录**: 每次修改都记录在本文档中，方便续接

### 批次划分逻辑
- **批次1**: 基础设施 (已完成✅)
- **批次2**: 核心系统 - 登录与账户
- **批次3**: 核心系统 - 角色基础
- **批次4**: 核心系统 - 背包与物品
- **批次5**: 核心系统 - 场景与移动
- **批次6**: 核心系统 - 战斗基础
- **批次7+**: 扩展系统 (任务、社交、商店等)

---

## 批次1: 基础设施 (已完成 ✅)

**状态**: COMPLETED  
**日期**: 2026-02-09  
**完成度**: 100%

### 已完成内容

#### 1.1 Store层架构
- [x] Driver接口设计
- [x] MySQL实现
- [x] Store缓存层
- [x] 数据库迁移系统

#### 1.2 Server层架构
- [x] Echo + cmux 服务器
- [x] gRPC-Gateway 支持
- [x] Connect RPC 支持
- [x] 认证模块基础

#### 1.3 Main层架构
- [x] Cobra CLI框架
- [x] Viper配置管理
- [x] 优雅关闭机制

#### 1.4 文档
- [x] 01-overview.md
- [x] 02-store-layer-design.md
- [x] 03-server-layer-design.md
- [x] 04-main-layer-design.md
- [x] 05-migration-plan.md

### 遗留问题
- [ ] Connect RPC拦截器链待完善
- [ ] 部分Store方法待实现

---

## 批次2: 登录与账户系统

**状态**: PLANNING  
**目标日期**: 2026-02-16  
**依赖**: 批次1 ✅  
**优先级**: P0 (最高)

### 2.1 需求分析

#### Java参考代码
```
Java位置:
- com.dnfm.game.login.* (登录逻辑)
- com.dnfm.mina.session.* (会话管理)
- com.dnfm.mina.cache.SessionCache (会话缓存)
```

#### 核心功能
1. 账户登录验证
2. 会话管理 (Session)
3. Token生成与验证
4. 在线状态管理

### 2.2 设计文档 (编写中)

#### 2.2.1 数据模型
```go
// Account 账户 (已定义在 store/account.go)
// Session 会话 (待定义)
type Session struct {
    ID        string    // Session ID
    AccountID uint64    // 账户ID
    RoleID    uint64    // 当前角色ID (0=未选择角色)
    IP        string    // 客户端IP
    ConnectAt int64     // 连接时间
    LastPing  int64     // 最后心跳
    Status    int32     // 0=正常, 1=断开中
}

// OnlineAccount 在线账户 (内存数据结构)
type OnlineAccount struct {
    AccountID uint64
    SessionID string
    ServerID  int32
    LoginTime int64
}
```

#### 2.2.2 Store层扩展
```go
// 添加到 store/driver.go
type Driver interface {
    // ... 现有方法
    
    // Session管理
    CreateSession(ctx context.Context, session *Session) error
    GetSession(ctx context.Context, sessionID string) (*Session, error)
    UpdateSession(ctx context.Context, session *Session) error
    DeleteSession(ctx context.Context, sessionID string) error
    ListSessionsByAccount(ctx context.Context, accountID uint64) ([]*Session, error)
}
```

#### 2.2.3 服务层设计
```go
// AuthService (部分已实现)
- Login(ctx, req) (resp, error)      // 登录
- Logout(ctx, req) (resp, error)     // 登出
- Heartbeat(ctx, req) (resp, error)  // 心跳
```

### 2.3 测试计划

#### 2.3.1 单元测试
```go
// Test: store/session_test.go
func TestCreateSession(t *testing.T)
func TestGetSession(t *testing.T)
func TestUpdateSession(t *testing.T)
func TestSessionExpiration(t *testing.T)

// Test: server/auth/login_test.go
func TestLoginSuccess(t *testing.T)
func TestLoginWrongPassword(t *testing.T)
func TestLoginAccountNotFound(t *testing.T)
func TestLoginAccountBanned(t *testing.T)
```

#### 2.3.2 集成测试
```go
// Test: tests/integration/login_test.go
func TestLoginFlow(t *testing.T) {
    // 1. 创建测试账户
    // 2. 登录
    // 3. 验证Session创建
    // 4. 心跳
    // 5. 登出
}
```

### 2.4 任务清单

#### Week 1: 设计与Store层 (2月10-14日)
- [ ] 2.1 编写详细设计文档 (docs/batch/02-login-system-design.md)
- [ ] 2.2 编写Session模型定义 (store/session.go)
- [ ] 2.3 编写Session MySQL实现 (store/db/mysql/session.go)
- [ ] 2.4 编写Session Store方法 (store/session.go)
- [ ] 2.5 编写Session单元测试 (store/session_test.go)

#### Week 2: 服务层与测试 (2月15-16日)
- [ ] 2.6 完善AuthService登录实现 (server/router/api/v1/auth_service.go)
- [ ] 2.7 实现心跳处理
- [ ] 2.8 实现登出处理
- [ ] 2.9 编写集成测试
- [ ] 2.10 更新本文档状态

### 2.5 验收标准
- [ ] 单元测试覆盖率 > 80%
- [ ] 登录/登出/心跳功能正常
- [ ] Session自动过期机制
- [ ] 同一账户多设备登录处理

### 2.6 记录区 (实时更新)

**2026-02-09**: 
- 状态: PLANNING
- 进展: 
  - ✅ 创建了批次2的完整文档结构
  - ✅ 完成设计文档 (design.md)
  - ✅ 完成测试计划 (tests.md)
  - ✅ 完成任务清单 (tasks.md)
- 下一步: 开始T1.1任务 - 完善设计文档细节

**当前任务**: T1.1 完善设计文档  
**负责人**: 待分配  
**截止日期**: 2026-02-10 12:00

---

## 批次3: 角色系统 (基础)

**状态**: PENDING  
**目标日期**: 2026-02-23  
**依赖**: 批次2  
**优先级**: P0

### 3.1 需求分析

#### Java参考代码
```
Java位置:
- com.dnfm.game.role.* (角色核心)
- com.dnfm.game.role.service.* (角色服务)
- com.dnfm.game.player.* (玩家数据)
```

#### 核心功能
1. 角色创建
2. 角色列表查询
3. 角色选择
4. 基础属性管理
5. 角色删除

### 3.2 任务清单 (概要)

#### Week 1: 角色CRUD
- [ ] 3.1 角色创建逻辑
- [ ] 3.2 角色属性初始化
- [ ] 3.3 角色列表查询
- [ ] 3.4 角色选择处理

#### Week 2: 属性系统
- [ ] 3.5 基础属性定义
- [ ] 3.6 属性计算逻辑
- [ ] 3.7 属性持久化
- [ ] 3.8 属性同步

### 3.3 测试重点
- [ ] 角色创建参数验证
- [ ] 角色数量限制
- [ ] 同名角色检测
- [ ] 属性计算公式验证

---

## 批次4: 背包与物品系统

**状态**: PENDING  
**目标日期**: 2026-03-02  
**依赖**: 批次3  
**优先级**: P0

### 4.1 需求分析

#### Java参考代码
```
Java位置:
- com.dnfm.game.bag.* (背包)
- com.dnfm.game.item.* (物品)
```

#### 核心功能
1. 背包格管理
2. 物品添加/删除
3. 物品移动
4. 物品使用
5. 物品拆分/合并

### 4.2 任务清单 (概要)

#### Week 1: 背包基础
- [ ] 4.1 背包格数据模型
- [ ] 4.2 物品添加逻辑
- [ ] 4.3 物品删除逻辑
- [ ] 4.4 背包容量限制

#### Week 2: 物品操作
- [ ] 4.5 物品移动
- [ ] 4.6 物品拆分/合并
- [ ] 4.7 物品使用
- [ ] 4.8 物品排序

---

## 批次5: 场景与移动系统

**状态**: PENDING  
**目标日期**: 2026-03-09  
**依赖**: 批次4  
**优先级**: P1

### 5.1 需求分析

#### Java参考代码
```
Java位置:
- com.dnfm.game.scene.* (场景)
- com.dnfm.game.map.* (地图)
- com.dnfm.game.scene.aoi.* (AOI)
```

#### 核心功能
1. 场景管理
2. 玩家进入/离开场景
3. 移动同步
4. AOI (Area of Interest)
5. 视野管理

---

## 批次6: 战斗系统 (基础)

**状态**: PENDING  
**目标日期**: 2026-03-16  
**依赖**: 批次5  
**优先级**: P1

### 6.1 需求分析

#### Java参考代码
```
Java位置:
- com.dnfm.game.fight.* (战斗)
- com.dnfm.game.skill.* (技能)
```

#### 核心功能
1. 攻击基础逻辑
2. 伤害计算
3. 技能释放
4. 受击处理
5. 死亡/复活

---

## 批次7+: 扩展系统

### 批次7: 任务系统
**优先级**: P2
**功能**: 任务接受、完成、奖励

### 批次8: 商店系统
**优先级**: P2
**功能**: NPC商店、购买、出售

### 批次9: 社交系统
**优先级**: P2
**功能**: 好友、聊天、邮件

### 批次10: 公会系统
**优先级**: P3
**功能**: 公会创建、成员管理

### 批次11: 副本系统
**优先级**: P3
**功能**: 副本进入、通关

### 批次12: 拍卖行系统
**优先级**: P3
**功能**: 拍卖、竞拍

---

## 附录A: 开发流程规范

### A.1 每个批次的开发流程

```
1. 文档编写 (1-2天)
   └─ docs/batch/XX-module-design.md
   
2. 测试编写 (1-2天)
   └─ *_test.go (先写测试)
   
3. 代码实现 (3-5天)
   └─ 实现功能，确保测试通过
   
4. 集成验证 (1天)
   └─ 端到端测试
   
5. 文档更新 (0.5天)
   └─ 更新本文档状态
```

### A.2 文档模板

每个批次需要创建以下文档：
- `docs/batch/XX-module-design.md` - 详细设计文档
- `docs/batch/XX-module-tasks.md` - 任务清单
- `docs/batch/XX-module-tests.md` - 测试计划

### A.3 代码规范

1. 每个功能必须有对应的测试文件
2. 测试覆盖率必须 > 80%
3. 代码必须经过 `go vet` 和 `gofmt`
4. 复杂逻辑必须有注释

---

## 附录B: 当前状态总览

| 批次 | 模块 | 状态 | 进度 | 预计完成 |
|------|------|------|------|----------|
| 1 | 基础设施 | ✅ COMPLETED | 100% | 2026-02-09 |
| 2 | 登录与账户 | 📝 PLANNING | 0% | 2026-02-16 |
| 3 | 角色系统 | ⏳ PENDING | 0% | 2026-02-23 |
| 4 | 背包物品 | ⏳ PENDING | 0% | 2026-03-02 |
| 5 | 场景移动 | ⏳ PENDING | 0% | 2026-03-09 |
| 6 | 战斗系统 | ⏳ PENDING | 0% | 2026-03-16 |
| 7+ | 扩展系统 | ⏳ PENDING | 0% | TBD |

**状态图例**:
- ✅ COMPLETED - 已完成
- 🔄 IN_PROGRESS - 进行中
- 📝 PLANNING - 计划阶段
- ⏳ PENDING - 待开始
- 🐛 BLOCKED - 阻塞中

---

## 附录C: 快速参考

### 创建新批次文档
```bash
# 创建批次目录
mkdir -p docs/batch/02-login

# 创建文档
touch docs/batch/02-login/design.md
touch docs/batch/02-login/tasks.md
touch docs/batch/02-login/tests.md
```

### 更新批次状态
编辑本文档对应批次的状态和记录区。

### 提交规范
```
[batch-02] feat: 实现Session模型定义

- 添加Session结构体
- 实现Session CRUD方法
- 添加单元测试

Refs: docs/batch/02-login/design.md
```

---

**创建日期**: 2026-02-09  
**最后更新**: 2026-02-09  
**版本**: v1.0  
**下次读取位置**: 批次2 - 2.1 设计文档编写
