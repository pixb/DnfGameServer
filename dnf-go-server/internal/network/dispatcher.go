package network

import (
	"reflect"
	"runtime/debug"
	"sync"

	"dnf-go-server/internal/utils/logger"
	"google.golang.org/protobuf/proto"
)

// MessageHandler 消息处理器函数类型
type MessageHandler func(session *Session, msg proto.Message)

// MessageDispatcher 消息分发器接口实现
type MessageDispatcher struct {
	// 处理器注册表: key = module<<16 | cmd
	handlers map[uint32]MessageHandler
	mu       sync.RWMutex

	// 中间件链
	middlewares []MiddlewareFunc
}

// MiddlewareFunc 中间件函数类型
type MiddlewareFunc func(next MessageHandler) MessageHandler

// NewMessageDispatcher 创建消息分发器
func NewMessageDispatcher() *MessageDispatcher {
	return &MessageDispatcher{
		handlers:    make(map[uint32]MessageHandler),
		middlewares: make([]MiddlewareFunc, 0),
	}
}

// RegisterHandler 注册消息处理器
// module: 消息模块
// cmd: 消息命令
// handler: 处理函数
func (d *MessageDispatcher) RegisterHandler(module, cmd uint16, handler MessageHandler) {
	key := MessageMeta{Module: module, Cmd: cmd}.MessageKey()

	d.mu.Lock()
	defer d.mu.Unlock()

	d.handlers[key] = handler

	logger.Info("registered message handler",
		logger.Uint16("module", module),
		logger.Uint16("cmd", cmd),
	)
}

// RegisterHandlerFunc 使用函数注册处理器(简化版)
func (d *MessageDispatcher) RegisterHandlerFunc(module, cmd uint16, handler func(*Session, proto.Message)) {
	d.RegisterHandler(module, cmd, MessageHandler(handler))
}

// Use 添加中间件
func (d *MessageDispatcher) Use(middleware MiddlewareFunc) {
	d.middlewares = append(d.middlewares, middleware)
}

// Dispatch 分发消息 - 实现 Dispatcher 接口
func (d *MessageDispatcher) Dispatch(session *Session, message interface{}) {
	// 提取packet和meta
	var packet *ProtocolPacket
	var ok bool

	if packet, ok = message.(*ProtocolPacket); !ok {
		logger.Error("invalid message type, expected *ProtocolPacket",
			logger.String("type", reflect.TypeOf(message).String()),
		)
		return
	}

	meta := packet.Meta
	msg := packet.Message

	// 查找处理器
	key := meta.MessageKey()

	d.mu.RLock()
	handler, exists := d.handlers[key]
	d.mu.RUnlock()

	if !exists {
		logger.Warn("no handler found for message",
			logger.Uint16("module", meta.Module),
			logger.Uint16("cmd", meta.Cmd),
			logger.Int64("session_id", session.ID()),
		)
		return
	}

	// 应用中间件链
	finalHandler := handler
	for i := len(d.middlewares) - 1; i >= 0; i-- {
		finalHandler = d.middlewares[i](finalHandler)
	}

	// 执行处理(带panic恢复)
	go func() {
		defer func() {
			if r := recover(); r != nil {
				logger.Error("panic in message handler",
					logger.Uint16("module", meta.Module),
					logger.Uint16("cmd", meta.Cmd),
					logger.Int64("session_id", session.ID()),
					logger.Any("panic", r),
					logger.String("stack", string(debug.Stack())),
				)
			}
		}()

		finalHandler(session, msg)
	}()
}

// RegisterAllHandlers 批量注册处理器
func (d *MessageDispatcher) RegisterAllHandlers(registry *HandlerRegistry) {
	registry.mu.RLock()
	defer registry.mu.RUnlock()

	for key, handler := range registry.handlers {
		module := uint16(key >> 16)
		cmd := uint16(key & 0xFFFF)
		d.handlers[key] = handler

		logger.Info("registered handler from registry",
			logger.Uint16("module", module),
			logger.Uint16("cmd", cmd),
		)
	}
}

// HandlerRegistry 处理器注册表(用于批量注册)
type HandlerRegistry struct {
	handlers map[uint32]MessageHandler
	mu       sync.RWMutex
}

// NewHandlerRegistry 创建处理器注册表
func NewHandlerRegistry() *HandlerRegistry {
	return &HandlerRegistry{
		handlers: make(map[uint32]MessageHandler),
	}
}

// Register 注册处理器到注册表
func (r *HandlerRegistry) Register(module, cmd uint16, handler MessageHandler) *HandlerRegistry {
	key := MessageMeta{Module: module, Cmd: cmd}.MessageKey()

	r.mu.Lock()
	defer r.mu.Unlock()

	r.handlers[key] = handler
	return r
}

// GetHandlerCount 获取处理器数量
func (d *MessageDispatcher) GetHandlerCount() int {
	d.mu.RLock()
	defer d.mu.RUnlock()
	return len(d.handlers)
}

// HasHandler 检查是否有处理器
func (d *MessageDispatcher) HasHandler(module, cmd uint16) bool {
	key := MessageMeta{Module: module, Cmd: cmd}.MessageKey()

	d.mu.RLock()
	defer d.mu.RUnlock()

	_, exists := d.handlers[key]
	return exists
}

// RemoveHandler 移除处理器
func (d *MessageDispatcher) RemoveHandler(module, cmd uint16) {
	key := MessageMeta{Module: module, Cmd: cmd}.MessageKey()

	d.mu.Lock()
	defer d.mu.Unlock()

	delete(d.handlers, key)

	logger.Info("removed message handler",
		logger.Uint16("module", module),
		logger.Uint16("cmd", cmd),
	)
}

// ClearHandlers 清除所有处理器
func (d *MessageDispatcher) ClearHandlers() {
	d.mu.Lock()
	defer d.mu.Unlock()

	d.handlers = make(map[uint32]MessageHandler)

	logger.Info("cleared all message handlers")
}

// LoggingMiddleware 日志中间件
func LoggingMiddleware(next MessageHandler) MessageHandler {
	return func(session *Session, msg proto.Message) {
		logger.Debug("processing message",
			logger.Int64("session_id", session.ID()),
			logger.String("msg_type", reflect.TypeOf(msg).String()),
		)
		next(session, msg)
	}
}

// RecoveryMiddleware 恢复中间件
func RecoveryMiddleware(next MessageHandler) MessageHandler {
	return func(session *Session, msg proto.Message) {
		defer func() {
			if r := recover(); r != nil {
				logger.Error("panic recovered in handler",
					logger.Int64("session_id", session.ID()),
					logger.Any("panic", r),
					logger.String("stack", string(debug.Stack())),
				)
			}
		}()
		next(session, msg)
	}
}

// SessionValidationMiddleware 会话验证中间件
func SessionValidationMiddleware(requiredAuth bool) MiddlewareFunc {
	return func(next MessageHandler) MessageHandler {
		return func(session *Session, msg proto.Message) {
			if requiredAuth {
				// 检查会话是否已认证
				if _, ok := session.GetAttr("uid"); !ok {
					logger.Warn("unauthorized session",
						logger.Int64("session_id", session.ID()),
					)
					// 可以在这里发送错误响应
					return
				}
			}
			next(session, msg)
		}
	}
}

// DefaultDispatcher 创建带默认中间件的分发器
func DefaultDispatcher() *MessageDispatcher {
	d := NewMessageDispatcher()
	d.Use(RecoveryMiddleware)
	d.Use(LoggingMiddleware)
	return d
}
