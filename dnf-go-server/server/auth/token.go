package auth

import (
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"time"

	"github.com/golang-jwt/jwt/v5"
)

const (
	AccessTokenDuration  = 15 * time.Minute
	RefreshTokenDuration = 7 * 24 * time.Hour
)

// JWTClaims JWT Claims结构
type JWTClaims struct {
	jwt.RegisteredClaims
	UserID   uint64 `json:"user_id"`
	Username string `json:"username"`
	RoleID   uint64 `json:"role_id"`
}

// GenerateAccessToken 生成访问令牌
func GenerateAccessToken(userID uint64, username string, roleID uint64, secret string) (string, error) {
	now := time.Now()
	claims := JWTClaims{
		RegisteredClaims: jwt.RegisteredClaims{
			ExpiresAt: jwt.NewNumericDate(now.Add(AccessTokenDuration)),
			IssuedAt:  jwt.NewNumericDate(now),
			NotBefore: jwt.NewNumericDate(now),
			Issuer:    "dnf-go-server",
			Subject:   fmt.Sprintf("%d", userID),
		},
		UserID:   userID,
		Username: username,
		RoleID:   roleID,
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	return token.SignedString([]byte(secret))
}

// ValidateAccessToken 验证访问令牌
func ValidateAccessToken(tokenString, secret string) (*UserClaims, error) {
	token, err := jwt.ParseWithClaims(tokenString, &JWTClaims{}, func(token *jwt.Token) (interface{}, error) {
		if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
		}
		return []byte(secret), nil
	})

	if err != nil {
		return nil, err
	}

	if claims, ok := token.Claims.(*JWTClaims); ok && token.Valid {
		return &UserClaims{
			UserID:   claims.UserID,
			Username: claims.Username,
			RoleID:   claims.RoleID,
		}, nil
	}
	return nil, fmt.Errorf("invalid token claims")
}

// GenerateRefreshToken 生成刷新令牌
func GenerateRefreshToken() (string, error) {
	b := make([]byte, 32)
	_, err := rand.Read(b)
	if err != nil {
		return "", err
	}
	return base64.URLEncoding.EncodeToString(b), nil
}
