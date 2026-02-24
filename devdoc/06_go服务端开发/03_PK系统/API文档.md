# PK系统API文档

## 1. 概述

本文档描述了PK系统的API接口，包括接口路径、方法、参数、返回值等详细信息。这些接口用于处理玩家的PK相关操作，如进入PK场、战斗操作、获取PK记录和排行榜等。

## 2. 接口列表

| 接口路径 | 方法 | 模块 | 功能描述 | 请求体 (JSON) | 成功响应 (200 OK) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `/api/pk/queue` | `POST` | 匹配模块 | 申请进入PK场 | `{"playerId": 123, "level": 50, "matchValue": 1000}` | `{"queueId": "q123", "position": 5, "status": "queued"}` |
| `/api/pk/queue/:playerId` | `DELETE` | 匹配模块 | 取消PK排队 | N/A | `{"success": true}` |
| `/api/pk/battle/start` | `POST` | 战斗模块 | 开始PK战斗 | `{"player1Id": 123, "player2Id": 456}` | `{"battleId": "b789", "startTime": 1620000000}` |
| `/api/pk/battle/action` | `POST` | 战斗模块 | 处理战斗操作 | `{"battleId": "b789", "playerId": 123, "action": {"type": "attack", "skillId": 1}}` | `{"result": {"damage": 100, "combo": 5}}` |
| `/api/pk/battle/end` | `POST` | 战斗模块 | 结束PK战斗 | `{"battleId": "b789", "winnerId": 123, "loserId": 456, "duration": 120, "winnerCombo": 10, "loserCombo": 5}` | `{"winnerId": 123, "loserId": 456, "winnerHonorChange": 15, "loserHonorChange": -7, "winnerNewHonor": 1015, "loserNewHonor": 993, "winnerLevel": 2, "loserLevel": 2}` |
| `/api/pk/ranking` | `GET` | 排行榜模块 | 获取排行榜 | N/A | `[{"ranking": 1, "playerId": 123, "playerName": "Player1", "honor": 2000, "honorLevel": 5, "wins": 100, "losses": 10, "winRate": 0.91}]` |
| `/api/pk/ranking/:playerId` | `GET` | 排行榜模块 | 获取玩家排名 | N/A | `{"ranking": 5, "honor": 1000, "level": 4}` |
| `/api/pk/record/:playerId` | `GET` | 记录模块 | 获取玩家PK记录 | N/A | `[{"id": 1, "opponentId": 456, "opponentName": "Player2", "result": "WIN", "honorChange": 15, "battleTime": "2023-01-01T12:00:00Z"}]` |
| `/api/pk/info/:playerId` | `GET` | 信息模块 | 获取玩家PK信息 | N/A | `{"playerId": 123, "honor": 1000, "honorLevel": 4, "totalBattles": 50, "wins": 30, "losses": 15, "draws": 5, "winRate": 0.6}` |

## 3. 接口详情

### 3.1 申请进入PK场

- **接口路径**: `/api/pk/queue`
- **方法**: `POST`
- **功能描述**: 玩家申请进入PK场，系统将玩家添加到匹配队列中
- **请求参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `playerId` | `uint64` | 是 | 玩家角色ID | `123` |
| `level` | `int` | 是 | 玩家等级 | `50` |
| `matchValue` | `int` | 是 | 匹配值（基于战斗力等因素） | `1000` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "queueId": "q123",
    "position": 5,
    "status": "queued"
  }
}
```

- **失败响应**:

```json
{
  "code": 400,
  "message": "参数错误",
  "data": null
}
```

### 3.2 取消PK排队

- **接口路径**: `/api/pk/queue/:playerId`
- **方法**: `DELETE`
- **功能描述**: 玩家取消PK排队，系统将玩家从匹配队列中移除
- **路径参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `playerId` | `uint64` | 是 | 玩家角色ID | `123` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "success": true
  }
}
```

- **失败响应**:

```json
{
  "code": 404,
  "message": "玩家不在排队队列中",
  "data": null
}
```

### 3.3 开始PK战斗

- **接口路径**: `/api/pk/battle/start`
- **方法**: `POST`
- **功能描述**: 系统为匹配成功的玩家创建PK战斗
- **请求参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `player1Id` | `uint64` | 是 | 玩家1角色ID | `123` |
| `player2Id` | `uint64` | 是 | 玩家2角色ID | `456` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "battleId": "b789",
    "startTime": 1620000000
  }
}
```

- **失败响应**:

```json
{
  "code": 400,
  "message": "参数错误",
  "data": null
}
```

### 3.4 处理战斗操作

- **接口路径**: `/api/pk/battle/action`
- **方法**: `POST`
- **功能描述**: 处理玩家在PK战斗中的操作，如攻击、技能释放等
- **请求参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `battleId` | `string` | 是 | 战斗ID | `b789` |
| `playerId` | `uint64` | 是 | 玩家角色ID | `123` |
| `action` | `object` | 是 | 战斗操作 | `{"type": "attack", "skillId": 1}` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "result": {
      "damage": 100,
      "combo": 5
    }
  }
}
```

- **失败响应**:

```json
{
  "code": 404,
  "message": "战斗不存在",
  "data": null
}
```

### 3.5 结束PK战斗

- **接口路径**: `/api/pk/battle/end`
- **方法**: `POST`
- **功能描述**: 结束PK战斗，计算战斗结果和荣誉值变化
- **请求参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `battleId` | `string` | 是 | 战斗ID | `b789` |
| `winnerId` | `uint64` | 是 | 胜利者角色ID | `123` |
| `loserId` | `uint64` | 是 | 失败者角色ID | `456` |
| `duration` | `int` | 是 | 战斗持续时间（秒） | `120` |
| `winnerCombo` | `int` | 是 | 胜利者最大连击数 | `10` |
| `loserCombo` | `int` | 是 | 失败者最大连击数 | `5` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "winnerId": 123,
    "loserId": 456,
    "winnerHonorChange": 15,
    "loserHonorChange": -7,
    "winnerNewHonor": 1015,
    "loserNewHonor": 993,
    "winnerLevel": 2,
    "loserLevel": 2
  }
}
```

- **失败响应**:

```json
{
  "code": 400,
  "message": "参数错误",
  "data": null
}
```

### 3.6 获取排行榜

- **接口路径**: `/api/pk/ranking`
- **方法**: `GET`
- **功能描述**: 获取PK排行榜前N名玩家
- **查询参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `limit` | `int` | 否 | 返回数量限制，默认10 | `20` |
| `page` | `int` | 否 | 页码，默认1 | `1` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "ranking": 1,
      "playerId": 123,
      "playerName": "Player1",
      "honor": 2000,
      "honorLevel": 5,
      "wins": 100,
      "losses": 10,
      "winRate": 0.91
    },
    {
      "ranking": 2,
      "playerId": 456,
      "playerName": "Player2",
      "honor": 1800,
      "honorLevel": 5,
      "wins": 90,
      "losses": 15,
      "winRate": 0.86
    }
  ]
}
```

- **失败响应**:

```json
{
  "code": 500,
  "message": "服务器内部错误",
  "data": null
}
```

### 3.7 获取玩家排名

- **接口路径**: `/api/pk/ranking/:playerId`
- **方法**: `GET`
- **功能描述**: 获取指定玩家的PK排名和相关信息
- **路径参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `playerId` | `uint64` | 是 | 玩家角色ID | `123` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "ranking": 5,
    "honor": 1000,
    "level": 4
  }
}
```

- **失败响应**:

```json
{
  "code": 404,
  "message": "玩家不存在",
  "data": null
}
```

### 3.8 获取玩家PK记录

- **接口路径**: `/api/pk/record/:playerId`
- **方法**: `GET`
- **功能描述**: 获取指定玩家的PK战斗记录
- **路径参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `playerId` | `uint64` | 是 | 玩家角色ID | `123` |

- **查询参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `limit` | `int` | 否 | 返回数量限制，默认20 | `50` |
| `page` | `int` | 否 | 页码，默认1 | `1` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "opponentId": 456,
      "opponentName": "Player2",
      "result": "WIN",
      "honorChange": 15,
      "battleTime": "2023-01-01T12:00:00Z"
    },
    {
      "id": 2,
      "opponentId": 789,
      "opponentName": "Player3",
      "result": "LOSE",
      "honorChange": -7,
      "battleTime": "2023-01-01T11:30:00Z"
    }
  ]
}
```

- **失败响应**:

```json
{
  "code": 404,
  "message": "玩家不存在",
  "data": null
}
```

### 3.9 获取玩家PK信息

- **接口路径**: `/api/pk/info/:playerId`
- **方法**: `GET`
- **功能描述**: 获取指定玩家的PK相关信息，如荣誉值、战斗场次等
- **路径参数**:

| 参数名 | 类型 | 必填 | 描述 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `playerId` | `uint64` | 是 | 玩家角色ID | `123` |

- **成功响应**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "playerId": 123,
    "honor": 1000,
    "honorLevel": 4,
    "totalBattles": 50,
    "wins": 30,
    "losses": 15,
    "draws": 5,
    "winRate": 0.6
  }
}
```

- **失败响应**:

```json
{
  "code": 404,
  "message": "玩家不存在",
  "data": null
}
```

## 4. 错误码说明

| 错误码 | 描述 | 处理建议 |
| :--- | :--- | :--- |
| 200 | 成功 | 操作成功 |
| 400 | 参数错误 | 检查请求参数是否正确 |
| 401 | 未授权 | 请先登录 |
| 403 | 禁止访问 | 无权限执行此操作 |
| 404 | 资源不存在 | 检查请求的资源是否存在 |
| 409 | 冲突 | 操作与现有状态冲突 |
| 429 | 请求过于频繁 | 请稍后再试 |
| 500 | 服务器内部错误 | 服务器故障，请稍后再试 |
| 503 | 服务不可用 | 服务暂时不可用，请稍后再试 |

## 5. 接口调用示例

### 5.1 申请进入PK场

**请求示例**:

```bash
curl -X POST http://localhost:8080/api/pk/queue \
  -H "Content-Type: application/json" \
  -d '{"playerId": 123, "level": 50, "matchValue": 1000}'
```

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "queueId": "q123",
    "position": 5,
    "status": "queued"
  }
}
```

### 5.2 结束PK战斗

**请求示例**:

```bash
curl -X POST http://localhost:8080/api/pk/battle/end \
  -H "Content-Type: application/json" \
  -d '{"battleId": "b789", "winnerId": 123, "loserId": 456, "duration": 120, "winnerCombo": 10, "loserCombo": 5}'
```

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "winnerId": 123,
    "loserId": 456,
    "winnerHonorChange": 15,
    "loserHonorChange": -7,
    "winnerNewHonor": 1015,
    "loserNewHonor": 993,
    "winnerLevel": 2,
    "loserLevel": 2
  }
}
```

### 5.3 获取排行榜

**请求示例**:

```bash
curl -X GET "http://localhost:8080/api/pk/ranking?limit=10&page=1"
```

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "ranking": 1,
      "playerId": 123,
      "playerName": "Player1",
      "honor": 2000,
      "honorLevel": 5,
      "wins": 100,
      "losses": 10,
      "winRate": 0.91
    },
    {
      "ranking": 2,
      "playerId": 456,
      "playerName": "Player2",
      "honor": 1800,
      "honorLevel": 5,
      "wins": 90,
      "losses": 15,
      "winRate": 0.86
    }
  ]
}
```