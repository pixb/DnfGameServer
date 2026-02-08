package db

import (
	"fmt"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"github.com/pixb/DnfGameServer/dnf-go-server/store/db/mysql"
	"github.com/pixb/DnfGameServer/dnf-go-server/store/db/sqlite"
)

// ProfileProvider 配置接口
type ProfileProvider interface {
	GetDriver() string
	GetDSN() string
	GetMode() string
}

// NewDBDriver 创建数据库驱动实例
func NewDBDriver(profile ProfileProvider) (store.Driver, error) {
	switch profile.GetDriver() {
	case "mysql":
		return mysql.NewDB(profile)
	case "sqlite":
		return sqlite.NewDB(profile)
	case "postgres":
		// TODO: 实现PostgreSQL驱动
		return nil, fmt.Errorf("postgres driver not implemented yet")
	default:
		return nil, fmt.Errorf("unsupported driver: %s", profile.GetDriver())
	}
}
