package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// ==================== 日志模块 (Module = 10502) ====================
// 说明: HTTP 层已有 /log/* 接口实现,此处先接通 TCP 命令链路。

func writeLogOK(session *network.Session, respCmd uint16, name string) {
	if err := session.WriteResponse(10502, respCmd, &dnfv1.Empty{}); err != nil {
		logger.Error("failed to send "+name+" response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// QueryLogHandler 处理查询日志请求 (cmd=0)
func QueryLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query log")
		return
	}
	logger.Info("query log request received", logger.Int64("session_id", session.ID()))
	// TODO: 按条件查询行为日志
	_ = req
	writeLogOK(session, 1, "query log")
}

// RecordLogHandler 处理记录日志请求 (cmd=2)
func RecordLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for record log")
		return
	}
	logger.Info("record log request received", logger.Int64("session_id", session.ID()))
	// TODO: 写入行为日志
	_ = req
	writeLogOK(session, 3, "record log")
}

// StatisticLogHandler 处理日志统计请求 (cmd=4)
func StatisticLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for statistic log")
		return
	}
	logger.Info("statistic log request received", logger.Int64("session_id", session.ID()))
	// TODO: 日志聚合统计
	_ = req
	writeLogOK(session, 5, "statistic log")
}

// DeleteLogHandler 处理删除日志请求 (cmd=6)
func DeleteLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for delete log")
		return
	}
	logger.Info("delete log request received", logger.Int64("session_id", session.ID()))
	// TODO: 按条件删除日志
	_ = req
	writeLogOK(session, 7, "delete log")
}

// ExportLogHandler 处理导出日志请求 (cmd=8)
func ExportLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for export log")
		return
	}
	logger.Info("export log request received", logger.Int64("session_id", session.ID()))
	// TODO: 导出日志文件
	_ = req
	writeLogOK(session, 9, "export log")
}

// CleanLogHandler 处理清理日志请求 (cmd=10)
func CleanLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for clean log")
		return
	}
	logger.Info("clean log request received", logger.Int64("session_id", session.ID()))
	// TODO: 清理过期日志
	_ = req
	writeLogOK(session, 11, "clean log")
}

// MonitorLogHandler 处理监控日志请求 (cmd=12)
func MonitorLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for monitor log")
		return
	}
	logger.Info("monitor log request received", logger.Int64("session_id", session.ID()))
	// TODO: 日志异常监控告警
	_ = req
	writeLogOK(session, 13, "monitor log")
}

// AnalyzeLogHandler 处理分析日志请求 (cmd=14)
func AnalyzeLogHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for analyze log")
		return
	}
	logger.Info("analyze log request received", logger.Int64("session_id", session.ID()))
	// TODO: 日志行为分析
	_ = req
	writeLogOK(session, 15, "analyze log")
}
