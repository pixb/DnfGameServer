package models

import (
	"time"
)

// Account 账号模型
type Account struct {
	ID            int64     `gorm:"primaryKey;autoIncrement" json:"id"`
	OpenID        string    `gorm:"type:varchar(100);uniqueIndex;not null" json:"openid"`
	AccountKey    string    `gorm:"type:varchar(100);uniqueIndex;not null" json:"accountKey"`
	Status        int       `gorm:"type:int;default:1" json:"status"` // 1:正常, 0:禁用
	CreatedAt     time.Time `json:"createdAt"`
	LastLoginAt   time.Time `json:"lastLoginAt"`
	AdventureLevel int      `gorm:"type:int;default:1" json:"adventureLevel"`
	AdventureExp   int64     `gorm:"type:bigint;default:0" json:"adventureExp"`
	AdventureDay   int       `gorm:"type:int;default:1" json:"adventureDay"`

	// 关联
	Roles      []Role        `gorm:"foreignKey:AccountID" json:"roles,omitempty"`
	AccountMoney *AccountMoney `gorm:"foreignKey:AccountID" json:"accountMoney,omitempty"`
}

// TableName 设置表名
func (Account) TableName() string {
	return "accounts"
}

// AccountMoney 账号货币模型
type AccountMoney struct {
	ID        int64     `gorm:"primaryKey;autoIncrement" json:"id"`
	AccountID int64     `gorm:"uniqueIndex;not null" json:"accountId"`
	Currency1 int64     `gorm:"type:bigint;default:0" json:"currency1"` // 示例货币1
	Currency2 int64     `gorm:"type:bigint;default:0" json:"currency2"` // 示例货币2
	Currency3 int64     `gorm:"type:bigint;default:0" json:"currency3"` // 示例货币3
	Currency4 int64     `gorm:"type:bigint;default:0" json:"currency4"` // 示例货币4
	UpdatedAt time.Time `json:"updatedAt"`

	// 关联
	Account *Account `gorm:"foreignKey:AccountID" json:"account,omitempty"`
}

// TableName 设置表名
func (AccountMoney) TableName() string {
	return "account_money"
}

// Auth 认证模型
type Auth struct {
	ID          int64     `gorm:"primaryKey;autoIncrement" json:"id"`
	AccountID   int64     `gorm:"index;not null" json:"accountId"`
	AuthKey     string    `gorm:"type:varchar(255);uniqueIndex;not null" json:"authKey"`
	ExpiresAt   time.Time `json:"expiresAt"`
	LastUsedAt  time.Time `json:"lastUsedAt"`
	IPAddress   string    `gorm:"type:varchar(50)" json:"ipAddress"`
	UserAgent   string    `gorm:"type:varchar(255)" json:"userAgent"`
	CreatedAt   time.Time `json:"createdAt"`

	// 关联
	Account *Account `gorm:"foreignKey:AccountID" json:"account,omitempty"`
}

// TableName 设置表名
func (Auth) TableName() string {
	return "auths"
}
