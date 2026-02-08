package network

import (
	"bufio"
	"context"
	"encoding/binary"
	"fmt"
	"io"
	"net"
	"sync"
	"sync/atomic"
	"time"

	"dnf-go-server/internal/utils/logger"
)

// TCPServer TCP服务器 (替代 Apache MINA)
type TCPServer struct {
	addr     string
	listener net.Listener

	// 配置
	config *ServerConfig

	// 处理器
	handler ConnectionHandler

	// 会话管理
	sessionManager *SessionManager

	// 编解码器
	codec MessageCodec

	// 消息分发器
	dispatcher Dispatcher

	// 运行状态
	running atomic.Bool

	// 关闭信号
	ctx    context.Context
	cancel context.CancelFunc
	wg     sync.WaitGroup
}

// ServerConfig 服务器配置
type ServerConfig struct {
	Host            string
	Port            int
	Backlog         int
	ReadBufferSize  int
	WriteBufferSize int
	MaxConnections  int
	IdleTimeout     time.Duration
}

// ConnectionHandler 连接处理器接口 (替代 MINA IoHandler)
type ConnectionHandler interface {
	OnSessionCreated(session *Session)
	OnSessionOpened(session *Session)
	OnMessageReceived(session *Session, message interface{})
	OnSessionClosed(session *Session)
	OnExceptionCaught(session *Session, err error)
}

// MessageCodec 消息编解码器接口 (替代 ProtocolCodecFilter)
type MessageCodec interface {
	Decode(reader io.Reader) (interface{}, error)
	Encode(message interface{}) ([]byte, error)
}

// Dispatcher 消息分发器接口
type Dispatcher interface {
	Dispatch(session *Session, message interface{})
}

// DefaultServerConfig 返回默认配置
func DefaultServerConfig() *ServerConfig {
	return &ServerConfig{
		Host:            "0.0.0.0",
		Port:            9000,
		Backlog:         3000,
		ReadBufferSize:  2048,
		WriteBufferSize: 2048,
		MaxConnections:  10000,
		IdleTimeout:     30 * time.Second,
	}
}

// NewTCPServer 创建TCP服务器
func NewTCPServer(config *ServerConfig, handler ConnectionHandler) *TCPServer {
	if config == nil {
		config = DefaultServerConfig()
	}

	ctx, cancel := context.WithCancel(context.Background())

	return &TCPServer{
		config:         config,
		handler:        handler,
		sessionManager: NewSessionManager(),
		ctx:            ctx,
		cancel:         cancel,
	}
}

// SetCodec 设置编解码器
func (s *TCPServer) SetCodec(codec MessageCodec) {
	s.codec = codec
}

// SetDispatcher 设置消息分发器
func (s *TCPServer) SetDispatcher(dispatcher Dispatcher) {
	s.dispatcher = dispatcher
}

// Start 启动服务器
func (s *TCPServer) Start() error {
	addr := fmt.Sprintf("%s:%d", s.config.Host, s.config.Port)

	// 创建TCP listener
	listener, err := net.Listen("tcp", addr)
	if err != nil {
		return fmt.Errorf("failed to listen on %s: %w", addr, err)
	}

	s.listener = listener
	s.running.Store(true)

	logger.Info("TCP server started",
		logger.String("addr", addr),
		logger.Int("backlog", s.config.Backlog),
	)

	// 接受连接
	go s.acceptLoop()

	return nil
}

// acceptLoop 接受连接的循环
func (s *TCPServer) acceptLoop() {
	for s.running.Load() {
		conn, err := s.listener.Accept()
		if err != nil {
			if s.running.Load() {
				logger.Error("failed to accept connection", logger.ErrorField(err))
			}
			continue
		}

		s.wg.Add(1)
		go s.handleConnection(conn)
	}
}

// handleConnection 处理单个连接
func (s *TCPServer) handleConnection(conn net.Conn) {
	defer s.wg.Done()

	// 创建会话
	session := NewSession(conn, s.sessionManager.NextSessionID())
	s.sessionManager.Add(session)

	// 触发创建事件
	if s.handler != nil {
		s.handler.OnSessionCreated(session)
		s.handler.OnSessionOpened(session)
	}

	logger.Info("new client connected",
		logger.Int64("session_id", session.ID()),
		logger.String("remote_addr", conn.RemoteAddr().String()),
	)

	// 读取循环
	reader := bufio.NewReaderSize(conn, s.config.ReadBufferSize)

	for s.running.Load() && session.IsConnected() {
		// 设置读取超时
		if s.config.IdleTimeout > 0 {
			conn.SetReadDeadline(time.Now().Add(s.config.IdleTimeout))
		}

		// 解码消息
		if s.codec == nil {
			logger.Error("codec not set")
			break
		}

		message, err := s.codec.Decode(reader)
		if err != nil {
			if err != io.EOF && s.running.Load() {
				logger.Error("failed to decode message",
					logger.Int64("session_id", session.ID()),
					logger.ErrorField(err),
				)
				if s.handler != nil {
					s.handler.OnExceptionCaught(session, err)
				}
			}
			break
		}

		// 处理消息
		if s.dispatcher != nil {
			go s.dispatcher.Dispatch(session, message)
		} else if s.handler != nil {
			s.handler.OnMessageReceived(session, message)
		}
	}

	// 关闭会话
	s.closeSession(session)
}

// closeSession 关闭会话
func (s *TCPServer) closeSession(session *Session) {
	if !session.IsConnected() {
		return
	}

	session.Close()
	s.sessionManager.Remove(session.ID())

	if s.handler != nil {
		s.handler.OnSessionClosed(session)
	}

	logger.Info("client disconnected",
		logger.Int64("session_id", session.ID()),
	)
}

// Stop 停止服务器
func (s *TCPServer) Stop() error {
	if !s.running.Load() {
		return nil
	}

	s.running.Store(false)
	s.cancel()

	// 关闭listener
	if s.listener != nil {
		s.listener.Close()
	}

	// 关闭所有会话
	s.sessionManager.CloseAll()

	// 等待所有goroutine结束
	done := make(chan struct{})
	go func() {
		s.wg.Wait()
		close(done)
	}()

	select {
	case <-done:
		logger.Info("TCP server stopped gracefully")
	case <-time.After(10 * time.Second):
		logger.Warn("TCP server stop timeout")
	}

	return nil
}

// GetSessionManager 获取会话管理器
func (s *TCPServer) GetSessionManager() *SessionManager {
	return s.sessionManager
}

// BinaryCodec 二进制消息编解码器示例
type BinaryCodec struct {
	// 消息长度字段大小 (2/4字节)
	LengthFieldSize int
}

// Decode 解码消息
func (c *BinaryCodec) Decode(reader io.Reader) (interface{}, error) {
	// 读取长度字段
	lengthBuf := make([]byte, c.LengthFieldSize)
	if _, err := io.ReadFull(reader, lengthBuf); err != nil {
		return nil, err
	}

	var length int
	switch c.LengthFieldSize {
	case 2:
		length = int(binary.BigEndian.Uint16(lengthBuf))
	case 4:
		length = int(binary.BigEndian.Uint32(lengthBuf))
	default:
		return nil, fmt.Errorf("unsupported length field size: %d", c.LengthFieldSize)
	}

	// 读取消息体
	body := make([]byte, length)
	if _, err := io.ReadFull(reader, body); err != nil {
		return nil, err
	}

	return body, nil
}

// Encode 编码消息
func (c *BinaryCodec) Encode(message interface{}) ([]byte, error) {
	data, ok := message.([]byte)
	if !ok {
		return nil, fmt.Errorf("message must be []byte")
	}

	length := len(data)
	result := make([]byte, c.LengthFieldSize+length)

	switch c.LengthFieldSize {
	case 2:
		binary.BigEndian.PutUint16(result, uint16(length))
	case 4:
		binary.BigEndian.PutUint32(result, uint32(length))
	}

	copy(result[c.LengthFieldSize:], data)
	return result, nil
}
