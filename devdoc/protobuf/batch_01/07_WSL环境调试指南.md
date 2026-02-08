# WSL环境调试指南

## 概述

由于MySQL数据库安装在WSL环境中，需要将项目切换到WSL进行调试，以完成完整的跨语言通信测试。

## 环境配置

### WSL环境信息

- **操作系统**：Ubuntu (WSL)
- **MySQL端口**：3306
- **Java服务端端口**：10001
- **HTTP服务端口**：20001

### 项目路径映射

- **Windows路径**：`c:\Users\pix\dev\code\java\DnfGameServer`
- **WSL路径**：`/mnt/c/Users/pix/dev/code/java/DnfGameServer`

## 调试步骤

### 1. 启动MySQL数据库

在WSL中启动MySQL数据库：

```bash
# 检查MySQL服务状态
sudo service mysql status

# 如果MySQL未运行，启动MySQL
sudo service mysql start

# 验证MySQL是否正常运行
sudo service mysql status
```

### 2. 连接到MySQL数据库

验证MySQL数据库连接：

```bash
# 连接到MySQL
mysql -uroot -p123456

# 查看数据库列表
SHOW DATABASES;

# 退出MySQL
EXIT;
```

### 3. 检查测试账号

检查是否存在测试账号：

```bash
# 连接到login数据库
mysql -uroot -p123456 -e "USE login; SELECT * FROM account LIMIT 10;"

# 如果没有测试账号，创建一个测试账号
mysql -uroot -p123456 -e "USE login; INSERT INTO account (openid, create_time) VALUES ('test_openid_123', NOW());"
```

### 4. 启动Java服务端

在WSL中启动Java服务端：

```bash
# 进入项目目录
cd /mnt/c/Users/pix/dev/code/java/DnfGameServer

# 设置Java环境变量（如果需要）
export JAVA_HOME=/mnt/c/Users/pix/.sdkman/candidates/java/8.0.462-tem
export PATH=$JAVA_HOME/bin:$PATH

# 启动Java服务端
./mvnw.cmd spring-boot:run
```

**注意**：由于在WSL中运行，需要确保：
1. Java环境变量正确设置
2. Maven能够正常执行
3. 网络端口没有被占用

### 5. 运行Go客户端测试

在WSL中运行Go客户端测试：

```bash
# 进入Go客户端目录
cd /mnt/c/Users/pix/dev/code/java/DnfGameServer/dnf-go-client

# 运行网络通信测试
go test ./test/... -v -run TestLoginRequest_NetworkCommunication
```

### 6. 验证跨语言通信

检查Java服务端日志，验证：

1. **StandardProtobufDecoder是否被调用**：
   ```
   ===== MessageCodecFactory.getDecoder() 被调用，protobuf.mode=standard =====
   ===== 返回 StandardProtobufDecoder =====
   ===== StandardProtobufDecoder.doDecode() 被调用 =====
   ```

2. **登录请求是否被正确解析**：
   ```
   ===== StandardProtobufDecoder.adaptLoginRequest() newRequest=openid: "test_openid_123"
   type: 1
   token: "test_token_abc"
   platID: 1001
   clientIP: "127.0.0.1"
   version: "1.0.0"
   countrycode: "CN"
   agetype: 1
    =====
   ```

3. **StandardProtobufEncoder是否被调用**：
   ```
   ===== StandardProtobufEncoder.encode() 被调用，message=... =====
   ===== StandardProtobufEncoder.encode() moduleId=10001 =====
   ```

4. **Go客户端是否收到响应**：
   ```
   收到响应头: TotalLen=..., Module=10001, Seq=..., TransID=..., Reserved=...
   收到响应体，数据长度: ...
   登录响应解析成功:
     Error: ...
     AuthKey: ...
     AccountKey: ...
     ...
   ```

## 网络配置

### 端口转发

如果需要从Windows访问WSL中的服务，需要配置端口转发：

```bash
# 获取WSL的IP地址
ip addr show eth0

# 在Windows中配置端口转发（以管理员身份运行PowerShell）
netsh interface portproxy add v4tov4 listenport=10001 listenaddress=127.0.0.1 connectport=10001 connectaddress=<WSL_IP>
```

### 防火墙配置

确保防火墙允许相关端口：

```bash
# 检查防火墙状态
sudo ufw status

# 如果需要，开放端口
sudo ufw allow 3306/tcp  # MySQL
sudo ufw allow 10001/tcp  # Java服务端
sudo ufw allow 20001/tcp  # HTTP服务
```

## 常见问题

### 问题1：MySQL连接失败

**错误信息**：`java.net.ConnectException: Connection refused`

**解决方案**：
1. 检查MySQL服务是否运行：`sudo service mysql status`
2. 如果未运行，启动MySQL：`sudo service mysql start`
3. 检查MySQL端口是否正确：`netstat -an | grep 3306`

### 问题2：Java服务端启动失败

**错误信息**：`Address already in use`

**解决方案**：
1. 检查端口占用情况：`netstat -an | grep 10001`
2. 杀死占用端口的进程：`kill -9 <PID>`
3. 重新启动Java服务端

### 问题3：Go客户端连接超时

**错误信息**：`read tcp 127.0.0.1:xxxxx->127.0.0.1:10001: i/o timeout`

**解决方案**：
1. 检查Java服务端是否正常运行
2. 检查网络连接是否正常：`telnet 127.0.0.1 10001`
3. 检查防火墙设置
4. 增加超时时间

### 问题4：账号不存在

**错误信息**：`ERROR== 登录服AccountLogin为空==test_openid_123`

**解决方案**：
1. 创建测试账号：
   ```bash
   mysql -uroot -p123456 -e "USE login; INSERT INTO account (openid, create_time) VALUES ('test_openid_123', NOW());"
   ```
2. 或者使用已存在的账号进行测试

## 调试技巧

### 1. 查看Java服务端日志

Java服务端日志会输出到控制台，包含：
- 编解码器调用日志
- 消息解析日志
- 业务处理日志

### 2. 查看Go客户端输出

Go客户端测试会输出：
- 发送的消息内容
- 接收的响应内容
- 测试结果

### 3. 使用Wireshark抓包

如果需要分析网络数据包，可以使用Wireshark：
1. 安装Wireshark
2. 选择网络接口
3. 过滤端口：`tcp.port == 10001`
4. 分析数据包内容

### 4. 使用telnet测试连接

使用telnet测试网络连接：

```bash
# 测试Java服务端连接
telnet 127.0.0.1 10001

# 测试MySQL连接
telnet 127.0.0.1 3306
```

## 下一步

完成WSL环境调试后：

1. **验证完整通信流程**：确保Go客户端能够发送请求并接收响应
2. **测试标准Protobuf编码器**：验证StandardProtobufEncoder是否正常工作
3. **性能测试**：对比JProtobuf和标准Protobuf的性能差异
4. **继续迁移批次02**：根据迁移计划，继续迁移下一批Java文件
5. **灰度发布**：设计灰度发布策略，逐步切换到标准Protobuf

## 总结

WSL环境调试是完成跨语言通信测试的关键步骤。通过在WSL中运行Java服务端和Go客户端，我们可以：

1. 访问WSL中的MySQL数据库
2. 验证完整的请求-响应循环
3. 测试标准Protobuf编解码器
4. 为后续批次迁移积累经验

按照本指南的步骤，可以顺利完成WSL环境调试，验证跨语言通信的正确性。
