package models

import (
	"time"
)

// Account 账户模型 (对应 t_account 表)
type Account struct {
	ID                 string    `gorm:"column:id;primaryKey;size:255;comment:openid" json:"id"`
	AccountKey         string    `gorm:"column:accountkey;size:255;comment:accountkey" json:"accountKey"`
	AccumulateCera     int64     `gorm:"column:accumulatecera;default:0;comment:累计充值" json:"accumulateCera"`
	UserID             string    `gorm:"column:userID;size:255;comment:用户ID" json:"userID"`
	Passwd             string    `gorm:"column:passwd;size:255;comment:密码" json:"passwd"`
	RoleMaxCount       int       `gorm:"column:roleMaxCount;default:3;comment:最大角色数量" json:"roleMaxCount"`
	IsStop             bool      `gorm:"column:isStop;default:false;comment:是否停用" json:"isStop"`
	Privilege          int16     `gorm:"column:privilege;default:0;comment:权限等级" json:"privilege"`
	Score              int       `gorm:"column:score;default:0;comment:积分" json:"score"`
	ChannelNo          string    `gorm:"column:channelNo;size:255;comment:渠道号" json:"channelNo"`
	CreateTime         time.Time `gorm:"column:createTime;autoCreateTime;comment:创建时间" json:"createTime"`
	ZhanlingExp        int       `gorm:"column:zhanlingexp;default:0;comment:战令经验" json:"zhanlingExp"`
	MoneyBox           string    `gorm:"column:moneyBox;type:json;comment:账户名下的各种货币" json:"moneyBox"`
	EpicPieceBox       string    `gorm:"column:epicPieceBox;type:json;comment:装备碎片" json:"epicPieceBox"`
	MailBox            string    `gorm:"column:mailBox;type:json;comment:账户邮箱" json:"mailBox"`
	Storageline        int       `gorm:"column:storageline;default:1;comment:仓库行数" json:"storageline"`
	AccShopInfoBox     string    `gorm:"column:accShopInfoBox;type:json;comment:角色购买记录" json:"accShopInfoBox"`
	AdventureReapInfo  string    `gorm:"column:adventureReapInfo;type:json;comment:冒险收获信息" json:"adventureReapInfo"`
	AdventureUnionInfo string    `gorm:"column:adventureUnionInfo;type:json;comment:冒险联盟信息" json:"adventureUnionInfo"`
	AdStorageBox       string    `gorm:"column:adStorageBox;type:json;comment:账号金库" json:"adStorageBox"`
	AdvBookBox         string    `gorm:"column:advBookBox;type:json;comment:冒险图鉴" json:"advBookBox"`
	AdvUnionSubInfoBox string    `gorm:"column:advUnionSubInfoBox;type:json;comment:冒险联盟子信息" json:"advUnionSubInfoBox"`
	ActivityBox        string    `gorm:"column:activityBox;type:json;comment:账号活动" json:"activityBox"`
	LastLoginTime      int64     `gorm:"column:lastLoginTime;default:0;comment:最后登录时间" json:"lastLoginTime"`
}

func (Account) TableName() string {
	return "t_account"
}

// Role 角色模型 (对应 t_role 表)
type Role struct {
	RoleId             int       `gorm:"column:roleId;not null;comment:角色ID" json:"roleId"`
	UID                int64     `gorm:"column:uid;primaryKey;not null;comment:角色唯一ID" json:"uid"`
	LastLogout         int64     `gorm:"column:lastlogout;default:0;comment:最后登出时间" json:"lastLogout"`
	GrowType           int       `gorm:"column:growtype;default:0;comment:成长类型" json:"growType"`
	SecGrowType        int       `gorm:"column:secgrowtype;default:0;comment:第二成长类型" json:"secGrowType"`
	Job                int       `gorm:"column:job;default:0;comment:职业" json:"job"`
	Level              int       `gorm:"column:level;default:1;comment:等级" json:"level"`
	Name               string    `gorm:"column:name;size:255;comment:角色名" json:"name"`
	Fatigue            int       `gorm:"column:fatigue;default:0;comment:疲劳值" json:"fatigue"`
	EquipScore         int       `gorm:"column:equipscore;default:229;comment:装备评分" json:"equipScore"`
	CharacterFrame     int       `gorm:"column:characterframe;default:0;comment:角色边框" json:"characterFrame"`
	Money              int       `gorm:"column:money;default:0;comment:金币" json:"money"`
	ResCoin            int       `gorm:"column:rescoin;default:0;comment:复活币" json:"resCoin"`
	ContributionCoin   int       `gorm:"column:contributioncoin;default:0;comment:贡献币" json:"contributionCoin"`
	MagicCrystal       int       `gorm:"column:magiccrystal;default:0;comment:魔晶" json:"magicCrystal"`
	HighMagicCrystal   int       `gorm:"column:highmagiccrystal;default:0;comment:高级魔晶" json:"highMagicCrystal"`
	CeraScore          int       `gorm:"column:cerascore;default:0;comment:点券" json:"ceraScore"`
	PKCoin             int       `gorm:"column:pkcoin;default:0;comment:PK币" json:"pkCoin"`
	FriendPoint        int       `gorm:"column:friendpoint;default:0;comment:好友点" json:"friendPoint"`
	SmallCoin          int       `gorm:"column:smallcoin;default:0;comment:小币" json:"smallCoin"`
	AvatarVisibleFlags int       `gorm:"column:avatarVisibleFlags;default:0;comment:头像可见标志" json:"avatarVisibleFlags"`
	DeletionStatus     int       `gorm:"column:deletionstatus;default:0;comment:删除状态" json:"deletionStatus"`
	DeletionTime       int64     `gorm:"column:deletiontime;default:0;comment:删除时间" json:"deletionTime"`
	CreateTime         int64     `gorm:"column:createtime;default:0;comment:创建时间" json:"createTime"`
	ChangeName         bool      `gorm:"column:changename;default:false;comment:是否改名" json:"changeName"`
	OpenID             string    `gorm:"column:openid;size:255;comment:openid" json:"openid"`
	Exp                int       `gorm:"column:exp;default:200;comment:经验值" json:"exp"`
	SP                 int       `gorm:"column:sp;default:40;comment:SP点" json:"sp"`
	TP                 int       `gorm:"column:tp;default:0;comment:TP点" json:"tp"`
	AddSP              int       `gorm:"column:addsp;default:0;comment:附加SP" json:"addSP"`
	AddTP              int       `gorm:"column:addtp;default:0;comment:附加TP" json:"addTP"`
	Day                int       `gorm:"column:day;default:0;comment:天数" json:"day"`
	Score              int       `gorm:"column:score;default:0;comment:分数" json:"score"`
	QIndex             int       `gorm:"column:qindex;default:100110;comment:任务索引" json:"qIndex"`
	DistName           string    `gorm:"column:distName;size:255;comment:区服名" json:"distName"`
	ServerName         string    `gorm:"column:servername;size:255;comment:服务器名" json:"serverName"`
	UpdateTime         time.Time `gorm:"column:updateTime;autoUpdateTime;comment:更新时间" json:"updateTime"`
	LockTime           int64     `gorm:"column:lockTime;default:0;comment:封号时间" json:"lockTime"`
	Pos                string    `gorm:"column:pos;type:json;comment:坐标数据" json:"pos"`
	Storageline        int       `gorm:"column:storageline;default:1;comment:仓库行数" json:"storageline"`
	WordTime           int64     `gorm:"column:wordTime;default:0;comment:禁言时间" json:"wordTime"`
	WeaponIndex        int       `gorm:"column:weaponIndex;default:0;comment:武器index" json:"weaponIndex"`
	ExpRatio           int       `gorm:"column:expratio;default:100;comment:经验比例" json:"expRatio"`
	FatigueRatio       int       `gorm:"column:fatigueratio;default:100;comment:疲劳比例" json:"fatigueRatio"`
	AdventureName      string    `gorm:"column:adventurename;size:255;default:'';comment:冒险名称" json:"adventureName"`

	// JSON 字段
	ServerSimpleDataBox string `gorm:"column:serverSimpleDataBox;type:json;comment:服务器简单数据" json:"serverSimpleDataBox"`
	FriendBox           string `gorm:"column:friendBox;type:json;comment:好友箱" json:"friendBox"`
	TitleBox            string `gorm:"column:titleBox;type:json;comment:称号箱" json:"titleBox"`
	AvatarBox           string `gorm:"column:avatarBox;type:json;comment:装扮背包" json:"avatarBox"`
	EmblemBox           string `gorm:"column:emblemBox;type:json;comment:徽章背包" json:"emblemBox"`
	CardBox             string `gorm:"column:cardBox;type:json;comment:卡片背包" json:"cardBox"`
	CreatureBox         string `gorm:"column:creatureBox;type:json;comment:宠物背包" json:"creatureBox"`
	ArtifactBox         string `gorm:"column:artifactBox;type:json;comment:宠物装备" json:"artifactBox"`
	EquipBox            string `gorm:"column:equipBox;type:json;comment:装备背包" json:"equipBox"`
	EquippedBox         string `gorm:"column:equippedBox;type:json;comment:已装备" json:"equippedBox"`
	MaterialBox         string `gorm:"column:materialBox;type:json;comment:材料背包" json:"materialBox"`
	ConsumableBox       string `gorm:"column:consumableBox;type:json;comment:消耗品背包" json:"consumableBox"`
	RoleShopInfoBox     string `gorm:"column:roleShopInfoBox;type:json;comment:角色购买记录" json:"roleShopInfoBox"`
	CrackEquipBox       string `gorm:"column:crackEquipBox;type:json;comment:强化装备箱" json:"crackEquipBox"`
	CrackBox            string `gorm:"column:crackBox;type:json;comment:强化箱" json:"crackBox"`
	DamageBox           string `gorm:"column:damageBox;type:json;comment:伤害字体" json:"damageBox"`
	ChatFrameBox        string `gorm:"column:chatFrameBox;type:json;comment:聊天边框" json:"chatFrameBox"`
	CharFrameBox        string `gorm:"column:charFrameBox;type:json;comment:人物边框" json:"charFrameBox"`
	SdAvatarBox         string `gorm:"column:sdAvatarBox;type:json;comment:SD装扮箱" json:"sdAvatarBox"`
	BookmarkBox         string `gorm:"column:bookmarkBox;type:json;comment:书签箱" json:"bookmarkBox"`
	ScrollBox           string `gorm:"column:scrollBox;type:json;comment:卷轴箱" json:"scrollBox"`
	MoneyBox            string `gorm:"column:moneyBox;type:json;comment:金钱箱" json:"moneyBox"`
	CeraShopBuyInfo     string `gorm:"column:ceraShopBuyInfo;type:json;comment:点券商店购买信息" json:"ceraShopBuyInfo"`
	TutoBox             string `gorm:"column:tutoBox;type:json;comment:教程箱" json:"tutoBox"`
	SkillBox            string `gorm:"column:skillBox;type:json;comment:技能箱" json:"skillBox"`
	SkillSlotBox        string `gorm:"column:skillslotBox;type:json;comment:技能槽箱" json:"skillSlotBox"`
	DungeonTicketsBox   string `gorm:"column:dungeonTicketsBox;type:json;comment:副本门票箱" json:"dungeonTicketsBox"`
	TonicBox            string `gorm:"column:tonicBox;type:json;comment:魔力强化信息" json:"tonicBox"`
	MailBox             string `gorm:"column:mailBox;type:json;comment:邮箱" json:"mailBox"`
	SysMailBox          string `gorm:"column:sysMailBox;type:json;comment:系统邮箱" json:"sysMailBox"`
	CharStorageBox      string `gorm:"column:charStorageBox;type:json;comment:角色金库" json:"charStorageBox"`
	RePurStoItem        string `gorm:"column:rePurStoItem;type:json;comment:回收仓库物品" json:"rePurStoItem"`
	TowerInfoBox        string `gorm:"column:towerInfoBox;type:json;comment:塔信息箱" json:"towerInfoBox"`
	CreatureErrandBox   string `gorm:"column:creatureErrandBox;type:json;comment:宠物差事箱" json:"creatureErrandBox"`
	LocalRewardBox      string `gorm:"column:localRewardBox;type:json;comment:本地奖励箱" json:"localRewardBox"`
	QuestInfoBox        string `gorm:"column:questInfoBox;type:json;comment:任务信息箱" json:"questInfoBox"`
	SysBuffBox          string `gorm:"column:sysBuffBox;type:json;comment:系统Buff箱" json:"sysBuffBox"`
	ClearDungeonBox     string `gorm:"column:clearDungeonBox;type:json;comment:通关副本箱" json:"clearDungeonBox"`
	AchievementBox      string `gorm:"column:achievementBox;type:json;comment:成就箱" json:"achievementBox"`
	CollectionBox       string `gorm:"column:collectionBox;type:json;comment:收集箱" json:"collectionBox"`
	NoteMsgBox          string `gorm:"column:noteMsgBox;type:json;comment:便签消息箱" json:"noteMsgBox"`
	EssenceBox          string `gorm:"column:essenceBox;type:json;comment:精华箱" json:"essenceBox"`
	AuctionBox          string `gorm:"column:auctionBox;type:json;comment:拍卖箱" json:"auctionBox"`
}

func (Role) TableName() string {
	return "t_role"
}

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
