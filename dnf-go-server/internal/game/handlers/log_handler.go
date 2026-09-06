package handlers

import (
	"context"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"google.golang.org/protobuf/proto"
)

// ==================== 日志模块 (Module = 10502) ====================
// 2026-09-06 第十九轮 实化: 行为日志落库(t_behavior_log, 2.3.0 迁移)。
// 客户端经 TCP 文本命令带 JSON payload(RECORD_LOG:{"log_level":...,"log_message":...}),
// 由 codec TextExtras 透传到会话; 查询/统计/删除/清理均落真实 store 调用,
// 响应仍为 Empty(协议未扩展, 结果经服务端日志可观测)。

var logStore *store.Store

// InitLogStore 初始化日志 Store
func InitLogStore(s *store.Store) {
	logStore = s
}

// logPayload 读取文本命令附加字段(proto 未定义,由 codec TextExtras 透传)
func logPayload(session *network.Session) map[string]interface{} {
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			return m
		}
	}
	return nil
}

// logPayloadString 读取 payload 字符串字段
func logPayloadString(p map[string]interface{}, key string) string {
	if p == nil {
		return ""
	}
	if v, ok := p[key].(string); ok {
		return v
	}
	return ""
}

// logPayloadInt64 读取 payload 整数字段(JSON 数字解码为 float64)
func logPayloadInt64(p map[string]interface{}, key string) int64 {
	if p == nil {
		return 0
	}
	if v, ok := p[key].(float64); ok {
		return int64(v)
	}
	return 0
}

// logPayloadIDs 读取 payload 数组字段(如 log_ids)
func logPayloadIDs(p map[string]interface{}, key string) []uint64 {
	if p == nil {
		return nil
	}
	raw, ok := p[key].([]interface{})
	if !ok {
		return nil
	}
	var ids []uint64
	for _, v := range raw {
		if f, ok := v.(float64); ok {
			ids = append(ids, uint64(f))
		}
	}
	return ids
}

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
	// 按条件查询行为日志(page_size 限制条数, 缺省 10)
	p := logPayload(session)
	limit := int(logPayloadInt64(p, "page_size"))
	if limit <= 0 {
		limit = 10
	}
	if logStore != nil {
		logs, err := logStore.ListBehaviorLogs(context.Background(), session.RoleID(), limit)
		if err != nil {
			logger.Error("query log failed", logger.ErrorField(err))
		} else {
			logger.Info("query log result",
				logger.Int64("session_id", session.ID()),
				logger.Int("count", len(logs)),
			)
		}
	}
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
	// 写入行为日志: log_category -> action, log_level -> level, log_message -> content
	p := logPayload(session)
	action := logPayloadString(p, "log_category")
	if action == "" {
		action = "record"
	}
	level := logPayloadString(p, "log_level")
	if level == "" {
		level = "info"
	}
	content := logPayloadString(p, "log_message")
	createTime := logPayloadInt64(p, "log_time")
	if createTime == 0 {
		createTime = time.Now().Unix()
	}
	if logStore != nil {
		if err := logStore.RecordBehaviorLog(context.Background(), &store.BehaviorLog{
			CreatedAt: createTime,
			RoleID:    session.RoleID(),
			Module:    "tcp",
			Action:    action,
			Level:     level,
			Content:   content,
		}); err != nil {
			logger.Error("record log failed", logger.ErrorField(err))
		}
	}
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
	// 日志聚合统计(按 action 计数)
	if logStore != nil {
		stats, err := logStore.StatisticBehaviorLogs(context.Background(), session.RoleID())
		if err != nil {
			logger.Error("statistic log failed", logger.ErrorField(err))
		} else {
			logger.Info("statistic log result",
				logger.Int64("session_id", session.ID()),
				logger.Int("actions", len(stats)),
			)
			for action, cnt := range stats {
				logger.Info("statistic action", logger.String("action", action), logger.Int64("count", cnt))
			}
		}
	}
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
	// 按条件删除日志(log_ids 列表)
	ids := logPayloadIDs(logPayload(session), "log_ids")
	if logStore != nil {
		if n, err := logStore.DeleteBehaviorLogs(context.Background(), ids); err != nil {
			logger.Error("delete log failed", logger.ErrorField(err))
		} else {
			logger.Info("delete log result",
				logger.Int64("session_id", session.ID()),
				logger.Int64("deleted", n),
			)
		}
	}
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
	// 导出日志文件: 全量查询并记录行数(响应协议未扩展, 结果经服务端日志可观测)
	if logStore != nil {
		logs, err := logStore.ListBehaviorLogs(context.Background(), session.RoleID(), 1000)
		if err != nil {
			logger.Error("export log failed", logger.ErrorField(err))
		} else {
			logger.Info("export log result",
				logger.Int64("session_id", session.ID()),
				logger.Int("exported", len(logs)),
			)
		}
	}
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
	// 清理过期日志(before_time 之前删除)
	p := logPayload(session)
	before := logPayloadInt64(p, "before_time")
	if before == 0 {
		before = time.Now().Unix()
	}
	if logStore != nil {
		if n, err := logStore.CleanBehaviorLogs(context.Background(), before); err != nil {
			logger.Error("clean log failed", logger.ErrorField(err))
		} else {
			logger.Info("clean log result",
				logger.Int64("session_id", session.ID()),
				logger.Int64("deleted", n),
			)
		}
	}
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
	// 日志异常监控告警: 统计 error 级别日志数
	if logStore != nil {
		logs, err := logStore.ListBehaviorLogs(context.Background(), session.RoleID(), 10000)
		if err != nil {
			logger.Error("monitor log failed", logger.ErrorField(err))
		} else {
			errorCount := 0
			for _, l := range logs {
				if l.Level == "error" {
					errorCount++
				}
			}
			logger.Info("monitor log result",
				logger.Int64("session_id", session.ID()),
				logger.Int("error_count", errorCount),
				logger.Int("total", len(logs)),
			)
		}
	}
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
	// 日志行为分析: 按 action 分布统计
	if logStore != nil {
		stats, err := logStore.StatisticBehaviorLogs(context.Background(), session.RoleID())
		if err != nil {
			logger.Error("analyze log failed", logger.ErrorField(err))
		} else {
			logger.Info("analyze log result",
				logger.Int64("session_id", session.ID()),
				logger.Int("actions", len(stats)),
			)
			for action, cnt := range stats {
				logger.Info("analyze action", logger.String("action", action), logger.Int64("count", cnt))
			}
		}
	}
	_ = req
	writeLogOK(session, 15, "analyze log")
}
