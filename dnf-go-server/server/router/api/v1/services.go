package v1

import (
	"context"
	"fmt"
	"strings"
	"time"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/server/auth"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
	"google.golang.org/protobuf/types/known/emptypb"
)

// 错误码定义
const (
	ErrCodeSuccess          = 0
	ErrCodeInvalidParam     = 1
	ErrCodeAccountNotFound  = 2
	ErrCodeRoleNameExists   = 3
	ErrCodeRoleLimitReached = 4
	ErrCodeInvalidRoleName  = 5
	ErrCodeNotFound         = 6
	ErrCodeSystemError      = 99
)

// Login 登录
func (s *APIV1Service) Login(ctx context.Context, req *dnfv1.LoginRequest) (*dnfv1.LoginResponse, error) {
	// 1. 验证账户（如果不存在则自动创建）
	account, err := s.Store.GetAccount(ctx, &store.FindAccount{
		OpenID: &req.Openid,
	})
	if err != nil {
		if err == store.ErrNotFound {
			// 自动创建账户
			account, err = s.Store.CreateAccount(ctx, &store.Account{
				OpenID:     req.Openid,
				AccountKey: "",
				AuthKey:    "",
				Authority:  0,
				Status:     0,
			})
			if err != nil {
				return nil, status.Errorf(codes.Internal, "failed to create account: %v", err)
			}
		} else {
			return nil, status.Errorf(codes.Internal, "failed to get account: %v", err)
		}
	}

	// 2. 检查账户状态
	if account.Status != 0 {
		return nil, status.Errorf(codes.PermissionDenied, "account is banned")
	}

	// 3. 更新登录信息
	now := time.Now().Unix()
	_, err = s.Store.UpdateAccount(ctx, &store.UpdateAccount{
		ID:          account.ID,
		LastLoginAt: &now,
		LastLoginIP: func() *string { s := "127.0.0.1"; return &s }(),
	})
	if err != nil {
		fmt.Printf("warning: failed to update login info: %v\n", err)
	}

	// 4. 生成Token
	accessToken, err := auth.GenerateAccessToken(account.ID, account.OpenID, 0, s.Secret)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to generate token")
	}

	refreshToken, err := auth.GenerateRefreshToken()
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to generate refresh token")
	}

	// 5. 返回响应
	return &dnfv1.LoginResponse{
		Error:      ErrCodeSuccess,
		AuthKey:    accessToken,
		AccountKey: refreshToken,
		Encrypt:    true,
		ServerTime: uint64(now),
		LocalTime:  time.Now().Format("2006-01-02 15:04:05"),
		Authority:  uint32(account.Authority),
		Key:        "session_key",
		WorldId:    1,
		Channels: []*dnfv1.ChannelInfo{
			{World: 1, Channel: 1, Ip: "127.0.0.1", Port: 9001, Priority: 1},
			{World: 1, Channel: 2, Ip: "127.0.0.1", Port: 9002, Priority: 2},
		},
		Seeds: []int32{12345, 67890, 11111, 22222, 33333, 44444, 55555, 66666},
	}, nil
}

// GetCharacterList 获取角色列表
func (s *APIV1Service) GetCharacterList(ctx context.Context, req *dnfv1.CharacterListRequest) (*dnfv1.CharacterListResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roles, err := s.Store.ListRolesByAccount(ctx, claims.UserID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to list roles: %v", err)
	}

	var characters []*dnfv1.CharacterInfo
	for _, role := range roles {
		characters = append(characters, &dnfv1.CharacterInfo{
			Uid:           int64(role.ID),
			RoleId:        int32(role.RoleID),
			Name:          role.Name,
			Job:           role.Job,
			Level:         role.Level,
			Exp:           role.Exp,
			Fatigue:       int32(role.Fatigue),
			LastLoginTime: role.UpdatedAt,
		})
	}

	return &dnfv1.CharacterListResponse{
		Error:      ErrCodeSuccess,
		Characters: characters,
	}, nil
}

// CreateCharacter 创建角色
func (s *APIV1Service) CreateCharacter(ctx context.Context, req *dnfv1.CreateCharacterRequest) (*dnfv1.CreateCharacterResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// 1. 验证角色名
	if len(req.Name) < 2 || len(req.Name) > 12 {
		return &dnfv1.CreateCharacterResponse{Error: ErrCodeInvalidRoleName}, nil
	}

	// 2. 检查角色名是否已存在
	role, err := s.Store.GetRoleByName(ctx, req.Name)
	if err == nil {
		return &dnfv1.CreateCharacterResponse{Error: ErrCodeRoleNameExists}, nil
	} else if err != store.ErrNotFound {
		return nil, status.Errorf(codes.Internal, "failed to check role name: %v", err)
	}

	// 3. 检查角色数量限制
	roleCount, err := s.Store.CountRolesByAccount(ctx, claims.UserID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to count roles: %v", err)
	}
	if roleCount >= 4 {
		return &dnfv1.CreateCharacterResponse{Error: ErrCodeRoleLimitReached}, nil
	}

	// 4. 检查槽位
	existingRoles, err := s.Store.ListRolesByAccount(ctx, claims.UserID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to list roles: %v", err)
	}
	for _, r := range existingRoles {
		if r.RoleID == req.Slot {
			return &dnfv1.CreateCharacterResponse{Error: ErrCodeInvalidParam}, nil
		}
	}

	// 5. 创建角色
	role, err = s.createRoleWithInit(ctx, claims.UserID, req.Slot, req.Name, req.Job)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to create role: %v", err)
	}

	return &dnfv1.CreateCharacterResponse{
		Error:  ErrCodeSuccess,
		Uid:    int64(role.ID),
		RoleId: int32(role.RoleID),
		Name:   role.Name,
		Job:    role.Job,
		Level:  role.Level,
	}, nil
}

func (s *APIV1Service) createRoleWithInit(ctx context.Context, accountID uint64, slot int32, name string, job int32) (*store.Role, error) {
	// 创建角色
	role, err := s.Store.CreateRole(ctx, &store.Role{
		AccountID:  accountID,
		RoleID:     slot,
		Name:       name,
		Job:        job,
		Level:      1,
		Exp:        0,
		Fatigue:    100,
		MaxFatigue: 100,
		MapID:      1,
		X:          0,
		Y:          0,
		Channel:    1,
	})
	if err != nil {
		return nil, fmt.Errorf("create role failed: %w", err)
	}

	// 注意：这里应该创建 role_attributes 和 role_currency
	// 但实际需要根据 driver 接口是否支持

	return role, nil
}

// SelectCharacter 选择角色
func (s *APIV1Service) SelectCharacter(ctx context.Context, req *dnfv1.SelectCharacterRequest) (*dnfv1.SelectCharacterResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := uint64(req.Uid)
	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.SelectCharacterResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	if role.AccountID != claims.UserID {
		return nil, status.Errorf(codes.PermissionDenied, "role does not belong to user")
	}

	gameToken, err := auth.GenerateAccessToken(role.ID, fmt.Sprintf("%d", role.ID), uint64(role.Job), s.Secret)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to generate game token")
	}

	return &dnfv1.SelectCharacterResponse{
		Error:      ErrCodeSuccess,
		ServerIp:   "127.0.0.1",
		ServerPort: 9001,
		AuthToken:  gameToken,
	}, nil
}

// GetRoleInfo 获取角色信息
func (s *APIV1Service) GetRoleInfo(ctx context.Context, req *dnfv1.GetRoleInfoRequest) (*dnfv1.GetRoleInfoResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := uint64(req.Uid)
	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.GetRoleInfoResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	if role.AccountID != claims.UserID {
		return nil, status.Errorf(codes.PermissionDenied, "role does not belong to user")
	}

	attrs, _ := s.Store.GetRoleAttributes(ctx, role.ID)

	return &dnfv1.GetRoleInfoResponse{
		Error: 0,
		BaseInfo: &dnfv1.RoleBaseInfo{
			Uid:   int64(role.ID),
			Name:  role.Name,
			Job:   role.Job,
			Level: role.Level,
			Exp:   role.Exp,
		},
		BattleInfo: &dnfv1.RoleBattleInfo{
			Hp:    attrs.HP,
			MaxHp: attrs.MaxHP,
			Mp:    attrs.MP,
			MaxMp: attrs.MaxMP,
		},
		Position: &dnfv1.RolePosition{
			MapId: role.MapID,
			X:     float32(role.X),
			Y:     float32(role.Y),
		},
	}, nil
}

// ==================== Phase 1.2: 角色属性系统 ====================

// ==================== Phase 1.2: 角色属性系统（简化版） ====================

// UpdateAttributes 更新角色属性（属性点分配）
func (s *APIV1Service) UpdateAttributes(ctx context.Context, req *dnfv1.UpdateAttributesRequest) (*dnfv1.UpdateAttributesResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.RoleID
	if roleID == 0 {
		return nil, status.Errorf(codes.InvalidArgument, "role not selected")
	}

	attrs, err := s.Store.GetRoleAttributes(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get role attributes: %v", err)
	}

	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	totalPoints := req.Str + req.Dex + req.Vit + req.Spr
	if totalPoints <= 0 {
		return &dnfv1.UpdateAttributesResponse{
			Error: ErrCodeSuccess,
			BattleInfo: &dnfv1.RoleBattleInfo{
				Hp:        attrs.HP,
				MaxHp:     attrs.MaxHP,
				Mp:        attrs.MP,
				MaxMp:     attrs.MaxMP,
				Str:       attrs.Strength,
				Dex:       attrs.Intelligence,
				Vit:       attrs.Vitality,
				Spr:       attrs.Spirit,
				Atk:       attrs.PhysicalAttack,
				Def:       attrs.PhysicalDefense,
				MagicAtk:  attrs.MagicAttack,
				MagicDef:  attrs.MagicDefense,
				MoveSpeed: attrs.MoveSpeed,
				AtkSpeed:  attrs.AttackSpeed,
				CastSpeed: attrs.CastSpeed,
			},
		}, nil
	}

	availablePoints := int32(5 + role.Level*1)
	currentUsed := attrs.Strength + attrs.Intelligence + attrs.Vitality + attrs.Spirit - 50
	availablePoints += currentUsed

	if totalPoints > availablePoints {
		return nil, status.Errorf(codes.InvalidArgument, "not enough attribute points: have %d, need %d", availablePoints, totalPoints)
	}

	newStrength := attrs.Strength + req.Str
	newIntelligence := attrs.Intelligence + req.Dex
	newVitality := attrs.Vitality + req.Vit
	newSpirit := attrs.Spirit + req.Spr

	maxHP := int32(500 + newVitality*50 + newStrength*10)
	maxMP := int32(200 + newSpirit*30 + newIntelligence*20)
	physicalAttack := int32(100 + newStrength*5 + newVitality*2)
	physicalDefense := int32(50 + newVitality*3 + newStrength*1)
	magicAttack := int32(100 + newIntelligence*5 + newSpirit*2)
	magicDefense := int32(50 + newSpirit*3 + newIntelligence*1)

	err = s.Store.UpdateRoleAttributes(ctx, &store.UpdateRoleAttributes{
		RoleID:          roleID,
		Strength:        &newStrength,
		Intelligence:    &newIntelligence,
		Vitality:        &newVitality,
		Spirit:          &newSpirit,
		MaxHP:           &maxHP,
		MaxMP:           &maxMP,
		PhysicalAttack:  &physicalAttack,
		PhysicalDefense: &physicalDefense,
		MagicAttack:     &magicAttack,
		MagicDefense:    &magicDefense,
	})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update attributes: %v", err)
	}

	return &dnfv1.UpdateAttributesResponse{
		Error: ErrCodeSuccess,
		BattleInfo: &dnfv1.RoleBattleInfo{
			Hp:        maxHP,
			MaxHp:     maxHP,
			Mp:        maxMP,
			MaxMp:     maxMP,
			Str:       newStrength,
			Dex:       newIntelligence,
			Vit:       newVitality,
			Spr:       newSpirit,
			Atk:       physicalAttack,
			Def:       physicalDefense,
			MagicAtk:  magicAttack,
			MagicDef:  magicDefense,
			MoveSpeed: attrs.MoveSpeed,
			AtkSpeed:  attrs.AttackSpeed,
			CastSpeed: attrs.CastSpeed,
		},
	}, nil
}

// LearnSkill 学习技能
func (s *APIV1Service) LearnSkill(ctx context.Context, req *dnfv1.LearnSkillRequest) (*dnfv1.LearnSkillResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.RoleID
	if roleID == 0 {
		return nil, status.Errorf(codes.InvalidArgument, "role not selected")
	}

	skillID := req.SkillId
	skill, err := s.Store.GetSkill(ctx, &store.FindSkill{SkillID: &skillID})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get skill: %v", err)
	}

	role, err := s.Store.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	if role.Job != skill.JobRequired && skill.JobRequired != 0 {
		return nil, status.Errorf(codes.InvalidArgument, "skill job requirement not met")
	}

	if role.Level < skill.LevelRequired {
		return nil, status.Errorf(codes.InvalidArgument, "level requirement not met: need %d, have %d", skill.LevelRequired, role.Level)
	}

	roleSkill, err := s.Store.GetRoleSkill(ctx, &store.FindRoleSkill{
		RoleID:  &roleID,
		SkillID: &skillID,
	})
	if err == nil && roleSkill.IsLearned {
		return nil, status.Errorf(codes.AlreadyExists, "skill already learned")
	}

	if err != nil {
		roleSkill = nil
	}

	var learnedSkill *store.RoleSkill
	if roleSkill == nil {
		roleSkill = &store.RoleSkill{
			RoleID:    roleID,
			SkillID:   skillID,
			Level:     1,
			IsLearned: true,
		}
		learnedSkill, err = s.Store.CreateRoleSkill(ctx, &store.CreateRoleSkill{
			RoleID:    roleID,
			SkillID:   skillID,
			Level:     1,
			IsLearned: true,
		})
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to create role skill: %v", err)
		}
	} else {
		level := int32(1)
		err = s.Store.UpdateRoleSkill(ctx, &store.UpdateRoleSkill{
			ID:        roleSkill.ID,
			Level:     &level,
			IsLearned: func() *bool { b := true; return &b }(),
		})
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to update role skill: %v", err)
		}
		roleSkill.Level = 1
		roleSkill.IsLearned = true
		learnedSkill = roleSkill
	}

	return &dnfv1.LearnSkillResponse{
		Error: ErrCodeSuccess,
		Skill: &dnfv1.SkillInfo{
			SkillId:  learnedSkill.SkillID,
			Level:    learnedSkill.Level,
			MaxLevel: skill.MaxLevel,
			SpCost:   skill.SP,
			Cooldown: 0,
		},
	}, nil
}

// UpgradeSkill 升级技能 - 简化版
func (s *APIV1Service) UpgradeSkill(ctx context.Context, req *dnfv1.UpgradeSkillRequest) (*dnfv1.UpgradeSkillResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// TODO: 实现技能升级逻辑
	// 1. 获取当前技能等级
	// 2. 检查最大等级限制
	// 3. 检查升级材料
	// 4. 提升技能等级

	return &dnfv1.UpgradeSkillResponse{
		Error: 0,
		Skill: &dnfv1.SkillInfo{
			SkillId: req.SkillId,
			Level:   2,
		},
	}, nil
}

// RecoverFatigue 恢复疲劳值
func (s *APIV1Service) RecoverFatigue(ctx context.Context, req *dnfv1.RecoverFatigueRequest) (*dnfv1.RecoverFatigueResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取角色
	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.RecoverFatigueResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	// 如果疲劳值已满，直接返回
	if role.Fatigue >= role.MaxFatigue {
		return &dnfv1.RecoverFatigueResponse{
			Error:          0,
			CurrentFatigue: role.Fatigue,
			MaxFatigue:     role.MaxFatigue,
		}, nil
	}

	// 计算恢复量
	recoverAmount := req.RecoverAmount
	if recoverAmount <= 0 {
		recoverAmount = 10 // 默认恢复10点
	}

	// 计算新的疲劳值（不超过最大值）
	newFatigue := role.Fatigue + recoverAmount
	if newFatigue > role.MaxFatigue {
		newFatigue = role.MaxFatigue
	}

	// 更新角色疲劳值
	update := &store.UpdateRole{
		ID:      roleID,
		Fatigue: &newFatigue,
	}

	_, err = s.Store.UpdateRole(ctx, update)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update fatigue: %v", err)
	}

	return &dnfv1.RecoverFatigueResponse{
		Error:          0,
		CurrentFatigue: newFatigue,
		MaxFatigue:     role.MaxFatigue,
	}, nil
}

// ==================== Phase 2: 背包系统 ====================

// GetBag 获取背包
func (s *APIV1Service) GetBag(ctx context.Context, req *dnfv1.GetBagRequest) (*dnfv1.GetBagResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 查询背包物品
	items, err := s.Store.ListBagItemsByRole(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to list bag items: %v", err)
	}

	// 转换为proto格式
	var bagItems []*dnfv1.BagItem
	for _, item := range items {
		bagItems = append(bagItems, &dnfv1.BagItem{
			Guid:   item.ID,
			ItemId: uint32(item.ItemID),
			Count:  item.Count,
			Slot:   item.GridIndex,
		})
	}

	return &dnfv1.GetBagResponse{
		Error: ErrCodeSuccess,
		Bag: &dnfv1.BagInfo{
			Items: bagItems,
		},
	}, nil
}

// MoveItem 移动物品
func (s *APIV1Service) MoveItem(ctx context.Context, req *dnfv1.MoveItemRequest) (*dnfv1.MoveItemResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID
	itemID := uint64(req.Guid)

	// 获取物品
	item, err := s.Store.GetBagItem(ctx, &store.FindBagItem{
		FindBase: store.FindBase{ID: &itemID},
		RoleID:   &roleID,
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.MoveItemResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get item: %v", err)
	}

	// 检查目标格子是否已有物品
	targetItem, err := s.Store.GetBagItem(ctx, &store.FindBagItem{
		RoleID:    &roleID,
		GridIndex: &req.ToSlot,
	})

	if err == nil && targetItem != nil {
		// 目标格子有物品，如果是相同物品则合并
		if targetItem.ItemID == item.ItemID {
			// 合并物品
			newCount := targetItem.Count + item.Count
			err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
				ID:    targetItem.ID,
				Count: &newCount,
			})
			if err != nil {
				return nil, status.Errorf(codes.Internal, "failed to merge items: %v", err)
			}
			// 删除源物品
			err = s.Store.DeleteBagItem(ctx, &store.DeleteBagItem{ID: item.ID})
			if err != nil {
				return nil, status.Errorf(codes.Internal, "failed to delete source item: %v", err)
			}
		} else {
			// 交换两个物品的位置
			sourceSlot := item.GridIndex
			targetSlot := targetItem.GridIndex

			err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
				ID:        item.ID,
				GridIndex: &targetSlot,
			})
			if err != nil {
				return nil, status.Errorf(codes.Internal, "failed to update source item: %v", err)
			}

			err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
				ID:        targetItem.ID,
				GridIndex: &sourceSlot,
			})
			if err != nil {
				return nil, status.Errorf(codes.Internal, "failed to update target item: %v", err)
			}
		}
	} else {
		// 目标格子为空，直接移动
		newSlot := req.ToSlot
		err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
			ID:        item.ID,
			GridIndex: &newSlot,
		})
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to move item: %v", err)
		}
	}

	return &dnfv1.MoveItemResponse{Error: ErrCodeSuccess}, nil
}

// UseItem 使用物品
func (s *APIV1Service) UseItem(ctx context.Context, req *dnfv1.UseItemRequest) (*dnfv1.UseItemResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID
	itemID := uint64(req.Guid)

	// 获取物品
	item, err := s.Store.GetBagItem(ctx, &store.FindBagItem{
		FindBase: store.FindBase{ID: &itemID},
		RoleID:   &roleID,
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.UseItemResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get item: %v", err)
	}

	// 检查数量
	if item.Count < req.Count {
		return &dnfv1.UseItemResponse{Error: ErrCodeInvalidParam}, nil
	}

	itemType := item.ItemID / 100
	switch itemType {
	case 0:
		attrs, err := s.Store.GetRoleAttributes(ctx, roleID)
		if err == nil {
			maxHP := attrs.MaxHP
			hp := attrs.HP + item.ItemID*10
			if hp > maxHP {
				hp = maxHP
			}
			s.Store.UpdateRoleAttributes(ctx, &store.UpdateRoleAttributes{
				RoleID: roleID,
				HP:     &hp,
			})
		}
	case 1:
		attrs, err := s.Store.GetRoleAttributes(ctx, roleID)
		if err == nil {
			maxMP := attrs.MaxMP
			mp := attrs.MP + item.ItemID*10
			if mp > maxMP {
				mp = maxMP
			}
			s.Store.UpdateRoleAttributes(ctx, &store.UpdateRoleAttributes{
				RoleID: roleID,
				MP:     &mp,
			})
		}
	case 2:
		role, err := s.Store.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}})
		if err == nil {
			fatigue := role.Fatigue + int32(item.ItemID%50)
			if fatigue > role.MaxFatigue {
				fatigue = role.MaxFatigue
			}
			s.Store.UpdateRole(ctx, &store.UpdateRole{
				ID:      role.ID,
				Fatigue: &fatigue,
			})
		}
	}

	newCount := item.Count - req.Count
	if newCount <= 0 {
		// 删除物品
		err = s.Store.DeleteBagItem(ctx, &store.DeleteBagItem{ID: item.ID})
	} else {
		// 更新数量
		err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
			ID:    item.ID,
			Count: &newCount,
		})
	}
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update item: %v", err)
	}

	// 返回更新后的物品列表
	items, _ := s.Store.ListBagItemsByRole(ctx, roleID)
	var updatedItems []*dnfv1.BagItem
	for _, it := range items {
		updatedItems = append(updatedItems, &dnfv1.BagItem{
			Guid:   it.ID,
			ItemId: uint32(it.ItemID),
			Count:  it.Count,
			Slot:   it.GridIndex,
		})
	}

	return &dnfv1.UseItemResponse{
		Error:        ErrCodeSuccess,
		UpdatedItems: updatedItems,
	}, nil
}

// SellItem 出售物品
func (s *APIV1Service) SellItem(ctx context.Context, req *dnfv1.SellItemRequest) (*dnfv1.SellItemResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID
	itemID := uint64(req.Guid)

	// 获取物品
	item, err := s.Store.GetBagItem(ctx, &store.FindBagItem{
		FindBase: store.FindBase{ID: &itemID},
		RoleID:   &roleID,
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.SellItemResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get item: %v", err)
	}

	// 检查数量
	if item.Count < req.Count {
		return &dnfv1.SellItemResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 计算出售价格（简化：根据物品ID计算基础价格）
	basePrice := int64(item.ItemID * 10) // 简化价格公式
	totalPrice := basePrice * int64(req.Count)

	// 删除/减少物品
	newCount := item.Count - req.Count
	if newCount <= 0 {
		err = s.Store.DeleteBagItem(ctx, &store.DeleteBagItem{ID: item.ID})
	} else {
		err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
			ID:    item.ID,
			Count: &newCount,
		})
	}
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update item: %v", err)
	}

	// TODO: 增加金币到角色货币
	// 这里应该调用 UpdateRoleCurrency 增加金币

	return &dnfv1.SellItemResponse{
		Error:        ErrCodeSuccess,
		GoldReceived: int32(totalPrice),
	}, nil
}

// EquipItem 装备物品
func (s *APIV1Service) EquipItem(ctx context.Context, req *dnfv1.EquipItemRequest) (*dnfv1.EquipItemResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID
	itemID := uint64(req.Guid)

	// 获取物品
	item, err := s.Store.GetBagItem(ctx, &store.FindBagItem{
		FindBase: store.FindBase{ID: &itemID},
		RoleID:   &roleID,
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.EquipItemResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get item: %v", err)
	}

	// 检查是否已经是装备状态
	if item.IsEquiped {
		// 卸下装备
		isEquipped := false
		err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
			ID:        item.ID,
			IsEquiped: &isEquipped,
		})
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to unequip item: %v", err)
		}
	} else {
		role, err := s.Store.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}})
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
		}

		equipType := item.ItemID % 10
		minLevel := item.ItemID / 1000

		if role.Level < minLevel {
			return nil, status.Errorf(codes.InvalidArgument, "level requirement not met: need %d, have %d", minLevel, role.Level)
		}

		items, err := s.Store.ListBagItemsByRole(ctx, roleID)
		if err == nil {
			for _, it := range items {
				if it.IsEquiped && it.ID != item.ID && it.ItemID%10 == equipType {
					oldEquipped := false
					s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
						ID:        it.ID,
						IsEquiped: &oldEquipped,
					})
					break
				}
			}
		}

		isEquipped := true
		err = s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{
			ID:        item.ID,
			IsEquiped: &isEquipped,
		})
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to equip item: %v", err)
		}
	}

	return &dnfv1.EquipItemResponse{
		Error:        ErrCodeSuccess,
		UpdatedItems: nil,
	}, nil
}

// ==================== Phase 3: 副本系统 ====================

// EnterDungeon 进入副本
func (s *APIV1Service) EnterDungeon(ctx context.Context, req *dnfv1.EnterDungeonRequest) (*dnfv1.EnterDungeonResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取角色信息
	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.EnterDungeonResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	// 检查疲劳值
	if role.Fatigue <= 0 {
		return &dnfv1.EnterDungeonResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 扣除疲劳值（进入副本消耗10点）
	fatigueCost := int32(10)
	newFatigue := role.Fatigue - fatigueCost
	if newFatigue < 0 {
		newFatigue = 0
	}

	update := &store.UpdateRole{
		ID:      roleID,
		Fatigue: &newFatigue,
	}
	_, err = s.Store.UpdateRole(ctx, update)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update fatigue: %v", err)
	}

	// 生成副本实例ID（简化：使用当前时间戳）
	instanceID := int32(time.Now().Unix())

	// TODO: 根据dungeon_id加载副本配置
	// TODO: 创建副本实例
	// TODO: 生成怪物

	// 模拟副本怪物
	monsters := []*dnfv1.DungeonMonster{
		{
			MonsterId: 1001,
			Level:     role.Level,
			X:         float32(100),
			Y:         float32(100),
			IsBoss:    false,
		},
		{
			MonsterId: 1002,
			Level:     role.Level + 1,
			X:         float32(200),
			Y:         float32(200),
			IsBoss:    false,
		},
	}

	return &dnfv1.EnterDungeonResponse{
		Error:      ErrCodeSuccess,
		InstanceId: instanceID,
		MapId:      req.DungeonId,
		StartPosition: &dnfv1.RolePosition{
			MapId: req.DungeonId,
			X:     0,
			Y:     0,
		},
		Monsters: monsters,
	}, nil
}

// ExitDungeon 退出副本
func (s *APIV1Service) ExitDungeon(ctx context.Context, req *dnfv1.ExitDungeonRequest) (*dnfv1.ExitDungeonResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取角色信息
	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.ExitDungeonResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	// 计算奖励（简化版）
	var expGain int64 = 0
	var goldGain int32 = 0
	var items []*dnfv1.ItemReward

	if req.IsClear {
		// 通关奖励
		expGain = int64(role.Level) * 100
		goldGain = role.Level * 50

		// 随机掉落物品
		items = []*dnfv1.ItemReward{
			{
				ItemId: 1001,
				Count:  1,
			},
			{
				ItemId: 2001,
				Count:  5,
			},
		}

		// 增加经验值
		newExp := role.Exp + expGain
		expUpdate := &store.UpdateRole{
			ID:  roleID,
			Exp: &newExp,
		}
		_, err = s.Store.UpdateRole(ctx, expUpdate)
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to update exp: %v", err)
		}

		// TODO: 增加金币
		// TODO: 添加掉落物品到背包
	}

	return &dnfv1.ExitDungeonResponse{
		Error: ErrCodeSuccess,
		Result: &dnfv1.DungeonResult{
			IsClear:  req.IsClear,
			ExpGain:  expGain,
			GoldGain: goldGain,
			Items:    items,
		},
	}, nil
}

// Revive 复活
func (s *APIV1Service) Revive(ctx context.Context, req *dnfv1.ReviveRequest) (*dnfv1.ReviveResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取角色属性
	attrs, err := s.Store.GetRoleAttributes(ctx, roleID)
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.ReviveResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role attributes: %v", err)
	}

	// 根据复活类型处理
	switch req.ReviveType {
	case 1: // 免费复活
		// 恢复30% HP/MP
		hp := attrs.MaxHP * 30 / 100
		mp := attrs.MaxMP * 30 / 100
		return &dnfv1.ReviveResponse{
			Error: ErrCodeSuccess,
			Position: &dnfv1.RolePosition{
				MapId: 1,
				X:     0,
				Y:     0,
			},
			Hp: hp,
			Mp: mp,
		}, nil

	case 2: // 金币复活
		// TODO: 扣除金币
		hp := attrs.MaxHP * 50 / 100
		mp := attrs.MaxMP * 50 / 100
		return &dnfv1.ReviveResponse{
			Error: ErrCodeSuccess,
			Position: &dnfv1.RolePosition{
				MapId: 1,
				X:     0,
				Y:     0,
			},
			Hp: hp,
			Mp: mp,
		}, nil

	case 3: // 道具复活
		// TODO: 扣除复活道具
		return &dnfv1.ReviveResponse{
			Error: ErrCodeSuccess,
			Position: &dnfv1.RolePosition{
				MapId: 1,
				X:     0,
				Y:     0,
			},
			Hp: attrs.MaxHP,
			Mp: attrs.MaxMP,
		}, nil

	default:
		return &dnfv1.ReviveResponse{Error: ErrCodeInvalidParam}, nil
	}
}

// ChangeRoom 切换房间
func (s *APIV1Service) ChangeRoom(ctx context.Context, req *dnfv1.ChangeRoomRequest) (*dnfv1.ChangeRoomResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// TODO: 验证副本实例
	// TODO: 检查房间是否可进入
	// TODO: 更新副本状态

	// 模拟新房间的怪物
	monsters := []*dnfv1.DungeonMonster{
		{
			MonsterId: 1003,
			Level:     5,
			X:         float32(150),
			Y:         float32(150),
			IsBoss:    false,
		},
	}

	return &dnfv1.ChangeRoomResponse{
		Error:     ErrCodeSuccess,
		NewRoomId: req.RoomId,
		Position: &dnfv1.RolePosition{
			MapId: 1,
			X:     0,
			Y:     0,
		},
		Monsters: monsters,
	}, nil
}

// ==================== Phase 4: 聊天与社交系统 ====================

// SendChat 发送聊天消息
func (s *APIV1Service) SendChat(ctx context.Context, req *dnfv1.SendChatRequest) (*dnfv1.SendChatResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取角色信息（发送者名称）
	_, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	// 验证消息内容
	if len(req.Content) == 0 || len(req.Content) > 200 {
		return &dnfv1.SendChatResponse{Error: ErrCodeInvalidParam}, nil
	}

	content := req.Content

	sensitiveWords := []string{"admin", "gm", "作弊", "外挂"}
	for _, word := range sensitiveWords {
		content = strings.Replace(content, word, "***", -1)
	}

	return &dnfv1.SendChatResponse{
		Error: ErrCodeSuccess,
	}, nil
}

// GetChatHistory 获取聊天历史
func (s *APIV1Service) GetChatHistory(ctx context.Context, req *dnfv1.GetChatHistoryRequest) (*dnfv1.GetChatHistoryResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// TODO: 从数据库查询聊天记录
	// 简化：返回空列表

	return &dnfv1.GetChatHistoryResponse{
		Error:    ErrCodeSuccess,
		Messages: []*dnfv1.ChatMessage{},
		HasMore:  false,
	}, nil
}

// GetFriendList 获取好友列表
func (s *APIV1Service) GetFriendList(ctx context.Context, req *dnfv1.GetFriendListRequest) (*dnfv1.GetFriendListResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 查询好友列表
	friends, err := s.Store.ListFriends(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to list friends: %v", err)
	}

	// 转换为proto格式
	var friendInfos []*dnfv1.FriendInfo
	for _, friend := range friends {
		// 获取好友角色信息
		friendRoleID := uint64(friend.FriendID)
		friendRole, err := s.Store.GetRole(ctx, &store.FindRole{
			FindBase: store.FindBase{ID: &friendRoleID},
		})
		if err != nil {
			continue // 跳过找不到的好友
		}

		friendInfos = append(friendInfos, &dnfv1.FriendInfo{
			Uid:      int64(friend.FriendID),
			Name:     friendRole.Name,
			Level:    friendRole.Level,
			Job:      friendRole.Job,
			Online:   true, // TODO: 检查在线状态
			Intimacy: int32(friend.Intimacy),
		})
	}

	return &dnfv1.GetFriendListResponse{
		Error:   ErrCodeSuccess,
		Friends: friendInfos,
	}, nil
}

// AddFriend 添加好友
func (s *APIV1Service) AddFriend(ctx context.Context, req *dnfv1.AddFriendRequest) (*dnfv1.AddFriendResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 根据名称查找目标角色
	targetRole, err := s.Store.GetRoleByName(ctx, req.TargetName)
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.AddFriendResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to find target: %v", err)
	}

	// 不能添加自己为好友
	if targetRole.ID == roleID {
		return &dnfv1.AddFriendResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 检查是否已经是好友
	_, err = s.Store.GetFriend(ctx, &store.FindFriend{
		RoleID:   &roleID,
		FriendID: &targetRole.ID,
	})
	if err == nil {
		// 已经是好友
		return &dnfv1.AddFriendResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 创建好友关系
	_, err = s.Store.CreateFriend(ctx, &store.Friend{
		RoleID:     roleID,
		FriendID:   targetRole.ID,
		FriendName: targetRole.Name,
		Intimacy:   0,
	})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to create friend: %v", err)
	}

	// TODO: 发送好友申请通知（双向好友）
	// 简化：直接添加为好友（双向）
	_, _ = s.Store.CreateFriend(ctx, &store.Friend{
		RoleID:     targetRole.ID,
		FriendID:   roleID,
		FriendName: "", // 需要查询当前角色名
		Intimacy:   0,
	})

	return &dnfv1.AddFriendResponse{
		Error: ErrCodeSuccess,
	}, nil
}

// ReplyFriendRequest 回复好友申请
func (s *APIV1Service) ReplyFriendRequest(ctx context.Context, req *dnfv1.ReplyFriendRequestMessage) (*emptypb.Empty, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	requesterUID := uint64(req.RequesterUid)

	// 查找申请人角色
	requesterRole, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &requesterUID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return nil, status.Errorf(codes.NotFound, "requester not found")
		}
		return nil, status.Errorf(codes.Internal, "failed to find requester: %v", err)
	}

	if req.Accept {
		// 接受好友申请 - 创建双向好友关系
		currentRoleID := claims.UserID

		currentRole, _ := s.Store.GetRole(ctx, &store.FindRole{
			FindBase: store.FindBase{ID: &currentRoleID},
		})

		_, err = s.Store.CreateFriend(ctx, &store.Friend{
			RoleID:     currentRoleID,
			FriendID:   requesterRole.ID,
			FriendName: requesterRole.Name,
			Intimacy:   0,
		})
		if err != nil {
			return nil, status.Errorf(codes.Internal, "failed to create friend: %v", err)
		}

		_, _ = s.Store.CreateFriend(ctx, &store.Friend{
			RoleID:     requesterRole.ID,
			FriendID:   currentRoleID,
			FriendName: currentRole.Name,
			Intimacy:   0,
		})
	}

	return &emptypb.Empty{}, nil
}

// RemoveFriend 删除好友
func (s *APIV1Service) RemoveFriend(ctx context.Context, req *dnfv1.RemoveFriendRequest) (*dnfv1.RemoveFriendResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID
	friendID := uint64(req.FriendUid)

	// 查找好友关系
	friend, err := s.Store.GetFriend(ctx, &store.FindFriend{
		RoleID:   &roleID,
		FriendID: &friendID,
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.RemoveFriendResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to find friend: %v", err)
	}

	// 删除好友关系
	err = s.Store.DeleteFriend(ctx, &store.DeleteFriend{ID: friend.ID})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to delete friend: %v", err)
	}

	// 删除反向好友关系
	reverseFriend, err := s.Store.GetFriend(ctx, &store.FindFriend{
		RoleID:   &friendID,
		FriendID: &roleID,
	})
	if err == nil {
		_ = s.Store.DeleteFriend(ctx, &store.DeleteFriend{ID: reverseFriend.ID})
	}

	return &dnfv1.RemoveFriendResponse{
		Error: ErrCodeSuccess,
	}, nil
}
func (s *APIV1Service) GetShopList(ctx context.Context, req *dnfv1.GetShopListRequest) (*dnfv1.GetShopListResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// 简化：根据商店类型返回固定商品
	items := []*dnfv1.ShopItem{
		{Slot: 1, ItemId: 1001, Price: 100, CurrencyType: 1, Stock: 999, Discount: 100},
		{Slot: 2, ItemId: 1002, Price: 200, CurrencyType: 1, Stock: 999, Discount: 100},
		{Slot: 3, ItemId: 1003, Price: 500, CurrencyType: 1, Stock: 100, Discount: 90},
	}

	return &dnfv1.GetShopListResponse{
		Error:       0,
		Items:       items,
		RefreshTime: 3600,
	}, nil
}

// BuyItem 购买商城物品
func (s *APIV1Service) BuyItem(ctx context.Context, req *dnfv1.BuyItemRequest) (*dnfv1.BuyItemResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 根据slot计算物品ID和价格
	itemID := int32(req.Slot*100 + 100)
	price := int64(itemID * 10)

	// 获取角色货币
	currency, err := s.Store.GetRoleCurrency(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get currency: %v", err)
	}

	totalPrice := price * int64(req.Count)

	// 检查金币是否足够
	if currency.Gold < totalPrice {
		return &dnfv1.BuyItemResponse{Error: 3}, nil
	}

	// 扣除金币
	currency.Gold -= totalPrice
	if err := s.Store.UpdateRoleCurrency(ctx, currency); err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update currency: %v", err)
	}

	// 创建物品
	var newItems []*dnfv1.BagItem
	for i := int32(0); i < req.Count; i++ {
		newItem := &store.BagItem{
			RoleID:       roleID,
			ItemID:       itemID,
			GridIndex:    int32(i),
			Count:        1,
			IsEquiped:    false,
			BindType:     1,
			Durability:   100,
			EnhanceLevel: 0,
			Attributes:   "",
		}
		bagItem, _ := s.Store.CreateBagItem(ctx, newItem)
		newItems = append(newItems, &dnfv1.BagItem{
			Guid:   bagItem.ID,
			ItemId: uint32(bagItem.ItemID),
			Count:  bagItem.Count,
			Slot:   bagItem.GridIndex,
		})
	}

	return &dnfv1.BuyItemResponse{
		Error:    0,
		NewItems: newItems,
		Currency: &dnfv1.Currency{Gold: int32(currency.Gold), Cera: int32(currency.Coin)},
	}, nil
}

// SellToShop 出售给商店
func (s *APIV1Service) SellToShop(ctx context.Context, req *dnfv1.SellToShopRequest) (*dnfv1.SellToShopResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取物品
	item, err := s.Store.GetBagItem(ctx, &store.FindBagItem{
		FindBase: store.FindBase{ID: func() *uint64 { v := uint64(req.Guid); return &v }()},
		RoleID:   &roleID,
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.SellToShopResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get item: %v", err)
	}

	// 检查数量
	sellCount := req.Count
	if item.Count < sellCount {
		return &dnfv1.SellToShopResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 计算出售价格
	sellPrice := int64(item.ItemID) * 10 * int64(sellCount)

	// 删除或减少物品
	newCount := item.Count - sellCount
	if newCount <= 0 {
		if err := s.Store.DeleteBagItem(ctx, &store.DeleteBagItem{ID: item.ID}); err != nil {
			return nil, status.Errorf(codes.Internal, "failed to delete item: %v", err)
		}
	} else {
		if err := s.Store.UpdateBagItem(ctx, &store.UpdateBagItem{ID: item.ID, Count: &newCount}); err != nil {
			return nil, status.Errorf(codes.Internal, "failed to update item: %v", err)
		}
	}

	// 增加金币
	currency, err := s.Store.GetRoleCurrency(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get currency: %v", err)
	}
	currency.Gold += sellPrice
	if err := s.Store.UpdateRoleCurrency(ctx, currency); err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update currency: %v", err)
	}

	return &dnfv1.SellToShopResponse{
		Error:        0,
		GoldReceived: int32(sellPrice),
	}, nil
}

// SearchAuction 搜索拍卖行
func (s *APIV1Service) SearchAuction(ctx context.Context, req *dnfv1.SearchAuctionRequest) (*dnfv1.SearchAuctionResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// 构建查询条件
	find := &store.FindAuctionItem{
		Status: func() *store.AuctionStatus { s := store.AuctionStatusSelling; return &s }(),
	}
	if req.ItemId > 0 {
		itemID := int32(req.ItemId)
		find.ItemID = &itemID
	}
	if req.MaxPrice > 0 {
		maxPrice := int64(req.MaxPrice)
		find.MaxPrice = &maxPrice
	}
	if req.PageSize > 0 {
		limit := int(req.PageSize)
		find.Limit = &limit
		offset := int(req.Page * req.PageSize)
		find.Offset = &offset
	}

	// 查询
	items, err := s.Store.ListAuctionItems(ctx, find)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to search auctions: %v", err)
	}

	// 转换为proto格式
	var auctionItems []*dnfv1.AuctionItem
	now := time.Now().Unix()
	for _, item := range items {
		timeLeft := int32(item.EndTime - now)
		if timeLeft < 0 {
			timeLeft = 0
		}
		auctionItems = append(auctionItems, &dnfv1.AuctionItem{
			AuctionId:  int64(item.ID),
			ItemId:     uint32(item.ItemID),
			SellerName: item.SellerName,
			Price:      int32(item.Price),
			BidPrice:   int32(item.BidPrice),
			TimeLeft:   timeLeft,
		})
	}

	return &dnfv1.SearchAuctionResponse{
		Error: 0,
		Items: auctionItems,
		Total: int32(len(auctionItems)),
	}, nil
}

// RegisterAuction 上架拍卖
func (s *APIV1Service) RegisterAuction(ctx context.Context, req *dnfv1.RegisterAuctionRequest) (*dnfv1.RegisterAuctionResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 检查上架数量限制
	count, err := s.Store.GetDriver().CountAuctionItemsBySeller(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to count auctions: %v", err)
	}
	if count >= 10 {
		return &dnfv1.RegisterAuctionResponse{Error: 10}, nil // 上架数量已达上限
	}

	// 获取物品
	item, err := s.Store.GetBagItem(ctx, &store.FindBagItem{
		FindBase: store.FindBase{ID: func() *uint64 { v := uint64(req.Guid); return &v }()},
		RoleID:   &roleID,
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.RegisterAuctionResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get item: %v", err)
	}

	// 获取角色名称
	role, err := s.Store.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	// 检查数量
	if item.Count < 1 {
		return &dnfv1.RegisterAuctionResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 创建拍卖
	auction := &store.AuctionItem{
		SellerID:   roleID,
		SellerName: role.Name,
		ItemID:     item.ItemID,
		Count:      1, // 简化：每次上架1个
		Price:      int64(req.StartPrice),
		TotalPrice: int64(req.StartPrice),
		Duration:   req.Duration,
		Status:     store.AuctionStatusSelling,
		BidPrice:   int64(req.StartPrice),
		BidCount:   0,
		Attributes: item.Attributes,
	}

	auctionItem, err := s.Store.CreateAuctionItem(ctx, auction)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to create auction: %v", err)
	}

	// 删除背包中的物品
	if err := s.Store.DeleteBagItem(ctx, &store.DeleteBagItem{ID: item.ID}); err != nil {
		return nil, status.Errorf(codes.Internal, "failed to delete item: %v", err)
	}

	return &dnfv1.RegisterAuctionResponse{
		Error:     0,
		AuctionId: int64(auctionItem.ID),
	}, nil
}

// BidAuction 竞拍
func (s *APIV1Service) BidAuction(ctx context.Context, req *dnfv1.BidAuctionRequest) (*dnfv1.BidAuctionResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取拍卖物品
	auction, err := s.Store.GetAuctionItem(ctx, &store.FindAuctionItem{
		FindBase: store.FindBase{ID: func() *uint64 { v := uint64(req.AuctionId); return &v }()},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.BidAuctionResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get auction: %v", err)
	}

	// 检查状态
	if auction.Status != store.AuctionStatusSelling {
		return &dnfv1.BidAuctionResponse{Error: 11}, nil // 拍卖已结束
	}

	// 检查是否自己的物品
	if auction.SellerID == roleID {
		return &dnfv1.BidAuctionResponse{Error: 12}, nil // 不能竞拍自己的物品
	}

	// 检查出价
	bidPrice := int64(req.BidPrice)
	if bidPrice <= auction.BidPrice {
		return &dnfv1.BidAuctionResponse{Error: 13}, nil // 出价必须高于当前价
	}

	// 获取角色货币
	currency, err := s.Store.GetRoleCurrency(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get currency: %v", err)
	}
	if currency.Gold < bidPrice {
		return &dnfv1.BidAuctionResponse{Error: 3}, nil // 金币不足
	}

	// 扣除金币
	currency.Gold -= bidPrice
	if err := s.Store.UpdateRoleCurrency(ctx, currency); err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update currency: %v", err)
	}

	// 退还之前的出价者
	if auction.BidderID > 0 {
		prevCurrency, _ := s.Store.GetRoleCurrency(ctx, auction.BidderID)
		prevCurrency.Gold += auction.BidPrice
		s.Store.UpdateRoleCurrency(ctx, prevCurrency)
	}

	// 更新拍卖
	if err := s.Store.UpdateAuctionItem(ctx, &store.UpdateAuctionItem{
		ID:       auction.ID,
		BidderID: &roleID,
		BidPrice: &bidPrice,
		BidCount: func() *int32 { v := auction.BidCount + 1; return &v }(),
	}); err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update auction: %v", err)
	}

	return &dnfv1.BidAuctionResponse{Error: 0}, nil
}

// BuyoutAuction 一口价购买
func (s *APIV1Service) BuyoutAuction(ctx context.Context, req *dnfv1.BuyoutAuctionRequest) (*dnfv1.BuyoutAuctionResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取拍卖物品
	auction, err := s.Store.GetAuctionItem(ctx, &store.FindAuctionItem{
		FindBase: store.FindBase{ID: func() *uint64 { v := uint64(req.AuctionId); return &v }()},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.BuyoutAuctionResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get auction: %v", err)
	}

	// 检查状态
	if auction.Status != store.AuctionStatusSelling {
		return &dnfv1.BuyoutAuctionResponse{Error: 11}, nil
	}

	// 检查是否自己的物品
	if auction.SellerID == roleID {
		return &dnfv1.BuyoutAuctionResponse{Error: 12}, nil
	}

	// 获取角色货币
	currency, err := s.Store.GetRoleCurrency(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get currency: %v", err)
	}

	buyPrice := auction.TotalPrice
	if currency.Gold < buyPrice {
		return &dnfv1.BuyoutAuctionResponse{Error: 3}, nil
	}

	// 扣除金币
	currency.Gold -= buyPrice
	if err := s.Store.UpdateRoleCurrency(ctx, currency); err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update currency: %v", err)
	}

	// 给卖家增加金币（扣除手续费5%）
	sellerIncome := buyPrice - buyPrice/20
	sellerCurrency, _ := s.Store.GetRoleCurrency(ctx, auction.SellerID)
	sellerCurrency.Gold += sellerIncome
	s.Store.UpdateRoleCurrency(ctx, sellerCurrency)

	// 更新拍卖状态
	auctionStatus := store.AuctionStatusSold
	if err := s.Store.UpdateAuctionItem(ctx, &store.UpdateAuctionItem{
		ID:       auction.ID,
		Status:   &auctionStatus,
		BidderID: &roleID,
	}); err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update auction: %v", err)
	}

	// 创建拍卖历史
	s.Store.CreateAuctionHistory(ctx, &store.CreateAuctionHistory{
		AuctionID:    auction.ID,
		SellerID:     auction.SellerID,
		BuyerID:      roleID,
		ItemID:       auction.ItemID,
		Count:        auction.Count,
		FinalPrice:   buyPrice,
		SellerIncome: sellerIncome,
	})

	// 创建物品到买家背包
	bagItem := &store.BagItem{
		RoleID:       roleID,
		ItemID:       auction.ItemID,
		GridIndex:    0,
		Count:        auction.Count,
		IsEquiped:    false,
		BindType:     2, // 拍卖获取绑定
		Durability:   100,
		EnhanceLevel: 0,
		Attributes:   auction.Attributes,
	}
	newItem, _ := s.Store.CreateBagItem(ctx, bagItem)

	return &dnfv1.BuyoutAuctionResponse{
		Error: 0,
		Item: &dnfv1.BagItem{
			Guid:   newItem.ID,
			ItemId: uint32(newItem.ItemID),
			Count:  newItem.Count,
			Slot:   newItem.GridIndex,
		},
	}, nil
}

func (s *APIV1Service) OpenPrivateStore(ctx context.Context, req *dnfv1.OpenPrivateStoreRequest) (*dnfv1.OpenPrivateStoreResponse, error) {
	return &dnfv1.OpenPrivateStoreResponse{Error: 0}, nil
}
func (s *APIV1Service) ViewPrivateStore(ctx context.Context, req *dnfv1.ViewPrivateStoreRequest) (*dnfv1.ViewPrivateStoreResponse, error) {
	return &dnfv1.ViewPrivateStoreResponse{Error: 0}, nil
}
func (s *APIV1Service) BuyFromPrivateStore(ctx context.Context, req *dnfv1.BuyFromPrivateStoreRequest) (*dnfv1.BuyFromPrivateStoreResponse, error) {
	return &dnfv1.BuyFromPrivateStoreResponse{Error: 0}, nil
}

// ==================== Phase 6: 任务系统 ====================

// GetQuestList 获取任务列表
func (s *APIV1Service) GetQuestList(ctx context.Context, req *dnfv1.GetQuestListRequest) (*dnfv1.GetQuestListResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	roleQuests, err := s.Store.ListRoleQuests(ctx, roleID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to list role quests: %v", err)
	}

	var quests []*dnfv1.QuestInfo
	for _, rq := range roleQuests {
		quest := &store.Quest{
			QuestID: rq.QuestID,
			Name:    "未知任务",
			Type:    0,
		}

		var state dnfv1.QuestState
		if rq.Status == 1 {
			state = dnfv1.QuestState_IN_PROGRESS
		} else if rq.Status == 2 {
			state = dnfv1.QuestState_COMPLETED
		} else if rq.Status == 3 {
			state = dnfv1.QuestState_REWARDED
		} else {
			state = dnfv1.QuestState_NOT_ACCEPTED
		}

		quests = append(quests, &dnfv1.QuestInfo{
			QuestId:       quest.QuestID,
			Name:          quest.Name,
			Description:   quest.Description,
			QuestType:     dnfv1.QuestType(quest.Type),
			State:         state,
			RequiredLevel: quest.LevelRequired,
			Objectives:    []*dnfv1.QuestObjective{},
			Rewards:       []*dnfv1.QuestReward{},
		})
	}

	return &dnfv1.GetQuestListResponse{
		Error:  ErrCodeSuccess,
		Quests: quests,
	}, nil
}

// AcceptQuest 接受任务
func (s *APIV1Service) AcceptQuest(ctx context.Context, req *dnfv1.AcceptQuestRequest) (*dnfv1.AcceptQuestResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	questID := req.QuestId

	existing, err := s.Store.GetRoleQuest(ctx, &store.FindRoleQuest{
		RoleID:  &roleID,
		QuestID: &questID,
	})
	if err == nil && existing != nil {
		return nil, status.Errorf(codes.AlreadyExists, "quest already accepted")
	}

	now := time.Now().Unix()
	_, err = s.Store.CreateRoleQuest(ctx, &store.RoleQuest{
		RoleID:     roleID,
		QuestID:    questID,
		Status:     1,
		Progress:   0,
		AcceptedAt: now,
	})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to create role quest: %v", err)
	}

	return &dnfv1.AcceptQuestResponse{
		Error: ErrCodeSuccess,
	}, nil
}

// CompleteQuest 完成任务
func (s *APIV1Service) CompleteQuest(ctx context.Context, req *dnfv1.CompleteQuestRequest) (*dnfv1.CompleteQuestResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID
	questID := req.QuestId

	roleQuest, err := s.Store.GetRoleQuest(ctx, &store.FindRoleQuest{
		RoleID:  &roleID,
		QuestID: &questID,
	})
	if err != nil {
		return nil, status.Errorf(codes.NotFound, "quest not found")
	}

	if roleQuest.Status != 1 {
		return nil, status.Errorf(codes.InvalidArgument, "quest not in progress")
	}

	quests, _ := s.Store.ListQuests(ctx, &store.FindQuest{QuestID: &questID})
	var quest *store.Quest
	if len(quests) > 0 {
		quest = quests[0]
	} else {
		quest = &store.Quest{RewardExp: 100, RewardGold: 50}
	}

	now := time.Now().Unix()
	err = s.Store.UpdateRoleQuest(ctx, &store.UpdateRoleQuest{
		ID:          roleQuest.ID,
		Status:      func() *int32 { s := int32(2); return &s }(),
		Progress:    &quest.TargetCount,
		CompletedAt: &now,
	})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update role quest: %v", err)
	}

	role, _ := s.Store.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}})
	if role != nil {
		newExp := role.Exp + quest.RewardExp
		newLevel := role.Level
		for newExp >= int64(newLevel*100) {
			newExp -= int64(newLevel * 100)
			newLevel++
		}
		s.Store.UpdateRole(ctx, &store.UpdateRole{
			ID:    role.ID,
			Level: &newLevel,
			Exp:   &newExp,
		})
	}

	return &dnfv1.CompleteQuestResponse{
		Error:    ErrCodeSuccess,
		ExpGain:  quest.RewardExp,
		GoldGain: int32(quest.RewardGold),
	}, nil
}

// GetQuestReward 领取任务奖励
func (s *APIV1Service) GetQuestReward(ctx context.Context, req *dnfv1.GetQuestRewardRequest) (*dnfv1.GetQuestRewardResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// TODO: 领取奖励逻辑
	return &dnfv1.GetQuestRewardResponse{
		Error: ErrCodeSuccess,
		Items: []*dnfv1.BagItem{},
	}, nil
}
func (s *APIV1Service) AbandonQuest(ctx context.Context, req *dnfv1.AbandonQuestRequest) (*dnfv1.AbandonQuestResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID
	questID := req.QuestId

	roleQuest, err := s.Store.GetRoleQuest(ctx, &store.FindRoleQuest{
		RoleID:  &roleID,
		QuestID: &questID,
	})
	if err != nil {
		return nil, status.Errorf(codes.NotFound, "quest not found")
	}

	err = s.Store.DeleteRoleQuest(ctx, &store.DeleteRoleQuest{ID: roleQuest.ID})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to abandon quest: %v", err)
	}

	return &dnfv1.AbandonQuestResponse{Error: ErrCodeSuccess}, nil
}

// GetGuildInfo 获取公会信息
func (s *APIV1Service) GetGuildInfo(ctx context.Context, req *dnfv1.GetGuildInfoRequest) (*dnfv1.GetGuildInfoResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.GetGuildInfoResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	// 1. 获取角色的公会成员信息
	member, err := s.Store.GetDriver().GetGuildMember(ctx, &store.FindGuildMember{RoleID: &roleID})
	if err != nil {
		return &dnfv1.GetGuildInfoResponse{Error: ErrCodeSuccess}, nil // 没有公会
	}

	// 3. 获取公会信息
	guild, err := s.Store.GetGuild(ctx, &store.FindGuild{FindBase: store.FindBase{ID: &member.GuildID}})
	if err != nil {
		return &dnfv1.GetGuildInfoResponse{Error: ErrCodeSystemError}, nil
	}

	// 4. 获取公会成员列表
	members, err := s.Store.GetDriver().ListGuildMembers(ctx, guild.ID)
	if err != nil {
		return &dnfv1.GetGuildInfoResponse{Error: ErrCodeSystemError}, nil
	}

	// 5. 获取会长信息
	leader, err := s.Store.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &guild.LeaderID}})
	leaderName := ""
	if err == nil && leader != nil {
		leaderName = leader.Name
	}

	// 6. 构建成员列表
	protoMembers := make([]*dnfv1.GuildMember, 0, len(members))
	for _, m := range members {
		memberRole, _ := s.Store.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &m.RoleID}})
		if memberRole == nil {
			continue
		}

		// 转换职位：store.Position (0-4) -> proto.GuildPosition (1-5)
		position := dnfv1.GuildPosition(m.Position + 1)

		protoMembers = append(protoMembers, &dnfv1.GuildMember{
			Uid:           int64(m.RoleID),
			Name:          memberRole.Name,
			Job:           memberRole.Job,
			Level:         memberRole.Level,
			Position:      position,
			Contribution:  int32(m.Contribution),
			LastLoginTime: 0,
			Online:        false,
		})
	}

	return &dnfv1.GetGuildInfoResponse{
		Error: ErrCodeSuccess,
		Guild: &dnfv1.GuildInfo{
			GuildId:        int64(guild.ID),
			Name:           guild.Name,
			Level:          guild.Level,
			Exp:            guild.Exp,
			Notice:         guild.Notice,
			MemberCount:    guild.MemberCount,
			MaxMemberCount: guild.MaxMembers,
			MasterName:     leaderName,
			CreateTime:     guild.CreatedAt,
		},
		Members: protoMembers,
	}, nil
}

// CreateGuild 创建公会
func (s *APIV1Service) CreateGuild(ctx context.Context, req *dnfv1.CreateGuildRequest) (*dnfv1.CreateGuildResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.CreateGuildResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	// 1. 验证公会名
	if req.Name == "" {
		return &dnfv1.CreateGuildResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 2. 检查角色是否已有公会
	existingMember, _ := s.Store.GetDriver().GetGuildMember(ctx, &store.FindGuildMember{RoleID: &roleID})
	if existingMember != nil {
		return &dnfv1.CreateGuildResponse{Error: 7}, nil // 已在公会中
	}

	// 3. 获取角色货币
	currency, err := s.Store.GetRoleCurrency(ctx, roleID)
	if err != nil {
		return &dnfv1.CreateGuildResponse{Error: ErrCodeSystemError}, nil
	}

	// 5. 检查创建费用 (100000金币)
	const createCost int64 = 100000
	if currency.Gold < createCost {
		return &dnfv1.CreateGuildResponse{Error: 3}, nil // 金币不足
	}

	// 6. 扣除金币
	currency.Gold -= createCost
	if err := s.Store.UpdateRoleCurrency(ctx, currency); err != nil {
		return &dnfv1.CreateGuildResponse{Error: ErrCodeSystemError}, nil
	}

	// 7. 创建公会
	guild, err := s.Store.CreateGuild(ctx, &store.Guild{
		Name:        req.Name,
		Level:       1,
		Exp:         0,
		Notice:      "",
		LeaderID:    roleID,
		MemberCount: 1,
		MaxMembers:  50,
		Fund:        createCost,
	})
	if err != nil {
		if err == store.ErrDuplicate {
			return &dnfv1.CreateGuildResponse{Error: ErrCodeRoleNameExists}, nil
		}
		return &dnfv1.CreateGuildResponse{Error: ErrCodeSystemError}, nil
	}

	// 8. 添加会长为成员
	_, err = s.Store.GetDriver().AddGuildMember(ctx, &store.GuildMember{
		GuildID:      guild.ID,
		RoleID:       roleID,
		Position:     3, // 会长
		Contribution: 0,
	})
	if err != nil {
		return &dnfv1.CreateGuildResponse{Error: ErrCodeSystemError}, nil
	}

	return &dnfv1.CreateGuildResponse{
		Error:   ErrCodeSuccess,
		GuildId: int64(guild.ID),
	}, nil
}

// JoinGuild 加入公会
func (s *APIV1Service) JoinGuild(ctx context.Context, req *dnfv1.JoinGuildRequest) (*dnfv1.JoinGuildResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.JoinGuildResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID
	guildID := uint64(req.GuildId)

	// 1. 检查角色是否已有公会
	existingMember, _ := s.Store.GetDriver().GetGuildMember(ctx, &store.FindGuildMember{RoleID: &roleID})
	if existingMember != nil {
		return &dnfv1.JoinGuildResponse{Error: 7}, nil // 已在公会中
	}

	// 2. 获取公会信息
	guild, err := s.Store.GetGuild(ctx, &store.FindGuild{FindBase: store.FindBase{ID: &guildID}})
	if err != nil {
		return &dnfv1.JoinGuildResponse{Error: ErrCodeNotFound}, nil
	}

	// 3. 检查公会是否已满
	if guild.MemberCount >= guild.MaxMembers {
		return &dnfv1.JoinGuildResponse{Error: 8}, nil // 公会已满
	}

	// 4. 添加成员
	_, err = s.Store.GetDriver().AddGuildMember(ctx, &store.GuildMember{
		GuildID:      guildID,
		RoleID:       roleID,
		Position:     0, // 普通成员
		Contribution: 0,
	})
	if err != nil {
		return &dnfv1.JoinGuildResponse{Error: ErrCodeSystemError}, nil
	}

	// 5. 更新公会人数
	newCount := guild.MemberCount + 1
	_, err = s.Store.UpdateGuild(ctx, &store.UpdateGuild{
		ID:          guildID,
		MemberCount: &newCount,
	})
	if err != nil {
		return &dnfv1.JoinGuildResponse{Error: ErrCodeSystemError}, nil
	}

	return &dnfv1.JoinGuildResponse{Error: ErrCodeSuccess}, nil
}

// LeaveGuild 离开公会
func (s *APIV1Service) LeaveGuild(ctx context.Context, req *dnfv1.LeaveGuildRequest) (*dnfv1.LeaveGuildResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.LeaveGuildResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	// 1. 获取角色的公会成员信息
	member, err := s.Store.GetDriver().GetGuildMember(ctx, &store.FindGuildMember{RoleID: &roleID})
	if err != nil {
		return &dnfv1.LeaveGuildResponse{Error: 9}, nil // 不在公会中
	}

	// 2. 检查是否是会长（会长不能离开，必须先转让）
	if member.Position == 3 {
		return &dnfv1.LeaveGuildResponse{Error: 10}, nil // 会长不能离开
	}

	// 3. 获取公会信息
	guild, err := s.Store.GetGuild(ctx, &store.FindGuild{FindBase: store.FindBase{ID: &member.GuildID}})
	if err != nil {
		return &dnfv1.LeaveGuildResponse{Error: ErrCodeSystemError}, nil
	}

	// 4. 删除成员
	err = s.Store.GetDriver().RemoveGuildMember(ctx, &store.DeleteGuildMember{ID: member.ID})
	if err != nil {
		return &dnfv1.LeaveGuildResponse{Error: ErrCodeSystemError}, nil
	}

	// 5. 更新公会人数
	newCount := guild.MemberCount - 1
	_, err = s.Store.UpdateGuild(ctx, &store.UpdateGuild{
		ID:          member.GuildID,
		MemberCount: &newCount,
	})
	if err != nil {
		return &dnfv1.LeaveGuildResponse{Error: ErrCodeSystemError}, nil
	}

	return &dnfv1.LeaveGuildResponse{Error: ErrCodeSuccess}, nil
}

// GuildDonate 公会捐赠
func (s *APIV1Service) GuildDonate(ctx context.Context, req *dnfv1.GuildDonateRequest) (*dnfv1.GuildDonateResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.GuildDonateResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	// 1. 获取角色的公会成员信息
	member, err := s.Store.GetDriver().GetGuildMember(ctx, &store.FindGuildMember{RoleID: &roleID})
	if err != nil {
		return &dnfv1.GuildDonateResponse{Error: 9}, nil // 不在公会中
	}

	// 2. 获取公会信息
	guild, err := s.Store.GetGuild(ctx, &store.FindGuild{FindBase: store.FindBase{ID: &member.GuildID}})
	if err != nil {
		return &dnfv1.GuildDonateResponse{Error: ErrCodeSystemError}, nil
	}

	// 3. 处理捐赠
	var contribution int64
	switch req.DonateType {
	case 1: // 金币
		currency, err := s.Store.GetRoleCurrency(ctx, roleID)
		if err != nil {
			return &dnfv1.GuildDonateResponse{Error: ErrCodeSystemError}, nil
		}
		if currency.Gold < int64(req.Amount) {
			return &dnfv1.GuildDonateResponse{Error: 3}, nil // 金币不足
		}
		currency.Gold -= int64(req.Amount)
		if err := s.Store.UpdateRoleCurrency(ctx, currency); err != nil {
			return &dnfv1.GuildDonateResponse{Error: ErrCodeSystemError}, nil
		}
		// 金币捐赠：1金币 = 1贡献，公会获得1资金
		contribution = int64(req.Amount)

	case 2: // 点券
		currency, err := s.Store.GetRoleCurrency(ctx, roleID)
		if err != nil {
			return &dnfv1.GuildDonateResponse{Error: ErrCodeSystemError}, nil
		}
		if currency.Coin < int64(req.Amount) {
			return &dnfv1.GuildDonateResponse{Error: 3}, nil // 点券不足
		}
		currency.Coin -= int64(req.Amount)
		if err := s.Store.UpdateRoleCurrency(ctx, currency); err != nil {
			return &dnfv1.GuildDonateResponse{Error: ErrCodeSystemError}, nil
		}
		// 点券捐赠：1点券 = 10贡献，公会获得10资金
		contribution = int64(req.Amount) * 10

	default:
		return &dnfv1.GuildDonateResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 4. 更新个人贡献
	newContribution := member.Contribution + contribution
	newPosition := member.Position
	position := &newPosition
	err = s.Store.GetDriver().UpdateGuildMember(ctx, &store.UpdateGuildMember{
		ID:           member.ID,
		Contribution: &newContribution,
		Position:     position,
	})
	if err != nil {
		return &dnfv1.GuildDonateResponse{Error: ErrCodeSystemError}, nil
	}

	// 5. 更新公会资金和贡献
	newFund := guild.Fund + contribution
	_, err = s.Store.UpdateGuild(ctx, &store.UpdateGuild{
		ID:   member.GuildID,
		Fund: &newFund,
	})
	if err != nil {
		return &dnfv1.GuildDonateResponse{Error: ErrCodeSystemError}, nil
	}

	return &dnfv1.GuildDonateResponse{
		Error:             ErrCodeSuccess,
		TotalContribution: int32(newContribution),
		GuildExpGain:      int32(contribution),
	}, nil
}

// GetGuildSkill 获取公会技能
func (s *APIV1Service) GetGuildSkill(ctx context.Context, req *dnfv1.GetGuildSkillRequest) (*dnfv1.GetGuildSkillResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.GetGuildSkillResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	// 1. 获取角色的公会成员信息
	_, err := s.Store.GetDriver().GetGuildMember(ctx, &store.FindGuildMember{RoleID: &roleID})
	if err != nil {
		return &dnfv1.GetGuildSkillResponse{Error: 9}, nil // 不在公会中
	}

	// 2. 返回固定的公会技能列表（简化版）
	skills := []*dnfv1.GuildSkill{
		{SkillId: 1, Level: 1, MaxLevel: 10, EffectValue: 10},
		{SkillId: 2, Level: 1, MaxLevel: 10, EffectValue: 5},
		{SkillId: 3, Level: 1, MaxLevel: 10, EffectValue: 15},
	}

	return &dnfv1.GetGuildSkillResponse{
		Error:  ErrCodeSuccess,
		Skills: skills,
	}, nil
}

// UpgradeGuildSkill 升级公会技能
func (s *APIV1Service) UpgradeGuildSkill(ctx context.Context, req *dnfv1.UpgradeGuildSkillRequest) (*dnfv1.UpgradeGuildSkillResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.UpgradeGuildSkillResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	// 1. 获取角色的公会成员信息
	member, err := s.Store.GetDriver().GetGuildMember(ctx, &store.FindGuildMember{RoleID: &roleID})
	if err != nil {
		return &dnfv1.UpgradeGuildSkillResponse{Error: 9}, nil // 不在公会中
	}

	// 2. 检查权限（只有会长和副会长能升级技能）
	if member.Position < 2 {
		return &dnfv1.UpgradeGuildSkillResponse{Error: 11}, nil // 权限不足
	}

	// 3. 获取公会信息
	guild, err := s.Store.GetGuild(ctx, &store.FindGuild{FindBase: store.FindBase{ID: &member.GuildID}})
	if err != nil {
		return &dnfv1.UpgradeGuildSkillResponse{Error: ErrCodeSystemError}, nil
	}

	// 4. 计算升级费用（简化版：技能ID × 10000 × 当前等级）
	currentLevel := int32(1)
	upgradeCost := int64(req.SkillId) * 10000 * int64(currentLevel)

	// 5. 检查公会资金
	if guild.Fund < upgradeCost {
		return &dnfv1.UpgradeGuildSkillResponse{Error: 12}, nil // 公会资金不足
	}

	// 6. 扣除公会资金
	newFund := guild.Fund - upgradeCost
	_, err = s.Store.UpdateGuild(ctx, &store.UpdateGuild{
		ID:   member.GuildID,
		Fund: &newFund,
	})
	if err != nil {
		return &dnfv1.UpgradeGuildSkillResponse{Error: ErrCodeSystemError}, nil
	}

	// 7. 返回升级后的技能（简化版）
	newLevel := currentLevel + 1
	newEffect := int32(req.SkillId) * 5 * newLevel

	return &dnfv1.UpgradeGuildSkillResponse{
		Error: ErrCodeSuccess,
		Skill: &dnfv1.GuildSkill{
			SkillId:     req.SkillId,
			Level:       newLevel,
			MaxLevel:    10,
			EffectValue: newEffect,
		},
	}, nil
}

// ==================== 冒险系统 API ====================

// GetAdventureData 获取冒险数据
func (s *APIV1Service) GetAdventureData(ctx context.Context, req *dnfv1.AdventureDataRequest) (*dnfv1.AdventureDataResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.AdventureDataResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	data, err := s.Store.GetDriver().GetAdventureData(ctx, &store.FindAdventureData{RoleID: &roleID})
	if err != nil {
		if err == store.ErrNotFound {
			// 创建默认冒险数据
			newData, createErr := s.Store.GetDriver().CreateAdventureData(ctx, &store.CreateAdventureData{
				RoleID:         roleID,
				AdventureLevel: 1,
				AdventureExp:   0,
				Energy:         100,
				MaxEnergy:      100,
			})
			if createErr != nil {
				return &dnfv1.AdventureDataResponse{Error: ErrCodeSystemError}, nil
			}
			return &dnfv1.AdventureDataResponse{
				Error: ErrCodeSuccess,
				Data: &dnfv1.AdventureData{
					AdventureLevel:     newData.AdventureLevel,
					AdventureExp:       newData.AdventureExp,
					Energy:             newData.Energy,
					MaxEnergy:          newData.MaxEnergy,
					LastEnergyRecovery: newData.LastEnergyRecovery,
				},
			}, nil
		}
		return &dnfv1.AdventureDataResponse{Error: ErrCodeSystemError}, nil
	}

	return &dnfv1.AdventureDataResponse{
		Error: ErrCodeSuccess,
		Data: &dnfv1.AdventureData{
			AdventureLevel:     data.AdventureLevel,
			AdventureExp:       data.AdventureExp,
			Energy:             data.Energy,
			MaxEnergy:          data.MaxEnergy,
			LastEnergyRecovery: data.LastEnergyRecovery,
		},
	}, nil
}

// GetAdventureStorageList 获取冒险存储列表
func (s *APIV1Service) GetAdventureStorageList(ctx context.Context, req *dnfv1.AdventureStorageListRequest) (*dnfv1.AdventureStorageListResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.AdventureStorageListResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	items, err := s.Store.GetDriver().ListAdventureStorageItems(ctx, &store.FindAdventureStorageItem{
		RoleID: &roleID,
		Limit:  func() *int { l := int(req.PageSize); return &l }(),
		Offset: func() *int { o := int((req.Page - 1) * req.PageSize); return &o }(),
	})
	if err != nil {
		return &dnfv1.AdventureStorageListResponse{Error: ErrCodeSystemError}, nil
	}

	var storageItems []*dnfv1.AdventureStorageItem
	for _, item := range items {
		storageItems = append(storageItems, &dnfv1.AdventureStorageItem{
			ItemId:      item.ItemID,
			Count:       item.Count,
			IsBound:     item.IsBound,
			StorageTime: item.StorageTime,
		})
	}

	return &dnfv1.AdventureStorageListResponse{
		Error: ErrCodeSuccess,
		Total: int32(len(items)),
		Items: storageItems,
	}, nil
}

// GetAdventureReapInfo 获取冒险收获信息
func (s *APIV1Service) GetAdventureReapInfo(ctx context.Context, req *dnfv1.AdventureReapInfoRequest) (*dnfv1.AdventureReapInfoResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.AdventureReapInfoResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	reaps, err := s.Store.GetDriver().ListAdventureReaps(ctx, roleID)
	if err != nil {
		return &dnfv1.AdventureReapInfoResponse{Error: ErrCodeSystemError}, nil
	}

	var reapInfos []*dnfv1.AdventureReapInfo
	for _, reap := range reaps {
		reapInfos = append(reapInfos, &dnfv1.AdventureReapInfo{
			ReapId:      reap.ReapID,
			Progress:    reap.Progress,
			Total:       reap.Total,
			IsCompleted: reap.IsCompleted,
			StartTime:   reap.StartTime,
			EndTime:     reap.EndTime,
		})
	}

	return &dnfv1.AdventureReapInfoResponse{
		Error: ErrCodeSuccess,
		Reaps: reapInfos,
	}, nil
}

// ClaimAdventureReapReward 领取冒险收获奖励
func (s *APIV1Service) ClaimAdventureReapReward(ctx context.Context, req *dnfv1.AdventureReapRewardRequest) (*dnfv1.AdventureReapRewardResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.AdventureReapRewardResponse{Error: ErrCodeInvalidParam}, nil
	}

	return &dnfv1.AdventureReapRewardResponse{
		Error:     ErrCodeSuccess,
		IsSuccess: true,
	}, nil
}

// GetAdventureBookInfo 获取冒险书信息
func (s *APIV1Service) GetAdventureBookInfo(ctx context.Context, req *dnfv1.AdventureBookInfoRequest) (*dnfv1.AdventureBookInfoResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.AdventureBookInfoResponse{Error: ErrCodeInvalidParam}, nil
	}
	roleID := claims.UserID

	books, err := s.Store.GetDriver().ListAdventureBooks(ctx, roleID)
	if err != nil {
		return &dnfv1.AdventureBookInfoResponse{Error: ErrCodeSystemError}, nil
	}

	var bookInfos []*dnfv1.AdventureBookInfo
	for _, book := range books {
		conditions, _ := s.Store.GetDriver().ListAdventureBookRewards(ctx, book.BookID)

		var bookConditions []*dnfv1.AdventureBookCondition
		for _, c := range conditions {
			bookConditions = append(bookConditions, &dnfv1.AdventureBookCondition{
				ConditionId: c.RewardID,
				Current:     c.Amount,
				Target:      c.Amount * 2,
				IsCompleted: c.IsClaimed,
			})
		}

		bookInfos = append(bookInfos, &dnfv1.AdventureBookInfo{
			BookId:     book.BookID,
			Name:       book.Name,
			Level:      book.Level,
			Experience: book.Experience,
			Conditions: bookConditions,
		})
	}

	return &dnfv1.AdventureBookInfoResponse{
		Error: ErrCodeSuccess,
		Books: bookInfos,
	}, nil
}

// AdventureAutoSearch 冒险自动搜索
func (s *APIV1Service) AdventureAutoSearch(ctx context.Context, req *dnfv1.AdventureAutoSearchRequest) (*dnfv1.AdventureAutoSearchResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return &dnfv1.AdventureAutoSearchResponse{Error: ErrCodeInvalidParam}, nil
	}

	return &dnfv1.AdventureAutoSearchResponse{
		Error:     ErrCodeSuccess,
		IsSuccess: true,
		Search: &dnfv1.AdventureAutoSearch{
			SearchId:    1,
			Duration:    req.Duration,
			Progress:    0,
			IsCompleted: false,
			StartTime:   time.Now().Unix(),
			EndTime:     time.Now().Add(time.Duration(req.Duration) * time.Minute).Unix(),
		},
	}, nil
}
