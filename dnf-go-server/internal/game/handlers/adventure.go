package handlers

import (
	"context"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/adventure_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

var adventureSvc *adventure_service.AdventureService

func InitAdventureService(svc *adventure_service.AdventureService) {
	adventureSvc = svc
}

func AdventureUnionInfoHandler(session *network.Session, msg dnfv1.AdventureUnionInfoRequest) {
	logger.Info("adventure union info request received",
		logger.Uint64("role_id", session.RoleID()),
	)

	info, err := adventureSvc.GetAdventureUnionInfo(context.Background(), session.RoleID())
	if err != nil {
		resp := &dnfv1.AdventureUnionInfoResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17201, 0, resp); err != nil {
			logger.Error("failed to send adventure union info response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionInfoResponse{
		Error:                          0,
		Exp:                            info.Exp,
		Level:                          info.Level,
		Day:                            info.Day,
		Typicalcharacterguid:           info.TypicalCharacterGUID,
		Name:                           info.Name,
		Updatetime:                     uint64(info.UpdateTime.Unix()),
		Lastchangenametime:             uint64(info.LastChangeNameTime.Unix()),
		Shareboardbackground:           info.ShareboardBackground,
		Shareboardframe:                info.ShareboardFrame,
		Shareboardshowantievilscore:    info.ShareboardShowAntiEvilScore,
		Autosearchcount:                info.AutoSearchCount,
		Shareboardtotalantievilscore:   info.ShareboardTotalAntiEvilScore,
		Shareboardantievilscorerefresh: info.ShareboardAntiEvilScoreRefresh,
		IsadventureCondition:           info.IsAdventureCondition,
	}

	if err := session.WriteResponse(17201, 0, resp); err != nil {
		logger.Error("failed to send adventure union info response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionNameChangeHandler(session *network.Session, msg dnfv1.AdventureUnionNameChangeRequest) {
	logger.Info("adventure union name change request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.String("name", msg.Field_1),
	)

	err := adventureSvc.ChangeAdventureUnionName(context.Background(), session.RoleID(), msg.Field_1)
	if err != nil {
		resp := &dnfv1.AdventureUnionNameChangeResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17202, 0, resp); err != nil {
			logger.Error("failed to send adventure union name change response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionNameChangeResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17202, 0, resp); err != nil {
		logger.Error("failed to send adventure union name change response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionExpeditionStartHandler(session *network.Session, msg dnfv1.AdventureUnionExpeditionStartRequest) {
	logger.Info("adventure union expedition start request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("expedition_id", msg.Field_1),
		logger.Int32("expedition_type", msg.Field_2),
	)

	err := adventureSvc.StartAdventureUnionExpedition(context.Background(), session.RoleID(), uint32(msg.Field_1), uint32(msg.Field_2))
	if err != nil {
		resp := &dnfv1.AdventureUnionExpeditionStartResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17203, 0, resp); err != nil {
			logger.Error("failed to send adventure union expedition start response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionExpeditionStartResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17203, 0, resp); err != nil {
		logger.Error("failed to send adventure union expedition start response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionExpeditionCancelHandler(session *network.Session, msg dnfv1.AdventureUnionExpeditionCancelRequest) {
	logger.Info("adventure union expedition cancel request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("expedition_id", msg.Field_1),
	)

	err := adventureSvc.CancelAdventureUnionExpedition(context.Background(), session.RoleID(), uint32(msg.Field_1))
	if err != nil {
		resp := &dnfv1.AdventureUnionExpeditionCancelResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17204, 0, resp); err != nil {
			logger.Error("failed to send adventure union expedition cancel response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionExpeditionCancelResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17204, 0, resp); err != nil {
		logger.Error("failed to send adventure union expedition cancel response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionExpeditionRewardHandler(session *network.Session, msg dnfv1.AdventureUnionExpeditionRewardRequest) {
	logger.Info("adventure union expedition reward request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("expedition_id", msg.Field_1),
	)

	err := adventureSvc.ClaimAdventureUnionExpeditionReward(context.Background(), session.RoleID(), uint32(msg.Field_1))
	if err != nil {
		resp := &dnfv1.AdventureUnionExpeditionRewardResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17205, 0, resp); err != nil {
			logger.Error("failed to send adventure union expedition reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionExpeditionRewardResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17205, 0, resp); err != nil {
		logger.Error("failed to send adventure union expedition reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionSubdueInfoHandler(session *network.Session, msg dnfv1.AdventureUnionSubdueInfoRequest) {
	logger.Info("adventure union subdue info request received",
		logger.Uint64("role_id", session.RoleID()),
	)

	resp := &dnfv1.AdventureUnionSubdueInfoResponse{
		Error: 3,
	}

	if err := session.WriteResponse(17206, 0, resp); err != nil {
		logger.Error("failed to send adventure union subdue info response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionSubdueStartHandler(session *network.Session, msg dnfv1.AdventureUnionSubdueStartRequest) {
	logger.Info("adventure union subdue start request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("subdue_id", msg.Field_1),
		logger.Int32("subdue_type", msg.Field_2),
		logger.Uint64("character_guid", msg.Field_3),
	)

	err := adventureSvc.StartAdventureUnionSubdue(context.Background(), session.RoleID(), uint32(msg.Field_1), uint32(msg.Field_2), msg.Field_3)
	if err != nil {
		resp := &dnfv1.AdventureUnionSubdueStartResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17207, 0, resp); err != nil {
			logger.Error("failed to send adventure union subdue start response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionSubdueStartResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17207, 0, resp); err != nil {
		logger.Error("failed to send adventure union subdue start response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionSubdueRewardHandler(session *network.Session, msg dnfv1.AdventureUnionSubdueRewardRequest) {
	logger.Info("adventure union subdue reward request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("subdue_id", msg.Field_1),
	)

	err := adventureSvc.ClaimAdventureUnionSubdueReward(context.Background(), session.RoleID(), uint32(msg.Field_1))
	if err != nil {
		resp := &dnfv1.AdventureUnionSubdueRewardResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17208, 0, resp); err != nil {
			logger.Error("failed to send adventure union subdue reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionSubdueRewardResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17208, 0, resp); err != nil {
		logger.Error("failed to send adventure union subdue reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionOpenShareboardSlotHandler(session *network.Session, msg dnfv1.AdventureUnionOpenShareboardSlotRequest) {
	logger.Info("adventure union open shareboard slot request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("slot_id", msg.Field_1),
	)

	err := adventureSvc.OpenAdventureUnionShareboardSlot(context.Background(), session.RoleID(), uint32(msg.Field_1))
	if err != nil {
		resp := &dnfv1.AdventureUnionOpenShareboardSlotResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17209, 0, resp); err != nil {
			logger.Error("failed to send adventure union open shareboard slot response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionOpenShareboardSlotResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17209, 0, resp); err != nil {
		logger.Error("failed to send adventure union open shareboard slot response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionSetShareboardHandler(session *network.Session, msg dnfv1.AdventureUnionSetShareboardRequest) {
	logger.Info("adventure union set shareboard request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("field_1", msg.Field_1),
		logger.Int32("field_2", msg.Field_2),
		logger.Bool("field_4", msg.Field_4),
	)

	err := adventureSvc.SetAdventureUnionShareboard(context.Background(), session.RoleID(), uint32(msg.Field_1), uint32(msg.Field_2), msg.Field_4)
	if err != nil {
		resp := &dnfv1.AdventureUnionSetShareboardResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17210, 0, resp); err != nil {
			logger.Error("failed to send adventure union set shareboard response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionSetShareboardResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17210, 0, resp); err != nil {
		logger.Error("failed to send adventure union set shareboard response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureReapInfoHandler(session *network.Session, msg dnfv1.AdventureReapInfoRequest) {
	logger.Info("adventure reap info request received",
		logger.Uint64("role_id", session.RoleID()),
	)

	resp := &dnfv1.AdventureReapInfoResponse{
		Error: 3,
	}

	if err := session.WriteResponse(17212, 0, resp); err != nil {
		logger.Error("failed to send adventure reap info response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureReapRewardHandler(session *network.Session, msg dnfv1.AdventureReapRewardRequest) {
	logger.Info("adventure reap reward request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("reap_id", msg.Field_1),
	)

	err := adventureSvc.ClaimAdventureReapReward(context.Background(), session.RoleID(), uint32(msg.Field_1))
	if err != nil {
		resp := &dnfv1.AdventureReapRewardResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17213, 0, resp); err != nil {
			logger.Error("failed to send adventure reap reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureReapRewardResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17213, 0, resp); err != nil {
		logger.Error("failed to send adventure reap reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionSearchStartHandler(session *network.Session, msg dnfv1.AdventureUnionSearchStartRequest) {
	logger.Info("adventure union search start request received",
		logger.Uint64("role_id", session.RoleID()),
	)

	err := adventureSvc.StartAdventureUnionSearch(context.Background(), session.RoleID())
	if err != nil {
		resp := &dnfv1.AdventureUnionSearchStartResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17214, 0, resp); err != nil {
			logger.Error("failed to send adventure union search start response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionSearchStartResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17214, 0, resp); err != nil {
		logger.Error("failed to send adventure union search start response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionCollectionRewardHandler(session *network.Session, msg dnfv1.AdventureUnionCollectionRewardRequest) {
	logger.Info("adventure union collection reward request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("collection_id", msg.Field_1),
	)

	err := adventureSvc.ClaimAdventureUnionCollectionReward(context.Background(), session.RoleID(), uint32(msg.Field_1))
	if err != nil {
		resp := &dnfv1.AdventureUnionCollectionRewardResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17216, 0, resp); err != nil {
			logger.Error("failed to send adventure union collection reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionCollectionRewardResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17216, 0, resp); err != nil {
		logger.Error("failed to send adventure union collection reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

func AdventureUnionLevelRewardHandler(session *network.Session, msg dnfv1.AdventureUnionLevelRewardRequest) {
	logger.Info("adventure union level reward request received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Int32("level", msg.Field_1),
	)

	err := adventureSvc.ClaimAdventureUnionLevelReward(context.Background(), session.RoleID(), uint32(msg.Field_1))
	if err != nil {
		resp := &dnfv1.AdventureUnionLevelRewardResponse{
			Error: 3,
		}
		if err := session.WriteResponse(17217, 0, resp); err != nil {
			logger.Error("failed to send adventure union level reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.AdventureUnionLevelRewardResponse{
		Error: 0,
	}

	if err := session.WriteResponse(17217, 0, resp); err != nil {
		logger.Error("failed to send adventure union level reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}
