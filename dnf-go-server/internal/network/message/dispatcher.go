package message

import (
	"fmt"
	"reflect"
	"sync"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
)

// Message 消息接口
type Message interface {
	GetMsgID() int32
}

// Handler 消息处理器函数类型
type Handler func(session *network.Session, msg Message)

// Dispatcher 消息分发器
type Dispatcher struct {
	handlers map[int32]Handler
	mu       sync.RWMutex
}

// NewDispatcher 创建消息分发器
func NewDispatcher() *Dispatcher {
	return &Dispatcher{
		handlers: make(map[int32]Handler),
	}
}

// Register 注册消息处理器
func (d *Dispatcher) Register(msgID int32, handler Handler) {
	d.mu.Lock()
	defer d.mu.Unlock()

	if _, exists := d.handlers[msgID]; exists {
		logger.Warn("handler already registered, overwriting",
			logger.Int("msg_id", int(msgID)),
		)
	}

	d.handlers[msgID] = handler
	logger.Info("message handler registered",
		logger.Int("msg_id", int(msgID)),
	)
}

// Dispatch 分发消息
func (d *Dispatcher) Dispatch(session *network.Session, message interface{}) {
	defer func() {
		if r := recover(); r != nil {
			logger.Error("panic in message dispatch",
				logger.Int64("session_id", session.ID()),
				logger.String("panic", fmt.Sprintf("%v", r)),
			)
		}
	}()

	msg, ok := message.(Message)
	if !ok {
		logger.Error("message does not implement Message interface",
			logger.String("type", reflect.TypeOf(message).String()),
		)
		return
	}

	msgID := msg.GetMsgID()

	d.mu.RLock()
	handler, exists := d.handlers[msgID]
	d.mu.RUnlock()

	if !exists {
		logger.Warn("no handler for message",
			logger.Int("msg_id", int(msgID)),
			logger.Int64("session_id", session.ID()),
		)
		return
	}

	// 执行处理器
	handler(session, msg)
}

// GetHandlerCount 获取处理器数量
func (d *Dispatcher) GetHandlerCount() int {
	d.mu.RLock()
	defer d.mu.RUnlock()
	return len(d.handlers)
}

// BaseMessage 基础消息结构
type BaseMessage struct {
	MsgID int32
}

// GetMsgID 获取消息ID
func (m *BaseMessage) GetMsgID() int32 {
	return m.MsgID
}

// MessageRegistry 消息注册表 (替代 Java MessageFactory)
type MessageRegistry struct {
	factories map[int32]func() Message
	mu        sync.RWMutex
}

// NewMessageRegistry 创建消息注册表
func NewMessageRegistry() *MessageRegistry {
	return &MessageRegistry{
		factories: make(map[int32]func() Message),
	}
}

// Register 注册消息工厂
func (r *MessageRegistry) Register(msgID int32, factory func() Message) {
	r.mu.Lock()
	defer r.mu.Unlock()
	r.factories[msgID] = factory
}

// Create 创建消息实例
func (r *MessageRegistry) Create(msgID int32) (Message, error) {
	r.mu.RLock()
	factory, exists := r.factories[msgID]
	r.mu.RUnlock()

	if !exists {
		return nil, fmt.Errorf("message factory not found for msg_id: %d", msgID)
	}

	return factory(), nil
}

// GlobalRegistry 全局消息注册表
var GlobalRegistry = NewMessageRegistry()
