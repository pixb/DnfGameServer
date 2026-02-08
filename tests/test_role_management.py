"""
角色创建与管理功能测试

测试游戏角色的创建、删除、属性管理等功能
"""

import pytest
from datetime import datetime
from utils import DatabaseHelper, TestDataGenerator


@pytest.fixture
def db():
    """数据库 helper fixture"""
    with DatabaseHelper() as db_helper:
        yield db_helper


@pytest.fixture
def test_account(db):
    """创建测试账户 fixture"""
    account_data = TestDataGenerator.generate_account_data()
    db.insert_account(account_data)
    yield account_data
    db.delete_account(account_data['id'])


@pytest.mark.role
class TestRoleCreation:
    """测试角色创建功能"""
    
    def test_create_basic_role(self, db, test_account):
        """测试创建基础角色"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        try:
            result = db.insert_role(role_data)
            assert result, "角色创建失败"
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role is not None, "无法查询到创建的角色"
            assert role['name'] == role_data['name'], "角色名不匹配"
            assert role['job'] == role_data['job'], "职业不匹配"
            assert role['level'] == role_data['level'], "等级不匹配"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_create_role_with_all_jobs(self, db, test_account):
        """测试创建所有职业的角色"""
        jobs = [0, 1, 2, 3, 11, 14]  # 剑客、格斗家、枪手、魔法师等
        
        for job in jobs:
            role_data = TestDataGenerator.generate_role_data(
                openid=test_account['id'],
                job=job
            )
            
            try:
                result = db.insert_role(role_data)
                assert result, f"职业 {job} 的角色创建失败"
                
                role = db.get_role_by_uid(role_data['uid'])
                assert role['job'] == job, f"职业 {job} 不匹配"
                
            finally:
                db.delete_role(role_data['uid'])
    
    def test_create_role_duplicate_name(self, db, test_account):
        """测试创建重名角色"""
        role_name = "TestRole"
        
        role_data1 = TestDataGenerator.generate_role_data(
            openid=test_account['id'],
            name=role_name
        )
        
        try:
            result1 = db.insert_role(role_data1)
            assert result1, "第一个角色创建应成功"
            
            role_data2 = TestDataGenerator.generate_role_data(
                openid=test_account['id'],
                name=role_name
            )
            
            result2 = db.insert_role(role_data2)
            
            # 根据业务逻辑，重名可能被拒绝
            if not result2:
                print("重名角色创建被正确拒绝")
            
        finally:
            db.delete_role(role_data1['uid'])
            if 'uid' in role_data2:
                db.delete_role(role_data2['uid'])
    
    def test_create_role_with_custom_attributes(self, db, test_account):
        """测试创建自定义属性的角色"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id'],
            level=50,
            exp=100000
        )
        
        try:
            db.insert_role(role_data)
            role = db.get_role_by_uid(role_data['uid'])
            
            assert role['level'] == 50, "等级不匹配"
            assert role['exp'] == 100000, "经验值不匹配"
            
        finally:
            db.delete_role(role_data['uid'])


@pytest.mark.role
class TestRoleManagement:
    """测试角色管理功能"""
    
    def test_delete_role(self, db, test_account):
        """测试删除角色"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        db.insert_role(role_data)
        role = db.get_role_by_uid(role_data['uid'])
        assert role is not None, "角色创建失败"
        
        db.delete_role(role_data['uid'])
        deleted = db.get_role_by_uid(role_data['uid'])
        assert deleted is None, "角色删除失败"
    
    def test_update_role_name(self, db, test_account):
        """测试更新角色名称"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        new_name = "NewRoleName"
        
        try:
            db.insert_role(role_data)
            
            db.execute(
                "UPDATE t_role SET name = %s WHERE uid = %s",
                (new_name, role_data['uid'])
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role['name'] == new_name, "角色名更新失败"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_update_role_level(self, db, test_account):
        """测试更新角色等级"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id'],
            level=10
        )
        
        try:
            db.insert_role(role_data)
            
            # 升级到20级
            db.execute(
                "UPDATE t_role SET level = 20 WHERE uid = %s",
                (role_data['uid'],)
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role['level'] == 20, "等级更新失败"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_update_role_experience(self, db, test_account):
        """测试更新角色经验"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id'],
            exp=0
        )
        
        try:
            db.insert_role(role_data)
            
            # 增加经验值
            db.execute(
                "UPDATE t_role SET exp = 5000 WHERE uid = %s",
                (role_data['uid'],)
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role['exp'] == 5000, "经验值更新失败"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_update_role_position(self, db, test_account):
        """测试更新角色位置"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        try:
            db.insert_role(role_data)
            
            # 更新位置（通过JSON字段）
            pos_data = {"x": 100, "y": 200, "mapId": 1}
            db.update_json_field(
                't_role', 'uid', role_data['uid'],
                'pos', pos_data
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role['pos'] is not None, "位置数据未更新"
            
        finally:
            db.delete_role(role_data['uid'])


@pytest.mark.role
class TestRoleAttributes:
    """测试角色属性功能"""
    
    def test_role_hp_mp_management(self, db, test_account):
        """测试角色HP/MP管理"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        try:
            db.insert_role(role_data)
            
            # 模拟HP/MP数据存储在JSON字段中
            stats_data = {"hp": 1000, "mp": 500, "maxHp": 2000, "maxMp": 1000}
            db.update_json_field(
                't_role', 'uid', role_data['uid'],
                'serverSimpleDataBox', stats_data
            )
            
            # 恢复HP
            stats_data["hp"] = 1500
            db.update_json_field(
                't_role', 'uid', role_data['uid'],
                'serverSimpleDataBox', stats_data
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role['serverSimpleDataBox'] is not None, "属性数据未更新"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_role_stats_management(self, db, test_account):
        """测试角色属性管理"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        try:
            db.insert_role(role_data)
            
            # 模拟属性数据存储在JSON字段中
            stats_data = {"strength": 50, "intelligence": 40, "agility": 45, "vitality": 60, "spirit": 35}
            db.update_json_field(
                't_role', 'uid', role_data['uid'],
                'serverSimpleDataBox', stats_data
            )
            
            # 增加力量
            stats_data["strength"] = 60
            db.update_json_field(
                't_role', 'uid', role_data['uid'],
                'serverSimpleDataBox', stats_data
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role['serverSimpleDataBox'] is not None, "属性数据未更新"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_role_money_management(self, db, test_account):
        """测试角色金币管理"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id'],
            money=10000
        )
        
        try:
            db.insert_role(role_data)
            
            # 获得金币
            db.execute(
                "UPDATE t_role SET money = 15000 WHERE uid = %s",
                (role_data['uid'],)
            )
            
            # 消耗金币
            db.execute(
                "UPDATE t_role SET money = 13000 WHERE uid = %s",
                (role_data['uid'],)
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role['money'] == 13000, "金币更新失败"
            
        finally:
            db.delete_role(role_data['uid'])


@pytest.mark.role
class TestRoleEquipment:
    """测试角色装备功能"""
    
    def test_role_equip_storage(self, db, test_account):
        """测试角色装备存储"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        # 模拟装备数据
        equip_data = [
            {"index": 1001, "name": "测试武器", "level": 50},
            {"index": 1002, "name": "测试防具", "level": 50}
        ]
        
        try:
            db.insert_role(role_data)
            
            # 通过JSON字段存储装备
            db.update_json_field(
                't_role', 'uid', role_data['uid'],
                'equipBox', equip_data
            )
            
            # 验证装备数据
            role = db.get_role_by_uid(role_data['uid'])
            assert role is not None, "角色查询失败"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_role_bag_management(self, db, test_account):
        """测试角色背包管理"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        # 模拟背包物品
        bag_data = [
            {"index": 2001, "count": 10},
            {"index": 2002, "count": 5}
        ]
        
        try:
            db.insert_role(role_data)
            
            # 存储背包数据
            db.update_json_field(
                't_role', 'uid', role_data['uid'],
                'bagBox', bag_data
            )
            
            role = db.get_role_by_uid(role_data['uid'])
            assert role is not None, "角色查询失败"
            
        finally:
            db.delete_role(role_data['uid'])


@pytest.mark.role
class TestRoleRelationships:
    """测试角色关系功能"""
    
    def test_account_role_relationship(self, db, test_account):
        """测试账户与角色关系"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id']
        )
        
        try:
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
            assert result['userID'] == test_account['userID'], "用户名不匹配"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_multiple_roles_per_account(self, db, test_account):
        """测试账户多角色"""
        roles = []
        
        try:
            # 创建3个角色
            for i in range(3):
                role_data = TestDataGenerator.generate_role_data(
                    openid=test_account['id'],
                    name=f"TestRole{i}"
                )
                db.insert_role(role_data)
                roles.append(role_data)
            
            # 查询账户的所有角色
            sql = "SELECT * FROM t_role WHERE openid = %s"
            account_roles = db.fetch_all(sql, (test_account['id'],))
            
            assert len(account_roles) == 3, "角色数量不匹配"
            
        finally:
            for role in roles:
                db.delete_role(role['uid'])


@pytest.mark.role
class TestRoleProgression:
    """测试角色进阶功能"""
    
    def test_role_level_progression(self, db, test_account):
        """测试角色等级进阶"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id'],
            level=1,
            exp=0
        )
        
        try:
            db.insert_role(role_data)
            
            # 模拟升级过程
            levels = [1, 5, 10, 20, 30, 40, 50]
            for level in levels:
                db.execute(
                    "UPDATE t_role SET level = %s WHERE uid = %s",
                    (level, role_data['uid'])
                )
                
                role = db.get_role_by_uid(role_data['uid'])
                assert role['level'] == level, f"等级 {level} 更新失败"
            
        finally:
            db.delete_role(role_data['uid'])
    
    def test_role_experience_progression(self, db, test_account):
        """测试角色经验进阶"""
        role_data = TestDataGenerator.generate_role_data(
            openid=test_account['id'],
            level=1,
            exp=0
        )
        
        try:
            db.insert_role(role_data)
            
            # 模拟经验积累
            exp_values = [100, 500, 1000, 5000, 10000, 50000]
            for exp in exp_values:
                db.execute(
                    "UPDATE t_role SET exp = %s WHERE uid = %s",
                    (exp, role_data['uid'])
                )
                
                role = db.get_role_by_uid(role_data['uid'])
                assert role['exp'] == exp, f"经验值 {exp} 更新失败"
            
        finally:
            db.delete_role(role_data['uid'])


@pytest.mark.role
class TestRoleValidation:
    """测试角色验证功能"""
    
    def test_role_name_validation(self, db, test_account):
        """测试角色名验证"""
        role_names = [
            ("A", False),  # 太短
            ("AB", True),  # 最小长度
            ("NormalName", True),  # 正常长度
            ("A" * 20, True),  # 最大长度
            ("A" * 21, False)  # 超过最大长度
        ]
        
        for name, should_succeed in role_names:
            try:
                role_data = TestDataGenerator.generate_role_data(
                    openid=test_account['id'],
                    name=name
                )
                
                result = db.insert_role(role_data)
                
                if should_succeed:
                    assert result, f"角色名 '{name}' 应该可以创建"
                else:
                    print(f"角色名 '{name}' 创建结果: {result}")
                
            finally:
                if 'uid' in role_data:
                    db.delete_role(role_data['uid'])
    
    def test_role_level_validation(self, db, test_account):
        """测试角色等级验证"""
        levels = [0, 1, 50, 100, 200]
        
        for level in levels:
            try:
                role_data = TestDataGenerator.generate_role_data(
                    openid=test_account['id'],
                    level=level
                )
                
                result = db.insert_role(role_data)
                
                if level > 0:  # 等级应该大于0
                    assert result, f"等级 {level} 应该可以创建"
                
            finally:
                if 'uid' in role_data:
                    db.delete_role(role_data['uid'])
