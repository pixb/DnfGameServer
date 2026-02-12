package models

import (
	"time"
)

type AdventureUnion struct {
	ID                          uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID                      uint64    `gorm:"column:role_id;uniqueIndex;not null" json:"roleId"`
	Name                        string    `gorm:"column:name;size:64;not null" json:"name"`
	Exp                         uint64    `gorm:"column:exp;default:0" json:"exp"`
	Level                       uint32    `gorm:"column:level;default:1" json:"level"`
	Day                         uint32    `gorm:"column:day;default:1" json:"day"`
	TypicalCharacterGUID         uint64    `gorm:"column:typical_character_guid" json:"typicalCharacterGuid"`
	LastChangeNameTime          time.Time `gorm:"column:last_change_name_time" json:"lastChangeNameTime"`
	ShareboardBackground        uint32    `gorm:"column:shareboard_background;default:0" json:"shareboardBackground"`
	ShareboardFrame             uint32    `gorm:"column:shareboard_frame;default:0" json:"shareboardFrame"`
	ShareboardShowAntiEvilScore bool      `gorm:"column:shareboard_show_antievil_score;default:false" json:"shareboardShowAntiEvilScore"`
	AutoSearchCount             uint32    `gorm:"column:auto_search_count;default:0" json:"autoSearchCount"`
	ShareboardTotalAntiEvilScore uint32  `gorm:"column:shareboard_total_antievil_score;default:0" json:"shareboardTotalAntiEvilScore"`
	ShareboardAntiEvilScoreRefresh bool   `gorm:"column:shareboard_antievil_score_refresh;default:false" json:"shareboardAntiEvilScoreRefresh"`
	IsAdventureCondition        bool      `gorm:"column:is_adventure_condition;default:false" json:"isAdventureCondition"`
	CreateTime                  time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime                  time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (AdventureUnion) TableName() string {
	return "t_adventure_union"
}

type AdventureUnionExpedition struct {
	ID            uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID        uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	ExpeditionID  uint32    `gorm:"column:expedition_id;not null" json:"expeditionId"`
	ExpeditionType uint32   `gorm:"column:expedition_type;not null" json:"expeditionType"`
	Status        uint32    `gorm:"column:status;default:0" json:"status"`
	StartTime     time.Time `gorm:"column:start_time" json:"startTime"`
	EndTime       time.Time `gorm:"column:end_time" json:"endTime"`
	RewardClaimed bool      `gorm:"column:reward_claimed;default:false" json:"rewardClaimed"`
	CreateTime    time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime    time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (AdventureUnionExpedition) TableName() string {
	return "t_adventure_union_expedition"
}

type AdventureUnionSubdue struct {
	ID             uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID         uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	SubdueID       uint32    `gorm:"column:subdue_id;not null" json:"subdueId"`
	SubdueType     uint32    `gorm:"column:subdue_type;not null" json:"subdueType"`
	CharacterGUID  uint64    `gorm:"column:character_guid" json:"characterGuid"`
	Status         uint32    `gorm:"column:status;default:0" json:"status"`
	StartTime      time.Time `gorm:"column:start_time" json:"startTime"`
	EndTime        time.Time `gorm:"column:end_time" json:"endTime"`
	RewardClaimed  bool      `gorm:"column:reward_claimed;default:false" json:"rewardClaimed"`
	CreateTime     time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime     time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (AdventureUnionSubdue) TableName() string {
	return "t_adventure_union_subdue"
}

type AdventureUnionCollection struct {
	ID             uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID         uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	CollectionID   uint32    `gorm:"column:collection_id;not null" json:"collectionId"`
	Progress       uint32    `gorm:"column:progress;default:0" json:"progress"`
	Completed      bool      `gorm:"column:completed;default:false" json:"completed"`
	RewardClaimed  bool      `gorm:"column:reward_claimed;default:false" json:"rewardClaimed"`
	CreateTime     time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime     time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (AdventureUnionCollection) TableName() string {
	return "t_adventure_union_collection"
}

type AdventureUnionShareboardSlot struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID     uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	SlotID     uint32    `gorm:"column:slot_id;not null" json:"slotId"`
	SlotType   uint32    `gorm:"column:slot_type;not null" json:"slotType"`
	ItemID     uint32    `gorm:"column:item_id" json:"itemId"`
	ItemCount  uint32    `gorm:"column:item_count" json:"itemCount"`
	Show       bool      `gorm:"column:show;default:false" json:"show"`
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (AdventureUnionShareboardSlot) TableName() string {
	return "t_adventure_union_shareboard_slot"
}

type AdventureReap struct {
	ID          uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID      uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	ReapID      uint32    `gorm:"column:reap_id;not null" json:"reapId"`
	RewardType  uint32    `gorm:"column:reward_type;not null" json:"rewardType"`
	RewardIndex uint32    `gorm:"column:reward_index;not null" json:"rewardIndex"`
	RewardCount uint32    `gorm:"column:reward_count;not null" json:"rewardCount"`
	Claimed     bool      `gorm:"column:claimed;default:false" json:"claimed"`
	ClaimTime   time.Time `gorm:"column:claim_time" json:"claimTime"`
	CreateTime  time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
}

func (AdventureReap) TableName() string {
	return "t_adventure_reap"
}

type AdventureUnionLevelReward struct {
	ID          uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	RoleID      uint64    `gorm:"column:role_id;index;not null" json:"roleId"`
	Level       uint32    `gorm:"column:level;not null" json:"level"`
	RewardType  uint32    `gorm:"column:reward_type;not null" json:"rewardType"`
	RewardIndex uint32    `gorm:"column:reward_index;not null" json:"rewardIndex"`
	RewardCount uint32    `gorm:"column:reward_count;not null" json:"rewardCount"`
	Claimed     bool      `gorm:"column:claimed;default:false" json:"claimed"`
	ClaimTime   time.Time `gorm:"column:claim_time" json:"claimTime"`
	CreateTime  time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
}

func (AdventureUnionLevelReward) TableName() string {
	return "t_adventure_union_level_reward"
}
