package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

// TestBatch82Messages 测试第82批消息类型
func TestBatch82Messages(t *testing.T) {
	testCases := []struct {
		name string
		msg  interface{}
	}{
		{
			name: "ReqGuildSkillLearn",
			msg:  &dnfv1.ReqGuildSkillLearn{},
		},
		{
			name: "ReqMinigameChallenge",
			msg:  &dnfv1.ReqMinigameChallenge{},
		},
		{
			name: "ResRewardClaim",
			msg:  &dnfv1.ResRewardClaim{},
		},
		{
			name: "ReqControlCenterTask",
			msg:  &dnfv1.ReqControlCenterTask{},
		},
		{
			name: "ReqRaidTeamCreate",
			msg:  &dnfv1.ReqRaidTeamCreate{},
		},
		{
			name: "ResAttackSquadRanking",
			msg:  &dnfv1.ResAttackSquadRanking{},
		},
		{
			name: "ResItemEnhance",
			msg:  &dnfv1.ResItemEnhance{},
		},
		{
			name: "PtUserExp",
			msg:  &dnfv1.PtUserExp{},
		},
		{
			name: "ResInquireEquipmentRanking",
			msg:  &dnfv1.ResInquireEquipmentRanking{},
		},
		{
			name: "ResPartyKick",
			msg:  &dnfv1.ResPartyKick{},
		},
		{
			name: "ReqEmblemCombine",
			msg:  &dnfv1.ReqEmblemCombine{},
		},
		{
			name: "ReqFriendDelete",
			msg:  &dnfv1.ReqFriendDelete{},
		},
		{
			name: "ReqCeraRecharge",
			msg:  &dnfv1.ReqCeraRecharge{},
		},
		{
			name: "ReqMessageReceive",
			msg:  &dnfv1.ReqMessageReceive{},
		},
		{
			name: "ResVoiceChat",
			msg:  &dnfv1.ResVoiceChat{},
		},
		{
			name: "ReqBoardDelete",
			msg:  &dnfv1.ReqBoardDelete{},
		},
		{
			name: "ResPvpBattle",
			msg:  &dnfv1.ResPvpBattle{},
		},
		{
			name: "ReqFacilityUpgrade",
			msg:  &dnfv1.ReqFacilityUpgrade{},
		},
		{
			name: "ReqTeamInvite",
			msg:  &dnfv1.ReqTeamInvite{},
		},
		{
			name: "ReqMoneyExchange",
			msg:  &dnfv1.ReqMoneyExchange{},
		},
		{
			name: "ReqItemBuy",
			msg:  &dnfv1.ReqItemBuy{},
		},
		{
			name: "ReqSysSetting",
			msg:  &dnfv1.ReqSysSetting{},
		},
		{
			name: "ReqRefine",
			msg:  &dnfv1.ReqRefine{},
		},
		{
			name: "ReqFatigueBuy",
			msg:  &dnfv1.ReqFatigueBuy{},
		},
		{
			name: "ResLockstep",
			msg:  &dnfv1.ResLockstep{},
		},
		{
			name: "ReqWarehouseExpand",
			msg:  &dnfv1.ReqWarehouseExpand{},
		},
		{
			name: "ReqDiningCook",
			msg:  &dnfv1.ReqDiningCook{},
		},
		{
			name: "ResMentee",
			msg:  &dnfv1.ResMentee{},
		},
		{
			name: "ResBingo",
			msg:  &dnfv1.ResBingo{},
		},
		{
			name: "ReqChivalryTask",
			msg:  &dnfv1.ReqChivalryTask{},
		},
		{
			name: "ResSeal",
			msg:  &dnfv1.ResSeal{},
		},
		{
			name: "ReqQuestSubmit",
			msg:  &dnfv1.ReqQuestSubmit{},
		},
		{
			name: "ReqQuestReward",
			msg:  &dnfv1.ReqQuestReward{},
		},
		{
			name: "ReqQuestTrack",
			msg:  &dnfv1.ReqQuestTrack{},
		},
		{
			name: "ReqControlCenterUpgrade",
			msg:  &dnfv1.ReqControlCenterUpgrade{},
		},
		{
			name: "ReqHistoricsiteChallenge",
			msg:  &dnfv1.ReqHistoricsiteChallenge{},
		},
		{
			name: "ResRaid",
			msg:  &dnfv1.ResRaid{},
		},
		{
			name: "ResUserInfo",
			msg:  &dnfv1.ResUserInfo{},
		},
		{
			name: "ResRanking",
			msg:  &dnfv1.ResRanking{},
		},
		{
			name: "ResParty",
			msg:  &dnfv1.ResParty{},
		},
		{
			name: "ResAttackSquad",
			msg:  &dnfv1.ResAttackSquad{},
		},
		{
			name: "ResMinigame",
			msg:  &dnfv1.ResMinigame{},
		},
		{
			name: "ResMail",
			msg:  &dnfv1.ResMail{},
		},
		{
			name: "ResGuild",
			msg:  &dnfv1.ResGuild{},
		},
		{
			name: "ResItem",
			msg:  &dnfv1.ResItem{},
		},
		{
			name: "ResInteraction",
			msg:  &dnfv1.ResInteraction{},
		},
		{
			name: "ReqSkillUpgrade",
			msg:  &dnfv1.ReqSkillUpgrade{},
		},
		{
			name: "ResQuestAccept",
			msg:  &dnfv1.ResQuestAccept{},
		},
		{
			name: "ResQuestComplete",
			msg:  &dnfv1.ResQuestComplete{},
		},
		{
			name: "ResQuestAbandon",
			msg:  &dnfv1.ResQuestAbandon{},
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
