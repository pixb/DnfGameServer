package auth

import "strings"

const BearerPrefix = "Bearer "

// ExtractBearerToken 从Authorization头提取Token
func ExtractBearerToken(authHeader string) string {
	if strings.HasPrefix(authHeader, BearerPrefix) {
		return strings.TrimPrefix(authHeader, BearerPrefix)
	}
	return ""
}
