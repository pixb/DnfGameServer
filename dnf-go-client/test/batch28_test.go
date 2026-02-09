package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试导师任务信息
func TestBatch28MentorQuestInfo(t *testing.T) {
	mentorQuestInfo := &dnfv1.MentorQuestInfo{
		Qindex:           1,
		State:            2,
		Masterrewardtime: 1770648000,
		Rewardtime:       1770651600,
	}

	data, err := proto.Marshal(mentorQuestInfo)
	if err != nil {
		t.Fatalf("Failed to marshal MentorQuestInfo: %v", err)
	}

	unmarshaled := &dnfv1.MentorQuestInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MentorQuestInfo: %v", err)
	}

	if unmarshaled.Qindex != mentorQuestInfo.Qindex {
		t.Errorf("Qindex mismatch: got %d, want %d", unmarshaled.Qindex, mentorQuestInfo.Qindex)
	}

	if unmarshaled.State != mentorQuestInfo.State {
		t.Errorf("State mismatch: got %d, want %d", unmarshaled.State, mentorQuestInfo.State)
	}

	if unmarshaled.Masterrewardtime != mentorQuestInfo.Masterrewardtime {
		t.Errorf("Masterrewardtime mismatch: got %d, want %d", unmarshaled.Masterrewardtime, mentorQuestInfo.Masterrewardtime)
	}

	if unmarshaled.Rewardtime != mentorQuestInfo.Rewardtime {
		t.Errorf("Rewardtime mismatch: got %d, want %d", unmarshaled.Rewardtime, mentorQuestInfo.Rewardtime)
	}
}

// 测试导师信息
func TestBatch28MenteeInfo(t *testing.T) {
	menteeInfo := &dnfv1.MenteeInfo{
		Guid:                  123456789,
		Level:                 50,
		Name:                   "TestMentee",
		Job:                    1,
		Growtype:               2,
		Secondgrowtype:         3,
		Active:                 1,
		Online:                 1,
		Followerallowgraduate:  1,
		Masterallowgraduate:    1,
		Mentordungeonticket:     1,
		Registlevel:            10,
		Regdate:                1770648000,
		Seqlevelgift:           "gift1",
		Seqlevelgiftmaster:     "gift2",
		Sendgreeting:           1770648000,
		Recvgreeting:            1770648100,
		Questcount:             5,
		Creditscore:             100,
		Quest: []*dnfv1.MentorQuestInfo{
			{
				Qindex:           1,
				State:            2,
				Masterrewardtime: 1770648000,
				Rewardtime:       1770651600,
			},
		},
	}

	data, err := proto.Marshal(menteeInfo)
	if err != nil {
		t.Fatalf("Failed to marshal MenteeInfo: %v", err)
	}

	unmarshaled := &dnfv1.MenteeInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MenteeInfo: %v", err)
	}

	if unmarshaled.Guid != menteeInfo.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, menteeInfo.Guid)
	}

	if unmarshaled.Level != menteeInfo.Level {
		t.Errorf("Level mismatch: got %d, want %d", unmarshaled.Level, menteeInfo.Level)
	}

	if unmarshaled.Name != menteeInfo.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, menteeInfo.Name)
	}

	if len(unmarshaled.Quest) != len(menteeInfo.Quest) {
		t.Errorf("Quest length mismatch: got %d, want %d", len(unmarshaled.Quest), len(menteeInfo.Quest))
	}
}

// 测试拍卖物品价格信息
func TestBatch28AuctionItemPriceInfo(t *testing.T) {
	auctionItemPriceInfo := &dnfv1.AuctionItemPriceInfo{
		Price: 1000,
		Count: 5,
	}

	data, err := proto.Marshal(auctionItemPriceInfo)
	if err != nil {
		t.Fatalf("Failed to marshal AuctionItemPriceInfo: %v", err)
	}

	unmarshaled := &dnfv1.AuctionItemPriceInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal AuctionItemPriceInfo: %v", err)
	}

	if unmarshaled.Price != auctionItemPriceInfo.Price {
		t.Errorf("Price mismatch: got %d, want %d", unmarshaled.Price, auctionItemPriceInfo.Price)
	}

	if unmarshaled.Count != auctionItemPriceInfo.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, auctionItemPriceInfo.Count)
	}
}

// 测试角色槽位信息
func TestBatch28CharacterSlotInfo(t *testing.T) {
	characterSlotInfo := &dnfv1.CharacterSlotInfo{
		Charguid: 987654321,
	}

	data, err := proto.Marshal(characterSlotInfo)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterSlotInfo: %v", err)
	}

	unmarshaled := &dnfv1.CharacterSlotInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal CharacterSlotInfo: %v", err)
	}

	if unmarshaled.Charguid != characterSlotInfo.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", unmarshaled.Charguid, characterSlotInfo.Charguid)
	}
}
