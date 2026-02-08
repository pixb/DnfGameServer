# Proto 协议迁移总结

## 已完成工作

### 1. 协议分析
分析了Java项目的protobuf消息结构：
- 使用百度 jprotobuf 库的注解方式定义
- `@MessageMeta(module, cmd)` 用于消息路由
- `@Protobuf` 注解定义字段类型和顺序
- REQ_ 前缀表示请求，RES_ 前缀表示响应
- PT_ 前缀表示嵌套消息类型

### 2. 协议定义文件

#### 配置文件
- **buf.yaml** - Buf lint和breaking change规则
- **buf.gen.yaml** - 代码生成配置（生成Go + gRPC + Connect）

#### 消息定义 (10个文件)
1. **common.proto** - 公共类型和枚举
2. **auth.proto** - 登录认证、角色创建
3. **role.proto** - 角色系统、技能
4. **item.proto** - 物品、装备、背包
5. **dungeon.proto** - 副本系统
6. **chat.proto** - 聊天、好友
7. **shop.proto** - 商店、拍卖行
8. **quest.proto** - 任务系统
9. **guild.proto** - 公会系统
10. **service.proto** - gRPC服务定义

### 3. 协议覆盖范围

#### 认证系统
- ✅ 登录/登出
- ✅ 角色创建
- ✅ 角色列表
- ✅ 角色选择

#### 角色系统
- ✅ 基础属性
- ✅ 战斗属性
- ✅ 技能学习/升级
- ✅ 疲劳值恢复

#### 物品系统
- ✅ 背包管理
- ✅ 装备穿戴
- ✅ 物品使用
- ✅ 物品交易

#### 副本系统
- ✅ 副本进入/退出
- ✅ 怪物系统
- ✅ 副本结算
- ✅ 复活机制

#### 社交系统
- ✅ 聊天频道
- ✅ 好友管理
- ✅ 私聊功能

#### 经济系统
- ✅ NPC商店
- ✅ 拍卖行
- ✅ 个人商店
- ✅ 交易系统

#### 任务系统
- ✅ 任务接受/完成
- ✅ 任务奖励
- ✅ 任务进度

#### 公会系统
- ✅ 公会创建/加入
- ✅ 公会技能
- ✅ 公会贡献

## Java → Proto 关键转换

### 注解映射

```java
// Java (jprotobuf)
@MessageMeta(module = 10000, cmd = 0)
@ProtobufClass
public class REQ_LOGIN extends Message {
    @Protobuf(fieldType = FieldType.STRING, order = 1)
    public String openid;
    
    @Protobuf(fieldType = FieldType.UINT32, order = 2)
    public Integer type;
}
```

```protobuf
// Proto3
message LoginRequest {
    string openid = 1;
    uint32 type = 2;
}
```

### 类型映射

| Java (jprotobuf) | Proto3 | Go |
|-----------------|--------|-----|
| STRING | string | string |
| INT32 | int32 | int32 |
| UINT32 | uint32 | uint32 |
| INT64 | int64 | int64 |
| UINT64 | uint64 | uint64 |
| BOOL | bool | bool |
| FLOAT | float | float32 |
| DOUBLE | double | float64 |
| List<T> | repeated T | []T |

## 生成Go代码

### 前提条件
```bash
# 安装 Buf
go install github.com/bufbuild/buf/cmd/buf@latest
```

### 生成命令
```bash
cd proto

# 下载依赖
buf dep update

# 生成代码
buf generate

# 检查lint
buf lint

# 检查破坏性变更
buf breaking --against .git#main
```

### 生成文件结构
```
gen/dnf/v1/
├── common.pb.go              # 基础消息
├── auth.pb.go                # 认证消息
├── role.pb.go                # 角色消息
├── item.pb.go                # 物品消息
├── dungeon.pb.go             # 副本消息
├── chat.pb.go                # 聊天消息
├── shop.pb.go                # 商店消息
├── quest.pb.go               # 任务消息
├── guild.pb.go               # 公会消息
├── service.pb.go             # 服务定义
├── service_grpc.pb.go        # gRPC客户端/服务端
└── service_connect.go        # Connect RPC
```

## 使用示例

### 1. 序列化/反序列化

```go
import (
    "google.golang.org/protobuf/proto"
    v1 "dnf-go-server/proto/gen/dnf/v1"
)

// 序列化
req := &v1.LoginRequest{
    Openid: "player123",
    Type:   1,
    Token:  "xxx",
}

data, err := proto.Marshal(req)
if err != nil {
    log.Fatal(err)
}

// 反序列化
var resp v1.LoginResponse
if err := proto.Unmarshal(data, &resp); err != nil {
    log.Fatal(err)
}
```

### 2. gRPC服务端

```go
import (
    "google.golang.org/grpc"
    v1 "dnf-go-server/proto/gen/dnf/v1"
)

type gameServer struct {
    v1.UnimplementedGameServiceServer
}

func (s *gameServer) Login(ctx context.Context, req *v1.LoginRequest) (*v1.LoginResponse, error) {
    // 处理登录逻辑
    return &v1.LoginResponse{
        Error:   0,
        AuthKey: "xxx",
    }, nil
}

func main() {
    lis, err := net.Listen("tcp", ":9000")
    if err != nil {
        log.Fatal(err)
    }
    
    s := grpc.NewServer()
    v1.RegisterGameServiceServer(s, &gameServer{})
    
    if err := s.Serve(lis); err != nil {
        log.Fatal(err)
    }
}
```

### 3. gRPC客户端

```go
conn, err := grpc.Dial("localhost:9000", grpc.WithInsecure())
if err != nil {
    log.Fatal(err)
}
defer conn.Close()

client := v1.NewAuthServiceClient(conn)
resp, err := client.Login(context.Background(), &v1.LoginRequest{
    Openid: "player123",
    Type:   1,
})
if err != nil {
    log.Fatal(err)
}
fmt.Println(resp.AuthKey)
```

## 与TCP服务器集成

### 消息编解码器

```go
type ProtoCodec struct{}

func (c *ProtoCodec) Decode(reader io.Reader) (interface{}, error) {
    // 1. 读取消息长度
    lengthBuf := make([]byte, 4)
    if _, err := io.ReadFull(reader, lengthBuf); err != nil {
        return nil, err
    }
    length := binary.BigEndian.Uint32(lengthBuf)
    
    // 2. 读取消息体
    data := make([]byte, length)
    if _, err := io.ReadFull(reader, data); err != nil {
        return nil, err
    }
    
    // 3. 根据module+cmd反序列化具体消息类型
    // 需要消息注册表来根据ID创建对应的消息实例
    return DeserializeMessage(data)
}

func (c *ProtoCodec) Encode(message interface{}) ([]byte, error) {
    msg, ok := message.(proto.Message)
    if !ok {
        return nil, fmt.Errorf("message must be proto.Message")
    }
    
    data, err := proto.Marshal(msg)
    if err != nil {
        return nil, err
    }
    
    // 添加长度前缀
    result := make([]byte, 4+len(data))
    binary.BigEndian.PutUint32(result, uint32(len(data)))
    copy(result[4:], data)
    
    return result, nil
}
```

### 消息路由

```go
// 消息注册表
type MessageRegistry struct {
    handlers map[int32]map[int32]MessageHandler
}

func (r *MessageRegistry) Register(module, cmd int32, handler MessageHandler) {
    if r.handlers[module] == nil {
        r.handlers[module] = make(map[int32]MessageHandler)
    }
    r.handlers[module][cmd] = handler
}

func (r *MessageRegistry) Handle(session *Session, module, cmd int32, data []byte) {
    handler := r.handlers[module][cmd]
    if handler == nil {
        log.Printf("no handler for module=%d, cmd=%d", module, cmd)
        return
    }
    handler(session, data)
}
```

## 最佳实践

1. **向后兼容**
   - 只添加新字段，不删除旧字段
   - 不要修改已有字段的number
   - 使用 `reserved` 保留已删除的字段

2. **性能优化**
   - 使用 proto.Marshal 而非 JSON
   - 使用 sync.Pool 复用消息对象
   - 避免在热路径创建大量临时对象

3. **安全性**
   - 验证所有输入字段
   - 限制消息大小防止DoS
   - 敏感字段使用oneof包装

## 后续工作

- [ ] 实现消息注册表（module+cmd → proto消息类型）
- [ ] 完成ProtoCodec编解码器
- [ ] 集成到TCP服务器
- [ ] 添加消息验证逻辑
- [ ] 压力测试序列化性能

---

**创建日期**: 2026-02-06  
**Proto文件数**: 10  
**消息类型数**: ~100+  
**服务方法数**: 50+
