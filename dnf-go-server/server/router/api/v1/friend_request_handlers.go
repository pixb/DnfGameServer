package v1

import (
	"fmt"
	"net/http"

	"github.com/labstack/echo/v4"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 好友申请/同意流程(2026-09-06 第三十九轮) ====================

// handleFriendRequest 发送好友申请(target_name)
// 重复申请(同申请人→同接收人且待处理)幂等返回 error=0
func (s *APIV1Service) handleFriendRequest(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	targetName := c.FormValue("target_name")
	if targetName == "" {
		if v, ok := req["target_name"].(string); ok {
			targetName = v
		}
	}
	if targetName == "" {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "target_name required"})
	}

	targetRole, err := s.Store.GetRoleByName(c.Request().Context(), targetName)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6, "message": "target role not found"})
	}

	myRoleID := s.activeRoleID(c, claims)

	// 不能申请自己
	myRoles, _ := s.Store.ListRolesByAccount(c.Request().Context(), claims.UserID)
	for _, r := range myRoles {
		if r.ID == targetRole.ID {
			return c.JSON(http.StatusOK, map[string]interface{}{"error": 8, "message": "cannot request self"})
		}
	}

	// 已互为好友则幂等成功
	existing, _ := s.Store.GetFriend(c.Request().Context(), &store.FindFriend{
		RoleID:   &myRoleID,
		FriendID: &targetRole.ID,
	})
	if existing != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
	}

	// 已有待处理申请则幂等返回
	status0 := int32(0)
	pending, _ := s.Store.GetFriendRequest(c.Request().Context(), &store.FindFriendRequest{
		FromRoleID: &myRoleID,
		ToRoleID:   &targetRole.ID,
		Status:     &status0,
	})
	if pending != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "request_id": pending.ID})
	}

	myRole, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{FindBase: store.FindBase{ID: &myRoleID}})
	myName := ""
	if myRole != nil {
		myName = myRole.Name
	}

	fr, err := s.Store.CreateFriendRequest(c.Request().Context(), &store.FriendRequest{
		FromRoleID:   myRoleID,
		FromRoleName: myName,
		ToRoleID:     targetRole.ID,
		Status:       0,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "request_id": fr.ID})
}

// handleFriendRequestList 我的待处理好友申请列表
func (s *APIV1Service) handleFriendRequestList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	myRoleID := s.activeRoleID(c, claims)
	status0 := int32(0)
	list, err := s.Store.ListFriendRequests(c.Request().Context(), &store.FindFriendRequest{
		ToRoleID: &myRoleID,
		Status:   &status0,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	items := make([]map[string]interface{}, 0, len(list))
	for _, fr := range list {
		items = append(items, map[string]interface{}{
			"request_id":     fr.ID,
			"from_role_id":   fr.FromRoleID,
			"from_role_name": fr.FromRoleName,
			"created_at":     fr.CreatedAt,
		})
	}
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "requests": items})
}

// handleFriendRequestApprove 同意好友申请: 双向建立好友关系 + 申请置为已同意
func (s *APIV1Service) handleFriendRequestApprove(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	requestIDStr := c.FormValue("request_id")
	if requestIDStr == "" {
		if v, ok := req["request_id"].(string); ok {
			requestIDStr = v
		}
	}
	if requestIDStr == "" {
		if v, ok := req["request_id"].(float64); ok {
			requestIDStr = fmt.Sprintf("%.0f", v)
		}
	}
	if requestIDStr == "" {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "request_id required"})
	}

	var requestID uint64
	if _, err := fmt.Sscanf(requestIDStr, "%d", &requestID); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "invalid request_id"})
	}

	myRoleID := s.activeRoleID(c, claims)

	fr, err := s.Store.GetFriendRequest(c.Request().Context(), &store.FindFriendRequest{FindBase: store.FindBase{ID: &requestID}})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6, "message": "request not found"})
	}
	if fr.ToRoleID != myRoleID {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 8, "message": "not your request"})
	}
	if fr.Status != 0 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 7, "message": "request already processed"})
	}

	// 双向建立好友关系(幂等)
	fromRole, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{FindBase: store.FindBase{ID: &fr.FromRoleID}})
	fromName := fr.FromRoleName
	if fromRole != nil {
		fromName = fromRole.Name
	}
	myRole, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{FindBase: store.FindBase{ID: &myRoleID}})
	myName := ""
	if myRole != nil {
		myName = myRole.Name
	}
	// 双向建立好友关系(幂等; 2026-09-06 第四十二轮: 申请同意初始化亲密度 10)
	s.addFriendRelation(c, myRoleID, fr.FromRoleID, fromName, 10)
	s.addFriendRelation(c, fr.FromRoleID, myRoleID, myName, 10)

	// 申请置为已同意
	status1 := int32(1)
	if err := s.Store.UpdateFriendRequest(c.Request().Context(), &store.UpdateFriendRequest{ID: fr.ID, Status: &status1}); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

// handleFriendRequestReject 拒绝好友申请: 申请置为已拒绝
func (s *APIV1Service) handleFriendRequestReject(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	requestIDStr := c.FormValue("request_id")
	if requestIDStr == "" {
		if v, ok := req["request_id"].(string); ok {
			requestIDStr = v
		}
	}
	if requestIDStr == "" {
		if v, ok := req["request_id"].(float64); ok {
			requestIDStr = fmt.Sprintf("%.0f", v)
		}
	}
	if requestIDStr == "" {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "request_id required"})
	}

	var requestID uint64
	if _, err := fmt.Sscanf(requestIDStr, "%d", &requestID); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "invalid request_id"})
	}

	myRoleID := s.activeRoleID(c, claims)

	fr, err := s.Store.GetFriendRequest(c.Request().Context(), &store.FindFriendRequest{FindBase: store.FindBase{ID: &requestID}})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6, "message": "request not found"})
	}
	if fr.ToRoleID != myRoleID {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 8, "message": "not your request"})
	}
	if fr.Status != 0 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 7, "message": "request already processed"})
	}

	status2 := int32(2)
	if err := s.Store.UpdateFriendRequest(c.Request().Context(), &store.UpdateFriendRequest{ID: fr.ID, Status: &status2}); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

// handleFriendGroup 修改好友分组(2026-09-06 第四十二轮): {friend_uid, group}
func (s *APIV1Service) handleFriendGroup(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	friendUIDStr := c.FormValue("friend_uid")
	if friendUIDStr == "" {
		if v, ok := req["friend_uid"].(string); ok {
			friendUIDStr = v
		}
	}
	if friendUIDStr == "" {
		if v, ok := req["friend_uid"].(float64); ok {
			friendUIDStr = fmt.Sprintf("%.0f", v)
		}
	}
	group := c.FormValue("group")
	if group == "" {
		if v, ok := req["group"].(string); ok {
			group = v
		}
	}
	if friendUIDStr == "" || group == "" {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "friend_uid and group required"})
	}
	if len([]rune(group)) > 16 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "group too long (max 16)"})
	}

	var friendUID uint64
	if _, err := fmt.Sscanf(friendUIDStr, "%d", &friendUID); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "invalid friend_uid"})
	}

	myRoleID := s.activeRoleID(c, claims)
	friend, err := s.Store.GetFriend(c.Request().Context(), &store.FindFriend{
		RoleID:   &myRoleID,
		FriendID: &friendUID,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6, "message": "friend not found"})
	}

	if err := s.Store.UpdateFriend(c.Request().Context(), &store.UpdateFriend{ID: friend.ID, Group: &group}); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "group": group})
}

// handleFriendIntimacy 增减好友亲密度(2026-09-06 第四十二轮): {friend_uid, delta}, 结果钳制 0~9999
func (s *APIV1Service) handleFriendIntimacy(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	req := decodeJSONBody(c)
	friendUIDStr := c.FormValue("friend_uid")
	if friendUIDStr == "" {
		if v, ok := req["friend_uid"].(string); ok {
			friendUIDStr = v
		}
	}
	if friendUIDStr == "" {
		if v, ok := req["friend_uid"].(float64); ok {
			friendUIDStr = fmt.Sprintf("%.0f", v)
		}
	}
	deltaStr := c.FormValue("delta")
	if deltaStr == "" {
		if v, ok := req["delta"].(float64); ok {
			deltaStr = fmt.Sprintf("%.0f", v)
		}
	}
	if friendUIDStr == "" || deltaStr == "" {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "friend_uid and delta required"})
	}

	var friendUID uint64
	var delta int32
	if _, err := fmt.Sscanf(friendUIDStr, "%d", &friendUID); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "invalid friend_uid"})
	}
	if _, err := fmt.Sscanf(deltaStr, "%d", &delta); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "invalid delta"})
	}

	myRoleID := s.activeRoleID(c, claims)
	friend, err := s.Store.GetFriend(c.Request().Context(), &store.FindFriend{
		RoleID:   &myRoleID,
		FriendID: &friendUID,
	})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6, "message": "friend not found"})
	}

	newIntimacy := friend.Intimacy + delta
	if newIntimacy < 0 {
		newIntimacy = 0
	}
	if newIntimacy > 9999 {
		newIntimacy = 9999
	}
	if err := s.Store.UpdateFriend(c.Request().Context(), &store.UpdateFriend{ID: friend.ID, Intimacy: &newIntimacy}); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "intimacy": newIntimacy})
}
