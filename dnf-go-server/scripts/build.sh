#!/bin/bash

# DNF Go Server 构建脚本

set -e

echo "========================================"
echo "Building DNF Go Server..."
echo "========================================"

# 获取脚本所在目录
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
cd "$PROJECT_DIR"

# 版本信息
VERSION=$(git describe --tags --always --dirty 2>/dev/null || echo "dev")
COMMIT=$(git rev-parse --short HEAD 2>/dev/null || echo "unknown")
BUILD_TIME=$(date -u '+%Y-%m-%d_%H:%M:%S')

echo "Version: ${VERSION}"
echo "Commit: ${COMMIT}"
echo "Build Time: ${BUILD_TIME}"
echo "Go Version: $(go version)"
echo ""

# 构建参数
LDFLAGS="-X github.com/dnf-go-server/cmd/server/cmd.Version=${VERSION} \
         -X github.com/dnf-go-server/cmd/server/cmd.Commit=${COMMIT} \
         -X github.com/dnf-go-server/cmd/server/cmd.BuildTime=${BUILD_TIME}"

# 创建输出目录
mkdir -p bin

# 构建
echo "Building..."
go build -ldflags "${LDFLAGS}" -o bin/dnf-server ./cmd/server

# 检查构建结果
if [ $? -eq 0 ]; then
    echo ""
    echo "========================================"
    echo "Build Success!"
    echo "Executable: $PROJECT_DIR/bin/dnf-server"
    echo "========================================"
    echo ""
    echo "Usage:"
    echo "  ./bin/dnf-server serve"
    echo "  ./bin/dnf-server serve --config=configs/config.yaml"
    echo "  ./bin/dnf-server migrate"
    echo "  ./bin/dnf-server version"
    echo ""
    # 显示版本
    ./bin/dnf-server version
else
    echo ""
    echo "========================================"
    echo "Build Failed"
    echo "========================================"
    exit 1
fi
