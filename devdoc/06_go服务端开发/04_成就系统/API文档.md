# 成就系统API文档

## 1. 概述

本文档描述了成就系统的API接口设计，包括HTTP接口和gRPC接口。这些接口用于实现成就的查询、奖励领取、称号管理等功能。

## 2. 接口设计

### 2.1 HTTP接口

#### 2.1.1 成就列表查询

- **路径**：`/api/achievement/list`
- **方法**：`POST`
- **请求体**：
  ```json
  {
    "player_id": 123456
  }
  ```
- **响应体**：
  ```json
  {
    "achievements": [
      {
        "id": 1,
        "name": "初出茅庐",
        "description": "完成第一个任务",
        "target_value": 1,
        "current_value": 1,
        "is_completed": true,
        "is_rewarded": false,
        "point": 10
      }
    ],
    "total_point": 10
  }
  ```
- **状态码**：
  - `200`：成功
  - `400`：请求参数错误
  - `500`：服务器内部错误

#### 2.1.2 成就奖励领取

- **路径**：`/api/achievement/reward`
- **方法**：`POST`
- **请求体**：
  ```json
  {
    "player_id": 123456,
    "achievement_id": 1
  }
  ```
- **响应体**：
  ```json
  {
    "success": true,
    "message": "成就奖励领取成功",
    "reward_id": 1001,
    "reward_count": 1
  }
  ```
- **状态码**：
  - `200`：成功
  - `400`：请求参数错误
  - `500`：服务器内部错误

#### 2.1.3 称号列表查询

- **路径**：`/api/achievement/title/list`
- **方法**：`POST`
- **请求体**：
  ```json
  {
    "player_id": 123456
  }
  ```
- **响应体**：
  ```json
  {
    "titles": [
      {
        "id": 1,
        "name": "勇士",
        "description": "完成10个任务获得",
        "attribute_type": 1,
        "attribute_value": 10,
        "is_wearing": false,
        "is_expired": false,
        "expire_time": 0
      }
    ]
  }
  ```
- **状态码**：
  - `200`：成功
  - `400`：请求参数错误
  - `500`：服务器内部错误

#### 2.1.4 称号佩戴

- **路径**：`/api/achievement/title/wear`
- **方法**：`POST`
- **请求体**：
  ```json
  {
    "player_id": 123456,
    "title_id": 1
  }
  ```
- **响应体**：
  ```json
  {
    "success": true,
    "message": "称号佩戴成功"
  }
  ```
- **状态码**：
  - `200`：成功
  - `400`：请求参数错误
  - `500`：服务器内部错误

#### 2.1.5 成就触发

- **路径**：`/api/achievement/trigger`
- **方法**：`POST`
- **请求体**：
  ```json
  {
    "player_id": 123456,
    "achievement_type": 1,
    "value": 1
  }
  ```
- **响应体**：
  ```json
  {
    "success": true,
    "completed_achievements": [1, 2]
  }
  ```
- **状态码**：
  - `200`：成功
  - `400`：请求参数错误
  - `500`：服务器内部错误

### 2.2 gRPC接口

#### 2.2.1 成就服务

```protobuf
service AchievementService {
  // 获取成就列表
  rpc GetAchievementList(AchievementListRequest) returns (AchievementListResponse);
  
  // 领取成就奖励
  rpc ClaimAchievementReward(AchievementRewardRequest) returns (AchievementRewardResponse);
  
  // 获取称号列表
  rpc GetTitleList(TitleListRequest) returns (TitleListResponse);
  
  // 佩戴称号
  rpc WearTitle(TitleWearRequest) returns (TitleWearResponse);
  
  // 触发成就
  rpc TriggerAchievement(AchievementTriggerRequest) returns (AchievementTriggerResponse);
}
```

## 3. 数据结构

### 3.1 请求数据结构

#### 3.1.1 成就列表请求

```protobuf
message AchievementListRequest {
  uint64 player_id = 1;
}
```

#### 3.1.2 成就奖励领取请求

```protobuf
message AchievementRewardRequest {
  uint64 player_id = 1;
  uint64 achievement_id = 2;
}
```

#### 3.1.3 称号列表请求

```protobuf
message TitleListRequest {
  uint64 player_id = 1;
}
```

#### 3.1.4 称号佩戴请求

```protobuf
message TitleWearRequest {
  uint64 player_id = 1;
  uint64 title_id = 2;
}
```

#### 3.1.5 成就触发请求

```protobuf
message AchievementTriggerRequest {
  uint64 player_id = 1;
  int32 achievement_type = 2;
  int32 value = 3;
}
```

### 3.2 响应数据结构

#### 3.2.1 成就信息

```protobuf
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
```

#### 3.2.2 成就列表响应

```protobuf
message AchievementListResponse {
  repeated AchievementInfo achievements = 1;
  int32 total_point = 2;
}
```

#### 3.2.3 成就奖励领取响应

```protobuf
message AchievementRewardResponse {
  bool success = 1;
  string message = 2;
  uint64 reward_id = 3;
  int32 reward_count = 4;
}
```

#### 3.2.4 称号信息

```protobuf
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
```

#### 3.2.5 称号列表响应

```protobuf
message TitleListResponse {
  repeated TitleInfo titles = 1;
}
```

#### 3.2.6 称号佩戴响应

```protobuf
message TitleWearResponse {
  bool success = 1;
  string message = 2;
}
```

#### 3.2.7 成就触发响应

```protobuf
message AchievementTriggerResponse {
  bool success = 1;
  repeated uint64 completed_achievements = 2;
}
```

## 4. 接口实现

### 4.1 HTTP接口实现

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

### 4.2 gRPC接口实现

```go
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

## 5. 接口测试

### 5.1 测试工具

- **Postman**：用于测试HTTP接口
- **gRPCurl**：用于测试gRPC接口
- **JMeter**：用于性能测试

### 5.2 测试用例

#### 5.2.1 成就列表查询测试

1. **正常场景**：玩家ID存在，返回成就列表
2. **异常场景**：玩家ID不存在，返回空列表
3. **边界场景**：玩家拥有大量成就，测试接口响应时间

#### 5.2.2 成就奖励领取测试

1. **正常场景**：成就是已完成且未领取奖励，领取成功
2. **异常场景1**：成就未完成，领取失败
3. **异常场景2**：成就已领取奖励，领取失败
4. **异常场景3**：成就ID不存在，领取失败

#### 5.2.3 称号列表查询测试

1. **正常场景**：玩家ID存在，返回称号列表
2. **异常场景**：玩家ID不存在，返回空列表
3. **边界场景**：玩家拥有大量称号，测试接口响应时间

#### 5.2.4 称号佩戴测试

1. **正常场景**：称号存在且未过期，佩戴成功
2. **异常场景1**：称号不存在，佩戴失败
3. **异常场景2**：称号已过期，佩戴失败
4. **异常场景3**：玩家未拥有该称号，佩戴失败

#### 5.2.5 成就触发测试

1. **正常场景**：成就类型存在，触发成功
2. **异常场景**：成就类型不存在，触发失败
3. **边界场景**：同时触发多个成就，测试接口响应时间

## 6. 接口监控

### 6.1 监控指标

- **接口调用次数**：统计各接口的调用次数
- **接口响应时间**：统计各接口的平均响应时间
- **接口错误率**：统计各接口的错误率
- **成就触发频率**：统计成就触发的频率
- **成就完成率**：统计成就的完成率

### 6.2 监控工具

- **Prometheus**：用于收集监控指标
- **Grafana**：用于展示监控指标
- **Alertmanager**：用于设置告警规则

## 7. 接口安全

### 7.1 安全措施

1. **参数验证**：验证请求参数的合法性
2. **权限控制**：验证玩家是否有权限操作
3. **防重放攻击**：使用请求ID和时间戳防止重放攻击
4. **数据加密**：使用HTTPS加密传输数据
5. **限流保护**：对接口进行限流，防止恶意攻击

### 7.2 安全测试

1. **渗透测试**：测试接口的安全性
2. **压力测试**：测试接口的抗攻击能力
3. **数据泄露测试**：测试接口是否存在数据泄露风险

## 8. 总结

成就系统API接口设计采用了RESTful风格的HTTP接口和gRPC接口，实现了成就的查询、奖励领取、称号管理等功能。接口设计遵循了简洁、易用、安全的原则，同时考虑了性能和可扩展性。通过合理的接口设计和实现，可以为玩家提供良好的成就系统体验。