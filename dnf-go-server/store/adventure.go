package store

import "time"

type AdventureData struct {
	ID                 uint64
	RoleID             uint64
	AdventureLevel     int32
	AdventureExp       int32
	Energy             int32
	MaxEnergy          int32
	LastEnergyRecovery int64
	CreatedAt          int64
	UpdatedAt          int64
}

type AdventureStorageItem struct {
	ID          uint64
	RoleID      uint64
	ItemID      int32
	Count       int32
	IsBound     bool
	StorageTime int64
	CreatedAt   int64
	UpdatedAt   int64
}

type AdventureReap struct {
	ID          uint64
	RoleID      uint64
	ReapID      int32
	Progress    int32
	Total       int32
	IsCompleted bool
	StartTime   int64
	EndTime     int64
	CreatedAt   int64
	UpdatedAt   int64
}

type AdventureBook struct {
	ID         uint64
	RoleID     uint64
	BookID     int32
	Name       string
	Level      int32
	Experience int32
	CreatedAt  int64
	UpdatedAt  int64
}

type AdventureBookCondition struct {
	ID          uint64
	BookID      int32
	ConditionID int32
	Current     int32
	Target      int32
	IsCompleted bool
	CreatedAt   int64
	UpdatedAt   int64
}

type AdventureBookReward struct {
	ID         uint64
	BookID     int32
	RewardID   int32
	RewardType int32
	Amount     int32
	IsClaimed  bool
	ClaimedAt  *time.Time
	CreatedAt  int64
	UpdatedAt  int64
}

type FindAdventureData struct {
	RoleID *uint64
	ID     *uint64
}

type FindAdventureStorageItem struct {
	ID     *uint64
	RoleID *uint64
	ItemID *int32
	Limit  *int
	Offset *int
}

type FindAdventureReap struct {
	ID     *uint64
	RoleID *uint64
	ReapID *int32
}

type FindAdventureBook struct {
	ID     *uint64
	RoleID *uint64
	BookID *int32
}

type CreateAdventureData struct {
	RoleID         uint64
	AdventureLevel int32
	AdventureExp   int32
	Energy         int32
	MaxEnergy      int32
}

type UpdateAdventureData struct {
	ID             uint64
	AdventureLevel *int32
	AdventureExp   *int32
	Energy         *int32
	MaxEnergy      *int32
}

type CreateAdventureStorageItem struct {
	RoleID      uint64
	ItemID      int32
	Count       int32
	IsBound     bool
	StorageTime int64
}

type UpdateAdventureStorageItem struct {
	ID      uint64
	Count   *int32
	IsBound *bool
}

type CreateAdventureReap struct {
	RoleID    uint64
	ReapID    int32
	Progress  int32
	Total     int32
	StartTime int64
	EndTime   int64
}

type UpdateAdventureReap struct {
	ID          uint64
	Progress    *int32
	IsCompleted *bool
}

type CreateAdventureBook struct {
	RoleID     uint64
	BookID     int32
	Name       string
	Level      int32
	Experience int32
}

type UpdateAdventureBook struct {
	ID         uint64
	Level      *int32
	Experience *int32
}

type CreateAdventureBookCondition struct {
	BookID      int32
	ConditionID int32
	Current     int32
	Target      int32
}

type UpdateAdventureBookCondition struct {
	ID          uint64
	Current     *int32
	IsCompleted *bool
}

type CreateAdventureBookReward struct {
	BookID     int32
	RewardID   int32
	RewardType int32
	Amount     int32
}

type ClaimAdventureBookReward struct {
	ID        uint64
	ClaimedAt time.Time
}
