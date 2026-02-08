# Java 到 Go 分批重构执行计划

## 现状分析

### 已完成
- ✅ ProtoBuf 协议定义 (100+ 消息类型)
- ✅ TCP 网络层框架 (Apache MINA 替代)
- ✅ Store 层架构 (Driver + Cache + Migration)
- ✅ 基础 gRPC/HTTP 服务框架
- ⚠️ Handler 层 (internal/game/handlers/) - 使用模拟数据
- ⚠️ Service 层 (server/router/api/v1/services.go) - 简化实现

### 核心问题
1. **Handler 层** - 仅返回模拟数据，未连接真实数据库
2. **Service 层** - 基本框架完成，但业务逻辑不完整
3. **数据验证** - 缺少参数校验和数据验证
4. **错误处理** - 错误码和错误信息需要完善

## 重构策略

### 目标
将 `internal/game/handlers/` (旧 TCP 层) 的业务逻辑迁移到 `server/router/api/v1/services.go` (新 gRPC/HTTP 层)，并实现完整的数据库操作。

### 分批原则
1. **核心优先** - 认证、角色、背包等核心功能先实现
2. **依赖排序** - 先实现基础功能，再实现依赖功能
3. **可测试性** - 每批完成后可独立测试
4. **向后兼容** - 保持与 Java 版本的协议兼容

## 分批计划

### Phase 1: 认证与角色管理 (核心功能) 🔥
**优先级**: P0 (最高)
**预计工时**: 2-3 天
**状态**: 🚧 进行中

#### 1.1 认证模块
- [ ] **Login** - 完整登录流程
  - 验证账户存在
  - 生成 Token (JWT)
  - 记录登录日志
  - 返回频道列表
  
- [ ] **CreateCharacter** - 创建角色
  - 检查角色名重复
  - 检查角色数量限制
  - 创建角色基础数据
  - 初始化角色属性
  - 初始化角色货币
  
- [ ] **GetCharacterList** - 获取角色列表
  - 查询账户下所有角色
  - 返回角色基本信息
  
- [ ] **SelectCharacter** - 选择角色
  - 验证角色归属
  - 加载角色完整数据
  - 返回游戏服务器地址

#### 1.2 角色信息模块
- [ ] **GetRoleInfo** - 获取角色详细信息
  - 基础信息
  - 属性信息
  - 货币信息
  
- [ ] **UpdateAttributes** - 更新角色属性
  - 属性加点
  - 验证可用点数

**完成标准**:
- 用户可正常登录
- 可创建、查看、选择角色
- 数据持久化到数据库
- 通过单元测试

---

### Phase 2: 背包与物品系统 🔥
**优先级**: P0
**预计工时**: 2-3 天
**依赖**: Phase 1

#### 2.1 背包管理
- [ ] **GetBag** - 获取背包
  - 查询所有格子
  - 按类型分类
  
- [ ] **MoveItem** - 移动物品
  - 验证格子合法性
  - 交换/移动物品
  
- [ ] **UseItem** - 使用物品
  - 验证物品存在
  - 减少数量/删除
  - 应用效果
  
- [ ] **SellItem** - 出售物品
  - 计算价格
  - 删除物品
  - 增加金币

#### 2.2 装备系统
- [ ] **EquipItem** - 装备物品
  - 验证装备条件
  - 替换已装备
  - 更新属性
  
- [ ] **UnequipItem** - 卸下装备
  - 检查背包空间
  - 移动回背包

**完成标准**:
- 物品的增删改查
- 装备/卸下逻辑
- 物品使用效果

---

### Phase 3: 副本系统 (游戏核心) 🔥
**优先级**: P0
**预计工时**: 3-4 天
**依赖**: Phase 1-2

#### 3.1 副本管理
- [ ] **EnterDungeon** - 进入副本
  - 验证等级条件
  - 检查疲劳值
  - 扣除门票
  - 创建副本实例
  
- [ ] **ExitDungeon** - 退出副本
  - 结算奖励
  - 保存进度
  
- [ ] **ChangeRoom** - 切换房间
  - 房间状态管理
  
- [ ] **Revive** - 复活
  - 扣除复活币
  - 恢复 HP/MP

#### 3.2 战斗系统
- [ ] **MonsterKill** - 怪物击杀
  - 经验计算
  - 掉落生成
  
- [ ] **BossKill** - BOSS击杀
  - 高级掉落
  - 副本完成判定
  
- [ ] **DungeonComplete** - 副本完成
  - 结算经验
  - 发放奖励

**完成标准**:
- 完整的副本流程
- 战斗结算逻辑
- 奖励发放

---

### Phase 4: 聊天与社交系统
**优先级**: P1
**预计工时**: 2 天
**依赖**: Phase 1

#### 4.1 聊天系统
- [ ] **SendChat** - 发送聊天
  - 频道管理 (世界/公会/队伍/私聊)
  - 敏感词过滤
  - 频率限制
  
- [ ] **GetChatHistory** - 获取聊天记录
  - 分页查询
  - 时间范围

#### 4.2 好友系统
- [ ] **GetFriendList** - 获取好友列表
  - 在线状态
  
- [ ] **AddFriend** - 添加好友
  - 发送申请
  - 验证角色存在
  
- [ ] **ReplyFriendRequest** - 回复好友申请
  - 同意/拒绝
  
- [ ] **RemoveFriend** - 删除好友

**完成标准**:
- 多频道聊天
- 好友管理
- 在线状态同步

---

### Phase 5: 商店与经济系统
**优先级**: P1
**预计工时**: 2-3 天
**依赖**: Phase 2

#### 5.1 商店系统
- [ ] **GetShopList** - 获取商店列表
  - NPC商店
  - 商品配置
  
- [ ] **BuyItem** - 购买物品
  - 验证货币
  - 扣除货币
  - 添加物品
  
- [ ] **SellToShop** - 出售给商店
  - 计算价格
  - 删除物品
  - 增加货币

#### 5.2 拍卖系统
- [ ] **SearchAuction** - 搜索拍卖行
  - 分页
  - 筛选条件
  
- [ ] **RegisterAuction** - 上架拍卖
  - 验证物品
  - 设置价格
  - 扣除手续费
  
- [ ] **BidAuction** - 竞拍
  - 验证出价
  - 扣除货币
  
- [ ] **BuyoutAuction** - 一口价购买

**完成标准**:
- 完整的交易流程
- 货币计算准确
- 拍卖逻辑正确

---

### Phase 6: 任务系统
**优先级**: P1
**预计工时**: 2 天
**依赖**: Phase 1-3

#### 6.1 任务管理
- [ ] **GetQuestList** - 获取任务列表
  - 可接任务
  - 进行中任务
  
- [ ] **AcceptQuest** - 接受任务
  - 验证条件
  - 创建任务进度
  
- [ ] **CompleteQuest** - 完成任务
  - 验证目标
  - 发放奖励
  
- [ ] **GetQuestReward** - 领取奖励
  
- [ ] **AbandonQuest** - 放弃任务

#### 6.2 任务进度
- [ ] **QuestProgressNotify** - 任务进度通知
  - 击杀怪物
  - 收集物品
  - 自动完成判定

**完成标准**:
- 任务流程完整
- 进度自动更新
- 奖励发放

---

### Phase 7: 公会系统
**优先级**: P2
**预计工时**: 2-3 天
**依赖**: Phase 1, 4

#### 7.1 公会管理
- [ ] **GetGuildInfo** - 获取公会信息
  - 基本信息
  - 成员列表
  
- [ ] **CreateGuild** - 创建公会
  - 验证条件
  - 扣除费用
  
- [ ] **JoinGuild** - 加入公会
  - 申请/直接加入
  
- [ ] **LeaveGuild** - 离开公会

#### 7.2 公会功能
- [ ] **GuildDonate** - 公会捐赠
  - 贡献值计算
  
- [ ] **GetGuildSkill** - 获取公会技能
  
- [ ] **UpgradeGuildSkill** - 升级公会技能
  - 验证权限
  - 扣除资金

**完成标准**:
- 公会完整生命周期
- 权限管理
- 公会技能

---

## 执行时间表

```
Week 1: Phase 1 (认证与角色)
Week 2: Phase 2 (背包与物品)
Week 3: Phase 3 (副本系统)
Week 4: Phase 4-5 (社交与经济)
Week 5: Phase 6-7 (任务与公会)
Week 6: 测试优化与Bug修复
```

## 技术实现要点

### 1. 事务管理
所有涉及多表操作的功能必须使用数据库事务：

```go
func (s *Store) TransferItem(ctx context.Context, from, to uint64, itemID int64) error {
    tx, err := s.driver.GetDB().BeginTx(ctx, nil)
    if err != nil {
        return err
    }
    defer tx.Rollback()
    
    // 1. 扣除发送方物品
    // 2. 增加接收方物品
    // 3. 记录日志
    
    return tx.Commit()
}
```

### 2. 缓存策略
- **读多写少**: 使用缓存 (account, role_info)
- **写多读少**: 直接查询 (bag_item, mail)
- **实时性要求高**: 不缓存 (chat, auction)

### 3. 错误码规范
保持与 Java 版本一致的错误码：

```go
const (
    ErrCodeSuccess        = 0
    ErrCodeInvalidParam   = 1
    ErrCodeNotFound       = 2
    ErrCodeNotEnoughMoney = 3
    ErrCodeNotEnoughItem  = 4
    ErrCodeLevelTooLow    = 5
    ErrCodeDuplicateName  = 6
    // ...
)
```

### 4. 日志规范
每个操作必须有完整的日志：

```go
logger.Info("player bought item",
    logger.Uint64("role_id", roleID),
    logger.Int64("item_id", itemID),
    logger.Int32("count", count),
    logger.Int64("cost", cost),
)
```

## 测试策略

### 单元测试
- 每个 Service 方法必须有测试
- 覆盖正常流程和异常流程
- 使用 SQLite 内存数据库

### 集成测试
- 完整的业务流程测试
- 多模块交互测试
- 并发安全测试

### 性能测试
- 登录并发测试
- 背包操作压力测试
- 副本进入/退出测试

## 当前执行状态

### Phase 1.1 认证模块 - 🚧 进行中

下一步任务:
1. 完善 Login 方法实现
2. 实现角色名唯一性检查
3. 完善 CreateCharacter 流程
4. 添加单元测试

---

**最后更新**: 2026-02-09
**版本**: v1.0
