# TC006_选择角色_无效角色GUID

## 测试用例概述

| 项目 | 内容 |
|------|------|
| 测试用例ID | TC006 |
| 测试用例名称 | 选择角色_无效角色GUID |
| 所属模块 | 进入游戏系统 |
| 测试类型 | 异常测试 |
| 优先级 | 高 |
| 预计执行时间 | 2分钟 |

## 测试目的

验证使用无效的角色GUID选择角色时返回正确的错误码，并且不修改任何数据。

## 前置条件

### 测试环境要求
1. Java服务端已启动
2. MySQL数据库已连接
3. 测试数据库已初始化
4. 日志记录已开启

### 测试数据准备
1. 测试账号已注册
   - openid: `test_openid_006`
   - 账号状态: 正常
   - 账号类型: 普通账号

2. 测试角色已创建
   - 角色GUID: 1001
   - 角色名称: TestPlayer1
   - 角色职业: 战士
   - 角色等级: 10
   - 角色状态: 正常

3. 确保无效角色GUID不存在
   - 无效角色GUID: 9999
   - 说明: 不存在于数据库中

### 依赖服务状态
1. 认证服务正常
2. 数据库服务正常
3. 网络连接正常

## 测试步骤

### 步骤1: 准备测试环境
**操作内容**:
1. 检查Java服务端是否启动
   - 命令: `ps aux | grep java`
   - 预期: Java进程存在

2. 检查数据库连接
   - 命令: `mysql -u root -p -e "SHOW DATABASES;"`
   - 预期: 数据库列表正常

3. 准备测试账号和角色
   - 创建测试账号（如果不存在）
   - 创建测试角色（如果不存在）
   - 确保无效角色GUID不存在

**输入参数**:
```sql
-- 创建测试账号
INSERT INTO t_account (openid, accountID, createTime, status) VALUES ('test_openid_006', 10006, NOW(), 1);

-- 创建测试角色
INSERT INTO t_character (charguid, accountID, name, job, level, createTime, status) 
VALUES (1001, 10006, 'TestPlayer1', 1, 10, NOW(), 1);

-- 确保无效角色GUID不存在
DELETE FROM t_character WHERE charguid = 9999;
```

**预期输出**:
- 服务端状态: 运行中
- 数据库状态: 连接正常
- 测试账号: 已创建
- 测试角色: 已创建
- 无效角色GUID: 不存在

**实际输出**:
- 待测试

### 步骤2: 玩家登录获取authKey
**操作内容**:
1. 构造登录请求
   - openid: `test_openid_006`
   - 设备ID: `device_test_006`
   - 设备类型: Android
   - 客户端版本: 1.0.0

2. 发送登录请求
   - 服务器地址: 127.0.0.1
   - 服务器端口: 8080
   - 连接超时: 5000ms

3. 接收登录响应
   - 验证错误码为0
   - 获取authKey
   - 获取accountID

**输入参数**:
```java
REQ_LOGIN req = new REQ_LOGIN();
req.setOpenid("test_openid_006");
req.setDeviceId("device_test_006");
req.setDeviceType(1);
req.setClientVersion("1.0.0");
```

**预期输出**:
- 错误码: 0
- authKey: 非空
- accountID: 10006

**实际输出**:
- 待测试

### 步骤3: 构造选择角色请求（无效角色GUID）
**操作内容**:
1. 创建选择角色请求对象
   - 请求类型: REQ_SELECT_CHARACTER
   - 协议版本: 1.0

2. 设置请求参数（使用无效角色GUID）
   - authKey: 从登录响应获取
   - accountID: 从登录响应获取
   - charguid: 9999（无效）

3. 序列化请求
   - 使用jprotobuf序列化
   - 编码格式: UTF-8
   - 压缩: 不压缩

**输入参数**:
```java
REQ_SELECT_CHARACTER req = new REQ_SELECT_CHARACTER();
req.setAuthKey(authKey);
req.setAccountID(accountID);
req.setCharguid(9999); // 无效的角色GUID
```

**预期输出**:
- 请求对象: 创建成功
- 序列化结果: 字节数组
- 序列化长度: > 0

**实际输出**:
- 待测试

### 步骤4: 发送选择角色请求
**操作内容**:
1. 建立TCP连接
   - 服务器地址: 127.0.0.1
   - 服务器端口: 8080
   - 连接超时: 5000ms

2. 发送序列化后的请求
   - 发送方式: 同步发送
   - 发送超时: 10000ms
   - 重试次数: 3

3. 等待响应
   - 等待超时: 15000ms
   - 响应缓冲区大小: 4096

**输入参数**:
```java
Socket socket = new Socket("127.0.0.1", 8080);
OutputStream out = socket.getOutputStream();
out.write(reqBytes);
out.flush();
```

**预期输出**:
- 连接状态: 已连接
- 发送状态: 发送成功
- 接收状态: 收到响应

**实际输出**:
- 待测试

### 步骤5: 接收并解析响应
**操作内容**:
1. 接收响应数据
   - 读取方式: 循环读取
   - 每次读取大小: 1024字节
   - 总读取超时: 15000ms

2. 解析响应类型
   - 读取响应头: 4字节
   - 解析响应类型: RES_SELECT_CHARACTER
   - 验证响应类型正确

3. 反序列化响应
   - 使用jprotobuf反序列化
   - 解码格式: UTF-8
   - 验证数据完整性

**输入参数**:
```java
InputStream in = socket.getInputStream();
byte[] responseBytes = readFully(in);
RES_SELECT_CHARACTER res = RES_SELECT_CHARACTER.parseFrom(responseBytes);
```

**预期输出**:
- 响应类型: RES_SELECT_CHARACTER
- 反序列化结果: 对象创建成功
- 数据完整性: 无损坏

**实际输出**:
- 待测试

### 步骤6: 验证选择角色失败
**操作内容**:
1. 验证错误码
   - 检查字段: error
   - 预期值: 非0
   - 说明: 错误码表示角色不存在

2. 验证错误码具体值
   - 预期错误码: 2001（角色不存在）
   - 或其他合适的错误码
   - 说明: 错误码应该明确表示角色不存在

3. 验证角色信息
   - 检查字段: character
   - 预期值: null
   - 说明: 选择失败不应该返回角色信息

**输入参数**:
```java
assertNotEquals(0, res.getError());
assertEquals(2001, res.getError()); // 角色不存在错误码
assertNull(res.getCharacter());
```

**预期输出**:
- 错误码: 非0（例如2001）
- 角色信息: null

**实际输出**:
- 待测试

### 步骤7: 验证角色状态未改变
**操作内容**:
1. 查询角色记录
   - 表名: t_character
   - 查询条件: charguid = 1001

2. 验证角色状态
   - 检查字段: status
   - 预期值: 0（未选择）或保持原值
   - 说明: 选择失败不应该修改角色状态

3. 验证最后登录时间
   - 检查字段: lastLoginTime
   - 预期值: 保持原值
   - 说明: 选择失败不应该更新最后登录时间

**输入参数**:
```sql
SELECT status, lastLoginTime FROM t_character WHERE charguid = 1001;
```

**预期输出**:
- 角色状态: 保持原值
- 最后登录时间: 保持原值
- 说明: 数据未被修改

**实际输出**:
- 待测试

### 步骤8: 验证数据库无新记录
**操作内容**:
1. 查询角色记录数量
   - 表名: t_character
   - 查询条件: accountID = 10006
   - 预期值: 1（只有测试角色）

2. 验证无效角色GUID不存在
   - 表名: t_character
   - 查询条件: charguid = 9999
   - 预期值: 0条记录

**输入参数**:
```sql
SELECT COUNT(*) FROM t_character WHERE accountID = 10006;
SELECT COUNT(*) FROM t_character WHERE charguid = 9999;
```

**预期输出**:
- 角色记录数量: 1
- 无效角色GUID记录: 0条
- 说明: 没有创建新记录

**实际输出**:
- 待测试

### 步骤9: 验证日志记录
**操作内容**:
1. 检查服务器日志
   - 日志文件: logs/game.log
   - 搜索关键字: test_openid_006
   - 验证日志级别: ERROR或WARN

2. 验证日志内容
   - 应该记录选择角色失败事件
   - 应该记录错误原因
   - 应该记录无效的角色GUID

**输入参数**:
```bash
grep "test_openid_006" logs/game.log | grep "select character"
```

**预期输出**:
- 日志记录: 存在
- 日志级别: ERROR或WARN
- 日志内容: 包含选择角色失败信息
- 错误原因: 角色不存在

**实际输出**:
- 待测试

### 步骤10: 清理测试数据
**操作内容**:
1. 删除角色记录
   - 表名: t_character
   - 删除条件: accountID = 10006

2. 删除账号记录
   - 表名: t_account
   - 删除条件: accountID = 10006

3. 关闭连接
   - 关闭Socket连接
   - 释放资源
   - 记录日志

**输入参数**:
```sql
DELETE FROM t_character WHERE accountID = 10006;
DELETE FROM t_account WHERE accountID = 10006;
```

**预期输出**:
- 角色记录: 已删除
- 账号记录: 已删除
- 连接状态: 已关闭
- 日志记录: 已记录

**实际输出**:
- 待测试

## 预期结果

### 成功情况
1. 登录请求发送成功
2. 收到登录响应，获取到authKey
3. 选择角色请求发送成功
4. 收到选择角色响应
5. 错误码非0（表示失败）
6. 错误码为2001（角色不存在）
7. 角色信息为null
8. 角色状态未改变
9. 数据库无新记录
10. 日志记录了选择角色失败事件

### 失败情况
1. 连接失败
2. 请求超时
3. 响应解析失败
4. 错误码为0（不应该）
5. 角色信息非空（不应该）
6. 角色状态被修改（不应该）
7. 数据库创建了新记录（不应该）

### 边界情况
1. 角色GUID为0
2. 角色GUID为负数
3. 角色GUID超长
4. 角色GUID包含特殊字符
5. 角色GUID为null
6. 角色GUID属于其他账号
7. 角色GUID已被删除
8. 角色GUID被封禁

## 实际结果

### 测试执行记录

| 项目 | 内容 |
|------|------|
| 测试日期 | 待测试 |
| 测试人员 | 待测试 |
| 测试环境 | 待测试 |
| 执行时间 | 待测试 |

### 测试步骤执行情况

| 步骤 | 状态 | 执行时间 | 备注 |
|------|------|---------|------|
| 步骤1: 准备测试环境 | ⏳ 待执行 | - | - |
| 步骤2: 玩家登录获取authKey | ⏳ 待执行 | - | - |
| 步骤3: 构造选择角色请求 | ⏳ 待执行 | - | - |
| 步骤4: 发送选择角色请求 | ⏳ 待执行 | - | - |
| 步骤5: 接收并解析响应 | ⏳ 待执行 | - | - |
| 步骤6: 验证选择角色失败 | ⏳ 待执行 | - | - |
| 步骤7: 验证角色状态未改变 | ⏳ 待执行 | - | - |
| 步骤8: 验证数据库无新记录 | ⏳ 待执行 | - | - |
| 步骤9: 验证日志记录 | ⏳ 待执行 | - | - |
| 步骤10: 清理测试数据 | ⏳ 待执行 | - | - |

### 测试结果验证

| 验证项 | 预期结果 | 实际结果 | 状态 |
|--------|----------|----------|------|
| 错误码非0 | 非0 | 待测试 | ⏳ |
| 错误码为2001 | 2001 | 待测试 | ⏳ |
| 角色信息为null | null | 待测试 | ⏳ |
| 角色状态未改变 | 保持原值 | 待测试 | ⏳ |
| 最后登录时间未改变 | 保持原值 | 待测试 | ⏳ |
| 无效角色GUID记录为0 | 0 | 待测试 | ⏳ |
| 日志记录存在 | 存在 | 待测试 | ⏳ |

### 测试状态
- 总体状态: ⏳ 待测试
- 通过步骤: 0/10
- 失败步骤: 0/10
- 测试结论: 待测试

## 问题记录

### 发现的问题

| 问题ID | 问题描述 | 严重程度 | 状态 | 修复建议 |
|--------|----------|----------|------|----------|
| - | - | - | - | - |

### 已知问题

| 问题ID | 问题描述 | 影响 | 临时方案 |
|--------|----------|------|----------|
| - | - | - | - |

## 注意事项

1. **测试数据隔离**: 确保测试使用的无效角色GUID不存在
2. **数据验证**: 要验证数据库中的实际数据
3. **状态验证**: 确保选择失败时不会修改角色状态
4. **日志记录**: 详细记录测试过程和结果
5. **性能监控**: 记录每个步骤的执行时间
6. **资源清理**: 测试完成后必须清理测试数据
7. **异常处理**: 测试过程中要注意异常情况
8. **错误码验证**: 确保错误码准确反映失败原因
9. **日志验证**: 确保日志记录了失败事件
10. **边界测试**: 注意角色GUID的各种边界情况

## 参考资源

### Java代码
- Controller: `src/main/java/com/dnfm/game/character/CharacterController.java`
- Service: `src/main/java/com/dnfm/game/character/service/CharacterService.java`
- Model: `src/main/java/com/dnfm/game/character/model/CharacterInfo.java`

### Protobuf定义
- 请求: `REQ_SELECT_CHARACTER`
- 响应: `RES_SELECT_CHARACTER`

### 数据库表
- t_account: 账号表
- t_character: 角色表

### 测试工具
- JUnit: https://junit.org/junit4/
- Mockito: https://site.mockito.org/

### 错误码定义
- 2001: 角色不存在
- 2002: 角色不属于该账号
- 2003: 角色状态异常
- 其他错误码请参考错误码定义文档
