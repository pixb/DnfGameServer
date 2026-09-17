package tests

import (
	"database/sql"
	"fmt"
	"net/url"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

// insertMailWithExpire 直插邮件(绕过 HTTP 层, 控制附件格式与过期时间; 附件支持多物品数组与旧单对象)
func insertMailWithExpire(roleID uint64, attachments string, gold int64, expireAt int64) uint64 {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return 0
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	now := time.Now().Unix()
	res, err := db.Exec(`
		INSERT INTO mail (created_at, updated_at, row_status, sender_id, sender_name, receiver_id, title, content, attachments, gold, is_read, is_claimed, expire_at)
		VALUES (?, ?, 'NORMAL', 0, '系统', ?, '测试邮件', '', ?, ?, 0, 0, ?)`,
		now, now, roleID, attachments, gold, expireAt)
	if err != nil {
		return 0
	}
	id, _ := res.LastInsertId()
	return uint64(id)
}

// insertMail 直插邮件(永不过期, 兼容既有用例)
func insertMail(roleID uint64, attachments string, gold int64) uint64 {
	return insertMailWithExpire(roleID, attachments, gold, 0)
}

// mailClaimed 查询邮件附件领取状态
func mailClaimed(mailID uint64) bool {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return false
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var claimed bool
	if err := db.QueryRow("SELECT is_claimed FROM mail WHERE id = ?", mailID).Scan(&claimed); err != nil {
		return false
	}
	return claimed
}

// bagItemBindType 查询角色背包指定模板物品的绑定类型
func bagItemBindType(roleID uint64, itemID int32) int {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return -1
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var bt int
	if err := db.QueryRow(
		"SELECT bind_type FROM bag_item WHERE role_id = ? AND item_id = ? AND row_status = 'NORMAL' ORDER BY id DESC LIMIT 1",
		roleID, itemID).Scan(&bt); err != nil {
		return -1
	}
	return bt
}

type MailClaimTestSuite struct {
	BaseTestSuite
}

func TestMailClaimTestSuite(t *testing.T) {
	suite.Run(t, new(MailClaimTestSuite))
}

func (s *MailClaimTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	// 清理本套件用到的固定 openid 旧角色, 避免角色累积
	if err := clearRolesForOpenids("ml_claim_01", "ml_claim_02", "ml_claim_03", "ml_claim_04", "ml_claim_05", "ml_claim_06", "ml_claim_07", "ml_claim_08", "ml_send_r1", "ml_send_s1", "ml_send_r2", "ml_send_s2", "ml_send_r3", "ml_send_s3", "ml_send_r4", "ml_send_s4", "ml_lst_01", "ml_race_01"); err != nil {
		s.T().Logf("clear roles warning: %v", err)
	}
}

// loginAndSelect 登录并选角, 返回角色ID(角色列表字段为 charGuid)
func (s *MailClaimTestSuite) loginAndSelect(openid string, slot int) uint64 {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Fatal("Login failed")
		return 0
	}
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	charguid := lastCharGuid(listResp)
	if charguid == 0 {
		charName := openid
		if len(charName) > 12 {
			charName = charName[:12]
		}
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": charName,
			"job":  1,
			"slot": slot,
		})
		s.NoError(err)
		s.NotNil(createResp)
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)
		charguid = lastCharGuid(listResp)
		if charguid == 0 {
			s.T().Fatal("Failed to create character")
		}
	}

	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"uid": charguid,
	})
	s.NoError(err)
	s.NotNil(selectResp)
	if authToken, ok := selectResp["authToken"].(string); ok {
		s.Client.SetToken(authToken)
	}
	return charguid
}

// TestClaimMultiAttachment 多物品附件(JSON 数组) + bind_type + 金币 一次性领取
func (s *MailClaimTestSuite) TestClaimMultiAttachment() {
	roleID := s.loginAndSelect("ml_claim_01", 1)
	mailID := insertMail(roleID,
		`[{"item_id":2001,"count":3,"bind_type":1},{"item_id":2002,"count":5,"bind_type":0}]`, 100)
	s.Require().NotZero(mailID)

	resp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 金币入账
	s.Equal(float64(100), resp["gold"])
	// 两件物品均入背包, 数量与绑定类型正确
	s.Equal(3, bagItemCount(roleID, 2001))
	s.Equal(5, bagItemCount(roleID, 2002))
	s.Equal(1, bagItemBindType(roleID, 2001))
	s.Equal(0, bagItemBindType(roleID, 2002))
	// 邮件已标记领取
	s.True(mailClaimed(mailID))
	// 响应 items 含两件与 bindType
	items, ok := resp["items"].([]interface{})
	s.True(ok)
	s.Len(items, 2)
}

// TestClaimLegacySingleAttachment 兼容旧格式: 单对象 {"item_id":x,"count":y}, bind_type 默认 0
func (s *MailClaimTestSuite) TestClaimLegacySingleAttachment() {
	roleID := s.loginAndSelect("ml_claim_02", 2)
	mailID := insertMail(roleID, `{"item_id":2001,"count":2}`, 0)
	s.Require().NotZero(mailID)

	resp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal(2, bagItemCount(roleID, 2001))
	s.Equal(0, bagItemBindType(roleID, 2001))
	s.True(mailClaimed(mailID))
}

// TestClaimAlreadyClaimed 重复领取应报错(附件已领取)
func (s *MailClaimTestSuite) TestClaimAlreadyClaimed() {
	roleID := s.loginAndSelect("ml_claim_03", 3)
	mailID := insertMail(roleID, `{"item_id":2001,"count":1}`, 0)
	s.Require().NotZero(mailID)

	_, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
	s.NoError(err)
	resp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(7), errVal)
	}
}

// TestClaimExpiredMail 过期邮件(expire_at 已到期)不可领取: 报错且附件不发放
func (s *MailClaimTestSuite) TestClaimExpiredMail() {
	roleID := s.loginAndSelect("ml_claim_04", 4)
	expiredAt := time.Now().Unix() - 100
	mailID := insertMailWithExpire(roleID, `{"item_id":2001,"count":1}`, 50, expiredAt)
	s.Require().NotZero(mailID)

	resp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	// 附件未发放、金币未入账、邮件未被标记领取
	s.Equal(0, bagItemCount(roleID, 2001))
	s.Equal(int64(0), getGold(roleID))
	s.False(mailClaimed(mailID))
}

// TestClaimInvalidBindType 附件绑定类型非法(0/1/2 之外)整封拒绝:
// 报错、附件不入包、邮件未被标记领取(校验先于标记, 避免数据丢失)
func (s *MailClaimTestSuite) TestClaimInvalidBindType() {
	roleID := s.loginAndSelect("ml_claim_07", 7)
	// 多物品附件: 一件合法(2002 bind1) + 一件非法(2001 bind9)
	mailID := insertMail(roleID, `[{"item_id":2002,"count":1,"bind_type":1},{"item_id":2001,"count":1,"bind_type":9}]`, 0)
	s.Require().NotZero(mailID)

	resp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	if msg, ok := resp["message"].(string); ok {
		s.Contains(msg, "绑定")
	}
	// 整封拒绝: 合法附件也不入包, 邮件未被标记领取(可重试修复后领取)
	s.Equal(0, bagItemCount(roleID, 2002))
	s.Equal(0, bagItemCount(roleID, 2001))
	s.False(mailClaimed(mailID))
}

// TestClaimBindType2 拾取绑定(2)附件正常领取
func (s *MailClaimTestSuite) TestClaimBindType2() {
	roleID := s.loginAndSelect("ml_claim_08", 8)
	mailID := insertMail(roleID, `{"item_id":2001,"count":1,"bind_type":2}`, 0)
	s.Require().NotZero(mailID)

	resp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal(1, bagItemCount(roleID, 2001))
	s.Equal(2, bagItemBindType(roleID, 2001))
	s.True(mailClaimed(mailID))
}

// TestMailCleanup 清理接口: 只删过期邮件(expire_at>0 且已到期), 未来/永不过期保留
func (s *MailClaimTestSuite) TestMailCleanup() {
	roleID := s.loginAndSelect("ml_claim_05", 5)
	// 先清一次, 消除其他用例遗留的过期邮件干扰, 保证删除数精确
	preResp, err := s.Client.Post("/api/v1/mail/cleanup", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(preResp)

	expiredID := insertMailWithExpire(roleID, `{"item_id":2001,"count":1}`, 0, time.Now().Unix()-100)
	futureID := insertMailWithExpire(roleID, `{"item_id":2001,"count":1}`, 0, time.Now().Unix()+86400)
	neverID := insertMail(roleID, `{"item_id":2001,"count":1}`, 0)
	s.Require().NotZero(expiredID)
	s.Require().NotZero(futureID)
	s.Require().NotZero(neverID)

	resp, err := s.Client.Post("/api/v1/mail/cleanup", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	// 仅删除 1 条过期邮件
	if deleted, ok := resp["deleted"].(float64); ok {
		s.Equal(float64(1), deleted)
	}
	// 过期邮件已删, 未来/永不过期保留
	s.False(mailExists(expiredID))
	s.True(mailExists(futureID))
	s.True(mailExists(neverID))
}

// TestMailListExcludesExpired 列表惰性清理: 过期邮件不再出现在邮件列表
func (s *MailClaimTestSuite) TestMailListExcludesExpired() {
	roleID := s.loginAndSelect("ml_claim_06", 6)
	expiredID := insertMailWithExpire(roleID, `{"item_id":2001,"count":1}`, 0, time.Now().Unix()-100)
	validID := insertMailWithExpire(roleID, `{"item_id":2002,"count":1}`, 0, time.Now().Unix()+86400)
	s.Require().NotZero(expiredID)
	s.Require().NotZero(validID)

	resp, err := s.Client.Get("/api/v1/mail/list")
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	mails, ok := resp["mails"].([]interface{})
	s.True(ok, "mails should be a list")
	foundValid := false
	foundExpired := false
	for _, m := range mails {
		mm, ok := m.(map[string]interface{})
		if !ok {
			continue
		}
		id, _ := mm["id"].(float64)
		if uint64(id) == validID {
			foundValid = true
		}
		if uint64(id) == expiredID {
			foundExpired = true
		}
	}
	s.True(foundValid, "有效邮件应出现在列表")
	s.False(foundExpired, "过期邮件不应出现在列表")
}

// TestSendMailWithExpire 发信自定义过期时间: expire_at 写入邮件, 过期前可领取
// 注意: openid 截断 12 字符建角色名, 避免与其它用例撞名(如 xxx2 截断后可能与 xxx 相同)
func (s *MailClaimTestSuite) TestSendMailWithExpire() {
	// 收件人角色(name=openid)
	recvID := s.loginAndSelect("ml_send_r1", 30)
	s.Require().NotZero(recvID)
	// 发件人
	senderID := s.loginAndSelect("ml_send_s1", 31)
	s.Require().NotZero(senderID)

	expireAt := time.Now().Unix() + 86400
	resp, err := s.Client.Post(fmt.Sprintf(
		"/api/v1/mail/send?target_name=%s&title=%s&content=%s&gold=50&expire_at=%d",
		"ml_send_r1", "带过期附件", "七天后过期", expireAt), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	mailID, _ := resp["mailId"].(float64)
	s.True(mailID > 0)
	// DB 校验 expire_at 落库
	s.Equal(expireAt, mailExpireAt(uint64(mailID)))

	// 切回收件人身份领取(未过期, 正常领取金币)
	recvID2 := s.loginAndSelect("ml_send_r1", 30)
	s.Require().Equal(recvID, recvID2)
	claimResp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", uint64(mailID)), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(claimResp)
	if errVal, ok := claimResp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	if gold, ok := claimResp["gold"].(float64); ok {
		s.Equal(float64(50), gold)
	}
}

// TestSendMailRejectPastExpire 发信过期时间在过去应拒绝
func (s *MailClaimTestSuite) TestSendMailRejectPastExpire() {
	s.loginAndSelect("ml_send_r2", 32)
	s.loginAndSelect("ml_send_s2", 33)

	resp, err := s.Client.Post(fmt.Sprintf(
		"/api/v1/mail/send?target_name=%s&title=%s&expire_at=%d",
		"ml_send_r2", "过期时间在过去", time.Now().Unix()-100), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	if msg, ok := resp["message"].(string); ok {
		s.Contains(msg, "过期时间")
	}
}

// TestSendMailWithAttachment 发信带附件(JSON 数组): 收件人领取后物品入包且 bind_type 正确
func (s *MailClaimTestSuite) TestSendMailWithAttachment() {
	recvID := s.loginAndSelect("ml_send_r3", 34)
	s.Require().NotZero(recvID)
	s.loginAndSelect("ml_send_s3", 35)

	attachments := `[{"item_id":2001,"count":2,"bind_type":1},{"item_id":2002,"count":3,"bind_type":0}]`
	resp, err := s.Client.Post(fmt.Sprintf(
		"/api/v1/mail/send?target_name=%s&title=%s&attachments=%s",
		"ml_send_r3", "带附件", url.QueryEscape(attachments)), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	mailID, _ := resp["mailId"].(float64)
	s.True(mailID > 0)

	// 收件人领取: 两件物品按配置入包
	s.loginAndSelect("ml_send_r3", 34)
	claimResp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", uint64(mailID)), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(claimResp)
	if errVal, ok := claimResp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	s.Equal(2, bagItemCount(recvID, 2001))
	s.Equal(1, bagItemBindType(recvID, 2001))
	s.Equal(3, bagItemCount(recvID, 2002))
	s.Equal(0, bagItemBindType(recvID, 2002))
}

// TestSendMailInvalidAttachment 发信附件非法(绑定类型越界/JSON 格式错误)整封拒绝, 不发信
func (s *MailClaimTestSuite) TestSendMailInvalidAttachment() {
	s.loginAndSelect("ml_send_r4", 36)
	s.loginAndSelect("ml_send_s4", 37)

	// 绑定类型越界(9)
	badBind := `[{"item_id":2001,"count":1,"bind_type":9}]`
	resp, err := s.Client.Post(fmt.Sprintf(
		"/api/v1/mail/send?target_name=%s&title=%s&attachments=%s",
		"ml_send_r4", "非法绑定", url.QueryEscape(badBind)), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	if msg, ok := resp["message"].(string); ok {
		s.Contains(msg, "绑定")
	}

	// JSON 格式错误
	resp2, err := s.Client.Post(fmt.Sprintf(
		"/api/v1/mail/send?target_name=%s&title=%s&attachments=%s",
		"ml_send_r4", "坏JSON", url.QueryEscape("not-json")), map[string]interface{}{})
	s.NoError(err)
	s.NotNil(resp2)
	if errVal, ok := resp2["error"]; ok {
		s.Equal(float64(1), errVal)
	}
	if msg, ok := resp2["message"].(string); ok {
		s.Contains(msg, "附件")
	}
}

// mailExpireAt 查询邮件过期时间
func mailExpireAt(mailID uint64) int64 {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return 0
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var expireAt int64
	if err := db.QueryRow(`SELECT expire_at FROM mail WHERE id = ?`, mailID).Scan(&expireAt); err != nil {
		return 0
	}
	return expireAt
}

// mailExists 查询邮件是否存在
func mailExists(mailID uint64) bool {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return false
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	var id uint64
	if err := db.QueryRow("SELECT id FROM mail WHERE id = ?", mailID).Scan(&id); err != nil {
		return false
	}
	return true
}

// TestMailListShowsAttachments 邮件列表返回附件信息与金币
// (2026-09-07 第七十二轮: handleGetMailList 补 attachments 字段, 客户端可预览附件)
func (s *MailClaimTestSuite) TestMailListShowsAttachments() {
	roleID := s.loginAndSelect("ml_lst_01", 8)
	mailID := insertMail(roleID,
		`[{"item_id":2001,"count":3,"bind_type":1},{"item_id":2002,"count":5,"bind_type":0}]`, 100)
	s.Require().NotZero(mailID)

	resp, err := s.Client.Get("/api/v1/mail/list")
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	mails, ok := resp["mails"].([]interface{})
	s.True(ok)
	found := false
	for _, mRaw := range mails {
		m, ok := mRaw.(map[string]interface{})
		if !ok {
			continue
		}
		if uint64(m["id"].(float64)) != mailID {
			continue
		}
		found = true
		s.Equal(`[{"item_id":2001,"count":3,"bind_type":1},{"item_id":2002,"count":5,"bind_type":0}]`, m["attachments"])
		s.Equal(float64(100), m["gold"])
		s.Equal(false, m["is_claimed"])
	}
	s.True(found, "新插入邮件应在列表中")
}

// TestClaimConcurrentOnce 并发领取同一邮件: 条件标记抢占(WHERE is_claimed=0)保证
// 恰好一个成功一个 error 7, 附件只入包一次
// (2026-09-07 第七十二轮: 领取先抢锁再入包, 防并发重复发放)
func (s *MailClaimTestSuite) TestClaimConcurrentOnce() {
	roleID := s.loginAndSelect("ml_race_01", 9)
	mailID := insertMail(roleID, `{"item_id":2001,"count":1}`, 50)
	s.Require().NotZero(mailID)

	results := make(chan map[string]interface{}, 2)
	claim := func() {
		resp, err := s.Client.Post(fmt.Sprintf("/api/v1/mail/claim?mail_id=%d", mailID), map[string]interface{}{})
		if err != nil {
			results <- nil
			return
		}
		results <- resp
	}
	go claim()
	go claim()

	err0, err7 := 0, 0
	for i := 0; i < 2; i++ {
		resp := <-results
		if resp == nil {
			continue
		}
		if errVal, ok := resp["error"]; ok {
			if errVal == float64(0) {
				err0++
			} else if errVal == float64(7) {
				err7++
			}
		}
	}
	s.Equal(1, err0, "恰好一个请求领取成功")
	s.Equal(1, err7, "另一个请求应报已领取")
	// 附件只入包一次、金币只入账一次、邮件已标记
	s.Equal(1, bagItemCount(roleID, 2001))
	s.Equal(int64(50), getGold(roleID))
	s.True(mailClaimed(mailID))
}
