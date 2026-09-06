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

// AuthTCPTestSuite TCP 登录认证测试套件(2026-09-06 第三十二轮):
// LoginHandler 实化 — 真实落库 account 表, 生成并刷新 authKey
type AuthTCPTestSuite struct {
	BaseTestSuite
	socket     net.Conn
	serverHost string
	serverPort int
}

func TestAuthTCPTestSuite(t *testing.T) {
	suite.Run(t, new(AuthTCPTestSuite))
}

func (s *AuthTCPTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	s.serverHost = "127.0.0.1"
	s.serverPort = tcpTestPort()
}

func (s *AuthTCPTestSuite) TearDownSuite() {
	if s.socket != nil {
		s.socket.Close()
	}
}

// TestTCPLoginCreatesAccount 新 openid 登录: 响应携带真实 authKey/accountKey, account 表落库
func (s *AuthTCPTestSuite) TestTCPLoginCreatesAccount() {
	openid := fmt.Sprintf("tcp_auth_%d", time.Now().UnixNano()%100000000)

	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	s.NotNil(conn)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	defer conn.Close()
	s.socket = conn

	resp := s.doLogin(openid)
	module, cmd, payload := parseTCPResponse(resp)
	s.Equal(uint16(10000), module, "login response module should be 10000")
	s.Equal(uint16(1), cmd, "login response cmd should be 1")
	lr := &dnfv1.LoginResponse{}
	s.NoError(proto.Unmarshal(payload, lr), "unmarshal LoginResponse")
	s.Equal(int32(0), lr.Error, "login should succeed")
	s.NotEmpty(lr.AuthKey, "authKey should be generated")
	s.Contains(lr.AuthKey, "_", "authKey should be unixnano_accountKey")
	s.NotEmpty(lr.AccountKey, "accountKey should be generated")
	s.NotZero(lr.ServerTime, "serverTime should be set")

	// DB 验证 account 表落库
	rowAuthKey, rowAccountKey, rowStatus := s.queryAccount(openid)
	s.Equal(lr.AuthKey, rowAuthKey, "account.auth_key should match response")
	s.Equal(lr.AccountKey, rowAccountKey, "account.account_key should match response")
	s.Equal(int32(0), rowStatus, "account.status should be normal(0)")
	fmt.Printf("TCP login created account: authKey=%s accountKey=%s\n", lr.AuthKey, lr.AccountKey)
}

// TestTCPLoginIdempotent 重复登录同一 openid: accountKey 不变, authKey 每次刷新
func (s *AuthTCPTestSuite) TestTCPLoginIdempotent() {
	openid := fmt.Sprintf("tcp_auth_dup_%d", time.Now().UnixNano()%100000000)

	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	s.NotNil(conn)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	defer conn.Close()
	s.socket = conn

	// 第一次登录
	_, _, p1 := parseTCPResponse(s.doLogin(openid))
	lr1 := &dnfv1.LoginResponse{}
	s.NoError(proto.Unmarshal(p1, lr1))
	s.Equal(int32(0), lr1.Error)

	// 第二次登录(同一连接)
	_, _, p2 := parseTCPResponse(s.doLogin(openid))
	lr2 := &dnfv1.LoginResponse{}
	s.NoError(proto.Unmarshal(p2, lr2))
	s.Equal(int32(0), lr2.Error)
	s.Equal(lr1.AccountKey, lr2.AccountKey, "accountKey should stay stable across logins")
	s.NotEqual(lr1.AuthKey, lr2.AuthKey, "authKey should be refreshed on each login")
	fmt.Printf("TCP repeat login: accountKey=%s (stable), authKey refreshed\n", lr2.AccountKey)
}

// TestTCPLoginEmptyOpenid 空 openid 登录应拒绝(error=1)
func (s *AuthTCPTestSuite) TestTCPLoginEmptyOpenid() {
	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	s.NotNil(conn)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	defer conn.Close()
	s.socket = conn

	_, _, payload := parseTCPResponse(s.doLogin(""))
	lr := &dnfv1.LoginResponse{}
	s.NoError(proto.Unmarshal(payload, lr), "unmarshal LoginResponse")
	s.Equal(int32(1), lr.Error, "empty openid should be rejected with error 1")
	fmt.Printf("TCP login empty openid rejected: error=%d\n", lr.Error)
}

// doLogin 发送 LOGIN 文本命令并读取响应帧
func (s *AuthTCPTestSuite) doLogin(openid string) []byte {
	loginReq := map[string]interface{}{"openid": openid}
	loginJSON, _ := json.Marshal(loginReq)
	msg := append([]byte("LOGIN:"), loginJSON...)
	lengthBytes := make([]byte, 2)
	binary.BigEndian.PutUint16(lengthBytes, uint16(len(msg)))
	_, err := s.socket.Write(append(lengthBytes, msg...))
	s.NoError(err, "send LOGIN")

	lenBuf := make([]byte, 2)
	_, err = s.socket.Read(lenBuf)
	s.NoError(err)
	bodyLen := binary.BigEndian.Uint16(lenBuf)
	s.NotZero(bodyLen, "login response body should not be empty")
	body := make([]byte, bodyLen)
	_, err = s.socket.Read(body)
	s.NoError(err)
	return body
}

// queryAccount 直连 DB 查询账号(返回 auth_key/account_key/status)
func (s *AuthTCPTestSuite) queryAccount(openid string) (string, string, int32) {
	db, err := sql.Open("mysql", testDBDSN)
	s.NoError(err)
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var authKey, accountKey string
	var status int32
	err = db.QueryRow("SELECT auth_key, account_key, status FROM account WHERE openid = ?", openid).
		Scan(&authKey, &accountKey, &status)
	s.NoError(err, "account should exist in DB")
	return authKey, accountKey, status
}
