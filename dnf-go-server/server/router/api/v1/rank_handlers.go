package v1

import (
	"net/http"
	"sort"

	"github.com/labstack/echo/v4"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// rankType 排名类型
// 1=等级榜 2=战力榜(装备评分) 3=副本榜
const (
	rankTypeLevel      = 1
	rankTypeEquipScore = 2
	rankTypeDungeon    = 3
)

type rankEntry struct {
	CharGuid uint64 `json:"charGuid"`
	Name     string `json:"name"`
	Job      int32  `json:"job"`
	Level    int32  `json:"level"`
	Value    int64  `json:"value"`
}

// loadRankedRoles 按排名类型加载全量角色并排序(等级榜按等级+经验,其余类型暂返回空)
func (s *APIV1Service) loadRankedRoles(c echo.Context, rankType uint32) ([]*store.Role, error) {
	if rankType != rankTypeLevel {
		// 战力榜/副本榜暂无数据来源,返回空榜
		return nil, nil
	}

	roles, err := s.Store.ListRoles(c.Request().Context(), &store.FindRole{})
	if err != nil {
		return nil, err
	}

	sort.SliceStable(roles, func(i, j int) bool {
		if roles[i].Level != roles[j].Level {
			return roles[i].Level > roles[j].Level
		}
		return roles[i].Exp > roles[j].Exp
	})
	return roles, nil
}

func toRankEntries(roles []*store.Role) []rankEntry {
	entries := make([]rankEntry, 0, len(roles))
	for _, r := range roles {
		entries = append(entries, rankEntry{
			CharGuid: r.ID,
			Name:     r.Name,
			Job:      r.Job,
			Level:    r.Level,
			Value:    int64(r.Level),
		})
	}
	return entries
}

// handleRankPersonal 查询个人排名
func (s *APIV1Service) handleRankPersonal(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	rankType := uint32(rankTypeLevel)
	if v, ok := req["type"].(float64); ok {
		rankType = uint32(v)
	}
	charGuid := claims.UserID
	if v, ok := req["charguid"].(float64); ok {
		charGuid = uint64(v)
	}

	roles, err := s.loadRankedRoles(c, rankType)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	rank := 0
	name := ""
	level := int32(0)
	value := int64(0)
	for i, r := range roles {
		if r.ID == charGuid {
			rank = i + 1
			name = r.Name
			level = r.Level
			value = int64(r.Level)
			break
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"rank": map[string]interface{}{
			"type":     rankType,
			"charGuid": charGuid,
			"rank":     rank,
			"name":     name,
			"level":    level,
			"value":    value,
		},
	})
}

// handleRankMy 查询我的排名
func (s *APIV1Service) handleRankMy(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	rankType := uint32(rankTypeLevel)
	if v, ok := req["type"].(float64); ok {
		rankType = uint32(v)
	}

	roleID := s.activeRoleID(c, claims)

	roles, err := s.loadRankedRoles(c, rankType)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	rank := 0
	name := ""
	level := int32(0)
	value := int64(0)
	for i, r := range roles {
		if r.ID == roleID {
			rank = i + 1
			name = r.Name
			level = r.Level
			value = int64(r.Level)
			break
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"rank": map[string]interface{}{
			"type":     rankType,
			"charGuid": roleID,
			"rank":     rank,
			"name":     name,
			"level":    level,
			"value":    value,
		},
	})
}

// handleRankFriend 查询好友排名
func (s *APIV1Service) handleRankFriend(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	rankType := uint32(rankTypeLevel)
	if v, ok := req["type"].(float64); ok {
		rankType = uint32(v)
	}

	// 好友排名:基于等级榜过滤好友(暂无好友数据源时返回空榜)
	roles, err := s.loadRankedRoles(c, rankType)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}
	_ = claims

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"type":    rankType,
		"ranking": toRankEntries(roles),
	})
}

// handleRankParty 查询我的队伍排名
func (s *APIV1Service) handleRankParty(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	rankType := uint32(rankTypeLevel)
	if v, ok := req["type"].(float64); ok {
		rankType = uint32(v)
	}

	// 队伍排名:暂无队伍榜单数据源,返回空榜
	_ = claims

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"type":    rankType,
		"ranking": []rankEntry{},
	})
}

// handleRankList 查询排行榜列表
func (s *APIV1Service) handleRankList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	rankType := uint32(rankTypeLevel)
	if v, ok := req["type"].(float64); ok {
		rankType = uint32(v)
	}
	page := 1
	if v, ok := req["page"].(float64); ok && v > 0 {
		page = int(v)
	}
	pageSize := 20
	if v, ok := req["page_size"].(float64); ok && v > 0 {
		pageSize = int(v)
	}

	roles, err := s.loadRankedRoles(c, rankType)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	total := len(roles)
	entries := toRankEntries(roles)

	start := (page - 1) * pageSize
	if start > total {
		start = total
	}
	end := start + pageSize
	if end > total {
		end = total
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"type":     rankType,
		"page":     page,
		"pageSize": pageSize,
		"total":    total,
		"ranking":  entries[start:end],
	})
}
