package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// ==================== 背包模块扩展 (Module = 10002) ====================

// DropItemHandler 处理丢弃物品请求 (cmd=10)
func DropItemHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UseItemRequest)
	if !ok {
		logger.Error("invalid message type for drop item")
		return
	}

	logger.Info("drop item request received",
		logger.Uint64("guid", req.Guid),
		logger.Int32("count", req.Count),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品归属，从背包移除物品
	resp := &dnfv1.UseItemResponse{
		Error: 0,
		UpdatedItems: []*dnfv1.BagItem{
			{
				Guid:   req.Guid,
				ItemId: 20001,
				Count:  req.Count,
				Slot:   0,
			},
		},
	}

	if err := session.WriteResponse(10002, 11, resp); err != nil {
		logger.Error("failed to send drop item response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// ItemComposeHandler 处理物品合成请求 (cmd=12)
func ItemComposeHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UseItemRequest)
	if !ok {
		logger.Error("invalid message type for item compose")
		return
	}

	logger.Info("item compose request received",
		logger.Uint64("guid", req.Guid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 校验合成配方与材料，产出合成物品
	resp := &dnfv1.UseItemResponse{
		Error: 0,
		UpdatedItems: []*dnfv1.BagItem{
			{
				Guid:   req.Guid,
				ItemId: 30001,
				Count:  1,
				Slot:   0,
			},
		},
	}

	if err := session.WriteResponse(10002, 13, resp); err != nil {
		logger.Error("failed to send item compose response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// ItemReinforceHandler 处理物品强化请求 (cmd=14)
func ItemReinforceHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UseItemRequest)
	if !ok {
		logger.Error("invalid message type for item reinforce")
		return
	}

	logger.Info("item reinforce request received",
		logger.Uint64("guid", req.Guid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证强化材料与金币，执行强化概率
	resp := &dnfv1.UseItemResponse{
		Error: 0,
		UpdatedItems: []*dnfv1.BagItem{
			{
				Guid:   req.Guid,
				ItemId: 20001,
				Count:  1,
				Slot:   0,
				Details: &dnfv1.BagItem_Equipment{
					Equipment: &dnfv1.EquipmentInfo{
						Guid:           req.Guid,
						ItemId:         20001,
						Slot:           dnfv1.EquipSlot_WEAPON,
						ReinforceLevel: 11,
						Durability:     100,
						MaxDurability:  100,
					},
				},
			},
		},
	}

	if err := session.WriteResponse(10002, 15, resp); err != nil {
		logger.Error("failed to send item reinforce response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// ItemSortHandler 处理物品整理请求 (cmd=16)
func ItemSortHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetBagRequest)
	if !ok {
		logger.Error("invalid message type for item sort")
		return
	}

	logger.Info("item sort request received",
		logger.Int32("bag_type", req.BagType),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 按类型/等级对背包物品排序
	bag := &dnfv1.BagInfo{
		BagType: req.BagType,
		MaxSlot: 50,
		Items:   make([]*dnfv1.BagItem, 0),
	}

	resp := &dnfv1.GetBagResponse{
		Error: 0,
		Bag:   bag,
	}

	if err := session.WriteResponse(10002, 17, resp); err != nil {
		logger.Error("failed to send item sort response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// ItemDecomposeHandler 处理物品分解请求 (cmd=18)
func ItemDecomposeHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UseItemRequest)
	if !ok {
		logger.Error("invalid message type for item decompose")
		return
	}

	logger.Info("item decompose request received",
		logger.Uint64("guid", req.Guid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品可分解性，产出分解材料
	resp := &dnfv1.UseItemResponse{
		Error: 0,
		UpdatedItems: []*dnfv1.BagItem{
			{
				Guid:   req.Guid,
				ItemId: 40001,
				Count:  5,
				Slot:   0,
			},
		},
	}

	if err := session.WriteResponse(10002, 19, resp); err != nil {
		logger.Error("failed to send item decompose response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// ItemRenameHandler 处理物品重命名请求 (cmd=20)
func ItemRenameHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UseItemRequest)
	if !ok {
		logger.Error("invalid message type for item rename")
		return
	}

	logger.Info("item rename request received",
		logger.Uint64("guid", req.Guid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品可命名性，更新自定义名称
	resp := &dnfv1.UseItemResponse{
		Error: 0,
		UpdatedItems: []*dnfv1.BagItem{
			{
				Guid:   req.Guid,
				ItemId: 20001,
				Count:  1,
				Slot:   0,
			},
		},
	}

	if err := session.WriteResponse(10002, 21, resp); err != nil {
		logger.Error("failed to send item rename response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// BagExpandHandler 处理背包扩容请求 (cmd=22)
func BagExpandHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetBagRequest)
	if !ok {
		logger.Error("invalid message type for bag expand")
		return
	}

	logger.Info("bag expand request received",
		logger.Int32("bag_type", req.BagType),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 校验扩展费用，扩大背包槽位
	bag := &dnfv1.BagInfo{
		BagType: req.BagType,
		MaxSlot: 60,
		Items:   make([]*dnfv1.BagItem, 0),
	}

	resp := &dnfv1.GetBagResponse{
		Error: 0,
		Bag:   bag,
	}

	if err := session.WriteResponse(10002, 23, resp); err != nil {
		logger.Error("failed to send bag expand response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
