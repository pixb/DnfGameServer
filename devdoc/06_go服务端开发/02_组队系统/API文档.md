# 组队系统API文档

## 1. 队伍管理API

### 1.1 创建队伍

- **接口路径**: `/api/party/create`
- **请求方法**: `POST`
- **功能描述**: 创建新队伍
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `playerId` | `uint64` | 是 | 创建者ID |
  | `name` | `string` | 是 | 队伍名称 |

- **请求示例**:
  ```json
  {
    "playerId": 123,
    "name": "测试队伍"
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "partyId": 1,
      "name": "测试队伍",
      "leaderId": 123,
      "memberCount": 1
    }
  }
  ```

### 1.2 邀请玩家

- **接口路径**: `/api/party/invite`
- **请求方法**: `POST`
- **功能描述**: 邀请玩家加入队伍
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `playerId` | `uint64` | 是 | 被邀请玩家ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "playerId": 456
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "invitationId": 1,
      "partyId": 1,
      "status": "PENDING"
    }
  }
  ```

### 1.3 接受邀请

- **接口路径**: `/api/party/accept`
- **请求方法**: `POST`
- **功能描述**: 接受队伍邀请
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `invitationId` | `uint64` | 是 | 邀请ID |
  | `playerId` | `uint64` | 是 | 玩家ID |

- **请求示例**:
  ```json
  {
    "invitationId": 1,
    "playerId": 456
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "partyId": 1,
      "status": "ACCEPTED"
    }
  }
  ```

### 1.4 拒绝邀请

- **接口路径**: `/api/party/reject`
- **请求方法**: `POST`
- **功能描述**: 拒绝队伍邀请
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `invitationId` | `uint64` | 是 | 邀请ID |
  | `playerId` | `uint64` | 是 | 玩家ID |

- **请求示例**:
  ```json
  {
    "invitationId": 1,
    "playerId": 456
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "REJECTED"
    }
  }
  ```

### 1.5 离开队伍

- **接口路径**: `/api/party/leave`
- **请求方法**: `POST`
- **功能描述**: 离开队伍
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `playerId` | `uint64` | 是 | 玩家ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "playerId": 456
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "SUCCESS"
    }
  }
  ```

### 1.6 踢出成员

- **接口路径**: `/api/party/kick`
- **请求方法**: `POST`
- **功能描述**: 队长踢出队伍成员
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `leaderId` | `uint64` | 是 | 队长ID |
  | `playerId` | `uint64` | 是 | 被踢出玩家ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "leaderId": 123,
    "playerId": 456
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "SUCCESS"
    }
  }
  ```

### 1.7 转让队长

- **接口路径**: `/api/party/transfer`
- **请求方法**: `POST`
- **功能描述**: 转让队长职位
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `currentLeaderId` | `uint64` | 是 | 当前队长ID |
  | `newLeaderId` | `uint64` | 是 | 新队长ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "currentLeaderId": 123,
    "newLeaderId": 456
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "SUCCESS"
    }
  }
  ```

### 1.8 获取队伍信息

- **接口路径**: `/api/party/info/:partyId`
- **请求方法**: `GET`
- **功能描述**: 获取队伍详细信息
- **路径参数**:
  | 参数名 | 类型 | 描述 |
  | :--- | :--- | :--- |
  | `partyId` | `uint64` | 队伍ID |

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "partyId": 1,
      "name": "测试队伍",
      "leaderId": 123,
      "members": [
        {
          "playerId": 123,
          "name": "玩家1",
          "level": 50,
          "isLeader": true
        },
        {
          "playerId": 456,
          "name": "玩家2",
          "level": 45,
          "isLeader": false
        }
      ],
      "memberCount": 2,
      "inDungeon": false
    }
  }
  ```

### 1.9 解散队伍

- **接口路径**: `/api/party/disband`
- **请求方法**: `POST`
- **功能描述**: 队长解散队伍
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `leaderId` | `uint64` | 是 | 队长ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "leaderId": 123
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "SUCCESS"
    }
  }
  ```

## 2. 匹配API

### 2.1 查找队伍

- **接口路径**: `/api/party/match/parties`
- **请求方法**: `GET`
- **功能描述**: 查找符合条件的队伍
- **查询参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `minLevel` | `int` | 否 | 最低等级 |
  | `maxLevel` | `int` | 否 | 最高等级 |
  | `dungeonId` | `int` | 否 | 副本ID |

- **请求示例**:
  ```
  /api/party/match/parties?minLevel=40&maxLevel=60&dungeonId=101
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": [
      {
        "partyId": 1,
        "name": "测试队伍",
        "leaderId": 123,
        "leaderName": "玩家1",
        "memberCount": 2,
        "maxMembers": 4
      }
    ]
  }
  ```

### 2.2 查找玩家

- **接口路径**: `/api/party/match/players`
- **请求方法**: `GET`
- **功能描述**: 查找符合条件的玩家
- **查询参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `minLevel` | `int` | 否 | 最低等级 |
  | `maxLevel` | `int` | 否 | 最高等级 |
  | `dungeonId` | `int` | 否 | 副本ID |

- **请求示例**:
  ```
  /api/party/match/players?minLevel=40&maxLevel=60&dungeonId=101
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": [
      {
        "playerId": 789,
        "name": "玩家3",
        "level": 50,
        "class": "Warrior"
      }
    ]
  }
  ```

### 2.3 推荐队伍

- **接口路径**: `/api/party/match/recommend`
- **请求方法**: `GET`
- **功能描述**: 推荐适合玩家的队伍
- **查询参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `playerId` | `uint64` | 是 | 玩家ID |

- **请求示例**:
  ```
  /api/party/match/recommend?playerId=789
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": [
      {
        "partyId": 1,
        "name": "测试队伍",
        "leaderId": 123,
        "leaderName": "玩家1",
        "memberCount": 2,
        "maxMembers": 4
      }
    ]
  }
  ```

## 3. 副本挑战API

### 3.1 进入副本

- **接口路径**: `/api/party/dungeon/enter`
- **请求方法**: `POST`
- **功能描述**: 队伍进入副本
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `dungeonId` | `int` | 是 | 副本ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "dungeonId": 101
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "instanceId": 1,
      "dungeonId": 101,
      "currentStage": 1,
      "completed": false
    }
  }
  ```

### 3.2 离开副本

- **接口路径**: `/api/party/dungeon/leave`
- **请求方法**: `POST`
- **功能描述**: 玩家离开副本
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `playerId` | `uint64` | 是 | 玩家ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "playerId": 123
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "SUCCESS"
    }
  }
  ```

### 3.3 完成副本

- **接口路径**: `/api/party/dungeon/complete`
- **请求方法**: `POST`
- **功能描述**: 队伍完成副本
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `dungeonId` | `int` | 是 | 副本ID |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "dungeonId": 101
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "instanceId": 1,
      "dungeonId": 101,
      "completed": true,
      "rewards": [
        {
          "itemId": 1001,
          "count": 1,
          "name": "测试装备"
        }
      ]
    }
  }
  ```

### 3.4 获取副本进度

- **接口路径**: `/api/party/dungeon/progress`
- **请求方法**: `GET`
- **功能描述**: 获取队伍副本进度
- **查询参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `dungeonId` | `int` | 是 | 副本ID |

- **请求示例**:
  ```
  /api/party/dungeon/progress?partyId=1&dungeonId=101
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "currentStage": 3,
      "completed": false
    }
  }
  ```

## 4. 聊天API

### 4.1 发送消息

- **接口路径**: `/api/party/chat/message`
- **请求方法**: `POST`
- **功能描述**: 发送队伍消息
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `partyId` | `uint64` | 是 | 队伍ID |
  | `playerId` | `uint64` | 是 | 玩家ID |
  | `message` | `string` | 是 | 消息内容 |

- **请求示例**:
  ```json
  {
    "partyId": 1,
    "playerId": 123,
    "message": "大家好"
  }
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "messageId": 1,
      "status": "SUCCESS"
    }
  }
  ```

### 4.2 获取聊天历史

- **接口路径**: `/api/party/chat/history/:partyId`
- **请求方法**: `GET`
- **功能描述**: 获取队伍聊天历史
- **路径参数**:
  | 参数名 | 类型 | 描述 |
  | :--- | :--- | :--- |
  | `partyId` | `uint64` | 队伍ID |

- **查询参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `limit` | `int` | 否 | 消息数量限制 |
  | `offset` | `int` | 否 | 偏移量 |

- **请求示例**:
  ```
  /api/party/chat/history/1?limit=20&offset=0
  ```

- **响应示例**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": [
      {
        "messageId": 1,
        "playerId": 123,
        "playerName": "玩家1",
        "message": "大家好",
        "sendTime": 1620000000
      }
    ]
  }
  ```

## 5. WebSocket API

### 5.1 连接WebSocket

- **接口路径**: `/api/party/ws`
- **请求方法**: `GET`
- **功能描述**: 建立WebSocket连接
- **查询参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | :--- | :--- | :--- | :--- |
  | `token` | `string` | 是 | 认证令牌 |
  | `playerId` | `uint64` | 是 | 玩家ID |

- **请求示例**:
  ```
  /api/party/ws?token=xxx&playerId=123
  ```

### 5.2 WebSocket消息类型

| 消息类型 | 描述 | 消息结构 |
| :--- | :--- | :--- |
| `party_chat` | 队伍聊天消息 | `{"type": "party_chat", "data": {"partyId": 1, "playerId": 123, "playerName": "玩家1", "message": "大家好", "sendTime": 1620000000}}` |
| `party_invitation` | 队伍邀请通知 | `{"type": "party_invitation", "data": {"invitationId": 1, "partyId": 1, "partyName": "测试队伍", "leaderId": 123, "leaderName": "玩家1", "sendTime": 1620000000}}` |
| `party_status` | 队伍状态更新 | `{"type": "party_status", "data": {"partyId": 1, "members": [...], "inDungeon": false}}` |
| `dungeon_progress` | 副本进度更新 | `{"type": "dungeon_progress", "data": {"partyId": 1, "dungeonId": 101, "currentStage": 2, "completed": false}}` |
| `error` | 错误消息 | `{"type": "error", "data": {"code": 400, "message": "错误信息"}}` |