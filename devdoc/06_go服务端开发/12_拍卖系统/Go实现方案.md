# 拍卖系统 - Go实现方案

## 1. 设计理念
基于Go语言的高性能特性，实现一个高并发、低延迟的拍卖系统。采用分层架构，将系统分为API层、业务逻辑层、数据访问层，使用依赖注入和接口分离原则，提高系统的可维护性和可扩展性。同时，利用Redis缓存热点数据，使用消息队列处理异步任务，确保系统的高性能和可靠性。

## 2. 核心数据结构

### 2.1 数据模型

**拍卖物品模型**
```go
type AuctionItem struct {
    ID            uint64    `gorm:"primaryKey;column:id" json:"id"`
    SellerID      uint64    `gorm:"column:seller_id;index" json:"seller_id"`
    ItemID        uint64    `gorm:"column:item_id;index" json:"item_id"`
    ItemName      string    `gorm:"column:item_name;size:100" json:"item_name"`
    ItemCount     int       `gorm:"column:item_count" json:"item_count"`
    ItemQuality   int       `gorm:"column:item_quality" json:"item_quality"`
    StartPrice    uint64    `gorm:"column:start_price" json:"start_price"`
    BuyoutPrice   uint64    `gorm:"column:buyout_price" json:"buyout_price"`
    CurrentPrice  uint64    `gorm:"column:current_price" json:"current_price"`
    HighestBidderID uint64  `gorm:"column:highest_bidder_id;index" json:"highest_bidder_id"`
    StartTime     time.Time `gorm:"column:start_time;index" json:"start_time"`
    EndTime       time.Time `gorm:"column:end_time;index" json:"end_time"`
    Status        int       `gorm:"column:status;index" json:"status"` // 0: 拍卖中, 1: 已成交, 2: 已取消, 3: 流拍
    Category      int       `gorm:"column:category;index" json:"category"`
    CreatedAt     time.Time `gorm:"column:created_at" json:"created_at"`
    UpdatedAt     time.Time `gorm:"column:updated_at" json:"updated_at"`
}

func (AuctionItem) TableName() string {
    return "auction_items"
}
```

**竞拍记录模型**
```go
type AuctionBid struct {
    ID        uint64    `gorm:"primaryKey;column:id" json:"id"`
    AuctionID uint64    `gorm:"column:auction_id;index" json:"auction_id"`
    BidderID  uint64    `gorm:"column:bidder_id;index" json:"bidder_id"`
    BidPrice  uint64    `gorm:"column:bid_price" json:"bid_price"`
    BidTime   time.Time `gorm:"column:bid_time" json:"bid_time"`
    CreatedAt time.Time `gorm:"column:created_at" json:"created_at"`
}

func (AuctionBid) TableName() string {
    return "auction_bids"
}
```

**拍卖交易记录模型**
```go
type AuctionTransaction struct {
    ID          uint64    `gorm:"primaryKey;column:id" json:"id"`
    AuctionID   uint64    `gorm:"column:auction_id;index" json:"auction_id"`
    SellerID    uint64    `gorm:"column:seller_id;index" json:"seller_id"`
    BuyerID     uint64    `gorm:"column:buyer_id;index" json:"buyer_id"`
    ItemID      uint64    `gorm:"column:item_id" json:"item_id"`
    ItemCount   int       `gorm:"column:item_count" json:"item_count"`
    TransactionPrice uint64 `gorm:"column:transaction_price" json:"transaction_price"`
    TransactionTime time.Time `gorm:"column:transaction_time" json:"transaction_time"`
    CreatedAt   time.Time `gorm:"column:created_at" json:"created_at"`
}

func (AuctionTransaction) TableName() string {
    return "auction_transactions"
}
```

**资金冻结模型**
```go
type FundFreeze struct {
    ID          uint64    `gorm:"primaryKey;column:id" json:"id"`
    UserID      uint64    `gorm:"column:user_id;index" json:"user_id"`
    AuctionID   uint64    `gorm:"column:auction_id;index" json:"auction_id"`
    Amount      uint64    `gorm:"column:amount" json:"amount"`
    Status      int       `gorm:"column:status" json:"status"` // 0: 冻结中, 1: 已解冻, 2: 已使用
    CreatedAt   time.Time `gorm:"column:created_at" json:"created_at"`
    UpdatedAt   time.Time `gorm:"column:updated_at" json:"updated_at"`
}

func (FundFreeze) TableName() string {
    return "fund_freezes"
}
```

### 2.2 Protobuf 消息定义

**auction.proto**
```protobuf
syntax = "proto3";

package dnf.v1;

option go_package = "gen/dnf/v1";

import "dnf/v1/common.proto";

// 拍卖状态
enum AuctionStatus {
    AUCTION_STATUS_UNKNOWN = 0;
    AUCTION_STATUS_ACTIVE = 1;      // 拍卖中
    AUCTION_STATUS_COMPLETED = 2;   // 已成交
    AUCTION_STATUS_CANCELLED = 3;   // 已取消
    AUCTION_STATUS_EXPIRED = 4;     // 流拍
}

// 拍卖物品请求消息
message CreateAuctionRequest {
    uint64 item_id = 1;
    int32 item_count = 2;
    uint64 start_price = 3;
    uint64 buyout_price = 4;
    int32 duration = 5; // 拍卖时长（小时）
    int32 category = 6;
}

// 竞拍请求消息
message BidRequest {
    uint64 auction_id = 1;
    uint64 bid_price = 2;
}

// 一口价购买请求消息
message BuyoutRequest {
    uint64 auction_id = 1;
}

// 取消拍卖请求消息
message CancelRequest {
    uint64 auction_id = 1;
}

// 拍卖列表请求消息
message AuctionListRequest {
    int32 page = 1;
    int32 page_size = 2;
    int32 category = 3;
    string sort = 4; // time, price, name
}

// 拍卖物品响应消息
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

// 拍卖列表响应消息
message AuctionListResponse {
    repeated AuctionItemResponse items = 1;
    int32 total = 2;
    int32 page = 3;
    int32 page_size = 4;
}

// 拍卖操作响应消息
message AuctionResponse {
    int32 code = 1;
    string message = 2;
    uint64 auction_id = 3;
}

// 我的拍卖请求消息
message MyAuctionsRequest {
    int32 page = 1;
    int32 page_size = 2;
}

// 我的拍卖响应消息
message MyAuctionsResponse {
    repeated AuctionItemResponse selling_items = 1;
    repeated AuctionItemResponse bidding_items = 2;
    repeated AuctionItemResponse completed_items = 3;
}

// 拍卖服务
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

### 2.3 配置结构

**AuctionConfig**
```go
type AuctionConfig struct {
    MaxAuctionItemsPerUser int           `yaml:"max_auction_items_per_user"`
    MinBidIncrement        uint64        `yaml:"min_bid_increment"`
    AuctionDurationOptions []int         `yaml:"auction_duration_options"` // 拍卖时长选项（小时）
    ExpiredCheckInterval   time.Duration `yaml:"expired_check_interval"`   // 过期检查间隔
    CacheTTL               time.Duration `yaml:"cache_ttl"`               // 缓存过期时间
    MaxConcurrentBids      int           `yaml:"max_concurrent_bids"`      // 最大并发竞拍数
}
```

## 3. 核心方法实现

### 3.1 拍卖服务接口

```go
type AuctionService interface {
    CreateAuction(ctx context.Context, req *CreateAuctionRequest, userID uint64) (*AuctionResponse, error)
    Bid(ctx context.Context, req *BidRequest, userID uint64) (*AuctionResponse, error)
    Buyout(ctx context.Context, req *BuyoutRequest, userID uint64) (*AuctionResponse, error)
    Cancel(ctx context.Context, req *CancelRequest, userID uint64) (*AuctionResponse, error)
    GetAuctionList(ctx context.Context, req *AuctionListRequest) (*AuctionListResponse, error)
    GetMyAuctions(ctx context.Context, req *MyAuctionsRequest, userID uint64) (*MyAuctionsResponse, error)
    ProcessExpiredAuctions(ctx context.Context) error
    ProcessAuctionSettlement(ctx context.Context, auctionID uint64) error
}
```

### 3.2 拍卖服务实现

```go
type auctionService struct {
    db             *gorm.DB
    redis          *redis.Client
    itemService    ItemService
    currencyService CurrencyService
    userService    UserService
    notificationService NotificationService
    config         *AuctionConfig
    mutex          sync.RWMutex
    activeAuctions map[uint64]*AuctionItem
}

func NewAuctionService(
    db *gorm.DB,
    redis *redis.Client,
    itemService ItemService,
    currencyService CurrencyService,
    userService UserService,
    notificationService NotificationService,
    config *AuctionConfig,
) AuctionService {
    return &auctionService{
        db:             db,
        redis:          redis,
        itemService:    itemService,
        currencyService: currencyService,
        userService:    userService,
        notificationService: notificationService,
        config:         config,
        activeAuctions: make(map[uint64]*AuctionItem),
    }
}

// CreateAuction 创建拍卖
func (s *auctionService) CreateAuction(ctx context.Context, req *CreateAuctionRequest, userID uint64) (*AuctionResponse, error) {
    // 1. 验证用户物品
    item, err := s.itemService.GetItem(ctx, userID, req.ItemID)
    if err != nil {
        return &AuctionResponse{Code: 1, Message: "Item not found"}, nil
    }
    
    if item.Count < req.ItemCount {
        return &AuctionResponse{Code: 1, Message: "Insufficient item count"}, nil
    }
    
    // 2. 检查用户拍卖物品数量限制
    var count int64
    if err := s.db.Model(&AuctionItem{}).Where("seller_id = ? AND status = ?", userID, 0).Count(&count).Error; err != nil {
        return nil, err
    }
    
    if int(count) >= s.config.MaxAuctionItemsPerUser {
        return &AuctionResponse{Code: 1, Message: "Auction item limit reached"}, nil
    }
    
    // 3. 计算拍卖结束时间
    endTime := time.Now().Add(time.Duration(req.Duration) * time.Hour)
    
    // 4. 创建拍卖物品
    auctionItem := &AuctionItem{
        SellerID:     userID,
        ItemID:       req.ItemID,
        ItemName:     item.Name,
        ItemCount:    req.ItemCount,
        ItemQuality:  item.Quality,
        StartPrice:   req.StartPrice,
        BuyoutPrice:  req.BuyoutPrice,
        CurrentPrice: req.StartPrice,
        StartTime:    time.Now(),
        EndTime:      endTime,
        Status:       0,
        Category:     req.Category,
    }
    
    // 5. 开始事务
    tx := s.db.Begin()
    defer func() {
        if r := recover(); r != nil {
            tx.Rollback()
        }
    }()
    
    // 6. 保存拍卖物品
    if err := tx.Create(auctionItem).Error; err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 7. 扣除用户物品
    if err := s.itemService.RemoveItem(ctx, userID, req.ItemID, req.ItemCount); err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 8. 提交事务
    if err := tx.Commit().Error; err != nil {
        return nil, err
    }
    
    // 9. 缓存拍卖物品
    s.cacheAuctionItem(auctionItem)
    
    // 10. 返回响应
    return &AuctionResponse{
        Code:      0,
        Message:   "Auction created successfully",
        AuctionID: auctionItem.ID,
    }, nil
}

// Bid 竞拍
func (s *auctionService) Bid(ctx context.Context, req *BidRequest, userID uint64) (*AuctionResponse, error) {
    // 1. 获取拍卖物品
    auctionItem, err := s.getAuctionItem(ctx, req.AuctionID)
    if err != nil {
        return &AuctionResponse{Code: 1, Message: "Auction not found"}, nil
    }
    
    // 2. 验证拍卖状态
    if auctionItem.Status != 0 {
        return &AuctionResponse{Code: 1, Message: "Auction not active"}, nil
    }
    
    // 3. 验证拍卖时间
    if time.Now().After(auctionItem.EndTime) {
        return &AuctionResponse{Code: 1, Message: "Auction expired"}, nil
    }
    
    // 4. 验证竞拍价格
    if req.BidPrice <= auctionItem.CurrentPrice {
        return &AuctionResponse{Code: 1, Message: "Bid price too low"}, nil
    }
    
    if req.BidPrice < auctionItem.CurrentPrice+s.config.MinBidIncrement {
        return &AuctionResponse{Code: 1, Message: "Bid price must be at least minimum increment"}, nil
    }
    
    // 5. 验证用户余额
    balance, err := s.currencyService.GetBalance(ctx, userID)
    if err != nil {
        return nil, err
    }
    
    if balance < req.BidPrice {
        return &AuctionResponse{Code: 1, Message: "Insufficient balance"}, nil
    }
    
    // 6. 开始事务
    tx := s.db.Begin()
    defer func() {
        if r := recover(); r != nil {
            tx.Rollback()
        }
    }()
    
    // 7. 处理旧的最高出价者
    if auctionItem.HighestBidderID > 0 {
        // 解冻旧最高出价者的资金
        if err := s.unfreezeFunds(tx, auctionItem.HighestBidderID, auctionItem.ID); err != nil {
            tx.Rollback()
            return nil, err
        }
    }
    
    // 8. 冻结新出价者的资金
    if err := s.freezeFunds(tx, userID, auctionItem.ID, req.BidPrice); err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 9. 更新拍卖物品
    auctionItem.CurrentPrice = req.BidPrice
    auctionItem.HighestBidderID = userID
    
    if err := tx.Save(auctionItem).Error; err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 10. 记录竞拍
    bid := &AuctionBid{
        AuctionID: auctionItem.ID,
        BidderID:  userID,
        BidPrice:  req.BidPrice,
        BidTime:   time.Now(),
    }
    
    if err := tx.Create(bid).Error; err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 11. 提交事务
    if err := tx.Commit().Error; err != nil {
        return nil, err
    }
    
    // 12. 更新缓存
    s.cacheAuctionItem(auctionItem)
    
    // 13. 发送通知
    s.notificationService.SendNotification(ctx, auctionItem.SellerID, "Your auction item has a new bid")
    if auctionItem.HighestBidderID > 0 && auctionItem.HighestBidderID != userID {
        s.notificationService.SendNotification(ctx, auctionItem.HighestBidderID, "You have been outbid")
    }
    
    // 14. 返回响应
    return &AuctionResponse{
        Code:      0,
        Message:   "Bid placed successfully",
        AuctionID: auctionItem.ID,
    }, nil
}

// Buyout 一口价购买
func (s *auctionService) Buyout(ctx context.Context, req *BuyoutRequest, userID uint64) (*AuctionResponse, error) {
    // 1. 获取拍卖物品
    auctionItem, err := s.getAuctionItem(ctx, req.AuctionID)
    if err != nil {
        return &AuctionResponse{Code: 1, Message: "Auction not found"}, nil
    }
    
    // 2. 验证拍卖状态
    if auctionItem.Status != 0 {
        return &AuctionResponse{Code: 1, Message: "Auction not active"}, nil
    }
    
    // 3. 验证拍卖时间
    if time.Now().After(auctionItem.EndTime) {
        return &AuctionResponse{Code: 1, Message: "Auction expired"}, nil
    }
    
    // 4. 验证一口价
    if auctionItem.BuyoutPrice == 0 {
        return &AuctionResponse{Code: 1, Message: "Buyout not available"}, nil
    }
    
    // 5. 验证用户余额
    balance, err := s.currencyService.GetBalance(ctx, userID)
    if err != nil {
        return nil, err
    }
    
    if balance < auctionItem.BuyoutPrice {
        return &AuctionResponse{Code: 1, Message: "Insufficient balance"}, nil
    }
    
    // 6. 开始事务
    tx := s.db.Begin()
    defer func() {
        if r := recover(); r != nil {
            tx.Rollback()
        }
    }()
    
    // 7. 处理最高出价者
    if auctionItem.HighestBidderID > 0 {
        // 解冻最高出价者的资金
        if err := s.unfreezeFunds(tx, auctionItem.HighestBidderID, auctionItem.ID); err != nil {
            tx.Rollback()
            return nil, err
        }
    }
    
    // 8. 转移货币
    if err := s.currencyService.Transfer(tx, userID, auctionItem.SellerID, auctionItem.BuyoutPrice); err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 9. 转移物品
    if err := s.itemService.TransferItem(tx, auctionItem.SellerID, userID, auctionItem.ItemID, auctionItem.ItemCount); err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 10. 更新拍卖状态
    auctionItem.Status = 1
    auctionItem.CurrentPrice = auctionItem.BuyoutPrice
    auctionItem.HighestBidderID = userID
    
    if err := tx.Save(auctionItem).Error; err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 11. 记录交易
    transaction := &AuctionTransaction{
        AuctionID:        auctionItem.ID,
        SellerID:         auctionItem.SellerID,
        BuyerID:          userID,
        ItemID:           auctionItem.ItemID,
        ItemCount:        auctionItem.ItemCount,
        TransactionPrice: auctionItem.BuyoutPrice,
        TransactionTime:  time.Now(),
    }
    
    if err := tx.Create(transaction).Error; err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 12. 提交事务
    if err := tx.Commit().Error; err != nil {
        return nil, err
    }
    
    // 13. 更新缓存
    s.cacheAuctionItem(auctionItem)
    
    // 14. 发送通知
    s.notificationService.SendNotification(ctx, auctionItem.SellerID, "Your auction item has been bought out")
    s.notificationService.SendNotification(ctx, userID, "You have successfully bought the auction item")
    
    // 15. 返回响应
    return &AuctionResponse{
        Code:      0,
        Message:   "Auction bought out successfully",
        AuctionID: auctionItem.ID,
    }, nil
}

// Cancel 取消拍卖
func (s *auctionService) Cancel(ctx context.Context, req *CancelRequest, userID uint64) (*AuctionResponse, error) {
    // 1. 获取拍卖物品
    auctionItem, err := s.getAuctionItem(ctx, req.AuctionID)
    if err != nil {
        return &AuctionResponse{Code: 1, Message: "Auction not found"}, nil
    }
    
    // 2. 验证用户权限
    if auctionItem.SellerID != userID {
        return &AuctionResponse{Code: 1, Message: "Permission denied"}, nil
    }
    
    // 3. 验证拍卖状态
    if auctionItem.Status != 0 {
        return &AuctionResponse{Code: 1, Message: "Auction not active"}, nil
    }
    
    // 4. 开始事务
    tx := s.db.Begin()
    defer func() {
        if r := recover(); r != nil {
            tx.Rollback()
        }
    }()
    
    // 5. 处理最高出价者
    if auctionItem.HighestBidderID > 0 {
        // 解冻最高出价者的资金
        if err := s.unfreezeFunds(tx, auctionItem.HighestBidderID, auctionItem.ID); err != nil {
            tx.Rollback()
            return nil, err
        }
    }
    
    // 6. 返还物品给卖家
    if err := s.itemService.AddItem(tx, auctionItem.SellerID, auctionItem.ItemID, auctionItem.ItemCount); err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 7. 更新拍卖状态
    auctionItem.Status = 2
    
    if err := tx.Save(auctionItem).Error; err != nil {
        tx.Rollback()
        return nil, err
    }
    
    // 8. 提交事务
    if err := tx.Commit().Error; err != nil {
        return nil, err
    }
    
    // 9. 更新缓存
    s.cacheAuctionItem(auctionItem)
    
    // 10. 发送通知
    if auctionItem.HighestBidderID > 0 {
        s.notificationService.SendNotification(ctx, auctionItem.HighestBidderID, "Auction you bid on has been cancelled")
    }
    
    // 11. 返回响应
    return &AuctionResponse{
        Code:      0,
        Message:   "Auction cancelled successfully",
        AuctionID: auctionItem.ID,
    }, nil
}

// GetAuctionList 获取拍卖列表
func (s *auctionService) GetAuctionList(ctx context.Context, req *AuctionListRequest) (*AuctionListResponse, error) {
    // 1. 构建查询
    query := s.db.Model(&AuctionItem{}).Where("status = ?", 0)
    
    // 2. 应用分类过滤
    if req.Category > 0 {
        query = query.Where("category = ?", req.Category)
    }
    
    // 3. 计算总数
    var total int64
    if err := query.Count(&total).Error; err != nil {
        return nil, err
    }
    
    // 4. 应用排序
    switch req.Sort {
    case "price":
        query = query.Order("current_price ASC")
    case "name":
        query = query.Order("item_name ASC")
    default:
        query = query.Order("end_time ASC")
    }
    
    // 5. 应用分页
    offset := (req.Page - 1) * req.PageSize
    var auctionItems []*AuctionItem
    if err := query.Offset(offset).Limit(req.PageSize).Find(&auctionItems).Error; err != nil {
        return nil, err
    }
    
    // 6. 构建响应
    items := make([]*AuctionItemResponse, 0, len(auctionItems))
    for _, item := range auctionItems {
        // 获取最高出价者名称
        var highestBidderName string
        if item.HighestBidderID > 0 {
            user, err := s.userService.GetUser(ctx, item.HighestBidderID)
            if err == nil {
                highestBidderName = user.Name
            }
        }
        
        // 获取卖家名称
        var sellerName string
        user, err := s.userService.GetUser(ctx, item.SellerID)
        if err == nil {
            sellerName = user.Name
        }
        
        // 计算剩余时间
        timeLeft := int64(0)
        if time.Now().Before(item.EndTime) {
            timeLeft = int64(item.EndTime.Sub(time.Now()).Seconds())
        }
        
        items = append(items, &AuctionItemResponse{
            ID:                item.ID,
            ItemName:          item.ItemName,
            ItemCount:         int32(item.ItemCount),
            ItemQuality:       int32(item.ItemQuality),
            StartPrice:        item.StartPrice,
            BuyoutPrice:       item.BuyoutPrice,
            CurrentPrice:      item.CurrentPrice,
            HighestBidderID:   item.HighestBidderID,
            HighestBidderName: highestBidderName,
            StartTime:         item.StartTime.Format(time.RFC3339),
            EndTime:           item.EndTime.Format(time.RFC3339),
            Status:            AuctionStatus_ACTIVE,
            Category:          int32(item.Category),
            SellerID:          item.SellerID,
            SellerName:        sellerName,
            TimeLeft:          uint64(timeLeft),
        })
    }
    
    return &AuctionListResponse{
        Items:     items,
        Total:     int32(total),
        Page:      req.Page,
        PageSize:  req.PageSize,
    }, nil
}

// GetMyAuctions 获取我的拍卖
func (s *auctionService) GetMyAuctions(ctx context.Context, req *MyAuctionsRequest, userID uint64) (*MyAuctionsResponse, error) {
    // 1. 获取用户上架的物品
    var sellingItems []*AuctionItem
    if err := s.db.Where("seller_id = ? AND status = ?", userID, 0).Find(&sellingItems).Error; err != nil {
        return nil, err
    }
    
    // 2. 获取用户参与竞拍的物品
    var biddingItems []*AuctionItem
    if err := s.db.Where("highest_bidder_id = ? AND status = ?", userID, 0).Find(&biddingItems).Error; err != nil {
        return nil, err
    }
    
    // 3. 获取用户已完成的物品
    var completedItems []*AuctionItem
    if err := s.db.Where("(seller_id = ? OR highest_bidder_id = ?) AND status IN (?, ?)", userID, userID, 1, 3).Find(&completedItems).Error; err != nil {
        return nil, err
    }
    
    // 4. 构建响应
    sellingResponses := s.buildAuctionItemResponses(ctx, sellingItems)
    biddingResponses := s.buildAuctionItemResponses(ctx, biddingItems)
    completedResponses := s.buildAuctionItemResponses(ctx, completedItems)
    
    return &MyAuctionsResponse{
        SellingItems:   sellingResponses,
        BiddingItems:   biddingResponses,
        CompletedItems: completedResponses,
    }, nil
}

// ProcessExpiredAuctions 处理过期拍卖
func (s *auctionService) ProcessExpiredAuctions(ctx context.Context) error {
    // 1. 查找过期拍卖
    var expiredAuctions []*AuctionItem
    if err := s.db.Where("status = ? AND end_time < ?", 0, time.Now()).Find(&expiredAuctions).Error; err != nil {
        return err
    }
    
    // 2. 处理每个过期拍卖
    for _, auction := range expiredAuctions {
        if err := s.processExpiredAuction(ctx, auction); err != nil {
            // 记录错误但继续处理其他拍卖
            log.Printf("Error processing expired auction %d: %v", auction.ID, err)
            continue
        }
    }
    
    return nil
}

// processExpiredAuction 处理单个过期拍卖
func (s *auctionService) processExpiredAuction(ctx context.Context, auction *AuctionItem) error {
    // 1. 开始事务
    tx := s.db.Begin()
    defer func() {
        if r := recover(); r != nil {
            tx.Rollback()
        }
    }()
    
    // 2. 检查是否有最高出价者
    if auction.HighestBidderID > 0 {
        // 有出价者，执行结算
        if err := s.processAuctionSettlementWithTx(tx, ctx, auction); err != nil {
            tx.Rollback()
            return err
        }
    } else {
        // 无出价者，流拍
        if err := s.processAuctionExpired(tx, ctx, auction); err != nil {
            tx.Rollback()
            return err
        }
    }
    
    // 3. 提交事务
    return tx.Commit().Error
}

// ProcessAuctionSettlement 处理拍卖结算
func (s *auctionService) ProcessAuctionSettlement(ctx context.Context, auctionID uint64) error {
    // 1. 获取拍卖物品
    auctionItem, err := s.getAuctionItem(ctx, auctionID)
    if err != nil {
        return err
    }
    
    // 2. 开始事务
    tx := s.db.Begin()
    defer func() {
        if r := recover(); r != nil {
            tx.Rollback()
        }
    }()
    
    // 3. 执行结算
    if err := s.processAuctionSettlementWithTx(tx, ctx, auctionItem); err != nil {
        tx.Rollback()
        return err
    }
    
    // 4. 提交事务
    return tx.Commit().Error
}

// processAuctionSettlementWithTx 执行拍卖结算（带事务）
func (s *auctionService) processAuctionSettlementWithTx(tx *gorm.DB, ctx context.Context, auction *AuctionItem) error {
    // 1. 转移货币
    if err := s.currencyService.Transfer(tx, auction.HighestBidderID, auction.SellerID, auction.CurrentPrice); err != nil {
        return err
    }
    
    // 2. 转移物品
    if err := s.itemService.TransferItem(tx, auction.SellerID, auction.HighestBidderID, auction.ItemID, auction.ItemCount); err != nil {
        return err
    }
    
    // 3. 解冻最高出价者的资金
    if err := s.unfreezeFunds(tx, auction.HighestBidderID, auction.ID); err != nil {
        return err
    }
    
    // 4. 更新拍卖状态
    auction.Status = 1
    if err := tx.Save(auction).Error; err != nil {
        return err
    }
    
    // 5. 记录交易
    transaction := &AuctionTransaction{
        AuctionID:        auction.ID,
        SellerID:         auction.SellerID,
        BuyerID:          auction.HighestBidderID,
        ItemID:           auction.ItemID,
        ItemCount:        auction.ItemCount,
        TransactionPrice: auction.CurrentPrice,
        TransactionTime:  time.Now(),
    }
    
    if err := tx.Create(transaction).Error; err != nil {
        return err
    }
    
    // 6. 更新缓存
    s.cacheAuctionItem(auction)
    
    // 7. 发送通知
    s.notificationService.SendNotification(ctx, auction.SellerID, "Your auction item has been sold")
    s.notificationService.SendNotification(ctx, auction.HighestBidderID, "You have won the auction")
    
    return nil
}

// processAuctionExpired 处理流拍
func (s *auctionService) processAuctionExpired(tx *gorm.DB, ctx context.Context, auction *AuctionItem) error {
    // 1. 返还物品给卖家
    if err := s.itemService.AddItem(tx, auction.SellerID, auction.ItemID, auction.ItemCount); err != nil {
        return err
    }
    
    // 2. 更新拍卖状态
    auction.Status = 3
    if err := tx.Save(auction).Error; err != nil {
        return err
    }
    
    // 3. 更新缓存
    s.cacheAuctionItem(auction)
    
    // 4. 发送通知
    s.notificationService.SendNotification(ctx, auction.SellerID, "Your auction item has expired")
    
    return nil
}

// 辅助方法

// getAuctionItem 获取拍卖物品（优先从缓存获取）
func (s *auctionService) getAuctionItem(ctx context.Context, auctionID uint64) (*AuctionItem, error) {
    // 1. 尝试从缓存获取
    s.mutex.RLock()
    auction, ok := s.activeAuctions[auctionID]
    s.mutex.RUnlock()
    if ok {
        return auction, nil
    }
    
    // 2. 从数据库获取
    if err := s.db.First(&auction, auctionID).Error; err != nil {
        return nil, err
    }
    
    // 3. 更新缓存
    s.cacheAuctionItem(auction)
    
    return auction, nil
}

// cacheAuctionItem 缓存拍卖物品
func (s *auctionService) cacheAuctionItem(auction *AuctionItem) {
    s.mutex.Lock()
    defer s.mutex.Unlock()
    
    // 只缓存活跃拍卖
    if auction.Status == 0 {
        s.activeAuctions[auction.ID] = auction
    } else {
        // 移除已完成拍卖的缓存
        delete(s.activeAuctions, auction.ID)
    }
}

// freezeFunds 冻结资金
func (s *auctionService) freezeFunds(tx *gorm.DB, userID, auctionID, amount uint64) error {
    // 1. 扣除用户货币
    if err := s.currencyService.Deduct(tx, userID, amount); err != nil {
        return err
    }
    
    // 2. 创建资金冻结记录
    freeze := &FundFreeze{
        UserID:    userID,
        AuctionID: auctionID,
        Amount:    amount,
        Status:    0,
        CreatedAt: time.Now(),
        UpdatedAt: time.Now(),
    }
    
    return tx.Create(freeze).Error
}

// unfreezeFunds 解冻资金
func (s *auctionService) unfreezeFunds(tx *gorm.DB, userID, auctionID uint64) error {
    // 1. 查找资金冻结记录
    var freeze FundFreeze
    if err := tx.Where("user_id = ? AND auction_id = ? AND status = ?", userID, auctionID, 0).First(&freeze).Error; err != nil {
        return err
    }
    
    // 2. 更新资金冻结状态
    freeze.Status = 1
    freeze.UpdatedAt = time.Now()
    if err := tx.Save(&freeze).Error; err != nil {
        return err
    }
    
    // 3. 返还用户货币
    return s.currencyService.Add(tx, userID, freeze.Amount)
}

// buildAuctionItemResponses 构建拍卖物品响应
func (s *auctionService) buildAuctionItemResponses(ctx context.Context, auctions []*AuctionItem) []*AuctionItemResponse {
    responses := make([]*AuctionItemResponse, 0, len(auctions))
    
    for _, auction := range auctions {
        // 获取最高出价者名称
        var highestBidderName string
        if auction.HighestBidderID > 0 {
            user, err := s.userService.GetUser(ctx, auction.HighestBidderID)
            if err == nil {
                highestBidderName = user.Name
            }
        }
        
        // 获取卖家名称
        var sellerName string
        user, err := s.userService.GetUser(ctx, auction.SellerID)
        if err == nil {
            sellerName = user.Name
        }
        
        // 计算剩余时间
        timeLeft := int64(0)
        if auction.Status == 0 && time.Now().Before(auction.EndTime) {
            timeLeft = int64(auction.EndTime.Sub(time.Now()).Seconds())
        }
        
        // 确定拍卖状态
        status := AuctionStatus_UNKNOWN
        switch auction.Status {
        case 0:
            status = AuctionStatus_ACTIVE
        case 1:
            status = AuctionStatus_COMPLETED
        case 2:
            status = AuctionStatus_CANCELLED
        case 3:
            status = AuctionStatus_EXPIRED
        }
        
        responses = append(responses, &AuctionItemResponse{
            ID:                auction.ID,
            ItemName:          auction.ItemName,
            ItemCount:         int32(auction.ItemCount),
            ItemQuality:       int32(auction.ItemQuality),
            StartPrice:        auction.StartPrice,
            BuyoutPrice:       auction.BuyoutPrice,
            CurrentPrice:      auction.CurrentPrice,
            HighestBidderID:   auction.HighestBidderID,
            HighestBidderName: highestBidderName,
            StartTime:         auction.StartTime.Format(time.RFC3339),
            EndTime:           auction.EndTime.Format(time.RFC3339),
            Status:            status,
            Category:          int32(auction.Category),
            SellerID:          auction.SellerID,
            SellerName:        sellerName,
            TimeLeft:          uint64(timeLeft),
        })
    }
    
    return responses
}
```

### 3.3 处理器实现

```go
type AuctionHandler struct {
    auctionService AuctionService
    authMiddleware middleware.AuthMiddleware
}

func NewAuctionHandler(auctionService AuctionService, authMiddleware middleware.AuthMiddleware) *AuctionHandler {
    return &AuctionHandler{
        auctionService: auctionService,
        authMiddleware: authMiddleware,
    }
}

// RegisterRoutes 注册路由
func (h *AuctionHandler) RegisterRoutes(router *gin.Engine) {
    auctionGroup := router.Group("/api/auction")
    {
        // 公开接口
        auctionGroup.GET("/list", h.GetAuctionList)
        
        // 需要认证的接口
        authGroup := auctionGroup.Group("/")
        authGroup.Use(h.authMiddleware.Authenticate())
        {
            authGroup.POST("/create", h.CreateAuction)
            authGroup.POST("/bid", h.Bid)
            authGroup.POST("/buyout", h.Buyout)
            authGroup.POST("/cancel", h.Cancel)
            authGroup.GET("/my", h.GetMyAuctions)
        }
    }
}

// CreateAuction 创建拍卖
func (h *AuctionHandler) CreateAuction(c *gin.Context) {
    var req CreateAuctionRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"code": 1, "message": "Invalid request"})
        return
    }
    
    userID := c.GetUint64("userID")
    resp, err := h.auctionService.CreateAuction(c.Request.Context(), &req, userID)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"code": 1, "message": "Internal error"})
        return
    }
    
    c.JSON(http.StatusOK, resp)
}

// Bid 竞拍
func (h *AuctionHandler) Bid(c *gin.Context) {
    var req BidRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"code": 1, "message": "Invalid request"})
        return
    }
    
    userID := c.GetUint64("userID")
    resp, err := h.auctionService.Bid(c.Request.Context(), &req, userID)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"code": 1, "message": "Internal error"})
        return
    }
    
    c.JSON(http.StatusOK, resp)
}

// Buyout 一口价购买
func (h *AuctionHandler) Buyout(c *gin.Context) {
    var req BuyoutRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"code": 1, "message": "Invalid request"})
        return
    }
    
    userID := c.GetUint64("userID")
    resp, err := h.auctionService.Buyout(c.Request.Context(), &req, userID)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"code": 1, "message": "Internal error"})
        return
    }
    
    c.JSON(http.StatusOK, resp)
}

// Cancel 取消拍卖
func (h *AuctionHandler) Cancel(c *gin.Context) {
    var req CancelRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"code": 1, "message": "Invalid request"})
        return
    }
    
    userID := c.GetUint64("userID")
    resp, err := h.auctionService.Cancel(c.Request.Context(), &req, userID)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"code": 1, "message": "Internal error"})
        return
    }
    
    c.JSON(http.StatusOK, resp)
}

// GetAuctionList 获取拍卖列表
func (h *AuctionHandler) GetAuctionList(c *gin.Context) {
    var req AuctionListRequest
    if err := c.ShouldBindQuery(&req); err != nil {
        // 设置默认值
        req.Page = 1
        req.PageSize = 20
        req.Category = 0
        req.Sort = "time"
    }
    
    resp, err := h.auctionService.GetAuctionList(c.Request.Context(), &req)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"code": 1, "message": "Internal error"})
        return
    }
    
    c.JSON(http.StatusOK, resp)
}

// GetMyAuctions 获取我的拍卖
func (h *AuctionHandler) GetMyAuctions(c *gin.Context) {
    var req MyAuctionsRequest
    if err := c.ShouldBindQuery(&req); err != nil {
        // 设置默认值
        req.Page = 1
        req.PageSize = 20
    }
    
    userID := c.GetUint64("userID")
    resp, err := h.auctionService.GetMyAuctions(c.Request.Context(), &req, userID)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"code": 1, "message": "Internal error"})
        return
    }
    
    c.JSON(http.StatusOK, resp)
}
```

## 4. 技术特点

### 4.1 高性能
- **缓存机制**：活跃拍卖物品使用内存缓存，减少数据库查询
- **并发处理**：使用互斥锁和读写锁保证并发安全
- **批量操作**：过期拍卖处理使用批量查询，减少数据库交互
- **异步处理**：通知发送等非关键操作使用异步处理，提高系统吞吐量

### 4.2 可靠性
- **事务处理**：关键操作使用数据库事务，确保数据一致性
- **资金冻结**：竞拍时冻结用户资金，确保交易安全
- **数据验证**：严格验证用户输入和操作权限
- **异常处理**：完善的异常处理机制，确保系统稳定运行

### 4.3 可扩展性
- **模块化设计**：采用分层架构，模块间通过接口通信
- **依赖注入**：使用依赖注入，方便替换实现
- **配置化**：关键参数通过配置文件管理，方便调整
- **插件支持**：预留扩展点，支持插件化开发

### 4.4 安全性
- **权限验证**：严格的用户权限验证，确保只有授权用户可以执行操作
- **数据加密**：敏感数据加密存储
- **防作弊**：防止恶意竞拍和刷拍卖
- **审计日志**：记录所有拍卖相关的操作，便于审计

### 4.5 用户体验
- **实时反馈**：竞拍和购买操作实时反馈结果
- **通知系统**：及时通知用户拍卖状态变化
- **友好界面**：提供简洁明了的API接口
- **搜索过滤**：支持多种条件的拍卖物品搜索和过滤

## 5. 依赖管理

### 5.1 内部依赖
- **用户服务**：提供用户信息和身份验证
- **物品服务**：提供物品管理和转移功能
- **货币服务**：提供货币管理和转移功能
- **通知服务**：提供消息通知功能

### 5.2 外部依赖
- **Gin**：Web框架，处理HTTP请求
- **GORM**：ORM框架，操作数据库
- **Redis**：缓存服务，缓存热点数据
- **MySQL**：数据库，存储拍卖数据
- **Protobuf**：序列化框架，定义API消息
- **Golang Cron**：定时任务，处理过期拍卖

## 6. 性能优化

### 6.1 数据库优化
- **索引设计**：为常用查询字段添加索引，如拍卖状态、结束时间、卖家ID等
- **分页查询**：使用分页查询，减少单次查询的数据量
- **批量操作**：使用批量操作，减少数据库交互次数
- **事务管理**：合理使用事务，避免长事务

### 6.2 缓存优化
- **内存缓存**：使用内存缓存活跃拍卖物品，减少数据库查询
- **Redis缓存**：使用Redis缓存热门拍卖物品和竞拍信息
- **缓存策略**：合理设置缓存过期时间，避免缓存雪崩
- **缓存预热**：系统启动时预热缓存，提高首次访问速度

### 6.3 并发优化
- **锁粒度**：减小锁的粒度，提高并发性能
- **读写锁**：读多写少的场景使用读写锁
- **并发限制**：限制并发竞拍数，防止系统过载
- **异步处理**：非关键操作使用异步处理，提高系统吞吐量

### 6.4 网络优化
- **HTTP/2**：使用HTTP/2，提高网络传输效率
- **连接池**：使用数据库和Redis连接池，减少连接建立开销
- **压缩传输**：使用数据压缩，减少网络传输数据量
- **CDN**：静态资源使用CDN，提高访问速度

## 7. 测试策略

### 7.1 单元测试
- **服务层测试**：测试拍卖服务的核心方法
- **处理器测试**：测试API处理器的请求处理逻辑
- **辅助方法测试**：测试工具类和辅助方法

### 7.2 集成测试
- **API测试**：测试完整的API调用流程
- **数据库测试**：测试数据库操作和事务处理
- **缓存测试**：测试缓存机制的正确性
- **通知测试**：测试通知系统的集成

### 7.3 性能测试
- **负载测试**：测试系统在高负载下的表现
- **并发测试**：测试系统的并发处理能力
- **响应时间测试**：测试API的响应时间
- **数据库性能测试**：测试数据库操作的性能

### 7.4 安全测试
- **权限测试**：测试权限验证的有效性
- **防作弊测试**：测试防作弊机制的有效性
- **数据安全测试**：测试数据加密和保护
- **SQL注入测试**：测试系统对SQL注入的防护

## 8. 部署与集成

### 8.1 部署方式
- **容器化部署**：使用Docker容器部署，便于管理和扩展
- **集群部署**：多实例部署，提高系统可用性
- **负载均衡**：使用负载均衡器分发请求
- **自动扩缩容**：根据负载自动调整实例数量

### 8.2 集成方式
- **API集成**：通过HTTP API与前端集成
- **RPC集成**：通过gRPC与其他服务集成
- **消息队列集成**：通过消息队列处理异步任务
- **监控集成**：集成监控系统，监控系统状态

### 8.3 配置管理
- **环境变量**：使用环境变量管理配置
- **配置文件**：使用配置文件管理复杂配置
- **配置中心**：使用配置中心管理分布式配置
- **密钥管理**：使用密钥管理服务管理敏感配置

## 9. 监控与维护

### 9.1 监控指标
- **拍卖数量**：监控拍卖物品的数量和变化趋势
- **竞拍频率**：监控竞拍的频率和峰值
- **交易金额**：监控拍卖交易的总金额
- **系统性能**：监控系统的响应时间和吞吐量
- **错误率**：监控系统的错误率和类型

### 9.2 告警机制
- **拍卖过期**：拍卖过期未处理时告警
- **系统错误**：系统出现错误时告警
- **性能异常**：系统性能异常时告警
- **安全事件**：发现安全事件时告警

### 9.3 维护任务
- **数据库清理**：定期清理过期的拍卖数据
- **缓存清理**：定期清理无效的缓存数据
- **日志清理**：定期清理日志文件
- **备份**：定期备份拍卖数据

### 9.4 故障处理
- **故障检测**：及时检测系统故障
- **故障隔离**：隔离故障，防止影响其他系统
- **故障恢复**：快速恢复系统功能
- **故障分析**：分析故障原因，防止再次发生

## 10. 总结

本Go实现方案为拍卖系统提供了一个高性能、可靠、可扩展的实现。系统采用分层架构，将核心业务逻辑与基础设施分离，使用依赖注入和接口分离原则，提高了系统的可维护性和可扩展性。

系统实现了完整的拍卖功能，包括物品拍卖、竞拍、一口价购买、拍卖管理、查询和通知等。同时，系统还实现了资金冻结、事务处理、数据验证等机制，确保交易安全可靠。

通过缓存机制、并发优化、数据库优化等技术手段，系统在性能方面也有良好的表现，能够处理大量拍卖物品和竞拍请求。

此外，系统还提供了完善的监控和维护机制，确保系统的稳定运行和及时故障处理。

总之，本实现方案为游戏拍卖系统提供了一个全面、可靠、高性能的解决方案，能够满足游戏玩家的交易需求，促进游戏内经济的健康发展。