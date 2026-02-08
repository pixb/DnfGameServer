package v1

import (
	"context"
	"fmt"
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
	_, err := s.Store.GetRoleByName(ctx, req.Name)
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
	role, err := s.createRoleWithInit(ctx, claims.UserID, req.Slot, req.Name, req.Job)
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

// UpdateAttributes 更新角色属性（属性点分配）
func (s *APIV1Service) UpdateAttributes(ctx context.Context, req *dnfv1.UpdateAttributesRequest) (*dnfv1.UpdateAttributesResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// TODO: 从请求中获取角色ID，这里暂时使用claims中的userID作为roleID
	// 实际上应该从请求参数或session中获取当前选中的角色
	roleID := claims.UserID

	// 获取当前属性
	attrs, err := s.Store.GetRoleAttributes(ctx, roleID)
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.UpdateAttributesResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role attributes: %v", err)
	}

	// 计算新属性值
	// 注意：这里应该检查是否有足够的属性点，暂时简化为直接累加
	newStrength := attrs.Strength + req.Str
	newIntelligence := attrs.Intelligence + req.Dex
	newVitality := attrs.Vitality + req.Vit
	newSpirit := attrs.Spirit + req.Spr

	// 更新属性
	update := &store.UpdateRoleAttributes{
		RoleID:       roleID,
		Strength:     &newStrength,
		Intelligence: &newIntelligence,
		Vitality:     &newVitality,
		Spirit:       &newSpirit,
	}

	// 根据属性计算战斗数值（简化版）
	// 力量影响物理攻击
	physicalAttack := newStrength * 2
	update.PhysicalAttack = &physicalAttack

	// 智力影响魔法攻击
	magicAttack := newIntelligence * 2
	update.MagicAttack = &magicAttack

	// 体力影响生命值和物理防御
	maxHP := 100 + newVitality*10
	update.MaxHP = &maxHP
	physicalDefense := newVitality
	update.PhysicalDefense = &physicalDefense

	// 精神影响魔法值和魔法防御
	maxMP := 100 + newSpirit*10
	update.MaxMP = &maxMP
	magicDefense := newSpirit
	update.MagicDefense = &magicDefense

	err = s.Store.UpdateRoleAttributes(ctx, update)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to update attributes: %v", err)
	}

	return &dnfv1.UpdateAttributesResponse{
		Error: 0,
		BattleInfo: &dnfv1.RoleBattleInfo{
			Hp:    maxHP, // 简化：加满血
			MaxHp: maxHP,
			Mp:    maxMP, // 简化：加满蓝
			MaxMp: maxMP,
		},
	}, nil
}

// LearnSkill 学习技能
func (s *APIV1Service) LearnSkill(ctx context.Context, req *dnfv1.LearnSkillRequest) (*dnfv1.LearnSkillResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取角色信息（用于验证职业）
	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.LearnSkillResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	// 检查技能是否已学习
	existingSkill, err := s.Store.GetRoleSkill(ctx, roleID, req.SkillId)
	if err == nil && existingSkill != nil {
		// 已学习，返回已有技能
		return &dnfv1.LearnSkillResponse{
			Error: 0,
			Skill: &dnfv1.SkillInfo{
				SkillId: existingSkill.SkillID,
				Level:   existingSkill.Level,
			},
		}, nil
	}

	// 获取技能配置
	skill, err := s.Store.GetSkill(ctx, req.SkillId)
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.LearnSkillResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get skill: %v", err)
	}

	// 验证职业要求
	if skill.JobRequired != 0 && skill.JobRequired != role.Job {
		return &dnfv1.LearnSkillResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 验证等级要求
	if skill.LevelRequired > role.Level {
		return &dnfv1.LearnSkillResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 创建角色技能记录
	_, err = s.Store.CreateRoleSkill(ctx, &store.CreateRoleSkill{
		RoleID:    roleID,
		SkillID:   req.SkillId,
		Level:     1,
		IsLearned: true,
	})
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to create role skill: %v", err)
	}

	return &dnfv1.LearnSkillResponse{
		Error: 0,
		Skill: &dnfv1.SkillInfo{
			SkillId: skill.SkillID,
			Name:    skill.Name,
			Level:   1,
		},
	}, nil
}

// UpgradeSkill 升级技能
func (s *APIV1Service) UpgradeSkill(ctx context.Context, req *dnfv1.UpgradeSkillRequest) (*dnfv1.UpgradeSkillResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.UserID

	// 获取角色技能
	roleSkill, err := s.Store.GetRoleSkill(ctx, roleID, req.SkillId)
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.UpgradeSkillResponse{Error: ErrCodeNotFound}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role skill: %v", err)
	}

	// 获取技能配置
	skill, err := s.Store.GetSkill(ctx, req.SkillId)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get skill: %v", err)
	}

	// 检查是否已达最大等级
	if roleSkill.Level >= skill.MaxLevel {
		return &dnfv1.UpgradeSkillResponse{Error: ErrCodeInvalidParam}, nil
	}

	// 升级技能
	newLevel := roleSkill.Level + 1
	update := &store.UpdateRoleSkill{
		ID:    roleSkill.ID,
		Level: &newLevel,
	}

	err = s.Store.UpdateRoleSkill(ctx, update)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to upgrade skill: %v", err)
	}

	return &dnfv1.UpgradeSkillResponse{
		Error: 0,
		Skill: &dnfv1.SkillInfo{
			SkillId: skill.SkillID,
			Name:    skill.Name,
			Level:   newLevel,
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

	// TODO: 如果使用道具恢复，需要扣除道具
	if req.ItemId > 0 {
		// 扣除道具逻辑
	}

	return &dnfv1.RecoverFatigueResponse{
		Error:          0,
		CurrentFatigue: newFatigue,
		MaxFatigue:     role.MaxFatigue,
	}, nil
}
func (s *APIV1Service) GetBag(ctx context.Context, req *dnfv1.GetBagRequest) (*dnfv1.GetBagResponse, error) {
	return &dnfv1.GetBagResponse{Error: 0}, nil
}
func (s *APIV1Service) UseItem(ctx context.Context, req *dnfv1.UseItemRequest) (*dnfv1.UseItemResponse, error) {
	return &dnfv1.UseItemResponse{Error: 0}, nil
}
func (s *APIV1Service) MoveItem(ctx context.Context, req *dnfv1.MoveItemRequest) (*dnfv1.MoveItemResponse, error) {
	return &dnfv1.MoveItemResponse{Error: 0}, nil
}
func (s *APIV1Service) SellItem(ctx context.Context, req *dnfv1.SellItemRequest) (*dnfv1.SellItemResponse, error) {
	return &dnfv1.SellItemResponse{Error: 0}, nil
}
func (s *APIV1Service) EquipItem(ctx context.Context, req *dnfv1.EquipItemRequest) (*dnfv1.EquipItemResponse, error) {
	return &dnfv1.EquipItemResponse{Error: 0}, nil
}
func (s *APIV1Service) EnterDungeon(ctx context.Context, req *dnfv1.EnterDungeonRequest) (*dnfv1.EnterDungeonResponse, error) {
	return &dnfv1.EnterDungeonResponse{Error: 0}, nil
}
func (s *APIV1Service) ExitDungeon(ctx context.Context, req *dnfv1.ExitDungeonRequest) (*dnfv1.ExitDungeonResponse, error) {
	return &dnfv1.ExitDungeonResponse{Error: 0}, nil
}
func (s *APIV1Service) Revive(ctx context.Context, req *dnfv1.ReviveRequest) (*dnfv1.ReviveResponse, error) {
	return &dnfv1.ReviveResponse{Error: 0}, nil
}
func (s *APIV1Service) ChangeRoom(ctx context.Context, req *dnfv1.ChangeRoomRequest) (*dnfv1.ChangeRoomResponse, error) {
	return &dnfv1.ChangeRoomResponse{Error: 0}, nil
}
func (s *APIV1Service) SendChat(ctx context.Context, req *dnfv1.SendChatRequest) (*dnfv1.SendChatResponse, error) {
	return &dnfv1.SendChatResponse{Error: 0}, nil
}
func (s *APIV1Service) GetChatHistory(ctx context.Context, req *dnfv1.GetChatHistoryRequest) (*dnfv1.GetChatHistoryResponse, error) {
	return &dnfv1.GetChatHistoryResponse{Error: 0}, nil
}
func (s *APIV1Service) GetFriendList(ctx context.Context, req *dnfv1.GetFriendListRequest) (*dnfv1.GetFriendListResponse, error) {
	return &dnfv1.GetFriendListResponse{Error: 0}, nil
}
func (s *APIV1Service) AddFriend(ctx context.Context, req *dnfv1.AddFriendRequest) (*dnfv1.AddFriendResponse, error) {
	return &dnfv1.AddFriendResponse{Error: 0}, nil
}
func (s *APIV1Service) ReplyFriendRequest(ctx context.Context, req *dnfv1.ReplyFriendRequestMessage) (*emptypb.Empty, error) {
	return &emptypb.Empty{}, nil
}
func (s *APIV1Service) RemoveFriend(ctx context.Context, req *dnfv1.RemoveFriendRequest) (*dnfv1.RemoveFriendResponse, error) {
	return &dnfv1.RemoveFriendResponse{Error: 0}, nil
}
func (s *APIV1Service) GetShopList(ctx context.Context, req *dnfv1.GetShopListRequest) (*dnfv1.GetShopListResponse, error) {
	return &dnfv1.GetShopListResponse{Error: 0}, nil
}
func (s *APIV1Service) BuyItem(ctx context.Context, req *dnfv1.BuyItemRequest) (*dnfv1.BuyItemResponse, error) {
	return &dnfv1.BuyItemResponse{Error: 0}, nil
}
func (s *APIV1Service) SellToShop(ctx context.Context, req *dnfv1.SellToShopRequest) (*dnfv1.SellToShopResponse, error) {
	return &dnfv1.SellToShopResponse{Error: 0}, nil
}
func (s *APIV1Service) SearchAuction(ctx context.Context, req *dnfv1.SearchAuctionRequest) (*dnfv1.SearchAuctionResponse, error) {
	return &dnfv1.SearchAuctionResponse{Error: 0}, nil
}
func (s *APIV1Service) RegisterAuction(ctx context.Context, req *dnfv1.RegisterAuctionRequest) (*dnfv1.RegisterAuctionResponse, error) {
	return &dnfv1.RegisterAuctionResponse{Error: 0}, nil
}
func (s *APIV1Service) BidAuction(ctx context.Context, req *dnfv1.BidAuctionRequest) (*dnfv1.BidAuctionResponse, error) {
	return &dnfv1.BidAuctionResponse{Error: 0}, nil
}
func (s *APIV1Service) BuyoutAuction(ctx context.Context, req *dnfv1.BuyoutAuctionRequest) (*dnfv1.BuyoutAuctionResponse, error) {
	return &dnfv1.BuyoutAuctionResponse{Error: 0}, nil
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
func (s *APIV1Service) GetQuestList(ctx context.Context, req *dnfv1.GetQuestListRequest) (*dnfv1.GetQuestListResponse, error) {
	return &dnfv1.GetQuestListResponse{Error: 0}, nil
}
func (s *APIV1Service) AcceptQuest(ctx context.Context, req *dnfv1.AcceptQuestRequest) (*dnfv1.AcceptQuestResponse, error) {
	return &dnfv1.AcceptQuestResponse{Error: 0}, nil
}
func (s *APIV1Service) CompleteQuest(ctx context.Context, req *dnfv1.CompleteQuestRequest) (*dnfv1.CompleteQuestResponse, error) {
	return &dnfv1.CompleteQuestResponse{Error: 0}, nil
}
func (s *APIV1Service) GetQuestReward(ctx context.Context, req *dnfv1.GetQuestRewardRequest) (*dnfv1.GetQuestRewardResponse, error) {
	return &dnfv1.GetQuestRewardResponse{Error: 0}, nil
}
func (s *APIV1Service) AbandonQuest(ctx context.Context, req *dnfv1.AbandonQuestRequest) (*dnfv1.AbandonQuestResponse, error) {
	return &dnfv1.AbandonQuestResponse{Error: 0}, nil
}
func (s *APIV1Service) GetGuildInfo(ctx context.Context, req *dnfv1.GetGuildInfoRequest) (*dnfv1.GetGuildInfoResponse, error) {
	return &dnfv1.GetGuildInfoResponse{Error: 0}, nil
}
func (s *APIV1Service) CreateGuild(ctx context.Context, req *dnfv1.CreateGuildRequest) (*dnfv1.CreateGuildResponse, error) {
	return &dnfv1.CreateGuildResponse{Error: 0}, nil
}
func (s *APIV1Service) JoinGuild(ctx context.Context, req *dnfv1.JoinGuildRequest) (*dnfv1.JoinGuildResponse, error) {
	return &dnfv1.JoinGuildResponse{Error: 0}, nil
}
func (s *APIV1Service) LeaveGuild(ctx context.Context, req *dnfv1.LeaveGuildRequest) (*dnfv1.LeaveGuildResponse, error) {
	return &dnfv1.LeaveGuildResponse{Error: 0}, nil
}
func (s *APIV1Service) GuildDonate(ctx context.Context, req *dnfv1.GuildDonateRequest) (*dnfv1.GuildDonateResponse, error) {
	return &dnfv1.GuildDonateResponse{Error: 0}, nil
}
func (s *APIV1Service) GetGuildSkill(ctx context.Context, req *dnfv1.GetGuildSkillRequest) (*dnfv1.GetGuildSkillResponse, error) {
	return &dnfv1.GetGuildSkillResponse{Error: 0}, nil
}
func (s *APIV1Service) UpgradeGuildSkill(ctx context.Context, req *dnfv1.UpgradeGuildSkillRequest) (*dnfv1.UpgradeGuildSkillResponse, error) {
	return &dnfv1.UpgradeGuildSkillResponse{Error: 0}, nil
}
