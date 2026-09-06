package tests

import (
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
	"testing"
	"time"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/stretchr/testify/suite"
	"google.golang.org/protobuf/proto"
)

// RankTCPTestSuite 排名TCP测试套件(2026-09-06 第三十轮):
// 验证 rank 响应携带真实数据(RankResponse 替代 Empty)
type RankTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	serverHost string
	serverPort int
}

func TestRankTCPTestSuite(t *testing.T) {
	suite.Run(t, new(RankTCPTestSuite))
}

func (s *RankTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = tcpTestPort()
}

func (s *RankTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// TestTCPQueryMyRank 查询我的排名(2026-09-06 第三十一轮增强):
// 建角 → TCP SELECT_CHARACTER 绑定角色 → QUERY_MY_RANK 应返回真实排名 rank≥1
func (s *RankTCPTestSuite) TestTCPQueryMyRank() {
	// 建一个角色保证榜单有数据(独立 openid)
	openid := "rank_tcp_openid"
	token := s.LoginAs(openid)
	s.NotEmpty(token, "Login should return a token")
	createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": fmt.Sprintf("RT_%012d", time.Now().UnixNano()%1000000000000),
		"job":  1,
	})
	s.NoError(err)
	s.AssertSuccess(createResp)
	charGuid, ok := createResp["data"].(map[string]interface{})["charGuid"].(float64)
	s.True(ok, "created character should have charGuid")
	s.Greater(charGuid, float64(0), "charGuid should be positive")

	// 建立 TCP 连接
	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	s.NotNil(conn)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	defer conn.Close()
	s.socket = conn

	// 发送 SELECT_CHARACTER 绑定角色(uid=charGuid)
	selectPayload := map[string]interface{}{"uid": charGuid}
	selectJSON, _ := json.Marshal(selectPayload)
	selectMsg := append([]byte("SELECT_CHARACTER:"), selectJSON...)
	s.NoError(s.sendTCP(selectMsg), "send SELECT_CHARACTER")
	// 读选角响应(module=10000 cmd=7)
	selResp, err := s.recvTCP()
	s.NoError(err)
	s.NotNil(selResp)
	selModule, selCmd, _ := parseTCPResponse(selResp)
	s.Equal(uint16(10000), selModule, "select response module should be 10000")
	s.Equal(uint16(7), selCmd, "select response cmd should be 7")

	// 发送 QUERY_MY_RANK(等级榜 rank_type=1)
	payload := map[string]interface{}{"rank_type": 1}
	payloadJSON, _ := json.Marshal(payload)
	msg := append([]byte("QUERY_MY_RANK:"), payloadJSON...)
	s.NoError(s.sendTCP(msg), "send QUERY_MY_RANK")

	// 接收响应并解析 RankResponse
	body, err := s.recvTCP()
	s.NoError(err)
	s.NotNil(body)
	module, cmd, payloadBytes := parseTCPResponse(body)
	s.Equal(uint16(10501), module, "response module should be 10501")
	s.Equal(uint16(1), cmd, "query my rank response cmd should be 1")
	rr := &dnfv1.RankResponse{}
	s.NoError(proto.Unmarshal(payloadBytes, rr), "unmarshal RankResponse")
	s.Equal(int32(1), rr.RankType, "rank_type should be echoed as 1")
	s.GreaterOrEqual(rr.Total, int32(1), "total should be >= 1 (roster has roles)")
	s.GreaterOrEqual(rr.Rank, int32(1), "bound role should have real rank >= 1")
	fmt.Printf("Rank response rank_type=%d rank=%d total=%d\n", rr.RankType, rr.Rank, rr.Total)
}

// sendTCP 发送文本命令(2字节大端长度 + 消息体)
func (s *RankTCPTestSuite) sendTCP(message []byte) error {
	lengthBytes := make([]byte, 2)
	binary.BigEndian.PutUint16(lengthBytes, uint16(len(message)))
	_, err := s.socket.Write(append(lengthBytes, message...))
	return err
}

// recvTCP 接收一条响应帧(不含长度字段, 含 4 字节 module/cmd 头)
func (s *RankTCPTestSuite) recvTCP() ([]byte, error) {
	lenBuf := make([]byte, 2)
	if _, err := s.socket.Read(lenBuf); err != nil {
		return nil, err
	}
	bodyLen := binary.BigEndian.Uint16(lenBuf)
	if bodyLen == 0 {
		return nil, nil
	}
	body := make([]byte, bodyLen)
	_, err := s.socket.Read(body)
	return body, err
}
