package tests

import (
	"database/sql"
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

// TestTCPQueryFriendRank 查询好友排名(2026-09-06 第三十四轮):
// 建 A/B 两角色, 直插好友关系(A→B), B 等级更高 → A 在好友圈排第 2, total=2
func (s *RankTCPTestSuite) TestTCPQueryFriendRank() {
	// 建好友关系双方角色(独立 openid)
	ownerOpenid := fmt.Sprintf("frank_owner_%d", time.Now().UnixNano()%100000000)
	friendOpenid := fmt.Sprintf("frank_friend_%d", time.Now().UnixNano()%100000000)
	ownerGuid := s.createCharacter(ownerOpenid)
	friendGuid := s.createCharacter(friendOpenid)
	s.Greater(ownerGuid, float64(0), "owner charGuid should be positive")
	s.Greater(friendGuid, float64(0), "friend charGuid should be positive")

	// 好友等级提升到 10(DB 直改, 模拟高等级好友)
	db, err := sql.Open("mysql", testDBDSN)
	s.NoError(err)
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)
	_, err = db.Exec("UPDATE role SET level = 10 WHERE id = ?", uint64(friendGuid))
	s.NoError(err, "update friend level")

	// 直插好友关系 A→B
	now := time.Now().Unix()
	_, err = db.Exec("INSERT INTO friend (created_at, updated_at, row_status, role_id, friend_id, friend_name, intimacy, friend_group) VALUES (?, ?, 0, ?, ?, ?, 0, '')",
		now, now, uint64(ownerGuid), uint64(friendGuid), fmt.Sprintf("FR_%012d", time.Now().UnixNano()%1000000000000))
	s.NoError(err, "insert friend relation")
	defer db.Exec("DELETE FROM friend WHERE role_id = ?", uint64(ownerGuid))

	// TCP 绑定 owner 并查好友榜
	s.bindAndQueryRank(ownerGuid, "QUERY_FRIEND_RANK", 5, func(rr *dnfv1.RankResponse) {
		s.Equal(int32(2), rr.Total, "friend circle total should be 2 (owner + friend)")
		s.Equal(int32(2), rr.Rank, "owner (level 1) should rank 2 behind friend (level 10)")
	})
}

// createCharacter 通过 HTTP 建角并返回 charGuid
func (s *RankTCPTestSuite) createCharacter(openid string) float64 {
	token := s.LoginAs(openid)
	s.NotEmpty(token, "Login should return a token")
	createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": fmt.Sprintf("FR_%012d", time.Now().UnixNano()%1000000000000),
		"job":  1,
	})
	s.NoError(err)
	s.AssertSuccess(createResp)
	guid, ok := createResp["data"].(map[string]interface{})["charGuid"].(float64)
	s.True(ok, "created character should have charGuid")
	return guid
}

// bindAndQueryRank TCP 绑定角色并查询指定榜, 断言响应字段
func (s *RankTCPTestSuite) bindAndQueryRank(charGuid float64, cmd string, respCmd uint16, assert func(*dnfv1.RankResponse)) {
	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	s.NotNil(conn)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	defer conn.Close()
	s.socket = conn

	// SELECT_CHARACTER 绑定角色
	selectJSON, _ := json.Marshal(map[string]interface{}{"uid": charGuid})
	selMsg := append([]byte("SELECT_CHARACTER:"), selectJSON...)
	s.NoError(s.sendTCP(selMsg), "send SELECT_CHARACTER")
	selResp, err := s.recvTCP()
	s.NoError(err)
	s.NotNil(selResp)
	selModule, selCmd, _ := parseTCPResponse(selResp)
	s.Equal(uint16(10000), selModule, "select response module should be 10000")
	s.Equal(uint16(7), selCmd, "select response cmd should be 7")

	// 发送榜单查询
	payloadJSON, _ := json.Marshal(map[string]interface{}{"rank_type": 1})
	msg := append([]byte(cmd+":"), payloadJSON...)
	s.NoError(s.sendTCP(msg), "send "+cmd)
	body, err := s.recvTCP()
	s.NoError(err)
	s.NotNil(body)
	module, rcmd, payloadBytes := parseTCPResponse(body)
	s.Equal(uint16(10501), module, "response module should be 10501")
	s.Equal(respCmd, rcmd, "response cmd should be %d", respCmd)
	rr := &dnfv1.RankResponse{}
	s.NoError(proto.Unmarshal(payloadBytes, rr), "unmarshal RankResponse")
	s.Equal(int32(1), rr.RankType, "rank_type should be echoed as 1")
	assert(rr)
	fmt.Printf("%s response rank=%d total=%d\n", cmd, rr.Rank, rr.Total)
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

// TestTCPQueryMyTeamRank 队伍榜(2026-09-06 第四十三轮实化):
// DB 直插建队(文本命令建队走 protobuf handler, 不走 textExtras) → 绑定 → QUERY_MY_TEAM_RANK
// 应返回队伍平均等级榜真实位置 rank≥1; 无队伍角色 rank=0
func (s *RankTCPTestSuite) TestTCPQueryMyTeamRank() {
	uid := time.Now().UnixNano()
	guid := s.createCharacter(fmt.Sprintf("test_rank_team_%d", uid))
	guid2 := s.createCharacter(fmt.Sprintf("test_rank_noteam_%d", uid))

	// DB 直插队伍
	db, err := sql.Open("mysql", testDBDSN)
	s.NoError(err)
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	res, err := db.Exec("INSERT INTO t_party (leader_id, name, max_members, status, create_time, update_time, row_status) VALUES (?, '测试队伍', 4, 0, 1, 1, 'NORMAL')", uint64(guid))
	s.NoError(err, "insert party")
	partyID, err := res.LastInsertId()
	s.NoError(err)
	s.Greater(partyID, int64(0))
	_, err = db.Exec("INSERT INTO t_party_member (party_id, role_id, player_id, team_type, status, join_time, row_status) VALUES (?, ?, 0, 0, 0, 1, 'NORMAL')", partyID, uint64(guid))
	s.NoError(err, "insert party member")
	defer db.Exec("DELETE FROM t_party_member WHERE party_id = ?", partyID)
	defer db.Exec("DELETE FROM t_party WHERE party_id = ?", partyID)

	// 有队伍: rank≥1 且 total≥1
	s.bindAndQueryRank(guid, "QUERY_MY_TEAM_RANK", 7, func(rr *dnfv1.RankResponse) {
		s.GreaterOrEqual(rr.Rank, int32(1), "member with party should have rank >= 1")
		s.GreaterOrEqual(rr.Total, int32(1), "total should be >= 1")
	})

	// 无队伍: rank=0(不在榜)
	s.bindAndQueryRank(guid2, "QUERY_MY_TEAM_RANK", 7, func(rr *dnfv1.RankResponse) {
		s.Equal(int32(0), rr.Rank, "role without party should have rank 0")
	})
	fmt.Printf("team rank verified (party_id=%d)\n", partyID)
}
