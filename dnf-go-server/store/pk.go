package store

import "context"

// ==================== PK 数据模型 ====================

// PvpRecord PK 记录
type PvpRecord struct {
	BaseModel

	RoleID     uint64
	MatchType  uint32
	Win        bool
	Score      int32
	OpponentID uint64
	BattleTime int64
}

// PvpStats PK 统计
type PvpStats struct {
	BaseModel

	RoleID        uint64
	TotalMatches  uint32
	WinCount      uint32
	LoseCount     uint32
	TotalScore    int32
	MaxWinStreak  uint32
	CurrentStreak uint32
}

// PvpSeason PK 赛季
type PvpSeason struct {
	BaseModel

	SeasonID   uint32
	SeasonName string
	StartTime  int64
	EndTime    int64
	Status     uint32
}

// PvpReward PK 奖励
type PvpReward struct {
	BaseModel

	RoleID     uint64
	RewardID   uint32
	RewardName string
	Count      uint32
	Claimed    bool
}

// PvpMatchType PK 匹配类型
type PvpMatchType struct {
	BaseModel

	MatchType  uint32
	TypeName   string
	MinLevel   uint32
	MaxLevel   uint32
	MinPlayers uint32
	MaxPlayers uint32
	Status     uint32
}

// ==================== Store 方法 ====================

// ListPvpRecords 查询角色 PK 记录(按战斗时间倒序)
func (s *Store) ListPvpRecords(ctx context.Context, roleID uint64, limit int) ([]*PvpRecord, error) {
	return s.driver.ListPvpRecords(ctx, roleID, limit)
}

// GetPvpStats 获取角色 PK 统计
func (s *Store) GetPvpStats(ctx context.Context, roleID uint64) (*PvpStats, error) {
	return s.driver.GetPvpStats(ctx, roleID)
}

// GetActivePvpSeason 获取进行中的 PK 赛季
func (s *Store) GetActivePvpSeason(ctx context.Context) (*PvpSeason, error) {
	return s.driver.GetActivePvpSeason(ctx)
}

// ListPvpRewards 查询角色 PK 奖励
func (s *Store) ListPvpRewards(ctx context.Context, roleID uint64) ([]*PvpReward, error) {
	return s.driver.ListPvpRewards(ctx, roleID)
}

// ListPvpMatchTypes 查询启用的 PK 匹配类型
func (s *Store) ListPvpMatchTypes(ctx context.Context) ([]*PvpMatchType, error) {
	return s.driver.ListPvpMatchTypes(ctx)
}

// SubmitPvpBattleResult 提交 PK 战斗结果(记录 + 统计,事务内)
func (s *Store) SubmitPvpBattleResult(ctx context.Context, record *PvpRecord, stats *PvpStats) error {
	return s.driver.SubmitPvpBattleResult(ctx, record, stats)
}
