# 冒险系统API文档

## 1. 副本相关接口

### 1.1 获取副本列表
- **接口路径**：`GET /api/dungeon/list`
- **接口描述**：获取玩家可进入的副本列表
- **请求参数**：
  - `level`：可选，玩家等级，用于过滤适合等级的副本
  - `difficulty`：可选，副本难度，1-5
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "dungeons": [
        {
          "id": 1,
          "name": "哥布林王国",
          "level": 10,
          "difficulty": 1,
          "description": "初级副本，适合新手玩家",
          "entry_requirement": {
            "level": 8,
            "items": []
          }
        }
      ]
    }
  }
  ```

### 1.2 创建副本实例
- **接口路径**：`POST /api/dungeon/create`
- **接口描述**：创建副本实例
- **请求参数**：
  ```json
  {
    "dungeon_id": 1,
    "party_members": [10001, 10002]
  }
  ```
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "instance_id": 1001,
      "start_time": "2023-10-01T10:00:00Z",
      "expire_time": "2023-10-01T10:30:00Z"
    }
  }
  ```

### 1.3 进入副本
- **接口路径**：`POST /api/dungeon/enter`
- **接口描述**：玩家进入副本实例
- **请求参数**：
  ```json
  {
    "instance_id": 1001
  }
  ```
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "entered",
      "dungeon_info": {
        "id": 1,
        "name": "哥布林王国",
        "map_id": 101
      }
    }
  }
  ```

### 1.4 完成副本
- **接口路径**：`POST /api/dungeon/complete`
- **接口描述**：完成副本挑战
- **请求参数**：
  ```json
  {
    "instance_id": 1001,
    "success": true,
    "boss_killed": true
  }
  ```
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "completed",
      "rewards": [
        {
          "type": "item",
          "id": 1001,
          "name": "哥布林的战斧",
          "count": 1
        },
        {
          "type": "exp",
          "value": 1000
        }
      ]
    }
  }
  ```

### 1.5 获取副本奖励
- **接口路径**：`GET /api/dungeon/reward`
- **接口描述**：获取副本奖励
- **请求参数**：
  - `instance_id`：副本实例ID
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "rewards": [
        {
          "type": "item",
          "id": 1001,
          "name": "哥布林的战斧",
          "count": 1
        },
        {
          "type": "gold",
          "value": 500
        }
      ]
    }
  }
  ```

## 2. 任务相关接口

### 2.1 获取可接受任务列表
- **接口路径**：`GET /api/quest/available`
- **接口描述**：获取玩家可接受的任务列表
- **请求参数**：无
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "quests": [
        {
          "id": 1,
          "name": "消灭哥布林",
          "description": "消灭10只哥布林",
          "level_requirement": 8,
          "rewards": [
            {
              "type": "item",
              "id": 1002,
              "name": "新手装备箱",
              "count": 1
            }
          ]
        }
      ]
    }
  }
  ```

### 2.2 接受任务
- **接口路径**：`POST /api/quest/accept`
- **接口描述**：玩家接受任务
- **请求参数**：
  ```json
  {
    "quest_id": 1
  }
  ```
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "accepted",
      "quest_id": 1,
      "accept_time": "2023-10-01T10:00:00Z"
    }
  }
  ```

### 2.3 获取当前任务列表
- **接口路径**：`GET /api/quest/active`
- **接口描述**：获取玩家当前正在进行的任务列表
- **请求参数**：无
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "quests": [
        {
          "id": 1,
          "name": "消灭哥布林",
          "description": "消灭10只哥布林",
          "progress": {
            "monster_kill": {
              "id": 101,
              "current": 5,
              "total": 10
            }
          },
          "status": "in_progress"
        }
      ]
    }
  }
  ```

### 2.4 更新任务进度
- **接口路径**：`POST /api/quest/update`
- **接口描述**：更新任务进度
- **请求参数**：
  ```json
  {
    "quest_id": 1,
    "progress": {
      "monster_kill": {
        "id": 101,
        "count": 5
      }
    }
  }
  ```
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "updated",
      "quest_id": 1,
      "progress": {
        "monster_kill": {
          "id": 101,
          "current": 5,
          "total": 10
        }
      }
    }
  }
  ```

### 2.5 完成任务
- **接口路径**：`POST /api/quest/complete`
- **接口描述**：完成任务
- **请求参数**：
  ```json
  {
    "quest_id": 1
  }
  ```
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "completed",
      "quest_id": 1,
      "rewards": [
        {
          "type": "item",
          "id": 1002,
          "name": "新手装备箱",
          "count": 1
        },
        {
          "type": "exp",
          "value": 500
        }
      ]
    }
  }
  ```

## 3. 地图相关接口

### 3.1 获取地图列表
- **接口路径**：`GET /api/map/list`
- **接口描述**：获取地图列表
- **请求参数**：
  - `area_id`：可选，区域ID，用于过滤特定区域的地图
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "maps": [
        {
          "id": 101,
          "name": "艾尔文防线",
          "type": "town",
          "level": 1,
          "area_count": 5
        }
      ]
    }
  }
  ```

### 3.2 获取地图详情
- **接口路径**：`GET /api/map/detail`
- **接口描述**：获取地图详情
- **请求参数**：
  - `map_id`：地图ID
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "map": {
        "id": 101,
        "name": "艾尔文防线",
        "type": "town",
        "level": 1,
        "areas": [
          {
            "id": 1,
            "name": "城镇中心",
            "explored": true
          },
          {
            "id": 2,
            "name": "武器店",
            "explored": false
          }
        ]
      }
    }
  }
  ```

### 3.3 探索区域
- **接口路径**：`POST /api/map/explore`
- **接口描述**：探索地图区域
- **请求参数**：
  ```json
  {
    "map_id": 101,
    "area_id": 2
  }
  ```
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "status": "discovered",
      "area": {
        "id": 2,
        "name": "武器店",
        "description": "出售各种武器的商店"
      },
      "reward": {
        "type": "exp",
        "value": 100
      }
    }
  }
  ```

### 3.4 获取探索进度
- **接口路径**：`GET /api/map/exploration`
- **接口描述**：获取地图探索进度
- **请求参数**：
  - `map_id`：地图ID
- **响应格式**：
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "map_id": 101,
      "explored_areas": 2,
      "total_areas": 5,
      "progress": 40,
      "explored_area_ids": [1, 2]
    }
  }
  ```

## 4. 错误码定义

| 错误码 | 描述 |
|--------|------|
| 400 | 请求参数错误 |
| 401 | 未授权访问 |
| 403 | 权限不足 |
| 404 | 资源不存在 |
| 409 | 冲突（如副本已存在） |
| 500 | 服务器内部错误 |
| 503 | 服务不可用 |

## 5. 注意事项

1. 所有接口需要在请求头中携带有效的JWT令牌
2. 副本实例有时间限制，超过时间未完成将自动失败
3. 任务进度更新需要符合任务的完成条件
4. 地图探索进度会影响玩家的成就系统
5. 接口调用频率限制：每个玩家每分钟最多调用60次

## 6. 版本控制

- **API版本**：v1
- **最后更新时间**：2023-10-01
- **更新内容**：初始版本发布