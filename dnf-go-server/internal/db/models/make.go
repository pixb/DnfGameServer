package models

import "time"

type EmblemUpgrade struct {
	ID           uint64    `db:"id"`
	RoleID       uint64    `db:"role_id"`
	EmblemIndex  int       `db:"emblem_index"`
	Level        int       `db:"level"`
	TryCount     int       `db:"try_count"`
	SuccessCount int       `db:"success_count"`
	CostMoney    int       `db:"cost_money"`
	CostTalisman int       `db:"cost_talisman"`
	CreateTime   time.Time `db:"create_time"`
}

type AvatarCompose struct {
	ID          uint64    `db:"id"`
	RoleID      uint64    `db:"role_id"`
	AvatarGUIDs  string    `db:"avatar_guids"`
	ResultIndex int       `db:"result_index"`
	ResultGUID  uint64    `db:"result_guid"`
	CostMoney   int       `db:"cost_money"`
	CreateTime  time.Time `db:"create_time"`
}

type ItemProduction struct {
	ID          uint64    `db:"id"`
	RoleID      uint64    `db:"role_id"`
	SlotIndex   int       `db:"slot_index"`
	RecipeIndex int       `db:"recipe_index"`
	Count       int       `db:"count"`
	ResultIndex int       `db:"result_index"`
	ResultCount int       `db:"result_count"`
	CostMoney   int       `db:"cost_money"`
	CreateTime  time.Time `db:"create_time"`
}

type ItemCombine struct {
	ID           uint64    `db:"id"`
	RoleID       uint64    `db:"role_id"`
	TargetIndex  int       `db:"target_index"`
	MaterialList string    `db:"material_list"`
	Count        int       `db:"count"`
	ResultGUID   uint64    `db:"result_guid"`
	CostMoney    int       `db:"cost_money"`
	CreateTime   time.Time `db:"create_time"`
}

type ItemDisjoint struct {
	ID           uint64    `db:"id"`
	RoleID       uint64    `db:"role_id"`
	EquipGUIDs   string    `db:"equip_guids"`
	MaterialList string    `db:"material_list"`
	CreateTime   time.Time `db:"create_time"`
}

type CardCompose struct {
	ID          uint64    `db:"id"`
	RoleID      uint64    `db:"role_id"`
	CardList    string    `db:"card_list"`
	ResultIndex int       `db:"result_index"`
	ResultCount int       `db:"result_count"`
	CostMoney   int       `db:"cost_money"`
	CreateTime  time.Time `db:"create_time"`
}

type WardrobeSlot struct {
	ID        uint64    `db:"id"`
	RoleID    uint64    `db:"role_id"`
	SlotIndex int       `db:"slot_index"`
	AvatarGUID uint64    `db:"avatar_guid"`
	CreateTime time.Time `db:"create_time"`
	UpdateTime time.Time `db:"update_time"`
}
