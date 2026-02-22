package auth_service

import (
	"errors"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
	"gorm.io/gorm"
)

// Service 认证服务
type Service struct {
	DB *gorm.DB
}

// NewService 创建认证服务
func NewService(db *gorm.DB) *Service {
	return &Service{DB: db}
}

// LoginRequest 登录请求
type LoginRequest struct {
	OpenID string `json:"openid" validate:"required"`
}

// LoginResponse 登录响应
type LoginResponse struct {
	Error       int    `json:"error"`
	AuthKey     string `json:"authKey"`
	AccountKey  string `json:"accountKey"`
	ServerTime  int64  `json:"serverTime"`
	AccountName string `json:"accountName,omitempty"`
}

// Login 处理登录请求
func (s *Service) Login(req *LoginRequest) (*LoginResponse, error) {
	if req.OpenID == "" {
		return &LoginResponse{Error: 1, AuthKey: "", AccountKey: ""}, errors.New("openid不能为空")
	}

	// 查找或创建账号
	var account models.Account
	result := s.DB.Where("openid = ?", req.OpenID).First(&account)

	if result.Error == gorm.ErrRecordNotFound {
		// 首次登录，创建账号
		logger.Info("首次登录", logger.String("openid", req.OpenID))

		// 创建新账号
		account = models.Account{
			OpenID:         req.OpenID,
			AccountKey:     fmt.Sprintf("%d", time.Now().UnixNano()),
			Status:         1,
			CreatedAt:      time.Now(),
			LastLoginAt:    time.Now(),
			AdventureLevel: 1,
			AdventureExp:   0,
			AdventureDay:   1,
		}

		// 保存账号
		if err := s.DB.Create(&account).Error; err != nil {
			logger.Error("创建账号失败", logger.String("openid", req.OpenID), logger.Error(err))
			return &LoginResponse{Error: 2, AuthKey: "", AccountKey: ""}, err
		}

		// 初始化账号货币
		accountMoney := models.AccountMoney{
			AccountID: account.ID,
			Currency1: 10, // 示例货币1
			Currency2: 10, // 示例货币2
			Currency3: 0,
			Currency4: 0,
		}
		if err := s.DB.Create(&accountMoney).Error; err != nil {
			logger.Error("初始化账号货币失败", logger.Int64("account_id", account.ID), logger.Error(err))
		}
	} else if result.Error != nil {
		logger.Error("查询账号失败", logger.String("openid", req.OpenID), logger.Error(result.Error))
		return &LoginResponse{Error: 3, AuthKey: "", AccountKey: ""}, result.Error
	} else {
		// 更新最后登录时间
		account.LastLoginAt = time.Now()
		if err := s.DB.Save(&account).Error; err != nil {
			logger.Error("更新登录时间失败", logger.Int64("account_id", account.ID), logger.Error(err))
		}
	}

	// 生成认证密钥
	authKey := fmt.Sprintf("%d_%s", time.Now().UnixNano(), account.AccountKey)

	// 保存认证信息
	auth := models.Auth{
		AccountID:  account.ID,
		AuthKey:    authKey,
		ExpiresAt:  time.Now().Add(24 * time.Hour), // 24小时过期
		LastUsedAt: time.Now(),
		IPAddress:  "", // 后续可以从请求中获取
		UserAgent:  "", // 后续可以从请求中获取
	}
	if err := s.DB.Create(&auth).Error; err != nil {
		logger.Error("保存认证信息失败", logger.Int64("account_id", account.ID), logger.Error(err))
		return &LoginResponse{Error: 4, AuthKey: "", AccountKey: ""}, err
	}

	logger.Info("登录成功", logger.String("openid", req.OpenID), logger.Int64("account_id", account.ID))

	return &LoginResponse{
		Error:       0,
		AuthKey:     authKey,
		AccountKey:  account.AccountKey,
		ServerTime:  time.Now().Unix(),
		AccountName: account.AccountKey, // 暂时使用AccountKey作为账号名
	}, nil
}

// ValidateAuth 验证认证密钥
func (s *Service) ValidateAuth(authKey string) (*models.Account, error) {
	if authKey == "" {
		return nil, errors.New("authKey不能为空")
	}

	var auth models.Auth
	result := s.DB.Where("auth_key = ?", authKey).First(&auth)

	if result.Error == gorm.ErrRecordNotFound {
		return nil, errors.New("认证密钥无效")
	} else if result.Error != nil {
		return nil, result.Error
	}

	// 检查是否过期
	if auth.ExpiresAt.Before(time.Now()) {
		return nil, errors.New("认证密钥已过期")
	}

	// 更新最后使用时间
	auth.LastUsedAt = time.Now()
	s.DB.Save(&auth)

	// 获取账号信息
	var account models.Account
	if err := s.DB.First(&account, auth.AccountID).Error; err != nil {
		return nil, err
	}

	return &account, nil
}
