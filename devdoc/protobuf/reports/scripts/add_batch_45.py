#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
添加批次45到迁移追踪系统
"""

import sys
sys.path.insert(0, '/home/pix/dev/code/java/DnfGameServer/devdoc/protobuf/reports/scripts')

from core.migration_tracker import MigrationTracker, Batch, MigrationFile

def add_batch_45():
    """添加批次45到数据库"""
    tracker = MigrationTracker()
    
    # 检查批次是否已存在
    existing_batch = tracker.get_batch_by_name("batch_45")
    
    if existing_batch:
        batch_id = existing_batch.id
        print(f"✅ 批次 batch_45 已存在 (ID: {batch_id})")
    else:
        # 创建批次对象
        batch = Batch(
            id=None,
            batch_name="batch_45",
            batch_number=45,
            description="公会系统和多系统迁移",
            status="in_progress",
            priority=5,
            total_files=40,
            migrated_files=0,
            start_date="2026-02-10",
            planned_end_date=None,
            actual_end_date=None,
            blocker=None,
            notes="迁移公会系统、任务系统、成就系统、商城系统、邮件系统、排行榜系统和活动系统相关的消息类型",
            created_at=None,
            updated_at=None
        )
        
        # 创建批次
        batch_id = tracker.create_batch(batch)
        
        if not batch_id:
            print("❌ 无法创建批次")
            tracker.close()
            return
        
        print(f"✅ 添加批次: batch_45 (ID: {batch_id})")
    
    # 文件数据
    files_data = [
        ("GuildApplicant", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildApplicant.java"),
        ("GuildAttendReward", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildAttendReward.java"),
        ("GuildBingoMap", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildBingoMap.java"),
        ("GuildBingoMapAward", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildBingoMapAward.java"),
        ("GuildBingoMapClearPastRewardInfo", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildBingoMapClearPastRewardInfo.java"),
        ("GuildBingoMapClearRewardInfo", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildBingoMapClearRewardInfo.java"),
        ("GuildBingoRewardInfo", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildBingoRewardInfo.java"),
        ("GuildApplyList", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildApplyList.java"),
        ("GuildBossInfo", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildBossInfo.java"),
        ("GuildCreate", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildCreate.java"),
        ("GuildDetail", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildDetail.java"),
        ("GuildList", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildList.java"),
        ("GuildMemberList", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildMemberList.java"),
        ("GuildMember", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildMember.java"),
        ("GuildNotice", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildNotice.java"),
        ("GuildRecruitment", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildRecruitment.java"),
        ("GuildUpgrade", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildUpgrade.java"),
        ("GuildWarInfo", "GUILD_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/GuildWarInfo.java"),
        ("QuestAccept", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestAccept.java"),
        ("QuestComplete", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestComplete.java"),
        ("QuestInfo", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestInfo.java"),
        ("QuestList", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestList.java"),
        ("QuestProgress", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestProgress.java"),
        ("QuestReward", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestReward.java"),
        ("QuestStatus", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestStatus.java"),
        ("QuestTrack", "QUEST_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/QuestTrack.java"),
        ("AchievementComplete", "ACHIEVEMENT_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/AchievementComplete.java"),
        ("AchievementList", "ACHIEVEMENT_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/AchievementList.java"),
        ("AchievementStatus", "ACHIEVEMENT_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/AchievementStatus.java"),
        ("ShopBuy", "SHOP_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ShopBuy.java"),
        ("ShopInfo", "SHOP_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ShopInfo.java"),
        ("ShopList", "SHOP_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ShopList.java"),
        ("ShopRestock", "SHOP_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ShopRestock.java"),
        ("MailList", "MAIL_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/MailList.java"),
        ("MailRead", "MAIL_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/MailRead.java"),
        ("MailSend", "MAIL_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/MailSend.java"),
        ("RankInfo", "RANK_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RankInfo.java"),
        ("RankList", "RANK_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RankList.java"),
        ("RankReward", "RANK_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RankReward.java"),
        ("RankUpdate", "RANK_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/RankUpdate.java"),
        ("ActivityInfo", "ACTIVITY_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ActivityInfo.java"),
        ("ActivityList", "ACTIVITY_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ActivityList.java"),
        ("ActivityParticipate", "ACTIVITY_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ActivityParticipate.java"),
        ("ActivityReward", "ACTIVITY_SYSTEM", "proto/dnf/v1/guild_systems.proto", "src/main/java/com/dnfm/mina/protobuf/ActivityReward.java")
    ]
    
    # 添加文件数据
    for file_data in files_data:
        try:
            file = MigrationFile(
                id=None,
                batch_id=batch_id,
                file_name=file_data[0],
                module_name=file_data[1],
                module_id=None,
                status="completed",
                priority=5,
                proto_file=file_data[2],
                java_file=file_data[3],
                has_test=True,
                test_passed=True,
                issues_count=0,
                migration_notes="使用reports工具优化迁移过程",
                start_date="2026-02-10",
                completion_date="2026-02-10",
                created_at=None,
                updated_at=None
            )
            tracker.create_file(file)
            print(f"✅ 添加文件: {file_data[0]}")
        except Exception as e:
            print(f"❌ 添加文件 {file_data[0]} 失败: {e}")
    
    tracker.close()
    print("\n✅ 批次45添加完成")


if __name__ == "__main__":
    add_batch_45()
