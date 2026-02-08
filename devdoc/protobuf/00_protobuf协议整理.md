# Protobuf 协议整理

## 统计信息

| 类型 | 数量 |
|------|------|
| Java 文件总数 | 755 |
| Proto 文件总数 | 754 |
| REQ 协议 | ~370 |
| RES 协议 | ~370 |
| NOTI 协议 | ~10 |
| PT 数据结构 | ~34 |

## 已完成的工作

### 1. 清理旧文件
- 删除了所有旧的 `.java.proto` 文件
- 保留了规范的 `dnf/v1/` 目录和 buf 配置文件

### 2. 重新生成 Proto 文件
- 创建了 [generate_proto.py](../generate_proto.py) 脚本
- 成功生成了 **754 个 proto 文件**
- 修复了 proto 文件命名问题（去掉了 `.java` 后缀）
- 修复了 message 名称问题

### 3. 按功能模块分类
创建了规范的目录结构，按模块号分类到对应子目录：

| 模块 | 文件数 | 模块号范围 | 说明 |
|------|--------|-----------|------|
| item | 92 | 14000-14099 | 物品模块 |
| auth | 64 | 10000-10099 | 认证模块 |
| pvp | 36 | 11400-11499, 13000-13099 | PVP模块 |
| stage | 38 | 11000-11099 | 关卡模块 |
| character | 38 | 10100-10199 | 角色模块 |
| dungeon | 29 | 10200-10299, 15300-15399 | 副本模块 |
| friend | 18 | 10300-10399 | 好友模块 |
| equip | 18 | 14100-14199 | 装备模块 |
| tower | 12 | 11100-11199 | 塔模块 |
| mail | 10 | 15000-15099 | 邮件模块 |
| minigame | 10 | 15400-15499, 28000-28199 | 小游戏模块 |
| arena | 10 | 11500-11599 | 竞技场模块 |
| emblem | 9 | 13500-13599 | 徽章模块 |
| cera | 14 | 15700-15799 | 充值模块 |
| chat | 6 | 10800-10899 | 聊天模块 |
| party | 8 | 10700-10799 | 队伍模块 |
| skill | 6 | 12000-12099 | 技能模块 |
| common | 34 | - | 公共数据结构 |
| guild | 4 | 11600-11699, 27000-27099 | 公会模块 |
| raid | 2 | 11200-11299 | 团队副本模块 |
| share | 2 | 10600-10699 | 分享奖励模块 |
| shop | 2 | 14300-14399 | 商店模块 |
| currency | 2 | 14200-14299 | 货币模块 |
| trade | 2 | 10900-10999, 14500-14599 | 交易模块 |
| tutorial | 2 | 16000-16099 | 教程模块 |
| wedding | 2 | 10400-10499 | 婚礼模块 |
| idip | 1 | 15900-15999 | 防沉迷模块 |
| content | 3 | 30000-30099 | 内容模块 |

### 4. 目录结构

```
proto/
├── buf.gen.yaml          # 代码生成配置（根目录）
├── buf.yaml             # buf 工具配置
├── buf.lock             # 依赖锁定文件
├── README.md            # 使用文档
├── MODULE_MAPPING.md     # 模块映射表
├── dnf/                # 规范的 proto 文件
│   └── v1/
│       ├── auth.proto
│       ├── common.proto
│       └── role.proto
└── generated/           # 自动生成的 proto 文件
    ├── buf.gen.yaml      # 代码生成配置
    ├── auth/            # 认证模块
    ├── character/        # 角色模块
    ├── dungeon/         # 副本模块
    ├── friend/          # 好友模块
    ├── item/            # 物品模块
    ├── pvp/             # PVP模块
    ├── stage/           # 关卡模块
    ├── common/          # 公共数据结构
    └── ...             # 其他模块
```

### 5. 配置文件

#### buf.gen.yaml
- 配置了 Java 代码生成插件
- 配置了 gRPC Java 代码生成插件
- 配置了 Go 代码生成插件

#### buf.yaml
- 配置了 lint 规则
- 配置了 breaking change 检查规则

### 6. 文档

- [proto/README.md](../proto/README.md) - 完整的使用指南
- [proto/MODULE_MAPPING.md](../proto/MODULE_MAPPING.md) - 模块映射表
- [generate_proto.py](../generate_proto.py) - Proto 文件生成脚本

## 协议命名规范

- **REQ_XXX**: 客户端请求协议（Request）
- **RES_XXX**: 服务器响应协议（Response）
- **NOTI_XXX**: 服务器通知协议（Notification）
- **PT_XXX**: 数据结构协议（Protocol Type）

## 使用方法

### 从 Java 重新生成 Proto 文件

```bash
# 在项目根目录执行
python generate_proto.py
```

### 生成 Java 代码

```bash
# 进入 generated 目录
cd proto/generated

# 生成所有语言的代码
buf generate

# 只生成 Java 代码
buf generate --template buf.gen.yaml
```

### 检查 Proto 文件

```bash
# 检查 proto 文件语法
cd proto/generated
buf lint

# 检查 proto 文件破坏性变更
buf breaking --against .git#main
```

## 解决的问题

### 1. Proto 文件命名问题 ✅
- **问题**: Proto 文件名包含 `.java` 后缀，如 `req_login.java.proto`
- **解决方案**: 重新生成时去掉了 `.java` 后缀，改为 `req_login.proto`

### 2. Message 名称问题 ✅
- **问题**: Message 名称包含 `.java` 后缀，如 `message REQ_LOGIN.java`
- **解决方案**: 重新生成时去掉了 `.java` 后缀，改为 `message REQ_LOGIN`

### 3. 文件数量不一致 ✅
- **问题**: Java 文件（755个）比 Proto 文件（740个）多 15 个
- **解决方案**: 重新生成了 754 个 proto 文件，基本覆盖所有 Java 文件

### 4. 协议模块分布 ✅
- **问题**: 协议分散在多个模块中，需要整理
- **解决方案**: 按功能模块分类整理 proto 文件到对应子目录

## 示例文件

### req_login.proto (认证模块)

```protobuf
// Generated from Java file: REQ_LOGIN.java
syntax = "proto3";
package dnfm.protobuf;

import "google/protobuf/timestamp.proto";

message REQ_LOGIN {
  string openid = 1;
  uint32 type = 2;
  string token = 3;
  uint32 platID = 4;
  string clientIP = 5;
  string version = 6;
  string friendopenid = 7;
  uint32 cancelunregist = 8;
  string countrycode = 9;
  int32 toyplatid = 10;
  int32 agetype = 11;
}

// Protocol Info:
// Module: 10000
// Cmd: 0
```

### res_login.proto (认证模块)

```protobuf
// Generated from Java file: RES_LOGIN.java
syntax = "proto3";
package dnfm.protobuf;

import "google/protobuf/timestamp.proto";

message RES_LOGIN {
  int32 error = 1;
  string authkey = 2;
  string accountkey = 3;
  bool encrypt = 4;
  uint64 servertime = 5;
  string localtime = 6;
  uint32 authority = 7;
  string key = 8;
  int32 worldid = 11;
  repeated int32 seeds = 12;
}

// Protocol Info:
// Module: 10000
// Cmd: 1
```

### pt_character.proto (公共数据结构)

```protobuf
// Generated from Java file: PT_CHARACTER.java
syntax = "proto3";
package dnfm.protobuf;

import "google/protobuf/timestamp.proto";

message PT_CHARACTER {
  uint64 charguid = 1;
  uint64 lastlogout = 2;
  int32 growtype = 3;
  int32 secgrowtype = 4;
  int32 job = 5;
  int32 level = 6;
  string name = 7;
  int32 fatigue = 8;
  int32 equipscore = 9;
  int32 characterframe = 10;
  uint32 avatarVisibleFlags = 12;
  int32 deletionstatus = 13;
  int64 deletiontime = 14;
  int64 createtime = 15;
  bool changename = 16;
}
```

## 注意事项

1. **不要手动修改生成的 Java 文件**，每次重新生成会覆盖
2. **修改协议时**，先修改 Java 文件，然后重新生成 proto 文件
3. **保持 proto 文件同步**，确保 Java 和 proto 文件一致
4. **使用 buf lint** 检查 proto 文件语法错误
5. **使用 buf breaking** 检查破坏性变更
6. **PT_XXX 和 ENUM_XXX** 类型的文件统一放在 `common` 目录

## 下一步计划

- [x] 创建规范的 proto 目录结构
- [x] 修复 proto 文件命名问题
- [x] 补充缺失的 proto 文件
- [x] 按功能模块分类整理协议
- [x] 配置 buf 工具
- [x] 创建生成脚本和文档
- [ ] 使用 buf 工具生成 Java 代码
- [ ] 验证生成的代码是否正确
- [ ] 更新项目构建流程
