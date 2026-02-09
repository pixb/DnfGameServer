package store

// Guild 公会
type Guild struct {
	BaseModel

	Name        string
	Level       int32
	Exp         int64
	Notice      string
	LeaderID    uint64
	MemberCount int32
	MaxMembers  int32
	Fund        int64
}

// GuildMember 公会成员
type GuildMember struct {
	BaseModel

	GuildID      uint64
	RoleID       uint64
	Position     int32 // 职位: 0=成员, 1=精英, 2=副会长, 3=会长
	Contribution int64
}

// FindGuild 查询公会
type FindGuild struct {
	FindBase

	Name     *string
	LeaderID *uint64
}

// FindGuildMember 查询公会成员
type FindGuildMember struct {
	FindBase

	GuildID *uint64
	RoleID  *uint64
}

// UpdateGuild 更新公会
type UpdateGuild struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Name        *string
	Level       *int32
	Exp         *int64
	Notice      *string
	LeaderID    *uint64
	MemberCount *int32
	MaxMembers  *int32
	Fund        *int64
}

// UpdateGuildMember 更新公会成员
type UpdateGuildMember struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Position     *int32
	Contribution *int64
}

// DeleteGuild 删除公会
type DeleteGuild DeleteBase

// DeleteGuildMember 删除公会成员
type DeleteGuildMember DeleteBase
