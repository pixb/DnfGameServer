package profile

// Profile 配置结构
type Profile struct {
	Driver string `json:"driver" yaml:"driver"`
	DSN    string `json:"dsn" yaml:"dsn"`
	Mode   string `json:"mode" yaml:"mode"`
	Port   int    `json:"port" yaml:"port"`
	// TCPPort TCP游戏服务器端口（默认9000）
	TCPPort int `json:"tcp_port" yaml:"tcp_port"`
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
