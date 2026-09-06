package tests

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"net/http"
	"net/url"
	"strings"
	"testing"
	"time"

	_ "github.com/go-sql-driver/mysql"
	"github.com/stretchr/testify/suite"
)

// LevelUpTestSuite 角色升级/SP派发测试(2026-09-07 第四十五轮):
// 任务链路 accept → complete(发 exp) → reward(再发 exp) 触发升级与技能点派发
type LevelUpTestSuite struct {
	BaseTestSuite
}

func TestLevelUpTestSuite(t *testing.T) {
	suite.Run(t, new(LevelUpTestSuite))
}

func (s *LevelUpTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// formPost 发送 application/x-www-form-urlencoded POST(任务接口为 form-only)
func (s *LevelUpTestSuite) formPost(path string, params map[string]string) map[string]interface{} {
	form := url.Values{}
	for k, v := range params {
		form.Set(k, v)
	}
	req, err := http.NewRequest("POST", "http://localhost:8081"+path, strings.NewReader(form.Encode()))
	s.NoError(err)
	req.Header.Set("Content-Type", "application/x-www-form-urlencoded")
	if s.Client.Token != "" {
		req.Header.Set("Authorization", "Bearer "+s.Client.Token)
	}
	resp, err := s.Client.Client.Do(req)
	s.NoError(err)
	defer resp.Body.Close()
	var result map[string]interface{}
	s.NoError(json.NewDecoder(resp.Body).Decode(&result))
	return result
}

// TestLevelUpDispatchSP 任务经验触发升级+SP派发:
// 建角(level=1, SP=100) → accept → complete(exp=100→level2, SP=120) → reward(exp=200→level3, SP=140)
func (s *LevelUpTestSuite) TestLevelUpDispatchSP() {
	uid := time.Now().UnixNano()
	token := s.LoginAs(fmt.Sprintf("test_levelup_%d", uid))
	s.NotEmpty(token, "login should succeed")

	createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
		"name": fmt.Sprintf("LU_%012d", uid%1000000000000),
		"job":  1,
	})
	s.NoError(err)
	s.AssertSuccess(createResp)
	guid, ok := createResp["data"].(map[string]interface{})["charGuid"].(float64)
	s.True(ok, "created character should have charGuid")

	// 接任务
	acceptResp := s.formPost("/api/v1/task/accept", map[string]string{"taskId": "9001"})
	s.AssertSuccess(acceptResp)

	// 交任务: expGain = level(1) * 100 = 100 → 升 1 级, SP 100+20=120, exp 0
	completeResp := s.formPost("/api/v1/task/complete", map[string]string{"taskId": "9001"})
	s.AssertSuccess(completeResp)
	s.Equal(float64(1), completeResp["levelUp"], "should level up once")
	s.Equal(float64(2), completeResp["level"], "level should be 2")
	s.Equal(float64(120), completeResp["sp"], "SP should gain +20")
	s.Equal(float64(0), completeResp["exp"], "exp should reset after level up")

	// 领奖: expGain = level(2) * 100 = 200 → 升 1 级, SP 120+20=140, exp 0
	rewardResp := s.formPost("/api/v1/task/reward", map[string]string{"taskId": "9001"})
	s.AssertSuccess(rewardResp)
	rewards := rewardResp["data"].(map[string]interface{})
	s.Equal(float64(1), rewards["levelUp"], "should level up once more")
	s.Equal(float64(3), rewards["level"], "level should be 3")
	s.Equal(float64(140), rewards["sp"], "SP should be 140")
	s.Equal(float64(0), rewards["exp"], "exp should reset again")

	// DB 校验
	db, err := sql.Open("mysql", testDBDSN)
	s.NoError(err)
	defer db.Close()
	var level, sp int
	var exp int64
	s.NoError(db.QueryRow("SELECT level, sp, exp FROM role WHERE id = ?", uint64(guid)).Scan(&level, &sp, &exp))
	s.Equal(3, level, "DB level should be 3")
	s.Equal(140, sp, "DB sp should be 140")
	s.Equal(int64(0), exp, "DB exp should be 0")
	fmt.Printf("level up + SP dispatch verified (level=%d sp=%d exp=%d)\n", level, sp, exp)
}
