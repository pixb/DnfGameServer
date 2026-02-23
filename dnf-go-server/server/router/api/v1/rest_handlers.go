package v1

import (
	"encoding/json"
	"net/http"
	"strconv"
	"time"

	"github.com/labstack/echo/v4"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/server/auth"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// getUserClaims 从Echo上下文获取用户Claims
func getUserClaims(c echo.Context) *auth.UserClaims {
	claims, ok := c.Get("claims").(*auth.UserClaims)
	if !ok {
		return nil
	}
	return claims
}

func (s *APIV1Service) handleGetBag(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	items, _ := s.Store.ListBagItemsByRole(c.Request().Context(), roleID)

	var bagItems []*dnfv1.BagItem
	for _, item := range items {
		bagItems = append(bagItems, &dnfv1.BagItem{
			Guid:   item.ID,
			ItemId: uint32(item.ItemID),
			Count:  item.Count,
			Slot:   item.GridIndex,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"bag": &dnfv1.BagInfo{
			Items: bagItems,
		},
	})
}

func (s *APIV1Service) handleGetBagItems(c echo.Context) error {
	return s.handleGetBag(c)
}

func (s *APIV1Service) handleGetShopList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	items := []*dnfv1.ShopItem{
		{Slot: 1, ItemId: 1001, Price: 100, CurrencyType: 1, Stock: 999, Discount: 100},
		{Slot: 2, ItemId: 1002, Price: 200, CurrencyType: 1, Stock: 999, Discount: 100},
		{Slot: 3, ItemId: 1003, Price: 500, CurrencyType: 1, Stock: 100, Discount: 90},
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":       0,
		"items":       items,
		"refreshTime": 3600,
	})
}

func (s *APIV1Service) handleBuyItem(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	slot, _ := strconv.Atoi(c.FormValue("slot"))
	count, _ := strconv.Atoi(c.FormValue("count"))
	if count <= 0 {
		count = 1
	}

	itemID := int32(slot*100 + 100)
	price := int64(itemID * 10)

	currency, _ := s.Store.GetRoleCurrency(c.Request().Context(), claims.UserID)
	totalPrice := price * int64(count)

	if currency.Gold < totalPrice {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	currency.Gold -= totalPrice
	s.Store.UpdateRoleCurrency(c.Request().Context(), currency)

	var newItems []*dnfv1.BagItem
	for i := 0; i < count; i++ {
		newItem, _ := s.Store.CreateBagItem(c.Request().Context(), &store.BagItem{
			RoleID:    claims.UserID,
			ItemID:    itemID,
			GridIndex: int32(i),
			Count:     1,
			IsEquiped: false,
			BindType:  1,
		})
		newItems = append(newItems, &dnfv1.BagItem{
			Guid:   newItem.ID,
			ItemId: uint32(newItem.ItemID),
			Count:  newItem.Count,
			Slot:   newItem.GridIndex,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"newItems": newItems,
		"currency": map[string]interface{}{
			"gold": currency.Gold,
			"cera": currency.Coin,
		},
	})
}

func (s *APIV1Service) handleSellItem(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	guid, _ := strconv.ParseUint(c.FormValue("guid"), 10, 64)
	count, _ := strconv.Atoi(c.FormValue("count"))

	item, _ := s.Store.GetBagItem(c.Request().Context(), &store.FindBagItem{
		FindBase: store.FindBase{ID: &guid},
		RoleID:   func() *uint64 { v := claims.UserID; return &v }(),
	})

	if item == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}

	sellPrice := int64(item.ItemID) * 10 * int64(count)
	currency, _ := s.Store.GetRoleCurrency(c.Request().Context(), claims.UserID)
	currency.Gold += sellPrice
	s.Store.UpdateRoleCurrency(c.Request().Context(), currency)

	if item.Count <= int32(count) {
		s.Store.DeleteBagItem(c.Request().Context(), &store.DeleteBagItem{ID: item.ID})
	} else {
		newCount := item.Count - int32(count)
		s.Store.UpdateBagItem(c.Request().Context(), &store.UpdateBagItem{ID: item.ID, Count: &newCount})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"goldReceived": sellPrice,
	})
}

func (s *APIV1Service) handleGetFriendList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	friends, _ := s.Store.ListFriends(c.Request().Context(), claims.UserID)

	var friendInfos []*dnfv1.FriendInfo
	for _, friend := range friends {
		friendRole, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{
			FindBase: store.FindBase{ID: &friend.FriendID},
		})
		if friendRole == nil {
			continue
		}
		friendInfos = append(friendInfos, &dnfv1.FriendInfo{
			Uid:      int64(friend.FriendID),
			Name:     friendRole.Name,
			Level:    friendRole.Level,
			Job:      friendRole.Job,
			Online:   false,
			Intimacy: int32(friend.Intimacy),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"friends": friendInfos,
	})
}

func (s *APIV1Service) handleAddFriend(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	targetName := c.FormValue("target_name")
	targetRole, err := s.Store.GetRoleByName(c.Request().Context(), targetName)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}

	s.Store.CreateFriend(c.Request().Context(), &store.Friend{
		RoleID:     claims.UserID,
		FriendID:   targetRole.ID,
		FriendName: targetRole.Name,
		Intimacy:   0,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleRemoveFriend(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	friendUID, _ := strconv.ParseUint(c.FormValue("friend_uid"), 10, 64)
	friend, _ := s.Store.GetFriend(c.Request().Context(), &store.FindFriend{
		RoleID:   &claims.UserID,
		FriendID: &friendUID,
	})

	if friend != nil {
		s.Store.DeleteFriend(c.Request().Context(), &store.DeleteFriend{ID: friend.ID})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleGetMailList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	mails, _ := s.Store.ListMails(c.Request().Context(), &store.FindMail{
		ReceiverID: &claims.UserID,
	})

	var mailList []map[string]interface{}
	for _, m := range mails {
		mailList = append(mailList, map[string]interface{}{
			"id":          m.ID,
			"sender_id":   m.SenderID,
			"sender_name": m.SenderName,
			"title":       m.Title,
			"content":     m.Content,
			"is_read":     m.IsRead,
			"is_claimed":  m.IsClaimed,
			"gold":        m.Gold,
			"created_at":  m.CreatedAt,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"mails": mailList,
	})
}

func (s *APIV1Service) handleSendMail(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	targetName := c.FormValue("target_name")
	title := c.FormValue("title")
	content := c.FormValue("content")
	gold, _ := strconv.ParseInt(c.FormValue("gold"), 10, 64)

	targetRole, err := s.Store.GetRoleByName(c.Request().Context(), targetName)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}

	role, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{
		FindBase: store.FindBase{ID: &claims.UserID},
	})

	mail, _ := s.Store.CreateMail(c.Request().Context(), &store.Mail{
		SenderID:   claims.UserID,
		SenderName: role.Name,
		ReceiverID: targetRole.ID,
		Title:      title,
		Content:    content,
		Gold:       gold,
		IsRead:     false,
		IsClaimed:  false,
		ExpireAt:   0,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"mailId": mail.ID,
	})
}

func (s *APIV1Service) handleClaimMail(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	mailID, _ := strconv.ParseUint(c.FormValue("mail_id"), 10, 64)

	mail, _ := s.Store.GetMail(c.Request().Context(), &store.FindMail{
		FindBase:   store.FindBase{ID: &mailID},
		ReceiverID: &claims.UserID,
	})

	if mail == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}

	if mail.IsClaimed {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 7})
	}

	isClaimed := true
	s.Store.UpdateMail(c.Request().Context(), &store.UpdateMail{
		ID:        mail.ID,
		IsClaimed: &isClaimed,
	})

	if mail.Gold > 0 {
		currency, _ := s.Store.GetRoleCurrency(c.Request().Context(), claims.UserID)
		currency.Gold += mail.Gold
		s.Store.UpdateRoleCurrency(c.Request().Context(), currency)
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"gold":  mail.Gold,
	})
}

func (s *APIV1Service) handleGetQuestList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	quests, _ := s.Store.ListRoleQuests(c.Request().Context(), claims.UserID)

	var questList []map[string]interface{}
	for _, q := range quests {
		questList = append(questList, map[string]interface{}{
			"id":       q.ID,
			"quest_id": q.QuestID,
			"status":   q.Status,
			"progress": q.Progress,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":  0,
		"quests": questList,
	})
}

func (s *APIV1Service) handleAcceptQuest(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	questID, _ := strconv.Atoi(c.FormValue("quest_id"))

	roleQuest, _ := s.Store.CreateRoleQuest(c.Request().Context(), &store.RoleQuest{
		RoleID:     claims.UserID,
		QuestID:    int32(questID),
		Status:     0,
		Progress:   0,
		AcceptedAt: 0,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"questId": roleQuest.QuestID,
	})
}

func (s *APIV1Service) handleCompleteQuest(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	questID, _ := strconv.Atoi(c.FormValue("quest_id"))

	quests, _ := s.Store.ListRoleQuests(c.Request().Context(), claims.UserID)
	for _, q := range quests {
		if q.QuestID == int32(questID) {
			status := int32(2)
			s.Store.UpdateRoleQuest(c.Request().Context(), &store.UpdateRoleQuest{
				ID:     q.ID,
				Status: &status,
			})

			role, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{
				FindBase: store.FindBase{ID: &claims.UserID},
			})

			expGain := int64(role.Level * 100)
			goldGain := int32(role.Level * 50)

			newExp := role.Exp + expGain
			s.Store.UpdateRole(c.Request().Context(), &store.UpdateRole{
				ID:  claims.UserID,
				Exp: &newExp,
			})

			currency, _ := s.Store.GetRoleCurrency(c.Request().Context(), claims.UserID)
			currency.Gold += int64(goldGain)
			s.Store.UpdateRoleCurrency(c.Request().Context(), currency)

			return c.JSON(http.StatusOK, map[string]interface{}{
				"error":    0,
				"expGain":  expGain,
				"goldGain": goldGain,
			})
		}
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
}

func (s *APIV1Service) handleGetGuildInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	member, _ := s.Store.GetDriver().GetGuildMember(c.Request().Context(), &store.FindGuildMember{
		RoleID: &claims.UserID,
	})

	if member == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "hasGuild": false})
	}

	guild, _ := s.Store.GetGuild(c.Request().Context(), &store.FindGuild{
		FindBase: store.FindBase{ID: &member.GuildID},
	})

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"hasGuild": true,
		"guild": map[string]interface{}{
			"id":      guild.ID,
			"name":    guild.Name,
			"level":   guild.Level,
			"members": guild.MemberCount,
		},
	})
}

func (s *APIV1Service) handleCreateGuild(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	name := c.FormValue("name")

	member, _ := s.Store.GetDriver().GetGuildMember(c.Request().Context(), &store.FindGuildMember{
		RoleID: &claims.UserID,
	})
	if member != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 7})
	}

	currency, _ := s.Store.GetRoleCurrency(c.Request().Context(), claims.UserID)
	if currency.Gold < 100000 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	currency.Gold -= 100000
	s.Store.UpdateRoleCurrency(c.Request().Context(), currency)

	guild, _ := s.Store.CreateGuild(c.Request().Context(), &store.Guild{
		Name:        name,
		Level:       1,
		Exp:         0,
		LeaderID:    claims.UserID,
		MemberCount: 1,
		MaxMembers:  50,
	})

	s.Store.GetDriver().AddGuildMember(c.Request().Context(), &store.GuildMember{
		GuildID:      guild.ID,
		RoleID:       claims.UserID,
		Position:     3,
		Contribution: 0,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"guildId": guild.ID,
	})
}

func (s *APIV1Service) handleJoinGuild(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	guildID, _ := strconv.ParseUint(c.FormValue("guild_id"), 10, 64)

	member, _ := s.Store.GetDriver().GetGuildMember(c.Request().Context(), &store.FindGuildMember{
		RoleID: &claims.UserID,
	})
	if member != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 7})
	}

	guild, _ := s.Store.GetGuild(c.Request().Context(), &store.FindGuild{
		FindBase: store.FindBase{ID: &guildID},
	})
	if guild == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}

	if guild.MemberCount >= guild.MaxMembers {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 8})
	}

	s.Store.GetDriver().AddGuildMember(c.Request().Context(), &store.GuildMember{
		GuildID:      guildID,
		RoleID:       claims.UserID,
		Position:     0,
		Contribution: 0,
	})

	newCount := guild.MemberCount + 1
	s.Store.UpdateGuild(c.Request().Context(), &store.UpdateGuild{
		ID:          guildID,
		MemberCount: &newCount,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleLeaveGuild(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	member, _ := s.Store.GetDriver().GetGuildMember(c.Request().Context(), &store.FindGuildMember{
		RoleID: &claims.UserID,
	})
	if member == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 9})
	}

	if member.Position == 3 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 10})
	}

	s.Store.GetDriver().RemoveGuildMember(c.Request().Context(), &store.DeleteGuildMember{ID: member.ID})

	guild, _ := s.Store.GetGuild(c.Request().Context(), &store.FindGuild{
		FindBase: store.FindBase{ID: &member.GuildID},
	})
	newCount := guild.MemberCount - 1
	s.Store.UpdateGuild(c.Request().Context(), &store.UpdateGuild{
		ID:          member.GuildID,
		MemberCount: &newCount,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleSearchAuction(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	itemID := c.FormValue("item_id")
	maxPrice := c.FormValue("max_price")

	find := &store.FindAuctionItem{
		Status: func() *store.AuctionStatus { s := store.AuctionStatusSelling; return &s }(),
	}
	if itemID != "" {
		id, _ := strconv.Atoi(itemID)
		find.ItemID = func() *int32 { v := int32(id); return &v }()
	}
	if maxPrice != "" {
		price, _ := strconv.ParseInt(maxPrice, 10, 64)
		find.MaxPrice = &price
	}

	items, _ := s.Store.ListAuctionItems(c.Request().Context(), find)

	var itemList []map[string]interface{}
	now := time.Now().Unix()
	for _, item := range items {
		itemList = append(itemList, map[string]interface{}{
			"auction_id":  item.ID,
			"item_id":     item.ItemID,
			"seller_name": item.SellerName,
			"price":       item.Price,
			"bid_price":   item.BidPrice,
			"time_left":   item.EndTime - now,
			"count":       item.Count,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"items": itemList,
	})
}

func (s *APIV1Service) handleRegisterAuction(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	guid, _ := strconv.ParseUint(c.FormValue("guid"), 10, 64)
	startPrice, _ := strconv.ParseInt(c.FormValue("start_price"), 10, 64)
	duration, _ := strconv.Atoi(c.FormValue("duration"))

	item, _ := s.Store.GetBagItem(c.Request().Context(), &store.FindBagItem{
		FindBase: store.FindBase{ID: &guid},
		RoleID:   &claims.UserID,
	})
	if item == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}

	role, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{
		FindBase: store.FindBase{ID: &claims.UserID},
	})

	auction, _ := s.Store.CreateAuctionItem(c.Request().Context(), &store.AuctionItem{
		SellerID:   claims.UserID,
		SellerName: role.Name,
		ItemID:     item.ItemID,
		Count:      item.Count,
		Price:      startPrice,
		TotalPrice: startPrice,
		Duration:   int32(duration),
		Status:     store.AuctionStatusSelling,
		BidPrice:   startPrice,
		BidCount:   0,
	})

	s.Store.DeleteBagItem(c.Request().Context(), &store.DeleteBagItem{ID: item.ID})

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":     0,
		"auctionId": auction.ID,
	})
}

func (s *APIV1Service) handleBidAuction(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	auctionID, _ := strconv.ParseUint(c.FormValue("auction_id"), 10, 64)
	bidPrice, _ := strconv.ParseInt(c.FormValue("bid_price"), 10, 64)

	auction, _ := s.Store.GetAuctionItem(c.Request().Context(), &store.FindAuctionItem{
		FindBase: store.FindBase{ID: &auctionID},
	})
	if auction == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}
	if auction.Status != store.AuctionStatusSelling {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 11})
	}
	if auction.SellerID == claims.UserID {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 12})
	}

	currency, _ := s.Store.GetRoleCurrency(c.Request().Context(), claims.UserID)
	if currency.Gold < bidPrice {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	currency.Gold -= bidPrice
	s.Store.UpdateRoleCurrency(c.Request().Context(), currency)

	if auction.BidderID > 0 {
		prevCurrency, _ := s.Store.GetRoleCurrency(c.Request().Context(), auction.BidderID)
		prevCurrency.Gold += auction.BidPrice
		s.Store.UpdateRoleCurrency(c.Request().Context(), prevCurrency)
	}

	bidCount := auction.BidCount + 1
	s.Store.UpdateAuctionItem(c.Request().Context(), &store.UpdateAuctionItem{
		ID:       auction.ID,
		BidderID: &claims.UserID,
		BidPrice: &bidPrice,
		BidCount: &bidCount,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleBuyoutAuction(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	auctionID, _ := strconv.ParseUint(c.FormValue("auction_id"), 10, 64)

	auction, _ := s.Store.GetAuctionItem(c.Request().Context(), &store.FindAuctionItem{
		FindBase: store.FindBase{ID: &auctionID},
	})
	if auction == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 6})
	}
	if auction.Status != store.AuctionStatusSelling {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 11})
	}
	if auction.SellerID == claims.UserID {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 12})
	}

	currency, _ := s.Store.GetRoleCurrency(c.Request().Context(), claims.UserID)
	if currency.Gold < auction.TotalPrice {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	currency.Gold -= auction.TotalPrice
	s.Store.UpdateRoleCurrency(c.Request().Context(), currency)

	if auction.BidderID > 0 {
		prevCurrency, _ := s.Store.GetRoleCurrency(c.Request().Context(), auction.BidderID)
		prevCurrency.Gold += auction.BidPrice
		s.Store.UpdateRoleCurrency(c.Request().Context(), prevCurrency)
	}

	status := store.AuctionStatusSold
	s.Store.UpdateAuctionItem(c.Request().Context(), &store.UpdateAuctionItem{
		ID:      auction.ID,
		Status:  &status,
		BuyerID: &claims.UserID,
	})

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAchievementInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        1,
		"message":      "Achievement system not implemented yet",
		"achievements": []map[string]interface{}{},
	})
}

func (s *APIV1Service) handleAchievementReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   1,
		"message": "Achievement system not implemented yet",
	})
}

func (s *APIV1Service) handleAchievementList(c echo.Context) error {
	return s.handleAchievementInfo(c)
}

func (s *APIV1Service) handleAchievementBonusReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   1,
		"message": "Achievement system not implemented yet",
	})
}

func (s *APIV1Service) handleAdventureUnionInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	union, err := s.Store.GetAdventureUnionInfo(c.Request().Context(), roleID)

	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error": 3,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"union": union,
	})
}

func (s *APIV1Service) handleAdventureUnionNameChange(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	name := c.FormValue("field_1")

	err := s.Store.ChangeAdventureUnionName(c.Request().Context(), claims.UserID, name)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionExpeditionStart(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))
	area, _ := strconv.Atoi(c.FormValue("field_2"))

	err := s.Store.StartAdventureUnionExpedition(c.Request().Context(), claims.UserID, uint32(typeVal), uint32(area))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionExpeditionCancel(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))

	err := s.Store.CancelAdventureUnionExpedition(c.Request().Context(), claims.UserID, uint32(typeVal))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionExpeditionReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))

	err := s.Store.ClaimAdventureUnionExpeditionReward(c.Request().Context(), claims.UserID, uint32(typeVal))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionSubdueInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	union, err := s.Store.GetAdventureUnionInfo(c.Request().Context(), claims.UserID)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"union": union,
	})
}

func (s *APIV1Service) handleAdventureUnionSubdueStart(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))
	area, _ := strconv.Atoi(c.FormValue("field_2"))
	charGuid, _ := strconv.ParseUint(c.FormValue("field_3"), 10, 64)

	err := s.Store.StartAdventureUnionSubdue(c.Request().Context(), claims.UserID, uint32(typeVal), uint32(area), charGuid)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionSubdueReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))

	err := s.Store.ClaimAdventureUnionSubdueReward(c.Request().Context(), claims.UserID, uint32(typeVal))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionOpenShareboardSlot(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))

	err := s.Store.OpenAdventureUnionShareboardSlot(c.Request().Context(), claims.UserID, uint32(typeVal))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionSetShareboard(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))
	slot, _ := strconv.Atoi(c.FormValue("field_2"))
	isPublic := c.FormValue("field_4") == "true"

	err := s.Store.SetAdventureUnionShareboard(c.Request().Context(), claims.UserID, uint32(typeVal), uint32(slot), isPublic)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureReapInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
}

func (s *APIV1Service) handleAdventureReapReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))

	err := s.Store.ClaimAdventureReapReward(c.Request().Context(), claims.UserID, uint32(typeVal))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionSearchStart(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	err := s.Store.StartAdventureUnionSearch(c.Request().Context(), claims.UserID)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionCollectionReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))

	err := s.Store.ClaimAdventureUnionCollectionReward(c.Request().Context(), claims.UserID, uint32(typeVal))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleAdventureUnionLevelReward(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	typeVal, _ := strconv.Atoi(c.FormValue("field_1"))

	err := s.Store.ClaimAdventureUnionLevelReward(c.Request().Context(), claims.UserID, uint32(typeVal))
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleEmblemUpgrade(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	index := int32(0)
	if v, ok := req["index"].(float64); ok {
		index = int32(v)
	}
	tryCount := int32(0)
	if v, ok := req["trycount"].(float64); ok {
		tryCount = int32(v)
	}
	talisman := int32(0)
	if v, ok := req["talisman"].(float64); ok {
		talisman = int32(v)
	}

	roleID := claims.RoleID
	result, err := s.Store.EmblemUpgrade(c.Request().Context(), roleID, index, tryCount, talisman)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":        0,
		"successcount": result.SuccessCount,
		"rewards":      result.Rewards,
		"removeitems":  result.RemoveItems,
	})
}

func (s *APIV1Service) handleEmblemUpgradeQuick(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	source := []*dnfv1.IndexCount{}
	if v, ok := req["source"].([]interface{}); ok {
		for _, item := range v {
			if itemMap, ok := item.(map[string]interface{}); ok {
				index := int32(0)
				if idx, ok := itemMap["index"].(float64); ok {
					index = int32(idx)
				}
				count := int32(0)
				if cnt, ok := itemMap["count"].(float64); ok {
					count = int32(cnt)
				}
				source = append(source, &dnfv1.IndexCount{Index: index, Count: count})
			}
		}
	}

	target := int32(0)
	if v, ok := req["target"].(float64); ok {
		target = int32(v)
	}

	roleID := claims.RoleID
	result, err := s.Store.EmblemUpgradeQuick(c.Request().Context(), roleID, source, target)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":       0,
		"rewards":     result.Rewards,
		"removeitems": result.RemoveItems,
	})
}

func (s *APIV1Service) handleAvatarCompose(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	guids := []uint64{}
	if v, ok := req["guids"].([]interface{}); ok {
		for _, guid := range v {
			if g, ok := guid.(float64); ok {
				guids = append(guids, uint64(g))
			}
		}
	}

	roleID := claims.RoleID
	result, err := s.Store.AvatarCompose(c.Request().Context(), roleID, guids)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":       0,
		"rewards":     result.Rewards,
		"removeitems": result.RemoveItems,
	})
}

func (s *APIV1Service) handleProductionInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	slotType := int32(0)
	if v := c.QueryParam("slottype"); v != "" {
		if st, err := strconv.Atoi(v); err == nil {
			slotType = int32(st)
		}
	}

	roleID := claims.RoleID
	result, err := s.Store.GetProductionInfo(c.Request().Context(), roleID, slotType)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"infos": result.Infos,
	})
}

func (s *APIV1Service) handleProductionRegister(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	slotIndex := int32(0)
	if v, ok := req["slot_index"].(float64); ok {
		slotIndex = int32(v)
	}
	recipeIndex := int32(0)
	if v, ok := req["recipe_index"].(float64); ok {
		recipeIndex = int32(v)
	}
	count := int32(0)
	if v, ok := req["count"].(float64); ok {
		count = int32(v)
	}

	roleID := claims.RoleID
	result, err := s.Store.ProductionRegister(c.Request().Context(), roleID, slotIndex, recipeIndex, count)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":         0,
		"rewards":       result.Rewards,
		"removeitems":   result.RemoveItems,
		"materialitems": result.MaterialItems,
	})
}

func (s *APIV1Service) handleItemCombine(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	index := int32(0)
	if v, ok := req["index"].(float64); ok {
		index = int32(v)
	}

	materialItems := []*dnfv1.MaterialItem{}
	if v, ok := req["material_items"].([]interface{}); ok {
		for _, item := range v {
			if itemMap, ok := item.(map[string]interface{}); ok {
				idx := int32(0)
				if i, ok := itemMap["index"].(float64); ok {
					idx = int32(i)
				}
				cnt := int32(0)
				if c, ok := itemMap["count"].(float64); ok {
					cnt = int32(c)
				}
				materialItems = append(materialItems, &dnfv1.MaterialItem{Index: idx, Count: cnt})
			}
		}
	}

	count := int32(0)
	if v, ok := req["count"].(float64); ok {
		count = int32(v)
	}

	roleID := claims.RoleID
	result, err := s.Store.ItemCombine(c.Request().Context(), roleID, index, materialItems, count)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":       0,
		"rewards":     result.Rewards,
		"removeitems": result.RemoveItems,
	})
}

func (s *APIV1Service) handleItemDisjoint(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	guids := []uint64{}
	if v, ok := req["guids"].([]interface{}); ok {
		for _, guid := range v {
			if g, ok := guid.(float64); ok {
				guids = append(guids, uint64(g))
			}
		}
	}

	roleID := claims.RoleID
	result, err := s.Store.ItemDisjoint(c.Request().Context(), roleID, guids)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"rewards": result.Rewards,
	})
}

func (s *APIV1Service) handleCardCompose(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	userCardList := []*dnfv1.CardCompose{}
	if v, ok := req["user_card_list"].([]interface{}); ok {
		for _, item := range v {
			if itemMap, ok := item.(map[string]interface{}); ok {
				index := int32(0)
				if idx, ok := itemMap["index"].(float64); ok {
					index = int32(idx)
				}
				count := int32(0)
				if cnt, ok := itemMap["count"].(float64); ok {
					count = int32(cnt)
				}
				userCardList = append(userCardList, &dnfv1.CardCompose{Index: index, Count: count})
			}
		}
	}

	roleID := claims.RoleID
	result, err := s.Store.CardCompose(c.Request().Context(), roleID, userCardList)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"card":     result.Card,
		"currency": result.Currency,
	})
}

func (s *APIV1Service) handleWardrobeSetSlot(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

// 角色相关处理方法

func (s *APIV1Service) handleGetCharacterList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roles, _ := s.Store.ListRolesByAccount(c.Request().Context(), claims.UserID)

	var characters []map[string]interface{}
	for _, role := range roles {
		characters = append(characters, map[string]interface{}{
			"charGuid": role.ID,
			"name":     role.Name,
			"level":    role.Level,
			"job":      role.Job,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":      0,
		"characters": characters,
	})
}

func (s *APIV1Service) handleCreateCharacter(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "Invalid request"})
	}

	name, _ := req["name"].(string)
	job, _ := req["job"].(float64)

	if name == "" {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 2, "message": "Character name is required"})
	}

	// 生成角色槽位ID
	roleID := int32(1)
	// 检查当前账号的角色数量，为新角色分配槽位
	roles, _ := s.Store.ListRolesByAccount(c.Request().Context(), claims.UserID)
	if len(roles) > 0 {
		roleID = int32(len(roles) + 1)
	}

	role, err := s.Store.CreateRole(c.Request().Context(), &store.Role{
		AccountID:  claims.UserID,
		RoleID:     roleID,
		Name:       name,
		Job:        int32(job),
		Level:      1,
		Exp:        0,
		Fatigue:    156,
		MaxFatigue: 156,
		MapID:      1,
		X:          0,
		Y:          0,
		Channel:    1,
	})

	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 3, "message": "Failed to create character"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"data": map[string]interface{}{
			"charGuid": role.ID,
			"name":     role.Name,
			"level":    role.Level,
			"job":      role.Job,
		},
	})
}

func (s *APIV1Service) handleSelectCharacter(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1, "message": "Invalid request"})
	}

	charGuid, _ := req["charGuid"].(float64)
	roleID := uint64(charGuid)

	role, _ := s.Store.GetRole(c.Request().Context(), &store.FindRole{
		FindBase:  store.FindBase{ID: &roleID},
		AccountID: &claims.UserID,
	})

	if role == nil {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 2, "message": "Character not found"})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":    0,
		"message":  "Character selected successfully",
		"charGuid": roleID,
	})
}

func (s *APIV1Service) handleEnterGame(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	// 获取账号下的第一个角色
	roles, _ := s.Store.ListRolesByAccount(c.Request().Context(), claims.UserID)
	if len(roles) == 0 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 2, "message": "No characters available"})
	}

	role := roles[0]

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":      0,
		"message":    "Enter game successful",
		"charGuid":   role.ID,
		"serverTime": time.Now().Unix(),
	})
}

// 组队路由处理函数

func (s *APIV1Service) handleSearchPartyList(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	dungeonIndex := uint32(0)
	if v, ok := req["dungeonindex"].(float64); ok {
		dungeonIndex = uint32(v)
	}
	minLevel := uint32(0)
	if v, ok := req["minlevel"].(float64); ok {
		minLevel = uint32(v)
	}
	maxLevel := uint32(0)
	if v, ok := req["maxlevel"].(float64); ok {
		maxLevel = uint32(v)
	}

	parties, err := s.Store.SearchPartyList(c.Request().Context(), dungeonIndex, minLevel, maxLevel)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	var partyInfos []*dnfv1.PartyInfo
	for _, party := range parties {
		partyInfos = append(partyInfos, &dnfv1.PartyInfo{
			Partyguid:    party.PartyGuid,
			Leaderguid:   party.LeaderGuid,
			Name:         party.Name,
			Maxmembers:   party.MaxMembers,
			Members:      party.Members,
			Dungeonindex: party.DungeonIndex,
			Roomid:       uint32(party.RoomID),
			Minlevel:     party.MinLevel,
			Maxlevel:     party.MaxLevel,
			Area:         party.Area,
			Subtype:      party.SubType,
			Stageindex:   party.StageIndex,
			Publictype:   party.PublicType,
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":   0,
		"parties": partyInfos,
	})
}

func (s *APIV1Service) handleCreateParty(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	if roleID == 0 {
		return c.JSON(http.StatusOK, map[string]interface{}{"error": 1})
	}

	err := s.Store.ControlGroup(c.Request().Context(), roleID, 0, 0, 0)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleCheckProhibitedWord(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	word := ""
	if v, ok := req["word"].(string); ok {
		word = v
	}

	prohibited, err := s.Store.CheckProhibitedWord(c.Request().Context(), word)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error":      0,
		"prohibited": prohibited,
	})
}

func (s *APIV1Service) handleTargetUserPartyInfo(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	var req map[string]interface{}
	if err := json.NewDecoder(c.Request().Body).Decode(&req); err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	roleID := claims.RoleID
	charGuid := uint64(0)
	if v, ok := req["charguid"].(float64); ok {
		charGuid = uint64(v)
	}

	party, err := s.Store.TargetUserPartyInfo(c.Request().Context(), roleID, charGuid)
	if err != nil {
		return c.JSON(http.StatusOK, map[string]interface{}{
			"error":   1,
			"message": err.Error(),
		})
	}

	partyInfo := &dnfv1.PartyInfo{
		Partyguid:    party.PartyGuid,
		Leaderguid:   party.LeaderGuid,
		Name:         party.Name,
		Maxmembers:   party.MaxMembers,
		Members:      party.Members,
		Dungeonindex: party.DungeonIndex,
		Roomid:       uint32(party.RoomID),
		Minlevel:     party.MinLevel,
		Maxlevel:     party.MaxLevel,
		Area:         party.Area,
		Subtype:      party.SubType,
		Stageindex:   party.StageIndex,
		Publictype:   party.PublicType,
	}

	return c.JSON(http.StatusOK, map[string]interface{}{
		"error": 0,
		"party": partyInfo,
	})
}
