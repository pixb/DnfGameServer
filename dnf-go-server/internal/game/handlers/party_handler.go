package handlers

import (
	"context"

	"google.golang.org/protobuf/proto"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/game/party_service"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
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

// ControlGroupHandler 控制队伍(2026-09-07 第四十八轮兼容文本命令):
// protobuf ControlGroupRequest 直读; 文本命令(CREATE_PARTY/LEAVE_PARTY/KICK_OUT_MEMBER)
// 经 textExtras JSON payload 传 type/targetguid/partyguid(与 protobuf 同构)
func ControlGroupHandler(session *network.Session, msg proto.Message) {
	ctx := context.Background()

	action := uint32(0)
	var targetGuid, partyGuid uint64
	var setting *store.PartySetting
	req, ok := msg.(*dnfv1.ControlGroupRequest)
	if ok {
		action = req.Type
		targetGuid = req.Targetguid
		partyGuid = req.Partyguid
		// 2026-09-07 第五十轮: MODIFY_PARTY_SETTING(type=6) 设置字段
		// 注意: 文本命令 payload 与 proto 字段重合时 protojson 已填充 req,
		// 故 protobuf 分支同样需要构建 setting
		if action == 6 {
			setting = &store.PartySetting{}
			if req.Partyname != "" {
				setting.Name = &req.Partyname
			}
			if req.Dungeonindex != 0 {
				u := req.Dungeonindex
				setting.DungeonIndex = &u
			}
			if req.Minlevel != 0 {
				u := req.Minlevel
				setting.MinLevel = &u
			}
			if req.Maxlevel != 0 {
				u := req.Maxlevel
				setting.MaxLevel = &u
			}
			if req.Area != 0 {
				u := req.Area
				setting.Area = &u
			}
			// 2026-09-07 第五十一轮: public_type 无 proto 字段(protojson 丢弃),
			// 文本命令场景从 textExtras 读; 0=公开(合法值, 仅字段存在时更新)
			if extras, exists := session.GetAttr("textExtras"); exists {
				if m, ok := extras.(map[string]interface{}); ok {
					if v, ok := m["publictype"].(float64); ok {
						u := uint32(v)
						setting.PublicType = &u
					}
				}
			}
		}
	} else if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["type"].(float64); ok {
				action = uint32(v)
			}
			if v, ok := m["targetguid"].(float64); ok {
				targetGuid = uint64(v)
			}
			if v, ok := m["partyguid"].(float64); ok {
				partyGuid = uint64(v)
			}
			// 2026-09-07 第五十轮: MODIFY_PARTY_SETTING(type=6) 设置字段
			if action == 6 {
				setting = &store.PartySetting{}
				if v, ok := m["partyname"].(string); ok {
					setting.Name = &v
				}
				if v, ok := m["dungeonindex"].(float64); ok {
					u := uint32(v)
					setting.DungeonIndex = &u
				}
				if v, ok := m["minlevel"].(float64); ok {
					u := uint32(v)
					setting.MinLevel = &u
				}
				if v, ok := m["maxlevel"].(float64); ok {
					u := uint32(v)
					setting.MaxLevel = &u
				}
				if v, ok := m["area"].(float64); ok {
					u := uint32(v)
					setting.Area = &u
				}
			}
		}
	} else {
		// 发送错误响应
		errorResp := &dnfv1.ControlGroupResponse{
			Error: 1,
		}
		session.WriteResponse(10009, 5, errorResp)
		return
	}

	// 2026-09-07 第五十轮: MODIFY_PARTY_SETTING 走 UpdatePartySetting(设置字段非 ControlGroup 签名可表达)
	if action == 6 {
		err := partySvc.UpdatePartySetting(ctx, session.RoleID(), setting)
		if err != nil {
			errorResp := &dnfv1.ControlGroupResponse{Error: 1}
			session.WriteResponse(10009, 5, errorResp)
			return
		}
		resp := &dnfv1.ControlGroupResponse{Error: 0, Type: action}
		session.WriteResponse(10009, 5, resp)
		return
	}

	err := partySvc.ControlGroup(ctx, session.RoleID(), action, targetGuid, partyGuid)
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
		Type:  action,
	}
	session.WriteResponse(10009, 5, resp)

	// 2026-09-07 第六十二轮: 加入成功后向全队广播最新队伍信息
	if action == 5 && partyGuid != 0 {
		broadcastPartyUpdate(session, partyGuid)
	}
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

// HalfOpenPartyAcceptHandler 半公开队伍接受(2026-09-07 第五十二轮: 文本命令可带 targetguid 指定申请者)
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

	var targetGuid uint64
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["targetguid"].(float64); ok {
				targetGuid = uint64(v)
			}
		}
	}

	err := partySvc.HalfOpenPartyAccept(ctx, session.RoleID(), req.Partyguid, targetGuid)
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

	// 2026-09-07 第六十二轮: 接受申请后向全队广播最新队伍信息
	broadcastPartyUpdate(session, req.Partyguid)
}

// HalfOpenPartyRefuseHandler 半公开队伍拒绝(2026-09-07 第五十二轮: 文本命令可带 targetguid 指定申请者)
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

	var targetGuid uint64
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["targetguid"].(float64); ok {
				targetGuid = uint64(v)
			}
		}
	}

	err := partySvc.HalfOpenPartyRefuse(ctx, session.RoleID(), req.Partyguid, targetGuid)
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

	// 2026-09-07 第六十二轮: 直接加入成功后向全队广播最新队伍信息
	broadcastPartyUpdate(session, req.Partyguid)
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

// broadcastPartyUpdate 队伍成员变化后向全队在线成员广播最新队伍信息(2026-09-07 第六十二轮)
func broadcastPartyUpdate(trigger *network.Session, partyGuid uint64) {
	ctx := context.Background()
	party, err := partySvc.GetPartyByGuid(ctx, partyGuid)
	if err != nil || party == nil {
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
	notify := &dnfv1.PartyUpdateNotify{Info: partyInfo}
	mg := trigger.SessionManager()
	if mg == nil {
		return
	}
	for _, m := range party.Members {
		if m == nil {
			continue
		}
		for _, s := range mg.GetAll() {
			if s != nil && s.RoleID() == m.Charguid && s.ID() != trigger.ID() {
				if err := s.WriteResponse(10009, 34, notify); err != nil {
					logger.Error("failed to send party update notify",
						logger.ErrorField(err),
						logger.Int64("session_id", s.ID()),
					)
				}
			}
		}
	}
}
