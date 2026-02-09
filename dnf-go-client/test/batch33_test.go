package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试系统命令
func TestBatch33SyscmdCommand(t *testing.T) {
	syscmdCommand := &dnfv1.SyscmdCommand{
		Runtype:    1,
		Targettype: 2,
		Type:       3,
		Time:       123456789,
	}

	data, err := proto.Marshal(syscmdCommand)
	if err != nil {
		t.Fatalf("Failed to marshal SyscmdCommand: %v", err)
	}

	unmarshaled := &dnfv1.SyscmdCommand{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SyscmdCommand: %v", err)
	}

	if unmarshaled.Runtype != syscmdCommand.Runtype {
		t.Errorf("Runtype mismatch: got %d, want %d", unmarshaled.Runtype, syscmdCommand.Runtype)
	}

	if unmarshaled.Targettype != syscmdCommand.Targettype {
		t.Errorf("Targettype mismatch: got %d, want %d", unmarshaled.Targettype, syscmdCommand.Targettype)
	}

	if unmarshaled.Type != syscmdCommand.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, syscmdCommand.Type)
	}

	if unmarshaled.Time != syscmdCommand.Time {
		t.Errorf("Time mismatch: got %d, want %d", unmarshaled.Time, syscmdCommand.Time)
	}
}

// 测试公会buff升级成本
func TestBatch33GuildBuffUpgradeCost(t *testing.T) {
	guildBuffUpgradeCost := &dnfv1.GuildBuffUpgradeCost{
		Itemindex: 100,
		Count:     5,
	}

	data, err := proto.Marshal(guildBuffUpgradeCost)
	if err != nil {
		t.Fatalf("Failed to marshal GuildBuffUpgradeCost: %v", err)
	}

	unmarshaled := &dnfv1.GuildBuffUpgradeCost{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildBuffUpgradeCost: %v", err)
	}

	if unmarshaled.Itemindex != guildBuffUpgradeCost.Itemindex {
		t.Errorf("Itemindex mismatch: got %d, want %d", unmarshaled.Itemindex, guildBuffUpgradeCost.Itemindex)
	}

	if unmarshaled.Count != guildBuffUpgradeCost.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, guildBuffUpgradeCost.Count)
	}
}

// 测试公会捐赠累积奖励
func TestBatch33GuildDonationAccumulateReward(t *testing.T) {
	guildDonationAccumulateReward := &dnfv1.GuildDonationAccumulateReward{
		Clearcount: 10,
		Received:    true,
	}

	data, err := proto.Marshal(guildDonationAccumulateReward)
	if err != nil {
		t.Fatalf("Failed to marshal GuildDonationAccumulateReward: %v", err)
	}

	unmarshaled := &dnfv1.GuildDonationAccumulateReward{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildDonationAccumulateReward: %v", err)
	}

	if unmarshaled.Clearcount != guildDonationAccumulateReward.Clearcount {
		t.Errorf("Clearcount mismatch: got %d, want %d", unmarshaled.Clearcount, guildDonationAccumulateReward.Clearcount)
	}

	if unmarshaled.Received != guildDonationAccumulateReward.Received {
		t.Errorf("Received mismatch: got %v, want %v", unmarshaled.Received, guildDonationAccumulateReward.Received)
	}
}
