package store

// InitialSkillPoints 建角初始技能点(2026-09-07 第四十四轮: 技能学习/升级消耗 SP)
const InitialSkillPoints = 100

// Role 角色
type Role struct {
	BaseModel

	AccountID  uint64
	RoleID     int32 // 槽位(1-4)
	Name       string
	Job        int32
	Level      int32
	Exp        int64
	Fatigue    int32
	MaxFatigue int32
	MapID      int32
	X          int32
	Y          int32
	Channel    int32
	SP         int32 // 技能点(2026-09-07 第四十四轮: 学习/升级技能消耗)
}

// RoleAttributes 角色属性
type RoleAttributes struct {
	RoleID uint64

	// 基础属性
	HP    int32
	MaxHP int32
	MP    int32
	MaxMP int32

	// 战斗属性
	Strength     int32
	Intelligence int32
	Vitality     int32
	Spirit       int32

	// 攻击属性
	PhysicalAttack  int32
	PhysicalDefense int32
	MagicAttack     int32
	MagicDefense    int32

	// 其他
	MoveSpeed   int32
	AttackSpeed int32
	CastSpeed   int32
}

// RoleCurrency 角色货币
type RoleCurrency struct {
	RoleID     uint64
	Gold       int64
	Coin       int64
	Fatigue    int32
	MaxFatigue int32
}

// FindRole 查询角色
type FindRole struct {
	FindBase

	AccountID *uint64
	RoleID    *int32
	Name      *string
	Job       *int32
	Level     *int32

	// NoCache 跳过角色缓存直接读库(2026-09-07 第四十四轮: 技能扣 SP 需实时值,
	// 避免直插 DB 修改(测试/运营)被缓存遮蔽)
	NoCache bool
}

// UpdateRole 更新角色
type UpdateRole struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Name    *string
	Level   *int32
	Exp     *int64
	Fatigue *int32
	MapID   *int32
	X       *int32
	Y       *int32
	SP      *int32
}

// DeleteRole 删除角色
type DeleteRole DeleteBase

// UpdateRoleAttributes 更新角色属性
type UpdateRoleAttributes struct {
	RoleID          uint64
	HP              *int32
	MaxHP           *int32
	MP              *int32
	MaxMP           *int32
	Strength        *int32
	Intelligence    *int32
	Vitality        *int32
	Spirit          *int32
	PhysicalAttack  *int32
	PhysicalDefense *int32
	MagicAttack     *int32
	MagicDefense    *int32
	MoveSpeed       *int32
	AttackSpeed     *int32
	CastSpeed       *int32
}
