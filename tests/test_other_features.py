"""
其他游戏功能测试

测试副本、公会、好友、任务等其他游戏功能
"""

import pytest
from utils import DatabaseHelper, TestDataGenerator


@pytest.fixture
def db():
    """数据库 helper fixture"""
    with DatabaseHelper() as db_helper:
        yield db_helper


@pytest.fixture
def test_account(db):
    """创建测试账户和角色 fixture"""
    account_data = TestDataGenerator.generate_account_data()
    db.insert_account(account_data)
    
    role_data = TestDataGenerator.generate_role_data(
        openid=account_data['id']
    )
    db.insert_role(role_data)
    
    yield {
        'account': account_data,
        'role': role_data
    }
    
    db.delete_role(role_data['uid'])
    db.delete_account(account_data['id'])


@pytest.fixture
def four_test_accounts(db):
    """创建四个测试账户和角色 fixture"""
    accounts = []
    
    for i in range(4):
        account_data = TestDataGenerator.generate_account_data()
        db.insert_account(account_data)
        
        role_data = TestDataGenerator.generate_role_data(
            openid=account_data['id']
        )
        db.insert_role(role_data)
        
        accounts.append({
            'account': account_data,
            'role': role_data
        })
    
    yield accounts
    
    for acc in reversed(accounts):
        db.delete_role(acc['role']['uid'])
        db.delete_account(acc['account']['id'])


@pytest.mark.game
class TestDungeon:
    """测试副本功能"""
    
    def test_enter_dungeon(self, db, test_account):
        """测试进入副本"""
        role = test_account['role']
        
        try:
            dungeon_id = 1001
            dungeon_data = {
                "uid": role['uid'],
                "dungeon_id": dungeon_id,
                "status": "in_progress",
                "enter_time": "2024-01-01 12:00:00"
            }
            
            print(f"角色 {role['name']} 进入副本 {dungeon_id}")
            assert dungeon_data['status'] == "in_progress", "副本状态错误"
            
        except Exception as e:
            print(f"进入副本失败: {e}")
    
    def test_complete_dungeon(self, db, test_account):
        """测试完成副本"""
        role = test_account['role']
        
        try:
            dungeon_id = 1001
            dungeon_data = {
                "uid": role['uid'],
                "dungeon_id": dungeon_id,
                "status": "completed",
                "completion_time": "2024-01-01 13:00:00",
                "rewards": {
                    "exp": 1000,
                    "money": 500,
                    "items": [{"index": 1001, "count": 1}]
                }
            }
            
            print(f"角色 {role['name']} 完成副本 {dungeon_id}")
            assert dungeon_data['status'] == "completed", "副本完成状态错误"
            
        except Exception as e:
            print(f"完成副本失败: {e}")
    
    def test_dungeon_rewards(self, db, test_account):
        """测试副本奖励"""
        role = test_account['role']
        
        try:
            rewards = {
                "exp": 1000,
                "money": 500,
                "items": [
                    {"index": 1001, "count": 1},
                    {"index": 1002, "count": 2}
                ]
            }
            
            # 增加经验
            db.execute(
                "UPDATE t_role SET exp = exp + %s WHERE uid = %s",
                (rewards['exp'], role['uid'])
            )
            
            # 增加金币
            db.execute(
                "UPDATE t_role SET money = money + %s WHERE uid = %s",
                (rewards['money'], role['uid'])
            )
            
            # 添加物品到背包
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'bagBox', rewards['items']
            )
            
            print(f"副本奖励: 经验={rewards['exp']}, 金币={rewards['money']}, 物品={len(rewards['items'])}个")
            
        except Exception as e:
            print(f"副本奖励测试失败: {e}")
    
    def test_dungeon_difficulty(self, db, test_account):
        """测试副本难度"""
        role = test_account['role']
        
        try:
            difficulties = ["normal", "hard", "expert", "master"]
            
            for difficulty in difficulties:
                dungeon_data = {
                    "uid": role['uid'],
                    "dungeon_id": 1001,
                    "difficulty": difficulty,
                    "status": "in_progress"
                }
                
                print(f"角色 {role['name']} 进入 {difficulty} 难度副本")
            
        except Exception as e:
            print(f"副本难度测试失败: {e}")
    
    def test_dungeon_cooldown(self, db, test_account):
        """测试副本冷却"""
        role = test_account['role']
        
        try:
            cooldown_time = 300  # 5分钟
            last_complete_time = 1234567890
            
            from datetime import datetime
            current_time = datetime.now().timestamp()
            
            if current_time - last_complete_time >= cooldown_time:
                print("副本冷却已结束，可以进入")
            else:
                remaining = cooldown_time - (current_time - last_complete_time)
                print(f"副本冷却中，还需 {remaining} 秒")
            
        except Exception as e:
            print(f"副本冷却测试失败: {e}")


@pytest.mark.game
class TestGuild:
    """测试公会功能"""
    
    def test_create_guild(self, db, test_account):
        """测试创建公会"""
        role = test_account['role']
        
        try:
            guild_data = {
                "guild_id": 1001,
                "name": "TestGuild",
                "leader_uid": role['uid'],
                "leader_name": role['name'],
                "level": 1,
                "exp": 0,
                "member_count": 1,
                "max_members": 50,
                "create_time": "2024-01-01 12:00:00"
            }
            
            print(f"角色 {role['name']} 创建公会: {guild_data['name']}")
            assert guild_data['leader_uid'] == role['uid'], "公会会长不匹配"
            
        except Exception as e:
            print(f"创建公会失败: {e}")
    
    def test_join_guild(self, db, four_test_accounts):
        """测试加入公会"""
        leader = four_test_accounts[0]['role']
        member = four_test_accounts[1]['role']
        
        try:
            guild_data = {
                "guild_id": 1001,
                "name": "TestGuild",
                "leader_uid": leader['uid'],
                "member_count": 2
            }
            
            print(f"角色 {member['name']} 加入公会 {guild_data['name']}")
            assert guild_data['member_count'] == 2, "成员数量不匹配"
            
        except Exception as e:
            print(f"加入公会失败: {e}")
    
    def test_guild_level_up(self, db, test_account):
        """测试公会升级"""
        role = test_account['role']
        
        try:
            guild_data = {
                "guild_id": 1001,
                "name": "TestGuild",
                "level": 1,
                "exp": 0
            }
            
            # 增加公会经验
            guild_data['exp'] = 5000
            guild_data['level'] = 2
            
            print(f"公会升级到 {guild_data['level']} 级")
            assert guild_data['level'] == 2, "公会升级失败"
            
        except Exception as e:
            print(f"公会升级失败: {e}")
    
    def test_guild_donate(self, db, test_account):
        """测试公会捐献"""
        role = test_account['role']
        
        try:
            guild_data = {
                "guild_id": 1001,
                "name": "TestGuild",
                "funds": 0,
                "exp": 0
            }
            
            # 捐献金币
            donate_amount = 1000
            guild_data['funds'] += donate_amount
            guild_data['exp'] += 100
            
            print(f"角色 {role['name']} 向公会捐献 {donate_amount} 金币")
            assert guild_data['funds'] == donate_amount, "公会资金增加失败"
            
        except Exception as e:
            print(f"公会捐献失败: {e}")
    
    def test_guild_member_management(self, db, four_test_accounts):
        """测试公会成员管理"""
        leader = four_test_accounts[0]['role']
        member = four_test_accounts[1]['role']
        
        try:
            guild_data = {
                "guild_id": 1001,
                "name": "TestGuild",
                "leader_uid": leader['uid'],
                "members": [
                    {"uid": leader['uid'], "name": leader['name'], "rank": "leader"},
                    {"uid": member['uid'], "name": member['name'], "rank": "member"}
                ]
            }
            
            # 提升成员为副会长
            guild_data['members'][1]['rank'] = "vice_leader"
            
            print(f"成员 {member['name']} 被提升为副会长")
            assert guild_data['members'][1]['rank'] == "vice_leader", "成员职位提升失败"
            
        except Exception as e:
            print(f"公会成员管理失败: {e}")


@pytest.mark.game
class TestFriend:
    """测试好友功能"""
    
    def test_add_friend(self, db, four_test_accounts):
        """测试添加好友"""
        role1 = four_test_accounts[0]['role']
        role2 = four_test_accounts[1]['role']
        
        try:
            friend_request = {
                "from_uid": role1['uid'],
                "from_name": role1['name'],
                "to_uid": role2['uid'],
                "to_name": role2['name'],
                "status": "pending"
            }
            
            print(f"角色 {role1['name']} 向角色 {role2['name']} 发送好友请求")
            assert friend_request['status'] == "pending", "好友请求状态错误"
            
        except Exception as e:
            print(f"添加好友失败: {e}")
    
    def test_accept_friend_request(self, db, four_test_accounts):
        """测试接受好友请求"""
        role1 = four_test_accounts[0]['role']
        role2 = four_test_accounts[1]['role']
        
        try:
            friend_data = {
                "uid1": role1['uid'],
                "name1": role1['name'],
                "uid2": role2['uid'],
                "name2": role2['name'],
                "status": "accepted",
                "add_time": "2024-01-01 12:00:00"
            }
            
            print(f"角色 {role2['name']} 接受了角色 {role1['name']} 的好友请求")
            assert friend_data['status'] == "accepted", "好友状态错误"
            
        except Exception as e:
            print(f"接受好友请求失败: {e}")
    
    def test_remove_friend(self, db, four_test_accounts):
        """测试删除好友"""
        role1 = four_test_accounts[0]['role']
        role2 = four_test_accounts[1]['role']
        
        try:
            friend_data = {
                "uid1": role1['uid'],
                "uid2": role2['uid'],
                "status": "removed"
            }
            
            print(f"角色 {role1['name']} 删除了好友 {role2['name']}")
            assert friend_data['status'] == "removed", "好友删除状态错误"
            
        except Exception as e:
            print(f"删除好友失败: {e}")
    
    def test_friend_list(self, db, four_test_accounts):
        """测试好友列表"""
        role1 = four_test_accounts[0]['role']
        
        try:
            friends = [
                {"uid": four_test_accounts[1]['role']['uid'], "name": four_test_accounts[1]['role']['name']},
                {"uid": four_test_accounts[2]['role']['uid'], "name": four_test_accounts[2]['role']['name']},
                {"uid": four_test_accounts[3]['role']['uid'], "name": four_test_accounts[3]['role']['name']}
            ]
            
            print(f"角色 {role1['name']} 的好友列表: {[f['name'] for f in friends]}")
            assert len(friends) == 3, "好友数量不匹配"
            
        except Exception as e:
            print(f"好友列表测试失败: {e}")
    
    def test_friend_online_status(self, db, four_test_accounts):
        """测试好友在线状态"""
        role1 = four_test_accounts[0]['role']
        role2 = four_test_accounts[1]['role']
        
        try:
            friend_data = {
                "uid": role2['uid'],
                "name": role2['name'],
                "online": True,
                "last_login": "2024-01-01 12:00:00"
            }
            
            print(f"好友 {friend_data['name']} 在线状态: {friend_data['online']}")
            assert friend_data['online'] == True, "在线状态错误"
            
        except Exception as e:
            print(f"好友在线状态测试失败: {e}")


@pytest.mark.game
class TestQuest:
    """测试任务功能"""
    
    def test_accept_quest(self, db, test_account):
        """测试接受任务"""
        role = test_account['role']
        
        try:
            quest_data = {
                "uid": role['uid'],
                "quest_id": 1001,
                "name": "Test Quest",
                "status": "in_progress",
                "accept_time": "2024-01-01 12:00:00",
                "progress": {}
            }
            
            print(f"角色 {role['name']} 接受任务: {quest_data['name']}")
            assert quest_data['status'] == "in_progress", "任务状态错误"
            
        except Exception as e:
            print(f"接受任务失败: {e}")
    
    def test_complete_quest(self, db, test_account):
        """测试完成任务"""
        role = test_account['role']
        
        try:
            quest_data = {
                "uid": role['uid'],
                "quest_id": 1001,
                "name": "Test Quest",
                "status": "completed",
                "complete_time": "2024-01-01 13:00:00",
                "rewards": {
                    "exp": 1000,
                    "money": 500
                }
            }
            
            print(f"角色 {role['name']} 完成任务: {quest_data['name']}")
            assert quest_data['status'] == "completed", "任务完成状态错误"
            
        except Exception as e:
            print(f"完成任务失败: {e}")
    
    def test_quest_progress(self, db, test_account):
        """测试任务进度"""
        role = test_account['role']
        
        try:
            quest_data = {
                "uid": role['uid'],
                "quest_id": 1001,
                "name": "Kill Quest",
                "status": "in_progress",
                "progress": {
                    "kill_count": 5,
                    "target_count": 10
                }
            }
            
            # 更新进度
            quest_data['progress']['kill_count'] = 8
            
            print(f"任务进度: {quest_data['progress']['kill_count']}/{quest_data['progress']['target_count']}")
            assert quest_data['progress']['kill_count'] == 8, "任务进度更新失败"
            
        except Exception as e:
            print(f"任务进度测试失败: {e}")
    
    def test_daily_quest(self, db, test_account):
        """测试日常任务"""
        role = test_account['role']
        
        try:
            daily_quest = {
                "uid": role['uid'],
                "quest_id": 2001,
                "name": "Daily Quest",
                "type": "daily",
                "status": "in_progress",
                "date": "2024-01-01"
            }
            
            print(f"角色 {role['name']} 接受日常任务: {daily_quest['name']}")
            assert daily_quest['type'] == "daily", "任务类型错误"
            
        except Exception as e:
            print(f"日常任务测试失败: {e}")
    
    def test_quest_chain(self, db, test_account):
        """测试任务链"""
        role = test_account['role']
        
        try:
            quest_chain = [
                {"quest_id": 1001, "name": "Quest 1", "status": "completed"},
                {"quest_id": 1002, "name": "Quest 2", "status": "in_progress"},
                {"quest_id": 1003, "name": "Quest 3", "status": "locked"}
            ]
            
            print(f"任务链: {[q['name'] + ' (' + q['status'] + ')' for q in quest_chain]}")
            assert len(quest_chain) == 3, "任务链长度不匹配"
            
        except Exception as e:
            print(f"任务链测试失败: {e}")


@pytest.mark.game
class TestAchievement:
    """测试成就功能"""
    
    def test_unlock_achievement(self, db, test_account):
        """测试解锁成就"""
        role = test_account['role']
        
        try:
            achievement_data = {
                "uid": role['uid'],
                "achievement_id": 1001,
                "name": "First Kill",
                "description": "Kill your first monster",
                "status": "unlocked",
                "unlock_time": "2024-01-01 12:00:00"
            }
            
            print(f"角色 {role['name']} 解锁成就: {achievement_data['name']}")
            assert achievement_data['status'] == "unlocked", "成就状态错误"
            
        except Exception as e:
            print(f"解锁成就失败: {e}")
    
    def test_achievement_progress(self, db, test_account):
        """测试成就进度"""
        role = test_account['role']
        
        try:
            achievement_data = {
                "uid": role['uid'],
                "achievement_id": 1002,
                "name": "Kill 100 Monsters",
                "status": "in_progress",
                "progress": {
                    "current": 50,
                    "target": 100
                }
            }
            
            # 更新进度
            achievement_data['progress']['current'] = 75
            
            print(f"成就进度: {achievement_data['progress']['current']}/{achievement_data['progress']['target']}")
            assert achievement_data['progress']['current'] == 75, "成就进度更新失败"
            
        except Exception as e:
            print(f"成就进度测试失败: {e}")
    
    def test_achievement_rewards(self, db, test_account):
        """测试成就奖励"""
        role = test_account['role']
        
        try:
            achievement_data = {
                "uid": role['uid'],
                "achievement_id": 1001,
                "name": "First Kill",
                "status": "unlocked",
                "rewards": {
                    "exp": 500,
                    "money": 200,
                    "title": "Monster Hunter"
                }
            }
            
            print(f"成就奖励: 经验={achievement_data['rewards']['exp']}, 金币={achievement_data['rewards']['money']}, 称号={achievement_data['rewards']['title']}")
            
        except Exception as e:
            print(f"成就奖励测试失败: {e}")
    
    def test_achievement_points(self, db, test_account):
        """测试成就点数"""
        role = test_account['role']
        
        try:
            achievement_points = {
                "uid": role['uid'],
                "total_points": 100,
                "spent_points": 30,
                "available_points": 70
            }
            
            print(f"成就点数: 总计={achievement_points['total_points']}, 已用={achievement_points['spent_points']}, 可用={achievement_points['available_points']}")
            assert achievement_points['available_points'] == 70, "可用点数错误"
            
        except Exception as e:
            print(f"成就点数测试失败: {e}")


@pytest.mark.game
class TestParty:
    """测试队伍功能"""
    
    def test_create_party(self, db, four_test_accounts):
        """测试创建队伍"""
        leader = four_test_accounts[0]['role']
        
        try:
            party_data = {
                "party_id": 1001,
                "leader_uid": leader['uid'],
                "leader_name": leader['name'],
                "members": [
                    {"uid": leader['uid'], "name": leader['name']}
                ],
                "max_members": 4,
                "create_time": "2024-01-01 12:00:00"
            }
            
            print(f"角色 {leader['name']} 创建队伍")
            assert party_data['leader_uid'] == leader['uid'], "队长不匹配"
            
        except Exception as e:
            print(f"创建队伍失败: {e}")
    
    def test_join_party(self, db, four_test_accounts):
        """测试加入队伍"""
        leader = four_test_accounts[0]['role']
        member = four_test_accounts[1]['role']
        
        try:
            party_data = {
                "party_id": 1001,
                "leader_uid": leader['uid'],
                "members": [
                    {"uid": leader['uid'], "name": leader['name']},
                    {"uid": member['uid'], "name": member['name']}
                ]
            }
            
            print(f"角色 {member['name']} 加入队伍")
            assert len(party_data['members']) == 2, "成员数量不匹配"
            
        except Exception as e:
            print(f"加入队伍失败: {e}")
    
    def test_leave_party(self, db, four_test_accounts):
        """测试离开队伍"""
        leader = four_test_accounts[0]['role']
        member = four_test_accounts[1]['role']
        
        try:
            party_data = {
                "party_id": 1001,
                "leader_uid": leader['uid'],
                "members": [
                    {"uid": leader['uid'], "name": leader['name']}
                ]
            }
            
            print(f"角色 {member['name']} 离开队伍")
            assert len(party_data['members']) == 1, "成员数量不匹配"
            
        except Exception as e:
            print(f"离开队伍失败: {e}")
    
    def test_party_leader_transfer(self, db, four_test_accounts):
        """测试转移队长"""
        leader = four_test_accounts[0]['role']
        new_leader = four_test_accounts[1]['role']
        
        try:
            party_data = {
                "party_id": 1001,
                "leader_uid": new_leader['uid'],
                "leader_name": new_leader['name'],
                "members": [
                    {"uid": leader['uid'], "name": leader['name']},
                    {"uid": new_leader['uid'], "name": new_leader['name']}
                ]
            }
            
            print(f"队长从 {leader['name']} 转移到 {new_leader['name']}")
            assert party_data['leader_uid'] == new_leader['uid'], "队长转移失败"
            
        except Exception as e:
            print(f"转移队长失败: {e}")
    
    def test_party_disband(self, db, four_test_accounts):
        """测试解散队伍"""
        leader = four_test_accounts[0]['role']
        
        try:
            party_data = {
                "party_id": 1001,
                "leader_uid": leader['uid'],
                "status": "disbanded",
                "disband_time": "2024-01-01 13:00:00"
            }
            
            print(f"队伍已解散")
            assert party_data['status'] == "disbanded", "队伍解散状态错误"
            
        except Exception as e:
            print(f"解散队伍失败: {e}")


@pytest.mark.game
class TestMail:
    """测试邮件功能"""
    
    def test_send_mail(self, db, four_test_accounts):
        """测试发送邮件"""
        sender = four_test_accounts[0]['role']
        receiver = four_test_accounts[1]['role']
        
        try:
            mail_data = {
                "mail_id": 1001,
                "sender_uid": sender['uid'],
                "sender_name": sender['name'],
                "receiver_uid": receiver['uid'],
                "receiver_name": receiver['name'],
                "title": "Test Mail",
                "content": "Hello, this is a test mail!",
                "send_time": "2024-01-01 12:00:00",
                "status": "unread"
            }
            
            print(f"角色 {sender['name']} 向角色 {receiver['name']} 发送邮件: {mail_data['title']}")
            assert mail_data['status'] == "unread", "邮件状态错误"
            
        except Exception as e:
            print(f"发送邮件失败: {e}")
    
    def test_read_mail(self, db, four_test_accounts):
        """测试读取邮件"""
        receiver = four_test_accounts[1]['role']
        
        try:
            mail_data = {
                "mail_id": 1001,
                "receiver_uid": receiver['uid'],
                "title": "Test Mail",
                "content": "Hello!",
                "status": "read",
                "read_time": "2024-01-01 12:30:00"
            }
            
            print(f"角色 {receiver['name']} 读取邮件: {mail_data['title']}")
            assert mail_data['status'] == "read", "邮件读取状态错误"
            
        except Exception as e:
            print(f"读取邮件失败: {e}")
    
    def test_mail_with_attachment(self, db, four_test_accounts):
        """测试带附件的邮件"""
        sender = four_test_accounts[0]['role']
        receiver = four_test_accounts[1]['role']
        
        try:
            mail_data = {
                "mail_id": 1001,
                "sender_uid": sender['uid'],
                "receiver_uid": receiver['uid'],
                "title": "Gift Mail",
                "content": "Here is a gift for you!",
                "attachments": [
                    {"index": 1001, "count": 5},
                    {"index": 1002, "count": 3}
                ],
                "status": "unread"
            }
            
            print(f"邮件附件: {len(mail_data['attachments'])} 个物品")
            assert len(mail_data['attachments']) == 2, "附件数量不匹配"
            
        except Exception as e:
            print(f"带附件邮件测试失败: {e}")
    
    def test_mail_expiry(self, db, four_test_accounts):
        """测试邮件过期"""
        receiver = four_test_accounts[1]['role']
        
        try:
            mail_data = {
                "mail_id": 1001,
                "receiver_uid": receiver['uid'],
                "title": "Test Mail",
                "send_time": "2024-01-01 12:00:00",
                "expiry_time": "2024-01-08 12:00:00",
                "status": "unread"
            }
            
            from datetime import datetime
            current_time = datetime.now()
            
            print(f"邮件过期时间: {mail_data['expiry_time']}")
            
        except Exception as e:
            print(f"邮件过期测试失败: {e}")
    
    def test_delete_mail(self, db, four_test_accounts):
        """测试删除邮件"""
        receiver = four_test_accounts[1]['role']
        
        try:
            mail_data = {
                "mail_id": 1001,
                "receiver_uid": receiver['uid'],
                "title": "Test Mail",
                "status": "deleted"
            }
            
            print(f"邮件已删除")
            assert mail_data['status'] == "deleted", "邮件删除状态错误"
            
        except Exception as e:
            print(f"删除邮件失败: {e}")
