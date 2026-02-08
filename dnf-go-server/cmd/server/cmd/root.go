package cmd

import (
	"fmt"
	"os"

	"github.com/spf13/cobra"
	"github.com/spf13/viper"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/profile"
)

var (
	cfgFile string
	prof    *profile.Profile
)

// rootCmd represents the base command when called without any subcommands
var rootCmd = &cobra.Command{
	Use:   "dnf-server",
	Short: "DNF Go Game Server",
	Long: `DNF Go Server - A high-performance game server written in Go.

This server provides both TCP game connections and HTTP API endpoints
for the DNF (Dungeon & Fighter) game.`,
	PersistentPreRunE: func(cmd *cobra.Command, args []string) error {
		// 加载配置
		if err := initConfig(); err != nil {
			return err
		}
		return nil
	},
}

// Execute adds all child commands to the root command and sets flags appropriately.
// This is called by main.main(). It only needs to happen once to the rootCmd.
func Execute() {
	if err := rootCmd.Execute(); err != nil {
		fmt.Fprintln(os.Stderr, err)
		os.Exit(1)
	}
}

func init() {
	// 全局flags
	rootCmd.PersistentFlags().StringVar(&cfgFile, "config", "", "config file (default is ./configs/config.yaml)")
	rootCmd.PersistentFlags().StringP("driver", "d", "mysql", "database driver (mysql, sqlite, postgres)")
	rootCmd.PersistentFlags().StringP("dsn", "", "", "database DSN")
	rootCmd.PersistentFlags().StringP("mode", "m", "prod", "server mode (dev, prod)")

	// 绑定viper
	viper.BindPFlag("driver", rootCmd.PersistentFlags().Lookup("driver"))
	viper.BindPFlag("dsn", rootCmd.PersistentFlags().Lookup("dsn"))
	viper.BindPFlag("mode", rootCmd.PersistentFlags().Lookup("mode"))
}

// initConfig reads in config file and ENV variables if set.
func initConfig() error {
	if cfgFile != "" {
		// Use config file from the flag.
		viper.SetConfigFile(cfgFile)
	} else {
		// Find home directory.
		home, err := os.UserHomeDir()
		if err != nil {
			return err
		}

		// Search config in home directory with name ".dnf-server" (without extension).
		viper.AddConfigPath(home)
		viper.AddConfigPath(".")
		viper.AddConfigPath("./configs")
		viper.SetConfigName("config")
		viper.SetConfigType("yaml")
	}

	// 读取配置文件
	if err := viper.ReadInConfig(); err != nil {
		// 配置文件不存在也没关系，使用默认配置或环境变量
		if _, ok := err.(viper.ConfigFileNotFoundError); !ok {
			return fmt.Errorf("failed to read config file: %w", err)
		}
	}

	// 创建profile
	prof = &profile.Profile{
		Driver: viper.GetString("driver"),
		DSN:    viper.GetString("dsn"),
		Mode:   viper.GetString("mode"),
		Port:   viper.GetInt("port"),
	}

	// 如果端口未设置，使用默认值
	if prof.Port == 0 {
		prof.Port = 8081
	}

	// 如果DSN未设置，尝试从环境变量读取
	if prof.DSN == "" {
		prof.DSN = os.Getenv("DATABASE_DSN")
	}

	return nil
}

// GetProfile returns the loaded profile
func GetProfile() *profile.Profile {
	return prof
}

// GetConfigFile returns the config file path
func GetConfigFile() string {
	if cfgFile != "" {
		return cfgFile
	}
	return viper.ConfigFileUsed()
}
