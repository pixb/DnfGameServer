package v1

import (
	"encoding/json"
	"net/http"
	"strconv"
	"time"

	"github.com/labstack/echo/v4"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// decodeJSONBody 解析 JSON 请求体(测试客户端以 application/json 发送)。
// 解析失败不视为错误,返回空 map,由各 handler 按缺省值处理。
func decodeJSONBody(c echo.Context) map[string]interface{} {
	req := map[string]interface{}{}
	_ = json.NewDecoder(c.Request().Body).Decode(&req)
	return req
}

func (s *APIV1Service) handleGamePing(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	timestamp := time.Now().Unix()
	if v, ok := req["timestamp"].(float64); ok {
		timestamp = int64(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":      0,
		"timestamp":  timestamp,
		"serverTime": time.Now().Unix(),
	})
}

func (s *APIV1Service) handleGameEnterTown(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	town := uint32(0)
	if v, ok := req["town"].(float64); ok {
		town = uint32(v)
	}
	area := uint32(0)
	if v, ok := req["area"].(float64); ok {
		area = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"town":  town,
		"area":  area,
	})
}

func (s *APIV1Service) handleGameLeaveTown(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
	})
}

func (s *APIV1Service) handleGameDailyReset(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
	})
}

func (s *APIV1Service) handleGameCharacterInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	charGUIDStr := c.QueryParam("charguid")
	var charGUID uint64
	if v, err := strconv.ParseUint(charGUIDStr, 10, 64); err == nil {
		charGUID = v
	}

	role, err := s.Store.GetRole(c.Request().Context(), &store.FindRole{
		FindBase: store.FindBase{ID: &charGUID},
	})
	if err != nil || role == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   2,
			"message": "character not found",
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"character": map[string]interface{}{
			"charGuid":   role.ID,
			"name":       role.Name,
			"job":        role.Job,
			"level":      role.Level,
			"exp":        role.Exp,
			"fatigue":    role.Fatigue,
			"maxFatigue": role.MaxFatigue,
			"channel":    role.Channel,
		},
	})
}

func (s *APIV1Service) handleGameInteractionMenu(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	openMenuType := uint32(0)
	if v, ok := req["openmenutype"].(float64); ok {
		openMenuType = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"status":       "ok",
		"openmenutype": openMenuType,
	})
}

func (s *APIV1Service) handleGameNotTransactionState(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	isOn := false
	if v, ok := req["ison"].(bool); ok {
		isOn = v
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"ison":   isOn,
		"status": "ok",
	})
}

func (s *APIV1Service) handleGamePvpRecord(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"pvpinfos": []interface{}{},
	})
}

func (s *APIV1Service) handleGameAdventureUnionSubdue(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":         0,
		"fatigues":      []interface{}{},
		"tickets":       []interface{}{},
		"entranceitems": []interface{}{},
	})
}

func (s *APIV1Service) handleGameSendingInviteFriendList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	// 查找账号下第一个角色,获取其好友列表
	roles, err := s.Store.ListRolesByAccount(c.Request().Context(), claims.UserID)
	flist := []interface{}{}
	if err == nil && len(roles) > 0 {
		friends, ferr := s.Store.ListFriends(c.Request().Context(), roles[0].ID)
		if ferr == nil {
			for _, f := range friends {
				flist = append(flist, map[string]interface{}{
					"friend_guid": f.FriendID,
					"friend_name": f.FriendName,
					"intimacy":    f.Intimacy,
					"group":       f.Group,
				})
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"flist": flist,
	})
}

func (s *APIV1Service) handleGameLoadServerSimpleData(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	dataType := uint32(0)
	if v, ok := req["type"].(float64); ok {
		dataType = uint32(v)
	}
	enumValue := uint32(0)
	if v, ok := req["enumvalue"].(float64); ok {
		enumValue = uint32(v)
	}

	// 加载服务器简单数据(当前无持久化配置,返回空值)
	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":     0,
		"type":      dataType,
		"enumvalue": enumValue,
		"value":     "",
	})
}

func (s *APIV1Service) handleGameSaveServerSimpleData(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
	})
}

func (s *APIV1Service) handleGameEnterChannel(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	channel := uint32(0)
	if v, ok := req["channel"].(float64); ok {
		channel = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"channel": channel,
	})
}

func (s *APIV1Service) handleGameStandby(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
	})
}

func (s *APIV1Service) handleGameIdipNotices(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"notices": []interface{}{},
	})
}

func (s *APIV1Service) handleGameBlackDiamonInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":       0,
		"blackDiamon": 0,
	})
}

func (s *APIV1Service) handleGamePrivateStoreGoodsList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"goods": []interface{}{},
	})
}

func (s *APIV1Service) handleGameRecommendGuildList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	// 推荐公会列表(当前返回空列表,公会模块提供独立查询接口)
	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"list":  []interface{}{},
	})
}

func (s *APIV1Service) handleGameAdventureUnionInfoOther(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	charGUID := uint64(0)
	if v, ok := req["charguid"].(float64); ok {
		charGUID = uint64(v)
	}

	union, err := s.Store.GetAdventureUnionInfo(c.Request().Context(), charGUID)
	if err != nil || union == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error": 3,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"level": union.Level,
		"name":  union.Name,
		"exp":   union.Exp,
	})
}

func (s *APIV1Service) handleGameStart(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	town := uint32(0)
	if v, ok := req["town"].(float64); ok {
		town = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"world": "1",
		"town":  town,
	})
}
