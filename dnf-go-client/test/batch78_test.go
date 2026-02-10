package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch78Migration 测试第78批迁移的消息类型
func TestBatch78Migration(t *testing.T) {
	t.Run("Item", testBatch78ItemMessages)
	t.Run("Tutorial", testBatch78TutorialMessages)
	t.Run("AttackSquad", testBatch78AttackSquadMessages)
	t.Run("Vote", testBatch78VoteMessages)
	t.Run("Auth", testBatch78AuthMessages)
	t.Run("Login", testBatch78LoginMessages)
	t.Run("BlackDiamond", testBatch78BlackDiamondMessages)
	t.Run("Wedding", testBatch78WeddingMessages)
	t.Run("Attach", testBatch78AttachMessages)
	t.Run("Verification", testBatch78VerificationMessages)
	t.Run("Ready", testBatch78ReadyMessages)
	t.Run("Recommend", testBatch78RecommendMessages)
	t.Run("Creature", testBatch78CreatureMessages)
	t.Run("Custom", testBatch78CustomMessages)
	t.Run("Emblem", testBatch78EmblemMessages)
	t.Run("Battleleague", testBatch78BattleleagueMessages)
	t.Run("Artifact", testBatch78ArtifactMessages)
	t.Run("Fatigue", testBatch78FatigueMessages)
	t.Run("Guild", testBatch78GuildMessages)
	t.Run("Skill", testBatch78SkillMessages)
	t.Run("Session", testBatch78SessionMessages)
	t.Run("Pos", testBatch78PosMessages)
	t.Run("WorldBoss", testBatch78WorldBossMessages)
	t.Run("Room", testBatch78RoomMessages)
	t.Run("Pvp", testBatch78PvpMessages)
	t.Run("Tent", testBatch78TentMessages)
	t.Run("Control", testBatch78ControlMessages)
	t.Run("Rotation", testBatch78RotationMessages)
}

// 测试物品相关消息
func testBatch78ItemMessages(t *testing.T) {
	reqItemDisjoint := &dnfv1.ReqItemDisjoint{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemDisjoint)
	assert.Equal(t, int32(0), reqItemDisjoint.Error)
	assert.Equal(t, int32(1), reqItemDisjoint.Index)

	reqItemCombine := &dnfv1.ReqItemCombine{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemCombine)
	assert.Equal(t, int32(0), reqItemCombine.Error)
	assert.Equal(t, int32(1), reqItemCombine.Index)
}

// 测试教程相关消息
func testBatch78TutorialMessages(t *testing.T) {
	resIntroTutorialSave := &dnfv1.ResIntroTutorialSave{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resIntroTutorialSave)
	assert.Equal(t, int32(0), resIntroTutorialSave.Error)
	assert.Equal(t, int32(1), resIntroTutorialSave.Index)
}

// 测试攻击小队相关消息
func testBatch78AttackSquadMessages(t *testing.T) {
	reqAttackSquadPartyLeave := &dnfv1.ReqAttackSquadPartyLeave{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadPartyLeave)
	assert.Equal(t, int32(0), reqAttackSquadPartyLeave.Error)
	assert.Equal(t, int32(1), reqAttackSquadPartyLeave.Index)
}

// 测试投票相关消息
func testBatch78VoteMessages(t *testing.T) {
	resVoteKickOutUser := &dnfv1.ResVoteKickOutUser{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resVoteKickOutUser)
	assert.Equal(t, int32(0), resVoteKickOutUser.Error)
	assert.Equal(t, int32(1), resVoteKickOutUser.Index)

	reqVoteKickOutUser := &dnfv1.ReqVoteKickOutUser{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqVoteKickOutUser)
	assert.Equal(t, int32(0), reqVoteKickOutUser.Error)
	assert.Equal(t, int32(1), reqVoteKickOutUser.Index)
}

// 测试认证相关消息
func testBatch78AuthMessages(t *testing.T) {
	resAuthkeyRefresh := &dnfv1.ResAuthkeyRefresh{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAuthkeyRefresh)
	assert.Equal(t, int32(0), resAuthkeyRefresh.Error)
	assert.Equal(t, int32(1), resAuthkeyRefresh.Index)
}

// 测试登录相关消息
func testBatch78LoginMessages(t *testing.T) {
	reqLogin := &dnfv1.ReqLogin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqLogin)
	assert.Equal(t, int32(0), reqLogin.Error)
	assert.Equal(t, int32(1), reqLogin.Index)
}

// 测试黑钻石相关消息
func testBatch78BlackDiamondMessages(t *testing.T) {
	reqBlackDiamondGetBucket := &dnfv1.ReqBlackDiamondGetBucket{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqBlackDiamondGetBucket)
	assert.Equal(t, int32(0), reqBlackDiamondGetBucket.Error)
	assert.Equal(t, int32(1), reqBlackDiamondGetBucket.Index)
}

// 测试婚礼相关消息
func testBatch78WeddingMessages(t *testing.T) {
	notifyWeddingPregressState := &dnfv1.NotifyWeddingPregressState{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyWeddingPregressState)
	assert.Equal(t, int32(0), notifyWeddingPregressState.Error)
	assert.Equal(t, int32(1), notifyWeddingPregressState.Index)
}

// 测试附加相关消息
func testBatch78AttachMessages(t *testing.T) {
	reqAttachCrack := &dnfv1.ReqAttachCrack{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttachCrack)
	assert.Equal(t, int32(0), reqAttachCrack.Error)
	assert.Equal(t, int32(1), reqAttachCrack.Index)
}

// 测试验证相关消息
func testBatch78VerificationMessages(t *testing.T) {
	reqVerificationSimple := &dnfv1.ReqVerificationSimple{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqVerificationSimple)
	assert.Equal(t, int32(0), reqVerificationSimple.Error)
	assert.Equal(t, int32(1), reqVerificationSimple.Index)
}

// 测试准备相关消息
func testBatch78ReadyMessages(t *testing.T) {
	readyRes := &dnfv1.ReadyRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, readyRes)
	assert.Equal(t, int32(0), readyRes.Error)
	assert.Equal(t, int32(1), readyRes.Index)
}

// 测试推荐相关消息
func testBatch78RecommendMessages(t *testing.T) {
	reqRecommendGuildList := &dnfv1.ReqRecommendGuildList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqRecommendGuildList)
	assert.Equal(t, int32(0), reqRecommendGuildList.Error)
	assert.Equal(t, int32(1), reqRecommendGuildList.Index)
}

// 测试生物相关消息
func testBatch78CreatureMessages(t *testing.T) {
	resCreatureCommunionLevelup := &dnfv1.ResCreatureCommunionLevelup{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureCommunionLevelup)
	assert.Equal(t, int32(0), resCreatureCommunionLevelup.Error)
	assert.Equal(t, int32(1), resCreatureCommunionLevelup.Index)

	resCreatureCommunionSelect := &dnfv1.ResCreatureCommunionSelect{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureCommunionSelect)
	assert.Equal(t, int32(0), resCreatureCommunionSelect.Error)
	assert.Equal(t, int32(1), resCreatureCommunionSelect.Index)
}

// 测试自定义相关消息
func testBatch78CustomMessages(t *testing.T) {
	ptCustomData := &dnfv1.PtCustomData{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptCustomData)
	assert.Equal(t, int32(0), ptCustomData.Error)
	assert.Equal(t, int32(1), ptCustomData.Index)
}

// 测试徽章相关消息
func testBatch78EmblemMessages(t *testing.T) {
	resEmblemUpgrade := &dnfv1.ResEmblemUpgrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resEmblemUpgrade)
	assert.Equal(t, int32(0), resEmblemUpgrade.Error)
	assert.Equal(t, int32(1), resEmblemUpgrade.Index)
}

// 测试战斗联赛相关消息
func testBatch78BattleleagueMessages(t *testing.T) {
	notifyBattleleagueSelectCard := &dnfv1.NotifyBattleleagueSelectCard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBattleleagueSelectCard)
	assert.Equal(t, int32(0), notifyBattleleagueSelectCard.Error)
	assert.Equal(t, int32(1), notifyBattleleagueSelectCard.Index)

	notifyBattleleagueCardlist := &dnfv1.NotifyBattleleagueCardlist{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyBattleleagueCardlist)
	assert.Equal(t, int32(0), notifyBattleleagueCardlist.Error)
	assert.Equal(t, int32(1), notifyBattleleagueCardlist.Index)

	reqBattleleagueCardlist := &dnfv1.ReqBattleleagueCardlist{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqBattleleagueCardlist)
	assert.Equal(t, int32(0), reqBattleleagueCardlist.Error)
	assert.Equal(t, int32(1), reqBattleleagueCardlist.Index)
}

// 测试神器相关消息
func testBatch78ArtifactMessages(t *testing.T) {
	ptArtifact := &dnfv1.PtArtifact{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptArtifact)
	assert.Equal(t, int32(0), ptArtifact.Error)
	assert.Equal(t, int32(1), ptArtifact.Index)
}

// 测试疲劳相关消息
func testBatch78FatigueMessages(t *testing.T) {
	reqFatigueHelpRequest := &dnfv1.ReqFatigueHelpRequest{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqFatigueHelpRequest)
	assert.Equal(t, int32(0), reqFatigueHelpRequest.Error)
	assert.Equal(t, int32(1), reqFatigueHelpRequest.Index)
}

// 测试公会相关消息
func testBatch78GuildMessages(t *testing.T) {
	ptGuildDonationRecipe2 := &dnfv1.PtGuildDonationRecipe2{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildDonationRecipe2)
	assert.Equal(t, int32(0), ptGuildDonationRecipe2.Error)
	assert.Equal(t, int32(1), ptGuildDonationRecipe2.Index)

	resGuildMemberGrade := &dnfv1.ResGuildMemberGrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resGuildMemberGrade)
	assert.Equal(t, int32(0), resGuildMemberGrade.Error)
	assert.Equal(t, int32(1), resGuildMemberGrade.Index)

	ptGuildChat := &dnfv1.PtGuildChat{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildChat)
	assert.Equal(t, int32(0), ptGuildChat.Error)
	assert.Equal(t, int32(1), ptGuildChat.Index)

	ptGuildDonationHelpInfo := &dnfv1.PtGuildDonationHelpInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptGuildDonationHelpInfo)
	assert.Equal(t, int32(0), ptGuildDonationHelpInfo.Error)
	assert.Equal(t, int32(1), ptGuildDonationHelpInfo.Index)
}

// 测试技能相关消息
func testBatch78SkillMessages(t *testing.T) {
	resSkillInit := &dnfv1.ResSkillInit{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSkillInit)
	assert.Equal(t, int32(0), resSkillInit.Error)
	assert.Equal(t, int32(1), resSkillInit.Index)

	resSkillSlot := &dnfv1.ResSkillSlot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resSkillSlot)
	assert.Equal(t, int32(0), resSkillSlot.Error)
	assert.Equal(t, int32(1), resSkillSlot.Index)
}

// 测试会话相关消息
func testBatch78SessionMessages(t *testing.T) {
	sessionLogoutRes := &dnfv1.SessionLogoutRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, sessionLogoutRes)
	assert.Equal(t, int32(0), sessionLogoutRes.Error)
	assert.Equal(t, int32(1), sessionLogoutRes.Index)
}

// 测试位置相关消息
func testBatch78PosMessages(t *testing.T) {
	posSync := &dnfv1.PosSync{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, posSync)
	assert.Equal(t, int32(0), posSync.Error)
	assert.Equal(t, int32(1), posSync.Index)
}

// 测试世界BOSS相关消息
func testBatch78WorldBossMessages(t *testing.T) {
	resWorldBossDamage := &dnfv1.ResWorldBossDamage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resWorldBossDamage)
	assert.Equal(t, int32(0), resWorldBossDamage.Error)
	assert.Equal(t, int32(1), resWorldBossDamage.Index)
}

// 测试房间相关消息
func testBatch78RoomMessages(t *testing.T) {
	reqJoinRoom := &dnfv1.ReqJoinRoom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqJoinRoom)
	assert.Equal(t, int32(0), reqJoinRoom.Error)
	assert.Equal(t, int32(1), reqJoinRoom.Index)
}

// 测试PVP相关消息
func testBatch78PvpMessages(t *testing.T) {
	reqPvp3vs3JoinMessage := &dnfv1.ReqPvp3vs3JoinMessage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqPvp3vs3JoinMessage)
	assert.Equal(t, int32(0), reqPvp3vs3JoinMessage.Error)
	assert.Equal(t, int32(1), reqPvp3vs3JoinMessage.Index)
}

// 测试帐篷相关消息
func testBatch78TentMessages(t *testing.T) {
	reqTentCreditScoreInfo := &dnfv1.ReqTentCreditScoreInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqTentCreditScoreInfo)
	assert.Equal(t, int32(0), reqTentCreditScoreInfo.Error)
	assert.Equal(t, int32(1), reqTentCreditScoreInfo.Index)
}

// 测试控制相关消息
func testBatch78ControlMessages(t *testing.T) {
	reqControlGroupCustom := &dnfv1.ReqControlGroupCustom{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqControlGroupCustom)
	assert.Equal(t, int32(0), reqControlGroupCustom.Error)
	assert.Equal(t, int32(1), reqControlGroupCustom.Index)
}

// 测试旋转相关消息
func testBatch78RotationMessages(t *testing.T) {
	notifyRotationPvpInfo := &dnfv1.NotifyRotationPvpInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyRotationPvpInfo)
	assert.Equal(t, int32(0), notifyRotationPvpInfo.Error)
	assert.Equal(t, int32(1), notifyRotationPvpInfo.Index)
}
