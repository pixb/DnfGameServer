package v1

import (
	"net/http"
	"time"

	"github.com/labstack/echo/v4"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// handleEventList 获取活动列表
// 2026-09-06 由空数据源接入 store(t_event_config)
func (s *APIV1Service) handleEventList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	eventType := uint32(0)
	if v, ok := req["type"].(float64); ok {
		eventType = uint32(v)
	}
	status := uint32(0)
	if v, ok := req["status"].(float64); ok {
		status = uint32(v)
	}

	find := &store.FindEventConfig{}
	if eventType > 0 {
		t := int32(eventType)
		find.EventType = &t
	}
	if status > 0 {
		st := store.EventStatus(status)
		find.Status = &st
	}

	configs, err := s.Store.ListEventConfigs(c.Request().Context(), find)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	events := make([]interface{}, 0, len(configs))
	for _, e := range configs {
		events = append(events, map[string]interface{}{
			"eventId":   e.EventID,
			"title":     e.Title,
			"desc":      e.Description,
			"type":      e.EventType,
			"status":    e.Status,
			"startTime": e.StartTime,
			"endTime":   e.EndTime,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"type":   eventType,
		"status": status,
		"events": events,
	})
}

// handleEventDetail 获取活动详情
// 2026-09-06 由空数据源接入 store(t_event_config)
func (s *APIV1Service) handleEventDetail(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	eventID := uint32(0)
	if v, ok := req["event_id"].(float64); ok {
		eventID = uint32(v)
	}

	eid := int32(eventID)
	config, err := s.Store.GetEventConfig(c.Request().Context(), &store.FindEventConfig{EventID: &eid})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "event not found"})
	}

	event := map[string]interface{}{
		"eventId":   config.EventID,
		"title":     config.Title,
		"desc":      config.Description,
		"type":      config.EventType,
		"status":    config.Status,
		"startTime": config.StartTime,
		"endTime":   config.EndTime,
		"rewards":   config.RewardConfig,
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"eventId": eventID,
		"event":   event,
	})
}

// handleEventAccessTime 查询活动访问时间(与 Java 侧一致返回当前时间戳)
func (s *APIV1Service) handleEventAccessTime(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":      0,
		"accesstime": time.Now().Unix(),
	})
}

// handleEventGetReward 获取活动奖励
// 2026-09-06 由 mock 接入 store(校验活动存在 + 进度标记已领奖)
func (s *APIV1Service) handleEventGetReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	eventID := uint32(0)
	if v, ok := req["event_id"].(float64); ok {
		eventID = uint32(v)
	}
	rewardID := uint32(0)
	if v, ok := req["reward_id"].(float64); ok {
		rewardID = uint32(v)
	}

	eid := int32(eventID)
	if _, err := s.Store.GetEventConfig(c.Request().Context(), &store.FindEventConfig{EventID: &eid}); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "event not found"})
	}

	roleID := s.activeRoleID(c, claims)
	status := int32(1)
	_, err := s.Store.UpsertEventProgress(c.Request().Context(), &store.EventProgress{
		RoleID:       roleID,
		EventID:      eid,
		ProgressType: int32(100 + rewardID),
		ProgressValue: 1,
		Status:       status,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"eventId":      eventID,
		"rewardId":     rewardID,
		"rewardStatus": "success",
	})
}

// handleEventUpdateProgress 更新活动进度
// 2026-09-06 由 mock 接入 store(写 t_event_progress)
func (s *APIV1Service) handleEventUpdateProgress(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	eventID := uint32(0)
	if v, ok := req["event_id"].(float64); ok {
		eventID = uint32(v)
	}
	progressType := uint32(0)
	if v, ok := req["progress_type"].(float64); ok {
		progressType = uint32(v)
	}
	progressValue := int64(0)
	if v, ok := req["progress_value"].(float64); ok {
		progressValue = int64(v)
	}

	roleID := s.activeRoleID(c, claims)
	_, err := s.Store.UpsertEventProgress(c.Request().Context(), &store.EventProgress{
		RoleID:        roleID,
		EventID:       int32(eventID),
		ProgressType:  int32(progressType),
		ProgressValue: progressValue,
		Status:        0,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":          0,
		"eventId":        eventID,
		"progressType":   progressType,
		"progressValue":  progressValue,
		"progressStatus": "success",
	})
}

// handleEventParticipate 参与活动
// 2026-09-06 由 mock 接入 store(写入参与进度)
func (s *APIV1Service) handleEventParticipate(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	eventID := uint32(0)
	if v, ok := req["event_id"].(float64); ok {
		eventID = uint32(v)
	}
	participateType := uint32(0)
	if v, ok := req["participate_type"].(float64); ok {
		participateType = uint32(v)
	}

	roleID := s.activeRoleID(c, claims)
	_, err := s.Store.UpsertEventProgress(c.Request().Context(), &store.EventProgress{
		RoleID:        roleID,
		EventID:       int32(eventID),
		ProgressType:  int32(participateType),
		ProgressValue: 1,
		Status:        0,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":             0,
		"eventId":           eventID,
		"participateType":   participateType,
		"participateStatus": "success",
	})
}
