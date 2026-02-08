package game

import (
	"fmt"
	"sync"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"

	"github.com/patrickmn/go-cache"
	"gorm.io/gorm"
)

// PlayerService 玩家服务 (替代 Java PlayerService)
type PlayerService struct {
	db *db.DB

	// 内存缓存 (替代 ConcurrentHashMap)
	playerProfiles  map[int64]*models.PlayerProfile
	accountProfiles map[string]*models.AccountProfile
	name2UID        map[string]int64
	roleID2UID      map[int]int64
	mu              sync.RWMutex

	// go-cache (替代 Guava Cache)
	roleCache *cache.Cache
}

// NewPlayerService 创建玩家服务
func NewPlayerService(database *db.DB) *PlayerService {
	return &PlayerService{
		db:              database,
		playerProfiles:  make(map[int64]*models.PlayerProfile),
		accountProfiles: make(map[string]*models.AccountProfile),
		name2UID:        make(map[string]int64),
		roleID2UID:      make(map[int]int64),
		roleCache:       cache.New(5*time.Minute, 10*time.Minute),
	}
}

// LoadAllPlayerProfiles 加载所有玩家资料
func (s *PlayerService) LoadAllPlayerProfiles() error {
	s.mu.Lock()
	defer s.mu.Unlock()

	// 清空现有数据
	s.playerProfiles = make(map[int64]*models.PlayerProfile)
	s.accountProfiles = make(map[string]*models.AccountProfile)
	s.name2UID = make(map[string]int64)
	s.roleID2UID = make(map[int]int64)

	// 查询所有角色
	var roles []models.Role
	if err := s.db.DB.Find(&roles).Error; err != nil {
		return err
	}

	logger.Info("loading player profiles", logger.Int("count", len(roles)))

	for _, role := range roles {
		profile := &models.PlayerProfile{
			OpenID:   role.OpenID,
			RoleID:   role.RoleId,
			UID:      role.UID,
			Name:     role.Name,
			DistName: role.DistName,
			Exp:      int(role.Exp),
			Level:    role.Level,
			Job:      role.Job,
			Fatigue:  role.Fatigue,
		}

		s.addPlayerProfile(profile)
	}

	logger.Info("player profiles loaded", logger.Int("count", len(s.playerProfiles)))
	return nil
}

// addPlayerProfile 添加玩家资料到内存
func (s *PlayerService) addPlayerProfile(profile *models.PlayerProfile) {
	s.playerProfiles[profile.UID] = profile
	s.name2UID[profile.Name] = profile.UID
	s.roleID2UID[profile.RoleID] = profile.UID

	// 更新账户资料
	account, exists := s.accountProfiles[profile.OpenID]
	if !exists {
		account = &models.AccountProfile{
			ID:      profile.OpenID,
			Players: []models.PlayerProfile{},
		}
		s.accountProfiles[profile.OpenID] = account
	}
	account.Players = append(account.Players, *profile)
}

// GetPlayerByUID 通过UID获取角色 (带缓存)
func (s *PlayerService) GetPlayerByUID(uid int64) (*models.Role, error) {
	// 1. 先查内存缓存
	if cached, found := s.roleCache.Get(cacheKeyRole(uid)); found {
		return cached.(*models.Role), nil
	}

	// 2. 查询数据库
	var role models.Role
	if err := s.db.DB.First(&role, uid).Error; err != nil {
		if err == gorm.ErrRecordNotFound {
			return nil, nil
		}
		return nil, err
	}

	// 3. 写入缓存
	s.roleCache.Set(cacheKeyRole(uid), &role, cache.DefaultExpiration)

	return &role, nil
}

// SavePlayer 保存角色
func (s *PlayerService) SavePlayer(role *models.Role) error {
	if err := s.db.DB.Save(role).Error; err != nil {
		return err
	}

	// 更新缓存
	s.roleCache.Set(cacheKeyRole(role.UID), role, cache.DefaultExpiration)

	return nil
}

// GetPlayersByOpenID 通过OpenID获取角色列表
func (s *PlayerService) GetPlayersByOpenID(openid string) []models.PlayerProfile {
	s.mu.RLock()
	defer s.mu.RUnlock()

	account, exists := s.accountProfiles[openid]
	if !exists {
		return []models.PlayerProfile{}
	}

	return account.Players
}

// GetUIDByName 通过角色名获取UID
func (s *PlayerService) GetUIDByName(name string) int64 {
	s.mu.RLock()
	defer s.mu.RUnlock()

	if uid, exists := s.name2UID[name]; exists {
		return uid
	}
	return 0
}

// GetUIDByRoleID 通过RoleID获取UID
func (s *PlayerService) GetUIDByRoleID(roleID int) int64 {
	s.mu.RLock()
	defer s.mu.RUnlock()

	if uid, exists := s.roleID2UID[roleID]; exists {
		return uid
	}
	return 0
}

// OccupyName 占用角色名
func (s *PlayerService) OccupyName(name string, uid int64) bool {
	s.mu.Lock()
	defer s.mu.Unlock()

	if _, exists := s.name2UID[name]; exists {
		return false
	}

	s.name2UID[name] = uid
	return true
}

// UpdateRoleLevel 更新角色等级
func (s *PlayerService) UpdateRoleLevel(uid int64, level int) error {
	s.mu.Lock()
	defer s.mu.Unlock()

	if profile, exists := s.playerProfiles[uid]; exists {
		profile.Level = level
	}

	return s.db.DB.Model(&models.Role{}).Where("uid = ?", uid).Update("level", level).Error
}

// GetAllUIDs 获取所有角色UID
func (s *PlayerService) GetAllUIDs() []int64 {
	s.mu.RLock()
	defer s.mu.RUnlock()

	uids := make([]int64, 0, len(s.name2UID))
	for _, uid := range s.name2UID {
		uids = append(uids, uid)
	}
	return uids
}

// cacheKeyRole 生成角色缓存key
func cacheKeyRole(uid int64) string {
	return fmt.Sprintf("role:%d", uid)
}
