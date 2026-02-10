package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch79Migration 测试第79批迁移的消息类型
func TestBatch79Migration(t *testing.T) {
	t.Run("Party", testBatch79PartyMessages)
	t.Run("Pray", testBatch79PrayMessages)
	t.Run("Creature", testBatch79CreatureMessages)
	t.Run("Item", testBatch79ItemMessages)
	t.Run("Interaction", testBatch79InteractionMessages)
	t.Run("Guild", testBatch79GuildMessages)
	t.Run("AttackSquad", testBatch79AttackSquadMessages)
	t.Run("Mail", testBatch79MailMessages)
	t.Run("Historicsite", testBatch79HistoricsiteMessages)
	t.Run("Shop", testBatch79ShopMessages)
	t.Run("Raid", testBatch79RaidMessages)
	t.Run("PartyAccept", testBatch79PartyAcceptMessages)
	t.Run("Community", testBatch79CommunityMessages)
	t.Run("Skill", testBatch79SkillMessages)
	t.Run("Timebox", testBatch79TimeboxMessages)
	t.Run("Sector", testBatch79SectorMessages)
	t.Run("Monsterkill", testBatch79MonsterkillMessages)
	t.Run("Battleleague", testBatch79BattleleagueMessages)
	t.Run("Adventure", testBatch79AdventureMessages)
	t.Run("MultiPlay", testBatch79MultiPlayMessages)
	t.Run("Dungeon", testBatch79DungeonMessages)
	t.Run("Arcade", testBatch79ArcadeMessages)
	t.Run("Achievement", testBatch79AchievementMessages)
	t.Run("Money", testBatch79MoneyMessages)
	t.Run("DreamMaze", testBatch79DreamMazeMessages)
}

// 测试队伍相关消息
func testBatch79PartyMessages(t *testing.T) {
	ptPartyDeathtowerObject := &dnfv1.PtPartyDeathtowerObject{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPartyDeathtowerObject)
	assert.Equal(t, int32(0), ptPartyDeathtowerObject.Error)
	assert.Equal(t, int32(1), ptPartyDeathtowerObject.Index)
}

// 测试祈祷相关消息
func testBatch79PrayMessages(t *testing.T) {
	ptPrayMaterialInfo := &dnfv1.PtPrayMaterialInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptPrayMaterialInfo)
	assert.Equal(t, int32(0), ptPrayMaterialInfo.Error)
	assert.Equal(t, int32(1), ptPrayMaterialInfo.Index)
}

// 测试生物相关消息
func testBatch79CreatureMessages(t *testing.T) {
	resCreatureRandomOptionLock := &dnfv1.ResCreatureRandomOptionLock{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCreatureRandomOptionLock)
	assert.Equal(t, int32(0), resCreatureRandomOptionLock.Error)
	assert.Equal(t, int32(1), resCreatureRandomOptionLock.Index)

	reqCreatureSkillUpgrade := &dnfv1.ReqCreatureSkillUpgrade{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqCreatureSkillUpgrade)
	assert.Equal(t, int32(0), reqCreatureSkillUpgrade.Error)
	assert.Equal(t, int32(1), reqCreatureSkillUpgrade.Index)
}

// 测试物品相关消息
func testBatch79ItemMessages(t *testing.T) {
	reqItemProductionInfo := &dnfv1.ReqItemProductionInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqItemProductionInfo)
	assert.Equal(t, int32(0), reqItemProductionInfo.Error)
	assert.Equal(t, int32(1), reqItemProductionInfo.Index)

	ptItemTimebox := &dnfv1.PtItemTimebox{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptItemTimebox)
	assert.Equal(t, int32(0), ptItemTimebox.Error)
	assert.Equal(t, int32(1), ptItemTimebox.Index)
}

// 测试交互相关消息
func testBatch79InteractionMessages(t *testing.T) {
	resInteractionMenu := &dnfv1.ResInteractionMenu{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resInteractionMenu)
	assert.Equal(t, int32(0), resInteractionMenu.Error)
	assert.Equal(t, int32(1), resInteractionMenu.Index)
}

// 测试公会相关消息
func testBatch79GuildMessages(t *testing.T) {
	reqGuildAdMessage := &dnfv1.ReqGuildAdMessage{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqGuildAdMessage)
	assert.Equal(t, int32(0), reqGuildAdMessage.Error)
	assert.Equal(t, int32(1), reqGuildAdMessage.Index)

	notifyGuildProhibit := &dnfv1.NotifyGuildProhibit{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, notifyGuildProhibit)
	assert.Equal(t, int32(0), notifyGuildProhibit.Error)
	assert.Equal(t, int32(1), notifyGuildProhibit.Index)
}

// 测试攻击小队相关消息
func testBatch79AttackSquadMessages(t *testing.T) {
	reqAttackSquadLeave := &dnfv1.ReqAttackSquadLeave{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadLeave)
	assert.Equal(t, int32(0), reqAttackSquadLeave.Error)
	assert.Equal(t, int32(1), reqAttackSquadLeave.Index)

	reqAttackSquadJoin := &dnfv1.ReqAttackSquadJoin{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadJoin)
	assert.Equal(t, int32(0), reqAttackSquadJoin.Error)
	assert.Equal(t, int32(1), reqAttackSquadJoin.Index)

	reqAttackSquadDetailSearch := &dnfv1.ReqAttackSquadDetailSearch{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadDetailSearch)
	assert.Equal(t, int32(0), reqAttackSquadDetailSearch.Error)
	assert.Equal(t, int32(1), reqAttackSquadDetailSearch.Index)

	reqAttackSquadDetailInfo := &dnfv1.ReqAttackSquadDetailInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAttackSquadDetailInfo)
	assert.Equal(t, int32(0), reqAttackSquadDetailInfo.Error)
	assert.Equal(t, int32(1), reqAttackSquadDetailInfo.Index)
}

// 测试邮件相关消息
func testBatch79MailMessages(t *testing.T) {
	resMailDistributeAccountToChraracter := &dnfv1.ResMailDistributeAccountToChraracter{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMailDistributeAccountToChraracter)
	assert.Equal(t, int32(0), resMailDistributeAccountToChraracter.Error)
	assert.Equal(t, int32(1), resMailDistributeAccountToChraracter.Index)
}

// 测试历史遗址相关消息
func testBatch79HistoricsiteMessages(t *testing.T) {
	ptHistoricsitePoint := &dnfv1.PtHistoricsitePoint{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptHistoricsitePoint)
	assert.Equal(t, int32(0), ptHistoricsitePoint.Error)
	assert.Equal(t, int32(1), ptHistoricsitePoint.Index)

	ptHistoricsiteCapture := &dnfv1.PtHistoricsiteCapture{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptHistoricsiteCapture)
	assert.Equal(t, int32(0), ptHistoricsiteCapture.Error)
	assert.Equal(t, int32(1), ptHistoricsiteCapture.Index)

	resHistoricsitePoint := &dnfv1.ResHistoricsitePoint{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHistoricsitePoint)
	assert.Equal(t, int32(0), resHistoricsitePoint.Error)
	assert.Equal(t, int32(1), resHistoricsitePoint.Index)
}

// 测试商店相关消息
func testBatch79ShopMessages(t *testing.T) {
	ptShopTabReset := &dnfv1.PtShopTabReset{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptShopTabReset)
	assert.Equal(t, int32(0), ptShopTabReset.Error)
	assert.Equal(t, int32(1), ptShopTabReset.Index)

	ptShopBuyCount := &dnfv1.PtShopBuyCount{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptShopBuyCount)
	assert.Equal(t, int32(0), ptShopBuyCount.Error)
	assert.Equal(t, int32(1), ptShopBuyCount.Index)
}

// 测试突袭相关消息
func testBatch79RaidMessages(t *testing.T) {
	resRaidSyncPhase := &dnfv1.ResRaidSyncPhase{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRaidSyncPhase)
	assert.Equal(t, int32(0), resRaidSyncPhase.Error)
	assert.Equal(t, int32(1), resRaidSyncPhase.Index)

	ptResRaidNodeVariable := &dnfv1.PtResRaidNodeVariable{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptResRaidNodeVariable)
	assert.Equal(t, int32(0), ptResRaidNodeVariable.Error)
	assert.Equal(t, int32(1), ptResRaidNodeVariable.Index)

	resRaidRetryVoteResult := &dnfv1.ResRaidRetryVoteResult{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resRaidRetryVoteResult)
	assert.Equal(t, int32(0), resRaidRetryVoteResult.Error)
	assert.Equal(t, int32(1), resRaidRetryVoteResult.Index)
}

// 测试队伍接受相关消息
func testBatch79PartyAcceptMessages(t *testing.T) {
	resHalfOpenPartyAccept := &dnfv1.ResHalfOpenPartyAccept{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resHalfOpenPartyAccept)
	assert.Equal(t, int32(0), resHalfOpenPartyAccept.Error)
	assert.Equal(t, int32(1), resHalfOpenPartyAccept.Index)
}

// 测试社区相关消息
func testBatch79CommunityMessages(t *testing.T) {
	resCommunityGiftSend := &dnfv1.ResCommunityGiftSend{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCommunityGiftSend)
	assert.Equal(t, int32(0), resCommunityGiftSend.Error)
	assert.Equal(t, int32(1), resCommunityGiftSend.Index)

	resCommunityGiftSendLogList := &dnfv1.ResCommunityGiftSendLogList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resCommunityGiftSendLogList)
	assert.Equal(t, int32(0), resCommunityGiftSendLogList.Error)
	assert.Equal(t, int32(1), resCommunityGiftSendLogList.Index)
}

// 测试技能相关消息
func testBatch79SkillMessages(t *testing.T) {
	reqSkillSet := &dnfv1.ReqSkillSet{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqSkillSet)
	assert.Equal(t, int32(0), reqSkillSet.Error)
	assert.Equal(t, int32(1), reqSkillSet.Index)
}

// 测试时间盒相关消息
func testBatch79TimeboxMessages(t *testing.T) {
	ptItemTimebox := &dnfv1.PtItemTimebox{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptItemTimebox)
	assert.Equal(t, int32(0), ptItemTimebox.Error)
	assert.Equal(t, int32(1), ptItemTimebox.Index)
}

// 测试扇区相关消息
func testBatch79SectorMessages(t *testing.T) {
	sectorListRes := &dnfv1.SectorListRes{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, sectorListRes)
	assert.Equal(t, int32(0), sectorListRes.Error)
	assert.Equal(t, int32(1), sectorListRes.Index)
}

// 测试怪物击杀相关消息
func testBatch79MonsterkillMessages(t *testing.T) {
	ptMonsterkillLastShot := &dnfv1.PtMonsterkillLastShot{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMonsterkillLastShot)
	assert.Equal(t, int32(0), ptMonsterkillLastShot.Error)
	assert.Equal(t, int32(1), ptMonsterkillLastShot.Index)
}

// 测试战斗联赛相关消息
func testBatch79BattleleagueMessages(t *testing.T) {
	resBattleleagueNextDungeon := &dnfv1.ResBattleleagueNextDungeon{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resBattleleagueNextDungeon)
	assert.Equal(t, int32(0), resBattleleagueNextDungeon.Error)
	assert.Equal(t, int32(1), resBattleleagueNextDungeon.Index)
}

// 测试冒险相关消息
func testBatch79AdventureMessages(t *testing.T) {
	reqAdventureUnionSubdueReward := &dnfv1.ReqAdventureUnionSubdueReward{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqAdventureUnionSubdueReward)
	assert.Equal(t, int32(0), reqAdventureUnionSubdueReward.Error)
	assert.Equal(t, int32(1), reqAdventureUnionSubdueReward.Index)
}

// 测试多人游戏相关消息
func testBatch79MultiPlayMessages(t *testing.T) {
	resMultiPlayDungeonSelectRewardCard := &dnfv1.ResMultiPlayDungeonSelectRewardCard{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resMultiPlayDungeonSelectRewardCard)
	assert.Equal(t, int32(0), resMultiPlayDungeonSelectRewardCard.Error)
	assert.Equal(t, int32(1), resMultiPlayDungeonSelectRewardCard.Index)

	resReturnToTownAtMultiPlay := &dnfv1.ResReturnToTownAtMultiPlay{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resReturnToTownAtMultiPlay)
	assert.Equal(t, int32(0), resReturnToTownAtMultiPlay.Error)
	assert.Equal(t, int32(1), resReturnToTownAtMultiPlay.Index)

	reqReturnToTownAtMultiPlay := &dnfv1.ReqReturnToTownAtMultiPlay{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, reqReturnToTownAtMultiPlay)
	assert.Equal(t, int32(0), reqReturnToTownAtMultiPlay.Error)
	assert.Equal(t, int32(1), reqReturnToTownAtMultiPlay.Index)
}

// 测试副本相关消息
func testBatch79DungeonMessages(t *testing.T) {
	resDungeonMonsterDie := &dnfv1.ResDungeonMonsterDie{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resDungeonMonsterDie)
	assert.Equal(t, int32(0), resDungeonMonsterDie.Error)
	assert.Equal(t, int32(1), resDungeonMonsterDie.Index)
}

// 测试街机PVP相关消息
func testBatch79ArcadeMessages(t *testing.T) {
	resArcadePvpRewardInfo := &dnfv1.ResArcadePvpRewardInfo{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resArcadePvpRewardInfo)
	assert.Equal(t, int32(0), resArcadePvpRewardInfo.Error)
	assert.Equal(t, int32(1), resArcadePvpRewardInfo.Index)
}

// 测试成就相关消息
func testBatch79AchievementMessages(t *testing.T) {
	resAchievementList := &dnfv1.ResAchievementList{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resAchievementList)
	assert.Equal(t, int32(0), resAchievementList.Error)
	assert.Equal(t, int32(1), resAchievementList.Index)
}

// 测试金钱相关消息
func testBatch79MoneyMessages(t *testing.T) {
	ptMoneyItem := &dnfv1.PtMoneyItem{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, ptMoneyItem)
	assert.Equal(t, int32(0), ptMoneyItem.Error)
	assert.Equal(t, int32(1), ptMoneyItem.Index)
}

// 测试梦境迷宫相关消息
func testBatch79DreamMazeMessages(t *testing.T) {
	resDreamMazeHpSync := &dnfv1.ResDreamMazeHpSync{
		Error: 0,
		Index: 1,
	}
	assert.NotNil(t, resDreamMazeHpSync)
	assert.Equal(t, int32(0), resDreamMazeHpSync.Error)
	assert.Equal(t, int32(1), resDreamMazeHpSync.Index)
}
