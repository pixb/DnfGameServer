package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestBatch37WeddingGuestbook(t *testing.T) {
	guestbook := &dnfv1.WeddingGuestbook{
		WriterGuid:   1234567890123456789,
		WriterName:   "Player1",
		WriterJob:    1,
		WriteDate:    1707523200000,
		Contents:     "Congratulations!",
	}

	data, err := proto.Marshal(guestbook)
	if err != nil {
		t.Fatalf("Failed to marshal guestbook: %v", err)
	}

	parsed := &dnfv1.WeddingGuestbook{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal guestbook: %v", err)
	}

	if parsed.WriterGuid != guestbook.WriterGuid {
		t.Errorf("WriterGuid mismatch: got %d, want %d", parsed.WriterGuid, guestbook.WriterGuid)
	}

	if parsed.WriterName != guestbook.WriterName {
		t.Errorf("WriterName mismatch: got %s, want %s", parsed.WriterName, guestbook.WriterName)
	}
}

func TestBatch37WeddingInvitation(t *testing.T) {
	invitation := &dnfv1.WeddingInvitation{
		MarriageGuid:    9876543210987654321,
		InviterGuid:     1234567890123456789,
		InviterName:     "Player1",
		SpouseGuid:      9876543210987654321,
		SpouseName:      "Player2",
		WeddingHallIndex: 1,
		WeddingDate:     1707523200,
	}

	data, err := proto.Marshal(invitation)
	if err != nil {
		t.Fatalf("Failed to marshal invitation: %v", err)
	}

	parsed := &dnfv1.WeddingInvitation{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal invitation: %v", err)
	}

	if parsed.MarriageGuid != invitation.MarriageGuid {
		t.Errorf("MarriageGuid mismatch: got %d, want %d", parsed.MarriageGuid, invitation.MarriageGuid)
	}

	if parsed.InviterName != invitation.InviterName {
		t.Errorf("InviterName mismatch: got %s, want %s", parsed.InviterName, invitation.InviterName)
	}
}

func TestBatch37WeddingAttendance(t *testing.T) {
	attendance := &dnfv1.WeddingAttendance{
		AttendanceGuid: 1234567890123456789,
		AttendanceName: "Guest1",
		GGuid:          9876543210987654321,
		ReplyType:      1,
	}

	data, err := proto.Marshal(attendance)
	if err != nil {
		t.Fatalf("Failed to marshal attendance: %v", err)
	}

	parsed := &dnfv1.WeddingAttendance{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal attendance: %v", err)
	}

	if parsed.AttendanceGuid != attendance.AttendanceGuid {
		t.Errorf("AttendanceGuid mismatch: got %d, want %d", parsed.AttendanceGuid, attendance.AttendanceGuid)
	}

	if parsed.ReplyType != attendance.ReplyType {
		t.Errorf("ReplyType mismatch: got %d, want %d", parsed.ReplyType, attendance.ReplyType)
	}
}

func TestBatch37WeddingTheme(t *testing.T) {
	theme := &dnfv1.WeddingTheme{
		WeddingHall: 1,
		Officiant:   1,
		GuestList:   []int32{1, 2, 3},
		Dress:       1,
		CeremonyList: []*dnfv1.WeddingThemeCeremony{
			{
				Category: 1,
				Index:    1,
			},
			{
				Category: 2,
				Index:    2,
			},
		},
	}

	data, err := proto.Marshal(theme)
	if err != nil {
		t.Fatalf("Failed to marshal theme: %v", err)
	}

	parsed := &dnfv1.WeddingTheme{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal theme: %v", err)
	}

	if parsed.WeddingHall != theme.WeddingHall {
		t.Errorf("WeddingHall mismatch: got %d, want %d", parsed.WeddingHall, theme.WeddingHall)
	}

	if len(parsed.GuestList) != len(theme.GuestList) {
		t.Errorf("GuestList length mismatch: got %d, want %d", len(parsed.GuestList), len(theme.GuestList))
	}

	if len(parsed.CeremonyList) != len(theme.CeremonyList) {
		t.Errorf("CeremonyList length mismatch: got %d, want %d", len(parsed.CeremonyList), len(theme.CeremonyList))
	}
}

func TestBatch37WeddingPreparation(t *testing.T) {
	preparation := &dnfv1.WeddingPreparation{
		CharGuid: 1234567890123456789,
		Theme: &dnfv1.WeddingTheme{
			WeddingHall: 1,
			Officiant:   1,
			Dress:       1,
		},
		PaymentAmount: 1000,
		PaymentGold:   500,
		PaymentTera:   300,
	}

	data, err := proto.Marshal(preparation)
	if err != nil {
		t.Fatalf("Failed to marshal preparation: %v", err)
	}

	parsed := &dnfv1.WeddingPreparation{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal preparation: %v", err)
	}

	if parsed.CharGuid != preparation.CharGuid {
		t.Errorf("CharGuid mismatch: got %d, want %d", parsed.CharGuid, preparation.CharGuid)
	}

	if parsed.PaymentAmount != preparation.PaymentAmount {
		t.Errorf("PaymentAmount mismatch: got %d, want %d", parsed.PaymentAmount, preparation.PaymentAmount)
	}

	if parsed.Theme == nil {
		t.Errorf("Theme is nil")
	}
}

func TestBatch37WeddingMoneygiftRanking(t *testing.T) {
	ranking := &dnfv1.WeddingMoneygiftRanking{
		Guid:           1234567890123456789,
		GrowType:       1,
		SecondGrowType: 2,
		Job:            1,
		Level:          100,
		Name:           "Player1",
		Rank:           1,
		Score:          10000,
	}

	data, err := proto.Marshal(ranking)
	if err != nil {
		t.Fatalf("Failed to marshal ranking: %v", err)
	}

	parsed := &dnfv1.WeddingMoneygiftRanking{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ranking: %v", err)
	}

	if parsed.Guid != ranking.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, ranking.Guid)
	}

	if parsed.Name != ranking.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, ranking.Name)
	}

	if parsed.Score != ranking.Score {
		t.Errorf("Score mismatch: got %d, want %d", parsed.Score, ranking.Score)
	}
}

func TestBatch37WorldRaid(t *testing.T) {
	info := &dnfv1.WorldRaidInfo{
		DungeonIndex: 1,
		Hp:           1000000,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal world raid info: %v", err)
	}

	parsed := &dnfv1.WorldRaidInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal world raid info: %v", err)
	}

	if parsed.DungeonIndex != info.DungeonIndex {
		t.Errorf("DungeonIndex mismatch: got %d, want %d", parsed.DungeonIndex, info.DungeonIndex)
	}

	if parsed.Hp != info.Hp {
		t.Errorf("Hp mismatch: got %d, want %d", parsed.Hp, info.Hp)
	}

	ranking := &dnfv1.WorldRaidRanking{
		GrowType:       1,
		SecondGrowType: 2,
		Guid:           1234567890123456789,
		Job:            1,
		Level:          100,
		Name:           "Player1",
		Rank:           1,
		Score:          50000,
		CharacterFrame: 1,
	}

	data, err = proto.Marshal(ranking)
	if err != nil {
		t.Fatalf("Failed to marshal world raid ranking: %v", err)
	}

	parsedRanking := &dnfv1.WorldRaidRanking{}
	if err := proto.Unmarshal(data, parsedRanking); err != nil {
		t.Fatalf("Failed to unmarshal world raid ranking: %v", err)
	}

	if parsedRanking.Guid != ranking.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsedRanking.Guid, ranking.Guid)
	}

	if parsedRanking.Score != ranking.Score {
		t.Errorf("Score mismatch: got %d, want %d", parsedRanking.Score, ranking.Score)
	}
}

func TestBatch37WatchingBookmark(t *testing.T) {
	bookmark := &dnfv1.WatchingBookmark{
		Guid:            1234567890123456789,
		Name:            "Player1",
		Level:           100,
		Job:             1,
		GrowType:        1,
		SecondGrowType:  2,
		CharacterFrame:  1,
		ProfileUrl:      "http://example.com/profile.jpg",
		ProfileName:     "Profile1",
		PlayTime:        1000000,
		LastPvpMatch:    1,
	}

	data, err := proto.Marshal(bookmark)
	if err != nil {
		t.Fatalf("Failed to marshal bookmark: %v", err)
	}

	parsed := &dnfv1.WatchingBookmark{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal bookmark: %v", err)
	}

	if parsed.Guid != bookmark.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, bookmark.Guid)
	}

	if parsed.Name != bookmark.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, bookmark.Name)
	}

	if parsed.PlayTime != bookmark.PlayTime {
		t.Errorf("PlayTime mismatch: got %d, want %d", parsed.PlayTime, bookmark.PlayTime)
	}
}

func TestBatch37Verification(t *testing.T) {
	verification := &dnfv1.Verification{
		ClientStartTime: "2024-02-10T00:00:00Z",
	}

	data, err := proto.Marshal(verification)
	if err != nil {
		t.Fatalf("Failed to marshal verification: %v", err)
	}

	parsed := &dnfv1.Verification{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal verification: %v", err)
	}

	if parsed.ClientStartTime != verification.ClientStartTime {
		t.Errorf("ClientStartTime mismatch: got %s, want %s", parsed.ClientStartTime, verification.ClientStartTime)
	}

	damageData := &dnfv1.VerificationAddDamageData{
		Flag:      1,
		Damage:    1000,
		ItemIndex: 100,
		IfIndex:   1,
		ThenIndex: 2,
	}

	data, err = proto.Marshal(damageData)
	if err != nil {
		t.Fatalf("Failed to marshal damage data: %v", err)
	}

	parsedDamage := &dnfv1.VerificationAddDamageData{}
	if err := proto.Unmarshal(data, parsedDamage); err != nil {
		t.Fatalf("Failed to unmarshal damage data: %v", err)
	}

	if parsedDamage.Flag != damageData.Flag {
		t.Errorf("Flag mismatch: got %d, want %d", parsedDamage.Flag, damageData.Flag)
	}

	if parsedDamage.Damage != damageData.Damage {
		t.Errorf("Damage mismatch: got %d, want %d", parsedDamage.Damage, damageData.Damage)
	}
}
