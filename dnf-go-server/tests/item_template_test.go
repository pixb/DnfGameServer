package tests

import (
	"testing"

	"github.com/stretchr/testify/suite"
)

// ItemTemplateTestSuite 物品模板体系(2026-09-08 第七十三轮)
type ItemTemplateTestSuite struct {
	BaseTestSuite
}

func TestItemTemplateTestSuite(t *testing.T) {
	suite.Run(t, new(ItemTemplateTestSuite))
}

func (s *ItemTemplateTestSuite) SetupSuite() {
	s.BaseTestSuite.SetupSuite()
	if err := clearRolesForOpenids("itm_tpl_01"); err != nil {
		s.T().Logf("clear roles warning: %v", err)
	}
}

// TestItemTemplateList 物品模板列表接口: 种子模板全量返回, 关键字段正确
func (s *ItemTemplateTestSuite) TestItemTemplateList() {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "itm_tpl_01",
	})
	s.NoError(err)
	s.NotNil(resp)
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}

	resp, err = s.Client.Get("/api/v1/item/templates")
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	templates, ok := resp["templates"].([]interface{})
	s.True(ok, "templates 应为数组")
	s.True(len(templates) >= 11, "种子模板至少 11 条, got %d", len(templates))

	// 按 item_id 建索引断言关键模板字段
	byID := map[float64]map[string]interface{}{}
	for _, raw := range templates {
		t, ok := raw.(map[string]interface{})
		if !ok {
			continue
		}
		byID[t["item_id"].(float64)] = t
	}

	// 1001 钢铁短剑: 武器(1) Lv1 售价100
	sword, ok := byID[1001]
	s.True(ok, "模板 1001 应存在")
	s.Equal("钢铁短剑", sword["name"])
	s.Equal(float64(1), sword["item_type"])
	s.Equal(float64(100), sword["sell_price"])

	// 2001 铁矿石: 材料(0) Lv1
	ore, ok := byID[2001]
	s.True(ok, "模板 2001 应存在")
	s.Equal("铁矿石", ore["name"])
	s.Equal(float64(0), ore["item_type"])

	// 2013000001 破损护甲: 分解产物, 售价3
	broken, ok := byID[2013000001]
	s.True(ok, "模板 2013000001 应存在")
	s.Equal("破损护甲", broken["name"])
	s.Equal(float64(3), broken["sell_price"])

	// 20001 任务嘉奖箱: 消耗品(3)
	box, ok := byID[20001]
	s.True(ok, "模板 20001 应存在")
	s.Equal(float64(3), box["item_type"])
}

// TestBagAndShopWithName 背包/商店响应带物品名称(2026-09-08 第七十五轮, 模板体系消费)
func (s *ItemTemplateTestSuite) TestBagAndShopWithName() {
	resp, err := s.Client.Post("/api/v1/auth/login", map[string]interface{}{
		"openid": "itm_tpl_01",
	})
	s.NoError(err)
	s.NotNil(resp)
	if token, ok := resp["authKey"].(string); ok {
		s.Client.SetToken(token)
	}
	listResp, err := s.Client.Get("/api/v1/character/list")
	s.NoError(err)
	s.NotNil(listResp)
	roleID := lastCharGuid(listResp)
	if roleID == 0 {
		createResp, err := s.Client.Post("/api/v1/character/create", map[string]interface{}{
			"name": "ItemTplHero",
			"job":  1,
			"slot": 2,
		})
		s.NoError(err)
		s.NotNil(createResp)
		listResp, err = s.Client.Get("/api/v1/character/list")
		s.NoError(err)
		s.NotNil(listResp)
		roleID = lastCharGuid(listResp)
	}
	if roleID == 0 {
		s.T().Fatal("no character")
	}

	// 背包: seed 2001x1 后 GET /bag, items[0].name 应为 铁矿石
	s.Require().NoError(seedBagItems(roleID, map[int32]int32{2001: 1}))
	resp, err = s.Client.Get("/api/v1/bag")
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	bag, ok := resp["bag"].(map[string]interface{})
	s.True(ok, "bag 应为对象")
	bagItems, ok := bag["items"].([]interface{})
	s.True(ok, "bag.items 应为数组")
	s.True(len(bagItems) >= 1, "背包应至少 1 件")
	first, _ := bagItems[0].(map[string]interface{})
	s.Equal("铁矿石", first["name"], "背包物品应带名称")
	s.Equal(float64(2001), first["itemId"])

	// 商店: items 带名称(1001 钢铁短剑 / 1002 精钢长剑 / 1003 秘银巨剑)
	resp, err = s.Client.Get("/api/v1/shop/list")
	s.NoError(err)
	s.NotNil(resp)
	if errVal, ok := resp["error"]; ok {
		s.Equal(float64(0), errVal)
	}
	shopItems, ok := resp["items"].([]interface{})
	s.True(ok, "shop items 应为数组")
	s.Len(shopItems, 3)
	shopByName := map[string]bool{}
	for _, raw := range shopItems {
		it, _ := raw.(map[string]interface{})
		shopByName[it["name"].(string)] = true
	}
	s.True(shopByName["钢铁短剑"], "商店应含 钢铁短剑")
	s.True(shopByName["精钢长剑"], "商店应含 精钢长剑")
	s.True(shopByName["秘银巨剑"], "商店应含 秘银巨剑")
}
