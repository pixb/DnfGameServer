#!/bin/bash

# MySQL Docker Compose 停止脚本

# 进入目录
cd "$(dirname "$0")"

echo "========================================"
echo "停止 MySQL 数据库容器..."
echo "========================================"

# 停止 Docker Compose
docker-compose down

echo ""
echo "========================================"
echo "MySQL 已停止"
echo "========================================"
echo ""
echo "注意：数据卷已保留，下次启动会恢复数据"
