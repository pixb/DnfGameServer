"""
测试数据生成器

提供测试数据的生成方法
"""

import random
import string
from datetime import datetime
from typing import Dict, Any


class TestDataGenerator:
    """测试数据生成器"""
    
    @staticmethod
    def generate_random_string(length: int = 10) -> str:
        """生成随机字符串"""
        return ''.join(random.choices(string.ascii_letters + string.digits, k=length))
    
    @staticmethod
    def generate_random_id(prefix: str = "test") -> str:
        """生成随机 ID"""
        timestamp = datetime.now().strftime("%Y%m%d%H%M%S")
        random_suffix = ''.join(random.choices(string.digits, k=4))
        return f"{prefix}_{timestamp}_{random_suffix}"
    
    @staticmethod
    def generate_random_uid() -> int:
        """生成随机 UID"""
        return random.randint(100000000, 999999999)
    
    @staticmethod
    def generate_account_data(**kwargs) -> Dict[str, Any]:
        """生成账户测试数据"""
        return {
            "id": kwargs.get("id", TestDataGenerator.generate_random_id("acc")),
            "userID": kwargs.get("userID", f"user_{TestDataGenerator.generate_random_string(6)}"),
            "passwd": kwargs.get("passwd", "password123"),
            "accountkey": kwargs.get("accountkey", TestDataGenerator.generate_random_string(32)),
            "accumulatecera": kwargs.get("accumulatecera", 0),
            "roleMaxCount": kwargs.get("roleMaxCount", 3),
            "isStop": kwargs.get("isStop", 0),
            "privilege": kwargs.get("privilege", 0),
            "score": kwargs.get("score", 0),
            "channelNo": kwargs.get("channelNo", "test_channel"),
            "createTime": kwargs.get("createTime", datetime.now()),
            "lastLoginTime": kwargs.get("lastLoginTime", 0),
            **{k: v for k, v in kwargs.items() if k not in [
                "id", "userID", "passwd", "accountkey", "accumulatecera",
                "roleMaxCount", "isStop", "privilege", "score", "channelNo", "createTime", "lastLoginTime"
            ]}
        }
    
    @staticmethod
    def generate_role_data(**kwargs) -> Dict[str, Any]:
        """生成角色测试数据"""
        return {
            "roleId": kwargs.get("roleId", random.randint(1, 100)),
            "uid": kwargs.get("uid", TestDataGenerator.generate_random_uid()),
            "name": kwargs.get("name", f"Role_{TestDataGenerator.generate_random_string(5)}"),
            "job": kwargs.get("job", random.randint(0, 14)),
            "level": kwargs.get("level", 1),
            "growtype": kwargs.get("growtype", 0),
            "secgrowtype": kwargs.get("secgrowtype", 0),
            "fatigue": kwargs.get("fatigue", 100),
            "equipscore": kwargs.get("equipscore", 0),
            "characterframe": kwargs.get("characterframe", 0),
            "money": kwargs.get("money", 0),
            "rescoin": kwargs.get("rescoin", 0),
            "contributioncoin": kwargs.get("contributioncoin", 0),
            "magiccrystal": kwargs.get("magiccrystal", 0),
            "highmagiccrystal": kwargs.get("highmagiccrystal", 0),
            "cerascore": kwargs.get("cerascore", 0),
            "pkcoin": kwargs.get("pkcoin", 0),
            "friendpoint": kwargs.get("friendpoint", 0),
            "smallcoin": kwargs.get("smallcoin", 0),
            "deletionstatus": kwargs.get("deletionstatus", 0),
            "openid": kwargs.get("openid", TestDataGenerator.generate_random_id("openid")),
            "exp": kwargs.get("exp", 0),
            "sp": kwargs.get("sp", 0),
            "tp": kwargs.get("tp", 0),
            "addsp": kwargs.get("addsp", 0),
            "addtp": kwargs.get("addtp", 0),
            "day": kwargs.get("day", datetime.now().day),
            **{k: v for k, v in kwargs.items() if k not in [
                "roleId", "uid", "name", "job", "level", "growtype", "secgrowtype",
                "fatigue", "equipscore", "characterframe", "money", "rescoin",
                "contributioncoin", "magiccrystal", "highmagiccrystal", "cerascore",
                "pkcoin", "friendpoint", "smallcoin", "deletionstatus", "openid",
                "exp", "sp", "tp", "addsp", "addtp", "day"
            ]}
        }
    
    @staticmethod
    def generate_charge_data(**kwargs) -> Dict[str, Any]:
        """生成充值测试数据"""
        return {
            "pay_id": kwargs.get("pay_id", TestDataGenerator.generate_random_id("pay")),
            "account_id": kwargs.get("account_id", TestDataGenerator.generate_random_id("acc")),
            "role_id": kwargs.get("role_id", TestDataGenerator.generate_random_uid()),
            "amount": kwargs.get("amount", random.choice([10, 30, 50, 100, 200])),
            "cera": kwargs.get("cera", random.choice([1000, 3000, 5000, 10000])),
            "pay_channel": kwargs.get("pay_channel", "test_channel"),
            "pay_status": kwargs.get("pay_status", 1),
            "create_time": kwargs.get("create_time", datetime.now()),
            **{k: v for k, v in kwargs.items() if k not in [
                "pay_id", "account_id", "role_id", "amount", "cera",
                "pay_channel", "pay_status", "create_time"
            ]}
        }
    
    @staticmethod
    def generate_item_data(**kwargs) -> Dict[str, Any]:
        """生成物品测试数据"""
        return {
            "item_id": kwargs.get("item_id", random.randint(1000, 9999)),
            "item_name": kwargs.get("item_name", f"Item_{TestDataGenerator.generate_random_string(5)}"),
            "item_type": kwargs.get("item_type", random.randint(1, 10)),
            "rarity": kwargs.get("rarity", random.randint(1, 5)),
            "level": kwargs.get("level", 1),
            "stack_size": kwargs.get("stack_size", 1),
            **{k: v for k, v in kwargs.items() if k not in [
                "item_id", "item_name", "item_type", "rarity", "level", "stack_size"
            ]}
        }
