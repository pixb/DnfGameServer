package handlers

import (
	"dnf-go-server/internal/network"
	"dnf-go-server/internal/utils/logger"
	dnfv1 "dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// GetRoleInfoHandler 处理获取角色信息请求
func GetRoleInfoHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetRoleInfoRequest)
	if !ok {
		logger.Error("invalid message type for get role info")
		return
	}

	logger.Info("get role info request received",
		logger.Int64("uid", req.Uid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 从数据库加载角色信息
	// 这里模拟返回数据
	resp := &dnfv1.GetRoleInfoResponse{
		Error: 0,
		BaseInfo: &dnfv1.RoleBaseInfo{
			Uid:        req.Uid,
			RoleId:     1,
			Name:       "勇者",
			Job:        1,
			Level:      50,
			Exp:        1000000,
			Fatigue:    100,
			MaxFatigue: 156,
		},
		BattleInfo: &dnfv1.RoleBattleInfo{
			Str:       100,
			Dex:       100,
			Vit:       100,
			Spr:       100,
			Hp:        10000,
			MaxHp:     10000,
			Mp:        5000,
			MaxMp:     5000,
			Atk:       500,
			Def:       300,
			MagicAtk:  400,
			MagicDef:  250,
			MoveSpeed: 100,
			AtkSpeed:  100,
			CastSpeed: 100,
		},
		Position: &dnfv1.RolePosition{
			MapId:     1,
			DungeonId: 0,
			X:         100.0,
			Y:         100.0,
			Z:         0.0,
		},
		Skills: []*dnfv1.SkillInfo{
			{
				SkillId:  1,
				Level:    5,
				MaxLevel: 10,
				SpCost:   20,
				Cooldown: 0,
			},
			{
				SkillId:  2,
				Level:    3,
				MaxLevel: 10,
				SpCost:   30,
				Cooldown: 5,
			},
		},
	}

	if err := session.WriteResponse(10001, 1, resp); err != nil {
		logger.Error("failed to send get role info response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// UpdateAttributesHandler 处理更新属性请求
func UpdateAttributesHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UpdateAttributesRequest)
	if !ok {
		logger.Error("invalid message type for update attributes")
		return
	}

	logger.Info("update attributes request received",
		logger.Int32("str", req.Str),
		logger.Int32("dex", req.Dex),
		logger.Int32("vit", req.Vit),
		logger.Int32("spr", req.Spr),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证属性点是否足够并更新
	resp := &dnfv1.UpdateAttributesResponse{
		Error: 0,
		BattleInfo: &dnfv1.RoleBattleInfo{
			Str:       req.Str,
			Dex:       req.Dex,
			Vit:       req.Vit,
			Spr:       req.Spr,
			Hp:        10000,
			MaxHp:     10000,
			Mp:        5000,
			MaxMp:     5000,
			Atk:       500 + req.Str*5,
			Def:       300 + req.Vit*3,
			MagicAtk:  400 + req.Spr*4,
			MagicDef:  250 + req.Spr*2,
			MoveSpeed: 100,
			AtkSpeed:  100,
			CastSpeed: 100,
		},
	}

	if err := session.WriteResponse(10001, 3, resp); err != nil {
		logger.Error("failed to send update attributes response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// LearnSkillHandler 处理学习技能请求
func LearnSkillHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.LearnSkillRequest)
	if !ok {
		logger.Error("invalid message type for learn skill")
		return
	}

	logger.Info("learn skill request received",
		logger.Int32("skill_id", req.SkillId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查SP是否足够，学习技能
	resp := &dnfv1.LearnSkillResponse{
		Error: 0,
		Skill: &dnfv1.SkillInfo{
			SkillId:  req.SkillId,
			Level:    1,
			MaxLevel: 10,
			SpCost:   20,
			Cooldown: 0,
		},
	}

	if err := session.WriteResponse(10001, 5, resp); err != nil {
		logger.Error("failed to send learn skill response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// UpgradeSkillHandler 处理升级技能请求
func UpgradeSkillHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UpgradeSkillRequest)
	if !ok {
		logger.Error("invalid message type for upgrade skill")
		return
	}

	logger.Info("upgrade skill request received",
		logger.Int32("skill_id", req.SkillId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查技能是否已学习，SP是否足够
	resp := &dnfv1.UpgradeSkillResponse{
		Error: 0,
		Skill: &dnfv1.SkillInfo{
			SkillId:  req.SkillId,
			Level:    2,
			MaxLevel: 10,
			SpCost:   30,
			Cooldown: 0,
		},
	}

	if err := session.WriteResponse(10001, 7, resp); err != nil {
		logger.Error("failed to send upgrade skill response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// RecoverFatigueHandler 处理恢复疲劳值请求
func RecoverFatigueHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.RecoverFatigueRequest)
	if !ok {
		logger.Error("invalid message type for recover fatigue")
		return
	}

	logger.Info("recover fatigue request received",
		logger.Int32("item_id", req.ItemId),
		logger.Int32("recover_amount", req.RecoverAmount),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查物品或货币，恢复疲劳值
	resp := &dnfv1.RecoverFatigueResponse{
		Error:          0,
		CurrentFatigue: 156,
		MaxFatigue:     156,
	}

	if err := session.WriteResponse(10001, 9, resp); err != nil {
		logger.Error("failed to send recover fatigue response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// RoleLevelUpNotifyHandler 发送角色升级通知
func RoleLevelUpNotify(session *network.Session, uid int64, oldLevel, newLevel int32, newExp int64) {
	notify := &dnfv1.RoleLevelUpNotify{
		Uid:      uid,
		OldLevel: oldLevel,
		NewLevel: newLevel,
		NewExp:   newExp,
	}

	// 通知广播给相关玩家
	if err := session.WriteResponse(10001, 100, notify); err != nil {
		logger.Error("failed to send level up notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
