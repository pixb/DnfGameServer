package store

// AuctionStatus 拍卖行物品状态
type AuctionStatus int32

const (
	AuctionStatusSelling   AuctionStatus = 0 // 出售中
	AuctionStatusSold      AuctionStatus = 1 // 已售出
	AuctionStatusExpired   AuctionStatus = 2 // 已过期
	AuctionStatusCancelled AuctionStatus = 3 // 已取消
)

// AuctionItem 拍卖行物品
type AuctionItem struct {
	BaseModel

	SellerID   uint64        // 卖家角色ID
	SellerName string        // 卖家名称
	ItemID     int32         // 物品模板ID
	Count      int32         // 数量
	Price      int64         // 单价
	TotalPrice int64         // 总价
	Duration   int32         // 持续时间(小时)
	Status     AuctionStatus // 状态
	BidderID   uint64        // 当前出价者ID
	BidderName string        // 当前出价者名称
	BidPrice   int64         // 当前出价
	BidCount   int32         // 出价次数
	Attributes string        // 物品属性(JSON)
	EndTime    int64         // 结束时间
}

// FindAuctionItem 查询拍卖物品
type FindAuctionItem struct {
	FindBase

	SellerID      *uint64
	ItemID        *int32
	Status        *AuctionStatus
	MinPrice      *int64
	MaxPrice      *int64
	BidderID      *uint64
	EndTimeBefore *int64
}

// UpdateAuctionItem 更新拍卖物品
type UpdateAuctionItem struct {
	ID        uint64
	UpdatedAt *int64
	RowStatus *RowStatus

	Status     *AuctionStatus
	BuyerID    *uint64
	BidderID   *uint64
	BidderName *string
	BidPrice   *int64
	BidCount   *int32
}

// DeleteAuctionItem 删除拍卖物品
type DeleteAuctionItem DeleteBase

// AuctionHistory 拍卖历史记录
type AuctionHistory struct {
	BaseModel

	AuctionID    uint64 // 拍卖ID
	SellerID     uint64 // 卖家ID
	BuyerID      uint64 // 买家ID
	ItemID       int32  // 物品ID
	Count        int32  // 数量
	FinalPrice   int64  // 成交价格
	SellerIncome int64  // 卖家实际收入(扣除手续费)
}

// FindAuctionHistory 查询拍卖历史
type FindAuctionHistory struct {
	FindBase

	SellerID *uint64
	BuyerID  *uint64
	ItemID   *int32
}

// CreateAuctionHistory 创建拍卖历史
type CreateAuctionHistory struct {
	AuctionID    uint64
	SellerID     uint64
	BuyerID      uint64
	ItemID       int32
	Count        int32
	FinalPrice   int64
	SellerIncome int64
}

// AuctionConfig 拍卖行配置
type AuctionConfig struct {
	MinPrice        int64   // 最低价格
	MaxPrice        int64   // 最高价格
	MinDuration     int32   // 最短时间(小时)
	MaxDuration     int32   // 最长时间(小时)
	FeeRate         float64 // 手续费率
	MaxItemsPerUser int32   // 每个用户最多上架数量
}

// DefaultAuctionConfig 返回默认配置
func DefaultAuctionConfig() *AuctionConfig {
	return &AuctionConfig{
		MinPrice:        1,
		MaxPrice:        999999999,
		MinDuration:     1,
		MaxDuration:     168,  // 7天
		FeeRate:         0.05, // 5%
		MaxItemsPerUser: 10,
	}
}
