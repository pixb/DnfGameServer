package profile

import "time"

// Profile 配置结构
type Profile struct {
	Driver string `json:"driver" yaml:"driver"`
	DSN    string `json:"dsn" yaml:"dsn"`
	Mode   string `json:"mode" yaml:"mode"`
	Port   int    `json:"port" yaml:"port"`
	// TCPPort TCP游戏服务器端口（默认9000）
	TCPPort int `json:"tcp_port" yaml:"tcp_port"`
	// MailCleanupInterval 邮件过期清理周期(Go duration 字符串, 如 "5m"/"30s"; 空/非法回退 5m)
	MailCleanupInterval string `json:"mail_cleanup_interval" yaml:"mail_cleanup_interval"`
}

// DefaultMailCleanupInterval 邮件过期清理默认周期
const DefaultMailCleanupInterval = 5 * time.Minute

// MailCleanupIntervalDuration 解析邮件清理周期, 空/非法回退默认 5 分钟(2026-09-06 第二十九轮)
func (p *Profile) MailCleanupIntervalDuration() time.Duration {
	if p == nil || p.MailCleanupInterval == "" {
		return DefaultMailCleanupInterval
	}
	d, err := time.ParseDuration(p.MailCleanupInterval)
	if err != nil || d <= 0 {
		return DefaultMailCleanupInterval
	}
	return d
}

// GetDriver 获取数据库驱动
func (p *Profile) GetDriver() string {
	return p.Driver
}

// GetDSN 获取数据库DSN
func (p *Profile) GetDSN() string {
	return p.DSN
}

// GetMode 获取运行模式
func (p *Profile) GetMode() string {
	if p.Mode == "" {
		return "prod"
	}
	return p.Mode
}
