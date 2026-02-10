package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch61Migration 测试第61批迁移的消息类型
func TestBatch61Migration(t *testing.T) {
	t.Run("Guild", testGuildMessages)
	t.Run("Quest", testQuestMessages)
	t.Run("Achievement", testAchievementMessages)
	t.Run("Shop", testShopMessages)
	t.Run("Mail", testMailMessages)
	t.Run("Rank", testRankMessages)
	t.Run("Activity", testActivityMessages)
}

// 测试公会相关消息
func testGuildMessages(t *testing.T) {
	// 测试GuildApplicant
	guildApplicant := &dnfv1.GuildApplicant{
		Guid:           1234567890,
		Growtype:       2,
		Secondgrowtype: 3,
		Job:            1,
		Level:          95,
		Name:           "Test Applicant",
		Creditscore:    1000,
		Characterframe: 1,
	}
	assert.NotNil(t, guildApplicant)
	assert.Equal(t, uint64(1234567890), guildApplicant.Guid)
	assert.Equal(t, int32(2), guildApplicant.Growtype)
	assert.Equal(t, int32(3), guildApplicant.Secondgrowtype)
	assert.Equal(t, int32(1), guildApplicant.Job)
	assert.Equal(t, int32(95), guildApplicant.Level)
	assert.Equal(t, "Test Applicant", guildApplicant.Name)
	assert.Equal(t, int32(1000), guildApplicant.Creditscore)
	assert.Equal(t, int32(1), guildApplicant.Characterframe)

	// 测试GuildAttendReward
	guildAttendReward := &dnfv1.GuildAttendReward{
		Index: 1,
	}
	assert.NotNil(t, guildAttendReward)
	assert.Equal(t, int32(1), guildAttendReward.Index)

	// 测试GuildBingoMap
	guildBingoMap := &dnfv1.GuildBingoMap{
		Index: 1,
		Count: 5,
	}
	assert.NotNil(t, guildBingoMap)
	assert.Equal(t, int32(1), guildBingoMap.Index)
	assert.Equal(t, int32(5), guildBingoMap.Count)

	// 测试GuildBingoMapAward
	guildBingoMapAward := &dnfv1.GuildBingoMapAward{
		Index: 1,
		Count: 3,
	}
	assert.NotNil(t, guildBingoMapAward)
	assert.Equal(t, int32(1), guildBingoMapAward.Index)
	assert.Equal(t, int32(3), guildBingoMapAward.Count)

	// 测试GuildBingoMapClearPastRewardInfo
	guildBingoMapClearPastRewardInfo := &dnfv1.GuildBingoMapClearPastRewardInfo{
		Index: 1,
		Count: 2,
	}
	assert.NotNil(t, guildBingoMapClearPastRewardInfo)
	assert.Equal(t, int32(1), guildBingoMapClearPastRewardInfo.Index)
	assert.Equal(t, int32(2), guildBingoMapClearPastRewardInfo.Count)

	// 测试GuildBingoMapClearRewardInfo
	guildBingoMapClearRewardInfo := &dnfv1.GuildBingoMapClearRewardInfo{
		Index: 1,
	}
	assert.NotNil(t, guildBingoMapClearRewardInfo)
	assert.Equal(t, int32(1), guildBingoMapClearRewardInfo.Index)

	// 测试GuildBingoRewardInfo
	guildBingoRewardInfo := &dnfv1.GuildBingoRewardInfo{
		Index: 1,
		Count: 3,
	}
	assert.NotNil(t, guildBingoRewardInfo)
	assert.Equal(t, int32(1), guildBingoRewardInfo.Index)
	assert.Equal(t, int32(3), guildBingoRewardInfo.Count)

	// 测试GuildApplyList
	guildApplyList := &dnfv1.GuildApplyList{
		Error:      0,
		Applicants: []*dnfv1.GuildApplicant{guildApplicant},
	}
	assert.NotNil(t, guildApplyList)
	assert.Equal(t, int32(0), guildApplyList.Error)
	assert.Len(t, guildApplyList.Applicants, 1)

	// 测试GuildBossInfo
	guildBossInfo := &dnfv1.GuildBossInfo{
		BossId:       1,
		Hp:           1000,
		MaxHp:        2000,
		LastAttackTime: 1234567890,
	}
	assert.NotNil(t, guildBossInfo)
	assert.Equal(t, int32(1), guildBossInfo.BossId)
	assert.Equal(t, int32(1000), guildBossInfo.Hp)
	assert.Equal(t, int32(2000), guildBossInfo.MaxHp)
	assert.Equal(t, int64(1234567890), guildBossInfo.LastAttackTime)

	// 测试GuildCreate
	guildCreate := &dnfv1.GuildCreate{
		Name:    "Test Guild",
		Notice:  "Welcome to Test Guild",
		Level:   1,
	}
	assert.NotNil(t, guildCreate)
	assert.Equal(t, "Test Guild", guildCreate.Name)
	assert.Equal(t, "Welcome to Test Guild", guildCreate.Notice)
	assert.Equal(t, int32(1), guildCreate.Level)

	// 测试GuildDetail
	guildDetail := &dnfv1.GuildDetail{
		GuildId:       1,
		Name:          "Test Guild",
		Notice:        "Welcome to Test Guild",
		Level:         1,
		MemberCount:   10,
		MaxMemberCount: 50,
		MasterName:    "Master",
		MasterGuid:    1234567890,
	}
	assert.NotNil(t, guildDetail)
	assert.Equal(t, int32(1), guildDetail.GuildId)
	assert.Equal(t, "Test Guild", guildDetail.Name)
	assert.Equal(t, "Welcome to Test Guild", guildDetail.Notice)
	assert.Equal(t, int32(1), guildDetail.Level)
	assert.Equal(t, int32(10), guildDetail.MemberCount)
	assert.Equal(t, int32(50), guildDetail.MaxMemberCount)
	assert.Equal(t, "Master", guildDetail.MasterName)
	assert.Equal(t, uint64(1234567890), guildDetail.MasterGuid)

	// 测试GuildList
	guildList := &dnfv1.GuildList{
		Error:   0,
		Guilds:  []*dnfv1.GuildDetail{guildDetail},
	}
	assert.NotNil(t, guildList)
	assert.Equal(t, int32(0), guildList.Error)
	assert.Len(t, guildList.Guilds, 1)

	// 测试GuildMemberList
	guildMemberList := &dnfv1.GuildMemberList{
		Error:   0,
		Members: []*dnfv1.GuildMember{},
	}
	assert.NotNil(t, guildMemberList)
	assert.Equal(t, int32(0), guildMemberList.Error)

	// 测试GuildMember
	guildMember := &dnfv1.GuildMember{
		Guid:         1234567890,
		Name:         "Test Member",
		Job:          1,
		Level:        95,
		Position:     1,
		JoinTime:     1234567890,
		Contribution: 100,
	}
	assert.NotNil(t, guildMember)
	assert.Equal(t, uint64(1234567890), guildMember.Guid)
	assert.Equal(t, "Test Member", guildMember.Name)
	assert.Equal(t, int32(1), guildMember.Job)
	assert.Equal(t, int32(95), guildMember.Level)
	assert.Equal(t, int32(1), guildMember.Position)
	assert.Equal(t, int32(1234567890), guildMember.JoinTime)
	assert.Equal(t, int32(100), guildMember.Contribution)

	// 测试GuildNotice
	guildNotice := &dnfv1.GuildNotice{
		Notice: "Test Notice",
	}
	assert.NotNil(t, guildNotice)
	assert.Equal(t, "Test Notice", guildNotice.Notice)

	// 测试GuildRecruitment
	guildRecruitment := &dnfv1.GuildRecruitment{
		Content: "Recruiting members",
		Level:   50,
		Job:     1,
	}
	assert.NotNil(t, guildRecruitment)
	assert.Equal(t, "Recruiting members", guildRecruitment.Content)
	assert.Equal(t, int32(50), guildRecruitment.Level)
	assert.Equal(t, int32(1), guildRecruitment.Job)

	// 测试GuildUpgrade
	guildUpgrade := &dnfv1.GuildUpgrade{
		Level: 2,
	}
	assert.NotNil(t, guildUpgrade)
	assert.Equal(t, int32(2), guildUpgrade.Level)

	// 测试GuildWarInfo
	guildWarInfo := &dnfv1.GuildWarInfo{
		GuildId:      1,
		EnemyGuildId: 2,
		State:        1,
		StartTime:    1234567890,
		EndTime:      1234567890,
	}
	assert.NotNil(t, guildWarInfo)
	assert.Equal(t, int32(1), guildWarInfo.GuildId)
	assert.Equal(t, int32(2), guildWarInfo.EnemyGuildId)
	assert.Equal(t, int32(1), guildWarInfo.State)
	assert.Equal(t, int64(1234567890), guildWarInfo.StartTime)
	assert.Equal(t, int64(1234567890), guildWarInfo.EndTime)
}

// 测试任务相关消息
func testQuestMessages(t *testing.T) {
	// 测试QuestAccept
	questAccept := &dnfv1.QuestAccept{
		Qindex: 1,
	}
	assert.NotNil(t, questAccept)
	assert.Equal(t, int32(1), questAccept.Qindex)

	// 测试QuestComplete
	questComplete := &dnfv1.QuestComplete{
		Qindex: 1,
	}
	assert.NotNil(t, questComplete)
	assert.Equal(t, int32(1), questComplete.Qindex)

	// 测试QuestInfo
	questInfo := &dnfv1.QuestInfo{
		Qindex:    1,
		State:     1,
		Count:     5,
		Isminequest: true,
	}
	assert.NotNil(t, questInfo)
	assert.Equal(t, int32(1), questInfo.Qindex)
	assert.Equal(t, int32(1), questInfo.State)
	assert.Equal(t, int32(5), questInfo.Count)
	assert.Equal(t, true, questInfo.Isminequest)

	// 测试QuestList
	questList := &dnfv1.QuestList{
		Error:  0,
		Quests: []*dnfv1.QuestInfo{questInfo},
	}
	assert.NotNil(t, questList)
	assert.Equal(t, int32(0), questList.Error)
	assert.Len(t, questList.Quests, 1)

	// 测试QuestProgress
	questProgress := &dnfv1.QuestProgress{
		Qindex: 1,
		Count:  3,
	}
	assert.NotNil(t, questProgress)
	assert.Equal(t, int32(1), questProgress.Qindex)
	assert.Equal(t, int32(3), questProgress.Count)

	// 测试QuestReward
	questReward := &dnfv1.QuestReward{
		Qindex:   1,
		RewardId: 100,
	}
	assert.NotNil(t, questReward)
	assert.Equal(t, int32(1), questReward.Qindex)
	assert.Equal(t, int32(100), questReward.RewardId)

	// 测试QuestStatus
	questStatus := &dnfv1.QuestStatus{
		Qindex: 1,
		State:  1,
	}
	assert.NotNil(t, questStatus)
	assert.Equal(t, int32(1), questStatus.Qindex)
	assert.Equal(t, int32(1), questStatus.State)

	// 测试QuestTrack
	questTrack := &dnfv1.QuestTrack{
		Qindex: 1,
		Track:  true,
	}
	assert.NotNil(t, questTrack)
	assert.Equal(t, int32(1), questTrack.Qindex)
	assert.Equal(t, true, questTrack.Track)
}

// 测试成就相关消息
func testAchievementMessages(t *testing.T) {
	// 测试AchievementComplete
	achievementComplete := &dnfv1.AchievementComplete{
		Aid: 1,
	}
	assert.NotNil(t, achievementComplete)
	assert.Equal(t, int32(1), achievementComplete.Aid)

	// 测试AchievementList
	achievementList := &dnfv1.AchievementList{
		Error:        0,
		Achievements: []*dnfv1.AchievementInfo{},
	}
	assert.NotNil(t, achievementList)
	assert.Equal(t, int32(0), achievementList.Error)

	// 测试AchievementStatus
	achievementStatus := &dnfv1.AchievementStatus{
		Aid:   1,
		State: 1,
	}
	assert.NotNil(t, achievementStatus)
	assert.Equal(t, int32(1), achievementStatus.Aid)
	assert.Equal(t, int32(1), achievementStatus.State)
}

// 测试商店相关消息
func testShopMessages(t *testing.T) {
	// 测试ShopBuy
	shopBuy := &dnfv1.ShopBuy{
		ShopId: 1,
		ItemId:  100,
		Count:   5,
	}
	assert.NotNil(t, shopBuy)
	assert.Equal(t, int32(1), shopBuy.ShopId)
	assert.Equal(t, int32(100), shopBuy.ItemId)
	assert.Equal(t, int32(5), shopBuy.Count)

	// 测试ShopInfo
	shopInfo := &dnfv1.ShopInfo{
		ShopId: 1,
		Name:   "Test Shop",
		Items:  []*dnfv1.ShopItem{},
	}
	assert.NotNil(t, shopInfo)
	assert.Equal(t, int32(1), shopInfo.ShopId)
	assert.Equal(t, "Test Shop", shopInfo.Name)

	// 测试ShopItem
	shopItem := &dnfv1.ShopItem{
		ItemId:      100,
		Price:       1000,
		Count:       10,
		MaxBuy:      5,
		RestockTime: 1234567890,
	}
	assert.NotNil(t, shopItem)
	assert.Equal(t, int32(100), shopItem.ItemId)
	assert.Equal(t, int32(1000), shopItem.Price)
	assert.Equal(t, int32(10), shopItem.Count)
	assert.Equal(t, int32(5), shopItem.MaxBuy)
	assert.Equal(t, int64(1234567890), shopItem.RestockTime)

	// 测试ShopList
	shopList := &dnfv1.ShopList{
		Error:  0,
		Shops:  []*dnfv1.ShopInfo{shopInfo},
	}
	assert.NotNil(t, shopList)
	assert.Equal(t, int32(0), shopList.Error)
	assert.Len(t, shopList.Shops, 1)

	// 测试ShopRestock
	shopRestock := &dnfv1.ShopRestock{
		ShopId: 1,
	}
	assert.NotNil(t, shopRestock)
	assert.Equal(t, int32(1), shopRestock.ShopId)
}

// 测试邮件相关消息
func testMailMessages(t *testing.T) {
	// 测试MailList
	mailList := &dnfv1.MailList{
		Error: 0,
		Mails: []*dnfv1.MailInfo{},
	}
	assert.NotNil(t, mailList)
	assert.Equal(t, int32(0), mailList.Error)

	// 测试MailRead
	mailRead := &dnfv1.MailRead{
		MailId: 1,
	}
	assert.NotNil(t, mailRead)
	assert.Equal(t, int32(1), mailRead.MailId)

	// 测试MailSend
	mailSend := &dnfv1.MailSend{
		Receiver:  "Test Receiver",
		Title:     "Test Title",
		Content:   "Test Content",
		Attachments: []*dnfv1.SelectedItem{},
	}
	assert.NotNil(t, mailSend)
	assert.Equal(t, "Test Receiver", mailSend.Receiver)
	assert.Equal(t, "Test Title", mailSend.Title)
	assert.Equal(t, "Test Content", mailSend.Content)
}

// 测试排行榜相关消息
func testRankMessages(t *testing.T) {
	// 测试RankInfo
	rankInfo := &dnfv1.RankInfo{
		RankId: 1,
		Name:   "Test Player",
		Score:  1000,
		Rank:   1,
	}
	assert.NotNil(t, rankInfo)
	assert.Equal(t, int32(1), rankInfo.RankId)
	assert.Equal(t, "Test Player", rankInfo.Name)
	assert.Equal(t, int32(1000), rankInfo.Score)
	assert.Equal(t, int32(1), rankInfo.Rank)

	// 测试RankList
	rankList := &dnfv1.RankList{
		Error:  0,
		RankId: 1,
		Ranks:  []*dnfv1.RankInfo{rankInfo},
	}
	assert.NotNil(t, rankList)
	assert.Equal(t, int32(0), rankList.Error)
	assert.Equal(t, int32(1), rankList.RankId)
	assert.Len(t, rankList.Ranks, 1)

	// 测试RankReward
	rankReward := &dnfv1.RankReward{
		RankId:   1,
		RewardId: 100,
	}
	assert.NotNil(t, rankReward)
	assert.Equal(t, int32(1), rankReward.RankId)
	assert.Equal(t, int32(100), rankReward.RewardId)

	// 测试RankUpdate
	rankUpdate := &dnfv1.RankUpdate{
		RankId: 1,
		Score:  1000,
	}
	assert.NotNil(t, rankUpdate)
	assert.Equal(t, int32(1), rankUpdate.RankId)
	assert.Equal(t, int32(1000), rankUpdate.Score)
}

// 测试活动相关消息
func testActivityMessages(t *testing.T) {
	// 测试ActivityInfo
	activityInfo := &dnfv1.ActivityInfo{
		Aid:       1,
		Name:      "Test Activity",
		StartTime: 1234567890,
		EndTime:   1234567890,
		State:     1,
	}
	assert.NotNil(t, activityInfo)
	assert.Equal(t, int32(1), activityInfo.Aid)
	assert.Equal(t, "Test Activity", activityInfo.Name)
	assert.Equal(t, int64(1234567890), activityInfo.StartTime)
	assert.Equal(t, int64(1234567890), activityInfo.EndTime)
	assert.Equal(t, int32(1), activityInfo.State)

	// 测试ActivityList
	activityList := &dnfv1.ActivityList{
		Error:      0,
		Activities: []*dnfv1.ActivityInfo{activityInfo},
	}
	assert.NotNil(t, activityList)
	assert.Equal(t, int32(0), activityList.Error)
	assert.Len(t, activityList.Activities, 1)

	// 测试ActivityParticipate
	activityParticipate := &dnfv1.ActivityParticipate{
		Aid: 1,
	}
	assert.NotNil(t, activityParticipate)
	assert.Equal(t, int32(1), activityParticipate.Aid)

	// 测试ActivityReward
	activityReward := &dnfv1.ActivityReward{
		Aid:      1,
		RewardId: 100,
	}
	assert.NotNil(t, activityReward)
	assert.Equal(t, int32(1), activityReward.Aid)
	assert.Equal(t, int32(100), activityReward.RewardId)
}
