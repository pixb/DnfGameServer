package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

// TestBatch81Messages 测试第81批消息类型
func TestBatch81Messages(t *testing.T) {
	testCases := []struct {
		name string
		msg  interface{}
	}{
		{
			name: "ReqGuildSkillUpgrade",
			msg:  &dnfv1.ReqGuildSkillUpgrade{},
		},
		{
			name: "ReqMinigameTreasureHunt",
			msg:  &dnfv1.ReqMinigameTreasureHunt{},
		},
		{
			name: "ReqRewardClaim",
			msg:  &dnfv1.ReqRewardClaim{},
		},
		{
			name: "ReqControlCenterTraining",
			msg:  &dnfv1.ReqControlCenterTraining{},
		},
		{
			name: "ReqRaidTeamInvite",
			msg:  &dnfv1.ReqRaidTeamInvite{},
		},
		{
			name: "ResAttackSquadMemberList",
			msg:  &dnfv1.ResAttackSquadMemberList{},
		},
		{
			name: "ResItemCombine",
			msg:  &dnfv1.ResItemCombine{},
		},
		{
			name: "PtUserLevelUp",
			msg:  &dnfv1.PtUserLevelUp{},
		},
		{
			name: "ResInquireRanking",
			msg:  &dnfv1.ResInquireRanking{},
		},
		{
			name: "ResPartyInvite",
			msg:  &dnfv1.ResPartyInvite{},
		},
		{
			name: "ReqEmblemUpgrade",
			msg:  &dnfv1.ReqEmblemUpgrade{},
		},
		{
			name: "ReqFriendAdd",
			msg:  &dnfv1.ReqFriendAdd{},
		},
		{
			name: "ReqCeraConsume",
			msg:  &dnfv1.ReqCeraConsume{},
		},
		{
			name: "ReqMessageSend",
			msg:  &dnfv1.ReqMessageSend{},
		},
		{
			name: "ReqVoiceChat",
			msg:  &dnfv1.ReqVoiceChat{},
		},
		{
			name: "ReqBoardPost",
			msg:  &dnfv1.ReqBoardPost{},
		},
		{
			name: "ReqPvpBattle",
			msg:  &dnfv1.ReqPvpBattle{},
		},
		{
			name: "ReqFacilityBuild",
			msg:  &dnfv1.ReqFacilityBuild{},
		},
		{
			name: "ReqTeamCreate",
			msg:  &dnfv1.ReqTeamCreate{},
		},
		{
			name: "ReqMoneyTrade",
			msg:  &dnfv1.ReqMoneyTrade{},
		},
		{
			name: "ReqItemUse",
			msg:  &dnfv1.ReqItemUse{},
		},
		{
			name: "ReqSyscmd",
			msg:  &dnfv1.ReqSyscmd{},
		},
		{
			name: "ReqReforge",
			msg:  &dnfv1.ReqReforge{},
		},
		{
			name: "ReqFatigueRecover",
			msg:  &dnfv1.ReqFatigueRecover{},
		},
		{
			name: "ReqLockstep",
			msg:  &dnfv1.ReqLockstep{},
		},
		{
			name: "ReqWarehouse",
			msg:  &dnfv1.ReqWarehouse{},
		},
		{
			name: "ReqDiningOrder",
			msg:  &dnfv1.ReqDiningOrder{},
		},
		{
			name: "ReqMentee",
			msg:  &dnfv1.ReqMentee{},
		},
		{
			name: "ReqBingo",
			msg:  &dnfv1.ReqBingo{},
		},
		{
			name: "ReqChivalry",
			msg:  &dnfv1.ReqChivalry{},
		},
		{
			name: "ReqSeal",
			msg:  &dnfv1.ReqSeal{},
		},
		{
			name: "ReqQuestAccept",
			msg:  &dnfv1.ReqQuestAccept{},
		},
		{
			name: "ReqQuestComplete",
			msg:  &dnfv1.ReqQuestComplete{},
		},
		{
			name: "ReqQuestAbandon",
			msg:  &dnfv1.ReqQuestAbandon{},
		},
		{
			name: "ReqControlCenter",
			msg:  &dnfv1.ReqControlCenter{},
		},
		{
			name: "ReqHistoricsite",
			msg:  &dnfv1.ReqHistoricsite{},
		},
		{
			name: "ReqRaid",
			msg:  &dnfv1.ReqRaid{},
		},
		{
			name: "ReqUserInfo",
			msg:  &dnfv1.ReqUserInfo{},
		},
		{
			name: "ReqRanking",
			msg:  &dnfv1.ReqRanking{},
		},
		{
			name: "ReqParty",
			msg:  &dnfv1.ReqParty{},
		},
		{
			name: "ReqAttackSquad",
			msg:  &dnfv1.ReqAttackSquad{},
		},
		{
			name: "ReqMinigame",
			msg:  &dnfv1.ReqMinigame{},
		},
		{
			name: "ReqMail",
			msg:  &dnfv1.ReqMail{},
		},
		{
			name: "ReqGuild",
			msg:  &dnfv1.ReqGuild{},
		},
		{
			name: "ReqItem",
			msg:  &dnfv1.ReqItem{},
		},
		{
			name: "ReqInteraction",
			msg:  &dnfv1.ReqInteraction{},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			if tc.msg == nil {
				t.Fatalf("%s message creation failed", tc.name)
			}
			t.Logf("✓ %s message created successfully", tc.name)
		})
	}
}
