#!/bin/bash

# MariaDB Docker Compose 启动脚本

# 进入目录
cd "$(dirname "$0")"

echo "========================================"
echo "启动 MariaDB 数据库容器..."
echo "========================================"

# 启动 Docker Compose
docker-compose up -d

# 等待 MariaDB 启动
echo ""
echo "等待 MariaDB 启动..."
sleep 10

# 检查容器状态
echo ""
echo "========================================"
echo "容器状态："
echo "========================================"
docker-compose ps

# 测试连接
echo ""
echo "========================================"
echo "测试数据库连接..."
echo "========================================"
docker exec -it dnf-mariadb mysql -uroot -p123456 -e "SHOW DATABASES;"

echo ""
echo "========================================"
echo "MariaDB 启动完成！"
echo "========================================"
echo ""
echo "数据库信息："
echo "  主机: 127.0.0.1"
echo "  端口: 3306"
echo "  用户: root"
echo "  密码: 123456"
echo "  数据库: game, login, game_kuafu"
echo ""
echo "现在可以运行: ./start.sh"
