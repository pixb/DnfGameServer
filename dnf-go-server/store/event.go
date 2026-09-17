package store

// EventStatus 活动状态
type EventStatus int32

const (
	EventStatusClosed    EventStatus = 0 // 已关闭
	EventStatusRunning   EventStatus = 1 // 进行中
	EventStatusFinished  EventStatus = 2 // 已结束
	EventStatusScheduled EventStatus = 3 // 未开始(已配置)
)

// EventConfig 活动配置
type EventConfig struct {
	BaseModel

	EventID      int32  // 活动业务ID
	Title        string // 活动标题
	Description  string // 活动描述
	EventType    int32  // 活动类型(0=通用 1=限时 2=签到 3=累计)
	Status       EventStatus
	StartTime    int64 // 开始时间(Unix秒)
	EndTime      int64 // 结束时间(Unix秒)
	RewardConfig string // 奖励配置(JSON)
}

// FindEventConfig 查询活动配置
type FindEventConfig struct {
	FindBase

	EventID   *int32
	EventType *int32
	Status    *EventStatus
	Running   *bool // true=仅查询进行中(now 落在 [start,end])
}

// UpdateEventConfig 更新活动配置
type UpdateEventConfig struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Title        *string
	Description  *string
	EventType    *int32
	Status       *EventStatus
	StartTime    *int64
	EndTime      *int64
	RewardConfig *string
}

// EventProgress 活动进度
type EventProgress struct {
	BaseModel

	RoleID        uint64
	EventID       int32
	ProgressType  int32
	ProgressValue int64
	Status        int32 // 0=进行中 1=已领奖
}

// FindEventProgress 查询活动进度
type FindEventProgress struct {
	FindBase

	RoleID       uint64
	EventID      *int32
	ProgressType *int32
}

// UpdateEventProgress 更新活动进度
type UpdateEventProgress struct {
	ID     uint64
	RoleID uint64

	ProgressValue *int64
	Status        *int32
}
