package v1

import (
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/server/auth"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

func (s *APIV1Service) handleGetBag(c echo.Context) error {
	claims := getUserClaims(c)
	if claims == nil {
		return c.JSON(http.StatusUnauthorized, map[string]interface{}{"code": 16, "message": "authentication required"})
	}

	roleID := claims.UserID
	items, err := s.Store.ListBagItemsByRole(c.Request().Context(), roleID)
	if err != nil {
		return c.JSON(http.StatusInternalServerError, map[string]interface{}{"error": 1})
	}

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

func (s *APIV1Service) handleGetGuildInfo(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleCreateGuild(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleJoinGuild(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleLeaveGuild(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleGetQuestList(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "quests": []*dnfv1.QuestInfo{}})
}

func (s *APIV1Service) handleAcceptQuest(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleCompleteQuest(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleSearchAuction(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0, "items": []*dnfv1.AuctionItem{}})
}

func (s *APIV1Service) handleRegisterAuction(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleBidAuction(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func (s *APIV1Service) handleBuyoutAuction(c echo.Context) error {
	return c.JSON(http.StatusOK, map[string]interface{}{"error": 0})
}

func getUserClaims(c echo.Context) *auth.UserClaims {
	if v := c.Get("claims"); v != nil {
		if claims, ok := v.(*auth.UserClaims); ok {
			return claims
		}
	}
	return nil
}
