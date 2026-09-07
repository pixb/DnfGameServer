package pk_service

import (
	"context"
	"errors"
	"fmt"
	"sync/atomic"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/role"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// PkService PK 服务
type PkService struct {
	store *store.Store

	matchingSeq uint64
}

// 战斗服务器占位配置(真实战斗服务器未接入,先回本机地址)
const (
	battleServerIP   = "127.0.0.1"
	battleServerPort = 9500
)

// 匹配状态
const (
	matchingStatusMatching = uint32(0) // 匹配中
	matchingStatusSuccess  = uint32(1) // 匹配成功
	matchingStatusCanceled = uint32(2) // 已取消
	matchingStatusTimeout  = uint32(3) // 已超时
)

// NewPkService 创建 PK 服务
func NewPkService(st *store.Store) *PkService {
	return &PkService{
		store: st,
	}
}

// MatchResult 匹配结果
type MatchResult struct {
	MatchingGuid uint64
	IP           string
	Port         uint32
}

// CustomGameRoomResult 自定义游戏房间结果
type CustomGameRoomResult struct {
	NotifyControlGroup *dnfv1.NotifyControlGroup
}

// genMatchingID 生成唯一匹配ID。
// 限制在 48 位(2^53 安全整数范围内),避免 JSON 大数精度丢失:
// 高24位为 Unix 秒,低24位为自增序列。
func (s *PkService) genMatchingID() uint64 {
	seq := atomic.AddUint64(&s.matchingSeq, 1) & 0xFFFFFF
	return ((uint64(time.Now().Unix()) & 0xFFFFFF) << 24) | seq
}

// RequestMatch 请求匹配
func (s *PkService) RequestMatch(ctx context.Context, roleID uint64, matchType, dungeonIndex uint32) (*MatchResult, error) {
	logger.Info("request match",
		logger.Uint64("role_id", roleID),
		logger.Uint32("match_type", matchType),
		logger.Uint32("dungeon_index", dungeonIndex),
	)

	// 1. 验证匹配类型
	types, err := s.store.ListPvpMatchTypes(ctx)
	if err != nil {
		return nil, fmt.Errorf("failed to load match types: %w", err)
	}
	valid := false
	for _, t := range types {
		if t.MatchType == matchType {
			valid = true
			break
		}
	}
	if !valid {
		return nil, fmt.Errorf("invalid match type: %d", matchType)
	}

	// 2. 检查是否存在进行中的匹配
	active, err := s.store.ListPvpMatchingsByRole(ctx, roleID, matchingStatusMatching)
	if err != nil {
		return nil, fmt.Errorf("failed to check active matching: %w", err)
	}
	if len(active) > 0 {
		return &MatchResult{
			MatchingGuid: active[0].MatchingID,
			IP:           battleServerIP,
			Port:         battleServerPort,
		}, nil
	}

	// 3. 创建匹配记录
	matchingID := s.genMatchingID()
	if _, err := s.store.CreatePvpMatching(ctx, &store.PvpMatching{
		MatchingID: matchingID,
		RoleID:     roleID,
		MatchType:  matchType,
		Status:     matchingStatusMatching,
	}); err != nil {
		return nil, fmt.Errorf("failed to create matching: %w", err)
	}

	return &MatchResult{
		MatchingGuid: matchingID,
		IP:           battleServerIP,
		Port:         battleServerPort,
	}, nil
}

// CancelMatch 取消匹配
func (s *PkService) CancelMatch(ctx context.Context, roleID, matchingGuid uint64) error {
	logger.Info("cancel match",
		logger.Uint64("role_id", roleID),
		logger.Uint64("matching_guid", matchingGuid),
	)

	// 校验匹配归属:查询该角色的匹配记录
	active, err := s.store.ListPvpMatchingsByRole(ctx, roleID, matchingStatusMatching)
	if err != nil {
		return fmt.Errorf("failed to check active matching: %w", err)
	}
	owned := false
	for _, m := range active {
		if m.MatchingID == matchingGuid {
			owned = true
			break
		}
	}
	if !owned {
		return fmt.Errorf("matching not found or not owned by role: %d", matchingGuid)
	}

	// 更新状态为已取消
	return s.store.UpdatePvpMatchingStatus(ctx, matchingGuid, matchingStatusCanceled)
}

// GetGuildDonationRecipes 获取公会捐赠配方
func (s *PkService) GetGuildDonationRecipes(ctx context.Context) ([]*dnfv1.RecipeInfo, error) {
	logger.Info("get guild donation recipes")

	// TODO: 实现实际的获取公会捐赠配方逻辑
	// 1. 查询公会捐赠配方
	// 2. 返回配方列表

	return []*dnfv1.RecipeInfo{
		{Index: 5},
		{Index: 13},
		{Index: 15},
		{Index: 11},
		{Index: 1},
		{Index: 9},
		{Index: 17},
		{Index: 4},
	}, nil
}

// GetRaidEntranceCount 获取副本入场次数
func (s *PkService) GetRaidEntranceCount(ctx context.Context, roleID uint64) ([]*dnfv1.RaidEntranceInfo, error) {
	logger.Info("get raid entrance count",
		logger.Uint64("role_id", roleID),
	)

	entrances, err := s.store.ListRaidEntrances(ctx, roleID)
	if err != nil {
		return nil, err
	}

	infos := make([]*dnfv1.RaidEntranceInfo, 0, len(entrances))
	for _, e := range entrances {
		infos = append(infos, &dnfv1.RaidEntranceInfo{
			Raidindex:            e.RaidIndex,
			Dailycharacter:       e.DailyCharacterCount,
			Character:            e.CharacterCount,
			Account:              e.AccountCount,
			Dailyrewardcharacter: e.DailyRewardCount,
			Rewardcharacter:      e.RewardCount,
			Rewardaccount:        e.RewardCount,
		})
	}

	return infos, nil
}

// ReportLoadingProgress 报告加载进度
func (s *PkService) ReportLoadingProgress(ctx context.Context, roleID uint64, progress uint32) error {
	logger.Info("report loading progress",
		logger.Uint64("role_id", roleID),
		logger.Uint32("progress", progress),
	)

	// TODO: 实现实际的报告加载进度逻辑
	// 1. 更新玩家加载进度
	// 2. 广播给所有PVP角色

	return nil
}

// ReturnToTown 返回城镇
func (s *PkService) ReturnToTown(ctx context.Context, roleID uint64) error {
	logger.Info("return to town",
		logger.Uint64("role_id", roleID),
	)

	// TODO: 实现实际的返回城镇逻辑
	// 1. 清除匹配状态
	// 2. 更新玩家位置
	// 3. 返回城镇

	return nil
}

// SetCustomGameRoom 设置自定义游戏房间
func (s *PkService) SetCustomGameRoom(ctx context.Context, roleID uint64, customData *dnfv1.CustomData) (*CustomGameRoomResult, error) {
	logger.Info("set custom game room",
		logger.Uint64("role_id", roleID),
		logger.Uint64("sender_guid", customData.Senderguid),
		logger.Uint32("type", customData.Type),
	)

	// TODO: 实现实际的自定义游戏房间设置逻辑
	// 1. 验证队长权限
	// 2. 设置房间参数
	// 3. 通知所有队员

	return &CustomGameRoomResult{
		NotifyControlGroup: nil,
	}, nil
}

// GetPvpRecord 获取 PK 记录
func (s *PkService) GetPvpRecord(ctx context.Context, roleID uint64) ([]*dnfv1.PvpRecordInfo, error) {
	logger.Info("get pvp record",
		logger.Uint64("role_id", roleID),
	)

	records, err := s.store.ListPvpRecords(ctx, roleID, 100)
	if err != nil {
		logger.Error("failed to get pvp record",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return nil, err
	}

	recordInfos := make([]*dnfv1.PvpRecordInfo, 0, len(records))
	for _, record := range records {
		recordInfos = append(recordInfos, &dnfv1.PvpRecordInfo{
			Id:         record.ID,
			RoleId:     record.RoleID,
			MatchType:  record.MatchType,
			Win:        record.Win,
			Score:      record.Score,
			OpponentId: record.OpponentID,
			BattleTime: record.BattleTime,
		})
	}

	return recordInfos, nil
}

// GetPvpRanking 获取 PK 排名
func (s *PkService) GetPvpRanking(ctx context.Context, matchType, page, pageSize uint32) ([]*dnfv1.PvpRankingInfo, error) {
	logger.Info("get pvp ranking",
		logger.Uint32("match_type", matchType),
		logger.Uint32("page", page),
		logger.Uint32("page_size", pageSize),
	)

	// 排名不分页,直接取前 pageSize 条
	limit := int(pageSize)
	if limit <= 0 {
		limit = 50
	}
	entries, err := s.store.ListPvpRanking(ctx, limit)
	if err != nil {
		return nil, err
	}

	infos := make([]*dnfv1.PvpRankingInfo, 0, len(entries))
	for _, e := range entries {
		infos = append(infos, &dnfv1.PvpRankingInfo{
			RoleId:    e.RoleID,
			Name:      e.Name,
			Level:     uint32(e.Level),
			Job:       uint32(e.Job),
			Score:     uint32(e.Score),
			Rank:      e.Rank,
			WinCount:  e.WinCount,
			LoseCount: e.LoseCount,
		})
	}

	return infos, nil
}

// GetPvpStats 获取 PK 统计
func (s *PkService) GetPvpStats(ctx context.Context, roleID uint64) (*dnfv1.PvpStatsInfo, error) {
	logger.Info("get pvp stats",
		logger.Uint64("role_id", roleID),
	)

	stats, err := s.store.GetPvpStats(ctx, roleID)
	if err != nil {
		if errors.Is(err, store.ErrNotFound) {
			return &dnfv1.PvpStatsInfo{
				RoleId:       roleID,
				TotalMatches: 0,
				WinCount:     0,
				LoseCount:    0,
				WinRate:      0,
				TotalScore:   0,
				AvgScore:     0,
				MaxWinStreak: 0,
			}, nil
		}
		logger.Error("failed to get pvp stats",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return nil, err
	}

	winRate := 0.0
	if stats.TotalMatches > 0 {
		winRate = float64(stats.WinCount) / float64(stats.TotalMatches)
	}

	avgScore := int32(0)
	if stats.TotalMatches > 0 {
		avgScore = stats.TotalScore / int32(stats.TotalMatches)
	}

	return &dnfv1.PvpStatsInfo{
		RoleId:       stats.RoleID,
		TotalMatches: stats.TotalMatches,
		WinCount:     stats.WinCount,
		LoseCount:    stats.LoseCount,
		WinRate:      float32(winRate),
		TotalScore:   stats.TotalScore,
		AvgScore:     avgScore,
		MaxWinStreak: stats.MaxWinStreak,
	}, nil
}

// GetPvpMatchHistory 获取 PK 匹配历史
func (s *PkService) GetPvpMatchHistory(ctx context.Context, roleID uint64, page, pageSize uint32) ([]*dnfv1.PvpMatchHistoryInfo, error) {
	logger.Info("get pvp match history",
		logger.Uint64("role_id", roleID),
		logger.Uint32("page", page),
		logger.Uint32("page_size", pageSize),
	)

	limit := int(pageSize)
	if limit <= 0 {
		limit = 50
	}
	entries, err := s.store.ListPvpMatchHistory(ctx, roleID, limit)
	if err != nil {
		return nil, err
	}

	infos := make([]*dnfv1.PvpMatchHistoryInfo, 0, len(entries))
	for _, e := range entries {
		infos = append(infos, &dnfv1.PvpMatchHistoryInfo{
			Id:           e.ID,
			RoleId:       e.RoleID,
			MatchType:    e.MatchType,
			Win:          e.Win,
			Score:        e.Score,
			OpponentName: e.OpponentName,
			BattleTime:   e.BattleTime,
		})
	}

	return infos, nil
}

// GetPvpSeasonInfo 获取 PK 赛季信息
func (s *PkService) GetPvpSeasonInfo(ctx context.Context) (*dnfv1.PvpSeasonInfo, error) {
	logger.Info("get pvp season info")

	season, err := s.store.GetActivePvpSeason(ctx)
	if err != nil {
		if errors.Is(err, store.ErrNotFound) {
			return &dnfv1.PvpSeasonInfo{
				SeasonId:   1,
				SeasonName: "Season 1",
				StartTime:  time.Now().Add(-30 * 24 * time.Hour).Unix(),
				EndTime:    time.Now().Add(30 * 24 * time.Hour).Unix(),
				Status:     1,
			}, nil
		}
		logger.Error("failed to get pvp season info",
			logger.ErrorField(err),
		)
		return nil, err
	}

	return &dnfv1.PvpSeasonInfo{
		SeasonId:   season.SeasonID,
		SeasonName: season.SeasonName,
		StartTime:  season.StartTime,
		EndTime:    season.EndTime,
		Status:     season.Status,
	}, nil
}

// GetPvpReward 获取 PK 奖励
func (s *PkService) GetPvpReward(ctx context.Context, roleID uint64) ([]*dnfv1.PvpRewardInfo, error) {
	logger.Info("get pvp reward",
		logger.Uint64("role_id", roleID),
	)

	rewards, err := s.store.ListPvpRewards(ctx, roleID)
	if err != nil {
		logger.Error("failed to get pvp reward",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return nil, err
	}

	rewardInfos := make([]*dnfv1.PvpRewardInfo, 0, len(rewards))
	for _, reward := range rewards {
		rewardInfos = append(rewardInfos, &dnfv1.PvpRewardInfo{
			RewardId:   reward.RewardID,
			RewardName: reward.RewardName,
			Count:      reward.Count,
			Claimed:    reward.Claimed,
		})
	}

	return rewardInfos, nil
}

// PvpDailyReset PK 每日重置
func (s *PkService) PvpDailyReset(ctx context.Context, roleID uint64) error {
	logger.Info("pvp daily reset",
		logger.Uint64("role_id", roleID),
	)

	// 1. 重置副本每日入场/奖励计数
	if err := s.store.ResetDailyRaidEntrances(ctx, roleID); err != nil {
		return err
	}

	// 2. 取消未完成的匹配(状态置为已取消)
	active, err := s.store.ListPvpMatchingsByRole(ctx, roleID, matchingStatusMatching)
	if err != nil {
		return err
	}
	for _, m := range active {
		if err := s.store.UpdatePvpMatchingStatus(ctx, m.MatchingID, matchingStatusCanceled); err != nil {
			return err
		}
	}

	return nil
}

// GetPvpMatchTypes 获取 PK 匹配类型
func (s *PkService) GetPvpMatchTypes(ctx context.Context) ([]*dnfv1.PvpMatchTypeInfo, error) {
	logger.Info("get pvp match types")

	matchTypes, err := s.store.ListPvpMatchTypes(ctx)
	if err != nil {
		logger.Error("failed to get pvp match types",
			logger.ErrorField(err),
		)
		return nil, err
	}

	typeInfos := make([]*dnfv1.PvpMatchTypeInfo, 0, len(matchTypes))
	for _, matchType := range matchTypes {
		typeInfos = append(typeInfos, &dnfv1.PvpMatchTypeInfo{
			MatchType:  matchType.MatchType,
			TypeName:   matchType.TypeName,
			MinLevel:   matchType.MinLevel,
			MaxLevel:   matchType.MaxLevel,
			MinPlayers: matchType.MinPlayers,
			MaxPlayers: matchType.MaxPlayers,
		})
	}

	return typeInfos, nil
}

// SubmitPvpBattleResult 提交 PK 战斗结果
func (s *PkService) SubmitPvpBattleResult(ctx context.Context, roleID, matchingGuid, opponentID uint64, win bool, score int32) (*role.LevelUpResult, error) {
	logger.Info("submit pvp battle result",
		logger.Uint64("role_id", roleID),
		logger.Uint64("matching_guid", matchingGuid),
		logger.Uint64("opponent_id", opponentID),
		logger.Bool("win", win),
		logger.Int32("score", score),
	)

	record := &store.PvpRecord{
		RoleID:     roleID,
		MatchType:  1,
		Win:        win,
		Score:      score,
		OpponentID: opponentID,
		BattleTime: time.Now().Unix(),
	}

	// 计算最新统计(存在则累加,不存在则新建)
	stats := &store.PvpStats{RoleID: roleID}
	existing, err := s.store.GetPvpStats(ctx, roleID)
	switch {
	case err == nil:
		stats = existing
		stats.TotalMatches++
		stats.TotalScore += score
		if win {
			stats.WinCount++
			stats.CurrentStreak++
			if stats.CurrentStreak > stats.MaxWinStreak {
				stats.MaxWinStreak = stats.CurrentStreak
			}
		} else {
			stats.LoseCount++
			stats.CurrentStreak = 0
		}
	case errors.Is(err, store.ErrNotFound):
		stats.TotalMatches = 1
		stats.TotalScore = score
		if win {
			stats.WinCount = 1
			stats.CurrentStreak = 1
			stats.MaxWinStreak = 1
		} else {
			stats.LoseCount = 1
		}
	default:
		return nil, err
	}

	if err := s.store.SubmitPvpBattleResult(ctx, record, stats); err != nil {
		return nil, err
	}

	// PK 结算奖励经验(2026-09-07 第六十九轮实化): 胜 +50, 败 +10
	expGain := int64(10)
	if win {
		expGain = 50
	}
	levelUp, err := role.AddRoleExp(ctx, s.store, roleID, expGain)
	if err != nil {
		// 经验发放失败不影响战绩落库, 仅告警
		logger.Error("failed to award pvp exp",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Int64("exp_gain", expGain),
		)
		return nil, nil
	}
	return levelUp, nil
}
