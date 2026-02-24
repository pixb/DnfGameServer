# 制作系统 - Go实现方案

## 1. 数据模型

### 1.1 制作配方

```go
type CraftRecipe struct {
    ID           uint64    `gorm:"primaryKey;column:id" json:"id"`
    ItemIndex    uint32    `gorm:"column:itemIndex;uniqueIndex" json:"item_index"`
    ItemName     string    `gorm:"column:itemName;size:100" json:"item_name"`
    SkillLevel   uint32    `gorm:"column:skillLevel" json:"skill_level"`
    SuccessRate  float64   `gorm:"column:successRate" json:"success_rate"`
    Description  string    `gorm:"column:description;size:500" json:"description"`
    CreateTime   time.Time `gorm:"column:createTime" json:"create_time"`
    UpdateTime   time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (CraftRecipe) TableName() string {
    return "t_craft_recipe"
}
```

### 1.2 制作材料

```go
type CraftMaterial struct {
    ID         uint64    `gorm:"primaryKey;column:id" json:"id"`
    RecipeID   uint64    `gorm:"column:recipeId;index" json:"recipe_id"`
    MaterialIndex uint32  `gorm:"column:materialIndex" json:"material_index"`
    MaterialCount uint32  `gorm:"column:materialCount" json:"material_count"`
    CreateTime time.Time `gorm:"column:createTime" json:"create_time"`
}

func (CraftMaterial) TableName() string {
    return "t_craft_material"
}
```

### 1.3 制作技能

```go
type CraftSkill struct {
    ID         uint64    `gorm:"primaryKey;column:id" json:"id"`
    RoleID     uint64    `gorm:"column:roleId;uniqueIndex" json:"role_id"`
    SkillLevel uint32    `gorm:"column:skillLevel" json:"skill_level"`
    Experience uint64    `gorm:"column:experience" json:"experience"`
    CreateTime time.Time `gorm:"column:createTime" json:"create_time"`
    UpdateTime time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (CraftSkill) TableName() string {
    return "t_craft_skill"
}
```

### 1.4 制作记录

```go
type CraftRecord struct {
    ID           uint64    `gorm:"primaryKey;column:id" json:"id"`
    RoleID       uint64    `gorm:"column:roleId;index" json:"role_id"`
    RecipeID     uint64    `gorm:"column:recipeId" json:"recipe_id"`
    ItemIndex    uint32    `gorm:"column:itemIndex" json:"item_index"`
    ItemCount    uint32    `gorm:"column:itemCount" json:"item_count"`
    Success      bool      `gorm:"column:success" json:"success"`
    SkillGained  uint64    `gorm:"column:skillGained" json:"skill_gained"`
    CreateTime   time.Time `gorm:"column:createTime" json:"create_time"`
}

func (CraftRecord) TableName() string {
    return "t_craft_record"
}
```

## 2. Protobuf 消息定义

```protobuf
syntax = "proto3";

package dnf.v1;

option go_package = "gen/dnf/v1";

import "dnf/v1/common.proto";

// 制作材料信息
message CraftMaterial {
    uint32 material_index = 1;
    uint32 material_count = 2;
}

// 制作配方信息
message CraftRecipe {
    uint64 id = 1;
    uint32 item_index = 2;
    string item_name = 3;
    uint32 skill_level = 4;
    float success_rate = 5;
    repeated CraftMaterial materials = 6;
    string description = 7;
}

// 制作配方列表请求
message CraftListRequest {
    uint32 skill_level = 1;
    uint32 item_type = 2;
}

// 制作配方列表响应
message CraftListResponse {
    repeated CraftRecipe recipes = 1;
    uint32 error = 2;
    string message = 3;
}

// 物品制作请求
message CraftCreateRequest {
    uint64 recipe_id = 1;
    uint32 craft_count = 2;
}

// 物品制作响应
message CraftCreateResponse {
    uint32 item_index = 1;
    uint32 item_count = 2;
    bool success = 3;
    uint32 error = 4;
    string message = 5;
}

// 制作技能请求
message CraftSkillRequest {
}

// 制作技能响应
message CraftSkillResponse {
    uint32 skill_level = 1;
    uint64 experience = 2;
    uint64 next_level_exp = 3;
    uint32 error = 4;
    string message = 5;
}

// 制作预览请求
message CraftPreviewRequest {
    uint64 recipe_id = 1;
}

// 制作预览响应
message CraftPreviewResponse {
    CraftRecipe recipe = 1;
    float success_rate = 2;
    uint32 error = 3;
    string message = 4;
}

// 制作系统服务
service CraftService {
    // 制作配方查询
    rpc CraftList(CraftListRequest) returns (CraftListResponse);
    
    // 物品制作
    rpc CraftCreate(CraftCreateRequest) returns (CraftCreateResponse);
    
    // 制作技能查询
    rpc CraftSkill(CraftSkillRequest) returns (CraftSkillResponse);
    
    // 制作预览
    rpc CraftPreview(CraftPreviewRequest) returns (CraftPreviewResponse);
}
```

## 3. 核心 Handler 实现

### 3.1 制作配方查询处理器

```go
func (h *CraftHandler) CraftListHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.CraftListRequest)
    if !ok {
        return
    }

    recipes, err := h.craftService.GetCraftRecipes(context.Background(), req.SkillLevel, req.ItemType)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get craft recipes")
        return
    }

    resp := &dnfv1.CraftListResponse{
        Recipes: recipes,
        Error:   0,
    }
    sess.Send(resp)
}
```

### 3.2 物品制作处理器

```go
func (h *CraftHandler) CraftCreateHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.CraftCreateRequest)
    if !ok {
        return
    }

    result, err := h.craftService.CraftItem(context.Background(), sess.RoleID, req.RecipeId, req.CraftCount)
    if err != nil {
        h.sendError(sess, req, 1, "failed to craft item")
        return
    }

    resp := &dnfv1.CraftCreateResponse{
        ItemIndex: result.ItemIndex,
        ItemCount: result.ItemCount,
        Success:   result.Success,
        Error:     0,
    }
    sess.Send(resp)
}
```

## 4. 业务逻辑服务

```go
type CraftService struct {
    store *store.Store
}

// 获取制作配方列表
func (s *CraftService) GetCraftRecipes(ctx context.Context, skillLevel, itemType uint32) ([]*dnfv1.CraftRecipe, error) {
    // 实现获取制作配方列表逻辑
}

// 制作物品
func (s *CraftService) CraftItem(ctx context.Context, roleID, recipeID uint64, craftCount uint32) (*dnfv1.CraftResult, error) {
    // 实现制作物品逻辑
}

// 获取制作技能
func (s *CraftService) GetCraftSkill(ctx context.Context, roleID uint64) (*dnfv1.CraftSkillInfo, error) {
    // 实现获取制作技能逻辑
}

// 制作预览
func (s *CraftService) CraftPreview(ctx context.Context, roleID, recipeID uint64) (*dnfv1.CraftPreviewInfo, error) {
    // 实现制作预览逻辑
}
```