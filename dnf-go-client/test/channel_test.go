package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"
)

func TestCreateCharacterRequest_Serialization(t *testing.T) {
	req := &dnfv1.CreateCharacterRequest{
		Job:  1,
		Name: "TestCharacter",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.CreateCharacterRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Job != req.Job {
		t.Errorf("Job mismatch: got %d, want %d", parsedReq.Job, req.Job)
	}
	if parsedReq.Name != req.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsedReq.Name, req.Name)
	}
}

func TestCreateCharacterResponse_Serialization(t *testing.T) {
	resp := &dnfv1.CreateCharacterResponse{
		Error:    0,
		Charguid: 1234567890,
		Job:      1,
		Name:     "TestCharacter",
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.CreateCharacterResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if parsedResp.Charguid != resp.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsedResp.Charguid, resp.Charguid)
	}
	if parsedResp.Job != resp.Job {
		t.Errorf("Job mismatch: got %d, want %d", parsedResp.Job, resp.Job)
	}
	if parsedResp.Name != resp.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsedResp.Name, resp.Name)
	}
}

func TestChannel_Serialization(t *testing.T) {
	channel := &dnfv1.Channel{
		World:       1,
		Channel:      1,
		Ip:          "127.0.0.1",
		Port:        8080,
		Saturation:  50,
		Integration: false,
	}

	data, err := proto.Marshal(channel)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedChannel dnfv1.Channel
	if err := proto.Unmarshal(data, &parsedChannel); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedChannel.World != channel.World {
		t.Errorf("World mismatch: got %d, want %d", parsedChannel.World, channel.World)
	}
	if parsedChannel.Channel != channel.Channel {
		t.Errorf("Channel mismatch: got %d, want %d", parsedChannel.Channel, channel.Channel)
	}
	if parsedChannel.Ip != channel.Ip {
		t.Errorf("Ip mismatch: got %s, want %s", parsedChannel.Ip, channel.Ip)
	}
	if parsedChannel.Port != channel.Port {
		t.Errorf("Port mismatch: got %d, want %d", parsedChannel.Port, channel.Port)
	}
	if parsedChannel.Saturation != channel.Saturation {
		t.Errorf("Saturation mismatch: got %d, want %d", parsedChannel.Saturation, channel.Saturation)
	}
	if parsedChannel.Integration != channel.Integration {
		t.Errorf("Integration mismatch: got %v, want %v", parsedChannel.Integration, channel.Integration)
	}
}

func TestIntegrationWorld_Serialization(t *testing.T) {
	world := &dnfv1.IntegrationWorld{
		World: 1,
	}

	data, err := proto.Marshal(world)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedWorld dnfv1.IntegrationWorld
	if err := proto.Unmarshal(data, &parsedWorld); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedWorld.World != world.World {
		t.Errorf("World mismatch: got %d, want %d", parsedWorld.World, world.World)
	}
}

func TestChannelListRequest_Serialization(t *testing.T) {
	req := &dnfv1.ChannelListRequest{
		Type:    1,
		Worldid: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.ChannelListRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsedReq.Type, req.Type)
	}
	if parsedReq.Worldid != req.Worldid {
		t.Errorf("Worldid mismatch: got %d, want %d", parsedReq.Worldid, req.Worldid)
	}
}

func TestChannelListResponse_Serialization(t *testing.T) {
	resp := &dnfv1.ChannelListResponse{
		Error: 0,
		Count: 2,
		Type:  1,
	}

	resp.List = append(resp.List, &dnfv1.Channel{
		World:       1,
		Channel:      1,
		Ip:          "127.0.0.1",
		Port:        8080,
		Saturation:  50,
		Integration: false,
	})

	resp.List = append(resp.List, &dnfv1.Channel{
		World:       1,
		Channel:      2,
		Ip:          "127.0.0.1",
		Port:        8081,
		Saturation:  30,
		Integration: false,
	})

	resp.Integrations = append(resp.Integrations, &dnfv1.IntegrationWorld{
		World: 1,
	})

	resp.Integrationrecommands = append(resp.Integrationrecommands, &dnfv1.Channel{
		World:       1,
		Channel:      3,
		Ip:          "127.0.0.1",
		Port:        8082,
		Saturation:  20,
		Integration: true,
	})

	resp.Worldrecommands = append(resp.Worldrecommands, &dnfv1.Channel{
		World:       2,
		Channel:      1,
		Ip:          "127.0.0.1",
		Port:        8083,
		Saturation:  10,
		Integration: false,
	})

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedResp dnfv1.ChannelListResponse
	if err := proto.Unmarshal(data, &parsedResp); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}
	if parsedResp.Count != resp.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsedResp.Count, resp.Count)
	}
	if parsedResp.Type != resp.Type {
		t.Errorf("Type mismatch: got %d, want %d", parsedResp.Type, resp.Type)
	}
	if len(parsedResp.List) != len(resp.List) {
		t.Errorf("List length mismatch: got %d, want %d", len(parsedResp.List), len(resp.List))
	}
	if len(parsedResp.Integrations) != len(resp.Integrations) {
		t.Errorf("Integrations length mismatch: got %d, want %d", len(parsedResp.Integrations), len(resp.Integrations))
	}
	if len(parsedResp.Integrationrecommands) != len(resp.Integrationrecommands) {
		t.Errorf("Integrationrecommands length mismatch: got %d, want %d", len(parsedResp.Integrationrecommands), len(resp.Integrationrecommands))
	}
	if len(parsedResp.Worldrecommands) != len(resp.Worldrecommands) {
		t.Errorf("Worldrecommands length mismatch: got %d, want %d", len(parsedResp.Worldrecommands), len(resp.Worldrecommands))
	}
}

func TestClientInfo_Serialization(t *testing.T) {
	clientInfo := &dnfv1.ClientInfo{
		PlatID:         1,
		DeviceSoft:      "Android",
		DeviceHard:      "SM-G960F",
		TeleOper:        "China Mobile",
		Network:         "4G",
		ScrWidth:        1080,
		ScrHeight:       2400,
		Density:         3.0,
		Cpu:             "Snapdragon 8 Gen 2",
		Memory:          8192,
		GlRender:        "Adreno 740",
		GlVersion:       "OpenGL ES 3.2",
		DeviceID:        "device123",
		ClientIP:        "192.168.1.100",
		Type:            1,
		OAID:            "oaid123",
		BuildType:       dnfv1.ClientBuildType_TEEN,
		DeviceLanguage:  "zh-CN",
		DeviceUTC:       1707433600,
	}

	data, err := proto.Marshal(clientInfo)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedClientInfo dnfv1.ClientInfo
	if err := proto.Unmarshal(data, &parsedClientInfo); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedClientInfo.PlatID != clientInfo.PlatID {
		t.Errorf("PlatID mismatch: got %d, want %d", parsedClientInfo.PlatID, clientInfo.PlatID)
	}
	if parsedClientInfo.DeviceSoft != clientInfo.DeviceSoft {
		t.Errorf("DeviceSoft mismatch: got %s, want %s", parsedClientInfo.DeviceSoft, clientInfo.DeviceSoft)
	}
	if parsedClientInfo.DeviceHard != clientInfo.DeviceHard {
		t.Errorf("DeviceHard mismatch: got %s, want %s", parsedClientInfo.DeviceHard, clientInfo.DeviceHard)
	}
	if parsedClientInfo.ScrWidth != clientInfo.ScrWidth {
		t.Errorf("ScrWidth mismatch: got %d, want %d", parsedClientInfo.ScrWidth, clientInfo.ScrWidth)
	}
	if parsedClientInfo.ScrHeight != clientInfo.ScrHeight {
		t.Errorf("ScrHeight mismatch: got %d, want %d", parsedClientInfo.ScrHeight, clientInfo.ScrHeight)
	}
	if parsedClientInfo.Density != clientInfo.Density {
		t.Errorf("Density mismatch: got %f, want %f", parsedClientInfo.Density, clientInfo.Density)
	}
	if parsedClientInfo.Memory != clientInfo.Memory {
		t.Errorf("Memory mismatch: got %d, want %d", parsedClientInfo.Memory, clientInfo.Memory)
	}
	if parsedClientInfo.BuildType != clientInfo.BuildType {
		t.Errorf("BuildType mismatch: got %v, want %v", parsedClientInfo.BuildType, clientInfo.BuildType)
	}
	if parsedClientInfo.DeviceUTC != clientInfo.DeviceUTC {
		t.Errorf("DeviceUTC mismatch: got %d, want %d", parsedClientInfo.DeviceUTC, clientInfo.DeviceUTC)
	}
}

func TestEnterChannelRequest_Serialization(t *testing.T) {
	req := &dnfv1.EnterChannelRequest{
		Openid:          "test_openid_123",
		Charguid:        1234567890,
		Authkey:          "test_authkey_123",
		Version:          "1.0.0",
		Accesstoken:     "test_token_123",
		Launchinfo:       1,
		Dungeonguid:     0,
		Registeredchannelid: "channel123",
		Installchannelid:  "install123",
		Isexternpackage:  false,
		Validusercheckcode: "check123",
		ToyPlatID:       1,
		Countrycode:      "CN",
		Language:         "zh-CN",
		Adid:            "ad123",
		Idfv:             "idfv123",
		Isadult:          false,
	}

	req.Deviceinfo = &dnfv1.ClientInfo{
		PlatID:    1,
		DeviceSoft: "Android",
		DeviceHard: "SM-G960F",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Marshal failed: %v", err)
	}

	var parsedReq dnfv1.EnterChannelRequest
	if err := proto.Unmarshal(data, &parsedReq); err != nil {
		t.Fatalf("Unmarshal failed: %v", err)
	}

	if parsedReq.Openid != req.Openid {
		t.Errorf("Openid mismatch: got %s, want %s", parsedReq.Openid, req.Openid)
	}
	if parsedReq.Charguid != req.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsedReq.Charguid, req.Charguid)
	}
	if parsedReq.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %s, want %s", parsedReq.Authkey, req.Authkey)
	}
	if parsedReq.Version != req.Version {
		t.Errorf("Version mismatch: got %s, want %s", parsedReq.Version, req.Version)
	}
	if parsedReq.Accesstoken != req.Accesstoken {
		t.Errorf("Accesstoken mismatch: got %s, want %s", parsedReq.Accesstoken, req.Accesstoken)
	}
	if parsedReq.Launchinfo != req.Launchinfo {
		t.Errorf("Launchinfo mismatch: got %d, want %d", parsedReq.Launchinfo, req.Launchinfo)
	}
	if parsedReq.Isexternpackage != req.Isexternpackage {
		t.Errorf("Isexternpackage mismatch: got %v, want %v", parsedReq.Isexternpackage, req.Isexternpackage)
	}
	if parsedReq.Isadult != req.Isadult {
		t.Errorf("Isadult mismatch: got %v, want %v", parsedReq.Isadult, req.Isadult)
	}
}

func TestCreateCharacterRequest_BoundaryValues(t *testing.T) {
	tests := []struct {
		name string
		req  *dnfv1.CreateCharacterRequest
	}{
		{
			name: "zero_values",
			req: &dnfv1.CreateCharacterRequest{
				Job:  0,
				Name: "",
			},
		},
		{
			name: "max_values",
			req: &dnfv1.CreateCharacterRequest{
				Job:  2147483647,
				Name: "A",
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			data, err := proto.Marshal(tt.req)
			if err != nil {
				t.Fatalf("Marshal failed: %v", err)
			}

			var parsedReq dnfv1.CreateCharacterRequest
			if err := proto.Unmarshal(data, &parsedReq); err != nil {
				t.Fatalf("Unmarshal failed: %v", err)
			}

			if parsedReq.Job != tt.req.Job {
				t.Errorf("Job mismatch: got %d, want %d", parsedReq.Job, tt.req.Job)
			}
			if parsedReq.Name != tt.req.Name {
				t.Errorf("Name mismatch: got %s, want %s", parsedReq.Name, tt.req.Name)
			}
		})
	}
}
