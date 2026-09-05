package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// ==================== 排名模块 (Module = 10501) ====================
// 说明: HTTP 层已有 /rank/* 接口实现,此处先接通 TCP 命令链路。

func writeRankOK(session *network.Session, respCmd uint16, name string) {
	if err := session.WriteResponse(10501, respCmd, &dnfv1.Empty{}); err != nil {
		logger.Error("failed to send "+name+" response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// QueryMyRankHandler 处理查询我的排名请求 (cmd=0)
func QueryMyRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query my rank")
		return
	}
	logger.Info("query my rank request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询角色在指定榜单的排名
	_ = req
	writeRankOK(session, 1, "query my rank")
}

// QueryPersonalRankHandler 处理查询个人排名请求 (cmd=2)
func QueryPersonalRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query personal rank")
		return
	}
	logger.Info("query personal rank request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询指定角色在榜单的排名
	_ = req
	writeRankOK(session, 3, "query personal rank")
}

// QueryFriendRankHandler 处理查询好友排名请求 (cmd=4)
func QueryFriendRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query friend rank")
		return
	}
	logger.Info("query friend rank request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询好友榜单排名
	_ = req
	writeRankOK(session, 5, "query friend rank")
}

// QueryMyTeamRankHandler 处理查询我的队伍排名请求 (cmd=6)
func QueryMyTeamRankHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query my team rank")
		return
	}
	logger.Info("query my team rank request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询角色所在队伍的榜单排名
	_ = req
	writeRankOK(session, 7, "query my team rank")
}
