package auth

import (
	"context"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// UserClaims 用户Claims
type UserClaims struct {
	UserID   uint64
	Username string
	RoleID   uint64
}

// ContextKey 上下文键类型
type ContextKey int

const (
	UserIDContextKey ContextKey = iota
	UserClaimsContextKey
	AccessTokenContextKey
)

// SetUserClaimsInContext 在上下文中设置Claims
func SetUserClaimsInContext(ctx context.Context, claims *UserClaims) context.Context {
	return context.WithValue(ctx, UserClaimsContextKey, claims)
}

// GetUserClaimsFromContext 从上下文获取Claims
func GetUserClaimsFromContext(ctx context.Context) *UserClaims {
	if v, ok := ctx.Value(UserClaimsContextKey).(*UserClaims); ok {
		return v
	}
	return nil
}

// GetUserIDFromContext 从上下文获取用户ID
func GetUserIDFromContext(ctx context.Context) uint64 {
	if v, ok := ctx.Value(UserIDContextKey).(uint64); ok {
		return v
	}
	return 0
}

// AuthResult 认证结果
type AuthResult struct {
	User        *store.Account
	Claims      *UserClaims
	AccessToken string
}

// Authenticator 认证器
type Authenticator struct {
	Store  *store.Store
	Secret string
}

// NewAuthenticator 创建认证器
func NewAuthenticator(s *store.Store, secret string) *Authenticator {
	return &Authenticator{
		Store:  s,
		Secret: secret,
	}
}

// Authenticate 执行认证
func (a *Authenticator) Authenticate(ctx context.Context, authHeader string) *AuthResult {
	token := ExtractBearerToken(authHeader)
	if token == "" {
		return nil
	}

	// 验证JWT
	claims, err := ValidateAccessToken(token, a.Secret)
	if err != nil || claims == nil {
		return nil
	}

	return &AuthResult{
		Claims:      claims,
		AccessToken: token,
	}
}
