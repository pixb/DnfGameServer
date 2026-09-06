package tests

import (
	"context"
	"database/sql"
	"fmt"
	"testing"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/profile"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
	"github.com/pixb/DnfGameServer/dnf-go-server/store/db"
	"github.com/stretchr/testify/suite"
)

// MailCleanupSchedulerSuite 邮件过期清理定时任务(2026-09-06 第二十八轮):
// 直接构造 Store + mysql 驱动, 验证 StartMailCleanup 的「启动即清一轮」与「周期性清理」
type MailCleanupSchedulerSuite struct {
	suite.Suite
	ctx    context.Context
	cancel context.CancelFunc
	store  *store.Store
	rawDB  *sql.DB
	logs   []string
}

func TestMailCleanupSchedulerSuite(t *testing.T) {
	suite.Run(t, new(MailCleanupSchedulerSuite))
}

func (s *MailCleanupSchedulerSuite) SetupSuite() {
	prof := &profile.Profile{
		Driver: "mysql",
		DSN:    testDBDSN,
		Mode:   "dev",
	}
	driver, err := db.NewDBDriver(prof)
	s.Require().NoError(err, "create mysql driver")
	s.Require().NotNil(driver)

	s.ctx, s.cancel = context.WithCancel(context.Background())
	s.store = store.New(driver, prof)

	raw, err := sql.Open("mysql", testDBDSN)
	s.Require().NoError(err, "open raw db")
	s.rawDB = raw
}

func (s *MailCleanupSchedulerSuite) TearDownSuite() {
	if s.cancel != nil {
		s.cancel()
	}
	if s.store != nil {
		_ = s.store.Close()
	}
	if s.rawDB != nil {
		_ = s.rawDB.Close()
	}
}

// testRoleID 使用不存在的角色ID, 邮件清理不校验角色存在性, 避免污染既有角色
const testRoleID = uint64(900000001)

func (s *MailCleanupSchedulerSuite) TestCleanupImmediatelyOnStart() {
	// 插一封已过期邮件(1 小时前过期)
	mailID := insertMailWithExpire(testRoleID, `[]`, 0, time.Now().Unix()-3600)
	s.True(mailExists(mailID), "expired mail should exist before cleanup")

	// 启动调度器(150ms 周期), 启动即清一轮
	stop := s.store.StartMailCleanup(s.ctx, 150*time.Millisecond, func(msg string) {
		s.logs = append(s.logs, msg)
	})
	defer stop()

	// 轮询等待被清(启动即清, 应在数百 ms 内完成)
	s.Eventually(func() bool {
		return !mailExists(mailID)
	}, 2*time.Second, 50*time.Millisecond, "expired mail should be removed immediately on scheduler start")
}

func (s *MailCleanupSchedulerSuite) TestCleanupPeriodic() {
	// 上一用例的调度器已 stop; 本用例重新启动, 先验证启动即清不影响本用例
	stop := s.store.StartMailCleanup(s.ctx, 200*time.Millisecond, func(msg string) {
		s.logs = append(s.logs, msg)
	})
	defer stop()

	// 等首个周期稳定后, 插入一封过期邮件, 验证由周期性 tick 清掉
	time.Sleep(100 * time.Millisecond)
	mailID := insertMailWithExpire(testRoleID, `[]`, 0, time.Now().Unix()-3600)
	s.True(mailExists(mailID), "expired mail should exist after insert")

	s.Eventually(func() bool {
		return !mailExists(mailID)
	}, 3*time.Second, 50*time.Millisecond, "expired mail should be removed by periodic tick")
}

func (s *MailCleanupSchedulerSuite) TestCleanupSkipsUnexpired() {
	stop := s.store.StartMailCleanup(s.ctx, 100*time.Millisecond, func(msg string) {
		s.logs = append(s.logs, msg)
	})
	defer stop()

	// 插一封未过期邮件(1 小时后过期), 不应被清理
	mailID := insertMailWithExpire(testRoleID, `[]`, 0, time.Now().Unix()+3600)
	s.True(mailExists(mailID))

	// 等 2 个周期, 邮件应仍在
	time.Sleep(500 * time.Millisecond)
	s.True(mailExists(mailID), "unexpired mail should survive cleanup ticks")

	// 收尾: 手动清掉该邮件, 避免残留
	db, err := s.rawDB.Exec("DELETE FROM mail WHERE id = ?", mailID)
	s.NoError(err, "cleanup test mail")
	s.NotNil(db)
	_ = fmt.Sprintf("cleanup test mail_id=%d", mailID)
}
