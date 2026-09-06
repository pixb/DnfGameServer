package tests

import (
	"database/sql"
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

// insertMail 直插邮件(绕过 HTTP 层, 控制附件格式; 附件支持多物品数组与旧单对象)
func insertMail(roleID uint64, attachments string, gold int64) uint64 {
	db, err := sql.Open("mysql", testDBDSN)
	if err != nil {
		return 0
	}
	defer db.Close()
	db.SetConnMaxLifetime(30 * time.Second)

	now := time.Now().Unix()
	res, err := db.Exec(`
		INSERT INTO mail (created_at, updated_at, row_status, sender_id, sender_name, receiver_id, title, content, attachments, gold, is_read, is_claimed, expire_at)
		VALUES (?, ?, 'NORMAL', 0, '系统', ?, '测试邮件', '', ?, ?, 0, 0, 0)`,
		now, now, roleID, attachments, gold)
	if err != nil {
		return 0
	}
	id, _ := res.LastInsertId()
	return uint64(id)
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
	if err := clearRolesForOpenids("ml_claim_01", "ml_claim_02", "ml_claim_03"); err != nil {
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
