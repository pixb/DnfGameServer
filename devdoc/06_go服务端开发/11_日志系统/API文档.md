# 日志系统 - API文档

## 1. 概述
本文档描述了日志系统的API接口，包括核心类、方法和使用示例。日志系统提供了简洁易用的API，用于记录系统运行过程中的各种事件和信息。

## 2. 核心API

### 2.1 日志记录器API

#### 2.1.1 创建日志记录器
```go
// NewLogger 创建一个新的日志记录器
// name: 日志记录器名称
// 返回值: 日志记录器实例
func NewLogger(name string) *Logger
```

**使用示例**：
```go
logger := logs.NewLogger("game-server")
```

#### 2.1.2 设置日志级别
```go
// WithLevel 设置日志级别
// level: 日志级别
// 返回值: 日志记录器实例（链式调用）
func (l *Logger) WithLevel(level Level) *Logger
```

**使用示例**：
```go
logger.WithLevel(logs.DebugLevel)
```

#### 2.1.3 添加处理器
```go
// WithHandler 添加日志处理器
// handler: 日志处理器
// 返回值: 日志记录器实例（链式调用）
func (l *Logger) WithHandler(handler Handler) *Logger
```

**使用示例**：
```go
fileHandler, err := logs.NewFileHandler("./logs/app.log")
if err == nil {
    logger.WithHandler(fileHandler)
}
```

#### 2.1.4 设置格式化器
```go
// WithFormatter 设置日志格式化器
// formatter: 日志格式化器
// 返回值: 日志记录器实例（链式调用）
func (l *Logger) WithFormatter(formatter Formatter) *Logger
```

**使用示例**：
```go
logger.WithFormatter(logs.NewJSONFormatter())
```

#### 2.1.5 添加字段
```go
// WithField 添加单个字段
// key: 字段键
// value: 字段值
// 返回值: 日志记录器实例（链式调用）
func (l *Logger) WithField(key string, value interface{}) *Logger

// WithFields 添加多个字段
// fields: 字段映射
// 返回值: 日志记录器实例（链式调用）
func (l *Logger) WithFields(fields Fields) *Logger
```

**使用示例**：
```go
logger.WithField("player_id", 12345).WithField("action", "login")

// 或批量添加
logger.WithFields(logs.Fields{
    "player_id": 12345,
    "action":    "login",
    "ip":        "192.168.1.1",
})
```

### 2.2 日志记录方法

#### 2.2.1 调试级别日志
```go
// Debug 记录调试级别日志
// args: 日志内容
func (l *Logger) Debug(args ...interface{})

// Debugf 记录格式化的调试级别日志
// format: 格式字符串
// args: 格式参数
func (l *Logger) Debugf(format string, args ...interface{})

// Debugln 记录带换行的调试级别日志
// args: 日志内容
func (l *Logger) Debugln(args ...interface{})
```

**使用示例**：
```go
logger.Debug("Debug message")
logger.Debugf("Debug message with %s", "parameter")
logger.Debugln("Debug message with newline")
```

#### 2.2.2 信息级别日志
```go
// Info 记录信息级别日志
// args: 日志内容
func (l *Logger) Info(args ...interface{})

// Infof 记录格式化的信息级别日志
// format: 格式字符串
// args: 格式参数
func (l *Logger) Infof(format string, args ...interface{})

// Infoln 记录带换行的信息级别日志
// args: 日志内容
func (l *Logger) Infoln(args ...interface{})
```

**使用示例**：
```go
logger.Info("Info message")
logger.Infof("Info message with %s", "parameter")
logger.Infoln("Info message with newline")
```

#### 2.2.3 警告级别日志
```go
// Warn 记录警告级别日志
// args: 日志内容
func (l *Logger) Warn(args ...interface{})

// Warnf 记录格式化的警告级别日志
// format: 格式字符串
// args: 格式参数
func (l *Logger) Warnf(format string, args ...interface{})

// Warnln 记录带换行的警告级别日志
// args: 日志内容
func (l *Logger) Warnln(args ...interface{})
```

**使用示例**：
```go
logger.Warn("Warn message")
logger.Warnf("Warn message with %s", "parameter")
logger.Warnln("Warn message with newline")
```

#### 2.2.4 错误级别日志
```go
// Error 记录错误级别日志
// args: 日志内容
func (l *Logger) Error(args ...interface{})

// Errorf 记录格式化的错误级别日志
// format: 格式字符串
// args: 格式参数
func (l *Logger) Errorf(format string, args ...interface{})

// Errorln 记录带换行的错误级别日志
// args: 日志内容
func (l *Logger) Errorln(args ...interface{})

// ErrorWith 记录带错误对象的错误级别日志
// err: 错误对象
// args: 日志内容
func (l *Logger) ErrorWith(err error, args ...interface{})
```

**使用示例**：
```go
logger.Error("Error message")
logger.Errorf("Error message with %s", "parameter")
logger.Errorln("Error message with newline")

// 带错误对象
if err != nil {
    logger.ErrorWith(err, "Failed to process request")
}
```

#### 2.2.5 致命级别日志
```go
// Fatal 记录致命级别日志并退出程序
// args: 日志内容
func (l *Logger) Fatal(args ...interface{})

// Fatalf 记录格式化的致命级别日志并退出程序
// format: 格式字符串
// args: 格式参数
func (l *Logger) Fatalf(format string, args ...interface{})

// Fatalln 记录带换行的致命级别日志并退出程序
// args: 日志内容
func (l *Logger) Fatalln(args ...interface{})
```

**使用示例**：
```go
logger.Fatal("Fatal message")
logger.Fatalf("Fatal message with %s", "parameter")
logger.Fatalln("Fatal message with newline")
```

#### 2.2.6 恐慌级别日志
```go
// Panic 记录恐慌级别日志并触发恐慌
// args: 日志内容
func (l *Logger) Panic(args ...interface{})

// Panicf 记录格式化的恐慌级别日志并触发恐慌
// format: 格式字符串
// args: 格式参数
func (l *Logger) Panicf(format string, args ...interface{})

// Panicln 记录带换行的恐慌级别日志并触发恐慌
// args: 日志内容
func (l *Logger) Panicln(args ...interface{})
```

**使用示例**：
```go
logger.Panic("Panic message")
logger.Panicf("Panic message with %s", "parameter")
logger.Panicln("Panic message with newline")
```

### 2.3 全局日志API

#### 2.3.1 全局日志记录方法
```go
// Debug 记录调试级别全局日志
// args: 日志内容
func Debug(args ...interface{})

// Info 记录信息级别全局日志
// args: 日志内容
func Info(args ...interface{})

// Warn 记录警告级别全局日志
// args: 日志内容
func Warn(args ...interface{})

// Error 记录错误级别全局日志
// args: 日志内容
func Error(args ...interface{})

// Fatal 记录致命级别全局日志并退出程序
// args: 日志内容
func Fatal(args ...interface{})

// Panic 记录恐慌级别全局日志并触发恐慌
// args: 日志内容
func Panic(args ...interface{})
```

**使用示例**：
```go
logs.Info("Global info message")
logs.Error("Global error message")
```

#### 2.3.2 获取日志记录器
```go
// GetLogger 获取指定名称的日志记录器
// name: 日志记录器名称
// 返回值: 日志记录器实例
func GetLogger(name string) *Logger
```

**使用示例**：
```go
logger := logs.GetLogger("game-server")
logger.Info("Message from named logger")
```

#### 2.3.3 设置全局日志级别
```go
// SetLevel 设置全局日志级别
// level: 日志级别
func SetLevel(level Level)
```

**使用示例**：
```go
logs.SetLevel(logs.DebugLevel)
```

#### 2.3.4 添加全局处理器
```go
// AddHandler 添加全局日志处理器
// handler: 日志处理器
func AddHandler(handler Handler)
```

**使用示例**：
```go
fileHandler, err := logs.NewFileHandler("./logs/app.log")
if err == nil {
    logs.AddHandler(fileHandler)
}
```

### 2.4 处理器API

#### 2.4.1 控制台处理器
```go
// NewConsoleHandler 创建控制台处理器
// 返回值: 控制台处理器实例
func NewConsoleHandler() Handler
```

**使用示例**：
```go
consoleHandler := logs.NewConsoleHandler()
logger.WithHandler(consoleHandler)
```

#### 2.4.2 文件处理器
```go
// NewFileHandler 创建文件处理器
// filePath: 文件路径
// 返回值: 文件处理器实例和错误
func NewFileHandler(filePath string) (Handler, error)
```

**使用示例**：
```go
fileHandler, err := logs.NewFileHandler("./logs/app.log")
if err == nil {
    logger.WithHandler(fileHandler)
}
```

#### 2.4.3 异步处理器
```go
// NewAsyncHandler 创建异步处理器
// handler: 目标处理器
// bufferSize: 缓冲区大小
// 返回值: 异步处理器实例
func NewAsyncHandler(handler Handler, bufferSize int) Handler
```

**使用示例**：
```go
fileHandler, err := logs.NewFileHandler("./logs/app.log")
if err == nil {
    asyncHandler := logs.NewAsyncHandler(fileHandler, 1024)
    logger.WithHandler(asyncHandler)
}
```

### 2.5 格式化器API

#### 2.5.1 文本格式化器
```go
// NewTextFormatter 创建文本格式化器
// 返回值: 文本格式化器实例
func NewTextFormatter() Formatter
```

**使用示例**：
```go
textFormatter := logs.NewTextFormatter()
logger.WithFormatter(textFormatter)
```

#### 2.5.2 JSON格式化器
```go
// NewJSONFormatter 创建JSON格式化器
// 返回值: JSON格式化器实例
func NewJSONFormatter() Formatter
```

**使用示例**：
```go
jsonFormatter := logs.NewJSONFormatter()
logger.WithFormatter(jsonFormatter)
```

### 2.6 配置API

#### 2.6.1 加载配置
```go
// LoadConfig 从文件加载配置
// filePath: 配置文件路径
// 返回值: 配置对象和错误
func LoadConfig(filePath string) (*Config, error)
```

**使用示例**：
```go
config, err := logs.LoadConfig("./config/log.json")
if err == nil {
    // 使用配置
}
```

#### 2.6.2 应用配置
```go
// ApplyConfig 应用配置到全局日志器
// config: 配置对象
// 返回值: 错误
func ApplyConfig(config *Config) error
```

**使用示例**：
```go
config, err := logs.LoadConfig("./config/log.json")
if err == nil {
    logs.ApplyConfig(config)
}
```

## 3. 数据结构

### 3.1 日志级别
```go
type Level int

const (
    DebugLevel Level = iota
    InfoLevel
    WarnLevel
    ErrorLevel
    FatalLevel
    PanicLevel
)
```

### 3.2 字段映射
```go
type Fields map[string]interface{}
```

### 3.3 配置结构
```go
type Config struct {
    Level     string            `json:"level" yaml:"level"`
    Handlers  []HandlerConfig   `json:"handlers" yaml:"handlers"`
    Formatter string            `json:"formatter" yaml:"formatter"`
    Fields    map[string]string `json:"fields" yaml:"fields"`
}

type HandlerConfig struct {
    Type   string            `json:"type" yaml:"type"`
    Config map[string]string `json:"config" yaml:"config"`
}
```

## 4. 错误处理

### 4.1 处理器错误
- **处理方式**：处理器错误不会影响业务代码，会被日志系统内部捕获并输出到标准错误
- **示例**：
  ```go
  // 当处理器处理日志失败时，错误会被捕获并输出到标准错误
  // 业务代码继续执行，不受影响
  logger.Error("This message might fail to log, but business code continues")
  ```

### 4.2 配置错误
- **处理方式**：配置加载和应用过程中的错误会返回给调用者
- **示例**：
  ```go
  config, err := logs.LoadConfig("./config/log.json")
  if err != nil {
      // 处理配置加载错误
      fmt.Printf("Failed to load log config: %v\n", err)
      return
  }
  
  if err := logs.ApplyConfig(config); err != nil {
      // 处理配置应用错误
      fmt.Printf("Failed to apply log config: %v\n", err)
      return
  }
  ```

### 4.3 常见错误类型
| 错误类型 | 描述 | 处理建议 |
|---------|------|--------|
| 文件打开错误 | 无法打开日志文件 | 检查文件路径和权限 |
| 配置解析错误 | 无法解析配置文件 | 检查配置文件格式 |
| 处理器初始化错误 | 处理器初始化失败 | 检查处理器配置 |
| 异步缓冲区满 | 异步处理器缓冲区满 | 增加缓冲区大小或处理速度 |

## 5. 使用示例

### 5.1 基本使用
```go
package main

import (
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/logs"
)

func main() {
    // 使用全局日志器
    logs.Info("Server starting")
    
    // 获取命名日志器
    logger := logs.GetLogger("game-server")
    
    // 设置日志级别
    logger.WithLevel(logs.DebugLevel)
    
    // 添加文件处理器
    fileHandler, err := logs.NewFileHandler("./logs/app.log")
    if err == nil {
        logger.WithHandler(fileHandler)
    }
    
    // 添加JSON格式化器
    logger.WithFormatter(logs.NewJSONFormatter())
    
    // 添加全局字段
    logger.WithField("server", "game-server")
    
    // 记录不同级别的日志
    logger.Debug("Debug message")
    logger.Info("Info message")
    logger.Warn("Warn message")
    logger.Error("Error message")
    
    // 带格式化参数
    logger.Infof("Player %d logged in", 12345)
    
    // 带错误对象
    if err != nil {
        logger.ErrorWith(err, "Failed to process request")
    }
    
    logs.Info("Server started successfully")
}
```

### 5.2 配置文件使用
```go
package main

import (
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/logs"
)

func main() {
    // 加载配置
    config, err := logs.LoadConfig("./config/log.json")
    if err != nil {
        // 配置加载失败，使用默认配置
        logs.ErrorWith(err, "Failed to load log config, using default")
    } else {
        // 应用配置
        if err := logs.ApplyConfig(config); err != nil {
            logs.ErrorWith(err, "Failed to apply log config, using default")
        }
    }
    
    // 使用全局日志器
    logs.Info("Server starting with config")
    
    // 其他代码...
}
```

### 5.3 异步日志使用
```go
package main

import (
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/logs"
)

func main() {
    // 创建文件处理器
    fileHandler, err := logs.NewFileHandler("./logs/app.log")
    if err != nil {
        logs.ErrorWith(err, "Failed to create file handler")
        return
    }
    
    // 创建异步处理器
    asyncHandler := logs.NewAsyncHandler(fileHandler, 1024)
    
    // 添加到全局日志器
    logs.AddHandler(asyncHandler)
    
    // 记录大量日志
    for i := 0; i < 1000; i++ {
        logs.Info("Log message ", i)
    }
    
    // 程序退出前，确保异步处理器完成
    // 注意：在实际应用中，应该在程序退出前优雅关闭日志系统
    // 这里简化处理，仅作示例
    
    logs.Info("All logs sent to async handler")
}
```

### 5.4 结构化日志使用
```go
package main

import (
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/logs"
)

func main() {
    // 创建日志器并使用JSON格式化器
    logger := logs.NewLogger("game-server").WithFormatter(logs.NewJSONFormatter())
    
    // 记录结构化日志
    logger.WithField("player_id", 12345).
           WithField("action", "login").
           WithField("ip", "192.168.1.1").
           WithField("timestamp", 1620000000).
           Info("Player logged in")
    
    // 输出示例:
    // {"time":"2021-05-03T12:00:00Z","level":"INFO","logger":"game-server","message":"Player logged in","player_id":12345,"action":"login","ip":"192.168.1.1","timestamp":1620000000}
}
```

## 6. 性能考虑

### 6.1 日志级别
- **建议**：在生产环境中使用较高的日志级别（如Info或Warn），减少日志数量
- **原因**：过多的日志会影响系统性能和存储

### 6.2 异步处理
- **建议**：对于I/O密集型的处理器（如文件处理器），使用异步处理器
- **原因**：异步处理可以减少对业务代码的阻塞

### 6.3 字段使用
- **建议**：合理使用字段，避免过多或过大的字段
- **原因**：过多或过大的字段会增加日志大小和处理时间

### 6.4 格式化方式
- **建议**：对于高频日志，使用固定格式的日志，避免复杂的格式化
- **原因**：复杂的格式化会增加CPU开销

### 6.5 处理器选择
- **建议**：根据场景选择合适的处理器
  - 开发环境：控制台处理器
  - 测试环境：文件处理器
  - 生产环境：异步处理器 + 文件处理器 + ELK Stack

## 7. 最佳实践

### 7.1 日志记录原则
- **重要性**：只记录重要的信息，避免过多的调试日志
- **清晰性**：日志消息应该清晰明了，包含必要的上下文信息
- **一致性**：使用一致的日志格式和命名规范
- **安全性**：不要记录敏感信息（如密码、令牌）
- **结构化**：对于复杂的日志，使用结构化字段

### 7.2 错误处理
- **错误记录**：当发生错误时，应该记录错误信息
- **错误传递**：对于需要上层处理的错误，应该返回错误，而不是仅记录
- **错误上下文**：记录错误时，应该包含足够的上下文信息

### 7.3 性能优化
- **异步处理**：对于I/O密集型的日志操作，使用异步处理
- **批量写入**：对于文件日志，考虑批量写入
- **级别过滤**：在生产环境中，使用较高的日志级别
- **缓存**：对于频繁使用的日志配置，考虑缓存

### 7.4 配置管理
- **环境分离**：为不同的环境（开发、测试、生产）使用不同的配置
- **动态配置**：支持运行时调整日志配置
- **配置验证**：加载配置时验证配置的有效性

### 7.5 监控与维护
- **日志监控**：监控日志中的异常信息
- **日志清理**：定期清理过期日志
- **日志备份**：定期备份重要日志
- **日志分析**：定期分析日志，发现系统问题

## 8. 版本兼容性

### 8.1 API变更
| 版本 | 变更内容 | 兼容性 |
|------|---------|--------|
| v1.0 | 初始版本 | 完全兼容 |
| v1.1 | 增加异步处理器 | 向后兼容 |
| v1.2 | 增加JSON格式化器 | 向后兼容 |
| v1.3 | 增加配置文件支持 | 向后兼容 |
| v2.0 | 重构API，简化接口 | 不兼容 |

### 8.2 迁移指南
- **从v1.x到v2.0**：
  - API接口更简洁，使用方式类似
  - 配置文件格式保持不变
  - 处理器和格式化器接口保持不变

## 9. 总结

本文档描述了日志系统的API接口，包括核心类、方法和使用示例。日志系统提供了简洁易用的API，用于记录系统运行过程中的各种事件和信息。通过合理使用日志系统，可以帮助开发者更好地监控系统运行状态、排查问题、分析数据。

在使用日志系统时，应该根据实际场景选择合适的配置和处理器，避免过多的日志对系统性能造成影响。同时，应该遵循日志记录的最佳实践，确保日志的清晰性、一致性和安全性。