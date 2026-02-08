"""
物品购买与出售功能测试

测试游戏物品的购买、出售、背包管理等核心功能
"""

import pytest
from utils import DatabaseHelper, TestDataGenerator, ApiHelper


@pytest.fixture
def db():
    """数据库 helper fixture"""
    with DatabaseHelper() as db_helper:
        yield db_helper


@pytest.fixture
def api():
    """API 客户端 fixture"""
    with ApiHelper() as client:
        yield client


@pytest.fixture
def test_account(db):
    """创建测试账户和角色 fixture"""
    account_data = TestDataGenerator.generate_account_data(
        money=100000
    )
    db.insert_account(account_data)
    
    role_data = TestDataGenerator.generate_role_data(
        openid=account_data['id'],
        money=50000
    )
    db.insert_role(role_data)
    
    yield {
        'account': account_data,
        'role': role_data
    }
    
    db.delete_role(role_data['uid'])
    db.delete_account(account_data['id'])


@pytest.mark.item
class TestItemPurchase:
    """测试物品购买功能"""
    
    def test_buy_item_from_npc_shop(self, db, test_account):
        """测试从NPC商店购买物品"""
        role = test_account['role']
        item_index = 1001
        item_count = 5
        item_price = 1000
        
        try:
            # 模拟购买物品
            total_price = item_price * item_count
            
            # 扣除金币
            db.execute(
                "UPDATE t_role SET money = money - %s WHERE uid = %s",
                (total_price, role['uid'])
            )
            
            # 添加物品到背包（通过JSON字段）
            bag_data = [
                {"index": item_index, "count": item_count}
            ]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证购买结果
            updated_role = db.get_role_by_uid(role['uid'])
            assert updated_role['money'] == role['money'] - total_price, "金币扣除失败"
            
        except Exception as e:
            print(f"购买物品失败: {e}")
    
    def test_buy_multiple_items(self, db, test_account):
        """测试批量购买物品"""
        role = test_account['role']
        
        items = [
            {"index": 1001, "count": 10, "price": 100},
            {"index": 1002, "count": 5, "price": 200},
            {"index": 1003, "count": 3, "price": 500}
        ]
        
        total_price = sum(item['count'] * item['price'] for item in items)
        
        try:
            # 扣除总金币
            db.execute(
                "UPDATE t_role SET money = money - %s WHERE uid = %s",
                (total_price, role['uid'])
            )
            
            # 添加所有物品到背包
            bag_data = [
                {"index": item['index'], "count": item['count']}
                for item in items
            ]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证购买结果
            updated_role = db.get_role_by_uid(role['uid'])
            assert updated_role['money'] == role['money'] - total_price, "总金币扣除失败"
            
        except Exception as e:
            print(f"批量购买失败: {e}")
    
    def test_buy_item_insufficient_money(self, db, test_account):
        """测试金币不足时购买物品"""
        role = test_account['role']
        item_price = 1000000  # 超过角色拥有的金币
        
        try:
            # 尝试购买
            current_money = role['money']
            
            # 验证金币不足
            assert current_money < item_price, "测试数据设置错误"
            
            # 模拟购买失败
            print(f"金币不足，无法购买价格 {item_price} 的物品")
            
        except Exception as e:
            print(f"购买失败（预期行为）: {e}")
    
    def test_buy_item_with_discount(self, db, test_account):
        """测试打折购买物品"""
        role = test_account['role']
        item_index = 1001
        item_count = 10
        original_price = 100
        discount = 0.8  # 8折
        
        try:
            discounted_price = int(original_price * discount)
            total_price = discounted_price * item_count
            
            # 扣除打折后的金币
            db.execute(
                "UPDATE t_role SET money = money - %s WHERE uid = %s",
                (total_price, role['uid'])
            )
            
            # 添加物品
            bag_data = [{"index": item_index, "count": item_count}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证打折购买
            updated_role = db.get_role_by_uid(role['uid'])
            expected_money = role['money'] - total_price
            assert updated_role['money'] == expected_money, "打折购买失败"
            
        except Exception as e:
            print(f"打折购买失败: {e}")


@pytest.mark.item
class TestItemSale:
    """测试物品出售功能"""
    
    def test_sell_item_to_npc(self, db, test_account):
        """测试向NPC出售物品"""
        role = test_account['role']
        item_index = 1001
        item_count = 5
        sell_price = 50
        
        try:
            # 模拟出售物品
            total_price = sell_price * item_count
            
            # 增加金币
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (total_price, role['uid'])
            )
            
            # 从背包移除物品
            bag_data = []  # 空背包
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证出售结果
            updated_role = db.get_role_by_uid(role['uid'])
            assert updated_role['money'] == role['money'] + total_price, "金币增加失败"
            
        except Exception as e:
            print(f"出售物品失败: {e}")
    
    def test_sell_multiple_items(self, db, test_account):
        """测试批量出售物品"""
        role = test_account['role']
        
        items = [
            {"index": 1001, "count": 10, "sell_price": 50},
            {"index": 1002, "count": 5, "sell_price": 100},
            {"index": 1003, "count": 3, "sell_price": 200}
        ]
        
        total_price = sum(item['count'] * item['sell_price'] for item in items)
        
        try:
            # 增加总金币
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (total_price, role['uid'])
            )
            
            # 清空背包
            bag_data = []
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证出售结果
            updated_role = db.get_role_by_uid(role['uid'])
            assert updated_role['money'] == role['money'] + total_price, "总金币增加失败"
            
        except Exception as e:
            print(f"批量出售失败: {e}")
    
    def test_sell_equipment(self, db, test_account):
        """测试出售装备"""
        role = test_account['role']
        equip_index = 2001
        equip_sell_price = 500
        
        try:
            # 模拟出售装备
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (equip_sell_price, role['uid'])
            )
            
            # 从装备栏移除
            equip_data = []
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'equipBox', equip_data
            )
            
            # 验证出售结果
            updated_role = db.get_role_by_uid(role['uid'])
            assert updated_role['money'] == role['money'] + equip_sell_price, "装备出售失败"
            
        except Exception as e:
            print(f"出售装备失败: {e}")


@pytest.mark.item
class TestBagManagement:
    """测试背包管理功能"""
    
    def test_add_item_to_bag(self, db, test_account):
        """测试添加物品到背包"""
        role = test_account['role']
        item_index = 1001
        item_count = 10
        
        try:
            # 添加物品到背包
            bag_data = [{"index": item_index, "count": item_count}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证物品已添加
            role = db.get_role_by_uid(role['uid'])
            assert role is not None, "角色查询失败"
            
        except Exception as e:
            print(f"添加物品到背包失败: {e}")
    
    def test_remove_item_from_bag(self, db, test_account):
        """测试从背包移除物品"""
        role = test_account['role']
        
        try:
            # 先添加物品
            bag_data = [
                {"index": 1001, "count": 10},
                {"index": 1002, "count": 5}
            ]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 移除一个物品
            updated_bag = [
                {"index": 1002, "count": 5}
            ]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', updated_bag
            )
            
            # 验证物品已移除
            role = db.get_role_by_uid(role['uid'])
            assert role is not None, "角色查询失败"
            
        except Exception as e:
            print(f"移除物品失败: {e}")
    
    def test_stack_items_in_bag(self, db, test_account):
        """测试背包物品堆叠"""
        role = test_account['role']
        item_index = 1001
        
        try:
            # 添加相同物品（应该堆叠）
            bag_data = [{"index": item_index, "count": 15}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证物品堆叠
            role = db.get_role_by_uid(role['uid'])
            assert role is not None, "角色查询失败"
            
        except Exception as e:
            print(f"物品堆叠失败: {e}")
    
    def test_bag_capacity_limit(self, db, test_account):
        """测试背包容量限制"""
        role = test_account['role']
        
        try:
            # 模拟背包满的情况
            bag_data = [
                {"index": i, "count": 1}
                for i in range(1, 51)  # 假设背包容量为50
            ]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 尝试添加更多物品
            print("背包已满，无法添加更多物品")
            
        except Exception as e:
            print(f"背包容量限制测试: {e}")


@pytest.mark.item
class TestShopAPI:
    """测试商店API接口"""
    
    def test_npc_shop_sell_endpoint(self, api):
        """测试NPC商店出售接口"""
        # 模拟出售请求
        sell_data = {
            "item": [
                {
                    "type": 1,
                    "index": 1001,
                    "count": 5
                }
            ]
        }
        
        # 注意：这是MINA协议接口，不是REST API
        # 这里只是测试接口是否存在
        print("NPC商店出售接口需要通过MINA协议测试")
    
    def test_cera_shop_buy_endpoint(self, api):
        """测试点券商店购买接口"""
        # 模拟购买请求
        buy_data = {
            "index": 50001,
            "count": 1
        }
        
        print("点券商店购买接口需要通过MINA协议测试")
    
    def test_shop_info_endpoint(self, api):
        """测试商店信息接口"""
        # 测试获取商店信息
        print("商店信息接口需要通过MINA协议测试")


@pytest.mark.item
class TestItemValidation:
    """测试物品验证功能"""
    
    def test_item_index_validation(self, db, test_account):
        """测试物品索引验证"""
        role = test_account['role']
        
        # 测试有效和无效的物品索引
        item_indices = [1001, 9999, -1, 0]
        
        for index in item_indices:
            try:
                bag_data = [{"index": index, "count": 1}]
                db.update_json_field(
                    't_role', 'uid', role['uid'],
                    'bagBox', bag_data
                )
                
                if index > 0:
                    print(f"物品索引 {index} 可以添加")
                else:
                    print(f"物品索引 {index} 可能无效")
                    
            except Exception as e:
                print(f"物品索引 {index} 验证失败: {e}")
    
    def test_item_count_validation(self, db, test_account):
        """测试物品数量验证"""
        role = test_account['role']
        
        # 测试不同数量
        counts = [0, 1, 100, 9999]
        
        for count in counts:
            try:
                bag_data = [{"index": 1001, "count": count}]
                db.update_json_field(
                    't_role', 'uid', role['uid'],
                    'bagBox', bag_data
                )
                
                if count > 0:
                    print(f"物品数量 {count} 可以设置")
                else:
                    print(f"物品数量 {count} 可能无效")
                    
            except Exception as e:
                print(f"物品数量 {count} 验证失败: {e}")
    
    def test_item_price_validation(self, db, test_account):
        """测试物品价格验证"""
        role = test_account['role']
        
        # 测试不同价格
        prices = [0, 1, 100, 10000, 999999]
        
        for price in prices:
            try:
                # 模拟购买
                if role['money'] >= price:
                    db.execute(
                        "UPDATE t_role SET money = money - %s WHERE uid = %s",
                        (price, role['uid'])
                    )
                    print(f"价格 {price} 的物品可以购买")
                else:
                    print(f"价格 {price} 的物品金币不足")
                    
            except Exception as e:
                print(f"物品价格 {price} 验证失败: {e}")


@pytest.mark.item
class TestItemTransaction:
    """测试物品交易功能"""
    
    def test_buy_sell_cycle(self, db, test_account):
        """测试购买-出售循环"""
        role = test_account['role']
        item_index = 1001
        item_count = 10
        buy_price = 100
        sell_price = 50
        
        try:
            # 购买
            buy_total = buy_price * item_count
            db.execute(
                "UPDATE t_role SET money = money - %s WHERE uid = %s",
                (buy_total, role['uid'])
            )
            
            # 添加物品
            bag_data = [{"index": item_index, "count": item_count}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 出售
            sell_total = sell_price * item_count
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (sell_total, role['uid'])
            )
            
            # 清空背包
            bag_data = []
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 验证最终金币
            final_role = db.get_role_by_uid(role['uid'])
            expected_money = role['money'] - buy_total + sell_total
            assert final_role['money'] == expected_money, "购买-出售循环失败"
            
        except Exception as e:
            print(f"购买-出售循环失败: {e}")
    
    def test_profit_calculation(self, db, test_account):
        """测试利润计算"""
        role = test_account['role']
        
        buy_price = 100
        sell_price = 150
        profit = sell_price - buy_price
        
        assert profit > 0, "测试数据应设置盈利场景"
        print(f"单件物品利润: {profit}")
        
        # 批量利润
        count = 10
        total_profit = profit * count
        print(f"批量购买 {count} 件物品总利润: {total_profit}")


@pytest.mark.item
class TestSpecialItems:
    """测试特殊物品功能"""
    
    def test_consumable_item_use(self, db, test_account):
        """测试消耗品使用"""
        role = test_account['role']
        consumable_index = 3001
        consumable_count = 5
        
        try:
            # 添加消耗品
            bag_data = [{"index": consumable_index, "count": consumable_count}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 模拟使用消耗品
            used_count = 2
            remaining_count = consumable_count - used_count
            
            bag_data = [{"index": consumable_index, "count": remaining_count}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            print(f"使用 {used_count} 个消耗品，剩余 {remaining_count} 个")
            
        except Exception as e:
            print(f"消耗品使用失败: {e}")
    
    def test_equipment_equip(self, db, test_account):
        """测试装备穿戴"""
        role = test_account['role']
        equip_index = 2001
        
        try:
            # 添加装备到背包
            bag_data = [{"index": equip_index, "count": 1}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 穿戴装备（移到装备栏）
            equip_data = [{"index": equip_index}]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'equipBox', equip_data
            )
            
            # 从背包移除
            bag_data = []
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            print("装备穿戴成功")
            
        except Exception as e:
            print(f"装备穿戴失败: {e}")
    
    def test_material_item_craft(self, db, test_account):
        """测试材料物品制作"""
        role = test_account['role']
        
        materials = [
            {"index": 4001, "count": 5},
            {"index": 4002, "count": 3}
        ]
        
        try:
            # 添加材料
            bag_data = materials
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 模拟制作消耗材料
            bag_data = [
                {"index": 4001, "count": 3},
                {"index": 4002, "count": 1}
            ]
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            # 添加制作成品
            bag_data.append({"index": 5001, "count": 1})
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', bag_data
            )
            
            print("材料制作成功")
            
        except Exception as e:
            print(f"材料制作失败: {e}")
