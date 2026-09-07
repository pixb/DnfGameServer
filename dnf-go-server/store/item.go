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

// ItemTemplate 物品模板配置
// 2026-09-08 第七十三轮: 物品模板体系实化——模板元数据(名称/类型/等级/默认绑定/售价/描述)
type ItemTemplate struct {
	ItemID      int32  `json:"item_id"`
	Name        string `json:"name"`
	ItemType    int32  `json:"item_type"`
	Level       int32  `json:"level"`
	BindType    int32  `json:"bind_type"`
	SellPrice   int32  `json:"sell_price"`
	Description string `json:"description"`
}
