package handlers

import (
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// EnterDungeonHandler 处理进入副本请求
func EnterDungeonHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.EnterDungeonRequest)
	if !ok {
		logger.Error("invalid message type for enter dungeon")
		return
	}

	logger.Info("enter dungeon request received",
		logger.Int32("dungeon_id", req.DungeonId),
		logger.String("difficulty", req.Difficulty.String()),
		logger.Bool("use_party", req.UseParty),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查等级、疲劳值，扣除疲劳，创建副本实例
	resp := &dnfv1.EnterDungeonResponse{
		Error:      0,
		InstanceId: 10001,
		MapId:      1,
		StartPosition: &dnfv1.RolePosition{
			MapId:     1,
			DungeonId: req.DungeonId,
			X:         0.0,
			Y:         0.0,
			Z:         0.0,
		},
		Monsters: []*dnfv1.DungeonMonster{
			{
				MonsterId: 1001,
				Level:     50,
				X:         10.0,
				Y:         10.0,
				Z:         0.0,
				IsBoss:    false,
			},
			{
				MonsterId: 1002,
				Level:     50,
				X:         20.0,
				Y:         20.0,
				Z:         0.0,
				IsBoss:    false,
			},
			{
				MonsterId: 2001,
				Level:     55,
				X:         50.0,
				Y:         50.0,
				Z:         0.0,
				IsBoss:    true,
			},
		},
	}

	if err := session.WriteResponse(10003, 1, resp); err != nil {
		logger.Error("failed to send enter dungeon response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	// 存储副本实例ID到session
	session.SetAttr("dungeon_instance_id", resp.InstanceId)
}

// ExitDungeonHandler 处理退出副本请求
func ExitDungeonHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.ExitDungeonRequest)
	if !ok {
		logger.Error("invalid message type for exit dungeon")
		return
	}

	logger.Info("exit dungeon request received",
		logger.Bool("is_clear", req.IsClear),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 结算副本，发放奖励，返回城镇
	result := &dnfv1.DungeonResult{
		IsClear:   req.IsClear,
		ExpGain:   10000,
		GoldGain:  5000,
		ClearTime: 300,
		Rating:    1, // S级
		Items: []*dnfv1.ItemReward{
			{
				ItemId:  10001,
				Count:   1,
				Quality: dnfv1.ItemQuality_RARE,
			},
			{
				ItemId:  20001,
				Count:   5,
				Quality: dnfv1.ItemQuality_COMMON,
			},
		},
	}

	resp := &dnfv1.ExitDungeonResponse{
		Error:  0,
		Result: result,
	}

	if err := session.WriteResponse(10003, 3, resp); err != nil {
		logger.Error("failed to send exit dungeon response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	// 清除副本实例ID
	session.RemoveAttr("dungeon_instance_id")
}

// ReviveHandler 处理复活请求
func ReviveHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.ReviveRequest)
	if !ok {
		logger.Error("invalid message type for revive")
		return
	}

	logger.Info("revive request received",
		logger.Int32("revive_type", req.ReviveType),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 检查复活类型，扣除金币或道具，复活角色
	resp := &dnfv1.ReviveResponse{
		Error: 0,
		Position: &dnfv1.RolePosition{
			MapId:     1,
			DungeonId: 0,
			X:         0.0,
			Y:         0.0,
			Z:         0.0,
		},
		Hp: 10000,
		Mp: 5000,
	}

	if err := session.WriteResponse(10003, 5, resp); err != nil {
		logger.Error("failed to send revive response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// ChangeRoomHandler 处理切换房间请求
func ChangeRoomHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.ChangeRoomRequest)
	if !ok {
		logger.Error("invalid message type for change room")
		return
	}

	logger.Info("change room request received",
		logger.Int32("room_id", req.RoomId),
		logger.Int32("portal_id", req.PortalId),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证传送门，切换房间，加载新房间怪物
	resp := &dnfv1.ChangeRoomResponse{
		Error:     0,
		NewRoomId: req.RoomId,
		Position: &dnfv1.RolePosition{
			MapId:     req.RoomId,
			DungeonId: 0,
			X:         0.0,
			Y:         0.0,
			Z:         0.0,
		},
		Monsters: []*dnfv1.DungeonMonster{
			{
				MonsterId: 1003,
				Level:     50,
				X:         15.0,
				Y:         15.0,
				Z:         0.0,
				IsBoss:    false,
			},
		},
	}

	if err := session.WriteResponse(10003, 7, resp); err != nil {
		logger.Error("failed to send change room response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// MonsterKillNotifyHandler 发送怪物击杀通知
func MonsterKillNotify(session *network.Session, monsterId int32, killerUid int64, expGain int64, drops []*dnfv1.ItemReward) {
	notify := &dnfv1.MonsterKillNotify{
		MonsterId: monsterId,
		KillerUid: killerUid,
		ExpGain:   expGain,
		Drops:     drops,
	}

	if err := session.WriteResponse(10003, 100, notify); err != nil {
		logger.Error("failed to send monster kill notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// BossKillNotifyHandler 发送BOSS击杀通知
func BossKillNotify(session *network.Session, bossId int32, killerUid int64, totalExp int64, drops []*dnfv1.ItemReward) {
	notify := &dnfv1.BossKillNotify{
		BossId:    bossId,
		KillerUid: killerUid,
		TotalExp:  totalExp,
		Drops:     drops,
	}

	if err := session.WriteResponse(10003, 101, notify); err != nil {
		logger.Error("failed to send boss kill notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// DungeonCompleteNotifyHandler 发送副本完成通知
func DungeonCompleteNotify(session *network.Session, dungeonId int32, result *dnfv1.DungeonResult) {
	notify := &dnfv1.DungeonCompleteNotify{
		DungeonId: dungeonId,
		Result:    result,
	}

	if err := session.WriteResponse(10003, 102, notify); err != nil {
		logger.Error("failed to send dungeon complete notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
