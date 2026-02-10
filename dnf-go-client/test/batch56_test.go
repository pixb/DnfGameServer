package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch56Migration 测试批次56的迁移结果
func TestBatch56Migration(t *testing.T) {
	// 测试账户和成就系统
	t.Run("AccountAndAchievement", testBatch56AccountAndAchievement)

	// 测试动作和状态系统
	t.Run("ActionAndStatus", testBatch56ActionAndStatus)

	// 测试AI和APC系统
	t.Run("AiAndApc", testBatch56AiAndApc)

	// 测试炼金和奖励系统
	t.Run("AlchemyAndReward", testBatch56AlchemyAndReward)

	// 测试技能和祭坛系统
	t.Run("SkillAndAltar", testBatch56SkillAndAltar)

	// 测试攻击队和战场系统
	t.Run("AttackSquadAndBattlefield", testBatch56AttackSquadAndBattlefield)

	// 测试拍卖系统
	t.Run("Auction", testBatch56Auction)

	// 测试头像系统
	t.Run("Avatar", testBatch56Avatar)

	// 测试战斗联盟系统
	t.Run("Battleleague", testBatch56Battleleague)
}

// testBatch56AccountAndAchievement 测试账户和成就系统
func testBatch56AccountAndAchievement(t *testing.T) {
	// 测试AccountTicket
	accountTicket := &dnfv1.AccountTicket{
		Dungeontype: "test_dungeon",
		Count:       10,
	}
	assert.NotNil(t, accountTicket)
	assert.Equal(t, "test_dungeon", accountTicket.Dungeontype)
	assert.Equal(t, int32(10), accountTicket.Count)

	// 测试AchievementReward
	achievementReward := &dnfv1.AchievementReward{
		RewardId: 1,
		Type:     2,
		Amount:   100,
		Claimed:  true,
	}
	assert.NotNil(t, achievementReward)
	assert.Equal(t, int32(1), achievementReward.RewardId)
	assert.Equal(t, int32(2), achievementReward.Type)
	assert.Equal(t, int32(100), achievementReward.Amount)
	assert.True(t, achievementReward.Claimed)
}

// testBatch56ActionAndStatus 测试动作和状态系统
func testBatch56ActionAndStatus(t *testing.T) {
	// 测试ActionCountInfo
	actionCountInfo := &dnfv1.ActionCountInfo{
		Combocount:        10,
		Aerialcount:       5,
		Overkillcount1:    3,
		Overkillcount2:    2,
		Countercount:      4,
		Backattackcount:   6,
		Crowdcontrolcount: 7,
		Justevadecount:    8,
	}
	assert.NotNil(t, actionCountInfo)
	assert.Equal(t, int32(10), actionCountInfo.Combocount)
	assert.Equal(t, int32(5), actionCountInfo.Aerialcount)
	assert.Equal(t, int32(3), actionCountInfo.Overkillcount1)
	assert.Equal(t, int32(2), actionCountInfo.Overkillcount2)
	assert.Equal(t, int32(4), actionCountInfo.Countercount)
	assert.Equal(t, int32(6), actionCountInfo.Backattackcount)
	assert.Equal(t, int32(7), actionCountInfo.Crowdcontrolcount)
	assert.Equal(t, int32(8), actionCountInfo.Justevadecount)

	// 测试ActiveStatusDamage
	activeStatusDamage := &dnfv1.ActiveStatusDamage{
		Type:       1,
		Damage:     100,
		Skillindex: 5,
		Itemindex:  10,
		Victimguid: 1234567890,
		Victimtype: 2,
		Tickcount:  3,
	}
	assert.NotNil(t, activeStatusDamage)
	assert.Equal(t, uint32(1), activeStatusDamage.Type)
	assert.Equal(t, int32(100), activeStatusDamage.Damage)
	assert.Equal(t, int32(5), activeStatusDamage.Skillindex)
	assert.Equal(t, int32(10), activeStatusDamage.Itemindex)
	assert.Equal(t, int32(1234567890), activeStatusDamage.Victimguid)
	assert.Equal(t, uint32(2), activeStatusDamage.Victimtype)
	assert.Equal(t, uint32(3), activeStatusDamage.Tickcount)
}

// testBatch56AiAndApc 测试AI和APC系统
func testBatch56AiAndApc(t *testing.T) {
	// 测试AiCharacterInfo
	aiCharacterInfo := &dnfv1.AiCharacterInfo{
		Charguid:   1234567890,
		Ownerguid:  1234567891,
		Objectguid: 100,
	}
	assert.NotNil(t, aiCharacterInfo)
	assert.Equal(t, uint64(1234567890), aiCharacterInfo.Charguid)
	assert.Equal(t, uint64(1234567891), aiCharacterInfo.Ownerguid)
	assert.Equal(t, int32(100), aiCharacterInfo.Objectguid)

	// 测试AiCharacterDetailInfo
	aiCharacterDetailInfo := &dnfv1.AiCharacterDetailInfo{
		Charguid:            1234567890,
		Name:                "Test AI",
		Growtype:            1,
		Secondgrowtype:      2,
		Exp:                 1000,
		Hp:                  100,
		Mp:                  50,
		Level:               85,
		Job:                 5,
		Equipscore:          10000,
		Score:               5000,
		Count:               10,
		Date:                1234567890,
		Spoint:              100,
		Adventureunionlevel: 10,
		Adventureunionexp:   1000000,
	}
	assert.NotNil(t, aiCharacterDetailInfo)
	assert.Equal(t, uint64(1234567890), aiCharacterDetailInfo.Charguid)
	assert.Equal(t, "Test AI", aiCharacterDetailInfo.Name)
	assert.Equal(t, int32(1), aiCharacterDetailInfo.Growtype)
	assert.Equal(t, int32(2), aiCharacterDetailInfo.Secondgrowtype)
	assert.Equal(t, int32(1000), aiCharacterDetailInfo.Exp)
	assert.Equal(t, int32(100), aiCharacterDetailInfo.Hp)
	assert.Equal(t, int32(50), aiCharacterDetailInfo.Mp)
	assert.Equal(t, int32(85), aiCharacterDetailInfo.Level)
	assert.Equal(t, int32(5), aiCharacterDetailInfo.Job)
	assert.Equal(t, int32(10000), aiCharacterDetailInfo.Equipscore)
	assert.Equal(t, int32(5000), aiCharacterDetailInfo.Score)
	assert.Equal(t, int32(10), aiCharacterDetailInfo.Count)
	assert.Equal(t, int64(1234567890), aiCharacterDetailInfo.Date)
	assert.Equal(t, int32(100), aiCharacterDetailInfo.Spoint)
	assert.Equal(t, int32(10), aiCharacterDetailInfo.Adventureunionlevel)
	assert.Equal(t, int64(1000000), aiCharacterDetailInfo.Adventureunionexp)

	// 测试ApcCharacter
	apcCharacter := &dnfv1.ApcCharacter{
		Charguid:       1234567890,
		Hp:             100,
		Mp:             50,
		Job:            5,
		Level:          85,
		Exp:            1000,
		Growtype:       1,
		Secondgrowtype: 2,
		Name:           "Test APC",
		Ownerguid:      1234567891,
		Teamtype:       1,
		Objectguid:     100,
		Equipscore:     10000,
	}
	assert.NotNil(t, apcCharacter)
	assert.Equal(t, uint64(1234567890), apcCharacter.Charguid)
	assert.Equal(t, int32(100), apcCharacter.Hp)
	assert.Equal(t, int32(50), apcCharacter.Mp)
	assert.Equal(t, int32(5), apcCharacter.Job)
	assert.Equal(t, int32(85), apcCharacter.Level)
	assert.Equal(t, int32(1000), apcCharacter.Exp)
	assert.Equal(t, int32(1), apcCharacter.Growtype)
	assert.Equal(t, int32(2), apcCharacter.Secondgrowtype)
	assert.Equal(t, "Test APC", apcCharacter.Name)
	assert.Equal(t, uint64(1234567891), apcCharacter.Ownerguid)
	assert.Equal(t, int32(1), apcCharacter.Teamtype)
	assert.Equal(t, int32(100), apcCharacter.Objectguid)
	assert.Equal(t, int32(10000), apcCharacter.Equipscore)

	// 测试ApcInfo
	apcInfo := &dnfv1.ApcInfo{
		Charguid:       1234567890,
		Name:           "Test APC",
		Growtype:       1,
		Secondgrowtype: 2,
		Level:          85,
		Job:            5,
		Equipscore:     10000,
		Count:          10,
		Date:           1234567890,
	}
	assert.NotNil(t, apcInfo)
	assert.Equal(t, uint64(1234567890), apcInfo.Charguid)
	assert.Equal(t, "Test APC", apcInfo.Name)
	assert.Equal(t, int32(1), apcInfo.Growtype)
	assert.Equal(t, int32(2), apcInfo.Secondgrowtype)
	assert.Equal(t, int32(85), apcInfo.Level)
	assert.Equal(t, int32(5), apcInfo.Job)
	assert.Equal(t, int32(10000), apcInfo.Equipscore)
	assert.Equal(t, int32(10), apcInfo.Count)
	assert.Equal(t, int64(1234567890), apcInfo.Date)
}

// testBatch56AlchemyAndReward 测试炼金和奖励系统
func testBatch56AlchemyAndReward(t *testing.T) {
	// 测试AlchemyRecipeLimit
	alchemyRecipeLimit := &dnfv1.AlchemyRecipeLimit{
		Recipeindex: 1,
		Usecount:    5,
	}
	assert.NotNil(t, alchemyRecipeLimit)
	assert.Equal(t, int32(1), alchemyRecipeLimit.Recipeindex)
	assert.Equal(t, int32(5), alchemyRecipeLimit.Usecount)

	// 测试AllClearReward
	allClearReward := &dnfv1.AllClearReward{
		Index: 1,
		Count: 5,
		Floor: 100,
	}
	assert.NotNil(t, allClearReward)
	assert.Equal(t, int32(1), allClearReward.Index)
	assert.Equal(t, int32(5), allClearReward.Count)
	assert.Equal(t, int32(100), allClearReward.Floor)

	// 测试Appendage
	appendage := &dnfv1.Appendage{
		Appendageindex: 1,
		Count:          5,
	}
	assert.NotNil(t, appendage)
	assert.Equal(t, int32(1), appendage.Appendageindex)
	assert.Equal(t, int32(5), appendage.Count)

	// 测试AppendageExp
	appendageExp := &dnfv1.AppendageExp{
		Index: 1,
		Exp:   100,
	}
	assert.NotNil(t, appendageExp)
	assert.Equal(t, int32(1), appendageExp.Index)
	assert.Equal(t, int32(100), appendageExp.Exp)

	// 测试ArcadePvpInfoCurrency
	arcadePvpInfoCurrency := &dnfv1.ArcadePvpInfoCurrency{
		Index: 1,
		Count: 100,
	}
	assert.NotNil(t, arcadePvpInfoCurrency)
	assert.Equal(t, int32(1), arcadePvpInfoCurrency.Index)
	assert.Equal(t, int32(100), arcadePvpInfoCurrency.Count)

	// 测试Artifact
	artifact := &dnfv1.Artifact{
		Id:    1,
		Level: 10,
		Exp:   1000,
	}
	assert.NotNil(t, artifact)
	assert.Equal(t, int32(1), artifact.Id)
	assert.Equal(t, int32(10), artifact.Level)
	assert.Equal(t, int32(1000), artifact.Exp)

	// 测试ArtifactBaseOption
	artifactBaseOption := &dnfv1.ArtifactBaseOption{
		Option: 1,
		Rarity: 5,
		Locked: false,
	}
	assert.NotNil(t, artifactBaseOption)
	assert.Equal(t, int32(1), artifactBaseOption.Option)
	assert.Equal(t, int32(5), artifactBaseOption.Rarity)
	assert.False(t, artifactBaseOption.Locked)
}

// testBatch56SkillAndAltar 测试技能和祭坛系统
func testBatch56SkillAndAltar(t *testing.T) {
	// 测试AllSkillSlot
	allSkillSlot := &dnfv1.AllSkillSlot{
		Active: []*dnfv1.SkillSlotInfo{
			{
				SlotId:  1,
				SkillId: 1,
				Level:   1,
				Hotkey:  1,
			},
		},
		Buff: []*dnfv1.SkillSlotInfo{
			{
				SlotId:  2,
				SkillId: 2,
				Level:   1,
				Hotkey:  2,
			},
		},
		Combo: []*dnfv1.SkillSlotInfo{
			{
				SlotId:  3,
				SkillId: 3,
				Level:   1,
				Hotkey:  3,
			},
		},
	}
	assert.NotNil(t, allSkillSlot)
	assert.Len(t, allSkillSlot.Active, 1)
	assert.Len(t, allSkillSlot.Buff, 1)
	assert.Len(t, allSkillSlot.Combo, 1)

	// 测试AltarInfo
	altarInfo := &dnfv1.AltarInfo{
		Index:     1,
		Scramble:  2,
		Poolindex: 3,
		State:     4,
		Endtime:   1234567890,
	}
	assert.NotNil(t, altarInfo)
	assert.Equal(t, int32(1), altarInfo.Index)
	assert.Equal(t, int32(2), altarInfo.Scramble)
	assert.Equal(t, int32(3), altarInfo.Poolindex)
	assert.Equal(t, int32(4), altarInfo.State)
	assert.Equal(t, int64(1234567890), altarInfo.Endtime)

	// 测试AttachCrackRequest
	attachCrackRequest := &dnfv1.AttachCrackRequest{
		Index: 1,
		Slot:  2,
	}
	assert.NotNil(t, attachCrackRequest)
	assert.Equal(t, int32(1), attachCrackRequest.Index)
	assert.Equal(t, int32(2), attachCrackRequest.Slot)
}

// testBatch56AttackSquadAndBattlefield 测试攻击队和战场系统
func testBatch56AttackSquadAndBattlefield(t *testing.T) {
	// 测试AttackSquadAdvertisement
	attackSquadAdvertisement := &dnfv1.AttackSquadAdvertisement{
		Register: true,
	}
	assert.NotNil(t, attackSquadAdvertisement)
	assert.True(t, attackSquadAdvertisement.Register)

	// 测试AttackSquadBoardInfo
	attackSquadBoardInfo := &dnfv1.AttackSquadBoardInfo{
		Rpguid:        1234567890,
		Rpname:        "Test Squad",
		Membercount:   5,
		Antievilscore: 1000,
		Publictype:    1,
		Status:        2,
		Raidindex:     3,
		Chivalry:      true,
		Leaderguid:    1234567891,
		Started:       false,
	}
	assert.NotNil(t, attackSquadBoardInfo)
	assert.Equal(t, uint64(1234567890), attackSquadBoardInfo.Rpguid)
	assert.Equal(t, "Test Squad", attackSquadBoardInfo.Rpname)
	assert.Equal(t, int32(5), attackSquadBoardInfo.Membercount)
	assert.Equal(t, int32(1000), attackSquadBoardInfo.Antievilscore)
	assert.Equal(t, int32(1), attackSquadBoardInfo.Publictype)
	assert.Equal(t, int32(2), attackSquadBoardInfo.Status)
	assert.Equal(t, int32(3), attackSquadBoardInfo.Raidindex)
	assert.True(t, attackSquadBoardInfo.Chivalry)
	assert.Equal(t, uint64(1234567891), attackSquadBoardInfo.Leaderguid)
	assert.False(t, attackSquadBoardInfo.Started)

	// 测试AttackSquadBoardUserInfo
	attackSquadBoardUserInfo := &dnfv1.AttackSquadBoardUserInfo{
		Job:      5,
		Growtype: 1,
	}
	assert.NotNil(t, attackSquadBoardUserInfo)
	assert.Equal(t, int32(5), attackSquadBoardUserInfo.Job)
	assert.Equal(t, int32(1), attackSquadBoardUserInfo.Growtype)

	// 测试AttackSquadDetailInfo
	attackSquadDetailInfo := &dnfv1.AttackSquadDetailInfo{
		Rpguid:        1234567890,
		Rpname:        "Test Squad",
		Membercount:   5,
		Antievilscore: 1000,
		Publictype:    1,
		Status:        2,
		Phasestatus:   3,
		Changetime:    1234567890,
		Leadername:    "Test Leader",
		Leaderguid:    1234567891,
		World:         1,
		Channel:       2,
		Ip:            "127.0.0.1",
		Port:          8080,
		Started:       false,
	}
	assert.NotNil(t, attackSquadDetailInfo)
	assert.Equal(t, uint64(1234567890), attackSquadDetailInfo.Rpguid)
	assert.Equal(t, "Test Squad", attackSquadDetailInfo.Rpname)
	assert.Equal(t, int32(5), attackSquadDetailInfo.Membercount)
	assert.Equal(t, int32(1000), attackSquadDetailInfo.Antievilscore)
	assert.Equal(t, int32(1), attackSquadDetailInfo.Publictype)
	assert.Equal(t, int32(2), attackSquadDetailInfo.Status)
	assert.Equal(t, int32(3), attackSquadDetailInfo.Phasestatus)
	assert.Equal(t, int64(1234567890), attackSquadDetailInfo.Changetime)
	assert.Equal(t, "Test Leader", attackSquadDetailInfo.Leadername)
	assert.Equal(t, uint64(1234567891), attackSquadDetailInfo.Leaderguid)
	assert.Equal(t, int32(1), attackSquadDetailInfo.World)
	assert.Equal(t, int32(2), attackSquadDetailInfo.Channel)
	assert.Equal(t, "127.0.0.1", attackSquadDetailInfo.Ip)
	assert.Equal(t, uint32(8080), attackSquadDetailInfo.Port)
	assert.False(t, attackSquadDetailInfo.Started)

	// 测试AttackSquadMemberInfo
	attackSquadMemberInfo := &dnfv1.AttackSquadMemberInfo{
		Partyguid:            1234567890,
		Partyindex:           1,
		Partyslot:            2,
		Disconnecttime:       1234567890,
		Rewardconditioncount: 5,
	}
	assert.NotNil(t, attackSquadMemberInfo)
	assert.Equal(t, uint64(1234567890), attackSquadMemberInfo.Partyguid)
	assert.Equal(t, int32(1), attackSquadMemberInfo.Partyindex)
	assert.Equal(t, int32(2), attackSquadMemberInfo.Partyslot)
	assert.Equal(t, int64(1234567890), attackSquadMemberInfo.Disconnecttime)
	assert.Equal(t, int32(5), attackSquadMemberInfo.Rewardconditioncount)

	// 测试AttackSquadTimer
	attackSquadTimer := &dnfv1.AttackSquadTimer{
		Breakuptime: 1234567890,
	}
	assert.NotNil(t, attackSquadTimer)
	assert.Equal(t, int64(1234567890), attackSquadTimer.Breakuptime)

	// 测试BattlefieldStateInfo
	battlefieldStateInfo := &dnfv1.BattlefieldStateInfo{
		Battlefield: 1,
		Scramble:    2,
		Slotindex:   3,
		State:       4,
		Endtime:     1234567890,
		Garrison:    false,
	}
	assert.NotNil(t, battlefieldStateInfo)
	assert.Equal(t, int32(1), battlefieldStateInfo.Battlefield)
	assert.Equal(t, int32(2), battlefieldStateInfo.Scramble)
	assert.Equal(t, int32(3), battlefieldStateInfo.Slotindex)
	assert.Equal(t, int32(4), battlefieldStateInfo.State)
	assert.Equal(t, int64(1234567890), battlefieldStateInfo.Endtime)
	assert.False(t, battlefieldStateInfo.Garrison)

	// 测试BattlefieldTeamInfo
	battlefieldTeamInfo := &dnfv1.BattlefieldTeamInfo{
		State:      1,
		Win:        5,
		Date:       1234567890,
		Count:      10,
		Clearcount: 8,
	}
	assert.NotNil(t, battlefieldTeamInfo)
	assert.Equal(t, int32(1), battlefieldTeamInfo.State)
	assert.Equal(t, int32(5), battlefieldTeamInfo.Win)
	assert.Equal(t, uint64(1234567890), battlefieldTeamInfo.Date)
	assert.Equal(t, int32(10), battlefieldTeamInfo.Count)
	assert.Equal(t, int32(8), battlefieldTeamInfo.Clearcount)
}

// testBatch56Auction 测试拍卖系统
func testBatch56Auction(t *testing.T) {
	// 测试AuctionBase
	auctionBase := &dnfv1.AuctionBase{
		Guid:        1234567890,
		Upgrade:     10,
		Quality:     5,
		Endurance:   100,
		Enchant:     8,
		Reforge:     3,
		Amplify:     2,
		Aoption:     1,
		Expiretime:  1234567890,
		Rappearance: true,
		Skin:        1,
		Skinguid:    1234567891,
		Locked:      false,
		Seal:        false,
		Type:        1,
		Auid:        1234567892,
		Bidder:      1234567893,
		Price:       10000,
		Enddate:     1234567894,
		Registcount: 5,
		Tera:        100,
		Buyprice:    15000,
		Flag:        true,
		Index:       1,
		Count:       1,
		Season:      1,
	}
	assert.NotNil(t, auctionBase)
	assert.Equal(t, uint64(1234567890), auctionBase.Guid)
	assert.Equal(t, int32(10), auctionBase.Upgrade)
	assert.Equal(t, int32(5), auctionBase.Quality)
	assert.Equal(t, int32(100), auctionBase.Endurance)
	assert.Equal(t, int32(8), auctionBase.Enchant)
	assert.Equal(t, int32(3), auctionBase.Reforge)
	assert.Equal(t, int32(2), auctionBase.Amplify)
	assert.Equal(t, int32(1), auctionBase.Aoption)
	assert.Equal(t, int64(1234567890), auctionBase.Expiretime)
	assert.True(t, auctionBase.Rappearance)
	assert.Equal(t, int32(1), auctionBase.Skin)
	assert.Equal(t, uint64(1234567891), auctionBase.Skinguid)
	assert.False(t, auctionBase.Locked)
	assert.False(t, auctionBase.Seal)
	assert.Equal(t, int32(1), auctionBase.Type)
	assert.Equal(t, uint64(1234567892), auctionBase.Auid)
	assert.Equal(t, uint64(1234567893), auctionBase.Bidder)
	assert.Equal(t, int32(10000), auctionBase.Price)
	assert.Equal(t, uint64(1234567894), auctionBase.Enddate)
	assert.Equal(t, int32(5), auctionBase.Registcount)
	assert.Equal(t, int32(100), auctionBase.Tera)
	assert.Equal(t, int32(15000), auctionBase.Buyprice)
	assert.True(t, auctionBase.Flag)
	assert.Equal(t, int32(1), auctionBase.Index)
	assert.Equal(t, int32(1), auctionBase.Count)
	assert.Equal(t, int32(1), auctionBase.Season)

	// 测试AuctionEquip
	auctionEquip := &dnfv1.AuctionEquip{
		Guid:         1234567890,
		Upgrade:      10,
		Quality:      5,
		Endurance:    100,
		Reforge:      3,
		Amplify:      2,
		Aoption:      1,
		Expiretime:   1234567890,
		Rappearance:  true,
		Skin:         1,
		Skinguid:     1234567891,
		Locked:       false,
		Seal:         false,
		Enchantindex: 1,
		Enchant:      8,
		Type:         1,
		Auid:         1234567892,
		Bidder:       1234567893,
		Price:        10000,
		Enddate:      1234567894,
		Registcount:  5,
		Tera:         100,
		Buyprice:     15000,
		Flag:         1,
		Index:        1,
		Count:        1,
		Season:       1,
	}
	assert.NotNil(t, auctionEquip)
	assert.Equal(t, uint64(1234567890), auctionEquip.Guid)
	assert.Equal(t, int32(10), auctionEquip.Upgrade)
	assert.Equal(t, int32(5), auctionEquip.Quality)
	assert.Equal(t, int32(100), auctionEquip.Endurance)
	assert.Equal(t, int32(3), auctionEquip.Reforge)
	assert.Equal(t, int32(2), auctionEquip.Amplify)
	assert.Equal(t, int32(1), auctionEquip.Aoption)
	assert.Equal(t, int64(1234567890), auctionEquip.Expiretime)
	assert.True(t, auctionEquip.Rappearance)
	assert.Equal(t, int32(1), auctionEquip.Skin)
	assert.Equal(t, uint64(1234567891), auctionEquip.Skinguid)
	assert.False(t, auctionEquip.Locked)
	assert.False(t, auctionEquip.Seal)
	assert.Equal(t, int32(1), auctionEquip.Enchantindex)
	assert.Equal(t, int32(8), auctionEquip.Enchant)
	assert.Equal(t, int32(1), auctionEquip.Type)
	assert.Equal(t, uint64(1234567892), auctionEquip.Auid)
	assert.Equal(t, uint64(1234567893), auctionEquip.Bidder)
	assert.Equal(t, int32(10000), auctionEquip.Price)
	assert.Equal(t, uint64(1234567894), auctionEquip.Enddate)
	assert.Equal(t, int32(5), auctionEquip.Registcount)
	assert.Equal(t, int32(100), auctionEquip.Tera)
	assert.Equal(t, int32(15000), auctionEquip.Buyprice)
	assert.Equal(t, int32(1), auctionEquip.Flag)
	assert.Equal(t, int32(1), auctionEquip.Index)
	assert.Equal(t, int32(1), auctionEquip.Count)
	assert.Equal(t, int32(1), auctionEquip.Season)

	// 测试AuctionItemIndex
	auctionItemIndex := &dnfv1.AuctionItemIndex{
		Index: 1,
		Qty:   5,
	}
	assert.NotNil(t, auctionItemIndex)
	assert.Equal(t, int32(1), auctionItemIndex.Index)
	assert.Equal(t, int32(5), auctionItemIndex.Qty)

	// 测试AuctionStackable
	auctionStackable := &dnfv1.AuctionStackable{
		Guid:         1234567890,
		Upgrade:      10,
		Quality:      5,
		Endurance:    100,
		Reforge:      3,
		Amplify:      2,
		Aoption:      1,
		Expiretime:   1234567890,
		Rappearance:  true,
		Skin:         1,
		Skinguid:     1234567891,
		Locked:       false,
		Seal:         false,
		Enchantindex: 1,
		Enchant:      8,
		Type:         1,
		Auid:         1234567892,
		Bidder:       1234567893,
		Price:        10000,
		Enddate:      1234567894,
		Registcount:  5,
		Tera:         100,
		Buyprice:     15000,
		Flag:         1,
		Index:        1,
		Count:        1,
		Season:       1,
	}
	assert.NotNil(t, auctionStackable)
	assert.Equal(t, uint64(1234567890), auctionStackable.Guid)
	assert.Equal(t, int32(10), auctionStackable.Upgrade)
	assert.Equal(t, int32(5), auctionStackable.Quality)
	assert.Equal(t, int32(100), auctionStackable.Endurance)
	assert.Equal(t, int32(3), auctionStackable.Reforge)
	assert.Equal(t, int32(2), auctionStackable.Amplify)
	assert.Equal(t, int32(1), auctionStackable.Aoption)
	assert.Equal(t, int64(1234567890), auctionStackable.Expiretime)
	assert.True(t, auctionStackable.Rappearance)
	assert.Equal(t, int32(1), auctionStackable.Skin)
	assert.Equal(t, uint64(1234567891), auctionStackable.Skinguid)
	assert.False(t, auctionStackable.Locked)
	assert.False(t, auctionStackable.Seal)
	assert.Equal(t, int32(1), auctionStackable.Enchantindex)
	assert.Equal(t, int32(8), auctionStackable.Enchant)
	assert.Equal(t, int32(1), auctionStackable.Type)
	assert.Equal(t, uint64(1234567892), auctionStackable.Auid)
	assert.Equal(t, uint64(1234567893), auctionStackable.Bidder)
	assert.Equal(t, int32(10000), auctionStackable.Price)
	assert.Equal(t, uint64(1234567894), auctionStackable.Enddate)
	assert.Equal(t, int32(5), auctionStackable.Registcount)
	assert.Equal(t, int32(100), auctionStackable.Tera)
	assert.Equal(t, int32(15000), auctionStackable.Buyprice)
	assert.Equal(t, int32(1), auctionStackable.Flag)
	assert.Equal(t, int32(1), auctionStackable.Index)
	assert.Equal(t, int32(1), auctionStackable.Count)
	assert.Equal(t, int32(1), auctionStackable.Season)
}

// testBatch56Avatar 测试头像系统
func testBatch56Avatar(t *testing.T) {
	// 测试AvatarComposeMaterial
	avatarComposeMaterial := &dnfv1.AvatarComposeMaterial{
		Index: 1,
		Count: 5,
	}
	assert.NotNil(t, avatarComposeMaterial)
	assert.Equal(t, int32(1), avatarComposeMaterial.Index)
	assert.Equal(t, int32(5), avatarComposeMaterial.Count)

	// 测试AvatarDisassembledMaterial
	avatarDisassembledMaterial := &dnfv1.AvatarDisassembledMaterial{
		Index: 1,
		Count: 5,
	}
	assert.NotNil(t, avatarDisassembledMaterial)
	assert.Equal(t, int32(1), avatarDisassembledMaterial.Index)
	assert.Equal(t, int32(5), avatarDisassembledMaterial.Count)

	// 测试AvatarGuid
	avatarGuid := &dnfv1.AvatarGuid{
		Guid: 1234567890,
	}
	assert.NotNil(t, avatarGuid)
	assert.Equal(t, uint64(1234567890), avatarGuid.Guid)

	// 测试AvatarItem
	avatarItem := &dnfv1.AvatarItem{
		Index:      1,
		Count:      1,
		Guid:       1234567890,
		Expiretime: 1234567890,
		Option:     1,
		Locked:     false,
		Scount:     0,
		Tcount:     0,
		Sindex:     0,
	}
	assert.NotNil(t, avatarItem)
	assert.Equal(t, int32(1), avatarItem.Index)
	assert.Equal(t, int32(1), avatarItem.Count)
	assert.Equal(t, uint64(1234567890), avatarItem.Guid)
	assert.Equal(t, uint64(1234567890), avatarItem.Expiretime)
	assert.Equal(t, int32(1), avatarItem.Option)
	assert.False(t, avatarItem.Locked)
	assert.Equal(t, int32(0), avatarItem.Scount)
	assert.Equal(t, int32(0), avatarItem.Tcount)
	assert.Equal(t, int32(0), avatarItem.Sindex)
}

// testBatch56Battleleague 测试战斗联盟系统
func testBatch56Battleleague(t *testing.T) {
	// 测试BattleleagueBuff
	battleleagueBuff := &dnfv1.BattleleagueBuff{
		Index:       1,
		Customvalue: 100,
	}
	assert.NotNil(t, battleleagueBuff)
	assert.Equal(t, int32(1), battleleagueBuff.Index)
	assert.Equal(t, int32(100), battleleagueBuff.Customvalue)

	// 测试BattleleagueContribute
	battleleagueContribute := &dnfv1.BattleleagueContribute{
		Charguid:     1234567890,
		Contribution: 1000,
	}
	assert.NotNil(t, battleleagueContribute)
	assert.Equal(t, uint64(1234567890), battleleagueContribute.Charguid)
	assert.Equal(t, int32(1000), battleleagueContribute.Contribution)

	// 测试BattleleaguePveRecord
	battleleaguePveRecord := &dnfv1.BattleleaguePveRecord{
		Charguid:     1234567890,
		Contribution: 1000,
	}
	assert.NotNil(t, battleleaguePveRecord)
	assert.Equal(t, uint64(1234567890), battleleaguePveRecord.Charguid)
	assert.Equal(t, int32(1000), battleleaguePveRecord.Contribution)

	// 测试BattleleaguePvpRecord
	battleleaguePvpRecord := &dnfv1.BattleleaguePvpRecord{
		Charguid:     1234567890,
		Contribution: 1000,
	}
	assert.NotNil(t, battleleaguePvpRecord)
	assert.Equal(t, uint64(1234567890), battleleaguePvpRecord.Charguid)
	assert.Equal(t, int32(1000), battleleaguePvpRecord.Contribution)

	// 测试BattleleagueReward
	battleleagueReward := &dnfv1.BattleleagueReward{
		Index: 1,
		Count: 5,
	}
	assert.NotNil(t, battleleagueReward)
	assert.Equal(t, int32(1), battleleagueReward.Index)
	assert.Equal(t, int32(5), battleleagueReward.Count)
}
