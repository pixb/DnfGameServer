package models

import (
	"time"
)

type Party struct {
	ID           uint64    `gorm:"column:partyId;primaryKey;autoIncrement" json:"partyId"`
	LeaderID     uint64    `gorm:"column:leaderId;not null" json:"leaderId"`
	Name         string    `gorm:"column:name;size:50" json:"name"`
	MaxMembers   uint32    `gorm:"column:maxMembers;default:4" json:"maxMembers"`
	DungeonIndex uint32    `gorm:"column:dungeonIndex" json:"dungeonIndex"`
	RoomID       uint64    `gorm:"column:roomId" json:"roomId"`
	Status       uint32    `gorm:"column:status;default:0" json:"status"`
	CreateTime   time.Time `gorm:"column:createTime;autoCreateTime" json:"createTime"`
	UpdateTime   time.Time `gorm:"column:updateTime;autoUpdateTime" json:"updateTime"`
}

func (Party) TableName() string {
	return "t_party"
}

type PartyMember struct {
	ID       uint64    `gorm:"column:memberId;primaryKey;autoIncrement" json:"memberId"`
	PartyID  uint64    `gorm:"column:partyId;not null;index" json:"partyId"`
	RoleID   uint64    `gorm:"column:roleId;not null;uniqueIndex" json:"roleId"`
	PlayerID uint32    `gorm:"column:playerId" json:"playerId"`
	TeamType uint32    `gorm:"column:teamType;default:0" json:"teamType"`
	Status   uint32    `gorm:"column:status;default:0" json:"status"`
	JoinTime time.Time `gorm:"column:joinTime;autoCreateTime" json:"joinTime"`
}

func (PartyMember) TableName() string {
	return "t_party_member"
}
