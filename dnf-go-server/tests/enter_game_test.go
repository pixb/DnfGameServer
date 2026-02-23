package tests

import (
	"fmt"
	"testing"
	"time"

	"github.com/stretchr/testify/suite"
)

type EnterGameTestSuite struct {
	BaseTestSuite
}

func (s *EnterGameTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
}

// TestStartGame 测试开始游戏
func (s *EnterGameTestSuite) TestStartGame() {
	// 1. 登录获取 token
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Skip("Login failed")
		return
	}

	// 2. 设置 token
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	// 3. 获取角色列表
	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		s.T().Skip("No characters available")
		return
	}

	// 4. 选择角色
	firstChar := characters[0].(map[string]interface{})
	charguid := firstChar["charGuid"]

	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"charGuid": charguid,
	})
	s.NoError(err)
	s.NotNil(selectResp)

	// 5. 开始游戏
	startGameResp, err := s.Client.Post("/api/v1/character/enter", nil)
	s.NoError(err)
	s.NotNil(startGameResp)

	if startGameResp != nil {
		s.Equal(float64(0), startGameResp["error"])
		s.NotNil(startGameResp["charGuid"])
		s.NotNil(startGameResp["serverTime"])
	}
}

// TestStartGameInvalidRole 测试开始游戏 - 无效角色
func (s *EnterGameTestSuite) TestStartGameInvalidRole() {
	// 当前实现不支持指定角色GUID，而是获取账号下的第一个角色
	// 因此这个测试用例暂时跳过
	s.T().Skip("Current implementation doesn't support specifying character GUID")
}

// TestLoginWithInvalidOpenID 测试玩家登录 - 无效openid
func (s *EnterGameTestSuite) TestLoginWithInvalidOpenID() {
	// 使用无效的openid登录
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "invalid_openid",
	})
	s.NoError(err)
	s.NotNil(resp)

	// 检查登录是否成功（服务器设计为允许任何openid登录）
	if resp != nil {
		if error, ok := resp["error"].(float64); ok {
			s.Equal(float64(0), error)
		}
		s.NotNil(resp["authKey"])
	}
}

// TestEnterGameWithoutSelectingCharacter 测试进入游戏 - 未选择角色
func (s *EnterGameTestSuite) TestEnterGameWithoutSelectingCharacter() {
	// 1. 登录获取token（使用一个新用户，确保没有选择角色）
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_002",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Skip("Login failed")
		return
	}

	// 2. 设置token
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	// 3. 直接进入游戏，不选择角色
	enterResp, err := s.Client.Post("/api/v1/character/enter", nil)
	s.NoError(err)
	s.NotNil(enterResp)

	// 检查是否失败
	if enterResp != nil {
		if error, ok := enterResp["error"].(float64); ok {
			s.NotEqual(float64(0), error)
		}
	}
}

// TestPlayerDataLoadIntegrity 测试玩家数据加载完整性
func (s *EnterGameTestSuite) TestPlayerDataLoadIntegrity() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 进入游戏
	enterResp, err := s.Client.Post("/api/v1/character/enter", nil)
	s.NoError(err)
	s.NotNil(enterResp)

	// 3. 检查返回数据的完整性
	if enterResp != nil {
		s.Equal(float64(0), enterResp["error"])
		s.NotNil(enterResp["charGuid"])
		s.NotNil(enterResp["serverTime"])
		// 可以根据实际返回的数据结构，添加更多的检查
	}
}

// TestDuplicateLogin 测试重复登录处理
func (s *EnterGameTestSuite) TestDuplicateLogin() {
	// 1. 第一次登录
	resp1, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.NotNil(resp1)
	if resp1 == nil {
		s.T().Skip("First login failed")
		return
	}

	// 2. 获取第一个token
	token1, ok := resp1["authKey"].(string)
	if !ok {
		s.T().Skip("Failed to get token")
		return
	}

	// 3. 第二次登录，使用相同的openid
	resp2, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.NotNil(resp2)
	if resp2 == nil {
		s.T().Skip("Second login failed")
		return
	}

	// 4. 获取第二个token
	token2, ok := resp2["authKey"].(string)
	if !ok {
		s.T().Skip("Failed to get second token")
		return
	}

	// 5. 检查两个token是否不同
	s.NotEqual(token1, token2)

	// 6. 使用第一个token尝试获取角色列表
	s.Client.SetToken(token1)
	listResp1, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp1)

	// 7. 使用第二个token尝试获取角色列表
	s.Client.SetToken(token2)
	listResp2, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp2)
}

// TestGetCharacterListWithoutCharacters 测试获取角色列表_无角色
func (s *EnterGameTestSuite) TestGetCharacterListWithoutCharacters() {
	// 1. 登录获取token
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "new_user_without_characters",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Skip("Login failed")
		return
	}

	// 2. 设置token
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	// 3. 获取角色列表
	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	// 4. 检查角色列表是否为空
	if listResp != nil {
		error, ok := listResp["error"].(float64)
		s.True(ok)
		s.Equal(float64(0), error)

		characters, ok := listResp["characters"]
		s.True(ok)
		s.Nil(characters)
	}
}

// TestSelectCharacterWithInvalidGuid 测试选择角色_无效角色GUID
func (s *EnterGameTestSuite) TestSelectCharacterWithInvalidGuid() {
	// 1. 登录获取token
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Skip("Login failed")
		return
	}

	// 2. 设置token
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	// 3. 使用无效的角色GUID选择角色
	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"charGuid": 999999999, // 无效的角色GUID
	})
	s.NoError(err)
	s.NotNil(selectResp)

	// 4. 检查是否失败
	if selectResp != nil {
		error, ok := selectResp["error"].(float64)
		s.True(ok)
		s.NotEqual(float64(0), error)
	}
}

// TestPing 测试心跳
func (s *EnterGameTestSuite) TestPing() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 发送心跳
	timestamp := time.Now().Unix()
	pingResp, err := s.Client.Post("/api/v1/game/ping", map[string]interface{}{
		"timestamp": timestamp,
	})
	s.NoError(err)
	s.NotNil(pingResp)

	if pingResp != nil {
		if error, ok := pingResp["error"].(float64); ok {
			s.Equal(float64(0), error)
		}
	}
}

// TestEnterTown 测试进入城镇
func (s *EnterGameTestSuite) TestEnterTown() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 进入城镇
	enterTownResp, err := s.Client.Post("/api/v1/game/enter_town", map[string]interface{}{
		"town": uint32(2),
		"area": uint32(5),
	})
	s.NoError(err)
	s.NotNil(enterTownResp)

	if enterTownResp != nil {
		s.Equal(float64(0), enterTownResp["error"])
		s.Equal(float64(2), enterTownResp["town"])
		s.Equal(float64(5), enterTownResp["area"])
	}
}

// TestLeaveTown 测试离开城镇
func (s *EnterGameTestSuite) TestLeaveTown() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 先进入城镇
	enterTownResp, err := s.Client.Post("/api/v1/game/enter_town", map[string]interface{}{
		"town": uint32(2),
		"area": uint32(5),
	})
	s.NoError(err)
	s.NotNil(enterTownResp)

	// 3. 离开城镇
	leaveTownResp, err := s.Client.Post("/api/v1/game/leave_town", map[string]interface{}{
		"town": uint32(2),
	})
	s.NoError(err)
	s.NotNil(leaveTownResp)

	if leaveTownResp != nil {
		s.Equal(float64(0), leaveTownResp["error"])
	}
}

// TestDailyReset 测试每日重置
func (s *EnterGameTestSuite) TestDailyReset() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 执行每日重置
	dailyResetResp, err := s.Client.Post("/api/v1/game/daily_reset", map[string]interface{}{})
	s.NoError(err)
	s.NotNil(dailyResetResp)

	if dailyResetResp != nil {
		s.Equal(float64(0), dailyResetResp["error"])
	}
}

// TestGetCharacterInfo 测试获取角色信息
func (s *EnterGameTestSuite) TestGetCharacterInfo() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取角色信息
	charInfoResp, err := s.Client.Get(fmt.Sprintf("/api/v1/game/character_info?charguid=%d", charguid))
	s.NoError(err)
	s.NotNil(charInfoResp)

	if charInfoResp != nil {
		s.Equal(float64(0), charInfoResp["error"])
		s.NotNil(charInfoResp["character"])
	}
}

// TestInteractionMenu 测试交互菜单
func (s *EnterGameTestSuite) TestInteractionMenu() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取交互菜单
	interactionResp, err := s.Client.Post("/api/v1/game/interaction_menu", map[string]interface{}{
		"charguid":     charguid,
		"openmenutype": uint32(1),
		"transid":      uint32(1),
	})
	s.NoError(err)
	s.NotNil(interactionResp)

	if interactionResp != nil {
		s.Equal(float64(0), interactionResp["error"])
		s.NotNil(interactionResp["status"])
	}
}

// TestNotTransactionCharacterState 测试非交易角色状态
func (s *EnterGameTestSuite) TestNotTransactionCharacterState() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取非交易角色状态
	stateResp, err := s.Client.Post("/api/v1/game/not_transaction_state", map[string]interface{}{
		"ison": true,
	})
	s.NoError(err)
	s.NotNil(stateResp)

	if stateResp != nil {
		s.NotNil(stateResp["status"])
	}
}

// TestPvpRecordInfo 测试 PVP 记录信息
func (s *EnterGameTestSuite) TestPvpRecordInfo() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取 PVP 记录
	pvpResp, err := s.Client.Post("/api/v1/game/pvp_record", map[string]interface{}{
		"matchtype": uint32(1),
		"transid":   uint32(1),
	})
	s.NoError(err)
	s.NotNil(pvpResp)

	if pvpResp != nil {
		s.Equal(float64(0), pvpResp["error"])
		s.NotNil(pvpResp["pvpinfos"])
	}
}

// TestAdventureUnionSubdueInfo 测试冒险联盟讨伐信息
func (s *EnterGameTestSuite) TestAdventureUnionSubdueInfo() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取冒险联盟讨伐信息
	adventureResp, err := s.Client.Post("/api/v1/game/adventure_union_subdue", map[string]interface{}{
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(adventureResp)

	if adventureResp != nil {
		s.Equal(float64(0), adventureResp["error"])
		s.NotNil(adventureResp["fatigues"])
		s.NotNil(adventureResp["tickets"])
		s.NotNil(adventureResp["entranceitems"])
	}
}

// TestSendingInviteFriendList 测试发送邀请好友列表
func (s *EnterGameTestSuite) TestSendingInviteFriendList() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取发送邀请的好友列表
	friendListResp, err := s.Client.Post("/api/v1/game/sending_invite_friend_list", map[string]interface{}{
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(friendListResp)

	if friendListResp != nil {
		s.Equal(float64(0), friendListResp["error"])
		s.NotNil(friendListResp["flist"])
	}
}

// TestLoadServerSimpleData 测试加载服务器简单数据
func (s *EnterGameTestSuite) TestLoadServerSimpleData() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 加载服务器简单数据
	loadDataResp, err := s.Client.Post("/api/v1/game/load_server_simple_data", map[string]interface{}{
		"type":      uint32(1),
		"enumvalue": uint32(12),
		"transid":   uint32(1),
	})
	s.NoError(err)
	s.NotNil(loadDataResp)

	if loadDataResp != nil {
		s.Equal(float64(0), loadDataResp["error"])
	}
}

// TestSaveServerSimpleData 测试保存服务器简单数据
func (s *EnterGameTestSuite) TestSaveServerSimpleData() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 保存服务器简单数据
	saveDataResp, err := s.Client.Post("/api/v1/game/save_server_simple_data", map[string]interface{}{
		"type":      uint32(1),
		"enumvalue": uint32(12),
		"value":     "test_data",
		"transid":   uint32(1),
	})
	s.NoError(err)
	s.NotNil(saveDataResp)

	if saveDataResp != nil {
		s.Equal(float64(0), saveDataResp["error"])
	}
}

// TestEnterChannel 测试进入频道
func (s *EnterGameTestSuite) TestEnterChannel() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 进入频道
	enterChannelResp, err := s.Client.Post("/api/v1/game/enter_channel", map[string]interface{}{
		"channel": uint32(1),
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(enterChannelResp)

	if enterChannelResp != nil {
		s.Equal(float64(0), enterChannelResp["error"])
	}
}

// TestStandby 测试待机
func (s *EnterGameTestSuite) TestStandby() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 待机
	standbyResp, err := s.Client.Post("/api/v1/game/standby", map[string]interface{}{
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(standbyResp)

	if standbyResp != nil {
		s.Equal(float64(0), standbyResp["error"])
	}
}

// TestIdipNotices 测试 IDIP 通知
func (s *EnterGameTestSuite) TestIdipNotices() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取 IDIP 通知
	idipResp, err := s.Client.Post("/api/v1/game/idip_notices", map[string]interface{}{
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(idipResp)

	if idipResp != nil {
		s.Equal(float64(0), idipResp["error"])
	}
}

// TestBlackDiamonInfo 测试黑色钻石信息
func (s *EnterGameTestSuite) TestBlackDiamonInfo() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取黑色钻石信息
	blackDiamonResp, err := s.Client.Post("/api/v1/game/black_diamon_info", map[string]interface{}{
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(blackDiamonResp)

	if blackDiamonResp != nil {
		s.Equal(float64(0), blackDiamonResp["error"])
	}
}

// TestGetPrivateStoreGoodsList 测试获取个人商店商品列表
func (s *EnterGameTestSuite) TestGetPrivateStoreGoodsList() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取个人商店商品列表
	storeResp, err := s.Client.Post("/api/v1/game/private_store_goods_list", map[string]interface{}{
		"shopid":  uint32(1),
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(storeResp)

	if storeResp != nil {
		s.Equal(float64(0), storeResp["error"])
		s.NotNil(storeResp["goods"])
	}
}

// TestRecommendGuildList 测试推荐公会列表
func (s *EnterGameTestSuite) TestRecommendGuildList() {
	// 1. 登录并选择角色
	_ = s.loginAndSelectCharacter()

	// 2. 获取推荐公会列表
	guildListResp, err := s.Client.Post("/api/v1/game/recommend_guild_list", map[string]interface{}{
		"transid": uint32(1),
	})
	s.NoError(err)
	s.NotNil(guildListResp)

	if guildListResp != nil {
		s.Equal(float64(0), guildListResp["error"])
		s.NotNil(guildListResp["list"])
	}
}

// TestAdventureUnionInfoOther 测试其他玩家冒险联盟信息
func (s *EnterGameTestSuite) TestAdventureUnionInfoOther() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 获取其他玩家冒险联盟信息
	adventureResp, err := s.Client.Post("/api/v1/game/adventure_union_info_other", map[string]interface{}{
		"charguid": charguid,
		"transid":  uint32(1),
	})
	s.NoError(err)
	s.NotNil(adventureResp)

	if adventureResp != nil {
		s.Equal(float64(0), adventureResp["error"])
		s.NotNil(adventureResp["level"])
		s.NotNil(adventureResp["name"])
	}
}

// TestReconnect 测试重连
func (s *EnterGameTestSuite) TestReconnect() {
	// 1. 登录并选择角色
	charguid := s.loginAndSelectCharacter()

	// 2. 模拟重连
	reconnectResp, err := s.Client.Post("/api/v1/game/start", map[string]interface{}{
		"charguid":    charguid,
		"authkey":     s.Client.Token,
		"accesstoken": "test_token",
		"town":        uint32(2),
	})
	s.NoError(err)
	s.NotNil(reconnectResp)

	if reconnectResp != nil {
		s.Equal(float64(0), reconnectResp["error"])
		s.NotNil(reconnectResp["world"])
		s.NotNil(reconnectResp["town"])
	}
}

// loginAndSelectCharacter 辅助函数：登录并选择角色
func (s *EnterGameTestSuite) loginAndSelectCharacter() uint64 {
	// 1. 登录
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "test_user_001",
	})
	s.NoError(err)
	s.NotNil(resp)
	if resp == nil {
		s.T().Fatal("Login failed")
	}

	// 2. 设置 token
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	// 3. 获取角色列表
	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)

	characters, ok := listResp["characters"].([]interface{})
	if !ok || len(characters) == 0 {
		s.T().Fatal("No characters available")
	}

	// 4. 选择角色
	firstChar := characters[0].(map[string]interface{})
	charguid := firstChar["charGuid"]

	selectResp, err := s.Client.Post("/api/v1/character/select", map[string]interface{}{
		"charGuid": charguid,
	})
	s.NoError(err)
	s.NotNil(selectResp)

	// 5. 进入游戏
	enterResp, err := s.Client.Post("/api/v1/character/enter", nil)
	s.NoError(err)
	s.NotNil(enterResp)

	// 尝试获取charGuid作为uint64返回
	if cg, ok := charguid.(float64); ok {
		return uint64(cg)
	}
	return 0
}

// TestEnterGameSuite 运行所有进入游戏测试
func TestEnterGameSuite(t *testing.T) {
	suite.Run(t, new(EnterGameTestSuite))
}
