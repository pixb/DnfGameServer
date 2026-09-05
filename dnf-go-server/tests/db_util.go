package tests

import (
	"database/sql"
	"fmt"
	"time"

	_ "github.com/go-sql-driver/mysql"
)

// testDBDSN 集成测试直连 MySQL(与服务端同一实例)
const testDBDSN = "root:123456@tcp(127.0.0.1:3306)/game?charset=utf8mb4&parseTime=True&loc=Local"

// setGold 直接更新角色金币(集成测试数据准备,绕过 HTTP 层)
func setGold(roleID uint64, gold int64) error {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return fmt.Errorf("open db: %w", err)
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	_, err = db.Exec("INSERT INTO role_currency (role_id, gold, coin, fatigue, max_fatigue) VALUES (?, ?, 0, 156, 156) ON DUPLICATE KEY UPDATE gold = ?", roleID, gold, gold)
	if err != nil {
		return fmt.Errorf("set gold: %w", err)
	}
	return nil
}
