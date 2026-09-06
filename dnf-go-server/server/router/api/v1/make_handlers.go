package v1

import (
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

// handleMakeEmblemUpgrade 纹章升级(index=9999 表示纹章不存在/材料不足)
// 2026-09-06 由 mock 接入 store(EmblemUpgrade,扣 gold 写 t_emblem_upgrade)
func (s *APIV1Service) handleMakeEmblemUpgrade(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	index := uint32(0)
	if v, ok := req["index"].(float64); ok {
		index = uint32(v)
	}
	tryCount := uint32(1)
	if v, ok := req["trycount"].(float64); ok && v > 0 {
		tryCount = uint32(v)
	}
	talisman := uint32(0)
	if v, ok := req["talisman"].(float64); ok {
		talisman = uint32(v)
	}

	if index == 0 || index == 9999 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "emblem not found or material not enough"})
	}

	roleID := s.activeRoleID(c, claims)
	result, err := s.Store.EmblemUpgrade(c.Request().Context(), roleID, int32(index), int32(tryCount), int32(talisman))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}
	if result == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "emblem upgrade failed"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"index":        index,
		"trycount":     tryCount,
		"talisman":     talisman,
		"successcount": result.SuccessCount,
	})
}

// handleMakeEmblemUpgradeQuick 纹章快速升级(材料合成)
// 2026-09-06 由 mock 接入 store(EmblemUpgradeQuick,扣 1000 gold)
func (s *APIV1Service) handleMakeEmblemUpgradeQuick(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	target := uint32(0)
	if v, ok := req["target"].(float64); ok {
		target = uint32(v)
	}

	source := make([]*dnfv1.IndexCount, 0)
	if src, ok := req["source"].([]interface{}); ok {
		for _, item := range src {
			if m, ok := item.(map[string]interface{}); ok {
				idx := uint32(0)
				if v, ok := m["index"].(float64); ok {
					idx = uint32(v)
				}
				cnt := uint32(0)
				if v, ok := m["count"].(float64); ok {
					cnt = uint32(v)
				}
				source = append(source, &dnfv1.IndexCount{Index: int32(idx), Count: int32(cnt)})
			}
		}
	}

	roleID := s.activeRoleID(c, claims)
	_, err := s.Store.EmblemUpgradeQuick(c.Request().Context(), roleID, source, int32(target))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"target": target,
		"result": "success",
	})
}

// handleMakeAvatarCompose 时装合成
// 2026-09-06 由 mock 接入 store(AvatarCompose,扣 4000 gold 写 t_avatar_compose)
func (s *APIV1Service) handleMakeAvatarCompose(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	guids := make([]uint64, 0)
	if g, ok := req["guids"].([]interface{}); ok {
		for _, item := range g {
			if v, ok := item.(float64); ok {
				guids = append(guids, uint64(v))
			}
		}
	}
	if len(guids) == 0 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "empty guids"})
	}

	roleID := s.activeRoleID(c, claims)
	_, err := s.Store.AvatarCompose(c.Request().Context(), roleID, guids)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"guids":  guids,
		"result": "success",
	})
}

// handleMakeProductionInfo 获取生产信息
// 2026-09-06 由 mock 接入 store(GetProductionInfo)
func (s *APIV1Service) handleMakeProductionInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	slotType := int32(0)
	if v := c.QueryParam("slottype"); v != "" {
		if n, err := strconv.ParseInt(v, 10, 32); err == nil {
			slotType = int32(n)
		}
	}

	roleID := s.activeRoleID(c, claims)
	result, err := s.Store.GetProductionInfo(c.Request().Context(), roleID, slotType)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	slots := make([]interface{}, 0)
	for _, info := range result.Infos {
		slots = append(slots, map[string]interface{}{
			"slotIndex":   info.SlotIndex,
			"usableCount": info.UsableCount,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"slotType": slotType,
		"slots":    slots,
	})
}

// handleMakeProductionRegister 生产登记(recipe_index=9999 表示配方不存在/金币不足)
// 2026-09-06 由 mock 接入 store(ProductionRegister,扣 gold 写 t_item_production)
func (s *APIV1Service) handleMakeProductionRegister(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	slotIndex := uint32(0)
	if v, ok := req["slot_index"].(float64); ok {
		slotIndex = uint32(v)
	}
	recipeIndex := uint32(0)
	if v, ok := req["recipe_index"].(float64); ok {
		recipeIndex = uint32(v)
	}
	count := uint32(1)
	if v, ok := req["count"].(float64); ok && v > 0 {
		count = uint32(v)
	}

	if recipeIndex == 0 || recipeIndex == 9999 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "recipe not found or money not enough"})
	}

	roleID := s.activeRoleID(c, claims)
	_, err := s.Store.ProductionRegister(c.Request().Context(), roleID, int32(slotIndex), int32(recipeIndex), int32(count))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":       0,
		"slotIndex":   slotIndex,
		"recipeIndex": recipeIndex,
		"count":       count,
		"result":      "success",
	})
}

// handleMakeItemCombine 物品合成
// 2026-09-06 由 mock 接入 store(ItemCombine,写 t_item_combine)
func (s *APIV1Service) handleMakeItemCombine(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	index := uint32(0)
	if v, ok := req["index"].(float64); ok {
		index = uint32(v)
	}

	materials := make([]*dnfv1.MaterialItem, 0)
	if mats, ok := req["material_items"].([]interface{}); ok {
		for _, item := range mats {
			if m, ok := item.(map[string]interface{}); ok {
				idx := uint32(0)
				if v, ok := m["index"].(float64); ok {
					idx = uint32(v)
				}
				cnt := uint32(0)
				if v, ok := m["count"].(float64); ok {
					cnt = uint32(v)
				}
				materials = append(materials, &dnfv1.MaterialItem{Index: int32(idx), Count: int32(cnt)})
			}
		}
	}

	roleID := s.activeRoleID(c, claims)

	// 2026-09-06 第十八轮: 支持批量合成(count>1 逐次掷点), 缺省 1, 上限 99
	count := int32(1)
	if v, ok := req["count"].(float64); ok && v >= 1 {
		if v > 99 {
			v = 99
		}
		count = int32(v)
	}

	result, err := s.Store.ItemCombine(c.Request().Context(), roleID, int32(index), materials, count)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	// 2026-09-06 第十五轮: 响应真实合成结果(成功率/随机产出);
	// result 为掷点结果(success/fail), guid/itemId 为实际入包产物(失败保底物品也算产物)
	// 2026-09-06 第十八轮: 批量时 items 逐次列出(每掷点一条), guid/itemId 指向首个产物
	guid := uint64(0)
	itemID := uint32(0)
	outcome := "fail"
	var items []map[string]interface{}
	if result != nil {
		if result.Success {
			outcome = "success"
		}
		for _, e := range result.Items {
			items = append(items, map[string]interface{}{
				"itemId":   e.ItemID,
				"count":    e.Count,
				"success":  e.Success,
				"guid":     e.GUID,
				"bindType": e.BindType,
			})
			if guid == 0 && e.GUID > 0 {
				guid = e.GUID
				itemID = uint32(e.ItemID)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"index":  index,
		"count":  count,
		"guid":   guid,
		"itemId": itemID,
		"result": outcome,
		"items":  items,
	})
}

// handleMakeItemDisjoint 物品分解
// 2026-09-06 由 mock 接入 store(ItemDisjoint,写 t_item_disjoint)
func (s *APIV1Service) handleMakeItemDisjoint(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	guids := make([]uint64, 0)
	if g, ok := req["guids"].([]interface{}); ok {
		for _, item := range g {
			if v, ok := item.(float64); ok {
				guids = append(guids, uint64(v))
			}
		}
	}
	if len(guids) == 0 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "empty guids"})
	}

	roleID := s.activeRoleID(c, claims)
	_, err := s.Store.ItemDisjoint(c.Request().Context(), roleID, guids)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"guids":  guids,
		"result": "success",
	})
}

// handleMakeCardCompose 卡片合成(index=9999 表示卡片不足)
// 2026-09-06 由 mock 接入 store(CardCompose,扣 gold 写 t_card_compose)
func (s *APIV1Service) handleMakeCardCompose(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	cardList := make([]*dnfv1.CardCompose, 0)
	if raw, ok := req["user_card_list"].([]interface{}); ok {
		for _, item := range raw {
			if card, ok := item.(map[string]interface{}); ok {
				idx := uint32(0)
				if v, ok := card["index"].(float64); ok {
					idx = uint32(v)
				}
				if idx == 0 || idx == 9999 {
					return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "card not enough"})
				}
				cnt := uint32(1)
				if v, ok := card["count"].(float64); ok {
					cnt = uint32(v)
				}
				cardList = append(cardList, &dnfv1.CardCompose{Index: int32(idx), Count: int32(cnt)})
			}
		}
	}
	if len(cardList) == 0 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "card not enough"})
	}

	roleID := s.activeRoleID(c, claims)
	_, err := s.Store.CardCompose(c.Request().Context(), roleID, cardList)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"result": "success",
	})
}

// handleMakeWardrobeSetSlot 衣柜槽位设置
func (s *APIV1Service) handleMakeWardrobeSetSlot(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	roleID := s.activeRoleID(c, claims)
	if err := s.Store.WardrobeSetSlot(c.Request().Context(), roleID); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "result": "success"})
}
