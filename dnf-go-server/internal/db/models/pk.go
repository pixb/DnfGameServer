package models

import (
	"time"
)

// PvpRecord PK 记录
type PvpRecord struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID     uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	MatchType  uint32    `gorm:"column:match_type;not null" json:"matchType"`
	Win        bool      `gorm:"column:win;not null" json:"win"`
	Score      int32     `gorm:"column:score;default:0" json:"score"`
	OpponentID uint64    `gorm:"column:opponent_id;index" json:"opponentId"`
	BattleTime time.Time `gorm:"column:battle_time;autoCreateTime" json:"battleTime"`
}

func (PvpRecord) TableName() string {
	return "t_pvp_record"
}

// RaidEntrance 副本入场记录
type RaidEntrance struct {
	ID                    uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID                uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	RaidIndex             uint32    `gorm:"column:raid_index;index;not null" json:"raidIndex"`
	DailyCharacterCount   uint32    `gorm:"column:daily_character_count;default:0" json:"dailyCharacterCount"`
	CharacterCount        uint32    `gorm:"column:character_count;default:0" json:"characterCount"`
	AccountCount          uint32    `gorm:"column:account_count;default:0" json:"accountCount"`
	DailyRewardCount      uint32    `gorm:"column:daily_reward_count;default:0" json:"dailyRewardCount"`
	RewardCount           uint32    `gorm:"column:reward_count;default:0" json:"rewardCount"`
	LastEnterTime         time.Time `gorm:"column:last_enter_time" json:"lastEnterTime"`
	CreateTime            time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime            time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (RaidEntrance) TableName() string {
	return "t_raid_entrance"
}

// PvpMatching PK 匹配
type PvpMatching struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	MatchingID uint64    `gorm:"column:matching_id;uniqueIndex;not null" json:"matchingId"`
	RoleID     uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	MatchType  uint32    `gorm:"column:match_type;not null" json:"matchType"`
	Status     uint32    `gorm:"column:status;default:0" json:"status"`
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (PvpMatching) TableName() string {
	return "t_pvp_matching"
}

// PvpStats PK 统计
type PvpStats struct {
	ID            uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID        uint64    `gorm:"column:role_id;uniqueIndex;not null" json:"roleId"`
	TotalMatches  uint32    `gorm:"column:total_matches;default:0" json:"totalMatches"`
	WinCount      uint32    `gorm:"column:win_count;default:0" json:"winCount"`
	LoseCount     uint32    `gorm:"column:lose_count;default:0" json:"loseCount"`
	TotalScore    int32     `gorm:"column:total_score;default:0" json:"totalScore"`
	MaxWinStreak  uint32    `gorm:"column:max_win_streak;default:0" json:"maxWinStreak"`
	CurrentStreak uint32    `gorm:"column:current_streak;default:0" json:"currentStreak"`
	CreateTime    time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime    time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (PvpStats) TableName() string {
	return "t_pvp_stats"
}

// PvpSeason PK 赛季
type PvpSeason struct {
	ID        uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	SeasonID  uint32    `gorm:"column:season_id;uniqueIndex;not null" json:"seasonId"`
	SeasonName string    `gorm:"column:season_name;size:50;not null" json:"seasonName"`
	StartTime time.Time `gorm:"column:start_time;not null" json:"startTime"`
	EndTime   time.Time `gorm:"column:end_time;not null" json:"endTime"`
	Status    uint32    `gorm:"column:status;default:0" json:"status"`
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (PvpSeason) TableName() string {
	return "t_pvp_season"
}

// PvpReward PK 奖励
type PvpReward struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID     uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	RewardID   uint32    `gorm:"column:reward_id;not null" json:"rewardId"`
	RewardName string    `gorm:"column:reward_name;size:100;not null" json:"rewardName"`
	Count      uint32    `gorm:"column:count;default:1" json:"count"`
	Claimed    bool      `gorm:"column:claimed;default:false" json:"claimed"`
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (PvpReward) TableName() string {
	return "t_pvp_reward"
}

// PvpMatchType PK 匹配类型
type PvpMatchType struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	MatchType  uint32    `gorm:"column:match_type;uniqueIndex;not null" json:"matchType"`
	TypeName   string    `gorm:"column:type_name;size:50;not null" json:"typeName"`
	MinLevel   uint32    `gorm:"column:min_level;default:1" json:"minLevel"`
	MaxLevel   uint32    `gorm:"column:max_level;default:100" json:"maxLevel"`
	MinPlayers uint32    `gorm:"column:min_players;default:1" json:"minPlayers"`
	MaxPlayers uint32    `gorm:"column:max_players;default:4" json:"maxPlayers"`
	Status     uint32    `gorm:"column:status;default:1" json:"status"`
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (PvpMatchType) TableName() string {
	return "t_pvp_match_type"
}
