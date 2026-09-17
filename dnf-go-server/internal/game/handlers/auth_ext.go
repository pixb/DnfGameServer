package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// ==================== 认证模块扩展 (Module = 10000) ====================

// EnterGameHandler 处理进入游戏请求 (cmd=8)
func EnterGameHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for enter game")
		return
	}

	roleID := session.RoleID()
	logger.Info("enter game request received",
		logger.Uint64("role_id", roleID),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 加载角色完整数据，同步进游戏场景
	_ = req
	resp := &dnfv1.SelectCharacterResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10000, 9, resp); err != nil {
		logger.Error("failed to send enter game response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// LoadPlayerDataHandler 处理玩家数据加载请求 (cmd=10)
func LoadPlayerDataHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.Empty)
	if !ok {
		logger.Error("invalid message type for load player data")
		return
	}

	logger.Info("load player data request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 加载玩家背包/任务/属性等完整数据
	_ = req
	resp := &dnfv1.CharacterListResponse{
		Error: 0,
		Characters: []*dnfv1.CharacterInfo{
			{
				Uid:     1,
				Name:    "TestHero",
				Job:     1,
				Level:   1,
			},
		},
	}

	if err := session.WriteResponse(10000, 11, resp); err != nil {
		logger.Error("failed to send load player data response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
