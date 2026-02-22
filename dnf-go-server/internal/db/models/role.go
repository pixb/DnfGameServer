package models

import (
	"time"
)

// Role 角色模型
type Role struct {
	ID           int64     `gorm:"primaryKey;autoIncrement" json:"id"`
	AccountID    int64     `gorm:"index;not null" json:"accountId"`
	CharGUID     int64     `gorm:"type:bigint;uniqueIndex;not null" json:"charGuid"`
	Name         string    `gorm:"type:varchar(50);not null" json:"name"`
	Level        int       `gorm:"type:int;default:1" json:"level"`
	Job          int       `gorm:"type:int;default:0" json:"job"`
	GrowType     int       `gorm:"type:int;default:0" json:"growType"`
	SecGrowType  int       `gorm:"type:int;default:0" json:"secGrowType"`
	Exp          int64     `gorm:"type:bigint;default:0" json:"exp"`
	Gold         int64     `gorm:"type:bigint;default:0" json:"gold"`
	Hp           int       `gorm:"type:int;default:100" json:"hp"`
	Mp           int       `gorm:"type:int;default:100" json:"mp"`
	Strength     int       `gorm:"type:int;default:10" json:"strength"`
	Intelligence int       `gorm:"type:int;default:10" json:"intelligence"`
	Vitality     int       `gorm:"type:int;default:10" json:"vitality"`
	Spirit       int       `gorm:"type:int;default:10" json:"spirit"`
	Status       int       `gorm:"type:int;default:1" json:"status"` // 1:正常, 0:删除
	CreatedAt    time.Time `json:"createdAt"`
	LastPlayAt   time.Time `json:"lastPlayAt"`

	// 关联
	Account *Account `gorm:"foreignKey:AccountID" json:"account,omitempty"`
}

// TableName 设置表名
func (Role) TableName() string {
	return "roles"
}

// RoleResponse 角色响应
type RoleResponse struct {
	CharGUID    int64  `json:"charGuid"`
	Name        string `json:"name"`
	Level       int    `json:"level"`
	Job         int    `json:"job"`
	GrowType    int    `json:"growType"`
	SecGrowType int    `json:"secGrowType"`
	Status      int    `json:"status"`
}
