package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type LogTestSuite struct {
	BaseTestSuite
}

func (s *LogTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestRecordLog 测试记录日志
func (s *LogTestSuite) TestRecordLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_record_01", 1)

	recordResp, err := s.Client.Post("/api/v1/log/record", map[string]interface{}{
		"log_level":   "info",
		"log_message": "测试日志记录",
		"log_category": "test",
		"log_time":    time.Now().Unix(),
	})
	s.NoError(err)
	s.NotNil(recordResp)

	if recordResp != nil {
		s.Equal(float64(0), recordResp["error"])
	}
}

// TestQueryLog 测试查询日志
func (s *LogTestSuite) TestQueryLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_query_01", 2)

	queryResp, err := s.Client.Post("/api/v1/log/query", map[string]interface{}{
		"log_level":    "info",
		"log_category": "test",
		"start_time":   time.Now().Add(-24 * time.Hour).Unix(),
		"end_time":     time.Now().Unix(),
		"page":         1,
		"page_size":    10,
	})
	s.NoError(err)
	s.NotNil(queryResp)

	if queryResp != nil {
		s.Equal(float64(0), queryResp["error"])
	}
}

// TestStatisticLog 测试统计日志
func (s *LogTestSuite) TestStatisticLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_stat_01", 3)

	statResp, err := s.Client.Post("/api/v1/log/statistic", map[string]interface{}{
		"start_time": time.Now().Add(-24 * time.Hour).Unix(),
		"end_time":   time.Now().Unix(),
		"group_by":   "hour",
	})
	s.NoError(err)
	s.NotNil(statResp)

	if statResp != nil {
		s.Equal(float64(0), statResp["error"])
	}
}

// TestDeleteLog 测试删除日志
func (s *LogTestSuite) TestDeleteLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_delete_01", 4)

	deleteResp, err := s.Client.Post("/api/v1/log/delete", map[string]interface{}{
		"log_ids": []int{1, 2, 3},
	})
	s.NoError(err)
	s.NotNil(deleteResp)

	if deleteResp != nil {
		s.Equal(float64(0), deleteResp["error"])
	}
}

// TestExportLog 测试导出日志
func (s *LogTestSuite) TestExportLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_export_01", 5)

	exportResp, err := s.Client.Post("/api/v1/log/export", map[string]interface{}{
		"start_time":    time.Now().Add(-24 * time.Hour).Unix(),
		"end_time":      time.Now().Unix(),
		"log_level":     "info",
		"export_format": "csv",
	})
	s.NoError(err)
	s.NotNil(exportResp)

	if exportResp != nil {
		s.Equal(float64(0), exportResp["error"])
	}
}

// TestCleanLog 测试清理日志
func (s *LogTestSuite) TestCleanLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_clean_01", 6)

	cleanResp, err := s.Client.Post("/api/v1/log/clean", map[string]interface{}{
		"before_time": time.Now().Add(-30 * 24 * time.Hour).Unix(),
		"log_level":   "info",
	})
	s.NoError(err)
	s.NotNil(cleanResp)

	if cleanResp != nil {
		s.Equal(float64(0), cleanResp["error"])
	}
}

// TestMonitorLog 测试监控日志
func (s *LogTestSuite) TestMonitorLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_monitor_01", 7)

	monitorResp, err := s.Client.Post("/api/v1/log/monitor", map[string]interface{}{
		"monitor_level":    "error",
		"monitor_duration": 3600,
	})
	s.NoError(err)
	s.NotNil(monitorResp)

	if monitorResp != nil {
		s.Equal(float64(0), monitorResp["error"])
	}
}

// TestAnalyzeLog 测试分析日志
func (s *LogTestSuite) TestAnalyzeLog() {
	_ = s.loginAndSelectCharacterWithUserAndSlot("lg_analyze_01", 8)

	analyzeResp, err := s.Client.Post("/api/v1/log/analyze", map[string]interface{}{
		"start_time":    time.Now().Add(-24 * time.Hour).Unix(),
		"end_time":      time.Now().Unix(),
		"analysis_type": "trend",
		"group_by":      "hour",
	})
	s.NoError(err)
	s.NotNil(analyzeResp)

	if analyzeResp != nil {
		s.Equal(float64(0), analyzeResp["error"])
	}
}

// loginAndSelectCharacterWithUserAndSlot 辅助函数：使用指定用户登录并确保有角色可选
func (s *LogTestSuite) loginAndSelectCharacterWithUserAndSlot(openid string, slot int) uint64 {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Fatal("Login failed")
	}

	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		// 无角色时创建(与其余套件一致)
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": fmt.Sprintf("LogHero%02d", slot),
			"job":  1,
		})
		s.NoError(err)
		s.NotNil(createResp)
		if createResp == nil || createResp["error"] != float64(0) {
			s.T().Fatal("Failed to create character")
		}
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)
		characters, ok = listResp["characters"].([]interface{})
		if !ok || len(characters) == 0 {
			s.T().Fatal("No characters available after creation")
		}
	}

	// 取账号首个角色(单角色账号 list[0] 即目标角色)
	firstChar, ok := characters[0].(map[string]interface{})
	if !ok {
		s.T().Fatal("Failed to parse character")
	}
	if guid, ok := firstChar["charGuid"].(float64); ok {
		return uint64(guid)
	}
	if uid, ok := firstChar["uid"].(float64); ok {
		return uint64(uid)
	}

	s.T().Fatal("Failed to select character")
	return 0
}

func TestLogTestSuite(t *testing.T) {
	suite.Run(t, new(LogTestSuite))
}
