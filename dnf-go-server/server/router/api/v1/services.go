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

// ==================== 错误码定义 ====================
const (
	ErrCodeSuccess          = 0
	ErrCodeInvalidParam     = 1
	ErrCodeAccountNotFound  = 2
	ErrCodeRoleNameExists   = 3
	ErrCodeRoleLimitReached = 4
	ErrCodeInvalidRoleName  = 5
	ErrCodeSystemError      = 99
)

// ==================== AuthService 实现 ====================

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
		LastLoginIP: func() *string { s := "127.0.0.1"; return &s }(), // 实际应从context获取
	})
	if err != nil {
		// 登录信息更新失败不应阻断登录流程，仅记录日志
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

	// 5. 返回响应（带频道列表）
	return &dnfv1.LoginResponse{
		Error:      ErrCodeSuccess,
		AuthKey:    accessToken,
		AccountKey: refreshToken,
		Encrypt:    true,
		ServerTime: uint32(now),
		LocalTime:  time.Now().Format("2006-01-02 15:04:05"),
		Authority:  uint32(account.Authority),
		Key:        "session_key",
		WorldId:    1,
		Channels: []*dnfv1.ChannelInfo{
			{
				World:    1,
				Channel:  1,
				Ip:       "127.0.0.1",
				Port:     9001,
				Priority: 1,
			},
			{
				World:    1,
				Channel:  2,
				Ip:       "127.0.0.1",
				Port:     9002,
				Priority: 2,
			},
		},
		Seeds: []int32{12345, 67890, 11111, 22222, 33333, 44444, 55555, 66666},
	}, nil
}

// GetCharacterList 获取角色列表
func (s *APIV1Service) GetCharacterList(ctx context.Context, req *dnfv1.CharacterListRequest) (*dnfv1.CharacterListResponse, error) {
	// 获取当前用户ID
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// 查询角色列表
	roles, err := s.Store.ListRolesByAccount(ctx, claims.UserID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to list roles: %v", err)
	}

	// 转换为proto消息
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
		return &dnfv1.CreateCharacterResponse{
			Error: ErrCodeInvalidRoleName,
		}, nil
	}

	// 2. 检查角色名是否已存在
	_, err := s.Store.GetRoleByName(ctx, req.Name)
	if err == nil {
		return &dnfv1.CreateCharacterResponse{
			Error: ErrCodeRoleNameExists,
		}, nil
	} else if err != store.ErrNotFound {
		return nil, status.Errorf(codes.Internal, "failed to check role name: %v", err)
	}

	// 3. 检查角色数量限制（最多4个）
	roleCount, err := s.Store.CountRolesByAccount(ctx, claims.UserID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to count roles: %v", err)
	}
	if roleCount >= 4 {
		return &dnfv1.CreateCharacterResponse{
			Error: ErrCodeRoleLimitReached,
		}, nil
	}

	// 4. 检查槽位是否已被占用
	slot := req.Slot
	existingRoles, err := s.Store.ListRolesByAccount(ctx, claims.UserID)
	if err != nil {
		return nil, status.Errorf(codes.Internal, "failed to list roles: %v", err)
	}
	for _, r := range existingRoles {
		if r.RoleID == slot {
			return &dnfv1.CreateCharacterResponse{
				Error: ErrCodeInvalidParam,
			}, nil
		}
	}

	// 5. 创建角色（需要事务）
	role, err := s.createRoleWithInitialization(ctx, claims.UserID, slot, req.Name, req.Job)
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

// createRoleWithInitialization 创建角色并初始化属性和货币
func (s *APIV1Service) createRoleWithInitialization(ctx context.Context, accountID uint64, slot int32, name string, job int32) (*store.Role, error) {
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

	// 初始化角色属性
	_, err = s.Store.CreateRoleAttributes(ctx, &store.RoleAttributes{
		RoleID:          role.ID,
		HP:              100,
		MaxHP:           100,
		MP:              100,
		MaxMP:           100,
		Strength:        10,
		Intelligence:    10,
		Vitality:        10,
		Spirit:          10,
		PhysicalAttack:  10,
		PhysicalDefense: 5,
		MagicAttack:     10,
		MagicDefense:    5,
		MoveSpeed:       100,
		AttackSpeed:     100,
		CastSpeed:       100,
	})
	if err != nil {
		// TODO: 应该回滚角色创建
		return nil, fmt.Errorf("create role attributes failed: %w", err)
	}

	// 初始化角色货币
	err = s.Store.UpdateRoleCurrency(ctx, &store.RoleCurrency{
		RoleID:     role.ID,
		Gold:       1000, // 初始金币
		Coin:       0,
		Fatigue:    100,
		MaxFatigue: 100,
	})
	if err != nil {
		// TODO: 应该回滚角色创建和属性创建
		return nil, fmt.Errorf("create role currency failed: %w", err)
	}

	return role, nil
}

// SelectCharacter 选择角色
func (s *APIV1Service) SelectCharacter(ctx context.Context, req *dnfv1.SelectCharacterRequest) (*dnfv1.SelectCharacterResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// 验证角色存在且属于当前用户
	roleID := uint64(req.Uid)
	role, err := s.Store.GetRole(ctx, &store.FindRole{
		FindBase: store.FindBase{ID: &roleID},
	})
	if err != nil {
		if err == store.ErrNotFound {
			return &dnfv1.SelectCharacterResponse{
				Error: ErrCodeNotFound,
			}, nil
		}
		return nil, status.Errorf(codes.Internal, "failed to get role: %v", err)
	}

	// 验证角色归属
	if role.AccountID != claims.UserID {
		return nil, status.Errorf(codes.PermissionDenied, "role does not belong to user")
	}

	// 生成游戏服务器 Token
	gameToken, err := auth.GenerateAccessToken(role.ID, claims.OpenID, int(role.Job), s.Secret)
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

// ==================== GameService 实现 (占位) ====================

// GetRoleInfo 获取角色信息
func (s *APIV1Service) GetRoleInfo(ctx context.Context, req *dnfv1.GetRoleInfoRequest) (*dnfv1.GetRoleInfoResponse, error) {
	claims := auth.GetUserClaimsFromContext(ctx)
	if claims == nil {
		return nil, status.Errorf(codes.Unauthenticated, "not authenticated")
	}

	// 查询角色
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

	// 验证角色归属
	if role.AccountID != claims.UserID {
		return nil, status.Errorf(codes.PermissionDenied, "role does not belong to user")
	}

	// 查询属性
	attrs, _ := s.Store.GetRoleAttributes(ctx, role.ID)

	// 查询货币
	currency, _ := s.Store.GetRoleCurrency(ctx, role.ID)

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
			Hp:              attrs.HP,
			MaxHp:           attrs.MaxHP,
			Mp:              attrs.MP,
			MaxMp:           attrs.MaxMP,
			PhysicalAttack:  attrs.PhysicalAttack,
			PhysicalDefense: attrs.PhysicalDefense,
			MagicAttack:     attrs.MagicAttack,
			MagicDefense:    attrs.MagicDefense,
			MoveSpeed:       attrs.MoveSpeed,
			AttackSpeed:     attrs.AttackSpeed,
			CastSpeed:       attrs.CastSpeed,
		},
		Position: &dnfv1.RolePosition{
			MapId: role.MapID,
			X:     role.X,
			Y:     role.Y,
		},
		Skills: nil,
	}, nil
}

// UpdateAttributes 更新角色属性
func (s *APIV1Service) UpdateAttributes(ctx context.Context, req *dnfv1.UpdateAttributesRequest) (*dnfv1.UpdateAttributesResponse, error) {
	return &dnfv1.UpdateAttributesResponse{Error: 0}, nil
}

// LearnSkill 学习技能
func (s *APIV1Service) LearnSkill(ctx context.Context, req *dnfv1.LearnSkillRequest) (*dnfv1.LearnSkillResponse, error) {
	return &dnfv1.LearnSkillResponse{Error: 0}, nil
}

// UpgradeSkill 升级技能
func (s *APIV1Service) UpgradeSkill(ctx context.Context, req *dnfv1.UpgradeSkillRequest) (*dnfv1.UpgradeSkillResponse, error) {
	return &dnfv1.UpgradeSkillResponse{Error: 0}, nil
}

// RecoverFatigue 恢复疲劳值
func (s *APIV1Service) RecoverFatigue(ctx context.Context, req *dnfv1.RecoverFatigueRequest) (*dnfv1.RecoverFatigueResponse, error) {
	return &dnfv1.RecoverFatigueResponse{Error: 0}, nil
}

// GetBag 获取背包
func (s *APIV1Service) GetBag(ctx context.Context, req *dnfv1.GetBagRequest) (*dnfv1.GetBagResponse, error) {
	return &dnfv1.GetBagResponse{Error: 0}, nil
}

// UseItem 使用物品
func (s *APIV1Service) UseItem(ctx context.Context, req *dnfv1.UseItemRequest) (*dnfv1.UseItemResponse, error) {
	return &dnfv1.UseItemResponse{Error: 0}, nil
}

// MoveItem 移动物品
func (s *APIV1Service) MoveItem(ctx context.Context, req *dnfv1.MoveItemRequest) (*dnfv1.MoveItemResponse, error) {
	return &dnfv1.MoveItemResponse{Error: 0}, nil
}

// SellItem 出售物品
func (s *APIV1Service) SellItem(ctx context.Context, req *dnfv1.SellItemRequest) (*dnfv1.SellItemResponse, error) {
	return &dnfv1.SellItemResponse{Error: 0}, nil
}

// EquipItem 装备物品
func (s *APIV1Service) EquipItem(ctx context.Context, req *dnfv1.EquipItemRequest) (*dnfv1.EquipItemResponse, error) {
	return &dnfv1.EquipItemResponse{Error: 0}, nil
}

// EnterDungeon 进入副本
func (s *APIV1Service) EnterDungeon(ctx context.Context, req *dnfv1.EnterDungeonRequest) (*dnfv1.EnterDungeonResponse, error) {
	return &dnfv1.EnterDungeonResponse{Error: 0}, nil
}

// ExitDungeon 退出副本
func (s *APIV1Service) ExitDungeon(ctx context.Context, req *dnfv1.ExitDungeonRequest) (*dnfv1.ExitDungeonResponse, error) {
	return &dnfv1.ExitDungeonResponse{Error: 0}, nil
}

// Revive 复活
func (s *APIV1Service) Revive(ctx context.Context, req *dnfv1.ReviveRequest) (*dnfv1.ReviveResponse, error) {
	return &dnfv1.ReviveResponse{Error: 0}, nil
}

// ChangeRoom 切换房间
func (s *APIV1Service) ChangeRoom(ctx context.Context, req *dnfv1.ChangeRoomRequest) (*dnfv1.ChangeRoomResponse, error) {
	return &dnfv1.ChangeRoomResponse{Error: 0}, nil
}

// SendChat 发送聊天
func (s *APIV1Service) SendChat(ctx context.Context, req *dnfv1.SendChatRequest) (*dnfv1.SendChatResponse, error) {
	return &dnfv1.SendChatResponse{Error: 0}, nil
}

// GetChatHistory 获取聊天历史
func (s *APIV1Service) GetChatHistory(ctx context.Context, req *dnfv1.GetChatHistoryRequest) (*dnfv1.GetChatHistoryResponse, error) {
	return &dnfv1.GetChatHistoryResponse{Error: 0}, nil
}

// GetFriendList 获取好友列表
func (s *APIV1Service) GetFriendList(ctx context.Context, req *dnfv1.GetFriendListRequest) (*dnfv1.GetFriendListResponse, error) {
	return &dnfv1.GetFriendListResponse{Error: 0}, nil
}

// AddFriend 添加好友
func (s *APIV1Service) AddFriend(ctx context.Context, req *dnfv1.AddFriendRequest) (*dnfv1.AddFriendResponse, error) {
	return &dnfv1.AddFriendResponse{Error: 0}, nil
}

// ReplyFriendRequest 回复好友申请
func (s *APIV1Service) ReplyFriendRequest(ctx context.Context, req *dnfv1.ReplyFriendRequestMessage) (*emptypb.Empty, error) {
	return &emptypb.Empty{}, nil
}

// RemoveFriend 删除好友
func (s *APIV1Service) RemoveFriend(ctx context.Context, req *dnfv1.RemoveFriendRequest) (*dnfv1.RemoveFriendResponse, error) {
	return &dnfv1.RemoveFriendResponse{Error: 0}, nil
}

// GetShopList 获取商店列表
func (s *APIV1Service) GetShopList(ctx context.Context, req *dnfv1.GetShopListRequest) (*dnfv1.GetShopListResponse, error) {
	return &dnfv1.GetShopListResponse{Error: 0}, nil
}

// BuyItem 购买物品
func (s *APIV1Service) BuyItem(ctx context.Context, req *dnfv1.BuyItemRequest) (*dnfv1.BuyItemResponse, error) {
	return &dnfv1.BuyItemResponse{Error: 0}, nil
}

// SellToShop 出售给商店
func (s *APIV1Service) SellToShop(ctx context.Context, req *dnfv1.SellToShopRequest) (*dnfv1.SellToShopResponse, error) {
	return &dnfv1.SellToShopResponse{Error: 0}, nil
}

// SearchAuction 搜索拍卖行
func (s *APIV1Service) SearchAuction(ctx context.Context, req *dnfv1.SearchAuctionRequest) (*dnfv1.SearchAuctionResponse, error) {
	return &dnfv1.SearchAuctionResponse{Error: 0}, nil
}

// RegisterAuction 上架拍卖
func (s *APIV1Service) RegisterAuction(ctx context.Context, req *dnfv1.RegisterAuctionRequest) (*dnfv1.RegisterAuctionResponse, error) {
	return &dnfv1.RegisterAuctionResponse{Error: 0}, nil
}

// BidAuction 竞拍
func (s *APIV1Service) BidAuction(ctx context.Context, req *dnfv1.BidAuctionRequest) (*dnfv1.BidAuctionResponse, error) {
	return &dnfv1.BidAuctionResponse{Error: 0}, nil
}

// BuyoutAuction 一口价购买
func (s *APIV1Service) BuyoutAuction(ctx context.Context, req *dnfv1.BuyoutAuctionRequest) (*dnfv1.BuyoutAuctionResponse, error) {
	return &dnfv1.BuyoutAuctionResponse{Error: 0}, nil
}

// OpenPrivateStore 开设个人商店
func (s *APIV1Service) OpenPrivateStore(ctx context.Context, req *dnfv1.OpenPrivateStoreRequest) (*dnfv1.OpenPrivateStoreResponse, error) {
	return &dnfv1.OpenPrivateStoreResponse{Error: 0}, nil
}

// ViewPrivateStore 查看个人商店
func (s *APIV1Service) ViewPrivateStore(ctx context.Context, req *dnfv1.ViewPrivateStoreRequest) (*dnfv1.ViewPrivateStoreResponse, error) {
	return &dnfv1.ViewPrivateStoreResponse{Error: 0}, nil
}

// BuyFromPrivateStore 从个人商店购买
func (s *APIV1Service) BuyFromPrivateStore(ctx context.Context, req *dnfv1.BuyFromPrivateStoreRequest) (*dnfv1.BuyFromPrivateStoreResponse, error) {
	return &dnfv1.BuyFromPrivateStoreResponse{Error: 0}, nil
}

// GetQuestList 获取任务列表
func (s *APIV1Service) GetQuestList(ctx context.Context, req *dnfv1.GetQuestListRequest) (*dnfv1.GetQuestListResponse, error) {
	return &dnfv1.GetQuestListResponse{Error: 0}, nil
}

// AcceptQuest 接受任务
func (s *APIV1Service) AcceptQuest(ctx context.Context, req *dnfv1.AcceptQuestRequest) (*dnfv1.AcceptQuestResponse, error) {
	return &dnfv1.AcceptQuestResponse{Error: 0}, nil
}

// CompleteQuest 完成任务
func (s *APIV1Service) CompleteQuest(ctx context.Context, req *dnfv1.CompleteQuestRequest) (*dnfv1.CompleteQuestResponse, error) {
	return &dnfv1.CompleteQuestResponse{Error: 0}, nil
}

// GetQuestReward 领取任务奖励
func (s *APIV1Service) GetQuestReward(ctx context.Context, req *dnfv1.GetQuestRewardRequest) (*dnfv1.GetQuestRewardResponse, error) {
	return &dnfv1.GetQuestRewardResponse{Error: 0}, nil
}

// AbandonQuest 放弃任务
func (s *APIV1Service) AbandonQuest(ctx context.Context, req *dnfv1.AbandonQuestRequest) (*dnfv1.AbandonQuestResponse, error) {
	return &dnfv1.AbandonQuestResponse{Error: 0}, nil
}

// GetGuildInfo 获取公会信息
func (s *APIV1Service) GetGuildInfo(ctx context.Context, req *dnfv1.GetGuildInfoRequest) (*dnfv1.GetGuildInfoResponse, error) {
	return &dnfv1.GetGuildInfoResponse{Error: 0}, nil
}

// CreateGuild 创建公会
func (s *APIV1Service) CreateGuild(ctx context.Context, req *dnfv1.CreateGuildRequest) (*dnfv1.CreateGuildResponse, error) {
	return &dnfv1.CreateGuildResponse{Error: 0}, nil
}

// JoinGuild 加入公会
func (s *APIV1Service) JoinGuild(ctx context.Context, req *dnfv1.JoinGuildRequest) (*dnfv1.JoinGuildResponse, error) {
	return &dnfv1.JoinGuildResponse{Error: 0}, nil
}

// LeaveGuild 离开公会
func (s *APIV1Service) LeaveGuild(ctx context.Context, req *dnfv1.LeaveGuildRequest) (*dnfv1.LeaveGuildResponse, error) {
	return &dnfv1.LeaveGuildResponse{Error: 0}, nil
}

// GuildDonate 公会捐赠
func (s *APIV1Service) GuildDonate(ctx context.Context, req *dnfv1.GuildDonateRequest) (*dnfv1.GuildDonateResponse, error) {
	return &dnfv1.GuildDonateResponse{Error: 0}, nil
}

// GetGuildSkill 获取公会技能
func (s *APIV1Service) GetGuildSkill(ctx context.Context, req *dnfv1.GetGuildSkillRequest) (*dnfv1.GetGuildSkillResponse, error) {
	return &dnfv1.GetGuildSkillResponse{Error: 0}, nil
}

// UpgradeGuildSkill 升级公会技能
func (s *APIV1Service) UpgradeGuildSkill(ctx context.Context, req *dnfv1.UpgradeGuildSkillRequest) (*dnfv1.UpgradeGuildSkillResponse, error) {
	return &dnfv1.UpgradeGuildSkillResponse{Error: 0}, nil
}
