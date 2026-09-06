package handlers

import (
	"context"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
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

// SearchAuctionHandler 处理搜索拍卖行请求(2026-09-07 第五十四轮实化: 接 store 真实查询)
func SearchAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.SearchAuctionRequest)
	if !ok {
		logger.Error("invalid message type for search auction")
		return
	}

	ctx := context.Background()
	// 2026-09-07 第五十七轮: 惰性到期结算(先处理过期拍卖, 再查当前数据)
	if _, err := shopStore.SettleExpiredAuctions(ctx); err != nil {
		logger.Error("failed to settle expired auctions",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	selling := store.AuctionStatusSelling
	find := &store.FindAuctionItem{
		Status: &selling,
	}
	if req.ItemId != 0 {
		itemID := int32(req.ItemId)
		find.ItemID = &itemID
	}
	if req.MaxPrice != 0 {
		maxPrice := int64(req.MaxPrice)
		find.MaxPrice = &maxPrice
	}

	items, err := shopStore.ListAuctionItems(ctx, find)
	if err != nil {
		logger.Error("failed to list auction items",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.SearchAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 101, resp)
		return
	}

	now := time.Now().Unix()
	itemList := make([]*dnfv1.AuctionItem, 0, len(items))
	for _, item := range items {
		timeLeft := item.EndTime - now
		if timeLeft < 0 {
			timeLeft = 0
		}
		itemList = append(itemList, &dnfv1.AuctionItem{
			AuctionId:  int64(item.ID),
			ItemId:     uint32(item.ItemID),
			SellerName: item.SellerName,
			Price:      int32(item.Price),
			BidPrice:   int32(item.BidPrice),
			TimeLeft:   int32(timeLeft),
			Quality:    dnfv1.ItemQuality_COMMON,
		})
	}

	resp := &dnfv1.SearchAuctionResponse{
		Error: 0,
		Total: int32(len(itemList)),
		Items: itemList,
	}

	if err := session.WriteResponse(10005, 101, resp); err != nil {
		logger.Error("failed to send search auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// RegisterAuctionHandler 处理上架拍卖请求(2026-09-07 第五十四轮实化: 背包校验+上架+扣物品)
func RegisterAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.RegisterAuctionRequest)
	if !ok {
		logger.Error("invalid message type for register auction")
		return
	}

	roleID := session.RoleID()
	ctx := context.Background()

	// 校验背包物品
	item, err := shopStore.GetBagItem(ctx, &store.FindBagItem{
		FindBase: store.FindBase{ID: &req.Guid},
		RoleID:   &roleID,
	})
	if err != nil || item == nil {
		resp := &dnfv1.RegisterAuctionResponse{Error: 6}
		_ = session.WriteResponse(10005, 103, resp)
		return
	}

	role, _ := shopStore.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	sellerName := ""
	if role != nil {
		sellerName = role.Name
	}

	startPrice := int64(req.StartPrice)
	duration := int32(req.Duration)
	if duration <= 0 {
		duration = 24
	}
	if startPrice <= 0 {
		startPrice = 1
	}
	// 2026-09-07 第五十八轮: 一口价落库, 未设置(<=起拍价)时默认=起拍价
	buyoutPrice := int64(req.BuyoutPrice)
	if buyoutPrice <= startPrice {
		buyoutPrice = startPrice
	}

	auction, err := shopStore.CreateAuctionItem(ctx, &store.AuctionItem{
		SellerID:    roleID,
		SellerName:  sellerName,
		ItemID:      item.ItemID,
		Count:       item.Count,
		Price:       startPrice,
		BuyoutPrice: buyoutPrice,
		TotalPrice:  startPrice,
		Duration:    duration,
		Status:      store.AuctionStatusSelling,
		BidPrice:    startPrice,
		BidCount:    0,
	})
	if err != nil {
		logger.Error("failed to create auction item",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.RegisterAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 103, resp)
		return
	}

	// 扣减背包物品
	if err := shopStore.DeleteBagItem(ctx, &store.DeleteBagItem{ID: item.ID}); err != nil {
		logger.Error("failed to delete bag item after register",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	resp := &dnfv1.RegisterAuctionResponse{
		Error:     0,
		AuctionId: int64(auction.ID),
	}

	if err := session.WriteResponse(10005, 103, resp); err != nil {
		logger.Error("failed to send register auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// BidAuctionHandler 处理竞拍请求
// 2026-09-07 第五十四轮: 校验状态/价格; 第五十六轮: 出价冻结金币 + 被超价退还旧出价者
func BidAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.BidAuctionRequest)
	if !ok {
		logger.Error("invalid message type for bid auction")
		return
	}

	roleID := session.RoleID()
	ctx := context.Background()
	auctionID := uint64(req.AuctionId)
	// 2026-09-07 第五十七轮: 惰性到期结算(防止对过期拍卖出价)
	if _, err := shopStore.SettleExpiredAuctions(ctx); err != nil {
		logger.Error("failed to settle expired auctions",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	auc, err := shopStore.GetAuctionItem(ctx, &store.FindAuctionItem{
		FindBase: store.FindBase{ID: &auctionID},
	})
	if err != nil || auc == nil {
		resp := &dnfv1.BidAuctionResponse{Error: 2}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}
	if auc.Status != store.AuctionStatusSelling {
		resp := &dnfv1.BidAuctionResponse{Error: 3}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}
	if auc.SellerID == roleID {
		resp := &dnfv1.BidAuctionResponse{Error: 9}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}
	if req.BidPrice <= int32(auc.BidPrice) {
		resp := &dnfv1.BidAuctionResponse{Error: 4}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}

	// 2026-09-07 第五十六轮: 出价者金币校验(不足 err8)并即时冻结
	bidPrice := int64(req.BidPrice)
	bidderCur, err := shopStore.GetRoleCurrency(ctx, roleID)
	if err != nil {
		logger.Error("failed to get bidder currency",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.BidAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}
	if bidderCur.Gold < bidPrice {
		resp := &dnfv1.BidAuctionResponse{Error: 8}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}
	bidderCur.Gold -= bidPrice
	if err := shopStore.UpdateRoleCurrency(ctx, bidderCur); err != nil {
		logger.Error("failed to freeze bidder gold",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.BidAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}

	// 被超价: 退还旧出价者(bidder)此前冻结的金币
	if auc.BidderID != 0 && auc.BidderID != roleID {
		oldCur, err := shopStore.GetRoleCurrency(ctx, auc.BidderID)
		if err == nil {
			oldCur.Gold += auc.BidPrice
			_ = shopStore.UpdateRoleCurrency(ctx, oldCur)
		}
	}

	bidCount := auc.BidCount + 1
	if err := shopStore.UpdateAuctionItem(ctx, &store.UpdateAuctionItem{
		ID:       auc.ID,
		BidderID: &roleID,
		BidPrice: &bidPrice,
		BidCount: &bidCount,
	}); err != nil {
		logger.Error("failed to update auction bid",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.BidAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 105, resp)
		return
	}

	resp := &dnfv1.BidAuctionResponse{Error: 0}
	if err := session.WriteResponse(10005, 105, resp); err != nil {
		logger.Error("failed to send bid auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// addBagItemAutoSlot 物品入包: 查该角色最大格子索引+1 分配新槽(2026-09-07 第五十五轮)
func addBagItemAutoSlot(ctx context.Context, roleID uint64, itemID int32, count int32) error {
	items, err := shopStore.ListBagItemsByRole(ctx, roleID)
	if err != nil {
		return err
	}
	nextSlot := int32(0)
	for _, it := range items {
		if it.GridIndex >= nextSlot {
			nextSlot = it.GridIndex + 1
		}
	}
	_, err = shopStore.CreateBagItem(ctx, &store.BagItem{
		RoleID:    roleID,
		ItemID:    itemID,
		GridIndex: nextSlot,
		Count:     count,
	})
	return err
}

// BuyoutAuctionHandler 处理一口价购买请求
// 2026-09-07 第五十四轮: 状态流转+拍卖历史; 第五十五轮: 金币扣减/入账 + 物品入包
func BuyoutAuctionHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.BuyoutAuctionRequest)
	if !ok {
		logger.Error("invalid message type for buyout auction")
		return
	}

	roleID := session.RoleID()
	ctx := context.Background()
	auctionID := uint64(req.AuctionId)
	// 2026-09-07 第五十七轮: 惰性到期结算(防止买断/查询过期拍卖)
	if _, err := shopStore.SettleExpiredAuctions(ctx); err != nil {
		logger.Error("failed to settle expired auctions",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	auc, err := shopStore.GetAuctionItem(ctx, &store.FindAuctionItem{
		FindBase: store.FindBase{ID: &auctionID},
	})
	if err != nil || auc == nil {
		resp := &dnfv1.BuyoutAuctionResponse{Error: 2}
		_ = session.WriteResponse(10005, 107, resp)
		return
	}
	if auc.Status != store.AuctionStatusSelling {
		resp := &dnfv1.BuyoutAuctionResponse{Error: 3}
		_ = session.WriteResponse(10005, 107, resp)
		return
	}
	if auc.SellerID == roleID {
		resp := &dnfv1.BuyoutAuctionResponse{Error: 5}
		_ = session.WriteResponse(10005, 107, resp)
		return
	}

	// 2026-09-07 第五十八轮: 一口价成交价(未设置时兼容旧数据取起拍价)
	buyoutPrice := auc.BuyoutPrice
	if buyoutPrice <= 0 {
		buyoutPrice = auc.Price
	}

	// 2026-09-07 第五十五轮: 金币校验/扣减/卖家入账(先钱后货, 钱不足终止)
	buyerCur, err := shopStore.GetRoleCurrency(ctx, roleID)
	if err != nil {
		logger.Error("failed to get buyer currency",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.BuyoutAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 107, resp)
		return
	}
	if buyerCur.Gold < buyoutPrice {
		resp := &dnfv1.BuyoutAuctionResponse{Error: 7}
		_ = session.WriteResponse(10005, 107, resp)
		return
	}

	// 2026-09-07 第五十六轮: 竞拍者结算
	//   - 买断者即当前最高出价者: 已冻结 bid_price, 按一口价成交, 退还差价 (bid_price - buyout_price)
	//   - 非买断者的最高出价者: 被买断截胡, 退还其冻结的 bid_price
	if auc.BidderID == roleID {
		diff := auc.BidPrice - buyoutPrice
		if diff > 0 {
			buyerCur.Gold += diff
		}
	} else if auc.BidderID != 0 {
		if oldCur, err := shopStore.GetRoleCurrency(ctx, auc.BidderID); err == nil {
			oldCur.Gold += auc.BidPrice
			_ = shopStore.UpdateRoleCurrency(ctx, oldCur)
		}
	}
	buyerCur.Gold -= buyoutPrice
	if err := shopStore.UpdateRoleCurrency(ctx, buyerCur); err != nil {
		logger.Error("failed to deduct buyer gold",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.BuyoutAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 107, resp)
		return
	}

	sellerIncome := buyoutPrice * 95 / 100
	sellerCur, err := shopStore.GetRoleCurrency(ctx, auc.SellerID)
	if err == nil {
		sellerCur.Gold += sellerIncome
		_ = shopStore.UpdateRoleCurrency(ctx, sellerCur)
	}

	sold := store.AuctionStatusSold
	bidPrice := buyoutPrice
	bidCount := auc.BidCount + 1
	if err := shopStore.UpdateAuctionItem(ctx, &store.UpdateAuctionItem{
		ID:       auc.ID,
		Status:   &sold,
		BuyerID:  &roleID,
		BidPrice: &bidPrice,
		BidCount: &bidCount,
	}); err != nil {
		logger.Error("failed to update auction buyout",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
		resp := &dnfv1.BuyoutAuctionResponse{Error: 1}
		_ = session.WriteResponse(10005, 107, resp)
		return
	}

	// 拍卖历史(5% 手续费)
	if _, err := shopStore.CreateAuctionHistory(ctx, &store.CreateAuctionHistory{
		AuctionID:    auc.ID,
		SellerID:     auc.SellerID,
		BuyerID:      roleID,
		ItemID:       auc.ItemID,
		Count:        auc.Count,
		FinalPrice:   buyoutPrice,
		SellerIncome: sellerIncome,
	}); err != nil {
		logger.Error("failed to create auction history",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	// 物品入买家背包(自动分配空槽)
	if err := addBagItemAutoSlot(ctx, roleID, auc.ItemID, auc.Count); err != nil {
		logger.Error("failed to add buyout item to bag",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	resp := &dnfv1.BuyoutAuctionResponse{
		Error: 0,
		Item: &dnfv1.BagItem{
			ItemId: uint32(auc.ItemID),
			Count:  int32(auc.Count),
		},
	}

	if err := session.WriteResponse(10005, 107, resp); err != nil {
		logger.Error("failed to send buyout auction response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
