package store

// BagExpand 背包扩容记录
type BagExpand struct {
	BaseModel

	RoleID   uint64 // 角色ID
	BagType  int32  // 背包类型(1=普通 2=仓库)
	Capacity int32  // 扩容槽位数(基础容量之上)
}

// FindBagExpand 查询背包扩容
type FindBagExpand struct {
	FindBase

	RoleID  uint64
	BagType *int32
}

// UpdateBagExpand 更新背包扩容
type UpdateBagExpand struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Capacity *int32
}
