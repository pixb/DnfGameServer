package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch38WatingListUserInfo(t *testing.T) {
	user := &dnfv1.WatingListUserInfo{
		Name:        "TestUser",
		Gname:       "TestGuild",
		Job:         1,
		Growtype:    2,
		Secgrowtype: 3,
		Charguid:    123456789,
		Level:       50,
		Leader:      true,
		Ready:       false,
		Customdata:  987654321,
		Partyguid:   456789123,
	}

	data, err := proto.Marshal(user)
	if err != nil {
		t.Fatalf("Failed to marshal user: %v", err)
	}

	parsed := &dnfv1.WatingListUserInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal user: %v", err)
	}

	if parsed.Name != user.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, user.Name)
	}

	if parsed.Charguid != user.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, user.Charguid)
	}
}

func TestBatch38UserMinimumInfo(t *testing.T) {
	info := &dnfv1.UserMinimumInfo{
		Charguid:     123456789,
		Growtype:     1,
		Secgrowtype:  2,
		Job:          3,
		Level:        50,
		Name:         "TestUser",
		Teamtype:     dnfv1.TeamType_PLAYER,
		Rank:         10,
		Pvpstargrade: 5,
		Profileurl:   "http://example.com/profile.jpg",
		World:        1,
		Skinchatinfo: &dnfv1.SkinChatInfo{
			Chatframe:      1,
			Characterframe: 2,
		},
		Iskeyboard: true,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal info: %v", err)
	}

	parsed := &dnfv1.UserMinimumInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal info: %v", err)
	}

	if parsed.Name != info.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, info.Name)
	}

	if parsed.Teamtype != info.Teamtype {
		t.Errorf("Teamtype mismatch: got %v, want %v", parsed.Teamtype, info.Teamtype)
	}

	if parsed.Skinchatinfo == nil {
		t.Error("Skinchatinfo should not be nil")
	}
}

func TestBatch38UserLoadingStatus(t *testing.T) {
	status := &dnfv1.UserLoadingStatus{
		Charguid:     123456789,
		Ready:        true,
		Waiting:      false,
		Disconnect:   false,
		Leave:        false,
		Kickoutvotes: []uint64{111, 222, 333},
	}

	data, err := proto.Marshal(status)
	if err != nil {
		t.Fatalf("Failed to marshal status: %v", err)
	}

	parsed := &dnfv1.UserLoadingStatus{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal status: %v", err)
	}

	if parsed.Ready != status.Ready {
		t.Errorf("Ready mismatch: got %v, want %v", parsed.Ready, status.Ready)
	}

	if len(parsed.Kickoutvotes) != len(status.Kickoutvotes) {
		t.Errorf("Kickoutvotes length mismatch: got %d, want %d", len(parsed.Kickoutvotes), len(status.Kickoutvotes))
	}
}

func TestBatch38SimpleCharacter(t *testing.T) {
	char := &dnfv1.SimpleCharacter{
		Charguid:       123456789,
		Growtype:       1,
		Secgrowtype:    2,
		Job:            3,
		Level:          50,
		Name:           "TestUser",
		Equipscore:     10000,
		Characterframe: 5,
	}

	data, err := proto.Marshal(char)
	if err != nil {
		t.Fatalf("Failed to marshal character: %v", err)
	}

	parsed := &dnfv1.SimpleCharacter{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal character: %v", err)
	}

	if parsed.Name != char.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, char.Name)
	}

	if parsed.Equipscore != char.Equipscore {
		t.Errorf("Equipscore mismatch: got %d, want %d", parsed.Equipscore, char.Equipscore)
	}
}

func TestBatch38RoomUserInfo(t *testing.T) {
	user := &dnfv1.RoomUserInfo{
		Charguid:       123456789,
		Level:          50,
		Job:            1,
		Growtype:       2,
		Secondgrowtype: 3,
		Name:           "TestUser",
		Teamtype:       1,
		Creditscore:    100,
		Ready:          true,
		Skinchatinfo: &dnfv1.SkinChatInfo{
			Chatframe:      1,
			Characterframe: 2,
		},
	}

	data, err := proto.Marshal(user)
	if err != nil {
		t.Fatalf("Failed to marshal user: %v", err)
	}

	parsed := &dnfv1.RoomUserInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal user: %v", err)
	}

	if parsed.Name != user.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, user.Name)
	}

	if parsed.Ready != user.Ready {
		t.Errorf("Ready mismatch: got %v, want %v", parsed.Ready, user.Ready)
	}
}

func TestBatch38ShopBuyCount(t *testing.T) {
	count := &dnfv1.ShopBuyCount{
		Goodsid:            1001,
		Count:              5,
		Lastbuytime:        1234567890,
		Supplycount:        10,
		Supplydurationtime: 9876543210,
	}

	data, err := proto.Marshal(count)
	if err != nil {
		t.Fatalf("Failed to marshal count: %v", err)
	}

	parsed := &dnfv1.ShopBuyCount{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal count: %v", err)
	}

	if parsed.Goodsid != count.Goodsid {
		t.Errorf("Goodsid mismatch: got %d, want %d", parsed.Goodsid, count.Goodsid)
	}

	if parsed.Lastbuytime != count.Lastbuytime {
		t.Errorf("Lastbuytime mismatch: got %d, want %d", parsed.Lastbuytime, count.Lastbuytime)
	}
}

func TestBatch38Ticket(t *testing.T) {
	ticket := &dnfv1.TicketInfo{
		Dungeontype:        "dungeon_1",
		Dailyticket:        5,
		Weeklyticket:       10,
		Premiumticket:      3,
		Sweepticket:        2,
		Dailyrewardticket:  1,
		Weeklyrewardticket: 2,
		Buycount:           5,
		Chargetime:         1234567890,
		Freeticket:         1,
	}

	data, err := proto.Marshal(ticket)
	if err != nil {
		t.Fatalf("Failed to marshal ticket: %v", err)
	}

	parsed := &dnfv1.TicketInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ticket: %v", err)
	}

	if parsed.Dungeontype != ticket.Dungeontype {
		t.Errorf("Dungeontype mismatch: got %s, want %s", parsed.Dungeontype, ticket.Dungeontype)
	}

	if parsed.Dailyticket != ticket.Dailyticket {
		t.Errorf("Dailyticket mismatch: got %d, want %d", parsed.Dailyticket, ticket.Dailyticket)
	}
}

func TestBatch38TicketRewardInfo(t *testing.T) {
	reward := &dnfv1.TicketRewardList{
		Character: []*dnfv1.TicketInfo{
			{
				Dungeontype: "dungeon_1",
				Dailyticket: 5,
			},
		},
		Account: []*dnfv1.TicketInfo{
			{
				Dungeontype:  "dungeon_2",
				Weeklyticket: 10,
			},
		},
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal reward: %v", err)
	}

	parsed := &dnfv1.TicketRewardList{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal reward: %v", err)
	}

	if len(parsed.Character) != len(reward.Character) {
		t.Errorf("Character length mismatch: got %d, want %d", len(parsed.Character), len(reward.Character))
	}

	if len(parsed.Account) != len(reward.Account) {
		t.Errorf("Account length mismatch: got %d, want %d", len(parsed.Account), len(reward.Account))
	}
}

func TestBatch38SkinItemInfo(t *testing.T) {
	item := &dnfv1.SkinItemInfo{
		Index:           1001,
		Guid:            123456789,
		Expiretime:      9876543210,
		Acquisitiontime: 1234567890,
		Favorite:        1,
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal item: %v", err)
	}

	parsed := &dnfv1.SkinItemInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal item: %v", err)
	}

	if parsed.Index != item.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, item.Index)
	}

	if parsed.Guid != item.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, item.Guid)
	}
}

func TestBatch38SkinChatInfo(t *testing.T) {
	info := &dnfv1.SkinChatInfo{
		Chatframe:      1,
		Characterframe: 2,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal info: %v", err)
	}

	parsed := &dnfv1.SkinChatInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal info: %v", err)
	}

	if parsed.Chatframe != info.Chatframe {
		t.Errorf("Chatframe mismatch: got %d, want %d", parsed.Chatframe, info.Chatframe)
	}

	if parsed.Characterframe != info.Characterframe {
		t.Errorf("Characterframe mismatch: got %d, want %d", parsed.Characterframe, info.Characterframe)
	}
}

func TestBatch38Tutorial(t *testing.T) {
	tutorial := &dnfv1.Tutorial{
		Index:   1,
		State:   2,
		Type:    3,
		Enforce: true,
	}

	data, err := proto.Marshal(tutorial)
	if err != nil {
		t.Fatalf("Failed to marshal tutorial: %v", err)
	}

	parsed := &dnfv1.Tutorial{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal tutorial: %v", err)
	}

	if parsed.Index != tutorial.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, tutorial.Index)
	}

	if parsed.Enforce != tutorial.Enforce {
		t.Errorf("Enforce mismatch: got %v, want %v", parsed.Enforce, tutorial.Enforce)
	}
}

func TestBatch38WhisperChat(t *testing.T) {
	chat := &dnfv1.WhisperChat{
		Charguid:       123456789,
		Sender:         "SenderName",
		Receiver:       "ReceiverName",
		Chat:           "Hello, this is a test message.",
		Voice:          "voice_data",
		Job:            1,
		Growtype:       2,
		Secondgrowtype: 3,
		Date:           1234567890,
	}

	data, err := proto.Marshal(chat)
	if err != nil {
		t.Fatalf("Failed to marshal chat: %v", err)
	}

	parsed := &dnfv1.WhisperChat{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal chat: %v", err)
	}

	if parsed.Sender != chat.Sender {
		t.Errorf("Sender mismatch: got %s, want %s", parsed.Sender, chat.Sender)
	}

	if parsed.Receiver != chat.Receiver {
		t.Errorf("Receiver mismatch: got %s, want %s", parsed.Receiver, chat.Receiver)
	}

	if parsed.Chat != chat.Chat {
		t.Errorf("Chat mismatch: got %s, want %s", parsed.Chat, chat.Chat)
	}
}

func TestBatch38WarehouseNpc(t *testing.T) {
	npc := &dnfv1.WarehouseNpc{
		Type:   dnfv1.GuildNpcType_NPC,
		Index:  1,
		Guid:   123456789,
		Status: dnfv1.GuildNpcStatus_COMPLETE_PURCHASE,
	}

	data, err := proto.Marshal(npc)
	if err != nil {
		t.Fatalf("Failed to marshal npc: %v", err)
	}

	parsed := &dnfv1.WarehouseNpc{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal npc: %v", err)
	}

	if parsed.Type != npc.Type {
		t.Errorf("Type mismatch: got %v, want %v", parsed.Type, npc.Type)
	}

	if parsed.Status != npc.Status {
		t.Errorf("Status mismatch: got %v, want %v", parsed.Status, npc.Status)
	}
}

func TestBatch38WarehouseStructure(t *testing.T) {
	structure := &dnfv1.WarehouseStructure{
		Type:   dnfv1.GuildStructureType_NORMAL,
		Index:  1,
		Count:  5,
		Guid:   123456789,
		Status: dnfv1.GuildStructureStatus_CONSTRUCTION,
	}

	data, err := proto.Marshal(structure)
	if err != nil {
		t.Fatalf("Failed to marshal structure: %v", err)
	}

	parsed := &dnfv1.WarehouseStructure{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal structure: %v", err)
	}

	if parsed.Type != structure.Type {
		t.Errorf("Type mismatch: got %v, want %v", parsed.Type, structure.Type)
	}

	if parsed.Status != structure.Status {
		t.Errorf("Status mismatch: got %v, want %v", parsed.Status, structure.Status)
	}
}

func TestBatch38SeriaBuffInfo(t *testing.T) {
	buff := &dnfv1.SeriaBuffInfo{
		Number:   1,
		Cooltime: 1234567890,
	}

	data, err := proto.Marshal(buff)
	if err != nil {
		t.Fatalf("Failed to marshal buff: %v", err)
	}

	parsed := &dnfv1.SeriaBuffInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal buff: %v", err)
	}

	if parsed.Number != buff.Number {
		t.Errorf("Number mismatch: got %d, want %d", parsed.Number, buff.Number)
	}

	if parsed.Cooltime != buff.Cooltime {
		t.Errorf("Cooltime mismatch: got %d, want %d", parsed.Cooltime, buff.Cooltime)
	}
}

func TestBatch38SdDeathMatchReward(t *testing.T) {
	reward := &dnfv1.SdDeathMatchReward{
		Charguid:    123456789,
		Rewardcount: 5,
		List: []*dnfv1.SdDeathMatchRewardInfo{
			{
				Index: 1,
				Count: 10,
			},
			{
				Index: 2,
				Count: 20,
			},
		},
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal reward: %v", err)
	}

	parsed := &dnfv1.SdDeathMatchReward{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal reward: %v", err)
	}

	if parsed.Charguid != reward.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, reward.Charguid)
	}

	if len(parsed.List) != len(reward.List) {
		t.Errorf("List length mismatch: got %d, want %d", len(parsed.List), len(reward.List))
	}
}

func TestBatch38SubdueInfo(t *testing.T) {
	info := &dnfv1.SubdueInfo{
		Index:         1,
		Charguids:     []uint64{123, 456, 789},
		Starttime:     1234567890,
		Endtime:       9876543210,
		State:         1,
		Count:         5,
		Totalantievil: 100,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal info: %v", err)
	}

	parsed := &dnfv1.SubdueInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal info: %v", err)
	}

	if parsed.Index != info.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, info.Index)
	}

	if len(parsed.Charguids) != len(info.Charguids) {
		t.Errorf("Charguids length mismatch: got %d, want %d", len(parsed.Charguids), len(info.Charguids))
	}
}

func TestBatch38Seasonpass(t *testing.T) {
	seasonpass := &dnfv1.Seasonpass{
		Index:       1,
		DateStart:   1234567890,
		DateEnd:     9876543210,
		TargetGuids: []uint64{111, 222, 333},
	}

	data, err := proto.Marshal(seasonpass)
	if err != nil {
		t.Fatalf("Failed to marshal seasonpass: %v", err)
	}

	parsed := &dnfv1.Seasonpass{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal seasonpass: %v", err)
	}

	if parsed.Index != seasonpass.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, seasonpass.Index)
	}

	if len(parsed.TargetGuids) != len(seasonpass.TargetGuids) {
		t.Errorf("TargetGuids length mismatch: got %d, want %d", len(parsed.TargetGuids), len(seasonpass.TargetGuids))
	}
}

func TestBatch38ToyBillingVerify(t *testing.T) {
	verify := &dnfv1.ToyBillingVerify{
		StampToken:     "token123",
		OrderId:        "order456",
		ProductId:      "product789",
		UserId:         "user123",
		CharacterId:    "char456",
		ServicePayload: "payload789",
		MarketType:     "market123",
		PurchaseType:   "purchase456",
	}

	data, err := proto.Marshal(verify)
	if err != nil {
		t.Fatalf("Failed to marshal verify: %v", err)
	}

	parsed := &dnfv1.ToyBillingVerify{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal verify: %v", err)
	}

	if parsed.StampToken != verify.StampToken {
		t.Errorf("StampToken mismatch: got %s, want %s", parsed.StampToken, verify.StampToken)
	}

	if parsed.OrderId != verify.OrderId {
		t.Errorf("OrderId mismatch: got %s, want %s", parsed.OrderId, verify.OrderId)
	}
}
