# TC002_玩家登录失败_无效openid

## 测试用例信息

| 项目 | 内容 |
|------|------|
| 测试用例ID | TC002 |
| 测试用例名称 | 玩家登录失败_无效openid |
| 测试级别 | 2 |
| 测试类型 | 异常测试 |
| 优先级 | 高 |
| 测试人员 | AI Assistant |
| 创建日期 | 2026-02-17 |
| 更新日期 | 2026-02-17 |

## 测试目的

验证使用无效openid登录时返回正确的错误码，并且不创建任何数据库记录。

## 测试前置条件

1. 游戏服务器已启动并正常运行
2. 数据库服务已启动并正常运行
3. 测试数据已准备（确保无效openid不存在于数据库中）

## 测试数据

### 输入数据
- openid: `invalid_openid_002`（无效的openid）
- type: `1`
- token: `test_token_002`
- platID: `1001`
- clientIP: `127.0.0.1`
- version: `1.0.0`

### 预期输出数据
- error: 非零值（登录失败）
- authkey: null或空
- accountkey: null或空

## 测试步骤

### 步骤1: 建立TCP连接
**操作内容**:
1. 创建TCP Socket连接
   - 服务器地址: 127.0.0.1
   - 服务器端口: 20001（根据application.properties配置）
   - 连接超时: 5000ms

2. 验证连接状态
   - 检查连接是否成功建立
   - 检查Socket是否可用

**预期结果**:
- 连接成功建立
- Socket状态为已连接

### 步骤2: 清理测试数据
**操作内容**:
1. 查询数据库
   - 查询t_account表
   - 检查是否存在openid为`invalid_openid_002`的账号

2. 清理测试数据
   - 如果存在该账号，则删除
   - 确保测试环境干净

**输入参数**:
```sql
DELETE FROM t_account WHERE openid = 'invalid_openid_002';
```

**预期结果**:
- 数据库中不存在该openid
- 测试环境干净

### 步骤3: 构造登录请求（无效openid）
**操作内容**:
1. 创建REQ_LOGIN对象
   - 设置openid: `invalid_openid_002`（无效）
   - 设置type: `1`
   - 设置token: `test_token_002`
   - 设置platID: `1001`
   - 设置clientIP: `127.0.0.1`
   - 设置version: `1.0.0`

2. 序列化请求对象
   - 使用jprotobuf的Codec进行序列化
   - 生成字节数组

**输入参数**:
```java
REQ_LOGIN req = new REQ_LOGIN();
req.setOpenid("invalid_openid_002");
req.setType(1);
req.setToken("test_token_002");
req.setPlatID(1001);
req.setClientIP("127.0.0.1");
req.setVersion("1.0.0");
```

**预期结果**:
- REQ_LOGIN对象创建成功
- 序列化成功，生成字节数组

### 步骤4: 发送登录请求
**操作内容**:
1. 构造消息包
   - 消息ID: 根据MessageMeta注解（module=10000, cmd=0）
   - 消息长度: 序列化后的字节数组长度
   - 消息体: 序列化后的字节数组

2. 发送消息包
   - 通过Socket输出流发送
   - 确保数据完整发送

**预期结果**:
- 消息包构造成功
- 消息发送成功

### 步骤5: 接收登录响应
**操作内容**:
1. 接收响应数据
   - 从Socket输入流读取数据
   - 解析消息包（消息ID、消息长度、消息体）

2. 反序列化响应对象
   - 使用jprotobuf的Codec进行反序列化
   - 转换为RES_LOGIN对象

**预期输出**:
- error: 非零值（登录失败）
- authkey: null或空
- accountkey: null或空

### 步骤6: 验证登录失败
**操作内容**:
1. 验证错误码
   - 检查error字段是否为非零值

2. 验证authkey
   - 检查authkey是否为null或空

3. 验证accountkey
   - 检查accountkey是否为null或空

**预期结果**:
- error != 0（登录失败）
- authkey为null或空
- accountkey为null或空

### 步骤7: 数据库验证
**操作内容**:
1. 查询数据库
   - 查询t_account表
   - 检查是否创建了新账号

2. 验证账号数据
   - 确保没有创建新账号
   - 确保数据库中没有该openid的记录

**预期结果**:
- 数据库中没有创建新账号
- 数据库中没有该openid的记录

### 步骤8: 清理测试数据
**操作内容**:
1. 关闭Socket连接
2. 清理测试数据（可选）

**预期结果**:
- 连接正常关闭
- 测试数据清理完成

## 验证点

| 验证点 | 验证方法 | 预期结果 |
|--------|----------|----------|
| TCP连接建立 | 检查Socket状态 | 连接成功 |
| 登录请求发送 | 检查发送字节数 | 发送成功 |
| 登录响应接收 | 检查接收字节数 | 接收成功 |
| 错误码验证 | 检查error字段 | error != 0 |
| authkey验证 | 检查authkey为空 | authkey为null或空 |
| accountkey验证 | 检查accountkey为空 | accountkey为null或空 |
| 数据库验证 | 查询t_account表 | 没有创建新账号 |

## 测试结果

| 测试项 | 结果 | 备注 |
|--------|------|------|
| TCP连接建立 | ⏳ 待测试 | |
| 登录请求发送 | ⏳ 待测试 | |
| 登录响应接收 | ⏳ 待测试 | |
| 错误码验证 | ⏳ 待测试 | |
| authkey验证 | ⏳ 待测试 | |
| accountkey验证 | ⏳ 待测试 | |
| 数据库验证 | ⏳ 待测试 | |

## 测试结论

| 项目 | 结果 |
|------|------|
| 测试状态 | ⏳ 待测试 |
| 通过/失败 | - |
| 执行时间 | - |
| 测试人员 | AI Assistant |
| 测试日期 | 2026-02-17 |

## 依赖关系

- 依赖: 无
- 被依赖: TC003, TC004, TC005, TC006, TC007, TC008, TC009, TC010

## 备注

1. 本测试用例使用实际的Protobuf消息类：`com.dnfm.mina.protobuf.REQ_LOGIN` 和 `com.dnfm.mina.protobuf.RES_LOGIN`
2. 使用jprotobuf进行序列化和反序列化
3. 使用Apache MINA框架的客户端进行连接
4. 服务器端口根据application.properties配置为20001
5. 测试数据需要根据实际数据库表结构进行调整
6. 验证无效openid不会创建数据库记录

## 相关文件

- 测试代码: `TC002_玩家登录失败_无效openid.java`
- Protobuf消息: `com.dnfm.mina.protobuf.REQ_LOGIN`, `com.dnfm.mina.protobuf.RES_LOGIN`
- 配置文件: `application.properties`
- 数据库表: `t_account`
