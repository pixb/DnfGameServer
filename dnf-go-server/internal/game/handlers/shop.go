package handlers

import (
	"dnf-go-server/internal/network"
	"dnf-go-server/internal/utils/logger"
	dnfv1 "dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// GetShopListHandler 处理获取商店列表请求
func GetShopListHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetShopListRequest)
	if !ok {
		logger.Error("invalid message type for get shop list")
		return
	}

	logger.Info("get shop list request received",
		logger.String("shop_type", req.ShopType.String()),
		logger.Int32("npc_id", req.NpcId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 根据商店类型加载商品列表
	resp := &dnfv1.GetShopListResponse{
		Error:       0,
		RefreshTime: 3600,
		Items: []*dnfv1.ShopItem{
			{
				Slot:         0,
				ItemId:       10001,
				Price:        1000,
				CurrencyType: 1,
				Stock:        -1,
				Discount:     0,
				LevelLimit:   1,
				JobLimit:     0,
			},
			{
				Slot:         1,
				ItemId:       10002,
				Price:        2000,
				CurrencyType: 1,
				Stock:        100,
				Discount:     10,
				LevelLimit:   10,
				JobLimit:     0,
			},
		},
	}

	if err := session.WriteResponse(10005, 1, resp); err != nil {
		logger.Error("failed to send shop list response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// BuyItemHandler 处理购买物品请求
func BuyItemHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.BuyItemRequest)
	if !ok {
		logger.Error("invalid message type for buy item")
		return
	}

	logger.Info("buy item request received",
		logger.String("shop_type", req.ShopType.String()),
		logger.Int32("slot", req.Slot),
		logger.Int32("count", req.Count),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查货币，扣除货币，添加物品
	resp := &dnfv1.BuyItemResponse{
		Error: 0,
		NewItems: []*dnfv1.BagItem{
			{
				Guid:   5001,
				ItemId: 10001,
				Count:  req.Count,
				Slot:   10,
			},
		},
		Currency: &dnfv1.Currency{
			Gold:    9000,
			Cera:    100,
			Fatigue: 100,
		},
	}

	if err := session.WriteResponse(10005, 3, resp); err != nil {
		logger.Error("failed to send buy item response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// SellToShopHandler 处理出售给商店请求
func SellToShopHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.SellToShopRequest)
	if !ok {
		logger.Error("invalid message type for sell to shop")
		return
	}

	logger.Info("sell to shop request received",
		logger.Uint64("guid", req.Guid),
		logger.Int32("count", req.Count),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品，计算价格，删除物品，增加金币
	resp := &dnfv1.SellToShopResponse{
		Error:        0,
		GoldReceived: 500,
	}

	if err := session.WriteResponse(10005, 5, resp); err != nil {
		logger.Error("failed to send sell to shop response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// SearchAuctionHandler 处理搜索拍卖行请求
func SearchAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.SearchAuctionRequest)
	if !ok {
		logger.Error("invalid message type for search auction")
		return
	}

	logger.Info("search auction request received",
		logger.Uint32("item_id", req.ItemId),
		logger.String("keyword", req.Keyword),
		logger.String("min_quality", req.MinQuality.String()),
		logger.Int32("max_price", req.MaxPrice),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 查询拍卖行数据库
	resp := &dnfv1.SearchAuctionResponse{
		Error: 0,
		Total: 2,
		Items: []*dnfv1.AuctionItem{
			{
				AuctionId:  1001,
				ItemId:     10001,
				SellerName: "卖家1",
				Price:      5000,
				BidPrice:   0,
				TimeLeft:   3600,
				Quality:    dnfv1.ItemQuality_RARE,
			},
			{
				AuctionId:  1002,
				ItemId:     10002,
				SellerName: "卖家2",
				Price:      10000,
				BidPrice:   8000,
				TimeLeft:   7200,
				Quality:    dnfv1.ItemQuality_EPIC,
			},
		},
	}

	if err := session.WriteResponse(10005, 101, resp); err != nil {
		logger.Error("failed to send search auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// RegisterAuctionHandler 处理上架拍卖请求
func RegisterAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.RegisterAuctionRequest)
	if !ok {
		logger.Error("invalid message type for register auction")
		return
	}

	logger.Info("register auction request received",
		logger.Uint64("guid", req.Guid),
		logger.Int32("start_price", req.StartPrice),
		logger.Int32("buyout_price", req.BuyoutPrice),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品，上架到拍卖行
	resp := &dnfv1.RegisterAuctionResponse{
		Error:     0,
		AuctionId: 1003,
	}

	if err := session.WriteResponse(10005, 103, resp); err != nil {
		logger.Error("failed to send register auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// BidAuctionHandler 处理竞拍请求
func BidAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.BidAuctionRequest)
	if !ok {
		logger.Error("invalid message type for bid auction")
		return
	}

	logger.Info("bid auction request received",
		logger.Int64("auction_id", req.AuctionId),
		logger.Int32("bid_price", req.BidPrice),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证竞拍，扣除金币，更新竞拍记录
	resp := &dnfv1.BidAuctionResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10005, 105, resp); err != nil {
		logger.Error("failed to send bid auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// BuyoutAuctionHandler 处理一口价购买请求
func BuyoutAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.BuyoutAuctionRequest)
	if !ok {
		logger.Error("invalid message type for buyout auction")
		return
	}

	logger.Info("buyout auction request received",
		logger.Int64("auction_id", req.AuctionId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证一口价，扣除金币，转移物品
	resp := &dnfv1.BuyoutAuctionResponse{
		Error: 0,
		Item: &dnfv1.BagItem{
			Guid:   6001,
			ItemId: 10001,
			Count:  1,
			Slot:   20,
		},
	}

	if err := session.WriteResponse(10005, 107, resp); err != nil {
		logger.Error("failed to send buyout auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
