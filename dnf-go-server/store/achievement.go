package store

import (
	"context"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

// AchievementInfo 成就信息
type AchievementInfo struct {
	AchievementID uint32 `json:"achievementId"`
	Name          string `json:"name"`
	Description   string `json:"description"`
	Progress      int32  `json:"progress"`
	TargetValue   int32  `json:"targetValue"`
	Completed     bool   `json:"completed"`
	Rewarded      bool   `json:"rewarded"`
}

// AchievementRewardResult 成就奖励结果
type AchievementRewardResult struct {
	AdventureUnionLevel int32                  `json:"adventureUnionLevel"`
	AdventureUnionExp   uint64                 `json:"adventureUnionExp"`
	ConsumeItems        []*dnfv1.StackableItem `json:"consumeItems"`
	InvenItems          *dnfv1.PT_ITEMS        `json:"invenItems"`
}

// AchievementListResult 成就列表结果
type AchievementListResult struct {
	Achievements []*AchievementInfo `json:"achievements"`
	Total        int32              `json:"total"`
}

// AchievementBonusRewardResult 成就额外奖励结果
type AchievementBonusRewardResult struct {
	InvenItems *dnfv1.PT_ITEMS `json:"invenItems"`
}

// ==================== 成就相关Store方法 ====================

// GetAchievements 获取成就信息
func (s *Store) GetAchievements(ctx context.Context, roleID uint64, queryType int32) ([]*AchievementInfo, error) {
	return s.driver.GetAchievements(ctx, roleID, queryType)
}

// ClaimAchievementReward 领取成就奖励
func (s *Store) ClaimAchievementReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32) (*AchievementRewardResult, error) {
	return s.driver.ClaimAchievementReward(ctx, roleID, achievementID, rewardType)
}

// GetAchievementList 获取成就列表
func (s *Store) GetAchievementList(ctx context.Context, roleID uint64, queryType int32) (*AchievementListResult, error) {
	return s.driver.GetAchievementList(ctx, roleID, queryType)
}

// ClaimAchievementBonusReward 领取成就额外奖励
func (s *Store) ClaimAchievementBonusReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32, rewardIndex uint32, rewardCount uint32) (*AchievementBonusRewardResult, error) {
	return s.driver.ClaimAchievementBonusReward(ctx, roleID, achievementID, rewardType, rewardIndex, rewardCount)
}
