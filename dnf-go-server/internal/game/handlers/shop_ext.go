package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// ==================== 商店模块扩展 (Module = 10005) ====================

// QueryShopOrderHandler 处理查询商城订单请求 (cmd=108)
func QueryShopOrderHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for query shop order")
		return
	}

	logger.Info("query shop order request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 查询角色的商城订单列表
	_ = req
	resp := &dnfv1.BuyItemResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10005, 109, resp); err != nil {
		logger.Error("failed to send query shop order response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// CancelShopOrderHandler 处理取消商城订单请求 (cmd=110)
func CancelShopOrderHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for cancel shop order")
		return
	}

	logger.Info("cancel shop order request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 校验订单归属与状态，取消并退回款项
	_ = req
	resp := &dnfv1.BuyItemResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10005, 111, resp); err != nil {
		logger.Error("failed to send cancel shop order response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// AuctionEndHandler 处理拍卖结算请求 (cmd=112)
func AuctionEndHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for auction end")
		return
	}

	logger.Info("auction end request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 结束拍卖，将物品转移给出价最高者，结算金币
	_ = req
	resp := &dnfv1.RegisterAuctionResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10005, 113, resp); err != nil {
		logger.Error("failed to send auction end response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// AuctionFeeHandler 处理拍卖手续费请求 (cmd=114)
func AuctionFeeHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for auction fee")
		return
	}

	logger.Info("auction fee request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 计算并收取拍卖手续费
	_ = req
	resp := &dnfv1.RegisterAuctionResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10005, 115, resp); err != nil {
		logger.Error("failed to send auction fee response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
