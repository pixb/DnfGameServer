# 市场系统 - API文档

## 1. HTTP 接口

| API路径 | 方法 | 模块/文件 | 类型 | 功能描述 | 请求体 (JSON) | 成功响应 (200 OK) |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `/api/v1/market/list` | `GET` | `handler/market_handler.go` | `Router` | 市场列表查询 | `{"page": 1, "page_size": 20, "item_type": 1, "min_level": 1, "max_level": 100, "min_price": 0, "max_price": 1000000}` | `{"items": [...], "total": 100, "error": 0}` |
| `/api/v1/market/add` | `POST` | `handler/market_handler.go` | `Router` | 物品上架 | `{"item_guid": 123456, "item_count": 1, "price": 10000, "currency_type": 1}` | `{"market_id": 789012, "error": 0}` |
| `/api/v1/market/remove` | `POST` | `handler/market_handler.go` | `Router` | 物品下架 | `{"market_id": 789012}` | `{"error": 0}` |
| `/api/v1/market/buy` | `POST` | `handler/market_handler.go` | `Router` | 物品购买 | `{"market_id": 789012, "buy_count": 1}` | `{"item": {...}, "error": 0}` |
| `/api/v1/market/search` | `GET` | `handler/market_handler.go` | `Router` | 市场搜索 | `{"item_name": "剑", "item_type": 1, "min_level": 1, "max_level": 100, "min_price": 0, "max_price": 1000000, "page": 1, "page_size": 20}` | `{"items": [...], "total": 10, "error": 0}` |
| `/api/v1/market/my-list` | `GET` | `handler/market_handler.go` | `Router` | 我的上架列表 | `{"page": 1, "page_size": 20}` | `{"items": [...], "total": 5, "error": 0}` |

## 2. gRPC 接口

| 方法 | 服务 | 模块/文件 | 功能描述 | 请求 | 响应 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `MarketList` | `MarketService` | `proto/dnf/v1/market.proto` | 市场列表查询 | `MarketListRequest` | `MarketListResponse` |
| `MarketAdd` | `MarketService` | `proto/dnf/v1/market.proto` | 物品上架 | `MarketAddRequest` | `MarketAddResponse` |
| `MarketRemove` | `MarketService` | `proto/dnf/v1/market.proto` | 物品下架 | `MarketRemoveRequest` | `MarketRemoveResponse` |
| `MarketBuy` | `MarketService` | `proto/dnf/v1/market.proto` | 物品购买 | `MarketBuyRequest` | `MarketBuyResponse` |
| `MarketSearch` | `MarketService` | `proto/dnf/v1/market.proto` | 市场搜索 | `MarketSearchRequest` | `MarketSearchResponse` |
| `MarketMyList` | `MarketService` | `proto/dnf/v1/market.proto` | 我的上架列表 | `MarketMyListRequest` | `MarketMyListResponse` |