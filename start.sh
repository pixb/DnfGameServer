#!/bin/bash

# DnfGameServer 启动脚本

# 设置 Java 8 环境
export JAVA_HOME=/Users/pix/Library/Java/JavaVirtualMachines/corretto-1.8.0_462/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

# 项目目录
PROJECT_DIR="/Users/pix/dev/code/java/DnfGameServer"
cd "$PROJECT_DIR"

# JAR 文件路径
JAR_FILE="$PROJECT_DIR/target/DnfGameServer-0.0.1-SNAPSHOT.jar"

# 检查 JAR 文件是否存在
if [ ! -f "$JAR_FILE" ]; then
    echo "错误: JAR 文件不存在，请先构建项目"
    echo "运行: export JAVA_HOME=/Users/pix/Library/Java/JavaVirtualMachines/corretto-1.8.0_462/Contents/Home && ./mvnw clean package -DskipTests"
    exit 1
fi

# JVM 参数
JVM_OPTS="-Xms512m \
           -Xmx2g \
           -XX:+UseG1GC \
           -XX:MaxGCPauseMillis=200"

# 启动服务器
echo "========================================"
echo "正在启动 DnfGameServer..."
echo "Java 版本: $(java -version 2>&1 | head -n 1)"
echo "JAR 文件: $JAR_FILE"
echo "========================================"

java $JVM_OPTS -jar "$JAR_FILE"
