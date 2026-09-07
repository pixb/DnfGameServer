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
