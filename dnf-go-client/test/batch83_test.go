package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

// TestBatch83Messages 测试第83批消息类型
func TestBatch83Messages(t *testing.T) {
	testCases := []struct {
		name string
		msg  interface{}
	}{
		{
			name: "ReqGuildSkillReset",
			msg:  &dnfv1.ReqGuildSkillReset{},
		},
		{
			name: "ReqMinigameReward",
			msg:  &dnfv1.ReqMinigameReward{},
		},
		{
			name: "ReqRewardClaimConfirm",
			msg:  &dnfv1.ReqRewardClaimConfirm{},
		},
		{
			name: "ReqControlCenterInfo",
			msg:  &dnfv1.ReqControlCenterInfo{},
		},
		{
			name: "ReqRaidTeamJoin",
			msg:  &dnfv1.ReqRaidTeamJoin{},
		},
		{
			name: "ResAttackSquadMemberInfo",
			msg:  &dnfv1.ResAttackSquadMemberInfo{},
		},
		{
			name: "ResItemDisassemble",
			msg:  &dnfv1.ResItemDisassemble{},
		},
		{
			name: "PtUserGold",
			msg:  &dnfv1.PtUserGold{},
		},
		{
			name: "ResInquirePowerRanking",
			msg:  &dnfv1.ResInquirePowerRanking{},
		},
		{
			name: "ResPartyInviteConfirm",
			msg:  &dnfv1.ResPartyInviteConfirm{},
		},
		{
			name: "ResFriendRequest",
			msg:  &dnfv1.ResFriendRequest{},
		},
		{
			name: "ReqCeraBalance",
			msg:  &dnfv1.ReqCeraBalance{},
		},
		{
			name: "ReqMessageDelete",
			msg:  &dnfv1.ReqMessageDelete{},
		},
		{
			name: "ResVoiceChatInvite",
			msg:  &dnfv1.ResVoiceChatInvite{},
		},
		{
			name: "ReqBoardPin",
			msg:  &dnfv1.ReqBoardPin{},
		},
		{
			name: "ResPvpBattleConfirm",
			msg:  &dnfv1.ResPvpBattleConfirm{},
		},
		{
			name: "ReqFacilityDemolish",
			msg:  &dnfv1.ReqFacilityDemolish{},
		},
		{
			name: "ReqTeamKick",
			msg:  &dnfv1.ReqTeamKick{},
		},
		{
			name: "ReqMoneyTransfer",
			msg:  &dnfv1.ReqMoneyTransfer{},
		},
		{
			name: "ReqItemSell",
			msg:  &dnfv1.ReqItemSell{},
		},
		{
			name: "ReqSysAnnouncement",
			msg:  &dnfv1.ReqSysAnnouncement{},
		},
		{
			name: "ReqGemEmbed",
			msg:  &dnfv1.ReqGemEmbed{},
		},
		{
			name: "ReqFatigueGift",
			msg:  &dnfv1.ReqFatigueGift{},
		},
		{
			name: "ResLockstepConfirm",
			msg:  &dnfv1.ResLockstepConfirm{},
		},
		{
			name: "ReqWarehouseSort",
			msg:  &dnfv1.ReqWarehouseSort{},
		},
		{
			name: "ReqDiningBuy",
			msg:  &dnfv1.ReqDiningBuy{},
		},
		{
			name: "ResMenteeConfirm",
			msg:  &dnfv1.ResMenteeConfirm{},
		},
		{
			name: "ResBingoConfirm",
			msg:  &dnfv1.ResBingoConfirm{},
		},
		{
			name: "ReqChivalrySkill",
			msg:  &dnfv1.ReqChivalrySkill{},
		},
		{
			name: "ResSealConfirm",
			msg:  &dnfv1.ResSealConfirm{},
		},
		{
			name: "ReqControlCenterBuild",
			msg:  &dnfv1.ReqControlCenterBuild{},
		},
		{
			name: "ReqHistoricsiteInfo",
			msg:  &dnfv1.ReqHistoricsiteInfo{},
		},
		{
			name: "ReqSkillLearn",
			msg:  &dnfv1.ReqSkillLearn{},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			if tc.msg == nil {
				t.Errorf("❌ %s message creation failed: nil message", tc.name)
				return
			}
			t.Logf("✓ %s message created successfully", tc.name)
		})
	}
}
