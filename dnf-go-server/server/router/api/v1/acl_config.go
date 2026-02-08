package v1

// PublicMethods 定义不需要认证的公开端点
var PublicMethods = map[string]struct{}{
	// 认证服务
	"/dnf.v1.AuthService/Login":          {},
	"/dnf.v1.AuthService/Register":       {},
	"/dnf.v1.AuthService/RefreshToken":   {},
	"/dnf.v1.AuthService/GetServerList":  {},
	"/dnf.v1.AuthService/GetChannelList": {},

	// 健康检查
	"/dnf.v1.SystemService/HealthCheck": {},
	"/dnf.v1.SystemService/GetVersion":  {},
}
