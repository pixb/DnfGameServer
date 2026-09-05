package v1

import (
	"net/http"

	"github.com/labstack/echo/v4"
)

// handleOnlineMallList 获取商城商品列表
func (s *APIV1Service) handleOnlineMallList(c echo.Context) error {
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

	// 商城商品配置暂存于运营后台,当前无本地数据源,返回空列表
	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"page":     page,
		"pageSize": pageSize,
		"total":    0,
		"items":    []interface{}{},
	})
}

// handleOnlineMallDetail 获取商城商品详情
func (s *APIV1Service) handleOnlineMallDetail(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemID := uint32(0)
	if v, ok := req["item_id"].(float64); ok {
		itemID = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"itemId": itemID,
		"item":   map[string]interface{}{},
	})
}

// handleOnlineMallBuy 购买商城商品
func (s *APIV1Service) handleOnlineMallBuy(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemID := uint32(0)
	if v, ok := req["item_id"].(float64); ok {
		itemID = uint32(v)
	}
	buyCount := uint32(1)
	if v, ok := req["buy_count"].(float64); ok && v > 0 {
		buyCount = uint32(v)
	}
	currencyType := uint32(1)
	if v, ok := req["currency_type"].(float64); ok {
		currencyType = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"itemId":       itemID,
		"buyCount":     buyCount,
		"currencyType": currencyType,
		"orderId":      "",
		"buyStatus":    "success",
	})
}

// handleOnlineMallCategories 获取商城分类
func (s *APIV1Service) handleOnlineMallCategories(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":      0,
		"categories": []interface{}{},
	})
}

// handleOnlineMallCategoryItems 按分类获取商城商品
func (s *APIV1Service) handleOnlineMallCategoryItems(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	categoryID := uint32(0)
	if v, ok := req["category_id"].(float64); ok {
		categoryID = uint32(v)
	}
	page := uint32(1)
	if v, ok := req["page"].(float64); ok && v > 0 {
		page = uint32(v)
	}
	pageSize := uint32(20)
	if v, ok := req["page_size"].(float64); ok && v > 0 {
		pageSize = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":      0,
		"categoryId": categoryID,
		"page":       page,
		"pageSize":   pageSize,
		"total":      0,
		"items":      []interface{}{},
	})
}

// handleOnlineMallRecommend 获取推荐商品
func (s *APIV1Service) handleOnlineMallRecommend(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"items":  []interface{}{},
	})
}

// handleOnlineMallHot 获取热门商品
func (s *APIV1Service) handleOnlineMallHot(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"items": []interface{}{},
	})
}

// handleOnlineMallBuyLimit 获取购买限制
func (s *APIV1Service) handleOnlineMallBuyLimit(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	itemID := uint32(0)
	if v, ok := req["item_id"].(float64); ok {
		itemID = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"itemId":  itemID,
		"limit":   0,
		"bought":  0,
		"remain":  0,
	})
}
