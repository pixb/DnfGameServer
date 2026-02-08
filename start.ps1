# DnfGameServer Startup Script

# Set Java 8 environment
$env:JAVA_HOME = "C:\Users\pix\.sdkman\candidates\java\8.0.462-tem"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Set console encoding to UTF-8
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
$OutputEncoding = [System.Text.Encoding]::UTF8

# Project directory
$PROJECT_DIR = "c:\Users\pix\dev\code\java\DnfGameServer"
Set-Location $PROJECT_DIR

# Start server
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Starting DnfGameServer..." -ForegroundColor Green
java -version
Write-Host "Project directory: $PROJECT_DIR" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

# Run Spring Boot application
.\mvnw.cmd spring-boot:run
