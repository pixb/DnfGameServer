# 市场系统 - Go实现方案

## 1. 数据模型

### 1.1 市场物品

```go
type MarketItem struct {
    ID           uint64    `gorm:"primaryKey;column:id" json:"id"`
    SellerGUID   uint64    `gorm:"column:sellerGuid;index" json:"seller_guid"`
    SellerName   string    `gorm:"column:sellerName;size:50" json:"seller_name"`
    ItemGUID     uint64    `gorm:"column:itemGuid;uniqueIndex" json:"item_guid"`
    ItemIndex    uint32    `gorm:"column:itemIndex;index" json:"item_index"`
    ItemCount    uint32    `gorm:"column:itemCount" json:"item_count"`
    Bind         bool      `gorm:"column:bind" json:"bind"`
    Quality      uint32    `gorm:"column:quality" json:"quality"`
    Level        uint32    `gorm:"column:level;index" json:"level"`
    Price        uint64    `gorm:"column:price;index" json:"price"`
    CurrencyType uint32    `gorm:"column:currencyType" json:"currency_type"`
    Status       uint32    `gorm:"column:status;default:0" json:"status"`
    CreateTime   time.Time `gorm:"column:createTime" json:"create_time"`
    ExpireTime   time.Time `gorm:"column:expireTime;index" json:"expire_time"`
    UpdateTime   time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (MarketItem) TableName() string {
    return "t_market_item"
}
```

### 1.2 市场交易记录

```go
type MarketRecord struct {
    ID          uint64    `gorm:"primaryKey;column:id" json:"id"`
    MarketID    uint64    `gorm:"column:marketId;index" json:"market_id"`
    SellerGUID  uint64    `gorm:"column:sellerGuid;index" json:"seller_guid"`
    BuyerGUID   uint64    `gorm:"column:buyerGuid;index" json:"buyer_guid"`
    ItemGUID    uint64    `gorm:"column:itemGuid" json:"item_guid"`
    ItemIndex   uint32    `gorm:"column:itemIndex" json:"item_index"`
    ItemCount   uint32    `gorm:"column:itemCount" json:"item_count"`
    Price       uint64    `gorm:"column:price" json:"price"`
    CurrencyType uint32   `gorm:"column:currencyType" json:"currency_type"`
    Tax         uint64    `gorm:"column:tax" json:"tax"`
    TradeTime   time.Time `gorm:"column:tradeTime" json:"trade_time"`
    CreateTime  time.Time `gorm:"column:createTime" json:"create_time"`
}

func (MarketRecord) TableName() string {
    return "t_market_record"
}
```

## 2. Protobuf 消息定义

```protobuf
syntax = "proto3";

package dnf.v1;

option go_package = "gen/dnf/v1";

import "dnf/v1/common.proto";

// 市场物品信息
message MarketItem {
    uint64 id = 1;
    uint64 seller_guid = 2;
    string seller_name = 3;
    uint64 item_guid = 4;
    uint32 item_index = 5;
    uint32 item_count = 6;
    bool bind = 7;
    uint32 quality = 8;
    uint32 level = 9;
    uint64 price = 10;
    uint32 currency_type = 11;
    uint64 create_time = 12;
    uint64 expire_time = 13;
}

// 市场列表请求
message MarketListRequest {
    uint32 page = 1;
    uint32 page_size = 2;
    uint32 item_type = 3;
    uint32 min_level = 4;
    uint32 max_level = 5;
    uint64 min_price = 6;
    uint64 max_price = 7;
}

// 市场列表响应
message MarketListResponse {
    repeated MarketItem items = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
}

// 物品上架请求
message MarketAddRequest {
    uint64 item_guid = 1;
    uint32 item_count = 2;
    uint64 price = 3;
    uint32 currency_type = 4;
}

// 物品上架响应
message MarketAddResponse {
    uint64 market_id = 1;
    uint32 error = 2;
    string message = 3;
}

// 物品下架请求
message MarketRemoveRequest {
    uint64 market_id = 1;
}

// 物品下架响应
message MarketRemoveResponse {
    uint32 error = 2;
    string message = 3;
}

// 物品购买请求
message MarketBuyRequest {
    uint64 market_id = 1;
    uint32 buy_count = 2;
}

// 物品购买响应
message MarketBuyResponse {
    MarketItem item = 1;
    uint32 error = 2;
    string message = 3;
}

// 市场搜索请求
message MarketSearchRequest {
    string item_name = 1;
    uint32 item_type = 2;
    uint32 min_level = 3;
    uint32 max_level = 4;
    uint64 min_price = 5;
    uint64 max_price = 6;
    uint32 page = 7;
    uint32 page_size = 8;
}

// 市场搜索响应
message MarketSearchResponse {
    repeated MarketItem items = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
}

// 我的上架列表请求
message MarketMyListRequest {
    uint32 page = 1;
    uint32 page_size = 2;
}

// 我的上架列表响应
message MarketMyListResponse {
    repeated MarketItem items = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
}

// 市场系统服务
service MarketService {
    // 市场列表查询
    rpc MarketList(MarketListRequest) returns (MarketListResponse);
    
    // 物品上架
    rpc MarketAdd(MarketAddRequest) returns (MarketAddResponse);
    
    // 物品下架
    rpc MarketRemove(MarketRemoveRequest) returns (MarketRemoveResponse);
    
    // 物品购买
    rpc MarketBuy(MarketBuyRequest) returns (MarketBuyResponse);
    
    // 市场搜索
    rpc MarketSearch(MarketSearchRequest) returns (MarketSearchResponse);
    
    // 我的上架列表
    rpc MarketMyList(MarketMyListRequest) returns (MarketMyListResponse);
}
```

## 3. 核心 Handler 实现

### 3.1 市场列表处理器

```go
func (h *MarketHandler) MarketListHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.MarketListRequest)
    if !ok {
        return
    }

    items, total, err := h.marketService.GetMarketList(context.Background(), req.Page, req.PageSize, req.ItemType, req.MinLevel, req.MaxLevel, req.MinPrice, req.MaxPrice)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get market list")
        return
    }

    resp := &dnfv1.MarketListResponse{
        Items:  items,
        Total:  total,
        Error:  0,
    }
    sess.Send(resp)
}
```

### 3.2 物品上架处理器

```go
func (h *MarketHandler) MarketAddHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.MarketAddRequest)
    if !ok {
        return
    }

    marketID, err := h.marketService.AddItemToMarket(context.Background(), sess.RoleID, req.ItemGuid, req.ItemCount, req.Price, req.CurrencyType)
    if err != nil {
        h.sendError(sess, req, 1, "failed to add item to market")
        return
    }

    resp := &dnfv1.MarketAddResponse{
        MarketId: marketID,
        Error:    0,
    }
    sess.Send(resp)
}
```

### 3.3 物品购买处理器

```go
func (h *MarketHandler) MarketBuyHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.MarketBuyRequest)
    if !ok {
        return
    }

    item, err := h.marketService.BuyItemFromMarket(context.Background(), sess.RoleID, req.MarketId, req.BuyCount)
    if err != nil {
        h.sendError(sess, req, 1, "failed to buy item from market")
        return
    }

    resp := &dnfv1.MarketBuyResponse{
        Item:  item,
        Error: 0,
    }
    sess.Send(resp)
}
```

## 4. 业务逻辑服务

```go
type MarketService struct {
    store *store.Store
}

// 获取市场列表
func (s *MarketService) GetMarketList(ctx context.Context, page, pageSize, itemType, minLevel, maxLevel uint32, minPrice, maxPrice uint64) ([]*dnfv1.MarketItem, uint32, error) {
    // 实现获取市场列表逻辑
}

// 物品上架
func (s *MarketService) AddItemToMarket(ctx context.Context, roleID, itemGUID uint64, itemCount uint32, price uint64, currencyType uint32) (uint64, error) {
    // 实现物品上架逻辑
}

// 物品下架
func (s *MarketService) RemoveItemFromMarket(ctx context.Context, roleID, marketID uint64) error {
    // 实现物品下架逻辑
}

// 购买物品
func (s *MarketService) BuyItemFromMarket(ctx context.Context, buyerID, marketID uint64, buyCount uint32) (*dnfv1.MarketItem, error) {
    // 实现购买物品逻辑
}

// 搜索市场
func (s *MarketService) SearchMarket(ctx context.Context, itemName string, itemType, minLevel, maxLevel uint32, minPrice, maxPrice uint64, page, pageSize uint32) ([]*dnfv1.MarketItem, uint32, error) {
    // 实现搜索市场逻辑
}

// 获取我的上架列表
func (s *MarketService) GetMyMarketList(ctx context.Context, roleID uint64, page, pageSize uint32) ([]*dnfv1.MarketItem, uint32, error) {
    // 实现获取我的上架列表逻辑
}
```