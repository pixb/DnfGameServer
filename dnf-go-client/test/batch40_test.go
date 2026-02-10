package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

func TestBatch40AccountTicket(t *testing.T) {
	ticket := &dnfv1.AccountTicket{
		Dungeontype: "dungeon_test",
		Count:       10,
	}

	data, err := proto.Marshal(ticket)
	if err != nil {
		t.Fatalf("Failed to marshal AccountTicket: %v", err)
	}

	parsed := &dnfv1.AccountTicket{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AccountTicket: %v", err)
	}

	if parsed.Dungeontype != ticket.Dungeontype {
		t.Errorf("Dungeontype mismatch: got %s, want %s", parsed.Dungeontype, ticket.Dungeontype)
	}

	if parsed.Count != ticket.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsed.Count, ticket.Count)
	}
}

func TestBatch40ActionCountInfo(t *testing.T) {
	info := &dnfv1.ActionCountInfo{
		Combocount:        100,
		Aerialcount:       50,
		Overkillcount1:    30,
		Overkillcount2:    20,
		Countercount:      15,
		Backattackcount:   40,
		Crowdcontrolcount: 25,
		Justevadecount:    10,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal ActionCountInfo: %v", err)
	}

	parsed := &dnfv1.ActionCountInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ActionCountInfo: %v", err)
	}

	if parsed.Combocount != info.Combocount {
		t.Errorf("Combocount mismatch: got %d, want %d", parsed.Combocount, info.Combocount)
	}
}

func TestBatch40ActiveStatusDamage(t *testing.T) {
	damage := &dnfv1.ActiveStatusDamage{
		Type:       1,
		Damage:     1000,
		Skillindex: 10,
		Itemindex:  5,
		Victimguid: 12345,
		Victimtype: 2,
		Tickcount:  3,
	}

	data, err := proto.Marshal(damage)
	if err != nil {
		t.Fatalf("Failed to marshal ActiveStatusDamage: %v", err)
	}

	parsed := &dnfv1.ActiveStatusDamage{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ActiveStatusDamage: %v", err)
	}

	if parsed.Damage != damage.Damage {
		t.Errorf("Damage mismatch: got %d, want %d", parsed.Damage, damage.Damage)
	}
}

func TestBatch40AiCharacterDetailInfo(t *testing.T) {
	info := &dnfv1.AiCharacterDetailInfo{
		Charguid:            123456789,
		Name:                "TestChar",
		Growtype:            1,
		Secondgrowtype:      2,
		Exp:                 1000,
		Hp:                  5000,
		Mp:                  3000,
		Level:               50,
		Job:                 5,
		Equipscore:          10000,
		Score:               5000,
		Count:               10,
		Date:                1234567890,
		Spoint:              100,
		Adventureunionlevel: 10,
		Adventureunionexp:   50000,
		World:               1,
		Gguid:               987654321,
		Gname:               "TestGuild",
		Gmastername:         "Master",
		Gmembergrade:        5,
		Blackdiamond:        1000,
		AvatarVisibleFlags:  1,
		Gamecenterinfo:      1,
		QqVipinfo:           1,
		Totallike:           100,
		Like:                50,
		Rank:                10,
		Communionlevel:      5,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AiCharacterDetailInfo: %v", err)
	}

	parsed := &dnfv1.AiCharacterDetailInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AiCharacterDetailInfo: %v", err)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}

	if parsed.Name != info.Name {
		t.Errorf("Name mismatch: got %s, want %s", parsed.Name, info.Name)
	}
}

func TestBatch40AllSkillSlot(t *testing.T) {
	slots := &dnfv1.AllSkillSlot{
		Active: []*dnfv1.SkillSlotInfo{
			{SlotId: 1, SkillId: 1},
			{SlotId: 2, SkillId: 2},
		},
		Buff: []*dnfv1.SkillSlotInfo{
			{SlotId: 3, SkillId: 3},
		},
		Combo: []*dnfv1.SkillSlotInfo{
			{SlotId: 4, SkillId: 4},
		},
		Keyboardmatching: []*dnfv1.SkillSlotMatching{
			{Index: 1, Slot: 1},
		},
		Padmatching: []*dnfv1.SkillSlotMatching{
			{Index: 2, Slot: 2},
		},
	}

	data, err := proto.Marshal(slots)
	if err != nil {
		t.Fatalf("Failed to marshal AllSkillSlot: %v", err)
	}

	parsed := &dnfv1.AllSkillSlot{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AllSkillSlot: %v", err)
	}

	if len(parsed.Active) != len(slots.Active) {
		t.Errorf("Active length mismatch: got %d, want %d", len(parsed.Active), len(slots.Active))
	}
}

func TestBatch40AltarInfo(t *testing.T) {
	info := &dnfv1.AltarInfo{
		Index:     1,
		Scramble:  2,
		Poolindex: 3,
		State:     1,
		Endtime:   1234567890,
		Red: &dnfv1.BattlefieldTeamInfo{
			State:      1,
			Win:        5,
			Date:       1234567890,
			Count:      10,
			Clearcount: 8,
		},
		Blue: &dnfv1.BattlefieldTeamInfo{
			State:      1,
			Win:        3,
			Date:       1234567890,
			Count:      10,
			Clearcount: 5,
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AltarInfo: %v", err)
	}

	parsed := &dnfv1.AltarInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AltarInfo: %v", err)
	}

	if parsed.Index != info.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, info.Index)
	}
}

func TestBatch40ApcCharacter(t *testing.T) {
	character := &dnfv1.ApcCharacter{
		Charguid:       123456789,
		Hp:             5000,
		Mp:             3000,
		Job:            5,
		Level:          50,
		Exp:            1000,
		Growtype:       1,
		Secondgrowtype: 2,
		Name:           "TestChar",
		Equiplist: &dnfv1.EquipList{
			Equips: []*dnfv1.Equip{
				{EquipId: 1, Level: 1},
			},
		},
		Skilllist: &dnfv1.Skills{
			Skills: []*dnfv1.Skill{
				{Index: 1, Level: 1},
			},
		},
		Ownerguid:  987654321,
		Teamtype:   1,
		Objectguid: 111,
		Equipscore: 10000,
	}

	data, err := proto.Marshal(character)
	if err != nil {
		t.Fatalf("Failed to marshal ApcCharacter: %v", err)
	}

	parsed := &dnfv1.ApcCharacter{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ApcCharacter: %v", err)
	}

	if parsed.Charguid != character.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, character.Charguid)
	}
}

func TestBatch40ApcInfo(t *testing.T) {
	info := &dnfv1.ApcInfo{
		Charguid:       123456789,
		Name:           "TestChar",
		Growtype:       1,
		Secondgrowtype: 2,
		Level:          50,
		Job:            5,
		Equipscore:     10000,
		Count:          10,
		Date:           1234567890,
		Equiplist: &dnfv1.EquipList{
			Equips: []*dnfv1.Equip{
				{EquipId: 1, Level: 1},
			},
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal ApcInfo: %v", err)
	}

	parsed := &dnfv1.ApcInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ApcInfo: %v", err)
	}

	if parsed.Charguid != info.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, info.Charguid)
	}
}

func TestBatch40Appendage(t *testing.T) {
	appendage := &dnfv1.Appendage{
		Appendageindex: 1,
		Count:          5,
	}

	data, err := proto.Marshal(appendage)
	if err != nil {
		t.Fatalf("Failed to marshal Appendage: %v", err)
	}

	parsed := &dnfv1.Appendage{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal Appendage: %v", err)
	}

	if parsed.Appendageindex != appendage.Appendageindex {
		t.Errorf("Appendageindex mismatch: got %d, want %d", parsed.Appendageindex, appendage.Appendageindex)
	}
}

func TestBatch40ArcadePvpInfoCurrency(t *testing.T) {
	currency := &dnfv1.ArcadePvpInfoCurrency{
		Index: 1,
		Count: 100,
	}

	data, err := proto.Marshal(currency)
	if err != nil {
		t.Fatalf("Failed to marshal ArcadePvpInfoCurrency: %v", err)
	}

	parsed := &dnfv1.ArcadePvpInfoCurrency{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ArcadePvpInfoCurrency: %v", err)
	}

	if parsed.Index != currency.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, currency.Index)
	}
}

func TestBatch40ArtifactBaseOption(t *testing.T) {
	option := &dnfv1.ArtifactBaseOption{
		Option: 1,
		Rarity: 5,
		Locked: true,
	}

	data, err := proto.Marshal(option)
	if err != nil {
		t.Fatalf("Failed to marshal ArtifactBaseOption: %v", err)
	}

	parsed := &dnfv1.ArtifactBaseOption{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal ArtifactBaseOption: %v", err)
	}

	if parsed.Option != option.Option {
		t.Errorf("Option mismatch: got %d, want %d", parsed.Option, option.Option)
	}
}

func TestBatch40AttachCrackRequest(t *testing.T) {
	request := &dnfv1.AttachCrackRequest{
		Index: 1,
		Slot:  2,
	}

	data, err := proto.Marshal(request)
	if err != nil {
		t.Fatalf("Failed to marshal AttachCrackRequest: %v", err)
	}

	parsed := &dnfv1.AttachCrackRequest{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AttachCrackRequest: %v", err)
	}

	if parsed.Index != request.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, request.Index)
	}
}

func TestBatch40AttackSquadAdvertisement(t *testing.T) {
	ad := &dnfv1.AttackSquadAdvertisement{
		Register: true,
	}

	data, err := proto.Marshal(ad)
	if err != nil {
		t.Fatalf("Failed to marshal AttackSquadAdvertisement: %v", err)
	}

	parsed := &dnfv1.AttackSquadAdvertisement{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AttackSquadAdvertisement: %v", err)
	}

	if parsed.Register != ad.Register {
		t.Errorf("Register mismatch: got %v, want %v", parsed.Register, ad.Register)
	}
}

func TestBatch40AttackSquadBoardInfo(t *testing.T) {
	info := &dnfv1.AttackSquadBoardInfo{
		Rpguid:        123456789,
		Rpname:        "TestSquad",
		Membercount:   10,
		Antievilscore: 5000,
		Publictype:    1,
		Status:        1,
		Raidindex:     1,
		Chivalry:      true,
		Leaderguid:    987654321,
		Started:       false,
		Memberinfos: []*dnfv1.AttackSquadBoardUserInfo{
			{Job: 5, Growtype: 1},
			{Job: 3, Growtype: 2},
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AttackSquadBoardInfo: %v", err)
	}

	parsed := &dnfv1.AttackSquadBoardInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AttackSquadBoardInfo: %v", err)
	}

	if parsed.Rpguid != info.Rpguid {
		t.Errorf("Rpguid mismatch: got %d, want %d", parsed.Rpguid, info.Rpguid)
	}
}

func TestBatch40AttackSquadDetailInfo(t *testing.T) {
	info := &dnfv1.AttackSquadDetailInfo{
		Rpguid:        123456789,
		Rpname:        "TestSquad",
		Membercount:   10,
		Antievilscore: 5000,
		Publictype:    1,
		Status:        1,
		Phasestatus:   1,
		Changetime:    1234567890,
		Leadername:    "Leader",
		Leaderguid:    987654321,
		World:         1,
		Channel:       1,
		Ip:            "127.0.0.1",
		Port:          8080,
		Started:       false,
		Users: []*dnfv1.AttackSquadMemberInfo{
			{
				Partyguid:  111,
				Partyindex: 1,
				Partyslot:  1,
				User: &dnfv1.GroupMember{
					Charguid: 123456789,
					Name:     "TestUser",
					Job:      5,
					Level:    50,
				},
				Disconnecttime:       0,
				Rewardconditioncount: 5,
			},
		},
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal AttackSquadDetailInfo: %v", err)
	}

	parsed := &dnfv1.AttackSquadDetailInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AttackSquadDetailInfo: %v", err)
	}

	if parsed.Rpguid != info.Rpguid {
		t.Errorf("Rpguid mismatch: got %d, want %d", parsed.Rpguid, info.Rpguid)
	}
}

func TestBatch40AttackSquadTimer(t *testing.T) {
	timer := &dnfv1.AttackSquadTimer{
		Breakuptime: 1234567890,
	}

	data, err := proto.Marshal(timer)
	if err != nil {
		t.Fatalf("Failed to marshal AttackSquadTimer: %v", err)
	}

	parsed := &dnfv1.AttackSquadTimer{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AttackSquadTimer: %v", err)
	}

	if parsed.Breakuptime != timer.Breakuptime {
		t.Errorf("Breakuptime mismatch: got %d, want %d", parsed.Breakuptime, timer.Breakuptime)
	}
}

func TestBatch40AuctionBase(t *testing.T) {
	auction := &dnfv1.AuctionBase{
		Guid:        123456789,
		Upgrade:     10,
		Quality:     1,
		Endurance:   100,
		Enchant:     5,
		Reforge:     3,
		Amplify:     2,
		Aoption:     1,
		Scount:      5,
		Tcount:      3,
		Expiretime:  1234567890,
		Rappearance: true,
		Skin:        1,
		Skinguid:    987654321,
		Locked:      false,
		Seal:        false,
		Type:        1,
		Auid:        111,
		Bidder:      222,
		Price:       1000,
		Enddate:     1234567890,
		Registcount: 5,
		Tera:        10,
		Buyprice:    500,
		Flag:        true,
		Index:       1,
		Count:       1,
		Season:      1,
	}

	data, err := proto.Marshal(auction)
	if err != nil {
		t.Fatalf("Failed to marshal AuctionBase: %v", err)
	}

	parsed := &dnfv1.AuctionBase{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AuctionBase: %v", err)
	}

	if parsed.Guid != auction.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, auction.Guid)
	}
}

func TestBatch40AuctionEquip(t *testing.T) {
	auction := &dnfv1.AuctionEquip{
		Guid:         123456789,
		Upgrade:      10,
		Quality:      1,
		Endurance:    100,
		Reforge:      3,
		Amplify:      2,
		Aoption:      1,
		Scount:       5,
		Tcount:       3,
		Expiretime:   1234567890,
		Rappearance:  true,
		Skin:         1,
		Skinguid:     987654321,
		Locked:       false,
		Seal:         false,
		Enchantindex: 1,
		Enchant:      5,
		Type:         1,
		Auid:         111,
		Bidder:       222,
		Price:        1000,
		Enddate:      1234567890,
		Registcount:  5,
		Tera:         10,
		Buyprice:     500,
		Flag:         1,
		Index:        1,
		Count:        1,
		Season:       1,
	}

	data, err := proto.Marshal(auction)
	if err != nil {
		t.Fatalf("Failed to marshal AuctionEquip: %v", err)
	}

	parsed := &dnfv1.AuctionEquip{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AuctionEquip: %v", err)
	}

	if parsed.Guid != auction.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, auction.Guid)
	}
}

func TestBatch40AuctionItemIndex(t *testing.T) {
	item := &dnfv1.AuctionItemIndex{
		Index: 1,
		Qty:   10,
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal AuctionItemIndex: %v", err)
	}

	parsed := &dnfv1.AuctionItemIndex{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AuctionItemIndex: %v", err)
	}

	if parsed.Index != item.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, item.Index)
	}
}

func TestBatch40AuctionStackable(t *testing.T) {
	auction := &dnfv1.AuctionStackable{
		Guid:         123456789,
		Upgrade:      10,
		Quality:      1,
		Endurance:    100,
		Reforge:      3,
		Amplify:      2,
		Aoption:      1,
		Scount:       5,
		Tcount:       3,
		Expiretime:   1234567890,
		Rappearance:  true,
		Skin:         1,
		Skinguid:     987654321,
		Locked:       false,
		Seal:         false,
		Enchantindex: 1,
		Enchant:      5,
		Type:         1,
		Auid:         111,
		Bidder:       222,
		Price:        1000,
		Enddate:      1234567890,
		Registcount:  5,
		Tera:         10,
		Buyprice:     500,
		Flag:         1,
		Index:        1,
		Count:        1,
		Season:       1,
	}

	data, err := proto.Marshal(auction)
	if err != nil {
		t.Fatalf("Failed to marshal AuctionStackable: %v", err)
	}

	parsed := &dnfv1.AuctionStackable{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AuctionStackable: %v", err)
	}

	if parsed.Guid != auction.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, auction.Guid)
	}
}

func TestBatch40AvatarComposeMaterial(t *testing.T) {
	material := &dnfv1.AvatarComposeMaterial{
		Index: 1,
		Count: 5,
	}

	data, err := proto.Marshal(material)
	if err != nil {
		t.Fatalf("Failed to marshal AvatarComposeMaterial: %v", err)
	}

	parsed := &dnfv1.AvatarComposeMaterial{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AvatarComposeMaterial: %v", err)
	}

	if parsed.Index != material.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, material.Index)
	}
}

func TestBatch40AvatarDisassembledMaterial(t *testing.T) {
	material := &dnfv1.AvatarDisassembledMaterial{
		Index: 1,
		Count: 5,
	}

	data, err := proto.Marshal(material)
	if err != nil {
		t.Fatalf("Failed to marshal AvatarDisassembledMaterial: %v", err)
	}

	parsed := &dnfv1.AvatarDisassembledMaterial{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AvatarDisassembledMaterial: %v", err)
	}

	if parsed.Index != material.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, material.Index)
	}
}

func TestBatch40AvatarGuid(t *testing.T) {
	guid := &dnfv1.AvatarGuid{
		Guid: 123456789,
	}

	data, err := proto.Marshal(guid)
	if err != nil {
		t.Fatalf("Failed to marshal AvatarGuid: %v", err)
	}

	parsed := &dnfv1.AvatarGuid{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AvatarGuid: %v", err)
	}

	if parsed.Guid != guid.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, guid.Guid)
	}
}

func TestBatch40AvatarItem(t *testing.T) {
	item := &dnfv1.AvatarItem{
		Index:      1,
		Count:      1,
		Guid:       123456789,
		Expiretime: 1234567890,
		Option:     1,
		Locked:     false,
		Scount:     5,
		Tcount:     3,
		Sindex:     1,
	}

	data, err := proto.Marshal(item)
	if err != nil {
		t.Fatalf("Failed to marshal AvatarItem: %v", err)
	}

	parsed := &dnfv1.AvatarItem{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal AvatarItem: %v", err)
	}

	if parsed.Guid != item.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", parsed.Guid, item.Guid)
	}
}

func TestBatch40BattlefieldStateInfo(t *testing.T) {
	info := &dnfv1.BattlefieldStateInfo{
		Battlefield: 1,
		Scramble:    2,
		Slotindex:   1,
		State:       1,
		Endtime:     1234567890,
		Red: &dnfv1.BattlefieldTeamInfo{
			State:      1,
			Win:        5,
			Date:       1234567890,
			Count:      10,
			Clearcount: 8,
		},
		Blue: &dnfv1.BattlefieldTeamInfo{
			State:      1,
			Win:        3,
			Date:       1234567890,
			Count:      10,
			Clearcount: 5,
		},
		Garrison: false,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal BattlefieldStateInfo: %v", err)
	}

	parsed := &dnfv1.BattlefieldStateInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattlefieldStateInfo: %v", err)
	}

	if parsed.Battlefield != info.Battlefield {
		t.Errorf("Battlefield mismatch: got %d, want %d", parsed.Battlefield, info.Battlefield)
	}
}

func TestBatch40BattlefieldTeamInfo(t *testing.T) {
	info := &dnfv1.BattlefieldTeamInfo{
		State:      1,
		Win:        5,
		Date:       1234567890,
		Count:      10,
		Clearcount: 8,
	}

	data, err := proto.Marshal(info)
	if err != nil {
		t.Fatalf("Failed to marshal BattlefieldTeamInfo: %v", err)
	}

	parsed := &dnfv1.BattlefieldTeamInfo{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattlefieldTeamInfo: %v", err)
	}

	if parsed.State != info.State {
		t.Errorf("State mismatch: got %d, want %d", parsed.State, info.State)
	}
}

func TestBatch40BattleleagueBuff(t *testing.T) {
	buff := &dnfv1.BattleleagueBuff{
		Index:       1,
		Customvalue: 100,
	}

	data, err := proto.Marshal(buff)
	if err != nil {
		t.Fatalf("Failed to marshal BattleleagueBuff: %v", err)
	}

	parsed := &dnfv1.BattleleagueBuff{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattleleagueBuff: %v", err)
	}

	if parsed.Index != buff.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, buff.Index)
	}
}

func TestBatch40BattleleagueContribute(t *testing.T) {
	contribute := &dnfv1.BattleleagueContribute{
		Charguid:     123456789,
		Contribution: 1000,
	}

	data, err := proto.Marshal(contribute)
	if err != nil {
		t.Fatalf("Failed to marshal BattleleagueContribute: %v", err)
	}

	parsed := &dnfv1.BattleleagueContribute{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattleleagueContribute: %v", err)
	}

	if parsed.Charguid != contribute.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, contribute.Charguid)
	}
}

func TestBatch40BattleleaguePveRecord(t *testing.T) {
	record := &dnfv1.BattleleaguePveRecord{
		Charguid:     123456789,
		Contribution: 1000,
	}

	data, err := proto.Marshal(record)
	if err != nil {
		t.Fatalf("Failed to marshal BattleleaguePveRecord: %v", err)
	}

	parsed := &dnfv1.BattleleaguePveRecord{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattleleaguePveRecord: %v", err)
	}

	if parsed.Charguid != record.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, record.Charguid)
	}
}

func TestBatch40BattleleaguePvpRecord(t *testing.T) {
	record := &dnfv1.BattleleaguePvpRecord{
		Charguid:     123456789,
		Contribution: 1000,
	}

	data, err := proto.Marshal(record)
	if err != nil {
		t.Fatalf("Failed to marshal BattleleaguePvpRecord: %v", err)
	}

	parsed := &dnfv1.BattleleaguePvpRecord{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattleleaguePvpRecord: %v", err)
	}

	if parsed.Charguid != record.Charguid {
		t.Errorf("Charguid mismatch: got %d, want %d", parsed.Charguid, record.Charguid)
	}
}

func TestBatch40BattleleagueReward(t *testing.T) {
	reward := &dnfv1.BattleleagueReward{
		Index: 1,
		Count: 10,
	}

	data, err := proto.Marshal(reward)
	if err != nil {
		t.Fatalf("Failed to marshal BattleleagueReward: %v", err)
	}

	parsed := &dnfv1.BattleleagueReward{}
	if err := proto.Unmarshal(data, parsed); err != nil {
		t.Fatalf("Failed to unmarshal BattleleagueReward: %v", err)
	}

	if parsed.Index != reward.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsed.Index, reward.Index)
	}
}
