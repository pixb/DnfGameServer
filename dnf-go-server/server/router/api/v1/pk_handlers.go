package v1

import (
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"
)

// pkService 获取 PK 服务(非 MySQL 驱动下为 nil,返回空数据而非报错)
func (s *APIV1Service) pkService() bool {
	return s.PK != nil
}

func (s *APIV1Service) handlePkMultiPlayRequestMatch(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}
	matchType := uint32(0)
	if v, ok := req["matchtype"].(float64); ok {
		matchType = uint32(v)
	}
	dungeonIndex := uint32(0)
	if v, ok := req["dungeonindex"].(float64); ok {
		dungeonIndex = uint32(v)
	}

	if !s.pkService() {
		return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 3})
	}

	result, err := s.PK.RequestMatch(c.Request().Context(), claims.UserID, matchType, dungeonIndex)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 3, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"transId":      transID,
		"error":        0,
		"matchingguid": result.MatchingGuid,
		"bip":          result.IP,
		"bport":        result.Port,
	})
}

func (s *APIV1Service) handlePkMultiPlayRequestMatchCancel(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}
	matchingGUID := uint64(0)
	if v, ok := req["matchingguid"].(float64); ok {
		matchingGUID = uint64(v)
	}

	if s.pkService() {
		if err := s.PK.CancelMatch(c.Request().Context(), claims.UserID, matchingGUID); err != nil {
			return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 3, "message": err.Error()})
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 0})
}

func (s *APIV1Service) handlePkHistoricSiteNoti(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 0})
}

func (s *APIV1Service) handlePkLoadGuildDonationInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}

	recipe := []interface{}{}
	if s.pkService() {
		recipes, err := s.PK.GetGuildDonationRecipes(c.Request().Context())
		if err == nil {
			for _, r := range recipes {
				recipe = append(recipe, r)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"transId": transID,
		"error":   0,
		"recipe":  recipe,
	})
}

func (s *APIV1Service) handlePkDreamMazeBasicInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 0})
}

func (s *APIV1Service) handlePkRaidEntranceCount(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}

	entrance := []interface{}{}
	if s.pkService() {
		entrances, err := s.PK.GetRaidEntranceCount(c.Request().Context(), claims.UserID)
		if err == nil {
			for _, e := range entrances {
				entrance = append(entrance, e)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"transId":  transID,
		"error":    0,
		"entrance": entrance,
	})
}

func (s *APIV1Service) handlePkLoadingProgress(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}
	value := uint32(0)
	if v, ok := req["value"].(float64); ok {
		value = uint32(v)
	}

	if s.pkService() {
		_ = s.PK.ReportLoadingProgress(c.Request().Context(), claims.UserID, value)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"transId":      transID,
		"error":        0,
		"matchingguid": 0,
		"charguid":     claims.UserID,
		"value":        value,
	})
}

func (s *APIV1Service) handlePkReturnToTown(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}

	if s.pkService() {
		_ = s.PK.ReturnToTown(c.Request().Context(), claims.UserID)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 0})
}

func (s *APIV1Service) handlePkCustomGameRoomSetting(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	transID := uint32(0)
	if v, ok := req["transId"].(float64); ok {
		transID = uint32(v)
	}

	if s.pkService() {
		// customdata 为 protobuf 结构,HTTP 层仅透传 ack(完整自定义房间逻辑走 TCP)
		_ = req["customdata"]
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"transId": transID, "error": 0})
}

func (s *APIV1Service) handlePkRecord(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	if v, err := strconv.ParseUint(c.QueryParam("charguid"), 10, 64); err == nil {
		roleID = v
	}

	records := []interface{}{}
	if s.pkService() {
		infos, err := s.PK.GetPvpRecord(c.Request().Context(), roleID)
		if err == nil {
			for _, r := range infos {
				records = append(records, r)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"records": records,
	})
}

func (s *APIV1Service) handlePkRanking(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	rankings := []interface{}{}
	if s.pkService() {
		infos, err := s.PK.GetPvpRanking(c.Request().Context(), 0, 1, 50)
		if err == nil {
			for _, r := range infos {
				rankings = append(rankings, r)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"rankings": rankings,
	})
}

func (s *APIV1Service) handlePkStats(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	if v, err := strconv.ParseUint(c.QueryParam("charguid"), 10, 64); err == nil {
		roleID = v
	}

	stats := map[string]interface{}{}
	if s.pkService() {
		if info, err := s.PK.GetPvpStats(c.Request().Context(), roleID); err == nil {
			stats = map[string]interface{}{
				"roleId":       info.RoleId,
				"totalMatches": info.TotalMatches,
				"winCount":     info.WinCount,
				"loseCount":    info.LoseCount,
				"winRate":      info.WinRate,
				"totalScore":   info.TotalScore,
				"avgScore":     info.AvgScore,
				"maxWinStreak": info.MaxWinStreak,
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"stats": stats,
	})
}

func (s *APIV1Service) handlePkMatchHistory(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	if v, err := strconv.ParseUint(c.QueryParam("charguid"), 10, 64); err == nil {
		roleID = v
	}

	history := []interface{}{}
	if s.pkService() {
		infos, err := s.PK.GetPvpMatchHistory(c.Request().Context(), roleID, 1, 50)
		if err == nil {
			for _, h := range infos {
				history = append(history, h)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"history": history,
	})
}

func (s *APIV1Service) handlePkSeasonInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	season := map[string]interface{}{}
	if s.pkService() {
		if info, err := s.PK.GetPvpSeasonInfo(c.Request().Context()); err == nil {
			season = map[string]interface{}{
				"seasonId":   info.SeasonId,
				"seasonName": info.SeasonName,
				"startTime":  info.StartTime,
				"endTime":    info.EndTime,
				"status":     info.Status,
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"season": season,
	})
}

func (s *APIV1Service) handlePkReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	if v, err := strconv.ParseUint(c.QueryParam("charguid"), 10, 64); err == nil {
		roleID = v
	}

	rewards := []interface{}{}
	if s.pkService() {
		infos, err := s.PK.GetPvpReward(c.Request().Context(), roleID)
		if err == nil {
			for _, r := range infos {
				rewards = append(rewards, r)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"rewards": rewards,
	})
}

func (s *APIV1Service) handlePkDailyReset(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	decodeJSONBody(c)

	if s.pkService() {
		_ = s.PK.PvpDailyReset(c.Request().Context(), claims.UserID)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handlePkMatchTypes(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	types := []interface{}{}
	if s.pkService() {
		infos, err := s.PK.GetPvpMatchTypes(c.Request().Context())
		if err == nil {
			for _, t := range infos {
				types = append(types, t)
			}
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"types": types,
	})
}

func (s *APIV1Service) handlePkBattleResult(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	matchingGUID := uint64(0)
	if v, ok := req["matchingguid"].(float64); ok {
		matchingGUID = uint64(v)
	}
	opponentID := uint64(0)
	if v, ok := req["opponentId"].(float64); ok {
		opponentID = uint64(v)
	}
	win := false
	if v, ok := req["win"].(bool); ok {
		win = v
	}
	score := int32(0)
	if v, ok := req["score"].(float64); ok {
		score = int32(v)
	}

	if s.pkService() {
		if _, err := s.PK.SubmitPvpBattleResult(c.Request().Context(), claims.UserID, matchingGUID, opponentID, win, score); err != nil {
			return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}
