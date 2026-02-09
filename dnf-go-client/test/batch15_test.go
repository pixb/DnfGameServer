package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试公会成员列表请求消息
func TestBatch15GuildMemberListRequest(t *testing.T) {
	req := &dnfv1.GuildMemberListRequest{
		CharacterId: 1,
		GuildId:     1,
		Page:        1,
		PageSize:    20,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildMemberListRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildMemberListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildMemberListRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.GuildId != req.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, req.GuildId)
	}

	if unmarshaled.Page != req.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, req.Page)
	}

	if unmarshaled.PageSize != req.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, req.PageSize)
	}
}

// 测试公会成员列表响应消息
func TestBatch15GuildMemberListResponse(t *testing.T) {
	resp := &dnfv1.GuildMemberListResponse{
		Error:       0,
		CharacterId: 1,
		GuildId:     1,
		Total:       1,
		Page:        1,
		PageSize:    20,
		Members: []*dnfv1.GuildMemberInfo{
			{
				CharacterId:   1,
				CharacterName: "TestPlayer",
				Level:         10,
				Job:           1,
				Position:      1,
				JoinTime:      1234567890,
				Contribution:  100,
				OnlineStatus:  1,
			},
		},
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildMemberListResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildMemberListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildMemberListResponse: %v", err)
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

	if unmarshaled.Total != resp.Total {
		t.Errorf("Total mismatch: got %d, want %d", unmarshaled.Total, resp.Total)
	}

	if unmarshaled.Page != resp.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, resp.Page)
	}

	if unmarshaled.PageSize != resp.PageSize {
		t.Errorf("PageSize mismatch: got %d, want %d", unmarshaled.PageSize, resp.PageSize)
	}

	if len(unmarshaled.Members) != len(resp.Members) {
		t.Errorf("Members length mismatch: got %d, want %d", len(unmarshaled.Members), len(resp.Members))
	}
}

// 测试公会捐赠请求消息
func TestBatch15GuildDonateRequest(t *testing.T) {
	req := &dnfv1.GuildDonateRequest{
		CharacterId:  1,
		GuildId:      1,
		DonateType:   1,
		DonateAmount: 100,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildDonateRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildDonateRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildDonateRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.GuildId != req.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, req.GuildId)
	}

	if unmarshaled.DonateType != req.DonateType {
		t.Errorf("DonateType mismatch: got %d, want %d", unmarshaled.DonateType, req.DonateType)
	}

	if unmarshaled.DonateAmount != req.DonateAmount {
		t.Errorf("DonateAmount mismatch: got %d, want %d", unmarshaled.DonateAmount, req.DonateAmount)
	}
}

// 测试公会捐赠响应消息
func TestBatch15GuildDonateResponse(t *testing.T) {
	resp := &dnfv1.GuildDonateResponse{
		Error:        0,
		CharacterId:  1,
		GuildId:      1,
		DonateType:   1,
		DonateAmount: 100,
		Contribution: 10,
		Success:      true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildDonateResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildDonateResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildDonateResponse: %v", err)
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

	if unmarshaled.DonateType != resp.DonateType {
		t.Errorf("DonateType mismatch: got %d, want %d", unmarshaled.DonateType, resp.DonateType)
	}

	if unmarshaled.DonateAmount != resp.DonateAmount {
		t.Errorf("DonateAmount mismatch: got %d, want %d", unmarshaled.DonateAmount, resp.DonateAmount)
	}

	if unmarshaled.Contribution != resp.Contribution {
		t.Errorf("Contribution mismatch: got %d, want %d", unmarshaled.Contribution, resp.Contribution)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试公会技能请求消息
func TestBatch15GuildSkillRequest(t *testing.T) {
	req := &dnfv1.GuildSkillRequest{
		CharacterId: 1,
		GuildId:     1,
		SkillId:     1,
		Operation:   1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildSkillRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildSkillRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildSkillRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.GuildId != req.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, req.GuildId)
	}

	if unmarshaled.SkillId != req.SkillId {
		t.Errorf("SkillId mismatch: got %d, want %d", unmarshaled.SkillId, req.SkillId)
	}

	if unmarshaled.Operation != req.Operation {
		t.Errorf("Operation mismatch: got %d, want %d", unmarshaled.Operation, req.Operation)
	}
}

// 测试公会技能响应消息
func TestBatch15GuildSkillResponse(t *testing.T) {
	resp := &dnfv1.GuildSkillResponse{
		Error:       0,
		CharacterId: 1,
		GuildId:     1,
		SkillId:     1,
		SkillInfo: &dnfv1.GuildSkillInfo{
			SkillId:          1,
			SkillName:        "Test Skill",
			Level:            1,
			MaxLevel:         10,
			EffectValue:      10,
			CostContribution: 100,
			CostGuildExp:     1000,
			IsLearned:        true,
		},
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildSkillResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildSkillResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildSkillResponse: %v", err)
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

	if unmarshaled.SkillId != resp.SkillId {
		t.Errorf("SkillId mismatch: got %d, want %d", unmarshaled.SkillId, resp.SkillId)
	}

	if unmarshaled.SkillInfo == nil {
		t.Errorf("SkillInfo should not be nil")
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试公会仓库请求消息
func TestBatch15GuildStorageRequest(t *testing.T) {
	req := &dnfv1.GuildStorageRequest{
		CharacterId: 1,
		GuildId:     1,
		Operation:   1,
		ItemId:      1,
		ItemCount:   1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal GuildStorageRequest: %v", err)
	}

	unmarshaled := &dnfv1.GuildStorageRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildStorageRequest: %v", err)
	}

	if unmarshaled.CharacterId != req.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, req.CharacterId)
	}

	if unmarshaled.GuildId != req.GuildId {
		t.Errorf("GuildId mismatch: got %d, want %d", unmarshaled.GuildId, req.GuildId)
	}

	if unmarshaled.Operation != req.Operation {
		t.Errorf("Operation mismatch: got %d, want %d", unmarshaled.Operation, req.Operation)
	}

	if unmarshaled.ItemId != req.ItemId {
		t.Errorf("ItemId mismatch: got %d, want %d", unmarshaled.ItemId, req.ItemId)
	}

	if unmarshaled.ItemCount != req.ItemCount {
		t.Errorf("ItemCount mismatch: got %d, want %d", unmarshaled.ItemCount, req.ItemCount)
	}
}

// 测试公会仓库响应消息
func TestBatch15GuildStorageResponse(t *testing.T) {
	resp := &dnfv1.GuildStorageResponse{
		Error:       0,
		CharacterId: 1,
		GuildId:     1,
		Operation:   1,
		ItemId:      1,
		ItemCount:   1,
		Items: []*dnfv1.GuildStorageItem{
			{
				ItemId:      1,
				ItemName:    "Test Item",
				ItemCount:   1,
				ItemLevel:   1,
				ItemRarity:  1,
				DepositTime: 1234567890,
				DepositorId: 1,
			},
		},
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal GuildStorageResponse: %v", err)
	}

	unmarshaled := &dnfv1.GuildStorageResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildStorageResponse: %v", err)
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

	if unmarshaled.Operation != resp.Operation {
		t.Errorf("Operation mismatch: got %d, want %d", unmarshaled.Operation, resp.Operation)
	}

	if unmarshaled.ItemId != resp.ItemId {
		t.Errorf("ItemId mismatch: got %d, want %d", unmarshaled.ItemId, resp.ItemId)
	}

	if unmarshaled.ItemCount != resp.ItemCount {
		t.Errorf("ItemCount mismatch: got %d, want %d", unmarshaled.ItemCount, resp.ItemCount)
	}

	if len(unmarshaled.Items) != len(resp.Items) {
		t.Errorf("Items length mismatch: got %d, want %d", len(unmarshaled.Items), len(resp.Items))
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试公会成员信息
func TestBatch15GuildMemberInfo(t *testing.T) {
	memberInfo := &dnfv1.GuildMemberInfo{
		CharacterId:   1,
		CharacterName: "TestPlayer",
		Level:         10,
		Job:           1,
		Position:      1,
		JoinTime:      1234567890,
		Contribution:  100,
		OnlineStatus:  1,
	}

	data, err := proto.Marshal(memberInfo)
	if err != nil {
		t.Fatalf("Failed to marshal GuildMemberInfo: %v", err)
	}

	unmarshaled := &dnfv1.GuildMemberInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildMemberInfo: %v", err)
	}

	if unmarshaled.CharacterId != memberInfo.CharacterId {
		t.Errorf("CharacterId mismatch: got %d, want %d", unmarshaled.CharacterId, memberInfo.CharacterId)
	}

	if unmarshaled.CharacterName != memberInfo.CharacterName {
		t.Errorf("CharacterName mismatch: got %s, want %s", unmarshaled.CharacterName, memberInfo.CharacterName)
	}

	if unmarshaled.Level != memberInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, memberInfo.Level)
	}

	if unmarshaled.Job != memberInfo.Job {
		t.Errorf("Job mismatch: got %d, want %d", unmarshaled.Job, memberInfo.Job)
	}

	if unmarshaled.Position != memberInfo.Position {
		t.Errorf("Position mismatch: got %d, want %d", unmarshaled.Position, memberInfo.Position)
	}

	if unmarshaled.JoinTime != memberInfo.JoinTime {
		t.Errorf("JoinTime mismatch: got %d, want %d", unmarshaled.JoinTime, memberInfo.JoinTime)
	}

	if unmarshaled.Contribution != memberInfo.Contribution {
		t.Errorf("Contribution mismatch: got %d, want %d", unmarshaled.Contribution, memberInfo.Contribution)
	}

	if unmarshaled.OnlineStatus != memberInfo.OnlineStatus {
		t.Errorf("OnlineStatus mismatch: got %d, want %d", unmarshaled.OnlineStatus, memberInfo.OnlineStatus)
	}
}

// 测试公会技能信息
func TestBatch15GuildSkillInfo(t *testing.T) {
	skillInfo := &dnfv1.GuildSkillInfo{
		SkillId:          1,
		SkillName:        "Test Skill",
		Level:            1,
		MaxLevel:         10,
		EffectValue:      10,
		CostContribution: 100,
		CostGuildExp:     1000,
		IsLearned:        true,
	}

	data, err := proto.Marshal(skillInfo)
	if err != nil {
		t.Fatalf("Failed to marshal GuildSkillInfo: %v", err)
	}

	unmarshaled := &dnfv1.GuildSkillInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildSkillInfo: %v", err)
	}

	if unmarshaled.SkillId != skillInfo.SkillId {
		t.Errorf("SkillId mismatch: got %d, want %d", unmarshaled.SkillId, skillInfo.SkillId)
	}

	if unmarshaled.SkillName != skillInfo.SkillName {
		t.Errorf("SkillName mismatch: got %s, want %s", unmarshaled.SkillName, skillInfo.SkillName)
	}

	if unmarshaled.Level != skillInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, skillInfo.Level)
	}

	if unmarshaled.MaxLevel != skillInfo.MaxLevel {
		t.Errorf("MaxLevel mismatch: got %d, want %d", unmarshaled.MaxLevel, skillInfo.MaxLevel)
	}

	if unmarshaled.EffectValue != skillInfo.EffectValue {
		t.Errorf("EffectValue mismatch: got %d, want %d", unmarshaled.EffectValue, skillInfo.EffectValue)
	}

	if unmarshaled.CostContribution != skillInfo.CostContribution {
		t.Errorf("CostContribution mismatch: got %d, want %d", unmarshaled.CostContribution, skillInfo.CostContribution)
	}

	if unmarshaled.CostGuildExp != skillInfo.CostGuildExp {
		t.Errorf("CostGuildExp mismatch: got %d, want %d", unmarshaled.CostGuildExp, skillInfo.CostGuildExp)
	}

	if unmarshaled.IsLearned != skillInfo.IsLearned {
		t.Errorf("IsLearned mismatch: got %v, want %v", unmarshaled.IsLearned, skillInfo.IsLearned)
	}
}

// 测试公会仓库物品
func TestBatch15GuildStorageItem(t *testing.T) {
	storageItem := &dnfv1.GuildStorageItem{
		ItemId:      1,
		ItemName:    "Test Item",
		ItemCount:   1,
		ItemLevel:   1,
		ItemRarity:  1,
		DepositTime: 1234567890,
		DepositorId: 1,
	}

	data, err := proto.Marshal(storageItem)
	if err != nil {
		t.Fatalf("Failed to marshal GuildStorageItem: %v", err)
	}

	unmarshaled := &dnfv1.GuildStorageItem{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildStorageItem: %v", err)
	}

	if unmarshaled.ItemId != storageItem.ItemId {
		t.Errorf("ItemId mismatch: got %d, want %d", unmarshaled.ItemId, storageItem.ItemId)
	}

	if unmarshaled.ItemName != storageItem.ItemName {
		t.Errorf("ItemName mismatch: got %s, want %s", unmarshaled.ItemName, storageItem.ItemName)
	}

	if unmarshaled.ItemCount != storageItem.ItemCount {
		t.Errorf("ItemCount mismatch: got %d, want %d", unmarshaled.ItemCount, storageItem.ItemCount)
	}

	if unmarshaled.ItemLevel != storageItem.ItemLevel {
		t.Errorf("ItemLevel mismatch: got %d, want %d", unmarshaled.ItemLevel, storageItem.ItemLevel)
	}

	if unmarshaled.ItemRarity != storageItem.ItemRarity {
		t.Errorf("ItemRarity mismatch: got %d, want %d", unmarshaled.ItemRarity, storageItem.ItemRarity)
	}

	if unmarshaled.DepositTime != storageItem.DepositTime {
		t.Errorf("DepositTime mismatch: got %d, want %d", unmarshaled.DepositTime, storageItem.DepositTime)
	}

	if unmarshaled.DepositorId != storageItem.DepositorId {
		t.Errorf("DepositorId mismatch: got %d, want %d", unmarshaled.DepositorId, storageItem.DepositorId)
	}
}
