package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
)

func main() {
	// 1. 登录
	loginResp, err := http.Post("http://localhost:8081/api/v1/auth/login", "application/json",
		mapToReader(map[string]interface{}{"openid": "test_debug_01"}))
	if err != nil {
		fmt.Printf("Login failed: %v\n", err)
		return
	}
	defer loginResp.Body.Close()

	loginData := map[string]interface{}{}
	json.NewDecoder(loginResp.Body).Decode(&loginData)
	fmt.Printf("Login response: %+v\n", loginData)

	authKey, _ := loginData["authKey"].(string)

	// 2. 创建角色
	createReq, _ := http.NewRequest("POST", "http://localhost:8081/api/v1/character/create",
		mapToReader(map[string]interface{}{"name": "test_dbg_01", "job": 1, "slot": 1}))
	createReq.Header.Set("Content-Type", "application/json")
	createReq.Header.Set("Authorization", "Bearer "+authKey)
	createResp, err := http.DefaultClient.Do(createReq)
	if err != nil {
		fmt.Printf("Create character failed: %v\n", err)
		return
	}
	defer createResp.Body.Close()

	createData := map[string]interface{}{}
	json.NewDecoder(createResp.Body).Decode(&createData)
	fmt.Printf("Create character response: %+v\n", createData)

	// 3. 获取角色列表
	listReq, _ := http.NewRequest("GET", "http://localhost:8081/api/v1/character/list", nil)
	listReq.Header.Set("Authorization", "Bearer "+authKey)
	listResp, err := http.DefaultClient.Do(listReq)
	if err != nil {
		fmt.Printf("List characters failed: %v\n", err)
		return
	}
	defer listResp.Body.Close()

	listData := map[string]interface{}{}
	json.NewDecoder(listResp.Body).Decode(&listData)
	fmt.Printf("List characters response: %+v\n", listData)

	// 4. 选择角色
	if chars, ok := listData["characters"].([]interface{}); ok && len(chars) > 0 {
		char := chars[0].(map[string]interface{})
		var uid float64
		switch v := char["uid"].(type) {
		case float64:
			uid = v
		case string:
			fmt.Sscanf(v, "%f", &uid)
		}
		selectReq, _ := http.NewRequest("POST", "http://localhost:8081/api/v1/character/select",
			mapToReader(map[string]interface{}{"uid": uid}))
		selectReq.Header.Set("Content-Type", "application/json")
		selectReq.Header.Set("Authorization", "Bearer "+authKey)
		selectResp, err := http.DefaultClient.Do(selectReq)
		if err != nil {
			fmt.Printf("Select character failed: %v\n", err)
			return
		}
		defer selectResp.Body.Close()

		selectData := map[string]interface{}{}
		json.NewDecoder(selectResp.Body).Decode(&selectData)
		fmt.Printf("Select character response: %+v\n", selectData)

		authToken, _ := selectData["authToken"].(string)

		// 5. 创建队伍
		createPartyReq, _ := http.NewRequest("POST", "http://localhost:8081/api/v1/party/create",
			mapToReader(map[string]interface{}{}))
		createPartyReq.Header.Set("Content-Type", "application/json")
		createPartyReq.Header.Set("Authorization", "Bearer "+authToken)
		createPartyResp, err := http.DefaultClient.Do(createPartyReq)
		if err != nil {
			fmt.Printf("Create party failed: %v\n", err)
			return
		}
		defer createPartyResp.Body.Close()

		createPartyData := map[string]interface{}{}
		json.NewDecoder(createPartyResp.Body).Decode(&createPartyData)
		fmt.Printf("Create party response: %+v\n", createPartyData)
	}
}

func mapToReader(m map[string]interface{}) io.Reader {
	b, _ := json.Marshal(m)
	return bytes.NewReader(b)
}
