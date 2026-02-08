package test

import (
	"testing"

	"github.com/golang/protobuf/proto"

dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func TestChannelInfo_Serialization(t *testing.T) {
	// 创建测试数据
	channelInfo := &dnfv1.ChannelInfo{
		World:    1,
		Channel:  1,
		Ip:       "127.0.0.1",
		Port:     8080,
		Priority: 1,
	}

	// 序列化
	data, err := proto.Marshal(channelInfo)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	// 反序列化
	var parsedChannelInfo dnfv1.ChannelInfo
	if err := proto.Unmarshal(data, &parsedChannelInfo); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	// 验证数据一致性
	if parsedChannelInfo.World != channelInfo.World {
		t.Errorf("World mismatch: got %d, want %d", parsedChannelInfo.World, channelInfo.World)
	}
	if parsedChannelInfo.Channel != channelInfo.Channel {
		t.Errorf("Channel mismatch: got %d, want %d", parsedChannelInfo.Channel, channelInfo.Channel)
	}
	if parsedChannelInfo.Ip != channelInfo.Ip {
		t.Errorf("Ip mismatch: got %s, want %s", parsedChannelInfo.Ip, channelInfo.Ip)
	}
	if parsedChannelInfo.Port != channelInfo.Port {
		t.Errorf("Port mismatch: got %d, want %d", parsedChannelInfo.Port, channelInfo.Port)
	}
	if parsedChannelInfo.Priority != channelInfo.Priority {
		t.Errorf("Priority mismatch: got %d, want %d", parsedChannelInfo.Priority, channelInfo.Priority)
	}
}

func TestIntrudeMemberInfo_Serialization(t *testing.T) {
	// 创建测试数据
	memberInfo := &dnfv1.IntrudeMemberInfo{
		Name:        "test_member",
		Level:       100,
		Job:         1,
		Growtype:    1,
		Secgrowtype: 1,
	}

	// 序列化
	data, err := proto.Marshal(memberInfo)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	// 反序列化
	var parsedMemberInfo dnfv1.IntrudeMemberInfo
	if err := proto.Unmarshal(data, &parsedMemberInfo); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	// 验证数据一致性
	if parsedMemberInfo.Name != memberInfo.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsedMemberInfo.Name, memberInfo.Name)
	}
	if parsedMemberInfo.Level != memberInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", parsedMemberInfo.Level, memberInfo.Level)
	}
	if parsedMemberInfo.Job != memberInfo.Job {
		t.Errorf("Job mismatch: got %d, want %d", parsedMemberInfo.Job, memberInfo.Job)
	}
	if parsedMemberInfo.Growtype != memberInfo.Growtype {
		t.Errorf("Growtype mismatch: got %d, want %d", parsedMemberInfo.Growtype, memberInfo.Growtype)
	}
	if parsedMemberInfo.Secgrowtype != memberInfo.Secgrowtype {
		t.Errorf("Secgrowtype mismatch: got %d, want %d", parsedMemberInfo.Secgrowtype, memberInfo.Secgrowtype)
	}
}

func TestIntrudeInfo_Serialization(t *testing.T) {
	// 创建测试数据
	channelInfo := &dnfv1.ChannelInfo{
		World:    1,
		Channel:  1,
		Ip:       "127.0.0.1",
		Port:     8080,
		Priority: 1,
	}

	memberInfo := &dnfv1.IntrudeMemberInfo{
		Name:        "test_member",
		Level:       100,
		Job:         1,
		Growtype:    1,
		Secgrowtype: 1,
	}

	intrudeInfo := &dnfv1.IntrudeInfo{
		Intrude:      false,
		Name:         "test_name",
		Guid:         1234567890123456789,
		Channel:      channelInfo,
		PartyMembers: []*dnfv1.IntrudeMemberInfo{memberInfo},
		RpGuid:       987654321098765432,
	}

	// 序列化
	data, err := proto.Marshal(intrudeInfo)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	// 反序列化
	var parsedIntrudeInfo dnfv1.IntrudeInfo
	if err := proto.Unmarshal(data, &parsedIntrudeInfo); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	// 验证数据一致性
	if parsedIntrudeInfo.Intrude != intrudeInfo.Intrude {
		t.Errorf("Intrude mismatch: got %v, want %v", parsedIntrudeInfo.Intrude, intrudeInfo.Intrude)
	}
	if parsedIntrudeInfo.Name != intrudeInfo.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsedIntrudeInfo.Name, intrudeInfo.Name)
	}
	if parsedIntrudeInfo.Guid != intrudeInfo.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsedIntrudeInfo.Guid, intrudeInfo.Guid)
	}
	if parsedIntrudeInfo.RpGuid != intrudeInfo.RpGuid {
		t.Errorf("RpGuid mismatch: got %d, want %d", parsedIntrudeInfo.RpGuid, intrudeInfo.RpGuid)
	}
	if len(parsedIntrudeInfo.PartyMembers) != len(intrudeInfo.PartyMembers) {
		t.Errorf("PartyMembers length mismatch: got %d, want %d", len(parsedIntrudeInfo.PartyMembers), len(intrudeInfo.PartyMembers))
	}
}

func TestLoginRequest_Serialization(t *testing.T) {
	// 创建测试数据
	req := &dnfv1.LoginRequest{
		Openid:         "test_openid",
		Type:           1,
		Token:          "test_token",
		PlatID:         100,
		ClientIP:       "127.0.0.1",
		Version:        "1.0.0",
		Friendopenid:   "friend_openid",
		Cancelunregist: 0,
		Countrycode:    "CN",
		Toyplatid:      1,
		Agetype:        1,
	}

	// 序列化
	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	// 反序列化
	var parsedReq dnfv1.LoginRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	// 验证数据一致性
	if parsedReq.Openid != req.Openid {
		t.Errorf("Openid mismatch: got %s, want %s", parsedReq.Openid, req.Openid)
	}
	if parsedReq.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsedReq.Type, req.Type)
	}
	if parsedReq.Token != req.Token {
		t.Errorf("Token mismatch: got %s, want %s", parsedReq.Token, req.Token)
	}
	if parsedReq.PlatID != req.PlatID {
		t.Errorf("PlatID mismatch: got %d, want %d", parsedReq.PlatID, req.PlatID)
	}
	if parsedReq.ClientIP != req.ClientIP {
		t.Errorf("ClientIP mismatch: got %s, want %s", parsedReq.ClientIP, req.ClientIP)
	}
	if parsedReq.Version != req.Version {
		t.Errorf("Version mismatch: got %s, want %s", parsedReq.Version, req.Version)
	}
	if parsedReq.Friendopenid != req.Friendopenid {
		t.Errorf("Friendopenid mismatch: got %s, want %s", parsedReq.Friendopenid, req.Friendopenid)
	}
	if parsedReq.Cancelunregist != req.Cancelunregist {
		t.Errorf("Cancelunregist mismatch: got %d, want %d", parsedReq.Cancelunregist, req.Cancelunregist)
	}
	if parsedReq.Countrycode != req.Countrycode {
		t.Errorf("Countrycode mismatch: got %s, want %s", parsedReq.Countrycode, req.Countrycode)
	}
	if parsedReq.Toyplatid != req.Toyplatid {
		t.Errorf("Toyplatid mismatch: got %d, want %d", parsedReq.Toyplatid, req.Toyplatid)
	}
	if parsedReq.Agetype != req.Agetype {
		t.Errorf("Agetype mismatch: got %d, want %d", parsedReq.Agetype, req.Agetype)
	}
}

func TestLoginResponse_Serialization(t *testing.T) {
	// 创建测试数据
	channelInfo := &dnfv1.ChannelInfo{
		World:    1,
		Channel:  1,
		Ip:       "127.0.0.1",
		Port:     8080,
		Priority: 1,
	}

	memberInfo := &dnfv1.IntrudeMemberInfo{
		Name:        "test_member",
		Level:       100,
		Job:         1,
		Growtype:    1,
		Secgrowtype: 1,
	}

	intrudeInfo := &dnfv1.IntrudeInfo{
		Intrude:      false,
		Name:         "test_name",
		Guid:         1234567890123456789,
		Channel:      channelInfo,
		PartyMembers: []*dnfv1.IntrudeMemberInfo{memberInfo},
		RpGuid:       9223372036854775807,
	}

	res := &dnfv1.LoginResponse{
		Error:       0,
		Authkey:     "test_authkey",
		Accountkey:  "test_accountkey",
		Encrypt:     true,
		Servertime:  1234567890123456789,
		Localtime:   "2023-01-01 00:00:00",
		Authority:   1,
		Key:         "test_key",
		Channel:     []*dnfv1.ChannelInfo{channelInfo},
		Intrudeinfo: intrudeInfo,
		Worldid:     1,
		Seeds:       []int32{1, 2, 3, 4, 5},
	}

	// 序列化
	data, err := proto.Marshal(res)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	// 反序列化
	var parsedRes dnfv1.LoginResponse
	if err := proto.Unmarshal(data, &parsedRes); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	// 验证数据一致性
	if parsedRes.Error != res.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedRes.Error, res.Error)
	}
	if parsedRes.Authkey != res.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedRes.Authkey, res.Authkey)
	}
	if parsedRes.Accountkey != res.Accountkey {
		t.Errorf("Accountkey mismatch: got %s, want %s", parsedRes.Accountkey, res.Accountkey)
	}
	if parsedRes.Encrypt != res.Encrypt {
		t.Errorf("Encrypt mismatch: got %v, want %v", parsedRes.Encrypt, res.Encrypt)
	}
	if parsedRes.Servertime != res.Servertime {
		t.Errorf("Servertime mismatch: got %d, want %d", parsedRes.Servertime, res.Servertime)
	}
	if parsedRes.Localtime != res.Localtime {
		t.Errorf("Localtime mismatch: got %s, want %s", parsedRes.Localtime, res.Localtime)
	}
	if parsedRes.Authority != res.Authority {
		t.Errorf("Authority mismatch: got %d, want %d", parsedRes.Authority, res.Authority)
	}
	if parsedRes.Key != res.Key {
		t.Errorf("Key mismatch: got %s, want %s", parsedRes.Key, res.Key)
	}
	if len(parsedRes.Channel) != len(res.Channel) {
		t.Errorf("Channel length mismatch: got %d, want %d", len(parsedRes.Channel), len(res.Channel))
	}
	if parsedRes.Worldid != res.Worldid {
		t.Errorf("Worldid mismatch: got %d, want %d", parsedRes.Worldid, res.Worldid)
	}
	if len(parsedRes.Seeds) != len(res.Seeds) {
		t.Errorf("Seeds length mismatch: got %d, want %d", len(parsedRes.Seeds), len(res.Seeds))
	}
}

func TestLoginRequest_BoundaryValues(t *testing.T) {
	// 测试边界值
	testCases := []struct {
		name string
		req  *dnfv1.LoginRequest
	}{
		{
			name: "zero values",
			req: &dnfv1.LoginRequest{
				Openid:         "",
				Type:           0,
				Token:          "",
				PlatID:         0,
				ClientIP:       "",
				Version:        "",
				Friendopenid:   "",
				Cancelunregist: 0,
				Countrycode:    "",
				Toyplatid:      0,
				Agetype:        0,
			},
		},
		{
			name: "max values",
			req: &dnfv1.LoginRequest{
				Openid:         "test_openid",
				Type:           4294967295,
				Token:          "test_token",
				PlatID:         4294967295,
				ClientIP:       "255.255.255.255",
				Version:        "1.0.0",
				Friendopenid:   "friend_openid",
				Cancelunregist: 4294967295,
				Countrycode:    "CN",
				Toyplatid:      2147483647,
				Agetype:        2147483647,
			},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			data, err := proto.Marshal(tc.req)
			if err != nil {
				t.Fatalf("Marshal failed: %v", err)
			}

			var parsedReq dnfv1.LoginRequest
			if err := proto.Unmarshal(data, &parsedReq); err != nil {
				t.Fatalf("Unmarshal failed: %v", err)
			}

			// 验证数据一致性
			if parsedReq.Openid != tc.req.Openid {
				t.Errorf("Openid mismatch: got %s, want %s", parsedReq.Openid, tc.req.Openid)
			}
			if parsedReq.Type != tc.req.Type {
				t.Errorf("Type mismatch: got %d, want %d", parsedReq.Type, tc.req.Type)
			}
			if parsedReq.Token != tc.req.Token {
				t.Errorf("Token mismatch: got %s, want %s", parsedReq.Token, tc.req.Token)
			}
			if parsedReq.PlatID != tc.req.PlatID {
				t.Errorf("PlatID mismatch: got %d, want %d", parsedReq.PlatID, tc.req.PlatID)
			}
			if parsedReq.ClientIP != tc.req.ClientIP {
				t.Errorf("ClientIP mismatch: got %s, want %s", parsedReq.ClientIP, tc.req.ClientIP)
			}
			if parsedReq.Version != tc.req.Version {
				t.Errorf("Version mismatch: got %s, want %s", parsedReq.Version, tc.req.Version)
			}
			if parsedReq.Friendopenid != tc.req.Friendopenid {
				t.Errorf("Friendopenid mismatch: got %s, want %s", parsedReq.Friendopenid, tc.req.Friendopenid)
			}
			if parsedReq.Cancelunregist != tc.req.Cancelunregist {
				t.Errorf("Cancelunregist mismatch: got %d, want %d", parsedReq.Cancelunregist, tc.req.Cancelunregist)
			}
			if parsedReq.Countrycode != tc.req.Countrycode {
				t.Errorf("Countrycode mismatch: got %s, want %s", parsedReq.Countrycode, tc.req.Countrycode)
			}
			if parsedReq.Toyplatid != tc.req.Toyplatid {
				t.Errorf("Toyplatid mismatch: got %d, want %d", parsedReq.Toyplatid, tc.req.Toyplatid)
			}
			if parsedReq.Agetype != tc.req.Agetype {
				t.Errorf("Agetype mismatch: got %d, want %d", parsedReq.Agetype, tc.req.Agetype)
			}
		})
	}
}
