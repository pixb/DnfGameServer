package handlers

import (
	"context"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

var shopStore *store.Store

// InitShopStore 初始化商店/拍卖行 Store
func InitShopStore(s *store.Store) {
	shopStore = s
}

// ==================== 商店模块扩展 (Module = 10005) ====================

// QueryShopOrderHandler 处理查询商城订单请求 (cmd=108)
// 2026-09-06 由 mock 接入 store: 查询角色在拍卖行的所有上架物品
func QueryShopOrderHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query shop order")
		return
	}

	logger.Info("query shop order request received",
		logger.Int64("session_id", session.ID()),
	)

	resp := &dnfv1.BuyItemResponse{
		Error:     0,
		NewItems:  make([]*dnfv1.BagItem, 0),
	}

	if shopStore != nil {
		roleID := session.RoleID()
		items, err := shopStore.ListAuctionItems(context.Background(), &store.FindAuctionItem{
			SellerID: &roleID,
		})
		if err != nil {
			logger.Error("failed to list auction items for shop order query",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
			writeShopExtResponse(session, 109, resp)
			return
		}
		// 拍卖品映射为背包条目(ItemID/Count/Slot=拍卖ID)
		for _, it := range items {
			resp.NewItems = append(resp.NewItems, &dnfv1.BagItem{
				Guid:   it.ID,
				ItemId: uint32(it.ItemID),
				Count:  it.Count,
				Slot:   int32(it.ID),
			})
		}
	}

	_ = req
	writeShopExtResponse(session, 109, resp)
}

// CancelShopOrderHandler 处理取消商城订单请求 (cmd=110)
// 2026-09-06 由 mock 接入 store: 取消角色所有出售中的拍卖物品
func CancelShopOrderHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for cancel shop order")
		return
	}

	logger.Info("cancel shop order request received",
		logger.Int64("session_id", session.ID()),
	)

	resp := &dnfv1.BuyItemResponse{
		Error:     0,
		NewItems:  make([]*dnfv1.BagItem, 0),
	}

	if shopStore != nil {
		roleID := session.RoleID()
		items, err := shopStore.ListAuctionItems(context.Background(), &store.FindAuctionItem{
			SellerID: &roleID,
		})
		if err != nil {
			logger.Error("failed to list auction items for cancel",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
			writeShopExtResponse(session, 111, resp)
			return
		}
		for _, it := range items {
			if it.Status != store.AuctionStatusSelling {
				continue
			}
			status := store.AuctionStatusCancelled
			if err := shopStore.UpdateAuctionItem(context.Background(), &store.UpdateAuctionItem{
				ID:     it.ID,
				Status: &status,
			}); err != nil {
				logger.Error("failed to cancel auction item",
					logger.ErrorField(err), logger.Int64("session_id", session.ID()))
				resp.Error = 1
				break
			}
		}
	}

	_ = req
	writeShopExtResponse(session, 111, resp)
}

// AuctionEndHandler 处理拍卖结算请求 (cmd=112)
// 2026-09-06 由 mock 接入 store: 到期物品结算(有出价者→售出, 无→过期)
// 2026-09-06 实化结算副作用: 售出→买家收物品邮件+卖家净收益入账(扣5%手续费); 流拍→卖家收回物品邮件
func AuctionEndHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for auction end")
		return
	}

	logger.Info("auction end request received",
		logger.Int64("session_id", session.ID()),
	)

	resp := &dnfv1.RegisterAuctionResponse{
		Error: 0,
	}

	if shopStore != nil {
		// 支持文本命令附加字段 seller_id 指定结算对象(测试/运营辅助), 否则用当前会话角色
		roleID := session.RoleID()
		if extras, exists := session.GetAttr("textExtras"); exists {
			if m, ok := extras.(map[string]interface{}); ok {
				if v, ok := m["seller_id"].(float64); ok {
					roleID = uint64(v)
				}
			}
		}
		items, err := shopStore.ListAuctionItems(context.Background(), &store.FindAuctionItem{
			SellerID: &roleID,
		})
		if err != nil {
			logger.Error("failed to list auction items for auction end",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
			writeShopExtResponse(session, 113, resp)
			return
		}
		now := time.Now().Unix()
		for _, it := range items {
			if it.Status != store.AuctionStatusSelling || it.EndTime > now {
				continue
			}
			status := store.AuctionStatusExpired
			if it.BidderID > 0 {
				status = store.AuctionStatusSold
			}
			if err := shopStore.UpdateAuctionItem(context.Background(), &store.UpdateAuctionItem{
				ID:     it.ID,
				Status: &status,
			}); err != nil {
				logger.Error("failed to settle auction item",
					logger.ErrorField(err), logger.Int64("session_id", session.ID()))
				resp.Error = 1
				break
			}

			// 结算副作用
			if err := settleAuctionTransfer(it, status); err != nil {
				logger.Error("failed to transfer auction proceeds",
					logger.ErrorField(err), logger.Int64("session_id", session.ID()),
					logger.Uint64("auction_id", it.ID))
				resp.Error = 1
				break
			}

			if resp.AuctionId == 0 {
				resp.AuctionId = int64(it.ID)
			}
		}
	}

	_ = req
	writeShopExtResponse(session, 113, resp)
}

// settleAuctionTransfer 结算转账: 售出→买家收物品邮件+卖家净收益入账(扣5%手续费); 流拍→物品退回卖家邮件
func settleAuctionTransfer(it *store.AuctionItem, status store.AuctionStatus) error {
	attachments := fmt.Sprintf(`{"item_id":%d,"count":%d}`, it.ItemID, it.Count)
	if status == store.AuctionStatusSold && it.BidderID > 0 {
		// 1. 买家收物品邮件
		if _, err := shopStore.CreateMail(context.Background(), &store.Mail{
			SenderID:    it.SellerID,
			SenderName:  it.SellerName,
			ReceiverID:  it.BidderID,
			Title:       "拍卖成交",
			Content:     "您在拍卖行拍得的物品已到账,请查收附件。",
			Attachments: attachments,
			ExpireAt:    time.Now().Add(30 * 24 * time.Hour).Unix(),
		}); err != nil {
			return fmt.Errorf("create buyer mail: %w", err)
		}
		// 2. 卖家净收益入账(成交价扣 5% 手续费)
		fee := it.BidPrice * 5 / 100
		cur, err := shopStore.GetRoleCurrency(context.Background(), it.SellerID)
		if err != nil {
			cur = &store.RoleCurrency{RoleID: it.SellerID}
		}
		if err := shopStore.UpdateRoleCurrency(context.Background(), &store.RoleCurrency{
			RoleID:     it.SellerID,
			Gold:       cur.Gold + it.BidPrice - fee,
			Coin:       cur.Coin,
			Fatigue:    cur.Fatigue,
			MaxFatigue: cur.MaxFatigue,
		}); err != nil {
			return fmt.Errorf("update seller currency: %w", err)
		}
		return nil
	}
	// 流拍: 物品退回卖家邮件
	if _, err := shopStore.CreateMail(context.Background(), &store.Mail{
		SenderID:    it.SellerID,
		SenderName:  "拍卖行",
		ReceiverID:  it.SellerID,
		Title:       "拍卖流拍退回",
		Content:     "您的拍卖物品因无人出价已退回,请查收附件。",
		Attachments: attachments,
		ExpireAt:    time.Now().Add(30 * 24 * time.Hour).Unix(),
	}); err != nil {
		return fmt.Errorf("create seller return mail: %w", err)
	}
	return nil
}

// AuctionFeeHandler 处理拍卖手续费请求 (cmd=114)
// 2026-09-06 由 mock 接入 store: 统计角色已售出物品的 5% 手续费
func AuctionFeeHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for auction fee")
		return
	}

	logger.Info("auction fee request received",
		logger.Int64("session_id", session.ID()),
	)

	resp := &dnfv1.RegisterAuctionResponse{
		Error: 0,
	}

	if shopStore != nil {
		roleID := session.RoleID()
		items, err := shopStore.ListAuctionItems(context.Background(), &store.FindAuctionItem{
			SellerID: &roleID,
		})
		if err != nil {
			logger.Error("failed to list auction items for fee",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
			writeShopExtResponse(session, 115, resp)
			return
		}
		// 已售出物品按成交价 5% 计手续费, 塞入 auction_id 字段回传
		var fee int64
		for _, it := range items {
			if it.Status == store.AuctionStatusSold {
				fee += it.BidPrice * 5 / 100
			}
		}
		resp.AuctionId = fee
	}

	_ = req
	writeShopExtResponse(session, 115, resp)
}

// writeShopExtResponse 发送商店扩展模块响应
func writeShopExtResponse(session *network.Session, respCmd uint16, msg proto.Message) {
	if err := session.WriteResponse(10005, respCmd, msg); err != nil {
		logger.Error("failed to send shop ext response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
