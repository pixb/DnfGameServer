package models

import (
	"time"
)

// ExpConfig 角色经验配置 (对应 p_exp 表)
type ExpConfig struct {
	Level int `gorm:"column:level;primaryKey;comment:等级" json:"level"`
	Exp   int `gorm:"column:exp;default:0;comment:经验值" json:"exp"`
}

func (ExpConfig) TableName() string {
	return "p_exp"
}

// GameMapConfig 游戏地图配置 (对应 p_gamemap 表)
type GameMapConfig struct {
	Name      string `gorm:"column:name;primaryKey;size:255;comment:地图名称" json:"name"`
	ID        int    `gorm:"column:id;default:0;comment:地图ID" json:"id"`
	SafeZone  bool   `gorm:"column:safe_zone;default:false;comment:安全区" json:"safeZone"`
	TelePortX int    `gorm:"column:telePortX;default:0;comment:传送点X" json:"telePortX"`
	TelePortY int    `gorm:"column:telePortY;default:0;comment:传送点Y" json:"telePortY"`
}

func (GameMapConfig) TableName() string {
	return "p_gamemap"
}

// NPCConfig NPC配置 (对应 p_npc 表)
type NPCConfig struct {
	ID        int    `gorm:"column:id;primaryKey;comment:NPC ID" json:"id"`
	Name      string `gorm:"column:name;size:255;comment:NPC名称" json:"name"`
	Type      int8   `gorm:"column:type;default:1;comment:NPC类型" json:"type"`
	X         int16  `gorm:"column:x;default:0;comment:X坐标" json:"x"`
	Y         int16  `gorm:"column:y;default:0;comment:Y坐标" json:"y"`
	FangXiang int16  `gorm:"column:fangxiang;default:0;comment:方向" json:"fangxiang"`
	MapID     int    `gorm:"column:mapId;default:0;comment:地图ID" json:"mapId"`
	Content   string `gorm:"column:content;type:text;default:'';comment:对话" json:"content"`
	Content1  string `gorm:"column:content1;type:text;comment:对话2" json:"content1"`
	Icon      int    `gorm:"column:icon;default:0;comment:外观" json:"icon"`
	List      string `gorm:"column:list;type:json;default:'[]';comment:物品" json:"list"`
}

func (NPCConfig) TableName() string {
	return "p_npc"
}

// PetExpConfig 宠物经验配置 (对应 p_petexp 表)
type PetExpConfig struct {
	Level        int `gorm:"column:level;primaryKey;comment:等级" json:"level"`
	NextLevelExp int `gorm:"column:nextLevelExp;default:0;comment:下一级经验" json:"nextLevelExp"`
	ExpDan       int `gorm:"column:expDan;default:0;comment:经验丹" json:"expDan"`
	HighExpDan   int `gorm:"column:highExpDan;default:0;comment:高级经验丹" json:"highExpDan"`
}

func (PetExpConfig) TableName() string {
	return "p_petexp"
}

// ConsumeConfig 消耗品配置 (对应 p_consume 表)
type ConsumeConfig struct {
	Index         int    `gorm:"column:index;primaryKey;comment:物品索引" json:"index"`
	ItemName      string `gorm:"column:itemname;size:255;comment:物品名称" json:"itemName"`
	StackableType int    `gorm:"column:stackabletype;default:0;comment:客户端物品类型" json:"stackableType"`
	Grade         int    `gorm:"column:grade;default:0;comment:物品品质" json:"grade"`
	SubType       int    `gorm:"column:subtype;default:0;comment:类别" json:"subType"`
	Weight        int    `gorm:"column:weight;default:0;comment:物品重量" json:"weight"`
	Score         int    `gorm:"column:score;default:0;comment:抗魔值" json:"score"`
	MinLevel      int    `gorm:"column:minlevel;default:0;comment:最低使用等级" json:"minLevel"`
	Rarity        int    `gorm:"column:rarity;default:0;comment:稀有度" json:"rarity"`
	Level         int    `gorm:"column:level;default:0;comment:物品等级" json:"level"`
	SellPrice     int    `gorm:"column:sellprice;default:0;comment:出售价格" json:"sellPrice"`
}

func (ConsumeConfig) TableName() string {
	return "p_consume"
}

// EquipConfig 装备配置 (对应 p_equip 表)
type EquipConfig struct {
	Index        int    `gorm:"column:index;primaryKey;comment:装备索引" json:"index"`
	Name         string `gorm:"column:name;size:255;comment:装备名称" json:"name"`
	Score        int    `gorm:"column:score;default:0;comment:抗魔值" json:"score"`
	MinLevel     int    `gorm:"column:minlevel;default:0;comment:最低使用等级" json:"minLevel"`
	EquipType    int    `gorm:"column:equiptype;default:0;comment:装备类型" json:"equipType"`
	Grade        int    `gorm:"column:grade;default:0;comment:品质" json:"grade"`
	Weight       int    `gorm:"column:weight;default:0;comment:重量" json:"weight"`
	Rarity       int    `gorm:"column:rarity;default:0;comment:稀有度" json:"rarity"`
	GroupName    string `gorm:"column:groupname;size:255;comment:组名" json:"groupName"`
	SubType      int    `gorm:"column:subtype;default:0;comment:子类型" json:"subType"`
	DisjointList string `gorm:"column:disjointList;type:json;comment:分解列表" json:"disjointList"`
}

func (EquipConfig) TableName() string {
	return "p_equip"
}

// ItemShopConfig 物品商店配置 (对应 p_itemshop 表)
type ItemShopConfig struct {
	Index      int    `gorm:"column:index;primaryKey;comment:索引" json:"index"`
	GoodsID    int    `gorm:"column:goodsid;default:0;comment:商品ID" json:"goodsId"`
	MoneyType  int    `gorm:"column:moneytype;default:0;comment:货币类型" json:"moneyType"`
	MoneyCount int    `gorm:"column:moneycount;default:0;comment:货币数量" json:"moneyCount"`
	LimitType  string `gorm:"column:limittype;size:50;comment:限制类型" json:"limitType"`
	LimitDay   string `gorm:"column:limitday;size:50;comment:限制天数" json:"limitDay"`
	LimitCount int    `gorm:"column:limitcount;default:0;comment:限制数量" json:"limitCount"`
	ShopName   string `gorm:"column:shopname;size:255;comment:商店名称" json:"shopName"`
}

func (ItemShopConfig) TableName() string {
	return "p_itemshop"
}

// SkillConfig 技能配置 (对应 p_skill 表)
type SkillConfig struct {
	ID       int    `gorm:"column:id;primaryKey;comment:技能ID" json:"id"`
	Name     string `gorm:"column:name;size:255;comment:技能名称" json:"name"`
	Level    int    `gorm:"column:level;default:0;comment:技能等级" json:"level"`
	SP       int    `gorm:"column:sp;default:0;comment:SP消耗" json:"sp"`
	TP       int    `gorm:"column:tp;default:0;comment:TP消耗" json:"tp"`
	MaxLevel int    `gorm:"column:maxLevel;default:0;comment:最大等级" json:"maxLevel"`
	Type     int    `gorm:"column:type;default:0;comment:技能类型" json:"type"`
}

func (SkillConfig) TableName() string {
	return "p_skill"
}

// ServerConfig 服务器配置 (对应 p_server 表)
type ServerConfig struct {
	ID         int    `gorm:"column:id;primaryKey;comment:服务器ID" json:"id"`
	Name       string `gorm:"column:name;size:255;comment:服务器名称" json:"name"`
	IP         string `gorm:"column:ip;size:255;comment:服务器IP" json:"ip"`
	Port       int    `gorm:"column:port;default:0;comment:服务器端口" json:"port"`
	MaxPlayers int    `gorm:"column:maxPlayers;default:0;comment:最大玩家数" json:"maxPlayers"`
}

func (ServerConfig) TableName() string {
	return "p_server"
}

// OnlineMallConfig 在线商城配置 (对应 p_onlinemall 表)
type OnlineMallConfig struct {
	ID       int    `gorm:"column:id;primaryKey;comment:商品ID" json:"id"`
	Name     string `gorm:"column:name;size:255;comment:商品名称" json:"name"`
	Price    int    `gorm:"column:price;default:0;comment:价格" json:"price"`
	Discount int    `gorm:"column:discount;default:0;comment:折扣" json:"discount"`
}

func (OnlineMallConfig) TableName() string {
	return "p_onlinemall"
}

// TransferConfig 转职配置 (对应 p_transfer 表)
type TransferConfig struct {
	ID      int `gorm:"column:id;primaryKey;comment:转职ID" json:"id"`
	FromJob int `gorm:"column:fromJob;default:0;comment:原职业" json:"fromJob"`
	ToJob   int `gorm:"column:toJob;default:0;comment:目标职业" json:"toJob"`
	Level   int `gorm:"column:level;default:0;comment:所需等级" json:"level"`
}

func (TransferConfig) TableName() string {
	return "p_transfer"
}

// TaskSetConfig 任务配置 (对应 p_taskset 表)
type TaskSetConfig struct {
	ID     int    `gorm:"column:id;primaryKey;comment:任务ID" json:"id"`
	Name   string `gorm:"column:name;size:255;comment:任务名称" json:"name"`
	Type   int    `gorm:"column:type;default:0;comment:任务类型" json:"type"`
	Reward int    `gorm:"column:reward;default:0;comment:奖励" json:"reward"`
}

func (TaskSetConfig) TableName() string {
	return "p_taskset"
}

// TalkSetConfig 对话配置 (对应 p_talkset 表)
type TalkSetConfig struct {
	ID      int    `gorm:"column:id;primaryKey;comment:对话ID" json:"id"`
	NPCId   int    `gorm:"column:npcId;default:0;comment:NPC ID" json:"npcId"`
	Content string `gorm:"column:content;type:text;comment:对话内容" json:"content"`
}

func (TalkSetConfig) TableName() string {
	return "p_talkset"
}

// SkinConfig 皮肤配置 (对应 p_skin 表)
type SkinConfig struct {
	ID   int    `gorm:"column:id;primaryKey;comment:皮肤ID" json:"id"`
	Name string `gorm:"column:name;size:255;comment:皮肤名称" json:"name"`
	Type int    `gorm:"column:type;default:0;comment:皮肤类型" json:"type"`
}

func (SkinConfig) TableName() string {
	return "p_skin"
}

// DungeonConfig 副本配置 (对应 p_dungeon 表)
type DungeonConfig struct {
	ID         int    `gorm:"column:id;primaryKey;comment:副本ID" json:"id"`
	Name       string `gorm:"column:name;size:255;comment:副本名称" json:"name"`
	Level      int    `gorm:"column:level;default:0;comment:副本等级" json:"level"`
	MaxPlayers int    `gorm:"column:maxPlayers;default:0;comment:最大玩家数" json:"maxPlayers"`
}

func (DungeonConfig) TableName() string {
	return "p_dungeon"
}

// DungeonMapConfig 副本地图配置 (对应 p_dungeonmap 表)
type DungeonMapConfig struct {
	ID        int    `gorm:"column:id;primaryKey;comment:地图ID" json:"id"`
	DungeonID int    `gorm:"column:dungeonId;default:0;comment:副本ID" json:"dungeonId"`
	MapName   string `gorm:"column:mapName;size:255;comment:地图名称" json:"mapName"`
}

func (DungeonMapConfig) TableName() string {
	return "p_dungeonmap"
}

// TaskInfoConfig 任务信息配置 (对应 p_taskinfo 表)
type TaskInfoConfig struct {
	ID          int    `gorm:"column:id;primaryKey;comment:任务ID" json:"id"`
	Name        string `gorm:"column:name;size:255;comment:任务名称" json:"name"`
	Description string `gorm:"column:description;type:text;comment:任务描述" json:"description"`
	Reward      int    `gorm:"column:reward;default:0;comment:奖励" json:"reward"`
}

func (TaskInfoConfig) TableName() string {
	return "p_taskinfo"
}

// WordConfig 敏感词配置 (对应 p_word 表)
type WordConfig struct {
	ID   int    `gorm:"column:id;primaryKey;comment:ID" json:"id"`
	Word string `gorm:"column:word;size:255;comment:敏感词" json:"word"`
}

func (WordConfig) TableName() string {
	return "p_word"
}

// ItemDropConfig 物品掉落配置 (对应 p_itemdropset 表)
type ItemDropConfig struct {
	ID     int `gorm:"column:id;primaryKey;comment:掉落组ID" json:"id"`
	ItemID int `gorm:"column:itemId;default:0;comment:物品ID" json:"itemId"`
	Rate   int `gorm:"column:rate;default:0;comment:掉落率" json:"rate"`
}

func (ItemDropConfig) TableName() string {
	return "p_itemdropset"
}

// CharacterScoreConfig 角色评分配置 (对应 cnf_characterscoreconfig 表)
type CharacterScoreConfig struct {
	ID    int `gorm:"column:id;primaryKey;comment:ID" json:"id"`
	Level int `gorm:"column:level;default:0;comment:等级" json:"level"`
	Score int `gorm:"column:score;default:0;comment:评分" json:"score"`
}

func (CharacterScoreConfig) TableName() string {
	return "cnf_characterscoreconfig"
}

// UpgradeScoreConfig 强化评分配置 (对应 cnf_upgradescoreconfig 表)
type UpgradeScoreConfig struct {
	ID    int `gorm:"column:id;primaryKey;comment:ID" json:"id"`
	Level int `gorm:"column:level;default:0;comment:强化等级" json:"level"`
	Score int `gorm:"column:score;default:0;comment:评分" json:"score"`
}

func (UpgradeScoreConfig) TableName() string {
	return "cnf_upgradescoreconfig"
}

// Server 登录服务器表 (对应 t_server 表)
type Server struct {
	ID   int    `gorm:"column:id;primaryKey;comment:服务器ID" json:"id"`
	Name string `gorm:"column:name;size:255;comment:服务器名称" json:"name"`
	IP   string `gorm:"column:ip;size:255;comment:服务器IP" json:"ip"`
	Port int    `gorm:"column:port;default:0;comment:服务器端口" json:"port"`
}

func (Server) TableName() string {
	return "t_server"
}

// Invite 邀请表 (对应 t_invite 表)
type Invite struct {
	ID         string    `gorm:"column:id;primaryKey;size:255;comment:邀请ID" json:"id"`
	InviterID  string    `gorm:"column:inviterId;size:255;comment:邀请者ID" json:"inviterId"`
	InviteeID  string    `gorm:"column:inviteeId;size:255;comment:被邀请者ID" json:"inviteeId"`
	CreateTime time.Time `gorm:"column:createTime;autoCreateTime;comment:创建时间" json:"createTime"`
}

func (Invite) TableName() string {
	return "t_invite"
}

// Identity 身份表 (对应 t_identity 表)
type Identity struct {
	Type   int `gorm:"column:type;primaryKey;comment:类型" json:"type"`
	CurrID int `gorm:"column:currId;default:0;comment:当前ID" json:"currId"`
}

func (Identity) TableName() string {
	return "t_identity"
}

// Offline 离线数据表 (对应 t_offline 表)
type Offline struct {
	UID            int64  `gorm:"column:uid;primaryKey;comment:角色UID" json:"uid"`
	Data           string `gorm:"column:data;type:json;comment:离线数据" json:"data"`
	LastLogoutTime int64  `gorm:"column:lastLogoutTime;default:0;comment:最后登出时间" json:"lastLogoutTime"`
}

func (Offline) TableName() string {
	return "t_offline"
}

// Auction 拍卖表 (对应 t_auction 表)
type Auction struct {
	ID        int       `gorm:"column:id;primaryKey;autoIncrement;comment:拍卖ID" json:"id"`
	ItemID    int       `gorm:"column:itemId;default:0;comment:物品ID" json:"itemId"`
	SellerID  string    `gorm:"column:sellerId;size:255;comment:卖家ID" json:"sellerId"`
	Price     int       `gorm:"column:price;default:0;comment:价格" json:"price"`
	StartTime time.Time `gorm:"column:startTime;comment:开始时间" json:"startTime"`
	EndTime   time.Time `gorm:"column:endTime;comment:结束时间" json:"endTime"`
}

func (Auction) TableName() string {
	return "t_auction"
}

// PayGateway 支付网关表 (对应 pay_gateway 表)
type PayGateway struct {
	ID     int    `gorm:"column:id;primaryKey;comment:网关ID" json:"id"`
	Name   string `gorm:"column:name;size:255;comment:网关名称" json:"name"`
	Type   string `gorm:"column:type;size:50;comment:网关类型" json:"type"`
	Config string `gorm:"column:config;type:json;comment:配置" json:"config"`
}

func (PayGateway) TableName() string {
	return "pay_gateway"
}
