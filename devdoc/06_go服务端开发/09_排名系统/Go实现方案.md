# 排名系统 - Go实现方案

## 1. ProtoBuf 消息定义

### 1.1 核心消息定义

**文件位置**: `proto/dnf/v1/rank.proto`

```protobuf
syntax = "proto3";

package dnf.v1;

option go_package = "gen/dnf/v1";

import "dnf/v1/common.proto";

// 排名信息
message RankInfo {
    uint64 charguid = 1;       // 角色GUID
    string name = 2;           // 角色名称
    uint32 job = 3;            // 职业
    uint32 level = 4;          // 等级
    uint32 rank = 5;           // 排名
    uint32 score = 6;          // 分数
    uint64 exp = 7;            // 经验值
    uint32 equipscore = 8;     // 装备评分
    uint32 guildid = 9;        // 公会ID
    string guildname = 10;     // 公会名称
}

// 查询个人排名请求
message InquirePersonalRankingRequest {
    uint64 charguid = 1;       // 角色GUID
    uint32 type = 2;           // 排名类型
    uint32 transid = 3;        // 交易ID
}

// 查询个人排名响应
message InquirePersonalRankingResponse {
    RankInfo rankinfo = 1;     // 排名信息
    uint32 type = 2;           // 排名类型
    uint32 error = 3;          // 错误码
    string message = 4;        // 错误信息
}

// 查询我的排名请求
message MyRankingRequest {
    uint32 type = 1;           // 排名类型
    uint32 transid = 2;        // 交易ID
}

// 查询我的排名响应
message MyRankingResponse {
    RankInfo rankinfo = 1;     // 排名信息
    uint32 type = 2;           // 排名类型
    uint32 error = 3;          // 错误码
    string message = 4;        // 错误信息
}

// 查询好友排名请求
message RankFriendRequest {
    uint32 type = 1;           // 排名类型
    uint32 transid = 2;        // 交易ID
}

// 查询好友排名响应
message RankFriendResponse {
    repeated RankInfo ranking = 1; // 好友排名列表
    uint32 type = 2;           // 排名类型
    uint32 error = 3;          // 错误码
    string message = 4;        // 错误信息
}

// 查询我的队伍排名请求
message MyPartyRankingRequest {
    uint32 type = 1;           // 排名类型
    uint32 transid = 2;        // 交易ID
}

// 查询我的队伍排名响应
message MyPartyRankingResponse {
    RankInfo rankinfo = 1;     // 队伍排名信息
    uint32 type = 2;           // 排名类型
    uint32 error = 3;          // 错误码
    string message = 4;        // 错误信息
}

// 排行榜列表请求
message RankingListRequest {
    uint32 type = 1;           // 排名类型
    uint32 page = 2;           // 页码
    uint32 page_size = 3;      // 每页数量
}

// 排行榜列表响应
message RankingListResponse {
    repeated RankInfo ranking = 1; // 排行榜列表
    uint32 type = 2;           // 排名类型
    uint32 total = 3;           // 总数量
    uint32 error = 4;          // 错误码
    string message = 5;        // 错误信息
}

// 排名更新请求（内部使用）
message UpdateRankRequest {
    uint64 role_id = 1;         // 角色ID
    uint32 type = 2;           // 排名类型
    uint32 score = 3;           // 分数
}

// 排名更新响应（内部使用）
message UpdateRankResponse {
    bool success = 1;           // 是否成功
    uint32 error = 2;          // 错误码
    string message = 3;        // 错误信息
}
```

### 1.2 消息注册

在消息注册表中添加排名相关的消息类型：

```go
// internal/network/message_registry.go
func RegisterMessages() {
    // 其他消息注册...
    
    // 排名系统消息
    RegisterMessage(&dnfv1.InquirePersonalRankingRequest{}, dnfv1.MessageType_INQUIRE_PERSONAL_RANKING)
    RegisterMessage(&dnfv1.InquirePersonalRankingResponse{}, dnfv1.MessageType_INQUIRE_PERSONAL_RANKING_RESP)
    RegisterMessage(&dnfv1.MyRankingRequest{}, dnfv1.MessageType_MY_RANKING)
    RegisterMessage(&dnfv1.MyRankingResponse{}, dnfv1.MessageType_MY_RANKING_RESP)
    RegisterMessage(&dnfv1.RankFriendRequest{}, dnfv1.MessageType_RANK_FRIEND)
    RegisterMessage(&dnfv1.RankFriendResponse{}, dnfv1.MessageType_RANK_FRIEND_RESP)
    RegisterMessage(&dnfv1.MyPartyRankingRequest{}, dnfv1.MessageType_MY_PARTY_RANKING)
    RegisterMessage(&dnfv1.MyPartyRankingResponse{}, dnfv1.MessageType_MY_PARTY_RANKING_RESP)
    RegisterMessage(&dnfv1.RankingListRequest{}, dnfv1.MessageType_RANKING_LIST)
    RegisterMessage(&dnfv1.RankingListResponse{}, dnfv1.MessageType_RANKING_LIST_RESP)
}
```

## 2. 数据模型设计

### 2.1 核心模型

**文件位置**: `internal/db/models/rank.go`

```go
package models

import (
    "time"
    "gorm.io/gorm"
)

// Rank 排名
type Rank struct {
    ID         uint64    `gorm:"primaryKey;column:id" json:"id"`
    Type       uint32    `gorm:"column:type;index:idx_type_rank" json:"type"`
    RoleID     uint64    `gorm:"column:roleId;uniqueIndex:idx_type_role;index" json:"role_id"`
    RoleName   string    `gorm:"column:roleName;size:50" json:"role_name"`
    Job        uint32    `gorm:"column:job" json:"job"`
    Level      uint32    `gorm:"column:level" json:"level"`
    Rank       uint32    `gorm:"column:rank;index:idx_type_rank" json:"rank"`
    Score      uint32    `gorm:"column:score;index:idx_type_score" json:"score"`
    Exp        uint64    `gorm:"column:exp" json:"exp"`
    EquipScore uint32    `gorm:"column:equipScore" json:"equip_score"`
    GuildID    uint32    `gorm:"column:guildId" json:"guild_id"`
    GuildName  string    `gorm:"column:guildName;size:50" json:"guild_name"`
    UpdateTime time.Time `gorm:"column:updateTime;index" json:"update_time"`
    CreateTime time.Time `gorm:"column:createTime" json:"create_time"`
}

func (Rank) TableName() string {
    return "t_rank"
}

// RankHistory 排名历史
type RankHistory struct {
    ID         uint64    `gorm:"primaryKey;column:id" json:"id"`
    Type       uint32    `gorm:"column:type;index" json:"type"`
    RoleID     uint64    `gorm:"column:roleId;index" json:"role_id"`
    RoleName   string    `gorm:"column:roleName;size:50" json:"role_name"`
    Rank       uint32    `gorm:"column:rank" json:"rank"`
    Score      uint32    `gorm:"column:score" json:"score"`
    RecordTime time.Time `gorm:"column:recordTime;index" json:"record_time"`
    CreateTime time.Time `gorm:"column:createTime" json:"create_time"`
}

func (RankHistory) TableName() string {
    return "t_rank_history"
}

// RankConfig 排名配置
type RankConfig struct {
    ID             uint64    `gorm:"primaryKey;column:id" json:"id"`
    Type           uint32    `gorm:"column:type;uniqueIndex" json:"type"`
    Name           string    `gorm:"column:name;size:50" json:"name"`
    Description    string    `gorm:"column:description;size:200" json:"description"`
    ScoreField     string    `gorm:"column:scoreField;size:50" json:"score_field"`
    UpdateInterval uint32    `gorm:"column:updateInterval" json:"update_interval"`
    MaxRank        uint32    `gorm:"column:maxRank" json:"max_rank"`
    Status         uint32    `gorm:"column:status;default:1" json:"status"`
    CreateTime     time.Time `gorm:"column:createTime" json:"create_time"`
    UpdateTime     time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (RankConfig) TableName() string {
    return "t_rank_config"
}

// RankReward 排名奖励
type RankReward struct {
    ID         uint64    `gorm:"primaryKey;column:id" json:"id"`
    Type       uint32    `gorm:"column:type;index" json:"type"`
    MinRank    uint32    `gorm:"column:minRank" json:"min_rank"`
    MaxRank    uint32    `gorm:"column:maxRank" json:"max_rank"`
    RewardType uint32    `gorm:"column:rewardType" json:"reward_type"`
    RewardID   uint32    `gorm:"column:rewardId" json:"reward_id"`
    RewardCount uint32   `gorm:"column:rewardCount" json:"reward_count"`
    Status     uint32    `gorm:"column:status;default:1" json:"status"`
    CreateTime time.Time `gorm:"column:createTime" json:"create_time"`
    UpdateTime time.Time `gorm:"column:updateTime" json:"update_time"`
}

func (RankReward) TableName() string {
    return "t_rank_reward"
}
```

### 2.2 数据库迁移

**文件位置**: `scripts/migrate_rank.go`

```go
package main

import (
    "log"
    "gorm.io/gorm"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
)

func migrateRank(db *gorm.DB) error {
    // 自动迁移排名相关表
    err := db.AutoMigrate(
        &models.Rank{},
        &models.RankHistory{},
        &models.RankConfig{},
        &models.RankReward{},
    )
    if err != nil {
        log.Printf("Failed to migrate rank tables: %v", err)
        return err
    }

    // 初始化排名配置
    initRankConfig(db)

    // 初始化排名奖励
    initRankReward(db)

    log.Println("Rank migration completed successfully")
    return nil
}

func initRankConfig(db *gorm.DB) {
    configs := []models.RankConfig{
        {
            Type:           1, // 等级排名
            Name:           "等级排名",
            Description:    "按玩家等级排序",
            ScoreField:     "level",
            UpdateInterval: 60, // 1分钟
            MaxRank:        100,
            Status:         1,
        },
        {
            Type:           2, // 战力排名
            Name:           "战力排名",
            Description:    "按玩家战力排序",
            ScoreField:     "equipScore",
            UpdateInterval: 60, // 1分钟
            MaxRank:        100,
            Status:         1,
        },
        {
            Type:           3, // 副本排名
            Name:           "副本排名",
            Description:    "按副本通关时间排序",
            ScoreField:     "score",
            UpdateInterval: 300, // 5分钟
            MaxRank:        100,
            Status:         1,
        },
        {
            Type:           4, // 财富排名
            Name:           "财富排名",
            Description:    "按玩家财富排序",
            ScoreField:     "score",
            UpdateInterval: 3600, // 1小时
            MaxRank:        100,
            Status:         1,
        },
    }

    for _, config := range configs {
        var existing models.RankConfig
        if result := db.Where("type = ?", config.Type).First(&existing); result.Error == gorm.ErrRecordNotFound {
            db.Create(&config)
        }
    }
}

func initRankReward(db *gorm.DB) {
    // 等级排名奖励
    levelRewards := []models.RankReward{
        {Type: 1, MinRank: 1, MaxRank: 1, RewardType: 1, RewardID: 1001, RewardCount: 1000000}, // 100万金币
        {Type: 1, MinRank: 2, MaxRank: 3, RewardType: 1, RewardID: 1001, RewardCount: 500000},  // 50万金币
        {Type: 1, MinRank: 4, MaxRank: 10, RewardType: 1, RewardID: 1001, RewardCount: 100000}, // 10万金币
    }

    // 战力排名奖励
    powerRewards := []models.RankReward{
        {Type: 2, MinRank: 1, MaxRank: 1, RewardType: 2, RewardID: 2001, RewardCount: 1}, // 传说装备箱
        {Type: 2, MinRank: 2, MaxRank: 3, RewardType: 2, RewardID: 2002, RewardCount: 1}, // 史诗装备箱
        {Type: 2, MinRank: 4, MaxRank: 10, RewardType: 2, RewardID: 2003, RewardCount: 1}, // 稀有装备箱
    }

    rewards := append(levelRewards, powerRewards...)

    for _, reward := range rewards {
        var existing models.RankReward
        if result := db.Where("type = ? AND minRank = ? AND maxRank = ?", reward.Type, reward.MinRank, reward.MaxRank).First(&existing); result.Error == gorm.ErrRecordNotFound {
            db.Create(&reward)
        }
    }
}
```

## 3. Handler 实现

### 3.1 核心处理器

**文件位置**: `internal/game/handlers/rank.go`

```go
package handlers

import (
    "context"

    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/rank_service"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
    dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
    "google.golang.org/protobuf/proto"
)

type RankHandler struct {
    rankService *rank_service.RankService
}

func NewRankHandler(rankService *rank_service.RankService) *RankHandler {
    return &RankHandler{
        rankService: rankService,
    }
}

// InquirePersonalRankingHandler 查询个人排名处理器
func (h *RankHandler) InquirePersonalRankingHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.InquirePersonalRankingRequest)
    if !ok {
        return
    }

    rankInfo, err := h.rankService.GetPersonalRank(context.Background(), req.Charguid, req.Type)
    if err != nil {
        h.sendError(sess, req, 3, "failed to get personal ranking")
        return
    }

    resp := &dnfv1.InquirePersonalRankingResponse{
        Rankinfo: rankInfo,
        Type:     req.Type,
        Error:    0,
    }
    sess.Send(resp)
}

// MyRankingHandler 查询我的排名处理器
func (h *RankHandler) MyRankingHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.MyRankingRequest)
    if !ok {
        return
    }

    rankInfo, err := h.rankService.GetMyRank(context.Background(), sess.RoleID, req.Type)
    if err != nil {
        h.sendError(sess, req, 3, "failed to get my ranking")
        return
    }

    resp := &dnfv1.MyRankingResponse{
        Rankinfo: rankInfo,
        Type:     req.Type,
        Error:    0,
    }
    sess.Send(resp)
}

// RankFriendHandler 查询好友排名处理器
func (h *RankHandler) RankFriendHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.RankFriendRequest)
    if !ok {
        return
    }

    rankings, err := h.rankService.GetFriendRankings(context.Background(), sess.RoleID, req.Type)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get friend rankings")
        return
    }

    resp := &dnfv1.RankFriendResponse{
        Ranking: rankings,
        Type:    req.Type,
        Error:   0,
    }
    sess.Send(resp)
}

// MyPartyRankingHandler 查询我的队伍排名处理器
func (h *RankHandler) MyPartyRankingHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.MyPartyRankingRequest)
    if !ok {
        return
    }

    rankInfo, err := h.rankService.GetMyPartyRank(context.Background(), sess.RoleID, req.Type)
    if err != nil {
        h.sendError(sess, req, 3, "failed to get my party ranking")
        return
    }

    resp := &dnfv1.MyPartyRankingResponse{
        Rankinfo: rankInfo,
        Type:     req.Type,
        Error:    0,
    }
    sess.Send(resp)
}

// RankingListHandler 排行榜列表处理器
func (h *RankHandler) RankingListHandler(sess *network.Session, msg proto.Message) {
    req, ok := msg.(*dnfv1.RankingListRequest)
    if !ok {
        return
    }

    rankings, total, err := h.rankService.GetRankingList(context.Background(), req.Type, req.Page, req.PageSize)
    if err != nil {
        h.sendError(sess, req, 1, "failed to get ranking list")
        return
    }

    resp := &dnfv1.RankingListResponse{
        Ranking: rankings,
        Type:    req.Type,
        Total:   total,
        Error:   0,
    }
    sess.Send(resp)
}

func (h *RankHandler) sendError(sess *network.Session, req proto.Message, code uint32, message string) {
    switch req.(type) {
    case *dnfv1.InquirePersonalRankingRequest:
        resp := &dnfv1.InquirePersonalRankingResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.MyRankingRequest:
        resp := &dnfv1.MyRankingResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.RankFriendRequest:
        resp := &dnfv1.RankFriendResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.MyPartyRankingRequest:
        resp := &dnfv1.MyPartyRankingResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    case *dnfv1.RankingListRequest:
        resp := &dnfv1.RankingListResponse{
            Error:   code,
            Message: message,
        }
        sess.Send(resp)
    }
}
```

### 3.2 Handler 注册

**文件位置**: `internal/game/handlers/register.go`

```go
package handlers

import (
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/rank_service"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/network"
    dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

func RegisterHandlers(server *network.Server) {
    // 其他处理器注册...
    
    // 排名系统处理器
    rankService := rank_service.NewRankService(server.Store)
    rankHandler := NewRankHandler(rankService)
    
    server.RegisterHandler(dnfv1.MessageType_INQUIRE_PERSONAL_RANKING, rankHandler.InquirePersonalRankingHandler)
    server.RegisterHandler(dnfv1.MessageType_MY_RANKING, rankHandler.MyRankingHandler)
    server.RegisterHandler(dnfv1.MessageType_RANK_FRIEND, rankHandler.RankFriendHandler)
    server.RegisterHandler(dnfv1.MessageType_MY_PARTY_RANKING, rankHandler.MyPartyRankingHandler)
    server.RegisterHandler(dnfv1.MessageType_RANKING_LIST, rankHandler.RankingListHandler)
}
```

## 4. Service 实现

### 4.1 核心服务

**文件位置**: `internal/game/rank_service/rank.go`

```go
package rank_service

import (
    "context"
    "time"

    "github.com/pixb/DnfGameServer/dnf-go-server/store"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
    dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

type RankService struct {
    store *store.Store
}

func NewRankService(store *store.Store) *RankService {
    return &RankService{
        store: store,
    }
}

// GetPersonalRank 获取个人排名
func (s *RankService) GetPersonalRank(ctx context.Context, roleID uint64, rankType uint32) (*dnfv1.RankInfo, error) {
    rank, err := s.store.GetRank(ctx, roleID, rankType)
    if err != nil {
        return nil, err
    }

    return &dnfv1.RankInfo{
        Charguid:   rank.RoleID,
        Name:       rank.RoleName,
        Job:        rank.Job,
        Level:      rank.Level,
        Rank:       rank.Rank,
        Score:      rank.Score,
        Exp:        rank.Exp,
        Equipscore: rank.EquipScore,
        Guildid:    rank.GuildID,
        Guildname:  rank.GuildName,
    }, nil
}

// GetMyRank 获取我的排名
func (s *RankService) GetMyRank(ctx context.Context, roleID uint64, rankType uint32) (*dnfv1.RankInfo, error) {
    return s.GetPersonalRank(ctx, roleID, rankType)
}

// GetFriendRankings 获取好友排名
func (s *RankService) GetFriendRankings(ctx context.Context, roleID uint64, rankType uint32) ([]*dnfv1.RankInfo, error) {
    // 1. 获取好友列表
    friends, err := s.store.GetFriendList(ctx, roleID)
    if err != nil {
        return nil, err
    }

    if len(friends) == 0 {
        return []*dnfv1.RankInfo{}, nil
    }

    // 2. 获取好友排名
    roleIDs := make([]uint64, len(friends))
    for i, friend := range friends {
        roleIDs[i] = friend.FriendID
    }

    ranks, err := s.store.GetRanksByRoleIDs(ctx, roleIDs, rankType)
    if err != nil {
        return nil, err
    }

    // 3. 转换为响应格式
    rankings := make([]*dnfv1.RankInfo, len(ranks))
    for i, rank := range ranks {
        rankings[i] = &dnfv1.RankInfo{
            Charguid:   rank.RoleID,
            Name:       rank.RoleName,
            Job:        rank.Job,
            Level:      rank.Level,
            Rank:       rank.Rank,
            Score:      rank.Score,
            Exp:        rank.Exp,
            Equipscore: rank.EquipScore,
            Guildid:    rank.GuildID,
            Guildname:  rank.GuildName,
        }
    }

    return rankings, nil
}

// GetMyPartyRank 获取我的队伍排名
func (s *RankService) GetMyPartyRank(ctx context.Context, roleID uint64, rankType uint32) (*dnfv1.RankInfo, error) {
    // 1. 获取队伍信息
    party, err := s.store.GetPartyByRoleID(ctx, roleID)
    if err != nil {
        return nil, err
    }

    // 2. 计算队伍总分
    members, err := s.store.GetPartyMembers(ctx, party.ID)
    if err != nil {
        return nil, err
    }

    totalScore := uint32(0)
    for _, member := range members {
        rank, err := s.store.GetRank(ctx, member.RoleID, rankType)
        if err == nil {
            totalScore += rank.Score
        }
    }

    // 3. 返回队伍排名信息
    return &dnfv1.RankInfo{
        Charguid: party.ID,
        Name:     party.Name,
        Rank:     party.Rank,
        Score:    totalScore,
    }, nil
}

// GetRankingList 获取排行榜列表
func (s *RankService) GetRankingList(ctx context.Context, rankType, page, pageSize uint32) ([]*dnfv1.RankInfo, uint32, error) {
    offset := (page - 1) * pageSize

    ranks, total, err := s.store.GetRanksByType(ctx, rankType, offset, pageSize)
    if err != nil {
        return nil, 0, err
    }

    rankings := make([]*dnfv1.RankInfo, len(ranks))
    for i, rank := range ranks {
        rankings[i] = &dnfv1.RankInfo{
            Charguid:   rank.RoleID,
            Name:       rank.RoleName,
            Job:        rank.Job,
            Level:      rank.Level,
            Rank:       rank.Rank,
            Score:      rank.Score,
            Exp:        rank.Exp,
            Equipscore: rank.EquipScore,
            Guildid:    rank.GuildID,
            Guildname:  rank.GuildName,
        }
    }

    return rankings, uint32(total), nil
}

// UpdateRank 更新排名
func (s *RankService) UpdateRank(ctx context.Context, roleID uint64, rankType uint32, score uint32) error {
    // 1. 获取角色信息
    role, err := s.store.GetRole(ctx, roleID)
    if err != nil {
        return err
    }

    // 2. 获取公会信息
    guildName := ""
    if role.GuildID > 0 {
        guild, err := s.store.GetGuild(ctx, role.GuildID)
        if err == nil {
            guildName = guild.Name
        }
    }

    // 3. 更新或创建排名
    rank, err := s.store.GetRank(ctx, roleID, rankType)
    if err == nil {
        rank.Score = score
        rank.Level = role.Level
        rank.Exp = role.Exp
        rank.EquipScore = role.EquipScore
        rank.GuildID = role.GuildID
        rank.GuildName = guildName
        rank.UpdateTime = time.Now()
        return s.store.UpdateRank(ctx, rank)
    }

    newRank := &models.Rank{
        Type:       rankType,
        RoleID:     roleID,
        RoleName:   role.Name,
        Job:        role.Job,
        Level:      role.Level,
        Score:      score,
        Exp:        role.Exp,
        EquipScore: role.EquipScore,
        GuildID:    role.GuildID,
        GuildName:  guildName,
        UpdateTime: time.Now(),
        CreateTime: time.Now(),
    }

    return s.store.CreateRank(ctx, newRank)
}

// RecalculateRank 重新计算排名
func (s *RankService) RecalculateRank(ctx context.Context, rankType uint32) error {
    return s.store.RecalculateRank(ctx, rankType)
}

// SaveRankHistory 保存排名历史
func (s *RankService) SaveRankHistory(ctx context.Context, rankType uint32) error {
    ranks, err := s.store.GetRanksByType(ctx, rankType, 0, 100)
    if err != nil {
        return err
    }

    for _, rank := range ranks {
        history := &models.RankHistory{
            Type:       rankType,
            RoleID:     rank.RoleID,
            RoleName:   rank.RoleName,
            Rank:       rank.Rank,
            Score:      rank.Score,
            RecordTime: time.Now(),
            CreateTime: time.Now(),
        }
        s.store.CreateRankHistory(ctx, history)
    }

    return nil
}

// CheckRankReward 检查并发放排名奖励
func (s *RankService) CheckRankReward(ctx context.Context, rankType uint32) error {
    ranks, err := s.store.GetRanksByType(ctx, rankType, 0, 100)
    if err != nil {
        return err
    }

    for _, rank := range ranks {
        rewards, err := s.store.GetRankRewards(ctx, rankType, rank.Rank)
        if err != nil {
            continue
        }

        for _, reward := range rewards {
            err := s.store.GiveReward(ctx, rank.RoleID, reward.RewardType, reward.RewardID, reward.RewardCount)
            if err != nil {
                continue
            }

            // 记录奖励发放
            s.store.CreateRankRewardRecord(ctx, rank.RoleID, rankType, rank.Rank, reward.RewardID, reward.RewardCount)
        }
    }

    return nil
}
```

## 5. 存储层实现

### 5.1 核心存储接口

**文件位置**: `store/rank.go`

```go
package store

import (
    "context"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
)

// GetRank 获取排名
func (s *Store) GetRank(ctx context.Context, roleID uint64, rankType uint32) (*models.Rank, error) {
    var rank models.Rank
    err := s.db.Where("roleId = ? AND type = ?", roleID, rankType).First(&rank).Error
    if err != nil {
        return nil, err
    }
    return &rank, nil
}

// GetRanksByType 按类型获取排名列表
func (s *Store) GetRanksByType(ctx context.Context, rankType uint32, offset, limit uint32) ([]*models.Rank, int64, error) {
    var ranks []*models.Rank
    var total int64

    // 获取总数
    s.db.Model(&models.Rank{}).Where("type = ?", rankType).Count(&total)

    // 获取排名列表
    err := s.db.Where("type = ?", rankType).
        Order("rank ASC").
        Offset(int(offset)).
        Limit(int(limit)).
        Find(&ranks).Error
    if err != nil {
        return nil, 0, err
    }

    return ranks, total, nil
}

// GetRanksByRoleIDs 按角色ID列表获取排名
func (s *Store) GetRanksByRoleIDs(ctx context.Context, roleIDs []uint64, rankType uint32) ([]*models.Rank, error) {
    var ranks []*models.Rank
    err := s.db.Where("roleId IN ? AND type = ?", roleIDs, rankType).Find(&ranks).Error
    if err != nil {
        return nil, err
    }
    return ranks, nil
}

// CreateRank 创建排名
func (s *Store) CreateRank(ctx context.Context, rank *models.Rank) error {
    return s.db.Create(rank).Error
}

// UpdateRank 更新排名
func (s *Store) UpdateRank(ctx context.Context, rank *models.Rank) error {
    return s.db.Save(rank).Error
}

// RecalculateRank 重新计算排名
func (s *Store) RecalculateRank(ctx context.Context, rankType uint32) error {
    // 1. 获取所有排名
    var ranks []*models.Rank
    err := s.db.Where("type = ?", rankType).Order("score DESC").Find(&ranks).Error
    if err != nil {
        return err
    }

    // 2. 重新计算排名
    for i, rank := range ranks {
        rank.Rank = uint32(i + 1)
        err := s.db.Save(rank).Error
        if err != nil {
            return err
        }
    }

    return nil
}

// CreateRankHistory 创建排名历史
func (s *Store) CreateRankHistory(ctx context.Context, history *models.RankHistory) error {
    return s.db.Create(history).Error
}

// GetRankHistory 获取排名历史
func (s *Store) GetRankHistory(ctx context.Context, roleID uint64, rankType uint32, limit int) ([]*models.RankHistory, error) {
    var histories []*models.RankHistory
    err := s.db.Where("roleId = ? AND type = ?", roleID, rankType).
        Order("recordTime DESC").
        Limit(limit).
        Find(&histories).Error
    if err != nil {
        return nil, err
    }
    return histories, nil
}

// GetRankConfig 获取排名配置
func (s *Store) GetRankConfig(ctx context.Context, rankType uint32) (*models.RankConfig, error) {
    var config models.RankConfig
    err := s.db.Where("type = ?", rankType).First(&config).Error
    if err != nil {
        return nil, err
    }
    return &config, nil
}

// GetRankRewards 获取排名奖励
func (s *Store) GetRankRewards(ctx context.Context, rankType uint32, rank uint32) ([]*models.RankReward, error) {
    var rewards []*models.RankReward
    err := s.db.Where("type = ? AND minRank <= ? AND maxRank >= ? AND status = 1", rankType, rank, rank).Find(&rewards).Error
    if err != nil {
        return nil, err
    }
    return rewards, nil
}

// CreateRankRewardRecord 创建排名奖励记录
func (s *Store) CreateRankRewardRecord(ctx context.Context, roleID uint64, rankType uint32, rank uint32, rewardID uint32, rewardCount uint32) error {
    record := &models.RankRewardRecord{
        RoleID:     roleID,
        Type:       rankType,
        Rank:       rank,
        RewardID:   rewardID,
        RewardCount: rewardCount,
        CreateTime: time.Now(),
    }
    return s.db.Create(record).Error
}
```

## 6. 测试用例

### 6.1 单元测试

**文件位置**: `tests/rank_test.go`

```go
package tests

import (
    "context"
    "testing"

    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/rank_service"
    "github.com/pixb/DnfGameServer/dnf-go-server/store"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
    dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

func TestRankService(t *testing.T) {
    // 初始化存储
    testStore := store.NewTestStore()
    rankService := rank_service.NewRankService(testStore)

    // 测试数据
    testRoleID := uint64(123456)
    testRankType := uint32(1) // 等级排名
    testScore := uint32(1000)

    // 测试1: 更新排名
    err := rankService.UpdateRank(context.Background(), testRoleID, testRankType, testScore)
    if err != nil {
        t.Errorf("UpdateRank failed: %v", err)
    }

    // 测试2: 获取个人排名
    rankInfo, err := rankService.GetPersonalRank(context.Background(), testRoleID, testRankType)
    if err != nil {
        t.Errorf("GetPersonalRank failed: %v", err)
    }
    if rankInfo.Charguid != testRoleID {
        t.Errorf("Expected role ID %d, got %d", testRoleID, rankInfo.Charguid)
    }

    // 测试3: 重新计算排名
    err = rankService.RecalculateRank(context.Background(), testRankType)
    if err != nil {
        t.Errorf("RecalculateRank failed: %v", err)
    }

    // 测试4: 保存排名历史
    err = rankService.SaveRankHistory(context.Background(), testRankType)
    if err != nil {
        t.Errorf("SaveRankHistory failed: %v", err)
    }

    // 测试5: 获取排行榜列表
    rankings, total, err := rankService.GetRankingList(context.Background(), testRankType, 1, 10)
    if err != nil {
        t.Errorf("GetRankingList failed: %v", err)
    }
    if total == 0 {
        t.Error("Expected total > 0")
    }
    if len(rankings) == 0 {
        t.Error("Expected rankings not empty")
    }

    t.Log("All rank service tests passed")
}
```

### 6.2 集成测试

**文件位置**: `tests/rank_integration_test.go`

```go
package tests

import (
    "testing"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/handlers"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/rank_service"
    "github.com/pixb/DnfGameServer/dnf-go-server/store"
    dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

func TestRankIntegration(t *testing.T) {
    // 初始化存储
    testStore := store.NewTestStore()
    rankService := rank_service.NewRankService(testStore)
    rankHandler := handlers.NewRankHandler(rankService)

    // 创建测试会话
    sess := NewTestSession()
    sess.RoleID = 123456

    // 测试1: 查询我的排名
    myRankReq := &dnfv1.MyRankingRequest{
        Type:    1,
        Transid: 1,
    }
    rankHandler.MyRankingHandler(sess, myRankReq)

    // 测试2: 查询排行榜列表
    rankListReq := &dnfv1.RankingListRequest{
        Type:     1,
        Page:     1,
        PageSize: 10,
    }
    rankHandler.RankingListHandler(sess, rankListReq)

    t.Log("All rank integration tests passed")
}
```

## 7. 性能优化

### 7.1 缓存优化

**文件位置**: `store/cache.go`

```go
package store

import (
    "context"
    "encoding/json"
    "time"

    "github.com/redis/go-redis/v9"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
)

// GetRankFromCache 从缓存获取排名
func (s *Store) GetRankFromCache(ctx context.Context, roleID uint64, rankType uint32) (*models.Rank, error) {
    key := fmt.Sprintf("rank:%d:%d", roleID, rankType)
    val, err := s.redis.Get(ctx, key).Result()
    if err != nil {
        return nil, err
    }

    var rank models.Rank
    err = json.Unmarshal([]byte(val), &rank)
    if err != nil {
        return nil, err
    }

    return &rank, nil
}

// SetRankToCache 设置排名到缓存
func (s *Store) SetRankToCache(ctx context.Context, rank *models.Rank) error {
    key := fmt.Sprintf("rank:%d:%d", rank.RoleID, rank.Type)
    val, err := json.Marshal(rank)
    if err != nil {
        return err
    }

    return s.redis.Set(ctx, key, val, 24*time.Hour).Err()
}

// GetRankListFromCache 从缓存获取排行榜列表
func (s *Store) GetRankListFromCache(ctx context.Context, rankType uint32, page uint32) ([]*models.Rank, error) {
    key := fmt.Sprintf("ranklist:%d:%d", rankType, page)
    val, err := s.redis.Get(ctx, key).Result()
    if err != nil {
        return nil, err
    }

    var ranks []*models.Rank
    err = json.Unmarshal([]byte(val), &ranks)
    if err != nil {
        return nil, err
    }

    return ranks, nil
}

// SetRankListToCache 设置排行榜列表到缓存
func (s *Store) SetRankListToCache(ctx context.Context, rankType uint32, page uint32, ranks []*models.Rank) error {
    key := fmt.Sprintf("ranklist:%d:%d", rankType, page)
    val, err := json.Marshal(ranks)
    if err != nil {
        return err
    }

    return s.redis.Set(ctx, key, val, 1*time.Hour).Err()
}
```

### 7.2 批量操作

**文件位置**: `store/rank_batch.go`

```go
package store

import (
    "context"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
)

// BatchUpdateRank 批量更新排名
func (s *Store) BatchUpdateRank(ctx context.Context, ranks []*models.Rank) error {
    return s.db.Transaction(func(tx *gorm.DB) error {
        for _, rank := range ranks {
            if err := tx.Save(rank).Error; err != nil {
                return err
            }
        }
        return nil
    })
}

// BatchCreateRank 批量创建排名
func (s *Store) BatchCreateRank(ctx context.Context, ranks []*models.Rank) error {
    return s.db.Transaction(func(tx *gorm.DB) error {
        for _, rank := range ranks {
            if err := tx.Create(rank).Error; err != nil {
                return err
            }
        }
        return nil
    })
}
```

## 8. 定时任务

### 8.1 排名更新任务

**文件位置**: `internal/game/task/rank_task.go`

```go
package task

import (
    "log"
    "time"

    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/rank_service"
)

type RankTask struct {
    rankService *rank_service.RankService
}

func NewRankTask(rankService *rank_service.RankService) *RankTask {
    return &RankTask{
        rankService: rankService,
    }
}

// Start 启动排名任务
func (t *RankTask) Start() {
    // 实时排名更新（每5分钟）
    go t.realTimeUpdate()

    // 每日排名更新（每天凌晨）
    go t.dailyUpdate()

    // 每周排名更新（每周一凌晨）
    go t.weeklyUpdate()
}

// realTimeUpdate 实时排名更新
func (t *RankTask) realTimeUpdate() {
    ticker := time.NewTicker(5 * time.Minute)
    defer ticker.Stop()

    for {
        <-ticker.C
        log.Println("Running real-time rank update...")

        // 更新实时排名类型
        realTimeRanks := []uint32{1, 2, 3} // 等级、战力、副本排名
        for _, rankType := range realTimeRanks {
            err := t.rankService.RecalculateRank(context.Background(), rankType)
            if err != nil {
                log.Printf("Failed to update rank type %d: %v", rankType, err)
            }
        }
    }
}

// dailyUpdate 每日排名更新
func (t *RankTask) dailyUpdate() {
    for {
        // 计算到凌晨的时间
        now := time.Now()
        next := time.Date(now.Year(), now.Month(), now.Day()+1, 0, 0, 0, 0, now.Location())
        duration := next.Sub(now)

        // 等待到凌晨
        time.Sleep(duration)

        log.Println("Running daily rank update...")

        // 更新每日排名类型
        dailyRanks := []uint32{4, 5} // 财富、成就排名
        for _, rankType := range dailyRanks {
            err := t.rankService.RecalculateRank(context.Background(), rankType)
            if err != nil {
                log.Printf("Failed to update rank type %d: %v", rankType, err)
                continue
            }

            // 保存排名历史
            err = t.rankService.SaveRankHistory(context.Background(), rankType)
            if err != nil {
                log.Printf("Failed to save rank history for type %d: %v", rankType, err)
            }

            // 检查排名奖励
            err = t.rankService.CheckRankReward(context.Background(), rankType)
            if err != nil {
                log.Printf("Failed to check rank reward for type %d: %v", rankType, err)
            }
        }
    }
}

// weeklyUpdate 每周排名更新
func (t *RankTask) weeklyUpdate() {
    for {
        // 计算到下周一凌晨的时间
        now := time.Now()
        daysUntilMonday := int(time.Monday - now.Weekday())
        if daysUntilMonday <= 0 {
            daysUntilMonday += 7
        }
        nextMonday := time.Date(now.Year(), now.Month(), now.Day()+daysUntilMonday, 0, 0, 0, 0, now.Location())
        duration := nextMonday.Sub(now)

        // 等待到下周一凌晨
        time.Sleep(duration)

        log.Println("Running weekly rank update...")

        // 更新每周排名类型
        weeklyRanks := []uint32{6, 7, 8} // 公会、PK、贡献排名
        for _, rankType := range weeklyRanks {
            err := t.rankService.RecalculateRank(context.Background(), rankType)
            if err != nil {
                log.Printf("Failed to update rank type %d: %v", rankType, err)
                continue
            }

            // 保存排名历史
            err = t.rankService.SaveRankHistory(context.Background(), rankType)
            if err != nil {
                log.Printf("Failed to save rank history for type %d: %v", rankType, err)
            }

            // 检查排名奖励
            err = t.rankService.CheckRankReward(context.Background(), rankType)
            if err != nil {
                log.Printf("Failed to check rank reward for type %d: %v", rankType, err)
            }
        }
    }
}
```

## 9. 集成到主服务器

### 9.1 服务器初始化

**文件位置**: `cmd/server/main.go`

```go
package main

import (
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/handlers"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/rank_service"
    "github.com/pixb/DnfGameServer/dnf-go-server/internal/game/task"
    "github.com/pixb/DnfGameServer/dnf-go-server/store"
)

func main() {
    // 初始化存储
    gameStore := store.NewStore(db, redisClient)

    // 初始化服务
    rankService := rank_service.NewRankService(gameStore)

    // 初始化处理器
    rankHandler := handlers.NewRankHandler(rankService)

    // 注册处理器
    server.RegisterHandler(dnfv1.MessageType_INQUIRE_PERSONAL_RANKING, rankHandler.InquirePersonalRankingHandler)
    server.RegisterHandler(dnfv1.MessageType_MY_RANKING, rankHandler.MyRankingHandler)
    server.RegisterHandler(dnfv1.MessageType_RANK_FRIEND, rankHandler.RankFriendHandler)
    server.RegisterHandler(dnfv1.MessageType_MY_PARTY_RANKING, rankHandler.MyPartyRankingHandler)
    server.RegisterHandler(dnfv1.MessageType_RANKING_LIST, rankHandler.RankingListHandler)

    // 启动排名任务
    rankTask := task.NewRankTask(rankService)
    rankTask.Start()

    // 启动服务器
    server.Start()
}
```

## 10. 注意事项

### 10.1 性能考虑

1. **大量数据时的性能优化**
   - 使用缓存减少数据库查询
   - 分页查询避免一次性加载过多数据
   - 批量操作减少数据库交互次数

2. **排名计算的性能优化**
   - 实时排名只更新变化的数据
   - 批量重新计算排名
   - 使用索引优化查询

3. **并发处理**
   - 排名更新使用异步处理
   - 避免排名计算时的锁竞争
   - 使用事务确保数据一致性

### 10.2 数据一致性

1. **排名数据的一致性**
   - 确保排名计算的准确性
   - 处理并发更新的情况
   - 定期验证排名数据

2. **历史数据的保存**
   - 定期保存排名历史
   - 避免历史数据过多导致存储问题
   - 提供历史数据的查询接口

### 10.3 安全性

1. **防止作弊**
   - 验证排名数据的合法性
   - 检测异常的排名变化
   - 记录排名相关的操作日志

2. **数据保护**
   - 对敏感的排名数据进行保护
   - 避免排名数据的泄露
   - 实现排名数据的访问控制

### 10.4 可扩展性

1. **新排名类型的添加**
   - 配置驱动的排名类型管理
   - 插件化的排名计算规则
   - 统一的排名接口

2. **排名算法的扩展**
   - 支持自定义排名算法
   - 提供排名算法的测试工具
   - 允许排名算法的动态切换

## 11. 总结

排名系统是游戏中的重要功能，通过Go语言的实现，可以获得更好的性能和可维护性。本方案详细设计了排名系统的各个组件，包括消息定义、数据模型、处理器实现、服务层逻辑和存储层操作，同时考虑了性能优化、数据一致性、安全性和可扩展性等方面。

通过本方案的实现，排名系统将能够：

1. 支持多种排名类型，满足游戏的各种排名需求
2. 提供实时、每日、每周的排名更新机制
3. 实现排名奖励的自动发放
4. 支持排名历史的记录和查询
5. 保证在大量玩家数据下的性能表现

排名系统的实现将为游戏增添更多的竞争性和可玩性，激发玩家的游戏热情，提高游戏的留存率。