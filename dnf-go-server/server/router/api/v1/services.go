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

	_, err = s.Store.GetRole(ctx, &store.FindRole{
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

// ==================== 成就系统 ====================

// AchievementInfo 获取成就信息
func (s *APIV1Service) AchievementInfo(ctx context.Context, req *dnfv1.AchievementInfoRequest) (*dnfv1.AchievementInfoResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.RoleID
	if roleID == 0 {
		return nil, status.Errorf(codes.InvalidArgument, "role not selected")
	}

	achievements, err := s.Store.GetAchievements(ctx, roleID, req.Field_1)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get achievements: %v", err)
	}

	var pbAchievements []*dnfv1.AchievementInfo
	for _, a := range achievements {
		pbAchievements = append(pbAchievements, &dnfv1.AchievementInfo{
			AchievementId: a.AchievementID,
			Name:          a.Name,
			Description:   a.Description,
			Progress:      a.Progress,
			TargetValue:   a.TargetValue,
			Completed:     a.Completed,
			Rewarded:      a.Rewarded,
		})
	}

	return &dnfv1.AchievementInfoResponse{
		Achievements: pbAchievements,
		Error:        ErrCodeSuccess,
	}, nil
}

// AchievementReward 领取成就奖励
func (s *APIV1Service) AchievementReward(ctx context.Context, req *dnfv1.AchievementRewardRequest) (*dnfv1.AchievementRewardResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.RoleID
	if roleID == 0 {
		return nil, status.Errorf(codes.InvalidArgument, "role not selected")
	}

	reward, err := s.Store.ClaimAchievementReward(ctx, roleID, uint32(req.Field_1), uint32(req.Field_2))
	if err != nil {
		return &dnfv1.AchievementRewardResponse{
			Error:   1,
			Message: err.Error(),
		}, nil
	}

	return &dnfv1.AchievementRewardResponse{
		Adventureunionlevel: reward.AdventureUnionLevel,
		Adventureunionexp:   reward.AdventureUnionExp,
		Consumeitems:        reward.ConsumeItems,
		Invenitems:          reward.InvenItems,
		Error:               ErrCodeSuccess,
	}, nil
}

// AchievementList 获取成就列表
func (s *APIV1Service) AchievementList(ctx context.Context, req *dnfv1.AchievementListRequest) (*dnfv1.AchievementListResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.RoleID
	if roleID == 0 {
		return nil, status.Errorf(codes.InvalidArgument, "role not selected")
	}

	result, err := s.Store.GetAchievementList(ctx, roleID, req.Field_1)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to get achievement list: %v", err)
	}

	var pbAchievements []*dnfv1.AchievementInfo
	for _, a := range result.Achievements {
		pbAchievements = append(pbAchievements, &dnfv1.AchievementInfo{
			AchievementId: a.AchievementID,
			Name:          a.Name,
			Description:   a.Description,
			Progress:      a.Progress,
			TargetValue:   a.TargetValue,
			Completed:     a.Completed,
			Rewarded:      a.Rewarded,
		})
	}

	return &dnfv1.AchievementListResponse{
		Achievements: pbAchievements,
		Total:        result.Total,
		Error:        ErrCodeSuccess,
	}, nil
}

// AchievementBonusReward 领取成就额外奖励
func (s *APIV1Service) AchievementBonusReward(ctx context.Context, req *dnfv1.AchievementBonusRewardRequest) (*dnfv1.AchievementBonusRewardResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	roleID := claims.RoleID
	if roleID == 0 {
		return nil, status.Errorf(codes.InvalidArgument, "role not selected")
	}

	rewards, err := s.Store.ClaimAchievementBonusReward(ctx, roleID, uint32(req.Field_1), uint32(req.Field_2), uint32(req.Field_3), uint32(req.Field_4))
	if err != nil {
		return &dnfv1.AchievementBonusRewardResponse{
			Error:   1,
			Message: err.Error(),
		}, nil
	}

	return &dnfv1.AchievementBonusRewardResponse{
		Rewards: rewards.InvenItems.ConsumeItems,
		Error:   ErrCodeSuccess,
	}, nil
}
