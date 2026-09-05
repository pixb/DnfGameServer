package v1

import (
	"net/http"

	"github.com/labstack/echo/v4"
)

// handleMakeEmblemUpgrade 纹章升级(index=9999 表示纹章不存在/材料不足)
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

	successCount := uint32(0)
	if talisman > 0 {
		successCount = tryCount
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"index":        index,
		"trycount":     tryCount,
		"talisman":     talisman,
		"successcount": successCount,
	})
}

// handleMakeEmblemUpgradeQuick 纹章快速升级(材料合成)
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

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"target": target,
		"result": "success",
	})
}

// handleMakeAvatarCompose 时装合成
func (s *APIV1Service) handleMakeAvatarCompose(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	guids := []interface{}{}
	if g, ok := req["guids"].([]interface{}); ok {
		guids = g
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"guids":  guids,
		"result": "success",
	})
}

// handleMakeProductionInfo 获取生产信息
func (s *APIV1Service) handleMakeProductionInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	slotType := c.QueryParam("slottype")

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"slotType": slotType,
		"slots":    []interface{}{},
	})
}

// handleMakeProductionRegister 生产登记(recipe_index=9999 表示配方不存在/金币不足)
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

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":       0,
		"slotIndex":   slotIndex,
		"recipeIndex": recipeIndex,
		"count":       count,
		"result":      "success",
	})
}

// handleMakeItemCombine 物品合成
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

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"index":  index,
		"result": "success",
	})
}

// handleMakeItemDisjoint 物品分解
func (s *APIV1Service) handleMakeItemDisjoint(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	guids := []interface{}{}
	if g, ok := req["guids"].([]interface{}); ok {
		guids = g
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"guids":  guids,
		"result": "success",
	})
}

// handleMakeCardCompose 卡片合成(index=9999 表示卡片不足)
func (s *APIV1Service) handleMakeCardCompose(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	cardList, _ := req["user_card_list"].([]interface{})
	for _, item := range cardList {
		if card, ok := item.(map[string]interface{}); ok {
			idx := uint32(0)
			if v, ok := card["index"].(float64); ok {
				idx = uint32(v)
			}
			if idx == 0 || idx == 9999 {
				return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "card not enough"})
			}
		}
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

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "result": "success"})
}
