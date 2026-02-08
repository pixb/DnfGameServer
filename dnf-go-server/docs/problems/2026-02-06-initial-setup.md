# DNF Go Server 迁移问题记录

## 2026-02-06 项目初始化

### 问题1: Go模块依赖管理
**问题描述**: 创建go.mod后，依赖包无法自动下载

**解决方案**:
```bash
go mod tidy
go get -u gorm.io/gorm gorm.io/driver/mysql
```

**关键要点**:
- go.mod 需要放在项目根目录
- 使用 `go mod tidy` 自动整理依赖
- 手动 `go get` 添加新依赖

---

### 问题2: Java → Go 类型映射
**问题描述**: Java中的基本类型和包装类型需要映射到Go类型

**映射表**:
| Java类型 | Go类型 | 说明 |
|---------|--------|------|
| int/Integer | int | Go中int根据平台32或64位 |
| long/Long | int64 | 明确使用int64 |
| String | string | 值类型，非指针 |
| boolean/Boolean | bool | 值类型 |
| Date | time.Time | 使用time包 |
| BigDecimal | float64 | 或使用decimal包 |
| byte[] | []byte | 切片 |
| List<T> | []T | 切片 |
| Map<K,V> | map[K]V | 内建map类型 |

---

### 问题3: 并发模型转换
**问题描述**: Java使用ThreadPool和synchronized，Go使用Goroutine和Channel

**Java代码示例**:
```java
Executor executor = Executors.newCachedThreadPool();
ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
```

**Go等效代码**:
```go
// Goroutine直接启动
go func() {
    // 任务逻辑
}()

// 使用sync.Map或map+RWLock
var syncMap sync.Map
var mutexMap = make(map[string]interface{})
var mu sync.RWMutex

// 读写锁示例
mu.RLock()
value := mutexMap[key]
mu.RUnlock()

mu.Lock()
mutexMap[key] = value
mu.Unlock()
```

---

### 问题4: Spring依赖注入 → Go手动管理
**问题描述**: Java使用@Autowired自动注入，Go没有内置DI框架

**解决方案**:
1. 手动注入：在main中按顺序创建依赖
2. 使用wire工具生成依赖注入代码
3. 使用全局变量（简单但不推荐用于大型项目）

**手动注入示例**:
```go
// main.go
db, err := db.NewDB(&cfg.Database)
if err != nil {
    log.Fatal(err)
}

playerService := game.NewPlayerService(db)
roleService := game.NewRoleService(db)

// 注入到HTTP控制器
httpServer := http.NewServer(playerService, roleService)
```

---

### 问题5: 日志库选择
**Java**: SLF4J + Logback  
**Go选型**: Zap (高性能，结构化日志)

**对比**:
- Zap性能更好，但API相对繁琐
- Logrus更简单易用，但性能稍差
- 游戏服务器推荐Zap，追求极致性能

**封装要点**:
```go
// 需要封装辅助函数创建Field
func String(key, val string) zap.Field
func Int(key string, val int) zap.Field
func Error(err error) zap.Field
```

---

### 问题6: ORM选择 - GORM vs 其他
**可选方案**:
1. **GORM**: 功能最全，学习成本低
2. **SQLx**: 轻量级，性能好
3. **Ent**: Facebook出品，类型安全
4. **原生database/sql**: 性能最好，代码量大

**选择GORM原因**:
- 类似Nutz的CRUD操作
- 支持自动迁移
- 社区活跃，文档完善

**注意事项**:
- 关闭默认事务提升性能
- 设置合理的连接池参数
- 使用单数表名(与Java保持一致)

---

## 待解决问题

1. [ ] Protobuf消息定义迁移
2. [ ] TCP Server实现（替代MINA）
3. [ ] 会话管理设计
4. [ ] 消息分发器实现
5. [ ] 热更新方案设计

---

## 经验总结

### Java → Go 思维转换
1. **不要试图写Java风格的Go代码**
2. ** embrace 显式错误处理**
3. **善用Goroutine和Channel**
4. **值传递 vs 指针传递要谨慎选择**
5. **interface在Go中更轻量，大胆使用**

### 性能优化预设
1. 使用 `sync.Pool` 复用对象
2. 避免在热路径分配内存
3. 使用 `atomic` 包做简单计数器
4. 合理设置 `GOMAXPROCS`
5. 考虑使用 `pprof` 做性能分析

---

**记录人**: AI Assistant  
**日期**: 2026-02-06
