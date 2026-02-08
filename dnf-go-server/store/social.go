package store

// Friend 好友
type Friend struct {
	BaseModel

	RoleID     uint64 // 拥有者角色ID
	FriendID   uint64 // 好友角色ID
	FriendName string // 好友名称(冗余)
	Intimacy   int32  // 亲密度
	Group      string // 分组
}

// FindFriend 查询好友
type FindFriend struct {
	FindBase

	RoleID   *uint64
	FriendID *uint64
}

// UpdateFriend 更新好友
type UpdateFriend struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Intimacy *int32
	Group    *string
}

// DeleteFriend 删除好友
type DeleteFriend DeleteBase

// Mail 邮件
type Mail struct {
	BaseModel

	SenderID    uint64
	SenderName  string
	ReceiverID  uint64
	Title       string
	Content     string
	Attachments string // JSON格式物品
	Gold        int64
	IsRead      bool
	IsClaimed   bool // 附件是否已领取
	ExpireAt    int64
}

// FindMail 查询邮件
type FindMail struct {
	FindBase

	ReceiverID *uint64
	IsRead     *bool
	IsClaimed  *bool
	ExpireAt   *int64
}

// UpdateMail 更新邮件
type UpdateMail struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	IsRead    *bool
	IsClaimed *bool
}

// DeleteMail 删除邮件
type DeleteMail DeleteBase
