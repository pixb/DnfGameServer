package store

import (
	"context"
	"database/sql"
	"errors"
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

	// ==================== 邮件相关 ====================
	CreateMail(ctx context.Context, create *Mail) (*Mail, error)
	UpdateMail(ctx context.Context, update *UpdateMail) error
	GetMail(ctx context.Context, find *FindMail) (*Mail, error)
	ListMails(ctx context.Context, find *FindMail) ([]*Mail, error)
	DeleteMail(ctx context.Context, delete *DeleteMail) error

	// ==================== 拍卖行相关 ====================
	CreateAuctionItem(ctx context.Context, create *AuctionItem) (*AuctionItem, error)
	UpdateAuctionItem(ctx context.Context, update *UpdateAuctionItem) error
	GetAuctionItem(ctx context.Context, find *FindAuctionItem) (*AuctionItem, error)
	ListAuctionItems(ctx context.Context, find *FindAuctionItem) ([]*AuctionItem, error)
	ListAuctionItemsBySeller(ctx context.Context, sellerID uint64) ([]*AuctionItem, error)
	DeleteAuctionItem(ctx context.Context, delete *DeleteAuctionItem) error
	CountAuctionItemsBySeller(ctx context.Context, sellerID uint64) (int, error)

	CreateAuctionHistory(ctx context.Context, create *CreateAuctionHistory) (*AuctionHistory, error)
	ListAuctionHistory(ctx context.Context, find *FindAuctionHistory) ([]*AuctionHistory, error)

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
}
