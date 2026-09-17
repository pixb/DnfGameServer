package v1

import (
	"context"
	"encoding/json"
	"fmt"
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
// 2026-09-06 实化发放:解析 reward_config(JSON) -> 物品入背包 / gold 入角色货币,防重复领取
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
	config, err := s.Store.GetEventConfig(c.Request().Context(), &store.FindEventConfig{EventID: &eid})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "event not found"})
	}

	roleID := s.activeRoleID(c, claims)
	progressType := int32(100 + rewardID)

	// 防重复领取:已领过则直接返回 already,不重发
	existing, err := s.Store.GetEventProgress(c.Request().Context(), &store.FindEventProgress{
		RoleID:       roleID,
		EventID:      &eid,
		ProgressType: &progressType,
	})
	if err == nil && existing != nil && existing.Status == 1 {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":        2,
			"eventId":      eventID,
			"rewardId":     rewardID,
			"rewardStatus": "already",
		})
	}

	// 解析奖励配置并发放
	granted, err := s.grantEventRewards(c, config.RewardConfig, roleID)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	status := int32(1)
	_, err = s.Store.UpsertEventProgress(c.Request().Context(), &store.EventProgress{
		RoleID:        roleID,
		EventID:       eid,
		ProgressType:  progressType,
		ProgressValue: 1,
		Status:        status,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"eventId":      eventID,
		"rewardId":     rewardID,
		"rewardStatus": "success",
		"rewards":      granted,
	})
}

// grantEventRewards 发放活动奖励
// reward_config 格式: {"rewards":[{"item_id":1001,"count":5,"bind_type":0},{"type":"gold","count":1000}]}
func (s *APIV1Service) grantEventRewards(c echo.Context, rewardConfig string, roleID uint64) ([]interface{}, error) {
	granted := make([]interface{}, 0)
	if rewardConfig == "" || rewardConfig == "{}" {
		return granted, nil
	}

	var cfg struct {
		Rewards []struct {
			Type     string `json:"type"`
			ItemID   int32  `json:"item_id"`
			Count    int32  `json:"count"`
			BindType int32  `json:"bind_type"`
		} `json:"rewards"`
	}
	if err := json.Unmarshal([]byte(rewardConfig), &cfg); err != nil {
		return nil, fmt.Errorf("invalid reward config: %w", err)
	}

	ctx := c.Request().Context()
	for _, r := range cfg.Rewards {
		if r.Count <= 0 {
			continue
		}
		if r.Type == "gold" {
			cur, err := s.Store.GetRoleCurrency(ctx, roleID)
			if err != nil {
				// 角色无货币行,按 0 起始创建(UpdateRoleCurrency 为 upsert)
				cur = &store.RoleCurrency{RoleID: roleID}
			}
			if err := s.Store.UpdateRoleCurrency(ctx, &store.RoleCurrency{
				RoleID:     roleID,
				Gold:       cur.Gold + int64(r.Count),
				Coin:       cur.Coin,
				Fatigue:    cur.Fatigue,
				MaxFatigue: cur.MaxFatigue,
			}); err != nil {
				return nil, fmt.Errorf("failed to update role currency: %w", err)
			}
			granted = append(granted, map[string]interface{}{
				"type":  "gold",
				"count": r.Count,
			})
			continue
		}
		if r.ItemID > 0 {
			grid, err := s.nextBagGrid(ctx, roleID)
			if err != nil {
				return nil, fmt.Errorf("failed to find bag grid: %w", err)
			}
			if _, err := s.Store.CreateBagItem(ctx, &store.BagItem{
				RoleID:    roleID,
				ItemID:    r.ItemID,
				GridIndex: grid,
				Count:     r.Count,
				BindType:  r.BindType,
			}); err != nil {
				return nil, fmt.Errorf("failed to grant item: %w", err)
			}
			granted = append(granted, map[string]interface{}{
				"itemId":  r.ItemID,
				"count":   r.Count,
				"grid":    grid,
				"bindType": r.BindType,
			})
		}
	}
	return granted, nil
}

// nextBagGrid 计算背包下一个空格子索引(现有最大格子 + 1)
func (s *APIV1Service) nextBagGrid(ctx context.Context, roleID uint64) (int32, error) {
	items, err := s.Store.ListBagItemsByRole(ctx, roleID)
	if err != nil {
		return 0, err
	}
	maxGrid := int32(0)
	for _, it := range items {
		if it.GridIndex > maxGrid {
			maxGrid = it.GridIndex
		}
	}
	return maxGrid + 1, nil
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
