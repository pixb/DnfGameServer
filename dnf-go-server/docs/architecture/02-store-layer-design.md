# Store层架构设计文档

## 1. 概述

### 1.1 设计目标
实现可插拔的数据库驱动架构，支持：
- **多数据库支持**: MySQL (主)、SQLite (开发)、PostgreSQL (未来)
- **统一接口**: 所有数据库通过相同Driver接口操作
- **缓存层**: Store包装器提供透明缓存
- **迁移系统**: 版本化数据库迁移

### 1.2 与GORM的区别

| 特性 | GORM方式 | Driver方式 |
|-----|---------|-----------|
| 数据库切换 | 需要修改所有查询 | 仅切换配置 |
| 缓存 | 手动实现 | Store层自动处理 |
| SQL优化 | ORM限制 | 完全控制SQL |
| 测试 | 依赖真实数据库 | 可用SQLite内存模式 |

## 2. 目录结构

```
store/
├── driver.go              # Driver接口定义 (STRUCTURAL)
├── store.go               # Store包装器 (STRUCTURAL)
├── cache.go               # 缓存实现 (STRUCTURAL)
├── model.go               # 基础类型定义 (STRUCTURAL)
│
├── account.go             # Account模型 + Store方法 (BUSINESS)
├── role.go                # Role模型 + Store方法 (BUSINESS)
├── role_attributes.go     # RoleAttributes模型 (BUSINESS)
├── bag_item.go            # BagItem模型 (BUSINESS)
├── quest.go               # Quest模型 (BUSINESS)
├── guild.go               # Guild模型 (BUSINESS)
├── friend.go              # Friend模型 (BUSINESS)
├── mail.go                # Mail模型 (BUSINESS)
├── auction.go             # Auction模型 (BUSINESS)
├── shop.go                # Shop模型 (BUSINESS)
├── system_setting.go      # 系统设置 (BUSINESS)
│
├── db/
│   ├── db.go              # Driver工厂 (STRUCTURAL)
│   ├── mysql/             # MySQL实现 (BUSINESS)
│   │   ├── db.go          # MySQL Driver实现
│   │   ├── account.go     # Account CRUD
│   │   ├── role.go        # Role CRUD
│   │   └── ...
│   ├── sqlite/            # SQLite实现 (BUSINESS)
│   └── postgres/          # PostgreSQL实现 (BUSINESS)
│
└── migration/             # 数据库迁移 (BUSINESS)
    ├── mysql/
    │   ├── LATEST.sql     # 完整Schema
    │   └── 0.1/           # 版本0.1迁移脚本
    └── sqlite/
        └── ...
```

## 3. 核心接口定义

### 3.1 Driver接口 (store/driver.go)

```go
package store

import (
    "context"
    "database/sql"
)

// Driver 定义数据库操作接口
type Driver interface {
    // ==================== 生命周期 ====================
    GetDB() *sql.DB
    Close() error
    IsInitialized(ctx context.Context) (bool, error)
    
    // ==================== 账户相关 ====================
    CreateAccount(ctx context.Context, create *Account) (*Account, error)
    UpdateAccount(ctx context.Context, update *UpdateAccount) (*Account, error)
    GetAccount(ctx context.Context, find *FindAccount) (*Account, error)
    ListAccounts(ctx context.Context, find *FindAccount) ([]*Account, error)
    DeleteAccount(ctx context.Context, delete *DeleteAccount) error
    
    // ==================== 角色相关 ====================
    CreateRole(ctx context.Context, create *Role) (*Role, error)
    UpdateRole(ctx context.Context, update *UpdateRole) (*Role, error)
    GetRole(ctx context.Context, find *FindRole) (*Role, error)
    ListRoles(ctx context.Context, find *FindRole) ([]*Role, error)
    DeleteRole(ctx context.Context, delete *DeleteRole) error
    
    // ==================== 角色属性 ====================
    CreateRoleAttributes(ctx context.Context, create *RoleAttributes) (*RoleAttributes, error)
    UpdateRoleAttributes(ctx context.Context, update *UpdateRoleAttributes) (*RoleAttributes, error)
    GetRoleAttributes(ctx context.Context, find *FindRoleAttributes) (*RoleAttributes, error)
    
    // ==================== 背包物品 ====================
    CreateBagItem(ctx context.Context, create *BagItem) (*BagItem, error)
    UpdateBagItem(ctx context.Context, update *UpdateBagItem) (*BagItem, error)
    ListBagItems(ctx context.Context, find *FindBagItem) ([]*BagItem, error)
    DeleteBagItem(ctx context.Context, delete *DeleteBagItem) error
    
    // ==================== 任务相关 ====================
    CreateQuest(ctx context.Context, create *Quest) (*Quest, error)
    UpdateQuest(ctx context.Context, update *UpdateQuest) (*Quest, error)
    ListQuests(ctx context.Context, find *FindQuest) ([]*Quest, error)
    
    CreateRoleQuest(ctx context.Context, create *RoleQuest) (*RoleQuest, error)
    UpdateRoleQuest(ctx context.Context, update *UpdateRoleQuest) (*RoleQuest, error)
    ListRoleQuests(ctx context.Context, find *FindRoleQuest) ([]*RoleQuest, error)
    
    // ==================== 公会相关 ====================
    CreateGuild(ctx context.Context, create *Guild) (*Guild, error)
    UpdateGuild(ctx context.Context, update *UpdateGuild) (*Guild, error)
    GetGuild(ctx context.Context, find *FindGuild) (*Guild, error)
    ListGuilds(ctx context.Context, find *FindGuild) ([]*Guild, error)
    
    CreateGuildMember(ctx context.Context, create *GuildMember) (*GuildMember, error)
    UpdateGuildMember(ctx context.Context, update *UpdateGuildMember) (*GuildMember, error)
    ListGuildMembers(ctx context.Context, find *FindGuildMember) ([]*GuildMember, error)
    DeleteGuildMember(ctx context.Context, delete *DeleteGuildMember) error
    
    // ==================== 好友相关 ====================
    CreateFriend(ctx context.Context, create *Friend) (*Friend, error)
    ListFriends(ctx context.Context, find *FindFriend) ([]*Friend, error)
    DeleteFriend(ctx context.Context, delete *DeleteFriend) error
    
    CreateFriendRequest(ctx context.Context, create *FriendRequest) (*FriendRequest, error)
    ListFriendRequests(ctx context.Context, find *FindFriendRequest) ([]*FriendRequest, error)
    UpdateFriendRequest(ctx context.Context, update *UpdateFriendRequest) (*FriendRequest, error)
    
    // ==================== 邮件相关 ====================
    CreateMail(ctx context.Context, create *Mail) (*Mail, error)
    UpdateMail(ctx context.Context, update *UpdateMail) (*Mail, error)
    ListMails(ctx context.Context, find *FindMail) ([]*Mail, error)
    
    // ==================== 拍卖行相关 ====================
    CreateAuction(ctx context.Context, create *Auction) (*Auction, error)
    UpdateAuction(ctx context.Context, update *UpdateAuction) (*Auction, error)
    ListAuctions(ctx context.Context, find *FindAuction) ([]*Auction, error)
    
    // ==================== 系统设置 ====================
    UpsertSystemSetting(ctx context.Context, setting *SystemSetting) error
    GetSystemSetting(ctx context.Context, name string) (*SystemSetting, error)
    ListSystemSettings(ctx context.Context) ([]*SystemSetting, error)
}
```

### 3.2 Store包装器 (store/store.go)

```go
package store

import (
    "context"
    "time"
    
    "github.com/patrickmn/go-cache"
    "github.com/dnf-go-server/internal/profile"
)

// Store 包装Driver，添加缓存层
type Store struct {
    driver  Driver
    profile *profile.Profile
    
    // 缓存配置
    cacheConfig cache.Config
    
    // 各模型的缓存
    accountCache     *cache.Cache
    roleCache        *cache.Cache
    guildCache       *cache.Cache
    systemSettingCache *cache.Cache
}

// New 创建Store实例
func New(driver Driver, profile *profile.Profile) *Store {
    cacheConfig := cache.Config{
        DefaultTTL:      10 * time.Minute,
        CleanupInterval: 5 * time.Minute,
        MaxItems:        1000,
    }
    
    return &Store{
        driver:             driver,
        profile:            profile,
        cacheConfig:        cacheConfig,
        accountCache:       cache.New(cacheConfig),
        roleCache:          cache.New(cacheConfig),
        guildCache:         cache.New(cacheConfig),
        systemSettingCache: cache.New(cacheConfig),
    }
}

func (s *Store) GetDriver() Driver { return s.driver }

func (s *Store) Close() error {
    s.accountCache.Close()
    s.roleCache.Close()
    s.guildCache.Close()
    s.systemSettingCache.Close()
    return s.driver.Close()
}
```

## 4. 模型定义

### 4.1 基础类型 (store/model.go)

```go
package store

type RowStatus string

const (
    RowStatusNormal    RowStatus = "NORMAL"
    RowStatusArchived  RowStatus = "ARCHIVED"
    RowStatusDeleted   RowStatus = "DELETED"
)

type Role int

const (
    RoleUser  Role = "USER"
    RoleAdmin Role = "ADMIN"
    RoleGM    Role = "GM"
)
```

### 4.2 Account模型 (store/account.go)

```go
package store

import (
    "context"
    "time"
)

// Account 账户模型
type Account struct {
    ID        int32
    RowStatus RowStatus
    CreatedTs int64
    UpdatedTs int64
    
    Username     string
    PasswordHash string
    Email        string
    Phone        string
    Status       int32  // 0-正常, 1-冻结
    LastLoginAt  *int64
    LastLoginIP  string
}

// UpdateAccount 更新账户 (指针字段表示可选)
type UpdateAccount struct {
    ID           int32
    UpdatedTs    *int64
    RowStatus    *RowStatus
    PasswordHash *string
    Email        *string
    Phone        *string
    Status       *int32
    LastLoginAt  *int64
    LastLoginIP  *string
}

// FindAccount 查询账户
type FindAccount struct {
    ID        *int32
    Username  *string
    Email     *string
    Phone     *string
    Status    *int32
    RowStatus *RowStatus
    Limit     *int
}

type DeleteAccount struct{ ID int32 }

// ==================== Store方法 (带缓存) ====================

func (s *Store) CreateAccount(ctx context.Context, create *Account) (*Account, error) {
    account, err := s.driver.CreateAccount(ctx, create)
    if err != nil {
        return nil, err
    }
    s.accountCache.Set(ctx, string(account.ID), account)
    return account, nil
}

func (s *Store) GetAccount(ctx context.Context, find *FindAccount) (*Account, error) {
    // 尝试从缓存获取
    if find.ID != nil {
        if cached, ok := s.accountCache.Get(ctx, string(*find.ID)); ok {
            if account, ok := cached.(*Account); ok {
                return account, nil
            }
        }
    }
    
    // 从数据库获取
    list, err := s.driver.ListAccounts(ctx, find)
    if err != nil {
        return nil, err
    }
    if len(list) == 0 {
        return nil, ErrNotFound
    }
    
    // 写入缓存
    s.accountCache.Set(ctx, string(list[0].ID), list[0])
    return list[0], nil
}

func (s *Store) UpdateAccount(ctx context.Context, update *UpdateAccount) (*Account, error) {
    account, err := s.driver.UpdateAccount(ctx, update)
    if err != nil {
        return nil, err
    }
    s.accountCache.Set(ctx, string(account.ID), account)
    return account, nil
}

func (s *Store) DeleteAccount(ctx context.Context, delete *DeleteAccount) error {
    if err := s.driver.DeleteAccount(ctx, delete); err != nil {
        return err
    }
    s.accountCache.Delete(ctx, string(delete.ID))
    return nil
}
```

### 4.3 Role模型 (store/role.go)

```go
package store

type Role struct {
    ID        int32
    RowStatus RowStatus
    CreatedTs int64
    UpdatedTs int64
    
    AccountID    int32
    Name         string
    Profession   int32   // 职业
    Level        int32
    Exp          int64
    Gold         int64
    Diamonds     int64
    Fatigue      int32   // 疲劳值
    MaxFatigue   int32
    Status       int32   // 0-正常, 1-删除
}

type UpdateRole struct {
    ID         int32
    UpdatedTs  *int64
    RowStatus  *RowStatus
    Name       *string
    Level      *int32
    Exp        *int64
    Gold       *int64
    Diamonds   *int64
    Fatigue    *int32
    MaxFatigue *int32
    Status     *int32
}

type FindRole struct {
    ID        *int32
    AccountID *int32
    Name      *string
    Status    *int32
    RowStatus *RowStatus
    Limit     *int
}

type DeleteRole struct{ ID int32 }

// Store方法实现 (类似Account)
```

### 4.4 RoleAttributes模型 (store/role_attributes.go)

```go
package store

// RoleAttributes 角色属性
type RoleAttributes struct {
    ID     int32
    RoleID int32
    
    // 基础属性
    Strength     int32  // 力量
    Intelligence int32  // 智力
    Vitality     int32  // 体力
    Spirit       int32  // 精神
    
    // 战斗属性
    HP          int32
    MaxHP       int32
    MP          int32
    MaxMP       int32
    Attack      int32
    Defense     int32
    MagicAttack int32
    MagicDefense int32
    
    // 其他属性
    MoveSpeed   int32
    AttackSpeed int32
    CastSpeed   int32
}

type UpdateRoleAttributes struct {
    ID           int32
    Strength     *int32
    Intelligence *int32
    Vitality     *int32
    Spirit       *int32
    HP           *int32
    MaxHP        *int32
    MP           *int32
    MaxMP        *int32
    Attack       *int32
    Defense      *int32
    MagicAttack  *int32
    MagicDefense *int32
    MoveSpeed    *int32
    AttackSpeed  *int32
    CastSpeed    *int32
}

type FindRoleAttributes struct {
    ID     *int32
    RoleID *int32
}
```

### 4.5 BagItem模型 (store/bag_item.go)

```go
package store

// BagItem 背包物品
type BagItem struct {
    ID        int32
    CreatedTs int64
    
    RoleID   int32  // 所属角色
    ItemID   int32  // 物品ID
    Count    int32  // 数量
    Position int32  // 背包位置
    IsBound  bool   // 是否绑定
    ExtraData string // 额外数据(JSON)
}

type UpdateBagItem struct {
    ID        int32
    Count     *int32
    Position  *int32
    IsBound   *bool
    ExtraData *string
}

type FindBagItem struct {
    ID       *int32
    RoleID   *int32
    ItemID   *int32
    Position *int32
}

type DeleteBagItem struct{ ID int32 }
```

## 5. MySQL实现

### 5.1 MySQL Driver (store/db/mysql/db.go)

```go
package mysql

import (
    "context"
    "database/sql"
    "fmt"
    
    _ "github.com/go-sql-driver/mysql"
    "github.com/dnf-go-server/internal/profile"
    "github.com/dnf-go-server/store"
)

type DB struct {
    db *sql.DB
}

func NewDB(profile *profile.Profile) (store.Driver, error) {
    db, err := sql.Open("mysql", profile.DSN)
    if err != nil {
        return nil, err
    }
    
    db.SetMaxOpenConns(25)
    db.SetMaxIdleConns(10)
    
    return &DB{db: db}, nil
}

func (d *DB) GetDB() *sql.DB { return d.db }

func (d *DB) Close() error { return d.db.Close() }

func (d *DB) IsInitialized(ctx context.Context) (bool, error) {
    query := `SELECT 1 FROM account LIMIT 1`
    var dummy int
    err := d.db.QueryRowContext(ctx, query).Scan(&dummy)
    if err == sql.ErrNoRows {
        return true, nil // 表存在但无数据
    }
    if err != nil {
        return false, nil // 表不存在
    }
    return true, nil
}
```

### 5.2 Account CRUD (store/db/mysql/account.go)

```go
package mysql

import (
    "context"
    "database/sql"
    "fmt"
    "strings"
    
    "github.com/dnf-go-server/store"
)

func (d *DB) CreateAccount(ctx context.Context, create *store.Account) (*store.Account, error) {
    query := `
        INSERT INTO account (row_status, created_ts, updated_ts, username, password_hash, email, phone, status)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    `
    
    result, err := d.db.ExecContext(ctx, query,
        create.RowStatus, create.CreatedTs, create.UpdatedTs,
        create.Username, create.PasswordHash, create.Email, create.Phone, create.Status,
    )
    if err != nil {
        return nil, err
    }
    
    id, _ := result.LastInsertId()
    create.ID = int32(id)
    return create, nil
}

func (d *DB) UpdateAccount(ctx context.Context, update *store.UpdateAccount) (*store.Account, error) {
    // 动态构建SET子句
    var sets []string
    var args []interface{}
    
    if update.RowStatus != nil {
        sets = append(sets, "row_status = ?")
        args = append(args, *update.RowStatus)
    }
    if update.PasswordHash != nil {
        sets = append(sets, "password_hash = ?")
        args = append(args, *update.PasswordHash)
    }
    if update.Email != nil {
        sets = append(sets, "email = ?")
        args = append(args, *update.Email)
    }
    if update.Phone != nil {
        sets = append(sets, "phone = ?")
        args = append(args, *update.Phone)
    }
    if update.Status != nil {
        sets = append(sets, "status = ?")
        args = append(args, *update.Status)
    }
    if update.LastLoginAt != nil {
        sets = append(sets, "last_login_at = ?")
        args = append(args, *update.LastLoginAt)
    }
    if update.LastLoginIP != nil {
        sets = append(sets, "last_login_ip = ?")
        args = append(args, *update.LastLoginIP)
    }
    
    if len(sets) == 0 {
        return d.GetAccount(ctx, &store.FindAccount{ID: &update.ID})
    }
    
    sets = append(sets, "updated_ts = ?")
    args = append(args, time.Now().Unix())
    args = append(args, update.ID)
    
    query := fmt.Sprintf("UPDATE account SET %s WHERE id = ?", strings.Join(sets, ", "))
    _, err := d.db.ExecContext(ctx, query, args...)
    if err != nil {
        return nil, err
    }
    
    return d.GetAccount(ctx, &store.FindAccount{ID: &update.ID})
}

func (d *DB) ListAccounts(ctx context.Context, find *store.FindAccount) ([]*store.Account, error) {
    var where []string
    var args []interface{}
    
    if find.ID != nil {
        where = append(where, "id = ?")
        args = append(args, *find.ID)
    }
    if find.Username != nil {
        where = append(where, "username = ?")
        args = append(args, *find.Username)
    }
    if find.Email != nil {
        where = append(where, "email = ?")
        args = append(args, *find.Email)
    }
    if find.Status != nil {
        where = append(where, "status = ?")
        args = append(args, *find.Status)
    }
    if find.RowStatus != nil {
        where = append(where, "row_status = ?")
        args = append(args, *find.RowStatus)
    }
    
    query := "SELECT id, row_status, created_ts, updated_ts, username, password_hash, email, phone, status, last_login_at, last_login_ip FROM account"
    if len(where) > 0 {
        query += " WHERE " + strings.Join(where, " AND ")
    }
    
    rows, err := d.db.QueryContext(ctx, query, args...)
    if err != nil {
        return nil, err
    }
    defer rows.Close()
    
    var accounts []*store.Account
    for rows.Next() {
        var a store.Account
        err := rows.Scan(&a.ID, &a.RowStatus, &a.CreatedTs, &a.UpdatedTs, &a.Username,
            &a.PasswordHash, &a.Email, &a.Phone, &a.Status, &a.LastLoginAt, &a.LastLoginIP)
        if err != nil {
            return nil, err
        }
        accounts = append(accounts, &a)
    }
    
    return accounts, nil
}

func (d *DB) DeleteAccount(ctx context.Context, delete *store.DeleteAccount) error {
    query := "DELETE FROM account WHERE id = ?"
    _, err := d.db.ExecContext(ctx, query, delete.ID)
    return err
}
```

## 6. 数据库迁移

### 6.1 Schema定义 (store/migration/mysql/LATEST.sql)

```sql
-- 账户表
CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) DEFAULT '',
    phone VARCHAR(20) DEFAULT '',
    status INT DEFAULT 0,
    last_login_at BIGINT,
    last_login_ip VARCHAR(50),
    INDEX idx_username (username),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 角色表
CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    row_status VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    account_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    profession INT NOT NULL,
    level INT DEFAULT 1,
    exp BIGINT DEFAULT 0,
    gold BIGINT DEFAULT 0,
    diamonds BIGINT DEFAULT 0,
    fatigue INT DEFAULT 100,
    max_fatigue INT DEFAULT 100,
    status INT DEFAULT 0,
    INDEX idx_account_id (account_id),
    INDEX idx_name (name),
    FOREIGN KEY (account_id) REFERENCES account(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 角色属性表
CREATE TABLE role_attributes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_id INT NOT NULL UNIQUE,
    strength INT DEFAULT 10,
    intelligence INT DEFAULT 10,
    vitality INT DEFAULT 10,
    spirit INT DEFAULT 10,
    hp INT DEFAULT 100,
    max_hp INT DEFAULT 100,
    mp INT DEFAULT 50,
    max_mp INT DEFAULT 50,
    attack INT DEFAULT 10,
    defense INT DEFAULT 5,
    magic_attack INT DEFAULT 10,
    magic_defense INT DEFAULT 5,
    move_speed INT DEFAULT 100,
    attack_speed INT DEFAULT 100,
    cast_speed INT DEFAULT 100,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 背包物品表
CREATE TABLE bag_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    role_id INT NOT NULL,
    item_id INT NOT NULL,
    count INT DEFAULT 1,
    position INT NOT NULL,
    is_bound BOOLEAN DEFAULT FALSE,
    extra_data TEXT,
    INDEX idx_role_id (role_id),
    INDEX idx_item_id (item_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 任务表
CREATE TABLE quest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    quest_id INT NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    type INT NOT NULL, -- 0-主线, 1-支线, 2-日常
    min_level INT DEFAULT 1,
    reward_exp INT DEFAULT 0,
    reward_gold INT DEFAULT 0,
    reward_items TEXT -- JSON格式
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 角色任务进度表
CREATE TABLE role_quest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    role_id INT NOT NULL,
    quest_id INT NOT NULL,
    status INT DEFAULT 0, -- 0-未接受, 1-进行中, 2-可完成, 3-已完成
    progress TEXT, -- JSON格式进度数据
    completed_at BIGINT,
    UNIQUE KEY uk_role_quest (role_id, quest_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 公会表
CREATE TABLE guild (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    name VARCHAR(50) NOT NULL UNIQUE,
    leader_id INT NOT NULL,
    level INT DEFAULT 1,
    exp INT DEFAULT 0,
    funds BIGINT DEFAULT 0,
    member_count INT DEFAULT 1,
    max_members INT DEFAULT 50,
    notice TEXT,
    INDEX idx_leader_id (leader_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 公会成员表
CREATE TABLE guild_member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    guild_id INT NOT NULL,
    role_id INT NOT NULL,
    rank INT DEFAULT 0, -- 0-成员, 1-精英, 2-副会长, 3-会长
    contribution INT DEFAULT 0,
    last_contribution_ts BIGINT,
    UNIQUE KEY uk_guild_role (guild_id, role_id),
    FOREIGN KEY (guild_id) REFERENCES guild(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 好友表
CREATE TABLE friend (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    role_id INT NOT NULL,
    friend_role_id INT NOT NULL,
    intimacy INT DEFAULT 0,
    UNIQUE KEY uk_friend (role_id, friend_role_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
    FOREIGN KEY (friend_role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 好友申请表
CREATE TABLE friend_request (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    from_role_id INT NOT NULL,
    to_role_id INT NOT NULL,
    status INT DEFAULT 0, -- 0-待处理, 1-已接受, 2-已拒绝
    message TEXT,
    handled_at BIGINT,
    UNIQUE KEY uk_request (from_role_id, to_role_id),
    FOREIGN KEY (from_role_id) REFERENCES role(id) ON DELETE CASCADE,
    FOREIGN KEY (to_role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 邮件表
CREATE TABLE mail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    role_id INT NOT NULL,
    sender_name VARCHAR(50),
    title VARCHAR(200) NOT NULL,
    content TEXT,
    attachments TEXT, -- JSON格式
    is_read BOOLEAN DEFAULT FALSE,
    is_claimed BOOLEAN DEFAULT FALSE,
    expired_at BIGINT,
    INDEX idx_role_id (role_id),
    INDEX idx_expired (expired_at),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 拍卖行表
CREATE TABLE auction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    updated_ts BIGINT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    seller_role_id INT NOT NULL,
    item_id INT NOT NULL,
    count INT DEFAULT 1,
    starting_price BIGINT NOT NULL,
    buyout_price BIGINT,
    current_bid BIGINT,
    current_bidder_id INT,
    status INT DEFAULT 0, -- 0-拍卖中, 1-已售出, 2-已取消, 3-已过期
    expired_at BIGINT NOT NULL,
    INDEX idx_status (status),
    INDEX idx_expired (expired_at),
    FOREIGN KEY (seller_role_id) REFERENCES role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 系统设置表
CREATE TABLE system_setting (
    name VARCHAR(100) PRIMARY KEY,
    value TEXT NOT NULL,
    description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## 7. Driver工厂 (store/db/db.go)

```go
package db

import (
    "fmt"
    
    "github.com/dnf-go-server/internal/profile"
    "github.com/dnf-go-server/store"
    "github.com/dnf-go-server/store/db/mysql"
)

func NewDBDriver(profile *profile.Profile) (store.Driver, error) {
    switch profile.Driver {
    case "mysql":
        return mysql.NewDB(profile)
    case "sqlite":
        // TODO: 实现SQLite驱动
        return nil, fmt.Errorf("sqlite driver not implemented yet")
    case "postgres":
        // TODO: 实现PostgreSQL驱动
        return nil, fmt.Errorf("postgres driver not implemented yet")
    default:
        return nil, fmt.Errorf("unsupported driver: %s", profile.Driver)
    }
}
```

## 8. 错误定义

```go
package store

import "errors"

var (
    ErrNotFound      = errors.New("not found")
    ErrDuplicate     = errors.New("duplicate entry")
    ErrInvalidInput  = errors.New("invalid input")
    ErrDBConnection  = errors.New("database connection error")
)
```

## 9. 使用示例

```go
package main

import (
    "context"
    "log"
    
    "github.com/dnf-go-server/internal/profile"
    "github.com/dnf-go-server/store"
    "github.com/dnf-go-server/store/db"
)

func main() {
    // 1. 创建profile
    prof := &profile.Profile{
        Driver: "mysql",
        DSN:    "user:password@tcp(localhost:3306)/dnf?charset=utf8mb4&parseTime=True&loc=Local",
    }
    
    // 2. 创建Driver
    driver, err := db.NewDBDriver(prof)
    if err != nil {
        log.Fatal(err)
    }
    
    // 3. 创建Store (带缓存)
    s := store.New(driver, prof)
    defer s.Close()
    
    // 4. 使用Store
    ctx := context.Background()
    
    // 创建账户
    account, err := s.CreateAccount(ctx, &store.Account{
        Username:     "player1",
        PasswordHash: "hashed_password",
        Email:        "player1@example.com",
    })
    if err != nil {
        log.Fatal(err)
    }
    
    // 查询账户 (会自动使用缓存)
    foundAccount, err := s.GetAccount(ctx, &store.FindAccount{
        ID: &account.ID,
    })
    if err != nil {
        log.Fatal(err)
    }
    
    log.Printf("Found account: %s", foundAccount.Username)
}
```

## 10. 性能考虑

### 10.1 缓存策略
- 账户数据: 缓存10分钟
- 角色数据: 缓存10分钟
- 公会数据: 缓存10分钟
- 背包物品: 不缓存(变化频繁)

### 10.2 数据库连接池
- MaxOpenConns: 25
- MaxIdleConns: 10
- ConnMaxLifetime: 1小时

### 10.3 批量操作
```go
// 批量插入示例
func (d *DB) BatchCreateBagItems(ctx context.Context, items []*BagItem) error {
    tx, err := d.db.BeginTx(ctx, nil)
    if err != nil {
        return err
    }
    defer tx.Rollback()
    
    stmt, err := tx.PrepareContext(ctx, `
        INSERT INTO bag_item (role_id, item_id, count, position, is_bound, extra_data)
        VALUES (?, ?, ?, ?, ?, ?)
    `)
    if err != nil {
        return err
    }
    defer stmt.Close()
    
    for _, item := range items {
        _, err := stmt.ExecContext(ctx, item.RoleID, item.ItemID, item.Count, 
            item.Position, item.IsBound, item.ExtraData)
        if err != nil {
            return err
        }
    }
    
    return tx.Commit()
}
```

---

**创建日期**: 2026-02-09
**版本**: v1.0
**状态**: 设计完成，待实现
