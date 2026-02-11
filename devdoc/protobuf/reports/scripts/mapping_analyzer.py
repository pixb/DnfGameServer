#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
消息映射关系分析器
分析JProtobuf消息和标准Protobuf消息之间的映射关系
"""

import sqlite3
import re
from pathlib import Path
from typing import Dict, List, Tuple, Optional
from difflib import SequenceMatcher

class MessageMappingAnalyzer:
    """消息映射关系分析器"""
    
    def __init__(self, db_path: str):
        self.db_path = db_path
        self.project_root = Path('/home/pix/dev/code/java/DnfGameServer')
        self.batch_docs_dir = self.project_root / 'devdoc' / 'protobuf'
    
    def _connect(self):
        """连接数据库"""
        return sqlite3.connect(self.db_path)
    
    def calculate_similarity(self, str1: str, str2: str) -> float:
        """计算两个字符串的相似度"""
        return SequenceMatcher(None, str1.lower(), str2.lower()).ratio()
    
    def find_possible_mappings(self, jprotobuf_message: str) -> List[Tuple[str, float]]:
        """查找JProtobuf消息可能对应的标准Protobuf消息"""
        conn = self._connect()
        cursor = conn.cursor()
        
        # 获取所有标准Protobuf消息
        cursor.execute('SELECT message_name FROM proto_messages')
        proto_messages = [row[0] for row in cursor.fetchall()]
        
        conn.close()
        
        # 生成可能的映射
        possible_mappings = []
        
        # 1. 直接映射：PT_ACHIEVEMENT_REWARD -> AchievementReward
        base_name = jprotobuf_message.replace('PT_', '').replace('REQ_', '').replace('RES_', '')
        
        # 转换为驼峰命名
        proto_name_camel = ''.join(word.capitalize() for word in base_name.split('_'))
        
        # 2. 去掉前缀：PT_ACHIEVEMENT_REWARD -> AchievementReward
        if jprotobuf_message.startswith('PT_'):
            proto_name_direct = jprotobuf_message[3:]
            possible_mappings.append((proto_name_direct, 1.0))
        
        # 3. 去掉前缀并添加Request/Response
        if jprotobuf_message.startswith('REQ_'):
            proto_name_request = jprotobuf_message[4:] + 'Request'
            proto_name_response = jprotobuf_message[4:] + 'Response'
            possible_mappings.append((proto_name_request, 0.9))
            possible_mappings.append((proto_name_response, 0.9))
        elif jprotobuf_message.startswith('RES_'):
            proto_name_request = jprotobuf_message[4:] + 'Request'
            proto_name_response = jprotobuf_message[4:] + 'Response'
            possible_mappings.append((proto_name_request, 0.9))
            possible_mappings.append((proto_name_response, 0.9))
        
        # 4. 添加驼峰命名
        possible_mappings.append((proto_name_camel, 0.8))
        
        # 5. 模糊匹配
        for proto_message in proto_messages:
            similarity = self.calculate_similarity(jprotobuf_message, proto_message)
            if similarity > 0.7:
                possible_mappings.append((proto_message, similarity))
        
        # 去重并排序
        unique_mappings = {}
        for proto_name, confidence in possible_mappings:
            if proto_name in unique_mappings:
                unique_mappings[proto_name] = max(unique_mappings[proto_name], confidence)
            else:
                unique_mappings[proto_name] = confidence
        
        # 排序
        sorted_mappings = sorted(unique_mappings.items(), key=lambda x: x[1], reverse=True)
        
        return sorted_mappings
    
    def analyze_mappings_from_batch_docs(self) -> Dict[str, Dict]:
        """从批次文档中分析映射关系"""
        print("🔍 从批次文档分析映射关系...")
        print()
        
        mappings = {}
        
        for batch_dir in sorted(self.batch_docs_dir.glob('batch_*')):
            batch_match = re.search(r'batch_(\d+)', batch_dir.name)
            if not batch_match:
                continue
            
            batch_id = int(batch_match.group(1))
            
            # 检查迁移结果
            result_file = batch_dir / '02_迁移结果.md'
            if not result_file.exists():
                continue
            
            with open(result_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 提取映射关系：从表格中提取 REQ_ACHIEVEMENT_LIST -> AchievementListRequest
            # 匹配源文件表格中的文件名
            source_table_pattern = r'\|\s*([A-Z_]+\.java)\s*\|'
            for match in re.finditer(source_table_pattern, content):
                jprotobuf_name = match.group(1).replace('.java', '')
                
                # 查找对应的目标文件
                target_table_pattern = r'\|\s*([A-Z][a-zA-Z]+\.java)\s*\|'
                for target_match in re.finditer(target_table_pattern, content):
                    proto_java_name = target_match.group(1).replace('.java', '')
                    
                    # 尝试匹配：REQ_ACHIEVEMENT_LIST -> AchievementListRequest
                    if jprotobuf_name.startswith('REQ_'):
                        base_name = jprotobuf_name[4:]
                        expected_proto_name = ''.join(word.capitalize() for word in base_name.split('_')) + 'Request'
                        if proto_java_name == expected_proto_name:
                            if jprotobuf_name not in mappings:
                                mappings[jprotobuf_name] = {
                                    'proto_name': proto_java_name,
                                    'batch_id': batch_id,
                                    'source': 'batch_document'
                                }
                    elif jprotobuf_name.startswith('RES_'):
                        base_name = jprotobuf_name[4:]
                        expected_proto_name = ''.join(word.capitalize() for word in base_name.split('_')) + 'Response'
                        if proto_java_name == expected_proto_name:
                            if jprotobuf_name not in mappings:
                                mappings[jprotobuf_name] = {
                                    'proto_name': proto_java_name,
                                    'batch_id': batch_id,
                                    'source': 'batch_document'
                                }
                    elif jprotobuf_name.startswith('PT_'):
                        base_name = jprotobuf_name[3:]
                        expected_proto_name = ''.join(word.capitalize() for word in base_name.split('_'))
                        if proto_java_name == expected_proto_name:
                            if jprotobuf_name not in mappings:
                                mappings[jprotobuf_name] = {
                                    'proto_name': proto_java_name,
                                    'batch_id': batch_id,
                                    'source': 'batch_document'
                                }
        
        print(f"  从批次文档中找到 {len(mappings)} 个映射")
        print()
        
        return mappings
    
    def save_mappings_to_database(self, mappings: Dict[str, Dict]):
        """保存映射关系到数据库"""
        print("💾 保存映射关系到数据库...")
        print()
        
        conn = self._connect()
        cursor = conn.cursor()
        
        inserted = 0
        updated = 0
        errors = 0
        
        for jprotobuf_name, mapping_info in mappings.items():
            try:
                # 获取JProtobuf消息ID
                cursor.execute('''
                    SELECT id FROM jprotobuf_messages
                    WHERE message_name = ?
                ''', (jprotobuf_name,))
                
                jprotobuf_result = cursor.fetchone()
                if not jprotobuf_result:
                    continue
                
                jprotobuf_id = jprotobuf_result[0]
                
                # 从Java类名推导Proto消息名
                # AchievementListRequest -> AchievementListRequest
                proto_message_name = mapping_info['proto_name']
                
                # 获取标准Protobuf消息ID
                cursor.execute('''
                    SELECT id FROM proto_messages
                    WHERE message_name = ?
                ''', (proto_message_name,))
                
                proto_result = cursor.fetchone()
                if not proto_result:
                    continue
                
                proto_id = proto_result[0]
                
                # 检查是否已存在
                cursor.execute('''
                    SELECT id FROM message_mappings
                    WHERE jprotobuf_message_id = ? AND proto_message_id = ?
                ''', (jprotobuf_id, proto_id))
                
                existing = cursor.fetchone()
                
                if existing:
                    # 更新
                    cursor.execute('''
                        UPDATE message_mappings
                        SET mapping_type = ?, mapping_confidence = 1.0,
                            is_verified = 1, verified_by = 'batch_document',
                            updated_at = CURRENT_TIMESTAMP
                        WHERE id = ?
                    ''', ('direct', existing[0]))
                    updated += 1
                else:
                    # 插入
                    cursor.execute('''
                        INSERT INTO message_mappings
                        (jprotobuf_message_id, proto_message_id, mapping_type,
                         mapping_confidence, is_verified, verified_by, notes)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                    ''', (
                        jprotobuf_id,
                        proto_id,
                        'direct',
                        1.0,
                        1,
                        'batch_document',
                        f'From batch {mapping_info["batch_id"]}'
                    ))
                    inserted += 1
                
            except Exception as e:
                errors += 1
                print(f"  错误: {jprotobuf_name} -> {mapping_info['proto_name']} - {e}")
        
        conn.commit()
        conn.close()
        
        print(f"  插入: {inserted} 条记录")
        print(f"  更新: {updated} 条记录")
        print(f"  错误: {errors} 条记录")
        print()
    
    def analyze_and_save(self):
        """分析并保存映射关系"""
        # 从批次文档分析
        mappings = self.analyze_mappings_from_batch_docs()
        
        # 保存到数据库
        self.save_mappings_to_database(mappings)
        
        print("✅ 映射关系分析完成！")
        print()
        
        # 打印统计
        conn = self._connect()
        cursor = conn.cursor()
        
        cursor.execute('SELECT COUNT(*) FROM message_mappings')
        total = cursor.fetchone()[0]
        
        cursor.execute('''
            SELECT mapping_type, COUNT(*) 
            FROM message_mappings 
            GROUP BY mapping_type
        ''')
        
        type_stats = cursor.fetchall()
        
        conn.close()
        
        print("📊 统计信息:")
        print(f"  总映射数: {total}")
        print("  按类型分布:")
        for mapping_type, count in type_stats:
            print(f"    {mapping_type}: {count}")
        print()

def main():
    """主函数"""
    import argparse
    
    parser = argparse.ArgumentParser(description='消息映射关系分析器')
    parser.add_argument('--analyze', action='store_true', help='分析并保存映射关系')
    
    args = parser.parse_args()
    
    if args.analyze:
        db_path = '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/data/migration_system.db'
        analyzer = MessageMappingAnalyzer(db_path)
        analyzer.analyze_and_save()
    else:
        parser.print_help()

if __name__ == '__main__':
    main()
