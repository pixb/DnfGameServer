package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// GetGuildInfoHandler 处理获取公会信息请求
func GetGuildInfoHandler(session *network.Session, msg proto.Message) {
	_, ok := msg.(*dnfv1.GetGuildInfoRequest)
	if !ok {
		logger.Error("invalid message type for get guild info")
		return
	}

	logger.Info("get guild info request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 从数据库加载公会信息
	resp := &dnfv1.GetGuildInfoResponse{
		Error: 0,
		Guild: &dnfv1.GuildInfo{
			GuildId:        1001,
			Name:           "勇者公会",
			Level:          5,
			Exp:            50000,
			Notice:         "欢迎加入勇者公会！",
			MemberCount:    30,
			MaxMemberCount: 50,
			MasterName:     "会长",
			CreateTime:     1700000000,
		},
		Members: []*dnfv1.GuildMember{
			{
				Uid:           10001,
				Name:          "会长",
				Job:           1,
				Level:         60,
				Position:      dnfv1.GuildPosition_MASTER,
				Contribution:  10000,
				LastLoginTime: 1707123456,
				Online:        true,
			},
			{
				Uid:           10002,
				Name:          "副会长",
				Job:           2,
				Level:         55,
				Position:      dnfv1.GuildPosition_VICE_MASTER,
				Contribution:  5000,
				LastLoginTime: 1707120000,
				Online:        true,
			},
		},
	}

	if err := session.WriteResponse(10007, 1, resp); err != nil {
		logger.Error("failed to send guild info response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// CreateGuildHandler 处理创建公会请求
func CreateGuildHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.CreateGuildRequest)
	if !ok {
		logger.Error("invalid message type for create guild")
		return
	}

	logger.Info("create guild request received",
		logger.String("name", req.Name),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查公会名是否可用，检查创建条件，创建公会
	resp := &dnfv1.CreateGuildResponse{
		Error:   0,
		GuildId: 1002,
	}

	if err := session.WriteResponse(10007, 3, resp); err != nil {
		logger.Error("failed to send create guild response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// JoinGuildHandler 处理加入公会请求
func JoinGuildHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.JoinGuildRequest)
	if !ok {
		logger.Error("invalid message type for join guild")
		return
	}

	logger.Info("join guild request received",
		logger.Int64("guild_id", req.GuildId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查公会是否存在，是否有空位，发送申请或直接加入
	resp := &dnfv1.JoinGuildResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10007, 5, resp); err != nil {
		logger.Error("failed to send join guild response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// LeaveGuildHandler 处理退出公会请求
func LeaveGuildHandler(session *network.Session, msg proto.Message) {
	_, ok := msg.(*dnfv1.LeaveGuildRequest)
	if !ok {
		logger.Error("invalid message type for leave guild")
		return
	}

	logger.Info("leave guild request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查是否是会长，移交会长或解散公会
	resp := &dnfv1.LeaveGuildResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10007, 7, resp); err != nil {
		logger.Error("failed to send leave guild response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// GuildDonateHandler 处理公会贡献请求
func GuildDonateHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GuildDonateRequest)
	if !ok {
		logger.Error("invalid message type for guild donate")
		return
	}

	logger.Info("guild donate request received",
		logger.Int32("donate_type", req.DonateType),
		logger.Int32("amount", req.Amount),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证贡献类型和数量，扣除资源，增加贡献度和公会经验
	resp := &dnfv1.GuildDonateResponse{
		Error:             0,
		TotalContribution: 1500,
		GuildExpGain:      int32(req.Amount / 10),
	}

	if err := session.WriteResponse(10007, 9, resp); err != nil {
		logger.Error("failed to send guild donate response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// GetGuildSkillHandler 处理获取公会技能请求
func GetGuildSkillHandler(session *network.Session, msg proto.Message) {
	_, ok := msg.(*dnfv1.GetGuildSkillRequest)
	if !ok {
		logger.Error("invalid message type for get guild skill")
		return
	}

	logger.Info("get guild skill request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 加载公会技能列表
	resp := &dnfv1.GetGuildSkillResponse{
		Error: 0,
		Skills: []*dnfv1.GuildSkill{
			{
				SkillId:     1,
				Level:       3,
				MaxLevel:    10,
				EffectValue: 30,
			},
			{
				SkillId:     2,
				Level:       2,
				MaxLevel:    10,
				EffectValue: 20,
			},
		},
	}

	if err := session.WriteResponse(10007, 11, resp); err != nil {
		logger.Error("failed to send guild skill response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// UpgradeGuildSkillHandler 处理升级公会技能请求
func UpgradeGuildSkillHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.UpgradeGuildSkillRequest)
	if !ok {
		logger.Error("invalid message type for upgrade guild skill")
		return
	}

	logger.Info("upgrade guild skill request received",
		logger.Int32("skill_id", req.SkillId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查公会等级和资金，升级技能
	resp := &dnfv1.UpgradeGuildSkillResponse{
		Error: 0,
		Skill: &dnfv1.GuildSkill{
			SkillId:     req.SkillId,
			Level:       4,
			MaxLevel:    10,
			EffectValue: 40,
		},
	}

	if err := session.WriteResponse(10007, 13, resp); err != nil {
		logger.Error("failed to send upgrade guild skill response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
