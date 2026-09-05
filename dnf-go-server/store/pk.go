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

// PvpMatching PK 匹配
type PvpMatching struct {
	BaseModel

	MatchingID uint64
	RoleID     uint64
	MatchType  uint32
	Status     uint32
}

// RaidEntrance 副本入场记录
type RaidEntrance struct {
	BaseModel

	RoleID              uint64
	RaidIndex           uint32
	DailyCharacterCount uint32
	CharacterCount      uint32
	AccountCount        uint32
	DailyRewardCount    uint32
	RewardCount         uint32
	LastEnterTime       int64
}

// PvpRankingEntry PK 排名条目(含角色名/等级/职业)
type PvpRankingEntry struct {
	RoleID    uint64
	Name      string
	Level     int32
	Job       int32
	Score     int32
	Rank      uint32
	WinCount  uint32
	LoseCount uint32
}

// PvpMatchHistoryEntry PK 匹配历史条目(含对手名)
type PvpMatchHistoryEntry struct {
	ID           uint64
	RoleID       uint64
	MatchType    uint32
	Win          bool
	Score        int32
	OpponentName string
	BattleTime   int64
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

// CreatePvpMatchType 创建 PK 匹配类型(用于 Seed 默认配置)
func (s *Store) CreatePvpMatchType(ctx context.Context, t *PvpMatchType) error {
	return s.driver.CreatePvpMatchType(ctx, t)
}

// SubmitPvpBattleResult 提交 PK 战斗结果(记录 + 统计,事务内)
func (s *Store) SubmitPvpBattleResult(ctx context.Context, record *PvpRecord, stats *PvpStats) error {
	return s.driver.SubmitPvpBattleResult(ctx, record, stats)
}

// ==================== Store 方法(PK 匹配/入场/排名/历史) ====================

// CreatePvpMatching 创建 PK 匹配记录
func (s *Store) CreatePvpMatching(ctx context.Context, m *PvpMatching) (*PvpMatching, error) {
	return s.driver.CreatePvpMatching(ctx, m)
}

// UpdatePvpMatchingStatus 更新匹配状态
func (s *Store) UpdatePvpMatchingStatus(ctx context.Context, matchingID uint64, status uint32) error {
	return s.driver.UpdatePvpMatchingStatus(ctx, matchingID, status)
}

// ListPvpMatchingsByRole 查询角色匹配记录(按状态)
func (s *Store) ListPvpMatchingsByRole(ctx context.Context, roleID uint64, status uint32) ([]*PvpMatching, error) {
	return s.driver.ListPvpMatchingsByRole(ctx, roleID, status)
}

// ListRaidEntrances 查询角色副本入场记录
func (s *Store) ListRaidEntrances(ctx context.Context, roleID uint64) ([]*RaidEntrance, error) {
	return s.driver.ListRaidEntrances(ctx, roleID)
}

// UpsertRaidEntrance 创建/更新副本入场记录
func (s *Store) UpsertRaidEntrance(ctx context.Context, e *RaidEntrance) error {
	return s.driver.UpsertRaidEntrance(ctx, e)
}

// ResetDailyRaidEntrances 重置角色副本每日入场/奖励计数
func (s *Store) ResetDailyRaidEntrances(ctx context.Context, roleID uint64) error {
	return s.driver.ResetDailyRaidEntrances(ctx, roleID)
}

// ListPvpRanking 查询 PK 排名(按总积分倒序)
func (s *Store) ListPvpRanking(ctx context.Context, limit int) ([]*PvpRankingEntry, error) {
	return s.driver.ListPvpRanking(ctx, limit)
}

// ListPvpMatchHistory 查询角色 PK 匹配历史(含对手名)
func (s *Store) ListPvpMatchHistory(ctx context.Context, roleID uint64, limit int) ([]*PvpMatchHistoryEntry, error) {
	return s.driver.ListPvpMatchHistory(ctx, roleID, limit)
}
