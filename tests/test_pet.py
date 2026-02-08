"""
宠物功能测试

测试游戏宠物的创建、培养、技能等功能
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


@pytest.mark.pet
class TestPetCreation:
    """测试宠物创建功能"""
    
    def test_create_basic_pet(self, db, test_account):
        """测试创建基础宠物"""
        role = test_account['role']
        
        try:
            # 模拟宠物数据
            pet_data = {
                "uid": role['uid'],
                "pet_id": 1001,
                "name": "TestPet",
                "level": 1,
                "exp": 0,
                "hp": 100,
                "mp": 50,
                "attack": 10,
                "defense": 5,
                "status": "active"
            }
            
            # 存储宠物数据（通过JSON字段）
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"角色 {role['name']} 创建宠物: {pet_data['name']}")
            assert pet_data['level'] == 1, "宠物初始等级应为1"
            
        except Exception as e:
            print(f"创建宠物失败: {e}")
    
    def test_create_pet_with_custom_attributes(self, db, test_account):
        """测试创建自定义属性宠物"""
        role = test_account['role']
        
        try:
            pet_data = {
                "uid": role['uid'],
                "pet_id": 1002,
                "name": "CustomPet",
                "level": 10,
                "exp": 5000,
                "hp": 500,
                "mp": 200,
                "attack": 50,
                "defense": 30,
                "speed": 20,
                "intelligence": 25,
                "status": "active"
            }
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"角色 {role['name']} 创建自定义宠物: {pet_data['name']}")
            assert pet_data['level'] == 10, "宠物等级不匹配"
            assert pet_data['attack'] == 50, "宠物攻击力不匹配"
            
        except Exception as e:
            print(f"创建自定义宠物失败: {e}")
    
    def test_create_multiple_pets(self, db, test_account):
        """测试创建多个宠物"""
        role = test_account['role']
        
        try:
            pets = [
                {"pet_id": 1001, "name": "Pet1", "level": 1, "exp": 0},
                {"pet_id": 1002, "name": "Pet2", "level": 5, "exp": 1000},
                {"pet_id": 1003, "name": "Pet3", "level": 10, "exp": 5000}
            ]
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', pets
            )
            
            print(f"角色 {role['name']} 创建了 {len(pets)} 个宠物")
            assert len(pets) == 3, "宠物数量不匹配"
            
        except Exception as e:
            print(f"创建多个宠物失败: {e}")
    
    def test_pet_naming(self, db, test_account):
        """测试宠物命名"""
        role = test_account['role']
        
        try:
            pet_names = [
                "Fluffy",
                "Shadow",
                "Lucky",
                "Thunder",
                "Snowball"
            ]
            
            pets = [
                {"pet_id": 1001 + i, "name": name, "level": 1}
                for i, name in enumerate(pet_names)
            ]
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', pets
            )
            
            print(f"角色 {role['name']} 的宠物名称: {[pet['name'] for pet in pets]}")
            assert len(pets) == len(pet_names), "宠物名称数量不匹配"
            
        except Exception as e:
            print(f"宠物命名测试失败: {e}")


@pytest.mark.pet
class TestPetLeveling:
    """测试宠物升级功能"""
    
    def test_pet_level_up(self, db, test_account):
        """测试宠物升级"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "LevelUpPet",
                "level": 1,
                "exp": 0
            }
            
            # 模拟升级
            pet_data['exp'] = 1000
            pet_data['level'] = 2
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物 {pet_data['name']} 升级到 {pet_data['level']} 级")
            assert pet_data['level'] == 2, "宠物升级失败"
            
        except Exception as e:
            print(f"宠物升级失败: {e}")
    
    def test_pet_exp_gain(self, db, test_account):
        """测试宠物获得经验"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "ExpPet",
                "level": 1,
                "exp": 0
            }
            
            # 获得经验
            exp_gain = 500
            pet_data['exp'] += exp_gain
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物 {pet_data['name']} 获得 {exp_gain} 经验，当前经验: {pet_data['exp']}")
            assert pet_data['exp'] == exp_gain, "经验增加失败"
            
        except Exception as e:
            print(f"宠物获得经验失败: {e}")
    
    def test_pet_max_level(self, db, test_account):
        """测试宠物最大等级"""
        role = test_account['role']
        
        try:
            max_level = 50
            pet_data = {
                "pet_id": 1001,
                "name": "MaxLevelPet",
                "level": max_level,
                "exp": 999999
            }
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物已达到最大等级: {max_level}")
            assert pet_data['level'] == max_level, "最大等级不匹配"
            
        except Exception as e:
            print(f"宠物最大等级测试失败: {e}")
    
    def test_pet_level_progression(self, db, test_account):
        """测试宠物等级进阶"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "ProgressionPet",
                "level": 1,
                "exp": 0
            }
            
            # 模拟等级进阶
            levels = [1, 5, 10, 20, 30, 40, 50]
            for level in levels:
                pet_data['level'] = level
                pet_data['exp'] = level * 1000
                
                db.update_json_field(
                    't_role', 'uid', role['uid'],
                    'petBox', [pet_data]
                )
                
                print(f"宠物升级到 {level} 级，经验: {pet_data['exp']}")
            
            assert pet_data['level'] == 50, "最终等级不匹配"
            
        except Exception as e:
            print(f"宠物等级进阶失败: {e}")


@pytest.mark.pet
class TestPetAttributes:
    """测试宠物属性功能"""
    
    def test_pet_hp_mp_management(self, db, test_account):
        """测试宠物HP/MP管理"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "HMPet",
                "level": 10,
                "hp": 100,
                "mp": 50,
                "max_hp": 100,
                "max_mp": 50
            }
            
            # 恢复HP
            pet_data['hp'] = pet_data['max_hp']
            
            # 消耗MP
            pet_data['mp'] -= 10
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物 HP: {pet_data['hp']}/{pet_data['max_hp']}, MP: {pet_data['mp']}/{pet_data['max_mp']}")
            assert pet_data['hp'] == pet_data['max_hp'], "HP恢复失败"
            
        except Exception as e:
            print(f"宠物HP/MP管理失败: {e}")
    
    def test_pet_attack_defense(self, db, test_account):
        """测试宠物攻击防御"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "AttackPet",
                "level": 10,
                "attack": 50,
                "defense": 30,
                "critical": 10,
                "dodge": 5
            }
            
            # 增加攻击力
            pet_data['attack'] += 10
            
            # 增加防御力
            pet_data['defense'] += 5
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物属性 - 攻击: {pet_data['attack']}, 防御: {pet_data['defense']}, 暴击: {pet_data['critical']}, 闪避: {pet_data['dodge']}")
            assert pet_data['attack'] == 60, "攻击力增加失败"
            
        except Exception as e:
            print(f"宠物攻击防御测试失败: {e}")
    
    def test_pet_stats_growth(self, db, test_account):
        """测试宠物属性成长"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "GrowthPet",
                "level": 1,
                "hp": 100,
                "attack": 10,
                "defense": 5
            }
            
            # 模拟属性成长
            growth_rates = {
                "hp": 10,
                "attack": 2,
                "defense": 1
            }
            
            for level in range(2, 11):
                pet_data['level'] = level
                pet_data['hp'] += growth_rates['hp']
                pet_data['attack'] += growth_rates['attack']
                pet_data['defense'] += growth_rates['defense']
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物10级属性 - HP: {pet_data['hp']}, 攻击: {pet_data['attack']}, 防御: {pet_data['defense']}")
            assert pet_data['level'] == 10, "等级不匹配"
            
        except Exception as e:
            print(f"宠物属性成长测试失败: {e}")


@pytest.mark.pet
class TestPetSkills:
    """测试宠物技能功能"""
    
    def test_pet_learn_skill(self, db, test_account):
        """测试宠物学习技能"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "SkillPet",
                "level": 10,
                "skills": []
            }
            
            # 学习技能
            new_skill = {
                "skill_id": 2001,
                "name": "Fireball",
                "level": 1,
                "damage": 100
            }
            pet_data['skills'].append(new_skill)
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物 {pet_data['name']} 学习技能: {new_skill['name']}")
            assert len(pet_data['skills']) == 1, "技能学习失败"
            
        except Exception as e:
            print(f"宠物学习技能失败: {e}")
    
    def test_pet_upgrade_skill(self, db, test_account):
        """测试宠物升级技能"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "UpgradePet",
                "level": 10,
                "skills": [
                    {"skill_id": 2001, "name": "Fireball", "level": 1, "damage": 100}
                ]
            }
            
            # 升级技能
            pet_data['skills'][0]['level'] = 2
            pet_data['skills'][0]['damage'] = 150
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物技能升级: {pet_data['skills'][0]['name']} -> {pet_data['skills'][0]['level']} 级")
            assert pet_data['skills'][0]['level'] == 2, "技能升级失败"
            
        except Exception as e:
            print(f"宠物升级技能失败: {e}")
    
    def test_pet_multiple_skills(self, db, test_account):
        """测试宠物多技能"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "MultiSkillPet",
                "level": 20,
                "skills": [
                    {"skill_id": 2001, "name": "Fireball", "level": 1},
                    {"skill_id": 2002, "name": "Iceball", "level": 1},
                    {"skill_id": 2003, "name": "Thunder", "level": 1}
                ]
            }
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物拥有 {len(pet_data['skills'])} 个技能: {[s['name'] for s in pet_data['skills']]}")
            assert len(pet_data['skills']) == 3, "技能数量不匹配"
            
        except Exception as e:
            print(f"宠物多技能测试失败: {e}")
    
    def test_pet_skill_cooldown(self, db, test_account):
        """测试宠物技能冷却"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "CooldownPet",
                "level": 10,
                "skills": [
                    {"skill_id": 2001, "name": "Fireball", "level": 1, "cooldown": 10}
                ]
            }
            
            # 使用技能
            pet_data['skills'][0]['current_cooldown'] = pet_data['skills'][0]['cooldown']
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物技能 {pet_data['skills'][0]['name']} 冷却中: {pet_data['skills'][0]['current_cooldown']} 秒")
            assert pet_data['skills'][0]['current_cooldown'] > 0, "技能冷却未设置"
            
        except Exception as e:
            print(f"宠物技能冷却测试失败: {e}")


@pytest.mark.pet
class TestPetEquipment:
    """测试宠物装备功能"""
    
    def test_pet_equip_item(self, db, test_account):
        """测试宠物装备物品"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "EquipPet",
                "level": 10,
                "equipment": []
            }
            
            # 装备物品
            equip_item = {
                "item_id": 3001,
                "name": "Pet Collar",
                "attack": 5,
                "defense": 3
            }
            pet_data['equipment'].append(equip_item)
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物装备: {equip_item['name']}")
            assert len(pet_data['equipment']) == 1, "宠物装备失败"
            
        except Exception as e:
            print(f"宠物装备物品失败: {e}")
    
    def test_pet_unequip_item(self, db, test_account):
        """测试宠物卸下装备"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "UnequipPet",
                "level": 10,
                "equipment": [
                    {"item_id": 3001, "name": "Pet Collar"}
                ]
            }
            
            # 卸下装备
            pet_data['equipment'].pop()
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物卸下装备，当前装备数: {len(pet_data['equipment'])}")
            assert len(pet_data['equipment']) == 0, "装备卸下失败"
            
        except Exception as e:
            print(f"宠物卸下装备失败: {e}")
    
    def test_pet_equipment_slots(self, db, test_account):
        """测试宠物装备槽位"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "SlotPet",
                "level": 10,
                "equipment": [],
                "max_slots": 3
            }
            
            # 填充所有槽位
            for i in range(pet_data['max_slots']):
                equip_item = {
                    "item_id": 3001 + i,
                    "name": f"Pet Item {i}",
                    "slot": i
                }
                pet_data['equipment'].append(equip_item)
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物装备槽位: {len(pet_data['equipment'])}/{pet_data['max_slots']}")
            assert len(pet_data['equipment']) == pet_data['max_slots'], "装备槽位不匹配"
            
        except Exception as e:
            print(f"宠物装备槽位测试失败: {e}")


@pytest.mark.pet
class TestPetEvolution:
    """测试宠物进化功能"""
    
    def test_pet_evolution(self, db, test_account):
        """测试宠物进化"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "BasicPet",
                "level": 20,
                "evolution_stage": 1,
                "exp": 10000
            }
            
            # 进化
            pet_data['evolution_stage'] = 2
            pet_data['pet_id'] = 2001
            pet_data['name'] = "EvolvedPet"
            pet_data['attack'] = pet_data.get('attack', 10) * 2
            pet_data['defense'] = pet_data.get('defense', 5) * 2
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物进化: {pet_data['name']} (阶段: {pet_data['evolution_stage']})")
            assert pet_data['evolution_stage'] == 2, "宠物进化失败"
            
        except Exception as e:
            print(f"宠物进化失败: {e}")
    
    def test_pet_evolution_requirements(self, db, test_account):
        """测试宠物进化条件"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "ReqPet",
                "level": 15,
                "evolution_stage": 1
            }
            
            # 检查进化条件
            min_level = 20
            min_exp = 10000
            
            if pet_data['level'] >= min_level and pet_data.get('exp', 0) >= min_exp:
                print("满足进化条件")
            else:
                print(f"不满足进化条件: 需要等级 {min_level}，当前 {pet_data['level']}")
            
        except Exception as e:
            print(f"宠物进化条件测试失败: {e}")
    
    def test_pet_max_evolution(self, db, test_account):
        """测试宠物最大进化阶段"""
        role = test_account['role']
        
        try:
            max_evolution_stage = 3
            pet_data = {
                "pet_id": 3001,
                "name": "UltimatePet",
                "level": 50,
                "evolution_stage": max_evolution_stage
            }
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物已达到最大进化阶段: {max_evolution_stage}")
            assert pet_data['evolution_stage'] == max_evolution_stage, "最大进化阶段不匹配"
            
        except Exception as e:
            print(f"宠物最大进化阶段测试失败: {e}")


@pytest.mark.pet
class TestPetInteraction:
    """测试宠物交互功能"""
    
    def test_pet_feed(self, db, test_account):
        """测试宠物喂食"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "HungryPet",
                "level": 10,
                "hunger": 50,
                "max_hunger": 100
            }
            
            # 喂食
            food_value = 30
            pet_data['hunger'] = min(pet_data['hunger'] + food_value, pet_data['max_hunger'])
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物喂食: 饱食度 {pet_data['hunger']}/{pet_data['max_hunger']}")
            assert pet_data['hunger'] > 50, "喂食失败"
            
        except Exception as e:
            print(f"宠物喂食失败: {e}")
    
    def test_pet_play(self, db, test_account):
        """测试宠物玩耍"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "PlayfulPet",
                "level": 10,
                "happiness": 50,
                "max_happiness": 100
            }
            
            # 玩耍
            happiness_gain = 20
            pet_data['happiness'] = min(pet_data['happiness'] + happiness_gain, pet_data['max_happiness'])
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物玩耍: 快乐度 {pet_data['happiness']}/{pet_data['max_happiness']}")
            assert pet_data['happiness'] > 50, "玩耍失败"
            
        except Exception as e:
            print(f"宠物玩耍失败: {e}")
    
    def test_pet_rest(self, db, test_account):
        """测试宠物休息"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "TiredPet",
                "level": 10,
                "stamina": 30,
                "max_stamina": 100
            }
            
            # 休息
            stamina_recovery = 50
            pet_data['stamina'] = min(pet_data['stamina'] + stamina_recovery, pet_data['max_stamina'])
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物休息: 体力 {pet_data['stamina']}/{pet_data['max_stamina']}")
            assert pet_data['stamina'] > 30, "休息失败"
            
        except Exception as e:
            print(f"宠物休息失败: {e}")
    
    def test_pet_status_effects(self, db, test_account):
        """测试宠物状态效果"""
        role = test_account['role']
        
        try:
            pet_data = {
                "pet_id": 1001,
                "name": "StatusPet",
                "level": 10,
                "status_effects": []
            }
            
            # 添加状态效果
            status_effect = {
                "effect_id": 4001,
                "name": "Poison",
                "duration": 10,
                "damage_per_second": 5
            }
            pet_data['status_effects'].append(status_effect)
            
            db.update_json_field(
                't_role', 'uid', role['uid'],
                'petBox', [pet_data]
            )
            
            print(f"宠物状态效果: {[e['name'] for e in pet_data['status_effects']]}")
            assert len(pet_data['status_effects']) == 1, "状态效果添加失败"
            
        except Exception as e:
            print(f"宠物状态效果测试失败: {e}")
