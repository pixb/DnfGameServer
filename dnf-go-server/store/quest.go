package store

// Quest 任务定义
type Quest struct {
	BaseModel

	QuestID       int32 // 任务模板ID
	Name          string
	Description   string
	Type          int32 // 0=主线, 1=支线, 2=日常
	LevelRequired int32
	JobRequired   int32 // 职业限制, 0=不限
	PreQuestID    int32 // 前置任务

	// 完成条件
	TargetType  int32 // 0=击杀, 1=收集, 2=对话, 3=通关副本
	TargetID    int32 // 目标ID
	TargetCount int32 // 目标数量

	// 奖励
	RewardExp   int64
	RewardGold  int64
	RewardItems string // JSON
}

// RoleQuest 角色任务进度
type RoleQuest struct {
	BaseModel

	RoleID      uint64
	QuestID     int32
	Status      int32 // 0=未接受, 1=进行中, 2=已完成, 3=已领取奖励
	Progress    int32 // 当前进度
	AcceptedAt  int64
	CompletedAt int64
}

// FindQuest 查询任务
type FindQuest struct {
	FindBase

	QuestID       *int32
	Type          *int32
	LevelRequired *int32
}

// UpdateQuest 更新任务
type UpdateQuest struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Name          *string
	Description   *string
	Type          *int32
	LevelRequired *int32
	JobRequired   *int32
	PreQuestID    *int32
	TargetType    *int32
	TargetID      *int32
	TargetCount   *int32
	RewardExp     *int64
	RewardGold    *int64
	RewardItems   *string
}

// FindRoleQuest 查询角色任务
type FindRoleQuest struct {
	FindBase

	RoleID  *uint64
	QuestID *int32
	Status  *int32
}

// UpdateRoleQuest 更新角色任务
type UpdateRoleQuest struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Status      *int32
	Progress    *int32
	CompletedAt *int64
}

// DeleteRoleQuest 删除角色任务
type DeleteRoleQuest DeleteBase
