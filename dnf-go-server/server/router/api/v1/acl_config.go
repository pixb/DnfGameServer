package v1

// PublicMethods 定义不需要认证的公开端点
var PublicMethods = map[string]struct{}{
	// 认证服务
	"/dnf.v1.AuthService/Login":           {},
	"/dnf.v1.AuthService/Register":        {},
	"/dnf.v1.AuthService/RefreshToken":    {},
	"/dnf.v1.AuthService/GetServerList":   {},
	"/dnf.v1.AuthService/GetChannelList":  {},
	"/dnf.v1.AuthService/GetPlatformInfo": {},

	// 健康检查
	"/dnf.v1.SystemService/HealthCheck":   {},
	"/dnf.v1.SystemService/GetVersion":    {},
	"/dnf.v1.SystemService/GetServerTime": {},

	// 副本系统
	"/dnf.v1.GameService/EnterDungeon":     {},
	"/dnf.v1.GameService/ChangeRoom":       {},
	"/dnf.v1.GameService/ExitDungeon":      {},
	"/dnf.v1.GameService/GetDungeonList":   {},
	"/dnf.v1.GameService/GetDungeonStatus": {},

	// 聊天系统
	"/dnf.v1.GameService/GetChatChannelList":  {},
	"/dnf.v1.GameService/GetWorldChannelList": {},

	// 公告系统
	"/dnf.v1.GameService/GetAnnouncement": {},

	// 角色系统
	"/dnf.v1.GameService/GetCharacterList":  {},
	"/dnf.v1.GameService/GetRoleInfo":       {},
	"/dnf.v1.GameService/GetRoleLevelLimit": {},
	"/dnf.v1.GameService/GetRoleQuality":    {},

	// 商店系统
	"/dnf.v1.ShopService/GetShopList":        {},
	"/dnf.v1.ShopService/GetShopItem":        {},
	"/dnf.v1.ShopService/GetShopItemList":    {},
	"/dnf.v1.ShopService/GetFrequentBuyList": {},
	"/dnf.v1.ShopService/GetDiscountList":    {},

	// 任务系统
	"/dnf.v1.GameService/GetQuestList":           {},
	"/dnf.v1.GameService/GetQuestTaskList":       {},
	"/dnf.v1.GameService/GetQuestProgress":       {},
	"/dnf.v1.GameService/GetQuestAcceptTime":     {},
	"/dnf.v1.GameService/GetQuestRewards":        {},
	"/dnf.v1.GameService/GetQuestProgressShow":   {},
	"/dnf.v1.GameService/GetQuestStamina":        {},
	"/dnf.v1.GameService/GetQuestStaminaTime":    {},
	"/dnf.v1.GameService/GetQuestPool":           {},
	"/dnf.v1.GameService/GetQuestPoolStatus":     {},
	"/dnf.v1.GameService/GetQuestPoolCanUse":     {},
	"/dnf.v1.GameService/GetQuestPoolReward":     {},
	"/dnf.v1.GameService/GetQuestPoolSelectItem": {},
	"/dnf.v1.GameService/GetQuestPoolSelfTime":   {},
	"/dnf.v1.GameService/GetQuestPoolCancelTime": {},

	// 装备系统
	"/dnf.v1.GameService/GetEquipmentList":  {},
	"/dnf.v1.GameService/GetEquipmentStats": {},

	// 背包系统
	"/dnf.v1.GameService/GetBagItems":           {},
	"/dnf.v1.GameService/GetBagBag":             {},
	"/dnf.v1.GameService/GetBagInfo":            {},
	"/dnf.v1.GameService/GetBagSlotInfo":        {},
	"/dnf.v1.GameService/GetBagBagBag":          {},
	"/dnf.v1.GameService/GetBagBagBagBag":       {},
	"/dnf.v1.GameService/GetBagBagBagBagBag":    {},
	"/dnf.v1.GameService/GetBagBagBagBagBagBag": {},

	// 社交系统
	"/dnf.v1.GameService/GetFriendRequestList":         {},
	"/dnf.v1.GameService/GetFriendApplyList":           {},
	"/dnf.v1.GameService/GetFriendList":                {},
	"/dnf.v1.GameService/GetGuildInfo":                 {},
	"/dnf.v1.GameService/GetGuildList":                 {},
	"/dnf.v1.GameService/GetGuildMemberList":           {},
	"/dnf.v1.GameService/GetGuildChannelList":          {},
	"/dnf.v1.GameService/GetGuildRank":                 {},
	"/dnf.v1.GameService/GetGuildRankList":             {},
	"/dnf.v1.GameService/GetGuildRankChangeList":       {},
	"/dnf.v1.GameService/GetGuildContribution":         {},
	"/dnf.v1.GameService/GetGuildContributionRankList": {},
	"/dnf.v1.GameService/GetGuildTitle":                {},
	"/dnf.v1.GameService/GetGuildTitleList":            {},
	"/dnf.v1.GameService/GetGuildChatList":             {},
	"/dnf.v1.GameService/GetGuildPower":                {},
	"/dnf.v1.GameService/GetGuildPowerRecord":          {},

	// 好友系统
	"/dnf.v1.GameService/GetFriendRelationshipInfo": {},

	// 公会系统
	"/dnf.v1.GameService/GetGuildPowerRankList":   {},
	"/dnf.v1.GameService/GetGuildPowerRewardList": {},

	// 邮件系统
	"/dnf.v1.GameService/GetMailList":       {},
	"/dnf.v1.GameService/GetMailInfo":       {},
	"/dnf.v1.GameService/GetSystemMailList": {},
	"/dnf.v1.GameService/GetSystemMailInfo": {},

	// 技能系统
	"/dnf.v1.GameService/GetSkillList":          {},
	"/dnf.v1.GameService/GetSkillTypeInfo":      {},
	"/dnf.v1.GameService/GetSkillTreeList":      {},
	"/dnf.v1.GameService/GetSkillTreeInfo":      {},
	"/dnf.v1.GameService/GetSkillCombo":         {},
	"/dnf.v1.GameService/GetSkillComboInfo":     {},
	"/dnf.v1.GameService/GetSkillComboTree":     {},
	"/dnf.v1.GameService/GetSkillComboTypeInfo": {},
	"/dnf.v1.GameService/GetSkillComboBuffInfo": {},

	// 公告
	"/dnf.v1.SystemService/GetClientConfig":    {},
	"/dnf.v1.SystemService/GetLoginConfig":     {},
	"/dnf.v1.SystemService/GetWorldName":       {},
	"/dnf.v1.SystemService/GetServerIp":        {},
	"/dnf.v1.SystemService/GetGMSiteUrl":       {},
	"/dnf.v1.SystemService/GetHelpUrl":         {},
	"/dnf.v1.SystemService/GetStoreUrl":        {},
	"/dnf.v1.SystemService/GetForumUrl":        {},
	"/dnf.v1.SystemService/GetBugReportUrl":    {},
	"/dnf.v1.SystemService/GetEventUrl":        {},
	"/dnf.v1.SystemService/GetStoreNotice":     {},
	"/dnf.v1.SystemService/GetTrend":           {},
	"/dnf.v1.SystemService/GetEventList":       {},
	"/dnf.v1.SystemService/GetEventDetails":    {},
	"/dnf.v1.SystemService/GetEventNews":       {},
	"/dnf.v1.SystemService/GetEventPreview":    {},
	"/dnf.v1.SystemService/GetEventSummary":    {},
	"/dnf.v1.SystemService/GetEventStatus":     {},
	"/dnf.v1.SystemService/GetEventPoints":     {},
	"/dnf.v1.SystemService/GetEventPointsRank": {},

	// 数据
	"/dnf.v1.GameService/GetRoleServerData":      {},
	"/dnf.v1.GameService/GetVersion":             {},
	"/dnf.v1.GameService/GetAccountInfo":         {},
	"/dnf.v1.GameService/GetAccountStamina":      {},
	"/dnf.v1.GameService/GetAccountStaminaTime":  {},
	"/dnf.v1.GameService/GetServerData":          {},
	"/dnf.v1.GameService/GetServerDataList":      {},
	"/dnf.v1.GameService/GetServerChannelData":   {},
	"/dnf.v1.GameService/GetServerRoleData":      {},
	"/dnf.v1.GameService/GetServerRoleCount":     {},
	"/dnf.v1.GameService/GetServerStage":         {},
	"/dnf.v1.GameService/GetServerStageList":     {},
	"/dnf.v1.GameService/GetServerMyStage":       {},
	"/dnf.v1.GameService/GetServerMyStageList":   {},
	"/dnf.v1.GameService/GetServerRoleHealth":    {},
	"/dnf.v1.GameService/GetServerRoleMaxHealth": {},
	"/dnf.v1.GameService/GetServerVersion":       {},
}
