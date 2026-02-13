package network

import (
	"net"
	"sync"
	"sync/atomic"

	"google.golang.org/protobuf/proto"
)

// Session 网络会话 (替代 MINA IoSession)
type Session struct {
	id         int64
	conn       net.Conn
	attrs      map[string]interface{}
	attrsMu    sync.RWMutex
	closed     atomic.Bool
	roleID     uint64
	matchingID uint64
}

// NewSession 创建新会话
func NewSession(conn net.Conn, id int64) *Session {
	return &Session{
		id:    id,
		conn:  conn,
		attrs: make(map[string]interface{}),
	}
}

// RoleID 返回角色ID
func (s *Session) RoleID() uint64 {
	return s.roleID
}

// SetRoleID 设置角色ID
func (s *Session) SetRoleID(roleID uint64) {
	s.roleID = roleID
}

// MatchingID 返回匹配ID
func (s *Session) MatchingID() uint64 {
	return s.matchingID
}

// SetMatchingID 设置匹配ID
func (s *Session) SetMatchingID(matchingID uint64) {
	s.matchingID = matchingID
}

// ID 返回会话ID
func (s *Session) ID() int64 {
	return s.id
}

// RemoteAddr 返回远程地址
func (s *Session) RemoteAddr() string {
	if s.conn != nil {
		return s.conn.RemoteAddr().String()
	}
	return ""
}

// LocalAddr 返回本地地址
func (s *Session) LocalAddr() string {
	if s.conn != nil {
		return s.conn.LocalAddr().String()
	}
	return ""
}

// IsConnected 检查是否连接
func (s *Session) IsConnected() bool {
	return !s.closed.Load()
}

// Close 关闭会话
func (s *Session) Close() error {
	if s.closed.CompareAndSwap(false, true) {
		if s.conn != nil {
			return s.conn.Close()
		}
	}
	return nil
}

// Write 发送数据
func (s *Session) Write(data []byte) error {
	if !s.IsConnected() {
		return net.ErrClosed
	}

	_, err := s.conn.Write(data)
	return err
}

// Encoder 编码器接口
type Encoder interface {
	EncodeWithMeta(module, cmd uint16, msg proto.Message) ([]byte, error)
}

// encoderInstance 全局编码器实例
var encoderInstance Encoder

// SetEncoderInstance 设置全局编码器
func SetEncoderInstance(encoder Encoder) {
	encoderInstance = encoder
}

// WriteResponse 发送protobuf响应
func (s *Session) WriteResponse(module, cmd uint16, msg interface{}) error {
	if encoderInstance == nil {
		return net.ErrClosed
	}

	// 类型断言为 proto.Message
	protoMsg, ok := msg.(proto.Message)
	if !ok {
		return net.ErrClosed
	}

	data, err := encoderInstance.EncodeWithMeta(module, cmd, protoMsg)
	if err != nil {
		return err
	}

	return s.Write(data)
}

// SetAttr 设置属性
func (s *Session) SetAttr(key string, value interface{}) {
	s.attrsMu.Lock()
	defer s.attrsMu.Unlock()
	s.attrs[key] = value
}

// GetAttr 获取属性
func (s *Session) GetAttr(key string) (interface{}, bool) {
	s.attrsMu.RLock()
	defer s.attrsMu.RUnlock()
	val, ok := s.attrs[key]
	return val, ok
}

// RemoveAttr 删除属性
func (s *Session) RemoveAttr(key string) {
	s.attrsMu.Lock()
	defer s.attrsMu.Unlock()
	delete(s.attrs, key)
}

// SessionManager 会话管理器 (替代 MINA SessionManager)
type SessionManager struct {
	sessions map[int64]*Session
	mu       sync.RWMutex
	nextID   atomic.Int64
}

// NewSessionManager 创建会话管理器
func NewSessionManager() *SessionManager {
	return &SessionManager{
		sessions: make(map[int64]*Session),
	}
}

// NextSessionID 获取下一个会话ID
func (m *SessionManager) NextSessionID() int64 {
	return m.nextID.Add(1)
}

// Add 添加会话
func (m *SessionManager) Add(session *Session) {
	m.mu.Lock()
	defer m.mu.Unlock()
	m.sessions[session.ID()] = session
}

// Remove 移除会话
func (m *SessionManager) Remove(sessionID int64) {
	m.mu.Lock()
	defer m.mu.Unlock()
	delete(m.sessions, sessionID)
}

// Get 获取会话
func (m *SessionManager) Get(sessionID int64) (*Session, bool) {
	m.mu.RLock()
	defer m.mu.RUnlock()
	session, ok := m.sessions[sessionID]
	return session, ok
}

// GetAll 获取所有会话
func (m *SessionManager) GetAll() []*Session {
	m.mu.RLock()
	defer m.mu.RUnlock()

	sessions := make([]*Session, 0, len(m.sessions))
	for _, s := range m.sessions {
		sessions = append(sessions, s)
	}
	return sessions
}

// Count 获取会话数量
func (m *SessionManager) Count() int {
	m.mu.RLock()
	defer m.mu.RUnlock()
	return len(m.sessions)
}

// CloseAll 关闭所有会话
func (m *SessionManager) CloseAll() {
	m.mu.Lock()
	sessions := make([]*Session, 0, len(m.sessions))
	for _, s := range m.sessions {
		sessions = append(sessions, s)
	}
	m.mu.Unlock()

	for _, s := range sessions {
		s.Close()
	}
}

// Broadcast 广播消息给所有会话
func (m *SessionManager) Broadcast(data []byte) {
	sessions := m.GetAll()
	for _, s := range sessions {
		go func(session *Session) {
			if err := session.Write(data); err != nil {
				// 忽略发送错误
			}
		}(s)
	}
}
