package store

// BagItem 背包物品
type BagItem struct {
	BaseModel

	RoleID       uint64
	ItemID       int32  // 物品模板ID
	GridIndex    int32  // 格子索引
	Count        int32  // 数量
	IsEquiped    bool   // 是否已装备
	BindType     int32  // 绑定类型
	Durability   int32  // 耐久度
	EnhanceLevel int32  // 强化等级
	Attributes   string // 附加属性(JSON)
}

// FindBagItem 查询物品
type FindBagItem struct {
	FindBase

	RoleID    *uint64
	ItemID    *int32
	GridIndex *int32
}

// UpdateBagItem 更新物品
type UpdateBagItem struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	GridIndex    *int32
	Count        *int32
	IsEquiped    *bool
	Durability   *int32
	EnhanceLevel *int32
	Attributes   *string
}

// DeleteBagItem 删除物品
type DeleteBagItem DeleteBase
