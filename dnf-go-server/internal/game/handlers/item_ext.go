package handlers

import (
	"context"
	"sort"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

var itemStore *store.Store

// InitItemStore 初始化背包物品 Store
func InitItemStore(s *store.Store) {
	itemStore = s
}

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

	resp := &dnfv1.UseItemResponse{
		Error:          0,
		UpdatedItems:   make([]*dnfv1.BagItem, 0),
	}

	if itemStore != nil {
		// 校验物品归属后删除
		items, err := itemStore.ListBagItemsByRole(context.Background(), session.RoleID())
		if err != nil {
			logger.Error("failed to list bag items for drop",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
			writeItemExtResponse(session, 11, resp)
			return
		}
		found := false
		for _, it := range items {
			if it.ID == req.Guid {
				found = true
				break
			}
		}
		if !found {
			resp.Error = 2 // 物品不存在或不属于该角色
			writeItemExtResponse(session, 11, resp)
			return
		}
		if err := itemStore.DeleteBagItem(context.Background(), &store.DeleteBagItem{ID: req.Guid}); err != nil {
			logger.Error("failed to delete bag item",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
		}
	}

	writeItemExtResponse(session, 11, resp)
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

	resp := &dnfv1.UseItemResponse{
		Error:        0,
		UpdatedItems: make([]*dnfv1.BagItem, 0),
	}

	if itemStore != nil {
		// compose_id 对应目标格子;材料列表由客户端传入
		result, err := itemStore.ItemCombine(context.Background(), session.RoleID(), int32(req.Guid), nil, 1)
		if err != nil {
			logger.Error("failed to combine items",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
		} else if result != nil && result.Equip != nil {
			resp.UpdatedItems = append(resp.UpdatedItems, &dnfv1.BagItem{
				Guid:   result.Equip.Guid,
				ItemId: uint32(result.Equip.ItemId),
				Count:  1,
				Slot:   int32(req.Guid),
			})
		}
	}

	writeItemExtResponse(session, 13, resp)
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

	resp := &dnfv1.UseItemResponse{
		Error:        0,
		UpdatedItems: make([]*dnfv1.BagItem, 0),
	}

	if itemStore != nil {
		items, err := itemStore.ListBagItemsByRole(context.Background(), session.RoleID())
		if err != nil {
			logger.Error("failed to list bag items for reinforce",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
			writeItemExtResponse(session, 15, resp)
			return
		}
		var target *store.BagItem
		for _, it := range items {
			if it.ID == req.Guid {
				target = it
				break
			}
		}
		if target == nil {
			resp.Error = 2
			writeItemExtResponse(session, 15, resp)
			return
		}
		nextLevel := target.EnhanceLevel + 1
		if err := itemStore.UpdateBagItem(context.Background(), &store.UpdateBagItem{
			ID:           req.Guid,
			EnhanceLevel: &nextLevel,
		}); err != nil {
			logger.Error("failed to reinforce bag item",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
		} else {
			resp.UpdatedItems = append(resp.UpdatedItems, &dnfv1.BagItem{
				Guid:   req.Guid,
				ItemId: uint32(target.ItemID),
				Count:  target.Count,
				Slot:   target.GridIndex,
				Details: &dnfv1.BagItem_Equipment{
					Equipment: &dnfv1.EquipmentInfo{
						Guid:           req.Guid,
						ItemId:         uint32(target.ItemID),
						Slot:           dnfv1.EquipSlot_WEAPON,
						ReinforceLevel: nextLevel,
						Durability:     target.Durability,
						MaxDurability:  100,
					},
				},
			})
		}
	}

	writeItemExtResponse(session, 15, resp)
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

	bag := &dnfv1.BagInfo{
		BagType: req.BagType,
		MaxSlot: 50,
		Items:   make([]*dnfv1.BagItem, 0),
	}

	if itemStore != nil {
		items, err := itemStore.ListBagItemsByRole(context.Background(), session.RoleID())
		if err != nil {
			logger.Error("failed to list bag items for sort",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
		} else {
			// 按物品ID排序后紧凑重排格子
			sort.Slice(items, func(i, j int) bool {
				if items[i].ItemID != items[j].ItemID {
					return items[i].ItemID < items[j].ItemID
				}
				return items[i].ID < items[j].ID
			})
			for slot, it := range items {
				bag.Items = append(bag.Items, &dnfv1.BagItem{
					Guid:   it.ID,
					ItemId: uint32(it.ItemID),
					Count:  it.Count,
					Slot:   int32(slot),
				})
			}
		}
	}

	resp := &dnfv1.GetBagResponse{
		Error: 0,
		Bag:   bag,
	}
	writeItemExtResponse(session, 17, resp)
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

	resp := &dnfv1.UseItemResponse{
		Error:        0,
		UpdatedItems: make([]*dnfv1.BagItem, 0),
	}

	if itemStore != nil {
		result, err := itemStore.ItemDisjoint(context.Background(), session.RoleID(), []uint64{req.Guid})
		if err != nil {
			logger.Error("failed to disjoint items",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
		} else if result != nil && result.Rewards != nil && result.Rewards.Items != nil {
			for _, m := range result.Rewards.Items.MaterialItems {
				resp.UpdatedItems = append(resp.UpdatedItems, &dnfv1.BagItem{
					Guid:   uint64(m.Index),
					ItemId: uint32(m.Index),
					Count:  int32(m.Count),
					Slot:   0,
				})
			}
		}
	}

	writeItemExtResponse(session, 19, resp)
}

// ItemRenameHandler 处理物品重命名请求 (cmd=20)
// 2026-09-06 由 mock 接入 store: 名称(文本命令附加字段 name)写入 bag_item.attributes(JSON)
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

	// 读取文本命令附加字段 name(proto 未定义,由 codec TextExtras 透传)
	name := ""
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["name"].(string); ok {
				name = v
			}
		}
	}

	resp := &dnfv1.UseItemResponse{
		Error:        0,
		UpdatedItems: make([]*dnfv1.BagItem, 0),
	}

	if itemStore != nil {
		items, err := itemStore.ListBagItemsByRole(context.Background(), session.RoleID())
		if err != nil {
			logger.Error("failed to list bag items for rename",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
			writeItemExtResponse(session, 21, resp)
			return
		}
		var target *store.BagItem
		for _, it := range items {
			if it.ID == req.Guid {
				target = it
				break
			}
		}
		if target == nil {
			resp.Error = 2
			writeItemExtResponse(session, 21, resp)
			return
		}
		if name == "" {
			name = "已命名"
		}
		attrs := `{"name":"` + name + `"}`
		if err := itemStore.UpdateBagItem(context.Background(), &store.UpdateBagItem{
			ID:         req.Guid,
			Attributes: &attrs,
		}); err != nil {
			logger.Error("failed to rename bag item",
				logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			resp.Error = 1
		} else {
			resp.UpdatedItems = append(resp.UpdatedItems, &dnfv1.BagItem{
				Guid:   req.Guid,
				ItemId: uint32(target.ItemID),
				Count:  target.Count,
				Slot:   target.GridIndex,
			})
		}
	}

	writeItemExtResponse(session, 21, resp)
}

// BagExpandHandler 处理背包扩容请求 (cmd=22)
// 2026-09-06 由 mock 接入 store: 扩容槽位(文本命令附加字段 slots)持久化到 t_bag_expand
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

	// 读取文本命令附加字段 slots(proto 未定义,由 codec TextExtras 透传)
	slots := int32(0)
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["slots"].(float64); ok {
				slots = int32(v)
			}
		}
	}

	maxSlot := int32(50)
	if itemStore != nil {
		roleID := session.RoleID()
		expand, err := itemStore.GetBagExpand(context.Background(), &store.FindBagExpand{
			RoleID:  roleID,
			BagType: &req.BagType,
		})
		if err == nil && expand != nil {
			maxSlot = 50 + expand.Capacity
		}
		if slots > 0 {
			_, err := itemStore.UpsertBagExpand(context.Background(), &store.BagExpand{
				RoleID:   roleID,
				BagType:  req.BagType,
				Capacity: slots,
			})
			if err != nil {
				logger.Error("failed to upsert bag expand",
					logger.ErrorField(err), logger.Int64("session_id", session.ID()))
			} else {
				maxSlot = 50 + slots
			}
		}
	}

	bag := &dnfv1.BagInfo{
		BagType: req.BagType,
		MaxSlot: maxSlot,
		Items:   make([]*dnfv1.BagItem, 0),
	}

	resp := &dnfv1.GetBagResponse{
		Error: 0,
		Bag:   bag,
	}
	writeItemExtResponse(session, 23, resp)
}

// writeItemExtResponse 发送背包扩展模块响应
func writeItemExtResponse(session *network.Session, respCmd uint16, msg proto.Message) {
	if err := session.WriteResponse(10002, respCmd, msg); err != nil {
		logger.Error("failed to send item ext response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
