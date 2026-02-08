package models

import (
	"time"
)

// Guild 公会表
type Guild struct {
	ID             uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	GuildID        int64     `gorm:"column:guild_id;uniqueIndex;not null" json:"guildId"`
	Name           string    `gorm:"column:name;size:32;uniqueIndex;not null" json:"name"`
	Level          int32     `gorm:"column:level;default:1" json:"level"`
	Exp            int64     `gorm:"column:exp;default:0" json:"exp"`
	Notice         string    `gorm:"column:notice;size:256" json:"notice"`
	MasterUID      int64     `gorm:"column:master_uid;not null" json:"masterUid"`
	MemberCount    int32     `gorm:"column:member_count;default:1" json:"memberCount"`
	MaxMemberCount int32     `gorm:"column:max_member_count;default:30" json:"maxMemberCount"`
	Funds          int64     `gorm:"column:funds;default:0" json:"funds"`
	CreateTime     time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime     time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (Guild) TableName() string {
	return "t_guild"
}

// GuildMember 公会成员表
type GuildMember struct {
	ID                uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	GuildID           int64     `gorm:"column:guild_id;index;not null" json:"guildId"`
	UID               int64     `gorm:"column:uid;index;not null" json:"uid"`
	Position          int32     `gorm:"column:position;default:1" json:"position"` // 1:成员 2:精英 3:官员 4:副会长 5:会长
	Contribution      int32     `gorm:"column:contribution;default:0" json:"contribution"`
	TotalContribution int32     `gorm:"column:total_contribution;default:0" json:"totalContribution"`
	JoinTime          time.Time `gorm:"column:join_time;autoCreateTime" json:"joinTime"`
	UpdateTime        time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (GuildMember) TableName() string {
	return "t_guild_member"
}

// GuildSkill 公会技能表
type GuildSkill struct {
	ID          uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	GuildID     int64     `gorm:"column:guild_id;index;not null" json:"guildId"`
	SkillID     int32     `gorm:"column:skill_id;not null" json:"skillId"`
	Level       int32     `gorm:"column:level;default:0" json:"level"`
	MaxLevel    int32     `gorm:"column:max_level;default:10" json:"maxLevel"`
	EffectValue int32     `gorm:"column:effect_value;default:0" json:"effectValue"`
	CreateTime  time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime  time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (GuildSkill) TableName() string {
	return "t_guild_skill"
}

// GuildJoinRequest 公会加入申请表
type GuildJoinRequest struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	GuildID    int64     `gorm:"column:guild_id;index;not null" json:"guildId"`
	UID        int64     `gorm:"column:uid;index;not null" json:"uid"`
	State      int32     `gorm:"column:state;default:0" json:"state"` // 0:待处理 1:已接受 2:已拒绝
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (GuildJoinRequest) TableName() string {
	return "t_guild_join_request"
}

// Mail 邮件表
type Mail struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID        int64     `gorm:"column:uid;index;not null" json:"uid"`
	SenderName string    `gorm:"column:sender_name;size:32" json:"senderName"`
	Title      string    `gorm:"column:title;size:64;not null" json:"title"`
	Content    string    `gorm:"column:content;type:text" json:"content"`
	Items      string    `gorm:"column:items;type:text" json:"items"` // JSON格式
	Gold       int32     `gorm:"column:gold;default:0" json:"gold"`
	IsRead     bool      `gorm:"column:is_read;default:false" json:"isRead"`
	IsReceived bool      `gorm:"column:is_received;default:false" json:"isReceived"`
	ExpireTime int64     `gorm:"column:expire_time" json:"expireTime"`
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
}

func (Mail) TableName() string {
	return "t_mail"
}
