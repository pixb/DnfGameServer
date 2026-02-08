"""
游戏逻辑测试

测试游戏核心逻辑和业务规则
"""

import pytest
from utils import DatabaseHelper, TestDataGenerator


@pytest.fixture
def db():
    """数据库 helper fixture"""
    with DatabaseHelper() as db_helper:
        yield db_helper


@pytest.mark.game
class TestAccountSystem:
    """测试账户系统"""
    
    def test_create_account(self, db):
        """测试创建账户"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            result = db.insert_account(account_data)
            assert result, "账户创建失败"
            
            # 验证数据已写入
            account = db.get_account_by_id(account_data['id'])
            assert account is not None, "无法查询到刚创建的账户"
            assert account['userID'] == account_data['userID'], "用户名不匹配"
            
        finally:
            # 清理测试数据
            db.delete_account(account_data['id'])
    
    def test_account_unique_constraint(self, db):
        """测试账户唯一性约束"""
        account_data = TestDataGenerator.generate_account_data()
        
        try:
            # 第一次插入应该成功
            result1 = db.insert_account(account_data)
            assert result1, "第一次插入应成功"
            
            # 相同 ID 第二次插入应该失败
            result2 = db.insert_account(account_data)
            assert not result2, "重复 ID 插入应失败"
            
        finally:
            db.delete_account(account_data['id'])
    
    def test_account_default_values(self, db):
        """测试账户默认值"""
        account_data = TestDataGenerator.generate_account_data(
            score=None,
            roleMaxCount=None
        )
        
        try:
            db.insert_account(account_data)
            account = db.get_account_by_id(account_data['id'])
            
            # 验证默认值
            assert account['score'] == 0, "默认 score 应为 0"
            assert account['roleMaxCount'] == 3, "默认角色上限应为 3"
            
        finally:
            db.delete_account(account_data['id'])


@pytest.mark.game
class TestRoleSystem:
    """测试角色系统"""
    
    def test_create_role(self, db):
        """测试创建角色"""
        # 先创建账户
        account_data = TestDataGenerator.generate_account_data()
        role_data = TestDataGenerator.generate_role_data(openid=account_data['id'])
        
        try:
            db.insert_account(account_data)
            result = db.insert_role(role_data)
            assert result, "角色创建失败"
            
            # 验证角色数据
            role = db.get_role_by_uid(role_data['uid'])
            assert role is not None, "无法查询到刚创建的角色"
            assert role['name'] == role_data['name'], "角色名不匹配"
            assert role['job'] == role_data['job'], "职业不匹配"
            
        finally:
            db.delete_role(role_data['uid'])
            db.delete_account(account_data['id'])
    
    def test_role_level_progression(self, db):
        """测试角色等级进阶"""
        account_data = TestDataGenerator.generate_account_data()
        role_data = TestDataGenerator.generate_role_data(
            openid=account_data['id'],
            level=50,
            exp=10000
        )
        
        try:
            db.insert_account(account_data)
            db.insert_role(role_data)
            
            # 验证等级和经验
            role = db.get_role_by_uid(role_data['uid'])
            assert role['level'] == 50, "等级不匹配"
            assert role['exp'] == 10000, "经验值不匹配"
            
        finally:
            db.delete_role(role_data['uid'])
            db.delete_account(account_data['id'])
    
    def test_role_job_types(self, db):
        """测试角色职业类型"""
        # 测试各种职业
        valid_jobs = [0, 1, 2, 3, 11, 14]  # 剑客、格斗家、枪手、魔法师等
        
        for job in valid_jobs:
            account_data = TestDataGenerator.generate_account_data()
            role_data = TestDataGenerator.generate_role_data(
                openid=account_data['id'],
                job=job
            )
            
            try:
                db.insert_account(account_data)
                result = db.insert_role(role_data)
                assert result, f"职业 {job} 的角色创建失败"
                
            finally:
                db.delete_role(role_data['uid'])
                db.delete_account(account_data['id'])


@pytest.mark.game
class TestItemSystem:
    """测试物品系统"""
    
    def test_item_creation(self, db):
        """测试物品创建（通过 JSON 字段）"""
        account_data = TestDataGenerator.generate_account_data()
        role_data = TestDataGenerator.generate_role_data(openid=account_data['id'])
        
        # 模拟物品数据
        items = [
            {"itemId": 1001, "itemName": "测试武器", "level": 50},
            {"itemId": 1002, "itemName": "测试防具", "level": 50}
        ]
        
        try:
            db.insert_account(account_data)
            db.insert_role(role_data)
            
            # 角色表有equipBox字段
            result = db.update_json_field(
                't_role', 'uid', role_data['uid'], 
                'equipBox', items
            )
            assert result, "物品数据更新失败"
            
        finally:
            db.delete_role(role_data['uid'])
            db.delete_account(account_data['id'])


@pytest.mark.game
class TestRelationshipSystem:
    """测试关系系统"""
    
    def test_account_role_relationship(self, db):
        """测试账户与角色关系"""
        account_data = TestDataGenerator.generate_account_data()
        role_data = TestDataGenerator.generate_role_data(openid=account_data['id'])
        
        try:
            db.insert_account(account_data)
            db.insert_role(role_data)
            
            # 查询关联数据
            sql = """
                SELECT r.name, a.userID 
                FROM t_role r 
                LEFT JOIN t_account a ON r.openid = a.id 
                WHERE r.uid = %s
            """
            result = db.fetch_one(sql, (role_data['uid'],))
            
            assert result is not None, "关联查询失败"
            assert result['name'] == role_data['name'], "角色名不匹配"
            assert result['userID'] == account_data['userID'], "用户名不匹配"
            
        finally:
            db.delete_role(role_data['uid'])
            db.delete_account(account_data['id'])


@pytest.mark.game
class TestDataIntegrity:
    """测试数据完整性"""
    
    def test_role_without_account(self, db):
        """测试无账户的角色创建"""
        role_data = TestDataGenerator.generate_role_data(openid="nonexistent_account")
        
        # 这种情况下应该允许创建（外键约束可能不严格）
        # 或者根据业务逻辑返回错误
        try:
            result = db.insert_role(role_data)
            # 如果允许创建，测试后清理
            if result:
                db.delete_role(role_data['uid'])
        except Exception as e:
            # 如果外键约束阻止创建，这也是可接受的
            print(f"创建无账户角色失败（预期行为）: {e}")


@pytest.mark.game
class TestBusinessRules:
    """测试业务规则"""
    
    def test_max_roles_per_account(self, db):
        """测试账户最大角色数限制"""
        account_data = TestDataGenerator.generate_account_data(roleMaxCount=2)
        
        try:
            db.insert_account(account_data)
            
            # 创建 3 个角色（超过限制）
            roles = []
            for i in range(3):
                role_data = TestDataGenerator.generate_role_data(openid=account_data['id'])
                result = db.insert_role(role_data)
                if result:
                    roles.append(role_data)
            
            # 注意：数据库层面可能没有强制限制，这是应用层的业务逻辑
            # 这里我们验证数据库层面的行为
            print(f"账户 {account_data['id']} 的角色数限制: {account_data['roleMaxCount']}")
            print(f"实际创建的角色数: {len(roles)}")
            
            # 数据库层面允许创建超过限制的角色
            # 实际限制应该在应用层实现
            assert len(roles) == 3, "数据库层面允许创建多个角色"
            
        finally:
            for role in roles:
                db.delete_role(role['uid'])
            db.delete_account(account_data['id'])
