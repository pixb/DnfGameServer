package achievement_service

import (
	"context"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

type AchievementService struct {
	db *db.DB
}

func NewAchievementService(db *db.DB) *AchievementService {
	return &AchievementService{
		db: db,
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

func (s *AchievementService) GetAchievementInfo(ctx context.Context, roleID uint64, queryType int32) ([]AchievementInfo, error) {
	logger.Info("get achievement info",
		logger.Uint64("role_id", roleID),
		logger.Int32("query_type", queryType),
	)

	var records []models.AchievementRecord
	query := s.db.DB.Where("role_id = ?", roleID)

	switch queryType {
	case 1:
	case 2:
		query = query.Where("completed = ?", true)
	case 3:
		query = query.Where("completed = ?", false)
	default:
		return nil, fmt.Errorf("invalid query type: %d", queryType)
	}

	if err := query.Find(&records).Error; err != nil {
		logger.Error("failed to query achievement records",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return nil, err
	}

	var configs []models.AchievementConfig
	configMap := make(map[uint32]*models.AchievementConfig)
	if len(records) > 0 {
		achievementIDs := make([]uint32, len(records))
		for i, record := range records {
			achievementIDs[i] = record.AchievementID
		}

		if err := s.db.DB.Where("achievement_id IN ?", achievementIDs).Find(&configs).Error; err != nil {
			logger.Error("failed to query achievement configs",
				logger.ErrorField(err),
			)
			return nil, err
		}

		for i := range configs {
			configMap[configs[i].AchievementID] = &configs[i]
		}
	}

	achievements := make([]AchievementInfo, len(records))
	for i, record := range records {
		achievements[i] = AchievementInfo{
			AchievementID: record.AchievementID,
			Progress:      record.Progress,
			Completed:     record.Completed,
			Rewarded:      record.Rewarded,
		}

		if config, ok := configMap[record.AchievementID]; ok {
			achievements[i].Name = config.Name
			achievements[i].Description = config.Description
			achievements[i].TargetValue = config.TargetValue
		}
	}

	return achievements, nil
}

func (s *AchievementService) GetAchievementList(ctx context.Context, roleID uint64, listType int32) (*AchievementListResult, error) {
	logger.Info("get achievement list",
		logger.Uint64("role_id", roleID),
		logger.Int32("list_type", listType),
	)

	var records []models.AchievementRecord
	query := s.db.DB.Where("role_id = ?", roleID)

	if err := query.Find(&records).Error; err != nil {
		logger.Error("failed to query achievement records",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return nil, err
	}

	var configs []models.AchievementConfig
	configMap := make(map[uint32]*models.AchievementConfig)
	if len(records) > 0 {
		achievementIDs := make([]uint32, len(records))
		for i, record := range records {
			achievementIDs[i] = record.AchievementID
		}

		if err := s.db.DB.Where("achievement_id IN ?", achievementIDs).Find(&configs).Error; err != nil {
			logger.Error("failed to query achievement configs",
				logger.ErrorField(err),
			)
			return nil, err
		}

		for i := range configs {
			configMap[configs[i].AchievementID] = &configs[i]
		}
	}

	achievements := make([]AchievementInfo, len(records))
	for i, record := range records {
		achievements[i] = AchievementInfo{
			AchievementID: record.AchievementID,
			Progress:      record.Progress,
			Completed:     record.Completed,
			Rewarded:      record.Rewarded,
		}

		if config, ok := configMap[record.AchievementID]; ok {
			achievements[i].Name = config.Name
			achievements[i].Description = config.Description
			achievements[i].TargetValue = config.TargetValue
		}
	}

	return &AchievementListResult{
		Achievements: achievements,
		Total:        int32(len(achievements)),
	}, nil
}

func (s *AchievementService) ClaimAchievementReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32) (*AchievementRewardResult, error) {
	logger.Info("claim achievement reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("achievement_id", achievementID),
		logger.Uint32("reward_type", rewardType),
	)

	var record models.AchievementRecord
	if err := s.db.DB.Where("role_id = ? AND achievement_id = ?", roleID, achievementID).First(&record).Error; err != nil {
		logger.Error("achievement record not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("achievement_id", achievementID),
		)
		return nil, fmt.Errorf("achievement not found")
	}

	if !record.Completed {
		return nil, fmt.Errorf("achievement not completed")
	}

	if record.Rewarded {
		return nil, fmt.Errorf("reward already claimed")
	}

	var config models.AchievementConfig
	if err := s.db.DB.Where("achievement_id = ?", achievementID).First(&config).Error; err != nil {
		logger.Error("achievement config not found",
			logger.ErrorField(err),
			logger.Uint32("achievement_id", achievementID),
		)
		return nil, fmt.Errorf("achievement config not found")
	}

	tx := s.db.DB.Begin()

	now := time.Now()
	record.Rewarded = true
	record.RewardTime = now
	if err := tx.Save(&record).Error; err != nil {
		tx.Rollback()
		logger.Error("failed to update achievement record",
			logger.ErrorField(err),
		)
		return nil, err
	}

	reward := &models.AchievementReward{
		RoleID:        roleID,
		AchievementID: achievementID,
		RewardType:    rewardType,
		RewardIndex:   config.RewardIndex,
		RewardCount:   config.RewardCount,
		Claimed:       true,
		ClaimTime:     now,
	}
	if err := tx.Create(reward).Error; err != nil {
		tx.Rollback()
		logger.Error("failed to create achievement reward record",
			logger.ErrorField(err),
		)
		return nil, err
	}

	if err := tx.Commit().Error; err != nil {
		logger.Error("failed to commit transaction",
			logger.ErrorField(err),
		)
		return nil, err
	}

	result := &AchievementRewardResult{
		AdventureUnionLevel: 1,
		AdventureUnionExp:   1,
		ConsumeItems: []*dnfv1.StackableItem{
			{
				Index:           config.RewardIndex,
				Count:           config.RewardCount,
				Bind:            false,
				AcquisitionTime: uint64(now.Unix()),
			},
		},
		InvenItems: &dnfv1.PT_ITEMS{
			ConsumeItems: []*dnfv1.StackableItem{
				{
					Index:           config.RewardIndex,
					Count:           config.RewardCount,
					Bind:            false,
					AcquisitionTime: uint64(now.Unix()),
				},
			},
		},
	}

	return result, nil
}

func (s *AchievementService) ClaimAchievementBonusReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType, rewardIndex, rewardCount uint32) ([]*dnfv1.StackableItem, error) {
	logger.Info("claim achievement bonus reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("achievement_id", achievementID),
		logger.Uint32("reward_type", rewardType),
		logger.Uint32("reward_index", rewardIndex),
		logger.Uint32("reward_count", rewardCount),
	)

	var record models.AchievementRecord
	if err := s.db.DB.Where("role_id = ? AND achievement_id = ?", roleID, achievementID).First(&record).Error; err != nil {
		logger.Error("achievement record not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("achievement_id", achievementID),
		)
		return nil, fmt.Errorf("achievement not found")
	}

	if !record.Completed {
		return nil, fmt.Errorf("achievement not completed")
	}

	var config models.AchievementConfig
	if err := s.db.DB.Where("achievement_id = ?", achievementID).First(&config).Error; err != nil {
		logger.Error("achievement config not found",
			logger.ErrorField(err),
			logger.Uint32("achievement_id", achievementID),
		)
		return nil, fmt.Errorf("achievement config not found")
	}

	if !config.BonusReward {
		return nil, fmt.Errorf("achievement does not have bonus reward")
	}

	var existingReward models.AchievementReward
	err := s.db.DB.Where("role_id = ? AND achievement_id = ? AND reward_type = ? AND reward_index = ?",
		roleID, achievementID, rewardType, rewardIndex).First(&existingReward).Error
	if err == nil && existingReward.Claimed {
		return nil, fmt.Errorf("bonus reward already claimed")
	}

	tx := s.db.DB.Begin()

	now := time.Now()
	if existingReward.ID == 0 {
		newReward := &models.AchievementReward{
			RoleID:        roleID,
			AchievementID: achievementID,
			RewardType:    rewardType,
			RewardIndex:   rewardIndex,
			RewardCount:   rewardCount,
			Claimed:       true,
			ClaimTime:     now,
		}
		if err := tx.Create(newReward).Error; err != nil {
			tx.Rollback()
			logger.Error("failed to create bonus reward record",
				logger.ErrorField(err),
			)
			return nil, err
		}
	} else {
		existingReward.Claimed = true
		existingReward.ClaimTime = now
		if err := tx.Save(&existingReward).Error; err != nil {
			tx.Rollback()
			logger.Error("failed to update bonus reward record",
				logger.ErrorField(err),
			)
			return nil, err
		}
	}

	if err := tx.Commit().Error; err != nil {
		logger.Error("failed to commit transaction",
			logger.ErrorField(err),
		)
		return nil, err
	}

	rewards := []*dnfv1.StackableItem{
		{
			Index:           rewardIndex,
			Count:           rewardCount,
			Bind:            false,
			AcquisitionTime: uint64(now.Unix()),
		},
	}

	return rewards, nil
}
