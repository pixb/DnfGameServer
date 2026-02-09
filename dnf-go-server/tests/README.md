# DNF Go Server 测试

本目录包含 DNF Go Server 的集成测试。

## 测试结构

```
tests/
├── client.go       # HTTP 测试客户端
├── auth_test.go    # 认证和角色测试 (Phase 1)
├── bag_test.go     # 背包和物品测试 (Phase 2)
├── dungeon_test.go # 副本系统测试 (Phase 3)
├── social_test.go  # 社交系统测试 (Phase 4)
├── shop_test.go    # 商店和经济测试 (Phase 5)
├── quest_test.go   # 任务系统测试 (Phase 6)
└── guild_test.go   # 公会系统测试 (Phase 7)
```

## 运行测试

### 前提条件

1. 确保服务器正在运行：
```bash
make dev
```

2. 安装测试依赖：
```bash
go get github.com/stretchr/testify
```

### 运行所有测试

```bash
go test ./tests/... -v
```

### 运行特定测试套件

```bash
# 认证测试
go test ./tests -v -run TestAuthSuite

# 背包测试
go test ./tests -v -run TestBagSuite

# 副本测试
go test ./tests -v -run TestDungeonSuite

# 社交测试
go test ./tests -v -run TestSocialSuite

# 商店测试
go test ./tests -v -run TestShopSuite

# 任务测试
go test ./tests -v -run TestQuestSuite

# 公会测试
go test ./tests -v -run TestGuildSuite
```

### 运行单个测试

```bash
# 测试登录功能
go test ./tests -v -run TestAuthSuite/TestLogin

# 测试创建公会
go test ./tests -v -run TestGuildSuite/TestCreateGuild
```

## 测试说明

### 测试客户端

测试使用 `TestClient` 封装 HTTP 请求：

```go
client := NewTestClient("http://localhost:8081")
resp, err := client.Post("/api/v1/login", map[string]interface{}{
    "openid": "test_user",
})
```

### 认证流程

大多数测试需要登录和选择角色：

1. 调用 `/api/v1/login` 获取 token
2. 设置 token 到 client
3. 调用 `/api/v1/character/select` 选择角色
4. 执行需要认证的 API 调用

### 错误码对照

测试中使用的错误码：

| 错误码 | 描述 |
|--------|------|
| 0 | 成功 |
| 1 | 参数无效 |
| 2 | 未找到 |
| 3 | 金币不足 |
| 7 | 已在公会中 |
| 8 | 公会已满 |
| 9 | 不在公会中 |
| 10 | 会长不能离开 |
| 11 | 权限不足 |
| 12 | 公会资金不足 |

## 添加新测试

创建新的测试文件，例如 `new_feature_test.go`：

```go
package tests

import (
    "testing"
    "github.com/stretchr/testify/suite"
)

type NewFeatureTestSuite struct {
    BaseTestSuite
}

func (s *NewFeatureTestSuite) TestNewFeature() {
    // 登录
    resp, err := s.Client.Post("/api/v1/login", map[string]interface{}{
        "openid": "test_user",
    })
    s.NoError(err)
    s.AssertSuccess(resp)
    
    // 设置 token
    if token, ok := resp["auth_key"].(string); ok {
        s.Client.SetToken(token)
    }
    
    // 测试新功能
    resp, err = s.Client.Post("/api/v1/new/feature", nil)
    s.NoError(err)
    s.AssertSuccess(resp)
}

func TestNewFeatureSuite(t *testing.T) {
    suite.Run(t, new(NewFeatureTestSuite))
}
```

## 注意事项

1. **测试顺序**: 测试之间相互独立，不要依赖执行顺序
2. **数据清理**: 每个测试应该清理自己创建的数据
3. **并发安全**: 避免多个测试同时使用相同的用户ID
4. **超时处理**: 测试客户端默认 10 秒超时

## 持续集成

可以在 CI/CD 中运行测试：

```yaml
# .github/workflows/test.yml
name: Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-go@v2
      - name: Start server
        run: make dev &
      - name: Wait for server
        run: sleep 5
      - name: Run tests
        run: go test ./tests/... -v
```
