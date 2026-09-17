package achievement_service

import (
	"context"
	"fmt"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

// AchievementService 成就服务
// 基于 store 层实现,支持 sqlite/mysql 双驱动
type AchievementService struct {
	store *store.Store
}

func NewAchievementService(s *store.Store) *AchievementService {
	return &AchievementService{
		store: s,
	}
}

type AchievementInfo struct {
	AchievementID uint32 `json:"achievementId"`
	Name          string `json:"name"`
	Description   string `json:"description"`
	Progress      int32  `json:"progress"`
	TargetValue   int32  `json:"targetValue"`
	Completed     bool   `json:"completed"`
	Rewarded      bool   `json:"rewarded"`
}

type AchievementListResult struct {
	Achievements []AchievementInfo `json:"achievements"`
	Total        int32             `json:"total"`
}

type AchievementRewardResult struct {
	AdventureUnionLevel int32                  `json:"adventureunionlevel"`
	AdventureUnionExp   uint64                 `json:"adventureunionexp"`
	ConsumeItems        []*dnfv1.StackableItem `json:"consumeitems"`
	InvenItems          *dnfv1.PT_ITEMS        `json:"invenitems"`
}

// GetAchievementInfo 获取成就信息
func (s *AchievementService) GetAchievementInfo(ctx context.Context, roleID uint64, queryType int32) ([]AchievementInfo, error) {
	logger.Info("get achievement info",
		logger.Uint64("role_id", roleID),
		logger.Int32("query_type", queryType),
	)

	infos, err := s.store.GetAchievements(ctx, roleID, queryType)
	if err != nil {
		logger.Error("failed to get achievements",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return nil, err
	}

	achievements := make([]AchievementInfo, len(infos))
	for i, info := range infos {
		achievements[i] = convertInfo(info)
	}
	return achievements, nil
}

// GetAchievementList 获取成就列表
func (s *AchievementService) GetAchievementList(ctx context.Context, roleID uint64, listType int32) (*AchievementListResult, error) {
	logger.Info("get achievement list",
		logger.Uint64("role_id", roleID),
		logger.Int32("list_type", listType),
	)

	result, err := s.store.GetAchievementList(ctx, roleID, listType)
	if err != nil {
		logger.Error("failed to get achievement list",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return nil, err
	}

	achievements := make([]AchievementInfo, len(result.Achievements))
	for i, info := range result.Achievements {
		achievements[i] = convertInfo(info)
	}

	return &AchievementListResult{
		Achievements: achievements,
		Total:        result.Total,
	}, nil
}

// ClaimAchievementReward 领取成就奖励
func (s *AchievementService) ClaimAchievementReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32) (*AchievementRewardResult, error) {
	logger.Info("claim achievement reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("achievement_id", achievementID),
		logger.Uint32("reward_type", rewardType),
	)

	result, err := s.store.ClaimAchievementReward(ctx, roleID, achievementID, rewardType)
	if err != nil {
		logger.Error("failed to claim achievement reward",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("achievement_id", achievementID),
		)
		return nil, err
	}

	return &AchievementRewardResult{
		AdventureUnionLevel: result.AdventureUnionLevel,
		AdventureUnionExp:   result.AdventureUnionExp,
		ConsumeItems:        result.ConsumeItems,
		InvenItems:          result.InvenItems,
	}, nil
}

// ClaimAchievementBonusReward 领取成就额外奖励
func (s *AchievementService) ClaimAchievementBonusReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType, rewardIndex, rewardCount uint32) ([]*dnfv1.StackableItem, error) {
	logger.Info("claim achievement bonus reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("achievement_id", achievementID),
		logger.Uint32("reward_type", rewardType),
		logger.Uint32("reward_index", rewardIndex),
		logger.Uint32("reward_count", rewardCount),
	)

	result, err := s.store.ClaimAchievementBonusReward(ctx, roleID, achievementID, rewardType, rewardIndex, rewardCount)
	if err != nil {
		logger.Error("failed to claim achievement bonus reward",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("achievement_id", achievementID),
		)
		return nil, err
	}

	if result == nil || result.InvenItems == nil {
		return nil, fmt.Errorf("no bonus reward returned")
	}
	return result.InvenItems.ConsumeItems, nil
}

// convertInfo 转换store层成就信息
func convertInfo(info *store.AchievementInfo) AchievementInfo {
	return AchievementInfo{
		AchievementID: info.AchievementID,
		Name:          info.Name,
		Description:   info.Description,
		Progress:      info.Progress,
		TargetValue:   info.TargetValue,
		Completed:     info.Completed,
		Rewarded:      info.Rewarded,
	}
}
