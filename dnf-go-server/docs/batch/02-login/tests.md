# 批次2: 登录与账户系统 - 测试计划

**批次ID**: batch-02  
**模块**: 登录与账户系统  
**状态**: 📝 PLANNING  
**创建日期**: 2026-02-09

---

## 1. 测试策略

### 1.1 测试金字塔

```
       /\
      /  \     E2E Tests (少量)
     /----\    
    /      \   Integration Tests (中等)
   /--------\ 
  /          \  Unit Tests (大量)
 /------------\
```

### 1.2 测试覆盖率目标
- **单元测试**: > 85%
- **集成测试**: 覆盖主要流程
- **E2E测试**: 登录完整流程

---

## 2. 单元测试

### 2.1 Store层测试

#### 文件: store/session_test.go

```go
package store

import (
    "context"
    "testing"
    "time"
    
    "github.com/stretchr/testify/assert"
    "github.com/stretchr/testify/require"
)

func TestCreateSession(t *testing.T) {
    ctx := context.Background()
    store := setupTestStore(t)
    
    tests := []struct {
        name    string
        session *Session
        wantErr bool
    }{
        {
            name: "正常创建Session",
            session: &Session{
                ID:        "test-session-001",
                AccountID: 10001,
                RoleID:    0,
                IP:        "127.0.0.1",
                Port:      12345,
                ConnectAt: time.Now().Unix(),
                LastPing:  time.Now().Unix(),
                ExpireAt:  time.Now().Add(30 * time.Minute).Unix(),
                Status:    SessionStatusNormal,
            },
            wantErr: false,
        },
        {
            name: "重复ID创建失败",
            session: &Session{
                ID:        "test-session-001", // 重复ID
                AccountID: 10002,
                IP:        "127.0.0.1",
                ConnectAt: time.Now().Unix(),
                LastPing:  time.Now().Unix(),
                ExpireAt:  time.Now().Add(30 * time.Minute).Unix(),
            },
            wantErr: true,
        },
        {
            name: "缺少必填字段失败",
            session: &Session{
                ID:        "test-session-002",
                // AccountID 为空
                IP:        "127.0.0.1",
                ConnectAt: time.Now().Unix(),
            },
            wantErr: true,
        },
    }
    
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            err := store.CreateSession(ctx, tt.session)
            if tt.wantErr {
                assert.Error(t, err)
            } else {
                assert.NoError(t, err)
            }
        })
    }
}

func TestGetSession(t *testing.T) {
    ctx := context.Background()
    store := setupTestStore(t)
    
    // 先创建测试数据
    testSession := &Session{
        ID:        "test-get-session",
        AccountID: 10001,
        IP:        "127.0.0.1",
        ConnectAt: time.Now().Unix(),
        LastPing:  time.Now().Unix(),
        ExpireAt:  time.Now().Add(30 * time.Minute).Unix(),
        Status:    SessionStatusNormal,
    }
    require.NoError(t, store.CreateSession(ctx, testSession))
    
    tests := []struct {
        name      string
        sessionID string
        wantErr   bool
    }{
        {
            name:      "获取存在的Session",
            sessionID: "test-get-session",
            wantErr:   false,
        },
        {
            name:      "获取不存在的Session",
            sessionID: "not-exist-session",
            wantErr:   true,
        },
        {
            name:      "空SessionID",
            sessionID: "",
            wantErr:   true,
        },
    }
    
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            session, err := store.GetSession(ctx, tt.sessionID)
            if tt.wantErr {
                assert.Error(t, err)
                assert.Nil(t, session)
            } else {
                assert.NoError(t, err)
                assert.NotNil(t, session)
                assert.Equal(t, tt.sessionID, session.ID)
            }
        })
    }
}

func TestUpdateSession(t *testing.T) {
    ctx := context.Background()
    store := setupTestStore(t)
    
    // 创建测试Session
    testSession := &Session{
        ID:        "test-update-session",
        AccountID: 10001,
        RoleID:    0,
        IP:        "127.0.0.1",
        ConnectAt: time.Now().Unix(),
        LastPing:  time.Now().Unix(),
        ExpireAt:  time.Now().Add(30 * time.Minute).Unix(),
        Status:    SessionStatusNormal,
    }
    require.NoError(t, store.CreateSession(ctx, testSession))
    
    tests := []struct {
        name    string
        update  *UpdateSession
        wantErr bool
    }{
        {
            name: "更新RoleID",
            update: &UpdateSession{
                ID:     "test-update-session",
                RoleID: uint64Ptr(20001),
            },
            wantErr: false,
        },
        {
            name: "更新LastPing",
            update: &UpdateSession{
                ID:       "test-update-session",
                LastPing: int64Ptr(time.Now().Unix()),
            },
            wantErr: false,
        },
        {
            name: "更新Status",
            update: &UpdateSession{
                ID:     "test-update-session",
                Status: int32Ptr(SessionStatusClosed),
            },
            wantErr: false,
        },
        {
            name: "更新不存在的Session",
            update: &UpdateSession{
                ID:     "not-exist",
                RoleID: uint64Ptr(20001),
            },
            wantErr: true,
        },
    }
    
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            err := store.UpdateSession(ctx, tt.update)
            if tt.wantErr {
                assert.Error(t, err)
            } else {
                assert.NoError(t, err)
                // 验证更新
                session, err := store.GetSession(ctx, tt.update.ID)
                require.NoError(t, err)
                if tt.update.RoleID != nil {
                    assert.Equal(t, *tt.update.RoleID, session.RoleID)
                }
            }
        })
    }
}

func TestDeleteSession(t *testing.T) {
    ctx := context.Background()
    store := setupTestStore(t)
    
    // 创建测试Session
    testSession := &Session{
        ID:        "test-delete-session",
        AccountID: 10001,
        IP:        "127.0.0.1",
        ConnectAt: time.Now().Unix(),
        LastPing:  time.Now().Unix(),
        ExpireAt:  time.Now().Add(30 * time.Minute).Unix(),
    }
    require.NoError(t, store.CreateSession(ctx, testSession))
    
    // 删除
    err := store.DeleteSession(ctx, "test-delete-session")
    assert.NoError(t, err)
    
    // 验证已删除
    _, err = store.GetSession(ctx, "test-delete-session")
    assert.Error(t, err)
}

func TestTouchSession(t *testing.T) {
    ctx := context.Background()
    store := setupTestStore(t)
    
    // 创建测试Session
    testSession := &Session{
        ID:        "test-touch-session",
        AccountID: 10001,
        IP:        "127.0.0.1",
        ConnectAt: time.Now().Unix(),
        LastPing:  time.Now().Unix(),
        ExpireAt:  time.Now().Add(30 * time.Minute).Unix(),
    }
    require.NoError(t, store.CreateSession(ctx, testSession))
    
    // 记录旧时间
    oldSession, _ := store.GetSession(ctx, "test-touch-session")
    oldPing := oldSession.LastPing
    
    // 等待一小段时间
    time.Sleep(100 * time.Millisecond)
    
    // 触发心跳
    err := store.TouchSession(ctx, "test-touch-session")
    assert.NoError(t, err)
    
    // 验证时间已更新
    newSession, _ := store.GetSession(ctx, "test-touch-session")
    assert.True(t, newSession.LastPing > oldPing)
}

func TestIsSessionValid(t *testing.T) {
    ctx := context.Background()
    store := setupTestStore(t)
    
    // 创建有效Session
    validSession := &Session{
        ID:        "test-valid-session",
        AccountID: 10001,
        IP:        "127.0.0.1",
        ConnectAt: time.Now().Unix(),
        LastPing:  time.Now().Unix(),
        ExpireAt:  time.Now().Add(30 * time.Minute).Unix(),
        Status:    SessionStatusNormal,
    }
    require.NoError(t, store.CreateSession(ctx, validSession))
    
    // 创建过期Session
    expiredSession := &Session{
        ID:        "test-expired-session",
        AccountID: 10002,
        IP:        "127.0.0.1",
        ConnectAt: time.Now().Add(-1 * time.Hour).Unix(),
        LastPing:  time.Now().Add(-1 * time.Hour).Unix(),
        ExpireAt:  time.Now().Add(-30 * time.Minute).Unix(), // 已过期
        Status:    SessionStatusNormal,
    }
    require.NoError(t, store.CreateSession(ctx, expiredSession))
    
    tests := []struct {
        name      string
        sessionID string
        wantValid bool
    }{
        {
            name:      "有效Session",
            sessionID: "test-valid-session",
            wantValid: true,
        },
        {
            name:      "过期Session",
            sessionID: "test-expired-session",
            wantValid: false,
        },
        {
            name:      "不存在Session",
            sessionID: "not-exist",
            wantValid: false,
        },
    }
    
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            valid, err := store.IsSessionValid(ctx, tt.sessionID)
            assert.NoError(t, err)
            assert.Equal(t, tt.wantValid, valid)
        })
    }
}

func TestCleanupExpiredSessions(t *testing.T) {
    ctx := context.Background()
    store := setupTestStore(t)
    
    // 创建一些Session
    sessions := []*Session{
        {
            ID:        "cleanup-1",
            AccountID: 10001,
            ExpireAt:  time.Now().Add(-1 * time.Hour).Unix(), // 已过期
        },
        {
            ID:        "cleanup-2",
            AccountID: 10002,
            ExpireAt:  time.Now().Add(-30 * time.Minute).Unix(), // 已过期
        },
        {
            ID:        "cleanup-3",
            AccountID: 10003,
            ExpireAt:  time.Now().Add(30 * time.Minute).Unix(), // 未过期
        },
    }
    
    for _, s := range sessions {
        s.IP = "127.0.0.1"
        s.ConnectAt = time.Now().Unix()
        s.LastPing = time.Now().Unix()
        require.NoError(t, store.CreateSession(ctx, s))
    }
    
    // 清理过期Session
    err := store.CleanupExpiredSessions(ctx, time.Now().Unix())
    assert.NoError(t, err)
    
    // 验证已清理
    _, err = store.GetSession(ctx, "cleanup-1")
    assert.Error(t, err)
    _, err = store.GetSession(ctx, "cleanup-2")
    assert.Error(t, err)
    
    // 验证未过期Session仍在
    _, err = store.GetSession(ctx, "cleanup-3")
    assert.NoError(t, err)
}

// 辅助函数
func uint64Ptr(v uint64) *uint64 {
    return &v
}

func int64Ptr(v int64) *int64 {
    return &v
}

func int32Ptr(v int32) *int32 {
    return &v
}
```

### 2.2 OnlineManager测试

#### 文件: server/session/online_manager_test.go

```go
package session

import (
    "testing"
    "time"
    
    "github.com/stretchr/testify/assert"
    "github.com/stretchr/testify/require"
    "dnf-go-server/store"
)

func TestOnlineManager_Login(t *testing.T) {
    store := setupTestStore(t)
    mgr := NewOnlineManager(store)
    
    session := &store.Session{
        ID:        "session-001",
        AccountID: 10001,
        ConnectAt: time.Now().Unix(),
    }
    
    // 登录
    err := mgr.Login(10001, session)
    require.NoError(t, err)
    
    // 验证在线
    assert.True(t, mgr.IsOnline(10001))
    
    // 验证Session
    s, ok := mgr.GetSession(10001)
    assert.True(t, ok)
    assert.Equal(t, "session-001", s.ID)
}

func TestOnlineManager_Logout(t *testing.T) {
    store := setupTestStore(t)
    mgr := NewOnlineManager(store)
    
    // 先登录
    session := &store.Session{
        ID:        "session-002",
        AccountID: 10002,
        ConnectAt: time.Now().Unix(),
    }
    require.NoError(t, mgr.Login(10002, session))
    assert.True(t, mgr.IsOnline(10002))
    
    // 登出
    err := mgr.Logout(10002)
    require.NoError(t, err)
    
    // 验证离线
    assert.False(t, mgr.IsOnline(10002))
    _, ok := mgr.GetSession(10002)
    assert.False(t, ok)
}

func TestOnlineManager_KickAccount(t *testing.T) {
    store := setupTestStore(t)
    mgr := NewOnlineManager(store)
    
    // 登录
    session := &store.Session{
        ID:        "session-003",
        AccountID: 10003,
        ConnectAt: time.Now().Unix(),
    }
    require.NoError(t, mgr.Login(10003, session))
    
    // 踢下线
    err := mgr.KickAccount(10003)
    require.NoError(t, err)
    
    // 验证已踢出
    assert.False(t, mgr.IsOnline(10003))
}

func TestOnlineManager_GetOnlineCount(t *testing.T) {
    store := setupTestStore(t)
    mgr := NewOnlineManager(store)
    
    // 初始为0
    assert.Equal(t, 0, mgr.GetOnlineCount())
    
    // 登录3个账户
    for i := 1; i <= 3; i++ {
        session := &store.Session{
            ID:        fmt.Sprintf("session-%d", i),
            AccountID: uint64(10000 + i),
            ConnectAt: time.Now().Unix(),
        }
        require.NoError(t, mgr.Login(uint64(10000+i), session))
    }
    
    // 验证在线人数
    assert.Equal(t, 3, mgr.GetOnlineCount())
}

func TestOnlineManager_ConcurrentAccess(t *testing.T) {
    store := setupTestStore(t)
    mgr := NewOnlineManager(store)
    
    // 并发登录
    var wg sync.WaitGroup
    for i := 0; i < 100; i++ {
        wg.Add(1)
        go func(idx int) {
            defer wg.Done()
            session := &store.Session{
                ID:        fmt.Sprintf("concurrent-%d", idx),
                AccountID: uint64(20000 + idx),
                ConnectAt: time.Now().Unix(),
            }
            mgr.Login(uint64(20000+idx), session)
        }(i)
    }
    wg.Wait()
    
    // 验证在线人数
    assert.Equal(t, 100, mgr.GetOnlineCount())
}
```

### 2.3 AuthService测试

#### 文件: server/router/api/v1/auth_service_test.go

```go
package v1

import (
    "context"
    "testing"
    
    "github.com/stretchr/testify/assert"
    "github.com/stretchr/testify/require"
)

func TestAuthService_Login(t *testing.T) {
    ctx := context.Background()
    svc := setupAuthService(t)
    
    // 先创建测试账户
    account := createTestAccount(t, "testuser", "password123")
    
    tests := []struct {
        name     string
        req      *LoginRequest
        wantErr  bool
        wantCode int32
    }{
        {
            name: "正常登录",
            req: &LoginRequest{
                Username: "testuser",
                Password: "password123",
                IP:       "127.0.0.1",
            },
            wantErr:  false,
            wantCode: LoginSuccess,
        },
        {
            name: "密码错误",
            req: &LoginRequest{
                Username: "testuser",
                Password: "wrongpassword",
                IP:       "127.0.0.1",
            },
            wantErr:  true,
            wantCode: LoginErrWrongPassword,
        },
        {
            name: "账户不存在",
            req: &LoginRequest{
                Username: "notexist",
                Password: "password123",
                IP:       "127.0.0.1",
            },
            wantErr:  true,
            wantCode: LoginErrAccountNotFound,
        },
        {
            name: "空用户名",
            req: &LoginRequest{
                Username: "",
                Password: "password123",
                IP:       "127.0.0.1",
            },
            wantErr:  true,
            wantCode: LoginErrInvalidParam,
        },
    }
    
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            resp, err := svc.Login(ctx, tt.req)
            if tt.wantErr {
                assert.Error(t, err)
                if resp != nil {
                    assert.Equal(t, tt.wantCode, resp.ErrorCode)
                }
            } else {
                assert.NoError(t, err)
                assert.True(t, resp.Success)
                assert.NotEmpty(t, resp.SessionID)
                assert.NotEmpty(t, resp.Token)
            }
        })
    }
}

func TestAuthService_Logout(t *testing.T) {
    ctx := context.Background()
    svc := setupAuthService(t)
    
    // 先登录
    account := createTestAccount(t, "logoutuser", "password123")
    loginResp, err := svc.Login(ctx, &LoginRequest{
        Username: "logoutuser",
        Password: "password123",
        IP:       "127.0.0.1",
    })
    require.NoError(t, err)
    
    // 登出
    resp, err := svc.Logout(ctx, &LogoutRequest{
        SessionID: loginResp.SessionID,
    })
    require.NoError(t, err)
    assert.True(t, resp.Success)
    
    // 验证Session已失效
    // ...
}

func TestAuthService_Heartbeat(t *testing.T) {
    ctx := context.Background()
    svc := setupAuthService(t)
    
    // 先登录
    account := createTestAccount(t, "heartbeatuser", "password123")
    loginResp, err := svc.Login(ctx, &LoginRequest{
        Username: "heartbeatuser",
        Password: "password123",
        IP:       "127.0.0.1",
    })
    require.NoError(t, err)
    
    // 发送心跳
    resp, err := svc.Heartbeat(ctx, &HeartbeatRequest{
        SessionID: loginResp.SessionID,
    })
    require.NoError(t, err)
    assert.True(t, resp.Success)
    assert.Greater(t, resp.ServerTime, int64(0))
}
```

---

## 3. 集成测试

### 3.1 登录流程集成测试

#### 文件: tests/integration/login_flow_test.go

```go
package integration

import (
    "testing"
    "time"
    
    "github.com/stretchr/testify/assert"
    "github.com/stretchr/testify/require"
)

// TestLoginFlow 完整登录流程测试
func TestLoginFlow(t *testing.T) {
    ctx := context.Background()
    
    // 准备测试环境
    store := setupTestStore(t)
    svc := setupAuthService(t, store)
    onlineMgr := setupOnlineManager(t, store)
    
    t.Run("完整登录流程", func(t *testing.T) {
        // Step 1: 创建测试账户
        account := &store.Account{
            Username:     "integration_test_user",
            PasswordHash: hashPassword("testpass123"),
            Status:       0,
        }
        _, err := store.CreateAccount(ctx, account)
        require.NoError(t, err)
        
        // Step 2: 登录
        loginResp, err := svc.Login(ctx, &LoginRequest{
            Username: "integration_test_user",
            Password: "testpass123",
            IP:       "192.168.1.100",
        })
        require.NoError(t, err)
        assert.True(t, loginResp.Success)
        assert.NotEmpty(t, loginResp.SessionID)
        assert.NotEmpty(t, loginResp.Token)
        
        // Step 3: 验证Session创建
        session, err := store.GetSession(ctx, loginResp.SessionID)
        require.NoError(t, err)
        assert.Equal(t, account.ID, session.AccountID)
        assert.Equal(t, "192.168.1.100", session.IP)
        
        // Step 4: 验证在线状态
        assert.True(t, onlineMgr.IsOnline(account.ID))
        
        // Step 5: 发送心跳
        heartbeatResp, err := svc.Heartbeat(ctx, &HeartbeatRequest{
            SessionID: loginResp.SessionID,
        })
        require.NoError(t, err)
        assert.True(t, heartbeatResp.Success)
        
        // Step 6: 登出
        logoutResp, err := svc.Logout(ctx, &LogoutRequest{
            SessionID: loginResp.SessionID,
        })
        require.NoError(t, err)
        assert.True(t, logoutResp.Success)
        
        // Step 7: 验证离线
        assert.False(t, onlineMgr.IsOnline(account.ID))
        
        // Step 8: 验证Session删除
        _, err = store.GetSession(ctx, loginResp.SessionID)
        assert.Error(t, err) // 应该报错
    })
    
    t.Run("重复登录测试", func(t *testing.T) {
        // 同一账户多次登录
        // 验证单设备登录限制
    })
    
    t.Run("Session过期测试", func(t *testing.T) {
        // 创建即将过期的Session
        // 等待过期
        // 验证自动清理
    })
}
```

---

## 4. 性能测试

### 4.1 登录性能测试

#### 文件: tests/benchmark/login_benchmark_test.go

```go
package benchmark

import (
    "context"
    "testing"
    "sync"
)

// BenchmarkLogin 登录性能测试
func BenchmarkLogin(b *testing.B) {
    ctx := context.Background()
    svc := setupAuthService(b)
    
    // 创建测试账户
    accounts := make([]*Account, 100)
    for i := 0; i < 100; i++ {
        accounts[i] = createTestAccount(b, fmt.Sprintf("benchuser%d", i), "password123")
    }
    
    b.ResetTimer()
    b.RunParallel(func(pb *testing.PB) {
        i := 0
        for pb.Next() {
            account := accounts[i%100]
            svc.Login(ctx, &LoginRequest{
                Username: account.Username,
                Password: "password123",
                IP:       "127.0.0.1",
            })
            i++
        }
    })
}

// BenchmarkHeartbeat 心跳性能测试
func BenchmarkHeartbeat(b *testing.B) {
    ctx := context.Background()
    svc := setupAuthService(b)
    
    // 创建1000个Session
    sessions := make([]string, 1000)
    for i := 0; i < 1000; i++ {
        account := createTestAccount(b, fmt.Sprintf("hbuser%d", i), "password123")
        resp, _ := svc.Login(ctx, &LoginRequest{
            Username: account.Username,
            Password: "password123",
            IP:       "127.0.0.1",
        })
        sessions[i] = resp.SessionID
    }
    
    b.ResetTimer()
    b.RunParallel(func(pb *testing.PB) {
        i := 0
        for pb.Next() {
            svc.Heartbeat(ctx, &HeartbeatRequest{
                SessionID: sessions[i%1000],
            })
            i++
        }
    })
}

// BenchmarkOnlineManager 在线管理器并发测试
func BenchmarkOnlineManager_Concurrent(b *testing.B) {
    store := setupTestStore(b)
    mgr := NewOnlineManager(store)
    
    b.RunParallel(func(pb *testing.PB) {
        i := 0
        for pb.Next() {
            accountID := uint64(100000 + i%10000)
            session := &store.Session{
                ID:        fmt.Sprintf("bench-session-%d", i),
                AccountID: accountID,
                ConnectAt: time.Now().Unix(),
            }
            
            if i%2 == 0 {
                mgr.Login(accountID, session)
            } else {
                mgr.Logout(accountID)
            }
            i++
        }
    })
}
```

---

## 5. 测试覆盖率检查清单

### 5.1 Store层
- [ ] CreateSession - 正常/异常/边界
- [ ] GetSession - 存在/不存在/空ID
- [ ] UpdateSession - 各字段更新/不存在
- [ ] DeleteSession - 正常/不存在
- [ ] TouchSession - 正常/不存在
- [ ] IsSessionValid - 有效/过期/不存在
- [ ] CleanupExpiredSessions - 清理过期/保留有效

### 5.2 OnlineManager
- [ ] Login - 正常/重复登录
- [ ] Logout - 正常/不存在
- [ ] KickAccount - 正常
- [ ] GetSession - 存在/不存在
- [ ] IsOnline - 在线/离线
- [ ] GetOnlineCount - 计数准确
- [ ] ConcurrentAccess - 并发安全

### 5.3 AuthService
- [ ] Login - 成功/密码错误/账户不存在/空参数
- [ ] Logout - 正常/无效Session
- [ ] Heartbeat - 正常/无效Session
- [ ] GetSessionInfo - 正常/无效Session

---

## 6. 测试运行命令

```bash
# 运行所有测试
go test ./...

# 运行Store层测试
go test ./store/... -v

# 运行Session测试
go test ./store/... -run TestSession -v

# 运行集成测试
go test ./tests/integration/... -v

# 运行性能测试
go test ./tests/benchmark/... -bench=. -benchmem

# 生成覆盖率报告
go test ./... -coverprofile=coverage.out
go tool cover -html=coverage.out
```

---

## 7. 变更记录

| 日期 | 版本 | 变更内容 |
|------|------|----------|
| 2026-02-09 | v1.0 | 初始版本 |

---

**下一步**: 开始编写具体测试代码
