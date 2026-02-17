# TC002_玩家登录失败_无效openid

## 测试用例概述

| 项目 | 内容 |
|------|------|
| 测试用例ID | TC002 |
| 测试用例名称 | 玩家登录失败_无效openid |
| 所属模块 | 进入游戏系统 |
| 测试类型 | 异常测试 |
| 优先级 | 高 |
| 预计执行时间 | 3分钟 |

## 测试目的

验证使用无效openid登录时返回正确的错误码，并且不创建任何数据库记录。

## 前置条件

### 测试环境要求
1. Java服务端已启动
2. MySQL数据库已连接
3. 测试数据库已初始化
4. 日志记录已开启

### 测试数据准备
1. 无效openid准备
   - openid: `invalid_openid_001`
   - 说明: 不存在于数据库中
   - 账号状态: 不存在

2. 确保测试账号不存在
   - 检查数据库中不存在该openid
   - 如果存在则删除

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

3. 确保测试账号不存在
   - 查询数据库: `SELECT * FROM t_account WHERE openid = 'invalid_openid_001'`
   - 预期: 返回空结果
   - 如果存在则删除

**输入参数**:
```sql
SELECT COUNT(*) FROM t_account WHERE openid = 'invalid_openid_001';
```

**预期输出**:
- 服务端状态: 运行中
- 数据库状态: 连接正常
- 测试账号: 不存在

**实际输出**:
- 待测试

### 步骤2: 构造登录请求（无效openid）
**操作内容**:
1. 创建登录请求对象
   - 请求类型: REQ_LOGIN
   - 协议版本: 1.0
   - 设备信息: 测试设备

2. 设置登录参数（使用无效openid）
   - openid: `invalid_openid_001`（不存在）
   - 设备ID: `device_test_002`
   - 设备类型: Android
   - 客户端版本: 1.0.0

3. 序列化请求
   - 使用jprotobuf序列化
   - 编码格式: UTF-8
   - 压缩: 不压缩

**输入参数**:
```java
REQ_LOGIN req = new REQ_LOGIN();
req.setOpenid("invalid_openid_001");
req.setDeviceId("device_test_002");
req.setDeviceType(1); // Android
req.setClientVersion("1.0.0");
```

**预期输出**:
- 请求对象: 创建成功
- 序列化结果: 字节数组
- 序列化长度: > 0

**实际输出**:
- 待测试

### 步骤3: 发送登录请求
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

### 步骤4: 接收并解析响应
**操作内容**:
1. 接收响应数据
   - 读取方式: 循环读取
   - 每次读取大小: 1024字节
   - 总读取超时: 15000ms

2. 解析响应类型
   - 读取响应头: 4字节
   - 解析响应类型: RES_LOGIN
   - 验证响应类型正确

3. 反序列化响应
   - 使用jprotobuf反序列化
   - 解码格式: UTF-8
   - 验证数据完整性

**输入参数**:
```java
InputStream in = socket.getInputStream();
byte[] responseBytes = readFully(in);
RES_LOGIN res = RES_LOGIN.parseFrom(responseBytes);
```

**预期输出**:
- 响应类型: RES_LOGIN
- 反序列化结果: 对象创建成功
- 数据完整性: 无损坏

**实际输出**:
- 待测试

### 步骤5: 验证登录失败
**操作内容**:
1. 验证错误码
   - 检查字段: error
   - 预期值: 非0
   - 说明: 错误码表示登录失败

2. 验证错误码具体值
   - 预期错误码: 1001（账号不存在）
   - 或其他合适的错误码
   - 说明: 错误码应该明确表示账号不存在

3. 验证authKey
   - 检查字段: authKey
   - 预期值: null或空字符串
   - 说明: 登录失败不应该返回authKey

4. 验证账号信息
   - 检查字段: account
   - 预期值: null
   - 说明: 登录失败不应该返回账号信息

5. 验证角色列表
   - 检查字段: characters
   - 预期值: null或空数组
   - 说明: 登录失败不应该返回角色列表

**输入参数**:
```java
assertNotEquals(0, res.getError());
assertNull(res.getAuthKey());
assertNull(res.getAccount());
assertNull(res.getCharacters());
```

**预期输出**:
- 错误码: 非0（例如1001）
- authKey: null或空
- 账号信息: null
- 角色列表: null或空

**实际输出**:
- 待测试

### 步骤6: 验证数据库无登录记录
**操作内容**:
1. 查询登录记录
   - 表名: t_account_login_log
   - 查询条件: openid = 'invalid_openid_001'
   - 排序: 按时间倒序

2. 验证记录不存在
   - 预期结果: 返回0条记录
   - 说明: 登录失败不应该创建登录记录

**输入参数**:
```sql
SELECT COUNT(*) FROM t_account_login_log
WHERE openid = 'invalid_openid_001';
```

**预期输出**:
- 登录记录: 0条
- 说明: 登录失败不应该创建登录记录

**实际输出**:
- 待测试

### 步骤7: 验证数据库无token记录
**操作内容**:
1. 查询token记录
   - 表名: t_account_token
   - 查询条件: accountID = ?
   - 说明: 由于账号不存在，accountID也不存在

2. 验证记录不存在
   - 预期结果: 返回0条记录
   - 说明: 登录失败不应该创建token记录

**输入参数**:
```sql
SELECT COUNT(*) FROM t_account_token
WHERE accountID IN (SELECT accountID FROM t_account WHERE openid = 'invalid_openid_001');
```

**预期输出**:
- token记录: 0条
- 说明: 登录失败不应该创建token记录

**实际输出**:
- 待测试

### 步骤8: 验证数据库无账号记录
**操作内容**:
1. 查询账号记录
   - 表名: t_account
   - 查询条件: openid = 'invalid_openid_001'

2. 验证记录不存在
   - 预期结果: 返回0条记录
   - 说明: 登录失败不应该自动创建账号

**输入参数**:
```sql
SELECT COUNT(*) FROM t_account
WHERE openid = 'invalid_openid_001';
```

**预期输出**:
- 账号记录: 0条
- 说明: 登录失败不应该自动创建账号

**实际输出**:
- 待测试

### 步骤9: 验证日志记录
**操作内容**:
1. 检查服务器日志
   - 日志文件: logs/game.log
   - 搜索关键字: invalid_openid_001
   - 验证日志级别: ERROR或WARN

2. 验证日志内容
   - 应该记录登录失败事件
   - 应该记录错误原因
   - 应该记录请求参数

**输入参数**:
```bash
grep "invalid_openid_001" logs/game.log
```

**预期输出**:
- 日志记录: 存在
- 日志级别: ERROR或WARN
- 日志内容: 包含登录失败信息

**实际输出**:
- 待测试

### 步骤10: 清理测试数据
**操作内容**:
1. 确认数据库无记录
   - 检查登录记录: 应该为0
   - 检查token记录: 应该为0
   - 检查账号记录: 应该为0

2. 关闭连接
   - 关闭Socket连接
   - 释放资源
   - 记录日志

**输入参数**:
```sql
SELECT COUNT(*) FROM t_account_login_log WHERE openid = 'invalid_openid_001';
SELECT COUNT(*) FROM t_account_token WHERE accountID IN (SELECT accountID FROM t_account WHERE openid = 'invalid_openid_001');
SELECT COUNT(*) FROM t_account WHERE openid = 'invalid_openid_001';
```

**预期输出**:
- 所有记录: 0条
- 连接状态: 已关闭
- 日志记录: 已记录

**实际输出**:
- 待测试

## 预期结果

### 成功情况
1. 登录请求发送成功
2. 收到登录响应
3. 错误码非0（表示失败）
4. authKey为null或空
5. 账号信息为null
6. 角色列表为null或空
7. 数据库无登录记录
8. 数据库无token记录
9. 数据库无账号记录
10. 日志记录了登录失败事件

### 失败情况
1. 连接失败
2. 请求超时
3. 响应解析失败
4. 错误码为0（不应该）
5. authKey非空（不应该）
6. 账号信息非空（不应该）
7. 数据库创建了记录（不应该）

### 边界情况
1. openid为空字符串
2. openid为null
3. openid超长（超过数据库限制）
4. openid包含特殊字符
5. openid包含SQL注入字符
6. 网络延迟
7. 网络中断
8. 服务器负载高

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
| 步骤2: 构造登录请求 | ⏳ 待执行 | - | - |
| 步骤3: 发送登录请求 | ⏳ 待执行 | - | - |
| 步骤4: 接收并解析响应 | ⏳ 待执行 | - | - |
| 步骤5: 验证登录失败 | ⏳ 待执行 | - | - |
| 步骤6: 验证数据库无登录记录 | ⏳ 待执行 | - | - |
| 步骤7: 验证数据库无token记录 | ⏳ 待执行 | - | - |
| 步骤8: 验证数据库无账号记录 | ⏳ 待执行 | - | - |
| 步骤9: 验证日志记录 | ⏳ 待执行 | - | - |
| 步骤10: 清理测试数据 | ⏳ 待执行 | - | - |

### 测试结果验证

| 验证项 | 预期结果 | 实际结果 | 状态 |
|--------|----------|----------|------|
| 错误码非0 | 非0 | 待测试 | ⏳ |
| 错误码为1001 | 1001 | 待测试 | ⏳ |
| authKey为null | null | 待测试 | ⏳ |
| 账号信息为null | null | 待测试 | ⏳ |
| 角色列表为null | null | 待测试 | ⏳ |
| 登录记录为0 | 0 | 待测试 | ⏳ |
| token记录为0 | 0 | 待测试 | ⏳ |
| 账号记录为0 | 0 | 待测试 | ⏳ |
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

1. **测试数据隔离**: 确保测试使用的openid不存在于数据库中
2. **网络环境**: 确保测试网络稳定
3. **时间同步**: 确保服务器时间准确
4. **日志记录**: 详细记录测试过程和结果
5. **异常处理**: 测试过程中要注意异常情况
6. **数据验证**: 要验证数据库中的实际数据
7. **性能监控**: 记录每个步骤的执行时间
8. **资源清理**: 测试完成后必须清理资源
9. **错误码验证**: 确保错误码准确反映失败原因
10. **日志验证**: 确保日志记录了失败事件

## 参考资源

### Java代码
- Controller: `src/main/java/com/dnfm/game/auth/AuthController.java`
- Service: `src/main/java/com/dnfm/game/auth/service/AuthService.java`
- Model: `src/main/java/com/dnfm/game/auth/model/Account.java`

### Protobuf定义
- 请求: `REQ_LOGIN`
- 响应: `RES_LOGIN`

### 数据库表
- t_account: 账号表
- t_account_login_log: 登录日志表
- t_account_token: token表

### 测试工具
- JUnit: https://junit.org/junit4/
- Mockito: https://site.mockito.org/

### 错误码定义
- 1001: 账号不存在
- 1002: 密码错误
- 1003: 账号被封禁
- 其他错误码请参考错误码定义文档
