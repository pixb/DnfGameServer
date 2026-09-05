package pk_service

import (
	"context"
	"errors"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// PkService PK 服务
type PkService struct {
	store *store.Store
}

// NewPkService 创建 PK 服务
func NewPkService(st *store.Store) *PkService {
	return &PkService{
		store: st,
	}
}

// MatchResult 匹配结果
type MatchResult struct {
	MatchingGuid uint64
	IP          string
	Port        uint32
}

// CustomGameRoomResult 自定义游戏房间结果
type CustomGameRoomResult struct {
	NotifyControlGroup *dnfv1.NotifyControlGroup
}

// RequestMatch 请求匹配
func (s *PkService) RequestMatch(ctx context.Context, roleID uint64, matchType, dungeonIndex uint32) (*MatchResult, error) {
	logger.Info("request match",
		logger.Uint64("role_id", roleID),
		logger.Uint32("match_type", matchType),
		logger.Uint32("dungeon_index", dungeonIndex),
	)

	// TODO: 实现实际的匹配逻辑
	// 1. 验证玩家状态
	// 2. 查找匹配的对手
	// 3. 创建匹配记录
	// 4. 返回匹配结果

	return nil, fmt.Errorf("matching not implemented")
}

// CancelMatch 取消匹配
func (s *PkService) CancelMatch(ctx context.Context, roleID, matchingGuid uint64) error {
	logger.Info("cancel match",
		logger.Uint64("role_id", roleID),
		logger.Uint64("matching_guid", matchingGuid),
	)

	// TODO: 实现实际的取消匹配逻辑
	// 1. 验证匹配ID
	// 2. 取消匹配
	// 3. 清理匹配记录

	return fmt.Errorf("cancel match not implemented")
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

	// TODO: 实现实际的获取副本入场次数逻辑
	// 1. 查询玩家副本入场记录
	// 2. 返回入场次数信息

	return []*dnfv1.RaidEntranceInfo{
		{
			Raidindex:             1,
			Dailycharacter:        1,
			Character:            3,
			Account:              12,
			Dailyrewardcharacter: 1,
			Rewardcharacter:       3,
			Rewardaccount:         12,
		},
		{
			Raidindex:             2,
			Dailycharacter:        1,
			Character:            1,
			Account:              4,
			Dailyrewardcharacter: 1,
			Rewardcharacter:       1,
			Rewardaccount:         4,
		},
	}, nil
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

	// TODO: 实现实际的获取 PK 排名逻辑
	// 1. 查询 PK 排行榜
	// 2. 返回排名列表

	return []*dnfv1.PvpRankingInfo{}, nil
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

	// TODO: 实现实际的获取 PK 匹配历史逻辑
	// 1. 查询玩家匹配历史
	// 2. 返回历史列表

	return []*dnfv1.PvpMatchHistoryInfo{}, nil
}

// GetPvpSeasonInfo 获取 PK 赛季信息
func (s *PkService) GetPvpSeasonInfo(ctx context.Context) (*dnfv1.PvpSeasonInfo, error) {
	logger.Info("get pvp season info")

	season, err := s.store.GetActivePvpSeason(ctx)
	if err != nil {
		if errors.Is(err, store.ErrNotFound) {
			return &dnfv1.PvpSeasonInfo{
				SeasonId:  1,
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
		SeasonId:  season.SeasonID,
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

	// TODO: 实现实际的 PK 每日重置逻辑
	// 1. 重置每日匹配次数
	// 2. 重置每日奖励

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
func (s *PkService) SubmitPvpBattleResult(ctx context.Context, roleID, matchingGuid, opponentID uint64, win bool, score int32) error {
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
		return err
	}

	return s.store.SubmitPvpBattleResult(ctx, record, stats)
}
