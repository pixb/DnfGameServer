package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"time"

	_ "github.com/go-sql-driver/mysql"
	_ "github.com/mattn/go-sqlite3"
)

// MySQL连接配置
const (
	mysqlHost     = "127.0.0.1"
	mysqlPort     = "3306"
	mysqlUser     = "root"
	mysqlPassword = "123456"
	mysqlDatabase = "game"
)

// SQLite数据库路径
const sqliteDB = "../game.db"

func main() {
	fmt.Println("============================================================")
	fmt.Println("MySQL 到 SQLite 数据迁移工具")
	fmt.Println("============================================================")
	fmt.Println()

	// 连接MySQL
	fmt.Println("连接MySQL数据库...")
	mysqlDSN := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8mb4&parseTime=True&loc=Local",
		mysqlUser, mysqlPassword, mysqlHost, mysqlPort, mysqlDatabase)
	mysqlDB, err := sql.Open("mysql", mysqlDSN)
	if err != nil {
		log.Fatalf("✗ MySQL连接失败: %v", err)
	}
	defer mysqlDB.Close()

	if err := mysqlDB.Ping(); err != nil {
		log.Fatalf("✗ MySQL连接失败: %v", err)
	}
	fmt.Println("✓ MySQL连接成功")

	// 连接SQLite
	fmt.Println("连接SQLite数据库...")
	sqliteDB, err := sql.Open("sqlite3", sqliteDB)
	if err != nil {
		log.Fatalf("✗ SQLite连接失败: %v", err)
	}
	defer sqliteDB.Close()

	if err := sqliteDB.Ping(); err != nil {
		log.Fatalf("✗ SQLite连接失败: %v", err)
	}
	fmt.Println("✓ SQLite连接成功")
	fmt.Println()

	fmt.Println("开始数据迁移...")
	fmt.Println("------------------------------------------------------------")

	// 迁移数据
	accountCount := migrateAccounts(mysqlDB, sqliteDB)
	roleCount := migrateRoles(mysqlDB, sqliteDB)
	achievementConfigCount := migrateAchievementConfig(mysqlDB, sqliteDB)
	achievementRecordCount := migrateAchievementRecords(mysqlDB, sqliteDB)
	configCount := migrateConfigTables(mysqlDB, sqliteDB)

	fmt.Println("------------------------------------------------------------")
	fmt.Println("数据迁移完成!")
	fmt.Println()

	// 验证数据
	verifyData(sqliteDB)

	fmt.Println("============================================================")
	fmt.Println("迁移统计:")
	fmt.Printf("  账户数据: %d 条\n", accountCount)
	fmt.Printf("  角色数据: %d 条\n", roleCount)
	fmt.Printf("  成就配置: %d 条\n", achievementConfigCount)
	fmt.Printf("  成就记录: %d 条\n", achievementRecordCount)
	fmt.Printf("  配置数据: %d 条\n", configCount)
	fmt.Println("============================================================")
}

func datetimeToTimestamp(dtStr string) int64 {
	if dtStr == "" {
		return 0
	}
	dt, err := time.Parse("2006-01-02 15:04:05", dtStr)
	if err != nil {
		return 0
	}
	return dt.Unix()
}

func migrateAccounts(mysqlDB, sqliteDB *sql.DB) int {
	fmt.Println("迁移账户数据...")

	rows, err := mysqlDB.Query("SELECT * FROM t_account")
	if err != nil {
		fmt.Printf("✗ 迁移账户数据失败: %v\n", err)
		return 0
	}
	defer rows.Close()

	count := 0
	for rows.Next() {
		var id, accountKey, passwd, userID, channelNo string
		var accumulatecera int64
		var isStop, privilege, score, zhanlingexp, storageline, roleMaxCount, lastLoginTime int
		var createTime sql.NullTime
		var regDate sql.NullString
		var moneyBox, epicPieceBox, mailBox, accShopInfoBox, adventureReapInfo, adventureUnionInfo, adStorageBox, advBookBox, advUnionSubInfoBox, activityBox, logBox sql.NullString

		err := rows.Scan(&id, &accountKey, &accumulatecera, &userID, &passwd, &isStop, &privilege, &score, &channelNo, &createTime, &zhanlingexp, &moneyBox, &epicPieceBox, &mailBox, &storageline, &accShopInfoBox, &adventureReapInfo, &adventureUnionInfo, &adStorageBox, &advBookBox, &advUnionSubInfoBox, &roleMaxCount, &regDate, &logBox, &activityBox, &lastLoginTime)
		if err != nil {
			fmt.Printf("  警告: 扫描账户数据失败: %v\n", err)
			continue
		}

		var created int64
		if createTime.Valid {
			created = createTime.Time.Unix()
		}

		_, err = sqliteDB.Exec(`
			INSERT OR REPLACE INTO account 
			(openid, account_key, auth_key, last_login_at, authority, status, created_at, updated_at)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?)
		`, id, accountKey, passwd, lastLoginTime, privilege, isStop, created, created)

		if err != nil {
			fmt.Printf("  警告: 插入账户数据失败: %v\n", err)
			continue
		}

		count++
	}

	fmt.Printf("✓ 迁移了 %d 条账户数据\n", count)
	return count
}

func migrateRoles(mysqlDB, sqliteDB *sql.DB) int {
	fmt.Println("迁移角色数据...")

	rows, err := mysqlDB.Query("SELECT * FROM t_role")
	if err != nil {
		fmt.Printf("✗ 迁移角色数据失败: %v\n", err)
		return 0
	}
	defer rows.Close()

	count := 0
	for rows.Next() {
		var openid string
		var roleId, lastlogout, growtype, secgrowtype, job, level, fatigue, equipscore, characterframe, money, rescoin, contributioncoin, magiccrystal, highmagiccrystal, cerascore, pkcoin, friendpoint, smallcoin, avatarVisibleFlags, deletionstatus, deletiontime, createtime, changename, exp, sp, tp, addsp, addtp, day, score, qindex, lockTime, storageline, wordTime, weaponIndex, expratio, fatigueratio, preOnlineSec, loginDays, linenum, firstPayStatus int
		var name, distName, servername, adventurename string
		var updateTime sql.NullTime
		var pos, serverSimpleDataBox, friendBox, titleBox, avatarBox, emblemBox, cardBox, creatureBox, artifactBox, equipBox, equippedBox, materialBox, consumableBox, roleShopInfoBox, crackEquipBox, crackBox, damageBox, chatFrameBox, charFrameBox, sdAvatarBox, bookmarkBox, scrollBox, moneyBox, ceraShopBuyInfo, tutoBox, skillBox, skillslotBox, dungeonTicketsBox, tonicBox, mailBox, sysMailBox, charStorageBox, rePurStoItem, towerInfoBox, creatureErrandBox, localRewardBox, questInfoBox, sysBuffBox, clearDungeonBox, achievementBox, collectionBox, noteMsgBox, essenceBox, auctionBox, taskJson, petBoxJson, skillBoxJson, equipBoxJson, mailBoxJson, sysMailBoxJson, serverSimpleDataBoxJson, roleDropJson sql.NullString

		err := rows.Scan(&roleId, &lastlogout, &growtype, &secgrowtype, &job, &level, &exp, &fatigue, &equipscore, &characterframe, &money, &rescoin, &contributioncoin, &magiccrystal, &highmagiccrystal, &cerascore, &pkcoin, &friendpoint, &smallcoin, &avatarVisibleFlags, &deletionstatus, &deletiontime, &createtime, &changename, &name, &sp, &tp, &addsp, &addtp, &day, &score, &qindex, &distName, &servername, &updateTime, &lockTime, &pos, &storageline, &wordTime, &weaponIndex, &expratio, &fatigueratio, &adventurename, &serverSimpleDataBox, &friendBox, &titleBox, &avatarBox, &emblemBox, &cardBox, &creatureBox, &artifactBox, &equipBox, &equippedBox, &materialBox, &consumableBox, &roleShopInfoBox, &crackEquipBox, &crackBox, &damageBox, &chatFrameBox, &charFrameBox, &sdAvatarBox, &bookmarkBox, &scrollBox, &moneyBox, &ceraShopBuyInfo, &tutoBox, &skillBox, &skillslotBox, &dungeonTicketsBox, &tonicBox, &mailBox, &sysMailBox, &charStorageBox, &rePurStoItem, &towerInfoBox, &creatureErrandBox, &localRewardBox, &questInfoBox, &sysBuffBox, &clearDungeonBox, &achievementBox, &collectionBox, &noteMsgBox, &essenceBox, &auctionBox, &taskJson, &petBoxJson, &skillBoxJson, &equipBoxJson, &mailBoxJson, &sysMailBoxJson, &serverSimpleDataBoxJson, &roleDropJson, &preOnlineSec, &loginDays, &linenum, &firstPayStatus, &openid)
		if err != nil {
			fmt.Printf("  警告: 扫描角色数据失败: %v\n", err)
			continue
		}

		// 获取account_id
		var accountID int
		err = sqliteDB.QueryRow("SELECT id FROM account WHERE openid = ?", openid).Scan(&accountID)
		if err != nil {
			fmt.Printf("  警告: 角色 %s 的账户 %s 不存在，跳过\n", name, openid)
			continue
		}

		// 解析pos JSON
		x, y := 0, 0
		if pos.Valid && pos.String != "" {
			var posData map[string]interface{}
			if err := json.Unmarshal([]byte(pos.String), &posData); err == nil {
				if val, ok := posData["x"].(float64); ok {
					x = int(val)
				}
				if val, ok := posData["y"].(float64); ok {
					y = int(val)
				}
			}
		}

		_, err = sqliteDB.Exec(`
			INSERT OR REPLACE INTO role 
			(account_id, role_id, name, job, level, exp, fatigue, x, y, created_at, updated_at)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
		`, accountID, roleId, name, job, level, exp, fatigue, x, y, createtime, createtime)

		if err != nil {
			fmt.Printf("  警告: 插入角色数据失败: %v\n", err)
			continue
		}

		count++
	}

	if count == 0 {
		fmt.Println("  警告: 没有角色数据需要迁移")
	}

	fmt.Printf("✓ 迁移了 %d 条角色数据\n", count)
	return count
}

func migrateAchievementConfig(mysqlDB, sqliteDB *sql.DB) int {
	fmt.Println("迁移成就配置数据...")

	rows, err := mysqlDB.Query("SELECT * FROM p_achievement")
	if err != nil {
		fmt.Printf("✗ 迁移成就配置数据失败: %v\n", err)
		return 0
	}
	defer rows.Close()

	count := 0
	for rows.Next() {
		var index int
		var title, ptype, subtype, summary, condition string
		var countVal int

		err := rows.Scan(&index, &title, &ptype, &subtype, &summary, &condition, &countVal)
		if err != nil {
			fmt.Printf("  警告: 扫描成就配置数据失败: %v\n", err)
			continue
		}

		_, err = sqliteDB.Exec(`
			INSERT OR REPLACE INTO t_achievement_config 
			(achievement_id, name, description, target_value, reward_type, reward_index, reward_count)
			VALUES (?, ?, ?, ?, ?, ?, ?)
		`, index, title, summary, countVal, 0, 0, 0)

		if err != nil {
			fmt.Printf("  警告: 插入成就配置数据失败: %v\n", err)
			continue
		}

		count++
	}

	fmt.Printf("✓ 迁移了 %d 条成就配置数据\n", count)
	return count
}

func migrateAchievementRecords(mysqlDB, sqliteDB *sql.DB) int {
	fmt.Println("迁移成就记录数据...")

	// 检查是否有成就记录表
	var tableName string
	err := mysqlDB.QueryRow("SHOW TABLES LIKE 't_achievement_record'").Scan(&tableName)
	if err != nil {
		fmt.Println("  提示: MySQL中没有成就记录表，跳过迁移")
		return 0
	}

	rows, err := mysqlDB.Query("SELECT * FROM t_achievement_record")
	if err != nil {
		fmt.Printf("✗ 迁移成就记录数据失败: %v\n", err)
		return 0
	}
	defer rows.Close()

	count := 0
	for rows.Next() {
		var id, roleId, achievementId, progress, completed, rewarded int
		var rewardTime, createTime, updateTime sql.NullTime

		err := rows.Scan(&id, &roleId, &achievementId, &progress, &completed, &rewarded, &rewardTime, &createTime, &updateTime)
		if err != nil {
			fmt.Printf("  警告: 扫描成就记录数据失败: %v\n", err)
			continue
		}

		// 转换role_id
		var sqliteRoleID int
		err = sqliteDB.QueryRow("SELECT id FROM role WHERE role_id = ?", roleId).Scan(&sqliteRoleID)
		if err != nil {
			fmt.Printf("  警告: 成就记录的角色ID %d 不存在，跳过\n", roleId)
			continue
		}

		var rewardTs, createTs, updateTs int64
		if rewardTime.Valid {
			rewardTs = rewardTime.Time.Unix()
		}
		if createTime.Valid {
			createTs = createTime.Time.Unix()
		}
		if updateTime.Valid {
			updateTs = updateTime.Time.Unix()
		}

		_, err = sqliteDB.Exec(`
			INSERT OR REPLACE INTO t_achievement_record 
			(role_id, achievement_id, progress, completed, rewarded, reward_time, created_at, updated_at)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?)
		`, sqliteRoleID, achievementId, progress, completed, rewarded, rewardTs, createTs, updateTs)

		if err != nil {
			fmt.Printf("  警告: 插入成就记录数据失败: %v\n", err)
			continue
		}

		count++
	}

	fmt.Printf("✓ 迁移了 %d 条成就记录数据\n", count)
	return count
}

func migrateConfigTables(mysqlDB, sqliteDB *sql.DB) int {
	fmt.Println("迁移配置表数据...")

	configTables := []string{
		"p_exp",
		"p_equip",
		"p_consume",
		"p_skill",
		"p_npc",
		"p_gamemap",
		"p_dungeon",
		"p_petexp",
		"p_itemshop",
		"p_server",
		"p_onlinemall",
		"p_transfer",
		"p_taskset",
		"p_talkset",
		"p_skin",
		"p_skill_play_time",
		"p_mapbosspos",
		"p_word",
		"p_itemdropset",
		"p_dungeonmap",
	}

	totalCount := 0
	for _, tableName := range configTables {
		count := migrateConfigTable(mysqlDB, sqliteDB, tableName)
		if count > 0 {
			fmt.Printf("  ✓ 迁移了 %d 条 %s 数据\n", count, tableName)
			totalCount += count
		}
	}

	return totalCount
}

func migrateConfigTable(mysqlDB, sqliteDB *sql.DB, tableName string) int {
	rows, err := mysqlDB.Query(fmt.Sprintf("SELECT * FROM %s", tableName))
	if err != nil {
		return 0
	}
	defer rows.Close()

	columns, err := rows.Columns()
	if err != nil {
		return 0
	}

	if len(columns) == 0 {
		return 0
	}

	count := 0
	for rows.Next() {
		values := make([]interface{}, len(columns))
		valuePtrs := make([]interface{}, len(columns))
		for i := range columns {
			valuePtrs[i] = &values[i]
		}

		if err := rows.Scan(valuePtrs...); err != nil {
			continue
		}

		placeholders := make([]string, len(columns))
		for i := range placeholders {
			placeholders[i] = "?"
		}

		_, err = sqliteDB.Exec(
			fmt.Sprintf("INSERT OR REPLACE INTO %s (%s) VALUES (%s)", tableName,
				fmt.Sprintf("%s", columns),
				fmt.Sprintf("%s", placeholders)),
			values...,
		)

		if err != nil {
			continue
		}

		count++
	}

	return count
}

func verifyData(sqliteDB *sql.DB) {
	fmt.Println("验证迁移数据...")

	var accountCount int
	sqliteDB.QueryRow("SELECT COUNT(*) FROM account").Scan(&accountCount)
	fmt.Printf("  账户数据: %d 条\n", accountCount)

	var roleCount int
	sqliteDB.QueryRow("SELECT COUNT(*) FROM role").Scan(&roleCount)
	fmt.Printf("  角色数据: %d 条\n", roleCount)

	var achievementConfigCount int
	sqliteDB.QueryRow("SELECT COUNT(*) FROM t_achievement_config").Scan(&achievementConfigCount)
	fmt.Printf("  成就配置: %d 条\n", achievementConfigCount)

	var achievementRecordCount int
	sqliteDB.QueryRow("SELECT COUNT(*) FROM t_achievement_record").Scan(&achievementRecordCount)
	fmt.Printf("  成就记录: %d 条\n", achievementRecordCount)

	var configTableCount int
	sqliteDB.QueryRow("SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name LIKE 'p_%'").Scan(&configTableCount)
	fmt.Printf("  配置表: %d 个\n", configTableCount)
}
