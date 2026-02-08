# Proto目录规划文档

## 一、目录结构

```
proto/
├── buf.gen.yaml                    # 代码生成配置
├── buf.yaml                        # Lint和Breaking Change规则
├── MODULE_MAPPING.md               # 模块号映射表
├── dnf/                            # DNF游戏协议包
│   └── v1/                         # API版本v1
│       ├── common.proto            # 通用类型和枚举
│       ├── auth.proto              # 认证模块 (10000-10099)
│       ├── character.proto         # 角色模块 (10100-10199)
│       ├── dungeon.proto           # 副本模块 (10200-10299)
│       ├── friend.proto            # 好友模块 (10300-10399)
│       ├── party.proto             # 队伍模块 (10700-10799)
│       ├── chat.proto              # 聊天模块 (10800-10899)
│       ├── guild.proto             # 公会模块 (11600-11699)
│       ├── quest.proto             # 任务模块 (11900-11999)
│       ├── skill.proto             # 技能模块 (12000-12099)
│       ├── item.proto              # 物品模块 (14000-14099)
│       ├── equip.proto             # 装备模块 (14100-14199)
│       ├── shop.proto              # 商店模块 (14300-14399)
│       ├── mail.proto              # 邮件模块 (15000-15099)
│       ├── battle.proto            # 战斗模块 (18000-18099)
│       └── ...                     # 其他模块
└── gen/                            # 生成的代码目录（自动生成）
    ├── java/                       # Java代码
    │   └── dnf/v1/
    │       ├── CommonProto.java
    │       ├── AuthProto.java
    │       ├── CharacterProto.java
    │       └── ...
    ├── go/                         # Go代码（可选）
    │   └── dnf/v1/
    │       ├── common.pb.go
    │       ├── auth.pb.go
    │       └── ...
    └── ts/                         # TypeScript代码（可选）
        └── dnf/v1/
            ├── common_pb.ts
            ├── auth_pb.ts
            └── ...
```

## 二、文件命名规范

### 2.1 Proto文件命名

| 模块 | 文件名 | 模块号范围 |
|------|--------|-----------|
| 通用类型 | `common.proto` | - |
| 认证 | `auth.proto` | 10000-10099 |
| 角色 | `character.proto` | 10100-10199 |
| 副本 | `dungeon.proto` | 10200-10299 |
| 好友 | `friend.proto` | 10300-10399 |
| 队伍 | `party.proto` | 10700-10799 |
| 聊天 | `chat.proto` | 10800-10899 |
| 公会 | `guild.proto` | 11600-11699 |
| 任务 | `quest.proto` | 11900-11999 |
| 技能 | `skill.proto` | 12000-12099 |
| 物品 | `item.proto` | 14000-14099 |
| 装备 | `equip.proto` | 14100-14199 |
| 商店 | `shop.proto` | 14300-14399 |
| 邮件 | `mail.proto` | 15000-15099 |
| 战斗 | `battle.proto` | 18000-18099 |

### 2.2 消息命名规范

**请求消息：** `{Action}Request`
**响应消息：** `{Action}Response`
**通知消息：** `{Action}Notification`
**数据结构：** `{Name}`（直接使用名称）

**示例：**
```protobuf
// 请求
message LoginRequest { ... }
message CreateCharacterRequest { ... }

// 响应
message LoginResponse { ... }
message CreateCharacterResponse { ... }

// 通知
message CharacterEnterNotification { ... }

// 数据结构
message Character { ... }
message Item { ... }
```

### 2.3 字段命名规范

使用**snake_case**命名：
```protobuf
message Character {
  int64 char_guid = 1;        // ✅ 正确
  int32 level = 2;            // ✅ 正确
  string playerName = 3;       // ✅ 正确
  int64 CharGUID = 4;         // ❌ 错误
  int32 Level = 5;            // ❌ 错误
}
```

## 三、模块分类与组织

### 3.1 模块号映射

详见 [MODULE_MAPPING.md](./MODULE_MAPPING.md)

### 3.2 文件组织原则

1. **按模块号范围分组**
   - 每个模块号范围对应一个proto文件
   - 模块号范围：10000-10099 → auth.proto

2. **通用类型统一管理**
   - 所有枚举类型放在 `common.proto`
   - 所有共享数据结构放在 `common.proto`
   - 跨模块使用的消息放在 `common.proto`

3. **请求/响应配对**
   - 每个请求都有对应的响应
   - 请求和响应在同一个proto文件中
   - 请求和响应的字段序号独立

## 四、Proto文件模板

### 4.1 基本模板

```protobuf
syntax = "proto3";

package dnf.v1;

option go_package = "gen/dnf/v1";
option java_package = "com.dnfm.mina.protobuf.generated";
option java_outer_classname = "AuthProto";

// 导入通用类型
import "dnf/v1/common.proto";

// ========== 请求消息 ==========

// 登录请求 (module=10000, cmd=1)
message LoginRequest {
  string openid = 1;
  uint32 type = 2;
  string token = 3;
  uint32 plat_id = 4;
  string client_ip = 5;
  string version = 6;
}

// 选择角色请求 (module=10001, cmd=1)
message SelectCharacterRequest {
  int64 uid = 1;
}

// ========== 响应消息 ==========

// 登录响应 (module=10000, cmd=2)
message LoginResponse {
  int32 error = 1;
  string auth_key = 2;
  string account_key = 3;
  bool encrypt = 4;
  uint64 server_time = 5;
  repeated ChannelInfo channels = 6;
}

// 选择角色响应 (module=10001, cmd=2)
message SelectCharacterResponse {
  int32 error = 1;
  string server_ip = 2;
  int32 server_port = 3;
  string auth_token = 4;
}

// ========== 通知消息 ==========

// 服务器关闭通知 (module=10000, cmd=100)
message ServerShutdownNotification {
  int32 reason = 1;
  int32 shutdown_time = 2;
}
```

### 4.2 注释规范

```protobuf
// 单行注释：简要说明消息用途

// 登录请求 (module=10000, cmd=1)
// 用于客户端向服务器发送登录请求
message LoginRequest {
  // 用户唯一标识
  string openid = 1;

  // 登录类型：1=正常登录, 2=游客登录
  uint32 type = 2;

  /*
   * 多行注释：详细说明字段含义
   * token用于验证用户身份
   * 有效期为30分钟
   */
  string token = 3;
}
```

## 五、buf.gen.yaml配置

### 5.1 Java代码生成

```yaml
version: v2
managed:
  enabled: true
  disable:
    - file_option: go_package
      module: buf.build/googleapis/googleapis
  override:
    - file_option: go_package_prefix
      value: gen

plugins:
  # Java Protobuf代码生成
  - remote: buf.build/protocolbuffers/java
    out: gen/java
    opt: paths=source_relative

  # Java gRPC代码生成（可选）
  - remote: buf.build/grpc/java
    out: gen/java
    opt: paths=source_relative

  # Go代码生成（可选）
  # - remote: buf.build/protocolbuffers/go
  #   out: gen/go
  #   opt: paths=source_relative

  # TypeScript代码生成（可选）
  # - remote: buf.build/bufbuild/es
  #   out: gen/ts
  #   opt: target=ts
  #   include_imports: true
```

### 5.2 多语言支持

如需支持多种语言，取消注释相应的插件配置。

## 六、buf.yaml配置

```yaml
version: v2
deps:
  - buf.build/googleapis/googleapis

lint:
  use:
    - BASIC
    - ENUM_ZERO_VALUE_SUFFIX
    - FIELD_LOWER_SNAKE_CASE
    - MESSAGE_NAMES
  except:
    - ENUM_VALUE_PREFIX
    - FIELD_NOT_REQUIRED
    - PACKAGE_DIRECTORY_MATCH
    - PACKAGE_NO_IMPORT_CYCLE
    - PACKAGE_VERSION_SUFFIX
    - ENUM_ZERO_VALUE_SUFFIX
    - PACKAGE_SAME_DIRECTORY
  disallow_comment_ignores: true

breaking:
  use:
    - FILE
  except:
    - EXTENSION_NO_DELETE
    - FIELD_SAME_DEFAULT
```

## 七、代码生成与集成

### 7.1 生成代码

```bash
# 进入proto目录
cd proto

# 生成代码
buf generate

# 检查代码规范
buf lint

# 检查破坏性变更
buf breaking --against .git#main
```

### 7.2 Java项目集成

**1. 将生成的Java代码复制到项目**

```bash
# 复制生成的Java代码
cp -r proto/gen/java/dnf/v1/*.java src/main/java/com/dnfm/mina/protobuf/generated/
```

**2. 修改pom.xml**

```xml
<dependencies>
    <!-- 标准protobuf依赖 -->
    <dependency>
        <groupId>com.google.protobuf</groupId>
        <artifactId>protobuf-java</artifactId>
        <version>3.21.12</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <!-- Protobuf Maven插件（可选，用于自动生成） -->
        <plugin>
            <groupId>org.xolstice.maven.plugins</groupId>
            <artifactId>protobuf-maven-plugin</artifactId>
            <version>0.6.1</version>
            <configuration>
                <protoSourceRoot>${project.basedir}/proto/dnf/v1</protoSourceRoot>
                <outputDirectory>target/generated-sources/protobuf/java</outputDirectory>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

**3. 使用生成的代码**

```java
import com.dnfm.mina.protobuf.generated.AuthProto;
import com.dnfm.mina.protobuf.generated.CommonProto;

// 创建登录请求
AuthProto.LoginRequest request = AuthProto.LoginRequest.newBuilder()
    .setOpenid("user123")
    .setType(1)
    .setToken("abc123")
    .build();

// 序列化
byte[] data = request.toByteArray();

// 反序列化
AuthProto.LoginRequest parsed = AuthProto.LoginRequest.parseFrom(data);
```

## 八、与JProtobuf的映射关系

### 8.1 消息映射

| JProtobuf | 标准Protobuf | 说明 |
|-----------|--------------|------|
| `LOGIN.REQ` | `LoginRequest` | 登录请求 |
| `LOGIN.RES` | `LoginResponse` | 登录响应 |
| `PT_ITEM` | `Item` | 物品数据结构 |
| `ENUM_JOB` | `Job` | 职业枚举 |

### 8.2 字段映射

| JProtobuf | 标准Protobuf |
|-----------|--------------|
| `@Protobuf(fieldType = FieldType.UINT64, order = 1)` | `uint64 xxx = 1;` |
| `@Protobuf(fieldType = FieldType.STRING, order = 2)` | `string xxx = 2;` |
| `@Protobuf(fieldType = FieldType.OBJECT, order = 3)` | `Message xxx = 3;` |

### 8.3 枚举映射

**JProtobuf:**
```java
public enum ENUM_JOB {
    SWORDMAN(0),
    FIGHTER(1),
    MAGE(2);

    private int value;
    ENUM_JOB(int value) { this.value = value; }
    public int getValue() { return value; }
}
```

**标准Protobuf:**
```protobuf
enum Job {
  JOB_UNSPECIFIED = 0;
  SWORDMAN = 1;
  FIGHTER = 2;
  MAGE = 3;
}
```

## 九、开发流程

### 9.1 添加新消息

1. **确定模块号和命令号**
   - 查看 [MODULE_MAPPING.md](./MODULE_MAPPING.md)
   - 确定消息所属模块

2. **编辑对应的proto文件**
   - 在对应的proto文件中添加消息定义
   - 遵循命名规范

3. **生成代码**
   ```bash
   cd proto && buf generate
   ```

4. **复制到项目**
   ```bash
   cp -r proto/gen/java/dnf/v1/*.java src/main/java/com/dnfm/mina/protobuf/generated/
   ```

5. **更新编解码器**
   - 在 `ProtobufParserRegistry` 中注册新的Parser
   - 更新消息路由逻辑

6. **测试**
   - 编写单元测试
   - 进行集成测试

### 9.2 修改现有消息

1. **检查破坏性变更**
   ```bash
   cd proto && buf breaking --against .git#main
   ```

2. **修改proto文件**
   - 遵循protobuf兼容性规则
   - 不能删除或重命名字段
   - 可以添加新字段

3. **生成代码并测试**

## 十、最佳实践

### 10.1 字段序号管理

1. **保留字段序号**
   - 删除字段时保留序号
   - 添加 `reserved` 声明

```protobuf
message Character {
  reserved 5, 7, 10 to 12;
  reserved "old_field", "another_old_field";

  int64 char_guid = 1;
  string name = 2;
  int32 level = 3;
}
```

2. **预留字段序号**
   - 为未来扩展预留序号
   - 建议每10个序号预留1-2个

### 10.2 消息设计原则

1. **保持消息简洁**
   - 单个消息不超过20个字段
   - 复杂结构拆分为多个消息

2. **使用枚举替代魔法数字**
   ```protobuf
   enum JobType {
     JOB_TYPE_UNSPECIFIED = 0;
     SWORDMAN = 1;
     FIGHTER = 2;
   }

   message Character {
     JobType job = 1;  // ✅ 使用枚举
     // int32 job = 1;  // ❌ 使用魔法数字
   }
   ```

3. **使用repeated而非数组**
   ```protobuf
   message CharacterListResponse {
     repeated Character characters = 1;  // ✅ 使用repeated
     // Character[] characters = 1;      // ❌ 不支持数组
   }
   ```

### 10.3 性能优化

1. **使用更小的字段类型**
   - `int32` vs `int64`
   - `uint32` vs `uint64`
   - 根据实际需求选择

2. **避免嵌套过深**
   - 嵌套层级不超过3层
   - 复杂结构使用引用

3. **使用oneof替代多个可选字段**
   ```protobuf
   message Character {
     oneof equipment {
       Weapon weapon = 1;
       Armor armor = 2;
       Accessory accessory = 3;
     }
   }
   ```

## 十一、迁移计划

### 11.1 阶段1：准备工作

- [x] 清理proto目录
- [x] 创建规划文档
- [ ] 统计需要转换的Java文件数量
- [ ] 制定转换优先级

### 11.2 阶段2：核心模块转换

- [ ] 转换auth.proto（认证模块）
- [ ] 转换character.proto（角色模块）
- [ ] 转换common.proto（通用类型）
- [ ] 测试核心模块

### 11.3 阶段3：其他模块转换

- [ ] 转换dungeon.proto（副本模块）
- [ ] 转换friend.proto（好友模块）
- [ ] 转换party.proto（队伍模块）
- [ ] 转换其他模块...

### 11.4 阶段4：集成与测试

- [ ] 修改编解码器
- [ ] 更新消息路由
- [ ] 全面测试
- [ ] 性能对比

### 11.5 阶段5：上线部署

- [ ] 灰度发布
- [ ] 监控错误率
- [ ] 监控性能指标
- [ ] 全量发布

## 十二、参考资源

- [Protocol Buffers官方文档](https://developers.google.com/protocol-buffers)
- [Buf官方文档](https://buf.build/docs)
- [Protobuf最佳实践](https://protobuf.dev/programming-guides/proto3/)
- [Go项目Proto规范](../.trae/skills/go-project-proto/SKILL.md)
