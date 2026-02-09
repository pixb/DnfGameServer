#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
分析Java工程中的JProtobuf文件迁移进度
"""

import sqlite3
import subprocess
import json
from pathlib import Path
from collections import defaultdict
from datetime import datetime

def count_jprotobuf_files():
    """统计使用JProtobuf的文件"""
    # 统计不同目录的文件
    result = {
        'mina_protobuf': 0,
        'cross_server': 0,
        'udp_model': 0,
        'mina_udp': 0,
        'test': 0,
        'total': 0
    }
    
    # 统计主protobuf目录
    cmd1 = """
    find src/main/java/com/dnfm/mina/protobuf -name "*.java" -type f ! -name "*Test*" | 
    xargs grep -l "@MessageMeta\\|@Protobuf" 2>/dev/null | wc -l
    """
    
    # 统计cross目录
    cmd2 = """
    find src/main/java/com/dnfm/cross -name "*.java" -typef | 
    xargs grep -l "@MessageMeta\\|@Protobuf" 2>/dev/null | wc -l
    """
    
    # 统计udp model
    cmd3 = """
    find src/main/java/com/dnfm/mina/udp/model -name "*.java" -type f | 
    xargs grep -l "@MessageMeta" 2>/dev/null | wc -l
    """
    
    try:
        r1 = subprocess.run(cmd1, shell=True, capture_output=True, text=True, cwd='/home/pix/dev/code/java/DnfGameServer')
        result['mina_protobuf'] = int(r1.stdout.strip()) if r1.stdout.strip() else 0
    except:
        pass
    
    try:
        r2 = subprocess.run(cmd2, shell=True, capture_output=True, text=True, cwd='/home/pix/dev/code/java/DnfGameServer')
        result['cross_server'] = int(r2.stdout.strip()) if r2.stdout.strip() else 0
    except:
        pass
    
    try:
        r3 = subprocess.run(cmd3, shell=True, capture_output=True, text=True, cwd='/home/pix/dev/code/java/DnfGameServer')
        result['udp_model'] = int(r3.stdout.strip()) if r3.stdout.strip() else 0
    except:
        pass
    
    result['total'] = result['mina_protobuf'] + result['cross_server'] + result['udp_model']
    
    return result

def analyze_file_categories():
    """分析文件分类（REQ/RES/PT/NOTIFY等）"""
    categories = defaultdict(int)
    
    cmd = """
    find src/main/java/com/dnfm/mina/protobuf -name "*.java" -type f ! -name "*Test*" | 
    xargs grep -l "@MessageMeta\\|@Protobuf" 2>/dev/null
    """
    
    try:
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, cwd='/home/pix/dev/code/java/DnfGameServer')
        files = result.stdout.strip().split('\n') if result.stdout.strip() else []
        
        for f in files:
            if not f:
                continue
            filename = Path(f).stem
            
            if filename.startswith('REQ_'):
                categories['REQ (请求)'] += 1
            elif filename.startswith('RES_'):
                categories['RES (响应)'] += 1
            elif filename.startswith('PT_'):
                categories['PT (数据类型)'] += 1
            elif filename.startswith('NOTIFY_'):
                categories['NOTIFY (通知)'] += 1
            else:
                categories['OTHER (其他)'] += 1
    except:
        pass
    
    return categories

def get_migration_progress():
    """从数据库获取迁移进度"""
    db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts/migration_progress.db'
    
    try:
        conn = sqlite3.connect(db_path)
        cursor = conn.cursor()
        
        # 获取总体统计
        cursor.execute('''
            SELECT 
                COUNT(*) as total_batches,
                SUM(CASE WHEN status = 'completed' THEN 1 ELSE 0 END) as completed_batches,
                SUM(total_files) as total_files,
                SUM(migrated_files) as migrated_files
            FROM batches
        ''')
        batch_stats = cursor.fetchone()
        
        # 获取文件统计
        cursor.execute('''
            SELECT status, COUNT(*) as count
            FROM migration_files
            GROUP BY status
        ''')
        file_stats = dict(cursor.fetchall())
        
        conn.close()
        
        return {
            'batches_total': batch_stats[0] or 0,
            'batches_completed': batch_stats[1] or 0,
            'files_total': batch_stats[2] or 0,
            'files_migrated': batch_stats[3] or 0,
            'file_stats': file_stats
        }
    except:
        return None

def generate_report():
    """生成分析报告"""
    print("=" * 80)
    print("🚀 JProtobuf 迁移分析报告")
    print("=" * 80)
    print(f"生成时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    print()
    
    # 1. 文件统计
    print("📊 文件分布统计")
    print("-" * 80)
    file_counts = count_jprotobuf_files()
    print(f"Mina Protobuf 目录: {file_counts['mina_protobuf']:,} 个文件")
    print(f"Cross Server 目录:  {file_counts['cross_server']:,} 个文件")
    print(f"UDP Model 目录:     {file_counts['udp_model']:,} 个文件")
    print(f"合计:               {file_counts['total']:,} 个文件")
    print()
    
    # 2. 文件分类
    print("📁 文件分类统计 (Protobuf目录)")
    print("-" * 80)
    categories = analyze_file_categories()
    for cat, count in sorted(categories.items(), key=lambda x: x[1], reverse=True):
        print(f"{cat:<20}: {count:>4} 个文件")
    print()
    
    # 3. 迁移进度
    print("📈 当前迁移进度")
    print("-" * 80)
    progress = get_migration_progress()
    
    if progress:
        print(f"批次进度: {progress['batches_completed']}/{progress['batches_total']} 批次完成")
        print(f"文件进度: {progress['files_migrated']}/{progress['files_total']} 文件已记录")
        
        if progress['files_total'] > 0:
            recorded_progress = (progress['files_migrated'] / progress['files_total']) * 100
            print(f"已记录进度: {recorded_progress:.2f}%")
        
        # 实际总进度
        if file_counts['mina_protobuf'] > 0:
            overall_progress = (progress['files_migrated'] / file_counts['mina_protobuf']) * 100
            print(f"总体迁移进度: {progress['files_migrated']}/{file_counts['mina_protobuf']} ({overall_progress:.2f}%)")
        
        print()
        print("文件状态分布:")
        for status, count in sorted(progress['file_stats'].items()):
            emoji = {
                'completed': '✅',
                'in_progress': '🔄',
                'pending': '⏳',
                'failed': '❌',
                'blocked': '🚫'
            }.get(status, '⚪')
            print(f"  {emoji} {status:<12}: {count:>4} 个")
    else:
        print("❌ 无法读取迁移数据库")
    
    print()
    
    # 4. 预估工作量
    print("⏱️ 工作量预估")
    print("-" * 80)
    files_per_batch = 50  # 假设每批次迁移50个文件
    total_files = file_counts['mina_protobuf']
    estimated_batches = (total_files + files_per_batch - 1) // files_per_batch
    
    print(f"总文件数: {total_files:,} 个")
    print(f"建议批次大小: {files_per_batch} 个文件/批次")
    print(f"预估需要批次: ~{estimated_batches} 个批次")
    
    if progress and progress['files_migrated'] > 0:
        remaining_files = total_files - progress['files_migrated']
        remaining_batches = (remaining_files + files_per_batch - 1) // files_per_batch
        print(f"已完成文件: {progress['files_migrated']} 个")
        print(f"待迁移文件: {remaining_files:,} 个")
        print(f"剩余批次: ~{remaining_batches} 个")
    
    print()
    
    # 5. 按模块分析建议
    print("📋 模块分类建议")
    print("-" * 80)
    print("""
根据文件名前缀分析，文件可按以下模块分类:

1. **登录认证模块** (LOGIN/SESSION/AUTH)
   - REQ_LOGIN, RES_LOGIN, SESSION_LOGOUT, AUTH_INFO 等
   
2. **角色管理模块** (CHARACTER/CHARAC)
   - CHARACTER_INFO, JOB_INFO, REQ_LOAD_CHARAC 等
   
3. **物品系统模块** (ITEM/EQUIP/STACKABLE)
   - PT_ITEM, REQ_ITEM_LIST, RES_EQUIP_LIST 等
   
4. **技能系统模块** (SKILL)
   - PT_SKILL, REQ_SKILL_SLOT, PT_ALL_SKILL_SLOT 等
   
5. **副本系统模块** (DUNGEON)
   - PT_DUNGEON_*, REQ_DUNGEON_*, RES_DUNGEON_* 等
   
6. **公会系统模块** (GUILD)
   - PT_GUILD_*, REQ_GUILD_*, RES_GUILD_* 等
   
7. **PVP系统模块** (PVP/PVE)
   - PT_PVP_*, REQ_PVP_*, RES_PVP_* 等
   
8. **任务系统模块** (QUEST)
   - PT_QUEST, REQ_QUEST_*, RES_QUEST_* 等
   
9. **宠物系统模块** (CREATURE)
   - PT_CREATURE_*, REQ_CREATURE_*, RES_CREATURE_* 等
   
10. **商城系统模块** (SHOP/GACHA)
    - PT_CERA_SHOP_*, REQ_CERA_*, RES_CERA_* 等
    
11. **聊天社交模块** (CHAT/PARTY/FRIEND)
    - PT_CHAT_*, REQ_CHAT_*, RES_CHAT_* 等
    
12. **活动系统模块** (EVENT/MINIGAME)
    - PT_EVENT_*, REQ_EVENT_*, RES_EVENT_* 等
    
13. **其他数据类型** (PT_前缀)
    - 大量 PT_ 开头的数据类型文件
""")
    
    print()
    print("=" * 80)
    print("✅ 分析完成")
    print("=" * 80)
    
    return {
        'total_files': file_counts['total'],
        'mina_protobuf': file_counts['mina_protobuf'],
        'progress': progress,
        'categories': dict(categories)
    }

if __name__ == '__main__':
    generate_report()
