package handlers

import (
	"context"

	"google.golang.org/protobuf/proto"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/party_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

var partySvc *party_service.PartyService

func InitPartyService(svc *party_service.PartyService) {
	partySvc = svc
}

// SearchPartyListHandler 搜索队伍列表
func SearchPartyListHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.SearchPartyListRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.SearchPartyListResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 1, errorResp)
		return
	}

	parties, err := partySvc.SearchPartyList(ctx, req.Dungeonindex, req.Minlevel, req.Maxlevel)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.SearchPartyListResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 1, errorResp)
		return
	}

	partyInfos := make([]*dnfv1.PartyInfo, 0, len(parties))
	for _, party := range parties {
		partyInfo := &dnfv1.PartyInfo{
			Partyguid:    party.PartyGuid,
			Leaderguid:   party.LeaderGuid,
			Name:         party.Name,
			Maxmembers:   party.MaxMembers,
			Members:      party.Members,
			Dungeonindex: party.DungeonIndex,
			Roomid:       uint32(party.RoomID),
			Minlevel:     party.MinLevel,
			Maxlevel:     party.MaxLevel,
			Area:         party.Area,
			Subtype:      party.SubType,
			Stageindex:   party.StageIndex,
			Publictype:   party.PublicType,
		}
		partyInfos = append(partyInfos, partyInfo)
	}

	resp := &dnfv1.SearchPartyListResponse{
		Parties: partyInfos,
		Error:   0,
	}
	session.WriteResponse(10009, 1, resp)
}

// RecommendGroupHandler 推荐队伍
func RecommendGroupHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.RecommendGroupRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.RecommendGroupResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 3, errorResp)
		return
	}

	parties, err := partySvc.RecommendGroup(ctx, req.Dungeonindex)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.RecommendGroupResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 3, errorResp)
		return
	}

	partyInfos := make([]*dnfv1.PartyInfo, 0, len(parties))
	for _, party := range parties {
		partyInfo := &dnfv1.PartyInfo{
			Partyguid:    party.PartyGuid,
			Leaderguid:   party.LeaderGuid,
			Name:         party.Name,
			Maxmembers:   party.MaxMembers,
			Members:      party.Members,
			Dungeonindex: party.DungeonIndex,
			Roomid:       uint32(party.RoomID),
			Minlevel:     party.MinLevel,
			Maxlevel:     party.MaxLevel,
			Area:         party.Area,
			Subtype:      party.SubType,
			Stageindex:   party.StageIndex,
			Publictype:   party.PublicType,
		}
		partyInfos = append(partyInfos, partyInfo)
	}

	resp := &dnfv1.RecommendGroupResponse{
		Parties: partyInfos,
		Error:   0,
	}
	session.WriteResponse(10009, 3, resp)
}

// ControlGroupHandler 控制队伍
func ControlGroupHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.ControlGroupRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.ControlGroupResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 5, errorResp)
		return
	}

	err := partySvc.ControlGroup(ctx, session.RoleID(), req.Type, req.Targetguid, req.Partyguid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.ControlGroupResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 5, errorResp)
		return
	}

	resp := &dnfv1.ControlGroupResponse{
		Error: 0,
		Type:  req.Type,
	}
	session.WriteResponse(10009, 5, resp)
}

// StartMultiPlayHandler 开始多人游戏
func StartMultiPlayHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.StartMultiPlayRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.StartMultiPlayResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 7, errorResp)
		return
	}

	result, err := partySvc.StartMultiPlay(ctx, session.RoleID(), req.Partyguid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.StartMultiPlayResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 7, errorResp)
		return
	}

	resp := &dnfv1.StartMultiPlayResponse{
		Matchingguid: result.MatchingGuid,
		Dungeonguid:  result.DungeonGuid,
		Bip:          result.IP,
		Bport:        result.Port,
		Users:        result.Users,
		Detail:       result.Detail,
		Error:        0,
	}
	session.WriteResponse(10009, 7, resp)
}

// MultiPlaySyncDungeonHandler 同步多人副本
func MultiPlaySyncDungeonHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.MultiPlaySyncDungeonRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.MultiPlaySyncDungeonResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 8, errorResp)
		return
	}

	err := partySvc.SyncDungeon(ctx, session.RoleID(), req.Stageid, req.Progress)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.MultiPlaySyncDungeonResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 8, errorResp)
		return
	}

	resp := &dnfv1.MultiPlaySyncDungeonResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 8, resp)
}

// MultiPlayDungeonEnterCompleteHandler 多人副本进入完成
func MultiPlayDungeonEnterCompleteHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.MultiPlayDungeonEnterCompleteRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.MultiPlayDungeonEnterCompleteResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 9, errorResp)
		return
	}

	err := partySvc.MultiPlayDungeonEnterComplete(ctx, session.RoleID(), req.Stageid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.MultiPlayDungeonEnterCompleteResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 9, errorResp)
		return
	}

	resp := &dnfv1.MultiPlayDungeonEnterCompleteResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 9, resp)
}

// PartyLoadingStatusHandler 获取队伍加载状态
func PartyLoadingStatusHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()

	loadingUsers, readyUsers, err := partySvc.GetPartyLoadingStatus(ctx, session.RoleID())
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.PartyLoadingStatusResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 10, errorResp)
		return
	}

	resp := &dnfv1.PartyLoadingStatusResponse{
		Loadingusers: loadingUsers,
		Readyusers:   readyUsers,
		Error:        0,
		TransId:      0,
	}
	session.WriteResponse(10009, 10, resp)
}

// ReadyToLockstepHandler 准备锁定步骤
func ReadyToLockstepHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.ReadyToLockstepRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.ReadyToLockstepResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 11, errorResp)
		return
	}

	err := partySvc.ReadyToLockstep(ctx, session.RoleID(), req.Ready)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.ReadyToLockstepResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 11, errorResp)
		return
	}

	resp := &dnfv1.ReadyToLockstepResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 11, resp)
}

// VoteKickOutUserHandler 投票踢出用户
func VoteKickOutUserHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.VoteKickOutUserRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.VoteKickOutUserResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 12, errorResp)
		return
	}

	err := partySvc.VoteKickOut(ctx, session.RoleID(), req.Targetguid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.VoteKickOutUserResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 12, errorResp)
		return
	}

	resp := &dnfv1.VoteKickOutUserResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 12, resp)
}

// ConnectBattleServerHandler 连接战斗服务器
func ConnectBattleServerHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.ConnectBattleServerRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.ConnectBattleServerResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 13, errorResp)
		return
	}

	ip, port, err := partySvc.ConnectBattleServer(ctx, req.Matchingguid, req.Charguid, req.Authkey)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.ConnectBattleServerResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 13, errorResp)
		return
	}

	resp := &dnfv1.ConnectBattleServerResponse{
		Ip:      ip,
		Port:    port,
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 13, resp)
}

// CheckProhibitedWordHandler 检查禁用词
func CheckProhibitedWordHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.CheckProhibitedWordRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.CheckProhibitedWordResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 14, errorResp)
		return
	}

	prohibited, err := partySvc.CheckProhibitedWord(ctx, req.Word)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.CheckProhibitedWordResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 14, errorResp)
		return
	}

	resp := &dnfv1.CheckProhibitedWordResponse{
		Prohibited: prohibited,
		Error:      0,
		TransId:    0,
	}
	session.WriteResponse(10009, 14, resp)
}

// HalfOpenPartyAcceptHandler 半公开队伍接受
func HalfOpenPartyAcceptHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.HalfOpenPartyAcceptRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.HalfOpenPartyAcceptResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 15, errorResp)
		return
	}

	err := partySvc.HalfOpenPartyAccept(ctx, session.RoleID(), req.Partyguid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.HalfOpenPartyAcceptResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 15, errorResp)
		return
	}

	resp := &dnfv1.HalfOpenPartyAcceptResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 15, resp)
}

// HalfOpenPartyRefuseHandler 半公开队伍拒绝
func HalfOpenPartyRefuseHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.HalfOpenPartyRefuseRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.HalfOpenPartyRefuseResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 16, errorResp)
		return
	}

	err := partySvc.HalfOpenPartyRefuse(ctx, session.RoleID(), req.Partyguid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.HalfOpenPartyRefuseResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 16, errorResp)
		return
	}

	resp := &dnfv1.HalfOpenPartyRefuseResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 16, resp)
}

// HalfOpenPartyJoinHandler 半公开队伍加入
func HalfOpenPartyJoinHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.HalfOpenPartyJoinRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.HalfOpenPartyJoinResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 17, errorResp)
		return
	}

	err := partySvc.HalfOpenPartyJoin(ctx, session.RoleID(), req.Partyguid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.HalfOpenPartyJoinResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 17, errorResp)
		return
	}

	resp := &dnfv1.HalfOpenPartyJoinResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 17, resp)
}

// PartyDungeonConditionHandler 多人游戏副本条件
func PartyDungeonConditionHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.PartyDungeonConditionRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.PartyDungeonConditionResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 18, errorResp)
		return
	}

	err := partySvc.PartyDungeonCondition(ctx, session.RoleID(), req.Dungeonindex)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.PartyDungeonConditionResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 18, errorResp)
		return
	}

	resp := &dnfv1.PartyDungeonConditionResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 18, resp)
}

// MultiPlayStartDungeonHandler 多人游戏开始副本
func MultiPlayStartDungeonHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.MultiPlayStartDungeonRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.MultiPlayStartDungeonResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 19, errorResp)
		return
	}

	err := partySvc.MultiPlayStartDungeon(ctx, session.RoleID(), req.Stageid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.MultiPlayStartDungeonResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 19, errorResp)
		return
	}

	resp := &dnfv1.MultiPlayStartDungeonResponse{
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 19, resp)
}

// TargetUserPartyInfoHandler 目标用户队伍信息
func TargetUserPartyInfoHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()
	req, ok := msg.(*dnfv1.TargetUserPartyInfoRequest)
	if !ok {
		// 发送错误响应
		errorResp := &dnfv1.TargetUserPartyInfoResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 20, errorResp)
		return
	}

	party, err := partySvc.TargetUserPartyInfo(ctx, session.RoleID(), req.Charguid)
	if err != nil {
		// 发送错误响应
		errorResp := &dnfv1.TargetUserPartyInfoResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 20, errorResp)
		return
	}

	partyInfo := &dnfv1.PartyInfo{
		Partyguid:    party.PartyGuid,
		Leaderguid:   party.LeaderGuid,
		Name:         party.Name,
		Maxmembers:   party.MaxMembers,
		Members:      party.Members,
		Dungeonindex: party.DungeonIndex,
		Roomid:       uint32(party.RoomID),
		Minlevel:     party.MinLevel,
		Maxlevel:     party.MaxLevel,
		Area:         party.Area,
		Subtype:      party.SubType,
		Stageindex:   party.StageIndex,
		Publictype:   party.PublicType,
	}

	resp := &dnfv1.TargetUserPartyInfoResponse{
		Party:   partyInfo,
		Error:   0,
		TransId: 0,
	}
	session.WriteResponse(10009, 20, resp)
}
