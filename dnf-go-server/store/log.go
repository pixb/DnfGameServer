package store

// BehaviorLog 行为日志
// 2026-09-06 第十九轮: rank/log TCP handler 实化——日志模块落库(t_behavior_log)。
// 客户端经 TCP 文本命令(RECORD_LOG 等)上报行为日志; 支持查询/统计/按ID删除/按时间清理。
type BehaviorLog struct {
	ID        uint64 `json:"id"`
	CreatedAt int64  `json:"createdAt"`
	RoleID    uint64 `json:"roleId"`
	Module    string `json:"module"`
	Action    string `json:"action"`
	Level     string `json:"level"`
	Content   string `json:"content"`
}
