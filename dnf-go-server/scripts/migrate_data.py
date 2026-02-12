#!/usr/bin/env python3
import sqlite3
import mysql.connector
import json
import sys
from datetime import datetime
from typing import Dict, List, Any, Optional

# MySQL连接配置
MYSQL_CONFIG = {
    'host': '127.0.0.1',
    'port': 3306,
    'user': 'root',
    'password': '123456',
    'database': 'game',
    'charset': 'utf8mb4'
}

# SQLite数据库路径
SQLITE_DB = 'game.db'

def datetime_to_timestamp(dt_str: Optional[str]) -> int:
    """将MySQL DATETIME转换为Unix时间戳"""
    if not dt_str:
        return 0
    try:
        dt = datetime.strptime(dt_str, '%Y-%m-%d %H:%M:%S')
        return int(dt.timestamp())
    except:
        try:
            dt = datetime.strptime(dt_str, '%Y-%m-%d %H:%M:%S.%f')
            return int(dt.timestamp())
        except:
            return 0

def safe_json_parse(json_str: Optional[str]) -> Dict[str, Any]:
    """安全解析JSON字符串"""
    if not json_str:
        return {}
    try:
        return json.loads(json_str)
    except:
        return {}

def migrate_accounts(mysql_conn: mysql.connector.MySQLConnection, sqlite_conn: sqlite3.Connection) -> int:
    """迁移账户数据"""
    print("迁移账户数据...")
    
    mysql_cursor = mysql_conn.cursor(dictionary=True)
    sqlite_cursor = sqlite_conn.cursor()
    
    try:
        mysql_cursor.execute("SELECT * FROM t_account")
        accounts = mysql_cursor.fetchall()
        
        for account in accounts:
            sql = """
            INSERT OR REPLACE INTO account 
            (openid, account_key, auth_key, last_login_at, authority, status, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """
            sqlite_cursor.execute(sql, (
                account.get('id', ''),
                account.get('accountkey', ''),
                account.get('passwd', ''),
                account.get('lastLoginTime', 0) or 0,
                account.get('privilege', 0) or 0,
                account.get('isStop', 0) or 0,
                datetime_to_timestamp(str(account.get('createTime', ''))),
                datetime_to_timestamp(str(account.get('createTime', '')))
            ))
        
        sqlite_conn.commit()
        count = len(accounts)
        print(f"✓ 迁移了 {count} 条账户数据")
        return count
        
    except Exception as e:
        print(f"✗ 迁移账户数据失败: {e}")
        import traceback
        traceback.print_exc()
        sqlite_conn.rollback()
        return 0
    finally:
        mysql_cursor.close()

def migrate_roles(mysql_conn: mysql.connector.MySQLConnection, sqlite_conn: sqlite3.Connection) -> int:
    """迁移角色数据"""
    print("迁移角色数据...")
    
    mysql_cursor = mysql_conn.cursor(dictionary=True)
    sqlite_cursor = sqlite_conn.cursor()
    
    try:
        mysql_cursor.execute("SELECT * FROM t_role")
        roles = mysql_cursor.fetchall()
        
        if not roles:
            print("  警告: 没有角色数据需要迁移")
            return 0
        
        for role in roles:
            # 获取account_id
            openid = role.get('openid', '')
            sqlite_cursor.execute("SELECT id FROM account WHERE openid = ?", (openid,))
            result = sqlite_cursor.fetchone()
            account_id = result[0] if result else 0
            
            if account_id == 0:
                print(f"  警告: 角色 {role.get('name')} 的账户 {openid} 不存在，跳过")
                continue
            
            sql = """
            INSERT OR REPLACE INTO role 
            (account_id, role_id, name, job, level, exp, fatigue, x, y, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """
            sqlite_cursor.execute(sql, (
                account_id,
                role.get('roleId', 0),
                role.get('name', ''),
                role.get('job', 0),
                role.get('level', 1),
                role.get('exp', 0),
                role.get('fatigue', 100),
                0,  # x
                0,  # y
                role.get('createtime', 0),
                role.get('createtime', 0)
            ))
        
        sqlite_conn.commit()
        count = len(roles)
        print(f"✓ 迁移了 {count} 条角色数据")
        return count
        
    except Exception as e:
        print(f"✗ 迁移角色数据失败: {e}")
        import traceback
        traceback.print_exc()
        sqlite_conn.rollback()
        return 0
    finally:
        mysql_cursor.close()

def migrate_achievement_config(mysql_conn: mysql.connector.MySQLConnection, sqlite_conn: sqlite3.Connection) -> int:
    """迁移成就配置数据"""
    print("迁移成就配置数据...")
    
    mysql_cursor = mysql_conn.cursor(dictionary=True)
    sqlite_cursor = sqlite_conn.cursor()
    
    try:
        mysql_cursor.execute("SELECT * FROM p_achievement")
        configs = mysql_cursor.fetchall()
        
        for config in configs:
            sql = """
            INSERT OR REPLACE INTO t_achievement_config 
            (achievement_id, name, description, target_value, reward_type, reward_value)
            VALUES (?, ?, ?, ?, ?, ?)
            """
            sqlite_cursor.execute(sql, (
                config.get('index', 0),
                config.get('title', ''),
                config.get('summary', ''),
                config.get('count', 0),
                0,  # reward_type - 默认为0
                0   # reward_value - 默认为0
            ))
        
        sqlite_conn.commit()
        count = len(configs)
        print(f"✓ 迁移了 {count} 条成就配置数据")
        return count
        
    except Exception as e:
        print(f"✗ 迁移成就配置数据失败: {e}")
        import traceback
        traceback.print_exc()
        sqlite_conn.rollback()
        return 0
    finally:
        mysql_cursor.close()

def migrate_achievement_records(mysql_conn: mysql.connector.MySQLConnection, sqlite_conn: sqlite3.Connection) -> int:
    """迁移成就记录数据"""
    print("迁移成就记录数据...")
    
    mysql_cursor = mysql_conn.cursor(dictionary=True)
    sqlite_cursor = sqlite_conn.cursor()
    
    try:
        # 检查是否有成就记录表
        mysql_cursor.execute("SHOW TABLES LIKE 't_achievement_record'")
        result = mysql_cursor.fetchone()
        
        if not result:
            print("  提示: MySQL中没有成就记录表，跳过迁移")
            return 0
        
        mysql_cursor.execute("SELECT * FROM t_achievement_record")
        records = mysql_cursor.fetchall()
        
        for record in records:
            # 转换role_id
            mysql_role_id = record.get('role_id', 0)
            sqlite_cursor.execute("SELECT id FROM role WHERE role_id = ?", (mysql_role_id,))
            result = sqlite_cursor.fetchone()
            sqlite_role_id = result[0] if result else 0
            
            if sqlite_role_id == 0:
                print(f"  警告: 成就记录的角色ID {mysql_role_id} 不存在，跳过")
                continue
            
            sql = """
            INSERT OR REPLACE INTO t_achievement_record 
            (role_id, achievement_id, progress, completed, rewarded, reward_time, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """
            sqlite_cursor.execute(sql, (
                sqlite_role_id,
                record.get('achievement_id', 0),
                record.get('progress', 0),
                record.get('completed', 0),
                record.get('rewarded', 0),
                datetime_to_timestamp(str(record.get('reward_time', ''))),
                datetime_to_timestamp(str(record.get('create_time', ''))),
                datetime_to_timestamp(str(record.get('update_time', '')))
            ))
        
        sqlite_conn.commit()
        count = len(records)
        print(f"✓ 迁移了 {count} 条成就记录数据")
        return count
        
    except Exception as e:
        print(f"✗ 迁移成就记录数据失败: {e}")
        import traceback
        traceback.print_exc()
        sqlite_conn.rollback()
        return 0
    finally:
        mysql_cursor.close()

def migrate_config_table(mysql_conn: mysql.connector.MySQLConnection, sqlite_conn: sqlite3.Connection, table_name: str) -> int:
    """迁移单个配置表"""
    mysql_cursor = mysql_conn.cursor(dictionary=True)
    sqlite_cursor = sqlite_conn.cursor()
    
    try:
        mysql_cursor.execute(f"SELECT * FROM {table_name}")
        rows = mysql_cursor.fetchall()
        
        if not rows:
            return 0
        
        columns = list(rows[0].keys())
        placeholders = ', '.join(['?' for _ in columns])
        column_names = ', '.join(columns)
        
        sql = f"INSERT OR REPLACE INTO {table_name} ({column_names}) VALUES ({placeholders})"
        
        for row in rows:
            values = [row[col] for col in columns]
            sqlite_cursor.execute(sql, values)
        
        sqlite_conn.commit()
        return len(rows)
        
    except Exception as e:
        print(f"  ✗ 迁移 {table_name} 失败: {e}")
        return 0
    finally:
        mysql_cursor.close()

def migrate_config_tables(mysql_conn: mysql.connector.MySQLConnection, sqlite_conn: sqlite3.Connection) -> int:
    """迁移配置表数据"""
    print("迁移配置表数据...")
    
    config_tables = [
        'p_exp',
        'p_equip',
        'p_consume',
        'p_skill',
        'p_npc',
        'p_gamemap',
        'p_dungeon',
        'p_petexp',
        'p_itemshop',
        'p_server',
        'p_onlinemall',
        'p_transfer',
        'p_taskset',
        'p_talkset',
        'p_skin',
        'p_skill_play_time',
        'p_mapbosspos',
        'p_word',
        'p_itemdropset',
        'p_dungeonmap'
    ]
    
    total_count = 0
    for table_name in config_tables:
        count = migrate_config_table(mysql_conn, sqlite_conn, table_name)
        if count > 0:
            print(f"  ✓ 迁移了 {count} 条 {table_name} 数据")
            total_count += count
    
    return total_count

def verify_data(sqlite_conn: sqlite3.Connection):
    """验证迁移后的数据"""
    print("\n验证迁移数据...")
    
    cursor = sqlite_conn.cursor()
    
    # 验证账户数据
    cursor.execute("SELECT COUNT(*) FROM account")
    account_count = cursor.fetchone()[0]
    print(f"  账户数据: {account_count} 条")
    
    # 验证角色数据
    cursor.execute("SELECT COUNT(*) FROM role")
    role_count = cursor.fetchone()[0]
    print(f"  角色数据: {role_count} 条")
    
    # 验证成就配置
    cursor.execute("SELECT COUNT(*) FROM t_achievement_config")
    achievement_config_count = cursor.fetchone()[0]
    print(f"  成就配置: {achievement_config_count} 条")
    
    # 验证成就记录
    cursor.execute("SELECT COUNT(*) FROM t_achievement_record")
    achievement_record_count = cursor.fetchone()[0]
    print(f"  成就记录: {achievement_record_count} 条")
    
    # 验证配置表
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table' AND name LIKE 'p_%'")
    config_tables = cursor.fetchall()
    print(f"  配置表: {len(config_tables)} 个")
    
    cursor.close()

def main():
    print("=" * 60)
    print("MySQL 到 SQLite 数据迁移工具")
    print("=" * 60)
    print()
    
    # 连接MySQL
    print("连接MySQL数据库...")
    try:
        mysql_conn = mysql.connector.connect(**MYSQL_CONFIG)
        print("✓ MySQL连接成功")
    except Exception as e:
        print(f"✗ MySQL连接失败: {e}")
        sys.exit(1)
    
    # 连接SQLite
    print("连接SQLite数据库...")
    try:
        sqlite_conn = sqlite3.connect(SQLITE_DB)
        sqlite_conn.execute('PRAGMA foreign_keys = ON')
        print("✓ SQLite连接成功")
    except Exception as e:
        print(f"✗ SQLite连接失败: {e}")
        mysql_conn.close()
        sys.exit(1)
    
    try:
        print("\n开始数据迁移...")
        print("-" * 60)
        
        # 迁移数据
        account_count = migrate_accounts(mysql_conn, sqlite_conn)
        role_count = migrate_roles(mysql_conn, sqlite_conn)
        achievement_config_count = migrate_achievement_config(mysql_conn, sqlite_conn)
        achievement_record_count = migrate_achievement_records(mysql_conn, sqlite_conn)
        config_count = migrate_config_tables(mysql_conn, sqlite_conn)
        
        print("-" * 60)
        print("\n数据迁移完成!")
        
        # 验证数据
        verify_data(sqlite_conn)
        
        print("\n" + "=" * 60)
        print("迁移统计:")
        print(f"  账户数据: {account_count} 条")
        print(f"  角色数据: {role_count} 条")
        print(f"  成就配置: {achievement_config_count} 条")
        print(f"  成就记录: {achievement_record_count} 条")
        print(f"  配置数据: {config_count} 条")
        print("=" * 60)
        
    except Exception as e:
        print(f"\n✗ 迁移过程中发生错误: {e}")
        import traceback
        traceback.print_exc()
    finally:
        mysql_conn.close()
        sqlite_conn.close()
        print("\n数据库连接已关闭")

if __name__ == '__main__':
    main()
