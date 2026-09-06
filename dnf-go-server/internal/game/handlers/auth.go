package handlers

import (
	"context"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"google.golang.org/protobuf/proto"
)

// authStore 认证 Store(由 serve 启动时注入)
var authStore *store.Store

// InitAuthStore 初始化认证 Store(2026-09-06 第三十二轮: LoginHandler 实化)
func InitAuthStore(s *store.Store) {
	authStore = s
}

// LoginHandler 处理登录请求
func LoginHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.LoginRequest)
	if !ok {
		logger.Error("invalid message type for login")
		return
	}

	logger.Info("login request received",
		logger.String("openid", req.Openid),
		logger.String("version", req.Version),
		logger.Int64("session_id", session.ID()),
	)

	ctx := context.Background()
	now := time.Now().Unix()

	// 空 openid 拒绝
	if req.Openid == "" {
		session.WriteResponse(10000, 1, &dnfv1.LoginResponse{Error: 1})
		return
	}

	// 查询或创建账号(2026-09-06 第三十二轮: 真实落库, 替代 mock)
	var account *store.Account
	var err error
	if authStore == nil {
		logger.Error("auth store not initialized")
		session.WriteResponse(10000, 1, &dnfv1.LoginResponse{Error: 3})
		return
	}
	account, err = authStore.GetAccount(ctx, &store.FindAccount{OpenID: &req.Openid})
	if err == store.ErrNotFound {
		accountKey := fmt.Sprintf("%d", time.Now().UnixNano())
		acc, cerr := authStore.CreateAccount(ctx, &store.Account{
			OpenID:      req.Openid,
			AccountKey:  accountKey,
			LastLoginAt: now,
			LastLoginIP: req.ClientIp,
			Authority:   0,
			Status:      0, // 0=正常
		})
		if cerr != nil {
			logger.Error("create account failed", logger.String("openid", req.Openid), logger.ErrorField(cerr))
			session.WriteResponse(10000, 1, &dnfv1.LoginResponse{Error: 2})
			return
		}
		account = acc
	} else if err != nil {
		logger.Error("query account failed", logger.String("openid", req.Openid), logger.ErrorField(err))
		session.WriteResponse(10000, 1, &dnfv1.LoginResponse{Error: 3})
		return
	} else {
		// 注: 封禁语义跨协议不一致(HTTP auth_service 用 1=正常/0=禁用, store 注释 0=正常/1=封禁),
		// 暂不在此做封禁拒绝, 待统一后启用(2026-09-06 第三十二轮遗留)
		// 更新最后登录时间
		authStore.UpdateAccount(ctx, &store.UpdateAccount{ID: account.ID, LastLoginAt: &now})
	}

	// 生成认证密钥并落库
	authKey := fmt.Sprintf("%d_%s", time.Now().UnixNano(), account.AccountKey)
	authStore.UpdateAccount(ctx, &store.UpdateAccount{ID: account.ID, AuthKey: &authKey})

	resp := &dnfv1.LoginResponse{
		Error:      0,
		AuthKey:    authKey,
		AccountKey: account.AccountKey,
		Encrypt:    true,
		ServerTime: uint64(now),
		LocalTime:  time.Unix(now, 0).Format("2006-01-02 15:04:05"),
		Authority:  uint32(account.Authority),
		Key:        "session_key_" + req.Openid,
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
	}

	// 发送响应 (module=10000, cmd=1)
	if err := session.WriteResponse(10000, 1, resp); err != nil {
		logger.Error("failed to send login response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	// 绑定openid到session
	session.SetAttr("openid", req.Openid)
}

// CreateCharacterHandler 处理创建角色请求
func CreateCharacterHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.CreateCharacterRequest)
	if !ok {
		logger.Error("invalid message type for create character")
		return
	}

	logger.Info("create character request received",
		logger.String("name", req.Name),
		logger.Int32("job", req.Job),
		logger.Int32("slot", req.Slot),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 实现角色创建逻辑
	// 1. 检查角色名是否可用
	// 2. 创建角色数据
	// 3. 保存到数据库

	resp := &dnfv1.CreateCharacterResponse{
		Error:  0,
		Uid:    10001,
		RoleId: 1,
		Name:   req.Name,
		Job:    req.Job,
		Level:  1,
	}

	if err := session.WriteResponse(10000, 3, resp); err != nil {
		logger.Error("failed to send create character response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// GetCharacterListHandler 处理获取角色列表请求
func GetCharacterListHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.CharacterListRequest)
	if !ok {
		logger.Error("invalid message type for character list")
		return
	}

	logger.Info("character list request received",
		logger.String("openid", req.Openid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 从数据库加载角色列表
	resp := &dnfv1.CharacterListResponse{
		Error: 0,
		Characters: []*dnfv1.CharacterInfo{
			{
				Uid:           10001,
				RoleId:        1,
				Name:          "测试角色",
				DistName:      "",
				Job:           1,
				Level:         50,
				Exp:           1000000,
				Fatigue:       100,
				LastLoginTime: 1707123456,
			},
		},
	}

	if err := session.WriteResponse(10000, 5, resp); err != nil {
		logger.Error("failed to send character list response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// SelectCharacterHandler 处理选择角色请求
func SelectCharacterHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.SelectCharacterRequest)
	if !ok {
		logger.Error("invalid message type for select character")
		return
	}

	logger.Info("select character request received",
		logger.Int64("uid", req.Uid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证角色是否存在并返回游戏服务器连接信息
	resp := &dnfv1.SelectCharacterResponse{
		Error:      0,
		ServerIp:   "127.0.0.1",
		ServerPort: 9001,
		AuthToken:  "game_server_token_12345",
	}

	if err := session.WriteResponse(10000, 7, resp); err != nil {
		logger.Error("failed to send select character response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	// 设置当前角色
	session.SetAttr("current_uid", req.Uid)
	// 2026-09-06 第三十一轮: 选角即绑定角色ID到会话, 后续 QUERY_MY_RANK 等按真实角色计算
	session.SetRoleID(uint64(req.Uid))
}
