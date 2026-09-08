package tests

import (
	"database/sql"
	"fmt"
	"time"

	_ "github.com/go-sql-driver/mysql"
)

// testDBDSN 集成测试直连 MySQL(与服务端同一实例)
const testDBDSN = "root:123456@tcp(127.0.0.1:3306)/game?charset=utf8mb4&parseTime=True&loc=Local"

// upsertDirtyRecipe 直插脏配方(引用未知模板), 供模板存在性校验测试(2026-09-08 第七十七轮)
func upsertDirtyRecipe(recipeIndex int32) error {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return fmt.Errorf("open db: %w", err)
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	_, err = db.Exec(`INSERT INTO t_make_recipe (recipe_index, result_index, result_count, material_list, cost_money, enabled, success_rate) VALUES (?, 999999, 1, '[{"index":2001,"count":1}]', 0, 1, 100) ON DUPLICATE KEY UPDATE recipe_index = recipe_index`, recipeIndex)
	if err != nil {
		return fmt.Errorf("upsert dirty recipe: %w", err)
	}
	return nil
}

// dropDirtyRecipe 删除测试脏配方
func dropDirtyRecipe(recipeIndex int32) error {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return fmt.Errorf("open db: %w", err)
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	_, err = db.Exec(`DELETE FROM t_make_recipe WHERE recipe_index = ?`, recipeIndex)
	if err != nil {
		return fmt.Errorf("drop dirty recipe: %w", err)
	}
	return nil
}

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
