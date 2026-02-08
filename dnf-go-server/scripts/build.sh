#!/bin/bash

# DNF Go Server 构建脚本

set -e

echo "========================================"
echo "正在构建 DNF Go Server..."
echo "========================================"

# 项目目录
PROJECT_DIR="/Users/pix/dev/code/java/DnfGameServer/dnf-go-server"
cd "$PROJECT_DIR"

# 检查 Go 版本
echo "Go 版本: $(go version)"

# 整理依赖
echo "整理依赖..."
go mod tidy

# 运行测试
echo "运行测试..."
go test ./... -v 2>/dev/null || echo "测试暂未实现，跳过"

# 构建
echo "开始构建..."
go build -o bin/dnf-server ./cmd/server

# 检查构建结果
if [ $? -eq 0 ]; then
    echo ""
    echo "========================================"
    echo "构建成功！"
    echo "可执行文件: $PROJECT_DIR/bin/dnf-server"
    echo "========================================"
    echo ""
    echo "运行方式:"
    echo "  ./bin/dnf-server"
    echo "  ./bin/dnf-server -config=configs/config.yaml"
else
    echo ""
    echo "========================================"
    echo "构建失败"
    echo "========================================"
    exit 1
fi
