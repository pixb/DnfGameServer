# 事件系统 - API文档

## 1. 概述
本文档描述了事件系统的API接口，包括事件管理器的核心方法和使用方式。事件系统提供了事件的注册、触发和处理机制，为游戏服务端提供了灵活的事件驱动能力。

## 2. 核心API

### 2.1 事件管理器API

#### 2.1.1 创建事件管理器
```go
// NewEventManager 创建一个新的事件管理器
// asyncWorkers: 异步工作协程数量
func NewEventManager(asyncWorkers int) *EventManager
```

**参数说明**：
- `asyncWorkers`：异步工作协程的数量，用于处理异步事件

**返回值**：
- `*EventManager`：事件管理器实例

#### 2.1.2 注册监听器
```go
// RegisterListener 注册事件监听器
// listener: 事件监听器实例
func (em *EventManager) RegisterListener(listener EventListener)
```

**参数说明**：
- `listener`：实现了EventListener接口的监听器实例

**使用示例**：
```go
// 创建监听器
listener := NewFuncListener(
    func(event Event) error {
        // 处理事件
        fmt.Printf("Received event: %s\n", event.Type())
        return nil
    },
    0, // 优先级
    EventTypePlayerLogin, // 感兴趣的事件类型
    EventTypePlayerLogout,
)

// 注册监听器
eventManager.RegisterListener(listener)
```

#### 2.1.3 触发同步事件
```go
// TriggerEvent 同步触发事件
// event: 事件对象
// 返回值: 错误信息
func (em *EventManager) TriggerEvent(event Event) error
```

**参数说明**：
- `event`：实现了Event接口的事件对象

**返回值**：
- `error`：事件处理过程中的错误

**使用示例**：
```go
// 创建事件
event := NewBaseEvent(EventTypePlayerLogin, map[string]interface{}{
    "playerId": 12345,
    "timestamp": time.Now().Unix(),
})

// 触发事件
if err := eventManager.TriggerEvent(event); err != nil {
    log.Printf("Error triggering event: %v", err)
}
```

#### 2.1.4 触发异步事件
```go
// TriggerAsyncEvent 异步触发事件
// event: 事件对象
func (em *EventManager) TriggerAsyncEvent(event Event)
```

**参数说明**：
- `event`：实现了Event接口的事件对象

**使用示例**：
```go
// 创建事件
event := NewBaseEvent(EventTypeSystemNotification, map[string]interface{}{
    "message": "System maintenance will start in 10 minutes",
    "priority": "high",
})

// 异步触发事件
eventManager.TriggerAsyncEvent(event)
```

### 2.2 事件接口

#### 2.2.1 事件类型
```go
// EventType 事件类型
type EventType string

// 预定义事件类型
const (
    EventTypePlayerLogin        EventType = "player_login"
    EventTypePlayerLogout       EventType = "player_logout"
    EventTypePlayerLevelUp      EventType = "player_level_up"
    EventTypePlayerTrade        EventType = "player_trade"
    EventTypeActivityStart      EventType = "activity_start"
    EventTypeActivityEnd        EventType = "activity_end"
    EventTypeSystemNotification EventType = "system_notification"
)
```

#### 2.2.2 事件接口方法
```go
// Event 事件接口
type Event interface {
    // Type 返回事件类型
    Type() EventType
    // Data 返回事件数据
    Data() interface{}
    // Timestamp 返回事件时间戳
    Timestamp() int64
}
```

### 2.3 监听器接口

#### 2.3.1 监听器接口方法
```go
// EventListener 事件监听器接口
type EventListener interface {
    // Handle 处理事件
    // event: 事件对象
    // 返回值: 错误信息
    Handle(event Event) error
    // Priority 返回监听器优先级
    Priority() int
    // InterestTypes 返回监听器感兴趣的事件类型
    InterestTypes() []EventType
}
```

## 3. 事件数据结构

### 3.1 基础事件结构
```go
// BaseEvent 基础事件实现
type BaseEvent struct {
    eventType EventType
    data      interface{}
    timestamp int64
}

// NewBaseEvent 创建基础事件
// eventType: 事件类型
// data: 事件数据
// 返回值: 事件对象
func NewBaseEvent(eventType EventType, data interface{}) *BaseEvent
```

### 3.2 函数式监听器结构
```go
// FuncListener 函数式监听器
type FuncListener struct {
    handleFunc    func(event Event) error
    priority      int
    interestTypes []EventType
}

// NewFuncListener 创建函数式监听器
// handleFunc: 事件处理函数
// priority: 监听器优先级
// interestTypes: 感兴趣的事件类型
// 返回值: 监听器对象
func NewFuncListener(handleFunc func(event Event) error, priority int, interestTypes ...EventType) *FuncListener
```

## 4. 错误处理

### 4.1 事件处理错误
- 监听器的`Handle`方法可以返回错误
- 事件管理器会记录错误但继续处理其他监听器
- 同步事件触发时，会返回最后一个错误

### 4.2 常见错误类型
| 错误类型 | 描述 | 处理建议 |
|---------|------|--------|
| 监听器处理错误 | 监听器处理事件时发生错误 | 检查监听器实现和事件数据 |
| 异步通道满 | 异步事件通道已满 | 增加通道大小或工作协程数量 |
| 监听器未注册 | 尝试触发事件但无监听器 | 检查监听器注册是否正确 |

## 5. 使用示例

### 5.1 基本使用示例
```go
// 创建事件管理器
eventManager := NewEventManager(4) // 4个异步工作协程

// 注册监听器
eventManager.RegisterListener(NewFuncListener(
    func(event Event) error {
        fmt.Printf("Player login event: %v\n", event.Data())
        return nil
    },
    0,
    EventTypePlayerLogin,
))

// 创建并触发事件
event := NewBaseEvent(EventTypePlayerLogin, map[string]interface{}{
    "playerId": 12345,
    "name": "Player1",
})

eventManager.TriggerEvent(event)
```

### 5.2 异步事件示例
```go
// 注册系统通知监听器
eventManager.RegisterListener(NewFuncListener(
    func(event Event) error {
        data := event.Data().(map[string]interface{})
        fmt.Printf("System notification: %s\n", data["message"])
        // 模拟耗时操作
        time.Sleep(100 * time.Millisecond)
        return nil
    },
    0,
    EventTypeSystemNotification,
))

// 异步触发系统通知
event := NewBaseEvent(EventTypeSystemNotification, map[string]interface{}{
    "message": "Server will restart in 5 minutes",
    "level": "warning",
})

eventManager.TriggerAsyncEvent(event)
fmt.Println("Async event triggered, continuing execution...")
```

### 5.3 优先级示例
```go
// 注册高优先级监听器
eventManager.RegisterListener(NewFuncListener(
    func(event Event) error {
        fmt.Println("High priority listener handling event")
        return nil
    },
    10, // 高优先级
    EventTypePlayerLevelUp,
))

// 注册低优先级监听器
eventManager.RegisterListener(NewFuncListener(
    func(event Event) error {
        fmt.Println("Low priority listener handling event")
        return nil
    },
    0, // 低优先级
    EventTypePlayerLevelUp,
))

// 触发事件
event := NewBaseEvent(EventTypePlayerLevelUp, map[string]interface{}{
    "playerId": 12345,
    "oldLevel": 10,
    "newLevel": 11,
})

eventManager.TriggerEvent(event)
// 输出顺序: 
// High priority listener handling event
// Low priority listener handling event
```

## 6. 性能考虑

### 6.1 同步 vs 异步事件
- **同步事件**：适用于需要立即处理且依赖处理结果的场景
- **异步事件**：适用于耗时操作或不需要立即处理结果的场景

### 6.2 监听器数量
- 每个事件类型的监听器数量应控制在合理范围内
- 过多的监听器会增加事件处理时间

### 6.3 事件数据大小
- 事件数据应保持简洁，避免传递过大的数据结构
- 对于大体积数据，建议传递引用或标识符

### 6.4 异步工作协程
- 异步工作协程数量应根据服务器CPU核心数和事件处理负载进行调整
- 一般建议设置为CPU核心数的1-2倍

## 7. 最佳实践

### 7.1 事件设计
- 事件类型应具有明确的语义
- 事件数据应包含必要的信息，避免冗余

### 7.2 监听器设计
- 监听器应职责单一，专注于处理特定类型的事件
- 监听器处理逻辑应简洁高效，避免阻塞

### 7.3 错误处理
- 监听器应妥善处理可能的错误
- 事件管理器应记录错误但不中断事件处理流程

### 7.4 资源管理
- 不再需要的监听器应及时移除
- 事件数据中的资源应正确释放

## 8. 版本兼容性

### 8.1 API变更
| 版本 | 变更内容 | 兼容性 |
|------|---------|--------|
| v1.0 | 初始版本 | 完全兼容 |
| v1.1 | 增加异步事件支持 | 向后兼容 |
| v1.2 | 增加事件过滤机制 | 向后兼容 |

### 8.2 迁移指南
- 从v1.0升级到v1.1：直接替换事件管理器实现，API接口保持不变
- 从v1.1升级到v1.2：新增事件过滤功能，现有代码无需修改