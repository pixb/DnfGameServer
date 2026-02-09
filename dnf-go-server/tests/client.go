package tests

import (
	"bytes"
	"encoding/json"
	"net/http"
	"testing"
	"time"

	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/suite"
)

// TestClient HTTP测试客户端
type TestClient struct {
	BaseURL    string
	HTTPClient *http.Client
	Token      string
}

// NewTestClient 创建测试客户端
func NewTestClient(baseURL string) *TestClient {
	return &TestClient{
		BaseURL: baseURL,
		HTTPClient: &http.Client{
			Timeout: 10 * time.Second,
		},
	}
}

// SetToken 设置认证令牌
func (c *TestClient) SetToken(token string) {
	c.Token = token
}

// Request 发送HTTP请求
func (c *TestClient) Request(method, path string, body interface{}) (*http.Response, error) {
	var bodyReader *bytes.Reader
	if body != nil {
		jsonBody, _ := json.Marshal(body)
		bodyReader = bytes.NewReader(jsonBody)
	} else {
		bodyReader = bytes.NewReader([]byte{})
	}

	req, err := http.NewRequest(method, c.BaseURL+path, bodyReader)
	if err != nil {
		return nil, err
	}

	req.Header.Set("Content-Type", "application/json")
	if c.Token != "" {
		req.Header.Set("Authorization", "Bearer "+c.Token)
	}

	return c.HTTPClient.Do(req)
}

// Post 发送POST请求
func (c *TestClient) Post(path string, body interface{}) (map[string]interface{}, error) {
	resp, err := c.Request("POST", path, body)
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	var result map[string]interface{}
	if err := json.NewDecoder(resp.Body).Decode(&result); err != nil {
		return nil, err
	}

	return result, nil
}

// BaseTestSuite 基础测试套件
type BaseTestSuite struct {
	suite.Suite
	Client *TestClient
}

// SetupSuite 测试套件设置
func (s *BaseTestSuite) SetupSuite() {
	s.Client = NewTestClient("http://localhost:8081")
}

// AssertSuccess 断言成功响应
func (s *BaseTestSuite) AssertSuccess(resp map[string]interface{}) {
	assert.Equal(s.T(), float64(0), resp["error"], "Expected success response")
}

// AssertError 断言错误响应
func (s *BaseTestSuite) AssertError(resp map[string]interface{}, expectedError float64) {
	assert.Equal(s.T(), expectedError, resp["error"], "Expected error response")
}
