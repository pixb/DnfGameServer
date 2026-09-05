package tests

import (
	"os"
	"strconv"
)

// tcpTestPort 返回TCP测试端口。
// 默认 9000;可用环境变量 DNF_TCP_PORT 覆盖(服务端实际监听 9500)。
func tcpTestPort() int {
	if p := os.Getenv("DNF_TCP_PORT"); p != "" {
		if v, err := strconv.Atoi(p); err == nil && v > 0 {
			return v
		}
	}
	return 9000
}
