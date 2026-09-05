package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// ==================== 事件模块 (Module = 10500) ====================
// 说明: HTTP 层已有 /event/* 接口实现,此处先接通 TCP 命令链路,
// 后续按 TODO 逐步接入事件 service。

func writeEventOK(session *network.Session, respCmd uint16, name string) {
	if err := session.WriteResponse(10500, respCmd, &dnfv1.Empty{}); err != nil {
		logger.Error("failed to send "+name+" response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// QueryEventListHandler 处理查询事件列表请求 (cmd=0)
func QueryEventListHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event list")
		return
	}
	logger.Info("query event list request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询进行中/已结束的活动列表
	_ = req
	writeEventOK(session, 1, "query event list")
}

// QueryEventDetailHandler 处理查询事件详情请求 (cmd=2)
func QueryEventDetailHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event detail")
		return
	}
	logger.Info("query event detail request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询活动规则/奖励/时间详情
	_ = req
	writeEventOK(session, 3, "query event detail")
}

// QueryEventStatusHandler 处理查询事件状态请求 (cmd=4)
func QueryEventStatusHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event status")
		return
	}
	logger.Info("query event status request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询活动开/关状态
	_ = req
	writeEventOK(session, 5, "query event status")
}

// QueryEventProgressHandler 处理查询事件进度请求 (cmd=6)
func QueryEventProgressHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event progress")
		return
	}
	logger.Info("query event progress request received", logger.Int64("session_id", session.ID()))
	// TODO: 查询角色在该活动中的进度
	_ = req
	writeEventOK(session, 7, "query event progress")
}

// TriggerEventHandler 处理触发事件请求 (cmd=8)
func TriggerEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for trigger event")
		return
	}
	logger.Info("trigger event request received", logger.Int64("session_id", session.ID()))
	// TODO: 触发活动条件判定与广播
	_ = req
	writeEventOK(session, 9, "trigger event")
}

// HandleEventHandler 处理事件处理请求 (cmd=10)
func HandleEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for handle event")
		return
	}
	logger.Info("handle event request received", logger.Int64("session_id", session.ID()))
	// TODO: 活动内交互处理
	_ = req
	writeEventOK(session, 11, "handle event")
}

// ValidateEventCompletionHandler 处理校验活动完成请求 (cmd=12)
func ValidateEventCompletionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for validate event completion")
		return
	}
	logger.Info("validate event completion request received", logger.Int64("session_id", session.ID()))
	// TODO: 校验活动完成条件
	_ = req
	writeEventOK(session, 13, "validate event completion")
}

// CreateEventHandler 处理创建事件请求 (cmd=14)
func CreateEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for create event")
		return
	}
	logger.Info("create event request received", logger.Int64("session_id", session.ID()))
	// TODO: 创建新活动(运营后台)
	_ = req
	writeEventOK(session, 15, "create event")
}

// DeleteEventHandler 处理删除事件请求 (cmd=16)
func DeleteEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for delete event")
		return
	}
	logger.Info("delete event request received", logger.Int64("session_id", session.ID()))
	// TODO: 删除活动(运营后台)
	_ = req
	writeEventOK(session, 17, "delete event")
}

// ReceiveEventRewardHandler 处理领取事件奖励请求 (cmd=18)
func ReceiveEventRewardHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for receive event reward")
		return
	}
	logger.Info("receive event reward request received", logger.Int64("session_id", session.ID()))
	// TODO: 校验领取条件,发放活动奖励
	_ = req
	writeEventOK(session, 19, "receive event reward")
}

// DistributeEventRewardHandler 处理发放事件奖励请求 (cmd=20)
func DistributeEventRewardHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for distribute event reward")
		return
	}
	logger.Info("distribute event reward request received", logger.Int64("session_id", session.ID()))
	// TODO: 批量发放活动奖励(运营后台)
	_ = req
	writeEventOK(session, 21, "distribute event reward")
}

// ResetEventHandler 处理重置事件请求 (cmd=22)
func ResetEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for reset event")
		return
	}
	logger.Info("reset event request received", logger.Int64("session_id", session.ID()))
	// TODO: 重置活动周期与进度
	_ = req
	writeEventOK(session, 23, "reset event")
}
