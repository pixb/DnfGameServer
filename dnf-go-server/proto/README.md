# DNF Protocol Buffer 协议定义

## 项目结构

```
proto/
├── buf.yaml              # Buf 配置文件
├── buf.gen.yaml          # 代码生成配置
└── dnf/v1/              # API v1 版本
    ├── common.proto     # 公共类型
    ├── auth.proto       # 登录认证
    ├── role.proto       # 角色系统
    ├── item.proto       # 物品/背包
    ├── dungeon.proto    # 副本系统
    ├── chat.proto       # 聊天/好友
    ├── shop.proto       # 商店/拍卖行
    ├── quest.proto      # 任务系统
    ├── guild.proto      # 公会系统
    └── service.proto    # 服务定义
```

## 文件说明

### common.proto
公共类型定义：
- `MessageMeta` - 消息元信息(module, cmd)
- `ErrorCode` - 错误码枚举
- `PageRequest/PageResponse` - 分页请求/响应

### auth.proto
登录认证相关：
- `LoginRequest/LoginResponse` - 登录请求/响应
- `CreateCharacterRequest/Response` - 创建角色
- `CharacterListRequest/Response` - 角色列表
- `SelectCharacterRequest/Response` - 选择角色

### role.proto
角色系统：
- `RoleBaseInfo` - 角色基础信息
- `RoleBattleInfo` - 战斗属性
- `RolePosition` - 位置信息
- `SkillInfo` - 技能信息
- 各种属性更新接口

### item.proto
物品系统：
- `ItemBase` - 物品基础信息
- `EquipmentInfo` - 装备信息
- `BagItem` - 背包物品
- `Currency` - 货币信息
- 背包操作接口

### dungeon.proto
副本系统：
- `DungeonInfo` - 副本信息
- `DungeonMonster` - 副本怪物
- `DungeonResult` - 副本结算
- 副本进出接口

### chat.proto
聊天系统：
- `ChatMessage` - 聊天消息
- `ChatChannel` - 聊天频道枚举
- `FriendInfo` - 好友信息
- 聊天和好友管理接口

### shop.proto
商店系统：
- `ShopItem` - 商店商品
- `AuctionItem` - 拍卖行物品
- `PrivateStoreItem` - 个人商店商品
- 各种购买/出售接口

### quest.proto
任务系统：
- `QuestInfo` - 任务信息
- `QuestObjective` - 任务目标
- `QuestReward` - 任务奖励
- 任务接受/完成接口

### guild.proto
公会系统：
- `GuildInfo` - 公会信息
- `GuildMember` - 公会成员
- `GuildSkill` - 公会技能
- 公会管理接口

### service.proto
服务定义：
- `GameService` - 游戏逻辑服务(TCP)
- `AuthService` - 认证服务(HTTP)

## 消息路由

原Java版本使用 `@MessageMeta(module, cmd)` 注解进行路由。
Go版本使用 proto 消息类型直接映射。

示例映射：
```java
@MessageMeta(module = 10000, cmd = 0)
public class REQ_LOGIN extends Message { ... }
```

对应：
```protobuf
message LoginRequest {
  // fields...
}
```

## 生成代码

```bash
cd proto
buf generate
```

生成文件位置：
```
gen/dnf/v1/
├── common.pb.go
├── auth.pb.go
├── role.pb.go
├── item.pb.go
├── dungeon.pb.go
├── chat.pb.go
├── shop.pb.go
├── quest.pb.go
├── guild.pb.go
├── service.pb.go
├── service_grpc.pb.go
└── service_connect.go
```

## 使用示例

### 发送消息

```go
import v1 "dnf-go-server/proto/gen/dnf/v1"

// 创建登录请求
req := &v1.LoginRequest{
    Openid:  "player123",
    Type:    1,
    Token:   "xxx",
}

// 序列化
data, err := proto.Marshal(req)
if err != nil {
    return err
}
```

### 接收消息

```go
// 反序列化
var resp v1.LoginResponse
if err := proto.Unmarshal(data, &resp); err != nil {
    return err
}

// 使用
fmt.Println(resp.AuthKey)
```

### gRPC调用

```go
conn, err := grpc.Dial("localhost:9000", grpc.WithInsecure())
if err != nil {
    log.Fatal(err)
}
defer conn.Close()

client := v1.NewAuthServiceClient(conn)
resp, err := client.Login(context.Background(), &v1.LoginRequest{...})
```

## Java → Proto 映射

| Java | Proto |
|------|-------|
| REQ_LOGIN | LoginRequest |
| RES_LOGIN | LoginResponse |
| PT_CHANNEL_INFO | ChannelInfo |
| PT_ITEM | BagItem |
| @MessageMeta | message type |

## 版本管理

使用 proto3 语法，所有字段默认 optional。

### 字段规则
- 新字段使用新的 field number
- 不要重用已删除的 field number
- 保留字段使用 `reserved` 关键字

示例：
```protobuf
message Example {
  reserved 2, 15, 9 to 11;
  reserved "foo", "bar";
  
  string name = 1;
  int32 age = 3; // 跳过 2
}
```

---

**创建日期**: 2026-02-06  
**版本**: v1.0  
**状态**: 完成
