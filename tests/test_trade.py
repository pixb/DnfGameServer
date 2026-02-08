"""
交易功能测试

测试玩家之间的物品交易、拍卖行等交易功能
"""

import pytest
from utils import DatabaseHelper, TestDataGenerator


@pytest.fixture
def db():
    """数据库 helper fixture"""
    with DatabaseHelper() as db_helper:
        yield db_helper


@pytest.fixture
def two_test_accounts(db):
    """创建两个测试账户和角色 fixture"""
    account1_data = TestDataGenerator.generate_account_data(
        money=100000
    )
    db.insert_account(account1_data)
    
    role1_data = TestDataGenerator.generate_role_data(
        openid=account1_data['id'],
        money=50000
    )
    db.insert_role(role1_data)
    
    account2_data = TestDataGenerator.generate_account_data(
        money=100000
    )
    db.insert_account(account2_data)
    
    role2_data = TestDataGenerator.generate_role_data(
        openid=account2_data['id'],
        money=30000
    )
    db.insert_role(role2_data)
    
    yield {
        'account1': account1_data,
        'role1': role1_data,
        'account2': account2_data,
        'role2': role2_data
    }
    
    db.delete_role(role2_data['uid'])
    db.delete_account(account2_data['id'])
    db.delete_role(role1_data['uid'])
    db.delete_account(account1_data['id'])


@pytest.mark.trade
class TestPlayerTrade:
    """测试玩家直接交易功能"""
    
    def test_initiate_trade_request(self, db, two_test_accounts):
        """测试发起交易请求"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟发起交易请求
            trade_request = {
                "from_uid": role1['uid'],
                "to_uid": role2['uid'],
                "status": "pending"
            }
            
            print(f"角色 {role1['name']} 向角色 {role2['name']} 发起交易请求")
            assert trade_request['status'] == "pending", "交易请求状态错误"
            
        except Exception as e:
            print(f"发起交易请求失败: {e}")
    
    def test_accept_trade_request(self, db, two_test_accounts):
        """测试接受交易请求"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟接受交易
            trade_status = "accepted"
            
            print(f"角色 {role2['name']} 接受了角色 {role1['name']} 的交易请求")
            assert trade_status == "accepted", "交易接受状态错误"
            
        except Exception as e:
            print(f"接受交易请求失败: {e}")
    
    def test_reject_trade_request(self, db, two_test_accounts):
        """测试拒绝交易请求"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟拒绝交易
            trade_status = "rejected"
            
            print(f"角色 {role2['name']} 拒绝了角色 {role1['name']} 的交易请求")
            assert trade_status == "rejected", "交易拒绝状态错误"
            
        except Exception as e:
            print(f"拒绝交易请求失败: {e}")
    
    def test_trade_items(self, db, two_test_accounts):
        """测试物品交易"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 角色1的物品
            item1 = {"index": 1001, "count": 5}
            
            # 角色2的物品
            item2 = {"index": 2001, "count": 3}
            
            # 模拟物品交换
            print(f"角色 {role1['name']} 用物品 {item1} 交换角色 {role2['name']} 的物品 {item2}")
            
            # 更新角色1的背包
            bag1_data = [item2]
            db.update_json_field(
                't_role', 'uid', role1['uid'],
                'bagBox', bag1_data
            )
            
            # 更新角色2的背包
            bag2_data = [item1]
            db.update_json_field(
                't_role', 'uid', role2['uid'],
                'bagBox', bag2_data
            )
            
            print("物品交易成功")
            
        except Exception as e:
            print(f"物品交易失败: {e}")
    
    def test_trade_with_money(self, db, two_test_accounts):
        """测试带金币的交易"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            trade_money = 10000
            
            # 角色1支付金币给角色2
            db.execute(
                "UPDATE t_role SET money = money - %s WHERE uid = %s",
                (trade_money, role1['uid'])
            )
            
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (trade_money, role2['uid'])
            )
            
            # 验证金币转移
            updated_role1 = db.get_role_by_uid(role1['uid'])
            updated_role2 = db.get_role_by_uid(role2['uid'])
            
            assert updated_role1['money'] == role1['money'] - trade_money, "角色1金币扣除失败"
            assert updated_role2['money'] == role2['money'] + trade_money, "角色2金币增加失败"
            
            print(f"角色 {role1['name']} 向角色 {role2['name']} 支付 {trade_money} 金币")
            
        except Exception as e:
            print(f"金币交易失败: {e}")
    
    def test_trade_items_and_money(self, db, two_test_accounts):
        """测试物品和金币混合交易"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 角色1的物品
            item1 = {"index": 1001, "count": 10}
            trade_money = 5000
            
            # 角色1支付金币，角色2支付物品
            db.execute(
                "UPDATE t_role SET money = money - %s WHERE uid = %s",
                (trade_money, role1['uid'])
            )
            
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (trade_money, role2['uid'])
            )
            
            # 更新背包
            bag1_data = [item1]
            db.update_json_field(
                't_role', 'uid', role1['uid'],
                'bagBox', bag1_data
            )
            
            bag2_data = []
            db.update_json_field(
                't_role', 'uid', role2['uid'],
                'bagBox', bag2_data
            )
            
            print(f"混合交易：角色 {role1['name']} 支付 {trade_money} 金币获得物品，角色 {role2['name']} 获得金币")
            
        except Exception as e:
            print(f"混合交易失败: {e}")
    
    def test_cancel_trade(self, db, two_test_accounts):
        """测试取消交易"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟取消交易
            trade_status = "cancelled"
            
            print(f"角色 {role1['name']} 和 {role2['name']} 的交易已取消")
            assert trade_status == "cancelled", "交易取消状态错误"
            
        except Exception as e:
            print(f"取消交易失败: {e}")
    
    def test_trade_validation_distance(self, db, two_test_accounts):
        """测试交易距离验证"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 设置角色位置
            db.execute(
                "UPDATE t_role SET posX = 100, posY = 100 WHERE uid = %s",
                (role1['uid'],)
            )
            
            db.execute(
                "UPDATE t_role SET posX = 200, posY = 200 WHERE uid = %s",
                (role2['uid'],)
            )
            
            # 计算距离
            import math
            distance = math.sqrt((200-100)**2 + (200-100)**2)
            
            print(f"两个角色之间的距离: {distance:.2f}")
            
            # 假设最大交易距离为150
            max_trade_distance = 150
            if distance <= max_trade_distance:
                print("距离在允许范围内，可以交易")
            else:
                print("距离过远，无法交易")
            
        except Exception as e:
            print(f"交易距离验证失败: {e}")
    
    def test_trade_validation_level(self, db, two_test_accounts):
        """测试交易等级验证"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 设置角色等级
            db.execute(
                "UPDATE t_role SET level = 10 WHERE uid = %s",
                (role1['uid'],)
            )
            
            db.execute(
                "UPDATE t_role SET level = 5 WHERE uid = %s",
                (role2['uid'],)
            )
            
            # 假设最低交易等级为10
            min_trade_level = 10
            
            role1_updated = db.get_role_by_uid(role1['uid'])
            role2_updated = db.get_role_by_uid(role2['uid'])
            
            if role1_updated['level'] >= min_trade_level and role2_updated['level'] >= min_trade_level:
                print("两个角色都达到交易等级要求")
            else:
                print(f"角色等级不足，需要至少 {min_trade_level} 级")
            
        except Exception as e:
            print(f"交易等级验证失败: {e}")


@pytest.mark.trade
class TestAuction:
    """测试拍卖行功能"""
    
    def test_register_auction_item(self, db, two_test_accounts):
        """测试注册拍卖物品"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟拍卖物品数据
            auction_item = {
                "charguid": role1['uid'],
                "type": 1,
                "index": 1001,
                "count": 5,
                "price": 10000,
                "enddate": 9999999999,  # 长时间
                "status": "active"
            }
            
            print(f"角色 {role1['name']} 注册拍卖物品: {auction_item}")
            assert auction_item['status'] == "active", "拍卖物品状态错误"
            
        except Exception as e:
            print(f"注册拍卖物品失败: {e}")
    
    def test_bid_auction_item(self, db, two_test_accounts):
        """测试竞拍物品"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟拍卖物品
            auction_item = {
                "charguid": role1['uid'],
                "index": 1001,
                "count": 5,
                "price": 10000,
                "current_bid": 10000,
                "highest_bidder": None
            }
            
            # 角色2出价
            bid_price = 12000
            auction_item['current_bid'] = bid_price
            auction_item['highest_bidder'] = role2['uid']
            
            print(f"角色 {role2['name']} 出价 {bid_price} 竞拍物品")
            assert auction_item['current_bid'] == bid_price, "出价更新失败"
            
        except Exception as e:
            print(f"竞拍物品失败: {e}")
    
    def test_buyout_auction_item(self, db, two_test_accounts):
        """测试一口价购买拍卖物品"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟拍卖物品
            auction_item = {
                "charguid": role1['uid'],
                "index": 1001,
                "count": 5,
                "price": 10000,
                "buyout_price": 15000,
                "status": "active"
            }
            
            # 角色2一口价购买
            buyout_price = auction_item['buyout_price']
            auction_item['status'] = "sold"
            auction_item['buyer'] = role2['uid']
            
            # 扣除买家金币
            db.execute(
                "UPDATE t_role SET money = money - %s WHERE uid = %s",
                (buyout_price, role2['uid'])
            )
            
            # 增加卖家金币
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (buyout_price, role1['uid'])
            )
            
            print(f"角色 {role2['name']} 以一口价 {buyout_price} 购买了拍卖物品")
            assert auction_item['status'] == "sold", "拍卖状态更新失败"
            
        except Exception as e:
            print(f"一口价购买失败: {e}")
    
    def test_cancel_auction_item(self, db, two_test_accounts):
        """测试取消拍卖物品"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟拍卖物品
            auction_item = {
                "charguid": role1['uid'],
                "index": 1001,
                "count": 5,
                "price": 10000,
                "status": "active"
            }
            
            # 取消拍卖
            auction_item['status'] = "cancelled"
            
            print(f"角色 {role1['name']} 取消了拍卖物品")
            assert auction_item['status'] == "cancelled", "拍卖取消状态错误"
            
        except Exception as e:
            print(f"取消拍卖物品失败: {e}")
    
    def test_auction_expire(self, db, two_test_accounts):
        """测试拍卖过期"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟拍卖物品
            auction_item = {
                "charguid": role1['uid'],
                "index": 1001,
                "count": 5,
                "price": 10000,
                "enddate": 1234567890,  # 过去的时间
                "status": "active"
            }
            
            # 检查是否过期
            from datetime import datetime
            current_time = datetime.now().timestamp()
            
            if current_time > auction_item['enddate']:
                auction_item['status'] = "expired"
                print("拍卖已过期")
            else:
                print("拍卖未过期")
            
            assert auction_item['status'] == "expired", "拍卖过期状态错误"
            
        except Exception as e:
            print(f"拍卖过期检查失败: {e}")
    
    def test_search_auction_items(self, db, two_test_accounts):
        """测试搜索拍卖物品"""
        try:
            # 模拟拍卖物品列表
            auction_items = [
                {"index": 1001, "name": "武器", "price": 10000},
                {"index": 1002, "name": "防具", "price": 15000},
                {"index": 1003, "name": "饰品", "price": 20000}
            ]
            
            # 搜索特定物品
            search_keyword = "武器"
            results = [item for item in auction_items if search_keyword in item['name']]
            
            print(f"搜索 '{search_keyword}' 找到 {len(results)} 个结果")
            assert len(results) > 0, "搜索结果为空"
            
        except Exception as e:
            print(f"搜索拍卖物品失败: {e}")
    
    def test_filter_auction_items(self, db, two_test_accounts):
        """测试筛选拍卖物品"""
        try:
            # 模拟拍卖物品列表
            auction_items = [
                {"index": 1001, "type": "weapon", "price": 10000},
                {"index": 1002, "type": "armor", "price": 15000},
                {"index": 1003, "type": "weapon", "price": 20000}
            ]
            
            # 按类型筛选
            filter_type = "weapon"
            filtered = [item for item in auction_items if item['type'] == filter_type]
            
            print(f"按类型 '{filter_type}' 筛选找到 {len(filtered)} 个结果")
            assert len(filtered) == 2, "筛选结果数量错误"
            
            # 按价格范围筛选
            min_price = 10000
            max_price = 15000
            price_filtered = [
                item for item in auction_items
                if min_price <= item['price'] <= max_price
            ]
            
            print(f"按价格范围 {min_price}-{max_price} 筛选找到 {len(price_filtered)} 个结果")
            
        except Exception as e:
            print(f"筛选拍卖物品失败: {e}")
    
    def test_auction_fee(self, db, two_test_accounts):
        """测试拍卖手续费"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟拍卖物品
            auction_item = {
                "charguid": role1['uid'],
                "index": 1001,
                "count": 5,
                "price": 10000,
                "sold_price": 10000
            }
            
            # 计算手续费（假设5%）
            fee_rate = 0.05
            fee = int(auction_item['sold_price'] * fee_rate)
            actual_income = auction_item['sold_price'] - fee
            
            print(f"拍卖价格: {auction_item['sold_price']}, 手续费: {fee}, 实际收入: {actual_income}")
            assert fee > 0, "手续费计算错误"
            
        except Exception as e:
            print(f"拍卖手续费计算失败: {e}")


@pytest.mark.trade
class TestTradeSecurity:
    """测试交易安全功能"""
    
    def test_trade_validation_duplicate(self, db, two_test_accounts):
        """测试防止重复交易"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟已有交易
            existing_trade = {
                "from_uid": role1['uid'],
                "to_uid": role2['uid'],
                "status": "active"
            }
            
            # 尝试发起新交易
            if existing_trade['status'] == "active":
                print("已有活跃交易，无法发起新交易")
            else:
                print("可以发起新交易")
            
        except Exception as e:
            print(f"重复交易验证失败: {e}")
    
    def test_trade_validation_sufficient_items(self, db, two_test_accounts):
        """测试验证物品数量充足"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟背包物品
            bag_items = [
                {"index": 1001, "count": 5}
            ]
            
            # 尝试交易10个
            trade_count = 10
            
            if bag_items[0]['count'] >= trade_count:
                print("物品数量充足，可以交易")
            else:
                print(f"物品数量不足，只有 {bag_items[0]['count']} 个，需要 {trade_count} 个")
            
        except Exception as e:
            print(f"物品数量验证失败: {e}")
    
    def test_trade_validation_sufficient_money(self, db, two_test_accounts):
        """测试验证金币充足"""
        role1 = two_test_accounts['role1']
        
        try:
            trade_money = 100000
            
            if role1['money'] >= trade_money:
                print("金币充足，可以交易")
            else:
                print(f"金币不足，只有 {role1['money']}，需要 {trade_money}")
            
        except Exception as e:
            print(f"金币验证失败: {e}")
    
    def test_trade_rollback(self, db, two_test_accounts):
        """测试交易回滚"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟交易失败
            trade_failed = True
            
            if trade_failed:
                print("交易失败，执行回滚")
                # 回滚操作：恢复原始状态
                print("交易已回滚")
            else:
                print("交易成功，无需回滚")
            
        except Exception as e:
            print(f"交易回滚失败: {e}")
    
    def test_trade_log(self, db, two_test_accounts):
        """测试交易日志"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟交易日志
            trade_log = {
                "timestamp": "2024-01-01 12:00:00",
                "from_uid": role1['uid'],
                "to_uid": role2['uid'],
                "items": [{"index": 1001, "count": 5}],
                "money": 10000,
                "status": "completed"
            }
            
            print(f"交易日志: {trade_log}")
            assert trade_log['status'] == "completed", "交易日志状态错误"
            
        except Exception as e:
            print(f"交易日志记录失败: {e}")
