# DNF Go Server 测试

本目录包含 DNF Go Server 的集成测试。

## 测试结构

```
tests/
├── client.go          # HTTP 测试客户端
├── auth_test.go       # 认证和角色测试
├── bag_test.go        # 背包测试
├── dungeon_test.go    # 副本测试
├── social_test.go    # 社交测试 (好友/邮件)
├── shop_test.go      # 商店测试
├── quest_test.go     # 任务测试
└── guild_test.go     # 公会测试
```

## 测试结果

```
=== RUN   TestAuthSuite       ✅ 7/7 通过
=== RUN   TestBagSuite        ✅ 2/2 通过
=== RUN   TestDungeonSuite    ✅ 3/3 通过
=== RUN   TestGuildSuite      ✅ 4/4 通过
=== RUN   TestQuestSuite      ✅ 3/3 通过
=== RUN   TestShopSuite       ✅ 4/4 通过
=== RUN   TestSocialSuite     ✅ 4/4 通过

总计: 27/27 测试通过 ✅
```

## 运行测试

### 前置条件

1. 确保服务器正在运行：
```bash
cd dnf-go-server
make dev
```

2. 运行测试：
```bash
go test ./tests/... -v
```

### 运行特定测试套件

```bash
# 认证测试
go test ./tests -v -run TestAuthSuite

# 背包测试
go test ./tests -v -run TestBagSuite

# 商店测试
go test ./tests -v -run TestShopSuite

# 公会测试
go test ./tests -v -run TestGuildSuite
```

## API 端点

### 认证 (Auth)
- `POST /api/v1/auth/login` - 用户登录
- `GET /api/v1/character/list` - 获取角色列表
- `POST /api/v1/character/create` - 创建角色
- `POST /api/v1/character/select` - 选择角色

### 背包 (Bag)
- `GET /api/v1/bag` - 获取背包
- `GET /api/v1/bag/items` - 获取背包物品

### 商店 (Shop)
- `GET /api/v1/shop/list` - 获取商店列表
- `POST /api/v1/shop/buy` - 购买物品
- `POST /api/v1/shop/sell` - 出售物品
- `GET /api/v1/auction/search` - 搜索拍卖行

### 社交 (Social)
- `GET /api/v1/friend/list` - 获取好友列表
- `POST /api/v1/friend/add` - 添加好友
- `POST /api/v1/friend/remove` - 删除好友
- `GET /api/v1/mail/list` - 获取邮件列表

### 任务 (Quest)
- `GET /api/v1/quest/list` - 获取任务列表
- `POST /api/v1/quest/accept` - 接受任务
- `POST /api/v1/quest/complete` - 完成任务

### 公会 (Guild)
- `GET /api/v1/guild/info` - 获取公会信息
- `POST /api/v1/guild/create` - 创建公会
- `POST /api/v1/guild/join` - 加入公会
- `POST /api/v1/guild/leave` - 离开公会

### 副本 (Dungeon)
- `POST /api/v1/dungeon/enter` - 进入副本
- `POST /api/v1/dungeon/exit` - 退出副本
- `POST /api/v1/dungeon/revive` - 复活

## 测试说明

### 测试客户端

测试使用 `TestClient` 封装 HTTP 请求：

```go
client := NewTestClient("http://localhost:8081")
resp, err := client.Post("/api/v1/auth/login", map[string]interface{}{
    "openid": "test_user",
})
```

### 认证流程

大多数测试需要登录和选择角色：

1. 调用 `/api/v1/auth/login` 获取 token
2. 设置 token 到 client
3. 调用 `/api/v1/character/list` 获取角色
4. 调用 `/api/v1/character/select` 选择角色
5. 执行需要认证的 API 调用

### 错误码对照

| 错误码 | 描述 |
|--------|------|
| 0 | 成功 |
| 1 | 参数无效 |
| 3 | 金币不足/角色名已存在 |
| 5 | 无效的角色名 |
| 6 | 未找到 |
| 7 | 已在公会中 |
| 8 | 公会已满 |
| 9 | 不在公会中 |
| 10 | 会长不能离开 |

## 注意事项

1. **测试顺序**: 测试之间相互独立
2. **数据清理**: 每个测试使用唯一的用户ID
3. **并发安全**: 测试使用时间戳生成唯一ID
