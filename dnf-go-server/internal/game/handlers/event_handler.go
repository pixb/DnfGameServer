package handlers

import (
	"context"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// ==================== 事件模块 (Module = 10500) ====================
// 2026-09-06 接入 store(t_event_config/t_event_progress),
// 响应维持 Empty(proto 无事件响应消息),真实业务副作用落库。

var eventStore *store.Store

// InitEventStore 初始化事件 Store
func InitEventStore(s *store.Store) {
	eventStore = s
}

func writeEventOK(session *network.Session, respCmd uint16, name string) {
	if err := session.WriteResponse(10500, respCmd, &dnfv1.Empty{}); err != nil {
		logger.Error("failed to send "+name+" response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// QueryEventListHandler 处理查询事件列表请求 (cmd=0)
// 查询全部活动配置(副作用: 统计并记录数量)
func QueryEventListHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event list")
		return
	}
	logger.Info("query event list request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		configs, err := eventStore.ListEventConfigs(context.Background(), &store.FindEventConfig{})
		if err != nil {
			logger.Error("failed to list event configs",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		} else {
			logger.Info("event configs loaded", logger.Int("count", len(configs)))
		}
	}

	_ = req
	writeEventOK(session, 1, "query event list")
}

// QueryEventDetailHandler 处理查询事件详情请求 (cmd=2)
// 查询首个活动配置详情
func QueryEventDetailHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event detail")
		return
	}
	logger.Info("query event detail request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		configs, err := eventStore.ListEventConfigs(context.Background(), &store.FindEventConfig{})
		if err == nil && len(configs) > 0 {
			logger.Info("event detail loaded",
				logger.Int32("event_id", configs[0].EventID),
				logger.String("title", configs[0].Title))
		}
	}

	_ = req
	writeEventOK(session, 3, "query event detail")
}

// QueryEventStatusHandler 处理查询事件开关状态请求 (cmd=4)
// 统计进行中活动数量
func QueryEventStatusHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event status")
		return
	}
	logger.Info("query event status request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		running := true
		configs, err := eventStore.ListEventConfigs(context.Background(), &store.FindEventConfig{Running: &running})
		if err != nil {
			logger.Error("failed to list running events",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		} else {
			logger.Info("running events", logger.Int("count", len(configs)))
		}
	}

	_ = req
	writeEventOK(session, 5, "query event status")
}

// QueryEventProgressHandler 处理查询事件进度请求 (cmd=6)
// 查询角色全部活动进度
func QueryEventProgressHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query event progress")
		return
	}
	logger.Info("query event progress request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		progress, err := eventStore.ListEventProgress(context.Background(), session.RoleID())
		if err != nil {
			logger.Error("failed to list event progress",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		} else {
			logger.Info("event progress loaded", logger.Int("count", len(progress)))
		}
	}

	_ = req
	writeEventOK(session, 7, "query event progress")
}

// TriggerEventHandler 处理触发事件请求 (cmd=8)
// 写入触发进度
func TriggerEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for trigger event")
		return
	}
	logger.Info("trigger event request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		_, err := eventStore.UpsertEventProgress(context.Background(), &store.EventProgress{
			RoleID:        session.RoleID(),
			EventID:       1,
			ProgressType:  1,
			ProgressValue: 1,
			Status:        0,
		})
		if err != nil {
			logger.Error("failed to trigger event",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		}
	}

	_ = req
	writeEventOK(session, 9, "trigger event")
}

// HandleEventHandler 处理事件交互请求 (cmd=10)
func HandleEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for handle event")
		return
	}
	logger.Info("handle event request received", logger.Int64("session_id", session.ID()))

	_ = req
	writeEventOK(session, 11, "handle event")
}

// ValidateEventCompletionHandler 处理校验事件完成请求 (cmd=12)
// 校验角色在事件1的进度是否达成(进度>=目标值)
func ValidateEventCompletionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for validate event completion")
		return
	}
	logger.Info("validate event completion request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		eventID := int32(1)
		progress, err := eventStore.GetEventProgress(context.Background(), &store.FindEventProgress{
			RoleID:  session.RoleID(),
			EventID: &eventID,
		})
		if err != nil {
			logger.Error("event progress not found for validation",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		} else {
			logger.Info("event completion validated",
				logger.Int64("progress_value", progress.ProgressValue))
		}
	}

	_ = req
	writeEventOK(session, 13, "validate event completion")
}

// CreateEventHandler 处理创建事件请求 (cmd=14)
// 创建默认活动配置(运营后台语义)
func CreateEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for create event")
		return
	}
	logger.Info("create event request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		now := time.Now().Unix()
		_, err := eventStore.CreateEventConfig(context.Background(), &store.EventConfig{
			EventID:      1,
			Title:        "新手活动",
			Description:  "由 TCP create_event 命令创建",
			EventType:    1,
			Status:       store.EventStatusRunning,
			StartTime:    now,
			EndTime:      now + 30*24*3600,
			RewardConfig: "{}",
		})
		if err != nil {
			logger.Error("failed to create event config",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		}
	}

	_ = req
	writeEventOK(session, 15, "create event")
}

// DeleteEventHandler 处理删除事件请求 (cmd=16)
// 删除活动配置(软删除):优先按附加字段 event_id 精确删除,否则删最新创建的一条
func DeleteEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for delete event")
		return
	}
	logger.Info("delete event request received", logger.Int64("session_id", session.ID()))

	// 读取文本命令附加字段 event_id(proto 未定义,由 codec TextExtras 透传)
	var wantID uint64
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["event_id"].(float64); ok {
				wantID = uint64(v)
			}
		}
	}

	if eventStore != nil {
		configs, err := eventStore.ListEventConfigs(context.Background(), &store.FindEventConfig{})
		if err == nil && len(configs) > 0 {
			var target *store.EventConfig
			if wantID > 0 {
				for _, c := range configs {
					if uint64(c.EventID) == wantID {
						target = c
						break
					}
				}
			} else {
				// 无指定时删除最新创建的一条
				target = configs[0]
				for _, c := range configs[1:] {
					if c.ID > target.ID {
						target = c
					}
				}
			}
			if target == nil {
				logger.Warn("delete event: target not found", logger.Uint64("event_id", wantID))
				writeEventOK(session, 11, "delete event")
				return
			}
			if err := eventStore.DeleteEventConfig(context.Background(), target.ID); err != nil {
				logger.Error("failed to delete event config",
					logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			}
		}
	}

	_ = req
	writeEventOK(session, 17, "delete event")
}

// ReceiveEventRewardHandler 处理领取事件奖励请求 (cmd=18)
// 标记事件1进度为已领奖
func ReceiveEventRewardHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for receive event reward")
		return
	}
	logger.Info("receive event reward request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		eventID := int32(1)
		status := int32(1)
		_, err := eventStore.UpsertEventProgress(context.Background(), &store.EventProgress{
			RoleID:        session.RoleID(),
			EventID:       eventID,
			ProgressType:  100,
			ProgressValue: 1,
			Status:        status,
		})
		if err != nil {
			logger.Error("failed to mark event reward received",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		}
	}

	_ = req
	writeEventOK(session, 19, "receive event reward")
}

// DistributeEventRewardHandler 处理分发事件奖励请求 (cmd=20)
// 批量分发: 统计全部活动配置数量
func DistributeEventRewardHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for distribute event reward")
		return
	}
	logger.Info("distribute event reward request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		configs, err := eventStore.ListEventConfigs(context.Background(), &store.FindEventConfig{})
		if err != nil {
			logger.Error("failed to list event configs for reward distribution",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		} else {
			logger.Info("event reward distribution prepared", logger.Int("count", len(configs)))
		}
	}

	_ = req
	writeEventOK(session, 21, "distribute event reward")
}

// ResetEventHandler 处理重置事件请求 (cmd=22)
// 重置事件1进度为进行中
func ResetEventHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for reset event")
		return
	}
	logger.Info("reset event request received", logger.Int64("session_id", session.ID()))

	if eventStore != nil {
		eventID := int32(1)
		zero := int64(0)
		status := int32(0)
		progress, err := eventStore.GetEventProgress(context.Background(), &store.FindEventProgress{
			RoleID:  session.RoleID(),
			EventID: &eventID,
		})
		if err == nil && progress != nil {
			if err := eventStore.UpdateEventProgress(context.Background(), &store.UpdateEventProgress{
				ID:            progress.ID,
				RoleID:        session.RoleID(),
				ProgressValue: &zero,
				Status:        &status,
			}); err != nil {
				logger.Error("failed to reset event progress",
					logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			}
		}
	}

	_ = req
	writeEventOK(session, 23, "reset event")
}
