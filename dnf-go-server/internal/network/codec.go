package network

import (
	"bufio"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"io"
	"reflect"
	"strings"
	"sync"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	"google.golang.org/protobuf/encoding/protojson"
	"google.golang.org/protobuf/proto"
)

// MessageMeta 消息元数据 (对应Java的@MessageMeta注解)
type MessageMeta struct {
	Module uint16 // 消息模块
	Cmd    uint16 // 消息命令
}

// MessageKey 生成消息唯一键
func (m MessageMeta) MessageKey() uint32 {
	return (uint32(m.Module) << 16) | uint32(m.Cmd)
}

// ProtoCodec Protobuf消息编解码器
type ProtoCodec struct {
	// 消息注册表: key = module<<16 | cmd
	msgRegistry  map[uint32]func() proto.Message
	typeRegistry map[reflect.Type]MessageMeta
	mu           sync.RWMutex

	// 长度字段大小 (默认2字节)
	LengthFieldSize int
}

// NewProtoCodec 创建Proto编解码器
func NewProtoCodec() *ProtoCodec {
	return &ProtoCodec{
		msgRegistry:     make(map[uint32]func() proto.Message),
		typeRegistry:    make(map[reflect.Type]MessageMeta),
		LengthFieldSize: 2,
	}
}

// RegisterMessage 注册消息类型
// module: 消息模块 (对应Java的module)
// cmd: 消息命令 (对应Java的cmd)
// factory: 消息工厂函数
func (c *ProtoCodec) RegisterMessage(module, cmd uint16, factory func() proto.Message) {
	c.mu.Lock()
	defer c.mu.Unlock()

	key := MessageMeta{Module: module, Cmd: cmd}.MessageKey()
	c.msgRegistry[key] = factory

	// 记录类型到meta的映射
	msg := factory()
	c.typeRegistry[reflect.TypeOf(msg)] = MessageMeta{Module: module, Cmd: cmd}
}

// GetMessageMeta 获取消息元数据
func (c *ProtoCodec) GetMessageMeta(msg proto.Message) (MessageMeta, bool) {
	c.mu.RLock()
	defer c.mu.RUnlock()

	msgType := reflect.TypeOf(msg)
	meta, ok := c.typeRegistry[msgType]
	return meta, ok
}

// ProtocolPacket 协议数据包
type ProtocolPacket struct {
	Meta    MessageMeta
	Message proto.Message
	// TextExtras 文本命令附加字段(测试辅助):proto 未定义但客户端 JSON 携带的
	// 字段(如 ITEM_RENAME:name、BAG_EXPAND:slots),由 decodeTextBody 提取。
	TextExtras map[string]interface{}
}

// Decode 解码消息
// 协议格式: [2字节长度][2字节module][2字节cmd][protobuf消息体]
// 兼容文本格式: [2字节长度]"COMMAND:JSON"（测试辅助）。
// 判别方式: 二进制消息的 module/cmd 一定在注册表中;文本消息则按冒号分割命令名。
func (c *ProtoCodec) Decode(reader io.Reader) (interface{}, error) {
	// 创建带缓冲的reader
	bufReader, ok := reader.(*bufio.Reader)
	if !ok {
		bufReader = bufio.NewReader(reader)
	}

	// 读取长度字段
	lengthBuf := make([]byte, c.LengthFieldSize)
	if _, err := io.ReadFull(bufReader, lengthBuf); err != nil {
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

	if length <= 0 {
		return nil, fmt.Errorf("invalid message length: %d", length)
	}

	// 读取消息体
	body := make([]byte, length)
	if _, err := io.ReadFull(bufReader, body); err != nil {
		return nil, err
	}

	// 判别协议类型: 二进制消息头(module+cmd)一定已注册
	if length >= 4 {
		module := binary.BigEndian.Uint16(body[0:2])
		cmd := binary.BigEndian.Uint16(body[2:4])
		key := MessageMeta{Module: module, Cmd: cmd}.MessageKey()

		c.mu.RLock()
		factory, ok := c.msgRegistry[key]
		c.mu.RUnlock()

		if ok {
			msg := factory()
			if err := proto.Unmarshal(body[4:], msg); err != nil {
				return nil, fmt.Errorf("failed to unmarshal protobuf: %w", err)
			}
			return &ProtocolPacket{
				Meta:    MessageMeta{Module: module, Cmd: cmd},
				Message: msg,
			}, nil
		}
	}

	// 文本命令模式: "COMMAND:JSON"
	return c.decodeTextBody(body)
}

// decodeTextBody 解析文本命令消息体 (测试辅助)
// 格式: "COMMAND:JSON" 或 "COMMAND" (无 payload)
func (c *ProtoCodec) decodeTextBody(body []byte) (interface{}, error) {
	// 分割命令与JSON: "COMMAND:JSON"
	text := string(body)
	command := text
	payload := ""
	if idx := strings.Index(text, ":"); idx >= 0 {
		command = text[:idx]
		payload = text[idx+1:]
	}

	// 查找命令映射
	meta, ok := textCommandMeta[command]
	if !ok {
		return nil, fmt.Errorf("unknown text command: %s", command)
	}

	// 查找消息类型
	key := meta.MessageKey()

	c.mu.RLock()
	factory, ok := c.msgRegistry[key]
	c.mu.RUnlock()

	if !ok {
		return nil, fmt.Errorf("unknown message type for command %s: module=%d, cmd=%d", command, meta.Module, meta.Cmd)
	}

	msg := factory()
	var textExtras map[string]interface{}
	if len(payload) > 0 {
		// 提取 proto 未定义的附加字段(测试辅助: name/slots 等)
		var raw map[string]interface{}
		if json.Unmarshal([]byte(payload), &raw) == nil {
			textExtras = raw
		}
		// 文本命令为测试辅助模式,采用宽松解析:丢弃未知字段,
		// 使 mock JSON 中测试自定义的字段不会导致解析失败
		opts := protojson.UnmarshalOptions{DiscardUnknown: true}
		if err := opts.Unmarshal([]byte(payload), msg); err != nil {
			// payload 非 JSON(如 ENTER_GAME:NO_CHARACTER / LOAD_PLAYER_DATA:1),
			// 视为无参数命令,忽略 payload 继续分发
			logger.Warn("text command payload is not valid JSON, ignored",
				logger.String("command", command),
				logger.ErrorField(err),
			)
		}
	}

	return &ProtocolPacket{
		Meta:       meta,
		Message:    msg,
		TextExtras: textExtras,
	}, nil
}

// Encode 编码消息
func (c *ProtoCodec) Encode(message interface{}) ([]byte, error) {
	// 支持两种格式:
	// 1. *ProtocolPacket - 包含meta信息
	// 2. proto.Message - 自动查找meta

	var meta MessageMeta
	var msg proto.Message

	switch m := message.(type) {
	case *ProtocolPacket:
		meta = m.Meta
		msg = m.Message
	case proto.Message:
		msg = m
		var ok bool
		meta, ok = c.GetMessageMeta(msg)
		if !ok {
			return nil, fmt.Errorf("message type not registered: %T", msg)
		}
	default:
		return nil, fmt.Errorf("unsupported message type: %T", message)
	}

	// 序列化protobuf
	body, err := proto.Marshal(msg)
	if err != nil {
		return nil, fmt.Errorf("failed to marshal protobuf: %w", err)
	}

	// 计算总长度 (header + body)
	totalLength := 4 + len(body) // 4字节header + body

	// 构建消息
	result := make([]byte, c.LengthFieldSize+4+len(body))

	// 写入长度
	switch c.LengthFieldSize {
	case 2:
		binary.BigEndian.PutUint16(result, uint16(totalLength))
	case 4:
		binary.BigEndian.PutUint32(result, uint32(totalLength))
	}

	// 写入header (module + cmd)
	offset := c.LengthFieldSize
	binary.BigEndian.PutUint16(result[offset:], meta.Module)
	offset += 2
	binary.BigEndian.PutUint16(result[offset:], meta.Cmd)
	offset += 2

	// 写入body
	copy(result[offset:], body)

	return result, nil
}

// EncodeWithMeta 使用指定的meta编码消息
func (c *ProtoCodec) EncodeWithMeta(module, cmd uint16, msg proto.Message) ([]byte, error) {
	packet := &ProtocolPacket{
		Meta:    MessageMeta{Module: module, Cmd: cmd},
		Message: msg,
	}
	return c.Encode(packet)
}

// MustEncode 编码消息(忽略错误，仅用于测试)
func (c *ProtoCodec) MustEncode(message interface{}) []byte {
	data, err := c.Encode(message)
	if err != nil {
		panic(err)
	}
	return data
}
