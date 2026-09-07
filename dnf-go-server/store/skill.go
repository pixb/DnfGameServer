package store

// Skill 技能定义
type Skill struct {
	BaseModel

	SkillID       int32
	Name          string
	Description   string
	Level         int32
	MaxLevel      int32
	SP            int32
	TP            int32
	Type          int32 // 0=主动, 1=被动, 2=BUFF
	Attack        int32 // 每级攻击力加成(2026-09-07 第六十八轮)
	Cooldown      int32 // 冷却秒数(2026-09-07 第六十八轮)
	JobRequired   int32 // 职业要求
	LevelRequired int32 // 等级要求
	PreSkillID    int32 // 前置技能
	PreSkillLevel int32 // 前置技能等级
}

// RoleSkill 角色技能进度
type RoleSkill struct {
	BaseModel

	RoleID     uint64
	SkillID    int32
	Level      int32
	IsLearned  bool
	LastCastAt int64 // 技能最后施放时间戳(2026-09-07 第六十八轮, 冷却校验用)
}

// FindSkill 查询技能
type FindSkill struct {
	FindBase

	SkillID     *int32
	JobRequired *int32
	Type        *int32
}

// FindRoleSkill 查询角色技能
type FindRoleSkill struct {
	FindBase

	RoleID  *uint64
	SkillID *int32
}

// UpdateRoleSkill 更新角色技能
type UpdateRoleSkill struct {
	ID        uint64
	Level     *int32
	IsLearned *bool
}

// CreateRoleSkill 创建角色技能
type CreateRoleSkill struct {
	RoleID    uint64
	SkillID   int32
	Level     int32
	IsLearned bool
}
