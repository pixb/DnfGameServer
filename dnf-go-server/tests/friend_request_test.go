package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

// FriendRequestTestSuite 好友申请/同意流程(2026-09-06 第三十九轮)
type FriendRequestTestSuite struct {
	BaseTestSuite
}

func TestFriendRequestSuite(t *testing.T) {
	suite.Run(t, new(FriendRequestTestSuite))
}

// createKnownRole 登录并建已知名角色(返回 charGuid)
func (s *FriendRequestTestSuite) createKnownRole(openid, name string) float64 {
	token := s.LoginAs(openid)
	s.NotEmpty(token, "Login should return a token")
	resp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": name,
		"job":  1,
	})
	s.NoError(err)
	s.NotNil(resp)
	s.Equal(float64(0), resp["error"], "character create should succeed")
	data, ok := resp["data"].(map[string]interface{})
	s.True(ok, "create should return data")
	guid, ok := data["charGuid"].(float64)
	s.True(ok, "created character should have charGuid")
	s.Greater(guid, float64(0), "charGuid should be positive")
	return guid
}

// TestApproveFlow 申请 → 接收方列表可见 → 同意 → 双向好友 → 重复处理报错
func (s *FriendRequestTestSuite) TestApproveFlow() {
	uid := time.Now().UnixNano()
	nameA := fmt.Sprintf("FR_A_%06d", uid%1000000)
	nameB := fmt.Sprintf("FR_B_%06d", uid%1000000)
	openidA := fmt.Sprintf("test_fr_a_%d", uid)
	openidB := fmt.Sprintf("test_fr_b_%d", uid)

	guidA := s.createKnownRole(openidA, nameA)
	guidB := s.createKnownRole(openidB, nameB)
	s.Greater(guidA, float64(0))
	s.Greater(guidB, float64(0))

	// 切回 A 登录态并发申请
	s.setupFRClient(openidA)
	reqResp, err := s.Client.Post("/api/v1/friend/request", map[string]interface{}{
		"target_name": nameB,
	})
	s.NoError(err)
	s.Equal(float64(0), reqResp["error"], "friend request should succeed")
	requestID, ok := reqResp["request_id"].(float64)
	s.True(ok, "should return request_id")
	s.Greater(requestID, float64(0))

	// 重复申请幂等
	reqResp2, err := s.Client.Post("/api/v1/friend/request", map[string]interface{}{
		"target_name": nameB,
	})
	s.NoError(err)
	s.Equal(float64(0), reqResp2["error"], "duplicate request should be idempotent")

	// B 侧待处理列表含 A
	s.setupFRClient(openidB)
	listResp, err := s.Client.Get("/api/v1/friend/requests")
	s.NoError(err)
	requests, ok := listResp["requests"].([]interface{})
	s.True(ok, "requests list should be present")
	s.Len(requests, 1, "B should have exactly 1 pending request")
	first := requests[0].(map[string]interface{})
	s.Equal(nameA, first["from_role_name"], "pending request should be from A")

	// B 同意 → 双向好友
	approveResp, err := s.Client.Post("/api/v1/friend/approve", map[string]interface{}{
		"request_id": requestID,
	})
	s.NoError(err)
	s.Equal(float64(0), approveResp["error"], "approve should succeed")

	listB, err := s.Client.Get("/api/v1/friend/list")
	s.NoError(err)
	friendsB, ok := listB["friends"].([]interface{})
	s.True(ok, "B friend list should be present")
	s.Len(friendsB, 1, "B should have A as friend")
	s.Equal(nameA, friendsB[0].(map[string]interface{})["name"])

	s.setupFRClient(openidA)
	listA, err := s.Client.Get("/api/v1/friend/list")
	s.NoError(err)
	friendsA, ok := listA["friends"].([]interface{})
	s.True(ok, "A friend list should be present")
	s.Len(friendsA, 1, "A should have B as friend (bidirectional)")
	s.Equal(nameB, friendsA[0].(map[string]interface{})["name"])

	// 重复 approve → error 7 已处理
	s.setupFRClient(openidB)
	approveResp2, err := s.Client.Post("/api/v1/friend/approve", map[string]interface{}{
		"request_id": requestID,
	})
	s.NoError(err)
	s.Equal(float64(7), approveResp2["error"], "re-approve should be rejected")
	fmt.Printf("friend request approve flow verified (A=%s B=%s req=%v)\n", nameA, nameB, uint64(requestID))
}

// TestRejectFlow 申请 → 拒绝 → 不建好友, 待处理列表清空
func (s *FriendRequestTestSuite) TestRejectFlow() {
	uid := time.Now().UnixNano()
	nameC := fmt.Sprintf("FR_C_%06d", uid%1000000)
	nameB := fmt.Sprintf("FR_D_%06d", uid%1000000)
	openidC := fmt.Sprintf("test_fr_c_%d", uid)
	openidB := fmt.Sprintf("test_fr_d_%d", uid)

	s.createKnownRole(openidC, nameC)
	s.createKnownRole(openidB, nameB)

	// C 申请 B
	s.setupFRClient(openidC)
	reqResp, err := s.Client.Post("/api/v1/friend/request", map[string]interface{}{
		"target_name": nameB,
	})
	s.NoError(err)
	s.Equal(float64(0), reqResp["error"])
	requestID, ok := reqResp["request_id"].(float64)
	s.True(ok)

	// B 拒绝
	s.setupFRClient(openidB)
	rejectResp, err := s.Client.Post("/api/v1/friend/reject", map[string]interface{}{
		"request_id": requestID,
	})
	s.NoError(err)
	s.Equal(float64(0), rejectResp["error"], "reject should succeed")

	// B 待处理列表空
	listResp, err := s.Client.Get("/api/v1/friend/requests")
	s.NoError(err)
	requests, ok := listResp["requests"].([]interface{})
	s.True(ok, "requests list should be present")
	s.Len(requests, 0, "pending list should be empty after reject")

	// 未建立好友关系
	listB, err := s.Client.Get("/api/v1/friend/list")
	s.NoError(err)
	s.Len(listB["friends"].([]interface{}), 0, "rejected request should not create friendship")

	// 重复 reject → error 7
	rejectResp2, err := s.Client.Post("/api/v1/friend/reject", map[string]interface{}{
		"request_id": requestID,
	})
	s.NoError(err)
	s.Equal(float64(7), rejectResp2["error"], "re-reject should be rejected")
	fmt.Printf("friend request reject flow verified (C=%s B=%s req=%v)\n", nameC, nameB, uint64(requestID))
}

// TestRequestSelf 申请自己 → error 8
func (s *FriendRequestTestSuite) TestRequestSelf() {
	uid := time.Now().UnixNano()
	name := fmt.Sprintf("FR_E_%06d", uid%1000000)
	openid := fmt.Sprintf("test_fr_e_%d", uid)
	s.createKnownRole(openid, name)
	s.setupFRClient(openid)

	resp, err := s.Client.Post("/api/v1/friend/request", map[string]interface{}{
		"target_name": name,
	})
	s.NoError(err)
	s.Equal(float64(8), resp["error"], "cannot request self")
}

// TestApproveNotYours 同意他人申请 → error 8
func (s *FriendRequestTestSuite) TestApproveNotYours() {
	uid := time.Now().UnixNano()
	nameX := fmt.Sprintf("FR_F_%06d", uid%1000000)
	nameY := fmt.Sprintf("FR_G_%06d", uid%1000000)
	nameZ := fmt.Sprintf("FR_H_%06d", uid%1000000)
	openidX := fmt.Sprintf("test_fr_f_%d", uid)
	openidY := fmt.Sprintf("test_fr_g_%d", uid)
	openidZ := fmt.Sprintf("test_fr_h_%d", uid)

	s.createKnownRole(openidX, nameX)
	s.createKnownRole(openidY, nameY)
	s.createKnownRole(openidZ, nameZ)

	// X 申请 Y
	s.setupFRClient(openidX)
	reqResp, err := s.Client.Post("/api/v1/friend/request", map[string]interface{}{
		"target_name": nameY,
	})
	s.NoError(err)
	s.Equal(float64(0), reqResp["error"])
	requestID, ok := reqResp["request_id"].(float64)
	s.True(ok)

	// Z 尝试同意(不是 Z 的申请) → error 8
	s.setupFRClient(openidZ)
	approveResp, err := s.Client.Post("/api/v1/friend/approve", map[string]interface{}{
		"request_id": requestID,
	})
	s.NoError(err)
	s.Equal(float64(8), approveResp["error"], "cannot approve others' request")

	// Y 的待处理列表仍含 X
	s.setupFRClient(openidY)
	listResp, err := s.Client.Get("/api/v1/friend/requests")
	s.NoError(err)
	requests, ok := listResp["requests"].([]interface{})
	s.True(ok)
	s.Len(requests, 1, "Y should still have X's pending request")
	fmt.Printf("friend request ownership check verified\n")
}

// setupFRClient 登录并确保已选角
func (s *FriendRequestTestSuite) setupFRClient(openid string) {
	token := s.LoginAs(openid)
	s.NotEmpty(token, "Login should return a token")
	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	if characters, ok := listResp["characters"].([]interface{}); ok && len(characters) > 0 {
		first := characters[0].(map[string]interface{})
		_, err = s.Client.Post("/api/v1/character/select", map[string]interface{}{
			"uid": first["uid"],
		})
		s.NoError(err)
	}
}

// TestFriendGroupIntimacy 分组/亲密度联动(2026-09-06 第四十二轮):
// 申请同意初始化亲密度10+默认分组 → 改分组 → 增/减亲密度(钳制0~9999)
func (s *FriendRequestTestSuite) TestFriendGroupIntimacy() {
	uid := time.Now().UnixNano()
	nameA := fmt.Sprintf("FR_I_%06d", uid%1000000)
	nameB := fmt.Sprintf("FR_J_%06d", uid%1000000)
	openidA := fmt.Sprintf("test_fr_i_%d", uid)
	openidB := fmt.Sprintf("test_fr_j_%d", uid)

	guidA := s.createKnownRole(openidA, nameA)
	guidB := s.createKnownRole(openidB, nameB)
	s.Greater(guidA, float64(0))
	s.Greater(guidB, float64(0))

	// A 申请 B, B 同意
	s.setupFRClient(openidA)
	reqResp, err := s.Client.Post("/api/v1/friend/request", map[string]interface{}{
		"target_name": nameB,
	})
	s.NoError(err)
	s.Equal(float64(0), reqResp["error"])
	requestID, ok := reqResp["request_id"].(float64)
	s.True(ok)

	s.setupFRClient(openidB)
	approveResp, err := s.Client.Post("/api/v1/friend/approve", map[string]interface{}{
		"request_id": requestID,
	})
	s.NoError(err)
	s.Equal(float64(0), approveResp["error"])

	// B 侧列表: A 的分组=默认分组, 亲密度=10(申请同意初始化)
	listB, err := s.Client.Get("/api/v1/friend/list")
	s.NoError(err)
	friendsB, ok := listB["friends"].([]interface{})
	s.True(ok)
	s.Len(friendsB, 1)
	bFriend := friendsB[0].(map[string]interface{})
	s.Equal("默认分组", bFriend["group"], "approved friend should be in 默认分组")
	s.Equal(float64(10), bFriend["intimacy"], "approved friend should start with intimacy 10")

	// B 改 A 的分组
	groupResp, err := s.Client.Post("/api/v1/friend/group", map[string]interface{}{
		"friend_uid": guidA,
		"group":      "战友",
	})
	s.NoError(err)
	s.Equal(float64(0), groupResp["error"])
	listB2, _ := s.Client.Get("/api/v1/friend/list")
	bFriend2 := listB2["friends"].([]interface{})[0].(map[string]interface{})
	s.Equal("战友", bFriend2["group"], "group should be updated")

	// B 增亲密度 50 → 60
	intimacyResp, err := s.Client.Post("/api/v1/friend/intimacy", map[string]interface{}{
		"friend_uid": guidA,
		"delta":      50,
	})
	s.NoError(err)
	s.Equal(float64(0), intimacyResp["error"])
	s.Equal(float64(60), intimacyResp["intimacy"])

	// 减亲密度 100 → 钳制 0
	intimacyResp2, err := s.Client.Post("/api/v1/friend/intimacy", map[string]interface{}{
		"friend_uid": guidA,
		"delta":      -100,
	})
	s.NoError(err)
	s.Equal(float64(0), intimacyResp2["error"])
	s.Equal(float64(0), intimacyResp2["intimacy"], "intimacy should be clamped at 0")

	// 非好友修改分组 → error 6(用第三个账号 C 改 A)
	nameC := fmt.Sprintf("FR_K_%06d", uid%1000000)
	openidC := fmt.Sprintf("test_fr_k_%d", uid)
	s.createKnownRole(openidC, nameC)
	s.setupFRClient(openidC)
	badGroup, err := s.Client.Post("/api/v1/friend/group", map[string]interface{}{
		"friend_uid": guidA,
		"group":      "陌生人",
	})
	s.NoError(err)
	s.Equal(float64(6), badGroup["error"], "non-friend group update should fail")
	fmt.Printf("friend group/intimacy flow verified (A=%s B=%s)\n", nameA, nameB)
}
