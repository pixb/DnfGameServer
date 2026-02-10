package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch53Migration 测试批次53的迁移结果
func TestBatch53Migration(t *testing.T) {
	// 测试婚礼系统
	t.Run("WeddingSystem", testBatch53WeddingSystem)
	
	// 测试世界副本系统
	t.Run("WorldRaidSystem", testBatch53WorldRaidSystem)
	
	// 测试观看书签系统
	t.Run("WatchingBookmarkSystem", testBatch53WatchingBookmarkSystem)
	
	// 测试验证系统
	t.Run("VerificationSystem", testBatch53VerificationSystem)
}

// testBatch53WeddingSystem 测试婚礼系统
func testBatch53WeddingSystem(t *testing.T) {
	// 测试WeddingGuestbook
	weddingGuestbook := &dnfv1.WeddingGuestbook{
		WriterGuid:   1234567890,
		WriterName:   "Test Writer",
		WriterJob:    5,
		WriteDate:    1234567890,
		Contents:      "Test guestbook entry",
	}
	assert.NotNil(t, weddingGuestbook)
	assert.Equal(t, uint64(1234567890), weddingGuestbook.WriterGuid)
	assert.Equal(t, "Test Writer", weddingGuestbook.WriterName)
	assert.Equal(t, int32(5), weddingGuestbook.WriterJob)
	assert.Equal(t, int64(1234567890), weddingGuestbook.WriteDate)
	assert.Equal(t, "Test guestbook entry", weddingGuestbook.Contents)
	
	// 测试WeddingInvitation
	weddingInvitation := &dnfv1.WeddingInvitation{
		MarriageGuid:   1234567890,
		InviterGuid:    1234567891,
		InviterName:    "Test Inviter",
		SpouseGuid:     1234567892,
		SpouseName:     "Test Spouse",
		WeddingHallIndex: 1,
		WeddingDate:     20260210,
	}
	assert.NotNil(t, weddingInvitation)
	assert.Equal(t, uint64(1234567890), weddingInvitation.MarriageGuid)
	assert.Equal(t, uint64(1234567891), weddingInvitation.InviterGuid)
	assert.Equal(t, "Test Inviter", weddingInvitation.InviterName)
	assert.Equal(t, uint64(1234567892), weddingInvitation.SpouseGuid)
	assert.Equal(t, "Test Spouse", weddingInvitation.SpouseName)
	assert.Equal(t, int32(1), weddingInvitation.WeddingHallIndex)
	assert.Equal(t, int32(20260210), weddingInvitation.WeddingDate)
	
	// 测试WeddingAttendance
	weddingAttendance := &dnfv1.WeddingAttendance{
		AttendanceGuid: 1234567890,
		AttendanceName: "Test Attendee",
		GGuid:          1234567891,
		ReplyType:      1,
	}
	assert.NotNil(t, weddingAttendance)
	assert.Equal(t, uint64(1234567890), weddingAttendance.AttendanceGuid)
	assert.Equal(t, "Test Attendee", weddingAttendance.AttendanceName)
	assert.Equal(t, uint64(1234567891), weddingAttendance.GGuid)
	assert.Equal(t, int32(1), weddingAttendance.ReplyType)
	
	// 测试WeddingThemeCeremony
	weddingThemeCeremony := &dnfv1.WeddingThemeCeremony{
		Category: 1,
		Index:    2,
	}
	assert.NotNil(t, weddingThemeCeremony)
	assert.Equal(t, int32(1), weddingThemeCeremony.Category)
	assert.Equal(t, int32(2), weddingThemeCeremony.Index)
	
	// 测试WeddingTheme
	weddingTheme := &dnfv1.WeddingTheme{
		WeddingHall: 1,
		Officiant:   2,
		GuestList:   []int32{1, 2, 3},
		Dress:       4,
		CeremonyList: []*dnfv1.WeddingThemeCeremony{
			weddingThemeCeremony,
		},
	}
	assert.NotNil(t, weddingTheme)
	assert.Equal(t, int32(1), weddingTheme.WeddingHall)
	assert.Equal(t, int32(2), weddingTheme.Officiant)
	assert.Len(t, weddingTheme.GuestList, 3)
	assert.Equal(t, int32(4), weddingTheme.Dress)
	assert.Len(t, weddingTheme.CeremonyList, 1)
	
	// 测试WeddingPreparation
	weddingPreparation := &dnfv1.WeddingPreparation{
		CharGuid:       1234567890,
		Theme:          weddingTheme,
		PaymentAmount:   1000,
		PaymentGold:    500,
		PaymentTera:     300,
	}
	assert.NotNil(t, weddingPreparation)
	assert.Equal(t, uint64(1234567890), weddingPreparation.CharGuid)
	assert.NotNil(t, weddingPreparation.Theme)
	assert.Equal(t, int32(1000), weddingPreparation.PaymentAmount)
	assert.Equal(t, int32(500), weddingPreparation.PaymentGold)
	assert.Equal(t, int32(300), weddingPreparation.PaymentTera)
	
	// 测试WeddingMoneygiftRanking
	weddingMoneygiftRanking := &dnfv1.WeddingMoneygiftRanking{
		Guid:           1234567890,
		GrowType:       1,
		SecondGrowType:  2,
		Job:            5,
		Level:          85,
		Name:           "Test Player",
		Rank:           1,
		Score:          1000000,
	}
	assert.NotNil(t, weddingMoneygiftRanking)
	assert.Equal(t, uint64(1234567890), weddingMoneygiftRanking.Guid)
	assert.Equal(t, int32(1), weddingMoneygiftRanking.GrowType)
	assert.Equal(t, int32(2), weddingMoneygiftRanking.SecondGrowType)
	assert.Equal(t, int32(5), weddingMoneygiftRanking.Job)
	assert.Equal(t, int32(85), weddingMoneygiftRanking.Level)
	assert.Equal(t, "Test Player", weddingMoneygiftRanking.Name)
	assert.Equal(t, int64(1), weddingMoneygiftRanking.Rank)
	assert.Equal(t, uint64(1000000), weddingMoneygiftRanking.Score)
}

// testBatch53WorldRaidSystem 测试世界副本系统
func testBatch53WorldRaidSystem(t *testing.T) {
	// 测试WorldRaidInfo
	worldRaidInfo := &dnfv1.WorldRaidInfo{
		DungeonIndex: 100,
		Hp:           5000000,
	}
	assert.NotNil(t, worldRaidInfo)
	assert.Equal(t, int32(100), worldRaidInfo.DungeonIndex)
	assert.Equal(t, uint64(5000000), worldRaidInfo.Hp)
	
	// 测试WorldRaidRanking
	worldRaidRanking := &dnfv1.WorldRaidRanking{
		GrowType:        1,
		SecondGrowType:  2,
		Guid:            1234567890,
		Job:             5,
		Level:           85,
		Name:            "Test Player",
		Rank:            1,
		Score:           1000000,
		CharacterFrame:  10,
	}
	assert.NotNil(t, worldRaidRanking)
	assert.Equal(t, int32(1), worldRaidRanking.GrowType)
	assert.Equal(t, int32(2), worldRaidRanking.SecondGrowType)
	assert.Equal(t, uint64(1234567890), worldRaidRanking.Guid)
	assert.Equal(t, int32(5), worldRaidRanking.Job)
	assert.Equal(t, int32(85), worldRaidRanking.Level)
	assert.Equal(t, "Test Player", worldRaidRanking.Name)
	assert.Equal(t, int32(1), worldRaidRanking.Rank)
	assert.Equal(t, uint64(1000000), worldRaidRanking.Score)
	assert.Equal(t, int32(10), worldRaidRanking.CharacterFrame)
}

// testBatch53WatchingBookmarkSystem 测试观看书签系统
func testBatch53WatchingBookmarkSystem(t *testing.T) {
	// 测试WatchingBookmark
	watchingBookmark := &dnfv1.WatchingBookmark{
		Guid:            1234567890,
		Name:             "Test Player",
		Level:            85,
		Job:              5,
		GrowType:         1,
		SecondGrowType:   2,
		CharacterFrame:   10,
		ProfileUrl:       "http://example.com/profile.jpg",
		ProfileName:      "Test Profile",
		PlayTime:         3600000,
		LastPvpMatch:    1234567890,
	}
	assert.NotNil(t, watchingBookmark)
	assert.Equal(t, uint64(1234567890), watchingBookmark.Guid)
	assert.Equal(t, "Test Player", watchingBookmark.Name)
	assert.Equal(t, int32(85), watchingBookmark.Level)
	assert.Equal(t, int32(5), watchingBookmark.Job)
	assert.Equal(t, int32(1), watchingBookmark.GrowType)
	assert.Equal(t, int32(2), watchingBookmark.SecondGrowType)
	assert.Equal(t, int32(10), watchingBookmark.CharacterFrame)
	assert.Equal(t, "http://example.com/profile.jpg", watchingBookmark.ProfileUrl)
	assert.Equal(t, "Test Profile", watchingBookmark.ProfileName)
	assert.Equal(t, int64(3600000), watchingBookmark.PlayTime)
	assert.Equal(t, int32(1234567890), watchingBookmark.LastPvpMatch)
}

// testBatch53VerificationSystem 测试验证系统
func testBatch53VerificationSystem(t *testing.T) {
	// 测试Verification
	verification := &dnfv1.Verification{
		ClientStartTime: "2026-02-10 12:00:00",
	}
	assert.NotNil(t, verification)
	assert.Equal(t, "2026-02-10 12:00:00", verification.ClientStartTime)
	
	// 测试VerificationAddDamageData
	verificationAddDamageData := &dnfv1.VerificationAddDamageData{
		Flag:      1,
		Damage:    1000,
		ItemIndex: 100,
		IfIndex:   200,
		ThenIndex: 300,
	}
	assert.NotNil(t, verificationAddDamageData)
	assert.Equal(t, uint32(1), verificationAddDamageData.Flag)
	assert.Equal(t, int32(1000), verificationAddDamageData.Damage)
	assert.Equal(t, int32(100), verificationAddDamageData.ItemIndex)
	assert.Equal(t, int32(200), verificationAddDamageData.IfIndex)
	assert.Equal(t, int32(300), verificationAddDamageData.ThenIndex)
}
