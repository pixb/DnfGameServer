package config

import (
	"fmt"
	"strings"

	"github.com/spf13/viper"
)

// Config 应用配置结构
type Config struct {
	Server   ServerConfig   `mapstructure:"server"`
	Database DatabaseConfig `mapstructure:"database"`
	Cache    CacheConfig    `mapstructure:"cache"`
	Log      LogConfig      `mapstructure:"log"`
}

// ServerConfig 服务器配置
type ServerConfig struct {
	TCP struct {
		Host    string `mapstructure:"host"`
		Port    int    `mapstructure:"port"`
		Backlog int    `mapstructure:"backlog"`
	} `mapstructure:"tcp"`
	HTTP struct {
		Host string `mapstructure:"host"`
		Port int    `mapstructure:"port"`
	} `mapstructure:"http"`
}

// DatabaseConfig 数据库配置
type DatabaseConfig struct {
	Driver          string `mapstructure:"driver"`
	Host            string `mapstructure:"host"`
	Port            int    `mapstructure:"port"`
	Username        string `mapstructure:"username"`
	Password        string `mapstructure:"password"`
	Database        string `mapstructure:"database"`
	MaxOpenConns    int    `mapstructure:"max_open_conns"`
	MaxIdleConns    int    `mapstructure:"max_idle_conns"`
	ConnMaxLifetime int    `mapstructure:"conn_max_lifetime"`
	ConnMaxIdleTime int    `mapstructure:"conn_max_idle_time"`
}

// CacheConfig 缓存配置
type CacheConfig struct {
	DefaultExpiration int `mapstructure:"default_expiration"`
	CleanupInterval   int `mapstructure:"cleanup_interval"`
}

// LogConfig 日志配置
type LogConfig struct {
	Level         string `mapstructure:"level"`
	IsDevelopment bool   `mapstructure:"is_development"`
}

// GlobalConfig 全局配置实例
var GlobalConfig *Config

// Load 加载配置文件
func Load(path string) (*Config, error) {
	v := viper.New()

	// 设置默认值
	setDefaults(v)

	// 设置配置文件路径
	if path != "" {
		v.SetConfigFile(path)
	} else {
		v.SetConfigName("config")
		v.SetConfigType("yaml")
		v.AddConfigPath("./configs")
		v.AddConfigPath(".")
	}

	// 读取环境变量前缀
	v.SetEnvPrefix("DNF")
	v.SetEnvKeyReplacer(strings.NewReplacer(".", "_"))
	v.AutomaticEnv()

	// 读取配置文件
	if err := v.ReadInConfig(); err != nil {
		// 配置文件不存在时，使用默认值
		if _, ok := err.(viper.ConfigFileNotFoundError); !ok {
			return nil, fmt.Errorf("failed to read config file: %w", err)
		}
	}

	var cfg Config
	if err := v.Unmarshal(&cfg); err != nil {
		return nil, fmt.Errorf("failed to unmarshal config: %w", err)
	}

	GlobalConfig = &cfg
	return &cfg, nil
}

// setDefaults 设置默认值
func setDefaults(v *viper.Viper) {
	// TCP Server 默认值
	v.SetDefault("server.tcp.host", "0.0.0.0")
	v.SetDefault("server.tcp.port", 9000)
	v.SetDefault("server.tcp.backlog", 3000)

	// HTTP Server 默认值
	v.SetDefault("server.http.host", "0.0.0.0")
	v.SetDefault("server.http.port", 8080)

	// 数据库默认值
	v.SetDefault("database.driver", "mysql")
	v.SetDefault("database.host", "127.0.0.1")
	v.SetDefault("database.port", 3306)
	v.SetDefault("database.max_open_conns", 100)
	v.SetDefault("database.max_idle_conns", 10)
	v.SetDefault("database.conn_max_lifetime", 3600)
	v.SetDefault("database.conn_max_idle_time", 600)

	// 缓存默认值
	v.SetDefault("cache.default_expiration", 300)
	v.SetDefault("cache.cleanup_interval", 600)

	// 日志默认值
	v.SetDefault("log.level", "info")
	v.SetDefault("log.is_development", false)
}

// GetConfig 获取全局配置
func GetConfig() *Config {
	if GlobalConfig == nil {
		// 返回默认配置
		return &Config{}
	}
	return GlobalConfig
}

// GetDSN 获取数据库连接字符串
func (d *DatabaseConfig) GetDSN() string {
	return fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=True&loc=Local",
		d.Username, d.Password, d.Host, d.Port, d.Database)
}
