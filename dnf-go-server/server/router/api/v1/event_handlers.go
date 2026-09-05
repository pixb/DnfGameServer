package v1

import (
	"net/http"
	"time"

	"github.com/labstack/echo/v4"
)

// handleEventList 获取活动列表
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

	// 活动配置暂存于运营后台,当前无数据源,返回空列表
	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"type":   eventType,
		"status": status,
		"events": []interface{}{},
	})
}

// handleEventDetail 获取活动详情
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

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"eventId": eventID,
		"event":   map[string]interface{}{},
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

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"eventId":      eventID,
		"rewardId":     rewardID,
		"rewardStatus": "success",
	})
}

// handleEventUpdateProgress 更新活动进度
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

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":          0,
		"eventId":        eventID,
		"progressType":   progressType,
		"progressValue":  progressValue,
		"progressStatus": "success",
	})
}

// handleEventParticipate 参与活动
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

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":             0,
		"eventId":           eventID,
		"participateType":   participateType,
		"participateStatus": "success",
	})
}
