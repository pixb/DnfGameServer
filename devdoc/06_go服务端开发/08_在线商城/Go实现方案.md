# 3. Go 实现方案

## 3.1 数据模型设计

### 3.1.1 models/onlinemall.go
```go
package models

import (
    "time"
    "gorm.io/gorm"
)

// OnlineMallItem 商城商品
type OnlineMallItem struct {
    ID           uint64    `gorm:"primaryKey;column:id" json:"id"`
    Name         string    `gorm:"column:name;size:100" json:"name"`
    Description  string    `gorm:"column:description;size:500" json:"description"`
    ItemType     uint32    `gorm:"column:itemType;index" json:"item_type"`
    ItemIndex    uint32    `gorm:"column:itemIndex" json:"item_index"`
    ItemCount    uint32    `gorm:"column:itemCount" json:"item_count"`
    Bind         bool      `gorm:"column:bind" json:"bind"`
    Price        uint64    `gorm:"column:price" json:"price"`
    CurrencyType uint32    `gorm:"column:currencyType" json:"currency_type"`
    OriginalPrice uint32    `gorm:"column:originalPrice" json:"original_price"`
    SaleQuota    uint32    `gorm:"column:saleQuota" json:"sale_quota"`
    SaleCount    uint32    `gorm:"column:saleCount" json:"sale_count"`
    ForSale      bool      `gorm:"column:forSale;index" json:"for_sale"`
    Recommend    bool      `gorm:"column:recommend;index" json:"recommend"`
    Page         uint32    `gorm:"column:page" json:"page"`
    Barcode      string    `gorm:"column:barcode;size:50" json:"barcode"`
    StartTime    time.Time `gorm:"column:startTime" json:"start_time"`
    EndTime      time.Time `gorm:"column:endTime" json:"end_time"`
    BuyLimit     uint32    `gorm:"column:buyLimit" json:"buy_limit"`
    Unit         string    `gorm:"column:unit;size:20" json:"unit"`
    Sort         uint32    `gorm:"column:sort" json:"sort"`
    CreateTime   time.Time `gorm:"column:createTime" json:"create_time"`
    UpdateTime   time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (OnlineMallItem) TableName() string {
    return "t_online_mall_item"
}

// OnlineMallBuyRecord 商城购买记录
type OnlineMallBuyRecord struct {
    ID           uint64    `gorm:"primaryKey;column:id" json:"id"`
    RoleID       uint64    `gorm:"column:roleId;index" json:"role_id"`
    ItemID       uint32    `gorm:"column:itemId;index" json:"item_id"`
    ItemName     string    `gorm:"column:itemName;size:100" json:"item_name"`
    ItemCount    uint32    `gorm:"column:itemCount" json:"item_count"`
    Price        uint64    `gorm:"column:price" json:"price"`
    CurrencyType uint32    `gorm:"column:currencyType" json:"currency_type"`
    BuyTime      time.Time `gorm:"column:buyTime;index" json:"buy_time"`
    CreateTime   time.Time `gorm:"column:createTime" json:"create_time"`
}

func (OnlineMallBuyRecord) TableName() string {
    return "t_online_mall_buy_record"
}

// OnlineMallCategory 商城分类
type OnlineMallCategory struct {
    ID        uint64    `gorm:"primaryKey;column:id" json:"id"`
    Name      string    `gorm:"column:name;size:50" json:"name"`
    ParentID  uint32    `gorm:"column:parentId;index" json:"parent_id"`
    Sort      uint32    `gorm:"column:sort" json:"sort"`
    Status    uint32    `gorm:"column:status;default:1" json:"status"`
    CreateTime time.Time `gorm:"column:createTime" json:"create_time"`
    UpdateTime time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (OnlineMallCategory) TableName() string {
    return "t_online_mall_category"
}

// OnlineMallBuyLimit 商城购买限制
type OnlineMallBuyLimit struct {
    ID        uint64    `gorm:"primaryKey;column:id" json:"id"`
    RoleID    uint64    `gorm:"column:roleId;index" json:"role_id"`
    ItemID    uint32    `gorm:"column:itemId;index" json:"item_id"`
    BuyCount  uint32    `gorm:"column:buyCount" json:"buy_count"`
    BuyDate   time.Time `gorm:"column:buyDate;index" json:"buy_date"`
    CreateTime time.Time `gorm:"column:createTime" json:"create_time"`
    UpdateTime time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (OnlineMallBuyLimit) TableName() string {
    return "t_online_mall_buy_limit"
}
```

## 3.2 Protobuf 消息定义

### 3.2.1 onlinemall.proto
```protobuf
syntax = "proto3";

package dnf.v1;

option go_package = "gen/dnf/v1";

import "dnf/v1/common.proto";

// 商城商品信息
message OnlineMallItem {
    uint32 id = 1;
    string name = 2;
    string description = 3;
    uint32 item_type = 4;
    uint32 item_index = 5;
    uint32 item_count = 6;
    bool bind = 7;
    uint64 price = 8;
    uint32 currency_type = 9;
    uint32 original_price = 10;
    uint32 sale_quota = 11;
    uint32 sale_count = 12;
    bool for_sale = 13;
    bool recommend = 14;
    uint32 page = 15;
    string barcode = 16;
    uint64 start_time = 17;
    uint64 end_time = 18;
    uint32 buy_limit = 19;
    uint32 buy_count = 20;
    string unit = 21;
}

// 商城列表请求
message OnlineMallListRequest {
    uint32 page = 1;
    uint32 page_size = 2;
    uint32 item_type = 3;
    uint32 sort_type = 4;
}

// 商城列表响应
message OnlineMallListResponse {
    repeated OnlineMallItem items = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
}

// 商城商品详情请求
message OnlineMallDetailRequest {
    uint32 item_id = 1;
}

// 商城商品详情响应
message OnlineMallDetailResponse {
    OnlineMallItem item = 1;
    uint32 error = 2;
    string message = 3;
}

// 商城购买请求
message OnlineMallBuyRequest {
    uint32 item_id = 1;
    uint32 buy_count = 2;
    uint32 currency_type = 3;
}

// 商城购买响应
message OnlineMallBuyResponse {
    OnlineMallItem item = 1;
    repeated StackableItem rewards = 2;
    uint32 remaining_count = 3;
    uint32 error = 4;
    string message = 5;
}

// 商城推荐请求
message OnlineMallRecommendRequest {
    uint32 recommend_type = 1;
    uint32 limit = 2;
}

// 商城推荐响应
message OnlineMallRecommendResponse {
    repeated OnlineMallItem items = 1;
    uint32 error = 2;
    string message = 3;
}

// 商城分类请求
message OnlineMallCategoryRequest {
}

// 商城分类响应
message OnlineMallCategoryResponse {
    repeated CategoryInfo categories = 1;
    uint32 error = 2;
    string message = 3;
}

// 分类信息
message CategoryInfo {
    uint32 id = 1;
    string name = 2;
    uint32 parent_id = 3;
    uint32 sort = 4;
}

// 商城搜索请求
message OnlineMallSearchRequest {
    string item_name = 1;
    uint32 item_type = 2;
    uint64 min_price = 3;
    uint64 max_price = 4;
    uint32 page = 5;
    uint32 page_size = 6;
}

// 商城搜索响应
message OnlineMallSearchResponse {
    repeated OnlineMallItem items = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
}

// 购买记录请求
message OnlineMallBuyRecordRequest {
    uint32 page = 1;
    uint32 page_size = 2;
}

// 购买记录响应
message OnlineMallBuyRecordResponse {
    repeated BuyRecordInfo records = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
}

// 购买记录信息
message BuyRecordInfo {
    uint64 id = 1;
    uint32 item_id = 2;
    string item_name = 3;
    uint32 item_count = 4;
    uint64 price = 5;
    uint32 currency_type = 6;
    uint64 buy_time = 7;
}
```

## 3.3 核心 Handler 实现

### 3.3.1 handlers/onlinemall.go
```go
package handlers

import (
    "context"

    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/onlinemall_service"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
    dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
    "google.golang.org/protobuf/proto"
)

type OnlineMallHandler struct {
    onlinemallService *onlinemall_service.OnlineMallService
}

func NewOnlineMallHandler(onlinemallService *onlinemall_service.OnlineMallService) *OnlineMallHandler {
    return &OnlineMallHandler{
        onlinemallService: onlinemallService,
    }
}

// OnlineMallListHandler 商城列表处理器
func (h *OnlineMallHandler) OnlineMallListHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.OnlineMallListRequest)
    if !ok {
        return
    }

    items, total, err := h.onlinemallService.GetMallList(context.Background(), req.Page, req.PageSize, req.ItemType, req.SortType)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get mall list")
        return
    }

    resp := &dnfv1.OnlineMallListResponse{
        Items:  items,
        Total:  total,
        Error:  0,
    }
    sess.Send(resp)
}

// OnlineMallDetailHandler 商城商品详情处理器
func (h *OnlineMallHandler) OnlineMallDetailHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.OnlineMallDetailRequest)
    if !ok {
        return
    }

    item, err := h.onlinemallService.GetMallItemDetail(context.Background(), req.ItemId)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get mall item detail")
        return
    }

    resp := &dnfv1.OnlineMallDetailResponse{
        Item:  item,
        Error: 0,
    }
    sess.Send(resp)
}

// OnlineMallBuyHandler 商城购买处理器
func (h *OnlineMallHandler) OnlineMallBuyHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.OnlineMallBuyRequest)
    if !ok {
        return
    }

    item, rewards, remainingCount, err := h.onlinemallService.BuyMallItem(context.Background(), sess.RoleID, req.ItemId, req.BuyCount, req.CurrencyType)
    if err != nil {
        h.sendError(sess, req, 1, "failed to buy mall item")
        return
    }

    resp := &dnfv1.OnlineMallBuyResponse{
        Item:           item,
        Rewards:        rewards,
        RemainingCount: remainingCount,
        Error:          0,
    }
    sess.Send(resp)
}

// OnlineMallRecommendHandler 商城推荐处理器
func (h *OnlineMallHandler) OnlineMallRecommendHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.OnlineMallRecommendRequest)
    if !ok {
        return
    }

    items, err := h.onlinemallService.GetRecommendItems(context.Background(), req.RecommendType, req.Limit)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get recommend items")
        return
    }

    resp := &dnfv1.OnlineMallRecommendResponse{
        Items:  items,
        Error:  0,
    }
    sess.Send(resp)
}

// OnlineMallCategoryHandler 商城分类处理器
func (h *OnlineMallHandler) OnlineMallCategoryHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.OnlineMallCategoryRequest)
    if !ok {
        return
    }

    categories, err := h.onlinemallService.GetMallCategories(context.Background())
    if err != nil {
        h.sendError(sess, req, 1, "failed to get mall categories")
        return
    }

    resp := &dnfv1.OnlineMallCategoryResponse{
        Categories: categories,
        Error:      0,
    }
    sess.Send(resp)
}

// OnlineMallSearchHandler 商城搜索处理器
func (h *OnlineMallHandler) OnlineMallSearchHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.OnlineMallSearchRequest)
    if !ok {
        return
    }

    items, total, err := h.onlinemallService.SearchMallItems(context.Background(), req.ItemName, req.ItemType, req.MinPrice, req.MaxPrice, req.Page, req.PageSize)
    if err != nil {
        h.sendError(sess, req, 1, "failed to search mall items")
        return
    }

    resp := &dnfv1.OnlineMallSearchResponse{
        Items:  items,
        Total:  total,
        Error:  0,
    }
    sess.Send(resp)
}

// OnlineMallBuyRecordHandler 购买记录处理器
func (h *OnlineMallHandler) OnlineMallBuyRecordHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.OnlineMallBuyRecordRequest)
    if !ok {
        return
    }

    records, total, err := h.onlinemallService.GetBuyRecords(context.Background(), sess.RoleID, req.Page, req.PageSize)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get buy records")
        return
    }

    resp := &dnfv1.OnlineMallBuyRecordResponse{
        Records: records,
        Total:   total,
        Error:   0,
    }
    sess.Send(resp)
}

func (h *OnlineMallHandler) sendError(sess *network.Session, req proto.Message, code uint32, message string) {
    switch req.(type) {
    case *dnfv1.OnlineMallListRequest:
        resp := &dnfv1.OnlineMallListResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.OnlineMallDetailRequest:
        resp := &dnfv1.OnlineMallDetailResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.OnlineMallBuyRequest:
        resp := &dnfv1.OnlineMallBuyResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.OnlineMallRecommendRequest:
        resp := &dnfv1.OnlineMallRecommendResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.OnlineMallCategoryRequest:
        resp := &dnfv1.OnlineMallCategoryResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.OnlineMallSearchRequest:
        resp := &dnfv1.OnlineMallSearchResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.OnlineMallBuyRecordRequest:
        resp := &dnfv1.OnlineMallBuyRecordResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    }
}
```

## 3.4 业务逻辑服务

### 3.4.1 onlinemall_service/onlinemall.go
```go
package onlinemall_service

import (
    "context"
    "time"

    "github.com/pixb/DnfGameServer/dnf-go-server/store"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
    dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

type OnlineMallService struct {
    store *store.Store
}

func NewOnlineMallService(store *store.Store) *OnlineMallService {
    return &OnlineMallService{
        store: store,
    }
}

// GetMallList 获取商城列表
func (s *OnlineMallService) GetMallList(ctx context.Context, page, pageSize, itemType, sortType uint32) ([]*dnfv1.OnlineMallItem, uint32, error) {
    offset := (page - 1) * pageSize

    items, total, err := s.store.GetOnlineMallItems(ctx, &store.FindOnlineMallItem{
        ItemType: itemType,
        ForSale:  true,
    }, offset, pageSize)
    if err != nil {
        return nil, 0, err
    }

    return items, uint32(total), nil
}

// GetMallItemDetail 获取商城商品详情
func (s *OnlineMallService) GetMallItemDetail(ctx context.Context, itemID uint32) (*dnfv1.OnlineMallItem, error) {
    item, err := s.store.GetOnlineMallItemByID(ctx, itemID)
    if err != nil {
        return nil, err
    }

    return item, nil
}

// BuyMallItem 购买商城商品
func (s *OnlineMallService) BuyMallItem(ctx context.Context, roleID uint64, itemID, buyCount, currencyType uint32) (*dnfv1.OnlineMallItem, []*dnfv1.StackableItem, uint32, error) {
    // 1. 获取商品信息
    item, err := s.store.GetOnlineMallItemByID(ctx, itemID)
    if err != nil {
        return nil, nil, 0, err
    }

    // 2. 检查商品是否在售
    if !item.ForSale {
        return nil, nil, 0, store.ErrInvalidParam
    }

    // 3. 检查购买限制
    if item.BuyLimit > 0 {
        buyLimit, err := s.store.GetOnlineMallBuyLimit(ctx, roleID, itemID, time.Now())
        if err == nil && buyLimit.BuyCount >= item.BuyLimit {
            return nil, nil, 0, store.ErrInvalidParam
        }
    }

    // 4. 计算总价
    totalPrice := uint64(item.Price) * uint64(buyCount)

    // 5. 扣除货币
    err = s.store.DeductMoney(ctx, roleID, totalPrice, currencyType)
    if err != nil {
        return nil, nil, 0, err
    }

    // 6. 添加物品到背包
    bagItem := &models.BagItem{
        RoleID:    roleID,
        ItemIndex: item.ItemIndex,
        Count:     item.ItemCount * buyCount,
        Bind:      item.Bind,
        CreateTime: time.Now(),
        UpdateTime: time.Now(),
    }

    err = s.store.CreateBagItem(ctx, bagItem)
    if err != nil {
        return nil, nil, 0, err
    }

    // 7. 更新购买限制
    if item.BuyLimit > 0 {
        buyLimit, err := s.store.GetOnlineMallBuyLimit(ctx, roleID, itemID, time.Now())
        if err == nil {
            buyLimit.BuyCount += buyCount
            buyLimit.UpdateTime = time.Now()
            s.store.UpdateOnlineMallBuyLimit(ctx, buyLimit)
        } else {
            newBuyLimit := &models.OnlineMallBuyLimit{
                RoleID:    roleID,
                ItemID:    itemID,
                BuyCount:  buyCount,
                BuyDate:   time.Now(),
                CreateTime: time.Now(),
                UpdateTime: time.Now(),
            }
            s.store.CreateOnlineMallBuyLimit(ctx, newBuyLimit)
        }
    }

    // 8. 记录购买
    record := &models.OnlineMallBuyRecord{
        RoleID:       roleID,
        ItemID:       itemID,
        ItemName:     item.Name,
        ItemCount:    buyCount,
        Price:        totalPrice,
        CurrencyType: currencyType,
        BuyTime:      time.Now(),
        CreateTime:   time.Now(),
    }

    err = s.store.CreateOnlineMallBuyRecord(ctx, record)
    if err != nil {
        return nil, nil, 0, err
    }

    // 9. 计算剩余购买次数
    remainingCount := uint32(0)
    if item.BuyLimit > 0 {
        buyLimit, _ := s.store.GetOnlineMallBuyLimit(ctx, roleID, itemID, time.Now())
        remainingCount = item.BuyLimit - buyLimit.BuyCount
    }

    // 10. 构建奖励列表
    rewards := []*dnfv1.StackableItem{
        {
            Index: item.ItemIndex,
            Count: item.ItemCount * buyCount,
            Bind:  item.Bind,
        },
    }

    return item, rewards, remainingCount, nil
}

// GetRecommendItems 获取推荐商品
func (s *OnlineMallService) GetRecommendItems(ctx context.Context, recommendType, limit uint32) ([]*dnfv1.OnlineMallItem, error) {
    items, _, err := s.store.GetOnlineMallItems(ctx, &store.FindOnlineMallItem{
        Recommend: true,
        ForSale:   true,
    }, 0, limit)
    if err != nil {
        return nil, err
    }

    return items, nil
}

// GetMallCategories 获取商城分类
func (s *OnlineMallService) GetMallCategories(ctx context.Context) ([]*dnfv1.CategoryInfo, error) {
    categories, err := s.store.GetOnlineMallCategories(ctx)
    if err != nil {
        return nil, err
    }

    return categories, nil
}

// SearchMallItems 搜索商城商品
func (s *OnlineMallService) SearchMallItems(ctx context.Context, itemName string, itemType uint32, minPrice, maxPrice uint64, page, pageSize uint32) ([]*dnfv1.OnlineMallItem, uint32, error) {
    offset := (page - 1) * pageSize

    items, total, err := s.store.SearchOnlineMallItems(ctx, itemName, itemType, minPrice, maxPrice, offset, pageSize)
    if err != nil {
        return nil, 0, err
    }

    return items, uint32(total), nil
}

// GetBuyRecords 获取购买记录
func (s *OnlineMallService) GetBuyRecords(ctx context.Context, roleID uint64, page, pageSize uint32) ([]*dnfv1.BuyRecordInfo, uint32, error) {
    offset := (page - 1) * pageSize

    records, total, err := s.store.GetOnlineMallBuyRecords(ctx, roleID, offset, pageSize)
    if err != nil {
        return nil, 0, err
    }

    return records, uint32(total), nil
}
```