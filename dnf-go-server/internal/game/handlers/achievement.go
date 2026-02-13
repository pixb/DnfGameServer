package handlers

import (
	"context"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/achievement_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

var achievementSvc *achievement_service.AchievementService

func InitAchievementService(svc *achievement_service.AchievementService) {
	achievementSvc = svc
}

func AchievementInfoHandler(session *network.Session, msg dnfv1.AchievementInfoRequest) {
	logger.Info("achievement info request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("field_1", msg.Field_1),
	)

	achievements, err := achievementSvc.GetAchievementInfo(context.Background(), session.RoleID(), msg.Field_1)
	if err != nil {
		resp := &dnfv1.AchievementInfoResponse{
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10700, 0, resp); err != nil {
			logger.Error("failed to send achievement info response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	achievementInfos := make([]*dnfv1.AchievementInfo, len(achievements))
	for i, ach := range achievements {
		achievementInfos[i] = &dnfv1.AchievementInfo{
			AchievementId: ach.AchievementID,
			Name:          ach.Name,
			Description:   ach.Description,
			Progress:      ach.Progress,
			TargetValue:   ach.TargetValue,
			Completed:     ach.Completed,
			Rewarded:      ach.Rewarded,
		}
	}

	resp := &dnfv1.AchievementInfoResponse{
		Achievements: achievementInfos,
		Error:        0,
	}

	if err := session.WriteResponse(10700, 0, resp); err != nil {
		logger.Error("failed to send achievement info response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AchievementRewardHandler(session *network.Session, msg dnfv1.AchievementRewardRequest) {
	logger.Info("achievement reward request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("field_1", msg.Field_1),
		logger.Int32("field_2", msg.Field_2),
		logger.Uint64("field_3", msg.Field_3),
	)

	result, err := achievementSvc.ClaimAchievementReward(context.Background(), session.RoleID(), uint32(msg.Field_1), uint32(msg.Field_2))
	if err != nil {
		resp := &dnfv1.AchievementRewardResponse{
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10701, 0, resp); err != nil {
			logger.Error("failed to send achievement reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AchievementRewardResponse{
		Adventureunionlevel: result.AdventureUnionLevel,
		Adventureunionexp:   result.AdventureUnionExp,
		Consumeitems:        result.ConsumeItems,
		Invenitems:          result.InvenItems,
		Error:               0,
	}

	if err := session.WriteResponse(10701, 0, resp); err != nil {
		logger.Error("failed to send achievement reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AchievementListHandler(session *network.Session, msg dnfv1.AchievementListRequest) {
	logger.Info("achievement list request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("field_1", msg.Field_1),
	)

	result, err := achievementSvc.GetAchievementList(context.Background(), session.RoleID(), msg.Field_1)
	if err != nil {
		resp := &dnfv1.AchievementListResponse{
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10704, 0, resp); err != nil {
			logger.Error("failed to send achievement list response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	achievementInfos := make([]*dnfv1.AchievementInfo, len(result.Achievements))
	for i, ach := range result.Achievements {
		achievementInfos[i] = &dnfv1.AchievementInfo{
			AchievementId: ach.AchievementID,
			Name:          ach.Name,
			Description:   ach.Description,
			Progress:      ach.Progress,
			TargetValue:   ach.TargetValue,
			Completed:     ach.Completed,
			Rewarded:      ach.Rewarded,
		}
	}

	resp := &dnfv1.AchievementListResponse{
		Achievements: achievementInfos,
		Total:        result.Total,
		Error:        0,
	}

	if err := session.WriteResponse(10704, 0, resp); err != nil {
		logger.Error("failed to send achievement list response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AchievementBonusRewardHandler(session *network.Session, msg dnfv1.AchievementBonusRewardRequest) {
	logger.Info("achievement bonus reward request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("field_1", msg.Field_1),
		logger.Int32("field_2", msg.Field_2),
		logger.Int32("field_3", msg.Field_3),
		logger.Int32("field_4", msg.Field_4),
		logger.Int32("field_5", msg.Field_5),
	)

	rewards, err := achievementSvc.ClaimAchievementBonusReward(context.Background(), session.RoleID(), uint32(msg.Field_1), uint32(msg.Field_2), uint32(msg.Field_3), uint32(msg.Field_4))
	if err != nil {
		resp := &dnfv1.AchievementBonusRewardResponse{
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10706, 0, resp); err != nil {
			logger.Error("failed to send achievement bonus reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AchievementBonusRewardResponse{
		Rewards: rewards,
		Error:   0,
	}

	if err := session.WriteResponse(10706, 0, resp); err != nil {
		logger.Error("failed to send achievement bonus reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}
