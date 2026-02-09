package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试公会创建请求消息
func TestBatch14GuildCreateRequest(t *testing.T) {
	req := &dnfv1.GuildCreateRequest{
		CharacterId: 1,
		GuildName:   "Test Guild",
		GuildNotice: "Welcome to our guild!",
		GuildType:   1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildCreateRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildCreateRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildCreateRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.GuildName != req.GuildName {
		t.Errorf("GuildName mismatch: got %s, want %s", unmarshaled.GuildName, req.GuildName)
	}

	if unmarshaled.GuildNotice != req.GuildNotice {
		t.Errorf("GuildNotice mismatch: got %s, want %s", unmarshaled.GuildNotice, req.GuildNotice)
	}

	if unmarshaled.GuildType != req.GuildType {
		t.Errorf("GuildType mismatch: got %d, want %d", unmarshaled.GuildType, req.GuildType)
	}
}

// 测试公会创建响应消息
func TestBatch14GuildCreateResponse(t *testing.T) {
	resp := &dnfv1.GuildCreateResponse{
		Error:       0,
		CharacterId: 1,
		GuildId:     1,
		GuildName:   "Test Guild",
		Success:     true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildCreateResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildCreateResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildCreateResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.GuildId != resp.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, resp.GuildId)
	}

	if unmarshaled.GuildName != resp.GuildName {
		t.Errorf("GuildName mismatch: got %s, want %s", unmarshaled.GuildName, resp.GuildName)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试公会加入请求消息
func TestBatch14GuildJoinRequest(t *testing.T) {
	req := &dnfv1.GuildJoinRequest{
		CharacterId:  1,
		GuildId:      1,
		ApplyMessage: "Please let me join the guild!",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildJoinRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildJoinRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildJoinRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.GuildId != req.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, req.GuildId)
	}

	if unmarshaled.ApplyMessage != req.ApplyMessage {
		t.Errorf("ApplyMessage mismatch: got %s, want %s", unmarshaled.ApplyMessage, req.ApplyMessage)
	}
}

// 测试公会加入响应消息
func TestBatch14GuildJoinResponse(t *testing.T) {
	resp := &dnfv1.GuildJoinResponse{
		Error:       0,
		CharacterId: 1,
		GuildId:     1,
		Success:     true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildJoinResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildJoinResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildJoinResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.GuildId != resp.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, resp.GuildId)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试公会信息请求消息
func TestBatch14GuildInfoRequest(t *testing.T) {
	req := &dnfv1.GuildInfoRequest{
		CharacterId: 1,
		GuildId:     1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildInfoRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.GuildId != req.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, req.GuildId)
	}
}

// 测试公会信息响应消息
func TestBatch14GuildInfoResponse(t *testing.T) {
	resp := &dnfv1.GuildInfoResponse{
		Error:       0,
		CharacterId: 1,
		GuildInfo: &dnfv1.GuildInfo{
			GuildId:        1,
			GuildName:      "Test Guild",
			GuildNotice:    "Welcome to our guild!",
			GuildLevel:     1,
			GuildExp:       0,
			MemberCount:    1,
			MaxMemberCount: 10,
			GuildMaster:    "GuildMaster",
			GuildType:      1,
			CreateTime:     1234567890,
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildInfoResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildInfoResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.GuildInfo == nil {
		t.Errorf("GuildInfo should not be nil")
	}
}

// 测试公会列表请求消息
func TestBatch14GuildListRequest(t *testing.T) {
	req := &dnfv1.GuildListRequest{
		CharacterId: 1,
		Page:        1,
		PageSize:    20,
		SearchName:  "Test",
		SortType:    1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildListRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildListRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.Page != req.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, req.Page)
	}

	if unmarshaled.PageSize != req.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, req.PageSize)
	}

	if unmarshaled.SearchName != req.SearchName {
		t.Errorf("SearchName mismatch: got %s, want %s", unmarshaled.SearchName, req.SearchName)
	}

	if unmarshaled.SortType != req.SortType {
		t.Errorf("SortType mismatch: got %d, want %d", unmarshaled.SortType, req.SortType)
	}
}

// 测试公会列表响应消息
func TestBatch14GuildListResponse(t *testing.T) {
	resp := &dnfv1.GuildListResponse{
		Error:       0,
		CharacterId: 1,
		Total:       1,
		Page:        1,
		PageSize:    20,
		Guilds: []*dnfv1.GuildBriefInfo{
			{
				GuildId:        1,
				GuildName:      "Test Guild",
				GuildLevel:     1,
				MemberCount:    1,
				MaxMemberCount: 10,
				GuildMaster:    "GuildMaster",
				IsApply:        false,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildListResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.CharacterId != resp.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, resp.CharacterId)
	}

	if unmarshaled.Total != resp.Total {
		t.Errorf("Total mismatch: got %d, want %d", unmarshaled.Total, resp.Total)
	}

	if unmarshaled.Page != resp.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, resp.Page)
	}

	if unmarshaled.PageSize != resp.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, resp.PageSize)
	}

	if len(unmarshaled.Guilds) != len(resp.Guilds) {
		t.Errorf("Guilds length mismatch: got %d, want %d", len(unmarshaled.Guilds), len(resp.Guilds))
	}
}

// 测试公会信息
func TestBatch14GuildInfo(t *testing.T) {
	guildInfo := &dnfv1.GuildInfo{
		GuildId:        1,
		GuildName:      "Test Guild",
		GuildNotice:    "Welcome to our guild!",
		GuildLevel:     1,
		GuildExp:       0,
		MemberCount:    1,
		MaxMemberCount: 10,
		GuildMaster:    "GuildMaster",
		GuildType:      1,
		CreateTime:     1234567890,
	}

	data, err := proto.Marshal(guildInfo)
	if err != nil {
		t.Fatalf("Failed to marshal GuildInfo: %v", err)
	}

	unmarshaled := &dnfv1.GuildInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildInfo: %v", err)
	}

	if unmarshaled.GuildId != guildInfo.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, guildInfo.GuildId)
	}

	if unmarshaled.GuildName != guildInfo.GuildName {
		t.Errorf("GuildName mismatch: got %s, want %s", unmarshaled.GuildName, guildInfo.GuildName)
	}

	if unmarshaled.GuildNotice != guildInfo.GuildNotice {
		t.Errorf("GuildNotice mismatch: got %s, want %s", unmarshaled.GuildNotice, guildInfo.GuildNotice)
	}

	if unmarshaled.GuildLevel != guildInfo.GuildLevel {
		t.Errorf("GuildLevel mismatch: got %d, want %d", unmarshaled.GuildLevel, guildInfo.GuildLevel)
	}

	if unmarshaled.GuildExp != guildInfo.GuildExp {
		t.Errorf("GuildExp mismatch: got %d, want %d", unmarshaled.GuildExp, guildInfo.GuildExp)
	}

	if unmarshaled.MemberCount != guildInfo.MemberCount {
		t.Errorf("MemberCount mismatch: got %d, want %d", unmarshaled.MemberCount, guildInfo.MemberCount)
	}

	if unmarshaled.MaxMemberCount != guildInfo.MaxMemberCount {
		t.Errorf("MaxMemberCount mismatch: got %d, want %d", unmarshaled.MaxMemberCount, guildInfo.MaxMemberCount)
	}

	if unmarshaled.GuildMaster != guildInfo.GuildMaster {
		t.Errorf("GuildMaster mismatch: got %s, want %s", unmarshaled.GuildMaster, guildInfo.GuildMaster)
	}

	if unmarshaled.GuildType != guildInfo.GuildType {
		t.Errorf("GuildType mismatch: got %d, want %d", unmarshaled.GuildType, guildInfo.GuildType)
	}

	if unmarshaled.CreateTime != guildInfo.CreateTime {
		t.Errorf("CreateTime mismatch: got %d, want %d", unmarshaled.CreateTime, guildInfo.CreateTime)
	}
}

// 测试公会简要信息
func TestBatch14GuildBriefInfo(t *testing.T) {
	guildBriefInfo := &dnfv1.GuildBriefInfo{
		GuildId:        1,
		GuildName:      "Test Guild",
		GuildLevel:     1,
		MemberCount:    1,
		MaxMemberCount: 10,
		GuildMaster:    "GuildMaster",
		IsApply:        false,
	}

	data, err := proto.Marshal(guildBriefInfo)
	if err != nil {
		t.Fatalf("Failed to marshal GuildBriefInfo: %v", err)
	}

	unmarshaled := &dnfv1.GuildBriefInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildBriefInfo: %v", err)
	}

	if unmarshaled.GuildId != guildBriefInfo.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, guildBriefInfo.GuildId)
	}

	if unmarshaled.GuildName != guildBriefInfo.GuildName {
		t.Errorf("GuildName mismatch: got %s, want %s", unmarshaled.GuildName, guildBriefInfo.GuildName)
	}

	if unmarshaled.GuildLevel != guildBriefInfo.GuildLevel {
		t.Errorf("GuildLevel mismatch: got %d, want %d", unmarshaled.GuildLevel, guildBriefInfo.GuildLevel)
	}

	if unmarshaled.MemberCount != guildBriefInfo.MemberCount {
		t.Errorf("MemberCount mismatch: got %d, want %d", unmarshaled.MemberCount, guildBriefInfo.MemberCount)
	}

	if unmarshaled.MaxMemberCount != guildBriefInfo.MaxMemberCount {
		t.Errorf("MaxMemberCount mismatch: got %d, want %d", unmarshaled.MaxMemberCount, guildBriefInfo.MaxMemberCount)
	}

	if unmarshaled.GuildMaster != guildBriefInfo.GuildMaster {
		t.Errorf("GuildMaster mismatch: got %s, want %s", unmarshaled.GuildMaster, guildBriefInfo.GuildMaster)
	}

	if unmarshaled.IsApply != guildBriefInfo.IsApply {
		t.Errorf("IsApply mismatch: got %v, want %v", unmarshaled.IsApply, guildBriefInfo.IsApply)
	}
}
