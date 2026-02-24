# 成就系统Go实现方案

## 1. 数据模型设计

### 1.1 成就定义模型

```go
// Achievement 成就定义模型
type Achievement struct {
    ID           uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
    Name         string    `gorm:"column:name;size:128;not null" json:"name"`
    Description  string    `gorm:"column:description;size:512;not null" json:"description"`
    TargetValue  int       `gorm:"column:target_value;not null" json:"targetValue"`
    RewardType   int       `gorm:"column:reward_type;not null" json:"rewardType"`
    RewardID     uint64    `gorm:"column:reward_id;not null" json:"rewardId"`
    RewardCount  int       `gorm:"column:reward_count;not null" json:"rewardCount"`
    Point        int       `gorm:"column:point;not null" json:"point"`
    Category     int       `gorm:"column:category;not null" json:"category"`
    CreateTime   time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
    UpdateTime   time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (Achievement) TableName() string {
    return "t_achievement"
}
```

### 1.2 玩家成就模型

```go
// PlayerAchievement 玩家成就模型
type PlayerAchievement struct {
    ID             uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
    PlayerID       uint64    `gorm:"column:player_id;index;not null" json:"playerId"`
    AchievementID  uint64    `gorm:"column:achievement_id;index;not null" json:"achievementId"`
    CurrentValue   int       `gorm:"column:current_value;not null" json:"currentValue"`
    IsCompleted    bool      `gorm:"column:is_completed;default:false" json:"isCompleted"`
    IsRewarded     bool      `gorm:"column:is_rewarded;default:false" json:"isRewarded"`
    CompleteTime   time.Time `gorm:"column:complete_time" json:"completeTime"`
    CreateTime     time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
    UpdateTime     time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (PlayerAchievement) TableName() string {
    return "t_player_achievement"
}
```

### 1.3 称号模型

```go
// Title 称号模型
type Title struct {
    ID             uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
    Name           string    `gorm:"column:name;size:64;not null" json:"name"`
    Description    string    `gorm:"column:description;size:256;not null" json:"description"`
    AttributeType  int       `gorm:"column:attribute_type;not null" json:"attributeType"`
    AttributeValue int       `gorm:"column:attribute_value;not null" json:"attributeValue"`
    Duration       int       `gorm:"column:duration;default:0" json:"duration"`
    CreateTime     time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
    UpdateTime     time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (Title) TableName() string {
    return "t_title"
}
```

### 1.4 玩家称号模型

```go
// PlayerTitle 玩家称号模型
type PlayerTitle struct {
    ID          uint64    `gorm:"column:id;primaryKey;autoIncrement" json:"id"`
    PlayerID    uint64    `gorm:"column:player_id;index;not null" json:"playerId"`
    TitleID     uint64    `gorm:"column:title_id;index;not null" json:"titleId"`
    IsWearing   bool      `gorm:"column:is_wearing;default:false" json:"isWearing"`
    AcquireTime time.Time `gorm:"column:acquire_time;not null" json:"acquireTime"`
    ExpireTime  time.Time `gorm:"column:expire_time" json:"expireTime"`
    CreateTime  time.Time `gorm:"column:create_time;autoCreateTime" json:"createTime"`
    UpdateTime  time.Time `gorm:"column:update_time;autoUpdateTime" json:"updateTime"`
}

func (PlayerTitle) TableName() string {
    return "t_player_title"
}
```

## 2. Protobuf 消息定义

### 2.1 成就相关消息

```protobuf
syntax = "proto3";

package achievement;

option go_package = "dnf-go-server/proto/achievement";

// 成就信息消息
message AchievementInfo {
    uint64 id = 1;
    string name = 2;
    string description = 3;
    int32 target_value = 4;
    int32 current_value = 5;
    bool is_completed = 6;
    bool is_rewarded = 7;
    int32 point = 8;
}

// 成就列表请求
message AchievementListRequest {
    uint64 player_id = 1;
}

// 成就列表响应
message AchievementListResponse {
    repeated AchievementInfo achievements = 1;
    int32 total_point = 2;
}

// 成就奖励领取请求
message AchievementRewardRequest {
    uint64 player_id = 1;
    uint64 achievement_id = 2;
}

// 成就奖励领取响应
message AchievementRewardResponse {
    bool success = 1;
    string message = 2;
    uint64 reward_id = 3;
    int32 reward_count = 4;
}

// 称号信息消息
message TitleInfo {
    uint64 id = 1;
    string name = 2;
    string description = 3;
    int32 attribute_type = 4;
    int32 attribute_value = 5;
    bool is_wearing = 6;
    bool is_expired = 7;
    uint64 expire_time = 8;
}

// 称号列表请求
message TitleListRequest {
    uint64 player_id = 1;
}

// 称号列表响应
message TitleListResponse {
    repeated TitleInfo titles = 1;
}

// 称号佩戴请求
message TitleWearRequest {
    uint64 player_id = 1;
    uint64 title_id = 2;
}

// 称号佩戴响应
message TitleWearResponse {
    bool success = 1;
    string message = 2;
}

// 成就触发请求
message AchievementTriggerRequest {
    uint64 player_id = 1;
    int32 achievement_type = 2;
    int32 value = 3;
}

// 成就触发响应
message AchievementTriggerResponse {
    bool success = 1;
    repeated uint64 completed_achievements = 2;
}
```

## 3. 业务逻辑实现

### 3.1 成就管理器

```go
// AchievementManager 成就管理器
type AchievementManager struct {
    db           *gorm.DB
    itemService  *ItemService
    currencyService *CurrencyService
    titleService *TitleService
}

// NewAchievementManager 创建成就管理器实例
func NewAchievementManager(db *gorm.DB, itemService *ItemService, currencyService *CurrencyService, titleService *TitleService) *AchievementManager {
    return &AchievementManager{
        db:           db,
        itemService:  itemService,
        currencyService: currencyService,
        titleService: titleService,
    }
}

// CheckAchievement 检查成就进度
func (am *AchievementManager) CheckAchievement(playerID uint64, achievementType int, value int) ([]uint64, error) {
    // 获取相关成就列表
    var achievements []Achievement
    if err := am.db.Where("category = ?", achievementType).Find(&achievements).Error; err != nil {
        return nil, err
    }

    var completedAchievements []uint64
    
    // 遍历检查成就
    for _, achievement := range achievements {
        var playerAchievement PlayerAchievement
        err := am.db.Where("player_id = ? AND achievement_id = ?", playerID, achievement.ID).First(&playerAchievement).Error
        
        if err == gorm.ErrRecordNotFound {
            // 创建新的玩家成就记录
            playerAchievement = PlayerAchievement{
                PlayerID:      playerID,
                AchievementID: achievement.ID,
                CurrentValue:  value,
                IsCompleted:   value >= achievement.TargetValue,
                IsRewarded:    false,
            }
            
            if playerAchievement.IsCompleted {
                playerAchievement.CompleteTime = time.Now()
                completedAchievements = append(completedAchievements, achievement.ID)
            }
            
            if err := am.db.Create(&playerAchievement).Error; err != nil {
                continue
            }
        } else if err == nil && !playerAchievement.IsCompleted {
            // 更新现有成就进度
            playerAchievement.CurrentValue += value
            if playerAchievement.CurrentValue >= achievement.TargetValue {
                playerAchievement.IsCompleted = true
                playerAchievement.CompleteTime = time.Now()
                completedAchievements = append(completedAchievements, achievement.ID)
            }
            
            if err := am.db.Save(&playerAchievement).Error; err != nil {
                continue
            }
        }
    }
    
    return completedAchievements, nil
}

// GetAchievementList 获取玩家成就列表
func (am *AchievementManager) GetAchievementList(playerID uint64) ([]AchievementInfo, int, error) {
    var playerAchievements []PlayerAchievement
    if err := am.db.Where("player_id = ?", playerID).Find(&playerAchievements).Error; err != nil {
        return nil, 0, err
    }
    
    var achievementInfos []AchievementInfo
    var totalPoint int
    
    for _, pa := range playerAchievements {
        var achievement Achievement
        if err := am.db.First(&achievement, pa.AchievementID).Error; err != nil {
            continue
        }
        
        achievementInfos = append(achievementInfos, AchievementInfo{
            ID:            achievement.ID,
            Name:          achievement.Name,
            Description:   achievement.Description,
            TargetValue:   int32(achievement.TargetValue),
            CurrentValue:  int32(pa.CurrentValue),
            IsCompleted:   pa.IsCompleted,
            IsRewarded:    pa.IsRewarded,
            Point:         int32(achievement.Point),
        })
        
        if pa.IsCompleted {
            totalPoint += achievement.Point
        }
    }
    
    return achievementInfos, totalPoint, nil
}

// GiveAchievementReward 发放成就奖励
func (am *AchievementManager) GiveAchievementReward(playerID uint64, achievementID uint64) (bool, error) {
    // 获取玩家成就记录
    var playerAchievement PlayerAchievement
    if err := am.db.Where("player_id = ? AND achievement_id = ?", playerID, achievementID).First(&playerAchievement).Error; err != nil {
        return false, err
    }
    
    // 检查成就是否已完成且未领取奖励
    if !playerAchievement.IsCompleted || playerAchievement.IsRewarded {
        return false, errors.New("achievement not completed or reward already given")
    }
    
    // 获取成就信息
    var achievement Achievement
    if err := am.db.First(&achievement, achievementID).Error; err != nil {
        return false, err
    }
    
    // 根据奖励类型发放奖励
    switch achievement.RewardType {
    case 1: // 物品奖励
        if err := am.itemService.GiveItem(playerID, achievement.RewardID, achievement.RewardCount); err != nil {
            return false, err
        }
    case 2: // 货币奖励
        if err := am.currencyService.AddCurrency(playerID, achievement.RewardID, achievement.RewardCount); err != nil {
            return false, err
        }
    case 3: // 称号奖励
        if err := am.titleService.GiveTitle(playerID, achievement.RewardID); err != nil {
            return false, err
        }
    }
    
    // 更新奖励领取状态
    playerAchievement.IsRewarded = true
    if err := am.db.Save(&playerAchievement).Error; err != nil {
        return false, err
    }
    
    return true, nil
}
```

### 3.2 称号管理器

```go
// TitleManager 称号管理器
type TitleManager struct {
    db *gorm.DB
}

// NewTitleManager 创建称号管理器实例
func NewTitleManager(db *gorm.DB) *TitleManager {
    return &TitleManager{
        db: db,
    }
}

// GetTitleList 获取玩家称号列表
func (tm *TitleManager) GetTitleList(playerID uint64) ([]TitleInfo, error) {
    var playerTitles []PlayerTitle
    if err := tm.db.Where("player_id = ?", playerID).Find(&playerTitles).Error; err != nil {
        return nil, err
    }
    
    var titleInfos []TitleInfo
    now := time.Now()
    
    for _, pt := range playerTitles {
        var title Title
        if err := tm.db.First(&title, pt.TitleID).Error; err != nil {
            continue
        }
        
        isExpired := false
        var expireTime uint64
        if !pt.ExpireTime.IsZero() && pt.ExpireTime.Before(now) {
            isExpired = true
        }
        if !pt.ExpireTime.IsZero() {
            expireTime = uint64(pt.ExpireTime.Unix())
        }
        
        titleInfos = append(titleInfos, TitleInfo{
            ID:             title.ID,
            Name:           title.Name,
            Description:    title.Description,
            AttributeType:  int32(title.AttributeType),
            AttributeValue: int32(title.AttributeValue),
            IsWearing:      pt.IsWearing,
            IsExpired:      isExpired,
            ExpireTime:     expireTime,
        })
    }
    
    return titleInfos, nil
}

// GiveTitle 发放称号
func (tm *TitleManager) GiveTitle(playerID uint64, titleID uint64) error {
    // 检查玩家是否已拥有该称号
    var existingTitle PlayerTitle
    err := tm.db.Where("player_id = ? AND title_id = ?", playerID, titleID).First(&existingTitle).Error
    if err == nil {
        return nil // 玩家已拥有该称号
    }
    
    // 获取称号信息
    var title Title
    if err := tm.db.First(&title, titleID).Error; err != nil {
        return err
    }
    
    // 计算过期时间
    var expireTime time.Time
    if title.Duration > 0 {
        expireTime = time.Now().Add(time.Duration(title.Duration) * time.Second)
    }
    
    // 创建玩家称号记录
    playerTitle := PlayerTitle{
        PlayerID:    playerID,
        TitleID:     titleID,
        IsWearing:   false,
        AcquireTime: time.Now(),
        ExpireTime:  expireTime,
    }
    
    return tm.db.Create(&playerTitle).Error
}

// WearTitle 佩戴称号
func (tm *TitleManager) WearTitle(playerID uint64, titleID uint64) error {
    // 检查玩家是否拥有该称号
    var playerTitle PlayerTitle
    if err := tm.db.Where("player_id = ? AND title_id = ?", playerID, titleID).First(&playerTitle).Error; err != nil {
        return err
    }
    
    // 检查称号是否过期
    if !playerTitle.ExpireTime.IsZero() && playerTitle.ExpireTime.Before(time.Now()) {
        return errors.New("title has expired")
    }
    
    // 事务处理
    return tm.db.Transaction(func(tx *gorm.DB) error {
        // 移除玩家当前佩戴的称号
        if err := tx.Model(&PlayerTitle{}).Where("player_id = ? AND is_wearing = ?", playerID, true).Update("is_wearing", false).Error; err != nil {
            return err
        }
        
        // 佩戴新称号
        playerTitle.IsWearing = true
        if err := tx.Save(&playerTitle).Error; err != nil {
            return err
        }
        
        return nil
    })
}
```

## 4. Handler 实现

### 4.1 成就Handler

```go
// AchievementHandler 成就处理器
type AchievementHandler struct {
    achievementService *AchievementService
}

// NewAchievementHandler 创建成就处理器实例
func NewAchievementHandler(achievementService *AchievementService) *AchievementHandler {
    return &AchievementHandler{
        achievementService: achievementService,
    }
}

// GetAchievementList 获取成就列表
func (h *AchievementHandler) GetAchievementList(c *gin.Context) {
    var req achievement.AchievementListRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
        return
    }
    
    achievements, totalPoint, err := h.achievementService.GetAchievementList(req.PlayerId)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
        return
    }
    
    c.JSON(http.StatusOK, achievement.AchievementListResponse{
        Achievements: achievements,
        TotalPoint:   int32(totalPoint),
    })
}

// ClaimAchievementReward 领取成就奖励
func (h *AchievementHandler) ClaimAchievementReward(c *gin.Context) {
    var req achievement.AchievementRewardRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
        return
    }
    
    success, err := h.achievementService.GiveAchievementReward(req.PlayerId, req.AchievementId)
    if err != nil {
        c.JSON(http.StatusInternalServerError, achievement.AchievementRewardResponse{
            Success: false,
            Message: err.Error(),
        })
        return
    }
    
    c.JSON(http.StatusOK, achievement.AchievementRewardResponse{
        Success: success,
        Message: "成就奖励领取成功",
    })
}

// GetTitleList 获取称号列表
func (h *AchievementHandler) GetTitleList(c *gin.Context) {
    var req achievement.TitleListRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
        return
    }
    
    titles, err := h.achievementService.GetTitleList(req.PlayerId)
    if err != nil {
        c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
        return
    }
    
    c.JSON(http.StatusOK, achievement.TitleListResponse{
        Titles: titles,
    })
}

// WearTitle 佩戴称号
func (h *AchievementHandler) WearTitle(c *gin.Context) {
    var req achievement.TitleWearRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
        return
    }
    
    err := h.achievementService.WearTitle(req.PlayerId, req.TitleId)
    if err != nil {
        c.JSON(http.StatusInternalServerError, achievement.TitleWearResponse{
            Success: false,
            Message: err.Error(),
        })
        return
    }
    
    c.JSON(http.StatusOK, achievement.TitleWearResponse{
        Success: true,
        Message: "称号佩戴成功",
    })
}

// TriggerAchievement 触发成就
func (h *AchievementHandler) TriggerAchievement(c *gin.Context) {
    var req achievement.AchievementTriggerRequest
    if err := c.ShouldBindJSON(&req); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
        return
    }
    
    completedAchievements, err := h.achievementService.CheckAchievement(req.PlayerId, int(req.AchievementType), int(req.Value))
    if err != nil {
        c.JSON(http.StatusInternalServerError, achievement.AchievementTriggerResponse{
            Success: false,
        })
        return
    }
    
    c.JSON(http.StatusOK, achievement.AchievementTriggerResponse{
        Success:             true,
        CompletedAchievements: completedAchievements,
    })
}
```

## 5. 服务层实现

### 5.1 成就服务

```go
// AchievementService 成就服务
type AchievementService struct {
    achievementManager *AchievementManager
}

// NewAchievementService 创建成就服务实例
func NewAchievementService(achievementManager *AchievementManager) *AchievementService {
    return &AchievementService{
        achievementManager: achievementManager,
    }
}

// GetAchievementList 获取成就列表
func (s *AchievementService) GetAchievementList(playerID uint64) ([]achievement.AchievementInfo, int, error) {
    return s.achievementManager.GetAchievementList(playerID)
}

// GiveAchievementReward 发放成就奖励
func (s *AchievementService) GiveAchievementReward(playerID uint64, achievementID uint64) (bool, error) {
    return s.achievementManager.GiveAchievementReward(playerID, achievementID)
}

// CheckAchievement 检查成就
func (s *AchievementService) CheckAchievement(playerID uint64, achievementType int, value int) ([]uint64, error) {
    return s.achievementManager.CheckAchievement(playerID, achievementType, value)
}

// GetTitleList 获取称号列表
func (s *AchievementService) GetTitleList(playerID uint64) ([]achievement.TitleInfo, error) {
    titleManager := s.achievementManager.titleService
    return titleManager.GetTitleList(playerID)
}

// WearTitle 佩戴称号
func (s *AchievementService) WearTitle(playerID uint64, titleID uint64) error {
    titleManager := s.achievementManager.titleService
    return titleManager.WearTitle(playerID, titleID)
}
```

## 6. 路由配置

### 6.1 HTTP路由

```go
// SetupAchievementRoutes 设置成就系统路由
func SetupAchievementRoutes(router *gin.Engine, achievementHandler *AchievementHandler) {
    achievementGroup := router.Group("/api/achievement")
    {
        achievementGroup.POST("/list", achievementHandler.GetAchievementList)
        achievementGroup.POST("/reward", achievementHandler.ClaimAchievementReward)
        achievementGroup.POST("/title/list", achievementHandler.GetTitleList)
        achievementGroup.POST("/title/wear", achievementHandler.WearTitle)
        achievementGroup.POST("/trigger", achievementHandler.TriggerAchievement)
    }
}
```

### 6.2 gRPC路由

```go
// RegisterAchievementService 注册成就服务
func RegisterAchievementService(s *grpc.Server, achievementService *AchievementService) {
    achievement.RegisterAchievementServiceServer(s, &achievementServer{
        achievementService: achievementService,
    })
}

// achievementServer 成就服务gRPC实现
type achievementServer struct {
    achievement.UnimplementedAchievementServiceServer
    achievementService *AchievementService
}

// GetAchievementList 获取成就列表
func (s *achievementServer) GetAchievementList(ctx context.Context, req *achievement.AchievementListRequest) (*achievement.AchievementListResponse, error) {
    achievements, totalPoint, err := s.achievementService.GetAchievementList(req.PlayerId)
    if err != nil {
        return nil, err
    }
    
    return &achievement.AchievementListResponse{
        Achievements: achievements,
        TotalPoint:   int32(totalPoint),
    }, nil
}

// ClaimAchievementReward 领取成就奖励
func (s *achievementServer) ClaimAchievementReward(ctx context.Context, req *achievement.AchievementRewardRequest) (*achievement.AchievementRewardResponse, error) {
    success, err := s.achievementService.GiveAchievementReward(req.PlayerId, req.AchievementId)
    if err != nil {
        return &achievement.AchievementRewardResponse{
            Success: false,
            Message: err.Error(),
        }, nil
    }
    
    return &achievement.AchievementRewardResponse{
        Success: success,
        Message: "成就奖励领取成功",
    }, nil
}

// GetTitleList 获取称号列表
func (s *achievementServer) GetTitleList(ctx context.Context, req *achievement.TitleListRequest) (*achievement.TitleListResponse, error) {
    titles, err := s.achievementService.GetTitleList(req.PlayerId)
    if err != nil {
        return nil, err
    }
    
    return &achievement.TitleListResponse{
        Titles: titles,
    }, nil
}

// WearTitle 佩戴称号
func (s *achievementServer) WearTitle(ctx context.Context, req *achievement.TitleWearRequest) (*achievement.TitleWearResponse, error) {
    err := s.achievementService.WearTitle(req.PlayerId, req.TitleId)
    if err != nil {
        return &achievement.TitleWearResponse{
            Success: false,
            Message: err.Error(),
        }, nil
    }
    
    return &achievement.TitleWearResponse{
        Success: true,
        Message: "称号佩戴成功",
    }, nil
}

// TriggerAchievement 触发成就
func (s *achievementServer) TriggerAchievement(ctx context.Context, req *achievement.AchievementTriggerRequest) (*achievement.AchievementTriggerResponse, error) {
    completedAchievements, err := s.achievementService.CheckAchievement(req.PlayerId, int(req.AchievementType), int(req.Value))
    if err != nil {
        return &achievement.AchievementTriggerResponse{
            Success: false,
        }, nil
    }
    
    return &achievement.AchievementTriggerResponse{
        Success:             true,
        CompletedAchievements: completedAchievements,
    }, nil
}
```

## 7. 依赖注入

### 7.1 服务注册

```go
// RegisterAchievementServices 注册成就系统服务
func RegisterAchievementServices(container *Container) {
    // 注册成就管理器
    container.Register(NewAchievementManager,
        DIC.Inject("db"),
        DIC.Inject("itemService"),
        DIC.Inject("currencyService"),
        DIC.Inject("titleService"),
    )
    
    // 注册成就服务
    container.Register(NewAchievementService,
        DIC.Inject("achievementManager"),
    )
    
    // 注册成就处理器
    container.Register(NewAchievementHandler,
        DIC.Inject("achievementService"),
    )
    
    // 注册称号管理器
    container.Register(NewTitleManager,
        DIC.Inject("db"),
    )
}
```

## 8. 配置管理

### 8.1 配置项

```go
// AchievementConfig 成就系统配置
type AchievementConfig struct {
    CheckInterval    int           `yaml:"check_interval"`     // 成就检查间隔(秒)
    MaxAchievements  int           `yaml:"max_achievements"`   // 最大成就数量
    CacheExpiration  time.Duration `yaml:"cache_expiration"`   // 缓存过期时间
}

// LoadAchievementConfig 加载成就系统配置
func LoadAchievementConfig(configPath string) (*AchievementConfig, error) {
    var config AchievementConfig
    
    data, err := ioutil.ReadFile(configPath)
    if err != nil {
        return nil, err
    }
    
    if err := yaml.Unmarshal(data, &config); err != nil {
        return nil, err
    }
    
    // 设置默认值
    if config.CheckInterval == 0 {
        config.CheckInterval = 5
    }
    
    if config.MaxAchievements == 0 {
        config.MaxAchievements = 1000
    }
    
    if config.CacheExpiration == 0 {
        config.CacheExpiration = 30 * time.Minute
    }
    
    return &config, nil
}
```

## 9. 监控与日志

### 9.1 监控指标

```go
// AchievementMetrics 成就系统监控指标
type AchievementMetrics struct {
    AchievementCheckCount     prometheus.Counter
    AchievementCompleteCount  prometheus.Counter
    AchievementRewardCount    prometheus.Counter
    AchievementCheckDuration  prometheus.Histogram
}

// NewAchievementMetrics 创建成就系统监控指标
func NewAchievementMetrics(registry *prometheus.Registry) *AchievementMetrics {
    metrics := &AchievementMetrics{
        AchievementCheckCount: prometheus.NewCounter(prometheus.CounterOpts{
            Name: "achievement_check_count",
            Help: "Number of achievement checks",
        }),
        AchievementCompleteCount: prometheus.NewCounter(prometheus.CounterOpts{
            Name: "achievement_complete_count",
            Help: "Number of achievements completed",
        }),
        AchievementRewardCount: prometheus.NewCounter(prometheus.CounterOpts{
            Name: "achievement_reward_count",
            Help: "Number of achievement rewards given",
        }),
        AchievementCheckDuration: prometheus.NewHistogram(prometheus.HistogramOpts{
            Name: "achievement_check_duration_seconds",
            Help: "Duration of achievement check operations",
        }),
    }
    
    registry.MustRegister(
        metrics.AchievementCheckCount,
        metrics.AchievementCompleteCount,
        metrics.AchievementRewardCount,
        metrics.AchievementCheckDuration,
    )
    
    return metrics
}
```

### 9.2 日志记录

```go
// AchievementLogger 成就系统日志记录器
type AchievementLogger struct {
    logger *zap.Logger
}

// NewAchievementLogger 创建成就系统日志记录器
func NewAchievementLogger(logger *zap.Logger) *AchievementLogger {
    return &AchievementLogger{
        logger: logger.Named("achievement"),
    }
}

// LogAchievementCheck 记录成就检查日志
func (l *AchievementLogger) LogAchievementCheck(playerID uint64, achievementType int, value int) {
    l.logger.Info("Achievement check",
        zap.Uint64("player_id", playerID),
        zap.Int("achievement_type", achievementType),
        zap.Int("value", value),
    )
}

// LogAchievementComplete 记录成就完成日志
func (l *AchievementLogger) LogAchievementComplete(playerID uint64, achievementID uint64) {
    l.logger.Info("Achievement completed",
        zap.Uint64("player_id", playerID),
        zap.Uint64("achievement_id", achievementID),
    )
}

// LogAchievementReward 记录成就奖励发放日志
func (l *AchievementLogger) LogAchievementReward(playerID uint64, achievementID uint64, rewardType int, rewardID uint64, rewardCount int) {
    l.logger.Info("Achievement reward given",
        zap.Uint64("player_id", playerID),
        zap.Uint64("achievement_id", achievementID),
        zap.Int("reward_type", rewardType),
        zap.Uint64("reward_id", rewardID),
        zap.Int("reward_count", rewardCount),
    )
}

// LogError 记录错误日志
func (l *AchievementLogger) LogError(err error, message string, fields ...zap.Field) {
    l.logger.Error(message, append(fields, zap.Error(err))...)
}
```

## 10. 测试计划

### 10.1 单元测试

1. **数据模型测试**：测试数据模型的创建、查询、更新和删除操作
2. **成就判定测试**：测试成就判定逻辑的正确性
3. **奖励发放测试**：测试不同类型奖励的发放逻辑
4. **称号管理测试**：测试称号的获取、佩戴和过期逻辑

### 10.2 集成测试

1. **成就系统与玩家系统集成测试**：测试成就系统与玩家系统的交互
2. **成就系统与物品系统集成测试**：测试成就奖励发放与物品系统的交互
3. **成就系统与货币系统集成测试**：测试成就奖励发放与货币系统的交互

### 10.3 性能测试

1. **成就判定性能测试**：测试高频成就触发场景下的系统性能
2. **成就列表查询性能测试**：测试大量成就数据下的查询性能
3. **并发测试**：测试多玩家同时触发成就的系统稳定性

### 10.4 安全测试

1. **权限测试**：测试成就操作的权限控制
2. **数据一致性测试**：测试成就数据的一致性和完整性
3. **异常处理测试**：测试系统对异常情况的处理能力

## 11. 部署与维护

### 11.1 部署方案

1. **容器化部署**：使用Docker容器部署成就系统服务
2. **服务编排**：使用Kubernetes进行服务编排和管理
3. **配置管理**：使用配置中心管理成就系统配置

### 11.2 维护计划

1. **定期备份**：定期备份成就系统数据
2. **监控告警**：设置监控指标和告警机制
3. **日志分析**：定期分析成就系统日志，优化系统性能
4. **版本更新**：制定成就系统版本更新计划

### 11.3 故障处理

1. **故障定位**：使用监控和日志定位故障原因
2. **故障恢复**：制定故障恢复方案
3. **灾备方案**：建立成就系统灾备机制

## 12. 总结

成就系统是DNF游戏中的重要组成部分，通过合理的设计和实现，可以为玩家提供丰富的游戏内容和良好的游戏体验。本实现方案采用Go语言，结合现代化的技术栈，实现了一个高效、可靠的成就系统。

主要特点包括：

1. **模块化设计**：将成就系统拆分为多个模块，提高代码可维护性
2. **高性能**：采用缓存和异步处理，提高系统性能
3. **可扩展性**：通过接口抽象和依赖注入，便于扩展新的成就类型和奖励类型
4. **可靠性**：完善的错误处理和事务管理，确保系统稳定性
5. **可监控性**：丰富的监控指标和日志记录，便于系统维护

通过本实现方案，可以为玩家提供一个功能完善、性能优异的成就系统，增强游戏的可玩性和用户粘性。