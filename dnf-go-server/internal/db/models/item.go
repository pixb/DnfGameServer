package models

import (
	"time"
)

// BagItem 背包物品表
type BagItem struct {
	ID      uint64 `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID     int64  `gorm:"column:uid;index;not null" json:"uid"`
	GUID    uint64 `gorm:"column:guid;uniqueIndex;not null" json:"guid"`
	ItemID  uint32 `gorm:"column:item_id;not null" json:"itemId"`
	BagType int32  `gorm:"column:bag_type;not null" json:"bagType"` // 1:装备 2:消耗品 3:材料 4:任务
	Slot    int32  `gorm:"column:slot;not null" json:"slot"`
	Count   int32  `gorm:"column:count;default:1" json:"count"`
	// 装备特有字段
	IsEquipment    bool      `gorm:"column:is_equipment;default:false" json:"isEquipment"`
	ReinforceLevel int32     `gorm:"column:reinforce_level;default:0" json:"reinforceLevel"`
	Durability     int32     `gorm:"column:durability;default:100" json:"durability"`
	MaxDurability  int32     `gorm:"column:max_durability;default:100" json:"maxDurability"`
	EquipSlot      int32     `gorm:"column:equip_slot;default:0" json:"equipSlot"`
	Options        string    `gorm:"column:options;type:text" json:"options"` // JSON格式存储装备属性
	CreateTime     time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
	UpdateTime     time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (BagItem) TableName() string {
	return "t_bag_item"
}

// RoleCurrency 角色货币表
type RoleCurrency struct {
	ID         uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UID        int64     `gorm:"column:uid;uniqueIndex;not null" json:"uid"`
	Gold       int64     `gorm:"column:gold;default:0" json:"gold"`
	Cera       int32     `gorm:"column:cera;default:0" json:"cera"`          // 点券
	BindCera   int32     `gorm:"column:bind_cera;default:0" json:"bindCera"` // 绑定点券
	UpdateTime time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (RoleCurrency) TableName() string {
	return "t_role_currency"
}

// ItemBase 物品基础表
type ItemBase struct {
	ItemID      uint32    `gorm:"column:item_id;primaryKey" json:"itemId"`
	ItemType    int32     `gorm:"column:item_type;not null" json:"itemType"` // 1:装备 2:消耗品 3:材料 4:货币 5:任务
	Name        string    `gorm:"column:name;size:64;not null" json:"name"`
	Description string    `gorm:"column:description;type:text" json:"description"`
	Quality     int32     `gorm:"column:quality;default:1" json:"quality"` // 1:普通 2:优秀 3:稀有 4:史诗 5:传说
	Level       int32     `gorm:"column:level;default:1" json:"level"`
	StackSize   int32     `gorm:"column:stack_size;default:1" json:"stackSize"`
	BuyPrice    int32     `gorm:"column:buy_price;default:0" json:"buyPrice"`
	SellPrice   int32     `gorm:"column:sell_price;default:0" json:"sellPrice"`
	Tradable    bool      `gorm:"column:tradable;default:true" json:"tradable"`
	JobLimit    int32     `gorm:"column:job_limit;default:0" json:"jobLimit"` // 0:无限制
	LevelLimit  int32     `gorm:"column:level_limit;default:1" json:"levelLimit"`
	CreateTime  time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
}

func (ItemBase) TableName() string {
	return "t_item_base"
}
