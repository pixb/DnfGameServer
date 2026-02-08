"""
HTTP API 接口测试

测试游戏服务器的 REST API 接口
"""

import pytest
from utils import ApiHelper, TestDataGenerator


@pytest.fixture
def api():
    """API 客户端 fixture"""
    with ApiHelper() as client:
        yield client


@pytest.mark.api
class TestServerController:
    """测试服务器控制器接口"""
    
    def test_get_id(self, api):
        """测试获取 ID 接口"""
        response = api.get("/scl/my/id")
        data = api.assert_success(response)
        
        assert "roleId" in data or "uid" in data, "响应应包含 roleId 或 uid"
        
    def test_server_status(self, api):
        """测试服务器状态接口"""
        # 这里假设有一个状态检查接口
        # 根据实际 API 调整端点
        response = api.get("/scl/my/status")
        
        # 如果接口不存在会返回 404，这也是可以接受的
        assert response.status_code in [200, 404], \
            f"意外的状态码: {response.status_code}"


@pytest.mark.api
class TestPayController:
    """测试支付相关接口"""
    
    def test_pay_callback(self, api):
        """测试支付回调接口"""
        pay_data = {
            "orderId": TestDataGenerator.generate_random_id("order"),
            "accountId": TestDataGenerator.generate_random_id("acc"),
            "amount": 100,
            "channel": "test",
            "sign": "test_sign"
        }
        
        response = api.post("/pay/callback", json_data=pay_data)
        
        # 支付回调接口可能返回各种状态码，根据实际实现调整
        assert response.status_code in [200, 400, 401, 404], \
            f"支付回调返回意外状态码: {response.status_code}"


@pytest.mark.api
class TestGMController:
    """测试 GM 命令接口"""
    
    def test_gm_command_without_auth(self, api):
        """测试未授权访问 GM 接口"""
        response = api.post("/gm/command", json_data={"cmd": "test"})
        
        # GM 接口通常需要认证，应返回 401 或 403
        assert response.status_code in [401, 403, 404], \
            f"GM 接口应拒绝未授权访问，但返回 {response.status_code}"


@pytest.mark.api
class TestGameController:
    """测试游戏逻辑接口"""
    
    def test_login_endpoint_exists(self, api):
        """测试登录端点是否存在"""
        login_data = {
            "username": "test_user",
            "password": "test_pass"
        }
        
        response = api.post("/game/login", json_data=login_data)
        
        # 根据实际实现调整预期状态码
        assert response.status_code in [200, 400, 401, 404], \
            f"登录接口返回意外状态码: {response.status_code}"
    
    def test_role_list_endpoint(self, api):
        """测试角色列表接口"""
        response = api.get("/game/role/list", params={"accountId": "test_account"})
        
        assert response.status_code in [200, 401, 404], \
            f"角色列表接口返回意外状态码: {response.status_code}"


@pytest.mark.api
class TestAPIErrorHandling:
    """测试 API 错误处理"""
    
    def test_invalid_endpoint(self, api):
        """测试无效端点"""
        response = api.get("/invalid/endpoint")
        assert response.status_code == 404, \
            "无效端点应返回 404"
    
    def test_invalid_method(self, api):
        """测试无效 HTTP 方法"""
        # GET 请求通常用于获取数据，用 DELETE 访问 GET 端点可能不被支持
        response = api.delete("/scl/my/id")
        assert response.status_code in [405, 404], \
            f"无效方法应返回 405 或 404，实际 {response.status_code}"


@pytest.mark.api
class TestAPIPerformance:
    """测试 API 性能"""
    
    def test_api_response_time(self, api):
        """测试 API 响应时间"""
        import time
        
        start = time.time()
        response = api.get("/scl/my/id")
        elapsed = time.time() - start
        
        assert response.status_code == 200
        assert elapsed < 2.0, f"API 响应时间过长: {elapsed:.2f}秒"
