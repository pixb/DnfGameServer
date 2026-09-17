package handlers

import (
	"context"
	"sort"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"google.golang.org/protobuf/proto"
)

// ==================== 排名模块 (Module = 10501) ====================
// 2026-09-06 第十九轮 实化: 排名查询接入真实角色数据(等级榜: 等级降序+经验降序, 与 HTTP /rank/* 同语义)。
// 客户端经 TCP 文本命令带 JSON payload(QUERY_MY_RANK:{"rank_type":1}), 由 codec TextExtras 透传;
// 响应仍为 Empty(协议未扩展), 查询结果经服务端日志可观测。

var rankStore *store.Store

// InitRankStore 初始化排名 Store
func InitRankStore(s *store.Store) {
	rankStore = s
}

// rankTypeFromPayload 读取排名类型(缺省 1=等级榜, 2=战力榜, 3=副本榜)
func rankTypeFromPayload(session *network.Session) uint32 {
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["rank_type"].(float64); ok {
				return uint32(v)
			}
		}
	}
	return 1
}

// uint32ToInt32 uint32 转 int32(供响应字段赋值)
func uint32ToInt32(v uint32) int32 {
	return int32(v)
}

// rankPosition 查询角色在等级榜的位置(等级降序+经验降序; 非等级榜暂无数据源返回 0)
func rankPosition(roleID uint64) (rank, total int) {
	if rankStore == nil {
		return 0, 0
	}
	roles, err := rankStore.ListRoles(context.Background(), &store.FindRole{})
	if err != nil {
		logger.Error("rank load roles failed", logger.ErrorField(err))
		return 0, 0
	}
	sort.SliceStable(roles, func(i, j int) bool {
		if roles[i].Level != roles[j].Level {
			return roles[i].Level > roles[j].Level
		}
		return roles[i].Exp > roles[j].Exp
	})
	for i, r := range roles {
		if r.ID == roleID {
			return i + 1, len(roles)
		}
	}
	return 0, len(roles)
}

// friendRankPosition 查询角色在好友榜的位置(2026-09-06 第三十四轮):
// 好友(store.Friend) + 自己 按等级降序+经验降序, 返回自己在好友圈中的排名与总数
func friendRankPosition(roleID uint64) (rank, total int) {
	if rankStore == nil {
		return 0, 0
	}
	ctx := context.Background()
	friends, err := rankStore.ListFriends(ctx, roleID)
	if err != nil {
		logger.Error("rank load friends failed", logger.ErrorField(err))
		return 0, 0
	}
	ids := map[uint64]bool{roleID: true}
	for _, f := range friends {
		if f.FriendID > 0 {
			ids[f.FriendID] = true
		}
	}
	roles, err := rankStore.ListRoles(ctx, &store.FindRole{})
	if err != nil {
		logger.Error("rank load roles failed", logger.ErrorField(err))
		return 0, 0
	}
	var circle []*store.Role
	for _, r := range roles {
		if ids[r.ID] {
			circle = append(circle, r)
		}
	}
	sort.SliceStable(circle, func(i, j int) bool {
		if circle[i].Level != circle[j].Level {
			return circle[i].Level > circle[j].Level
		}
		return circle[i].Exp > circle[j].Exp
	})
	for i, r := range circle {
		if r.ID == roleID {
			return i + 1, len(circle)
		}
	}
	// 自己不在角色表(异常): 排在末位
	return len(circle) + 1, len(circle) + 1
}

// writeRankResp 发送带数据的排名响应(2026-09-06 第三十轮: RankResponse 携带 rank/total/rank_type)
func writeRankResp(session *network.Session, respCmd uint16, name string, rankType, rank, total int32) {
	if err := session.WriteResponse(10501, respCmd, &dnfv1.RankResponse{
		RankType: rankType,
		Rank:     rank,
		Total:    total,
	}); err != nil {
		logger.Error("failed to send "+name+" response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// teamRankPosition 查询我的队伍在全服队伍平均等级榜的位置(2026-09-06 第四十三轮实化)
// 数据源: t_party + t_party_member + role(level); 无队伍返回 (0, 0)
func teamRankPosition(roleID uint64) (rank, total int) {
	if rankStore == nil {
		return 0, 0
	}
	r, t, err := rankStore.TeamRankPosition(context.Background(), roleID)
	if err != nil {
		logger.Error("rank load team position failed", logger.ErrorField(err))
		return 0, 0
	}
	return r, t
}

// QueryMyRankHandler 处理查询我的排名请求 (cmd=0)
func QueryMyRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query my rank")
		return
	}
	logger.Info("query my rank request received",
		logger.Int64("session_id", session.ID()),
		logger.Uint32("rank_type", rankTypeFromPayload(session)),
	)
	// 查询角色在指定榜单的排名(等级榜真实计算, 其余榜单暂无数据源)
	rank, total := rankPosition(session.RoleID())
	logger.Info("query my rank result",
		logger.Int64("session_id", session.ID()),
		logger.Int("rank", rank),
		logger.Int("total", total),
	)
	_ = req
	writeRankResp(session, 1, "query my rank", uint32ToInt32(rankTypeFromPayload(session)), int32(rank), int32(total))
}

// QueryPersonalRankHandler 处理查询个人排名请求 (cmd=2)
func QueryPersonalRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query personal rank")
		return
	}
	logger.Info("query personal rank request received",
		logger.Int64("session_id", session.ID()),
		logger.Uint32("rank_type", rankTypeFromPayload(session)),
	)
	// 查询指定角色在榜单的排名(按会话角色)
	rank, total := rankPosition(session.RoleID())
	logger.Info("query personal rank result",
		logger.Int64("session_id", session.ID()),
		logger.Int("rank", rank),
		logger.Int("total", total),
	)
	_ = req
	writeRankResp(session, 3, "query personal rank", uint32ToInt32(rankTypeFromPayload(session)), int32(rank), int32(total))
}

// QueryFriendRankHandler 处理查询好友排名请求 (cmd=4)
func QueryFriendRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query friend rank")
		return
	}
	logger.Info("query friend rank request received",
		logger.Int64("session_id", session.ID()),
		logger.Uint32("rank_type", rankTypeFromPayload(session)),
	)
	// 好友榜单: 好友(Friend 表)+自己按等级降序, 返回自己位置(2026-09-06 第三十四轮实化)
	rank, total := friendRankPosition(session.RoleID())
	logger.Info("query friend rank result",
		logger.Int64("session_id", session.ID()),
		logger.Int("rank", rank),
		logger.Int("total", total),
	)
	_ = req
	writeRankResp(session, 5, "query friend rank", uint32ToInt32(rankTypeFromPayload(session)), int32(rank), int32(total))
}

// QueryMyTeamRankHandler 处理查询我的队伍排名请求 (cmd=6)
func QueryMyTeamRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query my team rank")
		return
	}
	logger.Info("query my team rank request received",
		logger.Int64("session_id", session.ID()),
		logger.Uint32("rank_type", rankTypeFromPayload(session)),
	)
	// 队伍榜单: 全服队伍按平均等级降序, 返回我的队伍位置(2026-09-06 第四十三轮实化, 数据源 t_party/member/role)
	rank, total := teamRankPosition(session.RoleID())
	logger.Info("query my team rank result",
		logger.Int64("session_id", session.ID()),
		logger.Int("rank", rank),
		logger.Int("total", total),
	)
	_ = req
	writeRankResp(session, 7, "query my team rank", uint32ToInt32(rankTypeFromPayload(session)), int32(rank), int32(total))
}
