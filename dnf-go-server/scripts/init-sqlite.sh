#!/bin/bash
# MySQL to SQLite 数据导入脚本
# 用于从MariaDB容器导出数据并导入到SQLite开发数据库

set -e

# 配置
MYSQL_HOST="localhost"
MYSQL_PORT="3306"
MYSQL_USER="root"
MYSQL_PASS="root"
MYSQL_DB="dnf_game"
SQLITE_DB="./data/dnf_dev.db"

echo "=== DNF Game Server Data Import Tool ==="
echo ""

# 检查依赖
if ! command -v sqlite3 &> /dev/null; then
    echo "Error: sqlite3 is not installed. Please install it first."
    exit 1
fi

# 创建数据目录
mkdir -p ./data

# 初始化SQLite数据库
echo "1. Initializing SQLite database..."
sqlite3 "$SQLITE_DB" < ./store/migration/sqlite/schema.sql

# 导出MySQL数据到CSV（可选）
echo "2. Checking MariaDB connection..."
if docker exec dnf-mariadb mysql -u"$MYSQL_USER" -p"$MYSQL_PASS" -e "SELECT 1;" "$MYSQL_DB" 2>/dev/null; then
    echo "   MariaDB connection successful"
    
    # 导出表列表
    TABLES=$(docker exec dnf-mariadb mysql -u"$MYSQL_USER" -p"$MYSQL_PASS" -N -e "SHOW TABLES;" "$MYSQL_DB" 2>/dev/null || echo "")
    
    if [ -n "$TABLES" ]; then
        echo "   Found tables: $TABLES"
        echo ""
        echo "   Note: Automatic data migration from MySQL to SQLite requires table-by-table handling."
        echo "   Please use the Go migration tool for complete data transfer."
    else
        echo "   No tables found in MariaDB. Starting with empty SQLite database."
    fi
else
    echo "   Warning: Cannot connect to MariaDB. Starting with empty SQLite database."
fi

echo ""
echo "3. SQLite database initialized at: $SQLITE_DB"
echo ""
echo "=== Usage ==="
echo "Development (SQLite): go run ./cmd/server/main.go serve --config config.dev.yaml"
echo "Production (MySQL):   go run ./cmd/server/main.go serve --config config.prod.yaml"
echo ""
