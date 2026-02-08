package logger

import (
	"os"

	"go.uber.org/zap"
	"go.uber.org/zap/zapcore"
)

var (
	defaultLogger *zap.Logger
	sugarLogger   *zap.SugaredLogger
)

// Init 初始化日志系统
func Init(level string, isDevelopment bool) error {
	var config zap.Config

	if isDevelopment {
		config = zap.NewDevelopmentConfig()
		config.EncoderConfig.EncodeLevel = zapcore.CapitalColorLevelEncoder
	} else {
		config = zap.NewProductionConfig()
		config.EncoderConfig.TimeKey = "timestamp"
		config.EncoderConfig.EncodeTime = zapcore.ISO8601TimeEncoder
	}

	// 设置日志级别
	logLevel := zapcore.InfoLevel
	switch level {
	case "debug":
		logLevel = zapcore.DebugLevel
	case "info":
		logLevel = zapcore.InfoLevel
	case "warn":
		logLevel = zapcore.WarnLevel
	case "error":
		logLevel = zapcore.ErrorLevel
	}
	config.Level = zap.NewAtomicLevelAt(logLevel)

	// 输出到控制台
	config.OutputPaths = []string{"stdout"}
	config.ErrorOutputPaths = []string{"stderr"}

	logger, err := config.Build(zap.AddCallerSkip(1))
	if err != nil {
		return err
	}

	defaultLogger = logger
	sugarLogger = logger.Sugar()

	return nil
}

// GetLogger 获取 Logger 实例
func GetLogger() *zap.Logger {
	if defaultLogger == nil {
		// 兜底：返回一个默认的logger
		defaultLogger = zap.NewExample()
	}
	return defaultLogger
}

// GetSugar 获取 SugaredLogger 实例
func GetSugar() *zap.SugaredLogger {
	if sugarLogger == nil {
		sugarLogger = GetLogger().Sugar()
	}
	return sugarLogger
}

// Debug 打印 Debug 级别日志
func Debug(msg string, fields ...zap.Field) {
	GetLogger().Debug(msg, fields...)
}

// Info 打印 Info 级别日志
func Info(msg string, fields ...zap.Field) {
	GetLogger().Info(msg, fields...)
}

// Warn 打印 Warn 级别日志
func Warn(msg string, fields ...zap.Field) {
	GetLogger().Warn(msg, fields...)
}

// Error 打印 Error 级别日志
func Error(msg string, fields ...zap.Field) {
	GetLogger().Error(msg, fields...)
}

// Fatal 打印 Fatal 级别日志并退出
func Fatal(msg string, fields ...zap.Field) {
	GetLogger().Fatal(msg, fields...)
	os.Exit(1)
}

// Debugf 打印格式化的 Debug 级别日志
func Debugf(template string, args ...interface{}) {
	GetSugar().Debugf(template, args...)
}

// Infof 打印格式化的 Info 级别日志
func Infof(template string, args ...interface{}) {
	GetSugar().Infof(template, args...)
}

// Warnf 打印格式化的 Warn 级别日志
func Warnf(template string, args ...interface{}) {
	GetSugar().Warnf(template, args...)
}

// Errorf 打印格式化的 Error 级别日志
func Errorf(template string, args ...interface{}) {
	GetSugar().Errorf(template, args...)
}

// Fatalf 打印格式化的 Fatal 级别日志并退出
func Fatalf(template string, args ...interface{}) {
	GetSugar().Fatalf(template, args...)
}

// Sync 刷新日志缓冲区
func Sync() {
	if defaultLogger != nil {
		_ = defaultLogger.Sync()
	}
}

// String 辅助函数：创建 string 类型的 Field
func String(key, val string) zap.Field {
	return zap.String(key, val)
}

// Int 辅助函数：创建 int 类型的 Field
func Int(key string, val int) zap.Field {
	return zap.Int(key, val)
}

// Int32 辅助函数：创建 int32 类型的 Field
func Int32(key string, val int32) zap.Field {
	return zap.Int32(key, val)
}

// Int64 辅助函数：创建 int64 类型的 Field
func Int64(key string, val int64) zap.Field {
	return zap.Int64(key, val)
}

// Uint64 辅助函数：创建 uint64 类型的 Field
func Uint64(key string, val uint64) zap.Field {
	return zap.Uint64(key, val)
}

// Uint16 辅助函数：创建 uint16 类型的 Field
func Uint16(key string, val uint16) zap.Field {
	return zap.Uint16(key, val)
}

// Uint32 辅助函数：创建 uint32 类型的 Field
func Uint32(key string, val uint32) zap.Field {
	return zap.Uint32(key, val)
}

// Bool 辅助函数：创建 bool 类型的 Field
func Bool(key string, val bool) zap.Field {
	return zap.Bool(key, val)
}

// ErrorField 辅助函数：创建 error 类型的 Field
func ErrorField(err error) zap.Field {
	return zap.Error(err)
}

// Any 辅助函数：创建任意类型的 Field
func Any(key string, val interface{}) zap.Field {
	return zap.Any(key, val)
}
