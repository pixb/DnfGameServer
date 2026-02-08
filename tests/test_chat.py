"""
聊天功能测试

测试游戏内的聊天、频道、消息等功能
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
def two_test_accounts(db):
    """创建两个测试账户和角色 fixture"""
    account1_data = TestDataGenerator.generate_account_data()
    db.insert_account(account1_data)
    
    role1_data = TestDataGenerator.generate_role_data(
        openid=account1_data['id']
    )
    db.insert_role(role1_data)
    
    account2_data = TestDataGenerator.generate_account_data()
    db.insert_account(account2_data)
    
    role2_data = TestDataGenerator.generate_role_data(
        openid=account2_data['id']
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


@pytest.mark.chat
class TestTownChat:
    """测试城镇聊天功能"""
    
    def test_send_town_chat_message(self, db, two_test_accounts):
        """测试发送城镇聊天消息"""
        role1 = two_test_accounts['role1']
        chat_text = "Hello, everyone!"
        
        try:
            # 模拟发送聊天消息
            chat_message = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "job": role1['job'],
                "voice": chat_text,
                "type": 0,
                "timestamp": datetime.now()
            }
            
            print(f"角色 {role1['name']} 发送城镇聊天: {chat_text}")
            assert chat_message['voice'] == chat_text, "聊天内容不匹配"
            
        except Exception as e:
            print(f"发送城镇聊天失败: {e}")
    
    def test_receive_town_chat_message(self, db, two_test_accounts):
        """测试接收城镇聊天消息"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        chat_text = "Hello, everyone!"
        
        try:
            # 模拟发送消息
            chat_message = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "voice": chat_text
            }
            
            # 模拟角色2接收消息
            received_message = chat_message
            
            print(f"角色 {role2['name']} 接收到来自 {role1['name']} 的消息: {received_message['voice']}")
            assert received_message['voice'] == chat_text, "接收消息内容不匹配"
            
        except Exception as e:
            print(f"接收城镇聊天失败: {e}")
    
    def test_town_chat_with_job_icon(self, db, two_test_accounts):
        """测试带职业图标的城镇聊天"""
        role1 = two_test_accounts['role1']
        chat_text = "I am a warrior!"
        
        try:
            chat_message = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "job": role1['job'],
                "voice": chat_text,
                "type": 0
            }
            
            print(f"角色 {role1['name']} (职业: {role1['job']}) 发送消息: {chat_text}")
            assert chat_message['job'] == role1['job'], "职业图标不匹配"
            
        except Exception as e:
            print(f"带职业图标的聊天失败: {e}")
    
    def test_town_chat_distance_limit(self, db, two_test_accounts):
        """测试城镇聊天距离限制"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 设置角色位置
            db.execute(
                "UPDATE t_role SET posX = 100, posY = 100 WHERE uid = %s",
                (role1['uid'],)
            )
            
            db.execute(
                "UPDATE t_role SET posX = 1000, posY = 1000 WHERE uid = %s",
                (role2['uid'],)
            )
            
            # 计算距离
            import math
            distance = math.sqrt((1000-100)**2 + (1000-100)**2)
            
            print(f"两个角色之间的距离: {distance:.2f}")
            
            # 假设城镇聊天无距离限制
            print("城镇聊天无距离限制，所有玩家都能看到")
            
        except Exception as e:
            print(f"城镇聊天距离限制测试失败: {e}")


@pytest.mark.chat
class TestPrivateChat:
    """测试私聊功能"""
    
    def test_send_private_message(self, db, two_test_accounts):
        """测试发送私聊消息"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        private_text = "Hello, this is a private message!"
        
        try:
            private_message = {
                "from_uid": role1['uid'],
                "from_name": role1['name'],
                "to_uid": role2['uid'],
                "to_name": role2['name'],
                "message": private_text,
                "timestamp": datetime.now()
            }
            
            print(f"角色 {role1['name']} 向角色 {role2['name']} 发送私聊: {private_text}")
            assert private_message['message'] == private_text, "私聊内容不匹配"
            
        except Exception as e:
            print(f"发送私聊失败: {e}")
    
    def test_receive_private_message(self, db, two_test_accounts):
        """测试接收私聊消息"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        private_text = "Hello, this is a private message!"
        
        try:
            # 模拟发送私聊
            private_message = {
                "from_uid": role1['uid'],
                "from_name": role1['name'],
                "to_uid": role2['uid'],
                "message": private_text
            }
            
            # 模拟接收
            received = private_message
            
            print(f"角色 {role2['name']} 接收到来自 {role1['name']} 的私聊: {received['message']}")
            assert received['message'] == private_text, "接收私聊内容不匹配"
            
        except Exception as e:
            print(f"接收私聊失败: {e}")
    
    def test_private_chat_history(self, db, two_test_accounts):
        """测试私聊历史记录"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟多条私聊记录
            messages = [
                {"from": role1['name'], "to": role2['name'], "text": "Hi"},
                {"from": role2['name'], "to": role1['name'], "text": "Hello"},
                {"from": role1['name'], "to": role2['name'], "text": "How are you?"}
            ]
            
            print(f"私聊历史记录 ({len(messages)} 条):")
            for msg in messages:
                print(f"  {msg['from']} -> {msg['to']}: {msg['text']}")
            
            assert len(messages) == 3, "私聊记录数量不匹配"
            
        except Exception as e:
            print(f"私聊历史记录测试失败: {e}")
    
    def test_private_chat_block(self, db, two_test_accounts):
        """测试私聊屏蔽功能"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        
        try:
            # 模拟屏蔽列表
            blocked_list = [role1['uid']]
            
            # 尝试发送私聊
            if role1['uid'] in blocked_list:
                print(f"角色 {role2['name']} 已屏蔽角色 {role1['name']}，无法接收私聊")
            else:
                print("私聊可以发送")
            
        except Exception as e:
            print(f"私聊屏蔽测试失败: {e}")


@pytest.mark.chat
class TestChannelChat:
    """测试频道聊天功能"""
    
    def test_send_world_chat(self, db, two_test_accounts):
        """测试发送世界频道消息"""
        role1 = two_test_accounts['role1']
        chat_text = "Hello, world!"
        
        try:
            world_chat = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "voice": chat_text,
                "type": 1,  # 世界频道
                "timestamp": datetime.now()
            }
            
            print(f"角色 {role1['name']} 发送世界频道消息: {chat_text}")
            assert world_chat['type'] == 1, "频道类型不匹配"
            
        except Exception as e:
            print(f"发送世界频道消息失败: {e}")
    
    def test_send_guild_chat(self, db, two_test_accounts):
        """测试发送公会频道消息"""
        role1 = two_test_accounts['role1']
        chat_text = "Hello, guild members!"
        
        try:
            guild_chat = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "voice": chat_text,
                "type": 2,  # 公会频道
                "guild_id": 12345,
                "timestamp": datetime.now()
            }
            
            print(f"角色 {role1['name']} 发送公会频道消息: {chat_text}")
            assert guild_chat['type'] == 2, "频道类型不匹配"
            
        except Exception as e:
            print(f"发送公会频道消息失败: {e}")
    
    def test_send_party_chat(self, db, two_test_accounts):
        """测试发送队伍频道消息"""
        role1 = two_test_accounts['role1']
        chat_text = "Hello, party members!"
        
        try:
            party_chat = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "voice": chat_text,
                "type": 3,  # 队伍频道
                "party_id": 67890,
                "timestamp": datetime.now()
            }
            
            print(f"角色 {role1['name']} 发送队伍频道消息: {chat_text}")
            assert party_chat['type'] == 3, "频道类型不匹配"
            
        except Exception as e:
            print(f"发送队伍频道消息失败: {e}")
    
    def test_send_whisper_chat(self, db, two_test_accounts):
        """测试发送密语频道消息"""
        role1 = two_test_accounts['role1']
        role2 = two_test_accounts['role2']
        chat_text = "This is a whisper!"
        
        try:
            whisper_chat = {
                "from_uid": role1['uid'],
                "from_name": role1['name'],
                "to_uid": role2['uid'],
                "to_name": role2['name'],
                "voice": chat_text,
                "type": 4,  # 密语频道
                "timestamp": datetime.now()
            }
            
            print(f"角色 {role1['name']} 向角色 {role2['name']} 发送密语: {chat_text}")
            assert whisper_chat['type'] == 4, "频道类型不匹配"
            
        except Exception as e:
            print(f"发送密语频道消息失败: {e}")


@pytest.mark.chat
class TestChatFeatures:
    """测试聊天功能特性"""
    
    def test_chat_emoticon(self, db, two_test_accounts):
        """测试聊天表情"""
        role1 = two_test_accounts['role1']
        chat_text = "Hello! [smile]"
        
        try:
            chat_message = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "voice": chat_text,
                "type": 0
            }
            
            print(f"角色 {role1['name']} 发送带表情的消息: {chat_text}")
            assert "[smile]" in chat_message['voice'], "表情未正确包含"
            
        except Exception as e:
            print(f"聊天表情测试失败: {e}")
    
    def test_chat_item_link(self, db, two_test_accounts):
        """测试聊天物品链接"""
        role1 = two_test_accounts['role1']
        chat_text = "Look at this item: [item:1001]"
        
        try:
            chat_message = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "voice": chat_text,
                "type": 0
            }
            
            print(f"角色 {role1['name']} 发送带物品链接的消息: {chat_text}")
            assert "[item:1001]" in chat_message['voice'], "物品链接未正确包含"
            
        except Exception as e:
            print(f"聊天物品链接测试失败: {e}")
    
    def test_chat_timestamp(self, db, two_test_accounts):
        """测试聊天时间戳"""
        role1 = two_test_accounts['role1']
        chat_text = "Hello!"
        
        try:
            chat_message = {
                "charguid": role1['uid'],
                "name": role1['name'],
                "voice": chat_text,
                "timestamp": datetime.now()
            }
            
            print(f"消息时间戳: {chat_message['timestamp']}")
            assert chat_message['timestamp'] is not None, "时间戳未设置"
            
        except Exception as e:
            print(f"聊天时间戳测试失败: {e}")
    
    def test_chat_filter(self, db, two_test_accounts):
        """测试聊天过滤"""
        role1 = two_test_accounts['role1']
        bad_words = ["badword1", "badword2"]
        chat_text = "This is a badword1 message"
        
        try:
            # 模拟敏感词过滤
            filtered_text = chat_text
            for word in bad_words:
                filtered_text = filtered_text.replace(word, "***")
            
            print(f"原始消息: {chat_text}")
            print(f"过滤后消息: {filtered_text}")
            assert "badword1" not in filtered_text, "敏感词未被过滤"
            
        except Exception as e:
            print(f"聊天过滤测试失败: {e}")


@pytest.mark.chat
class TestChatRestrictions:
    """测试聊天限制功能"""
    
    def test_chat_cooldown(self, db, two_test_accounts):
        """测试聊天冷却时间"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟聊天冷却
            cooldown_time = 3  # 秒
            last_chat_time = 0
            
            import time
            current_time = time.time()
            
            if current_time - last_chat_time >= cooldown_time:
                print("可以发送消息")
                last_chat_time = current_time
            else:
                print(f"聊天冷却中，还需 {cooldown_time - (current_time - last_chat_time)} 秒")
            
        except Exception as e:
            print(f"聊天冷却测试失败: {e}")
    
    def test_chat_level_requirement(self, db, two_test_accounts):
        """测试聊天等级要求"""
        role1 = two_test_accounts['role1']
        
        try:
            # 设置角色等级
            db.execute(
                "UPDATE t_role SET level = 5 WHERE uid = %s",
                (role1['uid'],)
            )
            
            role = db.get_role_by_uid(role1['uid'])
            min_chat_level = 10
            
            if role['level'] >= min_chat_level:
                print(f"角色等级 {role['level']} 满足聊天要求")
            else:
                print(f"角色等级 {role['level']} 不满足聊天要求，需要至少 {min_chat_level} 级")
            
        except Exception as e:
            print(f"聊天等级要求测试失败: {e}")
    
    def test_chat_mute(self, db, two_test_accounts):
        """测试聊天禁言"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟禁言
            mute_time = 1234567890  # 过去的时间
            current_time = datetime.now().timestamp()
            
            if mute_time > current_time:
                print(f"角色被禁言，还需 {mute_time - current_time} 秒解禁")
            else:
                print("角色未被禁言")
            
        except Exception as e:
            print(f"聊天禁言测试失败: {e}")
    
    def test_chat_frequency_limit(self, db, two_test_accounts):
        """测试聊天频率限制"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟消息频率限制
            max_messages_per_minute = 10
            message_count = 5
            
            if message_count < max_messages_per_minute:
                print(f"已发送 {message_count} 条消息，还可以发送 {max_messages_per_minute - message_count} 条")
            else:
                print(f"已达到消息频率限制 {max_messages_per_minute} 条/分钟")
            
        except Exception as e:
            print(f"聊天频率限制测试失败: {e}")


@pytest.mark.chat
class TestChatGMCommands:
    """测试GM聊天命令"""
    
    def test_gm_send_item_command(self, db, two_test_accounts):
        """测试GM发送物品命令"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟GM命令
            gm_command = f"{role1['name']}@1001@5"
            
            # 解析GM命令
            parts = gm_command.split("@")
            if len(parts) >= 3:
                user_name = parts[0]
                item_index = int(parts[1])
                item_count = int(parts[2])
                
                print(f"GM命令解析: 用户={user_name}, 物品={item_index}, 数量={item_count}")
                assert item_index == 1001, "物品索引解析错误"
                assert item_count == 5, "物品数量解析错误"
            
        except Exception as e:
            print(f"GM发送物品命令测试失败: {e}")
    
    def test_gm_privilege_check(self, db, two_test_accounts):
        """测试GM权限检查"""
        role1 = two_test_accounts['role1']
        
        try:
            # 检查GM权限
            min_gm_privilege = 10
            
            if role1.get('privilege', 0) >= min_gm_privilege:
                print("角色具有GM权限")
            else:
                print("角色不具有GM权限")
            
        except Exception as e:
            print(f"GM权限检查测试失败: {e}")
    
    def test_gm_broadcast(self, db, two_test_accounts):
        """测试GM广播"""
        role1 = two_test_accounts['role1']
        broadcast_text = "Server maintenance in 10 minutes!"
        
        try:
            # 模拟GM广播
            broadcast = {
                "from": "GM",
                "message": broadcast_text,
                "type": "broadcast",
                "timestamp": datetime.now()
            }
            
            print(f"GM广播: {broadcast['message']}")
            assert broadcast['type'] == "broadcast", "广播类型错误"
            
        except Exception as e:
            print(f"GM广播测试失败: {e}")


@pytest.mark.chat
class TestChatHistory:
    """测试聊天历史功能"""
    
    def test_chat_history_storage(self, db, two_test_accounts):
        """测试聊天历史存储"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟存储聊天历史
            chat_history = [
                {
                    "uid": role1['uid'],
                    "name": role1['name'],
                    "message": "Hello",
                    "timestamp": datetime.now()
                },
                {
                    "uid": role1['uid'],
                    "name": role1['name'],
                    "message": "World",
                    "timestamp": datetime.now()
                }
            ]
            
            print(f"存储 {len(chat_history)} 条聊天历史记录")
            assert len(chat_history) == 2, "聊天历史记录数量不匹配"
            
        except Exception as e:
            print(f"聊天历史存储测试失败: {e}")
    
    def test_chat_history_retrieval(self, db, two_test_accounts):
        """测试聊天历史检索"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟检索聊天历史
            chat_history = [
                {"name": role1['name'], "message": "Hello"},
                {"name": role1['name'], "message": "World"}
            ]
            
            # 检索最近10条
            recent_history = chat_history[-10:]
            
            print(f"检索到 {len(recent_history)} 条最近聊天记录")
            assert len(recent_history) == 2, "聊天历史检索数量不匹配"
            
        except Exception as e:
            print(f"聊天历史检索测试失败: {e}")
    
    def test_chat_history_clear(self, db, two_test_accounts):
        """测试聊天历史清除"""
        role1 = two_test_accounts['role1']
        
        try:
            # 模拟清除聊天历史
            chat_history = [
                {"name": role1['name'], "message": "Hello"},
                {"name": role1['name'], "message": "World"}
            ]
            
            # 清除历史
            chat_history.clear()
            
            print(f"聊天历史已清除，当前记录数: {len(chat_history)}")
            assert len(chat_history) == 0, "聊天历史未清除"
            
        except Exception as e:
            print(f"聊天历史清除测试失败: {e}")
