package test

import (
	"testing"
	"time"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 公会系统测试
func TestBatch45GuildApplicant(t *testing.T) {
	original := &dnfv1.GuildApplicant{
		Guid:           123456789,
		Growtype:       1,
		Secondgrowtype: 2,
		Job:            3,
		Level:          50,
		Name:           "TestUser",
		Creditscore:    1000,
		Characterframe: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildApplicant{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Guid != original.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", decoded.Guid, original.Guid)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
}

func TestBatch45GuildAttendReward(t *testing.T) {
	original := &dnfv1.GuildAttendReward{
		Index: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildAttendReward{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
}

func TestBatch45GuildAuctionGetChar(t *testing.T) {
	original := &dnfv1.GuildAuctionGetChar{
		Index: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildAuctionGetChar{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
}

func TestBatch45GuildAuctionItem(t *testing.T) {
	original := &dnfv1.GuildAuctionItem{
		Index: 1,
		Count: 10,
		Level: 50,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildAuctionItem{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch45GuildAuctionRewardInfo(t *testing.T) {
	original := &dnfv1.GuildAuctionRewardInfo{
		Index: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildAuctionRewardInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
}

func TestBatch45GuildBingoChallengeHistoryInfo(t *testing.T) {
	original := &dnfv1.GuildBingoChallengeHistoryInfo{
		Index: 1,
		Count: 5,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildBingoChallengeHistoryInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch45GuildBingoChallengersInfo(t *testing.T) {
	original := &dnfv1.GuildBingoChallengersInfo{
		Index: 1,
		Count: 5,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildBingoChallengersInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch45GuildBingoMapClearPastRewardInfo(t *testing.T) {
	original := &dnfv1.GuildBingoMapClearPastRewardInfo{
		Index: 1,
		Count: 5,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildBingoMapClearPastRewardInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch45GuildBingoMapClearRewardInfo(t *testing.T) {
	original := &dnfv1.GuildBingoMapClearRewardInfo{
		Index: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildBingoMapClearRewardInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
}

func TestBatch45GuildBingoRewardInfo(t *testing.T) {
	original := &dnfv1.GuildBingoRewardInfo{
		Index: 1,
		Count: 5,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildBingoRewardInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Index != original.Index {
		t.Errorf("Index mismatch: got %d, want %d", decoded.Index, original.Index)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch45GuildApplyList(t *testing.T) {
	original := &dnfv1.GuildApplyList{
		Error: 0,
		Applicants: []*dnfv1.GuildApplicant{
			{
				Guid:           123456789,
				Growtype:       1,
				Secondgrowtype: 2,
				Job:            3,
				Level:          50,
				Name:           "TestUser",
				Creditscore:    1000,
				Characterframe: 1,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildApplyList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Applicants) != len(original.Applicants) {
		t.Errorf("Applicants length mismatch: got %d, want %d", len(decoded.Applicants), len(original.Applicants))
	}
}

func TestBatch45GuildBossInfo(t *testing.T) {
	original := &dnfv1.GuildBossInfo{
		BossId:         1,
		Hp:             10000,
		MaxHp:          20000,
		LastAttackTime: time.Now().Unix(),
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildBossInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.BossId != original.BossId {
		t.Errorf("BossId mismatch: got %d, want %d", decoded.BossId, original.BossId)
	}
	if decoded.Hp != original.Hp {
		t.Errorf("Hp mismatch: got %d, want %d", decoded.Hp, original.Hp)
	}
}

func TestBatch45GuildCreate(t *testing.T) {
	original := &dnfv1.GuildCreate{
		Name:   "TestGuild",
		Notice: "Welcome to TestGuild",
		Level:  1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildCreate{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
	if decoded.Notice != original.Notice {
		t.Errorf("Notice mismatch: got %s, want %s", decoded.Notice, original.Notice)
	}
}

func TestBatch45GuildDetail(t *testing.T) {
	original := &dnfv1.GuildDetail{
		GuildId:        1,
		Name:           "TestGuild",
		Notice:         "Welcome to TestGuild",
		Level:          1,
		MemberCount:    10,
		MaxMemberCount: 50,
		MasterName:     "GuildMaster",
		MasterGuid:     123456789,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildDetail{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.GuildId != original.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", decoded.GuildId, original.GuildId)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
}

func TestBatch45GuildList(t *testing.T) {
	original := &dnfv1.GuildList{
		Error: 0,
		Guilds: []*dnfv1.GuildDetail{
			{
				GuildId:        1,
				Name:           "TestGuild",
				Notice:         "Welcome to TestGuild",
				Level:          1,
				MemberCount:    10,
				MaxMemberCount: 50,
				MasterName:     "GuildMaster",
				MasterGuid:     123456789,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Guilds) != len(original.Guilds) {
		t.Errorf("Guilds length mismatch: got %d, want %d", len(decoded.Guilds), len(original.Guilds))
	}
}

func TestBatch45GuildMemberList(t *testing.T) {
	original := &dnfv1.GuildMemberList{
		Error: 0,
		Members: []*dnfv1.GuildMember{
			{
				Guid:         123456789,
				Name:         "TestMember",
				Job:          1,
				Level:        50,
				Position:     1,
				JoinTime:     int32(time.Now().Unix()),
				Contribution: 100,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildMemberList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Members) != len(original.Members) {
		t.Errorf("Members length mismatch: got %d, want %d", len(decoded.Members), len(original.Members))
	}
}

func TestBatch45GuildMember(t *testing.T) {
	original := &dnfv1.GuildMember{
		Guid:         123456789,
		Name:         "TestMember",
		Job:          1,
		Level:        50,
		Position:     1,
		JoinTime:     int32(time.Now().Unix()),
		Contribution: 100,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildMember{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Guid != original.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", decoded.Guid, original.Guid)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
}

func TestBatch45GuildNotice(t *testing.T) {
	original := &dnfv1.GuildNotice{
		Notice: "Welcome to TestGuild",
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildNotice{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Notice != original.Notice {
		t.Errorf("Notice mismatch: got %s, want %s", decoded.Notice, original.Notice)
	}
}

func TestBatch45GuildRecruitment(t *testing.T) {
	original := &dnfv1.GuildRecruitment{
		Content: "Looking for active members",
		Level:   50,
		Job:     1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildRecruitment{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Content != original.Content {
		t.Errorf("Content mismatch: got %s, want %s", decoded.Content, original.Content)
	}
	if decoded.Level != original.Level {
		t.Errorf("Level mismatch: got %d, want %d", decoded.Level, original.Level)
	}
}

func TestBatch45GuildUpgrade(t *testing.T) {
	original := &dnfv1.GuildUpgrade{
		Level: 2,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildUpgrade{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Level != original.Level {
		t.Errorf("Level mismatch: got %d, want %d", decoded.Level, original.Level)
	}
}

func TestBatch45GuildWarInfo(t *testing.T) {
	original := &dnfv1.GuildWarInfo{
		GuildId:      1,
		EnemyGuildId: 2,
		State:        1,
		StartTime:    time.Now().Unix(),
		EndTime:      time.Now().Unix() + 3600,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.GuildWarInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.GuildId != original.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", decoded.GuildId, original.GuildId)
	}
	if decoded.EnemyGuildId != original.EnemyGuildId {
		t.Errorf("EnemyGuildId mismatch: got %d, want %d", decoded.EnemyGuildId, original.EnemyGuildId)
	}
}

// 任务系统测试
func TestBatch45QuestAccept(t *testing.T) {
	original := &dnfv1.QuestAccept{
		Qindex: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestAccept{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Qindex != original.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", decoded.Qindex, original.Qindex)
	}
}

func TestBatch45QuestComplete(t *testing.T) {
	original := &dnfv1.QuestComplete{
		Qindex: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestComplete{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Qindex != original.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", decoded.Qindex, original.Qindex)
	}
}

func TestBatch45QuestInfo(t *testing.T) {
	original := &dnfv1.QuestInfo{
		Qindex:      1,
		State:       1,
		Count:       5,
		Isminequest: true,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Qindex != original.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", decoded.Qindex, original.Qindex)
	}
	if decoded.State != original.State {
		t.Errorf("State mismatch: got %d, want %d", decoded.State, original.State)
	}
}

func TestBatch45QuestList(t *testing.T) {
	original := &dnfv1.QuestList{
		Error: 0,
		Quests: []*dnfv1.QuestInfo{
			{
				Qindex:      1,
				State:       1,
				Count:       5,
				Isminequest: true,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Quests) != len(original.Quests) {
		t.Errorf("Quests length mismatch: got %d, want %d", len(decoded.Quests), len(original.Quests))
	}
}

func TestBatch45QuestProgress(t *testing.T) {
	original := &dnfv1.QuestProgress{
		Qindex: 1,
		Count:  5,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestProgress{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Qindex != original.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", decoded.Qindex, original.Qindex)
	}
	if decoded.Count != original.Count {
		t.Errorf("Count mismatch: got %d, want %d", decoded.Count, original.Count)
	}
}

func TestBatch45QuestReward(t *testing.T) {
	original := &dnfv1.QuestReward{
		Qindex:   1,
		RewardId: 1001,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestReward{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Qindex != original.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", decoded.Qindex, original.Qindex)
	}
	if decoded.RewardId != original.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", decoded.RewardId, original.RewardId)
	}
}

func TestBatch45QuestStatus(t *testing.T) {
	original := &dnfv1.QuestStatus{
		Qindex: 1,
		State:  1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestStatus{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Qindex != original.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", decoded.Qindex, original.Qindex)
	}
	if decoded.State != original.State {
		t.Errorf("State mismatch: got %d, want %d", decoded.State, original.State)
	}
}

func TestBatch45QuestTrack(t *testing.T) {
	original := &dnfv1.QuestTrack{
		Qindex: 1,
		Track:  true,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.QuestTrack{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Qindex != original.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", decoded.Qindex, original.Qindex)
	}
	if decoded.Track != original.Track {
		t.Errorf("Track mismatch: got %v, want %v", decoded.Track, original.Track)
	}
}

// 成就系统测试
func TestBatch45AchievementComplete(t *testing.T) {
	original := &dnfv1.AchievementComplete{
		Aid: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.AchievementComplete{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Aid != original.Aid {
		t.Errorf("Aid mismatch: got %d, want %d", decoded.Aid, original.Aid)
	}
}

func TestBatch45AchievementList(t *testing.T) {
	original := &dnfv1.AchievementList{
		Error: 0,
		Achievements: []*dnfv1.AchievementInfo{
			{
				AchievementId: 1,
				Name:          "Test Achievement",
				Description:   "This is a test achievement",
				CategoryId:    1,
				Points:        10,
				Completed:     false,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.AchievementList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Achievements) != len(original.Achievements) {
		t.Errorf("Achievements length mismatch: got %d, want %d", len(decoded.Achievements), len(original.Achievements))
	}
}

func TestBatch45AchievementStatus(t *testing.T) {
	original := &dnfv1.AchievementStatus{
		Aid:   1,
		State: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.AchievementStatus{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Aid != original.Aid {
		t.Errorf("Aid mismatch: got %d, want %d", decoded.Aid, original.Aid)
	}
	if decoded.State != original.State {
		t.Errorf("State mismatch: got %d, want %d", decoded.State, original.State)
	}
}

// 商店系统测试
func TestBatch45ShopBuy(t *testing.T) {
	original := &dnfv1.ShopBuy{
		ShopId: 1,
		ItemId: 1001,
		Count:  5,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ShopBuy{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.ShopId != original.ShopId {
		t.Errorf("ShopId mismatch: got %d, want %d", decoded.ShopId, original.ShopId)
	}
	if decoded.ItemId != original.ItemId {
		t.Errorf("ItemId mismatch: got %d, want %d", decoded.ItemId, original.ItemId)
	}
}

func TestBatch45ShopInfo(t *testing.T) {
	original := &dnfv1.ShopInfo{
		ShopId: 1,
		Name:   "TestShop",
		Items: []*dnfv1.ShopItem{
			{
				ItemId:      1001,
				Price:       100,
				Count:       5,
				MaxBuy:      10,
				RestockTime: time.Now().Unix(),
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ShopInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.ShopId != original.ShopId {
		t.Errorf("ShopId mismatch: got %d, want %d", decoded.ShopId, original.ShopId)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
}

func TestBatch45ShopList(t *testing.T) {
	original := &dnfv1.ShopList{
		Error: 0,
		Shops: []*dnfv1.ShopInfo{
			{
				ShopId: 1,
				Name:   "TestShop",
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ShopList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Shops) != len(original.Shops) {
		t.Errorf("Shops length mismatch: got %d, want %d", len(decoded.Shops), len(original.Shops))
	}
}

func TestBatch45ShopRestock(t *testing.T) {
	original := &dnfv1.ShopRestock{
		ShopId: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ShopRestock{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.ShopId != original.ShopId {
		t.Errorf("ShopId mismatch: got %d, want %d", decoded.ShopId, original.ShopId)
	}
}

// 邮件系统测试
func TestBatch45MailList(t *testing.T) {
	original := &dnfv1.MailList{
		Error: 0,
		Mails: []*dnfv1.MailInfo{
			{
				Guid:          123456789,
				Title:         "Test Mail",
				Content:       "This is a test mail",
				Type:          1,
				Status:        1,
				SendTime:      time.Now().Unix(),
				ExpireTime:    time.Now().Unix() + 86400,
				HasAttachment: true,
				IsRead:        false,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.MailList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Mails) != len(original.Mails) {
		t.Errorf("Mails length mismatch: got %d, want %d", len(decoded.Mails), len(original.Mails))
	}
}

func TestBatch45MailRead(t *testing.T) {
	original := &dnfv1.MailRead{
		MailId: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.MailRead{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.MailId != original.MailId {
		t.Errorf("MailId mismatch: got %d, want %d", decoded.MailId, original.MailId)
	}
}

func TestBatch45MailSend(t *testing.T) {
	original := &dnfv1.MailSend{
		Receiver: "TestUser",
		Title:    "Test Mail",
		Content:  "This is a test mail",
		Attachments: []*dnfv1.SelectedItem{
			{
				Guid:  123456789,
				Count: 1,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.MailSend{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Receiver != original.Receiver {
		t.Errorf("Receiver mismatch: got %s, want %s", decoded.Receiver, original.Receiver)
	}
	if decoded.Title != original.Title {
		t.Errorf("Title mismatch: got %s, want %s", decoded.Title, original.Title)
	}
}

// 排行榜系统测试
func TestBatch45RankInfo(t *testing.T) {
	original := &dnfv1.RankInfo{
		RankId: 1,
		Name:   "TestUser",
		Score:  1000,
		Rank:   1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RankInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.RankId != original.RankId {
		t.Errorf("RankId mismatch: got %d, want %d", decoded.RankId, original.RankId)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
}

func TestBatch45RankList(t *testing.T) {
	original := &dnfv1.RankList{
		Error:  0,
		RankId: 1,
		Ranks: []*dnfv1.RankInfo{
			{
				RankId: 1,
				Name:   "TestUser",
				Score:  1000,
				Rank:   1,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RankList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if decoded.RankId != original.RankId {
		t.Errorf("RankId mismatch: got %d, want %d", decoded.RankId, original.RankId)
	}
}

func TestBatch45RankReward(t *testing.T) {
	original := &dnfv1.RankReward{
		RankId:   1,
		RewardId: 1001,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RankReward{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.RankId != original.RankId {
		t.Errorf("RankId mismatch: got %d, want %d", decoded.RankId, original.RankId)
	}
	if decoded.RewardId != original.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", decoded.RewardId, original.RewardId)
	}
}

func TestBatch45RankUpdate(t *testing.T) {
	original := &dnfv1.RankUpdate{
		RankId: 1,
		Score:  1000,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.RankUpdate{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.RankId != original.RankId {
		t.Errorf("RankId mismatch: got %d, want %d", decoded.RankId, original.RankId)
	}
	if decoded.Score != original.Score {
		t.Errorf("Score mismatch: got %d, want %d", decoded.Score, original.Score)
	}
}

// 活动系统测试
func TestBatch45ActivityInfo(t *testing.T) {
	original := &dnfv1.ActivityInfo{
		Aid:       1,
		Name:      "Test Activity",
		StartTime: time.Now().Unix(),
		EndTime:   time.Now().Unix() + 86400,
		State:     1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ActivityInfo{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Aid != original.Aid {
		t.Errorf("Aid mismatch: got %d, want %d", decoded.Aid, original.Aid)
	}
	if decoded.Name != original.Name {
		t.Errorf("Name mismatch: got %s, want %s", decoded.Name, original.Name)
	}
}

func TestBatch45ActivityList(t *testing.T) {
	original := &dnfv1.ActivityList{
		Error: 0,
		Activities: []*dnfv1.ActivityInfo{
			{
				Aid:       1,
				Name:      "Test Activity",
				StartTime: time.Now().Unix(),
				EndTime:   time.Now().Unix() + 86400,
				State:     1,
			},
		},
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ActivityList{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Error != original.Error {
		t.Errorf("Error mismatch: got %d, want %d", decoded.Error, original.Error)
	}
	if len(decoded.Activities) != len(original.Activities) {
		t.Errorf("Activities length mismatch: got %d, want %d", len(decoded.Activities), len(original.Activities))
	}
}

func TestBatch45ActivityParticipate(t *testing.T) {
	original := &dnfv1.ActivityParticipate{
		Aid: 1,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ActivityParticipate{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Aid != original.Aid {
		t.Errorf("Aid mismatch: got %d, want %d", decoded.Aid, original.Aid)
	}
}

func TestBatch45ActivityReward(t *testing.T) {
	original := &dnfv1.ActivityReward{
		Aid:      1,
		RewardId: 1001,
	}

	data, err := proto.Marshal(original)
	if err != nil {
		t.Fatalf("Failed to marshal: %v", err)
	}

	decoded := &dnfv1.ActivityReward{}
	if err := proto.Unmarshal(data, decoded); err != nil {
		t.Fatalf("Failed to unmarshal: %v", err)
	}

	if decoded.Aid != original.Aid {
		t.Errorf("Aid mismatch: got %d, want %d", decoded.Aid, original.Aid)
	}
	if decoded.RewardId != original.RewardId {
		t.Errorf("RewardId mismatch: got %d, want %d", decoded.RewardId, original.RewardId)
	}
}
