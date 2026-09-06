package handlers

import (
	"context"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"google.golang.org/protobuf/proto"
)

// skillStore 技能 Store(由 serve.go 注入, 2026-09-07 第四十四轮实化)
var skillStore *store.Store

// InitSkillStore 初始化技能 Store
func InitSkillStore(s *store.Store) {
	skillStore = s
}

// skillErrorCode 技能错误码
const (
	skillErrorOK         = 0 // 成功
	skillErrorParam      = 1 // 参数错误/未绑定角色
	skillErrorNotFound   = 2 // 技能不存在
	skillErrorCondition  = 3 // 条件不足(职业/等级/前置)
	skillErrorNoSP       = 4 // 技能点不足
	skillErrorLearned    = 5 // 已学习(重复学习)
	skillErrorNotLearned = 6 // 未学习(无法升级)
	skillErrorMaxLevel   = 7 // 已满级
)

// skillIDFromPayload 读取技能ID: protobuf 请求优先, 否则读文本命令 JSON payload(LEARN_SKILL:{"skill_id":N})
func skillIDFromPayload(session *network.Session, reqSkillID int32) (int32, bool) {
	if reqSkillID > 0 {
		return reqSkillID, true
	}
	if extras, exists := session.GetAttr("textExtras"); exists {
		if m, ok := extras.(map[string]interface{}); ok {
			if v, ok := m["skill_id"].(float64); ok {
				return int32(v), true
			}
		}
	}
	return 0, false
}

// writeLearnSkillResp 写学习/升级技能响应
func writeSkillResp(session *network.Session, respCmd uint16, errorCode int32, skill *dnfv1.SkillInfo) {
	var resp proto.Message
	if respCmd == 5 {
		resp = &dnfv1.LearnSkillResponse{Error: errorCode, Skill: skill}
	} else {
		resp = &dnfv1.UpgradeSkillResponse{Error: errorCode, Skill: skill}
	}
	if err := session.WriteResponse(10001, respCmd, resp); err != nil {
		logger.Error("failed to send skill response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// loadSkillAndRole 加载技能配置与角色(未绑定/技能不存在返回对应错误码)
func loadSkillAndRole(session *network.Session, skillID int32) (*store.Skill, *store.Role, int32) {
	if skillStore == nil {
		return nil, nil, skillErrorParam
	}
	roleID := session.RoleID()
	if roleID == 0 {
		return nil, nil, skillErrorParam
	}
	ctx := context.Background()
	skill, err := skillStore.GetSkill(ctx, &store.FindSkill{SkillID: &skillID})
	if err != nil || skill == nil {
		return nil, nil, skillErrorNotFound
	}
	// NoCache: 技能点扣减需实时值, 避免缓存遮蔽(测试直插/运营直改)
	role, err := skillStore.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}, NoCache: true})
	if err != nil || role == nil {
		return nil, nil, skillErrorParam
	}
	return skill, role, skillErrorOK
}

// checkSkillCondition 校验职业/等级/前置条件, 通过返回 errorCode=0
func checkSkillCondition(ctx context.Context, skill *store.Skill, role *store.Role) int32 {
	if skill.JobRequired != 0 && role.Job != skill.JobRequired {
		return skillErrorCondition
	}
	if role.Level < skill.LevelRequired {
		return skillErrorCondition
	}
	if skill.PreSkillID != 0 {
		pre, err := skillStore.GetRoleSkill(ctx, &store.FindRoleSkill{
			RoleID:  &role.ID,
			SkillID: &skill.PreSkillID,
		})
		if err != nil || pre == nil || !pre.IsLearned || pre.Level < skill.PreSkillLevel {
			return skillErrorCondition
		}
	}
	return skillErrorOK
}

// consumeSkillSP 扣减角色技能点, 返回新 SP 与错误码
func consumeSkillSP(ctx context.Context, role *store.Role, cost int32) (int32, int32) {
	if role.SP < cost {
		return role.SP, skillErrorNoSP
	}
	newSP := role.SP - cost
	if _, err := skillStore.UpdateRole(ctx, &store.UpdateRole{ID: role.ID, SP: &newSP}); err != nil {
		logger.Error("failed to deduct skill points",
			logger.ErrorField(err),
			logger.Int64("role_id", int64(role.ID)),
		)
		return role.SP, skillErrorParam
	}
	return newSP, skillErrorOK
}

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

// LearnSkillHandler 处理学习技能请求(2026-09-07 第四十四轮实化):
// 校验 技能存在/职业/等级/前置/已学/SP → 扣 SP + 写 role_skills
func LearnSkillHandler(session *network.Session, msg proto.Message) {
	var reqSkillID int32
	if req, ok := msg.(*dnfv1.LearnSkillRequest); ok {
		reqSkillID = req.SkillId
	}
	skillID, ok := skillIDFromPayload(session, reqSkillID)
	if !ok {
		writeSkillResp(session, 5, skillErrorParam, nil)
		return
	}

	ctx := context.Background()
	skill, role, errCode := loadSkillAndRole(session, skillID)
	if errCode != skillErrorOK {
		writeSkillResp(session, 5, errCode, nil)
		return
	}

	// 已学幂等拒绝
	rs, err := skillStore.GetRoleSkill(ctx, &store.FindRoleSkill{RoleID: &role.ID, SkillID: &skillID})
	if err == nil && rs != nil && rs.IsLearned {
		writeSkillResp(session, 5, skillErrorLearned, nil)
		return
	}

	if errCode := checkSkillCondition(ctx, skill, role); errCode != skillErrorOK {
		writeSkillResp(session, 5, errCode, nil)
		return
	}

	newSP, errCode := consumeSkillSP(ctx, role, skill.SP)
	if errCode != skillErrorOK {
		writeSkillResp(session, 5, errCode, nil)
		return
	}

	if _, err := skillStore.CreateRoleSkill(ctx, &store.CreateRoleSkill{
		RoleID:    role.ID,
		SkillID:   skillID,
		Level:     1,
		IsLearned: true,
	}); err != nil {
		logger.Error("failed to create role skill",
			logger.ErrorField(err),
			logger.Int64("role_id", int64(role.ID)),
		)
		// 回滚 SP
		skillStore.UpdateRole(ctx, &store.UpdateRole{ID: role.ID, SP: &role.SP})
		writeSkillResp(session, 5, skillErrorParam, nil)
		return
	}

	logger.Info("skill learned",
		logger.Int32("skill_id", skillID),
		logger.Int32("remain_sp", newSP),
		logger.Int64("role_id", int64(role.ID)),
	)
	writeSkillResp(session, 5, skillErrorOK, &dnfv1.SkillInfo{
		SkillId:  skillID,
		Level:    1,
		MaxLevel: skill.MaxLevel,
		SpCost:   skill.SP,
	})
}

// UpgradeSkillHandler 处理升级技能请求(2026-09-07 第四十四轮实化):
// 校验 已学/未满级/SP → 扣 SP + 提升技能等级
func UpgradeSkillHandler(session *network.Session, msg proto.Message) {
	var reqSkillID int32
	if req, ok := msg.(*dnfv1.UpgradeSkillRequest); ok {
		reqSkillID = req.SkillId
	}
	skillID, ok := skillIDFromPayload(session, reqSkillID)
	if !ok {
		writeSkillResp(session, 7, skillErrorParam, nil)
		return
	}

	ctx := context.Background()
	skill, role, errCode := loadSkillAndRole(session, skillID)
	if errCode != skillErrorOK {
		writeSkillResp(session, 7, errCode, nil)
		return
	}

	rs, err := skillStore.GetRoleSkill(ctx, &store.FindRoleSkill{RoleID: &role.ID, SkillID: &skillID})
	if err != nil || rs == nil || !rs.IsLearned {
		writeSkillResp(session, 7, skillErrorNotLearned, nil)
		return
	}
	if rs.Level >= skill.MaxLevel {
		writeSkillResp(session, 7, skillErrorMaxLevel, nil)
		return
	}

	newSP, errCode := consumeSkillSP(ctx, role, skill.SP)
	if errCode != skillErrorOK {
		writeSkillResp(session, 7, errCode, nil)
		return
	}

	newLevel := rs.Level + 1
	if err := skillStore.UpdateRoleSkill(ctx, &store.UpdateRoleSkill{ID: rs.ID, Level: &newLevel}); err != nil {
		logger.Error("failed to upgrade role skill",
			logger.ErrorField(err),
			logger.Int64("role_id", int64(role.ID)),
		)
		// 回滚 SP
		skillStore.UpdateRole(ctx, &store.UpdateRole{ID: role.ID, SP: &role.SP})
		writeSkillResp(session, 7, skillErrorParam, nil)
		return
	}

	logger.Info("skill upgraded",
		logger.Int32("skill_id", skillID),
		logger.Int32("level", newLevel),
		logger.Int32("remain_sp", newSP),
		logger.Int64("role_id", int64(role.ID)),
	)
	writeSkillResp(session, 7, skillErrorOK, &dnfv1.SkillInfo{
		SkillId:  skillID,
		Level:    newLevel,
		MaxLevel: skill.MaxLevel,
		SpCost:   skill.SP,
	})
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
