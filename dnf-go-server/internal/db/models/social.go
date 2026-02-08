package models

import (
	"time"
)

// Quest 任务表
type Quest struct {
	ID            uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	QuestID       int32     `gorm:"column:quest_id;index;not null" json:"questId"`
	QuestType     int32     `gorm:"column:quest_type;not null" json:"questType"` // 1:主线 2:支线 3:日常 4:活动
	Name          string    `gorm:"column:name;size:64;not null" json:"name"`
	Description   string    `gorm:"column:description;type:text" json:"description"`
	RequiredLevel int32     `gorm:"column:required_level;default:1" json:"requiredLevel"`
	PreQuestID    int32     `gorm:"column:pre_quest_id;default:0" json:"preQuestId"`
	Rewards       string    `gorm:"column:rewards;type:text" json:"rewards"` // JSON格式
	CreateTime    time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
}

func (Quest) TableName() string {
	return "t_quest"
}

// RoleQuest 角色任务进度表
type RoleQuest struct {
	ID           uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID          int64     `gorm:"column:uid;index;not null" json:"uid"`
	QuestID      int32     `gorm:"column:quest_id;not null" json:"questId"`
	State        int32     `gorm:"column:state;default:1" json:"state"`           // 1:未接受 2:进行中 3:已完成 4:已领奖
	Objectives   string    `gorm:"column:objectives;type:text" json:"objectives"` // JSON格式存储进度
	AcceptTime   int64     `gorm:"column:accept_time;default:0" json:"acceptTime"`
	CompleteTime int64     `gorm:"column:complete_time;default:0" json:"completeTime"`
	RewardTime   int64     `gorm:"column:reward_time;default:0" json:"rewardTime"`
	CreateTime   time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime   time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (RoleQuest) TableName() string {
	return "t_role_quest"
}

// Friend 好友表
type Friend struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID        int64     `gorm:"column:uid;index;not null" json:"uid"`
	FriendUID  int64     `gorm:"column:friend_uid;index;not null" json:"friendUid"`
	Intimacy   int32     `gorm:"column:intimacy;default:0" json:"intimacy"`
	State      int32     `gorm:"column:state;default:1" json:"state"` // 1:好友 2:黑名单
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (Friend) TableName() string {
	return "t_friend"
}

// FriendRequest 好友申请表
type FriendRequest struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	FromUID    int64     `gorm:"column:from_uid;index;not null" json:"fromUid"`
	ToUID      int64     `gorm:"column:to_uid;index;not null" json:"toUid"`
	State      int32     `gorm:"column:state;default:0" json:"state"` // 0:待处理 1:已接受 2:已拒绝
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (FriendRequest) TableName() string {
	return "t_friend_request"
}
