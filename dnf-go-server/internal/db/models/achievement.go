package models

import (
	"time"
)

type AchievementRecord struct {
	ID            uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID        uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	AchievementID uint32    `gorm:"column:achievement_id;index;not null" json:"achievementId"`
	Progress      int32     `gorm:"column:progress;default:0" json:"progress"`
	Completed     bool      `gorm:"column:completed;default:false" json:"completed"`
	Rewarded      bool      `gorm:"column:rewarded;default:false" json:"rewarded"`
	CompleteTime  time.Time `gorm:"column:complete_time" json:"completeTime"`
	RewardTime    time.Time `gorm:"column:reward_time" json:"rewardTime"`
	CreateTime    time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime    time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (AchievementRecord) TableName() string {
	return "t_achievement_record"
}

type AchievementReward struct {
	ID            uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID        uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	AchievementID uint32    `gorm:"column:achievement_id;index;not null" json:"achievementId"`
	RewardType    uint32    `gorm:"column:reward_type;not null" json:"rewardType"`
	RewardIndex   uint32    `gorm:"column:reward_index;not null" json:"rewardIndex"`
	RewardCount   uint32    `gorm:"column:reward_count;not null" json:"rewardCount"`
	Claimed       bool      `gorm:"column:claimed;default:false" json:"claimed"`
	ClaimTime     time.Time `gorm:"column:claim_time" json:"claimTime"`
	CreateTime    time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
}

func (AchievementReward) TableName() string {
	return "t_achievement_reward"
}

type AchievementConfig struct {
	ID            uint64  `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	AchievementID uint32  `gorm:"column:achievement_id;uniqueIndex;not null" json:"achievementId"`
	Name          string  `gorm:"column:name;not null" json:"name"`
	Description   string  `gorm:"column:description" json:"description"`
	Type          uint32  `gorm:"column:type;not null" json:"type"`
	TargetValue   int32   `gorm:"column:target_value;not null" json:"targetValue"`
	RewardType    uint32  `gorm:"column:reward_type;not null" json:"rewardType"`
	RewardIndex   uint32  `gorm:"column:reward_index;not null" json:"rewardIndex"`
	RewardCount   uint32  `gorm:"column:reward_count;not null" json:"rewardCount"`
	BonusReward   bool    `gorm:"column:bonus_reward;default:false" json:"bonusReward"`
	Status        uint32  `gorm:"column:status;default:1" json:"status"`
	CreateTime    time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime    time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (AchievementConfig) TableName() string {
	return "t_achievement_config"
}
