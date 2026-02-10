package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch58Migration 测试第58批迁移的消息类型
func TestBatch58Migration(t *testing.T) {
	t.Run("BoardGameAndChivalry", testBoardGameAndChivalryMessages)
	t.Run("DungeonAndClient", testDungeonAndClientMessages)
	t.Run("ClosenessAndCollection", testClosenessAndCollectionMessages)
	t.Run("ComebackAndComment", testComebackAndCommentMessages)
	t.Run("CommunityGift", testCommunityGiftMessages)
	t.Run("QuestAndConsume", testQuestAndConsumeMessages)
	t.Run("ControlAndCrack", testControlAndCrackMessages)
	t.Run("Creature", testCreatureMessages)
	t.Run("CubeAndCurrency", testCubeAndCurrencyMessages)
	t.Run("CustomPvpAndDamage", testCustomPvpAndDamageMessages)
}

// 测试棋盘游戏和骑士精神相关消息
func testBoardGameAndChivalryMessages(t *testing.T) {
	// 测试BoardGameMoveResult
	boardGameMoveResult := &dnfv1.BoardGameMoveResult{}
	assert.NotNil(t, boardGameMoveResult)

	// 测试ChivalryCurrencyDailyGain
	chivalryCurrencyDailyGain := &dnfv1.ChivalryCurrencyDailyGain{
		Type:  1,
		Index: 1,
		Count: 100,
	}
	assert.NotNil(t, chivalryCurrencyDailyGain)
	assert.Equal(t, int32(1), chivalryCurrencyDailyGain.Type)
	assert.Equal(t, int32(1), chivalryCurrencyDailyGain.Index)
	assert.Equal(t, int32(100), chivalryCurrencyDailyGain.Count)

	// 测试ChivalryMission
	chivalryMission := &dnfv1.ChivalryMission{
		Type:  1,
		Value: 100,
		Count: 5,
	}
	assert.NotNil(t, chivalryMission)
	assert.Equal(t, dnfv1.ChivalryMissionType(1), chivalryMission.Type)
	assert.Equal(t, int32(100), chivalryMission.Value)
	assert.Equal(t, int32(5), chivalryMission.Count)
}

// 测试副本和客户端相关消息
func testDungeonAndClientMessages(t *testing.T) {
	// 测试ClearDungeonInfo
	clearDungeonInfo := &dnfv1.ClearDungeonInfo{
		Floor:             1,
		Grade:             2,
		Score:             1000,
		Step:              3,
		Shortestcleartime: 1234567890,
	}
	assert.NotNil(t, clearDungeonInfo)
	assert.Equal(t, int32(1), clearDungeonInfo.Floor)
	assert.Equal(t, int32(2), clearDungeonInfo.Grade)
	assert.Equal(t, int32(1000), clearDungeonInfo.Score)
	assert.Equal(t, int32(3), clearDungeonInfo.Step)
	assert.Equal(t, int64(1234567890), clearDungeonInfo.Shortestcleartime)

	// 测试ClearDungeonInfoList
	clearDungeonInfoList := &dnfv1.ClearDungeonInfoList{
		Index: 1,
	}
	assert.NotNil(t, clearDungeonInfoList)
	assert.Equal(t, int32(1), clearDungeonInfoList.Index)

	// 测试ClearDungeonStarreward
	clearDungeonStarreward := &dnfv1.ClearDungeonStarreward{
		Count: 3,
		Type:  1,
	}
	assert.NotNil(t, clearDungeonStarreward)
	assert.Equal(t, int32(3), clearDungeonStarreward.Count)
	assert.Equal(t, int32(1), clearDungeonStarreward.Type)

	// 测试Clientinfo
	clientinfo := &dnfv1.Clientinfo{
		PlatID:         1,
		DeviceSoft:     "Android",
		DeviceHard:     "Pixel",
		TeleOper:       "China Mobile",
		Network:        "4G",
		ScrWidth:       1920,
		ScrHeight:      1080,
		Density:        2.0,
		Cpu:            "Snapdragon",
		Memory:         8192,
		GlRender:       "OpenGL ES 3.2",
		GlVersion:      "OpenGL ES 3.2",
		DeviceID:       "1234567890",
		ClientIP:       "192.168.1.1",
		Type:           1,
		OAID:           "test_oaid",
		BuildType:      1,
		DeviceLanguage: "zh-CN",
		DeviceUTC:      8,
	}
	assert.NotNil(t, clientinfo)
	assert.Equal(t, uint32(1), clientinfo.PlatID)
	assert.Equal(t, "Android", clientinfo.DeviceSoft)
}

// 测试亲密度和收集相关消息
func testClosenessAndCollectionMessages(t *testing.T) {
	// 测试ClosenessResultInfo
	closenessResultInfo := &dnfv1.ClosenessResultInfo{
		Fguid:          1234567890,
		Increasescore:  10,
		Closenessscore: 100,
	}
	assert.NotNil(t, closenessResultInfo)
	assert.Equal(t, uint64(1234567890), closenessResultInfo.Fguid)
	assert.Equal(t, int32(10), closenessResultInfo.Increasescore)
	assert.Equal(t, int32(100), closenessResultInfo.Closenessscore)

	// 测试CollectionInfo
	collectionInfo := &dnfv1.CollectionInfo{
		Index:  1,
		Reward: true,
	}
	assert.NotNil(t, collectionInfo)
	assert.Equal(t, int32(1), collectionInfo.Index)
	assert.Equal(t, true, collectionInfo.Reward)

	// 测试CollectionItem
	collectionItem := &dnfv1.CollectionItem{
		Index:       1,
		Regist:      true,
		Reward:      true,
		Rewardindex: 1,
	}
	assert.NotNil(t, collectionItem)
	assert.Equal(t, int32(1), collectionItem.Index)
	assert.Equal(t, true, collectionItem.Regist)
	assert.Equal(t, true, collectionItem.Reward)
	assert.Equal(t, int32(1), collectionItem.Rewardindex)
}

// 测试回归和评论相关消息
func testComebackAndCommentMessages(t *testing.T) {
	// 测试CombackChannelInfo
	combackChannelInfo := &dnfv1.CombackChannelInfo{
		Charguid: 1234567890,
		Channel:  1,
		Ip:       "192.168.1.1",
	}
	assert.NotNil(t, combackChannelInfo)
	assert.Equal(t, uint64(1234567890), combackChannelInfo.Charguid)
	assert.Equal(t, int32(1), combackChannelInfo.Channel)
	assert.Equal(t, "192.168.1.1", combackChannelInfo.Ip)

	// 测试ComebackMatchingUsers
	comebackMatchingUsers := &dnfv1.ComebackMatchingUsers{
		Channel:  1,
		Ip:       "192.168.1.1",
		Port:     8080,
		World:    1,
		Charguid: 1234567890,
	}
	assert.NotNil(t, comebackMatchingUsers)
	assert.Equal(t, int32(1), comebackMatchingUsers.Channel)
	assert.Equal(t, "192.168.1.1", comebackMatchingUsers.Ip)
	assert.Equal(t, uint32(8080), comebackMatchingUsers.Port)
	assert.Equal(t, int32(1), comebackMatchingUsers.World)
	assert.Equal(t, uint64(1234567890), comebackMatchingUsers.Charguid)

	// 测试Comment
	comment := &dnfv1.Comment{
		Writerguid:     1234567890,
		Name:           "Test User",
		Comment:        "Test comment",
		Writetime:      1234567890,
		Growtype:       1,
		Secondgrowtype: 2,
		Job:            3,
	}
	assert.NotNil(t, comment)
	assert.Equal(t, uint64(1234567890), comment.Writerguid)
	assert.Equal(t, "Test User", comment.Name)
	assert.Equal(t, "Test comment", comment.Comment)
	assert.Equal(t, uint64(1234567890), comment.Writetime)
	assert.Equal(t, int32(1), comment.Growtype)
	assert.Equal(t, int32(2), comment.Secondgrowtype)
	assert.Equal(t, int32(3), comment.Job)
}

// 测试社区礼物相关消息
func testCommunityGiftMessages(t *testing.T) {
	// 测试CommunityGiftDeleteLog
	communityGiftDeleteLog := &dnfv1.CommunityGiftDeleteLog{
		Guid: 1234567890,
	}
	assert.NotNil(t, communityGiftDeleteLog)
	assert.Equal(t, uint64(1234567890), communityGiftDeleteLog.Guid)

	// 测试CommunityGiftGuideInfo
	communityGiftGuideInfo := &dnfv1.CommunityGiftGuideInfo{
		Guid: 1234567890,
	}
	assert.NotNil(t, communityGiftGuideInfo)
	assert.Equal(t, uint64(1234567890), communityGiftGuideInfo.Guid)

	// 测试CommunityGiftInfo
	communityGiftInfo := &dnfv1.CommunityGiftInfo{
		Index: 1,
		Count: 1,
	}
	assert.NotNil(t, communityGiftInfo)
	assert.Equal(t, int32(1), communityGiftInfo.Index)
	assert.Equal(t, int32(1), communityGiftInfo.Count)

	// 测试CommunityGiftReceiverInfo
	communityGiftReceiverInfo := &dnfv1.CommunityGiftReceiverInfo{
		Name:           "Test Receiver",
		Level:          95,
		Job:            1,
		Growtype:       2,
		Secondgrowtype: 3,
		Itemindex:      1001,
		Count:          5,
		Charm:          1000,
		Date:           1234567890,
		Expiretime:     1234567890 + 86400,
		Charguid:       1234567890,
		Closenessscore: 50,
		Check:          1,
		Msg:            "Test message",
		Index:          "test_index",
		Packagingindex: 1,
		Characterframe: 1,
	}
	assert.NotNil(t, communityGiftReceiverInfo)
	assert.Equal(t, "Test Receiver", communityGiftReceiverInfo.Name)
	assert.Equal(t, int32(95), communityGiftReceiverInfo.Level)
	assert.Equal(t, int32(1), communityGiftReceiverInfo.Job)
	assert.Equal(t, uint64(1234567890), communityGiftReceiverInfo.Charguid)

	// 测试CommunityGiftSenderInfo
	communityGiftSenderInfo := &dnfv1.CommunityGiftSenderInfo{
		Name:           "Test Sender",
		Level:          95,
		Job:            1,
		Growtype:       2,
		Secondgrowtype: 3,
		Itemindex:      1001,
		Count:          5,
		Fame:           1000,
		Date:           1234567890,
		Expiretime:     1234567890 + 86400,
		Charguid:       1234567890,
		Closenessscore: 50,
		Index:          "test_index",
		Openid:         "test_openid",
		Characterframe: 1,
	}
	assert.NotNil(t, communityGiftSenderInfo)
	assert.Equal(t, "Test Sender", communityGiftSenderInfo.Name)
	assert.Equal(t, int32(95), communityGiftSenderInfo.Level)
	assert.Equal(t, int32(1), communityGiftSenderInfo.Job)
	assert.Equal(t, uint64(1234567890), communityGiftSenderInfo.Charguid)

	// 测试CommunityGiftUntieInfo
	communityGiftUntieInfo := &dnfv1.CommunityGiftUntieInfo{
		Index:          "test_index",
		Charguid:       1234567890,
		Name:           "Test User",
		Level:          95,
		Job:            1,
		Growtype:       2,
		Secondgrowtype: 3,
		Itemindex:      1001,
		Count:          5,
		Charm:          1000,
		Closenessscore: 50,
		Date:           1234567890,
		Check:          1,
		Msg:            "Test message",
	}
	assert.NotNil(t, communityGiftUntieInfo)
	assert.Equal(t, "test_index", communityGiftUntieInfo.Index)
	assert.Equal(t, uint64(1234567890), communityGiftUntieInfo.Charguid)
	assert.Equal(t, "Test User", communityGiftUntieInfo.Name)
}

// 测试任务和消耗相关消息
func testQuestAndConsumeMessages(t *testing.T) {
	// 测试CompleteQuestInfo
	completeQuestInfo := &dnfv1.CompleteQuestInfo{
		Index: 1001,
		Type:  1,
		State: 2,
		Count: 5,
	}
	assert.NotNil(t, completeQuestInfo)
	assert.Equal(t, int32(1001), completeQuestInfo.Index)
	assert.Equal(t, int32(1), completeQuestInfo.Type)
	assert.Equal(t, int32(2), completeQuestInfo.State)
	assert.Equal(t, int32(5), completeQuestInfo.Count)

	// 测试ConsumeItems
	consumeItems := &dnfv1.ConsumeItems{
		Itemindex: 1,
		Count:     5,
	}
	assert.NotNil(t, consumeItems)
	assert.Equal(t, int32(1), consumeItems.Itemindex)
	assert.Equal(t, int32(5), consumeItems.Count)

	// 测试ConsumeList
	consumeList := &dnfv1.ConsumeList{
		Consume: []*dnfv1.Stackable{},
	}
	assert.NotNil(t, consumeList)
	assert.Len(t, consumeList.Consume, 0)

	// 测试ContentsRewardInfo
	contentsRewardInfo := &dnfv1.ContentsRewardInfo{}
	assert.NotNil(t, contentsRewardInfo)
}

// 测试控制和裂缝相关消息
func testControlAndCrackMessages(t *testing.T) {
	// 测试ControlGroupInviteItem
	controlGroupInviteItem := &dnfv1.ControlGroupInviteItem{
		Targetguid: 1234567890,
	}
	assert.NotNil(t, controlGroupInviteItem)
	assert.Equal(t, uint64(1234567890), controlGroupInviteItem.Targetguid)

	// 测试ControlTrainingroomClearinfo
	controlTrainingroomClearinfo := &dnfv1.ControlTrainingroomClearinfo{
		Index: 1,
		State: 2,
	}
	assert.NotNil(t, controlTrainingroomClearinfo)
	assert.Equal(t, int32(1), controlTrainingroomClearinfo.Index)
	assert.Equal(t, int32(2), controlTrainingroomClearinfo.State)

	// 测试Crack
	crack := &dnfv1.Crack{
		Index:      1,
		Count:      5,
		Expiretime: 1234567890,
		Slot:       1,
	}
	assert.NotNil(t, crack)
	assert.Equal(t, int32(1), crack.Index)
	assert.Equal(t, int32(5), crack.Count)
	assert.Equal(t, int64(1234567890), crack.Expiretime)
	assert.Equal(t, int32(1), crack.Slot)
}

// 测试生物相关消息
func testCreatureMessages(t *testing.T) {
	// 测试Creature
	creature := &dnfv1.Creature{}
	assert.NotNil(t, creature)

	// 测试CreatureCommunion
	creatureCommunion := &dnfv1.CreatureCommunion{
		Level:         1,
		Selectedslot:  2,
		Extentionslot: 3,
	}
	assert.NotNil(t, creatureCommunion)
	assert.Equal(t, int32(1), creatureCommunion.Level)
	assert.Equal(t, int32(2), creatureCommunion.Selectedslot)
	assert.Equal(t, int32(3), creatureCommunion.Extentionslot)

	// 测试CreatureErrand
	creatureErrand := &dnfv1.CreatureErrand{
		Level:         1,
		Lastresettime: 1234567890,
	}
	assert.NotNil(t, creatureErrand)
	assert.Equal(t, int32(1), creatureErrand.Level)
	assert.Equal(t, uint64(1234567890), creatureErrand.Lastresettime)

	// 测试CreatureLearnSkillInfo
	creatureLearnSkillInfo := &dnfv1.CreatureLearnSkillInfo{
		Category:   1,
		Skillindex: 1001,
		Skilllevel: 5,
	}
	assert.NotNil(t, creatureLearnSkillInfo)
	assert.Equal(t, int32(1), creatureLearnSkillInfo.Category)
	assert.Equal(t, int32(1001), creatureLearnSkillInfo.Skillindex)
	assert.Equal(t, int32(5), creatureLearnSkillInfo.Skilllevel)

	// 测试CreatureLearnSkillInfos
	creatureLearnSkillInfos := &dnfv1.CreatureLearnSkillInfos{
		SkillInfos: []*dnfv1.CreatureLearnSkillInfo{},
	}
	assert.NotNil(t, creatureLearnSkillInfos)
	assert.Len(t, creatureLearnSkillInfos.SkillInfos, 0)

	// 测试CreatureRandomOption
	creatureRandomOption := &dnfv1.CreatureRandomOption{
		Option: 1,
		Rarity: 2,
		Locked: true,
	}
	assert.NotNil(t, creatureRandomOption)
	assert.Equal(t, int32(1), creatureRandomOption.Option)
	assert.Equal(t, int32(2), creatureRandomOption.Rarity)
	assert.Equal(t, true, creatureRandomOption.Locked)

	// 测试CreatureSkill
	creatureSkill := &dnfv1.CreatureSkill{
		Skillindex: 1001,
		Pairindex:  2,
	}
	assert.NotNil(t, creatureSkill)
	assert.Equal(t, int32(1001), creatureSkill.Skillindex)
	assert.Equal(t, int32(2), creatureSkill.Pairindex)
}

// 测试立方体和货币相关消息
func testCubeAndCurrencyMessages(t *testing.T) {
	// 测试CubeItems
	cubeItems := &dnfv1.CubeItems{
		Itemindex: 1,
		Count:     5,
	}
	assert.NotNil(t, cubeItems)
	assert.Equal(t, int32(1), cubeItems.Itemindex)
	assert.Equal(t, int32(5), cubeItems.Count)

	// 测试CurrencyDailyGain
	currencyDailyGain := &dnfv1.CurrencyDailyGain{
		Index: 1,
		Count: 100,
	}
	assert.NotNil(t, currencyDailyGain)
	assert.Equal(t, int32(1), currencyDailyGain.Index)
	assert.Equal(t, int32(100), currencyDailyGain.Count)

	// 测试CurrencyRewardInfo
	currencyRewardInfo := &dnfv1.CurrencyRewardInfo{
		Currency: []*dnfv1.MoneyItem{},
	}
	assert.NotNil(t, currencyRewardInfo)
	assert.Len(t, currencyRewardInfo.Currency, 0)
}

// 测试自定义PVP和伤害分析相关消息
func testCustomPvpAndDamageMessages(t *testing.T) {
	// 测试CustomPvpData
	customPvpData := &dnfv1.CustomPvpData{
		Type:     1,
		Data:     100,
		Isuse:    1,
		Ishide:   0,
		Charguid: 1234567890,
	}
	assert.NotNil(t, customPvpData)
	assert.Equal(t, int32(1), customPvpData.Type)
	assert.Equal(t, int32(100), customPvpData.Data)
	assert.Equal(t, int32(1), customPvpData.Isuse)
	assert.Equal(t, int32(0), customPvpData.Ishide)
	assert.Equal(t, uint64(1234567890), customPvpData.Charguid)

	// 测试CustomPvpRoomSetting
	customPvpRoomSetting := &dnfv1.CustomPvpRoomSetting{
		Data: []*dnfv1.CustomPvpData{},
	}
	assert.NotNil(t, customPvpRoomSetting)
	assert.Len(t, customPvpRoomSetting.Data, 0)

	// 测试DamageAnalysis
	damageAnalysis := &dnfv1.DamageAnalysis{
		Time:           1.5,
		Skillindex:     1001,
		Damage:         10000,
		Criticaldamage: 15000,
	}
	assert.NotNil(t, damageAnalysis)
	assert.Equal(t, float32(1.5), damageAnalysis.Time)
	assert.Equal(t, int32(1001), damageAnalysis.Skillindex)
	assert.Equal(t, int32(10000), damageAnalysis.Damage)
	assert.Equal(t, int32(15000), damageAnalysis.Criticaldamage)

	// 测试DetailTimeLine
	detailTimeLine := &dnfv1.DetailTimeLine{
		Charguid:      1234567890,
		Index:         1,
		Date:          1234567890,
		Charactername: "Test Character",
	}
	assert.NotNil(t, detailTimeLine)
	assert.Equal(t, uint64(1234567890), detailTimeLine.Charguid)
	assert.Equal(t, int32(1), detailTimeLine.Index)
	assert.Equal(t, int64(1234567890), detailTimeLine.Date)
	assert.Equal(t, "Test Character", detailTimeLine.Charactername)
}
