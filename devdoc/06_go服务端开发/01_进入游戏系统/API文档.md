# 进入游戏系统API文档

## 1. 概述

进入游戏系统API提供了账号验证、角色管理、服务器选择等核心功能的接口。本文档详细描述了所有API接口的请求参数、响应格式、错误码等信息，为客户端开发和服务端测试提供参考。

### 1.1 API基础信息

- **API版本**: v1.0
- **基础URL**: `http://{server}:{port}/api`
- **请求格式**: JSON
- **响应格式**: JSON
- **认证方式**: JWT Token（Bearer Token）
- **Content-Type**: application/json

### 1.2 通用响应格式

```json
{
  "code": 200,            // 状态码，200表示成功，其他表示失败
  "message": "success",   // 响应消息
  "data": {}              // 响应数据，根据接口不同而不同
}
```

### 1.3 错误码说明

| 错误码 | 描述 | 解决方案 |
| :--- | :--- | :--- |
| 400 | 请求参数错误 | 检查请求参数是否符合要求 |
| 401 | 未授权，Token无效 | 重新登录获取有效Token |
| 403 | 禁止访问，权限不足 | 检查用户权限 |
| 404 | 资源不存在 | 检查请求的资源是否存在 |
| 405 | 请求方法错误 | 使用正确的HTTP方法 |
| 429 | 请求过于频繁 | 稍后再试 |
| 500 | 服务器内部错误 | 联系管理员 |
| 503 | 服务不可用 | 稍后再试 |

## 2. 认证相关API

### 2.1 登录

**接口描述**: 用户登录，获取认证Token

**请求URL**: `/api/login`

**请求方法**: POST

**请求参数**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| username | string | 是 | 用户名 |
| password | string | 是 | 密码 |
| clientInfo | object | 是 | 客户端信息 |
| deviceInfo | string | 否 | 设备信息 |
| ipAddress | string | 否 | IP地址 |

**clientInfo结构**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| version | string | 是 | 客户端版本 |
| deviceId | string | 否 | 设备ID |
| osVersion | string | 否 | 操作系统版本 |
| appVersion | string | 否 | 应用版本 |
| macAddress | string | 否 | MAC地址 |

**请求示例**:

```json
{
  "username": "test",
  "password": "123456",
  "clientInfo": {
    "version": "1.0.0",
    "deviceId": "device123",
    "osVersion": "Android 12",
    "appVersion": "1.0.0",
    "macAddress": "00:11:22:33:44:55"
  },
  "deviceInfo": "Samsung Galaxy S21",
  "ipAddress": "192.168.1.1"
}
```

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "expiresAt": 1620000000,
    "username": "test",
    "accountId": 1
  }
}
```

失败:

```json
{
  "code": 401,
  "message": "账号或密码错误",
  "data": null
}
```

### 2.2 登出

**接口描述**: 用户登出，使Token失效

**请求URL**: `/api/logout`

**请求方法**: POST

**请求参数**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| token | string | 是 | 认证Token |

**请求示例**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "status": "logged out"
  }
}
```

失败:

```json
{
  "code": 401,
  "message": "Token无效",
  "data": null
}
```

## 3. 服务器相关API

### 3.1 获取服务器列表

**接口描述**: 获取游戏服务器列表

**请求URL**: `/api/servers`

**请求方法**: GET

**请求参数**: 无（使用Authorization头传递Token）

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "servers": [
      {
        "serverId": 1,
        "serverName": "服务器1",
        "status": 1,          // 1: 在线, 2: 满员, 3: 维护, 4: 离线
        "onlineCount": 1234,
        "maxOnlineCount": 5000,
        "ipAddress": "192.168.1.100",
        "port": 8080
      },
      {
        "serverId": 2,
        "serverName": "服务器2",
        "status": 1,
        "onlineCount": 2345,
        "maxOnlineCount": 5000,
        "ipAddress": "192.168.1.101",
        "port": 8080
      }
    ]
  }
}
```

失败:

```json
{
  "code": 401,
  "message": "Token无效",
  "data": null
}
```

### 3.2 获取服务器状态

**接口描述**: 获取指定服务器的详细状态

**请求URL**: `/api/servers/{serverId}`

**请求方法**: GET

**请求参数**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| serverId | int | 是 | 服务器ID |

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "serverId": 1,
    "serverName": "服务器1",
    "status": 1,
    "onlineCount": 1234,
    "maxOnlineCount": 5000,
    "ipAddress": "192.168.1.100",
    "port": 8080,
    "version": "1.0.0",
    "startTime": "2023-01-01 00:00:00",
    "lastRestartTime": "2023-01-02 00:00:00"
  }
}
```

失败:

```json
{
  "code": 404,
  "message": "服务器不存在",
  "data": null
}
```

## 4. 角色相关API

### 4.1 获取角色列表

**接口描述**: 获取指定服务器的角色列表

**请求URL**: `/api/characters`

**请求方法**: GET

**请求参数**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| serverId | int | 是 | 服务器ID |

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "characters": [
      {
        "characterId": 1,
        "characterName": "角色1",
        "level": 10,
        "jobId": 1,
        "jobName": "战士",
        "jobLevel": 2,
        "factionId": 1,
        "factionName": "正义联盟",
        "exp": 1000,
        "gold": 5000,
        "strength": 50,
        "intelligence": 20,
        "vitality": 30,
        "spirit": 20,
        "fatigue": 100,
        "maxFatigue": 156,
        "lastLoginTime": "2023-01-01 12:00:00"
      },
      {
        "characterId": 2,
        "characterName": "角色2",
        "level": 5,
        "jobId": 2,
        "jobName": "法师",
        "jobLevel": 1,
        "factionId": 1,
        "factionName": "正义联盟",
        "exp": 500,
        "gold": 2000,
        "strength": 20,
        "intelligence": 50,
        "vitality": 20,
        "spirit": 30,
        "fatigue": 156,
        "maxFatigue": 156,
        "lastLoginTime": "2023-01-02 10:00:00"
      }
    ]
  }
}
```

失败:

```json
{
  "code": 404,
  "message": "服务器不存在",
  "data": null
}
```

### 4.2 创建角色

**接口描述**: 在指定服务器创建新角色

**请求URL**: `/api/characters`

**请求方法**: POST

**请求参数**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| serverId | int | 是 | 服务器ID |
| name | string | 是 | 角色名称（2-12个字符） |
| jobId | int | 是 | 职业ID |
| factionId | int | 是 | 阵营ID |

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**请求示例**:

```json
{
  "serverId": 1,
  "name": "新角色",
  "jobId": 1,
  "factionId": 1
}
```

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "character": {
      "characterId": 3,
      "characterName": "新角色",
      "level": 1,
      "jobId": 1,
      "jobName": "战士",
      "jobLevel": 1,
      "factionId": 1,
      "factionName": "正义联盟",
      "exp": 0,
      "gold": 1000,
      "strength": 10,
      "intelligence": 10,
      "vitality": 10,
      "spirit": 10,
      "fatigue": 156,
      "maxFatigue": 156,
      "createdAt": "2023-01-03 00:00:00"
    }
  }
}
```

失败:

```json
{
  "code": 400,
  "message": "角色名称已存在",
  "data": null
}
```

### 4.3 删除角色

**接口描述**: 删除指定角色

**请求URL**: `/api/characters/{characterId}`

**请求方法**: DELETE

**请求参数**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| characterId | int64 | 是 | 角色ID |
| verificationCode | string | 是 | 验证码 |

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**请求示例**:

```json
{
  "verificationCode": "123456"
}
```

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "status": "角色删除成功"
  }
}
```

失败:

```json
{
  "code": 400,
  "message": "验证码错误",
  "data": null
}
```

### 4.4 选择角色

**接口描述**: 选择角色进入游戏

**请求URL**: `/api/characters/{characterId}/select`

**请求方法**: POST

**请求参数**: 无

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "character": {
      "characterId": 1,
      "characterName": "角色1",
      "level": 10,
      "jobId": 1,
      "jobName": "战士",
      "jobLevel": 2,
      "factionId": 1,
      "factionName": "正义联盟",
      "exp": 1000,
      "gold": 5000,
      "strength": 50,
      "intelligence": 20,
      "vitality": 30,
      "spirit": 20,
      "fatigue": 100,
      "maxFatigue": 156
    },
    "initData": {
      "gameSettings": {
        "soundEnabled": true,
        "musicEnabled": true,
        "graphicsQuality": "high"
      },
      "inventory": [
        {
          "itemId": 1,
          "itemName": "新手剑",
          "count": 1,
          "position": 1
        }
      ],
      "skills": [
        {
          "skillId": 1,
          "skillName": "普通攻击",
          "level": 1,
          "cooldown": 0
        }
      ],
      "quests": [
        {
          "questId": 1,
          "questName": "新手任务",
          "status": "in_progress"
        }
      ],
      "buffs": [],
      "position": {
        "x": 100,
        "y": 100,
        "z": 0,
        "mapId": 1
      }
    },
    "loginRewards": [
      {
        "rewardId": 1,
        "name": "每日登录奖励",
        "type": 1,
        "value": 100,
        "day": 1
      }
    ]
  }
}
```

失败:

```json
{
  "code": 404,
  "message": "角色不存在",
  "data": null
}
```

## 5. 奖励相关API

### 5.1 获取登录奖励

**接口描述**: 获取用户的登录奖励列表

**请求URL**: `/api/rewards/login`

**请求方法**: GET

**请求参数**: 无

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "rewards": [
      {
        "rewardId": 1,
        "name": "每日登录奖励",
        "type": 1,          // 1: 金币, 2: 经验, 3: 物品
        "value": 100,
        "day": 1,
        "claimed": false    // 是否已领取
      },
      {
        "rewardId": 2,
        "name": "连续登录2天奖励",
        "type": 1,
        "value": 200,
        "day": 2,
        "claimed": false
      }
    ],
    "连续登录天数": 1
  }
}
```

失败:

```json
{
  "code": 401,
  "message": "Token无效",
  "data": null
}
```

### 5.2 领取登录奖励

**接口描述**: 领取指定的登录奖励

**请求URL**: `/api/rewards/login/{rewardId}/claim`

**请求方法**: POST

**请求参数**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| rewardId | int | 是 | 奖励ID |

**请求头**:

| 参数名 | 类型 | 必填 | 描述 |
| :--- | :--- | :--- | :--- |
| Authorization | string | 是 | Bearer {token} |

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "reward": {
      "rewardId": 1,
      "name": "每日登录奖励",
      "type": 1,
      "value": 100,
      "day": 1,
      "claimed": true
    },
    "status": "奖励领取成功"
  }
}
```

失败:

```json
{
  "code": 400,
  "message": "奖励已领取",
  "data": null
}
```

## 6. 健康检查API

### 6.1 系统健康检查

**接口描述**: 检查系统是否正常运行

**请求URL**: `/api/health`

**请求方法**: GET

**请求参数**: 无

**响应示例**:

成功:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "status": "ok",
    "timestamp": "2023-01-01 12:00:00",
    "services": {
      "database": "ok",
      "redis": "ok",
      "auth": "ok"
    }
  }
}
```

## 7. 安全注意事项

### 7.1 Token安全

- **Token有效期**: 默认24小时
- **Token存储**: 客户端应安全存储Token，避免明文存储
- **Token传输**: 所有API请求应使用HTTPS传输Token
- **Token刷新**: 当Token即将过期时，应使用刷新Token获取新Token

### 7.2 请求频率限制

- **登录接口**: 每IP每分钟最多5次请求
- **其他接口**: 每用户每分钟最多60次请求
- **超过限制**: 会返回429错误码，需要稍后再试

### 7.3 密码安全

- **密码传输**: 密码应使用HTTPS传输
- **密码验证**: 服务端应使用安全的哈希算法验证密码
- **密码强度**: 客户端应提示用户设置强密码

### 7.4 防作弊措施

- **客户端验证**: 服务端会验证客户端版本和完整性
- **行为检测**: 服务端会检测异常行为
- **IP限制**: 同一IP不能同时登录多个账号

## 8. 调用示例

### 8.1 使用cURL调用登录接口

```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "test",
    "password": "123456",
    "clientInfo": {
      "version": "1.0.0"
    }
  }'
```

### 8.2 使用cURL调用获取服务器列表接口

```bash
curl -X GET http://localhost:8080/api/servers \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### 8.3 使用cURL调用选择角色接口

```bash
curl -X POST http://localhost:8080/api/characters/1/select \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

## 9. 版本变更记录

| 版本 | 日期 | 变更内容 |
| :--- | :--- | :--- |
| v1.0 | 2023-01-01 | 初始版本，包含登录、服务器列表、角色管理等核心API |
| v1.1 | 2023-02-01 | 新增登录奖励API，优化角色选择流程 |
| v1.2 | 2023-03-01 | 增强安全性，添加防作弊验证 |