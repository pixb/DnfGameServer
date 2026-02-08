# Python 测试框架

## 目录结构

```
tests/
├── conftest.py              # pytest 配置和共享 fixtures
├── requirements.txt         # Python 依赖包
├── test_database.py        # 数据库测试（已有）
├── test_api.py             # API 接口测试
├── test_game_logic.py      # 游戏逻辑测试
└── utils/                  # 测试工具包
    ├── __init__.py
    ├── db_helper.py        # 数据库操作工具
    ├── api_helper.py       # API 请求工具
    └── test_data.py        # 测试数据生成器
```

## 安装依赖

```bash
cd tests
pip install -r requirements.txt
```

## 运行测试

```bash
# 运行所有测试
pytest

# 运行特定标记的测试
pytest -m api          # API 测试
pytest -m game         # 游戏逻辑测试
pytest -m db           # 数据库测试

# 运行特定文件
pytest test_api.py
pytest test_game_logic.py

# 运行特定类
pytest test_api.py::TestServerController

# 运行特定方法
pytest test_api.py::TestServerController::test_get_id -v

# 并行运行测试（需要 pytest-xdist）
pytest -n auto

# 生成覆盖率报告
pytest --cov=. --cov-report=html
```

## 测试工具使用

### 数据库工具 (DatabaseHelper)

```python
from utils import DatabaseHelper

# 使用上下文管理器
with DatabaseHelper() as db:
    # 插入账户
    account_data = {"id": "test", "userID": "user", "passwd": "pass"}
    db.insert_account(account_data)
    
    # 查询数据
    account = db.get_account_by_id("test")
    
    # 自定义查询
    result = db.fetch_one("SELECT * FROM t_role WHERE level > %s", (50,))
```

### API 工具 (ApiHelper)

```python
from utils import ApiHelper

with ApiHelper() as api:
    # GET 请求
    response = api.get("/scl/my/id")
    data = api.assert_success(response)
    
    # POST 请求
    response = api.post("/game/login", json_data={"username": "test"})
```

### 测试数据生成器 (TestDataGenerator)

```python
from utils import TestDataGenerator

# 生成账户数据
account = TestDataGenerator.generate_account_data()

# 生成角色数据（关联到账户）
role = TestDataGenerator.generate_role_data(openid=account['id'])

# 生成自定义数据
account = TestDataGenerator.generate_account_data(
    userID="custom_user",
    score=1000
)
```

## 编写新测试

### 1. API 测试示例

```python
import pytest
from utils import ApiHelper

@pytest.mark.api
class TestMyFeature:
    def test_my_endpoint(self):
        with ApiHelper() as api:
            response = api.get("/my/endpoint")
            data = api.assert_success(response)
            assert data['key'] == 'value'
```

### 2. 游戏逻辑测试示例

```python
import pytest
from utils import DatabaseHelper, TestDataGenerator

@pytest.mark.game
class TestMyGameLogic:
    def test_my_logic(self):
        with DatabaseHelper() as db:
            # 准备数据
            account = TestDataGenerator.generate_account_data()
            db.insert_account(account)
            
            # 执行业务逻辑
            # ...
            
            # 验证结果
            result = db.get_account_by_id(account['id'])
            assert result['score'] == 100
            
            # 清理
            db.delete_account(account['id'])
```

## 配置说明

在 `conftest.py` 中修改 `TestConfig` 类以适配你的环境：

```python
class TestConfig:
    DB_HOST = "127.0.0.1"      # 数据库地址
    DB_PORT = 3306             # 数据库端口
    DB_USER = "root"           # 数据库用户
    DB_PASSWORD = "123456"     # 数据库密码
    API_BASE_URL = "http://127.0.0.1:8080"  # API 基础地址
```

## 最佳实践

1. **使用 fixtures**: 复用 `db`、`api` 等 fixtures
2. **清理测试数据**: 使用 `try...finally` 确保数据清理
3. **标记测试**: 使用 `@pytest.mark.api`、`@pytest.mark.game` 等标记
4. **独立性**: 每个测试应独立运行，不依赖其他测试
5. **可读性**: 测试方法名应清晰描述测试内容

## 扩展测试

可以添加更多测试文件：
- `test_integration.py` - 集成测试
- `test_performance.py` - 性能测试
- `test_stress.py` - 压力测试
- `test_socket.py` - Socket/MINA 协议测试
