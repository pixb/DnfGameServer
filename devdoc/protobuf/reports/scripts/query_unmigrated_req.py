#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
查询未迁移的 REQ 消息，按模块分组
"""

import sqlite3
from pathlib import Path
from collections import defaultdict

def query_unmigrated_req():
    """查询未迁移的 REQ 消息"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
    
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()
    
    # 查询未迁移的 REQ 消息
    cursor.execute('''
        SELECT 
            jm.message_name,
            jm.file_path,
            jm.module_id,
            jm.field_count
        FROM jprotobuf_messages jm
        WHERE jm.message_type = 'REQ' 
        AND jm.id NOT IN (SELECT mr.jprotobuf_message_id FROM migration_records mr)
        ORDER BY jm.file_path, jm.message_name
    ''')
    
    results = cursor.fetchall()
    conn.close()
    
    # 按模块分组
    modules = defaultdict(list)
    for row in results:
        message_name, file_path, module_id, field_count = row
        
        # 从文件路径中提取模块名
        # 例如: /home/pix/dev/code/java/DnfGameServer/src/main/java/com/dnfm/mina/protobuf/REQ_LOGIN.java
        # 模块名应该是文件名去掉 REQ_ 前缀和 .java 后缀
        # 但更准确的是从文件路径中提取
        
        # 尝试从 ModuleID 推断模块
        # 10000-10999: 基础模块
        # 11000-11999: 角色模块
        # 12000-12999: 副本模块
        # 13000-13999: 城镇模块
        # 14000-14999: 物品模块
        # 15000-15999: 装备模块
        # 16000-16999: 技能模块
        # 17000-17999: 任务模块
        # 18000-18999: 社交模块
        # 19000-19999: 公会模块
        # 20000-20999: 拍卖模块
        # 21000-21999: 商城模块
        # 22000-22999: 充值模块
        # 23000-23999: 跨服模块
        # 24000-24999: 战斗模块
        # 25000-25999: 其他模块
        
        if module_id:
            if 10000 <= module_id < 11000:
                module = 'BASIC'
            elif 11000 <= module_id < 12000:
                module = 'CHARACTER'
            elif 12000 <= module_id < 13000:
                module = 'DUNGEON'
            elif 13000 <= module_id < 14000:
                module = 'TOWN'
            elif 14000 <= module_id < 15000:
                module = 'ITEM'
            elif 15000 <= module_id < 16000:
                module = 'EQUIP'
            elif 16000 <= module_id < 17000:
                module = 'SKILL'
            elif 17000 <= module_id < 18000:
                module = 'TASK'
            elif 18000 <= module_id < 19000:
                module = 'SOCIAL'
            elif 19000 <= module_id < 20000:
                module = 'GUILD'
            elif 20000 <= module_id < 21000:
                module = 'AUCTION'
            elif 21000 <= module_id < 22000:
                module = 'MALL'
            elif 22000 <= module_id < 23000:
                module = 'PAYMENT'
            elif 23000 <= module_id < 24000:
                module = 'CROSS_SERVER'
            elif 24000 <= module_id < 25000:
                module = 'BATTLE'
            else:
                module = 'OTHER'
        else:
            module = 'UNKNOWN'
        
        modules[module].append({
            'message_name': message_name,
            'file_path': file_path,
            'module_id': module_id,
            'field_count': field_count
        })
    
    # 打印结果
    print("📊 未迁移的 REQ 消息（按模块分组）")
    print("=" * 100)
    print()
    
    total_count = 0
    for module, messages in sorted(modules.items(), key=lambda x: len(x[1]), reverse=True):
        print(f"📦 {module}: {len(messages)} 个消息")
        print("-" * 100)
        for msg in messages[:10]:  # 只显示前10个
            print(f"  - {msg['message_name']:50s} | ModuleID: {str(msg['module_id']):6s} | 字段数: {str(msg['field_count']):3s}")
        if len(messages) > 10:
            print(f"  ... 还有 {len(messages) - 10} 个消息")
        print()
        total_count += len(messages)
    
    print("=" * 100)
    print(f"总计: {total_count} 个未迁移的 REQ 消息")
    print()
    
    # 推荐下一个迁移的模块
    print("💡 推荐迁移的模块（按消息数量排序）:")
    print("-" * 100)
    for i, (module, messages) in enumerate(sorted(modules.items(), key=lambda x: len(x[1]), reverse=True)[:5], 1):
        print(f"{i}. {module:20s} - {len(messages):3d} 个消息")
    print()

if __name__ == '__main__':
    query_unmigrated_req()
