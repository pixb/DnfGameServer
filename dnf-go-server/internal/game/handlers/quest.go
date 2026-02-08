package handlers

import (
	"dnf-go-server/internal/network"
	"dnf-go-server/internal/utils/logger"
	dnfv1 "dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// GetQuestListHandler 处理获取任务列表请求
func GetQuestListHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetQuestListRequest)
	if !ok {
		logger.Error("invalid message type for get quest list")
		return
	}

	logger.Info("get quest list request received",
		logger.String("quest_type", req.QuestType.String()),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 根据任务类型加载任务列表
	resp := &dnfv1.GetQuestListResponse{
		Error: 0,
		Quests: []*dnfv1.QuestInfo{
			{
				QuestId:       1001,
				Name:          "初出茅庐",
				Description:   "完成新手教程",
				QuestType:     dnfv1.QuestType_MAIN,
				State:         dnfv1.QuestState_NOT_ACCEPTED,
				RequiredLevel: 1,
				Objectives: []*dnfv1.QuestObjective{
					{
						ObjectiveId:  1,
						Description:  "与村长对话",
						TargetCount:  1,
						CurrentCount: 0,
						IsCompleted:  false,
					},
				},
				Rewards: []*dnfv1.QuestReward{
					{
						RewardType: 1, // 经验
						RewardId:   0,
						Count:      1000,
					},
					{
						RewardType: 2, // 金币
						RewardId:   0,
						Count:      500,
					},
				},
			},
			{
				QuestId:       2001,
				Name:          "每日训练",
				Description:   "完成3次副本",
				QuestType:     dnfv1.QuestType_DAILY,
				State:         dnfv1.QuestState_IN_PROGRESS,
				RequiredLevel: 10,
				Objectives: []*dnfv1.QuestObjective{
					{
						ObjectiveId:  1,
						Description:  "通关副本",
						TargetCount:  3,
						CurrentCount: 1,
						IsCompleted:  false,
					},
				},
				Rewards: []*dnfv1.QuestReward{
					{
						RewardType: 3, // 物品
						RewardId:   20001,
						Count:      5,
					},
				},
			},
		},
	}

	if err := session.WriteResponse(10006, 1, resp); err != nil {
		logger.Error("failed to send quest list response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// AcceptQuestHandler 处理接受任务请求
func AcceptQuestHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.AcceptQuestRequest)
	if !ok {
		logger.Error("invalid message type for accept quest")
		return
	}

	logger.Info("accept quest request received",
		logger.Int32("quest_id", req.QuestId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证任务可接，创建任务记录
	resp := &dnfv1.AcceptQuestResponse{
		Error: 0,
		Quest: &dnfv1.QuestInfo{
			QuestId:       req.QuestId,
			Name:          "初出茅庐",
			Description:   "完成新手教程",
			QuestType:     dnfv1.QuestType_MAIN,
			State:         dnfv1.QuestState_IN_PROGRESS,
			RequiredLevel: 1,
			Objectives: []*dnfv1.QuestObjective{
				{
					ObjectiveId:  1,
					Description:  "与村长对话",
					TargetCount:  1,
					CurrentCount: 0,
					IsCompleted:  false,
				},
			},
		},
	}

	if err := session.WriteResponse(10006, 3, resp); err != nil {
		logger.Error("failed to send accept quest response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// CompleteQuestHandler 处理完成任务请求
func CompleteQuestHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.CompleteQuestRequest)
	if !ok {
		logger.Error("invalid message type for complete quest")
		return
	}

	logger.Info("complete quest request received",
		logger.Int32("quest_id", req.QuestId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证任务完成条件，标记完成
	resp := &dnfv1.CompleteQuestResponse{
		Error:    0,
		ExpGain:  1000,
		GoldGain: 500,
		Rewards: []*dnfv1.QuestReward{
			{
				RewardType: 1,
				RewardId:   0,
				Count:      1000,
			},
		},
	}

	if err := session.WriteResponse(10006, 5, resp); err != nil {
		logger.Error("failed to send complete quest response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// GetQuestRewardHandler 处理领取任务奖励请求
func GetQuestRewardHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetQuestRewardRequest)
	if !ok {
		logger.Error("invalid message type for get quest reward")
		return
	}

	logger.Info("get quest reward request received",
		logger.Int32("quest_id", req.QuestId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 发放奖励，更新任务状态
	resp := &dnfv1.GetQuestRewardResponse{
		Error: 0,
		Items: []*dnfv1.BagItem{
			{
				Guid:   7001,
				ItemId: 20001,
				Count:  5,
				Slot:   30,
			},
		},
	}

	if err := session.WriteResponse(10006, 7, resp); err != nil {
		logger.Error("failed to send get quest reward response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// AbandonQuestHandler 处理放弃任务请求
func AbandonQuestHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.AbandonQuestRequest)
	if !ok {
		logger.Error("invalid message type for abandon quest")
		return
	}

	logger.Info("abandon quest request received",
		logger.Int32("quest_id", req.QuestId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 删除任务记录
	resp := &dnfv1.AbandonQuestResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10006, 9, resp); err != nil {
		logger.Error("failed to send abandon quest response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// QuestProgressNotifyHandler 发送任务进度更新通知
func QuestProgressNotify(session *network.Session, questId int32, objectiveId int32, currentCount int32, isCompleted bool) {
	notify := &dnfv1.QuestProgressNotify{
		QuestId:      questId,
		ObjectiveId:  objectiveId,
		CurrentCount: currentCount,
		IsCompleted:  isCompleted,
	}

	if err := session.WriteResponse(10006, 100, notify); err != nil {
		logger.Error("failed to send quest progress notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// QuestCompletedNotifyHandler 发送任务完成通知
func QuestCompletedNotify(session *network.Session, questId int32, questName string) {
	notify := &dnfv1.QuestCompletedNotify{
		QuestId:   questId,
		QuestName: questName,
	}

	if err := session.WriteResponse(10006, 101, notify); err != nil {
		logger.Error("failed to send quest completed notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
