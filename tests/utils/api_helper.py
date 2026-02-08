"""
API 测试工具类

封装 HTTP API 调用，简化接口测试
"""

import requests
from typing import Dict, Any, Optional
import json


class ApiHelper:
    """API 测试辅助类"""
    
    def __init__(self, base_url: str = "http://127.0.0.1:20001", timeout: int = 10):
        self.base_url = base_url.rstrip('/')
        self.timeout = timeout
        self.session = requests.Session()
        self.session.headers.update({
            "Content-Type": "application/json",
            "Accept": "application/json"
        })
    
    def get(self, endpoint: str, params: Dict = None, headers: Dict = None) -> requests.Response:
        """发送 GET 请求"""
        url = f"{self.base_url}{endpoint}"
        request_headers = self.session.headers.copy()
        if headers:
            request_headers.update(headers)
        
        return self.session.get(
            url, 
            params=params, 
            headers=request_headers,
            timeout=self.timeout
        )
    
    def post(self, endpoint: str, data: Dict = None, json_data: Dict = None, 
             headers: Dict = None) -> requests.Response:
        """发送 POST 请求"""
        url = f"{self.base_url}{endpoint}"
        request_headers = self.session.headers.copy()
        if headers:
            request_headers.update(headers)
        
        return self.session.post(
            url,
            data=data,
            json=json_data,
            headers=request_headers,
            timeout=self.timeout
        )
    
    def put(self, endpoint: str, data: Dict = None, json_data: Dict = None,
            headers: Dict = None) -> requests.Response:
        """发送 PUT 请求"""
        url = f"{self.base_url}{endpoint}"
        request_headers = self.session.headers.copy()
        if headers:
            request_headers.update(headers)
        
        return self.session.put(
            url,
            data=data,
            json=json_data,
            headers=request_headers,
            timeout=self.timeout
        )
    
    def delete(self, endpoint: str, headers: Dict = None) -> requests.Response:
        """发送 DELETE 请求"""
        url = f"{self.base_url}{endpoint}"
        request_headers = self.session.headers.copy()
        if headers:
            request_headers.update(headers)
        
        return self.session.delete(
            url,
            headers=request_headers,
            timeout=self.timeout
        )
    
    def assert_success(self, response: requests.Response, 
                       expected_status: int = 200) -> Dict[str, Any]:
        """断言请求成功并返回 JSON 数据"""
        assert response.status_code == expected_status, \
            f"期望状态码 {expected_status}，实际 {response.status_code}"
        
        try:
            return response.json()
        except json.JSONDecodeError:
            return {"text": response.text}
    
    def assert_error(self, response: requests.Response, 
                     expected_status: int = 400) -> bool:
        """断言请求返回错误"""
        assert response.status_code == expected_status, \
            f"期望错误状态码 {expected_status}，实际 {response.status_code}"
        return True
    
    def close(self):
        """关闭会话"""
        self.session.close()
    
    def __enter__(self):
        """上下文管理器入口"""
        return self
    
    def __exit__(self, exc_type, exc_val, exc_tb):
        """上下文管理器出口"""
        self.close()
