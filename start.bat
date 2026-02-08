@echo off

REM 设置控制台编码为 GBK
chcp 936 >nul

REM DnfGameServer 启动脚本

REM 设置 Java 8 环境
set JAVA_HOME=C:\Users\pix\.sdkman\candidates\java\8.0.462-tem
set PATH=%JAVA_HOME%\bin;%PATH%

REM 项目目录
set PROJECT_DIR=c:\Users\pix\dev\code\java\DnfGameServer
cd /d "%PROJECT_DIR%"

REM JAR 文件路径
set JAR_FILE=%PROJECT_DIR%\target\DnfGameServer-0.0.1-SNAPSHOT.jar

REM 检查 JAR 文件是否存在
if not exist "%JAR_FILE%" (
    echo 错误: JAR 文件不存在，请先构建项目
    echo 运行: mvnw.cmd clean package -DskipTests
    pause
    exit /b 1
)

REM JVM 参数
set JVM_OPTS=-Xms512m -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Dfile.encoding=UTF-8

REM 启动服务器
echo ========================================
echo 正在启动 DnfGameServer...
java -version
echo JAR 文件: %JAR_FILE%
echo ========================================

java %JVM_OPTS% -jar "%JAR_FILE%"

pause
