package tests

import (
	"bytes"
	"encoding/json"
	"net/http"
	"time"

	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/suite"
)

type TestClient struct {
	BaseURL string
	Client  *http.Client
	Token   string
}

func NewTestClient(baseURL string) *TestClient {
	return &TestClient{
		BaseURL: baseURL,
		Client: &http.Client{
			Timeout: 10 * time.Second,
		},
	}
}

func (c *TestClient) SetToken(token string) {
	c.Token = token
}

func (c *TestClient) Post(path string, body interface{}) (map[string]interface{}, error) {
	var bodyReader *bytes.Reader
	if body != nil {
		jsonBody, _ := json.Marshal(body)
		bodyReader = bytes.NewReader(jsonBody)
	} else {
		bodyReader = bytes.NewReader([]byte{})
	}

	req, err := http.NewRequest("POST", c.BaseURL+path, bodyReader)
	if err != nil {
		return nil, err
	}

	req.Header.Set("Content-Type", "application/json")
	if c.Token != "" {
		req.Header.Set("Authorization", "Bearer "+c.Token)
	}

	resp, err := c.Client.Do(req)
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

func (c *TestClient) Get(path string) (map[string]interface{}, error) {
	req, err := http.NewRequest("GET", c.BaseURL+path, nil)
	if err != nil {
		return nil, err
	}

	if c.Token != "" {
		req.Header.Set("Authorization", "Bearer "+c.Token)
	}

	resp, err := c.Client.Do(req)
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

type BaseTestSuite struct {
	suite.Suite
	Client *TestClient
}

func (s *BaseTestSuite) SetupSuite() {
	s.Client = NewTestClient("http://localhost:8081")
}

func (s *BaseTestSuite) LoginAs(openid string) string {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": openid,
	})
	s.NoError(err)
	if err != nil || resp == nil {
		return ""
	}
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
		return token
	}
	return ""
}

func (s *BaseTestSuite) AssertSuccess(resp map[string]interface{}) {
	if resp == nil {
		s.T().Errorf("Response is nil")
		return
	}
	assert.Equal(s.T(), float64(0), resp["error"], "Expected success response")
}

func (s *BaseTestSuite) AssertError(resp map[string]interface{}, expectedError float64) {
	if resp == nil {
		s.T().Errorf("Response is nil")
		return
	}
	assert.Equal(s.T(), expectedError, resp["error"], "Expected error response")
}
