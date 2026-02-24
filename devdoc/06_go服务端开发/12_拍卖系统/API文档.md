# 拍卖系统 - API文档

## 1. 概述
本文档描述了拍卖系统的API接口，包括HTTP接口和gRPC接口。拍卖系统API提供了物品拍卖、竞拍、一口价购买、拍卖管理、查询等功能，为前端应用和其他系统提供了标准化的接口。

## 2. 接口设计

### 2.1 HTTP接口

#### 2.1.1 基础信息
- **基础URL**：`/api/auction`
- **请求方法**：GET/POST
- **内容类型**：`application/json`
- **认证方式**：JWT Token（需要认证的接口）

#### 2.1.2 接口列表

| 接口路径 | 方法 | 模块 | 功能描述 | 认证要求 |
|---------|------|------|----------|----------|
| `/list` | GET | 拍卖查询 | 获取拍卖物品列表 | 否 |
| `/create` | POST | 拍卖管理 | 创建拍卖 | 是 |
| `/bid` | POST | 竞拍 | 竞拍拍卖物品 | 是 |
| `/buyout` | POST | 购买 | 一口价购买拍卖物品 | 是 |
| `/cancel` | POST | 拍卖管理 | 取消拍卖 | 是 |
| `/my` | GET | 个人中心 | 获取我的拍卖物品 | 是 |

### 2.2 gRPC接口

#### 2.2.1 服务定义
```protobuf
service AuctionService {
    // 创建拍卖
    rpc CreateAuction(CreateAuctionRequest) returns (AuctionResponse);
    // 竞拍
    rpc Bid(BidRequest) returns (AuctionResponse);
    // 一口价购买
    rpc Buyout(BuyoutRequest) returns (AuctionResponse);
    // 取消拍卖
    rpc Cancel(CancelRequest) returns (AuctionResponse);
    // 获取拍卖列表
    rpc GetAuctionList(AuctionListRequest) returns (AuctionListResponse);
    // 获取我的拍卖
    rpc GetMyAuctions(MyAuctionsRequest) returns (MyAuctionsResponse);
}
```

## 3. HTTP接口详情

### 3.1 获取拍卖列表

**接口路径**：`/api/auction/list`
**请求方法**：GET
**认证要求**：否

**请求参数**：

| 参数名 | 类型 | 必选 | 默认值 | 描述 |
|---------|------|------|--------|------|
| `page` | int | 否 | 1 | 页码 |
| `page_size` | int | 否 | 20 | 每页数量 |
| `category` | int | 否 | 0 | 分类ID（0表示所有分类） |
| `sort` | string | 否 | time | 排序方式（time: 按结束时间, price: 按价格, name: 按名称） |

**响应格式**：
```json
{
  "items": [
    {
      "id": 1,
      "item_name": "稀有武器",
      "item_count": 1,
      "item_quality": 5,
      "start_price": 10000,
      "buyout_price": 50000,
      "current_price": 15000,
      "highest_bidder_id": 2,
      "highest_bidder_name": "玩家2",
      "start_time": "2023-06-01T10:00:00Z",
      "end_time": "2023-06-02T10:00:00Z",
      "status": 1,
      "category": 1,
      "seller_id": 1,
      "seller_name": "玩家1",
      "time_left": 3600
    }
  ],
  "total": 100,
  "page": 1,
  "page_size": 20
}
```

**响应字段说明**：

| 字段名 | 类型 | 描述 |
|---------|------|------|
| `items` | array | 拍卖物品列表 |
| `items[].id` | number | 拍卖ID |
| `items[].item_name` | string | 物品名称 |
| `items[].item_count` | number | 物品数量 |
| `items[].item_quality` | number | 物品品质 |
| `items[].start_price` | number | 起拍价 |
| `items[].buyout_price` | number | 一口价 |
| `items[].current_price` | number | 当前价格 |
| `items[].highest_bidder_id` | number | 最高出价者ID |
| `items[].highest_bidder_name` | string | 最高出价者名称 |
| `items[].start_time` | string | 开始时间 |
| `items[].end_time` | string | 结束时间 |
| `items[].status` | number | 状态（1: 拍卖中） |
| `items[].category` | number | 分类ID |
| `items[].seller_id` | number | 卖家ID |
| `items[].seller_name` | string | 卖家名称 |
| `items[].time_left` | number | 剩余时间（秒） |
| `total` | number | 总数量 |
| `page` | number | 当前页码 |
| `page_size` | number | 每页数量 |

### 3.2 创建拍卖

**接口路径**：`/api/auction/create`
**请求方法**：POST
**认证要求**：是

**请求参数**：
```json
{
  "item_id": 1,
  "item_count": 1,
  "start_price": 10000,
  "buyout_price": 50000,
  "duration": 24,
  "category": 1
}
```

**请求参数说明**：

| 参数名 | 类型 | 必选 | 描述 |
|---------|------|------|------|
| `item_id` | number | 是 | 物品ID |
| `item_count` | number | 是 | 物品数量 |
| `start_price` | number | 是 | 起拍价 |
| `buyout_price` | number | 否 | 一口价（0表示无一口价） |
| `duration` | number | 是 | 拍卖时长（小时） |
| `category` | number | 是 | 分类ID |

**响应格式**：
```json
{
  "code": 0,
  "message": "Auction created successfully",
  "auction_id": 1
}
```

**响应字段说明**：

| 字段名 | 类型 | 描述 |
|---------|------|------|
| `code` | number | 状态码（0: 成功, 非0: 失败） |
| `message` | string | 提示信息 |
| `auction_id` | number | 拍卖ID |

### 3.3 竞拍

**接口路径**：`/api/auction/bid`
**请求方法**：POST
**认证要求**：是

**请求参数**：
```json
{
  "auction_id": 1,
  "bid_price": 20000
}
```

**请求参数说明**：

| 参数名 | 类型 | 必选 | 描述 |
|---------|------|------|------|
| `auction_id` | number | 是 | 拍卖ID |
| `bid_price` | number | 是 | 竞拍价格 |

**响应格式**：
```json
{
  "code": 0,
  "message": "Bid placed successfully",
  "auction_id": 1
}
```

**响应字段说明**：

| 字段名 | 类型 | 描述 |
|---------|------|------|
| `code` | number | 状态码（0: 成功, 非0: 失败） |
| `message` | string | 提示信息 |
| `auction_id` | number | 拍卖ID |

### 3.4 一口价购买

**接口路径**：`/api/auction/buyout`
**请求方法**：POST
**认证要求**：是

**请求参数**：
```json
{
  "auction_id": 1
}
```

**请求参数说明**：

| 参数名 | 类型 | 必选 | 描述 |
|---------|------|------|------|
| `auction_id` | number | 是 | 拍卖ID |

**响应格式**：
```json
{
  "code": 0,
  "message": "Auction bought out successfully",
  "auction_id": 1
}
```

**响应字段说明**：

| 字段名 | 类型 | 描述 |
|---------|------|------|
| `code` | number | 状态码（0: 成功, 非0: 失败） |
| `message` | string | 提示信息 |
| `auction_id` | number | 拍卖ID |

### 3.5 取消拍卖

**接口路径**：`/api/auction/cancel`
**请求方法**：POST
**认证要求**：是

**请求参数**：
```json
{
  "auction_id": 1
}
```

**请求参数说明**：

| 参数名 | 类型 | 必选 | 描述 |
|---------|------|------|------|
| `auction_id` | number | 是 | 拍卖ID |

**响应格式**：
```json
{
  "code": 0,
  "message": "Auction cancelled successfully",
  "auction_id": 1
}
```

**响应字段说明**：

| 字段名 | 类型 | 描述 |
|---------|------|------|
| `code` | number | 状态码（0: 成功, 非0: 失败） |
| `message` | string | 提示信息 |
| `auction_id` | number | 拍卖ID |

### 3.6 获取我的拍卖

**接口路径**：`/api/auction/my`
**请求方法**：GET
**认证要求**：是

**请求参数**：

| 参数名 | 类型 | 必选 | 默认值 | 描述 |
|---------|------|------|--------|------|
| `page` | int | 否 | 1 | 页码 |
| `page_size` | int | 否 | 20 | 每页数量 |

**响应格式**：
```json
{
  "selling_items": [
    {
      "id": 1,
      "item_name": "稀有武器",
      "item_count": 1,
      "item_quality": 5,
      "start_price": 10000,
      "buyout_price": 50000,
      "current_price": 15000,
      "highest_bidder_id": 2,
      "highest_bidder_name": "玩家2",
      "start_time": "2023-06-01T10:00:00Z",
      "end_time": "2023-06-02T10:00:00Z",
      "status": 1,
      "category": 1,
      "seller_id": 1,
      "seller_name": "玩家1",
      "time_left": 3600
    }
  ],
  "bidding_items": [
    {
      "id": 2,
      "item_name": "稀有防具",
      "item_count": 1,
      "item_quality": 5,
      "start_price": 8000,
      "buyout_price": 40000,
      "current_price": 12000,
      "highest_bidder_id": 1,
      "highest_bidder_name": "玩家1",
      "start_time": "2023-06-01T11:00:00Z",
      "end_time": "2023-06-02T11:00:00Z",
      "status": 1,
      "category": 2,
      "seller_id": 3,
      "seller_name": "玩家3",
      "time_left": 39600
    }
  ],
  "completed_items": [
    {
      "id": 3,
      "item_name": "稀有饰品",
      "item_count": 1,
      "item_quality": 5,
      "start_price": 5000,
      "buyout_price": 25000,
      "current_price": 20000,
      "highest_bidder_id": 4,
      "highest_bidder_name": "玩家4",
      "start_time": "2023-05-30T10:00:00Z",
      "end_time": "2023-05-31T10:00:00Z",
      "status": 2,
      "category": 3,
      "seller_id": 1,
      "seller_name": "玩家1",
      "time_left": 0
    }
  ]
}
```

**响应字段说明**：

| 字段名 | 类型 | 描述 |
|---------|------|------|
| `selling_items` | array | 我上架的拍卖物品 |
| `bidding_items` | array | 我参与竞拍的拍卖物品 |
| `completed_items` | array | 我已完成的拍卖物品 |
| 子字段 | 同获取拍卖列表接口 |

## 4. gRPC接口详情

### 4.1 CreateAuction

**功能**：创建拍卖

**请求消息**：
```protobuf
message CreateAuctionRequest {
    uint64 item_id = 1;
    int32 item_count = 2;
    uint64 start_price = 3;
    uint64 buyout_price = 4;
    int32 duration = 5; // 拍卖时长（小时）
    int32 category = 6;
}
```

**响应消息**：
```protobuf
message AuctionResponse {
    int32 code = 1;
    string message = 2;
    uint64 auction_id = 3;
}
```

### 4.2 Bid

**功能**：竞拍拍卖物品

**请求消息**：
```protobuf
message BidRequest {
    uint64 auction_id = 1;
    uint64 bid_price = 2;
}
```

**响应消息**：
```protobuf
message AuctionResponse {
    int32 code = 1;
    string message = 2;
    uint64 auction_id = 3;
}
```

### 4.3 Buyout

**功能**：一口价购买拍卖物品

**请求消息**：
```protobuf
message BuyoutRequest {
    uint64 auction_id = 1;
}
```

**响应消息**：
```protobuf
message AuctionResponse {
    int32 code = 1;
    string message = 2;
    uint64 auction_id = 3;
}
```

### 4.4 Cancel

**功能**：取消拍卖

**请求消息**：
```protobuf
message CancelRequest {
    uint64 auction_id = 1;
}
```

**响应消息**：
```protobuf
message AuctionResponse {
    int32 code = 1;
    string message = 2;
    uint64 auction_id = 3;
}
```

### 4.5 GetAuctionList

**功能**：获取拍卖物品列表

**请求消息**：
```protobuf
message AuctionListRequest {
    int32 page = 1;
    int32 page_size = 2;
    int32 category = 3;
    string sort = 4; // time, price, name
}
```

**响应消息**：
```protobuf
message AuctionListResponse {
    repeated AuctionItemResponse items = 1;
    int32 total = 2;
    int32 page = 3;
    int32 page_size = 4;
}

message AuctionItemResponse {
    uint64 id = 1;
    string item_name = 2;
    int32 item_count = 3;
    int32 item_quality = 4;
    uint64 start_price = 5;
    uint64 buyout_price = 6;
    uint64 current_price = 7;
    uint64 highest_bidder_id = 8;
    string highest_bidder_name = 9;
    string start_time = 10;
    string end_time = 11;
    AuctionStatus status = 12;
    int32 category = 13;
    uint64 seller_id = 14;
    string seller_name = 15;
    uint64 time_left = 16; // 剩余时间（秒）
}

enum AuctionStatus {
    AUCTION_STATUS_UNKNOWN = 0;
    AUCTION_STATUS_ACTIVE = 1;      // 拍卖中
    AUCTION_STATUS_COMPLETED = 2;   // 已成交
    AUCTION_STATUS_CANCELLED = 3;   // 已取消
    AUCTION_STATUS_EXPIRED = 4;     // 流拍
}
```

### 4.6 GetMyAuctions

**功能**：获取我的拍卖物品

**请求消息**：
```protobuf
message MyAuctionsRequest {
    int32 page = 1;
    int32 page_size = 2;
}
```

**响应消息**：
```protobuf
message MyAuctionsResponse {
    repeated AuctionItemResponse selling_items = 1;
    repeated AuctionItemResponse bidding_items = 2;
    repeated AuctionItemResponse completed_items = 3;
}
```

## 5. 错误码定义

| 错误码 | 描述 | 解决方案 |
|---------|------|----------|
| 0 | 成功 | - |
| 1 | 参数错误 | 检查请求参数是否正确 |
| 2 | 物品不存在 | 检查物品ID是否正确 |
| 3 | 物品数量不足 | 确保物品数量足够 |
| 4 | 货币不足 | 确保账户货币足够 |
| 5 | 拍卖不存在 | 检查拍卖ID是否正确 |
| 6 | 拍卖未激活 | 拍卖已结束或已取消 |
| 7 | 拍卖已过期 | 拍卖已超过结束时间 |
| 8 | 权限不足 | 确保您是拍卖的卖家 |
| 9 | 出价过低 | 出价必须高于当前价格 |
| 10 | 一口价不可用 | 该拍卖未设置一口价 |
| 11 | 拍卖数量限制 | 已达到最大拍卖物品数量 |
| 12 | 系统错误 | 请稍后重试或联系客服 |

## 6. 认证与授权

### 6.1 HTTP认证

- **认证方式**：JWT Token
- **认证头**：`Authorization: Bearer <token>`
- **权限验证**：
  - 创建拍卖：验证用户是否拥有指定物品
  - 竞拍：验证用户是否有足够的货币
  - 一口价购买：验证用户是否有足够的货币
  - 取消拍卖：验证用户是否是拍卖的卖家
  - 获取我的拍卖：验证用户身份

### 6.2 gRPC认证

- **认证方式**：基于Context的认证
- **权限验证**：同HTTP认证

## 7. 接口使用示例

### 7.1 HTTP接口示例

#### 7.1.1 获取拍卖列表

**请求**：
```bash
GET /api/auction/list?page=1&page_size=20&category=1&sort=time
```

**响应**：
```json
{
  "items": [
    {
      "id": 1,
      "item_name": "稀有武器",
      "item_count": 1,
      "item_quality": 5,
      "start_price": 10000,
      "buyout_price": 50000,
      "current_price": 15000,
      "highest_bidder_id": 2,
      "highest_bidder_name": "玩家2",
      "start_time": "2023-06-01T10:00:00Z",
      "end_time": "2023-06-02T10:00:00Z",
      "status": 1,
      "category": 1,
      "seller_id": 1,
      "seller_name": "玩家1",
      "time_left": 3600
    }
  ],
  "total": 100,
  "page": 1,
  "page_size": 20
}
```

#### 7.1.2 创建拍卖

**请求**：
```bash
POST /api/auction/create
Content-Type: application/json
Authorization: Bearer <token>

{
  "item_id": 1,
  "item_count": 1,
  "start_price": 10000,
  "buyout_price": 50000,
  "duration": 24,
  "category": 1
}
```

**响应**：
```json
{
  "code": 0,
  "message": "Auction created successfully",
  "auction_id": 1
}
```

#### 7.1.3 竞拍

**请求**：
```bash
POST /api/auction/bid
Content-Type: application/json
Authorization: Bearer <token>

{
  "auction_id": 1,
  "bid_price": 20000
}
```

**响应**：
```json
{
  "code": 0,
  "message": "Bid placed successfully",
  "auction_id": 1
}
```

### 7.2 gRPC接口示例

#### 7.2.1 创建拍卖

**代码示例**：
```go
package main

import (
    "context"
    "fmt"
    "log"

    "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
    "google.golang.org/grpc"
)

func main() {
    // 连接gRPC服务器
    conn, err := grpc.Dial("localhost:50051", grpc.WithInsecure())
    if err != nil {
        log.Fatalf("Failed to connect: %v", err)
    }
    defer conn.Close()

    // 创建客户端
    client := dnfv1.NewAuctionServiceClient(conn)

    // 创建拍卖请求
    req := &dnfv1.CreateAuctionRequest{
        ItemId:      1,
        ItemCount:   1,
        StartPrice:  10000,
        BuyoutPrice: 50000,
        Duration:    24,
        Category:    1,
    }

    // 调用CreateAuction
    resp, err := client.CreateAuction(context.Background(), req)
    if err != nil {
        log.Fatalf("CreateAuction failed: %v", err)
    }

    fmt.Printf("CreateAuction response: code=%d, message=%s, auction_id=%d\n", 
        resp.Code, resp.Message, resp.AuctionId)
}
```

## 8. 最佳实践

### 8.1 客户端实现

- **错误处理**：正确处理API返回的错误码和错误消息
- **重试机制**：对网络错误等临时错误实现重试机制
- **缓存策略**：缓存拍卖列表等频繁访问的数据，减少API调用
- **批量请求**：合理使用分页，避免一次性请求过多数据
- **实时更新**：使用WebSocket或轮询机制，实时更新拍卖状态

### 8.2 服务端实现

- **参数验证**：严格验证所有API输入参数
- **速率限制**：实现API速率限制，防止恶意请求
- **日志记录**：记录所有API调用和关键操作的日志
- **监控告警**：监控API的响应时间和错误率
- **安全防护**：防止SQL注入、XSS等安全攻击

### 8.3 性能优化

- **数据库索引**：为常用查询字段添加索引
- **缓存**：使用Redis等缓存热点数据
- **异步处理**：非关键操作使用异步处理
- **连接池**：使用数据库连接池，减少连接开销
- **负载均衡**：部署多个实例，使用负载均衡器分发请求

## 9. 兼容性说明

### 9.1 版本兼容性

- **API版本**：当前版本为v1
- **向后兼容**：确保API变更不破坏现有客户端
- **版本控制**：通过URL路径或请求头进行版本控制

### 9.2 数据兼容性

- **数据格式**：保持JSON和Protobuf消息格式的一致性
- **字段类型**：确保字段类型在不同语言中兼容
- **默认值**：为可选字段设置合理的默认值

## 10. 总结

本文档详细描述了拍卖系统的API接口，包括HTTP接口和gRPC接口。这些接口提供了完整的拍卖功能，包括物品拍卖、竞拍、一口价购买、拍卖管理、查询等。通过这些接口，前端应用和其他系统可以与拍卖系统进行交互，实现完整的拍卖流程。

在使用这些接口时，客户端应该注意错误处理、重试机制、缓存策略等最佳实践，服务端应该注意参数验证、速率限制、日志记录、监控告警等安全和性能优化措施。

通过标准化的API设计和良好的实现，拍卖系统可以为游戏玩家提供一个公平、透明、高效的交易平台，促进游戏内经济的健康发展。