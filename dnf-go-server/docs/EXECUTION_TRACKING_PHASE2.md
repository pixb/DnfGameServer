# Phase 2 背包系统执行追踪

## 前置条件
- ✅ Phase 1.1 认证模块已完成
- ✅ Phase 1.2 角色属性系统已完成
- ✅ Store 层基础架构已就绪
- ✅ 背包表已创建 (bag_item)

## 任务列表

### ✅ 已完成

#### 2.1 GetBag - 获取背包 ✅
- [x] 查询角色所有物品
- [x] 转换为proto格式
- [x] 返回背包信息

#### 2.2 MoveItem - 移动物品 ✅
- [x] 验证源物品存在
- [x] 检查目标格子
- [x] 处理物品交换
- [x] 处理物品合并
- [x] 数据库更新

#### 2.3 UseItem - 使用物品 ✅
- [x] 验证物品存在
- [x] 检查使用数量
- [x] 减少/删除物品
- [x] 返回更新后的物品列表

#### 2.4 SellItem - 出售物品 ✅
- [x] 计算出售价格
- [x] 验证物品存在
- [x] 删除/减少物品
- [x] 返回获得的金币

#### 2.5 EquipItem - 装备物品 ✅
- [x] 验证物品存在
- [x] 装备/卸下切换
- [x] 数据库更新

## 代码变更摘要

### 1. Store 层扩展 (`store/store.go`)
```go
+ GetBagItem() - 获取单个物品
```

### 2. 服务实现 (`server/router/api/v1/services.go`)
```go
// 已实现的方法
+ GetBag() - 获取背包（完整实现）
+ MoveItem() - 移动物品（完整实现）
+ UseItem() - 使用物品（完整实现）
+ SellItem() - 出售物品（完整实现）
+ EquipItem() - 装备物品（完整实现）
```

## 功能特性

### GetBag 获取背包
- ✅ 认证检查
- ✅ 查询角色所有物品
- ✅ 转换为proto格式
- ✅ 返回背包信息

### MoveItem 移动物品
- ✅ 认证检查
- ✅ 源物品验证
- ✅ 目标格子检查
- ✅ 物品交换逻辑
- ✅ 物品合并逻辑（相同物品）
- ✅ 数据库更新

### UseItem 使用物品
- ✅ 认证检查
- ✅ 物品存在验证
- ✅ 数量检查
- ✅ 物品减少/删除
- ✅ 返回更新后的背包
- ⚠️ 物品效果应用（TODO）

### SellItem 出售物品
- ✅ 认证检查
- ✅ 物品存在验证
- ✅ 价格计算（简化公式）
- ✅ 物品删除/减少
- ✅ 返回获得金币
- ⚠️ 金币增加（TODO）

### EquipItem 装备物品
- ✅ 认证检查
- ✅ 物品存在验证
- ✅ 装备/卸下切换
- ✅ 数据库更新
- ⚠️ 装备条件验证（TODO）
- ⚠️ 同类型装备处理（TODO）

## 测试验证

### 构建测试
```bash
cd dnf-go-server
go build -o bin/dnf-server ./cmd/server
# ✅ 构建成功
```

### 版本检查
```bash
./bin/dnf-server version
# DNF Go Server
# Version: dev
```

## 数据结构

### 背包物品 (BagItem)
```go
type BagItem struct {
    ID           uint64
    RoleID       uint64
    ItemID       int32   // 物品模板ID
    GridIndex    int32   // 格子索引
    Count        int32   // 数量
    IsEquiped    bool    // 是否已装备
    BindType     int32   // 绑定类型
    Durability   int32   // 耐久度
    EnhanceLevel int32   // 强化等级
    Attributes   string  // 附加属性(JSON)
}
```

## 价格计算

出售价格简化公式：
```go
basePrice := item.ItemID * 10  // 基础价格
totalPrice := basePrice * count // 总价
```

## 待完善功能

1. **UseItem** - 根据物品类型应用不同效果
2. **SellItem** - 实际增加角色金币
3. **EquipItem** - 添加装备条件验证（职业、等级）
4. **EquipItem** - 处理已装备的同类型装备
5. **MoveItem** - 添加格子范围验证

## 接下来的批次

### Phase 3 - 副本系统
- [ ] EnterDungeon - 进入副本
- [ ] ExitDungeon - 退出副本
- [ ] Revive - 复活
- [ ] ChangeRoom - 切换房间

### Phase 4 - 聊天与社交
- [ ] SendChat - 发送聊天
- [ ] GetChatHistory - 聊天历史
- [ ] AddFriend - 添加好友
- [ ] GetFriendList - 好友列表

---

**开始时间**: 2026-02-09
**完成时间**: 2026-02-09
**状态**: ✅ 已完成
**版本**: v1.3.0
