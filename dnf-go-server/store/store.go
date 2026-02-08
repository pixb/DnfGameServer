package store

import (
	"context"
	"fmt"
	"time"
)

// Store 数据库访问层包装器
type Store struct {
	driver  Driver
	profile ProfileProvider

	// 缓存
	cacheConfig  Config
	accountCache *Cache
	roleCache    *Cache
	guildCache   *Cache
	settingCache *Cache
}

// ProfileProvider 配置接口
type ProfileProvider interface {
	GetDriver() string
	GetDSN() string
	GetMode() string
}

// New 创建Store实例
func New(driver Driver, profile ProfileProvider) *Store {
	cacheConfig := Config{
		DefaultTTL:      10 * time.Minute,
		CleanupInterval: 5 * time.Minute,
		MaxItems:        1000,
	}

	return &Store{
		driver:       driver,
		profile:      profile,
		cacheConfig:  cacheConfig,
		accountCache: NewCache(cacheConfig),
		roleCache:    NewCache(cacheConfig),
		guildCache:   NewCache(cacheConfig),
		settingCache: NewCache(cacheConfig),
	}
}

// GetDriver 获取底层Driver
func (s *Store) GetDriver() Driver {
	return s.driver
}

// Close 关闭Store
func (s *Store) Close() error {
	s.accountCache.Close()
	s.roleCache.Close()
	s.guildCache.Close()
	s.settingCache.Close()
	return s.driver.Close()
}

// ==================== 账户相关Store方法 ====================

// CreateAccount 创建账户
func (s *Store) CreateAccount(ctx context.Context, create *Account) (*Account, error) {
	account, err := s.driver.CreateAccount(ctx, create)
	if err != nil {
		return nil, err
	}
	s.accountCache.Set(ctx, fmt.Sprintf("%d", account.ID), account)
	s.accountCache.Set(ctx, fmt.Sprintf("openid:%s", account.OpenID), account)
	return account, nil
}

// GetAccount 获取账户(带缓存)
func (s *Store) GetAccount(ctx context.Context, find *FindAccount) (*Account, error) {
	// 尝试从缓存获取
	if find.ID != nil {
		if cached, ok := s.accountCache.Get(ctx, fmt.Sprintf("%d", *find.ID)); ok {
			if account, ok := cached.(*Account); ok {
				return account, nil
			}
		}
	}

	if find.OpenID != nil {
		if cached, ok := s.accountCache.Get(ctx, fmt.Sprintf("openid:%s", *find.OpenID)); ok {
			if account, ok := cached.(*Account); ok {
				return account, nil
			}
		}
	}

	// 从数据库获取
	account, err := s.driver.GetAccount(ctx, find)
	if err != nil {
		return nil, err
	}

	// 写入缓存
	s.accountCache.Set(ctx, fmt.Sprintf("%d", account.ID), account)
	s.accountCache.Set(ctx, fmt.Sprintf("openid:%s", account.OpenID), account)
	return account, nil
}

// UpdateAccount 更新账户(带缓存)
func (s *Store) UpdateAccount(ctx context.Context, update *UpdateAccount) (*Account, error) {
	account, err := s.driver.UpdateAccount(ctx, update)
	if err != nil {
		return nil, err
	}

	// 更新缓存
	s.accountCache.Set(ctx, fmt.Sprintf("%d", account.ID), account)
	s.accountCache.Set(ctx, fmt.Sprintf("openid:%s", account.OpenID), account)
	return account, nil
}

// DeleteAccount 删除账户(带缓存清除)
func (s *Store) DeleteAccount(ctx context.Context, delete *DeleteAccount) error {
	// 先获取账户以清除缓存
	account, err := s.driver.GetAccount(ctx, &FindAccount{FindBase: FindBase{ID: &delete.ID}})
	if err == nil {
		s.accountCache.Delete(ctx, fmt.Sprintf("openid:%s", account.OpenID))
	}

	if err := s.driver.DeleteAccount(ctx, delete); err != nil {
		return err
	}

	s.accountCache.Delete(ctx, fmt.Sprintf("%d", delete.ID))
	return nil
}

// ==================== 角色相关Store方法 ====================

// CreateRole 创建角色
func (s *Store) CreateRole(ctx context.Context, create *Role) (*Role, error) {
	role, err := s.driver.CreateRole(ctx, create)
	if err != nil {
		return nil, err
	}
	s.roleCache.Set(ctx, fmt.Sprintf("%d", role.ID), role)
	return role, nil
}

// GetRole 获取角色(带缓存)
func (s *Store) GetRole(ctx context.Context, find *FindRole) (*Role, error) {
	if find.ID != nil {
		if cached, ok := s.roleCache.Get(ctx, fmt.Sprintf("%d", *find.ID)); ok {
			if role, ok := cached.(*Role); ok {
				return role, nil
			}
		}
	}

	role, err := s.driver.GetRole(ctx, find)
	if err != nil {
		return nil, err
	}

	s.roleCache.Set(ctx, fmt.Sprintf("%d", role.ID), role)
	return role, nil
}

// UpdateRole 更新角色(带缓存)
func (s *Store) UpdateRole(ctx context.Context, update *UpdateRole) (*Role, error) {
	role, err := s.driver.UpdateRole(ctx, update)
	if err != nil {
		return nil, err
	}

	s.roleCache.Set(ctx, fmt.Sprintf("%d", role.ID), role)
	return role, nil
}

// DeleteRole 删除角色(带缓存清除)
func (s *Store) DeleteRole(ctx context.Context, delete *DeleteRole) error {
	if err := s.driver.DeleteRole(ctx, delete); err != nil {
		return err
	}
	s.roleCache.Delete(ctx, fmt.Sprintf("%d", delete.ID))
	return nil
}

// ListRolesByAccount 获取账户的角色列表
func (s *Store) ListRolesByAccount(ctx context.Context, accountID uint64) ([]*Role, error) {
	return s.driver.ListRolesByAccount(ctx, accountID)
}

// GetRoleByName 根据角色名获取角色
func (s *Store) GetRoleByName(ctx context.Context, name string) (*Role, error) {
	return s.driver.GetRoleByName(ctx, name)
}

// CountRolesByAccount 获取账户的角色数量
func (s *Store) CountRolesByAccount(ctx context.Context, accountID uint64) (int, error) {
	return s.driver.CountRolesByAccount(ctx, accountID)
}

// ==================== 角色属性Store方法 ====================

// GetRoleAttributes 获取角色属性
func (s *Store) GetRoleAttributes(ctx context.Context, roleID uint64) (*RoleAttributes, error) {
	return s.driver.GetRoleAttributes(ctx, roleID)
}

// UpdateRoleAttributes 更新角色属性
func (s *Store) UpdateRoleAttributes(ctx context.Context, update *UpdateRoleAttributes) error {
	return s.driver.UpdateRoleAttributes(ctx, update)
}

// ==================== 角色货币Store方法 ====================

// GetRoleCurrency 获取角色货币
func (s *Store) GetRoleCurrency(ctx context.Context, roleID uint64) (*RoleCurrency, error) {
	return s.driver.GetRoleCurrency(ctx, roleID)
}

// UpdateRoleCurrency 更新角色货币
func (s *Store) UpdateRoleCurrency(ctx context.Context, update *RoleCurrency) error {
	return s.driver.UpdateRoleCurrency(ctx, update)
}

// ==================== 背包物品Store方法 ====================

// CreateBagItem 创建背包物品(无缓存)
func (s *Store) CreateBagItem(ctx context.Context, create *BagItem) (*BagItem, error) {
	return s.driver.CreateBagItem(ctx, create)
}

// UpdateBagItem 更新背包物品(无缓存)
func (s *Store) UpdateBagItem(ctx context.Context, update *UpdateBagItem) error {
	return s.driver.UpdateBagItem(ctx, update)
}

// ListBagItemsByRole 获取角色的背包物品(无缓存)
func (s *Store) ListBagItemsByRole(ctx context.Context, roleID uint64) ([]*BagItem, error) {
	return s.driver.ListBagItemsByRole(ctx, roleID)
}

// DeleteBagItem 删除背包物品
func (s *Store) DeleteBagItem(ctx context.Context, delete *DeleteBagItem) error {
	return s.driver.DeleteBagItem(ctx, delete)
}

// ==================== 任务相关Store方法 ====================

// ListQuests 获取任务列表
func (s *Store) ListQuests(ctx context.Context, find *FindQuest) ([]*Quest, error) {
	return s.driver.ListQuests(ctx, find)
}

// GetRoleQuest 获取角色任务
func (s *Store) GetRoleQuest(ctx context.Context, find *FindRoleQuest) (*RoleQuest, error) {
	return s.driver.GetRoleQuest(ctx, find)
}

// CreateRoleQuest 创建角色任务
func (s *Store) CreateRoleQuest(ctx context.Context, create *RoleQuest) (*RoleQuest, error) {
	return s.driver.CreateRoleQuest(ctx, create)
}

// UpdateRoleQuest 更新角色任务
func (s *Store) UpdateRoleQuest(ctx context.Context, update *UpdateRoleQuest) error {
	return s.driver.UpdateRoleQuest(ctx, update)
}

// ListRoleQuests 获取角色的所有任务
func (s *Store) ListRoleQuests(ctx context.Context, roleID uint64) ([]*RoleQuest, error) {
	return s.driver.ListRoleQuests(ctx, roleID)
}

// ==================== 公会相关Store方法 ====================

// CreateGuild 创建公会
func (s *Store) CreateGuild(ctx context.Context, create *Guild) (*Guild, error) {
	guild, err := s.driver.CreateGuild(ctx, create)
	if err != nil {
		return nil, err
	}
	s.guildCache.Set(ctx, fmt.Sprintf("%d", guild.ID), guild)
	return guild, nil
}

// GetGuild 获取公会(带缓存)
func (s *Store) GetGuild(ctx context.Context, find *FindGuild) (*Guild, error) {
	if find.ID != nil {
		if cached, ok := s.guildCache.Get(ctx, fmt.Sprintf("%d", *find.ID)); ok {
			if guild, ok := cached.(*Guild); ok {
				return guild, nil
			}
		}
	}

	guild, err := s.driver.GetGuild(ctx, find)
	if err != nil {
		return nil, err
	}

	s.guildCache.Set(ctx, fmt.Sprintf("%d", guild.ID), guild)
	return guild, nil
}

// UpdateGuild 更新公会(带缓存)
func (s *Store) UpdateGuild(ctx context.Context, update *UpdateGuild) (*Guild, error) {
	guild, err := s.driver.UpdateGuild(ctx, update)
	if err != nil {
		return nil, err
	}

	s.guildCache.Set(ctx, fmt.Sprintf("%d", guild.ID), guild)
	return guild, nil
}

// DeleteGuild 删除公会(带缓存清除)
func (s *Store) DeleteGuild(ctx context.Context, delete *DeleteGuild) error {
	if err := s.driver.DeleteGuild(ctx, delete); err != nil {
		return err
	}
	s.guildCache.Delete(ctx, fmt.Sprintf("%d", delete.ID))
	return nil
}

// ==================== 好友相关Store方法 ====================

// CreateFriend 创建好友
func (s *Store) CreateFriend(ctx context.Context, create *Friend) (*Friend, error) {
	return s.driver.CreateFriend(ctx, create)
}

// ListFriends 获取好友列表
func (s *Store) ListFriends(ctx context.Context, roleID uint64) ([]*Friend, error) {
	return s.driver.ListFriends(ctx, roleID)
}

// DeleteFriend 删除好友
func (s *Store) DeleteFriend(ctx context.Context, delete *DeleteFriend) error {
	return s.driver.DeleteFriend(ctx, delete)
}

// ==================== 邮件相关Store方法 ====================

// CreateMail 创建邮件
func (s *Store) CreateMail(ctx context.Context, create *Mail) (*Mail, error) {
	return s.driver.CreateMail(ctx, create)
}

// ListMails 获取邮件列表
func (s *Store) ListMails(ctx context.Context, find *FindMail) ([]*Mail, error) {
	return s.driver.ListMails(ctx, find)
}

// UpdateMail 更新邮件(已读/领取)
func (s *Store) UpdateMail(ctx context.Context, update *UpdateMail) error {
	return s.driver.UpdateMail(ctx, update)
}

// DeleteMail 删除邮件
func (s *Store) DeleteMail(ctx context.Context, delete *DeleteMail) error {
	return s.driver.DeleteMail(ctx, delete)
}

// ==================== 系统设置Store方法 ====================

// GetInstanceBasicSetting 获取实例基本设置
func (s *Store) GetInstanceBasicSetting(ctx context.Context) (*InstanceBasicSetting, error) {
	return s.driver.GetInstanceBasicSetting(ctx)
}

// UpsertInstanceSetting 更新或创建设置
func (s *Store) UpsertInstanceSetting(ctx context.Context, setting *InstanceSetting) (*InstanceSetting, error) {
	return s.driver.UpsertInstanceSetting(ctx, setting)
}
