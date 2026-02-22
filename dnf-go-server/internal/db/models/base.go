package models

import (
	"time"
)

// Base models for reference only
// Account and Role models are defined in account.go and role.go respectively

// PayData 支付数据模型 (对应 t_paydata 表)
type PayData struct {
	ID          int       `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	UserID      string    `gorm:"column:userid;size:255;comment:用户ID" json:"userId"`
	OrderID     string    `gorm:"column:orderid;size:255;comment:订单号" json:"orderId"`
	Pkg         string    `gorm:"column:pkg;size:255;comment:包名" json:"pkg"`
	Money       string    `gorm:"column:money;size:255;comment:金额" json:"money"`
	GameName    string    `gorm:"column:gamename;size:255;comment:游戏名称" json:"gameName"`
	CreateTime  time.Time `gorm:"column:createtime;autoCreateTime;comment:时间" json:"createTime"`
	AppChannel  string    `gorm:"column:app_channel;size:255;comment:游戏渠道码" json:"appChannel"`
	UserChannel string    `gorm:"column:userchannel;size:255;comment:用户渠道" json:"userChannel"`
	Status      string    `gorm:"column:status;size:255;comment:支付状态" json:"status"`
}

func (PayData) TableName() string {
	return "t_paydata"
}

// Charge 充值模型 (对应 t_charge 表)
type Charge struct {
	ID       int       `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	TradeNo  string    `gorm:"column:tradeNo;size:255;not null;uniqueIndex:uk_tradeNo;comment:交易号" json:"tradeNo"`
	Gold     int       `gorm:"column:gold;default:0;comment:金币" json:"gold"`
	Rmb      int       `gorm:"column:rmb;default:0;comment:人民币" json:"rmb"`
	Time     time.Time `gorm:"column:time;comment:时间" json:"time"`
	UserName string    `gorm:"column:userName;size:255;index:idx_userName;comment:用户名" json:"userName"`
	Status   int       `gorm:"column:status;default:0;comment:状态" json:"status"`
}

func (Charge) TableName() string {
	return "t_charge"
}

// Notice 公告模型 (对应 t_notice 表)
type Notice struct {
	ID      int    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
	Content string `gorm:"column:content;size:1024;comment:公告内容" json:"content"`
}

func (Notice) TableName() string {
	return "t_notice"
}

// PlayerProfile 玩家资料 (内存缓存用)
type PlayerProfile struct {
	OpenID   string `json:"openid"`
	RoleID   int    `json:"roleId"`
	UID      int64  `json:"uid"`
	Name     string `json:"name"`
	DistName string `json:"distName"`
	Exp      int    `json:"exp"`
	Level    int    `json:"level"`
	Job      int    `json:"job"`
	Fatigue  int    `json:"fatigue"`
}

// AccountProfile 账户资料 (内存缓存用)
type AccountProfile struct {
	ID      string          `json:"id"`
	Players []PlayerProfile `json:"players"`
}
