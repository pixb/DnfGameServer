# 日志系统 - Go实现方案

## 1. 设计理念
基于Go语言的并发特性和标准库，实现一个高性能、可靠、灵活的日志系统。该系统应支持多种日志级别、多种输出方式、结构化日志、异步输出等功能，同时提供简洁易用的API。

## 2. 核心数据结构

### 2.1 日志级别
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

var levelNames = map[Level]string{
    DebugLevel: "DEBUG",
    InfoLevel:  "INFO",
    WarnLevel:  "WARN",
    ErrorLevel: "ERROR",
    FatalLevel: "FATAL",
    PanicLevel: "PANIC",
}

func (l Level) String() string {
    if name, ok := levelNames[l]; ok {
        return name
    }
    return "UNKNOWN"
}

func ParseLevel(level string) (Level, error) {
    for l, name := range levelNames {
        if strings.EqualFold(name, level) {
            return l, nil
        }
    }
    return InfoLevel, fmt.Errorf("invalid log level: %s", level)
}
```

### 2.2 日志记录器
```go
type Logger struct {
    name      string
    level     Level
    handlers  []Handler
    formatter Formatter
    ctx       context.Context
    fields    Fields
    mu        sync.RWMutex
}

type Fields map[string]interface{}

func NewLogger(name string) *Logger {
    return &Logger{
        name:      name,
        level:     InfoLevel,
        handlers:  []Handler{NewConsoleHandler()},
        formatter: NewTextFormatter(),
        ctx:       context.Background(),
        fields:    Fields{},
    }
}

func (l *Logger) WithLevel(level Level) *Logger {
    l.mu.Lock()
    defer l.mu.Unlock()
    l.level = level
    return l
}

func (l *Logger) WithHandler(handler Handler) *Logger {
    l.mu.Lock()
    defer l.mu.Unlock()
    l.handlers = append(l.handlers, handler)
    return l
}

func (l *Logger) WithFormatter(formatter Formatter) *Logger {
    l.mu.Lock()
    defer l.mu.Unlock()
    l.formatter = formatter
    return l
}

func (l *Logger) WithContext(ctx context.Context) *Logger {
    l.mu.Lock()
    defer l.mu.Unlock()
    l.ctx = ctx
    return l
}

func (l *Logger) WithField(key string, value interface{}) *Logger {
    l.mu.Lock()
    defer l.mu.Unlock()
    if l.fields == nil {
        l.fields = Fields{}
    }
    l.fields[key] = value
    return l
}

func (l *Logger) WithFields(fields Fields) *Logger {
    l.mu.Lock()
    defer l.mu.Unlock()
    if l.fields == nil {
        l.fields = Fields{}
    }
    for k, v := range fields {
        l.fields[k] = v
    }
    return l
}
```

### 2.3 日志事件
```go
type Entry struct {
    Logger     *Logger
    Level      Level
    Time       time.Time
    Message    string
    Fields     Fields
    Caller     *Caller
    Error      error
    StackTrace string
}

type Caller struct {
    File     string
    Line     int
    Function string
}

func (e *Entry) String() string {
    return e.Logger.formatter.Format(e)
}
```

### 2.4 处理器接口
```go
type Handler interface {
    Handle(entry *Entry) error
    Close() error
}

// 控制台处理器
func NewConsoleHandler() Handler {
    return &ConsoleHandler{
        writer: os.Stdout,
    }
}

type ConsoleHandler struct {
    writer io.Writer
    mu     sync.Mutex
}

func (h *ConsoleHandler) Handle(entry *Entry) error {
    h.mu.Lock()
    defer h.mu.Unlock()
    _, err := h.writer.Write([]byte(entry.String() + "\n"))
    return err
}

func (h *ConsoleHandler) Close() error {
    return nil
}

// 文件处理器
func NewFileHandler(filePath string) (Handler, error) {
    file, err := os.OpenFile(filePath, os.O_CREATE|os.O_APPEND|os.O_WRONLY, 0644)
    if err != nil {
        return nil, err
    }
    return &FileHandler{
        file: file,
    }, nil
}

type FileHandler struct {
    file *os.File
    mu   sync.Mutex
}

func (h *FileHandler) Handle(entry *Entry) error {
    h.mu.Lock()
    defer h.mu.Unlock()
    _, err := h.file.Write([]byte(entry.String() + "\n"))
    return err
}

func (h *FileHandler) Close() error {
    return h.file.Close()
}

// 异步处理器
func NewAsyncHandler(handler Handler, bufferSize int) Handler {
    if bufferSize <= 0 {
        bufferSize = 1024
    }
    h := &AsyncHandler{
        handler:    handler,
        buffer:     make(chan *Entry, bufferSize),
        done:       make(chan struct{}),
        bufferSize: bufferSize,
    }
    go h.process()
    return h
}

type AsyncHandler struct {
    handler    Handler
    buffer     chan *Entry
    done       chan struct{}
    bufferSize int
    wg         sync.WaitGroup
}

func (h *AsyncHandler) Handle(entry *Entry) error {
    select {
    case h.buffer <- entry:
        h.wg.Add(1)
        return nil
    default:
        return fmt.Errorf("async handler buffer full")
    }
}

func (h *AsyncHandler) process() {
    for {
        select {
        case entry := <-h.buffer:
            h.handler.Handle(entry)
            h.wg.Done()
        case <-h.done:
            return
        }
    }
}

func (h *AsyncHandler) Close() error {
    close(h.done)
    h.wg.Wait()
    return h.handler.Close()
}
```

### 2.5 格式化器接口
```go
type Formatter interface {
    Format(entry *Entry) string
}

// 文本格式化器
func NewTextFormatter() Formatter {
    return &TextFormatter{}
}

type TextFormatter struct{}

func (f *TextFormatter) Format(entry *Entry) string {
    var buf bytes.Buffer
    
    // 时间
    buf.WriteString(entry.Time.Format("2006-01-02 15:04:05.000"))
    buf.WriteString(" ")
    
    // 级别
    levelStr := entry.Level.String()
    buf.WriteString(fmt.Sprintf("[%s]", levelStr))
    buf.WriteString(" ")
    
    // 记录器名称
    buf.WriteString(fmt.Sprintf("[%s]", entry.Logger.name))
    buf.WriteString(" ")
    
    // 消息
    buf.WriteString(entry.Message)
    
    // 字段
    if len(entry.Fields) > 0 {
        buf.WriteString(" ")
        for k, v := range entry.Fields {
            buf.WriteString(fmt.Sprintf("%s=%v", k, v))
            buf.WriteString(" ")
        }
    }
    
    // 错误
    if entry.Error != nil {
        buf.WriteString(fmt.Sprintf("error=%v", entry.Error))
    }
    
    // 调用者
    if entry.Caller != nil {
        buf.WriteString(fmt.Sprintf(" caller=%s:%d", entry.Caller.File, entry.Caller.Line))
    }
    
    return buf.String()
}

// JSON格式化器
func NewJSONFormatter() Formatter {
    return &JSONFormatter{}
}

type JSONFormatter struct{}

func (f *JSONFormatter) Format(entry *Entry) string {
    data := map[string]interface{}{
        "time":    entry.Time,
        "level":   entry.Level.String(),
        "logger":  entry.Logger.name,
        "message": entry.Message,
    }
    
    // 添加字段
    for k, v := range entry.Fields {
        data[k] = v
    }
    
    // 添加错误
    if entry.Error != nil {
        data["error"] = entry.Error.Error()
    }
    
    // 添加调用者
    if entry.Caller != nil {
        data["caller"] = fmt.Sprintf("%s:%d", entry.Caller.File, entry.Caller.Line)
        data["function"] = entry.Caller.Function
    }
    
    b, err := json.Marshal(data)
    if err != nil {
        return fmt.Sprintf(`{"time":"%s","level":"ERROR","message":"json marshal error: %v"}`, 
            entry.Time.Format(time.RFC3339), err)
    }
    
    return string(b)
}
```

## 3. 核心方法实现

### 3.1 日志记录方法
```go
func (l *Logger) Debug(args ...interface{}) {
    l.log(DebugLevel, fmt.Sprint(args...), nil)
}

func (l *Logger) Debugf(format string, args ...interface{}) {
    l.log(DebugLevel, fmt.Sprintf(format, args...), nil)
}

func (l *Logger) Debugln(args ...interface{}) {
    l.log(DebugLevel, fmt.Sprintln(args...), nil)
}

func (l *Logger) Info(args ...interface{}) {
    l.log(InfoLevel, fmt.Sprint(args...), nil)
}

func (l *Logger) Infof(format string, args ...interface{}) {
    l.log(InfoLevel, fmt.Sprintf(format, args...), nil)
}

func (l *Logger) Infoln(args ...interface{}) {
    l.log(InfoLevel, fmt.Sprintln(args...), nil)
}

func (l *Logger) Warn(args ...interface{}) {
    l.log(WarnLevel, fmt.Sprint(args...), nil)
}

func (l *Logger) Warnf(format string, args ...interface{}) {
    l.log(WarnLevel, fmt.Sprintf(format, args...), nil)
}

func (l *Logger) Warnln(args ...interface{}) {
    l.log(WarnLevel, fmt.Sprintln(args...), nil)
}

func (l *Logger) Error(args ...interface{}) {
    l.log(ErrorLevel, fmt.Sprint(args...), nil)
}

func (l *Logger) Errorf(format string, args ...interface{}) {
    l.log(ErrorLevel, fmt.Sprintf(format, args...), nil)
}

func (l *Logger) Errorln(args ...interface{}) {
    l.log(ErrorLevel, fmt.Sprintln(args...), nil)
}

func (l *Logger) ErrorWith(err error, args ...interface{}) {
    l.log(ErrorLevel, fmt.Sprint(args...), err)
}

func (l *Logger) Fatal(args ...interface{}) {
    l.log(FatalLevel, fmt.Sprint(args...), nil)
    os.Exit(1)
}

func (l *Logger) Fatalf(format string, args ...interface{}) {
    l.log(FatalLevel, fmt.Sprintf(format, args...), nil)
    os.Exit(1)
}

func (l *Logger) Fatalln(args ...interface{}) {
    l.log(FatalLevel, fmt.Sprintln(args...), nil)
    os.Exit(1)
}

func (l *Logger) Panic(args ...interface{}) {
    msg := fmt.Sprint(args...)
    l.log(PanicLevel, msg, nil)
    panic(msg)
}

func (l *Logger) Panicf(format string, args ...interface{}) {
    msg := fmt.Sprintf(format, args...)
    l.log(PanicLevel, msg, nil)
    panic(msg)
}

func (l *Logger) Panicln(args ...interface{}) {
    msg := fmt.Sprintln(args...)
    l.log(PanicLevel, msg, nil)
    panic(msg)
}
```

### 3.2 内部日志处理方法
```go
func (l *Logger) log(level Level, msg string, err error) {
    l.mu.RLock()
    if level < l.level {
        l.mu.RUnlock()
        return
    }
    
    // 创建日志条目
    entry := &Entry{
        Logger:  l,
        Level:   level,
        Time:    time.Now(),
        Message: msg,
        Fields:  l.fields,
        Error:   err,
    }
    
    // 获取调用者信息
    entry.Caller = l.getCaller()
    
    // 复制处理器列表
    handlers := make([]Handler, len(l.handlers))
    copy(handlers, l.handlers)
    l.mu.RUnlock()
    
    // 处理日志
    for _, handler := range handlers {
        if err := handler.Handle(entry); err != nil {
            // 处理错误，避免日志系统本身的错误影响业务
            fmt.Fprintf(os.Stderr, "log handler error: %v\n", err)
        }
    }
}

func (l *Logger) getCaller() *Caller {
    pc, file, line, ok := runtime.Caller(3) // 跳过log、log调用者和getCaller
    if !ok {
        return nil
    }
    
    fn := runtime.FuncForPC(pc)
    var function string
    if fn != nil {
        function = fn.Name()
    }
    
    return &Caller{
        File:     file,
        Line:     line,
        Function: function,
    }
}
```

### 3.3 全局日志器
```go
var defaultLogger = NewLogger("root")

func Debug(args ...interface{}) {
    defaultLogger.Debug(args...)
}

func Debugf(format string, args ...interface{}) {
    defaultLogger.Debugf(format, args...)
}

func Debugln(args ...interface{}) {
    defaultLogger.Debugln(args...)
}

func Info(args ...interface{}) {
    defaultLogger.Info(args...)
}

func Infof(format string, args ...interface{}) {
    defaultLogger.Infof(format, args...)
}

func Infoln(args ...interface{}) {
    defaultLogger.Infoln(args...)
}

func Warn(args ...interface{}) {
    defaultLogger.Warn(args...)
}

func Warnf(format string, args ...interface{}) {
    defaultLogger.Warnf(format, args...)
}

func Warnln(args ...interface{}) {
    defaultLogger.Warnln(args...)
}

func Error(args ...interface{}) {
    defaultLogger.Error(args...)
}

func Errorf(format string, args ...interface{}) {
    defaultLogger.Errorf(format, args...)
}

func Errorln(args ...interface{}) {
    defaultLogger.Errorln(args...)
}

func ErrorWith(err error, args ...interface{}) {
    defaultLogger.ErrorWith(err, args...)
}

func Fatal(args ...interface{}) {
    defaultLogger.Fatal(args...)
}

func Fatalf(format string, args ...interface{}) {
    defaultLogger.Fatalf(format, args...)
}

func Fatalln(args ...interface{}) {
    defaultLogger.Fatalln(args...)
}

func Panic(args ...interface{}) {
    defaultLogger.Panic(args...)
}

func Panicf(format string, args ...interface{}) {
    defaultLogger.Panicf(format, args...)
}

func Panicln(args ...interface{}) {
    defaultLogger.Panicln(args...)
}

func GetLogger(name string) *Logger {
    return NewLogger(name)
}

func SetLevel(level Level) {
    defaultLogger.WithLevel(level)
}

func AddHandler(handler Handler) {
    defaultLogger.WithHandler(handler)
}
```

### 3.4 配置加载
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

func LoadConfig(filePath string) (*Config, error) {
    data, err := ioutil.ReadFile(filePath)
    if err != nil {
        return nil, err
    }
    
    var config Config
    if strings.HasSuffix(filePath, ".json") {
        if err := json.Unmarshal(data, &config); err != nil {
            return nil, err
        }
    } else if strings.HasSuffix(filePath, ".yaml") || strings.HasSuffix(filePath, ".yml") {
        if err := yaml.Unmarshal(data, &config); err != nil {
            return nil, err
        }
    } else {
        return nil, fmt.Errorf("unsupported config file format: %s", filePath)
    }
    
    return &config, nil
}

func ApplyConfig(config *Config) error {
    // 设置日志级别
    level, err := ParseLevel(config.Level)
    if err != nil {
        return err
    }
    defaultLogger.WithLevel(level)
    
    // 设置格式化器
    switch config.Formatter {
    case "json":
        defaultLogger.WithFormatter(NewJSONFormatter())
    case "text":
        defaultLogger.WithFormatter(NewTextFormatter())
    }
    
    // 添加处理器
    for _, hc := range config.Handlers {
        var handler Handler
        switch hc.Type {
        case "console":
            handler = NewConsoleHandler()
        case "file":
            filePath, ok := hc.Config["path"]
            if !ok {
                return fmt.Errorf("file handler missing path config")
            }
            handler, err = NewFileHandler(filePath)
            if err != nil {
                return err
            }
        case "async":
            targetType, ok := hc.Config["target"]
            if !ok {
                return fmt.Errorf("async handler missing target config")
            }
            var targetHandler Handler
            switch targetType {
            case "console":
                targetHandler = NewConsoleHandler()
            case "file":
                filePath, ok := hc.Config["path"]
                if !ok {
                    return fmt.Errorf("file handler missing path config")
                }
                targetHandler, err = NewFileHandler(filePath)
                if err != nil {
                    return err
                }
            default:
                return fmt.Errorf("invalid async handler target: %s", targetType)
            }
            bufferSize := 1024
            if bs, ok := hc.Config["buffer_size"]; ok {
                if size, err := strconv.Atoi(bs); err == nil {
                    bufferSize = size
                }
            }
            handler = NewAsyncHandler(targetHandler, bufferSize)
        default:
            return fmt.Errorf("invalid handler type: %s", hc.Type)
        }
        defaultLogger.WithHandler(handler)
    }
    
    // 添加全局字段
    if len(config.Fields) > 0 {
        fields := Fields{}
        for k, v := range config.Fields {
            fields[k] = v
        }
        defaultLogger.WithFields(fields)
    }
    
    return nil
}
```

## 4. 技术特点

### 4.1 高性能
- **级别过滤**：通过级别过滤减少不必要的日志处理
- **异步处理**：支持异步日志处理，减少对业务代码的影响
- **缓冲机制**：异步处理器支持缓冲，提高性能
- **并发安全**：使用读写锁保证并发安全

### 4.2 灵活性
- **可配置性**：支持通过配置文件调整日志行为
- **可扩展性**：支持自定义处理器和格式化器
- **多种输出**：支持控制台、文件等多种输出方式
- **多种格式**：支持文本和JSON等多种日志格式

### 4.3 可靠性
- **错误处理**：处理器错误不会影响业务代码
- **优雅关闭**：支持优雅关闭，确保日志不会丢失
- **上下文支持**：支持上下文，方便追踪请求
- **结构化日志**：支持结构化日志，方便分析

### 4.4 易用性
- **简洁API**：提供简洁易用的API，与标准库log类似
- **链式调用**：支持链式调用，方便添加字段和配置
- **全局日志器**：提供全局日志器，方便快速使用
- **丰富的辅助方法**：提供Debug/Info/Warn/Error等多种级别的辅助方法

## 5. 依赖管理

### 5.1 内部依赖
- **标准库**：
  - `fmt`：格式化输出
  - `os`：文件操作和系统调用
  - `sync`：并发控制
  - `time`：时间处理
  - `runtime`：获取调用者信息
  - `context`：上下文支持
  - `bytes`：字节缓冲区
  - `io`：I/O操作
  - `json`：JSON格式化
  - `strings`：字符串操作
  - `strconv`：字符串转换

### 5.2 外部依赖
- **可选依赖**：
  - `gopkg.in/yaml.v3`：YAML配置文件支持
  - `github.com/elastic/go-elasticsearch`：Elasticsearch支持
  - `github.com/go-redis/redis/v8`：Redis支持

## 6. 性能优化

### 6.1 内存优化
- **对象池**：对于频繁创建的对象（如日志条目），考虑使用对象池
- **字段复用**：复用Fields对象，减少内存分配
- **字符串拼接**：使用bytes.Buffer或strings.Builder进行字符串拼接

### 6.2 CPU优化
- **级别过滤**：尽早进行级别过滤，减少不必要的处理
- **异步处理**：将I/O操作异步化，减少CPU等待
- **批量处理**：对于文件输出，考虑批量写入
- **缓存**：缓存格式化结果，减少重复计算

### 6.3 I/O优化
- **缓冲写入**：使用带缓冲的写入器，减少I/O操作次数
- **异步写入**：将写入操作异步化，减少阻塞
- **滚动文件**：支持按大小或时间滚动文件，避免单个文件过大
- **压缩存储**：对于历史日志，考虑压缩存储

## 7. 测试策略

### 7.1 单元测试
- **测试日志级别过滤**：测试不同级别的日志是否正确过滤
- **测试处理器**：测试各种处理器是否正确处理日志
- **测试格式化器**：测试各种格式化器是否正确格式化日志
- **测试并发**：测试并发场景下的日志处理
- **测试错误处理**：测试错误处理是否正确

### 7.2 集成测试
- **测试配置加载**：测试配置文件是否正确加载
- **测试多处理器**：测试多个处理器同时工作
- **测试异步处理**：测试异步处理器的性能和可靠性
- **测试文件滚动**：测试文件滚动功能

### 7.3 性能测试
- **测试日志吞吐量**：测试单位时间内可以处理的日志数量
- **测试延迟**：测试日志处理的延迟
- **测试内存使用**：测试内存使用情况
- **测试CPU使用**：测试CPU使用情况

## 8. 部署与配置

### 8.1 配置文件示例

**JSON配置**：
```json
{
  "level": "info",
  "handlers": [
    {
      "type": "console"
    },
    {
      "type": "file",
      "config": {
        "path": "./logs/app.log"
      }
    },
    {
      "type": "async",
      "config": {
        "target": "file",
        "path": "./logs/async.log",
        "buffer_size": "1024"
      }
    }
  ],
  "formatter": "text",
  "fields": {
    "app": "dnf-game-server",
    "env": "production"
  }
}
```

**YAML配置**：
```yaml
level: info
handlers:
  - type: console
  - type: file
    config:
      path: ./logs/app.log
  - type: async
    config:
      target: file
      path: ./logs/async.log
      buffer_size: "1024"
formatter: text
fields:
  app: dnf-game-server
  env: production
```

### 8.2 部署建议
- **本地开发**：使用控制台处理器，方便调试
- **测试环境**：使用文件处理器，保存日志
- **生产环境**：使用异步处理器和文件处理器，同时考虑使用ELK Stack

### 8.3 日志管理
- **日志滚动**：配置日志滚动，避免单个文件过大
- **日志清理**：定期清理过期日志，避免磁盘空间不足
- **日志备份**：定期备份重要日志，用于审计和分析

## 9. 监控与维护

### 9.1 监控指标
- **日志级别分布**：不同级别的日志数量分布
- **日志吞吐量**：单位时间内的日志数量
- **日志处理延迟**：日志处理的延迟
- **处理器错误**：处理器错误的数量
- **异步队列长度**：异步处理器的队列长度

### 9.2 维护任务
- **日志清理**：定期清理过期日志
- **日志分析**：定期分析日志，发现问题
- **性能调优**：根据监控指标调优日志系统
- **安全审计**：定期审计安全相关的日志

## 10. 总结

本Go日志系统实现了一个高性能、可靠、灵活、易用的日志系统，具有以下特点：

- **丰富的功能**：支持多种日志级别、多种输出方式、多种日志格式
- **高性能**：支持异步处理、缓冲机制，对系统性能影响小
- **可靠性**：处理器错误不会影响业务代码，支持优雅关闭
- **灵活性**：支持自定义处理器和格式化器，可通过配置文件调整行为
- **易用性**：提供简洁易用的API，与标准库log类似

该日志系统可以满足游戏服务端的各种日志需求，为系统监控、问题排查、数据分析提供重要支持。