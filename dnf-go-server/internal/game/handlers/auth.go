package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

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

	// TODO: 实现实际的登录逻辑
	// 1. 验证openid/token
	// 2. 生成auth_key和account_key
	// 3. 加载用户信息
	// 4. 绑定uid到session

	// 模拟登录成功
	resp := &dnfv1.LoginResponse{
		Error:      0,
		AuthKey:    "mock_auth_key_12345",
		AccountKey: "mock_account_key_67890",
		Encrypt:    true,
		ServerTime: 1707123456,
		LocalTime:  "2026-02-06 15:30:00",
		Authority:  0,
		Key:        "session_key_mock",
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
}
