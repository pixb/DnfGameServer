package store

import (
	"context"
	"fmt"
	"time"
)

// StartMailCleanup 启动邮件过期清理定时任务(2026-09-06 第二十八轮):
// 启动后立即清理一轮, 之后按 interval 周期性调用 DeleteExpiredMails 清理过期邮件;
// 返回 stop 函数用于停止定时任务(重复调用 stop 仅首次生效, 由调用方保证单次停止)。
func (s *Store) StartMailCleanup(ctx context.Context, interval time.Duration, logf func(string)) func() {
	ticker := time.NewTicker(interval)
	stop := make(chan struct{})

	runOnce := func() {
		deleted, err := s.DeleteExpiredMails(ctx, time.Now().Unix())
		if err != nil {
			if logf != nil {
				logf("mail cleanup error: " + err.Error())
			}
			return
		}
		if deleted > 0 && logf != nil {
			logf(fmt.Sprintf("mail cleanup removed %d expired mails", deleted))
		}
	}

	go func() {
		defer ticker.Stop()
		// 启动即清一轮, 避免服务重启后残留过期邮件滞留一个完整周期
		runOnce()
		for {
			select {
			case <-stop:
				return
			case <-ticker.C:
				runOnce()
			}
		}
	}()

	return func() {
		select {
		case <-stop:
			// 已停止, 幂等
		default:
			close(stop)
		}
	}
}
