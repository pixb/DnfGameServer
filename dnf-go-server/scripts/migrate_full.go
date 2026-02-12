package main

import (
	"database/sql"
	"fmt"
	"log"
	"strings"

	_ "github.com/go-sql-driver/mysql"
	_ "github.com/mattn/go-sqlite3"
)

// MySQL连接配置
const (
	mysqlHost     = "127.0.0.1"
	mysqlPort     = "3306"
	mysqlUser     = "root"
	mysqlPassword = "123456"
)

// SQLite数据库路径
const sqliteDB = "../game.db"

// 需要迁移的数据库
var databases = []string{"game", "login"}

// game库需要迁移的表
var gameTables = []string{
	// 配置表
	"p_achievement",
	"p_consume",
	"p_dungeon",
	"p_dungeonmap",
	"p_equip",
	"p_exp",
	"p_gamemap",
	"p_itemdropset",
	"p_itemshop",
	"p_mapbosspos",
	"p_npc",
	"p_onlinemall",
	"p_petexp",
	"p_server",
	"p_skill",
	"p_skill_play_time",
	"p_skillatdemoniclancer",
	"p_skillatfighter",
	"p_skillatpriest",
	"p_skillatswordman",
	"p_skillcreature",
	"p_skillfighter",
	"p_skillgunner",
	"p_skillmage",
	"p_skillpriest",
	"p_skillswordman",
	"p_skin",
	"p_talkset",
	"p_taskinfo",
	"p_taskset",
	"p_transfer",
	"p_word",
	"cnf_characterscoreconfig",
	"cnf_upgradescoreconfig",
	// 数据表
	"t_account",
	"t_agent",
	"t_agent_account",
	"t_agent_charge",
	"t_auction",
	"t_channel",
	"t_charge",
	"t_identity",
	"t_invite",
	"t_notice",
	"t_offline",
	"t_party",
	"t_paydata",
	"t_payinfo",
	"t_payorder",
	"t_rank",
	"t_rankview",
	"t_recharge",
	"t_role",
	"t_server",
	"t_yaoqing",
	"pay_gateway",
}

// login库需要迁移的表
var loginTables = []string{
	"sys_agent",
	"sys_user",
	"t_account",
	"t_channel",
	"t_gm_log",
	"t_invite",
	"t_order",
	"t_server",
}

func main() {
	fmt.Println("============================================================")
	fmt.Println("MySQL 到 SQLite 完整数据迁移工具")
	fmt.Println("============================================================")
	fmt.Println()

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

	// 统计总迁移量
	totalRecords := 0

	// 迁移game库
	fmt.Println("============================================================")
	fmt.Println("迁移 game 库")
	fmt.Println("============================================================")
	gameRecords := migrateDatabase("game", gameTables, sqliteDB)
	totalRecords += gameRecords

	// 迁移login库
	fmt.Println()
	fmt.Println("============================================================")
	fmt.Println("迁移 login 库")
	fmt.Println("============================================================")
	loginRecords := migrateDatabase("login", loginTables, sqliteDB)
	totalRecords += loginRecords

	// 输出统计
	fmt.Println()
	fmt.Println("============================================================")
	fmt.Println("迁移完成!")
	fmt.Println("============================================================")
	fmt.Printf("总迁移记录数: %d\n", totalRecords)
	fmt.Println("============================================================")
}

func migrateDatabase(dbName string, tables []string, sqliteDB *sql.DB) int {
	totalRecords := 0

	// 连接MySQL
	mysqlDSN := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8mb4&parseTime=True&loc=Local",
		mysqlUser, mysqlPassword, mysqlHost, mysqlPort, dbName)
	mysqlDB, err := sql.Open("mysql", mysqlDSN)
	if err != nil {
		fmt.Printf("✗ %s库连接失败: %v\n", dbName, err)
		return 0
	}
	defer mysqlDB.Close()

	if err := mysqlDB.Ping(); err != nil {
		fmt.Printf("✗ %s库连接失败: %v\n", dbName, err)
		return 0
	}

	for _, tableName := range tables {
		count := migrateTable(mysqlDB, sqliteDB, dbName, tableName)
		if count >= 0 {
			totalRecords += count
		}
	}

	return totalRecords
}

func migrateTable(mysqlDB, sqliteDB *sql.DB, dbName, tableName string) int {
	// 为login库的表添加前缀
	sqliteTableName := tableName
	if dbName == "login" {
		sqliteTableName = "login_" + tableName
	}

	fmt.Printf("迁移 %s.%s -> %s...", dbName, tableName, sqliteTableName)

	// 检查表是否存在
	var exists string
	err := mysqlDB.QueryRow(fmt.Sprintf("SHOW TABLES LIKE '%s'", tableName)).Scan(&exists)
	if err != nil {
		fmt.Printf("  跳过 (表不存在)\n")
		return 0
	}

	// 获取表结构
	columns, err := getTableColumns(mysqlDB, tableName)
	if err != nil {
		fmt.Printf("  ✗ 获取表结构失败: %v\n", err)
		return 0
	}

	if len(columns) == 0 {
		fmt.Printf("  跳过 (无列)\n")
		return 0
	}

	// 创建SQLite表
	if err := createSQLiteTable(sqliteDB, sqliteTableName, columns); err != nil {
		fmt.Printf("  ✗ 创建表失败: %v\n", err)
		return 0
	}

	// 查询MySQL数据
	query := fmt.Sprintf("SELECT * FROM %s", tableName)
	rows, err := mysqlDB.Query(query)
	if err != nil {
		fmt.Printf("  ✗ 查询数据失败: %v\n", err)
		return 0
	}
	defer rows.Close()

	// 准备插入语句
	insertSQL := buildInsertSQL(sqliteTableName, columns)
	stmt, err := sqliteDB.Prepare(insertSQL)
	if err != nil {
		fmt.Printf("  ✗ 准备插入语句失败: %v\n", err)
		rows.Close()
		return 0
	}
	defer stmt.Close()

	// 迁移数据
	count := 0
	for rows.Next() {
		values := make([]interface{}, len(columns))
		valuePtrs := make([]interface{}, len(columns))
		for i := range columns {
			valuePtrs[i] = &values[i]
		}

		if err := rows.Scan(valuePtrs...); err != nil {
			fmt.Printf("  ✗ 扫描数据失败: %v\n", err)
			continue
		}

		// 转换数据类型
		convertedValues := convertValues(values, columns)

		if _, err := stmt.Exec(convertedValues...); err != nil {
			fmt.Printf("  ✗ 插入数据失败: %v\n", err)
			continue
		}

		count++
	}

	fmt.Printf("  ✓ %d 条\n", count)
	return count
}

func getTableColumns(db *sql.DB, tableName string) ([]ColumnInfo, error) {
	query := `
		SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_DEFAULT, COLUMN_KEY
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?
		ORDER BY ORDINAL_POSITION
	`

	rows, err := db.Query(query, tableName)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var columns []ColumnInfo
	for rows.Next() {
		var col ColumnInfo
		var nullable, key sql.NullString
		if err := rows.Scan(&col.Name, &col.Type, &nullable, &col.Default, &key); err != nil {
			continue
		}
		col.Nullable = nullable.String == "YES"
		col.PrimaryKey = key.String == "PRI"
		columns = append(columns, col)
	}

	return columns, nil
}

type ColumnInfo struct {
	Name       string
	Type       string
	Nullable   bool
	Default    sql.NullString
	PrimaryKey bool
}

func createSQLiteTable(db *sql.DB, tableName string, columns []ColumnInfo) error {
	// 检查表是否已存在
	var exists int
	db.QueryRow(`SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name=?`, tableName).Scan(&exists)
	if exists > 0 {
		// 如果表已存在且没有主键，先删除表以避免重复数据
		hasPrimaryKey := false
		for _, col := range columns {
			if col.PrimaryKey {
				hasPrimaryKey = true
				break
			}
		}
		if !hasPrimaryKey {
			_, _ = db.Exec(fmt.Sprintf(`DROP TABLE IF EXISTS "%s"`, tableName))
		} else {
			return nil
		}
	}

	// 构建CREATE TABLE语句
	var colDefs []string
	for _, col := range columns {
		colDef := fmt.Sprintf(`"%s" %s`, col.Name, mysqlTypeToSQLite(col.Type))
		if !col.Nullable {
			colDef += " NOT NULL"
		}
		if col.PrimaryKey {
			colDef += " PRIMARY KEY"
		}
		colDefs = append(colDefs, colDef)
	}

	createSQL := fmt.Sprintf(`CREATE TABLE "%s" (%s)`, tableName, strings.Join(colDefs, ", "))
	_, err := db.Exec(createSQL)
	return err
}

func mysqlTypeToSQLite(mysqlType string) string {
	mysqlType = strings.ToUpper(mysqlType)
	switch {
	case strings.HasPrefix(mysqlType, "TINYINT"):
		return "INTEGER"
	case strings.HasPrefix(mysqlType, "SMALLINT"):
		return "INTEGER"
	case strings.HasPrefix(mysqlType, "INT"):
		return "INTEGER"
	case strings.HasPrefix(mysqlType, "BIGINT"):
		return "INTEGER"
	case strings.HasPrefix(mysqlType, "FLOAT"):
		return "REAL"
	case strings.HasPrefix(mysqlType, "DOUBLE"):
		return "REAL"
	case strings.HasPrefix(mysqlType, "DECIMAL"):
		return "REAL"
	case strings.HasPrefix(mysqlType, "CHAR"), strings.HasPrefix(mysqlType, "VARCHAR"):
		return "TEXT"
	case strings.HasPrefix(mysqlType, "TEXT"):
		return "TEXT"
	case strings.HasPrefix(mysqlType, "DATE"):
		return "INTEGER"
	case strings.HasPrefix(mysqlType, "DATETIME"), strings.HasPrefix(mysqlType, "TIMESTAMP"):
		return "INTEGER"
	case strings.HasPrefix(mysqlType, "JSON"):
		return "TEXT"
	default:
		return "TEXT"
	}
}

func buildInsertSQL(tableName string, columns []ColumnInfo) string {
	placeholders := make([]string, len(columns))
	for i := range placeholders {
		placeholders[i] = "?"
	}

	colNames := make([]string, len(columns))
	for i, col := range columns {
		colNames[i] = fmt.Sprintf(`"%s"`, col.Name)
	}

	return fmt.Sprintf(`INSERT OR REPLACE INTO "%s" (%s) VALUES (%s)`,
		tableName,
		strings.Join(colNames, ", "),
		strings.Join(placeholders, ", "))
}

func convertValues(values []interface{}, columns []ColumnInfo) []interface{} {
	result := make([]interface{}, len(values))

	for i, val := range values {
		if val == nil {
			result[i] = nil
			continue
		}

		colType := strings.ToUpper(columns[i].Type)

		switch v := val.(type) {
		case []byte:
			// JSON字段转换为字符串
			if strings.Contains(colType, "JSON") {
				result[i] = string(v)
			} else {
				result[i] = string(v)
			}
		default:
			result[i] = val
		}
	}

	return result
}
