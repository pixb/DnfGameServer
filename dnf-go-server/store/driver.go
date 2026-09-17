package store

import (
	"context"
	"database/sql"
	"errors"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

// 全局错误定义
var (
	ErrNotFound     = errors.New("not found")
	ErrDuplicate    = errors.New("duplicate entry")
	ErrInvalidInput = errors.New("invalid input")
	ErrDBConnection = errors.New("database connection error")
)

// RowStatus 行状态
type RowStatus string

const (
	RowStatusNormal  RowStatus = "NORMAL"
	RowStatusDeleted RowStatus = "DELETED"
)

// RoleType 角色类型
type RoleType int

const (
	RoleUser  RoleType = 0
	RoleAdmin RoleType = 1
	RoleGM    RoleType = 2
)

// BaseModel 基础模型
type BaseModel struct {
	ID        uint64
	CreatedAt int64
	UpdatedAt int64
	RowStatus RowStatus
}

// FindBase 基础查询参数
type FindBase struct {
	ID        *uint64
	RowStatus *RowStatus
	Limit     *int
	Offset    *int
}

// DeleteBase 基础删除参数
type DeleteBase struct {
	ID uint64
}

// Driver 数据库驱动接口
type Driver interface {
	// 生命周期
	GetDB() *sql.DB
	Close() error
	IsInitialized(ctx context.Context) (bool, error)
	GetCurrentSchemaVersion() string

	// ==================== 账户相关 ====================
	CreateAccount(ctx context.Context, create *Account) (*Account, error)
	UpdateAccount(ctx context.Context, update *UpdateAccount) (*Account, error)
	GetAccount(ctx context.Context, find *FindAccount) (*Account, error)
	ListAccounts(ctx context.Context, find *FindAccount) ([]*Account, error)
	DeleteAccount(ctx context.Context, delete *DeleteAccount) error

	// ==================== 角色相关 ====================
	CreateRole(ctx context.Context, create *Role) (*Role, error)
	UpdateRole(ctx context.Context, update *UpdateRole) (*Role, error)
	GetRole(ctx context.Context, find *FindRole) (*Role, error)
	GetRoleByName(ctx context.Context, name string) (*Role, error)
	ListRoles(ctx context.Context, find *FindRole) ([]*Role, error)
	ListRolesByAccount(ctx context.Context, accountID uint64) ([]*Role, error)
	DeleteRole(ctx context.Context, delete *DeleteRole) error
	CountRolesByAccount(ctx context.Context, accountID uint64) (int, error)

	// ==================== 角色属性 ====================
	CreateRoleAttributes(ctx context.Context, create *RoleAttributes) (*RoleAttributes, error)
	UpdateRoleAttributes(ctx context.Context, update *UpdateRoleAttributes) error
	GetRoleAttributes(ctx context.Context, roleID uint64) (*RoleAttributes, error)

	// ==================== 角色货币 ====================
	GetRoleCurrency(ctx context.Context, roleID uint64) (*RoleCurrency, error)
	UpdateRoleCurrency(ctx context.Context, update *RoleCurrency) error

	// ==================== 背包物品 ====================
	CreateBagItem(ctx context.Context, create *BagItem) (*BagItem, error)
	UpdateBagItem(ctx context.Context, update *UpdateBagItem) error
	GetBagItem(ctx context.Context, find *FindBagItem) (*BagItem, error)
	ListBagItems(ctx context.Context, find *FindBagItem) ([]*BagItem, error)
	ListBagItemsByRole(ctx context.Context, roleID uint64) ([]*BagItem, error)
	DeleteBagItem(ctx context.Context, delete *DeleteBagItem) error
	// ==================== 物品模板(2026-09-08 第七十三轮) ====================
	GetItemTemplate(ctx context.Context, itemID int32) (*ItemTemplate, error)
	ListItemTemplates(ctx context.Context) ([]*ItemTemplate, error)

	// ==================== 任务相关 ====================
	CreateQuest(ctx context.Context, create *Quest) (*Quest, error)
	UpdateQuest(ctx context.Context, update *UpdateQuest) (*Quest, error)
	GetQuest(ctx context.Context, find *FindQuest) (*Quest, error)
	ListQuests(ctx context.Context, find *FindQuest) ([]*Quest, error)

	CreateRoleQuest(ctx context.Context, create *RoleQuest) (*RoleQuest, error)
	UpdateRoleQuest(ctx context.Context, update *UpdateRoleQuest) error
	GetRoleQuest(ctx context.Context, find *FindRoleQuest) (*RoleQuest, error)
	ListRoleQuests(ctx context.Context, roleID uint64) ([]*RoleQuest, error)
	DeleteRoleQuest(ctx context.Context, delete *DeleteRoleQuest) error

	// ==================== 公会相关 ====================
	CreateGuild(ctx context.Context, create *Guild) (*Guild, error)
	UpdateGuild(ctx context.Context, update *UpdateGuild) (*Guild, error)
	GetGuild(ctx context.Context, find *FindGuild) (*Guild, error)
	ListGuilds(ctx context.Context, find *FindGuild) ([]*Guild, error)
	DeleteGuild(ctx context.Context, delete *DeleteGuild) error

	AddGuildMember(ctx context.Context, create *GuildMember) (*GuildMember, error)
	UpdateGuildMember(ctx context.Context, update *UpdateGuildMember) error
	GetGuildMember(ctx context.Context, find *FindGuildMember) (*GuildMember, error)
	ListGuildMembers(ctx context.Context, guildID uint64) ([]*GuildMember, error)
	RemoveGuildMember(ctx context.Context, delete *DeleteGuildMember) error

	// ==================== 好友相关 ====================
	CreateFriend(ctx context.Context, create *Friend) (*Friend, error)
	UpdateFriend(ctx context.Context, update *UpdateFriend) error
	GetFriend(ctx context.Context, find *FindFriend) (*Friend, error)
	ListFriends(ctx context.Context, roleID uint64) ([]*Friend, error)
	DeleteFriend(ctx context.Context, delete *DeleteFriend) error

	// ==================== 好友申请相关(2026-09-06 第三十九轮) ====================
	CreateFriendRequest(ctx context.Context, create *FriendRequest) (*FriendRequest, error)
	GetFriendRequest(ctx context.Context, find *FindFriendRequest) (*FriendRequest, error)
	ListFriendRequests(ctx context.Context, find *FindFriendRequest) ([]*FriendRequest, error)
	UpdateFriendRequest(ctx context.Context, update *UpdateFriendRequest) error

	// ==================== 邮件相关 ====================
	CreateMail(ctx context.Context, create *Mail) (*Mail, error)
	UpdateMail(ctx context.Context, update *UpdateMail) error
	GetMail(ctx context.Context, find *FindMail) (*Mail, error)
	ListMails(ctx context.Context, find *FindMail) ([]*Mail, error)
	DeleteMail(ctx context.Context, delete *DeleteMail) error
	// DeleteExpiredMails 删除所有过期邮件(expire_at > 0 且 < now), 返回删除行数
	// 2026-09-06 第十七轮: 过期邮件清理(发信可带 expire_at, 0 表示永不过期)
	DeleteExpiredMails(ctx context.Context, now int64) (int64, error)
	// ClaimMail 条件领取附件标记: UPDATE mail SET is_claimed=1 WHERE id=? AND is_claimed=0
	// 返回是否抢到(影响行数=1); 用于防并发重复领取
	// 2026-09-07 第七十二轮: 领取先抢锁再入包, 失败回滚
	ClaimMail(ctx context.Context, id uint64) (bool, error)

	// ==================== 拍卖行相关 ====================
	CreateAuctionItem(ctx context.Context, create *AuctionItem) (*AuctionItem, error)
	UpdateAuctionItem(ctx context.Context, update *UpdateAuctionItem) error
	GetAuctionItem(ctx context.Context, find *FindAuctionItem) (*AuctionItem, error)
	ListAuctionItems(ctx context.Context, find *FindAuctionItem) ([]*AuctionItem, error)
	// CountAuctionItems 统计拍卖物品条数(2026-09-07 第六十五轮, 搜索分页独立计数)
	CountAuctionItems(ctx context.Context, find *FindAuctionItem) (int, error)
	ListAuctionItemsBySeller(ctx context.Context, sellerID uint64) ([]*AuctionItem, error)
	DeleteAuctionItem(ctx context.Context, delete *DeleteAuctionItem) error
	CountAuctionItemsBySeller(ctx context.Context, sellerID uint64) (int, error)

	CreateAuctionHistory(ctx context.Context, create *CreateAuctionHistory) (*AuctionHistory, error)
	ListAuctionHistory(ctx context.Context, find *FindAuctionHistory) ([]*AuctionHistory, error)
	// SettleExpiredAuctions 到期结算(2026-09-07 第五十七轮): 过期未售拍卖 → Expired + 退最高出价者冻结金 + 物品退回卖家背包
	SettleExpiredAuctions(ctx context.Context) (int, error)
	// TryBidAuction 原子抢锁出价(2026-09-07 第五十九轮): 条件 UPDATE(状态=Selling 且 bid_price<出价) → 影响行数=1 表示抢锁成功
	TryBidAuction(ctx context.Context, auctionID, bidderID uint64, bidderName string, bidPrice int64) (bool, error)
	// TryBuyoutAuction 原子抢锁买断(2026-09-07 第五十九轮): 条件 UPDATE(状态=Selling) 置 Sold 并落成交买家 → 影响行数=1 表示抢锁成功
	TryBuyoutAuction(ctx context.Context, auctionID, buyerID uint64, buyerName string, bidPrice int64) (bool, error)

	// ==================== 系统设置 ====================
	GetInstanceBasicSetting(ctx context.Context) (*InstanceBasicSetting, error)
	UpsertInstanceSetting(ctx context.Context, setting *InstanceSetting) (*InstanceSetting, error)

	// ==================== 技能相关 ====================
	GetSkill(ctx context.Context, find *FindSkill) (*Skill, error)
	ListSkills(ctx context.Context, find *FindSkill) ([]*Skill, error)
	GetRoleSkill(ctx context.Context, find *FindRoleSkill) (*RoleSkill, error)
	ListRoleSkills(ctx context.Context, roleID uint64) ([]*RoleSkill, error)
	CreateRoleSkill(ctx context.Context, create *CreateRoleSkill) (*RoleSkill, error)
	UpdateRoleSkill(ctx context.Context, update *UpdateRoleSkill) error
	// UpdateRoleSkillLastCast 条件更新技能最后施放时间(2026-09-07 第六十八轮):
	// 仅当 last_cast_at 仍等于 expected 时更新(乐观锁防并发), 返回是否更新成功
	UpdateRoleSkillLastCast(ctx context.Context, roleID uint64, skillID int32, now, expected int64) (bool, error)

	// ==================== 冒险相关 ====================
	GetAdventureData(ctx context.Context, find *FindAdventureData) (*AdventureData, error)
	CreateAdventureData(ctx context.Context, create *CreateAdventureData) (*AdventureData, error)
	UpdateAdventureData(ctx context.Context, update *UpdateAdventureData) error

	CreateAdventureStorageItem(ctx context.Context, create *CreateAdventureStorageItem) (*AdventureStorageItem, error)
	UpdateAdventureStorageItem(ctx context.Context, update *UpdateAdventureStorageItem) error
	ListAdventureStorageItems(ctx context.Context, find *FindAdventureStorageItem) ([]*AdventureStorageItem, error)
	DeleteAdventureStorageItem(ctx context.Context, id uint64) error

	CreateAdventureReap(ctx context.Context, create *CreateAdventureReap) (*AdventureReap, error)
	UpdateAdventureReap(ctx context.Context, update *UpdateAdventureReap) error
	ListAdventureReaps(ctx context.Context, roleID uint64) ([]*AdventureReap, error)
	DeleteAdventureReap(ctx context.Context, id uint64) error

	CreateAdventureBook(ctx context.Context, create *CreateAdventureBook) (*AdventureBook, error)
	UpdateAdventureBook(ctx context.Context, update *UpdateAdventureBook) error
	GetAdventureBook(ctx context.Context, find *FindAdventureBook) (*AdventureBook, error)
	ListAdventureBooks(ctx context.Context, roleID uint64) ([]*AdventureBook, error)

	CreateAdventureBookCondition(ctx context.Context, create *CreateAdventureBookCondition) (*AdventureBookCondition, error)
	UpdateAdventureBookCondition(ctx context.Context, update *UpdateAdventureBookCondition) error

	CreateAdventureBookReward(ctx context.Context, create *CreateAdventureBookReward) (*AdventureBookReward, error)
	ClaimAdventureBookReward(ctx context.Context, claim *ClaimAdventureBookReward) error
	ListAdventureBookRewards(ctx context.Context, bookID int32) ([]*AdventureBookReward, error)

	// ==================== 成就相关 ====================
	GetAchievements(ctx context.Context, roleID uint64, queryType int32) ([]*AchievementInfo, error)
	ClaimAchievementReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32) (*AchievementRewardResult, error)
	GetAchievementList(ctx context.Context, roleID uint64, queryType int32) (*AchievementListResult, error)
	ClaimAchievementBonusReward(ctx context.Context, roleID uint64, achievementID uint32, rewardType uint32, rewardIndex uint32, rewardCount uint32) (*AchievementBonusRewardResult, error)

	// ==================== 冒险联盟相关 ====================
	GetAdventureUnionInfo(ctx context.Context, roleID uint64) (*AdventureUnionInfo, error)
	ChangeAdventureUnionName(ctx context.Context, roleID uint64, name string) error
	StartAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32, expeditionType uint32) error
	CancelAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32) error
	ClaimAdventureUnionExpeditionReward(ctx context.Context, roleID uint64, expeditionID uint32) error
	StartAdventureUnionSubdue(ctx context.Context, roleID uint64, subdueID uint32, subdueType uint32, characterGUID uint64) error
	ClaimAdventureUnionSubdueReward(ctx context.Context, roleID uint64, subdueID uint32) error
	OpenAdventureUnionShareboardSlot(ctx context.Context, roleID uint64, slotID uint32) error
	SetAdventureUnionShareboard(ctx context.Context, roleID uint64, slotID uint32, slotType uint32, show bool) error
	ClaimAdventureReapReward(ctx context.Context, roleID uint64, reapID uint32) error
	StartAdventureUnionSearch(ctx context.Context, roleID uint64) error
	ClaimAdventureUnionCollectionReward(ctx context.Context, roleID uint64, collectionID uint32) error
	ClaimAdventureUnionLevelReward(ctx context.Context, roleID uint64, level uint32) error

	// ==================== 制作相关 ====================
	EmblemUpgrade(ctx context.Context, roleID uint64, index int32, tryCount int32, talisman int32) (*EmblemUpgradeResult, error)
	EmblemUpgradeQuick(ctx context.Context, roleID uint64, source []*dnfv1.IndexCount, target int32) (*EmblemUpgradeQuickResult, error)
	AvatarCompose(ctx context.Context, roleID uint64, guids []uint64) (*AvatarComposeResult, error)
	GetProductionInfo(ctx context.Context, roleID uint64, slotType int32) (*ProductionInfoResult, error)
	ProductionRegister(ctx context.Context, roleID uint64, slotIndex int32, recipeIndex int32, count int32) (*ProductionRegisterResult, error)
	ItemCombine(ctx context.Context, roleID uint64, index int32, materialItems []*dnfv1.MaterialItem, count int32) (*ItemCombineResult, error)
	ItemDisjoint(ctx context.Context, roleID uint64, guids []uint64) (*ItemDisjointResult, error)
	CardCompose(ctx context.Context, roleID uint64, userCardList []*dnfv1.CardCompose) (*CardComposeResult, error)
	WardrobeSetSlot(ctx context.Context, roleID uint64) error

	// ==================== 组队相关 ====================
	SearchPartyList(ctx context.Context, dungeonIndex, minLevel, maxLevel uint32) ([]*PartyInfo, error)
	RecommendGroup(ctx context.Context, dungeonIndex uint32) ([]*PartyInfo, error)
	ControlGroup(ctx context.Context, roleID uint64, action uint32, targetGuid uint64, partyGuid uint64) error
	UpdatePartySetting(ctx context.Context, roleID uint64, setting *PartySetting) error // 2026-09-07 第五十轮
	StartMultiPlay(ctx context.Context, roleID uint64, partyGuid uint64) (*StartMultiPlayResult, error)
	SyncDungeon(ctx context.Context, roleID uint64, stageID, progress uint32) error
	MultiPlayDungeonEnterComplete(ctx context.Context, roleID uint64, stageID uint32) error
	GetPartyLoadingStatus(ctx context.Context, roleID uint64) ([]uint64, []uint64, error)
	ReadyToLockstep(ctx context.Context, roleID uint64, ready bool) error
	VoteKickOut(ctx context.Context, roleID, targetGuid uint64) error
	ConnectBattleServer(ctx context.Context, matchingGuid, charguid uint64, authKey string) (string, uint32, error)
	CheckProhibitedWord(ctx context.Context, word string) (bool, error)
	HalfOpenPartyAccept(ctx context.Context, roleID, partyGuid, targetGuid uint64) error // 2026-09-07 第五十二轮: targetGuid>0 指定申请者, 0=接受全部
	HalfOpenPartyRefuse(ctx context.Context, roleID, partyGuid, targetGuid uint64) error // 2026-09-07 第五十二轮: targetGuid>0 指定, 0=拒绝全部
	ControlGroupCustom(ctx context.Context, roleID uint64, customData []byte) error
	ControlGroupQueryarea(ctx context.Context, roleID uint64) error
	HalfOpenPartyJoin(ctx context.Context, roleID, partyGuid uint64) error
	PartyDungeonCondition(ctx context.Context, roleID uint64, dungeonIndex uint32) error
	MultiPlayStartDungeon(ctx context.Context, roleID uint64, stageID uint32) error
	RequestToReEnterAcceptDungeon(ctx context.Context, roleID, charguid uint64) error
	RequestToReEnterDungeon(ctx context.Context, roleID, charguid uint64) error
	SuggestMoveParty(ctx context.Context, roleID uint64, area uint32) error
	TargetUserPartyInfo(ctx context.Context, roleID, targetGuid uint64) (*PartyInfo, error)
	// GetPartyByGuid 按队伍ID查队伍(2026-09-07 第六十二轮, 成员变化广播用)
	GetPartyByGuid(ctx context.Context, partyGuid uint64) (*PartyInfo, error)
	// GetPartyByRoleID 按角色ID查当前队伍(2026-09-07 第六十三轮, 离队/踢人/队长转移广播用)
	GetPartyByRoleID(ctx context.Context, roleID uint64) (*PartyInfo, error)
	// ListPartyRequests 按队伍ID查申请者角色列表(2026-09-07 第六十七轮, 拒绝全部时推送用)
	ListPartyRequests(ctx context.Context, partyGuid uint64) ([]uint64, error)
	WaitinigToUsersLoading(ctx context.Context, roleID uint64) error
	// TeamRankPosition 我的队伍在全服队伍平均等级榜的位置(2026-09-06 第四十三轮)
	// 无队伍返回 (0, 0, nil)
	TeamRankPosition(ctx context.Context, roleID uint64) (rank, total int, err error)

	// ==================== PK 相关 ====================
	ListPvpRecords(ctx context.Context, roleID uint64, limit int) ([]*PvpRecord, error)
	GetPvpStats(ctx context.Context, roleID uint64) (*PvpStats, error)
	GetActivePvpSeason(ctx context.Context) (*PvpSeason, error)
	ListPvpRewards(ctx context.Context, roleID uint64) ([]*PvpReward, error)
	ListPvpMatchTypes(ctx context.Context) ([]*PvpMatchType, error)
	CreatePvpMatchType(ctx context.Context, t *PvpMatchType) error
	SubmitPvpBattleResult(ctx context.Context, record *PvpRecord, stats *PvpStats) error
	CreatePvpMatching(ctx context.Context, m *PvpMatching) (*PvpMatching, error)
	UpdatePvpMatchingStatus(ctx context.Context, matchingID uint64, status uint32) error
	ListPvpMatchingsByRole(ctx context.Context, roleID uint64, status uint32) ([]*PvpMatching, error)
	ListRaidEntrances(ctx context.Context, roleID uint64) ([]*RaidEntrance, error)
	UpsertRaidEntrance(ctx context.Context, e *RaidEntrance) error
	ResetDailyRaidEntrances(ctx context.Context, roleID uint64) error
	ListPvpRanking(ctx context.Context, limit int) ([]*PvpRankingEntry, error)
	ListPvpMatchHistory(ctx context.Context, roleID uint64, limit int) ([]*PvpMatchHistoryEntry, error)

	// ==================== 活动相关 ====================
	ListEventConfigs(ctx context.Context, find *FindEventConfig) ([]*EventConfig, error)
	GetEventConfig(ctx context.Context, find *FindEventConfig) (*EventConfig, error)
	CreateEventConfig(ctx context.Context, create *EventConfig) (*EventConfig, error)
	UpdateEventConfig(ctx context.Context, update *UpdateEventConfig) error
	DeleteEventConfig(ctx context.Context, id uint64) error
	GetEventProgress(ctx context.Context, find *FindEventProgress) (*EventProgress, error)
	ListEventProgress(ctx context.Context, roleID uint64) ([]*EventProgress, error)
	UpsertEventProgress(ctx context.Context, p *EventProgress) (*EventProgress, error)
	UpdateEventProgress(ctx context.Context, update *UpdateEventProgress) error

	// ==================== 背包扩容相关 ====================
	GetBagExpand(ctx context.Context, find *FindBagExpand) (*BagExpand, error)
	UpsertBagExpand(ctx context.Context, b *BagExpand) (*BagExpand, error)

	// ==================== 行为日志相关 ====================
	// 2026-09-06 第十九轮: rank/log TCP handler 实化
	RecordBehaviorLog(ctx context.Context, log *BehaviorLog) error
	ListBehaviorLogs(ctx context.Context, roleID uint64, limit int) ([]*BehaviorLog, error)
	StatisticBehaviorLogs(ctx context.Context, roleID uint64) (map[string]int64, error)
	DeleteBehaviorLogs(ctx context.Context, ids []uint64) (int64, error)
	CleanBehaviorLogs(ctx context.Context, before int64) (int64, error)
}
