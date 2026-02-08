# 示例 .class 文件

本目录包含用于测试和演示的示例 .class 文件。

## 文件列表

### RES_VERIFICATION_AUTH.class

一个简单的 protobuf 消息类，包含两个整数字段。

**Java 源代码**：
```java
public class RES_VERIFICATION_AUTH extends Message {
    @Protobuf(fieldType = FieldType.INT32, order = 1)
    public Integer error;
    
    @Protobuf(fieldType = FieldType.INT32, order = 2)
    public Integer authkey;
}
```

**生成的 .proto 文件**：
```protobuf
// Generated from class file: RES_VERIFICATION_AUTH.class
syntax = "proto3";
package dnfm.protobuf;

message RES_VERIFICATION_AUTH {
  int32 error = 1;
  int32 authkey = 2;
}
```

### RES_MONEY_ITEM_LIST.class

一个包含 repeated 字段的 protobuf 消息类。

**Java 源代码**：
```java
public class RES_MONEY_ITEM_LIST extends Message {
    @Protobuf(fieldType = FieldType.INT32, order = 1)
    public Integer error;
    
    @Protobuf(order = 2)
    public List<PT_MONEY_ITEM> currency;
    
    @Protobuf(order = 4)
    public List<PT_CURRENCY_DAILY_GAIN> characterdailygain;
}
```

**生成的 .proto 文件**：
```protobuf
// Generated from class file: RES_MONEY_ITEM_LIST.class
syntax = "proto3";
package dnfm.protobuf;

import "common/pt_currency_daily_gain.proto";
import "common/pt_money_item.proto";

message RES_MONEY_ITEM_LIST {
  int32 error = 1;
  repeated PT_MONEY_ITEM currency = 2;
  repeated PT_MONEY_ITEM accountcurrency = 3;
  repeated PT_CURRENCY_DAILY_GAIN characterdailygain = 4;
  repeated PT_CURRENCY_DAILY_GAIN accountdailygain = 5;
}
```

### USER_INFO.class

一个包含多种字段类型的 protobuf 消息类。

**Java 源代码**：
```java
public class USER_INFO extends Message {
    @Protobuf(fieldType = FieldType.UINT64, order = 1)
    public Long charguid;
    
    @Protobuf(fieldType = FieldType.INT32, order = 2)
    public Integer job;
    
    @Protobuf(fieldType = FieldType.INT32, order = 3)
    public Integer growtype;
    
    @Protobuf(fieldType = FieldType.INT32, order = 4)
    public Integer secgrowtype;
    
    @Protobuf(fieldType = FieldType.INT32, order = 5)
    public Integer teamType;
    
    @Protobuf(fieldType = FieldType.INT32, order = 6)
    public Integer world;
    
    @Protobuf(fieldType = FieldType.INT32, order = 7)
    public Integer level;
    
    @Protobuf(fieldType = FieldType.STRING, order = 8)
    public String name;
    
    @Protobuf(fieldType = FieldType.STRING, order = 9)
    public String profileURL;
    
    @Protobuf(fieldType = FieldType.STRING, order = 10)
    public String profileName;
    
    @Protobuf(fieldType = FieldType.INT32, order = 11)
    public Integer characterFrame;
    
    @Protobuf(fieldType = FieldType.INT32, order = 12)
    public Integer rank;
}
```

**生成的 .proto 文件**：
```protobuf
// Generated from class file: USER_INFO.class
syntax = "proto3";
package dnfm.protobuf;

message USER_INFO {
  uint64 charguid = 1;
  int32 job = 2;
  int32 growtype = 3;
  int32 secgrowtype = 4;
  int32 teamType = 5;
  int32 world = 6;
  int32 level = 7;
  string name = 8;
  string profileURL = 9;
  string profileName = 10;
  int32 characterFrame = 11;
  int32 rank = 12;
}
```

## 使用方法

### 1. 查看字节码信息

```bash
# 使用 javap 查看详细信息
javap -p -v RES_VERIFICATION_AUTH.class
```

### 2. 生成 proto 文件

```bash
# 使用脚本生成 proto 文件
python generate_proto_from_class.py --class_dir . --proto_dir .
```

### 3. 验证生成的文件

```bash
# 使用 buf lint 检查语法
buf lint
```

## 注意事项

- 这些示例文件仅用于测试和演示
- 实际使用时，请根据项目需求调整
- 确保已安装 JDK 和 Python 环境
