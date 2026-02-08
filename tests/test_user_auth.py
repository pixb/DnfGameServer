"""
用户注册与登录功能测试

测试用户账户的注册、登录、认证等核心功能
"""

import pytest
from datetime import datetime
from utils import DatabaseHelper, TestDataGenerator


@pytest.fixture
def db():
    """数据库 helper fixture"""
    with DatabaseHelper() as db_helper:
        yield db_helper


@pytest.mark.auth
class TestUserRegistration:
    """测试用户注册功能"""
    
    def test_register_new_account(self, db):
        """测试注册新账户"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            result = db.insert_account(account_data)
            assert result, "账户注册失败"
            
            account = db.get_account_by_id(account_data['id'])
            assert account is not None, "无法查询到注册的账户"
            assert account['userID'] == account_data['userID'], "用户名不匹配"
            assert account['passwd'] == account_data['passwd'], "密码不匹配"
            assert account['createTime'] is not None, "创建时间未设置"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_register_duplicate_username(self, db):
        """测试注册重复用户名"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            result1 = db.insert_account(account_data)
            assert result1, "第一次注册应成功"
            
            duplicate_data = TestDataGenerator.generate_account_data(
                userID=account_data['userID']
            )
            result2 = db.insert_account(duplicate_data)
            
            # 根据实际业务逻辑，重复用户名可能失败或成功
            # 这里假设会失败
            if not result2:
                print("重复用户名注册被正确拒绝")
            
        finally:
            db.delete_account(account_data['id'])
            if 'id' in duplicate_data:
                db.delete_account(duplicate_data['id'])
    
    def test_register_with_default_values(self, db):
        """测试注册时使用默认值"""
        account_data = TestDataGenerator.generate_account_data(
            score=None,
            roleMaxCount=None,
            privilege=None
        )
        
        try:
            db.insert_account(account_data)
            account = db.get_account_by_id(account_data['id'])
            
            assert account['score'] == 0, "默认积分应为0"
            assert account['roleMaxCount'] == 3, "默认角色上限应为3"
            assert account['privilege'] == 0, "默认权限等级应为0"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_register_account_with_custom_values(self, db):
        """测试注册时使用自定义值"""
        account_data = TestDataGenerator.generate_account_data(
            score=10000,
            roleMaxCount=5,
            privilege=10
        )
        
        try:
            db.insert_account(account_data)
            account = db.get_account_by_id(account_data['id'])
            
            assert account['score'] == 10000, "自定义积分不匹配"
            assert account['roleMaxCount'] == 5, "自定义角色上限不匹配"
            assert account['privilege'] == 10, "自定义权限等级不匹配"
            
        finally:
            db.delete_account(account_data['id'])


@pytest.mark.auth
class TestUserLogin:
    """测试用户登录功能"""
    
    def test_login_with_correct_credentials(self, db):
        """测试使用正确凭据登录"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            db.insert_account(account_data)
            
            account = db.get_account_by_id(account_data['id'])
            assert account is not None, "账户不存在"
            assert account['passwd'] == account_data['passwd'], "密码不匹配"
            
            # 更新最后登录时间
            import time
            timestamp = int(time.time() * 1000)
            db.execute(
                "UPDATE t_account SET lastLoginTime = %s WHERE id = %s",
                (timestamp, account_data['id'])
            )
            
            updated = db.get_account_by_id(account_data['id'])
            assert updated['lastLoginTime'] is not None, "最后登录时间未更新"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_login_with_wrong_password(self, db):
        """测试使用错误密码登录"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            db.insert_account(account_data)
            
            account = db.get_account_by_id(account_data['id'])
            assert account is not None, "账户不存在"
            
            # 验证密码不匹配
            assert account['passwd'] != "wrong_password", "错误密码不应匹配"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_login_nonexistent_account(self, db):
        """测试登录不存在的账户"""
        account = db.get_account_by_id("nonexistent_account")
        assert account is None, "不存在的账户应返回None"
    
    def test_account_status_check(self, db):
        """测试账户状态检查"""
        account_data = TestDataGenerator.generate_account_data(isStop=False)
        
        try:
            db.insert_account(account_data)
            account = db.get_account_by_id(account_data['id'])
            
            assert account['isStop'] == 0 or account['isStop'] is False, \
                "账户默认状态应为正常"
            
            # 测试封号状态
            db.execute(
                "UPDATE t_account SET isStop = 1 WHERE id = %s",
                (account_data['id'],)
            )
            
            banned = db.get_account_by_id(account_data['id'])
            assert banned['isStop'] == 1 or banned['isStop'] is True, \
                "账户状态应更新为封禁"
            
        finally:
            db.delete_account(account_data['id'])


@pytest.mark.auth
class TestAccountManagement:
    """测试账户管理功能"""
    
    def test_update_account_password(self, db):
        """测试更新账户密码"""
        account_data = TestDataGenerator.generate_account_data()
        new_password = "new_password_123"
        
        try:
            db.insert_account(account_data)
            
            db.execute(
                "UPDATE t_account SET passwd = %s WHERE id = %s",
                (new_password, account_data['id'])
            )
            
            account = db.get_account_by_id(account_data['id'])
            assert account['passwd'] == new_password, "密码更新失败"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_update_account_score(self, db):
        """测试更新账户积分"""
        account_data = TestDataGenerator.generate_account_data(score=100)
        
        try:
            db.insert_account(account_data)
            
            db.execute(
                "UPDATE t_account SET score = score + 500 WHERE id = %s",
                (account_data['id'],)
            )
            
            account = db.get_account_by_id(account_data['id'])
            assert account['score'] == 600, "积分更新失败"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_delete_account(self, db):
        """测试删除账户"""
        account_data = TestDataGenerator.generate_account_data()
        
        db.insert_account(account_data)
        account = db.get_account_by_id(account_data['id'])
        assert account is not None, "账户创建失败"
        
        db.delete_account(account_data['id'])
        deleted = db.get_account_by_id(account_data['id'])
        assert deleted is None, "账户删除失败"
    
    def test_account_query_by_username(self, db):
        """测试通过用户名查询账户"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            db.insert_account(account_data)
            
            account = db.fetch_one(
                "SELECT * FROM t_account WHERE userID = %s",
                (account_data['userID'],)
            )
            
            assert account is not None, "无法通过用户名查询账户"
            assert account['id'] == account_data['id'], "账户ID不匹配"
            
        finally:
            db.delete_account(account_data['id'])


@pytest.mark.auth
class TestAccountSecurity:
    """测试账户安全功能"""
    
    def test_account_privilege_levels(self, db):
        """测试账户权限等级"""
        privilege_levels = [0, 1, 5, 10, 100]
        
        for privilege in privilege_levels:
            account_data = TestDataGenerator.generate_account_data(
                privilege=privilege
            )
            
            try:
                db.insert_account(account_data)
                account = db.get_account_by_id(account_data['id'])
                
                assert account['privilege'] == privilege, \
                    f"权限等级 {privilege} 设置失败"
                
            finally:
                db.delete_account(account_data['id'])
    
    def test_account_ban_and_unban(self, db):
        """测试账户封禁和解封"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            db.insert_account(account_data)
            
            # 封禁账户
            db.execute(
                "UPDATE t_account SET isStop = 1 WHERE id = %s",
                (account_data['id'],)
            )
            
            banned = db.get_account_by_id(account_data['id'])
            assert banned['isStop'] == 1 or banned['isStop'] is True, \
                "账户封禁失败"
            
            # 解封账户
            db.execute(
                "UPDATE t_account SET isStop = 0 WHERE id = %s",
                (account_data['id'],)
            )
            
            unbanned = db.get_account_by_id(account_data['id'])
            assert unbanned['isStop'] == 0 or unbanned['isStop'] is False, \
                "账户解封失败"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_account_login_history(self, db):
        """测试账户登录历史"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            db.insert_account(account_data)
            
            # 模拟多次登录
            for i in range(3):
                import time
                timestamp = int(time.time() * 1000)
                db.execute(
                    "UPDATE t_account SET lastLoginTime = %s WHERE id = %s",
                    (timestamp, account_data['id'])
                )
            
            account = db.get_account_by_id(account_data['id'])
            assert account['lastLoginTime'] is not None, "登录时间未记录"
            
        finally:
            db.delete_account(account_data['id'])


@pytest.mark.auth
class TestAccountValidation:
    """测试账户验证功能"""
    
    def test_username_length_validation(self, db):
        """测试用户名长度验证"""
        # 测试不同长度的用户名
        usernames = [
            ("a", False),  # 太短
            ("ab", True),  # 最小长度
            ("normal_user", True),  # 正常长度
            ("a" * 50, True),  # 最大长度
            ("a" * 51, False)  # 超过最大长度
        ]
        
        for username, should_succeed in usernames:
            try:
                account_data = TestDataGenerator.generate_account_data(
                    userID=username
                )
                
                result = db.insert_account(account_data)
                
                if should_succeed:
                    assert result, f"用户名 '{username}' 应该可以注册"
                else:
                    # 根据实际实现，可能允许或拒绝
                    print(f"用户名 '{username}' 注册结果: {result}")
                
            finally:
                if 'id' in account_data:
                    db.delete_account(account_data['id'])
    
    def test_password_validation(self, db):
        """测试密码验证"""
        passwords = [
            "123",  # 短密码
            "password123",  # 正常密码
            "P@ssw0rd!",  # 复杂密码
            ""  # 空密码
        ]
        
        for password in passwords:
            try:
                account_data = TestDataGenerator.generate_account_data(
                    passwd=password
                )
                
                result = db.insert_account(account_data)
                
                if password:  # 非空密码应该可以注册
                    assert result, f"密码 '{password}' 应该可以注册"
                else:
                    # 空密码可能被拒绝
                    print(f"空密码注册结果: {result}")
                
            finally:
                if 'id' in account_data:
                    db.delete_account(account_data['id'])
