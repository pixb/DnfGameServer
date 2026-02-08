package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// GetBagHandler 处理获取背包请求
func GetBagHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetBagRequest)
	if !ok {
		logger.Error("invalid message type for get bag")
		return
	}

	logger.Info("get bag request received",
		logger.Int32("bag_type", req.BagType),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 从数据库加载背包数据
	// 模拟返回背包数据
	bag := &dnfv1.BagInfo{
		BagType: req.BagType,
		MaxSlot: 50,
		Items:   make([]*dnfv1.BagItem, 0),
	}

	// 根据背包类型返回不同的模拟数据
	switch req.BagType {
	case 1: // 装备背包
		bag.Items = []*dnfv1.BagItem{
			{
				Guid:   1001,
				ItemId: 10001,
				Count:  1,
				Slot:   0,
				Details: &dnfv1.BagItem_Equipment{
					Equipment: &dnfv1.EquipmentInfo{
						Guid:           1001,
						ItemId:         10001,
						Slot:           dnfv1.EquipSlot_WEAPON,
						ReinforceLevel: 10,
						Durability:     100,
						MaxDurability:  100,
						Options: []*dnfv1.EquipmentOption{
							{OptionId: 1, OptionValue: 50},
							{OptionId: 2, OptionValue: 30},
						},
					},
				},
			},
			{
				Guid:   1002,
				ItemId: 10002,
				Count:  1,
				Slot:   1,
				Details: &dnfv1.BagItem_Equipment{
					Equipment: &dnfv1.EquipmentInfo{
						Guid:           1002,
						ItemId:         10002,
						Slot:           dnfv1.EquipSlot_ARMOR,
						ReinforceLevel: 8,
						Durability:     80,
						MaxDurability:  100,
					},
				},
			},
		}
	case 2: // 消耗品背包
		bag.Items = []*dnfv1.BagItem{
			{
				Guid:   2001,
				ItemId: 20001,
				Count:  99,
				Slot:   0,
				Details: &dnfv1.BagItem_Consumable{
					Consumable: &dnfv1.ConsumableInfo{
						CooldownId:   1,
						CooldownTime: 30,
					},
				},
			},
			{
				Guid:   2002,
				ItemId: 20002,
				Count:  50,
				Slot:   1,
			},
		}
	case 3: // 材料背包
		bag.Items = []*dnfv1.BagItem{
			{
				Guid:   3001,
				ItemId: 30001,
				Count:  999,
				Slot:   0,
			},
		}
	}

	resp := &dnfv1.GetBagResponse{
		Error: 0,
		Bag:   bag,
	}

	if err := session.WriteResponse(10002, 1, resp); err != nil {
		logger.Error("failed to send get bag response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// UseItemHandler 处理使用物品请求
func UseItemHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UseItemRequest)
	if !ok {
		logger.Error("invalid message type for use item")
		return
	}

	logger.Info("use item request received",
		logger.Uint64("guid", req.Guid),
		logger.Int32("count", req.Count),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品是否存在，检查冷却时间，使用物品
	resp := &dnfv1.UseItemResponse{
		Error: 0,
		UpdatedItems: []*dnfv1.BagItem{
			{
				Guid:   req.Guid,
				ItemId: 20001,
				Count:  98, // 使用了一个
				Slot:   0,
			},
		},
	}

	if err := session.WriteResponse(10002, 3, resp); err != nil {
		logger.Error("failed to send use item response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// MoveItemHandler 处理移动物品请求
func MoveItemHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.MoveItemRequest)
	if !ok {
		logger.Error("invalid message type for move item")
		return
	}

	logger.Info("move item request received",
		logger.Uint64("guid", req.Guid),
		logger.Int32("from_bag", req.FromBag),
		logger.Int32("to_bag", req.ToBag),
		logger.Int32("to_slot", req.ToSlot),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品，检查目标位置是否合法，移动物品
	resp := &dnfv1.MoveItemResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10002, 5, resp); err != nil {
		logger.Error("failed to send move item response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// SellItemHandler 处理出售物品请求
func SellItemHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.SellItemRequest)
	if !ok {
		logger.Error("invalid message type for sell item")
		return
	}

	logger.Info("sell item request received",
		logger.Uint64("guid", req.Guid),
		logger.Int32("count", req.Count),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品，计算价格，删除物品，增加金币
	resp := &dnfv1.SellItemResponse{
		Error:        0,
		GoldReceived: 1000 * req.Count,
	}

	if err := session.WriteResponse(10002, 7, resp); err != nil {
		logger.Error("failed to send sell item response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// EquipItemHandler 处理装备穿戴请求
func EquipItemHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.EquipItemRequest)
	if !ok {
		logger.Error("invalid message type for equip item")
		return
	}

	logger.Info("equip item request received",
		logger.Uint64("guid", req.Guid),
		logger.Bool("equip", req.Equip),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证物品，检查等级职业要求，穿戴/卸下装备
	resp := &dnfv1.EquipItemResponse{
		Error: 0,
		UpdatedItems: []*dnfv1.BagItem{
			{
				Guid:   req.Guid,
				ItemId: 10001,
				Count:  1,
				Slot:   0,
				Details: &dnfv1.BagItem_Equipment{
					Equipment: &dnfv1.EquipmentInfo{
						Guid:           req.Guid,
						ItemId:         10001,
						Slot:           dnfv1.EquipSlot_WEAPON,
						ReinforceLevel: 10,
						Durability:     100,
						MaxDurability:  100,
					},
				},
			},
		},
	}

	if err := session.WriteResponse(10002, 9, resp); err != nil {
		logger.Error("failed to send equip item response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
