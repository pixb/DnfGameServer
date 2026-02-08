# 批次2: 登录与账户系统 - 任务清单

**批次ID**: batch-02  
**模块**: 登录与账户系统  
**状态**: 📝 PLANNING  
**创建日期**: 2026-02-09  
**计划完成**: 2026-02-16

---

## 任务总览

| 阶段 | 任务数 | 预计时间 | 状态 |
|------|--------|----------|------|
| 设计阶段 | 1 | 1天 | ⏳ 待开始 |
| Store层 | 5 | 3天 | ⏳ 待开始 |
| 服务层 | 4 | 2天 | ⏳ 待开始 |
| 测试验证 | 3 | 2天 | ⏳ 待开始 |
| **总计** | **13** | **8天** | **0%** |

---

## 详细任务清单

### 📋 阶段1: 设计阶段 (Day 1)

#### T1.1 完善设计文档
- **文件**: `docs/batch/02-login/design.md`
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **详细内容**:
  - [ ] 完善数据模型定义
  - [ ] 细化接口定义
  - [ ] 添加时序图
  - [ ] 定义错误码
- **验收标准**:
  - 设计文档通过review
  - 数据模型定义完整
  - 接口定义清晰
- **依赖**: 无

---

### 🔧 阶段2: Store层实现 (Day 1-3)

#### T2.1 创建Session模型定义
- **文件**: `store/session.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 2h
- **详细内容**:
  ```go
  // 需要定义:
  - Session 结构体
  - FindSession 结构体
  - UpdateSession 结构体
  - SessionStatus 常量
  - SessionTimeout 常量
  ```
- **验收标准**:
  - 模型定义完整
  - JSON tag正确
  - 常量定义清晰
- **依赖**: T1.1 ✅

#### T2.2 创建Session MySQL实现
- **文件**: `store/db/mysql/session.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **详细内容**:
  ```go
  // 需要实现:
  - CreateSession
  - GetSession
  - UpdateSession
  - DeleteSession
  - ListSessionsByAccount
  - CleanupExpiredSessions
  ```
- **验收标准**:
  - 所有CRUD方法实现
  - SQL语句正确
  - 错误处理完善
- **依赖**: T2.1 ✅

#### T2.3 扩展Driver接口
- **文件**: `store/driver.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 1h
- **详细内容**:
  - 添加Session相关方法到Driver接口
  - 确保与现有代码兼容
- **验收标准**:
  - 接口定义完整
  - 无编译错误
- **依赖**: T2.1 ✅

#### T2.4 实现Store层Session方法
- **文件**: `store/store.go` (扩展)
- **状态**: ⏳ PENDING
- **预计耗时**: 3h
- **详细内容**:
  ```go
  // 需要实现:
  - CreateSession
  - GetSession
  - UpdateSession
  - DeleteSession
  - TouchSession (更新心跳)
  - IsSessionValid
  ```
- **验收标准**:
  - 方法实现正确
  - 错误处理完善
  - 逻辑清晰
- **依赖**: T2.2, T2.3 ✅

#### T2.5 编写Session Store单元测试
- **文件**: `store/session_test.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **详细内容**:
  - 按tests.md中的测试用例编写
  - 覆盖率 > 85%
- **验收标准**:
  - 所有测试通过
  - 覆盖率达标
- **依赖**: T2.4 ✅

---

### 🖥️ 阶段3: 服务层实现 (Day 4-5)

#### T3.1 实现OnlineManager
- **文件**: `server/session/online_manager.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **详细内容**:
  ```go
  // 需要实现:
  - Login (用户上线)
  - Logout (用户下线)
  - GetSession (获取Session)
  - IsOnline (检查在线)
  - KickAccount (踢下线)
  - GetOnlineCount (在线人数)
  - CleanupExpired (清理过期)
  ```
- **验收标准**:
  - 并发安全 (使用sync.RWMutex)
  - 内存管理正确
- **依赖**: T2.4 ✅

#### T3.2 编写OnlineManager单元测试
- **文件**: `server/session/online_manager_test.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 3h
- **详细内容**:
  - 按tests.md中的测试用例编写
  - 包含并发测试
- **验收标准**:
  - 测试通过
  - 并发测试无race condition
- **依赖**: T3.1 ✅

#### T3.3 完善AuthService
- **文件**: `server/router/api/v1/auth_service.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **详细内容**:
  ```go
  // 需要实现:
  - Login (登录逻辑)
  - Logout (登出逻辑)
  - Heartbeat (心跳处理)
  - GetSessionInfo (获取Session)
  ```
- **验收标准**:
  - 登录流程完整
  - Session管理正确
  - 错误码返回准确
- **依赖**: T3.1 ✅

#### T3.4 编写AuthService单元测试
- **文件**: `server/router/api/v1/auth_service_test.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 3h
- **详细内容**:
  - 按tests.md中的测试用例编写
- **验收标准**:
  - 测试通过
  - 覆盖所有错误场景
- **依赖**: T3.3 ✅

---

### ✅ 阶段4: 测试验证 (Day 6-7)

#### T4.1 编写集成测试
- **文件**: `tests/integration/login_flow_test.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **详细内容**:
  - 完整登录流程测试
  - 多场景覆盖
- **验收标准**:
  - 测试通过
  - 覆盖主要业务场景
- **依赖**: T3.4 ✅

#### T4.2 编写性能测试
- **文件**: `tests/benchmark/login_benchmark_test.go`
- **状态**: ⏳ PENDING
- **预计耗时**: 2h
- **详细内容**:
  - 登录性能测试
  - 心跳性能测试
  - 并发性能测试
- **验收标准**:
  - 性能指标达标
  - 无明显性能问题
- **依赖**: T3.4 ✅

#### T4.3 集成测试与Bug修复
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **详细内容**:
  - 运行所有测试
  - 修复发现的Bug
  - 验证覆盖率
- **验收标准**:
  - 单元测试通过率 100%
  - 覆盖率 > 80%
  - 集成测试通过
- **依赖**: T4.1, T4.2 ✅

---

## 每日计划

### Day 1 (2月10日): 设计与模型
- [ ] 上午: T1.1 完善设计文档
- [ ] 下午: T2.1 Session模型定义, T2.3 Driver接口扩展

### Day 2 (2月11日): Store层实现(上)
- [ ] T2.2 Session MySQL实现

### Day 3 (2月12日): Store层实现(下)
- [ ] T2.4 Store层Session方法
- [ ] T2.5 Session Store单元测试

### Day 4 (2月13日): 在线管理
- [ ] T3.1 OnlineManager实现
- [ ] T3.2 OnlineManager单元测试

### Day 5 (2月14日): 认证服务
- [ ] T3.3 AuthService实现
- [ ] T3.4 AuthService单元测试

### Day 6 (2月15日): 集成测试
- [ ] T4.1 集成测试编写
- [ ] T4.2 性能测试编写

### Day 7 (2月16日): 验证与文档
- [ ] T4.3 集成测试与Bug修复
- [ ] 更新批次状态文档
- [ ] 代码Review

---

## 进度追踪

```
Week 1 Progress: [░░░░░░░░░░] 0%

设计阶段:    [░░░░░░░░░░] 0% ▶ T1.1
Store层:     [░░░░░░░░░░] 0% ▶ 等待设计完成
服务层:      [░░░░░░░░░░] 0% ▶ 等待Store层完成
测试验证:    [░░░░░░░░░░] 0% ▶ 等待服务层完成
```

---

## 风险与问题

| 风险 | 概率 | 影响 | 状态 | 缓解措施 |
|------|------|------|------|----------|
| Session并发问题 | 中 | 高 | 🟡 监控 | 使用sync.RWMutex, 充分测试 |
| MySQL连接池耗尽 | 低 | 高 | 🟢 可控 | 合理配置连接池大小 |
| 内存泄漏 | 低 | 高 | 🟢 可控 | 定期清理过期Session |

---

## 变更记录

| 日期 | 任务 | 变更类型 | 说明 |
|------|------|----------|------|
| 2026-02-09 | - | 创建 | 初始任务清单 |

---

## 下一步行动

**当前任务**: T1.1 完善设计文档  
**负责人**: 待分配  
**截止日期**: 2026-02-10 12:00  
**阻塞项**: 无

---

**快速导航**:
- [设计文档](./design.md)
- [测试计划](./tests.md)
- [批次总览](../README.md)
