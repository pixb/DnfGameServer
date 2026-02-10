package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch60Migration 测试第60批迁移的消息类型
func TestBatch60Migration(t *testing.T) {
	t.Run("UserInfo", testUserInfoMessages)
	t.Run("Skill", testSkillMessages)
	t.Run("Stage", testStageMessages)
	t.Run("Dungeon", testBatch60DungeonMessages)
	t.Run("Chat", testChatMessages)
	t.Run("Item", testItemMessages)
	t.Run("System", testBatch60SystemMessages)
	t.Run("Tutorial", testTutorialMessages)
	t.Run("Sector", testSectorMessages)
}

// 测试用户信息相关消息
func testUserInfoMessages(t *testing.T) {
	// 测试UserInfo
	userInfo := &dnfv1.UserInfo{
		Charguid:       1234567890,
		Job:            1,
		Growtype:       2,
		Secgrowtype:    3,
		TeamType:       1,
		World:          1,
		Level:          95,
		Name:           "Test Player",
		ProfileUrl:     "http://example.com/avatar.jpg",
		ProfileName:    "Avatar",
		CharacterFrame: 1,
		Rank:           1,
	}
	assert.NotNil(t, userInfo)
	assert.Equal(t, uint64(1234567890), userInfo.Charguid)
	assert.Equal(t, int32(1), userInfo.Job)
	assert.Equal(t, int32(2), userInfo.Growtype)
	assert.Equal(t, int32(3), userInfo.Secgrowtype)
	assert.Equal(t, int32(1), userInfo.TeamType)
	assert.Equal(t, int32(1), userInfo.World)
	assert.Equal(t, int32(95), userInfo.Level)
	assert.Equal(t, "Test Player", userInfo.Name)
	assert.Equal(t, "http://example.com/avatar.jpg", userInfo.ProfileUrl)
	assert.Equal(t, "Avatar", userInfo.ProfileName)
	assert.Equal(t, int32(1), userInfo.CharacterFrame)
	assert.Equal(t, int32(1), userInfo.Rank)

	// 测试ResUserStatus
	resUserStatus := &dnfv1.ResUserStatus{
		Status:  0,
		Message: "Success",
	}
	assert.NotNil(t, resUserStatus)
	assert.Equal(t, int32(0), resUserStatus.Status)
	assert.Equal(t, "Success", resUserStatus.Message)

	// 测试ResUserReport
	resUserReport := &dnfv1.ResUserReport{
		Error:   0,
		Message: "Reported successfully",
	}
	assert.NotNil(t, resUserReport)
	assert.Equal(t, int32(0), resUserReport.Error)
	assert.Equal(t, "Reported successfully", resUserReport.Message)

	// 测试UpdateAntievilScore
	updateAntievilScore := &dnfv1.UpdateAntievilScore{
		Score: 100,
	}
	assert.NotNil(t, updateAntievilScore)
	assert.Equal(t, int32(100), updateAntievilScore.Score)

	// 测试UpdateAntievilScore1
	updateAntievilScore1 := &dnfv1.UpdateAntievilScore1{
		Score: 100,
	}
	assert.NotNil(t, updateAntievilScore1)
	assert.Equal(t, int32(100), updateAntievilScore1.Score)

	// 测试ResTencentCreditscoreInfo
	resTencentCreditscoreInfo := &dnfv1.ResTencentCreditscoreInfo{
		Score:   850,
		Message: "Good credit",
	}
	assert.NotNil(t, resTencentCreditscoreInfo)
	assert.Equal(t, int32(850), resTencentCreditscoreInfo.Score)
	assert.Equal(t, "Good credit", resTencentCreditscoreInfo.Message)

	// 测试ResTitleList
	resTitleList := &dnfv1.ResTitleList{
		Titles: []int32{1, 2, 3},
	}
	assert.NotNil(t, resTitleList)
	assert.Len(t, resTitleList.Titles, 3)
	assert.Equal(t, int32(1), resTitleList.Titles[0])

	// 测试ResSeasonPassInfo
	resSeasonPassInfo := &dnfv1.ResSeasonPassInfo{
		Level: 10,
		Exp:   500,
	}
	assert.NotNil(t, resSeasonPassInfo)
	assert.Equal(t, int32(10), resSeasonPassInfo.Level)
	assert.Equal(t, int32(500), resSeasonPassInfo.Exp)

	// 测试ResVerificationAuth
	resVerificationAuth := &dnfv1.ResVerificationAuth{
		Error: 0,
		Url:   "http://example.com/verify",
	}
	assert.NotNil(t, resVerificationAuth)
	assert.Equal(t, int32(0), resVerificationAuth.Error)
	assert.Equal(t, "http://example.com/verify", resVerificationAuth.Url)

	// 测试ResVerificationFinish
	resVerificationFinish := &dnfv1.ResVerificationFinish{
		Error:   0,
		Message: "Verification completed",
	}
	assert.NotNil(t, resVerificationFinish)
	assert.Equal(t, int32(0), resVerificationFinish.Error)
	assert.Equal(t, "Verification completed", resVerificationFinish.Message)
}

// 测试技能相关消息
func testSkillMessages(t *testing.T) {
	// 测试ResSkillInfo
	resSkillInfo := &dnfv1.ResSkillInfo{
		Error:  0,
		Skills: []int32{1, 2, 3},
	}
	assert.NotNil(t, resSkillInfo)
	assert.Equal(t, int32(0), resSkillInfo.Error)
	assert.Len(t, resSkillInfo.Skills, 3)

	// 测试ResSkillInit
	resSkillInit := &dnfv1.ResSkillInit{
		Error:  0,
		Skills: []int32{1, 2, 3},
	}
	assert.NotNil(t, resSkillInit)
	assert.Equal(t, int32(0), resSkillInit.Error)
	assert.Len(t, resSkillInit.Skills, 3)

	// 测试ResSkillSlot
	resSkillSlot := &dnfv1.ResSkillSlot{
		Error: 0,
		Slots: []int32{1, 2, 3},
	}
	assert.NotNil(t, resSkillSlot)
	assert.Equal(t, int32(0), resSkillSlot.Error)
	assert.Len(t, resSkillSlot.Slots, 3)

	// 测试ResSkillPageSet
	resSkillPageSet := &dnfv1.ResSkillPageSet{
		Error: 0,
		Page:  1,
	}
	assert.NotNil(t, resSkillPageSet)
	assert.Equal(t, int32(0), resSkillPageSet.Error)
	assert.Equal(t, int32(1), resSkillPageSet.Page)

	// 测试PtSkill
	ptSkill := &dnfv1.PtSkill{
		Id:    1,
		Level: 10,
	}
	assert.NotNil(t, ptSkill)
	assert.Equal(t, int32(1), ptSkill.Id)
	assert.Equal(t, int32(10), ptSkill.Level)

	// 测试PtAllSkillSlot
	ptAllSkillSlot := &dnfv1.PtAllSkillSlot{
		Slots: []int32{1, 2, 3},
	}
	assert.NotNil(t, ptAllSkillSlot)
	assert.Len(t, ptAllSkillSlot.Slots, 3)
}

// 测试舞台相关消息
func testStageMessages(t *testing.T) {
	// 测试ResStageInfo
	resStageInfo := &dnfv1.ResStageInfo{
		Error: 0,
		Name:  "Test Stage",
	}
	assert.NotNil(t, resStageInfo)
	assert.Equal(t, int32(0), resStageInfo.Error)
	assert.Equal(t, "Test Stage", resStageInfo.Name)

	// 测试ResStageClear
	resStageClear := &dnfv1.ResStageClear{
		Error: 0,
		Stars: 3,
	}
	assert.NotNil(t, resStageClear)
	assert.Equal(t, int32(0), resStageClear.Error)
	assert.Equal(t, int32(3), resStageClear.Stars)
}

// 测试副本相关消息
func testBatch60DungeonMessages(t *testing.T) {
	// 测试ResDungeonInfo
	resDungeonInfo := &dnfv1.ResDungeonInfo{
		Error: 0,
		Name:  "Test Dungeon",
	}
	assert.NotNil(t, resDungeonInfo)
	assert.Equal(t, int32(0), resDungeonInfo.Error)
	assert.Equal(t, "Test Dungeon", resDungeonInfo.Name)

	// 测试ResDungeonClear
	resDungeonClear := &dnfv1.ResDungeonClear{
		Error: 0,
		Grade: 1,
	}
	assert.NotNil(t, resDungeonClear)
	assert.Equal(t, int32(0), resDungeonClear.Error)
	assert.Equal(t, int32(1), resDungeonClear.Grade)

	// 测试ResTransmissionItem
	resTransmissionItem := &dnfv1.ResTransmissionItem{
		Error: 0,
		Count: 5,
	}
	assert.NotNil(t, resTransmissionItem)
	assert.Equal(t, int32(0), resTransmissionItem.Error)
	assert.Equal(t, int32(5), resTransmissionItem.Count)

	// 测试ResRequestToReEnterDungeon
	resRequestToReEnterDungeon := &dnfv1.ResRequestToReEnterDungeon{
		Error:   0,
		Allowed: true,
	}
	assert.NotNil(t, resRequestToReEnterDungeon)
	assert.Equal(t, int32(0), resRequestToReEnterDungeon.Error)
	assert.Equal(t, true, resRequestToReEnterDungeon.Allowed)
}

// 测试聊天相关消息
func testChatMessages(t *testing.T) {
	// 测试SendChat
	sendChat := &dnfv1.SendChat{
		Content: "Hello World",
		Type:    1,
	}
	assert.NotNil(t, sendChat)
	assert.Equal(t, "Hello World", sendChat.Content)
	assert.Equal(t, int32(1), sendChat.Type)

	// 测试ResTownChat
	resTownChat := &dnfv1.ResTownChat{
		Error:   0,
		Message: "Hello World",
	}
	assert.NotNil(t, resTownChat)
	assert.Equal(t, int32(0), resTownChat.Error)
	assert.Equal(t, "Hello World", resTownChat.Message)

	// 测试ResTownChatList
	resTownChatList := &dnfv1.ResTownChatList{
		Messages: []string{"Hello", "World"},
	}
	assert.NotNil(t, resTownChatList)
	assert.Len(t, resTownChatList.Messages, 2)

	// 测试SessionAuthChat
	sessionAuthChat := &dnfv1.SessionAuthChat{
		Token: "test-token",
	}
	assert.NotNil(t, sessionAuthChat)
	assert.Equal(t, "test-token", sessionAuthChat.Token)

	// 测试WatchRoomData
	watchRoomData := &dnfv1.WatchRoomData{
		RoomId:   1,
		RoomName: "Test Room",
	}
	assert.NotNil(t, watchRoomData)
	assert.Equal(t, int32(1), watchRoomData.RoomId)
	assert.Equal(t, "Test Room", watchRoomData.RoomName)
}

// 测试物品相关消息
func testItemMessages(t *testing.T) {
	// 测试SendItemGuidInfo
	sendItemGuidInfo := &dnfv1.SendItemGuidInfo{
		Guid: 1234567890,
	}
	assert.NotNil(t, sendItemGuidInfo)
	assert.Equal(t, uint64(1234567890), sendItemGuidInfo.Guid)

	// 测试SendItemInfo
	sendItemInfo := &dnfv1.SendItemInfo{
		Id:    1,
		Count: 5,
	}
	assert.NotNil(t, sendItemInfo)
	assert.Equal(t, int32(1), sendItemInfo.Id)
	assert.Equal(t, int32(5), sendItemInfo.Count)
}

// 测试系统相关消息
func testBatch60SystemMessages(t *testing.T) {
	// 测试Subsystem
	subsystem := &dnfv1.Subsystem{
		Id:   1,
		Name: "Test Subsystem",
	}
	assert.NotNil(t, subsystem)
	assert.Equal(t, int32(1), subsystem.Id)
	assert.Equal(t, "Test Subsystem", subsystem.Name)

	// 测试StreamData (已在其他文件中定义)
	// 测试ResServerCheckup
	resServerCheckup := &dnfv1.ResServerCheckup{
		Status:  0,
		Message: "Server is online",
	}
	assert.NotNil(t, resServerCheckup)
	assert.Equal(t, int32(0), resServerCheckup.Status)
	assert.Equal(t, "Server is online", resServerCheckup.Message)
}

// 测试教程相关消息
func testTutorialMessages(t *testing.T) {
	// 测试TypeTutorialList
	typeTutorialList := &dnfv1.TypeTutorialList{
		Tutorials: []int32{1, 2, 3},
	}
	assert.NotNil(t, typeTutorialList)
	assert.Len(t, typeTutorialList.Tutorials, 3)

	// 测试ResTutorialList
	resTutorialList := &dnfv1.ResTutorialList{
		Tutorials: []int32{1, 2, 3},
	}
	assert.NotNil(t, resTutorialList)
	assert.Len(t, resTutorialList.Tutorials, 3)

	// 测试ResTutorialSave
	resTutorialSave := &dnfv1.ResTutorialSave{
		Error:      0,
		TutorialId: 1,
	}
	assert.NotNil(t, resTutorialSave)
	assert.Equal(t, int32(0), resTutorialSave.Error)
	assert.Equal(t, int32(1), resTutorialSave.TutorialId)
}

// 测试区域相关消息
func testSectorMessages(t *testing.T) {
	// 测试SectorList
	sectorList := &dnfv1.SectorList{
		Sectors: []int32{1, 2, 3},
	}
	assert.NotNil(t, sectorList)
	assert.Len(t, sectorList.Sectors, 3)

	// 测试SectorInfo
	sectorInfo := &dnfv1.SectorInfo{
		Id:   1,
		Name: "Test Sector",
	}
	assert.NotNil(t, sectorInfo)
	assert.Equal(t, int32(1), sectorInfo.Id)
	assert.Equal(t, "Test Sector", sectorInfo.Name)
}
