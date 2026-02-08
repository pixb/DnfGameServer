# Examples

本目录包含Go客户端示例程序，用于测试与Java服务器的通信。

## 文件说明

### login_client.go
登录客户端示例，演示如何：
- 连接到游戏服务器（端口10001）
- 发送登录请求（ModuleID: 10000）
- 接收并解析登录响应
- 显示频道信息、入侵信息等

**运行方式**：
```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-client/examples
go run login_client.go
```

### ping_client.go
PING客户端示例，演示如何：
- 连接到游戏服务器（端口10001）
- 发送PING请求（ModuleID: 10006）
- 接收并解析PING响应

**运行方式**：
```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-client/examples
go run ping_client.go
```

### char_list_client.go
角色列表客户端示例，演示如何：
- 连接到游戏服务器（端口10001）
- 先发送登录请求获取会话
- 再发送角色列表请求（ModuleID: 10002）
- 接收并解析角色列表响应
- 显示角色信息、职业限制、装备列表等

**运行方式**：
```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-client/examples
go run char_list_client.go
```

## 注意事项

1. **服务器端口**：游戏服务器TCP端口为10001，不是HTTP端口20001
2. **登录凭证**：某些示例需要有效的登录凭证，请根据实际情况修改
3. **服务器状态**：运行示例前确保Java服务器已启动
4. **网络连接**：确保可以连接到127.0.0.1:10001

## 测试文件

真正的Go测试文件位于`../test/`目录，使用`go test`命令运行：

```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-client
go test ./test/...
```

## 迁移批次

| 批次 | 消息 | ModuleID | 示例文件 |
| :--- | :--- | :--- | :--- |
| 批次01 | AUTH_LOGIN | 10000 | login_client.go |
| 批次02 | PING | 10006 | ping_client.go |
| 批次03 | CHARACTER | 10002 | char_list_client.go |

## 相关文档

- [../test/](../test/) - Go测试文件
- [../../devdoc/protobuf/](../../devdoc/protobuf/) - 迁移文档
- [../../.trae/skills/pix-jptotobuf-to-proto/](../../.trae/skills/pix-jptotobuf-to-proto/) - JProtobuf迁移技能
