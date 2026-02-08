package models

import (
	"time"
)

// RoleAttributes 角色属性表
type RoleAttributes struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID        int64     `gorm:"column:uid;uniqueIndex;not null" json:"uid"`
	Str        int32     `gorm:"column:str;default:0" json:"str"`
	Dex        int32     `gorm:"column:dex;default:0" json:"dex"`
	Vit        int32     `gorm:"column:vit;default:0" json:"vit"`
	Spr        int32     `gorm:"column:spr;default:0" json:"spr"`
	Hp         int32     `gorm:"column:hp;default:100" json:"hp"`
	MaxHp      int32     `gorm:"column:max_hp;default:100" json:"maxHp"`
	Mp         int32     `gorm:"column:mp;default:50" json:"mp"`
	MaxMp      int32     `gorm:"column:max_mp;default:50" json:"maxMp"`
	Atk        int32     `gorm:"column:atk;default:10" json:"atk"`
	Def        int32     `gorm:"column:def;default:5" json:"def"`
	MagicAtk   int32     `gorm:"column:magic_atk;default:8" json:"magicAtk"`
	MagicDef   int32     `gorm:"column:magic_def;default:4" json:"magicDef"`
	MoveSpeed  int32     `gorm:"column:move_speed;default:100" json:"moveSpeed"`
	AtkSpeed   int32     `gorm:"column:atk_speed;default:100" json:"atkSpeed"`
	CastSpeed  int32     `gorm:"column:cast_speed;default:100" json:"castSpeed"`
	SP         int32     `gorm:"column:sp;default:0" json:"sp"` // 技能点
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (RoleAttributes) TableName() string {
	return "t_role_attributes"
}

// RolePosition 角色位置表
type RolePosition struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID        int64     `gorm:"column:uid;uniqueIndex;not null" json:"uid"`
	MapID      int32     `gorm:"column:map_id;default:1" json:"mapId"`
	DungeonID  int32     `gorm:"column:dungeon_id;default:0" json:"dungeonId"`
	X          float32   `gorm:"column:x;default:0" json:"x"`
	Y          float32   `gorm:"column:y;default:0" json:"y"`
	Z          float32   `gorm:"column:z;default:0" json:"z"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (RolePosition) TableName() string {
	return "t_role_position"
}

// RoleSkill 角色技能表
type RoleSkill struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID        int64     `gorm:"column:uid;index;not null" json:"uid"`
	SkillID    int32     `gorm:"column:skill_id;not null" json:"skillId"`
	Level      int32     `gorm:"column:level;default:1" json:"level"`
	CreateTime time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (RoleSkill) TableName() string {
	return "t_role_skill"
}
