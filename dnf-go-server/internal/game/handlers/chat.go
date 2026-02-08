package handlers

import (
	"time"

	"dnf-go-server/internal/network"
	"dnf-go-server/internal/utils/logger"
	dnfv1 "dnf-go-server/proto/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// SendChatHandler 处理发送聊天请求
func SendChatHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.SendChatRequest)
	if !ok {
		logger.Error("invalid message type for send chat")
		return
	}

	logger.Info("send chat request received",
		logger.String("channel", req.Channel.String()),
		logger.String("content", req.Content),
		logger.String("target_name", req.TargetName),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证聊天内容，根据频道广播消息
	resp := &dnfv1.SendChatResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10004, 1, resp); err != nil {
		logger.Error("failed to send chat response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	// 广播聊天消息给同一频道的玩家
	// TODO: 实现频道广播逻辑
	uid, _ := session.GetAttr("uid")
	username, _ := session.GetAttr("username")

	notify := &dnfv1.ReceiveChatNotify{
		Message: &dnfv1.ChatMessage{
			Id:          time.Now().UnixNano(),
			Channel:     req.Channel,
			SenderUid:   uid.(int64),
			SenderName:  username.(string),
			SenderJob:   1,
			SenderLevel: 50,
			Content:     req.Content,
			Timestamp:   time.Now().Unix(),
		},
	}

	// 发送给自己确认
	if err := session.WriteResponse(10004, 100, notify); err != nil {
		logger.Error("failed to send chat notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// GetChatHistoryHandler 处理获取聊天历史请求
func GetChatHistoryHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.GetChatHistoryRequest)
	if !ok {
		logger.Error("invalid message type for get chat history")
		return
	}

	logger.Info("get chat history request received",
		logger.String("channel", req.Channel.String()),
		logger.Int64("before_id", req.BeforeId),
		logger.Int32("limit", req.Limit),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 从数据库或缓存加载聊天历史
	resp := &dnfv1.GetChatHistoryResponse{
		Error: 0,
		Messages: []*dnfv1.ChatMessage{
			{
				Id:          1,
				Channel:     req.Channel,
				SenderUid:   10001,
				SenderName:  "玩家1",
				SenderJob:   1,
				SenderLevel: 50,
				Content:     "大家好！",
				Timestamp:   time.Now().Unix() - 3600,
			},
			{
				Id:          2,
				Channel:     req.Channel,
				SenderUid:   10002,
				SenderName:  "玩家2",
				SenderJob:   2,
				SenderLevel: 45,
				Content:     "有人组队刷副本吗？",
				Timestamp:   time.Now().Unix() - 1800,
			},
		},
		HasMore: false,
	}

	if err := session.WriteResponse(10004, 3, resp); err != nil {
		logger.Error("failed to send chat history response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// GetFriendListHandler 处理获取好友列表请求
func GetFriendListHandler(session *network.Session, msg proto.Message) {
	_, ok := msg.(*dnfv1.GetFriendListRequest)
	if !ok {
		logger.Error("invalid message type for get friend list")
		return
	}

	logger.Info("get friend list request received",
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 从数据库加载好友列表
	resp := &dnfv1.GetFriendListResponse{
		Error: 0,
		Friends: []*dnfv1.FriendInfo{
			{
				Uid:           10001,
				Name:          "好友1",
				Job:           1,
				Level:         50,
				Online:        true,
				LastLoginTime: time.Now().Unix(),
				Intimacy:      100,
			},
			{
				Uid:           10002,
				Name:          "好友2",
				Job:           2,
				Level:         45,
				Online:        false,
				LastLoginTime: time.Now().Unix() - 86400,
				Intimacy:      50,
			},
		},
	}

	if err := session.WriteResponse(10004, 201, resp); err != nil {
		logger.Error("failed to send friend list response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// AddFriendHandler 处理添加好友请求
func AddFriendHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.AddFriendRequest)
	if !ok {
		logger.Error("invalid message type for add friend")
		return
	}

	logger.Info("add friend request received",
		logger.String("target_name", req.TargetName),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 查找目标玩家，发送好友申请
	resp := &dnfv1.AddFriendResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10004, 203, resp); err != nil {
		logger.Error("failed to send add friend response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}

	// 发送好友申请通知给目标玩家
	uid, _ := session.GetAttr("uid")
	notify := &dnfv1.FriendRequestNotify{
		RequesterUid:   uid.(int64),
		RequesterName:  "请求者",
		RequesterJob:   1,
		RequesterLevel: 50,
	}

	if err := session.WriteResponse(10004, 204, notify); err != nil {
		logger.Error("failed to send friend request notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// RemoveFriendHandler 处理删除好友请求
func RemoveFriendHandler(session *network.Session, msg proto.Message) {
	req, ok := msg.(*dnfv1.RemoveFriendRequest)
	if !ok {
		logger.Error("invalid message type for remove friend")
		return
	}

	logger.Info("remove friend request received",
		logger.Int64("friend_uid", req.FriendUid),
		logger.Int64("session_id", session.ID()),
	)

	// TODO: 验证好友关系，删除好友
	resp := &dnfv1.RemoveFriendResponse{
		Error: 0,
	}

	if err := session.WriteResponse(10004, 207, resp); err != nil {
		logger.Error("failed to send remove friend response",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// SystemMessageHandler 发送系统消息
func SystemMessageHandler(session *network.Session, msgType int32, content string, params []string) {
	msg := &dnfv1.SystemMessage{
		MsgType: msgType,
		Content: content,
		Params:  params,
	}

	if err := session.WriteResponse(10004, 101, msg); err != nil {
		logger.Error("failed to send system message",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}

// BroadcastNotifyHandler 发送广播通知
func BroadcastNotifyHandler(session *network.Session, broadcastType int32, content string, priority int32) {
	notify := &dnfv1.BroadcastNotify{
		BroadcastType: broadcastType,
		Content:       content,
		Priority:      priority,
	}

	if err := session.WriteResponse(10004, 102, notify); err != nil {
		logger.Error("failed to send broadcast notify",
			logger.ErrorField(err),
			logger.Int64("session_id", session.ID()),
		)
	}
}
