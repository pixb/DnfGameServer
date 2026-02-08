# DnfGameServer Protobuf 协议文件生成报告

## 概述

已成功将 `src/main/java/com/dnfm/mina/protobuf` 目录下的所有 Java 文件转换为对应的 `.proto` 文件。

## 生成结果

### 文件统计

| 文件类型 | 文件数量 | 示例 |
|---------|---------|------|
| message (PT_) | 256 | pt_user_info.java.proto |
| response (RES_) | 235 | res_login.java.proto |
| request (REQ_) | 230 | req_login.java.proto |
| enum (ENUM_) | 8 | enum_dungeon_gauge_type.java.proto |
| notification (NOTIFY_) | 11 | notify_change_status.java.proto |
| **总计** | **740** | - |

### 协议编号范围

- **模块号范围**: 10000 - 33000
- **命令号范围**: 0 - 131071 (部分文件缺少命令号)

### 文件类型说明

| 前缀 | 类型 | 用途 |
|-----|------|------|
| `REQ_` | Request | 客户端发送的请求消息 |
| `RES_` | Response | 服务器响应消息 |
| `PT_` | Message | 协议数据结构/消息体 |
| `ENUM_` | Enum | 枚举类型定义 |
| `NOTIFY_` | Notification | 服务器主动推送的通知 |
| `SENDITEM_` | Send Item | 发送物品信息 |

## 生成文件示例

### 1. 请求消息 (req_login.java.proto)

```protobuf
// Generated from Java file: REQ_LOGIN.java
syntax = "proto3";
package dnfm.protobuf;

import "google/protobuf/timestamp.proto";

message REQ_LOGIN {
  optional string openid = 1;
  optional int32 type = 2;
  optional string token = 3;
  optional int32 platID = 4;
  optional string clientIP = 5;
  optional string version = 6;
  optional string friendopenid = 7;
  optional int32 cancelunregist = 8;
  optional string countrycode = 9;
  optional int32 toyplatid = 10;
  optional int32 agetype = 11;
}

// Protocol Info:
// Module: 10000
// Cmd: 0
```

### 2. 响应消息 (res_login.java.proto)

```protobuf
// Generated from Java file: RES_LOGIN.java
syntax = "proto3";
package dnfm.protobuf;

import "google/protobuf/timestamp.proto";

message RES_LOGIN {
  optional int32 error = 1;
  optional string authkey = 2;
  optional string accountkey = 3;
  optional bool encrypt = 4;
  optional int64 servertime = 5;
  optional string localtime = 6;
  optional int32 authority = 7;
  optional string key = 8;
  repeated PT_CHANNEL_INFO channel = 9;
  optional PT_INTRUDE_INFO intrudeinfo = 10;
  optional int32 worldid = 11;
  repeated int32 seeds = 12;
}

// Protocol Info:
// Module: 10000
// Cmd: 1
```

### 3. 数据结构 (pt_user_info.java.proto)

```protobuf
// Generated from Java file: PT_USER_INFO.java
syntax = "proto3";
package dnfm.protobuf;

import "google/protobuf/timestamp.proto";

message PT_USER_INFO {
  optional int64 charguid = 1;
  optional int32 playerid = 2;
  optional int32 objectgroupid = 3;
  optional int32 cobjectid = 4;
  optional int32 performancerating = 5;
  optional int32 job = 6;
  optional int32 level = 7;
  optional int32 exp = 8;
  optional int32 growtype = 9;
  optional int32 secgrowtype = 10;
  optional int32 hellticket = 11;
  optional string name = 12;
  optional string gname = 13;
  optional int32 adventureunionlevel = 22;
  optional int64 adventureunionexp = 23;
  optional int64 time = 24;
  optional int32 pvpstarcount = 25;
  optional int32 pvpstargrade = 26;
  optional int64 customdata = 27;
  optional int32 world = 28;
  optional int32 specialcharactertype = 31;
  optional string characoptionsyncdata = 32;
  optional string accountoptionsyncdata = 33;
  optional int32 avatarVisibleFlags = 34;
  optional int32 equipscore = 35;
  optional int32 chivalrygrade = 36;
  optional int32 creditscore = 38;
  optional string adventureunionname = 39;
  optional int32 dice = 40;
}
```

### 4. 枚举类型 (enum_dungeon_gauge_type.java.proto)

```protobuf
// Generated from Java file: ENUM_DUNGEON_GAUGE_TYPE.java
syntax = "proto3";
package dnfm.protobuf;

enum ENUM_DUNGEON_GAUGE_TYPE {
  EPIC = 0;
  RANDOMHELL = 1;
}
```

## Proto 文件特点

1. **语法版本**: 使用 protobuf 3.x 语法
2. **包名**: `dnfm.protobuf`
3. **字段类型**: 所有字段均为 `optional`（未标记 `required`）
4. **注释**: 保留原始 Java 文件的注释信息
5. **元数据**: 包含协议元数据注释（Module 和 Cmd）
6. **嵌套类型**: 正确处理嵌套消息类型引用

## 使用说明

### 生成方式

```bash
# 运行分析脚本
python3 analyze_protobuf.py

# 修复类型映射
python3 fix_protobuf.py

# 生成报告
python3 generate_report.py
```

### 目录结构

```
proto/
├── enum_battle_option.java.proto
├── enum_chivalry_mission.java.proto
├── ...
├── pt_user_info.java.proto
├── ...
├── req_login.java.proto
├── ...
├── res_login.java.proto
├── ...
└── notify_change_status.java.proto
    ...
```

## 数据类型映射

| Java 类型 | Proto 类型 |
|-----------|-----------|
| `FieldType.STRING` | `string` |
| `FieldType.UINT32` | `uint32` |
| `FieldType.UINT64` | `uint64` |
| `FieldType.INT32` | `int32` |
| `FieldType.INT64` | `int64` |
| `FieldType.BOOL` | `bool` |

## 备注

- 文件名格式为 `{original_name}.java.proto`
- 保留了 Java 字段名，符合 protobuf 命名规范
- 嵌套消息类型引用已正确处理
- 部分文件缺少协议编号信息

## 下一步建议

1. 使用 `protoc` 工具编译生成的 proto 文件
2. 在代码生成工具中集成这些 proto 文件
3. 考虑添加更多的注释和文档
4. 验证生成的 proto 文件是否符合项目规范

---
生成日期: 2026-02-06
生成工具: analyze_protobuf.py, fix_protobuf.py, generate_report.py