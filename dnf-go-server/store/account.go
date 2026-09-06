package store

// Account 账户
type Account struct {
	BaseModel

	OpenID      string
	AccountKey  string
	AuthKey     string
	LastLoginAt int64
	LastLoginIP string
	Authority   int32
	Status      int32 // 1=正常, 0=禁用(2026-09-06 第三十三轮: 与 HTTP auth_service 语义统一)
}

// FindAccount 查询账户
type FindAccount struct {
	FindBase

	OpenID     *string
	AccountKey *string
	Status     *int32
}

// UpdateAccount 更新账户
type UpdateAccount struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	AccountKey  *string
	AuthKey     *string
	LastLoginAt *int64
	LastLoginIP *string
	Authority   *int32
	Status      *int32
}

// DeleteAccount 删除账户
type DeleteAccount DeleteBase
