"""
Pytest 配置文件

提供共享的 fixtures 和测试配置
"""

import pytest
import mysql.connector
import requests
from typing import Generator
import os
import sys

# 添加项目根目录到路径
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))


class TestConfig:
    """测试配置类"""
    # 数据库配置
    DB_HOST = "127.0.0.1"
    DB_PORT = 3306
    DB_USER = "root"
    DB_PASSWORD = "123456"
    DB_NAME_GAME = "game"
    DB_NAME_LOGIN = "login"
    DB_NAME_KUAFU = "game_kuafu"
    
    # HTTP API 配置
    API_BASE_URL = "http://127.0.0.1:20001"
    API_TIMEOUT = 10
    
    # 游戏服务器配置
    GAME_SERVER_HOST = "127.0.0.1"
    GAME_SERVER_PORT = 9000


@pytest.fixture(scope="session")
def db_config():
    """提供数据库配置"""
    return TestConfig


@pytest.fixture
def db_connection():
    """提供数据库连接 fixture"""
    connection = mysql.connector.connect(
        host=TestConfig.DB_HOST,
        port=TestConfig.DB_PORT,
        user=TestConfig.DB_USER,
        password=TestConfig.DB_PASSWORD,
        database=TestConfig.DB_NAME_GAME
    )
    yield connection
    connection.close()


@pytest.fixture
def db_cursor(db_connection):
    """提供数据库游标 fixture"""
    cursor = db_connection.cursor()
    yield cursor
    cursor.close()


@pytest.fixture
def api_client():
    """提供 HTTP API 客户端"""
    session = requests.Session()
    session.headers.update({
        "Content-Type": "application/json",
        "Accept": "application/json"
    })
    yield session
    session.close()


@pytest.fixture
def api_base_url():
    """提供 API 基础 URL"""
    return TestConfig.API_BASE_URL


@pytest.fixture(scope="function")
def clean_test_data(db_connection):
    """
    测试数据清理 fixture
    在测试结束后清理测试数据
    """
    test_data_ids = []
    
    def register_test_id(table: str, id_value):
        """注册需要清理的测试数据"""
        test_data_ids.append((table, id_value))
    
    yield register_test_id
    
    # 测试结束后清理数据
    cursor = db_connection.cursor()
    for table, id_value in test_data_ids:
        try:
            cursor.execute(f"DELETE FROM {table} WHERE id = %s", (id_value,))
        except Exception:
            pass
    db_connection.commit()
    cursor.close()


@pytest.fixture
def test_account_data():
    """提供测试账户数据模板"""
    return {
        "userID": "test_user_001",
        "passwd": "test_password",
        "score": 0,
        "roleMaxCount": 3
    }


@pytest.fixture
def test_role_data():
    """提供测试角色数据模板"""
    return {
        "name": "TestRole",
        "job": 1,
        "level": 1,
        "openid": "test_openid"
    }


# 自定义标记
def pytest_configure(config):
    """配置 pytest 标记"""
    config.addinivalue_line("markers", "slow: marks tests as slow")
    config.addinivalue_line("markers", "integration: marks tests as integration tests")
    config.addinivalue_line("markers", "api: marks tests as API tests")
    config.addinivalue_line("markers", "db: marks tests as database tests")
    config.addinivalue_line("markers", "game: marks tests as game logic tests")
