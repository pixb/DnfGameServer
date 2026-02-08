#!/bin/bash

# DnfGameServer 构建脚本

# 设置 Java 8 环境
export JAVA_HOME=/Users/pix/Library/Java/JavaVirtualMachines/corretto-1.8.0_462/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

# 项目目录
PROJECT_DIR="/Users/pix/dev/code/java/DnfGameServer"
cd "$PROJECT_DIR"

echo "========================================"
echo "正在构建 DnfGameServer..."
echo "Java 版本: $(java -version 2>&1 | head -n 1)"
echo "项目目录: $PROJECT_DIR"
echo "========================================"

# 清理并打包
./mvnw clean package -DskipTests

# 检查构建结果
if [ $? -eq 0 ]; then
    echo ""
    echo "========================================"
    echo "构建成功！"
    echo "JAR 文件: $PROJECT_DIR/target/DnfGameServer-0.0.1-SNAPSHOT.jar"
    echo "========================================"
    echo ""
    echo "现在可以运行: ./start.sh"
else
    echo ""
    echo "========================================"
    echo "构建失败，请检查错误信息"
    echo "========================================"
    exit 1
fi
