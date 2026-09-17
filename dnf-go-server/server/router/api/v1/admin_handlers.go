package v1

import (
	"net/http"

	"github.com/labstack/echo/v4"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// handleAdminDisableAccount 禁用账号(Status=0)(2026-09-06 第三十七轮):
// 禁用后 TCP 登录返回 error=5; 注: 当前无管理员角色体系, 任意登录用户可调, 待补权限校验
func (s *APIV1Service) handleAdminDisableAccount(c echo.Context) error {
	return s.handleAdminSetAccountStatus(c, 0)
}

// handleAdminEnableAccount 启用账号(Status=1)(2026-09-06 第三十七轮)
func (s *APIV1Service) handleAdminEnableAccount(c echo.Context) error {
	return s.handleAdminSetAccountStatus(c, 1)
}

// handleAdminSetAccountStatus 设置账号状态(1=正常, 0=禁用)
// 2026-09-06 第三十八轮: 管理员权限校验(账号 Authority>=1)
func (s *APIV1Service) handleAdminSetAccountStatus(c echo.Context, status int32) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	// 管理员校验: 账号 Authority >= 1
	operator, err := s.Store.GetAccount(c.Request().Context(), &store.FindAccount{FindBase: store.FindBase{ID: &claims.UserID}})
	if err != nil || operator == nil || operator.Authority < 1 {
		return c.JSON(http.StatusForbidden, map[string]interface{}{"code": 9, "message": "admin permission required"})
	}

	req := decodeJSONBody(c)
	openid := c.FormValue("openid")
	if openid == "" {
		if v, ok := req["openid"].(string); ok {
			openid = v
		}
	}
	if openid == "" {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 4, "message": "openid required"})
	}

	target, err := s.Store.GetAccount(c.Request().Context(), &store.FindAccount{OpenID: &openid})
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6, "message": "account not found"})
	}

	if _, err := s.Store.UpdateAccount(c.Request().Context(), &store.UpdateAccount{ID: target.ID, Status: &status}); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": err.Error()})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "openid": openid, "status": status})
}
