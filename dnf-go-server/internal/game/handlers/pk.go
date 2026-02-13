package handlers

import (
	"context"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/pk_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

var pkSvc *pk_service.PkService

// InitPkService 初始化 PK 服务
func InitPkService(service *pk_service.PkService) {
	pkSvc = service
}

// MultiPlayRequestMatchHandler 多人游戏请求匹配处理器
func MultiPlayRequestMatchHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.MultiPlayRequestMatchRequest)
	if !ok {
		logger.Error("invalid message type for multi play request match")
		return
	}

	logger.Info("multi play request match received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
		logger.Uint32("match_type", req.Matchtype),
		logger.Uint32("dungeon_index", req.Dungeonindex),
	)

	// TODO: 实现实际的匹配逻辑
	// 1. 验证玩家状态
	// 2. 查找匹配的对手
	// 3. 创建匹配记录
	// 4. 返回匹配结果

	result, err := pkSvc.RequestMatch(context.Background(), session.RoleID(), req.Matchtype, req.Dungeonindex)
	if err != nil {
		resp := &dnfv1.MultiPlayRequestMatchResponse{
			TransId: req.TransId,
			Error:   3,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 0, resp); err != nil {
			logger.Error("failed to send multi play request match response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.MultiPlayRequestMatchResponse{
		TransId:      req.TransId,
		Error:        0,
		Matchingguid: result.MatchingGuid,
		Bip:          result.IP,
		Bport:        result.Port,
	}

	if err := session.WriteResponse(10008, 0, resp); err != nil {
		logger.Error("failed to send multi play request match response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// MultiPlayRequestMatchCancelHandler 取消多人游戏请求匹配处理器
func MultiPlayRequestMatchCancelHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.MultiPlayRequestMatchCancelRequest)
	if !ok {
		logger.Error("invalid message type for multi play request match cancel")
		return
	}

	logger.Info("multi play request match cancel received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
		logger.Uint64("matching_guid", req.Matchingguid),
	)

	// TODO: 实现实际的取消匹配逻辑
	// 1. 验证匹配ID
	// 2. 取消匹配
	// 3. 清理匹配记录

	err := pkSvc.CancelMatch(context.Background(), session.RoleID(), req.Matchingguid)
	if err != nil {
		resp := &dnfv1.MultiPlayRequestMatchCancelResponse{
			TransId: req.TransId,
			Error:   3,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 2, resp); err != nil {
			logger.Error("failed to send multi play request match cancel response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.MultiPlayRequestMatchCancelResponse{
		TransId: req.TransId,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 2, resp); err != nil {
		logger.Error("failed to send multi play request match cancel response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// HistoricSiteNotiHandler 历史遗迹通知处理器
func HistoricSiteNotiHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.HistoricSiteNotiRequest)
	if !ok {
		logger.Error("invalid message type for historic site noti")
		return
	}

	logger.Info("historic site noti received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
	)

	// TODO: 实现实际的历史遗迹通知逻辑

	resp := &dnfv1.HistoricSiteNotiResponse{
		TransId: req.TransId,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 4, resp); err != nil {
		logger.Error("failed to send historic site noti response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// LoadGuildDonationInfoHandler 加载公会捐赠信息处理器
func LoadGuildDonationInfoHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.LoadGuildDonationInfoRequest)
	if !ok {
		logger.Error("invalid message type for load guild donation info")
		return
	}

	logger.Info("load guild donation info received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
	)

	// TODO: 实现实际的加载公会捐赠信息逻辑
	// 1. 查询公会捐赠配方
	// 2. 返回配方列表

	recipes, err := pkSvc.GetGuildDonationRecipes(context.Background())
	if err != nil {
		resp := &dnfv1.LoadGuildDonationInfoResponse{
			TransId: req.TransId,
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 6, resp); err != nil {
			logger.Error("failed to send load guild donation info response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.LoadGuildDonationInfoResponse{
		TransId: req.TransId,
		Recipe:  recipes,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 6, resp); err != nil {
		logger.Error("failed to send load guild donation info response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// DreamMazeBasicInfoHandler 梦境迷宫基本信息处理器
func DreamMazeBasicInfoHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.DreamMazeBasicInfoRequest)
	if !ok {
		logger.Error("invalid message type for dream maze basic info")
		return
	}

	logger.Info("dream maze basic info received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
	)

	// TODO: 实现实际的梦境迷宫基本信息逻辑

	resp := &dnfv1.DreamMazeBasicInfoResponse{
		TransId: req.TransId,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 8, resp); err != nil {
		logger.Error("failed to send dream maze basic info response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// RaidEntranceCountHandler 副本入场次数处理器
func RaidEntranceCountHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.RaidEntranceCountRequest)
	if !ok {
		logger.Error("invalid message type for raid entrance count")
		return
	}

	logger.Info("raid entrance count received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
	)

	// TODO: 实现实际的副本入场次数逻辑
	// 1. 查询玩家副本入场记录
	// 2. 返回入场次数信息

	// 模拟返回副本入场次数
	resp := &dnfv1.RaidEntranceCountResponse{
		TransId: req.TransId,
		Entrance: []*dnfv1.RaidEntranceInfo{
			{
				Raidindex:            1,
				Dailycharacter:       1,
				Character:            3,
				Account:              12,
				Dailyrewardcharacter: 1,
				Rewardcharacter:      3,
				Rewardaccount:        12,
			},
			{
				Raidindex:            2,
				Dailycharacter:       1,
				Character:            1,
				Account:              4,
				Dailyrewardcharacter: 1,
				Rewardcharacter:      1,
				Rewardaccount:        4,
			},
		},
		Error: 0,
	}

	if err := session.WriteResponse(10008, 10, resp); err != nil {
		logger.Error("failed to send raid entrance count response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// LoadingProgressHandler 加载进度处理器
func LoadingProgressHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.LoadingProgressRequest)
	if !ok {
		logger.Error("invalid message type for loading progress")
		return
	}

	logger.Info("loading progress received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
		logger.Uint32("value", req.Value),
	)

	// TODO: 实现实际的加载进度逻辑
	// 1. 更新玩家加载进度
	// 2. 广播给所有PVP角色

	resp := &dnfv1.LoadingProgressResponse{
		TransId:      req.TransId,
		Matchingguid: session.MatchingID(),
		Charguid:     session.RoleID(),
		Value:        req.Value,
	}

	if err := session.WriteResponse(10008, 12, resp); err != nil {
		logger.Error("failed to send loading progress response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// ReturnToTownAtMultiPlayHandler 返回城镇处理器
func ReturnToTownAtMultiPlayHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.ReturnToTownAtMultiPlayRequest)
	if !ok {
		logger.Error("invalid message type for return to town at multi play")
		return
	}

	logger.Info("return to town at multi play received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
	)

	// TODO: 实现实际的返回城镇逻辑
	// 1. 清除匹配状态
	// 2. 更新玩家位置
	// 3. 返回城镇

	resp := &dnfv1.ReturnToTownAtMultiPlayResponse{
		TransId: req.TransId,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 14, resp); err != nil {
		logger.Error("failed to send return to town at multi play response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// CustomGameRoomSettingHandler 自定义游戏房间设置处理器
func CustomGameRoomSettingHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.CustomGameRoomSettingRequest)
	if !ok {
		logger.Error("invalid message type for custom game room setting")
		return
	}

	logger.Info("custom game room setting received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("trans_id", req.TransId),
	)

	// TODO: 实现实际的自定义游戏房间设置逻辑
	// 1. 验证队长权限
	// 2. 设置房间参数
	// 3. 通知所有队员

	resp := &dnfv1.CustomGameRoomSettingResponse{
		TransId: req.TransId,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 16, resp); err != nil {
		logger.Error("failed to send custom game room setting response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpRecordHandler PK 记录处理器
func PvpRecordHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.PvpRecordRequest)
	if !ok {
		logger.Error("invalid message type for pvp record")
		return
	}

	logger.Info("pvp record received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint64("charguid", req.Charguid),
	)

	// TODO: 实现实际的 PK 记录逻辑
	// 1. 查询玩家 PK 记录
	// 2. 返回记录列表

	records, err := pkSvc.GetPvpRecord(context.Background(), session.RoleID())
	if err != nil {
		resp := &dnfv1.PvpRecordResponse{
			Records: []*dnfv1.PvpRecordInfo{},
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 20, resp); err != nil {
			logger.Error("failed to send pvp record response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.PvpRecordResponse{
		Records: records,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 20, resp); err != nil {
		logger.Error("failed to send pvp record response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpRankingHandler PK 排名处理器
func PvpRankingHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.PvpRankingRequest)
	if !ok {
		logger.Error("invalid message type for pvp ranking")
		return
	}

	logger.Info("pvp ranking received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint32("match_type", req.MatchType),
		logger.Uint32("page", req.Page),
		logger.Uint32("page_size", req.PageSize),
	)

	// TODO: 实现实际的 PK 排名逻辑
	// 1. 查询 PK 排行榜
	// 2. 返回排名列表

	resp := &dnfv1.PvpRankingResponse{
		Rankings: []*dnfv1.PvpRankingInfo{},
		Error:    0,
	}

	if err := session.WriteResponse(10008, 22, resp); err != nil {
		logger.Error("failed to send pvp ranking response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpStatsHandler PK 统计处理器
func PvpStatsHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.PvpStatsRequest)
	if !ok {
		logger.Error("invalid message type for pvp stats")
		return
	}

	logger.Info("pvp stats received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint64("charguid", req.Charguid),
	)

	// TODO: 实现实际的 PK 统计逻辑
	// 1. 查询玩家 PK 统计
	// 2. 返回统计信息

	stats, err := pkSvc.GetPvpStats(context.Background(), session.RoleID())
	if err != nil {
		resp := &dnfv1.PvpStatsResponse{
			Stats:   &dnfv1.PvpStatsInfo{},
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 24, resp); err != nil {
			logger.Error("failed to send pvp stats response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.PvpStatsResponse{
		Stats: stats,
		Error: 0,
	}

	if err := session.WriteResponse(10008, 24, resp); err != nil {
		logger.Error("failed to send pvp stats response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpMatchHistoryHandler PK 匹配历史处理器
func PvpMatchHistoryHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.PvpMatchHistoryRequest)
	if !ok {
		logger.Error("invalid message type for pvp match history")
		return
	}

	logger.Info("pvp match history received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint64("charguid", req.Charguid),
		logger.Uint32("page", req.Page),
		logger.Uint32("page_size", req.PageSize),
	)

	// TODO: 实现实际的 PK 匹配历史逻辑
	// 1. 查询玩家匹配历史
	// 2. 返回历史列表

	resp := &dnfv1.PvpMatchHistoryResponse{
		History: []*dnfv1.PvpMatchHistoryInfo{},
		Error:   0,
	}

	if err := session.WriteResponse(10008, 26, resp); err != nil {
		logger.Error("failed to send pvp match history response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpSeasonInfoHandler PK 赛季信息处理器
func PvpSeasonInfoHandler(session *network.Session, msg proto.Message) {
	_, ok := msg.(*dnfv1.PvpSeasonInfoRequest)
	if !ok {
		logger.Error("invalid message type for pvp season info")
		return
	}

	logger.Info("pvp season info received",
		logger.Uint64("role_id", session.RoleID()),
	)

	// TODO: 实现实际的 PK 赛季信息逻辑
	// 1. 查询当前赛季信息
	// 2. 返回赛季信息

	_, err := pkSvc.GetPvpSeasonInfo(context.Background())
	if err != nil {
		resp := &dnfv1.PvpSeasonInfoResponse{
			Season:  &dnfv1.PvpSeasonInfo{},
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 28, resp); err != nil {
			logger.Error("failed to send pvp season info response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.PvpSeasonInfoResponse{
		Season: &dnfv1.PvpSeasonInfo{},
		Error:  0,
	}

	if err := session.WriteResponse(10008, 28, resp); err != nil {
		logger.Error("failed to send pvp season info response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpRewardHandler PK 奖励处理器
func PvpRewardHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.PvpRewardRequest)
	if !ok {
		logger.Error("invalid message type for pvp reward")
		return
	}

	logger.Info("pvp reward received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint64("charguid", req.Charguid),
	)

	// TODO: 实现实际的 PK 奖励逻辑
	// 1. 查询玩家可领取奖励
	// 2. 返回奖励列表

	rewards, err := pkSvc.GetPvpReward(context.Background(), session.RoleID())
	if err != nil {
		resp := &dnfv1.PvpRewardResponse{
			Rewards: []*dnfv1.PvpRewardInfo{},
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 30, resp); err != nil {
			logger.Error("failed to send pvp reward response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.PvpRewardResponse{
		Rewards: rewards,
		Error:   0,
	}

	if err := session.WriteResponse(10008, 30, resp); err != nil {
		logger.Error("failed to send pvp reward response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpDailyResetHandler PK 每日重置处理器
func PvpDailyResetHandler(session *network.Session, msg proto.Message) {
	_, ok := msg.(*dnfv1.PvpDailyResetRequest)
	if !ok {
		logger.Error("invalid message type for pvp daily reset")
		return
	}

	logger.Info("pvp daily reset received",
		logger.Uint64("role_id", session.RoleID()),
	)

	// TODO: 实现实际的 PK 每日重置逻辑
	// 1. 重置每日匹配次数
	// 2. 重置每日奖励

	resp := &dnfv1.PvpDailyResetResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10008, 32, resp); err != nil {
		logger.Error("failed to send pvp daily reset response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpMatchTypesHandler PK 匹配类型处理器
func PvpMatchTypesHandler(session *network.Session, msg proto.Message) {
	_, ok := msg.(*dnfv1.PvpMatchTypesRequest)
	if !ok {
		logger.Error("invalid message type for pvp match types")
		return
	}

	logger.Info("pvp match types received",
		logger.Uint64("role_id", session.RoleID()),
	)

	// TODO: 实现实际的 PK 匹配类型逻辑
	// 1. 查询所有匹配类型
	// 2. 返回类型列表

	_, err := pkSvc.GetPvpMatchTypes(context.Background())
	if err != nil {
		resp := &dnfv1.PvpMatchTypesResponse{
			Types:   []*dnfv1.PvpMatchTypeInfo{},
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 34, resp); err != nil {
			logger.Error("failed to send pvp match types response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.PvpMatchTypesResponse{
		Types: []*dnfv1.PvpMatchTypeInfo{},
		Error: 0,
	}

	if err := session.WriteResponse(10008, 34, resp); err != nil {
		logger.Error("failed to send pvp match types response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}

// PvpBattleResultHandler PK 战斗结果处理器
func PvpBattleResultHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.PvpBattleResultRequest)
	if !ok {
		logger.Error("invalid message type for pvp battle result")
		return
	}

	logger.Info("pvp battle result received",
		logger.Uint64("role_id", session.RoleID()),
		logger.Uint64("matchingguid", req.Matchingguid),
		logger.Bool("win", req.Win),
		logger.Int32("score", req.Score),
		logger.Uint64("opponent_id", req.OpponentId),
	)

	// TODO: 实现实际的 PK 战斗结果逻辑
	// 1. 记录战斗结果
	// 2. 更新玩家统计
	// 3. 计算积分变化

	err := pkSvc.SubmitPvpBattleResult(context.Background(), session.RoleID(), req.Matchingguid, req.OpponentId, req.Win, req.Score)
	if err != nil {
		resp := &dnfv1.PvpBattleResultResponse{
			Error:   1,
			Message: err.Error(),
		}
		if err := session.WriteResponse(10008, 36, resp); err != nil {
			logger.Error("failed to send pvp battle result response",
				logger.ErrorField(err),
				logger.Uint64("role_id", session.RoleID()),
			)
		}
		return
	}

	resp := &dnfv1.PvpBattleResultResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10008, 36, resp); err != nil {
		logger.Error("failed to send pvp battle result response",
			logger.ErrorField(err),
			logger.Uint64("role_id", session.RoleID()),
		)
	}
}
