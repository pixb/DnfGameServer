# 组队系统Java代码分析

## 1. 核心类与接口

### 1.1 队伍管理相关类

- **PartyController**：负责队伍相关的请求处理
  - 核心方法：`createParty()`, `inviteToParty()`, `kickFromParty()`, `transferLeader()`, `leaveParty()`
  - 依赖：`PartyService`, `PlayerService`

- **PartyService**：实现队伍管理的核心业务逻辑
  - 核心方法：`createParty(Player leader)`, `invitePlayer(long partyId, long playerId)`, `removePlayer(long partyId, long playerId)`, `changeLeader(long partyId, long newLeaderId)`
  - 依赖：`PartyRepository`, `PlayerRepository`

- **PartyRepository**：队伍数据访问层
  - 核心方法：`save(Party party)`, `findById(long id)`, `findByPlayerId(long playerId)`, `deleteById(long id)`

### 1.2 匹配相关类

- **PartyMatcher**：负责队伍匹配
  - 核心方法：`findParties(int minLevel, int maxLevel, int dungeonId)`, `findPlayers(int minLevel, int maxLevel, int dungeonId)`
  - 依赖：`PartyRepository`, `PlayerRepository`

- **MatchService**：实现匹配算法
  - 核心方法：`matchPlayers(List<Player> players)`, `matchParties(List<Party> parties)`

### 1.3 副本挑战相关类

- **DungeonPartyController**：处理组队副本相关请求
  - 核心方法：`enterDungeon()`, `leaveDungeon()`, `resetDungeon()`
  - 依赖：`DungeonPartyService`

- **DungeonPartyService**：实现组队副本挑战逻辑
  - 核心方法：`enterDungeon(long partyId, int dungeonId)`, `leaveDungeon(long partyId, long playerId)`, `completeDungeon(long partyId, int dungeonId)`
  - 依赖：`PartyService`, `DungeonService`

### 1.4 聊天相关类

- **PartyChatController**：处理队伍聊天请求
  - 核心方法：`sendMessage()`, `getChatHistory()`
  - 依赖：`PartyChatService`

- **PartyChatService**：实现队伍聊天逻辑
  - 核心方法：`sendMessage(long partyId, long playerId, String message)`, `getChatHistory(long partyId, int limit)`
  - 依赖：`PartyChatRepository`

## 2. 关键方法与实现

### 2.1 队伍创建

```java
public Party createParty(Player leader) {
    // 创建队伍对象
    Party party = new Party();
    party.setLeaderId(leader.getId());
    party.setName(leader.getName() + "的队伍");
    party.setCreateTime(new Date());
    
    // 保存队伍
    party = partyRepository.save(party);
    
    // 添加队长到队伍
    PartyMember member = new PartyMember();
    member.setPartyId(party.getId());
    member.setPlayerId(leader.getId());
    member.setIsLeader(true);
    member.setJoinTime(new Date());
    partyMemberRepository.save(member);
    
    // 更新玩家的队伍信息
    leader.setPartyId(party.getId());
    playerRepository.save(leader);
    
    return party;
}
```

### 2.2 队伍邀请

```java
public boolean invitePlayer(long partyId, long playerId) {
    // 检查队伍是否存在
    Party party = partyRepository.findById(partyId).orElse(null);
    if (party == null) {
        return false;
    }
    
    // 检查玩家是否存在
    Player player = playerRepository.findById(playerId).orElse(null);
    if (player == null) {
        return false;
    }
    
    // 检查玩家是否已在队伍中
    if (player.getPartyId() != null) {
        return false;
    }
    
    // 创建邀请
    PartyInvitation invitation = new PartyInvitation();
    invitation.setPartyId(partyId);
    invitation.setPlayerId(playerId);
    invitation.setSendTime(new Date());
    invitation.setStatus(InvitationStatus.PENDING);
    partyInvitationRepository.save(invitation);
    
    // 发送邀请通知
    notificationService.sendPartyInvitation(playerId, partyId, party.getName());
    
    return true;
}
```

### 2.3 队伍匹配

```java
public List<Party> findParties(int minLevel, int maxLevel, int dungeonId) {
    // 查询符合条件的队伍
    return partyRepository.findAvailableParties(minLevel, maxLevel, dungeonId);
}

public List<Player> findPlayers(int minLevel, int maxLevel, int dungeonId) {
    // 查询符合条件的玩家
    return playerRepository.findAvailablePlayers(minLevel, maxLevel, dungeonId);
}
```

### 2.4 副本进入

```java
public boolean enterDungeon(long partyId, int dungeonId) {
    // 检查队伍是否存在
    Party party = partyRepository.findById(partyId).orElse(null);
    if (party == null) {
        return false;
    }
    
    // 获取队伍成员
    List<PartyMember> members = partyMemberRepository.findByPartyId(partyId);
    if (members.isEmpty()) {
        return false;
    }
    
    // 检查所有成员是否满足副本要求
    for (PartyMember member : members) {
        Player player = playerRepository.findById(member.getPlayerId()).orElse(null);
        if (player == null) {
            continue;
        }
        
        // 检查等级
        if (player.getLevel() < dungeonService.getMinLevel(dungeonId)) {
            return false;
        }
        
        // 检查入场次数
        if (!dungeonService.hasEntranceCount(player.getId(), dungeonId)) {
            return false;
        }
    }
    
    // 扣除所有成员的入场次数
    for (PartyMember member : members) {
        dungeonService.decreaseEntranceCount(member.getPlayerId(), dungeonId);
    }
    
    // 创建副本实例
    DungeonInstance instance = dungeonService.createInstance(dungeonId);
    
    // 更新队伍的副本信息
    party.setCurrentDungeonId(dungeonId);
    party.setDungeonInstanceId(instance.getId());
    party.setInDungeon(true);
    partyRepository.save(party);
    
    return true;
}
```

### 2.5 队伍聊天

```java
public void sendMessage(long partyId, long playerId, String message) {
    // 检查玩家是否在队伍中
    PartyMember member = partyMemberRepository.findByPartyIdAndPlayerId(partyId, playerId);
    if (member == null) {
        return;
    }
    
    // 创建聊天消息
    PartyChatMessage chatMessage = new PartyChatMessage();
    chatMessage.setPartyId(partyId);
    chatMessage.setPlayerId(playerId);
    chatMessage.setMessage(message);
    chatMessage.setSendTime(new Date());
    partyChatRepository.save(chatMessage);
    
    // 广播消息给队伍所有成员
    List<PartyMember> members = partyMemberRepository.findByPartyId(partyId);
    for (PartyMember m : members) {
        if (m.getPlayerId() != playerId) {
            notificationService.sendPartyChatMessage(m.getPlayerId(), partyId, playerId, message);
        }
    }
}
```

## 3. 数据结构

### 3.1 队伍

```java
public class Party {
    private long id;
    private long leaderId;
    private String name;
    private int memberCount;
    private boolean inDungeon;
    private Integer currentDungeonId;
    private Long dungeonInstanceId;
    private Date createTime;
    private Date updateTime;
    
    // getters and setters
}
```

### 3.2 队伍成员

```java
public class PartyMember {
    private long id;
    private long partyId;
    private long playerId;
    private boolean isLeader;
    private Date joinTime;
    private Date updateTime;
    
    // getters and setters
}
```

### 3.3 队伍邀请

```java
public class PartyInvitation {
    private long id;
    private long partyId;
    private long playerId;
    private InvitationStatus status;
    private Date sendTime;
    private Date respondTime;
    
    // getters and setters
}

public enum InvitationStatus {
    PENDING,
    ACCEPTED,
    REJECTED
}
```

### 3.4 队伍聊天消息

```java
public class PartyChatMessage {
    private long id;
    private long partyId;
    private long playerId;
    private String message;
    private Date sendTime;
    
    // getters and setters
}
```

### 3.5 队伍副本进度

```java
public class PartyDungeonProgress {
    private long id;
    private long partyId;
    private int dungeonId;
    private int currentStage;
    private boolean completed;
    private Date enterTime;
    private Date completeTime;
    
    // getters and setters
}
```

## 4. 数据库设计

### 4.1 队伍表 (`t_party`)

| 字段名 | 数据类型 | 约束 | 描述 |
|--------|----------|------|------|
| id | BIGINT | PRIMARY KEY | 队伍ID |
| leader_id | BIGINT | NOT NULL | 队长ID |
| name | VARCHAR(50) | NOT NULL | 队伍名称 |
| member_count | INT | NOT NULL DEFAULT 1 | 成员数量 |
| in_dungeon | BOOLEAN | NOT NULL DEFAULT FALSE | 是否在副本中 |
| current_dungeon_id | INT | NULL | 当前副本ID |
| dungeon_instance_id | BIGINT | NULL | 副本实例ID |
| create_time | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

### 4.2 队伍成员表 (`t_party_member`)

| 字段名 | 数据类型 | 约束 | 描述 |
|--------|----------|------|------|
| id | BIGINT | PRIMARY KEY | 成员ID |
| party_id | BIGINT | NOT NULL | 队伍ID |
| player_id | BIGINT | NOT NULL | 玩家ID |
| is_leader | BOOLEAN | NOT NULL DEFAULT FALSE | 是否为队长 |
| join_time | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 加入时间 |
| update_time | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

### 4.3 队伍邀请表 (`t_party_invitation`)

| 字段名 | 数据类型 | 约束 | 描述 |
|--------|----------|------|------|
| id | BIGINT | PRIMARY KEY | 邀请ID |
| party_id | BIGINT | NOT NULL | 队伍ID |
| player_id | BIGINT | NOT NULL | 被邀请玩家ID |
| status | VARCHAR(20) | NOT NULL DEFAULT 'PENDING' | 邀请状态 |
| send_time | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 发送时间 |
| respond_time | DATETIME | NULL | 响应时间 |

### 4.4 队伍聊天消息表 (`t_party_chat`)

| 字段名 | 数据类型 | 约束 | 描述 |
|--------|----------|------|------|
| id | BIGINT | PRIMARY KEY | 消息ID |
| party_id | BIGINT | NOT NULL | 队伍ID |
| player_id | BIGINT | NOT NULL | 发送者ID |
| message | VARCHAR(500) | NOT NULL | 消息内容 |
| send_time | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 发送时间 |

### 4.5 队伍副本进度表 (`t_party_dungeon_progress`)

| 字段名 | 数据类型 | 约束 | 描述 |
|--------|----------|------|------|
| id | BIGINT | PRIMARY KEY | 进度ID |
| party_id | BIGINT | NOT NULL | 队伍ID |
| dungeon_id | INT | NOT NULL | 副本ID |
| current_stage | INT | NOT NULL DEFAULT 1 | 当前阶段 |
| completed | BOOLEAN | NOT NULL DEFAULT FALSE | 是否完成 |
| enter_time | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 进入时间 |
| complete_time | DATETIME | NULL | 完成时间 |

## 5. 代码优化建议

1. **队伍管理优化**：
   - 增加队伍缓存，减少数据库查询
   - 优化队伍成员管理，使用集合存储成员信息

2. **匹配算法优化**：
   - 引入更智能的匹配算法，考虑玩家的战斗力、职业等因素
   - 增加匹配池，提高匹配效率

3. **副本挑战优化**：
   - 实现副本进度的实时同步
   - 优化副本实例的创建和管理

4. **聊天系统优化**：
   - 使用消息队列处理聊天消息
   - 实现聊天消息的持久化和清理策略

5. **并发处理优化**：
   - 增加并发控制，避免队伍操作的竞态条件
   - 使用分布式锁处理跨服务的队伍操作

6. **代码结构优化**：
   - 提取重复代码到公共方法
   - 优化异常处理，提供更清晰的错误信息

## 6. 总结

Java版本的组队系统实现了完整的组队功能，包括队伍创建、成员管理、副本挑战、队伍聊天等核心功能。但在性能优化、代码结构和可扩展性方面仍有改进空间。Go语言实现时，可以借鉴其核心业务逻辑，同时利用Go的并发特性和性能优势，构建更高效、可扩展的组队系统。