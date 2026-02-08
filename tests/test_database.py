"""
数据库模拟环境测试用例

使用 pytest 框架验证数据库模拟环境的正确性
"""

import pytest
import mysql.connector
from mysql.connector import Error
import json
from datetime import datetime


class DatabaseTestConfig:
    """数据库测试配置"""
    HOST = "127.0.0.1"
    PORT = 3306
    USER = "root"
    PASSWORD = "123456"
    DATABASES = ["game", "login", "game_kuafu"]


def get_connection(database="game"):
    """获取数据库连接"""
    try:
        connection = mysql.connector.connect(
            host=DatabaseTestConfig.HOST,
            port=DatabaseTestConfig.PORT,
            user=DatabaseTestConfig.USER,
            password=DatabaseTestConfig.PASSWORD,
            database=database
        )
        return connection
    except Error as e:
        pytest.fail(f"数据库连接失败: {e}")


class TestDatabaseConnection:
    """测试数据库连接"""

    def test_can_connect_to_mysql(self):
        """测试能否连接到 MySQL"""
        connection = None
        try:
            connection = mysql.connector.connect(
                host=DatabaseTestConfig.HOST,
                port=DatabaseTestConfig.PORT,
                user=DatabaseTestConfig.USER,
                password=DatabaseTestConfig.PASSWORD
            )
            assert connection.is_connected(), "数据库连接失败"
        finally:
            if connection and connection.is_connected():
                connection.close()

    def test_all_databases_exist(self):
        """测试所有数据库是否存在"""
        connection = get_connection()
        cursor = connection.cursor()
        cursor.execute("SHOW DATABASES")
        databases = [db[0] for db in cursor.fetchall()]
        
        for db in DatabaseTestConfig.DATABASES:
            assert db in databases, f"数据库 {db} 不存在"
        
        cursor.close()
        connection.close()


class TestTableStructure:
    """测试表结构"""

    def test_t_account_table_exists(self):
        """测试 t_account 表是否存在"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("SHOW TABLES LIKE 't_account'")
        result = cursor.fetchone()
        assert result is not None, "t_account 表不存在"
        cursor.close()
        connection.close()

    def test_t_role_table_exists(self):
        """测试 t_role 表是否存在"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("SHOW TABLES LIKE 't_role'")
        result = cursor.fetchone()
        assert result is not None, "t_role 表不存在"
        cursor.close()
        connection.close()

    def test_t_paydata_table_exists(self):
        """测试 t_paydata 表是否存在"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("SHOW TABLES LIKE 't_paydata'")
        result = cursor.fetchone()
        assert result is not None, "t_paydata 表不存在"
        cursor.close()
        connection.close()

    def test_t_charge_table_exists(self):
        """测试 t_charge 表是否存在"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("SHOW TABLES LIKE 't_charge'")
        result = cursor.fetchone()
        assert result is not None, "t_charge 表不存在"
        cursor.close()
        connection.close()

    def test_t_notice_table_exists(self):
        """测试 t_notice 表是否存在"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("SHOW TABLES LIKE 't_notice'")
        result = cursor.fetchone()
        assert result is not None, "t_notice 表不存在"
        cursor.close()
        connection.close()

    def test_t_account_columns(self):
        """测试 t_account 表的列结构"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("DESCRIBE t_account")
        columns = [col[0] for col in cursor.fetchall()]
        
        required_columns = [
            "id", "accountkey", "accumulatecera", "userID", "passwd",
            "roleMaxCount", "isStop", "privilege", "score", "channelNo",
            "createTime", "zhanlingexp", "moneyBox", "epicPieceBox",
            "mailBox", "storageline", "accShopInfoBox", "adventureReapInfo",
            "adventureUnionInfo", "adStorageBox", "advBookBox",
            "advUnionSubInfoBox", "activityBox", "lastLoginTime"
        ]
        
        for col in required_columns:
            assert col in columns, f"t_account 表缺少列: {col}"
        
        cursor.close()
        connection.close()

    def test_t_role_columns(self):
        """测试 t_role 表的列结构"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("DESCRIBE t_role")
        columns = [col[0] for col in cursor.fetchall()]
        
        required_columns = [
            "roleId", "uid", "lastlogout", "growtype", "secgrowtype",
            "job", "level", "name", "fatigue", "equipscore",
            "characterframe", "money", "rescoin", "contributioncoin",
            "magiccrystal", "highmagiccrystal", "cerascore", "pkcoin",
            "friendpoint", "smallcoin", "avatarVisibleFlags", "deletionstatus",
            "deletiontime", "createtime", "changename", "openid", "exp",
            "sp", "tp", "addsp", "addtp", "day", "score", "qindex",
            "distName", "servername", "updateTime", "lockTime", "pos",
            "storageline", "wordTime", "weaponIndex", "expratio",
            "fatigueratio", "adventurename", "serverSimpleDataBox",
            "friendBox", "titleBox", "avatarBox", "emblemBox", "cardBox",
            "creatureBox", "artifactBox", "equipBox", "equippedBox",
            "materialBox", "consumableBox", "roleShopInfoBox", "crackEquipBox",
            "crackBox", "damageBox", "chatFrameBox", "charFrameBox",
            "sdAvatarBox", "bookmarkBox", "scrollBox", "moneyBox",
            "ceraShopBuyInfo", "tutoBox", "skillBox", "skillslotBox",
            "dungeonTicketsBox", "tonicBox", "mailBox", "sysMailBox",
            "charStorageBox", "rePurStoItem", "towerInfoBox",
            "creatureErrandBox", "localRewardBox", "questInfoBox",
            "sysBuffBox", "clearDungeonBox", "achievementBox", "collectionBox",
            "noteMsgBox", "essenceBox", "auctionBox"
        ]
        
        for col in required_columns:
            assert col in columns, f"t_role 表缺少列: {col}"
        
        cursor.close()
        connection.close()

    def test_t_account_primary_key(self):
        """测试 t_account 表的主键"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("""
            SELECT COLUMN_NAME
            FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
            WHERE TABLE_SCHEMA = 'game'
            AND TABLE_NAME = 't_account'
            AND CONSTRAINT_NAME = 'PRIMARY'
        """)
        result = cursor.fetchone()
        assert result is not None, "t_account 表没有主键"
        assert result[0] == "id", "t_account 表的主键应该是 id"
        cursor.close()
        connection.close()

    def test_t_role_primary_key(self):
        """测试 t_role 表的主键"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("""
            SELECT COLUMN_NAME
            FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
            WHERE TABLE_SCHEMA = 'game'
            AND TABLE_NAME = 't_role'
            AND CONSTRAINT_NAME = 'PRIMARY'
        """)
        result = cursor.fetchone()
        assert result is not None, "t_role 表没有主键"
        assert result[0] == "uid", "t_role 表的主键应该是 uid"
        cursor.close()
        connection.close()

    def test_t_charge_unique_constraint(self):
        """测试 t_charge 表的唯一约束"""
        connection = get_connection("game")
        cursor = connection.cursor()
        cursor.execute("""
            SELECT CONSTRAINT_NAME
            FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
            WHERE TABLE_SCHEMA = 'game'
            AND TABLE_NAME = 't_charge'
            AND CONSTRAINT_TYPE = 'UNIQUE'
        """)
        result = cursor.fetchone()
        assert result is not None, "t_charge 表没有唯一约束"
        cursor.close()
        connection.close()


class TestDataOperations:
    """测试数据操作"""

    def test_insert_and_select_account(self):
        """测试插入和查询账户数据"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        test_id = "test_account_001"
        
        try:
            cursor.execute("""
                INSERT INTO t_account (id, userID, passwd, score, createTime)
                VALUES (%s, %s, %s, %s, %s)
            """, (test_id, "testuser", "password123", 100, datetime.now()))
            connection.commit()
            
            cursor.execute("SELECT * FROM t_account WHERE id = %s", (test_id,))
            result = cursor.fetchone()
            assert result is not None, "插入的账户数据无法查询"
            assert result[3] == "testuser", "用户名不匹配"
            
        finally:
            cursor.execute("DELETE FROM t_account WHERE id = %s", (test_id,))
            connection.commit()
            cursor.close()
            connection.close()

    def test_insert_and_select_role(self):
        """测试插入和查询角色数据"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        test_uid = 123456789
        
        try:
            cursor.execute("""
                INSERT INTO t_role (roleId, uid, name, level, job, openid)
                VALUES (%s, %s, %s, %s, %s, %s)
            """, (1, test_uid, "TestRole", 10, 1, "test_openid"))
            connection.commit()
            
            cursor.execute("SELECT * FROM t_role WHERE uid = %s", (test_uid,))
            result = cursor.fetchone()
            assert result is not None, "插入的角色数据无法查询"
            assert result[7] == "TestRole", "角色名不匹配"
            
        finally:
            cursor.execute("DELETE FROM t_role WHERE uid = %s", (test_uid,))
            connection.commit()
            cursor.close()
            connection.close()

    def test_json_field_support(self):
        """测试 JSON 字段支持"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        test_id = "test_json_001"
        test_json = {"gold": 1000, "diamond": 500}
        
        try:
            cursor.execute("""
                INSERT INTO t_account (id, userID, moneyBox)
                VALUES (%s, %s, %s)
            """, (test_id, "jsonuser", json.dumps(test_json)))
            connection.commit()
            
            cursor.execute("SELECT moneyBox FROM t_account WHERE id = %s", (test_id,))
            result = cursor.fetchone()
            assert result is not None, "JSON 字段查询失败"
            
            retrieved_json = json.loads(result[0])
            assert retrieved_json["gold"] == 1000, "JSON 数据不匹配"
            
        finally:
            cursor.execute("DELETE FROM t_account WHERE id = %s", (test_id,))
            connection.commit()
            cursor.close()
            connection.close()

    def test_notice_initial_data(self):
        """测试公告初始化数据"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        cursor.execute("SELECT * FROM t_notice WHERE id = 1")
        result = cursor.fetchone()
        
        assert result is not None, "初始化公告数据不存在"
        assert "欢迎" in result[1], "公告内容不正确"
        
        cursor.close()
        connection.close()


class TestDataIntegrity:
    """测试数据完整性"""

    def test_t_account_not_null_constraints(self):
        """测试 t_account 表的非空约束"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        cursor.execute("DESCRIBE t_account")
        columns_info = cursor.fetchall()
        
        primary_key_column = None
        for col in columns_info:
            if col[3] == "PRI":
                primary_key_column = col[0]
                break
        
        assert primary_key_column == "id", "主键列应该是 id"
        
        cursor.close()
        connection.close()

    def test_foreign_key_relationship(self):
        """测试外键关系（角色与账户）"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        test_openid = "test_fk_001"
        test_uid = 987654321
        
        try:
            cursor.execute("""
                INSERT INTO t_account (id, userID, passwd)
                VALUES (%s, %s, %s)
            """, (test_openid, "fkuser", "password"))
            
            cursor.execute("""
                INSERT INTO t_role (roleId, uid, name, level, job, openid)
                VALUES (%s, %s, %s, %s, %s, %s)
            """, (1, test_uid, "FKRole", 5, 1, test_openid))
            
            connection.commit()
            
            cursor.execute("""
                SELECT r.name, a.userID 
                FROM t_role r 
                LEFT JOIN t_account a ON r.openid = a.id 
                WHERE r.uid = %s
            """, (test_uid,))
            
            result = cursor.fetchone()
            assert result is not None, "关联查询失败"
            assert result[0] == "FKRole", "角色名不匹配"
            assert result[1] == "fkuser", "用户名不匹配"
            
        finally:
            cursor.execute("DELETE FROM t_role WHERE uid = %s", (test_uid,))
            cursor.execute("DELETE FROM t_account WHERE id = %s", (test_openid,))
            connection.commit()
            cursor.close()
            connection.close()


class TestDatabasePerformance:
    """测试数据库性能"""

    def test_query_performance(self):
        """测试查询性能"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        import time
        start_time = time.time()
        
        cursor.execute("SELECT COUNT(*) FROM t_account")
        cursor.fetchone()
        
        end_time = time.time()
        query_time = end_time - start_time
        
        assert query_time < 1.0, f"查询时间过长: {query_time}秒"
        
        cursor.close()
        connection.close()

    def test_index_usage(self):
        """测试索引使用"""
        connection = get_connection("game")
        cursor = connection.cursor()
        
        cursor.execute("""
            EXPLAIN SELECT * FROM t_role WHERE openid = 'test'
        """)
        result = cursor.fetchone()
        
        assert result is not None, "EXPLAIN 查询失败"
        
        cursor.close()
        connection.close()


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])