"""
数据库操作工具类

封装常用的数据库操作，简化测试代码
"""

import mysql.connector
from mysql.connector import Error
from typing import Dict, List, Any, Optional
import json


class DatabaseHelper:
    """数据库操作辅助类"""
    
    def __init__(self, host: str = "127.0.0.1", port: int = 3306, 
                 user: str = "root", password: str = "123456", 
                 database: str = "game"):
        self.host = host
        self.port = port
        self.user = user
        self.password = password
        self.database = database
        self._connection = None
        self._cursor = None
    
    def connect(self) -> 'DatabaseHelper':
        """建立数据库连接"""
        try:
            self._connection = mysql.connector.connect(
                host=self.host,
                port=self.port,
                user=self.user,
                password=self.password,
                database=self.database
            )
            self._cursor = self._connection.cursor(dictionary=True)
            return self
        except Error as e:
            raise ConnectionError(f"数据库连接失败: {e}")
    
    def close(self):
        """关闭数据库连接"""
        if self._cursor:
            self._cursor.close()
        if self._connection:
            self._connection.close()
    
    def execute(self, sql: str, params: tuple = None) -> int:
        """执行 SQL 语句"""
        try:
            if params:
                self._cursor.execute(sql, params)
            else:
                self._cursor.execute(sql)
            self._connection.commit()
            return self._cursor.rowcount
        except Error as e:
            self._connection.rollback()
            raise RuntimeError(f"SQL 执行失败: {e}")
    
    def fetch_one(self, sql: str, params: tuple = None) -> Optional[Dict[str, Any]]:
        """查询单条记录"""
        try:
            if params:
                self._cursor.execute(sql, params)
            else:
                self._cursor.execute(sql)
            return self._cursor.fetchone()
        except Error as e:
            raise RuntimeError(f"查询失败: {e}")
    
    def fetch_all(self, sql: str, params: tuple = None) -> List[Dict[str, Any]]:
        """查询多条记录"""
        try:
            if params:
                self._cursor.execute(sql, params)
            else:
                self._cursor.execute(sql)
            return self._cursor.fetchall()
        except Error as e:
            raise RuntimeError(f"查询失败: {e}")
    
    def insert_account(self, account_data: Dict[str, Any]) -> bool:
        """插入账户数据"""
        sql = """
            INSERT INTO t_account (id, userID, passwd, score, roleMaxCount, privilege, createTime)
            VALUES (%s, %s, %s, %s, %s, %s, NOW())
        """
        try:
            score = account_data.get('score', 0)
            if score is None:
                score = 0
            
            roleMaxCount = account_data.get('roleMaxCount', 3)
            if roleMaxCount is None:
                roleMaxCount = 3
            
            privilege = account_data.get('privilege', 0)
            if privilege is None:
                privilege = 0
            
            self._cursor.execute(sql, (
                account_data.get('id'),
                account_data.get('userID'),
                account_data.get('passwd'),
                score,
                roleMaxCount,
                privilege
            ))
            self._connection.commit()
            return True
        except Error as e:
            self._connection.rollback()
            print(f"插入账户失败: {e}")
            return False
    
    def insert_role(self, role_data: Dict[str, Any]) -> bool:
        """插入角色数据"""
        sql = """
            INSERT INTO t_role (roleId, uid, name, level, job, openid, exp, createtime)
            VALUES (%s, %s, %s, %s, %s, %s, %s, NOW())
        """
        try:
            self._cursor.execute(sql, (
                role_data.get('roleId'),
                role_data.get('uid'),
                role_data.get('name'),
                role_data.get('level', 1),
                role_data.get('job'),
                role_data.get('openid'),
                role_data.get('exp', 200)
            ))
            self._connection.commit()
            return True
        except Error as e:
            self._connection.rollback()
            print(f"插入角色失败: {e}")
            return False
    
    def delete_account(self, account_id: str) -> bool:
        """删除账户"""
        try:
            self._cursor.execute("DELETE FROM t_account WHERE id = %s", (account_id,))
            self._connection.commit()
            return True
        except Error as e:
            self._connection.rollback()
            return False
    
    def delete_role(self, uid: int) -> bool:
        """删除角色"""
        try:
            self._cursor.execute("DELETE FROM t_role WHERE uid = %s", (uid,))
            self._connection.commit()
            return True
        except Error as e:
            self._connection.rollback()
            return False
    
    def get_account_by_id(self, account_id: str) -> Optional[Dict[str, Any]]:
        """根据 ID 获取账户"""
        return self.fetch_one("SELECT * FROM t_account WHERE id = %s", (account_id,))
    
    def get_role_by_uid(self, uid: int) -> Optional[Dict[str, Any]]:
        """根据 UID 获取角色"""
        return self.fetch_one("SELECT * FROM t_role WHERE uid = %s", (uid,))
    
    def update_json_field(self, table: str, id_field: str, id_value: Any, 
                          json_field: str, json_data: Dict) -> bool:
        """更新 JSON 字段"""
        sql = f"UPDATE {table} SET {json_field} = %s WHERE {id_field} = %s"
        try:
            self._cursor.execute(sql, (json.dumps(json_data), id_value))
            self._connection.commit()
            return True
        except Error as e:
            self._connection.rollback()
            return False
    
    def __enter__(self):
        """上下文管理器入口"""
        return self.connect()
    
    def __exit__(self, exc_type, exc_val, exc_tb):
        """上下文管理器出口"""
        self.close()
