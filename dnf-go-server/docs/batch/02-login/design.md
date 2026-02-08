# 批次2: 登录与账户系统设计文档

**批次ID**: batch-02  
**模块**: 登录与账户系统  
**状态**: 📝 PLANNING  
**创建日期**: 2026-02-09  
**计划完成**: 2026-02-16

---

## 1. 需求概述

### 1.1 功能范围
实现用户登录、会话管理和在线状态维护的核心功能。

### 1.2 Java参考
```
位置: /src/main/java/com/dnfm/game/login/
- LoginController.java
- LoginService.java
- SessionManager.java

位置: /src/main/java/com/dnfm/mina/session/
- SessionManager.java
- SessionProperties.java
```

### 1.3 核心功能清单
1. 账户登录验证 (用户名+密码)
2. Session创建与管理
3. Token生成与验证 (JWT)
4. 心跳检测机制
5. 在线状态管理
6. 登出处理

---

## 2. 数据模型设计

### 2.1 Session 模型

```go
// Session 用户会话
// 位置: store/session.go
type Session struct {
    // 基础字段
    ID        string    `json:"id"`         // Session唯一ID (UUID)
    AccountID uint64    `json:"account_id"` // 关联账户ID
    RoleID    uint64    `json:"role_id"`    // 当前选中角色ID (0=未选择)
    
    // 连接信息
    IP        string    `json:"ip"`         // 客户端IP地址
    Port      int32     `json:"port"`       // 客户端端口
    
    // 时间戳
    ConnectAt int64     `json:"connect_at"` // 连接时间 (Unix时间戳)
    LastPing  int64     `json:"last_ping"`  // 最后心跳时间
    ExpireAt  int64     `json:"expire_at"`  // Session过期时间
    
    // 状态
    Status    int32     `json:"status"`     // 0=正常, 1=断开中, 2=已断开
}

// SessionStatus 会话状态常量
const (
    SessionStatusNormal    int32 = 0
    SessionStatusClosing   int32 = 1
    SessionStatusClosed    int32 = 2
)

// SessionTimeout Session超时配置
const (
    SessionTimeoutDefault = 30 * time.Minute // 默认30分钟无心跳断开
    SessionTimeoutMax     = 24 * time.Hour   // 最大Session存活时间
)
```

### 2.2 查询与更新结构

```go
// FindSession 查询Session参数
type FindSession struct {
    ID        *string
    AccountID *uint64
    RoleID    *uint64
    Status    *int32
}

// UpdateSession 更新Session参数
type UpdateSession struct {
    ID        string
    RoleID    *uint64
    LastPing  *int64
    ExpireAt  *int64
    Status    *int32
}
```

### 2.3 内存数据结构 (非持久化)

```go
// OnlineManager 在线管理器 (内存管理)
// 位置: server/session/online_manager.go
type OnlineManager struct {
    // accountID -> sessionID 映射
    accountSessions map[uint64]string
    
    // sessionID -> Session 缓存
    sessionCache map[string]*Session
    
    // 保护锁
    mu sync.RWMutex
}

// OnlineAccount 在线账户信息
type OnlineAccount struct {
    AccountID   uint64    `json:"account_id"`
    SessionID   string    `json:"session_id"`
    ServerID    int32     `json:"server_id"`    // 所在服务器ID
    LoginTime   int64     `json:"login_time"`
    LastActTime int64     `json:"last_act_time"`
    IP          string    `json:"ip"`
}
```

---

## 3. Store层设计

### 3.1 Driver接口扩展

```go
// 添加到 store/driver.go

// Session相关操作
type SessionDriver interface {
    // CreateSession 创建Session
    CreateSession(ctx context.Context, session *Session) error
    
    // GetSession 获取Session
    GetSession(ctx context.Context, sessionID string) (*Session, error)
    
    // UpdateSession 更新Session
    UpdateSession(ctx context.Context, update *UpdateSession) error
    
    // DeleteSession 删除Session
    DeleteSession(ctx context.Context, sessionID string) error
    
    // ListSessionsByAccount 获取账户的所有Session
    ListSessionsByAccount(ctx context.Context, accountID uint64) ([]*Session, error)
    
    // CleanupExpiredSessions 清理过期Session
    CleanupExpiredSessions(ctx context.Context, before int64) error
}
```

### 3.2 MySQL实现

```go
// store/db/mysql/session.go

// 表结构
const sessionTableSchema = `
CREATE TABLE IF NOT EXISTS session (
    id VARCHAR(64) PRIMARY KEY,
    account_id BIGINT NOT NULL,
    role_id BIGINT DEFAULT 0,
    ip VARCHAR(50) NOT NULL,
    port INT DEFAULT 0,
    connect_at BIGINT NOT NULL,
    last_ping BIGINT NOT NULL,
    expire_at BIGINT NOT NULL,
    status INT DEFAULT 0,
    INDEX idx_account_id (account_id),
    INDEX idx_expire_at (expire_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
`

// DB 实现 SessionDriver 接口
func (d *DB) CreateSession(ctx context.Context, session *Session) error
func (d *DB) GetSession(ctx context.Context, sessionID string) (*Session, error)
func (d *DB) UpdateSession(ctx context.Context, update *UpdateSession) error
func (d *DB) DeleteSession(ctx context.Context, sessionID string) error
func (d *DB) ListSessionsByAccount(ctx context.Context, accountID uint64) ([]*Session, error)
func (d *DB) CleanupExpiredSessions(ctx context.Context, before int64) error
```

### 3.3 Store包装器方法

```go
// store/store.go 扩展

// CreateSession 创建Session (不缓存，Session变化频繁)
func (s *Store) CreateSession(ctx context.Context, session *Session) error

// GetSession 获取Session
func (s *Store) GetSession(ctx context.Context, sessionID string) (*Session, error)

// UpdateSession 更新Session
func (s *Store) UpdateSession(ctx context.Context, update *UpdateSession) error

// DeleteSession 删除Session
func (s *Store) DeleteSession(ctx context.Context, sessionID string) error

// TouchSession 更新Session心跳
func (s *Store) TouchSession(ctx context.Context, sessionID string) error

// IsSessionValid 检查Session是否有效
func (s *Store) IsSessionValid(ctx context.Context, sessionID string) (bool, error)
```

---

## 4. 服务层设计

### 4.1 AuthService接口

```go
// server/router/api/v1/auth_service.go

type AuthService interface {
    // Login 用户登录
    // POST /api/v1/auth/login
    Login(ctx context.Context, req *LoginRequest) (*LoginResponse, error)
    
    // Logout 用户登出
    // POST /api/v1/auth/logout
    Logout(ctx context.Context, req *LogoutRequest) (*LogoutResponse, error)
    
    // Heartbeat 心跳检测
    // POST /api/v1/auth/heartbeat
    Heartbeat(ctx context.Context, req *HeartbeatRequest) (*HeartbeatResponse, error)
    
    // GetSessionInfo 获取Session信息
    // GET /api/v1/auth/session
    GetSessionInfo(ctx context.Context, req *GetSessionRequest) (*SessionInfo, error)
}
```

### 4.2 请求/响应结构

```go
// LoginRequest 登录请求
type LoginRequest struct {
    Username string `json:"username" validate:"required"`
    Password string `json:"password" validate:"required"`
    IP       string `json:"ip"`        // 客户端IP
}

// LoginResponse 登录响应
type LoginResponse struct {
    Success      bool   `json:"success"`
    SessionID    string `json:"session_id,omitempty"`
    Token        string `json:"token,omitempty"`
    ExpireIn     int64  `json:"expire_in,omitempty"`     // Token过期时间(秒)
    ErrorCode    int32  `json:"error_code,omitempty"`
    ErrorMessage string `json:"error_message,omitempty"`
}

// LogoutRequest 登出请求
type LogoutRequest struct {
    SessionID string `json:"session_id" validate:"required"`
}

// LogoutResponse 登出响应
type LogoutResponse struct {
    Success bool `json:"success"`
}

// HeartbeatRequest 心跳请求
type HeartbeatRequest struct {
    SessionID string `json:"session_id" validate:"required"`
}

// HeartbeatResponse 心跳响应
type HeartbeatResponse struct {
    Success   bool  `json:"success"`
    ServerTime int64 `json:"server_time"` // 服务器时间戳
}

// GetSessionRequest 获取Session请求
type GetSessionRequest struct {
    SessionID string `json:"session_id"`
}

// SessionInfo Session信息
type SessionInfo struct {
    SessionID  string `json:"session_id"`
    AccountID  uint64 `json:"account_id"`
    RoleID     uint64 `json:"role_id"`
    ConnectAt  int64  `json:"connect_at"`
    LastPing   int64  `json:"last_ping"`
    ExpireAt   int64  `json:"expire_at"`
}
```

### 4.3 登录流程

```
┌──────────┐     ┌──────────┐     ┌──────────┐     ┌──────────┐
│  Client  │────▶│  Server  │────▶│  Store   │────▶│  MySQL   │
└──────────┘     └──────────┘     └──────────┘     └──────────┘
     │                │                │                │
     │  1. Login      │                │                │
     │───────────────▶│                │                │
     │                │                │                │
     │                │  2. Validate   │                │
     │                │  Account       │                │
     │                │───────────────▶│                │
     │                │                │  3. Query      │
     │                │                │  Account       │
     │                │                │───────────────▶│
     │                │                │◀───────────────│
     │                │◀───────────────│                │
     │                │                │                │
     │                │  4. Create     │                │
     │                │  Session       │                │
     │                │───────────────▶│                │
     │                │                │  5. Insert     │
     │                │                │  Session       │
     │                │                │───────────────▶│
     │                │                │◀───────────────│
     │                │◀───────────────│                │
     │                │                │                │
     │  6. Return     │                │                │
     │  SessionID+    │                │                │
     │  Token         │                │                │
     │◀───────────────│                │                │
```

### 4.4 错误码定义

```go
const (
    LoginSuccess         int32 = 0
    LoginErrInvalidParam int32 = 1001  // 参数错误
    LoginErrAccountNotFound int32 = 1002  // 账户不存在
    LoginErrWrongPassword   int32 = 1003  // 密码错误
    LoginErrAccountBanned   int32 = 1004  // 账户被封禁
    LoginErrAlreadyOnline   int32 = 1005  // 已在线(可选)
    LoginErrServerBusy      int32 = 1006  // 服务器繁忙
    LoginErrSessionExpired  int32 = 1007  // Session已过期
    LoginErrInvalidSession  int32 = 1008  // 无效Session
)
```

---

## 5. 在线管理设计

### 5.1 OnlineManager

```go
// server/session/online_manager.go

type OnlineManager struct {
    store  *store.Store
    
    // 内存索引
    accountSessions map[uint64]string  // accountID -> sessionID
    sessionCache    map[string]*Session // sessionID -> Session
    
    mu sync.RWMutex
}

// NewOnlineManager 创建在线管理器
func NewOnlineManager(store *store.Store) *OnlineManager

// Login 用户上线
func (m *OnlineManager) Login(accountID uint64, session *Session) error

// Logout 用户下线
func (m *OnlineManager) Logout(accountID uint64) error

// GetSession 获取用户Session
func (m *OnlineManager) GetSession(accountID uint64) (*Session, bool)

// IsOnline 检查用户是否在线
func (m *OnlineManager) IsOnline(accountID uint64) bool

// KickAccount 踢下线指定账户
func (m *OnlineManager) KickAccount(accountID uint64) error

// GetOnlineCount 获取在线人数
func (m *OnlineManager) GetOnlineCount() int

// CleanupExpired 清理过期Session
func (m *OnlineManager) CleanupExpired()
```

### 5.2 心跳检测

```go
// HeartbeatChecker 心跳检测器
type HeartbeatChecker struct {
    onlineMgr *OnlineManager
    store     *store.Store
    interval  time.Duration
    timeout   time.Duration
}

// Start 启动心跳检测
func (c *HeartbeatChecker) Start(ctx context.Context)

// check 执行一次检测
func (c *HeartbeatChecker) check()

// 检测逻辑:
// 1. 遍历所有在线Session
// 2. 检查 last_ping 是否超过 timeout
// 3. 超时则断开连接，清理Session
```

---

## 6. 安全设计

### 6.1 Token生成

```go
// JWT Token配置
const (
    TokenSecret        = "your-secret-key"  // 从配置文件读取
    TokenExpireHours   = 24                  // Token有效期24小时
)

// GenerateToken 生成JWT Token
func GenerateToken(accountID uint64, sessionID string) (string, error)

// ValidateToken 验证Token
func ValidateToken(tokenString string) (*TokenClaims, error)

// TokenClaims Token声明
type TokenClaims struct {
    AccountID uint64 `json:"account_id"`
    SessionID string `json:"session_id"`
    jwt.RegisteredClaims
}
```

### 6.2 登录限流

```go
// LoginRateLimiter 登录限流器
type LoginRateLimiter struct {
    // IP -> 尝试次数 映射
    attempts map[string]*LoginAttempt
    mu       sync.Mutex
}

type LoginAttempt struct {
    Count     int
    LastTime  int64
    Blocked   bool
    BlockTime int64
}

// 限流规则:
// - 同一IP 5分钟内失败5次，锁定15分钟
// - 同一账户 5分钟内失败3次，锁定30分钟
```

---

## 7. 配置项

```yaml
# configs/config.yaml 添加

session:
  timeout: 30m              # Session超时时间
  max_lifetime: 24h         # 最大存活时间
  cleanup_interval: 5m      # 清理间隔
  max_sessions_per_account: 1  # 每个账户最大Session数 (1=单设备登录)

login:
  max_attempts: 5           # 最大尝试次数
  block_duration: 15m       # 锁定时间
  enable_captcha: false     # 是否启用验证码

security:
  jwt_secret: "change-in-production"
  token_expire_hours: 24
```

---

## 8. 接口清单

| 方法 | 路径 | 说明 | 优先级 |
|------|------|------|--------|
| POST | /api/v1/auth/login | 登录 | P0 |
| POST | /api/v1/auth/logout | 登出 | P0 |
| POST | /api/v1/auth/heartbeat | 心跳 | P0 |
| GET | /api/v1/auth/session | 获取Session | P1 |

---

## 9. 依赖关系

```
batch-02 (登录与账户系统)
    ├── store/account.go (已存在)
    ├── store/session.go (新建)
    ├── store/db/mysql/session.go (新建)
    ├── server/session/online_manager.go (新建)
    ├── server/auth/token.go (已存在，扩展)
    └── server/router/api/v1/auth_service.go (已存在，完善)
```

---

## 10. 风险评估

| 风险 | 概率 | 影响 | 缓解措施 |
|------|------|------|----------|
| Session并发问题 | 中 | 高 | 使用sync.RWMutex保护 |
| 内存泄漏 | 低 | 高 | 定期清理过期Session |
| Token安全问题 | 低 | 高 | 使用强密钥，定期更换 |

---

## 11. 变更记录

| 日期 | 版本 | 变更内容 | 作者 |
|------|------|----------|------|
| 2026-02-09 | v1.0 | 初始版本 | AI |

---

**下一步**: 编写测试计划 (tests.md)
