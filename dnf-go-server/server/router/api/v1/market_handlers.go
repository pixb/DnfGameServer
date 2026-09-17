package v1

import (
	"net/http"

	"github.com/labstack/echo/v4"
)

// handleMarketSearch 搜索市场物品
func (s *APIV1Service) handleMarketSearch(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemIndex := uint32(0)
	if v, ok := req["item_index"].(float64); ok {
		itemIndex = uint32(v)
	}
	page := uint32(1)
	if v, ok := req["page"].(float64); ok && v > 0 {
		page = uint32(v)
	}
	pageSize := uint32(20)
	if v, ok := req["page_size"].(float64); ok && v > 0 {
		pageSize = uint32(v)
	}

	// 市场交易数据当前无本地数据源,返回空列表
	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"itemIndex": itemIndex,
		"page":     page,
		"pageSize": pageSize,
		"total":    0,
		"items":    []interface{}{},
	})
}

// handleMarketPublish 发布市场物品
func (s *APIV1Service) handleMarketPublish(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemGuid := uint64(0)
	if v, ok := req["item_guid"].(float64); ok {
		itemGuid = uint64(v)
	}
	price := uint64(0)
	if v, ok := req["price"].(float64); ok {
		price = uint64(v)
	}
	currencyType := uint32(1)
	if v, ok := req["currency_type"].(float64); ok {
		currencyType = uint32(v)
	}
	duration := uint32(72)
	if v, ok := req["duration"].(float64); ok {
		duration = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"itemGuid":     itemGuid,
		"price":        price,
		"currencyType": currencyType,
		"duration":     duration,
		"publishStatus": "success",
	})
}

// handleMarketCancel 取消市场物品
func (s *APIV1Service) handleMarketCancel(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemGuid := uint64(0)
	if v, ok := req["item_guid"].(float64); ok {
		itemGuid = uint64(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"itemGuid":     itemGuid,
		"cancelStatus": "success",
	})
}

// handleMarketBuy 购买市场物品
func (s *APIV1Service) handleMarketBuy(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemGuid := uint64(0)
	if v, ok := req["item_guid"].(float64); ok {
		itemGuid = uint64(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"itemGuid": itemGuid,
		"buyStatus": "success",
	})
}

// handleMarketDetail 获取市场物品详情
func (s *APIV1Service) handleMarketDetail(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemGuid := uint64(0)
	if v, ok := req["item_guid"].(float64); ok {
		itemGuid = uint64(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"itemGuid": itemGuid,
		"item":     map[string]interface{}{},
	})
}

// handleMarketMyItems 获取我的市场物品
func (s *APIV1Service) handleMarketMyItems(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	page := uint32(1)
	if v, ok := req["page"].(float64); ok && v > 0 {
		page = uint32(v)
	}
	pageSize := uint32(20)
	if v, ok := req["page_size"].(float64); ok && v > 0 {
		pageSize = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"page":     page,
		"pageSize": pageSize,
		"total":    0,
		"items":    []interface{}{},
	})
}
