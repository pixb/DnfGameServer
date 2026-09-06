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

// TestTCPLearnUpgradeSkill 技能学习/升级(2026-09-07 第四十四轮实化):
// 建角(SP=100, job=1) → 绑定 → LEARN_SKILL/UPGRADE_SKILL 文本命令 JSON payload
// 覆盖: 成功/重复学习/职业不符/等级不足/前置不足/SP不足/未学升级/满级
func (s *RankTCPTestSuite) TestTCPLearnUpgradeSkill() {
	uid := time.Now().UnixNano()
	guid := s.createCharacter(fmt.Sprintf("test_skill_%d", uid))

	// 绑定角色
	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	s.NotNil(conn)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	defer conn.Close()
	s.socket = conn

	selectJSON, _ := json.Marshal(map[string]interface{}{"uid": guid})
	s.NoError(s.sendTCP(append([]byte("SELECT_CHARACTER:"), selectJSON...)), "send SELECT_CHARACTER")
	selResp, err := s.recvTCP()
	s.NoError(err)
	s.NotNil(selResp)
	sModule, sCmd, _ := parseTCPResponse(selResp)
	s.Equal(uint16(10000), sModule)
	s.Equal(uint16(7), sCmd)

	// 发送技能命令并解析响应
	sendSkill := func(cmd string, respCmd uint16, skillID int) (*dnfv1.LearnSkillResponse, *dnfv1.UpgradeSkillResponse) {
		payloadJSON, _ := json.Marshal(map[string]interface{}{"skill_id": skillID})
		s.NoError(s.sendTCP(append([]byte(cmd+":"), payloadJSON...)), "send "+cmd)
		body, err := s.recvTCP()
		s.NoError(err)
		s.NotNil(body)
		module, rcmd, payloadBytes := parseTCPResponse(body)
		s.Equal(uint16(10001), module, "skill response module should be 10001")
		s.Equal(respCmd, rcmd, "skill response cmd should be %d", respCmd)
		if respCmd == 5 {
			rr := &dnfv1.LearnSkillResponse{}
			s.NoError(proto.Unmarshal(payloadBytes, rr), "unmarshal LearnSkillResponse")
			return rr, nil
		}
		rr := &dnfv1.UpgradeSkillResponse{}
		s.NoError(proto.Unmarshal(payloadBytes, rr), "unmarshal UpgradeSkillResponse")
		return nil, rr
	}

	// 1. 学习通用技能 1001(SP 5) 成功, SP 100→95
	learn, _ := sendSkill("LEARN_SKILL", 5, 1001)
	s.Equal(int32(0), learn.Error, "learn 1001 should succeed")
	s.Equal(int32(1001), learn.Skill.SkillId)
	s.Equal(int32(1), learn.Skill.Level)
	s.Equal(int32(5), learn.Skill.MaxLevel)
	s.Equal(int32(5), learn.Skill.SpCost)

	// 2. 学习通用技能 1002(SP 5) 成功, SP 95→90
	learn, _ = sendSkill("LEARN_SKILL", 5, 1002)
	s.Equal(int32(0), learn.Error, "learn 1002 should succeed")

	// 3. 重复学习 1001 → error=5 已学习
	learn, _ = sendSkill("LEARN_SKILL", 5, 1001)
	s.Equal(int32(5), learn.Error, "repeat learn should be error 5")

	// 4. 职业不符: 1201 是职业2技能, 角色 job=1 → error=3
	learn, _ = sendSkill("LEARN_SKILL", 5, 1201)
	s.Equal(int32(3), learn.Error, "job mismatch should be error 3")

	// 5. 等级不足: 1101 需 5 级, 角色 1 级 → error=3
	learn, _ = sendSkill("LEARN_SKILL", 5, 1101)
	s.Equal(int32(3), learn.Error, "level requirement should be error 3")

	// 6. 前置未学: 1102 需前置 1101 → error=3
	learn, _ = sendSkill("LEARN_SKILL", 5, 1102)
	s.Equal(int32(3), learn.Error, "pre-skill requirement should be error 3")

	// 7. SP 不足: DB 直插 SP=0 → 升级 1002 error=4
	db, err := sql.Open("mysql", testDBDSN)
	s.NoError(err)
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)
	_, err = db.Exec("UPDATE role SET sp = 0 WHERE id = ?", uint64(guid))
	s.NoError(err, "set sp=0")
	_, upgrade := sendSkill("UPGRADE_SKILL", 7, 1002)
	s.Equal(int32(4), upgrade.Error, "no SP should be error 4")

	// 8. 恢复 SP 到升级前值(学完两技能后为 90)再升级 1002 → level=2 成功
	_, err = db.Exec("UPDATE role SET sp = 90 WHERE id = ?", uint64(guid))
	s.NoError(err)
	_, upgrade = sendSkill("UPGRADE_SKILL", 7, 1002)
	s.Equal(int32(0), upgrade.Error, "upgrade 1002 should succeed")
	s.Equal(int32(2), upgrade.Skill.Level)
	s.Equal(int32(5), upgrade.Skill.MaxLevel)

	// 9. 升级未学技能 1301 → error=6
	_, upgrade = sendSkill("UPGRADE_SKILL", 7, 1301)
	s.Equal(int32(6), upgrade.Error, "upgrade unlearned should be error 6")

	// 10. 升级 1001 到满级(level 1→5), 再升 error=7
	for i := 0; i < 4; i++ {
		_, upgrade = sendSkill("UPGRADE_SKILL", 7, 1001)
		s.Equal(int32(0), upgrade.Error, "upgrade 1001 round %d", i)
	}
	_, upgrade = sendSkill("UPGRADE_SKILL", 7, 1001)
	s.Equal(int32(7), upgrade.Error, "max level should be error 7")

	// 11. DB 校验: role_skills 两行(1001 满级5, 1002 2级), 1001 SP 总耗 5+4*5=25
	var rsCount, lv1, lv2 int
	s.NoError(db.QueryRow("SELECT COUNT(*) FROM role_skills WHERE role_id = ?", uint64(guid)).Scan(&rsCount))
	s.Equal(2, rsCount, "two role skills expected")
	s.NoError(db.QueryRow("SELECT level FROM role_skills WHERE role_id = ? AND skill_id = 1001", uint64(guid)).Scan(&lv1))
	s.Equal(5, lv1, "1001 should be max level 5")
	s.NoError(db.QueryRow("SELECT level FROM role_skills WHERE role_id = ? AND skill_id = 1002", uint64(guid)).Scan(&lv2))
	s.Equal(2, lv2, "1002 should be level 2")
	var sp int
	s.NoError(db.QueryRow("SELECT sp FROM role WHERE id = ?", uint64(guid)).Scan(&sp))
	// 100: -5(学1001) -5(学1002) -5(升1002) -5*4(升1001满) = 65
	s.Equal(65, sp, "final SP should be 65")
	fmt.Printf("skill learn/upgrade verified (sp=%d)\n", sp)
}

// TestRoleInfoSkills 角色信息带技能列表(2026-09-07 第四十六轮实化):
// 建角 → 绑定 → 学 1001/1002 → GET_ROLE_INFO(TCP) 与 /game/character_info(HTTP) 均返回真实 base_info + skills
func (s *RankTCPTestSuite) TestRoleInfoSkills() {
	uid := time.Now().UnixNano()
	guid := s.createCharacter(fmt.Sprintf("test_skinfo_%d", uid))

	// TCP: 绑定 + 学两个技能 + GET_ROLE_INFO
	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	s.NotNil(conn)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	defer conn.Close()
	s.socket = conn

	selectJSON, _ := json.Marshal(map[string]interface{}{"uid": guid})
	s.NoError(s.sendTCP(append([]byte("SELECT_CHARACTER:"), selectJSON...)), "send SELECT_CHARACTER")
	selResp, err := s.recvTCP()
	s.NoError(err)
	s.NotNil(selResp)

	for _, skID := range []int{1001, 1002} {
		payloadJSON, _ := json.Marshal(map[string]interface{}{"skill_id": skID})
		s.NoError(s.sendTCP(append([]byte("LEARN_SKILL:"), payloadJSON...)), "learn skill")
		body, err := s.recvTCP()
		s.NoError(err)
		s.NotNil(body)
		module, rcmd, payloadBytes := parseTCPResponse(body)
		s.Equal(uint16(10001), module)
		s.Equal(uint16(5), rcmd)
		learn := &dnfv1.LearnSkillResponse{}
		s.NoError(proto.Unmarshal(payloadBytes, learn))
		s.Equal(int32(0), learn.Error, "learn skill %d", skID)
	}

	// GET_ROLE_INFO(文本命令, 无 payload)
	s.NoError(s.sendTCP([]byte("GET_ROLE_INFO:{}")), "send GET_ROLE_INFO")
	body, err := s.recvTCP()
	s.NoError(err)
	s.NotNil(body)
	module, rcmd, payloadBytes := parseTCPResponse(body)
	s.Equal(uint16(10001), module)
	s.Equal(uint16(1), rcmd, "role info response cmd should be 1")
	info := &dnfv1.GetRoleInfoResponse{}
	s.NoError(proto.Unmarshal(payloadBytes, info), "unmarshal GetRoleInfoResponse")
	s.Equal(int32(0), info.Error)
	s.NotNil(info.BaseInfo)
	s.NotEqual("勇者", info.BaseInfo.Name, "should return real character name")
	s.Equal(int32(1), info.BaseInfo.Level, "new char level should be 1")
	s.Equal(int32(1), info.BaseInfo.Job)
	s.Len(info.Skills, 2, "should have 2 learned skills")
	skillMap := map[int32]int32{}
	for _, sk := range info.Skills {
		skillMap[sk.SkillId] = sk.Level
	}
	s.Equal(int32(1), skillMap[1001], "skill 1001 learned at level 1")
	s.Equal(int32(1), skillMap[1002], "skill 1002 learned at level 1")

	// HTTP: character_info 带 skills/sp
	resp, err := s.Client.Get(fmt.Sprintf("/api/v1/game/character_info?charguid=%d", uint64(guid)))
	s.NoError(err)
	s.AssertSuccess(resp)
	char := resp["character"].(map[string]interface{})
	s.Equal(float64(90), char["sp"], "SP should be 90 after learning two 5-cost skills")
	skillsArr, ok := char["skills"].([]interface{})
	s.True(ok, "skills should be an array")
	s.Len(skillsArr, 2, "HTTP should also return 2 skills")
	found := false
	for _, item := range skillsArr {
		sk := item.(map[string]interface{})
		if sk["skillId"].(float64) == 1001 {
			found = true
			s.Equal("冲刺", sk["name"], "skill name should come from skills table")
			s.Equal(float64(5), sk["maxLevel"], "1001 max level is 5")
		}
	}
	s.True(found, "skill 1001 should be in HTTP response")
	// 2026-09-07 第四十七轮: battle_info 接 role_attributes 真实映射(建角默认属性)
	s.NotNil(info.BattleInfo, "battle info should be present")
	s.Equal(int32(10), info.BattleInfo.Str, "str from role_attributes")
	s.Equal(int32(10), info.BattleInfo.Dex, "dex maps intelligence")
	s.Equal(int32(10), info.BattleInfo.Vit, "vit from role_attributes")
	s.Equal(int32(10), info.BattleInfo.Spr, "spr from role_attributes")
	s.Equal(int32(100), info.BattleInfo.Hp, "hp from role_attributes")
	s.Equal(int32(100), info.BattleInfo.MaxHp, "max_hp from role_attributes")
	s.Equal(int32(100), info.BattleInfo.Mp, "mp from role_attributes")
	s.Equal(int32(100), info.BattleInfo.MaxMp, "max_mp from role_attributes")
	s.Equal(int32(10), info.BattleInfo.Atk, "atk from role_attributes")
	s.Equal(int32(10), info.BattleInfo.Def, "def from role_attributes")
	s.Equal(int32(100), info.BattleInfo.MoveSpeed, "move_speed from role_attributes")

	// HTTP: battle map 同样来自 role_attributes
	battle, ok := char["battle"].(map[string]interface{})
	s.True(ok, "battle should be a map")
	s.Equal(float64(10), battle["str"], "http battle str")
	s.Equal(float64(100), battle["hp"], "http battle hp")
	s.Equal(float64(100), battle["moveSpeed"], "http battle moveSpeed")
	fmt.Printf("role info skills verified (tcp=%d, http=%d)\n", len(info.Skills), len(skillsArr))
}

// TestTCPPartyCommands 队伍控制文本命令兼容(2026-09-07 第四十八轮):
// ControlGroupHandler 此前仅接受 protobuf 请求, 文本命令(CREATE_PARTY/LEAVE_PARTY/KICK_OUT_MEMBER)
// 直接走 codec textExtras → handler 拒绝; 本轮支持 textExtras JSON payload(type/targetguid/partyguid)
func (s *RankTCPTestSuite) TestTCPPartyCommands() {
	uid := time.Now().UnixNano()
	guidA := s.createCharacter(fmt.Sprintf("test_party_a_%d", uid))
	guidB := s.createCharacter(fmt.Sprintf("test_party_b_%d", uid))

	// A 建立连接并绑定
	s.bindRole(guidA)

	// CREATE_PARTY:{"type":0} → 成功
	msgA, _ := json.Marshal(map[string]interface{}{"type": 0})
	s.NoError(s.sendTCP(append([]byte("CREATE_PARTY:"), msgA...)), "send CREATE_PARTY")
	bodyA, err := s.recvTCP()
	s.NoError(err)
	modA, cmdA, pbA := parseTCPResponse(bodyA)
	s.Equal(uint16(10009), modA, "party response module")
	s.Equal(uint16(5), cmdA, "control group response cmd")
	cgA := &dnfv1.ControlGroupResponse{}
	s.NoError(proto.Unmarshal(pbA, cgA), "unmarshal ControlGroupResponse")
	s.Equal(int32(0), cgA.Error, "create party should succeed")

	// 重复创建 → error 1(已在队)
	s.NoError(s.sendTCP(append([]byte("CREATE_PARTY:"), msgA...)), "send duplicate CREATE_PARTY")
	bodyA2, _ := s.recvTCP()
	_, _, pbA2 := parseTCPResponse(bodyA2)
	cgA2 := &dnfv1.ControlGroupResponse{}
	s.NoError(proto.Unmarshal(pbA2, cgA2))
	s.Equal(int32(1), cgA2.Error, "duplicate create should fail")

	// DB 校验: A 为队长的队伍存在
	db, err := sql.Open("mysql", testDBDSN)
	s.NoError(err)
	var cnt int
	s.NoError(db.QueryRow("SELECT COUNT(*) FROM t_party WHERE leader_id = ?", uint64(guidA)).Scan(&cnt))
	s.Equal(1, cnt, "party with leader A should exist")
	db.Close()

	// B 建立连接并绑定, 踢人但不在队 → error 1
	s.bindRole(guidB)
	msgB, _ := json.Marshal(map[string]interface{}{"type": 3, "targetguid": guidA})
	s.NoError(s.sendTCP(append([]byte("KICK_OUT_MEMBER:"), msgB...)), "send KICK_OUT_MEMBER")
	bodyB, _ := s.recvTCP()
	_, _, pbB := parseTCPResponse(bodyB)
	cgB := &dnfv1.ControlGroupResponse{}
	s.NoError(proto.Unmarshal(pbB, cgB))
	s.Equal(int32(1), cgB.Error, "kick while not in party should fail")

	// A 重连 → LEAVE_PARTY:{"type":2} → 队长解散成功
	s.bindRole(guidA)
	msgL, _ := json.Marshal(map[string]interface{}{"type": 2})
	s.NoError(s.sendTCP(append([]byte("LEAVE_PARTY:"), msgL...)), "send LEAVE_PARTY")
	bodyL, _ := s.recvTCP()
	_, _, pbL := parseTCPResponse(bodyL)
	cgL := &dnfv1.ControlGroupResponse{}
	s.NoError(proto.Unmarshal(pbL, cgL))
	s.Equal(int32(0), cgL.Error, "leader leave should disband party")

	// 再次离开 → error 1(队伍已解散)
	s.NoError(s.sendTCP(append([]byte("LEAVE_PARTY:"), msgL...)), "send duplicate LEAVE_PARTY")
	bodyL2, _ := s.recvTCP()
	_, _, pbL2 := parseTCPResponse(bodyL2)
	cgL2 := &dnfv1.ControlGroupResponse{}
	s.NoError(proto.Unmarshal(pbL2, cgL2))
	s.Equal(int32(1), cgL2.Error, "leave after disband should fail")

	// DB 校验: 队伍已删除
	db2, err := sql.Open("mysql", testDBDSN)
	s.NoError(err)
	var cnt2 int
	s.NoError(db2.QueryRow("SELECT COUNT(*) FROM t_party WHERE leader_id = ?", uint64(guidA)).Scan(&cnt2))
	s.Equal(0, cnt2, "party should be deleted after disband")
	db2.Close()

	fmt.Printf("party text commands verified (create/dup/kick/leave/disband)\n")
}

// bindRole 建立 TCP 连接并 SELECT_CHARACTER 绑定角色
func (s *RankTCPTestSuite) bindRole(charGuid float64) {
	conn, err := net.DialTimeout("tcp", fmt.Sprintf("%s:%d", s.serverHost, s.serverPort), 10*time.Second)
	s.NoError(err)
	if conn == nil {
		s.T().Skip("TCP connection failed")
		return
	}
	s.socket = conn
	selectJSON, _ := json.Marshal(map[string]interface{}{"uid": charGuid})
	selMsg := append([]byte("SELECT_CHARACTER:"), selectJSON...)
	s.NoError(s.sendTCP(selMsg), "send SELECT_CHARACTER")
	selResp, err := s.recvTCP()
	s.NoError(err)
	selModule, selCmd, _ := parseTCPResponse(selResp)
	s.Equal(uint16(10000), selModule, "select response module")
	s.Equal(uint16(7), selCmd, "select response cmd")
}
