package store

import (
	"context"
	"time"
)

// ==================== 冒险联盟相关数据结构 ====================

type AdventureUnionInfo struct {
	Exp                         uint64    `json:"exp"`
	Level                       uint32    `json:"level"`
	Day                         uint32    `json:"day"`
	TypicalCharacterGUID         uint64    `json:"typicalCharacterGuid"`
	Name                        string    `json:"name"`
	UpdateTime                  time.Time `json:"updateTime"`
	LastChangeNameTime          time.Time `json:"lastChangeNameTime"`
	ShareboardBackground        uint32    `json:"shareboardBackground"`
	ShareboardFrame             uint32    `json:"shareboardFrame"`
	ShareboardShowAntiEvilScore bool      `json:"shareboardShowAntiEvilScore"`
	AutoSearchCount             uint32    `json:"autoSearchCount"`
	ShareboardTotalAntiEvilScore uint32  `json:"shareboardTotalAntiEvilScore"`
	ShareboardAntiEvilScoreRefresh bool   `json:"shareboardAntiEvilScoreRefresh"`
	IsAdventureCondition        bool      `json:"isAdventureCondition"`
}

// ==================== 冒险联盟相关Store方法 ====================

// GetAdventureUnionInfo 获取冒险联盟信息
func (s *Store) GetAdventureUnionInfo(ctx context.Context, roleID uint64) (*AdventureUnionInfo, error) {
	return s.driver.GetAdventureUnionInfo(ctx, roleID)
}

// ChangeAdventureUnionName 修改冒险联盟名称
func (s *Store) ChangeAdventureUnionName(ctx context.Context, roleID uint64, name string) error {
	return s.driver.ChangeAdventureUnionName(ctx, roleID, name)
}

// StartAdventureUnionExpedition 开始冒险联盟远征
func (s *Store) StartAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32, expeditionType uint32) error {
	return s.driver.StartAdventureUnionExpedition(ctx, roleID, expeditionID, expeditionType)
}

// CancelAdventureUnionExpedition 取消冒险联盟远征
func (s *Store) CancelAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32) error {
	return s.driver.CancelAdventureUnionExpedition(ctx, roleID, expeditionID)
}

// ClaimAdventureUnionExpeditionReward 领取冒险联盟远征奖励
func (s *Store) ClaimAdventureUnionExpeditionReward(ctx context.Context, roleID uint64, expeditionID uint32) error {
	return s.driver.ClaimAdventureUnionExpeditionReward(ctx, roleID, expeditionID)
}

// StartAdventureUnionSubdue 开始冒险联盟讨伐
func (s *Store) StartAdventureUnionSubdue(ctx context.Context, roleID uint64, subdueID uint32, subdueType uint32, characterGUID uint64) error {
	return s.driver.StartAdventureUnionSubdue(ctx, roleID, subdueID, subdueType, characterGUID)
}

// ClaimAdventureUnionSubdueReward 领取冒险联盟讨伐奖励
func (s *Store) ClaimAdventureUnionSubdueReward(ctx context.Context, roleID uint64, subdueID uint32) error {
	return s.driver.ClaimAdventureUnionSubdueReward(ctx, roleID, subdueID)
}

// OpenAdventureUnionShareboardSlot 开启冒险联盟展示板槽位
func (s *Store) OpenAdventureUnionShareboardSlot(ctx context.Context, roleID uint64, slotID uint32) error {
	return s.driver.OpenAdventureUnionShareboardSlot(ctx, roleID, slotID)
}

// SetAdventureUnionShareboard 设置冒险联盟展示板
func (s *Store) SetAdventureUnionShareboard(ctx context.Context, roleID uint64, slotID uint32, slotType uint32, show bool) error {
	return s.driver.SetAdventureUnionShareboard(ctx, roleID, slotID, slotType, show)
}

// ClaimAdventureReapReward 领取冒险奖励
func (s *Store) ClaimAdventureReapReward(ctx context.Context, roleID uint64, reapID uint32) error {
	return s.driver.ClaimAdventureReapReward(ctx, roleID, reapID)
}

// StartAdventureUnionSearch 开始冒险联盟搜索
func (s *Store) StartAdventureUnionSearch(ctx context.Context, roleID uint64) error {
	return s.driver.StartAdventureUnionSearch(ctx, roleID)
}

// ClaimAdventureUnionCollectionReward 领取冒险联盟收藏奖励
func (s *Store) ClaimAdventureUnionCollectionReward(ctx context.Context, roleID uint64, collectionID uint32) error {
	return s.driver.ClaimAdventureUnionCollectionReward(ctx, roleID, collectionID)
}

// ClaimAdventureUnionLevelReward 领取冒险联盟等级奖励
func (s *Store) ClaimAdventureUnionLevelReward(ctx context.Context, roleID uint64, level uint32) error {
	return s.driver.ClaimAdventureUnionLevelReward(ctx, roleID, level)
}

// ==================== 原有的冒险数据结构（保留兼容性） ====================

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
